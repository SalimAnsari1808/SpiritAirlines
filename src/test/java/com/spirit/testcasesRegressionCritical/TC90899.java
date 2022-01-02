package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//**********************************************************************************************
//Test Case ID: TC90899
//Description: CP_CI_Online Check In page full validation_UMNR
//Created By: Kartik chauhan
//Created On: 17-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-July-2019
//**********************************************************************************************
public class TC90899 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn","RoundTrip","DomesticDomestic","WithIn7Days","Child","Guest","Connecting","BookIt","NoBags"
                    ,"NoSeats","MasterCard","TravelInsurancePopUp","ReservationUI","BoardingPassUI"})
    public void CP_CI_Online_Check_In_page_full_validation_UMNR(@Optional("NA") String platform) {
        //***************************Navigate to Payment Page**************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90899 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "DFW";
        final String DEP_DATE               = "0";
        final String ARR_DATE               = "2";
        final String ADULTS                 = "0";
        final String CHILD                  = "1";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String ARR_Flight             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Payment Page Constant Values
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GUARD           = "Required";

        //Confirmation Page Constant value
        final String BOOKING_STATUS         = "Confirmed";

        //Bag Page constant values
        final String CHECKIN_BAG_PURCHASE   = "DontPurchase";

        //Seat Page constant values
        final String CHECKIN_SEAT_PURCHASE  = "DontPurchase";

        //Car Page constant values
        final String CHECKIN_CAR_PURCHASE   = "DontPurchase";

        //Boarding pass constant values
        final String CHECKIN_HAZMAT         = "Accept";
        final String CHECKIN_BP_PRINT       = "Print";
        final String CHECKIN_BP_EMAIL       = "NA";

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
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

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
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //no TG available within 24 hours
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//STEP--2
        //*********************************************Start OF Check-In Path**********************/
        //login to Check-In Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--3
        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify Sub Header-'Hey There! online...' of the Confirmation Page",
                pageObjectManager.getConfirmationPage().getConfirmationPageSubHeaderText().isDisplayed());

        ValidationUtil.validateTestStep("Verify Sub Header-'Buy Your Bags and Seat...' of the Confirmation Page",
                pageObjectManager.getConfirmationPage().getConfirmationPageSubHeaderBuyYourBagsText().isDisplayed());
//STEP--4
        //Click on Check-in and get Boarding pass button
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //do not select Bag
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECKIN_BAG_PURCHASE);

        //do not select Seat
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(CHECKIN_SEAT_PURCHASE);

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup(CHECKIN_CAR_PURCHASE);

//STEP--5
        //wait till 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//        //No TG
//        WaitUtil.untilPageLoadComplete(getDriver());
//        WaitUtil.untilTimeCompleted(2000);
//        pageObjectManager.getPaymentPage().getNoTravelGuardPopupLabel().click();
//        WaitUtil.untilTimeCompleted(1200);

        //***********************Validation to Hazmat Pop-up****************************/
        //validate Hazmat Pop-up is displaying
        ValidationUtil.validateTestStep("Hazmat Pop-up Header is displaying",
                pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupHeaderText().isDisplayed());

        //validate Hazmat Pop-up Sub-Header is displaying
        ValidationUtil.validateTestStep("Hazmat Pop-up Sub-Header is displaying",
                pageObjectManager.getReservationSummaryPage().getHazardousMaterialSubHeaderText().isDisplayed());

        //Accept and print boarding pass
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(CHECKIN_HAZMAT);
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(CHECKIN_BP_PRINT,CHECKIN_BP_EMAIL);

        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--6
        //**********************Validation on Boarding pass****************************/
        //declare constant used in Validation
        final String BOARDING_PASS_URL = "check-in/boarding-pass";

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //close print popup
        TestUtil.closeBoardingPassPrintPopup();

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
                getDriver().getCurrentUrl(),BOARDING_PASS_URL);

        ValidationUtil.validateTestStep("Verify 'This is not your Boarding Pass verbiage'",
                pageObjectManager.getBoardingPassPage().getNotBoardingPassText().get(0).isDisplayed());
        //****************************Belongs to SKYSPEED*************************
//STEP-7
    }
}



