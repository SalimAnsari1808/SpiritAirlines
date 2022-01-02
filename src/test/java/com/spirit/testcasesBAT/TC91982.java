package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


// **********************************************************************************************
//Test Case ID: TC91982
// Description: Uplift_CP_BP_Flight_Only_Guest_Standard_Fare
// Created By : Kartik Chauhan
// Created On : 14-Mar-2019
// Reviewed By: Salim Ansari
// Reviewed On: 15-Mar-2019
// **********************************************************************************************
public class TC91982 extends BaseClass {
	@Parameters ({"platform"})
	@Test(groups = {"Guest","BookPath","RoundTrip","DomesticDomestic","Within21Days","Adult","NonStop","BookIt","OptionalUI",
			"CarryOn","ShortCutBoarding","NoSeats","CheckInOptions","TravelInsuranceBlock","MasterCard"})
	public void Uplift_CP_BP_Flight_Only_Guest_Standard_Fare (@Optional("NA")String platform){

		//*********************Navigate to FLight Availability Page*****************************/
    	//Mention Suite and Browser in reports 
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID TC91982 under BAT Suite on " + platform + " Browser"   , true);
    	}
    	
		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "RoundTrip";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "LAS";
		final String DEP_DATE 			= "20";
		final String ARR_DATE 			= "22";
		final String ADULT				= "1";
		final String CHILD				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";
	
		//Flight Availability Page Constant Values
		final String DEP_FLIGHT 		= "nonstop";
		final String ARR_Flight 		= "nonstop";
		final String UPGRADE_VALUE		= "BookIt";
		final String FARE_TYPE			= "Standard";
		
		//Bags Page constant values
		final String DEP_BAGS 			= "Carry_1|Checked_0";

		//Option page Constant Values
		final String OPTION_VALUE 		= "CheckInOption:MobileFree";

		//Payment page constant values
		final String TRAVEL_GUARD 		= "Required";
		final String PAYMENT_CARD 		= "MasterCard";
		
//STEP--1,2,3
		//open browser 
		openBrowser( platform);
		
		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
		pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
		pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
		pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
		pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
		pageMethodManager.getHomePageMethods().clickSearchButton();

		//Flight Availability Methods
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
//STEP--4,5
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--6,7	(didn't get any uplift on Passenger info page)
		//Passenger Info Methods
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//STEP--8
		//Bags Page Methods
		pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
		pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
//STEP--9
		//Seat Page Methods
		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);
		//****************Validation to Option Page*************************************/
		//declare constant used in validation
		final String BOOKING_STATUS 	= "Confirmed";
		final String CONFIRMATION_URL 	= "book/confirmation";
		double itineraryTotalAmount = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
		int daysOut	= Integer.parseInt(ARR_DATE) - Integer.parseInt(DEP_DATE);

//STEP--10,11
		//****************************************CAR SECTION*****************************************
		for (int i = 0; i < pageObjectManager.getCarPage().getCarsPanel().size(); i ++)
		{
			double carPrice = Double.parseDouble(pageObjectManager.getCarPage().getCarCardPriceLink().get(i).getText().replace("$",""));

			if (((carPrice*daysOut) + itineraryTotalAmount) >= 300)
			{
				ValidationUtil.validateTestStep("Validating Car uplift options is available whn booking is above $300",
						TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(i)));

				pageObjectManager.getCarPage().getCarCardNameText().get(i).click();
				WaitUtil.untilPageLoadComplete(getDriver());
				WaitUtil.untilTimeCompleted(1000);

				ValidationUtil.validateTestStep("Validating uplift is displayed under car description window",
						TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpUpliftPricingText()));

				pageObjectManager.getCarPage().getCarPopUpExitIconButton().click();
				WaitUtil.untilTimeCompleted(1200);
			}
		}
//		if(TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText())){
//			for(int upliftcounter=0;upliftcounter<pageObjectManager.getCarPage().getCarCardUpliftPricingText().size();upliftcounter++){
//				if(TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(upliftcounter))){
//					//Validate Car Uplift Tag is displaying on Option page
//					ValidationUtil.validateTestStep("Kartik...Car Uplift Tag is displaying on Option page when booking is above $300", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(upliftcounter)));
//
//					//click on card view link
//					//JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getCarcardViewLink().get(upliftcounter));
//					try{
//						pageObjectManager.getCarPage().getCarcardViewLink().get(upliftcounter).click();
//					}catch (Exception e){
//
//					}
//
//					JSExecuteUtil.scrollDown(getDriver(),"100");
//
//					WaitUtil.untilTimeCompleted(2000);
//
//
//					WaitUtil.untilTimeCompleted(2000);
//
//					//break loop
//					break;
//				}
//			}
//
//			//Put the wait till page load completely
//			WaitUtil.untilPageLoadComplete(getDriver());
//
//			//put wait for 2 sec
//			WaitUtil.untilTimeCompleted(2000);
//
//			//Validate Car Uplift Tag on Car View Pop-Up
//			ValidationUtil.validateTestStep("Car Uplift Tag on Car View Pop-Up is displaying on Option page when booking is above $300",
//					TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_CarPopUpUpliftPricingText)));
//
//			//click on card view link
//			//JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getCarPopUpExitIconButton());
//			pageObjectManager.getCarPage().getCarPopUpExitIconButton().click();
//
//			//Put the wait till page load completely
//			WaitUtil.untilPageLoadComplete(getDriver());
//		}


