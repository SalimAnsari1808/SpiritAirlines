package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.FSEnrollmentErrorData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90931
//Description : FS Enroll _CP_ FS Enroll general
//Created By  : Kartik Chauhan
//Created On  : 11-Mar-2019
//Reviewed By : Salim Ansari
//Reviewed On : 14-Mar-2019
//**********************************************************************************************

public class TC90931 extends BaseClass {
	@Parameters ({"platform"})
	@Test(groups = {"FreeSpirit","MemberEnrollmentUI","BookPath"})
	public void FS_Enroll_CP_FS_Enroll_general (@Optional("NA")String platform){
		/******************************************************************************
		 ***********************Navigate to FS Enrollment Page*************************
		 ******************************************************************************/
    	//Mention Suite and Browser in reports 
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID TC90931 under BAT Suite on " + platform + " Browser"   , true);
    	}
		
		//declare constant used during Navigation
		final String LANGUAGE 	= "English";
		
		//open browser 
		openBrowser( platform);
		
		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		
		/******************************************************************************
		 ***********************Validation on Home Page********************************
		 ******************************************************************************/
		//Below Xpaths are not added into framework
		WebElement FSSignUpFooterLink = getDriver().findElement(By.xpath("//div[contains(@class,'row footDesktop-left')]//a[contains(text(),'Free Spirit®')]"));
				
		//declare constant used during Validation
		final String[] tabNames		= {"Account","Contact"};
		final int SECTION_TAB_SIZE 	= 2;
		final int FIRST_INDEX  		= 0;
		final int SECOND_INDEX 		= 1;
								
		//click on 9DFC sign up
		JSExecuteUtil.clickOnElement(getDriver(),FSSignUpFooterLink);
//		FSSignUpFooterLink.click();
		
		//wait till page is load is complete
		WaitUtil.untilPageLoadComplete(getDriver());
		
		//Below Xpaths are not added into framework
		List<WebElement> EnrollmentTabs = getDriver().findElements(By.xpath("//a[contains(@class,'nav-link')]"));

		//**********************************
		//*********Enrollment Tabs**********
		//**********************************
		//Verify the header of information table size
		ValidationUtil.validateTestStep("Header of Information table have 3 section", EnrollmentTabs.size()==SECTION_TAB_SIZE);
		
		//verify Account tab on Enrollment page
		ValidationUtil.validateTestStep("Header of Information table conatins Account Tab", EnrollmentTabs.get(FIRST_INDEX).getText().trim().equals(tabNames[FIRST_INDEX]));
		
		//verify Contact tab on Enrollment page
		ValidationUtil.validateTestStep("Header of Information table conatins Contact Tab", EnrollmentTabs.get(SECOND_INDEX).getText().trim().equals(tabNames[SECOND_INDEX]));
		
		
		for(int errorCounter=1;errorCounter<=43;errorCounter++){
			//Below Xpaths are not added into framework
			EnrollmentTabs = getDriver().findElements(By.xpath("//a[contains(@class,'nav-link')]"));

			//get passenger First and last name			
			FSEnrollmentErrorData fSEnrollmentErrorData = FileReaderManager.getInstance().getJsonReader().getFSEnrollmentErrorByRequest("Error"+errorCounter);
			
			//**********************************
			//*********Title Drop Down**********
			//**********************************
			//select title
			if(!fSEnrollmentErrorData.title.equals("NA")){
				TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getUserTitleDropDown(), fSEnrollmentErrorData.title);
			}
			
			//**********************************
			//*********First Name Box***********
			//**********************************
			//enter the first name
			if(!fSEnrollmentErrorData.firstName.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getUserFirstNameTextBox().sendKeys(fSEnrollmentErrorData.firstName);
			}
				
			//**********************************
			//*********Middle Name Box**********
			//**********************************
			//enter middle name
			if(!fSEnrollmentErrorData.middleName.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getUserMiddleNameTextBox().sendKeys(fSEnrollmentErrorData.middleName);
			}
			
			//**********************************
			//*********Last Name Box************
			//**********************************
			//enter last name
			if(!fSEnrollmentErrorData.lastName.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getUserLastNameTextBox().sendKeys(fSEnrollmentErrorData.lastName);
			}
			
