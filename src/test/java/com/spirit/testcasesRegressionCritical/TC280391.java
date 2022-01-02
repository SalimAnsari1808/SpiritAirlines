package com.spirit.testcasesRegressionCritical;

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
//TODO 22314 - MP: BP: PI: When booking with UMNR 9FC member w/Max UMNR guests, the DOBs are being assigned incorrectly preventing customer from continuing
//Test Case ID: TC280391
//Description: 437. E2E_9DFC_OW DOM MAX PAX ALL UMNR_SW Change Departing City Bundle It [Tier 3] Direct Flight_STD_1CO 1CB_1 Free Seat_No Extras_CI Web_Reservation Credit_Credit Card
//Created By : Anthony Cardona
//Created On : 11-Jun-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 11-Jun-2019
//**********************************************************************************************

public class TC280391 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Child" , "NineDFC" , "NewFlightSearch" , "NonStop" ,
                     "BundleIt" ,"PassengerInformationUI", "CarryOn" , "CheckedBags" ,"BagsUI", "Standard" ,"SeatsUI", "FlightFlex" ,
                     "ShortCutBoarding" , "OptionalUI" , "ReservationCredit" , "MasterCard" , "ActiveBug","PaymentUI","ConfirmationUI"})
    public void E2E_9DFC_OW_DOM_MAX_PAX_ALL_UMNR_SW_Change_Departing_City_Bundle_It_Tier_3_Direct_Flight_STD_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Reservation_Credit_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280391 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "UMNR9FC";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "MCO";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "DEN";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "0";
        final String CHILD  			= "9";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String NEW_DEP_AIRPORT_CODE= "FLL";
        final String DEP_FLIGHT 		= "Nonstop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Seat Page Constant
        final String DEP_SEAT           = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //Payment Page Constant Values
        final String TRAVEL_GUARD       = "NotRequired";
        final String CARD_TYPE          = "MasterCard";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
        openBrowser(platform);
        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        createResCredit();

        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillPassengerDOB(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, NEW_DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillPassengerDOB(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        //TODO 22314 - MP: BP: PI: When booking with UMNR 9FC member w/Max UMNR guests, the DOBs are being assigned incorrectly preventing customer from continuing
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,DEP_SEAT,"NA");//Bare Fare
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);//Bare Fare

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of UMNR on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /***************************************************************************
         **********************Payment Page Methods*********************************
         ***************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifySelectedBaseFareConfirmation(UPGRADE_VALUE);//Bare Fare
    }

    private void createResCredit() {
        //Booking Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "5";
        final String ARR_DATE 			= "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "Notrequired";

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
        WaitUtil.untilTimeCompleted(1200);
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("UMNR9FC");
        pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).sendKeys(passengerInfoData.title);

        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);

        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);
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
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        //MyTrip Page
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}