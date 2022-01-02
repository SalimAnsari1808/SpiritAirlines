package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC90876
//Test Name: Reservation Summary_CP_MT_Cancel Reservation Page Wireframe Links Test scenario
//Description: Links validation on Cancel Reservation page
//Created By: Gabriela
//Created On: 17-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 20-May-2019
//**********************************************************************************************
public class TC90876 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "CancelReservationUI"})
    public void Reservation_Summary_CP_MT_Cancel_Reservation_Page_Wireframe_Links_Test_scenario(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90876 under SMOKE Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "OneWay";
        final String ARR_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String DEP_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "BOS";
        final String DEP_DATE               = "4";
        final String ARR_DATE               = "NA";
        final String ADULT                  = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "Nonstop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_FARE           = "BookIt";

        //Options Page Constant Values
        final String CHECKIN_VALUE          = "CheckInOption:MobileFree";

        //Payment Page Constant Value
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GAURD           = "NotRequired";

        //Reservation Summary Page Constant Values
        final String URL                    = "my-trips/reservation-summary";
        final String cancelURL              = "my-trips/cancel-reservation";
        final String canceledURL            = "my-trips/cancel-reservation";
        final String linkURL                = "https://customersupport.spirit.com/hc/en-us/articles/202097956-How-do-I-find-my-Future-Travel-Voucher-or-Reservation-Credit-code-";

//Step 1: PreReq: A flight booked within 24hrs with a departure date within 7 days.

        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page method
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(CHECKIN_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();

//-- Step 2: User lands on Reservation Summary page
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating user reached to Reservation Summary Page",
                getDriver().getCurrentUrl(),URL);

//-- Step 3: Click on CANCEL Itinerary on the bottom of the page
        pageObjectManager.getReservationSummaryPage().getCancelItineraryButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 4: User lands on Cancel Reservation page
        ValidationUtil.validateTestStep("Validating user reached to Cancel Reservation page",
                getDriver().getCurrentUrl(),cancelURL);

//-- Step 5: Click on the Cancel Reservation button
        pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();

        WaitUtil.untilTimeCompleted(2000);

//-- Step 6: On the modal (pop up) click the X on the upper right corner
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpCloseButton().click();

//-- Step 7: User lands on Reservation Summary page (Invalid step)
//-- Step 8: Click on CANCEL ITINERARY on the bottom of the page  (Invalid step)

//-- Step 9: User lands on Cancel Reservation page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating user after closing Reservation Cancel Popup is on Cancel Reservation Page ",
                getDriver().getCurrentUrl(),cancelURL);

//-- Step 10: Click on the Cancel Reservation button
        pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();

//-- Step 11: On the modal (pop up) click Keep My Reservation inside the modal (pop up)
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpKeepMyReservationButton().click();

//-- Step 12: User lands on Reservation Summary page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating user after clicking Keep my Reservation button on Reservation Cancel Popup is on Cancel Reservation Page",
                getDriver().getCurrentUrl(),URL);

//-- Step 13: Click on CANCEL ITINERARY on the bottom of the page
        pageObjectManager.getReservationSummaryPage().getCancelItineraryButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 14: User lands on Cancel Reservation page
        //WaitUtil.untilPageLoadComplete(getDriver());Validating user is taken to the right the right URL
        ValidationUtil.validateTestStep("Validating user is taken to the right the right URL",
                getDriver().getCurrentUrl(),cancelURL);

//-- Step 15: Click on the Cancel Reservation button
        pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();

//-- Step 16: On the modal (pop up) click Cancel Reservation inside the modal (pop up)
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpCancelReservationButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating user after clicking cancel reservation on Reservation cancel Popup is on Cancel Reservation Page",
                getDriver().getCurrentUrl(),canceledURL);

//-- Step 17: Click on Learn how to use your credit in the Voucher summary content box
        pageObjectManager.getCancelReservationPage().getReservationCancellationLearnHowToUseYourCreditLink().click();

        //Validate the user is taken to the right URL
        TestUtil.switchToWindow(getDriver(),1);
        ValidationUtil.validateTestStep("Validating user is taken to the right the right URL",
                getDriver().getCurrentUrl(),linkURL);

        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
    }
}

