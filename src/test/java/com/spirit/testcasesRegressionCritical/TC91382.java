package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

//**********************************************************************************************
//Check8 (TFS: 31325 | 35148 )
//Test Case ID: TC91382
//Description:  $9FC Email Subscription_CP_Update Email Account
//Created By : Anthony Cardona
//Created On : 12-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************
public class TC91382 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"AccountProfileUI"})
    public void $9FC_Email_Subscription_CP_Update_Email_Account(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91382 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //open browser
        openBrowser(platform);

        //Home Page Method
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//Step 1: create a 9DFC member

        //return map of member credentials
        Map<String, String> memberCredentials = pageMethodManager.getMemberEnrollmentPageMethods().getNew9FCMemberInformation();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        //User Constant Variables
        final String USER_EMAIL = memberCredentials.get("email");
        final String USER_PASSWORD = memberCredentials.get("password");
        final String USER_FIRSTNAME = memberCredentials.get("firstName");
        final String USER_LASTNAME = memberCredentials.get("lastName");

        //Home Page Methods
        pageObjectManager.getHeader().getSignInLink().click();

        //Use email and temporary password on spirit application
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(USER_EMAIL);
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(USER_PASSWORD);
        pageObjectManager.getHomePage().getLoginButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getUserDropDown().click();

//Step 2,3,4,5,6: Go to member account page
        WaitUtil.untilTimeCompleted(1500);
        pageObjectManager.getHeader().getMyAccountUserLink().click();
        ValidationUtil.validateTestStep("The user clicks on My account link", true);

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1500);

//Step 7: Click on manage subscription
        pageObjectManager.getAccountProfilePage().getLeftMenuEmailSubscriptionsLink().click();

//Step 8: Input Customer information on the subscription page
        pageObjectManager.getAccountProfilePage().getEmailDealEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealContinueButton().click();

        //pageObjectManager.getAccountProfilePage().getEmailDealNewEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealConfirmEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealFirstNameTextBox().sendKeys(USER_FIRSTNAME);
        pageObjectManager.getAccountProfilePage().getEmailDealLastNameTextBox().sendKeys(USER_LASTNAME);

        TestUtil.selectDropDownUsingValue(pageObjectManager.getAccountProfilePage().getEmailDealPrimaryAirportDropDown(), "FLL");
        TestUtil.selectDropDownUsingValue(pageObjectManager.getAccountProfilePage().getEmailDealSecondaryAirportDropDown(), "MCO");

        WaitUtil.untilTimeCompleted(1200);

//Step 9 : CLick on subscibe and validate the modal.
        pageObjectManager.getAccountProfilePage().getEmailDealSubscribeButton().click();

        //Deals modal Constant Variables
        final String EMAILDEALS_MODAL_HEADER        = "EMAIL DEALS";
        final String EMAILDEALS_MODAL_SUB_HEADING   = "Your subscription has been successfully created.";
        final String EMAILDEALS_MODAL_PARAGRAPH     = "To ensure delivery of our e-mails, please remember to add deals@p.spiritairlines.com or the domain spiritairlines.com to your address book or approved senders list.";
        final String EMAILDEALS_MODAL_GO_HOME       = "Go To Homepage";
        final String EMAILDEALS_MODAL_CLOSE         = "Close";

        ValidationUtil.validateTestStep("Email Deals Modal Header is displayed",
                pageObjectManager.getAccountProfilePage().getEmailDealPopupHeaderText().getAttribute("innerText"), EMAILDEALS_MODAL_HEADER);

        ValidationUtil.validateTestStep("Email Deals Modal Sub-Heading is displayed",
                pageObjectManager.getAccountProfilePage().getEmailDealPopupSubHeaderText().getAttribute("innerText"), EMAILDEALS_MODAL_SUB_HEADING);

        ValidationUtil.validateTestStep("Email Deals Modal Paragraph is displayed",
                pageObjectManager.getAccountProfilePage().getEmailDealPopupParagraphText().getAttribute("innerText"), EMAILDEALS_MODAL_PARAGRAPH);

        ValidationUtil.validateTestStep("Email Deals Modal Go To Homepage Button is displayed",
                pageObjectManager.getAccountProfilePage().getEmailDealPopupGoToHomepageButton().getAttribute("innerText"),EMAILDEALS_MODAL_GO_HOME);

        ValidationUtil.validateTestStep("Email Deals Modal Close Button is displayed",
                pageObjectManager.getAccountProfilePage().getEmailDealPopupContinueButton().getAttribute("innerText"), EMAILDEALS_MODAL_CLOSE);

        WaitUtil.untilTimeCompleted(1200);
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getAccountProfilePage().getEmailDealPopupContinueButton());
    }
}