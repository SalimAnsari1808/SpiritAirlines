package com.spirit.testcasesBAT;
import com.spirit.utility.*;
import com.spirit.baseClass.*;
import org.openqa.selenium.By;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90757
//Description: Search Widget_BP_CP_OW_ Invalid Promo Code(EDIT)
//Created By : Sunny Sok
//Created On : 11-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Mar-2019
//**********************************************************************************************

public class TC90757 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","OneWay","DomesticDomestic","WithIn7Days","Adult","Guest","HomeUI","PromoCode","ActiveBug"})
    public void Search_Widget_BP_CP_OW__Invalid_Promo_Code(@Optional("NA") String platform) {
		//*******************************Navigate to Home Page**************************/
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID TC90757 under BAT Suite on " + platform + " Browser"   , true);
    	}
    	
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "BWI";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "NA";
        final String ADULTS 			= "1";
        final String CHILDS 			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT 		= "0";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        
        
		//***************************Validation to Home Page****************************/
        //declare constant used in validation
        final String POPOVER_VERBIAGE           = "You'll find our Promo Codes in our promotional emails.";
        final String ENROLLMENT_PAGE            = "account-enrollment";
        final String SPECIAL_CHARACTER          = "!@#$%";
        final String SPECIAL_CHARACTER_ERROR    = "Only letters and numbers are allowed";
        final String MORE_THAN_EIGHT_CHARACTER  = "123456789123";
        final String INVALID_PROMOCODE 	        = "10OFF";
        final String PROMOCODE_VERBIAGE1        = "We’re sorry, your promotion code is not valid for one of the following reasons:";
        final String PROMOCODE_VERBIAGE2        = "Please review the requirements for your code and try again.";
        final String PROMOCODE_VERBIAGE3        = "It does not exist.";
        final String PROMOCODE_VERBIAGE4        = "It has expired.";
        final String PROMOCODE_VERBIAGE5        = "You have selected dates that are not allowed for this code.";
        final String PROMOCODE_VERBIAGE6        = "You have selected a route that is not allowed for this code.";
        final int FIRST_INDEX                   = 0;
        final int SECOND_INDEX                  = 1;
        final int THIRD_INDEX                   = 2;
        final int FOURTH_INDEX                  = 3;
        
        //click on optional services pop over
        pageObjectManager.getHomePage().getOptionalServicesPopoverLink().click();
        
        //Verify Tooltip pops up with the verbiage
        ValidationUtil.validateTestStep("Verify Tooltip pops up with the verbiage \"You'll find our Promo Codes in our promotional emails.\" on Home Page",
                pageObjectManager.getHomePage().getPopOverContainerText().getText(),POPOVER_VERBIAGE);
        
        //Verify Tooltip pops hyperlink
        ValidationUtil.validateTestStep("Verify Tooltip pops verbiage is the \"Sign Up Now\" hyperlink appear on Home Page",
                TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getHomePage().xpath_PromoSignUpNowLink)));
        
        //click on sign up now
        pageObjectManager.getHomePage().getPromoSignUpNowLink().click();

        //waiting for page to load
        WaitUtil.untilPageLoadComplete(getDriver());

        //validate user is taken to spirit.com/account-enrollment)
        ValidationUtil.validateTestStep("User proceeds to Enrollment page",
                getDriver().getCurrentUrl(),ENROLLMENT_PAGE);

        //click on spirit logo to return home
        pageObjectManager.getHomePage().getSpiritLogoImage().click();

        //reenter information into search widget
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);

        //click on optional services pop over
        pageObjectManager.getHomePage().getPromoCodeLink().click();
        
        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(SPECIAL_CHARACTER);
        
        //click on search button
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getHomePage().getSearchFlightButton().get(0).click();

        //Promo code text input is alphanumeric only.
        ValidationUtil.validateTestStep("Verify Promo code text input accept only alphanumeric character on Home Page",
                pageObjectManager.getHomePage().getErrorMessageText().getText(),SPECIAL_CHARACTER_ERROR);
        
        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());
        
        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(MORE_THAN_EIGHT_CHARACTER);

        //Promo code can have a maximum 8 characters
        ValidationUtil.validateTestStep("Verify Promo code can have a maximum 8 characters on Home Page",
                pageObjectManager.getHomePage().getPromoCodeTextBox().getAttribute("maxlength").equals("8"));
        
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getPromoCodePopupCloseButton().click();
        WaitUtil.untilTimeCompleted(1200);
        
        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());
        
        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(INVALID_PROMOCODE);
        
        //click on search button
        pageMethodManager.getHomePageMethods().clickSearchButton();
        
        //verify Recieve a PROMOTION CODE popup
        ValidationUtil.validateTestStep("Verify Recieve a PROMOTION CODE popup on Home Page",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHomePage().xpath_PromoCodePopupHeaderText)));
        
        //Verify We’re sorry,...
        ValidationUtil.validateTestStep("Verify We’re sorry, your promotion code is not valid for one of the following reasons: on Home Page",
                pageObjectManager.getHomePage().getPromoCodePopupVerbiage1Text().get(FIRST_INDEX).getText(),PROMOCODE_VERBIAGE1);
        
        //Verify Please review the requirements,...
        ValidationUtil.validateTestStep("Verify Please review the requirements for your code and try again on Home Page",
                pageObjectManager.getHomePage().getPromoCodePopupVerbiage1Text().get(SECOND_INDEX).getText(),PROMOCODE_VERBIAGE2);
        
        //Verify  It does not exist...
        ValidationUtil.validateTestStep("Verify  It does not exist. on Home Page",
                pageObjectManager.getHomePage().getPromoCodePopupVerbiage2Text().get(FIRST_INDEX).getText(),PROMOCODE_VERBIAGE3);
        
        //Verify It has expired....
        ValidationUtil.validateTestStep("Verify It has expired. on Home Page",
                pageObjectManager.getHomePage().getPromoCodePopupVerbiage2Text().get(SECOND_INDEX).getText(),PROMOCODE_VERBIAGE4);
        
        //Verify You have selected dates that are not allowed for this code....
        ValidationUtil.validateTestStep("Verify You have selected dates that are not allowed for this code. on Home Page",
                pageObjectManager.getHomePage().getPromoCodePopupVerbiage2Text().get(THIRD_INDEX).getText(),PROMOCODE_VERBIAGE5);
        
        //Verify You have selected a route that is not allowed for this code....
        ValidationUtil.validateTestStep("Verify You have selected a route that is not allowed for this code. on Home Page",
                pageObjectManager.getHomePage().getPromoCodePopupVerbiage2Text().get(FOURTH_INDEX).getText(),PROMOCODE_VERBIAGE6);
        
        //Validate edit button is displayed
        ValidationUtil.validateTestStep("User Verify Edit coupon Code button is displayed on Home Page",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHomePage().xpath_PromoCodePopupEditCouponCodeButton)));

        //Validate continue without promo button is displayed
        ValidationUtil.validateTestStep("User Verify Continue without coupon button is displayed on Home Page",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHomePage().xpath_PromoCodePopupContinueWitoutCouponCodeButton)));

        //click on edit button
        pageObjectManager.getHomePage().getPromoCodePopupEditCouponCodeButton().click();

        //verify Recieve a PROMOTION CODE popup not displayed
        ValidationUtil.validateTestStep("Verify Receive a PROMOTION CODE popup not displayed on Home Page",
                pageObjectManager.getHomePage().getPromoCodePopupVerbiage2Text().size()==0);

        //click on search button
        pageMethodManager.getHomePageMethods().clickSearchButton();
        
        //click on close button
        pageObjectManager.getHomePage().getPromoCodePopupCloseButton().click();
        
        //click on search button
        pageMethodManager.getHomePageMethods().clickSearchButton();
        
        //click without promo code
        pageObjectManager.getHomePage().getPromoCodePopupContinueWitoutCouponCodeButton().click();
        
        //waiting for popup
        WaitUtil.untilPageLoadComplete(getDriver());
        
        //verify user proceeds to flights page
        ValidationUtil.validateTestStep("User proceeds to flights page", getDriver().getCurrentUrl(),("spirit.com/book/flights"));

    }
}