package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91430
//Description:  CP_BP_Payment Page_Voucher_attempt to redeem expired voucher
//Created By:   Salim Ansari
//Created On:   24-July-2019
//Reviewed By:  Kartik Chauhan
//Reviewed On:  24-July-2019
//**********************************************************************************************
public class TC91430 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "Voucher" , "PaymentUI"})
    public void CP_BP_Payment_Page_Voucher_attempt_to_redeem_expired_voucher(@Optional("NA") String platform) {
        /******************************************************************************
         ******************************Navigate to Purchase Page***********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91430 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "4";
        final String ARR_DATE           = "7";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String ARR_Flight         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //open browser
        openBrowser(platform);
//STEP--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //Store expire voucher information
        scenarioContext.setContext(Context.RESERVATION_VOUCHER_CODE,"27415362555100001");
        scenarioContext.setContext(Context.RESERVATION_VOUCHER_AMOUNT,"10");

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ****************************Validation on Purchase Page***********************
         ******************************************************************************/
        final String PAYMENT_URL            = "book/payment";
        final String EXPIRE_VOUCHER_ERROR   = "The voucher number entered has expired.  Please try to enter the number again or contact the Spirit Reservations Center at 801-401-2222.";

       //Wait till payment url appear
        WaitUtil.untilPageURLVisible(getDriver(),PAYMENT_URL);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verify User reached to Payment Page",
                getDriver().getCurrentUrl(),PAYMENT_URL);

        //check redeem reservation credit link
        if(pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditChevronLink().getAttribute("style").contains("-180deg")){
            pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditChevronLink().click();
        }

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //enter voucher number
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys(scenarioContext.getContext(Context.RESERVATION_VOUCHER_CODE).toString());

        //click on Go button
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Verify Expire Voucher Error message appear on Payment Page",
                pageObjectManager.getCommon().getIBlockVerbiageText().getAttribute("aria-label"),EXPIRE_VOUCHER_ERROR);
    }
}