package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
//TODO: [IN:24308] CP: CI: Options Page: Travel Guard CTA Pops Up when Travel Guard already purchased through web
/** test case passed, removed active bug tag**/

// TestCase :BoardingPass_CP_CI_Reprint boarding pass
// Description: Test Case will check the Reprint Boarding Pass
// Created By : Kartik Chauhan
// Created On : 20-Mar-2019
// Reviewed By: Salim Ansari
// Reviewed On: 27-Mar-2019
// **********************************************************************************************
public class TC90940 extends BaseClass {

	@Parameters ({"platform"})
	@Test(groups = {"Guest","CheckIn","OneWay","Adult","DomesticDomestic","WithIn7Days","BookIt","NonStop",
					"NoBags","NoSeats","CheckInOptions","MasterCard","TravelInsuranceBlock","ReservationUI"})
	public void BoardingPass_CP_CI_Reprint_boarding_pass (@Optional("NA")String platform){
		
		if(!platform.equals("NA")){
			ValidationUtil.validateTestStep("Starting Test Case ID TC90940 under BAT suite on " + platform +" Browser", true);
		}
		//***************************Navigate to Confirmation Page*****************
		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "OneWay";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "LAS";
		final String DEP_DATE 			= "0";
		final String ARR_DATE 			= "NA";
		final String ADULTS				= "4";
		final String CHILDS				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";
		
		//Flight Availability Page Constant Values
		final String SORTING_TYPE		= "Departure";
		final String DEP_FLIGHT			= "NonStop";
		final String FARE_TYPE			= "Standard";
		final String UPGRADE_TYPE		= "BookIt";
		
		//Option Page Constant Values
		final String OPTIONS_VALUE		= "CheckInOption:MobileFree";
		
		//Payment page Constant values
		final String TRAVEL_GUARD		= "NotRequired";
		final String CARD_TYPE			= "MasterCard";
		
		//Confirmation Page Constant value
		final String BOOKING_STATUS 	= "Confirmed";
		final String CONFIRMATION_URL 	= "confirmation";

//Step1			
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
		pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORTING_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);
		
		//Passenger Info Methods
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
	
		//Bags Page Methods
		pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
		
		//Seat Methods
		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
		
		//Option method
		pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
		
		//Payment Page Method
//		pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
		pageMethodManager.getPaymentPageMethods().fillAnotherCardPaymentDetailsModifyPath(CARD_TYPE);
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
		
		//Confirmation Page Methods
		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
		pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
		
		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
				getDriver().getCurrentUrl(),CONFIRMATION_URL);
		
		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
				pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
		
		//*********************************************Start OF CheckIn Path**********************/
		//login to checkIn Path
		pageMethodManager.getHomePageMethods().loginToCheckIn();
		
		//wait till page is load is complete
		WaitUtil.untilPageLoadComplete(getDriver());
		
		//Click on Check In Boarding Pass 
		pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
		
//		//wait till boarding pass appear
//		WaitUtil.untilPageLoadComplete(getDriver());
//
//		//wait till 2 sec
//		WaitUtil.untilTimeCompleted(2000);
//
//		//Create web element for Nope I am Good verbiage
//		pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupNopeIAmGoodButton().click();
//
//		//wait till 2 sec
//		WaitUtil.untilTimeCompleted(2000);
//
//		//get random Seats
//		pageObjectManager.getReservationSummaryPage().getChooseYourSeatGetRandomSeatButton().click();
//
//		//wait till 2 sec
//		WaitUtil.untilTimeCompleted(2000);
//
//		//wait till page is load is complete
//		WaitUtil.untilPageLoadComplete(getDriver());

		pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup("DontPurchase");
		pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup("DontPurchase");
		//Check In Option method
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

		pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//TODO: [IN:24308] CP: CI: Options Page: Travel Guard CTA Pops Up when Travel Guard already purchased through web
		//validate Accept and Print Boarding Pass
		ValidationUtil.validateTestStep("Accept and Print Boarding Pass is selected",
				TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getReservationSummaryPage().xpath_HazardousMaterialPopupAcceptBoardingPassButton)));
				
		//Make a click on Accept and Print Boarding Pass
		pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");
		WaitUtil.untilTimeCompleted(2000);

		//print boarding pass
		pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(0).click();
		
		//validate correct Member mail is displaying
		ValidationUtil.validateTestStep("Members Mail id is displaying on Checkin/Extras", 
				JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassTextBox()).trim().equals(scenarioContext.getContext(Context.CUSTOMER_EMAIL).toString()));
		
		//create Web Element for finish Check-In button
		pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassButton().click();
		WaitUtil.untilPageLoadComplete(getDriver());
		WaitUtil.untilTimeCompleted(4000);

		TestUtil.closeBoardingPassPrintPopup();

		//**********************Validation to Confirmation Page************************
		//declare constant used in Validation
		final String BOARDING_PASS_URL = "check-in/boarding-pass";
		
		//validate Boarding Pass is appear
		ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page", 
				getDriver().getCurrentUrl(),BOARDING_PASS_URL);
	}
}
	
