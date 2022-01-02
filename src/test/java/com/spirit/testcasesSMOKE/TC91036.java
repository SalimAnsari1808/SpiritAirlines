package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91036
//Test Name:Task 23336: 31489 Search Widget_CP_BP_Lap child popup, Infants exceeds the number of adults(edit)
//Description: Validating Lap Child pops up when more than 1 infant on lap is selected per passengers
//Created By : Gabriela
//Created On : 13-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-MAY-2019
//**********************************************************************************************

public class TC91036 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "Guest" , "InfantLap" , "HomeUI"})
    public void Search_Widget_CP_BP_Lap_child_popup_Infants_exceeds_the_number_of_adults_edit(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91036 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "Oneway";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "ATL";
        final String DEP_DATE 			= "8";
        final String ARR_DATE 			= "NA";
        final String ADULTS 			= "2";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "3";
        final String INFANT_SEAT		= "0";

        final String EXCEED_LAP_CHILD   = "The number of infants exceeds the number of adults. There must be at least one adult customer for each infant passenger. Please verify the number of passengers and resubmit.";

//-- Step 1: Access Skysales testing environment (web)
        //open browser
        openBrowser(platform);

        pageMethodManager.getHomePageMethods().launchSpiritApp();

//-- Step 2: Select a Point of Origin(From)  to Point of Destination (To), DOM
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

//-- Step 3: Choose a date of departure
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

//-- Step 4: Select 1 adult and 2 children
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);

//-- Step 5: Click on the search flight button
        pageMethodManager.getHomePageMethods().clickSearchButton();

//-- Step 6 and 7: On the Young Travelers popup, make both children under 2 years old and lap child
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        WaitUtil.untilTimeCompleted(2000);

//--Step 8: Lap Child popup comes up
        ValidationUtil.validateTestStep("Validating the right information is displayed on the Lap Child pop up",
                pageObjectManager.getHomePage().getLapChildPopUpInfoText().getText(),EXCEED_LAP_CHILD);

//-- Step 9: Verify that popup reflects the image and click on close icon
        pageObjectManager.getHomePage().getLapChildPopUpCloseButton().click();
    }
}
