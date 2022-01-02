package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
// TestCase ID: TC90776
// TestCase : CP_Footer_Spirit Master Card
// Created By : Kartik Chauhan
// Created On : 01-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 02-July-2019
// **********************************************************************************************
public class PRODTC90776 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups="Production")

    public void CP_Footer_Spirit_Master_Card (@Optional("NA")String platform){
        /******************************************************************************
         ****************************Navigate to Bags Page via FA user*****************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90776 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE      = "English";

//Step--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step--2
        //scroll down to display taxes on screen
        JSExecuteUtil.scrollDown(getDriver(),"4000");
//Step--3
        ValidationUtil.validateTestStep("Master Card Image is displaying in footer",
                pageObjectManager.getFooter().getMasterCardImage().isDisplayed());

        ValidationUtil.validateTestStep("'UpTo 30000 Miles' is displaying in footer",
                pageObjectManager.getFooter().getMasterCardUpToBonusMilesVerbiage().isDisplayed());
//Step--4
        ValidationUtil.validateTestStep("Apply Now Button is displaying in footer",
                pageObjectManager.getFooter().getMasterCardApplyNowButton().isDisplayed());

        //Click on Apply Now Button
        pageObjectManager.getFooter().getMasterCardApplyNowButton().click();

        //switch to new tab
        TestUtil.switchToWindow(getDriver(),1);

        //Create Constant
        final String BANK_OF_AMERICA_URL = "bankofamerica.com";

        //verify url navigated
        ValidationUtil.validateTestStep("User verify World MasterCard redirect link is navigated correctly",
                getDriver().getCurrentUrl(),BANK_OF_AMERICA_URL);

        ValidationUtil.validateTestStep("User verify World MasterCard redirect link is not broken",
                TestUtil.verifyLinks(getDriver().getCurrentUrl()));

        //close new open tab
        getDriver().close();

        //switch to default tab
        TestUtil.switchToWindow(getDriver(),0);

        WaitUtil.untilPageLoadComplete(getDriver(), (long) 2000);

    }

}
