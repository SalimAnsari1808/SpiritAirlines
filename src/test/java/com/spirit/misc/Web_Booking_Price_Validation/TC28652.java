package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC28652
//Description : 9FC Flight Only DOM OW outside 24 hours. Validate prices on Dynamic Shopping Cart on Passenger Information,
//              Bags, Seats and Options page.  Validating prices on Payment, Confirmation, Reservation Summary, Itinerary
//              Receipt Page. Change flight for an expensive one. Verify prices on Payment Reservation Summary and Receipt Page
//Created By  : Manasa Tilakraj
//Created On  : Dec 17 2019
//Reviewed By : Gabriela
//Reviewed On : 2/Jan/2019
//**********************************************************************************************

public class TC28652 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "MyTrips", "OneWay", "DomesticDomestic","Adult", "NineDFC", "WithIn7Days","NonStop",
                    "BookIt", "NoBags","NoSeats","CheckInOptions","Visa", "ChangeFlight", "DynamicShoppingCartUI",
                    "PaymentUI", "ConfirmationUI", "ReservationUI", "ItineraryReceiptUI"})
    public void  Flight_Only_DOM_OW_outside_24_hours_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_ummary_Itinerary_Receipt_Page_Change_flight_for_an_expensive_one_Verify_prices_on_Payment_Reservation_Summary_and_Receipt_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28652 under Web booking Price Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CLE";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "12";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        // Flight details constant values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Reservation Summary page constant values
        final String CHANGE_FLIGHT_POPUP = "Continue";
        final String MY_TRIPS_BAGS       = "DontPurchase";
        final String MY_TRIPS_SEAT       = "DontPurchase";

        //Open Spirit home page in the browser
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

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);

        String Flighttotal1 = "";
        if (TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText()))
        {
            Flighttotal1 = pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText().getText().replace(",","");
        }else {
            Flighttotal1 = pageObjectManager.getFlightAvailabilityPage().getStandardFarePriceText().getText().replace(",","");
        }

        double Flighttotal = Double.parseDouble(Flighttotal1.replace("$",""));
        System.out.println("Flighttotal: " + Flighttotal);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Validate Flight price from the dropdown in passenger info page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Validate Shopping Cart is present
        ValidationUtil.validateTestStep("Verify user Shopping Cart is Displayed on Passenger Info page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));

        //Click on the dropdown arrow
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //Validate Prices
        double TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$", ""));
        ValidationUtil.validateTestStep("Validating prices matching on Passenger info page with flight total cost \n FLight cost:" + Flighttotal + "\n Cost on Passenger Itinerary:" + TotalPriceIti,
                TotalPriceIti == Flighttotal);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Validate Flight price from the dropdown in Bags page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$", ""));
        ValidationUtil.validateTestStep("Validating prices matching on bags page with flight total \n FLight cost:" + Flighttotal + "\n Cost on bags page Itinerary:" + TotalPriceIti,
                TotalPriceIti == Flighttotal);

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate Flight price from the dropdown in seats page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$", ""));
        ValidationUtil.validateTestStep("Validating prices matching on seats page with Flight total cost \n FLight cost:" + Flighttotal + "\n Cost on seats page Itinerary:" + TotalPriceIti, TotalPriceIti == Flighttotal);

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        // Validate Flight price from the dropdown in options page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$", ""));
        ValidationUtil.validateTestStep("Validating prices matching on options page with Flight total cost\n FLight cost:" + Flighttotal + "\n Cost on options page Itinerary:" + TotalPriceIti,
                TotalPriceIti == Flighttotal);

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();


        //Validate prices on Payment page and complete booking
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);

        String TotalPrice1 = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace(",", "");
        double TotalPrice = Double.parseDouble(TotalPrice1.replace("$", ""));
        String TotalFlightPrice1 = pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace(",", "");
        double TotalFlightPrice = Double.parseDouble(TotalFlightPrice1.replace("$", ""));

        ValidationUtil.validateTestStep("Validating prices matching on Payment page \n Total Due:" + TotalPrice + "\n Total Due Flight Price:" + TotalFlightPrice,
                TotalPrice == TotalFlightPrice);

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Validate prices on confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        String TotalPricePaid1 = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace(",", "");
        double TotalPricePaid = Double.parseDouble(TotalPricePaid1.replace("$", ""));
        ValidationUtil.validateTestStep("Validating prices matching on Confirmation page \n Total Due(From Payment Page):" + TotalPrice + "\n Total Due(From confirmation Page):" + TotalPricePaid1,
                TotalPricePaid == TotalPrice);

        //Validate prices on Itinerary Receipt
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        String ITotalPricePaid1 = pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptTotal().getText().replace(",", "");
        double ITotalPricePaid = Double.parseDouble(ITotalPricePaid1.replace("$", ""));
        ValidationUtil.validateTestStep("Validating prices matching on Itinerary page \nTotal Due(From Payment Page):" + TotalPrice + "\n Total Due(From Itinerary Page):" + ITotalPricePaid,
                TotalPrice == ITotalPricePaid);

        //Select expensive flight
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep", "NA", "NA","2");
        pageMethodManager.getReservationSummaryPageMethods().continueCancelOnChangeFlightPopup(CHANGE_FLIGHT_POPUP);

        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("Dep", "9DFC", "Costly");
        WaitUtil.untilTimeCompleted(2000);
        String costlyFilght = pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText();
        System.out.println("New Flight Price is: " + costlyFilght);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        String IFlighttotal1 = pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().replace(",", "");
        double IFlighttotal = Double.parseDouble(IFlighttotal1.replace("$", ""));
        System.out.println("Extra Flight Cost:" + IFlighttotal);

        //No purchase on Bags Pop Up
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(MY_TRIPS_BAGS);
        //No purchase on Seats Pop Up
//        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(MY_TRIPS_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Continue to Payment from Options Page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //My Trips Payment Page Validations
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Validating right new flight price is being charged",
                pageObjectManager.getPaymentPage().getTotalDuePriceText().getText(),costlyFilght);

    }

}
