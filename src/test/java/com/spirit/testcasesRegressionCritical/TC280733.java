package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280733
//Test Case Name: Task 24229: 31627 360. E2E_FS_RT INT 2 ADT_SW Change to 3 ADT Bundle Fare Red Eye Flight_Need help to and from seat_1Pax 1CO 1CB 2Pax 1CO 1 BIKE 1SB 3Pax No Bags _3 BFS Seats_ALL Extras_CI Agent_Discover
//Note: No Red Eye available with saving options offered after log in as member on web
//Created By : Gabriela
//Created On : 03-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 04-Jun-2019
//**********************************************************************************************

public class TC280733 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "ColombiaDomestic" , "WithIn21Days" , "Adult" ,"NewFlightSearch", "NonStop" , "BundleIt" ,
                    "PassengerInfoSSR" , "FreeSpirit" , "CarryOn" , "CheckedBags" , "BagsUI" , "BigFrontSeat" ,"ShortCutBoarding",
                    "CheckInOptions","FlightFlex" , "Discover" })
    public void E2E_FS_RT_INT_2_ADT_SW_Change_to_3_ADT_Bundle_Fare_Red_Eye_Flight_Need_help_to_and_from_seat_1Pax_1CO_1CB_2Pax_1CO_1_BIKE_1SB_3Pax_No_Bags_3_BFS_Seats_ALL_Extras_CI_Agent_Discover(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280733 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "BOG";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "8";
        final String ARR_DATE 			= "13";
        final String ADULT  			= "2";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String ADULT_1  			= "3";
        final String DEP_FLIGHT 		= "NonStop";
        final String RET_FLIGHT 		= "NonStop";
        final String UPGRADE_VALUE      = "BundleIt";

        //Passenger Info Page Constant Values
        final String SSR                = "WheelChairNeedFromSeat";

        //Bags Page Method
        final String BAG_URL            = "spirit.com/book/bags";
        final String ZERO_BAG_PRICE 	= "Included";

        //Seat Page Constant Values
        final String SEAT               = "BigFront|BigFront|BigFront";

        //Option Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:AirportAgent";

        //Payment Page Constant Values
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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT_1, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();

//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageObjectManager.getFlightAvailabilityPage().getEarlyDepartureContinueButton().click(); //option is not available

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL",
                getDriver().getCurrentUrl(),BAG_URL);

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

        //Selecting 4 bikes for pax 1
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(1).click();

        WaitUtil.untilTimeCompleted(1200);

        for (int count = 0; count <= 1; count ++){
            pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(1).click();
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(1).click();
        }
        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();
        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getOptionsPage().getShortCutSecurityCardAddButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        for (int i = 0; i < pageObjectManager.getOptionsPage().getShortCutSecurityPopUpSelectCityCheckBox().size(); i++)
        {
            pageObjectManager.getOptionsPage().getShortCutSecurityPopUpSelectCityCheckBox().get(i).click();
        }
        pageObjectManager.getOptionsPage().getShortCutSecurityPopUpAddButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

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