package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC91067
// TestCase : Check-In_CP_BP_ Links CONFIRMATION CODE popup
// Created By : Kartik Chauhan
// Created On : 27-Jun-2019
// Reviewed By: Salim Ansari
// Reviewed On: 28-Jun-2019
// **********************************************************************************************
public class TC91067 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"HomeUI"})
    public void Check_In_CP_BP_Links_CONFIRMATION_CODE_popup(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91067 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";

        //STEP--1
        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Click on Check in tab
        pageObjectManager.getHomePage().getCheckInPathLink().click();

//STEP--3
        //Click on confirmation Code link
        pageObjectManager.getHomePage().getCheckInHowToFindConfirmationCodeLink().click();

        WaitUtil.untilTimeCompleted(2000);

        //Verify Confirmation Code pup-up is displaying
        ValidationUtil.validateTestStep("Your Confirmation Code pop-up is displaying on Check-in page",
                pageObjectManager.getHomePage().getYouConfirmationPopupHeaderText().isDisplayed());

//STEP--4
        //Check-in Page Constant Values
        final String IMG_URL            = "https://cms10dss.spirit.com/a/70";
        final String EXPEDIA_IMG_URL    = "https://cms10dss.spirit.com/a/67";
        final String PRICELINE_IMG_URL  = "https://cms10dss.spirit.com/a/69";
        final String CHEAPOAIR_IMG_URL  = "https://cms10dss.spirit.com/a/66";
        final String ORBITZ_IMG_URL     = "https://cms10dss.spirit.com/a/68";

//STEP--5
        //Click on Expedia Link
        pageObjectManager.getHomePage().getExpediaConfirmationPopUpLink().click();
        //verify Expedia Image
        ValidationUtil.validateTestStep("Expedia Image on Confirmation Code pop-up is displaying on Check-in page",
                pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src"),EXPEDIA_IMG_URL);
//STEP--6
        //Click on PriceLine Link
        pageObjectManager.getHomePage().getPricelineConfirmationPopUpLink().click();
        //verify PriceLine Image
        ValidationUtil.validateTestStep("PricelineImage on Confirmation Code pop-up is displaying on Check-in page",
                pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src"),PRICELINE_IMG_URL);

//STEP--7
        //Click on Cheapoair Link
        pageObjectManager.getHomePage().getCheapoairConfirmationPopUpLink().click();
        //verify Cheapoair Image
        ValidationUtil.validateTestStep("Cheapoair Image on Confirmation Code pop-up is displaying on Check-in page",
                pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src"),CHEAPOAIR_IMG_URL);

//STEP--8
        //Click on Orbitz Link
        pageObjectManager.getHomePage().getOrbitzConfirmationPopUpLink().click();
        //verify Orbitz Image
        ValidationUtil.validateTestStep("Orbitz Image on Confirmation Code pop-up is displaying on Check-in page",
                pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src"),ORBITZ_IMG_URL);
//STEP--9
        //Click on Spirit Link
        pageObjectManager.getHomePage().getSpiritConfirmationPopUpLink().click();
        //verify Spirit Image
        ValidationUtil.validateTestStep("Spirit Image on Confirmation Code pop-up is displaying on Check-in page",
                pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src"),IMG_URL);
//STEP--10
        //Close Pop-up
        pageObjectManager.getHomePage().getPopUpCrossButton().click();
    }
}