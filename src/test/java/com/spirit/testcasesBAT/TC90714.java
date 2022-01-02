package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

//**********************************************************************************************
//Test Case ID: TC90714
//Description: ADD BAG(S) WITH PLUS (+) OR MINUS (-)
//Created By : Salim Ansari
//Created On : 11-Mar-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 14-Mar-2019
//**********************************************************************************************

public class TC90714 extends BaseClass{

	@Parameters ({"platform"})
	@Test(groups = {"Guest","BookPath","RoundTrip","Adult","Child","DomesticDomestic","Outside21Days","Connecting","BookIt", "BagsUI"})
	public void ADD_BAG_WITH_PLUS_OR_MINUS (@Optional("NA")String platform) {
		/******************************************************************************
		 ****************************Navigate to Bags Page*****************************
		 ******************************************************************************/
		//Mention Suite and Browser in reports 
		if(!platform.equals("NA")) {
			ValidationUtil.validateTestStep("Starting Test Case ID TC90714 under BAT Suite on " + platform + " Browser"   , true);
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
		final String ADULTS				= "2";
		final String CHILDS				= "1";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";
		
		//Flight Availability Page Constant Values
		final String DEP_FLIGHT 		= "Connecting";
		final String ARR_Flight 		= "Connecting";
		final String FARE_TYPE			= "Standard";
		final String UPGRADE_VALUE		= "BookIt";
		
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
		pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
		
		//Flight Availability Methods
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
		
		//Passenger Info Methods
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
		
		/******************************************************************************
		 ****************************Validation on Bags Page***************************
		 ******************************************************************************/
		//declare constant used in validation
		final String BLUE_COLOR 	= "rgb(0, 115, 230)";
		final String GRAY_COLOR 	= "rgb(204, 204, 204)";
		final int NO_CARRY_BAG 		= 0;
		final int ONE_CARRY_BAG 	= 1;
		final int NO_CHECKED_BAG 	= 0;
		final int ONE_CHECKED_BAG 	= 1;
		final int TWO_CHECKED_BAG 	= 2;
		final int THREE_CHECKED_BAG = 3;
		final int FOUR_CHECKED_BAG 	= 4;
		final int FIVE_CHECKED_BAG 	= 5;
		final String DOLLOR_SIGN	= "\\$";
		
		//declare variable used in validation
		String signColor;
		int carryBagCounter;
		int checkedBagCounter;
		double preChecBagPrice;
		double crrChecBagPrice;
		
		for(int passengerCounter=0;passengerCounter<=2;passengerCounter++) {
			
			//Verify carry-on bag minus link color before selection is Gray
			ValidationUtil.validateTestStep("*************Verify Bags Details for passenger " + (passengerCounter+1) + "*************", true);
			//*******************************************Departing Carry-On Bag*****************************************************
			
			//********************************************
			//**Carry-On Bag when no bag is selected******
			//********************************************
			//get carry-on bag minus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnMinusButton().get(passengerCounter), "color");
		
			//Verify carry-on bag minus link color before selection is Gray
			ValidationUtil.validateTestStep("Verify Departing carry-on bag minus link color before selection is Gray", signColor.equals(GRAY_COLOR));
			
			//get carry-on bag plus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnPlusButton().get(passengerCounter), "color");

			//verify carry-on bag minus link color before selection is Gray
			ValidationUtil.validateTestStep("Verify Departing carry-on bag plus link color before selection is Blue", signColor.equals(BLUE_COLOR));
			
			//verify add Carry-on for $xx is displaying on screen
			ValidationUtil.validateTestStep("Verify \\\"Add a Carry-On Bag for $xx\\\" is displaying on screen for Departing Bags", pageObjectManager.getBagsPage().getDepartingNextCarryOnPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);
			
			//get the carry-on bag cont before selection
			carryBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(passengerCounter)));
			
			//verify carry-on bag count before selection is 0
			ValidationUtil.validateTestStep("Verify Departing carry-on bag count before selection is 0", carryBagCounter==NO_CARRY_BAG);
			
			//*******************************************
			//**Carry-On Bag after selecting one bag*****
			//*******************************************
			//click on carry-on bag plus link
			pageObjectManager.getBagsPage().getDepartingCarryOnPlusButton().get(passengerCounter).click();
			
			//get carry-on bag minus link color after selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnMinusButton().get(passengerCounter), "color");
			
			//verify carry-on bag minus link color after selection is Gray
			ValidationUtil.validateTestStep("Verify Departing carry-on bag minus link color after selection is Blue", signColor.equals(BLUE_COLOR));
			
			//get carry-on bag plus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnPlusButton().get(passengerCounter), "color");
			
			//verify carry-on bag minus link color before selection is Gray
			ValidationUtil.validateTestStep("Verify Departing carry-on bag plus link color after selection is Gray", signColor.equals(GRAY_COLOR));
			
			//verify add Carry-on for $xx is not displaying on screen
			ValidationUtil.validateTestStep("Verify \"Add a Carry-On Bag for $xx\" is not displaying on screen for Departing Bags", pageObjectManager.getBagsPage().getDepartingNextCarryOnPriceText().get(passengerCounter).findElements(By.tagName("p")).size()==0);
			
			//get the carry-on bag counter after selection
			carryBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(passengerCounter)));
			
			//verify carry-on bag count before selection is 1
			ValidationUtil.validateTestStep("Verify Departing carry-on bag count after selection is 1", carryBagCounter==ONE_CARRY_BAG);
				
			//*************************************************
			//**Carry-On Bag when user try to add two bags*****
			//*************************************************
			//click on carry-on bag plus link
			pageObjectManager.getBagsPage().getDepartingCarryOnPlusButton().get(passengerCounter).click();
			
			//get the carry-on bag counter after selection
			carryBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(passengerCounter)));
			
			//verify carry-on bag count after selection is not 2
			ValidationUtil.validateTestStep("Verify Departing carry-on bag count after selection is not 2", carryBagCounter==ONE_CARRY_BAG);
			
			//******************************************
			//**Carry-On Bag after removing one bag*****
			//******************************************
			//click on carry-on bag minus link
			pageObjectManager.getBagsPage().getDepartingCarryOnMinusButton().get(passengerCounter).click();
			
			//get carry-on bag minus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnMinusButton().get(passengerCounter), "color");
		
			//verify carry-on bag minus link color before removing is Gray
			ValidationUtil.validateTestStep("Verify Departing carry-on bag minus link color after removing is Gray", signColor.equals(GRAY_COLOR));
			
			//get carry-on bag plus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnPlusButton().get(passengerCounter), "color");

			//verify carry-on bag minus link color before removing is Gray
			ValidationUtil.validateTestStep("Verify Departing carry-on bag plus link color after removing is Blue", signColor.equals(BLUE_COLOR));
			
			//verify add Carry-on for $xx is displaying again on screen
			ValidationUtil.validateTestStep("Verify \"Add a Carry-On Bag for $xx\" is displaying on screen after removing bag for Departing Bags", pageObjectManager.getBagsPage().getDepartingNextCarryOnPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);
			
			//get the carry-on bag cont before selection
			carryBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(passengerCounter)));
			
			//verify carry-on bag count after removing is 0
			ValidationUtil.validateTestStep("Verify Departing carry-on bag count after removing is 0", carryBagCounter==NO_CARRY_BAG);
			
			//*****************************************
			//**Carry-On Bag cannot be -1 in count*****
			//*****************************************
			//click on carry-on bag minus link
			pageObjectManager.getBagsPage().getDepartingCarryOnMinusButton().get(passengerCounter).click();
			
			//get the carry-on bag counter after selection
			carryBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(passengerCounter)));
			
			//verify carry-on bag cannot be -1
			ValidationUtil.validateTestStep("Verify Departing carry-on bag count cannot be -1", carryBagCounter==NO_CARRY_BAG);
			
			
			//*******************************************Departing Checked Bag*****************************************************
			
			//********************************************
			//***Checked Bag when no bag is selected******
			//********************************************
			//get checked bag minus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter), "color");
		
			//verify checked bag minus link color before selection is Gray
			ValidationUtil.validateTestStep("Verify Departing checked bag minus link color before selection is Gray", signColor.equals(GRAY_COLOR));
			
			//get checked bag plus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter), "color");

			//verify cachecked bag minus link color before selection is blue
			ValidationUtil.validateTestStep("Verify Departing Checked bag plus link color before selection is Blue", signColor.equals(BLUE_COLOR));
			
			//verify add Checked Bag for $xx is displaying on screen
			ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is displaying on screen", pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);
			
			//store bag price in varaibel
			preChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
		
			//get the checked bag cont before selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 0
			ValidationUtil.validateTestStep("Verify Departing checked bag count before selection is 0", checkedBagCounter==NO_CHECKED_BAG);
			
			//*******************************************
			//***Checked Bag after selecting one bag*****
			//*******************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();
			
			//get checked bag minus link color after selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter), "color");
			
			//verify checked bag minus link color after selection is Blue
			ValidationUtil.validateTestStep("Verify Departing checked bag minus link color after selection is Blue", signColor.equals(BLUE_COLOR));
			
			//get checked bag plus link color after selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter), "color");
			
			//verify checked bag minus link color after selection is Blue
			ValidationUtil.validateTestStep("Verify Departing checked bag plus link color after selection is Blue", signColor.equals(BLUE_COLOR));
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

			//"Add Checked Bag for $XX" price has increased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has increased after adding 1 Departing checked Bag", crrChecBagPrice-preChecBagPrice>0);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 1
			ValidationUtil.validateTestStep("Verify Departing checked bag count after selection is 1", checkedBagCounter==ONE_CHECKED_BAG);
			
			//*******************************************
			//***Checked Bag after selecting two bags****
			//*******************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has increased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has increased after adding 2 Departing checked Bags", crrChecBagPrice-preChecBagPrice>0);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 2
			ValidationUtil.validateTestStep("Verify Departing checked bag count after selection is 2", checkedBagCounter==TWO_CHECKED_BAG);
			
			//*********************************************
			//***Checked Bag after selecting three bags****
			//*********************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has increased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after adding 3 Departing checked Bags", crrChecBagPrice==preChecBagPrice);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 3
			ValidationUtil.validateTestStep("Verify Departing checked bag count after selection is 3", checkedBagCounter==THREE_CHECKED_BAG);
			
			//*********************************************
			//***Checked Bag after selecting four bags*****
			//*********************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has increased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after adding 4 Departing checked Bags", crrChecBagPrice==preChecBagPrice);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 4
			ValidationUtil.validateTestStep("Verify Departing checked bag count after selection is 4", checkedBagCounter==FOUR_CHECKED_BAG);
			
			//*********************************************
			//***Checked Bag after selecting five bags*****
			//*********************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();
			
			//get checked bag plus link color after selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter), "color");
			
			//Verify checked bag plus link color after selecting 5 bags is Gray
			ValidationUtil.validateTestStep("Verify checked bag plus link color after selecting 5 bags is Gray", signColor.equals(GRAY_COLOR));
			
			//verify add Checked for $xx is not displaying on screen
			ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is not displaying on screen for Departing Checked Bags" , pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()==0);
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 5
			ValidationUtil.validateTestStep("Verify Departing checked bag count after selection is 5", checkedBagCounter==FIVE_CHECKED_BAG);
			
			//*********************************************
			//***Checked Bag cannot be more then 5 bags****
			//*********************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot be 6
			ValidationUtil.validateTestStep("Verify Departing Checked bag count cannot be 6", checkedBagCounter==FIVE_CHECKED_BAG);
			
			//******************************************************
			//**Checked Bag after removing one bag(with 4 bags)*****
			//******************************************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify checked bag count is 4 after removing one Bag", checkedBagCounter==FOUR_CHECKED_BAG);
			
			//verify add Checked Bag for $xx is displaying on screen after removing one bag
			ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is displaying on screenafter removing one Departing bag", pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);
			
			//get the current bag price appear for checked Bag
			preChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//******************************************************
			//**Checked Bag after removing one bag(with 3 bags)*****
			//******************************************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Departing checked bag count is 3 after removing one Bag", checkedBagCounter==THREE_CHECKED_BAG);
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has same.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after removing one Departing Checked bag(with 3 bags)", crrChecBagPrice==preChecBagPrice);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//******************************************************
			//**Checked Bag after removing one bag(with 2 bags)*****
			//******************************************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Departing Checked bag count is 2 after removing one Bag", checkedBagCounter==TWO_CHECKED_BAG);
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has same.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after removing one Departing Checked bag(with 2 bags)", crrChecBagPrice==preChecBagPrice);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//******************************************************
			//**Checked Bag after removing one bag(with 1 bags)*****
			//******************************************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Departing Checked bag count is 1 after removing one Bag", checkedBagCounter==ONE_CHECKED_BAG);
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has decreased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the decreased after removing one Departing Checked bag(with 1 bags)", crrChecBagPrice<preChecBagPrice);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//******************************************************
			//**Checked Bag after removing one bag(with 0 bags)*****
			//******************************************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Departing Checked bag count is 0 after removing one Departing Checked Bag", checkedBagCounter==NO_CHECKED_BAG);
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has decreased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the decreased after removing one Departing Checked bag(with 0 bags)", crrChecBagPrice<preChecBagPrice);
			
			//*******************************
			//**Checked Bag cannot be -1*****
			//*******************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Departing Checked bag count is not -1 after removing one Departing Checked Bag", checkedBagCounter==NO_CHECKED_BAG);
			
			//*******************************************Returning Carry-On Bag*****************************************************
			
			//********************************************
			//**Carry-On Bag when no bag is selected******
			//********************************************
			//get carry-on bag minus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnMinusButton().get(passengerCounter), "color");
		
			//Verify carry-on bag minus link color before selection is Gray
			ValidationUtil.validateTestStep("Verify Returning carry-on bag minus link color before selection is Gray", signColor.equals(GRAY_COLOR));
			
			//get carry-on bag plus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnPlusButton().get(passengerCounter), "color");

			//verify carry-on bag minus link color before selection is Gray
			ValidationUtil.validateTestStep("Verify Returning carry-on bag plus link color before selection is Blue", signColor.equals(BLUE_COLOR));
			
			//verify add Carry-on for $xx is displaying on screen
			ValidationUtil.validateTestStep("Verify \\\"Add a Carry-On Bag for $xx\\\" is displaying on screen for Returning Bags", pageObjectManager.getBagsPage().getDepartingNextCarryOnPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);
			
			//get the carry-on bag cont before selection
			carryBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnCounterTextBox().get(passengerCounter)));
			
			//verify carry-on bag count before selection is 0
			ValidationUtil.validateTestStep("Verify Returning carry-on bag count before selection is 0", carryBagCounter==NO_CARRY_BAG);
			
			//*******************************************
			//**Carry-On Bag after selecting one bag*****
			//*******************************************
			//click on carry-on bag plus link
			pageObjectManager.getBagsPage().getReturningCarryOnPlusButton().get(passengerCounter).click();
			
			//get carry-on bag minus link color after selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnMinusButton().get(passengerCounter), "color");
			
			//verify carry-on bag minus link color after selection is Gray
			ValidationUtil.validateTestStep("Verify Returning carry-on bag minus link color after selection is Blue", signColor.equals(BLUE_COLOR));
			
			//get carry-on bag plus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnPlusButton().get(passengerCounter), "color");
			
			//verify carry-on bag minus link color before selection is Gray
			ValidationUtil.validateTestStep("Verify Returning carry-on bag plus link color after selection is Gray", signColor.equals(GRAY_COLOR));
			
			//verify add Carry-on for $xx is not displaying on screen
			ValidationUtil.validateTestStep("Verify \"Add a Carry-On Bag for $xx\" is not displaying on screen for Returning Bags", pageObjectManager.getBagsPage().getReturningNextCarryOnPriceText().get(passengerCounter).findElements(By.tagName("p")).size()==0);
			
			//get the carry-on bag counter after selection
			carryBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnCounterTextBox().get(passengerCounter)));
			
			//verify carry-on bag count before selection is 1
			ValidationUtil.validateTestStep("Verify Returning carry-on bag count after selection is 1", carryBagCounter==ONE_CARRY_BAG);
				
			//*************************************************
			//**Carry-On Bag when user try to add two bags*****
			//*************************************************
			//click on carry-on bag plus link
			pageObjectManager.getBagsPage().getReturningCarryOnPlusButton().get(passengerCounter).click();
			
			//get the carry-on bag counter after selection
			carryBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnCounterTextBox().get(passengerCounter)));
			
			//verify carry-on bag count after selection is not 2
			ValidationUtil.validateTestStep("Verify Returning carry-on bag count after selection is not 2", carryBagCounter==ONE_CARRY_BAG);
			
			//******************************************
			//**Carry-On Bag after removing one bag*****
			//******************************************
			//click on carry-on bag minus link
			pageObjectManager.getBagsPage().getReturningCarryOnMinusButton().get(passengerCounter).click();
			
			//get carry-on bag minus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnMinusButton().get(passengerCounter), "color");
		
			//verify carry-on bag minus link color before removing is Gray
			ValidationUtil.validateTestStep("Verify Returning carry-on bag minus link color after removing is Gray", signColor.equals(GRAY_COLOR));
			
			//get carry-on bag plus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnPlusButton().get(passengerCounter), "color");

			//verify carry-on bag minus link color before removing is Gray
			ValidationUtil.validateTestStep("Verify Returning carry-on bag plus link color after removing is Blue", signColor.equals(BLUE_COLOR));
			
			//verify add Carry-on for $xx is displaying again on screen
			ValidationUtil.validateTestStep("Verify \"Add a Carry-On Bag for $xx\" is displaying on screen after removing bag for Returning Bags", pageObjectManager.getBagsPage().getReturningNextCarryOnPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);
			
			//get the carry-on bag cont before selection
			carryBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnCounterTextBox().get(passengerCounter)));
			
			//verify carry-on bag count after removing is 0
			ValidationUtil.validateTestStep("Verify Returning carry-on bag count after removing is 0", carryBagCounter==NO_CARRY_BAG);
			
			//*****************************************
			//**Carry-On Bag cannot be -1 in count*****
			//*****************************************
			//click on carry-on bag minus link
			pageObjectManager.getBagsPage().getReturningCarryOnMinusButton().get(passengerCounter).click();
			
			//get the carry-on bag counter after selection
			carryBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnCounterTextBox().get(passengerCounter)));
			
			//verify carry-on bag cannot be -1
			ValidationUtil.validateTestStep("Verify Returning carry-on bag count cannot be -1", carryBagCounter==NO_CARRY_BAG);
			
			//*******************************************Returning Checked Bag*****************************************************
			
			//********************************************
			//***Checked Bag when no bag is selected******
			//********************************************
			//get checked bag minus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter), "color");
		
			//verify checked bag minus link color before selection is Gray
			ValidationUtil.validateTestStep("Verify Returning checked bag minus link color before selection is Gray", signColor.equals(GRAY_COLOR));
			
			//get checked bag plus link color before selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter), "color");

			//verify cachecked bag minus link color before selection is blue
			ValidationUtil.validateTestStep("Verify Returning Checked bag plus link color before selection is Blue", signColor.equals(BLUE_COLOR));
			
			//verify add Checked Bag for $xx is displaying on screen
			ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is displaying on screen for Returning Checked Bag", pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);
			
			//store bag price in varaibel
			preChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
		
			//get the checked bag cont before selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 0
			ValidationUtil.validateTestStep("Verify Returning Checked checked bag count before selection is 0", checkedBagCounter==NO_CHECKED_BAG);
						
			//*******************************************
			//***Checked Bag after selecting one bag*****
			//*******************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();
			
			//get checked bag minus link color after selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter), "color");
			
			//verify checked bag minus link color after selection is Blue
			ValidationUtil.validateTestStep("Verify Returning Checked bag minus link color after selection is Blue", signColor.equals(BLUE_COLOR));
			
			//get checked bag plus link color after selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter), "color");
			
			//verify checked bag minus link color after selection is Blue
			ValidationUtil.validateTestStep("Verify Returning Checked bag plus link color after selection is Blue", signColor.equals(BLUE_COLOR));
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

			//"Add Checked Bag for $XX" price has increased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has increased after adding 1 Returning Checked Bag", crrChecBagPrice-preChecBagPrice>0);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 1
			ValidationUtil.validateTestStep("Verify Returning Checked bag count after selection is 1", checkedBagCounter==ONE_CHECKED_BAG);
					
			//*******************************************
			//***Checked Bag after selecting two bags****
			//*******************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has increased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has increased after adding 2 Returning Checked Bags", crrChecBagPrice-preChecBagPrice>0);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 2
			ValidationUtil.validateTestStep("Verify Returning Checked bag count after selection is 2", checkedBagCounter==TWO_CHECKED_BAG);
					
			//*********************************************
			//***Checked Bag after selecting three bags****
			//*********************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has increased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after adding 3 Returning Checked Bags", crrChecBagPrice==preChecBagPrice);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 3
			ValidationUtil.validateTestStep("Verify Returning Checked bag count after selection is 3", checkedBagCounter==THREE_CHECKED_BAG);
					
			//*********************************************
			//***Checked Bag after selecting four bags*****
			//*********************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has increased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after adding 4 Returning Checked Bags", crrChecBagPrice==preChecBagPrice);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 4
			ValidationUtil.validateTestStep("Verify Returning Checked bag count after selection is 4", checkedBagCounter==FOUR_CHECKED_BAG);
					
			//*********************************************
			//***Checked Bag after selecting five bags*****
			//*********************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();
			
			//get checked bag plus link color after selection
			signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter), "color");
			
			//Verify checked bag plus link color after selecting 5 bags is Gray
			ValidationUtil.validateTestStep("Verify Returning Checked bag plus link color after selecting 5 bags is Gray", signColor.equals(GRAY_COLOR));
			
			//verify add Checked for $xx is not displaying on screen
			ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is not displaying on screen for Returning Checked Bags" , pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()==0);
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count before selection is 5
			ValidationUtil.validateTestStep("Verify Returning Checked bag count after selection is 5", checkedBagCounter==FIVE_CHECKED_BAG);
					
			//*********************************************
			//***Checked Bag cannot be more then 5 bags****
			//*********************************************
			//click on checked bag plus link
			pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot be 6
			ValidationUtil.validateTestStep("Verify Returning Checked bag count cannot be 6", checkedBagCounter==FIVE_CHECKED_BAG);
			
			//******************************************************
			//**Checked Bag after removing one bag(with 4 bags)*****
			//******************************************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Returning Checked bag count is 4 after removing one Bag", checkedBagCounter==FOUR_CHECKED_BAG);
			
			//verify add Checked Bag for $xx is displaying on screen after removing one bag
			ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is displaying on screenafter removing one Returning Checked bag", pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);
			
			//get the current bag price appear for checked Bag
			preChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
					
			//******************************************************
			//**Checked Bag after removing one bag(with 3 bags)*****
			//******************************************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Returning Checked bag count is 3 after removing one Bag", checkedBagCounter==THREE_CHECKED_BAG);
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has same.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after removing one Returning Checked bag(with 3 bags)", crrChecBagPrice==preChecBagPrice);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
					
			//******************************************************
			//**Checked Bag after removing one bag(with 2 bags)*****
			//******************************************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Returning Checked bag count is 2 after removing one Bag", checkedBagCounter==TWO_CHECKED_BAG);
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has same.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after removing one Returning Checked bag(with 2 bags)", crrChecBagPrice==preChecBagPrice);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
					
			//******************************************************
			//**Checked Bag after removing one bag(with 1 bags)*****
			//******************************************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Returning Checked bag count is 1 after removing one Bag", checkedBagCounter==ONE_CHECKED_BAG);
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has decreased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the decreased after removing one Returning Checked bag(with 1 bags)", crrChecBagPrice<preChecBagPrice);
			
			//store current bag price to previous bag price variable for furthur testing
			preChecBagPrice = crrChecBagPrice;
					
			//******************************************************
			//**Checked Bag after removing one bag(with 0 bags)*****
			//******************************************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Returning Checked bag count is 0 after removing one Returning Checked Bag", checkedBagCounter==NO_CHECKED_BAG);
			
			//get the current bag price appear for checked Bag
			crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);
			
			//"Add Checked Bag for $XX" price has decreased.
			ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the decreased after removing one Returning Checked bag(with 0 bags)", crrChecBagPrice<preChecBagPrice);
					
			//*******************************
			//**Checked Bag cannot be -1*****
			//*******************************
			//click on checked bag minus link
			pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter).click();
			
			//get the checked bag counter after selection
			checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));
			
			//verify checked bag count cannot is 4 after removing one Bag
			ValidationUtil.validateTestStep("Verify Returning Checked bag count is not -1 after removing one Returning Checked Bag", checkedBagCounter==NO_CHECKED_BAG);

		}
	}

}
