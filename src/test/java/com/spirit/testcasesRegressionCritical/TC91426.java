package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91426
//Test Case Name: Task 24678: 35347 CP_BP_Payment Page_Itinerary_RT Layover_Validate EDIT Options link
//Created By: Gabriela
//Created On: 16-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 16-Jul-2019
//**********************************************************************************************

public class TC91426 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" ,"NonStop", "BookIt" , "NoBags" , "NoSeats" ,"FlightFlex", "CheckInOptions" , "MasterCard" , "PaymentUI"})
    public void CP_BP_Payment_Page_Itinerary_RT_Layover_Validate_EDIT_Options_link(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91426 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "5";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String RET_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        final String FA_URL             = "/book/flights";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";
        final String OPTIONS_URL        = "/book/options";
        final String OPTIONS_VALUE_1    = "FlightFlex|CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String PAYMENT_URL        = "/book/payment";
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_TYPE          = "MasterCard";

        //Confirmation Page Constant Values
        final String CONFIRMATION_URL   = "/book/confirmation";

        //open browser
        openBrowser(platform);
//--Step 1
        /****************************************************************************
         * *********************Navigate to Payment Page ****************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

        //Recording Departing Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();
        WaitUtil.untilTimeCompleted(2500);

        List<String> layoverInfo    = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().size(); i++) {
            layoverInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().get(i).getText().replace(" LAYOVER", ""));
        }

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityName    = new ArrayList<>();
        List<String> arrCityName    = new ArrayList<>();
        List<String> depTime        = new ArrayList<>();
        List<String> arrTime        = new ArrayList<>();

        //Storing Departure Cities Name for 1st journey
        for(WebElement depCity:pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText()){
            depCityName.add(depCity.getText().trim());
        }

        //Storing Arrival Cities Name for 1st journey
        for(WebElement arrcity: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText()){
            arrCityName.add(arrcity.getText().trim());
        }

        //Storing Departure Cities Time for 1st journey
        for(WebElement depTim: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText()){
            depTime.add(depTim.getText().trim());
        }

        //Storing Arrival Cities Time for 1st journey
        for (WebElement arTime: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText()){
            arrTime.add(arTime.getText().trim());
        }

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //Recording Returning Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSelectedReturningFlightNatureButton().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        for (int count = 0; count < pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().size(); count++) {
            layoverInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().get(count).getText().replace(" LAYOVER", ""));
        }

        //Storing Departure Cities Name for 1st journey
        for(WebElement depCity:pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText()){
            depCityName.add(depCity.getText().trim());
        }

        //Storing Arrival Cities Name for 1st journey
        for(WebElement arrcity: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText()){
            arrCityName.add(arrcity.getText().trim());
        }

        //Storing Departure Cities Time for 1st journey
        for(WebElement depTim: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText()){
            depTime.add(depTim.getText().trim());
        }

        //Storing Arrival Cities Time for 1st journey
        for (WebElement arTime: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText()){
            arrTime.add(arTime.getText().trim());
        }

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*********************************Passenger Information Methods*************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        List<String> passTitle      = new ArrayList<>();
        List<String> passFirstName  = new ArrayList<>();
        List<String> passLastName   = new ArrayList<>();

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

        /*********************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Validations ****************************
         ****************************************************************************/
//-- Step 2:
        WaitUtil.untilPageLoadComplete(getDriver());

        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL", getDriver().getCurrentUrl(), PAYMENT_URL);
//-- Step 3:
        //Recording Dep and Arr info for validation
        List<String> depCityNamePayment     = getDisplayedText(pageObjectManager.getPaymentPage().getDepartingFlightCityNameText());
        List<String> arrCityNamePayment     = getDisplayedText(pageObjectManager.getPaymentPage().getArriveFlightCityNameText());

        //Validating Dep City info
        for (int count = 0; count < depCityNamePayment.size(); count++) {
            ValidationUtil.validateTestStep("Validating Cities and Time Departing",
                    depCityNamePayment.get(count), depCityName.get(count) + " " + depTime.get(count));
        }

        //Validating Arr City  Info
        for (int count = 0; count < arrCityNamePayment.size(); count++) {
            ValidationUtil.validateTestStep("Validating Cities and Time Arriving",
                    arrCityNamePayment.get(count), arrCityName.get(count) + " " + arrTime.get(count));
        }

        //Validating Passenger Info
        for (int count = 0; count < pageObjectManager.getPaymentPage().getPassengerNameText().size(); count++) {
            ValidationUtil.validateTestStep("Validating Pass Info displayed properly",
                    pageObjectManager.getPaymentPage().getPassengerNameText().get(count).getText(),
                    passTitle.get(count) + ". " + passFirstName.get(count) + " " + passLastName.get(count));
        }

        //Validating right layover information is displayed
        List<String> layoverInfoPayment = getDisplayedText(pageObjectManager.getPaymentPage().getLayoverTimeText());
        for (int count = 0; count < layoverInfoPayment.size(); count++) {
            ValidationUtil.validateTestStep("validating right layover information is displayed",
                    layoverInfo.get(count).toUpperCase() , layoverInfoPayment.get(count).toUpperCase());
        }

//-- Step 4:
        //Total Due break Down section expanded
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Options sections is displayed in the fourth position",
                pageObjectManager.getPaymentPage().getTotalDueBreakDownText().get(3).getText(),
                pageObjectManager.getPaymentPage().getTotalDueOptionsText().getText().toUpperCase());

        /*********************************Options Page Methods*************************************************/
//-- Step 5:
        pageObjectManager.getPaymentPage().getTotalDueOptionsEditText().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //URL Validation
        ValidationUtil.validateTestStep("Validating Options Page URL", getDriver().getCurrentUrl(), OPTIONS_URL);

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE_1);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*********************************Payment Page Methods*************************************************/
//--Step 6:
        WaitUtil.untilPageLoadComplete(getDriver());

        //Total Due break Down section expanded
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 7:
        pageMethodManager.getPaymentPageMethods().verifyTotalDueOptions();

//-- Step 8:
        //Selecting 'No' for TG and completing the booking
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Method
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Confirmation URL Validation
        ValidationUtil.validateTestStep("Validating Confirmation Page URL", getDriver().getCurrentUrl(), CONFIRMATION_URL);
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
}