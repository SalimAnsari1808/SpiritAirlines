package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373127
//Description: Payment Page: Validate_PayPal_option_is_available_for_award/miles_FS_OW_DOM_booking_within_21_180 days - iOS
//Created By : Manasa Tilakraj
//Created On : 10-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373127 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"NineDFC", "MyTrips", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Connecting", "BookIt",
            "CheckedBags", "NoSeats", "CheckInOptions", "MasterCard", "AddEditBags"})
    public void Validate_PayPal_option_is_available_for_award_miles_FS_OW_DOM_booking_within_21_180_days(@Optional("NA") String platform) {
        /******************************************************************************
         ***********************Navigate to Confirmation Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC37127 under PayPal Suite on " + platform + " Browser", true);
        }
        //Step 1: Pre Req:
        //        Have a PNR outside of 24 hrs
        //        Have a paypal account with funds.

        //Step 2: start booking a OW DOM as a FS member for  1 Pax, no bags, no seats, no extras and land on the Payment page
        // Create PNR
        //Home Page Constant Values
        final String LANGUAGE = "English";
        final String LOGIN_ACCOUNT = "FSEmail";
        final String JOURNEY_TYPE = "Flight";
        final String TRIP_TYPE = "OneWay";
        final String DEP_AIRPORTS = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS = "AllLocation";
        final String ARR_AIRPORT_CODE = "LAS";
        final String DEP_DATE = "77"; //Check if the date has miles flight
        final String ARR_DATE = "NA";
        final String ADULT = "1";
        final String CHILD = "0";
        final String INFANT_LAP = "0";
        final String INFANT_SEAT = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT = "NonStop";
        final String FARE_TYPE = "Standard";
        final String UPGRADE_VALUE = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS = "Carry_0|Checked_0";

        //Options Page Constant Values
        final String OPTIONS_VALUE = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE = "DiscoverCard1";
        final String TRAVEL_GUARD = "NotRequired";
        final String PAYPAL_ACCOUNT     = "AccountPayPalEmail";
        final String PAYPAL_TYPE        = "Credit Union";

        //Home Page Methods
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);


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

        // Step 3: Scroll down to the TOTAL DUE section and verify the amount due is MILES + Dollars
        /* Active bug, MILES + DOLLARS is missing*/


        //Step 4: Verify there is a "Checkout with PayPal" Button and click on it
        //Step 5: Click the PayPal button and proceed with the login and payment selection within the PayPal modal
        ValidationUtil.validateTestStep("PayPal Button is showing on the checkout page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayPalButton()));
        pageObjectManager.getPaymentPage().getPayPalButton().click();

        pageMethodManager.getPaymentPageMethods().loginToPayPal(PAYPAL_ACCOUNT);
        pageMethodManager.getPaymentPageMethods().payWithPayPal(PAYPAL_TYPE);

        //Step 6: Complete the booking with the PayPal Option
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        String PNR = scenarioContext.getContext(Context.CONFIRMATION_CODE).toString();
        System.out.println(PNR);



    }
}