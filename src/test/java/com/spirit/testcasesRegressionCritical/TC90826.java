package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90826
//Test Name: Task 22940: 31256 Search Widget_CP_BP_Flight+Car 1 Pax with Lap Child
// Created By: Gabriela Gonzalez
//Created On : 06-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 07-Aug-2019
//**************************************************************************************************

public class TC90826 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Adult" , "InfantLap" ,"Guest", "HomeUI"})
    public void Search_Widget_CP_BP_Flight_Car_1_Pax_with_Lap_Child(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90826 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE 			= "Flight+Car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "35";
        final String ARR_DATE 			= "36";
        final String ADULT				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "1";
        final String INFANT_SEAT		= "0";
        final String DRIVER_AGE 		= "25+";

        final String FLIGHT_CAR         = "Flight + Car";
        final String FLIGHT_HOTEL       = "Flight + Hotel";
        final String FLIGHT_HOTEL_CAR   = "Flight + Hotel + Car";
        final String F_A_URL            = "/book/flights-cars";

        //open browser
        openBrowser( platform);

        /**************************Home Page Methods********************************/
//-- Step 9: Access SkySales testing environment
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//-- Step 1: On the Search Widget, under the Book Tab, select Vacation
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        ValidationUtil.validateTestStep("Validating Flight + Car text",
                pageObjectManager.getHomePage().getFlightCarLabel().getText(),FLIGHT_CAR);

        ValidationUtil.validateTestStep("Validating Flight + Hotel text",
                pageObjectManager.getHomePage().getFlightHotelRadiobutton().getText(),FLIGHT_HOTEL);

        ValidationUtil.validateTestStep("Validating Flight + Hotel + Car text",
                pageObjectManager.getHomePage().getFlightHotelCarRadiobutton().getText(),FLIGHT_HOTEL_CAR);

//-- Step 2: Select Flight+ Car and a DOM city pair Note: Preferably a major city where car rentals are available
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

//-- Step 3:  Add 1 Adult and 1 Lap Child
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

//-- Step 4: Select the drivers age: Select 25+
        //User should see two options, 21-24 and 25+.
        List<WebElement> ageDropDown =  new Select(pageObjectManager.getHomePage().getDriversAgeDropDown()).getOptions();
        List<String> dropDownValue = new ArrayList<>();
        for (int count = 0; count < ageDropDown.size(); count ++) {
            dropDownValue.add(ageDropDown.get(count).getAttribute("value"));
        }

        for (int count = 1; count < dropDownValue.size(); count ++) {
            ValidationUtil.validateTestStep("Validating age drop down contains the right 2 options",
                    dropDownValue.get(count).equals("21")||dropDownValue.get(count).equals("25"));
        }

        //Select 25+
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);

//-- Step 5: Select a Departing and Returning date that is over 48 hours (will not be offered if flight is within 48hrs of booking to minimize fraud)
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

//-- Step 6: Click SEARCH VACATION
        pageMethodManager.getHomePageMethods().clickSearchButton();

//-- Step 7: On the Young Travelers modal, have the child be under 2 years old
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//-- Step 8: User should now land on Car Availability page, where the flights are pre-selected, to select a Car (/book/flights-cars)
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify Car Availability Page load",
                getDriver().getCurrentUrl(),(F_A_URL));
    }
}