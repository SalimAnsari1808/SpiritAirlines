package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.CardDetailsData;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO:[IN:25878] Goldfinger R1: Loyalty LTY: Cannot enroll in Free Spirit and / or $9 Fare Club membership
//Test Case ID: TC91134
//Description: $9FC_Enrollment_CP_BP_Enroll_as_a_$9FC
//Created By : Kartik Chauhan
//Created On : 11-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Mar-2019
//**********************************************************************************************
public class TC91134 extends BaseClass{

	@Parameters ({"platform"})
	@Test(groups = {"ActiveBug","BookPath","MemberEnrollmentUI","NineDFC"})
	public void $9FC_Enrollment_CP_BP_Enroll_as_a_$9FC (@Optional("NA")String platform) {
		//**********************Navigate to 9DFC Enrollment Page***********************
    	//Mention Suite and Browser in reports 
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID TC91134 under BAT Suite on " + platform + " Browser"   , true);
    	}
    	
		//declare constant used during Navigation
		final String LANGUAGE 	= "English";
				
		//open browser 
		openBrowser( platform);
		
		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		
		//************************Validation on 9DFC Enrollment Page*********************
		//Below Xpaths are not added ointo framework
		WebElement NineDFCFooterLink = getDriver().findElement(By.xpath("//div[contains(@class,'footDesktop-left')]//a[contains(text(),'$9 Fare Club®')]"));
		
		//declare constant used during Validation
		final String[] tabNames		= {"Account","Contact","Billing"};
		final String[] titleValues 	= {"Mr.", "Mrs.","Ms."};
		final String[] suffixValues = {"Jr.", "Sr."};
		final int SECTION_TAB_SIZE 	= 3;
		final int FIRST_INDEX  		= 0;
		final int SECOND_INDEX 		= 1;
		final int THIRD_INDEX  		= 2;
		final int FOURTH_INDEX 		= 3;
		final String RED_COLOR 		= "rgb(220, 0, 0)";
		final String ASTERISK_SYMBOL= "*";
		final String ASTERISK_NONE	= "none";
		final String IMPORTANT_VERBIAGE = "IMPORTANT";
		final String ENTERYOURFIRST_VERBIAGE = "Enter your first, middle, last name and any suffix, exactly as it appears on your government ID that you plan to use at the airport.";
		
		//declare variable used in validation
		String asteriskColor = null;
		String asteriskSym 	 = null;
				
		//click on 9DFC sign up link
		NineDFCFooterLink.click();
		
		//wait till page is load is complete
		WaitUtil.untilPageLoadComplete(getDriver());
		
		//get passenger First and last name
		MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("NineFCAccountTab");
		
		//Below Xpaths are not added into framework
		List<WebElement> EnrollmentTabs = getDriver().findElements(By.xpath("//a[contains(@class,'nav-link ')]"));

		//**********************************
		//*********Enrollment Tabs**********
		//**********************************
		//Verify the header of information table size
		ValidationUtil.validateTestStep("For 9DFC Sign up, Header of Information table have 3 section", EnrollmentTabs.size()==SECTION_TAB_SIZE);
		
		//verify Account tab on Enrollment page
		ValidationUtil.validateTestStep("For 9DFC Sign up, Header of Information table conatins Account Tab", EnrollmentTabs.get(FIRST_INDEX).getText().trim(),tabNames[FIRST_INDEX]);
		
		//verify Contact tab on Enrollment page
		ValidationUtil.validateTestStep("For 9DFC Sign up, Header of Information table conatins Contact Tab", EnrollmentTabs.get(SECOND_INDEX).getText().trim(),tabNames[SECOND_INDEX]);
		
		//verify Billing tab on Enrollment page
		ValidationUtil.validateTestStep("For 9DFC Sign up, Header of Information table conatins Billing Tab", EnrollmentTabs.get(THIRD_INDEX).getText().trim(),tabNames[THIRD_INDEX]);
		
