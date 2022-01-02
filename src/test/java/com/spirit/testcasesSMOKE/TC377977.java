package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//BUG ID:  23950, As DOB is not displaying for 9DFC UMNR, on Customer info page
//TODO: Bug 22314: MP: BP: PI: When booking with UMNR 9FC member w/Max UMNR guests, the DOBs are being assigned incorrectly preventing customer from continuing
//Test Case ID: TC377977
//Title       : 437. E2E_9DFC_OW DOM MAX PAX ALL UMNR_SW Change Departing City Bundle It [Tier 3] Fare Direct Flight_STD_1CO 1CB_1 Free Seat_No Extras_CI Web_Reservation Credit_Credit Card
//Description :
//Created By  : Kartik Chauhan
//Created On  : 07-May-2019
//Reviewed By : Salim Ansari
//Reviewed On : 08-May-2019
//**********************************************************************************************

public class TC377977 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath","OneWay","DomesticDomestic","WithIn7Days","Child","NineDFC","NonStop","BundleIt","CarryOn",
                    "CheckedBags","Standard","FlightFlex","ShortCutBoarding","ReservationCredit","Visa","ActiveBug",
                    "TravelInsuranceBlock"})
    public void E2E_9DFC_OW_DOM_MAX_PAX_ALL_UMNR_SW_Change_Departing_City_Bundle_It_Tier_3_Fare_Direct_Flight_STD_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Reservation_Credit_Credit_Card(@Optional("NA") String platform) {

        //************Navigate to Confirmation Page to Cancel Reservation Page**********/
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC377977 under SMOKE Suite on " + platform + " Browser", true);
        }
        //Reservation Credit Path Constant variables
        final String LANGUAGE                           = "English";
        final String LOGIN_EMAIL 		                = "UMNR9FC";
        final String JOURNEY_TYPE                       = "Flight";
        final String TRIP_TYPE                          = "Oneway";
        final String DEP_AIRPORTS                       = "AllLocation";
        final String DEP_AIRPORT_CODE                   = "BOS";
        final String ARR_AIRPORTS                       = "AllLocation";
        final String ARR_AIRPORT_CODE                   = "FLL";
        final String DEP_DATE                           = "5";
        final String ARR_DATE                           = "NA";
        final String ADULTS                             = "0";
        final String CHILDREN                           = "9";
        final String INFANT_LAP                         = "0";
        final String INFANT_SEAT                        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                         = "NonStop";
        final String FARE_TYPE                          = "Member";
        final String UPGRADE_VALUE                      = "BundleIt";
        final String JOURNEY                            = "Dep";

        //Seats page constant value
        final String SEATS                              = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE                      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE1                         = "VisaCard";
        final String TRAVEL_GUARD                       = "Required";

        //confirmation page constant value
        final String CONFIRMATION_PAGE_URL              = "book/confirmation";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);

        //create resCredit
        createResCredit();
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Filling UMNR9FC DOB info
        WaitUtil.untilPageLoadComplete(getDriver());
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("UMNR9FC");
        pageObjectManager.getHomePage().getChildPopUpBirthBoxes().get(0).sendKeys(passengerInfoData.dob);

        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
//        WaitUtil.untilTimeCompleted(1200);

//        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        //TODO: Bug 22314: MP: BP: PI: When booking with UMNR 9FC member w/Max UMNR guests, the DOBs are being assigned incorrectly preventing customer from continuing
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 2

//Step 3
        //select yes on travel guard
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

//Step 4
//Step 5
//Step 6
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();


//Step 7
        //input CC information
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE1);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//Step 8
        //Validate the user is taken to the confirmation page
        ValidationUtil.validateTestStep("The user is correctly taken to the payment page" , getDriver().getCurrentUrl(),CONFIRMATION_PAGE_URL);

    }

    private void createResCredit() {
        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "5";
        final String ADULTS             = "0";
        final String CHILDREN           = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
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
        //Filling UMNR9FC DOB info
        WaitUtil.untilPageLoadComplete(getDriver());
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("UMNR9FC");
        pageObjectManager.getHomePage().getChildPopUpBirthBoxes().get(0).sendKeys(passengerInfoData.dob);
        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectUMNRPopup();
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //Options Page Methods
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
}