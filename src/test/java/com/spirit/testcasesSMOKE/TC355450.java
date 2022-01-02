package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
// Test Case ID: TC355450
// Description: E2E_Guest_OW DOM 1 ADT 1 INFT (Lap)_[Tier 3] Thru Flight_Military_Bundle Bags_Bundle Seats_No Extras CI Web_Credit Card
// Created By : Sunny Sok
// Created On : 20-May-2019
// Reviewed By: Salim Ansari
// Reviewed On: 21-May-2019
//**********************************************************************************************
public class TC355450 extends BaseClass {
    /******************************************************************************
     ***********************Navigation to Purchase Page****************************
     ******************************************************************************/
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "InfantLap" , "Military" ,
                     "NonStop" , "CarryOn" , "CheckedBags" , "BagsUI" , "Standard" , "ShortCutBoarding" , "OptionalUI" ,
                     "AmericanExpress","PassengerInformationUI"})
    public void E2E_Guest_OW_DOM_1_ADT_1_INFT_Lap_Tier_3_Thru_Flight_Military_Bundle_bags_Bundle_Seats_No_Extras_CI_Web_Credit_Card (@Optional("NA")String platform){
        /******************************************************************************
         *********************Navigation to Confirmation Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC355450 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "ATL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "5";
        final String ARR_DATE 			= "NA";
        final String ADULT				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "1";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Nonstop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE		= "BundleIt";

        //bags page constant
        final String BLUE_COLOR2        = "rgb(0, 115, 230)";
        final String BAGS_URL           = "book/bags";
        final String DEP_BAGS 			= "Carry_1|Checked_2";
        final String ZERO_BAG_PRICE 	= "$0.00";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "NotRequired";
        final String CARD_TYPE			= "AmericanExpressCard";

//STEP--1
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
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--2 & STEP--3
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //select Military Bags
        pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton().click();

        //wait for 30 sec
        WaitUtil.untilTimeCompleted(500);

        //validate Military bags are selected
        ValidationUtil.validateTestStep("Verify Military Bags are selected on Military and Bundle popup on Passenger info Page",
                JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton(), "background-color"),BLUE_COLOR2);

        //click on continue button
        pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupContinueButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //wait till url appear on web
        WaitUtil.untilPageURLVisible(getDriver(), BAGS_URL);

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

        //set flag to true for departing carry-on bags
        boolean statusFalg = true;

        //loop through all departing carry-on bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().size();bagsCounter++) {
            //check carry-on Bag price for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
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

        /********End of 1-Carry-On and 2-Checked Bags are Free For Military***************/

        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats("Standard");
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Option Page Methods
        ValidationUtil.validateTestStep("Verify Shortcut Boarding is Selected on Option Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        ValidationUtil.validateTestStep("Verify Shortcut Boarding Remove button is not visible on Option Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardRemoveButton()));

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Purchase Page Methods
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