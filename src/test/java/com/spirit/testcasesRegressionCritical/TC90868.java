package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.util.List;
import java.util.ArrayList;

//**********************************************************************************************
//Test Case ID: TC90868
//Description : Misc_CP_MT_SinglePAX_Options Verify seats tag is correct
//Created By : Alex Rodriguez
//Created On : 24-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-JUL-2019
//**********************************************************************************************
public class TC90868 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"OptionalServicesUI"})
    public void Misc_CP_MT_SinglePAX_Options_Verify_seats_tag_is_correct(@Optional("NA") String platform) {

        //************************************************************************************
        //*************************Navigate to Reservation Summary Page***********************
        //************************************************************************************
        //mention the browser
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90868 under REGRESSION-CRITICAL suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE       = "English";

        //Options Services Seat Constant Values
        final String OP_SERVICE_URL = "/optional-services";
        final String TEXT           = "Every Guest has the opportunity to select their favorite spot on the plane. Maybe you want a sweet window seat or an aisle up front – go for it. If you don't select a seat, we'll assign random seats at check-in for free, but we can't guarantee that you will get to sit with your friends or family. And for those who want some space to stretch out, grab a Big Front Seat®: wider seat, extra legroom, and an even more comfortable flight";
        final String TEXT1          = "Spirit Assigned Seating at Check In";
        final String TEXT2          = "Customer-Requested Seat Assignments / Regular Seats";
        final String TEXT3          = "Big Front Seats (in advance)";
        final String TEXT4          = "Big Front Seats (onboard upgrades—depending on flight length)";
        final String TOOLTIP_TEXT1  = "We’ll assign random seat assignments at check-in.\n" + "Save more by choosing this free option.";
        final String TOOLTIP_TEXT2  = "These are seat assignments requested by a customer either online or at our airport. Save more by purchasing in advance online or by choosing a free random seat assignment at check-in.";
        final String TOOLTIP_TEXT3  = "These are our widest seats, which feature additional legroom and no middle seat. Save more by purchasing in advance online or by choosing a free random seat assignment at check-in.";
        final String TOOLTIP_TEXT4  = "These are our widest seats, which feature additional legroom and no middle seat. Save more by purchasing in advance online or by choosing a free random seat assignment at check-in.";
        final String PRICE_TEXT1    = "Free";
        final String PRICE_TEXT2    = "$1 to $50";
        final String PRICE_TEXT3    = "$12 to $150";
        final String PRICE_TEXT4    = "$25 to $175";

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
        pageObjectManager.getOptionalServicesPage().getSeatLabel().click();
//Step 4
        ValidationUtil.validateTestStep("User verifies 'Every Guest' verbiage",
                pageObjectManager.getOptionalServicesPage().getEveryGuestText().getText().trim(), TEXT);
//Step 5-8
        ValidationUtil.validateTestStep("User verifies 'Spirit Assigned Seating' verbiage",
                pageObjectManager.getOptionalServicesPage().getSpiritAssignedtext().getText().trim(), TEXT1);
        ValidationUtil.validateTestStep("User verifies 'Customer-Requested Seat' verbiage",
                pageObjectManager.getOptionalServicesPage().getCustomerRequestedText().getText().trim(), TEXT2);
        ValidationUtil.validateTestStep("User verifies 'Big Front Seats(in advanced)' verbiage",
                pageObjectManager.getOptionalServicesPage().getBigFrontSeatText().getText().trim(), TEXT3);
        ValidationUtil.validateTestStep("User verifies 'Big Front Seats (onboard upgrades)' verbiage",
                pageObjectManager.getOptionalServicesPage().getBigFrontSeatOnBoardUpgradesText().getText().trim(), TEXT4);

        String[] toolTipVerbiage = new String[]{TOOLTIP_TEXT1, TOOLTIP_TEXT2, TOOLTIP_TEXT3, TOOLTIP_TEXT4};
        List<WebElement> tooltipXpath = new ArrayList<>();

        tooltipXpath.add(pageObjectManager.getOptionalServicesPage().getWeWillAssignText());
        tooltipXpath.add(pageObjectManager.getOptionalServicesPage().getSeatAssignmentsText());
        tooltipXpath.add(pageObjectManager.getOptionalServicesPage().getWidestSeatsText());
        tooltipXpath.add(pageObjectManager.getOptionalServicesPage().getBFSOnBoardUpgradesNotificationText());

        System.out.println("\n\n\n");
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
        prices.add(pageObjectManager.getOptionalServicesPage().get1To50Text());
        prices.add(pageObjectManager.getOptionalServicesPage().get12To150Text());
        prices.add(pageObjectManager.getOptionalServicesPage().get25To175Text());

        for (int count = 0 ; count < prices.size() ; count ++ ) {
            ValidationUtil.validateTestStep("Validate the price verbiage: " + priceText[count], priceText[count], prices.get(count).getText().trim() );
        }
    }
}