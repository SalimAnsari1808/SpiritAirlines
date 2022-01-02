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
//Test CaseID: TC90807
//Title      : Home Page_CP_BP_Flight Status by Number_Status On time
//Created By : Salim Ansari
//Created On : 05-Aug-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 05-Aug-2019
//**********************************************************************************************
public class TC90807 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"FlightStatus"})
    public void Home_Page_CP_BP_Flight_Status_by_Number_Status_On_time (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Home Page******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90807 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";

        final String BLACK_COLOR_RGBA   = "rgba(0, 0, 0, 1)";
        final String BLACK_COLOR_RGB    = "rgb(0, 0, 0)";
        final String BLUE_COLOR_RGBA    = "rgba(0, 115, 230, 1)";
        final String BLUE_COLOR_RGB     = "rgb(0, 115, 230)";
        final String GREEN_COLOR_RGBA   = "rgba(47, 127, 41, 1)";
        final String GREEN_COLOR_RGB    = "rgb(47, 127, 41)";
        final String FLIGHT_NUMBER      = "511";
        final String DEP_DATE           = "1";
        final String TERMINAL           = "Terminal:";
        final String GATE               = "Gate:";
        final String FLIGHT_DURATION    = "Flight Duration";
        final String Time               = "min";


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

        boolean colorIsCorrect = pageObjectManager.getHomePage().getFlightStatusPathLink().getCssValue("color").toLowerCase().contains(BLACK_COLOR_RGBA)
                ||  pageObjectManager.getHomePage().getFlightStatusPathLink().getCssValue("color").toLowerCase().contains(BLACK_COLOR_RGB );
        //verify user navigated to MyTrips Reservation Summary Page

        ValidationUtil.validateTestStep("Verify User clicked on Flight Status tab on Home Page and tab color is now black", colorIsCorrect);
//STEP--3
        //Select the "Check Flight Status" BY "NUMBER" option from the flight status Widget See the attachment
        //click on flight status link
        pageObjectManager.getHomePage().getCheckByFlightNumberLabel().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //verify flight number text box
        ValidationUtil.validateTestStep("Verify Flight Number text box appear on Flight Status Tab on Home Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightStatusFlightNumberTextBox()));

        //verify flight date drop down
        ValidationUtil.validateTestStep("Verify Flight Date Drop Down appear on Flight Status Tab on Home Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightStatusDateDropDown()));

        //verify check status button
        ValidationUtil.validateTestStep("Verify Check Status button appear on Flight Status Tab on Home Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightStatusCheckStatusButton()));

        //verify check status button color

        colorIsCorrect = pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().getCssValue("background-color").toLowerCase().contains(BLUE_COLOR_RGB)
                ||  pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().getCssValue("background-color").toLowerCase().contains(BLUE_COLOR_RGBA);
        ValidationUtil.validateTestStep("Verify Check Status button color appears blue on the Flight Status Tab on Home Page", colorIsCorrect);

//STEP--4
        //Input an Flight Number for tomorrow Then click on Check status
        //Enter Flight Number
        pageObjectManager.getHomePage().getFlightStatusFlightNumberTextBox().sendKeys(FLIGHT_NUMBER);

        //Enter tomorrow date
        TestUtil.selectDropDownUsingValue(pageObjectManager.getHomePage().getFlightStatusDateDropDown(),TestUtil.getStringDateFormat(DEP_DATE,"yyyy-MM-dd"));

        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();

//        The selected Flight should be displayed on the defined date. Verify that the following information is displayed:
//        Flight number, day, date, time and flight. The displayed status must be ON TIME (on green, solid color) See the attachment

//STEP--5
        boolean statusFlag = true;
        WaitUtil.untilTimeCompleted(1000);

        for(WebElement flightNumber : pageObjectManager.getHomePage().getFLightStatusFlightNumberText()){
            if(!flightNumber.getText().equals(FLIGHT_NUMBER)){
                statusFlag = false;
            }
        }

        //verify flight number
        ValidationUtil.validateTestStep("Verify correct Flight Number appear on Flight Status Tab on Home Page", statusFlag);

        //verify day, date
        ValidationUtil.validateTestStep("Verify correct day, date appear on Flight Status Tab on Home Page",
                pageObjectManager.getHomePage().getFlightStatusDateText().getText(),TestUtil.getStringDateFormat(DEP_DATE,"EEEE, MMMM dd"));

        statusFlag = true;

        //on time button
        for(WebElement onTimeStatus : pageObjectManager.getHomePage().getFlightStatusOnTimeText()){

            boolean greenColor = onTimeStatus.getCssValue("background").toLowerCase().contains(GREEN_COLOR_RGBA) || onTimeStatus.getCssValue("background").toLowerCase().contains(GREEN_COLOR_RGB);

            if(!greenColor){
                statusFlag = false;
            }
        }

        //verify flight number
        ValidationUtil.validateTestStep("Verify On Time Green color appears on Flight Status Tab on Home Page", statusFlag);

//STEP--6
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
    }
}
