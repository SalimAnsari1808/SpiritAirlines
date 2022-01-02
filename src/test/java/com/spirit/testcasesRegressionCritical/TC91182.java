package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91182
//Description: CP_Footer_Pencil Banner and Spirit Master Card Offer
//Created By : Kartik Chauhan
//Created On : 23-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 31-July-2019
//**********************************************************************************************
public class TC91182 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"Footer" , "FSMasterCard" , "Spanish"})
    public void CP_Footer_Pencil_Banner_and_Spirit_Master_Card_Offer(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91182 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE               = "Spanish";
        final String JOURNEY_TYPE           = "Flight";
        final String LANGUAGE1              = "English";
        final String LOGIN_ACCOUNT          = "FSMCEmail";

        //open browser
        openBrowser(platform);

        //launch application
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //Home Page Method
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
//STEP--2
        //scroll down to display taxes on screen
        JSExecuteUtil.scrollDown(getDriver(),"5000");
//STEP-3
        //********************NA*************
//STEP--4
        //As with Alex discussion- It should be supressed in Spanish******
        ValidationUtil.validateTestStep("Master card Image is not displaying under Spanish Culture",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getFooter().getMasterCardImage()));

        //verify BONUS MILE verbiage is not displaying
        ValidationUtil.validateTestStep("BONUS MILE verbiage is not displaying under Spanish Culture",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getFooter().getMasterCardUpToBonusMilesVerbiage()));
//STEP--5
        //change language to English
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE1);
//STEP--6
        //log-in to Applic
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
//STEP--7
        //********************NA*************
//STEP--8
        //scroll down to display taxes on screen
        JSExecuteUtil.scrollDown(getDriver(),"5000");

        //As with Alex discussion- It should be supressed in Spanish******
        ValidationUtil.validateTestStep("Master card Image is not displaying under English Culture of FSMC user",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getFooter().getMasterCardImage()));

        //verify BONUS MILE verbiage is not displaying for FSMC User
        ValidationUtil.validateTestStep("BONUS MILE verbiage is not displaying under English Culture of FSMC user",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getFooter().getMasterCardUpToBonusMilesVerbiage()));
    }
}