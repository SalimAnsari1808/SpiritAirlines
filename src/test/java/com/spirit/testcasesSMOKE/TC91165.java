package com.spirit.testcasesSMOKE;

import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.spirit.baseClass.BaseClass;


//**********************************************************************************************
//TestCase ID: TC91165
//Test Name  : Options_CP_MT_Shortcut_Boarding_Spirit_Master_Card
//Description: Validating a FSMC member has Shortcut Boarding included on the Options page.
//Created By : Alex Rodriguez
//Created On : 12-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Apr-2019
//**********************************************************************************************
public class TC91165 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FSMasterCard" , "Connecting" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard"})
    public void Options_CP_MT_Shortcut_Boarding_Spirit_Master_Card(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91165 under SMOKE suite on " + platform + " Browser", true);
        }

        //open browser
        openBrowser(platform);

        //Step 1 & 2
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_EMAIL        = "FSMCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String JOURNEY_UPGRADE    = "BookIt";

        //Options Page Constant values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Reservation page Constant Values
        final String MYTRIP_BAGS_UPSELL = "Purchase";
        final String MYTRIP_URL         = "SPIRIT.COM/CHECK-IN/PAYMENT";

        //Bags Page constant Values
        final String MYTRIP_DEP_BAG     = "Carry_0|Checked_1";
        final String MYTRIP_SEAT_UPSELL = "NotRequired";


        //Step_ 1,2,3
        //*********Navigate to Flight Availability Page******
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //********Navigate to Passenger Information Page****
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(JOURNEY_UPGRADE);

        //****************Navigate to Bags Page**************
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //***************Navigate to Seats Page**************
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //***************Navigate to Options Page***********
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //**************Navigate to Payment Page**************
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //**************Navigate to Confirmation Page********
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().fillAnotherCardPaymentDetailsModifyPath(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //************Navigate to ManageTravel Page**********
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //********************Start oF Check In Path**********************/
        //Step 3
        //login to Check In
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

//        if(TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupBuyBagsNowButton())) {
            pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(MYTRIP_BAGS_UPSELL);
            WaitUtil.untilPageLoadComplete(getDriver());
//        }else {
//            pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(MYTRIP_BAGS_UPSELL);
//        }
        //User selects
        pageMethodManager.getBagsPageMethods().selectDepartingBags(MYTRIP_DEP_BAG);

        if(TestUtil.verifyElementDisplayed(pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton())){
            pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();
        }else if(TestUtil.verifyElementDisplayed(pageObjectManager.getBagsPage().getContinueWithBagsButton())){
            pageObjectManager.getBagsPage().getContinueWithBagsButton().click();
        }

        pageMethodManager.getBagsPageMethods().checkInPurchaseSeatPopup(MYTRIP_SEAT_UPSELL);

        //************************************************************
        // * Validate Shortcut Security is not present on Options Page*
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User verifies Shortcut boarding is not an available option for purchase",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardTitleText()));

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Travel guard modal
        WaitUtil.untilTimeCompleted(2000);

        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        WaitUtil.untilPageLoadComplete(getDriver());
        //Payment Page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCVVDetailsModifyPath(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");
        pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(0).click();

//
//        ValidationUtil.validateTestStep("User verifies payment was completed and Navigated to reservation Summary Page",
//                getDriver().getCurrentUrl(),MYTRIP_URL);
    }
}