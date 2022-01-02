package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//**********************************************************************************************
//Test Case ID: TC373864
//Description : Task 27259: TC373864 - 003 - CP - Price Display Shopping Cart - Hub Packaging - Flight with Hotel & Car upsell
//Created By  : Alex Rodriguez
//Created On  : 19-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 04-Dec-2019
//**********************************************************************************************
public class TC373864 extends BaseClass {
  @Parameters({"platform"})
  @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "BookIt",
         "Connecting", "FlightAvailabilityUI", "CarsUI", "HotelsUI", "NoBags","NoSeats","CheckInOptions","OptionalUI"})
  public void CP_Price_Display_Shopping_Cart_Hub_Packaging_Flight_with_Hotel_Car_upsell(@Optional("NA") String platform){
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC373864 under GoldFinger Suite on " + platform + " Browser", true);
    }
    //Home Page Constant Values
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Flight";
    final String TRIP_TYPE          = "RoundTrip";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "FLL";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "LAS";
    final String DEP_DATE           = "135";
    final String ARR_DATE           = "137";
    final String ADULT              = "4";
    final String CHILD              = "0";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT         = "Connecting";
    final String ARR_Flight         = "Connecting";
    final String FARE_TYPE          = "Standard";
    final String UPGRADE_VALUE      = "BookIt";

    //Options Constant Values
    final String OPTIONS_VALUE		  = "CheckInOption:MobileFree";


    //Step 1	Access GoldFinger testing environment
    openBrowser(platform);
    pageMethodManager.getHomePageMethods().launchSpiritApp();

    //Step 2	create RT DOM-DOM | 4 ADT  3 months out
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //Flight Availability Methods
    //Step 3	select first available flights and select continue at the bottom of the page
    //Step 4	select book it
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    //Passenger Info Methods
    //Step 5	enter info for all pax
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //Bags page
    //Step 6	continue without bags
    //Step 7	I Don't need bags
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

    //Seats Page Methods
    //Step 8	on seats page continue without seats     
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

    //Options page
    //Step 9	on options page locate the dynamic shopping cart on the right and select the drop down arrow
    //Step 10	verify flight details is only displaying
    pageObjectManager.getHeader().getArrowYourItineraryImage().click();
    WaitUtil.untilTimeCompleted(1000);

    pageObjectManager.getHeader().getFlightItineraryPanel().click();
    //verify flight label
    ValidationUtil.validateTestStep("verify flight label is displayed",
            pageObjectManager.getHeader().getFlightItineraryText().getText().equals("Flight"));

    ValidationUtil.validateTestStep("verify city pair",
            DEP_AIRPORT_CODE + " - " + ARR_AIRPORT_CODE, pageObjectManager.getHeader().getAirportFlightItineraryText().get(0).getText());

    //verify flight dynamic pricing
    ValidationUtil.validateTestStep("verify flight price label is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightPriceItineraryText()));

    //Step 11	verify car and hotel carousel is displaying on options page
    if(TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCarouselTitleText())) {
      ValidationUtil.validateTestStep("Options page Car header is displayed on the options page",
              pageObjectManager.getCarPage().getCarCarouselTitleText().getText(), "Savings on Cars");}

    ValidationUtil.validateTestStep("\"Hotels for Less\" is displayed on the options page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText()));

    //Step 12	Click "ADD CAR" button on any of the cars.
    //Step 13	Select a Primary Driver and click the "ADD CAR" button
    pageMethodManager.getCarPageMethods().clickAddCarButtonOptionPage("NA");
    TestUtil.selectDropDownUsingValue(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(),"1: 0");
    //TODO: New GoldFinger
//    pageObjectManager.getCarPage().getBookCarButton().get(0).click();
    pageObjectManager.getCarPage().getWhoSDrivingVerifyAndBookThisCarButton().click();
    //Step 14	Verify there is a "REMOVE" hyperlink in the car tile.
    WaitUtil.untilPageLoadComplete(getDriver());
    ValidationUtil.validateTestStep("User verifies the car remove button is present",
            TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRemoveButton()));


    //Step 17	Select "VIEW ROOMS" button on any of the hotel tiles.
    //Step 18	Click "(x) ROOM FROM $XXX.xx" button
    //Click ""SELECT ROOM"" button."
    pageObjectManager.getHotelPage().getViewAllHotelsButton().click();
    pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM","NA");

    //Step 20	Verify there is a REMOVE hyperlink hotel tile.
    ValidationUtil.validateTestStep("User verifies the Hotel remove button is present",
            TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getSelectedHotelRemoveButton()));

    //Step 21	verify the dynamic shopping cart now displays FLIGHT + HOTEL + CAR
    pageObjectManager.getHeader().getArrowYourItineraryImage().click();
    WaitUtil.untilTimeCompleted(1000);
    pageObjectManager.getHeader().getFlightItineraryPanel().click();
    ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car", "Flight + Hotel + Car",
            pageObjectManager.getHeader().getFlightItineraryText().getText());

    //Step 22	verify flight flex is NOT greyed out
    ValidationUtil.validateTestStep("Verify Flight Flex is not greyed out on Option Page",
            pageObjectManager.getOptionsPage().getFlightFlexCardAddButton().isDisplayed());

    //Step 23	select check in web
    pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
    //Step 24	complete booking
//    Booking over $3k cannot complete payment
//    pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//    pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//
  }
}
