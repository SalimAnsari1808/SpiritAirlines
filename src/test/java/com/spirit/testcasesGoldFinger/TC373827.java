package com.spirit.testcasesGoldFinger;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//**********************************************************************************************
//Test CaseID: TC373827
//Title      : 002 - CP - Car Verbiage - Dollar - Flight + Car - Validate correct verbiage displays
//Created By : Kartik Chauhan
//Created On : 08-Nov-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Nov-2019
//**********************************************************************************************
public class TC373827 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","RoundTrip","FlightCar","Guest","DomesticDomestic","WithIn21Days","Adult","FlightAvailabilityUI","CarsUI","Spanish"})
    public void CP_Car_Verbiage_Dollar_Flight_Car_Validate_correct_verbiage_displays(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373827 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "109";
        final String ADULTS             = "5";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        //Car Page Constant Values
        final String CAR_COMPANY        = "Thrifty_Spanish";
       //open browser
        openBrowser(platform);
//Step--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step--2/3/4
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
//Step5-6
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getSelectedLanguage().get(0));
        //Flight Availability Methods
        pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA");
        /////////////////////////////ALAMO cars are not available*************************
//Step7
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency(CAR_COMPANY);

//Step--9
        pageObjectManager.getCarPage().getCarPopUpLocationsTab().click();
    }
}