package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373992
//Description: Task 27823: TC373992 - US 22066 - 012 - CP - Pick-Up and Drop-Off Format - Flight + Hotel + Car - Validate the new format for a booking with Dollar
//Created By: Anthony Cardona
//Created On: 01-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373992 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "FlightHotelCar", "InternationalDomestic", "Outside21Days", "Adult", "Guest" , "NoBags" , "NoSeats" , "Cars" , "Hotels" , "PaymentUI" , "ConfirmationUI"})
    public void Pick_Up_and_Drop_Off_Format_Flight_Hotel_Car_Validate_the_new_format_for_a_booking_with_Dollar(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373992 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CUN";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "136";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        final String ROOMS_VALUE        = "1 Room";

        // Hotel Page Constant Values
        final String HOTEL_BOOK_NAME    = "MGM";

        //Car Page Constant Values
        final String CAR_COMPANY_NAME    = "Dollar";

        final String UPGRADE_VALUE      = "BookIt";
        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

// Step 1: Start Vacation [Flight + Car + Hotel] booking, departure in 3 months out for 5 ADT and 3 CHD passengers, 2 rooms and driver age +25
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

// Step 3: On the First available Hotel select the View button
// Step 4: Select the button Rooms From $##.##
// Step 5: Click the Select Room button on the first available Room
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage(HOTEL_BOOK_NAME, "NA");

// Step 6: Select "continue" at the bottom of the page
//Step is not longer valid due the passenger is automatically taken to cars page after select a hotel

//Step 7: Search for one Dollar  car and click the Book Car button
        pageMethodManager.getCarPageMethods().selectCarOnCarPage(CAR_COMPANY_NAME , "NA");

//Step 17: Click on BOOK IT button
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Step 8: Populate all the Passenger Information and select Who's driving? then click the Continue button.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 9: Click on Continue without adding bags
//Step 10: Click on i dont need bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//Step 11: Click on Continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 12: Verify that the Flight Flex is available after the user selects a car
        ValidationUtil.validateTestStep("Validating flight Flex is Available after the car was selected",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//Step 13: Select the Check-In Option and click the Continue button
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 14: On the Payment page locate the Options section and validate that
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

//Step 15: Complete the booking and record the PNR information
//Step 16: Populate all the required information to reach Confirmation page
//        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
////Step 17: Scroll down to the Option content box and validate tha the Pick Up and Drop of time match with the Information displayed on Payment Page.
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();
//
////Step 18: Note the PNR and the Last Name then retrieve the booking through My Trips tab on the home page
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////Step 19 20: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
//        pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails();
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//
////Step 21: Scroll Up and click the PRINT RECEIPT link
//        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
//
////Step 22 23: On the my-trips/itinerary page locate the Options section and validate that:
//        pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails();
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//
//        //Cancel Hotel & Car
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
    }
}