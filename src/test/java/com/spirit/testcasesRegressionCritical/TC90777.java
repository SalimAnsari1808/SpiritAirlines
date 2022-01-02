package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90777
//Test Case Name: Task 24730: 35327 CP_CI_Payment Page_Your Itinerary
//Created By: Gabriela
//Created On: 19-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-Jul-2019
//**********************************************************************************************

public class TC90777 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "Visa" , "CheckInOptions" , "PaymentUI"})
    public void CP_CI_Payment_Page_Your_Itinerary(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90777 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "5";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String PAYMENT_URL        = "/book/payment";
        final String CI_PAYMENT_URL     = "/check-in/payment";
        final String PAGE_HEADER        = "Payment";
        final String PAGE_SUB_HEADER    = "You've assembled an awesome trip. Let's book it.";
        final String NEW_FLIGHT         = "Your New Flight";
        final String ORIGINAL_ITINERARY = "Original Itinerary";

        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //checking constant value
        final String CHECK_IN_SEAT      = "DontPurchase";
        final String CHECK_IN_BAGS      = "DontPurchase";

        //open browser
        openBrowser(platform);

//--Step 1
        /*********************************************************************************************************
         * ******************************************HOME PAGE****************************************************
         *********************************************************************************************************/
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

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityName    = new ArrayList<>();
        List<String> arrCityName    = new ArrayList<>();
        List<String> depTime        = new ArrayList<>();
        List<String> arrTime        = new ArrayList<>();
        List<String> nkInfo         = new ArrayList<>();

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

        //Storing NK Number for 1st journey
        for (WebElement nkInformation: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText()){
            nkInfo.add(nkInformation.getText().trim());
        }

        //Closing Flight Info Pop Up
        JSExecuteUtil.clickOnElement(getDriver() , pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton());
        WaitUtil.untilTimeCompleted(1000);

        //Recording Returning Journey Information
        JSExecuteUtil.clickOnElement(getDriver() , pageObjectManager.getFlightAvailabilityPage().getSelectedReturningFlightNatureButton().get(0));
        WaitUtil.untilTimeCompleted(1000);

        //Storing Departure Cities Name for 2nd journey
        for(WebElement depCity:pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText()){
            depCityName.add(depCity.getText().trim());
        }

        //Storing Arrival Cities Name for 2nd journey
        for(WebElement arrcity: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText()){
            arrCityName.add(arrcity.getText().trim());
        }

        //Storing Departure Cities Time for 2nd journey
        for(WebElement depTim: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText()){
            depTime.add(depTim.getText().trim());
        }

        //Storing Arrival Cities Time for 2nd journey
        for (WebElement arTime: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText()){
            arrTime.add(arTime.getText().trim());
        }

        //Storing NK Number for 2nd journey
        for (WebElement nkInformation: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText()){
            nkInfo.add(nkInformation.getText().trim());
        }

        //Closing Flight Info Pop Up
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton());

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        /*********************************Passenger Info Methods*************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        // Pax info
        List<String> passTitle      = new ArrayList<>();
        List<String> passFirstName  = new ArrayList<>();
        List<String> passLastName   = new ArrayList<>();

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
        //Validating Payment URL
        ValidationUtil.validateTestStep("User verify Navigated to Payment page",
                getDriver().getCurrentUrl(),PAYMENT_URL);

        //Completing payment and booking
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*********************************Confirmation Page Methods*************************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Reaching into Check In path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

//-- Step 2
        /*********************************Online Check In Page Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep","NA","NA","3");
        pageMethodManager.getReservationSummaryPageMethods().continueCancelOnChangeFlightPopup("Continue");

        /**************************************Flight Availability Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        //Recording Departing Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityNameNew = new ArrayList<>();
        List<String> arrCityNameNew = new ArrayList<>();
        List<String> depTimeNew     = new ArrayList<>();
        List<String> arrTimeNew     = new ArrayList<>();
        List<String> nkInfoNew      = new ArrayList<>();

        //Storing Flight duration
        String durationInfoNew      = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightDurationTimeText().get(0).getText();

        //Storing Departure Cities Name for 1st journey
        for(WebElement depCity:pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText()){
            depCityNameNew.add(depCity.getText().trim());
        }

        //Storing Arrival Cities Name for 1st journey
        for(WebElement arrcity: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText()){
            arrCityNameNew.add(arrcity.getText().trim());
        }

        //Storing Departure Cities Time for 1st journey
        for(WebElement depTim: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText()){
            depTimeNew.add(depTim.getText().trim());
        }

        //Storing Arrival Cities Time for 1st journey
        for (WebElement arTime: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText()){
            arrTimeNew.add(arTime.getText().trim());
        }

        //Storing NK Number for 1st journey
        for (WebElement nkInformation: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText()){
            nkInfoNew.add(nkInformation.getText().trim());
        }

        //Closing Flight Info Pop Up
        WaitUtil.untilTimeCompleted(1000);
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton());
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //No Bags
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(CHECK_IN_BAGS);

        //No Seats
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(CHECK_IN_SEAT);

        WaitUtil.untilPageLoadComplete(getDriver());

        /*********************************Options Page Methods*************************************************/
        pageObjectManager.getOptionsPage().getContinueButtonOnCheckInPathButton().click();

        /*********************************Payment Page Methods*************************************************/
        //-- Step 1:
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL",
                getDriver().getCurrentUrl(), CI_PAYMENT_URL);