			//**********************************
			//*********Date OF Birth Box********
			//**********************************
			//enter the Date of Birth
			if(!fSEnrollmentErrorData.dob.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getUserDateOfBirthTextBox().sendKeys(fSEnrollmentErrorData.dob);
				pageObjectManager.getMemberEnrollmentPage().getUserDateOfBirthTextBox().sendKeys(Keys.TAB);
			}
			
			//**********************************
			//*********Email Box****************
			//**********************************
			//Create variable to generate email id
			String emailvalue = TestUtil.currentDateTimeString() + fSEnrollmentErrorData.email;
			
			//enter the Email
			if(!fSEnrollmentErrorData.email.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getUserEmailIdTextBox().sendKeys(emailvalue);
			}
			
			//**********************************
			//*********Confirm Email Box********
			//**********************************
			//enter the Confirm Email
			if(!fSEnrollmentErrorData.confirmEmail.equals("NA")){
				if(fSEnrollmentErrorData.confirmEmail.contains("@")) {
					pageObjectManager.getMemberEnrollmentPage().getUserConfirmEmailIdTextBox().sendKeys(emailvalue);
				}else {
					pageObjectManager.getMemberEnrollmentPage().getUserConfirmEmailIdTextBox().sendKeys(emailvalue);
					pageObjectManager.getMemberEnrollmentPage().getUserConfirmEmailIdTextBox().click();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pageObjectManager.getMemberEnrollmentPage().getUserConfirmEmailIdTextBox().sendKeys(Keys.BACK_SPACE);
				}
			}
			
			//**********************************
			//*********Create Password Box******
			//**********************************
			//enter the Create Password
			if(!fSEnrollmentErrorData.createPassword.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getUserCreateAPasswordTextBox().sendKeys(fSEnrollmentErrorData.createPassword);
			}
			
			//**********************************
			//*********Confirm Password Box*****
			//**********************************
			//*************************************To be Discussed with SALIM(X path is fine , issue is in validation)
			//enter the Confirm Password
			if(!fSEnrollmentErrorData.confirmPassowrd.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getConfirmAPasswordTextBox().sendKeys(fSEnrollmentErrorData.confirmPassowrd);
				//pageObjectManager.getMemberEnrollmentPage().getConfirmAPasswordTextBox().sendKeys(Keys.TAB);
			}
			
			//************************************
			//*********Continue To Step2 Button***
			//************************************
			for(int count=0;count<3;count++){
				//verify Continue To Step2 Button Clicked successfully
				JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getMemberEnrollmentPage().getContinueToStep2Button());

				WaitUtil.untilTimeCompleted(500);

				if(!EnrollmentTabs.get(SECOND_INDEX).getAttribute("class").contains("active")){
					pageObjectManager.getMemberEnrollmentPage().getContinueToStep2Button().click();
				}

