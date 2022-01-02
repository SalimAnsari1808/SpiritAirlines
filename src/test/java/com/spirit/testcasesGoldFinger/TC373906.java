package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373906
//Description: Email Confirmation - Car Upsell - Validate the proper email is sent for an Active Duty Military Passenger
//Created By: Salim Ansari
//Created On: 02-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373906 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightCar", "DomesticDomestic", "Outside21Days", "Adult", "Military", "Connecting", "BookIt","ContactInformation",
                    "CarryOn","CheckedBags","NoSeats","CheckInOptions","OptionalUI","Visa","PaymentUI","ConfirmationUI","Email"})
    public void Email_Confirmation_Car_Upsell_Validate_the_proper_email_is_sent_for_an_Active_Duty_Military_Passenger(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373906 under GoldFinger Suite on " + platform + " Browser", true);
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

//- Step 2: Create a regular booking for 1 Adt age 18 or over year old, DOM , RT, outside 48 hrs.
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Choose flights for POO and POD
//- Step 4: Click Continue at the bottom of the page.
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep","Connecting");
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret","Connecting");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare("Standard");

//- Step 5: If the Bundle pop-up is displayed. Select "Book it"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: Fill customers info, check "Active Duty US Military Personnel" box
//- Step 7: On the contact information put an @EmailTesters address
//- Step 8: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Typing emailtesters email for next validation
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 9: Select 1 carry-on and 1 checked bag for your passenger and continue
        pageMethodManager.getBagsPageMethods().selectDepartingBags("Carry_1|Checked_1");
        pageMethodManager.getBagsPageMethods().selectReturnBags("Carry_1|Checked_1");
        pageMethodManager.getBagsPageMethods().selectBagsFare("Standard");

//- Step 10: Click "Continue without seats" below the travellers Box on the seats page.
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 11: On the options page book a car
//- Step 12:Click continue with purchase
        //pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getCarPageMethods().selectCarOnOptionPage("NA","NA");
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 13: Use a valid credit card from roles and credentials and complete the booking
        //pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//- Step 14: On the confirmation page verify that a PNR and the carnect Code are created for the booking
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        //pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();

//- Step 15: Open the Emailtesters and locate the email used as contact for the booking.
//- Step 16: Validate that the new packaging vendor, called Carnect, infomation is on the Email
//- Step 17: Validate price shown on email is a packaging price (flightl+car as one price)
//- Step 18: Verify Military Credentials using one of the roles and credentials
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
