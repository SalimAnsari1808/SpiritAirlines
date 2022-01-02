package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC377942
//Description: E2E_9DFC_RT DOM MAX PAX MIX_ConnectingFlight_Standard_BundleBags_BundleSeats_NoExtras CI web_CreditCard
//Created By : Sunny Sok
//Created On : 18-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 26-Apr-2019
//**********************************************************************************************
public class TC377942 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Outside21Days" , "Adult" , "Child" ,
            "InfantSeat" , "NineDFC" , "Connecting" , "BundleIt" , "BagsUI" , "CarryOn" , "CheckedBags" ,
            "Standard" , "SeatsUI" , "FlightFlex" ,"DynamicShoppingCartUI", "ShortCutBoarding" , "CheckInOptions" ,
            "OptionalUI" , "MasterCard" , "PaymentUI","ConfirmationUI"})
    public void  E2E_9DFC_RT_DOM_MAX_PAX_MIX_ConnectingFlight_Standard_BundleBags_BundleSeats_NoExtras_CI_web_CreditCard(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC377942 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String LOGIN_EMAIL 	        = "NineDFCEmail";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "BWI";
        final String DEP_DATE 			    = "5";
        final String ARR_DATE 			    = "30";
        final String ADULTS 			    = "5";
        final String CHILDS 			    = "2";
        final String INFANT_LAP 		    = "0";
        final String INFANT_SEAT		    = "2";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    	= "Connecting";
        final String ARR_FLIGHT 		    = "Connecting";
        final String FARE_TYPE			    = "Member";
        final String UPGRADEVALUE 		    = "BundleIt";

        //Seat Page Constant
        final String DEP_SEAT               = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard||Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";
        final String ARV_SEAT               = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard||Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //Options Page constant values
        final String OPTION_VALUE           = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String SELECTED_OPTION_VALUE  = "ShortCutBoarding";
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        for (int i = 0; i < pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().size(); i ++)
        {
            pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(i).click();
        }
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADEVALUE);
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //continue withbags
        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADEVALUE);
        pageObjectManager.getBagsPage().getContinueWithBagsButton().click();

        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(ARV_SEAT);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADEVALUE,DEP_SEAT,ARV_SEAT);

        //verify for continue without seat
        ValidationUtil.validateTestStep("Verify 'Continue Without Seat' is not displayed on Seat Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton()));

        //continue with seat
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADEVALUE);
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION_VALUE);
        pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADEVALUE);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS = "Confirmed";
        final String CONFIRMATION_URL = "book/confirmation";

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //verify selected option
        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION_VALUE);
    }
}