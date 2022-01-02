package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test CaseID: TC280779
//Title      : 346. E2E_FS_RT DOM 1 ADT 1 child -5_SW Change Airports Bundle Fare Direct Flight_STD_RT 1CO 1 CB_Any Seats_No Extras_CI Web_Credit Card
//Description: Validate user can complete booking by using parameters from the title
//Created By : Kartik chauhan
//Created On : 03-June-2019
//Reviewed By: Salim Ansari
//Reviewed On: 03-June-2019
//**********************************************************************************************

public class PRODTC280779 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"Production"})
    public void E2E_FS_RT_DOM_1_ADT_1child_5_SW_Change_Airports_Bundle_Fare_Direct_Flight_STD_RT_1CO_1CB_Any_Seats_No_Extras_CI_Web_Credit_Card (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC280779 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "ProdFSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String DEP_AIRPORT_CODE1  = "BWI";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String ARR_AIRPORT_CODE1  = "FLL";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "8";
        final String ADULT              = "1";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        // Bags page constant
        final String BAGS_FARE          = "Standard";
        final String BUNDLE_ITINERARY   = "Bundle It Discount";

        //seats page constant value
        final String DEP_SEATS          = "Standard|Standard";
        final String RET_SEATS          = "Standard|Standard";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        /******************************************************************************
         *******************************Voucher Section********************************
         ******************************************************************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //select a child with age between 15-18 years
        WebElement travellingChildSection = pageObjectManager.getHomePage().getChildTravelerPanel().get(0);
        //convert date in required format
        travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-696), "MM/dd/yyyy"));

        travellingChildSection.click();

        //close child popup
        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);


        WaitUtil.untilPageLoadComplete(getDriver());

        //Flight Availability Methods
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //New sear on Search Widget is made and new dates are selected
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE1, ARR_AIRPORTS, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //select a child with age between 15-18 years
        WebElement travellingChildSection1 = pageObjectManager.getHomePage().getChildTravelerPanel().get(0);
        //convert date in required format
        travellingChildSection1.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-696), "MM/dd/yyyy"));

        travellingChildSection1.click();

        //close child popup
        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Methods
        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart",
                scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText().replace("-",""));

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);

        //Seats page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//
//        /******************************************************************************
//         *************************Validation on Confirmation Page**********************
//         ******************************************************************************/
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        //Confirmation Code
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        //
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }

}