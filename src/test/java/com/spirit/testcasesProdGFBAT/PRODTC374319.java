package com.spirit.testcasesProdGFBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//*********************************************************************************************
//Test Case ID: TC374319
//Description: Task 27825: TC374319- 002 - CP - Price Display Options Total - Hub Packaging - Flight with Hotel upsell
//Created By: Gabriela
//Created On: 25-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class PRODTC374319 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Production"})
    public void CP_Price_Display_Options_Total_Hub_Packaging_Flight_with_Hotel_upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374319 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String CAROUSEL_TITLE     = "Hotels for Less";
        final String FLIGHT             = "Flight";
        final String FLIGHT_HOTEL_TEXT  = "Flight + Hotel";

        //- Step 1: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        /*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //- Step 2: Create RT | DOM-DOM | 1 ADT | Date 3 months in the future
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

        //- Step 3: Click Search Flights
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //- Step 4: Choose flights for POO and POD
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);

        //- Step 5: Click Continue at the bottom of the page.
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //- Step 6: Select "Book It"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //- Step 7: Have the adult age as 25+ and input all necessary information needed for the guest.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //- Step 8: Click "Continue"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //- Step 9 & 10: Click "CONTINUE WITHOUT ADDING BAGS" at the bottom of the page. & Click "I Don't Need Bags"
        ValidationUtil.validateTestStep("Verify user lands on bags page",
                getDriver().getCurrentUrl().contains("bags"));
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //- Step 11: Click "CONTINUE WITHOUT SELECTING SEATS" below the travelers box on the seats page.
        ValidationUtil.validateTestStep("Verify user lands on seats page",
                getDriver().getCurrentUrl().contains("seats"));
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //- Step 12: On the Options page, locate the Dynamic Shopping Cart on the right and expand all available carets
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user lands on options page",
                getDriver().getCurrentUrl().contains("options"));
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHeader().getFlightItineraryPanel().click();

        //- Step 13: Verify Flight is only displaying
        ValidationUtil.validateTestStep("verify flight label is displayed",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT);
        ValidationUtil.validateTestStep("verify city pair",
                DEP_AIRPORT_CODE + " - " + ARR_AIRPORT_CODE, pageObjectManager.getHeader().getAirportFlightItineraryText().get(0).getText());

        //- Step 14: Verify Hotel(s) are being offered and a carousel is displaying
        ValidationUtil.validateTestStep("\"Hotels for Less\" is displayed on the options page",
               pageObjectManager.getHotelPage().getHotelCarouselTitleText().getText(),CAROUSEL_TITLE);

        //- Step 15: Click "VIEW ROOMS" in any hotel
        pageObjectManager.getHotelPage().getHotelCardViewRoomButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //- Step 16: Click ROOMS FROM $XXX.XX and select any room by clicking SELECT ROOM button
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton());
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //- Step 17: Verify there is a REMOVE link within the selected hotel box
        ValidationUtil.validateTestStep("User verifies the Hotel remove button is present",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelRemoveButton()));

        //- Step 18: Verify that Dynamic Shopping Cart now shows Flight + Hotel
        WaitUtil.untilPageLoadComplete(getDriver());
        if (pageObjectManager.getHeader().getArrowYourItineraryImage().getAttribute("Style").equals("transform: rotate(-180deg);"))
        {
            pageObjectManager.getHeader().getArrowYourItineraryImage().click();
            WaitUtil.untilTimeCompleted(1000);
        }

        ValidationUtil.validateTestStep("Validating Flight + Hotel is displayed on the Dynamic Shopping Cart",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_HOTEL_TEXT);

//- Step 19: Scroll down and verify the OPTIONS TOTAL section does not display a Hotel
        pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verifying Hotel cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalHotelBreakdownPriceText()));
    }
}