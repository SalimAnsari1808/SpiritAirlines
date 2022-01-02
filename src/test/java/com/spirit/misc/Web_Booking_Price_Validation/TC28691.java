package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//********************************************************************************************************
//Test Case ID: TC28691
//Description : Task 28691: FS Flight + Car DOM RT. Validate prices on Dynamic Shoping Cart on Passenger Information,
// Bags, Seats and Options page. Validating prices on Payment, Confirmation, Reservation Summary, Itinerary Receipt Page.
// Validate cannot be modified or cancelled on MT
// Created By : Gabriela
//Created on  : 23-Dec-2019
//Reviewed By : Manasa Tilakraj
//Reviewed On : 26-Dec-2019
//*********************************************************************************************************
public class TC28691 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult","FreeSpirit", "BookIt",
                    "NoBags", "NoSeats", "FlightCar", "CarsUI", "CheckInOptions", "PaymentUI", "Visa",
                    "DynamicShoppingCartUI", "ConfirmationUI", "ReservationUI", "ItineraryReceiptUI"})
    public void FS_Flight_Car_DOM_RT_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Validate_cannot_be_modified_or_cancelled_on_MT(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28691 under Web_Booking_Price_Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String FS_LOGIN           = "FSEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "25";
        final String ARR_DATE           = "27";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        //Flight Availability Page Constant Values
        final String JOURNEY_UPGRADE    = "BookIt";

        //Option Page Constant Value
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String TRAVEL_GUARD       = "NotRequired";
        final String CARD_DETAIL        = "VisaCard";

        //Reservation Summary Page Constant Values
        final String WARNING_INFO       = "To make changes to your itinerary, please contact Customer Service directly at 1.954.698.0125.";

        //open browser
        openBrowser(platform);


        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(FS_LOGIN);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();


        //Flight Availability Page Method
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();


        //Cars Page Methods
        //sorting by prices lower to cheap
        WaitUtil.untilTimeCompleted(1200);
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getCarPage().getSortByPriceButton());
        WaitUtil.untilPageLoadComplete(getDriver());

        //storing price information for next validation
        double flightCarPrice = Double.parseDouble(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(0).getText().replace("$", ""));
        System.out.println("flightCarPrice: " + flightCarPrice);

        //selecting first cheap car available
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");

        //Selecting Book It on Upgrade & Save pop up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(JOURNEY_UPGRADE);
        WaitUtil.untilPageLoadComplete(getDriver());


        //Passenger Information Page Validations
        WaitUtil.untilTimeCompleted(1000);

        //storing price displayed for validation
        String itineraryPrice1 = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$", "");
        double itineraryPrice = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$", ""));
        System.out.println("itineraryPrice: " + itineraryPrice);

        ValidationUtil.validateTestStep("Validating total price displayed on the dynamic shopping cart on passenger info page it's matching with the expected result",
                itineraryPrice == flightCarPrice);

        //click on itinerary arrow to verify the vacation prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Flight + Car is displayed on the DSC",
                pageObjectManager.getHeader().getFlightItineraryText().getText(), TRIP_TYPE);

        //Selecting Continue on Passenger Information Page
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();


        //Bags Page Validation
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating itinerary prices are consistent on bags page",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$", "").equals(itineraryPrice1));

        //click on itinerary arrow to verify the vacation prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Flight + Car is displayed on the DSC",
                pageObjectManager.getHeader().getFlightItineraryText().getText(), TRIP_TYPE);
        ValidationUtil.validateTestStep("Validating vacation prices are consistent on bags page",
                pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$", "").equals(itineraryPrice1));

        //continue without adding bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();


        //Seats Page Validation
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating itinerary prices are consistent on seats page",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$", "").equals(itineraryPrice1));

        //click on itinerary arrow to verify the vacation prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Flight + Car is displayed on the DSC",
                pageObjectManager.getHeader().getFlightItineraryText().getText(), TRIP_TYPE);
        ValidationUtil.validateTestStep("Validating vacation prices are consistent on seats page",
                pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$", "").equals(itineraryPrice1));

        //continue without adding seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();


        //Options Page Validations
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating itinerary prices are consistent on options page",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$", "").equals(itineraryPrice1));

        //click on itinerary arrow to verify the vacation prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Flight + Car is displayed on the DSC",
                pageObjectManager.getHeader().getFlightItineraryText().getText(), TRIP_TYPE);
        ValidationUtil.validateTestStep("Validating vacation prices are consistent on options page",
                pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$", "").equals(itineraryPrice1));

        //Continue to payment page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();


        //Payment Page Validations
        //Storing total due value for validation
        WaitUtil.untilPageLoadComplete(getDriver());
        String totalDuePrice1 = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$", "");
        double totalDuePrice = Double.parseDouble(totalDuePrice1);

        //Validating Total Due is displaying the right information
        ValidationUtil.validateTestStep("Validating total due price is displaying the right information on Payment Page",
                totalDuePrice1.equals(itineraryPrice1));

        //Click on Total Due breakdown for validation
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        ;

        //click on Flight + Hotel + Car breakdown for values validation
        pageObjectManager.getPaymentPage().getTotalDueFlightText().click();
        WaitUtil.untilTimeCompleted(1200);

        System.out.println("total due displayed: " + pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace("$", ""));
        System.out.println("totalDuePrice1 " + totalDuePrice1);
        ValidationUtil.validateTestStep("Validating Total Due matching with the information displayed on the breakdown",
                pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace("$", "").equals(totalDuePrice1));

        //storing dep and ret values for validation
        System.out.println("depPrices: " + pageObjectManager.getPaymentPage().getTotalDepFlightDueText().getText());
        System.out.println("retPrices: " + pageObjectManager.getPaymentPage().getTotalRetFlightDueText().getText());
        double depPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDepFlightDueText().getText().replace("$", ""));
        double retPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalRetFlightDueText().getText().replace("$", ""));
        ValidationUtil.validateTestStep("Validating departure and returning prices are matching with the total due",
                Math.rint((retPrice + depPrice) * 100) / 100 == totalDuePrice);

        //Completing Payment
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);



        //Confirmation Page Validations
        try {
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

            //Storing PNR for validation
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

            ValidationUtil.validateTestStep("Validating Total Paid price information matching",
                    pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace("$", "").equals(itineraryPrice1));

            //Click on the Breakdown
            pageObjectManager.getConfirmationPage().getTotalPaidPriceText().click();
            WaitUtil.untilTimeCompleted(1200);

            //Click on Flight Breakdown
            pageObjectManager.getConfirmationPage().getFlightVerbiageLabel().click();
            WaitUtil.untilTimeCompleted(1200);

            //Storing dep and ret values
            double depPricePaid = Double.parseDouble(pageObjectManager.getConfirmationPage().getTotalDepFlightPaidText().getText().replace("$", ""));
            double retPricePaid = Double.parseDouble(pageObjectManager.getConfirmationPage().getTotalRetFlightPaidText().getText().replace("$", ""));
            System.out.println("depPricePaid: " + depPricePaid + " and retPricePaid: " + retPricePaid);

            double totalPaid = Double.parseDouble(pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace("$", ""));
            ValidationUtil.validateTestStep("Validating departure and returning prices are matching with the total due",
                    Math.rint((depPricePaid + retPricePaid) * 100) / 100 == totalPaid);

            //Log in on My Trips
            pageMethodManager.getHomePageMethods().loginToMyTrip();


            //Reservation Summary Page Validations
            ValidationUtil.validateTestStep("Validating Red Warning information",
                    getDriver().findElement(By.xpath("//div[contains(@class,'card modify-vacation-banner')]")).getText(), WARNING_INFO);

            //Validating package booking cannot modify the journey
            ValidationUtil.validateTestStep("Validating package booking cannot modify the journey",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton()));


            //Validating package booking cannot cancel the booking
            ValidationUtil.validateTestStep("Validating package booking cannot cancel the booking",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getCancelItineraryButton()));

            //click on Print Receipt link
            pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();


            //Itinerary Receipt Page Validation
            ValidationUtil.validateTestStep("Validating Total Paid price information matching",
                    pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace("$", "").equals(itineraryPrice1));

            //Storing dep and ret values
            double depPricePaid1 = Double.parseDouble(pageObjectManager.getConfirmationPage().getTotalDepFlightPaidText().getText().replace("$", ""));
            double retPricePaid1 = Double.parseDouble(pageObjectManager.getConfirmationPage().getTotalRetFlightPaidText().getText().replace("$", ""));
            System.out.println("depPricePaid1: " + depPricePaid1 + " and retPricePaid1: " + retPricePaid1);

            double totalPaid1 = Double.parseDouble(pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace("$", ""));
            ValidationUtil.validateTestStep("Validating departure and returning prices are matching with the total due",
                    Math.rint((depPricePaid1 + retPricePaid1) * 100) / 100 == totalPaid1);
        }

        //This catch block will catch any Validation/Assertion errors encountered after Payment and still cancel reservations
        catch (AssertionError fail) {
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage(), false);
        }

        //This catch block will catch any Exceptions (null pointer, no such element, etc) after Payment and still cancel reservations
        catch (Exception fail) {
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage(), false);
        }

        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
    }
}