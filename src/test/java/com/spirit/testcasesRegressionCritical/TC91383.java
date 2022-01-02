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
//Test Case ID: TC91383
//Description:  $9FC Email Subscription_CP_NEG_Unable to Subscribe with existing email
//Created By : Anthony Cardona
//Created On : 24-Jun-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC91383 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"NineDFC" , "AccountProfileUI"})
    public void $9FC_Email_Subscription_CP_NEG_Unable_to_Subscribe_with_existing_email(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91383 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //open browser
        openBrowser(platform);

        //Home Page Method
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//Step 1: create a 9DFC member that is subscribed to email deals
        String USER_EMAIL = emailDeals9FC();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getUserDropDown().click();

//Step 2,3,4,5: Go to member account page
        WaitUtil.untilTimeCompleted(1500);
        pageObjectManager.getHeader().getMyAccountUserLink().click();
        ValidationUtil.validateTestStep("The user clicks on My account link", true);

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1500);

//Step 6: Click on manage subscription
        pageObjectManager.getAccountProfilePage().getLeftMenuEmailSubscriptionsLink().click();

//Step 7: User taken to the edit/unsubscribe page
        pageObjectManager.getAccountProfilePage().getEmailDealEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealContinueButton().click();

        //validate that the correct buttons and text are displayed if the user is already subscribed
        ValidationUtil.validateTestStep("The update account/unsubscribe message is displayed" , pageObjectManager.getAccountProfilePage().getEmailDealUpdateAccountOrUnsubscribeText().isDisplayed());
        ValidationUtil.validateTestStep("The unsubscribe CheckBox is displayed" , pageObjectManager.getAccountProfilePage().getEmailDealUnsubscribeCheckBox().isDisplayed());
        ValidationUtil.validateTestStep("The Submit Button is displayed" , pageObjectManager.getAccountProfilePage().getEmailDealSubmitChangeButton().isDisplayed());

        pageObjectManager.getAccountProfilePage().getEmailDealUnsubscribeCheckBox().click();
        pageObjectManager.getAccountProfilePage().getEmailDealSubmitChangeButton().click();
    }

    private String emailDeals9FC()
    {
        //return map of member credentials
        Map<String, String> memberCredentials = pageMethodManager.getMemberEnrollmentPageMethods().getNew9FCMemberInformation();

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

        //wait for 1.5 sec
        WaitUtil.untilTimeCompleted(1500);

        //Click on my account
        pageObjectManager.getHeader().getMyAccountUserLink().click();
        ValidationUtil.validateTestStep("The user clicks on My account link", true);

        //wait for account profile page
        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on my account
        pageObjectManager.getAccountProfilePage().getLeftMenuEmailSubscriptionsLink().click();
        ValidationUtil.validateTestStep("The user clicks on Email Subscription link", true);
        //wait for account profile page
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getAccountProfilePage().getEmailDealEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealContinueButton().click();

        //pageObjectManager.getAccountProfilePage().getEmailDealNewEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealConfirmEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealFirstNameTextBox().sendKeys(USER_FIRSTNAME);
        pageObjectManager.getAccountProfilePage().getEmailDealLastNameTextBox().sendKeys(USER_LASTNAME);

        TestUtil.selectDropDownUsingValue(pageObjectManager.getAccountProfilePage().getEmailDealPrimaryAirportDropDown(), "FLL");
        TestUtil.selectDropDownUsingValue(pageObjectManager.getAccountProfilePage().getEmailDealSecondaryAirportDropDown(), "MCO");

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getAccountProfilePage().getEmailDealSubscribeButton().click();
        WaitUtil.untilTimeCompleted(1200);
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getAccountProfilePage().getEmailDealPopupContinueButton());

        return USER_EMAIL;
    }

}
