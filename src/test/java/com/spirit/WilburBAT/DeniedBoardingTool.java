package com.spirit.WilburBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DeniedBoardingTool extends BaseClass {

    @Parameters({"Platform"})
    @Test()
    public void DeniedBoardingTool(@Optional("NA") String platform) {

        openBrowser(platform);
        getDriver().get("http://miadesc03:2380/#/");
        WaitUtil.untilTimeCompleted(3000);

        //wait until DBT is loaded successful
        while(dbt_isLoaded())
        {
            WaitUtil.untilTimeCompleted(1000);
        }

        selectCity("FLL");
        selectDate("0");
        searchBDT();

        //wait for Stations Flight List to load
        while(!dbt_FlightTableLoaded())
        {
            WaitUtil.untilTimeCompleted(1000);
        }

        selectFlightOnDBT("777");
        selectMemberFromFlightManifest("Test/MaddyAutomation", "F6ZMGL");
//        selectPassengerDeniedBoardingOptions("INVOL" , "Overbooked");
//        SelectRebookOptions("yes" , "OA | SS | 500.00 | MAX" , "1-2" );
//        SelectCompensationAndVoucher("YES | 7862645654321");
//        selectAnemitites("YES | 200 | Direct Bill" , "NO" , "YES | 50 | Voucher");
        selectBid("Bid3");
        selectBid("400");


    }

    public void selectCity(String DEP_CityCode)
    {
        DEP_CityCode = DEP_CityCode.toUpperCase();
        Select cityDropDown = new Select(pageObjectManager.getDeniedBoardingToolPage().getSelectStationDropDown());
        cityDropDown.selectByVisibleText(DEP_CityCode);

        ValidationUtil.validateTestStep(DEP_CityCode + " was selected from the station Drop Down" ,
                pageObjectManager.getDeniedBoardingToolPage().getSelectStationDropDown().getText() , DEP_CityCode);
    }

    public void selectDate(String DEP_Date)
    {
        // if date equals 0,
        if(DEP_Date.equals("0"))
        {
            pageObjectManager.getDeniedBoardingToolPage().getTodayRadioButton().click();
            ValidationUtil.validateTestStep("The user selects to use Today radio Button" ,
                    pageObjectManager.getDeniedBoardingToolPage().getTodayRadioButton().isSelected());
        }
        else
        {
            String dep_DateFormat = TestUtil.getStringDateFormat(DEP_Date,"MM/DD/YYYY"); //get dep date
            String depEnd_DateFormat = TestUtil.getStringDateFormat(Integer.parseInt(DEP_Date) + 1 ,"MM/DD/YYYY"); //get one day added to dep

            pageObjectManager.getDeniedBoardingToolPage().getDateRangeRadioButton().click();
            ValidationUtil.validateTestStep("The user selects to use date range option" ,
                    pageObjectManager.getDeniedBoardingToolPage().getDateRangeRadioButton().isSelected());
            WaitUtil.untilTimeCompleted(1000);
            pageObjectManager.getDeniedBoardingToolPage().getDateRangeStartTextBox().clear();
            pageObjectManager.getDeniedBoardingToolPage().getDateRangeStartTextBox().sendKeys(dep_DateFormat);
            pageObjectManager.getDeniedBoardingToolPage().getDateRangeEndTextBox().clear();
            pageObjectManager.getDeniedBoardingToolPage().getDateRangeEndTextBox().sendKeys(depEnd_DateFormat);
            ValidationUtil.validateTestStep("The user selects to use date " + dep_DateFormat + " through " + depEnd_DateFormat ,
                    pageObjectManager.getDeniedBoardingToolPage().getDateRangeRadioButton().isSelected());
        }
    }

    public void searchBDT()
    {
        pageObjectManager.getDeniedBoardingToolPage().getSearchButton().click();
        ValidationUtil.validateTestStep("The user clicked on search button" , true);
    }

    public void selectFlightOnDBT(String overBookedFlight)
    {
        for(WebElement flightNum : pageObjectManager.getDeniedBoardingToolPage().getTableFlightNumberButton())
        {
            if (flightNum.getText().contains(overBookedFlight))
            {
                flightNum.click();
                ValidationUtil.validateTestStep("Flight Number \"" + overBookedFlight + "\" was selected" , true);
            }
        }
    }

    public boolean dbt_isLoaded()
    {

        System.out.println(TestUtil.verifyElementDisplayed(pageObjectManager.getDeniedBoardingToolPage().getPageLoadToasterPanel()));
        return !(TestUtil.verifyElementDisplayed(pageObjectManager.getDeniedBoardingToolPage().getPageLoadToasterPanel()));
    }

    public boolean dbt_FlightTableLoaded()
    {
        WaitUtil.untilTimeCompleted(500);
        return !(TestUtil.verifyElementDisplayed(pageObjectManager.getDeniedBoardingToolPage().getFlightTableLoadSpinner()));
    }

    public void selectMemberFromFlightManifest(String paxName , String PNR)
    {
        //pax name is a max length of 17 chars
        paxName = paxName.toUpperCase().substring(0 , 16);
        boolean pnrFound = false;//store if PNR was found

        //Store the index of the reservations that match PNR (names can repeat on a flight with different PNR)
        List<Integer> reservationIndexOnManifestList = new ArrayList<Integer>();
        int manifestCounter = 0;
        for (WebElement reservation :  pageObjectManager.getDeniedBoardingToolPage().getManifestTablePNRText())
        {
            if( reservation.getText().contains(PNR))
            {
                reservationIndexOnManifestList.add(manifestCounter);
            }
            manifestCounter++;
        }

        System.out.println("There are " + reservationIndexOnManifestList.size() + " passengers found with this Name: " + paxName);

        //Get the names for that PNR
        for(int indexOfPNR : reservationIndexOnManifestList)
        {
            //if this passenger name contains the name passed as a parameter, click on that name
            if(pageObjectManager.getDeniedBoardingToolPage().getManifestTablePaxNameText().get(indexOfPNR).getText().contains(paxName))
            {
                ValidationUtil.validateTestStep("The passenger name was found as " + pageObjectManager.getDeniedBoardingToolPage().getManifestTablePaxNameText().get(indexOfPNR).getText() , true);
                pageObjectManager.getDeniedBoardingToolPage().getManifestTablePaxNameText().get(indexOfPNR).click();
                pnrFound = true;
            }
        }

        ValidationUtil.validateTestStep("The Passenger "+ paxName +" and PNR \"" + PNR + "\" was found in this flight" , pnrFound && (reservationIndexOnManifestList.size() > 0) );
    }

    public void selectPassengerDeniedBoardingOptions(String deniedBoardingStatus , String reason)
    {
        pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingTab().click();

        switch (deniedBoardingStatus.toUpperCase())
        {
            case "INVOL":
                pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingStatusINVOLRadioButton().click();
                ValidationUtil.validateTestStep("The INVOL check box was selected" , pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingStatusINVOLRadioButton().isSelected());
                break;
            case "VOL":
                pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingStatusVOLRadioButton().click();
                ValidationUtil.validateTestStep("The VOL check box was selected" , pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingStatusVOLRadioButton().isSelected());
                break;
        }

        switch (reason.toLowerCase().replace(" ", ""))
        {
            case "equipmentdowngrade":
                pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingReasonEquipmentDowngradeRadioButton().click();
                ValidationUtil.validateTestStep("The Equipment Downgrade Reason check box was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingReasonEquipmentDowngradeRadioButton().isSelected());
                break;
            case "overbooked":
                pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingReasonOverbookedRadioButton().click();
                ValidationUtil.validateTestStep("The Overbooked Reason check box was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingReasonOverbookedRadioButton().isSelected());
                break;
            case "weightrestriction":
                pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingReasonWeightRestrictionRadioButton().click();
                ValidationUtil.validateTestStep("The Weight Restriction Reason check box was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getDeniedBoardingReasonWeightRestrictionRadioButton().isSelected());
                break;
        }

    }

    /**
     * @param rebookWith when selecting rebookWith paramater must follow format "NK" or "OA | XX | ###.## | ITS /P "
     */
    public void SelectRebookOptions(String rebook , String rebookWith , String arrivalTimeFrame)
    {
        pageObjectManager.getDeniedBoardingToolPage().getRebookTab().click();
        WaitUtil.untilTimeCompleted(1000);

        if(rebook.toUpperCase().equals("NO"))
        {
            pageObjectManager.getDeniedBoardingToolPage().getRebookNoRadioButton().click();
            ValidationUtil.validateTestStep("The Rebook \"No\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getRebookNoRadioButton().isSelected());
        }
        //else select yes and select the other check Boxes
        else
        {
            pageObjectManager.getDeniedBoardingToolPage().getRebookYesRadioButton().click();
            ValidationUtil.validateTestStep("The Rebook \"Yes\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getRebookYesRadioButton().isSelected());

            //select the Rebook With Option
            if (rebookWith.toUpperCase().equals("NK"))
            {
                pageObjectManager.getDeniedBoardingToolPage().getRebookWithNKRadioButton().click();
                ValidationUtil.validateTestStep("The Rebook With \"NK\" check box was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getRebookWithNKRadioButton().isSelected());
            }
            else if(rebookWith.toUpperCase().contains("OA"))
            {
                String rebookWithDetail[] = rebookWith.replace(" ","").split("\\|"); //separate all OA details by "|"

                pageObjectManager.getDeniedBoardingToolPage().getRebookWithOARadioButton().click();
                ValidationUtil.validateTestStep("The Rebook With \"OA\" check box was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getRebookWithOARadioButton().isSelected());

                //Airline Code textBox
                pageObjectManager.getDeniedBoardingToolPage().getRebookTwoLetterAirlineCodeTextBox().sendKeys(rebookWithDetail[1].trim());
                ValidationUtil.validateTestStep(rebookWithDetail[1].trim() + " input as the 2 letter Airline Code", true);
                //ticket amount textBox
                pageObjectManager.getDeniedBoardingToolPage().getRebookTicketAmountTextBox().sendKeys(rebookWithDetail[2].trim());
                ValidationUtil.validateTestStep(rebookWithDetail[2].trim() + " input into the Ticket Amount TextBox", true);
                //select form of payment checkbox

                if(rebookWithDetail[3].contains("ITS") || rebookWithDetail[3].contains("MAX"))
                {
                    pageObjectManager.getDeniedBoardingToolPage().getRebookFormOfPaymentITSMAXRadioButton().click();
                    ValidationUtil.validateTestStep("The Form of Payment \"ITS\\MAX\" check box was selected" ,
                            pageObjectManager.getDeniedBoardingToolPage().getRebookFormOfPaymentITSMAXRadioButton().isSelected());
                }
                else
                {
                    pageObjectManager.getDeniedBoardingToolPage().getRebookFormOfPaymentPCardRadioButton().click();
                    ValidationUtil.validateTestStep("The Form of Payment \"P-card\" check box was selected" ,
                            pageObjectManager.getDeniedBoardingToolPage().getRebookFormOfPaymentPCardRadioButton().isSelected());
                }
            }

            //select the Arrival Time Frame
            if (arrivalTimeFrame.toUpperCase().contains("0-1"))
            {
                pageObjectManager.getDeniedBoardingToolPage().getArriveTimeFrame0to1HourRadioButton().click();
                ValidationUtil.validateTestStep("The Arrival Time Frame \"0-1 Hour\" check box was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getArriveTimeFrame0to1HourRadioButton().isSelected());
            }
            else if(arrivalTimeFrame.toUpperCase().contains("1-2"))
            {
                pageObjectManager.getDeniedBoardingToolPage().getArriveTimeFrame1to2HoursRadioButton().click();
                ValidationUtil.validateTestStep("The Arrival Time Frame \"1-2 Hours\" check box was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getArriveTimeFrame1to2HoursRadioButton().isSelected());
            }
            else
            {
                pageObjectManager.getDeniedBoardingToolPage().getArriveTimeFrameMoreThan2HoursRadioButton().click();
                ValidationUtil.validateTestStep("The Arrival Time Frame \"More Than 2 Hours\" check box was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getArriveTimeFrameMoreThan2HoursRadioButton().isSelected());
            }
        }
    }

    /**
     * @param voucherSelect when selecting voucherSelect paramater must follow format "NO" or "YES | ############"
     */
    public void SelectCompensationAndVoucher(String voucherSelect)
    {
        pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersTab().click();
        WaitUtil.untilTimeCompleted(1000);

        if (voucherSelect.toUpperCase().equals("NO"))
        {
            pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersNoRadioButton().click();
            ValidationUtil.validateTestStep("The Compensation & Vouchers \"No\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersNoRadioButton().isSelected());
        }
        else
        {
            pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersYesRadioButton().click();
            ValidationUtil.validateTestStep("The Compensation & Vouchers \"Yes\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersYesRadioButton().isSelected());

            String CheckNumber[] = voucherSelect.replace(" ","").split("\\|"); //separate all OA details by "|"

            //input check amount
            pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersCheckNumberTextbox().sendKeys(CheckNumber[1].trim());
            ValidationUtil.validateTestStep(CheckNumber[1].trim() + " input into the Check Number TextBox", true);

        }
    }

    public void SelectFinalizeVoucher(String voucherSelect, String voucherAmount)
    {
        pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersTab().click();

        if (voucherSelect.toUpperCase().equals("NO"))
        {
            pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersNoRadioButton().click();
            ValidationUtil.validateTestStep("The Compensation & Vouchers \"No\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersNoRadioButton().isSelected());
        }
        else
        {
            pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersYesRadioButton().click();
            ValidationUtil.validateTestStep("The Compensation & Vouchers \"Yes\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getCompensationAndVouchersYesRadioButton().isSelected());

            if (voucherAmount.contains("$")) voucherAmount.replace("$" ,""); // replace any "$" if they are on the voucherAmount String

            pageObjectManager.getDeniedBoardingToolPage().getFinalizeVoucherAmountText().sendKeys();

            pageObjectManager.getDeniedBoardingToolPage().getFinalizeVoucherIssueVoucherButton().click();
            ValidationUtil.validateTestStep("Voucher issued button clicked" , true);
        }
    }


    /**
    * @param food when selecting food parameter must follow format "NO" or "YES | ###.## | DirectBill/ Voucher/ P-card"
    * @param groundTransportation when selecting groundTransportation parameter must follow format "NO" or "YES | ###.## | DirectBill/ Voucher/ P-card"
    * @param hotel when selecting hotel parameter must follow format "NO" or "YES | ###.## | DirectBill/ Voucher/ P-card"
    **/
    public void selectAnemitites(String food, String groundTransportation , String hotel)
    {
        pageObjectManager.getDeniedBoardingToolPage().getAmenitiesTab().click();

        if (food.toUpperCase().equals("NO"))
        {
            pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodNoRadioButton().click();
            ValidationUtil.validateTestStep("The Food Amenities \"No\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodNoRadioButton().isSelected());
        }
        else if(food.toUpperCase().contains("YES"))
        {
            pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodYesRadioButton().click();
            ValidationUtil.validateTestStep("The Food Amenities \"Yes\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodYesRadioButton().isSelected());

            String[] foodDetails = food.replace(" ","").split("\\|");

            pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodAmountTextBox().sendKeys(foodDetails[1].trim());
            ValidationUtil.validateTestStep(foodDetails[1] + " was input into the food amount text box" , true);

            if (foodDetails[2].toLowerCase().trim().equals("directbill"))
            {
                pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodDirectBillRadioButton().click();
                ValidationUtil.validateTestStep("The Food Amenities \"Direct Bill\" radio button was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodDirectBillRadioButton().isSelected());
            }
            else if (foodDetails[2].toLowerCase().trim().equals("voucher"))
            {
                pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodVoucherRadioButton().click();
                ValidationUtil.validateTestStep("The Food Amenities \"Voucher\" radio button was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodVoucherRadioButton().isSelected());
            }
            else
            {
                pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodPCardRadioButton().click();
                ValidationUtil.validateTestStep("The Food Amenities \"P-Card\" radio button was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getAmenitiesFoodPCardRadioButton().isSelected());
            }
        }

        if (groundTransportation.toUpperCase().equals("NO"))
        {
            pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationNoRadioButton().click();
            ValidationUtil.validateTestStep("The Ground Transportation Amenities \"No\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationNoRadioButton().isSelected());
        }
        else if(groundTransportation.toUpperCase().contains("YES"))
        {
            pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationNoRadioButton().click();
            ValidationUtil.validateTestStep("The Ground Transportation Amenities \"Yes\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationYesRadioButton().isSelected());

            String[] groundTransportationDetails = groundTransportation.replace(" ","").split("\\|");

            pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationAmountTextBox().sendKeys(groundTransportationDetails[1].trim());
            ValidationUtil.validateTestStep(groundTransportationDetails[1] + " was input into the ground transportation amount text box" , true);

            if (groundTransportationDetails[2].toLowerCase().trim().equals("directbill"))
            {
                pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationDirectBillRadioButton().click();
                ValidationUtil.validateTestStep("The ground transportation Amenities \"Direct Bill\" radio button was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationDirectBillRadioButton().isSelected());
            }
            else if (groundTransportationDetails[2].toLowerCase().trim().equals("voucher"))
            {
                pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationVoucherRadioButton().click();
                ValidationUtil.validateTestStep("The ground transportation Amenities \"Voucher\" radio button was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationVoucherRadioButton().isSelected());
            }
            else
            {
                pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationPCardRadioButton().click();
                ValidationUtil.validateTestStep("The Ground Transportation Amenities \"P-Card\" radio button was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getAmenitiesGroundTransportationPCardRadioButton().isSelected());
            }
        }

        if (hotel.toUpperCase().equals("NO"))
        {
            pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelNoRadioButton().click();
            ValidationUtil.validateTestStep("The Hotel Amenities \"No\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelNoRadioButton().isSelected());
        }
        else if(hotel.toUpperCase().contains("YES"))
        {
            pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelYesRadioButton().click();
            ValidationUtil.validateTestStep("The Hotel Amenities \"Yes\" check box was selected" ,
                    pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelYesRadioButton().isSelected());

            String[] hotelDetails = hotel.replace(" ","").split("\\|");

            pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelAmountTextBox().sendKeys(hotelDetails[1].trim());
            ValidationUtil.validateTestStep(hotelDetails[1] + " was input into the hotel amount text box" , true);

            if (hotelDetails[2].toLowerCase().trim().equals("directbill"))
            {
                pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelDirectBillRadioButton().click();
                ValidationUtil.validateTestStep("The hotel Amenities \"Direct Bill\" radio button was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelDirectBillRadioButton().isSelected());
            }
            else if (hotelDetails[2].toLowerCase().trim().equals("voucher"))
            {
                pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelVoucherRadioButton().click();
                ValidationUtil.validateTestStep("The hotel Amenities \"Voucher\" radio button was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelVoucherRadioButton().isSelected());
            }
            else
            {
                pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelPCardRadioButton().click();
                ValidationUtil.validateTestStep("The hotel Amenities \"P-Card\" radio button was selected" ,
                        pageObjectManager.getDeniedBoardingToolPage().getAmenitiesHotelPCardRadioButton().isSelected());
            }
        }
    }

    /**
     * @param bidOption "Bid1" or "Bid2" or "Bid3" or "Bid4" or "###"
     */
    public void selectBid(String bidOption)
    {
        pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateTab().click();

        switch (bidOption.toLowerCase())
        {
            case "bid1":
                pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateSelectBid1RadioButton().click();
                ValidationUtil.validateTestStep("Bid option 1 selected",
                        pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateSelectBid1RadioButton().isSelected());
                break;
            case "bid2":
                pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateSelectBid2RadioButton().click();
                ValidationUtil.validateTestStep("Bid option 2 selected",
                        pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateSelectBid2RadioButton().isSelected());
                break;
            case "bid3":
                pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateSelectBid3RadioButton().click();
                ValidationUtil.validateTestStep("Bid option 3 selected",
                        pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateSelectBid3RadioButton().isSelected());
                break;
            case "bid4":
                pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateSelectBid4RadioButton().click();
                ValidationUtil.validateTestStep("Bid option 4 selected",
                        pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateSelectBid4RadioButton().isSelected());

                break;
            default:
                pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateSelectBidTextBox().sendKeys(bidOption);
                ValidationUtil.validateTestStep(bidOption + " input into the bid text box" , true);
        }

        pageObjectManager.getDeniedBoardingToolPage().getBidInputUpdateSelectBidSaveButton().click();
        ValidationUtil.validateTestStep("The Bid option has been saved", true);
    }


}
