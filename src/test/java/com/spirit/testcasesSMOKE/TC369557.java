package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// Test Case ID: TC369557
// Test Name   : BAGS_CP_BP_OW_DOM_1PAX_VALIDATE VERBAGE IN BAGS BOX
// Description : Verify embargo restriction link and More information link on bags page
// Created By  : Kartik Chauhan
// Created On  : 13-May-2019
// Reviewed By : Salim Ansari
// Reviewed On : 15-May-2019
// **********************************************************************************************

public class TC369557 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "BagsUI"})
    public void BAGS_CP_BP_OW_DOM_1PAX_VALIDATE_VERBAGE_IN_BAGS_BOX (@Optional("NA")String platform){
        /******************************************************************************
         *********************Navigate to Flight Availability Bags Page****************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC369557 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "OneWay";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "10";
        final String ARR_DATE               = "NA";
        final String ADULTS	                = "1";
        final String CHILDREN               = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT      	    = "nonstop";
        final String FARE_TYPE		        = "Standard";
        final String UPGRADE_TYPE           = "BookIt";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//STEP--2
        //Bags Page Methods
        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--3 & STEP--4
        //Create constant on Bags Page
        final String EMBARGO_RESTRICTIONS   = "https://customersupport.spirit.com/hc/en-us/articles/202096466-Are-there-any-restrictions-on-how-many-bags-I-can-bring-";
        final String MORE_INFO              = "https://customersupport.spirit.com/hc/en-us/articles/202096616-How-much-does-Spirit-charge-for-bags-";

        //verify embargo restriction link is redirected to proper link
        ValidationUtil.validateTestStep("User redirected successfully on Embargo restriction link",
                pageObjectManager.getBagsPage().getEmbargoRestrictionsLink().getAttribute("href"),EMBARGO_RESTRICTIONS);
//STEP--5
        //verify More Info link is redirected to proper link
        ValidationUtil.validateTestStep("User redirected successfully on More Information Link",
                pageObjectManager.getBagsPage().getMoreInformationLink().getAttribute("href"),MORE_INFO);

    }
}