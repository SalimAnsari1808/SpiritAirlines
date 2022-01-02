package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90921
//Description: Validate hotel link hover message and validate the user taken to the correct hotel booking link
//Created By : Anthony Cardona
//Created On : 25-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 26-Jul-2019
//**********************************************************************************************

public class TC90921 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"HomeUI","HotelOnly"})
    public void Hotel_Only_web(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90921 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //****************************************************************************
        //**************************Home Page Methods*********************************
        //****************************************************************************/

        //Expected values on the home page
        final String mouseOverMessageExpected       = "External site may not meet accessibility requirements.";
        final String hotelLinkURL                   = "https://www.spiritairhotels.com/";

        //open browser
        openBrowser(platform);

//Step 1: Open the Test Environment
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//Step 2: Hover over icon next to hotel and validate the hover message
        //MOUSE OVER THE ELEMENT MESSAGE
        String toolTipMessage = pageObjectManager.getHomePage().getHotelBookingLink().findElement(By.xpath("//app-external-link//span")).getAttribute("title");

        ValidationUtil.validateTestStep("The tool Tip Message for hovering over hotel link is correct" , mouseOverMessageExpected ,toolTipMessage);

//Step 3: Click on hotel tab and validate user taken to correct URL
        //Click on the hotel link
        pageObjectManager.getHomePage().getHotelBookingLink().click();
        WaitUtil.untilTimeCompleted(1000);

        TestUtil.switchToWindow(getDriver(),1);//Switch to new Tab
        WaitUtil.untilTimeCompleted(1000);

        //Validate the user is taken to the correct URL
        ValidationUtil.validateTestStep("The user redirected to the correct hotel Page" , getDriver().getCurrentUrl(),hotelLinkURL);
    }
}