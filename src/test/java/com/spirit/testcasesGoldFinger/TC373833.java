package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC373833
//Title      : 007 - CP - SP -  Car Verbiage - Thrifty - Flight + Car - Validate correct verbiage displays
//Created By : Alex Rodriguez
//Created On : 03-Dec-2019
//Reviewed By: Gabriela
//Reviewed On: 5-Dec-2019
//**********************************************************************************************

public class TC373833 extends BaseClass {

  @Parameters({"platform"})
  @Test(groups = {"OutOfExecution", "BookPath", "Spanish", "FlightCar", "Guest", "DomesticDomestic", "RoundTrip", "Outside21Days", "Adult", "CarsUI"})
  public void CP_SP_Car_Verbiage_Thrifty_Flight_Car_Validate_correct_verbiage_displays(@Optional("NA") String platform) {

    //Mention Suite and Browser in reports
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC373833 under GoldFinger Suite on " + platform + " Browser", true);
    }

    //Home Page Constant variables
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Vacation";
    final String TRIP_TYPE          = "Flight+Car";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "IAH";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "DEN";
    final String DEP_DATE           = "100";
    final String ARR_DATE           = "103";
    final String ADULTS             = "5";
    final String CHILDREN           = "0";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";
    final String DRIVER_AGE         = "25+";

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
    WaitUtil.untilTimeCompleted(500);

    pageMethodManager.getHomePageMethods().clickSearchButton();

//Step-5
    JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getHeader().getEnglishSpanishLink());
    JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getEnglishSpanishLink());

//Step-6
    WaitUtil.untilPageLoadComplete(getDriver());
    pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA");
    pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);

//- Step 8: Click on the "Ubicacione" tab, verify top to bottom:
    pageMethodManager.getCarPageMethods().storeCarInformationOnCarPage(CAR_COMPANY,"NA");
    //TODO: This Method need Salim verification due it's failing
    pageMethodManager.getCommonPageMethods().storeCarnetCarLocationInformation();
    pageMethodManager.getCarPageMethods().verifyLocationTabDetailsWithCarnetWebsite();

  }
}