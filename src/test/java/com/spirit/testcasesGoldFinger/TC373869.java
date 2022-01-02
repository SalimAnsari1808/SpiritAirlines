package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.enums.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TC373869 extends BaseClass {
  //**********************************************************************************************
//Test Case ID:TC373869
//Description : TC373869 - 006 - CP - Price Display Options Total - Vacation Path - Flight + Hotel + Car
//Created By  : Alex Rodriguez
//Created On  : 22-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 04-Dec-2019
//************************************************************************************************
  @Parameters({"platform"})
  @Test(groups = {"BookPath","RoundTrip","FlightHotelCar","Guest","DomesticDomestic","Outside21Days","Adult", "CarryOn","CheckedBags", "Standard", "OptionsUI", "DynamicShoppingCartUI"})
  public void CP_Price_Display_Options_Total_Vacation_Path_Flight_Hotel_Car(@Optional("NA") String platform)  {
    //Mention Suite and Browser in reports
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC373869 under GoldFinger Suite on " + platform + " Browser", true);
    }

    //Reservation Credit Path Constant variables
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Vacation";
    final String TRIP_TYPE          = "Flight+Hotel+Car";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "FLL";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "MCO";
    final String DEP_DATE           = "133";
    final String ARR_DATE           = "134";
    final String ADULTS             = "2";
    final String CHILDREN           = "0";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";
    final String DRIVER_AGE         = "25+";
    final String HOTEL_ROOM         = "2 Rooms";
    //Flight Availability Page Constant Values
    final String UPGRADE_VALUE      = "BookIt";
    //Bags Page Constant
    final String BAGS               = "Carry_1|Checked_1||Carry_1|Checked_1";
    final String FARE_TYPE          = "Standard";
    //Seats Page Constant
    final String SEATS              = "Standard|Standard||Standard|Standard";

    //    Step 1	Access GoldFinger testing environment
    openBrowser(platform);
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    //    Step 2	On the search widget, click on "Vacation" tab
    //    Step 3	Create a vacation booking Flight + Hotel + Car | DOM-DOM | 2 ADT |  2 Rooms | 25+ | Date 3 months in the future, Search Vacation
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().selectHotelRoom(HOTEL_ROOM);
    pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //    Step 4	Select "SELECT ROOM" on one of the available Hotels displayed.
    //    Step 5	Click ROOMS FROM $XXX.XX and select any room by clicking SELECT ROOM button
    pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
    pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA", "NA");

    //    Step 6	Select "BOOK" on one of the available Cars displayed
    pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");

    //    Step 7	If Upgrade & Save pop-up modal displays, Select "BOOK IT"
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    //    Step 8	Have at least one adult age as 25+ and input all necessary information needed for the guest. 
    //    Step 9	Selected the Primary Driver.
    //    Step 10	Click "Continue"
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");
    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),passengerInfoData.firstName+" "+passengerInfoData.lastName);
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();


    //    Step 11	Add 1 Carry-On bag and 1 Checked bag for both passengers for both departing and returning, and click CONTINUE in the STANDARD BOX
    pageMethodManager.getBagsPageMethods().selectDepartingBags(BAGS);
    pageMethodManager.getBagsPageMethods().selectReturnBags(BAGS);
    //setting bags total
    scenarioContext.setContext(Context.BAGS_TOTAL_PRICE, pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText());
    String bagsPrice = scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString();
    ValidationUtil.validateTestStep("User validates bags price total " + bagsPrice, true);

    pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

    //    Step 12	Click Regular seats for both passengers and click CONTINUE
     pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS);
    pageMethodManager.getSeatsPageMethods().continueWithSeats();


    //    Step 13	On the Options page, locate the Dynamic Shopping Cart on the right and expand all available carets
    //    Step 14	Verify Flight + Hotel + Car, Bags, Seats and Options are displaying and accurate within the Dynamic Shopping Cart
    pageObjectManager.getHeader().getArrowYourItineraryImage().click();
    WaitUtil.untilTimeCompleted(1000);

    pageObjectManager.getHeader().getFlightItineraryPanel().click();
    ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car", "Flight + Hotel + Car",
              pageObjectManager.getHeader().getFlightItineraryText().getText());

    ValidationUtil.validateTestStep("verify Bags label is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBagsItineraryText()));

    WaitUtil.untilTimeCompleted(1000);
    ValidationUtil.validateTestStep("User expands Bags arrow in shopping cart",
            TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowBagsItineraryImage()));
    pageObjectManager.getHeader().getArrowBagsItineraryImage().click();
    WaitUtil.untilTimeCompleted(1500);
    //Verify price

    ValidationUtil.validateTestStep("Verify Bags Total " + bagsPrice + " matches shopping cart",
            scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString(), pageObjectManager.getHeader().getBagsPriceItineraryText().getText());

    WaitUtil.untilTimeCompleted(1500);
    ValidationUtil.validateTestStep("Verify Options label is present in the shopping cart",
            TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getOptionsItineraryText()));

    pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();
    WaitUtil.untilTimeCompleted(1500);
    ValidationUtil.validateTestStep("Verify Shortcut Boarding label is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getShortcutBoardingOptionItineraryLabel()));


    //    Step 15	Verify the previous Hotel and Car selected are displaying on the Options page
    ValidationUtil.validateTestStep("Verifying Hotel selected is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getHotelContainerSelectedText()));
    ValidationUtil.validateTestStep("Verifying Car selected is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getCarContainerSelectedText()));
    //    Step 16	Scroll down and verify the OPTIONS TOTAL section does not display a Car or Hotel
    pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
    ValidationUtil.validateTestStep("User verifies Hotel cost is not displayed within Option Total breakdown",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalHotelBreakdownPriceText()));
    ValidationUtil.validateTestStep("User verifies Car cost is not displayed within Option Total breakdown",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalCarBreakdownPriceText()));
  }

}
