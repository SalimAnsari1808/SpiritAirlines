package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.CardDetailsData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91412
//Test Name: Task 23304: 31453 CP_BP_Payment Page_ FS_save card for future booking with voucher
//Description: Validating that the customer is allowed to save a credit card using the checkbox from Payment page
//Created By : Gabriela
//Created On : 14-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 16-MAY-2019
//**********************************************************************************************

public class TC91412 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FreeSpirit" , "NonStop", "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "Visa" , "Voucher" , "PaymentUI" , "AccountProfileUI","ActiveBug"})
    public void CP_BP_Payment_Page_FS_save_card_for_future_booking_with_voucher(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91412 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE1 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE1 	= "LAS";
        final String DEP_DATE 			= "4";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        /******************************************************************************
         **************FS new member Creation without a CC saved***********************
         ******************************************************************************/
        pageObjectManager.getHomePage().getSignInListLink().get(1).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getSignUpNowLink().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getMemberEnrollmentPageMethods().createNewFSMember();

        pageObjectManager.getHomePage().getSpiritLogoImage().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getBookPathLink().click();

        //create voucher
        createVoucher();

        /******************************************************************************
         *****************************Test Case Scenario*******************************
         ******************************************************************************/
//-- Step 1: Perquisites Book a Flight with any number of PAX continue to the payment page. Make sure you make the
// booking with a FS member and that you have a voucher redemption Note: Make sure voucher does not cover entire total
// of the flight and make sure your FS member does not have a credit card saved already
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE1, ARR_AIRPORTS, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        String name = pageObjectManager.getSeatsPage().getPassengerNameText().get(0).getText();
//        System.out.println("name " + name);
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        String Name1 = pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText().replace("MR. ", "");
        ValidationUtil.validateTestStep("Validating the right information displayed on passenger section",
                name.equals(Name1));

        //Step 5: Input a valid Voucher
        //-- Step 6: Verify the pricing on the total due by clicking the drop down
        pageMethodManager.getPaymentPageMethods().applyVoucherNumber();

        //--Step 7: Enter payment information
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);

        //Saving CC information for future comparision
        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(CARD_TYPE);

        String card = cardDetailsData.requestType;
        System.out.println("card " + card);

        String date1 = cardDetailsData.expirationDate;
        System.out.println("date1 " + date1);

        String card1 = cardDetailsData.cardNumber.substring(cardDetailsData.cardNumber.length()-4);
        System.out.println("card1 " + card1);

        //TODO: Bug 24074: CP: BP: Payment Page: "Save this card for future bookings" checkbox
        // suppressed when the member logged in doesn't have a valid credit card already saved in the account profile
        //--Step 8: Click the "Save this card for future bookings" check box
        pageObjectManager.getPaymentPage().getPaymentSectionSaveCardCheckBox().click();

        //--Step 9: Complete your payment and retrieve your PNR
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));

        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //--Step 10: Click your members name on the top right corner
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getUserDropDown().click();
        pageObjectManager.getHeader().getMyAccountUserLink().click();

        //--Step 11: Click the "Edit Billing Information" link on the left side of the page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getLeftMenuBillingInformationLink().click();

        //-- Step 12: Verify that your card was saved

        ValidationUtil.validateTestStep("Validating right name was saved",
                pageObjectManager.getAccountProfilePage().getBillingInformationAddCardAccountHolderNameTextBox().getText().equals(name));

        ValidationUtil.validateTestStep("Validating right CC Type information",
                pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardTypeText().getText().equals(card));

        ValidationUtil.validateTestStep("Validate the right Expiration date",
                pageObjectManager.getAccountProfilePage().getBillingInformationAddCardExpMonthYearTextBox().getText().replace("20","").equals(date1));

        String cardNumb1 = pageObjectManager.getAccountProfilePage().getBillingInformationAdditionalCardNumberText().get(0).getText();
        String cardNumb = cardNumb1.substring(cardNumb1.length()-4);
        System.out.println(cardNumb);
        ValidationUtil.validateTestStep("", cardNumb.equals(card1));
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