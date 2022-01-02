package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test CaseID: TC281047
//Title      : 31512 010. E2E_Guest_OW Dom 1 ADT 1 Child 5-15_Direct Flight_No Bags_No Seats_SS, SB, FF, CI Airport_Amex
//Description: Validate user can complete booking by using parameters from the title
//Created By : Alex Rodriguez
//Created On : 16-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 17-May-2019
//**********************************************************************************************

public class PRODTC281047 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void  E2E_Guest_OW_Dom_1_ADT_1_Child_5_15_Direct_Flight_No_Bags_No_Seats_SS_SB_FF_CI_Airport_Amex (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC281047 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values for Reservation Credit
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ORD";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTION_VALUE 		= "FlightFlex|ShortCutSecurity,NotRequired|ShortCutBoarding|CheckInOption:AirportAgent";
        final String OPTION_VALIDATION	= "FlightFlex|ShortCutSecurity|ShortCutBoarding|CheckInOption:AirportAgent";

        //Payment page constant values
        final String CARD_TYPE          = "AmericanExpressCard";
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
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(OPTION_VALIDATION);
        pageMethodManager.getPaymentPageMethods().verifyTotalDueOptions();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//
//        /******************************************************************************
//         ***************************Validation on Confirmation Page********************
//         ******************************************************************************/
//        //Confirmation page closing ROKT Popup
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        //Verifying booking is confirmed
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//
//        //verify options on confirmation Page
//        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(OPTION_VALIDATION);
    }
}