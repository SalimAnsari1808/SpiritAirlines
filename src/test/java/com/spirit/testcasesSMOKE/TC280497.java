package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test CaseID: TC280497
//Title      : E2E_9DFC_RT DOM Multi ADT_DirectFlight_Miles_Standard_NoBags_NoSeats_NoExtras CI web_Discover
//Description:
//Created By : Sunny Sok
//Created On : 07-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-May-2019
//**********************************************************************************************

public class TC280497 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "Miles", "RoundTrip" ,"Nonstop" , "DomesticDomestic" , "Outside21Days" ,"Adult",
            "NineDFC", "BookIt" ,"NoBags" , "NoSeats", "Discover", "ActiveBug", "CheckInOptions" })
    //Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page

    public void E2E_9DFC_RT_DOM_Multi_ADT_DirectFlight_Miles_Standard_NoBags_NoSeats_NoExtras_CI_web_Discover(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Confirmation  Page********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280497 under Smoke Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE         = "English";
        final String JOURNEY_TYPE     = "Flight";
        final String EMAIL_LOGIN      = "NineDFCEmail";
        final String TRIP_TYPE        = "RoundTrip";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE = "LGA";
        final String DEP_DATE         = "25";
        final String ARR_DATE         = "30";
        final String ADULTS           = "2";
        final String CHILDREN         = "0";
        final String INFANT_LAP       = "0";
        final String INFANT_SEAT      = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT       = "Nonstop";
        final String RET_FLIGHT       = "Nonstop";
        final String UPGRADE_VALUE    = "BookIt";

        //Options Page Constant Values
        final String OPTIONS          = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String TRAVEL_GUARD     = "NotRequired";
        final String CREDIT_CARD      = "DiscoverCard1";

        //Confirmation page Constant values
        final String STATUS           = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(EMAIL_LOGIN);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep",DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Ret",RET_FLIGHT);
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillAnotherCardPaymentDetailsModifyPath(CREDIT_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /******************************************************************************
         ****************************Validation on Confirmation  Page******************
         ******************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking status
        ValidationUtil.validateTestStep("User confirms booking was completed successfully on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),STATUS);
    }
}