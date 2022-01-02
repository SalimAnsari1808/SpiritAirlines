package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//@Listeners(com.spirit.testNG.Listener.class)


//**********************************************************************************************
//Test Case ID: TC90760
//Description: CP_Header_Sign_In_Login_FS_email
//Created By : Alex Rodriguez
//Created On : 8-Mar-2018
//Reviewed By: Salim Ansari
//Reviewed On: 17-Mar-2019
//**********************************************************************************************

public class PRODTC90760 extends BaseClass {

	  @Parameters({"platform"})
	  @Test(groups="Production")

	  public void CP_Header_Sign_In_Login_FS_email(@Optional("NA") String platform) {
		/******************************************************************************
		 *******************************Navigate to Home Page**************************
		 ******************************************************************************/
		//Mention Suite and Browser in reports
		if(!platform.equals("NA")) {
			ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90760 under PRODUCTION Suite on " + platform + " Browser"   , true);
		}

		//declare constant used in navigation
		final String LANGUAGE 			= "English";

		//open browser
		openBrowser(platform);

		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		/******************************************************************************
		 *******************************Validation on Home Page**************************
		 ******************************************************************************/
		//Home Page Constant Values
		final String USERNAME_HEADER = "Email Address or Free Spirit Number";
		final String PASSWORD_HEADER = "Password";
		final String LOGIN_ACCOUNT 	 = "FSEmail";
		final String RESET_PASSWORD  = "RESET PASSWORD";

		//get the login credentials from Json file
		LoginCredentialsData loginCredentialsData =FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(LOGIN_ACCOUNT);

		//loop on home page and get all sign in links
		for(WebElement signInLink: pageObjectManager.getHomePage().getSignInListLink()) {
			//check sign in link is visible
			if(signInLink.isDisplayed()) {
				//click on sign in link
				signInLink.click();
			}
		}

		//wait till page is stable
		WaitUtil.untilPageLoadComplete(getDriver());

		//verify username header
		ValidationUtil.validateTestStep("Verify Username Header section on Login popup",
				pageObjectManager.getHomePage().getUserNameHeaderText().getText().trim(),USERNAME_HEADER);

		//verify password header
		ValidationUtil.validateTestStep("Verify Password Header section on Login popup",
				pageObjectManager.getHomePage().getPasswordHeaderText().getText().trim(),PASSWORD_HEADER);

		//verify reset password link
		ValidationUtil.validateTestStep("Verify Reset Password Link on Login popup",
				pageObjectManager.getHomePage().getResetPasswordButton().getText().trim(),RESET_PASSWORD);

		//enter username
		pageObjectManager.getHomePage().getUserNameBox().sendKeys(loginCredentialsData.userName);

		//enter password
		pageObjectManager.getHomePage().getPasswordBox().sendKeys(loginCredentialsData.password);

		//click on login button
		pageObjectManager.getHomePage().getLoginButton().click();

		//wait till page is stable
		WaitUtil.untilPageLoadComplete(getDriver());

		//validate sign in is done
		ValidationUtil.validateTestStep("User Login to Spirit Application with "+ LOGIN_ACCOUNT + " on Home Page", pageObjectManager.getHomePage().getLoginUserIconImage().size()>0);

	  }
}