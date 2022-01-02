package com.spirit.testcasesPayPal;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC371592
//Description: PayPal- CP: BP: Payment Page: NEG - Validate the PayPal option is suppressed when the user is joining 9FC membership
//Created By: Un Fai Chan
//Created On: 12/10/2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC280083 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn", "Guest", "OneWay", "DomesticDomestic", "WithIn7Days", "Adult", "NonStop", "NineDFC",
            "BookIt", "NoBags", "NoSeats", "CheckInOptions", "VisaCard", "HomeUI", "PayPal"})
    public void PayPal_CP_BP_Payment_Page_NEG_Validate_the_PayPal_option_is_suppressed_when_the_user_is_joining_9FC_membership(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to CheckHome Page*********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280083 under Paypal Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
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

        //Step 1: On the web, start booking a OW DOM as a FS member for  1 Pax, no bags, no seats, no extras.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Log in as a 9DFC member
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

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
        // Step 2: Once on the Options Page, select to join 9FC from the Dynamic Shopping Cart
        pageObjectManager.getHeader().getJoinFareClubAndSaveCarrot().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getJoinNowAndSaveItineraryButton().click();


        //Step 3: Select a check in option and continue to the payment page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        //Step 4: Scroll down to the TOTAL DUE section and verify there isn't a "Check out with PayPal" button displaying
        ValidationUtil.validateTestStep("Check out with PayPal Option is not showing",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPayPalButton()));
    }
}