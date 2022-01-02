package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC90758
// TestCase : CP_Header_Neg_Book, My Trips, Check-In, Flight Status
// Created By : Kartik Chauhan
// Created On : 09-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 09-July-2019
// **********************************************************************************************
public class TC90758 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups = {"Header"})
    public void CP_Header_Neg_Book_My_Trips_Check_In_Flight_Status_Link (@Optional("NA")String platform){

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90758 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         *************************Navigate to Home Page********************************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE               = "English";
//Step 1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//STEP--2
        ValidationUtil.validateTestStep("'Book' tab is not displaying at the top of the page",
                !pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("Book"));

        ValidationUtil.validateTestStep("'My Trips' tab is not displaying at the top of the page",
                !pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("My Trips"));

        ValidationUtil.validateTestStep("'Check-In' tab is not displaying at the top of the page",
                !pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("Check-In"));

        ValidationUtil.validateTestStep("'Flight Status' tab is not displaying at the top of the page",
                !pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("FLight Status"));
//STEP--3
        ValidationUtil.validateTestStep("Spirit logo is displaying at the left",
                pageObjectManager.getHeader().getSpiritLogoImage().isDisplayed());
//STEP--4 a
        ValidationUtil.validateTestStep("SIGN-IN link is displaying at the top of the page",
                pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("SIGN-IN"));

        ValidationUtil.validateTestStep("HELP link is not displaying at the top of the page",
                pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("HELP"));

        ValidationUtil.validateTestStep("CONTACT US link is not displaying at the top of the page",
                pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("CONTACT US"));
//STEP--4 b

        ValidationUtil.validateTestStep("Deals link is displaying at the top of the page",
                pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("Deals"));

        ValidationUtil.validateTestStep("$9 Fare Club link is displaying at the top of the page",
                pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("Fare Club"));

        ValidationUtil.validateTestStep("Spirit101 link is displaying at the top of the page",
                pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("Spirit 101"));

        ValidationUtil.validateTestStep("Destinations link is displaying at the top of the page",
                pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("Destinations"));

        ValidationUtil.validateTestStep("Español link is displaying at the top of the page",
                pageObjectManager.getHeader().getSpiritLogoPanel().getText().contains("Español"));

    }
}
