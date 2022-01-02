package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//**********************************************************************************************
//Test CaseID: TC281172
//Test Name:  E2E_Guest_RT_INT_1_ADT_1_INFT_Direct_Flight_Military_Bundle_It_Bags_Bundle_It_Seats_No_Extras_CI_Web_Credit_Card
//Description:
//Created By : Alex Rodriguez
//Created On : 11-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Jun-2019
//**********************************************************************************************


public class TC281172 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticInternational" , "WithIn7Days" , "Adult" , "InfantLap" ,
                     "Military" , "NonStop" , "CarryOn" , "CheckedBags" ,  "Standard" , "ShortCutBoarding" , "OptionalUI" ,
                     "AmericanExpress","BundleIt","ConfirmationUI"})
    public void E2E_Guest_RT_INT_1_ADT_1_INFT_Direct_Flight_Military_Bundle_It_Bags_Bundle_It_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281172 under Critical REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE         = "English";
        final String JOURNEY_TYPE     = "Flight";
        final String TRIP_TYPE        = "RoundTrip";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE = "BWI";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE = "CUN";
        final String DEP_DATE         = "5";
        final String ARR_DATE         = "10";
        final String ADULTS           = "1";
        final String CHILD            = "0";
        final String INFANT_LAP       = "1";
        final String INFANT_SEAT      = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT       = "Nonstop";
        final String RET_FLIGHT       = "Nonstop";
        final String FARE_TYPE        = "Standard";
        final String UPGRADE_VALUE    = "BundleIt";

        //bags page constant
        final String BAGS_URL         = "book/bags";
        final String BAGS             = "Carry_1|Checked_2";
        final String BAG_PRICE        = "$0.00";

        //Seats page constant
        final String SEAT             = "Standard";

        //Payment page Constant values
        final String SELECTED_OPTION  = "ShortCutBoarding";
        final String TRAVEL_GUARD     = "NotRequired";
        final String CARD_TYPE        = "AmericanExpressCard";

//STEP--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--2 & STEP--3
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        pageMethodManager.getPassengerInfoMethods().selectMilitaryBagBundlePopup("MilitaryBags");


        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Option Page Methods
        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"), "disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Purchase Page Methods
        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS   = "Confirmed";
        final String CONFIRMATION_URL = "book/confirmation";

        //confirmation page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(), CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(), BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);

    }
}
