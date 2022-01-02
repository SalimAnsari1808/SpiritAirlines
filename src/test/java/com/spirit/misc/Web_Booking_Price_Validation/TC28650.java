package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC28650
//Description : FS Flight Only DOM OW within 24 hours. Validate prices on Dynamic Shoping Cart on Passenger Information,
//              Bags, Seats and Options page.  Validating prices on Payment, Confirmation, Reservation Summary, Itinerary
//              Receipt Page. Change flight for a cheaper one. Verify Res Credit or voucher information information on
//              Payment Page
// Created By : Un Fai Chan
//Created On  : 12/18/2019
//Reviewed By : Gabriela
//Reviewed On : 26/Dec/2019
//**********************************************************************************************

public class TC28650 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "OneWay", "DomesticDomestic", "Flight", "WithIn7Days", "Adult", "FreeSpirit",
                    "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa","DynamicShoppingCartUI",
                    "PaymentUI", "ConfirmationUI", "ReservationUI" , "ChangeFlight","ReservationCredit"})
    public void FS_Flight_Only_DOM_OW_within_24_hours_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Change_flight_for_a_cheaper_one_Verify_Res_Credit_or_voucher_information_information_on_Payment_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28650 under Web_Booking_Price_Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String FS_EMAIL           = "FSEmail";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "NA";
        final String ADULTS 			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE 			= "Standard";
        final String UPGRADE_VALUE 		= "BookIt";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";
        final String CREDIT_TYPE        = "Reservation Credit";

        //Options Page constant values
        final String OPTION_VALUE 		= "CheckInOption:MobileFree";

        //Bag Page constant values
        final String CHECKIN_BAG_PURCHASE = "DontPurchase";

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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        String totalPrice = String.valueOf(scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE));
        totalPrice = '$' + totalPrice;
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        String flightPricePassenger = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalPassenger = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Passenger Page: \n flightPricePassenger = "
                        + flightPricePassenger + "\n ItineraryTotalPassenger = " + ItineraryTotalPassenger,
                flightPricePassenger.equals(ItineraryTotalPassenger) && totalPrice.equals(ItineraryTotalPassenger));

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        String flightPriceBag = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalBag = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Bags Page: \n flightPriceBag = "
                        + flightPriceBag + "\n ItineraryTotalBag = " + ItineraryTotalBag,
                flightPriceBag.equals(ItineraryTotalBag) && totalPrice.equals(ItineraryTotalBag));
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        String flightPriceSeats = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalSeats = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Seats Page: \n flightPriceSeats = "
                        + flightPriceSeats + "\n ItineraryTotalSeats = " + ItineraryTotalSeats,
                flightPriceSeats.equals(ItineraryTotalSeats) && totalPrice.equals(ItineraryTotalSeats));
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        String flightPriceOptions = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalOptions = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Seats Page: \n flightPriceOptions = "
                        + flightPriceOptions + "\n ItineraryTotalOptions = " + ItineraryTotalOptions,
                flightPriceOptions.equals(ItineraryTotalOptions) && totalPrice.equals(ItineraryTotalOptions));
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);
        String totalDuePayment = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
        String flightPricePayment= pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Payment Page: \n totalDuePayment = "
                        + totalDuePayment + "\n flightPricePayment = " + flightPricePayment,
                totalDuePayment.equals(flightPricePayment) && totalPrice.equals(flightPricePayment));

        //Completing Payment
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Confirmation Page Methods
        pageObjectManager.getReservationSummaryPage().getTotalPaidChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);
        String totalFlightConfirmation = pageObjectManager.getConfirmationPage().getPackagePricePaidlabel().getText();
        String totalPaidConfirmation = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Confirmation Page: \n totalDuePayment = "
                        + totalDuePayment + "\n flightPricePayment = " + flightPricePayment,
                totalFlightConfirmation.equals(totalPaidConfirmation) && totalPrice.equals(totalPaidConfirmation));

        //Storing Confirmation Code
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Logged in to My Trips
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Selecting Change Flight link on Summary Page
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        //Changing departure to 2 out from the Change Flight pop up
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep","NA","NA","4");
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

        //Selecting new flight on Flight Availability page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("Dep","Standard","Cheap");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //Skipping bags, seats and extra adds on
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(CHECKIN_BAG_PURCHASE);
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page verification on My Trips Path
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);

        //Total Due calculation
        List<WebElement> listTotalDue = pageObjectManager.getPaymentPage().getTotalDuePriceTextList();
        double actualTotalDue     = priceToDouble(pageObjectManager.getPaymentPage().getTotalDuePriceText().getText());
        double expectedTotalDue   = 0.0;
        for (WebElement wb:listTotalDue){
            expectedTotalDue += priceToDouble(wb.getText());
        }

        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Confirmation Page: \n actualTotalDue = "
                        + actualTotalDue + "\n expectedTotalDue = " +  (Math.rint(expectedTotalDue*100)/100),
                actualTotalDue == (Math.rint(expectedTotalDue*100)/100));

        //Validating CHANGE FEE it's being charged per $90.00
        List<WebElement> changeFee = getDriver().findElements(By.xpath("//app-breakdown-section//p[contains(text(),'Change Fees')]"));
        List<WebElement> feePrice = getDriver().findElements(By.xpath("//app-breakdown-section//p[contains(text(),'Change Fees')]//following::p[2]"));

        ValidationUtil.validateTestStep("Validating Changes Fees right amount was applied to the total",
                changeFee.get(0).getText().equals("CHANGE FEES") && feePrice.get(0).getText().equals("$90.00"));

        //Validating ResCredit section
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getPaymentPage().getReservationCreditBlockTypeText());
        ValidationUtil.validateTestStep("Verify amount is added into Reservation Credit on Payment Page",
                pageObjectManager.getPaymentPage().getReservationCreditBlockTypeText().getText().trim(), CREDIT_TYPE);

        //verify pnr
        ValidationUtil.validateTestStep("Verify the PNR value for Reservation Credit on Payment Page",
                pageObjectManager.getPaymentPage().getReservationCreditBlockPNRText().getText().trim(), scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        //verify amount
        ValidationUtil.validateTestStep("Verify the amount to covert into reservation Credit on Payment Page",
                pageObjectManager.getPaymentPage().getReservationCreditBlockPriceText().getText().equals(pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("-","")));

        //Completing payment
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        WaitUtil.untilPageLoadComplete(getDriver());

        String totalPaidConfirmation2 = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();

        ValidationUtil.validateTestStep("Validating Total Amount matches on Confirmation Page: \n totalPaidConfirmation2 = "
                        + totalPaidConfirmation2 + "\n actualTotalDue = " + actualTotalDue,
                priceToDouble(totalPaidConfirmation2) == actualTotalDue );

        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();

        String finalTotalPaid = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();

        ValidationUtil.validateTestStep("Validating the last paid amount == the two previous paid combined: \n finalTotalPaid = "
                        + finalTotalPaid + "\n actualTotalDue = " + actualTotalDue + "\n totalPrice = " + totalPrice,
                priceToDouble(finalTotalPaid) == (Math.rint((actualTotalDue + priceToDouble(totalPrice))*100)/100));

        // Apply reservation credit
        if (actualTotalDue < 0){
            scenarioContext.setContext(Context.RESERVATION_CREDIT_AMOUNT,"$" + (-actualTotalDue));
            scenarioContext.setContext(Context.RESERVATION_CREDIT_CODE,scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

            //Home Page Methods
            pageMethodManager.getHomePageMethods().launchSpiritApp();
            pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
            pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
            pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
            pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
            pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
            pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
            pageMethodManager.getHomePageMethods().clickSearchButton();

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
            pageMethodManager.getPaymentPageMethods().applyReservationCredit();

            pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
            pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

            //Confirmation Page Methods
            WaitUtil.untilTimeCompleted(2000);
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        }
    }
    private double priceToDouble(String price){
        String value = price.replace("$","");
        value = value.replace(",","");
        return Double.valueOf(value);
    }
}
