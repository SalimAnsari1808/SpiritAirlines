package com.spirit.testcasesBAT;


import com.spirit.baseClass.*;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC280787
//Description: E2E_FS_RT DOM 2 ADT_SW Change PAX to 3 ADT Bundle It [Tier 3] Fare Connecting Flight_MIL_RT 1CO 2CB_1 Free Seat_No Extras_CI Web_Credit Card
//Created By : Salim Ansari
//Created On : 18-Mar-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 19-Mar-2019
//**********************************************************************************************
public class TC280787 extends BaseClass{
	
    @Parameters({"platform"})
	@Test(groups = {"FreeSpirit","BookPath","RoundTrip","DomesticDomestic","Outside21Days","Adult","NewFlightSearch","Connecting","BundleIt",
					"CarryOn","CheckedBags","Military","NoSeats","ShortCutBoarding","CheckInOptions","MasterCard","PassengerInformationUI"})
    public void E2E_FS_RT_DOM_2ADT_Change_3ADT_BundleItFare_Connecting_Flight_MIL_RT_1CO2CB_1FreeSeat_NoExtras_CI_Web_Credit_Card(@Optional("NA") String platform) {

		/******************************************************************************
		 ***********************Navigate to Confirmation Page**************************
		 ******************************************************************************/
		//Mention Suite and Browser in reports 
		if(!platform.equals("NA")) {
			ValidationUtil.validateTestStep("Starting Test Case ID TC280787 under BAT Suite on " + platform + " Browser" , true);
		}
    	
		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String LOGIN_EMAIL 		= "MilitaryFSMiles";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "RoundTrip";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "BWI";
		final String DEP_DATE 			= "25";
		final String ARR_DATE 			= "30";
		final String ADULTS				= "2";
		final String CHILDS				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";
		
		//Flight Availability Page Constant Values
		final String NEW_ADULTS			= "3";
		final String NEW_CHILDS			= "0";
		final String NEW_INFANT_LAP		= "0";
		final String NEW_INFANT_SEAT	= "0";
		final String DEP_FLIGHT 		= "Connecting";
		final String ARR_Flight 		= "Connecting";
		final String FARE_TYPE			= "Standard";
		final String UPGRADE_FARE		= "BundleIt";
		
		//Passenger Info Page Constant value
	    final String BLUE_COLOR2 		= "rgb(0, 115, 230)";
		
		//Bags Page constant values
		final String DEP_BAGS 			= "Carry_1|Checked_2||Carry_1|Checked_2||Carry_1|Checked_2";
		final String RET_BAGS			= "Carry_1|Checked_2||Carry_1|Checked_2||Carry_1|Checked_2";
		
		//Options Page constant values
		final String FREE_CHECKIN 		= "CheckInOption:MobileFree";
		
		//Payment Page Constant values
		final String TRAVEL_GUARD 		= "NotRequired";	
		final String CARD_DETAIL 		= "MasterCard";
	
		//open browser 
		openBrowser( platform);
		
		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
		pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
		pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
		pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
		pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
		pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
		pageMethodManager.getHomePageMethods().clickSearchButton();
		
		//Flight Availability Methods
		pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
		pageMethodManager.getHomePageMethods().selectTravellingPassenger(NEW_ADULTS, NEW_CHILDS, NEW_INFANT_SEAT, NEW_INFANT_LAP);
		pageMethodManager.getHomePageMethods().clickSearchButton();
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);
    	
		//Passenger Info Methods
		pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
		
		 //select Military Bags
	    pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton().click();
	    
	   //wait for 30 sec
	    WaitUtil.untilTimeCompleted(500);
	    //validate Military bags are selected
	    ValidationUtil.validateTestStep("Verify Military Bags are selected on Military and Bundle popup on Passenger info Page",
				JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton(), "background-color"),BLUE_COLOR2);
	  
	    //click on continue button
	    pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupContinueButton().click();
	    
	    //wait until Bags page appear on web
	    WaitUtil.untilPageLoadComplete(getDriver());

		//Bags Page Methods
		pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
		pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
//		pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_FARE);
		pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
		
		//Seats Page Methods
		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
		
		//Options Page Methods
		pageMethodManager.getOptionsPageMethods().selectOptions(FREE_CHECKIN);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
		
		//Purchase Page Methods
		pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
		pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
		
		/******************************************************************************
		 ***********************Validation to Confirmation Page************************
		 ******************************************************************************/
		//declare constant used in validation
		final String BOOKING_STATUS = "Confirmed";
		final String CONFIRMATION_URL = "book/confirmation";

		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    	
    }
}
