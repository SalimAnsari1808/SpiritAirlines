package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281135
//Description: 178. E2E_Guest_RT DOM 1 ADT 1 Child 5-15_SW Change Airport, Bundle Fare, Thru Flight_PAX1 Hearing Disabled_Add Bags_ Button_BFS Seats_No Extras, CI Web_Voucher, Reservation Credit,Credit Card
//Created By : Anthony Cardona
//Created On : 31-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 03-June-2019
//Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web  return null names and are then invalid for use
//**********************************************************************************************
public class TC281135 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Child" , "Guest" ,
                    "NewFlightSearch" , "Through" , "BundleIt" , "PassengerInfoSSR" , "CarryOn" , "CheckedBags" ,
                    "DynamicShoppingCartUI" , "Bikes" , "SurfBoard" , "BigFrontSeat" , "Visa" , "Voucher" , "ReservationCredit" ,
                    "TravelInsuranceBlock" , "OptionalUI"})
    public void E2E_Guest_RT_DOM_1_ADT_1_Child_5to15_SW_Change_Airport_Bundle_Fare_Thru_Flight_PAX1_Hearing_Disabled_Add_Bags_Button_BFS_Seats_No_Extras_CI_Web_Voucher_Reservation_Credit_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281135 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "DFW";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "10";
        final String ADULTS                 = "1";
        final String CHILD                  = "1";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        //Flight Availability Page Constant Values
        final String NEW_DEP_AIRPORT_CODE   = "ATL";
        final String NEW_ARR_AIRPORT_CODE   = "FLL";
        final String NEW_DEP_DATE           = "5";
        final String NEW_ARR_DATE           = "10";

        final String DEP_FLIGHT             = "Through";
        final String RET_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BundleIt";
        //Passenger Page Constant Value
        final String ADDITIONAL_SSR         = "HearingImpaired";
        //Bags Page Constant Values

        final String BUNDLE_ITINERARY       = "Bundle It Discount";
        //Seats Page Constant Values
        final String SEATS_DEP              = "BigFront|BigFront";
        final String SEATS_RET              = "BigFront|BigFront";
        //Options Page Constant Values
        final String OPTIONS_VALUE          = "CheckInOption:DecideLater";
        //Payment Page Constant Value
        final String CARD_TYPE              = "VisaCard";
        final String TRAVEL_GUARD           = "Required";
        //Confirmation Page Constant Values
        final String BOOKING_STATUS         = "Confirmed";
        //open browser
        openBrowser(platform);

        pageMethodManager.getHomePageMethods().launchSpiritApp();
        createVoucher();
        createResCredit();

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
        pageMethodManager.getHomePageMethods().selectDates(NEW_DEP_DATE, NEW_ARR_DATE);
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
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        ValidationUtil.validateTestStep("Validating Bags Total Price displayed is 0.00 on Bags Page for Bundle It",
                pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText(),"$0.00");

        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(0).click(); //click on sporting equipment drop down
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(0).click(); //click on bicycle plus button
        pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(0).click(); //click on bicycle plus button
        pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(0).click(); //click on bicycle plus button
        pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(0).click(); //click on bicycle plus button
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
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();
        pageMethodManager.getPaymentPageMethods().applyVoucherNumber();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
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
        final String TRAVEL_GUARD       = "Notrequired";


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
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilPageLoadComplete(getDriver());
    }


    private void createResCredit() {

        //Booking Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "Notrequired";

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
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}