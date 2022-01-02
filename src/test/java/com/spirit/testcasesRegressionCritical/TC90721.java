package com.spirit.testcasesRegressionCritical;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
// TestCase ID: TC90721
// TestCase : BAGS_CP_BP_OW_DOM_1PAX_VALIDATE VERBIAGE ON FOOTER
// Created By : Kartik Chauhan
// Created On : 24-Jun-2019
// Reviewed By: Salim Ansari
// Reviewed On: 26-Jun-2019
// **********************************************************************************************
public class TC90721 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "BagsUI"})
    public void BAGS_CP_BP_OW_DOM_1PAX_VALIDATE_VERBIAGE_ON_FOOTER(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Bags Page via FA user*****************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90721 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
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
        final String BAG_URL         = "book/bags";
        final String MAX_SIZE        = "* Maximum size includes handles and wheels";
        final String FOLLOWING_ITEMS = "**The following items are not counted as carry-on items: umbrella, assistive devices, outer garments (coats/hats/wraps), camera, car seat/stroller, infant diaper bag, medicine, pet container, reading material for the flight, or food for immediate consumption. More information.";
        final String CERTAIN_EMBARGO = "Certain embargo restrictions may apply depending on destination.  All bag fees are non-refundable.";

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
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        //User redirect To Bags Page
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL",
                getDriver().getCurrentUrl(), BAG_URL);
//STEP--2
        //verify the verbiage "max size include...."
        ValidationUtil.validateTestStep("Verify 'maximum size..' verbiage is displaying at the bottom of the Bags Page",
                pageObjectManager.getBagsPage().getMaximumSizeText().getText(),MAX_SIZE);
//STEP--3
        //verify the verbiage "The following Items are not....."
        ValidationUtil.validateTestStep("Verify 'The following Items are not..' verbiage is displaying at the bottom of the Bags Page",
                pageObjectManager.getBagsPage().getFollowingItemText().getText(),FOLLOWING_ITEMS);
//STEP--4
        //verify the verbiage "Certain Embargo....."
        ValidationUtil.validateTestStep("Verify 'Certain Embargo..' verbiage is displaying at the bottom of the Bags Page",
                pageObjectManager.getBagsPage().getCertainEmbargomText().getText(),CERTAIN_EMBARGO);

    }
}
