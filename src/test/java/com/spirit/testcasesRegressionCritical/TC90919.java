package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC90919
//Description: Optional Services_CP_Content Block_WireFrame
//Created By: Kartik chauhan
//Created On: 1-August-2019
//Reviewed By: Salim Ansari
//Reviewed On: 1-August-2019
//**********************************************************************************************

public class TC90919 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"OptionalServicesUI"})
    public void Optional_Services_CP_Content_Block_WireFrame(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90919 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE = "English";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //Select spanish language
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//STEP--2
        //click on optional Service page
        pageObjectManager.getHomePage().getOptionalServiceLink().click();

        //verify user reach to optional Service page
        ValidationUtil.validateTestStep("Optional Service page is displaying",
                pageObjectManager.getOptionalServicesPage().getBagOTronNewTripLabel().isDisplayed());

//STEP-3-STEP--7 ***********are not Applicable as its functionality changed
    }

}