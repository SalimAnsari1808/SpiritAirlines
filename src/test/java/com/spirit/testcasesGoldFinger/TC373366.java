package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:15876] - CP: BP: Options page:  Cars or Hotels sections are not displayed for an International destination (CUN)
//Test CaseID: TC373366
//Title      : 006 - CP - Car Verbiage - Hertz - Flight + Car - Validate verbiage  for a booking with an International Destination
//Created By : Kartik Chauhan
//Created On : 27-Nov-2019
//Reviewed By: Gabriela
//Reviewed On: 9-Dec-2019
//**********************************************************************************************

public class TC373366 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"OutOfExecution","ActiveBug", "BookPath","RoundTrip","FlightCar","Guest","DomesticInternational","Outside21Days","Adult","CarsUI"})
    public void CP_Car_Verbiage_Hertz_Flight_Car_Validate_verbiage_for_a_booking_with_an_International_Destination(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373366 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "109";
        final String ADULTS             = "2";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        //Flight Availability Constant Values
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

//Step5-6
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA");

        //verify the vehicle description under more infor link
        pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);
    }
}