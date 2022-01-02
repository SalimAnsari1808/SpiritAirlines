package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: PRODTC91411
//Description: Task 24677: 35346 CP_BP_Payment_Page_Itinerary_OW_Validate EDIT Flight Link on Payment Page
//Description: Validating Itinerary/EDIT link/Uplift
//Created By: Gabriela
//Created On: 27-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 28-Jun-2019
//**********************************************************************************************

public class PRODTC91411 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = "Production")

    public void CP_BP_Payment_Page_Itinerary_OW_Validate_EDIT_Flight_Link_on_Payment_Page(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC91411 under PRODUCTION Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE = "English";
        final String JOURNEY_TYPE = "Flight";
        final String TRIP_TYPE = "OneWay";
        final String DEP_AIRPORTS = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String DEP_AIRPORT_CODE_1 = "LAS";
        final String ARR_AIRPORTS = "AllLocation";
        final String ARR_AIRPORT_CODE = "ORD";
        final String ARR_AIRPORT_CODE_1 = "LAX";
        final String DEP_DATE = "5";
        final String ARR_DATE = "NA";
        final String ADULTS = "1";
        final String CHILD = "0";
        final String INFANT_LAP = "0";
        final String INFANT_SEAT = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT = "NonStop";
        final String FARE_TYPE = "Standard";
        final String UPGRADE_VALUE = "BookIt";
        final String FA_URL = "/book/flights";

        //Options Page Constant Value
        final String OPTION_VALUE = "CheckInOption:DecideLater";

        //Payment page constant values
        final String PAYMENT_URL = "spirit.com/book/payment";
        final String CARD_TYPE = "MasterCard";
        final String TRAVEL_GUARD = "NotRequired";

        //Confirmation Page Constant value
        final String BOOKING_STATUS = "Confirmed";
        final String CONFIRMATION_URL = "book/confirmation";

        //open browser
        openBrowser(platform);

//-- Step 1
        /******************************************************************************
         ***************************Navigate to Payment Page********************
         ******************************************************************************/

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        String DEP_CITY_INFO = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText();
        String RET_CITY_INFO = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText();

        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ***************************Validation on Payment Page********************
         ******************************************************************************/
//-- Step 2: Navigate to the Payment Page
        ValidationUtil.validateTestStep("Validating Bags Page  is on the right URL",
                getDriver().getCurrentUrl(), PAYMENT_URL);

//-- Step 3: Within Your Itinerary content block, verify your booking details are reflecting what was selected
        ValidationUtil.validateTestStep("Validating Departure City info",
                pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).getText(), DEP_CITY_INFO);

        ValidationUtil.validateTestStep("Validating Arrival city info",
                pageObjectManager.getPaymentPage().getArriveFlightCityNameText().get(0).getText(), RET_CITY_INFO);

////-- Step 4: Scroll down to the Payment Section - verify Uplift is being offered due to booking being greater than $200 and more than 24hrs out
//        ValidationUtil.validateTestStep("Validate Uplift is visible when booking is up to $200 and up to 24 hours",
//                pageObjectManager.getPaymentPage().getPayMonthlyUpliftLabel().isDisplayed());

//-- Step 5: Navigate to the Total Due pencil banner and click the caret for expansion. Within expanded section, Flight should be the first line with an EDIT link far right aligned.
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

//-- Step 6: Click the EDIT link - user should receive an "Are You Sure?" popup modal.
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getTotalDueFlightEditText().click();

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validate Are you Sure pop up is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getAreYouSurePopUpHeaderText()));

        pageObjectManager.getPaymentPage().getAreYouSurePopUpContinueToFlightButton().click();

//-- Step 7: User should be redirected to book/availability. On the Flight Availability page, select a cheaper flight that's under $200.
        ValidationUtil.validateTestStep("Validating user is on the right URL",
                getDriver().getCurrentUrl(), FA_URL);

        WaitUtil.untilPageLoadComplete(getDriver());
        for (int count = 0; count < pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().size(); count++) {
            if (pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(count).isDisplayed()) {
                JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(count));
            }
        }
//        pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(1).click();

            WaitUtil.untilPageLoadComplete(getDriver());
            pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

            WaitUtil.untilPageLoadComplete(getDriver());
            pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE_1, ARR_AIRPORTS, ARR_AIRPORT_CODE_1);

            pageMethodManager.getHomePageMethods().clickSearchButton();

            /******************************************************************************
             ***************************Navigating back to Payment Page********************
             ******************************************************************************/
            //Flight Availability Methods
            pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
            WaitUtil.untilPageLoadComplete(getDriver());
            pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

            WaitUtil.untilPageLoadComplete(getDriver());
            String DEP_CITY_INFO_1 = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText();
            String RET_CITY_INFO_1 = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText();

            pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
            pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
            pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

            //Customer Info Page Method
            pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
            pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
            pageMethodManager.getPassengerInfoMethods().clickContinueButton();
            WaitUtil.untilPageLoadComplete(getDriver());

            //Bags page
            pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
            WaitUtil.untilPageLoadComplete(getDriver());

            //Seats Page
            pageMethodManager.getSeatsPageMethods().continueWithSeats();
            WaitUtil.untilPageLoadComplete(getDriver());

            //Option Page Methods
            pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
            pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

            /******************************************************************************
             ***************************Validation on Payment Page********************
             ******************************************************************************/
//-- Step 8: Verify flight changes are captured within Your Itinerary content block
            ValidationUtil.validateTestStep("Validating Departure City info",
                    pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).getText(), DEP_CITY_INFO_1);

            ValidationUtil.validateTestStep("Validating Arrival city info",
                    pageObjectManager.getPaymentPage().getArriveFlightCityNameText().get(0).getText(), RET_CITY_INFO_1);

//-- Step 9: Scroll down to the Payment section - verify Uplift is not being offered due to booking being less than $200.
//        ValidationUtil.validateTestStep("Validate Uplift is visible when booking is up to $200 and up to 24 hours",
//                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayMonthlyUpliftLabel()));


//-- Step 10: Select No for Travel Guard. Complete payment using a Credit Card
            pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
            pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        //verify booking is confirmed
//        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
//                getDriver().getCurrentUrl(),CONFIRMATION_URL);
//
//        //verify booking is confirmed
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
        }
    }