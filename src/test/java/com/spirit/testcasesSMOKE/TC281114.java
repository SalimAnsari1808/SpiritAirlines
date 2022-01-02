package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
// Test Case ID: TC281114
// Description: E2E_Guest_OW DOM 1 ADT_Direct Flight_Military_Military bags_Any Seats_No Extras CI Web_Credit Card
// Created By : Salim Ansari
// Created On : 16-Apr-2019
// Reviewed By: Kartik Chauhan
// Reviewed On: 16-Apr-2019
//**********************************************************************************************
//Bug: IN:24071 Military Verification failing on Payment page using IE as browser
/**10/21/2019 Removed ActiveBug tag**/

public class TC281114 extends BaseClass {
    /******************************************************************************
     ***********************Navigation to Purchase Page****************************
     ******************************************************************************/
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Military" ,
            "NonStop" , "BundleIt" , "CarryOn" , "CheckedBags" , "Standard" , "TravelInsurancePopUp" , "MasterCard" ,
            "BagsUI" , "PaymentUI" , "OptionalUI","CheckInOptions"})
    public void E2E_Guest_OW_DOM_1_ADT_Direct_Flight_Military_Military_bags_Any_Seats_No_Extras_CI_Web_Credit_Card (@Optional("NA")String platform){
        /******************************************************************************
         *********************Navigation to Confirmation Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281114 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "DTW";
        final String DEP_DATE 			= "23";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "nonstop";
        final String ARR_FLIGHT 		= "nonstop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE		= "BundleIt";

        //bags page constant
        final String BLUE_COLOR2        = "rgb(0, 115, 230)";
        final String BAGS_URL           = "book/bags";
        final String DEP_BAGS 			= "Carry_1|Checked_2";
        final String ZERO_BAG_PRICE 	= "$0.00";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "Required";
        final String CARD_TYPE			= "MasterCard";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--2 & STEP--3
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //select Military Bags
        pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton().click();

        //wait for 30 sec
        WaitUtil.untilTimeCompleted(500);

        //validate Military bags are selected
        ValidationUtil.validateTestStep("Verify Military Bags are selected on Military and Bundle popup on Passenger info Page", JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton(), "background-color"),BLUE_COLOR2);

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

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
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
        final String BOOKING_STATUS = "Confirmed";
        final String CONFIRMATION_URL = "book/confirmation";

        //confirmation page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl().contains(CONFIRMATION_URL));

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));

    }


}
