package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import java.lang.String;


//**********************************************************************************************
//Test CaseID: TC280469
//Title      : E2E_9DFC_RT INT 1 ADT Miles_ConnectingFlight_WheelChairManual_1CarryOn 3CheckedBags_NoSeats_SB CI web_Mastercard
//Description:
//Created By : Alex Rodriguez
//Created On : 23-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 2-May-2019
//**********************************************************************************************
/** 10/21/19 Bug closed, removed active bug tag**/

public class TC280469 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "Miles" , "DomesticInternational" , "Outside21Days" ,
            "Adult" , "NineDFC" , "Connecting" , "BookIt" , "PassengerInfoSSR" , "CheckedBags" ,
            "CarryOn" , "NoSeats" , "CheckInOptions" ,"ShortCutBoarding" , "MasterCard" , "TravelInsuranceBlock" , "PaymentUI","ConfirmationUI"})
    public void E2E_9DFC_RT_INT_1_ADT_Miles_ConnectingFlight_WheelChairManual_1CarryOn_3CheckedBags_NoSeats_SB_CI_web_Mastercard(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Confirmation  Page********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280469 under Smoke Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE         = "English";
        final String JOURNEY_TYPE     = "Flight";
        final String EMAIL_LOGIN      = "NineDFCEmail";
        final String TRIP_TYPE        = "RoundTrip";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE = "GUA";
        final String DEP_DATE         = "25";
        final String ARR_DATE         = "30";
        final String ADULTS           = "1";
        final String CHILDREN         = "0";
        final String INFANT_LAP       = "0";
        final String INFANT_SEAT      = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT       = "Connecting";
        final String RET_FLIGHT       = "Nonstop";
        final String UPGRADE_VALUE    = "BookIt";

        //Bags Page Constant Values
        final String DEPARTING_BAGS   = "Carry_1|Checked_3";

        //Options Page Constant Values
        final String OPTIONS          = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String SELECTED_OPTION  = "ShortCutBoarding";
        final String TRAVEL_GUARD     = "Required";
        final String CREDIT_CARD      = "MasterCard";

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
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger("WheelChairNeedFromGate");
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEPARTING_BAGS);
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CREDIT_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /******************************************************************************
         ****************************Validation on Confirmation  Page******************
         ******************************************************************************/
        final String WHEEL_CHAIR        = "Wheelchair - To/From Gate";

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking status
        ValidationUtil.validateTestStep("User confirms booking was completed successfully on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),STATUS);

        //verify wheel chair status
        ValidationUtil.validateTestStep("User confirms Wheel Chair is added on Confirmation Page ",
                pageObjectManager.getConfirmationPage().getWheelChairServiceText().get(0).getText(),WHEEL_CHAIR);

        //verify pre selected Shortcut boarding
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);

    }
}