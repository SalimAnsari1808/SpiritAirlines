package com.spirit.testcasesRegressionCritical;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC91105
//Title      : Station Advisory_ CP_BP_ Flight Status
//Created By : Salim Ansari
//Created On : 05-Aug-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 05-Aug-2019
//**********************************************************************************************
public class TC91105 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"HomeUI" ,"DomesticDomestic", "FlightStatus"})
    public void Station_Advisory_CP_BP_Flight_Status (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Home Page******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91105 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE = "English";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "DTW";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "FLL";
        final String BLACK_COLOR		    = "0, 0, 0";
        final String DEP_DATE               = "1";
        final String STATION_ADVISORY       = "Detroit (DTW) Guests: Arrive early due to long lines Due to higher volume at the airport, learn more";

        //open browser
        openBrowser(platform);
//STEP--2
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//STEP--3
        //Select the Flight Status Tab on the Booking widget from the Home Page  See the attachment
        pageObjectManager.getHomePage().getFlightStatusPathLink().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //verify user navigated to MyTrips Reservation Summary Page
        ValidationUtil.validateTestStep("Verify User clicked on Flight Status tab on Home Page",
                pageObjectManager.getHomePage().getFlightStatusPathLink().getCssValue("color"),BLACK_COLOR);


        //Select the "Check BY DESTINATION" option from the flight status Widget See the attachment
        //click on check by Destination
        pageObjectManager.getHomePage().getCheckByDestinationLabel().click();

        //Select a Domestic Departure and Arrival city pair for tomorrow
        //Then click on Check status
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS,DEP_AIRPORT_CODE,ARR_AIRPORTS,ARR_AIRPORT_CODE);

        //Enter tomorrow date
        TestUtil.selectDropDownUsingValue(pageObjectManager.getHomePage().getFlightStatusDateDropDown(),TestUtil.getStringDateFormat(DEP_DATE,"yyyy-MM-dd"));

        WaitUtil.untilTimeCompleted(2000);
        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(4000);

//STEP--4
        //On the Flight Status page search for flight that has a Station Advisory on it
        ValidationUtil.validateTestStep("Verify Station Advisory appear on Flight Status Page",
                pageObjectManager.getHomePage().getFlightStatusStationAdvisoryText().getText() , STATION_ADVISORY);

    }
}
