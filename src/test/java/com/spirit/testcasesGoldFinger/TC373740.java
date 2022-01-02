package com.spirit.testcasesGoldFinger;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373740
//Description: Email Confirmation - Hotel Upsell - Validate the proper email is sent for a Standard booking
//Created By: Salim Ansari
//Created On: 02-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373740 extends BaseClass{
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightHotel", "DomesticDomestic", "Outside21Days", "Adult", "Guest",
                    "Connecting", "BookIt","ContactInformation","NoBags","NoSeats","CheckInOptions","OptionalUI","Visa",
                    "PaymentUI","ConfirmationUI","Email"})
    public void Email_Confirmation_Hotel_Upsell_Validate_the_proper_email_is_sent_for_a_Standard_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373740 under GoldFinger Suite on " + platform + " Browser", true);
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

//- Step 2: Create a regular booking for 1 Adt age 18 or over year old, DOM ( to any city diferent than MCO, CUN, FLL, DEN), RT, outside 48 hrs.
//- Step 3: Click Search Flights
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Choose flights for POO and POD
//- Step 5: Click Continue at the bottom of the page.
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep",DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FLIGHT_FARE);

//- Step 6: If the Upgrade & Save pop-up is displayed. Select "Book it"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 7: On the contact information put an @EmailTesters address
//- Step 8: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Typing emailtesters email for next validation
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 9: Click "continue without bags" at the bottom of the page.
//- Step 10: Click "I Don't Need Bags"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 11: Click "Continue without seats" below the travellers Box on the seats page.
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 12: On the options page book a hotel.
//- Step 13: Click continue with purchase
        pageMethodManager.getHotelPageMethods().selectHotelOnOptionPage("MGM","NA");
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 14: Use a valid credit card from roles and credentials and complete the booking
        //pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//- Step 15: On the confirmation page verify that a PNR and the HBG Code are created for the booking
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        System.out.println("PNR: " + pageObjectManager.getConfirmationPage().getBookingSectionTopConfirmationCode().get(0).getText());
        //pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();

//- Step 16: Open the Emailtesters and locate the email used as contact for the booking.
//- Step 17: Validate that the new packaging vendor, called HotelsBeds, infomation is on the Email
//- Step 18: Validate price shown on email is a packaging price (flight+hotel as one price)
//- Step 19: Verify verbiage about hotel check-in time should display.
        try {
            pageMethodManager.getCommonPageMethods().verifyPackageBookingEmails();

            //cancel Car package booking
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        }
        catch(AssertionError fail)
        {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
        }
        //This catch block will catch any Exceptions (null pointer, no such element, etc) after Payment and still cancel reservations
        catch (Exception fail)
        {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
        }
    }
}
