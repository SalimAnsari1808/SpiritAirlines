package com.spirit.testcasesSMOKE;

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
//Test Case ID: TC280896
//Test Name: Task 23117: 31411 638. E2E_FSMC_RT DOM 1 ADT 2 Children_SW Change PAX 1 ADT 2 INFT (1Lap, 1 Seat), Bare, Direct_Jr-Sr_1 CryOn 5 Chkd for all PAX_Thrills Seats_No Extras, CI Web_Mastercard
//Description: Validating "New Search" button on Flight Availability page and "Duplicates Passenger Names Found" pop up is displayed on Passenger information page
//Created By : Gabriela
//Created On : 25-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 26-APR-2019
//**********************************************************************************************
public class TC280896 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Within21Days" , "Adult" ,
            "InfantLap" , "InfantSeat" , "FSMasterCard" , "NewFlightSearch" , "NonStop" ,
            "MandatoryFields" , "Standard" , "NoBags" , "MasterCard"})
    public void E2E_FSMC_RT_DOM_1_ADT_2_Children_SW_Change_PAX_1_ADT_2_INFT_1Lap_1Seat_Bare_Direct_Jr_Sr_1_CryOn_5_Chkd_for_all_PAX_Thrills_Seats_No_Extras_CI_Web_Mastercard(@Optional("NA") String platform) {
        //*******************************************************************
        //****************Navigate to Confirmation Page**********************
        //*******************************************************************
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280896 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSMCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "ORD";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ATL";
        final String DEP_DATE           = "15";
        final String ARR_DATE           = "21";
        final String ADULTS             = "1";
        final String CHILD              = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //New Search Flight Availability Page Constant Values
        final String TRIP_TYPE1         = "RoundTrip";
        final String DEP_AIRPORTS1      = "AllLocation";
        final String DEP_AIRPORT_CODE1  = "ORD";
        final String ARR_AIRPORTS1      = "AllLocation";
        final String ARR_AIRPORT_CODE1  = "ATL";
        final String DEP_DATE1          = "15";
        final String ARR_DATE1          = "21";
        final String ADULTS1            = "1";
        final String CHILD1             = "0";
        final String INFANT_LAP1        = "1";
        final String INFANT_SEAT1       = "1";

        final String DEP_FLIGHT         = "NonStop";
        final String ARR_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_FARE		= "BookIt";


        //Seats Page Constant Values
        final String SEAT_DEP           = "Standard|Standard";

        //payment page constant
        final String CRAD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //*******************************************************************
        //******************************Home Page****************************
        //*******************************************************************
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //*******************************************************************
        //******************Flight Availability Page*************************
        //*******************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE1);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS1, DEP_AIRPORT_CODE1, ARR_AIRPORTS1, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE1, ARR_DATE1);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS1, CHILD1, INFANT_SEAT1, INFANT_LAP1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Selecting Standard Flight fares
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);


        //*******************************************************************
        //******************Passenger Information page***********************
        //*******************************************************************
        //Filling infant information with the same adult information
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("FSMC");
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getInfantTitleListDropDown().get(0),passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getInfantFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getInfantLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);

        //Filling mandatory passenger fields
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(0).click();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Duplicates Passenger Names Found pop up
        pageObjectManager.getPassengerInfoPage().getEditDuplicateNamesButton().click();

        //Fill infant Suffix to eliminate duplicates passengers
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getInfantSuffixListDropDown().get(0),"JR");

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //*******************************************************************
        //***************************Bag page********************************
        //*******************************************************************
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //*******************************************************************
        //***************************Seat page*******************************
        //*******************************************************************
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT_DEP);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //*******************************************************************
        //************************Options page*******************************
        //*******************************************************************
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*******************************************************************
        //************************Payment page*******************************
        //*******************************************************************
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CRAD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //*******************************************************************
        //****************Validation on Confirmation Page********************
        //*******************************************************************
        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}