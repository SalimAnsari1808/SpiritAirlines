package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
// Test Case ID: TC280879
// Description: E2E_FSMC_OW DOM 2 ADT_SW Change PAX 3 ADT, Bundle It [Tier 3] ComboThru Flight_Military_Military Bags_No Seats_No Extras CI Web_Credit Card
// Created By : Sunny Sok
// Created On :
// Reviewed By: Salim Ansari
// Reviewed On: 23-May-2019
//**********************************************************************************************
public class TC280879 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FSMasterCard" , "Military"
                   , "NewFlightSearch" , "Connecting" , "CarryOn" , "CheckedBags" , "BagsUI" , "NoSeats" , "ShortCutBoarding" ,
                     "CheckInOptions" , "OptionalUI" , "AmericanExpress"})
    public void E2E_FSMC_OW_DOM_2_ADT_SW_Change_PAX_3_ADT_Bundle_It_Tier_3_ComboThru_Flight_Military_Military_Bags_No_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA")String platform){
        /******************************************************************************
         *********************Navigation to Confirmation Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280879 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_ACCOUNT      = "MilitaryFreeSpiritMasterCard";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "LAS";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "5";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "2";
        final String CHILD  			= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String NEW_ADULT			= "3";
        final String DEP_FLIGHT 		= "Connecting";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE		= "BundleIt";

        //bags page constant
        final String BAGS_URL           = "book/bags";
        final String DEP_BAGS 			= "Carry_1|Checked_2||Carry_1|Checked_2||Carry_1|Checked_2";
        final String ZERO_BAG_PRICE 	= "$0.00";

        //Options page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "NotRequired";
        final String CARD_TYPE			= "AmericanExpressCard";

//STEP--1
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(NEW_ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--2 & STEP--3
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        pageMethodManager.getPassengerInfoMethods().selectMilitaryBagBundlePopup("MilitaryBags");

        //wait till url appear on web
        WaitUtil.untilPageURLVisible(getDriver(), BAGS_URL);

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

        //set flag to true for departing carry-on bags
        boolean statusFlag = true;

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

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        ValidationUtil.validateTestStep("Verify Shortcut Boarding is Selected on Option Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        ValidationUtil.validateTestStep("Verify Shortcut Boarding Remove button is not visible on Option Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardRemoveButton()));

        //Option page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Puchase Page Methods
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //confirmation page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    }
}