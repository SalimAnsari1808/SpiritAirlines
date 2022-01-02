package com.spirit.testcasesBAT;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;

//**********************************************************************************************
//Description: Customer_Info_CP_BP_Try_to_use_already_existing_9DFC_email
//Return: NA
//Created By : Alex Rodriguez
//Created On : 8-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 15-Mar-2019
//**********************************************************************************************

public class TC90914 extends BaseClass{

  @Parameters({"platform"})
  @Test(groups = {"NineDFC","BookPath","Adult","OneWay","DomesticDomestic","Outside21Days","BookIt","PassengerInfoSignIn"})
  public void Customer_Info_CP_BP_Try_to_use_already_existing_9DFC_email(@Optional("NA") String platform) {
	//*********************Navigate to Customer Info Page***************************
  	//Mention Suite and Browser in reports 
  	if(!platform.equals("NA")) {
  		ValidationUtil.validateTestStep("Starting Test Case ID TC90914 under BAT Suite on " + platform + " Browser"   , true);
  	}
	  
    //Home Page Constant Values
    final String LANGUAGE 			= "English";
    final String JOURNEY_TYPE 		= "Flight";
    final String TRIP_TYPE 			= "OneWay";
    final String DEP_AIRPORTS 		= "AllLocation";
    final String DEP_AIRPORT_CODE 	= "FLL";
    final String ARR_AIRPORTS 		= "AllLocation";
    final String ARR_AIRPORT_CODE 	= "MSY";
    final String DEP_DATE 			= "5";
    final String ARR_DATE 		    = "NA";
    final String ADULTS			    = "1";
    final String CHILDS			    = "0";
    final String INFANT_LAP		    = "0";
    final String INFANT_SEAT		= "0";

    //Flight Availability Constant Variables
    final String DEP_FLIGHT 		= "9DFC";
    final String MEMBER_FARE_TYPE 	= "Member";
    final String upgrade_VALUE 		= "BookIt";

    //open browser
    openBrowser( platform);

    //Home Page Methods
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //Flight Availability Methods
      WaitUtil.untilPageLoadComplete(getDriver());
    pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(MEMBER_FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(upgrade_VALUE);

    //Passenger information Page
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();
    
	//*******************Validation on Customer Info Page***************************
    //declare constant used in validation
    final String CREDENTAIL_DATA = "NineDFCEmail";
    
	//get 9dfc username and password
	LoginCredentialsData loginCredentialsDataList = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(CREDENTAIL_DATA);
	
	//Click log in button
	pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInButton().click();
	
	//Inline login email
	pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInUserNameTextBox().sendKeys(loginCredentialsDataList.userName);

	//Input Member Password
	pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInPasswordTextBox().sendKeys(loginCredentialsDataList.password);

	//Click Sign-In Button
	pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInUNPWSubmitLogInTextBox().click();
	
	//wait for login complete
	WaitUtil.untilPageLoadComplete(getDriver());
	
	//verify login successful
	ValidationUtil.validateTestStep("Verify Member Log-In Successful on Passenger Info Page", pageObjectManager.getHomePage().getSignInListLink().size()==0);
  }
}

