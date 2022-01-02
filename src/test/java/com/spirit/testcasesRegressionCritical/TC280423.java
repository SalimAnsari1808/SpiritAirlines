package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280423
//Description:  E2E_9DFC_MC DOM 1 ADT 2 CHILD_SW Change PAX to 1 ADT 2 INFT (LAP and SEAT) Connecting Flight_PAX1 Other_1CO 1 CB_1 Seat_No Extras_CI Web_Visa
//Created By:   Salim Ansari
//Created On:   24-July-2019
//Reviewed By:  Kartik Chauhan
//Reviewed On:  24-July-2019
//**********************************************************************************************
public class TC280423 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "MultiCity" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "InfantSeat" , "InfantLap" , "NineDFC" ,
                     "NewFlightSearch" , "Connecting" , "BundleIt" , "CarryOn" , "CheckedBags" ,"BagsUI", "Standard" ,"SeatsUI",
                     "FlightFlex" , "ShortCutBoarding" , "OptionalUI" , "TravelInsuranceBlock" , "Visa","PaymentUI","ConfirmationUI"})
    public void E2E_9DFC_MC_DOM_1_ADT_2_CHILD_SW_Change_PAX_to_1_ADT_2_INFT_LAP_and_SEAT_Connecting_Flight_PAX1_Other_1CO_1_CB_1_Seat_No_Extras_CI_Web_Visa(@Optional("NA") String platform) {
        /******************************************************************************
         ******************************Navigate to Purchase Page***********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280423 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "BWI|FLL";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL|BWI";
        final String DEP_DATE           = "5|8";
        final String ADULTS             = "1";
        final String CHILD              = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String ADULTS1            = "1";
        final String CHILD1             = "0";
        final String INFANT_LAP1        = "1";
        final String INFANT_SEAT1       = "1";
        final String DEP_FLIGHT         = "Connecting";
        final String RET_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //seats page constant value
        final String DEP_SEATS          = "Standard|Standard||Standard|Standard";
        final String RET_SEATS          = "Standard|Standard||Standard|Standard";

        //payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "Required";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        /******************************************************************************
         *******************************Home Page Method********************************
         ******************************************************************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(DEP_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Flight Availability Methods
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS1, CHILD1, INFANT_SEAT1, INFANT_LAP1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(0).click();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);
        pageObjectManager.getBagsPage().getContinueWithBagsButton().click();

        //Seats page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,DEP_SEATS,RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);

//        ValidationUtil.validateTestStep("Validating no check in options is not available when an infant is present",
//                !pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown().isEnabled());
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of emotional support animal Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADE_VALUE);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /******************************************************************************
         *************************Validation on Confirmation Page**********************
         ******************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifySelectedBaseFareConfirmation(UPGRADE_VALUE);
    }

}

