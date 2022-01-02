package com.spirit.pageMethods;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.CardDetailsData;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.MemberEnrollmentPage;
import com.spirit.pageObjects.PaymentPage;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Test;


public class PaymentPageMethods {
	
	private WebDriver driver;
	private ScenarioContext scenarioContext;
	private PaymentPage paymentPage;
	private MemberEnrollmentPage memberEnrollmentPage;

	
	public PaymentPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
		this.driver				= driver;
		this.scenarioContext  	= scenarioContext;
		paymentPage 			= pageObjectManager.getPaymentPage();
		memberEnrollmentPage	= pageObjectManager.getMemberEnrollmentPage();
	}

	// **********************************************************************************************
	// Method : checkBought9FCMembership
	// Description: Method is used to check if any 9FC membership is bought when login with FS account
	// Input Arguments: travelGuardSelection: Required/NotRequired
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	private void checkBought9FCMembership(){
		if(scenarioContext.isContains(Context.HOMEPAGE_LOGINTYPE)){
			if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_ExclusiveMembershipLabel))){
				ValidationUtil.validateTestStep("User cannot buy 9DFC Exclusive Membership with login FS account",false);
			}else if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_TrialMembershipLabel))){
				if(JSExecuteUtil.getElementBeforeProperty(driver,paymentPage.getTrialMembershipLabel(),"background-color").equals("rgb(0, 123, 255)")){
					ValidationUtil.validateTestStep("User cannot buy 9DFC Trail Membership with login FS account",false);
				}
			}
		}
	}

	// **********************************************************************************************
	// Method : calculateTravelGuard
	// Description: Method is used to select travel Guard on TG section
	// Input Arguments: travelGuardSelection: Required/NotRequired
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void calculateTravelGuard() {

		/*I. Conditions that need to be true if TG is to come:
		''	•	US resident (Should not be Puerto Rican)
		''	•	Should be FS/$9dfc/FSOR path. Travel Guard is out of scope for TA/FSTA.
		''	•	Booking path shouldn’t be Car Only
		''	•	Trip duration should be less than 180 days
		''II. Pricing for ALL applicable within 24hr bookings on FS/$9DFC/FSOR paths:
		''	•	$14.99 always.
		''III. Pricing for Hotel Only bookings on FS/$9DFC paths:
		''	•	$14.00 flat fee per passenger, or a 5% of total booking amount, whichever is Greater.
		''IV. Pricing for Package bookings(F+H, F+H+C, F+C and F+A) on FS/$9DFC paths:
		''	•	$28.00 flat fee per passenger, or a 7.2% of total booking amount, whichever is Greater.
		''V. Pricing for Domestic Flight Only bookings on FS/$9DFC/FSOR paths:
		''	•	$14.00 flat fee per passenger, or a 6% of total booking amount, whichever is Greater.
		''VI. Pricing for International Flight Only bookings on FS/$9DFC/FSOR paths:
		''	•	$25.00 flat fee per passenger, or a 7.57% of total booking amount, whichever is Greater.*/

		int paxCount = 	Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString()) +
						Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString()) +
						Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());

		double totalAmountTG	= 0;
		double paxCountTG		= 0;
		double calculatedTG		= 0;
		double redeemAmount		= 0;


		//open total due section
		if(paymentPage.getTotalDueChevronLink().getAttribute("style").contains("-180deg")){
			paymentPage.getTotalDueChevronLink().click();
		}

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//open credit and voucher section
		if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_TotalDueVoucherAndCreditChevronLink))){
			redeemAmount = Double.parseDouble(paymentPage.getTotalDueVoucherAndCreditPriceText().getText().replace("-$","").replace(",",""));
		}

		//System.out.println("This is Redeem Amount " + redeemAmount);
		double totalPrice = Double.parseDouble(paymentPage.getTotalDuePriceText().getText().replace("$","").replace(",",""));
		//System.out.println("This is New Amount " + totalPrice);

		totalPrice = totalPrice + redeemAmount;
		//System.out.println("This is Total Amount " + totalPrice);


		//II. Pricing for ALL applicable within 24hr bookings on FS/$9DFC/FSOR paths:
		if(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString().equalsIgnoreCase("0")){
			//$14.99 always
			calculatedTG = paxCount * 14.99;

			ValidationUtil.validateTestStep("Pricing for ALL applicable within 24hr bookings on FS/$9DFC/FSOR paths",true);
		//III. Pricing for Hotel Only bookings on FS/$9DFC paths:
		}else if(scenarioContext.getContext(Context.HOMEPAGE_JOURNEYTYPE).toString().equalsIgnoreCase("Hotel")){
			//total TG based on pasenger($14.00 flat fee per passenger)
			paxCountTG = paxCount * 14;

			//total TG based on total amount(5% of total booking amount)
			totalAmountTG = 0.05 * totalPrice;

			if(paxCountTG>totalAmountTG){
				calculatedTG = paxCountTG;

				ValidationUtil.validateTestStep("Pricing for Hotel Only bookings on FS/$9DFC paths with $14.00 flat fee per passenger)",true);
			}else{
				calculatedTG = totalAmountTG;

				ValidationUtil.validateTestStep("Pricing for Hotel Only bookings on FS/$9DFC paths with 5% of total booking amount",true);
			}
		//IV. Pricing for Package bookings(F+H, F+H+C, F+C and F+A) on FS/$9DFC paths:
		}else if(scenarioContext.getContext(Context.HOMEPAGE_JOURNEYTYPE).toString().equalsIgnoreCase("Vacation")){
			//total TG based on pasenger($28.00 flat fee per passenger)
			paxCountTG = paxCount * 28;

			//total TG based on total amount(7.2% of total booking amount)
			totalAmountTG = 0.072 * totalPrice;

			if(paxCountTG>totalAmountTG){
				calculatedTG = paxCountTG;

				ValidationUtil.validateTestStep("Pricing for Package bookings(F+H, F+H+C, F+C and F+A) on FS/$9DFC paths with $28.00 flat fee per passenger",true);
			}else{
				calculatedTG = totalAmountTG;

				ValidationUtil.validateTestStep("Pricing for Package bookings(F+H, F+H+C, F+C and F+A) on FS/$9DFC paths with 7.2% of total booking amount",true);
			}
		//V. Pricing for Domestic Flight Only bookings on FS/$9DFC/FSOR paths
		}else if(scenarioContext.getContext(Context.HOMEPAGE_JOURNEYTYPE).toString().equalsIgnoreCase("Flight") &&
				!scenarioContext.isContains(Context.HOMEPAGE_INTERNATIONAL)){
			//total TG based on pasenger($14.00 flat fee per passenger)
			paxCountTG = paxCount * 14;

			//total TG based on total amount(6% of total booking amount)
			totalAmountTG = 0.06 * totalPrice;

			if(paxCountTG>totalAmountTG){
				calculatedTG = paxCountTG;

				ValidationUtil.validateTestStep("Pricing for Domestic Flight Only bookings on FS/$9DFC/FSOR paths with $14.00 flat fee per passenger",true);
			}else{
				calculatedTG = totalAmountTG;

				ValidationUtil.validateTestStep("Pricing for Domestic Flight Only bookings on FS/$9DFC/FSOR paths with 6% of total booking amount",true);
			}
		//VI. Pricing for International Flight Only bookings on FS/$9DFC/FSOR paths:
		}else if(scenarioContext.getContext(Context.HOMEPAGE_JOURNEYTYPE).toString().equalsIgnoreCase("Flight") &&
				scenarioContext.isContains(Context.HOMEPAGE_INTERNATIONAL)){
			//total TG based on pasenger($25.00 flat fee per passenger)
			paxCountTG = paxCount * 25;

			//total TG based on total amount(7.57% of total booking amount)
			totalAmountTG = 0.0757 * totalPrice;

			if(paxCountTG>totalAmountTG){
				calculatedTG = paxCountTG;

				ValidationUtil.validateTestStep("Pricing for International Flight Only bookings on FS/$9DFC/FSOR paths with $25.00 flat fee per passenger",true);
			}else{
				calculatedTG = totalAmountTG;

				ValidationUtil.validateTestStep("Pricing for International Flight Only bookings on FS/$9DFC/FSOR paths with 7.57% of total booking amount",true);
			}
		}

		//get the TG amount
		double actualAmount = Double.parseDouble(paymentPage.getYesTravelGuardLabel().getText().split("\\$")[1].split(" ")[0].trim());

		//System.out.println("This is amount difference" + (actualAmount-calculatedTG));

		//check the difference is not more than 0.01
		if(actualAmount-calculatedTG<0.01){
			calculatedTG = actualAmount;
		}else if(actualAmount-calculatedTG>-0.01){
			calculatedTG = actualAmount;
		}

		//store travel guard in global variable
		scenarioContext.setContext(Context.PAYMENT_TRAVELGUARD_PRICE,TestUtil.convertTo2DecimalValue(calculatedTG));

		//return total amount
		//return calculatedTG;

	}

	// **********************************************************************************************
	// Method : calculateTravelGuard
	// Description: Method is used to select travel Guard on TG section With rgument as total amount
	// Input Arguments: travelGuardSelection: Required/NotRequired
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void calculateTravelGuard(String totalAmount) {

		/*I. Conditions that need to be true if TG is to come:
		''	•	US resident (Should not be Puerto Rican)
		''	•	Should be FS/$9dfc/FSOR path. Travel Guard is out of scope for TA/FSTA.
		''	•	Booking path shouldn’t be Car Only
		''	•	Trip duration should be less than 180 days
		''II. Pricing for ALL applicable within 24hr bookings on FS/$9DFC/FSOR paths:
		''	•	$14.99 always.
		''III. Pricing for Hotel Only bookings on FS/$9DFC paths:
		''	•	$14.00 flat fee per passenger, or a 5% of total booking amount, whichever is Greater.
		''IV. Pricing for Package bookings(F+H, F+H+C, F+C and F+A) on FS/$9DFC paths:
		''	•	$28.00 flat fee per passenger, or a 7.2% of total booking amount, whichever is Greater.
		''V. Pricing for Domestic Flight Only bookings on FS/$9DFC/FSOR paths:
		''	•	$14.00 flat fee per passenger, or a 6% of total booking amount, whichever is Greater.
		''VI. Pricing for International Flight Only bookings on FS/$9DFC/FSOR paths:
		''	•	$25.00 flat fee per passenger, or a 7.57% of total booking amount, whichever is Greater.*/

		int paxCount = 	Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString()) +
				Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString()) +
				Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());

		double totalAmountTG	= 0;
		double paxCountTG		= 0;
		double calculatedTG		= 0;

		System.out.println("This is the First Amount" + totalAmount);
		double totalPrice = Double.parseDouble(totalAmount.replace("$","").replace(",",""));

		System.out.println("This is the Second Amount" + totalPrice);

		DecimalFormat formatter = new DecimalFormat("#0.00");
		String d = new String(totalAmount.replace("$","").replace(",",""));
		Double dble = new Double(d.valueOf(d));
		System.out.println("This is the Third Amount" + dble);

		//II. Pricing for ALL applicable within 24hr bookings on FS/$9DFC/FSOR paths:
		if(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString().equalsIgnoreCase("0")){
			//$14.99 always
			calculatedTG = paxCount * 14.99;

			ValidationUtil.validateTestStep("Pricing for ALL applicable within 24hr bookings on FS/$9DFC/FSOR paths",true);
			//III. Pricing for Hotel Only bookings on FS/$9DFC paths:
		}else if(scenarioContext.getContext(Context.HOMEPAGE_JOURNEYTYPE).toString().equalsIgnoreCase("Hotel")){
			//total TG based on pasenger($14.00 flat fee per passenger)
			paxCountTG = paxCount * 14;

			//total TG based on total amount(5% of total booking amount)
			totalAmountTG = 0.05 * totalPrice;

			if(paxCountTG>totalAmountTG){
				calculatedTG = paxCountTG;

				ValidationUtil.validateTestStep("Pricing for Hotel Only bookings on FS/$9DFC paths with $14.00 flat fee per passenger)",true);
			}else{
				calculatedTG = totalAmountTG;

				ValidationUtil.validateTestStep("Pricing for Hotel Only bookings on FS/$9DFC paths with 5% of total booking amount",true);
			}
			//IV. Pricing for Package bookings(F+H, F+H+C, F+C and F+A) on FS/$9DFC paths:
		}else if(scenarioContext.getContext(Context.HOMEPAGE_JOURNEYTYPE).toString().equalsIgnoreCase("Vacation")){
			//total TG based on pasenger($28.00 flat fee per passenger)
			paxCountTG = paxCount * 28;

			//total TG based on total amount(7.2% of total booking amount)
			totalAmountTG = 0.072 * totalPrice;

			if(paxCountTG>totalAmountTG){
				calculatedTG = paxCountTG;

				ValidationUtil.validateTestStep("Pricing for Package bookings(F+H, F+H+C, F+C and F+A) on FS/$9DFC paths with $28.00 flat fee per passenger",true);
			}else{
				calculatedTG = totalAmountTG;

				ValidationUtil.validateTestStep("Pricing for Package bookings(F+H, F+H+C, F+C and F+A) on FS/$9DFC paths with 7.2% of total booking amount",true);
			}
			//V. Pricing for Domestic Flight Only bookings on FS/$9DFC/FSOR paths
		}else if(scenarioContext.getContext(Context.HOMEPAGE_JOURNEYTYPE).toString().equalsIgnoreCase("Flight") &&
				!scenarioContext.isContains(Context.HOMEPAGE_INTERNATIONAL)){
			//total TG based on pasenger($14.00 flat fee per passenger)
			paxCountTG = paxCount * 14;

			//total TG based on total amount(6% of total booking amount)
			totalAmountTG = 0.06 * totalPrice;

			if(paxCountTG>totalAmountTG){
				calculatedTG = paxCountTG;

				ValidationUtil.validateTestStep("Pricing for Domestic Flight Only bookings on FS/$9DFC/FSOR paths with $14.00 flat fee per passenger",true);
			}else{
				calculatedTG = totalAmountTG;

				ValidationUtil.validateTestStep("Pricing for Domestic Flight Only bookings on FS/$9DFC/FSOR paths with 6% of total booking amount",true);
			}
			//VI. Pricing for International Flight Only bookings on FS/$9DFC/FSOR paths:
		}else if(scenarioContext.getContext(Context.HOMEPAGE_JOURNEYTYPE).toString().equalsIgnoreCase("Flight") &&
				scenarioContext.isContains(Context.HOMEPAGE_INTERNATIONAL)){
			//total TG based on pasenger($25.00 flat fee per passenger)
			paxCountTG = paxCount * 25;

			//total TG based on total amount(7.57% of total booking amount)
			totalAmountTG = 0.0757 * totalPrice;

			if(paxCountTG>totalAmountTG){
				calculatedTG = paxCountTG;

				ValidationUtil.validateTestStep("Pricing for International Flight Only bookings on FS/$9DFC/FSOR paths with $25.00 flat fee per passenger",true);
			}else{
				calculatedTG = totalAmountTG;

				ValidationUtil.validateTestStep("Pricing for International Flight Only bookings on FS/$9DFC/FSOR paths with 7.57% of total booking amount",true);
			}
		}

		double actualAmount = Double.parseDouble(paymentPage.getYesTravelGuardLabel().getText().split("\\$")[1].split(" ")[0].trim());

		System.out.println("This is amount difference" + (actualAmount-calculatedTG));

		//store travel guard in global variable
		scenarioContext.setContext(Context.PAYMENT_TRAVELGUARD_PRICE,TestUtil.convertTo2DecimalValue(calculatedTG));

		//return total amount
		//return calculatedTG;

	}

	// **********************************************************************************************
	// Method : selectTravelGaurd
	// Description: Method is used to select travel Guard on TG section
	// Input Arguments: travelGuardSelection: Required/NotRequired
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void selectTravelGuard(String travelGuardSelection) {
		//declare constant used in method
		final String BLUE_COLOR = "rgb(0, 115, 230)";
		
		//declare variable used in method
		String selectedTG = null;
		
		//check TG is required or not required in booking
		if(travelGuardSelection.equalsIgnoreCase("Required")) {
			if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_YesTravelGuardLabel))) {
				//require TG
				JSExecuteUtil.clickOnElement(driver,paymentPage.getYesTravelGuardLabel());

				//get TG amount
				//calculateTravelGuard();

				//verify TG amount
				//ValidationUtil.validateTestStep("Verify the travel Guard amount appear on Travel Guard Section on Payment Page",
				//		paymentPage.getYesTravelGuardLabel().getText(),scenarioContext.getContext(Context.PAYMENT_TRAVELGUARD_PRICE).toString());

				//get color of TG radio button color
				selectedTG = JSExecuteUtil.getElementAfterProperty(driver, paymentPage.getYesTravelGuardLabel(), "background-color");
			}else {
				//validate required TG is selected as per passed option
				ValidationUtil.validateTestStep("Require Travel Guard radio button is not visible on Payment page", false);
			}
		}else if(travelGuardSelection.equalsIgnoreCase("NotRequired")) {
			if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_NoTravelGuardLabel))) {
				//not require TG
				paymentPage.getNoTravelGuardLabel().click();	
				
				//get color of TG radio button color
				selectedTG = JSExecuteUtil.getElementAfterProperty(driver, paymentPage.getNoTravelGuardLabel(), "background-color");
			}else {
				//validate not required TG is selected as per passed option
//				ValidationUtil.validateTestStep("Not Require Travel Guard radio button is not visible on Payment page", false);
			}

		}

		//store travel guard in global variable
		scenarioContext.setContext(Context.CONFIRMATION_TRAVELGUARD, travelGuardSelection);
		
		//validate TG is selected as per passed option
