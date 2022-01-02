package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
//Test Case ID: TC91271
//Description: My Account_CP_FS_Redeem Miles Link_No Miles
//Created By : Anthony Cardona
//Created On : 27-Mar-2019
//Reviewed By: Salim Ansari(Not Reviewed)
//Reviewed On: 30-Mar-2019
//**********************************************************************************************
public class TC91271 extends BaseClass{

    @Parameters({"platform"})
    @Test (groups = {"Header" , "FreeSpirit" , "BookPath","AccountProfileUI"})
    public void My_Account_CP_FS_Redeem_Miles_Link_No_Miles(@Optional("NA") String platform) {
        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC91271 under SMOKE suite on " + platform +" Browser", true);
        }

        //******************************************************************************
        //****************************Navigate to Redeem Miles Page*********************
        //******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_ACCOUNT 	    = "ZeroMiles";

        //open browser
        openBrowser(platform);

//Step_ 1,2,3, 4
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//Step- 5 click on member name
        //navigate to my account drop down menu
        pageObjectManager.getHeader().getUserDropDown().click();
        WaitUtil.untilTimeCompleted(1500);

//Step- 6
        //Click on my account
        pageObjectManager.getHeader().getMyAccountUserLink().click();

        //wait for account profile page
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on Redeem Miles on Account profile page
        pageObjectManager.getAccountProfilePage().getLeftMenuRedeemMilesLink().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //******************************************************************************
        //****************************Validation to Redeem Miles Page*******************
        //******************************************************************************/
        //declare constant used in validation
        final String REDEEM_MILES_URL           = "account/redemption-points";
        final String REDEEM_MILES_HEADER        = "Redeeming Your Free Miles";
        final String CURRENT_MILEAGE_TEXT1      = "Currently, you do not have enough miles to book an Award Ticket.";
        final String CURRENT_MILEAGE_TEXT2      = "View the current Award Ticket chart";
        final String SUB_HEADER_TEXT1           = "To book an award ticket, search for flights with the purchase with miles option selected on spirit.com.";
        final String SUB_HEADER_TEXT2           = "OR, from the Flight Availability page, you can toggle between pricing by miles or pricing in USD if you are signed into your Free Spirit account.";
        final String SUB_HEADER_TEXT3           = "To book by phone with one of our Free Spirit reservation specialists, call us at 1.801.401.2210.";
        final String SUB_HEADER_TEXT4           = "A $25 per passenger reservation fee will apply to reservations made via telephone.";
        final String MILEAGE_CHART_URL          = "https://doublizer.com/awards/fs_chart.pdf";


        //verify user navigated to redeem miles page
        ValidationUtil.validateTestStep("The user is redirected to the Redeem Miles page" , getDriver().getCurrentUrl(),REDEEM_MILES_URL);

 //step- 7
        //click on the Redemption miles time table/chart
        pageObjectManager.getAccountProfilePage().getRedeemMilesYourCurrentMilesLink().click();

        //switch to second window
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("The user is redirected correctly", getDriver().getCurrentUrl(),MILEAGE_CHART_URL);

        //close new open tab
        getDriver().close();

        //switch to main window
        TestUtil.switchToWindow(getDriver(),0);

//Step - 8
        //validate that the Page title is correct
        ValidationUtil.validateTestStep("The \"Redeem Your Free Miles\" Header is correct" ,
                pageObjectManager.getAccountProfilePage().getRedeemMilesHeaderText().getText(),REDEEM_MILES_HEADER);

//Step - 9
        //get current miles
        String currentMiles = pageObjectManager.getAccountProfilePage().getRedeemMilesYourCurrentMilesText().getText();

        if(currentMiles.contains(CURRENT_MILEAGE_TEXT1) && currentMiles.contains(CURRENT_MILEAGE_TEXT2)){
            ValidationUtil.validateTestStep("The Current mileage text is correct on Redeem Miles Page" , true);
        }else{
            ValidationUtil.validateTestStep("The Current mileage text is correct on Redeem Miles Page" , false);
        }
//Step - 10
        //verify sub header text1
        ValidationUtil.validateTestStep("Verify the Sub Header Text 1 appear on Redeem Miles Page",
                pageObjectManager.getAccountProfilePage().getRedeemMilesSubHeadersText().get(0).getText(),SUB_HEADER_TEXT1);

        //verify sub header text2
        ValidationUtil.validateTestStep("Verify the Sub Header Text 2 appear on Redeem Miles Page",
                pageObjectManager.getAccountProfilePage().getRedeemMilesSubHeadersText().get(1).getText(),SUB_HEADER_TEXT2);

        //verify sub header text3
        ValidationUtil.validateTestStep("Verify the Sub Header Text 3 appear on Redeem Miles Page",
                pageObjectManager.getAccountProfilePage().getRedeemMilesSubHeadersText().get(2).getText(),SUB_HEADER_TEXT3);

        //verify sub header text4
        ValidationUtil.validateTestStep("Verify the Sub Header Text 4 appear on Redeem Miles Page",
                pageObjectManager.getAccountProfilePage().getRedeemMilesSubHeadersText().get(3).getText(),SUB_HEADER_TEXT4);
    }
}
