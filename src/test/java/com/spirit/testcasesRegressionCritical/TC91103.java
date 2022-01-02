package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
//Test Case ID: TC91103
//Description: Station Advisory _CP_BP_ Home Page Travel Banner
//Created By : Sunny Sok
//Created On : 9-Aug-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
/**NOTE:Test Case steps are incorrect in Spira**/

public class TC91103 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"HomeUI","DomesticDomestic"})

    public void Station_Advisory_CP_BP_Home_Page_Travel_Banner(@Optional("NA") String platform) {
		/******************************************************************************
		 ************************Navigate to Flight Status Page************************
		 ******************************************************************************/
    	//Mention Suite and Browser in reports
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID TC91103 under RGRESSIONAL CRITICAL Suite on " + platform + " Browser"   , true);
    	}

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "DTW";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageObjectManager.getHomePage().getFlightStatusPathLink().click();
        pageObjectManager.getHomePage().getCheckByDestinationLabel().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        WaitUtil.untilTimeCompleted(2000);
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHomePage().getFlightStatusCheckStatusButton());

        //constants to validate
        final String TRAVE_ADVISORIE_VERBIAGE   = "Detroit (DTW) Guests: Arrive early due to long lines Due to higher volume at the airport, learn more";
        final String TRAVEL_ADVISORIE_URL       = "/travel-advisory";

        //Verify Station Advisory is displayed
        ValidationUtil.validateTestStep("Verify Station Advisory is displayed",
                TestUtil.verifyElementDisplayed( pageObjectManager.getHomePage().getFlightStatusStationAdvisoryText()));

        //Verify Verbiage
        ValidationUtil.validateTestStep("Verify Station Advisory is Verbiage",
                pageObjectManager.getHomePage().getFlightStatusStationAdvisoryText().getText(), TRAVE_ADVISORIE_VERBIAGE);

        //click on Station Adisory Learn more link
        pageObjectManager.getHomePage().getFlightStatusStationAdvisoryLearnMoreLink().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Verify user is navigated to travel advisory page
        ValidationUtil.validateTestStep("Verify user is navigated to travel advisory page",
                getDriver().getCurrentUrl(),TRAVEL_ADVISORIE_URL);

    }
}