//-- Step 3
        //Validating Page title
        ValidationUtil.validateTestStep("Validating right info displayed on the Header Page Title",
                pageObjectManager.getPaymentPage().getPaymentHeaderText().getText(),PAGE_HEADER);

        ValidationUtil.validateTestStep("Validating verbiage under page title",
                pageObjectManager.getPaymentPage().getPaymentSubHeaderText().getText(),PAGE_SUB_HEADER);

//-- Step 4
        ValidationUtil.validateTestStep("Validating Your New Flight header displaying the right info",
                pageObjectManager.getPaymentPage().getYourNewFlightText().getText(),NEW_FLIGHT);

//-- Step 5
        //Storing flight info for validation
        List<String> depCityNamePaymentNew = getDisplayedText(pageObjectManager.getPaymentPage().getDepartingFlightCityNameText());
        List<String> arrCityNamePaymentNew = getDisplayedText(pageObjectManager.getPaymentPage().getArriveFlightCityNameText());

        //Validating departing flight info

        ValidationUtil.validateTestStep("Validating Cities and Time ",
                depCityNamePaymentNew.get(0), depCityNameNew.get(0) + " " + depTimeNew.get(0));

        //Validating Arrival info displayed
        ValidationUtil.validateTestStep("Validating Cities and Time Arriving",
                arrCityNamePaymentNew.get(0), arrCityNameNew.get(0) + " " + arrTimeNew.get(0));

//-- Step 6
        //Itinerary section collapsed
        ValidationUtil.validateTestStep("Validating Itinerary is collapsed",
                pageObjectManager.getPaymentPage().getYourItineraryArrowIcon().get(0).getAttribute("style").contains("transform: rotate(-180deg)"));

        //Expanding Itinerary section
        pageObjectManager.getPaymentPage().getYourItineraryArrowIcon().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        //Validating Section Expanded
        ValidationUtil.validateTestStep("Validating Itinerary is collapsed",
                pageObjectManager.getPaymentPage().getYourItineraryArrowIcon().get(0).getAttribute("style").contains("transform: rotate(0deg)"));

        //Validating Passenger Info
        for (int count = 0; count < pageObjectManager.getPaymentPage().getPassengerNameText().size(); count++) {
            ValidationUtil.validateTestStep("Validating Pass Info displayed properly",
                    pageObjectManager.getPaymentPage().getPassengerNameText().get(count).getText(), passTitle.get(0) + ". " + passFirstName.get(0) + " " + passLastName.get(0));
        }

        //Storing Flight NUmber information foe validation
        List<String> nkNew = getDisplayedText(pageObjectManager.getPaymentPage().getFlightSectionFlightNumberText());
        for (int count = 0; count < 1; count ++) {
            ValidationUtil.validateTestStep("Validating NK time right information",
                    nkInfoNew.get(count).replace("Flight ","").replace(" ",""),nkNew.get(count).substring(nkNew.get(count).indexOf(":")+2).replace(" ",""));
        }

        ValidationUtil.validateTestStep("Validating right Duration ifo is displayed ",
                pageObjectManager.getPaymentPage().getDurationTimeText().get(0).getText(),durationInfoNew);

//-- Step 7
        ValidationUtil.validateTestStep("Validating Your New Flight header displaying the right info",
                pageObjectManager.getPaymentPage().getOriginalItineraryText().getText(),ORIGINAL_ITINERARY);

        pageObjectManager.getPaymentPage().getYourItineraryArrowIcon().get(2).click();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 8
        //Original Itinerary Section
        //Recording Dep and Arr info for validation on Original Itinerary section
        List<String>  depCityNamePayment = getDisplayedText(pageObjectManager.getPaymentPage().getOriginalItineraryDepartingFlightCityNameText());
        List<String>  arrCityNamePayment = getDisplayedText(pageObjectManager.getPaymentPage().getOriginalItineraryArriveFlightCityNameText());

        //Validating Dep City info
        for (int count = 0; count < depCityNamePayment.size(); count ++) {
            ValidationUtil.validateTestStep("Validating right departing city displayed on the original itinerary section",
                    depCityNamePayment.get(count), depCityName.get(count) + " " + depTime.get(count));
        }
        //Validating Arr City  Info
        for (int count = 0; count < arrCityNamePayment.size(); count++) {
            ValidationUtil.validateTestStep("Validating right Arrival displayed on the original itinerary section",
                    arrCityNamePayment.get(count), arrCityName.get(count) + " " + arrTime.get(count));
        }
    }

    private List<String> getDisplayedText(List<WebElement> elementList){

        ArrayList<String> arrayList = new ArrayList<>();//empty

        for (WebElement element:elementList) {
            if(element.isDisplayed()){

                arrayList.add(element.getText().trim());
            }
        }
        return arrayList;
    }
}