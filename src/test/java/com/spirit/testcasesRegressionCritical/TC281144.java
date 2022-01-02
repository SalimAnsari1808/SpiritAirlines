package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.*;

//**********************************************************************************************
//Test Case ID: TC281144
//Test Name: Task 24190: 31590 186. E2E_Guest_RT DOM 1 ADT_SW Change PAX 2 ADT, Bundle It [Tier 3] Fare, Direct Flight_1 Military 1 Standard_Add Bags_BFS Seats CWO_No Extras, CI Web_TG_CC
//Created By : Gabriela
//Created On : 05-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 17-Jun-2019
//**********************************************************************************************

public class TC281144 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Military" ,
                     "NewFlightSearch" , "NonStop" , "MandatoryFields" , "CarryOn" , "CheckedBags" , "Bikes" ,
                     "BigFrontSeat" , "ShortCutBoarding" , "CheckInOptions" , "MasterCard" , "TravelInsuranceBlock" ,
                     "PaymentUI", "BundleIt"})
    public void E2E_Guest_RT_DOM_1_ADT_SW_Change_PAX_2_ADT_Bundle_It_Tier_3_Fare_Direct_Flight_1_Military_1_Standard_Add_Bags_BFS_Seats_No_Extras_CI_Web_TG_CC(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281144 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "CLE";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAX";
        final String DEP_DATE 			= "2";
        final String ARR_DATE 			= "5";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String ADULT_1  			= "2";
        final String FARE_TYPE          = "Standard";

        final String DEP_FLIGHT 		= "NonStop";
        final String RET_FLIGHT 		= "NonStop";
        final String UPGRADE_VALUE      = "BundleIt";

        //passenger page constant value
        final String SELECTED_OPTION    = "MilitaryBags";

        //Bags Page Constant Vales
        final String BAG_URL            = "/book/bags";
        final String DEP_BAGS           = "Carry_1|Checked_2||Carry_1|Checked_1";
        final String RET_BAGS           = "Carry_1|Checked_2||Carry_1|Checked_1";

        //Seats Page
        final String DEP_SEATS          = "BigFront|BigFront";

        //Option Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT_1, CHILD, INFANT_SEAT, INFANT_LAP);

        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().get(1).click();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        pageMethodManager.getPassengerInfoMethods().selectMilitaryBagBundlePopup(SELECTED_OPTION);

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL", getDriver().getCurrentUrl(),BAG_URL);
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(1).click();
        pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(1).click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getBagsPage().getReturningSportingEquipmentLinkButton().get(1).click();
        pageObjectManager.getBagsPage().getReturningBicyclePlusButton().get(1).click();
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();

        WaitUtil.untilPageLoadComplete(getDriver());
        //Validating Travel Insurance information
        List<String> textBeingValidated = new ArrayList<String>();
        textBeingValidated.add("100% Trip Cost Cancellation");
        textBeingValidated.add("125% Trip Cost Trip Interruption");
        textBeingValidated.add("$500 Missed Connection");
        textBeingValidated.add("$500 Trip Delay");
        textBeingValidated.add("$500 Baggage & Personal Effects");
        //Verify All section under TG section
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getPaymentPageMethods().travelGuardVerbiagesAndLink(textBeingValidated);

        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getPaymentPage().getYesTravelGuardLabel().click();

        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getPaymentPageMethods().calculateTravelGuard();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}