package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC90737
// TestCase : 35326 CP_CI_Travel Guard
// Created By : Kartik Chauhan
// Created On : 01-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 02-July-2019
// **********************************************************************************************
public class TC90737 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" ,
                    "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "PaymentUI"})
    public void CP_CI_Travel_Guard (@Optional("NA")String platform){
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90737 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //************************Navigate to Payment Page*****************************
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE 		    	= "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 	  	    = "AllLocation";
        final String ARR_AIRPORT_CODE  	    = "DFW";
        final String DEP_DATE 	    		= "6";
        final String ARR_DATE 	    		= "7";
        final String ADULTS 		       	= "1";
        final String CHILDREN		      	= "0";
        final String INFANT_LAP 	     	= "0";
        final String INFANT_SEAT	     	= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    	= "Connecting";
        final String ARR_Flight 		    = "Connecting";
        final String FARE_TYPE 	    		= "Standard";
        final String UPGRADE_VALUE 	  	    = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD           = "NotRequired";
        final String CARD_TYPE			    = "MasterCard";
//Step 2
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//STEP--1
        //Payment page methods
        //boarding pass page
        final String TRAVEL_MORE    = "TRAVEL MORE. WORRY LESS. GET INSURANCE FOR ONLY";
        final String PER_PERSON     = "ALL TRAVELERS.*";

//Step--2 and //STEP--3
        //verify Travel guard header is displaying on TG section
        ValidationUtil.validateTestStep("Travel More worry less.. Travel guard Section header is displaying on TG section",
                pageObjectManager.getPaymentPage().getTravelMoreWorryLessText().getText(),TRAVEL_MORE);

        //verify Travel guard header is displaying on TG section
        ValidationUtil.validateTestStep("Per person.. Travel guard Section header is displaying on TG section",
                pageObjectManager.getPaymentPage().getTravelMoreWorryLessText().getText(),PER_PERSON);
//Step--4,5,6 and 7
        ///////////validation that the travel Guard text is displaying
        String[] textBeingValidated =
                {
                        "100% Trip Cost Cancellation",
                        "125% Trip Cost Trip Interruption",
                        "$500 Missed Connection",
                        "$500 Trip Delay",
                        "$500 Baggage & Personal Effects"
                };

        for(String element : textBeingValidated)
        {
            try
            {
                getDriver().findElement(By.xpath("//div[@class='insurance-container']//p[contains(text(),'" + element + "')]"));
                ValidationUtil.validateTestStep("The Text \"" + element + "\" on the insurance block is correct" , true); //text should display: pass
            }
            catch (Exception e)
            {
                ValidationUtil.validateTestStep("Travel Guard text  \"" + element + "\"  is suppressed for New York Resident: fail", false);
            }
        }

//Step--8 & 9

        //verify YES-label verbiage under TG section
        final String ADD_INSURANCE          = "Yes, add insurance for";
        final String COVERING_ALL_CUSTOMERS = "covering all guests on this reservation.";
        final String READ_UNDERSTAND        = "I have read and understand the ";
        final String AGREE_TERMS            = " and agree to the terms of conditions.";

        ValidationUtil.validateTestStep("Yes, add insurance.. verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText(),ADD_INSURANCE);

        ValidationUtil.validateTestStep("covering all customers on this reservation... verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText(),COVERING_ALL_CUSTOMERS);

        ValidationUtil.validateTestStep("I have read and understand the .. verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getHavereadAndUnderstandText().getText(),READ_UNDERSTAND);

        ValidationUtil.validateTestStep("Agree to Terms and Condition .. verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getHavereadAndUnderstandText().getText(),AGREE_TERMS);
//STEP--10,11,12 & 13
        //verify No-label verbiage under TG section
        final String NO_INSURANCE                   = "No, I don't want to insure my";
        final String RESPONSIBLE_FOR_NONREFUNDABLE  = "trip and understand that I may be responsible for non-refundable charges.";
        final String COVERAGE_OFFERED               = "Coverage offered by Travel Guard Group, Inc. and limitations will apply. Full Disclaimer";

        ValidationUtil.validateTestStep("No, I don't want to insure my.. verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getNoTravelGuardLabel().getText(),NO_INSURANCE);

        ValidationUtil.validateTestStep("trip and understand that I am responsible.. verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getNoTravelGuardLabel().getText(),RESPONSIBLE_FOR_NONREFUNDABLE);

        ValidationUtil.validateTestStep("Coverage offered by Travel guard.. verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getCoverageOfferedText().getText(),COVERAGE_OFFERED);

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);

        pageObjectManager.getPaymentPage().getTermsAndConditionsLabel().click();
        pageObjectManager.getPaymentPage().getBookTripButton().click();

        //Validation of the travel guard pop-up
        pageMethodManager.getPaymentPageMethods().travelGuardRecommendedPopUp();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //**********************Validation to Confirmation Page************************
        //declare constant used in validation
        final String BOOKING_STATUS = "CONFIRMED";
        final String CONFIRMATION_URL = "book/confirmation";

        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(),BOOKING_STATUS.trim().toUpperCase());
    }
}