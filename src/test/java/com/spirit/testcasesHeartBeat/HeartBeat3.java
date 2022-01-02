package com.spirit.testcasesHeartBeat;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase :  HEARTBEAT3
// Description: Guest_DOM_RT_2Adt_Connecting_NoBags_NoSeats
// Created By : Kartik Chauhan
// Created On : 06-Aug-2019
// Reviewed By: Salim Ansari
// Reviewed On: 06-Aug-2019
// **********************************************************************************************
public class HeartBeat3 extends BaseClass {

    @Parameters ({"platform"})
    @Test
    public void Guest_DOM_RT_2Adt_Connecting_NoBags_NoSeats (@Optional("NA")String platform){

        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID HEARTBEAT3 under HeartBeat suite on " + platform +" Browser", true);
        }
        /******************************************************************************
         ****************************Navigate to Confirmation Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "5";
        final String ADULTS				= "4";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORTING_TYPE		= "Departure";
        final String DEP_FLIGHT			= "Connecting";
        final String RET_FLIGHT			= "Connecting";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_TYPE		= "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GURAD		= "Required";
        final String CARD_TYPE			= "MasterCard";


//Step1
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GURAD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);

    }
}

