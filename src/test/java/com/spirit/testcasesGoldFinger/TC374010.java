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
//Test Case ID: TC374010
//Description: Task 27826: TC374010- 007 - CP - Price Display Options Total - Vacation Path - Flight + Hotel with Edit made to Hotel
//Created By: Gabriela
//Created On: 23-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC374010 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic","FlightHotel", "Outside21Days", "Adult", "Guest", "BookIt", "CarryOn","CheckedBags", "Standard", "OptionalUI","DynamicShoppingCartUI"})
    public void CP_Price_Display_Options_Total_Vacation_Path_Flight_Hotel_with_Edit_made_to_Hotel(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374010 under GoldFinger Suite on " + platform + " Browser", true);
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
        final String ADULT              = "3";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Value
        final String DEP_BAGS           = "Carry_1|Checked_3";
        final String RET_BAGS           = "Carry_1|Checked_3";
        final String FARE_TYPE          = "Standard";

        //Seats Page Constant Values
        final String DEP_SEAT           = "Standard|Standard|Standard";
        final String RET_SEAT           = "Standard|Standard|Standard";

        //Common Constant Values
        final String FLIGHT_HOTEL_TEXT  = "Flight + Hotel";

//- Step 1: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Create a vacation booking Flight + Hotel | DOM-DOM | 3 ADT |  2 Rooms | Date 3 months in the future
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);


//- Step 3: Click SEARCH VACATION
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4 & 5: Select "SELECT ROOM" on one of the available Hotels displayed. &  Click ROOMS FROM $XXX.XX and select any room by clicking SELECT ROOM button
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

//- Step 6: Click Continue without Car at the bottom of the page.
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

//- Step 7: Select book it
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: Input all necessary information needed for the guests.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

//- Step 9: Click "Continue" at the bottom of the page.
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 10: Add 1 Carry-On bag and 3 Checked bag for one passengers for both departing and returning, and click CONTINUE in the STANDARD BOX
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        scenarioContext.setContext(Context.BAGS_TOTAL_PRICE, pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText());
        String bagsPrice = scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString();
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

//- Step 11: Click Regular seats for all the passengers and click CONTINUE
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//- Step 12: On the Options page, locate the Dynamic Shopping Cart on the right and expand all available carets
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

//- Step 13: Verify Flight + Hotel, Bags, Seats and Options are displaying and accurate
        //Seat validation need it,  pageMethodManager.getSeatsPageMethods().selectDepartureSeats() is not working properly on vacation path
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

//- Step 15: Scroll down and verify the OPTIONS TOTAL section does not display a Hotel
        pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verifying Hotel cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalHotelBreakdownPriceText()));

//- Step 16: Click Edit on the Hotel content box
        pageObjectManager.getHotelPage().getHotelCardEditLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 17: Select CONTINUE TO FLIGHTS
        pageObjectManager.getHotelPage().getHotelCardEditPopUpContinueToFlightButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 18: Select "SELECT ROOM" on a different Hotel
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 19: Click ROOMS FROM $XXX.XX and select a room by clicking SELECT ROOM button
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().get(0).click();

        //Cars Page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        WaitUtil.untilPageLoadComplete(getDriver());

        //Passenger Information Page
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 20: Verify that Dynamic Shopping Cart still shows Flight + Hotel
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getHeader().getFlightItineraryPanel().click();
        ValidationUtil.validateTestStep("User verifies dynamic shopping cart still as Flight + Hotel",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_HOTEL_TEXT);

//- Step 21: Scroll down and verify the OPTIONS TOTAL section does not display a Hotel
        pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verifying Hotel cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalHotelBreakdownPriceText()));
    }
}

/************************** XPATH on Hotel Page **********/
//*******************************************************************
//********Hotel Card Container Options Page**************************
//*******************************************************************
//Edit button
//public final String xpath_HotelCardEditLinkButton = "//app-selected-ancillary-item//button[contains(text(),'Edit') or contains(text(),'Editar')]";
//    @FindBy(xpath=xpath_HotelCardEditLinkButton)
//    private WebElement lnkbtn_HotelCardEdit;

//Edit Hotel Pop Up Title
//public final String xpath_HotelCardEditPopUpTitleText = "//app-branded-modal//h2";
//    @FindBy(xpath=xpath_HotelCardEditPopUpTitleText)
//    private WebElement txt_HotelCardEditPopUpTitle;

//Edit Hotel Pop Up Close Button
//public final String xpath_HotelCardEditPopUpCloseButton = "//app-branded-modal//button[@class='close']";
//    @FindBy(xpath=xpath_HotelCardEditPopUpCloseButton)
//    private WebElement btn_HotelCardEditPopUpClose;

//Edit Hotel Pop Up Continue to Flight Button
//public final String xpath_HotelCardEditPopUpContinueToFlightButton = "//app-branded-modal//button[contains(text(),'Continue to Flights') or contains(text(),'Continuar a los vuelos')]";
//    @FindBy(xpath=xpath_HotelCardEditPopUpContinueToFlightButton)
//    private WebElement btn_HotelCardEditPopUpContinueToFlight;

//public WebElement getHotelCardEditLink() {return lnkbtn_HotelCardEdit;}

//public WebElement getHotelCardEditPopUpTitleText() {return txt_HotelCardEditPopUpTitle;}

//public WebElement getHotelCardEditPopUpCloseButton() {return btn_HotelCardEditPopUpClose;}

//public WebElement getHotelCardEditPopUpContinueToFlightButton() {return btn_HotelCardEditPopUpContinueToFlight;}