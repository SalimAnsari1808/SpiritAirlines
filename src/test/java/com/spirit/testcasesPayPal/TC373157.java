package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373157
//Test Name: Vacation_F + C: Payment Page: Complete_a_Vacation_Flight_+_Car_booking_and_pay_with_PayPal_IE
//Description: Payment Page: Validate booking payment with PayPal and a Reservation Credit
//Created By: Manasa Tilakraj
//Created On: 12-DEC-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373157 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip","FlightCar", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "BookIt",
            "NoBags", "NoSeats","CheckInOptions", "Visa"})
    public void Complete_a_Vacation_Flight_Car_booking_and_pay_with_PayPal_IE(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373179 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE = "English";
        final String JOURNEY_TYPE = "Vacation";
        final String TRIP_TYPE = "Flight+Car";
        final String DEP_AIRPORTS = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS = "AllLocation";
        final String ARR_AIRPORT_CODE = "LGA";
        final String DEP_DATE = "31";
        final String ARR_DATE = "33";
        final String ADULT = "1";
        final String CHILD = "0";
        final String INFANT_LAP = "0";
        final String INFANT_SEAT = "0";
        final String DRIVER_AGE = "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD = "NotRequired";
        final String CARD_DETAIL = "VisaCard";
        final String PAYPAL_ACCOUNT     = "AccountPayPalEmail";
        final String PAYPAL_TYPE        = "Credit Union";

        //Step 1: Have PayPal account with funds
        //Go to Spirit home page in test environment
        openBrowser(platform); //IE browser
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        // Step 2: Start creating a Vacation booking | Flight + Car |  RT | DOM | 1 Pax | No Bags | No Seats | booking
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //FA page, store all flight information for vacation booking
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

        //On select Your Car page select "BOOK CAR" inside the box of the rental car you choose.
        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on Book Car Button
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");

        //Select "BOOK IT"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //continue without bags and seats
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Step 3: continue to payment page
        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Select no to travel insurance, fill out all payment info, agree to HAZMAT terms and condition. click on "BOOK TRIP"
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

        //Step 4: Verify there is a "Checkout with PayPal" Button
        ValidationUtil.validateTestStep("PayPal Button is showing on the checkout page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayPalButton()));

        //Step 5:Click the PayPal button and proceed with the login and payment selection within the PayPal modal
        pageObjectManager.getPaymentPage().getPayPalButton().click();
        pageMethodManager.getPaymentPageMethods().loginToPayPal(PAYPAL_ACCOUNT);
        WaitUtil.untilTimeCompleted(1000);
        pageMethodManager.getPaymentPageMethods().payWithPayPal(PAYPAL_TYPE);

        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        //Step 6: Take screenshot of payment page

        // Step 7: Complete booking and verify confirmation page
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        String PNR = scenarioContext.getContext(Context.CONFIRMATION_CODE).toString();
        System.out.println(PNR);


    }
}
