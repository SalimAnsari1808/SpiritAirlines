package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:25943]	Prod: GoldFinger R1: CP BP Dynamic shopping cart displaying second leg price wrong in Passenger info page for multicity booking int-dom
//Test Case ID: TC28710
//Description : 9FC Multicity DOM-INT. Validate no vacation upsell options are displayed on Home Page. Validate prices on
//              Dynamic Shopping Cart on Passenger Information, Bags, Seats and Options Page. Validate No Car or Hotel
//              Upsell on Options Page. Validate prices on Payment, Confirmation, Reservation Summary and Receipt Page.
//              Add seats and verify prices on Payment page
//Created By  : Manasa Tilakraj
//Created On  : Dec 23 2019
//Reviewed By : Gabriela
//Reviewed On : 2/Jan/2020
//**********************************************************************************************

public class TC28710 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "MultiCity", "DomesticInternational", "WithIn21Days", "Visa", "Adult", "NineDFC",
                    "NonStop","Connecting", "BookIt", "NoBags","Standard","CheckInOptions","OptionalUI","PaymentUI",
                    "ReservationUI","ConfirmationUI","ItineraryReceiptUI","AddEditSeats","ActiveBug"})
    public void Multicity_DOM_INT_Validate_no_vacation_optoins_are_on_Home_Page_Validate_prices_on_Dynamic_Shoipng_Cart_on_Passenger_Information_Bags__and_Options_Page_Validate_No_Car_or_Hotel_Upsell_on_Options_Page_Validate_prices_on_Payment_Confirmation_Reservation_Summary_and_Receipt_Page_Add_seats_and_verify_prices_on_Payment_page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28710 under Web booking Price Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL|FLL";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "GYE|GUA";
        final String DEP_DATE           = "2|6";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String RET_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //My Trip bags page
        final String TRAVEL_SEATS       = "Seats";
        final String MYTRIP_DEP_SEAT    = "Standard|Standard||Standard|Standard";
        final String BAGS_POP_UP        = "DontPurchase";

        //open browser
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

        ValidationUtil.validateTestStep("Validating vacation- Flight+Hotel || Flight+Car || Flight+Hotel+Car is not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightHotelLabel())
                        && !TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightCarRadioButton())
                        && !TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightHotelCarRadiobutton()));

        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(DEP_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

        //Storing Price information for next validations
        String Flighttotal1 = "";
        if (TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText()))
        {
            Flighttotal1 = pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText().getText();
        }else {
            Flighttotal1 = pageObjectManager.getFlightAvailabilityPage().getStandardFarePriceText().getText();
        }

        double Flighttotal = Double.parseDouble(Flighttotal1.replace("$",""));
        System.out.println("Flighttotal: " + Flighttotal);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Validate Flight price from the dropdown in passenger info page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Validate Shopping Cart is present
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        String TotalPriceIti1 = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace(",", "");
        double TotalPriceIti = Double.parseDouble(TotalPriceIti1.replace("$", ""));
        //TODO: [IN:25943]	Prod: GoldFinger R1: CP BP Dynamic shopping cart displaying second leg price wrong in Passenger info page for multicity booking int-dom
        ValidationUtil.validateTestStep("Validating prices matching on Passenger info page with flight total cost \n FLight cost:" + Flighttotal + "\n Cost on Passenger Itinerary:" + TotalPriceIti,
                TotalPriceIti == Flighttotal);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Validate Flight price from the dropdown in Bags page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti1 = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace(",", "");
        TotalPriceIti = Double.parseDouble(TotalPriceIti1.replace("$", ""));
        //TODO: [IN:25943]	Prod: GoldFinger R1: CP BP Dynamic shopping cart displaying second leg price wrong in Passenger info page for multicity booking int-dom
        ValidationUtil.validateTestStep("Validating prices matching on bags page with flight total \n FLight cost:" + Flighttotal + "\n Cost on bags page Itinerary:" + TotalPriceIti,
                TotalPriceIti == Flighttotal);

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        // Validate Flight price from the dropdown in seats page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
       TotalPriceIti1 = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace(",", "");
       TotalPriceIti = Double.parseDouble(TotalPriceIti1.replace("$", ""));
        //TODO: [IN:25943]	Prod: GoldFinger R1: CP BP Dynamic shopping cart displaying second leg price wrong in Passenger info page for multicity booking int-dom
        ValidationUtil.validateTestStep("Validating prices matching on seats page with Flight total cost \n FLight cost:" + Flighttotal + "\n Cost on seats page Itinerary:" + TotalPriceIti,
                TotalPriceIti == Flighttotal);

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Validate Flight price from the dropdown in options page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti1 = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace(",", "");
        TotalPriceIti = Double.parseDouble(TotalPriceIti1.replace("$", ""));
        //TODO: [IN:25943]	Prod: GoldFinger R1: CP BP Dynamic shopping cart displaying second leg price wrong in Passenger info page for multicity booking int-dom
        ValidationUtil.validateTestStep("Validating prices matching on options page with Flight total cost\n FLight cost:" + Flighttotal + "\n Cost on options page Itinerary:" + TotalPriceIti,
                TotalPriceIti == Flighttotal);

        //Validate upsell is not displayed
        ValidationUtil.validateTestStep("Validating Car and Hotel upsell are not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getViewAllHotelsButton()) && !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getViewAllCarsButton()));

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

        ValidationUtil.validateTestStep("Validating prices matching on Confirmation page \n Total Due(From Payment Page):" + TotalPrice + "\n Total Paid(From confirmation Page):" + TotalPricePaid1,
                TotalPricePaid == TotalPrice);

        //Logging into My Trips Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate prices on Receipt page
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        String ITotalPricePaid1 = pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptTotal().getText().replace(",", "");
        double ITotalPricePaid = Double.parseDouble(ITotalPricePaid1.replace("$", ""));
        ValidationUtil.validateTestStep("Validating prices matching on Itinerary page \nTotal Due(From Payment Page):" + TotalPrice + "\n Total Due(From Itinerary Page):" + ITotalPricePaid,
                TotalPrice == ITotalPricePaid);
        WaitUtil.untilTimeCompleted(1000);

        //Logging back to My Trips page
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(TRAVEL_SEATS);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(MYTRIP_DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(MYTRIP_DEP_SEAT);

        String TotalSeatsPrice1 = pageObjectManager.getSeatsPage().getSeatsTotalPriceText().getText().replace(",", "");
        double TotalSeatsPrice = Double.parseDouble(TotalSeatsPrice1.replace("$", ""));

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Bags pop up, No purchase
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(BAGS_POP_UP);

        //Options Page Methods
        if (TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getContinueButtonOnCheckInPathButton()))
        {
            pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        }

        //Payment page Methods
        String TotalDue1 = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace(",", "");
        double TotalDue = Double.parseDouble(TotalDue1.replace("$", ""));

        ValidationUtil.validateTestStep("Validating seats prices matching on Payment page \n Total Due:" + TotalDue + "\n Total Due Seats Price:" + TotalSeatsPrice,
                TotalDue == TotalSeatsPrice);

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
    }
}
