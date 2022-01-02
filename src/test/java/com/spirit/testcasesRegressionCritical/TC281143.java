package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281143
//Test Case Name:Task 24144: 31589 184. E2E_Guest_RT INT MAX PAX MIX_SW Change Month Date, Bundle It [Tier 3] Fare, Thru Flight_PAX 3 Voluntary Emergency Services_Add Bags_Bundle It [Tier 3] Seats_No Extras, CI Web_Discover
//Created By : Gabriela
//Created On : 05-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Jun-2019
//**********************************************************************************************

public class TC281143 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticInternational" , "Outside21Days" , "Adult" , "Child" ,
                     "Guest" , "NewFlightSearch" , "Through" , "BundleIt" , "PassengerInfoSSR" , "CarryOn" ,
                     "CheckedBags" , "Bikes" , "SurfBoard" , "BagsUI" , "Standard" , "SeatsUI" , "FlightFlex" ,
                     "ShortCutBoarding" , "CheckInOptions" , "Discover","DynamicShoppingCartUI","ConfirmationUI"})
    public void E2E_Guest_RT_INT_MAX_PAX_MIX_SW_Change_Month_Date_Bundle_It_Tier_3_Fare_Thru_Flight_PAX_3_Voluntary_Emergency_Services_Add_Bags_Bundle_It_Tier_3_Seats_No_Extras_CI_Web_Discover(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281143 under REGRESSION CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "EWR";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "CUN";
        final String DEP_DATE 			= "2";
        final String ARR_DATE 			= "5";
        final String ADULT  			= "6";
        final String CHILD  			= "3";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_DATE_1			= "45";
        final String ARR_DATE_1			= "49";
        final String DEP_FLIGHT 		= "Through";
        final String RET_FLIGHT 		= "Through";
        final String UPGRADE_VALUE      = "BundleIt";

        //Bags Page Constant Values
        final String BAGS_PRESELECTED 	= "1";
        final String BAGS_INCLUDED		= "Included";
        final String BAGS_FARE          = "Standard";

        //Seats page Constant Values
        final String DEP_SEATS			= "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";
        final String RET_SEATS			= "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //Options Page Constant Value
        final String OPTION_VALUE 		= "CheckInOption:MobileFree";
        final String OPTIONS_VALIDATION = "FlightFlex|ShortCutBoarding";

        //payment page constant value
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";

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
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1, ARR_DATE_1);

        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);
        pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().get(2).click();
        pageObjectManager.getPassengerInfoPage().getVoluntaryProvisionofEmergencyServicesProgramListCheckBox().get(2).click();

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ****************************Bags Page Methods*****************************
         ****************************************************************************/
        //Validating City Pair are right
        ValidationUtil.validateTestStep("Validating departure city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),DEP_AIRPORT_CODE);

        ValidationUtil.validateTestStep("Validating arriving city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),ARR_AIRPORT_CODE);

        //ValidationUtil.validateTestStep("Verify 1-Carry-On and 1-Checked Bags are Pre-Selected for all passengers on Bags Page
        for(int i = 0; i < pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().size(); i ++) {
            // Verifying Carry-on is preselected
            ValidationUtil.validateTestStep("Verifying Carry On is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(i)).contains(BAGS_PRESELECTED));

            //Verifying Carry On price is included
            ValidationUtil.validateTestStep("Verifying Carry On price is included",
                    pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(i).getText().equals(BAGS_INCLUDED));

            // Verifying Checked Bag is preselected
            ValidationUtil.validateTestStep("Verifying 1 checked bag is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(i)).contains(BAGS_PRESELECTED));

            //Verifying Checked Bag price is included
            ValidationUtil.validateTestStep("Verifying Checked Bag price is included",
                    pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(i).getText().equals(BAGS_INCLUDED));
        }
        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);
        //Selecting 4 bikes for pax 1
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(0).click();
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count <= 4; count ++){
            pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(0).click();
        }

        //Selecting 4 Surf for pax 2
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(1).click(); //open sporting equipment
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count <= 4; count ++){
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(1).click();
        }

        //Selecting Max Bags for pax 3
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count <= 4; count ++){
            pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(2).click();
        }

        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);

        /****************************************************************************
         * ************************Seats Page Methods********************************
         ****************************************************************************/
        //Selecting Seats for Departure Flight
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,DEP_SEATS,RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         * **************************Options Page Methods****************************
         ****************************************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ************************Payment Page Method **********************************
         ******************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(OPTIONS_VALIDATION);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        /******************************************************************************
         ************************Confirmation Page Method******************************
         ******************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(OPTIONS_VALIDATION);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}