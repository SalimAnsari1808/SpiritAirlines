package com.spirit.testcasesHeartBeat;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: HeartBeat2
//Description: Guest_DOM_OW_1Adt_1Inf_1Child_Direct_BundleIt_Standard_Seat
// Created By : Kartik Chauhan
// Created On : 06-Aug-2019
// Reviewed By: Salim Ansari
// Reviewed On: 06-Aug-2019
//**********************************************************************************************
public class HeartBeat2 extends BaseClass {

    @Parameters({"platform"})
    @Test
    public void Guest_DOM_OW_1Adt_1Inf_1Child_Direct_BundleIt_Standard_Seat(@Optional("NA") String platform){

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID HeartBeat2 under HeartBeat Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "BWI";
        final String DEP_DATE 			= "2";
        final String ARR_DATE 			= "30";
        final String ADULTS1 			= "2";
        final String CHILDS 			= "1";
        final String INFANT_LAP 		= "1";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE 			= "Standard";
        final String UPGRADEVALUE 		= "BundleIt";

        //Seat Page Constant
        final String DEP_SEAT1         = "Standard|Standard|Standard";

        //Payment Page Constant Values
        final String CARD_TYPE         = "MasterCard";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //*******************************************************************
        //****************Navigate to Reservation Credit Page****************
        //*******************************************************************

        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS1, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //wait till UMNR popup appear
        WaitUtil.untilPageLoadComplete(getDriver());
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //continue without bags
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT1);

        //continue with seat
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);

    }
}