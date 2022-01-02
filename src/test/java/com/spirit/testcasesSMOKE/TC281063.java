package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281063
//Test Name: Task 24031: 31530 039. E2E_Guest_OW INT 1 ADT 1 INF (Lap)_Thru Flight_Bundle It [Tier 3]_Military_Military Bags_No Seats_No Extras CI Web_Credit Card
//Description: Bag Validation for military passenger on an INT booking
//Created By : Gabriela
//Created On : 21-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 23-MAY-2019
//**********************************************************************************************

public class TC281063 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticInternational" , "WithIn7Days" , "Adult" , "InfantLap" , "Military"
                    , "Through" , "BundleIt","PassengerInformationUI" , "CarryOn" , "CheckedBags" , "BagsUI" , "NoSeats" , "OptionalUI" ,
                     "PaymentUI" , "AmericanExpress"})
    public void E2E_Guest_OW_INT_1_ADT_1_INF_Lap_Thru_Flight_Bundle_It_Tier_3_Military_Military_Bags_No_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281063 under SMOKE Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "LGA";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "CUN";
        final String DEP_DATE 			= "5";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "1";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Connecting";
        final String FARE_TYPE 			= "Standard";
        final String UPGRADE_VALUE 		= "BundleIt";

        //Passenger Info Page Methods
        final String BLUE_COLOR2        = "rgb(0, 115, 230)";

        //Bags Page Method
        final String BAG_URL            = "book/bags";
        final String DEP_BAGS           = "Carry_1|Checked_2";
        final String ZERO_BAG_PRICE 	= "$0.00";

        //Payment Page Constant Values
        final String MILITARY_INFO      = "Thank you for your service";
        final String CARD_TYPE          = "AmericanExpressCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Value
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Page Method
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*************************************************************************************************
         * *****************************Passenger Info Page Method****************************************
         ************************************************************************************************/
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
        ValidationUtil.validateTestStep("Verify Military Bags are selected on Military and Bundle popup on Passenger info Page", JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton(), "background-color"),BLUE_COLOR2);

        //click on continue button
        pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupContinueButton().click();

        /*************************************************************************************************
         * ************************************Bags Page Methods******************************************
         ************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL", getDriver().getCurrentUrl(),BAG_URL);

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


        /*************************************************************************************************
         * ***********************************Seats Page Methods******************************************
         ************************************************************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*************************************************************************************************
         * *********************************Options Page Methods******************************************
         ************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
//        ValidationUtil.validateTestStep("Validating Check In dropdown option is not available when an Infant on Lap is present",
//                !pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown().isEnabled());

        ValidationUtil.validateTestStep("Verify Check-In Option is disabled on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*************************************************************************************************
         * *******************************Payment Page Methods********************************************
         ************************************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating the right info is displayed after military verification",
                pageObjectManager.getPaymentPage().getActiveMilitaryThankYouText().getText().trim(),MILITARY_INFO);

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);


        /*************************************************************************************************
         * *******************************Confirmation Page Methods***************************************
         ************************************************************************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    }
}