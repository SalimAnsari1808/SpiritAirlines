package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:25127] 	GoldFinger R1: CP: BP: F+C: Options Page: Incorrect car Pick Up information displayed.
//Test Case ID: TC376023
//Description: Task 27243: TC376023 - US 22065 - 001 - CP - Pick-Up and Drop-Off Format - Car Upsell - Validate the new format for a booking with Thrifty
//Created By: Gabriela
//Created On: 1-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC376023 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Child" , "Guest", "BookIt", "NoBags" , "NoSeats" , "Cars" , "PaymentUI" , "ConfirmationUI" , "ItineraryReceiptUI"})
    public void CP_Pick_Up_and_Drop_Off_Format_Car_Upsell_Validate_the_new_format_for_a_booking_with_Thrifty(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC376023 under GoldFinger Suite on " + platform + " Browser", true);
        }

//- Step 24: Access Goldfinger Home Page test environment
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "5";
        final String CHILD              = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 1: Book RT DOM-DOM| Outside 24 hours | 5 ADT 2 CHD | Select "Search Flight"
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 2: Fill DOB for all child PAX, select "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 3: Select first flights, click "Continue"
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 4: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 5: Fill in all Pax info
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        String firstName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value").toUpperCase();
        String lastName = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value").toUpperCase();
//- Step 6: Select "CONTINUE"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7: Scroll to the bottom of the page and select "Continue Without Adding Bags"
//- Step 8: select "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 9: Select "Continue without Selecting Seats"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 10: Browse carousel until you see an Alamo car and click the View link // Switching to Thrifty
//- Step 11: Under the section Who's driving? Select the Primary driver, and click the Book Car button
        pageMethodManager.getCarPageMethods().selectCarOnOptionPage("Thrifty","NA");
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(),firstName + " " + lastName);
        WaitUtil.untilTimeCompleted(1200);

//        pageObjectManager.getCarPage().getCarsPageBookButton().get(0).click();
        pageObjectManager.getCarPage().getWhoSDrivingVerifyAndBookThisCarButton().click();

//- Step 12: Verify that the Flight Flex is available after the user selects a car
        ValidationUtil.validateTestStep("Validating flight Flex is Available after the car was selected",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//- Step 13: Select the Check-In Option and click the Continue button
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 14: On the Payment page locate the Options section and validate that:
//- Step 15: On the Payment page locate the Options section and validate that:
        //TODO: [IN:25127] 	GoldFinger R1: CP: BP: F+C: Options Page: Incorrect car Pick Up information displayed.
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

//- Step 16: Populate all the required information to reach Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

//- Step 17: Scroll down to the Option content box and validate tha the Pick Up and Drop of time match with the Information displayed on Payment Page.
//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();

//- Step 18: Note the PNR and the Last Name then retrieve the booking through My Trips tab on the home page
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageMethodManager.getHomePageMethods().loginToMyTrip();

//- Step 19: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
//- Step 20: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();

//- Step 21: Scroll Up and click the PRINT RECEIPT link
//        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();

//- Step 22: On the my-trips/itinerary page locate the Options section and validate that:
//- Step 23: On the my-trips/itinerary page locate the Options section and validate that:
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();

    }
}