		//**********************************
		//*********Title Drop Down**********
		//**********************************
		//verify the Account tab title tab
		//value will appear from second index as first value is empty
		pageObjectManager.getMemberEnrollmentPage().getUserFirstNameTextBox().click();
		Select drpdnTitle = new Select(pageObjectManager.getMemberEnrollmentPage().getUserTitleDropDown());
		List<WebElement> drpdnTitleValues = drpdnTitle.getOptions();
		
		//verify the first value of title drop down
		ValidationUtil.validateTestStep("For 9DFC Sign up,Title drop down first value is Mr.",drpdnTitleValues.get(SECOND_INDEX).getText().trim(),titleValues[FIRST_INDEX]);
		
		//verify the second value of title drop down
		ValidationUtil.validateTestStep("For 9DFC Sign up,Title drop down second value is Mrs.",drpdnTitleValues.get(THIRD_INDEX).getText().trim(),titleValues[SECOND_INDEX]);
		
		//verify the third value of title drop down
		ValidationUtil.validateTestStep("For 9DFC Sign up,Title drop down second value is Ms.",drpdnTitleValues.get(FOURTH_INDEX).getText().trim(),titleValues[THIRD_INDEX]);
		
		//select title
		drpdnTitle.selectByVisibleText(memberEnrollmentData.title);
		
		//**********************************
		//*********First Name Box***********
		//**********************************
		//get the asterisk symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getUserFirstNameText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getUserFirstNameText(), "content");	

		//verify First Name label should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the First Name Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify First Name label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify First Name label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the first name
		pageObjectManager.getMemberEnrollmentPage().getUserFirstNameTextBox().sendKeys(memberEnrollmentData.firstName);
		
		//**********************************
		//*********Middle Name Box**********
		//**********************************
		//get the asterisk symbol and color
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getUserMiddleNameText(), "content");
		
		//Should not have a red astersik since it is not required
