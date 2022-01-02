package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;

//**********************************************************************************************
//Test Case ID: TC380906
//Description: Task 25940: Inhibited Document_CP_CI_DOM_UMNR triggers inhibited document
//Created By : Gabriela
//Created On : 20-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 21-Aug-2019
//**********************************************************************************************

public class TC380906 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Child" , "NonStop" , "Guest" , "BookIt" , "NoBags" , "NoSeats" , "Discover" , "BoardingPassUI"})
    public void Inhibited_Document_CP_CI_DOM_UMNR_triggers_inhibited_document(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC380906 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAS";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "3";
        final String ADULT              = "0";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_FARE       = "BookIt";

        //payment page constant value
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";

        //Reservation Summary Page Constant Value
        final String POPUP_PURCHASE     = "DontPurchase";
        final String CHECKIN_HAZMAT     = "accept";
        final String BOARDING_POPUP     = "Print";

        // Boarding Pass Constant Values
        final String BOARDING_URL       = "boarding-pass";
        final String CONFIRMATION_EXT   = " Confirmation Code ";
        final String PRINT_ME           = " Print Me ";
        final String DOC_TITLE          = "MINOR DETECTED! GET THEE TO AN AGENT!";
        final String DOC_MESSAGE        = "We love kids, but online check-in is unavailable for unaccompanied minors.";
        final String BULLET_1           = "An Unaccompanied Minor Form needs to be completed at the airport by an adult with photo I.D. before you can travel";
        final String BULLET_2           = "The Unaccompanied Minor Service includes a snack and drink - SCORE!";
        final String BULLET_3           = "Visit spirit.com/minors prior to travelling for additional information";

        //open browser
        openBrowser(platform);
//- Step 7: Prerequisite book a DOM flight that departs in less than 24hrs, make sure your booking is for a UMNR,  Check in and print the boarding pass.
        /***************************************** Home Page Method ********************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        /************************************ Flight Availability Methods ******************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        String dateFlight = pageObjectManager.getFlightAvailabilityPage().getSelectedDepDateText().getText();

        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        String flightNumber = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().replace("Flight NK ", "");
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        /********************************* Passenger Info Page Methods *********************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /*************************************** Bags Page Methods *************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /********************************** Seats Page Methods ****************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /************************************* Options Page Methods ************************************************/
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /************************************** Payment page methods *********************************************/
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /*************************************** Confirmation Page Methods *************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        pageMethodManager.getHomePageMethods().loginToCheckIn();

        /************************************ Check In Page Methods ************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(POPUP_PURCHASE);
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(POPUP_PURCHASE);

        /********************************** Options Page Check In Path Method *********************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(CHECKIN_HAZMAT);

        //print boarding pass
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(BOARDING_POPUP,"NA");

        //validate user taken to the boarding pass page
        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(2000);

        TestUtil.closeBoardingPassPrintPopup();

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("user redirected to the Boarding pass page", getDriver().getCurrentUrl(),BOARDING_URL);

//-- Step 2: Verify the top left part of the boarding pass
        //The word "YOUR FLIGHT" in bold should be on the top of this section
        ValidationUtil.validateTestStep("Validating 'Your Flight' text is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getFlightHeaderText()));

        // The day in the following format should be shown week day, month, day, year
        System.out.println("Inhibited date: " + getDriver().findElements(By.xpath("(//p[@class='label'])[1]")).get(0).getText().replace("Your Flight",""));
        ValidationUtil.validateTestStep("Validating date is displayed in the right format",
                getDriver().findElements(By.xpath("(//p[@class='label'])[1]")).get(0).getText().replace("Your Flight",""),dateFlight);

        //Then there should be a black horizontal line
        ValidationUtil.validateTestStep("Validating horizontal black line is displayed ",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//hr[@class='ng-star-inserted'][1]"))));

        // After that should be the words "CONFIRMATION CODE" with your confirmation code in bold next to it
        System.out.println("Confirmation Code Text " + getDriver().findElement(By.xpath("//p[contains(@class,'confirmCode')]//span")).getText());
        ValidationUtil.validateTestStep("Validating 'Confirmation Code' text is displayed" ,
                getDriver().findElement(By.xpath("//p[contains(@class,'confirmCode')]//span")).getText(),CONFIRMATION_EXT);

        ValidationUtil.validateTestStep("Validating Confirmation code is displayed properly",
                pageObjectManager.getBoardingPassPage().getInhibitedDocConfirmationCode().get(0).getText(),scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        String [] DepFlightDetails = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split("\\|");
        System.out.println(Arrays.toString(DepFlightDetails));
        String FlightNumber =  DepFlightDetails[0].replace("Number:NK",":");

//-- Step 3: Verify the top right of the inhibited document
        //Verify 9dfc Marketing image is displayed
        ValidationUtil.validateTestStep("Verify 9fc Marketing image is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getFareClubSavingImage().get(0)));

//-- Step 4: Verify the bottom left of the inhibited document
        // The word "Print Me" in all bold with red exclamation markings next to it
        ValidationUtil.validateTestStep("Validating Print Me text is displayed",
                pageObjectManager.getBoardingPassPage().getInhibitedDocPrintMeText().get(0).getText(),PRINT_ME);

        // A red box that has the following verbiage "MINOR DETECTED! GET THEE TO AN AGENT!
        ValidationUtil.validateTestStep("Validating Doc Title displaying the right info",
                pageObjectManager.getBoardingPassPage().getInhibitedDocTitleText().get(0).getText(), DOC_TITLE);

        // We love kids, but online check-in is unavailable for unaccompanied minors.
        ValidationUtil.validateTestStep("Validating right inhibited message is displayed",
                pageObjectManager.getBoardingPassPage().getInhibitedDocMessage().get(0).getText(), DOC_MESSAGE);

        //• An Unaccompanied Minor Form needs to be completed at the airport by an adult with photo I.D. before the kid can travel.
        ValidationUtil.validateTestStep("Validating right info is displayed for UNMR inhibited bullet_1",
                pageObjectManager.getBoardingPassPage().getInhibitedDocBullet1Text().get(0).getText(),BULLET_1);

        //• The Unaccompanied Minor Service includes a snack and drink. SCORE!
        ValidationUtil.validateTestStep("Validating right info is displayed for UNMR inhibited bullet_2",
                pageObjectManager.getBoardingPassPage().getInhibitedDocBullet2Text().get(0).getText(), BULLET_2);

        //• Visit spirit.com/minors prior to travelling for additional information.
        ValidationUtil.validateTestStep("Validating right info is displayed for UNMR inhibited bullet_3",
                pageObjectManager.getBoardingPassPage().getInhibitedDocBullet3Text().get(0).getText(), BULLET_3 );

//-- Step 5: Verify the bottom right of the inhibited document
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
        ValidationUtil.validateTestStep("Validate departing and arriving city is displayed",
                pageObjectManager.getBoardingPassPage().getInhibitedDocOriginAndDestiantionText().get(0).getText(), DepFlight + "\n" + ArrFlight);

        //Validate Depart label and time is displayed
        String DepartureTime = DepFlightDetails[4].replace("DepartureTime:","");
        ValidationUtil.validateTestStep("Validate Depart label and time is displayed",TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getInhibitedDocDepartingTimeLabel().get(0))
                && pageObjectManager.getBoardingPassPage().getInhibitedDocDepartingTimeText().get(0).getText().replace(" ","").equals(DepartureTime));

        //Validate Arriving label and time is displayed
        ValidationUtil.validateTestStep("Validate Arriving label and time is displayed",TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getInhibitedDocArrivingTimeLabel().get(0))
                && pageObjectManager.getBoardingPassPage().getInhibitedDocArrivingTimeText().get(0).getText().replace(" ","").equals(ArrivingTime));

        //Validate Duration label and time is displayed
        ValidationUtil.validateTestStep("Validate Duration label and time is displayed",TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getInhibitedDocFlightDurationLabel().get(0))
                && pageObjectManager.getBoardingPassPage().getInhibitedDocFlightDurationText().get(0).getText().replaceAll(" ","").equalsIgnoreCase(DurationTime));

//-- Step 6: Go to SkySpeed and verify all SSRS that have been created
        //Step is unable to automated

    }
}