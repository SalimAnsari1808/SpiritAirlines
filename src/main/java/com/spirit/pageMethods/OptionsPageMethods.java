package com.spirit.pageMethods;



import com.spirit.pageObjects.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.OptionsPage;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;

import javax.swing.plaf.synth.SynthEditorPaneUI;


public class OptionsPageMethods {

	private WebDriver driver;
	private ScenarioContext scenarioContext;
	private OptionsPage optionsPage;
	private Header header;

	public OptionsPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
		this.driver				= driver;
		this.scenarioContext 	= scenarioContext;
		optionsPage 			= pageObjectManager.getOptionsPage();
		header					= pageObjectManager.getHeader();
	}

	// **********************************************************************************************
	// Method : selectOptions
	// Description: Method is used to select the Flight option on Options Page
	// Input Arguments: options :FlightFlex|ShortCutSecurity,ShortCutSecurity|ShortCutBoarding|CheckInOption:MobileFree
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void selectOptions(String options) {

		WaitUtil.untilPageLoadComplete(driver);

		//declare constant used in method
		final String SEPARATOR = "\\|";
		
		//get all options 
		String[] allOptions = options.split(SEPARATOR);
		
		//loop through all options
		for(int optionsCounter=0;optionsCounter<allOptions.length;optionsCounter++) {
			//check for option value and call methods
			if(allOptions[optionsCounter].contains("FlightFlex")) {
				//call method to select flight flex
				selectFlightFlex();
			}else if(allOptions[optionsCounter].contains("ShortCutSecurity")) {
				//select method to select shortcut security
				selectShortCutSecurity(allOptions[optionsCounter].split(",")[0], allOptions[optionsCounter].split(",")[1]);
			}else if(allOptions[optionsCounter].contains("ShortCutBoarding")) {
				//call method to select shortcut boarding
				selectShortCutBoarding();
			}else if(allOptions[optionsCounter].contains("CheckInOption")) {
				//call method to select checkIn option
				selectCheckInOption(allOptions[optionsCounter].split(":")[1]);
			}
		}
	}
	
	// **********************************************************************************************
	// Method : selectFlightFlex
	// Description: Method is used to select the Flight Flex on Options Page
	// Input Arguments: NA
	// Return: NA
	// Created By : Anthony C
	// Created On : 13-Mar-2019
	// Reviewed By: Salim Ansari
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	private synchronized void selectFlightFlex(){
		//declare variable used in methods
		double flightFlexPrice;
		double totalFlightFlexPrice;
		int totalPaxCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());
		
		//click on add button
		optionsPage.getFlightFlexCardAddButton().click();
		
		//get the flight flex amout person
		flightFlexPrice = Double.parseDouble(optionsPage.getFlightFlexCardPriceText().getText().replace("$", "").trim());
		
		//get total flight flex amount for all passengers
		totalFlightFlexPrice = flightFlexPrice * totalPaxCount;
		
		//store flight flex per person
		scenarioContext.setContext(Context.OPTIONS_FLIGHT_FLEX, TestUtil.convertTo2DecimalValue(totalFlightFlexPrice));
	
		//Validate that the Flight Flex has been selected
		ValidationUtil.validateTestStep("Flight Flex is selected on Optional Page", TestUtil.verifyElementDisplayed(driver,By.xpath(optionsPage.xpath_FlightFlexCardSelectedLabel)));
	}
	
	// **********************************************************************************************
	// Method : selectShortCutSecurity
	// Description: Method is used to select the ShortCut Security on Options Page
	// Input Arguments: NA
	// Return: NA
	// Created By : Anthony C
	// Created On : 13-Mar-2019
	// Reviewed By: Salim Ansari
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	private synchronized void selectShortCutSecurity(String depShortCutSecurity, String retShortCutSecurity){
		//declare variable used in method
		String totalShortcutSecurityPrice;
		String airportCode;
		double shortcutSecurityValue;
		int shortCutSecurityCount = 0;
		int totalPaxCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());
		
		//declare constant used in method
		final int DEP_SHORTCUTSECURITY = 0;
		final int RET_SHORTCUTSECURITY = 0;
		String NotAva = "";

		//click on add button
		optionsPage.getShortCutSecurityCardAddButton().click();

		WaitUtil.untilTimeCompleted(1000);

		//check not avaliable text is present
		if(TestUtil.verifyElementDisplayed(driver,By.xpath(optionsPage.xpath_ShortCutSecurityPopUpNotAvaiableText))){
			//get airport name for english and spanish
			if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
				NotAva = optionsPage.getShortCutSecurityPopUpNotAvaiableText().getText().split("available in ")[1].split(",")[0];
			}else{
				NotAva = optionsPage.getShortCutSecurityPopUpNotAvaiableText().getText().split("disponible en ")[1].split(",")[0];
			}

			if(scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().contains(NotAva) && depShortCutSecurity.equalsIgnoreCase("ShortCutSecurity")){
				depShortCutSecurity = "NotRequired";
			}else if(scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().contains(NotAva) && retShortCutSecurity.equalsIgnoreCase("ShortCutSecurity")){
				retShortCutSecurity = "NotRequired";
			}
		}

		//check shortcut security is required for dep flight
		if(!depShortCutSecurity.trim().equalsIgnoreCase("NotRequired")) {
			shortCutSecurityCount = shortCutSecurityCount +1;
		}
		
		//check shortcut security is required for return flight
		if(!retShortCutSecurity.trim().equalsIgnoreCase("NotRequired")) {
			shortCutSecurityCount = shortCutSecurityCount +1;
		}

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);
		
		//pop up for each journey
		//Validate that the ShortCut Security has been selected
		ValidationUtil.validateTestStep("ShortCut Security visible for all journey on ShortCut Security Popup on Options page", optionsPage.getShortCutSecurityPopUpSelectCityCheckBox().size()>=shortCutSecurityCount);

		//select shortcut security for departing flight
		if(depShortCutSecurity.trim().equalsIgnoreCase("ShortCutSecurity")) {
			//get the airport code
			airportCode = scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split("\\(")[1].split("\\)")[0];
			
			//check shortcut is previously stored in global variable
			if(!scenarioContext.isContains(Context.OPTIONS_SHORTCUT_SECURITY)) {
				//get the shortcut security amount
				shortcutSecurityValue = Double.parseDouble(optionsPage.getShortCutSecurityPopUpSelectCityCheckBox().get(DEP_SHORTCUTSECURITY).getText().split("\\$",2)[1].split(" ")[0].trim());
				
				//calculate total shortcut security amount
				shortcutSecurityValue = totalPaxCount * shortcutSecurityValue;
				
				//get the total amount based on passengers
				totalShortcutSecurityPrice = totalPaxCount + " " + airportCode + "|" + TestUtil.convertTo2DecimalValue(shortcutSecurityValue);
				
				//store into global varaible
				scenarioContext.setContext(Context.OPTIONS_SHORTCUT_SECURITY, totalShortcutSecurityPrice);
			}else {
				//get the shortcut security amount
				shortcutSecurityValue = Double.parseDouble(optionsPage.getShortCutSecurityPopUpSelectCityCheckBox().get(DEP_SHORTCUTSECURITY).getText().split("\\$",2)[1].split(" ")[0].trim());
				
				//get the total amount based on passengers
				shortcutSecurityValue = totalPaxCount * shortcutSecurityValue;
				
				//get the total amount based on passengers
				totalShortcutSecurityPrice = scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY)+ "||" + totalPaxCount + " " + airportCode + "|" + TestUtil.convertTo2DecimalValue(shortcutSecurityValue);
				
				//store into global variable
				scenarioContext.setContext(Context.OPTIONS_SHORTCUT_SECURITY, totalShortcutSecurityPrice);
			}
			//select dep shortcut security
			optionsPage.getShortCutSecurityPopUpSelectCityCheckBox().get(DEP_SHORTCUTSECURITY).click();
		}
		
		if(retShortCutSecurity.trim().equalsIgnoreCase("ShortCutSecurity")) {
			//get the airport code
			airportCode = scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[1].split("\\)")[0];
			
			//check shortcut is previously stored in global variable
			if(!scenarioContext.isContains(Context.OPTIONS_SHORTCUT_SECURITY)) {
				//get the shortcut security amount
				shortcutSecurityValue = Double.parseDouble(optionsPage.getShortCutSecurityPopUpSelectCityCheckBox().get(optionsPage.getShortCutSecurityPopUpSelectCityCheckBox().size()-1).getText().split("\\$",2)[1].split(" ")[0].trim());
				
				//calculate total shortcut security amount
				shortcutSecurityValue = totalPaxCount * shortcutSecurityValue;

				//store the passenger arrival airport and total shortcut security amount
				totalShortcutSecurityPrice = totalPaxCount + " " + airportCode + "|" + TestUtil.convertTo2DecimalValue(shortcutSecurityValue);
				
				//store in global variable
				scenarioContext.setContext(Context.OPTIONS_SHORTCUT_SECURITY, totalShortcutSecurityPrice);		
			}else {
				//get the shortcut security amount
				shortcutSecurityValue = Double.parseDouble(optionsPage.getShortCutSecurityPopUpSelectCityCheckBox().get(optionsPage.getShortCutSecurityPopUpSelectCityCheckBox().size()-1).getText().split("\\$",2)[1].split(" ")[0].trim());
				
				//calculate total shortcut security amount
				shortcutSecurityValue = totalPaxCount * shortcutSecurityValue;
				
				//store the passenger arrival airport and total shortcut security amount
				totalShortcutSecurityPrice = scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY)+ "||" + totalPaxCount + " " + airportCode + "|" + TestUtil.convertTo2DecimalValue(shortcutSecurityValue);
				
				//store in global variable
				scenarioContext.setContext(Context.OPTIONS_SHORTCUT_SECURITY, totalShortcutSecurityPrice);
			}
			
			//select return shortcut security
			optionsPage.getShortCutSecurityPopUpSelectCityCheckBox().get(optionsPage.getShortCutSecurityPopUpSelectCityCheckBox().size()-1).click();
		}
		
		//click to select shortcut security popup
		optionsPage.getShortCutSecurityPopUpAddButton().click();
		
		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//Validate that the ShortCut Security has been selected
		ValidationUtil.validateTestStep("ShortCut Security is selected on Options Page", TestUtil.verifyElementDisplayed(driver,By.xpath(optionsPage.xpath_ShortCutSecurityCardSelectedLabel)));
	}
	
	// **********************************************************************************************
	// Method : selectShortCutBoarding
	// Description: Method is used to select the ShortCut Boarding on Options Page
	// Input Arguments: NA
	// Return: NA
	// Created By : Anthony C
	// Created On : 13-Mar-2019
	// Reviewed By: Salim Ansari
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	private synchronized void selectShortCutBoarding(){
		double shortcutBoardingPrice;
		double totalShortcutBoardingPrice;
		int journeyCounter = 0;
		int totalPaxCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());
		
		//get shortcut boarding price for each pax
		shortcutBoardingPrice = Double.parseDouble(optionsPage.getShortCutBoardingCardPriceText().getText().replace("$", "").trim());
		
		if(scenarioContext.isContains(Context.HOMEPAGE_TRIP_TYPE)) {
			if(scenarioContext.getContext(Context.HOMEPAGE_TRIP_TYPE).toString().contains("roundtrip")) {
				journeyCounter = 2;
			}else if(scenarioContext.getContext(Context.HOMEPAGE_TRIP_TYPE).toString().contains("oneway")) {
				journeyCounter = 1;
			}else if(scenarioContext.getContext(Context.HOMEPAGE_TRIP_TYPE).toString().contains("multicity")) {
				throw new RuntimeException("Multicity code is missing in selectShortCutBoarding");
			}
		}
		//calculate total shortcut boarding price
		totalShortcutBoardingPrice = shortcutBoardingPrice * totalPaxCount *  journeyCounter;
		
		//store into global variable
		scenarioContext.setContext(Context.OPTIONS_SHORTCUT_BOARDING, TestUtil.convertTo2DecimalValue(totalShortcutBoardingPrice));
		
		//click on add button
		optionsPage.getShortCutBoardingCardAddButton().click();
		
		//Validate that the ShortCut Boarding has been selected
		ValidationUtil.validateTestStep("ShortCut Boarding is selected on options Page", TestUtil.verifyElementDisplayed(driver,By.xpath(optionsPage.xpath_ShortCutBoardingCardSelectedLabel)));
	}
	
	// **********************************************************************************************
	// Method : selectCheckInOption
	// Description: Method is used to select the CheckIn Option on Options Page
	// Input Arguments: NA
	// Return: NA
	// Created By : Anthony C
	// Created On : 13-Mar-2019
	// Reviewed By: Salim Ansari
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	private synchronized void selectCheckInOption(String checkInOption){
		//declare available used in method
		String checkInOptionValue = null;
		double checkInSelectedValue;
		double totalCheckInPrice;
		int totalPaxCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());
		
		//select the drop down value based on the check in option
		if(checkInOption.equalsIgnoreCase("MobileFree")) {
			checkInOptionValue = "ONLINE";
		}else if(checkInOption.equalsIgnoreCase("AirportAgent")) {
			checkInOptionValue = "PRE-PAY";
		}else if(checkInOption.equalsIgnoreCase("DecideLater")) {
			checkInOptionValue = "NONE";
		}

		//click  to make sure check-in section is visible
		optionsPage.getCheckInOptionCardBodySelectLabel().click();

		//create drop down object
		Select drpdnCheckInOptions = new Select(optionsPage.getCheckInOptionCardBodySelectDropDown());
		
		//Select check in option by value passed as a parameter
		drpdnCheckInOptions.selectByValue(checkInOptionValue);
		
		//check when user selected airport agent for checkin
		if(checkInOptionValue == "PRE-PAY") {
			//get the checkin option amount
			checkInSelectedValue = Double.parseDouble(drpdnCheckInOptions.getFirstSelectedOption().getText().split("\\$")[1].split(" ")[0]);
			
			totalCheckInPrice = checkInSelectedValue * totalPaxCount;
			
			//store in global variable
			scenarioContext.setContext(Context.OPTIONS_CHECK_IN, TestUtil.convertTo2DecimalValue(totalCheckInPrice));	
		}
		
