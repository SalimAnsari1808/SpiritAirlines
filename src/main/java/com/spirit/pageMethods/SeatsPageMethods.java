package com.spirit.pageMethods;

import com.spirit.pageObjects.Header;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.SeatsPage;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class SeatsPageMethods {

	private WebDriver driver;
	private ScenarioContext scenarioContext;
	private SeatsPage seatsPage;
	private Header header;

	public SeatsPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
		this.driver				= driver;
		this.scenarioContext 	= scenarioContext;
		seatsPage 				= pageObjectManager.getSeatsPage();
		header					= pageObjectManager.getHeader();
	}

	// **********************************************************************************************
	// Method : continueWithoutSeats
	// Description: Method is used to click on continue without Seat button
	// Input Arguments: 1.depSeats: Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 12-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	public synchronized void continueWithoutSeats() {
		//wait untill page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//loop through all continue without seat buttons
		for(WebElement continueButton : seatsPage.getContinueWithoutSeatButton()) {
			//check button is visible on screen
			if(continueButton.isDisplayed()) {
				//click continue button
				JSExecuteUtil.clickOnElement(driver,continueButton);

				ValidationUtil.validateTestStep("User clicked on Continue Without Seat Button on Seat Page",true);

				break;
			}
		}

		//Wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//wait till URL appear on web
		WaitUtil.untilPageURLVisible(driver, "book/options");

		//Wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);
	}

	// **********************************************************************************************
	// Method : continueWithSeats
	// Description: Method is used to select the departure seats per pax per leg
	// Input Arguments:
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 12-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void continueWithSeats() {

		//store seat price
		scenarioContext.setContext(Context.SEATS_TOTAL_PRICE,seatsPage.getSeatsTotalPriceText().getText().replace("$", "").trim());

		//loop through all continue with seat buttons
//		for(WebElement continueButton : seatsPage.getContinueWithSeatButton()) {
//			//check button is visible on screen
//			if(continueButton.isDisplayed()) {
//				//click continue button
//				continueButton.click();
//			}
//		}

		seatsPage.getContinueWithSeatButton().get(0).click();

		//wait for seat page url appear
		WaitUtil.untilPageURLVisible(driver, "options");

		//Wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);
	}

	// **********************************************************************************************
	// Method : selectDepartureSeats
	// Description: Method is used to select the departure seats per pax per leg
	// Input Arguments: 1.depSeats: BigFront|Standard|NoSeat|Premium||Standard|Standard|Standard|Standard
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 12-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void selectDepartureSeats(String depSeats) {
		WaitUtil.untilPageLoadComplete(driver);
		//declare variable used in method
		boolean flightLegFound = false;
		String[] departureLegs    = scenarioContext.getContext(Context.AVAILABILITY_DEP_LEG).toString().split(":");
		String[] departureLegSeat = depSeats.split("\\|\\|");

		//loop through all legs details stored on FA page
		for(int legCounter=0;legCounter<departureLegs.length;legCounter++) {
			//loop through all flights legs available on seat page
			for(int depLegs=0;depLegs<seatsPage.getFlightLegsText().size();depLegs++) {
				//check
				if(seatsPage.getFlightLegsText().get(depLegs).getText().equals(departureLegs[legCounter])) {
					//wait for 2 sec
					WaitUtil.untilTimeCompleted(5000);

					//check current flight leg passenger list is not open
					if(!seatsPage.getPassengerDetailListLink().get(depLegs).getAttribute("style").contains("0deg")) {
						//open flight leg passenger list
						seatsPage.getPassengerDetailListLink().get(depLegs).click();

						//set flag flight leg is ready
						flightLegFound=true;
					}else if(seatsPage.getPassengerDetailListLink().get(depLegs).getAttribute("style").contains("0deg")) {
						//set flag flight leg is ready if list is already open
						flightLegFound=true;
					}
				}
			}

			//check flight leg is found on seat page
			if(flightLegFound) {
				//set flight leg flag to false for next flight leg
				flightLegFound=false;

				//call method to select flight type
				selectSeatType(departureLegSeat[legCounter]);
			}
		}//end of flight legs
	}


	public synchronized void selectDepartureSeatsMobile(String depSeats) {
		//declare variable used in method
		boolean flightLegFound = false;
		String[] departureLegs    = scenarioContext.getContext(Context.AVAILABILITY_DEP_LEG).toString().split(":");
		String[] departureLegSeat = depSeats.split("\\|\\|");

		//loop through all legs details stored on FA page
		for(int legCounter=0;legCounter<departureLegs.length;legCounter++) {

			if(seatsPage.getFlightLegsText().get(0).getText().equalsIgnoreCase(departureLegs[legCounter])){
				flightLegFound = true;
			}else {
				JSExecuteUtil.clickOnElement(driver,seatsPage.getSeatPageNextFlightButton());
				WaitUtil.untilTimeCompleted(1500);
			}

			if(flightLegFound){
				selectSeatType(departureLegSeat[legCounter]);
				WaitUtil.untilTimeCompleted(2000);
				flightLegFound = false;
			}
		}
	}

	// **********************************************************************************************
	// Method : selectReturningSeats
	// Description: Method is used to select the return seats per pax per leg
	// Input Arguments: 1.depSeats: BigFront|Standard|NoSeat|Premium||Standard|Standard|Standard|Standard
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 12-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void selectReturningSeats(String retSeats) {
		//declare variable used in method
		boolean flightLegFound = false;
		String[] returnLegs    = scenarioContext.getContext(Context.AVAILABILITY_RET_LEG).toString().split(":");
		String[] returnLegSeat = retSeats.split("\\|\\|");

		//loop through all legs details stored on FA page
		for(int legCounter=0;legCounter<returnLegs.length;legCounter++) {
			//loop through all flights legs available on seat page
			for(int retLegs=0;retLegs<seatsPage.getFlightLegsText().size();retLegs++) {
				//check
				if(seatsPage.getFlightLegsText().get(retLegs).getText().equals(returnLegs[legCounter])) {
					//wait for 2 sec
					WaitUtil.untilTimeCompleted(2000);

					//check current flight leg passenger list is not open
					if(!seatsPage.getPassengerDetailListLink().get(retLegs).getAttribute("style").contains("0deg")) {
						//open flight leg passenger list
						seatsPage.getPassengerDetailListLink().get(retLegs).click();

						//set flag flight leg is ready
						flightLegFound=true;
					}else if(seatsPage.getPassengerDetailListLink().get(retLegs).getAttribute("style").contains("0deg")) {
						//set flag flight leg is ready if list is already open
						flightLegFound=true;
					}
				}
			}

			//check flight leg is found on seat page
			if(flightLegFound) {
				//set flight leg flag to false for next flight leg
				flightLegFound=false;

				//call method to select flight type
				selectSeatType(returnLegSeat[legCounter]);
			}
		}//end of flight legs

	}

	public synchronized void selectReturningSeatsMobile(String retSeats) {
		//declare variable used in method
		boolean flightLegFound = false;
		String[] returnLegs    = scenarioContext.getContext(Context.AVAILABILITY_RET_LEG).toString().split(":");
		String[] returnLegSeat = retSeats.split("\\|\\|");

		//loop through all legs details stored on FA page
		for(int legCounter=0;legCounter<returnLegs.length;legCounter++) {

			if(seatsPage.getFlightLegsText().get(0).getText().equalsIgnoreCase(returnLegs[legCounter])){
				flightLegFound = true;
			}else {
				JSExecuteUtil.clickOnElement(driver,seatsPage.getSeatPageNextFlightButton());
				WaitUtil.untilTimeCompleted(1500);
			}

			if(flightLegFound){
				selectSeatType(returnLegSeat[legCounter]);
				WaitUtil.untilTimeCompleted(2000);
				flightLegFound = false;
			}

		}

	}


	private synchronized void selectSeatType(String perLegSeats) {
		//get seat type for each passenger
		String[] departurePerPaxSeat = perLegSeats.split("\\|");

		//loop through all passenger
		for(int paxList=0;paxList<departurePerPaxSeat.length;paxList++) {
			//seatsPage.getPassengerListText()

			if(!seatsPage.getPassengerListText().get(paxList).getAttribute("class").contains("selected arrow-box")) {
				seatsPage.getPassengerListText().get(paxList).click();
			}

			if(departurePerPaxSeat[paxList].equalsIgnoreCase("BigFront")) {
				selectBigFrontSeat(paxList+1);
			}else if(departurePerPaxSeat[paxList].equalsIgnoreCase("Standard")) {
				selectStandardSeat(paxList+1);
			}else if(departurePerPaxSeat[paxList].equalsIgnoreCase("Premium")) {
				selectPremiumSeat(paxList+1);
			}else if(departurePerPaxSeat[paxList].equalsIgnoreCase("NoSeat")) {
				int optionIndex = TestUtil.getSelectedOptionIndex(seatsPage.getSeatPagePassengerDropDown());

				if(new Select(seatsPage.getSeatPagePassengerDropDown()).getOptions().size()==(optionIndex+1)){
					JSExecuteUtil.clickOnElement(driver,seatsPage.getSeatPageNextFlightButton());
					WaitUtil.untilTimeCompleted(1500);
				}else {
					TestUtil.selectDropDownUsingIndex(seatsPage.getSeatPagePassengerDropDown(),optionIndex+1);
					WaitUtil.untilTimeCompleted(1500);
				}

				//validate Seat is selected
				ValidationUtil.validateTestStep("No Seat is selected for passenger:" + (paxList+1), true);
			}

		}//end of passenger list 
	}

	//Standard-12A-$20
	private synchronized void selectBigFrontSeat(int paxNumber) {
		//loop through all BFS seats
		for(WebElement bigFrontSeat : seatsPage.getBigFrontSeatsGridView()) {
			//check seat is available on seat map
			if(!bigFrontSeat.getAttribute("class").contains("unavailable") && !bigFrontSeat.getAttribute("class").contains("invisible")) {
				//check seat is not selected for another passenger
				if(bigFrontSeat.findElements(By.tagName("div")).size()==0) {
					//select seat
					bigFrontSeat.click();

					//validate Seat is selected
					ValidationUtil.validateTestStep("Big Front Seat is selected for passenger:" + paxNumber, true);

					//break loop
					break;
				}//end of if condition seat is not previously selected
			}//end of if condition seat is available
		}//end of BFS loop
	}

	private synchronized void selectPremiumSeat(int paxNumber) {
		//loop through all Premium seats
		for(WebElement premiumSeat : seatsPage.getPremiumSeatsGridView()) {
			//check seat is available on seat map
			if(!premiumSeat.getAttribute("class").contains("unavailable") && !premiumSeat.getAttribute("class").contains("invisible")) {
				//check seat is not selected for another passenger
				if(premiumSeat.findElements(By.tagName("div")).size()==0) {
					//select seat
					premiumSeat.click();

					WaitUtil.untilPageLoadComplete(driver);

					//accept Exit row
					seatsPage.getAcceptExitRowSeatButton().click();

					//validate seat is selected
					ValidationUtil.validateTestStep("Premium Seat is selected for passenger:" + paxNumber, true);

					//break loop
					break;
				}//end of if condition seat is not previously selected
			}//end of if condition seat is available
		}//end of Premium loop
	}

	private synchronized void selectStandardSeat(int paxNumber) {
		//loop through all Standard seats
		for(WebElement standardSeat : seatsPage.getStandardSeatsGridView()) {
			//check seat is available on seat map
			if(!standardSeat.getAttribute("class").contains("unavailable") && !standardSeat.getAttribute("class").contains("invisible")) {
				//check seat is not selected for another passenger
				if(standardSeat.findElements(By.tagName("div")).size()==0) {
					//select seat
					standardSeat.click();

					//validate seat is selected
					ValidationUtil.validateTestStep("Standard Seat is selected for passenger:" + paxNumber, true);

					//break loop
					break;
				}//end of if condition seat is not previously selected
			}//end of if condition seat is available
		}//end of Premium loop
	}


	//**********************************************************************************************
	// Method : verifySelectedBaseFareSeats
	// Description: Method is used to verify below points
	//				1.Verify Bare Fare in Dynamic Shopping Cart
	//				2.Verify Total Seats Amount after adding Bare Fare bags is Zero
	//				3.Verify Selected Seats in Total Breakdown Section for Bare Fare
	//				4.Verify Included Verbiage for Selected Seats in Total Breakdown Section for Bare Fare
	//				5.Verify Continue Without Seat is not present on Seat Page
	// Input Arguments: basefareType -> BoostIt/BundleIt
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 11-June-2019
	//**********************************************************************************************
	public synchronized void verifySelectedBaseFareSeats(String basefareType, String depSeats, String retSeats) {

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
			ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart on Seats Page", barefareText, BUNDLE_ITINERARY);

			//verify bundle discount amount
			ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart on Seats Page",
					scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), barefareAmount);

		} else if (basefareType.equalsIgnoreCase("BoostIt")) {
			//verify boost discount text
			ValidationUtil.validateTestStep("Verify the Boost It Discount text on Dynamic Shopping Cart on Seats Page", barefareText, BOOST_ITINERARY);

			//verify boost discount amount
			ValidationUtil.validateTestStep("Verify the Boost It Discount Price on Dynamic Shopping Cart on Seats Page",
					scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(), barefareAmount);
		}

		//open dynamic shopping cart
		header.getArrowYourItineraryImage().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//********************************************************
		//*********************Seats Total BreakDown**************
		//********************************************************
		//declare constant used in method
		final String TOTAL_AMOUNT = "$0.00";
		final String SINGLE_SEPARATOR = "\\|";
		final String DOUBLE_SEPARATOR = "\\|\\|";

		//declare variable used in method
		int seatBlockCounter 	= 0;
		int perLegSeatCount		= 0;
		int standardSeatCount 	= 0;
		int premiumSeatCount 	= 0;
		String selectedSeats 	= "";
		String perLegSeat		= "";

		for (int journeySeat = 0; journeySeat < depSeats.split(DOUBLE_SEPARATOR).length; journeySeat++) {

			for (int legSeat = 0; legSeat < depSeats.split(DOUBLE_SEPARATOR)[journeySeat].split(SINGLE_SEPARATOR).length; legSeat++) {
				//System.out.println(depSeats.split(DOUBLE_SEPARATOR)[journeySeat].split(SINGLE_SEPARATOR)[legSeat]);
				if (depSeats.split(DOUBLE_SEPARATOR)[journeySeat].split(SINGLE_SEPARATOR)[legSeat].equalsIgnoreCase("Standard")) {
					standardSeatCount = standardSeatCount + 1;
				} else if (depSeats.split(DOUBLE_SEPARATOR)[journeySeat].split(SINGLE_SEPARATOR)[legSeat].equalsIgnoreCase("Premium")) {
					premiumSeatCount = premiumSeatCount + 1;
				}
			}

			if (premiumSeatCount > 0 && standardSeatCount > 0) {
				selectedSeats = selectedSeats + premiumSeatCount + " PREMIUM|" + standardSeatCount + " STANDARD||";
			} else if (premiumSeatCount > 0 && standardSeatCount == 0) {
				selectedSeats = selectedSeats + premiumSeatCount + " PREMIUM||";
			} else if (premiumSeatCount == 0 && standardSeatCount > 0) {
				selectedSeats = selectedSeats + standardSeatCount + " STANDARD||";
			}

			standardSeatCount = 0;
			premiumSeatCount = 0;

		}

		if (!retSeats.equalsIgnoreCase("NA")) {

			for (int journeySeat = 0; journeySeat < retSeats.split(DOUBLE_SEPARATOR).length; journeySeat++) {

				for (int legSeat = 0; legSeat < retSeats.split(DOUBLE_SEPARATOR)[journeySeat].split(SINGLE_SEPARATOR).length; legSeat++) {
					if (retSeats.split(DOUBLE_SEPARATOR)[journeySeat].split(SINGLE_SEPARATOR)[legSeat].equalsIgnoreCase("Standard")) {
						standardSeatCount = standardSeatCount + 1;
					} else if (retSeats.split(DOUBLE_SEPARATOR)[journeySeat].split(SINGLE_SEPARATOR)[legSeat].equalsIgnoreCase("Premium")) {
						premiumSeatCount = premiumSeatCount + 1;
					}
				}

				if (premiumSeatCount > 0 && standardSeatCount > 0) {
					selectedSeats = selectedSeats + premiumSeatCount + " PREMIUM-" + standardSeatCount + " STANDARD||";
				} else if (premiumSeatCount > 0 && standardSeatCount == 0) {
					selectedSeats = selectedSeats + premiumSeatCount + " PREMIUM||";
				} else if (premiumSeatCount == 0 && standardSeatCount > 0) {
					selectedSeats = selectedSeats + standardSeatCount + " STANDARD||";
				}

				standardSeatCount = 0;
				premiumSeatCount = 0;

			}
		}

		if(selectedSeats.length()>0){
			selectedSeats = selectedSeats.substring(0,selectedSeats.length()-2);
		}

		//check for booking language
		if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("Spanish")){

		}

		//verify total breakdown amount
		ValidationUtil.validateTestStep("User verify total breakdown amount is zero with selected Seats for " + basefareType + " Bare Fare on Seats Page",
				seatsPage.getSeatsTotalPriceText().getText().trim(),TOTAL_AMOUNT);

		//user click on total break down amount
		seatsPage.getSeatsTotalContainerLink().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);


		List<WebElement> seatBlocks = driver.findElements(By.tagName("app-breakdown-section"));

		for(WebElement seatBlock:seatBlocks){
			if(seatBlock.isDisplayed()){
				perLegSeat = selectedSeats.split(DOUBLE_SEPARATOR)[seatBlockCounter];

				List<WebElement> seatTypes = seatBlock.findElements(By.tagName("app-price-section-line"));

				for(WebElement seatType : seatTypes){
					if(seatType.isDisplayed()){

						ValidationUtil.validateTestStep("Verify the Selected Seats in Total breakdown of Seat Page",
								seatType.getText().toUpperCase().trim(), perLegSeat.split(SINGLE_SEPARATOR)[perLegSeatCount]);
						//System.out.println("Actual Seats " + seatType.getText().toUpperCase().trim());
						//System.out.println("Expected Seats " + perLegSeat.split(SINGLE_SEPARATOR)[perLegSeatCount]);
						perLegSeatCount = perLegSeatCount + 1;
					}

				}
				seatBlockCounter = seatBlockCounter + 1;
				perLegSeatCount = 0;
			}

		}

		//********************************************************
		//*****************Continue Without Seat Link*************
		//********************************************************
		//verify for continue without seat
		ValidationUtil.validateTestStep("Verify 'Continue Without Seat' is not displayed on Seat Page",
				!TestUtil.verifyElementDisplayed(seatsPage.getContinueWithoutSeatButton()));

	}

}