//		ValidationUtil.validateTestStep("Travel Guard is " + travelGuardSelection + " on Payment page", selectedTG.equals(BLUE_COLOR));
	}
	
	
	// **********************************************************************************************
	// Method : selectTravelGuardPopup
	// Description: Method is used to select travel Guard on final popup during booking
	// Input Arguments: travelGuardSelection: Required/NotRequired
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void selectTravelGuardPopup(String travelGuardSelection) {
		//declare constant used in method
		final String CONFIRMATION_URL 	= "confirmation"; 
		
		//check TG is required or not required in booking
		if(travelGuardSelection.equalsIgnoreCase("Required")) {
			if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_YesTravelGuardPopupLabel))) {
				//require TG
				paymentPage.getYesTravelGuardPopupLabel().click();
			}else {
				//validate required TG is selected as per passed option
				ValidationUtil.validateTestStep("Require Travel Guard Popup radio button is not visible on Payment page", false);
			}
		}else if(travelGuardSelection.equalsIgnoreCase("NotRequired")) {
			if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_CNoTravelGuardPopupLabel))) {
				//not require TG
				paymentPage.getNoTravelGuardPopupLabel().click();
			}else {
				//validate not required TG is selected as per passed option
//				ValidationUtil.validateTestStep("Not Require Travel Guard popup radio button is not visible on Payment page", false);
			}

		}
		
		//store travel guard in global variable
		scenarioContext.setContext(Context.CONFIRMATION_TRAVELGUARD, travelGuardSelection);
		
		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);
		
		//wait for confirmation url appear in application
		WaitUtil.untilPageURLVisible(driver, CONFIRMATION_URL);

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);
	}
	
	// **********************************************************************************************
	// Method : fillCardPaymentDetails
	// Description: Method is used to fill the card details on Payment page
	// Input Arguments: cardType: MasterCard/VisaCard/AmericanExpressCard/DiscoverCard1-DiscoverCard6
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void fillCardPaymentDetails(String cardType) {

		//check 9fc membership is bought
		checkBought9FCMembership();

		//wait for page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//get the card details
		CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(cardType);
		
		//fill card holder name
		memberEnrollmentPage.getAccountHolderNameTextBox().sendKeys(cardDetailsData.cardHolderName);
		
		//fill card number
		memberEnrollmentPage.getCardNumberTextBox().sendKeys(cardDetailsData.cardNumber);
		
		//fill expire date
		memberEnrollmentPage.getExpirationMonthYearTextBox().sendKeys(cardDetailsData.expirationDate);
		
		//fill security code
		memberEnrollmentPage.getSecurityCodeTextBox().sendKeys(cardDetailsData.securityCode);

		//wait for page load is complete
		WaitUtil.untilPageLoadComplete(driver);
		
		//validate TG is selected as per passed option
		ValidationUtil.validateTestStep("Card details are filled for " + cardType + " on Booking path Payment page", true);
	}
	
	// **********************************************************************************************
	// Method : fillAnotherCardPaymentDetailsModifyPath
	// Description: Method is used to another card to fill the card details on Modify Booking Payment page
	// Input Arguments: cardType: MasterCard/VisaCard/AmericanExpressCard/DiscoverCard1-DiscoverCard6
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void fillAnotherCardPaymentDetailsModifyPath(String cardType) {
		//select another card for payment
		TestUtil.selectDropDownUsingValue(paymentPage.getSelectCardDropDown(), "0: Other");
		
		//wait for 1 sec
		WaitUtil.untilTimeCompleted(1000);
		
		//fill card details
		fillCardPaymentDetails(cardType);
		//validate TG is selected as per passed option
		ValidationUtil.validateTestStep("New Card details are filled for " + cardType + " on Modify Booking Payment page", true);
	}
	
	// **********************************************************************************************
	// Method : fillCVVDetailsModifyPath
	// Description: Method is used to verify same card is used for modify booking that is used for booking path and fill CVV number
	// Input Arguments: cardType: MasterCard/VisaCard/AmericanExpressCard/DiscoverCard1-DiscoverCard6
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void fillCVVDetailsModifyPath(String cardType) {

		//check 9fc membership is bought
		checkBought9FCMembership();

		//declare variable used in method
		String expectedCardNumber = "";
		
		//get the card details
		CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(cardType);
		
	    //Replace card number to X
		for(int carnNumberCounter=0;carnNumberCounter<cardDetailsData.cardNumber.length()-4;carnNumberCounter++){
		    expectedCardNumber = expectedCardNumber + "X";
		}
		
		//get expected card number as shown in credit summary
		expectedCardNumber = expectedCardNumber.trim() + cardDetailsData.cardNumber.substring(cardDetailsData.cardNumber.length()-4);
		
		//create drop down select method
		Select drpdwnSelectCard = new Select(paymentPage.getSelectCardDropDown());

		//System.out.println(drpdwnSelectCard.getFirstSelectedOption().getText());
		//System.out.println(expectedCardNumber);

		//verify same card is used in modify booking
		ValidationUtil.validateTestStep("Verify the same card used in Booking path is also appear on Modify Path on Payment Page",
				drpdwnSelectCard.getFirstSelectedOption().getText(),expectedCardNumber);
		
		//fill security code
		memberEnrollmentPage.getSecurityCodeTextBox().sendKeys(cardDetailsData.securityCode);
		
		//validate TG is selected as per passed option
		ValidationUtil.validateTestStep("Card details are filled for " + cardType + " on Modify Booking Payment page", true);
		
	}
	
	// **********************************************************************************************
	// Method : acceptAndCompleteBooking
	// Description: Method is used to accept and click book button
	// Input Arguments: cardType: MasterCard/VisaCard/AmericanExpressCard/DiscoverCard1-DiscoverCard6
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void acceptAndCompleteBooking() {
		//declare constant used in method
		final String TRAVEL_GUARD		= "Required"; 
		final String CONFIRMATION_URL 	= "confirmation";

		//check 9fc membership is bought
		//checkBought9FCMembership();
		
		//accept terms and conditions
		paymentPage.getTermsAndConditionsLabel().click();

		//verify checkbox is clicked
		ValidationUtil.validateTestStep("User selected the Terms and Condition check box on Payment Page",
				JSExecuteUtil.getElementBeforeProperty(driver,paymentPage.getTermsAndConditionsLabel(),"background-color"),"rgb(0, 123, 255)");

		//click on book button
		JSExecuteUtil.clickOnElement(driver,paymentPage.getBookTripButton());

		ValidationUtil.validateTestStep("User click on Book Trip button on Payment Page", true);

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);
		
		if(scenarioContext.isContains(Context.CONFIRMATION_TRAVELGUARD)) {
			//check TG is selected on payment page
			if(scenarioContext.getContext(Context.CONFIRMATION_TRAVELGUARD).toString().contains(TRAVEL_GUARD)) {
				//wait for confirmation url appear in application
				WaitUtil.untilPageURLVisible(driver, CONFIRMATION_URL);
			}	
		}

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

	}
	
	// **********************************************************************************************
	// Method : verifyMilitaryPassenger
	// Description: Method is used to verify Military passengers 
	// Input Arguments: cardType: MasterCard/VisaCard/AmericanExpressCard/DiscoverCard1-DiscoverCard6
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 13-Mar-2019
	// ***********************************************************************************************
	public synchronized void verifyMilitaryPassengerLoginDetails() {
		//declare constant used in methods
		final int FIRST_INDEX	= 0;
		final int SECOND_INDEX 	= 1;
		final String MILITARY_LOGIN = "en/session/new";
		final String PAYMENT_URL = "payment";
		
		//declare variable used in method
		String paxName = null;
		int totalPassenger;
		WebElement militaryLogin;
		LoginCredentialsData loginCredentialsData;

		for(int dummyCounter=0;dummyCounter<=2;dummyCounter++){

			//wait till page load is complete
			WaitUtil.untilPageLoadComplete(driver);

			//get total passengers
			totalPassenger = paymentPage.getActiveMilitrayRowsGridView().size();

			//loop through all passengers
			for(int verifyPassenger=0;verifyPassenger<totalPassenger;verifyPassenger++) {
				//get military apssenger details
				militaryLogin = paymentPage.getActiveMilitrayRowsGridView().get(verifyPassenger);

				//System.out.println("UserName:" + militaryLogin);

				//check header is skip during varification
				if(!(militaryLogin.getText().contains("Name") || militaryLogin.getText().contains("Nombre"))) {
					//check passenger is already verified
					if(militaryLogin.getText().contains("Verify") || militaryLogin.getText().contains("Verificar")) {
						//get all the columns
						List<WebElement> militaryPaxRows = militaryLogin.findElements(By.tagName("td"));

						//get pax name
						paxName = militaryPaxRows.get(FIRST_INDEX).getText().split("\\.",2)[SECOND_INDEX].trim().replaceAll(" ", "");

						//get login details based on military pax name
						loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("Militray"+paxName);

						//click on verify link
						militaryPaxRows.get(SECOND_INDEX).findElements(By.tagName("a")).get(FIRST_INDEX).click();

						//wait till military login page appear
						WaitUtil.untilPageURLVisible(driver, MILITARY_LOGIN);

						//System.out.println("UserName:" + loginCredentialsData.userName);
						//System.out.println("Password:" + loginCredentialsData.password);

						WaitUtil.untilJavaScriptPageLoadComplete(driver);

						WaitUtil.untilTimeCompleted(5000);

						//wait for username
						WaitUtil.untilElementIsClickable(driver, paymentPage.getActiveMilitaryEmailTextBox());

						//enter user name
						paymentPage.getActiveMilitaryEmailTextBox().sendKeys(loginCredentialsData.userName);

						//wait for password
						WaitUtil.untilElementIsClickable(driver, paymentPage.getActiveMilitaryPasswordTextBox());

						//enter password
						paymentPage.getActiveMilitaryPasswordTextBox().sendKeys(loginCredentialsData.password);

						//wait for sign in button
						WaitUtil.untilElementIsClickable(driver, paymentPage.getActiveMilitarySignInButton());

						//click on signin button
						paymentPage.getActiveMilitarySignInButton().click();

						//wait till Payment page is loaded
						WaitUtil.untilPageURLVisible(driver, PAYMENT_URL);

						ValidationUtil.validateTestStep("User login with Military Pasenger as " + paxName + " on Military Page", true);

						//wait till page load is complete
						WaitUtil.untilPageLoadComplete(driver);

						WaitUtil.untilTimeCompleted(5000);
					}
				}
			}
		}

		//verify Military login credentials
		ValidationUtil.validateTestStep("Military passengers are verifed with their Login Credentials on Payment Page" , paymentPage.getActiveMilitaryVerifyLink().size()==0);
	}
	
	// **********************************************************************************************
	// Method : verifyTotalDueOptions
	// Description: Method is used to verify options section in total due amount
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 23-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 23-Mar-2019
	// ***********************************************************************************************
	public synchronized void verifyTotalDueOptions() {
		//declare constant used in method
		final String ENGLISH_FLIGHT_FLEX 		= "FLIGHT FLEX";
		final String ENGLISH_SHORTCUT_SECURITY 	= "SHORTCUT SECURITY";
		final String ENGLISH_SHORTCUT_BOARDING 	= "SHORTCUT BOARDING";
		final String ENGLISH_CHECK_IN 			= "CHECK-IN";
		final String SPANISH_FLIGHT_FLEX 		= "FLIGHT FLEX";
		final String SPANISH_SHORTCUT_SECURITY 	= "ATAJO DE SEGURIDAD";
		final String SPANISH_SHORTCUT_BOARDING 	= "PRIORIDAD DE EMBARQUE";
		final String SPANISH_CHECK_IN 			= "REGISTRO";
		final int FIRST_INDEX 					= 0;
		final int SECOND_INDEX 					= 1;
		final String DOUBLE_SEPARATOR 			= "\\|\\|";
		final String SINGLE_SEPARATOR 			= "\\|";
		
		//declare variable used in method
		int totalPaxCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());
		String firstLeg;
		String secondLeg;
		double shorcutSecurityTotalPrice;
		String actualValue;
		String expectedValue;
		
		//check total due break down is open
		if(paymentPage.getTotalDueChevronLink().getAttribute("style").contains("-180deg")) {
			//open total due break down
			paymentPage.getTotalDueChevronLink().click();
			
			//wait for 1 sec
			WaitUtil.untilTimeCompleted(1000);
		}
		
		//check options break down is open
		if(paymentPage.getTotalDueOptionsChevronLink().getAttribute("style").contains("-180deg")) {
			//open options break down
			paymentPage.getTotalDueOptionsChevronLink().click();
			
			//wait for 1 sec
			WaitUtil.untilTimeCompleted(1000);
		}
		
		//loop through all opyions in Total Due amount breakdon
		for(WebElement optionsSection: paymentPage.getTotalDueOptionsBreakDownGridView()) {
			//get label and price of Option in total due amount breakdown
			List<WebElement> allOptionsSubSection = optionsSection.findElements(By.tagName("p"));
			
			//Flight Flex Section in total due section
			if(allOptionsSubSection.get(FIRST_INDEX).getText().toUpperCase().contains(ENGLISH_FLIGHT_FLEX) || allOptionsSubSection.get(FIRST_INDEX).getText().toUpperCase().contains(SPANISH_FLIGHT_FLEX)) {
				//check flight flex value is stored on options page
				if(scenarioContext.isContains(Context.OPTIONS_FLIGHT_FLEX)) {
					//verify the total number of passenger for flight flex
					ValidationUtil.validateTestStep("Flight Flex is bought for all passenger in Total due section on Payment page", allOptionsSubSection.get(FIRST_INDEX).getText().contains(Integer.toString(totalPaxCount)));

					//verify total amount for flight flex 
					ValidationUtil.validateTestStep("Flight Flex amount in Total Due section on Payment page", allOptionsSubSection.get(SECOND_INDEX).getText().contains(scenarioContext.getContext(Context.OPTIONS_FLIGHT_FLEX).toString()));
				}else {
					//verify flight flex is not stored on option page
					ValidationUtil.validateTestStep("Flight Flex value is not stored on Options page", false);
				}
			}else if(allOptionsSubSection.get(FIRST_INDEX).getText().toUpperCase().contains(ENGLISH_SHORTCUT_SECURITY) || allOptionsSubSection.get(FIRST_INDEX).getText().toUpperCase().contains(SPANISH_SHORTCUT_SECURITY)) {
				//check shortcut security is stored on options page
				if(scenarioContext.isContains(Context.OPTIONS_SHORTCUT_SECURITY)) {
					//check the city for which shortcut security is bought
					if(scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY).toString().split(DOUBLE_SEPARATOR).length==2) {
						//get first leg details
						firstLeg = scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY).toString().split(DOUBLE_SEPARATOR)[FIRST_INDEX];
						
						//get second leg details
						secondLeg = scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY).toString().split(DOUBLE_SEPARATOR)[SECOND_INDEX];
						
						//calculate total amount
						shorcutSecurityTotalPrice = Double.parseDouble(firstLeg.split(SINGLE_SEPARATOR)[SECOND_INDEX]) + Double.parseDouble(secondLeg.split(SINGLE_SEPARATOR)[SECOND_INDEX]);
					}else if(scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY).toString().split(DOUBLE_SEPARATOR).length==1){
						//get first leg detail
						firstLeg = scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY).toString();
						
						//calculate total amount
						shorcutSecurityTotalPrice = Double.parseDouble(firstLeg.split(SINGLE_SEPARATOR)[SECOND_INDEX]);
					}else {
						throw new RuntimeException("code is not implemented for multicity in verifyTotalDueOptions");
					}
					
					//verify the total amount for shortcut security
					ValidationUtil.validateTestStep("Shortcut Security amount n total due section on Payment Page", allOptionsSubSection.get(SECOND_INDEX).getText().trim().contains(TestUtil.convertTo2DecimalValue(shorcutSecurityTotalPrice)));
					
					//get all shortcut security city
					List<WebElement> allShortcutSecurity = optionsSection.findElements(By.tagName("app-price-section-line"));
					
					//loop through all shortcut security cities
					for(int shortcutSecurityCounter=0;shortcutSecurityCounter<allShortcutSecurity.size();shortcutSecurityCounter++) {
						//get the shortcut security city pair from application
						actualValue = allShortcutSecurity.get(shortcutSecurityCounter).findElements(By.tagName("p")).get(FIRST_INDEX).getText().trim();
						
						//get the shortcut security city pair from global variable
						expectedValue = scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY).toString().split(DOUBLE_SEPARATOR)[shortcutSecurityCounter].split(SINGLE_SEPARATOR)[FIRST_INDEX];
						
						//verify the shortcut security for each flight leg
						ValidationUtil.validateTestStep("Total Due Amount breakdown contains Shortcut Security for Flight Leg:"+expectedValue+ " on Payment page", actualValue.equals(expectedValue));
					}
				}else {
					//verify shortcut security is not stored on option page
					ValidationUtil.validateTestStep("Shortcut Security value is not stored on Options page", false);
				}
			}else if(allOptionsSubSection.get(FIRST_INDEX).getText().toUpperCase().contains(ENGLISH_SHORTCUT_BOARDING) || allOptionsSubSection.get(FIRST_INDEX).getText().toUpperCase().contains(SPANISH_SHORTCUT_BOARDING)) {
				//check shortcut boarding is stored on options page
				if(scenarioContext.isContains(Context.OPTIONS_SHORTCUT_BOARDING)) {
					//verify the total amount for shortcut boarding
					ValidationUtil.validateTestStep("Shortcut Boarding amount in total due section on Payment Page", allOptionsSubSection.get(SECOND_INDEX).getText().trim().contains(scenarioContext.getContext(Context.OPTIONS_SHORTCUT_BOARDING).toString()));
					
					//get all shortcut security city
					List<WebElement> allShortcutBoarding = optionsSection.findElements(By.tagName("app-price-section-line"));
					
					//loop through all shortcut boarding cities
					for(int shortcutBoardingCounter=0;shortcutBoardingCounter<allShortcutBoarding.size();shortcutBoardingCounter++) {
						//get the shortcut security city pair from application
						actualValue = allShortcutBoarding.get(shortcutBoardingCounter).findElements(By.tagName("p")).get(FIRST_INDEX).getText().trim();
						
						if(shortcutBoardingCounter==0) {
							//get the shortcut security city pair from global variable
							expectedValue = totalPaxCount + " " + scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];	
						}else {
							//get the shortcut security city pair from global variable
							expectedValue = totalPaxCount + " " + scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];	
						}

						//verify the shortcut boarding for each flight leg
						ValidationUtil.validateTestStep("Total Due Amount breakdown contains Shortcut Boarding for Flight Leg:"+expectedValue+ " on Payment page", actualValue.equals(expectedValue));
					}
					
				}else {
					//verify shortcut boarding is not stored on option page
					ValidationUtil.validateTestStep("Shortcut Boarding value is not stored on Options page", false);
				}
			}else if(allOptionsSubSection.get(FIRST_INDEX).getText().toUpperCase().contains(ENGLISH_CHECK_IN) || allOptionsSubSection.get(FIRST_INDEX).getText().toUpperCase().contains(SPANISH_CHECK_IN)) {
				//check shortcut boarding is stored on options page
				if(scenarioContext.isContains(Context.OPTIONS_CHECK_IN)) {
					//verify the total amount for checkin
					ValidationUtil.validateTestStep("CheckIn amount in total due section on Payment Page", allOptionsSubSection.get(SECOND_INDEX).getText().trim().contains(scenarioContext.getContext(Context.OPTIONS_CHECK_IN).toString()));
					
					//get all shortcut security city
					List<WebElement> allCheckIn = optionsSection.findElements(By.tagName("app-price-section-line"));
					
					//loop through all shortcut security cities
					for(int checkInCounter=0;checkInCounter<allCheckIn.size();checkInCounter++) {
						//get the shortcut security city pair from application
						actualValue = allCheckIn.get(checkInCounter).findElements(By.tagName("p")).get(FIRST_INDEX).getText().trim();
						
						if(checkInCounter==0) {
							//get the shortcut security city pair from global variable
							expectedValue = totalPaxCount + " " + scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];	
						}else {
							//get the shortcut security city pair from global variable
							expectedValue = totalPaxCount + " " + scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];	
						}

						//verify the shortcut boarding for each flight leg
						ValidationUtil.validateTestStep("Total Due Amount breakdown contains Check-In for Flight Leg:"+expectedValue+ " on Payment page", actualValue.equals(expectedValue));
					}
				}else {
					//verify checkin option is not stored on option page
					ValidationUtil.validateTestStep("CheckIn Option value is not stored on Options page", false);
				}
			}else {
				ValidationUtil.validateTestStep("Option Section " + allOptionsSubSection.get(FIRST_INDEX).getText() + " is not handle in method", false);
			}
		}
	}
	
	
	// **********************************************************************************************
	// Method : verifyTotalDueBags
	// Description: Method is used to verify bags section in total due amount
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 24-Mar-2019
	// Reviewed By: Kartik
	// Reviewed On: 24-Mar-2019
	// ***********************************************************************************************
	public synchronized void verifyTotalDueBags() {
		//declare constant used in method
		final int FIRST_INDEX 					= 0;
		final int SECOND_INDEX 					= 1;
		final String DOUBLE_SEPARATOR 			= "\\|\\|";
		final String SINGLE_SEPARATOR 			= "\\|";
		final String SINGLE_HYPHEN				= "-";
		
		//declare variable used in methods
		double totalBagsAmount					= 0;
		double totalDepBagsAmount				= 0;
		double totalDepCarryOnBagsAmount		= 0;
		double totalDepCheckedBagsAmount		= 0;
		double totalRetBagsAmount				= 0;
		double totalRetCarryOnBagsAmount		= 0;
		double totalRetCheckedBagsAmount		= 0;
		double totalJourneyBagsAmount			= 0;
		double totalCarryOnBagsAmount			= 0;
		double totalCheckedBagsAmount			= 0;
		int depCarryOnBags						= 0;
		int depCheckedBags						= 0;
		int retCarryOnBags						= 0;
		int retCheckedBags						= 0;
		int carryOnBagsCount					= 0;
		int checkedBagsCount					= 0;
		String airportCodes						= null;
		String depAirportCode					= scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];
		String retAirportCode					= scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];;
		
		//check total due break down is open
		if(paymentPage.getTotalDueChevronLink().getAttribute("style").contains("-180deg")) {
			//open total due break down
			paymentPage.getTotalDueChevronLink().click();
			
			//wait for 1 sec
			WaitUtil.untilTimeCompleted(1000);
		}
		
		//check options break down is open
		if(paymentPage.getTotalDueBagsChevronLink().getAttribute("style").contains("-180deg")) {
			//open options break down
			paymentPage.getTotalDueBagsChevronLink().click();
			
			//wait for 1 sec
			WaitUtil.untilTimeCompleted(1000);
		}
		
		//check dep bags are selected
		if(scenarioContext.isContains(Context.BAGS_DEP_BAGS)) {
			//loop through all passenger bags selected
			for(int depBagsCounter=0;depBagsCounter<scenarioContext.getContext(Context.BAGS_DEP_BAGS).toString().split(DOUBLE_SEPARATOR).length;depBagsCounter++) {
				//loop through per passenger bag selected
				for(int depBagsPerPaxCounter=0;depBagsPerPaxCounter<scenarioContext.getContext(Context.BAGS_DEP_BAGS).toString().split(DOUBLE_SEPARATOR)[depBagsCounter].split(SINGLE_SEPARATOR).length;depBagsPerPaxCounter++) {
					//check for passenger bag type
					if(depBagsPerPaxCounter==0) {
						//get carryon bag count
						depCarryOnBags = depCarryOnBags + Integer.parseInt(scenarioContext.getContext(Context.BAGS_DEP_BAGS).toString().split(DOUBLE_SEPARATOR)[depBagsCounter].split(SINGLE_SEPARATOR)[depBagsPerPaxCounter].split(SINGLE_HYPHEN)[FIRST_INDEX]);
						
						//get carry on bag amount
						totalDepCarryOnBagsAmount = totalDepCarryOnBagsAmount + Double.parseDouble(scenarioContext.getContext(Context.BAGS_DEP_BAGS).toString().split(DOUBLE_SEPARATOR)[depBagsCounter].split(SINGLE_SEPARATOR)[depBagsPerPaxCounter].split(SINGLE_HYPHEN)[SECOND_INDEX]);
					}else {
						//get checked bag count
						depCheckedBags = depCheckedBags + Integer.parseInt(scenarioContext.getContext(Context.BAGS_DEP_BAGS).toString().split(DOUBLE_SEPARATOR)[depBagsCounter].split(SINGLE_SEPARATOR)[depBagsPerPaxCounter].split(SINGLE_HYPHEN)[FIRST_INDEX]);
						
						//get checked bag amount
						totalDepCheckedBagsAmount = totalDepCheckedBagsAmount + Double.parseDouble(scenarioContext.getContext(Context.BAGS_DEP_BAGS).toString().split(DOUBLE_SEPARATOR)[depBagsCounter].split(SINGLE_SEPARATOR)[depBagsPerPaxCounter].split(SINGLE_HYPHEN)[SECOND_INDEX]);
					}
					
					//calculate the total amount of all dep bags
					totalBagsAmount = totalBagsAmount + Double.parseDouble(scenarioContext.getContext(Context.BAGS_DEP_BAGS).toString().split(DOUBLE_SEPARATOR)[depBagsCounter].split(SINGLE_SEPARATOR)[depBagsPerPaxCounter].split(SINGLE_HYPHEN)[SECOND_INDEX]);
					
					//get total dep bags amount
					totalDepBagsAmount = totalBagsAmount;
				}
			}	
		}
		
		//check ret bags are selected
		if(scenarioContext.isContains(Context.BAGS_RET_BAGS)) {
			//loop through all passenger bags selected
			for(int retBagsCounter=0;retBagsCounter<scenarioContext.getContext(Context.BAGS_RET_BAGS).toString().split(DOUBLE_SEPARATOR).length;retBagsCounter++) {
				//loop through per passenger bag selected
				for(int retBagsPerPaxCounter=0;retBagsPerPaxCounter<scenarioContext.getContext(Context.BAGS_RET_BAGS).toString().split(DOUBLE_SEPARATOR)[retBagsCounter].split(SINGLE_SEPARATOR).length;retBagsPerPaxCounter++) {
					//check for passenger bag type
					if(retBagsPerPaxCounter==0) {
						//get the carry on bag count
						retCarryOnBags = retCarryOnBags + Integer.parseInt(scenarioContext.getContext(Context.BAGS_RET_BAGS).toString().split(DOUBLE_SEPARATOR)[retBagsCounter].split(SINGLE_SEPARATOR)[retBagsPerPaxCounter].split(SINGLE_HYPHEN)[FIRST_INDEX]);
						
						//get the carry on bag amount
						totalRetCarryOnBagsAmount = totalRetCarryOnBagsAmount + Double.parseDouble(scenarioContext.getContext(Context.BAGS_RET_BAGS).toString().split(DOUBLE_SEPARATOR)[retBagsCounter].split(SINGLE_SEPARATOR)[retBagsPerPaxCounter].split(SINGLE_HYPHEN)[SECOND_INDEX]);
					}else {
						//get the checked bag count
						retCheckedBags = retCheckedBags + Integer.parseInt(scenarioContext.getContext(Context.BAGS_RET_BAGS).toString().split(DOUBLE_SEPARATOR)[retBagsCounter].split(SINGLE_SEPARATOR)[retBagsPerPaxCounter].split(SINGLE_HYPHEN)[FIRST_INDEX]);
						
						//get the checked bag amount
						totalRetCheckedBagsAmount = totalRetCheckedBagsAmount + Double.parseDouble(scenarioContext.getContext(Context.BAGS_RET_BAGS).toString().split(DOUBLE_SEPARATOR)[retBagsCounter].split(SINGLE_SEPARATOR)[retBagsPerPaxCounter].split(SINGLE_HYPHEN)[SECOND_INDEX]);
					}
					
					//calculate the total amount of all dep bags
					totalBagsAmount = totalBagsAmount + Double.parseDouble(scenarioContext.getContext(Context.BAGS_RET_BAGS).toString().split(DOUBLE_SEPARATOR)[retBagsCounter].split(SINGLE_SEPARATOR)[retBagsPerPaxCounter].split(SINGLE_HYPHEN)[SECOND_INDEX]);
					
					//get total ret bags amount 
					totalRetBagsAmount = totalBagsAmount- totalDepBagsAmount;
				}
			}	
		}
		
		//verify bag text is displayed in total due breakdown
		ValidationUtil.validateTestStep("Verify the Bags text is displayed in Total Due Breakdown amount on Payment Page",
				TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_TotalDueBagsText)));
		
		//verify the total bags price for complete journey
		ValidationUtil.validateTestStep("Verify the Total Bags amount $" + totalBagsAmount + " for all Bags in Total Due Breakdown amount on Payment Page", Double.parseDouble(paymentPage.getTotalDueBagsPriceText().getText().replace("$", "").replace(",", ""))==totalBagsAmount);
		
		//loop through all journey 
		for(int journeyBagCounter=0;journeyBagCounter<paymentPage.getTotalDueBagsBreakDownGridView().size();journeyBagCounter++) {
			
			if(journeyBagCounter==0) {
				airportCodes 			= depAirportCode + " - " + retAirportCode;
				totalJourneyBagsAmount 	= totalDepBagsAmount;
				totalCarryOnBagsAmount 	= totalDepCarryOnBagsAmount;
				totalCheckedBagsAmount 	= totalDepCheckedBagsAmount;
				carryOnBagsCount		= depCarryOnBags;
				checkedBagsCount		= depCheckedBags;
			}else {
				airportCodes 			= retAirportCode + " - " + depAirportCode;
				totalJourneyBagsAmount 	= totalRetBagsAmount;
				totalCarryOnBagsAmount 	= totalRetCarryOnBagsAmount;
				totalCheckedBagsAmount 	= totalRetCheckedBagsAmount;
				carryOnBagsCount		= retCarryOnBags;
				checkedBagsCount		= retCheckedBags;
			}
			
			//create webelement 
			WebElement journeyBagsType = paymentPage.getTotalDueBagsBreakDownGridView().get(journeyBagCounter);
			
			//verify airport code for each journey
			ValidationUtil.validateTestStep("Verify Airport Codes " + airportCodes + " in Total Due Breakdown on payment Page", journeyBagsType.findElements(By.tagName("p")).get(FIRST_INDEX).getText().trim().equals(airportCodes));
			
			//verify the total amount of each journey
			ValidationUtil.validateTestStep("Verify the Total Journey Bags Price is $"+ totalJourneyBagsAmount + " in Total Due Breakdown on payment Page", Double.parseDouble(journeyBagsType.findElements(By.tagName("p")).get(SECOND_INDEX).getText().trim().replace("$", "").replace(",", ""))==totalJourneyBagsAmount);
			
			//get all the bags deatils
			List<WebElement> allJourneyBags = journeyBagsType.findElements(By.tagName("app-price-section-line"));
		
			//verify the carry on bag count
			ValidationUtil.validateTestStep("Verify the " + carryOnBagsCount + " Carry-On Bags in Total Due Breakdown on payment Page", allJourneyBags.get(0).findElements(By.tagName("p")).get(FIRST_INDEX).getText().trim().contains(Integer.toString(carryOnBagsCount)));
			
			//verify the carry on bags amount 
			ValidationUtil.validateTestStep("Verify the Carry-On Bags Amount is $" + totalCarryOnBagsAmount + " in Total Due Breakdown on payment Page", Double.parseDouble(allJourneyBags.get(0).findElements(By.tagName("p")).get(SECOND_INDEX).getText().trim().replace("$", "").replace(",", ""))==totalCarryOnBagsAmount);
			
			//verify checked bag count
			ValidationUtil.validateTestStep("Verify the " + checkedBagsCount + " Checked Bags in Total Due Breakdown on payment Page", allJourneyBags.get(1).findElements(By.tagName("p")).get(FIRST_INDEX).getText().trim().contains(Integer.toString(checkedBagsCount)));
			
			//verify checked bag amount
			ValidationUtil.validateTestStep("Verify the Checked Bags Amount is $" + totalCheckedBagsAmount + " in Total Due Breakdown on payment Page", Double.parseDouble(allJourneyBags.get(1).findElements(By.tagName("p")).get(SECOND_INDEX).getText().trim().replace("$", "").replace(",", ""))==totalCheckedBagsAmount);	
		}
	}

	//**********************************************************************************************
	// Method : toCityPairTaxesPayment
	// Description: Method is used to verify all specific Taxes on Payment page, Flight Section
	// Input Arguments:
	// Return: NA
	// Created By : Kartik Chauhan
	// Created On : 05-Apr-2019
	// Reviewed By: Salim Ansari
	// Reviewed On: 10-Apr-2019
	//***********************************************************************************************
	public synchronized void toCityPairTaxes(List<String> taxList) {

		//declare constant used in method
		final String DUC		= "Regulatory Compliance Charge (Carrier Fee)";
		final String FEC		= "Fuel Charge (Carrier Fee)";

		//Click on Total Due text Verbiage on Payment Page
		paymentPage.getTotalDueText().click();

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//make a click on Flight section
		paymentPage.getTotalDueFlightText().click();

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//make a click on Flight section
		paymentPage.getTotalDueBagsText().click();

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//declare constant used in validation
		boolean taxFlag = false;
		boolean carrierFee_Under22_99  ;
		double carrierFee_Price = 0.0 ;

		//Validate which type of carrier fee is displayed
		boolean CAF_PRICE 		= TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_TotalDueFlightCAFText));
		boolean COPUF_PRICE		= TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_TotalDueFlightCOPUFText));
		boolean PUF_PRICE		= TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_TotalDueFlightPUFText));

		//if carrier fee is displayed, get its price and convert to double
		if (CAF_PRICE){
			carrierFee_Price = Double.parseDouble(paymentPage.getTotalDueFlightCAFText().getText().replace("$",""));
		}else if (COPUF_PRICE){
			carrierFee_Price = Double.parseDouble(paymentPage.getTotalDueFlightCOPUFText().getText().replace("$",""));
		}else if (PUF_PRICE){
			carrierFee_Price = Double.parseDouble(paymentPage.getTotalDueFlightPUFText().getText().replace("$",""));
		}

		//Check to see if carrier fee is less than
		carrierFee_Under22_99 = carrierFee_Price < 22.99;

		//create loop for all Taxes and calculate its size
		for (int taxCounter = 0; taxCounter < taxList.size(); taxCounter++) {
			//Below Xpaths are not added into framework
			List<WebElement> allTaxesVerbiage = paymentPage.getDepTotalBreakDownFlightText();

			//Bypass when carrier fee is less than $22.99
			if(carrierFee_Under22_99 && (taxList.get(taxCounter).equalsIgnoreCase(DUC) || taxList.get(taxCounter).equalsIgnoreCase(FEC))){
				//System.out.println(taxList.get(taxCounter) + "  ----->   Next pay move hoo gaya");
				continue;
			}

			//create loop for all Taxes and calculate its size
			for (int Counter = 0; Counter < allTaxesVerbiage.size(); Counter++) {
				//System.out.println(taxList.get(taxCounter).replaceAll(" ",""));
				//Verify 'All Taxes verbiage'
				if (allTaxesVerbiage.get(Counter).isDisplayed()) {
					//System.out.println(allTaxesVerbiage.get(Counter).getText().replaceAll(" ",""));
					//verify both taxes are equal
					//if (allTaxesVerbiage.get(Counter).getText().trim().contains(taxList.get(taxCounter))) {
					if (allTaxesVerbiage.get(Counter).getText().replaceAll(" ","").contains(taxList.get(taxCounter).replaceAll(" ",""))) {
						//set flag to true
						taxFlag = true;

						break;
					}
				}
			}

			//check additional tax appear on flight section
			if (allTaxesVerbiage.size()>taxList.size()){
				//verify New tax found on Payment Page
				ValidationUtil.validateTestStep("New tax found on Payment Page",false);
			}

			//validate for all the taxes in console
			ValidationUtil.validateTestStep("Verifying Tax verbiages From  " + scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT)+ " To " + scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT) + " City pair of Tax " + (taxList.get(taxCounter)) + " on Payment Page", taxFlag);

			//set flag to false for next tax
			taxFlag = false;
		}
	}

	// **********************************************************************************************
	// Method : verifyOptionSectionSelectedOptions
	// Description: Method is used to verify all options value on confirmation page
	// Input Arguments:
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 25-Apr-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 25-Apr-2019
	// ***********************************************************************************************
	public synchronized void verifyOptionSectionSelectedOptions(String selectedOptions){

		//declare constant used in method
		final String ENGLIGH_FLIGHTFLEX 		= "Flight Flex - Modify your flight once for free! (fare difference may apply)";
		final String SPANISH_FLIGHTFLEX 		= "Flight Flex - ¡Modifique su vuelo una vez gratis! (podrían aplicar diferencias en la tarifa)";
		final String ENGLISH_SHORTCUTSECURITY 	= "Skip the line and get access to a dedicated security lane at the airport.";
		final String SPANISH_SHORTCUTSECURITY 	= "Evite las colas y obtenga acceso a la fila de seguridad especial en el aeropuerto";
		final String ENGLISH_SHORTCUTBOARDING	= "Zone 2 priority boarding and early access to the overhead bins";
		final String SPANISH_SHORTCUTBOARDING	= "Prioridad de embarque de la Zona 2 y acceso temprano a los compartimentos superiores";
		final String ENGLISH_CHECKIN            = "I want to pre-pay for airport agent check-in";
		final String SPANISH_CHECKIN            = "Quiero pagar por adelantado para registrarme con un agente en el aeropuerto";

		//loop through all options value
		for(String currentOptions : selectedOptions.split("\\|")){
			//check for flight flex
			if(currentOptions.equalsIgnoreCase("FlightFlex")){
				//check flight flex header is displayed
				if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_OptionSectionFlightFlexSubHeaderText))){
					//check for language
					if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
						//verify flight flex verbiage for english
						ValidationUtil.validateTestStep("User verify Flight Flex Sub Header verbiage in Option Section on Payment Page",
								paymentPage.getOptionSectionFlightFlexSubHeaderText().getText().trim(),ENGLIGH_FLIGHTFLEX);
					}else{
						//verify flight flex verbiage for spanish
						ValidationUtil.validateTestStep("User verify Flight Flex Sub Header verbiage in Option Section on Payment Page",
								paymentPage.getOptionSectionFlightFlexSubHeaderText().getText().trim(),SPANISH_FLIGHTFLEX);
					}
				}else{
					//verify flight flex is not visible
					ValidationUtil.validateTestStep("Flight Flex is not appear in option Section on Payment Page",false);
				}
				//check for shortcut security
			}else if(currentOptions.equalsIgnoreCase("ShortCutSecurity")){
				//check shortcut security is visible
				if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_OptionSectionShortcutSecurityHeaderText))){
					//check for language
					if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
						//verify shortcut security verbiage for english
						ValidationUtil.validateTestStep("User verify ShortCut Security Sub Header verbiage in Option Section on Payment Page",
								paymentPage.getOptionSectionShortcutSecuritySubHeaderText().getText().trim(),ENGLISH_SHORTCUTSECURITY);
					}else{
						//verify shortcut security verbiage for spanish
						ValidationUtil.validateTestStep("User verify ShortCut Security Sub Header verbiage in Option Section on Payment Page",
								paymentPage.getOptionSectionShortcutSecuritySubHeaderText().getText().trim(),SPANISH_SHORTCUTSECURITY);
					}
				}else{
					//verify shortcut security is not visible
					ValidationUtil.validateTestStep("ShortCut Security is not appear in option Section on Payment Page",false);
				}
				//verify for shortcut boarding
			}else if(currentOptions.equalsIgnoreCase("ShortCutBoarding")){
				//verify shortcut boarding is visible
				if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_OptionSectionShortcutBoardingHeaderText))){
					//check for language
					if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
						//verify shortcut boarding in english
						ValidationUtil.validateTestStep("User verify ShortCut Boarding Sub Header verbiage in Option Section on Payment Page",
								paymentPage.getOptionSectionShortcutBoardingSubHeaderText().getText().trim(),ENGLISH_SHORTCUTBOARDING);
					}else{
						//verify shortcut boarding in spanish
						ValidationUtil.validateTestStep("User verify ShortCut Boarding Sub Header verbiage in Option Section on Payment Page",
								paymentPage.getOptionSectionShortcutBoardingSubHeaderText().getText().trim(),SPANISH_SHORTCUTBOARDING);
					}
				}else{
					//verify shortcut boarding is not visible
					ValidationUtil.validateTestStep("ShortCut Boarding is not appear in option Section on Payment Page",false);
				}
			}else if(currentOptions.equalsIgnoreCase("CheckInOption:AirportAgent")){
				if (TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_OptionSectionCheckInHeaderText))) {
					//check for language
					if (scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")) {
						//verify  Check In in english
						ValidationUtil.validateTestStep("User verify Check-in Sub Header verbiage in Option Section on Payment Page",
								paymentPage.getOptionSectionCheckInSubHeaderText().getText().trim(), ENGLISH_CHECKIN);
					} else {
						//verify Check In in spanish
						ValidationUtil.validateTestStep("User verify Check-in Sub Header verbiage in Option Section on Payment Page",
								paymentPage.getOptionSectionCheckInSubHeaderText().getText().trim(), SPANISH_CHECKIN);
					}
				} else {
					//verify Check In is not visible
					ValidationUtil.validateTestStep("Check-In is not appear in option Section on Payment Page", false);
				}
			}else{
				//verify shortcut boarding is not visible
				ValidationUtil.validateTestStep(currentOptions + " is not appear in option Section on Payment Page",currentOptions,"Not Found");
			}
		}
	}


	// **********************************************************************************************
	// Method : applyVoucherNumber
	// Description: Method is used to vapply voucher Number on Payment Page
	// Input Arguments:
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 25-Apr-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 25-Apr-2019
	// ***********************************************************************************************
	public synchronized void applyVoucherNumber(){
		//declare variable used in method
		double amountBeforeVoucher, amountAfterVoucher;

		String totalBeforeAmount, totalAfterAmount;

		//check redeem reservation credit link
		if(paymentPage.getRedeemVoucherOrCreditChevronLink().getAttribute("style").contains("-180deg")){
			paymentPage.getRedeemVoucherOrCreditChevronLink().click();
		}

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//wait for page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//get total amount
		totalBeforeAmount = paymentPage.getTotalDuePriceText().getText();

		//check total amount contain miles booking
		if(totalBeforeAmount.contains("+")){
			//total amount before applying voucher
			amountBeforeVoucher = Double.parseDouble(totalBeforeAmount.split("\\+")[1].trim().replace("$","").replace(",",""));
		}else{
			//total amount before applying voucher
			amountBeforeVoucher = Double.parseDouble(totalBeforeAmount.replace("$","").replace(",",""));
		}

		//enter voucher number
		paymentPage.getVoucherNumberTextBox().sendKeys(scenarioContext.getContext(Context.RESERVATION_VOUCHER_CODE).toString());

		//click on Go button
		paymentPage.getVoucherNumberGoButton().click();

		//wait for page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//System.out.println(paymentPage.getRedeemVoucherAmountText().getText());

		//verify correct amount is displayed
		ValidationUtil.validateTestStep("Verify the total Redeem Amount appear after Redeem Voucher on payment page",
				paymentPage.getRedeemVoucherAmountText().getText(),scenarioContext.getContext(Context.RESERVATION_VOUCHER_AMOUNT).toString() );

		//get redeem amount
		String redeemAmount = JSExecuteUtil.getElementTextValue(driver,paymentPage.getRedeemVoucherAmountToApplyTextBox());

		//System.out.println(redeemAmount);

		//click on apply button
		paymentPage.getVoucherNumberApplyButton().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//wait for page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//get total amount
		totalAfterAmount = paymentPage.getTotalDuePriceText().getText();

		//check total amount contain miles booking
		if(totalAfterAmount.contains("+")){
			//total amount before applying voucher
			amountAfterVoucher = Double.parseDouble(totalAfterAmount.split("\\+")[1].trim().replace("$","").replace(",",""));
		}else{
			//total amount before applying voucher
			amountAfterVoucher = Double.parseDouble(totalAfterAmount.replace("$","").replace(",",""));
		}

		//oprn total due section
		if(paymentPage.getTotalDueChevronLink().getAttribute("style").contains("-180deg")){
			paymentPage.getTotalDueChevronLink().click();
		}

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//oprn credit and voucher section
		if(paymentPage.getTotalDueVoucherAndCreditChevronLink().getAttribute("style").contains("-180deg")){
			paymentPage.getTotalDueVoucherAndCreditChevronLink().click();
		}

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//loop through all fields
		for(int fieldCount=0;fieldCount<paymentPage.getTotalDueVouchersAndCreditFieldListText().size();fieldCount++){
			//check for reservation credit section
			if(paymentPage.getTotalDueVouchersAndCreditFieldListText().get(fieldCount).getText().contains(scenarioContext.getContext(Context.RESERVATION_VOUCHER_CODE).toString())){
				//validate amount added is correct
				ValidationUtil.validateTestStep("Verify the Redeem added into Total Due amount on Payment Page",
						paymentPage.getTotalDueVouchersAndCreditFieldListText().get(fieldCount+1).getText().replace("-",""),redeemAmount);
				//break loop
				break;
			}
		}



		if(amountBeforeVoucher-amountAfterVoucher!=Double.parseDouble(redeemAmount.replaceAll(",",""))){
			if(Double.parseDouble(redeemAmount.replaceAll(",",""))-amountBeforeVoucher-amountAfterVoucher>0.01){
				ValidationUtil.validateTestStep("Total amount is not reduced with applied Voucher on Payment page",
						Double.toString(amountBeforeVoucher-amountAfterVoucher),redeemAmount);
			}
		}

		//wait for page load is complete
		WaitUtil.untilPageLoadComplete(driver);

	}

	// **********************************************************************************************
	// Method : applyReservationCredit
	// Description: Method is used to apply Reservation Credit on Payment Page
	// Input Arguments:
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 25-Apr-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 25-Apr-2019
	// ***********************************************************************************************
	public synchronized void applyReservationCredit(){

		//declare variable used in method
		double amountBeforeVoucher, amountAfterVoucher;

		String totalBeforeAmount, totalAfterAmount;

		String redeemAmount = "";
		//check redeem reservation credit link
		if(paymentPage.getRedeemVoucherOrCreditChevronLink().getAttribute("style").contains("-180deg")){
			//open redeem section
			paymentPage.getRedeemVoucherOrCreditChevronLink().click();
		}

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//wait for page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//get total amount
		totalBeforeAmount = paymentPage.getTotalDuePriceText().getText();

		//check total amount contain miles booking
		if(totalBeforeAmount.contains("+")){
			//total amount before applying voucher
			amountBeforeVoucher = Double.parseDouble(totalBeforeAmount.split("\\+")[1].trim().replace("$","").replaceAll(",",""));
		}else{
			//total amount before applying voucher
			amountBeforeVoucher = Double.parseDouble(totalBeforeAmount.replace("$","").replaceAll(",",""));
		}

		//enter voucher number
		paymentPage.getRedeemVoucherConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.RESERVATION_CREDIT_CODE).toString());

		//click on Go button
		paymentPage.getRedeemVoucherGoButton().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//verify correct amount is displayed
		ValidationUtil.validateTestStep("Verify the total Redeem Amount appear after Redeem Reservation Credit on payment page",
				paymentPage.getRedeemVoucherAmountText().getText().replaceAll(",",""),scenarioContext.getContext(Context.RESERVATION_CREDIT_AMOUNT).toString() );

		//get redeem amount
		redeemAmount = JSExecuteUtil.getElementTextValue(driver,paymentPage.getRedeemVoucherAmountToSpendTextBox());

		//click on apply button
		paymentPage.getRedeemVoucherApplyCreditButton().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//wait for page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//get total amount
		totalAfterAmount = paymentPage.getTotalDuePriceText().getText();

		//check total amount contain miles booking
		if(totalAfterAmount.contains("+")){
			//total amount before applying voucher
			amountAfterVoucher = Double.parseDouble(totalAfterAmount.split("\\+")[1].trim().replace("$","").replace(",",""));
		}else{
			//total amount before applying voucher
			amountAfterVoucher = Double.parseDouble(totalAfterAmount.replace("$","").replace(",",""));
		}

		//oprn total due section
		if(paymentPage.getTotalDueChevronLink().getAttribute("style").contains("-180deg")){
			paymentPage.getTotalDueChevronLink().click();
		}

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//oprn credit and voucher section
		if(paymentPage.getTotalDueVoucherAndCreditChevronLink().getAttribute("style").contains("-180deg")){
			paymentPage.getTotalDueVoucherAndCreditChevronLink().click();
		}

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//loop through all fields
		for(int fieldCount=0;fieldCount<paymentPage.getTotalDueVouchersAndCreditFieldListText().size();fieldCount++){
			//check for reservation credit section
			if(paymentPage.getTotalDueVouchersAndCreditFieldListText().get(fieldCount).getText().contains(scenarioContext.getContext(Context.RESERVATION_CREDIT_CODE).toString())){
				//validate amount added is correct
				ValidationUtil.validateTestStep("Verify the Redeem added into Total Due amount on Payment Page",
						paymentPage.getTotalDueVouchersAndCreditFieldListText().get(fieldCount+1).getText().replace("-",""),redeemAmount);
				//break loop
				break;
			}
		}


		if(amountBeforeVoucher-amountAfterVoucher!=Double.parseDouble(redeemAmount.replaceAll(",",""))){
			if(Double.parseDouble(redeemAmount.replaceAll(",",""))-amountBeforeVoucher-amountAfterVoucher>0.01){
				ValidationUtil.validateTestStep("Total amount is not reduced with applied Voucher on Payment page",
						Double.toString(amountBeforeVoucher-amountAfterVoucher),redeemAmount);
			}
		}

		//wait for page load is complete
		WaitUtil.untilPageLoadComplete(driver);
	}

	// **********************************************************************************************
	// Method : travelGuardRecommendedPopUp
	// Description: Method is used to check all the verbiages and select Travel Guard Recommended pop-up
	// Return: NA
	// Created By : Kartik Chauhan
	// Created On : 10-may-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	public synchronized void travelGuardRecommendedPopUp() {
		//put a wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//declare constant used in validation
		final String TG_RECOMMEND = "Travel Guard Recommends Travel Insurance";
		final String DONT_IGNORE = "Don't Ignore the Unexpected";

		//Verify User landed on TG Group Website
		ValidationUtil.validateTestStep("User verify TG recommends Travel Insurance on TG pop-up",
				paymentPage.getTravelGuardPopupHeaderText().getText(),TG_RECOMMEND);

		//Verify User landed on TG Group Website
		ValidationUtil.validateTestStep("User verify 'Don't ignore Unexpected..'Sub-header on TG pop-up",
				paymentPage.getTravelGuardPopupSubHeaderText().getText(),DONT_IGNORE);

		///////////validation that the text is displaying on Recommended pop-up
		String[] VerbiageBeingValidated =
				{
						"A family member could get sick...",
						"You may miss a flight connection...",
						"Your trip could be delayed...",
						"You could lose your passport...",
						"Your bags could be lost...",
						"travel insurance can provide financial reimbursement should the unexpected occur.",
						"Coverage offered by ",
				};

		for(String element1 : VerbiageBeingValidated)
		{
			try
			{
				WebElement TGREcommend_Text = driver.findElement(By.xpath("//div[@class='modal-body']//p[contains(text(),'" + element1 + "')]"));

				ValidationUtil.validateTestStep("Text \" " + element1 + "\" is displaying on TG Recommend pop-up: pass" , true); //text should dislpay: pass
			}
			catch (Exception e)
			{
				ValidationUtil.validateTestStep("Text \" " + element1 + "\" is not displaying on TG Recommend pop-up: Fail", false);
			}
		}

		//declare constant used in validation
		final String YES_ADD_INSURANCE = "Yes, add insurance for";
		final String COVERING_ALL = "covering all guests on this reservation.";
		final String NO_DON_WANT = "No, I don't want to insure my";
		final String TRIP_AND_UNDERSTAND = "trip and understand that I may be responsible for non-refundable charges.";

		//Verify User landed on TG Group Website
		ValidationUtil.validateTestStep("Yes, add insurance.. verbiage is displaying on TG recommended pop-up",
				paymentPage.getYesTravelGuardPopupLabel().getText(),YES_ADD_INSURANCE);

		//Verify User landed on TG Group Website
		ValidationUtil.validateTestStep("covering all guests.. verbiage is displaying on TG recommended pop-up",
				paymentPage.getYesTravelGuardPopupLabel().getText(),COVERING_ALL);

		//Verify User landed on TG Group Website
		ValidationUtil.validateTestStep("No, I don't want to insure my.. verbiage is displaying on TG recommended pop-up",
				paymentPage.getNoTravelGuardPopupLabel().getText(),NO_DON_WANT);

		//Verify User landed on TG Group Website
		ValidationUtil.validateTestStep("trip and understand that I am responsible.. verbiage is displaying on TG recommended pop-up",
				paymentPage.getNoTravelGuardPopupLabel().getText(),(TRIP_AND_UNDERSTAND));
	}

	// **********************************************************************************************
	// Method : travelGuardVerbiagesAndLink
	// Description: Method is used to check all the verbiages and Link under TG section
	// Return: NA
	// Created By : Kartik Chauhan
	// Created On : 10-may-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	public synchronized void travelGuardVerbiagesAndLink(List<String> travelGaurdVerbageList) {

		//put a wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//Payment Page Methods
		//boarding pass page
		final String TRAVEL_MORE 	= "TRAVEL MORE. WORRY LESS. GET INSURANCE FOR ONLY";
		final String PER_PERSON 	= "PER TRAVELER.*";

		//verify Travel guard header is displaying on TG section
		ValidationUtil.validateTestStep("Travel More worry less.. Travel guard Section header is displaying on TG section",
				paymentPage.getTravelMoreWorryLessText().getText().toUpperCase(),TRAVEL_MORE);

		//verify Travel guard header is displaying on TG section
		ValidationUtil.validateTestStep("Per person.. Travel guard Section header is displaying on TG section",
				paymentPage.getTravelMoreWorryLessText().getText().toUpperCase(),PER_PERSON);

		List<String> actualTextList = new ArrayList<>();

		for (WebElement element:paymentPage.getTravelGaurdCoverageText()) {
			actualTextList.add(element.getText().trim());
		}

		for (String travelGaurdText:travelGaurdVerbageList) {
			if(actualTextList.contains(travelGaurdText)){
				ValidationUtil.validateTestStep("Travel Gaurd Text "+travelGaurdText+" is displayed on Payment Page",true);
			}else {
				ValidationUtil.validateTestStep("Travel Gaurd Text "+travelGaurdText+" is displayed on Payment Page",false);
			}
		}

		//verify YES-label verbiage under TG section
		final String ADD_INSURANCE      	= "Yes, add insurance for";
		final String COVERING_ALL_CUSTOMERS = "covering all guests on this reservation.";
		final String READ_UNDERSTAND 		= "I have read and understand the ";
		final String AGREE_TERMS 			= " and agree to the terms of conditions.";

		ValidationUtil.validateTestStep("Yes, add insurance.. verbiage is displaying in TG section",
				paymentPage.getYesTravelGuardLabel().getText(),ADD_INSURANCE);

		ValidationUtil.validateTestStep("covering all customers on this reservation... verbiage is displaying in TG section",
				paymentPage.getYesTravelGuardLabel().getText(),COVERING_ALL_CUSTOMERS);

		ValidationUtil.validateTestStep("I have read and understand the .. verbiage is displaying in TG section",
				paymentPage.getHavereadAndUnderstandText().getText(),READ_UNDERSTAND);

		ValidationUtil.validateTestStep("Agree to Terms and Condition .. verbiage is displaying in TG section",
				paymentPage.getHavereadAndUnderstandText().getText(),AGREE_TERMS);

		//verify No-label verbiage under TG section
		final String NO_INSURANCE      = "No, I don't want to insure my";
		final String RESPONSIBLE_FOR_NONREFUNDABLE = "trip and understand that I may be responsible for non-refundable charges.";
		final String COVERAGE_OFFERED = "Coverage offered by Travel Guard Group, Inc. and limitations will apply. Full Disclaimer";

		ValidationUtil.validateTestStep("No, I don't want to insure my.. verbiage is diplaying in TG section",
				paymentPage.getNoTravelGuardLabel().getText(),NO_INSURANCE);

		ValidationUtil.validateTestStep("trip and understand that I am responsible.. verbiage is diplaying in TG section",
				paymentPage.getNoTravelGuardLabel().getText(),RESPONSIBLE_FOR_NONREFUNDABLE);

		ValidationUtil.validateTestStep("Coverage offered by Travel guard.. verbiage is diplaying in TG section",
				paymentPage.getCoverageOfferedText().getText(),COVERAGE_OFFERED);

