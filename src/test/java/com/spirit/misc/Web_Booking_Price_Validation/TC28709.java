package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC28709
//Description : 9FC Multicity DOM within 24 hours. Validate no vacation upsell options are displayed on Home Page.
//              Validate prices on Dynamic Shopping Cart on Passenger Information, Bags, Seats and Options Page.
//              Validate No Car or Hotel Upsell on Options Page. Validate prices on Payment, Confirmation, Reservation
//              Summary and Receipt Page
//Created By  : Manasa Tilakraj
//Created On  : Dec 23 2019
//Reviewed By : Gabriela
//Reviewed On : 31/Dec/2019
//**********************************************************************************************
public class TC28709 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "MultiCity", "DomesticDomestic", "WithIn7Days", "VisaCard", "Adult", "NineDFC",
            "NonStop", "BookIt", "NoBags","NoSeats","CheckInOptions","OptionalUI","PaymentUI","ReservationUI",
            "ConfirmationUI","ItineraryReceiptUI"})
    public void  Multicity_DOM_within_24_hours_no_vacation_optoins_are_displayed_on_Home_Page_Validate_prices_on_Dynamic_Shoipng_Cart_on_Passenger_Information_Bags_Seats_and_Options_Page_Validate_No_Car_or_Hotel_Upsell_on_Options_Page_Validate_prices_on_Payment_Confirmation_Reservation_Summary_and_Receipt_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28709 under Web booking Price Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL|ORD";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "ATL|FLL";
        final String DEP_DATE           = "0|7";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String RET_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        double Flighttotal = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().replace("$",""));
        System.out.println("Total Flight Cost:" + Flighttotal);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Click on the dropdown arrow
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        double TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on Passenger info page with flight total cost \n FLight cost:" + Flighttotal + "\n Cost on Passenger Itinerary:" + TotalPriceIti,
                TotalPriceIti==Flighttotal);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on bags page with flight total \n FLight cost:" + Flighttotal +"\n Cost on bags page Itinerary:" + TotalPriceIti,
                TotalPriceIti==Flighttotal);

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on seats page with Flight total cost \n FLight cost:" + Flighttotal +"\n Cost on seats page Itinerary:" + TotalPriceIti, TotalPriceIti==Flighttotal);

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Validate Flight price from the dropdown in options page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on options page with Flight total cost\n FLight cost:" + Flighttotal +"\n Cost on options page Itinerary:" + TotalPriceIti,
                TotalPriceIti==Flighttotal);

        //Validate upsell is not displayed
        ValidationUtil.validateTestStep("Hotel and Car upsell is not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getViewAllHotelsButton()) && !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getViewAllCarsButton()));

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();


        //Validate prices on Payment page and complete booking
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);

        double TotalPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$",""));
        double TotalFlightPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace("$",""));

        ValidationUtil.validateTestStep("Validating prices matching on Payment page \n Total Due:" + TotalPrice +"\n Total Due Flight Price:" + TotalFlightPrice,
                TotalPrice==TotalFlightPrice);

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCVVDetailsModifyPath(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD); // No TG within 24 hours

        //Validate prices on confirmation page
        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        double TotalPricePaid1 = Double.parseDouble(pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on Confirmation page \n Total Due(From Payment Page):" + TotalPrice +"\n Total Paid:" + TotalPricePaid1,
                TotalPricePaid1==TotalPrice);


        //Validate prices on Itinerary Receipt
        //Online Check In
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Receipt Page Validation
        double TotalPricePaid2 = Double.parseDouble(pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptTotal().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on Itinerary page \nTotal Due(From Payment Page):" + TotalPrice +"\n Total Due(From Itinerary Page):" + TotalPricePaid2,
                TotalPrice==TotalPricePaid2);
        WaitUtil.untilTimeCompleted(1000);
    }
}
