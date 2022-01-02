package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Bug ID: 23950 - DOB for 9DFC UMNR is not available on Passenger Info Page
//TODO: Bug 22314: MP: BP: PI: When booking with UMNR 9FC member w/Max UMNR guests, the DOBs are being assigned incorrectly preventing customer from continuing
//Test Case ID: TC280540
//Description: E2E_9DFC_RT DOM MAX PAX all UMNR_DirectFlight_Standard_ThrillBags_Included seats_NoExtras CI web_Res credit and credit card
//Created By : Kartik chauhan
//Created On : 06-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 06-May-2019
//**********************************************************************************************
public class TC280540 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Child" ,
            "NineDFC","NonStop" , "BundleIt" , "CarryOn" , "CheckedBags" , "Standard" , "FlightFlex" ,
            "ShortCutBoarding" , "MasterCard" , "ActiveBug" , "ReservationCredit"})
    public void E2E_9DFC_RT_DOM_MAX_PAX_all_UMNR_DirectFlight_Standard_ThrillBags_Included_seats_NoExtras_CI_web_Res_credit_and_credit_card(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280540 under SMOKE Suite on " +
                    platform + " Browser"   , true);
        }

        //Home Page Constant Values
        //Home Page Constant Values for Reservation Credit
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "UMNR9FC";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "BWI";
        final String DEP_DATE           = "2";
        final String ARR_DATE           = "5";
        final String ADULTS             = "0";
        final String CHILD              = "9";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BundleIt";

        //Bags Page Constant
        final String BUNDLE_ITINERARY   = "Bundle It Discount";
        final String CHECKED_BAG    	= "Included";

        //Seat Page Constant
        final String DEP_SEAT          = "Standard";

        //Payment Page Constant Values
        final String OPTION_VALUE      = "FlightFlex|ShortCutBoarding";
        final String CARD_TYPE         = "MasterCard";
        final String TRAVEL_GUARD      = "NotRequired";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //Reservation Summary Page Constant
        final String MYTRIP_BAGS_POPUP   = "DontPurchase";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

        //create resCredit
        createResCredit();

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //get UMNR FSMC info from json file
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("UMNR9FC");

        //Select specific date
        pageObjectManager.getHomePage().getChildPopUpBirthBoxes().get(0).sendKeys(passengerInfoData.dob);

        //Select Young Traveler pop-up
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //wait till UMNR popup appear
        WaitUtil.untilPageLoadComplete(getDriver());

        //accept UMNR popup
        pageObjectManager.getHomePage().getUMNRAcceptButton().click();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        //TODO: Bug 22314: MP: BP: PI: When booking with UMNR 9FC member w/Max UMNR guests, the DOBs are being assigned incorrectly preventing customer from continuing
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
//        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        /*********************************************Start OF Manage Travel**********************/
        //login to Manage Travel Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Selecting Seats page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        // Bag pop up. Do not purchase
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(MYTRIP_BAGS_POPUP);

        //Continue without extras
        pageObjectManager.getOptionsPage().getContinueToPurchaseButton().click();

        // Payment page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         ***********Validation on Manage Travel Reservation Summary Page***************
         ******************************************************************************/
        //reservation Summary URL
        final String RESERVATION_URL    = "my-trips/reservation-summary";

        //verify user on Reservation summary OPage
        ValidationUtil.validateTestStep("Verify user navigated to Manage Travel Reservation Summary Page,",
                getDriver().getCurrentUrl(),RESERVATION_URL);
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
        //Options Page Constant Values
//        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";
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
        //Options page methods
//        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
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