//STEP--12
		//****************************************HOTEL SECTION*****************************************
		for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i ++)
		{
			double hotelPrice = Double.parseDouble(pageObjectManager.getHotelPage().getHotelCardPriceLink().get(i).getText().replace("$",""));


			if (((hotelPrice*daysOut) + itineraryTotalAmount) >= 300)
			{
				ValidationUtil.validateTestStep("Validating Hotel uplift options is available when booking is above $300",
						TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().get(i)));

				pageObjectManager.getHotelPage().getHotelCardNameText().get(i).click();
				WaitUtil.untilPageLoadComplete(getDriver());
				WaitUtil.untilTimeCompleted(1000);

				ValidationUtil.validateTestStep("Validating uplift is displayed under car description window",
						TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpUpliftPricingText()));

				pageObjectManager.getHotelPage().getHotelPopUpExitIconLink().click();
				WaitUtil.untilTimeCompleted(1200);
			}
		}

//		if(TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText())){
//			for(int upliftcounter=0;upliftcounter<pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().size();upliftcounter++){
//				if(TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().get(upliftcounter))){
//					//Validate Car Uplift Tag is displaying on Option page
//					ValidationUtil.validateTestStep("Hotel Uplift Tag is displaying on Option page when booking is above $300",
//							TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().get(upliftcounter)));
//
//					try{
//						//click on card view link
//						pageObjectManager.getHotelPage().getHotelCardViewLink().get(upliftcounter).click();
//					}catch (Exception e){
//
//					}
//
//					//put wait for 2 sec
//					WaitUtil.untilTimeCompleted(2000);
//
//					//break loop
//					break;
//				}
//			}
//
//			//Put the wait till page load completely
//			WaitUtil.untilPageLoadComplete(getDriver());
//
//			//put wait for 2 sec
//			WaitUtil.untilTimeCompleted(2000);
//
//			//Validate Hotel Uplift Tag on Hotel View Pop-Up
//			ValidationUtil.validateTestStep("Hotel Uplift Tag on Hotel View Pop-Up is displaying on Option page when booking is above $300",
//					TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHotelPage().xpath_HotelPopUpUpliftPricingText)));
//
//			pageObjectManager.getHotelPage().getHotelPopUpExitIconLink().click();
//
//			//Put the wait till page load completely
//			WaitUtil.untilPageLoadComplete(getDriver());
//		}

////STEP--13
		//****************************************ACTIVITY SECTION*****************************************
//		if(TestUtil.verifyElementDisplayed(pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText())){
//			for(int upliftcounter=0;upliftcounter<pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText().size();upliftcounter++){
//				if(TestUtil.verifyElementDisplayed(pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText().get(upliftcounter))){
//					//Validate Car Uplift Tag is displaying on Option page
//					ValidationUtil.validateTestStep("Activity Uplift Tag is displaying on Option page when booking is above $300",
//							TestUtil.verifyElementDisplayed(pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText().get(upliftcounter)));
//
//					try{
//						//click on card view link
//						pageObjectManager.getActivityPage().getActivitiesCardViewLink().get(upliftcounter).click();
//					}catch (Exception e){
//
//					}
//
//					//put wait for 2 sec
//					WaitUtil.untilTimeCompleted(2000);
//
//					//break loop
//					break;
//				}
//			}
//
//			//Put the wait till page load completely
//			WaitUtil.untilPageLoadComplete(getDriver());
//
//			//put wait for 2 sec
//			WaitUtil.untilTimeCompleted(2000);
//
//			//Validate Activity Uplift Tag on Activity View Pop-Up
//			ValidationUtil.validateTestStep("Activity Uplift Tag on Activity View Pop-Up is displaying on Option page when booking is above $300",
//					TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getActivityPage().xpath_ActivityPopUpUpliftPricingText)));
//
//			pageObjectManager.getActivityPage().getActivityPopUpExitIconButton().click();
//
//			//Put the wait till page load completely
//			WaitUtil.untilPageLoadComplete(getDriver());
//		}


//STEP--14
		//Option Page Methods
		pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//STEP--15
		//Payment Page Methods
		//Put the wait till page load completely
		WaitUtil.untilPageLoadComplete(getDriver());

		//Validate Hotel Uplift Tag on Hotel View Pop-Up //TODO: Invalid Step
//		ValidationUtil.validateTestStep("Pay Monthly From section is displaying on Option page when booking is above $300",
////				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getPaymentPage().xpath_TotalDuePayMonthlyText)));
//				TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDuePayMonthlyText()));

//STEP--16
		pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(PAYMENT_CARD);
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

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
