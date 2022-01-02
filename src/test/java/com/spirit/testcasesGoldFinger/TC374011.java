package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test Case ID: TC374011
//Description: Task 27802: TC374011- 008 - CP - Price Display Options Total- Vacation Path - Flight + Car with Edit made to Car
//Created By: Gabriela
//Created On: 29-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC374011 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightCar", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt",
            "DynamicShoppingCartUI", "CarsUI", "NoBags", "NoSeats", "OptionalUI"})
    public void  CP_Price_Display_Options_Total_Vacation_Path_Flight_Car_with_Edit_made_to_Car(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374011 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3";
        final String RET_BAGS           = "Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3";
        final String FARE_TYPE          = "Standard";

        //Seats Page Constant Values
        final String DEP_SEAT           = "Standard|Standard|Standard|Standard|Standard";

        //Options Page Constant Values
        final String FLIGHT_CAR_TEXT    = "Flight + Car";

//- Step 1: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Create a vacation booking Flight + Car | DOM-DOM | 5 ADT | 25+ | Date 3 months in the future
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);

//- Step 3: Click SEARCH VACATION
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Verify the POO and POD are preselected
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        ValidationUtil.validateTestStep("Validating POO is preselected",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedDepDateText()));

        ValidationUtil.validateTestStep("Validating POD is preselected",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedRetDateText()));

//- Step 5: Verify there are Car(s) are available
        int tileCount = 0;
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i ++)
        {
            tileCount++;
        }
        ValidationUtil.validateTestStep("Validating there is cars available", tileCount!=0);

//- Step 6: Click "BOOK" in any car
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Hertz","NA");

//- Step 7: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: Have at least one adult age as 25+ and input all necessary information needed for the guest.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

//- Step 9: Select your Primary driver
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();

//- Step 10: Click "Continue"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 11: Add 1 Carry-On bag and 4 Checked bag for 3 passengers for both departing and returning, and click CONTINUE in the STANDARD BOX
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        scenarioContext.setContext(Context.BAGS_TOTAL_PRICE, pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText());
        String bagsPrice = scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString();
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

//- Step 12: Select seats for ALL passengers and click CONTINUE
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        String seatsPrice = pageObjectManager.getSeatsPage().getSeatsTotalPriceText().getText();
        System.out.println("seatsPrice: " + seatsPrice);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//- Step 13: On the Options page, locate the Dynamic Shopping Cart on the right and expand all available carets
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHeader().getFlightItineraryPanel().click();

//- Step 14: Verify Flight + Car, Bags, Seats and Options are displaying and accurate within the Dynamic Shopping Cart
        pageObjectManager.getHeader().getFlightItineraryPanel().click();
        ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Car",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_CAR_TEXT);

        ValidationUtil.validateTestStep("verify Bags label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBagsItineraryText()));

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("User expands Bags arrow in shopping cart",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowBagsItineraryImage()));
        pageObjectManager.getHeader().getArrowBagsItineraryImage().click();
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Verify Bags Total " + bagsPrice + " matches shopping cart",
                scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString(), pageObjectManager.getHeader().getBagsPriceItineraryText().getText());

        ValidationUtil.validateTestStep("Verifying Seats Total matches on shopping cart",
                seatsPrice, pageObjectManager.getHeader().getSeatsPriceItineraryText().getText());

        WaitUtil.untilTimeCompleted(3000);
        ValidationUtil.validateTestStep("Verifying Options label is present in the shopping cart",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getOptionsItineraryText()));

        pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();
        WaitUtil.untilTimeCompleted(3000);
        ValidationUtil.validateTestStep("Verifying Shortcut Boarding label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getShortcutBoardingOptionItineraryLabel()));

//- Step 15: Verify that the previous selected Car is displaying on the page
        ValidationUtil.validateTestStep("Verifying Car selected is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getSelectedCarHeaderText()));

//- Step 16: Scroll down and verify the OPTIONS TOTAL section does not display a Car
        pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verifying Car cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalCarBreakdownPriceText()));

//- Step 17: Click Edit on the Car content box
        if( !pageObjectManager.getHeader().getArrowYourItineraryImage().getAttribute("style").contains("-180deg")){
            pageObjectManager.getHeader().getArrowYourItineraryImage().click();
            WaitUtil.untilTimeCompleted(1200);
        }

        pageObjectManager.getOptionsPage().getSelectedCarEditButton().click();

//- Step 18: Select CONTINUE TO FLIGHTS
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHotelPage().getHotelCardEditPopUpContinueToFlightButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 19: Select a different Car and "BOOK"
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency("Dollar");
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageBookButton().get(2).click();

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //User directed back to Options
        //*** Passenger Information Page **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //*** Bags Page **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //*** Seats Page **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 20: Verify that Dynamic Shopping Cart still shows Flight + Car
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHeader().getFlightItineraryPanel().click();

        pageObjectManager.getHeader().getFlightItineraryPanel().click();
        ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Car",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_CAR_TEXT);

//- Step 21: Scroll down and verify the OPTIONS TOTAL section does not display a Car
        ValidationUtil.validateTestStep("User verifies Car cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalCarBreakdownPriceText()));
    }
}