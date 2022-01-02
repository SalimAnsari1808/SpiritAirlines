package com.spirit.pageMethods;



import com.spirit.dataType.PassengerInfoData;
import com.spirit.pageObjects.HomePage;
import org.openqa.selenium.*;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.Header;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;

import java.util.Date;
import java.util.List;


public class HomePageMethods {

	private WebDriver driver;
	private ScenarioContext scenarioContext;
	private HomePage homePage;
	private Header header;

	public HomePageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
		this.driver=driver;
		this.scenarioContext = scenarioContext;
		homePage = pageObjectManager.getHomePage();
		header   = pageObjectManager.getHeader();
	}


	public synchronized void launchSpiritApp() {
		//This will catch the exception when browser does not load Spirit application
		try{
			ValidationUtil.validateTestStep("Start Launching of Spirit Application", true);
			//launch application under test
			driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationURL());
			//wait for page load
			WaitUtil.initialPageLoadComplete(driver);
			//get current url from application
			String actualURL = driver.getCurrentUrl();
			//get url from config file
			String expectedURL = FileReaderManager.getInstance().getConfigReader().getApplicationURL();
			//click on Spirit logo
			homePage.getSpiritLogoImage().click();
			//disable Print popup
			((JavascriptExecutor)driver).executeScript("window.print=function(){};");
			scenarioContext.setContext(Context.HOMEPAGE_CITYPAIRS,"");
			//validate correct url is open
			ValidationUtil.validateTestStep("Validate Spirit application open Successfully", expectedURL, actualURL);
		}catch(Exception e){
			ValidationUtil.validateTestStep("Validate Spirit application is unable to Load on Browser", false);
		}
		WaitUtil.untilTimeCompleted(1200);
		if(driver.findElements(By.xpath("//button[@title=('Accept Cookies')]")).size() >= 1 )
		{
			JSExecuteUtil.clickOnElement(driver, driver.findElement(By.xpath("//button[@title=('Accept Cookies')]")));
		}
	}


	public synchronized void selectApplicationLanguage(String selectedLanguage) {
		if(!TestUtil.verifyElementDisplayed(driver,By.xpath(homePage.xpath_IconBarImage))){
			//loop through all language link
			for(WebElement appLanguage: homePage.getSelectedLanguage()) {
				//check for displayed language link on Home Page
				if(appLanguage.isDisplayed()) {
					//check for Spanish language if not selected
					if(selectedLanguage.equalsIgnoreCase("Spanish") && appLanguage.getText().trim().equals("Español")) {
						//change to Spanish language
						JSExecuteUtil.clickOnElement(driver, appLanguage);

						//wait till page is stable
						WaitUtil.untilPageLoadComplete(driver);

						//validate Spanish language is selected
						ValidationUtil.validateTestStep("User selected Spanish Language on Home Page", appLanguage.getText().trim().equalsIgnoreCase("English"));
					}else if(selectedLanguage.equalsIgnoreCase("English") && appLanguage.getText().trim().equals("English")) {
						//change to English language
						JSExecuteUtil.clickOnElement(driver, appLanguage);

						//wait till page is stable
						WaitUtil.untilPageLoadComplete(driver);

						//validate Español language is selected
						ValidationUtil.validateTestStep("User selected English Language on Home Page", appLanguage.getText().trim().equalsIgnoreCase("Español"));
					}else if(selectedLanguage.equalsIgnoreCase("Spanish") && appLanguage.getText().trim().equals("English")){
						//Do Nothing as Spanish is already selected
					}else if(selectedLanguage.equalsIgnoreCase("English") && appLanguage.getText().trim().equals("Español")){
						//Do Nothing as English is already selected
					}else{
						//fail test case
						ValidationUtil.validateTestStep("Selected Language " + selectedLanguage + " is not visible on Home Page", false);
					}
				}
			}

			//click on book tab
			homePage.getBookPathLink().click();
		}else{

			homePage.getIconBar().click();

			WaitUtil.untilPageLoadComplete(driver);

			//loop through all language link
			for(WebElement appLanguage: homePage.getSelectedLanguage()) {
				//check for displayed language link on Home Page
				if(appLanguage.isDisplayed()) {
					//check for Spanish language if not selected
					if(selectedLanguage.equalsIgnoreCase("Spanish") && appLanguage.getText().trim().equals("Español")) {
						//change to Spanish language
						JSExecuteUtil.clickOnElement(driver, appLanguage);

						//wait till page is stable
						WaitUtil.untilPageLoadComplete(driver);

						//validate Spanish language is selected
						ValidationUtil.validateTestStep("User selected Spanish Language on Home Page", appLanguage.getText().trim().equalsIgnoreCase("English"));
					}else if(selectedLanguage.equalsIgnoreCase("English") && appLanguage.getText().trim().equals("English")) {
						//change to English language
						JSExecuteUtil.clickOnElement(driver, appLanguage);

						//wait till page is stable
						WaitUtil.untilPageLoadComplete(driver);

						//validate Español language is selected
						ValidationUtil.validateTestStep("User selected English Language on Home Page", appLanguage.getText().trim().equalsIgnoreCase("Español"));
					}else if(selectedLanguage.equalsIgnoreCase("Spanish") && appLanguage.getText().trim().equals("English")){
						//Do Nothing as Spanish is already selected
					}else if(selectedLanguage.equalsIgnoreCase("English") && appLanguage.getText().trim().equals("Español")){
						//Do Nothing as English is already selected
					}else{
						//fail test case
						ValidationUtil.validateTestStep("Selected Language " + selectedLanguage + " is not visible on Home Page", false);
					}
				}
			}

			//click on book tab
			homePage.getBookPathLink().click();

			//wait for 3 sec
			WaitUtil.untilTimeCompleted(3000);
		}


		scenarioContext.setContext(Context.HOMEPAGE_LANGUAGE, selectedLanguage);
	}




	public synchronized void loginToMyTrip() {
		//declare constant used in methods
		final String RES_SUMMARY_URL	= "my-trips/reservation-summary";
		final String MY_TRIP_COLOR1		= "rgba(0, 0, 0, 1)";
		final String MY_TRIP_COLOR2		= "rgb(0, 0, 0)";

		//click on header spitit logo
		header.getSpiritLogoImage().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//click on My trip tab
		homePage.getMyTripPathLink().click();

		//wait for 1 sec
		WaitUtil.untilTimeCompleted(1000);

		if(homePage.getMyTripPathLink().getCssValue("color").contains("rgba")){
			//verify user navigated to MyTrips Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on My Trip tab on Home Page", homePage.getMyTripPathLink().getCssValue("color"),MY_TRIP_COLOR1);
		}else if(homePage.getMyTripPathLink().getCssValue("color").contains("rgb")){
			//verify user navigated to MyTrips Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on My Trip tab on Home Page", homePage.getMyTripPathLink().getCssValue("color"),MY_TRIP_COLOR2);
		}else{
			//verify user navigated to MyTrips Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on My Trip tab on Home Page", false);
		}

		//check link is present on my trip page
		if(TestUtil.verifyElementDisplayed(driver,By.xpath(homePage.xpath_MyTripConfirmationCodeChevronLink))){
			//check link is closed
			if(homePage.getMyTripConfirmationCodeChevronLink().findElements(By.tagName("i")).get(0).getAttribute("style").contains("-180deg")){
				//open link
				homePage.getMyTripConfirmationCodeChevronLink().click();

				//wait for 2 sec
				WaitUtil.untilTimeCompleted(2000);
			}
		}
		//enter last name
		homePage.getMyTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

		//verify the last name
		ValidationUtil.validateTestStep("User login to My Trip with LastName as:" + scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString(),true);

		//enter confirmation code
		homePage.getMyTripConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

		//verify the last name
		ValidationUtil.validateTestStep("User login to My Trip with PNR as:" + scenarioContext.getContext(Context.CONFIRMATION_CODE).toString(),true);

		//click on continue button
		homePage.getMyTripContinueButton().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//Navigate to MyTrips Reservation Summary Page
		WaitUtil.untilPageURLVisible(driver, RES_SUMMARY_URL);

		//verify user navigated to MyTrips Reservation Summary Page
		ValidationUtil.validateTestStep("User redirected to the Reservation Summary Page", driver.getCurrentUrl(),RES_SUMMARY_URL);
	}


	public synchronized void loginToMyTripWithCarnetPNR(){
		//declare constant used in methods
		final String MY_TRIP_COLOR1		= "rgba(0, 0, 0, 1)";
		final String MY_TRIP_COLOR2		= "rgb(0, 0, 0)";

		//click on header spitit logo
		header.getSpiritLogoImage().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//click on My trip tab
		homePage.getMyTripPathLink().click();

		//wait for 1 sec
		WaitUtil.untilTimeCompleted(1000);

		if(homePage.getMyTripPathLink().getCssValue("color").contains("rgba")){
			//verify user navigated to MyTrips Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on My Trip tab on Home Page", homePage.getMyTripPathLink().getCssValue("color"),MY_TRIP_COLOR1);
		}else if(homePage.getMyTripPathLink().getCssValue("color").contains("rgb")){
			//verify user navigated to MyTrips Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on My Trip tab on Home Page", homePage.getMyTripPathLink().getCssValue("color"),MY_TRIP_COLOR2);
		}else{
			//verify user navigated to MyTrips Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on My Trip tab on Home Page", false);
		}

		//check link is present on my trip page
		if(TestUtil.verifyElementDisplayed(driver,By.xpath(homePage.xpath_MyTripConfirmationCodeChevronLink))){
			//check link is closed
			if(homePage.getMyTripConfirmationCodeChevronLink().findElements(By.tagName("i")).get(0).getAttribute("style").contains("-180deg")){
				//open link
				homePage.getMyTripConfirmationCodeChevronLink().click();

				//wait for 2 sec
				WaitUtil.untilTimeCompleted(2000);
			}
		}

		//clear last name testbox
		TestUtil.clearTextBoxUsingSendKeys(driver,homePage.getMyTripLastNameTextBox());

		//enter last name
		homePage.getMyTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

		//verify the last name
		ValidationUtil.validateTestStep("User login to My Trip with LastName as:" + scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString(),true);

		//clear confirmation testbox
		TestUtil.clearTextBoxUsingSendKeys(driver,homePage.getMyTripConfirmationCodeTextBox());

		//enter confirmation code
		homePage.getMyTripConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CARNETCAR_CODE).toString());

		//verify the last name
		ValidationUtil.validateTestStep("User login to My Trip with Carnet PNR as:" + scenarioContext.getContext(Context.CONFIRMATION_CARNETCAR_CODE).toString(),true);

		//click on continue button
		homePage.getMyTripContinueButton().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//verify reservation not found popup appear on page
		ValidationUtil.validateTestStep("Verify Reservation Not Found Popup Appear Carnet PNR cannot be used to retrieve Booking Spirit.com",
				TestUtil.verifyElementDisplayed(driver,By.xpath(homePage.xpath_ReservationNotFoundHeaderText)));

		//close reservation not found popup
		homePage.getReservationNotFoundCloseBtn().click();
	}

	public synchronized void loginToMyTripWithHotelBedsPNR(){
		//declare constant used in methods
		final String MY_TRIP_COLOR1		= "rgba(0, 0, 0, 1)";
		final String MY_TRIP_COLOR2		= "rgb(0, 0, 0)";

		//click on header spitit logo
		header.getSpiritLogoImage().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//click on My trip tab
		homePage.getMyTripPathLink().click();

		//wait for 1 sec
		WaitUtil.untilTimeCompleted(1000);

		if(homePage.getMyTripPathLink().getCssValue("color").contains("rgba")){
			//verify user navigated to MyTrips Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on My Trip tab on Home Page", homePage.getMyTripPathLink().getCssValue("color"),MY_TRIP_COLOR1);
		}else if(homePage.getMyTripPathLink().getCssValue("color").contains("rgb")){
			//verify user navigated to MyTrips Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on My Trip tab on Home Page", homePage.getMyTripPathLink().getCssValue("color"),MY_TRIP_COLOR2);
		}else{
			//verify user navigated to MyTrips Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on My Trip tab on Home Page", false);
		}

		//check link is present on my trip page
		if(TestUtil.verifyElementDisplayed(driver,By.xpath(homePage.xpath_MyTripConfirmationCodeChevronLink))){
			//check link is closed
			if(homePage.getMyTripConfirmationCodeChevronLink().findElements(By.tagName("i")).get(0).getAttribute("style").contains("-180deg")){
				//open link
				homePage.getMyTripConfirmationCodeChevronLink().click();

				//wait for 2 sec
				WaitUtil.untilTimeCompleted(2000);
			}
		}
		//clear lastname testbox
		TestUtil.clearTextBoxUsingSendKeys(driver,homePage.getMyTripLastNameTextBox());

		//enter last name
		homePage.getMyTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

		//verify the last name
		ValidationUtil.validateTestStep("User login to My Trip with LastName as:" + scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString(),true);

		//clear confirmation testbox
		TestUtil.clearTextBoxUsingSendKeys(driver,homePage.getMyTripConfirmationCodeTextBox());

		//enter confirmation code
		homePage.getMyTripConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_HBGHOTEL_CODE).toString().replace("-"," "));

		//verify the last name
		ValidationUtil.validateTestStep("User login to My Trip with HBG PNR as:" + scenarioContext.getContext(Context.CONFIRMATION_HBGHOTEL_CODE).toString(),true);

		//click on continue button
		homePage.getMyTripContinueButton().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//verify reservation not found popup appear on page
		ValidationUtil.validateTestStep("Verify Reservation Not Found Popup Appear HBG PNR cannot be used to retrieve Booking Spirit.com",
				TestUtil.verifyElementDisplayed(driver,By.xpath(homePage.xpath_ReservationNotFoundHeaderText)));

		//close reservation not found popup
		homePage.getReservationNotFoundCloseBtn().click();
	}


	public synchronized void loginToCheckIn() {
		//declare constant used in methods
		final String CHECKIN_SUMMARY_URL	= "check-in/reservation-summary";
		final String CHECKIN_COLOR1			= "rgba(0, 0, 0, 1)";
		final String CHECKIN_COLOR2			= "rgb(0, 0, 0)";

		//click on header spirit logo
		header.getSpiritLogoImage().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//click on My trip tab
		homePage.getCheckInPathLink().click();

		//wait for 1 sec
		WaitUtil.untilTimeCompleted(1000);

		if(homePage.getMyTripPathLink().getCssValue("color").contains("rgba")){
			//verify user navigated to Check-In Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on CheckIn tab on Home Page", homePage.getCheckInPathLink().getCssValue("color"),CHECKIN_COLOR1);
		}else if(homePage.getMyTripPathLink().getCssValue("color").contains("rgb")){
			//verify user navigated to Check-In Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on CheckIn tab on Home Page", homePage.getCheckInPathLink().getCssValue("color"),CHECKIN_COLOR2);
		}else{
			//verify user navigated to Check-In Reservation Summary Page
			ValidationUtil.validateTestStep("Verify User clicked on CheckIn tab on Home Page", false);
		}



		//enter last name
		homePage.getCheckInLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

		//verify the last name
		ValidationUtil.validateTestStep("User login to Check-In with LastName as:" + scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString(),true);

		//enter confirmation code
		homePage.getCheckInConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

		//verify the last name
		ValidationUtil.validateTestStep("User login to Check-In with PNR as:" + scenarioContext.getContext(Context.CONFIRMATION_CODE).toString(),true);

		//click on continue button
		homePage.getCheckInButton().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//Navigate to MyTrips Reservation Summary Page
		WaitUtil.untilPageURLVisible(driver, CHECKIN_SUMMARY_URL);

		//verify user navigated to MyTrips Reservation Summary Page
		ValidationUtil.validateTestStep("User redirected to the Reservation Summary Page", driver.getCurrentUrl(),CHECKIN_SUMMARY_URL);
	}


	public synchronized void loginToApplication(String userName,String password) {

		//loop on home page and get all sign in links
		for(WebElement signInLink: homePage.getSignInListLink()) {
			//check sign in link is visible
			if(signInLink.isDisplayed()) {
				//click on sign in link
				signInLink.click();
			}
		}

		//wait till page is stable
		WaitUtil.untilPageLoadComplete(driver);

		//enter username
		homePage.getUserNameBox().sendKeys(userName);

		//enter password
		homePage.getPasswordBox().sendKeys(password);

		//click on login button
		homePage.getLoginButton().click();

		//wait till page is stable
		WaitUtil.untilPageLoadComplete(driver);

	}


	public synchronized void loginToApplication(String loginUserType) {
		//get the login mail type in global variable
		scenarioContext.setContext(Context.HOMEPAGE_LOGINTYPE,loginUserType);

		//get the login credentials from Json file
		LoginCredentialsData loginCredentialsData =FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(loginUserType);

		//loop on home page and get all sign in links
		for(WebElement signInLink: homePage.getSignInListLink()) {
			//check sign in link is visible
			if(signInLink.isDisplayed()) {
				//click on sign in link
//				signInLink.click();
				JSExecuteUtil.clickOnElement(driver,signInLink);
			}
		}

		//wait till page is stable
		WaitUtil.untilPageLoadComplete(driver);

		//store email address in global variable
		scenarioContext.setContext(Context.CUSTOMER_EMAIL, loginCredentialsData.userName);

		//enter username
		homePage.getUserNameBox().sendKeys(loginCredentialsData.userName);

		//enter password
		homePage.getPasswordBox().sendKeys(loginCredentialsData.password);

		//click on login button
//		homePage.getLoginButton().click();
		JSExecuteUtil.clickOnElement(driver,homePage.getLoginButton());

		//wait till page is stable
		WaitUtil.untilPageLoadComplete(driver);

		//validate sign in is done
		ValidationUtil.validateTestStep("User Login to Spirit Application with "+ loginUserType, homePage.getLoginUserIconImage().size()>0);
	}


	public synchronized void logoutFromApplication(){

		try{
//			if(TestUtil.verifyElementDisplayed(driver,By.xpath(header.xpath_UserDropDown))){
			header.getUserDropDown().click();

			WaitUtil.untilPageLoadComplete(driver);
			WaitUtil.untilTimeCompleted(2000);

			header.getSignOutUserLink().click();

			WaitUtil.untilPageLoadComplete(driver);

			//ValidationUtil.validateTestStep("User verify application is logout",!TestUtil.verifyElementDisplayed(header.getUserDropDown()));

//			}
		}catch(Exception e){

		}



	}

	public synchronized void selectJourneyType(String journeyType) {
		//declare variable used in method
		boolean bolStatusFlag = false;

		//store in global variable
		scenarioContext.setContext(Context.HOMEPAGE_JOURNEYTYPE,journeyType);

		switch(journeyType.toLowerCase()){
			case "flight":
				//click on flight
				homePage.getFlightBookingLink().click();

				//get the active status
				bolStatusFlag = homePage.getFlightBookingLink().getAttribute("class").contains("active");

				//break from switch
				break;
			case "vacation":
				//click on vacation
				homePage.getVacationBookingLink().click();

				//get the active status
				bolStatusFlag = homePage.getVacationBookingLink().getAttribute("class").contains("active");

				//break from switch
				break;
			case "car":
				//click on car
				homePage.getCarBookingLink().click();

				//get the active status
				bolStatusFlag = homePage.getCarBookingLink().getAttribute("class").contains("active");

				//break from switch
				break;
			case "hotel":
				//click on hotel
				homePage.getHotelBookingLink().click();

				//get the active status
				bolStatusFlag = homePage.getHotelBookingLink().getAttribute("class").contains("active");

				//break from switch
				break;
			case "cruise":
				//click on cruise
				homePage.getCruiseBookingLink().click();

				//get the active status
				bolStatusFlag = homePage.getCruiseBookingLink().getAttribute("class").contains("active");

				//break from switch
				break;
		}

		//validate journey type on home page
		ValidationUtil.validateTestStep("User Select Journey Type as " + journeyType + " on Home Page",bolStatusFlag);
	}

	public synchronized void selectTripType(String tripType) {
		//declare variable used in method
		String backgroundColor = null;
		boolean bolStatusFlag = false;

		switch(tripType.toLowerCase()){
			case "roundtrip":
				//click on roundtrip
				homePage.getRoundTripRadioButton().click();

				//get the back ground color after selection
				backgroundColor = JSExecuteUtil.getElementBackGroundColor(driver, homePage.getRoundTripLabel());

				//check the background color
				bolStatusFlag = backgroundColor.equals("rgb(0, 115, 230)")?true:false;

				//break from switch
				break;
			case "oneway":
				//click on oneway
				homePage.getOneWayRadioButton().click();

				//get the back ground color after selection
				backgroundColor = JSExecuteUtil.getElementBackGroundColor(driver, homePage.getOneWayLabel());

				//check the background color
				bolStatusFlag = backgroundColor.equals("rgb(0, 115, 230)")?true:false;

				//break from switch
				break;
			case "multicity":
				//click on multicity
				homePage.getMultiCityRadioButton().click();

				//get the back ground color after selection
				backgroundColor = JSExecuteUtil.getElementBackGroundColor(driver, homePage.getMultiCityLabel());

				//check the background color
				bolStatusFlag = backgroundColor.equals("rgb(0, 115, 230)")?true:false;

				//break from switch
				break;
			case "flight+car":
				//click on flight+car
				homePage.getFlightCarRadioButton().click();

				//get the back ground color after selection
				backgroundColor = JSExecuteUtil.getElementBackGroundColor(driver, homePage.getFlightCarLabel());

				//check the background color
				bolStatusFlag = backgroundColor.equals("rgb(0, 115, 230)")?true:false;

				//break from switch
				break;
			case "flight+hotel":
				//click on flight+hotel
				homePage.getFlightHotelRadiobutton().click();

				//get the back ground color after selection
				backgroundColor = JSExecuteUtil.getElementBackGroundColor(driver, homePage.getFlightHotelLabel());

				//check the background color
				bolStatusFlag = backgroundColor.equals("rgb(0, 115, 230)")?true:false;

				//break from switch
				break;
			case "flight+hotel+car":
				//click on flight+hotel
				homePage.getFlightHotelCarRadiobutton().click();

				//get the back ground color after selection
				backgroundColor = JSExecuteUtil.getElementBackGroundColor(driver, homePage.getFlightHotelCarLabel());

				//check the background color
				bolStatusFlag = backgroundColor.equals("rgb(0, 115, 230)")?true:false;

				//break from switch
				break;
		}

		//store trip type in global variable
		scenarioContext.setContext(Context.HOMEPAGE_TRIP_TYPE, tripType.toLowerCase());

		//validate trip type on home page
		ValidationUtil.validateTestStep("User Select Trip Type "+ tripType + " on Home Page",bolStatusFlag);
	}

	// **********************************************************************************************
	// Method : selectAirports
	// Description: Method is used to select the departure and Arrival Airports
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 14-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	public synchronized void selectAirportsMultiCity(String strAllDepLocations, String strAllDepAirports, String strAllArrLocations, String strAllArrAirports) {
		String strDepLocation, strDepAirport, strArrLocation, strArrAirport;

		for(int airportCounter=0;airportCounter<strAllDepLocations.split("\\|").length;airportCounter++){

			strDepLocation = strAllDepLocations.split("\\|")[airportCounter];
			strDepAirport  = strAllDepAirports.split("\\|")[airportCounter];
			strArrLocation = strAllArrLocations.split("\\|")[airportCounter];
			strArrAirport  = strAllArrAirports.split("\\|")[airportCounter];

			//check departing airport is visible
			if(homePage.getMultiCityDepartAirportBox().get(airportCounter).isDisplayed()){
				//click on departing airport
				homePage.getMultiCityDepartAirportBox().get(airportCounter).click();
			}

			//*****************************************************************
			//********Selecting Departing Airport Region***********************
			//*****************************************************************
			//select airport type for departing airport
			switch(strDepLocation.toLowerCase()){
				case "alllocation":
					//loop through all location for departing airport
					for( WebElement allLocation: homePage.getAllLocationAirportText()){
						//check all location is visible
						if(allLocation.isDisplayed()){
							//click on all location text
							allLocation.click();
						}
					}

					//break from switch
					break;
				case "northamerica":
					//loop through north america for departing airport
					for( WebElement northAmerica: homePage.getNorthAmericaAirportText()){
						//check north america is visible
						if(northAmerica.isDisplayed()){
							//click on north america text
							northAmerica.click();
						}
					}

					//break from switch
					break;
				case "centralamerica":
					//loop through central america for departing airport
					for( WebElement centralAmerica: homePage.getCentralAmericaAirportText()){
						//check central america is visible
						if(centralAmerica.isDisplayed()){
							//click on central america text
							centralAmerica.click();
						}
					}

					//break from switch
					break;
				case "southamerica":
					//loop through south america for departing airport
					for( WebElement southAmerica: homePage.getSouthAmericaAirportText()){
						//check south america is visible
						if(southAmerica.isDisplayed()){
							//click on south america text
							southAmerica.click();
						}
					}

					//break from switch
					break;
				case "caribbean":
					//loop through caribbean for departing airport
					for( WebElement caribbean: homePage.getCaribbeanAirportText()){
						//check caribbean is visible
						if(caribbean.isDisplayed()){
							//click on caribbean text
							caribbean.click();
						}
					}

					//break from switch
					break;
			}

			//*****************************************************************
			//***************Selecting Departing Airport***********************
			//*****************************************************************
			//loop through all departing airport
			for(WebElement depAirport: homePage.getAirportListText()){
				//check current departing airport contains airport code
				//check current departing airport is visible on screen
				if(depAirport.getText().contains(strDepAirport) && depAirport.isDisplayed()){
					depAirport.click();

					//departing airport selected break loop
					break;
				}
			}

			//check return airport is visible
			if(homePage.getMultiCityArrivalAirportBox().get(airportCounter).isDisplayed()){
				//click on return airport
				homePage.getMultiCityArrivalAirportBox().get(airportCounter).click();
			}

			//*****************************************************************
			//**********Selecting Arrival Airport Region***********************
			//*****************************************************************
			//select airport type for arrival airport
			switch(strArrLocation.toLowerCase()){
				case "alllocation":
					//loop through all location for arrival airport
					for( WebElement allLocation: homePage.getAllLocationAirportText()){
						//check all location is visible
						if(allLocation.isDisplayed()){
							//click on all location text
							allLocation.click();
						}
					}

					//break from switch
					break;
				case "northamerica":
					//loop through north america for arrival airport
					for( WebElement northAmerica: homePage.getNorthAmericaAirportText()){
						//check north america is visible
						if(northAmerica.isDisplayed()){
							//click on north america text
							northAmerica.click();
						}
					}

					//break from switch
					break;
				case "centralamerica":
					//loop through central america for arrival airport
					for( WebElement centralAmerica: homePage.getCentralAmericaAirportText()){
						//check central america is visible
						if(centralAmerica.isDisplayed()){
							//click on central america text
							centralAmerica.click();
						}
					}

					//break from switch
					break;
				case "southamerica":
					//loop through south america for arrival airport
					for( WebElement southAmerica: homePage.getSouthAmericaAirportText()){
						//check south america is visible
						if(southAmerica.isDisplayed()){
							//click on south america text
							southAmerica.click();
						}
					}

					//break from switch
					break;
				case "caribbean":
					//loop through caribbean for arrival airport
					for( WebElement caribbean: homePage.getCaribbeanAirportText()){
						//check caribbean is visible
						if(caribbean.isDisplayed()){
							//click on caribbean text
							caribbean.click();
						}
					}

					//break from switch
					break;
			}

			//*****************************************************************
			//****************Selecting Arrival Airport************************
			//*****************************************************************
			//loop through all departing airport
			for(WebElement arrAirport: homePage.getAirportListText()){
				//check current departing airport contains airport code
				//check current departing airport is visible on screen
				if(arrAirport.getText().contains(strArrAirport) && arrAirport.isDisplayed()){
					arrAirport.click();

					//departing airport selected break loop
					break;
				}
			}

			//wait for page load
			WaitUtil.untilPageLoadComplete(driver);

			String strDepartureAirport = null;

			//check departing airport is visible
			if(homePage.getMultiCityDepartAirportBox().get(airportCounter).isDisplayed()){
				//get selected departure airport
				strDepartureAirport =  JSExecuteUtil.getElementTextValue(driver, homePage.getMultiCityDepartAirportBox().get(airportCounter));
			}

			//check departure airport selected
			ValidationUtil.validateTestStep("User selected Departure Airport  for Journey:"+ (airportCounter+1) + " as " + strDepartureAirport + " on Home Page", strDepartureAirport,strDepAirport);

			if(airportCounter==0){
				//store dep Airport in global variable
				scenarioContext.setContext(Context.HOMEPAGE_DEP_AIRPORT, strDepartureAirport);
			}else{
				//store dep Airport in global variable
				scenarioContext.setContext(Context.HOMEPAGE_DEP_AIRPORT, scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString()+"|"+strDepartureAirport);
			}

			String strArrivalAirport = null;

			//check return airport is visible
			if(homePage.getMultiCityArrivalAirportBox().get(airportCounter).isDisplayed()){
				//get selected arrival airport
				strArrivalAirport = JSExecuteUtil.getElementTextValue(driver, homePage.getMultiCityArrivalAirportBox().get(airportCounter));
			}

			//check arrival airport selected
			//check departure airport selected
			ValidationUtil.validateTestStep("User selected Arrival Airport  for Journey:"+ (airportCounter+1) + " as " + strArrivalAirport + " on Home Page", strArrivalAirport,strArrAirport);


			if(airportCounter==0){
				//store arr Airport in global variable
				scenarioContext.setContext(Context.HOMEPAGE_ARR_AIRPORT, strArrivalAirport);
			}else{
				//store dep Airport in global variable
				scenarioContext.setContext(Context.HOMEPAGE_ARR_AIRPORT, scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString()+"|"+strArrivalAirport);
			}


		}
	}

	//**********************************************************************************************
	// Method : storeAirportsDetails
	// Description: Method is used to store airport information
	// Input Arguments:
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 14-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	public synchronized void storeAirportsDetails(){

		//loop through all departing airport
		for( WebElement depAirport: homePage.getDepartAirportBox()){
			//check departing airport is visible
			if(depAirport.isDisplayed()){
				//click on departing airport
				depAirport.click();
			}
		}

		//loop through all location for departing airport
		for( WebElement allLocation: homePage.getAllLocationAirportText()){
			//check all location is visible
			if(allLocation.isDisplayed()){
				//click on all location text
				allLocation.click();
			}
		}

		//loop through all departing airport
		for(WebElement depAirport: homePage.getAirportListText()){
			//check current departing airport contains airport code
			//check current departing airport is visible on screen
			if(depAirport.isDisplayed()){
				scenarioContext.setContext(Context.HOMEPAGE_CITYPAIRS,scenarioContext.getContext(Context.HOMEPAGE_CITYPAIRS).toString() + depAirport.getText() + "|");
			}
		}


	}

	//**********************************************************************************************
	// Method : selectAirports
	// Description: Method is used to select the departure and Arrival Airports
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 14-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	public synchronized void selectAirports(String strDepLocation, String strDepAirport, String strArrLocation, String strArrAirport) {

		//loop through all departing airport
		for( WebElement depAirport: homePage.getDepartAirportBox()){
			//check departing airport is visible
			if(depAirport.isDisplayed()){
				//click on departing airport
//				depAirport.click();
				JSExecuteUtil.clickOnElement(driver,depAirport);
			}
		}

		//*****************************************************************
		//********Selecting Departing Airport Region***********************
		//*****************************************************************
		//select airport type for departing airport
		switch(strDepLocation.toLowerCase()){
			case "alllocation":
				//loop through all location for departing airport
				for( WebElement allLocation: homePage.getAllLocationAirportText()){
					//check all location is visible
					if(allLocation.isDisplayed()){
						//click on all location text
//					allLocation.click();
						JSExecuteUtil.clickOnElement(driver,allLocation);
					}
				}

				//break from switch
				break;
			case "northamerica":
				//loop through north america for departing airport
				for( WebElement northAmerica: homePage.getNorthAmericaAirportText()){
					//check north america is visible
					if(northAmerica.isDisplayed()){
						//click on north america text
						northAmerica.click();
					}
				}

				//break from switch
				break;
			case "centralamerica":
				//loop through central america for departing airport
				for( WebElement centralAmerica: homePage.getCentralAmericaAirportText()){
					//check central america is visible
					if(centralAmerica.isDisplayed()){
						//click on central america text
						centralAmerica.click();
					}
				}

				//break from switch
				break;
			case "southamerica":
				//loop through south america for departing airport
				for( WebElement southAmerica: homePage.getSouthAmericaAirportText()){
					//check south america is visible
					if(southAmerica.isDisplayed()){
						//click on south america text
						southAmerica.click();
					}
				}

				//break from switch
				break;
			case "caribbean":
				//loop through caribbean for departing airport
				for( WebElement caribbean: homePage.getCaribbeanAirportText()){
					//check caribbean is visible
					if(caribbean.isDisplayed()){
						//click on caribbean text
						caribbean.click();
					}
				}

				//break from switch
				break;
		}

		//*****************************************************************
		//***************Selecting Departing Airport***********************
		//*****************************************************************
		//loop through all departing airport
		for(WebElement depAirport: homePage.getAirportListText()){
			//check current departing airport contains airport code
			//check current departing airport is visible on screen
			if(depAirport.getText().contains(strDepAirport) && depAirport.isDisplayed()){
				depAirport.click();

				//departing airport selected break loop
				break;
			}
		}

		//loop through all return airport
		for( WebElement arrAirport: homePage.getArrivalAirportBox()){
			//check return airport is visible
			if(arrAirport.isDisplayed()){
				//click on return airport
				arrAirport.click();
			}
		}

		//*****************************************************************
		//**********Selecting Arrival Airport Region***********************
		//*****************************************************************
		//select airport type for arrival airport
		switch(strArrLocation.toLowerCase()){
			case "alllocation":
				//loop through all location for arrival airport
				for( WebElement allLocation: homePage.getAllLocationAirportText()){
					//check all location is visible
					if(allLocation.isDisplayed()){
						//click on all location text
						allLocation.click();
					}
				}

				//break from switch
				break;
			case "northamerica":
				//loop through north america for arrival airport
				for( WebElement northAmerica: homePage.getNorthAmericaAirportText()){
					//check north america is visible
					if(northAmerica.isDisplayed()){
						//click on north america text
						northAmerica.click();
					}
				}

				//break from switch
				break;
			case "centralamerica":
				//loop through central america for arrival airport
				for( WebElement centralAmerica: homePage.getCentralAmericaAirportText()){
					//check central america is visible
					if(centralAmerica.isDisplayed()){
						//click on central america text
						centralAmerica.click();
					}
				}

				//break from switch
				break;
			case "southamerica":
				//loop through south america for arrival airport
				for( WebElement southAmerica: homePage.getSouthAmericaAirportText()){
					//check south america is visible
					if(southAmerica.isDisplayed()){
						//click on south america text
						southAmerica.click();
					}
				}

				//break from switch
				break;
			case "caribbean":
				//loop through caribbean for arrival airport
				for( WebElement caribbean: homePage.getCaribbeanAirportText()){
					//check caribbean is visible
					if(caribbean.isDisplayed()){
						//click on caribbean text
						caribbean.click();
					}
				}

				//break from switch
				break;
		}

		//*****************************************************************
		//****************Selecting Arrival Airport************************
		//*****************************************************************
		//loop through all departing airport
		for(WebElement arrAirport: homePage.getAirportListText()){
			//check current departing airport contains airport code
			//check current departing airport is visible on screen
			if(arrAirport.getText().contains(strArrAirport) && arrAirport.isDisplayed()){
				arrAirport.click();

				//departing airport selected break loop
				break;
			}
		}

		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);

		String strDepartureAirport = null;

		//loop through all departing airport
		for( WebElement depAirport: homePage.getDepartAirportBox()){
			//check departing airport is visible
			if(depAirport.isDisplayed()){
				//get selected departure airport
				strDepartureAirport =  JSExecuteUtil.getElementTextValue(driver, depAirport);
			}
		}

		//check departure airport selected
		ValidationUtil.validateTestStep("User selected Departure Airport  "+ strDepartureAirport + " on Home Page", strDepartureAirport,strDepAirport);

		//store dep Airport in global variable
		scenarioContext.setContext(Context.HOMEPAGE_DEP_AIRPORT, strDepartureAirport);

		String strArrivalAirport = null;

		//loop through all return airport
		for( WebElement arrAirport: homePage.getArrivalAirportBox()){
			//check return airport is visible
			if(arrAirport.isDisplayed()){
				//get selected arrival airport
				strArrivalAirport = JSExecuteUtil.getElementTextValue(driver, arrAirport);
			}
		}

		//check arrival airport selected
		//check departure airport selected
		ValidationUtil.validateTestStep("User selected Arrival Airport  "+ strArrivalAirport + " on Home Page", strArrivalAirport,strArrAirport);

		//store arr Airport in global variable
		scenarioContext.setContext(Context.HOMEPAGE_ARR_AIRPORT, strArrivalAirport);
	}

	//**********************************************************************************************
	// Method : selectAirports
	// Description: Method is used to select the departure and Arrival Airports on Mobile browser
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 14-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	public synchronized void selectAirports( String strDepAirport, String strArrAirport) {

		//loop through all departing airport
		for( WebElement depAirport: homePage.getDepartAirportBox()){
			//check departing airport is visible
			if(depAirport.isDisplayed()){
				//click on departing airport
//				depAirport.click();
				JSExecuteUtil.clickOnElement(driver,depAirport);

				depAirport.sendKeys(strDepAirport);

				break;
			}
		}

		//*****************************************************************
		//***************Selecting Departing Airport***********************
		//*****************************************************************
		//loop through all departing airport
		for(WebElement depAirport: homePage.getAirportListText()){
			//check current departing airport contains airport code
			//check current departing airport is visible on screen
			if(depAirport.getText().contains(strDepAirport) && depAirport.isDisplayed()){
				depAirport.click();

				//departing airport selected break loop
				break;
			}
		}

		//loop through all return airport
		for( WebElement arrAirport: homePage.getArrivalAirportBox()){
			//check return airport is visible
			if(arrAirport.isDisplayed()){
				//click on return airport
				arrAirport.click();

				arrAirport.sendKeys(strArrAirport);

				break;
			}
		}

		//*****************************************************************
		//****************Selecting Arrival Airport************************
		//*****************************************************************
		//loop through all departing airport
		for(WebElement arrAirport: homePage.getAirportListText()){
			//check current departing airport contains airport code
			//check current departing airport is visible on screen
			if(arrAirport.getText().contains(strArrAirport) && arrAirport.isDisplayed()){
				arrAirport.click();

				//departing airport selected break loop
				break;
			}
		}

		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);

		String strDepartureAirport = null;

		//loop through all departing airport
		for( WebElement depAirport: homePage.getDepartAirportBox()){
			//check departing airport is visible
			if(depAirport.isDisplayed()){
				//get selected departure airport
				strDepartureAirport =  JSExecuteUtil.getElementTextValue(driver, depAirport);
			}
		}

		//check departure airport selected
		ValidationUtil.validateTestStep("User selected Departure Airport  "+ strDepartureAirport + " on Home Page", strDepartureAirport,strDepAirport);

		//store dep Airport in global variable
		scenarioContext.setContext(Context.HOMEPAGE_DEP_AIRPORT, strDepartureAirport);

		String strArrivalAirport = null;

		//loop through all return airport
		for( WebElement arrAirport: homePage.getArrivalAirportBox()){
			//check return airport is visible
			if(arrAirport.isDisplayed()){
				//get selected arrival airport
				strArrivalAirport = JSExecuteUtil.getElementTextValue(driver, arrAirport);
			}
		}

		//check arrival airport selected
		//check departure airport selected
		ValidationUtil.validateTestStep("User selected Arrival Airport  "+ strArrivalAirport + " on Home Page", strArrivalAirport,strArrAirport);

		//store arr Airport in global variable
		scenarioContext.setContext(Context.HOMEPAGE_ARR_AIRPORT, strArrivalAirport);
	}

	//**********************************************************************************************
	// Method : selectDatesMultiCity
	// Description: Method is used to select dates for multicity on Home Page
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 14-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	public synchronized void selectDatesMultiCity(String strTravellingDates) {

		//declare variable used in method
		String strActualDepDate, strActualArrDate = null, strFinalDate=null;
		String strDepDate;

		for(int dateCounter=0;dateCounter<strTravellingDates.split("\\|").length;dateCounter++){

			strDepDate = strTravellingDates.split("\\|")[dateCounter];

			//get the dep date
			strActualDepDate = TestUtil.getStringDateFormat(strDepDate, "MM/dd/yyyy");

			//clear previous dates
			TestUtil.clearTextBoxUsingSendKeys(driver, homePage.getMultiCityDateBox().get(dateCounter));

			homePage.getMultiCityDateBox().get(dateCounter).sendKeys(strActualDepDate);

			WaitUtil.untilTimeCompleted(2000);

			//validate depart date
			ValidationUtil.validateTestStep("User Selected Journey:" + (dateCounter+1) +" Date as "+ strActualDepDate + " on Home Page", true);

			homePage.getMultiCityLabel().click();
			if(dateCounter==0){
				//store dep date
				scenarioContext.setContext(Context.HOMEPAGE_DEP_DATE, strDepDate);

				//set global variable for 24 hour flight
				if(strDepDate.equalsIgnoreCase("0")){
					scenarioContext.setContext(Context.HOMEPAGE_CHECK_IN, "24Hours");
				}else{
					scenarioContext.setContext(Context.HOMEPAGE_CHECK_IN, "Outside24Hours");
				}
			}else if(dateCounter==1){
				//store dep date
				scenarioContext.setContext(Context.HOMEPAGE_ARR_DATE, strDepDate);
			}
		}

	}

	//**********************************************************************************************
	// Method : selectDatesOnCalendar
	// Description: Method is used to select dates on Home Page Date Calendar
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 14-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	public synchronized void selectDatesOnCalendar(String strDepDate, String strArrDate) {
		//declare variable used in method
		String strActualDepDate, strActualArrDate = null, strFinalDate=null;

		homePage.getDateBox().click();

		//*****************************************************************
		//***********************Departure Date****************************
		//*****************************************************************
		//get the dep date
		strActualDepDate = verifyUSAHolidayDates(TestUtil.getStringDateFormat(strDepDate, "MM/dd/yyyy"),0);

		//select Departure date on calendar
		selectCalendarDate(strActualDepDate);

		//*****************************************************************
		//*************************Arrival Date****************************
		//*****************************************************************
		//check arrival date is defined
		if(!strArrDate.equals("NA")){
			//convert date in required format
			strActualArrDate = verifyUSAHolidayDates(TestUtil.getStringDateFormat(strArrDate, "MM/dd/yyyy"),Integer.parseInt(strArrDate)-Integer.parseInt(strDepDate));

			//select Arrival date on calendar
			selectCalendarDate(strActualArrDate);

		}

		//validate depart date
		ValidationUtil.validateTestStep("User Selected Departure Date as "+ strActualDepDate + " on Home Page", true);

		//store dep date
		scenarioContext.setContext(Context.HOMEPAGE_DEP_DATE, TestUtil.getDaysFromStringDate(strActualDepDate));

		//set global variable for 24 hour flight
		if(TestUtil.getDaysFromStringDate(strActualDepDate).equalsIgnoreCase("0")){
			scenarioContext.setContext(Context.HOMEPAGE_CHECK_IN, "24Hours");
		}else{
			scenarioContext.setContext(Context.HOMEPAGE_CHECK_IN, "Outside24Hours");
		}

		//check for return date
		if(!strArrDate.equals("NA")){
			//validate return date
			ValidationUtil.validateTestStep("User Selected Return Date as "+ strActualArrDate + " on Home Page", true);

			//store ret date
			scenarioContext.setContext(Context.HOMEPAGE_ARR_DATE, TestUtil.getDaysFromStringDate(strActualArrDate));
		}
	}

	//**********************************************************************************************
	// Method : selectDatesOnCalendar
	// Description: Method is used to select dates on Home Page
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 14-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	private synchronized void selectCalendarDate(String selectedDate){

		for(int dateCounter=0;dateCounter<15;dateCounter++){

			String calendarMonth = TestUtil.getMonthNumberFromMonthName(homePage.getCalendarHeaderValuesText().get(0).getText());

			String calendarYear = homePage.getCalendarHeaderValuesText().get(1).getText();

			if(Integer.toString(Integer.parseInt(selectedDate.split("/")[0])).equals(Integer.toString(Integer.parseInt(calendarMonth))) && selectedDate.contains(calendarYear)){
				List<WebElement> calendarAllRows= homePage.getCalendarMonthDatesText().get(0).findElements(By.tagName("td"));

				for(WebElement calendarRow : calendarAllRows){

					if(!calendarRow.getAttribute("class").contains("week")){
						List<WebElement> calendarAllDates = calendarRow.findElements(By.tagName("span"));
						if(!calendarAllDates.get(0).getAttribute("class").contains("disabled") && calendarAllDates.get(0).isDisplayed() && calendarAllDates.get(0).getText().toString().length()>0){
							//System.out.println(calendarAllDates.get(0).getText());
							if((Integer.toString(Integer.parseInt(selectedDate.split("/")[1])).equals(Integer.toString(Integer.parseInt(calendarAllDates.get(0).getText()))))){

								calendarAllDates.get(0).click();

								dateCounter=17;

								break;
							}
						}
					}
				}
			}else{
				for(WebElement nextMonthLink : homePage.getCalendarNextButton()){
					if(nextMonthLink.isDisplayed()){
						nextMonthLink.click();

						break;
					}
				}
			}
		}

	}
	//**********************************************************************************************
	// Method : selectDates
	// Description: Method is used to select dates on Home Page
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 14-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	public synchronized void selectDates(String strDepDate, String strArrDate) {
		//declare variable used in method
		String strActualDepDate, strActualArrDate = null, strFinalDate=null;

		//*****************************************************************
		//***********************Departure Date****************************
		//*****************************************************************
		//get the dep date
		if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
			strActualDepDate = verifyUSAHolidayDates(TestUtil.getStringDateFormat(strDepDate, "MM/dd/yyyy"),0);
		}else{
			strActualDepDate = verifyUSAHolidayDates(TestUtil.getStringDateFormat(strDepDate, "dd/MM/yyyy"),0);
		}

		//*****************************************************************
		//*************************Arrival Date****************************
		//*****************************************************************
		//check arrival date is defined
		if(!strArrDate.equals("NA")){
			//convert date in required format
			if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
				strActualArrDate = verifyUSAHolidayDates(TestUtil.getStringDateFormat(strArrDate, "MM/dd/yyyy"),Integer.parseInt(strArrDate)-Integer.parseInt(strDepDate));
			}else{
				strActualArrDate = verifyUSAHolidayDates(TestUtil.getStringDateFormat(strArrDate, "dd/MM/yyyy"),Integer.parseInt(strArrDate)-Integer.parseInt(strDepDate));
			}

			strFinalDate = strActualDepDate + " - " + strActualArrDate;
		}else{
			strFinalDate = strActualDepDate;
		}

		//clear previous dates
		TestUtil.clearTextBoxUsingSendKeys(driver, homePage.getDateBox());

		//enter new dates
		homePage.getDateBox().sendKeys(strFinalDate);
		homePage.getDateBox().sendKeys(Keys.TAB);

		//validate depart date
		ValidationUtil.validateTestStep("User Selected Departure Date as "+ strActualDepDate + " on Home Page", true);

		//store dep date
		scenarioContext.setContext(Context.HOMEPAGE_DEP_DATE, TestUtil.getDaysFromStringDate(strActualDepDate));

		//set global variable for 24 hour flight
		if(TestUtil.getDaysFromStringDate(strActualDepDate).equalsIgnoreCase("0")){
			scenarioContext.setContext(Context.HOMEPAGE_CHECK_IN, "24Hours");
		}else{
			scenarioContext.setContext(Context.HOMEPAGE_CHECK_IN, "Outside24Hours");
		}

		//check for return date
		if(!strArrDate.equals("NA")){
			//validate return date
			ValidationUtil.validateTestStep("User Selected Return Date as "+ strActualArrDate + " on Home Page", true);

			//store ret date
			scenarioContext.setContext(Context.HOMEPAGE_ARR_DATE, TestUtil.getDaysFromStringDate(strActualArrDate));
		}
	}

	public synchronized void selectTravellingPassenger(String strAdultCount,String strChildCount,String strInfantSeatCount,String strInfantLapCount) {
		//declare variable used in method
		boolean bolAdultFlag, bolChildFlag;

		//store pax details on global variable
		scenarioContext.setContext(Context.HOMEPAGE_ADULT_COUNT, strAdultCount);
		scenarioContext.setContext(Context.HOMEPAGE_CHILD_COUNT, strChildCount);
		scenarioContext.setContext(Context.HOMEPAGE_INFANTSEAT_COUNT, strInfantSeatCount);
		scenarioContext.setContext(Context.HOMEPAGE_INFANTLAP_COUNT, strInfantLapCount);

		int intAdultCount      = Integer.parseInt(strAdultCount);
		int intChildCount      = Integer.parseInt(strChildCount);
		int intInfantSeatCount = Integer.parseInt(strInfantSeatCount);
		int intInfantLapCount  = Integer.parseInt(strInfantLapCount);

		int intTotalChild    = (intChildCount + intInfantSeatCount + intInfantLapCount);
		String strTotalChild = Integer.toString(intTotalChild);

		//open passenger selection drop down
		homePage.getPassengerBox().click();

		//wait 1.2 seconds
		WaitUtil.untilTimeCompleted(1200);

		//loop through all adult count
		for(int adultCounter=0;adultCounter<=9;adultCounter++) {
			if(intAdultCount>Integer.parseInt(JSExecuteUtil.getElementTextValue(driver, homePage.getAdultBox()))) {
				homePage.getAdultPlusLink().click();
			}else if(intAdultCount<Integer.parseInt(JSExecuteUtil.getElementTextValue(driver, homePage.getAdultBox()))) {
				homePage.getAdultMinusLink().click();
			}else if(intAdultCount==Integer.parseInt(JSExecuteUtil.getElementTextValue(driver, homePage.getAdultBox()))) {
				//do nothing
				break;
			}
		}

		for(int childCounter=0;childCounter<=9;childCounter++) {
			if(intTotalChild>Integer.parseInt(JSExecuteUtil.getElementTextValue(driver, homePage.getChildBox()))) {
				homePage.getChildPlusLink().click();
			}else if(intTotalChild<Integer.parseInt(JSExecuteUtil.getElementTextValue(driver, homePage.getChildBox()))) {
				homePage.getChildMinusLink().click();
			}else if(intTotalChild==Integer.parseInt(JSExecuteUtil.getElementTextValue(driver, homePage.getChildBox()))) {
				//do nothing
				break;
			}
		}

		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);

		WaitUtil.untilTimeCompleted(1000);

		//get selected passengers
		String stractualPassengerCount = JSExecuteUtil.getElementTextValue(driver, homePage.getPassengerBox());

		//check adult passenger
		bolAdultFlag = stractualPassengerCount.contains( strAdultCount + " Adult")?true:false;
		ValidationUtil.validateTestStep("User Selected "+ strAdultCount + " Adult on Home Page", bolAdultFlag);

		//check for language
		if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
			bolChildFlag = stractualPassengerCount.contains( strTotalChild + " Child")?true:false;
		}else{
			bolChildFlag = stractualPassengerCount.contains( strTotalChild + " Niño")?true:false;
		}

		ValidationUtil.validateTestStep("User Selected "+ strTotalChild + " Child and Infant on Home Page", bolChildFlag);

		homePage.getPassengerBox().click();
		homePage.getPassengerBox().sendKeys(Keys.TAB);
	}

	public synchronized void clickSearchButton() {
		//check search flight is visible on Home Page
		WaitUtil.untilTimeCompleted(2000);
		WaitUtil.untilJqueryIsDone(driver);
		if (homePage.getSearchFlightButton().size() > 0) {
			for(WebElement searchFlight:homePage.getSearchFlightButton()){
				if(searchFlight.isDisplayed()){
					//click on search flight button
					JSExecuteUtil.clickOnElement(driver, searchFlight);
//						homePage.getSearchFlightButton().get(0).click();
				}
			}
			//check search vacation is visible on Home Page
		} else if (homePage.getSearchVacationButton().size() > 0) {
			for(WebElement searchVacation:homePage.getSearchVacationButton()){
				if(searchVacation.isDisplayed()){
					//click on search vacation on Home Page
//				        JSExecuteUtil.clickOnElement(driver, homePage.getSearchVacationButton().get(0));
//			    		homePage.getSearchVacationButton().get(0).click();
					searchVacation.sendKeys(Keys.ENTER);
				}
			}
			//check for search car is visible on Home Page
		} else if (homePage.getSearchCarButton().size() > 0) {
			//click on search car on Home Page
//				JSExecuteUtil.clickOnElement(driver, homePage.getSearchCarButton().get(0));
//			homePage.getSearchCarButton().get(0).click();
			homePage.getSearchCarButton().get(0).sendKeys(Keys.ENTER);
		}
		//wait for page load
		WaitUtil.untilPageLoadComplete(driver, (long) 120);

		//validate after click on search button
		if (driver.getCurrentUrl().contains("book")) {
			//validate user click on search button
			ValidationUtil.validateTestStep("User click on Search button and Successfully move from Home Page", true);
		} else if (homePage.getChildTravelerPanel().size() > 0) {
			//validate user click on search button
			ValidationUtil.validateTestStep("User click on Search button and Young Travelers popup appear on Home Page", true);
		} else {
			//validate user click on search button
			//ValidationUtil.validateTestStep("User click on Search button and Successfully move from Home Page", false);
		}
	}

	// **********************************************************************************************
	// Method : fillYoungTravellerPopup
	// Description: Method is used fill the DOB of child and infant based on the passenger selected on Home Page
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 11-June-2019
	// ***********************************************************************************************
	public synchronized void fillYoungTravellerPopup() {
		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);

		//declare variable used in method
		int ChildInfantDOB =	homePage.getChildTravelerPanel().size();
		int ChildCounter =0;
		int InfantCounter = 0;
		//int InfantSeat = 0;
		int InfantLap = 0;

		//get pax details from global variables
		int intChildCount  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString());
		int intInfantSeat  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());
		int intInfantLap   = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTLAP_COUNT).toString());

		int intInfantCount = intInfantSeat + intInfantLap;

		//loop through all DOB box available on Young Traveller popup page
		for(int DateBoxCounter=0;DateBoxCounter < ChildInfantDOB;DateBoxCounter++) {
			//get the DOB box available
			WebElement travellingChildSection = homePage.getChildTravelerPanel().get(DateBoxCounter);

			//check date is already filled by user then skip this date box
			if(JSExecuteUtil.getElementTextValue(driver,travellingChildSection.findElement(By.tagName("input"))).length() > 4){
				//continue to next date box
				continue;
			}

			//check
			if(intChildCount > ChildCounter) {
				if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
					//convert date in required format
					travellingChildSection.findElement(By.tagName("input")).click();
					travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-2190 - ChildCounter), "MM/dd/yyyy"));
				}else{
					//convert date in required format
					travellingChildSection.findElement(By.tagName("input")).click();
					travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-2190 - ChildCounter), "dd/MM/yyyy"));
				}


				travellingChildSection.click();

				//increment child date
				ChildCounter = ChildCounter + 1;

				//break loop
				continue;
			}else if(intInfantCount > InfantCounter) {
				if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
					//convert date in required format
					travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-14 - InfantCounter), "MM/dd/yyyy"));
				}else{
					//convert date in required format
					travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-14 - InfantCounter), "dd/MM/yyyy"));
				}


				travellingChildSection.click();

				//increment child date
				InfantCounter = InfantCounter + 1;

				//wait for page load
				WaitUtil.untilPageLoadComplete(driver);

				if(intInfantLap>InfantLap) {
					//select lap child
					travellingChildSection.findElements(By.xpath("//label[contains(@for,'LapOption')]")).get(InfantLap).click();

					//increment lap child by one
					InfantLap = InfantLap + 1;
				}

				//break loop
				continue;
			}
		}

		//close child popup
		JSExecuteUtil.clickOnElement(driver,homePage.getChildPopUpCloseButton());
