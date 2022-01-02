package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.CardDetailsData;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
// Test Case ID: TC91138
// Description: $9FC Booking_Edit your primary card wireframe
// Created By : Gabriela
// Created On : 16-May-2019
// Reviewed By: Salim Ansari
// Reviewed On: 21-May-2019
//**********************************************************************************************

public class TC91138 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath", "NineDFC", "AccountProfileUI"})
    public void $9FC_Booking_Edit_your_primary_card_wireframe(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91138 under SMOKE Suite on " + platform + " Browser", true);
        }


//-- Step 1: Create or have a $9FC member
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String SAVE_BUTTON        = "SAVE CHANGES";
        final String CANCEL_BUTTON      = "Cancel";
        final String MEMBER_ENROLLMENT  = "ContactTab";
        final String CARD_TYPE          = "MasterCard";

//-- Ste 2: Go to the test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//-- Step 3: On the top right corner click the sign in link on the header
//-- Step 4: Enter the credentials for your $9FC member
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//-- Step 5: Click your $9FC members name
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getUserDropDown().click();

//-- Step 6: Click the "My Account" link on the drop down
        pageObjectManager.getHeader().getMyAccountUserLink().click();

//-- Step 7: Click on Billing Information link
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getLeftMenuBillingInformationLink().click();


//-- Step 8: Click the Edit link on the primary card
        pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardEditLink().click();

        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(CARD_TYPE);

//-- Step 9: Verify that all of your billing information for the card selected is shown correctly
        WaitUtil.untilPageLoadComplete(getDriver());
        System.out.println("expected name " + pageObjectManager.getAccountProfilePage().getBillingInformationNameOnCardText().getText());
        System.out.println("card holder actual name " + cardDetailsData.cardHolderName);
        ValidationUtil.validateTestStep("Validating the right Name is displayed",
                pageObjectManager.getAccountProfilePage().getBillingInformationNameOnCardText().getText(),cardDetailsData.cardHolderName);

        String cardNum = pageObjectManager.getAccountProfilePage().getBillingInformationCardNumberText().getText();
        String cardnum1 = cardNum.substring(cardNum.length()-4);

        ValidationUtil.validateTestStep("Validating the right Card Number is displayed",
                cardnum1,cardDetailsData.cardNumber.substring(cardDetailsData.cardNumber.length()-4));

        ValidationUtil.validateTestStep("Validating the right expiration date is displayed",
                pageObjectManager.getAccountProfilePage().getBillingInformationExpiryMonthYearTextBox().getAttribute("value"),cardDetailsData.expirationDate);

        WaitUtil.untilTimeCompleted(2000);

//-- Step 10: Look for the "Please use (Your address) for my billing information" checkbox
        pageObjectManager.getAccountProfilePage().getBillingInformationUseSameAddressLabel().click();

        MemberEnrollmentData memberEnrollmentContactData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab(MEMBER_ENROLLMENT);

        ValidationUtil.validateTestStep("Validating the right address",
                pageObjectManager.getAccountProfilePage().getBillingInformationBillingAddressTextBox().getAttribute("value"),memberEnrollmentContactData.address1);

        ValidationUtil.validateTestStep("Validating the right city",
                pageObjectManager.getAccountProfilePage().getBillingInformationBillingCityTextBox().getAttribute("value"),memberEnrollmentContactData.city);

        //Store stat information displayed for comparision
        pageObjectManager.getAccountProfilePage().getBillingInformationBillingZipPostalTextBox().click();
        Select stateDD = new Select(pageObjectManager.getAccountProfilePage().getBillingInformationStateDropDown());
        String state = stateDD.getFirstSelectedOption().getText();

        ValidationUtil.validateTestStep("Validating the right state", state,memberEnrollmentContactData.state);

        ValidationUtil.validateTestStep("Validating the right zip code",
                pageObjectManager.getAccountProfilePage().getBillingInformationBillingZipPostalTextBox().getAttribute("value"),memberEnrollmentContactData.zipCode);

        //Store country information displayed for  comparision
        Select countryDD = new Select(pageObjectManager.getAccountProfilePage().getBillingInformationCountryDropDown());
        String country = countryDD.getFirstSelectedOption().getText();
        ValidationUtil.validateTestStep("Validating the right country", country,memberEnrollmentContactData.country);

//-- Step 11: Look for the save button and cancel link
        ValidationUtil.validateTestStep("Validate the Save Changes button is present",
                pageObjectManager.getAccountProfilePage().getBillingInformationSaveButton().getText(),SAVE_BUTTON);

        ValidationUtil.validateTestStep("Validate the Cancel link is present",
                pageObjectManager.getAccountProfilePage().getBillingInformationCancelButton().getText(),CANCEL_BUTTON);
    }
}