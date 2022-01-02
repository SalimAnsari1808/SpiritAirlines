package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.testng.annotations.*;

public class TC280856 extends BaseClass {
//**********************************************************************************************
//Test Case ID: TC280856
//Description: 31681 572. E2E_FSMC_OW DOM MAX PAX MIX_Direct Flight_No Bags_No Seats_No Extras Web CI_Reservation Credit
//Created By : Alex Rodriguez
//Created On : 21-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 24-Jun-2019
//**********************************************************************************************
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Child" , "FSMasterCard" , "BookIt" ,
                    "NonStop" , "NoBags" , "NoSeats" ,"ShortCutBoarding","CheckInOptions", "ReservationCredit"})
    public void E2E_FSMC_OW_DOM_MAX_PAX_MIX_Direct_Flight_No_Bags_No_Seats_No_Extras_Web_CI_Reservation_Credit(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280856 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE         = "English";
        final String LOGIN            = "FSMCEmail";
        final String JOURNEY_TYPE     = "Flight";
        final String TRIP_TYPE        = "OneWay";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE1= "FLL";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE1= "MCO";
        final String DEP_DATE         = "5";
        final String ARR_DATE         = "NA";
        final String ADULTS           = "5";
        final String CHILD            = "4";
        final String INFANT_LAP       = "0";
        final String INFANT_SEAT      = "0";


        //Flight Availability Page Constant Values
        final String DEP_FLIGHT       = "Nonstop";
        final String FARE_TYPE        = "Standard";
        final String UPGRADE_VALUE    = "BookIt";

        //Payment page Constant values
        final String TRAVEL_GUARD     = "NotRequired";
        final String CARD_TYPE        = "MasterCard";

        //Option Page Constant Values
        final String OPTIONS_VALUE    = "CheckInOption:MobileFree";

        //STEP--1
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN);

        createReservationCredit();

        //Create res credit and continue to the payment page
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE1, ARR_AIRPORTS, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //Use ResCredit and make partial payment via credit card
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageObjectManager.getPaymentPage().getTermsAndConditionsLabel().click();
//        pageObjectManager.getPaymentPage().getBookTripButton().click();
//        WaitUtil.untilPageLoadComplete(getDriver());
//        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //******************************************************************************
        //***********************Validation to Confirmation Page************************
        //******************************************************************************
        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //confirmation page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    }

    private void createReservationCredit(){

        //Home Page Constant Values
        final String JOURNEY_TYPE     = "Flight";
        final String TRIP_TYPE        = "OneWay";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE = "BWI";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE = "LAX";
        final String DEP_DATE         = "5";
        final String ARR_DATE         = "NA";
        final String ADULTS           = "3";
        final String CHILD            = "0";
        final String INFANT_LAP       = "0";
        final String INFANT_SEAT      = "0";


        //Flight Availability Page Constant Values
        final String DEP_FLIGHT       = "Nonstop";
        final String FARE_TYPE        = "Standard";
        final String UPGRADE_VALUE    = "BookIt";

        //Bags Page Constant values
        final String DEP_BAGS         = "Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5";

        //Payment page Constant values
        final String TRAVEL_GUARD     = "NotRequired";
        final String CARD_TYPE        = "MasterCard";

        //Option Page Constant Values
        final String OPTIONS_VALUE    = "CheckInOption:MobileFree";

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--2 & STEP--3
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare("Standard");

        //Seat Page Method
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Purchase Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //******************************************************************************
        //***********************Validation to Confirmation Page************************
        //******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //confirmation page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        WaitUtil.untilTimeCompleted(2000);

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
    }
}


