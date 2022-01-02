package com.spirit.testcasesGoldFinger;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373915
//Description: Email Confirmation - Flight + Hotel + Car - Validate the proper email is sent for a Standard Booking
//Created By: Salim Ansari
//Created On: 29-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373915  extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightHotelCar", "DomesticDomestic", "Outside21Days", "Adult", "Child", "Guest", "NonStop", "BookIt","ContactInformation",
                    "NoBags","NoSeats","CheckInOptions","OptionalUI","Visa","PaymentUI","ConfirmationUI","Email"})
    public void Email_Confirmation_Flight_Hotel_Car_Validate_the_proper_email_is_sent_for_a_Standard_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373915 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE = "English";
        final String JOURNEY_TYPE = "Vacation";
        final String TRIP_TYPE = "Flight+Hotel+Car";
        final String DEP_AIRPORTS = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS = "AllLocation";
        final String ARR_AIRPORT_CODE = "MCO";
        final String DEP_DATE = "105";
        final String ARR_DATE = "107";
        final String ADULT = "4";
        final String CHILD = "1";
        final String INFANT_LAP = "0";
        final String INFANT_SEAT = "0";
        final String DRIVER_AGE = "25+";
        final String SELECTED_ROOMS = "3 Rooms";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD = "NotRequired";
        final String CARD_DETAIL = "VisaCard";

//- Step 1: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: On the search widget, select the "Vacation" tab
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

//- Step 3:Create booking for F+H+C  DOM | 3 months out | 4 ADT +1 Child | 3 Room  Search Vacation
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().selectHotelRoom(SELECTED_ROOMS);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //STEP 4: Enter any DOB for a Child PAX, click "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 5: Select the Hotel and book the rooms.
        //Storing flight information for next validation
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

        //Selecting any MGM hotel available
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal", "NA");

//- Step 6: Select "car" and continue
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");

//- Step 7: If the Upgrade & Save pop-up is displayed. Select "Book it"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: On the contact information put a valid email address
//- Step 9: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Typing emailtesters email for next validation
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 10: click "continue without bags" at the bottom of the page.
//- Step 11: Click "I Don't Need Bags"
        //Step 9 and 19
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 12: Click "Continue without seats" below the travellers Box on the seats page.
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 13: The Selected Hotel and Car should be displayed on the Options page.
//- Step 14:Click continue with purchase
        //pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 15: Use a valid credit card from roles and credentials and complete the booking
        //pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        //pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//- Step 16:  On the confirmation page verify that a PNR and the vacation Code are created for the booking
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        //pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
        //pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();

//- Step 17: Open the Email box used on the booking and locate the Confirmation Email for the booking.
//- Step 18: Validate that the new packaging vendors called HotelsBeds and carnect infomation is on the Email
//- Step 19: Validate price shown on email is a packaging price (flight+hotel+car as one price)
//- Step 20: Verify verbiage about hotel check-in time should display.
        try {
            pageMethodManager.getCommonPageMethods().verifyPackageBookingEmails();

            //cancel
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
        }
        catch (Exception fail) {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
        }
        catch (AssertionError fail) {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
        }
    }

}
