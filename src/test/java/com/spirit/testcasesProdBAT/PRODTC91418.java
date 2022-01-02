package com.spirit.testcasesProdBAT;


import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: PRODTC91418
//Description:  CP_BP_Payment_Page_Itinerary_MC_Validate EDIT Bags Links
//Created By:   Salim Ansari
//Created On:   07-Aug-2019
//Reviewed By:  Kartik Chauhan
//Reviewed On:  07-Aug-2019
//**********************************************************************************************
public class PRODTC91418 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void CP_BP_Payment_Page_Itinerary_MC_Validate_EDIT_Bags_Links(@Optional("NA") String platform) {
        /******************************************************************************
         ******************************Navigate to Purchase Page***********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC91418 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL|LAS";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS|FLL";
        final String DEP_DATE           = "5|8";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_1|Checked_2";
        final String RET_BAGS 			= "Carry_1|Checked_2";
        final String BAGS_FARE          = "Standard";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "Required";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(DEP_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();


        final String NO_BAGS        = "--";
        final String SELECTED_BAGS  = "1 Carry-On , 2 Checked Bag";

        //Purchase Page
//        pageMethodManager.getPaymentPageMethods().verifyFlightSectionDetails();
        pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().click();

        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Verify No Bags are selected for Departing Flight on Passenger Sectio on Payment Page",
                pageObjectManager.getPaymentPage().getAllBagsInfoText().get(0).getText(),NO_BAGS);

        ValidationUtil.validateTestStep("Verify No Bags are selected for Arrival Flight on Passenger Sectio on Payment Page",
                pageObjectManager.getPaymentPage().getAllBagsInfoText().get(1).getText(),NO_BAGS);

        //open total due amount
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Make click on Edit bags under Total Breakdown
        pageObjectManager.getPaymentPage().getTotalDueBagsEditText().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Bags Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);

        //Seat Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().click();

        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Verify No Bags are selected for Departing Flight on Passenger Sectio on Payment Page",
                pageObjectManager.getPaymentPage().getAllBagsInfoText().get(0).getText(),SELECTED_BAGS);

        ValidationUtil.validateTestStep("Verify No Bags are selected for Arrival Flight on Passenger Sectio on Payment Page",
                pageObjectManager.getPaymentPage().getAllBagsInfoText().get(1).getText(),SELECTED_BAGS);

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//
//        //confirmation Page Methods
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        /******************************************************************************
//         ***********************Validation to Confirmation Page************************
//         ******************************************************************************/
//        //declare constant used in validation
//        final String BOOKING_STATUS = "Confirmed";
//        final String CONFIRMATION_URL = "book/confirmation";
//
//        //verify booking is confirmed
//        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);
//
//        //verify booking is confirmed
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);


    }
}
