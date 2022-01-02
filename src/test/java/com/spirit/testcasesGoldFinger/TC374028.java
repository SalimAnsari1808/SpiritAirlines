package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//*********************************************************************************************
//Test Case ID: TC374028
//Description: Task 27852: TC374028- 013 - CP - Flight Flex Packaging - Flight + Hotel + Car - Validate that Flight Flex can be added to a Package Booking
//Created By: Gabriela
//Created On: 25-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 06-Dec-2019
//**********************************************************************************************

public class TC374028 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest", "BookIt", "NoBags","NoSeats","OptionalUI"})
    public void CP_Flight_Flex_Packaging_Flight_Hotel_Car_Validate_that_Flight_Flex_can_be_added_to_a_Package_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374028 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "21-24";

        //Flight Availability Constant Values
        final String UPGRADE_VALUE      = "BookIt";

//- Step 1: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        //*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Click on Vacation tab and keep the radio button defaulted to Flight + Hotel + Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Input the following: DOM_DOM | Any Date 3 months in the future | 2 PAX | 1 Room | Driver 21-24 and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Select any hotel and car, and proceed to the Options page with BOOK IT |  No Bags | No Seats
        //*** Flight Availability Page **/
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        //*** Hotel Page **/
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");
        //*** Car Page **/
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");
        //*** Save & Upgrade pop up **/
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //*** Passenger Information Page **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //*** Bags Page **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //*** Seats Page **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 5: Verify that Flight Flex is Available for the Packaging Booking.
        //*** Options Page **/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating FLight Flex is available on Options page on Vacation path",
                pageObjectManager.getOptionsPage().getFlightFlexCardAddButton().isEnabled());
    }
}