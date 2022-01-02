package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373745
//Description: Task 27165: TC373745 - US 19879 - 007 - CP - Email Confirmation - Flight + Hotel + Car - Validate the proper email is sent for a booking with an Infant on Lap
//Created By: Gabriela
//Created On: 13-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373745 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "Vacation", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult","InfantLap",
                    "Guest", "NonStop", "BookIt","NoBags","NoSeats","CheckInOptions","Visa","ConfirmationUI","Email"})
    public void CP_Email_Confirmation_Flight_Hotel_Car_Validate_the_proper_email_is_sent_for_a_booking_with_an_Infant_on_Lap(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373745 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "106";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 1: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: On the search widget, select the "Vacation" tab
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Create booking for F+H+C DOM | 3 months out | 1 ADT 1 INFT Lap | 1 Rooms
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Enter a DOB for a Lap PAX, click "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 5: Select the Hotel and book the rooms.
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

//- Step 6: Select "car" and continue
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 7: If the Upgrade & Save pop-up is displayed. Select "Book it"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: On the contact information put a valid email address
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

//- Step 9: Fill out passenger info and click "Continue
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 10 & 11: click "continue without bags" at the bottom of the page & Click "I Don't Need Bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 12: Click "Continue without seats" below the travellers Box on the seats page.
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 13: The Selected Hotel and car should be displayed on the Options page.
        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();

//- Step 14: Click continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 15: Use a valid credit card from roles and credentials and complete the booking
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//- Step 16: On the confirmation page verify that a PNR and the vacation Code are created for the booking
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//- Step 17: Open the Email box used on the booking and locate the Confirmation Email for the booking.
//- Step 18: Validate that the new packaging vendor, called HotelsBeds and carnect, information is on the Email
//- Step 19: Validate price shown on email is a packaging price (flight+hotel+car as one price)
//- Step 20: Verify verbiage about hotel check-in time should display.
        try{
            pageMethodManager.getCommonPageMethods().verifyPackageBookingEmails();
        }
        catch (AssertionError fail) {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage() , false );
        }

    }
}