package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


// **********************************************************************************************
//Test Case ID: TC91343
// Description: Taxes and Fees_CP_BP_RT_To El Salvador (San Salvador)
// Created By : Kartik Chauhan
// Created On : 02-Apr-2019
// Reviewed By: Salim Ansari
// Reviewed On: 04-Apr-2019
// **********************************************************************************************
public class TC91343 extends BaseClass {
    //***********************Navigation to Purchase Page**********************************/
    @Parameters ({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticInternational" , "Outside21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "TravelInsuranceBlock" , "MasterCard" , "TaxesAndFee" , "PaymentUI", "ConfirmationUI"})
    public void Taxes_and_Fees_CP_BP_RT_To_El_Salvador_San_Salvador (@Optional("NA")String platform){

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91343 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "SAL";
        final String DEP_DATE 			= "26";
        final String ARR_DATE 			= "30";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "nonstop";
        final String ARR_FLIGHT 		= "nonstop";
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
        // pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//STEP--2 & Step--3
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
//Step--4
        //make a click on Flight section
        pageObjectManager.getPaymentPage().getTotalDueFlightText().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //***********************Validate on Purchase Page******************************/
        //declare constant used in validation
        boolean taxFlag = false;

        //****************************Taxes To San Salvador****************************

        //create list for all Taxes to be verified
        List <String> taxList = new ArrayList<String>();

        //add tax into list
        taxList.add("Regulatory Compliance Charge (Carrier Fee)");
        taxList.add("Fuel Charge (Carrier Fee)");
        taxList.add("Security Fee");
        taxList.add("US-International Departure/Arrival Tax");
        taxList.add("Passenger Usage Charge (Non-refundable Carrier Fee)");
        taxList.add("Passenger Facility Fee");

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
            ValidationUtil.validateTestStep("Verifing Tax verbiages To San Salvador City pair of Tax " + (taxList.get(taxCounter)) + " on Payment Page", taxFlag);

            //set flag to false for next tax
            taxFlag = false;

        }

        //Validate All Taxes are displaying correctly
        ValidationUtil.validateTestStep("All Taxes To San Salvador is displaying correctly for Roundtrip on Payment Page", true);

//STEP--5
        //Reach to Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

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

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

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
            ValidationUtil.validateTestStep("Verifing Tax verbiages To San Salvador City pair of Tax " + (taxList.get(taxCounter)) + " on Confirmation Page", taxFlag);

            //set flag to false for next tax
            taxFlag = false;

        }

        //Validate All Taxes are displaying correctly
        ValidationUtil.validateTestStep("All Taxes To San Salvador is displaying correctly for Roundtrip on Confirmation Page", true);

        //**********************************Belongs to Skyspeed******************************
//STEP--7
    }
}
