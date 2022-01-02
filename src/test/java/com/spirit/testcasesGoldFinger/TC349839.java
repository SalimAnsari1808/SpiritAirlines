package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//**********************************************************************************************
//Test Case ID: TC349839
//Description: Task 27814: TC349839- 009 - CP - Fight + Hotel - Validate the amount of passengers vs rooms display accurate on Payment, Confirmation and Itinerary Pages respectively
//Created By: Anthony Cardona
//Created On: 03-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC349839 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult", "Guest", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "OptionalUI", "PaymentUI","ConfirmationUI", "ReservationUI"})
    public void CP_Fight_Hotel_Validate_the_amount_of_passengers_vs_rooms_display_accurate_on_Payment_Confirmation_and_Itinerary_Pages_respectively(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349839 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "90";
        final String ARR_DATE           = "91";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "1 Room";

        //Flight Availability Page Constant Values
        final String HOTEL_NAME         = "MGM";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//Step 1: Access test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//Step 2: Start booking a Vacation [Flight + Hotel ], 5 ADT, 2 Rooms outside of 90 days
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 3: Select hotel rooms
//Step 4: Continue to the next page
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage(HOTEL_NAME,"NA");

//Step 5: Scroll down to the end of the page and validate there is a CONTINUE WITHOUT A CAR button. Click on it.
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);
        //Save and Update pop up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Step 6: Enter Passenger Information and continue without adding bags or seat
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 7: Validate the hotel selection and details display on the Options Page. Then continue
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();

//Step 8: Under the Hotel details, verify the correct amount of passengers and rooms is displaying.
        //Info is not displayed on options page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 9: Proceed with the payment
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        //Confirmation Page methods
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//
////Step 10: Validate the hotel selection and details display on the Confirmation Page. Record PNR
//        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();
//
////Step 11: Retrieve PNR via Manage Travel
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////Step 12: Validate the correct amount of passengers and rooms are accurate with the original booking
//        pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails();
//
////Step 13: Access skyspeed to validate booking details match the web
//        //Invalid Step for Automation
//
////Step 14: IMPORTANT: DO NOT FORGET to send your lead the booking details for cancellation.
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
    }
}
