package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373990
//Description: Task 27893: TC373990 - US 22066 - 012 - CP - Pick-Up and Drop-Off Format - Flight + Hotel + Car - Validate the new format for a booking with Dollar
//Created By: Anthony Cardona
//Created On: 01-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373990 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "FlightHotelCar", "InternationalDomestic", "Outside21Days", "Adult", "Guest", "NoBags", "NoSeats", "Cars", "Hotels", "PaymentUI", "ConfirmationUI"})
    public void Pick_Up_and_Drop_Off_Format_Car_Upsell_Validate_the_new_format_for_a_booking_with_Dollar(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373990 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "IAH";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "35";
        final String ARR_DATE           = "36";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        // Hotel Page Constant Values
        final String HOTEL_BOOK_NAME    = "MGM";

        //Car Page Constant Values
        final String CAR_COMPANY_NAME    = "Dollar";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//Step1 : Book RT DOM-INT| Outside 24 hours | 2 ADT 1 Lap | Select "Search Flight"
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step2 : Fill DOB for the Lap child PAX, select "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//Step3: Select first flights, click "Continue"
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//Step 4: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Step 5: Fill in all Pax info
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        String firstName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value").toUpperCase();
        String lastName = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value").toUpperCase();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
//Step 6: Select "CONTINUE"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 7: Click on Continue without adding bags
//Step 8: Click on i dont need bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//Step 9: Click on Continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 10: Browse carousel until you see a Dollar car and click the View link
        pageMethodManager.getCarPageMethods().selectCarOnOptionPage(CAR_COMPANY_NAME , "NA");

        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(),firstName + " " + lastName);
        WaitUtil.untilTimeCompleted(1200);

        //TODO: New GoldFinger
//        pageObjectManager.getCarPage().getCarsPageBookButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getCarPage().getWhoSDrivingVerifyAndBookThisCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating flight Flex is Available after the car was selected",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//Step 13: Select the Check-In Option and click the Continue button
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 14: On the Payment page locate the Options section and validate that
        //TODO: Active Bug: [IN:14803]
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

//Step 15: Complete the booking and record the PNR information
//Step 16: Populate all the required information to reach Confirmation page
//        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
////Step 17: Scroll down to the Option content box and validate tha the Pick Up and Drop of time match with the Information displayed on Payment Page.
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();
//
////Step 18: Note the PNR and the Last Name then retrieve the booking through My Trips tab on the home page
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////Step 19 20: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
//        pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails();
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//
////Step 21: Scroll Up and click the PRINT RECEIPT link
//        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
//
////Step 22 23: On the my-trips/itinerary page locate the Options section and validate that:
//        pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails();
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//
//        //Cancel Hotel & Car
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();

    }
}
