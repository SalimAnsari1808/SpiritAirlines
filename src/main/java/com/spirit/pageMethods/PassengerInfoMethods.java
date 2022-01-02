package com.spirit.pageMethods;

import com.spirit.pageObjects.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.PassengerInfoPage;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;

import java.util.List;
import java.util.Random;

public class PassengerInfoMethods {
	
	WebDriver driver;
	PageObjectManager pageObjectManager;
	ScenarioContext scenarioContext;
	PassengerInfoPage passengerInfoPage;
	Header header;
	
	public PassengerInfoMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
		this.driver				= driver;
		this.pageObjectManager 	= pageObjectManager;
		this.scenarioContext  	= scenarioContext;
		passengerInfoPage      	= pageObjectManager.getPassengerInfoPage();
		header					= pageObjectManager.getHeader();
	}

	//**********************************************************************************************
	// Method : fillPassengersMandatoryFields
	// Description: Method is used to fill details of passenger as a Guest
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Apr-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 13-Apr-2019
	//**********************************************************************************************
	public synchronized void fillPassengersMandatoryFields() {
		//declare infant max count
		int infantOnLap = 9;

		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);

		//System.out.println(scenarioContext.isContains(Context.HOMEPAGE_LOGINTYPE));

		//check user is login as guest
		if (!scenarioContext.isContains(Context.HOMEPAGE_LOGINTYPE)) {
			//get adult mandatory details
			PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

			//adult title drop down
			if (!passengerInfoPage.getAdultTitleListDropDown().get(0).getAttribute("class").contains("ng-valid")) {
				JSExecuteUtil.clickOnElement(driver, passengerInfoPage.getAdultTitleListDropDown().get(0));
				TestUtil.selectDropDownUsingVisibleText(passengerInfoPage.getAdultTitleListDropDown().get(0), passengerInfoData.title);
			}

			//adult first name box
			if (!passengerInfoPage.getAdultFirstNameListTextBox().get(0).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);
			}

			//adult last name list box
			if (!passengerInfoPage.getAdultLastNameListTextBox().get(0).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);
			}

			//adult KT Number box
			//if(!passengerInfoPage.getAdultKTNListTextBox().get(0).getAttribute("class").contains("ng-touched")) {
			//	passengerInfoPage.getAdultKTNListTextBox().get(0).sendKeys(passengerInfoData.ktNumber);
			//}

			//adult date of birth
			if (!passengerInfoPage.getAdultDOBListTextBox().get(0).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getAdultDOBListTextBox().get(0).click();
				passengerInfoPage.getAdultDOBListTextBox().get(0).sendKeys(passengerInfoData.dob);
				passengerInfoPage.getAdultDOBListTextBox().get(0).sendKeys(Keys.TAB);
			}
		}

		//loop through all adult passengers
		for (int adultCount = 2; adultCount <= passengerInfoPage.getAdultKTNListTextBox().size(); adultCount++) {
			//get adult mandatory details 		
			PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger" + adultCount);

			//click on first name to make sure section is visible
			passengerInfoPage.getAdultFirstNameListTextBox().get(adultCount - 1).click();

			//adult title drop down
			if (!passengerInfoPage.getAdultTitleListDropDown().get(adultCount - 1).getAttribute("class").contains("ng-valid")) {
				JSExecuteUtil.clickOnElement(driver, passengerInfoPage.getAdultTitleListDropDown().get(adultCount - 1));
				TestUtil.selectDropDownUsingVisibleText(passengerInfoPage.getAdultTitleListDropDown().get(adultCount - 1), passengerInfoData.title);
			}

			//adult first name box
			if (!passengerInfoPage.getAdultFirstNameListTextBox().get(adultCount - 1).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getAdultFirstNameListTextBox().get(adultCount-1).sendKeys(passengerInfoData.firstName);
			}

			//adult last name list box
			if (!passengerInfoPage.getAdultLastNameListTextBox().get(adultCount - 1).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getAdultLastNameListTextBox().get(adultCount - 1).sendKeys(passengerInfoData.lastName);
			}

			//adult last name list box
			//if(!passengerInfoPage.getAdultKTNListTextBox().get(adultCount-1).getAttribute("class").contains("ng-touched")) {
			//	passengerInfoPage.getAdultKTNListTextBox().get(adultCount-1).sendKeys(passengerInfoData.ktNumber);
			//}

			//adult date of birth
			if (!passengerInfoPage.getAdultDOBListTextBox().get(adultCount - 1).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getAdultDOBListTextBox().get(adultCount - 1).click();
				passengerInfoPage.getAdultDOBListTextBox().get(adultCount - 1).sendKeys(passengerInfoData.dob);
				passengerInfoPage.getAdultDOBListTextBox().get(adultCount - 1).sendKeys(Keys.TAB);
			}
		}

		for (int infantCount = 0; infantCount < passengerInfoPage.getInfantTitleListDropDown().size(); infantCount++) {
			//get infant mandatory details 
			PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger" + infantOnLap);

			//click on first name of infant
			passengerInfoPage.getInfantFirstNameListTextBox().get(infantCount).click();

			//infant title drop down
			if (!passengerInfoPage.getInfantTitleListDropDown().get(infantCount).getAttribute("class").contains("ng-valid")) {
				TestUtil.selectDropDownUsingVisibleText(passengerInfoPage.getInfantTitleListDropDown().get(infantCount), passengerInfoData.title);
			}

			//infant first name box
			if (!passengerInfoPage.getInfantFirstNameListTextBox().get(infantCount).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getInfantFirstNameListTextBox().get(infantCount).sendKeys(passengerInfoData.firstName);
			}

			//infant last name list box
			if (!passengerInfoPage.getInfantLastNameListTextBox().get(infantCount).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getInfantLastNameListTextBox().get(infantCount).sendKeys(passengerInfoData.lastName);
			}

			//decrement infant counter by one
			infantOnLap = infantOnLap - 1;
		}

		//user fill all mandatory details
		ValidationUtil.validateTestStep("User Filled Passenger mandatory details on Passenger Information Page", true);
	}

		public synchronized void fillMilitaryPassengerMandatoryFields() {
		//declare constant used in method
		final String BLUE_COLOR = "rgb(0, 123, 255)";
		
		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);
		
		//loop through all adult passengers
		for(int adultCount=1;adultCount<=passengerInfoPage.getAdultKTNListTextBox().size();adultCount++) {
			//get adult mandatory details 		
			PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("MilitaryPax"+adultCount);

			//click on first name to make sure section is visible
			//passengerInfoPage.getAdultFirstNameListTextBox().get(adultCount-1).click();

			//adult title drop down
			if(!passengerInfoPage.getAdultTitleListDropDown().get(adultCount-1).getAttribute("class").contains("ng-valid")) {
				TestUtil.selectDropDownUsingVisibleText(passengerInfoPage.getAdultTitleListDropDown().get(adultCount-1), passengerInfoData.title);
			}
			
			//adult first name box
			if(!passengerInfoPage.getAdultFirstNameListTextBox().get(adultCount-1).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getAdultFirstNameListTextBox().get(adultCount-1).sendKeys(passengerInfoData.firstName);
			}
			
			//adult last name list box
			if(!passengerInfoPage.getAdultLastNameListTextBox().get(adultCount-1).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getAdultLastNameListTextBox().get(adultCount-1).sendKeys(passengerInfoData.lastName);
			}
			
			//adult date of birth
			if(!passengerInfoPage.getAdultDOBListTextBox().get(adultCount-1).getAttribute("class").contains("ng-valid")) {
				passengerInfoPage.getAdultDOBListTextBox().get(adultCount-1).click();
				passengerInfoPage.getAdultDOBListTextBox().get(adultCount-1).sendKeys(passengerInfoData.dob);
				passengerInfoPage.getAdultDOBListTextBox().get(adultCount-1).sendKeys(Keys.TAB);
			}
			
		}
		
		//user fill all mandatory details
		ValidationUtil.validateTestStep("User Filled Military Passenger mandatory details on Passenger Information Page", true);
		
	    //select military checkbox for all passengers
	    for(int adultCounter=0;adultCounter<pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().size();adultCounter++) {
	    	//store current checkbox into web element
	    	WebElement activeMilitary = pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().get(adultCounter);
	    	
	    	//verify checkbox is displayed
	    	if(activeMilitary.isDisplayed()) {
	        	//click on checkbox
	    		activeMilitary.click();
	        	
	    		//wait for 30 sec
	        	WaitUtil.untilTimeCompleted(500);
	        	
	        	//verify checkbox is selected
	        	ValidationUtil.validateTestStep("Verify Active Military checkbox is selected for Passenger:"+(adultCounter+1), JSExecuteUtil.getElementBeforeProperty(driver, activeMilitary, "background-color").equals(BLUE_COLOR));
	    	}
	    }
	}


	public synchronized void fillContactMandatoryFields() {
		//declare constant used in method
		final int FIRST_INDEX = 0;
		String emailValue = null;

		//get passenger First and last name
		PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

		//click on zip code to make sure section is visible
		passengerInfoPage.getContactPersonZipPostalCodeTextBox().click();
		
		//check box contact person
		passengerInfoPage.getPrimaryPassengerIstheContactPersonCheckBox();
		
		//contact first name
		if(passengerInfoPage.getContactPersonFirstNameTextBox().size()>0) {
			if(!passengerInfoPage.getContactPersonFirstNameTextBox().get(FIRST_INDEX).getAttribute("class").contains("ng-valid")) {
				//enter first name
				passengerInfoPage.getContactPersonFirstNameTextBox().get(FIRST_INDEX).sendKeys(passengerInfoData.firstName);
			}
		}
		
		//contact last name
		if(passengerInfoPage.getContactPersonLastNameTextBox().size()>0) {
			if(!passengerInfoPage.getContactPersonLastNameTextBox().get(FIRST_INDEX).getAttribute("class").contains("ng-valid")) {
				//enter last name
				passengerInfoPage.getContactPersonLastNameTextBox().get(FIRST_INDEX).sendKeys(passengerInfoData.lastName);
			}
		}
		
		//get passenger First and last name
		MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");
		
		//address
		if(!passengerInfoPage.getContactPersonAddressTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonAddressTextBox().sendKeys(memberEnrollmentData.address1);
		}
		
		//contact city
		if(!passengerInfoPage.getContactPersonCityTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonCityTextBox().sendKeys(memberEnrollmentData.city);
		}
		
		//contact state
		if(!passengerInfoPage.getContactPersonStateDropDown().getAttribute("class").contains("ng-valid")) {
			TestUtil.selectDropDownUsingVisibleText(passengerInfoPage.getContactPersonStateDropDown(), memberEnrollmentData.state);
		}
		
		//contact zip code
		if(!passengerInfoPage.getContactPersonZipPostalCodeTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonZipPostalCodeTextBox().sendKeys(memberEnrollmentData.zipCode);
		}
		
		//contact country
		if(!passengerInfoPage.getContactPersonCountryDropDown().getAttribute("class").contains("ng-valid")) {
			TestUtil.selectDropDownUsingVisibleText(passengerInfoPage.getContactPersonCountryDropDown(), memberEnrollmentData.country);
		}
		
		//contact personal phone
		if(!passengerInfoPage.getContactPersonPhoneNumberTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonPhoneNumberTextBox().sendKeys(memberEnrollmentData.phoneNumber);
		}
		
		//get the email
		memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("FSAccountTab");
		emailValue = TestUtil.currentDateTimeString() + memberEnrollmentData.email;
		
		//contact personal email
		if(!passengerInfoPage.getContactPersonEmailTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonEmailTextBox().sendKeys(emailValue);
			
			//store customer Email in global variable
			scenarioContext.setContext(Context.CUSTOMER_EMAIL,emailValue);
		}
		
		//contact personal confirm email
		if(!passengerInfoPage.getContactPersonConfirmEmailTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonConfirmEmailTextBox().sendKeys(emailValue);
		}
		
		//contact country phone 
		//passengerInfoPage.getContactPersonPhoneCountryCodeDropDown();
		
		//user fill all mandatory details
		ValidationUtil.validateTestStep("User Filled Contact mandatory details on Passenger Information Page", true);
			
	}



	public synchronized void fillContactNonUSResident() {
		//declare constant used in method
		final int FIRST_INDEX = 0;
		String emailValue = null;

		//get passenger First and last name
		PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

		//click on zip code to make sure section is visible
		passengerInfoPage.getContactPersonZipPostalCodeTextBox().click();

		//check box contact person
		passengerInfoPage.getPrimaryPassengerIstheContactPersonCheckBox();

		//contact first name
		if(passengerInfoPage.getContactPersonFirstNameTextBox().size()>0) {
			if(!passengerInfoPage.getContactPersonFirstNameTextBox().get(FIRST_INDEX).getAttribute("class").contains("ng-valid")) {
				//enter first name
				passengerInfoPage.getContactPersonFirstNameTextBox().get(FIRST_INDEX).sendKeys(passengerInfoData.firstName);
			}
		}

		//contact last name
		if(passengerInfoPage.getContactPersonLastNameTextBox().size()>0) {
			if(!passengerInfoPage.getContactPersonLastNameTextBox().get(FIRST_INDEX).getAttribute("class").contains("ng-valid")) {
				//enter last name
				passengerInfoPage.getContactPersonLastNameTextBox().get(FIRST_INDEX).sendKeys(passengerInfoData.lastName);
			}
		}

		//get passenger First and last name
		MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTabNonUS");

		//contact country
		TestUtil.selectDropDownUsingVisibleText(passengerInfoPage.getContactPersonCountryDropDown(), memberEnrollmentData.country);


		//address
		if(!passengerInfoPage.getContactPersonAddressTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonAddressTextBox().sendKeys(memberEnrollmentData.address1);
		}

		//contact city
		if(!passengerInfoPage.getContactPersonCityTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonCityTextBox().sendKeys(memberEnrollmentData.city);
		}

		//----THE BELOW 2 FIELDS ARE NOT REQUIRED, Bug logged 22474
		passengerInfoPage.getContactPersonStateDropDown().sendKeys(memberEnrollmentData.state);
		passengerInfoPage.getContactPersonZipPostalCodeTextBox().sendKeys(memberEnrollmentData.zipCode);

		//contact state
		if(!passengerInfoPage.getContactPersonStateDropDown().getAttribute("class").contains("ng-valid")) {
			TestUtil.selectDropDownUsingVisibleText(passengerInfoPage.getContactPersonStateDropDown(), memberEnrollmentData.state);
		}

		//contact zip code
		if(!passengerInfoPage.getContactPersonZipPostalCodeTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonZipPostalCodeTextBox().sendKeys(memberEnrollmentData.zipCode);
		}

		//contact personal phone
		if(!passengerInfoPage.getContactPersonPhoneNumberTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonPhoneNumberTextBox().sendKeys(memberEnrollmentData.phoneNumber);
		}

		//get the email
		memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("FSAccountTab");
		emailValue = TestUtil.currentDateTimeString() + memberEnrollmentData.email;

		//contact personal email
		if(!passengerInfoPage.getContactPersonEmailTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonEmailTextBox().sendKeys(emailValue);

			//store customer Email in global variable
			scenarioContext.setContext(Context.CUSTOMER_EMAIL,emailValue);
		}

		//contact personal confirm email
		if(!passengerInfoPage.getContactPersonConfirmEmailTextBox().getAttribute("class").contains("ng-valid")) {
			passengerInfoPage.getContactPersonConfirmEmailTextBox().sendKeys(emailValue);
		}

		//contact country phone

		//TestUtil.selectDropDownUsingVisibleText(passengerInfoPage.getContactPersonPhoneCountryCodeDropDown(););

		//user fill all mandatory details
		ValidationUtil.validateTestStep("User Filled Non US Resident Contact mandatory details on Passenger Information Page", true);

	}


	public synchronized void clickContinueButton() {
		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//click on continue button on PassengerInfo Page
		JSExecuteUtil.clickOnElement(driver, passengerInfoPage.getContinueToBagsButton());

		
		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		WaitUtil.untilPageURLVisible(driver,"book/bags");
		
		//user fill all mandatory details
		ValidationUtil.validateTestStep("User Clicked on Continue button on Passenger Information Page", true);
	}


	//**********************************************************************************************
	// Method : selectSSRPerPassenger
	// Description: Method is used to select Additional Services
	// Input Arguments: String -> ServiceAnimal|PortableOxygen||ServiceAnimal|PortableOxygen
	//							OwnWheelChair-
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 1-May-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 1-May-2019
	//**********************************************************************************************
	public synchronized void selectSSRPerPassenger(String ssrField){

		final String DOUBLE_SAPRATOR = "\\|\\|";
		final String SINGLE_SAPRATOR = "\\|";

		//storing all passenger additional service in array
		String allPassengerSSR[] = ssrField.split(DOUBLE_SAPRATOR);

		//Waiting until page load
		WaitUtil.untilPageLoadComplete(driver);
		WaitUtil.untilTimeCompleted(2000);

		//accessing all passenger
		for (int passengerCount = 0;passengerCount<allPassengerSSR.length;passengerCount++){

			//check SSR is required
			if(allPassengerSSR[passengerCount].equalsIgnoreCase("NotRequired")){
				continue;
			}

			//clicking on Additional Services
			passengerInfoPage.getAdditionalServicesListLinkButton().get(passengerCount).click();

			WaitUtil.untilTimeCompleted(1000);

			//storing all additional services in array
			String ssrList[] = allPassengerSSR[passengerCount].split(SINGLE_SAPRATOR);

			//accessing all additional services
			for (int ssrCount = 0;ssrCount<ssrList.length;ssrCount++){

				//checking additional services equals to given text
				if(ssrList[ssrCount].equalsIgnoreCase("HearingImpaired")){

					//clicking on additional service
					passengerInfoPage.getHearingImpairedListCheckBox().get(passengerCount).click();

					//validating step
					ValidationUtil.validateTestStep("Additional Services Hearing Impaired is selected on Passenger Information Page",true);

				}else if (ssrList[ssrCount].equalsIgnoreCase("ServiceAnimal")){
					//clicking on additional service
					passengerInfoPage.getServiceAnimalListCheckBox().get(passengerCount).click();

					//validating step
					ValidationUtil.validateTestStep("Additional Services Service Animal is selected on Passenger Information Page",true);

				}else if (ssrList[ssrCount].equalsIgnoreCase("PortableOxygen")){
					//clicking on additional service
					passengerInfoPage.getPortableOxygenContainerListCheckBox().get(passengerCount).click();

					//validating step
					ValidationUtil.validateTestStep("Additional Services Portable Oxygen is selected on Passenger Information Page",true);

				}else if (ssrList[ssrCount].equalsIgnoreCase("VisionDisability")){
					//clicking on additional service
					passengerInfoPage.getVisionDisabilityListCheckBox().get(passengerCount).click();

					//validating step
					ValidationUtil.validateTestStep("Additional Services Vision Disability is selected on Passenger Information Page",true);

				}else if (ssrList[ssrCount].equalsIgnoreCase("EmotionalAnimal")){
					//clicking on additional service
					passengerInfoPage.getEmotionalSupportAnimalListCheckBox().get(passengerCount).click();

					//validating step
					ValidationUtil.validateTestStep("Additional Services Emotional Psychiatric Support Animal is selected on Passenger Information Page",true);

				}else if (ssrList[ssrCount].equalsIgnoreCase("Other")){
					//clicking on additional service
					passengerInfoPage.getOtherDisabilityListCheckBox().get(passengerCount).click();

					//validating step
					ValidationUtil.validateTestStep("Additional Services Other is selected on Passenger Information Page",true);

				}else if (ssrList[ssrCount].equalsIgnoreCase("WheelChairNeedFromGate")){
					//clicking on additional service
					passengerInfoPage.getWheelChairToAndFromGateListCheckBox().get(passengerCount).click();

					//validating step
					ValidationUtil.validateTestStep("Additional Services WheelChair Need From Gate is selected on Passenger Information Page",true);

				}else if (ssrList[ssrCount].equalsIgnoreCase("WheelChairNeedFromSeat")){
					//clicking on additional service
					passengerInfoPage.getWheelChairToAndFromSeatListCheckBox().get(passengerCount).click();

					//validating step
					ValidationUtil.validateTestStep("Additional Services WheelChair Need From Seat is selected on Passenger Information Page",true);

				}else if (ssrList[ssrCount].equalsIgnoreCase("WheelChairCompletelyImmobile")){
					//clicking on additional service
					passengerInfoPage.getWheelChairCompletelyImmobileListCheckBox().get(passengerCount).click();

					//validating step
					ValidationUtil.validateTestStep("Additional Services WheelChair Completely Immobile is selected on Passenger Information Page",true);

				}else if (ssrList[ssrCount].toLowerCase().contains("ownwheelchair")) {

					String wheelChairType  = "";
					//clicking on additional service
					passengerInfoPage.getWheelchairIHaveMyOwnListCheckBox().get(passengerCount).click();

					WaitUtil.untilTimeCompleted(2000);

					//BatteryPoweredDryGelCell
					//BatteryPoweredWetCell
					//ManuallyPowered
					if(ssrList[ssrCount].toLowerCase().contains("batterypowereddrygelcell")){
						wheelChairType = "Battery Powered Dry/Gel Cell Battery";
					}else if(ssrList[ssrCount].toLowerCase().contains("batterypoweredwetcell")){
						wheelChairType = "Battery Powered Wet Cell Battery";
					}else if(ssrList[ssrCount].toLowerCase().contains("manuallypowered")){
						wheelChairType = "Manually Powered";
					}

					TestUtil.selectDropDownUsingVisibleText(passengerInfoPage.getWheelChairTypeOfWheelChairDropDown().get(passengerInfoPage.getWheelChairTypeOfWheelChairDropDown().size()-1),wheelChairType);

					//validating step
					ValidationUtil.validateTestStep("Additional Services Own WheelChair is selected on Passenger Information Page",true);

				}else if (ssrList[ssrCount].equalsIgnoreCase("CertifyQualified")){
					//clicking on additional service
					passengerInfoPage.getVoluntaryProvisionofEmergencyServicesProgramListCheckBox().get(passengerCount).click();
					//validating step
					ValidationUtil.validateTestStep("Additional Services \"I certify that I am a qualified law enforcement officer\" is selected on Passenger Information Page",true);

				}else {
					ValidationUtil.validateTestStep("No Additional Services is selected",false);
				}
			}
		}
	}

	//**********************************************************************************************
	// Method : selectMilitaryBagBundlePopup
	// Description: Method is used to select Military Bags or Bundle saving
	// Input Arguments: selectOption -> MilitaryBags/BundleSaving
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 1-May-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 1-May-2019
	//**********************************************************************************************
	public synchronized void selectMilitaryBagBundlePopup(String selectOption){
		//declare constant used in method
		final String BLUE_COLOR2 = "rgb(0, 115, 230)";

		if(selectOption.equalsIgnoreCase("MilitaryBags")){
			//select Military Bags
			passengerInfoPage.getMilitaryBundlePopupMilitaryBagsRadioButton().click();

			//wait for 30 sec
			WaitUtil.untilTimeCompleted(500);

			//validate Military bags are selected
			ValidationUtil.validateTestStep("Verify Military Bags are selected on Military and Bundle popup on Passenger Information Page",
					JSExecuteUtil.getElementAfterProperty(driver, pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton(), "background-color").equals(BLUE_COLOR2));

		}else if(selectOption.equalsIgnoreCase("BundleSaving")){
			//select Bundle Saving
			passengerInfoPage.getMilitaryBundlePopupBundleRadioButton().click();

			//wait for 30 sec
			WaitUtil.untilTimeCompleted(500);

			//validate Military bags are selected
			ValidationUtil.validateTestStep("Verify Military Bags are selected on Military and Bundle popup on Passenger Information Page",
					JSExecuteUtil.getElementAfterProperty(driver, pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupBundleRadioButton(), "background-color").equals(BLUE_COLOR2));
		}

		//click on continue button
		passengerInfoPage.getMilitaryBundlePopupContinueButton().click();

		//wait until Bags page appear on web
		WaitUtil.untilPageLoadComplete(driver,(long)120);

	}

	//**********************************************************************************************
	// Method : verifySelectedBaseFarePassengerInfo
	// Description: Method is used verify the bare fare verbiage and amount in shopping cart
	// Input Arguments: basefareType -> BoostIt/BundleIt
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 11-June-2019
	//**********************************************************************************************
	public synchronized void verifySelectedBaseFarePassengerInfo(String basefareType){
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

		if(basefareType.equalsIgnoreCase("BundleIt")){
			//verify bundle discount text
			ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart on Passenger Information Page", barefareText,BUNDLE_ITINERARY);

			//verify bundle discount amount
			ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart on Passenger Information Page",
					scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), barefareAmount);

		}else if(basefareType.equalsIgnoreCase("BoostIt")){
			//verify boost discount text
			ValidationUtil.validateTestStep("Verify the Boost It Discount text on Dynamic Shopping Cart on Passenger Information Page", barefareText,BOOST_ITINERARY);

			//verify boost discount amount
			ValidationUtil.validateTestStep("Verify the Boost It Discount Price on Dynamic Shopping Cart on Passenger Information Page",
					scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(), barefareAmount);
		}

		//open dynamic shopping cart
		header.getArrowYourItineraryImage().click();

		//wait for 2 sec
		WaitUtil.untilTimeCompleted(2000);
	}

	public synchronized void selectPrimaryDriver(){
		List<WebElement> primaryDriver = pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().findElements(By.tagName("option"));
		TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),primaryDriver.get(1).getText());
	}

}
