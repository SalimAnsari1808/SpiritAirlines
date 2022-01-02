package com.spirit.testcasesHeartBeat;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: HeartBeat1
//Description: Create_new_FS_member
// Created By : Kartik Chauhan
// Created On : 06-Aug-2019
// Reviewed By: Salim Ansari
// Reviewed On: 06-Aug-2019
//**********************************************************************************************
public class HeartBeat1 extends BaseClass {
    @Parameters ({"platform"})
    @Test
    public void Create_new_FS_member(@Optional("NA")String platform) {
        /******************************************************************************
         ************************Navigation to Payments Page***************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID HeartBeat10 under HeartBeat Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //click on footer Member enrollment link
        pageObjectManager.getFooter().getFreeSpiritLink().click();

        //Enrollment Page methods
        pageMethodManager.getMemberEnrollmentPageMethods().createNewFSMember();
        pageObjectManager.getHeader().getSpiritLogoImage().click();

    }
}
