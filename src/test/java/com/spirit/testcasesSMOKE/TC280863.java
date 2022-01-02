package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC280863
//Title      : E2E_FSMC_OW DOM 1 ADT_SW Change Date Connecting_Standard_No Bags_BFS Seats_No Extras CI Web_Credit Card
//Created By : Gabriela
//Created On : 22-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 23-Apr-2019
//**********************************************************************************************
public class TC280863 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" ,
            "FSMasterCard" , "NewFlightSearch" ,"Connecting" , "BookIt" , "NoBags" , "BigFrontSeat" ,
            "DynamicShoppingCartUI" , "CheckInOptions" , "Visa"})
    public void E2E_FSMC_OW_DOM_1_ADT_SW_Change_Date_Connecting_Standard_No_Bags_BFS_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        //*******************************************************************
        //*******************Navigate to Confirmation Page*******************
        //*******************************************************************
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280863 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSMCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "BWI";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_DATE1           = "10";
        final String ARR_DATE1           = "NA";
        final String DEP_FLIGHT          = "Connecting";
        final String FARE_TYPE           = "Standard";
        final String UPGRADE_VALUE       = "BookIt";

        //Seats Page constant values
        final String BFS_DEP             = "BigFront||BigFront";
        final String ITINERARY_SEAT      = "1 Big Front Seat";

        //Options page constant values
        final String FREE_CHECKIN        = "CheckInOption:MobileFree";

        //Payment page constant values
        final String CARD_TYPE           = "VisaCard";
        final String TRAVEL_GUARD        = "NotRequired";

        //Confirmation page constant values
        final String BOOKING_STATUS      = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //New sear on Search Widget is made and new dates are selected
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE1, ARR_DATE1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Selecting BFS for departure flight
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(BFS_DEP);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //click on dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on seat on dynamic shopping cart
        pageObjectManager.getHeader().getArrowSeatsItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify departing seat
        ValidationUtil.validateTestStep("Verify the Departing Flight Seat selected on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getDepSeatFlightLegDetailsText().get(0).getText(),ITINERARY_SEAT);

        //verift return seat
        ValidationUtil.validateTestStep("Verify the Departing Flight Seat selected on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getRetSeatFlightLegDetailsText().get(0).getText(),ITINERARY_SEAT);

        //Options Page Methods. Continue without extras
        pageMethodManager.getOptionsPageMethods().selectOptions(FREE_CHECKIN);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //*******************************************************************
        //*******************Validation ton Confirmation Page****************
        //*******************************************************************
        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}