//    	homePage.getChildPopUpCloseButton().click();

		//wait till page load is complete
		WaitUtil.untilPageLoadComplete(driver);
	}

	// **********************************************************************************************
	// Method : fillPassengerDOB
	// Description: Method is used fill the passenger DOB from PassengerInfo.json File
	// Input Arguments: passengerType:Type of passenger from json file whose DOB is required
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 11-June-2019
	// ***********************************************************************************************
	public synchronized void fillPassengerDOB(String passengerType){
		//get passnger details from Passnger Info json file
		PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest(passengerType);

		//fill the passenger DOB
		homePage.getChildPopUpBirthBoxes().get(0).sendKeys(passengerInfoData.dob);

		//verify passenger DOB is filled
		ValidationUtil.validateTestStep("User enter the " + passengerInfoData.dob + " date for  " + passengerType + " passnger on Home Page", true);

		//click on continue button
		homePage.getChildPopUpCloseButton().click();
	}

	// **********************************************************************************************
	// Method : selectUMNRPopup
	// Description: Method is used select the UMNR popup on Home Page
	// Input Arguments: passengerType:Type of passenger from json file whose DOB is required
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 11-June-2019
	// Reviewed By: Kartik Chauhan
	// Reviewed On: 11-June-2019
	// ***********************************************************************************************
	public synchronized void selectUMNRPopup(){

		//wait till UMNR popup appear
		WaitUtil.untilPageLoadComplete(driver);

		//accept UMNR popup
		homePage.getUMNRAcceptButton().click();

		//verify UMNR popup is selected
		ValidationUtil.validateTestStep("User accepted the UMNR popup on Home Page", true);

		WaitUtil.untilPageLoadComplete(driver);
	}

	//**********************************************************************************************
	// Method : selectDriversAge
	// Description: Method is used to select the driver age 
	// Input Arguments: drivers_Age
	// Return: NA
	// Created By : Sunny Lok
	// Created On : 11-Mar-2019
	// Reviewed By: Salim Ansari
	// Reviewed On: 14-Mar-2019
	// ***********************************************************************************************
	public synchronized void selectDriversAge(String drivers_Age) {
		//select value by visible text
		TestUtil.selectDropDownUsingVisibleText(homePage.getDriversAgeDropDown(), drivers_Age);

		ValidationUtil.validateTestStep("User selects: " + drivers_Age + "for drivers age on Home Page", true);
	}

	// **********************************************************************************************
	// Method : selectHotelRoom
	// Description: Method is used to select the hotel room
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Sunny Lok
	// Created On : 11-Mar-2019
	// Reviewed By: Salim Ansari
	// Reviewed On: 14-Mar-2019
	// ***********************************************************************************************
	public synchronized void selectHotelRoom(String HotelRoom) {
		//select value by visible text
		TestUtil.selectDropDownUsingVisibleText(homePage.getRoomsDropDown(), HotelRoom);

		//store room information
		scenarioContext.setContext(Context.HOMEPAGE_ROOMS,HotelRoom.split(" ")[0]);

		ValidationUtil.validateTestStep("User selects: " + HotelRoom + " on Home Page", true);

	}

	// **********************************************************************************************
	// Method : enterPromoCode
	// Description: Method is used to select the hotel room
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Sunny Lok
	// Created On : 11-Mar-2019
	// Reviewed By: Salim Ansari
	// Reviewed On: 14-Mar-2019
	// ***********************************************************************************************
	public synchronized void enterPromoCode(String PromoCode) {
		//Clicking on Promocode link
		homePage.getPromoCodeLink().click();

		//Inputting PromoCode
		homePage.getPromoCodeTextBox().sendKeys(PromoCode.toLowerCase());

		ValidationUtil.validateTestStep("User Inputs Promo code: " + PromoCode + " on Home Page", true);
	}

	// **********************************************************************************************
	// Method : selectOneWayInternationalPopup
	// Description: Method is used to select the one way International Popup
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 14-Mar-2019
	// Reviewed By: 
	// Reviewed On: 
	// ***********************************************************************************************
	public synchronized void selectOneWayInternationalPopup() {
		//click on ok button
		homePage.getOneWayInternationalButton().click();

		//save in global variable
		scenarioContext.setContext(Context.HOMEPAGE_INTERNATIONAL,"International");

		ValidationUtil.validateTestStep("User select the One Way International Popup on Home Page", true);
	}

	// **********************************************************************************************
	// Method : verifyUSAHolidayDates
	// Description: Method is used to
	// Input Arguments: HotelRoom
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 14-Mar-2019
	// Reviewed By:
	// Reviewed On:
	// ***********************************************************************************************
	private synchronized String verifyUSAHolidayDates(String requiredDate,int dateDiff){
		//create constant of all USA Holiday dates
		final String USA_HOLIDAYS 		= "21-Nov-19|05-Dec-19||18-Dec-19|08-Jan-20||08-Mar-20|22-Mar-20||20-May-20|03-Jun-20||27-Jun-20|11-Jul-20||26-Aug-20|09-Sep-20||24-Sep-20|07-Nov-20";
		final String SINGLE_SEPARATOR	= "\\|";
		final String DOUBLE_SEPARATOR	= "\\|\\|";

		//declare variable used in method
		Date lowerDate, upperDate, currentDate, todayDate;

		//System.out.println(TestUtil.getDaysFromStringDate(requiredDate));

		//return same date when booking is normal
		if(!scenarioContext.getContext(Context.HOMEPAGE_JOURNEYTYPE).toString().equalsIgnoreCase("Vacation")){
			return requiredDate;
		}

		//selectable date
		currentDate = TestUtil.convertStringToDate(requiredDate,"MM/dd/yyyy");
		todayDate = TestUtil.convertStringToDate(TestUtil.getStringDateFormat("0","MM/dd/yyyy"),"MM/dd/yyyy");

		//loop through all dates
		for(String holidayRange : USA_HOLIDAYS.split(DOUBLE_SEPARATOR)){

			lowerDate = TestUtil.convertStringToDate(holidayRange.split(SINGLE_SEPARATOR)[0],"dd-MMM-yy");

			upperDate = TestUtil.convertStringToDate(holidayRange.split(SINGLE_SEPARATOR)[1],"dd-MMM-yy");

			if(currentDate.compareTo(lowerDate) >= 0 && currentDate.compareTo(upperDate) <= 0){
				upperDate = TestUtil.addDays(upperDate,1);
				long difference = upperDate.getTime() - todayDate.getTime() + dateDiff *(1000*60*60*24);
				long daysBetween = (difference / (1000*60*60*24));

				//convert date in required format
				if(scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")){
					return TestUtil.getStringDateFormat(Integer.toString(Integer.parseInt(Long.toString(daysBetween))),"MM/dd/yyyy");
				}else{
					return TestUtil.getStringDateFormat(Integer.toString(Integer.parseInt(Long.toString(daysBetween))),"dd/MM/yyyy");
				}
			}
		}
		return requiredDate;
	}
}