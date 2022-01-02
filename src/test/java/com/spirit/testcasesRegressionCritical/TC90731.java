package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90731
//Test Case Task 24772: 35332 CP_MT_Payment Page_Your Itinerary Content Block
//Created By: Gabriela
//Created On: 16-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 17-Jul-2019
//**********************************************************************************************

public class TC90731 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "PaymentUI","ChangeFlight"})
    public void CP_MT_Payment_Page_Your_Itinerary_Content_Block(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90731 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        // Home Page Constant Values
        final String LANGUAGE           = "English";
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

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String PAYMENT_URL        = "my-trips/payment";
        final String MASTER_CARD        = "MasterCard";
        final String TRAVEL_GUARD 		= "NotRequired";
        final String PAGE_HEADER        = "Payment";
        final String NEW_FLIGHT         = "Your New Flight";
        final String ORIGINAL_ITINERARY = "Original Itinerary";

        //Reservation Summary constant value
        final String BAGS_SEAT          = "DontPurchase";

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

        WaitUtil.untilTimeCompleted(1200);
        //Recording Departing Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(2000);
        List<String> depCityName    = new ArrayList<>();
        List<String> arrCityName    = new ArrayList<>();
        List<String> depTime        = new ArrayList<>();
        List<String> arrTime        = new ArrayList<>();

        //Storing Departure Cities Name for 1st journey
        depCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText().trim());

        //Storing Arrival Cities Name for 1st journey
        arrCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText().trim());

        //Storing Departure Cities Time for 1st journey
        depTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText().get(0).getText().trim());

        //Storing Arrival Cities Time for 1st journey
        arrTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText().get(0).getText().trim());

        //Closing Flight Info Pop Up
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);


        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*********************************Passenger Info Methods*************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
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
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(MASTER_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().travelGuardRecommendedPopUp();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*********************************Confirmation Page Methods*************************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        /*********************************My Trips Page Methods*************************************************/
        //-- Step 2
        //Flight modified
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);

        //Recording Departing Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityNameNew     = new ArrayList<>();
        List<String> arrCityNameNew     = new ArrayList<>();
        List<String> depTimeNew         = new ArrayList<>();
        List<String> arrTimeNew         = new ArrayList<>();
        List<String> nkInfo             = new ArrayList<>();

        //Storing Flight duration
        String durationInfo = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightDurationTimeText().get(0).getText();

        //Storing Departure Cities Name for 1st journey
        depCityNameNew.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText().trim());

        //Storing Arrival Cities Name for 1st journey
        arrCityNameNew.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText().trim());

        //Storing Departure Cities Time for 1st journey
        depTimeNew.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText().get(0).getText().trim());

        //Storing Arrival Cities Time for 1st journey
        arrTimeNew.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText().get(0).getText().trim());

        //Storing NK Number for 1st journey
        nkInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().replace("Flight ","").replace(" ",""));

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //No Bags
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(BAGS_SEAT);

        //No seats
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(BAGS_SEAT);

        //No Options
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*********************************Payment Page Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 3:
        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL", getDriver().getCurrentUrl(), PAYMENT_URL);

        ValidationUtil.validateTestStep("Validating right information displayed on the Header Page Title",
                pageObjectManager.getPaymentPage().getPaymentHeaderText().getText(),PAGE_HEADER);

//-- Step 4:
        ValidationUtil.validateTestStep("Validating Your New Flight header displaying the right info",
                pageObjectManager.getPaymentPage().getYourNewFlightText().getText(),NEW_FLIGHT);

//-- step 5:
        //Storing flight info for validation
        List<String> depCityNamePaymentNew = getDisplayedText(pageObjectManager.getPaymentPage().getDepartingFlightCityNameText());
        List<String> arrCityNamePaymentNew = getDisplayedText(pageObjectManager.getPaymentPage().getArriveFlightCityNameText());

        //Validating departing flight info
        ValidationUtil.validateTestStep("Validating Cities and Time ", depCityNamePaymentNew.get(0), depCityNameNew.get(0) + " " + depTimeNew.get(0));

        //Validating Arrival info displayed
        ValidationUtil.validateTestStep("Validating Cities and Time Arriving", arrCityNamePaymentNew.get(0), arrCityNameNew.get(0) + " " + arrTimeNew.get(0));

//-- step 6:
        //Click on Flight details chevron
        pageObjectManager.getPaymentPage().getYourItineraryArrowIcon().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        //Storing Flight NUmber information foe validation
        List<String> nk = getDisplayedText(pageObjectManager.getPaymentPage().getFlightSectionFlightNumberText());

        ValidationUtil.validateTestStep("Validating NK time right information", nk.get(0),nkInfo.get(0));

        ValidationUtil.validateTestStep("Validating right Duration ifo is displayed ",
                pageObjectManager.getPaymentPage().getDurationTimeText().get(0).getText(),durationInfo);

//-- Step 7:
        ValidationUtil.validateTestStep("Validating Your New Flight header displaying the right info",
                pageObjectManager.getPaymentPage().getOriginalItineraryText().getText(),ORIGINAL_ITINERARY);

        pageObjectManager.getPaymentPage().getYourItineraryArrowIcon().get(2).click();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 8:
        //Recording Dep and Arr info for validation
        String depCityNamePayment = pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(2).getText();
        String arrCityNamePayment = pageObjectManager.getPaymentPage().getArriveFlightCityNameText().get(2).getText();

        //Validating Dep City info
        ValidationUtil.validateTestStep("Validating right departing city displayed on the original itinerary section",
                depCityNamePayment, depCityName.get(0) + " " + depTime.get(0));

        //Validating Arr City  Info
        ValidationUtil.validateTestStep("Validating right Arrival displayed on the original itinerary section",
                arrCityNamePayment, arrCityName.get(0) + " " + arrTime.get(0));

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