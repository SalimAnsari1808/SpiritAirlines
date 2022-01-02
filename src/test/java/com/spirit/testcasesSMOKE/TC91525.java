package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;


//**********************************************************************************************
//Test Case ID: TC91525
//Test Name:  Options_CP_MT_Shortcut Security_Shortcut Boarding
//Description: Validates Shortcut boarding and Shortcut Security on Options page/ Validates prices on payment page
//Created By : Sunny Sok
//Created On : 04-APR-2019
//Reviewed By: Anthony C
//Reviewed On: 04-APR-2019
//**********************************************************************************************

public class TC91525 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "WithIn21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "NoBags" , "Standard" , "CheckInOptions","ShortCutSecurity","ShortCutBoarding" , "PaymentUI","MasterCard", "OptionsUI"})
    public void Options_CP_MT_Shortcut_Security_Shortcut_Boarding(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to Home Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91525 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LGA";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "10";
        final String ADULTS                 = "1";
        final String CHILDS                 = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY                = "Departure";
        final String DEP_FLIGHT             = "NonStop";
        final String ARR_Flight             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADEVALUE           = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE           = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //Seats Page constant values
        final String MY_TRIP_SEAT           = "Standard";

        final String MY_TRIP_OPTION_VALUE   = "ShortCutSecurity,ShortCutSecurity|ShortCutBoarding";

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

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
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
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Reservation Summary Page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getPassengerSectionAddSeatsButton().get(0).click();

        //wait till pop up appear on page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(MY_TRIP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //click on dont purchase Bags
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getBagsPopupDontPurchaseMyBagsButton().click();
        /******************************************************************************
         ***************Validation Options Page Security/Shortcut Boarding**************
         ******************************************************************************/
        //declare constant used in validation
        final String OPTIONS_URL        = "my-trips/extras";
        final String RESERVATION_URL    = "my-trips/reservation-summary";

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify the options page URL
        ValidationUtil.validateTestStep("User verify Navigation to Options page on Manage Travel", getDriver().getCurrentUrl().contains(OPTIONS_URL));

        //click on Add ShortCut Security card button
        pageObjectManager.getOptionsPage().getShortCutSecurityCardAddButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify Shortcut security popup displayed
        ValidationUtil.validateTestStep("User verify Shortcut Security Popup is displayed on Manage Travel Options Page",
               pageObjectManager.getOptionsPage().getShortCutSecurityPopUpHeaderText().isDisplayed());

        //verify the departing airport
        ValidationUtil.validateTestStep("User verify Shortcut Security Airport on Manage Travel Options Page",
                pageObjectManager.getOptionsPage().getShortCutSecurityPopUpSelectCityCheckBox().get(0).getText().contains(scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split("\\(")[0]));

        if(!TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutSecurityPopUpNotAvaiableText())){
            ValidationUtil.validateTestStep("User verify Shortcut Security Airport on Manage Travel Options Page",
                    pageObjectManager.getOptionsPage().getShortCutSecurityPopUpSelectCityCheckBox().get(1).getText().contains(scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[0]));

        }

        //close Security popup
        pageObjectManager.getOptionsPage().getShortCutSecurityPopUpCloseWindowButton().click();

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(MY_TRIP_OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().verifyTotalDueOptions();

        //payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //verify the reservation Summary page
        ValidationUtil.validateTestStep("User verify Navigation to My Trip Reservation Summary page on Manage Travel", getDriver().getCurrentUrl().contains(RESERVATION_URL));

    }
}
