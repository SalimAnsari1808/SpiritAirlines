package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC91109
// TestCase : MyTrip_CP_BP_ Links CONFIRMATION CODE popup
// Created By : Kartik Chauhan
// Created On : 02-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 02-Aug-2019
// **********************************************************************************************
public class TC91109 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"HomeUI","MyTrips"})
    public void MyTrip_CP_BP_Links_CONFIRMATION_CODE_popup(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Home Page*****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91109 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
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
//STEP--1
        //Click on Check in tab
        pageObjectManager.getHomePage().getMyTripPathLink().click();

        //verify Last Name TextBox is displaying
        ValidationUtil.validateTestStep("Last Name TextBox is displaying on Mytrip page",
                pageObjectManager.getHomePage().getMyTripLastNameTextBox().isDisplayed());

        //verify Confirmation Code  TextBox is displaying
        ValidationUtil.validateTestStep("Confirmation Code Textbox is displaying on Mytrip page",
                pageObjectManager.getHomePage().getMyTripConfirmationCodeTextBox().isDisplayed());
//STEP--2
        //Click on confirmation Code link
        pageObjectManager.getHomePage().getMyTripConfirmationCodeLink().click();
        WaitUtil.untilTimeCompleted(2000);

//STEP--3
        //Verify Confirmation Code pup-up is displaying
        ValidationUtil.validateTestStep("Your Confirmation Code pop-up is displaying on My Trip page",
                pageObjectManager.getHomePage().getYouConfirmationPopupHeaderText().isDisplayed());

        //Check-in Page Constant Values
        final String IMG_URL            = "https://cms10dss.spirit.com/a/70";
        final String EXPEDIA_IMG_URL    = "https://cms10dss.spirit.com/a/67";
        final String PRICELINE_IMG_URL  = "https://cms10dss.spirit.com/a/69";
        final String CHEAPOAIR_IMG_URL  = "https://cms10dss.spirit.com/a/66";
        final String ORBITZ_IMG_URL     = "https://cms10dss.spirit.com/a/68";

//STEP--4
        //Click on Expedia Link
        pageObjectManager.getHomePage().getExpediaConfirmationPopUpLink().click();
        //verify Expedia Image
        ValidationUtil.validateTestStep("Expedia Image on Confirmation Code pop-up is displaying on Mytrip Page",
                pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src"),EXPEDIA_IMG_URL);

        //verify Expedia link
        ValidationUtil.validateTestStep("Expedia Image Link on Confirmation Code pop-up is not broken on Mytrip Page",
                TestUtil.verifyLinks(pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src")));
//STEP--5
        //Click on PriceLine Link
        pageObjectManager.getHomePage().getPricelineConfirmationPopUpLink().click();

        //verify PriceLine Image
        ValidationUtil.validateTestStep("PricelineImage on Confirmation Code pop-up is displaying on Mytrip page",
                pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src"),PRICELINE_IMG_URL);

        //verify PriceLine Link
        ValidationUtil.validateTestStep("Priceline Image Link on Confirmation Code pop-up is not broken on Mytrip page",
                TestUtil.verifyLinks(pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src")));

//STEP--6
        //Click on Cheapoair Link
        pageObjectManager.getHomePage().getCheapoairConfirmationPopUpLink().click();

        //verify Cheapoair Image
        ValidationUtil.validateTestStep("Cheapoair Image Link on Confirmation Code pop-up is not broken on Mytrip page",
                pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src"),CHEAPOAIR_IMG_URL);

        //verify Cheapoair Link
        ValidationUtil.validateTestStep("Cheapoair Image on Confirmation Code pop-up is displaying on Mytrip page",
                TestUtil.verifyLinks(pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src")));

//STEP--7
        //Click on Orbitz Link
        pageObjectManager.getHomePage().getOrbitzConfirmationPopUpLink().click();

        //verify Orbitz Image
        ValidationUtil.validateTestStep("Orbitz Image on Confirmation Code pop-up is displaying on Mytrip page",
                pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src"),ORBITZ_IMG_URL);

        //verify Orbitz Link
        ValidationUtil.validateTestStep("Orbitz Image Link on Confirmation Code pop-up is not broken on Mytrip page",
                TestUtil.verifyLinks(pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src")));
//STEP--8
        //Click on Spirit Link
        pageObjectManager.getHomePage().getSpiritConfirmationPopUpLink().click();

        //verify Spirit Image
        ValidationUtil.validateTestStep("Spirit Image on Confirmation Code pop-up is displaying on Mytrip page",
                pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src"),IMG_URL);

        //verify Spirit Link
        ValidationUtil.validateTestStep("Spirit Image Link on Confirmation Code pop-up is not broken on Mytrip page",
                TestUtil.verifyLinks(pageObjectManager.getHomePage().getYouConfirmationPopupImage().getAttribute("src")));
//STEP--9
        //Close Pop-up
        pageObjectManager.getHomePage().getPopUpCrossButton().click();

        //click on Book tab
        pageObjectManager.getHomePage().getBookPathLink().click();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user is back to Home page
        ValidationUtil.validateTestStep("User is on Home page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFlightBookingLink()));
    }
}