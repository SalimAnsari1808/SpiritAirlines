package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC281153
//Description:  E2E_Guest_MC DOM 1 ADT 1 Child - 5_Thru Flight_Standard_No Bags_No Seats_SS, SB, FF, CI Web_Discover
//Created By:   Sunny Sok
//Created On:   9-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************

public class PRODTC89928 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")
    public void E2E_9DFC_MC_DOM_1_ADT_2_CHILD_SW_Change_PAX_1_ADT_2_INFT_Connecting_Flight_PAX1_Other_1CO_1_CB_1_Seat_No_Extras_CI_Web_Visa(@Optional("NA") String platform) {

        /******************************************************************************
         ******************************Navigate to Purchase Page***********************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODEPIC31651 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "BOS|FLL";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL|BOS";
        final String DEP_DATE           = "5|8";
        final String ADULTS             = "1";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Through";
        final String RET_FLIGHT         = "Through";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Optional page constant value
        final String SELECT_OPTION      = "FlightFlex|ShortCutSecurity,ShortCutSecurity|CheckInOption:MobileFree";

        //payment page constant value
        final String OPTIONS_PAGE       = "FlightFlex|ShortCutSecurity|ShortCutBoarding";

        //payment page constant value
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        /******************************************************************************
         *******************************Home Page Method********************************
         ******************************************************************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(DEP_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //select child of 5 years
        pageObjectManager.getHomePage().getChildTravelerPanel().get(0).findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-1825), "MM/dd/yyyy"));

        //close child popup
        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(SELECT_OPTION);
        pageObjectManager.getOptionsPage().getShortCutBoardingCardAddButton().click();
        WaitUtil.untilTimeCompleted(1000);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(OPTIONS_PAGE);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//
//        /******************************************************************************
//         *************************Validation on Confirmation Page**********************
//         ******************************************************************************/
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(OPTIONS_PAGE);
    }
}