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

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//TODO: [IN:15876] - CP: BP: Options page:  Cars or Hotels sections are not displayed for an International destination (CUN)
//Test Case ID: TC28665
//Description: 9FC Hotel Upsell DOM-INT RT. Validate prices on Dynamic Shoping Cart on Passenger Information, Bags, Seats and Options page.  Validating prices on Payment, Confirmation, Reservation Summary, Itinerary Receipt Page. Validate cannot be modified or cancelled on MT
// Created By: Un Fai Chan
//Created On: 12/18/2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC28665 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug","BookPath", "OneWay", "DomesticInternational", "Flight", "WithIn7Days", "Adult", "FreeSpirit",
                    "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "ReservationUI" , "CheckIn"})
    public void nineFC_Hotel_Upsell_DOM_INT_RT_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Validate_cannot_be_modified_or_cancelled_on_MT(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28665 under Web_Booking_Price_Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String FS_EMAIL           = "NineDFCEmail";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "117";
        final String ARR_DATE           = "119";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADEVALUE       = "BookIt";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Reservation Summary Page Constant Values
        final String WARNING_INFO       = "To make changes to your itinerary, please contact Customer Service directly at 1.954.698.0125.";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

        String totalPrice;
        if (pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText().isDisplayed())
        {
            totalPrice = pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText().getText();
        }else
            totalPrice = pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText();

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

//        String totalPrice = String.valueOf(scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE));
//        totalPrice = '$' + totalPrice;

        //Passenger Info Methods
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        String flightPricePassenger = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalPassenger = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        System.out.println("totalPrice: " + totalPrice);
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


        //seats
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        String flightPriceSeats = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalSeats = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Seats Page: \n flightPriceSeats = "
                        + flightPriceSeats + "\n ItineraryTotalSeats = " + ItineraryTotalSeats,
                flightPriceSeats.equals(ItineraryTotalSeats) && totalPrice.equals(ItineraryTotalSeats));
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //options
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        String flightPriceOptions = pageObjectManager.getHeader().getFlightPriceItineraryText().getText();
        String ItineraryTotalOptions = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        ValidationUtil.validateTestStep("Validating Flight price and Itinerary Total Amount matches on Seats Page: \n flightPriceOptions = "
                        + flightPriceOptions + "\n ItineraryTotalOptions = " + ItineraryTotalOptions,
                flightPriceOptions.equals(ItineraryTotalOptions) && totalPrice.equals(ItineraryTotalOptions));

        //click on VIEW ALL hotels
        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Hotels Page Methods and Validations
        //looking for  hotels
        //TODO: [IN:15876] - CP: BP: Options page:  Cars or Hotels sections are not displayed for an International destination (CUN)
        List<String> hotelList = new ArrayList<>();
        int hotelListIndex = 0;
        hotelList.add("Grand Fiesta Americana Coral Beach Cancun Resort");
        hotelList.add("Fiesta Americana Condesa Cancun All Inclusive");
        hotelList.add("U by Grand Fiesta Americana ");
        hotelList.add("Bahia Principe");
        // Loop through the hotel List and check which one has availability
        for (String hotelName: hotelList){
            pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys(hotelName);
            WaitUtil.untilPageLoadComplete(getDriver());
            if (getDriver().findElements(By.xpath("//img[@alt='No results were found.']/..")).size() > 0){
                hotelListIndex++;
                pageObjectManager.getHotelPage().getHotelNameTextBox().clear();
            }else {
                break;
            }
        }



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
        //TODO: [IN:25913]	GoldFinger R1: CP: BP: Hotel upsell on Hub Page: total hotel price is off by a penny when adding Hotels on the Options page
        int daysTotal = Integer.parseInt(ARR_DATE) - Integer.parseInt(DEP_DATE);
        ValidationUtil.validateTestStep("Validating room price ($"+roomPrice+") match with the Hotel price information displayed on the Hotel page ($"+hotelPrice+") per the days out: "+daysTotal,
                roomPrice - Math.rint((hotelPrice * daysTotal)*100)/100 <= 0.01);

//Selecting room
        pageObjectManager.getHotelPage().getHotelPopUpExitIconLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage(hotelList.get(hotelListIndex),"NA");

        //expanding the dynamic shopping cart for prices validation
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getArrowYourItineraryImage());
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Flight + Hotel + Car itinerary is displayed on the Dynamic Shopping Cart on Options Page",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),"Flight +Hotel");

        String flightHotelCarItinerary1 = pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace(",","");
        double flightHotelCarItinerary = Double.parseDouble(flightHotelCarItinerary1.replace("$",""));
        System.out.println("flightHotelCarItinerary: " +flightHotelCarItinerary);
        double round = Math.rint((priceToDouble(ItineraryTotalOptions) + roomPrice)*100)/100;
        System.out.println("round: "+round );
        ValidationUtil.validateTestStep("Validating the Hotel Price ($"+roomPrice+") was added to the itinerary ("+ItineraryTotalOptions+") on the Dynamic Shopping Cart and now is displaying ($"+flightHotelCarItinerary+")",
                flightHotelCarItinerary == Math.rint((priceToDouble(ItineraryTotalOptions) + roomPrice)*100)/100);

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment page
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getTotalDueFlightChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);
        String totalDuePayment = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
        String flightPricePayment = pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText();
        String retFlightDue = pageObjectManager.getPaymentPage().getTotalRetFlightDueText().getText();
        String depFlightDue = pageObjectManager.getPaymentPage().getTotalDepFlightDueText().getText();
        ValidationUtil.validateTestStep("Validating Total Flight Due = retFlightDue + depFlightDue:" +
                        "\n flightPricePayment = " + flightPricePayment + "\n retFlightDue = " + retFlightDue + "\n depFlightDue = " + depFlightDue + "\n sum = " + Math.rint((priceToDouble(retFlightDue) + priceToDouble(depFlightDue))*100)/100,
                priceToDouble(flightPricePayment) == Math.rint((priceToDouble(retFlightDue) + priceToDouble(depFlightDue))*100)/100);
        ValidationUtil.validateTestStep("Validating Total Due equals to the flight + hotel price: \n Total Due = "+ totalDuePayment +"\n flight + hotel = " + flightPricePayment,
                flightPricePayment.equals(totalDuePayment));

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);

        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        try {
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

            //Storing PNR for validation
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

            System.out.println(totalDuePayment);
            System.out.println(pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText());
            ValidationUtil.validateTestStep("Validating Total Paid price information is correct on Confirmation Page",
                    pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().equals(totalDuePayment));

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
                    pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().equals(totalDuePayment));

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

    private double priceToDouble(String price){
        String value = price.replace("$","");
        value = value.replace(",","");
        return Double.valueOf(value);
    }
}