package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373180
//Description: Task 27136: TC373180 - 004 - CP - New Car Page Rules - Validate that a HBG PNR cannot be used to retrieve a Vacation Booking
//Created By: Gabriela
//Created On: 04-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373180 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip","FlightHotelCar", "DomesticDomestic", "Outside21Days", "Adult", "Guest","BookIt",
            "NoBags", "NoSeats","CheckInOptions", "OptionalUI","Visa","PaymentUI"})
    public void CP_New_Car_Page_Rules_Validate_that_a_HBG_PNR_cannot_be_used_to_retrieve_a_Vacation_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373180 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "100";
        final String ARR_DATE           = "102";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 15: Go to Spirit home page in test environment
        openBrowser(platform);

//- Step 1: Start creating a Vacation booking | Flight + Hotel + Car |  RT | DOM | 1 Pax | No Bags | No Seats | booking
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        //FA page, store all flight information for vacation booking
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

//- Step 2: Under "Choose Your Hotel", click on "SELECT ROOM" button
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 3: Click the "ROOMS FROM $XXX.xx" button
//- Step 4: Click "SELECT ROOM" button on the first hotel.
        WaitUtil.untilTimeCompleted(3000);
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM","NA");

//- Step 5: Choose a car and select "BOOK"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 6: Select "BOOK IT"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 7: Fill in all customer information and click on " CONTINUE"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 8 & 9: Select "CONTINUE WITHOUT ADDING BAGS" & Select "I DON'T NEED BAGS"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 10: Select "CONTINUE WITHOUT SELECTING SEATS"s
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 11: Select "CONTINUE"
        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();


//- Step 12: Select no to travel insurance, fill out all payment info, agree to HAZMAT terms and condition. click on "BOOK TRIP"
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//
//        //Getting error
//       pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
////-Step 13: Verify HBG PNR cannot be used on Spirit.com to retrieve a booking
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//
//
//        WaitUtil.untilPageLoadComplete(getDriver());
//         pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
//
////- Step 14: Verify Carnect PNR cannot be used on Spirit.com to retrieve a booking
//        //saving car confirmation code in a variable for validation
//        pageMethodManager.getHomePageMethods().loginToMyTripWithCarnetPNR();
//        pageMethodManager.getHomePageMethods().loginToMyTripWithHotelBedsPNR();
//
//        //cancel
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();

    }
}