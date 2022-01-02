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
//Test Case ID: TC28685
//Description : Task 28685: 9FC Flight + Hotel DOM RT. Validate prices on Dynamic Shoping Cart on Passenger Information,
// Bags, Seats and Options page. Validating prices on Payment, Confirmation, Reservation Summary, Itinerary Receipt Page.
// Validate cannot be modified or cancelled on
//Created By  : Gabriela
//Created on  : 19-Dec-2019
//Reviewed By : Manasa Tilakraj
//Reviewed On : 26-Dec-2019
//*********************************************************************************************************
public class TC28685 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult",
                    "NineDFC", "HotelsUI", "BookIt", "NoBags", "NoSeats", "DynamicShoppingCartUI",
                    "CheckInOptions", "PaymentUI", "Visa", "ConfirmationUI", "ReservationUI", "ItineraryReceiptUI"})
    public void Flight_Hotel_DOM_RT_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Validate_cannot_be_modified_or_cancelled_on(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28685 under Web_Booking_Price_Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 		= "English";
        final String FS_LOGIN           = "NineDFCEmail";
        final String JOURNEY_TYPE 	= "Vacation";
        final String TRIP_TYPE 		= "Flight+Hotel";
        final String DEP_AIRPORTS 	= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 	= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "MCO";
        final String DEP_DATE 		= "125";
        final String ARR_DATE 		= "127";
        final String ADULTS		= "1";
        final String CHILD		= "0";
        final String INFANT_LAP		= "0";
        final String INFANT_SEAT	= "0";

        //Flight Availability Page Constant Values
        final String JOURNEY_UPGRADE	= "BookIt";

        //Option Page Constant Value
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String TRAVEL_GUARD 	= "NotRequired";
        final String CARD_DETAIL 	= "VisaCard";

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
        pageMethodManager.getHomePageMethods().clickSearchButton();


        //Flight Availability Page Method
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        //looking for Universal hotels
        pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys("Universal");
        WaitUtil.untilPageLoadComplete(getDriver());

        //storing price information for next validation
        double hotelPrice = Double.parseDouble(pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(0).getText().replace("$",""));
        System.out.println("hotelPrice: " + hotelPrice);

        //Selecting cheaper universal hotel
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //selecting room from Room Category window
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validating room price match with the price information displayed on F+H availability page
        double roomPrice = Double.parseDouble(pageObjectManager.getHotelPage().getHotelPopUpRoomprice().get(0).getText().replace("$",""));
        System.out.println("roomPrice: " + roomPrice);
        ValidationUtil.validateTestStep("Validating room price match with the price information displayed on F+H availability page",
                roomPrice==hotelPrice);

        //Selecting room
        pageObjectManager.getHotelPage().getHotelPopUpExitIconLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");


        //Cars Page Methods
        pageMethodManager.getCarPageMethods().continueWithoutCar();

        //Selecting Book It on Upgrade & Save pop up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(JOURNEY_UPGRADE);
        WaitUtil.untilPageLoadComplete(getDriver());


        //Passenger Information Page Validations
        WaitUtil.untilTimeCompleted(1000);
        //storing price displayed for validation
        String itineraryPrice1 = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$","");
        double itineraryPrice = Double.parseDouble(itineraryPrice1);
        System.out.println("itineraryPrice: " + itineraryPrice);

        ValidationUtil.validateTestStep("Validating total price displayed match with the expected result",
                itineraryPrice == roomPrice);

        //click on itinerary arrow to verify the vacation prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Flight + Hotel + Car is displayed on the DSC",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),TRIP_TYPE);

        //Storing vacation price for validation
        String vacationPrice1 = pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$","");
        double vacationPrice = Double.parseDouble(vacationPrice1);
        System.out.println("vacationPrice: " + vacationPrice);

        ValidationUtil.validateTestStep("Validating Itinerary and Vacation prices matching on Passenger Information Page",
                vacationPrice == itineraryPrice);

        //Selecting Continue on Passenger Information Page
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();


        //Bags Page Validation
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating itinerary prices are consistent on bags page",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$","").equals(itineraryPrice1));

        //click on itinerary arrow to verify the vacation prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Flight + Hotel + Car is displayed on the DSC",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),TRIP_TYPE);
        ValidationUtil.validateTestStep("Validating vacation prices are consistent on bags page",
                pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$","").equals(vacationPrice1));

        //continue without adding bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();


        //Seats Page Validation
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating itinerary prices are consistent on seats page",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$","").equals(itineraryPrice1));

        //click on itinerary arrow to verify the vacation prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Flight + Hotel + Car is displayed on the DSC",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),TRIP_TYPE);
        ValidationUtil.validateTestStep("Validating vacation prices are consistent on seats page",
                pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$","").equals(vacationPrice1));

        //continue without adding seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();


        //Options Page Validations
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating itinerary prices are consistent on options page",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$","").equals(itineraryPrice1));

        //click on itinerary arrow to verify the vacation prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Flight + Hotel is displayed on the DSC",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),TRIP_TYPE);
        ValidationUtil.validateTestStep("Validating vacation prices are consistent on options page",
                pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$","").equals(vacationPrice1));

        //Continue to payment page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();


        //Payment Page Validations
        //Storing total due value for validation
        WaitUtil.untilPageLoadComplete(getDriver());
        String totalDuePrice1 = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$","");
        double totalDuePrice = Double.parseDouble(totalDuePrice1);

        //Validating Total Due is displaying the right information
        ValidationUtil.validateTestStep("Validating total due price is displaying the right information on Payment Page",
                totalDuePrice1.equals(itineraryPrice1));

        //Click on Total Due breakdown for validation
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();;

        //click on Flight + Hotel + Car breakdown for values validation
        pageObjectManager.getPaymentPage().getTotalDueFlightText().click();
        WaitUtil.untilTimeCompleted(1200);

        System.out.println("total due displayed: " + pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace("$",""));
        System.out.println("totalDuePrice1 " + totalDuePrice1);
        ValidationUtil.validateTestStep("Validating Total Due matching with the information displayed on the breakdown",
                pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace("$","").equals(totalDuePrice1));

        //storing dep and ret values for validation
        System.out.println("depPrices: " + pageObjectManager.getPaymentPage().getTotalDepFlightDueText().getText());
        System.out.println("retPrices: " + pageObjectManager.getPaymentPage().getTotalRetFlightDueText().getText());
        double depPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDepFlightDueText().getText().replace("$",""));
        double retPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalRetFlightDueText().getText().replace("$",""));

        ValidationUtil.validateTestStep("Validating departure and returning prices are matching with the total due",
                Math.rint((depPrice+retPrice)*100)/100 == totalDuePrice);
        WaitUtil.untilTimeCompleted(2000);

        //Completing Payment
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);


        //Confirmation Page Validations
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Storing PNR for validation
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        try {
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
                    Math.rint((depPrice+retPrice)*100)/100 == totalPaid);

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
                    Math.rint((depPrice+retPrice)*100)/100 == totalPaid1);
        }//This catch block will catch any Validation/Assertion errors encountered after Payment and still cancel reservations
        catch(AssertionError fail)
        {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage() , false );
        }
        //This catch block will catch any Exceptions (null pointer, no such element, etc) after Payment and still cancel reservations
        catch (Exception fail)
        {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage() , false );
        }
        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

    }
}