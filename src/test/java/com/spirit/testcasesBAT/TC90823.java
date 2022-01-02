package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

// **********************************************************************************************
// TestCase   : Search Widget_CP_BP_ Flight+ Car Multi PAX
// Description: Test Case will verify Flight with Car Booking for 4 pax reach to payment page and check error message and complete the booking
// Created By : Kartik Chauhan
// Created On : 18-Mar-2019
// Reviewed By: Salim Ansdari
// Reviewed On: 26-Mar-2019
// **********************************************************************************************

public class TC90823 extends BaseClass {
	
	@Parameters ({"platform"})
	@Test(groups = {"Guest","BookPath","FlightCar","Adult","DomesticDomestic","Outside21Days","BookIt",
					"CarryOn","NoSeats","CheckInOptions","TravelInsuranceBlock","Discover","PaymentUI"})
	public void Search_Widget_CP_BP_Flight_Car_Multi_PAX (@Optional("NA")String platform){
		
		if(!platform.equals("NA")){
			ValidationUtil.validateTestStep("Starting Test Case ID TC90823 under BAT suite on " + platform +" Browser", true);
		}
		//***************************Navigate to Passenger Info Page******************/
		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String JOURNEY_TYPE 		= "Vacation";
		final String TRIP_TYPE 			= "Flight+Car";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "LAS";
		final String DEP_DATE 			= "25";
		final String ARR_DATE 			= "30";
		final String ADULTS				= "4";
		final String CHILDS				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";
		final String DRIVER_AGE 		= "25+";
		
		//Flight Availability Page Constant Values
		final String FARE_TYPE			= "Standard";
		final String UPGRADE_VALUE		= "BookIt";
		
		//Bags Page constant values
		final String DEP_BAGS 			= "Carry_1|Checked_0||Carry_0|Checked_0||Carry_0|Checked_0||Carry_0|Checked_0";
		
		final String OPTION_VALUE		= "CheckInOption:MobileFree";

		//payment page constant value
		final String TRAVEL_GUARD		= "Required";
		final String CARD_TYPE			= "DiscoverCard1";

//Step1-Step7		
		//open browser 
		openBrowser( platform);
		
		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
	
		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
		pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
		pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
		pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
		pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
		pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
		pageMethodManager.getHomePageMethods().clickSearchButton();
		
		//wait till page is load is complete
		WaitUtil.untilPageLoadComplete(getDriver());
//Step-8		
		//Click on Book Car Button
		pageObjectManager.getCarPage().getCarsPageBookButton().get(0).click();
//		pageObjectManager.getCarPage().getBookCarButton().get(0).click();
		WaitUtil.untilPageLoadComplete(getDriver());
		//verify if activity will display, continue without it
		if(getDriver().getCurrentUrl().contains("activi")) {
			//Select continue without Activity		
			pageObjectManager.getActivityPage().getContinueWithoutActivityButton().click();	
		}
		
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
		
		//wait till page is load is complete
		WaitUtil.untilPageLoadComplete(getDriver());
		
		//Passenger Info Methods
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().click();
		Select driverInfoDropDown = new Select(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown());
		List<WebElement> allOptionList =driverInfoDropDown.getOptions();
		ArrayList<String> allOptionString = new ArrayList<String>();
		for (WebElement element:allOptionList) {
			allOptionString.add(element.getText().trim());
		}
		TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),allOptionString.get(1));
		WaitUtil.untilTimeCompleted(2000);
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//Step-9		
		//Bags Page Methods
		pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
		pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
		
		//Seat Page Methods
		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
		
		//Option Page Methods
		pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
		
		//STep-10
		//Payment Page Methods
		pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
		JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getPaymentPage().getBookTripButton());
		WaitUtil.untilPageLoadComplete(getDriver());
		WaitUtil.untilTimeCompleted(1000);

		//*********************Validation to Confirmation Page*************************/
		//declare constant used in validation
		final String BOOKING_STATUS 	= "Confirmed";
		final String CONFIRMATION_URL 	= "book/confirmation";
		final String TERMS_ERROR 		= "You must agree to the Terms and Conditions in order to complete your reservation.";

		//validate error on Purchase page
		ValidationUtil.validateTestStep("Verify Terms and Condition error message appear on Payment Page",
				pageObjectManager.getCommon().getErrorMessageLabel().getText().trim(),TERMS_ERROR);

		//accept and complete the booking
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

		//Confirmation Page Methods
		//close ROKT pop-up
		try	{
		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
				getDriver().getCurrentUrl(),CONFIRMATION_URL);

		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
				pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

		//Canceling booking
			pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
		pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
		}catch (AssertionError fail){
			pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
			pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
			ValidationUtil.validateTestStep("Test Case failed after Payment: " + fail.getMessage() , false);
		}catch (Exception fail){
			pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
			pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
			ValidationUtil.validateTestStep("Test Case failed after Payment: " + fail.getMessage() , false);
		}

	}
}
	
