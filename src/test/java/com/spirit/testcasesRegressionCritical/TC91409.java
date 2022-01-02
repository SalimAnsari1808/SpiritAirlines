package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91409
//Test Name: Task 24869: 35230 Search_CP_BP_ No Car Model
// Created By: Gabriela Gonzalez
//Created On : 05-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 07-Aug-2019
//**************************************************************************************************

public class TC91409 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "FlightCar" , "DomesticDomestic" ,"WithIn7Days", "Adult" ,"Guest", "HomeUI"})
    public void Search_CP_BP_No_Car_Model(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91409 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE 			= "Flight+Car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "1";
        final String ADULT				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DRIVER_AGE 		= "25+";

        final String FLIGHT_CAR         = "Flight + Car";
        final String FLIGHT_HOTEL       = "Flight + Hotel";
        final String FLIGHT_HOTEL_CAR   = "Flight + Hotel + Car";
        final String POP_UP_HEADER      = "No Car Available";
        final String POP_UP_INFO        = "Unfortunately, There are no cars available for the dates selected.";
        final String POP_UP_CHANGE_DATE = "Change Dates";
        final String HOME_PAGE_URL      = ".spirit.com";

        //open browser
        openBrowser( platform);

        /**************************Home Page Methods********************************/
//-- Step 13: Access SkySales testing environment
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//-- Step 1:Select the Vacation tab
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        ValidationUtil.validateTestStep("Validating Flight + Car text",
                pageObjectManager.getHomePage().getFlightCarLabel().getText(),FLIGHT_CAR);

        ValidationUtil.validateTestStep("Validating Flight + Hotel text",
                pageObjectManager.getHomePage().getFlightHotelRadiobutton().getText(),FLIGHT_HOTEL);

        ValidationUtil.validateTestStep("Validating Flight + Hotel + Car text",
                pageObjectManager.getHomePage().getFlightHotelCarRadiobutton().getText(),FLIGHT_HOTEL_CAR);

//-- Step 2: Select Flight + Car
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//-- Step 3: Select a City pair in the From and To fields
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

//-- Step 4: Select a Departing and Returning date that is within 48 hours
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

//-- Step 5: Select 1 adult
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

//-- Step 6: Select the drivers age (25+)
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);

//-- Step 7: Click on the search vacation button
        pageMethodManager.getHomePageMethods().clickSearchButton();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 8: Verify that you the pop has an X button
        ValidationUtil.validateTestStep("Verify that you the pop has an X button",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getNoCarAvailablePopUpCloseButton()));

//-- Step 9: Verify that the popup has "No CAR AVAILABLE" verbiage
        ValidationUtil.validateTestStep("Validating pop up header",
                pageObjectManager.getHomePage().getNoCarAvailablePopUpHeaderText().getText(),POP_UP_HEADER);

//-- Step 10: Verify there is verbiage "Unfortunately, There are no cars available for the dates selected."
        ValidationUtil.validateTestStep("Validating pop up info",
                pageObjectManager.getHomePage().getNoCarAvailablePopUpInfoText().getText(),POP_UP_INFO);

//-- Step 11: Verify that the "CHANGE DATES" button is there
        ValidationUtil.validateTestStep("Validating Change Dates Button is displayed",
                pageObjectManager.getHomePage().getNoCarAvailablePopUpChangeFlightButton().getText(),POP_UP_CHANGE_DATE);

//-- Step 12: Verify that the popup will close
        //Click the X button which would close out the popup and return you back to the search widget
        pageObjectManager.getHomePage().getNoCarAvailablePopUpCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verify Still on Home page",
                getDriver().getCurrentUrl(),(HOME_PAGE_URL));
    }
}