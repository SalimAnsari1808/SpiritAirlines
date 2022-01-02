package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Check2 (TFS: 31401  | 31622 )
//TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
//TODO Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web return null names and are then invalid for use
//Test Case ID: TC355017
//Test Name:  E2E_FS_RT DOM 1 ADT 2 children Miles Booking_SW Change PAX to 2 INFT (lap and seat) Bundle It [Tier 3] Fare Connecting Flight_STD_RT 1CO 1CB_1 Free Seat_No Extras_CI Web_Reservation Credit_Voucher_Credit Card
//Description:
//Created By : Anthony Cardona
//Created On : 09-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 09-MAY-2019
//**********************************************************************************************

public class TC280784 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "Miles" , "DomesticDomestic" , "Outside21Days" , "Adult" ,
            "InfantSeat" , "InfantLap" ,"FreeSpirit" , "Connecting" , "NewFlightSearch" , "BundleIt" ,
            "DynamicShoppingCartUI" , "CarryOn" , "CheckedBags" , "BagsUI" , "Standard" , "SeatsUI" ,
            "FlightFlex" , "ShortCutBoarding" , "OptionalUI" , "ReservationCredit" , "Voucher" ,"Visa" , "ActiveBug"})
    public void E2E_FS_RT_DOM_1_ADT_2_children_Miles_Booking_SW_Change_PAX_to_2_INFT_lap_and_seat_Bundle_It_Tier_3_Fare_Connecting_Flight_STD_RT_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Reservation_Credit_Voucher_Credit_Card(@Optional("NA") String platform) {
        //*******************************************************************
        //****************Navigate to Confirmation Page**********************
        //*******************************************************************
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280784 under SMOKE Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Roundtrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "DEN";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "30";
        final String ARR_DATE           = "40";
        final String ADULTS             = "1";
        final String CHILDREN           = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String NEW_ADULTS			= "1";
        final String NEW_CHILDREN		= "0";
        final String NEW_INFANT_LAP		= "1";
        final String NEW_INFANT_SEAT	= "1";
        final String DEP_FLIGHT         = "Connecting";
        final String RET_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Bags Page Constant Values
        final String BUNDLE_ITINERARY   = "Bundle It Discount";
        final String BAGS_INCLUDED      = "Included";

        //Seats Page Constant Values
        final String DEPARTING_SEAT     = "Standard|Standard||Standard|Standard";
        final String RETURNING_SEAT     = "Standard|Standard||Standard|Standard";
        final String ZERO_SEAT_PRICE    = "$0.00";

        //payment page constant
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        //create voucher
        createVoucher();

        //create resCredit
        createResCredit();

        String creditPNR = scenarioContext.getContext(Context.CONFIRMATION_CODE).toString();

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        //Select Miles pill switch
        pageObjectManager.getHomePage().getMilesLabel().click();
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        //Select Miles pill switch
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getMilesLabel().click();

        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods

        /*Select new Flight Search Button*/
        //TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(NEW_ADULTS, NEW_CHILDREN, NEW_INFANT_SEAT, NEW_INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Ret", RET_FLIGHT);
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(0).click();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate Bags prices
        ValidationUtil.validateTestStep("Departing Carry-on bag is included with Bundle Fare on Bags Page" ,
                pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(0).getText() , BAGS_INCLUDED);
        ValidationUtil.validateTestStep("Departing checked bags price is included with Bundle Fare on Bags Page" ,
                pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(0).getText() , BAGS_INCLUDED);
        ValidationUtil.validateTestStep("Return Carry-on price is included with Bundle Fare on Bags Page" ,
                pageObjectManager.getBagsPage().getReturningCarryOnPriceText().get(0).getText() , BAGS_INCLUDED);
        ValidationUtil.validateTestStep("Return checked bags price is included with Bundle Fare on Bags Page" ,
                pageObjectManager.getBagsPage().getReturningCheckedBagPriceText().get(0).getText() , BAGS_INCLUDED);

        //open dynamic shopping cart
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart",
                scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText().replace("-",""));

        //continue with bags
        //pageObjectManager.getBagsPage().getContinueWithBagsButton().click();
        pageMethodManager.getBagsPageMethods().selectBagsFare("Standard");

        //Seats Page Methods
        //Selecting seats for flight
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEPARTING_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RETURNING_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Validating seats price is 0.00
        ValidationUtil.validateTestStep("Verifying Seats Price is $0.00",
                pageObjectManager.getSeatsPage().getSeatsTotalPriceText().getText(),ZERO_SEAT_PRICE);

        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        //verify flight flex is selected
        ValidationUtil.validateTestStep("Verify Flight Flex is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));

        //verify Shortcut Boarding is selected
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify CheckIn Option is disabled on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        // open total due amount
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
        //verify bundle discount text
        ValidationUtil.validateTestStep("User verify BUNDLE IT label is displayed on Payment page",
                pageObjectManager.getPaymentPage().getTotalDueBundleDiscountText().isDisplayed());
        ValidationUtil.validateTestStep("User verify BUNDLE IT SAVE Amount",
                scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(),pageObjectManager.getPaymentPage().getTotalDueBundleDiscountPriceText().getText().replace("-",""));

        //on payment page click on redeem voucher
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditLink().click();

        //apply reservation credit
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();

        //TODO Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web return null names and are then invalid for use
        //input Voucher
        pageMethodManager.getPaymentPageMethods().applyVoucherNumber();

        //Complete Booking
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    }

    private void createVoucher() {
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
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

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_CARD        = "Notrequired";


        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
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

        //My Trip
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();

    }


    private void createResCredit() {
        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_CARD        = "Notrequired";

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

        //My Trip
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();

    }
}
