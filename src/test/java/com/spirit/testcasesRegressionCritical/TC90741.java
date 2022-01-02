package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.text.*;
import java.util.*;

//**********************************************************************************************
//Test Case ID: TC90741
//Description:  CP_BP_Payment_Page_Hotel Info
//Created By:   Anthony Cardona
//Created On:   01-Aug-2019
//Reviewed By:  Salim Ansari
//Reviewed On:  08-Aug-2019
//**********************************************************************************************
public class TC90741 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "MandatoryFields" , "NoBags" , "NoSeats" , "Hotels" ,"CheckInOptions", "PaymentUI"})
    public void CP_BP_Payment_Page_Hotel_Info(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90741 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "60";
        final String ARR_DATE           = "65";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //open browser
        openBrowser(platform);

//Step 7: Create a domestic booking with hotel and land on the payment page
        //*************************Home Page Methods*********************************
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

        //Store Date information for later use
        String Date = pageObjectManager.getHomePage().getDateBox().getAttribute("value");
        String [] Dates = Date.split("\\-");
        Date DepdateTemp = TestUtil.convertStringToDate(Dates[0],"MM/d/y");
        Date ArrdateTemp = TestUtil.convertStringToDate(Dates[1],"MM/d/y");

        pageMethodManager.getHomePageMethods().clickSearchButton();

        //**************Flight Availability Page Methods*****************************
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //******************Passenger Information Page Methods************************
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0));
        //adult DOB more than 18 years old and less than 21 years old list box
        String dateOfBirth = TestUtil.getStringDateFormat("-9000", "MM/dd/yyyy");
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(dateOfBirth);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //************************Bags Page Methods*********************************
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //*************************Seats Page Methods*********************************
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //***********************Options Page Methods*********************************
        clickOnExpectedHotel();

        //"The Stratosphere Hotel Casino and Tower" or "The Flamingo" or "Luxor" or "The D LAS"  hotel block pops-up. Choose the drop-down located next to "FROM $xxx.xx" Then select the cheapest room to continue
        WaitUtil.untilTimeCompleted(2000);
        WaitUtil.untilPageLoadComplete(getDriver());

        //Store hotel address for later validation
        String hotelAddress         = pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelAddressText().getText();
        System.out.println("hotelAddress: "+hotelAddress );

        pageObjectManager.getHotelPage().getHotelWindowRoomPricesButton().click();
        WaitUtil.untilTimeCompleted(2500);
        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();
        WaitUtil.untilTimeCompleted(2500);

        //Store hotel name for later validation
        String hotelName            = pageObjectManager.getOptionsPage().getSelectedHotelNameText().getText();


        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);

//Step 1: Navigate to the payment page
        //Store Hotel Information
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilTimeCompleted(2000);

//Step 2: Validate the Options section
        ValidationUtil.validateTestStep("The options section is displayed" , pageObjectManager.getPaymentPage().getOptionstHeaderText().getText(), "Options");
        ValidationUtil.validateTestStep("The hotel section is on the options page" , pageObjectManager.getPaymentPage().getYourHotelText().getText() , "Your Hotel");

//Step 3: Validate Hotel information first section
        //Todo: Active bug: Hotel address not displayed on options page
        ValidationUtil.validateTestStep("The hotel name on the payment page is correct",hotelName,
                pageObjectManager.getPaymentPage().getOptionSectionHotelNameText().getText() );
        ValidationUtil.validateTestStep("The hotel address on the payment page is correct", hotelAddress,
                pageObjectManager.getPaymentPage().getOptionSectionHotelAddressText().getText() );

//Step 4: Validate Hotel information second section (room guest)
        //Epic only allows 1 room selection during flight booking flow
        ValidationUtil.validateTestStep("The number of rooms is correct",
                pageObjectManager.getPaymentPage().getOptionSectionHotelRoomsText().getText().replace("Room:", "").trim(),"1");

        String guests  = Integer.toString(Integer.parseInt(ADULT) + Integer.parseInt(CHILD));// get number of children and adults

        ValidationUtil.validateTestStep("The number of Guests is correct" ,
                pageObjectManager.getPaymentPage().getOptionSectionHotelGuestCountText().getText().replace("Guests", "").trim() ,guests);

        String daysStay  = Integer.toString(Integer.parseInt(ARR_DATE) - Integer.parseInt(DEP_DATE));

        ValidationUtil.validateTestStep("The number of Guests is correct" ,
                pageObjectManager.getPaymentPage().getOptionSectionHotelNightsStayText().getText().replace("Nights", "").trim() ,daysStay);


//Step 5: Validate Hotel information third section (checkIn and checkOut date)
        DateFormat dateFormat = new SimpleDateFormat("MMM dd");
        String HotelCheckInDay      = dateFormat.format(DepdateTemp);
        String HotelCheckOutDay     = dateFormat.format(ArrdateTemp);

        ValidationUtil.validateTestStep("The Check in date is correct" ,
                pageObjectManager.getPaymentPage().getOptionSectionHotelCheckInText().getText(),HotelCheckInDay);

        ValidationUtil.validateTestStep("The Check Out date is correct" ,
                pageObjectManager.getPaymentPage().getOptionSectionHotelCheckOutText().getText(),HotelCheckOutDay);

//Step 6: N/A
    }

    public void clickOnExpectedHotel() {
        WaitUtil.untilTimeCompleted(1200);

        final String STRATOSPHERE_HOTEL_NAME       = "STRATOSPHERE CASINO, HOTEL AND TOWER";
        final String THE_D_HOTEL_NAME              = "THE D LAS VEGAS";
        final String LUXOR_HOTEL_NAME              = "LUXOR HOTEL AND CASINO";

        int count = 0;

        List<WebElement> displayedHotelNamesList = getDisplayedWebElements(pageObjectManager.getHotelPage().getHotelCardNameText()); //list of displayed Hotel Names
        List<WebElement> displayedHotelViewButtons = getDisplayedWebElements(pageObjectManager.getHotelPage().getHotelCardViewLink()); //list of displayed Hotel View Buttons

        for (WebElement hotelNameOnCard : displayedHotelNamesList) //loop through displayed hotel card names
        {

            //if the hotel names matches expected name
            if (hotelNameOnCard.getText().toUpperCase().contains(LUXOR_HOTEL_NAME) ||
                    hotelNameOnCard.getText().toUpperCase().contains(THE_D_HOTEL_NAME) ||
                    hotelNameOnCard.getText().toUpperCase().contains(STRATOSPHERE_HOTEL_NAME))
            {

                if (displayedHotelViewButtons.get(count).isDisplayed()) //if the view link is displayed for that hotel, click it and break loop
                {
                    try //script giving issues here clicking on element (TimeoutException), have to surround with try catch
                    {
                        displayedHotelViewButtons.get(count).click();
                    }
                    catch (Exception e){}
                    break;
                }

            }
            else if (count == getDisplayedWebElements(pageObjectManager.getHotelPage().getHotelCardNameText()).size()  - 1)//if we are on the 4th hotel card, click on the right carousel button and begin loop again
            {
                try //script giving issues here clicking on element (TimeoutException), have to surround with try catch
                {
                    pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
                }
                catch (Exception e){}
                clickOnExpectedHotel();
            }
            count++;
        }
    }

    private List<WebElement> getDisplayedWebElements(List<WebElement> elementList){
        ArrayList<WebElement> arrayList = new ArrayList<>();
        for (WebElement element:elementList) {
            if(element.isDisplayed()){
                arrayList.add(element);
            }
        }
        return arrayList;
    }
}
