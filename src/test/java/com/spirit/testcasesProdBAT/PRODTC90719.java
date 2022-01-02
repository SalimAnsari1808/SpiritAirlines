package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
//Test Case ID: TC90719
//Title: KEEP_BAG_SAME_FOR_ALL_FLIGHTS(OW FLIGHT)
//Description: Ensure "Keep the same bags for all flights" checkbox is not available
//Created By : Alex Rodriguez
//Created On : 26-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 28-Mar-2019
//**********************************************************************************************

public class PRODTC90719 extends BaseClass{

    @Parameters ({"platform"})
    @Test(groups="Production")

    public void KEEP_BAG_SAME_FOR_ALL_FLIGHTS_OW_FLIGHT (@Optional("NA")String platform) {
        //Steps 1
        //******************************************************************************
        //****************************Navigate to Bags Page*****************************
        //******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90719 under PRODUCTION Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE 	  	= "Flight";
        final String TRIP_TYPE 	        = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE 	= "LAS";
        final String ARR_AIRPORTS 	    = "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 		    = "25";
        final String RET_DATE           = "NA";

        //Flight Availability Constant Variables
        final String DEP_FLIGHT 		= "NonStop";
        final String MEMBER_FARE_TYPE 	= "Standard";
        final String UPGRADE_VALUE 		= "BookIt";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, RET_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(MEMBER_FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

     //Step 2
        //******************************************************************************
        //**************************Validation on Bags Page*****************************
        //******************************************************************************
        //Validate "Keep the same bags for all flights" checkbox is available
        ValidationUtil.validateTestStep("Verify text for 'Keep the same bags for all flights' is not displayed or available for selection",
                pageObjectManager.getBagsPage().getKeepSameBagsLabel().size()==0 );


    }

}
