package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC381904
//Description: Flight Flex Packaging Verbiage - Hub Packaging - Validate verbiage for Flight Flex on the Thrills Pop-Up
//Created By: Salim Ansari
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC381904 extends BaseClass {

    @Parameters("platform")
    @Test(groups = {"BookPath" , "Flight" , "RoundTrip", "DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BundleIt" ,"FlightAvailabilityUI "})
    public void Flight_Flex_Packaging_Verbiage_Hub_Packaging_Validate_verbiage_for_Flight_Flex_on_the_Thrills_Pop_Up(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC381904 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "93";
        final String ARR_DATE 			= "95";
        final String ADULT				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";


//Step1--Land on current test environment.
        openBrowser( platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);


//STEP--2 Input the following: RT | DOM_DOM | Any Date 3 months in the future | 1 PAX and click SEARCH FLIGHTS
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//STEP--3 Select any flights and click CONTINUE in the STANDARD tile
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep",DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//STEP--4 Verify the Bundle It verbiage contains: Flight Flex - Modify your flight once for free! (fare difference may apply)
        boolean Flag = false;

        for(WebElement bundleItBenfits : pageObjectManager.getFlightAvailabilityPage().getBundleTileBenefitsList()){
            if(bundleItBenfits.isDisplayed()){
                if(bundleItBenfits.getText().equalsIgnoreCase("Flight Flex - Modify your flight once for free! (fare difference may apply)")){
                    Flag = true;
                }
            }
        }

        ValidationUtil.validateTestStep("Verify the Flight Flex - Modify your flight once for free! (fare difference may apply) verbiage in the Bundle it section of the pop up on Flight Availability Page", Flag);

    }
}
