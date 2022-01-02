package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
// Test Case ID: TC355441
// Description: 023. E2E_Guest_OW Dom 1 ADT 1 INFT_Thru Flight_Standard_Bundle Bags_Bundle Seats_SS CI Web_Reservation Credit, credit Card
// Created By : Kartik Chauhan
// Created On : 20 May, 2019
// Reviewed By: Salim Ansari
// Reviewed On: 21 May, 2019
//**********************************************************************************************
public class TC355441 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "InfantLap" , "Guest" ,
                     "Through" ,"BundleIt" , "DynamicShoppingCartUI" , "CarryOn" , "CheckedBags" , "BagsUI" , "Standard" ,
                     "FlightFlex" , "ShortCutBoarding" , "ShortCutSecurity" , "OptionalUI" , "MasterCard" , "ReservationCredit"})
    public void E2E_Guest_OW_Dom_1_AD__1_INFT_Thru_Flight_Standard_Bundle_Bags_Bundle_Seats_SS_CI_Web_Reservation_Credit_credit_Card(@Optional("NA")String platform) {
        /******************************************************************************
         *********************Navigation to Confirmation Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC355441 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "ATL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "23";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "1";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE		= "BundleIt";

        //Bags Page Constant
        final String BUNDLE_ITINERARY   = "Bundle It Discount";
        final String CHECKED_BAG    	= "Included";

        //Seats Page Constant Values
        final String DEP_SEATS          = "Standard";

        //Options Page Constant Value
        final String OPTION_VALUE       = "ShortCutSecurity,NotRequired";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "NotRequired";
        final String CARD_TYPE			= "MasterCard";

//STEP--1
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //create resCredit
        createResCredit();

        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--2 & STEP--3
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart",
                scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(),
                        pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText().replace("-",""));

        //Bags Page Methods
        for(WebElement checkedBag : pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText()){
            //verify checked is already included
            ValidationUtil.validateTestStep("Verify Checked Bag is included with Boost It on Flight Availiability Page",
                    checkedBag.getText(),CHECKED_BAG);
        }

        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seat Page Method
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Option Page Methods
        ValidationUtil.validateTestStep("Verify Shortcut Boarding is Selected on Option Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        ValidationUtil.validateTestStep("Verify Shortcut Boarding Remove button is not visible on Option Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardRemoveButton()));

        ValidationUtil.validateTestStep("Verify Check-In Option is disabled  on Option Page",
                pageObjectManager.getOptionsPage(). getCheckInOptionCardPanel().getAttribute("class"), "disabled");

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Purchase Page Methods
//        pageMethodManager.getPaymentPageMethods().applyReservationCredit();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //confirmation page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    }
    private void createResCredit() {
        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";
        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_CARD        = "NotRequired";
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_CARD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}
