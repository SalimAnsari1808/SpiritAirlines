package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281133
//Description: E2E_Guest_RT DOM 1 ADT 1 INFT (Lap)_SW Change Date, Bundle Fare, Direct Flight_Military, POC and Service Animal_Military Bags_BFS Seats_SB, CI Web_CC
//Created By : Anthony Cardona
//Created On : 28-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 31-May-2019
//**********************************************************************************************
public class TC281133 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn21Days" , "Adult" , "Guest" , "Military" , "NewFlightSearch" ,
                    "NonStop" , "BundleIt" , "PassengerInfoSSR" , "CarryOn" ,  "CheckedBags" , "BigFrontSeat" , "CheckInOptions" ,
                    "TravelInsuranceBlock" , "Visa" , "BagsUI" , "OptionalUI" })
    public void E2E_Guest_RT_OM_1_ADT_1_INFT_Lap_SW_Change_Date_Bundle_Fare_Direct_Flight_Military_POC_and_Service_Animal_Military_Bags_BFS_Seats_SB_CI_Web_CC(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281133 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Roundtrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "10";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String NEW_DEP_DATE       = "15";
        final String NEW_ARR_DATE       = "20";

        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Passenger Information Constant Variables
        final String SSR_VALUE          = "EmotionalAnimal|PortableOxygen";
        final String MILITARY_BAG_POPUP = "MilitaryBags";

        //Bags Page Constant Values
        final String SELECT_BAGS        = "Carry_1|Checked_2";

        //Seats Page Constant Values
        final String SEATS_DEP          = "BigFront";
        final String SEATS_RET          = "BigFront";


        //Payment Page Constant Value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Method
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //Search Widget Modify New Date
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectDates(NEW_DEP_DATE, NEW_ARR_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        WaitUtil.untilTimeCompleted(2000);

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR_VALUE);
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        pageMethodManager.getPassengerInfoMethods().selectMilitaryBagBundlePopup(MILITARY_BAG_POPUP);

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().selectDepartingBags(SELECT_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(SELECT_BAGS);

        ValidationUtil.validateTestStep("Validating Bags Total Price displayed is $0.00 on Bags Page for military member",
                pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText(),"$0.00");

        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS_DEP);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS_RET);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options Page Methods
        //verify Shortcut Boarding is selected with carry on
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));


        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of emotional support animal Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}