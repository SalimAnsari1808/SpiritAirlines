package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:15876] - CP: BP: Options page:  Cars or Hotels sections are not displayed for an International destination (CUN)
//Test Case ID: TC373910
//Description: Task 27819: TC373910- US 19878 - 002 - CP - Email Confirmation - Hotel Upsell - Validate the proper email is sent for a booking with an International origin
// Created By: Gabriela
//Created On: 24-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 04-Dec-2019
//**********************************************************************************************

public class TC373910 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticInternational", "Outside21Days", "Adult", "Guest", "Child", "InfantLap","NonStop", "BookIt","ContactInformation", "NoBags", "NoSeats","Hotels","OptionalUI", "CheckInOptions", "Discover","Email"})
    public void CP_Email_Confirmation_Hotel_Upsell_Validate_the_proper_email_is_sent_for_a_booking_with_an_International_origin(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373910 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "11";
        final String ARR_DATE           = "15";
        final String ADULT              = "2";
        final String CHILD              = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Payment Page Constant values
        final String TRAVEL_GUARD       = "NotRequired";
        final String CARD_DETAIL        = "DiscoverCard1";

//- Step 1: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        //*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Create a regular booking for 2 Adt+1 Child +1Lap, INT to DOM, RT, outside 48 hrs. Click Search Flights
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Enter the DOB for the Child and Lap  PAX's, click "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 4: Choose flights for POO and POD
        //*** Flight Availability Page **/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);

//- Step 5: Click Continue at the bottom of the page.
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 6: If the Upgrade & Save pop-up is displayed. Select "Book it"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 7: On the contact information put an @EmailTesters address
        //*** Passenger Information Page **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");

//- Step 8: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 9 & 10: click "continue without bags" at the bottom of the page. & Click "I Don't Need Bags"
        //*** Bags Page **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 11: Click "Continue without seats" below the travellers Box on the seats page.
        //*** Seats Page **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 12: On the options page book a hotel.
        //* Options Page ***/
        pageMethodManager.getHotelPageMethods().selectHotelOnOptionPage("Fiesta Americana", "NA");
        //pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();

//- Step 13: Validate that the Flight Flex is offered after the Hotel been selected.
        ValidationUtil.validateTestStep("Validating Flight Flex is offered after hotel selection outside 24 hours",
                pageObjectManager.getOptionsPage().getFlightFlexCardAddButton().isEnabled());

//- Step 14: Click continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 15: Use a valid credit card from roles and credentials and complete the booking
        //** Payment Page **/
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        try
        {
            pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

            //** Confirmation Page**/
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

    //- Step 16: On the confirmation page verify that a PNR and the HBG Code are created for the booking
            pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();

    //- Step 17: Open the Emailtesters and locate the email used as contact for the booking.
            pageMethodManager.getCommonPageMethods().verifyPackageBookingEmails();

    //- Step 18: Validate that the new packaging vendor, called HotelsBeds, information is on the Email
            //Invalid Step

    //- Step 19: Validate price shown on email is a packaging price (flight+hotel as one price)

    //- Step 20: Verify verbiage about hotel check-in time should display.

            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        }
    //This catch block will catch any Validation/Assertion errors encountered after Payment and still cancel reservations
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