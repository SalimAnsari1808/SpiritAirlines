package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.enums.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//@Listeners(com.spirit.testNG.Listener.class)
//**********************************************************************************************
//Test Case ID: TC91389
//Test Name:   $9FC Booking _CP_MT_Flight Availiability_ $9FC Sign in
//Description: Verify User is able to login to $9FC account on FA Page during Manage Travel path
//Created By : Sunny Sok
//Created On : 08-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 09-Apr-2019
//**********************************************************************************************
public class TC91389 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NineDFC" , "NonStop" , "BookIt" ,
                    "MandatoryFields" , "NoBags" , "NoSeats" , "CheckInOptions","MasterCard" , "ChangeFlight" , "PaymentUI" ,
                    "PassengerInfoSignIn"})
    public void $9FC_Booking_CP_MT_Flight_Availiability_$9FC_Sign_in(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91389 under SMOKE Suite on " + platform + " Browser", true);
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
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADEVALUE       = "BookIt";
        final String FARE_TYPE_CHANGE   = "Member";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        //get passenger First and last name
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("NineFCMember");

        //fill first passenger detail
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

        //*****************************Start of manage Travel Path******************************/
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Reservation Summary Page
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

//        //wait till pop up appear on page
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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE_CHANGE);

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

        //click on dont purchase Bags
//        pageObjectManager.getReservationSummaryPage().getBagsPopupDontPurchaseMyBagsButton().click();
        pageObjectManager.getReservationSummaryPage().getBagsPopupDontPurchaseMyBagsButton().click();

        //Wait for Bags pop up
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Click on dont purchase seats
        //pageObjectManager.getReservationSummaryPage().getSeatsPopupDontPurchaseMySeatsButton().click();
//        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        pageObjectManager.getReservationSummaryPage().getSeatsPopupDontPurchaseMySeatsButton().click();

        //Wait for Bags pop up
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        //************************Validation Payment Page********************************/
        //declare constant used in Validation
        final String FARE_CLUB_SAVING   = "$9 FARE CLUB DISCOUNT SAVINGS";
        final String CREDIT_TYPE        = "Reservation Credit";
        final String CONFIRMATION_URL   = "/my-trips/confirmation";

        //verify fare club saving on payment page

            JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getPaymentPage().getFareClubSavingVerbiageText());
            ValidationUtil.validateTestStep("Verify the Fare Club Discount Saving appear on Payment Page",
                    pageObjectManager.getPaymentPage().getFareClubSavingVerbiageText().getText(), FARE_CLUB_SAVING);

            //verify reservation type
        if(pageObjectManager.getPaymentPage().getReservationCreditBlockTypeText().isDisplayed()) {
            JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getPaymentPage().getReservationCreditBlockTypeText());
            ValidationUtil.validateTestStep("Verify amount is added into Reservation Credit on Payment Page",
                    pageObjectManager.getPaymentPage().getReservationCreditBlockTypeText().getText().trim(), CREDIT_TYPE);

            //verify pnr
            ValidationUtil.validateTestStep("Verify the PNR value for Reservation Credit on Payment Page",
                    pageObjectManager.getPaymentPage().getReservationCreditBlockPNRText().getText().trim(), scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

            //verify amount
            ValidationUtil.validateTestStep("Verify the amount to covert into reservation Credit on Payment Page",
                    Double.parseDouble(pageObjectManager.getPaymentPage().getReservationCreditBlockPriceText().getText().replace("$", "")) == Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_9DFC_TOTAL_PRICE).toString()) * -1);
            pageObjectManager.getPaymentPage().getReservationCreditBlockPriceText().getText().replace("$", "");
        }else{
            System.out.println("No Res Credit");
            pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);

        }

        //Accept Terms and Conditions
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
                getDriver().getCurrentUrl().contains(CONFIRMATION_URL));
    }
}
