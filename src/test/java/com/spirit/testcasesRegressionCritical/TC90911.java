package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//**********************************************************************************************
//Test Case ID: TC90911
//Description: Flight Availability_MP_BP_Search Widget_Functionality_Validate error messages pop up when required fields are left blank
//Created By: Kartik chauhan
//Created On: 29-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-July-2019
//**********************************************************************************************

public class TC90911 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NewFlightSearch" , "FlightAvailabilityUI"})
    public void Flight_Availability_MP_BP_Search_Widget_Functionality_Validate_error_messages_pop_up_when_required_fields_are_left_blank(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to FA Page********************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90911 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "2";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //New Search Flight Availability Page Constant Values
        final String DEP_AIRPORT_CODE1  = "ORD";
        final String BLUE_COLOR         = "0, 115, 230";
        final String FROM_ERROR         = "Please enter a valid origin.";
        final String TO_ERROR           = "Please enter a valid destination.";
        final String DATE_ERROR         = "Please enter a valid date range.";
        final String BOLD               = "900";

        //open browser
        openBrowser(platform);
//STEP--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        /****************************************************************************
         **************Flight Availability Page Methods*****************************
         ****************************************************************************/
        //wait till driver get load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //Wait for 3 second
        WaitUtil.untilTimeCompleted(3000);
//STEP--2
        //click on New Search button on FA page
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Clear Departure Textbox
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getDepartAirportBox().get(0));

        //click on Arrival airport Box
        pageObjectManager.getHomePage().getArrivalAirportBox().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify error displaying for departure
        ValidationUtil.validateTestStep("Error is displaying on Leaving departure Text Box blank",
                pageObjectManager.getHomePage().getErrorMessageText().getText(),FROM_ERROR);

        //select city pair
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE1, ARR_AIRPORTS, ARR_AIRPORT_CODE);
//STEP--3
        //wait till driver get load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Clear Arrival Textbox
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getArrivalAirportBox().get(0));

        //click on Departure airport Box
        pageObjectManager.getHomePage().getDepartAirportBox().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify error displaying for arrival
        ValidationUtil.validateTestStep("Error is displaying on Leaving arrival text box blank",
                pageObjectManager.getHomePage().getErrorMessageText().getText(),TO_ERROR);

        //select city pair
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE1, ARR_AIRPORTS, ARR_AIRPORT_CODE);
//STEP--4
        //Clear Date Textbox
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getDateBox());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(3000);

        //Click on Search button
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify error displaying for date
        ValidationUtil.validateTestStep("Error is displaying on Leaving Date text box blank",
                pageObjectManager.getHomePage().getErrorMessageText().getText(),DATE_ERROR);

        //Select date
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

        //Click on Search button
        pageMethodManager.getHomePageMethods().clickSearchButton();
//STEP--5
        //verifying placement of search button
        ValidationUtil.validateTestStep("New search Button is displaying on FA page",
                pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().isDisplayed());

        //verifying color of search button
        ValidationUtil.validateTestStep("Verify color of Search button on FA page",
                JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getFlightAvailabilityPage().getNewSearchButton(), "color").contains(BLUE_COLOR));

        //verifying font of search button
        ValidationUtil.validateTestStep("Verify font of Search button on FA page",
                pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().getCssValue("font-Weight").equals(BOLD));
    }
}