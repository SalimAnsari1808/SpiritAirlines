package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC280386
//Title      : 434. E2E_9DFC_OW DOM 1 ADT 2 INFT 1 lap 1 seat_SW ChangePAX to 1 INFT (LAP) 1 child +2 Bundle It [Tier 3] Fare Direct Flight_STD_1CO 1CB_1 Free Seat_No Extras_CI Web_Credit Card
//Description: Validate user can complete booking by using parameters from the title
//Created By : Kartik chauhan
//Created On : 10-June-2019
//Reviewed By: Salim Ansari
//Reviewed On: 10-June-2019
//**********************************************************************************************

public class TC280386 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Child" , "InfantLap" , "NineDFC" ,
                     "NewFlightSearch" , "NonStop" , "BundleIt" , "DynamicShoppingCartUI" , "CarryOn" , "CheckedBags" , "BagsUI" ,
                     "Standard" , "SeatsUI" , "FlightFlex" , "ShortCutBoarding" , "OptionalUI" , "Visa","ConfirmationUI"})
    public void E2E_9DFC_OW_DOM_1_ADT_2_INFT_1_lap_1_seat_SW_ChangePAX_to_1_INFT_LAP_1_child_2_Bundle_It_Tier_3_Fare_Direct_Flight_STD_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Credit_Card (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280386 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String DEP_AIRPORT_CODE1  = "BWI";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String ARR_AIRPORT_CODE1  = "FLL";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String CHILD1             = "1";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        // Bags page constant
        final String BAGS_PRESELECTED   = "1";
        final String BAGS_INCLUDED      = "Included";
        final String BUNDLE_ITINERARY   = "Bundle It Discount";

        //seats page constant value
        final String DEP_SEATS          = "Standard|Standard";

        //Options Page constant values
        final String SELECTED_OPTION    = "FlightFlex|ShortCutBoarding";
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //New sear on Search Widget is made and new dates are selected
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE1, ARR_AIRPORTS, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD1, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Methods
        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart",
                scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText().replace("-",""));

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

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Seats page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);

        //verify for continue without seat
        ValidationUtil.validateTestStep("Verify 'Continue Without Seat' is not displayed on Seat Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton()));
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page methods
        //verify flight flex is selected
        ValidationUtil.validateTestStep("Verify Flight Flex is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));

        //verify Shortcut Boarding is selected
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

//        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        ValidationUtil.validateTestStep("Verify check-in options are disabled because of emotional support animal Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         *************************Validation on Confirmation Page**********************
         ******************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);

    }

}