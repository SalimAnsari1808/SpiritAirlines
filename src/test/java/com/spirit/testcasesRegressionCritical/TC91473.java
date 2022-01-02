package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC91473
// TestCase   : CP_Links on Spirit  Airlines Online Privacy Policy PDF
// Description: In this test case user need to verify the links appear in pdf file. xpath to links
//              present in pdf file is only created for Firefox browser. For browser apart from Firefox
//              xpaths cannot be created as link will be part of document that Selenium cannot read.
//              So this test case will only run in Firefox not in any other browser.
// Created By : Kartik Chauhan
// Created On : 05-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 08-July-2019
// **********************************************************************************************
public class TC91473 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups = {"Footer"})
    public void CP_Links_on_Spirit_Airlines_Online_Privacy_Policy_PDF (@Optional("NA")String platform){
        /******************************************************************************
         ****************************Navigate to Home Page ****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91473 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE      = "English";
//Step--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step--2
        //scroll down to display taxes on screen
        JSExecuteUtil.scrollDown(getDriver(),"4000");
//Step--3
        //validate Legal Link is displaying
        ValidationUtil.validateTestStep("Legal Link is displaying in Footer",
                pageObjectManager.getFooter().getLegalLink().isDisplayed());

        //Create Constant
        final String LEGAL_URL                   = "legal";
        final String LEGAL_PRIVACY_POLICY_URL    = "https://cms10dss.spirit.com/Shared/en-us/Documents/Privacy_Policy.pdf";
        final String NETWORK_ADVERTISING_URL     = "https://www.networkadvertising.org";

        //Click on Legal link image
        pageObjectManager.getFooter().getLegalLink().click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify user navigated to legal link
        ValidationUtil.validateTestStep("On Clicking Legal link ... correct url is displaying",
                getDriver().getCurrentUrl(),LEGAL_URL);
//STEP--4
        //Click on Privacy Policy link image
        pageObjectManager.getFooter().getLegalPrivacyPolicyLink().click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify user navigated to privacy policy link
        ValidationUtil.validateTestStep("PRIVACY POLICY PDF is displaying",
                getDriver().getCurrentUrl(),LEGAL_PRIVACY_POLICY_URL);

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //click on sig nup link on privacy policy pdf
        pageObjectManager.getFooter().getLegalPrivacyPolicyPDFEmailSignUpPageLink().click();

        //verify email sign up page
        ValidationUtil.validateTestStep("Verify user reached Email Deals Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getEmailDealEmailAddressTextBox()));

        //back to pdf
        getDriver().navigate().back();

        //click on first help link in privacy policy pdf
        pageObjectManager.getFooter().getLegalPrivacyPolicyPDFHelpLink().get(0).click();

        //verify spirit support page
        ValidationUtil.validateTestStep("Verify User reached Spirit Airlines Support after clicking first spirit.com/Help link on Privacy_Policy.pdf",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//select[@id='request_issue_type_select']"))));

        //back to pdf
        getDriver().navigate().back();

        //click on network advertising link
        pageObjectManager.getFooter().getLegalPrivacyPolicyPDFNetworkAdvertisingLink().click();

        //Verify User navigated to network Advertising
        ValidationUtil.validateTestStep("Verify User navigated network Advertising url after clicking on on Privacy_Policy.pdf",getDriver().getCurrentUrl(),NETWORK_ADVERTISING_URL);

        //back to pdf
        getDriver().navigate().back();

        //wait for 5 sec
        WaitUtil.untilTimeCompleted(5000);

        //scroll to second help link in privacy policy
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getFooter().getLegalPrivacyPolicyPDFHelpLink().get(0));

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on second help link in privacy policy pdf
        pageObjectManager.getFooter().getLegalPrivacyPolicyPDFHelpLink().get(1).click();

        //verify spirit support page
        ValidationUtil.validateTestStep("Verify User reached Spirit Airlines Support after clicking second spirit.com/Help link on Privacy_Policy.pdf",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//select[@id='request_issue_type_select']"))));

        //back to pdf
        getDriver().navigate().back();

        //wait for 5 sec
        WaitUtil.untilTimeCompleted(5000);

        //scroll to third help link in privacy policy
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getFooter().getLegalPrivacyPolicyPDFHelpLink().get(1));

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on second help link in privacy policy pdf
        pageObjectManager.getFooter().getLegalPrivacyPolicyPDFHelpLink().get(2).click();

        //verify spirit support page
        ValidationUtil.validateTestStep("Verify User reached Spirit Airlines Support after clicking third spirit.com/Help link on Privacy_Policy.pdf",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//select[@id='request_issue_type_select']"))));
//STEP--5
    }
}

