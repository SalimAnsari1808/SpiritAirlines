package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90802
//Description: Task 24870: 35297 Seats Page_CP_BP-CI-MT_Generic Wireframe Validation
//Created By : Gabriela
//Created On : 06-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Aug-2019
//**********************************************************************************************

public class TC90802 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "MyTrips" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "NonStop" , "BookIt" , "SeatsUI" , "NoBags" ,"NoSeats","CheckInOptions", "MasterCard"})
    public void Seats_Page_CP_BP_CI_MT_Generic_Wireframe_Validation(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90802 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        /***************************************************************************************
         * ************************* BP and MT Validation **************************************
         ***************************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "ATL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "6";
        final String DEP_DATE_1         = "0";
        final String ARR_DATE_1         = "2";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Seats Page constant Values
        final String SEAT_URL           = "book/seats";
        final String SEAT_MY_TRIP_URL   = "/my-trips/seats";
        final String SEAT_CI_TRIP_URL   = "/check-in/seats";
        final String HEADER_TEXT        = "Choose Your Seat";
        final String SEAT_TOTAL         = "SEATS TOTAL";
        final String SEAT               = "Standard";
        final String BLACK_RGB          = "0, 0, 0";
        final String WHITE_RGB          = "255, 255, 255";
        final String BLUE_RGB           = "0, 115, 230";

        //Options Page Constant Values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE         = "MasterCard";
        final String TRAVEL_GUARD      = "NotRequired";

        //Reservation Page common methods
        final String MYTRIP_SEAT        = "Seats";

        //open browser
        openBrowser(platform);

//-- Step 1: Continue to Seats Page
        /******************************************************************************
         *************************** Navigate to Seats Page (BP) **********************
         ******************************************************************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /******************************Flight Availability Page Method ***************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("RET", DEP_FLIGHT);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        String DEP_CITY = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText();
        String RET_CITY = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText();

        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /******************************Passenger Information Page Method***************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /******************************Bags Page Method***********************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /******************************************************************************
         ***************************Seats Page Validations*****************************
         ******************************************************************************/
        ValidationUtil.validateTestStep("Validating Bags Page  is on the right URL",
                getDriver().getCurrentUrl(),SEAT_URL);

//-- Step 2: Navigate to Page Title
        ValidationUtil.validateTestStep("Validating the right title",
                pageObjectManager.getSeatsPage().getSeatPageHeaderText().getText(),HEADER_TEXT);

//-- Step 3: Navigate to Flight Content Block
        ValidationUtil.validateTestStep("Validating Departing city info",
                pageObjectManager.getSeatsPage().getFlightLegsText().get(0).getText(),DEP_CITY);

        ValidationUtil.validateTestStep("Validating first leg is active by default",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getPassengerNameText().get(0)));

        ValidationUtil.validateTestStep("Validating Returning city info",
                pageObjectManager.getSeatsPage().getFlightLegsText().get(1).getText(),RET_CITY);

        for (WebElement element : pageObjectManager.getSeatsPage().getPassengerSeatText()) {
            if (element.isDisplayed()) {
                String tempText = element.getText() ;
                ValidationUtil.validateTestStep("The Seat number field is empty" ,
                        "" , tempText);
            }
        }

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);

        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getSeatsPage().getPassengerDetailListLink().get(0).click();

        for (WebElement element : pageObjectManager.getSeatsPage().getPassengerSeatText()) {
            if (element.isDisplayed()) {
                String tempText = element.getText() ;
                ValidationUtil.validateTestStep("The Seat number field is empty" ,
                        tempText.length() > 0);
            }
        }

//-- Step 4: Navigate to Seat Total Content Block
        ValidationUtil.validateTestStep("Validating Total Content Block is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatsTotalText()));
        ValidationUtil.validateTestStep("Validating right content block text",
                pageObjectManager.getSeatsPage().getSeatsTotalText().getText(),SEAT_TOTAL);

        ValidationUtil.validateTestStep("Validating price is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatsTotalPriceText()));

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getSeatsPage().getSeatsTotalContainerLink().click();

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("Validating seats prices are displayed on the total breakdown for departure leg",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText()));

        ValidationUtil.validateTestStep("Validating seats prices are displayed on the total breakdown for returning leg",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText()));

