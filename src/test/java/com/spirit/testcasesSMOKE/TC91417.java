package com.spirit.testcasesSMOKE;


import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91417
//Description: CP_BP_Payment Page_Guest_use fraudulent CC
//Created By : Sunny Sok
//Created On : 12-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 17-Apr-2019
//**********************************************************************************************
public class TC91417 extends BaseClass{

    @Parameters({"platform"})
    @Test (groups = {"OutOfExecution","BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "PaymentUI"})
    public void CP_BP_Payment_Page_Guest_use_fraudulent_CC(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91417 under SMOKE Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LGA";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Nonstop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_FARE		= "BookIt";

        //Options Page constant values
        final String OPTION_VALUE 		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String INVALID_CARD_TYPE 	= "HotCard";
        final String VALID_CARD_TYPE    = "MasterCard";

        //open browser
        openBrowser( platform);

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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait until Bags page appear on web
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //options
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         **********************Validation Payment Page************************
         ******************************************************************************/
        //Constant Values to validate
        final String PAYMENT_URL        = "/book/payment";
        final String FRAUD_CARD_ERROR   = "Were sorry, an unknown error has occurred. Your card has not been charged. Please check that all payment information below matches what is printed on your card (including cardholder name) or try another card.";

        ValidationUtil.validateTestStep("User verify Navigated to Payment page",
                getDriver().getCurrentUrl().contains(PAYMENT_URL));

        //fill invalid card details
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(INVALID_CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        WaitUtil.untilPageLoadComplete(getDriver());

        String CardError = pageObjectManager.getCommon().getIBlockVerbiageText().getText();
        System.out.println(CardError);
        CardError = CardError.replaceAll("[^\\p{ASCII}]", "");
        System.out.println(CardError);

        ValidationUtil.validateTestStep("User Verify error message for Hot Card",
               CardError,FRAUD_CARD_ERROR);

        //clear invalid card information
        pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().clear();
        pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().clear();

        //ebter valid card information
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(VALID_CARD_TYPE);
        pageObjectManager.getPaymentPage().getBookTripButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //confirmation page
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);



//	TestCase fails in NAV01!!!!!!!

    }

}
