package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90712
//Description : CP_MT_INT_U.S. Residents_outside 24HRs
//Created By  : Kartik chauhan
//Created On  : 11-July-2019
//Reviewed By : Salim Ansari
//Reviewed On : 11-July-2019
//**********************************************************************************************

public class TC90712 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "RoundTrip" , "DomesticInternational" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" , "CarryOn" , "CheckedBags" , "NoSeats" ,"ShortCutBoarding", "CheckInOptions" , "MasterCard" ,"AddEditBags", "PaymentUI","TravelInsurancePopUp"})
    public void CP_MT_INT_US_Residents_outside_24HRs(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90712 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        //Home Page Constant Values for Reservation Credit
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAS";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "2";
        final String ARR_DATE           = "5";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String ARR_Flight         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";
        final String TRAVEL_GUARD1      = "Required";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //Reservation page constant values
        final String BUY_BAGS_LINK      = "Bags";
        final String BUY_SEAT_POPUP     = "DontPurchase";

        //Bags Page constant values
        final String MYTRIP_DEP_BAGS 	= "Carry_1|Checked_2";
        final String MYTRIP_RET_BAGS	= "Carry_1|Checked_2";

        //open browser
        openBrowser(platform);
//STEP--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//STEP--2 & 3
        /*********************************************Start OF Manage Travel**********************/
        //login to Manage Travel Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--4
        // Bag pop up.  purchase
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(BUY_BAGS_LINK);
        pageMethodManager.getBagsPageMethods().selectDepartingBags(MYTRIP_DEP_BAGS);

        //select same bags for all flights
        pageObjectManager.getBagsPage().getKeepSameBagsLabel().get(0).click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
//STEP--5
        //do not purchase seat
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(BUY_SEAT_POPUP);

        //Continue without extras
        pageObjectManager.getOptionsPage().getContinueToPurchaseButton().click();

//STEP--6 & 7
        //PAYMENT PAGE
        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //Put wait for 3 second
        WaitUtil.untilTimeCompleted(3000);

        //verify Insurance Private Policy link is not broken
        ValidationUtil.validateTestStep("Validating Insurance Private Policy is displaying on Payment Page",
                pageObjectManager.getPaymentPage().getInsurancePolicyLink().isDisplayed());

        //make a click on link to verify its URL
        pageObjectManager.getPaymentPage().getInsurancePolicyLink().click();

        //create constant for privacy policy link
        final String POLICY_URL_DOM    = "https://webservices.travelguard.com/Product/FileRetrieval.aspx?CountryCode=US&StateCode=AL&ProductCode=944301&PlanCode=NW&FileType=PROD_PLAN_GM";

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //move focus from one window to another
        TestUtil.switchToWindow(getDriver(),1);

        //verify correct url of Privacy policy link
        ValidationUtil.validateTestStep("Validating Payment Page right URL",
                getDriver().getCurrentUrl(),POLICY_URL_DOM);

        //verify correct url of Privacy policy link is not broken
        ValidationUtil.validateTestStep("Validating correct url of Privacy policy link",
                TestUtil.verifyLinks(getDriver().getCurrentUrl()));

        //Close second window
        getDriver().close();

        //make focus on first window
        TestUtil.switchToWindow(getDriver(),0);

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP--8
        //Validating Travel Insurance information
        List<String> textBeingValidated = new ArrayList<String>();
        textBeingValidated.add("100% Trip Cost Cancellation");
        textBeingValidated.add("125% Trip Cost Trip Interruption");
        textBeingValidated.add("$500 Missed Connection");
        textBeingValidated.add("$500 Trip Delay");
        textBeingValidated.add("$500 Baggage & Personal Effects");

        //Verify All section under TG section
        WaitUtil.untilTimeCompleted(1200);

        //method to verify all the above list
        pageMethodManager.getPaymentPageMethods().travelGuardVerbiagesAndLink(textBeingValidated);

        //fill payment details
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        //Accept booking
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //Select Travel Guard
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD1);
//STEP--9
        /******************************************************************************
         ***********Validation on Manage Travel Reservation Summary Page***************
         ******************************************************************************/
        //reservation Summary URL
        final String RESERVATION_URL    = "my-trips/reservation-summary";

        //verify user on Reservation summary OPage
        ValidationUtil.validateTestStep("Verify user navigated to Manage Travel Reservation Summary Page,",
                getDriver().getCurrentUrl(),RESERVATION_URL);
//STEP-10
        //****************************Belongs to SKYSPEED*************************
    }
}