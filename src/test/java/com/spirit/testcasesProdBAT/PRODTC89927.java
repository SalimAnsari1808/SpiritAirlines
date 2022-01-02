package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC281152
//Description:  110. E2E_Guest_MC DOM 1 ADT 1 INFT LAP_Direct Fligt_Military_Military Bags_No Seats_No Extras CI Web_Visa
//Created By:   Sunny Sok
//Created On:   12-Aug-2019
//Reviewed By:  Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************

public class PRODTC89927 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")
    public void E2E_Guest_MC_DOM_1_ADT_1_INFT_LAP_Direct_Fligt_Military_Military_Bags_No_Seats_No_Extras_CI_Web_Visa(@Optional("NA") String platform) {
        /******************************************************************************
         ******************************Navigate to Purchase Page***********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC89927 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "BOS|FLL";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL|BOS";
        final String DEP_DATE           = "5|8";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_2";
        final String RET_BAGS           = "Carry_1|Checked_2";
        final String BAGS_PRICES        = "$0.00";
        final String BAGS_FARE          = "Standard";

        //payment page constant value
        final String PAYMENT_URL        = "/payment";
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        /******************************************************************************
         *******************************Home Page Method********************************
         ******************************************************************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(DEP_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);

        ValidationUtil.validateTestStep("Carry on for free validation for military passenger on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(0).getText(),BAGS_PRICES);

        ValidationUtil.validateTestStep("2 checked bags for free validation for military passenger on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(0).getText(),BAGS_PRICES);

        ValidationUtil.validateTestStep("Carry on for free validation for military passenger on Bags Page",
                pageObjectManager.getBagsPage().getReturningCarryOnPriceText().get(0).getText(),BAGS_PRICES);

        ValidationUtil.validateTestStep("2 checked bags for free validation for military passenger on Bags Page",
                pageObjectManager.getBagsPage().getReturningCheckedBagPriceText().get(0).getText(),BAGS_PRICES);

        ValidationUtil.validateTestStep("Validating Bags Total Price displayed is 0.00 on Bags Page",
                pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText(),BAGS_PRICES);

        pageObjectManager.getHeader().getYouItineraryImage().click();

        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("The price in the Dynamic Shopping cart is right",
                pageObjectManager.getHeader().getBagsPriceItineraryText().getText(),BAGS_PRICES);

        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);

        //Seats page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User verify Navigated to Payment page",
                getDriver().getCurrentUrl(), PAYMENT_URL);

        //Verifying Military Bags in 0.00 and redirecting for military verification
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPaymentPage().getTotalDueBagsChevronLink().click();
        WaitUtil.untilTimeCompleted(2000);
        ValidationUtil.validateTestStep("Validating Bags Price is 0.00 on Payment page",
                pageObjectManager.getPaymentPage().getTotalDueBagsPriceText().getText(),BAGS_PRICES);
//        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//
//        /******************************************************************************
//         *************************Validation on Confirmation Page**********************
//         ******************************************************************************/
//        WaitUtil.untilPageLoadComplete(getDriver());
//        //Confirmation Code
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}