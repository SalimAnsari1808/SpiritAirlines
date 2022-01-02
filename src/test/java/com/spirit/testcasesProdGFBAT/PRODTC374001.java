package com.spirit.testcasesProdGFBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC374001
//Description: Task 27846: TC374001 010 - CP - Price Display Shopping Cart - Vacation Path - Flight + Hotel + Car with Edit made to Hotel and Car
//Created By: Gabriela
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class PRODTC374001 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Production"})
    public void CP_Price_Display_Shopping_Cart_Vacation_Path_Flight_Hotel_Car_with_Edit_made_to_Hotel_and_Car(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374001 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
//        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "60";
        final String ARR_DATE           = "70";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";
        final String F_H_URL            = "/book/flights-hotels";

        //Options Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "AmericanExpressCard";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        //- Step 14: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //- Step 1: go to vacation tab and create F+H+C | dom- dom | 3 adt | 2 rooms | 25+
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //- Step 2: select view on any hotel
        //- Step 3: select rooms from ##$$
        //- Step 4: select room
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

        //- Step 5: select continue at the bottom of the page
        //Invalid Step

        //- Step 6: select book car
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Thrifty","NA");

        //- Step 7: select continue at the bottom of the page
        //Invalid Step

        //- Step 8:	select book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //- Step 9: locate the dynamic shopping cart at the top right corner and select drop down arrow
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        //- Step 10: select edit
        pageObjectManager.getHeader().getArrowFlightItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getHeader().getEditFlightItineraryButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getPaymentPage().getAreYouSurePopUpContinueToFlightButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //- Step 11: select a different flight and select continue
        ValidationUtil.validateTestStep("Verify user land on F + H Page",
                getDriver().getCurrentUrl(),F_H_URL);

        //Selecting different flight
        pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(1));
        WaitUtil.untilPageLoadComplete(getDriver());

        //- Step 12: select a different hotel and continue at the bottom of the page
        pageObjectManager.getHotelPage().getPriceButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("The D","NA");

        //- Step 13: book a different car and continue to confirmation page
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Hertz","NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //passenger info page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        ValidationUtil.validateTestStep("Verify user lands on bags page",
                getDriver().getCurrentUrl().contains("bags"));
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page
        ValidationUtil.validateTestStep("Verify user lands on seats page",
                getDriver().getCurrentUrl().contains("seats"));
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page
        ValidationUtil.validateTestStep("Verify user lands on options page",
                getDriver().getCurrentUrl().contains("options"));
        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        //Confirmation Page
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();

//        //Cancel Hotel & Car
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
    }
}