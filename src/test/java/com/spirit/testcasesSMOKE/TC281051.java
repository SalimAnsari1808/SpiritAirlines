package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC281059
//Title      : 31514 013. E2E_Guest_OW Dom 1 ADT 2 INFT (1 Lap) (1 Seat)_Thru Flight_No Bags_No Seats_No Extras CI Web_Voucher, Reservation Credit
//Description: Validate user can complete booking by using parameters from the title
//Created By : Alex Rodriguez
//Created On : 16-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-May-2019
//**********************************************************************************************
public class TC281051 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Adult" , "WithIn7Days" , "Adult" , "InfantSeat" ,
                    "InfantLap" , "Guest" , "Through" , "BookIt" , "NoBags" , "NoSeats" , "OptionalUI" , "Voucher" ,
                    "ReservationCredit" , "Visa"})
    public void  E2E_Guest_OW_Dom_1_ADT_2_INFT_1_Lap_1_Seat_Thru_Flight_No_Bags_No_Seats_No_Extras_CI_Web_Voucher_Reservation_Credit (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281051 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "DEN";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE2  = "FLL";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP2        = "1";
        final String INFANT_SEAT2       = "1";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT2        = "Through";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        /******************************************************************************
         *******************************Home Page Section********************************
         ******************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        /**Create voucher**/
        createVoucher();

        /**create resCredit**/
        createResCredit();

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE2);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT2, INFANT_LAP2);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT2);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(0).click();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page
        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of Lap Child on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*******************************************************************
        //***************************Payment Page****************************
        //*******************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().applyVoucherNumber();
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();

        //Select Book Trip button
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         *************************Validation on Confirmation Page**********************
         ******************************************************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
    private void createResCredit() {
        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";
        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_CARD        = "NotRequired";
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_CARD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());
    }
    private void createVoucher() {
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAX";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";
        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_CARD        = "NotRequired";
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_CARD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
    }
}