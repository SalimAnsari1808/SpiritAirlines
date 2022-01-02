package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC91478
// TestCase : ADT-CHD pop-up modal ages from 0 to17
// Created By : Kartik Chauhan
// Created On : 27-Jun-2019
// Reviewed By: Salim Ansari
// Reviewed On: 28-Jun-2019
// **********************************************************************************************
public class TC91478 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"HomeUI","BookPath","OneWay","DomesticDomestic","WithIn7Days"})
    public void ADT_CHD_pop_up_modal_ages_from_0_to_17(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Home Page*****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91478 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
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
        final String ARR_DATE           = "5";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

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

        //open passenger selection drop down
        pageObjectManager.getHomePage().getPassengerBox().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Verify verbiage Age 0 to 17 is displaying
        ValidationUtil.validateTestStep("Verbiage Age0-17 is displaying on Home Page",
                pageObjectManager.getHomePage().getAge0To17Verbiage().isDisplayed());


    }
}
