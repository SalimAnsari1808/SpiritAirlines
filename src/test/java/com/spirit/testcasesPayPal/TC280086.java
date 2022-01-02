package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280086
//Description: PayPal - CP: BP: Payment Page: Validate PayPal option is available for award/miles $9FC - RT - DOM booking within 0 - 6 days
//Created By: Un Fai Chan
//Created On: 12/9/2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC280086 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Flight", "WithIn7Days", "Adult", "NineDFC",
            "NonStop", "BookIt", "NoBags","NoSeats","CheckInOptions","Visa","ReservationUI","PayPal"})
    public void Payment_Page_Validate_PayPal_option_is_available_for_award_miles_$9FC_RT_DOM_booking_within_0_6_days(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280086 under PayPal Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "2";
        final String ARR_DATE           = "4";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        // Flight details constant values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        // Step 1: "On the web, start booking a RT DOM Miles booking as a $9FC member for  1 Pax, no bags, no seats, no extras and land on the Payment page
        // Important: Select departure date within 0 - 6 days"
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Log in as a 9DFC member
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Ret", RET_FLIGHT);
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

        // Step 2: Scroll down to the TOTAL DUE section and verify the amount due is MILES + Dollars
        /* Active bug, MILES + DOLLARS is missing*/

//        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
//        pageObjectManager.getPaymentPage().getTotalDueFlightText().click();

        // Step 3: Under the TOTAL DUE section, verify there is a "Checkout with PayPal" button displaying
        ValidationUtil.validateTestStep("PayPal Button is showing on the checkout page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayPalButton()));

        // Step 4: Click the PayPal button and proceed with the login and payment selection within the PayPal modal
        pageObjectManager.getPaymentPage().getPayPalButton().click();

        // TODO:: Complete steps below once PayPal checkout option is available
        // Step 5: Complete the booking with the PayPal Option
        // Step 6: Check PNR in Skyspeed and validate the PayPal comment is displaying on the reservation
        // Step 7: Also under the Comments section, verify the Redemption fee applied is $75
        // Step 8: Validate the PayPal payment is displaying properly under the Payment Section in Skyspeed along with the miles used.
        // Step 9: Go to the Breakdown and verify the Award redemption fee is properly charged and that the proper Fee code is showing. 

    }
}
