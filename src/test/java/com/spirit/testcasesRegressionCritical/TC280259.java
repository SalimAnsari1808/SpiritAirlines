package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Already
//Test Case ID: TC280259
//Test Name: 31653 460. E2E_9DFC_OWDOM 0 ADT 1 UMNR_DirectFlight_WheelChairDry_1CarryOn 1Checked_Seats_SS CI not sure_Voucher and credit card
// Created By: Kartik Chauhan
//Created On : 08-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**************************************************************************************************

public class TC280259 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Child" , "NineDFC" ,
            "NewFlightSearch" , "NonStop" , "BookIt" , "PassengerInfoSSR" , "CarryOn" , "CheckedBags" , "Standard" ,
            "Visa" , "Voucher" , "DynamicShoppingCartUI","OptionalUI"})
    public void E2E_9DFC_OWDOM_0_ADT1_UMNR_DirectFlight_WheelChairDry_1CarryOn_1Checked_Seats_SS_CI_not_sure_Voucher_and_credit_card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280259 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "UMNR9FC";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "ATL";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "0";
        final String CHILD  			= "1";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_DATE_1			= "5";
        final String DEP_FLIGHT 		= "Nonstop";
        final String FARE_TYPE			= "member";
        final String UPGRADE_VALUE      = "BookIt";

        //Passenger Information Constant Variables
        final String SSR_VALUE          = "OwnWheelChair-BatteryPoweredDryGelCell";

        //Bags Page Constant Values
        final String SELECT_BAGS        = "Carry_1|Checked_1";

        //Seat Page Constant
        final String DEP_SEAT           = "Standard";

        //Payment Page Constant Values
        final String TRAVEL_GUARD       = "NotRequired";
        final String CARD_TYPE          = "Visacard";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
        openBrowser(platform);
        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/

        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

        //TODO Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web return null names and are then invalid for use
        createVoucher();
        getDriver().navigate().refresh();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
//STEP--3
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillPassengerDOB(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectUMNRPopup();
//STEP--4
        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        //update Booking
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE_1, ARR_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillPassengerDOB(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        //Select Flights
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//STEP--5
        /****************************************************************************
         ******************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
//STEP--6
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR_VALUE);
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
//STEP--7
        pageMethodManager.getBagsPageMethods().selectDepartingBags(SELECT_BAGS);

        //wait till page load complete
        WaitUtil.untilPageLoadComplete(getDriver(),(long)1000);

        pageObjectManager.getBagsPage().getContinueWithBagsButton().click();
//STEP--8
        WaitUtil.untilPageLoadComplete(getDriver());

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilTimeCompleted(2000);

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);

        //Click on itinerary
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait till page load complete
        WaitUtil.untilPageLoadComplete(getDriver(),(long)1000);

        Double FlIGHTTOTAL  = Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_PRICE).toString().replace("$","").replace("TOTAL","").trim());
        Double BAGSTOTAL    = Double.parseDouble(pageObjectManager.getHeader().getBagsPriceItineraryText().getText().replace("$",""));
        Double SEATTOTAL    = Double.parseDouble(pageObjectManager.getHeader().getSeatsPriceItineraryText().getText().replace("$",""));
        Double OPTIONSTOTAL = Double.parseDouble(pageObjectManager.getHeader().getOptionsPriceItineraryText().getText().replace("$",""));

        //Constant Values to validate
        final String BACKGROUND_GRAY           = "rgba(239, 239, 239, 1)";
        final String BACKGROUND_YELLOW         = "rgba(255, 236, 0, 1)";
        final String FONT_BLACK                = "rgba(0, 0, 0, 1)";
        final String ITINERARYTOTAL            = String.format("%.2f", FlIGHTTOTAL + BAGSTOTAL + SEATTOTAL + OPTIONSTOTAL);
        final String JOIN_SAVE_TEXT            = "JOIN $9 FARE CLUB AND SAVE $";

        //Validate Shopping Cart is present
        ValidationUtil.validateTestStep("Verify user Shopping Cart is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));

        //Verify A Grey text block, left aligned icon of cart followed by verbiage in black font: Your Itinerary and right aligned $XXX (dynamic pricing).
        ValidationUtil.validateTestStep("Verify A Grey text block",
                getDriver().findElement(By.xpath("//div[contains(@class,'total-cost')]")).getCssValue("background-color"),BACKGROUND_GRAY);

        ValidationUtil.validateTestStep("Verify Your Itinerary is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getYourItineraryText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryTotalAmountText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing is Correct for Your Itinerary",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText(),"$" + ITINERARYTOTAL);

        //--Flight
        //verify Icon of an airplane
        ValidationUtil.validateTestStep("verify Icon of an airplane for flight is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryImage()));

        //verify flight label
        ValidationUtil.validateTestStep("verify flight label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryText()));

        //verify flight dynamic pricing
        ValidationUtil.validateTestStep("verify flight price is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightPriceItineraryText())
                        && pageObjectManager.getHeader().getFlightPriceItineraryText().getText().equals("$" + String.format("%.2f", FlIGHTTOTAL)));
        //verify arrow
        ValidationUtil.validateTestStep("verify arrow  is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowFlightItineraryImage()));
//STEP--4
        //--Bags
        //Verify a suitcase icon is displayed
        ValidationUtil.validateTestStep("Verify a suitcase icon for bags is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBagsItineraryImage()));

        //verify Bags label is displayed
        ValidationUtil.validateTestStep("verify Bags label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBagsItineraryText()));

        //Verify price
        ValidationUtil.validateTestStep("Verify Bags Total matches shopping cart",
                pageObjectManager.getHeader().getBagsPriceItineraryText().getText(),"$" + String.format("%.2f",BAGSTOTAL));

        //Verify chevron is displayed
        ValidationUtil.validateTestStep("Verify chevron is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowBagsItineraryImage()));
//STEp--5
        Double SEATTOTAL1    = Double.parseDouble(pageObjectManager.getHeader().getSeatsPriceItineraryText().getText().replace("$",""));

        //--Seat
        //Verify a Chair icon is displayed
        ValidationUtil.validateTestStep("Verify a chair icon for seats is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSeatsItinerarayImage()));

        //verify Seats label is displayed
        ValidationUtil.validateTestStep("verify Seats label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSeatsItinerarayTextText()));

        //Verify price
        ValidationUtil.validateTestStep("Verify Seats Total matches shopping cart",
                pageObjectManager.getHeader().getSeatsPriceItineraryText().getText(),"$" + String.format("%.2f", SEATTOTAL1));

        //Verify chevron is displayed
        ValidationUtil.validateTestStep("Verify chevron is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowSeatsItineraryImage()));

        //click on Itinerary
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         **********************Options Page Methods**********************************
         ****************************************************************************/
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of UMNR on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        //TODO Bug 23961: PROD: CP: MT: Voucher - Vouchers created on web return null names and are then invalid for use
        pageMethodManager.getPaymentPageMethods().applyVoucherNumber();
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }

    private void createVoucher() {
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String LOGIN_ACCOUNT      = "UMNR9FC";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAX";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "0";
        final String CHILDREN           = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "member";
        final String UPGRADE_VALUE      = "BookIt";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "Notrequired";

        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillPassengerDOB(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        //Flight Availability Methods
        WaitUtil.untilPageLoadComplete(getDriver());
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

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        //MyTrip Page
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}