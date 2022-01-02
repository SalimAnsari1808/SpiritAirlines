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
//Test Case ID: TC376024
//Description: Task 27248: TC376024 - US 22065 - 006 - CP - Pick-Up and Drop-Off Format - Flight + Car - Validate the new format for a booking with Thrifty
//Created By: Anthony Cardona
//Created On: 29-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC376024 extends BaseClass{
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Child" , "Guest", "BookIt", "NoBags" , "NoSeats" , "Cars" , "PaymentUI" , "ConfirmationUI" , "ItineraryReceiptUI"})
    public void Pick_Up_and_Drop_Off_Format_Flight_Car_Validate_the_new_format_for_a_booking_with_Thrifty(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC376024 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "3";
        final String CHILD              = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        final String UPGRADE_VALUE      = "BookIt";

        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";


//- Step 1: On the Home page Select the Vacation tab, specific Flight + Car
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
//- Step 2: Start a Vacation Flight+Car booking | DOM to DOM| outside 48h | 3 ADT + 2 Child | Driver's age 25 +|  Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
//- Step 3: Book RT DOM-DOM | Any date outside 48h | 2 ADT | No Bags / No Seats / No Bundle / Select "Search Flight"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 4: Search for one Alamo car and click the Book Car button
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Thrifty" , "NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//
////Step 5: Populate all the Passenger Information and select Who's driving? then click the Continue button.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value")+" "+pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 6&7: Select "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 8: Select "Continue without Selecting Seats"

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        String OPTIONS_PAGE           = "book/options";

        ValidationUtil.validateTestStep("Validating Cars Page is on the right URL",
                getDriver().getCurrentUrl(),OPTIONS_PAGE);

//Step 9: Verify that the Flight Flex is available after the user selects a car
        ValidationUtil.validateTestStep("Validating flight Flex is Available after the car was selected",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));


//Step 10: Select the Check-In Option and click the Continue button
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 11&12 : On the Payment page locate the Options section and validate that:
        //TODO: [IN:25127] 	GoldFinger R1: CP: BP: F+C: Options Page: Incorrect car Pick Up information displayed.
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
//Step 13: Populate all the required information to reach Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
////Step 14: Scroll down to the Option content box and validate tha the Pick Up and Drop of time match with the Information displayed on Payment Page.
//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
////Step 15: Note the PNR and the Last Name then retrieve the booking through My Trips tab on the home page
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
////Step 16&17: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//
////Step 18: Scroll Up and click the PRINT RECEIPT link
//        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
////Step 19&20: On the my-trips/itinerary page locate the Options section and validate that:
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
    }

}