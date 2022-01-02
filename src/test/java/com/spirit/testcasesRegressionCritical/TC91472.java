package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.awt.event.KeyEvent;

//**********************************************************************************************
// TestCase ID: TC91472
// TestCase : CP_Links on $9 Fare Club Terms and Conditions PDF
// Description: In this test case user need to verify the links appear in pdf file. xpath to links
//              present in pdf file is only created for Firefox browser. For browser apart from Firefox
//              xpaths cannot be created as link will be part of document that Selenium cannot read.
//              So this test case will only run in Firefox not in any other browser.
// Created By : Kartik Chauhan
// Created On : 10-July-2019
// Reviewed By: 11-July-2019
// Reviewed On: Salim Ansari
// **********************************************************************************************
public class TC91472 extends BaseClass {
    @Parameters ({"platform"})
    @Test (groups = {"BookPath" ,"MemberEnrollmentUI","ActiveBug","Footer"})
    public void CP_Links_on_Fare_Club_Terms_and_Conditions_PDF (@Optional("NA")String platform) {
        /******************************************************************************
         ****************************Navigate to Legal Page ***************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91472 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE = "English";
//Step--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step--2
        //scroll down to display taxes on screen
        JSExecuteUtil.scrollDown(getDriver(), "4000");

        //validate Fare Club Link is displaying
        ValidationUtil.validateTestStep("$9 Fare Club is displaying in Footer",
                pageObjectManager.getFooter().getNineFareClubLink().isDisplayed());
//STEP--3
        //make a click on Fare Club link
        pageObjectManager.getFooter().getNineFareClubLink().click();

        //Create Constant
        final String FARECLUB_URL                 = "club-enrollment";
        final String FARECLUB_TERMS_CONDITION_URL = "https://cms10dss.spirit.com/Shared/en-us/Documents/9FC_Terms_and_Conditions.pdf";
        final String SIGNUP_LINK                  = "https://content.spirit.com/Shared/en-us/Documents/Privacy_Policy.pdf";
        final String FREE_SPIRIT_PROFILE          = "https://www.spirit.com/FreeSpiritLogin.aspx";

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--4
        //verify correct url of Fare Club Link
        ValidationUtil.validateTestStep("On Clicking Fare Club link ... correct url is displaying",
                getDriver().getCurrentUrl(), FARECLUB_URL);

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--4
        //Click on For $9 Fare club Terms and Condition- link
        pageObjectManager.getMemberEnrollmentPage().getTermsAndConditionsLink().click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify correct url of Fare Club Terms and Condition
        ValidationUtil.validateTestStep("On Clicking Fare Club Terms and Condition Link ... correct url of PDF is displaying",
                getDriver().getCurrentUrl(), FARECLUB_TERMS_CONDITION_URL);
//STEP--5
        //Make a click on Privacy Policy Link
        pageObjectManager.getFooter().getLegalDFCTermsAndConditionPDFFreeSpiritProfileLink().click();

        //Put a wait for 5 sec
        WaitUtil.untilTimeCompleted(5000);

        //verify Privacy policy link
        ValidationUtil.validateTestStep("On Clicking Privacy Policy Link ... correct page is displaying",
                getDriver().getCurrentUrl(), FREE_SPIRIT_PROFILE);

        //Verify Privacy policy link is not broken
        ValidationUtil.validateTestStep("On Clicking Privacy Policy Link ... correct page is not broken",
                TestUtil.verifyLinks( getDriver().getCurrentUrl()));

        //naviaget back to the page
        getDriver().navigate().back();

        //Put a wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//********************************
//STEP--6
        //make a search on PDF via ROBOT class
        try{//create instance of robot class
            java.awt.Robot robot = new java.awt.Robot();

            //press Control and F button
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_F);

            //release key
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_F);

            //wait for 30 sec
            WaitUtil.untilTimeCompleted(500);

            //press PRIVACY keyword, as it will reflect sign up here link
            robot.keyPress(KeyEvent.VK_P);
            robot.keyPress(KeyEvent.VK_R);
            robot.keyPress(KeyEvent.VK_I);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyPress(KeyEvent.VK_Y);

            //release all
            robot.keyRelease(KeyEvent.VK_P);
            robot.keyRelease(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_I);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_Y);

        }catch(Exception e){

        }
        //Put a wait for 5 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on Sign Up here Link
        pageObjectManager.getFooter().getLegalDFCTermsAndConditionPDFSignUpHereLink().click();

        //Put a wait for 5 sec
        WaitUtil.untilTimeCompleted(5000);

        //verify Sign Up link
        ValidationUtil.validateTestStep("On Clicking Terms and Condition Sign Up Link ... correct page is displaying",
                getDriver().getCurrentUrl(), SIGNUP_LINK);

        //Verify Sign Up link is not broken
        ValidationUtil.validateTestStep("On Clicking Terms and Condition Sign Up Link ... correct page is not broken",
                TestUtil.verifyLinks( getDriver().getCurrentUrl()));
    }
}

