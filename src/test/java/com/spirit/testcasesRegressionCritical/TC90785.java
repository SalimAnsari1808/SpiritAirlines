package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC90785
//Title      : Home Page_CP_BP_Flight Status by Destination_Status On time
//Created By : Salim Ansari
//Created On : 05-Aug-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 05-Aug-2019
//**********************************************************************************************
//TODO: BUG: [IN:24589] CP: Flight Status: Get Updates 500 error when user inputs email, wrong endpoint // fixed
public class TC90785 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"FlightStatus"})
    public void Home_Page_CP_BP_Flight_Status_by_Destination_Status_On_time (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Home Page******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90785 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "DTW";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "FLL";
        final String BLACK_COLOR		    = "0, 0, 0";
        final String GREEN_COLOR            = "47, 127, 41";
        final String DEP_DATE               = "1";
        final String TERMINAL               = "Terminal:";
        final String GATE                   = "Gate:";
        final String FLIGHT_DURATION        = "Flight Duration";
        final String Time                   = "min";
        final String GET_UPDATE_HEADER      = "For your convenience, you can sign up for flight status updates via email. We'll only use this email to send you flight status updates related to this flight.";
        final String ERROR_EMAIL            = "Email is required";
        final String FAKE_EMAIL             = "fake@gmail.com";
        final String GET_UPDATE_POPUP_TEXT  = "You have signed up fake@gmail.com for flight status updates related to Flight 417 to FT LAUDERDALE HOLLYWOOD on";


        //open browser
        openBrowser(platform);
//STEP--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//STEP--2
        //Select the Flight Status Tab on the Booking widget from the Home Page  See the attachment
        pageObjectManager.getHomePage().getFlightStatusPathLink().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //verify user navigated to MyTrips Reservation Summary Page
        ValidationUtil.validateTestStep("Verify User clicked on Flight Status tab on Home Page",
                pageObjectManager.getHomePage().getFlightStatusPathLink().getCssValue("color"),BLACK_COLOR);

//STEP--3
        //Select the "Check BY DESTINATION" option from the flight status Widget See the attachment
        //click on check by Destination
        pageObjectManager.getHomePage().getCheckByDestinationLabel().click();

//STEP--4
        //Select a Domestic Departure and Arrival city pair for tomorrow
        //Then click on Check status
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS,DEP_AIRPORT_CODE,ARR_AIRPORTS,ARR_AIRPORT_CODE);

        //Enter tomorrow date
        TestUtil.selectDropDownUsingValue(pageObjectManager.getHomePage().getFlightStatusDateDropDown(),TestUtil.getStringDateFormat(DEP_DATE,"yyyy-MM-dd"));

        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(2000);

//STEP--5
        //verify day, date
        ValidationUtil.validateTestStep("Verify correct day, date appear on Flight Status Tab on Home Page",
                pageObjectManager.getHomePage().getFlightStatusDateText().getText(),TestUtil.getStringDateFormat(DEP_DATE,"EEEE, MMMM dd"));

        boolean statusFlag = true;


        //on time button
        System.out.println(pageObjectManager.getHomePage().getFlightStatusOnTimeText().get(0).getCssValue("background-color"));
        for(WebElement onTimeStatus : pageObjectManager.getHomePage().getFlightStatusOnTimeText()){
            if(!onTimeStatus.getCssValue("background-color").contains(GREEN_COLOR)){
                statusFlag = false;
            }
        }

        //verify flight number
        ValidationUtil.validateTestStep("Verify On Time Green color appear on Flight Status Tab on Home Page", statusFlag);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //loop through all Flight Status Block
        for(int collapseBlock=0;collapseBlock<pageObjectManager.getHomePage().getFlightStatusCollapseShowLink().size();collapseBlock++){
            int startCounter = 0;

            //open collapse block
            pageObjectManager.getHomePage().getFlightStatusCollapseShowLink().get(collapseBlock).click();

            //wait for 2 sec
            WaitUtil.untilTimeCompleted(2000);

            startCounter = collapseBlock * 2;

//Departure 'Terminal/Gate'
            ValidationUtil.validateTestStep("Verify Departure flight Terminal verbiage on collapse block on Flight Status Page",
                    pageObjectManager.getHomePage().getFlightStatusCollapseShowDepartureText().get(startCounter).getText(),TERMINAL);

            ValidationUtil.validateTestStep("verify Departure flight Gate verbiage on collapse block on Flight Status Page",
                    pageObjectManager.getHomePage().getFlightStatusCollapseShowDepartureText().get(startCounter+1).getText(),GATE);

//Arrival 'Terminal/Gate'
            ValidationUtil.validateTestStep("Verify Arrival flight Terminal verbiage on collapse block on Flight Status Page",
                    pageObjectManager.getHomePage().getFlightStatusCollapseShowArrivalText().get(startCounter).getText(),TERMINAL);

            ValidationUtil.validateTestStep("verify Arrival flight Gate verbiage on collapse block on Flight Status Page",
                    pageObjectManager.getHomePage().getFlightStatusCollapseShowArrivalText().get(startCounter+1).getText(),GATE);

//Flight number
            ValidationUtil.validateTestStep("Verify Flight Duration verbiage on collapse block on Flight Status Page",
                    pageObjectManager.getHomePage().getFlightStatusCollapseShowTimeText().get(startCounter).getText(),FLIGHT_DURATION);

            ValidationUtil.validateTestStep("Verify Flight Duration Time verbiage on collapse block on Flight Status Page",
                    pageObjectManager.getHomePage().getFlightStatusCollapseShowTimeText().get(startCounter+1).getText(),Time);

        }

//STEP--6
        //Pick a flight from the list and push the Get Update button associated with it
        //click on get update button
        pageObjectManager.getHomePage().getFlightStatusGetUpdateButton().get(0).click();

        //Wait untill page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //verify the get status block header
        ValidationUtil.validateTestStep("verify the get status block header on Flight Status Page",
                pageObjectManager.getHomePage().getGetUpdateHeaderText().get(0).getText(),GET_UPDATE_HEADER);

 //STEP--7
        //Do not Enter an email address and push the Get Updates button

        //click on get update button
        pageObjectManager.getHomePage().getGetUpdateGetUpdateButton().get(0).click();

        //Verify that a valid email address is required
        ValidationUtil.validateTestStep("Verify that a valid email address is required error message appear on Flight Status Page",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR_EMAIL);

//STEP--8
        //Enter an email address and push the Get Updates button
        //enter Email
        pageObjectManager.getHomePage().getGetUpdateEmailTextBox().get(0).sendKeys(FAKE_EMAIL);

        //click on get update button
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHomePage().getGetUpdateGetUpdateButton().get(0).click();

        //Wait until page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //verify update is send on the mail entered
        ValidationUtil.validateTestStep("verify update is send on the mail entered  on Get Update popup on Flight Status Page",
                pageObjectManager.getHomePage().getGetUpdatePopupVerbiageText().getText(),GET_UPDATE_POPUP_TEXT);
    }

}

