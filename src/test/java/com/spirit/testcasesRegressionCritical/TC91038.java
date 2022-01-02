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
//Test Case ID: TC91038
//Description: Customer Info_CP_BP _NEG_FS member forgot password email
//Created By : Anthony Cardona
//Created On : 23-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 24-Jul-2019
//**********************************************************************************************

public class TC91038 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn21Days" , "Adult" , "FreeSpirit" , "NonStop" , "BookIt" , "PassengerInfoPasswordReset" , "Email" , "RetrievePasswordUI"})
    public void Customer_Info_CP_BP_NEG_FS_member_forgot_password_email(@Optional("NA") String platform) {

        /******************************************************************************
         ************************Navigate to Retrieve Password Page********************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91038 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "10";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";


        //open browser
        openBrowser(platform);

//pre-req= create a member, store information and log out
        //launch application
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //return map of member credentials
        Map<String, String> memberCredentials = createNewFSMember();


//STEP 1: Get to the customer information page with a FS member that is already created and data is already stored
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        WaitUtil.untilTimeCompleted(4000);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Page
//Step 2: Click on reset password
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().getResetPasswordInLineLogInLink().click();

        /******************************************************************************
         ************************Validation on Retrieve Password Page******************
         ******************************************************************************/
        //declare constant used in validation
        final String RETRIVE_PASSWORD_URL   = "retrieve-password";
        final String EMAIL_ERROR            = "A valid Free Spirit Number or associated Email address is required";
        final String RESET_PASSWORD_HEADER  = "Password Reset";
        final String RESET_PASSWORD_BODY    = "Your temporary password has been emailed to you. Please check your spam folder if you don't receive it within 30 minutes.";
        final String newPassword            = "Spirit1!";

//Step 3: validate user taken to the Retrieve Password page
        WaitUtil.untilPageLoadComplete(getDriver());

        //veify user navigated to retrieve password page
        ValidationUtil.validateTestStep("The user correctly taken to the retrieve password  page", getDriver().getCurrentUrl(), RETRIVE_PASSWORD_URL);


//Step 4: input incorrect email for FS member
        pageObjectManager.getRetrievePasswordPage().getEmailFSNumberTextBox().sendKeys((memberCredentials.get("fsNumber") + "234"));
        //Click on send to send password reset email
        pageObjectManager.getRetrievePasswordPage().getResetPasswordButton().click();

        //wait for 2 secc
        WaitUtil.untilTimeCompleted(2000);

        //verify incorrect fs number
        ValidationUtil.validateTestStep("The Incorrect FS Number error message appear on Retrive Password Page",
                pageObjectManager.getRetrievePasswordPage().getIncorrectEmailHeaderText().getText(), EMAIL_ERROR);

        //clear text in the textbox
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getRetrievePasswordPage().getEmailFSNumberTextBox());

//Step 5: input correct FS email address and click send to get email
        pageObjectManager.getRetrievePasswordPage().getEmailFSNumberTextBox().sendKeys(memberCredentials.get("email"));

        //Click on send to send password reset email
        pageObjectManager.getRetrievePasswordPage().getResetPasswordButton().click();

        //get current time
        String startTime = TestUtil.getStringDateFormat("0", "yyyy-MM-dd HH:mm:ss");

        //wait for 2 secc
        WaitUtil.untilTimeCompleted(2000);

//Step 6: Validate that the Pop-Up Verbiage is correct
        //verify resert password header
        ValidationUtil.validateTestStep("The Password Reset Header is correct on Retrieve Password Page",
                pageObjectManager.getRetrievePasswordPage().getResetPasswordHeaderText().getText(), RESET_PASSWORD_HEADER);

        //verify reset password body
        ValidationUtil.validateTestStep("The Password Reset Body Text is correct on Retrieve Password Page",
                pageObjectManager.getRetrievePasswordPage().getResetPasswordBodyText().getText(), RESET_PASSWORD_BODY);

        //verify go to login page
        ValidationUtil.validateTestStep("The Password Reset \"Go To Login Page\" is displayed on Retrieve Password Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getRetrievePasswordPage().getResetPasswordGoToLoginPageButton()));

//Step 7: Click Go to login page
        pageObjectManager.getRetrievePasswordPage().getReserPasswordCloseButton().click();

        //user must then click on sign in open log in drop down
        pageObjectManager.getHeader().getSignInLink().click();

//Step 8:
        //store temporary password
        memberCredentials.put("tempResetPW", EmailUtil.openOutlookInNewTab(getDriver(), memberCredentials.get("firstName"), startTime, "TemporaryPasswordFromEmail"));

//Step 9: Follow steps on the email
        //Use email and temporary password on spirit application
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(memberCredentials.get("email"));
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(memberCredentials.get("tempResetPW"));
        pageObjectManager.getHomePage().getLoginButton().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //enter new password
        pageObjectManager.getHomePage().getNewPasswordBox().sendKeys(newPassword);
        pageObjectManager.getHomePage().getConfirmNewPasswordBox().sendKeys(newPassword);
        pageObjectManager.getHomePage().getLoginButton().click();

        //wait for page is complete
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 10: Validate used is logged in
        ValidationUtil.validateTestStep("User Logged in to Spirit Application successfully", pageObjectManager.getHomePage().getLoginUserIconImage().size() > 0);

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