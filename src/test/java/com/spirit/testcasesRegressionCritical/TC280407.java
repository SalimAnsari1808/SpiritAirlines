package com.spirit.testcasesRegressionCritical;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
/**10/21/19 test case passed, removed active bug tag**/
//Test Case ID: TC280407
//Description:  E2E_9DFC_MC INT 2 ADT MILES_SW Change to 3 ADT Red-Eye Flight_Need Help To and From Seat_Bags_BFS_No Extras_CI Web_Visa
//Created By:   Salim Ansari
//Created On:   25-July-2019
//Reviewed By:  Kartik Chauhan
//Reviewed On:  25-July-2019
//**********************************************************************************************
public class TC280407 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "MultiCity" ,"Miles", "InternationalDomestic", "DomesticInternational" , "WithIn7Days" ,
                    "Adult" , "FSMasterCard" , "NewFlightSearch" , "NonStop" , "BookIt" , "PassengerInfoSSR" , "NoBags" , "BigFrontSeat" ,
                    "ShortCutBoarding" , "CheckInOptions" ,"Visa"})
    public void E2E_9DFC_MC_INT_2_ADT_MILES_SW_Change_to_3_ADT_Red_Eye_Flight_Need_Help_To_and_From_Seat_Bags_BFS_No_Extras_CI_Web_Visa(@Optional("NA") String platform) {
        /******************************************************************************
         ******************************Navigate to Purchase Page***********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280407 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSMCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "LAS|EWR";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "AUS|CUN";
        final String DEP_DATE           = "5|8";
        final String ADULTS             = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String ADULTS1            = "3";
        final String DEP_FLIGHT         = "540";
        final String RET_FLIGHT         = "521";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Passenger Infor Page Constant
        final String SSR_VALUE          = "WheelChairNeedFromSeat||NotRequired||NotRequired";

        //Bags page constant
        final String DEP_BAGS           = "Checked_1|Carry_1||Checked_1|Carry_1||Checked_0|Carry_0";
        final String RET_BAGS           = "Checked_1|Carry_1||Checked_1|Carry_1||Checked_0|Carry_0";

        //Seat Page Constant
        final String DEP_SEAT           = "BigFront|BigFront|BigFront";
        final String RET_SEAT           = "BigFront|BigFront|BigFront";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

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

        //Flight Availability Methods
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS1, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();

        //TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNumberType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNumberType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);



        //Passenger Information Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR_VALUE);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(1).click();
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(1).click();
        pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(1).click();

        pageObjectManager.getBagsPage().getReturningSportingEquipmentLinkButton().get(1).click();
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getBagsPage().getReturningBicyclePlusButton().get(1).click();
        pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(1).click();
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS = "Confirmed";
        final String CONFIRMATION_URL = "book/confirmation";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);


    }
}
