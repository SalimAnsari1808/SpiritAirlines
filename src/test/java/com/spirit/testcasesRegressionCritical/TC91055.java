package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91055
//Description: Search Widget_CP_BP_ Flight+Car+Hotel Invalid Promo
//Created By : Kartik Chauhan
//Created On : 13-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************
public class TC91055 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "DomesticDomestic" , "FlightHotelCar" , "HomeUI", "PromoCode", "ActiveBug"})
    public void Search_Widget_CP_BP_Flight_Car_Hotel_Invalid_Promo(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91055 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "vacation";
        final String TRIP_TYPE2			= "flight+hotel+car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "10";

        /******************************************************************************
         *****************************Validation to Home Page***************************
         ******************************************************************************/
        //declare constant used in validation
        final String SPECIAL_CHARACTER          = "!@#$%";
        final String SPECIAL_CHARACTER_ERROR    = "Only letters and numbers are allowed";
        final String MORE_THAN_EIGHT_CHARACTER  = "123456789123";
        final String INVALID_PROMOCODE 	        = "100OFF";
        final String PROMOCODE_VERBIAGE1        = "We’re sorry, your promotion code is not valid for one of the following reasons:";
        final int FIRST_INDEX                   = 0;
        final String FLIGHT_PAGE_URL            = "book/hotels";

        //open browser
        openBrowser(platform);
        //STEP--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//STEP--2
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        //Verify field box for room and driver's age is displayed
        ValidationUtil.validateTestStep("Verify field box for room and driver's age is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getRoomsDropDown())
                        && TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getDriversAgeDropDown()));

        //*******************************************************************
        //*****************Verifying Calenders Flight + Hotel + Car**********
        //*******************************************************************
//STEP--3
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);

        WaitUtil.untilTimeCompleted(2000);
//STEP--4
        //select from and To City pairs
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
//STEP--5
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
//STEP--6
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
//STEP--7
        //Verify 1 Room is displaying
        ValidationUtil.validateTestStep("Verify 1 Room is displaying as By Default",
                pageObjectManager.getHomePage().getRoomsDropDown().getText(),"1 Room");

        pageObjectManager.getHomePage().getDateBox().click();

        WaitUtil.untilTimeCompleted(2000);
//STEP--8
        //VERIFY Driver's age ************************************************

        //Click on Drivers Age drop down
        pageObjectManager.getHomePage().getDriversAgeDropDown().click();

        //Verify Drivers's option 21- 24
        ValidationUtil.validateTestStep("Verify Drivers's option 21- 24 is displaying",
                pageObjectManager.getHomePage().getDriversAgeDropDown().getText(),"21-24");

        //Verify Drivers's option 21- 24
        ValidationUtil.validateTestStep("Verify Drivers's option 25+ is displaying",
                pageObjectManager.getHomePage().getDriversAgeDropDown().getText(),"25+");

        pageObjectManager.getHomePage().getDriversAgeDropDown().sendKeys("21-24");

        WaitUtil.untilTimeCompleted(2000);
//STEP--9 & 10
        pageObjectManager.getHomePage().getPromoCodeLink().click();

        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getPromoCodeTextBox());

        WaitUtil.untilTimeCompleted(2000);
//STEP--11
        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(SPECIAL_CHARACTER);

        //click on search button
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Promo code text input is alphanumeric only.
        ValidationUtil.validateTestStep("Verify Promo code text input accept only alphanumeric character on Home Page", pageObjectManager.getHomePage().getErrorMessageText().getText().equals(SPECIAL_CHARACTER_ERROR));

        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());

        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(MORE_THAN_EIGHT_CHARACTER);

        /**********************
         ****This is a Bug*****
         **********************/
        //Promo code can have a maximum 8 characters
         ValidationUtil.validateTestStep("Verify Promo code can have a maximum 8 characters on Home Page", pageObjectManager.getHomePage().getErrorMessageText().getText().toString().length()==8);

        pageMethodManager.getHomePageMethods().clickSearchButton();

        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());

        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(INVALID_PROMOCODE);

        //click on search button
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //verify Recieve a PROMOTION CODE popup
        ValidationUtil.validateTestStep("Verify Recieve a PROMOTION CODE popup on Home Page",
                pageObjectManager.getHomePage().getPromoCodePopupHeaderText().isDisplayed());

        //Verify We’re sorry,...
        ValidationUtil.validateTestStep("Verify We’re sorry, your promotion code is not valid for one of the following reasons: on Home Page",
                pageObjectManager.getHomePage().getPromoCodePopupVerbiage1Text().get(FIRST_INDEX).getText(),PROMOCODE_VERBIAGE1);
//STEP-11
        //click on edit button
        pageObjectManager.getHomePage().getPromoCodePopupContinueWitoutCouponCodeButton().click();

        //waiting for popup
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user proceeds to flights page
        ValidationUtil.validateTestStep("User proceeds to flights page",
                getDriver().getCurrentUrl(),FLIGHT_PAGE_URL);

    }
}
