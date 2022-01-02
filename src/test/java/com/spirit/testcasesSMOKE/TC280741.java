package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//**********************************************************************************************
//TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
/** 10/21/19 Bug closed, removed active bug tag**/
//Test CaseID: TC280741
//Title      : 31404 366. E2E_FS_RT DOM 1 ADT 1 child +2 Miles Booking_SW Change 1 ADT 2 children +2 Bundle It [Tier 3]
//             Direct Flight_Emotional Support Animal_RT 1CO 1 CB both pax_1 free each_SB and FF_CI Web_Travel Guard_Mastercard
//Description: Validate user can complete booking by using parameters from the title
//Created By : Alex Rodriguez
//Created On : 2-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 5-May-2019
//**********************************************************************************************

public class TC280741 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "Miles" , "DomesticDomestic","Within21Days" ,"Outside21Days", "Adult" , "Child" ,
            "FreeSpirit" , "NonStop" , "BundleIt" , "NewFlightSearch" , "PassengerInfoSSR" , "CarryOn" ,
            "CheckedBags" , "BagsUI" , "Standard" , "FlightFlex" , "ShortCutBoarding" , "OptionalUI" ,
            "MasterCard" , "TravelInsuranceBlock" , "ActiveBug","DynamicShoppingCartUI"})
    public void E2E_FS_RT_DOM_1_ADT_1_child_2yo_Miles_Booking_SW_Change_1_ADT_2_children_2_yo_Bundle_It_Direct_Flight_Emotional_Support_Animal_RT_1CO_1_CB_both_pax_1_free_each_SB_and_FF_CI_Web_Travel_Guard_Mastercard (@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Bags Page*****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280741 under Smoke Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String EMAIL              = "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ATL";
        final String DEP_DATE           = "20";
        final String ARR_DATE           = "25";
        final String ADULTS             = "1";
        final String CHILDREN           = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String TRIP_TYPE2         = "RoundTrip";
        final String CHILDREN2          = "2";
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String UPGRADE_VALUE      = "BundleIt";

        //Passenger Page Constant Value
        final String ADDITIONAL_SSR     = "EmotionalAnimal";

        //Bags Page Constant
        final String BUNDLE_ITINERARY   = "Bundle It Discount";
        final String CHECKED_BAG    	= "Included";

        //Seats Page constant values
        final String DEPARTING_SEAT     = "Standard|Standard|Standard|";
        final String RETURNING_SEAT     = "Standard|Standard|Standard";

        //Payment Page Constant Values
        final String TRAVEL_GUARD       = "Required";
        final String CREDIT_CARD        = "MasterCard";

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
        //TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
        pageObjectManager.getHomePage().getMilesLabel().click();
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods

        /*Select new Flight Search Button*/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN2, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Ret", RET_FLIGHT);

        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(ADDITIONAL_SSR);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

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

        //Bags Page Methods
        for(WebElement checkedBag : pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText()){
            //verify checked is already included
            ValidationUtil.validateTestStep("Verify Checked Bag is included with Bundle It on Flight Availability Page",
                    checkedBag.getText(),CHECKED_BAG);
        }

        //continue with bag changes
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        //Selecting seats for flight
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEPARTING_SEAT);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RETURNING_SEAT);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify flight flex is selected
        ValidationUtil.validateTestStep("Verify Flight Flex is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));

        //verify Shortcut Boarding is selected
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of emotional support animal Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        //continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CREDIT_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("User confirms booking was completed successfully", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(STATUS));
    }
}