package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
// Test Case ID: TC91354
// Description: Taxes and Fees_CP_BP_RT_From Jamaica (Kingston)
// Created By : Kartik Chauhan
// Created On : 17-Apr-2019
// Reviewed By: Salim Ansari
// Reviewed On: 17-Apr-2019
//**********************************************************************************************
public class TC91354 extends BaseClass {
    //***********************Navigation to Purchase Page****************************/
    @Parameters ({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "InternationalDomestic" , "Outside21Days" , "Adult" , "Guest" , "Connecting" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions","TravelInsuranceBlock" , "TaxesAndFee" , "MasterCard" , "ActiveBug"})
    public void Taxes_and_Fees_CP_BP_RT_From_Jamaica_Kingston (@Optional("NA")String platform){
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91354 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "KIN";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "24";
        final String ARR_DATE 			= "31";
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

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(3000);

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//STEP--2
        //Payment Page Methods
        //***********************Validate on Purchase Page******************************/
        //create list for all Taxes to be verified
        List <String> taxList = new ArrayList<String>();

        //add tax into list
        taxList.add("Regulatory Compliance Charge (Carrier Fee)");
        taxList.add("Fuel Charge (Carrier Fee)");
        taxList.add("JM-Pax Svc And Security");
        taxList.add("JM-Airport Improvement");
        taxList.add("JM-Stamp Tax");
        taxList.add("Jamaica Passenger Facility Charge");
        taxList.add("JM Passenger Aviation Service Charge");
        taxList.add("JM- Travel Tax");
        taxList.add("Security Fee");
        taxList.add("US-International Departure/Arrival Tax");
        taxList.add("US Customs Fee");
        taxList.add("US APHIS Fee");
        taxList.add("US Immigration Fee");
        taxList.add("Passenger Usage Charge (Non-refundable Carrier Fee)");
        taxList.add("Passenger Facility Fee");

        //Create new method to verify "To City pair" taxes
        pageMethodManager.getPaymentPageMethods().toCityPairTaxes(taxList);

        //Validate All Taxes are displaying correctly
        ValidationUtil.validateTestStep("All Taxes From " + scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT)+" To " +  scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT) + " are displaying correctly for Roundtrip on Payment Page", true);

        //scroll down to display taxes on screen
        JSExecuteUtil.scrollDown(getDriver(),"100");

//STEP--3
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

        //**********************************Belongs to Skyspeed******************************
//STEP--5
    }
}
