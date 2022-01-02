package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC280882
//Description: E2E_FSMC_RT DOM 1 ADT_SW Change Date, Bundle, Direct_Own Wheel Chair Completely Immobile_Continue Without Button_Bundle Seats_No Extras, CI Web_Mastercard
//Created By : Sunny Sok
//Created On : 22-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 23-MAY-2019
//**********************************************************************************************
public class TC280882 extends BaseClass{
    @Parameters({"platform"})
    @Test (groups = {"BookPath","OneWay","DomesticDomestic","Outside21Days","Adult","FSMasterCard","NewFlightSearch"
            ,"NonStop","BookIt","PassengerInfoSSR","NoBags","NoSeats","CheckInOptions","MasterCard"})
    public void E2E_FSMC_RT_DOM_1_ADT_SW_Change_Date_Bundle_Direct_Own_Wheel_Chair_Completely_Immobile_Continue_Without_Button_Bundle_Seats_No_Extras_CI_Web_Mastercard(@Optional("NA") String platform) {
        //**********************Navigate to Confirmation Page***************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280882 under SMOKE Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_ACCOUNT      = "FSMCEmail";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "NA";
        final String ADULT				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT	    = "0";

        //Flight Availability Page Constant Values
        final String NEW_DEP_DATE 	    = "30";
        final String DEP_FLIGHT 	    = "NonStop";
        final String FARE_TYPE		    = "Standard";
        final String UPGRADE_FARE	    = "BookIt";

        //Passenger info page Constant Values
        final String DISABILITY         = "WheelChairCompletelyImmobile";

        //Option Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "MasterCard";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().selectDates(NEW_DEP_DATE, ARR_DATE);
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(DISABILITY);
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Purchase Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //***********************Validation to Confirmation Page*************************/

        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //declare constant used in validation
        final String BOOKING_STATUS   = "Confirmed";
        final String CONFIRMATION_URL = "book/confirmation";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}