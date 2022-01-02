package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281206
//Test Name: Task 24110: 31555 129. E2E_Guest_RT DOM 1 ADT 1 INFT (Lap)_Thru Flight Boost It (Tier 2)_Military_Military Bags_Standard Seats_No Extras CI Web_Credit Card
//Created By: Gabriela
//Created On : 29-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-May-2019
//**************************************************************************************************


public class TC281206 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "InfantLap" , "Military" , "Through" ,
                    "NonStop" , "CarryOn" , "CheckedBags" , "BagsUI" , "Standard" , "ShortCutBoarding" , "OptionalUI" ,
                    "AmericanExpress" , "PaymentUI","BoostIt"})
    public void E2E_Guest_RT_DOM_1_ADT_1_INFT_Lap_Thru_Flight_Boost_It_Tier_2_Military_Military_Bags_Standard_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281206 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "DEN";
        final String DEP_DATE 			= "2";
        final String ARR_DATE 			= "5";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "1";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Through";
        final String RET_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE      = "BoostIt";

        //Bags Page constant value
        final String DEP_BAGS           = "Carry_1|Checked_2";
        final String ZERO_BAG_PRICE 	= "$0.00";

        //Seat Page Constant
        final String DEP_SEAT           = "Standard|Standard";

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
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //select Military Bags
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton().click();

        //click on continue button
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupContinueButton().click();

        /*************************************************************************************************
         * ************************************Bags Page Methods******************************************
         ************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

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
         * ************************************Seats Page Methods*****************************************
         ************************************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);

        //Continue to Options page
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /*************************************************************************************************
         * *********************************Options Page Methods******************************************
         ************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating check in options are unable when infant on the booking",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*************************************************************************************************
         * *********************************Payment Page Methods******************************************
         ************************************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating the right info is displayed after military verification",
                pageObjectManager.getPaymentPage().getActiveMilitaryThankYouText().getText(),MILITARY_INFO);

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