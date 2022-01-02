package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280898
//Description: Task 24318: 31703 640. E2E_FSMC_RT INT MAX PAX MIX_SW Change Month Date, Bare, Thru_PAX3 Voluntary Emergency Services_Add Bags_Thrills Seats_No Extras, CI Web_Mastercard
//Created By: Gabriela
//Created On: 25-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 26-Jun-2019
//**********************************************************************************************

public class TC280898 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "ColombiaDomestic" , "Outside21Days" , "Adult" , "Child" , "FSMasterCard" ,
                     "NewFlightSearch" , "NonStop" , "BookIt" , "PassengerInfoSSR" , "CheckedBags" , "Bikes" , "SurfBoard" ,
                     "Standard" , "CheckInOptions" , "MasterCard"})
    public void E2E_FSMC_RT_INT_MAX_PAX_MIX_SW_Change_Month_Date_Bare_Thru_PAX3_Voluntary_Emergency_Services_Add_Bags_Thrills_Seats_No_Extras_CI_Web_Mastercard(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280898 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String LOGIN_ACCOUNT          = "FSMCEmail";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "EWR";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "CUN";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "8";
        final String ADULT                  = "4";
        final String CHILD                  = "5";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_DATE_1             = "35";
        final String RET_DATE_1             = "38";
        final String DEP_FLIGHT             = "through";
        final String RET_FLIGHT             = "through";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Seat Page Constant
        final String SEAT                   = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //Options page Constant Values
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS         = "Confirmed";
        final String CONFIRMATION_URL       = "book/confirmation";

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        //New Search
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1,RET_DATE_1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);//Bare Fare

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().get(2).click();
        pageObjectManager.getPassengerInfoPage().getVoluntaryProvisionofEmergencyServicesProgramListCheckBox().get(2).click();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilTimeCompleted(2000);
        //Selecting 4 bikes for pax 1
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(0).click();
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count < 4; count ++){
            pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(0).click();
        }

        //Selecting 4 Surf for pax 2
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(1).click();
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count < 4; count ++){
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(1).click();
        }

        //Selecting 4 checked bags for pax 3
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count < 4; count ++){
            pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(2).click();
        }

        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().travelGuardRecommendedPopUp();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         *************************Validation on Confirmation Page**********************
         ******************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}