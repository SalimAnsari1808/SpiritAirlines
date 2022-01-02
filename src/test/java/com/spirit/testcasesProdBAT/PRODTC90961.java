package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//@Listeners(com.spirit.testNG.Listener.class)
//**********************************************************************************************
//Test Case ID: TC90961
//Description:  $9FC Enrollment from $9FC Enrollment Page Links
//Created By : Sunny Sok
//Created On : 25-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 11-Apr-2019
//**********************************************************************************************
public class PRODTC90961 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups="Production")

    public void NineFC_Enrollment_from_$9FC_Enrollment_Page_Links(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to Home Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90961 under PRODUCTION Suite on " + platform + " Browser", true);
        }
        //declare constant used in Validation
        final String FARECLUB_URL               = "/club-enrollment";
        final String FARECLUB_CHEAP_DISCOUNT    = "50% DISCOUNT";
        final String FARECLUB_CHEAP_VERBIAGE    = "based on first checked bag at $9 Fare Club rate bought online before check-in compared with airport pricing.";
        final String FARECLUB_TOP_VERBIAGE      = "APPLY TO YOU AND UP TO 8 ADDITIONAL PASSENGERS";
        final String FARECLUB_BOTTOM_DISCOUNT   = "on your itinerary. Passengers must be reserved on the same booking confirmation number as the $9 fare club member in order to receive membership benefits.";
        final String TERMS_AND_CONDITIONS_URL   = "/Shared/en-us/Documents/9FC_Terms_and_Conditions.pdf";
        final String FAQ_URL                    = "/hc/en-us/categories/200154236";
        final String PopUpLogInVerbiage         = "Log In To Your Account";

        //open browser
        openBrowser(platform);
        //STEP--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //STEP--2
        //Click on "$9 Fare Club" located on the bottom of the page
        pageObjectManager.getFooter().getNineFareClubLink().click();

        //Validate that you are redirected to http://www.spirit.com/fare-club-enrollment
        WaitUtil.untilPageURLVisible(getDriver(), FARECLUB_URL);

        //wait till page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //STEP--3
        //Click the ! tooltip next to verbiage Get disounted fares and cheaper bags. An image will populate
        pageObjectManager.getMemberEnrollmentPage().getDiscountedFaresAndCheaperBagsPopOverButton().click();

        //Validate image populates
        ValidationUtil.validateTestStep("User Validate image populates on Fare Club Emrollment Page",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getMemberEnrollmentPage().xpath_FareClubPopOverBodyPlaceHolder)));

        //STEP--4
        //Once image populates, Validate all verbiage is present"
        ValidationUtil.validateTestStep("User verify Get discounted fares and cheaper bags pop over verbiage is present on Fare Club Emrollment Page",
                pageObjectManager.getMemberEnrollmentPage().getFareClubPopOverTopText().getText().contains(FARECLUB_CHEAP_DISCOUNT) &&
                        pageObjectManager.getMemberEnrollmentPage().getFareClubPopOverBottomText().getText().contains(FARECLUB_CHEAP_VERBIAGE));

        pageObjectManager.getMemberEnrollmentPage().getDiscountedFaresAndCheaperBagsPopOverButton().click();

        //STEP--5
        //Click the ! tooltip next to verbiage Covers everyone on your booking. An image will populate
        pageObjectManager.getMemberEnrollmentPage().getCoversEveryoneOnYourBookingPopOverButton().click();

        WaitUtil.untilTimeCompleted(2000);
        //Validate image populates
        ValidationUtil.validateTestStep("Validate image populates  on Fare Club Emrollment Page",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getMemberEnrollmentPage().xpath_FareClubPopOverBodyPlaceHolder)));

        //STEP--6
        //Once image populates, Validate all verbiage is present"
        ValidationUtil.validateTestStep("User verify Covers everyone on your booking pop over verbiage is present on Fare Club Emrollment Page",
                pageObjectManager.getMemberEnrollmentPage().getFareClubPopOverTopText().getText().contains(FARECLUB_TOP_VERBIAGE) &&
                        pageObjectManager.getMemberEnrollmentPage().getFareClubPopOverBottomText().getText().contains(FARECLUB_BOTTOM_DISCOUNT));

        //hide the popover to expose sign-up now button
        pageObjectManager.getMemberEnrollmentPage().getCoversEveryoneOnYourBookingPopOverButton().click();

        //get offset value before click
        double preOffSetValue = GetPageYOffSetValue();

        //STEP--7
        //Click on the button "SIGN-UP NOW!"
        pageObjectManager.getMemberEnrollmentPage().getSignUpNowButton().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //get offset value after click
        double curOffSetValue = GetPageYOffSetValue();

        //Validate that when clicked, it auto scrolls down to the bottom of the page
        ValidationUtil.validateTestStep("User verify SIGN-UP NOW button auto scrolls to bottom of the page",
                curOffSetValue>preOffSetValue);

        //open new tab using java script
        JSExecuteUtil.openNewTabWithGivenURL(getDriver(),pageObjectManager.getMemberEnrollmentPage().getTermsAndConditionsLink().getAttribute("href"));

        //switch to new tab
        TestUtil.switchToWindow(getDriver(),1);

        //verift url navigated
        ValidationUtil.validateTestStep("User verify Term and Condition link is navigated correctly",
                getDriver().getCurrentUrl(),TERMS_AND_CONDITIONS_URL);

        //close new open tab
        getDriver().close();

        //switch to default tab
        TestUtil.switchToWindow(getDriver(),0);

        WaitUtil.untilTimeCompleted(2000);

        //STEP--9
        //Go back to the fare club enrollment page and click on the F.A.Q hyperlink
        //open new tab using java script
        JSExecuteUtil.openNewTabWithGivenURL(getDriver(),pageObjectManager.getMemberEnrollmentPage().getFAQLink().getAttribute("href"));

        //switch to new tab
        TestUtil.switchToWindow(getDriver(),1);

        WaitUtil.untilTimeCompleted(4000);

        System.out.println(getDriver().getCurrentUrl());

        //verify Freqyently Ask Questions link
        ValidationUtil.validateTestStep("User verify Frequently ask Questions link is navigated correctly",
                getDriver().getCurrentUrl(),FAQ_URL);

        //close new open tab
        getDriver().close();

        //switch to default tab
        TestUtil.switchToWindow(getDriver(),0);

        //STEP--10
        //Go back to the fare club enrollment page and click on Sign-up faster and easier hyperlink
        pageObjectManager.getMemberEnrollmentPage().getSignUpFasterAndEasierLink().click();

        //wait for pop up
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate that a pop up appears
        List<WebElement> LogIn_PopUp = pageObjectManager.getMemberEnrollmentPage().getlogInPopupText();
        ValidationUtil.validateTestStep("User verify popup is displayed  on Fare Club Emrollment Page", 1 == LogIn_PopUp.size());

        ValidationUtil.validateTestStep("User verify popup Header Verbiage  on Fare Club Emrollment Page", LogIn_PopUp.get(0).getText(),PopUpLogInVerbiage);

        //STEP--11
        //Click on the X on top right of the modal
        pageObjectManager.getMemberEnrollmentPage().getPopUpLogInToYourAccountCloseButton().click();

        //Validate modal closes
        List<WebElement> NewLogIn_PopUp = pageObjectManager.getMemberEnrollmentPage().getlogInPopupText();
        ValidationUtil.validateTestStep("User verify no popup is displayed  on Fare Club Emrollment Page", 0 == NewLogIn_PopUp.size());

    }
    private Double GetPageYOffSetValue(){
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        double value = Double.parseDouble(executor.executeScript("return window.pageYOffset;").toString());

        return value;
    }
}