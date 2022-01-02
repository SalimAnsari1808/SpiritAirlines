package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;


//**********************************************************************************************
//Test Case ID: TC90718
//Title: KEEP_BAG_SAME_FOR_ALL_FLIGHTS_RT_or_MC
//Description: Select max bags for departing flight and ensure the return trip matches the number
//             of bags selected after "Keep the same bags for all flights" checkbox is selected
//Created By : Alex Rodriguez
//Created On : 26-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 28-Mar-2019
//**********************************************************************************************

public class PRODTC90718 extends BaseClass{
    @Parameters ({"platform"})
    @Test(groups="Production")

    public void KEEP_BAG_SAME_FOR_ALL_FLIGHTS_RT_or_MC (@Optional("NA")String platform) {
     //Steps 1-2
        //******************************************************************************
        //****************************Navigate to Bags Page*****************************
        //******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90718 under PRODUCTION Suite on " + platform + " Browser"   , true);


        }
        //declare constant used in Navigation
        final int FIRST_INDEX           = 0;
        final String BLUE_COLOR         = "rgb(0, 123, 255)";

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE 	        = "RoundTrip";
        final String DEP_AIRPORTS 	    = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE           = "25";
        final String ARR_DATE           = "30";
        final String ADULTS	            = "3";
        final String CHILDREN 	        = "0";
        final String INFANT_LAP	        = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String ARR_Flight         = "Connecting";
        final String FARE_TYPE	        = "Standard";
        final String UPGRADE_VALUE	    = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_3||Carry_0|Checked_1||Carry_1|Checked_2";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

     //Step 3
        //Validate "Keep the same bags for all flights" checkbox is available
        ValidationUtil.validateTestStep("Verify text for 'Keep the same bags for all flights' is displayed and available for selection",
                pageObjectManager.getBagsPage().getKeepSameBagsLabel().get(FIRST_INDEX).isDisplayed());

        //click on Keep Same Bags check box
        pageObjectManager.getBagsPage().getKeepSameBagsLabel().get(FIRST_INDEX).click();

        //Validate "Keep the same bags for all flights" checkbox is selected
        ValidationUtil.validateTestStep("Verify checkbox for 'Keep the same bags for all flights' is selected",
                JSExecuteUtil.getElementBeforeProperty(getDriver(),pageObjectManager.getBagsPage().getKeepSameBagsLabel().get(FIRST_INDEX),"background-color").toString().contains(BLUE_COLOR));

        //******************************************************************************
        //****************************Validation on Bags Page***************************
        //******************************************************************************/
        //declare variable used in validaion
        int depCarryOnBags;
        int depCheckedBags;
        int retCarryOnBags;
        int retCheckedBags;

     //Step 4
        //loop through all passengers
        for(int passengerCounter = 0;passengerCounter <3;passengerCounter++) {
            //get the carry on bags for Departing flight
            depCarryOnBags = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(passengerCounter)));

            //get the carry on bags for Returning flight
            retCarryOnBags = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getBagsPage().getReturningCarryOnCounterTextBox().get(passengerCounter)));

            //get the checked bags for Departing flight
            depCheckedBags = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));

            //get the checked bags for Returning flight
            retCheckedBags = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));

            //verify Departing and Returning bags for carry on
            ValidationUtil.validateTestStep("Verify the Carry On Bags for Passenger:" + (passengerCounter+1) + " have same count for Departing and Returning Flight on Bags Page",depCarryOnBags==retCarryOnBags);

            //verify Departing and Returning bags for checked
            ValidationUtil.validateTestStep("Verify the Checked Bags for Passenger:" + (passengerCounter+1) + " have same count for Departing and Returning Flight on Bags Page",depCheckedBags==retCheckedBags);
        }

    }

}
