package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

//**********************************************************************************************
//Test CaseID: TC373832
//Title      : 001 - CP - SP - Car Verbiage - Thrifty - Booking Upsell - Validate correct verbiage displays (Spanish)
//Created By : Alex Rodriguez
//Created On : 03-Dec-2019
//Reviewed By: Gabriela
//Reviewed On: 4_Dec_2019
//**********************************************************************************************

public class TC373832 extends BaseClass {

  @Parameters({"platform"})
  @Test(groups = {"BookPath", "Spanish", "RoundTrip", "Guest", "DomesticDomestic", "Outside21Days", "Adult","Child","NonStop","BookIt", "NoBags","NoSeats", "CarsUI"})
  public void CP_SP_Car_Verbiage_Thrifty_Booking_Upsell_Validate_correct_verbiage_displays_Spanish(@Optional("NA") String platform) {

    //Mention Suite and Browser in reports
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC373832 under GoldFinger Suite on " + platform + " Browser", true);
    }

    //Home Page Constant variables
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Flight";
    final String TRIP_TYPE          = "RoundTrip";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "IAH";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "DEN";
    final String DEP_DATE           = "100";
    final String ARR_DATE           = "103";
    final String ADULTS             = "5";
    final String CHILDREN           = "2";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT         = "NonStop";
    final String ARR_Flight         = "NonStop";
    final String FARE_TYPE          = "Standard";
    final String UPGRADE_VALUE      = "BookIt";

    final String CAR_COMPANY        = "Thrifty_Spanish";

    //open browser
    openBrowser(platform);
//Step--1
    //Home Page Methods
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step--2/3
    //Home Page Methods
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();
    pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//Step 4: Select first flights, click "Continue"
    //Flight Availability Methods
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
//- Step 5: Select Book it
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//- Step 6 & 7: Fill out passenger info and click "Continue"
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 8 & 9: click "continue without bags" at the bottom of the pag & Click "I Don't Need Bags"
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 10: Click "Continue without seats"
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 11: Click "View All Cars" link
//- Step 12: Select Espanol and verify page changes to Spanish
    WaitUtil.untilPageLoadComplete(getDriver());
    //loop through all language link
    List<WebElement> appLanguage = pageObjectManager.getHomePage().getSelectedLanguage();
      //check for displayed language link on Home Page
      if(appLanguage.get(0).isDisplayed()) {
        //change to Spanish language
        JSExecuteUtil.clickOnElement(getDriver(), appLanguage.get(0));
        WaitUtil.untilPageLoadComplete(getDriver());
      }else {
        //change to Spanish language
        JSExecuteUtil.clickOnElement(getDriver(), appLanguage.get(1));
        WaitUtil.untilPageLoadComplete(getDriver());
      }

    pageObjectManager.getCarPage().getViewAllCarsButton().click();

//- Step 13: Search for Thrifty Economy Car 2 or 4 door "Mitsubishi Mirage Or Similar,"; Click the "Mas" link to expand the tile.
    pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA");

    //verify the vehicle description under more info link
//- Step 14: Click the "Políticas" tab and Verify top to bottom:
    pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0).click();
    WaitUtil.untilTimeCompleted(3000);
    pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);

//- Step 15: Click on the "Ubicacione" tab, verify top to bottom:
    pageObjectManager.getCarPage().getCarPageMoreInfoLocationsLink().click();
    WaitUtil.untilTimeCompleted(3000);

    pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA");
    pageMethodManager.getCarPageMethods().storeCarInformationOnCarPage(CAR_COMPANY,"NA");

    //TODO: Method is failing, needs Salim verification
    pageMethodManager.getCommonPageMethods().storeCarnetCarLocationInformation();
    pageMethodManager.getCarPageMethods().verifyLocationTabDetailsWithCarnetWebsite();
    //Unable to validate in case the information displayed is right or not, due is received by Carnect website
  }
}