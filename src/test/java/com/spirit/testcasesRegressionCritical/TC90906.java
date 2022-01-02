package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC90906
//Description: CP_CI_Online Check In page full validation Add POC and Walker
//Created By:  Kartik chauhan
//Created On:  22-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-July-2019
//**********************************************************************************************
public class TC90906 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" , "AddEditSSR" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "TravelInsurancePopUp" , "ReservationUI","HazmatUI"})
    public void CP_CI_Online_Check_In_page_full_validation_Add_POC_and_Walker(@Optional("NA") String platform) {
        //****************************Navigate to Reservation Page**********************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90906 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
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
        final String ADULTS                 = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "Connecting";
        final String ARR_Flight             = "Connecting";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

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

        //Passenger Information Constant Variables
        final String SSR_VALUE              = "PortableOxygen|Other";

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

        //No TG within 24 hrs
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on CP
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//STEP--2 & STEP--3
        //*********************************************Start OF Checkin Path**********************/
        //login to Checkin Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--4
        //Verifying sub-header
        ValidationUtil.validateTestStep("'Hey there! online...' Sub header is displaying on Reservation summary page",
                pageObjectManager.getConfirmationPage().getConfirmationPageSubHeaderText().isDisplayed());

        //Verifying sub-header
        ValidationUtil.validateTestStep("'Buy your Bags and Seat..' Sub header is displaying on Reservation summary page",
                pageObjectManager.getConfirmationPage().getConfirmationPageSubHeaderBuyYourBagsText().isDisplayed());

//STEP--5,6 & STEP--7
        //Put a wait on Reservation summary page
        WaitUtil.untilTimeCompleted(2000);

        //method to select and verify SSR
        pageMethodManager.getReservationSummaryPageMethods().selectSSRPerPassenger(SSR_VALUE);

//STEP--8
        //Verifying sub-header
        ValidationUtil.validateTestStep("'CHECK-IN and PRINT BOARDING PASS' button is displaying on Reservation summary page",
                pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).isDisplayed());

        //Add SSR on Reservation page
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait till 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Click on Check-in and get Boarding pass button
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //wait till 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //do not select Bag
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECKIN_BAG_PURCHASE);

        //do not select Seat
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(CHECKIN_SEAT_PURCHASE);

        //handle car section
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup(CHECKIN_CAR_PURCHASE);

        //wait till 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //No TG
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPaymentPage().getNoTravelGuardPopupLabel().click();
        WaitUtil.untilTimeCompleted(1200);

        //*********************Validation to Hazmat Pop-up******************************/
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

        //************************Validation on Boarding pass*****************************/
        //declare constant used in Validation
        final String BOARDING_PASS_URL = "check-in/boarding-pass";

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //close print popup
        TestUtil.closeBoardingPassPrintPopup();

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
                getDriver().getCurrentUrl(),BOARDING_PASS_URL);
    }
}