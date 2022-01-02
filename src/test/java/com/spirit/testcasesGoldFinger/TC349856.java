package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC349856
//Description: Task 27835: TC349856- 009 - MT - Flight + Hotel + Car - NEG Validate package booking cannot be modified or cancelled on web
//Created By: Gabriela
//Created On: 21-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC349856 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"MyTrips", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest", "BookIt","CheckedBags","NoSeats","CheckInOptions","Visa","AddEditBags","OptionalUI","PaymentUI","ReservationUI"})
    public void MT_Flight_Hotel_Car_NEG_Validate_package_booking_cannot_be_modified_or_cancelled_on_web(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349856 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
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
        final String DRIVER_AGE 		= "25+";


        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

        //Reservation Summary Page Constant Values
        final String MYTRIP_BAG         = "Bags";
        final String DEP_BAGS           = "Carry_0|Checked_1";
        final String SEATS              = "Purchase";
        final String DEP_SEATS          = "Standard|Standard";
        final String FARE_TYPE          = "Standard";

//- Step 1: Pre Requisite: Have/Create a package booking [Flight + Hotel + Car] outside of 90 days for 2 adults
        openBrowser(platform);

        /*** Home Page Methods **/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*** Flight Availability Page Methods **/
        //Storing flight information for next validation
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

        //Hotel Selection
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM","NA");

        //Car Selection
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

        //Save and Update pop up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*** Passenger Information Page **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value")+" "+pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /*** Bags Page Methods **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*** Seats Page Methods **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*** Options Page Methods **/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*** Payment Page Methods **/
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        /*** Confimration Page Methods **/
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();
//
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//
////- Step 2: Retrieve booking via My Trips
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////- Step 3: Verify the "Cancel" button and the "Change flights" link are NOT present.
//        // Package validation method need it
//        WaitUtil.untilPageLoadComplete(getDriver());
//        boolean buttonsFlag = true;
//        try {
//            if (pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().isDisplayed() && pageObjectManager.getReservationSummaryPage().getCancelItineraryButton().isDisplayed())
//                buttonsFlag = false;
//        } catch (Exception e) {}
//
//        ValidationUtil.validateTestStep("Validating there is not'Cancel Itinerary' and 'Change Flight' button", buttonsFlag);
//
////- Step 4: Proceed to update bags and seats
//        /*** Bags Page Methods **/
//        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_BAG);
//        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
//        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
//
//        /*** Seats Page Methods **/
//        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(SEATS);
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        pageMethodManager.getSeatsPageMethods().continueWithSeats();
//
//        /*** Options Page Methods **/
//        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//
////- Step 5: Complete modification and validate changes are saved
//        /*** Payment Page Methods **/
//        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        //Reservation Summary Page bag and seat validation
//       ValidationUtil.validateTestStep("Validating Bag section was updated on Reservation Summary Page",
//               getDriver().findElement(By.xpath("(//div[@ngmodelgroup='passengers']//following-sibling::span)[3]")).getText().equals("1 Checked Bag"));
//
////- Step 6: Access skyspeed to validate the modification saved and matches the web
//        //Invalid Step for Automation
//
////- Step 7: IMPORTANT: DO NOT FORGET to send your lead the booking details for cancellation.
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
    }
}