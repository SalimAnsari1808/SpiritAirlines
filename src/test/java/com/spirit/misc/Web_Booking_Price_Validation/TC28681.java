package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//********************************************************************************************************
//TODO: [IN:25529]	GoldFinger R1: CP: BP: Car upsell on Hub Page: 0.01 cent is missing from the total car price when adding car on Options page
//Test Case ID: TC28681
//Description : Task 28681: 9FC Hotel + Car Upsell DOM-INT RT. Validate prices on Dynamic Shopping Cart on Passenger Information, Bags, Seats and Options page. Validating prices on Payment, Confirmation, Reservation Summary, Itinerary Receipt Page. Validate cannot be modified or canc
//Created By  : Gabriela
//Created on  : 19-Dec-2019
//Reviewed By : Un Fai Chan
//Reviewed On : 26-Dec-2019
//*********************************************************************************************************
public class TC28681 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticInternational", "FlightHotelCar", "Outside21Days", "Adult",
            "NineDFC", "CarsUI", "HotelsUI", "BookIt", "NoBags", "NoSeats", "DynamicShoppingCartUI","Cars","Hotels",
            "CheckInOptions", "PaymentUI", "Visa", "ConfirmationUI", "ReservationUI", "ItineraryReceiptUI"})
    public void  Hotel_Car_Upsell_DOM_INT_RT_Validate_prices_on_Dynamic_Shopping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Validate_cannot_be_modified_or_canc(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28681 under Web_Booking_Price_Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 		= "English";
        final String FS_LOGIN       = "NineDFCEmail";
        final String JOURNEY_TYPE 	= "Flight";
        final String TRIP_TYPE 		= "RoundTrip";
        final String DEP_AIRPORTS 	= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 	= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "CUN";
        final String DEP_DATE 		= "105";
        final String ARR_DATE 		= "107";
        final String ADULT		    = "1";
        final String CHILD		    = "0";
        final String INFANT_LAP		= "0";
        final String INFANT_SEAT	= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT     = "NonStop";
        final String ARR_FLIGHT     = "NonStop";
        final String FARE_TYPE      = "Standard";
        final String JOURNEY_UPGRADE= "BookIt";

        //Option Page Constant Value
        final String Vacation       = "Flight + Car";
        final String Vacation1      = "Flight + Hotel + Car";
        final String OPTION_VALUE   = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String TRAVEL_GUARD 	= "NotRequired";
        final String CARD_DETAIL 	= "VisaCard";

        //Reservation Summary Page Constant Values
        final String WARNING_INFO   = "To make changes to your itinerary, please contact Customer Service directly at 1.954.698.0125.";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Flight Availability Page Method
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep",DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        String totalPrice1 = String.valueOf(scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE).toString());
        Double totalPrice = Double.parseDouble(totalPrice1);
        System.out.println("Only Flight Total Price: " + totalPrice);

        //Selecting Book It on Upgrade & Save pop up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(JOURNEY_UPGRADE);

//Passenger Information Page Validations
        WaitUtil.untilTimeCompleted(1000);

        //storing flight price for validation on the Dynamic Shopping Cart for validation
        String itineraryPrice1 = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$","");
        double itinerary = Double.parseDouble(itineraryPrice1);

        ValidationUtil.validateTestStep("Validating total price displayed on the dynamic shopping cart on passenger info page (" + itineraryPrice1 + ") it's matching with the Flight Total Price (" +totalPrice1+")",
                itineraryPrice1.equals(totalPrice1));

        //click on itinerary arrow to verify the vacation prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating only Flight itinerary and no vacation is displayed on the Dynamic Shopping Cart on Passenger Information Page",
                pageObjectManager.getHeader().getFlightItineraryText().getText().equals(JOURNEY_TYPE));

        //Selecting Continue on Passenger Information Page
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Bags Page Validation
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on itinerary arrow to verify the Flight itinerary prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Flight itinerary price on the Dynamic Shopping Cart is consistent on Bags page",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$","").equals(itineraryPrice1)
                        && pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$","").equals(itineraryPrice1));

        ValidationUtil.validateTestStep("Validating only Flight itinerary and no vacation is displayed on the Dynamic Shopping Cart on Bags Page",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),JOURNEY_TYPE);

        //continue without adding bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//Seats Page Validation
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on itinerary arrow to verify the Flight itinerary prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Flight itinerary price on the Dynamic Shopping Cart is consistent on Seats page",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$","").equals(itineraryPrice1)
                        && pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$","").equals(itineraryPrice1));

        ValidationUtil.validateTestStep("Validating only Flight itinerary and no vacation is displayed on the Dynamic Shopping Cart on Seats Page",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),JOURNEY_TYPE);

        //continue without adding seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Options Page Validations
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on itinerary arrow to verify the Flight itinerary prices
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Flight itinerary price on the Dynamic Shopping Cart is consistent on Options page",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$","").equals(itineraryPrice1)
                        && pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$","").equals(itineraryPrice1));

        ValidationUtil.validateTestStep("Validating only Flight itinerary and no vacation is displayed on the Dynamic Shopping Cart on Options Page",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),JOURNEY_TYPE);

        //click on view all cars link on Options page
        pageObjectManager.getCarPage().getViewAllCarsButton().click();

