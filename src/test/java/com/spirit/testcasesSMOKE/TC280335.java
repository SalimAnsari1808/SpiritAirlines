package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC280335
//Description: E2E_9DFC_OW DOM 0 ADT 1 UMNR_Book It_Direct Flight_STD_No Bags_No Seats_NoExt_CI Web_Amex
//Created By : Sunny Sok
//Created On : 30-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 2-May-2019
//**********************************************************************************************
public class TC280335 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Child" , "WithIn7Days" , "NineDFC" ,
            "NonStop" , "BookIt" ,"NoBags" , "NoSeats" , "OptionalUI" , "AmericanExpress"})
    public void E2E_9DFC_OW_DOM_0_ADT_1_UMNR_Book_It_Direct_Flight_STD_No_Bags_No_Seats_NoExt_CI_Web_Amex(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280335 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_ACCOUNT      = "UMNR9FC";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "BWI";
        final String DEP_DATE 			= "2";
        final String ARR_DATE 			= "NA";
        final String ADULTS 			= "0";
        final String CHILDS 			= "1";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Nonstop";
        final String FARE_TYPE 			= "Member";
        final String UPGRADEVALUE 		= "BookIt";

        //Payment Page Constant Values
        final String CARD_TYPE         = "AmericanExpressCard";
        final String TRAVEL_GUARD      = "NotRequired";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //get UMNR FSMC info from json file
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("UMNR9FC");

        pageObjectManager.getHomePage().getChildPopUpBirthBoxes().get(0).sendKeys(passengerInfoData.dob);

        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();

        //wait till UMNR popup appear
        WaitUtil.untilPageLoadComplete(getDriver());

        //accept UMNR popup
        pageObjectManager.getHomePage().getUMNRAcceptButton().click();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        //continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods

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

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    }
}
