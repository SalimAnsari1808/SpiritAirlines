package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC369496
//Test Name:   Flow_CP_CI_Flow for flight only yes to all
//Description:
////Created By : Sunny Sok
//Created On :   15-Apr-2019
//Reviewed By:   Salim Ansari
//Reviewed On:   17-Apr-2019
//**********************************************************************************************
public class TC369496 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FreeSpirit" , "NonStop" , "BookIt" ,
            "CarryOn" , "CheckedBags" , "Standard" ,"ShortCutBoarding", "CheckInOptions" , "MasterCard" , "TravelInsurancePopUp","ReservationUI"})
    public void Flow_CP_CI_Flow_for_flight_only_yes_to_all(@Optional("NA") String platform) {
        //*******************************Navigate to checkin path***********************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC369496 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_EMAIL 		= "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS				= "2";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADEVALUE       = "BookIt";

        //Seat Page Constant
        final String DEP_SEAT           = "Standard|Standard";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_1|Checked_5||Carry_1|Checked_5";

        //Confirmation Page Constant
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //Boarding Pass Constant
        final String HAZMET_POPUP       = "Accept";
        final String BOARDING_PASS      = "Print";

        //STEP--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl().contains(CONFIRMATION_URL));

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));

        //Check-In Path methods
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //************************Validation for Checkin path***************************/
        //declare constant used in validation
        final String EXTRAS_URL         = "check-in/extras";
        final String BOARDINGPASS_URL   = "check-in/boarding-pass";

        //verify for Bags popup
        ValidationUtil.validateTestStep("Verify Bags pop up is not displayed on CheckIn Path",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getBagsPopupPurchaseMyBagsButton()));

        //verify for Seats popup
        ValidationUtil.validateTestStep("Verify Seats pop up is not displayed on CheckIn Path",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSeatsPopupPurchaseMySeatsButton()));

        ValidationUtil.validateTestStep("Verify user navigated to Extras page on CheckIn Path",
                getDriver().getCurrentUrl().contains(EXTRAS_URL));

        pageObjectManager.getOptionsPage().getContinueToPurchaseButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //verify for Travel Guard popup //TODO: Invalid Step
//        ValidationUtil.validateTestStep("Verify Travel guard pop up is not displayed on CheckIn Path",
//                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getYesTravelGuardPopupLabel()));

        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //accept hazmet popup
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(HAZMET_POPUP);

        //print Boarding pass
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(BOARDING_PASS,"NA");

        //close Boarding Pass print popup
        TestUtil.closeBoardingPassPrintPopup();

        WaitUtil.untilPageLoadComplete(getDriver());

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify User is navigated to boarding pass page",
                getDriver().getCurrentUrl().contains(BOARDINGPASS_URL));
    }
}
