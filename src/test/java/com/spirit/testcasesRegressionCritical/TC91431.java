package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Date;

//**********************************************************************************************
//Test Case ID: TC91431
//Description:  CP_BP_Payment Page_Volunteer Future Travel Voucher_NEG_attempt to redeem during major holiday
//Created By:   Salim Ansari
//Created On:   24-July-2019
//Reviewed By:  Kartik Chauhan
//Reviewed On:  24-July-2019
//**********************************************************************************************
public class TC91431 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Adult" , "Guest" , "Connecting" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" ,"Voucher", "PaymentUI","OutOfExecution"})
    public void CP_BP_Payment_Page_Volunteer_Future_Travel_Voucher_NEG_attempt_to_redeem_during_major_holiday(@Optional("NA") String platform) {
        /******************************************************************************
         ******************************Navigate to Purchase Page***********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91431 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        ValidationUtil.skipTestCase("This Test case is Skipped as not possible with Automation",true);

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = getUSHolidayDate();
        final String ARR_DATE           = "NA";
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

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "Required";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);
//STEP--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //create voucher
        //createVoucher();

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
        scenarioContext.setContext(Context.RESERVATION_VOUCHER_CODE,"27415223420100001");
        final String PAYMENT_URL            = "book/payment";
        final String EXPIRE_VOUCHER_ERROR   = "Unfortunately there are restrictions on this voucher and or the selected dates. Please try a different date or remove the voucher and try again. For additional assistance please call us at 801.401.222.";

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

        //emter voucher nukber
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

    private void createVoucher() {
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAX";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "1";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "Notrequired";

        ValidationUtil.validateTestStep("*******Started Creating Voucher Number*******",true);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("*******Ending Creating Voucher Number*******",true);
    }


    private String getUSHolidayDate() {
        //Declare constant used in method
        String HOLIDAY_LIST = "8/30/19, 9/2/19, 11/27/19, 11/30/19, 12/1/19, 12/2/19, 12/20/19, 12/21/19, 12/26/19, 12/27/19, 12/28/19, 12/29/19, 1/1/20, 1/3/20, 1/4/20, 1/5/20";

        //get current date in correct format
        Date d2 = TestUtil.convertStringToDate(TestUtil.getStringDateFormat("0", "MM/dd/yy"), "MM/dd/yy");

        //loop through all avaliable dates
        for (int dateCounter = 0; dateCounter < HOLIDAY_LIST.split(",").length; dateCounter++) {
            //convert given date into correct format
            Date d1 = TestUtil.convertStringToDate(HOLIDAY_LIST.split(",")[dateCounter].trim(), "MM/dd/yy");

            //check holiday date is avaliable in future
            if (((d1.getTime() - d2.getTime()) / 86400000) > 1) {
                //return day difference to be used during booking
                return Long.toString(Math.abs((d1.getTime() - d2.getTime()) / 86400000));
            }
        }
        //Validate future USA holiday dates are not avaliable in test case
        ValidationUtil.validateTestStep("Enter Future USA Holiday dates in test case TC91431", false);

        //return null value
        return null;
    }

}
