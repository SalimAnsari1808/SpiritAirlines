package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC371589
//Description: Validate_the_MT_PAYPAL_comment_displays_in_Skyspeed_when_payment_is_made_with_PayPal_on_Manage_Travel
//Created By : Manasa Tilakraj
//Created On : 09-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC371589 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"NineDFC", "MyTrips", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Connecting", "BookIt",
            "CheckedBags", "NoSeats", "CheckInOptions", "MasterCard", "AddEditBags"})
    public void Validate_the_MT_PAYPAL_comment_displays_in_Skyspeed_when_payment_is_made_with_PayPal_on_Manage_Travel(@Optional("NA") String platform) {
        /******************************************************************************
         ***********************Navigate to Confirmation Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC371589 under PayPal Suite on " + platform + " Browser", true);
        }
        //Step 1: Pre Req:
        //        Have a PNR outside of 24 hrs
        //        Have a paypal account with funds.

        //Create PNR
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LGA";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "3";
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

        //My Trip bags page
        final String MANAGE_TRAVEL_BAGS = "Carry_1|Checked_1";

        //My Trip Reservation page constant value
        final String MYTRIP_BUY_BAGS    = "Bags";
        final String MYTRIP_SEAT_POPUP  = "DontPurchase";

        final String PAYPAL_TYPE        = "Credit Union";

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
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        String PNR = scenarioContext.getContext(Context.CONFIRMATION_CODE).toString();
        System.out.println(PNR);

        //Step 2: Go via the Manage Travel path and proceed to add bags and or seats so you are required a payment.

        //Go to manage travel
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //add Bags
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_BUY_BAGS);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().selectDepartingBags(MANAGE_TRAVEL_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(MYTRIP_SEAT_POPUP);
//      pageObjectManager.getReservationSummaryPage().getSeatsPopupDontPurchaseMySeatsButton().click();

        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getPaymentPage().getTotalDueChevronLink());

        //Step 3: Total due Validation
        //ValidationUtil.validateTestStep("Validating dollar amount is displayed",
        //       pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().equals());

        //Step 4 & 5: Verify there is a "Checkout with PayPal" Button and click on it to proceed with the PayPal Payment
        ValidationUtil.validateTestStep("PayPal Button is showing on the checkout page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayPalButton()));
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getPayPalButton().click();

        //Step 6: Complete the booking with the PayPal Option
        pageMethodManager.getPaymentPageMethods().loginToPayPal(PAYPAL_ACCOUNT);
        pageMethodManager.getPaymentPageMethods().payWithPayPal(PAYPAL_TYPE);

        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Step 7: Access Skyspeed and validate PayPal payment was processed and displaying properly under Payment summary section.
        //Step 8: Validate the comment under Comments Summary Section.


    }
}