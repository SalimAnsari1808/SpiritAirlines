package com.spirit.pageMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.CardDetailsData;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.AccountProfilePage;
import com.spirit.pageObjects.Footer;
import com.spirit.pageObjects.Header;
import com.spirit.pageObjects.MemberEnrollmentPage;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class MemberEnrollmentPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private MemberEnrollmentPage enrollmentPage;
    private Footer footer;
    private AccountProfilePage accountProfilePage;
    private Header header;


    public MemberEnrollmentPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        enrollmentPage = pageObjectManager.getMemberEnrollmentPage();
        footer = pageObjectManager.getFooter();
        accountProfilePage = pageObjectManager.getAccountProfilePage();
        header   = pageObjectManager.getHeader();
    }


    // **********************************************************************************************
    // Method : createNewFSMember
    // Description: Method is used to Create FS Member from Enrollment Page
    // Input Arguments: NA
    // Return:
    // Created By : Sunny Sok
    // Created On : 04-Apr-2019
    // Reviewed By:
    // Reviewed On:
    // ***********************************************************************************************
    public synchronized void createNewFSMember() {

        //click on footer Member enrollment link
        //footer.getFreeSpiritLink().click();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(driver);

        //get passenger First and last name
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("FSAccountTab");

        //**********************************
        //*********Title Drop Down**********
        //**********************************
        //select title
        TestUtil.selectDropDownUsingVisibleText(enrollmentPage.getUserTitleDropDown(), memberEnrollmentData.title);

        //**********************************
        //*********First Name Box***********
        //**********************************
        //enter the first name
        enrollmentPage.getUserFirstNameTextBox().sendKeys(memberEnrollmentData.firstName);

        //**********************************
        //*********Middle Name Box**********
        //**********************************
        //enter middle name
        enrollmentPage.getUserMiddleNameTextBox().sendKeys(memberEnrollmentData.middleName);

        //**********************************
        //*********Last Name Box************
        //**********************************
        //enter last name
        enrollmentPage.getUserLastNameTextBox().sendKeys(memberEnrollmentData.lastName);

        //**********************************
        //*********Date OF Birth Box********
        //**********************************
        //enter the Date of Birth
        enrollmentPage.getUserDateOfBirthTextBox().sendKeys(memberEnrollmentData.dob);
        enrollmentPage.getUserDateOfBirthTextBox().sendKeys(Keys.TAB);

        //**********************************
        //*********Email Box****************
        //**********************************
        //Create variable to generate email id
        String emailvalue = TestUtil.currentDateTimeString() + memberEnrollmentData.email;

        //store email of the FS user
        scenarioContext.setContext(Context.CUSTOMER_EMAIL,emailvalue);

        //enter the Email
        enrollmentPage.getUserEmailIdTextBox().sendKeys(emailvalue);

        //**********************************
        //*********Confirm Email Box********
        //**********************************
        //enter the Confirm Email
        enrollmentPage.getUserConfirmEmailIdTextBox().sendKeys(emailvalue);

        //**********************************
        //*********Create Password Box******
        //**********************************
        //enter the Create Password
        enrollmentPage.getUserCreateAPasswordTextBox().sendKeys(memberEnrollmentData.createPassword);

        //**********************************
        //*********Confirm Password Box*****
        //**********************************
        //enter the Confirm Password
        enrollmentPage.getConfirmAPasswordTextBox().sendKeys(memberEnrollmentData.confirmPassowrd);

        WaitUtil.untilTimeCompleted(1000);

        //************************************
        //*********Continue To Step2 Button***
        //************************************
        //verify Continue To Step2 Button Clicked successfully
        JSExecuteUtil.clickOnElement(driver,enrollmentPage.getContinueToStep2Button());

        WaitUtil.untilTimeCompleted(1000);

        //**********************************************CONTACT TAB**************************************************************
        MemberEnrollmentData memberEnrollmentContactData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");
        //*********************************
        //*********Address Box*************
        //*********************************
        //enter the Address
        enrollmentPage.getAddressTextBox().sendKeys(memberEnrollmentContactData.address1);

        //**********************************
        //*********City Box*****************
        //**********************************
        //enter the City
        enrollmentPage.getCityTextBox().sendKeys(memberEnrollmentContactData.city);

        //**********************************
        //*********Zip/Postal Code Box******
        //**********************************
        //enter the Zip Code
        enrollmentPage.getPostalCodeTextBox().sendKeys(memberEnrollmentContactData.zipCode);

        //**********************************
        //*********Country DropDown***********
        //**********************************
        //enter the Country
        TestUtil.selectDropDownUsingVisibleText(enrollmentPage.getCountryDropDown(), memberEnrollmentContactData.country);

        enrollmentPage.getCountryDropDown().sendKeys(Keys.TAB);

        //**********************************
        //***State/Province DropDown********
        //**********************************
        //enter the State
        TestUtil.selectDropDownUsingVisibleText(enrollmentPage.getStateDropDown(), memberEnrollmentContactData.state);

        //**********************************
        //*****Primary Phone TextBox********
        //**********************************
        //enter the PrimaryPhone
        enrollmentPage.getPrimaryPhoneTextBox().sendKeys(memberEnrollmentContactData.phoneNumber);

        //**********************************
        //*****Secondary Phone TextBox******
        //**********************************
        //enter the PrimaryPhone
        enrollmentPage.getSecondaryPhoneTextBox().sendKeys(memberEnrollmentContactData.secondaryPhone);

        //**********************************
        //*****Home Airport TextBox*********
        //**********************************
        //enter the Home Airport
        TestUtil.selectDropDownUsingVisibleText(enrollmentPage.getPrimaryAirportDropDown(),memberEnrollmentContactData.homeAirport);

        //**********************************
        //****FS Terms and Condition********
        //**********************************
        //select FS Terms and Condition
        enrollmentPage.getFSTermsAndConditionText().click();

        //**********************************
        //*********Free Sign Up*************
        //**********************************
        //Click on Free Sign up Button
        enrollmentPage.getFSSignUpButton().click();

        WaitUtil.untilPageLoadComplete(driver);

        //wait for 3 sec
        WaitUtil.untilTimeCompleted(3000);

        //**********************************
        //*****Welcome To Free Sign Up******
        //**********************************
        final String FS_WELCOME = "Welcome to Free Spirit!";
        final String FS_WELCOME_SUB_HEADER = "You have successfully registered and can now start earning miles for future award travel";

        //verify popup header for FS account
        ValidationUtil.validateTestStep("Verify Welcome to Free Spirit popup appears on Enrollment Page",
                enrollmentPage.getPopUpHeaderText().getText().trim().equalsIgnoreCase(FS_WELCOME));

        ValidationUtil.validateTestStep("Verify You have successfully registered... verbiage displays on Enrollment Page",
                enrollmentPage.getSuccessfullyRegisteredText().getText().trim().contains(FS_WELCOME_SUB_HEADER));

        //close FS Registration popup
        enrollmentPage.getCloseImage().click();
    }


    // **********************************************************************************************
    // Method : getNew9FCMemberInformation
    // Description: Method is used to Create 9FC Member from Enrollment Page
    // Input Arguments: NA
    // Return:
    // Created By : Anthony
    // Created On : 04-Apr-2019
    // Reviewed By:
    // Reviewed On:
    // ***********************************************************************************************
    public Map<String, String> getNew9FCMemberInformation() {
        //click on 9DFC sign up link
        footer.getNineFareClubLink().click();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(driver);

        //**********Account Tab************

        //get account information of passenger
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("NineFCAccountTab");

        //create random first name
        String firstNameValue = TestUtil.getAlphaCharacterString(10);

        enrollmentPage.getUserFirstNameTextBox().click();

        //title
        TestUtil.selectDropDownUsingVisibleText(enrollmentPage.getUserTitleDropDown(),memberEnrollmentData.title);

        //firstname
        enrollmentPage.getUserFirstNameTextBox().sendKeys(firstNameValue);

        //lastname
        enrollmentPage.getUserLastNameTextBox().sendKeys(memberEnrollmentData.lastName);

        //date of birth
        enrollmentPage.getUserDateOfBirthTextBox().sendKeys(memberEnrollmentData.dob);

        //create random email
        String emailvalue = TestUtil.currentDateTimeString() + memberEnrollmentData.email;

        //email
        enrollmentPage.getUserEmailIdTextBox().sendKeys(emailvalue);

        //confirm email
        enrollmentPage.getUserConfirmEmailIdTextBox().sendKeys(emailvalue);

        //password
        enrollmentPage.getUserCreateAPasswordTextBox().sendKeys(memberEnrollmentData.createPassword);

        //confirm password
        enrollmentPage.getConfirmAPasswordTextBox().sendKeys(memberEnrollmentData.confirmPassowrd);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //move to billing tab
        enrollmentPage.getContinueToStep2Button().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //**********Contact Tab************

        //get contact tab information
        memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //address
        enrollmentPage.getAddressTextBox().sendKeys(memberEnrollmentData.address1);

        //city
        enrollmentPage.getCityTextBox().sendKeys(memberEnrollmentData.city);

        //postal code
        enrollmentPage.getPostalCodeTextBox().sendKeys(memberEnrollmentData.zipCode);

        //sate
        //enrollmentPage.getStateDropDown().sendKeys(memberEnrollmentData.state);
        TestUtil.selectDropDownUsingVisibleText(enrollmentPage.getStateDropDown(),memberEnrollmentData.state);

        //phone number
        enrollmentPage.getPrimaryPhoneTextBox().sendKeys(memberEnrollmentData.phoneNumber);

        //home airport
        //enrollmentPage.getPrimaryAirportDropDown().sendKeys(memberEnrollmentData.homeAirport);
        TestUtil.selectDropDownUsingVisibleText(enrollmentPage.getPrimaryAirportDropDown(),memberEnrollmentData.homeAirport);

        //move to Billing Tab
        enrollmentPage.getContinueToStep3Button().click();

        //**********Billing Tab************

        //get billing tab information
        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType("MasterCard");

        //click on card holder name
        enrollmentPage.getAccountHolderNameTextBox().click();

        //name on card
        enrollmentPage.getAccountHolderNameTextBox().sendKeys(cardDetailsData.cardHolderName);

        //card number
        enrollmentPage.getCardNumberTextBox().sendKeys(cardDetailsData.cardNumber);

        //expiration date
        enrollmentPage.getExpirationMonthYearTextBox().sendKeys(cardDetailsData.expirationDate);

        //security code
        enrollmentPage.getSecurityCodeTextBox().sendKeys(cardDetailsData.securityCode);

        //use same address
        enrollmentPage.getPleaseUseSameAddressText().click();

        //term and conditions
        enrollmentPage.getAgreeTermsAndConditionText().click();

        WaitUtil.untilPageLoadComplete(driver);

        //sign up for 9fc
        enrollmentPage.getDFCSignUpButton().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        WaitUtil.untilTimeCompleted(5000);

        //close 9DFC Registration popup
        enrollmentPage.getCloseImage().click();

        //get fs number
        String fsNumberValue = accountProfilePage.getFreeSpiritNumberText().getText();

        ValidationUtil.validateTestStep("User created a 9DFC account with FSNumber:"+fsNumberValue,true);

        //logout
        if(TestUtil.verifyElementDisplayed(driver, By.xpath(header.xpath_UserDropDown))){
            header.getUserDropDown().click();

            WaitUtil.untilTimeCompleted(3000);

            header.getSignOutUserLink().click();

            WaitUtil.untilPageLoadComplete(driver);

            ValidationUtil.validateTestStep("User verify application is logout",!TestUtil.verifyElementDisplayed(driver,By.xpath(header.xpath_UserDropDown)));

        }

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("NineFCAccountTab");

        //store data of 9fc member
        Map<String, String> memberDetails = new HashMap<String,String>();

        memberDetails.put("title",      memberEnrollmentData.title);
        memberDetails.put("firstName",  firstNameValue);
        memberDetails.put("lastName",   memberEnrollmentData.lastName);
        memberDetails.put("birthDate",  memberEnrollmentData.dob);
        memberDetails.put("email",      emailvalue);
        memberDetails.put("fsNumber",   fsNumberValue);
        memberDetails.put("password",   memberEnrollmentData.createPassword);

        //return 9fc member data
        return memberDetails;
    }
}
