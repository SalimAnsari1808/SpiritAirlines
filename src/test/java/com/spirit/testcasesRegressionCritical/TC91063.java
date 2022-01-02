package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;


//**********************************************************************************************
//Test Case ID: TC91063
//Description:  Flight Availability_CP_BP_Flight Only_Verify SOLD OUT verbiage displays when applicable
//Created By:   Salim Ansari
//Created On:   25-July-2019
//Reviewed By:  Kartik Chauhan
//Reviewed On:  25-July-2019
//**********************************************************************************************
public class TC91063 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NewFlightSearch" , "FlightAvailabilityUI"})
    public void Flight_Availability_CP_BP_Flight_Only_Verify_SOLD_OUT_verbiage_displays_when_applicable(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91063 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         ***********************Navigate to Flight Availiability Page******************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "BWI";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "0";
        final String ADULTS1            = "9";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";


        //Flight Availability Page Constant Values
        final String DEP_AIRPORT_CODE1  = "FLL";
        final String ARR_AIRPORT_CODE1  = "LAS";

        //STEP--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS1, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE1, ARR_AIRPORTS, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS1, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();


        /******************************************************************************
         ***********************Validation on Flight Availiability Page****************
         ******************************************************************************/
        //declare constant used in validation
        final String GREY_COLOR1    = "204, 204, 204, 1";
        final String GREY_COLOR2    = "108, 117, 125, 1";

        //declare variable used in validation
        int flightCounter               = 0;
        boolean flightFound             = false;
        List<WebElement> allFlightRows = getDriver().findElements(By.xpath("//div[@class='flight-card-container']"));

        //loop through all flight rows on FA page
        for(flightCounter=0;flightCounter<allFlightRows.size();flightCounter++){
            //check for "Sold Out" text
            if(allFlightRows.get(flightCounter).getText().contains("Sold Out")){
                flightFound = true;
                break;
            }
        }

        //check flight is found in calender
        if(flightFound){
            //check Departing Time text color
            ValidationUtil.validateTestStep("Verify Departing Time text of Sold Out flight is in GREY color on Flight Availiability Page",
                    getDriver().findElements(By.xpath("//p[@data-qa='journey-depart-time']")).get(flightCounter).getCssValue("color"),GREY_COLOR1);

            //check Arrival Time text color
            ValidationUtil.validateTestStep("Verify Arrival time text of Sold Out flight is in GREY color on Flight Availiability Page",
                    getDriver().findElements(By.xpath("//p[@data-qa='journey-arrival-time']")).get(flightCounter).getCssValue("color"),GREY_COLOR1);

            //check Available Seats text color
            ValidationUtil.validateTestStep("Verify Available Seats text of Sold Out flight is in GREY color on Flight Availiability Page",
                    getDriver().findElements(By.xpath("//button[@data-qa='journey-seats']")).get(flightCounter).getCssValue("color"),GREY_COLOR2);

            //check Sold Out text color
            ValidationUtil.validateTestStep("Verify Sold Out text of Sold Out flight is in GREY color on Flight Availiability Page",
                    getDriver().findElements(By.xpath("//div[@data-qa='journey-sold-out']")).get(0).getCssValue("color"),GREY_COLOR1);
        }else{
            //skip test cases if sold out verbiage is not found on FA page
            ValidationUtil.skipTestCase("Test case is skipped as Sold Out flight is not found on Flight Availiability Page", true);
        }

    }

}
