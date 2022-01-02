package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC90983
//Description: CP_BP_Payment Method_Voucher_Neg
//Created By : Salim Ansari
//Created On : 3-MAY-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 3-MAY-2019
//**********************************************************************************************

public class TC90983 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "FSMasterCard" ,
            "NonStop","BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "PaymentUI"})
    public void CP_BP_Payment_Method_Voucher_Neg(@Optional("NA") String platform){
        //******************************************************************************
        //****************************Navigate to Payment Page**************************
        //******************************************************************************/
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC90983 under SMOKE suite on " + platform +" Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE                   = "English";
        final String LOGIN_EMAIL 		        = "FSMCEmail";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE                  = "oneway";
        final String ARR_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "LAX";
        final String DEP_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "LAS";
        final String DEP_DATE                   = "25";
        final String ARR_DATE                   = "NA";
        final String ADULTS                     = "1";
        final String CHILDS                     = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		        = "Autoselect";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_FARE               = "BookIt";
        final String JOURNEY                    = "Dep";

        //Option Page Constant Value
        final String SELECTED_OPTIONS           = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GURAD		        = "NotRequired";
        final String CARD_TYPE			        = "MasterCard";
        final String ERROR_MESSAGE_LESS_DIGIT   = "Please enter valid voucher number";
        final String ERROR_MESSAGE_17_DIGIT     = "The voucher number entered is not valid. Please try to enter the number again";

//Step 1
        //open browser
        openBrowser(platform);
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(SELECTED_OPTIONS);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment page Methods
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GURAD);
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditLink().click();
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys("spirit1234567890");
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("",pageObjectManager.getPaymentPage().getRedeemVoucherErrorMessageText().getText().trim(),ERROR_MESSAGE_LESS_DIGIT);
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().clear();
        WaitUtil.untilTimeCompleted(2000);

        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys("spirit&1234567890");
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("",pageObjectManager.getPaymentPage().getRedeemVoucherErrorMessageText().getText().trim(),ERROR_MESSAGE_17_DIGIT);
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().clear();
        WaitUtil.untilTimeCompleted(2000);

        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys("qaepic1234567890");
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("",pageObjectManager.getPaymentPage().getRedeemVoucherErrorMessageText().getText().trim(),ERROR_MESSAGE_LESS_DIGIT);
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys("");



        /*pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //login to checkIn Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageObjectManager.getReservationSummaryPage().getCancelItineraryButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpCancelReservationButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        String voucherNum = pageObjectManager.getCancelReservationPage().getCreditSummaryVoucherNumberText().getText();
        pageObjectManager.getCancelReservationPage().getContinueToHomePageButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getSpiritLogoImage().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getBookPathLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());*/

    }
}
