package com.spirit.testcasesBAT;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC90751
//Description: Flight Availability_CP_BP_Flight Only_Verify than an already selected flight can be changed
//Created By : Anthony C
//Created On : 11-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Mar-2019
//**********************************************************************************************

public class TC90751 extends BaseClass {

	@Parameters ({"platform"})
	@Test(groups = {"Guest","BookPath","OneWay","Adult","DomesticDomestic","Within21Days","NewFlightSearch","BookIt",
					"FlightAvailabilityUI","NonStop"})
	public void Flight_Availability_CP_BP_Flight_Only_Verify_that_an_already_selected_flight_can_be_changed (@Optional("NA")String platform) {
		/******************************************************************************
		 ***********************Navigate to Flight Availability Page*******************
		 ******************************************************************************/
		//Mention Suite and Browser in reports 
		if(!platform.equals("NA")) {
			ValidationUtil.validateTestStep("Starting Test Case ID TC90751 under BAT Suite on " + platform + " Browser"   , true);
		}
		
		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "OneWay";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "ATL";
		final String DEP_DATE 			= "1";
		final String NO_DATE			= "NA";
		
		//Flight Availability Constant Variables
		final String SEASONAL_SERVICE	= "Seasonal service";
		final String DEP_FLIGHT 		= "NonStop";
		
		//open browser 
		openBrowser( platform);
		
		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
		pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
		pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
		pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, NO_DATE);
		pageMethodManager.getHomePageMethods().clickSearchButton();

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(3000);

		// Skip test case as Seasonal service of Las Cabos are not active
		if (TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getHomePage().xpath_SeasonalServiceText))){
			//Skip Test Case if its service are still not Active
			ValidationUtil.skipTestCase("Seasonal Service of Las Cabos are not active",pageObjectManager.getHomePage().getSeasonalServiceText().getText().contains(SEASONAL_SERVICE));
		}
				
		//Flight Availability Methods
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		
		/******************************************************************************
		 ***********************Validation on Flight Availability Page*****************
		 ******************************************************************************/
		/*
		 * This test cases required the following steps, which are not implemented in this script as it is not part of the functionality being tested
		 * 
		 * Step 4 : Sort By FLight Duration
		 * Step 5 : Click on View Seat Map and verify tabs of flight Stops
		 * Validation on flight standard select radioButton price is same as price Breakdown
		 */
		
		//Input information for new Flight Search
		final String NEW_TRIP_TYPE 			= "OneWay";
		final String NEW_DEP_AIRPORTS 		= "AllLocation";
		final String NEW_DEP_AIRPORT_CODE 	= "FLL";
		final String NEW_ARR_AIRPORTS 		= "AllLocation";
		final String NEW_ARR_AIRPORT_CODE 	= "MCO";
		final String NEW_DEP_DATE 			= "10";
		
		//Upgrade to select on travel and save pop-up
		final String FARE_TYPE				= "Standard";
		final String UPGRADE_TYPE 			= "BookIt";
		
		//declare constant used on Passenger Page
		final String PASSENGER_PAGE 		= "book/passenger";
		
		//declare variable used in validation
		double flightPrice;
		double newSearchFlightPrice;
		
		//get total flight price
		flightPrice = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().replace("$", "").trim());
		
		//loop through all element
		for (WebElement editButton : pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton())
		{
			//check edit button is displayed
			if (TestUtil.verifyElementDisplayed(editButton)) {
				//click on edit button
				editButton.click();
			}
		}
		
		//click on New Search 
		pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().sendKeys(Keys.ENTER);
		
		//wait for pag load is complete
		WaitUtil.untilPageLoadComplete(getDriver());
		
		//Flight Availability New Search Methods
		pageMethodManager.getHomePageMethods().selectTripType(NEW_TRIP_TYPE);
		pageMethodManager.getHomePageMethods().selectAirports(NEW_DEP_AIRPORTS, NEW_DEP_AIRPORT_CODE, NEW_ARR_AIRPORTS, NEW_ARR_AIRPORT_CODE);
		pageMethodManager.getHomePageMethods().selectDates(NEW_DEP_DATE, NO_DATE);
		pageMethodManager.getHomePageMethods().clickSearchButton();

		//put wait for 2 sec
		WaitUtil.untilTimeCompleted(3000);

		// Skip test case as Seasonal service of Las Cabos are not active
		if (TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHomePage().xpath_SeasonalServiceText))){
			//Skip Test Case if its service are still not Active
			ValidationUtil.skipTestCase("Seasonal Service of Las Cabos are not active",
					pageObjectManager.getHomePage().getSeasonalServiceText().getText().contains(SEASONAL_SERVICE));
		}
		
		//Flight Availability Methods for selecting Flight
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		
		//validate new flight price is different from first flight change
		newSearchFlightPrice = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().replace("$", "").trim());

		//verify different flight is selected
		System.out.println("Old Flight Price:" + flightPrice);
		System.out.println("New Flight Price:" + newSearchFlightPrice);

		ValidationUtil.validateTestStep("The New Flight Search is not same price as the original price" , flightPrice != newSearchFlightPrice);
		
		//select the fare on FA page
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		
		//validate that the Travel more pop-up is displayed
		ValidationUtil.validateTestStep("The Bundle and save Pop Up is not displayed",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getFlightAvailabilityPage().xpath_BundleFareHeaderText)));

		//user selects Book it on the Flight Availability Page
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);
		
		//validate user taken to the Passenger Information Page
		ValidationUtil.validateTestStep("The Boost it button is not displayed, User reached the Passenger Info Page",
				getDriver().getCurrentUrl(),PASSENGER_PAGE);
	}
	
}
