package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//TODO bUG 22587 CP: BP: being brought back to devepic01.spirit.com after ID.ME verification
//TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
//Test Case ID: TC280758
//Description: 296 E2E_FS_RT DOM 1 ADT 1 INFT LAP Miles_BF_Direct Flight Miles Booking_MIL_1CO 2CB_No Seats_No Extras CI Web_Visa
//Created By : Anthony Cardona
//Created On : 06-Jun-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 18-Jun-2019
//**********************************************************************************************
public class TC280758 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "Miles" , "DomesticDomestic" , "Outside21Days" , "Adult" ,
                     "InfantLap" , "Military" ,"FreeSpirit", "NonStop" , "BookIt" , "CarryOn" , "CheckedBags" ,
                     "NoSeats" , "ShortCutBoarding" , "OptionalUI" , "MasterCard" , "ActiveBug"})
    public void E2E_FS_RT_DOM_1_ADT_1_INFT_LAP_Miles_BF_Direct_Flight_Miles_Booking_MIL_1CO_2CB_No_Seats_No_Extras_CI_Web_Visa(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280758 under REGRESSION-CRITICAL  Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String FSLOGIN            = "MilitaryFSMiles";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "ATL";
        final String DEP_DATE 			= "20";
        final String ARR_DATE 			= "30";
        final String ADULTS 			= "1";
        final String CHILDS 			= "0";
        final String INFANT_LAP 		= "1";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String RET_FLIGHT 		= "NonStop";
        final String FARE_TYPE 			= "Standard";
        final String UPGRADEVALUE 		= "BookIt";

        //Bags Page Constant Values
        final String SELECT_BAGS        = "Carry_1|Checked_2";
        final String ZERO_BAG_PRICE     = "$0.00";

        //Payment Page Constant Values
        final String SELECTED_OPTION    = "ShortCutBoarding";
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(FSLOGIN);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageObjectManager.getHomePage().getMilesLabel().click();
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        //TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().verifySelectedMilitaryBags();
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
        final String OPTION_TEXT_ENGLISH	= "SELECTED";
        //verify Shortcut Boarding is pre-selected
        ValidationUtil.validateTestStep("Validating Shortcut Boarding is preselected on Options Page",
                pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel().getText().toUpperCase().trim(),OPTION_TEXT_ENGLISH);


        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of Lap Infant on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());

        //payment Page Methods
        //TODO bUG 22587 CP: BP: being brought back to devepic01.spirit.com after ID.ME verification
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
    }
}