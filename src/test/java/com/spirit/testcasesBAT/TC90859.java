package com.spirit.testcasesBAT;

import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//**********************************************************************************************
//Test Case ID: TC90859
//Description: CP_MT_Itinerary Page_OW Make changes to flight
//Created By : Anthony C
//Created On : 20-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 28-Mar-2019
//**********************************************************************************************
public class TC90859 extends BaseClass{

	@Parameters ({"platform"})
	@Test(groups = {"Guest","MyTrips","OneWay","Adult","DomesticDomestic","WithIn21Days","NonStop","BookIt",
					"NoBags","NoSeats","CheckInOptions","MasterCard","ChangeFlight","ConfirmationUI"})
	public void cp_MT_Itinerary_Page_OW_Make_changes_to_flight (@Optional("NA")String platform) {

		if(!platform.equals("NA")){
			ValidationUtil.validateTestStep("Starting Test Case ID TC90859 under BAT suite on " + platform +" Browser", true);
		}

		//Home Page Constant Values
		final String LANGUAGE 					= "English";
		final String JOURNEY_TYPE 				= "Flight";
		final String TRIP_TYPE 					= "OneWay";
		final String DEP_AIRPORTS 				= "AllLocation";
		final String DEP_AIRPORT_CODE 			= "FLL";
		final String ARR_AIRPORTS 				= "AllLocation";
		final String ARR_AIRPORT_CODE 			= "ATL";
		final String DEP_DATE 					= "6";
		final String ARR_DATE 					= "NA";
		final String ADULTS						= "1";
		final String CHILD						= "0";
		final String INFANT_LAP					= "0";
		final String INFANT_SEAT				= "0";

		//Flight Availability Page Constant Values
		final String DEP_FLIGHT 				= "NonStop";
		final String FARE_TYPE					= "Standard";
		final String JOURNEY_UPGRADE			= "BookIt";

		//Options Page Constant values
		final String OPTIONS_VALUE				= "CheckInOption:MobileFree";

		//Payment page Constant Values
		final String CARD_TYPE					= "MasterCard";
		final String TRAVEL_GUARD				= "NotRequired";

		//my trip page constant value
		final String MYTRIP_DEP_CITY			= "FLL";
		final String MYTRIP_ARR_CITY			= "LAS";
		final String MYTRIP_DEP_DATE 			= "10";
		final String MYTRIP_CHANGE_FLIGHT_POPUP	= "Continue";
		final String MYTRIP_SEAT_POPUP			= "DontPurchase";
		final String MYTRIP_BAGS_POPUP			= "DontPurchase";

		//open browser
		openBrowser(platform);

		//Step_ 1,2,3
		/***************************************************
		 *********Navigate to Flight Availability Page******
		 ***************************************************/
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
		pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
		pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
		pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
		pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
		pageMethodManager.getHomePageMethods().clickSearchButton();

		/***************************************************
		 *********Navigate to Passenger Information Page****
		 ***************************************************/
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(JOURNEY_UPGRADE);

		/***************************************************
		 ****************Navigate to Bags Page**************
		 ***************************************************/
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();

		/***************************************************
		 ****************Navigate to Seats Page**************
		 ***************************************************/
		pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

		/***************************************************
		 ****************Navigate to Options Page***********
		 ***************************************************/
		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

		/***************************************************
		 *************Navigate to Payment Page**************
		 ***************************************************/
		WaitUtil.untilPageLoadComplete(getDriver());
		pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

		/***************************************************
		 **************Navigate to Confirmation Page********
		 ***************************************************/
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
		pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

		/***************************************************
		 ************Navigate to ManageTravel Page**********
		 ***************************************************/
		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
		pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

		/*********************Start OF Manage Travel Path**********************/
		//login to My Trip
		pageMethodManager.getHomePageMethods().loginToMyTrip();

//Step - 4	Step -5

		pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep",MYTRIP_DEP_CITY,MYTRIP_ARR_CITY,MYTRIP_DEP_DATE);
		pageMethodManager.getReservationSummaryPageMethods().continueCancelOnChangeFlightPopup(MYTRIP_CHANGE_FLIGHT_POPUP);

//Step - 6

		List<WebElement> listCities;
		List<WebElement> departTimeList;
		List<WebElement> arivalTimeList;
		WebElement flightdates;
		String FlightPrice;

		WaitUtil.untilPageLoadComplete(getDriver());
		manageTravel_SelectMoreExpensiveFlight(FARE_TYPE);
		WaitUtil.untilTimeCompleted(1000);

		String fromCity = null ;
		String  toCity = null ;

		listCities = pageObjectManager.getFlightAvailabilityPage().getSelectedFlightCityText();
		FlightPrice = (String)scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE);

		for(WebElement element : listCities)
			if (TestUtil.verifyElementDisplayed(element)) {
				String str  = element.getText();
				String[] addOfString = str.split(" to ");
				int iterator = 0;

				for(String tempStr : addOfString ) {
					if (iterator == 0 ) {
						fromCity = tempStr;
					}
					else {
						toCity = tempStr;
					}
					iterator++;
				}
			}

