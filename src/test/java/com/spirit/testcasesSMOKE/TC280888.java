package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280888
//Description:  Task 24302: 31700 630. E2E_FSMC_RT INT 2 ADT_SW Change 3 ADT, Bare, Red-Eye_Need Help To and From Seat_Add Bags_3 BFS Seats_No Extras, CI Web_Mastercard
//Created By : Gabriela
//Created On : 23-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 27-May-2019
//**********************************************************************************************

public class TC280888 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "Miles" , "InternationalDomestic" , "WithIn7Days" , "NewFlightSearch" ,
                    "Adult" , "FSMasterCard" , "NonStop" , "PassengerInfoSSR" , "NoBags" , "BigFrontSeat" , "CheckInOptions" ,
                    "MasterCard" , "TravelInsurancePopUp" , "ReservationUI"})
    public void E2E_FSMC_RT_INT_2_ADT_SW_Change_3_ADT_Bare_Red_Eye_Need_Help_To_and_From_Seat_Add_Bags_3_BFS_Seats_No_Extras_CI_Web_Mastercard(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280888 under SMOKE Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSMCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "PTY";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "5";
        final String ADULT              = "2";
        final String ADULTS             = "3";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "914";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Miles";
        final String UPGRADE_VALUE		= "BookIt";

        //Seats Page Constant Values
        final String DEP_SEATS          = "BigFront|BigFront|BigFront";

        //Option Page Constant Value
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
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

        //Flight Availability Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //new search
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Select 3 Adult
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);

        //Back to Flight availability page
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Switch to Miles View
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNumberType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Page Methods
        pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().get(0).click();
        pageObjectManager.getPassengerInfoPage().getWheelChairToAndFromGateListCheckBox().get(0).click();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
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
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));

    }
}