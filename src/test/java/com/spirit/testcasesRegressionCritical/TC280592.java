package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web return null names and are then invalid for use
//TODO: Method failing due Bug 24457: CP: BP: Payment Page PMT: 500 (Internal Server Error) received when Military Passenger Validation failing
/**10/21/19 test case passed, removed active bug tag**/
//Test Case ID: TC280592
//Test Name: 278. E2E_FS_DOM 1 ADT 1 INFT (Lap)_SW Change Date, Bundle It[Tier 3], Direct Flight_Military, POC and Service Animal_Military Bags_Standard Seats_No Extras, CI Web_Voucher_credit card
// Created By: Anthony Cardona
//Created On : 05-Jun-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 18-Jun-2019
//**************************************************************************************************

            public class TC280592 extends BaseClass {
            @Parameters({"platform"})
            @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "InfantLap" , "Military" ,"NewFlightSearch", "NonStop" , "PassengerInfoSSR" , "CarryOn" , "CheckedBags" , "Standard" , "ShortCutBoarding" , "OptionalUI" , "Voucher" , "MasterCard"})
    public void E2E_FS_DOM_1_ADT_1_INFT_Lap_SW_Change_Date_Bundle_It_Tier_3_Direct_Flight_Military_POC_and_Service_Animal_Military_Bags_Standard_Seats_No_Extras_CI_Web_Voucher_credit_card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280592 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "MilitaryFSMiles";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "DEN";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "1";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_DATE_1			= "5";
        final String DEP_FLIGHT 		= "Nonstop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Passenger Information Constant Variables
        final String SSR_VALUE          = "ServiceAnimal|PortableOxygen";
        final String MILITRY_BAG_POPUP  = "MilitaryBags";

        //Bags Page Constant Values
        boolean statusFlag              = true;
        final String BAG_URL            = "spirit.com/book/bags";
        final String SELECT_BAGS        = "Carry_1|Checked_2";
        final String ZERO_BAG_PRICE 	= "$0.00";

        //Seat Page Constant
        final String DEP_SEAT           = "Standard";

        //Option Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

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
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
//TODO Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web return null names and are then invalid for use
        createVoucher();

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
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
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR_VALUE);
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        pageMethodManager.getPassengerInfoMethods().selectMilitaryBagBundlePopup(MILITRY_BAG_POPUP);

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().verifySelectedMilitaryBags();
//        pageMethodManager.getBagsPageMethods().selectDepartingBags(SELECT_BAGS);
//
//        //set flag to true for returning carry-on bags
//        statusFlag = true;
//
//        //Verify Departing Carry-On Bag prices
//        ValidationUtil.validateTestStep("Verify Departing Carry-On Bag prices is zero on Bags Page", statusFlag);
//
//        //loop through all departing checked bags
//        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().size();bagsCounter++) {
//            //check checked bag bag prices for all passenger
//            if(!pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
//                //set flag to false
//                statusFlag = false;
//            }
//        }
//
//        //Verify Departing Checked Bag prices
//        ValidationUtil.validateTestStep("Verify Departing Checked Bag prices is zero on Bags Page", statusFlag);
//
//        ValidationUtil.validateTestStep("Validating Bags Total Price displayed is 0.00 on Bags Page for military member",
//                pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText(),"$0.00");

        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/

        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of Lap Infant on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        //TODO: Method failing due Bug 24457: CP: BP: Payment Page PMT: 500 (Internal Server Error) received when Military Passenger Validation failing
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        //TODO Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web return null names and are then invalid for use
        pageMethodManager.getPaymentPageMethods().applyVoucherNumber();
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
    }

    private void createVoucher() {
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
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

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

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
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
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

        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}