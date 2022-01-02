package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
// **********************************************************************************************
// TestCase :TC91160
// Description: CP_CI_Print Boarding Pass steps
// Created By : Kartik Chauhan
// Created On : 08-April-2019
// Reviewed By: Salim Ansari
// Reviewed On: 10-April-2019
// **********************************************************************************************
public class TC91160 extends BaseClass {

    @Parameters ({"platform"})
    @Test (groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" ,
            "CarryOn" , "CheckedBags" , "CheckInOptions" , "TravelInsuranceBlock" , "MasterCard","ReservationUI"})
    public void CP_CI_Print_Boarding_Pass_steps (@Optional("NA")String platform){
        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC91160 under SMOKE suite on " + platform +" Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORTING_TYPE		= "Departure";
        final String DEP_FLIGHT			= "Connecting";
        final String FARE_TYPE			= "standard";
        final String UPGRADE_TYPE		= "BookIt";

        //Check In Bags Page constant values
        final String DEP_BAGS 	        = "Carry_1|Checked_5";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "Required";
        final String CARD_TYPE			= "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS 	= "Confirmed";

        //Check In Bags Page constant values
        final String CHECKIN_SEAT_POPUP	= "Required";

        //CheckIn Seat Page
        final String CHECKIN_DEP_SEATS	= "Standard||Standard";


//Step1
        //open browser
        openBrowser( platform);

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
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORTING_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);


        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(3000);

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        //pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));
//Step--2
        /*********************************************Start OF CheckIn Path**********************/
        //login to checkIn Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //*********************************Verify First Check-In and Get Boarding pass button(At top right)****
        //Top Right 'Check-In and Get Boarding Pass' Button constant
        final String CHECKINBOARDINGPASS_TOP_BUTTON	= "CHECK-IN AND GET";

        //To Right Check-In and Boarding Pass Button is displaying
        ValidationUtil.validateTestStep("Check-In and Boarding Pass button is displaying at the top right of the page", pageObjectManager.getReservationSummaryPage().getTopCheckInAndGetBoardingPassButton().getText().trim().toUpperCase(),CHECKINBOARDINGPASS_TOP_BUTTON);


//Step--3
        //Check-In and Boarding Pass Button is displaying
        ValidationUtil.validateTestStep("Check-In and Boarding Pass Button is displaying at the bottom center of the page",
                pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).isDisplayed());

        //Click on Check In Boarding Pass
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);
//Step--4
        //validate that Save on Bag pop-up is not displaying
        ValidationUtil.validateTestStep("Save on Bag pop-up is not displaying when user take max bags",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupNopeIAmGoodButton()));

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seat page during check-in path
        pageObjectManager.getReservationSummaryPage().getChooseYourSeatGetRandomSeatButton().click();

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(3000);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup("NotRequired");

        //validate Accept and Print Boarding Pass
        ValidationUtil.validateTestStep("Accept and Print Boarding Pass is displayed",  pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupAcceptBoardingPassButton().isDisplayed());

        //Make a click on Accept and Print Boarding Pass
        pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupAcceptBoardingPassButton().click();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);
//Step--5
        //print boarding pass
        pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(0).click();

        //create Web Element for finish Check-In button
        pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassButton().click();

        //wait till boarding pass appear
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait till 4 sec
        WaitUtil.untilTimeCompleted(4000);

        TestUtil.closeBoardingPassPrintPopup();

        WaitUtil.untilTimeCompleted(2000);

        //Validation to Confirmation Page
        //declare constant used in Validation
        final String BOARDING_PASS_URL = "check-in/boarding-pass";

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
                getDriver().getCurrentUrl(),BOARDING_PASS_URL);

    }

}

