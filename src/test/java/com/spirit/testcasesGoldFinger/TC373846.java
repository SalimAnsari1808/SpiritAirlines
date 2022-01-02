package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373846
//Description: Task 27246: TC373846 - US 22065 - 005 - CP -BP - Check-In and Check-Out Format - Flight + Hotel + Car - Validate the Facade of the hotel Check-In and Check-Out time
// Created By: Gabriela
//Created On: 1-Dec-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 04-Dec-2019
//**********************************************************************************************
public class TC373846 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightHotelCar", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "Cars", "BookIt", "NoBags", "NoSeats",
            "CheckInOptions", "CarsUI", "HotelsUI", "PaymentUI","ReservationUI"})
    public void CP_BP_Check_In_and_Check_Out_Format_Flight_Hotel_Car_Validate_the_Facade_of_the_hotel_Check_In_and_Check_Out_time(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373846 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "133";
        final String ARR_DATE           = "134";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

//- Step 18: Go to Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Book a Vacation F+H+C DOM-DOM outside of 24 hours | 1 Adult Passenger | 1 Room | Age 25+
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 2: Click "View" on a hotel tile
//- Step 3: Click "Book From $XX.XX". Select a Room.
//- Step 4: Click "Continue"
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

//- Step 5: Click "Book Car" button any car. Click "Continue" button
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: Fill out the passenger information. Click "Continue" Button
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7: Click "Continue without Adding Bags" button.
//- Step 8: Click "I DON'T NEED BAGS' Button
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 9: Click "CONTINUE WITHOUT SELECTING SEATS" Button
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 10: Select Check-in on web and Click "Continue" button
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 11: Validate that the "Your Hotel" has a Check-IN time and a Check-Out time. Format should be Day Month Date year, Time
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();

//- Step 12: Complete the payment of the booking.
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        try {
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
            pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

            //- Step 13: Validate that the "Your Hotel" has a Check-IN time and a Check-Out time. Format should be Day Month Date year, Time
            pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();

            //- Step 14: Record the PNR and last name.
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

            //- Step 15: Click "My TRIPS" on the header
            //- Step 16: Input the PNR and Last name. Click "Continue" button
            pageMethodManager.getHomePageMethods().loginToMyTrip();

            //- Step 17: Validate that the "Your Hotel" has a Check-IN time and a Check-Out time. Format should be Day Month Date year, Time
            pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails();

            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        }
        //This catch block will catch any Validation/Assertion errors encountered after Payment
        catch(AssertionError fail)
        {
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

            ValidationUtil.validateTestStep("Test case failed after cancellation: " + fail.getMessage() , false );
        }
        //This catch block will catch any Exceptions (null pointer, no such element, etc)
        catch (Exception fail)
        {

            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

            ValidationUtil.validateTestStep("Test case failed after cancellation: " + fail.getMessage() , false );
        }
    }
}