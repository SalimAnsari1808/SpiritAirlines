package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//This test case is blocked due "Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web
/**10/21/19 test case passed, removed active bug tag**/
//return null names and are then invalid for use".
//Test Case ID: TC280782
//Test Name:  E2E_FS_RT_DOM_1_ADT_1_child_2_SW_Change_to_1_ADT_2_children_2_Bundle_It_Tier_3_Fare_Direct_Flight_STD_RT_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Voucher_Reservation_Credit_Credit_Card
//Description: Validating that a FS member with bundle option can pay with voucher and reservation credit successfully
//Created By : Gabriela
//Created On : 6-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 9-MAY-2019
//**********************************************************************************************

public class TC280782 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Child" , "Adult" ,
            "FreeSpirit" , "NonStop" , "BundleIt" , "PassengerInformationUI" , "CarryOn" , "CheckedBags" ,
            "BagsUI" , "Standard" , "SeatsUI" , "FlightFlex" , "ShortCutBoarding" , "CheckInOptions" ,
            "MasterCard" , "ReservationCredit" , "Voucher", "ActiveBug","FlightAvailabilityUI"})
    public void E2E_FS_RT_DOM_1_ADT_1_child_2_SW_Change_to_1_ADT_2_children_2_Bundle_It_Tier_3_Fare_Direct_Flight_STD_RT_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Voucher_Reservation_Credit_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280782 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE                           = "English";
        final String LOGIN_ACCOUNT                      = "FSEmail";
        final String JOURNEY_TYPE                       = "Flight";
        final String TRIP_TYPE1                         = "RoundTrip";
        final String DEP_AIRPORTS                       = "AllLocation";
        final String DEP_AIRPORT_CODE                   = "FLL";
        final String ARR_AIRPORTS                       = "AllLocation";
        final String ARR_AIRPORT_CODE2                  = "BWI";
        final String DEP_DATE                           = "5";
        final String ARR_DATE                           = "9";
        final String ADULTS                             = "1";
        final String CHILDREN                           = "2";
        final String INFANT_LAP                         = "0";
        final String INFANT_SEAT                        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                         = "NonStop";
        final String RET_FLIGHT                         = "NonStop";
        final String FARE_TYPE                          = "Standard";
        final String UPGRADE_VALUE1                     = "BundleIt";

        //Seats Page Constant Values
        final String DEP_SEATS			                = "Standard|Standard|Standard";
        final String RET_SEATS                          = "Standard|Standard|Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE                      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE                          = "MasterCard";
        final String TRAVEL_GAURD                       = "NotRequired";

        //confirmation page constant value
        final String BOOKING_STATUS                     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

        //create voucher
        createVoucher();

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());

        //create resCredit
        createResCredit();

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());

        //*******************************Test Case**************************************/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE1);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE2);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //*******************************************************************
        //************************Upgrade and Save Pop Up********************
        //*******************************************************************
        //Verifying Bundle saving is higher than Boost it
        WaitUtil.untilPageLoadComplete(getDriver());

        //Converting Bundle Saving from sting to double
        String BundleSaveUpto = pageObjectManager.getFlightAvailabilityPage().getBundleItSaveUpToPriceText().getText();
        String BundleSaveUptoSub = BundleSaveUpto.replace("Save Up To $", "");
        double BundleSaveDouble = Double.parseDouble(BundleSaveUptoSub);

        //Converting Bundle Saving from sting to double
        String BoostSaveUpto = pageObjectManager.getFlightAvailabilityPage().getBoostItSaveUpToPriceText().getText();
        String BoostSaveUptoSub = BoostSaveUpto.replace("Save Up To $", "");
        double BoostSaveDouble = Double.parseDouble(BoostSaveUptoSub);
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verifying Bundle saving is higher than Boost it",
                BundleSaveDouble > BoostSaveDouble);

        //Selecting Bundle It Options
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE1);

        //Passenger Information page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //Verifying Children DOB fields are present
        for (int count = 1; count < pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().size(); count++) {
            ValidationUtil.validateTestStep("Verifying children DOB is displayed by default",
                    !pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(count).getAttribute("value").isEmpty());
        }
        //Filling fields and continue
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        //Validating City Pair are right
        ValidationUtil.validateTestStep("Validating departure city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),(DEP_AIRPORT_CODE));

        ValidationUtil.validateTestStep("Validating arriving city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),(ARR_AIRPORT_CODE2));

        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE1);
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        //Selecting standard seats
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE1,DEP_SEATS,RET_SEATS);

        //Continue to Options page
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*******************************************************************
        //************************Payment Page*******************************
        //*******************************************************************

        pageMethodManager.getPaymentPageMethods().applyReservationCredit();
        pageMethodManager.getPaymentPageMethods().applyVoucherNumber();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);

        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
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
    private void createResCredit() {
        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "IAH";
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
}