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
//Test Case ID: TC90706
//Description: CP_CI_DOM_Travel Guard_U.S. Residents_within 24HRs_no TG
//Created By: Kartik chauhan
//Created On: 12-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 16-July-2019
//Comments: 01-07-2020 - Travel Guard no longer available within 24hrs, adding OutOfExecution tag
//**********************************************************************************************
public class TC90706 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" , "CarryOn" , "CheckedBags" , "Standard" ,"ShortCutBoarding", "CheckInOptions" ,"TravelInsurancePopUp", "MasterCard" , "PaymentUI","ReservationUI","BoardingPassUI","OutOfExecution"})
    public void CP_CI_DOM_Travel_Guard_US_Residents_within_24HRs_no_TG(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90706 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAS";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
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
        final String DEP_BAGS 			= "Carry_1|Checked_5";
        final String RET_BAGS			= "Carry_1|Checked_5";

        //Seat Page Constant
        final String DEP_SEAT           = "Standard|Standard||Standard|Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "Required";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //Boarding pass constant values
        final String CHECKIN_CAR_POPUP  = "DontPurchase";
        final String CHECKIN_HAZMET     = "Accept";
        final String CHECKIN_BP_PRINT   = "Print";
        final String CHECKIN_BP_EMAIL   = "NA";

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
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

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
        /*********************************************Start OF Check In**********************/
        //login to Manage Travel Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--4
        //Click on Check-in and get Boarding pass button
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //wait till 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--5
        //Verifying Bags pop-up is not displaying
        ValidationUtil.validateTestStep("Verify Bags pop-up is not displaying on Bags Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupNopeIAmGoodButton()));

        //Verifying Seat pop-up is not displaying
        ValidationUtil.validateTestStep("Verify Seats pop-up is not displaying on Bags Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSeatsPopupPurchaseMySeatsButton()));

        //pageObjectManager.getReservationSummaryPage().getRentACarNoThanksButton().click();
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup(CHECKIN_CAR_POPUP);

//        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //validate Travel guard is not displaying
        ValidationUtil.validateTestStep("Travel guard is not displaying during the Check-in path",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTravelGaurdPanel()));

//STEP--6
        /******************************************************************************
         ***********************Validation to Hazmat Pop-up****************************
         ******************************************************************************/
        //validate Hazmat Pop-up is displaying
        ValidationUtil.validateTestStep("Hazmat Pop-up Header is displaying",
                pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupHeaderText().isDisplayed());

        //validate Hazmat Pop-up Sub-Header is displaying
        ValidationUtil.validateTestStep("Hazmat Pop-up Sub-Header is displaying",
                pageObjectManager.getReservationSummaryPage().getHazardousMaterialSubHeaderText().isDisplayed());

        //Accept and print boarding pass
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(CHECKIN_HAZMET);
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(CHECKIN_BP_PRINT,CHECKIN_BP_EMAIL);

        WaitUtil.untilPageLoadComplete(getDriver());

        /******************************************************************************
         ***********************Validation on Boarding pass****************************
         ******************************************************************************/
        //declare constant used in Validation
        final String BOARDING_PASS_URL = "check-in/boarding-pass";

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //close print popup
        TestUtil.closeBoardingPassPrintPopup();

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
                getDriver().getCurrentUrl(),BOARDING_PASS_URL);
        //****************************Belongs to SKYSPEED*************************
//STEP-7
    }
}