package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC381911
//Description: Task 27854: TC381911- 008 - CP - Flight Flex Packaging Verbiage - Flight + Hotel + Car - Validate verbiage for Flight Flex on the Hub Page
//Created By: Gabriela
//Created On: 25-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 06-Dec-2019
//**********************************************************************************************

public class TC381911 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest", "BookIt", "NoBags","NoSeats","OptionalUI"})
    public void CP_Flight_Flex_Packaging_Verbiage_Flight_Hotel_Car_Validate_verbiage_for_Flight_Flex_on_the_Hub_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC381911 under GoldFinger Suite on " + platform + " Browser", true);
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

        //Options Page Constant Values
        final String FLIGHT_FLEX_INFO    = "Flight Flex - Modify your flight once for free!";
        final String POP_UP_BODY        = "Flight Flex is only available during initial booking at spirit.com\n" +
                "Flight Flex must be purchased for all the passengers on the booking. Only one modification charge can be waived when Flight Flex is purchased.\n" +
                "You can only modify the time, date, and/or the origin/destination of the booking. The new time, date, or origin/destination must be known at time of change.\n" +
                "All modifications must be done online on spirit.com more than 24 hours in advance of their scheduled outbound or return departure.\n" +
                "Changes not made more than 24 hours in advance will be subject to our standard modification charges.\n" +
                "Please note that you will still be responsible for any difference in airfare and optional services for the alternate requested date(s) or flight(s), as well as any difference in government taxes and fees.\n" +
                "3rd (i.e. hotel/car/activity) modification and/or cancellation charges may apply";

//- Step 8: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        //*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Click on Vacation tab and keep the radio button defaulted to Flight + Hotel + Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: Input the following: DOM_DOM | Any Date 3 months in the future | 2 PAX | 1 Room | Driver 21-24 and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select any hotel and car and proceed to the Options page with BOOK IT | Fill Required Passenger Info., Select Primary Driver |  No Bags | No Seats
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

//- Step 4: Verify that Flight Flex is Available:
        //*** Options Page **/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating FLight Flex is available on Options page on Vacation path",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//- Step 5: Scroll down to the Options title and verify the following verbiage:
        ValidationUtil.validateTestStep("Validating Right Spanish verbiage on Flight Flex card",
                pageObjectManager.getOptionsPage().getFlightFlexCardBodyText().getText(),FLIGHT_FLEX_INFO);

//- Step 6: Click the tool tip next to the verbiage within the Flight Flex tile
        pageObjectManager.getOptionsPage().getFlightFlexCardToolTipLink().click();
        WaitUtil.untilTimeCompleted(1000);

//- Step 7: Verify the following verbiage is within the Flight Flex modal:
        ValidationUtil.validateTestStep("Validating Spanish translation on the Flight Flex Pop Up Information ",
                pageObjectManager.getOptionsPage().getFlightFlexToolTipPopUpBodyText().getText(),POP_UP_BODY);
    }
}