//-- Step 5: Navigate to ‘Continue’ and ‘Continue Without Selecting Seats’ Action Button
        ValidationUtil.validateTestStep("Validating Continue With Seat button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithSeatButton().get(0)));


        ValidationUtil.validateTestStep("Validating Continue without seat button is  displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton().get(0)));

        String seatTotalPanelRGBValue = pageObjectManager.getSeatsPage().getSeatTotalPanel().getCssValue("color");
        String seatTotalTextRGBValue = pageObjectManager.getSeatsPage().getSeatsTotalText().getCssValue("color");

        ValidationUtil.validateTestStep("Validating Seat Total Panel Color is Black",seatTotalPanelRGBValue,BLACK_RGB);
        ValidationUtil.validateTestStep("Validating Seat Total Text Color is White",seatTotalTextRGBValue,WHITE_RGB);

        String continueButtonTextRGBValue = pageObjectManager.getSeatsPage().getContinueWithSeatButton().get(0).getCssValue("color");
        String continueButtonBackgroundRGBValue = pageObjectManager.getSeatsPage().getContinueWithSeatButton().get(0).getCssValue("background-color");

        ValidationUtil.validateTestStep("Validating continue button Text Color is Black",continueButtonTextRGBValue,WHITE_RGB);
        ValidationUtil.validateTestStep("Validating Seat Total Text Color is White",continueButtonBackgroundRGBValue,BLUE_RGB);

//-- Step 6: Navigate to Seat Map
        //Invalid Step
        /******************************************************************************
         *************************** Navigate to Seats Page (MT) **********************
         ******************************************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /********************** Options Page Methods **********************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /********************** Payment Page Methods **********************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /********************** Confirmation Page Methods **********************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        /********************** Login to My Trips Page Methods **********************/
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Selecting Add Seats link
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_SEAT);

        /********************** Reservation Summary Page Methods **********************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(SEAT_MY_TRIP_URL);

//-- Step 2: Navigate to Page Title
        ValidationUtil.validateTestStep("Validating the right title",
                pageObjectManager.getSeatsPage().getSeatPageHeaderText().getText(),HEADER_TEXT);

//-- Step 3: Navigate to Flight Content Block
        ValidationUtil.validateTestStep("Validating Departing city info",
                pageObjectManager.getSeatsPage().getFlightLegsText().get(0).getText(),DEP_CITY);

        ValidationUtil.validateTestStep("Validating first leg is active by default",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getPassengerNameText().get(0)));

        ValidationUtil.validateTestStep("Validating Returning city info",
                pageObjectManager.getSeatsPage().getFlightLegsText().get(1).getText(),RET_CITY);

        for (WebElement element : pageObjectManager.getSeatsPage().getPassengerSeatText()) {
            if (element.isDisplayed()) {
                String tempText = element.getText() ;
                ValidationUtil.validateTestStep("The Seat number field is empty" ,
                        "" , tempText);
            }
        }

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);

        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getSeatsPage().getPassengerDetailListLink().get(0).click();

        for (WebElement element : pageObjectManager.getSeatsPage().getPassengerSeatText()) {
            if (element.isDisplayed()) {
                String tempText = element.getText() ;
                ValidationUtil.validateTestStep("The Seat number field is empty" ,
                        tempText.length() > 0);
            }
        }

//-- Step 4: Navigate to Seat Total Content Block
        ValidationUtil.validateTestStep("Validating Total Content Block is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatsTotalText()));
        ValidationUtil.validateTestStep("Validating right content block text",
                pageObjectManager.getSeatsPage().getSeatsTotalText().getText(),SEAT_TOTAL);

        ValidationUtil.validateTestStep("Validating price is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatsTotalPriceText()));

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getSeatsPage().getSeatsTotalContainerLink().click();

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("Validating seats prices are displayed on the total breakdown for departure leg",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText()));

        ValidationUtil.validateTestStep("Validating seats prices are displayed on the total breakdown for returning leg",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText()));

//-- Step 5: Navigate to ‘Continue’ and ‘Continue Without Selecting Seats’ Action Button
        ValidationUtil.validateTestStep("Validating Continue With Seat button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithSeatButton().get(0)));

        ValidationUtil.validateTestStep("Validating Continue without seat button is  displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton().get(0)));

        ValidationUtil.validateTestStep("Validating Seat Total Panel Color is Black",seatTotalPanelRGBValue,BLACK_RGB);
        ValidationUtil.validateTestStep("Validating Seat Total Text Color is White",seatTotalTextRGBValue,WHITE_RGB);

        ValidationUtil.validateTestStep("Validating continue button Text Color is Black",continueButtonTextRGBValue,WHITE_RGB);
        ValidationUtil.validateTestStep("Validating Seat Total Text Color is White",continueButtonBackgroundRGBValue,BLUE_RGB);

//-- Step 6: Navigate to Seat Map
        // Invalid Step

        /******************************************************************************
         *************************** Navigate to Seats Page (MT) **********************
         ******************************************************************************/
        //Going back to Home Page
        pageObjectManager.getHomePage().getSpiritLogoImage().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getHomePage().getBookPathLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());

        // Starting over from the beginning to verify seats page on Check In Path

        /************************* Home Page Methods ***************************/
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1, ARR_DATE_1);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /******************************Flight Availability Page Method ***************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("RET", DEP_FLIGHT);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        String DEP_CITY_1 = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText();
        String RET_CITY_1 = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText();

        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /******************************Passenger Information Page Method***************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /******************************Bags Page Method***********************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /******************************************************************************
         ***************************Seats Page Validations*****************************
         ******************************************************************************/
        ValidationUtil.validateTestStep("Validating Bags Page  is on the right URL",
                getDriver().getCurrentUrl(),SEAT_URL);

