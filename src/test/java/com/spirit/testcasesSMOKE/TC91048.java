package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.TestUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase :TC91048
// Description: Flight Availability_CP_BP_Flight Only_Week Calendar View_Wireframe
// Created By : Kartik Chauhan
// Created On : 20-May-2019
// Reviewed By: Salim Ansari
// Reviewed On: 20-May-2019
// **********************************************************************************************
public class TC91048 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "FlightAvailabilityUI"})
    public void Flight_Availability_CP_BP_Flight_Only_Week_Calendar_View_Wireframe(@Optional("NA") String platform) {
        //mention the browser
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91048 under SMOKE suite on " + platform + " Browser", true);
        }
        /******************************************************************************
         ********************************Navigate to Flight Availability Page**********
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

//Step1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        String date1 = TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString(), "d");
        pageMethodManager.getHomePageMethods().clickSearchButton();
//STEP--2& 3
        //Flight Availability Methods
        //validate Week tab is displaying
        ValidationUtil.validateTestStep("Week tab is displaying",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselWeekViewSwitchLabel().isDisplayed());

        //validate Week tab is displayingSelected date is displaying in Outlined
        ValidationUtil.validateTestStep("Month tab is displaying",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().isDisplayed());

        //validate Week tab is displaying
        ValidationUtil.validateTestStep("Dollar tab is displaying",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDollarsViewSwitchLabel().isDisplayed());

        //validate Week tab is displaying
        ValidationUtil.validateTestStep("Mile tab is displaying",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().isDisplayed());

//STEP--4 & //STEP--5
        //validate Left arrow is displaying
        ValidationUtil.validateTestStep("Left arrow is displaying",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselLeftButton().get(0).isDisplayed());

        //validate Right arrow is displaying
        ValidationUtil.validateTestStep("Right arrow is displaying",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselRightButton().get(0).isDisplayed());
//STEP--6
        //validate all 7 tiles are displaying
        ValidationUtil.validateTestStep("All 7 Tiles Sections are displaying",
                pageObjectManager.getFlightAvailabilityPage().getDepartingWeeklyTilesGrid().isDisplayed());
//STEP--7
        //Calculate size of all 7 tiles
        int TotalTileCount = pageObjectManager.getFlightAvailabilityPage().getDepartingWeekEachTilesGrid().size();

        //Create loop to verify Each tile for all week
        for (int Tilecounter = 0; Tilecounter < TotalTileCount; Tilecounter++) {
            //All Tiles are displaying
            pageObjectManager.getFlightAvailabilityPage().getDepartingWeekEachTilesGrid().get(Tilecounter).isDisplayed();
        }
//STEP--8, STEP--9., STEP--10 and STEP--11

        //verify if Selected Date box id displaing
        if (pageObjectManager.getFlightAvailabilityPage().getDepartingSelectedDateTileWeekly().isDisplayed()) {
            //validate Selected date is displaying in Outline box
            ValidationUtil.validateTestStep("Selected date is displaying in Outlined box",
                    pageObjectManager.getFlightAvailabilityPage().getDepartingSelectedDateTileWeekly().getText(), date1.replace("^0",""));

        }

//STEP--12
        //verify Saving Money verbiages are displaying
        if (pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsSavingsText().get(0).isDisplayed()) {

            //validate date is displaying Price
            ValidationUtil.validateTestStep("Saving Money verbiages is displaying in Outlined box",
                    pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsSavingsText().get(0).getText(), "$");
        }
//STEP--13
        //verify Prices are displaying
        if (pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsLowestFareInDollarsText().get(0).isDisplayed()) {

            //validate date is displaying Price
            ValidationUtil.validateTestStep("Prices in center are displaying correct in Outlined box",
                    pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsLowestFareInDollarsText().get(0).getText(), "$");
        }
    }
}