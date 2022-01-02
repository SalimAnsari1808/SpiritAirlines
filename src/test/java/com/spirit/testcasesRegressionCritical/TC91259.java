package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91259
//Description: Task 24878: 35369 Uplift_CP_BP_Flight Only_Booking more than $200
//Created By : Gabriela
//Created On : 1-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 2-Aug-2019
//**********************************************************************************************

public class TC91259 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "Connecting" , "BundleIt" , "CarryOn" , "CheckedBags" ,"BagsUI", "Standard" ,"FlightFlex","ShortCutBoarding","CheckInOptions", "OptionalUI" , "Visa" , "PaymentUI"})
    public void Uplift_CP_BP_Flight_Only_Booking_more_than_$200(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91259 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CLE";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "40";
        final String ARR_DATE           = "44";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Price";
        final String DEP_FLIGHT         = "Connecting";
        final String RET_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Bags Page Constant Values
        final String CARRY_ON_INCLUDED  = "Included";

        //Seats Page Constant Values
        final String DEP_SEATS          = "Standard||Standard";
        final String RET_SEATS          = "Standard||Standard";

        //Options Page Constant Values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String PAYMENT_URL        = "/book/payment";
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

//-- Step 1: Access test environment
        //open browser
        openBrowser(platform);

        /*****************************************Home Page Methods*****************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//-- Step 2: Start booking a Flight only
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*****************************************Flight Availability Page Methods*****************************************/
//-- Step 3: Select the a flight less than $200 and continue to the next page

        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);

        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("RET", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//-- Step 4: Select "Bare Fare" option by clicking "Continue with Bare Fare" button
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*****************************************Passenger Info Page Methods*****************************************/
//-- Step 5: Enter Customer information and continue to the next page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /*****************************************Bags Page Methods*****************************************/
//-- Step 6: Carry on bag should be preselected.
        for (int count = 0; count < pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().size(); count ++) {
            ValidationUtil.validateTestStep("Validating Carry on is included due Bundle It option selection",
                    pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(count).getText(), CARRY_ON_INCLUDED);
        }

        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();

        /*****************************************Seats Page Methods*****************************************/
//-- Step 7: Select a seat and continue to the next page
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /*****************************************Options Page Methods*****************************************/
//-- Step 8: Scroll down the Options page and validate there are options to pay monthly for the Cars, Hotels or Activities offered. The attached image will show what you should see
        for (int count = 0; count <  pageObjectManager.getCarPage().getCarsPanel().size(); count++) {
            if (pageObjectManager.getCarPage().getCarsPanel().get(count).isDisplayed()) {
                ValidationUtil.validateTestStep("Verifying car uplift is displayed under car price line",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(count)));
            }
        }

        for (int count = 0; count <  pageObjectManager.getHotelPage().getHotelPanel().size(); count++) {
            if (pageObjectManager.getHotelPage().getHotelPanel().get(count).isDisplayed()) {
                ValidationUtil.validateTestStep("Verifying Hotel uplift is displayed under car price line",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().get(count)));
            }
        }

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);

//-- Step 9: Continue to the next page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*****************************************Options Page Methods*****************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify Payment Page URL",
                getDriver().getCurrentUrl(),PAYMENT_URL);

//-- Step 10: Verify there is an option to pay monthly under the Payment Information section
        ValidationUtil.validateTestStep("Option to pay monthly under the Payment Information section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDuePayMonthlyText()));

//-- Step 11: Verify the following sections are present:
        //Vouchers and Credits
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Voucher section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getVoucherNumberTextBox()));

        ValidationUtil.validateTestStep("Reservation Credit section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getRedeemReservationCreditConfirmationCodeText()));

        //Credit card under Payment Information
        ValidationUtil.validateTestStep("Credit card under Payment Information is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox()));

//-- Step 12: Finish the booking by making a credit card payment
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /***********************************Confirmation Page Method**************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}