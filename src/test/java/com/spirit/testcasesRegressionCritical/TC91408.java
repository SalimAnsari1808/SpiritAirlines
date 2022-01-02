package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//TODO Bug 25625: PROD: CP: BP: Payment Page PMT: User receiving Red Block error message after intro any Alphanumeric character on "Confirmation Code or Voucher Number" field by mistake
//Test Case ID: TC91408
//Test Case Name: Task 24696: 35358 CP_BP_Validating Forms of Payment_Res Credit_Neg
//Created By: Gabriela
//Created On: 18-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-Jul-2019
//**********************************************************************************************

public class TC91408 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "ReservationCredit" , "PaymentUI"})
    public void CP_BP_Validating_Forms_of_Payment_Res_Credit_Neg(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91408 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "6";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String PAYMENT_URL        = "/book/payment";
        final String ALPHANUMERIC_6     = "!@#$%^";
        final String ALPHANUMERIC_ERROR = "The Confirmation Code is entered is not valid";
        final String ALPHANUMERIC_8     = "!@#$%^&*";

        //open browser
        openBrowser(platform);

        /*********************************************************************************************************
         * ******************************************HOME PAGE****************************************************
         *********************************************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        creteRescredit();

//-- Step 1:
        /******************************************************************************
         ************************Navigate to Payment Page******************************
         ******************************************************************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
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
//--Step 2
        WaitUtil.untilPageLoadComplete(getDriver());
        //Validating Payment URL
        ValidationUtil.validateTestStep("User verify Navigated to Payment page", getDriver().getCurrentUrl(),PAYMENT_URL);

//-- Step 3
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditLink().click();

//-- Step 4
        ValidationUtil.validateTestStep("Res Credit section located",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox()));

//-- Step 5
        //TODO Bug 25625: PROD: CP: BP: Payment Page PMT: User receiving Red Block error message after intro any Alphanumeric character on "Confirmation Code or Voucher Number" field by mistake
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys(ALPHANUMERIC_6);
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating error message",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ALPHANUMERIC_ERROR);

        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 6
        //TODO Bug 25625: PROD: CP: BP: Payment Page PMT: User receiving Red Block error message after intro any Alphanumeric character on "Confirmation Code or Voucher Number" field by mistake
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys(ALPHANUMERIC_8);
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating error message",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ALPHANUMERIC_ERROR);

        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);

//--Step 7
        //Typing the valid reservation credit code
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.RESERVATION_CREDIT_CODE).toString());

        //Clicking on Go Button
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //Available Found info
        ValidationUtil.validateTestStep("Validating Available Found info is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getRedeemReservationCreditAvailableFundsText()));

        ValidationUtil.validateTestStep("Validating right amount to spend info is displayed",
                pageObjectManager.getPaymentPage().getRedeemVoucherAmountPriceText().getText(), scenarioContext.getContext(Context.RESERVATION_CREDIT_AMOUNT).toString());

        //Amount to Spend text
        ValidationUtil.validateTestStep("Validating Amount to Spend info is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getRedeemReservationCreditAvailableFundsText()));

        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);

        //Validating out of range error message id displayed
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().sendKeys("999");

        pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Invalid range error message is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().geRedeemVoucherInvalidNumberRangeText()));
    }

    public void creteRescredit()
    {
        //Home Page Constant variables
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE_1        = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "6";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_1";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";


        /*********************************************************************************************************
         * ***********************************RESCREDIT SECTION***************************************************
         *********************************************************************************************************/
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE_1);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seats page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
    }
}