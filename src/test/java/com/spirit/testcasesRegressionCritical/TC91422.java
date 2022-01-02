package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91422
//Test Case Name: Task 24694: 35357 CP_BP_Payment Page_Voucher_Validating Error Messages
//Created By: Gabriela
//Created On: 18-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-Jul-2019
//**********************************************************************************************

public class TC91422 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "Voucher" , "PaymentUI"})
    public void CP_BP_Payment_Page_Voucher_Validating_Error_Messages(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91422 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAX";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
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


        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String PAYMENT_URL        = "/book/payment";
        final String ALPHANUMERIC       = "!@#$%^987YHG";
        final String ALPHANUMERIC_ERROR = "Please enter valid voucher number.";
        final String WRONG_VOUCHER      = "65498732175395136";
        final String OTHER_VOUCHER      = "654987321753951";
        final String INPUT_ERROR        = "Please enter a valid input";

        //open browser
        openBrowser(platform);

        /*********************************************************************************************************
         * ******************************************HOME PAGE****************************************************
         *********************************************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Create Voucher
        createVoucher();

//-- Step 1:
        /******************************************************************************
         ************************Navigate to Payment Page******************************
         ******************************************************************************/
        //Home Page Methods
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        /*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);

        //Recording Departing Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityName = new ArrayList<>();
        List<String> arrCityName = new ArrayList<>();
        List<String> depTime = new ArrayList<>();
        List<String> arrTime = new ArrayList<>();
        List<String> nkInfo = new ArrayList<>();

        //Storing Departure Cities Name for 1st journey
        depCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText().trim());

        //Storing Arrival Cities Name for 1st journey
        arrCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText().trim());

        //Storing Departure Cities Time for 1st journey
        depTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText().get(0).getText().trim());

        //Storing Arrival Cities Time for 1st journey
        arrTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText().get(0).getText().trim());

        //Storing NK Number for 1st journey
        nkInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().replace("Flight ", "").replace(" ", ""));

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //Storing Flight Date Information
        String FlightDate = pageObjectManager.getFlightAvailabilityPage().getSelectedDepDateText().getText();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        /*********************************Passenger Info Methods*************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        // Pax info
        List<String> passTitle = new ArrayList<>();
        List<String> passFirstName = new ArrayList<>();
        List<String> passLastName = new ArrayList<>();

        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().size(); count++) {
            passTitle.add(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(count).getAttribute("value"));
        }

        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().size(); count++) {
            passFirstName.add(pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(count).getAttribute("value"));
        }

        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().size(); count++) {
            passLastName.add(pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(count).getAttribute("value"));
        }

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /**************************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*********************************Payment Page Methods*************************************************/
//--Step 2
        //Verifying booking Date Info
        String departingDateOnPayment = null;
        for (WebElement element : pageObjectManager.getPaymentPage().getDepartDateText())
        {
            if (TestUtil.verifyElementDisplayed(element)) {
                departingDateOnPayment = element.getText();
                break;
            }
        }

        ValidationUtil.validateTestStep("Verify Date on itinerary section",
                FlightDate.substring(FlightDate.indexOf(",") + 2) , departingDateOnPayment);

        //Storing flight info for validation
        List<String> depCityNamePaymentNew = getDisplayedText(pageObjectManager.getPaymentPage().getDepartingFlightCityNameText());
        List<String> arrCityNamePaymentNew = getDisplayedText(pageObjectManager.getPaymentPage().getArriveFlightCityNameText());

        //Validating departing flight info
        for (int count = 0;count < depCityNamePaymentNew.size(); count++) {
            ValidationUtil.validateTestStep("Validating Cities and Time ", depCityNamePaymentNew.get(count), depCityName.get(count) + " " + depTime.get(count));
        }

        //Validating Arrival info displayed
        for (int count = 0; count < arrCityNamePaymentNew.size(); count++) {
            ValidationUtil.validateTestStep("Validating Cities and Time Arriving", arrCityNamePaymentNew.get(count), arrCityName.get(count) + " " + arrTime.get(count));
        }

        //Validating Passenger info
        for (int count = 0; count < pageObjectManager.getPaymentPage().getPassengerNameText().size(); count++) {
            ValidationUtil.validateTestStep("Validating Pass Info displayed properly",
                    pageObjectManager.getPaymentPage().getPassengerNameText().get(count).getText(), passTitle.get(count) + ". " + passFirstName.get(count) + " " + passLastName.get(count));
        }

//-- Step 3:
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditLink().click();

//-- Step 4
        //Typing alphanumeric characters
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys(ALPHANUMERIC);
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating error message",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ALPHANUMERIC_ERROR);

        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 5
        //Typing a not existing voucher
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys(WRONG_VOUCHER);
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating red iblock message displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCommon().getIBlockVerbiageText()));

        pageObjectManager.getCommon().getIBlockCloseButton().click();

        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 6
        //Typing less than 17 digit
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys(OTHER_VOUCHER);
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating error message",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ALPHANUMERIC_ERROR);

        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 7
        //Typing the right voucher
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys(scenarioContext.getContext(Context.RESERVATION_VOUCHER_CODE).toString());

        //Clicking on Go Button
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 8
        //Validating out of range error message id displayed
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToApplyTextBox().clear();
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToApplyTextBox().sendKeys("999");

        pageObjectManager.getPaymentPage().getVoucherNumberApplyButton().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Invalid range error message is displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),INPUT_ERROR);
    }

    private List<String> getDisplayedText(List<WebElement> elementList) {

        ArrayList<String> arrayList = new ArrayList<>();//empty

        for (WebElement element : elementList) {
            if (element.isDisplayed()) {

                arrayList.add(element.getText().trim());
            }
        }
        return arrayList;
    }

    public void createVoucher()
    {
        //Home Page Constant variables
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAX";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
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


        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        /*********************************************************************************************************
         * *************************************VOUCHER SECTION***************************************************
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
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

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