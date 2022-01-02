package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC381906
//Description: Flight Flex Packaging Verbiage - Flight + Car - Validate verbiage for Flight Flex on the Thrills Pop-Up
//Created By: Salim Ansari
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC381906 extends BaseClass {

    @Parameters("platform")
    @Test(groups = {"BookPath" , "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BundleIt" ,"FlightAvailabilityUI "})
    public void Flight_Flex_Packaging_Verbiage_Flight_Car_Validate_verbiage_for_Flight_Flex_on_the_Thrills_Pop_Up(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC381906 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_EMAIL 		= "FSEmail";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE 			= "Flight+Car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "93";
        final String ARR_DATE 			= "95";
        final String ADULT				= "2";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DRIVER_AGE 		= "25+";


//Step1--Land on current test environment.
        openBrowser( platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//STEP--2 Login as a FS member
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);

//STEP--3 Click on Vacation tab and select Flight + Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//STEP--4 Input the following: DOM_DOM | Any Date 3 months in the future | 2 PAX | Driver 25+ and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//STEP--5 Click BOOK CAR
//STEP--6 croll down to the bottom of the page and click CONTINUE
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");


//STEP--5 In the "Bundle It" content block, verify the following verbiage: Flight Flex - Modify your flight once for free!  (fare difference may apply)
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
