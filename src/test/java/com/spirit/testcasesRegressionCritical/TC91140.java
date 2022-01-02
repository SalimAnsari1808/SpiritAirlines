package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.CardDetailsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.Map;

//**********************************************************************************************
//Check6 (31289 | 35139 )
//Test Case ID: TC91140
//Description: $9FC Booking_CP_BP_ Make sure the save button does save your information on the edit primary card link
//Created By : Anthony Cardona
//Created On : 17-JUN-2019
//Reviewed By: Salim Ansari
//Reviewed On: 20-June-2019
//**********************************************************************************************
public class TC91140 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"NineDFC" , "Header" , "MasterCard" , "AccountProfileUI" })
    public void $9FC_Booking_CP_BP_Make_sure_the_save_button_does_save_your_information_on_the_edit_primary_card_link(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91140 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //open browser
        openBrowser(platform);

        //launch application
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//STEP 1: create a 9DFC member and store information
        //return map of member credentials
        Map<String, String> memberCredentials = pageMethodManager.getMemberEnrollmentPageMethods().getNew9FCMemberInformation();

        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType("MasterCard");

        //Home Page Constant Values
        final String NEW_CARD_EXPIRATION    = "10/32";
        final String CARD_HOLDER            = cardDetailsData.cardHolderName;
        final String CARD_NUMBER_LAST4CHAR  = cardDetailsData.cardNumber.substring(cardDetailsData.cardNumber.length() - 4);
        final String CARD_TYPE              = cardDetailsData.requestType;
        final String CARD_BILLING_ADDRESS   = "2800 Executive Way\nMiramar, FL 33025, US";

//Step: 2 3 and 4 click to log in
        //Home Page Methods
        //Use email and temporary password on spirit application
        pageMethodManager.getHomePageMethods().loginToApplication(memberCredentials.get("email"),memberCredentials.get("password"));

//Step 5 click on user name
        pageObjectManager.getHeader().getUserDropDown().click();

        //wait for 1.5 sec
        WaitUtil.untilTimeCompleted(1500);

//Step- 6: click to go to my account
        //Click on my account
        pageObjectManager.getHeader().getMyAccountUserLink().click();
        ValidationUtil.validateTestStep("The user clicks on My account link", true);

        //wait for account profile page
        WaitUtil.untilPageLoadComplete(getDriver());

//Step- 7: click on edit billing information
        pageObjectManager.getAccountProfilePage().getLeftMenuBillingInformationLink().click();
        ValidationUtil.validateTestStep("The user clicks on billing information link", true);
        WaitUtil.untilPageLoadComplete(getDriver());

//Step- 8: click on edit for existing primary card
        pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardEditLink().click();
        ValidationUtil.validateTestStep("The user clicks on Edit primary billing information link", true);
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 9: edit the billing information
        pageObjectManager.getAccountProfilePage().getBillingInformationExpiryMonthYearTextBox().clear();
        pageObjectManager.getAccountProfilePage().getBillingInformationExpiryMonthYearTextBox().sendKeys(NEW_CARD_EXPIRATION);
        ValidationUtil.validateTestStep("The user edits primary card expiration", true);
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

//Step 10
        pageObjectManager.getAccountProfilePage().getBillingInformationSaveButton().click();
        ValidationUtil.validateTestStep("The clicks on save primary card information", true);
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 11: Validate expiration month has been updated for primary card

        ValidationUtil.validateTestStep("The card Holder name is correct",
                pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardHolderText().getText(), CARD_HOLDER);


        ValidationUtil.validateTestStep("The card type is correct",
                pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardTypeText().getText(), CARD_TYPE);

        ValidationUtil.validateTestStep("The last 4 digits of the " + CARD_TYPE + " are correct",
                pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardNumberText().getText(), "XXXXXXXXXXXX" + CARD_NUMBER_LAST4CHAR );

        ValidationUtil.validateTestStep("The expiration date has been successfully updated",
                pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardExpirationDateText().getText(), NEW_CARD_EXPIRATION.substring(0,3) + "20" +  NEW_CARD_EXPIRATION.substring(3,5) );

        ValidationUtil.validateTestStep("The Billing Adress is correct",
                pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardBillingAddressText().getText().trim(), CARD_BILLING_ADDRESS);

//Step 12: obsolete
    }
}