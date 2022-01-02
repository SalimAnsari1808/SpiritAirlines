package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC373368
//Title      : 008 - CP - Car Verbiage - Hertz - Flight + Hotel + Car - Validate verbiage  for a booking with an International Destination
//Created By : Kartik Chauhan
//Created On : 27-Nov-2019
//Reviewed By: Gabriela
//Reviewed On: 9-Dec-2019
//**********************************************************************************************

public class TC373368 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"OutOfExecution","BookPath","RoundTrip","FlightHotelCar","Guest","DomesticInternational","Outside21Days","Adult","CarsUI"})
    public void CP_Car_Verbiage_Hertz_Flight_Hotel_Car_Validate_verbiage_for_a_booking_with_an_International_Destination(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373368 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "109";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        final String CAR_COMPANY        = "HERTZ";

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


        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");
//Step5-6
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA"); // changed to Hertz due Alamo is not available

       pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);
    }
}