//		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Middle Name should not have a red astersik since it is not required", asteriskSym,ASTERISK_NONE);

		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Middle Name should not have a red astersik since it is not required", asteriskSym.equalsIgnoreCase(ASTERISK_NONE)||asteriskSym.equals(""));

		//enter middle name
		pageObjectManager.getMemberEnrollmentPage().getUserMiddleNameTextBox().sendKeys(memberEnrollmentData.middleName);
		
		//**********************************
		//*********Last Name Box************
		//**********************************
		//get the asterisk symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getUserLastNameText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getUserLastNameText(), "content");	

		//verify Last Name label should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Last Namd Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Last Name label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Last Name label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter last name
		pageObjectManager.getMemberEnrollmentPage().getUserLastNameTextBox().sendKeys(memberEnrollmentData.lastName);
		
		//**********************************
		//*********Suffix Drop Down*********
		//**********************************
		//verify the Suffix Drop down
		Select drpdnSuffix = new Select(pageObjectManager.getMemberEnrollmentPage().getUserSuffixDropDown());
		List<WebElement> drpdnSuffixValues = drpdnSuffix.getOptions();
		
		//verify the first value of Suffix drop down
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Suffix drop down first value is Jr.",drpdnSuffixValues.get(SECOND_INDEX).getText().trim(),suffixValues[FIRST_INDEX]);

		//verify the second value of Suffix drop down
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Suffix drop down second value is Sr.",drpdnSuffixValues.get(THIRD_INDEX).getText().trim(),suffixValues[SECOND_INDEX]);
			
		//select suffix
		drpdnSuffix.selectByVisibleText(memberEnrollmentData.suffix);
		
		//**********************************
		//*********Date OF Birth Box********
		//**********************************
		//get the asterisk symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getUserDateOfBirthText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getUserDateOfBirthText(), "content");	

		//verify Date of Birth label should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Date of Birth Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Date of Birth label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Date of Birth label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the Date of Birth
		pageObjectManager.getMemberEnrollmentPage().getUserDateOfBirthTextBox().sendKeys(memberEnrollmentData.dob);
		
		//******************************
		//*********IMPORTANT Verbiage***
		//******************************
		//Validate IMPORTANT Verbiage is displaying
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the IMPORTANT Verbiage is displaying", pageObjectManager.getMemberEnrollmentPage().getImportantText().getText().trim().toUpperCase(),IMPORTANT_VERBIAGE);

		//Validate 'Enter Your First Name..' Verbiage is displaying
		ValidationUtil.validateTestStep("Verify 'Enter Your First Name..' Verbiage is displaying", pageObjectManager.getMemberEnrollmentPage().getImportantText().getText().trim(),ENTERYOURFIRST_VERBIAGE);
		
		//*****************************
		//*********Email Box***********
		//*****************************
		//get the asterisk symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getEmailText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getEmailText(), "content");	

		//verify Email Box should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Email Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Email label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Email label color of asterisk", asteriskColor,RED_COLOR);
		
		//Create variable to generate email id
		String emailvalue = TestUtil.currentDateTimeString() + memberEnrollmentData.email;
		
		//enter the Email
		pageObjectManager.getMemberEnrollmentPage().getUserEmailIdTextBox().sendKeys(emailvalue);
		
		//**********************************
		//*********Confirm Email Box***********
		//**********************************
		//get the asterisk symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getConfirmEmailText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getConfirmEmailText(), "content");	

		//verify Confirm Email Box should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Confirm Email Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Confirm Email label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Confirm Email label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the Confirm Email
		pageObjectManager.getMemberEnrollmentPage().getUserConfirmEmailIdTextBox().sendKeys(emailvalue);
		
		//**********************************
		//*********Create Password Box******
		//**********************************
		List<WebElement> asteriskElement = pageObjectManager.getMemberEnrollmentPage().getCreateAPasswordText().findElements(By.tagName("span"));
		
		for(WebElement asteriskSpan : asteriskElement) {
			if(asteriskSpan.getAttribute("class").contains("color-error")) {
				asteriskSym = asteriskSpan.getText();
				asteriskColor = asteriskSpan.getCssValue("color");
			}
			
		}

		//verify Create Password Box should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("Verify the Create Password Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);

		if(asteriskColor.contains("rgba")){
			//verify Confirm Password label color of asterisk
			ValidationUtil.validateTestStep("Verify Create Password label color of asterisk", asteriskColor,"rgba(220, 0, 0, 1)");
		}else if(asteriskColor.contains("rgb")){
			//verify Confirm Password label color of asterisk
			ValidationUtil.validateTestStep("Verify Create Password label color of asterisk", asteriskColor,"rgb(220, 0, 0)");
		}
				
		//enter the Create Password
		pageObjectManager.getMemberEnrollmentPage().getUserCreateAPasswordTextBox().sendKeys(memberEnrollmentData.createPassword);
		
		//****************************************
		//*********Confirm Password Box***********
		//****************************************
		//get the span element
		asteriskElement = pageObjectManager.getMemberEnrollmentPage().getConfirmAPasswordText().findElements(By.tagName("span"));
		
		//loop through all elelment
		for(WebElement asteriskSpan : asteriskElement) {
			if(asteriskSpan.getAttribute("class").contains("color-error")) {
				//get the Confirm Password symbol and color
				asteriskSym = asteriskSpan.getText();
				asteriskColor = asteriskSpan.getCssValue("color");
			}
			
		}

		//verify Confirm Password Box should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("Verify the Confirm Password Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);

		if(asteriskColor.contains("rgba")){
			//verify Confirm Password label color of asterisk
			ValidationUtil.validateTestStep("Verify Confirm Password label color of asterisk", asteriskColor,"rgba(220, 0, 0, 1)");
		}else if(asteriskColor.contains("rgb")){
			//verify Confirm Password label color of asterisk
			ValidationUtil.validateTestStep("Verify Confirm Password label color of asterisk", asteriskColor,"rgb(220, 0, 0)");
		}
		
		//enter the Confirm Password
		pageObjectManager.getMemberEnrollmentPage().getConfirmAPasswordTextBox().sendKeys(memberEnrollmentData.confirmPassowrd);
		
		//************************************
		//*********Continue To Step2 Button***
		//************************************
		//Validate Continue To Step2 Button is displaying
		ValidationUtil.validateTestStep("Verify the Continue To Step2 Button is displaying",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getMemberEnrollmentPage().xpath_ContinueToStep2Button)));
			
		//verify Continue To Step2 Button Clicked successfully
		pageObjectManager.getMemberEnrollmentPage().getContinueToStep2Button().click();
		
		//****************************************************************************************************************************
		//**********************************************CONTACT TAB**************************************************************
		//****************************************************************************************************************************
		memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");
		
		//*******************************
		//*********Address Box***********
		//*******************************
		//get the Address symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getAddressText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getAddressText(), "content");	

		//verify Address Box should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Address Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Address label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Address label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the Address
		pageObjectManager.getMemberEnrollmentPage().getAddressTextBox().sendKeys(memberEnrollmentData.address1);
		
		//**********************************
		//*********City Box***********
		//**********************************
		//get the City symbol and color
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCityText(), "content");	

		//verify City Box should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the City Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify City label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify City label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the City
		pageObjectManager.getMemberEnrollmentPage().getCityTextBox().sendKeys(memberEnrollmentData.city);	
		
		//**********************************
		//*********Zip/Postal Code Box******
		//**********************************
		//get the Zip Code symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getZipText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getZipText(), "content");	

		//verify Zip Code should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Zip Code Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Zip Code label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Zip Code label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the Zip Code
		pageObjectManager.getMemberEnrollmentPage().getPostalCodeTextBox().sendKeys(memberEnrollmentData.zipCode);
		
		//**********************************
		//*********Country DropDown***********
		//**********************************
		//get the Country DropDown symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCountryText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCountryText(), "content");	

		//verify Country should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Country Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Country label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Country label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the Country
		TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getCountryDropDown(),memberEnrollmentData.country);
		
		//**********************************
		//*********State/Province DropDown***********
		//**********************************
		//get the State symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getStateText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getStateText(), "content");	

		//verify State Box should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the State Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify State label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify State label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the State
