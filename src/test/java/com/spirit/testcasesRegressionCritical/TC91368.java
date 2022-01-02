package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91368
// Description: Taxes and Fees_CP_Sales Tax_US
// Created By : Kartik Chauhan
// Created On : 01-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 02-July-2019
// **********************************************************************************************
public class TC91368 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" ,"Header", "Connecting" , "BookIt" , "NoBags" , "NoSeats" ,"CheckInOptions", "TravelInsuranceBlock" , "MasterCard" , "TaxesAndFee" , "PaymentUI"})
    public void Taxes_and_Fees_CP_Sales_Tax_US (@Optional("NA")String platform){
        //**********************Navigate to Purchase Page*******************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91368 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LGA";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "5";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Connecting";
        final String ARR_FLIGHT 		= "Connecting";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE		= "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "Required";
        final String CARD_TYPE			= "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

//STEP--1
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

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--2|STEP--3	(Reach to Passenger Info page but as per new functionality Taxes will reflect on Payment Page only)
        //Passenger Info Methods

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowFlightItineraryImage().click();

        //set value of Flag
        boolean verbiageFlag = true;

        //create list for all Verbiages to be verified
        List <String> VerbiagesList = new ArrayList<String>();

        //add tax into list
        VerbiagesList.add("Federal Excise Tax");

        List<WebElement> ItineraryVerbiage = pageObjectManager.getHeader().getFlightItineraryBlockPanel();

        //create loop for all Taxes and calculate its size
        for (int iterator = 0; iterator < ItineraryVerbiage.size(); iterator++) {

            //Create Constant for TAX
            final String TAX = "Federal Excise Tax";

            //verify if Federal Tax is displaying on Passenger Itinerary block
            if (pageObjectManager.getHeader().getFlightItineraryBlockPanel().get(iterator).getText().contains(TAX)){
                //set flag to true
                verbiageFlag = false;
            }
        }

        //Validate Tax verbiage is not displaying in itinerary of Passenger Info Page
        ValidationUtil.validateTestStep("Federal Excise Tax verbiages is not displaying in itinerary of Passsenger Info Page", verbiageFlag);

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        //***********************Validate on Purchase Page******************************/
        //create list for all Taxes to be verified
        List <String> taxList = new ArrayList<String>();

        //add tax into list

        taxList.add("Regulatory Compliance Charge (Carrier Fee)");
        taxList.add("Fuel Charge (Carrier Fee)");
        taxList.add("Security Fee");
        taxList.add("Segment Fee");
        taxList.add("Passenger Usage Charge (Non-refundable Carrier Fee)");
        taxList.add("Passenger Facility Fee");
        taxList.add("Federal Excise Tax");

        //Validate federal excise tax is displayed
        pageMethodManager.getPaymentPageMethods().toCityPairTaxes(taxList);

        //Validate All Taxes are displaying correctly
        ValidationUtil.validateTestStep("Federal Excise Taxes From " + scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT)+" To " +  scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT) + " is displaying correctly for Roundtrip on Payment Page", true);

//STEP--5
        //Reach to Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
////STEP--6
        //******************************************************************************
        //****************************Validation on Confirmation Page*******************
        //******************************************************************************/

        pageMethodManager.getConfirmationPageMethods().toCityPairTaxesConfirmationpage(taxList);

        //Validate All Taxes are displaying correctly
        ValidationUtil.validateTestStep("Federal Exercise Taxes From " + scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT) + " To " +  scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT) + " is displaying correctly for Roundtrip on Confirmation Page", true);

        //**********************************Belongs to Skyspeed******************************
//STEP--7
    }
}

