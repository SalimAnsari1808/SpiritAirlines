package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91425
//Test Case Name: Task 24685: 35352 CP_BP_Payment Page_Res Credit_Validate Error Messages
//Created By: Gabriela
//Created On: 5-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-Jul-2019
//**********************************************************************************************

public class TC91425 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "ReservationCredit" , "PaymentUI"})
    public void CP_BP_Payment_Page_Res_Credit_Validate_Error_Messages(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91425 under REGRESSION_CRITICAL Suite on " + platform + " Browser", true);
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
        final String CODE_NOT_LOCATE_ERROR  = "THECONFIRMATIONCODEYOUENTEREDCOULDNOTBELOCATED.PLEASEVERIFYYOURENTRYANDTRYAGAIN.";

        //open browser
        openBrowser(platform);

        /**************************Home Page Methods*********************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //create resCredit
        createResCredit();

        /*******************************Test Case**************************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE_2);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /***************Flight Availability Page Methods*****************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        String DEP_CITY_INFO = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText();
        String RET_CITY_INFO= pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText();
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        String DEP_TIME = pageObjectManager.getFlightAvailabilityPage().getDepartingFlightBlockDepartTimeText().get(1).getText().trim();
        String ARR_TIME = pageObjectManager.getFlightAvailabilityPage().getDepartingFlightBlockArivalTimeText().get(1).getText().trim();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*******************Passenger Information Page Methods***********************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        String FIRST_NAME_PAX     = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value");
        String LAST_NAME_PAX      = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value");
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /*************************Bags Page Methods**************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /******************Seats Page Methods********************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*************************Options Page Methods************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*************************Payment Page Methods***********************/
//-- Step 2:
        ValidationUtil.validateTestStep("Validating Departure City info",
                pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).getText(),DEP_CITY_INFO);

        ValidationUtil.validateTestStep("Validating Arrival city info",
                pageObjectManager.getPaymentPage().getArriveFlightCityNameText().get(0).getText(), RET_CITY_INFO);

        ValidationUtil.validateTestStep("Validating customer first name",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText(),FIRST_NAME_PAX);

        ValidationUtil.validateTestStep("Validating customer last name",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText(),LAST_NAME_PAX);

        ValidationUtil.validateTestStep("Validating Departing Time Info",
                pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).getText(),DEP_TIME);

        ValidationUtil.validateTestStep("Validating Arrival Time Info",
                pageObjectManager.getPaymentPage().getArriveFlightCityNameText().get(0).getText(),ARR_TIME);

//-- Step 3:
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditChevronLink().click();

//-- Step 4:
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys("+*()&^%");
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating the right error message displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().isDisplayed());
        pageObjectManager.getCommon().getIBlockCloseButton().click();

//-- Step 5:
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().clear();
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys("ACG876");
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating the right error message displayed", pageObjectManager.getCommon().getErrorMessageLabel().getText(),CODE_NOT_LOCATE_ERROR);

//-- Step 6:
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);
        String confirmationCode = scenarioContext.getContext(Context.RESERVATION_CREDIT_CODE).toString();
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys(confirmationCode);
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().sendKeys("asdf");
        pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton().click();

        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating right error message is displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().isDisplayed());
    }

    private void createResCredit() {
        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
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