package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91322
//Test Name: CP_MT_Itinerary Page_Added TG through Edit bag
//Description: Booking outside 24 hours, can select bags and Travel Guard on My Tris path and
// Travel Guard is verified on Summary page
//Created By : Gabriela
//Created On : 2-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 3-May-2019
//**********************************************************************************************

public class TC91322 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" ,"WithIn21Days", "Adult" , "Guest" , "NonStop" ,"BookIt" ,
            "CarryOn" , "NoSeats" ,"ShortCutBoarding", "CheckInOptions" , "Visa" , "CheckedBags" ,"AddEditBags", "TravelInsuranceBlock","ConfirmationUI"})
    public void CP_MT_Itinerary_Page_Added_TG_through_Edit_bag(@Optional("NA") String platform) {
        //******************************************************************************
        //*******************Navigate to My Trip Reservation Summary Page***************
        //******************************************************************************/
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91322 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "BOS";
        final String DEP_DATE           = "7";
        final String ARR_DATE           = "10";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1";
        final String BAGS_FARE          = "Standard";

        //Options page constant values
        final String FREE_CHECKIN       = "CheckInOption:MobileFree";

        //Confirmation page constant values
        final String BOOKING_STATUS     = "Confirmed";

        //Reservation Summary Bag Section
        final String MYTRIP_BAG         = "Bags";

        //Payment page constant values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //MT Bags Constant Values
        final String MT_DEP_BAG         = "Carry_1|Checked_1";
        final String MT_RET_BAG         = "Checked_1";
        final String MT_BAGS_FARE       = "Standard";

        //MT Seat popup
        final String MT_SEAT_POPUP      = "DontPurchase";

        //MT Payment Page Constant values
        final String TRAVEL_GUARD1 		= "Required";

        //open browser
        openBrowser(platform);

        //--Step 1 & 2: Booking outside 24 hours with 1 carry on
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page
        pageMethodManager.getOptionsPageMethods().selectOptions(FREE_CHECKIN);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //--Step 3: Select and Log In into My Trips
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //--Step 4: Select Edit Bags
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_BAG);

        //--Step 5: Add 1 checked bag per journey
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().selectDepartingBags(MT_DEP_BAG);
        pageMethodManager.getBagsPageMethods().selectReturnBags(MT_RET_BAG);
        pageMethodManager.getBagsPageMethods().selectBagsFare(MT_BAGS_FARE);

        //--Step 6: Don't purchase seats from the pop up
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(MT_SEAT_POPUP);

        //--Step 7: Continue Button on Options page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getOptionsPage().getContinueToPurchaseButton().click();

        //--Step 8:
        WaitUtil.untilPageLoadComplete(getDriver());

        //Add Travel Insurance
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD1);

        //fill card details
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE); //Making payment with Visa Credit Card

        //accept terams and condition
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //******************************************************************************
        //*******************Validation On My Trip Reservation Summary Page*************
        //******************************************************************************/

        //--Step 9: Invalid

        //declare constant used in validation
        final String RESERVATION_URL        = "my-trips/reservation-summary";
        final String TRAVEL_POLICY          = "buy.uat.travelguard.com";

        ValidationUtil.validateTestStep("Validating the customer is taken to the My Trip Reservation Page",
                getDriver().getCurrentUrl(),RESERVATION_URL);

        //-Step 10:
        //verify TG section
//        pageMethodManager.getConfirmationPageMethods().verifyTravelInsuranceSection();

        //Validating customer is taken to the right URL after select Online policy link
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getConfirmationPage().getTravelInsurancePolicyOnlineLink().click();

        WaitUtil.untilTimeCompleted(1200);
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating the policy page right URL",
                getDriver().getCurrentUrl().contains(TRAVEL_POLICY));

        getDriver().close();

        TestUtil.switchToWindow(getDriver(),0);

        //--Step 11: Invalid
    }
}
