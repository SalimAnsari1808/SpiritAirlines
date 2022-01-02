package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:25511]	GoldFinger R1: CP: MT: Flight+Hotel+Car: Reservation Summary Page: Hotel room selected information is missing
//Test Case ID: TC373844
//Description:Task 27241: TC373844 - US 22065 - 001 - CP - BP - Check-In and Check-Out Format - Hotel Upsell - Validate the Facade of the hotel Check-In and Check-Out time
// Created By: Gabriela
//Created On: 1-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373844 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "Cars", "BookIt", "NoBags", "NoSeats",
            "CheckInOptions", "HotelsUI", "PaymentUI", "ReservationUI"})
    public void  CP_BP_Check_In_and_Check_Out_Format_Hotel_Upsell_Validate_the_Facade_of_the_hotel_Check_in_and_Check_Out_time(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373844 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
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

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

//- Step 17: Go to Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Start a DOM RT booking outside 24 hours for 1 adult. Click "SEARCH FLIGHTS"
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 2: Select departing and return flights, click on "CONTINUE" in the STANDARD box
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 3: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 4: Fill in Pax information and click "CONTINUE" at the bottom of the page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 5: Scroll down to the bottom of the page and click on "CONTINUE WITHOUT ADDING BAGS"
//- Step 6: Click "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 7: Scroll down and click on "CONTINUE WITHOUT SELECTING SEATS"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 8: Book a Hotel from the carousel
        pageMethodManager.getHotelPageMethods().selectHotelOnOptionPage("Universal","NA");

//- Step 9: Select Check-In in at Web. Click "Continue" Button
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 10: Validate that the "Your Hotel" has a Check-IN Date and a Check-Out date. Format should be Day Month Date year
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();

//- Step 11:Complete the payment of the booking.
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

//- Step 12: Validate that the "Your Hotel" has a Check-IN time and a Check-Out time. Format should be Day Month Date year, Time
        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();

//- Step 13: Record the PNR and last name.
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//- Step 14: Click "My TRIPS" on the header
//- Step 15: Input the PNR and Last name. Click "Continue" button
        pageMethodManager.getHomePageMethods().loginToMyTrip();

//- Step 16: Validate that the "Your Hotel" has a Check-IN time and a Check-Out time. Format should be Day Month Date year, Time
        //TODO: [IN:25511]	GoldFinger R1: CP: MT: Flight+Hotel+Car: Reservation Summary Page: Hotel room selected information is missing
        pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails();

        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
    }
}