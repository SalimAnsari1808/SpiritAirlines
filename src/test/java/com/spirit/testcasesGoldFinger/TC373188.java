package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase :002 - CP - Vacation Flight + Car - NEGATIVE - Validate that a user cannot book UNMR
// Description: UMNR can not book Vacation Flight
// Created By : Kartik Chauhan
// Created On : 07-Nov-2019
// Reviewed By: Salim Ansari
// Reviewed On: 18-Nov-2019
// **********************************************************************************************

public class TC373188 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups = {"BookPath", "RoundTrip","FlightCar", "DomesticDomestic", "Outside21Days", "Child","HomeUI"})
    public void CP_Vacation_Flight_Car_NEGATIVE_Validate_that_a_user_cannot_book_UNMR (@Optional("NA")String platform){

        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC373188 under GoldFinger suite on " + platform +" Browser", true);
        }
        /******************************************************************************
         ****************************Navigate to Home Page*****************************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE 			= "Flight+Car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "30";

//Step1/2
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

        //Click on Passenger textBox
        pageObjectManager.getHomePage().getPassengerBox().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Click on Adult Minus button
        pageObjectManager.getHomePage().getAdultMinusLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Click on Child Plus button
        pageObjectManager.getHomePage().getChildPlusLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//Step--3
        ValidationUtil.validateTestStep("Validate user deselect all child ",
                pageObjectManager.getHomePage().getAdultMinusLink().getAttribute("class").contains("icon-minus-circle disabled"));

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("User can not select 1 child with out an Adult in Flight+Car booking",
                JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getHomePage().getAdultBox()).contains("1"));

    }
}