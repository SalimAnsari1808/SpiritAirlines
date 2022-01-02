package com.spirit.testcasesProdGFBAT;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC374012
//Description: Task 27848: TC374012- 009 - CP - Price Display Options Total - Vacation Path - Flight + Hotel + Car with Edit made to Hotel and Car
//Created By: Gabriela
//Created On: 25-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class PRODTC374012 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Production"})
    public void Price_Display_Options_Total_Vacation_Path_Flight_Hotel_Car_with_Edit_made_to_Hotel_and_Car(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374012 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "135";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String F_H_URL            = "/book/hotels";
        final String F_C_URL            = "/book/options/cars";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Value
        final String DEP_BAGS           = "Carry_1|Checked_1";
        final String RET_BAGS           = "Carry_1|Checked_1";
        final String FARE_TYPE          = "Standard";

        //Seats Page Constant Values
        final String DEP_SEAT           = "Standard|Standard";
        final String RET_SEAT           = "Standard|Standard";

        //Common Constant Values
        final String F_H_C_TEXT         = "Flight + Hotel + Car";

        //- Step 1: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //- Step 2: On the search widget, click on "Vacation" tab
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        //- Step 3: Create a vacation booking Flight + Hotel + Car | DOM-DOM | 2 ADT |  2 Rooms | 25+ | Date 3 months in the future, Search Vacation
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        ValidationUtil.validateTestStep("Verify user land on F + H Page",
                getDriver().getCurrentUrl(),F_H_URL);

        //- Step 4: Select "SELECT ROOM" on one of the available Hotels displayed.
        //- Step 5: Click ROOMS FROM $XXX.XX and select any room by clicking SELECT ROOM button
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

        ValidationUtil.validateTestStep("Verify user land on F + H Page",
                getDriver().getCurrentUrl(),F_C_URL);

        //- Step 6: Select "BOOK" on one of the available Cars displayed
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Hertz","NA");

        //- Step 7: If Upgrade & Save pop-up modal displays, Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //- Step 8: Have at least one adult age as 25+ and input all necessary information needed for the guest.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //- Step 9: Selected the Primary Driver.
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        //- Step 10: Click "Continue"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //- Step 11: Add 1 Carry-On bag and 1 Checked bag for both passengers for both departing and returning, and click CONTINUE in the STANDARD BOX
        ValidationUtil.validateTestStep("Verify user lands on bags page",
                getDriver().getCurrentUrl().contains("bags"));
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        scenarioContext.setContext(Context.BAGS_TOTAL_PRICE, pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText());
        String bagsPrice = scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString();
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //- Step 12: Click Regular seats for both passengers and click CONTINUE
//        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
//        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEAT);
//        pageMethodManager.getSeatsPageMethods().continueWithSeats();
        ValidationUtil.validateTestStep("Verify user lands on seats page",
                getDriver().getCurrentUrl().contains("seats"));
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //- Step 13: On the Options page, locate the Dynamic Shopping Cart on the right and expand all available carets
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user lands on options page",
                getDriver().getCurrentUrl().contains("options"));
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        //- Step 14: Verify Flight + Hotel + Car, Bags, Seats and Options are displaying and accurate within the Dynamic Shopping Cart
        pageObjectManager.getHeader().getFlightItineraryPanel().click();
        ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),F_H_C_TEXT);

        ValidationUtil.validateTestStep("verify Bags label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBagsItineraryText()));

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("User expands Bags arrow in shopping cart",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowBagsItineraryImage()));
        pageObjectManager.getHeader().getArrowBagsItineraryImage().click();
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Verify Bags Total " + bagsPrice + " matches shopping cart",
                scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString(), pageObjectManager.getHeader().getBagsPriceItineraryText().getText());


//        ValidationUtil.validateTestStep("Verifying Seats Total matches on shopping cart",
//                scenarioContext.getContext(Context.SEATS_TOTAL_PRICE).toString(), pageObjectManager.getHeader().getSeatsPriceItineraryText().getText().replace("$",""));

        WaitUtil.untilTimeCompleted(3000);
        ValidationUtil.validateTestStep("Verifying Options label is present in the shopping cart",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getOptionsItineraryText()));

        pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();
        WaitUtil.untilTimeCompleted(3000);
        ValidationUtil.validateTestStep("Verifying Shortcut Boarding label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getShortcutBoardingOptionItineraryLabel()));

        //- Step 15: Verify the previous Hotel and Car selected are displaying on the Options page
        ValidationUtil.validateTestStep("Verifying Hotel selected is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getHotelContainerSelectedText()));

        ValidationUtil.validateTestStep("Verifying Car selected is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getCarContainerSelectedText()));

        //- Step 16: Scroll down and verify the OPTIONS TOTAL section does not display a Car or Hotel
        pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verifying Hotel cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalHotelBreakdownPriceText()));

        ValidationUtil.validateTestStep("Verifying Car cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalCarBreakdownPriceText()));

        //- Step 17: Click Edit on the Hotel content box
        pageObjectManager.getHotelPage().getHotelCardEditLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //- Step 18: Select CONTINUE TO FLIGHTS
        pageObjectManager.getHotelPage().getHotelCardEditPopUpContinueToFlightButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //- Step 19 & 20: Select "SELECT ROOM" on a different Hotel & Click ROOMS FROM $XXX.XX and select any room by clicking SELECT ROOM button
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

        //- Step 21: Select a different Car and "BOOK"
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency("Thrifty");
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getCarPage().getCarsPageBookButton().get(2).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        WaitUtil.untilPageLoadComplete(getDriver());

        //- Step 22: On the Options page, locate the Dynamic Shopping Cart on the right and expand all available carets
        // Passenger Information Page
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        // Bags Page
        ValidationUtil.validateTestStep("Verify user lands on bags page",
                getDriver().getCurrentUrl().contains("bags"));
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        // Seats Page
        ValidationUtil.validateTestStep("Verify user lands on seats page",
                getDriver().getCurrentUrl().contains("seats"));
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //- Step 23: Verify Flight + Hotel + Car, Bags, Seats and Options are displaying and accurate within the Dynamic Shopping Cart
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user lands on options page",
                getDriver().getCurrentUrl().contains("options"));
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getHeader().getFlightItineraryPanel().click();
        ValidationUtil.validateTestStep("User verifies dynamic shopping cart still as Flight + Hotel + Car",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),F_H_C_TEXT);

        //- Step 24: Verify Hotel and Car selected are displaying on the Options page
        ValidationUtil.validateTestStep("Verifying Hotel selected is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getHotelContainerSelectedText()));

        ValidationUtil.validateTestStep("Verifying Car selected is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getCarContainerSelectedText()));

        //- Step 25: Scroll down and verify the OPTIONS TOTAL  section does not display a Car or Hotel
        pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Verifying Hotel cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalHotelBreakdownPriceText()));

        ValidationUtil.validateTestStep("Verifying Car cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalCarBreakdownPriceText()));
    }
}