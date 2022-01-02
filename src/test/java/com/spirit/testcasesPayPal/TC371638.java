package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.managers.PageObjectManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC371638
//Description: Payment_Page_Validate_the_replacement_of_the_PayPal_Checkout_button_when_PayPal_Review_is_successful
//Created By : Manasa Tilakraj
//Created On : 10-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC371638 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"FSEmail", "MyTrips", "OneWay", "DomesticDomestic", "Adult", "Connecting", "BookIt",
            "CheckedBags", "NoSeats", "CheckInOptions", "MasterCard", "AddEditBags"})
    public void Payment_Page_Validate_the_replacement_of_the_PayPal_Checkout_button_when_PayPal_Review_is_successful(@Optional("NA") String platform) {
        /******************************************************************************
         ***********************Navigate to Confirmation Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC371638 under PayPal Suite on " + platform + " Browser", true);
        }
        //Step 1: Pre Req:
        //        Have a PNR outside of 24 hrs
        //        Have a paypal account with funds.

        //Step 2: start booking a OW DOM as a FS member for  1 Pax, no bags, no seats, no extras and land on the Payment page
        // Create PNR
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "2";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE_1    = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_0|Checked_0";

        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";
        final String PAYPAL_ACCOUNT     = "AccountPayPalEmail";

        final String PAYPAL_TYPE = "MasterCard";
        final String PAYPAL_TYPE1 = "Visa";


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

        //Step 3: Verify there is a "Checkout with PayPal" Button and click on it
        ValidationUtil.validateTestStep("PayPal Button is showing on the checkout page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayPalButton()));
        pageObjectManager.getPaymentPage().getPayPalButton().click();

        //Step 4: Enter credentials to login to the PayPal account.
        pageMethodManager.getPaymentPageMethods().loginToPayPal(PAYPAL_ACCOUNT);
        pageMethodManager.getPaymentPageMethods().payWithPayPal(PAYPAL_TYPE);

        //Step 5: Validate the PayPal Checkout button has been replaced with a PayPal Acceptance Mark selected
        ValidationUtil.validateTestStep("Validating Paypal is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().get_PayPalDisplay()));

        //Step 6: Click the "Update" link from the PayPal Acceptance Mark and validate the PayPal button is re-displayed.
        pageObjectManager.getPaymentPage().get_PayPalUpdateLink().click();

        ValidationUtil.validateTestStep("PayPal Button is showing again",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayPalButton()));

        //Step 7: Click the PayPal button and validate you are automatically logged in to the PayPal account.
        pageObjectManager.getPaymentPage().getPayPalButton().click();
        System.out.println("worked");
        //pageMethodManager.getPaymentPageMethods().loginToPayPal(PAYPAL_ACCOUNT);
        WaitUtil.untilTimeCompleted(3000);

        //pageMethodManager.getPaymentPageMethods().payWithPayPal1(PAYPAL_TYPE1);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();









    }
}