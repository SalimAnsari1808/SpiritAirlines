package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID:TC373824
//Description : TC373824 - US 21915 - 006 - CP - Verbiage Car Driver Below 25 - Flight + Hotel + Car - Thrifty
//Created By  : Alex Rodriguez
//Created On  : 25-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : Dec-5-2019
//*********************************************************************************************
public class TC373824 extends BaseClass {
  @Parameters({"platform"})
  @Test(groups = {"Spanish", "BookPath", "RoundTrip", "FlightHotelCar", "Guest", "DomesticDomestic", "Outside21Days",
          "Adult", "Child","BookIt", "MandatoryFields", "PassengerInformationUI"})
  public void US_21915_006_CP_Verbiage_Car_Driver_Below_25_Flight_Hotel_Car_Thrifty(@Optional("NA") String platform){

    //Mention Suite and Browser in reports
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC373824 under GoldFinger Suite on " + platform + " Browser", true);
    }
    //Home Page Constant Values
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Vacation";
    final String TRIP_TYPE          = "Flight+Hotel+Car";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "EWR";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "LAS";
    final String DEP_DATE           = "135";
    final String ARR_DATE           = "136";
    final String ADULT              = "5";
    final String CHILD              = "3";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";
    final String ROOMS_VALUE        = "2 Rooms";
    final String DRIVER_AGE 		= "21-24";
    final String DOB21YearOld       = TestUtil.getStringDateFormat((-7700), "MM/dd/yyyy");
//    final String DOB25YearOld       = TestUtil.getStringDateFormat((-9130), "MM/dd/yyyy");

    //Flight Availability Page Constant Values
    final String UPGRADE_VALUE      = "BookIt";

    //  Step 1	Open the Goldfinger testing Website
  //  Step 2	Create a vacation booking flight + hotel + car | DOM to DOM| Any date outside 48h | 5 ADT 3 CHD | Drivers age 21 - 24 | 2 Room | select "SEARCH VACATION"
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
    pageMethodManager.getHomePageMethods().clickSearchButton();

  //  Step 3	Fill DOB for all child PAX, select "Continue"
    pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
  //  Step 4	Select "VIEW" in the box of a Hotel you want
  //  Step 5	Select "ROOMS FROM $$$" and "SELECT ROOM"
    pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA", "NA");

  //  Step 7	Click on "Book Car" button inside of one Alamo Car content box
    pageMethodManager.getCarPageMethods().selectCarOnCarPage("Thrifty", "NA");

  //  Step 8	If the Upgrade & Save pop up is displayed selected Book it
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

  //  Step 9	Make one Passenger DOB 21 year old and Another one older than 25 and Populate all the required information.
    pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(DOB21YearOld);
    //pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).sendKeys(DOB25YearOld);

    ValidationUtil.validateTestStep("User enter 21 years of passenger Age on Passenger Information Page", true);
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

  //  Step 10	On the Drop down for the Primary driver selection, "Who's driving?" section choose the passenger with the age above 25 and verify that the verbiage for Additional underage charges is not displayed.
  //  Step 11	On the Drop down for driver selection "Who's driving?" choose the passenger with the age of 21
    pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
  //  Step 12	"Above the Drop down in the section Who's driving? verify that the following verbiage is displayed in bold:
  //  Additional underage charges and/or restrictions may apply for Guests under 25 years age. "
    String primaryDriverVerbiage = "Additional underage charges and/or restrictions may apply for Guests under 25 years age.";
    ValidationUtil.validateTestStep("The under age charge and restriction above the primary driver label is displayed", underAgeChargesForUnder25().getText(), primaryDriverVerbiage);
//Step 11: Click the Spanish link on the Header and the verbiage should be displayed in Spanish:
    JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getHeader().getEnglishSpanishLink());
    JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getEnglishSpanishLink());
    String spanish_primaryDriverVerbiage = "Cargos adicionales y/o restricciones por minoría de edad podrían aplicar a Invitados menores de 25 años de edad.";
    ValidationUtil.validateTestStep("The under age charge and restriction above the primary driver label is displayed in spanish" , underAgeChargesForUnder25().getText(), spanish_primaryDriverVerbiage);
  }


  public WebElement underAgeChargesForUnder25() {
    return getDriver().findElement(By.xpath("//app-contact-input/preceding-sibling::section[1]//div[1]//div[2]//div"));
  }


}
