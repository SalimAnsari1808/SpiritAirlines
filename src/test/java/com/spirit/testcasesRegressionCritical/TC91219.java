package com.spirit.testcasesRegressionCritical;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
// TestCase ID: TC91219
// TestCase   : CP_Header_Book, My Trips, Check-In, Flight Status_Link
// Created By : Kartik Chauhan
// Created On : 08-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 09-July-2019
// **********************************************************************************************
public class TC91219 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups={"BookPath", "RoundTrip", "DomesticDomestic", "WithIn7Days", "Adult","Guest", "HomeUI","FlightAvailabilityUI"})

    public void CP_Header_Book_My_Trips_Check_In_Flight_Status_Link (@Optional("NA")String platform){

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91219 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         *************************Navigate to FA Page**********************************
         ******************************************************************************/
        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC91219 under REGRESSION-CRITICAL suite on " + platform +" Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE 		    	= "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 	  	    = "AllLocation";
        final String ARR_AIRPORT_CODE  	    = "DFW";
        final String DEP_DATE 	    		= "6";
        final String ARR_DATE 	    		= "7";
        final String ADULTS 		       	= "1";
        final String CHILDREN		      	= "0";
        final String INFANT_LAP 	     	= "0";
        final String INFANT_SEAT	     	= "0";
        final String LAST_NAME              = "Automation";
        final String CONFIRMATION_CODE      = "XXXXX";

//Step 2
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
//STEP--2
        //Flight Availability Methods
        ValidationUtil.validateTestStep("FA page is displaying",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().isDisplayed());
//STEP--3
        //Click on Spirit logo
        pageObjectManager.getHeader().getSpiritLogoImage().click();
//STEP--4
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        ValidationUtil.validateTestStep("FA page is displaying again",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().isDisplayed());
//STEP--5
        //click on Book Link
        pageObjectManager.getHeader().getBookLink().click();

//STEP--6
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        ValidationUtil.validateTestStep("FA page is displaying again",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().isDisplayed());

        //click on My Trip Link
        pageObjectManager.getHeader().getMyTripsLink().click();

        //input last name and PNR
        pageObjectManager.getHomePage().getMyTripsLastNameTextBox().sendKeys(LAST_NAME);
        pageObjectManager.getHomePage().getMyTripsPNRTextBox().sendKeys(CONFIRMATION_CODE);

        ValidationUtil.validateTestStep("User is able to enter Last Name in My trip",
                pageObjectManager.getHomePage().getMyTripsLastNameTextBox().getAttribute("value"),LAST_NAME);

        ValidationUtil.validateTestStep("User is able to enter Confirmation Code in My trip",
                pageObjectManager.getHomePage().getMyTripsPNRTextBox().getAttribute("value"),CONFIRMATION_CODE);

        //Wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP--7
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        ValidationUtil.validateTestStep("FA page is displaying again",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().isDisplayed());

        //click on Check-In Link
        pageObjectManager.getHeader().getCheckInLink().click();

        //input last name and PNR
        pageObjectManager.getHomePage().getCheckInLastNameTextBox().sendKeys(LAST_NAME);
        pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox().sendKeys(CONFIRMATION_CODE);

        ValidationUtil.validateTestStep("User is able to enter Last Name in My trip",
                pageObjectManager.getHomePage().getCheckInLastNameTextBox().getAttribute("value"),LAST_NAME);

        ValidationUtil.validateTestStep("User is able to enter Confirmation Code in My trip",
                pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox().getAttribute("value"),CONFIRMATION_CODE);

        //Wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--8
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        ValidationUtil.validateTestStep("FA page is displaying again",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().isDisplayed());
//STEP--8
        //click on Flight Status Link
        pageObjectManager.getHeader().getFlightStatusLink().click();

        //Wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Check by Destination option is displaying on Flight Status Page",
                pageObjectManager.getHomePage().getCheckByDestinationLabel().isDisplayed());

        ValidationUtil.validateTestStep("Check by Flight Number option is displaying on Flight Status Page",
                pageObjectManager.getHomePage().getCheckByFlightNumberLabel().isDisplayed());

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
    }

}
