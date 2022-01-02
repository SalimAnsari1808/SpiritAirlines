package com.spirit.testcasesBAT;

import com.spirit.utility.WaitUtil;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
//Test Case ID: TC281217
//Description: E2E_MT_9DFC_RT DOM Multi ADT_Booked Outside 7 days_No Flight_+1 Checked_No Seats_SB Extras_Discover
//Created By : Salim Ansari
//Created On : 20-Mar-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 20-Mar-2019
//**********************************************************************************************
public class TC281217 extends BaseClass{
	
	  @Parameters ({"platform"})
	  @Test(groups = {"NineDFC","MyTrips","RoundTrip","DomesticDomestic","Outside21Days","Adult","Connecting","BookIt",
			  "CheckedBags","NoSeats","CheckInOptions","MasterCard","AddEditBags"})
	  public void E2E_MT_9DFC_RT_DOM_Multi_ADT_Booked_Outside_7_days_No_Flight_1Checked_No_Seats_SB_Extras_Discover (@Optional("NA")String platform) {
		/******************************************************************************
		 ***********************Navigate to Confirmation Page**************************
		 ******************************************************************************/
		//Mention Suite and Browser in reports 
		if(!platform.equals("NA")) {
			ValidationUtil.validateTestStep("Starting Test Case ID TC281217 under BAT Suite on " + platform + " Browser"   , true);
		}
		
		//Home Page Constant Values
		final String LANGUAGE 				= "English";
		final String LOGIN_EMAIL 			= "NineDFCEmail";
		final String JOURNEY_TYPE 			= "Flight";
		final String TRIP_TYPE 				= "RoundTrip";
		final String DEP_AIRPORTS 			= "AllLocation";
		final String DEP_AIRPORT_CODE 		= "FLL";
		final String ARR_AIRPORTS 			= "AllLocation";
		final String ARR_AIRPORT_CODE 		= "LAS";
		final String DEP_DATE 				= "25";
		final String ARR_DATE 				= "30";
		final String ADULTS					= "2";
		final String CHILDS					= "0";
		final String INFANT_LAP				= "0";
		final String INFANT_SEAT			= "0";
		
		//Flight Availability Page Constant Values
		final String DEP_FLIGHT 			= "Connecting";
		final String ARR_Flight 			= "Connecting";
		final String FARE_TYPE				= "Member";
		final String UPGRADE_FARE			= "BookIt";
		
		//Options Page constant values
		final String FREE_CHECKIN 			= "CheckInOption:MobileFree";
		
		//Payment Page Constant values
		final String TRAVEL_GUARD 			= "NotRequired";
		final String CARD_DETAIL 			= "MasterCard";

		  //Check In Bags Page constant values
		  final String CHECKIN_DEP_BAGS 	= "Carry_0|Checked_1||Carry_0|Checked_1";
		  final String CHECKIN_RET_BAGS 	= "Carry_0|Checked_1||Carry_0|Checked_1";
		  final String CHECKIN_SEAT_POPUP	= "NotRequired";
		
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
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);
    	
		//Passenger Info Methods
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();

		//Bags Page Methods
		pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
		
		//Seats Page Methods
		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
		
		//Options Page Methods
		pageMethodManager.getOptionsPageMethods().selectOptions(FREE_CHECKIN);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
		
		//Puchase Page Methods
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
		pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
		
		//confirmation page methods
		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
		pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

		//login to My Trip
		pageMethodManager.getHomePageMethods().loginToMyTrip();

		//Clicked on Add Bag Link
		pageObjectManager.getReservationSummaryPage().getPassengerSectionAddBagsButton().get(0).click();

		//My Trip Bags Page Method
		pageMethodManager.getBagsPageMethods().selectDepartingBags(CHECKIN_DEP_BAGS);
		pageMethodManager.getBagsPageMethods().selectReturnBags(CHECKIN_RET_BAGS);
		pageMethodManager.getBagsPageMethods().checkInContiueWithBag();
		pageMethodManager.getBagsPageMethods().checkInPurchaseSeatPopup(CHECKIN_SEAT_POPUP);

		//My Trip Option method
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

		//check In Payment Page
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
		pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

		//wait till page is load is complete
		WaitUtil.untilPageLoadComplete(getDriver());

		/*****************************************************************************
		***********************Validation on ConfirmationPage*************************
		******************************************************************************/
		//declare constant used in validation
		final String CONFIRMATION_URL		= "my-trips/reservation-summary";

		//verify user reached to confirmation page
		  ValidationUtil.validateTestStep("Verify user reached to Confirmation Page of Manage Travel Path", getDriver().getCurrentUrl().contains(CONFIRMATION_URL));


		
   }

}
