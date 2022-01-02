package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.CardDetailsData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test Case ID: TC28651
//Description : FS Flight Only DOM RT outside 15 days. Validate prices on Dynamic Shoping Cart on Passenger Information,
//              Bags, Seats and Options page.  Validating prices on Payment, Confirmation, Reservation Summary, Itinerary
//              Receipt Page. Cancel journey and verify the refund information to the credit card.
// Created By : Un Fai Chan
//Created On  : 12/20/2019
//Reviewed By : Gabriela
//Reviewed On : 27/Dec/2019
//**********************************************************************************************

public class TC28651 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","MyTrips", "RoundTrip", "DomesticDomestic", "Flight", "WithIn21Days", "Adult", "FreeSpirit",
                    "Connecting", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "ReservationUI","PaymentUI",
                    "DynamicShoppingCartUI","ConfirmationUI","CancelReservationUI"})
    public void FS_Flight_Only_DOM_RT_outside_15_days_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Cancel_journey_and_verify_the_refund_information_to_the_credit_card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28651 under Web_Booking_Price_Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String FS_EMAIL           = "FSEmail";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "20";
        final String ARR_DATE           = "22";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String RET_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Cancellation Page constant values
        final int FIRST_INDEX 		    = 0;
        final String CANCELLATION_CONFIRMATION = "Your reservation has been cancelled and refunded to the original form of payment as shown below, and an email with details has been sent to:";


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

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        String totalPrice   = String.valueOf(scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE));
        totalPrice          = '$' + totalPrice;
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        String flightPricePassenger     = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalPassenger  = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Passenger Page: \n flightPricePassenger = "
                        + flightPricePassenger + "\n ItineraryTotalPassenger = " + ItineraryTotalPassenger,
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

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Bags Page: \n flightPriceBag = "
                        + flightPriceBag + "\n ItineraryTotalBag = " + ItineraryTotalBag,
                flightPriceBag.equals(ItineraryTotalBag) && totalPrice.equals(ItineraryTotalBag));

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        String flightPriceSeats         = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalSeats      = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Seats Page: \n flightPriceSeats = "
                        + flightPriceSeats + "\n ItineraryTotalSeats = " + ItineraryTotalSeats,
                flightPriceSeats.equals(ItineraryTotalSeats) && totalPrice.equals(ItineraryTotalSeats));

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        String flightPriceOptions       = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalOptions    = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Seats Page: \n flightPriceOptions = "
                        + flightPriceOptions + "\n ItineraryTotalOptions = " + ItineraryTotalOptions,
                flightPriceOptions.equals(ItineraryTotalOptions) && totalPrice.equals(ItineraryTotalOptions));

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getTotalDueFlightChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        String totalDuePayment      = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
        String flightPricePayment   = pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText();
        String retFlightDue         = pageObjectManager.getPaymentPage().getTotalRetFlightDueText().getText();
        String depFlightDue         = pageObjectManager.getPaymentPage().getTotalDepFlightDueText().getText();

        ValidationUtil.validateTestStep("Validating Total Flight Due = retFlightDue + depFlightDue:" +
                "\n flightPricePayment = " + flightPricePayment + "\n retFlightDue = " + retFlightDue + "\n depFlightDue = " + depFlightDue + "\n sum = " + Math.rint((priceToDouble(retFlightDue) + priceToDouble(depFlightDue))*100)/100,
                priceToDouble(flightPricePayment) == Math.rint((priceToDouble(retFlightDue) + priceToDouble(depFlightDue))*100)/100 );

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Payment Page: \n totalDuePayment = "
                        + totalDuePayment + "\n flightPricePayment = " + flightPricePayment,
                totalDuePayment.equals(flightPricePayment) && totalPrice.equals(flightPricePayment));

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getTotalPaidChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        String totalFlightConfirmation = pageObjectManager.getConfirmationPage().getPackagePricePaidlabel().getText();
        String totalPaidConfirmation = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Confirmation Page: \n totalDuePayment = "
                        + totalDuePayment + "\n flightPricePayment = " + flightPricePayment,
                totalFlightConfirmation.equals(totalPaidConfirmation) && totalPrice.equals(totalPaidConfirmation));

        //Logged into My Trips
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Cancelling Journey
        pageMethodManager.getReservationSummaryPageMethods().cancelItineraryButton();
        pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();

        //Storing refund values for next validation
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpCancelReservationButton().click();
        String refund1 = pageObjectManager.getCancelReservationPage().getCreditSummaryValesAmountText().getText().replace(",","");
        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(CARD_TYPE);

        double refund = Double.parseDouble(refund1.replace("$",""));
        ValidationUtil.validateTestStep("Validating refund price equals to the paid amount: " +
                "\n refund = " + refund + "\n totalDuePayment = " + totalDuePayment, totalDuePayment.equals(refund1));

        //verify booking has been cancelled
        ValidationUtil.validateTestStep("Verify cancel confirmation verbiage appear in Sub Header of Cancel reservation Page",
                pageObjectManager.getCancelReservationPage().getCancellationSubHeaderText().get(FIRST_INDEX).getText(),CANCELLATION_CONFIRMATION);

        //verify card name
        ValidationUtil.validateTestStep("Validating credit refunded to Credit Card",
                pageObjectManager.getCancelReservationPage().getCreditSummaryValuesTypeText().getText().equals("Credit Card"));

        ValidationUtil.validateTestStep("Verify Card Name in credit summary  of Cancel reservation Page",
                pageObjectManager.getCancelReservationPage().getCreditSummaryValuesValidThruText().getText().trim(),cardDetailsData.cardName);

        ValidationUtil.validateTestStep("Validating refund price:  $" + refund + " equals to the paid amount " + totalPaidConfirmation, totalPaidConfirmation,refund1);
    }

    private double priceToDouble(String price){
        String value = price.replace("$","");
        value = value.replace(",","");
        return Double.valueOf(value);
    }
}
