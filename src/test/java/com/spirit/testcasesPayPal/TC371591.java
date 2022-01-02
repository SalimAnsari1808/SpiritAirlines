package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC371591
//Description: Validate_refund_goes_back_to_PayPal_when_the_flight_is_cancelled_within_24_hrs_of_booking_and_departure_date_is_outside_of_7_days
//Created By : Manasa Tilakraj
//Created On : 10-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC371591 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"NineDFC", "MyTrips", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Connecting", "BookIt",
            "CheckedBags", "NoSeats", "CheckInOptions", "MasterCard", "AddEditBags"})
    public void Validate_refund_goes_back_to_PayPal_when_the_flight_is_cancelled_within_24_hrs_of_booking_and_departure_date_is_outside_of_7_days(@Optional("NA") String platform) {
        /******************************************************************************
         ***********************Navigate to Confirmation Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC371591 under PayPal Suite on " + platform + " Browser", true);
        }
        //Step 1: Pre Req:
        //        Have a PNR outside of 24 hrs
        //        Have a paypal account with funds.

        //Create PNR
        //Home Page Constant Values
        final String LANGUAGE = "English";
        final String JOURNEY_TYPE = "Flight";
        final String TRIP_TYPE = "OneWay";
        final String DEP_AIRPORTS = "AllLocation";
        final String DEP_AIRPORT_CODE = "LGA";
        final String ARR_AIRPORTS = "AllLocation";
        final String ARR_AIRPORT_CODE = "FLL";
        final String DEP_DATE = "9";
        final String ARR_DATE = "NA";
        final String ADULT = "1";
        final String CHILD = "0";
        final String INFANT_LAP = "0";
        final String INFANT_SEAT = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT = "NonStop";
        final String FARE_TYPE = "Standard";
        final String UPGRADE_VALUE_1 = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS = "Carry_0|Checked_0";

        //Options Page Constant Values
        final String OPTIONS_VALUE = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE = "DiscoverCard1";
        final String TRAVEL_GUARD = "NotRequired";
        final String PAYPAL_ACCOUNT     = "AccountPayPalEmail";
        final String PAYPAL_TYPE        = "Credit Union";

        //Cancellation Confirmation Page Constant Values
        final  String CANCEL_CONFIRM_URL    = "my-trips/cancel-reservation";
        final int FIRST_INDEX = 0;
        final String CANCELLATION_CONFIRMATION = "Your reservation has been cancelled and refunded to the original form of payment as shown below, and an email with details has been sent to:";


        //Home Page Methods
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE_1);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        //No valid payment in going through in Paypal
        pageObjectManager.getPaymentPage().getPayPalButton().click();
        pageMethodManager.getPaymentPageMethods().loginToPayPal(PAYPAL_ACCOUNT);
        pageMethodManager.getPaymentPageMethods().payWithPayPal(PAYPAL_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        String PNR = scenarioContext.getContext(Context.CONFIRMATION_CODE).toString();
        System.out.println(PNR);

        //Step 2: Go to Manage Travel path

        //Go to manage travel
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Step 3: Proceed to Cancel reservation
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getReservationSummaryPage().getCancelItineraryButton().click();

        //Wait till url is loaded
        WaitUtil.untilPageLoadComplete(getDriver());

        //Navigate to Cancellation Confirmation Page
        WaitUtil.untilPageURLVisible(getDriver(), CANCEL_CONFIRM_URL);

        //verify user navigated to cancel reservation summary page
        ValidationUtil.validateTestStep("User redirected to the Cancellation Confirmation Page",
                getDriver().getCurrentUrl(),CANCEL_CONFIRM_URL);

        //Step 4: Validate the amount is being refunded to the PayPal account


        //Step 5: Proceed with cancellation
        pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();
        //click on cancel reservation on Reservation Cancellation PopUp
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpCancelReservationButton().click();

        //verify booking has been cancelled
        ValidationUtil.validateTestStep("Verify cancel confirmation verbiage appear in Sub Header of Cancel reservation Page",
                pageObjectManager.getCancelReservationPage().getCancellationSubHeaderText().get(FIRST_INDEX).getText(),CANCELLATION_CONFIRMATION);

        //verify email occur in the cancellation verbiage
        ValidationUtil.validateTestStep("Verify cancel confirmation email appear in Sub Header of Cancel reservation Page",
                pageObjectManager.getCancelReservationPage().getCancellationSubHeaderText().get(FIRST_INDEX).getText(),(scenarioContext.getContext(Context.CUSTOMER_EMAIL).toString()));

        //TODO: acces skyspeed and validate refund


    }
}
