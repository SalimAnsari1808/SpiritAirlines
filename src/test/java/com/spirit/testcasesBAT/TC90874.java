package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.CardDetailsData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
//Test Case ID: TC90874
//Description: Reservation Summary_CP_MT_Cancel Flight booked within 24hrs and departure is greater than 7 days_Full Refund
//Created By : Alex Rodriguez
//Created On : 20-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 25-Mar-2019
//**********************************************************************************************
public class TC90874 extends BaseClass {

  @Parameters ({"platform"})
  @Test(groups = {"Guest","MyTrips","RoundTrip","Adult","DomesticDomestic","Outside21Days","NonStop","BookIt",
          "NoBags","NoSeats","CheckInOptions","AmericanExpress","CancelReservationUI"})
  public void Reservation_Summary_CP_MT_Cancel_Flight_booked_within_24hrs_and_departure_is_greater_than_7_days_Full_Refund (@Optional("NA")String platform){
    /******************************************************************************
     ***********************Navigate to Cancellation Page**************************
     ******************************************************************************/
    //Mention Suite and Browser in reports
    if(!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC90874 under BAT Suite on " + platform + " Browser"   , true);
    }

    //Home Page Constant Values
    final String LANGUAGE 		      	= "English";
    final String JOURNEY_TYPE 	    	= "Flight";
    final String TRIP_TYPE 		      	= "RoundTrip";
    final String DEP_AIRPORTS 	    	= "AllLocation";
    final String DEP_AIRPORT_CODE   	= "FLL";
    final String ARR_AIRPORTS 	    	= "AllLocation";
    final String ARR_AIRPORT_CODE    	= "LAS";
    final String DEP_DATE 		      	= "25";
    final String ARR_DATE 		      	= "30";
    final String ADULTS			        = "1";
    final String CHILDREN			    = "0";
    final String INFANT_LAP		      	= "0";
    final String INFANT_SEAT		    = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT 	      	= "NonStop";
    final String ARR_Flight 	      	= "NonStop";
    final String FARE_TYPE		      	= "Standard";
    final String UPGRADE_VALUE          = "BookIt";

    //Options Page Constant Values
    final String OPTIONS_VALUE			= "CheckInOption:MobileFree";

    //Purchase Page Constant Values
    final String TRAVEL_GUARD     		= "NotRequired";
    final String CARD_TYPE 		      	= "AmericanExpressCard";

    //Cancellation Confirmation Page Constant Values
    final  String CANCEL_CONFIRM_URL    = "my-trips/cancel-reservation";

    //open browser
    openBrowser( platform);

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
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    //Passenger Info Methods
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //Bags Page Methods
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

    //Seats Page Methods
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

    //Option Page Methods
    pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

    //Purchase Page Methods
    pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
    pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
    pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

    //Confirmation Page Methods
    pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
    pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

    //Home page common methods
    pageMethodManager.getHomePageMethods().loginToMyTrip();
    /******************************************************************************
     ***********************Validation on Cancellation Page************************
     ******************************************************************************/
    //declare constant used in validation
    final int FIRST_INDEX 		= 0;
    final String CANCELLATION_CONFIRMATION = "Your reservation has been cancelled and refunded to the original form of payment as shown below, and an email with details has been sent to:";

    //declare variable used in method
    String expectedCardNumber = "";

    //Select Cancel itinerary Button
    pageObjectManager.getReservationSummaryPage().getCancelItineraryButton().click();

    //Wait till url is loaded
    WaitUtil.untilPageLoadComplete(getDriver());

    //Navigate to Cancellation Confirmation Page
    WaitUtil.untilPageURLVisible(getDriver(), CANCEL_CONFIRM_URL);

    //verify user navigated to cancel reservation summary page
    ValidationUtil.validateTestStep("User redirected to the Cancellation Confirmation Page",
            getDriver().getCurrentUrl(),CANCEL_CONFIRM_URL);

    //*******************
    //click on cancel reservation button
    pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();

    //wait for 1 sec
    WaitUtil.untilTimeCompleted(1000);

    //click on cancel reservation on Reservation Cancellation PopUp
    pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpCancelReservationButton().click();

    //wait for 1 sec
    WaitUtil.untilTimeCompleted(1000);

    //verify booking has been cancelled
    ValidationUtil.validateTestStep("Verify cancel confirmation verbiage appear in Sub Header of Cancel reservation Page",
            pageObjectManager.getCancelReservationPage().getCancellationSubHeaderText().get(FIRST_INDEX).getText(),CANCELLATION_CONFIRMATION);

    //verify email occur in the cancellation verbiage
    ValidationUtil.validateTestStep("Verify cancel confirmation email appear in Sub Header of Cancel reservation Page",
            pageObjectManager.getCancelReservationPage().getCancellationSubHeaderText().get(FIRST_INDEX).getText(),(scenarioContext.getContext(Context.CUSTOMER_EMAIL).toString()));

    CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(CARD_TYPE);

    //wait for 1 sec
    WaitUtil.untilTimeCompleted(1000);

    //verify card name
    ValidationUtil.validateTestStep("Verify Card Name in credit summary  of Cancel reservation Page",
            pageObjectManager.getCancelReservationPage().getCreditSummaryValuesValidThruText().getText().trim(),cardDetailsData.cardName);

    //Replace card number to X
    for(int carnNumberCounter=0;carnNumberCounter<cardDetailsData.cardNumber.length()-4;carnNumberCounter++){
      expectedCardNumber = expectedCardNumber + "X";
    }

    //get expected card number as shown in credit summary
    expectedCardNumber = expectedCardNumber.trim() + cardDetailsData.cardNumber.substring(cardDetailsData.cardNumber.length()-4);

    //verify Card Number
    ValidationUtil.validateTestStep("Verify Crad Number in credit summary  of Cancel reservation Page",
            pageObjectManager.getCancelReservationPage().getCreditSummaryValuesValidThruText().getText().trim(),expectedCardNumber);

    ValidationUtil.validateTestStep("Verify Total Amount refunded is same as Booking amount in credit summary  of Cancel reservation Page",
            Double.parseDouble(pageObjectManager.getCancelReservationPage().getCreditSummaryValesAmountText().getText().trim().replace("$" , ""))==Double.parseDouble(scenarioContext.getContext(Context.CONFIRMATION_TOTALPAID).toString()));
  }
}