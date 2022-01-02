package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC369523
//Test Name: CP_BP_Payment_Page_Voucher_Error_Messages
//Description: Validate error message displayed each case an incorrect Voucher Info is entered on payment page
//Created By : Gabriela
//Created On : 3-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 6-MAY-2019
//**********************************************************************************************

public class TC369523 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath","OneWay","DomesticDomestic","WithIn7Days","Adult","Guest","NonStop","BookIt","NoBags",
                    "NoSeats","CheckInOptions","MasterCard","Voucher","PaymentUI"})
    public void CP_BP_Payment_Page_Voucher_Error_Messages(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC369523 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Booking Path Constant variables
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "Oneway";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "BOS";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "FLL";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "NA";
        final String ADULTS                 = "1";
        final String CHILDREN               = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "Nonstop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        final String ALFA_NUM_ERROR         = "The voucher number entered is not valid. Please try to enter the number again.";
        final String INVALID_ERROR_MESSAGE  = "The voucher number was not found.";
        final String INVALID_ERROR_MESSAGE_1= "The voucher number entered is not found. Please try to enter the number again.";
        final String LENGTH_ERROR_MESSAGE_2 = "Please enter valid voucher number.";

        //open browser and redirect tot the application
        openBrowser(platform);

        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //create voucher
        createVoucher();

        //--Step 1: Land on Payment page with a valid voucher
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        String PaxName =  pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getText();
        String PaxLastName = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getText();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //--Step 2
        ValidationUtil.validateTestStep("Departure flight is displayed",
                pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).isDisplayed());

        ValidationUtil.validateTestStep("Passenger name is correct",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText().contains(PaxName + PaxLastName));

        //--Step 3: Click on Redeem Vouchers
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditLink().click();


        //--Step 4: Enter a invalid voucher number: Enter Non-Alpha Numeric number into the voucher textbox
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys("!@#$%^*$*-@#%!+()");
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();

        ValidationUtil.validateTestStep("Validating error message for Non-Alpha numeric entered",
                pageObjectManager.getCommon().getErrorMessageLabel().getText().equals(ALFA_NUM_ERROR));
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().clear();


        //--Step 5: Enter Voucher Number doesn't exist
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys("12345678912345678");
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();

        boolean message =  false;
        for (int i = 0; i < 1; i ++)
        {
         if ( pageObjectManager.getCommon().getErrorMessageLabel().getText().contains(INVALID_ERROR_MESSAGE))
         {
             message = true;
         }
         if (pageObjectManager.getCommon().getErrorMessageLabel().getText().contains(INVALID_ERROR_MESSAGE_1))
         {
             message = true;
         }
        }

        ValidationUtil.validateTestStep("Validating error message for invalid voucher number entered", message==true);

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().clear();


        //--Step 6: Enter Voucher Number with less than 17 digits
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys("1234567891234");
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();

        ValidationUtil.validateTestStep("Validating error message for incorrect voucher length",
                pageObjectManager.getCommon().getErrorMessageLabel().getText().equals(LENGTH_ERROR_MESSAGE_2));
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().clear();

        //TODO Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web return null names and are then invalid for use
        // Step 7: Enter a valid Voucher number
       pageMethodManager.getPaymentPageMethods().applyVoucherNumber();

        //Step 8: Enter invalid information (text box (IE: inputs alpha characters)

    }
    private void createVoucher() {
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
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
        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";
        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_CARD        = "NotRequired";
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //Flight Availability Methods
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
        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_CARD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
    }

}