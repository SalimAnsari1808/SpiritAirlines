package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: PRODTC378518
//Test Name:  Task 23102: 31397 484. E2E_9DFC_RT DOM 1 ADT 1 Child +5 -15_DirectFlight_Standard_NoBags_NoSeats_SS,SB,FF CI Airport_Amex
//Description: Validating a 9FC member with 1 child can select Flight Flex, Shortcut Boarding and Shortcut Security for a booking,
// outside 24 hours and pay with an Amex credit card
//Created By : Gabriela
//Created On : 22-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 23-APR-2019
//**********************************************************************************************
public class PRODTC378518 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups="Production")

    public void  E2E_9DFC_RT_DOM_1_ADT_1_Child_5_15_DirectFlight_Standard_NoBags_NoSeats_SS_SB_FF_CI_Airport_Amex(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC378518 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values for Reservation Credit
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "ProdNineDFCEmail";
        final String LOGIN_EMAIL_TEST   = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ORD";
        final String DEP_DATE           = "2";
        final String ARR_DATE           = "5";
        final String ADULTS             = "1";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTION_VALUE 		= "FlightFlex|ShortCutSecurity,ShortCutSecurity|ShortCutBoarding|CheckInOption:MobileFree";
        final String OPTION_VER         =  "FlightFlex|ShortCutSecurity|ShortCutBoarding";
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
        if(getDriver().getCurrentUrl().equals("https://www.spirit.com/")) {
            pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        }else
        {
            pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL_TEST);
        }
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
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
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(OPTION_VER);
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
//        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(OPTION_VER);
    }
}