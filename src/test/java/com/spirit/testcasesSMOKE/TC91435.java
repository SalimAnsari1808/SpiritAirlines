package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

// **********************************************************************************************
// Test Case ID: TC91435
// Test Name   : CP_BP_Payment Page_Travel Guard_DOM_FS
// Description : For FS member, verify all Travel Guard prices for Domestic flight on Payment page
// Created By  : Kartik Chauhan
// Created On  : 13-May-2019
// Reviewed By : Salim Ansari
// Reviewed On : 15-May-2019
// **********************************************************************************************
public class TC91435 extends BaseClass {

    @Parameters ({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "FreeSpirit" , "NonStop" ,
                     "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "PaymentUI" , "MasterCard" , "TravelInsuranceBlock","ConfirmationUI", "ActiveBug"})
    public void CP_BP_Payment_Page_Travel_Guard_DOM_FS(@Optional("NA")String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91435 under SMOKE Suite on " + platform + " Browser", true);
        }

        //BUG!!!!
        //[IN:24461] CP: BP: Payment Page PMT: As a FS Member with a saved credit card, and choosing 'Yes' for travel guard, the payment block disappears completely, when only the saved card info should be suppressed so the user must enter a new card

        /******************************************************************************
         ****************************Navigate to Passenger Info Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_EMAIL 		= "FSSavedCards";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "Oneway";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "10";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        final String JOURNEY            = "Dep";

        //Option page constant
        final String OPTION_VALUE		= "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

//Step1-
        //open browser
        openBrowser( platform);

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

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP--2
        //Select NO in TG
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

//STEP--3
        List<String> textBeingValidated = new ArrayList<String>();
        textBeingValidated.add("100% Trip Cost Cancellation");
        textBeingValidated.add("125% Trip Cost Trip Interruption");
        textBeingValidated.add("$500 Missed Connection");
        textBeingValidated.add("$500 Trip Delay");
        textBeingValidated.add("$500 Baggage & Personal Effects");

        //Verify All section under TG section
        pageMethodManager.getPaymentPageMethods().travelGuardVerbiagesAndLink(textBeingValidated);
//STEP--4
        //verify Credit card Dropdown is displaying after clicking on Yes
        ValidationUtil.validateTestStep("Credit card Dropdown is displaying after clicking on Yes" ,
                pageObjectManager.getPaymentPage().getSelectCardDropDown().isDisplayed());

//STEP--5
        //Now click on YES under TG section
        pageObjectManager.getPaymentPage().getYesTravelGuardLabel().click();
//STEP--6
        //Method to calculate TG amount in Total Due section
        //To be ADDED after Discussion***************************************************

        //complete payment
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//STEP--7
        /******************************************************************************
         ***********************Validation to Confirmation Page****************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //Confirmation Page Methods
        //close ROKT pop-up
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl().contains(CONFIRMATION_URL));

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));
//STEP--6
        //Verify TG is Calculated successfully on Confirmation Page
        pageMethodManager.getConfirmationPageMethods().verifyTravelInsuranceSection();
    }
}