		departTimeList = pageObjectManager.getFlightAvailabilityPage().getDepartingFlightBlockDepartTimeText();
		arivalTimeList = pageObjectManager.getFlightAvailabilityPage().getDepartingFlightBlockArivalTimeText();

		String departTime = returnTextFromList(departTimeList);
		String arriveTime = returnTextFromList(arivalTimeList);

		String flightdate = null;

		flightdates = pageObjectManager.getFlightAvailabilityPage().getSelectedDepDateText();
		flightdate = flightdates.getText();
		flightdate = flightdate.substring(flightdate.indexOf(",") + 1).trim();

		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
//Step - 7
		WaitUtil.untilTimeCompleted(1500);
		pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(MYTRIP_BAGS_POPUP);

//Step - 8
		WaitUtil.untilTimeCompleted(1500);
		pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(MYTRIP_SEAT_POPUP);

//Steps - 9
		WaitUtil.untilPageLoadComplete(getDriver());
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();//continue on the Extras page

//Step - 10
		//Input all payment information
		WaitUtil.untilPageLoadComplete(getDriver());
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
		//click on book button
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
		pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
		WaitUtil.untilTimeCompleted(1500);

//Step - 11
		ValidationUtil.validateTestStep("The \"Change Confirmation\" header is displayed",
				pageObjectManager.getConfirmationPage().getConfirmationPageHeaderText().getText(),"Change Confirmation");

		String emailsent = "Your reservation has been changed and an email with the details has been sent to";
		ValidationUtil.validateTestStep("The text displaying \"Your reservation has been changed and an email sent\" header is displayed",
				pageObjectManager.getReservationSummaryPage().getYourReservationChangeText().getText().trim(),emailsent);

//Step - 12
		//validate new date time and cities on the change confirmation page
		String dateonConf;
		String departCityOnConf;
		String ArriveCityOnConf;

		WaitUtil.untilTimeCompleted(1500);

		dateonConf = returnTextFromList(pageObjectManager.getConfirmationPage().getFlightSectionDateText());
		departCityOnConf = returnTextFromList(pageObjectManager.getConfirmationPage().getFlightSectionDepartCityText());
		departCityOnConf = departCityOnConf.replace("Depart:", "").trim();


		ArriveCityOnConf = returnTextFromList(pageObjectManager.getConfirmationPage().getFlightSectionAriveCityText()).replace("Arrive: ", "").trim();
		ValidationUtil.validateTestStep("The New Flight date is correct", dateonConf.contains(flightdate));
		ValidationUtil.validateTestStep("The Departing city and depart time is correct", departCityOnConf.trim().contains(fromCity.trim()) && departCityOnConf.trim().contains(departTime.trim()));
		ValidationUtil.validateTestStep("The Departing city and arrive time is correct", ArriveCityOnConf.trim().contains(toCity.trim()) && ArriveCityOnConf.trim().contains(arriveTime.trim()));

//Step - 13

		pageObjectManager.getReservationSummaryPage().getTotalPaidChevronLink().click();

		WaitUtil.untilTimeCompleted(2000);

		String breakDownPrice = pageObjectManager.getReservationSummaryPage().getTotalPaidOrignalReservationValuePriceText().getText().trim().replace("$","").replace("-","");


		//validate flight price from Manage Travel FA page to Change Confirmation Breakdown
		ValidationUtil.validateTestStep("The Flight price on the breakdown is correct",breakDownPrice.toString().trim(),FlightPrice.toString().trim());
	}

	/**
	 * This method clicks on a more expensive fare that is of paramater fareType
	 * @param fareType String: "club" , "standard", or "card-holder"
	 * @author Anthony Cardona <BR>
	 * Created on 21 Mar, 2019
	 * Reviewed By:
	 * Reviewed Date:
	 */
	private void manageTravel_SelectMoreExpensiveFlight(String fareType)
	{
		WaitUtil.untilTimeCompleted(1500);

		fareType = fareType.toLowerCase();
		if (fareType.contains("card")) fareType = "card-holder"; //if faretype is card, change faretype to "card-holder"

		WaitUtil.untilPageLoadComplete(getDriver());
		List <WebElement> TempallStandardsFares = getDriver().findElements(By.xpath("//label[contains(@for,'" + fareType + "')]"));

		List<WebElement> allStandardFares = new ArrayList<>();
		for(WebElement element : TempallStandardsFares)
		{
			if (TestUtil.verifyElementDisplayed(element))
			{
				allStandardFares.add(element);
			}
		}

		for(WebElement fare : allStandardFares)
		{
			if (fare.getText().contains("+"))// if label contains + it is more expensive than original flight
			{
				fare.click();
				break;
			}
		}
	}

	private String returnTextFromList(List<WebElement> list)
	{
		String str = null;

		for (WebElement element : list)
		{
			if (TestUtil.verifyElementDisplayed(element))
			{
				str = element.getText();
				break;
			}
		}

		return str;
	}
}