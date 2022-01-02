package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC371592
//Description: PayPal - CP: BP: Payment Page: Validate the consumer is redirected back to Payment page when the PayPal modal is voluntarily closed without processing the payment
//Created By: Un Fai Chan
//Created On: 12/9/2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC371592 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = { "CheckIn" , "Guest" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NonStop",
            "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "VisaCard" , "HomeUI"  , "PayPal"})
    public void Validate_the_consumer_is_redirected_back_to_Payment_page_when_the_PayPal_modal_is_voluntarily_closed_without_processing_the_payment(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to CheckHome Page*********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC371592 under Paypal Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
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

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String CARD_DETAIL 		= "VisaCard";

        // Step 1: Have a paypal account with funds
        // Step 2: On the web, start booking a OW DOM as a Guest for  1 Pax, no bags, no seats, no extras and land on the Payment page

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
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
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        // Step 3: Verify there is a "Checkout with PayPal" Button and click on it to proceed with the login
        ValidationUtil.validateTestStep("PayPal Button is showing on the checkout page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayPalButton()));
        pageObjectManager.getPaymentPage().getPayPalButton().click();


        // Step 4: Without completing the Paypal payment, close the modal.
        // TODO:: close the PayPal Popup

        // Step 5: Complete the booking with a Visa credit Card
        WaitUtil.untilTimeCompleted(5000);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        // Step 6: Validate the Visa payment is displaying properly under the Payment Section in Skyspeed
        // TODO:: implement automation for Skyspeed
    }
}
