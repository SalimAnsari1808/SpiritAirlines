package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC281042
//Test Name:  E2E_Guest_OW INT 1 ADT_Thru Flight_Miltary_ Bags_Exit Row_FLight Flex CI_Discover
//Description: Validating a Military member as a guest with bags for free and the verification process on payment page can be made satisfactory
//Created By : Alex Rodriguez
//Created On : 21-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-MAY-2019
//**********************************************************************************************

public class TC281042 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticInternational" , "Within21Days" , "Adult" , "Military" , "Through"
                    , "BookIt" , "DynamicShoppingCartUI" , "CarryOn" , "CheckedBags" , "BagsUI" , "NoSeats" , "FlightFlex" ,
                     "ShortCutBoarding" , "PaymentUI" , "Discover","CheckInOptions"})
    public void E2E_Guest_OW_INT_1_ADT_Thru_Flight_Military_No_Bags_Exit_Row_Flight_Flex_Web_CI_DISCOVER(@Optional("NA") String platform) {
        /******************************************************************************
         ***********************Navigation to Confirmation Page************************
         ******************************************************************************/
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281042 under SMOKE Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "OneWay";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "BOS";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "CUN";
        final String DEP_DATE               = "15";
        final String ARR_DATE               = "NA";
        final String ADULTS                 = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "connecting";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS               = "Carry_1|Checked_2";
        final String BAGS_PRICES            = "$0.00";
        final String BAGS_FARE              = "Standard";

        //Options Page Constant Value
        final String MY_TRIP_OPTION_VALUE   = "FlightFlex";
        final String OPTION_VALUE 		    = "CheckInOption:MobileFree";

        //payment Page constant values
        final String CARD_TYPE              = "DiscoverCard1";
        final String TRAVEL_GUARD           = "NotRequired";

        //Confirmation Page Constant value
        final String BOOKING_STATUS         = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        //******************************************************************
        //*****************************Bags Page ***************************
        //******************************************************************
        //Todo Edited test to include bags as you can not verify Military personnel on Payment page without including bags in booking
        //Validating City Pair and Bags for free available for Military Passenger
        ValidationUtil.validateTestStep("Validating departure city is correct on Bags Page",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),DEP_AIRPORT_CODE);

        ValidationUtil.validateTestStep("Validating arriving city is correct on Bags Page",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),ARR_AIRPORT_CODE);

        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

        ValidationUtil.validateTestStep("Carry on for free validation for military passenger on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(0).getText(),BAGS_PRICES);

        ValidationUtil.validateTestStep("2 checked bags for free validation for military passenger on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(0).getText(),BAGS_PRICES);

        ValidationUtil.validateTestStep("Validating Bags Total Price displayed is 0.00 on Bags Page",
                pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText(),BAGS_PRICES);

        ValidationUtil.validateTestStep("Validating 3rd bag for a military passenger is not for free on Bags Page",
                !pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().contains(BAGS_PRICES));

        ValidationUtil.validateTestStep("9FC price displayed is right on Bags Page" ,
                pageObjectManager.getBagsPage().getContinueWith9FCBagsContainerPriceText().getText(),BAGS_PRICES);

        ValidationUtil.validateTestStep("Validating Standard price match with the total displayed on Bags Page",
                pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerPriceText().getText(),BAGS_PRICES);

        pageObjectManager.getHeader().getYouItineraryImage().click();

        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("The price in the Dynamic Shopping cart is right",
                pageObjectManager.getHeader().getBagsPriceItineraryText().getText(),BAGS_PRICES);

        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);

        //Seats page Method
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //******************************************************************
        //***************************Options Page***************************
        //******************************************************************
        //Flight Flex is selected
        pageMethodManager.getOptionsPageMethods().selectOptions(MY_TRIP_OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //******************************************************************
        //***************************Payment Page***************************
        //******************************************************************
        //Verifying Military Bags in 0.00 and redirecting for military verification
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        WaitUtil.untilTimeCompleted(2000);

        pageObjectManager.getPaymentPage().getTotalDueBagsChevronLink().click();

        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Validating Bags Price is 0.00 on Payment page",
                pageObjectManager.getPaymentPage().getTotalDueBagsPriceText().getText(),BAGS_PRICES);

        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         ***********************Validation on Confirmation Page************************
         ******************************************************************************/
        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}