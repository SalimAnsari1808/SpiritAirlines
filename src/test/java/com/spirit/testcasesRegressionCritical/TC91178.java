package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91178
//Test Name: Task 24710: 35387 CP_CI_Modify Flight_Res Credit
// Created By: Gabriela
//Created On : 08-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 09-Aug-2019
//**************************************************************************************************

public class TC91178 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NonStop", "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "ReservationCredit" , "MasterCard" ,"ReservationUI","ChangeFlight","FlightAvailabilityUI", "PaymentUI"})

    public void CP_CI_Modify_Flight_Res_Credit(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91178 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "0";
        final String ARR_DATE               = "2";
        final String ADULTS                 = "1";
        final String CHILDREN               = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "Nonstop";
        final String RET_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_CARD            = "NotRequired";

        //Online Check In Constant Values
        final String ONLINE_CHECK_IN_URL    = "/check-in/reservation-summary";
        final String CHECK_IN_FLIGHT_URL    = "/check-in/flights";
        final String POPUP_PURCHASE         = "DontPurchase";
        final String CHECK_IN_PAYMENT_URL   = "/check-in/payment";
        final String CHANGE_CONFIRMATION_URL= "/check-in/confirmation";

        //open browser
        openBrowser(platform);

//-- Step 14: Prereq: Must have a DOM RT PNR that's within 24hrs with at least 90mins remaining before flight estimated time of departure (ETD) Note: This is a time restraint test - it must be completed (62mins) before ETD

        /***************************** Home Page Methods *****************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /***************************** Flight Availability Page Methods *****************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /***************************** Passenger Information Page Methods *****************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /***************************** Bags Page Methods *****************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /***************************** Seats Page Methods *****************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /***************************** Options Page Methods *****************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /***************************** Payment Page Methods *****************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_CARD);

        /***************************** Confirmation Page Methods *****************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 1: From the homepage, user should access their booking through Check-In tab. User should land on Online Check-In Page
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());

        /***************************** Online Check In Page Methods *****************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //verify booking is confirmed
        ValidationUtil.validateTestStep("Validating Online Check IN URL ",
                getDriver().getCurrentUrl(),ONLINE_CHECK_IN_URL);

//-- Step 2: User should see MAKE CHANGES aligned to the right above the Flights content box (#4 in screenshot from prev. step) - Click the box
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
        List<WebElement> depList = getDriver().findElements(By.xpath("//select[@name='originStationCode']//option"));
        List<String> depCityList= new ArrayList<>();

        for (int i = 1; i < depList.size(); i ++)
        {
            depCityList.add(depList.get(i).getAttribute("value"));
        }

        ValidationUtil.validateTestStep("Validating departure cities", !depCityList.isEmpty());

        List<WebElement> retList = getDriver().findElements(By.xpath("//select[@name='destinationStationCode']//option"));
        List<String> retCityList= new ArrayList<>();

        for (int i = 1; i < retList.size(); i ++)
        {
            retCityList.add(retList.get(i).getAttribute("value"));
        }

        ValidationUtil.validateTestStep("Validating returning cities", !retCityList.isEmpty());

//-- Step 6: If a user click the Date: container within search widget, a calendar should popup for users to select the date they want to modify their flight to
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupRetDateTextBox().click();

        ValidationUtil.validateTestStep("Validating calendar is displayed",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//bs-days-calendar-view"))));

//-- Step 7:  Without changing anything, click the blue CONTINUE box
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate user is redirected to the Check-In Flight Availability page to select a new flight based on available options
        ValidationUtil.validateTestStep("Validating user is redirected to the Check-In Flight Availability page to select a new flight based on available options",
                getDriver().getCurrentUrl(),CHECK_IN_FLIGHT_URL);

//-- Step 8:  On the Flight Availability page, modify your flight to a cheaper fair
        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("Dep", "Standard", "Cheap");

        ValidationUtil.validateTestStep("Validating minus sign for cheap flight",
                pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().contains("-"));

        String newFlightPrice = pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().replace("-","");
        String negativeFlightPrice = pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText();

//-- Step 9: Below the list of flights, verify Your Itinerary is present - that Itinerary should reflect your current modification
        //Invalid Step

//-- Step 10: Below Your Itinerary should be a CONTINUE button - click it - user should now land on Extras page
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(POPUP_PURCHASE);
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(POPUP_PURCHASE);
        pageObjectManager.getOptionsPage().getContinueButtonOnCheckInPathButton().click();

//-- Step 11: User should land on Purchase page - verify Your New Flight
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating land on Payment Page",
                getDriver().getCurrentUrl(),CHECK_IN_PAYMENT_URL);

//-- Step 12: Located at the bottom of the page should be a container with Reservation Credit info - verify the amount of the Res. Credit matches what the flight value was
        ValidationUtil.validateTestStep("Validating Reservation Credit section is displayed",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("(//section[@class='ng-star-inserted'])[3]//strong[contains(text(),'Reservation Credit(s) for future travel Issued for this Reservation:')]"))));

        //verify the amount of the Res. Credit matches what the flight value was
        ValidationUtil.validateTestStep("Validating Res Credit price is correct",
                newFlightPrice, pageObjectManager.getPaymentPage().getReservationCreditBlockPriceText().getText());

        //Accept Hazmat
        pageObjectManager.getPaymentPage().getTermsAndConditionsLabel().click();
        WaitUtil.untilTimeCompleted(3000);

        //select the CONTINUE box below it
        pageObjectManager.getPaymentPage().getManageTravelContinueButton().click();

//-- Step 13: User should now land on Change Confirmation page. Below Confirmation Code should be a green banner verifying changes. Scroll down to the bottom of the page, user should see Credit Summary with Reservation Credit  details
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_CARD);
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating land on Change Confirmation",
                getDriver().getCurrentUrl(),CHANGE_CONFIRMATION_URL);

        ValidationUtil.validateTestStep("Validating Res Credit price is correct on Confirmation Page",
                negativeFlightPrice, pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText());

    }
}