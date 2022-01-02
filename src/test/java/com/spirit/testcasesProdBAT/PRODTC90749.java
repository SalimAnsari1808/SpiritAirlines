package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90749
//Test Case Name:Task 24687: 35323 CP_BP_Payment Page_Terms & Conditions
//Created By: Gabriela
//Created On: 2-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 4-Jul-2019
//**********************************************************************************************

public class PRODTC90749 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void CP_BP_Payment_Page_Terms_Conditions(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90749 under PRODUCTION Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "ORD";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "10";
        final String ADULTS                 = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTION_VALUE 	        = "CheckInOption:DecideLater";

        //Payment page constant values
        final String PAYMENT_URL            = "/book/payment";
        final String TERMS_COND_TITLE       = "Terms and Conditions";
        final String HAZMAT_TERMS_COND      = "I agree to terms and conditions including the HAZMAT restrictions.*";
        final String BOOK_BUTTON            = "BOOK TRIP";
        final String ERROR                  = "You must agree to the Terms and Conditions in order to complete your reservation.";
        final String WHITE_RGB_VALUE        = "RGB(255, 255, 255)";
        final String CARD_TYPE              = "AmericanExpressCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS         = "Confirmed";
        final String CONFIRMATION_URL       = "book/confirmation";

        //open browser
        openBrowser(platform);

//-- Step 1:
        /******************************************************************************
         *******************************Navigate to Payment Page***********************
         *********************RT DOM flight outside of 24HRs***************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
//        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ***************************Validation on Payment Page********************
         ******************************************************************************/
        ValidationUtil.validateTestStep("Validating Payment Page right URL",
                getDriver().getCurrentUrl(),PAYMENT_URL);

//-- Step 2:
        ValidationUtil.validateTestStep("Validating Terms & Conditions title",
                pageObjectManager.getPaymentPage().getTermsAndConditionsHeader().getText() ,TERMS_COND_TITLE);

        ValidationUtil.validateTestStep("Validating Terms & Conditions is not selected by default ",
                !getDriver().findElement(By.id("termsCheck")).isSelected());
        
        String selectedCheckBoxRGB = JSExecuteUtil.getElementBeforeProperty(getDriver(),pageObjectManager.getPaymentPage().getTermsAndConditionsLabel(),"background-color");

        ValidationUtil.validateTestStep("Validating Terms & Conditions is not selected by default",selectedCheckBoxRGB,WHITE_RGB_VALUE);

        ValidationUtil.validateTestStep("Validating Terms & Conditions checkbox text",
                pageObjectManager.getPaymentPage().getTermsAndConditionsLabel().getText(), HAZMAT_TERMS_COND);

        ValidationUtil.validateTestStep("Validating book button text", pageObjectManager.getPaymentPage().getBookTripButton().getText(),BOOK_BUTTON);

//-- Step 3:
        pageObjectManager.getPaymentPage().getBookTripButton().click();

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("Validating error displayed right info", pageObjectManager.getPaymentPage().getTermsAndConditionsErrorMessageText().getText(),ERROR);

//-- Step 4
        //finish payment process
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().travelGuardRecommendedPopUp();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//
//        /*************************************************************************************************************
//         * *********************************Confirmation Page Method**************************************************
//         *************************************************************************************************************/
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        //verify booking is confirmed
//        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
//                getDriver().getCurrentUrl(),CONFIRMATION_URL);
//
//        //verify booking is confirmed
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}