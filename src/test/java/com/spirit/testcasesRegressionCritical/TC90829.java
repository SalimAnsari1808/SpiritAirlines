package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC90829
//Test Name: Search Widget_CP_BP_ Flight+Car Multi Pax with Multi Children DOM-DOM
// Created By: Kartik Chauhan
//Created On : 13-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**************************************************************************************************
public class TC90829 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Child" ,"Guest", "HomeUI"})
    public void Search_Widget_CP_BP_Flight_Car_Multi_Pax_with_Multi_Child_DOM_DOM(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90829 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "35";
        final String ARR_DATE           = "36";
        final String ADULT              = "3";
        final String CHILD              = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        final String FLIGHT_CAR         = "Flight + Car";
        final String FLIGHT_HOTEL       = "Flight + Hotel";
        final String FLIGHT_HOTEL_CAR   = "Flight + Hotel + Car";
        final String F_A_URL            = "/book/flights-cars";

        //open browser
        openBrowser(platform);

        /**************************Home Page Methods********************************/
//Step 1
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        ValidationUtil.validateTestStep("Validating Flight + Car text",
                pageObjectManager.getHomePage().getFlightCarLabel().getText(), FLIGHT_CAR);

        ValidationUtil.validateTestStep("Validating Flight + Hotel text",
                pageObjectManager.getHomePage().getFlightHotelRadiobutton().getText(), FLIGHT_HOTEL);

        ValidationUtil.validateTestStep("Validating Flight + Hotel + Car text",
                pageObjectManager.getHomePage().getFlightHotelCarRadiobutton().getText(), FLIGHT_HOTEL_CAR);

//Step--2
        //select Flight + car
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
//Step--3
        //select DOM-DOM flight
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

// Step 4
//VERIFY Driver's age are displaying************************************************
        //Click on Drivers Age drop down
        pageObjectManager.getHomePage().getDriversAgeDropDown().click();

        //Verify Drivers's option 21- 24
        ValidationUtil.validateTestStep("Verify Drivers's option 21- 24 is displaying",
                pageObjectManager.getHomePage().getDriversAgeDropDown().getText(),"21-24");

        //Verify Drivers's option 21- 24
        ValidationUtil.validateTestStep("Verify Drivers's option 25+ is displaying",
                pageObjectManager.getHomePage().getDriversAgeDropDown().getText(),"25+");

        //Select 25+
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
//STEP--5
        //Select 4 adult and 2 child
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

//Step--6
        //Select Date and handle young traveler pop-up
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//Step--7
        //wait till page complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify redirecting url is correct
        ValidationUtil.validateTestStep("Verify Car Availability Page load",
                getDriver().getCurrentUrl().contains(F_A_URL));
    }
}