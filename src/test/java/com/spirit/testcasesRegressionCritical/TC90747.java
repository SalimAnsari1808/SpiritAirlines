package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90747
//Test Case Name: Task Task 24671: 35313 CP_BP_Payment Method_Reservation_Credit_Wireframe
//Created By: Gabriela
//Created On: 3-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 5-Jul-2019
//**********************************************************************************************

public class TC90747 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" ,"CheckInOptions", "ReservationCredit" , "PaymentUI"})
    public void CP_BP_Payment_Method_Reservation_Credit_Wireframe(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90747 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "OneWay";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE_2     = "MCO";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "NA";
        final String ADULT                  = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment page constant value
        final String PAYMENT_URL            = "/book/payment";
        final String RES_CREDIT_VERBIAGE    = "Please enter the 6-character Confirmation Code you received" +
                                                " in your confirmation email when you booked the original " +
                                                "reservation or enter the 8-character alphanumeric Travel " +
                                                "Voucher Number you received as credit towards a future booking";
        final String HEADER                 = "Confirmation Code or Voucher Number";
        final String RES_CREDIT_TERMS       = "If you are attempting to use credit remaining from a reservation " +
                                                "that you cancelled or modified, voucher numbers are no longer applicable. " +
                                                "Any remaining balance is considered a Reservation Credit and has been stored " +
                                                "in your original booking. Reservation Credit is based on previous booking " +
                                                "and can be used to pay for anything except Travel Insurance.";
        final String ERROR                  = "Invalid number range.";
        final String FUNDS_TEXT             = "Available Funds:";
        final String APPLY_BUTTON           = "APPLY CREDIT";

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//-- Step 1:
        /*********************************************************************************************************
         * ***********************************RESCREDIT SECTION***************************************************
         *********************************************************************************************************/
       createResCredit();

        /******************************************************************************
         ************************Navigate to Payment Page******************************
         ******************************************************************************/
//-- Step 2
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE_2);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Page Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*******************************************************************
         //***********************Payment Page Validation********************
         //******************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //Saving the Total Due in a variable for future comparision
        String tempTotal = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
        String Total = tempTotal.substring(tempTotal.indexOf("$"));

        String TotalSub1 = Total.replace("$" , "");
        final double TotalDouble = Double.parseDouble(TotalSub1);

        ValidationUtil.validateTestStep("Validating Payment Page right URL", getDriver().getCurrentUrl(),PAYMENT_URL);

// Step 3:
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditChevronLink().click();

//-- Step 4:
        ValidationUtil.validateTestStep("Validating ResCredit section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox()));

//-- Step 5
        ValidationUtil.validateTestStep("Validating right ResCredit info",
                pageObjectManager.getPaymentPage().getRedeemReservationCreditSubHeaderText().getText(),RES_CREDIT_VERBIAGE);

//-- Step 6:
        ValidationUtil.validateTestStep("Validating right title displayed",
                pageObjectManager.getPaymentPage().getRedeemReservationCreditConfirmationCodeText().getText(),HEADER);

//-- Step 7:
        ValidationUtil.validateTestStep("Validating GO button is available on resCredit section",
                pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().isEnabled());

//-- Step 8:
        ValidationUtil.validateTestStep("Validating Travel Insurance information",
                pageObjectManager.getPaymentPage().getRedeemReservationCreditFooterText().getText(), RES_CREDIT_TERMS);

//-- Step 9: ResCredit rules description

//-- Step 10: Under 11, 12 and 13 validation

//-- Step 11:
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys("ACG876");
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating the right error message displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCommon().getErrorMessageLabel()));
        pageObjectManager.getCommon().getIBlockCloseButton().click();

//-- Step 12:
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys("+*()&^%");
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating the right error message displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCommon().getErrorMessageLabel()));
        pageObjectManager.getCommon().getIBlockCloseButton().click();

//-- Step 10:
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);
        String confirmationCode = scenarioContext.getContext(Context.RESERVATION_CREDIT_CODE).toString();
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys(confirmationCode);
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();

//-- Step 13:
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().sendKeys("900");

        pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton().click();

        ValidationUtil.validateTestStep("Validating right error message is displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR);


        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating ResCredit funds section displayed after click on Go button",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton()));

//-- Step 14:
        ValidationUtil.validateTestStep("Validating right text displayed",
                pageObjectManager.getPaymentPage().getRedeemReservationCreditAvailableFundsText().getText(),FUNDS_TEXT);

        String reservationCreditAmount = scenarioContext.getContext(Context.RESERVATION_CREDIT_AMOUNT).toString();

        ValidationUtil.validateTestStep("Validating the right funds amount is displayed",
                pageObjectManager.getPaymentPage().getRedeemVoucherAmountText().getText(),reservationCreditAmount);

        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().sendKeys("10");

        ValidationUtil.validateTestStep("Validating Apply Credit button displayed properly",
                pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton().getText(), APPLY_BUTTON);

//-- Step 15
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Opening Total Due BreakDown for prices validation
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getPaymentPage().getTotalDueChevronLink());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getTotalDueVoucherAndCreditChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);
        double resCreditAmount = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDueVouchersAndCreditPriceListText().get(0).getText().replace("-$",""));

        //Converting voucher and resCredit displayed to validate with the calculated one
        WaitUtil.untilTimeCompleted(2000);
        String vCTotal = pageObjectManager.getPaymentPage().getTotalDueVoucherAndCreditPriceText().getText().replace("-$", "");
        double vCAmount = Double.parseDouble(vCTotal);
        ValidationUtil.validateTestStep("Validating the right discount amount is displayed", vCAmount == resCreditAmount);

        //Subtracting the voucher + resCredit from the original Total amount to validate the right price is displayed
        WaitUtil.untilTimeCompleted(3000);
        double validate = TotalDouble - resCreditAmount;

        WaitUtil.untilTimeCompleted(3000);
        String totalLast = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$", "");
        String totalLastSub = totalLast.replace(",", "");
        double totalEnd = Double.parseDouble(totalLastSub);

        WaitUtil.untilTimeCompleted(3000);
        ValidationUtil.validateTestStep("Validating voucher and reservation credit discount were applied properly", totalEnd == validate);
    }
    public void createResCredit()
    {
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "OneWay";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAX";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "NA";
        final String ADULT                  = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GUARD           = "NotRequired";


//-- Step 1:
        /*********************************************************************************************************
         * ***********************************RESCREDIT SECTION***************************************************
         *********************************************************************************************************/
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
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
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

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
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
    }
}