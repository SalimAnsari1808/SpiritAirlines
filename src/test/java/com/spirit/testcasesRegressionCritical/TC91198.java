package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//@Listeners(com.spirit.testNG.Listener.class)
//**********************************************************************************************
//Test Case ID: TC91198
//Test Name:  Check-in_CP_CI_ Check-in errors
//Created By : Sunny Sok
//Created On : 26-JUN-2019
//Reviewed By: Salim Ansari
//Reviewed On: 28-JUN-2019
//**********************************************************************************************
public class TC91198 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "HomeUI"})
    public void Check_in_CP_CI_Check_in_errors(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Check in Home Page*********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91198 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //STEP--1
        //AccessSkysales (Website)testing environment

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //--STEP2
        // --create a booking for a flight that leaves in 24 hours and no earlier than 1 hour
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        /******************************************************************************
         ********************Validation Home Page Ci Widget****************************
         ******************************************************************************/
        //declare constant used in Validation
        final String LAST_NAME_ERROR            = "Please enter Passenger's Last Name.";
        final String CONFIRMATION_CODE_ERROR    = "Please enter your Confirmation Code.";
        final String RESERVATION_NOT_FOUND      = "We are unable to locate the itinerary. Please verify the information is correct and try again. The combination of the customer last name and the Confirmation Code is invalid. Please try again.";

        //--Step 3
        //Select Check-In Tab on the Booking widget from the Home Page. See the attachment
        pageObjectManager.getHeader().getSpiritLogoImage().click();
        pageObjectManager.getHomePage().getCheckInPathLink().click();

        //--Step 4
        //Enter a valid PNR and leave the Last Name field blank
        //Error message is display below the field box saying, "Please enter Passenger's Last Name", the image gives an exp. on how it looks
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getCheckInLastNameTextBox());
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox());
        pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());
        pageObjectManager.getHomePage().getCheckInButton().click();
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);
        ValidationUtil.validateTestStep("User verify Error message is display below the field box saying, Please enter Passenger's Last Name",
                 pageObjectManager.getCommon().getErrorMessageLabel().getText().trim(),LAST_NAME_ERROR);

        //--Step 5
        //Enter a valid Last Name and leave the PNR field blank
        //Error message  is displayed below the field box, "Please enter your Confirmation Code", the image gives an exp. on how it looks
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getCheckInPathLink().click();
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getCheckInLastNameTextBox());
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox());
        pageObjectManager.getHomePage().getCheckInLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());
        pageObjectManager.getHomePage().getCheckInButton().click();
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);
        ValidationUtil.validateTestStep("User verify Error message is display below the field box saying, Please enter your Confirmation Code",
                 pageObjectManager.getCommon().getErrorMessageLabel().getText().trim(),CONFIRMATION_CODE_ERROR);
        //--Step 6
        //Enter a valid Last name and the wrong PNR
        //Passenger receives "Reservation Not Found" pop-up
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getCheckInPathLink().click();
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getCheckInLastNameTextBox());
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox());
        pageObjectManager.getHomePage().getCheckInLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());
        pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox().sendKeys(TestUtil.getAlphaCharacterString(6));
        pageObjectManager.getHomePage().getCheckInButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("User verify Reservation Not Found Pop-up is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getReservationNotFoundHeaderTxt()));

        //--Step 7
        // pop up displaying "Reservation Not Found" and with the following verbiage "We are unable to locate the itinerary.
        // Please verify the information is correct and try again. The combination of the customer last name and the Confirmation Code is invalid.
        // Please try again."  see attached image,
        ValidationUtil.validateTestStep("User verify Reservation Not Found Pop-up Verbiage",
                pageObjectManager.getHomePage().getReservationNotFoundBodyTxt().getText().trim(),RESERVATION_NOT_FOUND);
        //--Step 8
        //Close out the Popup by either clicking on the X or blue Close button
        pageObjectManager.getHomePage().getReservationNotFoundCloseBtn().click();

        ValidationUtil.validateTestStep("User verify Reservation Not Found Pop-up is not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getReservationNotFoundHeaderTxt()));
    }
}