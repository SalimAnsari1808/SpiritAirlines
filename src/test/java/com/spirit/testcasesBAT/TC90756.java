package com.spirit.testcasesBAT;

import com.spirit.utility.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.spirit.baseClass.BaseClass;

//**********************************************************************************************
//Test Case ID: TC90756
//Description: Customer_Info_CP_BP_Multi_Military_members_in_the_booking
//Created By : Alex Rodriguez
//Created On : 15-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 15-Mar-2019
//**********************************************************************************************
public class TC90756 extends BaseClass{

  @Parameters ({"platform"})
  @Test(groups = {"BookPath","OneWay","Adult","DomesticDomestic","WithIn7Days","Guest","Connecting","Military","BundleIt",
                  "PassengerInformationUI"})
  public void Customer_Info_CP_BP_Multi_Military_members_in_the_booking (@Optional("NA")String platform) {
	/******************************************************************************
	 ***********************Navigate to Customer Info Page*************************
	 ******************************************************************************/
	//Mention Suite and Browser in reports 
	if(!platform.equals("NA")) {
		ValidationUtil.validateTestStep("Starting Test Case ID TC90756 under BAT Suite on " + platform + " Browser"   , true);
	}
		
    //Home Page Constant Values
    final String LANGUAGE 			= "English";
    final String JOURNEY_TYPE 		= "Flight";
    final String TRIP_TYPE 			= "OneWay";
    final String DEP_AIRPORTS 		= "AllLocation";
    final String DEP_AIRPORT_CODE 	= "FLL";
    final String ARR_AIRPORTS 		= "AllLocation";
    final String ARR_AIRPORT_CODE 	= "ATL";
    final String DEP_DATE 			= "3";
    final String NO_DATE 			= "NA";
    final String ADULTS				= "3";
    final String CHILD  			= "0";
    final String INFANT_LAP 		= "0";
    final String INFANT_SEAT 		= "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT 		= "NonStop";
    final String FARE_TYPE 			= "Standard";
    final String JOURNEY_UPGRADE	= "BundleIt";

    //open browser
    openBrowser( platform);

    //Home Page Methods
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, NO_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //Flight Availability Page Methods
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(JOURNEY_UPGRADE);
    
    //Passenger Info Methods
    pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
	/******************************************************************************
	 ***********************Navigate to Customer Info Page*************************
	 ******************************************************************************/
    //declare constant used in validation
    final String BLUE_COLOR2  = "rgb(0, 115, 230)";
    final String BAGS_URL     = "book/bags";

    //continue to bags page
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //wait for 2 sec
    WaitUtil.untilTimeCompleted(2000);

    //select Military Bags
    pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton().click();
    
   //wait for 30 sec
    WaitUtil.untilTimeCompleted(500);
    
    //validate Military bags are selected
    ValidationUtil.validateTestStep("Verify Military Bags are selected on Military and Bundle popup on Passenger info Page",
            JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton(), "background-color"),BLUE_COLOR2);
  
    //click on continue button
    pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupContinueButton().click();
    
    WaitUtil.untilPageLoadComplete(getDriver());
    
  //wait till url appear on web
    WaitUtil.untilPageURLVisible(getDriver(), BAGS_URL);

    //wait until page bags is appear on web
    WaitUtil.untilPageLoadComplete(getDriver());
    
    //validate user reached to the Bags page
    ValidationUtil.validateTestStep("User after Military Bags on Passenger Info proceeds to Bags Page", getDriver().getCurrentUrl(),BAGS_URL);

  }
}
