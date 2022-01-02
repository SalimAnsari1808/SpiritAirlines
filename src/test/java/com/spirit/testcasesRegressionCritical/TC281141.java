package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281141
//Description: 31587 183. E2E_Guest_RT DOM 1 ADT 2 Children_SW Change PAX 1 ADT 2 INFT (1Lap, 1 Seat), Bundle It [Tier 3] Fare, Direct Flight_Jr-Sr_1 Carry On 5 Checked for all PAX_Bundle It [Tier 3] Seats_No Extras, CI Web_AMEX
//Created By : Anthony Cardona
//Created On : 29-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 31-May-2019
//**********************************************************************************************

public class TC281141 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "InfantSeat" , "InfantLap"
            ,       "Guest" , "NewFlightSearch" , "Connecting" , "BundleIt" , "MandatoryFields" , "CarryOn" , "CheckedBags" ,
                    "DynamicShoppingCartUI" , "Standard" , "ShortCutBoarding" , "ShortCutSecurity" , "AmericanExpress" ,
                    "TravelInsuranceBlock" , "OptionalUI","Military" })
    public void E2E_Guest_RT_DOM_1_ADT_2_Children_SW_Change_PAX_1_ADT_2_INFT_1Lap_1_Seat_Bundle_It_Tier_3_fare_Direct_Flight_Jr_Sr_1_Carry_On_5_Checked_for_all_PAX_Bundle_It_Tier_3_Seats_No_Extras_CI_Web_AMEX(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281141 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Roundtrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "10";
        final String ADULTS             = "1";
        final String CHILD              = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        //Flight Availability Page Constant Values

        final String NEW_CHILD          = "0";
        final String NEW_INFANT_LAP     = "1";
        final String NEW_INFANT_SEAT    = "1";

        final String DEP_FLIGHT         = "Connecting";
        final String RET_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Bags Page Constant Values
        final String BUNDLE_ITINERARY   = "Bundle It Discount";

        //Seats Page Constant Values
        final String SEATS_DEP          = "Standard|Standard||Standard|Standard";
        final String SEATS_RET          = "Standard|Standard||Standard|Standard";

        //Options Page Constant Values
        final String OPTION_VALUE       = "ShortCutSecurity,ShortCutSecurity";

        //Payment Page Constant Value
        final String CARD_TYPE          = "AmericanExpressCard";
        final String TRAVEL_GAURD       = "NotRequired";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, NEW_CHILD, NEW_INFANT_SEAT, NEW_INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        //Create two passengers with same name, one with "Sr" and the other with "Jr"
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0),passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getAdultSuffixListDropDown().get(0),"SR");

        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(1),passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(1).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(1).sendKeys(passengerInfoData.lastName);
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getAdultSuffixListDropDown().get(1),"JR");

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
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
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        //verify Flight Flex is selected with carry on
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of Lap Infant on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GAURD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}