//Cars Page Methods and Validations
        //sorting by prices lower to cheap
        WaitUtil.untilTimeCompleted(1200);
        JSExecuteUtil.clickOnElement(getDriver(),  pageObjectManager.getCarPage().getSortByPriceButton());
        WaitUtil.untilPageLoadComplete(getDriver());

        //storing price information for next validation
        double carPrice = Double.parseDouble(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(0).getText().replace("$",""));
        System.out.println("Car Price information displayed on the Cars Page: " +carPrice);
        String carTotalPrice1 = pageObjectManager.getCarPage().getFACarTotalPriceText().get(0).getText().replace("$","");
        double carTotalPrice = Double.parseDouble(carTotalPrice1.replace(" Total Includes taxes",""));
        System.out.println("Car Total Price to charge displayed on Cars Page is: " + carTotalPrice);
        int daysTotal = Integer.parseInt(ARR_DATE) - Integer.parseInt(DEP_DATE);
        System.out.println("days out Total: " + daysTotal);

        //TODO: [IN:25529]	GoldFinger R1: CP: BP: Car upsell on Hub Page: 0.01 cent is missing from the total car price when adding car on Options page
        ValidationUtil.validateTestStep("Validating Car price information displayed on the page ($"+carPrice+") per the days out: "+daysTotal+"is matching with amount charged: $"+carTotalPrice,
                Math.rint((carPrice*daysTotal)*100)/100 == carTotalPrice);

        //selecting first cheap car available
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

        WaitUtil.untilPageLoadComplete(getDriver());
        //expanding the dynamic shopping cart for prices validation
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);

        double itineraryPrice = Double.parseDouble( pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
        double flightCarItinerary = Double.parseDouble( pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$",""));
        System.out.println("Flight + Car itinerary displayed on Dynamic Shopping Cart: " + itineraryPrice);

        ValidationUtil.validateTestStep("Validating itinerary Flight + Car price displayed on the Dynamic Shopping Cart displayed ($"+itineraryPrice+") is matching with the Flight Price value ($"+itinerary+") + the car total price to charge ("+carTotalPrice+")",
                itineraryPrice == Math.rint((itinerary + carTotalPrice)*100)/100 && flightCarItinerary == itineraryPrice);

        ValidationUtil.validateTestStep("Validating Flight + Car itinerary is displayed on the Dynamic Shopping Cart on Options Page",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),Vacation);

        //click on VIEW ALL hotels
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHotelPage().getViewAllHotelsButton());
        WaitUtil.untilPageLoadComplete(getDriver());

