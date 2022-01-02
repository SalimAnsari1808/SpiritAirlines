package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281137
//Description: 180. E2E_Guest_RT DOM 1 ADT 1 INFT_SW Change 1 ADT 2 INFT, Bundle It [Tier 3] Fare, Direct Flight_Emotional Support Animal_Bundle It [Tier 3] Bags_Bundle It [Tier 3] Seats_SB and FF_CI Web_Credit Card
//Created By : Anthony Cardona
//Created On : 31-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 03-May-2019
//**********************************************************************************************

public class TC281137 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "InfantSeat" , "Guest" ,
                    "NewFlightSearch" , "Nonstop" , "BundleIt" , "PassengerInfoSSR" , "CarryOn" , "DynamicShoppingCartUI" ,
                    "Standard" , "ShortCutBoarding" , "MasterCard" , "TravelInsuranceBlock","OptionalUI"})
    public void E2E_Guest_RT_DOM_1_ADT_1_INFT_SW_Change_1_ADT_2_INFT_Bundle_It_Tier_3_Fare_Direct_Flight_Emotional_Support_Animal_Bundle_It_Tier_3_Bags_Bundle_It_Tier_3__Seats_SB_and_FF_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281137 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "10";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "1";
        //Flight Availability Page Constant Values

        final String NEW_INFANT_SEAT    = "2";

        final String DEP_FLIGHT         = "Nonstop";
        final String RET_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";
        //Passenger Page Constant Value
        final String ADDITIONAL_SSR     = "EmotionalAnimal";
        //Bags Page Constant Values
        final String BUNDLE_ITINERARY   = "Bundle It Discount";
        //Seats Page Constant Values
        final String SEATS_DEP          = "Standard|Standard|Standard";
        final String SEATS_RET          = "Standard|Standard|Standard";
        //Payment Page Constant Value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "Required";
        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        //open browser
        openBrowser(platform);

        //Home Page Method
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
        WaitUtil.untilPageLoadComplete(getDriver());
        //Search Widget Modify New Date
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, NEW_INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        for (int i = 0; i < pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().size(); i ++) {
            pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(i).click();
        }
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
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        ValidationUtil.validateTestStep("Validating Bags Total Price displayed is 0.00 on Bags Page for Bundle It",
                pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText(),"$0.00");
        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();
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
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}