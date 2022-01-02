package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.enums.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.text.*;
import java.util.*;

//**********************************************************************************************
//Test Case ID: TC369580
//Test Name:    Inhibited Document_CP_CI_DOM_Emotional Support Animal triggers inhibited document
//Created By :  Sunny Sok
//Created On :  20 -Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 21-Aug-2019
//**********************************************************************************************

public class TC369580 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NonStop" , "Guest" , "BookIt" , "PassengerInfoSSR" , "NoBags" , "NoSeats", "MasterCard" , "BoardingPassUI"})
    public void Inhibited_Document_CP_CI_DOM_Emotional_Support_Animal_triggers_inhibited_document(@Optional("NA") String platform) {
        /******************************************************************************
         **********************Navigate to Boarding Pass Page********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC369580 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "BWI";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "5";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADEVALUE       = "BookIt";

        //Passenger info page Constant Values
        final String DISABILITY         ="EmotionalAnimal";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

        //--Step 1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        WaitUtil.untilPageLoadComplete(getDriver());
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(DISABILITY);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify Check-In Option is disabled on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        pageMethodManager.getHomePageMethods().loginToCheckIn();

        /******************************************************************************
         ***********************Validation Checin page*********************************
         ******************************************************************************/

        //declare constant used in Validation
        final String Checkin_URL = "check-in/reservation-summary";

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Checkin Page",
                getDriver().getCurrentUrl(),(Checkin_URL));

        ValidationUtil.validateTestStep("User Verify Check in and Print boarding pass button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton()));

        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup("DontPurchase");
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup("DontPurchase");
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup("DontPurchase");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup("NotRequired");
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");

        pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(0).click();
        pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(1).click();
        pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassButton().click();

        /******************************************************************************
         ***********************Validation on Boarding pass****************************
         ******************************************************************************/
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

        //declare constant used in Validation
        final String BOARDING_PASS_URL       = "check-in/boarding-pass";
        final String INHIBITED_HEADER        = "VISIT AN AGENT TO CLEAR YOUR ANIMAL!";
        final String INHIBITED_CONTEXT_LINE1 = "Sorry, we'll need to verify your emotional support animal before you fly.";
        final String INHIBITED_CONTEXT_LINE2 = "Current documentation for emotional support animals is required (no older than one year before your scheduled initial flight)";
        final String INHIBITED_CONTEXT_LINE3 = "Visit spirit.com/esa for full emotional support animal guidelines and restrictions";
        final String PASSENGERNAME           = passengerInfoData.firstName + " " + passengerInfoData.lastName;

        String [] DepFlightDetails = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split("\\|");

        //wait for pagfe load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //close print popup
        TestUtil.closeBoardingPassPrintPopup();

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
                getDriver().getCurrentUrl().contains(BOARDING_PASS_URL));

        //Validate Welcome Aboard is displayed
        ValidationUtil.validateTestStep("Validate Welcome Aboard is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getWelcomeHeaderText().get(0)));

        //Verify PAX name
        ValidationUtil.validateTestStep("Validate Pax Name",
                pageObjectManager.getBoardingPassPage().getUserNameText().get(0).getText(),passengerInfoData.firstName + "\n" + passengerInfoData.lastName);

        //Validate This is not a boarding Pass is displayed
        ValidationUtil.validateTestStep("Validate This is not a boarding Pass is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getNotBoardingPassText().get(0)));

        //Verify Boarding Time
        String DepartureTime = DepFlightDetails[4].replace("DepartureTime:","");
        String BoardingExpectedTime = getDesiredTime(DepartureTime , 0 , -45, "h:m");
        ValidationUtil.validateTestStep("Verify Boarding Time",
                pageObjectManager.getBoardingPassPage().getSelfBagTagText().get(0).getText(),BoardingExpectedTime);

        //Validate Bring this page to avoid being charged is displayed
        ValidationUtil.validateTestStep("Validate Bring this page to avoid being charged is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getBringThisPageToAvoidBeingChargedText().get(0)));

        //Verify 9dfc Marketing image is displayed
        ValidationUtil.validateTestStep("Verify 9fc Marketing image is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getFareClubSavingImage().get(0)));

        //Validate Inhibited Print me is displayed
        ValidationUtil.validateTestStep("Validate Inhibited Print me is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getInhibitedDocPrintMeText().get(0)));

        //Validate inhibited header is displayed on boarding pass
        ValidationUtil.validateTestStep("Validate inhibited header is displayed on boarding pass",
                pageObjectManager.getBoardingPassPage().getInhibitedDocTitleText().get(0).getText(), INHIBITED_HEADER);

        //Validate inhibited Context is displayed
        ValidationUtil.validateTestStep("Validate inhibited Context is displayed",
                pageObjectManager.getBoardingPassPage().getInhibitedDocMessage().get(0).getText(), INHIBITED_CONTEXT_LINE1);

        //Validate Users Name is displayed in inhibited section
        ValidationUtil.validateTestStep("Validate Users Name is displayed in inhibited section",
                pageObjectManager.getBoardingPassPage().getInhibitedPaxNameText().get(0).getText(), PASSENGERNAME);

        //Validate inhibited Context is displayed
        ValidationUtil.validateTestStep("Validate inhibited Context is displayed",
                pageObjectManager.getBoardingPassPage().getInhibitedDocBullet1Text().get(0).getText(), INHIBITED_CONTEXT_LINE2);

        //Validate inhibited Context is displayed
        ValidationUtil.validateTestStep("Validate inhibited Context is displayed",
                pageObjectManager.getBoardingPassPage().getInhibitedDocBullet2Text().get(0).getText(), INHIBITED_CONTEXT_LINE3);

        //Validate Flight information
        ValidationUtil.validateTestStep("Verify verbiage Your flight is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getFlightHeaderText()));

        //Validate Depature Date
        String DepDate = TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE), "EEEE, MMMM d, y");
        ValidationUtil.validateTestStep("Validate Departure date",
                pageObjectManager.getBoardingPassPage().getInhibitedDocFlightDateText().get(0).getText().replace("Your Flight",""), DepDate);

        //Validate Confirmation Code
        ValidationUtil.validateTestStep("Validate Confirmation Code",
                pageObjectManager.getBoardingPassPage().getInhibitedDocConfirmationCode().get(0).getText(), scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        //Validate flight number
        String FlightNumber =  DepFlightDetails[0].replace("Number:NK",":");
        ValidationUtil.validateTestStep("Verify Flight Number",
                pageObjectManager.getBoardingPassPage().getInhibitedDocFlightNumber().get(0).getText(), FlightNumber);

        //Validate terminal is displayed
        ValidationUtil.validateTestStep("Verify Terminal is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getInhibitedDocterminalText().get(0)));

        String DepFlightTemp = scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString();
        String DepFlight = DepFlightTemp.substring(0,DepFlightTemp.lastIndexOf('(')-1) + " - " + DEP_AIRPORT_CODE;

        String ArrFlightTemp = scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString();
        String ArrFlight = ArrFlightTemp.substring(0,ArrFlightTemp.lastIndexOf('(')-1) + " - " + ARR_AIRPORT_CODE;

        //Validate departing city is displayed
        String ArrivingTime = DepFlightDetails[5].replace("ArrivalTime:","");
        String DurationTime = DepFlightDetails[6].replace("FlightDuration:","").replace(",","").toUpperCase();
        ValidationUtil.validateTestStep("Validate departing and arriving city is displayed", pageObjectManager.getBoardingPassPage().getInhibitedDocOriginAndDestiantionText().get(0).getText(), DepFlight + "\n" + ArrFlight);

        //Validate Depart label and time is displayed
        ValidationUtil.validateTestStep("Validate Depart label and time is displayed",TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getInhibitedDocDepartingTimeLabel().get(0))
                && pageObjectManager.getBoardingPassPage().getInhibitedDocDepartingTimeText().get(0).getText().replace(" ","").equals(DepartureTime));

        //Validate Arriving label and time is displayed
        ValidationUtil.validateTestStep("Validate Arriving label and time is displayed",TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getInhibitedDocArrivingTimeLabel().get(0))
                && pageObjectManager.getBoardingPassPage().getInhibitedDocArrivingTimeText().get(0).getText().replace(" ","").equals(ArrivingTime));

        //Validate Duration label and time is displayed
        ValidationUtil.validateTestStep("Validate Duration label and time is displayed",TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getInhibitedDocFlightDurationLabel().get(0))
                && pageObjectManager.getBoardingPassPage().getInhibitedDocFlightDurationText().get(0).getText().replaceAll(" ","").equalsIgnoreCase(DurationTime));


    }

    private String getDesiredTime(String boardingTime, int hourIncrementor, int minIncrementor, String dateFormat) {

        //create calendar instance
        Calendar calendar = Calendar.getInstance();

        int hour = Integer.parseInt(boardingTime.substring(0 , boardingTime.indexOf( ":")));
        int min = Integer.parseInt(boardingTime.substring(boardingTime.indexOf(":")+1, boardingTime.indexOf(":")+ 3));

        calendar.set(Calendar.HOUR_OF_DAY , hour);
        calendar.set(Calendar.MINUTE ,  min);

        //Increment calendar date to get departure date
        calendar.add(Calendar.HOUR_OF_DAY, hourIncrementor);
        calendar.add(Calendar.MINUTE, minIncrementor);

        //get date from calendar in correct format
        Date date = calendar.getTime();

        //set format in which date is required
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        String time = dateFormatter.format(date);

        if( time.charAt(0) == '0') {
            time = time.substring(1);
        }

        if (boardingTime.contains("AM")) {
            time = time + "AM";
        }
        else {
            time = time + "PM";
        }

        //convert date in required format
        return  time;
    }
}