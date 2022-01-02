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
//Test Case ID: TC376028
//Description: Task 27844: TC376028- US 22066 - 012 - CP - Pick-Up and Drop-Off Format - Flight + Hotel + Car - Validate the new format for a booking with Dollar
//Created By: Gabriela
//Created On: 29-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 04-Dec-2019
//**********************************************************************************************
public class TC376028 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip","FlightHotelCar", "DomesticInternational", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt",
            "OptionalUI", "CarsUI", "NoBags", "NoSeats", "Visa", "PaymentUI", "ConfirmationUI", "ReservationUI", "ItineraryReceiptUI"})
    public void CP_Pick_Up_and_Drop_Off_Format_Flight_Hotel_Car_Validate_the_new_format_for_a_booking_with_Dollar(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC376028 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CUN";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

        //Reservation Page Constant Values
        final String RES_SUM_URL        = "/my-trips/reservation-summary";
        final String ITINERARY_URL      = "my-trips/itinerary";

//- Step 24: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: On the Home page Select the Vacation tab, specific Flight + Hotel + Car.
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: Start a Vacation Flight+Hotel+Car booking | INT to DOM| outside 48h | 2 ADT | Driver's age 25 +| 1 Room |  Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: On the First available Hotel select the View button
//- Step 4: Select the button Rooms From $##.##
//- Step 5: Click the Select Room button on the first available Room
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM","NA");
//- Step 6: Scroll down to the bottom of the page and select the Continue button.
        //Invalid step

//- Step 7: Search for one Alamo car and click the Book Car button
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA"); // no Alamo company available in a domestic city
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: Populate all the Passenger Information and select Who's driving? then click the Continue button.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 9: Scroll to the bottom of the page and select "Continue Without Adding Bags"
//- Step 10: select "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 11: Select Continue without Selecting Seats"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 12: Verify that the Flight Flex is available after the user selects a car
        ValidationUtil.validateTestStep("Validating flight Flex is Available on F+C booking",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//- Step 13: Select the Check-In Option and click the Continue button
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 14: On the Payment page locate the Options section and validate that:
//- Step 15: On the Payment page locate the Options section and validate that:
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

//- Step 16: Populate all the required information to reach Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);

        //TODO: Package booking exceed the $1500
        try
        {
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

    //Step 17: Scroll down to the Option content box and validate tha the Pick Up and Drop of time match with the Information displayed on Payment Page.
            pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();

    //Step 18: Note the PNR and the Last Name then retrieve the booking through My Trips tab on the home page
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
            pageMethodManager.getHomePageMethods().loginToMyTrip();

    //Step 19: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
    //Step 20: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("Validating user is taken back to Reservation Summary Page page after car selected",
                    getDriver().getCurrentUrl(),(RES_SUM_URL));

            pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();

    //Step 21: Scroll Up and click the PRINT RECEIPT link
            pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
            WaitUtil.untilPageLoadComplete(getDriver());

            //verify user on Navigated to Itinerary Receipt page
            ValidationUtil.validateTestStep("Verify user navigated Print Receipt Page,",
                    getDriver().getCurrentUrl(), ITINERARY_URL);

            //verify The title of the page is Your Itinerary Receipt
            ValidationUtil.validateTestStep("verify The title of the page is Your Itinerary Receipt",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptText()));

    //Step 22: On the my-trips/itinerary page locate the Options section and validate that:
    //Step 23: On the my-trips/itinerary page locate the Options section and validate that:
            pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        }
        //This catch block will catch any Validation/Assertion errors encountered after Payment and still cancel reservations
        catch(AssertionError fail)
        {
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
        }
        //This catch block will catch any Exceptions (null pointer, no such element, etc) after Payment and still cancel reservations
        catch (Exception fail)
        {
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
        }
    }
}