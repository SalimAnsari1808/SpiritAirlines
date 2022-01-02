package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.Common;
import com.spirit.mobileObjects.SignInPage;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SignInPageMethods {

    private AppiumDriver driver;
    private MobileObjectManager mobileObjectManager;
    private ScenarioContext scenarioContext;
    private SignInPage signInPage;
    private Common common;

    public  SignInPageMethods(AppiumDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext){
        this.driver = driver;
        this.mobileObjectManager = mobileObjectManager;
        this.scenarioContext = scenarioContext;
        signInPage  = mobileObjectManager.getSignInPage();
        common      = mobileObjectManager.getCommon();
    }

    // **********************************************************************************************
    // Method : LoginToNativeApplication
    // Description: Method is used to login in with given user
    // Input Arguments: loginUserType->User Type for Login
    // Return: Null
    // Created By : Salim Ansari
    // Created On : 21-Oct-2019
    // Reviewed By:
    // Reviewed On:
    // ***********************************************************************************************
    public void LoginToNativeApplication(String loginUserType){
        //get the login mail type in global variable
        scenarioContext.setContext(Context.HOMEPAGE_LOGINTYPE,loginUserType);

        //get the login credentials from Json file
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(loginUserType);

        //wait for login page
        WaitUtil.untilElementIsClickable(driver,signInPage.getSpiritLogoImage());

        //enter user name
        signInPage.getEmailTextBox().sendKeys(loginCredentialsData.userName);

        //enter password
        signInPage.getPasswordTextBox().sendKeys(loginCredentialsData.password);

        //click on sign-in button
        signInPage.getSignInButton().click();

        //wait for user logged in
        WaitUtil.untilElementIsClickable(driver, signInPage.getLoggedUserImage());

        //validate user logged in to application
        ValidationUtil.validateTestStep("Logged User image appear on Mobile Trip Screen",signInPage.getLoggedUserImage().isDisplayed());
    }

    // **********************************************************************************************
    // Method : sendForgotPasswordSendEmail
    // Description: Method is used to send forgot password email to given user
    // Input Arguments: loginUserType->user type for which mail to be send
    // Return: Null
    // Created By : Salim Ansari
    // Created On : 21-Oct-2019
    // Reviewed By:
    // Reviewed On:
    // ***********************************************************************************************
    public void sendForgotPasswordSendEmailForUser(String loginUserType){

        final String FORGOT_PASSWORD_HEADER     = "Forgot Password";
        final String FORGOT_PASSWORD_SUBHEADER  = "Enter your email address and we will email you instructions on how to reset your password.";
        final String FORGOT_PASSWORD_EMAIL_SENT = "Password reset email successfully sent";

        //get the login credentials from Json file
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(loginUserType);

        //click on forgot password link
        signInPage.getForgotPasswordButton().click();

        //wait for forgot password popup appear
        WaitUtil.untilElementIsClickable(driver,signInPage.getForgotPasswordHeaderText());

        //validate forgot password header
        ValidationUtil.validateTestStep("Verify forgot Password header is correct",
                signInPage.getForgotPasswordHeaderText().getAttribute("text").equalsIgnoreCase(FORGOT_PASSWORD_HEADER));

        //verify forgot password sub-header
        ValidationUtil.validateTestStep("Verify forgot Password sub-header is correct",
                signInPage.getForgotPasswordSubHeaderText().getAttribute("text").equalsIgnoreCase(FORGOT_PASSWORD_SUBHEADER));

        //enter user email
        signInPage.getForgotPasswordEmailTextBox().sendKeys(loginCredentialsData.userName);

        //click on forgot password button
        signInPage.getForgotPasswordSubmitButton().click();

        //wait for confirmation message
        WaitUtil.untilElementIsClickable(driver,signInPage.getForgotPasswordEmailConfirmationText());

        //verift password reset email has been send
        ValidationUtil.validateTestStep("Verify Reset password mail has been send to the Email ID",
                signInPage.getForgotPasswordEmailConfirmationText().getAttribute("text").equalsIgnoreCase(FORGOT_PASSWORD_EMAIL_SENT));
    }

    // **********************************************************************************************
    // Method : sendForgotPasswordSendEmail
    // Description: Method is used to send forgot password email to given email
    // Input Arguments: loginUserEmail->mail to be send
    // Return: Null
    // Created By : Salim Ansari
    // Created On : 21-Oct-2019
    // Reviewed By:
    // Reviewed On:
    // ***********************************************************************************************
    public void sendForgotPasswordSendEmailForEmail(String loginUserEmail){

        final String FORGOT_PASSWORD_HEADER     = "Forgot Password";
        final String FORGOT_PASSWORD_SUBHEADER  = "Enter your email address and we will email you instructions on how to reset your password.";
        final String FORGOT_PASSWORD_EMAIL_SENT = "Password reset email successfully sent";

        //click on forgot password link
        signInPage.getForgotPasswordButton().click();

        //wait for forgot password popup appear
        WaitUtil.untilElementIsClickable(driver,signInPage.getForgotPasswordHeaderText());

        //validate forgot password header
        ValidationUtil.validateTestStep("Verify forgot Password header is correct",
                signInPage.getForgotPasswordHeaderText().getAttribute("text").equalsIgnoreCase(FORGOT_PASSWORD_HEADER));

        //verify forgot password sub-header
        ValidationUtil.validateTestStep("Verify forgot Password sub-header is correct",
                signInPage.getForgotPasswordSubHeaderText().getAttribute("text").equalsIgnoreCase(FORGOT_PASSWORD_SUBHEADER));

        //enter user email
        signInPage.getForgotPasswordEmailTextBox().sendKeys(loginUserEmail);

        //click on forgot password button
        signInPage.getForgotPasswordSubmitButton().click();

        //wait for confirmation message
        WaitUtil.untilElementIsClickable(driver,signInPage.getForgotPasswordEmailConfirmationText());

        //verift password reset email has been send
        ValidationUtil.validateTestStep("Verify Reset password mail has been send to the Email ID",
                signInPage.getForgotPasswordEmailConfirmationText().getAttribute("text").equalsIgnoreCase(FORGOT_PASSWORD_EMAIL_SENT));
    }

    // **********************************************************************************************
    // Method : createNewAccount
    // Description: Method is used to send forgot password email to given email
    // Input Arguments: loginUserEmail->mail to be send
    // Return: Null
    // Created By : Salim Ansari
    // Created On : 21-Oct-2019
    // Reviewed By:
    // Reviewed On:
    // ***********************************************************************************************
    public void createNewFSAccount(){
        /////////////////////////////////////
        //get passenger First and last name//
        /////////////////////////////////////
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("FSAccountTab");

        ///////////////////////////
        //click on create account//
        ///////////////////////////
        signInPage.getCreateAccountButton().click();

        WaitUtil.untilElementIsClickable(driver,signInPage.getCreateAccountFirstNameTextBox());

        ////////////////////
        //Enter First Name//
        ////////////////////
        signInPage.getCreateAccountFirstNameTextBox().sendKeys(memberEnrollmentData.firstName);

        ////////////////////
        //Enter Last Name//
        ////////////////////
        signInPage.getCreateAccountLastNameTextBox().sendKeys(memberEnrollmentData.lastName);

        //////////////////
        //Enter Email ID//
        //////////////////
        //Create variable to generate email id
        String emailvalue = TestUtil.currentDateTimeString() + memberEnrollmentData.email;

        //store email of the FS user
        scenarioContext.setContext(Context.CUSTOMER_EMAIL,emailvalue);

        //enter email id
        signInPage.getCreateAccountEmailTextBox().sendKeys(emailvalue);

        //////////////////
        //Enter Passowrd//
        //////////////////
        signInPage.getCreateAccountPasswordTextBox().sendKeys(memberEnrollmentData.createPassword);

        /////////////
        //Enter DOB//
        /////////////
        //click on DOB section
        signInPage.getCreateAccountDOBButton().click();

        //declare constant used nmethod
        final int DOB_YEAR  = Integer.parseInt(memberEnrollmentData.dob.split("/")[2]);
        final int DOB_MONTH = Integer.parseInt(memberEnrollmentData.dob.split("/")[0]);
        final int DOB_DAY   = Integer.parseInt(memberEnrollmentData.dob.split("/")[1]);

        //declare variables used in method
        List<MobileElement> yearList;
        boolean listFlag   = true;
        int     swipeCounter = 0;

        //click on calender year button
        common.getPassengerCalenderDOBPopUpYearHeaderButton().click();

        //wait for year list appear on screen
        for(int waitCounter=0;waitCounter<7;waitCounter++){
            yearList = common.getPassengerCalenderDOBPopUpYearText();

            if(yearList.size()>0){
                //year list loaded break from loop
                break;
            }else{
                //wait for 1 sec
                WaitUtil.untilTimeCompleted(1000);
            }
        }

        //get all year list
        yearList = common.getPassengerCalenderDOBPopUpYearText();

        while(listFlag){
            //loop through all mobile lement of yesr
            for(MobileElement requiredYear : yearList){
                //check selected element is matching with year
                if(Integer.parseInt(requiredYear.getAttribute("text"))-DOB_YEAR==0){
                    //click on required element
                    requiredYear.click();

                    //set flag to False
                    listFlag    = false;

                    //break loop
                    break;
                }else if(swipeCounter==20){
                    //break loop if counter reeach 20
                    listFlag    = false;

                    break;
                }
            }

            //check required year is not found in list
            if(listFlag){
                //get first and last element from year list
                MobileElement firstElement  = yearList.get(0);
                MobileElement lastElement   = yearList.get(yearList.size()-1);

                //check required year is below or above screen
                if(Integer.parseInt(lastElement.getAttribute("text"))-DOB_YEAR>0){
                    //scroll down
                    TestUtil.swipeScreenOnMobile(driver,firstElement,lastElement);
                }else{
                    //scroll up
                    TestUtil.swipeScreenOnMobile(driver,lastElement,firstElement);
                }
            }

            //increment swipe counter
            swipeCounter++;
        }

        //get all elements of days on current calender
        List<MobileElement> allDays = common.getPassengerCalenderDOBPopUpDaysText();

        //declare variable
        Calendar cal = null;
        MobileElement selectMonthLink;
        int monthCounter;

        //get the required month number from given date
        try{
            Date date = new SimpleDateFormat("MMMM").parse(allDays.get(0).getAttribute("content-desc").split(" ")[1]);
            cal = Calendar.getInstance();
            cal.setTime(date);
        }catch(Exception e){

        }

        //check required month is previous of next month as compared with current month
        if(DOB_MONTH - cal.get(Calendar.MONTH)-1>0){
            //select next month button
            selectMonthLink = common.getPassengerCalenderDOBPopUpNextMonthButton();

            //get month difference
            monthCounter = DOB_MONTH - cal.get(Calendar.MONTH);
        }else{
            //get previous month button
            selectMonthLink = common.getPassengerCalenderDOBPopUpPreviousMonthButton();

            //get month difference
            monthCounter = cal.get(Calendar.MONTH)-DOB_MONTH;
        }

        //loop through to select the required montyh either using previous/next month button
        for(int counterMonth=0;counterMonth<=monthCounter;counterMonth++){
            selectMonthLink.click();

            WaitUtil.untilTimeCompleted(500);
        }

        //get all days of required month
        allDays = common.getPassengerCalenderDOBPopUpDaysText();

        //loop through all days of month calender
        for(int counterDay=0;counterDay<allDays.size();counterDay++){
            //get selected day from required month
            int dayNumber = Integer.parseInt(allDays.get(counterDay).getAttribute("content-desc").split(" ")[0]);

            //check the curerent day is required day in calender
            if(DOB_DAY-dayNumber==0){
                allDays.get(counterDay).click();

                break;
            }
        }

        //wait for 1/2 sec
        WaitUtil.untilTimeCompleted(500);

        //click on OK button
        common.getPassengerCalenderDOBPopUpOKButton().click();

        //get teh contact information from json file
        MemberEnrollmentData memberEnrollmentContactData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");

        //set list flag to true for country
        listFlag = true;
        swipeCounter = 0;

        //Get  currently selected country name
        String selectedCountry = signInPage.getCreateAccountCountryButton().getAttribute("text");

        //check current selected country is required country
        if(!selectedCountry.equalsIgnoreCase(memberEnrollmentContactData.country)){
            //click on country button
            signInPage.getCreateAccountCountryButton().click();

            //get all countries list
            List<MobileElement> allCountries = signInPage.getCountrySearchCountriesText();

            while(listFlag){
                //loop through all countries list
                for(MobileElement requiredCountry: allCountries){
                    //check name of current country is matching withrequired country
                    if(requiredCountry.getAttribute("text").equalsIgnoreCase(memberEnrollmentContactData.country)){
                        //click on current country
                        requiredCountry.click();

                        //set flag to false
                        listFlag    = false;

                        //break loop
                        break;
                    }else if(swipeCounter==20){
                        listFlag    = false;

                        break;
                    }
                }

                if(listFlag){
                    //get first and last element
                    MobileElement firstElement  = allCountries.get(0);
                    MobileElement lastElement   = allCountries.get(allCountries.size()-1);

                    //move down
                    TestUtil.swipeScreenOnMobile(driver,firstElement,lastElement);
                }

                //increment swipe counter
                swipeCounter++;
            }
        }

        //click on create account button
        signInPage.getCreateAccountButton().click();

        //wait for user logged in
        WaitUtil.untilElementIsClickable(driver, signInPage.getLoggedUserImage());

        //wait till header notification disappear
        for(int waitCounter =0;waitCounter<40;waitCounter++){
            //wait for .5 sec
            WaitUtil.untilTimeCompleted(500);

            //check header navigation is disappear
            if(common.getHeaderNotificationText().size()==0){
                //break from loop
                break;
            }
        }

        //validate user logged in to application
        ValidationUtil.validateTestStep("New FS Account created and Logged User image appear on Mobile Trip Screen",signInPage.getLoggedUserImage().isDisplayed());
    }
}
