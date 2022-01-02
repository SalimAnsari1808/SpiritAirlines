package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373408
//Description: Task 27864: TC373408 - 003 - CP - Manage Travel Tab - No Modifications - Flight + Car - Validate a customer can retrieve a booking for a NonStop Flight
//Created By: Gabriela
//Created On: 30-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373408 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightCar", "Outside21Days", "Adult", "NineDFC","NonStop", "BookIt", "NoBags","NoSeats","CheckInOptions","Visa","ReservationUI"})
    public void CP_Manag_Travel_tab_No_Modifications_Flight_Car_Validate_a_customer_can_retrieve_a_booking_for_a_NonStop_Flight(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373408 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values:
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_TYPE   		= "VisaCard";

        //Reservation Summary Page Constant Values
        final String WARNING_INFO       = "To make changes to your itinerary, please contact Customer Service directly at 1.954.698.0125.";


//- Step 1: Open goldfinger website test environment on consumer portal
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Log in as a 9DFC member
        /*** Home Page Method **/
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//- Step 3: Book a | flight + car | RT | DOM | NonStop | 1 Adt | outside 48 hrs | no bags | No seats | No extras | record pax last name and PNR
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        /*** Flight Availability Page Method **/
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        /*** Passenger Information Page Method **/
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        /*** Bags Page Method **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        /*** Seats Page Method **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        /*** Options Page Method **/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        /*** Payment Page Method **/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//        /*** Confirmation Page Method **/
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//
////- Step 4: Click on "My Trips" tab
////- Step 5: Input pax last name and PNR then click "CONTINUE"
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////- Step 6: Verify all flight Information is correct
////- Step 7: Verify all car information is correct
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
//
//        ValidationUtil.validateTestStep("Validating Red Warning information",
//                getDriver().findElement(By.xpath("//div[contains(@class,'card modify-vacation-banner')]")).getText(),WARNING_INFO);
//
//        //Canceling CAR
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
    }
}