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
//Test Case ID: TC28656
//Description : 9FC Flight Only INT-DOM OW. Validate prices on Dynamic Shopping Cart on Passenger Information, Bags, Seats
//              and Options page. Validating prices on Payment, Confirmation, Reservation Summary, Itinerary Receipt
//              Page. Add bags and verify Vat on Payment page and complete the checkin
//Created By  : Manasa Tilakraj
//Created On  : Dec 19 2019
//Reviewed By : Gabriela
//Reviewed On : 2/Jan/2020
//**********************************************************************************************

public class TC28656 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","CheckIn", "OneWay", "InternationalDomestic", "WithIn7Days", "Adult", "NineDFC","NonStop",
                    "BookIt","CarryOn","CheckedBags", "NoSeats", "CheckInOptions","AmericanExpress","DynamicShoppingCartUI",
                    "PaymentUI","ConfirmationUI","ItineraryReceiptUI","BagsUI"})
    public void Flight_Only_INT_DOM_OW_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Add_bags_and_verify_Vat_on_Payment_page_and_complete_the_check_in(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28656 under Web booking Price Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "GUA";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight details constant values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "AmericanExpressCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //My Trip bags page
        final String TRAVEL_BAGS        = "Carry_1|Checked_1";
        final String BUY_BAGS           = "Bags";
        final String SEAT_POPUP         = "DontPurchase";
        final String BAGS_POPUP         = "DontPurchase";
        final String HAZARD_POP_UP      = "Accept";
        final String PRINT_BP           = "Print";
        final String EMAIL_BP           = "NoEmail";
        final String TOTAL_VAT          = "12";
        final String BOARDING_PASS_URL  = "/check-in/boarding-pass";

        //Open spirit home page on browser
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Log in as a 9DFC member
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        String Flighttotal1 = "";
        if (TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText()))
        {
            Flighttotal1 = pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText().getText();
        }else {
            Flighttotal1 = pageObjectManager.getFlightAvailabilityPage().getStandardFarePriceText().getText();
        }

        double Flighttotal = Double.parseDouble(Flighttotal1.replace("$",""));
        System.out.println("Flighttotal: " + Flighttotal);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Validate Shopping Cart is present
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        String TotalPriceIti1 = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace(",","");
        double TotalPriceIti = Double.parseDouble(TotalPriceIti1.replace("$",""));

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
        WaitUtil.untilPageLoadComplete(getDriver());

        //Option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on options page with Flight total cost\n FLight cost:" + Flighttotal +"\n Cost on options page Itinerary:" + TotalPriceIti,
                TotalPriceIti==Flighttotal);

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);
        String TotalPrice1 = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace(",","");
        double TotalPrice = Double.parseDouble(TotalPrice1.replace("$",""));
        String TotalFlightPrice1 = pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace(",","");
        double TotalFlightPrice = Double.parseDouble(TotalFlightPrice1.replace("$",""));

        ValidationUtil.validateTestStep("Validating prices matching on Payment page \n Total Due:" + TotalPrice +"\n Total Due Flight Price:" + TotalFlightPrice,
                TotalPrice==TotalFlightPrice);

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Validate prices on confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        double TotalPricePaid1 = Double.parseDouble(pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on Confirmation page \n Total Due(From Payment Page):" + TotalPrice +"\n Total Due(From confirmation Page):" + TotalPricePaid1,
                TotalPricePaid1==TotalPrice);

        //Logging into My Trips Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        double TotalPricePaid2 = Double.parseDouble(pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptTotal().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on Itinerary page \nTotal Due(From Payment Page):" + TotalPrice +"\n Total Due(From Itinerary Page):" + TotalPricePaid2,
                TotalPrice==TotalPricePaid2);
        WaitUtil.untilTimeCompleted(1000);

        //Back to Online Check-In Page
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Adding Bags on Online Check-In
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(BUY_BAGS);
        pageMethodManager.getBagsPageMethods().selectDepartingBags(TRAVEL_BAGS);

        pageMethodManager.getBagsPageMethods().verifyVATTax("Dep", TOTAL_VAT);
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //No Purchase on Seats Pop up
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(SEAT_POPUP);

        //validate Vat Taxes are displaying correctly on Payment page
        TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getDepTotalDueBagsVatPriceText()==scenarioContext.getContext(Context.BAGS_DEP_VAT));

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Check In and complete
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).click();

        //insert passport information
        pageMethodManager.getPassportPageMethods().fillPassportInformation();

        //No purchase on Bags Pop Up
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(BAGS_POPUP);

        //No purchase on Seats Pop Up
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(SEAT_POPUP);

        //No purchase on Travel Guard Pop Up
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Accept on Hazard pop up
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(HAZARD_POP_UP);

        //Printing Boarding Pass
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(PRINT_BP,EMAIL_BP);

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user reached on Boarding Pass Page",
                getDriver().getCurrentUrl(),BOARDING_PASS_URL);
    }
}