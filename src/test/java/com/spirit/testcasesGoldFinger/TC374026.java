package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC374026
//Description: Task 27829: TC374026- 010 - CP - Flight Flex Packaging - Flight + Hotel - Validate that Flight Flex can be added to a Package Booking
//Created By: Gabriela
//Created On: 21-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC374026 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult", "NineDFC", "NoBags","NoSeats","OptionalUI"})
    public void CP_Flight_Flex_Packaging_Flight_Hotel_Validate_that_Flight_Flex_can_be_added_to_a_Package_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374026 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

//- Step 1: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Login as a $9FC member
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//- Step 3: Click on Vacation tab and select Flight + Hotel
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 4: Input the following: DOM_DOM | Any Date 3 months in the future | 5 PAX | 2 Room and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 5: Select any Hotel and proceed to the Options page with BOOK IT |  No Bags | No Seats
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA" , "NA");

        //No Cars Selected
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

        //Selecting Book It option
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Filling Passenger Information
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Selecting No Bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Selecting No Seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 6: Verify that Flight Flex is Available
        ValidationUtil.validateTestStep("Validating Flight Flex options is available on Options page for F+H booking",
                pageObjectManager.getOptionsPage().getFlightFlexCardAddButton().isEnabled());
    }
}