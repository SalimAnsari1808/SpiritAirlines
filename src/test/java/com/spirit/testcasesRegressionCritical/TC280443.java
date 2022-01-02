package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO 22314 - MP: BP: PI: When booking with UMNR 9FC member w/Max UMNR guests, the DOBs are being assigned incorrectly preventing customer from continuing
//Test Case ID: TC280443
//Description: Task 24243: 31642 406. E2E_9DFC_OW DOM_UMNR MAX PAX_Bundle_Direct_STD_1 CO_Any Free_NoExt_CI Web_Credit Card
// Created By : Gabriela
//Created On : 10-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Jun-2019
//**********************************************************************************************

public class TC280443 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Child" , "NineDFC" , "NonStop" , "BundleIt" ,
                     "PassengerInformationUI", "CarryOn" , "CheckedBags" ,"BagsUI", "Standard" ,"SeatsUI", "FlightFlex" ,
                     "ShortCutBoarding" , "OptionalUI" , "MasterCard" ,"PaymentUI", "ActiveBug","ConfirmationUI"})
    public void E2E_9DFC_OW_DOM_UMNR_MAX_PAX_Bundle_Direct_STD_1_CO_Any_Free_NoExt_CI_Web_Credit_Card(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280443 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "UMNR9FC";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "DEN";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "5";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "0";
        final String CHILD  			= "9";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Seats Page Constant Values
        final String SEAT               = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
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
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);
        //TODO 22314 - MP: BP: PI: When booking with UMNR 9FC member w/Max UMNR guests, the DOBs are being assigned incorrectly preventing customer from continuing
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        //Continue without bags
        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,SEAT,"NA");
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
//        ValidationUtil.validateTestStep("Validating no check in options available for UNMR",
//                !pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown().isEnabled());
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of emotional support animal Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");
        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADE_VALUE);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/
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