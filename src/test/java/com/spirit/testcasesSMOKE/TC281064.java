package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281064
//Test Name: Task 24032: 31531 040. E2E_Guest_OW DOM 1 ADT_Red-eye Flight_Bundle It [Tier 3] _Standard_1co 1 cb_Any Seats_No Extras CI Web_Credit Card
//Description: Validating Red Eye pops up when early departure for OW DOM booking. Bundle discounts applying
//Created By : Gabriela
//Created On : 20-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 21-MAY-2019
//**********************************************************************************************

public class TC281064 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" ,
                     "BundleIt" , "CarryOn" , "CheckedBags" , "BagsUI" , "Standard" , "FlightFlex" , "ShortCutBoarding" ,
                     "CheckInOptions" , "Discover","RedEye"})
    public void E2E_Guest_OW_DOM_1_ADT_Red_eye_Flight_Bundle_It_Tier_3_Standard_1co_1_cb_Any_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281064 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "LAS";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "DFW";
        final String DEP_DATE 			= "2";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "2";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "214";
        final String UPGRADE_VALUE 		= "BundleIt";

        //Bags Page Constant Values
        final String BAGS_PRESELECTED 	= "1";
        final String BAGS_INCLUDED		= "Included";

        //Seats Page Constant Values
        final String DEP_SEAT           = "Standard|Standard";

        //Options page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNumberType("Dep", DEP_FLIGHT);
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();

        //Red Eye pop up
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getEarlyDepartureContinueButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Page method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Method
        ValidationUtil.validateTestStep("Validating departure city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),DEP_AIRPORT_CODE);

        ValidationUtil.validateTestStep("Validating arriving city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),ARR_AIRPORT_CODE);

        //ValidationUtil.validateTestStep("Verify 1-Carry-On and 1-Checked Bags are Pre-Selected for all passengers on Bags Page
        for(int i = 0; i < pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().size(); i ++) {
            // Verifying Carry-on is preselected
            ValidationUtil.validateTestStep("Verifying Carry On is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(i)),BAGS_PRESELECTED);

            //Verifying Carry On price is included
            ValidationUtil.validateTestStep("Verifying Carry On price is included",
                    pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(i).getText(),BAGS_INCLUDED);

            // Verifying Checked Bag is preselected
            ValidationUtil.validateTestStep("Verifying 1 checked bag is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(i)),BAGS_PRESELECTED);

            //Verifying Checked Bag price is included
            ValidationUtil.validateTestStep("Verifying Checked Bag price is included",
                    pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(i).getText(),BAGS_INCLUDED);
        }

        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Method
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        WaitUtil.untilTimeCompleted(5000);

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options Page Method
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}