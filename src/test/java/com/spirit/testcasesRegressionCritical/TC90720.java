package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

// **********************************************************************************************
// TestCase ID: TC90720
// TestCase : BAGS_CP_BP_OW_DOM_1PAX_VALIDATE VERBIAGE ON BANNER
// Created By : Kartik Chauhan
// Created On : 25-Jun-2019
// Reviewed By:
// Reviewed On:
// **********************************************************************************************
public class TC90720 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "BagsUI"})
    public void BAGS_CP_BP_OW_DOM_1PAX_VALIDATE_VERBIAGE_ON_BANNER(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Bags Page via FA user*****************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90720 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Method
        final String BAG_URL                    = "book/bags";
        final String BAG_HEADER                 = "Add Bags Now And Save";
        final String BAG_SUB_HEADER             = "Bringing anything besides your 18\" x 14\" x 8\" personal item? Save money by buying bags now instead of paying at the airport.";
        final String PERSONAL_ITEMS             = "1 Personal Item";
        final String PERSONAL_ITEMS_DIMENSIONS  =  "18 x 14 x 8 inches*";
        final String PERSONAL_ITEMS_INCLUDED    = "Included";
        final String CARRYON_BAG                = "1 Carry-On Bag**";
        final String CARRYON_BAG_DIMENSIONS     = "22 x 18 x 10 inches*";
        final String CARRYON_BAGS_PRICE         = "From $37.00";
        final String CHECKED_BAG                = "Up to 5 Checked Bags";
        final String CHECKED_BAG_DIMENSIONS     = "62 linear inches";
        final String CHECKED_BAGS_PRICE         = "From $32.00";

//STEP--1
        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        //User redirect To Bags Page
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL",
                getDriver().getCurrentUrl(), BAG_URL);
//STEP--2
        //verify the verbiage "Add Bag Now and Save...."
        ValidationUtil.validateTestStep("Verify 'Add Bag Now and Save' header verbiage is displaying at the top of the Bgas Page",
                pageObjectManager.getBagsPage().getHeaderText().getText(),BAG_HEADER);
        System.out.println(pageObjectManager.getBagsPage().getMarketingVerbiageText().getText());

        //verify the verbiage "Bringing anything...."
        ValidationUtil.validateTestStep("Verify 'Bringing anything..' Sub-header verbiage is displaying at the top of the Bgas Page",
                pageObjectManager.getBagsPage().getMarketingVerbiageText().getText(),BAG_SUB_HEADER);
//STEP--3
        //verify the verbiage "1 Personal item....."
        ValidationUtil.validateTestStep("Verify '1 Personal item..' verbiage is displaying at the bottom of the Bgas Page",
                pageObjectManager.getBagsPage().getPersonalItemText().getText(),PERSONAL_ITEMS);

        //verify the verbiage "1 Personal item's dimensions....."
        ValidationUtil.validateTestStep("Verify '1 Personal item..Dimensions' verbiage is displaying at the bottom of the Bags Page",
                pageObjectManager.getBagsPage().getPersonalItemDimensionsText().getText(),PERSONAL_ITEMS_DIMENSIONS);

        //verify the verbiage "1 Personal item-Included verbiage....."
        ValidationUtil.validateTestStep("Verify '1 Personal item-Included verbiage..' verbiage is displaying at the bottom of the Bgas Page",
                pageObjectManager.getBagsPage().getPersonalItemPriceDisplayText().getText(),PERSONAL_ITEMS_INCLUDED);

        //verify the color of  "1 Personal item- included yellow verbiage"..
        ValidationUtil.validateTestStep("Verify 'Yellow' color image is displaying",
                pageObjectManager.getBagsPage().getIncludedYellowCircleImage().isDisplayed());


//STEP--4
        //verify the verbiage "1 Carry on-bag....."
        ValidationUtil.validateTestStep("Verify '1 Carry on-bag..' verbiage is displaying at the top of the Bags Page",
                pageObjectManager.getBagsPage().getCarryOnBagText().getText(),CARRYON_BAG);

        //verify the verbiage "1 Carry on-bag's dimensions....."
        ValidationUtil.validateTestStep("Verify '1 Carry on-bag..Dimesions' verbiage is displaying at the top of the Bags Page",
                pageObjectManager.getBagsPage().getCarryOnBagDimensionsText().getText(),CARRYON_BAG_DIMENSIONS);

        //verify the verbiage "1 Carry on-bag's Price....."
        ValidationUtil.validateTestStep("Verify '1 Carry on-bag's Price..' is displaying at the top of the Bags Page",
                pageObjectManager.getBagsPage().getCarryOnBagPriceDisplayText().getText(),CARRYON_BAGS_PRICE);

        //verify the color of  "1 Personal item- included yellow verbiage"..
        ValidationUtil.validateTestStep("Verify 'Yellow-Carry on' color image is displaying",
                pageObjectManager.getBagsPage().getIncludedYellowCarryonCircleImage().isDisplayed());

// STEP--5
        //verify the verbiage "Upto 5 Checked-bag....."
        ValidationUtil.validateTestStep("Verify 'Upto 5 Checked-bag..' verbiage is displaying at the top of the Bags Page",
                pageObjectManager.getBagsPage().getCheckedBagText().getText(),CHECKED_BAG);

        //verify the verbiage "Upto 5 Checked-bag's dimensions....."
        ValidationUtil.validateTestStep("Verify 'Upto 5 Checked-bag..Dimesions' verbiage is displaying at the top of the Bags Page",
                pageObjectManager.getBagsPage().getCheckedBagDimensionsText().getText(),CHECKED_BAG_DIMENSIONS);

        //verify the verbiage "Upto 5 Checked-bag's Price....."
        ValidationUtil.validateTestStep("Verify 'Upto 5 Checked Price..' is displaying at the top of the Bags Page",
                pageObjectManager.getBagsPage().getCheckedBagPriceDisplayText().getText(),CHECKED_BAGS_PRICE);

        //verify the color of  "Upto 5 Checked-bag's included yellow verbiage"..
        ValidationUtil.validateTestStep("Verify 'Yellow-Checked Bag on' color image is displaying",
                pageObjectManager.getBagsPage().getIncludedYellowCheckedCircleImage().isDisplayed());
    }
}
