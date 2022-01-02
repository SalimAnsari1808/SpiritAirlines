package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90753
//Test Case Name: Task 24891: 35200 Home Page_CP_BP_Flight Status by Destination
//Created By:  Gabriela
//Created On:  02-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 09-Aug-2019
//**********************************************************************************************
//BUG: [IN:23954] Flight Status Notification: Successful notification missing after enrolling for updates. Error message displayed
/**10/21/19 test case passed, removed active bug tag**/
/**12/01/19 Same bug returned**/


public class TC90753 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"FlightStatus","ActiveBug"})
    public void Home_Page_CP_BP_Flight_Status_by_Destination(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90753 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Flight Status Constant Values
        final String DESTINATION_INFO    = "On-time? Delayed? Find arrival and departure info here.";
        final String ORIGIN_ERROR        = "Please select an origin";
        final String DESTINATION_ERROR   = "Please select a destination";
        final String NO_INFO             = "There is no flight information available for this flight. Please verify your flight number or origin and destination, and try again.";
        final String GET_UPDATES         = "GET UPDATES";
        final String UPDATES_INFO        = "For your convenience, you can sign up for flight status updates via email. We'll only use this email to send you flight status updates related to this flight.";
        final String DEP_DATE            = "2";
        final String BLACK_COLOR		 = "0, 0, 0";
        final String GREEN_COLOR         = "47, 127, 41";

        //open browser
        openBrowser( platform);

        /***********************Home Page Methods********************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//-- Step 1: Select the Flight Status Tab on the Booking widget from the Home Page  See the attachment
        pageObjectManager.getHomePage().getFlightStatusPathLink().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //verify user navigated to MyTrips Reservation Summary Page
        ValidationUtil.validateTestStep("Verify User clicked on Flight Status tab on Home Page",
                pageObjectManager.getHomePage().getFlightStatusPathLink().getCssValue("color").contains(BLACK_COLOR));

        /***********************Flight Status Page Methods********************************/
//-- Step 2: Select the "Check Flight By Destination" option from the flight status Widget.
        pageObjectManager.getHomePage().getCheckByDestinationLabel().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

//-- Step 3: verify that you have the text " On-Time? Delayed? Find arrival and departure info here."
        //Verify that you have the text " On-Time? Delayed? Find arrival and departure info here."
        ValidationUtil.validateTestStep("Verify that you have the text \" On-Time? Delayed? Find arrival and departure info here.",
                pageObjectManager.getHomePage().getFlightStatusSubHeaderText().getText(),DESTINATION_INFO);

//-- Step 4: Verify that you have three field box: From, TO, and a Calendar
        ValidationUtil.validateTestStep("Validating 'From' Field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getDepartAirportBox()));

        ValidationUtil.validateTestStep("Validating 'To' Field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getArrivalAirportBox()));

        ValidationUtil.validateTestStep("Validating 'Calendar' Field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightStatusDateDropDown()));

//-- Step 5: On the calendar, select a date
        //Enter tomorrow date
        TestUtil.selectDropDownUsingValue(pageObjectManager.getHomePage().getFlightStatusDateDropDown(),TestUtil.getStringDateFormat(DEP_DATE,"yyyy-MM-dd"));
        WaitUtil.untilTimeCompleted(1200);

//-- Step 6: Do not select Point Of Origin (POO) Then push the Check Status button
        pageObjectManager.getHomePage().getArrivalAirportBox().get(0).sendKeys("LAS");

        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();

        //wait for 1.2 sec
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Validate the error on the flight status page when field left blank", pageObjectManager.getCommon().getErrorMessageLabel().getText(),ORIGIN_ERROR);

        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getArrivalAirportBox().get(0));

//-- Step 7: Do not select Point Of Arrival (POA)  Then push the Check Status button
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getHomePage().getDepartAirportBox().get(0).sendKeys("LIM");


        pageObjectManager.getHomePage().getArrivalAirportBox().get(0).click();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHomePage().getArrivalAirportBox().get(0).click();

        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Validate the error on the flight status page when field left blank", pageObjectManager.getCommon().getErrorMessageLabel().getText(),DESTINATION_ERROR);

//-- Step 8: Select a POO, POA and Date that doesn't have flight and click on check status
        pageObjectManager.getHomePage().getArrivalAirportBox().get(0).sendKeys("LAS");

        //Enter tomorrow date
        TestUtil.selectDropDownUsingValue(pageObjectManager.getHomePage().getFlightStatusDateDropDown(),TestUtil.getStringDateFormat(DEP_DATE,"yyyy-MM-dd"));
        WaitUtil.untilTimeCompleted(3000);

        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Search criteria result is not available. Error Icon Text
        ValidationUtil.validateTestStep("Verify Search criteria result is not available. Error Icon Text on Flight Status Page",
                pageObjectManager.getCommon().getTriangleAlertMessagLabel().getText(),NO_INFO);

//-- Step 9: Select a POO, POA and Date and click on check status
        pageObjectManager.getHomePage().getDepartAirportBox().get(0).clear();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHomePage().getDepartAirportBox().get(0).sendKeys("FLL");

        pageObjectManager.getHomePage().getArrivalAirportBox().get(0).clear();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHomePage().getArrivalAirportBox().get(0).sendKeys("LGA");

        //Enter tomorrow date
        TestUtil.selectDropDownUsingValue(pageObjectManager.getHomePage().getFlightStatusDateDropDown(),TestUtil.getStringDateFormat(DEP_DATE,"yyyy-MM-dd"));
        WaitUtil.untilTimeCompleted(3000);

        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 10: On the flight-status page verify that the web matches the wording and image with the Image that is attached to this step
        //Click on the get update for the desired flight
        //click on get update button
        pageObjectManager.getHomePage().getFlightStatusGetUpdateButton().get(0).click();

        //Wait until page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //verify the get status block header
        ValidationUtil.validateTestStep("verify the get status block header on Flight Status Page",
                pageObjectManager.getHomePage().getGetUpdateHeaderText().get(0).getText(),UPDATES_INFO);

        boolean statusFlag = true;

        //on time button
        for(WebElement onTimeStatus : pageObjectManager.getHomePage().getFlightStatusOnTimeText()){
            if(!onTimeStatus.getCssValue("background").contains(GREEN_COLOR)){
                statusFlag = false;
            }
        }

        //verify flight number
        ValidationUtil.validateTestStep("Verify On Time Green color appear on Flight Status Tab on Home Page", statusFlag);
        //Validating right city info displayed
        String depAirportCode = pageObjectManager.getHomePage().getDepartAirportBox().get(0).getAttribute("value").replace(", FL / Miami, FL AREA (FLL)", "");
        String retAirportCode = pageObjectManager.getHomePage().getArrivalAirportBox().get(0).getAttribute("value").replace(", NY - LaGuardia (LGA)","");
        System.out.println("depAirportCode " + depAirportCode);
        System.out.println("retAirportCode " + retAirportCode);

        ValidationUtil.validateTestStep("Validating Dep City information displayed correctly",
                getDriver().findElement(By.xpath("(//i[@class='icon-primary-plane icon-custom mr-3']//following::p)[1]")).getText(), depAirportCode);

        ValidationUtil.validateTestStep("Validating Dep City information displayed correctly",
                getDriver().findElement(By.xpath("(//i[@class='icon-primary-plane icon-custom mr-3']//following::span)[1]")).getText(), retAirportCode);

//-- Step 11: Get the Text "For your convenience, you can sign up for flight status updates via email. We'll only use this email to send you flight status updates related to this flight." with a Content Box to enter email address
        pageObjectManager.getHomePage().getGetUpdateHeaderText().get(0).click();
        WaitUtil.untilTimeCompleted(3000);

        //Updates Info list
        List<WebElement> updatesInfo = getDriver().findElements(By.xpath("//div[@class='card mb-2 collapse show']//p"));
        for (int i = 0; i < updatesInfo.size(); i ++)
        {
            ValidationUtil.validateTestStep("Validating right updates info is displayed per button",
                    updatesInfo.get(i).getText(),UPDATES_INFO);

            //Validating email address content box displayed
            ValidationUtil.validateTestStep("Validating email address content box displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getGetUpdateEmailTextBox()));

            //Validating Cancel button displayed
            ValidationUtil.validateTestStep("Validating Cancel button is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getGetUpdateCancelButton()));

            //Validating get updates displayed
            ValidationUtil.validateTestStep("Validating Get Status content box displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getGetUpdateGetUpdateButton()));
        }

//-- Step 12: Click on the cancel button
        pageObjectManager.getHomePage().getGetUpdateCancelButton().get(0).click();
        WaitUtil.untilTimeCompleted(3000);

//        ValidationUtil.validateTestStep("Validating no updates made",
//                !TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getGetUpdateEmailTextBox()));

//-- Step 13: Select get update once more, enter your email and click on get updates
        pageObjectManager.getHomePage().getFlightStatusGetUpdateButton().get(0).click();
        WaitUtil.untilTimeCompleted(3000);

        //Entering email address
        pageObjectManager.getHomePage().getGetUpdateEmailTextBox().get(0).sendKeys("joe.flyer12@spirit.com");
        WaitUtil.untilTimeCompleted(1200);

        //Clicking on Get updates button
        pageObjectManager.getHomePage().getGetUpdateGetUpdateButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //TODO: [IN:23954] Flight Status Notification: Successful notification missing after enrolling for updates. Error message displayed
        //verify update is send on the mail entered
        ValidationUtil.validateTestStep("verify update is send on the mail entered  on Get Update popup on Flight Status Page",
                pageObjectManager.getHomePage().getGetUpdatePopupHeaderText().getText(),"Success");

        //Closing message
        pageObjectManager.getHomePage().getGetUpdatePopupCloseLink().click();

//-- Step 14: Select a new destination and date and click on the check status
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getDepartAirportBox().get(0));
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHomePage().getDepartAirportBox().get(0).sendKeys("MCO");

        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getArrivalAirportBox().get(0));
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHomePage().getArrivalAirportBox().get(0).sendKeys("FLL");

        //Enter tomorrow date
        TestUtil.selectDropDownUsingValue(pageObjectManager.getHomePage().getFlightStatusDateDropDown(),TestUtil.getStringDateFormat(DEP_DATE,"yyyy-MM-dd"));
        WaitUtil.untilTimeCompleted(3000);

        //click on check status button
        pageObjectManager.getHomePage().getFlightStatusCheckStatusButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating dep city updates after changes",
                !pageObjectManager.getHomePage().getFlightStatusSelectedDepartureAirportText().getText().contains(depAirportCode));

        ValidationUtil.validateTestStep("Validating dep city updates after changes",
                !pageObjectManager.getHomePage().getFlightStatusSelectedArrivalAirportText().getText().contains(retAirportCode));

//-- Step 15: Access Skysales (Website)  testing environment
        //Invalid step
    }
}