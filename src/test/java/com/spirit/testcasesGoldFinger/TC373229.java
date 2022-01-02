package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test CaseID: TC373229
//Title      : CP - Car Verbiage - Hertz - Flight + Car - Validate correct verbiage displays
//Created By : Kartik Chauhan
//Created On : 06-Nov-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Nov-2019
//**********************************************************************************************
public class TC373229 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath"})
    public void CP_Car_Verbiage_Hertz_Flight_Car_Validate_correct_verbiage_displays(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373229 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "flight+car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "90";
        final String ARR_DATE           = "93";
        final String ADULTS             = "3";
        final String CHILDREN           = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        final String CAR_COMPANY        = "Hertz";
        final String CAR_VERBAGE1       = "Vehicle Description";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Step 4
        //Flight Availability Methods
        pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA");

        ValidationUtil.validateTestStep("Verify "+CAR_VERBAGE1+" is displayed on Car Modal PopUp",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab().getText(),CAR_VERBAGE1);

        pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);
    }
}