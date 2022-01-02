package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Check3
//Test CaseID: TC369591
//Title: Task 23914: 31504 423. E2E_9DFC_OW DOM Multi ADT_SW Change Date Book It [Tier 1]_Connecting Flight_STD_No Bags_EXIT Seats_No Extras_CI Web_Travel Guard_Reservation Credit_Visa
//Description: Validating 9FC member can change the original journey information on FA page and can select Travel Insurance and pay with a credit card
//Created By : Gabriela
//Created On : 9-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 11-May-2019
//**********************************************************************************************

public class TC369591 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NineDFC" ,
                     "NewFlightSearch" , "Connecting" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "Visa" ,
                     "ReservationCredit" , "TravelInsuranceBlock","PaymentUI","ConfirmationUI"})
    public void E2E_9DFC_OW_DOM_Multi_ADT_SW_Change_Date_Book_It_Tier_1_Connecting_Flight_STD_No_Bags_EXIT_Seats_No_Extras_CI_Web_Travel_Guard_Reservation_Credit_Visa(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC369591 under Smoke Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE         = "English";
        final String LOGIN_ACCOUNT    = "NineDFCEmail";
        final String JOURNEY_TYPE     = "Flight";
        final String TRIP_TYPE        = "OneWay";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE = "LAS";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE1= "LGA";
        final String DEP_DATE         = "5";
        final String DEP_DATE1        = "8";
        final String ARR_DATE         = "NA";
        final String ADULTS           = "5";
        final String CHILD            = "0";
        final String INFANT_LAP       = "0";
        final String INFANT_SEAT      = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT1      = "Connecting";
        final String FARE_TYPE        = "Member";
        final String UPGRADE_VALUE    = "BookIt";

        //Options page Constant Values
        final String OPTIONS_VALUE    = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE        = "VisaCard";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS   = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

        //create resCredit
        createResCredit();

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //New search button
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

        //Selecting a new date
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE1, ARR_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Selecting Connecting flight and Book It discount option
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT1);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Method
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*******************************************************************
        //***********************Payment Page Methods************************
        //*******************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();
        pageMethodManager.getPaymentPageMethods().calculateTravelGuard();

        //verify TG amount
        ValidationUtil.validateTestStep("Verify the travel Guard amount appear on Travel Guard Section on Payment Page",
        		pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText(),scenarioContext.getContext(Context.PAYMENT_TRAVELGUARD_PRICE).toString());

        //ValidationUtil.validateTestStep("",false);
        //Selecting Travel Guard
        pageMethodManager.getPaymentPageMethods().selectTravelGuard("Required");

        //enter credit card
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));

        pageMethodManager.getConfirmationPageMethods().verifyTravelInsuranceSection();
    }
    private void createResCredit() {
        //Reservation Credit Path Constant variables
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
        //Bags page constant Values
        final String DEP_BAG            = "Carry_1|Checked_1";
        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";
        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_CARD        = "NotRequired";
        //Home Page Methods
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
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAG);
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();
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