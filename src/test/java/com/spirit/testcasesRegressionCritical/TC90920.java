package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC90920 extends BaseClass {
    //**********************************************************************************************
//Test Case ID: TC90920
//Description: Validate Cruise link hover message and validate the user taken to the correct cruise booking link
//Created By : Anthony Cardona
//Created On : 25-Jun-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
    @Parameters({"platform"})
    @Test(groups = {"HomeUI","CruiseOnly"})
    public void Cruise_Web(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90920 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //open browser
        openBrowser(platform);

        //****************************************************************************
        //**************************Home Page Methods*********************************
        //****************************************************************************/

//Step 1: Open the Test Environment
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //Expected values on the home page
        String       mouseOverMessageExpected       = "External site may not meet accessibility requirements.";
        String       cruiseLinkURL                  = "https://cruises.spirit.com/";

//Step 2: Hover over icon next to cruise and validate the hover message
        //MOUSE OVER THE ELEMENT MESSAGE
        String       toolTipMessage                 = pageObjectManager.getHomePage().getCruiseBookingLink().findElement(By.xpath("//app-external-link//span")).getAttribute("title");

        ValidationUtil.validateTestStep("The tool Tip Message for hovering over cruise link is correct" , mouseOverMessageExpected ,toolTipMessage);

//Step 3: Click on Cruise tab and validate user taken to correct URL
        //Click on the cruise link
        pageObjectManager.getHomePage().getCruiseBookingLink().click();
        WaitUtil.untilTimeCompleted(1000);

        TestUtil.switchToWindow(getDriver(),1);//Switch to new Tab
        WaitUtil.untilTimeCompleted(1000);

        //Validate the user is taken to the correct URL
        ValidationUtil.validateTestStep("The user redirected to the correct Cruise Page" , getDriver().getCurrentUrl().contains(cruiseLinkURL));

    }
}