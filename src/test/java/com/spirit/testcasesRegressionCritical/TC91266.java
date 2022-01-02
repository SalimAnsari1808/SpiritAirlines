package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.PageObjectManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

//**********************************************************************************************
//Test Case ID: TC91266
//Description: My Account_CP_$9FC_Redeem Miles Link_No Miles
//Created By : Anthony Cardona
//Created On : 19-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 20-Jun-2019
//**********************************************************************************************
public class TC91266 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"NineDFC" , "AccountProfileUI"})
    public void My_Account_CP_$9FC_Redeem_Miles_Link_No_Miles(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91266 under RegressionCritical Suite on " + platform + " Browser", true);
        }

        //open browser
        openBrowser(platform);

        //Home Page Method
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//Step 1: create a 9DFC member with 0 miles
        //return map of member credentials
        Map<String, String> memberCredentials = pageMethodManager.getMemberEnrollmentPageMethods().getNew9FCMemberInformation();

        //User Constant Variables
        final String USER_EMAIL         = memberCredentials.get("email");
        final String USER_PASSWORD      = memberCredentials.get("password");

        //Redemption-points page Constant variables
        final String REDEMPTION_HEADER  = "Redeeming Your Free Miles";
        final String REDEEM_SUB_HEADER  = "Currently, you do not have enough miles to book an Award Ticket.\nView the current Award Ticket chart";
        final String[] PARAGRAPH        = {"To book an award ticket, search for flights with the purchase with miles option selected on spirit.com." ,
                "OR, from the Flight Availability page, you can toggle between pricing by miles or pricing in USD if you are signed into your Free Spirit account." ,
                "To book by phone with one of our Free Spirit reservation specialists, call us at 1.801.401.2210." ,
                "A $25 per passenger reservation fee will apply to reservations made via telephone."};

        final String AWARD_REDEMPTION_CHART_PDF = "/awards/fs_chart.pdf";

//Step 2 and 3: click to log in
        //Home Page Methods
        pageObjectManager.getHeader().getSignInLink().click();

//Step 4: Log in to member
        //Use email and temporary password on spirit application
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(USER_EMAIL);
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(USER_PASSWORD);
        pageObjectManager.getHomePage().getLoginButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 5: click on user name
        pageObjectManager.getHeader().getUserDropDown().click();

        //wait for 1.5 sec
        WaitUtil.untilTimeCompleted(1500);

//Step 6: click to go to my account
        //Click on my account
        pageObjectManager.getHeader().getMyAccountUserLink().click();
        ValidationUtil.validateTestStep("The user clicks on My account link", true);

        //wait for account profile page
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 7: click Redeem Points
        //Click on my account
        pageObjectManager.getAccountProfilePage().getLeftMenuRedeemMilesLink().click();
        ValidationUtil.validateTestStep("The user clicks on Redeem miles link", true);

        //wait for account profile page
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 8: Validate the Header of the redeem-points page
        ValidationUtil.validateTestStep("The header on the redemption page is correct",
                pageObjectManager.getAccountProfilePage().getRedeemMilesHeaderText().getText(), REDEMPTION_HEADER);

//Step : Validate the subheading of the redeem-points page
        ValidationUtil.validateTestStep("The sub-header on the redemption page is correct",
                pageObjectManager.getAccountProfilePage().getRedeemMilesYourCurrentMilesText().getText(), REDEEM_SUB_HEADER);

        int i  = 0 ;
        for(WebElement text : pageObjectManager.getAccountProfilePage().getRedeemMilesSubHeadersText())//validate each text on the page
        {
            ValidationUtil.validateTestStep("Text " + (i+1) + " on the redemption-points page is correct",
                    text.getText(), PARAGRAPH[i]);

            i++;
        }

//Step 10: Verify that the user is taken to the correct award redemption PDF page
        pageObjectManager.getAccountProfilePage().getRedeemMilesYourCurrentMilesLink().click();
        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(),1);
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct redemption pdf page",
                getDriver().getCurrentUrl().contains(AWARD_REDEMPTION_CHART_PDF));

        TestUtil.switchToWindow(getDriver(),0);
        WaitUtil.untilTimeCompleted(1200);

    }
}