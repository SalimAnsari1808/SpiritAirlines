package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC374877
//Title       : CP_BP_Payment Page_Res Credit_Error Messages
//Description : Validate incorrect Rescredit information input on payment page
//Created By  : Anthony Cardona
//Created On  : 04-Apr-2019
//Reviewed By : Salim Ansari
//Reviewed On : 18-Apr-2019
//**********************************************************************************************
public class TC374877 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "ReservationCredit" , "PaymentUI"})
    public void CP_BP_Payment_Page_Res_Credit_Error_Messages(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374877 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Booking Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GAURD       = "Notrequired";
        final String ERROR_MESSAGE      = " The Confirmation Code could not be located. ";
//        final String ERROR_MESSAGE      = "The Confirmation Code you entered could not be located. Please verify your entry and try again.";

        //open browser and redirect tot the application
        openBrowser(platform);

        pageMethodManager.getHomePageMethods().launchSpiritApp();

//Step 1
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

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Start of Manage TRavel
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageObjectManager.getReservationSummaryPage().getCancelItineraryButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpCancelReservationButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        double resCredit  = Double.parseDouble(pageObjectManager.getCancelReservationPage().getCreditSummaryValesAmountText().getText().trim().replace("$",""));

        pageObjectManager.getCancelReservationPage().getContinueToHomePageButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getSpiritLogoImage().click();
        pageObjectManager.getHomePage().getBookPathLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 2
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

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 3
        //click on Redeem Vouchers
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditLink().click();

//Step 4
        //Enter Non-Alpha Numeric number into the res credit textbox
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys("!@#$%^");
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        getConfrimationCodeError(ERROR_MESSAGE);

//Step 5
        //input invalid PNR that does not exist
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys("123ABC");
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        getConfrimationCodeError(ERROR_MESSAGE);

//Step 6
        //input valid PNR and INVALID amount
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();

        //add $1 to resCredit and pass that to the input field, convert to String
        resCredit = resCredit + 1;

        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().clear();
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().sendKeys(TestUtil.convertTo2DecimalValue(resCredit)); //passString
        pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton().click();

        //validate error for out of range amount
        ValidationUtil.validateTestStep("The number range error is correct" , pageObjectManager.getPaymentPage().geRedeemVoucherInvalidNumberRangeText().isDisplayed());

        //validate error for invalid inpur "$%^@#$"
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().clear();
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().sendKeys("$%^@#$"); //passString
        pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton().click();

        //Validate that an invalid input error is displaying
//TODO There is an open bug that is blocking from checking this functionality (Bug: 23451)
// (http://miaptfs01.spirit.local/tfs/acapella/Spirit.com%20Remix/_workitems?id=23451&_a=edit)

    }

    private void getConfrimationCodeError (String errorMessage)
    {
        //declare constant
        WaitUtil.untilTimeCompleted(1200);

        //validate the error message
        ValidationUtil.validateTestStep("The Error message is displayed correctly" , getDriver().findElement(By.xpath("//div[contains(@class,'error-text')]")).getText(),errorMessage);

        //close the error message
//        pageObjectManager.getCommon().getIBlockCloseButton().click();

        //clear PNR text box
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().clear();

    }
}