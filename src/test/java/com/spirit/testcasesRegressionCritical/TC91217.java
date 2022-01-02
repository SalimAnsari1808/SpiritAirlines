package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

//**********************************************************************************************
//Test Case ID: TC91217
//Description:  FS Email Subscription_CP_Update Email Account
//Created By : Anthony Cardona
//Created On : 22-JuL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 24-JuL-2019
//**********************************************************************************************

public class TC91217 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"FreeSpirit","Header","AccountProfileUI"})

    public void FS_Email_Subscription_CP_Update_Email_Account(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91217 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //open browser
        openBrowser(platform);

        //Home Page Method
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//Step 1: create a FS member that is subscribed to email deals
        Map<String, String> memberCredentials = createNewFSMember();

        //User Constant Variables
        final String USER_EMAIL     = memberCredentials.get("email");
        final String USER_PASSWORD  = memberCredentials.get("password");
        final String USER_FIRSTNAME = memberCredentials.get("firstName");
        final String USER_LASTNAME  = memberCredentials.get("lastName");

        //Home Page Methods
        pageObjectManager.getHeader().getSignInLink().click();


//Step 2,3,4,5: Go to member account page
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

//Step 6: Click on Email subscription
        pageObjectManager.getAccountProfilePage().getLeftMenuEmailSubscriptionsLink().click();
        ValidationUtil.validateTestStep("The user clicks on Email Subscription link", true);
        //wait for account profile page
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 7: Input Email and click continue
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

//Step 8: User succefully registed
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

        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getAccountProfilePage().getEmailDealPopupContinueButton());
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