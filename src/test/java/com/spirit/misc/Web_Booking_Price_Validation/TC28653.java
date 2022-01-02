package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test Case ID: TC28653
//Description : 9FC Flight Only DOM RT within 24 hours. Validate prices on Dynamic Shopping Cart on Passenger Information,
//              Bags, Seats and Options page.  Validating prices on Payment, Confirmation, Reservation Summary, Itinerary
//              Receipt Page. Check-In
//Created By  : Manasa Tilakraj
//Created On  : Dec 18 2019
//Reviewed By : Gabriela
//Reviewed On : 2/Jan/2020
//**********************************************************************************************
public class TC28653 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","CheckIn", "RoundTrip", "DomesticDomestic","WithIn7Days", "Adult", "NineDFC","NonStop",
                    "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa","DynamicShoppingCartUI","PaymentUI",
                    "ConfirmationUI","ItineraryReceiptUI"})
    public void Flight_Only_DOM_RT_within_24_hours_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Check_In(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28653 under Web booking Price Validation Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
    final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "DFW";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "2";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        // Flight details constant values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Online Check-In Page Constant Values
        final String SEATS_POP_UP       = "DontPurchase";
        final String BAGS_POP_UP        = "DontPurchase";
        final String HAZARD_POP_UP      = "Accept";
        final String PRINT_BP           = "Print";
        final String EMAIL_BP           = "NoEmail";
        final String BOARDING_PASS_URL  = "/check-in/boarding-pass";


        //open Spirit home page on the browser
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Log in as a 9DFC member
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

        String total = "";
        if (TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText()))
        {
             total = pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText().getText();
        }else {
             total = pageObjectManager.getFlightAvailabilityPage().getStandardFarePriceText().getText();
        }
        System.out.println("total: " + total);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Validate Flight price from the dropdown in passenger info page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Click on the Dynamic Shopping Cart's dropdown arrow
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        String TotalPriceIti = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        System.out.println(TotalPriceIti);

        ValidationUtil.validateTestStep("Validating prices matching on Passenger info page", TotalPriceIti.equals(total));
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Validate Flight price from the dropdown in Bags page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        System.out.println(TotalPriceIti);

        ValidationUtil.validateTestStep("Validating prices matching on bags page", TotalPriceIti.equals(total));

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Validate Flight price from the dropdown in seats page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        System.out.println(TotalPriceIti);

        ValidationUtil.validateTestStep("Validating prices matching on seats page", TotalPriceIti.equals(total));

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Validate Flight price from the dropdown in options page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating prices matching on options page", TotalPriceIti.equals(total));

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Validate prices on Payment page and complete booking
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        String TotalPrice = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
        String TotalFlightPrice = pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText();

        ValidationUtil.validateTestStep("Validating prices matching on Payment page", TotalPrice.equals(TotalFlightPrice));

        //Payment page Methods
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().fillCVVDetailsModifyPath(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD); // no Tg option within 24 hours on booking path

        //Validate prices on confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        String TotalPricePaid1 = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();
        ValidationUtil.validateTestStep("Validating prices matching on Confirmation page", TotalPricePaid1.equals(TotalPrice));

        //Logging into My Trips Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Receipt Page
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        WaitUtil.untilTimeCompleted(1200);

        String TotalPricePaid2 = pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptTotal().getText();
        ValidationUtil.validateTestStep("Validating prices matching on Itinerary page", TotalPricePaid1.equals(TotalPricePaid2));

        //Check-in
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).click();

       //No purchase on Bags pop up
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(BAGS_POP_UP);

        //No purchase on Seats pop up
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(SEATS_POP_UP);

        //No Purchase on Cars pop up
        WaitUtil.untilPageLoadComplete(getDriver());
        if (pageObjectManager.getReservationSummaryPage().getRentACarSubHeaderText().isDisplayed())
        {
            pageObjectManager.getReservationSummaryPage().getRentACarContinueButton().click();
        }

        //Continue on Options Page
        WaitUtil.untilPageLoadComplete(getDriver());
//        if (TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getContinueButtonOnCheckInPathButton()))
//        {
            //shortcut security and shortcut boarding should be available to select for these city pairs
            pageObjectManager.getOptionsPage().getContinueButtonOnCheckInPathButton().click();
//        }

        //No purchase on Travel Guard pop up
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Accept on Hazard pop up
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(HAZARD_POP_UP);
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(PRINT_BP,EMAIL_BP);

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user reached on Boarding Pass Page",
                getDriver().getCurrentUrl(),BOARDING_PASS_URL);
    }
}