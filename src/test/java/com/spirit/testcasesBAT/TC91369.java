package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
// Test Case ID: TC91369
// Description: Taxes_and_Fees_CP_BP_OW_From_Colombia_Armenia
// Created By : Kartik Chauhan
// Created On : 14-Mar-2019
// Reviewed By: Salim Anasari
// Reviewed On: 15-Mar-2019
// *********************************************************************************************
public class TC91369 extends BaseClass {

	@Parameters ({"platform"})
	@Test(groups = {"Guest","BookPath","OneWay","ColumbiaDomestic","Outside21Days","Adult","NonStop","BookIt",
			        "CarryOn","CheckedBags","NoSeats","BagsUI","PaymentUI","CheckInOptions","MasterCard","TaxesAndFee","TravelInsuranceBlock","ConfirmationUI"})
	public void Taxes_and_Fees_CP_BP_OW_From_Colombia_Armenia (@Optional("NA")String platform){
		/******************************************************************************
		 ***********************Navigate to Bags Page**********************************
		 ******************************************************************************/
		//Mention Suite and Browser in reports
		if(!platform.equals("NA")) {
			ValidationUtil.validateTestStep("Starting Test Case ID TC91369 under BAT Suite on " + platform + " Browser"   , true);
		}

		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "OneWay";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "AXM";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "FLL";
		final String DEP_DATE 			= "25";
		final String ARR_DATE 			= "NA";
		final String ADULTS				= "1";
		final String CHILDS				= "0";
		final String INFANT_LAP			= "0";
		final String INFANT_SEAT		= "0";

		//Flight Availability Page Constant Values
		final String DEP_FLIGHT 		= "NonStop";
		final String FARE_TYPE			= "Standard";
		final String UPGRADE_VALUE		= "BookIt";

		//Bags Page constant values
		final String DEP_BAGS 			= "Carry_1|Checked_1";
		final String TOTAL_VAT          = "19";

		//Option Page Constant Values
		final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

		//Payment page Constant values
		final String TRAVEL_GUARD		= "Required";
		final String CARD_TYPE			= "MasterCard";

		//Confirmation Page Constant value
		final String BOOKING_STATUS     = "Confirmed";

		//open browser
		openBrowser( platform);

		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
		pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
		pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
		pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
		pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
		pageMethodManager.getHomePageMethods().clickSearchButton();
		pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

		//Flight Availability Methods
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

		//Passenger Info Methods
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();

		//Bags Page Methods
		pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
//STEP--2
		pageMethodManager.getBagsPageMethods().verifyVATTax("Dep", TOTAL_VAT);
		pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
		//wait for 1 sec
		WaitUtil.untilTimeCompleted(3000);

		//Seat Page Methods
		pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

		//Option Page Methods
		pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
		pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//STEP--3
		//Payment Page Methods
		/******************************************************************************
		 ***********************Validate on Purchase Page******************************
		 ******************************************************************************/
		//create list for all Taxes to be verified
		List <String> taxList = new ArrayList<String>();

		//add tax into list
		taxList.add("Regulatory Compliance Charge (Carrier Fee)");
		taxList.add("Fuel Charge (Carrier Fee)");
		taxList.add("CO - Airport Tax");
		taxList.add("CO - Resident Stamp Tax");
		taxList.add("US-International Departure/Arrival Tax");
		taxList.add("US Customs Fee");
		taxList.add("US APHIS Fee");
		taxList.add("US Immigration Fee");
		taxList.add("Colombian Administrative Fee");
		taxList.add("CO-IVA");

		//Create new method to verify "To City pair" taxes
		pageMethodManager.getPaymentPageMethods().toCityPairTaxes(taxList);

		//Validate All Taxes are displaying correctly
		ValidationUtil.validateTestStep("All Taxes From " + scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT)+" To " +  scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT) + " are displaying correctly for Roundtrip on Payment Page", true);

		//scroll down to display taxes on screen
		JSExecuteUtil.scrollDown(getDriver(),"100");

		//wait for 1 sec
		WaitUtil.untilTimeCompleted(2000);

		//verify VAT tAxes are displaying on Payment Page
		ValidationUtil.validateTestStep("Verify VAT Taxes are displaying in Total Due Breakdown on Payment Page",
				TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getPaymentPage().xpath_DepTotalDueBagsVatPriceText)));

		//validate Vat Taxes are displaying correctly on Payment page
		ValidationUtil.validateTestStep("VAT Tax are correctly displaying on Payment Page ",scenarioContext.getContext(Context.BAGS_DEP_VAT).equals(Double.parseDouble(pageObjectManager.getPaymentPage().getDepTotalDueBagsVatPriceText().getText().replace("$", "").trim().toString())));

//STEP--4
		//Reach to Confirmation page
		pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
		pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

		//Confirmation Page Methods
		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
		pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));

		//******************************************************************************
		//****************************Validation on Confirmation Page*******************
		//******************************************************************************/
		//Create new method to verify "To City pair" taxes
		pageMethodManager.getConfirmationPageMethods().toCityPairTaxesConfirmationpage(taxList);

		//Validate All Taxes are displaying correctly
		ValidationUtil.validateTestStep("All Taxes From " + scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT) + " To " +  scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT) + " are displaying correctly for Roundtrip on Confirmation Page", true);

		//Click on Bags BreakDown
		pageObjectManager.getConfirmationPage().getBagsBreakdownLabel().click();

		//wait for 1 sec
		WaitUtil.untilTimeCompleted(2000);

		//scroll down to display taxes on screen
		JSExecuteUtil.scrollDown(getDriver(),"100");

		//Verify vat Tax is displaying on Confirmation Page
		ValidationUtil.validateTestStep("Verify VAT Taxes are displaying in Total Due Paid on Confirmation Page",
				TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getConfirmationPage().xpath_VatTaxAmountText)));

		//Validate Vat Tax are displaying correctly on Confirmation Page
		ValidationUtil.validateTestStep("Vat Tax are correctly displaying on Confirmation Page ",scenarioContext.getContext(Context.BAGS_DEP_VAT).equals(Double.parseDouble(pageObjectManager.getConfirmationPage().getVatTaxAmountText().getText().replace("$", "").trim().toString())));

		//**********************************Belongs to Skyspeed******************************
//STEP--5
	}
}