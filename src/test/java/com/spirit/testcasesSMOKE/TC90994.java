package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC90994
//Test Name: Customer Info_CP_BP _9DFC member forgot password email
//Description: Validate 9DFC user can change their password through the Retrieve password page
//and email reset is triggered
//Created By : Anthony Cardona
//Created On : 16-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 17-May-2019
//**********************************************************************************************
public class TC90994 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "NineDFC" , "NonStop" , "BookIt" , "PassengerInfoPasswordReset","Email","RetrievePasswordUI"})
    public void Customer_Info_CP_BP_9DFC_member_forgot_password_email(@Optional("NA") String platform) {
        /******************************************************************************
         ************************Navigate to Retrieve Password Page********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90994 under SMOKE Suite on " + platform + " Browser", true);
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
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //open browser
        openBrowser(platform);

        //launch application
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //return map of member credentials
        Map<String, String> memberCredentials = pageMethodManager.getMemberEnrollmentPageMethods().getNew9FCMemberInformation();


//STEP 1: Get to the customer information page with a 9DFC member that is already created and data is already stored
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        WaitUtil.untilTimeCompleted(4000);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Page
//Step 3: Click on reset password
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().getResetPasswordInLineLogInLink().click();

        /******************************************************************************
         ************************Validation on Retrieve Password Page******************
         ******************************************************************************/
        //declare constant used in validation
        final String RETRIEVE_PASSWORD_URL   = "retrieve-password";
        final String RESET_PASSWORD_HEADER  = "Password Reset";
        final String RESET_PASSWORD_BODY    = "Your temporary password has been emailed to you. Please check your spam folder if you don't receive it within 30 minutes.";

        String newPassword = "Spirit1!";

//Step 4: retrieve password page
        //validate user taken to the Retrieve Password page
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user navigated to retrieve password page
        ValidationUtil.validateTestStep("The user correctly taken to the retrieve password  page", getDriver().getCurrentUrl(),RETRIEVE_PASSWORD_URL);

//Step 5: input 9DFC email email address and click send to get email
        //enter incorrect FS number
        pageObjectManager.getRetrievePasswordPage().getEmailFSNumberTextBox().sendKeys(memberCredentials.get("email"));

        //Click on send to send password reset email
        pageObjectManager.getRetrievePasswordPage().getResetPasswordButton().click();

        //get current time
        String startTime = TestUtil.getStringDateFormat("0","yyyy-MM-dd HH:mm:ss");

        //wait for 2 seconds
        WaitUtil.untilTimeCompleted(2000);

//Step 6: Validate that the Pop-Up Verbiage is correct
        //verify reset password header
        ValidationUtil.validateTestStep("The Password Reset Header is correct on Retrieve Password Page" ,
                pageObjectManager.getRetrievePasswordPage().getResetPasswordHeaderText().getText() , RESET_PASSWORD_HEADER );

        //verify reset password body
        ValidationUtil.validateTestStep("The Password Reset Body Text is correct on Retrieve Password Page" ,
                pageObjectManager.getRetrievePasswordPage().getResetPasswordBodyText().getText() , RESET_PASSWORD_BODY );

        //verify go to login page
        ValidationUtil.validateTestStep("The Password Reset \"Go To Login Page\" is displayed on Retrieve Password Page" ,
                pageObjectManager.getRetrievePasswordPage().getResetPasswordGoToLoginPageButton().isDisplayed());

//Step 7: Click (X) to close the Pop-Up
        pageObjectManager.getRetrievePasswordPage().getReserPasswordCloseButton().click();

        //user must then click on sign in open log in drop down
        pageObjectManager.getHeader().getSignInLink().click();

//Step 8 and 9:
        //store temporary password
        memberCredentials.put("tempResetPW", EmailUtil.openOutlookInNewTab(getDriver(),memberCredentials.get("firstName"),startTime,"TemporaryPasswordFromEmail") );

        //wait for page is complete
        WaitUtil.untilPageLoadComplete(getDriver());
        //wait for 1 sec
        WaitUtil.untilTimeCompleted(2000);
        System.out.println("Reached Here");
//Step 10: Follow steps on the email
        //Use email and temporary password on spirit application
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(memberCredentials.get("email"));
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(memberCredentials.get("tempResetPW"));
        pageObjectManager.getHomePage().getLoginButton().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(2000);

        //enter new password
        pageObjectManager.getHomePage().getNewPasswordBox().sendKeys(newPassword);
        pageObjectManager.getHomePage().getConfirmNewPasswordBox().sendKeys(newPassword);
        pageObjectManager.getHomePage().getLoginButton().click();

        //wait for page is complete
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 11: Validate used is logged in
        ValidationUtil.validateTestStep("User Logged in to Spirit Application successfully", pageObjectManager.getHomePage().getLoginUserIconImage().size()>0);

    }

}