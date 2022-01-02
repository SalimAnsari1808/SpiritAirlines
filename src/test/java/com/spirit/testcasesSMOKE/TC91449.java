package com.spirit.testcasesSMOKE;
import com.spirit.utility.*;
import com.spirit.baseClass.*;
import org.testng.annotations.*;


// **********************************************************************************************
// TestCase : Search Widget_BP_CP_RT Invalid Promo Code(EDIT)
// Description: Validate that prices displayed in bag-o-tron are consistent when completing a booking
// Created By : Alex Rodriguez
// Created On : 8-April-2019
// Reviewed By: Salim Ansari
// Reviewed On: 18-April-2019
// **********************************************************************************************
public class TC91449 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "WithIn21Days" , "Adult" , "Guest" , "PromoCode" , "HomeUI"})
    public void Search_Widget_BP_CP_RT__Invalid_Promo_Code_EDIT(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to Home Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91449 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String JOURNEY_TYPE 	  	    = "Flight";
        final String TRIP_TYPE 			    = "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "BWI";
        final String DEP_DATE 		    	= "3";
        final String ARR_DATE 		    	= "10";
        final String ADULTS 		       	= "1";
        final String CHILDREN		       	= "0";
        final String INFANT_LAP 		    = "0";
        final String INFANT_SEAT 	    	= "0";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

        /******************************************************************************
         *****************************Validation to Home Page***************************
         ******************************************************************************/
        //declare constant used in validation
        final String POPOVER_VERBIAGE          = "You'll find our Promo Codes in our promotional emails.";
        final String ENROLLMENT_PAGE           = "account-enrollment";
        final String AVALIABILITY_PAGE         = "spirit.com/book/flights";
        final String INVALID_PROMO_CODE 	   = "100OFF";
        final String PROMO_CODE_VERBIAGE1      = "We’re sorry, your promotion code is not valid for one of the following reasons:";
        final String PROMO_CODE_VERBIAGE2      = "Please review the requirements for your code and try again.";
        final String PROMO_CODE_VERBIAGE3      = "It does not exist.";
        final String PROMO_CODE_VERBIAGE4      = "It has expired.";
        final String PROMO_CODE_VERBIAGE5      = "You have selected dates that are not allowed for this code.";
        final String PROMO_CODE_VERBIAGE6      = "You have selected a route that is not allowed for this code.";
        final int FIRST_INDEX                  = 0;
        final int SECOND_INDEX                 = 1;
        final int THIRD_INDEX                  = 2;
        final int FOURTH_INDEX                 = 3;

        //click on optional services pop over
        pageObjectManager.getHomePage().getOptionalServicesPopoverLink().click();

        //Verify Tooltip pops up with the verbiage
        ValidationUtil.validateTestStep("Verify Tooltip pops up with the verbiage \"You'll find our Promo Codes in our promotional emails.\" on Home Page", pageObjectManager.getHomePage().getPopOverContainerText().getText(),POPOVER_VERBIAGE);

        //Verify Tooltip pops hyperlink
        ValidationUtil.validateTestStep("Verify Tooltip pops verbiage is the \"Sign Up Now\" hyperlink appear on Home Page",pageObjectManager.getHomePage().getPromoSignUpNowLink().isDisplayed());

        //click on sign up now
        pageObjectManager.getHomePage().getPromoSignUpNowLink().click();

        //waiting for page to load
        WaitUtil.untilPageLoadComplete(getDriver());

        //validate user is taken to spirit.com/account-enrollment)
        ValidationUtil.validateTestStep("User proceeds to Enrollment page", getDriver().getCurrentUrl(),ENROLLMENT_PAGE);

        //click on spirit logo to return home
        pageObjectManager.getHomePage().getSpiritLogoImage().click();

        //reenter information into search widget
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);

        //click on optional services pop over
        pageObjectManager.getHomePage().getPromoCodeLink().click();

        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(INVALID_PROMO_CODE);

        //click on search button
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //verify Receive a PROMOTION CODE popup
        ValidationUtil.validateTestStep("Verify Recieve a PROMOTION CODE popup on Home Page", pageObjectManager.getHomePage().getPromoCodePopupHeaderText().isDisplayed());

        //Verify We’re sorry,...
        ValidationUtil.validateTestStep("Verify We’re sorry, your promotion code is not valid for one of the following reasons: on Home Page", pageObjectManager.getHomePage().getPromoCodePopupVerbiage1Text().get(FIRST_INDEX).getText(),PROMO_CODE_VERBIAGE1);

        //Verify Please review the requirements,...
        ValidationUtil.validateTestStep("Verify Please review the requirements for your code and try again on Home Page", pageObjectManager.getHomePage().getPromoCodePopupVerbiage1Text().get(SECOND_INDEX).getText(),PROMO_CODE_VERBIAGE2);

        //Verify  It does not exist...
        ValidationUtil.validateTestStep("Verify  It does not exist. on Home Page", pageObjectManager.getHomePage().getPromoCodePopupVerbiage2Text().get(FIRST_INDEX).getText(),PROMO_CODE_VERBIAGE3);

        //Verify It has expired....
        ValidationUtil.validateTestStep("Verify It has expired. on Home Page", pageObjectManager.getHomePage().getPromoCodePopupVerbiage2Text().get(SECOND_INDEX).getText(),PROMO_CODE_VERBIAGE4);

        //Verify You have selected dates that are not allowed for this code....
        ValidationUtil.validateTestStep("Verify You have selected dates that are not allowed for this code. on Home Page", pageObjectManager.getHomePage().getPromoCodePopupVerbiage2Text().get(THIRD_INDEX).getText(),PROMO_CODE_VERBIAGE5);

        //Verify You have selected a route that is not allowed for this code....
        ValidationUtil.validateTestStep("Verify You have selected a route that is not allowed for this code. on Home Page", pageObjectManager.getHomePage().getPromoCodePopupVerbiage2Text().get(FOURTH_INDEX).getText(),PROMO_CODE_VERBIAGE6);

        //Validate edit button is displayed
        ValidationUtil.validateTestStep("User Verify Edit coupon Code button is displayed on Home Page", pageObjectManager.getHomePage().getPromoCodePopupEditCouponCodeButton().isDisplayed());

        //Validate continue without promo button is displayed
        ValidationUtil.validateTestStep("User Verify Continue without coupon button is displayed on Home Page", pageObjectManager.getHomePage().getPromoCodePopupContinueWitoutCouponCodeButton().isDisplayed());

        //click on edit button
        pageObjectManager.getHomePage().getPromoCodePopupEditCouponCodeButton().click();

        //verify Receive a PROMOTION CODE popup not displayed
        ValidationUtil.validateTestStep("Verify Receive a PROMOTION CODE popup not displayed on Home Page", pageObjectManager.getHomePage().getPromoCodePopupVerbiage2Text().size()==0);

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
        ValidationUtil.validateTestStep("User proceeds to flights page", getDriver().getCurrentUrl(),AVALIABILITY_PAGE);

    }
}