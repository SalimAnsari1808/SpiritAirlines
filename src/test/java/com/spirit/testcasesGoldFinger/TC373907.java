package com.spirit.testcasesGoldFinger;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373907
//Description: Email Confirmation - Flight + Car - Validate the proper email is sent for INFT on Lap
//Created By: Salim Ansari
//Created On: 02-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373907 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightCar", "DomesticDomestic", "Outside21Days", "Adult", "InfantLap", "Guest", "NonStop", "BookIt","ContactInformation",
            "NoBags","NoSeats","CheckInOptions","OptionalUI","Visa","PaymentUI","ConfirmationUI","Email"})
    public void Email_Confirmation_Flight_Car_Validate_the_proper_email_is_sent_for_INFT_on_Lap(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373907 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "95";
        final String ARR_DATE           = "97";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

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
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: On the search widget, select the "Vacation" tab
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

//- Step 3: Create booking for F+C DOM | 3 months out | 1 ADT 1 INFT Lap |
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//STEP 4: Enter a DOB for a Lap PAX, click "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 5: Select "car" and continue
        //Storing flight information for next validation
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

        //Selecting any MGM hotel available
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA" , "NA");

//- Step 6: If the Bundle pop-up is displayed. Select "Book it"
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 7: On the contact information put a valid email address
//- Step 8: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Typing emailtesters email for next validation
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 9: click "continue without bags" at the bottom of the page.
//- Step 10: Click "I Don't Need Bags"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 11: Click "Continue without seats" below the travellers Box on the seats page.
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 12: The Selected car should be displayed on the Options page.
//- Step 13:Click continue with purchase
        //pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 14: Use a valid credit card from roles and credentials and complete the booking
        //pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//- Step 15: On the confirmation page verify that a PNR and the vacation Code are created for the booking
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        //pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();

//- Step 16: Open the Email box used on the booking and locate the Confirmation Email for the booking.
//- Step 17: Validate that the new packaging vendor, called carnect, information is on the Email
//- Step 18: Validate price shown on email is a packaging price (flight+car as one price)
        try {
            pageMethodManager.getCommonPageMethods().verifyPackageBookingEmails();
            //cancel Car package booking
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
        }
        catch (AssertionError fail) {
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage() , false );
        }
    }
}
