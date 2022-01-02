package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//TODO: Bug 25053: CP: BP: Payment Page PMT: Reservation Credit can be used for a different user to was originally issued
//Test Case ID: TC91432
//Test Case Name: Task 24681: 35350 CP_BP_Payment Page_NEG_Res Credit_PAX name must match one on PNR
//Created By: Gabriela
//Created On: 3-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 4-Jul-2019
//**********************************************************************************************
public class TC91432 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" ,"ReservationCredit", "PaymentUI"})
    public void CP_BP_Payment_Page_NEG_Res_Credit_PAX_name_must_match_one_on_PNR(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91432 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE_2 = "MCO";
        final String DEP_DATE           = "5";
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

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //create resCredit
        createResCredit();

        /******************************************************************************
         *******************************Test Case**************************************
         ******************************************************************************/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE_2);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        String DEP_CITY_INFO = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText();
        String RET_CITY_INFO = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText();
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        String FIRST_NAME_PAX   = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value");
        String LAST_NAME_PAX    = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value");

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /*******************************************************************
         /************************Bags Page Methods**************************
         /*******************************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*******************************************************************
         /*************************Seats Page Methods************************
         /*******************************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*******************************************************************
         /***********************Options Page Methods************************
         /*******************************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*******************************************************************
         //***********************Payment Page Methods************************
         //******************************************************************/
//-- Step 2:
        ValidationUtil.validateTestStep("Validating Departure City info",
                pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).getText(),DEP_CITY_INFO);

        ValidationUtil.validateTestStep("Validating Arrival city info",
                pageObjectManager.getPaymentPage().getArriveFlightCityNameText().get(0).getText(), RET_CITY_INFO);

        ValidationUtil.validateTestStep("Validating customer first name",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText(),FIRST_NAME_PAX);

        ValidationUtil.validateTestStep("Validating customer last name",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText(),LAST_NAME_PAX);

//-- Step 3:
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditChevronLink().click();

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Redeem a Voucher or Credit section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getVoucherNumberTextBox()));

//-- Step 4:
        String confirmationCode = scenarioContext.getContext(Context.RESERVATION_CREDIT_CODE).toString();
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys(confirmationCode);
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();

        //TODO: Bug 25053: CP: BP: Payment Page PMT: Reservation Credit can be used for a different user to was originally issued
        ValidationUtil.validateTestStep("Validate error message displayed for reservation credit redemption with mismatched names",
                pageObjectManager.getCommon().getErrorMessageLabel().isDisplayed());
    }

    private void createResCredit() {
        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "5";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";
        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_CARD        = "NotRequired";
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        WaitUtil.untilPageLoadComplete(getDriver());
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
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_CARD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}