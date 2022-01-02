package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280785
//Description: Task 24178: 31623 352. E2E_FS_RT DOM MAX PAX MIX_SW Change Month Date Bundle It [Tier 3] Fare Thru Flight_STD_RT 1CO 1CB_1 Free Seat_No Extras_CI Web_Credit Card
// Created By : Gabriela
//Created On : 06-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 17-Jun-2019
//**********************************************************************************************

public class TC280785 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Child" , "FreeSpirit" ,
                     "NewFlightSearch" , "Through" ,"NonStop", "BundleIt" ,"PassengerInformationUI", "CarryOn" , "CheckedBags" ,
                     "BagsUI" , "Standard" ,"SeatsUI", "FlightFlex" , "ShortCutBoarding" , "CheckInOptions" ,"OptionalUI", "MasterCard",
                     "PaymentUI","ConfirmationUI"})
    public void E2E_FS_RT_DOM_MAX_PAX_MIX_SW_Change_Month_Date_Bundle_It_Tier_3_Fare_Thru_Flight_STD_RT_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280785 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "DEN";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "5";
        final String ADULT  			= "6";
        final String CHILD  			= "3";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_DATE_1			= "35";
        final String ARR_DATE_1			= "45";
        final String DEP_FLIGHT 		= "Through";
        final String RET_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Bags Page Method
        final String BAG_URL            = "/book/bags";

        //Seat Page Constant Values
        final String SEAT               = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //Option Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

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
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1, ARR_DATE_1);

        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL",
                getDriver().getCurrentUrl(),BAG_URL);

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
        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
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