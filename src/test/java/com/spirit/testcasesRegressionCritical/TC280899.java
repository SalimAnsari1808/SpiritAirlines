package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280899
//Description: Task 24319: 31704 641. E2E_FSMC_RT DOM MAX PAX ALL UMNR_SW Change Depart City, Bare, Direct_PAX2 Vision Impaired PAX4 Service Animal_Thrills Bags_Thrills Seats_No Extras, CI Web_Mastercard
//Created By: Gabriela
//Created On: 25-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 26-Jun-2019
//**********************************************************************************************

public class TC280899 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Within21Days" , "Child" , "FSMasterCard" , "NewFlightSearch" ,
                     "NonStop" , "BundleIt" , "PassengerInfoSSR" , "CheckedBags" , "CarryOn" , "Standard" , "FlightFlex" ,
                     "ShortCutBoarding" , "MasterCard","OptionalUI"})
    public void E2E_FSMC_RT_DOM_MAX_PAX_ALL_UMNR_SW_Change_Depart_City_Bare_Direct_PAX2_Vision_Impaired_PAX4_Service_Animal_Thrills_Bags_Thrills_Seats_No_Extras_CI_Web_Mastercard(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280899 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String LOGIN_ACCOUNT          = "FreeSpiritMasterCardUMNR";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "LAS";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "FLL";
        final String DEP_DATE               = "8";
        final String ARR_DATE               = "12";
        final String ADULT                  = "0";
        final String CHILD                  = "9";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_AIRPORT_CODE_1     = "DEN";
        final String DEP_FLIGHT             = "NonStop";
        final String RET_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BundleIt";

        //Passenger Information Constant Values
        final String SSR                    = "NotRequired||VisionDisability||NotRequired||ServiceAnimal";

        //Seat Page Constant
        final String SEAT                   = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //Payment Page Constant Values
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS         = "Confirmed";
        final String CONFIRMATION_URL       = "book/confirmation";

        //open browser and redirect tot the application
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
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

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE_1, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillPassengerDOB(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);//Bundle Fare

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);
//        pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().get(1).click();
//        WaitUtil.untilTimeCompleted(1200);
//        pageObjectManager.getPassengerInfoPage().getVisionDisabilityListCheckBox().get(1).click();
//
//        WaitUtil.untilTimeCompleted(2000);
//        pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().get(3).click();
//        WaitUtil.untilTimeCompleted(1200);
//        pageObjectManager.getPassengerInfoPage().getServiceAnimalListCheckBox().get(3).click();

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,SEAT,SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/

//        ValidationUtil.validateTestStep("Validating no check in option for UNMR",
//                !pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown().isEnabled());
        ValidationUtil.validateTestStep("Verify Check-In Option is disabled on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADE_VALUE);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().travelGuardRecommendedPopUp();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         *************************Validation on Confirmation Page**********************
         ******************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().verifySelectedBaseFareConfirmation(UPGRADE_VALUE);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}