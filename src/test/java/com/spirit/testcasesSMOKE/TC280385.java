package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web return null names and are then invalid for use
//Test Case ID: TC280385
//Test Name: E2E_9DFC_OW_DOM_1_ADT_1_child_2_SW_Change_to_1_ADT_2_children_2_Bundle_It_Tier_3_Fare_Direct_Flight_STD_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Voucher_Reservation_Credit_Credit_Card
//Description: Validation 9FC member traveling with 2 children can select bundle options and pay with reservation credit and voucher
//Created By : Gabriela
//Created On : 7-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 8-MAY-2019
//**********************************************************************************************

public class TC280385 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Child" ,
            "NineDFC" , "NewFlightSearch" , "NonStop" , "BundleIt" , "PassengerInformationUI","CarryOn",
            "CheckedBags" , "BagsUI" , "Standard","SeatsUI" , "FlightFlex" , "ShortCutBoarding" , "CheckInOptions" ,
            "MasterCard" , "Voucher" , "ReservationCredit" , "ActiveBug","ConfirmationUI"})
    public void E2E_9DFC_OW_DOM_1_ADT_1_child_2_SW_Change_to_1_ADT_2_children_2_Bundle_It_Tier_3_Fare_Direct_Flight_STD_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Voucher_Reservation_Credit_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280385 under SMOKE Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE                           = "English";
        final String LOGIN_ACCOUNT                      = "NineDFCEmail";
        final String JOURNEY_TYPE                       = "Flight";
        final String TRIP_TYPE                          = "Oneway";
        final String DEP_AIRPORTS                       = "AllLocation";
        final String DEP_AIRPORT_CODE                   = "FLL";
        final String ARR_AIRPORTS                       = "AllLocation";
        final String ARR_AIRPORT_CODE2                  = "BWI";
        final String DEP_DATE                           = "5";
        final String ARR_DATE                           = "NA";
        final String ADULTS                             = "1";
        final String CHILD1                             = "1";
        final String CHILDREN                           = "2";
        final String INFANT_LAP                         = "0";
        final String INFANT_SEAT                        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                         = "NonStop";
        final String FARE_TYPE                          = "Member";
        final String UPGRADE_VALUE                      = "BundleIt";

        //Seats Page Constant Values
        final String DEP_SEATS			                = "Standard|Standard|Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE                      = "CheckInOption:MobileFree";
        final String OPTIONS_VAL                        = "FlightFlex|ShortCutBoarding";

        //payment page constant value
        final String CARD_TYPE                          = "MasterCard";
        final String TRAVEL_GAURD                       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS                     = "Confirmed";
        final String CONFIRMATION_URL                   = "book/confirmation";

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

        //create reservation credit
        createReservationCredit();
        createVoucher();

        /******************************************************************************
         *******************************Test Case**************************************
         ******************************************************************************/
        //Home Page Methods
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE2);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD1, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();


        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        //New Search button is selected and passengers changes
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Selecting Booking journey
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        //Verifying Children DOB fields are present
        for (int i = 1; i < pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().size(); i++)
        {
            ValidationUtil.validateTestStep("Verifying children DOB is displayed by default",
                    !pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(i).getAttribute("value").isEmpty());
        }

        //Filling fields and continue
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //*******************************************************************
        //*************************Bags Page Methods************************
        //*******************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validating City Pair are right
        ValidationUtil.validateTestStep("Validating departure city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText().contains(DEP_AIRPORT_CODE));

        ValidationUtil.validateTestStep("Validating arriving city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText().contains(ARR_AIRPORT_CODE2));

        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);//Bundle Fare

        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();
        //*******************************************************************
        //*************************Seats Page Methods************************
        //*******************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());

        //Selecting Standard Seats
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);

        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,DEP_SEATS,"");//Bare Fare

        //Continue to Options page
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //*******************************************************************
        //***********************Options Page Methods************************
        //*******************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*******************************************************************
        //***********************Payment Page Methods************************
        //******************************************************************/
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();

        //TODO: Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web return null names and are then invalid for use
        pageMethodManager.getPaymentPageMethods().applyVoucherNumber();

        //enter credit card cvv number
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //Validating Options Selected
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(OPTIONS_VAL);
    }

    private void createReservationCredit(){
        //Home Page Constant Values
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE  		= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "LGA";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "3";
        final String ARR_DATE   		= "NA";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE_1    = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_2";

        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";

        /*********************************************************************************************************
         * ***********************************RESCREDIT SECTION***************************************************
         *********************************************************************************************************/
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE_1);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Seats page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        //My Trips Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilPageLoadComplete(getDriver());
    }

    private void createVoucher(){
        //Home Page Constant Values
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE  		= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "LAS";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAX";
        final String DEP_DATE 			= "3";
        final String ARR_DATE   		= "NA";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE_1    = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";

        /*********************************************************************************************************
         * *************************************VOUCHER SECTION***************************************************
         *********************************************************************************************************/
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE_1);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        //My Trips Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}