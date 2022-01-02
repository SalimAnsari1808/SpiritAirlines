package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91391
//Description: $9FC Booking _CP_MT_Flight Availiability_ $9FC No, I dont want to join and save
//Created By : Anthony Cardona
//Created On : 18-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 20-Jun-2019
//**********************************************************************************************
public class TC91391 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "Wintin7Days" , "Adult" , "NineDFC" , "Nonstop" , "BookIt" , "NoBags" , "NoSeats" ,"CheckInOptions", "MasterCard" ,"ChangeFlight","PassengerinfoSignUp","PassengerInformationUI","FlightAvailabilityUI","PaymentUI"})
    public void $9FC_Booking_CP_MT_Flight_Availiability_$9FC_No_I_dont_want_to_join_and_save(@Optional("NA") String platform) {
        /******************************************************************************
         ************************Navigate to Retrieve Password Page********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91391 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "Oneway";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "ATL";
        final String DEP_DATE 			= "5";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability constant variables
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Reservation Summary page constant values
        final String NEW_DEP_AIRPORTS   = "FLL";
        final String NEW_ARR_AIRPORTS   = "LAX";
        final String CHANGE_FLIGHT_POPUP= "Continue";

        //CheckIn path flight availability page constant values
        final String NEW_DEP_FLIGHT     = "9DFC";
        final String NEW_FARE_TYPE      = "Member";
        final String POPUP_PURCHASE     = "DontPurchase";

        //open browser
        openBrowser(platform);

//Step 1 create a booking within 24 hours and retrieve reservation on check in path to change flight
        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        //Filling mandatory passenger fields
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         ***********************************Confirmation Page Method*******************
         ******************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //login to Mytrip path
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Change the Origin and destination to get to the Check in FA page
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep", NEW_DEP_AIRPORTS, NEW_ARR_AIRPORTS,"NA");
        pageMethodManager.getReservationSummaryPageMethods().continueCancelOnChangeFlightPopup(CHANGE_FLIGHT_POPUP);


//Step 2 select a 9DFC fare and select member fare
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep",NEW_DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(NEW_FARE_TYPE);

//Step 3: validate the 9DFC upsell modal has 3 buttons
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("The sign up button is correctly displayed", pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpButton().isDisplayed());
        ValidationUtil.validateTestStep("The log in button is correctly displayed" , pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInButton().isDisplayed());
        ValidationUtil.validateTestStep("The continue with standard button is correctly displayed" , pageObjectManager.getPassengerInfoPage().get9FCUpsellContinueWithStandardFareButton().isDisplayed());

//Step 4: Click on sign up
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpButton().click();
        ValidationUtil.validateTestStep("The choose password textbox is correctly displayed" , pageObjectManager.getPassengerInfoPage().get9FCUpselSignUpChoosePasswordTextBox().isDisplayed());
        ValidationUtil.validateTestStep("The confirm password textbox is correctly displayed" , pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpConfirmPasswordTextBox().isDisplayed());
        ValidationUtil.validateTestStep("The sign up with ermail button is correctly displayed" , pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpWithEmailButton().isDisplayed());

//Step 5: click exit from modal
        pageObjectManager.getPassengerInfoPage().get9FCUpsellCloseBttnButton().click();

//Step 6: click on continue with member fare
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(NEW_FARE_TYPE);

//Step 7: Click on log in
        pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInButton().click();
        ValidationUtil.validateTestStep("The user name textbox is correctly displayed" , pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInUserNameTextBox().isDisplayed());
        ValidationUtil.validateTestStep("The user password textbox is correctly displayed" , pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInPasswordTextBox().isDisplayed());
        ValidationUtil.validateTestStep("The log in button is correctly displayed" , pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInUNPWSubmitLogInTextBox().isDisplayed());

//Step 8: click exit from modal
        pageObjectManager.getPassengerInfoPage().get9FCUpsellCloseBttnButton().click();

//Step 9: click on continue with member fare
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(NEW_FARE_TYPE);

//Step 10: Click on No I dont want to save
        pageObjectManager.getPassengerInfoPage().get9FCUpsellContinueWithStandardFareButton().click();

        //dont buy Bags
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(POPUP_PURCHASE);

        //dont purchase Seats
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(POPUP_PURCHASE);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Check Price on the payment page is correct for standard fare
        ValidationUtil.validateTestStep("User is correctly being charged for standard fare",
                pageObjectManager.getPaymentPage().getTotalDuePriceText().getText() , scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE).toString());
    }

}