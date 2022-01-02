package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//@Listeners(com.spirit.testNG.Listener.class)
//**********************************************************************************************
//Test Case ID: TC91268
//Description: Validate for 9DFC user the Redeem Miles page on "My Account" validate links and current Mileage
//Created By : Anthony Cardona
//Created On : 27-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 09-Apr-2019
//**********************************************************************************************
public class TC91268 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"Header" ,"BookPath", "NineDFC" , "AccountProfileUI"})
    public void My_Account_CP_$9FC_Redeem_Miles_Link(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91268 under SMOKE Suite on " + platform + " Browser", true);
        }

        //******************************************************************************
        //****************************Navigate to Redeem Miles Page*********************
        //******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_ACCOUNT 	    = "NineDFCEmail";

        //open browser
        openBrowser(platform);

//Step_ 1,2,3, 4
        /***************************************************
         ********Log in member******
         ***************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//Step- 5 click on member name
        //navigate to my account drop down menu
        pageObjectManager.getHeader().getUserDropDown().click();

        //wait for 1.5 sec
        WaitUtil.untilTimeCompleted(1500);

//Step- 6
        //Click on my account
        pageObjectManager.getHeader().getMyAccountUserLink().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

//step- 7
        //click on Redeem Miles on Account profile page
        pageObjectManager.getAccountProfilePage().getLeftMenuRedeemMilesLink().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //******************************************************************************
        //****************************Validation to Redeem Miles Page*******************
        //******************************************************************************/
        //declare constant used in validation
        final String REDEEM_MILES_URL           = "account/redemption-points";
        final String REDEEM_MILES_HEADER       = "Redeeming Your Free Miles";
        final String CURRENT_MILEAGE_TEXT1      = "You currently have";
        final String CURRENT_MILEAGE_TEXT2      = "miles which is enough to book an Award Ticket";
        final String CURRENT_MILEAGE_TEXT3      = "View the current Award Ticket chart";
        final String SUB_HEADER_TEXT1           = "To book an award ticket, search for flights with the purchase with miles option selected on spirit.com.";
        final String SUB_HEADER_TEXT2           = "OR, from the Flight Availability page, you can toggle between pricing by miles or pricing in USD if you are signed into your Free Spirit account.";
        final String SUB_HEADER_TEXT3           = "To book by phone with one of our Free Spirit reservation specialists, call us at 1.801.401.2210.";
        final String SUB_HEADER_TEXT4           = "A $25 per passenger reservation fee will apply to reservations made via telephone.";
        final String MILEAGE_CHART_URL          = "https://doublizer.com/awards/fs_chart.pdf";

        //verify user navigated to redeem miles page
        ValidationUtil.validateTestStep("The user is redirected to the Redeem Miles page" , getDriver().getCurrentUrl().contains(REDEEM_MILES_URL));


//Step - 8
        //validate that the Page title is correct
        ValidationUtil.validateTestStep("The \"Redeem Your Free Miles\" Header is correct" ,
                pageObjectManager.getAccountProfilePage().getRedeemMilesHeaderText().getText().equalsIgnoreCase(REDEEM_MILES_HEADER));

//Step - 9

        String currentMiles = pageObjectManager.getAccountProfilePage().getRedeemMilesYourCurrentMilesText().getText();

        if(currentMiles.contains(CURRENT_MILEAGE_TEXT1) && currentMiles.contains(CURRENT_MILEAGE_TEXT2) && currentMiles.contains(CURRENT_MILEAGE_TEXT3)){
            ValidationUtil.validateTestStep("The Current mileage text is correct on Redeem Miles Page" , true);
        }else{
            ValidationUtil.validateTestStep("The Current mileage text is correct on Redeem Miles Page" , false);
        }

//Step - 10
        //click on the Redemption miles time table/chart
        pageObjectManager.getAccountProfilePage().getRedeemMilesYourCurrentMilesLink().click();

        //switch to second window
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("The user is redirected correctly", getDriver().getCurrentUrl().contains(MILEAGE_CHART_URL));

        //close new open tab
        getDriver().close();

        //switch to main window
        TestUtil.switchToWindow(getDriver(),0);

//Step - 11
        //verify sub header text1
        ValidationUtil.validateTestStep("Verify the Sub Header Text 1 appear on Redeem Miles Page",
            pageObjectManager.getAccountProfilePage().getRedeemMilesSubHeadersText().get(0).getText().contains(SUB_HEADER_TEXT1));

        //verify sub header text2
        ValidationUtil.validateTestStep("Verify the Sub Header Text 2 appear on Redeem Miles Page",
                pageObjectManager.getAccountProfilePage().getRedeemMilesSubHeadersText().get(1).getText().contains(SUB_HEADER_TEXT2));

        //verify sub header text3
        ValidationUtil.validateTestStep("Verify the Sub Header Text 3 appear on Redeem Miles Page",
                pageObjectManager.getAccountProfilePage().getRedeemMilesSubHeadersText().get(2).getText().contains(SUB_HEADER_TEXT3));

        //verify sub header text4
        ValidationUtil.validateTestStep("Verify the Sub Header Text 4 appear on Redeem Miles Page",
                pageObjectManager.getAccountProfilePage().getRedeemMilesSubHeadersText().get(3).getText().contains(SUB_HEADER_TEXT4));
    }

}
