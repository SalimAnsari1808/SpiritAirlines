package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91099
//Description : CP_CI_Ancillaries_Add Carry On Bag After Check-In
//Created By : Anthony Cardona
//Created On : 22-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 23-JUL-2019
//**********************************************************************************************

public class TC91099 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "CarryOn" , "NoSeats" ,"ShortCutBoarding", "CheckInOptions" , "MasterCard" , "BagsUI" , "AddEditBags","BoardingPassUI"})
    public void CP_CI_Ancillaries_Add_Carry_On_Bag_After_Check_In(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Payment Page*******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91099 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
//Step 1: Create booking within 24 hours with seats already checked in
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ATL";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "3";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_FARE       = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD       = "NotRequired";
        final String VALID_CARD_TYPE    = "MasterCard";

        //Seats pop up
        final String CHECKIN_DEP_BAGS   = "Carry_1";

        //reservation page constant value
        final String CHECK_IN_BAGS      = "DontPurchase";
        final String CHECK_IN_SEAT      = "DontPurchase";
        final String CHECK_IN_URL       = "spirit.com/check-in/payment";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait until Bags page appear on web
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //options
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(VALID_CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
        //No Bags
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECK_IN_BAGS);
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        try {
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getRentACarNoThanksButton())) {
                pageObjectManager.getReservationSummaryPage().getRentACarNoThanksButton().click();//no on travel guard
            }
        } catch (Exception e) {
        }

        pageObjectManager.getReservationSummaryPage().getChooseYourSeatGetRandomSeatButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        /*********************************Options Page Methods*************************************************/
        try {
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getRentACarNoThanksButton())) {
                pageObjectManager.getReservationSummaryPage().getRentACarNoThanksButton().click();//no on travel guard
            }
        } catch (Exception e) {
        }
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getOptionsPage().getContinueButtonOnCheckInPathButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup("Print", "NA");

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 2: Begin check in process while already checked in
        pageMethodManager.getHomePageMethods().loginToCheckIn();

//Step 3: Click on Edit Seats
        pageObjectManager.getReservationSummaryPage().getPassengerSectionAddBagsButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

//Step 4: Add carry-on and select to keep same bags for return

        pageMethodManager.getBagsPageMethods().selectDepartingBags(CHECKIN_DEP_BAGS);
        pageObjectManager.getBagsPage().getKeepSameBagsLabel().get(0).click();
        String bagsPrice = pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText();

        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);


//Step 5: Continue to the payment page
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(CHECK_IN_SEAT);
        pageObjectManager.getOptionsPage().getContinueButtonOnCheckInPathButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

//Step 7: Validate that the user lands on the payment page
        ValidationUtil.validateTestStep("The user lands on the payment page", getDriver().getCurrentUrl(),CHECK_IN_URL);
        ValidationUtil.validateTestStep("The amount charged is correct", pageObjectManager.getPaymentPage().getTotalDuePriceText().getText(), bagsPrice);

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(VALID_CARD_TYPE);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//Step 8: Click to print boarding pass
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup("Print", "NA");

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);


        ValidationUtil.validateTestStep("The carry on bag is displayed on the departing boarding pass",
                pageObjectManager.getBoardingPassPage().getCarryOnText().get(0).getText().trim() , "CARRY-ON BAG");
        ValidationUtil.validateTestStep("The Number of carry on bag is displayed on the departing boarding pass",
                pageObjectManager.getBoardingPassPage().getNumberOfCarryon().get(0).getText().trim() , "1");
    }
}