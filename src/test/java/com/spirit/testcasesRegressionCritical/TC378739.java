package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Check7 (TC31241  | TC378739)
//Test Case ID: TC378739
//Description: CP_Header_Ingresar-Login_FS_email
//Created By: Kartik chauhan
//Created On: 1-August-2019
//Reviewed By: Salim Ansari
//Reviewed On: 1-August-2019
//**********************************************************************************************
public class TC378739 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"Spanish" , "Header" , "FreeSpirit"})
    public void CP_Header_Ingresar_Login_FS_email(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC378739 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "Spanish";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
//STEP--1
        //Login account already exist
//STEP--2
        //Home Page Methods
        pageObjectManager.getHeader().getSpiritLogoImage().click();
//STEP--3
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

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
        //log-in via FS User
        //wait till page is stable
        WaitUtil.untilPageLoadComplete(getDriver());

        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("FSEmail");

        //enter username
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(loginCredentialsData.userName);

        //enter password
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(loginCredentialsData.password);

        //click on login button
        pageObjectManager.getHomePage().getLoginButton().click();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify user name with log in
        ValidationUtil.validateTestStep("User is log-in with its name in Spanish language",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSignInLink()));

    }
}