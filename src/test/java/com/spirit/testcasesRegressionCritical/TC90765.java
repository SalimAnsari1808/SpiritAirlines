package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC90765
//Test Name:  Home Page_CP_BP_Marketing_Tiles
//Created By : Kartik Chauhan
//Created On : 25-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-July-2019
//**********************************************************************************************
public class TC90765 extends BaseClass{
    @Parameters({"platform"})
    @Test(groups = {"HomeUI"})
    public void Home_Page_CP_BP_Marketing_Tiles(@Optional("NA") String platform) {
        /******************************************************************************
         ************************Navigate to Home Page*********************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90765 under REGRESSION_CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";

        //open browser
        openBrowser(platform);

//STEP 1
        //launch application
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//STEP 2
        ValidationUtil.validateTestStep("Search Widget is displaying on Home Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getSearchWidgetPanel()));

//STEP 3
        ValidationUtil.validateTestStep("Join and Save Panel is displaying in left on Home Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getJoinAndSavePanel()));

        ValidationUtil.validateTestStep("Subscribe and Save Panel is displaying in left on Home Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getSubscribeAndSavePanel()));

        ValidationUtil.validateTestStep("Limited Time offer Panel is displaying in left on Home Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getLimitedTimeOfferPanel()));
//STEP 4
        //create constant
        final String JOIN_SAVE_URL       = "club-enrollment";
        final String SUBSCRIBER_SAVE_URL = "email-notify-sign-in";
        final String LIMITED_TIME_URL    = "bankofamerica.com/apply";

        //click on Join and save tab
        pageObjectManager.getHomePage().getJoinAndSavePanel().click();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Join and Save Tile url is redirecting correctly",
                getDriver().getCurrentUrl(),JOIN_SAVE_URL);

        //navigate back
        getDriver().navigate().back();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--5
        //click on Subscriber and Save tab
        pageObjectManager.getHomePage().getSubscribeAndSavePanel().click();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Subscriber and Save Tile url is redirecting correctly",
                getDriver().getCurrentUrl(),SUBSCRIBER_SAVE_URL);

        //click back
        getDriver().navigate().back();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--6

        //click on Subscriber and Save tab
        pageObjectManager.getHomePage().getLimitedTimeOfferPanel().click();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //Switch to new tab
        TestUtil.switchToWindow(getDriver(),1);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Limited Time Offer Tile url is redirecting correctly",
                getDriver().getCurrentUrl(),LIMITED_TIME_URL);

        //close new open tab and return to main tab
        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--7
        //verify Spirit 101 is displaying
        ValidationUtil.validateTestStep("Spirit101 is displaying at home page",
                pageObjectManager.getHomePage().get_Spirit101ListLink().get(1).isDisplayed());
    }
}