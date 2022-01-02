package com.spirit.pageMethods;

import com.spirit.managers.PageMethodManager;
import com.spirit.pageObjects.Header;
import com.spirit.utility.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.BagsPage;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;

import java.util.List;


public class BagsPageMethods {

	private WebDriver driver;
	private PageObjectManager pageObjectManager;
	//private PageMethodManager
	private ScenarioContext scenarioContext;
	private BagsPage bagsPage;
	private Header header;

	public BagsPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
		this.driver				= driver;
		this.pageObjectManager 	= pageObjectManager;
		this.scenarioContext 	= scenarioContext;
		bagsPage 				= pageObjectManager.getBagsPage();
		header					= pageObjectManager.getHeader();
	}

	// **********************************************************************************************
	// Method : selectDepartingBags
	// Description: Method is used to select the departing bags on Bags page
	// Input Arguments: 1.depBags: //Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void selectDepartingBags(String depBags) {
		//declare variable used in method
		String[] passengerBagsType;
		int carryBagCount = 0;
		int checkedBagCount = 0;
		int paxCounter =0;
		String carryCount;
		String carryPrice;
		String checkedCount;
		String checkedPrice;


		//declare constant used in method
		final String CARRY_ON_BAG = "Carry_";
		final String CHECKED_BAG  = "Checked_";
		final String NULL_VALUE	  = "";
		final String DOUBLE_SAPRATOR = "\\|\\|";
		final String SINGLE_SAPRATOR = "\\|";

		//get all the Bags per passenger
		String[] allDepartureBags = depBags.split(DOUBLE_SAPRATOR);

		//loop through all passengers Bags
		for(String perPassengerBags : allDepartureBags) {
			//get bag type and count
			passengerBagsType = perPassengerBags.split(SINGLE_SAPRATOR);

			//loop through bag type to get the bag count
			for(String perPassengerBag:passengerBagsType) {
				//check Bag type to get the count
				if(perPassengerBag.contains(CARRY_ON_BAG)) {
					//get carry bag count
					carryBagCount = Integer.parseInt(perPassengerBag.replace(CARRY_ON_BAG, NULL_VALUE).trim());
				}else if(perPassengerBag.contains(CHECKED_BAG)) {
					//get checked bag count
					checkedBagCount = Integer.parseInt(perPassengerBag.replace(CHECKED_BAG, NULL_VALUE).trim());
				}
			}

			//select Carry-On Bags
			if(carryBagCount>0) {
				//click on add carry-on bag button 
				bagsPage.getDepartingCarryOnPlusButton().get(paxCounter).click();
			}

			//loop through and select all checked bag
			for(int checkedBagCounter=0;checkedBagCounter<=checkedBagCount-1;checkedBagCounter++) {
				//click on add checked bag button
				bagsPage.getDepartingCheckedBagPlusButton().get(paxCounter).click();
			}

			//validate Departure carry-on Bags
			ValidationUtil.validateTestStep("Departure Carry-On Bag is selected for Passenger: "+(paxCounter+1), Integer.parseInt(JSExecuteUtil.getElementTextValue(driver,bagsPage.getDepartingCarryOnBagCounterTextBox().get(paxCounter)))==carryBagCount);

			//validate Departure checked Bags
			ValidationUtil.validateTestStep("Departure Checked Bag is selected for Passenger: "+(paxCounter+1), Integer.parseInt(JSExecuteUtil.getElementTextValue(driver,bagsPage.getDepartingCheckedBagCounterTextBox().get(paxCounter)))==checkedBagCount);

			//Increment passenger counter
			paxCounter = paxCounter + 1;
		}

		//store departing bags information into Global variable
		//loop through all departing bags 
		for(int depBagsCounter=0;depBagsCounter<bagsPage.getDepartingCarryOnBagCounterTextBox().size();depBagsCounter++) {
			//get the carry bag count
			carryCount = JSExecuteUtil.getElementTextValue(driver, bagsPage.getDepartingCarryOnBagCounterTextBox().get(depBagsCounter)).trim();

			//get the selected carry bag price
			carryPrice = bagsPage.getDepartingCarryOnPriceText().get(depBagsCounter).getText().trim().replace("$", "");

			//get the checked bag count
			checkedCount = JSExecuteUtil.getElementTextValue(driver, bagsPage.getDepartingCheckedBagCounterTextBox().get(depBagsCounter));

			//get the checked bag price
			checkedPrice = bagsPage.getDepartingCheckedBagPriceText().get(depBagsCounter).getText().trim().replace("$", "");

			//check value is already stored in global variable
			if(!scenarioContext.isContains(Context.BAGS_DEP_BAGS)) {
				//set new value in global variable
				scenarioContext.setContext(Context.BAGS_DEP_BAGS, carryCount + "-" + carryPrice + "|" + checkedCount + "-" + checkedPrice);
			}else {
				//add new value in global variable
				scenarioContext.setContext(Context.BAGS_DEP_BAGS, scenarioContext.getContext(Context.BAGS_DEP_BAGS).toString() + "||" + carryCount + "-" + carryPrice + "|" + checkedCount + "-" + checkedPrice);
			}

		}
	}

	// **********************************************************************************************
	// Method : selectReturnBags
	// Description: Method is used to select the returning bags on Bags page
	// Input Arguments: 1.retBags: //Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void selectReturnBags(String retBags) {
		//declare variable used in method
		String[] passengerBagsType;
		int carryBagCount = 0;
		int checkedBagCount = 0;
		int paxCounter =0;
		String carryCount;
		String carryPrice;
		String checkedCount;
		String checkedPrice;

		//declare constant used in method
		final String CARRY_ON_BAG = "Carry_";
		final String CHECKED_BAG  = "Checked_";
		final String NULL_VALUE	  = "";
		final String DOUBLE_SAPRATOR = "\\|\\|";
		final String SINGLE_SAPRATOR = "\\|";

		//get all the Bags per passenger
		String[] allReturnBags = retBags.split(DOUBLE_SAPRATOR);

		//loop through all passengers Bags
		for(String perPassengerBags : allReturnBags) {
			//get bag type and count
			passengerBagsType = perPassengerBags.split(SINGLE_SAPRATOR);

			//loop through bag type to get the bag count
			for(String perPassengerBag:passengerBagsType) {
				//check Bag type to get the count
				if(perPassengerBag.contains(CARRY_ON_BAG)) {
					//get carry bag count
					carryBagCount = Integer.parseInt(perPassengerBag.replace(CARRY_ON_BAG, NULL_VALUE).trim());
				}else if(perPassengerBag.contains(CHECKED_BAG)) {
					//get checked bag count
					checkedBagCount = Integer.parseInt(perPassengerBag.replace(CHECKED_BAG, NULL_VALUE).trim());
				}
			}

			//select Carry-On Bags
			if(carryBagCount>0) {
				//click on add carry-on bag button 
				bagsPage.getReturningCarryOnPlusButton().get(paxCounter).click();
			}

			//loop through and select all checked bag
			for(int checkedBagCounter=0;checkedBagCounter<=checkedBagCount-1;checkedBagCounter++) {
				//click on add checked bag button
				bagsPage.getReturningCheckedBagPlusButton().get(paxCounter).click();
			}

			//validate Return carry-on Bags
			ValidationUtil.validateTestStep("Return Carry-On Bag is selected for Passenger: "+(paxCounter+1), Integer.parseInt(JSExecuteUtil.getElementTextValue(driver,bagsPage.getReturningCarryOnCounterTextBox().get(paxCounter)))==carryBagCount);

			//validate Return checked Bags
			ValidationUtil.validateTestStep("Return Checked Bag is selected for Passenger: "+(paxCounter+1), Integer.parseInt(JSExecuteUtil.getElementTextValue(driver,bagsPage.getReturningCheckedBagCounterTextBox().get(paxCounter)))==checkedBagCount);

			//Increment passenger counter
			paxCounter = paxCounter + 1;
		}

		//store returning bags information into Global variable
		//loop through all returning bags 
		for(int retBagsCounter=0;retBagsCounter<bagsPage.getReturningCarryOnCounterTextBox().size();retBagsCounter++) {
			//get the carry bag count
			carryCount = JSExecuteUtil.getElementTextValue(driver, bagsPage.getReturningCarryOnCounterTextBox().get(retBagsCounter)).trim();

			//get the selected carry bag price
			carryPrice = bagsPage.getReturningCarryOnPriceText().get(retBagsCounter).getText().trim().replace("$", "");

			//get the checked bag count
			checkedCount = JSExecuteUtil.getElementTextValue(driver, bagsPage.getReturningCheckedBagCounterTextBox().get(retBagsCounter));

			//get the checked bag price
			checkedPrice = bagsPage.getReturningCheckedBagPriceText().get(retBagsCounter).getText().trim().replace("$", "");

			//check value is already stored in global variable
			if(!scenarioContext.isContains(Context.BAGS_RET_BAGS)) {
				//set new value in global variable
				scenarioContext.setContext(Context.BAGS_RET_BAGS, carryCount + "-" + carryPrice + "|" + checkedCount + "-" + checkedPrice);
			}else {
				//add new value in global variable
				scenarioContext.setContext(Context.BAGS_RET_BAGS, scenarioContext.getContext(Context.BAGS_RET_BAGS).toString() + "||" + carryCount + "-" + carryPrice + "|" + checkedCount + "-" + checkedPrice);
			}

		}
	}

	// **********************************************************************************************
	// Method : continueWithoutSelectingBags
	// Description: Method is used to continue without bags
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void continueWithoutSelectingBags() {
		//loop through to click on continue Without Bags link
		WaitUtil.untilPageLoadComplete(driver);
		for(WebElement continueWithoutBags : bagsPage.getContinueWithoutBagsButton()) {
			if(continueWithoutBags.isDisplayed()) {
				continueWithoutBags.click();

				break;
			}
		}

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//wait for 1 sec
		WaitUtil.untilTimeCompleted(1000);

		//click on i dont need bag button
		bagsPage.getAreYouSurePopUpIDontNeedBagButton().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//wait for seat page url appear in application
		WaitUtil.untilPageURLVisible(driver, "book/seats");

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//validate user continue without bags
		ValidationUtil.validateTestStep("User not selected any Bags on Bags Page", true);

	}

	// **********************************************************************************************
	// Method : selectBagsFare
	// Description: Method is used to select the Standard and Member fares on Bags page
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-Mar-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 11-Mar-2019
	// **********************************************************************************************
	public synchronized void selectBagsFare(String memberFareType) {
		//check the spirit fare to be selected on FA page
		if(memberFareType.equalsIgnoreCase("Standard")) {
			//check for safari browser
			if(scenarioContext.getContext(Context.HOMEPAGE_BROWSER).toString().equalsIgnoreCase("Safari")){
				//select standard fare
				JSExecuteUtil.scrollDown(driver,"10");

				WaitUtil.untilTimeCompleted(2000);
			}
			//select standard fare
			bagsPage.getContinueWithStandardBagsContainerContinueButton().click();

			//validate standard fare is selected
			ValidationUtil.validateTestStep("User selected Standard fare on BagsPage", true);
		}else if(memberFareType.equalsIgnoreCase("Member")) {
			//check for safari browser
			if(scenarioContext.getContext(Context.HOMEPAGE_BROWSER).toString().equalsIgnoreCase("Safari")){
				//select standard fare
				JSExecuteUtil.scrollDown(driver,"10");

				WaitUtil.untilTimeCompleted(2000);
			}

			//select member fare
			bagsPage.getContinueWith9FCBagsContainerContinueButton().click();

			//validate member fare is selected
			ValidationUtil.validateTestStep("User selected Member fare on Bags Page", true);
		}

		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);

		//wait till bags page appear on web
		//WaitUtil.untilPageURLVisible(driver, "book/bags");
	}

	// **********************************************************************************************
	// Method : checkInContiueWithBag
	// Description: Method is used to select continue with Bags on Check In Bags Page
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 26-Mar-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 26-Mar-2019
	// **********************************************************************************************
	public synchronized void checkInContiueWithBag() {
		bagsPage.getContinueWithBagsButton().click();

		//wait tillpage load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//wait for 1 sec
		WaitUtil.untilTimeCompleted(1000);
		//store bags information purchased till bags page
	}

	// **********************************************************************************************
	// Method : checkInContiueWithOutBag
	// Description: Method is used to select continue with Bags on Check In Bags Page
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 26-Mar-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 26-Mar-2019
	// **********************************************************************************************
	public synchronized void continueWithOutChangesBag() {

		for(WebElement continueButton : bagsPage.getContinueWithOutChangesButton()) {
			if(continueButton.isDisplayed()) {
				JSExecuteUtil.clickOnElement(driver, continueButton);
				break;
			}
		}

		//store bags information purchased till bags page
	}

	// **********************************************************************************************
	// Method : checkInPurchaseSeatPopup
	// Description: Method is used to select seat required to purchase on seat page or not
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 26-Mar-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 26-Mar-2019
	// **********************************************************************************************
	public synchronized void checkInPurchaseSeatPopup(String seatRequired) {
		//wait tillpage load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//wait for 1 sec
		WaitUtil.untilTimeCompleted(1000);

		//check seat is required
		if(seatRequired.equalsIgnoreCase("Required")) {
			//click on purchase seat
			bagsPage.getCheckInPurchaseSeatPopupPurchaseMySeatsButton().click();
		}else if(seatRequired.equalsIgnoreCase("NotRequired")) {
			//click on without purchase seat
			bagsPage.getCheckInPurchaseSeatPopupDontPurchaseMySeatsButton().click();
		}

		//wait tillpage load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//wait for 1 sec
		WaitUtil.untilTimeCompleted(1000);
	}

	// **********************************************************************************************
	// Method : verifyVATTax
	// Description: Method is used to select continue with Bags on Check In Bags Page
	// Input Arguments: NA
	// Return: NA
	// Created By : Kartik Chauhan
	// Created On : 11-Apr-2019
	// Reviewed By: Salim Ansari
	// Reviewed On: 11-Apr-2019
	// **********************************************************************************************
	public synchronized void verifyVATTax(String journey, String totalVAT) {
		//declare element use in method
		WebElement checkedBag, carryBag, appliedVAT;

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//Verify Vat Tax section under TOTAL BAGS AMOUNT Container
		bagsPage.getBagsTotalContainerLink().click();

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//declare variable used in method
		double carryOnBagsPriceBreakdown;
		double checkedOnBagsPriceBreakdown;
		double vatPriceOnBag;

		if(journey.equalsIgnoreCase("Dep")){
			//get the dep bags
			checkedBag 	= bagsPage.getOutboundJourneyBreakdownCheckedBagTotalPriceText();
			carryBag 	= bagsPage.getOutboundJourneyBreakdownCarryOnBagTotalPriceText();
			appliedVAT	= bagsPage.getOutboundJourneyBreakdownVatTaxPriceText();
		}else if(journey.equalsIgnoreCase("Ret")){
			//get the ret bags
			checkedBag 	= bagsPage.getReturnJourneyBreakdownCheckedBagTotalPriceText();
			carryBag 	= bagsPage.getReturnJourneyBreakdownCarryOnBagTotalPriceText();
			appliedVAT	= bagsPage.getReturnJourneyBreakdownVatTaxPriceText();
		}else{
			throw new RuntimeException("Vat is not calculated for Multicity");
		}

		//get the value of carry on Bags Price
		carryOnBagsPriceBreakdown = Double.parseDouble(checkedBag.getText().replace("$", "").trim());

		//get the value of Checked Bags Price
		checkedOnBagsPriceBreakdown = Double.parseDouble(carryBag.getText().replace("$", "").trim());

		//apply Vat on Total Bags Price on Bags Total Breakdown
		vatPriceOnBag = Double.parseDouble(TestUtil.convertTo2DecimalValue(((carryOnBagsPriceBreakdown + checkedOnBagsPriceBreakdown) * Integer.parseInt(totalVAT)/100))) ;

		//validate VAT price displaying for Departure is correct
		ValidationUtil.validateTestStep("Total VAT " + vatPriceOnBag + " applied on Bags Page", Double.parseDouble(appliedVAT.getText().replace("$", "").trim())==vatPriceOnBag);

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(1000);

		//store in global variable
		if(journey.equalsIgnoreCase("Dep")){
			//store into global varaible
			scenarioContext.setContext(Context.BAGS_DEP_VAT,vatPriceOnBag );
		}else if(journey.equalsIgnoreCase("Ret")){
			//store into global varaible
			scenarioContext.setContext(Context.BAGS_RET_VAT,vatPriceOnBag );
		}else{
			throw new RuntimeException("Vat is not calculated for Multicity");
		}

	}

	//**********************************************************************************************
	// Method : continueWithSelectingBags
	// Description: Method is used to continue bags
	// Input Arguments: NA
	// Return: NA
	// Created By : Sunny sok
	// Created On : 19-Apr-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void continueWithSelectingBags() {

		//get bags total
		String bagsTotal = bagsPage.getBagsTotalContainerAmountTotalText().getText().trim().replace("$", "");

		if(TestUtil.verifyElementDisplayed(bagsPage.getWayToGoSaverText())) {
			String dfcSaveUpToPrice = bagsPage.getWayToGoSaverText().getText().trim();

			//add new value in global variable
			scenarioContext.setContext(Context.BAGS_9DFC_SAVING, dfcSaveUpToPrice.substring(dfcSaveUpToPrice.indexOf("$")).trim());
		}
		//add new value in global variable
		scenarioContext.setContext(Context.BAGS_TOTAL_PRICE, bagsTotal);

		//loop through to click on continue With Bags link
		bagsPage.getContinueWithBagsButton().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//wait for seat page url appear in application
		WaitUtil.untilPageURLVisible(driver, "book/seats");

	}



	//**********************************************************************************************
	// Method : verifySelectedBaseFareBags
	// Description: Method is used to verify below points
	//				1.Verify Bare Fare in Dynamic Shopping Cart
	//				2.Verify Pre-Selected Bags in Bags Section for Bare Fare
	//				3.Verify Included verbiage for Pre-Selected Bags in Bags Section for Bare Fare
	//				4.Verify Total Bags Amount after adding Bare Fare bags is Zero
	//				5.Verify Pre-Selected Bags in Total Breakdown Section for Bare Fare
	//				6.Verify Included Verbiage for Pre-Selected Bags in Total Breakdown Section for Bare Fare
	// Input Arguments: basefareType -> BoostIt/BundleIt
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 11-June-2019
	//**********************************************************************************************
	public synchronized void verifySelectedBaseFareBags(String basefareType){

		//********************************************************
		//****************Bare Fare From Shopping Cart************
		//********************************************************
		//declare constant used in method
		final String PASSENGER_URL 		= "book/passenger";
		final String BUNDLE_ITINERARY   = "Bundle It Discount";
		final String BOOST_ITINERARY    = "Boost It Discount";

		//declare variable used in method
		String barefareText 	= "";
		String barefareAmount 	= "";

		//wait till passenger page appear on web application
		WaitUtil.untilPageURLVisible(driver, PASSENGER_URL);

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//open dynamic shopping cart
		header.getArrowYourItineraryImage().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//get the bare fare text and amount
		barefareText 	= header.getBareFareDiscountItineraryText().getText();
		barefareAmount	= header.getBareFareDiscountPriceItineraryText().getText().replace("-","");

		//check for bare fare type
		if(basefareType.equalsIgnoreCase("BundleIt")){
			//verify bundle discount text
			ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart on Bags Page", barefareText,BUNDLE_ITINERARY);

			//verify bundle discount amount
			ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart on Bags Page",
					scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), barefareAmount);

		}else if(basefareType.equalsIgnoreCase("BoostIt")){
			//verify boost discount text
			ValidationUtil.validateTestStep("Verify the Boost It Discount text on Dynamic Shopping Cart on Bags Page", barefareText,BOOST_ITINERARY);

			//verify boost discount amount
			ValidationUtil.validateTestStep("Verify the Boost It Discount Price on Dynamic Shopping Cart on Bags Page",
					scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(), barefareAmount);
		}

		//********************************************************
		//*********************Pre-Selected Bags******************
		//********************************************************
		//declare constnat used in method
		final String BAGS_PRESELECTED    		= "1";
		final String BAGS_INCLUDED_ENGLISH      = "Included";
		final String BAGS_INCLUDED_SPANISH		= "incluido";

		//declare variable used in method
		String bagsIncluded = "";

		//get the carry bags data
		List<WebElement> carryBagCounterBox   = driver.findElements(By.xpath("//div[@data-qa='carry-on-bag']//div[@class='counter-number']//input"));
		List<WebElement> carryBagIncluded     = driver.findElements(By.xpath("//div[@data-qa='carry-on-bag']/div[4]/p"));

		//get the checked bags data
		List<WebElement> checkedBagCounterBox = driver.findElements(By.xpath("//div[@data-qa='checked-bag']//div[@class='counter-number']//input"));
		List<WebElement> checkedBagIncluded   = driver.findElements(By.xpath("//div[@data-qa='checked-bag']/div[4]/p"));

		//check for booking language
		if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
			bagsIncluded = BAGS_INCLUDED_ENGLISH;
		}else if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("Spanish")){
			bagsIncluded = BAGS_INCLUDED_SPANISH;
		}

		//verify carry bags is only pre-selected for bundle fare
		if(basefareType.equalsIgnoreCase("BundleIt")){
			//verify Checked-Bag bags are pre selected and included for Boost It and Bundle It bare fare
			for(int i = 0; i < carryBagCounterBox.size(); i ++) {
				// Verifying Carry-on is preselected
				ValidationUtil.validateTestStep("Verifying Carry On is preselected for " + basefareType + " Bare Fare on Bags Page",
						JSExecuteUtil.getElementTextValue(driver, carryBagCounterBox.get(i)),BAGS_PRESELECTED);

				//Verifying Carry On price is included
				ValidationUtil.validateTestStep("Verifying Carry On price is included for " + basefareType + " Bare Fare on Bags Page", carryBagIncluded.get(i).getText(),bagsIncluded);
			}
		}

		//verify Checked-Bag bags are pre selected and included for Boost It and Bundle It bare fare
		for(int i = 0; i < checkedBagCounterBox.size(); i ++) {
			// Verifying Checked Bag is preselected
			ValidationUtil.validateTestStep("Verifying 1 checked bag is preselected for " + basefareType + " Bare Fare on Bags Page",
					JSExecuteUtil.getElementTextValue(driver, checkedBagCounterBox.get(i)),BAGS_PRESELECTED);

			//Verifying Checked Bag price is included
			ValidationUtil.validateTestStep("Verifying Checked Bag price is included for " + basefareType + " Bare Fare on Bags Page", checkedBagIncluded.get(i).getText(),bagsIncluded);
		}

		//********************************************************
		//*********************Bags Total BreakDown***************
		//********************************************************
		//declare constant used in method
		final String TOTAL_AMOUNT = "$0.00";
		final String BUNDLE_BAGS_ENGLISH	= "X CARRY-ON|X CHECKED BAGS|X OVERWEIGHT BAG 41-50LBS (18-23KG)|X CARRY-ON|X CHECKED BAGS|X OVERWEIGHT BAG 41-50LBS (18-23KG)";
		final String BUNDLE_BAGS_SPANISH	= "X EQUIPAJE DE MANO|X EQUIPAJES REGISTRADOS|X OVERWEIGHT BAG 41-50LBS (18-23KG)|X EQUIPAJE DE MANO|X EQUIPAJES REGISTRADOS|X OVERWEIGHT BAG 41-50LBS (18-23KG)";
		final String BOOST_BAGS_ENGLISH		= "X CHECKED BAGS|X OVERWEIGHT BAG 41-50LBS (18-23KG)|X CHECKED BAGS|X OVERWEIGHT BAG 41-50LBS (18-23KG)";
		final String BOOST_BAGS_SPANISH		= "X EQUIPAJES REGISTRADOS|X OVERWEIGHT BAG 41-50LBS (18-23KG)|X EQUIPAJES REGISTRADOS|X OVERWEIGHT BAG 41-50LBS (18-23KG)";
		final String INCLUDED_BAGS			= "INCLUDED";
		final String SINGLE_SEPARATOR		= "\\|";

		//declare variable used in method
		String selectedBags = "";
		int totalPassenger	= 0;

		//get pax details from global variables
		int intAdultCount  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString());
		int intChildCount  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString());
		int intInfantSeat  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());

		totalPassenger = intAdultCount + intChildCount + intInfantSeat;

		//check for fare type
		if(basefareType.equalsIgnoreCase("BundleIt")){
			//check for booking language
			if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
				selectedBags = BUNDLE_BAGS_ENGLISH.replaceAll("X",Integer.toString(totalPassenger));
			}else if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("Spanish")){
				selectedBags = BUNDLE_BAGS_SPANISH.replaceAll("X",Integer.toString(totalPassenger));
			}
		}else if(basefareType.equalsIgnoreCase("BoostIt")){
			//check for booking language
			if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
				selectedBags = BOOST_BAGS_ENGLISH.replaceAll("X",Integer.toString(totalPassenger));
			}else if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("Spanish")){
				selectedBags = BOOST_BAGS_SPANISH.replaceAll("X",Integer.toString(totalPassenger));
			}
		}

		//verify total breakdown amount
		ValidationUtil.validateTestStep("User verify total breakdown amount is zero with Preselected Bags for " + basefareType + " Bare Fare on Bags Page",
				bagsPage.getBagsTotalContainerAmountTotalText().getText().trim(),TOTAL_AMOUNT);

		//user click on total break down amount
		bagsPage.getBagsTotalContainerLink().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//get all bags data in total breakdown section
		List<WebElement> includedBags	= driver.findElements(By.xpath("//div[@data-qa='total-options-item']"));
		List<WebElement> includedPrice	= driver.findElements(By.xpath("//div[@data-qa='total-options-price']"));

		//loop through all pre-selected bags in total breakdown section
		for(int bagsCounter=0;bagsCounter<includedBags.size();bagsCounter++){
			ValidationUtil.validateTestStep("User verify the included bags type in Total Breakdown section on Bags Page",
					includedBags.get(bagsCounter).getText().toUpperCase().trim(), selectedBags.split(SINGLE_SEPARATOR)[bagsCounter]);
		}

		for(int bagsPrice=0;bagsPrice<includedPrice.size();bagsPrice++){
			ValidationUtil.validateTestStep("User verify the included bags type in Total Breakdown section on Bags Page",
					includedPrice.get(bagsPrice).getText().toUpperCase().trim(), INCLUDED_BAGS);
		}

		//user click on total break down amount
		bagsPage.getBagsTotalContainerLink().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

	}

	//**********************************************************************************************
	// Method : verifySelectedBaseFareBagsWithVAT
	// Description: Method is used to verify below points
	//				1.Verify Bare Fare in Dynamic Shopping Cart
	//				2.Verify Pre-Selected Bags in Bags Section for Bare Fare
	//				3.Verify Included verbiage for Pre-Selected Bags in Bags Section for Bare Fare
	// Input Arguments: basefareType -> BoostIt/BundleIt
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 11-June-2019
	//**********************************************************************************************
	public synchronized void verifySelectedBaseFareBagsWithVAT(String basefareType){

		//********************************************************
		//****************Bare Fare From Shopping Cart************
		//********************************************************
		//declare constant used in method
		final String PASSENGER_URL 		= "book/passenger";
		final String BUNDLE_ITINERARY   = "Bundle It Discount";
		final String BOOST_ITINERARY    = "Boost It Discount";

		//declare variable used in method
		String barefareText 	= "";
		String barefareAmount 	= "";

		//wait till passenger page appear on web application
		WaitUtil.untilPageURLVisible(driver, PASSENGER_URL);

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//open dynamic shopping cart
		header.getArrowYourItineraryImage().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//get the bare fare text and amount
		barefareText 	= header.getBareFareDiscountItineraryText().getText();
		barefareAmount	= header.getBareFareDiscountPriceItineraryText().getText().replace("-","");

		//check for bare fare type
		if(basefareType.equalsIgnoreCase("BundleIt")){
			//verify bundle discount text
			ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart on Bags Page", barefareText,BUNDLE_ITINERARY);

			//verify bundle discount amount
			ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart on Bags Page",
					scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), barefareAmount);

		}else if(basefareType.equalsIgnoreCase("BoostIt")){
			//verify boost discount text
			ValidationUtil.validateTestStep("Verify the Boost It Discount text on Dynamic Shopping Cart on Bags Page", barefareText,BOOST_ITINERARY);

			//verify boost discount amount
			ValidationUtil.validateTestStep("Verify the Boost It Discount Price on Dynamic Shopping Cart on Bags Page",
					scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(), barefareAmount);
		}

		//********************************************************
		//*********************Pre-Selected Bags******************
		//********************************************************
		//declare constnat used in method
		final String BAGS_PRESELECTED    		= "1";
		final String BAGS_INCLUDED_ENGLISH      = "Included";
		final String BAGS_INCLUDED_SPANISH		= "incluido";

		//declare variable used in method
		String bagsIncluded = "";

		//get the carry bags data
		List<WebElement> carryBagCounterBox   = driver.findElements(By.xpath("//div[@data-qa='carry-on-bag']//div[@class='counter-number']//input"));
		List<WebElement> carryBagIncluded     = driver.findElements(By.xpath("//div[@data-qa='carry-on-bag']/div[4]/p"));

		//get the checked bags data
		List<WebElement> checkedBagCounterBox = driver.findElements(By.xpath("//div[@data-qa='checked-bag']//div[@class='counter-number']//input"));
		List<WebElement> checkedBagIncluded   = driver.findElements(By.xpath("//div[@data-qa='checked-bag']/div[4]/p"));

		//check for booking language
		if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
			bagsIncluded = BAGS_INCLUDED_ENGLISH;
		}else if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("Spanish")){
			bagsIncluded = BAGS_INCLUDED_SPANISH;
		}

		//verify carry bags is only pre-selected for bundle fare
		if(basefareType.equalsIgnoreCase("BundleIt")){
			//verify Checked-Bag bags are pre selected and included for Boost It and Bundle It bare fare
			for(int i = 0; i < carryBagCounterBox.size(); i ++) {
				// Verifying Carry-on is preselected
				ValidationUtil.validateTestStep("Verifying Carry On is preselected for " + basefareType + " Bare Fare on Bags Page",
						JSExecuteUtil.getElementTextValue(driver, carryBagCounterBox.get(i)),BAGS_PRESELECTED);

				//Verifying Carry On price is included
				ValidationUtil.validateTestStep("Verifying Carry On price is included for " + basefareType + " Bare Fare on Bags Page", carryBagIncluded.get(i).getText(),bagsIncluded);
			}
		}

		//verify Checked-Bag bags are pre selected and included for Boost It and Bundle It bare fare
		for(int i = 0; i < checkedBagCounterBox.size(); i ++) {
			// Verifying Checked Bag is preselected
			ValidationUtil.validateTestStep("Verifying 1 checked bag is preselected for " + basefareType + " Bare Fare on Bags Page",
					JSExecuteUtil.getElementTextValue(driver, checkedBagCounterBox.get(i)),BAGS_PRESELECTED);

			//Verifying Checked Bag price is included
			ValidationUtil.validateTestStep("Verifying Checked Bag price is included for " + basefareType + " Bare Fare on Bags Page", checkedBagIncluded.get(i).getText(),bagsIncluded);
		}

	}


	//**********************************************************************************************
	// Method : verifySelectedMilitaryBags
	// Description: Method is used to verify below points
	//				1.Verify Bare Fare in not added into Dynamic Shopping Cart
	//				2.Verify User can select 1-Carry + 2 Checked Bags for free
	//				3.Verify Selected Bags in Total Breakdown Section for Bare Fare
	//				4.Verify Included Verbiage for Selected Bags in Total Breakdown Section for Bare Fare
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 17-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 17-June-2019
	//**********************************************************************************************
	public synchronized void verifySelectedMilitaryBags(){
		//********************************************************
		//****************Bare Fare From Shopping Cart************
		//********************************************************
		//declare constant used in method
		final String PASSENGER_URL 		= "book/passenger";
		final String BUNDLE_ITINERARY   = "Bundle It Discount";
		final String BOOST_ITINERARY    = "Boost It Discount";

		//declare variable used in method
		String barefareText 	= "";
		String barefareAmount 	= "";

		//wait till passenger page appear on web application
		WaitUtil.untilPageURLVisible(driver, PASSENGER_URL);

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//open dynamic shopping cart
		header.getArrowYourItineraryImage().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//Verify Bare Fare is not appear
		ValidationUtil.validateTestStep("User verify Bare Fare is not appear in Dynamic Shopping Cart on Bags Page",
				!TestUtil.verifyElementDisplayed(driver,By.xpath(header.xpath_BareFareDiscountItineraryText)));

		//close dynamic shopping cart
		header.getArrowYourItineraryImage().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//********************************************************
		//********************Select Military Bags****************
		//********************************************************
		final String SELECT_BAGS        = "Carry_1|Checked_2";

		//declare variable used in method
		int totalPassenger 		= 0;
		String totalPaxBags		= "";

		//get pax details from global variables
		int intAdultCount  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString());
		int intChildCount  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString());
		int intInfantSeat  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());

		//get total passenger count
		totalPassenger = intAdultCount + intChildCount + intInfantSeat;

		//loop through all passengers
		for(int paxCount=0;paxCount<totalPassenger;paxCount++){
			if(paxCount==0){
				totalPaxBags = SELECT_BAGS;
			}else{
				totalPaxBags = totalPaxBags + "||" + SELECT_BAGS;
			}
		}

		//select departing bags
		selectDepartingBags(totalPaxBags);

		//check for RoundTrip
		if(scenarioContext.getContext(Context.HOMEPAGE_TRIP_TYPE).toString().equalsIgnoreCase("RoundTrip")){
			//select return bags
			selectReturnBags(totalPaxBags);
		}

		//declare constnat used in method
		final String BAGS_CARRY   				= "1";
		final String BAGS_CHECKED				= "2";
		final String BAGS_PRICE				    = "$0.00";

		//get the carry bags data
		List<WebElement> carryBagCounterBox   = driver.findElements(By.xpath("//div[@data-qa='carry-on-bag']//div[@class='counter-number']//input"));
		List<WebElement> carryBagIncluded     = driver.findElements(By.xpath("//div[@data-qa='carry-on-bag']/div[4]/p"));

		//get the checked bags data
		List<WebElement> checkedBagCounterBox = driver.findElements(By.xpath("//div[@data-qa='checked-bag']//div[@class='counter-number']//input"));
		List<WebElement> checkedBagIncluded   = driver.findElements(By.xpath("//div[@data-qa='checked-bag']/div[4]/p"));


		//verify Checked-Bag bags are pre selected and included for Boost It and Bundle It bare fare
		for(int i = 0; i < carryBagCounterBox.size(); i ++) {
			// Verifying Carry-on is preselected
			ValidationUtil.validateTestStep("Verifying 1 Carry-On is Selected with Military Passenger on Bags Page",
					JSExecuteUtil.getElementTextValue(driver, carryBagCounterBox.get(i)),BAGS_CARRY);

			//Verifying Carry On price is included
			ValidationUtil.validateTestStep("Verifying 1 Carry-On is free with Military Passenger on Bags Page", carryBagIncluded.get(i).getText(),BAGS_PRICE);
		}


		//verify Checked-Bag bags are pre selected and included for Boost It and Bundle It bare fare
		for(int i = 0; i < checkedBagCounterBox.size(); i ++) {
			// Verifying Checked Bag is preselected
			ValidationUtil.validateTestStep("Verifying 1 Checked is Selected with Military Passenger on Bags Page",
					JSExecuteUtil.getElementTextValue(driver, checkedBagCounterBox.get(i)),BAGS_CHECKED);

			//Verifying Checked Bag price is included
			ValidationUtil.validateTestStep("Verifying 1 Checked is free with Military Passenger on Bags Page", checkedBagIncluded.get(i).getText(),BAGS_PRICE);
		}