//-- Step 2: Navigate to Page Title
        ValidationUtil.validateTestStep("Validating the right title",
                pageObjectManager.getSeatsPage().getSeatPageHeaderText().getText(),HEADER_TEXT);

//-- Step 3: Navigate to Flight Content Block
        ValidationUtil.validateTestStep("Validating Departing city info",
                pageObjectManager.getSeatsPage().getFlightLegsText().get(0).getText(),DEP_CITY_1);

        ValidationUtil.validateTestStep("Validating first leg is active by default",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getPassengerNameText().get(0)));

        ValidationUtil.validateTestStep("Validating Returning city info",
                pageObjectManager.getSeatsPage().getFlightLegsText().get(1).getText(),RET_CITY_1);

        for (WebElement element : pageObjectManager.getSeatsPage().getPassengerSeatText()) {
            if (element.isDisplayed()) {
                String tempText = element.getText() ;
                ValidationUtil.validateTestStep("The Seat number field is empty" ,
                        "" , tempText);
            }
        }

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);

        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getSeatsPage().getPassengerDetailListLink().get(0).click();

        for (WebElement element : pageObjectManager.getSeatsPage().getPassengerSeatText()) {
            if (element.isDisplayed()) {
                String tempText = element.getText() ;
                ValidationUtil.validateTestStep("The Seat number field is empty" ,
                        tempText.length() > 0);
            }
        }

//-- Step 4: Navigate to Seat Total Content Block
        ValidationUtil.validateTestStep("Validating Total Content Block is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatsTotalText()));
        ValidationUtil.validateTestStep("Validating right content block text",
                pageObjectManager.getSeatsPage().getSeatsTotalText().getText(),SEAT_TOTAL);

        ValidationUtil.validateTestStep("Validating price is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatsTotalPriceText()));

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getSeatsPage().getSeatsTotalContainerLink().click();

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("Validating seats prices are displayed on the total breakdown for departure leg",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText()));

        ValidationUtil.validateTestStep("Validating seats prices are displayed on the total breakdown for returning leg",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText()));

