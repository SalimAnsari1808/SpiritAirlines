package com.spirit.testcasesProdGFBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: PRODTC373999
//Description: Task 27824: TC373999- 008 - CP - Price Display Shopping Cart - Vacation Path - Flight + Hotel with Edit made to Hotel
//Created By: Gabriela
//Created On: 23-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class PRODTC373999 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Production"})
    public void CP_Price_Display_Shopping_Cart_Vacation_Path_Flight_Hotel_with_Edit_made_to_Hotel(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC373999 under GoldFinger Suite on " + platform + " Browser", true);
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
        final String F_H_URL            = "/book/flights-hotels";
        final String UPGRADE_VALUE      = "BookIt";

        //Dynamic Shopping Cart Constant Values
        final String FLIGHT_HOTEL_TEXT  = "Flight + Hotel";

        //Options Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 13: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Create a vacation booking dom - dom | F+H | 2 adt | 1 room 3 months out
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 2, 3 & 4: select view on any hotel, select rooms from ##$$ & select room
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal" , "NA");

//- Step 5: select continue at the bottom of the page
        //Invalid Step

//- Step 6: continue without car
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

//- Step 7: select book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: locate the dynamic shopping cart at the top right corner and select drop down arrow
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Flight + Hotel is displayed on the Dynamic Shopping Cart",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_HOTEL_TEXT);

//- Step 9: select edit
        //click on F+H arrow
        pageObjectManager.getHeader().getArrowFlightItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //click on edit link
        pageObjectManager.getHeader().getEditFlightItineraryButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 10: click CONTINUE TO FLIGHTS
        //verify Are you sure popup is displayed
        ValidationUtil.validateTestStep("verify Are you sure popup is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getAreYouSurePopUpPanel()));

        //click button on popup
        pageObjectManager.getPaymentPage().getAreYouSurePopUpContinueToFlightButton().click();

//- Step 11: select a different flight and select continue
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(1));
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify user land on F + H Page",
                getDriver().getCurrentUrl(),F_H_URL);

//- Step 12: select a different hotel and continue through confirmation page
        pageObjectManager.getHotelPage().getPriceButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

        //Continue to Confirmation page
        WaitUtil.untilPageLoadComplete(getDriver());
        //car page
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //passenger info page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
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
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment Page
        ValidationUtil.validateTestStep("Verify user lands on payments page",
                getDriver().getCurrentUrl().contains("payment"));
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        //Confirmation Page
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        //Cancel Hotel
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

    }
}