//********************************************************
		//*********************Bags Total BreakDown***************
		//********************************************************
		//declare constant used in method
		final String TOTAL_AMOUNT 			= "$0.00";
		final String BUNDLE_BAGS_ENGLISH	= "X CARRY-ON|Z CHECKED BAGS|X CARRY-ON|Z CHECKED BAGS";
		final String BUNDLE_BAGS_SPANISH	= "X EQUIPAJE DE MANO|Z EQUIPAJES REGISTRADOS|X EQUIPAJE DE MANO|Z EQUIPAJES REGISTRADOS";
		final String INCLUDED_BAGS			= "INCLUDED";
		final String SINGLE_SEPARATOR		= "\\|";

		//declare variable used in method
		String selectedBags = "";

		//check for booking language
		if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
			selectedBags = BUNDLE_BAGS_ENGLISH.replaceAll("X",Integer.toString(totalPassenger)).replaceAll("Z",Integer.toString(totalPassenger*2));
		}else if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("Spanish")){
			selectedBags = BUNDLE_BAGS_SPANISH.replaceAll("X",Integer.toString(totalPassenger)).replaceAll("Z",Integer.toString(totalPassenger*2));
		}

		//verify total breakdown amount
		ValidationUtil.validateTestStep("User verify total breakdown amount is zero with Military Bags on Bags Page",
				bagsPage.getBagsTotalContainerAmountTotalText().getText().trim(),TOTAL_AMOUNT);

		//user click on total break down amount
		bagsPage.getBagsTotalContainerLink().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//get all bags data in total breakdown section
		List<WebElement> includedBags	= driver.findElements(By.xpath("//div[@data-qa='total-options-item']"));
		List<WebElement> includedPrice	= driver.findElements(By.xpath("//div[@data-qa='total-options-price']"));

		//loop through all pre-selected bags in total breakdown section
		for(int bagsCounter=0;bagsCounter<includedBags.size();bagsCounter++){
			ValidationUtil.validateTestStep("User verify the included bags type in Total Breakdown section on Bags Page",
					includedBags.get(bagsCounter).getText().toUpperCase().trim(), selectedBags.split(SINGLE_SEPARATOR)[bagsCounter]);
		}

		for(int bagsPrice=0;bagsPrice<includedPrice.size();bagsPrice++){
			ValidationUtil.validateTestStep("User verify the included bags type in Total Breakdown section on Bags Page",
					includedPrice.get(bagsPrice).getText().toUpperCase().trim(), INCLUDED_BAGS);
		}

		//user click on total break down amount
		bagsPage.getBagsTotalContainerLink().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);


	}
}
