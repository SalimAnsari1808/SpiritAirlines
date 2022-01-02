package com.spirit.testcasesBAT;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

//**********************************************************************************************
//Test Case ID: TC377294
//Description: E2E_9DFC_OW_DOM_Multi_ADT_Bundle_Connecting_STD_1_CO_Any_Free_NoExt_CI_Web_TG_Credit_Card
//Created By : Salim Ansari
//Created On : 19-Mar-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 20-Mar-2019
//**********************************************************************************************
public class TC377294 extends BaseClass{
	
	  @Parameters ({"platform"})
	  @Test(groups = {"BookPath","OneWay","DomesticDomestic","Outside21Days","Adult","NineDFC","Connecting", "BundleIt","CarryOn",
			  "CheckedBags","Standard","FlightFlex","ShortCutBoarding","MasterCard","TravelInsurancePopUp","CheckInOptions",
			  "PassengerInformationUI","BagsUI","SeatsUI","OptionalUI","PaymentUI","DynamicShoppingCartUI"})
	  public void E2E_9DFC_OW_DOM_Multi_ADT_Bundle_Connecting_STD_1_CO_Any_Free_NoExt_CI_Web_TG_Credit_Card(@Optional("NA")String platform) {
		  
		/*****************************************************************************
		***********************Navigate to Confirmation Page**************************
		******************************************************************************/
		//Mention Suite and Browser in reports 
		if(!platform.equals("NA")) {
			ValidationUtil.validateTestStep("Starting Test Case ID TC377294 under BAT Suite on " + platform + " Browser"   , true);
		}
    	
		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String LOGIN_EMAIL 		= "NineDFCEmail";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "OneWay";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "BWI";
		final String DEP_DATE 			= "25";
		final String ARR_DATE 			= "NA";
		final String ADULTS				= "3";
		final String CHILDS				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";
		
		//Flight Availability Page Constant Values
		final String DEP_FLIGHT 		= "Connecting";
		final String FARE_TYPE			= "Standard";
		final String UPGRADE_FARE		= "BundleIt";
		
		//Seats Page constant values
		final String DEP_SEATS			= "Standard|Standard|Standard||Standard|Standard|Standard";
		
		//Option Page constant value
		final String FREE_CHECKIN 		= "CheckInOption:MobileFree";
		
		//Payment page constant value
		final String TRAVEL_GUARD 		= "Required";
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
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);
		
		//Passenger Info Methods
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_FARE);
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
		
		/***********************************************************************************/
		/************Start OF Check Carry Bag is PreSelected with Bundle Fare***************/
		/***********************************************************************************/

		pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_FARE);

		//select fare type on Bags page
		pageMethodManager.getBagsPageMethods().continueWithSelectingBags();
		
		//Seats Page Methods
		pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
		pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_FARE,DEP_SEATS,"NA");
		pageMethodManager.getSeatsPageMethods().continueWithSeats();
		
		//Options Page Method
		pageMethodManager.getOptionsPageMethods().selectOptions(FREE_CHECKIN);
		pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_FARE);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
		
		//Puchase Page Methods
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
		pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADE_FARE);
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
		pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
		/******************************************************************************
		 ***********************Validation to Confirmation Page************************
		 ******************************************************************************/
		//declare constant used in validation
		final String BOOKING_STATUS = "Confirmed";
		final String CONFIRMATION_URL = "book/confirmation";
		
		//confirmation page Methods
		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
		
		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
				getDriver().getCurrentUrl(),CONFIRMATION_URL);
		
		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
				pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
	  }

}
