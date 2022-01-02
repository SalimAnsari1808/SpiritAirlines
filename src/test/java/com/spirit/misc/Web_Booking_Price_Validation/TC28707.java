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
//Test Case ID: TC28707
//Description : FS Multicity DOM-INT within 24 hours. Validate prices on Dynamic Shopping Cart on Passenger Information,
//              Bags, Seats and Options Page. Validate No Car or Hotel Upsell on Options Page. Validate prices on Payment,
//              Confirmation, Reservation Summary and Receipt Page. Check In booking
//Created By  : Shourya Ravula
//Created On  : Dec 23 2019
//Reviewed By : Gabriela
//Reviewed On : 3/Jan/2019
//**********************************************************************************************

public class TC28707 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "MultiCity", "DomesticInternational", "WithIn7Days", "Adult", "FreeSpirit", "NonStop",
                    "BookIt","NoBags", "NoSeats", "CheckInOptions","MasterCard","PaymentUI","ItineraryReceiptUI",
                    "ConfirmationUI","DynamicShoppingCartUI"})
    public void FS_Multicity_DOM_INT_within_24_hours_Validate_no_vacation_optoins_are_displayed_on_Home_Page_Validate_prices_on_Dynamic_Shoipng_Cart_on_Passenger_Information_Bags_Seats_and_Options_Page_Validate_No_Car_or_Hotel_Upsell_on_Options_Page_Validate_prices_on_Payment_Confirmation_Reservation_Summary_and_Receipt_Page_Check_In_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28707 under Web_Booking_Price_Validation Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL|FLL";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "GUA|LIM";
        final String DEP_DATE           = "0|2";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Online Check-In Page Constant Values
        final String CHECK_IN_BAGS      = "DontPurchase";
        final String CHECK_IN_SEAT      = "DontPurchase";
        final String CHECK_IN_HAZARD    = "Accept";
        final String CHECK_IN_PRINT     = "Print";
        final String CHECK_IN_EMAIL     = "NoEmail";
        final String BOARDING_PASS_URL  = "/check-in/boarding-pass";

        // open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(DEP_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        String FlightPrice = String.valueOf(scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE));
        FlightPrice = '$' + FlightPrice;
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        // Validate Flight price from the dropdown in passenger info page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Validate Dynamic shopping cart is available on Passenger Info Page
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Price Validation @Passenger Page
        String TotalPrice = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        System.out.println(TotalPrice);
        ValidationUtil.validateTestStep("Validate price on Passenger page", TotalPrice.equals(FlightPrice) );
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Validate Flight price from the dropdown in Bags page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Price Validation @Bags Page
        TotalPrice = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        System.out.println(TotalPrice);
        ValidationUtil.validateTestStep("Validate Price on Bags Page",TotalPrice.equals(FlightPrice) );
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        // Validate Flight price from the dropdown in Seats page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Price Validation @Seats Page
        TotalPrice = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        System.out.println(TotalPrice);
        ValidationUtil.validateTestStep("Validate Price on Seats Page", TotalPrice.equals(FlightPrice));
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        // Validate Flight price from the dropdown in Options page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Price Validation @Options Page
        TotalPrice = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        System.out.println(TotalPrice);
        ValidationUtil.validateTestStep("Validate Total Price on Options Page", TotalPrice.equals(FlightPrice));

        // Validate No Car or Hotel Upsell on Options Page
        ValidationUtil.validateTestStep("Validate Car Upsell is not displayed", !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getCarContainerSelectedText()));
        ValidationUtil.validateTestStep("Validate Hotel Upsell is not Displayed", !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getHotelContainerSelectedText()));

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Validate Flight price from the dropdown in Payment page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);

        String TotalDuePrice = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
        String TotalFlightPrice = pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText();

        ValidationUtil.validateTestStep("Validating prices matching on Payment page", TotalDuePrice.equals(TotalFlightPrice));

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Validate Flight price from the dropdown in Confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Price Validation @Confirmation Page
        String TotalPricePaid = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();
        ValidationUtil.validateTestStep("Validating prices matching on Confirmation page", TotalPricePaid.equals(TotalPrice));

        //Validate Flight price on Reservation Summary and Receipt Page
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        WaitUtil.untilTimeCompleted(1200);

        //Price Validation @Receipt Page
        String TotalPaid = pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptTotal().getText();
        ValidationUtil.validateTestStep("Validate Price on Receipt", TotalPricePaid.equals(TotalPaid));

        //Validate Checking-In after Price validation on Receipt Page
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on Check In and Get Your Boarding Pass button
        pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).click();

        //Passport Information Page Method
        pageMethodManager.getPassportPageMethods().fillPassportInformation();

        //No Purchase on Bags Pop Up
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECK_IN_BAGS);

        //No Purchase on Seats Pop Up
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(CHECK_IN_SEAT);

        //Continue to complete the Check In on Options Page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //No Purchase on Travel Guard Pop Up
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Accept on Hazard Pop Up
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(CHECK_IN_HAZARD);
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(CHECK_IN_PRINT,CHECK_IN_EMAIL);

        //Boarding Pass Page Verification
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user reached on Boarding Pass Page",
                getDriver().getCurrentUrl(),BOARDING_PASS_URL);
    }
}

