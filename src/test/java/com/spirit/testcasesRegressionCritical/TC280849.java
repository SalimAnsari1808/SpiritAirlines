package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

public class TC280849 extends BaseClass {
//**********************************************************************************************
//Test Case ID: TC280849
//Description: E2E_FSMC_OW DOM 1 ADT 1 INFT_Direct Flight_Military_1,2 Bags_No Seats_No Extras Web CI_Visa
//Created By : Alex Rodriguez
//Created On : 21-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 24-Jun-2019
//**********************************************************************************************
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "InfantLap" , "NonStop" , "FSMasterCard" ,
                    "Military" , "BookIt" , "CarryOn" , "CheckedBags" ,"NoSeats" ,"ShortCutBoarding", "Visa" , "TravelInsurancePopUp" ,
                    "OptionalUI" , "PaymentUI"})
    public void E2E_FSMC_OW_DOM_1_ADT_1_INFT_Direct_Flight_Military_1_2_Bags_No_Seats_No_Extras_Web_CI_Visa(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280849 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE         = "English";
        final String JOURNEY_TYPE     = "Flight";
        final String LOGIN_ACCOUNT    = "MilitaryFreeSpiritMasterCard";
        final String TRIP_TYPE        = "OneWay";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE = "LGA";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE = "FLL";
        final String DEP_DATE         = "3";
        final String ARR_DATE         = "NA";
        final String ADULT            = "1";
        final String CHILD            = "0";
        final String INFANT_LAP       = "1";
        final String INFANT_SEAT      = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT       = "Nonstop";
        final String FARE_TYPE        = "Standard";
        final String UPGRADE_VALUE    = "BookIt";

        //Payment Page Constant Values
        final String MILITARY_INFO    = "Thank you for your service";
        final String CARD_TYPE        = "VisaCard";
        final String TRAVEL_GUARD     = "NotRequired";

        //Confirmation Page Constant Value
        final String BOOKING_STATUS   = "Confirmed";

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
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().verifySelectedMilitaryBags();
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/

        ValidationUtil.validateTestStep("Validating no check in options when an infant is present on the booking",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*************************************************************************************************
         * *********************************Payment Page Methods******************************************
         ************************************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating the right info is displayed after military verification",
                pageObjectManager.getPaymentPage().getActiveMilitaryThankYouText().getText().trim(),MILITARY_INFO);

        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions("ShortCutBoarding");
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);


        /*************************************************************************************************
         * *******************************Confirmation Page Methods***************************************
         ************************************************************************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions("ShortCutBoarding");

    }

}
