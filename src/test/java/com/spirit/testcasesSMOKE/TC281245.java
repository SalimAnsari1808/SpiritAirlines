package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281245
//Test Name: E2E_MT_Guest_OW_INT_1_ADT_Booked_Out_of_7_days__New_Flight_Same_Price_No_Bags_No_Seats_No_Extras_Customer_Receives_reservation_credit
//Description: Validating the customer receive a reservation credit after modify the flight date for a cheapest one
//Created By : Gabriela Gonzalez
//Created On : 1-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 2-May-2019
//**********************************************************************************************
public class TC281245 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "OneWay" , "InternationalDomestic" , "Within21Days" , "Adult" ,
            "Guest" , "NonStop" , "BookIt" , "NoSeats" , "NoBags" , "CheckInOptions" , "Visa" , "ChangeFlight" , "PaymentUI"})
    public void E2E_MT_Guest_OW_INT_1_ADT_Booked_Out_of_7_days__New_Flight_Same_Price_No_Bags_No_Seats_No_Extras_Customer_Receives_reservation_credit(@Optional("NA") String platform) {
        //*****************Navigate to Manage Travel Payment Page**********************
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281245 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "GUA";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "9";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Option Page Constant Value
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_TYPE          = "VisaCard";

        //Confirmation Page Constant Value
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        //Selecting Book It option from Upgrade & Dave Pop up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Page Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //Close ROKT Pop Up
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Validating Confirmation Page Method
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //******************Manage Travel Page*******************************

//        scenarioContext.setContext(Context.CONFIRMATION_LASTNAME,"AUTOMATION");
//        scenarioContext.setContext(Context.CONFIRMATION_CODE,"SIGV4E");
//        scenarioContext.setContext(Context.HOMEPAGE_DEP_DATE,"2");

        //login to Manage Travel Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Selecting Change Flight link on Summary Page
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        //Selecting change departure from the Change Flight pop up
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep","NA","NA","30");
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("dep","Standard","Cheap");
        //selectCheapestFlight();

        //Store flight price information for validation
        final String resCredit = pageObjectManager.getFlightAvailabilityPage().getStandardFarePriceText().getText();

        //Continue with Standard Fare
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();

        //Continue without bags
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getReservationSummaryPage().getBagsPopupDontPurchaseMyBagsButton().click();

        //Continue without seats
        WaitUtil.untilPageLoadComplete(getDriver());
        //pageObjectManager.getReservationSummaryPage().getSeatsPopupDontPurchaseMySeatsButton().click();
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //Continue to Payment Page from Options page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getOptionsPage().getContinueButtonOnCheckInPathButton().click();

       //******************Validation On Manage Travel Payment Page********************
        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(1200);

        //Validate that the Reservation Credit will be applied to the PNR
        ValidationUtil.validateTestStep("The PNR is displayed on the Reservation Credit block",
                pageObjectManager.getPaymentPage().getReservationCreditBlockPNRText().isDisplayed());

        //Validating the Reservation Credit right amount
        ValidationUtil.validateTestStep("Verifying the Reservation Credit correspond with the right amount",
                resCredit,pageObjectManager.getPaymentPage().getReservationCreditBlockPriceText().getText());

        //Validating Reservation Credit expiration date is displayed
        ValidationUtil.validateTestStep("Validating Expiration Date is displayed",
                pageObjectManager.getPaymentPage().getReservationCreditBlockDateText().isDisplayed());

      //Accepting and ending the modification
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //*******************************************************************
        //********************Change Confirmation Page***********************
        //*******************************************************************
        //Validating the right Reservation amount is displayed
        ValidationUtil.validateTestStep("Reservation Credit amount is correct on Confirmation page ",
                resCredit,pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText());
    }

}

