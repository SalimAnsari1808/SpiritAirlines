package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90738
//Test Case Name: Task 24695: 35325 CP_BP_Payment Page_Your Itinerary Content Block
//Created By: Gabriela
//Created On: 18-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-Jul-2019
//**********************************************************************************************

public class TC90738 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "PaymentUI"})
    public void CP_BP_Payment_Page_Your_Itinerary_Content_Block(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90738 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "6";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String PAYMENT_URL        = "/book/payment";
        final String YOUR_ITINERARY     = "Your Itinerary";
        final String PAGE_HEADER        = "Payment";
        final String PAGE_SUB_HEADER    = "You've assembled an awesome trip. Let's book it.";
        final String NEXT_DAY           = "(Next Day)";

        //open browser
        openBrowser(platform);

//-- Step 1
        /*********************************************************************************************************
         * ************************************NAVIGATE TO PAYMENT PAGE*******************************************
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

        //Recording Layover info for future validation
        List<String> layoverInfo    = new ArrayList<>();
        for (int count = 0; count < pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().size(); count++) {
            layoverInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().get(count).getText().replace(" LAYOVER", ""));
        }

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityName    = new ArrayList<>();
        List<String> arrCityName    = new ArrayList<>();
        List<String> depTime        = new ArrayList<>();
        List<String> arrTime        = new ArrayList<>();
        List<String> nkInfo         = new ArrayList<>();

        //Storing Flight duration
        String durationInfo         = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightDurationTimeText().get(0).getText();

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

        nkInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().replace("Flight ", "").replace(" ", ""));

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //Recording Returning Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSelectedReturningFlightNatureButton().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

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
        nkInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().replace("Flight ", "").replace(" ", ""));

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

        //Storing Flight Date Information
        String FlightDate = pageObjectManager.getFlightAvailabilityPage().getSelectedDepDateText().getText();
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
        WaitUtil.untilPageLoadComplete(getDriver());
        //Validating Payment URL
        ValidationUtil.validateTestStep("User verify Navigated to Payment page", getDriver().getCurrentUrl(),PAYMENT_URL);

//-- Step 2
        //Validating Page title
        ValidationUtil.validateTestStep("Validating right info displayed on the Header Page Title",
                pageObjectManager.getPaymentPage().getPaymentHeaderText().getText(),PAGE_HEADER);

        ValidationUtil.validateTestStep("Validating verbiage under page title",
                pageObjectManager.getPaymentPage().getPaymentSubHeaderText().getText(),PAGE_SUB_HEADER);

//-- Step 3
        //'Your Itinerary' content block
        ValidationUtil.validateTestStep("Validating 'Your Itinerary' content block is displayed",
                pageObjectManager.getPaymentPage().getYourItineraryText().getText(),YOUR_ITINERARY);

//-- Step 4
        //plane icon validation
        ValidationUtil.validateTestStep("Validating plane icon displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPrimaryPlaneImage()));

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

//-- Step 5
        //Clicking on the caret dropdown
        pageObjectManager.getPaymentPage().getYourItineraryArrowIcon().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 6
        //Flight Number information for validation
        ValidationUtil.validateTestStep("Validating NK time right information", getDisplayedText(pageObjectManager.getPaymentPage().getFlightSectionFlightNumberText()).get(0),nkInfo.get(0));

        ValidationUtil.validateTestStep("Validating right Duration ifo is displayed ", pageObjectManager.getPaymentPage().getDurationTimeText().get(0).getText(),durationInfo);

//-- Step 7
        ValidationUtil.validateTestStep("Validating Next Day is displayed when applicable", arrCityNamePaymentNew.get(2),NEXT_DAY);

//-- Step 8
        //Validating right layover information is displayed
        List<String> layoverInfoPayment = getDisplayedText(pageObjectManager.getPaymentPage().getLayoverTimeText());

        for (int count = 0; count < layoverInfoPayment.size(); count++) {
            ValidationUtil.validateTestStep("", layoverInfoPayment.get(count).toUpperCase(), layoverInfo.get(count).toUpperCase());
        }

//-Step 9, 10 and 11 are not applicable. (Multi - City)
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