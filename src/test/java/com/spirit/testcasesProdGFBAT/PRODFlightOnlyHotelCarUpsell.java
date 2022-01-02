package com.spirit.testcasesProdGFBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

public class PRODFlightOnlyHotelCarUpsell extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Production"})
    public void FlightOnlyHotelCarUpsell(@Optional("NA") String platform){

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "90";
        final String ARR_DATE           = "102";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";
        final String ROOMS       		= "1 Room";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

        //open Browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM","NA");

        //Car Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 6: Select "BOOK IT"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Page Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        //pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        //pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        //confirmation Page Methods
//        //pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();
//
//        //cancel HBG and Carnet PNR's
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
    }
}
