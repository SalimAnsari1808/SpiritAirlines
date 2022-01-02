package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;


public class TC86725 extends BaseClass {
  //**********************************************************************************************
//Test Case ID: TC86725
//Description : TC86725- 003- BP_ Car Verbiage THRIFTY with 15 - 18 years old PAX
//Created By  : Alex Rodriguez
//Created On  : 23-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 4-Dec-2019
//**********************************************************************************************
  @Parameters("platform")
  @Test(groups = {"BookPath", "OneWay", "Flight", "DomesticDomestic", "Within7Days", "Child", "Guest", "BookIt","NoBags", "NoSeats","OptionalUI"})
  public void BP_Car_Verbiage_THRIFTY_with_15_18_years_old_PAX(@Optional("NA") String platform){
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC86725 under GoldFinger Suite on " + platform + " Browser", true);
    }
    //Home Page Constant Values
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Flight";
    final String TRIP_TYPE          = "OneWay";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "FLL";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "DEN";
    final String DEP_DATE           = "0";
    final String ARR_DATE           = "NA";
    final String ADULT              = "0";
    final String CHILD              = "2";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT         = "NonStop";
    final String FARE_TYPE          = "Standard";
    final String UPGRADE_VALUE      = "BookIt";
    final String DOB16YearOld       = TestUtil.getStringDateFormat((-5850), "MM/dd/yyyy");



    // Step 1	Launch the QA URL for testing Environment
    openBrowser(platform);
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    //    Step 2	Create booking on SkySALES: OW: within 24 hours|  DOM | 2 Pax 15 - 18 years old PAX | NO BAGS / NO SEATS / NO FRILLS /  Select "Search Flight"
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    fillDobFor16yoPassengers();
    // Step 3	Select flight 
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    // Step 4	Guest lands on Login Page continue as Guest Fill out all pernet info for all PAX
    pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(DOB16YearOld);
    pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).sendKeys(DOB16YearOld);

    ValidationUtil.validateTestStep("User enter 16 years of passenger Age on Passenger Information Page", true);
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    // Step 5	Continue till Guest Reaches Options Page
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
    //    Step 6	Verify no car options are available to Pax 15 - 18 yrs old
    ValidationUtil.validateTestStep("User verifies Car rental option is not available for minors",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCarouselTitleText()));

  }

  public void fillDobFor16yoPassengers(){
    int intChildCount  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString());
    int ChildCounter = 0;

    for (int i = 0; i < pageObjectManager.getHomePage().getChildPopUpBirthBoxes().size(); i++) {
      WebElement travellingChildSection = pageObjectManager.getHomePage().getChildTravelerPanel().get(i);

      //check
      if (intChildCount > ChildCounter) {
        //convert date in required format
        travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-5850 - ChildCounter), "MM/dd/yyyy"));

        travellingChildSection.click();

        //increment child date
        ChildCounter = ChildCounter + 1;
      }
    }
    //close child popup
    JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHomePage().getChildPopUpCloseButton());

    //wait till page load is complete
    WaitUtil.untilPageLoadComplete(getDriver());
  }

}
