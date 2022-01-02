package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373723
//Description: Task 27149: TC373723 - US 17569 - 006 - CP - Booking Path - Hub Packaging - Negative - View All Hotels - UNMR
//Created By: Gabriela
//Created On: 05-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373723 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Child", "Guest", "NonStop", "BookIt", "NoBags",
            "NoSeats", "OptionalUI"})
    public void CP_Booking_Path_Hub_Packaging_Negative_View_All_Hotels_UNMR(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373723 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "15";
        final String ARR_DATE           = "17";
        final String ADULT              = "0";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

//- Step 11: Open Goldfinger test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 1: Input RT | DOM_DOM | 3 months out | 1 Child | and click SEARCH FLIGHTS
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 2: Input Child DOB between ( 5 to 14) and click continue
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 3: Click ACCEPT within Unaccompanied Minor popup modal
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

//- Step 4: Select any Standard flights and click CONTINUE in the STANDARD box
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 5: Click on BOOK IT
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: Fill out customer info
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7 & 8: Click on Continue without bags & Click on I dont need bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 9: click on Continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 10: Validate the hotels section is not displayed on the Options page.
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Hotels for Less section is not displayed on Options page for UNMR",
                !TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getHotelPage().xpath_HotelPanel)));
    }
}