//Hotels Page Methods and Validations
        //looking for  hotels
        pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys("U by Grand Fiesta Americana");
        WaitUtil.untilPageLoadComplete(getDriver());

        //storing price information for next validation
        double hotelPrice = Double.parseDouble(pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(0).getText().replace("$",""));
        System.out.println("hotel Price information displayed on the Hotels Page: " + hotelPrice);

        //Selecting hotel
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //selecting room from Room Category window
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validating room price match with the price information displayed on Hotel Page
        String roomPrice1 = pageObjectManager.getHotelPage().getHotelPopUpRoomprice().get(0).getText().replace(",","");
        double roomPrice = Double.parseDouble(roomPrice1.replace("$",""));
        System.out.println("room Price information displayed on Room Category window: " + roomPrice);
        ValidationUtil.validateTestStep("Validating room price ($"+roomPrice+") match with the Hotel price information displayed on the Hotel page ($"+hotelPrice+") per the days out: "+daysTotal,
                roomPrice == Math.rint((hotelPrice * daysTotal)*100)/100);

        //Selecting room
        pageObjectManager.getHotelPage().getHotelPopUpExitIconLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("U by Grand Fiesta Americana","NA");

        //expanding the dynamic shopping cart for prices validation
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Flight + Hotel + Car itinerary is displayed on the Dynamic Shopping Cart on Options Page",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),Vacation1);

        String flightHotelCarItinerary1 = pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace(",","");
        double flightHotelCarItinerary = Double.parseDouble(flightHotelCarItinerary1.replace("$",""));
        System.out.println("flightHotelCarItinerary: " +flightHotelCarItinerary);
        double round = Math.rint((itineraryPrice + roomPrice)*100)/100;
        System.out.println("round: "+round );
        ValidationUtil.validateTestStep("Validating the Hotel Price ($"+roomPrice+") was added to the itinerary ($"+itineraryPrice+") on the Dynamic Shopping Cart and now is displaying ($"+flightHotelCarItinerary+")",
                flightHotelCarItinerary == Math.rint((itineraryPrice + roomPrice)*100)/100);

        //Continue to payment page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Payment Page Validations
        //Storing total due value for validation
        WaitUtil.untilPageLoadComplete(getDriver());
        String totalDuePrice1 = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace(",","");
        double totalDuePrice = Double.parseDouble(totalDuePrice1.replace("$" ,""));

        //Expanding Total Due breakdown for validation
        pageObjectManager.getPaymentPage().getTotalDuePriceText().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getTotalDueFlightText().click();
        WaitUtil.untilTimeCompleted(1000);

        //Validating Total Due is displaying the right information
        String breakdownValue1 = pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace(",","");
        double breakdownValue = Double.parseDouble(breakdownValue1.replace("$",""));
        System.out.println("breakdownValue: " + breakdownValue);

        System.out.println("Total DuePrice displayed on the breakdown on Payment Page: " +totalDuePrice);
        ValidationUtil.validateTestStep("Validating total due price ($"+totalDuePrice+") displayed on the Total Due Breakdown is matching with the F+H+C itinerary price",
                totalDuePrice == flightHotelCarItinerary && breakdownValue == totalDuePrice);

        //storing dep and ret values for validation
        System.out.println("depPrices: " + pageObjectManager.getPaymentPage().getTotalDepFlightDueText().getText());
        System.out.println("retPrices: " + pageObjectManager.getPaymentPage().getTotalRetFlightDueText().getText());
        double depPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDepFlightDueText().getText().replace("$","").replace(",",""));
        double retPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalRetFlightDueText().getText().replace("$","").replace(",",""));
        ValidationUtil.validateTestStep("Validating departure price ($"+depPrice+") + the returning price ($"+retPrice+") are matching with the total due",
                Math.rint((retPrice + depPrice)*100)/100 == totalDuePrice);

        //Completing Payment
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

//Confirmation Page Validations
        try {
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

            //Storing PNR for validation
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

            ValidationUtil.validateTestStep("Validating Total Paid price information is correct on Confirmation Page",
                    pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace(",","").equals(totalDuePrice1));

            //Click on the Breakdown
            pageObjectManager.getConfirmationPage().getTotalPaidPriceText().click();
            WaitUtil.untilTimeCompleted(1200);

            //Click on Flight Breakdown
            pageObjectManager.getConfirmationPage().getFlightVerbiageLabel().click();
            WaitUtil.untilTimeCompleted(1200);

            //Storing dep and ret values
            String depPricePaid1 = pageObjectManager.getConfirmationPage().getTotalDepFlightPaidText().getText().replace(",", "");
            double depPricePaid = Double.parseDouble(depPricePaid1.replace("$", ""));
            String retPricePaid1 = pageObjectManager.getConfirmationPage().getTotalRetFlightPaidText().getText().replace(",", "");
            double retPricePaid = Double.parseDouble(retPricePaid1.replace("$", ""));
            String totalPaid1 = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace(",", "");
            double totalPaid = Double.parseDouble(totalPaid1.replace("$", ""));

            ValidationUtil.validateTestStep("Validating departure price ($"+depPricePaid+") + the returning price ($"+retPricePaid+") are matching with the total paid on Confirmation Page",
                    Math.rint((depPricePaid + retPricePaid)*100)/100 == totalPaid);

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
            ValidationUtil.validateTestStep("Validating Total Paid price information is correct on Itinerary Page",
                    pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace(",", "").equals(totalDuePrice1));

            //Storing dep and ret values
            String IdepPricePaid1 = pageObjectManager.getConfirmationPage().getTotalDepFlightPaidText().getText().replace(",", "");
            double IdepPricePaid = Double.parseDouble(IdepPricePaid1.replace("$", ""));
            String IretPricePaid1 = pageObjectManager.getConfirmationPage().getTotalRetFlightPaidText().getText().replace(",", "");
            double IretPricePaid = Double.parseDouble(IretPricePaid1.replace("$", ""));
            String ItotalPaid1 = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace(",", "");
            double ItotalPaid = Double.parseDouble(ItotalPaid1.replace("$", ""));

            ValidationUtil.validateTestStep("Validating departure price ($"+IdepPricePaid+") + the returning price ($"+IretPricePaid+") are matching with the total paid on Itinerary Page",
                    Math.rint((IdepPricePaid + IretPricePaid)*100)/100 == ItotalPaid);
        }        //This catch block will catch any Validation/Assertion errors encountered after Payment and still cancel reservations
        catch(AssertionError fail)
        {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage() , false );
        }
        //This catch block will catch any Exceptions (null pointer, no such element, etc) after Payment and still cancel reservations
        catch (Exception fail)
        {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage() , false );
        }
        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();

    }

}

