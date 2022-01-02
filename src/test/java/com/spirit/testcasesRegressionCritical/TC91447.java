package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91447
//Description: Search Widget_BP_CP_RT_ Valid Promo Code for dollars
//Created By:  Kartik Chauhan
//Created On:  13-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************

public class TC91447 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "PromoCode" , "Guest","HomeUI"})
    public void Search_Widget_BP_CP_RT_Valid_Promo_Code_for_dollars(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91447 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
//STEP--1
        //Home Page Constant Values
        final String LANGUAGE 			        = "English";
        final String JOURNEY_TYPE 	  	        = "Flight";
        final String TRIP_TYPE 			        = "RoundTrip";
        final String DEP_DATE 		    	    = "7";
        final String ARR_DATE 	    		    = "10";
        final String DEP_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "LAX";
        final String ADULT   		       	    = "1";
        final String CHILDREN 		            = "0";
        final String INFANT_LAP 	    	    = "0";
        final String INFANT_SEAT 		        = "0";
        final String TOOLTIP_TEXT               = "You'll find our Promo Codes in our promotional emails.\n" + "Sign Up Now";
        final String SPECIAL_CHARACTER          = "!@#$%" + Keys.TAB;
        final String SPECIAL_CHARACTER_ERROR    = "Only letters and numbers are allowed";
        final String MORE_THAN_EIGHT_CHARACTER  = "123456789123";
        final String VALID_PROMO_CODE           = "25PCT";

        //Flight Availability Page Constant Values
        final String FA_URL                    = "book/flights";

        //open browser
        openBrowser(platform);

        /****************************************** Home Page Methods ************************************/
//STEP--2
        //launch application
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

//STEP--3
        //Select Trip type
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
//STEP--4
        //Select Departure and arrival airport
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

//STEP--5:
        //Select 1 Adult
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILDREN, INFANT_SEAT, INFANT_LAP);

//STEP--6
        //Select Departure and arrival Date
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

//STEP--7
        //click on Pop over link opf promo code
        pageObjectManager.getHomePage().getOptionalServicesPopoverLink().click();

        //Validating the right info is displayed
        ValidationUtil.validateTestStep("Validating the right info is displayed",
                pageObjectManager.getHomePage().getPopOverContainerText().getText(), TOOLTIP_TEXT);

//STEP--8
        //cl;ick on Promo code link
        pageObjectManager.getHomePage().getPromoCodeLink().click();

        //Wait for 2 sec
        WaitUtil.untilTimeCompleted(1200);

        //Validating Promo Code field box should populate in that
        ValidationUtil.validateTestStep("Validating Promo Code field box should populate in that space, replacing the tool tip",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getPromoCodeTextBox()));

        //Promo code text input is alphanumeric only.
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(SPECIAL_CHARACTER);

        //click on search button
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Promo code text input is alphanumeric only.
        ValidationUtil.validateTestStep("Verify Promo code text input accept only alphanumeric character on Home Page",
                pageObjectManager.getHomePage().getErrorMessageText().getText(),SPECIAL_CHARACTER_ERROR);

        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());

        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(MORE_THAN_EIGHT_CHARACTER);

        //TODO: Bug 21419: GAP: CP: BP: Search Widget SW: Promo Codes are only 8 Characters long should the Promo Text box allow infinite Characters?
        //Promo code can have a maximum 8 characters
//         ValidationUtil.validateTestStep("Verify Promo code can have a maximum 8 characters on Home Page",
//         pageObjectManager.getHomePage().getErrorMessageText().getText().toString().length()==8);
//
//        pageMethodManager.getHomePageMethods().clickSearchButton();

        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());

//Step--9
        //wait for 3 sec
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(VALID_PROMO_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************** Flight Availability Page Methods ************************************/
        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //Verify Flight Availability Page load
        ValidationUtil.validateTestStep("Verify Flight Availability Page load",
                getDriver().getCurrentUrl().contains(FA_URL));
    }
}