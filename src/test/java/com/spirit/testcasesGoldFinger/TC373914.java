package com.spirit.testcasesGoldFinger;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.aspectj.apache.bcel.generic.RET;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373914
//Description: Email Confirmation - Hotel + Car Upsell - Validate the proper email is sent for a booking with an Active Duty Military passenger
//Created By: Salim Ansari
//Created On: 29-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373914   extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightHotelCar", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "Connecting", "BookIt","ContactInformation",
            "NoBags","NoSeats","CheckInOptions","OptionalUI","Visa","PaymentUI","ConfirmationUI","Email"})
    public void Email_Confirmation_Hotel_Car_Upsell_Validate_the_proper_email_is_sent_for_a_booking_with_an_Active_Duty_Military_passenger(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373914 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "95";
        final String ARR_DATE           = "97";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";


        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String RET_FLIGHT         = "Connecting";
        final String FLIGHT_FARE        = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 1: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2:Create a regular booking for 1 Adt age 18 or over year old, DOM , RT, outside 48 hrs.
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

//- Step 3: Click Search Flights
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Choose flights for POO and POD
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

//- Step 5: Click Continue at the bottom of the page.
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FLIGHT_FARE);

//- Step 6: If the Bundle pop-up is displayed. Select "Book it"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 7: Fill customers info, check "Active Duty US Military Personnel" box
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();

//- Step 8: On the contact information put an @EmailTesters address
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Typing emailtesters email for next validation
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");

//- Step 9: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 10: Select 1 carry-on and 1 checked bag for your passenger and continue
            pageMethodManager.getBagsPageMethods().selectDepartingBags("Carry_1|Checked_1");
            pageMethodManager.getBagsPageMethods().selectReturnBags("Carry_1|Checked_1");
            pageMethodManager.getBagsPageMethods().selectBagsFare("Standard");

//- Step 11: Click "Continue without seats" below the travellers Box on the seats page.
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 12: On the options page book a hotel and car
        pageMethodManager.getHotelPageMethods().selectHotelOnOptionPage("MGM", "NA");
        pageMethodManager.getCarPageMethods().selectCarOnOptionPage("NA", "NA");

//- Step 14:Click continue with purchase
        //pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 15: Use a valid credit card from roles and credentials and complete the booking
        //pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        //pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
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
        catch (AssertionError fail) {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage() , false );
        }
    }
}
