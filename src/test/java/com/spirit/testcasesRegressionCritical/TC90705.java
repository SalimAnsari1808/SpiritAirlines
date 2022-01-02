package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//BUG: Unable to click on Bags button after selecting Checked bag
//Test Case ID: TC90705
//Description: CP_CI_DOM_Travel Guard_U.S. Residents_within 24HRs
//Created By: Kartik chauhan
//Created On: 15-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-July-2019
//**********************************************************************************************
public class TC90705 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" , "CarryOn" , "CheckedBags" , "Standard" , "CheckInOptions" , "MasterCard" ,"TravelInsurancePopUp"})
    public void CP_CI_DOM_Travel_Guard_US_Residents_within_24HRs(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90705 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "2";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String ARR_Flight         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page constant values
        final String MT_DEP_BAGS 		= "Carry_1|Checked_2";

        //Reservation Page common methods
        final String MYTRIP_SEAT        = "Seats";

        //My Trip Common Methods
        final String MYTRIP_DEP_SEAT    = "Standard|Standard||Standard|Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

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

//        scenarioContext.setContext(Context.CONFIRMATION_CODE,"KBE3FZ");
//        scenarioContext.setContext(Context.CONFIRMATION_LASTNAME,"AUTOMATION");

//STEP--2 & STEP--3
        /*********************************************Start OF Check-In path**********************/
        //login to Manage Travel Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--4
        //Click on Check-in and get Boarding pass button
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //wait till 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--5
        //Verifying Bags pop-up is displaying
        ValidationUtil.validateTestStep("Verify Bags pop-up is displaying on Bags Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupNopeIAmGoodButton()));

        //Add Bags on CheckIn Path
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup("Purchase");
        pageMethodManager.getBagsPageMethods().selectDepartingBags(MT_DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Add Seat on CheckIn Path
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup("Purchase");
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(MYTRIP_DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //select no for car
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup("DontPurchase");

        //wait till 2 sec
        WaitUtil.untilTimeCompleted(2000);

        WaitUtil.untilPageLoadComplete(getDriver());

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //validate Travel guard is displaying
        ValidationUtil.validateTestStep("Travel guard is displaying during the Check-in path",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getYesTravelGuardPopupLabel()));

        System.out.println(pageObjectManager.getPaymentPage().getYesTravelGuardPopupLabel().getText());

        //validate Travel guard is displaying
//        ValidationUtil.validateTestStep("Travel guard is displaying with Travel Guard Amount as $14.99",
//                pageObjectManager.getPaymentPage().getYesTravelGuardPopupLabel().getText(),"$14.99");

        //Select TG
        pageObjectManager.getPaymentPage().getYesTravelGuardPopupLabel().click();
        //fill payment details
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        //Accept booking
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //*******************************RETURN TO HOME PAGE error*********************************************

//STEP--6
        /******************************************************************************
         ****************Validation to Reservation Summary Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String CONFIRMATION_URL   = "check-in/reservation-summary";

        //Close the HAZMAT  POP-UP
        pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupRejectBoardingPassButton().click();

//        //confirmation page Methods
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //****************************Belongs to SKYSPEED*************************
//STEP-7
    }
}