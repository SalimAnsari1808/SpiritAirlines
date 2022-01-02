package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


// **********************************************************************************************
//Check4
// TestCase :TC91101
// Description: Task 23052: 31347 BoardingPass_CP_CI_Email delivery option dont check any of the check boxes (Bags Drop Off)
// Created By : Kartik Chauhan
// Created On : 22-April-2019
// Reviewed By:
// Reviewed On:
// **********************************************************************************************
public class TC91101 extends BaseClass {

    @Parameters ({"platform"})
    @Test (groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "TravelInsuranceBlock" , "MasterCard" , "BoardingPassUI", "ReservationUI"})
    public void BoardingPass_CP_CI_Email_delivery_option_dont_check_any_of_the_check_boxes_Bags_Drop_Off (@Optional("NA")String platform){
        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC91101 under SMOKE suite on " + platform +" Browser", true);
        }
        /******************************************************************************
         ********************************Navigate to Boarding Page*********************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORTING_TYPE		= "Departure";
        final String DEP_FLIGHT			= "Connecting";
        final String FARE_TYPE			= "standard";
        final String UPGRADE_TYPE		= "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "Required";
        final String CARD_TYPE			= "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS 	= "Confirmed";

        //Check In Bags Page constant values
        final String CHECKIN_SEAT_POPUP	= "Required";

        //CheckIn Seat Page
        final String CHECKIN_DEP_SEATS	= "Standard||Standard";


//Step1
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORTING_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(3000);

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        //pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        WaitUtil.untilTimeCompleted(5000);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        WaitUtil.untilTimeCompleted(5000);
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//Step--2
        /*********************************************Start OF CheckIn Path**********************/
        //login to checkIn Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //*********************************Verify First Check-In and Get Boarding pass button(At top right)****

//Step--3
        //Check-In and Boarding Pass Button is displaying
        ValidationUtil.validateTestStep("Check-In and Boarding Pass Button is displaying at the bottom center of the page",
                pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).isDisplayed());

        //Click on Check In Boarding Pass
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);
//Step--4
        //Bags page during check-in path
        pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupNopeIAmGoodButton().click();

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);

        //Bags page during check-in path
        pageObjectManager.getReservationSummaryPage().getChooseYourSeatGetRandomSeatButton().click();

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup("NotRequired");

        //validate Accept and Print Boarding Pass
        ValidationUtil.validateTestStep("Accept and Print Boarding Pass is displayed",
                pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupAcceptBoardingPassButton().isDisplayed());

        //Make a click on Accept and Print Boarding Pass
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);


//Step--4
        //create Web Element for finish Check-In button
        pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassButton().click();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());
        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);

        TestUtil.closeBoardingPassPrintPopup();

        WaitUtil.untilPageLoadComplete(getDriver());
        /******************************************************************************
         ***********************Validation to Boarding Page****************************
         ******************************************************************************/
//Step--6
        //declare constant used in Validation
        final String BOARDING_PASS_URL = "check-in/boarding-pass";

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
                getDriver().getCurrentUrl(), BOARDING_PASS_URL);

