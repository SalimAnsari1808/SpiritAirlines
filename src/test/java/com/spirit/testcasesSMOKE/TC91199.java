package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.text.*;
import java.util.*;
import java.util.NoSuchElementException;

import static org.testng.Assert.fail;

//**********************************************************************************************
//Test Case ID: TC91199
//Test Name:  Check-in_CP_CI_ Widget
//Description:
//Created By : Sunny Sok
//Created On : 03-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 04-Apr-2019
//**********************************************************************************************

/**IN:24533 PROD: CP: The Guest's first five letters in their last name do not pull up their reservation.**/

public class TC91199 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = { "CheckIn" , "Guest" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NonStop",
            "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "HomeUI" , "ActiveBug"})

    public void Check_in_CP_CI_Widget(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to CheckHome Page*********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91199 under SMOKE Suite on " + platform + " Browser", true);
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
        final String CHILDREN           = "0";
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
        //Access (Website)testing environment

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

     //STEP2--create a booking for a flight that leaves in 24 hours and no earlier than 1 hour
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
        final int MaxLength = 6;
        final String CheckIn_URL = "check-in/reservation-summary";

     //--Step 3
        //Select Check-In Tab on the Booking widget from the Home Page. See the attachment
        pageObjectManager.getHeader().getSpiritLogoImage().click();
        pageObjectManager.getHomePage().getCheckInPathLink().click();

        //Check-In widget should be displayed Verify that you have two fields for input the Last Name and  PNR
        // with a link "Where to find your Confirmation Code."
        ValidationUtil.validateTestStep("User verify Last Name input field is displayed on Check-In Home Page",
                pageObjectManager.getHomePage().getCheckInLastNameTextBox().isDisplayed());
        ValidationUtil.validateTestStep("User verify Confirmation Code input field is displayed on Check-In Home Page",
                pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox().isDisplayed());
        ValidationUtil.validateTestStep("User verify Where to find your Confirmation Code link is displayed on Check-In Home Page",
                pageObjectManager.getHomePage().getWhereToFindYourConfirmationCodeLink().isDisplayed());

        //--Step 4
        //On the Confirmation Code box you should only be able to input 6 alpha numerical characters
        pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox().sendKeys("ABC4567");
        int ConfirmationCodeInputlength =  pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox().getAttribute("value").length();
        ValidationUtil.validateTestStep("User verify input is no more then 6 character on Check-In Home Page",
                ConfirmationCodeInputlength == MaxLength);

        //--Step 5
        //Only 5 alpha characters minimum are required IF last name is greater than 5 characters.
        // ALL characters are required IF last name is less than 5 characters.
        // (Example: Rodriguez all you need to use is Rodri whereas someone with the last name Lee would require all characters)to complete the search
        pageObjectManager.getHomePage().getCheckInLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString().substring(0,5));
        pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox().clear();
        pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());
        pageObjectManager.getHomePage().getCheckInButton().click();

        //Should be able to be complete the search and directed to the passenger check page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("User verify Navigation to Confirmation page on Check-In Home Page",
                getDriver().getCurrentUrl(),CheckIn_URL);
    }
}

