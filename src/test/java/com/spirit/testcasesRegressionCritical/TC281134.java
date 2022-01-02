package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281134
//Description: 177. E2E_Guest_RT DOM 1 ADT 1 Child 2-5_SW Change Airports, Bundle Fare, Direct Flight_Need Help To and From Seats_1 Carry On BP, 5 Checked_Bundle Seats_No Extras, CI Web_Credit Card
//Created By : Anthony Cardona
//Created On : 03-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 04-Jun-2019
//**********************************************************************************************

public class TC281134 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Child" , "Guest" , "NewFlightSearch" ,
                    "Nonstop" , "BundleIt" , "PassengerInfoSSR" , "DynamicShoppingCartUI" , "CarryOn" , "CheckedBags" , "Standard" ,
                    "Visa" , "TravelInsuranceBlock" , "OptionalUI"})
    public void E2E_Guest_RT_DOM_1_ADT_1_Child_2_5_SW_Change_Airports_Bundle_Fare_Direct_Flight_Need_Help_To_and_From_Seats_1_Carry_On_BP_5_Checked_Bundle_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281134 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "Roundtrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAX";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "10";
        final String ADULTS                 = "1";
        final String CHILD                  = "1";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        //Flight Availability Page Constant Values
        final String NEW_DEP_AIRPORT_CODE   = "LAS";
        final String NEW_ARR_AIRPORT_CODE   = "DFW";

        final String DEP_FLIGHT             = "Nonstop";
        final String RET_FLIGHT             = "Nonstop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BundleIt";
        //Passenger Page Constant Value
        final String ADDITIONAL_SSR         = "WheelChairNeedFromSeat";
        //Bags Page Constant
        final String DEPARTING_BAGS         = "Carry_1|Checked_1|Checked_2|Checked_3|Checked_4|Checked_5||Carry_1";
        final String BUNDLE_ITINERARY       = "Bundle It Discount";
        //Seats Page Constant Values
        final String SEATS_DEP              = "Standard|Standard";
        final String SEATS_RET              = "Standard|Standard";
        //Options Page Constant Values
        final String OPTIONS_VALUE          = "CheckInOption:DecideLater";
        //Payment Page Constant Value
        final String CARD_TYPE              = "VisaCard";
        final String TRAVEL_GUARD           = "NotRequired";
        //Confirmation Page Constant Values
        final String BOOKING_STATUS         = "Confirmed";
        //open browser
        openBrowser(platform);

        pageMethodManager.getHomePageMethods().launchSpiritApp();

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
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, NEW_DEP_AIRPORT_CODE, ARR_AIRPORTS, NEW_ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);


        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(ADDITIONAL_SSR);

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(), BUNDLE_ITINERARY);

        ValidationUtil.validateTestStep("Validating Bags Total Price displayed is 0.00 on Bags Page for Bundle It",
                pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText(), "$0.00");

        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEPARTING_BAGS);
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS_DEP);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(SEATS_RET);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();
        //Options Page Methods
        //verify Shortcut Boarding is selected with carry on
        ValidationUtil.validateTestStep("Verify Flight Flex is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(), BOOKING_STATUS);
    }
}