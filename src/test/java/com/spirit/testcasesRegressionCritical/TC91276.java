package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//military passenger is nota able to verify on Payment page. After entering detail page os redirected to home page
//Test Case ID: TC91276
//Description: Task 24673: 35316 CP_BP_Payment_Page Military Validation
//Created By: Gabriela
//Created On: 1-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 4-Jul-2019
//**********************************************************************************************

public class TC91276 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "OneWay", "DomesticDomestic", "WithIn7Days", "Adult", "Military","FSMiles","NonStop", "CarryOn","CheckedBags","NoSeats","ShortCutBoarding","CheckInOptions","PaymentUI", "Discover"})
    public void CP_BP_Payment_Page_Military_Validation(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91276 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "MilitaryFSMiles";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "BWI";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE      = "BoostIt";

        //Passenger Info Page Constant Values
        final String MILITRY_BAG_POPUP  = "MilitaryBags";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_2";

        //Options Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";
        final String OPTION_VALIDATION  = "ShortCutBoarding";

        //Payment Page Constant Values
        final String PAYMENT_URL        = "/book/payment";
        final String MILITARY_HEADER    = "Active Military Personnel";
        final String MILITARY_INFO      = "Thank you for your service";
        final String MILITARY_INFO_1    = "Each Active Duty U.S. Military Personnel customer needs to be verified individually to get baggage discounts.";
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";
        final String MILI_VERIFICATION  = "Please click 'Verify' below to confirm active military status";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

//-- Step 1 & 2: Start creating a booking
        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//-- Step 3: Navigate to Passenger Info page - Complete Primary Passenger required information
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 4: Select the Active Duty U.S. Military Personnel checkbox and Continue with booking process
        pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().get(1).click();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//-- Step 5: If Tier 2 or Tier 3 combo were selected on Flight Availability page, user should receive a Sorry popup
//      modal advising you can't combine Military bags with bundles.
        pageMethodManager.getPassengerInfoMethods().selectMilitaryBagBundlePopup(MILITRY_BAG_POPUP);

//-- Step 6: User should be redirected through booking process - Reach Payment Page
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Validations*********************************
         ****************************************************************************/
//-- Step 7: On the Payment Page, user should see an Active Duty U.S. Military Personnel container under the
//  Passenger Info section (#3 on screenshot)
        ValidationUtil.validateTestStep("Validating Military container is displayed",
                pageObjectManager.getPaymentPage().getActiveMilitrayHeaderText().getText(),MILITARY_HEADER);

//-- Step 13: If a user forgets to click the Verify link under Verification and selects "BOOK TRIP $XXX.XX", uers
// should receive an error message with the following verbiage: "Please click "Verify" below to confirm active military status"
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        ValidationUtil.validateTestStep("Verifying error is received when no military verification was done",
                pageObjectManager.getPaymentPage().getActiveMilitaryPleaseVerifyPopUpText().getText(),MILI_VERIFICATION);

//-- Step 14: If a user forgets to click the Verify link twice in a row, user should receive a SORRY popup modal
// advising "Your Active military affiliation couldn't be verified at this time..."
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getBookTripButton().click();

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating SORRY pop up is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getSorryPopUpMilitaryRepricePanel()));

        pageObjectManager.getPaymentPage().getSorryPopUpMilitaryRepriceCloseButton().click();

//-- Step 8: Located under Verification, select Verify - user should be redirected to an external website,
//  https://api.id.me/en/session/new to input their ID me military account email and password
        ValidationUtil.validateTestStep("Validating right info displyed",
                pageObjectManager.getPaymentPage().getActiveMilitraySubHeaderText().getText(),MILITARY_INFO_1);

        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();

//-- Step 9: After entering ID me credentials and signing in, ID.me sign-in screen should read You are now returning to DEV SPIRIT
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),PAYMENT_URL);

/** Step 10: If the ID.me was not verified, user should see Verification Failed under Verification**/

//-- Step 11: If the ID.me was verified, user should see Thank you for your service with a red Troop ID box in the right corner
        ValidationUtil.validateTestStep("Validating the right info is displayed after military verification",
                pageObjectManager.getPaymentPage().getActiveMilitaryThankYouText().getText(),MILITARY_INFO);

/** Step 12: If ID.me was not verified and user selects "BOOK TRIP $XXX.XX", user should receive a SORRY popup modal advising
 * "Your Active military affiliation couldn't be verified at this time..." Following the verbiage should be two options,
 * Re-price bags or Start New Booking.*/

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(OPTION_VALIDATION);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}