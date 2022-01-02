package com.spirit.testcasesGoldFinger;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//**********************************************************************************************
//Test CaseID: TC373962
//Title      : 009 - CP - SP - Car Verbiage - Thrifty - Flight + Car - Validate verbiage for a booking with an International Origin
//Created By : Kartik Chauhan
//Created On : 29-Nov-2019
//Reviewed By: Gabriela
//Reviewed On: 10-Dec-2019
//**********************************************************************************************
public class TC373962 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","RoundTrip","FlightCar","Guest","InternationalDomestic","Outside21Days","Adult","CarsUI","Spanish"})
    public void CP_SP_Car_Verbiage_Thrifty_Flight_Car_Validate_verbiage_for_a_booking_with_an_International_Origin(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373962 under GOLD-FINGER Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CUN";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "23";
        final String ARR_DATE           = "26";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        //Flight Availability Page Constant Values
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
//Step5
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getSelectedLanguage().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA");
        pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);

// Step 8: Click on the "Ubicacione" tab, verify top to bottom:
        pageMethodManager.getCarPageMethods().storeCarInformationOnCarPage(CAR_COMPANY,"NA");
        pageMethodManager.getCommonPageMethods().storeCarnetCarLocationInformation();
        pageMethodManager.getCarPageMethods().verifyLocationTabDetailsWithCarnetWebsite();
    }
}