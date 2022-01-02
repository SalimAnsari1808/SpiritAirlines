package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.*;


//**********************************************************************************************
//Test Case ID: TC373158
//Test Name: 471. E2E_9DFC_OW INT 1 ADT 2 CHILD Miles_ConnectingFlight_Other_ThrillBags_Included seats_All CI agent_Visa credit card
//Description: Validating Miles booking can add bundle It discounts and pay with
// credit card
//Created By : Alex Rodriguez
//Created On : 10-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-May-2019
//**********************************************************************************************

public class TC373158 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" ,"Miles", "InternationalDomestic" , "Outside21Days" , "Adult" , "InfantLap" ,"InfantSeat", "NineDFC" , "NonStop" , "BundleIt" ,
            "NewFlightSearch","DynamicShoppingCartUI","PassengerInfoSSR" , "CarryOn" , "CheckedBags","BagsUI" , "Standard" ,"FlightFlex" , "ShortCutBoarding","ShortCutSecurity",
            "OptionalUI" , "Visa" ,"PaymentUI", "ConfirmationUI"})
    public void E2E_9DFC_OW_INT_1_ADT_2_CHILD_Miles_ConnectingFlight_Other_ThrillBags_Included_seats_All_CI_agent_Visa_credit_card(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373158 under Smoke Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String EMAIL              = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CUN";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ATL";
        final String DEP_DATE           = "55";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String TRIP_TYPE2         = "OneWay";
        final String CHILDREN2          = "0";
        final String INFANT_LAP2        = "1";
        final String INFANT_SEAT2       = "1";
        final String DEP_DATE2          = "56";
        final String ARR_DATE2          = "NA";
        final String DEP_FLIGHT         = "Connecting";
        final String UPGRADE_VALUE      = "BundleIt";

        //Passenger Page Constant
        final String BUNDLE_ITINERARY   = "Bundle It Discount";

        //Seats Page constant values
        final String DEPARTING_SEAT     = "Standard|Standard||Standard|Standard";

        //Option Page constant value
        final String OPTIONS_VALUE      = "ShortCutSecurity,NotRequired";

        //Payment Page Constant Values
        final String SELECTED_OPTION    = "FlightFlex|ShortCutBoarding";
        final String TRAVEL_GUARD       = "NotRequired";
        final String CREDIT_CARD        = "VisaCard";

        //Confirmation page Constant values
        final String STATUS             = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        //Select Miles pill switch
        pageObjectManager.getHomePage().getMilesLabel().click();
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods

        /*Select new Flight Search Button*/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE2, ARR_DATE2);
        //Select Miles pill switch
        WaitUtil.untilPageLoadComplete(getDriver());

        //pageObjectManager.getHomePage().getMilesLabel().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN2, INFANT_SEAT2, INFANT_LAP2);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep", DEP_FLIGHT);
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        //open dynamic shopping cart
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(0).click();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger("Other");
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //continue without bag changes
        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();

        //Seats Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEPARTING_SEAT);

        //verify for continue without seat
        ValidationUtil.validateTestStep("Verify 'Continue Without Seat' is not displayed on Seat Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton()));

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Option page
        WaitUtil.untilPageLoadComplete(getDriver());
        //pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);

        //Validate that the Flight Flex has been selected
        WaitUtil.untilTimeCompleted(4000);
        ValidationUtil.validateTestStep("Flight Flex is selected on Optional Page",
                pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel().isDisplayed());

        //Validate that the ShortCut Security has been selected
//        ValidationUtil.validateTestStep("ShortCut Security is selected on Options Page",
//                pageObjectManager.getOptionsPage().getShortCutSecurityCardSelectedLabel().isDisplayed());

        //verify Shortcut Boarding is selected
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of Lap Child on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        //continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCVVDetailsModifyPath(CREDIT_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
        ValidationUtil.validateTestStep("User confirms booking was completed successfully",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),STATUS);
    }
}