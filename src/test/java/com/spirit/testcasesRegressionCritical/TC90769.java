package com.spirit.testcasesRegressionCritical;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
// TestCase ID: TC90769
// TestCase : CP_Footer_Get To Know US_9 FC
// Created By : Kartik Chauhan
// Created On : 02-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 02-July-2019
// **********************************************************************************************
public class TC90769 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups =  {"Footer"})
    public void CP_Footer_Get_To_Know_US_9_FC (@Optional("NA")String platform){
        /******************************************************************************
         ****************************Navigate to Home Page ****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90769 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
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
                pageObjectManager.getFooter().getNineFareClubLink().isDisplayed());

        //Create Constant
        final String DFC_URL    = "club-enrollment";

        //Click on 9DFC link image
        pageObjectManager.getFooter().getNineFareClubLink().click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("On Clicking 9DFC link ... correct url is displaying",
                getDriver().getCurrentUrl(),DFC_URL);

        getDriver().navigate().back();

        WaitUtil.untilPageLoadComplete(getDriver());

        //scroll down to display taxes on screen
        JSExecuteUtil.scrollDown(getDriver(),"4000");
//Step--4
        ValidationUtil.validateTestStep("9DFC fare club link is displaying",
                pageObjectManager.getFooter().getNineFareClubLink().isDisplayed());

    }

}
