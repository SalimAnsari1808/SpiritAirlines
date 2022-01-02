package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90969
//Description: Check-in_Options_CP_BP_Options Page_NEG_RT_INT_Lima Peru
//Created By : Alex Rodriguez
//Created On : 20-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-Mar-2019
//**********************************************************************************************

public class TC90969 extends BaseClass{
  @Parameters({"platform"})
  @Test(groups = {"Guest","BookPath","RoundTrip","InternationalDomestic","Within21Days","Adult","BookIt","NonStop",
                  "NoBags","NoSeats","CheckInOptions","PaymentUI"})
  public void Check_in_Options_CP_BP_Options_Page_NEG_RT_INT_Lima_Peru(@Optional("NA") String platform) {

    //Mention Suite and Browser in reports
    if(!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC90969 under BAT Suite on " + platform + " Browser"   , true);
    }

    //Home Page Constant Values
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Flight";
    final String TRIP_TYPE          = "RoundTrip";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "LIM";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "FLL";
    final String DEP_DATE           = "15";
    final String ARR_DATE           = "18";
    final String ADULTS             = "1";
    final String CHILDREN           = "0";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT         = "NonStop";
    final String ARR_Flight         = "NonStop";
    final String FARE_TYPE          = "Standard";
    final String UPGRADE_VALUE      = "BookIt";

    //Options Page Constant Values
    final String OPTIONS_VALUE      = "CheckInOption:AirportAgent";

    //open browser
    openBrowser(platform);

    //Home Page Methods
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //Flight Availability Methods
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    //Passenger Info Methods
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageObjectManager.getPassengerInfoPage().getPrimaryPassengerIstheContactPersonCheckBox().click();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //Bags Page Methods
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

    //Seats Page Methods
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

    //Options Page Methods
    pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

    //******************************************************************************
    //******************Validate Price on Payment Page******************************
    //******************************************************************************/
    pageMethodManager.getPaymentPageMethods().verifyTotalDueOptions();

  }
}

