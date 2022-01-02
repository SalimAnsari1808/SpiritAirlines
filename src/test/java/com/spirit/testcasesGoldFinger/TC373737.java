package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373737
//Description: Task 27161: TC373737 - US 19877 - 007 - CP - Email Confirmation - Flight + Car - Validate the proper email is sent for Multi Pax
//Created By: Gabriela
//Created On: 14-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373737 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightCar", "Outside21Days", "Adult","Child", "Guest", "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions","OptionalUI", "Visa","PaymentUI", "ConfirmationUI", "Email"})
    public void CP_Email_Confirmation_Flight_Car_Validate_the_proper_email_is_sent_for_Multi_PaxCP_Email_Confirmation_Flight_Car_Validate_the_proper_email_is_sent_for_Multi_Pax(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373737 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "106";
        final String ADULT              = "4";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 18: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 1: On the search widget, select the "Vacation" tab
        /*** Home Page Methods**/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

//- Step 2: Create booking for F+C  DOM | 3 months out | 4 ADT +1 Child |  Search Vacation
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Enter any DOB for a Child PAX, click "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 4: Select "car" and continue
        /*** Flight Availability Page Method**/
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 5: If the Bundle pop-up is displayed. Select "Book it"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: On the contact information put a valid email address
        /*** Passenger Information Page Methos**/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");

        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value") + " " + pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));

//- Step 7: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 8 & 9: click "continue without bags" at the bottom of the page. & Click "I Don't Need Bags"
        /*** Bags Page Methods**/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 10: Click "Continue without seats" below the travellers Box on the seats page.
        /*** Seats Page Methods**/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 11: The Selected Car should be displayed on the Options page.
        /***Options Page Method**/
        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();

//- Step 12: Click continue with purchase
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 13: Use a valid credit card from roles and credentials and complete the booking
        /***Payment Page Method**/
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /***Confirmation Page Method**/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 14: On the confirmation page verify that a PNR and the carnect Code are created for the booking
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//- Step 15: Open the Email box used on the booking and locate the Confirmation Email for the booking
//- Step 16: Validate that the new packaging vendors called carnect infomation is on the Email
//- Step 17: Validate price shown on email is a packaging price (flight+car as one price)
        pageMethodManager.getCommonPageMethods().verifyPackageBookingEmails();

        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();


    }
}