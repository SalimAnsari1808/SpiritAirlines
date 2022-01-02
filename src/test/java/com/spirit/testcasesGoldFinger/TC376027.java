package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:25127] 	GoldFinger R1: CP: BP: F+C: Options Page: Incorrect car Pick Up information displayed.
//Test Case ID: TC376027
//Description: Task 27798: TC376027- US 22066 - 007 - CP - Pick-Up and Drop-Off Format - Flight + Car - Validate the new format for a booking with Dollar
//Created By: Gabriela
//Created On: 29-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC376027 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip","FlightCar", "DomesticInternational", "Outside21Days", "Adult", "InfantLap", "Guest", "NonStop", "BookIt",
            "OptionalUI", "CarsUI", "NoBags", "NoSeats", "Visa", "PaymentUI", "ConfirmationUI", "ReservationUI", "ItineraryReceiptUI"})
    public void CP_Pick_Up_and_Drop_Off_Format_Flight_Car_Validate_the_new_format_for_a_booking_with_Dollar(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC376027 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

        //Reservation Page Constant Values
        final String RES_SUM_URL        = "/my-trips/reservation-summary";
        final String ITINERARY_URL      = "my-trips/itinerary";

//- Step 1: On the Home page Select the Vacation tab, specific Flight + Car
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: Start a Vacation Flight+Car booking | DOM to DOM| outside 48h |  2 ADT 1 Lap | Driver's age 25 +|  Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);

//- Step 3: Fill DOB for the Lap child PAX, select "Continue"
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 4: Search for one Dollar car and click the Book Car button
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Dollar","NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//- Step 5: Populate all the Passenger Information and select Who's driving? then click the Continue button.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 6 & 7: Scroll to the bottom of the page and select "Continue Without Adding Bags" & select "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 8: Select "Continue without Selecting Seats"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 9: Verify that the Flight Flex is available after the user selects a car
        ValidationUtil.validateTestStep("Validating flight Flex is Available on F+C booking",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//- Step 10: Verify user cannot select Check-In Option and click the Continue button
        ValidationUtil.validateTestStep("Verify Check-In Option is disabled on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

//- Step 11: On the Payment page locate the Options section and validate that:
    //Pick Up: verbiage should be displayed in bold right aligned the date and time should match with the arrival date and time of the selected departure:
    //Day Month Date Year, Time (HH:MM AM or PM)
        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 12: On the Payment page locate the Options section and validate that:
    //Drop off: verbiage should be displayed in bold right aligned the date and time should match the departing date and time of the selected return:
    //Day Month Date Year, Time (HH:MM AM or PM)
        //TODO: [IN:25127] 	GoldFinger R1: CP: BP: F+C: Options Page: Incorrect car Pick Up information displayed.
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

//- Step 13: Populate all the required information to reach Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);

        //TODO: Package booking exceed the $1500
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
////- Step 14: Scroll down to the Option content box and validate tha the Pick Up and Drop of time match with the Information displayed on Payment Page.
//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
//
////- Step 15: Note the PNR and the Last Name then retrieve the booking through My Trips tab on the home page
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////- Step 1: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
//    //Pick Up: verbiage should be displayed in bold right aligned the date and time should match with the arrival date and time of the selected departure:
//    //Day Month Date Year, Time (HH:MM AM or PM)
//
////- Step 17: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
//    //Drop off: verbiage should be displayed in bold right aligned the date and time should match the departing date and time of the selected return:
//    //Day Month Date Year, Time (HH:MM AM or PM)
//        WaitUtil.untilPageLoadComplete(getDriver());
//        ValidationUtil.validateTestStep("Validating user is taken back to Reservation Summary Page page after car selected",
//                getDriver().getCurrentUrl(),(RES_SUM_URL));
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//
////- Step 18: Scroll Up and click the PRINT RECEIPT link
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
////- Step 19: On the my-trips/itinerary page locate the Options section and validate that:
//    //Pick Up: verbiage should be displayed in boldright aligned the date and time should match with the arrival date and time of the selected departure:
//    //Day Month Date Year, Time (HH:MM AM or PM)
////- Step 20: On the my-trips/itinerary page locate the Options section and validate that:
//   //Pick Up: verbiage should be displayed in boldright aligned the date and time should match with the arrival date and time of the selected departure:
//    //Day Month Date Year, Time (HH:MM AM or PM)
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();

    }
}