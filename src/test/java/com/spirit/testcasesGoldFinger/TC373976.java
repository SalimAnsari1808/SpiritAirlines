package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC373976
//Title      : 015 - CP - SP  - Car Verbiage - Dollar - Flight + Car - Validate verbiage for a booking with an International Origin
//Created By : Kartik Chauhan
//Created On : 28-Nov-2019
//Reviewed By: Gabriela
//Reviewed On: 9-Dec-2019
//**********************************************************************************************

public class TC373976 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Guest","BookPath","FlightCar","InternationalDomestic","Outside21Days","Adult","CarUI","Spanish"})
    public void CP_SP_Car_Verbiage_Dollar_Flight_Car_Validate_verbiage_for_a_booking_with_an_International_Origin(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373976 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "Spanish";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "flight+car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CUN";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "115";
        final String ARR_DATE           = "119";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        //Flight Availability Page Constant Values
        final String CAR_COMPANY        = "DollarSpanish";
        final String CAR_VERBAGE1       = "Descripción del Vehículo";

        //open browser
        openBrowser(platform);
//Step--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step--2/3/4
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step5-6
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

        //Flight Availability Methods
        pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA");

        /////////////////////////////DOLLAR cars are not available*************************
//Step7
        ValidationUtil.validateTestStep("Verify "+CAR_VERBAGE1+" is displayed on Car Modal PopUp",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab().getText(),CAR_VERBAGE1);

        //verify the vehicle description under more infor link
        pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);

//Step--9
        pageMethodManager.getCarPageMethods().storeCarInformationOnCarPage(CAR_COMPANY,"NA");
        pageMethodManager.getCommonPageMethods().storeCarnetCarLocationInformation();
        pageMethodManager.getCarPageMethods().verifyLocationTabDetailsWithCarnetWebsite();
    }
}