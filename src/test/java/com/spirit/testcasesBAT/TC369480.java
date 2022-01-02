package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC369480
//Description: E2E_Guest_RT_INT_1_ADT_Thru_Flight_Military_Bags_No_Seats_Flight_Flex_CI_Web_AMEX
//Created By : Salim Ansari
//Created On : 19-Mar-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 20-Mar-2019
//**********************************************************************************************
public class TC369480 extends BaseClass{
	
	  @Parameters ({"platform"})
	  @Test(groups = {"BookPath","OneWay","DomesticInternational","Outside21Days","Adult","Through","BookIt","Military",
			  "BagsUI","CarryOn","CheckedBags","NoSeats","FlightFlex","ShortCutBoarding","CheckInOptions","AmericanExpress"})
	  public void E2E_Guest_RT_INT_1_ADT_Thru_Flight_Military_Bags_No_Seats_Flight_Flex_CI_Web_AMEX(@Optional("NA")String platform) {
		  
		//**********************Navigate to Confirmation Page**************************
		//Mention Suite and Browser in reports 
		if(!platform.equals("NA")) {
			ValidationUtil.validateTestStep("Starting Test Case ID TC369480 under BAT Suite on " + platform + " Browser" , true);
		}
		
	  	//declare variable used in navigation
	  	boolean statusFalg 				= true;
	  	
	    //Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "RoundTrip";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "CUN";
		final String DEP_DATE 			= "25";
		final String ARR_DATE 			= "30";
		final String ADULTS				= "1";
		final String CHILDS				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";
		
		//Flight Availability Page Constant Values
		final String DEP_FLIGHT 		= "Nonstop";
		final String RET_FLIGHT 		= "Nonstop";
		final String FARE_TYPE			= "Standard";
		final String UPGRADE_FARE		= "BookIt";
		
		//Bags Page constant values
		final String DEP_BAGS 			= "Carry_1|Checked_2";
		final String ZERO_BAG_PRICE 	= "$0.00";
		
		//Option Page constant value
		final String FREE_CHECKIN 		= "FlightFlex|CheckInOption:MobileFree";
		
		//Payment page constant value
		final String CARD_DETAIL 		= "AmericanExpressCard";
		final String TRAVEL_GUARD 		= "NotRequired";	
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
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("ret", RET_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);
		
		//Passenger Info Methods
		pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
		
		//select fare type on Bags page
		pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
		
		//********Start OF 1-Carry-On and 2-Checked Bags are Free For Military***************/
		//set flag to true for departing carry-on bags
		statusFalg = true;
		
		//loop through all departing carry-on bags
		for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().size();bagsCounter++) {
			//check carry-on Bag price for all passenger
			if(!pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
				//set flag to false
				statusFalg = false;
			}
		}
		
		//Verify Departing Carry-On Bag prices
		ValidationUtil.validateTestStep("Verify Departing Carry-On Bag prices is zero on Bags Page", statusFalg);

		//set flag to true for departing checked bags
		statusFalg = true;
		
		//loop through all departing checked bags
		for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().size();bagsCounter++) {
			//check checked bag bag prices for all passenger
			if(!pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
				//set flag to false
				statusFalg = false;	
			}
		}

		//Verify Departing Checked Bag prices
		ValidationUtil.validateTestStep("Verify Departing Checked Bag prices is zero on Bags Page", statusFalg);
		
		//********End of 1-Carry-On and 2-Checked Bags are Free For Military***************/
		
		pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
		
		//Seats Page Methods
		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

		//Options Page Methods
		pageMethodManager.getOptionsPageMethods().selectOptions(FREE_CHECKIN);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
		
		//Puchase Page Methods
		pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
		pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
		
		//***********************Validation to Confirmation Page************************
		//declare constant used in validation
		final String BOOKING_STATUS 	= "Confirmed";
		final String CONFIRMATION_URL 	= "book/confirmation";
		
		//confirmation page Methods
		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
		
		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);
		
		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
				pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
	  
	  }
}
