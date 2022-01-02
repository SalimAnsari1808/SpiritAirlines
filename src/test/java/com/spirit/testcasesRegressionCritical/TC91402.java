package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91402
//Test Case Name:Task 24690: 35353 CP_BP_Payment Page_Validate Terms and Conditions Error Message
//Created By: Gabriela
//Created On: 16-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 16-Jul-2019
//**********************************************************************************************

public class TC91402 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "PaymentUI"})
    public void CP_BP_Payment_Page_Validate_Terms_and_Conditions_Error_Message(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91402 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String PAYMENT_URL        = "/book/payment";
        final String AMEX_CARD          = "AmericanExpressCard";
        final String VISA_CARD          = "VisaCard";
        final String DISCOVER_CARD      = "DiscoverCard1";
        final String MASTER_CARD        = "MasterCard";
        final String UATP_CARD          = "135410189003949";
        final String TC_ERROR           = "You must agree to the Terms and Conditions in order to complete your reservation.";
        final String TRAVEL_GUARD 		= "NotRequired";

        //Confirmation Page Constant Values
        final String CONFIRMATION_URL   = "/book/confirmation";

        //open browser
        openBrowser(platform);
//--Step 1
        /****************************************************************************
         * *********************Navigate to Payment Page ****************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*********************************Passenger Info Methods*************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /**************************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*********************************Payment Page Methods*************************************************/
//-- Step 2:
        WaitUtil.untilPageLoadComplete(getDriver());

        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL", getDriver().getCurrentUrl(), PAYMENT_URL);

//-- Step 3:
        ValidationUtil.validateTestStep("Locating Payment section",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPaymentHeaderText()));

//-- Step 4
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(AMEX_CARD);

//-- Step 5 and 6: Attempting to finish the booking without accept terms and conditions
        pageObjectManager.getPaymentPage().getBookTripButton().click();

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("error message displayed regarding to accept Terms and Conditions",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(), TC_ERROR);

//-- Step 7
        WaitUtil.untilTimeCompleted(1000);
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox());
        WaitUtil.untilTimeCompleted(1000);
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox());
        WaitUtil.untilTimeCompleted(1000);
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox());

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(VISA_CARD);
        pageObjectManager.getPaymentPage().getBookTripButton().click();

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("error message displayed regarding to accept Terms and Conditions",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(), TC_ERROR);

        //-- Step 8
        WaitUtil.untilTimeCompleted(1000);
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox());
        WaitUtil.untilTimeCompleted(1000);
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox());
        WaitUtil.untilTimeCompleted(1000);
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox());

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(DISCOVER_CARD);
        pageObjectManager.getPaymentPage().getBookTripButton().click();

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("error message displayed regarding to accept Terms and Conditions",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(), TC_ERROR);

//-- step 9:
        WaitUtil.untilTimeCompleted(1000);
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox());
        WaitUtil.untilTimeCompleted(1000);
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox());
        WaitUtil.untilTimeCompleted(1000);
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox());

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(MASTER_CARD);
        pageObjectManager.getPaymentPage().getBookTripButton().click();

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("error message displayed regarding to accept Terms and Conditions",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(), TC_ERROR);

//-- Step 10: Validated with Master Card Step

//- Step 11: UATP Credit Card is put of scope.

//-- Step 12 and 13
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().travelGuardRecommendedPopUp();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        WaitUtil.untilPageLoadComplete(getDriver());
        //Confirmation URL Validation
        //Confirmation Page Method
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        ValidationUtil.validateTestStep("Validating Confirmation Page URL",
                getDriver().getCurrentUrl(), CONFIRMATION_URL);
    }
}