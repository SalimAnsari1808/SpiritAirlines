package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:15876] - CP: BP: Options page:  Cars or Hotels sections are not displayed for an International destination (CUN)
//Test Case ID: TC376026
//Description: Task 27797: TC376026- US 22066 - 002 - CP - Pick-Up and Drop-Off Format - Car Upsell - Validate the new format for a booking with Dollar
//Created By: Gabriela
//Created On: 29-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On:17-Dec-2019
//**********************************************************************************************
public class TC376026 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticInternational", "Outside21Days", "Adult","InfantLap", "Guest","NonStop", "BookIt",
                    "OptionalUI", "CarsUI", "NoBags", "NoSeats","Visa","PaymentUI","ConfirmationUI","ReservationUI","ItineraryReceiptUI"})
    public void CP_Pick_Up_and_Drop_Off_Format_Car_Upsell_Validate_the_new_format_for_a_booking_with_Dollar(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC376026 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_URL        = "/BOOK/OPTIONS";

        //Payment Page Constant values
        final String PAYMENT_URL        = "/book/payment";
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

        //Reservation Page Constant Values
        final String RES_SUM_URL        = "/my-trips/reservation-summary";
        final String ITINERARY_URL      = "my-trips/itinerary";

//- Step 24: Access Goldfinger Home Page test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 1: Book RT DOM-INT| Outside 24 hours | 2 ADT 1 Lap | Select "Search Flight"
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

//- Step 2: Fill DOB for the Lap child PAX, select "Continue"
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 3: Select first flights, click "Continue"
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep",DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 4: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 5: Fill in all Pax info
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        String firstName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value").toUpperCase();
        String lastName = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value").toUpperCase();
        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

//- Step 6: Select "CONTINUE"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7 & 8: Scroll to the bottom of the page and select "Continue Without Adding Bags" & select "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 9: Select "Continue without Selecting Seats"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 10: Browse carousel until you see a Dollar car and click the View link
        //Switching to Alamo due on Cancun Dollar is not available
        pageObjectManager.getCarPage().getCarsCarouselAddCarButton().get(0).click();

//- Step 11: Under the section Who's driving? Select the Primary driver, and click the Book Car button.
        WaitUtil.untilPageLoadComplete(getDriver());
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(),firstName + " " + lastName);
        WaitUtil.untilTimeCompleted(1000);

        //pageObjectManager.getCarPage().getBookCarButton().get(0).click();
        pageObjectManager.getCarPage().getWhoSDrivingVerifyAndBookThisCarButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating user is taken back to Options page after car selected",
                getDriver().getCurrentUrl(),(OPTIONS_URL));

        ValidationUtil.validateTestStep("Validating car carousel is replaced with the selected car tile",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarSelectedPanel()));

//- Step 12: Verify that the Flight Flex is available after the user selects a car
        ValidationUtil.validateTestStep("Validating flight Flex is Available after the car was selected",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//- Step 13: Select the Check-In Option and click the Continue button
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 14: On the Payment page locate the Options section and validate that:
    //Pick Up: verbiage should be displayed in bold right aligned the date and time should match with the arrival date and time of the selected departure:
    //Day Month Date Year, Time (HH:MM AM or PM)
//- Step 15: On the Payment page locate the Options section and validate that:
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating user is taken to Payment Page",
                getDriver().getCurrentUrl(),(PAYMENT_URL));

        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

//- Step 16: Populate all the required information to reach Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);

        //TODO: Package booking exceed the $1500
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
////- Step 17: Scroll down to the Option content box and validate tha the Pick Up and Drop of time match with the Information displayed on Payment Page.
//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
//
////- Step 18: Note the PNR and the Last Name then retrieve the booking through My Trips tab on the home page
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////- Step 18: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that://
//    //Pick Up: verbiage should be displayed in bold right aligned the date and time should match with the arrival date and time of the selected departure:
//    //Day Month Date Year, Time (HH:MM AM or PM)
//
////- Step 20: On the https://spirit.com/my-trips/reservation-summary page locate the Options section and validate that:
//    //Drop off: verbiage should be displayed in bold right aligned the date and time should match the departing date and time of the selected return:
//    //Day Month Date Year, Time (HH:MM AM or PM)
//        WaitUtil.untilPageLoadComplete(getDriver());
//        ValidationUtil.validateTestStep("Validating user is taken back to Reservation Summary Page page after car selected",
//                getDriver().getCurrentUrl(),(RES_SUM_URL));
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//
////- Step 21: Scroll Up and click the PRINT RECEIPT link
//        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
//        WaitUtil.untilPageLoadComplete(getDriver());
//        //verify user on Navigated to Itinerary Receipt page
//        ValidationUtil.validateTestStep("Verify user navigated Print Receipt Page,",
//                getDriver().getCurrentUrl(), ITINERARY_URL);
//
//        //verify The title of the page is Your Itinerary Receipt
//        ValidationUtil.validateTestStep("verify The title of the page is Your Itinerary Receipt",
//                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptText()));
//
////- Step 22: On the my-trips/itinerary page locate the Options section and validate that:
//    //Pick Up: verbiage should be displayed in bold right aligned the date and time should match with the arrival date and time of the selected departure:
//    //Day Month Date Year, Time (HH:MM AM or PM)
////- Step 23: On the my-trips/itinerary page locate the Options section and validate that:
//    //Drop off: verbiage should be displayed in bold right aligned the date and time should match the departing date and time of the selected return:
//    //Day Month Date Year, Time (HH:MM AM or PM)
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();


    }
}