package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280820
//Description: Task 24320: 31705 644. E2E_FSMC_OW DOM MULTI ADT_SW Change Date, Book It [Tier 1], Connecting_Standard, Add KTN#_0,0 (1st PAX) Mix Bags for Rest of PAX_Mix Continue without Seats_ SS, SB, FF, CI Web_Mastercard
//Created By: Gabriela
//Created On:
//Reviewed By: Salim Ansari
//Reviewed On: 27-Jun-2019
//**********************************************************************************************

public class TC280820 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn21Days" , "Adult" , "FSMasterCard" , "NewFlightSearch" ,
                     "Connecting" , "BookIt" , "PassengerInfoKTN" , "CheckedBags" , "CarryOn" , "FlightFlex" , "ShortCutSecurity" ,
                     "ShortCutBoarding" , "CheckInOptions" , "MasterCard"})
    public void E2E_FSMC_OW_DOM_MULTI_ADT_SW_Change_Date_Book_It_Tier_1_Connecting_Standard_Add_KTN_1st_PAX_Mix_Bags_for_Rest_of_PAX_Mix_Continue_without_Seats_SS_SB_FF_CI_Web_Mastercard(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280820 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String LOGIN_ACCOUNT          = "FSMCEmail";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "OneWay";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "LAS";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LGA";
        final String DEP_DATE               = "6";
        final String ARR_DATE               = "NA";
        final String ADULT                  = "4";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_DATE_1             = "8";
        final String DEP_FLIGHT             = "Connecting";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS               = "Carry_0|Checked_0||Carry_1|Checked_1||Carry_0|Checked_2||Carry_0|Checked_2";

        //Seat Page Constant
        final String DEP_SEAT               = "Standard|Standard|Standard|Standard||Standard|Standard|Standard|Standard";

        //Options page Constant Values
        final String OPTIONS_VALUE          = "FlightFlex|ShortCutSecurity,NotRequired|CheckInOption:MobileFree";
        final String OPTIONS_VAL            = "FlightFlex|ShortCutBoarding|ShortCutSecurity";

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
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        //New Search
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1,ARR_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);//Bare Fare

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(1).clear();
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(1).sendKeys("AKnown");
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(1).clear();
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(1).sendKeys("Traveler");
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).clear();
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).sendKeys("03211940");
        pageObjectManager.getPassengerInfoPage().getAdultKTNListTextBox().get(1).clear();
        pageObjectManager.getPassengerInfoPage().getAdultKTNListTextBox().get(1).sendKeys("135704549");

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(OPTIONS_VAL);
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

        ValidationUtil.validateTestStep("Validating KTN",
//                pageObjectManager.getConfirmationPage().PassengerKTN(),"135704549");
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//p[contains(text(),'135704549')]"))));
        //Validating Options Selected
        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(OPTIONS_VAL);
    }
}