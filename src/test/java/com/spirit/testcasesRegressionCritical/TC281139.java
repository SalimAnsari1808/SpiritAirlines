package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281139
//Description: 181. E2E_Guest_RT INT 1 ADT 1 INFT (Lap) 2 Children 2+_SW Change PAX 1 ADT 1 INF (Lap) 1 Child 2+, Bundle It [Tier 3] Fare, Thru Flight_1 PAX Own Wheel Chair Wet Cell_Bundle It [Tier 3] Bags_Bundle It [Tier 3] Seats_No Extras, CI Web_Reservation cre
//Created By : Anthony Cardona
//Created On : 30-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 3-June-2019
//**********************************************************************************************
public class TC281139 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticInternational" , "WithIn7Days" , "Adult" , "Child" , "InfantLap" , "Guest" ,
                    "NewFlightSearch" , "Nonstop" , "BundleIt" , "PassengerInfoSSR" , "CarryOn" , "CheckedBags" ,
                    "DynamicShoppingCartUI" , "Standard" , "MasterCard" , "ReservationCredit" , "TravelInsuranceBlock" , "OptionalUI" })
    public void E2E_Guest_RT_INT_1_ADT_1_INFT_Lap_2_Children_2_SW_Change_PAX_1_ADT_1_INF_Lap_1_Child_2_Bundle_It_Tier_3_Fare_Thru_Flight_1_PAX_Own_Wheel_Chair_Wet_Cell_Bundle_It_Tier_3_Bags_Bundle_It_Tier_3_Seats_No_Extras_CI_Web_Reservation_cre(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281139 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String TRIP_TYPE1         = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "BWI";
        final String DEP_AIRPORT_CODE1  = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String ARR_AIRPORT_CODE1  = "LAX";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "10";
        final String ADULTS             = "1";
        final String CHILD              = "2";
        final String INFANT_LAP         = "1";
        final String CHILD1             = "0";
        final String INFANT_LAP1        = "0";
        final String INFANT_SEAT        = "0";
        //Flight Availability Page Constant Values

        final String NEW_CHILD          = "1";
        final String DEP_FLIGHT         = "Nonstop";
        final String RET_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";
        final String UPGRADE_VALUE1     = "BookIt";

        //Passenger Info Page Constant Values
        final String WHEEL_CHAIR_TYPE   = "hasWheelchairBatteryPoweredWetCell";
        final String SSR_FIELD          = "OwnWheelChair-BatteryPoweredWetCell";

        //Bags Page Constant Values
        final String BUNDLE_ITINERARY   = "Bundle It Discount";

        //Seats Page Constant Values
        final String SEATS_DEP          = "Standard|Standard";
        final String SEATS_RET          = "Standard|Standard";

        //option page constant value
        final String OPTION_PAGE        = "CheckInOption:MobileFree";

        //Payment Page Constant Value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        //open browser
        openBrowser(platform);

        //Create res credit and continue to the payment page
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE1);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE1, ARR_AIRPORTS, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD1, INFANT_SEAT, INFANT_LAP1);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE1);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_PAGE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Start of Manage TRavel
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Home Page Method
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        //Search Widget Modify New Date
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, NEW_CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR_FIELD);
        WaitUtil.untilTimeCompleted(3000);
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getWheelChairTypeOfWheelChairDropDown().get(0),WHEEL_CHAIR_TYPE);

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        ValidationUtil.validateTestStep("Validating Bags Total Price displayed is 0.00 on Bags Page for Bundle It",
                pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText(),"$0.00");
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();
        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS_DEP);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS_RET);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();
        //Options Page Methods
        //verify Shortcut Boarding is selected with carry on
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));
        //verify Flight Flex is selected with carry on
        ValidationUtil.validateTestStep("Verify Flight Flex is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));
        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of Lap Infant on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}