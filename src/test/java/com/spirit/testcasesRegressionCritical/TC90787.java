package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90787
//Description: BAGS_CP_BP_OW_DOM_1PAX_VALIDATE BAG TOTAL BAR
//Created By : Alex Rodriguez
//Created On : 24-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 27-Jun-2019
//**********************************************************************************************
public class TC90787 extends BaseClass {

  @Parameters({"platform"})
  @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,"CarryOn","CheckedBags", "BagsUI"})
  public void BAGS_CP_BP_OW_DOM_1PAX_VALIDATE_BAG_TOTAL_BAR(@Optional("NA") String platform) {
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC90787 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
    }
    //Home Page Constant Values
    final String LANGUAGE         = "English";
    final String JOURNEY_TYPE     = "Flight";
    final String TRIP_TYPE        = "OneWay";
    final String DEP_AIRPORTS     = "AllLocation";
    final String DEP_AIRPORT_CODE = "LGA";
    final String ARR_AIRPORTS     = "AllLocation";
    final String ARR_AIRPORT_CODE = "FLL";
    final String DEP_DATE         = "3";
    final String ARR_DATE         = "NA";
    final String ADULT            = "1";
    final String CHILD            = "0";
    final String INFANT_LAP       = "0";
    final String INFANT_SEAT      = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT       = "Nonstop";
    final String FARE_TYPE        = "Standard";
    final String UPGRADE_VALUE    = "BookIt";

    //Bags Page Method
    final String DEP_BAGS         = "Carry_1|Checked_3";

    //open browser
    openBrowser(platform);

    //****************************************************************************
    //**************************Home Page Methods*********************************
    //****************************************************************************/
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //****************************************************************************
    //* *************Flight Availability Page Methods*****************************
    //****************************************************************************/
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    /****************************************************************************
     * *****************Passenger Information Page Methods************************
     ****************************************************************************/
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    /****************************************************************************
     * ************************Bags Page Methods*********************************
     ****************************************************************************/
    WaitUtil.untilPageLoadComplete(getDriver());
    pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

    /***************************************************
     *************Validation on Bags Page**************
     ***************************************************/
//Step -- 4
    final String totalDueContainerBarColorExpected  = "0, 0, 0";
    final String bagsTotalTextColorExpected         = "255, 255, 255";

    WaitUtil.untilTimeCompleted(2000);
    String totalDueContainerBarColorAutual = pageObjectManager.getBagsPage().getTotalDueContainerPanel().getCssValue("background-color");
    String bagsTotalTextColorAutual = pageObjectManager.getBagsPage().getBagsTotalContainerText().getCssValue("color");

    ValidationUtil.validateTestStep("Validating Bags Total Container Bar color is Black on Bags Page" ,totalDueContainerBarColorAutual,totalDueContainerBarColorExpected);
    ValidationUtil.validateTestStep("Validating Bags Total text color is White on Bags Page " ,bagsTotalTextColorAutual,bagsTotalTextColorExpected);

    pageObjectManager.getBagsPage().getBagsTotalContainerLink().click();
    WaitUtil.untilTimeCompleted(2000);
    ValidationUtil.validateTestStep("Validating that the city pairs are present in Price Break down of Bags page",
            pageObjectManager.getBagsPage().getOutboundJourneyBreakdownCityPairText().getText().trim().contains(DEP_AIRPORT_CODE)
                    &&pageObjectManager.getBagsPage().getOutboundJourneyBreakdownCityPairText().getText().trim().contains(ARR_AIRPORT_CODE));

    //Declare variable used for validation
    String carryOnBagPrice = pageObjectManager.getBagsPage().getOutboundJourneyBreakdownCarryOnBagTotalPriceText().getText().replace("$", "").trim();
    double carryOnPrice = Double.parseDouble(carryOnBagPrice); //changed to double
    //get checked bag price
    String checkedBagPrice = pageObjectManager.getBagsPage().getOutboundJourneyBreakdownCheckedBagTotalPriceText().getText().replace("$", "").trim();
    double checkedPrice = Double.parseDouble(checkedBagPrice); //changed to double
    String bagsTotalStr = Double.toString(carryOnPrice + checkedPrice); //bags total converted to a string

//Step -- 5

    //open the shopping cart breakdown
    pageObjectManager.getHeader().getArrowYourItineraryImage().click();
    WaitUtil.untilTimeCompleted(2000);
    ValidationUtil.validateTestStep("The Shopping Cart price for bags is displayed correctly: " + bagsTotalStr,
            pageObjectManager.getHeader().getBagsPriceItineraryText().getText().trim(),bagsTotalStr);
  }
}