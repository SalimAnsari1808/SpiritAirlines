package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC381909
//Description: Task 27831: TC381909- 006 - CP - Flight Flex Packaging Verbiage - Flight + Hotel - Validate verbiage for Flight Flex on the Hub Page
//Created By: Gabriela
//Created On: 21-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC381909 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult", "Guest", "NoBags","NoSeats","OptionalUI"})
    public void CP_Flight_Flex_Packaging_Verbiage_Flight_Hotel_Validate_verbiage_for_Flight_Flex_on_the_Hub_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC381909 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        final String FLIGT_FLEX_INFO    = "Flight Flex - Modify your flight once for free!";
        final String POPUPBODY          = "Flight Flex is only available during initial booking at spirit.com\n" +
                "Flight Flex must be purchased for all the passengers on the booking. Only one modification charge can be waived when Flight Flex is purchased.\n" +
                "You can only modify the time, date, and/or the origin/destination of the booking. The new time, date, or origin/destination must be known at time of change.\n" +
                "All modifications must be done online on spirit.com more than 24 hours in advance of their scheduled outbound or return departure.\n" +
                "Changes not made more than 24 hours in advance will be subject to our standard modification charges.\n" +
                "Please note that you will still be responsible for any difference in airfare and optional services for the alternate requested date(s) or flight(s), as well as any difference in government taxes and fees.\n" +
                "3rd (i.e. hotel/car/activity) modification and/or cancellation charges may apply";

//- Step 10: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Click on Vacation tab and select Flight + Hotel
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: Input the following: DOM_DOM | Any Date 3 months in the future | 2 PAX | 1 Room and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select any hotel and proceed to the Cars Page.
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA" , "NA");

//- Step 4: On Cars page, click "CONTINUE WITHOUT CAR".
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

//- Step 5:  Options page with BOOK IT | Required Passenger Information; Select Primary Driver | No Bags | No Seats
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

//- Step 6: Verify that Flight Flex is Available:
        ValidationUtil.validateTestStep("Validating flight Flex is Available for F+H booking",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//- Step 7: Scroll down to the Options title and verify the following verbiage:
        ValidationUtil.validateTestStep("Validating Right Spanish verbiage on Flight Flex card",
                pageObjectManager.getOptionsPage().getFlightFlexCardBodyText().getText(),FLIGT_FLEX_INFO);

//- Step 8: Click the tool tip next to the verbiage within the Flight Flex tile
        pageObjectManager.getOptionsPage().getFlightFlexCardToolTipLink().click();
        WaitUtil.untilTimeCompleted(1000);

//- Step 9: Verify the following verbiage is within the Flight Flex modal:
        ValidationUtil.validateTestStep("Validating Spanish translation on the Flight Flex Pop Up Information ",
                pageObjectManager.getOptionsPage().getFlightFlexToolTipPopUpBodyText().getText(),POPUPBODY);
    }
}