//-- Step 5: Navigate to ‘Continue’ and ‘Continue Without Selecting Seats’ Action Button
        ValidationUtil.validateTestStep("Validating Continue With Seat button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithSeatButton().get(0)));


        ValidationUtil.validateTestStep("Validating Continue without seat button is  displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton().get(0)));

        ValidationUtil.validateTestStep("Validating Seat Total Panel Color is Black",seatTotalPanelRGBValue,BLACK_RGB);
        ValidationUtil.validateTestStep("Validating Seat Total Text Color is White",seatTotalTextRGBValue,WHITE_RGB);

        ValidationUtil.validateTestStep("Validating continue button Text Color is Black",continueButtonTextRGBValue,WHITE_RGB);
        ValidationUtil.validateTestStep("Validating Seat Total Text Color is White",continueButtonBackgroundRGBValue,BLUE_RGB);

//-- Step 6: Navigate to Seat Map
        //Invalid Step
        /******************************************************************************
         *************************** Navigate to Seats Page (CI) **********************
         ******************************************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /********************** Options Page Methods **********************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /********************** Payment Page Methods **********************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /********************** Confirmation Page Methods **********************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        /********************** Login to Online Check In Page Methods **********************/
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Selecting Add Seats link
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_SEAT);

        /********************** Reservation Summary Page Methods **********************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(SEAT_CI_TRIP_URL);

//-- Step 2: Navigate to Page Title
        ValidationUtil.validateTestStep("Validating the right title",
                pageObjectManager.getSeatsPage().getSeatPageHeaderText().getText(),HEADER_TEXT);

//-- Step 3: Navigate to Flight Content Block
        ValidationUtil.validateTestStep("Validating Departing city info",
                pageObjectManager.getSeatsPage().getFlightLegsText().get(0).getText(),DEP_CITY);

        ValidationUtil.validateTestStep("Validating first leg is active by default",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getPassengerNameText().get(0)));

        ValidationUtil.validateTestStep("Validating Returning city info",
                pageObjectManager.getSeatsPage().getFlightLegsText().get(1).getText(),RET_CITY);

        for (WebElement element : pageObjectManager.getSeatsPage().getPassengerSeatText()) {
            if (element.isDisplayed()) {
                String tempText = element.getText() ;
                ValidationUtil.validateTestStep("The Seat number field is empty" ,
                        "" , tempText);
            }
        }

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);

        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getSeatsPage().getPassengerDetailListLink().get(0).click();

        for (WebElement element : pageObjectManager.getSeatsPage().getPassengerSeatText()) {
            if (element.isDisplayed()) {
                String tempText = element.getText() ;
                ValidationUtil.validateTestStep("The Seat number field is empty" ,
                        tempText.length() > 0);
            }
        }

//-- Step 4: Navigate to Seat Total Content Block
        ValidationUtil.validateTestStep("Validating Total Content Block is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatsTotalText()));
        ValidationUtil.validateTestStep("Validating right content block text",
                pageObjectManager.getSeatsPage().getSeatsTotalText().getText(),SEAT_TOTAL);

        ValidationUtil.validateTestStep("Validating price is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatsTotalPriceText()));

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getSeatsPage().getSeatsTotalContainerLink().click();

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("Validating seats prices are displayed on the total breakdown for departure leg",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText()));

        ValidationUtil.validateTestStep("Validating seats prices are displayed on the total breakdown for returning leg",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText()));

//-- Step 5: Navigate to ‘Continue’ and ‘Continue Without Selecting Seats’ Action Button
        ValidationUtil.validateTestStep("Validating Continue With Seat button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithSeatButton().get(0)));


        ValidationUtil.validateTestStep("Validating Continue without seat button is  displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton().get(0)));

        ValidationUtil.validateTestStep("Validating Seat Total Panel Color is Black",seatTotalPanelRGBValue,BLACK_RGB);
        ValidationUtil.validateTestStep("Validating Seat Total Text Color is White",seatTotalTextRGBValue,WHITE_RGB);

        ValidationUtil.validateTestStep("Validating continue button Text Color is Black",continueButtonTextRGBValue,WHITE_RGB);
        ValidationUtil.validateTestStep("Validating Seat Total Text Color is White",continueButtonBackgroundRGBValue,BLUE_RGB);

//-- Step 6: Navigate to Seat Map
        // Invalid Step
    }
}