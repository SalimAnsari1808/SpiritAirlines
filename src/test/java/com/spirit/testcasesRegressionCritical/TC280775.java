package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.*;

//**********************************************************************************************
//TODO bUG 22587 CP: BP: being brought back to devepic01.spirit.com after ID.ME verification
//TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
/**10/21/19 test case passed, removed active bug tag**/
//Test Case ID: TC280775
//Test Name: Task 24221: 31620 342. E2E_FS_RT DOM 1 ADT_SW Change Date Book It [Tier 1] Connecting Flight_MIL_RT 1CO 2CB_No Seats_No Extras_CI Web_Travel Guard_Credit Card
//Created By : GabrielaTC280779
//Created On : 05-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Jun-2019
//**********************************************************************************************

public class TC280775 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" ,"Military" , "FSMiles" ,
                     "NewFlightSearch" , "Connecting" , "BookIt" , "CarryOn" , "CheckedBags" ,"BagsUI" , "NoSeats" ,
                     "ShortCutBoarding", "CheckInOptions" , "TravelInsuranceBlock" , "MasterCard" ,"PaymentUI"})
    public void E2E_FS_RT_DOM_1_ADT_SW_Change_Date_Book_It_Tier_1_Connecting_Flight_MIL_RT_1CO_2CB_No_Seats_No_Extras_CI_Web_Travel_Guard_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280775 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "MilitaryFSMiles";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "CLE";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAX";
        final String DEP_DATE 			= "2";
        final String ARR_DATE 			= "5";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_DATE_1			= "5";
        final String ARR_DATE_1			= "9";
        final String FARE_TYPE          = "Standard";
        final String DEP_FLIGHT 		= "Connecting";
        final String RET_FLIGHT 		= "Connecting";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Vales
        boolean statusFalg              = true;
        final String BAG_URL            = "/book/bags";
        final String DEP_BAGS           = "Carry_1|Checked_2";
        final String RET_BAGS           = "Carry_1|Checked_2";
        final String ZERO_BAG_PRICE 	= "$0.00";

        //Option Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1, ARR_DATE_1);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().get(0).click();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL", getDriver().getCurrentUrl(),BAG_URL);

        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);

        //loop through all departing carry-on bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().size();bagsCounter++) {
            //check carry-on Bag price for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFalg = false;
            }
        }

        //set flag to true for returning carry-on bags
        statusFalg = true;

        //Verify Departing Carry-On Bag prices
        ValidationUtil.validateTestStep("Verify Departing Carry-On Bag prices is zero on Bags Page", statusFalg);

        //loop through all returning carry-on bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getReturningNextCarryOnPriceText().size();bagsCounter++) {
            //check carry-on Bag price for all passenger
            if(!pageObjectManager.getBagsPage().getReturningCarryOnPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFalg = false;
            }
        }

        //Verify Departing Carry-On Bag prices
        ValidationUtil.validateTestStep("Verify Departing Carry-On Bag prices is zero on Bags Page", statusFalg);

        //set flag to true for departing checked bags
        statusFalg = true;

        //loop through all departing checked bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().size();bagsCounter++) {
            //check checked bag bag prices for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFalg = false;
            }
        }

        //Verify Departing Checked Bag prices
        ValidationUtil.validateTestStep("Verify Departing Checked Bag prices is zero on Bags Page", statusFalg);

        //set flag to true for returning checked bags
        statusFalg = true;

        //loop through all returning checked bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getReturningCheckedBagPriceText().size();bagsCounter++) {
            //check checked bag bag prices for all passenger
            if(!pageObjectManager.getBagsPage().getReturningCheckedBagPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFalg = false;
            }
        }

        //Verify Departing Checked Bag prices
        ValidationUtil.validateTestStep("Verify Departing Checked Bag prices is zero on Bags Page", statusFalg);

        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        //TODO bUG 22587 CP: BP: being brought back to devepic01.spirit.com after ID.ME verification
        //TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        //Validating Travel Insurance information
        List<String> textBeingValidated = new ArrayList<String>();
        textBeingValidated.add("100% Trip Cost Cancellation");
        textBeingValidated.add("125% Trip Cost Trip Interruption");
        textBeingValidated.add("$500 Missed Connection");
        textBeingValidated.add("$500 Trip Delay");
        textBeingValidated.add("$500 Baggage & Personal Effects");

        //Verify All section under TG section
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getPaymentPageMethods().travelGuardVerbiagesAndLink(textBeingValidated);

        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getPaymentPage().getYesTravelGuardLabel().click();

        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getPaymentPageMethods().calculateTravelGuard();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/
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