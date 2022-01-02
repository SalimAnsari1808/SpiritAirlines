package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91258
//Description: CP_Header_Ofertas
//Created By: Kartik chauhan
//Created On: 1-August-2019
//Reviewed By: Salim Ansari
//Reviewed On: 1-August-2019
//**********************************************************************************************

public class TC91258 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"HomeUI", "Spanish", "Header"})
    public void CP_Header_Ofertas(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91258 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE = "Spanish";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
//STEP--1
        //Select spanish language
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//STEP--2
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify Deal link in spanish
        ValidationUtil.validateTestStep("Deal link is displaying in Spanish language",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getDealsLinkLink()));
//STEP--3
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on Deal link
        //verify Continue To Step2 Button Clicked successfully
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getDealsLinkLink());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //declare constant used in Validation
        final String DEAL_URL = "https://www.spirit.com/es/vuelos";

        //wait for pagfe load is complete
        WaitUtil.untilPageURLVisible(getDriver(),DEAL_URL);

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Deal page",
                getDriver().getCurrentUrl(), DEAL_URL);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //naviagte back to home page
        getDriver().navigate().back();

        //wait for pagfe load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify user reach to Home page
        ValidationUtil.validateTestStep("Home page is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getBookPathLink()));
    }

}