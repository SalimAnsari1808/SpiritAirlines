package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91254
//Test Case Name: Task 24847: 35153 My Account_CP_FS_Error Message for Login_Email is Required
//Created By : Gabriela
//Created On : 28-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 01-July-2019
//**********************************************************************************************

public class TC91254 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"HomeUI"})
    public void My_Account_CP_FS_Error_Message_for_Login_Email_is_Required(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91254 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String FS_NUM_SYMBOL      = "*&^%$#@!";
        final String ERROR1             = "Email Address or Free Spirit Number is required";
        final String INVALID_EMAIL      = "ASDF";
        final String ERROR2             = "Email address is invalid";
        final String ERROR3             = "Invalid email address or incorrect password. Please correct and re-try or select Sign Up.";

        //open browser
        openBrowser(platform);
        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
//-- Step 1: open Test Environment
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//-- Step 2: In the top right hand corner of the website. Click on the Sign In.
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getSignInListLink().get(1).click();

//-- Step 3: Add non Alpha numeric in the password but not the email
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(FS_NUM_SYMBOL);

//-- Step 4: Click on the Login Button
        pageObjectManager.getHomePage().getLoginButton().click();

//-- Step 5: Validate the verbiage of the error. "Email Address or FREE SPIRIT Number is required"
        ValidationUtil.validateTestStep("Validating the right error message is displayed when email is missing",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR1);

//-- Step 6: Enter "ASDF" into the Email Field and Click on the Login Button
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(INVALID_EMAIL);
        pageObjectManager.getHomePage().getLoginButton().click();

//-- Step 7: Validate the error message. "Email address is invalid"
        ValidationUtil.validateTestStep("Validating the right error message is displayed for invalid email",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR2);

//-- Step 8: Enter a Valid FS member Email address but make sure the password is wrong. Click on the Login button
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("FSEmail");
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getUserNameBox());
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getHomePage().getLoginButton().click();

//-- Step 9: Validate the verbiage of the error. "Invalid email address or incorrect password. Please correct and re-try or select Sign up."
        ValidationUtil.validateTestStep("Validating the right error message is displayed when email is missing",
                pageObjectManager.getCommon().getAlertMessageLabel().getText().trim(),ERROR3);
    }
}