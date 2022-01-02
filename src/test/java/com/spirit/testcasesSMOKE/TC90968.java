package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.enums.Context;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90968
//Test Name:  $9FC Booking _CP_CI_Flight Availiability_ $9FC Sign in through sign up
//Description:
//Created By : Sunny Sok
//Created On : 26-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 02-Apr-2019
//**********************************************************************************************
public class TC90968 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NineDFC" , "NonStop" , "BookIt" , "MandatoryFields" ,"NoBags","NoSeats",
            "CheckInOptions","MasterCard","ChangeFlight","PaymentUI"})
    public void $9FCBooking_CP_CI_FlightAvailability_$9FCSigninthroughsign_up(@Optional("NA") String platform) {
       //*****************************Navigate to Home Page**************************
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90968 under SMOKE Suite on " + platform + " Browser", true);
        }

        //declare constant used in Navigation
        final int FIRST_PAX             = 0;

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
        final String ADULTS				= "1";
        final String CHILDREN			= "0";
        final String INFANT_LAP		    = "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
//        final String FARE_TYPE_CHANGE   = "Member";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //STEP--1
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

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        //get passenger First and last name
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("NineFCMember");

        //fill first passenger detail
        WaitUtil.untilPageLoadComplete(getDriver());
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(FIRST_PAX),passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(FIRST_PAX).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(FIRST_PAX).sendKeys(passengerInfoData.lastName);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(FIRST_PAX).sendKeys(passengerInfoData.dob);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //*****************************Start of CheckIn Path******************************/
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Reservation Summary Page
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        //wait till pop up appear on page
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        //click on Departing edit box
//        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep","NA","NA","30");

        //click on continue button
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

        //Flight Availability common Methods
//        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep","9DFC");
        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("dep","9DFC","Cheap");

        //select member fare on Flight Availability page
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare("Member");

        //9FC pop up to log in
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on login button on 9DFC upsell
        pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInButton().click();

        //login to application with member login
        pageMethodManager.getHomePageMethods().loginToApplication("NineDFCEmail");

        //Wait for Bags pop up
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on don't purchase Bags
        pageObjectManager.getReservationSummaryPage().getBagsPopupDontPurchaseMyBagsButton().click();

        //Wait for Bags pop up
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Click on don't purchase seats
        pageObjectManager.getReservationSummaryPage().getSeatsPopupDontPurchaseMySeatsButton().click();

        //Wait for Bags pop up
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        //***********************Validation Payment Page********************************/
        //declare constant used in Validation
        final String FARE_CLUB_SAVING   = "$9 FARE CLUB DISCOUNT SAVINGS";
        final String CREDIT_TYPE        = "Reservation Credit";
        final String CONFIRMATION_URL   = "/check-in/confirmation";

        //verify fare club saving on payment page
        ValidationUtil.validateTestStep("Verify the Fare Club Discount Saving appear on Payment Page",
                pageObjectManager.getPaymentPage().getFareClubSavingVerbiageText().getText(),FARE_CLUB_SAVING);

        //verify reservation type
        ValidationUtil.validateTestStep("Verify amount is added into Reservation Credit on Payment Page" ,
                pageObjectManager.getPaymentPage().getReservationCreditBlockTypeText().getText().trim(),CREDIT_TYPE);

        //verify pnr
        ValidationUtil.validateTestStep("Verify the PNR value for Reservation Credit on Payment Page",
                pageObjectManager.getPaymentPage().getReservationCreditBlockPNRText().getText().trim(),scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        //verify amount
        ValidationUtil.validateTestStep("Verify the amount to covert into reservation Credit on Payment Page",
                Double.parseDouble(pageObjectManager.getPaymentPage().getReservationCreditBlockPriceText().getText().replace("$", "")) == Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_9DFC_TOTAL_PRICE).toString())*-1);

        //Accept terms and conditions
        pageObjectManager.getPaymentPage().getTermsAndConditionsLabel().click();

        //click on continue button
        pageObjectManager.getPaymentPage().getContunueButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //select TG
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //wait for confirmation
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for confirmation page
        WaitUtil.untilPageURLVisible(getDriver(), CONFIRMATION_URL);

        //verify confirmation page
        ValidationUtil.validateTestStep("User verify Navigation to Confirmation page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);
    }
}
