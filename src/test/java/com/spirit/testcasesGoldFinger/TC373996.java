package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373996
//Description: Task 27823: TC373996- 004 - CP - Price Display Shopping Cart - Hub Packaging - Remove Hotel from reservation on Hotel upsell
//Created By: Gabriela
//Created On: 23-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373996 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "BookIt", "NonStop", "NoBags", "NoSeats", "OptionalUI","DynamicShoppingCartUI"})
    public void CP_Price_Display_Shopping_Cart_Hub_Packaging_Remove_Hotel_from_reservation_on_Hotel_upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373996 under GoldFinger Suite on " + platform + " Browser", true);
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
        final String FLIGHT_TEXT        = "Flight";
        final String FLIGHT_HOTEL_TEXT  = "Flight + Hotel";
        final String CAROUSEL_TITLE     = "Hotels for Less";

//- Step 18: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: create RT DOM-DOM | 1 ADT  3 months out
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 2: select first available flights and select continue at the bottom of the page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 3: select book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 4: enter info for all pax
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 5 & 6: continue without bags & I Don't need bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 7: on seats page continue without seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 8: on options page locate the dynamic shopping cart on the right and select the drop down arrow
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

//- Step 9: verify flight details is only displaying
        ValidationUtil.validateTestStep("Validating Flight only is displayed on the Dynamic Shopping Cart",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_TEXT);

//- Step 10: verify hotel carousel is displaying on options page
        ValidationUtil.validateTestStep("Validating right Hotel's carousel title verbiage",
                pageObjectManager.getHotelPage().getHotelCarouselTitleText().getText(),CAROUSEL_TITLE);

        //Validating carousel banner
        ValidationUtil.validateTestStep("Validating the hotel carousel is displayed",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//app-multi-carousel"))));

//- Step 11: select view on any hotel
        pageObjectManager.getHotelPage().getHotelCardViewRoomButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 12: select rooms from $$$
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton());
        WaitUtil.untilTimeCompleted(1200);
//- Step 13: select room
        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 14: verify there is a remove selection in the hotel box
        ValidationUtil.validateTestStep("Verify 'Remove' link is displayed on the hotel selected card",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardRemoveLink()));

//- Step 15: verify the dynamic shopping cart now displays FLIGHT  + HOTEL
        WaitUtil.untilPageLoadComplete(getDriver());
        if (pageObjectManager.getHeader().getArrowYourItineraryImage().getAttribute("Style").equals("transform: rotate(-180deg);"))
        {
            pageObjectManager.getHeader().getArrowYourItineraryImage().click();
            WaitUtil.untilTimeCompleted(1000);
        }

        ValidationUtil.validateTestStep("Validating Flight + Hotel is displayed on the Dynamic Shopping Cart",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_HOTEL_TEXT);

//- Step 16: select remove
        pageObjectManager.getHotelPage().getHotelCardRemoveLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 17: select the drop down arrow on the dynamic shopping cart and verify it is showing flight only
        WaitUtil.untilPageLoadComplete(getDriver());
        if (pageObjectManager.getHeader().getArrowYourItineraryImage().getAttribute("Style").equals("transform: rotate(-180deg);"))
        {
            pageObjectManager.getHeader().getArrowYourItineraryImage().click();
            WaitUtil.untilTimeCompleted(1000);
        }

        ValidationUtil.validateTestStep("Validating Flight only is displayed on the Dynamic Shopping Cart",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_TEXT);
    }
}

//*******************************************************************
//********Hotel Card Container Options Page**************************
//*******************************************************************
//Hotel View Room button
//public final String xpath_HotelCardViewRoomButton = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]//button[contains(text(),'View Rooms') or contains(text(),'Ver Habitacion')]";
//    @FindBy(xpath = xpath_HotelCardViewRoomButton)
//    private List<WebElement> btn_HotelCardViewRoom;
//    public List<WebElement> getHotelCardViewRoomButton() {return btn_HotelCardViewRoom;}