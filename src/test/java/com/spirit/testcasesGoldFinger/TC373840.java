package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373840
//Description: Task 27240: TC373840 - 013 - CP - Car Verbiage - Dollar - Flight + Hotel + Car - Validate correct verbiage displays - 5 ADT
//Created By: Gabriela
//Created On: 11-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373840 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Spanish","BookPath", "RoundTrip", "DomesticDomestic","FlightHotelCar", "Outside21Days", "Adult", "Guest",
            "NonStop","CarsUI"})
    public void CP_Car_Verbiage_Dollar_Flight_Hotel_Car_Validate_correct_verbiage_displays_5_ADT(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373840 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "106";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";
        final String ROOMS_VALUE        = "2 Rooms";

//- Step 1: Using Google Chrome access to Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Click on vacation tab, and click on Flight+Hotel+Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Book DOM_DOM | 3 months out | 5 ADT | 2 Rooms | 25+ Driver and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //FA page, store all flight information for vacation booking
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

//- Step 4: Select any hotel and proceed to the option/cars page
       pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

//- Step 5: Select Espanol and verify page changes to Spanish
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getSelectedLanguage().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 6: Search for a Dollar.  Click "Mas" link
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency("Dollar");

        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0).click();
        WaitUtil.untilTimeCompleted(3000);

        pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies("DollarSpanish");
//- Step 8: Click on the "Ubicacione" tab, verify top to bottom:
        pageObjectManager.getCarPage().getCarPageMoreInfoLocationsLink().click();
        WaitUtil.untilTimeCompleted(3000);

        //Unable to validate in case the information displayed is right or not, due is received by Carnet website
        pageMethodManager.getCommonPageMethods().storeCarnetCarLocationInformation();
        pageMethodManager.getCarPageMethods().verifyLocationTabDetailsWithCarnetWebsite();
    }
}