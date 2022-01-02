package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91226
// TestCase   : My_Account_CP_FS_Request_Miles_Credit_Link_Neg_Already_Received_Milage
// Description: Validate error message for request mileage credit when User has already recieved mileage credit
//Created By : Anthony Cardona
//Created On : 12-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************

public class TC91226 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"FreeSpirit","Header","AccountProfileUI"})

    public void My_Account_CP_FS_Request_Miles_Credit_Link_Neg_Already_Received_Milage(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91226 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

//-- Step 1:  Must be signed in as a FS member. Passeneger has already received miles for the PNR
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_EMAIL        = "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        //navigate to my account drop down menu
        WaitUtil.untilPageLoadComplete(getDriver(), (long) 300);
        pageObjectManager.getHeader().getUserDropDown().click();

//Step 2: Reach the request miles page
        //Click on my account
        pageObjectManager.getHeader().getMyAccountUserLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getLeftMenuRequestMileageCreditLink().click();

//Step 3: Input the PNR on the confirmation code textbox
        String PNR = "QYQN6M";//PNR has already been checked in and boarded, user has already recieved Mileage credit
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getReqestMilesConfirmationTextBox().sendKeys(PNR);
        ValidationUtil.validateTestStep("The uer inputs PNR \"" + PNR + "\" into the text box", true);

//Step 4: Click on Go button
        pageObjectManager.getAccountProfilePage().getReqestMilesGoButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

//Step 5: Validate red iblock for text "Sorry, please wait until your flight has arrived before requesting mileage credit."
        String ALREADY_RECIEVED_MILEAGE_CREDIT = "You have already received mileage credit for this booking.";

        ValidationUtil.validateTestStep("The error message for flight that has not landed is correct",
                pageObjectManager.getCommon().getErrorMessageLabel().getText().trim(), ALREADY_RECIEVED_MILEAGE_CREDIT);
    }
}