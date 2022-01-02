package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

// **********************************************************************************************
// TestCase :BoardingPass_CP_Boarding Pass With Email
// Description: Test Case will check the Reprint Boarding pass via email is working fine
// Created By : Kartik Chauhan
// Created On : 19-Mar-2019
// Reviewed By: Salim Ansari
// Reviewed On: 26-Mar-2019
// **********************************************************************************************
public class TC369493 extends BaseClass {

	@Parameters ({"platform"})
	@Test(groups = {"NineDFC","CheckIn","OneWay","DomesticDomestic","WithIn7Days","Adult","Connecting","BookIt", "CarryOn",
			"CheckedBags","Standard","CheckInOptions","TravelInsuranceBlock","Visa","AddEditBags","AddEditSeats","ReservationUI","ShortCutBoarding","BoardingPassUI"})
	public void BoardingPass_CP_Boarding_Pass_With_Email (@Optional("NA")String platform){
		//mention the browser
		if(!platform.equals("NA")){
			ValidationUtil.validateTestStep("Starting Test Case ID TC369493 under BAT suite on " + platform +" Browser", true);
		}
		/******************************************************************************
		 ****************************Navigate to Confirmation Page*****************
		 ******************************************************************************/
		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String LOGIN_EMAIL 		= "NineDFCEmail";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "OneWay";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "LAS";
		final String DEP_DATE 			= "0";
		final String ARR_DATE 			= "NA";
		final String ADULTS				= "1";
		final String CHILDS				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";
		
		//Flight Availability Page Constant Values
		final String SORTING_TYPE		= "Departure";
		final String DEP_FLIGHT			= "Connecting";
		final String FARE_TYPE			= "Member";
		final String UPGRADE_TYPE		= "BookIt";
		
		//Option Page Constant Values
		final String OPTIONS_VALUE		= "CheckInOption:MobileFree";
		
		//Payment page Constant values
//		final String TRAVEL_GUARD		= "Required";
		final String TRAVEL_GUARD		= "NotRequired";
		final String CARD_TYPE			= "VisaCard";
		
		//Confirmation Page Constant value
		final String BOOKING_STATUS 	= "Confirmed";
		
		//Check In Bags Page constant values
		final String CHECKIN_DEP_BAGS 	= "Carry_1|Checked_1";
		final String CHECKIN_SEAT_POPUP	= "Required";
		
		//CheckIn Seat Page
		final String CHECKIN_DEP_SEATS	= "Standard||Standard";

//Step1			
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
//		pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GURAD);
		pageMethodManager.getPaymentPageMethods().fillAnotherCardPaymentDetailsModifyPath(CARD_TYPE);
//		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
		
		//Confirmation Page Methods
		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
		pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
		
		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
				pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));
		
		/*********************************************Start OF CheckIn Path**********************/
		//login to checkIn Path
		pageMethodManager.getHomePageMethods().loginToCheckIn();
		
		//Clicked on Add Bag Link
		pageObjectManager.getReservationSummaryPage().getPassengerSectionAddBagsButton().get(0).click();
		WaitUtil.untilPageLoadComplete(getDriver());
		WaitUtil.untilTimeCompleted(2000);

		//check In Bags Page Method
		pageMethodManager.getBagsPageMethods().selectDepartingBags(CHECKIN_DEP_BAGS);	
		pageMethodManager.getBagsPageMethods().checkInContiueWithBag();
		pageMethodManager.getBagsPageMethods().checkInPurchaseSeatPopup(CHECKIN_SEAT_POPUP);
		
		//Check In Seat Methods
		pageMethodManager.getSeatsPageMethods().selectDepartureSeats(CHECKIN_DEP_SEATS);
		pageMethodManager.getSeatsPageMethods().continueWithSeats();
		
		//Check In Option method
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
		
		//check In Payment Page
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);

		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

		pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

		//wait till page is load is complete
		WaitUtil.untilPageLoadComplete(getDriver());
//Step4	
		//Check-In and Boarding Pass Button is displaying
		ValidationUtil.validateTestStep("Check-In and Boarding Pass Button is displaying",
				TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0)));
				
		//Click on Check In Boarding Pass 
		pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

		//wait till page is load is complete
		WaitUtil.untilPageLoadComplete(getDriver());

		//wait till page is load is complete
		WaitUtil.untilTimeCompleted(2000);
				
		//Create web element for Nope I am Good verbiage
		pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupNopeIAmGoodButton().click();

		//wait till page is load is complete
		WaitUtil.untilPageLoadComplete(getDriver());

		//wait till page is load is complete
		WaitUtil.untilTimeCompleted(2000);
				
		//Check In Option method
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

		pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

		//validate Accept and Print Boarding Pass
		ValidationUtil.validateTestStep("Accept and Print Boarding Pass is selected",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getReservationSummaryPage().xpath_HazardousMaterialPopupAcceptBoardingPassButton)));
				
		pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");

		//print boarding pass
		pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(0).click();

		//email boarding pass
		pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(1).click();
		
		//validate correct Member mail is displaying
		ValidationUtil.validateTestStep("Members Mail id is displaying on Checkin/Extras", 
				JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassTextBox()).trim(),(scenarioContext.getContext(Context.CUSTOMER_EMAIL).toString()));
		
		//create Web Element for finish Check-In button
		pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassButton().click();

		//wait till boarding pass appear
		WaitUtil.untilPageLoadComplete(getDriver());

		//wait till 4 sec
		WaitUtil.untilTimeCompleted(4000);

		//wait till boarding pass appear
		WaitUtil.untilPageLoadComplete(getDriver());

		/*************************Error Code to be Removed from here*****************************/
		/******************************************************************************
		 ***********************Validation to Confirmation Page************************
		 ******************************************************************************/
		//declare constant used in Validation
		final String BOARDING_PASS_URL = "check-in/boarding-pass";

		//close BP print popup
		TestUtil.closeBoardingPassPrintPopup();

		//validate Boarding Pass is appear
		ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
				getDriver().getCurrentUrl(),BOARDING_PASS_URL);
	}
}
	
