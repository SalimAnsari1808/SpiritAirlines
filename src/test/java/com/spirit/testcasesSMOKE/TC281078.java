package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC281078
//Description: Task 24037: 31536 054. E2E_Guest_OW DOM 1 ADT_SW Change Date Connecting Flight_Military_Military Bags_No Seats_No Extras CI Web_Travel Guard, Credit Card
//Created By : Gabriela
//Created On : 21-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-MAY-2019
//**********************************************************************************************

public class TC281078 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "Military" , "NewFlightSearch"
            , "Connecting" , "BookIt" , "CarryOn" , "CheckedBags" , "BagsUI" , "NoSeats" , "ShortCutBoarding" , "CheckInOptions"
            , "TravelInsuranceBlock" , "Visa" , "PaymentUI"})
    public void E2E_Guest_OW_DOM_1_ADT_SW_Change_Date_Connecting_Flight_Military_Military_Bags_No_Seats_No_Extras_CI_Web_Travel_Guard_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281078 under SMOKE Suite on " + platform + " Browser", true);
        }
        //declare variable used in navigation
        boolean statusFlag = true;

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "LAX";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LGA";
        final String DEP_DATE 			= "5";
        final String ARR_DATE 			= "NA";
        final String ADULT				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String NEW_DEP_DATE 	    = "15";
        final String DEP_FLIGHT 		= "Connecting";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_FARE		= "BookIt";

        //Bags Page Constant Values
        final String BAG_URL            = "book/bags";
        final String DEP_BAGS           = "Carry_1|Checked_2";
        final String ZERO_BAG_PRICE 	= "$0.00";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String MILITARY_INFO      = "Thank you for your service";
        final String CARD_DETAIL 		= "VisaCard";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectDates(NEW_DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL", getDriver().getCurrentUrl(),BAG_URL);

        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

        //set flag to true for departing carry-on bags
        //loop through all departing carry-on bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().size();bagsCounter++) {
            //check carry-on Bag price for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFlag = false;
            }
        }

        //Verify Departing Carry-On Bag prices
        ValidationUtil.validateTestStep("Verify Departing Carry-On Bag prices is zero on Bags Page", statusFlag);

        //set flag to true for departing checked bags
        statusFlag = true;

        //loop through all departing checked bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().size();bagsCounter++) {
            //check checked bag bag prices for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFlag = false;
            }
        }

        //Verify Departing Checked Bag prices
        ValidationUtil.validateTestStep("Verify Departing Checked Bag prices is zero on Bags Page", statusFlag);

        /********End of 1-Carry-On and 2-Checked Bags are Free For Military***************/

        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        /****************Military Autentication*************/
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating the right info is displayed after military verification",
                pageObjectManager.getPaymentPage().getActiveMilitaryThankYouText().getText().trim(),MILITARY_INFO);
        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPaymentPage().getYesTravelGuardLabel().click();

        /*****Travel Guard Validation*******/
        List<String> textBeingValidated = new ArrayList<String>();
        textBeingValidated.add("100% Trip Cost Cancellation");
        textBeingValidated.add("125% Trip Cost Trip Interruption");
        textBeingValidated.add("$500 Missed Connection");
        textBeingValidated.add("$500 Trip Delay");
        textBeingValidated.add("$500 Baggage & Personal Effects");

        //method to check all the verbiages and link under TG section on payment page
        pageMethodManager.getPaymentPageMethods().travelGuardVerbiagesAndLink(textBeingValidated);

        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getPaymentPageMethods().calculateTravelGuard();

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);

        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/

        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    }
}