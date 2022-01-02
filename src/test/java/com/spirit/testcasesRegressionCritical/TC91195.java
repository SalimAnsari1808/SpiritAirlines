package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.managers.*;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//TODO: Bug 25854: CP: CI: PROD: Flight Availability Page FA: Plus sign missing from Flights Total banner after selecting an expensive flight.
//Test Case ID: TC91195
//Test Name: Task 24709: 35383 CP_CI_Modify Flight_Payment
// Created By: Gabriela
//Created On : 09-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Aug-2019
//**************************************************************************************************

public class TC91195 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups={"CheckIn", "RoundTrip", "DomesticDomestic", "WithIn7Days", "Adult","Guest", "NonStop","BookIt","NoBags","NoSeats","CheckInOptions", "ChangeFlight", "Visa","PaymentUI","FlightAvailabilityUI", "ActiveBug"})

    public void CP_CI_Modify_Flight_Payment(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91195 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "ATL";
        final String DEP_DATE               = "0";
        final String ARR_DATE               = "5";
        final String ADULT                  = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String RET_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment page constant value
        final String PAYMENT_URL            = "/book/payment";
        final String CI_PAYMENT_URL         = "/check-in/payment";
        final String NEW_FLIGHT             = "Your New Flight";

        final String CARD_TYPE              = "VisaCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //checking constant value
        final String ONLINE_CHECK_IN_URL    = "/check-in/reservation-summary";
        final String CHECK_IN_FLIGHT_URL    = "/check-in/flights";
        final String POPUP_PURCHASE         = "DontPurchase";
        final String CHANGE_FEE             = "$90";
        final String CHANGE_CONFIRMATION_URL= "/check-in/confirmation";


        //open browser
        openBrowser(platform);

//--Step 14: Pre Req: Must have a DOM RT PNR that's within 24hrs with at least 90mins remaining before flight estimated time of departure (ETD) Note: This is a time restraint test - it must be completed (62mins) before ETD
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
        //Validating Payment URL
        ValidationUtil.validateTestStep("User verify Navigated to Payment page",
                getDriver().getCurrentUrl(), PAYMENT_URL);

        //Completing payment and booking
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*********************************Confirmation Page Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//-- Step 1: From the homepage, user should access their booking through Check-In tab. User should land on Online Check-In Page
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        /*********************************Online Check In Page Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //verify booking is confirmed
        ValidationUtil.validateTestStep("Validating Online Check IN URL ",
                getDriver().getCurrentUrl(),(ONLINE_CHECK_IN_URL));

//-- Step 2: User should see CHANGE FLIGHTS aligned to the right above the Flights content box (#4 in screenshot from prev. step) - Click the box
        ValidationUtil.validateTestStep("Validating Change Flighy button is displayed on Online Check In page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton()));

//-- Step 3: Within the Flights content block, locate CHANGE FLIGHTS - click the link and user should receive a CHANGE FLIGHTS modal
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        //If the booking is a RT user should see 2 edit buttons to select which flight to modify.
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating edit options is displayed for departure",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel()));
        ValidationUtil.validateTestStep("Validating edit options is displayed for returning",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupRetEditLabel()));

//-- Step 4: Click the Edit box for the returning flight - the flight search widget From: To: and Date: should be active for modification
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupRetEditLabel().click();
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("From field displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupRetFromCityDropDown()));

        ValidationUtil.validateTestStep("To field displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupRetToCityDropDown()));

        ValidationUtil.validateTestStep("Date field displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupRetDateTextBox()));

//-- Step 5: If a user click From: or To:, a drop down of cities should populate for the user to select a different city.
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupRetFromCityDropDown().click();
        WaitUtil.untilTimeCompleted(1200);
        List<WebElement> depList = new Select(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupRetFromCityDropDown()).getOptions();

        ValidationUtil.validateTestStep("Validating departure cities", depList.size()>1);

        List<WebElement> retList = new Select(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupRetToCityDropDown()).getOptions();

        ValidationUtil.validateTestStep("Validating returning cities", retList.size()>1);

//-- Step 6: If a user click the Date: container within search widget, a calendar should popup for users to select the date they want to modify their flight to
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupRetDateTextBox().click();

        ValidationUtil.validateTestStep("Validating calendar is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getCalenderPanel()));

//-- Step 7: Without changing anything, click the blue CONTINUE box
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate user is redirected to the Check-In Flight Availability page to select a new flight based on available options
        ValidationUtil.validateTestStep("Validating user is redirected to the Check-In Flight Availability page to select a new flight based on available options",
                getDriver().getCurrentUrl(),(CHECK_IN_FLIGHT_URL));

        /**************************************Flight Availability Methods*************************************************/
//-- Step 8: On the Flight Availability page, modify your flight to a more expensive fair
        //Note: Expensive fairs have a plus sign aligned to the left of the dollar value
        WaitUtil.untilPageLoadComplete(getDriver());
        manageTravel_SelectMoreExpensiveFlight(FARE_TYPE);
        WaitUtil.untilTimeCompleted(1000);

        //Note: Expensive fairs have a plus sign aligned to the left of the dollar value
        //TODO: Bug 25854: CP: CI: PROD: Flight Availability Page FA: Plus sign missing from Flights Total banner after selecting an expensive flight.
        ValidationUtil.validateTestStep("Validating minus sign for cheap flight",
                pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().contains("+"));

        String newFlightPrice = pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().replace("+","");
        String expensiveFlightPrice = pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText();


//-- Step 10: Below Your Itinerary should be a CONTINUE button - click it - user should now land on Extras page
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //No Bags
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(POPUP_PURCHASE);

        //No Seats
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(POPUP_PURCHASE);

        WaitUtil.untilPageLoadComplete(getDriver());

        /*********************************Options Page Methods*************************************************/
        pageObjectManager.getOptionsPage().getContinueButtonOnCheckInPathButton().click();

        /*********************************Payment Page Methods*************************************************/
//-- Step 11: User should land on Purchase page - verify Your New Flight
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Payment Page URL",
                getDriver().getCurrentUrl(), CI_PAYMENT_URL);

        ValidationUtil.validateTestStep("Validating Your New Flight header displaying the right info",
                pageObjectManager.getPaymentPage().getYourNewFlightText().getText(), NEW_FLIGHT);


