package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC90870
//Description : Misc_CP_MT_SinglePAX _ Options_Verify Memberships tag is correct
//Created By : Alex Rodriguez
//Created On : 2-AUG-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Aug-2019
//**********************************************************************************************
public class PRODTC90870 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void Misc_CP_MT_SinglePAX_Options_Verify_Memberships_tag_is_correct(@Optional("NA") String platform) {

    //************************************************************************************
    //*************************Navigate to Reservation Summary Page***********************
    //************************************************************************************
    //mention the browser
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90870 under PRODUCTION suite on " + platform + " Browser", true);
    }

        //Home Page Constant Values
        final String LANGUAGE       = "English";

        //Options Services Seat Constant Values
        final String OP_SERVICE_URL = "/optional-services";
        final String TEXT           = "There's no better way to save than the $9 Fare Club. You'll have access to our lowest available fares and discounted online bag prices.";
        final String TEXT1          = "FREE SPIRIT Membership ";
        final String TEXT2          = "$9 Fare Club Trial Membership (60 days trial)";
        final String TEXT3          = "$9 Fare Club Annual Membership";
        final String TEXT4          = "$9 Fare Club Annual Renewal";
        final String TOOLTIP_TEXT1  = "Our frequent flyer program helps you get to an award trip even faster and allows quick access to your reservations. More Info";
        final String TOOLTIP_TEXT2  = "Unlike an annual membership, trials are only available in the booking path. Benefits won’t begin until your next purchase. More Info";
        final String TOOLTIP_TEXT3  = "Our $9 Fare Club gives you exclusive access to extreme ultra-low fares and bag discounts. Save even more by becoming a member today. More Info";
        final String TOOLTIP_TEXT4  = "For your convenience, we’ll automatically renew your membership unless cancelled so you don’t miss out on savings. More Info";
        final String PRICE_TEXT1    = "Free";
        final String PRICE_TEXT2    = "$19.95";
        final String PRICE_TEXT3    = "$59.95";
        final String PRICE_TEXT4    = "$69.95";

//open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        WaitUtil.untilTimeCompleted(2000);

        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getOptionalServiceLink());
        WaitUtil.untilPageLoadComplete(getDriver());
//Step 2
        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL", getDriver().getCurrentUrl(), OP_SERVICE_URL);
//Step 3
        pageObjectManager.getOptionalServicesPage().getMembershipsText().click();
        WaitUtil.untilTimeCompleted(2000);
//Step 4
        ValidationUtil.validateTestStep("User verifies Membership header verbiage",
                pageObjectManager.getOptionalServicesPage().getThereNoBetterText().getText().trim(), TEXT);
//Step 5-8
        ValidationUtil.validateTestStep("User verifies Free Spirit Membership verbiage",
                pageObjectManager.getOptionalServicesPage().getFreeSpiritMembershipText().getText().trim(), TEXT1);
        ValidationUtil.validateTestStep("User verifies Fare Club Membership Trial verbiage",
                pageObjectManager.getOptionalServicesPage().getFairClubTrialMembershipText().getText().trim(), TEXT2);
        ValidationUtil.validateTestStep("User verifies Fare Club Annual Membership verbiage",
                pageObjectManager.getOptionalServicesPage().getFareClubAnnualMembershipText().getText().trim(), TEXT3);
        ValidationUtil.validateTestStep("User verifies Fare Club Annual Renewal verbiage",
                pageObjectManager.getOptionalServicesPage().getFairClubAnnualRenewalText().getText().trim(), TEXT4);


        String[] toolTipVerbiage = new String[]{TOOLTIP_TEXT1, TOOLTIP_TEXT2, TOOLTIP_TEXT3, TOOLTIP_TEXT4};
        List<WebElement> tooltipXpath = new ArrayList<>();

        tooltipXpath.add(pageObjectManager.getOptionalServicesPage().getOurFrequentFlyerText());
        tooltipXpath.add(pageObjectManager.getOptionalServicesPage().getUnlikeAnnualMembershipText());
        tooltipXpath.add(pageObjectManager.getOptionalServicesPage().getFareClubGiveExclusiveText());
        tooltipXpath.add(pageObjectManager.getOptionalServicesPage().getForyourConvenienceText());

        for (int count = 0; count < pageObjectManager.getOptionalServicesPage().getSpiritAssignedNotificationt().size() ; count ++ ) {
            pageObjectManager.getOptionalServicesPage().getSpiritAssignedNotificationt().get(count).click();
            WaitUtil.untilTimeCompleted(1000);
            ValidationUtil.validateTestStep("Validate the tooltip icon verbiage: " + toolTipVerbiage[count], toolTipVerbiage[count], tooltipXpath.get(count).getText().trim() );
            if(TestUtil.verifyElementDisplayed(pageObjectManager.getCommon().getPopOverCloseIcon()))
                pageObjectManager.getCommon().getPopOverCloseIcon().click();
        }
//Step 9
        String[] priceText = new String[]{PRICE_TEXT1, PRICE_TEXT2, PRICE_TEXT3, PRICE_TEXT4};
        List<WebElement> prices = new ArrayList<>();
        prices.add(pageObjectManager.getOptionalServicesPage().getFreeText());
        prices.add(pageObjectManager.getOptionalServicesPage().get19DollarText());
        prices.add(pageObjectManager.getOptionalServicesPage().get59DollarText());
        prices.add(pageObjectManager.getOptionalServicesPage().get69DollarText());

        for (int count = 0 ; count < prices.size() ; count ++ ) {
            ValidationUtil.validateTestStep("Validate the price verbiage: " + priceText[count], priceText[count], prices.get(count).getText().trim() );
        }
    }
}