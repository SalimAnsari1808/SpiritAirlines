package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.Keys;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

//**********************************************************************************************
//Test Case ID: TC91216
//Description:  FS Email Subscription_CP_Unsubscribe Account
//Created By : Anthony Cardona
//Created On : 12-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************
public class TC91216 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups={"FreeSpirit","Header","AccountProfileUI"})
    public void FS_Email_Subscription_CP_Unsubscribe_Account(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91216 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //open browser
        openBrowser(platform);

//Step 1: Acess the test environment
        //Home Page Method
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//Step 9: create a FS member that is subscribed to email deals
        String USER_EMAIL = emailDealsFS();

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

//Step 8: User taken to the edit/unsubscribe page
        pageObjectManager.getAccountProfilePage().getEmailDealEmailAddressTextBox().sendKeys(USER_EMAIL);
        pageObjectManager.getAccountProfilePage().getEmailDealContinueButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //validate that the correct buttons and text are displayed if the user is already subscribed
        ValidationUtil.validateTestStep("The update account/unsubscribe message is displayed" , pageObjectManager.getAccountProfilePage().getEmailDealUpdateAccountOrUnsubscribeText().isDisplayed());
        ValidationUtil.validateTestStep("The unsubscribe CheckBox is displayed" , pageObjectManager.getAccountProfilePage().getEmailDealUnsubscribeCheckBox().isDisplayed());
        ValidationUtil.validateTestStep("The Submit Button is displayed" , pageObjectManager.getAccountProfilePage().getEmailDealSubmitChangeButton().isDisplayed());

        //scroll to checkbox
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getAccountProfilePage().getEmailDealUnsubscribeCheckBox());

        //click on checkbox
        TestUtil.mouseClickOnElement(getDriver(),pageObjectManager.getAccountProfilePage().getEmailDealUnsubscribeCheckBox());

        //click on submit button
        pageObjectManager.getAccountProfilePage().getEmailDealSubmitChangeButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(4000);

        //declare constant used on popup
        final String UNSUBSCRIBE_POPUP  = "To ensure delivery of our e-mails, please remember to add deals@p.spiritairlines.com or the domain spiritairlines.com to your address book or approved senders list.";

        //verify popup
        ValidationUtil.validateTestStep("Verify Email is unsubcribe popup appear on Account Summary Page",
                pageObjectManager.getAccountProfilePage().getEmailDealPopupParagraphText().getText(),UNSUBSCRIBE_POPUP);

    }

    private String emailDealsFS()
    {
        //return map of member credentials
        Map<String, String> memberCredentials = createNewFSMember();

        //User Constant Variables
        final String USER_EMAIL     = memberCredentials.get("email");
        final String USER_PASSWORD  = memberCredentials.get("password");
        final String USER_FIRSTNAME = memberCredentials.get("firstName");
        final String USER_LASTNAME  = memberCredentials.get("lastName");

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

    private Map<String, String> createNewFSMember() {

        //click on footer Member enrollment link
        pageObjectManager.getFooter().getFreeSpiritLink().click();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //get passenger First and last name
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("FSAccountTab");

        //**********************************
        //*********Title Drop Down**********
        //**********************************
        //select title
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getUserTitleDropDown(), memberEnrollmentData.title);

        //**********************************
        //*********First Name Box***********
        //**********************************
        //enter the first name
        pageObjectManager.getMemberEnrollmentPage().getUserFirstNameTextBox().sendKeys(memberEnrollmentData.firstName);

        //**********************************
        //*********Middle Name Box**********
        //**********************************
        //enter middle name
        pageObjectManager.getMemberEnrollmentPage().getUserMiddleNameTextBox().sendKeys(memberEnrollmentData.middleName);

        //**********************************
        //*********Last Name Box************
        //**********************************
        //enter last name
        pageObjectManager.getMemberEnrollmentPage().getUserLastNameTextBox().sendKeys(memberEnrollmentData.lastName);

        //**********************************
        //*********Date OF Birth Box********
        //**********************************
        //enter the Date of Birth
        pageObjectManager.getMemberEnrollmentPage().getUserDateOfBirthTextBox().sendKeys(memberEnrollmentData.dob);
        pageObjectManager.getMemberEnrollmentPage().getUserDateOfBirthTextBox().sendKeys(Keys.TAB);

        //**********************************
        //*********Email Box****************
        //**********************************
        //Create variable to generate email id
        String emailvalue = TestUtil.currentDateTimeString() + memberEnrollmentData.email;

        //store email of the FS user
        scenarioContext.setContext(Context.CUSTOMER_EMAIL, emailvalue);

        //enter the Email
        pageObjectManager.getMemberEnrollmentPage().getUserEmailIdTextBox().sendKeys(emailvalue);

        //**********************************
        //*********Confirm Email Box********
        //**********************************
        //enter the Confirm Email
        pageObjectManager.getMemberEnrollmentPage().getUserConfirmEmailIdTextBox().sendKeys(emailvalue);

        //**********************************
        //*********Create Password Box******
        //**********************************
        //enter the Create Password
        pageObjectManager.getMemberEnrollmentPage().getUserCreateAPasswordTextBox().sendKeys(memberEnrollmentData.createPassword);

        //**********************************
        //*********Confirm Password Box*****
        //**********************************
        //enter the Confirm Password
        pageObjectManager.getMemberEnrollmentPage().getConfirmAPasswordTextBox().sendKeys(memberEnrollmentData.confirmPassowrd);

        WaitUtil.untilTimeCompleted(1000);

        //************************************
        //*********Continue To Step2 Button***
        //************************************
        //verify Continue To Step2 Button Clicked successfully
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getMemberEnrollmentPage().getContinueToStep2Button());

        WaitUtil.untilTimeCompleted(1000);

        //**********************************************CONTACT TAB**************************************************************
        MemberEnrollmentData memberEnrollmentContactData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");
        //*********************************
        //*********Address Box*************
        //*********************************
        //enter the Address
        pageObjectManager.getMemberEnrollmentPage().getAddressTextBox().sendKeys(memberEnrollmentContactData.address1);

        //**********************************
        //*********City Box*****************
        //**********************************
        //enter the City
        pageObjectManager.getMemberEnrollmentPage().getCityTextBox().sendKeys(memberEnrollmentContactData.city);

        //**********************************
        //*********Zip/Postal Code Box******
        //**********************************
        //enter the Zip Code
        pageObjectManager.getMemberEnrollmentPage().getPostalCodeTextBox().sendKeys(memberEnrollmentContactData.zipCode);

        //**********************************
        //*********Country DropDown***********
        //**********************************
        //enter the Country
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getCountryDropDown(), memberEnrollmentContactData.country);

        pageObjectManager.getMemberEnrollmentPage().getCountryDropDown().sendKeys(Keys.TAB);

        //**********************************
        //***State/Province DropDown********
        //**********************************
        //enter the State
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getStateDropDown(), memberEnrollmentContactData.state);

        //**********************************
        //*****Primary Phone TextBox********
        //**********************************
        //enter the PrimaryPhone
        pageObjectManager.getMemberEnrollmentPage().getPrimaryPhoneTextBox().sendKeys(memberEnrollmentContactData.phoneNumber);

        //**********************************
        //*****Secondary Phone TextBox******
        //**********************************
        //enter the PrimaryPhone
        pageObjectManager.getMemberEnrollmentPage().getSecondaryPhoneTextBox().sendKeys(memberEnrollmentContactData.secondaryPhone);

        //**********************************
        //*****Home Airport TextBox*********
        //**********************************
        //enter the Home Airport
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getPrimaryAirportDropDown(), memberEnrollmentContactData.homeAirport);

        //**********************************
        //****FS Terms and Condition********
        //**********************************
        //select FS Terms and Condition
        pageObjectManager.getMemberEnrollmentPage().getFSTermsAndConditionText().click();

        //**********************************
        //*********Free Sign Up*************
        //**********************************
        //Click on Free Sign up Button
        pageObjectManager.getMemberEnrollmentPage().getFSSignUpButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 3 sec
        WaitUtil.untilTimeCompleted(3000);

        //**********************************
        //*****Welcome To Free Sign Up******
        //**********************************
        final String FS_WELCOME = "Welcome to Free Spirit!";
        final String FS_WELCOME_SUB_HEADER = "You have successfully registered and can now start earning miles for future award travel";

        //verify popup header for FS account
        ValidationUtil.validateTestStep("Verify Welcome to Free Spirit popup appears on Enrollment Page",
                pageObjectManager.getMemberEnrollmentPage().getPopUpHeaderText().getText().trim().equalsIgnoreCase(FS_WELCOME));

        ValidationUtil.validateTestStep("Verify You have successfully registered... verbiage displays on Enrollment Page",
                pageObjectManager.getMemberEnrollmentPage().getSuccessfullyRegisteredText().getText().trim().contains(FS_WELCOME_SUB_HEADER));

        //close FS Registration popup
        pageObjectManager.getMemberEnrollmentPage().getCloseImage().click();


        //get fs number
        String fsNumberValue = pageObjectManager.getAccountProfilePage().getFreeSpiritNumberText().getText();

        ValidationUtil.validateTestStep("User created a 9DFC account with FSNumber:" + fsNumberValue, true);

        //logout
        if (TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getUserDropDown())) {
            pageObjectManager.getHeader().getUserDropDown().click();

            WaitUtil.untilTimeCompleted(3000);

            pageObjectManager.getHeader().getSignOutUserLink().click();

            WaitUtil.untilPageLoadComplete(getDriver());

            ValidationUtil.validateTestStep("User verify application is logout", !TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getUserDropDown()));

        }

        Map<String, String> memberDetails = new HashMap<String, String>();

        memberDetails.put("title", memberEnrollmentData.title);
        memberDetails.put("firstName", memberEnrollmentData.firstName);
        memberDetails.put("lastName", memberEnrollmentData.lastName);
        memberDetails.put("birthDate", memberEnrollmentData.dob);
        memberDetails.put("email", emailvalue);
        memberDetails.put("fsNumber", fsNumberValue);
        memberDetails.put("password", memberEnrollmentData.createPassword);

        //return 9fc member data
        return memberDetails;
    }

}

