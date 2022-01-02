package com.spirit.testcasesProdGFBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: PRODTC373868
//Title       : 003 - CP - Price Display Options Total - Hub Packaging - Flight with Hotel & Car upsell
//Description : validate shopping cart updates after selecting hotel and car on hub page
// Created By : Alex Rodriguez
//Created On  : 20-Nov-2019
//Reviewed By :
//Reviewed On :
//**********************************************************************************************
public class PRODTC373868 extends BaseClass {

  @Parameters({"platform"})
  @Test(groups = {"Production"})
  public void CP_Price_Display_Options_Total_Hub_Packaging_Flight_with_Hotel_Car_upsell(@Optional("NA") String platform){
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID PRODTC373868 under GoldFinger Suite on " + platform + " Browser", true);
    }
    //Home Page Constant Values
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Flight";
    final String TRIP_TYPE          = "RoundTrip";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "FLL";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "LAS";
    final String DEP_DATE           = "105";
    final String ARR_DATE           = "108";
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
    //Step 2	Create RT | DOM-DOM | 4 ADT | Date 3 months in the future
    //Step 3	Click Search Flights
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();
    //Flight Availability Methods
    //Step 4	Choose flights for POO and POD
    //Step 5	Click Continue at the bottom of the page.
    //Step 6	Select "Book It"
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
    //Passenger Info Methods
    //Step 7	Have at least one adult age as 25+ and input all necessary information needed for the guest. 
    //Step 8	Click "Continue"    
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //Bags page
    //Step 9	Click "CONTINUE WITHOUT ADDING BAGS" at the bottom of the page.
    //Step 10	Click "I Don't Need Bags"
    //Verify user landed on bags pag
    ValidationUtil.validateTestStep("Verify user lands on bags page",
            getDriver().getCurrentUrl().contains("bags"));
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

    //Seats Page Methods
    //Step 11	Click "CONTINUE WITHOUT SELECTING SEATS" below the travelers box on the seats page.
    //Step 13	on seats page continue without seats     
    ValidationUtil.validateTestStep("Verify user lands on seats page",
            getDriver().getCurrentUrl().contains("seats"));
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

    //Options page
    // Step 12	On the Options page, locate the Dynamic Shopping Cart on the right and expand all available carets
    ValidationUtil.validateTestStep("Verify user lands on options page",
            getDriver().getCurrentUrl().contains("options"));
    pageObjectManager.getHeader().getArrowYourItineraryImage().click();
    WaitUtil.untilTimeCompleted(1000);
    pageObjectManager.getHeader().getFlightItineraryPanel().click();
    // Step 13	Verify Flight is only displaying
    //verify flight label
    ValidationUtil.validateTestStep("verify flight label is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryText()));
    ValidationUtil.validateTestStep("verify city pair",
            DEP_AIRPORT_CODE + " - " + ARR_AIRPORT_CODE, pageObjectManager.getHeader().getAirportFlightItineraryText().get(0).getText());
    //verify flight dynamic pricing
    ValidationUtil.validateTestStep("verify flight price label is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightPriceItineraryText()));
    // Step 14	Verify both Car(s) and Hotel(s) are being offered

    if(TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCarouselTitleText())) {
      ValidationUtil.validateTestStep("Options page Car header is displayed on the options page",
              pageObjectManager.getCarPage().getCarCarouselTitleText().getText(), "Savings on Cars");}

    ValidationUtil.validateTestStep("\"Hotels for Less\" is displayed on the options page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText()));


    // Step 15	Click "ADD CAR" in any car
    pageMethodManager.getCarPageMethods().clickAddCarButtonOptionPage("NA");

    // Step 16	Select a primary driver and select ADD CAR
    TestUtil.selectDropDownUsingValue(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(),"1: 0");
    WaitUtil.untilPageLoadComplete(getDriver());
    WaitUtil.untilTimeCompleted(1200);
    pageObjectManager.getCarPage().getWhoSDrivingVerifyAndBookThisCarButton().click();

    // Step 17	Verify there is a REMOVE link within the selected car box
    WaitUtil.untilPageLoadComplete(getDriver());
    ValidationUtil.validateTestStep("User verifies the car remove button is present",
            TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRemoveButton()));


    // Step 18	Click "VIEW ROOMS" in any hotel
    pageObjectManager.getHotelPage().getViewAllHotelsButton().click();

    WaitUtil.untilPageLoadComplete(getDriver());

    WaitUtil.untilTimeCompleted(5000);

    // Step 19	Click ROOMS FROM $XXX.XX and select any room by clicking SELECT ROOM button
    pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");
    // Step 20	Verify there is a REMOVE link within the selected hotel box
    ValidationUtil.validateTestStep("User verifies the Hotel remove button is present",
            TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getSelectedHotelRemoveButton()));


    // Step 21	Verify that Dynamic Shopping Cart now shows Flight + Hotel + Car
    pageObjectManager.getHeader().getArrowYourItineraryImage().click();
    WaitUtil.untilTimeCompleted(1000);
    pageObjectManager.getHeader().getFlightItineraryPanel().click();
    ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car", "Flight + Hotel + Car",
            pageObjectManager.getHeader().getFlightItineraryText().getText());


    pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);

    // Step 22	Scroll down and verify the OPTIONS TOTAL section does not display a Hotel or Car
    JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText());
    pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
    ValidationUtil.validateTestStep("User verifies Hotel cost is not displayed within Option Total breakdown",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalHotelBreakdownPriceText()));
    ValidationUtil.validateTestStep("User verifies Car cost is not displayed within Option Total breakdown",
    !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalCarBreakdownPriceText()));
  }
}