				if(EnrollmentTabs.get(SECOND_INDEX).getAttribute("class").contains("active")){
					break;
				}
			}

			
			//wait till page is load is complete
			WaitUtil.untilPageLoadComplete(getDriver());
			
			//**********************************************CONTACT TAB**************************************************************

			//*********************************
			//*********Address Box*************
			//*********************************
			//enter the Address
			if(!fSEnrollmentErrorData.address1.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getAddressTextBox().sendKeys(fSEnrollmentErrorData.address1);
			}
						
			//**********************************
			//*********City Box*****************
			//**********************************
			//enter the City
			if(!fSEnrollmentErrorData.city.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getCityTextBox().sendKeys(fSEnrollmentErrorData.city);
			}
				
			//**********************************
			//*********Zip/Postal Code Box******
			//**********************************
			//enter the Zip Code
			if(!fSEnrollmentErrorData.zipCode.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getPostalCodeTextBox().sendKeys(fSEnrollmentErrorData.zipCode);
			}
			
			//**********************************
			//*********Country DropDown***********
			//**********************************
			//enter the Country
			if(!fSEnrollmentErrorData.country.equals("NA")){
				if(!fSEnrollmentErrorData.country.equals("Country")) {
					TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getCountryDropDown(), fSEnrollmentErrorData.country);
				}else {
					TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getCountryDropDown(), fSEnrollmentErrorData.country);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pageObjectManager.getMemberEnrollmentPage().getCountryDropDown().sendKeys(Keys.TAB);
				}
				
			}
				
			//**********************************
			//***State/Province DropDown********
			//**********************************
			//enter the State
			if(!fSEnrollmentErrorData.state.equals("NA") &&  !fSEnrollmentErrorData.country.equals("Country")){
				TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getStateDropDown(), fSEnrollmentErrorData.state);
			}
			
			//**********************************
			//*****Primary Phone TextBox********
			//**********************************
			//enter the PrimaryPhone
			if(!fSEnrollmentErrorData.phoneNumber.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getPrimaryPhoneTextBox().sendKeys(fSEnrollmentErrorData.phoneNumber);
				//JSExecuteUtil.sendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getPrimaryPhoneTextBox(),fSEnrollmentErrorData.phoneNumber);
			}
			
			//**********************************
			//*****Secondary Phone TextBox******
			//**********************************
			//enter the PrimaryPhone
			if(!fSEnrollmentErrorData.secondaryPhone.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getSecondaryPhoneTextBox().sendKeys(fSEnrollmentErrorData.secondaryPhone);
			}
			
			//**********************************
			//*****Home Airport TextBox*********
			//**********************************
			//enter the Home Airport
			if(!fSEnrollmentErrorData.homeAirport.equals("NA")){
				TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getPrimaryAirportDropDown(),fSEnrollmentErrorData.homeAirport);
				//pageObjectManager.getMemberEnrollmentPage().getPrimaryAirportDropDown().sendKeys(fSEnrollmentErrorData.homeAirport);
			}
			
			//**********************************
			//****FS Terms and Condition********
			//**********************************
			//select FS Terms and Condition
			if(!fSEnrollmentErrorData.fsTermsAgree.equals("NA")){
				pageObjectManager.getMemberEnrollmentPage().getFSTermsAndConditionText().click();
			}	
		
			//**********************************
			//*********Free Sign Up*************
			//**********************************
			//Click on Free Sign up Button
			if(TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getMemberEnrollmentPage().xpath_FSignUpButton))){
				pageObjectManager.getMemberEnrollmentPage().getFSSignUpButton().click();
			}

			System.out.println(fSEnrollmentErrorData.errorType);
			///check the error message verification is required			
			if(!fSEnrollmentErrorData.errorType.equals("NA") && !fSEnrollmentErrorData.errorType.equals("Skip")) {
				//validate the error message
				ValidationUtil.validateTestStep("Verify the error message on FS Enrollment " +  fSEnrollmentErrorData.errorType,
						pageObjectManager.getCommon().getErrorMessageLabel().getText().trim(),fSEnrollmentErrorData.errorType);
				
				//refresh browser
				JSExecuteUtil.refreshBrowser(getDriver());
			}else if(fSEnrollmentErrorData.errorType.equals("Skip")){
				//refresh browser
				JSExecuteUtil.refreshBrowser(getDriver());
			}

			//wait till page is load is complete
			WaitUtil.untilPageLoadComplete(getDriver());
		}

		//wait till page is load is complete
		WaitUtil.untilPageLoadComplete(getDriver());

		//**********************************
		//*********Welcome To Free Sign Up**
		//**********************************
		final String FS_WELCOME = "Welcome to Free Spirit!";
		final String FS_WELCOME_SUB_HEADER = "You have successfully registered and can now start earning miles for future award travel";

		//verify popup header for FS account
		ValidationUtil.validateTestStep("Verify Welcome to Free Spirit popuop appear on Enrollment Page",
				pageObjectManager.getMemberEnrollmentPage().getPopUpHeaderText().getText().trim().equalsIgnoreCase(FS_WELCOME));
		
		ValidationUtil.validateTestStep("Verify You have successfully registered... verbiage appear on Enrollment Page",
				pageObjectManager.getMemberEnrollmentPage().getSuccessfullyRegisteredText().getText().trim().contains(FS_WELCOME_SUB_HEADER));
        
        //close FS Registration popup
        pageObjectManager.getMemberEnrollmentPage().getCloseImage().click();
	}
	
}
