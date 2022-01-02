package com.spirit.testcasesRegressionCritical;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//**********************************************************************************************
//TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
/**10/21/19 test case passed, removed active bug tag**/

//Test Case ID: TC280413
//Test Name: Task 23138: 31431 447. E2E_9DFC_MultiCity INT 1 ADT 1 CHILD -5 MILES_SW Change Airports Direct Flight_Help To From Seat_1CO 5CB 1CO_Any_No Extras_CI Web_Credit Card
// Created By: Gabriela
//Created On : 06-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-Aug-2019
//**************************************************************************************************
public class TC280413 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "MultiCity" , "Miles" , "DomesticInternational" , "InternationalDomestic" , "WithIn7Days" , "Adult" ,
                     "Child" , "FSMasterCard" , "NewFlightSearch" , "NonStop" , "BookIt" , "PassengerInfoSSR" , "CarryOn" , "CheckedBags" ,
                     "Standard" , "ShortCutBoarding" , "CheckInOptions" ,  "MasterCard"})
    public void E2E_9DFC_MultiCity_INT_1_ADT_1_CHILD_5_MILES_SW_Change_Airports_Direct_Flight_Help_To_From_Seat_1CO_5CB_1CO_Any_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280413 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String LOGIN_ACCOUNT      = "FSMCEmail";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "MCO|MGA";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "KIN|MCO";
        final String TRAVEL_DATE        = "3|5";
        final String ADULT              = "1";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        //Flight Availability Page Constant Values
        final String DEP_AIRPORT_CODE_1 = "FLL|MGA";
        final String ARR_AIRPORT_CODE_1 = "KIN|FLL";
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        //Passenger Information Page Constant Values
        final String SSR                = "WheelChairNeedFromSeat||NotRequired";
        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_5||Carry_1|Checked_0";
        //Seats Page Constant Values
        final String DEP_SEATS          = "Standard|Standard";
        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";
        //Payment Page Constant Values
        final String CARD_TYPE         = "MasterCard";
        final String TRAVEL_GUARD      = "NotRequired";
        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";
        //open browser
        openBrowser(platform);
        /********************** Home Page Methods ********************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(TRAVEL_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //Filling infant information
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        /********************** Flight Availability Page Methods ********************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //Selecting new city pair
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE_1, ARR_AIRPORTS, ARR_AIRPORT_CODE_1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //Filling infant information
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        //Selecting miles flights
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        //TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        /********************** Passenger Information Page Methods ********************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        /***************************** Bags Page Methods *****************************************/
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
        /***************************** Seats Page Methods *****************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();
        /***************************** Options Page Methods *****************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        /***************************** Payment Page Methods *****************************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        /***************************** Confirmation Page Methods *****************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl().contains(CONFIRMATION_URL));
        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}