package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC379793
//Test Name: CP_MT_Itinerary Page_Added TG through Add Seat
//Description: Booking outside 24 hours, can select seats and Travel Guard on My Tris path and
// Travel Guard is verified on Summary page
//Created By : Gabriela
//Created On : 2-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 3-May-2019
//**********************************************************************************************
public class TC379793 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" ,"WithIn21Days", "Adult" , "Guest" , "NonStop" ,"BookIt" ,
            "NoBags" , "Standard" , "CheckInOptions" , "Visa" , "AddEditSeats" , "TravelInsuranceBlock","ConfirmationUI"})
    public void CP_MT_Itinerary_Page_Added_TG_through_Add_Seat(@Optional("NA") String platform) {
        //******************************************************************************
        //*******************Navigate to My Trip Reservation Summary Page***************
        //******************************************************************************/
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC379793 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ACY";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "10";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options page constant values
        final String FREE_CHECKIN       = "CheckInOption:MobileFree";

        //Payment page conatnst values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation page constant values
        final String BOOKING_STATUS     = "Confirmed";

        //Reservation Summary Seat Section
        final String MYTRIP_SEAT        = "Seats";

        //MT Bags Pop Up Constant
        final String MYTRIP_BAGS_POPUP   = "DontPurchase";

        //MT Seats Page BFS
        final String MYTRIP_DEP     = "Standard";
        final String MYTRIP_RET     = "Standard";

        //MT Payment Page Constant values
        final String TRAVEL_GUARD1 		= "Required";

        //open browser
        openBrowser(platform);

        //--Step 1 and 2: Booking outside 24 hours, without seats or Travel Insurance.
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(FREE_CHECKIN);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();


        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //--Step 3 Log In on My Trips
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());

        //--Step 4: Click on Add Seat hyperlink
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_SEAT);

        //--Step 5: Selecting Regular seats
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(MYTRIP_DEP);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(MYTRIP_RET);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //--Step 6: Do not select bags from the pop up
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(MYTRIP_BAGS_POPUP);

        //--Step 7: Continue Button on Options page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageObjectManager.getOptionsPage().getContinueToPurchaseButton().click();

        //--Step 8:
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD1); //Selecting Travel Guard

        //fill card details
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE); //Making payment with Visa Credit Card

        //accept terams and condition
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //******************************************************************************
        //*******************Validation On My Trip Reservation Summary Page*************
        //******************************************************************************/
        //declare constant used in validation
        final String RESERVATION_URL        = "my-trips/reservation-summary";
        final String TRAVEL_POLICY          = "buy.uat.travelguard.com";

        ValidationUtil.validateTestStep("Validating the customer is taken to the My Trip Reservation Page",
                getDriver().getCurrentUrl(),RESERVATION_URL);

        //--Step 9: Invalid

        //-Step 10:
        //verify TG section
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
//        pageMethodManager.getConfirmationPageMethods().verifyTravelInsuranceSection();

        //Validating customer is taken to the right URL after select Online policy link
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on policy link
        pageObjectManager.getConfirmationPage().getTravelInsurancePolicyOnlineLink().click();

        //wait for 1.2 sec
        WaitUtil.untilTimeCompleted(1200);

        //switch to second window
        TestUtil.switchToWindow(getDriver(),1);

        //verify policy page url
        ValidationUtil.validateTestStep("Validating the user is navigated to Policy Page URL",
                getDriver().getCurrentUrl(),TRAVEL_POLICY);

        //close current browser
        getDriver().close();

        //swicth back to default browser
        TestUtil.switchToWindow(getDriver(),0);

        //--Step 11: Invalid

    }
}
