package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//Test Case ID: PRODTC91080
//Description: CP_BP_Payment Page_Travel Guard_Non Approved TRM State
//Created By: Kartik chauhan
//Created On: 17-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-July-2019
//**********************************************************************************************
public class PRODTC91080 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups="Production")

    public void CP_BP_Payment_Page_Travel_Guard_Non_Approved_TRM_State(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC91080 under PRODUCTION Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "12";
        final String ARR_DATE           = "15";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String ARR_Flight         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //open browser
        openBrowser(platform);
//STEP--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getContactPersonStateDropDown(),"Pennsylvania");
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//STEP--2
        //Payment Page
        //validate that travel guard is not available
        ValidationUtil.validateTestStep("Travel Guard Section is displayed on payment page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getYesTravelGuardLabel()));
//STEP--3
        //Validating Travel Insurance information
        List<String> textBeingValidated = new ArrayList<String>();
        textBeingValidated.add("100% Trip Cost Cancellation");
        textBeingValidated.add("125% Trip Cost Trip Interruption");
        textBeingValidated.add("$500 Missed Connection");
        textBeingValidated.add("$500 Trip Delay");
        textBeingValidated.add("$500 Baggage & Personal Effects");

        //loop through all Suppressed Taxes
        for(int taxCounter=0;taxCounter<textBeingValidated.size();taxCounter++) {
            //check checked bag bag prices for all passenger
            if(!pageObjectManager.getPaymentPage().getTravelGaurdPanel().getText().trim().contains(textBeingValidated.get(taxCounter))) {
                //validate that travel guard is not available
                ValidationUtil.validateTestStep("Travel Guard Text " + textBeingValidated.get(taxCounter) + ",is not displaying on Payment Page",true);
            }else {
                ValidationUtil.validateTestStep("Travel Guard Text " + textBeingValidated.get(taxCounter) + ",is displaying on Payment Page",false);
            }
        }

    }
}