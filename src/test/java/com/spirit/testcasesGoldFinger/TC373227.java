package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import java.lang.String;

//**********************************************************************************************
//Test CaseID: TC373227
//Title      : CP - Car Verbiage - Hertz - Booking Upsell - Validate correct verbiage displays
//Created By : Kartik Chauhan
//Created On : 06-Nov-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373227 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath"})
    public void CP_Car_Verbiage_Hertz_Booking_Upsell_Validate_correct_verbiage_displays(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373227 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Reservation Credit Path Constant variables
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE2         = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE2          = "15";
        final String ARR_DATE2          = "19";
        final String ADULTS             = "3";
        final String CHILDREN           = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Through";
        final String RET_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE2, ARR_DATE2);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        final String CAR_COMPANY_NAME       = "Hertz";

        pageObjectManager.getCarPage().getViewAllCarsButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getCarPageMethods().filterCarByRentalAgency(CAR_COMPANY_NAME);
        pageMethodManager.getCarPageMethods().filterCarByCarType("Economy");

        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY_NAME);
    }
}