//-- Step 12: Located at the bottom of the page should be a Payment Information  section- verify the Total Due amount, including Change Fee
        ValidationUtil.validateTestStep("Validating flight price charged is right displayed",
                expensiveFlightPrice, pageObjectManager.getPaymentPage().getTotalDuePriceText().getText());

        pageObjectManager.getPaymentPage().getTotalDueText().click();
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Validating flight price charged is right displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueChangeFeesText()));

        ValidationUtil.validateTestStep("Validating flight price charged is right displayed",
                pageObjectManager.getPaymentPage().getTotalDueChangeFeesPriceText().get(0).getText(),CHANGE_FEE);

//-- Step 13: User should now land on Change Confirmation page. Below Confirmation Code should be a green banner verifying changes. Scroll down to the bottom of the page, user should see Purchase Price with fees
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating land on Change Confirmation",
                getDriver().getCurrentUrl(),(CHANGE_CONFIRMATION_URL));

        ValidationUtil.validateTestStep("Validating Res Credit price is correct on Confirmation Page",
                expensiveFlightPrice, pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText());
    }

    private void manageTravel_SelectMoreExpensiveFlight(String fareType) {
        WaitUtil.untilTimeCompleted(1500);
        fareType = fareType.toLowerCase();
        if (fareType.contains("card")) fareType = "card-holder"; //if faretype is card, change faretype to "card-holder"

        WaitUtil.untilPageLoadComplete(getDriver());
        List <WebElement> TempallStandardsFares = getDriver().findElements(By.xpath("//label[contains(@for,'" + fareType + "')]"));

        List<WebElement> allStandardFares = new ArrayList<>();
        for(WebElement element : TempallStandardsFares) {
            if (element.isDisplayed()) {
                allStandardFares.add(element);
            }
        }

        for(WebElement fare : allStandardFares) {
            if (fare.getText().contains("+")){// if label contains + it is more expensive than original flight
                fare.click();
                break;
            }
        }
    }
}