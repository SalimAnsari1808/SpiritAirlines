package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
// Test Case ID: TC91358
// Description: Taxes and Fees_CP_BP_RT_FROM Mexico (Los Cabos)
// Created By : Kartik Chauhan
// Created On : 05-Apr-2019
// Reviewed By: Salim Ansari
// Reviewed On: 10-Apr-2019
//**********************************************************************************************
public class TC91358 extends BaseClass {
    //************************Navigation to Purchase Page***************************/
    @Parameters ({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "InternationalDomestic" , "Outside21Days" , "Adult" , "Guest" , "Connecting" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "TravelInsuranceBlock" , "MasterCard", "TaxesAndFee" , "PaymentUI", "ConfirmationUI"})
    public void Taxes_and_Fees_CP_BP_RT_From_Mexico_Los_Cabos (@Optional("NA")String platform){
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91358 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "SJD";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "26";
        final String ARR_DATE 			= "30";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String SEASONAL_SERVICE	= "Seasonal service";

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

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(3000);

        // Skip test case as Seasonal service of Las Cabos are not active
        if (TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getSeasonalServiceText())){
            //Skip Test Case if its service are still not Active
            ValidationUtil.skipTestCase("Seasonal Service of Las Cabos are not active",pageObjectManager.getHomePage().getSeasonalServiceText().getText().contains(SEASONAL_SERVICE));
        }

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--2 & STEP--3
        //Passenger Info Methods
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
        pageObjectManager.getPaymentPage().getTotalDueText().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--4
        //make a click on Flight section
        pageObjectManager.getPaymentPage().getTotalDueFlightText().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //***********************Validate on Purchase Page*****************************/
        //declare constant used in validation
        boolean taxFlag = false;

        //****************************Taxes From Mexico (Los Cabos)******************

        //create list for all Taxes to be verified
        List <String> taxList = new ArrayList<String>();

        //add tax into list
        taxList.add("Regulatory Compliance Charge (Carrier Fee)");
        taxList.add("Fuel Charge (Carrier Fee)");
        taxList.add("MX -TUA");
        taxList.add("Security Fee");
        taxList.add("US-International Departure/Arrival Tax");
        taxList.add("US Customs Fee");
        taxList.add("US APHIS Fee");
        taxList.add("US Immigration Fee");
        taxList.add("Passenger Usage Charge (Non-refundable Carrier Fee)");
        taxList.add("Passenger Facility Fee");
        taxList.add("MX-Value Added Tax");

        //create loop for all Taxes and calculate its size
        for (int taxCounter = 0; taxCounter < taxList.size(); taxCounter++) {
            //Below Xpaths are not added into framework
            List<WebElement> allTaxesVerbiage = pageObjectManager.getPaymentPage().getDepTotalBreakDownFlightText();

            //create loop for all Taxes and calculate its size
            for(int Counter=0;Counter<allTaxesVerbiage.size();Counter++){
                //Verify 'All Taxes verbiage'
                if(allTaxesVerbiage.get(Counter).isDisplayed()){
                    //verify both taxes are equal
                    if(allTaxesVerbiage.get(Counter).getText().trim().contains(taxList.get(taxCounter))){
                        //set flag to true
                        taxFlag = true;

                        break;
                    }
                }
            }

            //validate for all the taxes in console
            ValidationUtil.validateTestStep("Verifing Tax verbiages From Mexico (Los Cabos) City pair of Tax " + (taxList.get(taxCounter)) + " on Payment Page", taxFlag);

            //set flag to false for next tax
            taxFlag = false;

        }

        //Validate All Taxes are displaying correctly
        ValidationUtil.validateTestStep("All Taxes From \"scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT)\" To " +  scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT) + " are displaying correctly for Roundtrip on Payment Page", true);

//STEP--5
        //Reach to Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));
//STEP--6
        //******************************************************************************
        //****************************Validation on Confirmation Page*******************
        //******************************************************************************/
        //Confirmation Page Total Due Verbiage
        pageObjectManager.getConfirmationPage().getTotalPaidBreakDownLink().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //make a click on Flight section
        pageObjectManager.getConfirmationPage().getFlightVerbiageLabel().click();

        //create loop for all Taxes and calculate its size on Confirmation Page
        for (int taxCounter = 0; taxCounter < taxList.size(); taxCounter++) {
            //Below Xpaths are not added into framework
            List<WebElement> allTaxesVerbiage = pageObjectManager.getPaymentPage().getDepTotalBreakDownFlightText();

            //create loop for all Taxes and calculate its size
            for(int Counter=0;Counter<allTaxesVerbiage.size();Counter++){
                //Verify 'All Taxes verbiage'
                if(allTaxesVerbiage.get(Counter).isDisplayed()){
                    //verify both taxes are equal
                    if(allTaxesVerbiage.get(Counter).getText().trim().contains(taxList.get(taxCounter))){
                        //set flag to true
                        taxFlag = true;

                        break;
                    }
                }
            }

            //validate for all the taxes in console
            ValidationUtil.validateTestStep("All Taxes From \" + scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT)+\" To \" +  scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT) + \" are displaying correctly for Roundtrip on Confirmation Page", taxFlag);

            //set flag to false for next tax
            taxFlag = false;

        }

        //Validate All Taxes are displaying correctly
        ValidationUtil.validateTestStep("All Taxes From Mexico (Los Cabos) is displaying correctly for Roundtrip on Confirmation Page", true);

        //**********************************Belongs to Skyspeed******************************
//STEP--7
    }
}
