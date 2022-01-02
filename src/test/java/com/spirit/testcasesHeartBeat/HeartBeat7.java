package com.spirit.testcasesHeartBeat;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase : HeartBeat7
// Description: Guest_DOM_2Adt_Through_1Carry_0Checked_BFS_Seat
// Created By : Kartik Chauhan
// Created On : 06-Aug-2019
// Reviewed By: Salim Ansari
// Reviewed On: 06-Aug-2019
// **********************************************************************************************
public class HeartBeat7 extends BaseClass {

    @Parameters ({"platform"})
    @Test
    public void Guest_DOM_2Adt_Through_1Carry_0Checked_BFS_Seat (@Optional("NA")String platform){

        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID HEARTBEAT7 under HeartBeat suite on " + platform +" Browser", true);
        }
        /******************************************************************************
         ****************************Navigate to Confirmation Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_ACCOUNT 		= "FSEmail";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "2";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORTING_TYPE		= "Departure";
        final String DEP_FLIGHT			= "Through";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_TYPE		= "BookIt";

        //Bags Page page connstant
        final String DEP_BAGS           = "Checked_0|Carry_1";
        final String RET_BAGS           = "Checked_0|Carry_1";
        final String BAGS_FARE          = "Standard";

        //Seat Page Constant
        final String DEP_SEAT           = "BigFront||BigFront";

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
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GURAD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);

    }
}

