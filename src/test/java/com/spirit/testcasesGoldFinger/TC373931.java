package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID:TC373931
//Description : TC373931- 009 - CP - Verbiage Car Driver Below 21 - Flight + Hotel + Car - Hertz
//Created By  : Alex Rodriguez
//Created On  : 25-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 4-Dec_2019
public class TC373931 extends BaseClass {
  @Parameters({"platform"})
  @Test(groups = {"BookPath", "RoundTrip", "FlightHotelCar", "Guest", "DomesticDomestic", "Outside21Days", "Adult","BookIt", "MandatoryFields", "PassengerInformationUI"})
  public void CP_Verbiage_Car_Driver_Below_21_Flight_Hotel_Car_Hertz(@Optional("NA") String platform) {

    //Mention Suite and Browser in reports
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC373931 under GoldFinger Suite on " + platform + " Browser", true);
    }

    //Home Page Constant Values
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Vacation";
    final String TRIP_TYPE          = "Flight+Hotel+Car";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "IAH";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "LAS";
    final String DEP_DATE           = "135";
    final String ARR_DATE           = "136";
    final String ADULT              = "1";
    final String CHILD              = "0";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";
    final String ROOMS_VALUE        = "1 Room";
    final String DRIVER_AGE 		= "21-24";
    final String DOB20YearOld       = TestUtil.getStringDateFormat((-7300), "MM/dd/yyyy");

    final String UPGRADE_VALUE 	    = "BookIt";

    final String HOME_URL           = "spirit.com";

//  Step 1	Open the Goldfinger testing Website
//  Create a vacation booking flight + hotel + car | Any date outside 48h | 1 ADT | Drivers age 21 - 24 | 1 Room Select "SEARCH VACATION"
    openBrowser(platform);
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
    pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);

//  Step 3	Fill DOB for all child PAX, select "Continue"
    pageMethodManager.getHomePageMethods().clickSearchButton();

//  Step 4	Select "VIEW" in the Hotel content box of a Hotel you want
//  Step 5	Select "ROOMS FROM $$$" and "SELECT ROOM"
    pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA", "NA");
//  Step 6	Scroll down and select "CONTINUE"
//  Select "BOOK CAR" in the box for a Hertz car
    pageMethodManager.getCarPageMethods().selectCarOnCarPage("Hertz", "NA");
//  Step 8	Select "BOOK IT"
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//  Step 9	Input all Adult passengers DOB less than 21 years old and verify that the drop down will not pull a driver.
    pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(DOB20YearOld);

    ValidationUtil.validateTestStep("User enter 20 years of passenger Age on Passenger Information Page", true);
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
//  Step 10	Have the Primary driver box remain grayed out and fill out any information needed on the page then click CONTINUE at the bottom of the page
    ValidationUtil.validateTestStep("User verifies primary driver dropdown is grayed out", TestUtil.verifyAttributePresent(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(), "disabled"));
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//  Step 11	"Verify the following verbiage in the popup modal:
//  We’re sorry - Guests must be at least 21 years old to add a rental car.  If you’re under 21, please continue without a car selected."
    String insufficientAgeVerbiage = "We’re sorry - Guests must be at least 21 years old to add a rental car.  If you’re under 21, please continue without a car selected.";

    pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpVerbageText().isDisplayed();
    ValidationUtil.validateTestStep("User validates insufficient age verbiage",  pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpVerbageText().getText(), insufficientAgeVerbiage);
//  Step 12	Click the FLIGHT ONLY button within the Insufficient Age popup modal
    pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpFlightOnlyButton().click();
    WaitUtil.untilPageURLVisible(getDriver(), HOME_URL);
    ValidationUtil.validateTestStep("User successfully lands on the home page after selecting 'Flight Only Button'", true);
  }
}