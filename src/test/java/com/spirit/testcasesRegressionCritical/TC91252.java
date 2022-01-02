package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91252
//Description: My Account_MP_$9FC_Error Message for Login_Email is Required
//Created By : Anthony Cardona
//Created On : 12-JuL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-July-2019
//**********************************************************************************************
public class TC91252 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups={"HomeUI", "Header"})

    public void My_Account_MP_$9FC_Error_Message_for_Login_Email_is_Required(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91252 under Regression-Critical Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			        = "English";
        final String RED_BORDER_COLOR           = "RGB(220, 53, 69";
        final String RED_ERROR_BACKGROUND       = "RGBA(220, 0, 0, 1)";
        final String EMAIL_ERROR_MESSAGE        = "Email Address or Free Spirit Number is required";
        final String INVALID_CREDENTIALS_ERROR  = "Invalid email address or incorrect password. Please correct and re-try or select Sign Up";

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
//Step 1 and 2 open the test environment
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//Step 3 click on the sign in link
        pageObjectManager.getHeader().getSignInLink().click();

//Step 4 add characters to the password field and nor the user name field
        pageObjectManager.getHomePage().getPasswordBox().sendKeys("Test");

//Step 5 click on log in, ERROR SHOULD DISPLAY
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHomePage().getLoginButton().click();

//Step 6 validate error message "Email Address or Free Spirit Number is required" and box becomes highlighted red
        ValidationUtil.validateTestStep("The error message is correct" , pageObjectManager.getCommon().getErrorMessageLabel().getText() , EMAIL_ERROR_MESSAGE);

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("The border is highlighted red", pageObjectManager.getHomePage().getUserNameBox().getCssValue("border-color"), RED_BORDER_COLOR);//highlighted red

//Step 7 click on login againa and validate that an additional error message for password is displayed
        pageObjectManager.getHomePage().getPasswordBox().clear();

        String email                 = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("AccountNineDFCEmail").userName;

        pageObjectManager.getHomePage().getUserNameBox().sendKeys(email);
        pageObjectManager.getHomePage().getLoginButton().click();


//Step 8 Validate the error message "Invalid email address or incorrect password. Please correct and re-try or select Sign Up"
        ValidationUtil.validateTestStep("The error message is incorrect" ,
                pageObjectManager.getCommon().getAlertMessageLabel().getText() , INVALID_CREDENTIALS_ERROR);

        ValidationUtil.validateTestStep("The error message background is red" ,
                pageObjectManager.getCommon().getAlertMessageLabel().getCssValue("background-color") , RED_ERROR_BACKGROUND);
    }
}
