package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC281174
//Title      : 31566 146. E2E_Guest_RT DOM 1 ADT 1 Child 5-15_Direct Flight_Standard_Bundle It [Tier 3] Bags_Trills Seats_No Extras CI Web_Credit Card
//Description: Validate user can complete booking by using parameters from the title
//Created By : Alex Rodriguez
//Created On : 7-June-2019
//Reviewed By: Salim Ansari
//Reviewed On: 10-June-2019
//**********************************************************************************************

public class TC281174 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Child" , "Guest" , "NonStop" ,
                     "BundleIt" , "PassengerInformationUI" , "BagsUI" , "CarryOn" , "CheckedBags" ,"Standard" , "SeatsUI" ,
                     "FlightFlex" , "ShortCutBoarding" , "OptionalUI" , "CheckInOptions" , "AmericanExpress","ConfirmationUI"})
    public void  E2E_Guest_RT_DOM_1_ADT_1_Child_5_15_Direct_Flight_Standard_Bundle_It_Bags_Trills_Seats_No_Extras_CI_Web_Credit_Card (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281174 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ORD";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "10";
        final String ADULT              = "1";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Options Page Constant Value
        final String SEATS          	= "Standard|Standard";

        //Options Page Constant Value
        final String OPTION_VALUE 		= "CheckInOption:MobileFree";

        //Payment page constant values
        final String CARD_TYPE          = "AmericanExpressCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant value
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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bags page
        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);//Bare Fare
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(SEATS);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,SEATS,SEATS);//Bare Fare
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         ***************************Validation on Confirmation Page********************
         ******************************************************************************/
        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //verify options on confirmation Page
        pageMethodManager.getConfirmationPageMethods().verifySelectedBaseFareConfirmation(UPGRADE_VALUE);//Bare Fare
    }
}