//		pageObjectManager.getMemberEnrollmentPage().getStateDropDown().sendKeys(memberEnrollmentData.state);
		TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getStateDropDown(),memberEnrollmentData.state);
		
		//**********************************
		//*********PrimaryPhone TextBox***********
		//**********************************
		//get the PrimaryPhone symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getPrimaryPhoneText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getPrimaryPhoneText(), "content");	

		//verify PrimaryPhone should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the PrimaryPhone Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify PrimaryPhone label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify PrimaryPhone label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the PrimaryPhone
		pageObjectManager.getMemberEnrollmentPage().getPrimaryPhoneTextBox().sendKeys(memberEnrollmentData.phoneNumber);
		
		//**********************************
		//*********Home Airport TextBox***********
		//**********************************
		//get the Home Airport symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getHomeAirportText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getHomeAirportText(), "content");	

		//verify Home Airport should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Home Airport Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Home Airport label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Home Airport label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the Home Airport
		pageObjectManager.getMemberEnrollmentPage().getHomeAirportText().click();
		TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getPrimaryAirportDropDown(),memberEnrollmentData.homeAirport);
		
		//**********************************
		//*********Continue To Step3 Button***QUERY
		//**********************************
		//Validate Continue To Step3 Button is displaying
		ValidationUtil.validateTestStep("Verify the Continue To Step3 Button is displaying",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getMemberEnrollmentPage().xpath_ContinueToStep3Button)));
			
		//verify Continue To Step3 Button Clicked successfully
		pageObjectManager.getMemberEnrollmentPage().getContinueToStep3Button().click();

		WaitUtil.untilPageLoadComplete(getDriver());
					
		//****************************************************************************************************************************
		//**********************************************BILLING TAB*******************************************************************
		//****************************************************************************************************************************
		CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType("MasterCard");
		//**********************************
		//*********Name On Card TextBox*****
		//**********************************
		//get the Name On Card symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getnameOnCardText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getnameOnCardText(), "content");	

		//verify Name On Card should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Name On Card Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Name On Card label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Name On Card label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the Name On Card
		pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().click();
		pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().sendKeys(cardDetailsData.cardHolderName);
		
		//**********************************
		//*********Card Number**************
		//**********************************
		//get the Card Number symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCardNumberText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCardNumberText(), "content");	

		
		//System.out.println(asteriskSym.contains(ASTERISK_SYMBOL));
		//verify Card Number should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Card Number Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Card Number label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Card Number label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the Card Number
		pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().sendKeys(cardDetailsData.cardNumber);
		
		//**********************************
		//*********Expiration Date**************
		//**********************************
		//get the Expiration Date symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getExpirationDateText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getExpirationDateText(), "content");	

		//verify Expiration Date should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Expiration Date Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Expiration Date label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Expiration Date label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the Expiration Date
		pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox().sendKeys(cardDetailsData.expirationDate);
		
		//**********************************
		//*********Security Code**************
		//**********************************
		//get the Security Code symbol and color
		asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getSecurityCodeText(), "color");
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getSecurityCodeText(), "content");	

		//verify Security Code should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the Security Code Symbol must have asterisk to show it is required", asteriskSym,ASTERISK_SYMBOL);
		
		//verify Security Code label color of asterisk
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Security Code label color of asterisk", asteriskColor,RED_COLOR);
		
		//enter the Security Code
		pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox().sendKeys(cardDetailsData.securityCode);
		
		//**********************************
		//*********Billing Address CheckBox*
		//**********************************
		//verify the existance of Text 'Billing Address'
		ValidationUtil.validateTestStep("Billing Address label is displaying",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getMemberEnrollmentPage().xpath_BillingAddressText)));
		
		//get the Billing Address symbol and color
		asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getMemberEnrollmentPage().getBillingAddressText(), "content");
				
		//Should not have a red astersik since it is not required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify Billing Address should not have a red astersik since it is not required",asteriskSym.equalsIgnoreCase(ASTERISK_NONE)||asteriskSym.equals(""));
		
		//Select Billing Address Checkbox
		pageObjectManager.getMemberEnrollmentPage().getPleaseUseSameAddressText().click();
		
		//**********************************
		//*********9DFC Terms and Condition*
		//**********************************
		//get the 9DFC Terms and Condition symbol and color
		ValidationUtil.validateTestStep("Nine DFC Terms & Condition label is displaying",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getMemberEnrollmentPage().xpath_DFCTermsAndConditionHeaderText)));
		
		//verify 9DFC Terms and Condition should have a asterisk to show that it is required
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the 9DFC Terms and Condition Symbol must have asterisk to show it is required", asteriskSym.equalsIgnoreCase(ASTERISK_NONE)||asteriskSym.equals(""));
				
		//Select the 9DFC Terms and Condition
		pageObjectManager.getMemberEnrollmentPage().getAgreeTermsAndConditionText().click();
		
		//**********************************
		//*********SignUp and Start Button***
		//**********************************
		//get the SignUp and Start symbol and color
		ValidationUtil.validateTestStep("Nine DFC SignUp Button is displaying",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getMemberEnrollmentPage().xpath_DFCSignUpButton)));
		
		//Validate SignUp and Start Button is displaying
		ValidationUtil.validateTestStep("For 9DFC Sign up, Verify the SignUp and Start Button is displaying", asteriskSym.equalsIgnoreCase(ASTERISK_NONE)||asteriskSym.equals(""));
			
		//verify SignUp and Start Button Clicked successfully
		pageObjectManager.getMemberEnrollmentPage().getDFCSignUpButton().click();
		
		WaitUtil.untilPageLoadComplete(getDriver());
		
		final String DFC_WELCOME = "Successful registration";
		
		//verify popup header for 9DFC account
		ValidationUtil.validateTestStep("Verify 9DFC Successful registration popuop appear on Enrollment Page",
				pageObjectManager.getMemberEnrollmentPage().getPopUpHeaderText().getText().trim(),DFC_WELCOME);
		
        //close 9DFC Registration popup
		//TODO:[IN:25878] Goldfinger R1: Loyalty LTY: Cannot enroll in Free Spirit and / or $9 Fare Club membership
        pageObjectManager.getMemberEnrollmentPage().getCloseImage().click();
		
	}
}
	
