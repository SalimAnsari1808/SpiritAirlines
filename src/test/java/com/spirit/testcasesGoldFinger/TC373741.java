package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373741
//Description: Task 27163: TC373741 - US 19878 - 007 - CP - Email Confirmation - Vacation Path - Validate the proper email is sent for a booking with an inhibited passenger
//Created By: Gabriela
//Created On: 14-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373741 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult", "Guest",
                    "NonStop", "BookIt","PassengerInfoSSR","NoBags", "NoSeats", "CheckInOptions", "Visa","PaymentUI",
                    "ConfirmationUI", "Email"})
    public void CP_Email_Confirmation_Vacation_Path_Validate_the_proper_email_is_sent_for_a_booking_with_an_inhibited_passenger(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373741 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "106";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Passenger Info Page Constant Values
        final String SSR                = "HearingImpaired";

        //Options Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD       = "NotRequired";
        final String CARD_DETAIL        = "VisaCard";

//- Step 1: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: On the search widget, select the "Vacation" tab
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

//- Step 3: Create booking for F+H DOM | 3 months out | 2 ADT | 2 Rooms
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Select the Hotel and book the rooms.
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal", "NA");

//- Step 5: Select "Continue without car"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getCarPageMethods().continueWithoutCar();

//- Step 6: If the Upgrade & Save pop-up is displayed. Select "Book it"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 7: Fill customers info, check any box under "Special Services"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);

//- Step 8: On the contact information put a valid email address
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

//- Step 9: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 10 & 11: click "continue without bags" at the bottom of the page. & Click "I Don't Need Bags"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 12: Click "Continue without seats" below the travellers Box on the seats page.
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 13: The Selected Hotel should be displayed on the Options page.
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();

//- Step 14: Click continue with purchase
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 15: Use a valid credit card from roles and credentials and complete the booking
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        System.out.println("PNR: " + pageObjectManager.getConfirmationPage().getBookingSectionTopConfirmationCode().get(0).getText());
//- Step 16: On the confirmation page verify that a PNR and the HBG Code are created for the booking
//- Step 17: Open the Email box used on the booking and locate the Confirmation Email for the booking.
//- Step 18: Validate that the new packaging vendor, called HotelsBeds, information is on the Email
//- Step 19: Validate price shown on email is a packaging price (flight+hotel as one price)
//- Step 20: Verify verbiage about hotel check-in time should display.
        try {
            pageMethodManager.getCommonPageMethods().verifyPackageBookingEmails();
            //cancel hotel booking
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        } catch (Exception fail) {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        } catch (AssertionError fail) {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        }
    }
}