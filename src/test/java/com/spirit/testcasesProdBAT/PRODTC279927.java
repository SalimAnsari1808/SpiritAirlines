package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC279927
//Description: Flight Flex+Shortcut Security+Shortcut Boarding
//Created By : Anthony C
//Created On : 11-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Mar-2019
//**********************************************************************************************
public class PRODTC279927 extends BaseClass {

	@Parameters ({"platform"})
	@Test(groups="Production")

	public void Flight_FlexShortcut_Security_and_Shortcut_Boarding (@Optional("NA")String platform) {
		/******************************************************************************
		 *****************************Navigate to Optional Page************************
		 ******************************************************************************/
		//Mention Suite and Browser in reports
		if(!platform.equals("NA")) {
			ValidationUtil.validateTestStep("Starting Test Case ID PRODTC279927 under PRODUCTION Suite on " + platform + " Browser"   , true);
		}

		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "OneWay";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "ATL";
		final String DEP_DATE 			= "3";
		final String NO_DATE			= "NA";
		final String ADULTS				= "1";
		final String CHILDS				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";

		//Flight Availability Constant Variables
		final String DEP_FLIGHT 		= "NonStop";

		//Flight Availability Constant Values
		final String MEMBER_FARE_TYPE 	= "Standard";
		final String UPGRADE_VALUE		= "BookIt";

		//open browser
		openBrowser( platform);

		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
		pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
		pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
		pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, NO_DATE);
		pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
		pageMethodManager.getHomePageMethods().clickSearchButton();

		//Flight Availability Methods
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(MEMBER_FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

		//Passenger information Page
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();

		//Bags Page
		pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

		//Seats Page
		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

		/******************************************************************************
		 ***********************Validation Options Page********************************
		 ******************************************************************************/
		//declare constnat used in validation
		//Options PAGE Constant Values
		final String CHECKN_IN_OPTION 	= "ONLINE";
		final String PAYMENT_URL		= "book/payment";
		final int FIRST_INDEX       	= 0;

		//declare constant used in validation
		int numberOfPassengers;
		//Shortcut Security
		double shortcutBoardingPerPerson;
		double expectedShortCutBoardingForAllPax;

		//Flight Flex price
		double FlightFlexPricePerPerson;
		double expectedFlightFlexForAllPax;

		//Short CutSecurity price
		double ShortCutSecurityPricePerPerson;
		double expectedShortCutSecurityForAllPax;

		//get the number iof passenger
		numberOfPassengers = Integer.parseInt(ADULTS) + Integer.parseInt(CHILDS) + Integer.parseInt(INFANT_SEAT);

		/*******************ShortCut Boarding******************************/
		pageMethodManager.getOptionsPageMethods().selectOptions("FlightFlex|ShortCutSecurity,NotRequired|ShortCutBoarding|CheckInOption:MobileFree");

		//verify selected label displayed after adding shortcut security
		ValidationUtil.validateTestStep("Shortcut Boarding is selected on Options Page",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getOptionsPage().xpath_ShortCutBoardingCardSelectedLabel)));

		//verify Remove button is added
		ValidationUtil.validateTestStep("Shortcut Boarding button Changed to Remove on Options Page",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getOptionsPage().xpath_ShortCutBoardingCardRemoveButton)));

		//get expected total price for Shortcut Boarding
		shortcutBoardingPerPerson = Double.parseDouble(pageObjectManager.getOptionsPage().getShortCutBoardingCardPriceText().getText().replace("$", "").trim());

		//calculate expected shortcut security prices
		expectedShortCutBoardingForAllPax = shortcutBoardingPerPerson * numberOfPassengers;

		//open the shopping cart breakdown
		pageObjectManager.getHeader().getArrowYourItineraryImage().click();

		WaitUtil.untilTimeCompleted(2000);

		pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();

		//wait for 1.5 sec
		WaitUtil.untilTimeCompleted(1500);

		//validate shortcut Boarding price on the shopping cart
		double actualShortCutBoardingPrice = Double.parseDouble(pageObjectManager.getHeader().getShortcutBoardingOptionPriceItineraryText().getText().replace("$", "").trim());
		ValidationUtil.validateTestStep("Expected shortcut boarding amount is displayed correctly on the shopping Cart", expectedShortCutBoardingForAllPax == actualShortCutBoardingPrice);

		//wait for 1.5 sec
		WaitUtil.untilTimeCompleted(1500);

		/*******************Flight Flex*****************************/
		//get expected total price for Flight Flex
		FlightFlexPricePerPerson = Double.parseDouble(pageObjectManager.getOptionsPage().getFlightFlexCardPriceText().getText().replace("$", "").trim());

		//claculate expected flight flex prices
		expectedFlightFlexForAllPax = FlightFlexPricePerPerson * numberOfPassengers;

		//validate that Flight Flex is selected
		ValidationUtil.validateTestStep("Flight Flex is selected on Options Page",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getOptionsPage().xpath_FlightFlexCardSelectedLabel)));

		//verify Remove button appear for Flight Flex
		ValidationUtil.validateTestStep("Flight Flex button Changed to Remove on Options Page",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getOptionsPage().xpath_FlightFlexCardRemoveButton)));

		/************this xpath still pending and need to be implemented in separate file***********************/
		//validate Flight Flex price on the shopping cart
		double actualFlightFlexPrice = Double.parseDouble(pageObjectManager.getHeader().getFlightFlexOptionPriceItineraryText().getText().replace("$", "").trim());
		ValidationUtil.validateTestStep("Expected Flight Flex amount is displayed correctly on the shopping Cart", expectedFlightFlexForAllPax == actualFlightFlexPrice);

		/************this xpath still pending and need to be implemented in separate file***********************/

		/*******************ShortCut Security*****************************/
		//Get ShortCut Security Price from Text box
		ShortCutSecurityPricePerPerson = Double.parseDouble(pageObjectManager.getOptionsPage().getShortCutSecurityCardPriceText().getText().replace("$", "").trim());

		//calculate the expected shortcut security prices
		expectedShortCutSecurityForAllPax = ShortCutSecurityPricePerPerson * numberOfPassengers;

		//validate that Short Cut Security is selected
		ValidationUtil.validateTestStep("ShortCut Security is selected",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getOptionsPage().xpath_ShortCutSecurityCardSelectedLabel)));

		//verify shortcut security edit button appear
		ValidationUtil.validateTestStep("ShortCut Security button Changed to \"Edit\"",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getOptionsPage().xpath_ShortCutSecurityCardRemoveButton)));

		//validate that Short Cut Security is correctly priced in the shopping cart
		double actualShortCutSecurityPrice = Double.parseDouble(pageObjectManager.getHeader().getShortcutSecurityOptionPriceItineraryText().getText().replace("$", "").trim());
		ValidationUtil.validateTestStep("Expected shortcut security amount is displayed correctly on the shopping Cart", expectedShortCutSecurityForAllPax == actualShortCutSecurityPrice);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
		ValidationUtil.validateTestStep("User redirected to the Payment Page",
				getDriver().getCurrentUrl(),PAYMENT_URL);
	}
}