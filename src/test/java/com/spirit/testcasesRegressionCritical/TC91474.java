package com.spirit.testcasesRegressionCritical;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC91474
// TestCase : CP_Links on Contract_of_Carriage PDF
// Description: In this test case user need to verify the links appear in pdf file. xpath to links
//              present in pdf file is only created for Firefox browser. For browser apart from Firefox
//              xpaths cannot be created as link will be part of document that Selenium cannot read.
//              So this test case will only run in Firefox not in any other browser.
// Created By : Kartik Chauhan
// Created On : 09-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 09-July-2019
// **********************************************************************************************
public class TC91474 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups = {"Footer"})
    public void CP_Links_on_Contract_of_Carriage_PDF (@Optional("NA")String platform) {
        /******************************************************************************
         ****************************Navigate to Home Page ****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91474 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
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
//Step--3
        //validate Legal Link is displaying
        ValidationUtil.validateTestStep("Legal Link is displaying in Footer",
                pageObjectManager.getFooter().getLegalLink().isDisplayed());

        //Create Constant
        final String LEGAL_URL = "legal";
        final String LEGAL_PRIVACY_POLICY_URL = "https://cms10dss.spirit.com/Shared/en-us/Documents/Contract_of_Carriage.pdf";

        //Click on Legal link image
        pageObjectManager.getFooter().getLegalLink().click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("On Clicking Legal link ... correct url is displaying",
                getDriver().getCurrentUrl(), LEGAL_URL);
//STEP--4
        WaitUtil.untilTimeCompleted(2000);

        //Click on For further Info- link on PDF
        pageObjectManager.getFooter().getLegalContactOfCarriageLink().click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("On Clicking Contact of Carriage Link ... correct url of PDF is displaying",
                getDriver().getCurrentUrl(), LEGAL_PRIVACY_POLICY_URL);

        //Create Constant for all Links
        final String FURTHER_INFO_LINK      = "https://customersupport.spirit.com/hc/en-us/articles/217154817";
        final String OPTIONAL_SERVICE_LINK  = "https://www.spirit.com/optional-services";
        final String ADDITIONAL_INFO_LINK   = "https://customersupport.spirit.com/hc/en-us/articles/202098626-Can-I-purchase-an-extra-seat-for-myself-or-something-I-m-transporting-";
        final String MIN_SEAT_LINK          = "https://customersupport.spirit.com/hc/en-us/articles/202096526-Can-I-bring-my-child-s-car-seat-and-or-stroller-onboard-";
        final String CUS_PROPERTY_LINK      = "https://customersupport.spirit.com/hc/en-us/articles/202096436-Customer-Property-Form";

//STEP--2
        TestUtil.sendKeyUsingRobotClass("21715");

        pageObjectManager.getFooter().getLegalContactOfCarriagePDFForFurtherInformationLink().click();

        ValidationUtil.validateTestStep("On Clicking Further Information Link ... correct page is displaying",
                getDriver().getCurrentUrl(), FURTHER_INFO_LINK);

        ValidationUtil.validateTestStep("On Clicking Further Information Link ... correct page is not broken",
                TestUtil.verifyLinks( getDriver().getCurrentUrl()));

        getDriver().navigate().back();
//STEP--3
        TestUtil.sendKeyUsingRobotClass("3.3.4.");

        pageObjectManager.getFooter().getLegalContactOfCarriagePDFOptionalServicesLink().click();

        ValidationUtil.validateTestStep("On Clicking Optional Service Link ... correct page is displaying",
                getDriver().getCurrentUrl(), OPTIONAL_SERVICE_LINK);

        ValidationUtil.validateTestStep("On Clicking Optional Service Link ... correct page is not broken",
                TestUtil.verifyLinks( getDriver().getCurrentUrl()));

        getDriver().navigate().back();

//STEP--4
        TestUtil.sendKeyUsingRobotClass("4.11.2");

        pageObjectManager.getFooter().getLegalContactOfCarriagePDFAdditionalInformationLink().click();

        ValidationUtil.validateTestStep("On Clicking Additional Info Link ... correct page is displaying",
                getDriver().getCurrentUrl(), ADDITIONAL_INFO_LINK);

        ValidationUtil.validateTestStep("On Clicking Additional Info Link ... correct page is not broken",
                TestUtil.verifyLinks( getDriver().getCurrentUrl()));

        getDriver().navigate().back();

//STEP--5
        TestUtil.sendKeyUsingRobotClass("202096");

        pageObjectManager.getFooter().getLegalContactOfCarriagePDFMinimumSeatLink().click();

        ValidationUtil.validateTestStep("On Clicking Minimum Seat Link ... correct page is displaying",
                getDriver().getCurrentUrl(), MIN_SEAT_LINK);

        ValidationUtil.validateTestStep("On Clicking Minimum Seat Link ... correct page is not broken",
                TestUtil.verifyLinks( getDriver().getCurrentUrl()));

        getDriver().navigate().back();
//STEP--6
        TestUtil.sendKeyUsingRobotClass("21354");

        pageObjectManager.getFooter().getLegalContactOfCarriagePDFCustomerPropertyLink().click();

        ValidationUtil.validateTestStep("On Clicking Customer Property Link ... correct page is displaying",
                getDriver().getCurrentUrl(), CUS_PROPERTY_LINK);

        ValidationUtil.validateTestStep("On Clicking Customer Property Link ... correct page is not broken",
                TestUtil.verifyLinks( getDriver().getCurrentUrl()));

        getDriver().navigate().back();

    }
}

