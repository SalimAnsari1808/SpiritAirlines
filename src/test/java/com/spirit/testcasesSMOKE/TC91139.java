package com.spirit.testcasesSMOKE;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.*;

//**********************************************************************************************
//Check6
//Test Case ID: TC91139
//Test Name:  $9FCBooking_CP_BP_Make sure the cancel button does not save your information on the edit primary card link
//Description:
//Created By : Alex R
//Created On : 10-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 16-Apr-2019
//**********************************************************************************************
public class TC91139 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath","NineDFC" , "Header" , "AccountProfileUI", "OutOfExecution"})
    public void $9FCBooking_CP_BP_Make_sure_the_cancel_button_does_not_save_your_information_on_the_edit_primary_card_link(@Optional("NA") String platform) {
        /******************************************************************************
         *********************Navigation to Account Billing Page***********************
         ******************************************************************************/
        //mention the browser
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91139 under SMOKE suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE       = "English";
        final String LOGIN_ACCOUNT  = "AccountNineDFCEmail";

        final String ACCOUNT_URL    = "account/profile";
        final String BILLING_URL    = "account/billing";
        final String BILLING_CARD   = "account/billing-card";

        //Step_ 1,2,3, 4
        //open browser
        openBrowser(platform);

        //Home Page Methods
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

        //wait till page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user navigated to account profile page
        ValidationUtil.validateTestStep("User navigated to user account profile", getDriver().getCurrentUrl(),ACCOUNT_URL);

        //Step -7
        //Click the billing information link
        pageObjectManager.getAccountProfilePage().getLeftMenuBillingInformationLink().click();

        //wait till page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user navigated to billing page
        ValidationUtil.validateTestStep("User navigated to Billing Information Page", getDriver().getCurrentUrl(),BILLING_URL);

        /******************************************************************************
         *********************Validation on Account Billing Page***********************
         ******************************************************************************/
        //Step- 8
        //get current Primary Card Link
        String currentDate = pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardExpirationDateText().getText();

        //click on edit of primary card
        pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardEditLink().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user navigated to billing card page
        ValidationUtil.validateTestStep("User clicked Edit Primary Card link and navigated to Billing Card Page", getDriver().getCurrentUrl(),BILLING_CARD);

        //Step- 9
        //clear expiry date
        pageObjectManager.getAccountProfilePage().getBillingInformationAddCardExpMonthYearTextBox().click();
        pageObjectManager.getAccountProfilePage().getBillingInformationAddCardExpMonthYearTextBox().clear();

        int currentDateMonth = Integer.parseInt(currentDate.substring(0, (currentDate.indexOf("/")  ))); //get only the month
        int currentDateYear = Integer.parseInt(currentDate.substring((currentDate.indexOf("/") + 3 ))); //get only the year

        //get random Month that is not current Month
        int newExpMon =  new Random().ints(1, 12).iterator().nextInt();
        if (newExpMon == currentDateMonth)  newExpMon =  new Random().ints(1, 12).iterator().nextInt();

        //get random year that is not current year
        int newExpYear =  new Random().ints(30, 99).iterator().nextInt();
        if (newExpYear == currentDateYear) newExpYear =  new Random().ints(30, 99).iterator().nextInt();

        String newExpDateStr;

        //if new month is less than 10 add a zero to the newExpDate
        if (newExpMon < 10 ) newExpDateStr = "0" + newExpMon + "/" + newExpYear;

            //if new month is greater than 10 dont add 0
        else  newExpDateStr =  + newExpMon + "/" + newExpYear ;

        //enter new expiration date
        pageObjectManager.getAccountProfilePage().getBillingInformationAddCardExpMonthYearTextBox().sendKeys(newExpDateStr);

        System.out.println("newExpDateStr: " + newExpDateStr);

        //Set 10: Under the "Billing Address" sub-header, un-check the "Please use {address} for my billing information" box.
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getAccountProfilePage().getBillingInformationUseSameAddressLabel().click();

        ValidationUtil.validateTestStep("Validating new address modal get expanded",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//div[@data-qa='other-billing-address']"))));

        //Step 11: Change all of the Billing Address fields to reflect a new Billing Address.
        pageObjectManager.getAccountProfilePage().getBillingInformationBillingAddressTextBox().clear();
        pageObjectManager.getAccountProfilePage().getBillingInformationBillingAddressTextBox().sendKeys("2354 N Commerce Pkwy");

        pageObjectManager.getAccountProfilePage().getBillingInformationBillingCityTextBox().clear();
        pageObjectManager.getAccountProfilePage().getBillingInformationBillingCityTextBox().sendKeys("Miami");

        pageObjectManager.getAccountProfilePage().getBillingInformationBillingZipPostalTextBox().clear();
        pageObjectManager.getAccountProfilePage().getBillingInformationBillingZipPostalTextBox().sendKeys("33024");

        //Step 12: Click the cancel button
        pageObjectManager.getAccountProfilePage().getBillingInformationCancelButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //All of your information should not save
        ValidationUtil.validateTestStep("Validating changes done on expiration date are not saved",
                !newExpDateStr.contains(pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardExpirationDateText().getText()));

        ValidationUtil.validateTestStep("Validating changes done on address are not saved",
                !pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardBillingAddressText().getText().contains("2354 N Commerce Pkwy " + " Miami " + "33024"));



//        //Step- 10
//        //Click the save button
//        pageObjectManager.getAccountProfilePage().getBillingInformationSaveButton().click();
//
//        //Declare variable being used in validations
//        ValidationUtil.validateTestStep("User verifies save link was clicked and redirected to Billing Information page", getDriver().getCurrentUrl(),BILLING_URL);
//
//        //Step- 11
//        WaitUtil.untilPageLoadComplete(getDriver(), (long) 1500);
//        //Validate your card was added with all of the correct information
//
//        //get new Primary Card expiration date
//        String lastTwoExp = pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardExpirationDateText().getText();
//        lastTwoExp = lastTwoExp.substring(lastTwoExp.length()- 2 );
//        String lastTwoNewYear = newExpDateStr.substring(newExpDateStr.length() - 2 );
//
//        //Verify the Card Number is correct
//        ValidationUtil.validateTestStep("The Expiration Date on new card added is correct", lastTwoExp, Integer.toString(newExpYear));

    }
}