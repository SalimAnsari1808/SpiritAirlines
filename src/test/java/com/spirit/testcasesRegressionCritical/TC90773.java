package com.spirit.testcasesRegressionCritical;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


// **********************************************************************************************
// TestCase ID: TC90773
// TestCase : 35113 CP_Footer_Connect With US
// Created By : Kartik Chauhan
// Created On : 02-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 02-July-2019
// **********************************************************************************************
public class TC90773 extends BaseClass {
    @Parameters ({"platform"})
    @Test (groups = {"Footer"})

    public void CP_Footer_Connect_With_US (@Optional("NA")String platform){
        /******************************************************************************
         ****************************Navigate to Home Page ****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90773 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
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
        ValidationUtil.validateTestStep("Connect WIth Us Header.. is displaying in footer",
                pageObjectManager.getFooter().getConnectWithUsText().isDisplayed());

        //Create Constant
        final String FACEBOOK_URL    = "https://www.facebook.com/SpiritAirlines";
        final String TWITTER_URL     = "https://twitter.com/spiritairlines";
        final String INSTAGRAM_URL   = "https://www.instagram.com/spiritairlines";
        final String YOUTUBE_URL     = "https://www.youtube.com/channel/UCrOwgmAwcw8r8xs34RiIuJg";
        final String TUMBLR_URL      = "https://www.tumblr.com/login_required/spiritairlines";


        //Click on Facebook link image
        pageObjectManager.getFooter().getSocialMediaImageLink().get(0).click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("On Clicking Facebook image .. correct url is displaying",
                getDriver().getCurrentUrl(),FACEBOOK_URL);

        getDriver().navigate().back();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on Twitter link image
        pageObjectManager.getFooter().getSocialMediaImageLink().get(1).click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("On Clicking Twitter image .. correct url is displaying",
                getDriver().getCurrentUrl(),TWITTER_URL);

        getDriver().navigate().back();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on Instagram link image
        pageObjectManager.getFooter().getSocialMediaImageLink().get(2).click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("On Clicking Instagram image .. correct url is displaying",
                getDriver().getCurrentUrl(),INSTAGRAM_URL);

        getDriver().navigate().back();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on YouTube link image
        pageObjectManager.getFooter().getSocialMediaImageLink().get(3).click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("On Clicking YouTube image .. correct url is displaying",
                getDriver().getCurrentUrl(),YOUTUBE_URL);

        getDriver().navigate().back();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on YouTube link image
        pageObjectManager.getFooter().getSocialMediaImageLink().get(4).click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("On Clicking Tumblr image .. correct url is displaying",
                getDriver().getCurrentUrl(),TUMBLR_URL);

        getDriver().navigate().back();

        WaitUtil.untilPageLoadComplete(getDriver());
    }

}

//*****************Add Below xpath in footer page*************************
