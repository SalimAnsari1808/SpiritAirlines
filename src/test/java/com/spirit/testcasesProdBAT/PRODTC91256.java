package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: PRODTC91256
//Description: Task 24662: 35302 CP_BP_Hub-Options Page_Multi-PAX_Neg
//Created By: Gabriela
//Created On: 25-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 27-Jun-2019
//**********************************************************************************************

public class PRODTC91256 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void CP_BP_Hub_Options_Page_Multi_PAX_Neg(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC91256 under PRODDUCTION Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "105";
        final String ARR_DATE               = "110";
        final String ADULT                  = "9";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String RET_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE          = "CheckInOption:DecideLater";

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating no cars carousel available when 9 pax on booking",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardNameText()));

        ValidationUtil.validateTestStep("Validating no hotels carousel available when 9 pax on booking",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText()));

        ValidationUtil.validateTestStep("Validating Flight Flex tile is displayed",
                pageObjectManager.getOptionsPage().getFlightFlexCardPanel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Shortcut Security tile is displayed",
                pageObjectManager.getOptionsPage().getShortCutSecurityCardPanel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Shortcut Boarding tile is displayed",
                pageObjectManager.getOptionsPage().getShortCutBoardingCardPanel().isDisplayed());

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);

        ValidationUtil.validateTestStep("Validating check in option was selected",
                pageObjectManager.getOptionsPage().getCheckInOptionCardSelectedLabel().isDisplayed());

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
    }
}