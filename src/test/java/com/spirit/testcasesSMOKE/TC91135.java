package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Check5
//Test Case ID: TC91135
//Test Name:   CP_INT_CI_Bag Modal_Add Bags
//Description:
//Created By : Sunny Sok
//Created On : 14-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 15-May-2019
//**********************************************************************************************
public class TC91135 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FreeSpirit" , "NonStop" ,"BookIt" ,
            "Standard" , "CheckInOptions" , "MasterCard","TravelInsurancePopUp" , "NoBags","AddEditSeats","ReservationUI"})
    public void CP_INT_CI_Bag_Modal_Add_Bags(@Optional("NA") String platform) {
        //******************************Navigate to CheckIn path************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91135 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE            = "English";
        final String LOGIN_EMAIL 		 = "FSEmail";
        final String JOURNEY_TYPE        = "Flight";
        final String TRIP_TYPE           = "RoundTrip";
        final String DEP_AIRPORTS        = "AllLocation";
        final String DEP_AIRPORT_CODE    = "FLL";
        final String ARR_AIRPORTS        = "AllLocation";
        final String ARR_AIRPORT_CODE    = "PTY";
        final String DEP_DATE            = "0";
        final String ARR_DATE            = "7";
        final String ADULT				 = "1";
        final String CHILD				 = "0";
        final String INFANT_LAP			 = "0";
        final String INFANT_SEAT		 = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "Nonstop";
        final String RET_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADEVALUE       = "BookIt";

        //Seat Page Constant
        final String DEP_SEAT           = "Standard|Standard";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
//        final String TRAVEL_GUARD       = "Required";

        //Confirmation Page Constant
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

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
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        System.out.println(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME));

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Check-In Path methods
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //************************Validation for Checkin path***************************/
        //declare constant used in validation
        final String EXTRAS_URL         = "check-in/extras";
        final String PASSPORT_URL       = "/check-in/travel-docs";
        final String BAGS_URL           = "check-in/bags";
        final String BAG_SEAT_BUY       = "Purchase";
        final String BAG_SEAT_DONT_BUY  = "DontPurchase";
        final String CAR_BUY            = "DontPurchase";


        WaitUtil.untilPageLoadComplete(getDriver());

        //Validating customer is taken to Passport page due is an INT flight
        ValidationUtil.validateTestStep("Verify user navigated to Passport page on CheckIn Path",
                getDriver().getCurrentUrl(),PASSPORT_URL);

        //Filling passport information
        pageMethodManager.getPassportPageMethods().fillPassportInformation();

        //verify for bags popup
        ValidationUtil.validateTestStep("Verify Bags pop up is displayed on CheckIn Path",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupBuyBagsNowButton()));

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(BAG_SEAT_DONT_BUY);

        WaitUtil.untilPageLoadComplete(getDriver());

//        //Choose NO for car due to live inventory
//        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup(BAG_SEAT_DONT_BUY);
//
//        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup(CAR_BUY);

        ValidationUtil.validateTestStep("Verify user navigated to Extras page on CheckIn Path",
                getDriver().getCurrentUrl(),EXTRAS_URL);


        //*******************************Restart Checkin path***************************/
        //Check-In Path methods
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        WaitUtil.untilPageLoadComplete(getDriver());

        //verify for bags popup
        ValidationUtil.validateTestStep("Verify Bags pop up is displayed on CheckIn Path",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupBuyBagsNowButton()));

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(BAG_SEAT_BUY);

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verify user navigated to bags page on CheckIn Path",
                getDriver().getCurrentUrl(),BAGS_URL);

    }
}
