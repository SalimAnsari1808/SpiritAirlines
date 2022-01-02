package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC349841
//Description: Task 27834: TC349841- 008 - CP - Fight + Hotel + Car - Validate the amount of passengers vs rooms display accurate on Payment, Confirmation and Itinerary Pages respectively
//Created By: Gabriela
//Created On: 21-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC349841 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "OptionalUI", "PaymentUI","ConfirmationUI", "ReservationUI"})
    public void CP_Fight_Hotel_Car_Validate_the_amount_of_passengers_vs_rooms_display_accurate_on_Payment_confirmation_and_Itinerary_Pages_respectively(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349841 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "3";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 1: Access test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Start booking a Vacation [Flight + Hotel + Car], 3 ADT, 2 Rooms outside of 90 days
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- STep 3 & 4: Select hotel rooms & Continue to the next page
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

//- STep 5: Select a car and continue
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");
//Save and Update pop up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- STep 6: Enter Passenger Information and continue without adding bags or seat
        /*** Passenger Information Page Methods **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value")+" "+pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /** Bags Page Methods **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*** Seats Page Methods **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- STep 7: Validate the hotel selection and details display on the Options Page. Then continue
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();

//- STep 8: Under the Hotel details, verify the correct amount of passengers and rooms is displaying.
        //Info is not displayed on options page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- STep 9: Proceed with the payment
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        /*** Confirmation Page Methods **/
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//
////- STep 10: Validate the hotel selection and details display on the Confirmation Page. Record PNR
//        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();
//
////- STep 11: Retrieve PNR via Manage Travel
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////- STep 12: Validate the correct amount of passengers and rooms are accurate with the original booking
//        //Hotel Validation on Reservation Summary page need it
//
////- STep 13: Access Skyspeed to validate the booking details match the web
//        //Invalid Step for Automation
//
////- STep 14: IMPORTANT: DO NOT FORGET to send your lead the booking details for cancellation.
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
    }
}