package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Date;

//**********************************************************************************************
//Test CaseID: TC90759
//Title      : Home Page_CP_BP_Flight Status by Number
//Created By : Salim Ansari
//Created On : 05-Aug-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 05-Aug-2019
//**********************************************************************************************
//BUG: [IN:24589] CP: Flight Status: Get Updates 500 error when user inputs email, wrong endpoint
/**10/21/19 test case passed, removed active bug tag**/
/**12/01/19 Same bug returned**/

public class TC90759 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"FlightStatus", "ActiveBug"})
    public void Home_Page_CP_BP_Flight_Status_by_Number (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Home Page******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90759 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String SUB_HEADER             = "On-time? Delayed? Find arrival and departure info here.";
        final String ALPHA_FLIGHT_NUMBER    = "SPIRIT";
        final String FLIGHT_NUMBER          = "511";
        final String FAKE_FLIGHT_NUMBER     = "50";
        final String ERROR_FLIGHT_NUMBER    = "Please enter Flight Number.";
        final String ERROR_FLIGHT_AVAILABLE = "There is no flight information available for this flight. Please verify your flight number or origin and destination, and try again.";
        final String GET_UPDATE_HEADER      = "For your convenience, you can sign up for flight status updates via email. We'll only use this email to send you flight status updates related to this flight.";
        final String DEP_DATE               = "0";
        final String YESTERDAY_DATE         = "-1";
        final String TODAY_DATE             = "0";
        final String FAKE_EMAIL             = "fake@gmail.com";
        final String GET_UPDATE_POPUP_TEXT  = "You have signed up fake@gmail.com for flight status updates related to Flight 511 to MC CARRAN INTL on";


        //declare variable used in method
        Date dropdownDate;
        Date currentDate;

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

//STEP--3
        //Select the "Check Flight Status" by "Number" option from the flight status Widget
        pageObjectManager.getHomePage().getCheckByFlightNumberLabel().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);
//STEP--4
        //Verify that you have the text " On-Time? Delayed? Find arrival and departure info here."
        ValidationUtil.validateTestStep("Verify that you have the text \" On-Time? Delayed? Find arrival and departure info here.",
                pageObjectManager.getHomePage().getFlightStatusSubHeaderText().getText(),SUB_HEADER);

