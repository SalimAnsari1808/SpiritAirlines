package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//BUG: Unable to click on Bags button after selecting Checked bag
//Test Case ID: TC90710
//Description: CP_MT_DOM_U.S. Residents_outside 24HRs_no TG
//Created By: Kartik chauhan
//Created On: 12-July-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC90710 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" ,"WithIn21Days", "Adult" , "Guest" , "Connecting" , "BookIt" , "CarryOn" , "CheckedBags" , "BigFrontSeat" ,"ShortCutBoarding", "CheckInOptions" ,"TravelInsurancePopUp", "MasterCard" ,"AddEditSeats", "PaymentUI"})
    public void CP_MT_DOM_US_Residents_outside_24HRs_no_TG(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90710 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "8";
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

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "Required";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //Reservation Summary Seat Section
        final String MYTRIP_SEAT        = "Seats";

        //Seats Page BFS
        final String MYTRIP_BFS_DEP     = "BigFront|BigFront||BigFront|BigFront";

        //Reservation Summary Page Constant
        final String MYTRIP_BAGS_POPUP  = "DontPurchase";

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
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//STEP--2 & TSEP--3
        /*********************************************Start OF Manage Travel**********************/
        //login to Manage Travel Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--4
        // Selecting BFS
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(MYTRIP_BFS_DEP);

        //Calculate size
        int flightsize = pageObjectManager.getSeatsPage().getPassengerDetailListLink().size();

        //create list array
        List<String> seatNumberList = new ArrayList<>();
        //loop through all legs details stored on FA page
        for(int legCounter=0;legCounter<flightsize;legCounter++) {
            pageObjectManager.getSeatsPage().getPassengerDetailListLink().get(legCounter).click();

            //store seat in array
            seatNumberList.add(pageObjectManager.getSeatsPage().getPassengerSeatText().get(legCounter).getText().trim());
        }

        pageMethodManager.getSeatsPageMethods().continueWithSeats();
//STEP--5
        // Bag pop up. Do not purchase
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(MYTRIP_BAGS_POPUP);

        //Continue without extras
        pageObjectManager.getOptionsPage().getContinueToPurchaseButton().click();

        //wait till page load
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--6
        // Payment page
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--7
        //validate Travel guard is not displaying
        ValidationUtil.validateTestStep("Travel guard is not displaying during the Check-in path",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTravelGaurdPanel()));

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//STEP--8
        /******************************************************************************
         ***********Validation on Manage Travel Reservation Summary Page***************
         ******************************************************************************/
        //reservation Summary URL
        final String RESERVATION_URL    = "my-trips/reservation-summary";

        //verify user on Reservation summary Page
        ValidationUtil.validateTestStep("Verify user navigated to Manage Travel Reservation Summary Page,",
                getDriver().getCurrentUrl(),RESERVATION_URL);

        //loop through all legs details stored on FA page
        for(int legCounter=0;legCounter<flightsize;legCounter++) {

            //verify Correct Selected seat are displaying
            ValidationUtil.validateTestStep("Correct Selected seat are displaying on Confirmation page",
                    pageObjectManager.getPaymentPage().getPassengerSectionSeatFlightSeatNumberText().get(legCounter).getText().contains(seatNumberList.get(legCounter)));
        }
        //****************************Belongs to SKYSPEED*************************
//STEP-9
    }
}