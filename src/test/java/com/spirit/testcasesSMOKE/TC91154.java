package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.CardDetailsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC91154
//Test Name:  $9FCBooking_CP_BP_ Make sure the save button does save your information on the add a card page
//Description:
//Created By : Alex R
//Created On : 04-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 04-Apr-2019
//**********************************************************************************************
public class TC91154 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"NineDFC" ,"BookPath", "Header" , "AccountProfileUI","OutOfExecution"})
    public void $9FCBooking_CP_BP_Make_sure_the_save_button_does_save_your_information_on_the_add_a_card_page(@Optional("NA") String platform) {
        /******************************************************************************
         ************************Navigate to Billing Information Page******************
         ******************************************************************************/
        //mention the browser
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91154 under SMOKE suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE       = "English";
        final String LOGIN_ACCOUNT  = "AccountNineDFCEmail";

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
        ValidationUtil.validateTestStep("User selected account profile link in header dropdown on Home Page", true);

        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getHeader().getUserDropDown().click();

        //Step -7
        //Click the billing information link
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getLeftMenuBillingInformationLink().click();
        ValidationUtil.validateTestStep("User selected Billing Information link on Account Profile Page", true);

        if(TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getDeleteCardPopupDeleteCardButton())){
            pageObjectManager.getAccountProfilePage().getDeleteCardPopupDeleteCardButton().click();
        }

        //Step- 8
        //Click the Add another card link
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getBillingInformationAddAnotherCardButton().click();
        ValidationUtil.validateTestStep("User selected Add Another Card link on Billing Information Page", true);

        WaitUtil.untilPageLoadComplete(getDriver());
        //Step- 9
        //Enter valid information for all fields
        //Below Xpaths are not added into framework

        //get the visa card details
        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType("VisaCard");

        //fill card holder name
        pageObjectManager.getAccountProfilePage().getBillingInformationAddCardAccountHolderNameTextBox().click();
        pageObjectManager.getAccountProfilePage().getBillingInformationAddCardAccountHolderNameTextBox().sendKeys(cardDetailsData.cardHolderName);
        ValidationUtil.validateTestStep("User entered cardholder name", true);

        //fil card number
        pageObjectManager.getAccountProfilePage().getBillingInformationAddCardCardNumberTextBox().click();
        pageObjectManager.getAccountProfilePage().getBillingInformationAddCardCardNumberTextBox().sendKeys(cardDetailsData.cardNumber);
        ValidationUtil.validateTestStep("User entered credit card number", true);

        //fill expiration date
        pageObjectManager.getAccountProfilePage().getBillingInformationAddCardExpMonthYearTextBox().click();
        pageObjectManager.getAccountProfilePage().getBillingInformationAddCardExpMonthYearTextBox().sendKeys(cardDetailsData.expirationDate);
        ValidationUtil.validateTestStep("User entered card expiration date", true);

        //Step- 10
        //Click the save button
        pageObjectManager.getAccountProfilePage().getBillingInformationSaveButton().click();

        /******************************************************************************
         ************************Validation to Billing Information Page****************
         ******************************************************************************/
        //Declare variable being used in validations
        final String BILLING_URL = "account/billing";

        //Step- 11
        //wait for page load
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user on biiling information page
        ValidationUtil.validateTestStep("User verifies save link was clicked and redirected to Billing Information page",
                getDriver().getCurrentUrl(),BILLING_URL);

        //Validate your card was added with all of the correct information
        //Verify the Name on Card is correct
        ValidationUtil.validateTestStep("User verifies Card Holder name is correct",
                pageObjectManager.getAccountProfilePage().getBillingInformationAdditionalCardHolderText().getText(),cardDetailsData.cardHolderName);

        String expectedCardNumber = "";
        //Replace card number to X
        for(int carnNumberCounter=0;carnNumberCounter<cardDetailsData.cardNumber.length()-4;carnNumberCounter++){
            expectedCardNumber = expectedCardNumber + "X";
        }

        //get expected card number as shown in credit summary
        expectedCardNumber = expectedCardNumber.trim() + cardDetailsData.cardNumber.substring(cardDetailsData.cardNumber.length()-4);

        //Verify the Card Number is correct
       ValidationUtil.validateTestStep("Verify the card added having the same card number on Additional Card section on Billing Information Page",
               pageObjectManager.getAccountProfilePage().getBillingInformationAdditionalCardNumberText().get(0).getText(),expectedCardNumber);


       //Deleteing Card details to set the precondition for the same test during next run
       //click on delete card link
        pageObjectManager.getAccountProfilePage().getBillingInformationAdditionalCardDeleteLink().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on delete card button
        pageObjectManager.getAccountProfilePage().getDeleteCardPopupDeleteCardButton().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}