package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373729
//Description: Task 27153: TC373729 - US 18549 - 005 - Confirmation Email - Flight - Car Vacation Booking Verification
//Created By: Gabriela
//Created On: 14-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373729 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightCar", "Outside21Days", "Adult", "Child", "Guest", "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "OptionalUI", "Visa", "PaymentUI", "ConfirmationUI", "Email"})
    public void Confirmation_Email_Flight_Car_Vacation_Booking_Verification(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373729 under GoldFinger Suite on " + platform + " Browser", true);
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

//- Step 1: Land on GoldFinger Test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Click on the "Vacation" Tab on the Search Widget
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

//- Step 3: Begin to reserve F+C DOM | At least 3 months out| 1 Adult| Passenger is 25+ Years of age
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 4: Click "BOOK" action button on any of the available vehicles (DON'T add any Hotels)
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 5: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: Input Valid Passenger Information(DON'T use a name subject to Copyright Infringement ex. Goku, Mickey Mouse ETC. Use a unique Email Adress and take note of it)
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");

//- Step 7: Navigate to "Who's driving" subsection and click the Primary driver drop-down
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value") + " " + pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));

//- Step 8: Select Your Primary Passenger and click the "CONTINUE" Action Button
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 9 & 10: Click the Blue "CONTINUE WITHOUT BAGS" hyperlink & Click "I DON'T NEED BAG" Action Button on the "ARE YOU SURE" pop-up
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 11: Click the "CONTINUE WITHOUT SEATS" Hyperlink
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 12: Verrify that the selected Car is being displayed on the top of Page

        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();

//- Step 13: Click the "CONTINUE" Action button
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 14: Purchase The Vacation Reservation using any Valid testing Credit Card

//        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        /***Confirmation Page Method**/
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 15: Verify Both the Flight Confirmation and Package Confirmation Code are displayed on the right  hand of the Page
//        ValidationUtil.validateTestStep("Validating Booking Confirmation Code is displayed",
//                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getBookingSectionTopConfirmationCode()));
//        ValidationUtil.validateTestStep("Validating Package Confirmation Code is displayed",
//                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getBookingSectionTopPackageConfirmationCodeText()));

//- Step 16: Open SkySpeed on the test environment 1_4.2 using the apagent credentials
        //TODO: Unable to automate this step
//- Step 17: Using the Flight's PNR look up the flights PNR
        //TODO: Unable to automate this step
//- Step 18: Access QA test Online Email Testers using any browser
        //TODO: Method to validate package booking on email needs to be implemented
//- Step 19: Search for the email address used to make this reservation
        //TODO: Method to validate package booking on email needs to be implemented
//- Step 20: Verify the email displays the correct flight information and vacation Information
        //TODO: Method to validate package booking on email needs to be implemented
//- Step 21: Compare Reservation price on Confirmation Page, Skyspeed and Email
    }
}