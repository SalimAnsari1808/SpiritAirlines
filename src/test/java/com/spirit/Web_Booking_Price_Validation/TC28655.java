package com.spirit.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC28655
//Description: FS Flight Only INT-DOM RT within 24 hours. Validate prices on Dynamic Shoping Cart on Passenger Information, Bags, Seats and Options page.  Validating prices on Payment, Confirmation, Reservation Summary, Itinerary Receipt Page. Check-In. Verify Boarding Pass Information
// Created By: Un Fai Chan
//Created On: 12/17/2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC28655 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "OneWay", "DomesticDomestic", "Flight", "WithIn7Days", "Adult", "FreeSpirit",
            "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "ReservationUI" , "CheckIn"})
    public void TC28655(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28655 under Web_Booking_Price_Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String FS_EMAIL           = "FSEmail";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "GUA";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "1";
        final String ARR_DATE 			= "NA";
        final String ADULTS 			= "1";
        final String CHILDS 			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Nonstop";
        final String FARE_TYPE 			= "Standard";
        final String UPGRADEVALUE 		= "BookIt";

        //Payment Page Constant Values
        final String CARD_TYPE         = "VisaCard";
        final String TRAVEL_GUARD      = "NotRequired";

        //Options Page constant values
        final String OPTION_VALUE 		= "CheckInOption:MobileFree";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        String totalPrice = String.valueOf(scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE));
        totalPrice = '$' + totalPrice;
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
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
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        String flightPriceBag = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalBag = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Bags Page",
                flightPriceBag.equals(ItineraryTotalBag) && totalPrice.equals(ItineraryTotalBag));
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();


        //seats
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        String flightPriceSeats = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalSeats = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Seats Page",
                flightPriceSeats.equals(ItineraryTotalSeats) && totalPrice.equals(ItineraryTotalSeats));
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //options
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        String flightPriceOptions = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalOptions = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Seats Page",
                flightPriceOptions.equals(ItineraryTotalOptions) && totalPrice.equals(ItineraryTotalOptions));
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment page
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);
        String totalDuePayment = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
        String flightPricePayment= pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Payment Page",
                totalDuePayment.equals(flightPricePayment) && totalPrice.equals(flightPricePayment));

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);

        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        // confirmation page
        pageObjectManager.getReservationSummaryPage().getTotalPaidChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);
        String totalFlightConfirmation = pageObjectManager.getConfirmationPage().getPackagePricePaidlabel().getText();
        String totalPaidConfirmation = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Confirmation Page",
                totalFlightConfirmation.equals(totalPaidConfirmation) && totalPrice.equals(totalPaidConfirmation));

        //- Step 14: Record the PNR and last name.
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        pageMethodManager.getHomePageMethods().loginToMyTrip();

        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        String totalFlightReservation = pageObjectManager.getConfirmationPage().getPackagePricePaidlabel().getText();
        String totalDueReservation = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Confirmation Page",
                totalFlightReservation.equals(totalDueReservation) && totalPrice.equals(totalDueReservation));

    }
}
