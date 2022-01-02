package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC281186
//Title      : 31573 158. E2E_Guest_RT DOM 1 UMNR_SW Change to Mutli ADT and change date, Bundle Combo, Direct Flight_Standard_Bundle Bags_No Seats_No Extras CI Web_Credit Card
//Created By : Kartik
//Created On : 31-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 31-May-2019
//**********************************************************************************************

public class TC281186 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" ,"Adult" , "Child" , "InfantLap" , "Guest" ,
                     "NewFlightSearch" , "NonStop" , "BundleIt" , "DynamicShoppingCartUI" , "CarryOn" , "CheckedBags" , "Standard" ,
                     "FlightFlex" , "ShortCutBoarding" , "OptionalUI" , "Visa"})
    public void E2E_Guest_RT_DOM_1_ADT_1_INFT_Lap_SW_Change_Month_Direct_Flight_Standard_No_Bags_No_Seats_No_Extras_CI_Web_Travel_Guard_Credit_Card(@Optional("NA") String platform) {
        //*******************************************************************
        //*******************Navigate to Confirmation Page*******************
        //*******************************************************************
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281186 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "8";
        final String ADULT              = "0";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String ADULT1             = "4";
        final String CHILD1             = "2";
        final String INFANT_LAP1        = "1";
        final String DEP_DATE1          = "35";
        final String ARR_DATE1          = "38";
        final String DEP_FLIGHT         = "nonstop";
        final String RET_FLIGHT         = "nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        // Bags page constant
        final String BAGS_FARE          = "Standard";
        final String BUNDLE_ITINERARY   = "Bundle It Discount";

        //seats page constant value
        final String DEP_SEATS          = "Standard|Standard|Standard|Standard|Standard|Standard";
        final String RET_SEATS          = "Standard|Standard|Standard|Standard|Standard|Standard";

        //Payment page constant values
        final String CARD_TYPE           = "VisaCard";
        final String TRAVEL_GUARD        = "NotRequired";

        //Confirmation page constant values
        final String BOOKING_STATUS      = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //accept UMNR popup
        pageObjectManager.getHomePage().getUMNRAcceptButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //New sear on Search Widget is made and new dates are selected
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE1, ARR_DATE1);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT1, CHILD1, INFANT_SEAT, INFANT_LAP1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        WaitUtil.untilTimeCompleted(4000);
//        pageObjectManager.getHomePage().getUMNRAcceptButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Methods
        //Bags Page Methods
        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart",
                scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText().replace("-",""));

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);

        //Seat Page method
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();


        //Options Page Methods
        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of Lap Child on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //*******************************************************************
        //*******************Validation ton Confirmation Page****************
        //*******************************************************************
        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}
