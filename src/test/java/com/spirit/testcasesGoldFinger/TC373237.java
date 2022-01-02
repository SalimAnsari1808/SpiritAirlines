package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373237
//Description: Task 27174: TC373237 - 003 - CP - Car Verbiage - Dollar - Flight + Hotel + Car - Validate correct verbiage displays
//Created By:  Gabriela
//Created On:  13-Nov-2019
//Reviewed By: Sunny Sok
//Reviewed On: 10-Dec-2019
//**********************************************************************************************

public class TC373237 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest", "NonStop", "CarsUI"})
    public void CP_Car_Verbiage_Dollar_Flight_Hotel_Car_Validate_correct_verbiage_displays(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373237 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";
        final String CAR_COMPANY        = "Dollar";

//- Step 1: Access Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Click on vacation tab, and click on Flight+Hotel+Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Book Vacation | DOM | 3 months out | 1 ADT |  25+ Driver
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Select hotel and room and continue
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

//- Step 5: Search for VD Dollar, select "More " hyperlink.
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency(CAR_COMPANY); //Switching from Dollar to Thrifty
        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0).click();
        WaitUtil.untilTimeCompleted(3000);

//-Step 6: Verify Policies tab verbiage:
        pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);
    }
}