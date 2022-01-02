package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC349825
//Description : CP - Flight + Hotel - Availability Page - NEG Validate the More info and less info links are replaced with just More and Less
//Created By  :  Kartik Chauhan
//Created On  :  26-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 9-Dec-2019
//**********************************************************************************************
public class TC349825 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightAvailabilityUI","FlightHotelCar", "Outside21Days", "Adult", "Guest", "HotelsUI"})
    public void CP_Flight_Hotel_Availability_Page_NEG_Validate_the_More_info_and_less_info_links_are_replaced_with_just_More_and_Less (@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349825 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "140";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

//- Step 1: Using Google Chrome, Access Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Book Vacation F+H+C | DOM | 3 months out | 5 ADT |  25+ Driver
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select hotel and room and continue
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        ValidationUtil.validateTestStep("Validating 'More Info' is not displaying",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getMoreInformationLink()));

        //click on any link
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelCard().size(); i ++) {
            pageObjectManager.getHotelPage().getHotelNamesText().get(i).click();
            WaitUtil.untilPageLoadComplete(getDriver());

            ValidationUtil.validateTestStep("Verifying hotel Rooms link displayed",
                    TestUtil.verifyElementDisplayed( pageObjectManager.getHotelPage().getHotelPopUpRoomsTab()));

            ValidationUtil.validateTestStep("Validating 'Overview' is displaying",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpOverviewTab()));

            ValidationUtil.validateTestStep("Validating 'Policies' is displaying",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpPoliciesTab()));

            ValidationUtil.validateTestStep("Validating 'Amenities' is displaying",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpAmenitiesTab()));

            ValidationUtil.validateTestStep("Validating 'Dinning' is displaying",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpDiningTab()));

//            ValidationUtil.validateTestStep("Validating 'Activities & Entertainment' is displaying",
//                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpActivitiesTab()));

            ValidationUtil.validateTestStep("Validating 'Photos' is displaying",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpPhotosTab()));

            ValidationUtil.validateTestStep("Validating 'map' is displaying",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpMapTab()));
        }

    }
}