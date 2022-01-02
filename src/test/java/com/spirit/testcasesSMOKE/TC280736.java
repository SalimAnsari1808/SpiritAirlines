package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC280736
//Title      : E2E_FS_RT DOM 1 ADT 1 INFT (LAP)_SW Change Date Bundle Direct Flight Miles Booking_MIL_POC_Service Animal_RT 1CO 2CB_Any Seat_SB_CI Web_Credit
//Description:
//Created By : Anthony Cardona
//Created On : 07-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-May-2019
//** BUG 22587 CP: BP: being brought back to devepic01.spirit.com after ID.ME verification
// 10/21/19 test case passed, removed active bug tag**/
//**********************************************************************************************
public class TC280736 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "Miles" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" ,
            "InfantLap" , "Military" , "NewFlightSearch" , "PassengerInfoSSR" , "CarryOn" ,
            "CheckedBags" , "BagsUI" , "Standard" , "OptionalUI" , "MasterCard"})
    public void E2E_FS_RT_DOM_1_ADT_1_INFT_LAP_SW_Change_Date_Bundle_Direct_Flight_Miles_Booking_MIL_POC_Service_Animal_RT_1CO_2CB_Any_Seat_SB_CI_Web_Credit(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Bags Page*****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280736 under Smoke Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String EMAIL              = "MilitaryFSMiles";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ATL";
        final String DEP_DATE           = "20";
        final String ARR_DATE           = "25";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String TRIP_TYPE2         = "RoundTrip";
        final String INFANT_LAP2        = "1";
        final String DEP_DATE2          = "30";
        final String ARR_DATE2          = "35";
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String UPGRADE_VALUE      = "BundleIt";

        //Passenger Page Constant Value
        final String ADDITIONAL_SSR     = "PortableOxygen|ServiceAnimal";

        //Bags Page Constant
        final String BUNDLE_ITINERARY   = "Bundle It Discount";
        final String BAG_PRICES     	= "$0.00";
        final String DEPARTING_BAG      = "Carry_1|Checked_2";
        final String RETURNING_BAG      = "Carry_1|Checked_2";

        //Seats Page constant values
        final String DEPARTING_SEAT     = "Standard";
        final String RETURNING_SEAT     = "Standard";

        //Payment Page Constant Values
        final String TRAVEL_GUARD       = "Notrequired";
        final String CREDIT_CARD        = "MasterCard";

        //Confirmation page Constant values
        final String STATUS             = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        //Select Miles pill switch
        pageObjectManager.getHomePage().getMilesLabel().click();
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods

        /*Select new Flight Search Button*/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE2, ARR_DATE2);
        //Select Miles pill switch
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getHomePage().getMilesLabel().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP2);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Ret", RET_FLIGHT);
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(ADDITIONAL_SSR);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        //select Military Bags
        pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupMilitaryBagsRadioButton().click();
        pageObjectManager.getPassengerInfoPage().getMilitaryBundlePopupContinueButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        //Add 1 carry on and 2 Checked bags for depart and return
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEPARTING_BAG);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RETURNING_BAG);
        //Validate Bags prices
        ValidationUtil.validateTestStep("Departing Carry-on price is $0.00" , pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(0).getText() , BAG_PRICES);
        ValidationUtil.validateTestStep("Departing checked bags price is $0.00" , pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(0).getText() , BAG_PRICES);
        ValidationUtil.validateTestStep("Return Carry-on price is $0.00" , pageObjectManager.getBagsPage().getReturningCarryOnPriceText().get(0).getText() , BAG_PRICES);
        ValidationUtil.validateTestStep("Return checked bags price is $0.00" , pageObjectManager.getBagsPage().getReturningCheckedBagPriceText().get(0).getText() , BAG_PRICES);
        //continue without bag changes
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        //Selecting seats for flight
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEPARTING_SEAT);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RETURNING_SEAT);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        //verify Shortcut Boarding is selected
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of emotional support animal Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        //continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPaymentPage().getTotalDueBagsChevronLink().click();
        WaitUtil.untilTimeCompleted(2000);
        ValidationUtil.validateTestStep("Validating Bags Price is 0.00 on Payment page",
                pageObjectManager.getPaymentPage().getTotalDueBagsPriceText().getText(),BAG_PRICES);
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();

        //Test Fails after validating the Military Member on the Payment Page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CREDIT_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("User confirms booking was completed successfully", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),STATUS);
    }
}