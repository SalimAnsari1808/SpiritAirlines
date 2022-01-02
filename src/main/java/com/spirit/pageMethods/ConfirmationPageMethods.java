package com.spirit.pageMethods;

import com.spirit.managers.FileReaderManager;
import com.spirit.pageObjects.PaymentPage;
import com.spirit.utility.JSExecuteUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.ConfirmationPage;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;

import java.util.List;

public class ConfirmationPageMethods {
	WebDriver driver;
	PageObjectManager pageObjectManager;
	ScenarioContext scenarioContext;
	ConfirmationPage confirmationPage;
	PaymentPage	paymentPage;

	public ConfirmationPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
		this.driver=driver;
		this.pageObjectManager = pageObjectManager;
		this.scenarioContext = scenarioContext;
		confirmationPage = pageObjectManager.getConfirmationPage();
		paymentPage = pageObjectManager.getPaymentPage();
	}

	// **********************************************************************************************
	// Method : closeROKTPopup
	// Description: Method is used to close the ROKT popup
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 18-Mar-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 18-Mar-2019
	// ***********************************************************************************************
	public synchronized void closeROKTPopup() {
		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

//		WaitUtil.untilTimeCompleted(5000);
		//check for Return to Home Page popup
		if(TestUtil.verifyElementDisplayed(driver, By.xpath(pageObjectManager.getCommon().xpath_ReturnToHomePageButton))){
			ValidationUtil.validateTestStep("Return To HomePage error message appear on Payment Page", false);
		}

//		//Handle Survey Pop-Up
//		List<WebElement> surveyPopUp = driver.findElements(By.xpath("//iframe[contains(@data-src, 'https://siteintercept.qualtrics.com')]"));
//		int frame = 0;
//		for (WebElement popUp :  surveyPopUp)
//		{
//			if(surveyPopUp.get(frame).isDisplayed()) {
//				JSExecuteUtil.clickOnElement(driver , driver.findElement(By.xpath("(//img[contains(@src, 'close-btn')])[" + (frame + 1) + "]")));
//				ValidationUtil.validateTestStep("The Survey Pop-up has been closed", true);
//			}
//			frame++;
//		}


		List<WebElement> frameList = driver.findElements(By.xpath("//iframe"));

		int frameCount = 0;
		for(frameCount = 0;frameCount<frameList.size();frameCount++){

			if(frameList.get(frameCount).isDisplayed()){
				TestUtil.switchToFrame(driver,frameList.get(frameCount));
				WaitUtil.untilTimeCompleted(1000);
				//
				if(driver.getPageSource().contains("You're all set!")){
					break;
				}else{
					driver.switchTo().defaultContent();
					WaitUtil.untilTimeCompleted(1000);
				}
			}
		}
		if(frameCount==frameList.size()){
			if(!FileReaderManager.getInstance().getConfigReader().getROKTWitchValue()){
				return;
			}else {
//				ValidationUtil.validateTestStep("ROKT Popup is not appear on Confirmation Page", false);
			}
		}

		int buttonCount = 0;
		for(buttonCount = 0;buttonCount<confirmationPage.getROKTPoupCloseButton().size();buttonCount++){
			if(confirmationPage.getROKTPoupCloseButton().get(buttonCount).isDisplayed()&&confirmationPage.getROKTPoupCloseButton().get(buttonCount).getText().length()<=2){
				confirmationPage.getROKTPoupCloseButton().get(buttonCount).click();
				WaitUtil.untilTimeCompleted(1000);
				driver.switchTo().defaultContent();
				break;
			}else {
				System.out.println(confirmationPage.getROKTPoupCloseButton().get(buttonCount).getText());
			}
		}

//		if(buttonCount==confirmationPage.getROKTPoupCloseButton().size()){
//			ValidationUtil.validateTestStep("ROKT Popup close button is not appear on Confirmation Page", false);
//		}
	}

	// **********************************************************************************************
	// Method : storeInformationFromConfirmation
	// Description: Method is used to store information like PNR,Total Paid and LastName
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 18-Mar-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 18-Mar-2019
	// ***********************************************************************************************
	public synchronized void storeInformationFromConfirmation() {
		//declare constant use din method
		final int FIRST_PAX = 0;

		//declare variable used in method
		String passengerName = null;

		//loop through all confirmation Code
		for(WebElement confirmationCode : confirmationPage.getBookingSectionTopConfirmationCode()) {
			//check confirmation code is displayed
			if(confirmationCode.isDisplayed()) {
				if(confirmationCode.getText().contains(":")){
					//get the PNr value
					scenarioContext.setContext(Context.CONFIRMATION_CODE, confirmationCode.getText().split(":")[1].trim());
				}else{
					//get the PNr value
					scenarioContext.setContext(Context.CONFIRMATION_CODE, confirmationCode.getText().trim());
				}
				//break loop
				break;
			}
		}

		//loop through all Car Confirmation Code
		for(WebElement confirmationCode : confirmationPage.getBookingSectionTopCarConfirmationCodeText()) {
			//check confirmation code is displayed
			if(confirmationCode.isDisplayed()) {
				//get the PNr value
				scenarioContext.setContext(Context.CONFIRMATION_CARNETCAR_CODE, confirmationCode.getText().split(":")[1].trim());

				ValidationUtil.validateTestStep("CAR PNR Value is:"+ scenarioContext.getContext(Context.CONFIRMATION_CARNETCAR_CODE).toString(),true );
				//break loop
				break;
			}
		}

		//loop through all Car Confirmation Code
		for(WebElement confirmationCode : confirmationPage.getBookingSectionTopHotelConfirmationCodeText()) {
			//check confirmation code is displayed
			if(confirmationCode.isDisplayed()) {
				//get the PNr value
				scenarioContext.setContext(Context.CONFIRMATION_HBGHOTEL_CODE, confirmationCode.getText().split(":")[1].trim());

				ValidationUtil.validateTestStep("HOTEL PNR Value is:"+ scenarioContext.getContext(Context.CONFIRMATION_HBGHOTEL_CODE).toString(),true );
				//break loop
				break;
			}
		}

		//get passenger name
		passengerName = confirmationPage.getPassengerSectionNamesText().get(FIRST_PAX).getText().trim();

		//store last of passenger 
		scenarioContext.setContext(Context.CONFIRMATION_LASTNAME,passengerName.split(" ")[passengerName.split(" ").length-1].trim());

		//store total paid amount from confirmation page
		scenarioContext.setContext(Context.CONFIRMATION_TOTALPAID,confirmationPage.getTotalPaidPriceText().getText().trim().replace("$", ""));

	}

	// **********************************************************************************************
	// Method : toCityPairTaxesConfirmationpage
	// Description: Method is used to verify all specific Taxes on Confirmation page, Flight Section
	// Input Arguments:
	// Return: NA
	// Created By : Kartik Chauhan
	// Created On : 05-Apr-2019
	// Reviewed By: Salim Ansari
	// Reviewed On: 10-Apr-2019
	// ***********************************************************************************************
	public synchronized void toCityPairTaxesConfirmationpage(List<String> taxList) {

		//declare constant used in method
		final String DUC		= "Regulatory Compliance Charge (Carrier Fee)";
		final String FEC		= "Fuel Charge (Carrier Fee)";

		//Confirmation Page Total Due Verbiage
		pageObjectManager.getConfirmationPage().getTotalPaidBreakDownLink().click();

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//make a click on Flight section
		pageObjectManager.getConfirmationPage().getFlightVerbiageLabel().click();

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		//scroll down to display taxes on screen
		JSExecuteUtil.scrollDown(driver,"400");

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
			List<WebElement> allTaxesVerbiage = pageObjectManager.getPaymentPage().getDepTotalBreakDownFlightText();

			//Bypass when carrier fee is less than $22.99
			if(carrierFee_Under22_99 && (taxList.get(taxCounter).equalsIgnoreCase(DUC) || taxList.get(taxCounter).equalsIgnoreCase(FEC))){
				//System.out.println(taxList.get(taxCounter) + "  ----->   Next pay move hoo gaya");
				continue;
			}

			//create loop for all Taxes and calculate its size
			for (int Counter = 0; Counter < allTaxesVerbiage.size(); Counter++) {
				//Verify 'All Taxes verbiage'
				if (allTaxesVerbiage.get(Counter).isDisplayed()) {
					//verify both taxes are equal
					//if (allTaxesVerbiage.get(Counter).getText().trim().contains(taxList.get(taxCounter))) {
					if (allTaxesVerbiage.get(Counter).getText().trim().replaceAll(" ","").contains(taxList.get(taxCounter).replaceAll(" ",""))) {
						//set flag to true
						taxFlag = true;

						break;
					}
				}

			}

			//check for additional tax
			if (allTaxesVerbiage.size()>taxList.size()){
				//verify New tax found on Payment Page
				ValidationUtil.validateTestStep("New tax found on Confirmation Page",false);

			}

			//validate for all the taxes in console
			ValidationUtil.validateTestStep("Verifing Tax verbiages From  " + scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT)+ " To " + scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT) + " City pair of Tax " + (taxList.get(taxCounter)) + " on Confirmation Page", taxFlag);
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
				if(TestUtil.verifyElementDisplayed(driver,By.xpath(confirmationPage.xpath_OptionSectionFlightFlexHeaderText))){
					//check for language
					if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
						//verify flight flex verbiage for english
						ValidationUtil.validateTestStep("User verify Flight Flex Sub Header verbiage in Option Section on Confirmation Page",
								confirmationPage.getOptionSectionFlightFlexSubHeaderText().getText().trim(),ENGLIGH_FLIGHTFLEX);
					}else{
						//verify flight flex verbiage for spanish
						ValidationUtil.validateTestStep("User verify Flight Flex Sub Header verbiage in Option Section on Confirmation Page",
								confirmationPage.getOptionSectionFlightFlexSubHeaderText().getText().trim(),SPANISH_FLIGHTFLEX);
					}
				}else{
					//verofy flight flex is not visible
					ValidationUtil.validateTestStep("Flight Flex is not appear in option Section on Confirmation Page",false);
				}
				//check for shortcut security
			}else if(currentOptions.equalsIgnoreCase("ShortCutSecurity")){
				//check shortcut security is visible
				if(TestUtil.verifyElementDisplayed(driver,By.xpath(confirmationPage.xpath_OptionSectionShortcutSecurityHeaderText))){
					//check for language
					if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
						//verify fshortcut security verbiage for english
						ValidationUtil.validateTestStep("User verify ShortCut Security Sub Header verbiage in Option Section on Confirmation Page",
								confirmationPage.getOptionSectionShortcutSecuritySubHeaderText().getText().trim(),ENGLISH_SHORTCUTSECURITY);
					}else{
						//verify fshortcut security verbiage for spanish
						ValidationUtil.validateTestStep("User verify ShortCut Security Sub Header verbiage in Option Section on Confirmation Page",
								confirmationPage.getOptionSectionShortcutSecuritySubHeaderText().getText().trim(),SPANISH_SHORTCUTSECURITY);
					}
				}else{
					//verify shortcut security is not visible
					ValidationUtil.validateTestStep("ShortCut Security is not appear in option Section on Confirmation Page",false);
				}
				//verify for shortcut boarding
			}else if(currentOptions.equalsIgnoreCase("ShortCutBoarding")){
				//verify shortcut boarding is visible
				if(TestUtil.verifyElementDisplayed(driver,By.xpath(confirmationPage.xpath_OptionSectionShortcutBoardingHeaderText))){
					//check for language
					if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
						//verify shortcut boarding in english
						ValidationUtil.validateTestStep("User verify ShortCut Boarding Sub Header verbiage in Option Section on Confirmation Page",
								confirmationPage.getOptionSectionShortcutBoardingSubHeaderText().getText().trim(),ENGLISH_SHORTCUTBOARDING);
					}else{
						//verify shortcut boarding in spanish
						ValidationUtil.validateTestStep("User verify ShortCut Boarding Sub Header verbiage in Option Section on Confirmation Page",
								confirmationPage.getOptionSectionShortcutBoardingSubHeaderText().getText().trim(),SPANISH_SHORTCUTBOARDING);
					}
				}else{
					//verify shortcut boarding is not visible
					ValidationUtil.validateTestStep("ShortCut Boarding is not appear in option Section on Confirmation Page",false);
				}
			}else if(currentOptions.equalsIgnoreCase("CheckInOption:AirportAgent")){
				if (TestUtil.verifyElementDisplayed(paymentPage.getOptionSectionCheckInHeaderText())) {
					//check for language
					if (scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")) {
						//verify  Check In in english
						ValidationUtil.validateTestStep("User verify Check-in Sub Header verbiage in Option Section on Confirmation Page",
								paymentPage.getOptionSectionCheckInSubHeaderText().getText().trim(), ENGLISH_CHECKIN);
					} else {
						//verify Check In in spanish
						ValidationUtil.validateTestStep("User verify Check-in Sub Header verbiage in Option Section on Confirmation Page",
								paymentPage.getOptionSectionCheckInSubHeaderText().getText().trim(), SPANISH_CHECKIN);
					}
				} else {
					//verify Check In is not visible
					ValidationUtil.validateTestStep("Check-In is not appear in option Section on Confirmation Page", false);
				}
			}else{
				//verify shortcut boarding is not visible
				ValidationUtil.validateTestStep( currentOptions + " is not appear in option Section on Confirmation Page",currentOptions,"Not Found");
			}
		}
	}

	//**********************************************************************************************
	// Method : verifyTravelInsuranceSection
	// Description: Method is used to verify Travel Insurance on confirmation page
	// Input Arguments:
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 25-Apr-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 25-Apr-2019
	// ***********************************************************************************************
	public synchronized void verifyTravelInsuranceSection(){
		//declare consnat used in method
		final String ENGLISH_SUBHEADER1	= "Policy Number:";
		final String ENGLISH_SUBHEADER2	= "Primary Insured Name:";
		final String SPANISH_SUBHEADER1	= "Número de Póliza:";
		final String SPANISH_SUBHEADER2	= "Nombre del Asegurado Primario:";
		final String ENGLISH_POLICYLINK	= "your policy online";
		final String SPANISH_POLICYLINK	= "su póliza en linea ";
		final String ENGLISH_ADD_INFO	= "For additional questions about your policy please contact Travel Guard directly at 866-877-3191.";
		final String SPANISH_ADD_INFO	= "Para preguntas adicionales acerca de su póliza, por favor comuníquese con Travel Guard directamente al 866-877-3191.";

		final int FIRST_INDEX			= 0;
		final int SECOND_INDEX			= 1;

		if(TestUtil.verifyElementDisplayed(driver,By.xpath(confirmationPage.xpath_TravelInsuranceHeaderText))){
			if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
				//verify policy number text
				ValidationUtil.validateTestStep("Verify Policy Nummber in Travel Insurance Setion on Confirmation Page",
						confirmationPage.getTravelInsuranceSubHeaderText().get(FIRST_INDEX).getText(),ENGLISH_SUBHEADER1);

				//verify primary insured Name text
				ValidationUtil.validateTestStep("Verify Primary Insured Name in Travel Insurance Setion on Confirmation Page",
						confirmationPage.getTravelInsuranceSubHeaderText().get(SECOND_INDEX).getText(),ENGLISH_SUBHEADER2);

				//verify policy link
				ValidationUtil.validateTestStep("Verify Your Policy Online in Travel Insurance Setion on Confirmation Page",
						confirmationPage.getTravelInsurancePolicyOnlineLink().getText(),ENGLISH_POLICYLINK);

				//verify additional info text
				ValidationUtil.validateTestStep("Verify Additional Questions text in Travel Insurance Setion on Confirmation Page",
						confirmationPage.getTravelInsuranceAdditionalQuestionsText().getText(),ENGLISH_ADD_INFO);
			}else{
				//verify policy number text
				ValidationUtil.validateTestStep("Verify Policy Nummber in Travel Insurance Setion on Confirmation Page",
						confirmationPage.getTravelInsuranceSubHeaderText().get(FIRST_INDEX).getText(),SPANISH_SUBHEADER1);

				//verify primary insured Name text
				ValidationUtil.validateTestStep("Verify Primary Insured Name in Travel Insurance Setion on Confirmation Page",
						confirmationPage.getTravelInsuranceSubHeaderText().get(SECOND_INDEX).getText(),SPANISH_SUBHEADER2);

				//verify policy link
				ValidationUtil.validateTestStep("Verify Your Policy Online in Travel Insurance Setion on Confirmation Page",
						confirmationPage.getTravelInsurancePolicyOnlineLink().getText(),SPANISH_POLICYLINK);

				//verify additional info text
				ValidationUtil.validateTestStep("Verify Additional Questions text in Travel Insurance Setion on Confirmation Page",
						confirmationPage.getTravelInsuranceAdditionalQuestionsText().getText(),SPANISH_ADD_INFO);
			}

			//verify policy number is valid
			ValidationUtil.validateTestStep("Verify Policy Number value in Travel Insurance Setion on Confirmation Page",
					confirmationPage.getTravelInsuranceSubHeaderValuesText().get(FIRST_INDEX).getText().length()>=9);


			//store on global varaiable
			scenarioContext.setContext(Context.CONFIRMATION_TRAVELINSURANCE,confirmationPage.getTravelInsuranceSubHeaderValuesText().get(FIRST_INDEX).getText().trim());

			//verify policy insured name
			ValidationUtil.validateTestStep("Verify Primary Insured Name value in Travel Insurance Setion on Confirmation Page",
					confirmationPage.getTravelInsuranceSubHeaderValuesText().get(SECOND_INDEX).getText(),confirmationPage.getPassengerSectionNamesText().get(FIRST_INDEX).getText().split("\\.")[1].trim());

			//verify policy link working
			ValidationUtil.validateTestStep("Verify Your Policy Online link in Travel Insurance Setion on Confirmation Page",
					TestUtil.verifyLinks(confirmationPage.getTravelInsurancePolicyOnlineLink().getAttribute("href")));

		}else{
			//verify TG section not appear
			ValidationUtil.validateTestStep("Verify Travel Insurance Section is not appear on Confirmation Page", false);
		}
	}

	//**********************************************************************************************
	// Method : verifySelectedBaseFareConfirmation
	// Description: Method is used to verify below points
	//				1.Verify pre-selected Options for Barer Fare
	//				2.Verify Bare Fare discount on Total Paid Amount section
	// Input Arguments: basefareType -> BoostIt/BundleIt
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 13-June-2019
	//**********************************************************************************************
	public synchronized void verifySelectedBaseFareConfirmation(String basefareType) {
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
		//*********Bare Fare Amount in Total Paid Amount***********
		//********************************************************
		//open toatl paid amount
		confirmationPage.getTotalPaidBreakDownLink().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);

		if (basefareType.equalsIgnoreCase("BundleIt")){
			//verify bundle fare discount
			if(TestUtil.verifyElementDisplayed(driver,By.xpath(confirmationPage.xpath_TotalDueBundleDiscountText))){
				//verify bundle fare price
				ValidationUtil.validateTestStep("Verify the Bundle It Discount price appear on Confirmation Page",
						scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(),confirmationPage.getTotalDueBundleDiscountPriceText().getText().replace("-","" ));
			}else{
				ValidationUtil.validateTestStep("Verify the Bundle It Discount appear on Confirmation Page",false);
			}
		}else if(basefareType.equalsIgnoreCase("BoostIt")){
			//verify bundle fare discount
			if(TestUtil.verifyElementDisplayed(driver,By.xpath(confirmationPage.xpath_TotalDueBoostDiscountPriceText))){
				//verify bundle fare price
				ValidationUtil.validateTestStep("Verify the Bundle It Discount price appear on Confirmation Page",
						scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(),confirmationPage.getTotalDueBoostDiscountPriceText().getText().replace("-","" ).replace(",",""));
			}else{
				ValidationUtil.validateTestStep("Verify the Bundle It Discount appear on Confirmation Page",false);
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
						ValidationUtil.validateTestStep("Verify Car Type information for Selected Car on Confirmation Page",
								paymentPage.getOptionSectionCarCarModelText().getText(),carDetails.replace("CarModel:",""));

						break;

					case "CarType":
						//verify Car Model
						ValidationUtil.validateTestStep("Verify Car Model information for Selected Car on Confirmation Page",
								carDetails.replace("CarType:",""),paymentPage.getOptionSectionCarRentalAgencyText().getText());

						break;

					case "PickUpPoint":
						String pickUpAddress = paymentPage.getOptionSectionCarPickUpAddressText().get(0).getText() + " " + paymentPage.getOptionSectionCarPickUpAddressText().get(1).getText();

						//verify PickUp Address
						ValidationUtil.validateTestStep("Verify PickUp Address Information information for Selected Car on Confirmation Page",
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
					ValidationUtil.validateTestStep("Verify Pick Up information for Selected Car on Confirmation Page",
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
					ValidationUtil.validateTestStep("Verify Drop OFF information for Selected Car on Confirmation Page",
							paymentPage.getOptionSectionCarDropOffTimeText().getText(),dropOff);

					break;
				}
			}
		}else{
			ValidationUtil.validateTestStep("Selected Car details is not displayed on Confirmation page", false);
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

					ValidationUtil.validateTestStep("Verify Selected Hotel Name on Confirmation Page",
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

					ValidationUtil.validateTestStep("Verify Selected Hotel Address on Confirmation Page",
							addressFlag);

					break;
			}
		}

		//**********Room Count***********
		//verify room count
		ValidationUtil.validateTestStep("Verify Selected Hotel Rooms count on Confirmation Page",
				paymentPage.getOptionSectionHotelRoomsText().getText().trim(),scenarioContext.getContext(Context.HOMEPAGE_ROOMS).toString());

		//**********Passenger Count***********
		//get total passengers
		int paxCount = 	Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString()) +
				Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString())+
				Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString())+
				Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTLAP_COUNT).toString());

		//verify guest count
		ValidationUtil.validateTestStep("Verify Selected Hotel Guest count on Confirmation Page",
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
		ValidationUtil.validateTestStep("Verify Selected Hotel Night Stay Count  on Confirmation Page",
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
		ValidationUtil.validateTestStep("Verify Selected Hotel Check-In Date on Confirmation Page",
				paymentPage.getOptionSelectedHotelCheckInDateText().getText().trim(),checkInDate);

		//**********Check-Out Date***********
		dateDays = scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString();

		//get arribval time
		String checkOutDate = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy");

		//validate Check-Out date of Passenger
		ValidationUtil.validateTestStep("Verify Selected Hotel Check-Out Date on Confirmation Page",
				paymentPage.getOptionSelectedHotelCheckOutDateText().getText().trim(),checkOutDate);

	}
}