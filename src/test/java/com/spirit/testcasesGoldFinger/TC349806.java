package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC349806
//Description: Task 27783: TC349806 - 008 - CI - Flight Only - Payment Page - Validate the Spanish verbiage on Payment page when booking a car during check-in
//Created By: Gabriela
//Created On: 3-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC349806 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn", "DomesticDomestic", "WithIn7Days", "Adult", "Guest", "NonStop" ,"BookIt","NoBags","NoSeats","CheckInOptions"
    ,"Discover","Cars","PaymentUI","Spanish"})
    public void CI_Flight_Only_Payment_Page_Validate_the_Spanish_verbiage_on_Payment_page_when_booking_a_car_during_check_in(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349806 under GoldFinger Suite on " + platform + " Browser", true);
        }
//Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "1";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values:
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD       = "NotRequired";
        final String CARD_DETAIL        = "DiscoverCard1";

        //Reservation Summary Page constant values
        final String CHECKIN_BAG_PURCHASE   = "DontPurchase";
        final String CHECKIN_SEAT_PURCHASE  = "DontPurchase";
        final String PAYMENT_VERBIAGE       = "Pagado en el mostrador de alquiler de autos";

//- Step 1: Access test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Create a flight only DOM booking within 24 hrs
        /*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        /*** Flight Availability Page **/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        /*** Passenger Information Page **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        /*** Bags Page **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        /*** Seats Page **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        /*** Options Page **/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        /*** Payment Page **/
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        /*** Confirmation PAge **/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//- Step 3: Retrieve PNR via check-in
        pageMethodManager.getHomePageMethods().loginToCheckIn();

//- Step 4: Switch to Spanish
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getSelectedLanguage().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 5: Start the check-in process and accept the car when the offer shows
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //do not select Bag
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECKIN_BAG_PURCHASE);

        //do not select Seat
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(CHECKIN_SEAT_PURCHASE);

//- Step 6: Select a car and continue with the check-in process until you land on the Payment page
        //handle car section
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getLastChanceBookCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Travel Guard Pop UP
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

//- Step 7: On the Payment page, below the TOTAL ADEUDADO section, validate the verbiage "Pagado en el mostrador de alquiler de autos"
        String paymentVerbiage = getDriver().findElements(By.xpath("(//app-car-price-info-summary//div)[6]//p")).get(0).getText();
        System.out.println("paymentVerbiage: " + paymentVerbiage);
        ValidationUtil.validateTestStep("Validating right verbiage is displayed on the total breakdown", paymentVerbiage.toUpperCase(),PAYMENT_VERBIAGE.toUpperCase());

        //TODO: not allowed to completing bookings with car withing 24 hours
//- Step 8: Complete check-in process.
//- Step 9: Validate the Itinerary Page is displaying the selected car and the details
//- Step 10: Access Skyspeed and validate the booking details match the web
    }
}