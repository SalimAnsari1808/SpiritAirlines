package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
//Test Case ID: TC280728
//Description: Task 24225: 31624 355. E2E_FS_RT DOM 1 ADT Miles Booking_SW Change Date Bundle Fare Direct Flight_RTn Wheel Chair Wet Cell_OW 1CO 4CB_Any Seats_FF_CI Counter_Credit Card Visa
// Created By : Gabriela
//Created On : 03-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 04-Jun-2019
//**********************************************************************************************

public class TC280728 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "Miles" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FreeSpirit" ,
                     "NewFlightSearch" , "NonStop" , "BundleIt" , "PassengerInfoSSR" , "CarryOn" , "CheckedBags" , "BagsUI" ,
                     "Standard" , "SeatsUI" , "FlightFlex" , "ShortCutBoarding" , "CheckInOptions" , "OptionalUI" , "Visa" , "ActiveBug",
                     "ConfirmationUI"})
    public void E2E_FS_RT_DOM_1_ADT_Miles_Booking_SW_Change_Date_Bundle_Fare_Direct_Flight_RTn_Wheel_Chair_Wet_Cell_OW_1CO_4CB_Any_Seats_FF_CI_Counter_Credit_Card_Visa(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280728 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "DEN";
        final String DEP_DATE 			= "2";
        final String ARR_DATE 			= "5";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_DATE_1 		= "3";
        final String ARR_DATE_1 		= "7";
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Passenger Info Page Constant Values
        final String SSR                = "WheelChairCompletelyImmobile|OwnWheelChair";
        final String WHEEL_CHAIR_TYPE   = "hasWheelchairBatteryPoweredWetCell";

        //Bags Page Method
        final String BAG_URL            = "spirit.com/book/bags";
        final String ZERO_BAG_PRICE 	= "Included";

        //Seat Page Constant Values
        final String SEAT               = "Standard";

        //Option Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:AirportAgent";
        final String OPTION_TEXT        = "SELECTED";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";
        final String OPTIONS_VALUE_1	= "FlightFlex|ShortCutBoarding";

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
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

        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1, ARR_DATE_1);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getReturningCarouselMilesViewSwitchLabel().click();

        //TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Ret", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);
        WaitUtil.untilTimeCompleted(3000);
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getWheelChairTypeOfWheelChairDropDown().get(0),WHEEL_CHAIR_TYPE);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL",
                getDriver().getCurrentUrl(),BAG_URL);

        //set flag to true for departing carry-on bags
        boolean statusFalg = true;
        //loop through all departing carry-on bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().size();bagsCounter++) {
            //check carry-on Bag price for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFalg = false;
            }
        }

        //Verify Departing Carry-On Bag prices
        ValidationUtil.validateTestStep("Verify Departing Carry-On Bag prices is zero on Bags Page", statusFalg);

        //set flag to true for departing checked bags
        statusFalg = true;
        //loop through all departing checked bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().size();bagsCounter++) {
            //check checked bag bag prices for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFalg = false;
            }
        }
        //Verify Departing Checked Bag prices
        ValidationUtil.validateTestStep("Verify Departing Checked Bag prices is zero on Bags Page", statusFalg);

        WaitUtil.untilTimeCompleted(1200);
        //Selecting 3 more checked bags
        for (int count = 0; count <= 2; count ++){
            pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();
        }

        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        for (int i=0; i< pageObjectManager.getSeatsPage().getPremiumSeatsGridView().size(); i++)
        {
            ValidationUtil.validateTestStep("Validating premium seats are disable for wheelchair passenger",
                    pageObjectManager.getSeatsPage().getPremiumSeatsGridView().get(i).getAttribute("class").contains("unavailable"));
        }

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(SEAT);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,SEAT,SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        ValidationUtil.validateTestStep("Validating Flight Flex is preselected",
                pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel().getText(),OPTION_TEXT);

        ValidationUtil.validateTestStep("Validating Shortcut Boarding is preselected",
                pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel().getText(),OPTION_TEXT);

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(OPTIONS_VALUE);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

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

        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(OPTIONS_VALUE_1);
    }
}