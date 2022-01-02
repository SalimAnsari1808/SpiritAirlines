package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373198
//Description: Task 27150: TC373198 - 002 - CP - Vacation Flight + Hotel - NEGATIVE - Validate that a User cannot book UNMR
//Created By: Gabriela
//Created On: 06-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 08-Nov-2019
//**********************************************************************************************

public class TC373198 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightHotelCar", "DomesticDomestic", "HomeUI"})
    public void CP_Vacation_Flight_Hotel_NEGATIVE_Validate_that_a_User_cannot_ok_UNMR(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373198 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";

//- Step 3: Open the GoldFinger test Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Click on vacation tab, Flight+Hotel tab
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

//- Step 2: Validate that you cannot select 1 child without an adult
        //Click on passenger box
        pageObjectManager.getHomePage().getPassengerBox().click();

        //Taking out all the possible adults
        WaitUtil.untilTimeCompleted(1000);

        for (int i = 0; i < 3; i ++)
        {
            pageObjectManager.getHomePage().getAdultMinusLink().click();
            WaitUtil.untilTimeCompleted(1000);
        }

        ValidationUtil.validateTestStep("Validating there is not option to book without atleast 1 adult on vacation booking",
                !pageObjectManager.getHomePage().getAdultBox().getText().equals(0));
    }
}