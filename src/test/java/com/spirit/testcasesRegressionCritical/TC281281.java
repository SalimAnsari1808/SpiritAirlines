package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: Method failing due Bug 24457: CP: BP: Payment Page PMT: 500 (Internal Server Error) received when Military Passenger Validation failing
//Test Case ID: TC281281
//Description: 545. E2E_9DFC_RT INT 1 ADT Miles_ConnectingFlight_WheelChairManual_1 CO 3CB_NoSeats_SB CI web_Mastercard
//Created By: Anthony Cardona
//Created On: 17-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 20-Jun-2019
//**********************************************************************************************
//Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page

public class TC281281 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "BookPath" , "RoundTrip" , "DomesticInternational" , "WithIn7Days" , "Adult" , "Connecting" ,
                    "NineDFC" , "Military" , "Miles" ,"NewFlightSearch", "BookIt" , "PassengerInfoSSR" , "CarryOn" , "NoSeats" ,
                    "CheckedBags" , "MasterCard" , "OptionalUI"})
    public void E2E_9DFC_RT_INT_1_ADT_Miles_ConnectingFlight_WheelChairManual_1_CO_3CB_NoSeats_SB_CI_web_Mastercard(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281281 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "Military9DFC";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "ATL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "CUN";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "6";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String NEW_DEP_DATE 		= "4";
        final String NEW_ARR_DATE 		= "7";
        final String DEP_FLIGHT 		= "Connecting";
        final String RET_FLIGHT 		= "Connecting";
        final String FARE_TYPE			= "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Passanger Information Page Constant Values
        final String SSR                = "OwnWheelChair-ManuallyPowered";

        //Bags Page Method
        final String SELECT_BAGS    	= "Carry_1|Checked_3";

        //Option Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Value
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
        openBrowser(platform);
        /*********************************************************************************************************
         * ***********************************RESCREDIT SECTION***************************************************
         *********************************************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageObjectManager.getHomePage().getMilesLabel().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectDates(NEW_DEP_DATE, NEW_ARR_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();


        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(SELECT_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(SELECT_BAGS);
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Seats page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page Methods
        //verify Shortcut Boarding is selected with carry on
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}