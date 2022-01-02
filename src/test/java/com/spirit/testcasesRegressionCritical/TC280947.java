package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280947
//Description: Task 24337: 31722 740. E2E_FSMC_RT DOM 1 ADT 2 children_SW Change Pax to 2 INFT (lap and seat) Thrills Direct Flight_Jr-Sr_RT 1CO 5CB_Any free seat_SB and FF_CI Web_Mastercard
//Created By: Gabriela
//Created On: 14-Jun-2019
//Reviewed By: Kartik Chauhan
//Reviewed On:19-Jun-2019
//**********************************************************************************************

public class TC280947 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip", "DomesticDomestic" , "Adult" , "InfantSeat" , "InfantLap" , "WithIn7Days" , "FSMasterCard" , "NewFlightSearch" , "NonStop", "BundleIt" , "MandatoryFields" , "CarryOn" , "CheckedBags" , "Standard" , "FlightFlex" ,"ShortCutBoarding", "OptionalUI" , "MasterCard","DynamicShoppingCartUI"})
    public void E2E_FSMC_RT_DOM_1_ADT_2_children_SW_Change_Pax_to_2_INFT_lap_and_seat_Thrills_Direct_Flight_Jr_Sr_RT_1CO_5CB_Any_free_seat_SB_and_FF_CI_Web_Mastercard(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280947 under REGRESSION_CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "FSMCEmail";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "LGA";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "MYR";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "5";
        final String ADULT  			= "1";
        final String CHILD  			= "2";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";


        //Flight Availability Page Constant Values
        final String CHILD_1  			= "0";
        final String INFANT_LAP_1 		= "1";
        final String INFANT_SEAT_1		= "1";
        final String DEP_FLIGHT 		= "NonStop";
        final String RET_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Bags Page Method
        final String BAG_URL            = "/book/bags";
        final String DEP_BAGS           = "Carry_1|Checked_5||Carry_1|Checked_5";
        final String RET_BAGS           = "Carry_1|Checked_5||Carry_1|Checked_5";

        //Seats Page Constant Values
        final String DEP_SEAT           = "Standard|Standard";
        final String RET_SEAT           = "Standard|Standard";

        //Options Page Constant Values
        final String OPTION_VALIDATION  = "FlightFlex|ShortCutBoarding";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD_1, INFANT_SEAT_1, INFANT_LAP_1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("RET", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("FSMC");
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getInfantTitleListDropDown().get(0),passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getInfantFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getInfantLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);

        //Filling mandatory passenger fields
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Duplicates Passenger Names Found pop up
        pageObjectManager.getPassengerInfoPage().getEditDuplicateNamesButton().click();

        //Fill infant Suffix to eliminate duplicates passengers
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getInfantSuffixListDropDown().get(0),"JR");

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL",
                getDriver().getCurrentUrl(),BAG_URL);

        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEAT);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,DEP_SEAT,RET_SEAT);//Bare Fare
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        ValidationUtil.validateTestStep("Validating no check in options available for Infant",
                !pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown().isEnabled());
        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(OPTION_VALIDATION);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(OPTION_VALIDATION);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifySelectedBaseFareConfirmation(UPGRADE_VALUE);//Bare Fare
    }
}