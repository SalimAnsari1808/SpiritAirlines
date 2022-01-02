package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280489
//Test Name   : 555. E2E_9DFC_RT INT 1 ADT 2 CHILD_ConnectingFlight_Other_ThrillBags_Included seats_All CI agent_Credit card
//Created By  : Alex Rodriguez
//Created On  : 19-Jun-2019
//Reviewed By :
//Reviewed On :
//**************************************************************************************************
public class TC280489 extends BaseClass {
  @Parameters({"platform"})
  @Test(groups = {"BookPath" , "RoundTrip" , "DomesticInternational" , "WithIn21Days" ,"Outside21Days", "Adult" , "InfantSeat" ,
                  "InfantLap", "Connecting" , "NineDFC" , "BundleIt" ,"PassengerInformationUI", "PassengerInfoSSR" , "CarryOn" ,
                  "CheckedBags" ,"BagsUI", "Standard" ,"SeatsUI","FlightFlex", "ShortCutBoarding" ,"ShortCutSecurity", "Visa" ,
                  "TravelInsurancePopUp" , "OptionalUI" , "PaymentUI", "ConfirmationUI"})
  public void E2E_9DFC_RT_INT_1_ADT_2_CHILD_ConnectingFlight_Other_ThrillBags_Included_seats_All_CI_agent_Credit_card(@Optional("NA") String platform) {
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC280489 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
    }

    //Home Page Constant Values
    final String LANGUAGE 			= "English";
    final String JOURNEY_TYPE       = "Flight";
    final String LOGIN_ACCOUNT      = "NineDFCEmail";
    final String TRIP_TYPE 			= "RoundTrip";
    final String DEP_AIRPORTS 		= "AllLocation";
    final String DEP_AIRPORT_CODE   = "BWI";
    final String ARR_AIRPORTS 		= "AllLocation";
    final String ARR_AIRPORT_CODE   = "CUN";
    final String DEP_DATE 			= "15";
    final String ARR_DATE 			= "25";
    final String ADULT  			= "1";
    final String CHILDREN  		    = "2";
    final String CHILDREN2 		    = "0";
    final String INFANT_LAP 		= "0";
    final String INFANT_SEAT		= "0";
    final String INFANT_LAP2 		= "1";
    final String INFANT_SEAT2		= "1";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT 	  	= "Connecting";
    final String ARR_FLIGHT 		= "Connecting";
    final String FARE_TYPE			= "Member";
    final String UPGRADE_VALUE      = "BundleIt";


    //Passenger Info Page Constant Values
    final String SSR                = "Other";

    //Seats Page Constant Variables
    final String SEAT               = "Standard|Standard||Standard|Standard";

    //Options Page Constant Variables
    final String OPTIONS            = "ShortCutSecurity,ShortCutSecurity";

    //Payment Page Constant Values
    final String TRAVEL_GUARD       = "NotRequired";
    final String CARD_TYPE          = "VisaCard";

    //Confirmation Page Constant Values
    final String BOOKING_STATUS     = "Confirmed";
    final String CONFIRMATION_URL   = "book/confirmation";

    //open browser
    openBrowser(platform);
    //****************************************************************************
    //**************************Home Page Methods*********************************
    //***************************************************************************
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILDREN, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();
    pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

    //****************************************************************************
    //*************Flight Availability Page Methods*****************************
    //****************************************************************************/
    /*Select new Flight Search Button*/
    WaitUtil.untilPageLoadComplete(getDriver());
    pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILDREN2, INFANT_SEAT2, INFANT_LAP2);
    pageMethodManager.getHomePageMethods().clickSearchButton();
    pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    //****************************************************************************
    //*****************Passenger Information Page Methods************************
    //****************************************************************************
    //Passenger Info Methods
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);
    pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //****************************************************************************
    //************************Bags Page Methods*********************************
    //****************************************************************************/
    WaitUtil.untilPageLoadComplete(getDriver());
    pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);
    pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

    //****************************************************************************
    //*************************Seats Page Methods*********************************
    //****************************************************************************
    pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);
    pageMethodManager.getSeatsPageMethods().selectReturningSeats(SEAT);
    pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,SEAT,SEAT);
    pageMethodManager.getSeatsPageMethods().continueWithSeats();

    //****************************************************************************
    //*********************Options Page Methods*********************************
    //****************************************************************************/
    WaitUtil.untilPageLoadComplete(getDriver());
    pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);
    WaitUtil.untilPageLoadComplete(getDriver());

    //verify flight flex is selected
    ValidationUtil.validateTestStep("Verify Flight Flex is Selected on Options Page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));

    //verify Shortcut Boarding is selected
    ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

    pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS);

    WaitUtil.untilPageLoadComplete(getDriver(), (long) 500);
    //verify Shortcut Security is selected
    ValidationUtil.validateTestStep("Verify ShortCut Security is Selected on Options Page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutSecurityCardSelectedLabel()));


    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
    //****************************************************************************
    //*********************Payment Page Methods*********************************
    //****************************************************************************/
    WaitUtil.untilPageLoadComplete(getDriver());
    WaitUtil.untilTimeCompleted(1200);
    pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADE_VALUE);
    pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
    pageMethodManager.getPaymentPageMethods().fillCVVDetailsModifyPath(CARD_TYPE);
    pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

    //*************************************************************************************************************
    //**********************************Confirmation Page Method**************************************************
    //*************************************************************************************************************/
    WaitUtil.untilPageLoadComplete(getDriver());
    pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

    //verify booking is confirmed
    ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
            getDriver().getCurrentUrl(),CONFIRMATION_URL);

    //verify booking is confirmed
    ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
            pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    pageMethodManager.getConfirmationPageMethods().verifySelectedBaseFareConfirmation(UPGRADE_VALUE);
  }
}