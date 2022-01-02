package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC375022
//Description: Flight Only: Payment_Page_Complete_a_Flight_only_booking_and_pay_with_PayPal in spanish
//Created By : Manasa Tilakraj
//Created On : 10-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC375022 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Flight", "WithIn7Days", "Adult", "NineDFC",
            "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "ReservationUI", "PayPal"})
    public void Payment_Page_Complete_a_Flight_only_booking_and_pay_with_PayPal(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC375022 under PayPal Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "GUA";
        final String DEP_DATE           = "7";
        final String ARR_DATE           = "15";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        // Flight details constant values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        final String PAYPAL_ACCOUNT     = "AccountPayPalEmail";
        final String PAYPAL_TYPE        = "Credit Union";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        // Step 1: "On the web, start booking a RT DOM Miles booking as a $9FC member for  1 Pax, no bags, no seats, no extras and land on the Payment page
        // Important: Select departure date within 7 - 20 days"
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
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

        //Step 3: Continue to the Payment page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Step 4: Under the TOTAL DUE section, verify there is a "Checkout with PayPal" button displaying
        ValidationUtil.validateTestStep("PayPal Button is showing on the checkout page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayPalButton()));

        //Step 5:Click the PayPal button and proceed with the login and payment selection within the PayPal modal
        pageObjectManager.getPaymentPage().getPayPalButton().click();
        pageMethodManager.getPaymentPageMethods().loginToPayPal(PAYPAL_ACCOUNT);
        WaitUtil.untilTimeCompleted(1000);
        pageMethodManager.getPaymentPageMethods().payWithPayPal(PAYPAL_TYPE);

        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        //Step 6: Take screenshot of payment page

        // Step 7: Complete booking and verify confirmation page
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        String PNR = scenarioContext.getContext(Context.CONFIRMATION_CODE).toString();
        System.out.println(PNR);
    }
}