//Constant value
		final String INSURANCE_POLICY = "https://webservices.travelguard.com/Product/";

		ValidationUtil.validateTestStep("User redirect to Travel guard Insurance website URL ",
				paymentPage.getInsurancePolicyLink().getAttribute("href"),INSURANCE_POLICY);

//*********************************************************************************
		//Constant value
		final String TRAVEL_GUARD_GROUP = "www.travelguard.com/spirit/";

		ValidationUtil.validateTestStep("User redirect to Travel guard Group website URL ",
				paymentPage.getTravelGuardGroupLink().getAttribute("href"),TRAVEL_GUARD_GROUP);
//*********************************************************************************

		//Constant value
		final String TRAVEL_GUARD_DISCLAIMER = "www.travelguard.com/disclaimer";

		ValidationUtil.validateTestStep("User redirect to Travel guard Disclaimer website URL ",
				paymentPage.getTravelGuardDisclaimerLink().getAttribute("href"),TRAVEL_GUARD_DISCLAIMER);
	}

	//**********************************************************************************************
	// Method : verifySelectedBaseFarePayment
	// Description: Method is used to verify below points
	//				1.Verify pre-selected Options for Barer Fare
	//				2.Verify Bare Fare discount on Total Due Amount section
	// Input Arguments: basefareType -> BoostIt/BundleIt
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 13-June-2019
	//**********************************************************************************************
	public synchronized void verifySelectedBaseFarePayment(String basefareType) {
		//********************************************************
		//****************Selected Option Bare Fare***************
		//********************************************************
		//declare constant used in method
		final String BUNDLE_OPTION	= "FlightFlex|ShortCutBoarding";
		final String BOOST_OPTION	= "ShortCutBoarding";

		//declare variable used in method
		String optionsValue	= "";

		if (basefareType.equalsIgnoreCase("BundleIt")){
			optionsValue = BUNDLE_OPTION;
		}else if(basefareType.equalsIgnoreCase("BoostIt")){
			optionsValue = BOOST_OPTION;
		}

		//call method to verify the options value
		verifyOptionSectionSelectedOptions(optionsValue);

		//********************************************************
		//*********Bare Fare Amount in Total Due Amount***********
		//********************************************************
		//open toatl due amount
		paymentPage.getTotalDueChevronLink().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		if (basefareType.equalsIgnoreCase("BundleIt")){
			//verify bundle fare discount
			if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_TotalDueBundleDiscountText))){
				//verify bundle fare price
				ValidationUtil.validateTestStep("Verify the Bundle It Discount price appear on Payment Page",
						scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString().replace("SAVEUPTO","").replace(",",""),paymentPage.getTotalDueBundleDiscountPriceText().getText().replace("-","" ).replace(",",""));
			}else{
				ValidationUtil.validateTestStep("Verify the Bundle It Discount appear on Payment Page",false);
			}
		}else if(basefareType.equalsIgnoreCase("BoostIt")){
			//verify bundle fare discount
			if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_TotalDueBoostDiscountPriceText))){
				//verify bundle fare price
				ValidationUtil.validateTestStep("Verify the Bundle It Discount price appear on Payment Page",
						scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(),paymentPage.getTotalDueBoostDiscountPriceText().getText().replace("-","" ).replace(",",""));
			}else{
				ValidationUtil.validateTestStep("Verify the Bundle It Discount appear on Payment Page",false);
			}
		}

	}

	//**********************************************************************************************
	// Method : verifyFlightSectionDetails
	// Description: Method is used to verify Flight Details like Date Airport Name, Flight Time, Flight Duration
	// Input Arguments: basefareType -> NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-July-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 13-July-2019
	//**********************************************************************************************
	public synchronized void verifyFlightSectionDetails(){
		WaitUtil.untilPageLoadComplete(driver);

		//declare variable used in method
		String FlightDetails            = "";
		int counter                     = 0;
		int depFlightLeg                = 0;
		int travellingDate              = 0;
		List<String> allFlightDetails   = null;
		String flightNumber             = "";
		String departingFlightDetail    = "";
		String arrivalFlightDetail      = "";
		String flightDuration           = "";
		String flightOverlayDuration    = "";

		//declare constant used in method
		final String SINGLE_SEPARATOR   = "\\|";
		final String DOUBLE_SEPARATOR   = "\\|\\|";


		List<WebElement> FlightSectionTravellingDate            = paymentPage.getFlightSectionTravellingDateText();
		List<WebElement> FlightSectionFlightNumber              = paymentPage.getFlightSectionFlightNumberText();
		List<WebElement> FlightSectionDepartingFlightDetail     = paymentPage.getFlightSectionDepartingFlightDetailText();
		List<WebElement> FlightSectionArrivalFlightDetail       = paymentPage.getFlightSectionArrivalFlightDetailText();
		List<WebElement> FlightSectionFlightDuration            = paymentPage.getFlightSectionFlightDurationText();
		List<WebElement> FlightSectionFlightOverlayDuration     = paymentPage.getFlightSectionFlightOverlayDurationText();

		if(scenarioContext.getContext(Context.AVAILABILITY_RET_FLIGHT_DETAILS).toString().length()>1){
			//get all flight details
			FlightDetails = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString() + "||" + scenarioContext.getContext(Context.AVAILABILITY_RET_FLIGHT_DETAILS);
		}else{
			FlightDetails = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString();
		}


		depFlightLeg = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split(DOUBLE_SEPARATOR).length;


		if(paymentPage.getFlightSectionFlightChevronImage().getAttribute("style").contains("180deg")){
			paymentPage.getFlightSectionFlightChevronImage().click();

			WaitUtil.untilTimeCompleted(2000);
		}

		//loop through all flight section
		for(int count=0;count<FlightSectionTravellingDate.size();count++){
			//check flight section is displayed
			if(FlightSectionTravellingDate.get(count).isDisplayed()){
				///all flight section variables
				flightNumber            = "";
				departingFlightDetail   = "";
				arrivalFlightDetail     = "";
				flightDuration          = "";
				flightOverlayDuration   = "";

				//loop through flight section to get the details
				for(String flightData : FlightDetails.split(DOUBLE_SEPARATOR)[count].split(SINGLE_SEPARATOR)){
					if(flightData.contains("FlightNumber:")){
						flightNumber = flightData.replace("FlightNumber:","");
					}else if(flightData.contains("DepartureAirport:")){
						departingFlightDetail = flightData.replace("DepartureAirport:","");
					}else if(flightData.contains("DepartureTime:")){
						departingFlightDetail = departingFlightDetail + flightData.replace("DepartureTime:","");
					}else if(flightData.contains("ArrivalAirport:")){
						arrivalFlightDetail = flightData.replace("ArrivalAirport:","");
					}else if(flightData.contains("ArrivalTime:")){
						arrivalFlightDetail = arrivalFlightDetail + flightData.replace("ArrivalTime:","");
					}else if(flightData.contains("FlightDuration:")){
						flightDuration = flightData.replace("FlightDuration:","");
					}else if(flightData.contains("LayOverTime:")){
						flightOverlayDuration = flightData.replace("LayOverTime:","");
					}
				}

				ValidationUtil.validateTestStep("Verifying all Flight details for Flight Section:"+(count+1),true);

				if(depFlightLeg>=count){
					if(count==0){
						ValidationUtil.validateTestStep("Verify Flight Travelling date is correctly displayed",
								FlightSectionTravellingDate.get(count).getText().replaceAll(" ",""),
								TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString(),"MMMMd,yyyy"));
					}else if(FlightDetails.split(DOUBLE_SEPARATOR)[count-1].contains("LayOverTime:")){
						if(FlightSectionTravellingDate.get(count-1).getText().toUpperCase().contains("PM") && FlightSectionTravellingDate.get(count).getText().toUpperCase().contains("AM")){
							travellingDate = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString()) + 1;

							ValidationUtil.validateTestStep("Verify Flight Travelling date is correctly displayed",
									FlightSectionTravellingDate.get(count).getText().replaceAll(" ",""),
									TestUtil.getStringDateFormat(Integer.toString(travellingDate),"MMMMdd,yyyy"));
						}

					}
				}else{
					if(!FlightDetails.split(DOUBLE_SEPARATOR)[count-1].contains("LayOverTime:")){
						ValidationUtil.validateTestStep("Verify Flight Travelling date is correctly displayed",
								FlightSectionTravellingDate.get(count).getText().replaceAll(" ",""),
								TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString(),"MMMMdd,yyyy"));
					}else {
						if(FlightSectionTravellingDate.get(count-1).getText().toUpperCase().contains("PM") && FlightSectionTravellingDate.get(count).getText().toUpperCase().contains("AM")){
							travellingDate = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString()) + 1;

							ValidationUtil.validateTestStep("Verify Flight Travelling date is correctly displayed",
									FlightSectionTravellingDate.get(count).getText().replaceAll(" ",""),
									TestUtil.getStringDateFormat(Integer.toString(travellingDate),"MMMMdd,yyyy"));
						}
					}
				}

				ValidationUtil.validateTestStep("Verify Flight Number is correctly dispayed in the Flight Section",
						FlightSectionFlightNumber.get(count).getText().split(": ")[1].replaceAll(" ",""), flightNumber);

				ValidationUtil.validateTestStep("Verify Departure Flight Details is correctly displayed in the Flight Section",
						FlightSectionDepartingFlightDetail.get(count).getText().split(": ")[1].replaceAll(" ",""),departingFlightDetail);

				ValidationUtil.validateTestStep("Verify Arrival Flight Details is correctly displayed in the Flight Section",
						FlightSectionArrivalFlightDetail.get(count).getText().split(": ")[1].replaceAll(" ",""),arrivalFlightDetail);

				ValidationUtil.validateTestStep("Verify Flight Duration is correctly displayed in the Flight Section",
						FlightSectionFlightDuration.get(count).getText().split(": ")[1].replaceAll(" ",""),flightDuration);


				if(flightOverlayDuration.length()>1){
					ValidationUtil.validateTestStep("Verify Flight DLayOverTime is correctly displayed in the Flight Section",
							FlightSectionFlightOverlayDuration.get(counter).getText().replaceAll(" ",""),flightOverlayDuration);
					counter+=1;
				}

			}
		}

	}

	//**********************************************************************************************
	// Method : verifyCarSectionDetails
	// Description: Method is used to verify Car Details like Car Model, Rental Agency, PickUp Time & Address
	// Input Arguments: basefareType -> NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-Nov-2019
	// Reviewed By:
	// Reviewed On:
	//**********************************************************************************************
	public synchronized void verifyCarSectionDetails(){

		//declare constant used in method
		final String SINGLE_SEPARATOR = "\\|";
		final String DOUBLE_SEPARATOR = "\\|\\|";

		//verify Car Header
		if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_OptionSectionCarHeaderText))){

			//CarType:THRIFTY ECONOMY CAR|CarModel:CHEVROLET SPARK or similar|Seats:4 Seats|Bags:3 Bags|Options:3 Bags|Miles:Unlimited Miles|Price:278.45|PickUpPoint:7135 GILESPIE STREET LAS VEGAS AP NV 89119
			for(String carDetails : scenarioContext.getContext(Context.CAR_DETAILS).toString().split(SINGLE_SEPARATOR)){
				switch (carDetails.split(":")[0]){
					case "CarModel":
						//verify Car Type
						ValidationUtil.validateTestStep("Verify Car Type information for Selected Car on Payment Page",
								paymentPage.getOptionSectionCarCarModelText().getText(),carDetails.replace("CarModel:",""));

						break;

					case "CarType":
						//verify Car Model
						ValidationUtil.validateTestStep("Verify Car Model information for Selected Car on Payment Page",
								carDetails.replace("CarType:",""),paymentPage.getOptionSectionCarRentalAgencyText().getText());

						break;

					case "PickUpPoint":
						String pickUpAddress = paymentPage.getOptionSectionCarPickUpAddressText().get(0).getText() + " " + paymentPage.getOptionSectionCarPickUpAddressText().get(1).getText();

						//verify PickUp Address
						ValidationUtil.validateTestStep("Verify PickUp Address Information information for Selected Car on Payment Page",
								pickUpAddress,carDetails.replace("PickUpPoint:",""));

						break;
				}
			}

			//verify PickUp time for Selected Car
			//get last leg departure flight information
			int lastLegDetails = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split(DOUBLE_SEPARATOR).length;
			String dateDays = null;

			//loop through all departure flight details of last leg
			for(String arrivalTime : scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split(DOUBLE_SEPARATOR)[lastLegDetails-1].split(SINGLE_SEPARATOR)){
				//check for arrival time
				if(arrivalTime.contains("ArrivalTime")){

					if(scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString().split(DOUBLE_SEPARATOR)[0].equals("1")){
						dateDays = Integer.toString(Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString())+1);
					}else{
						dateDays = scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString();
					}

					//get arribval time
					String pickUp = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy") + " " + arrivalTime.replace("ArrivalTime:","");


					//validate pickup point of Car
					ValidationUtil.validateTestStep("Verify Pick Up information for Selected Car on Payment Page",
							paymentPage.getOptionSectionCarPickUpTimeText().getText(),pickUp);

					break;
				}
			}

			//verify drop off time for selected Car
			//loop through all flight details of first leg
			for(String departureTime : scenarioContext.getContext(Context.AVAILABILITY_RET_FLIGHT_DETAILS).toString().split(DOUBLE_SEPARATOR)[0].split(SINGLE_SEPARATOR)){
				//check for arrival time
				if(departureTime.contains("DepartureTime")){

					dateDays = scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString();

					//get arribval time
					String dropOff = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy") + " " + departureTime.replace("DepartureTime:","");

					//validate pickup point of Car
					ValidationUtil.validateTestStep("Verify Drop OFF information for Selected Car on Payment Page",
							paymentPage.getOptionSectionCarDropOffTimeText().getText(),dropOff);

					break;
				}
			}
		}else{
			ValidationUtil.validateTestStep("Selected Car details is not displayed on Payment page", false);
		}
	}

	//**********************************************************************************************
	// Method : verifyHotelSectionDetails
	// Description: Method is used to verify Hotel Details
	// Input Arguments: basefareType -> NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-Nov-2019
	// Reviewed By:
	// Reviewed On:
	//**********************************************************************************************
	public synchronized void verifyHotelSectionDetails(){

		final String SINGLE_SEPARATOR	= "\\|";
		final String DOUBLE_SEPARATOR	= "\\|\\|";

		for(String hotelDetails:scenarioContext.getContext(Context.HOTEL_DETAILS).toString().split(SINGLE_SEPARATOR)){

			switch(hotelDetails.split(":")[0]){
				case "HotelName:":

					ValidationUtil.validateTestStep("Verify Selected Hotel Name on Payment Page",
							paymentPage.getOptionSectionHotelNameText().getText().trim(),hotelDetails.replace("HotelName:",""));

					break;
				case "HotelLandmark":



					break;

				case "HotelAddress:":

					boolean addressFlag = true;
					String actualAddress = paymentPage.getOptionSectionHotelAddressText().getText().trim().replace(","," ").replace("  "," ");
					String expectedAddress = hotelDetails.replace("HotelAddress:","");

					for(String allAddress : actualAddress.split(" ")){
						if(expectedAddress.toUpperCase().contains(allAddress.toUpperCase())){
							addressFlag = true;
						}else{
							addressFlag = false;
						}
					}

					ValidationUtil.validateTestStep("Verify Selected Hotel Address on Payment Page",
							addressFlag);

					break;
			}
		}

		//**********Room Count***********
		//verify room count
		ValidationUtil.validateTestStep("Verify Selected Hotel Rooms count on Payment Page",
				paymentPage.getOptionSectionHotelRoomsText().getText().trim(),scenarioContext.getContext(Context.HOMEPAGE_ROOMS).toString());

		//**********Passenger Count***********
		//get total passengers
		int paxCount = 	Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString()) +
						Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString())+
						Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString())+
						Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTLAP_COUNT).toString());

		//verify guest count
		ValidationUtil.validateTestStep("Verify Selected Hotel Guest count on Payment Page",
				paymentPage.getOptionSectionHotelGuestCountText().getText().trim(),Integer.toString(paxCount));

		//**********Night Stay Count***********
		int totalNightStay = 0;
		//verify check-In date
		if(scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString().split(DOUBLE_SEPARATOR)[0].equals("1")){
			totalNightStay = 	Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString()) -
								Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString());
		}else{
			totalNightStay = 	Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString()) -
					Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString());
		}
		//verify night count
		ValidationUtil.validateTestStep("Verify Selected Hotel Night Stay Count  on Payment Page",
				paymentPage.getOptionSectionHotelNightsStayText().getText().trim(),Integer.toString(totalNightStay));

		//**********Check-In Date***********
		String dateDays = null;

		//verify check-In date
		if(scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString().split(DOUBLE_SEPARATOR)[0].equals("1")){
			dateDays = Integer.toString(Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString())+1);
		}else{
			dateDays = scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString();
		}

		//get Check-In Date
		String checkInDate = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy");

		//validate Check-In date of Passenger
		ValidationUtil.validateTestStep("Verify Selected Hotel Check-In Date on Payment Page",
				paymentPage.getOptionSelectedHotelCheckInDateText().getText().trim(),checkInDate);

		//**********Check-Out Date***********
		dateDays = scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString();

		//get arribval time
		String checkOutDate = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy");

		//validate Check-Out date of Passenger
		ValidationUtil.validateTestStep("Verify Selected Hotel Check-Out Date on Payment Page",
				paymentPage.getOptionSelectedHotelCheckOutDateText().getText().trim(),checkOutDate);

	}

	//**********************************************************************************************
	// Method : loginToPayPal
	// Description: Method is used to log in to PayPal on payment page
	// Input Arguments:
	// Return: NA
	// Created By : Un Fai Chan
	// Created On : 12/11/2019
	// Reviewed By:
	// Reviewed On:
	//**********************************************************************************************
	public synchronized void loginToPayPal(String loginUserType) {
		//get the login credentials from Json file
		LoginCredentialsData loginCredentialsData =FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(loginUserType);

		TestUtil.switchToWindow(driver,1);
		//Click on Next

		//enter username
		paymentPage.getPayPalEmailTextBox().sendKeys(loginCredentialsData.userName);

		//Click on Next
		paymentPage.getPayPalNextOnEmailButton().click();

		WaitUtil.untilTimeCompleted(5000);

		//enter password
		paymentPage.getPayPalPasswordTextBox().sendKeys(loginCredentialsData.password);

		paymentPage.getPayPalLogInButton().click();
	}

	//**********************************************************************************************
	// Method : Pay with PayPal
	// Description: Method is used to pay with PayPal after logging in
	// Input Arguments:
	// Return: NA
	// Created By : Un Fai Chan
	// Created On : 12/11/2019
	// Reviewed By:
	// Reviewed On:
	//**********************************************************************************************
	public synchronized void payWithPayPal(String paymentType) {
		//wait till page is stable
		WaitUtil.untilPageLoadComplete(driver, (long) 5);


		for (int i = 0; i < paymentPage.getPayPalPaymentMethodsLabelList().size(); i++){
			System.out.println(paymentPage.getPayPalPaymentMethodsLabelList().get(i).getText().trim());
			if (paymentPage.getPayPalPaymentMethodsLabelList().get(i).getText().trim().
					equalsIgnoreCase(paymentType.trim())){
				paymentPage.getPayPalPaymentMethodsLabelList().get(i).click();
				break;
			}
		}


		paymentPage.getPayPalPayNowButton().click();

		ValidationUtil.validateTestStep("User paid with Paypal", true);
		TestUtil.switchToWindow(driver,0);
	}

	public synchronized void payWithPayPal1(String paymentType) {
		//wait till page is stable
		WaitUtil.untilPageLoadComplete(driver, (long) 5);

		TestUtil.switchToWindow(driver,1);

		for (int i = 0; i < paymentPage.getPayPalPaymentMethodsLabelList().size(); i++){
			System.out.println(paymentPage.getPayPalPaymentMethodsLabelList().get(i).getText().trim());
			if (paymentPage.getPayPalPaymentMethodsLabelList().get(i).getText().trim().
					equalsIgnoreCase(paymentType.trim())){
				paymentPage.getPayPalPaymentMethodsLabelList().get(i).click();
				break;
			}

		}


		paymentPage.getPayPalPayNowButton().click();

		ValidationUtil.validateTestStep("User paid with Paypal", true);
		TestUtil.switchToWindow(driver,0);
	}
}


	
	


