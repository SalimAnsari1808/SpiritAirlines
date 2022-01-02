package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.enums.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90966
//Test Name:   $9FC Booking _CP_CI_Flight Availiability_ $9FC Sign up
//Description: Verify User is able to Sign up for $9FC on FA Page during Check in path
////Created By : Sunny Sok
//Created On : 11-Apr-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC90966 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "NineDFC" , "DomesticDomestic" , "Within7Days" , "Adult" , "NonStop" , "BookIt" ,
            "MandatoryFields" , "NoBags" , "NoSeats","CheckInOptions" , "AmericanExpress" , "ChangeFlight" ,"PassengerinfoSignUp", "PaymentUI", "ActiveBug"})
    public void $9FCBooking_CP_CI_FlightAvailiability_$9FCSignup(@Optional("NA") String platform) {
        //*******************************Navigate to Home Page**************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90966 under SMOKE Suite on " + platform + " Browser", true);
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
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADEVALUE       = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "AmericanExpressCard";
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
        WaitUtil.untilTimeCompleted(2000);

        //get passenger First and last name
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("NineFCAccountTab");

        //fill first passenger detail
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(FIRST_PAX),memberEnrollmentData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(FIRST_PAX).sendKeys(memberEnrollmentData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(FIRST_PAX).sendKeys(memberEnrollmentData.lastName);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(FIRST_PAX).sendKeys(memberEnrollmentData.dob);
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
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //*****************************Start of check in Path******************************/
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Reservation Summary Page
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        //wait till pop up appear on page
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on Departing edit box
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep","NA","NA","NA");


        //click on continue button
        pageMethodManager.getReservationSummaryPageMethods().continueCancelOnChangeFlightPopup("Continue");

        //Flight Availability common Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep","9DFC");

        //select member fare on Flight Availability page
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare("Member");

        //9FC pop up to log in
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on login button on 9DFC upsell
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpButton().click();

        //Create Password
        pageObjectManager.getPassengerInfoPage().get9FCUpselSignUpChoosePasswordTextBox().sendKeys(memberEnrollmentData.createPassword);

        //Confirm Password
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpConfirmPasswordTextBox().sendKeys(memberEnrollmentData.confirmPassowrd);

        //click on login button
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpButton().click();

        //dont purchase bags
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup("DontPurchase");

        //dont purchase Seats
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup("DontPurchase");

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //************************Validation Payment Page*******************************/
        //declare constant used in Validation
        final String FARE_CLUB_SAVING   = "$9 FARE CLUB DISCOUNT SAVINGS";
        final String CREDIT_TYPE        = "Reservation Credit";
        final String CONFIRMATION_URL   = "check-in/confirmation";

        //verify fare club saving on payment page
        ValidationUtil.validateTestStep("Verify the Fare Club Discount Saving appear on Payment Page",
                pageObjectManager.getPaymentPage().getFareClubSavingVerbiageText().getText(),FARE_CLUB_SAVING);

//        //verify reservation type //TODO: Invalid step
//        ValidationUtil.validateTestStep("Verify amount is added into Reservation Credit on Payment Page" ,
//                pageObjectManager.getPaymentPage().getReservationCreditBlockTypeText().getText().trim(),CREDIT_TYPE);

        //verify pnr //TODO: Invalid step
//        ValidationUtil.validateTestStep("Verify the PNR value for Reservation Credit on Payment Page",
//                pageObjectManager.getPaymentPage().getReservationCreditBlockPNRText().getText().trim(),scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        //verify amount //TODO: Invalid step
//        ValidationUtil.validateTestStep("Verify the amount to covert into reservation Credit on Payment Page",
//                Double.parseDouble(pageObjectManager.getPaymentPage().getReservationCreditBlockPriceText().getText().replace("$", "")) == Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_9DFC_TOTAL_PRICE).toString())*-1);

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

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