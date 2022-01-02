package com.spirit.testcasesRegressionCritical;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

// **********************************************************************************************
// TestCase :   TC91174
// Description: CP_CI_Car Modal_Reserve a car_INT-DOM
// Created By : Salim Ansari
// Created On : 31-July-2019
// Reviewed By: Kartik Chauhan
// Reviewed On: 31-July-2019
// **********************************************************************************************
public class TC91174 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"CarsUI", "CheckIn", "RoundTrip", "InternationalDomestic", "WithIn7Days", "Adult","Guest", "Connecting", "NoBags","NoSeats","CheckInOptions","TravelInsuranceBlock", "MasterCard","ReservationUI","BoardingPassUI"})

    public void CP_CI_Car_Modal_Reserve_a_car_INT_DOM (@Optional("NA")String platform){
        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC91174 under REGRESSION-CRITICAL suite on " + platform +" Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "CUN";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "4";
        final String ADULTS				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORTING_TYPE		= "Departure";
        final String DEP_FLIGHT			= "Connecting";
        final String ARR_Flight			= "Connecting";
        final String FARE_TYPE			= "standard";
        final String UPGRADE_TYPE		= "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "Required";
        final String CARD_TYPE			= "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS 	= "Confirmed";

        //Reservation Summary page constant
        final String BAGS_POPUP         = "DontPurchase";
        final String SEATS_POPUP        = "DontPurchase";
        final String CAR_POPUP          = "Purchase";
        final String HAZMAT_POPUP       = "Accept";
        final String PRINT_POPUP        = "Print";
        final String EMAIL_POPUP        = "NA";

//Step1
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE,ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT,INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORTING_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
       // pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));

        //login to checkIn Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Check-In and Boarding Pass Button is displaying
        ValidationUtil.validateTestStep("Check-In and Boarding Pass Button is displaying at the bottom center of the page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton()));

        //Reservation Summary Methods
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
        pageMethodManager.getPassportPageMethods().fillPassportInformation();
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(BAGS_POPUP);
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(SEATS_POPUP);

        //Validation On Rental Car Popup
        //verify Rental Car popup will appear
        ValidationUtil.validateTestStep("Verify Buy a Rental Car popup appear on Check-In path",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getRentACarSelectCarButton()));

        //Navigate to check-In Car Page
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup(CAR_POPUP);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Store Car information
        scenarioContext.setContext(Context.CAR_NAME,pageObjectManager.getCarPage().getCheckInCarNameText().get(0).getText());
        scenarioContext.setContext(Context.CAR_PRICE,pageObjectManager.getCarPage().getCheckInCarPriceText().get(0).getText());

        //select car on check-in path
        pageObjectManager.getCarPage().getCheckInCarsPageBookCarButton().get(0).click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 3 sec
        WaitUtil.untilTimeCompleted(3000);

        //scrool to option section
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getPaymentPage().getOptionstHeaderText());

        WaitUtil.untilTimeCompleted(1000);

        //Get snapshot of booked Car
        try{
            String base64Screenshot = TestUtil.getBase64Screenshot(getDriver());
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build();
            getExtText().fail(" Failed ScreenShot",mediaModel);
        }catch(IOException ex){

        }

        //send mail to recipent
        EmailUtil.sendEmailForVacationBooking(TestUtil.getLatestFailedSnapshotFile(),"Car");

        //accept terms and condition for car
        pageObjectManager.getPaymentPage().getTermsAndConditionsHertzLabel().click();

        //aacept terms and conditions
        pageObjectManager.getPaymentPage().getTermsAndConditionsLabel().click();

        //continue to confirmation page
        pageObjectManager.getPaymentPage().getContunueButton().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Boarding Pass Methods
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(HAZMAT_POPUP);
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(PRINT_POPUP,EMAIL_POPUP);

        //wait till boarding pass appear
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait till 4 sec
        WaitUtil.untilTimeCompleted(4000);

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user verify This is your boarding pass verbiage on  Boarding Pass Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getYourBoardingPassText()));

    }
}