//STEP--5
        //Verify that guest sees two input box For flight number and date"
        //verify flight number text box
        ValidationUtil.validateTestStep("Verify Flight Number text box appear on Flight Status Tab on Home Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightStatusFlightNumberTextBox()));

        //verify flight date drop down
        ValidationUtil.validateTestStep("Verify Flight Date Drop Down appear on Flight Status Tab on Home Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightStatusDateDropDown()));

//STEP--6
        //Flight number field box well Align left within input box, box has a hash tag icon and blue background
        //Image cannot be verified as it is stored in CSS and coming as garbage value using Selenium API

//STEP--7
        //Enter a number on the field box(Any attempt to enter alpha character immediately reverts back to blank)
        //Enter Flight Number
        pageObjectManager.getHomePage().getFlightStatusFlightNumberTextBox().sendKeys(ALPHA_FLIGHT_NUMBER);

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //verify alpha character immediately reverts back to blank
        ValidationUtil.validateTestStep("verify alpha character immediately reverts back to blank in Flight Number text box on Flight Status Page",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getHomePage().getFlightStatusFlightNumberTextBox()).length()==0);

//STEP--8
        //the second field box well have a Calendar icon and a blue background.
        //Image cannot be verified as it is stored in CSS and coming as garbiage value using Selenium API

//STEP--9
        //get current date
        currentDate = TestUtil.convertStringToDate(TestUtil.getStringDateFormat(DEP_DATE,"EEEE, MMMM dd, yyyy"),"EEEE, MMMM dd, yyyy");

        //Sunday, August 4, 2019
        //EEEE, MMMM, dd, yyyy
        Select drpdwnDate = new Select(pageObjectManager.getHomePage().getFlightStatusDateDropDown());
        dropdownDate = TestUtil.convertStringToDate(drpdwnDate.getFirstSelectedOption().getText(),"EEEE, MMMM dd, yyyy");


        //Verify that the date is default today when the drop down opens,
        ValidationUtil.validateTestStep("Verify the default selected Date in dropdown is today's Date on Flight Status Page",
                (currentDate.getTime() - dropdownDate.getTime())/(1000 * 60 * 60 * 24)==Integer.parseInt(TODAY_DATE));

        //Prior day placement is above today's date,
        dropdownDate = TestUtil.convertStringToDate(drpdwnDate.getOptions().get(0).getText(),"EEEE, MMMM dd, yyyy");

        //Verify Prior day placement is above today's date
        ValidationUtil.validateTestStep("Verify the Prior day placement is above today's date on Flight Status Page",
                (dropdownDate.getTime()-currentDate.getTime())/(1000 * 60 * 60 * 24)==Integer.parseInt(YESTERDAY_DATE));

        int datediff = -1;

        //Additional 5 day selection will be greater than today's date in chronological order
        for(WebElement drpdownOptions : drpdwnDate.getOptions()){
            //get dropdown date
            dropdownDate = TestUtil.convertStringToDate(drpdownOptions.getText(),"EEEE, MMMM dd, yyyy");

            //Additional 5 day selection will be greater than today's date in chronological order
            ValidationUtil.validateTestStep("Verify Additional 5 day selection will be greater than today's date in chronological order on Flight Status Page",
                   (dropdownDate.getTime()-currentDate.getTime())/(1000 * 60 * 60 * 24)==datediff);

            //increment date by one
            datediff += 1;
        }

        //verify only 5 days appear after today's date
        ValidationUtil.validateTestStep("verify only 5 days appear after todays date on Flight Status Page",
                datediff-1==5);

//STEP--10
        //Enter a date within 24hrs or up to 5 days out but, no flight number . Select 'Check Status' button
        //Enter today's date
        TestUtil.selectDropDownUsingValue(pageObjectManager.getHomePage().getFlightStatusDateDropDown(),TestUtil.getStringDateFormat(DEP_DATE,"yyyy-MM-dd"));

        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();

        //Verify Flight Number not available error message
        ValidationUtil.validateTestStep("Verify Flight Number not available error message on Flight Status Page",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR_FLIGHT_NUMBER);

//STEP--11
        //Select a flight and Date that doesn't have flight and click on check status
        //Enter Flight Number
        pageObjectManager.getHomePage().getFlightStatusFlightNumberTextBox().sendKeys(FAKE_FLIGHT_NUMBER);

        //Enter today's date
        TestUtil.selectDropDownUsingValue(pageObjectManager.getHomePage().getFlightStatusDateDropDown(),TestUtil.getStringDateFormat(DEP_DATE,"yyyy-MM-dd"));

        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();

        //Search criteria result is not available. Error Icon Text
        ValidationUtil.validateTestStep("Verify Search criteria result is not available. Error Icon Text on Flight Status Page",
                pageObjectManager.getCommon().getTriangleAlertMessagLabel().getText(),ERROR_FLIGHT_AVAILABLE);

//STEP--12

        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getFlightStatusFlightNumberTextBox());

        //Select a new flight and date and click on the check status
        //Enter Flight Number
        pageObjectManager.getHomePage().getFlightStatusFlightNumberTextBox().click();
        pageObjectManager.getHomePage().getFlightStatusFlightNumberTextBox().sendKeys(FLIGHT_NUMBER);

        //Enter today's date
        TestUtil.selectDropDownUsingValue(pageObjectManager.getHomePage().getFlightStatusDateDropDown(),TestUtil.getStringDateFormat(DEP_DATE,"yyyy-MM-dd"));

        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();

        //Wait until page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

//STEP--13
        //All points are covered below

//STEP--14
        //Click on the get update for the desired flight
        //click on get update button
        pageObjectManager.getHomePage().getFlightStatusGetUpdateButton().get(0).click();

        //Wait until page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //verify the get status block header
        ValidationUtil.validateTestStep("verify the get status block header on Flight Status Page",
                pageObjectManager.getHomePage().getGetUpdateHeaderText().get(0).getText(),GET_UPDATE_HEADER);

//STEP--15
        //Click on the cancel
        pageObjectManager.getHomePage().getGetUpdateCancelButton().get(0).click();

//STEP--16
        //Once aging select a flight to get update and enter your email and click on get updates
        //click on get update button
        pageObjectManager.getHomePage().getFlightStatusGetUpdateButton().get(0).click();

        //Wait until page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //enter Email
        pageObjectManager.getHomePage().getGetUpdateEmailTextBox().get(0).sendKeys(FAKE_EMAIL);

        //click on get update button
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