package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test Case ID: TC28655
//Description : FS Flight Only INT-DOM RT within 24 hours. Validate prices on Dynamic Shoping Cart on Passenger Information,
//              Bags, Seats and Options page.  Validating prices on Payment, Confirmation, Reservation Summary, Itinerary
//              Receipt Page. Check-In. Verify Boarding Pass Information
// Created By : Un Fai Chan
//Created On  : 12/17/2019
//Reviewed By : Gabriela
//Reviewed On : 30/Dec/2019
//**********************************************************************************************

public class TC28655 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","CheckIn", "OneWay", "InternationalDomestic", "WithIn7Days", "Adult", "FreeSpirit","NonStop",
                    "BookIt", "NoBags", "NoSeats","DynamicShoppingCartUI", "CheckInOptions", "Visa", "PaymentUI",
                    "ReservationUI" , "BoardingPassUI"})
    public void Flight_Only_INT_DOM_RT_within_24_hours_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Check_In_Verify_Boarding_Pass_Information(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28655 under Web_Booking_Price_Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String FS_EMAIL           = "FSEmail";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "PTY";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "NA";
        final String ADULTS 			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Nonstop";
        final String FARE_TYPE 			= "Standard";
        final String UPGRADE_VALUE 		= "BookIt";

        //Payment Page Constant Values
        final String CARD_TYPE         = "VisaCard";
        final String TRAVEL_GUARD      = "NotRequired";

        //Options Page constant values
        final String OPTION_VALUE 		= "CheckInOption:MobileFree";

        //Online Check In Page Constant Values
        final String MYTRIP_BAGS_POPUP      = "DontPurchase";
        final String MYTRIP_BUY_SEAT_POPUP  = "DontPurchase";
        final String MYTRIP_HAZMAT_POPUP    = "Accept";
        final String MYTRIP_BOARDING_POPUP  = "Print";
        final String MYTRIP_BOARDING_URL    = "boarding-pass";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(FS_EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        String totalPrice = String.valueOf(scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE));
        totalPrice = '$' + totalPrice;
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        String flightPricePassenger     = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalPassenger  = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Passenger Page",
                flightPricePassenger.equals(ItineraryTotalPassenger) && totalPrice.equals(ItineraryTotalPassenger));

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        String flightPriceBag       = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalBag    = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Bags Page",
                flightPriceBag.equals(ItineraryTotalBag) && totalPrice.equals(ItineraryTotalBag));

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();


        //Seats Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        String flightPriceSeats     = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalSeats  = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Seats Page",
                flightPriceSeats.equals(ItineraryTotalSeats) && totalPrice.equals(ItineraryTotalSeats));

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        String flightPriceOptions       = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalOptions    = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Options Page",
                flightPriceOptions.equals(ItineraryTotalOptions) && totalPrice.equals(ItineraryTotalOptions));
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        String totalDuePayment      = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
        String flightPricePayment   = pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Total Due Amount matches on Payment Page",
                totalDuePayment.equals(flightPricePayment) && totalPrice.equals(flightPricePayment));

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Storing price values for next validations
        pageObjectManager.getReservationSummaryPage().getTotalPaidChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        String totalFlightConfirmation  = pageObjectManager.getConfirmationPage().getPackagePricePaidlabel().getText();
        String totalPaidConfirmation    = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Total Paid Amount matches on Confirmation Page",
                totalFlightConfirmation.equals(totalPaidConfirmation) && totalPrice.equals(totalPaidConfirmation));

        //Logged into Online Check In Page
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Itinerary Page Methods
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        String totalFlightReservation   = pageObjectManager.getConfirmationPage().getPackagePricePaidlabel().getText();
        String totalDueReservation      = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Total Paid Amount matches on Itinerary Page",
                totalFlightReservation.equals(totalDueReservation) && totalPrice.equals(totalDueReservation));

        //Going back to Online Check In page
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Checking in
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //Passport Page Methods
        pageMethodManager.getPassportPageMethods().fillPassportInformation();

        //Select no to Bags upsell
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(MYTRIP_BAGS_POPUP);

        //select seat on popup
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(MYTRIP_BUY_SEAT_POPUP);

        //continue on Extras page if any extra available
        WaitUtil.untilPageLoadComplete(getDriver());
        if (TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getContinueButtonOnCheckInPathButton())){
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();}

        //TG pop up
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Accept and print boarding pass
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(MYTRIP_HAZMAT_POPUP);

        //print boarding pass
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(MYTRIP_BOARDING_POPUP,"NA");

        //validate user taken to the boarding pass page
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);
        TestUtil.closeBoardingPassPrintPopup();

        ValidationUtil.validateTestStep("user redirected to the Boarding pass page",
                getDriver().getCurrentUrl(),MYTRIP_BOARDING_URL);
    }
}
