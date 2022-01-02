package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91415
//Test Case Name: Task 24680: 35349 CP_BP_Payment Page_Itinerary_RT_Validate EDIT Flight link on payment page
//Description: Validating Itinerary/PAX/EDIT link/bags/Uplift/Travel Guard
//Created By: Gabriela
//Created On: 15-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 16-Jul-2019
//**********************************************************************************************

public class TC91415 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "CarryOn" , "CheckedBags" , "NoSeats" ,"ShortCutBoarding", "CheckInOptions" , "MasterCard" ,"TravelInsuranceBlock", "PaymentUI"})
    public void CP_BP_Payment_Page_Itinerary_RT_Validate_EDIT_Flight_link_on_payment_page(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91415 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "5";
        final String ADULT              = "9";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        final String FA_URL             = "/book/flights";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5";
        final String RET_BAGS           = "Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5||Carry_1|Checked_5";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String PAYMENT_URL        = "/book/payment";
        final String HEADER             = "Flight";
        final String TRAVEL_GUARD 		= "Required";
        final String CARD_TYPE          = "MasterCard";

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

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
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
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //Recording Returning Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSelectedReturningFlightNatureButton().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        //Storing Departure Cities Name for 1st journey
        depCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText().trim());

        //Storing Arrival Cities Name for 1st journey
        arrCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText().trim());

        //Storing Departure Cities Time for 1st journey
        depTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText().get(0).getText().trim());

        //Storing Arrival Cities Time for 1st journey
        arrTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText().get(0).getText().trim());

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

        //Storing Flight Date Information
        String FlightDate = pageObjectManager.getFlightAvailabilityPage().getSelectedDepDateText().getText();

        //Continue to Passenger Info Page
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        List<String> passTitle      = new ArrayList<>();
        List<String> passFirstName  = new ArrayList<>();
        List<String> passLastName   = new ArrayList<>();

        /*********************************Passenger Information Methods*************************************************/
        //Storing Pax title info
        for (int i = 0; i < pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().size(); i++) {
            passTitle.add(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(i).getAttribute("value"));
        }

        //Storing Pax First Name List
        for (int i = 0; i < pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().size(); i ++) {
            passFirstName.add(pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(i).getAttribute("value"));
        }

        //Pax Last Name List
        for (int i = 0; i < pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().size(); i ++) {
            passLastName.add(pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(i).getAttribute("value"));
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

        //Recording Dep and Arr info for validation
        List<String> depCityNamePayment = getDisplayedText(pageObjectManager.getPaymentPage().getDepartingFlightCityNameText());
        List<String> arrCityNamePayment = getDisplayedText(pageObjectManager.getPaymentPage().getArriveFlightCityNameText());

        //Validating Dep City info
        for (int count = 0; count < depCityNamePayment.size(); count ++) {
            ValidationUtil.validateTestStep("Validating Cities and Time Departing", depCityNamePayment.get(count), depCityName.get(count) + " " + depTime.get(count));
        }

        //Validating Arr City  Info
        for (int count = 0; count < arrCityNamePayment.size(); count ++) {
            ValidationUtil.validateTestStep("Validating Cities and Time Arriving",
                    arrCityNamePayment.get(count), arrCityName.get(count) + " " + arrTime.get(count));
        }

        //Validating Passenger Info
        for (int count = 0; count < pageObjectManager.getPaymentPage().getPassengerNameText().size(); count ++) {
            ValidationUtil.validateTestStep("Validating Pass Info displayed properly",
                    pageObjectManager.getPaymentPage().getPassengerNameText().get(count).getText(), passTitle.get(count) + ". " + passFirstName.get(count) + " " + passLastName.get(count));
        }

////-- Step 4:
//        ValidationUtil.validateTestStep("Validating PAY IN FULL uplift is displayed",
//                getDriver().findElement(By.xpath("//div[@class='payment-card-container pl-3 selected-card']")).isDisplayed());
//
//        ValidationUtil.validateTestStep("Validating PAY MONTHLY uplift is displayed",
//                getDriver().findElement(By.xpath("//div[@class='payment-card-container pl-3']")).isDisplayed());

//-- Stp 5: Not Valid

//-- Step 6
        //Total Due break Down section
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        //Flight section is the first on the break down list
        ValidationUtil.validateTestStep("Validating Flight section is the first line", pageObjectManager.getPaymentPage().getTotalDueBreakDownText().get(0).getText(),HEADER);

//-- Step 7:
        pageObjectManager.getPaymentPage().getTotalDueFlightChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        //Selecting edit link and going to flight availability page
        pageObjectManager.getPaymentPage().getTotalDueFlightEditText().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating 'Are you Sure?' pops up",
                pageObjectManager.getPaymentPage().getAreYouSurePopUpHeaderText().getText().trim(),"Are you sure?");

        pageObjectManager.getPaymentPage().getAreYouSurePopUpContinueToFlightButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        /*********************************Flight Availability Methods*************************************************/
//-- Step 8:
        ValidationUtil.validateTestStep("Validating Flight Availability Page URL", getDriver().getCurrentUrl(),FA_URL);
        pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(0).click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getFlightAvailabilityPage().getArrivalFlightEditButton().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        //Selecting expensive flight
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep" , "Price");
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityNameNew = new ArrayList<>();
        List<String> arrCityNameNew = new ArrayList<>();
        List<String> depTimeNew = new ArrayList<>();
        List<String> arrTimeNew = new ArrayList<>();

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

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //Recording Returning Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSelectedReturningFlightNatureButton().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

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

        //Closing Flight Info Pop Up
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*********************************Passenger Information Methods*************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /*********************************Bags Page Methods*************************************************/
//-- Step 9:
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /********************************Seats PAge Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*********************************Options Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//-- Step 10 and 11
        /****************************************************************************
         * *********************Payment Page Validations ****************************
         ****************************************************************************/
        //Verifying booking Date Info
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
        for (int count = 0; count < depCityNamePaymentNew.size(); count ++) {
            ValidationUtil.validateTestStep("Validating Cities and Time ", depCityNamePaymentNew.get(count), depCityNameNew.get(count) + " " + depTimeNew.get(count));
        }

        //Validating Arrival info displayed
        for (int count = 0; count < arrCityNamePaymentNew.size(); count ++) {
            ValidationUtil.validateTestStep("Validating Cities and Time Arriving", arrCityNamePaymentNew.get(count), arrCityNameNew.get(count) + " " + arrTimeNew.get(count));
        }

        //Validating Passenger Info
        for (int count = 0; count < pageObjectManager.getPaymentPage().getPassengerNameText().size(); count ++) {
            ValidationUtil.validateTestStep("Validating Pass Info displayed properly",
                    pageObjectManager.getPaymentPage().getPassengerNameText().get(count).getText(),
                    passTitle.get(count) + ". " + passFirstName.get(count) + " " + passLastName.get(count));
        }
//--Step 11:
        pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().click();

//-- Step 12:
        //Verifying right bags amount displayed
        for (WebElement bagInfo: pageObjectManager.getPaymentPage().getAllBagsInfoText()) {
            ValidationUtil.validateTestStep("Validating right Bags section displayed", bagInfo.getText().trim(), "1 Carry-On , 5 Checked Bag");
        }

//-- Step 13
        //Selecting 'Yes' for TG and completing the booking
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Method
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
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