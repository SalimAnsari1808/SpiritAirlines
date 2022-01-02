package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

/*Unable to run on Macs due System User directory function on UNIX machine*/
//**********************************************************************************************
//Test Case ID: TC90860
//Test Case ID: TC90860
//Test Case Name: Task 24841: 35162 Misc_CP_MT_SinglePAX _Add trip to calendar is working correctly
//Created By: Gabriela
//Created On: 02-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-Aug-2019
//**********************************************************************************************

public class TC90860 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" ,"Guest", "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "Visa" , "ReservationUI"})
    public void Misc_CP_MT_SinglePAX_Add_trip_to_calendar_is_working_correctly(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90860 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
// Step 1: Prerequisite book a flight with any number of PAX. Then proceed to the reservation summary page
//Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE 	        = "OneWay";
        final String DEP_AIRPORTS 	    = "AllLocation";
        final String DEP_AIRPORT_CODE   = "DFW";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE 	= "DEN";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULTS	            = "1";
        final String CHILDREN 	        = "0";
        final String INFANT_LAP	        = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Reservation Summary Page Constant Values
        final String RESERVATION_SUMMARY_URL= "/my-trips/reservation-summary";

        //open browser
        openBrowser( platform);

        /***********************Home Page Methods********************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /***********************Flight Availability Methods********************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /***********************Passenger Information Methods********************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /***********************Bags Page Methods********************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /***********************Seats Page Methods********************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /***********************Options Page Methods********************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /***********************Payment Page Methods********************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /***********************************Confirmation Page Method**************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        pageMethodManager.getHomePageMethods().loginToMyTrip();

// Step 2: Verify you land on reservation summary page
        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify Reservation Summary Page load Page",
                getDriver().getCurrentUrl(),RESERVATION_SUMMARY_URL);

//-- Step 3: Click the Add Trip to calendar link
        pageObjectManager.getReservationSummaryPage().getAddTripToCalendarButton().click();
        WaitUtil.untilTimeCompleted(3000);

// Step 4: Select open with outlook and click okay
        //Not able to open Outlook in this script

//-- Step 5: Go to your outlook and verify that trip was added to your calender
        //Not able to open Outlook in this script

// Validating event was download in calendar format
        ValidationUtil.validateTestStep("The file is downloaded" , TestUtil.verifyFileDownload("spirit","ics"));
    }
}