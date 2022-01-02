package com.spirit.misc.Web_Booking_Price_Validation;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.CardDetailsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC28706
//Description : FS Multicity DOM within 7 days. Validate no vacation upsell options are displayed on Home Page.
//              Validate prices on Dynamic Shoipng Cart on Passenger Information, Bags, Seats and Options Page.
//              Validate No Car or Hotel Upsell on Options Page. Validate prices on Payment, Confirmation, Reservation
//              Summary and Receipt Page. Cancel booking and verify reservation Credit
//Created By  : Manasa Tilakraj
//Created On  : Dec 23 2019
//Reviewed By : Gabriela
//Reviewed On : 31/Dec/2019
//**********************************************************************************************
public class TC28706 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "MultiCity", "DomesticDomestic", "WithIn7Days", "VisaCard", "Adult", "FreeSpirit",
                    "NonStop", "BookIt", "NoBags","NoSeats","CheckInOptions","OptionalUI","PaymentUI","ReservationUI",
                    "ConfirmationUI","ItineraryReceiptUI"})
    public void FS_Multicity_DOM_within_7_days_Validate_no_vacation_optoins_are_displayed_on_Home_Page_Validate_prices_on_Dynamic_Shoipng_Cart_on_Passenger_Information_Bags_Seats_and_Options_Page_Validate_No_Car_or_Hotel_Upsell_on_Options_Page_Validate_prices_on_Payment_Confirmation_Reservation_Summary_and_Receipt_Page_Cancel_booking_and_verify_reservation_Credit(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC28706 under Web booking Price Validation Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL|LAS";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO|LAX";
        final String DEP_DATE           = "2|7";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String RET_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Cancellation Page constant values
        final int FIRST_INDEX 		    = 0;
        final String CANCELLATION_CONFIRMATION = "Your reservation has been cancelled and refunded to the original form of payment as shown below, and an email with details has been sent to:";

        //open browser
        openBrowser(platform);

//Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

        ValidationUtil.validateTestStep("Validating vacation- Flight+Hotel || Flight+Car || Flight+Hotel+Car is not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightHotelLabel())
                        && !TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightCarRadioButton())
                        && !TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightHotelCarRadiobutton()));

        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(DEP_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

        //Storing departing flight prices for next validations
        double depPrice = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getDepartureFlightBlockPriceText().getText().replace("$",""));
        System.out.println("depPrice: " +depPrice);
        double retPrice = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getArrivalFlightBlockPriceText().getText().replace("$",""));
        System.out.println("retPrice: " +retPrice);

        //Storing values on Flight Availability Page for next validations
        String flightTotal1 = pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().replace(",", "");
        double flightTotal = Double.parseDouble(flightTotal1.replace("$", ""));
        System.out.println("Total Flight Cost:" + flightTotal);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        ValidationUtil.validateTestStep("Validating the departing price $" +depPrice+" + the returning price $"+retPrice+" is $" +flightTotal,
                Math.rint((depPrice + retPrice)*100)/100 == flightTotal);

        //Selecting Book It on Upgrade & Save pop up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Click on the dropdown arrow
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Validate Shopping Cart is present
        ValidationUtil.validateTestStep("Verify Only flight is displayed on the Shopping Cart on Passenger Info page",
                pageObjectManager.getHeader().getFlightItineraryText().getText().equals(JOURNEY_TYPE));

        //Validate Prices
        String TotalPriceIti1 = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace(",", "");
        double TotalPriceIti = Double.parseDouble(TotalPriceIti1.replace("$", ""));
        ValidationUtil.validateTestStep("Validating prices matching on Passenger info page with flight total cost FLight cost:" + flightTotal + " Cost on Passenger Itinerary:" + TotalPriceIti,
                TotalPriceIti == flightTotal);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Bags Page Methods
        //Click on the dropdown arrow
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //Validate Prices
        ValidationUtil.validateTestStep("Validating prices matching on bags page with flight total FLight cost:" + flightTotal + " Cost on bags page Itinerary:" + TotalPriceIti,
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace(",", "").equals(TotalPriceIti1));

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//Seats Page Methods
        //Click on the dropdown arrow
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //Validate Prices
        ValidationUtil.validateTestStep("Validating prices matching on seats page with Flight total cost \n FLight cost:" + flightTotal + "\n Cost on seats page Itinerary:" + TotalPriceIti,
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace(",", "").equals(TotalPriceIti1));

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

//option Page Methods
        //Click on the dropdown arrow
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //Validate Prices
        ValidationUtil.validateTestStep("Validating prices matching on options page with Flight total cost FLight cost:" + flightTotal + " Cost on options page Itinerary:" + TotalPriceIti,
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace(",", "").equals(TotalPriceIti1));

        //Validate upsell is not displayed
        ValidationUtil.validateTestStep("Hotel upsell is not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText())
                        &&  !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getViewAllHotelsButton()));

        ValidationUtil.validateTestStep("Car upsell is not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCarouselTitleText()) &&
                        !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getViewAllCarsButton()));

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);
        double TotalPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$", ""));
        double TotalFlightPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace("$", ""));

        ValidationUtil.validateTestStep("Validating prices matching on Payment page Total Due:" + TotalPrice + " Total Due Flight Price:" + TotalFlightPrice,
                pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace(",", "").equals(TotalPriceIti1));

        //Completing Payment
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        double TotalPricePaid1 = Double.parseDouble(pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace("$", ""));
        ValidationUtil.validateTestStep("Validating prices matching on Confirmation page \n Total Due(From Payment Page):" + TotalPrice + "\n Total Due(From confirmation Page):" + TotalPricePaid1,
                TotalPricePaid1 == TotalPrice);

        //My Trips Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        double TotalPricePaid2 = Double.parseDouble(pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptTotal().getText().replace("$", ""));
        ValidationUtil.validateTestStep("Validating prices matching on Itinerary page (From Payment Page):" + TotalPrice + " and Total Due(From Itinerary Page):" + TotalPricePaid2,
                TotalPrice == TotalPricePaid2);
        WaitUtil.untilTimeCompleted(1000);

        //Moving back to reservation Summary page
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //canceling Journey
        pageMethodManager.getReservationSummaryPageMethods().cancelItineraryButton();
        pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();

        //Storing refund values for next validation
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpCancelReservationButton().click();
        String refund1 = pageObjectManager.getCancelReservationPage().getCreditSummaryValesAmountText().getText().replace(",","");
        double refund = Double.parseDouble(refund1.replace("$",""));
        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(CARD_TYPE);

        //verify booking has been cancelled
        ValidationUtil.validateTestStep("Verify cancel confirmation verbiage appear in Sub Header of Cancel reservation Page",
                pageObjectManager.getCancelReservationPage().getCancellationSubHeaderText().get(FIRST_INDEX).getText(),CANCELLATION_CONFIRMATION);

        //verify card name
        ValidationUtil.validateTestStep("Validating credit refunded to Credit Card",
                pageObjectManager.getCancelReservationPage().getCreditSummaryValuesTypeText().getText().equals("Credit Card"));

        ValidationUtil.validateTestStep("Verify Card Name in credit summary  of Cancel reservation Page",
                pageObjectManager.getCancelReservationPage().getCreditSummaryValuesValidThruText().getText().trim(),cardDetailsData.cardName);

        ValidationUtil.validateTestStep("Validating refund price:  $" + refund + " equals to the paid amount " + TotalPrice, TotalPrice == refund);
    }
}
