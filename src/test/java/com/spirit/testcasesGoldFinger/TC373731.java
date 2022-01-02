package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373731
//Description: Task 27155: TC373731 - US 18549 - 007 - Confirmation Email - Flight - Hotel - Car Vacation Booking Verification
//Created By: Gabriela
//Created On: 18-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373731 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "FlightHotelCar", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt","ContactInformation","NoBags","NoSeats","CheckInOptions","OptionalUI","Visa","PaymentUI","ConfirmationUI","Email"})
    public void Confirmation_Email_Flight_Hotel_Car_Vacation_Booking_Verification(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373731 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "113";
        final String ARR_DATE           = "115";
        final String ADULT              = "1";
        final String CHILD              = "0";
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
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Click on the "Vacation" Tab on the Search Widget
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

//- Step 3: Begin to reserve F+H+C | DOM-DOM | Outside 3 months | 1 Adult | Age +25 | 1 Room
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Book a Room for any hotel and Click Continue
        //Storing flight information for next validation
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

        //Selecting any MGM hotel available
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM" , "NA");

//- Step 5: Book any car and Click Continue
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");

//- Step 6: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 7: Input Valid Passenger Information
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Typing emailtesters email for next validation
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");

//- Step 8: Navigate to "Who's driving" subsection and click the Primary driver drop-down
//- Step 9: Select Your Primary Passenger and click the "CONTINUE" Action Button
        //Step 8 and 9
        //Selecting primary passenger as primary driver
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value")+" "+pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 10: Click the Blue "CONTINUE WITHOUT BAGS" hyperlink
//- Step 11: Click "I DON'T NEED BAG" Action Button on the "ARE YOU SURE" pop-up
        //Step 9 and 19
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 12: Click the "CONTINUE WITHOUT SEATS" Hyperlink
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 13: Select "I'll check in at spirit.com/Mobile for free" Click the "CONTINUE " Action button
        //pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 14: Purchase The Vacation Reservation using any Valid testing Credit Card
        //pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        //pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//- Step 15:  Verify Both the Flight Confirmation and Package Confirmation Code are displayed on the right  hand of the Page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        //pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
        //pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();

//- Step 16: Open SkySpeed on the test environment 1_4.2 using the apagent credentials
        //Invalid Step

//- Step 17: Using the Flight's PNR look up the flights PNR
        //Invalid Step

//- Step 18: Access the QA Online Email Testers using any browser
        //TODO: Method to validate on email needs to be implemented
//- Step 19: Search for the email address used to make this reservation
        //TODO: Method to validate on email needs to be implemented
//- Step 20: Verify the email displays the correct flight information and vacation Information
        //TODO: Method to validate on email needs to be implemented
//- Step 21: Compare Reservation price on Confirmation Page, Skyspeed and Email

        try {
            pageMethodManager.getCommonPageMethods().verifyPackageBookingEmails();

            //cancel
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
        }
        catch(AssertionError fail)
        {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
        }
        //This catch block will catch any Exceptions (null pointer, no such element, etc) after Payment and still cancel reservations
        catch (Exception fail)
        {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
        }
    }
}