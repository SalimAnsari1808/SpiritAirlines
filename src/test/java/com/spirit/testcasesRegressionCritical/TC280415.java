package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280415
//Test Name: Task 23139: 31432 448. E2E_9DFC_MultiCity DOM 1 ADT 1 CHILD +5 -15_SW Change Date and Airports Thru Flight_PAX1 Hearing Disability_Bags_2BFS_No Extras_CI Web_Voucher Res Cred CC
// Created By: Gabriela
//Created On : 08-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 09-Aug-2019
//**************************************************************************************************
public class TC280415 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath","MultiCity","DomesticDomestic","WithIn21Days","Adult","Child","NineDFC","NewFlightSearch","Through","PassengerInfoSSR","CarryOn","CheckedBags","Bikes","SurfBoard","BigFrontSeat","ShortCutBoarding","CheckInOptions","Voucher","ReservationCredit","MasterCard"})
    public void E2E_9DFC_MultiCity_DOM_1_ADT_1_CHILD_5_15_SW_Change_Date_and_Airports_Thru_Flight_PAX1_Hearing_Disability_Bags_2BFS_No_Extras_CI_Web_Voucher_Res_Cred_CC(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280415 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "LAS|DEN";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX|DFW";
        final String TRAVEL_DATE        = "3|5";
        final String ADULTS             = "1";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String TRAVEL_DATE_1      = "7|9";
        final String DEP_AIRPORT_CODE_1 = "BOS|OAK";
        final String ARR_AIRPORT_CODE_1 = "FLL|DTW";
        final String DEP_FLIGHT         = "Through";
        final String ARR_Flight         = "Through";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Passenger Information Page Constant Values
        final String SSR                = "HearingImpaired";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_0|Checked_3||Carry_1|Checked_0";

        //Seats Page Constant Values
        final String DEP_SEATS          = "BigFront|BigFront";

        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE         = "MasterCard";
        final String TRAVEL_GUARD      = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
        openBrowser(platform);

        /********************** Home Page Methods ********************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
//        //create voucher
//        createVoucher();
//
//        //create resCredit
//        createResCredit();

        pageObjectManager.getHomePage().getSpiritLogoImage().click(); //click on book link
        WaitUtil.untilPageLoadComplete(getDriver());

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);

        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(TRAVEL_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        /********************** Flight Availability Page Methods ********************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE_1, ARR_AIRPORTS, ARR_AIRPORT_CODE_1);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(TRAVEL_DATE_1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        /********************** Passenger Information Page Methods ********************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /***************************** Bags Page Methods *****************************************/
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

        //Selecting 2 bikes
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(1).click();
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count < 2; count ++){
            pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(1).click();
        }

        //Selecting 2 Surf
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count < 2; count ++){
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(1).click();
        }

        pageObjectManager.getBagsPage().getContinueWithBagsButton().click();

        /***************************** Seats Page Methods *****************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /***************************** Options Page Methods *****************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /***************************** Payment Page Methods *****************************************/
        pageMethodManager.getPaymentPageMethods().applyVoucherNumber();

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.RESERVATION_CREDIT_CODE).toString());
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().sendKeys("50");
        pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton().click();

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /***************************** Confirmation Page Methods *****************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl().contains(CONFIRMATION_URL));

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));
    }
    private void createVoucher() {
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAX";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "5";
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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        WaitUtil.untilTimeCompleted(1200);
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("NineFCMember");
        pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).sendKeys(passengerInfoData.title);

        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);

        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //Options Page Methods
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        WaitUtil.untilTimeCompleted(1200);
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("NineFCMember");
        pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).sendKeys(passengerInfoData.title);

        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);

        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);

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