//Validate that the check in option has been selected
		ValidationUtil.validateTestStep("Check in Option "+ checkInOption + " is selected in Option Page",
					TestUtil.verifyElementDisplayed(driver,By.xpath(optionsPage.xpath_CheckInOptionCardSelectedLabel)));
}

	// **********************************************************************************************
	// Method : continueToPurchaseButton
	// Description: Method is used to click on continue buuton on Option page
	// Input Arguments: options :FlightFlex|ShortCutSecurity,ShortCutSecurity|ShortCutBoarding|CheckInOption:MobileFree
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void continueToPurchaseButton(){
		//click on continue button
		optionsPage.getContinueToPurchaseButton().click();

		ValidationUtil.validateTestStep("User click on Continue button on Options Page" ,true);
		
		//wait untill page load is complete
		WaitUtil.untilPageLoadComplete(driver);
		
		WaitUtil.untilPageURLVisible(driver, "payment");
		
		//wait untill page load is complete
		WaitUtil.untilPageLoadComplete(driver);
	}

	//**********************************************************************************************
	// Method : verifySelectedBaseFareOptions
	// Description: Method is used to verify below points
	//				1.Verify Bare Fare in Dynamic Shopping Cart
	//				2.Verify Flight Flex and Shortcut Boarding is pre selected for Bundle It Bare Fare
	//				3.Verify Shortcut Boarding is pre selected for Boost It Bare Fare
	//				4.Verify Flight Flex and Shortcut Boarding added into Total Breakdown for Bundle It Bare Fare
	//				5.Verify Shortcut Boarding added into Total Breakdown for Boost It Bare Fare
	//				6.Verify Included Verbiage for Selected Options in Total Breakdown Section for Bare Fare
	// Input Arguments: basefareType -> BoostIt/BundleIt
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 11-June-2019
	//**********************************************************************************************
	public synchronized void verifySelectedBaseFareOptions(String basefareType) {
		//********************************************************
		//****************Bare Fare From Shopping Cart************
		//********************************************************
		//declare constant used in method
		final String BUNDLE_ITINERARY = "Bundle It Discount";
		final String BOOST_ITINERARY = "Boost It Discount";

		//declare variable used in method
		String barefareText = "";
		String barefareAmount = "";

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//open dynamic shopping cart
		header.getArrowYourItineraryImage().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//get the bare fare text and amount
		barefareText = header.getBareFareDiscountItineraryText().getText();
		barefareAmount = header.getBareFareDiscountPriceItineraryText().getText().replace("-", "");

		//check for bare fare type
		if (basefareType.equalsIgnoreCase("BundleIt")) {
			//verify bundle discount text
			ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart on Options Page", barefareText, BUNDLE_ITINERARY);

			//verify bundle discount amount
			ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart on Options Page",
					scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), barefareAmount);

		} else if (basefareType.equalsIgnoreCase("BoostIt")) {
			//verify boost discount text
			ValidationUtil.validateTestStep("Verify the Boost It Discount text on Dynamic Shopping Cart on Options Page", barefareText, BOOST_ITINERARY);

			//verify boost discount amount
			ValidationUtil.validateTestStep("Verify the Boost It Discount Price on Dynamic Shopping Cart on Options Page",
					scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(), barefareAmount);
		}

		//open dynamic shopping cart
		header.getArrowYourItineraryImage().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);


		//********************************************************
		//****************Pre-Selected Options Bare Fare**********
		//********************************************************
		//declare constant used in method
		final String OPTION_TEXT_ENGLISH	= "SELECTED";
		final String OPTION_TEXT_SPANISH	= "SELECCIONADO";

		//declare variable used in method
		String selected		= "";

		//check for booking language
		if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
			selected = OPTION_TEXT_ENGLISH;
		}else if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("Spanish")){
			selected = OPTION_TEXT_SPANISH;
		}

		//check for bundle fare
		if(basefareType.equalsIgnoreCase("BundleIt")){
			//verify Flight Flex is pre-selected
			ValidationUtil.validateTestStep("Validating Flight Flex is preselected on Options Page",
					optionsPage.getFlightFlexCardSelectedLabel().getText().toUpperCase().trim(),selected);
		}

		//verify Shortcut Boarding is pre-selected
		ValidationUtil.validateTestStep("Validating Shortcut Boarding is preselected on Options Page",
				optionsPage.getShortCutBoardingCardSelectedLabel().getText().toUpperCase().trim(),selected);

		//********************************************************
		//****************Total Breakdown Section*****************
		//********************************************************
		//declare constant used in method
		final String TOTAL_AMOUNT 				= "$0.00";
		final String FLIGHT_FLEX_ENGLISH		= "FLIGHT FLEX";
		final String FLIGHT_FLEX_SPANISH		= "FLIGHT FLEX";
		final String SHORTCUT_BOARDING_ENGLISH	= "SHORTCUT BOARDING";
		final String SHORTCUT_BOARDING_SPANISH	= "PRIORIDAD DE EMBARQUE";
		final String INDLUDED_ENGLISH			= "INCLUDED";
		final String INCLUDED_SPANISH			= "INCLUIDO";

		//declare variable used in method
		String flightFlex 		= "";
		String shortcutBoarding	= "";
		String included			= "";

		//check for booking language
		if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
			flightFlex 			= FLIGHT_FLEX_ENGLISH;
			shortcutBoarding	= SHORTCUT_BOARDING_ENGLISH;
			included			= INDLUDED_ENGLISH;
		}else if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("Spanish")){
			flightFlex 			= FLIGHT_FLEX_SPANISH;
			shortcutBoarding	= SHORTCUT_BOARDING_SPANISH;
			included			= INCLUDED_SPANISH;
		}

		//verify total breakdown amount
		ValidationUtil.validateTestStep("User verify total breakdown amount is zero with selected Options for " + basefareType + " Bare Fare on Options Page",
				optionsPage.getOptionTotalContainerAmountTotalText().getText().trim(),TOTAL_AMOUNT);


		//click on drop down link
		optionsPage.getOptionTotalContainerDropDown().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//check for bundle fare
		if(basefareType.equalsIgnoreCase("BundleIt")){
			//verify Flight Flex is pre-selected
			ValidationUtil.validateTestStep("Validating Flight Flex is preselected in Total Amount Breaskdown section on Options Page",
					optionsPage.getOptionTotalContainerFlightFlexText().getText().toUpperCase().trim(),flightFlex);

			//verify Flight Flex is pre-selected
			ValidationUtil.validateTestStep("Validating Flight Flex is Included in Total Amount Breaskdown section on Options Page",
					optionsPage.getOptionTotalContainerFlightFlexAmountText().getText().toUpperCase().trim(),included);

		}

		//verify Flight Flex is pre-selected
		ValidationUtil.validateTestStep("Validating Shortcut Boarding is preselected in Total Amount Breaskdown section on Options Page",
				optionsPage.getOptionTotalContainerShortcutBoardingText().getText().toUpperCase().trim(),shortcutBoarding);

		//verify Flight Flex is pre-selected
		ValidationUtil.validateTestStep("Validating Shortcut Boarding is Included in Total Amount Breaskdown section on Options Page",
				optionsPage.getOptionTotalContainerShortcutBoardingAmountText().getText().toUpperCase().trim(),included);


		//click on drop down link
		optionsPage.getOptionTotalContainerDropDown().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);
	}
	
}
