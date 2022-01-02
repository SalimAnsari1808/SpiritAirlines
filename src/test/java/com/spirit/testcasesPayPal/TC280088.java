package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280088
//Description: PayPal - CP: BP: Payment Page: Validate booking payment with PayPal and a Voucher Credit
//Created By: Un Fai Chan
//Created On: 12/9/2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC280088 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = { "CheckIn" , "Guest" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NonStop",
            "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "VisaCard" , "HomeUI"  , "PayPal" , "Voucher"})
    public void Validate_booking_payment_with_PayPal_and_a_Voucher_Credit(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to CheckHome Page*********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280088 under Paypal Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE = "English";
        final String JOURNEY_TYPE = "Flight";
        final String TRIP_TYPE = "OneWay";
        final String DEP_AIRPORTS = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS = "AllLocation";
        final String ARR_AIRPORT_CODE = "LGA";
        final String DEP_DATE = "0";
        final String ARR_DATE = "NA";
        final String ADULTS = "1";
        final String CHILDREN = "0";
        final String INFANT_LAP = "0";
        final String INFANT_SEAT = "0";

        // Flight details constant values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //open browser
        openBrowser(platform);
        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        // Step 1: Have or create a voucher credit for the customer you will be using for the booking
        // Have a paypal account with funds for the customer you will be using
        createVoucher();

        // Step 2: On the web, start booking a OW DOM as a Guest for  1 Pax, no bags, no seats, no extras and land on the Payment page
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        // Step 3: Scroll down to the TOTAL DUE section, click on "Redeem voucher or credit"
        // Step 4: Enter the Voucher Credit number and pay only for a portion of the booking.  Note: Make sure to leave a balance to be paid
        pageMethodManager.getPaymentPageMethods().applyVoucherNumber();

        // Step 5: Verify there is a "Check out with PayPal" Button and click on it to proceed with the PayPal Payment
        ValidationUtil.validateTestStep("PayPal Button is showing on the checkout page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayPalButton()));
        pageObjectManager.getPaymentPage().getPayPalButton().click();

        // Step 6: Complete the booking with the PayPal Option
        // TODO:: Complete this step once PayPal checkout option is available

        // Step 7: Check PNR in Skyspeed and validate the comment UATP PayPal is displaying on the reservation
        // TODO:: Cannot be automated at this moment

        // Step 8: Validate the PayPal payment is displaying properly under the Payment Section in Skyspeed along with the voucher payment
        // TODO:: Cannot be automated at this moment

        // Step 9: Validate the comment under Comment Summary Section. "Comment should display as follow: BP PAYPAL $[amount paid]
        // TODO:: Cannot be automated at this moment

    }

    private void createVoucher() {
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAX";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";


        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        //MyTrip Page
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}
