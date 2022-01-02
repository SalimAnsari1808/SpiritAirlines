package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280089
//Description: PayPal - CP: BP: Payment Page: Validate the PayPal Acceptance suppresses the pre-filled Billing Information Section
//Created By: Un Fai Chan
//Created On: 12/10/2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC280089 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Flight", "WithIn7Days", "Adult", "FreeSpirit",
            "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "ReservationUI", "PayPal"})
    public void PayPal_CP_BP_Payment_Page_Validate_the_PayPal_Acceptance_suppresses_the_pre_filled_Billing_Information_Section(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280089 under PayPal Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        // Flight details constant values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page constant values
        final String CARD_DETAIL        = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Step 1: You need a paypal with funds
        //Step 2: On the web, start booking a OW DOM as a FS member for  1 Pax, no bags, no seats, no extras and land on the Payment page
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

        //payment page methods
        //Step 3: Select "No" to the Travel Guard Section
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

        //Step 4: Scroll down to the Credit Card Section and input the Credit Card information.
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);

        //Step 5: Click the "Check out with PayPal" button and proceed with the PayPal login process.
        pageObjectManager.getPaymentPage().getPayPalButton().click();


        // TODO:: complete the following steps once PayPal option is available
        //Step 6: Go over the Terms and Conditions
        //Step 7: IGNORE the following steps if testing in production. No PNR is needed to validate this functionality
        //Complete the booking with the PayPal Option
        //Step 8: Check PNR in Skyspeed and validate the PayPal comment is displaying on the reservation
        //Step 9: Validate the PayPal payment is displaying properly under the Payment Section in Skyspeed
    }
}
