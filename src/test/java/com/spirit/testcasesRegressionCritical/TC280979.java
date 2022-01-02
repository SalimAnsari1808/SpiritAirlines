package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280979
//Description: Task 24331: 31716 717. E2E_FSMC_RT DOM 1 ADT 1 INFT (LAP)_SW Change Date Bundle Fare Red Eye Flight_MIL_RT 1CO 2CB_No Seats_No Extras_CI Web_Credit Card
//Created By: Gabriela
//Created On: 13-Jun-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 20-Jun-2019
//**********************************************************************************************

public class TC280979 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip", "DomesticDomestic" , "Adult" , "InfantLap" , "Within7Days" , "FSMasterCard" , "Military" ,
                    "NewFlightSearch" , "NonStop" , "RedEye", "CarryOn" , "CheckedBags" , "NoSeats" ,"ShortCutBoarding", "Visa",
                    "ActiveBug","ConfirmationUI","PaymentUI"})
    public void E2E_FSMC_RT_DOM_1_ADT_1_INFT_LAP_SW_Change_Date_Bundle_Fare_Red_Eye_Flight_MIL_RT_1CO_2CB_No_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280979 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "MilitaryFreeSpiritMasterCard";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "LAS";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "DFW";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "5";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "1";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_DATE_1			= "1";
        final String ARR_DATE_1			= "3";
        final String DEP_FLIGHT 		= "214";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Passenger Info Page Constant Values
        final String MILITARY_BAG_POPUP = "MilitaryBags";

        //Options Page Constant Values
        final String OPTION_VALIDATION  = "ShortCutBoarding";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
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
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1, ARR_DATE_1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNumberType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);//Bare Fare
        pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().get(1).click();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        pageMethodManager.getPassengerInfoMethods().selectMilitaryBagBundlePopup(MILITARY_BAG_POPUP);

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
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(OPTION_VALIDATION);
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Verify reached on confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //Verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifySelectedBaseFareConfirmation(UPGRADE_VALUE);//Bare Fare
    }
}