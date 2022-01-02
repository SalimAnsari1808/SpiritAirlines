package com.spirit.testcasesGoldFinger;


import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;


public class TC86724 extends BaseClass {
  //**********************************************************************************************
//Test Case ID: TC86724
//Description : TC86724- 003- BP_ Car Verbiage THRIFTY with 5 - 14 years old PAX
//Created By  : Alex Rodriguez
//Created On  : 22-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 4-Dec-2019
//**********************************************************************************************
  @Parameters("platform")
  @Test(groups = {"BookPath", "RoundTrip", "Flight", "DomesticDomestic", "Outside21Days", "Child", "Guest", "BookIt","NoBags", "NoSeats","OptionalUI"})
  public void BP_Car_Verbiage_THRIFTY_with_5_14_years_old_PAX(@Optional("NA") String platform){
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC86724 under GoldFinger Suite on " + platform + " Browser", true);
    }
    //Home Page Constant Values
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Flight";
    final String TRIP_TYPE          = "RoundTrip";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "FLL";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "DEN";
    final String DEP_DATE           = "105";
    final String ARR_DATE           = "108";
    final String ADULT              = "0";
    final String CHILD              = "2";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT         = "NonStop";
    final String ARR_Flight         = "NonStop";
    final String FARE_TYPE          = "Standard";
    final String UPGRADE_VALUE      = "BookIt";

  // Step 1	Launch the QA URL for testing Environment
    openBrowser(platform);
    pageMethodManager.getHomePageMethods().launchSpiritApp();
  // Step 2	Create booking on SkySALES: R/T NON-STOP: within 24 hours|  DOM | 2 PAX under 14 yr old | NO BAGS / NO SEATS / NO FRILLS /  Select "Search Flight"
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();
//    5110
    // Step 3	Fill DOB for all child PAX to be under 14yrs, select "Continue"
    fillDobFor13yoPassengers();
  // Step 4	Select "Accept"
    pageMethodManager.getHomePageMethods().selectUMNRPopup();
  // Step 5	Select flight 
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    // Step 6	Guest lands on Login Page continue as Guest Fill out all pernet info for all PAX
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    // Step 7	Continue till Guest Reaches Options Page
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
  // Step 8	Scroll down till Guest sees THRIFTY(Invalid step)
  // Step 9	Verify no car options are available to UMNR
  ValidationUtil.validateTestStep("User verifies Car rental option is not available for minors", !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCarouselTitleText()));


  }

  public void fillDobFor13yoPassengers(){
    int intChildCount  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString());
    int ChildCounter = 0;

    for (int i = 0; i < pageObjectManager.getHomePage().getChildPopUpBirthBoxes().size(); i++) {
      WebElement travellingChildSection = pageObjectManager.getHomePage().getChildTravelerPanel().get(i);

      //check
      if (intChildCount > ChildCounter) {
        //convert date in required format
        travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-4500 - ChildCounter), "MM/dd/yyyy"));

        travellingChildSection.click();

        //increment child date
        ChildCounter = ChildCounter + 1;
      }
    }
    //close child popup
    JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHomePage().getChildPopUpCloseButton());
//    	homePage.getChildPopUpCloseButton().click();

    //wait till page load is complete
    WaitUtil.untilPageLoadComplete(getDriver());
  }

}
