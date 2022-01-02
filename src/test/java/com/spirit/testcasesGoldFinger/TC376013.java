package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:25511]	GoldFinger R1: CP: MT: Flight+Hotel+Car: Reservation Summary Page: Hotel room selected information is missing
//Test Case ID: TC376013
//Description: Task 27245: TC376013 - US 22065 - 003 - CP - BP - Check-In and Check-Out Format - Flight + Hotel - Validate the Facade of the hotel Check-In and Check-Out time
// Created By: Gabriela
//Created On: 1-Dec-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 04-Dec-2019
//**********************************************************************************************
public class TC376013 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult", "Guest", "BookIt", "NoBags", "NoSeats", "PaymentUI", "ConfirmationUI", "ItineraryReceiptUI"})
    public void CP_BP_Check_In_and_Check_Out_Format_Flight_Hotel_Validate_the_Facade_of_the_hotel_Check_In_and_Check_Out_time(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC376013 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "130";
        final String ARR_DATE           = "131";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD       = "NotRequired";
        final String CARD_DETAIL        = "VisaCard";

//- Step 18: Go to Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Book a Vacation F+H DOM-DOM outside of 24 hours | 1 Adult Passenger | 1 Room |
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 2: Click "View" on a hotel tile
//- Step 3: Click "Book From $XX.XX". Select a Room.
//- Step 4: Click "Continue"
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM", "NA");

//- Step 5: Click "Continue Without Car"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: Fill out the passenger information. Click "Continue" Button
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7: Click "Continue without Adding Bags" button.
//- Step 8: Click "I DON'T NEED BAGS' Button
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 9: Click "CONTINUE WITHOUT SELECTING SEATS" Button
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 10: Select Check-in on web and Click "Continue" button
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 11: Validate that the "Your Hotel" has a Check-IN time and a Check-Out time. Format   should be Day Month Date year, Time
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();

//- Step 12: Complete the payment of the booking.
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);

        try
        {
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

            //- Step 13: Validate that the "Your Hotel" has a Check-IN time and a Check-Out time. Format should be Day Month Date year, Time
            pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();

            //- Step 14: Record the PNR and last name.
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

            //- Step 15: Click "My TRIPS" on the header
            //- Step 16: Input the PNR and Last name. Click "Continue" button
            pageMethodManager.getHomePageMethods().loginToMyTrip();

            //- Step 17: Validate that the "Your Hotel" has a Check-IN time and a Check-Out time. Format should be Day Month Date year, Time
            //TODO: [IN:25511]	GoldFinger R1: CP: MT: Flight+Hotel+Car: Reservation Summary Page: Hotel room selected information is missing
            pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails();

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