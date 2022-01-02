package com.spirit.testcasesProdGFBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//*********************************************************************************************
//Test Case ID: TC374019
//Description: Task 27849: TC374019- 003 - CP - Price Display Total Due - Hub Packaging - Flight with Hotel & Car upsell
//Created By: Gabriela
//Created On: 25-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class PRODTC374019 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Production"})
    public void CP_Price_Display_Total_Due_Hub_Packaging_Flight_with_Hotel_Car_upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374019 under GoldFinger Suite on " + platform + " Browser", true);
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
        final String ADULT              = "4";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String H_CAROUSEL_TITLE   = "Hotels for Less";
        final String C_CAROUSEL_TITLE   = "Savings on Cars";
        final String FLIGHT             = "Flight";
        final String F_H_CL_TEXT        = "Flight + Hotel + Car";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "DiscoverCard3";

        //- Step 1: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        /*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //- Step 2: Create RT DOM-DOM | 4 ADT | 3 months out
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //- Step 3: Select first available flights and select continue at the bottom of the page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //- Step 4: Select Book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //- Step 5: Enter info for all pax
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //- Step 6 & 7: Continue without bags & I don't need bags
        ValidationUtil.validateTestStep("Verify user lands on bags page",
                getDriver().getCurrentUrl().contains("bags"));
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //- Step 8: Continue without selecting seats
        ValidationUtil.validateTestStep("Verify user lands on seats page",
                getDriver().getCurrentUrl().contains("seats"));
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //- Step 9: On Options page locate the dynamic shopping cart on the right and select the drop down arrow
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user lands on options page",
                getDriver().getCurrentUrl().contains("options"));
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHeader().getFlightItineraryPanel().click();

        //- Step 10: Verify flight details is only displaying
        ValidationUtil.validateTestStep("verify flight label is displayed",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT);
        ValidationUtil.validateTestStep("verify city pair",
                DEP_AIRPORT_CODE + " - " + ARR_AIRPORT_CODE, pageObjectManager.getHeader().getAirportFlightItineraryText().get(0).getText());

        //- Step 11: Verify Car and Hotel carousel is displaying on Options page
            ValidationUtil.validateTestStep("Options page Car header is displayed on the options page",
                    pageObjectManager.getCarPage().getCarCarouselTitleText().getText(), C_CAROUSEL_TITLE);

        ValidationUtil.validateTestStep("\"Hotels for Less\" is displayed on the options page",
                pageObjectManager.getHotelPage().getHotelCarouselTitleText().getText(),H_CAROUSEL_TITLE);

        //- Step 12: Select ADD CAR on any car
        pageMethodManager.getCarPageMethods().clickAddCarButtonOptionPage("NA");

        //- Step 13: Select a primary driver and select ADD CAR
        TestUtil.selectDropDownUsingValue(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(),"1: 0");
        WaitUtil.untilPageLoadComplete(getDriver());
        //TODO: New GoldFinger
//        pageObjectManager.getCarPage().getCarPopUpAddCar().click();
        pageObjectManager.getCarPage().getWhoSDrivingVerifyAndBookThisCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //- Step 14: Verify there is a remove selection link in the selected car box
        ValidationUtil.validateTestStep("User verifies the car remove button is present",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRemoveButton()));

        //- Step 15: Before selecting a hotel, you must check HBG to make sure the hotel will be refundable with no cancellation fees.
        //- Step 16:  Select ROOMS FROM $XXX.XX
        //- Step 17: Select room
        pageMethodManager.getHotelPageMethods().selectHotelOnOptionPage("Universal","NA");

        //- Step 18: Verify there is a remove selection in the hotel box
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("User verifies the Hotel remove button is present",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelRemoveButton()));

        //- Step 19: Verify the Options section does not display Flight + Hotel + Car separately in drop down
        pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verifying Car cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalCarBreakdownPriceText()));

        ValidationUtil.validateTestStep("Verifying Hotel cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalHotelBreakdownPriceText()));

        //- Step 20: Verify Flight Flex is not grayed out
        ValidationUtil.validateTestStep("Verifying Flight Flex option is available",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

        //- Step 21: Select I'll check in at Spirit.com/Mobile for free and continue
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //- Step 22: Complete booking
        ValidationUtil.validateTestStep("Verify user lands on payments page",
                getDriver().getCurrentUrl().contains("payment"));
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//
////- Step 23: Verify total paid is displaying as packaging item and that options doesn't display anything
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        //Hotel validation on Confirmation page method need it
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageObjectManager.getConfirmationPage().getTotalPaidBreakDownLink().click();
//        WaitUtil.untilTimeCompleted(1000);
//
//        ValidationUtil.validateTestStep("Validating total paid is displayed as a package",
//                pageObjectManager.getConfirmationPage().getFlightVerbiageLabel().getText(),F_H_CL_TEXT);
//
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
    }
}