//Step--7 and Step--8
        //wait till 4 sec
        WaitUtil.untilTimeCompleted(4000);

        //CheckIn Seat Page
        final String WELCOME_ABOARD	= "Welcome Aboard";
        final String YOUR_BOARDINGPASS	= "This is your";
        final String FARECLUBSAVING_IMAGE_ALT = "Fare Club Savings";
        final String SPIRIT_LOGO_IMAGE_ALT = "Spirit Logo";
        final String BOARDING_PASS = "BOARDING PASS";
        final String DATE_TEXT = "DATE";
        final String FROM_TEXT = "FROM:";
        final String TO_TEXT = "TO:";
        final String SEQ_TEXT = "Seq #";
        final String CONFIRMATION_TEXT = "CONFIRMATION";
        final String FLIGHT_TEXT = "FLIGHT";
        final String GATE_TEXT = "GATE";
        final String CHECK_SCREEN_TEXT = "CHECK SCREEN";
        final String BOARDING_TIME_TEXT = "BOARDING TIME";
        final String BOARDING_ACTUAL_TIME_TEXT = "BOARDING TIME";
        final String LOCATED_TERMINAL = "LOCATED IN TERMINAL";
        final String DOOR_CLOSED = "DOORS CLOSE 15 MINUTES PRIOR TO DEPARTURE.";
        final String ZONE = "ZONE";
        final String SEAT = "SEAT";
        final String SEAT_NUMBER = "SEAT";
        final String DEPARTS = "DEPARTS";
        final String ARRIVE = "ARRIVE";
        final String ISSUED = "ISSUED";


        //validate Welcome verbiage is displaying correct
        ValidationUtil.validateTestStep("Welcome verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getWelcomeHeaderText().get(0).getText(),WELCOME_ABOARD);

        System.out.println(pageObjectManager.getBoardingPassPage().getYourBoardingPassText().get(0).getText());
        //validate 'This is your boarding Pass' verbiage is displaying correct
        ValidationUtil.validateTestStep("'This is your boarding Pass' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getYourBoardingPassText().get(0).getText(),YOUR_BOARDINGPASS);
//Step--9
        //validate '$9 Image' is displaying correct
        ValidationUtil.validateTestStep("'$9 Image' is displaying correct",pageObjectManager.getBoardingPassPage().getFareClubSavingImage().get(0).getAttribute("alt"),FARECLUBSAVING_IMAGE_ALT);
//Step--10
        //validate 'Spirit' Logo is displaying correct
        ValidationUtil.validateTestStep("'Spirit' Logo is displaying correct",pageObjectManager.getBoardingPassPage().getSpiritLogoImage().get(0).getAttribute("alt"),SPIRIT_LOGO_IMAGE_ALT);

        //validate 'Boarding Pass' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Boarding Pass' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getBoardingPassHeader().get(0).getText(),BOARDING_PASS);

        //validate 'Date' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Date' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getDate().get(0).getText(),DATE_TEXT);

        //validate 'FROM' verbiage is displaying correct
        ValidationUtil.validateTestStep("'FROM' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getFromText().get(0).getText().toUpperCase(),FROM_TEXT);


        //validate 'To' verbiage is displaying correct
        ValidationUtil.validateTestStep("'To' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getToText().get(0).getText().toUpperCase(),TO_TEXT);

        //validate 'SEQ' verbiage is displaying correct
        ValidationUtil.validateTestStep("'SEQ' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getSeqText().get(0).getText(),SEQ_TEXT);

        //validate 'Confirmation' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Confirmation' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getConfirmationText().get(0).getText().toUpperCase(),CONFIRMATION_TEXT);

        //validate 'Confirmation-Code' is displaying correct
        ValidationUtil.validateTestStep("'Confirmation-Code' is displaying correct",pageObjectManager.getBoardingPassPage().getConfirmationCode().get(0).getText(),scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

//Step--11
        //validate 'Flight' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Flight' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getFlightHeaderText().get(0).getText(),FLIGHT_TEXT);

        //validate 'GATE' verbiage is displaying correct
        ValidationUtil.validateTestStep("'GATE' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getGateText().get(0).getText().toUpperCase(),GATE_TEXT);

        //validate 'CHECK SCREEN' verbiage is displaying correct
        ValidationUtil.validateTestStep("'CHECK SCREEN' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getCheckScreensText().get(0).getText().toUpperCase(),CHECK_SCREEN_TEXT);

        //validate 'BOARDING TIME' verbiage is displaying correct
        ValidationUtil.validateTestStep("'BOARDING TIME' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getBoardingTimeText().get(0).getText(),BOARDING_TIME_TEXT);

        //validate 'LOCATED IN TERMINAL' verbiage is displaying correct
        ValidationUtil.validateTestStep("'LOCATED IN TERMINAL' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getLocatedinTerminalText().get(0).getText().toUpperCase(),LOCATED_TERMINAL);

        //validate 'DOOR CLOSE' verbiage is displaying correct
        ValidationUtil.validateTestStep("'DOOR CLOSE' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getDoorCloseText().get(0).getText().toUpperCase(),DOOR_CLOSED);

        //validate 'ZONE' verbiage is displaying correct
        ValidationUtil.validateTestStep("'ZONE' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getZoneText().get(0).getText(),ZONE);

        //validate 'SEAT' verbiage is displaying correct
        ValidationUtil.validateTestStep("'SEAT' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getSeatText().get(0).getText(),SEAT);

        //validate 'DEPART' verbiage is displaying correct
        ValidationUtil.validateTestStep("'DEPART' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getDepartsText().get(0).getText(),DEPARTS);

        //validate 'ARRIVE' verbiage is displaying correct
        ValidationUtil.validateTestStep("'ARRIVE' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getArriveText().get(0).getText().toUpperCase(),ARRIVE);

        //validate 'ISSUED' verbiage is displaying correct
        ValidationUtil.validateTestStep("'ISSUED' verbiage is displaying correct",pageObjectManager.getBoardingPassPage().getIssuedText().get(0).getText(),ISSUED);
//STEP--12
        //********************************************SKYSPEED***********************************************
    }

}

