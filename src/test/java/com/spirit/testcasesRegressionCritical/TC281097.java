package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC281097
//Description: E2E_Guest_RT DOM 1 UMNR_SW Change Date, Book It [Tier 1], Direct Flight_Need help to and from gate and Manual Wheel Chair_1 Carry On, 5 Checked_Continue without Seats_No Extras, CI Web_Credit Card
//Created By : Sunny Sok
//Created On : 03-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 04-Jun-2019
//**********************************************************************************************
public class TC281097 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "WithIn7Days" , "DomesticDomestic" , "Child" , "Guest" , "NonStop" ,
                     "NewFlightSearch" , "BookIt" , "PassengerInfoSSR" , "CarryOn" , "CheckedBags" , "Standard" , "ShortCutBoarding" ,
                     "AmericanExpress","OptionalUI"})
    public void E2E_Guest_RT_DOM_1_UMNR_SW_Change_Date_Book_It_Tier_1_Direct_Flight_Need_help_to_and_from_gate_and_Manual_Wheel_Chair_1_Carry_On_5_Checked_Continue_without_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281097 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 	        = "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "ATL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "2";
        final String ARR_DATE 			= "30";
        final String ADULTS 			= "0";
        final String CHILDS 			= "1";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String NEW_DEP_DATE       = "15";
        final String NEW_ARR_DATE       = "20";
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE 			= "Standard";
        final String UPGRADEVALUE 		= "BookIt";

        //Passenger Information Constant Variables
        final String SSR_VALUE          = "WheelChairNeedFromGate|OwnWheelChair-manuallypowered";

        //Bags Page Constant
        final String DEP_BAGS           = "Carry_1|Checked_5";
        final String RET_BAGS           = "Carry_1|Checked_5";

        //Seat Page Constant
        final String DEP_SEAT           = "Standard";

        //Payment Page Constant Values
        final String CARD_TYPE          = "AmericanExpressCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //wait till UMNR popup appear
        WaitUtil.untilPageLoadComplete(getDriver());

        //accept UMNR popup
        pageObjectManager.getHomePage().getUMNRAcceptButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        //Flight Availability Methods
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectDates(NEW_DEP_DATE, NEW_ARR_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getUMNRAcceptButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR_VALUE);
        WaitUtil.untilTimeCompleted(3000);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify Check-In Option is disabled on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        //continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
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

    }
}