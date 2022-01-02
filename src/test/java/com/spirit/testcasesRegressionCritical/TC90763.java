package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC90763
//Title      : Home Page_CP_BP_Link_Where we fly
//Created By : Kartik chauhan
//Created On : 25-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-July-2019
//**********************************************************************************************

public class TC90763 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"HomeUI"})
    public void Home_Page_CP_BP_Link_Where_we_fly (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Home Page******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90763 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String WHERE_WE_FLY_URL   = "route-maps";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        /******************************************************************************
         *******************************FA Page****************************************
         ******************************************************************************/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //click on Where we Fly Link
        pageObjectManager.getHomePage().getWhereWeFlyLink().click();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify Where We Fly...
        ValidationUtil.validateTestStep("Where We Fly.. url is redirecting correctly",
                getDriver().getCurrentUrl(),WHERE_WE_FLY_URL);

        //navigate back
        getDriver().navigate().back();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Where We Fly.. url is redirecting correctly",
                pageObjectManager.getHomePage().getBookPathLink().isDisplayed());
    }
}

