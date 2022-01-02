package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC376025
//Description: Task 27249: TC376025 - US 22065 - 011 - CP - Pick-Up and Drop-Off Format - Flight + Hotel + Car - Validate the new format for a booking with Thrifty
// Created By: Gabriela
//Created On: 1-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC376025 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip","FlightHotelCar", "DomesticDomestic", "Outside21Days", "Adult","InfantSeat","InfantLap", "Guest", "NonStop", "BookIt",
            "OptionalUI", "CarsUI", "NoBags", "NoSeats", "AmericanExpress", "PaymentUI", "ConfirmationUI", "ReservationUI", "ItineraryReceiptUI"})
    public void CP_Pick_Up_andDrop_Off_Format_Flight_Hotel_Car_Validate_the_new_format_for_a_booking_with_Thrifty(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC376025 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "130";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "1";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "AmericanExpressCard";

        //Reservation Page Constant Values
        final String RES_SUM_URL        = "/my-trips/reservation-summary";
        final String ITINERARY_URL      = "my-trips/itinerary";

//- Step 25: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: On the Home page Select the Vacation tab, specific Flight + Hotel + Car.
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: Start a Vacation Flight+Hotel+Car booking | DOM to DOM| outside 48h | 2 ADT + 1 Lap + 1 child | Driver's age 25 +| 1 Room |  Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Fill DOB for all child PAX to be under 2yrs old, "Lap child and does not require a seat", select "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //store selected flight info
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

//- Step 4: On the First available Hotel select the View button
//- Step 5: Select the button Rooms From $##.##
//- Step 6: Click the Select Room button on the first available Room
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal", "NA");

//- Step 7: Scroll down to the bottom of the page and select the Continue button.
        //Invalid Step

//- Step 8: Search for one Alamo car and click the Book Car button // switching search to Thrifty
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Thrifty","NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 9: Populate all the Passenger Information and select Who's driving? then click the Continue button.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(0).click();
        WaitUtil.untilTimeCompleted(1200);
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value")+" "+pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 10 & 11: Scroll to the bottom of the page and select "Continue Without Adding Bags" & select "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 12: Select "Continue without Selecting Seats"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 13: Verify that the Flight Flex is available after the user selects a car
        ValidationUtil.validateTestStep("Validating flight Flex is Available on F+C booking",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//- Step 14: Verify you cannot select a Check-In Option and click the Continue button
        ValidationUtil.validateTestStep("Verify Check-In Option is disabled on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 15: On the Payment page locate the Options section and validate that:
//- Step 16: On the Payment page locate the Options section and validate that
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

//- Step 17: Populate all the required information to reach Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);

        //TODO: Package booking exceed the $1000
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
////- Step 18: Scroll down to the Option content box and validate tha the Pick Up and Drop of time match with the Information displayed on Payment Page.
//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
//
////- Step 19: Note the PNR and the Last Name then retrieve the booking through My Trips tab on the home page
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////- Step 20: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate   that:
////- Step 21: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
//        WaitUtil.untilPageLoadComplete(getDriver());
//        ValidationUtil.validateTestStep("Validating user is taken back to Reservation Summary Page page after car selected",
//                getDriver().getCurrentUrl(),(RES_SUM_URL));
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//
////- Step 22: Scroll Up and click the PRINT RECEIPT link
//        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        //verify user on Navigated to Itinerary Receipt page
//        ValidationUtil.validateTestStep("Verify user navigated Print Receipt Page,",
//                getDriver().getCurrentUrl(), ITINERARY_URL);
//
//        //verify The title of the page is Your Itinerary Receipt
//        ValidationUtil.validateTestStep("verify The title of the page is Your Itinerary Receipt",
//                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptText()));
//
////- Step 23: On the my-trips/itinerary page locate the Options section and validate that:
////- Step 24: On the my-trips/itinerary page locate the Options section and validate that:
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();

    }
}