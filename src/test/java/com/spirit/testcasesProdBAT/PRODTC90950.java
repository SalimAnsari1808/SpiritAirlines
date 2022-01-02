package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
//@Listeners(com.spirit.testNG.Listener.class)

// **********************************************************************************************
// Test Case ID: TC90950
// Test Name   : Flight Availability_CP_BP_Advanced Search Widget_Functionality_Validate the OW INT travel message pops up for DOM-INT
// Description : Confirm the functionality of the OW INT travel message pops up for DOM-INT on the
//              Flight Availability Search Widget
// Created By  : Alex Rodriguez
// Created On  : 27-Mar-2019
// Reviewed By : Salim Ansari
// Reviewed On : 28-Mar-2019
// **********************************************************************************************
public class PRODTC90950 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups="Production")

    public void Flight_Availability_CP_BP_Advanced_Search_Widget_Functionality_Validate_the_OW_INT_travel_message_pops_up_for_DOM_INT (@Optional("NA")String platform){
        /******************************************************************************
         *********************Navigate to Flight Availability Bags Page****************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90950 under PRODUCTION Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "Roundtrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "DTW";
        final String DEP_DATE               = "15";
        final String ARR_DATE               = "20";
        final String ADULTS	                = "1";
        final String CHILDREN               = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String NEW_TRIP_TYPE 		    = "OneWay";
        final String NEW_DEP_AIRPORTS 		= "AllLocation";
        final String NEW_DEP_AIRPORT_CODE   = "FLL";
        final String NEW_ARR_AIRPORTS 		= "AllLocation";
        final String NEW_ARR_AIRPORT_CODE   = "BOG";
        final String NEW_DEP_DATE 		    = "25";
        final String NEW_ARR_DATE 		    = "NA";
        final String DEP_FLIGHT      	    = "nonstop";
        final String FARE_TYPE		        = "Standard";
        final String UPGRADE_TYPE           = "BookIt";

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
        //***************************************************************************
        //***** NOTE: Since common method for Search widget is being used,***********
        //***** Console output will say Home Page instead of Flight Availability.****
        //***************************************************************************
        //Select New Search Flight Button
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

        //Change Flight to DOM_INT
        pageMethodManager.getHomePageMethods().selectTripType(NEW_TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(NEW_DEP_AIRPORTS, NEW_DEP_AIRPORT_CODE, NEW_ARR_AIRPORTS, NEW_ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(NEW_DEP_DATE, NEW_ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /******************************************************************************
         *******************Validation to Flight Availability Bags Page****************
         ******************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

        //validate One way trip modal is displaying for international flight
        ValidationUtil.validateTestStep("User verifies \"One-Way International travel may require proof of return travel at the airport.\" is displaying",
                TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getHomePage().xpath_OneWayInternationalButton)));

        //Click on One-Way International travel Okay button
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Finish flight selection and continue to Passenger information page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);
    }
}
