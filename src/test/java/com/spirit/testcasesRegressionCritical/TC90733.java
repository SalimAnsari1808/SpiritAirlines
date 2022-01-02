package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC90733
//Description : Task 24769: 35334 CP_MT_Payment Page_Purchase Price
//Created By : Alex Rodriguez
//Created On : 18-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 01-AUG-2019
//**********************************************************************************************
public class TC90733 extends BaseClass {

  @Parameters({"platform"})
  @Test(groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "WithIn21Days" , "Adult" , "Guest" , "NonStop" , "BundleIt" , "CarryOn" , "CheckedBags" , "Standard" ,"ShortCutBoarding","FlightFlex", "CheckInOptions" , "Visa" , "PaymentUI" , "AddEditBags"})
  public void CP_MT_Payment_Page_Purchase_Price(@Optional("NA") String platform) {


    //Mention Suite and Browser in reports
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC90733 under REGRESSION CRITICAL Suite on " + platform + " Browser", true);
    }
//Home Page Constant variables
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Flight";
    final String TRIP_TYPE          = "OneWay";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "FLL";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "MCO";
    final String DEP_DATE           = "15";
    final String ARR_DATE           = "NA";
    final String ADULT              = "1";
    final String CHILD              = "0";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT         = "NonStop";
    final String FARE_TYPE          = "Standard";
    final String UPGRADE_VALUE      = "BundleIt";

    //Seats Page Constant Values
    final String DEP_SEATS          = "Standard";

    //Options Page Constant Value
    final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

    //Payment page constant value
    final String PAYMENT_URL        = "/book/payment";
    final String CARD_TYPE          = "VisaCard";
    final String TRAVEL_GUARD       = "NotRequired";
    final String CI_PAYMENT_URL     = "/my-trips/payment";
    final String TOTAL_DUE_TXT      = "TOTAL DUE";
    final String FARES_INFO         = "Fares are not guaranteed until purchased.";
    final String BAGS_TEXT          = "BAGS";

    //Manage travel Bags Page Constant Value
    final String DEP_BAGS            ="Carry_0|Checked_1";
    /******************************************************************************
     ***********************Navigate to Manage Travel Payment Page*****************
     ******************************************************************************/

    //open browser
    openBrowser(platform);
    //*******************************************************************************************************
    //******************************************HOME PAGE****************************************************
    //*******************************************************************************************************
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //*******************************************************************************************************
    //*********************************Flight Availability Methods*******************************************
    //*******************************************************************************************************
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    //*******************************************************************************************************
    //*********************************Passenger Info Methods************************************************
    //*******************************************************************************************************
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
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

    //*******************************************************************************************************
    //**************************************Bags Page Methods************************************************
    //*******************************************************************************************************
    pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();
    //*******************************************************************************************************
    //*********************************Seats Page Methods****************************************************
    //*******************************************************************************************************
    pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
    pageMethodManager.getSeatsPageMethods().continueWithSeats();
    //*******************************************************************************************************
    //*********************************Options Page Methods**************************************************
    //*******************************************************************************************************
    pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
    //*******************************************************************************************************
    //*********************************Payment Page Methods**************************************************
    //*******************************************************************************************************
    //Validating Payment URL
    ValidationUtil.validateTestStep("User verify Navigated to Payment page",
            getDriver().getCurrentUrl(),PAYMENT_URL);

    //Completing payment and booking
    pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
    pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
    pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
    //*******************************************************************************************************
    //*********************************Confirmation Page Methods*********************************************
    //*******************************************************************************************************
    pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
    pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//-- Step 2:
    //Reaching into My Trips path
    pageMethodManager.getHomePageMethods().loginToMyTrip();
    //*******************************************************************************************************
    //*********************************Online Manage Travel Page Methods*************************************
    //*******************************************************************************************************
    //Add bags
    pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection("Bags");

    //*******************************************************************************************************
    //*********************************Bags Page Methods*****************************************************
    //*******************************************************************************************************
    WaitUtil.untilPageLoadComplete(getDriver());
    pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();
    WaitUtil.untilTimeCompleted(1200);
    String bagPrice     = pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText();
    WaitUtil.untilTimeCompleted(1000);
    pageObjectManager.getBagsPage().getBagsTotalContainerLink().click();
    WaitUtil.untilTimeCompleted(1000);

    pageObjectManager.getBagsPage().getOutboundJourneyBreakdownCheckedBagTotalPriceText().getText();

    //Recording checked bag amount information
    String checkedBagText       = pageObjectManager.getBagsPage().getBagsTotalContainerOptionsText().get(0).getText();
    pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
    //*******************************************************************************************************
    //*********************************Options Page Methods**************************************************
    //*******************************************************************************************************
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

    //*******************************************************************************************************
    //*********************************Payment Page Methods**************************************************
    //*******************************************************************************************************
