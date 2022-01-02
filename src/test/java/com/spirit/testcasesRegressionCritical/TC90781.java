package com.spirit.testcasesRegressionCritical;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC90781
// TestCase : 35291 Bags_CP_BP_DOM_1PAX_Validate Surfboard info
// Created By : Kartik Chauhan
// Created On : 24-Jun-2019
// Reviewed By: Salim Ansari
// Reviewed On: 25-Jun-2019
// **********************************************************************************************
public class TC90781 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "BagsUI"})
    public void Bags_CP_BP_DOM_1PAX_Validate_Surfboard_info(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Bags Page via FA user*****************
         ******************************************************************************/
        //Mention Suite and Browser in reportsF
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90781 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LGA";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MYR";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "Bookit";

        //Bags Page Method
        final String BAG_URL            = "book/bags";
        final String MORE_INFO_LINK_URL = "https://customersupport.spirit.com/hc/en-us/articles/202096586-Can-I-bring-my-surfboard-or-wakeboard-on-my-trip";
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
//STEP--2
        //User redirect To Bags Page
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL",
                getDriver().getCurrentUrl(),BAG_URL);
//STEP--3
        //Clicked on Departing Sorting Equipment
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(0).click();
//STEP--4
        //Clicked on Departing Bicycle Info Icon link
        pageObjectManager.getBagsPage().getDepartingSurfBoardInfoIconLink().get(0).click();

//STEP--5
        //User clocked on More info link
        pageObjectManager.getBagsPage().getSportingEquipmentToolTipMoreInfoLink().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(2000);

        //switch to second window
        TestUtil.switchToWindow(getDriver(),1);

        //verify href URL of more info link on Bags Page
        WaitUtil.untilPageURLVisible(getDriver(),MORE_INFO_LINK_URL);

        ValidationUtil.validateTestStep("Verify User navigated to Surfboard Information Page",
                getDriver().getCurrentUrl(), MORE_INFO_LINK_URL);

        //close second browser
        getDriver().close();

        //switch to default window
        TestUtil.switchToWindow(getDriver(),0);

    }
}
