package com.spirit.testcasesBAT;



import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;


public class BATTest extends BaseClass{
	

	@Parameters ({"platform"})
	@Test
	public void SampleTest1 (@Optional("NA")String platform) {
		
		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String LOGIN_EMAIL 		= "FSEmail";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "roundtrip";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "LAS";
		final String DEP_DATE 			= "25";
		final String ARR_DATE 			= "30";
		final String ADULTS				= "2";
		final String CHILDS				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";

		//Flight Availability Page Constant Values
		final String DEP_FLIGHT 		= "NonStop";
		final String ARR_Flight 		= "NonStop";
		final String FARE_TYPE			= "Standard";
		final String UPGRADE_FARE		= "BookIt";

		//Bags Page constant values
		final String DEP_BAGS 			= "Carry_1|Checked_3||Carry_0|Checked_1||Carry_1|Checked_2||Carry_0|Checked_5";
		final String RET_BAGS			= "Carry_0|Checked_1||Carry_1|Checked_2||Carry_0|Checked_3||Carry_1|Checked_4";

		//05-Dec-19
		//open browser
		//openBrowser( platform);
		openMobileBrowser(platform);

		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
		pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
		pageMethodManager.getHomePageMethods().selectAirports( DEP_AIRPORT_CODE,  ARR_AIRPORT_CODE);
		pageMethodManager.getHomePageMethods().selectDatesOnCalendar(DEP_DATE, ARR_DATE);
		pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
		pageMethodManager.getHomePageMethods().clickSearchButton();

//		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep",DEP_FLIGHT);
//		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",ARR_Flight);
//		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
//		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);
//
//		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
//		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
//		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//
//		pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
//
//		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
//
//		pageMethodManager.getOptionsPageMethods().selectOptions("CheckInOption:MobileFree");
//		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//
//		pageMethodManager.getPaymentPageMethods().selectTravelGuard("Required");
//		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails("MasterCard");
//		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//		pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//		pageMethodManager.getHomePageMethods().loginToMyTripWithCarnetPNR();
//
//		pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();

		//Flight Availability Methods
//		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
//		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
//		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		//pageMethodManager.getFlightAvailabilityMethods().selectEarlyDeparturePopup();


//		createCheckInPNR();
//		openNativeApp(platform);

//		MobileElement element = mobileObjectManager.getSeatsPage().getSeatAvailabilityBackButton();
//
//		WaitUtil.untilElementIsClickable(getDriver(),element);

		//Passenger Info Methods
//		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
//		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
//		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//
//		//Bags Page Methods
//		pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
//		pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
//		pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
//
//		System.out.println(scenarioContext.getContext(Context.BAGS_DEP_BAGS));
//		System.out.println(scenarioContext.getContext(Context.BAGS_RET_BAGS));
//		//pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
//
//		ValidationUtil.validateTestStep("Salim",false);
//
//		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
//
//
//
//		//pageMethodManager.getSeatsPageMethods().selectDepartureSeats("BigFront|Standard|NoSeat|Premium||Standard|Standard|Standard|Standard");
//
//		//pageMethodManager.getSeatsPageMethods().selectReturningSeats("Standard|Standard|Standard|Standard");
//
//		//pageMethodManager.getSeatsPageMethods().continueWithSeats();
//
//		pageMethodManager.getOptionsPageMethods().selectOptions("CheckInOption:MobileFree");
//		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//
//		pageMethodManager.getPaymentPageMethods().verifyTotalDueBags();
//		pageMethodManager.getPaymentPageMethods().verifyTotalDueBags();
//
		
		
		//pageMethodManager.getPaymentPageMethods().selectTravelGuard("Required");
		//pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails("MasterCard");
		//pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
		/*
		 * 
		 * Validate Test Case
		 * 
		 */

		
		
		
		
	}

	private void createCheckInPNR(){

	}
}
