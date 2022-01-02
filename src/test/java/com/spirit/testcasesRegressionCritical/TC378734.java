package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Check5
//Test Case ID: TC378734
//Description : CP_DOM_CI_Bag Modal_Add Bags
//Created By : Sunny Sok
//Created On : 17-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-JUL-2019
//**********************************************************************************************

public class TC378734 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "BagsUI" , "AddEditBags","ReservationUI"})
    public void CP_DOM_CI_Bag_Modal_Add_Bags(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Payment Page*******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC378734 under REGRESSION-CRITICAL Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LGA";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Nonstop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_FARE		= "BookIt";

        //Options Page constant values
        final String OPTION_VALUE 		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String VALID_CARD_TYPE    = "MasterCard";

        //Seats pop up
        final String SEATS             = "DontPurchase";

        //open browser
        openBrowser( platform);

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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait until Bags page appear on web
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //options
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(VALID_CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        WaitUtil.untilPageLoadComplete(getDriver());

        /******************************************************************************
         ***************************Validation on Payment Page*************************
         ******************************************************************************/
        final String CHECKIN_URL   = "check-in/reservation-summary";
        final String OPTIONS_URL   = "check-in/extras";
        final String BAGS_URL      = "check-in/bags";

        ValidationUtil.validateTestStep("User verify Navigated to online check- in page",
                getDriver().getCurrentUrl(),CHECKIN_URL);

        //verify check-in and print boarding pass top button
        ValidationUtil.validateTestStep("User verify top check-in and print boarding pass button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getTopCheckInAndGetBoardingPassButton()));

        //verify check-in and print boarding pass bottom button
        ValidationUtil.validateTestStep("User verify Bottom check-in and print boarding pass button is displayed",
                TestUtil.verifyElementDisplayed( pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton()));

        //verify bags modal appears
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

        ValidationUtil.validateTestStep("User verify bags popup is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getBagsPopupHeaderText()));

        //Select I dont need bags
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup("DontPurchase");

        //verify user proceeds to checkin path
        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(SEATS);

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User verify Navigated to Options page",
                getDriver().getCurrentUrl(),OPTIONS_URL);

        //Restart checkin path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        ValidationUtil.validateTestStep("User verify Navigated to Online Check-In page",
                getDriver().getCurrentUrl(),CHECKIN_URL);

        //select buy bags
        pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup("Purchase");

        //verify user lands on bags page
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User verify Navigated to Add Bags Now And Save page",
                getDriver().getCurrentUrl(),BAGS_URL);
    }
}