package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC280670
//Description:  E2E_RT_FS_DOM 2 ADT_SW Change 3 ADT, Book It [Tier 1], Red-Eye Flight_Need Helpto and From Seat_Add Bags_3 BFS Seats_No Extras, CI Web_Discover
//Created By : Sunny Sok
//Created On : 05-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Jun-2019
//**********************************************************************************************

public class TC280670 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FreeSpirit" ,
                     "NewFlightSearch" , "NonStop" ,"RedEye", "BookIt" , "PassengerInfoSSR" , "CarryOn" ,
                     "CheckedBags" , "Bikes" , "SurfBoard" , "BigFrontSeat" ,"ShortCutBoarding", "CheckInOptions" ,
                     "Discover"})
    public void  E2E_RT_FS_DOM_2_ADT_SW_Change_3_ADT_Book_It_Tier_1_RedEye_Flight_Need_Helpto_and_From_Seat_Add_Bags_3_BFS_Seats_No_Extras_CI_Web_Discover(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280670 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAS";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "5";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String NEW_ADULT          = "3";
        final String DEP_FLIGHT         = "214";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_FARE       = "BookIt";

        //Passenger Page Constant Value
        final String ADDITIONAL_SSR     = "WheelChairNeedFromSeat";

        final String DEP_BAGS 		    = "Carry_1|Checked_3||Carry_1|Checked_1";

        //Seats Page Constant Values
        final String DEP_SEATS          = "BigFront|BigFront|BigFront";

        //Option Page Constant Value
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Value
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Method
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(NEW_ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNumberType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Page Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(ADDITIONAL_SSR);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

        //Selecting 1 bikes and 1 Surf board for pax 2
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(1).click();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(1).click();
        pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(1).click();
//        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    }
}