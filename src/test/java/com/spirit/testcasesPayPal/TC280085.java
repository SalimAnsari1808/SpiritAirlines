package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280085
//Description: PayPal - CP: BP: Payment Page: Validate PayPal option suppresses when $9FC Trial is selected and displays by deselecting it.
//Created By: Un Fai Chan
//Created On: 12/10/2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC280085 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Flight", "WithIn7Days", "Adult", "FreeSpirit",
            "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "ReservationUI", "PayPal"})
    public void PayPal_CP_BP_Payment_Page_Validate_PayPal_option_suppresses_when_9FC_Trial_is_selected_and_displays_by_deselecting_it(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280085 under PayPal Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        // Flight details constant values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page constant values
        final String CARD_DETAIL        = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Step 1: On the web, start booking a OW DOM as a FS member for  1 Pax, no bags, no seats, no extras and land on the Payment page
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Step 2: Select the "Join the $9 Fare Club 60-day Trial Membership for exclusive fares and bag discounts, on your next booking!"
        //payment page methods
        pageObjectManager.getPaymentPage().getTrialMembershipLabel().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //finish 9FC sign up process
        pageObjectManager.getPaymentPage().getMembershipSignUpButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().get9FCPopUpChoosePassWordTextBox().sendKeys("!a12345Z!");
        pageObjectManager.getPaymentPage().get9FCPopUpConfirmPassWordTextBox().sendKeys("!a12345Z!");
        pageObjectManager.getPaymentPage().get9FCPopUpSignUpWithEmailButton().click();

        // TODO:: Active bug: PayPal option is still showing and complete the following steps when PayPal option is available
        //Step 3: Scroll down to the TOTAL DUE section and verify there isn't a "Checkout with PayPal" button displaying
        //Step 4: Deselect the "Join the $9 Fare Club 60-day Trial Membership for exclusive fares and bag discounts, on your next booking!"
        //Step 5: IGNORE the following steps if testing in production. No PNR is needed to validate this functionality
        //Proceed to pay with PayPal option
        //Step 6: Check PNR in Skyspeed and validate the comment PayPal is displaying on the reservation
        //Step 7: Validate the PayPal payment is displaying properly under the Payment Section in Skyspeed

    }
}
