package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.dataProviders.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import java.io.*;
import java.net.*;
import java.util.*;

// **********************************************************************************************
// TestCase ID: TC91143
// TestCase   : $9FCBooking_CP_BP_WireFrame_for_my_account_page_FS_member
// Created By : Alex
// Created On : 05-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 08-July-2019
// **********************************************************************************************
public class TC91143 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups={"FreeSpirit", "AccountProfileUI","Header", "ActiveBug"})

    public void $9FCBooking_CP_BP_WireFrame_for_my_account_page_FS_member (@Optional("NA") String platform){
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91143 under Regression-Critical Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";

        //Open Browser
        openBrowser(platform);

//Step 1, 2 : Go to Test Environment
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//Step 3-4: Log in to FS account
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//Step 5-6 : Click on My Account drop Down
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getUserDropDown().click();

//Step 7: Verify the Account Title
        WaitUtil.untilTimeCompleted(1500);
        pageObjectManager.getHeader().getMyAccountUserLink().click();
        ValidationUtil.validateTestStep("The user clicks on My Account link", true);
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 8: Verify the links on the left hand side

        final String MYACCOUNT    = "My Account";
        final String PERSONALINFO = "Personal Information";
        final String BILLINGINFO  = "Billing Information";
        final String EMAIL        = "Email Subscriptions";
        final String STATEMENTS   = "Statements";
        final String REDEEMMILES  = "Redeem Miles";
        final String BUYMILES     = "Buy or Gift Miles";
        final String REQUESTMILES = "Request Mileage Credit";

        List<WebElement> list = new ArrayList<>();

        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuMyAccountLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuPersonalInformationLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuBillingInformationLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuEmailSubscriptionsLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuStatementsLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuRedeemMilesLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuBuyGiftMilesLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuRequestMileageCreditLink());


        String verifyLinkText[] = new String[]{MYACCOUNT,PERSONALINFO,BILLINGINFO,EMAIL,STATEMENTS,REDEEMMILES,BUYMILES,REQUESTMILES};

        for(int i = 0 ;  i < list.size() ; i ++)
        {
            ValidationUtil.validateTestStep("User verifies links on the Left of Page: " + list.get(i).getText(), list.get(i).getText().toUpperCase(), verifyLinkText[i]);

        }

        WaitUtil.untilTimeCompleted(1000);
        testAllURLS();

//Step 9: Verify the "Your Free Spirit Account" header
        //Todo active BUG 25022, header is missing.

        ValidationUtil.validateTestStep("User verifies 'Welcome back' header is present",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getWelcomeHeaderText()));

//Step 10: Verify current miles for member are shown
        ValidationUtil.validateTestStep("User verifies miles are being displayed for member",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getYourCurrentMilesText()));

//Step 11: Verify correct account number is displayed
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("FSEmail");

        String fsNumber = pageObjectManager.getAccountProfilePage().getFreeSpiritNumberText().getText();
        ValidationUtil.validateTestStep("User verifies member Free Spirit account number is correct: " + fsNumber,
                fsNumber , loginCredentialsData.fsNumber );

//Step 12: Verify mileage earning tier for member
        String earningTier = pageObjectManager.getAccountProfilePage().getMileageEarningTierText().getText();
        //Todo Open bug for missing Earning Tier
        ValidationUtil.validateTestStep("User verifies mileage earning tier is displayed: " + earningTier,!earningTier.equals(""));

//Step 13: Verify that there is a section with a marketing tile
        ValidationUtil.validateTestStep("User verifies marketing tile is present on the page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSpiritLogoImage()));

//Step 14: Verify that there is a title that says "Your Membership"
        ValidationUtil.validateTestStep("User verifies that there is a title that says 'Your Membership'",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getYourMembershipHeaderText()));

//Step 15: Verify that there is a section that says "Member Name"
        ValidationUtil.validateTestStep("User verifies that there is a section that says 'Member Name' on the page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getMemberNameText()));

//Step 16: Verify that there is a section saying not a member yet
        ValidationUtil.validateTestStep("User verifies there is a section saying not a member yet",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getNotAMemberYetText()));

//Step 17: Verify the section that says "Your Reservations"
        ValidationUtil.validateTestStep("User verifies the section that says 'Your Reservations' ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getYourReservationsHeaderText()));

//Step 18: Verify the section that says "Locate a reservation"
        ValidationUtil.validateTestStep("User verifies the section that says 'Locate a reservation'",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getLocateYourReservationText()));

//Step 19: Verify the section with the "confirmation code"
        ValidationUtil.validateTestStep("User verifies section with the 'confirmation code'",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getConfirmationCodeSearchTextBox()));

//Step 20: Verify the section with "Current Reservations"

        ValidationUtil.validateTestStep("User verifies the section with 'Current Reservations' on the page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getCurrentReservationsHeaderText()));

//Step 21: Verify the section with "Past Reservations"
        ValidationUtil.validateTestStep("User verifies the section with 'Past Reservations' on the page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getPastReservationsHeader()));

    }

    private void testAllURLS(){
        String homePage = new ConfigFileReader().getApplicationURL();
        String url;
        HttpURLConnection urlConnection;
        int responseCode = 200;


        List<WebElement> list = new ArrayList<>();

        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuMyAccountLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuPersonalInformationLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuBillingInformationLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuEmailSubscriptionsLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuStatementsLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuRedeemMilesLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuBuyGiftMilesLink());
        list.add(pageObjectManager.getAccountProfilePage().getLeftMenuRequestMileageCreditLink());

        Iterator<WebElement> it = list.iterator();

        while(it.hasNext()){

            url = it.next().getAttribute("href");

            if(url == null || url.isEmpty()){
                System.out.println(url + "URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if(!url.startsWith(homePage)){
                System.out.println( url + "URL belongs to another domain, skipping it.");
                continue;
            }

            try {
                urlConnection = (HttpURLConnection)(new URL(url).openConnection());
                urlConnection.setRequestMethod("HEAD");
                urlConnection.connect();
                responseCode = urlConnection.getResponseCode();

                if(responseCode >= 400){
                    System.out.println(url + " is a broken link");
                }
                else{
                    System.out.println(url + " is a valid link");
                }

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}

