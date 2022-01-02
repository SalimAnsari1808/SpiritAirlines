package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC91477
// TestCase : CP_Links on General_Terms_and_Conditions PDF
// Description: In this test case user need to verify the links appear in pdf file. xpath to links
//              present in pdf file is only created for Firefox browser. For browser apart from Firefox
//              xpaths cannot be created as link will be part of document that Selenium cannot read.
//              So this test case will only run in Firefox not in any other browser.
// Created By : Kartik Chauhan
// Created On : 04-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 05-July-2019
// **********************************************************************************************
public class TC91477 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups = {"Footer"})
    public void CP_Links_on_General_Terms_and_Conditions_PDF (@Optional("NA")String platform){
        /******************************************************************************
         ****************************Navigate to Home Page ****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91477 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
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
        ValidationUtil.validateTestStep("9DFC fare club link is displaying",
                pageObjectManager.getFooter().getLegalLink().isDisplayed());

        //Create Constant
        final String LEGAL_URL      = "legal";
        final String GENERAL_TC_URL = "https://cms10dss.spirit.com/Shared/en-us/Documents/General_Terms_and_Conditions.pdf";

        //Click on Legal link image
        pageObjectManager.getFooter().getLegalLink().click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("On Clicking Legal link ... correct url is displaying",
                getDriver().getCurrentUrl(),LEGAL_URL);
//STEP--4
        //verify General Terms and Condition Link is displaying
        ValidationUtil.validateTestStep("General Terms and Condition Link is displaying",
                pageObjectManager.getFooter().getLegalGeneralTermsLink().isDisplayed());

        //Click on General terms and Condition Link of Legal link image
        pageObjectManager.getFooter().getLegalGeneralTermsLink().click();

        //verify General Terms and Condition Link is displaying
        ValidationUtil.validateTestStep("General Terms and Condition Link is displaying",
                getDriver().getCurrentUrl(),GENERAL_TC_URL);

        getDriver().navigate().back();

        WaitUtil.untilPageLoadComplete(getDriver());

        getDriver().navigate().back();

        WaitUtil.untilPageLoadComplete(getDriver());

        //scroll down to display taxes on screen
        JSExecuteUtil.scrollDown(getDriver(),"4000");

        ValidationUtil.validateTestStep("Legal link is displaying",
                pageObjectManager.getFooter().getLegalLink().isDisplayed());
    }
}
