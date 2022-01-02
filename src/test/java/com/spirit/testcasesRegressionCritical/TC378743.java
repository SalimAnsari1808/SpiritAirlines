package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;

import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

//**********************************************************************************************
//Check8 (TFS: 31325 | 35148 )
//Test Case ID: TC378743
//Description: Task 24639: 35148 $9FC Email Subscription_CP_Update Email Account (Unsubscribe checkbox)
//Created By : Anthony Cardona
//Created On : 19-Jun-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC378743 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"NineDFC" , "AccountProfileUI"})
    public void $9FC_Email_Subscription_CP_Update_Email_Account_Unsubscribe_checkbox (@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC378743 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //open browser
        openBrowser(platform);

        //Home Page Method
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//Step 1: create a 9DFC member
        //return map of member credentials
        Map<String, String> memberCredentials = pageMethodManager.getMemberEnrollmentPageMethods().getNew9FCMemberInformation();

        //User Constant Variables
        final String USER_EMAIL        = memberCredentials.get("email");
        final String USER_PASSWORD     = memberCredentials.get("password");
        final String USER_FIRSTNAME    = memberCredentials.get("firstName");
        final String USER_LASTNAME     = memberCredentials.get("lastName");

//Step 2 and 3: click to log in
        //Home Page Methods
        pageObjectManager.getHeader().getSignInLink().click();

//Step 4: Log in to member
        //Use email and temporary password on spirit application
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(USER_EMAIL);
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(USER_PASSWORD);
        pageObjectManager.getHomePage().getLoginButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 5: click on user name
        pageObjectManager.getHeader().getUserDropDown().click();

        //wait for 1.5 sec
        WaitUtil.untilTimeCompleted(1500);

//Step 6: click to go to my account
        //Click on my account
        pageObjectManager.getHeader().getMyAccountUserLink().click();
        ValidationUtil.validateTestStep("The user clicks on My account link", true);

        //wait for account profile page
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 7: click Email subscription on left options
        //Click on my account
        pageObjectManager.getAccountProfilePage().getLeftMenuEmailSubscriptionsLink().click();
        ValidationUtil.validateTestStep("The user clicks on Email Subscription link", true);
        //wait for account profile page
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 8: Type in email and click continue
        pageObjectManager.getAccountProfilePage().getEmailDealEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealContinueButton().click();

//User taken to second sign up page, input information, and validates pop up
        pageObjectManager.getAccountProfilePage().getEmailDealConfirmEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealFirstNameTextBox().sendKeys(USER_FIRSTNAME);
        pageObjectManager.getAccountProfilePage().getEmailDealLastNameTextBox().sendKeys(USER_LASTNAME);

        TestUtil.selectDropDownUsingValue(pageObjectManager.getAccountProfilePage().getEmailDealPrimaryAirportDropDown(),"FLL");
        TestUtil.selectDropDownUsingValue(pageObjectManager.getAccountProfilePage().getEmailDealSecondaryAirportDropDown(),"MCO");

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getAccountProfilePage().getEmailDealSubscribeButton().click();

        //Deals modal Constant Variables
        final String EMAILDEALS_MODAL_HEADER        = "EMAIL DEALS";
        final String EMAILDEALS_MODAL_SUB_HEADING   = "Your subscription has been successfully created.";
        final String EMAILDEALS_MODAL_SUB_HEADING_1 = "Your subscription has been successfully updated.";
        final String EMAILDEALS_MODAL_PARAGRAPH     = "To ensure delivery of our e-mails, please remember to add deals@p.spiritairlines.com or the domain spiritairlines.com to your address book or approved senders list.";
        final String EMAILDEALS_MODAL_GO_HOME       = "Go To Homepage";
        final String EMAILDEALS_MODAL_CLOSE         = "Close";

        ValidationUtil.validateTestStep("Email Deals Modal Header is displayed", pageObjectManager.getAccountProfilePage().getEmailDealPopupHeaderText().getAttribute("innerText"), EMAILDEALS_MODAL_HEADER);
        ValidationUtil.validateTestStep("Email Deals Modal Sub-Heading is displayed", pageObjectManager.getAccountProfilePage().getEmailDealPopupSubHeaderText().getAttribute("innerText"), EMAILDEALS_MODAL_SUB_HEADING);
        ValidationUtil.validateTestStep("Email Deals Modal Paragraph is displayed", pageObjectManager.getAccountProfilePage().getEmailDealPopupParagraphText().getAttribute("innerText"), EMAILDEALS_MODAL_PARAGRAPH);
        ValidationUtil.validateTestStep("Email Deals Modal Go To Homepage Button is displayed", pageObjectManager.getAccountProfilePage().getEmailDealPopupGoToHomepageButton().getAttribute("innerText"),EMAILDEALS_MODAL_GO_HOME);
        ValidationUtil.validateTestStep("Email Deals Modal Close Button is displayed", pageObjectManager.getAccountProfilePage().getEmailDealPopupContinueButton().getAttribute("innerText"), EMAILDEALS_MODAL_CLOSE);

//Step 9: Click on the "Unsubscribe from Email Deals." checkbox and click on "SUBMIT" button
        //Close pop up
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getEmailDealPopupContinueButton().click();

        //Type in email and click continue
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getEmailDealEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealContinueButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getEmailDealUnsubscribeCheckBox().click();
        pageObjectManager.getAccountProfilePage().getEmailDealSubmitChangeButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Email Deals Modal Header is displayed", pageObjectManager.getAccountProfilePage().getEmailDealPopupHeaderText().getAttribute("innerText"), EMAILDEALS_MODAL_HEADER);
        ValidationUtil.validateTestStep("Email Deals Modal Sub-Heading is displayed", pageObjectManager.getAccountProfilePage().getEmailDealPopupSubHeaderText().getAttribute("innerText"), EMAILDEALS_MODAL_SUB_HEADING_1);
        ValidationUtil.validateTestStep("Email Deals Modal Paragraph is displayed", pageObjectManager.getAccountProfilePage().getEmailDealPopupParagraphText().getAttribute("innerText"), EMAILDEALS_MODAL_PARAGRAPH);
        ValidationUtil.validateTestStep("Email Deals Modal Go To Homepage Button is displayed", pageObjectManager.getAccountProfilePage().getEmailDealPopupGoToHomepageButton().getAttribute("innerText"),EMAILDEALS_MODAL_GO_HOME);
        ValidationUtil.validateTestStep("Email Deals Modal Close Button is displayed", pageObjectManager.getAccountProfilePage().getEmailDealPopupContinueButton().getAttribute("innerText"), EMAILDEALS_MODAL_CLOSE);
    }
}