//-- Step 3
    ValidationUtil.validateTestStep("User verify Navigated to Payment page",
            getDriver().getCurrentUrl(),CI_PAYMENT_URL);
//-- Step 4
    //Text Validation
    ValidationUtil.validateTestStep("Validating Total Due banner title",
            pageObjectManager.getPaymentPage().getTotalDueText().getText(),TOTAL_DUE_TXT);
    //Price Validation
    ValidationUtil.validateTestStep("Validating dollar amount is displayed",
            pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().startsWith("$"));
    //Fares info
    ValidationUtil.validateTestStep("Validating fares info is displayed",
            pageObjectManager.getPaymentPage().getTotalDueDisclaimerText().getText(),FARES_INFO);
    //Caret is collapsed
    ValidationUtil.validateTestStep("Validating Total Due caret is collapsed",
            pageObjectManager.getPaymentPage().getTotalDueChevronLink().getAttribute("Style"),"transform: rotate(-180deg)");

//--Step 5: Invalid Step. Flight should not display

//-- Step 6
    pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
    WaitUtil.untilTimeCompleted(1000);
    ValidationUtil.validateTestStep("Validating Bags displayed on the dropdown",
            pageObjectManager.getPaymentPage().getTotalDueBagsText().getText(),BAGS_TEXT);

//-- Step 7
    pageObjectManager.getPaymentPage().getTotalDueBagsChevronLink().click();
    WaitUtil.untilTimeCompleted(1000);
    WaitUtil.untilTimeCompleted(1000);
    final int FIRST_INDEX   = 0;
    final int SECOND_INDEX 	= 1;
    String depAirportCode		= scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];
    String retAirportCode		= scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];

    ValidationUtil.validateTestStep("Validating right city displayed on bags section",
            pageObjectManager.getPaymentPage().getTotalDueBagsCityText().getText(),depAirportCode + " - " + retAirportCode);
    ValidationUtil.validateTestStep("Validating the right Checked bag amount is displayed",
            pageObjectManager.getPaymentPage().getTotalDueBagsCheckedText().get(0).getText(),checkedBagText);
//-- Step 8
    ValidationUtil.validateTestStep("Validating the right price information is displayed",
            pageObjectManager.getPaymentPage().getTotalDueBagsPriceText().getText(),bagPrice);
//--Step 9
    ValidationUtil.validateTestStep("Validating no seats line is displayed on the dropdown",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueSeatsText()));
//--Step 10
    ValidationUtil.validateTestStep("Validating no Travel insurance line is displayed on the dropdown",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueTravelInsuranceText()));
//-- Step 11
    ValidationUtil.validateTestStep("Validating the right bag price information displayed on the total dropdown",
            pageObjectManager.getPaymentPage().getTotalDueBagsPriceText().getText(),pageObjectManager.getPaymentPage().getTotalDuePriceText().getText());
//-- Step 12
    ValidationUtil.validateTestStep("Validating 'Continue' button is displayed at the bottom of the page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getManageTravelContinueButton()));
    ValidationUtil.validateTestStep("Validating 'Cancel Changes' button is displayed at the bottom of the page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getManageTravelCancelChangesButton()));
  }
}