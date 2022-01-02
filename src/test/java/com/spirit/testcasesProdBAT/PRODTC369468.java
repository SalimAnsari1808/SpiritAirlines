package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC369468
//Description: E2E_Guest_RT_DOM_1_ADT_Thru_Flight_Standard_No_Bags_No_Seats_No_Extras_CI_Web_Visa
//Created By : Salim Ansari
//Created On : 16-Mar-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 17-Mar-2019
//**********************************************************************************************
public class PRODTC369468 extends BaseClass {
	@Parameters ({"platform"})
	@Test(groups="Production")

	public void E2E_Guest_RT_DOM_1_ADT_Thru_Flight_Standard_No_Bags_No_Seats_No_Extras_CI_Web_Visa (@Optional("NA")String platform){
		/******************************************************************************
		 ***********************Navigate to Confirmation Page**************************
		 ******************************************************************************/
    	//Mention Suite and Browser in reports 
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID PROTC369468 under PRODUCTION Suite on " + platform + " Browser"   , true);
    	}
    	
		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "RoundTrip";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "LAS";
		final String DEP_DATE 			= "25";
		final String ARR_DATE 			= "30";
		final String ADULTS				= "1";
		final String CHILDS				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";
		
		//Flight Availability Page Constant Values
		final String DEP_FLIGHT 		= "Through";
		final String ARR_Flight 		= "Through";
		final String FARE_TYPE			= "Standard";
		final String UPGRADE_VALUE		= "BookIt";

		//option page constant value
		final String SELECTED_OPTION	= "CheckInOption:MobileFree";
		
		//Purchase Page Constant Values
		final String TRAVEL_GURAD 		= "NotRequired";
		final String CARD_TYPE 			= "VisaCard";		
		
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
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		//pageMethodManager.getFlightAvailabilityMethods().selectEarlyDeparturePopup();
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
		
		//Passenger Info Methods
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
		
		//Bags Page Methods
		pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
		
		//Seats Page Methods
		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
		
		//Option Page Methods
		pageMethodManager.getOptionsPageMethods().selectOptions(SELECTED_OPTION);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
		
		//Purchase Page Methods
		pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GURAD);
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//		//Confirmation Page Methods
//		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//		/******************************************************************************
//		 ***********************Validation to Confirmation Page************************
//		 ******************************************************************************/
//		//declare constant used in validation
//		final String BOOKING_STATUS 	= "Confirmed";
//		final String CONFIRMATION_URL 	= "book/confirmation";
//
//		//verify booking is confirmed
//		ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
//				getDriver().getCurrentUrl(),CONFIRMATION_URL);
//
//		//verify booking is confirmed
//		ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//				pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
	}
}
