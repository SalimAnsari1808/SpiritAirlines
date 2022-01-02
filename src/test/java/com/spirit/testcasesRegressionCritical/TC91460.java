package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91460
//Test Case Task 24724: 35360 CP_CI_Payment Page_FS_use another card option after saving from past booking with res credit
//Created By: Gabriela
//Created On: 17-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-Jul-2019
//**********************************************************************************************

public class TC91460 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn", "OneWay", "DomesticDomestic", "WithIn7Days", "Adult", "FreeSpirit", "NonStop", "Connecting", "BookIt", "NoBags" , "NoSeats" , "CheckInOptions", "Discover", "ReservationCredit" , "PaymentUI","ChangeFlight"})
    public void CP_CI_Payment_Page_FS_use_another_card_option_after_saving_from_past_booking_with_res_credit(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91460 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String LOGIN_ACCOUNT      = "FSSavedCards";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE_2 = "LAX";
        final String DEP_DATE_1         = "0";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String DEP_FLIGHT_1       = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";


        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String PAYMENT_URL        = "/book/payment";
        final String CARD_TYPE          = "DiscoverCard2";
        final String TRAVEL_GUARD       = "NotRequired";

        //reservation page constant value
        final String CHECK_IN_SEAT      = "DontPurchase";
        final String CHECK_IN_BAGS      = "DontPurchase";

        //Billing Page Constant Values
        final String DICOVER_ADDED      = "XXXXXXXXXXXX0084";

        //open browser
        openBrowser(platform);

        /*********************************************************************************************************
         * ******************************************HOME PAGE****************************************************
         *********************************************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//-- Step 1:
        /***********************************RESCREDIT SECTION***************************************************/
        createResCredit();

        /******************************************************************************
         ************************Navigate to Payment Page******************************
         ******************************************************************************/
//-- Step 2
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE_2);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*********************************Passenger Info Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        //Saving Passenger Information for future validation
        List<String> passTitle = new ArrayList<>();
        List<String> passFirstName = new ArrayList<>();
        List<String> passLastName = new ArrayList<>();

        //Storing Pax title info
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().size(); count++) {
            passTitle.add(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(count).getAttribute("value"));
        }

        //Storing Pax First Name List
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().size(); count++) {
            passFirstName.add(pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(count).getAttribute("value"));
        }

        //Pax Last Name List
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().size(); count++) {
            passLastName.add(pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(count).getAttribute("value"));
        }

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /**************************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*********************************Payment Page Methods*************************************************/
        //Validating Payment URL
        ValidationUtil.validateTestStep("User verify Navigated to Payment page",
                getDriver().getCurrentUrl(), PAYMENT_URL);

        //Applying ResCredit
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();

        //Completing payment and booking
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*********************************Confirmation Page Methods*************************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//-- Step 2:
        //Reaching into Check In path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        /*********************************Online Check In Page Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        //Flight modified
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

        /**************************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT_1);

        //Recording Departing Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityName = new ArrayList<>();
        List<String> arrCityName = new ArrayList<>();
        List<String> depTime = new ArrayList<>();
        List<String> arrTime = new ArrayList<>();

        //Storing Departure Cities Name for 1st journey
        for (WebElement depCity : pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText()) {
            depCityName.add(depCity.getText().trim());
        }

        //Storing Arrival Cities Name for 1st journey
        for (WebElement arrcity : pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText()) {
            arrCityName.add(arrcity.getText().trim());
        }

        //Storing Departure Cities Time for 1st journey
        for (WebElement depTim : pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText()) {
            depTime.add(depTim.getText().trim());
        }

        //Storing Arrival Cities Time for 1st journey
        for (WebElement arTime : pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText()) {
            arrTime.add(arTime.getText().trim());
        }

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /**************************************Bags Page Methods*************************************************/
        //No Bags
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(CHECK_IN_BAGS);

        /*********************************Seats Page Methods*************************************************/
        //No seats
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(CHECK_IN_SEAT);

        /*********************************Options Page Methods*************************************************/
        //No extras
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*********************************Payment Page Methods*************************************************/
//-- Step 3 and 4
        //Validating Departing and Arrival Flight Information
        //Recording Dep and Arr info for validation
        String depCityNamePayment = pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).getText();
        String arrCityNamePayment = pageObjectManager.getPaymentPage().getArriveFlightCityNameText().get(0).getText();

        //Validating Dep City info
        ValidationUtil.validateTestStep("Validating right departing city displayed on the original itinerary section",
                depCityNamePayment, depCityName.get(0) + " " + depTime.get(0));

        //Validating Arr City  Info
        ValidationUtil.validateTestStep("Validating right Arrival displayed on the original itinerary section",
                arrCityNamePayment, arrCityName.get(0) + " " + arrTime.get(0));

        //Validating Passenger Info
        for (int count = 0; count < pageObjectManager.getPaymentPage().getPassengerNameText().size(); count++) {
            ValidationUtil.validateTestStep("Validating Pass information displayed properly", pageObjectManager.getPaymentPage().getPassengerNameText().get(count).getText(), passTitle.get(0) + ". " + passFirstName.get(0) + " " + passLastName.get(0));
        }

        //No Travel Guard
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

//-- Step 5
        pageObjectManager.getPaymentPage().getSelectCardDropDown().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating 'Update Credit Card' link is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPaymentSectionUpdateCreditCardLink()));

//-- Step 6
        //Paying with a different credit card
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPaymentPage().getSelectCardDropDown(), "0: Other");

//-- Step 7
        //Filling credit card information
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);

        //Saving new credit card for future booking
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getPaymentSectionSaveCardCLabel().click();


//-- Step 8
        //Completing the booking
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//--Step 9
        //Going to 'My Account' page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getUserDropDown().click();
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getHeader().getMyAccountUserLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        /*********************************Account Profile Methods*************************************************/
//Step 10
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        //Going to 'Bulling info' page
        pageObjectManager.getAccountProfilePage().getLeftMenuBillingInformationLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 11
        //Validating credit card was added and Deleting credit card recently added
        List<WebElement> deleteButton = pageObjectManager.getAccountProfilePage().getBillingInformationAdditionalCardDeleteLink();
        List<WebElement> cardNumber = pageObjectManager.getAccountProfilePage().getBillingInformationAdditionalCardNumberText();

        for (int count = 0; count < cardNumber.size(); count++) {
            if (cardNumber.get(count).getText().equals(DICOVER_ADDED)) {
                deleteButton.get(count).click();
                WaitUtil.untilPageLoadComplete(getDriver());
                pageObjectManager.getAccountProfilePage().getDeleteCardPopupDeleteCardButton().click();
            }
        }
    }

    public void createResCredit()
    {
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_1";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String CARD_TYPE          = "DiscoverCard2";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser

//-- Step 1:
        /*********************************************************************************************************
         * ***********************************RESCREDIT SECTION***************************************************
         *********************************************************************************************************/
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seats page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
    }
}