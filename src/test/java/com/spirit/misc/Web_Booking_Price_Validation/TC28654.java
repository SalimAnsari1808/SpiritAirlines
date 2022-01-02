package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//TODO: [IN:25968]	Goldfinger R1: MT:  FS member is receiving a NEGATIVE balance instead of a reservation credit after modifying the booking on My Trips path for a cheaper option and paying an upgrade with part of it
//Test Case ID: TC28654
//Description : FS Flight Only DOM-INT OW. Validate prices on Dynamic Shoping Cart on Passenger Information, Bags, Seats
//              and Options page. Validating prices on Payment, Confirmation, Reservation Summary, Itinerary Receipt Page.
//              Change journey for a cheaper one and add bags. Verify information on Payment Reservation Summary and Receipt Page
// Created By : Un Fai Chan
//Created On  : 12/18/2019
//Reviewed By : Gabriela
//Reviewed On : 30/Dec/2019
//**********************************************************************************************

public class TC28654 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","MyTrips", "OneWay", "DomesticInternational", "Flight", "WithIn21Days", "Adult", "FreeSpirit",
                    "Connecting", "BookIt", "CarryOn", "NoSeats","DynamicShoppingCartUI", "CheckInOptions","PaymentUI",
                    "Visa", "ReservationUI" , "ItineraryReceiptUI","ActiveBug"})
    public void FS_DOM_INT_OW_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Change_journey_for_a_cheaper_one_and_add_bags_Verify_information_on_Payment_Reservation_Summary_and_Receipt_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28654 under Web_Booking_Price_Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String FS_EMAIL           = "FSEmail";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "GUA";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";
        final String CREDIT_TYPE        = "Reservation Credit";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Bag Page constant values
        final String CHECKIN_BAG_PURCHASE = "Purchase";
        final String BAGS_COUNT         = "Carry_1|Checked_0";

        //Seats Page constant values
        final String CHECKIN_SEAT_PURCHASE = "DontPurchase";

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

        String flightPricePassenger = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalPassenger = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Passenger Page",
                flightPricePassenger.equals(ItineraryTotalPassenger) && totalPrice.equals(ItineraryTotalPassenger));

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        String flightPriceBag = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalBag = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Bags Page",
                flightPriceBag.equals(ItineraryTotalBag) && totalPrice.equals(ItineraryTotalBag));

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        String flightPriceSeats = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalSeats = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Seats Page",
                flightPriceSeats.equals(ItineraryTotalSeats) && totalPrice.equals(ItineraryTotalSeats));
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        String flightPriceOptions = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalOptions = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Options Page",
                flightPriceOptions.equals(ItineraryTotalOptions) && totalPrice.equals(ItineraryTotalOptions));

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        String totalDuePayment = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
        String flightPricePayment = pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Total Due Amount matches on Payment Page",
                totalDuePayment.equals(flightPricePayment) && totalPrice.equals(flightPricePayment));

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Confirmation page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getTotalPaidChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        String totalFlightConfirmation = pageObjectManager.getConfirmationPage().getPackagePricePaidlabel().getText();
        String totalPaidConfirmation = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();

        ValidationUtil.validateTestStep("Validating Flight price and Total Paid Amount matches on Confirmation Page",
                totalFlightConfirmation.equals(totalPaidConfirmation) && totalPrice.equals(totalPaidConfirmation));

        //Storing Booking information
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Logged into My Trips
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Selecting Change Flight link on Summary Page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        //Selecting Change Flight link on Change Flight pop up
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep", "NA", "NA", "21");
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

        //Selecting a cheap journey on Flight Availability page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("dep", "Standard", "Cheap");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //Adding Bags, Yes
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(CHECKIN_BAG_PURCHASE);
        pageMethodManager.getBagsPageMethods().selectDepartingBags(BAGS_COUNT);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Adding Seats, No
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(CHECKIN_SEAT_PURCHASE);

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page verification on My Trips Path
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);

        //Total Due calculation
        List<WebElement> listTotalDue = pageObjectManager.getPaymentPage().getTotalDuePriceTextList();
        double actualTotalDue = priceToDouble(pageObjectManager.getPaymentPage().getTotalDuePriceText().getText());
        System.out.println("actualTotalDue: " +actualTotalDue);
        double expectedTotalDue = 0.0;
        for (WebElement wb : listTotalDue) {
            expectedTotalDue += priceToDouble(wb.getText());
        }

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Confirmation Page: \n actualTotalDue = "
                        + actualTotalDue + "\n expectedTotalDue = " + (Math.rint(expectedTotalDue * 100) / 100),
                actualTotalDue == (Math.rint(expectedTotalDue * 100) / 100));

        //Validating ResCredit section
        JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getPaymentPage().getReservationCreditBlockTypeText());
        ValidationUtil.validateTestStep("Verify amount is added into Reservation Credit on Payment Page",
                pageObjectManager.getPaymentPage().getReservationCreditBlockTypeText().getText().trim(), CREDIT_TYPE);

        //verify pnr
        ValidationUtil.validateTestStep("Verify the PNR value for Reservation Credit on Payment Page",
                pageObjectManager.getPaymentPage().getReservationCreditBlockPNRText().getText().trim(), scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        //Saving Reservation Credit amount for next validation
        String resCredit1 = pageObjectManager.getPaymentPage().getReservationCreditBlockPriceText().getText();
        double resCredit = Double.parseDouble(pageObjectManager.getPaymentPage().getReservationCreditBlockPriceText().getText().replace("$",""));

        //Completing payment
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        WaitUtil.untilPageLoadComplete(getDriver());

        String totalPaidConfirmation2 = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();

        ValidationUtil.validateTestStep("Validating Total Amount matches on Confirmation Page: \n totalPaidConfirmation2 = "
                        + totalPaidConfirmation2 + "\n actualTotalDue = " + actualTotalDue,
                priceToDouble(totalPaidConfirmation2) == actualTotalDue);

        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();

        String finalTotalPaid = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();

        ValidationUtil.validateTestStep("Validating the last paid amount == the two previous paid combined: \n finalTotalPaid = "
                        + finalTotalPaid + "\n actualTotalDue = " + actualTotalDue + "\n totalPrice = " + totalPrice,
                priceToDouble(finalTotalPaid) == (Math.rint((actualTotalDue + priceToDouble(totalPrice)) * 100) / 100));

        // Apply reservation credit
        if (actualTotalDue < 0) {
            scenarioContext.setContext(Context.RESERVATION_CREDIT_AMOUNT, "$" + (-actualTotalDue));
            scenarioContext.setContext(Context.RESERVATION_CREDIT_CODE, scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

            //Home Page Methods
            pageMethodManager.getHomePageMethods().launchSpiritApp();
            pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
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
            pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

            //Passenger Info Methods
            pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
            pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
            pageMethodManager.getPassengerInfoMethods().clickContinueButton();

            //Bags Info Methods
            pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

            //Seats Info Methods
            pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

            //Options Page Methods
            pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
            pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

            //Payment Page Methods
            WaitUtil.untilPageLoadComplete(getDriver());
            //TODO: [IN:25968]	Goldfinger R1: MT:  FS member is receiving a NEGATIVE balance instead of a reservation credit after modifying the booking on My Trips path for a cheaper option and paying an upgrade with part of it
            pageMethodManager.getPaymentPageMethods().applyReservationCredit();

            WaitUtil.untilTimeCompleted(1200);
            pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
            WaitUtil.untilTimeCompleted(1200);

            ValidationUtil.validateTestStep("Validating the right reservation credit amount was applied",
                    pageObjectManager.getPaymentPage().getTotalDueVouchersAndCreditFieldListText().get(0).getText(), resCredit1);

            pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
            pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

            //Confirmation Page Methods
            WaitUtil.untilTimeCompleted(2000);
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

            //Payment Page Methods
            WaitUtil.untilPageLoadComplete(getDriver());
            pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
            WaitUtil.untilTimeCompleted(1000);

            String totalDuePayment1 = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
            String flightPricePayment1 = pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText();

            ValidationUtil.validateTestStep("Validating Flight price and Total Due Amount matches on Payment Page",
                    totalDuePayment1.equals(flightPricePayment1) && totalPrice.equals(flightPricePayment1));

            pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
            pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

            ValidationUtil.validateTestStep("Validating new prices added into the booking and Total Due Amount matches on Payment Page",
                    actualTotalDue == expectedTotalDue);

            String totalFlightReservation = pageObjectManager.getConfirmationPage().getPackagePricePaidlabel().getText();
            String totalDueReservation = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();

            ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Confirmation Page",
                    totalFlightReservation.equals(totalDueReservation) && totalPrice.equals(totalDueReservation));
        }
    }
    private double priceToDouble(String price){
        String value = price.replace("$","");
        return Double.valueOf(value);
    }
}
