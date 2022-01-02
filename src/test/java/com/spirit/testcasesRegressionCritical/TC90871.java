package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90871
//Description: Search_Widget_CP_BP_interact_with_Miles_login_Popup
//Created By : Anthony Cardona
//Created On : 05-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 06-Aug-2019
//**********************************************************************************************

public class TC90871 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"HomeUI","Miles"})
    public void Search_Widget_CP_BP_interact_with_Miles_login_Popup(@Optional("NA") String platform) {

        /******************************************************************************
         ***************************Navigate to Home Page******************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90871 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			        = "English";
        final String JOURNEY_TYPE 		        = "Flight";

        //Validation Variables
        final String INVALID_EMAIL              = "NotARealEmail@spirit.com";
        final String PASSWORD_INCORRECT         = "Password1";
        final String RED_ERROR_BACKGROUND       = "RGBA(220, 0, 0, 1)";
        final String INVALID_CREDENTIALS_ERROR  = "Invalid email address or incorrect password. Please correct and re-try or select Sign Up";

//--Step 1: Access test environment and start booking and reach flight availability page
        //open browser
        openBrowser(platform);

        //Starting booking
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

//Step 2: User clicks on miles and Log in modal displays
        pageObjectManager.getHomePage().getMilesLabel().click();
        WaitUtil.untilTimeCompleted(2000);

//Step 3: Validate user taken to retrieve password page
        String resetPasswordLink         = getDriver().getCurrentUrl()  + pageObjectManager.getHomePage().getResetPasswordButton().getAttribute("routerlink");
        JSExecuteUtil.openNewTabWithGivenURL(getDriver(),resetPasswordLink);
        TestUtil.switchToWindow(getDriver(),1);//Switch to new Tab

        final String RETRIEVE_PASSWORD_URL   = "retrieve-password";

        WaitUtil.untilPageLoadComplete(getDriver());
        //verify user navigated to retrieve password page
        ValidationUtil.validateTestStep("The user correctly taken to the retrieve password  page", getDriver().getCurrentUrl(),RETRIEVE_PASSWORD_URL);

        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);//Switch to new Tab

//Step 4: Validate user taken to Sign Up page
        String signUpNowLink         = pageObjectManager.getHomePage().getSignUpNowLink().getAttribute("href");
        JSExecuteUtil.openNewTabWithGivenURL(getDriver(),signUpNowLink);
        TestUtil.switchToWindow(getDriver(),1);//Switch to new Tab


        final String SIGN_UP_NOW_URL   = "account-enrollment";

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user correctly taken to the Sign up now  page", getDriver().getCurrentUrl(),SIGN_UP_NOW_URL);
        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);//Switch to new Tab

//Step 5: input invalid email address, validate error message

        pageObjectManager.getHomePage().getUserNameBox().sendKeys(INVALID_EMAIL);
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(PASSWORD_INCORRECT);

        pageObjectManager.getHomePage().getLoginButton().click();

        ValidationUtil.validateTestStep("The error message is incorrect" ,
                pageObjectManager.getCommon().getAlertMessageLabel().getText() , INVALID_CREDENTIALS_ERROR);

        ValidationUtil.validateTestStep("The error message background is red" ,
                pageObjectManager.getCommon().getAlertMessageLabel().getCssValue("background-color") , RED_ERROR_BACKGROUND);

        //Clear text box content
        pageObjectManager.getHomePage().getUserNameBox().clear();
        pageObjectManager.getHomePage().getPasswordBox().clear();

//Step 6: input invalid password, validate error message
        //User signs into the Log-In modal with No miles User
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("ZeroMiles");

        pageObjectManager.getHomePage().getUserNameBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(PASSWORD_INCORRECT);

        ValidationUtil.validateTestStep("The error message is incorrect" ,
                pageObjectManager.getCommon().getAlertMessageLabel().getText() , INVALID_CREDENTIALS_ERROR);

        ValidationUtil.validateTestStep("The error message background is red" ,
                pageObjectManager.getCommon().getAlertMessageLabel().getCssValue("background-color") , RED_ERROR_BACKGROUND);

//Step 7: Click on close on the pop-up modal
        pageObjectManager.getHomePage().getLoginPopUpCloseButton().click();
    }
}