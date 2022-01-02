package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.*;
import java.util.Map;
//**********************************************************************************************
//Test Case ID: TC91235
//Description: CP_Header_Registrarme_Login_$9FC_email
//Created By:  Kartik chauhan
//Created On:  2-August-2019
//Reviewed By: Salim Ansari
//Reviewed On: 2-August-2019
//**********************************************************************************************

public class TC91235 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"HomeUI", "NineDFC", "Spanish","Header"})

    public void CP_Header_Registrarme_Login_$9FC_email(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Home PAge******************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91235 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "Spanish";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
//STEP--1
        //create a 9DFC member that is subscribed to email deals
        Map<String, String> memberCredentials = pageMethodManager.getMemberEnrollmentPageMethods().getNew9FCMemberInformation();

//STEP--2
        //Home Page Methods
        pageObjectManager.getHeader().getSpiritLogoImage().click();
//STEP--3
        //select spanish
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //click on sign in link
        pageObjectManager.getHeader().getSignInLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--4
        //verify User name header is displaying on sign in pop-up
        ValidationUtil.validateTestStep("User name header is displaying on sign in pop-up in Spanish language",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getUserNameHeaderText()));

        //verify User name Textbox is displaying on sign in pop-up
        ValidationUtil.validateTestStep("User name Textbox is displaying on sign in pop-up in Spanish language",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getUserNameBox()));

        //verify Password header is displaying on sign in pop-up
        ValidationUtil.validateTestStep("Password header is displaying on sign in pop-up in Spanish language",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getPasswordHeaderText()));

        //verify Password Textbox is displaying on sign in pop-up
        ValidationUtil.validateTestStep("Password Textbox is displaying on sign in pop-up in Spanish language",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getPasswordBox()));

        //verify Reset Password link is displaying on sign in pop-up
        ValidationUtil.validateTestStep("Reset Password link is displaying on sign in pop-up in Spanish language",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getResetPasswordButton()));

        //verify Sign Up link is displaying on sign in pop-up
        ValidationUtil.validateTestStep("Sign Up link is displaying on sign in pop-up in Spanish language",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getSignUpNowLink()));
//STEP--5
        //log-in via 9DFC User
        //wait till page is stable
        WaitUtil.untilPageLoadComplete(getDriver());

        //enter username
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(memberCredentials.get("email"));

        //enter password
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(memberCredentials.get("password"));

        //click on login button
        pageObjectManager.getHomePage().getLoginButton().click();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify user name with log in
        ValidationUtil.validateTestStep("9DFC User is log-in with its name in Spanish language",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSignInLink()));

    }
}