package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC374008
//Description: Task 27847: TC374008- 004 - CP - Price Display Options Total - Vacation Path - Flight + Hotel with Car upsell
//Created By: Gabriela
//Created On: 26-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 05-Dec-2019
//**********************************************************************************************

public class TC374008 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic","FlightHotel", "Outside21Days", "Adult", "Guest", "BookIt", "CarryOn","CheckedBags", "Standard", "OptionalUI","DynamicShoppingCartUI"})
    public void CP_Price_Display_Options_Total_Vacation_Path_Flight_Hotel_with_Car_upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374008 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Value
        final String DEP_BAGS           = "Carry_1|Checked_1";
        final String RET_BAGS           = "Carry_1|Checked_1";
        final String FARE_TYPE          = "Standard";

        //Seats Page Constant Values
        final String DEP_SEAT           = "Standard|Standard||Standard|Standard";
        final String RET_SEAT           = "Standard|Standard||Standard|Standard";

        //Common Constant Values
        final String FLIGHT_HOTEL_TEXT  = "Flight + Hotel";


//- Step 1: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Create a vacation booking Flight + Hotel | DOM-DOM | 2 ADT |  1 Room | Date 3 months in the future
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

//- Step 3: Click SEARCH VACATION
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4 & 5: Select "SELECT ROOM" on one of the available Hotels displayed. & Click ROOMS FROM $XXX.XX and select any room by clicking SELECT ROOM button
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

//- Step 6: Click Continue without Car at the bottom of the page.
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

//- Step 7: If the Upgrade & Save is displayed, Select Book it
        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: Have at least one adult age as 25+ and input all necessary information needed for the guest.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

//- Step 9: Click "Continue" at the bottom of the page.
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 10: Add 1 Carry-On bag and 1 Checked bag for both passengers for both departing and returning, and click CONTINUE in the STANDARD BOX
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        scenarioContext.setContext(Context.BAGS_TOTAL_PRICE, pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText());
        String bagsPrice = scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString();
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

//- Step 11: Click Regular seats for both passengers and click CONTINUE
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//- Step 12: On the Options page, locate the Dynamic Shopping Cart on the right and expand all available carets
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

//- Step 13: Verify Flight + Hotel, Bags, Seats and Options are displaying and accurate
        pageObjectManager.getHeader().getFlightItineraryPanel().click();
        ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_HOTEL_TEXT);

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
                scenarioContext.getContext(Context.SEATS_TOTAL_PRICE).toString(), pageObjectManager.getHeader().getSeatsPriceItineraryText().getText().replace("$",""));

        WaitUtil.untilTimeCompleted(3000);
        ValidationUtil.validateTestStep("Verifying Options label is present in the shopping cart",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getOptionsItineraryText()));

        pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();
        WaitUtil.untilTimeCompleted(3000);
        ValidationUtil.validateTestStep("Verifying Shortcut Boarding label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getShortcutBoardingOptionItineraryLabel()));

//- Step 14: Verify that the previously selected Hotel is displaying on the Options page
        ValidationUtil.validateTestStep("Verifying Hotel selected is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getHotelContainerSelectedText()));

//- Step 15: Verify Car(s) are being offered and a Car carousel is displaying
        ValidationUtil.validateTestStep("Validating Car is offered on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCarouselTitleText()));


//- Step 16 & 17: Click "ADD CAR" in any car & Select a primary driver and select ADD CAR
        pageMethodManager.getCarPageMethods().clickAddCarButtonOptionPage("NA");

        // Step 16	Select a primary driver and select ADD CAR
        TestUtil.selectDropDownUsingValue(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(),"1: 0");

        //TODO: New GoldFinger
//        pageObjectManager.getCarPage().getBookCarButton().get(0).click();
//        WaitUtil.untilTimeCompleted(1200);
//        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getCarPage().getWhoSDrivingVerifyAndBookThisCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 18: Verify there is a REMOVE link within the selected car box
        ValidationUtil.validateTestStep("Validating REMOVE options is displayed on the Car tile after selected",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRemoveButton()));

//- Step 19: Verify that Dynamic Shopping Cart now shows Flight + Hotel + Car
        if(!pageObjectManager.getHeader().getArrowYourItineraryImage().findElement(By.xpath("//i")).getAttribute("style").contains("(0deg)")){
            System.out.println("inside the if statement");
            JSExecuteUtil.clickOnElement(getDriver() , pageObjectManager.getHeader().getArrowYourItineraryImage().findElement(By.xpath("//i")));
            WaitUtil.untilTimeCompleted(1000);
        }

        pageObjectManager.getHeader().getFlightItineraryPanel().click();
        ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car", "Flight + Hotel + Car",
                pageObjectManager.getHeader().getFlightItineraryText().getText());

//- Step 20: Scroll down and verify the OPTIONS TOTAL  section does not display a Car or Hotel
        pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verifying Hotel cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalHotelBreakdownPriceText()));
    }
}