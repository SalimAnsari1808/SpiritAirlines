package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC281105
//Description: E2E_Guest_RT DOM 1 ADT 2 Children_SW Change PAX 1 ADT 2 INFT (1Lap, 1 Seat), Book It [Tier 1], Direct Flight_JR and SR_1 Carry On 5 Checked for all PAX_Any Seats_No Extras, CI Web_Visa
//Created By : Sunny Sok
//Created On : 25-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 31-MAY-2019
//**********************************************************************************************


public class TC281105 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Within21Days" , "Adult" ,"InfantSeat", "InfantLap" , "Guest" ,
                    "NewFlightSearch" , "NonStop" , "BookIt" , "MandatoryFields" , "CarryOn" , "CheckedBags" , "Standard" ,
                    "ShortCutBoarding" , "OptionalUI" , "Visa"})
    public void E2E_Guest_RT_DOM_1_ADT_2_Children_SW_Change_PAX_1_ADT_2_INFT_1Lap_1_Seat_Book_It_Tier_1_Direct_Flight_JR_and_SR_1_Carry_On_5_Checked_for_all_PAX_Any_Seats_No_Extras_CI_Web_Visa(@Optional("NA") String platform) {
        //*******************************************************************
        //****************Navigate to Confirmation Page**********************
        //*******************************************************************
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281105 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "15";
        final String ARR_DATE           = "21";
        final String ADULTS             = "1";
        final String CHILD              = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //New Search Flight Availability Page Constant Values
        final String NEW_CHILD          = "0";
        final String NEW_INFANT_LAP     = "1";
        final String NEW_INFANT_SEAT    = "1";
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE		= "BookIt";

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_1|Checked_5||Carry_1|Checked_5";
        final String RET_BAGS 			= "Carry_1|Checked_5||Carry_1|Checked_5";
        //Seats Page Constant Values
        final String SEAT_DEP           = "Standard|Standard";

        //payment page constant
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, NEW_CHILD, NEW_INFANT_SEAT, NEW_INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        //Filling infant information with the same adult information
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0),passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getAdultSuffixListDropDown().get(0),"SR");

        pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(1).click();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(1),passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(1).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(1).sendKeys(passengerInfoData.lastName);
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getAdultSuffixListDropDown().get(1),"JR");

        //Filling mandatory passenger fields
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();


        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT_DEP);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //option Page Methods
        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify Check-In Option is disabled on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);


        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}