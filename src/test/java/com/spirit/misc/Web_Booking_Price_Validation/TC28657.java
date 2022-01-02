package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC28657
//Description : 9FC Flight Only DOM-INT RT. Validate prices on Dynamic Shopping Cart on Passenger Information, Bags, Seats
//              and Options page.  Validating prices on Payment, Confirmation, Reservation Summary, Itinerary Receipt Page.
//              Check-In, add bags and seats verify VAT and prices on Payment page and Boarding Pass information
//Created By  : Manasa Tilakraj
//Created On  : Dec 19 2019
//Reviewed By : Gabriela
//Reviewed On : 2/Jan/2020
//**********************************************************************************************

public class TC28657 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticInternational", "WithIn7Days", "Adult", "NineDFC","Connecting","BookIt"
                    ,"CheckInOptions","PaymentUI","ConfirmationUI","DynamicShoppingCartUI","ItineraryReceiptUI","AddEditBags",
                    "CarryOn","CheckedBags","Standard"})
    public void Flight_Hotel_DOM_INT_RT_Validate_prices_on_Dynamic_Shoping_Cart_on_Passenger_Information_Bags_Seats_and_Options_page_Validating_prices_on_Payment_Confirmation_Reservation_Summary_Itinerary_Receipt_Page_Validate_cannot_be_modified_or_cancelled_on_MT(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28657 under Web booking Price Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "ATL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "GUA";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "4";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight details constant values
        final String DEP_FLIGHT         = "Connecting";
        final String RET_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Check In Page Constant Values
        final String TRAVEL_BAGS        = "Carry_1|Checked_1";
        final String MYTRIP_BUY_BAGS    = "Bags";
        final String TRAVEL_SEATS       = "Purchase";
        final String MYTRIP_BUY_SEAT    = "Standard||Standard";
        final String BAGS_POP_UP        = "DontPurchase";
        final String HAZARD_POP_UP      = "Accept";
        final String PRINT_BP           = "Print";
        final String EMAIL_BP           = "NoEmail";
        final String BOARDING_PASS_URL  = "/check-in/boarding-pass";
        final String MYTRIP_CARD_TYPE   = "DiscoverCard1";


        //Open Spirit home page in the browser
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

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

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

        //Validate Flight price from the dropdown in passenger info page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Click on the dropdown arrow
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        String TotalPriceIti1 = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace(",","");
        double TotalPriceIti = Double.parseDouble(TotalPriceIti1.replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on Passenger info page with flight total cost \n FLight cost:" + Flighttotal + "\n Cost on Passenger Itinerary:" + TotalPriceIti,
                TotalPriceIti==Flighttotal);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Validate Flight price from the dropdown in Bags page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on bags page with flight total \n FLight cost:" + Flighttotal +"\n Cost on bags page Itinerary:" + TotalPriceIti,
                TotalPriceIti==Flighttotal);

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());


        //Validate Flight price from the dropdown in seats page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on seats page with Flight total cost \n FLight cost:" + Flighttotal +"\n Cost on seats page Itinerary:" + TotalPriceIti, TotalPriceIti==Flighttotal);

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Validate Flight price from the dropdown in options page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Prices
        TotalPriceIti = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on options page with Flight total cost\n FLight cost:" + Flighttotal +"\n Cost on options page Itinerary:" + TotalPriceIti,
                TotalPriceIti==Flighttotal);

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Validate prices on Payment page and complete booking
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

        String TotalPricePaid1 = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace(",","");
        double TotalPricePaid = Double.parseDouble(TotalPricePaid1.replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on Confirmation page \n Total Due(From Payment Page):" + TotalPrice +"\n Total Due(From confirmation Page):" + TotalPricePaid1,
                TotalPricePaid==TotalPrice);

        //Logging into Online Check-In
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate prices on Itinerary Receipt
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        String ITotalPricePaid1 = pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptTotal().getText().replace(",","");
        double ITotalPricePaid = Double.parseDouble(ITotalPricePaid1.replace("$",""));
        ValidationUtil.validateTestStep("Validating prices matching on Itinerary page \nTotal Due(From Payment Page):" + TotalPrice +"\n Total Due(From Itinerary Page):" + ITotalPricePaid,
                TotalPrice==ITotalPricePaid);

        //Add Bags in Check in
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_BUY_BAGS);
        pageMethodManager.getBagsPageMethods().selectDepartingBags(TRAVEL_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(TRAVEL_BAGS);

        double TotalBagsPrice = Double.parseDouble(pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText().replace("$", ""));

        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Add Seat on CheckIn Path
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(TRAVEL_SEATS);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(MYTRIP_BUY_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(MYTRIP_BUY_SEAT);

        double TotalSeatsPrice = Double.parseDouble(pageObjectManager.getSeatsPage().getSeatsTotalPriceText().getText().replace("$", ""));

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Payment page Methods
        double TotalDue = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$", ""));
        double Totalbagandseats = TotalBagsPrice+TotalSeatsPrice;

        ValidationUtil.validateTestStep("Validating bags and seats prices matching on Payment page \n Total Due:" + TotalDue + "\n Total Due Seats and Bag Price:" + Totalbagandseats,
                TotalDue == Totalbagandseats);

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(MYTRIP_CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).click();

        //insert passport information
        pageMethodManager.getPassportPageMethods().fillPassportInformation();

        //No purchase on Bags pop up
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(BAGS_POP_UP);

        //No purchase on Travel guard pop up
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