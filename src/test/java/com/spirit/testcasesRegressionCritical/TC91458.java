package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91458
//Test Case Name:CP_CI_Validating Forms of Payment_CreditCard
//Created By: Sunny Sok
//Created On: 16-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 17-Jul-2019
/**10/21/19 test case passed, removed active bug tag**/
//**********************************************************************************************

public class TC91458 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,"AddEditBags", "CheckedBags" , "NoSeats" , "CheckInOptions" , "AmericanExpress" , "PaymentUI","AddEditBags"})
    public void CP_CI_Validating_Forms_of_Payment_CreditCard(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91458 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "ORD";
        final String DEP_DATE               = "0";
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
        final String OPTION_VALUE 	         = "CheckInOption:DecideLater";

        //Bags Page constant values
        final String DEP_BAGS               = "Carry_0|Checked_5";

        //Seats pop up
        final String SEATS                  = "DontPurchase";

        //Payment page constant values
        final String PAYMENT_URL            = "/check-in/payment";
        final String CARD_TYPE              = "AmericanExpressCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS         = "Confirmed";
        final String CONFIRMATION_URL       = "check-in/reservation-summary";


        //open browser
        openBrowser(platform);

        //-- Step 1:
        /******************************************************************************
         ***********************Navigate to Check-in Payment Page**********************
         ******************************************************************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
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

        //Payment Page Method
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        pageMethodManager.getHomePageMethods().loginToCheckIn();

        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection("Bags");
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(SEATS);

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ***************************Validation on Payment Page*************************
         ******************************************************************************/
        ValidationUtil.validateTestStep("Validating Payment Page right URL",
                getDriver().getCurrentUrl(),PAYMENT_URL);

        //validating Card Number text box and badges
        ValidateCardNumberTxbx("MasterCard", "MASTER-CARD");

        ValidateCardNumberTxbx("VisaCard", "VISA-CARD");

        ValidateCardNumberTxbx("DiscoverCard1", "DISCOVER-CARD");

        ValidateCardNumberTxbx("AmericanExpressCard", "AMERICAN-EXPRESS");

        //-- Step 4
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().travelGuardRecommendedPopUp();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/

        WaitUtil.untilPageLoadComplete(getDriver());

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }

    //Method for Validation Card Number Text box
    private void ValidateCardNumberTxbx(String CardType, String CreditCardBadge){
        //clearing Card number text box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox());
        //get card details
        CardDetailsData CardTypeDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(CardType);

        //send card details
        pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().sendKeys(CardTypeDetailsData.cardNumber);

        //validation credit card badge
        ValidationUtil.validateTestStep("Validating badge for " +CardType,
                pageObjectManager.getMemberEnrollmentPage().getCardTypeImage().getAttribute("class"), CreditCardBadge);


    }
}