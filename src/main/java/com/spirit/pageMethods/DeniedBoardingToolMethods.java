package com.spirit.pageMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.DeniedBoardingToolPage;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DeniedBoardingToolMethods {

    WebDriver driver;
    PageObjectManager pageObjectManager;
    ScenarioContext scenarioContext;
    DeniedBoardingToolPage deniedBoardingToolPage;

    public DeniedBoardingToolMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
        this.driver             = driver;
        this.pageObjectManager  = pageObjectManager;
        this.scenarioContext    = scenarioContext;
        deniedBoardingToolPage  = pageObjectManager.getDeniedBoardingToolPage();
    }

    //**********************************************************************************************
    //Method Name: selectCity
    //Description: Method is used to select departing city on Denied Boarding Tool
    //Input Arguments: String->DEP_CityCode
    //Return: NA
    //Created By : Anthony C
    //Created On : 22-Jan-2020
    //Reviewed By: Salim Ansari
    //Reviewed On: 02-Feb-2020
    //**********************************************************************************************
    public void selectCity(String DEP_CityCode) {
        //convert to upper case
        DEP_CityCode = DEP_CityCode.toUpperCase();

        //select dep airport from drop down
        TestUtil.selectDropDownUsingVisibleText(deniedBoardingToolPage.getSelectStationDropDown(),DEP_CityCode);

        //validate city is selected
        ValidationUtil.validateTestStep(DEP_CityCode + " was selected from the station Drop Down on Denied Boarding Tool" ,
                deniedBoardingToolPage.getSelectStationDropDown().getText() , DEP_CityCode);
    }

    //**********************************************************************************************
    //Method Name: selectDate
    //Description: Method is used to select departing date on Denied Boarding Tool
    //Input Arguments: String->DEP_Date
    //Return: NA
    //Created By : Anthony C
    //Created On : 22-Jan-2020
    //Reviewed By: Salim Ansari
    //Reviewed On: 02-Feb-2020
    //**********************************************************************************************
    public void selectDate(String DEP_Date) {
        // if date equals 0,
        if(DEP_Date.equals("0")) {
            //click on todays readio button
            deniedBoardingToolPage.getTodayRadioButton().click();

            //validate today's radio button is selected
            ValidationUtil.validateTestStep("The user selects to use Today radio Button on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getTodayRadioButton().isSelected());
        } else {
            //get start and end dates of range
            String dep_DateFormat = TestUtil.getStringDateFormat(DEP_Date,"MM/DD/YYYY"); //get dep date
            String depEnd_DateFormat = TestUtil.getStringDateFormat(Integer.parseInt(DEP_Date) + 1 ,"MM/DD/YYYY"); //get one day added to dep

            //click on date range radio button
            deniedBoardingToolPage.getDateRangeRadioButton().click();

            //validate date range radio button is selected

            ValidationUtil.validateTestStep("The user selects to use date range option on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getDateRangeRadioButton().isSelected());

            //wait for 1 sec
            WaitUtil.untilTimeCompleted(1000);

            //clear and enter start date
            deniedBoardingToolPage.getDateRangeStartTextBox().clear();
            deniedBoardingToolPage.getDateRangeStartTextBox().sendKeys(dep_DateFormat);

            //clear and enter end date
            deniedBoardingToolPage.getDateRangeEndTextBox().clear();
            deniedBoardingToolPage.getDateRangeEndTextBox().sendKeys(depEnd_DateFormat);

            //validate start and end dates are entered
            ValidationUtil.validateTestStep("The user selects to use date " + dep_DateFormat + " through " + depEnd_DateFormat + " on Denied Boarding Tool",
                    deniedBoardingToolPage.getDateRangeRadioButton().isSelected());
        }
    }

    //**********************************************************************************************
    //Method Name: clickSearch
    //Description: Method is used to select search button on Denied Boarding Tool
    //Input Arguments: NA
    //Return: NA
    //Created By : Anthony C
    //Created On : 22-Jan-2020
    //Reviewed By: Salim Ansari
    //Reviewed On: 02-Feb-2020
    //**********************************************************************************************
    public void clickSearch() {
        //click on BDT button
        deniedBoardingToolPage.getSearchButton().click();

        //validate BDT buytton is cliked on denied boarding toll
        ValidationUtil.validateTestStep("The user clicked on search button on Denied Boarding Tool" , true);
    }

    //**********************************************************************************************
    //Method Name: selectFlightByNumber
    //Description: Method is used to select overbooked flight button on Denied Boarding Tool
    //Input Arguments: String->overBookedFlight
    //Return: NA
    //Created By : Anthony C
    //Created On : 22-Jan-2020
    //Reviewed By: Salim Ansari
    //Reviewed On: 02-Feb-2020
    //**********************************************************************************************
    public void selectFlightByNumber(String overBookedFlight) {
        //loop through all avaliable flight
        for(WebElement flightNum : deniedBoardingToolPage.getTableFlightNumberButton()) {
            //check the flight number
            if (flightNum.getText().contains(overBookedFlight)) {
                //select flight when flight number is found
                flightNum.click();

                //validate flight number is selected
                ValidationUtil.validateTestStep("Flight Number:" + overBookedFlight + " was selected on Denied Boarding Tool" , true);

                //break loop
                break;
            }
        }
    }



    public boolean waitUntilDBTisLoaded() {
        WaitUtil.untilJavaScriptPageLoadComplete(driver);

        System.out.println(TestUtil.verifyElementDisplayed(deniedBoardingToolPage.getPageLoadToasterPanel()));

        return !(TestUtil.verifyElementDisplayed(deniedBoardingToolPage.getPageLoadToasterPanel()));
    }

    public boolean waitUntilDBTFlightTableLoaded() {
        WaitUtil.untilTimeCompleted(500);

        return !(TestUtil.verifyElementDisplayed(deniedBoardingToolPage.getFlightTableLoadSpinner()));
    }

    //**********************************************************************************************
    //Method Name: selectMemberFromFlightManifest
    //Description: Method is used to select Specific Passenger booked with PNR value on Denied Boarding Tool
    //Input Arguments: NA
    //Return: NA
    //Created By : Anthony C
    //Created On : 22-Jan-2020
    //Reviewed By: Salim Ansari
    //Reviewed On: 04-Feb-2020
    //**********************************************************************************************
    public void selectMemberFromFlightManifest(String paxName , String PNR) {
        //SPIRITAUTOMATION TEST
        //TEST/SPIRITAUTOMA
        //pax name is a max length of 17 chars
        paxName = paxName.toUpperCase().substring(0 , 17);

        System.out.println(paxName);
        int manifestCounter = 0;

        //Store the index of the reservations that match PNR (names can repeat on a flight with different PNR)
        List<Integer> reservationIndexOnManifestList = new ArrayList<Integer>();

        //loop through complete Passenger list and find the location of PNR and store into list
        for (WebElement reservation :  deniedBoardingToolPage.getManifestTablePNRText()) {
            //check the PNR value
            if( reservation.getText().contains(PNR)) {
                //store index value into array list
                reservationIndexOnManifestList.add(manifestCounter);
            }
            //increment counter value
            manifestCounter++;
        }

        //check the number of passenger assigned to PNR
        ValidationUtil.validateTestStep("There are " + reservationIndexOnManifestList.size() + " passengers found with PNR: " + PNR + " on Denied Boarding Tool", true);

        //Get the names for that PNR
        for(int indexOfPNR : reservationIndexOnManifestList) {
            //if this passenger name contains the name passed as a parameter, click on that name
            if(deniedBoardingToolPage.getManifestTablePaxNameText().get(indexOfPNR).getText().contains(paxName)) {
                //click on passenger
                deniedBoardingToolPage.getManifestTablePaxNameText().get(indexOfPNR).click();

                //check passenger and PNR value is found on DBT
                ValidationUtil.validateTestStep("The Passenger:"+ paxName +" and PNR:" + PNR + " was found in this flight on Denied Boarding Tool" , true);

                //return from method
                return;
            }
        }

        //check passenger and PNR value is NOT found on DBT
        ValidationUtil.validateTestStep("The Passenger:"+ paxName +" and PNR:" + PNR + " was NOT found in this flight on Denied Boarding Tool" , false);
    }

    //**********************************************************************************************
    //Method Name: selectPassengerOptions
    //Description: Method is used to select passenger options on Denied Boarding Tool
    //Input Arguments: NA
    //Return: NA
    //Created By : Anthony C
    //Created On : 22-Jan-2020
    //Reviewed By: Salim Ansari
    //Reviewed On: 04-Feb-2020
    //**********************************************************************************************
    public void selectPassengerOptions(String deniedBoardingStatus , String reason) {
        //click on boarding tab
        deniedBoardingToolPage.getDeniedBoardingTab().click();

        //check status
        switch (deniedBoardingStatus.toUpperCase()) {
            case "INVOL":
                //click on INVOL radio buton
                deniedBoardingToolPage.getDeniedBoardingStatusINVOLRadioButton().click();

                //validate INVOL radio button is clicked
                ValidationUtil.validateTestStep("The INVOL check box was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getDeniedBoardingStatusINVOLRadioButton().isSelected());

                //break switch statement
                break;
            case "VOL":
                //click on VOL radio button
                deniedBoardingToolPage.getDeniedBoardingStatusVOLRadioButton().click();

                //validate VOL radio button is clicked
                ValidationUtil.validateTestStep("The VOL check box was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getDeniedBoardingStatusVOLRadioButton().isSelected());

                //break switch statement
                break;
        }

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //select reason on boarding option
        switch (reason.toLowerCase().replace(" ", "")) {
            case "equipmentdowngrade":
                //click on Equipment Downgrade Button
                deniedBoardingToolPage.getDeniedBoardingReasonEquipmentDowngradeRadioButton().click();

                //validate equipment downgrade button is clicked
                ValidationUtil.validateTestStep("The Equipment Downgrade Reason check box was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getDeniedBoardingReasonEquipmentDowngradeRadioButton().isSelected());

                //break switch statement
                break;
            case "overbooked":
                //click on OverBook button
                deniedBoardingToolPage.getDeniedBoardingReasonOverbookedRadioButton().click();

                //validate overbook button is clicked
                ValidationUtil.validateTestStep("The Overbooked Reason check box was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getDeniedBoardingReasonOverbookedRadioButton().isSelected());

                //break switch statement
                break;
            case "weightrestriction":
                //click on Weight Restriction Button
                deniedBoardingToolPage.getDeniedBoardingReasonWeightRestrictionRadioButton().click();

                //validate weight restriction button is cliked
                ValidationUtil.validateTestStep("The Weight Restriction Reason check box was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getDeniedBoardingReasonWeightRestrictionRadioButton().isSelected());

                //break switch statement
                break;
        }
    }

    //**********************************************************************************************
    //Method Name: selectRebookOptions
    //Description: Method is used to select Rebook options on Denied Boarding Tool
    //Input Arguments:  String->rebook
    //                  String->rebookWith("NK" or "OA | XX | ###.## | ITS /P)
    //                  String->arrivalTimeFrame
    //Return: NA
    //Created By : Anthony C
    //Created On : 22-Jan-2020
    //Reviewed By: Salim Ansari
    //Reviewed On: 04-Feb-2020
    //**********************************************************************************************
    public void selectRebookOptions(String rebook , String rebookWith , String arrivalTimeFrame) {
        //click on rebook tab
        deniedBoardingToolPage.getRebookTab().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //check rebook value
        if(rebook.toUpperCase().equals("NO")) {
            //click on No radio button
            deniedBoardingToolPage.getRebookNoRadioButton().click();

            //validate no button is selected
            ValidationUtil.validateTestStep("The Rebook \"No\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getRebookNoRadioButton().isSelected());
        } else {
            //click on yes radio button
            deniedBoardingToolPage.getRebookYesRadioButton().click();

            //validate yes radio button is selected
            ValidationUtil.validateTestStep("The Rebook \"Yes\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getRebookYesRadioButton().isSelected());

            //select the Rebook With Option
            if (rebookWith.toUpperCase().equals("NK")) {
                //click on NK
                deniedBoardingToolPage.getRebookWithNKRadioButton().click();

                //validate NK radio button is selected
                ValidationUtil.validateTestStep("The Rebook With \"NK\" check box was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getRebookWithNKRadioButton().isSelected());
            } else if(rebookWith.toUpperCase().contains("OA")) {
                //get rebook details
                String rebookWithDetail[] = rebookWith.replace(" ","").split("\\|"); //separate all OA details by "|"

                //click on OA radio button
                deniedBoardingToolPage.getRebookWithOARadioButton().click();

                //validate OA radio button is selected
                ValidationUtil.validateTestStep("The Rebook With \"OA\" check box was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getRebookWithOARadioButton().isSelected());

                //Airline Code textBox
                deniedBoardingToolPage.getRebookTwoLetterAirlineCodeTextBox().sendKeys(rebookWithDetail[1].trim());
                ValidationUtil.validateTestStep(rebookWithDetail[1].trim() + " input as the 2 letter Airline Code on Denied Boarding Tool", true);

                //ticket amount textBox
                deniedBoardingToolPage.getRebookTicketAmountTextBox().sendKeys(rebookWithDetail[2].trim());
                ValidationUtil.validateTestStep(rebookWithDetail[2].trim() + " input into the Ticket Amount TextBox on Denied Boarding Tool", true);

                //select form of payment checkbox
                if(rebookWithDetail[3].contains("ITS") || rebookWithDetail[3].contains("MAX")) {
                    //click on ITS/MAX radio button
                    deniedBoardingToolPage.getRebookFormOfPaymentITSMAXRadioButton().click();

                    //validate ITS/MAX radio button is selected
                    ValidationUtil.validateTestStep("The Form of Payment \"ITS\\MAX\" check box was selected on Denied Boarding Tool" ,
                            deniedBoardingToolPage.getRebookFormOfPaymentITSMAXRadioButton().isSelected());
                } else {
                    //click on pCard radio button
                    deniedBoardingToolPage.getRebookFormOfPaymentPCardRadioButton().click();

                    //validate P Card radio button is selected
                    ValidationUtil.validateTestStep("The Form of Payment \"P-card\" check box was selected on Denied Boarding Tool" ,
                            deniedBoardingToolPage.getRebookFormOfPaymentPCardRadioButton().isSelected());
                }
            }

            //select the Arrival Time Frame
            if (arrivalTimeFrame.toUpperCase().contains("0-1")) {
                //click on 0-1 time frane
                deniedBoardingToolPage.getArriveTimeFrame0to1HourRadioButton().click();

                //validate 0-1 time frame is selected
                ValidationUtil.validateTestStep("The Arrival Time Frame \"0-1 Hour\" check box was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getArriveTimeFrame0to1HourRadioButton().isSelected());
            } else if(arrivalTimeFrame.toUpperCase().contains("1-2")) {
                //click on 1-2 time frame
                deniedBoardingToolPage.getArriveTimeFrame1to2HoursRadioButton().click();

                //validate 1-2 time frame is selected
                ValidationUtil.validateTestStep("The Arrival Time Frame \"1-2 Hours\" check box was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getArriveTimeFrame1to2HoursRadioButton().isSelected());
            } else {
                //click on more than 2 time frame
                deniedBoardingToolPage.getArriveTimeFrameMoreThan2HoursRadioButton().click();

                //validate more than 2 time frame is selected
                ValidationUtil.validateTestStep("The Arrival Time Frame \"More Than 2 Hours\" check box was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getArriveTimeFrameMoreThan2HoursRadioButton().isSelected());
            }
        }
    }

    //**********************************************************************************************
    //Method Name: selectCompensationAndVoucher
    //Description: Method is used to select Rebook options on Denied Boarding Tool
    //Input Arguments:  String->voucherSelect("NO" or "YES | ############)
    //Return: NA
    //Created By : Anthony C
    //Created On : 22-Jan-2020
    //Reviewed By: Salim Ansari
    //Reviewed On: 04-Feb-2020
    //**********************************************************************************************
    public void selectCompensationAndVoucher(String voucherSelect) {
        //click on voucher tab
        deniedBoardingToolPage.getCompensationAndVouchersTab().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //check for voucher option
        if (voucherSelect.toUpperCase().equals("NO")) {
            //click on voucher No button
            deniedBoardingToolPage.getCompensationAndVouchersNoRadioButton().click();

            //validate voucher No button is selected
            ValidationUtil.validateTestStep("The Compensation & Vouchers \"No\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getCompensationAndVouchersNoRadioButton().isSelected());
        } else {
            //click on voucher Yes button
            deniedBoardingToolPage.getCompensationAndVouchersYesRadioButton().click();

            //validate voucher Yes button is selected
            ValidationUtil.validateTestStep("The Compensation & Vouchers \"Yes\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getCompensationAndVouchersYesRadioButton().isSelected());

            //get check number of vourcher
            String CheckNumber[] = voucherSelect.replace(" ","").split("\\|"); //separate all OA details by "|"

            //input check amount
            deniedBoardingToolPage.getCompensationAndVouchersCheckNumberTextbox().sendKeys(CheckNumber[1].trim());
            ValidationUtil.validateTestStep(CheckNumber[1].trim() + " input into the Check Number TextBox on Denied Boarding Tool", true);
        }
    }

    //**********************************************************************************************
    //Method Name: selectFinalizeVoucher
    //Description: Method is used to select Voucher on Denied Boarding Tool
    //Input Arguments:  String->voucherSelect("NO" or "YES | ############)
    //Return: NA
    //Created By : Anthony C
    //Created On : 22-Jan-2020
    //Reviewed By: Salim Ansari
    //Reviewed On: 05-Feb-2020
    //**********************************************************************************************
    public void selectFinalizeVoucher(String voucherSelect, String voucherAmount) {
        //click on voucher tab
        deniedBoardingToolPage.getCompensationAndVouchersTab().click();

        //check the voucher value to select
        if (voucherSelect.toUpperCase().equals("NO")) {
            //click on No radio button
            deniedBoardingToolPage.getCompensationAndVouchersNoRadioButton().click();

            //verify No radio buttion is selected
            ValidationUtil.validateTestStep("The Compensation & Vouchers \"No\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getCompensationAndVouchersNoRadioButton().isSelected());
        } else {
            //click on yes radio button
            deniedBoardingToolPage.getCompensationAndVouchersYesRadioButton().click();

            //vbalidate yes radio button is selected
            ValidationUtil.validateTestStep("The Compensation & Vouchers \"Yes\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getCompensationAndVouchersYesRadioButton().isSelected());

            //remove dollor if appear ion amount
            if (voucherAmount.contains("$")) voucherAmount.replace("$" ,""); // replace any "$" if they are on the voucherAmount String

            //enter amount
            deniedBoardingToolPage.getFinalizeVoucherAmountText().sendKeys(voucherAmount);

            //get final voucher value of added amount
            deniedBoardingToolPage.getFinalizeVoucherIssueVoucherButton().click();

            //validate voucher amount is created
            ValidationUtil.validateTestStep("Voucher issued button clicked on Denied Boarding Tool" , true);
        }
    }


    /**
     * @param food when selecting food parameter must follow format "NO" or "YES | ###.## | DirectBill/ Voucher/ P-card"
     * @param groundTransportation when selecting groundTransportation parameter must follow format "NO" or "YES | ###.## | DirectBill/ Voucher/ P-card"
     * @param hotel when selecting hotel parameter must follow format "NO" or "YES | ###.## | DirectBill/ Voucher/ P-card"
     **/
    public void selectAnemitites(String food, String groundTransportation , String hotel) {
        deniedBoardingToolPage.getAmenitiesTab().click();

        if (food.toUpperCase().equals("NO")) {
            deniedBoardingToolPage.getAmenitiesFoodNoRadioButton().click();
            ValidationUtil.validateTestStep("The Food Amenities \"No\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getAmenitiesFoodNoRadioButton().isSelected());
        } else if(food.toUpperCase().contains("YES")) {
            deniedBoardingToolPage.getAmenitiesFoodYesRadioButton().click();
            ValidationUtil.validateTestStep("The Food Amenities \"Yes\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getAmenitiesFoodYesRadioButton().isSelected());

            String[] foodDetails = food.replace(" ","").split("\\|");

            deniedBoardingToolPage.getAmenitiesFoodAmountTextBox().sendKeys(foodDetails[1].trim());
            ValidationUtil.validateTestStep(foodDetails[1] + " was input into the food amount text box on Denied Boarding Tool" , true);

            if (foodDetails[2].toLowerCase().trim().equals("directbill")) {
                deniedBoardingToolPage.getAmenitiesFoodDirectBillRadioButton().click();
                ValidationUtil.validateTestStep("The Food Amenities \"Direct Bill\" radio button was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getAmenitiesFoodDirectBillRadioButton().isSelected());
            } else if (foodDetails[2].toLowerCase().trim().equals("voucher")) {
                deniedBoardingToolPage.getAmenitiesFoodVoucherRadioButton().click();
                ValidationUtil.validateTestStep("The Food Amenities \"Voucher\" radio button was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getAmenitiesFoodVoucherRadioButton().isSelected());
            } else {
                deniedBoardingToolPage.getAmenitiesFoodPCardRadioButton().click();
                ValidationUtil.validateTestStep("The Food Amenities \"P-Card\" radio button was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getAmenitiesFoodPCardRadioButton().isSelected());
            }
        }


        if (groundTransportation.toUpperCase().equals("NO")) {
            deniedBoardingToolPage.getAmenitiesGroundTransportationNoRadioButton().click();
            ValidationUtil.validateTestStep("The Ground Transportation Amenities \"No\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getAmenitiesGroundTransportationNoRadioButton().isSelected());
        } else if(groundTransportation.toUpperCase().contains("YES")) {
            deniedBoardingToolPage.getAmenitiesGroundTransportationNoRadioButton().click();
            ValidationUtil.validateTestStep("The Ground Transportation Amenities \"Yes\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getAmenitiesGroundTransportationYesRadioButton().isSelected());

            String[] groundTransportationDetails = groundTransportation.replace(" ","").split("\\|");

            deniedBoardingToolPage.getAmenitiesGroundTransportationAmountTextBox().sendKeys(groundTransportationDetails[1].trim());
            ValidationUtil.validateTestStep(groundTransportationDetails[1] + " was input into the ground transportation amount text box on Denied Boarding Tool" , true);

            if (groundTransportationDetails[2].toLowerCase().trim().equals("directbill")) {
                deniedBoardingToolPage.getAmenitiesGroundTransportationDirectBillRadioButton().click();
                ValidationUtil.validateTestStep("The ground transportation Amenities \"Direct Bill\" radio button was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getAmenitiesGroundTransportationDirectBillRadioButton().isSelected());
            } else if (groundTransportationDetails[2].toLowerCase().trim().equals("voucher")) {
                deniedBoardingToolPage.getAmenitiesGroundTransportationVoucherRadioButton().click();
                ValidationUtil.validateTestStep("The ground transportation Amenities \"Voucher\" radio button was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getAmenitiesGroundTransportationVoucherRadioButton().isSelected());
            } else {
                deniedBoardingToolPage.getAmenitiesGroundTransportationPCardRadioButton().click();
                ValidationUtil.validateTestStep("The Ground Transportation Amenities \"P-Card\" radio button was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getAmenitiesGroundTransportationPCardRadioButton().isSelected());
            }
        }

        if (hotel.toUpperCase().equals("NO")) {
            deniedBoardingToolPage.getAmenitiesHotelNoRadioButton().click();
            ValidationUtil.validateTestStep("The Hotel Amenities \"No\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getAmenitiesHotelNoRadioButton().isSelected());
        } else if(hotel.toUpperCase().contains("YES")) {
            deniedBoardingToolPage.getAmenitiesHotelYesRadioButton().click();
            ValidationUtil.validateTestStep("The Hotel Amenities \"Yes\" check box was selected on Denied Boarding Tool" ,
                    deniedBoardingToolPage.getAmenitiesHotelYesRadioButton().isSelected());

            String[] hotelDetails = hotel.replace(" ","").split("\\|");

            deniedBoardingToolPage.getAmenitiesHotelAmountTextBox().sendKeys(hotelDetails[1].trim());
            ValidationUtil.validateTestStep(hotelDetails[1] + " was input into the hotel amount text box on Denied Boarding Tool" , true);

            if (hotelDetails[2].toLowerCase().trim().equals("directbill")) {
                deniedBoardingToolPage.getAmenitiesHotelDirectBillRadioButton().click();
                ValidationUtil.validateTestStep("The hotel Amenities \"Direct Bill\" radio button was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getAmenitiesHotelDirectBillRadioButton().isSelected());
            } else if (hotelDetails[2].toLowerCase().trim().equals("voucher")) {
                deniedBoardingToolPage.getAmenitiesHotelVoucherRadioButton().click();
                ValidationUtil.validateTestStep("The hotel Amenities \"Voucher\" radio button was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getAmenitiesHotelVoucherRadioButton().isSelected());
            } else {
                deniedBoardingToolPage.getAmenitiesHotelPCardRadioButton().click();
                ValidationUtil.validateTestStep("The hotel Amenities \"P-Card\" radio button was selected on Denied Boarding Tool" ,
                        deniedBoardingToolPage.getAmenitiesHotelPCardRadioButton().isSelected());
            }
        }
    }

    /**
     * @param bidOption "Bid1" or "Bid2" or "Bid3" or "Bid4" or "###"
     */
    public void selectBid(String bidOption) {
        deniedBoardingToolPage.getBidInputUpdateTab().click();

        switch (bidOption.toLowerCase()) {
            case "bid1":
                deniedBoardingToolPage.getBidInputUpdateSelectBid1RadioButton().click();

                ValidationUtil.validateTestStep("Bid option 1 selected on Denied Boarding Tool",
                        deniedBoardingToolPage.getBidInputUpdateSelectBid1RadioButton().isSelected());

                break;
            case "bid2":
                deniedBoardingToolPage.getBidInputUpdateSelectBid2RadioButton().click();

                ValidationUtil.validateTestStep("Bid option 2 selected on Denied Boarding Tool",
                        deniedBoardingToolPage.getBidInputUpdateSelectBid2RadioButton().isSelected());

                break;
            case "bid3":
                deniedBoardingToolPage.getBidInputUpdateSelectBid3RadioButton().click();

                ValidationUtil.validateTestStep("Bid option 3 selected on Denied Boarding Tool",
                        deniedBoardingToolPage.getBidInputUpdateSelectBid3RadioButton().isSelected());

                break;
            case "bid4":
                deniedBoardingToolPage.getBidInputUpdateSelectBid4RadioButton().click();

                ValidationUtil.validateTestStep("Bid option 4 selected on Denied Boarding Tool",
                        deniedBoardingToolPage.getBidInputUpdateSelectBid4RadioButton().isSelected());


                break;
            default:
                deniedBoardingToolPage.getBidInputUpdateSelectBidTextBox().sendKeys(bidOption);

                ValidationUtil.validateTestStep(bidOption + " input into the bid text box on Denied Boarding Tool" , true);
        }

        deniedBoardingToolPage.getBidInputUpdateSelectBidSaveButton().click();

        ValidationUtil.validateTestStep("The Bid option has been saved on Denied Boarding Tool", true);
    }
}
