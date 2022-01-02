package com.spirit.testcasesProdGFBAT;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC373864
//Description: TC373864 - 005 - CP - Price Display Total Due - Vacation Path - Flight + Hotel + Car with SB
//Created By: Alex Rodriguez
//Created On: 19-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class PRODTC373865 extends BaseClass {
  @Parameters({"platform"})
  @Test(groups = {"Production"})
  public void CP_Price_Display_Shopping_Cart_Hub_Packaging_Flight_with_Hotel_Car_upsell(@Optional("NA") String platform){
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID PRODTC373865 under GoldFinger Suite on " + platform + " Browser", true);
    }

    //Reservation Credit Path Constant variables
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Vacation";
    final String TRIP_TYPE          = "Flight+Hotel+Car";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "FLL";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "LAS";
    final String DEP_DATE           = "80";
    final String ARR_DATE           = "85";
    final String ADULTS             = "2";
    final String CHILDREN           = "0";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";
    final String DRIVER_AGE         = "25+";
    final String HOTEL_ROOM         = "1 Room";
    final String OPTION_VALUE       = "CheckInOption:MobileFree";
    //Payment Page Constant Value
    final String CARD_TYPE          = "MasterCard";
    final String TRAVEL_GUARD       = "NotRequired";

    final String UPGRADE_VALUE      = "BookIt";
    //  Step 1	Access GoldFinger testing environment
    openBrowser(platform);
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    //  Step 2	Create a vacation booking DOM-DOM | F+H+C | 2 ADT | 1 rooms | 25+ | 3 months out
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().selectHotelRoom(HOTEL_ROOM);
    pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //  Step 3	"Before selecting a hotel, you must check HBG to make sure the hotel will be refundable with no cancellation fees.
    //  Scroll through the hotels, and click on SELECT ROOM on a hotel."
    //  Step 4	Back on the GoldFinger site, click on "SELECT ROOM" on a hotel tile.
    //  Step 5	Click "(x) ROOM FROM $XXX.xx" button
    pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

    pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA", "NA");


    //  Step 6	Select "ADD CAR" on any of the car tiles.
    //  Step 7	Select BOOK IT.
    pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");

    WaitUtil.untilPageLoadComplete(getDriver());
    //Step 8	locate the dynamic shopping cart at the top right corner and select drop down arrow
    //Step 9 verify that you see FLIGHT + HOTEL + CAR
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    pageObjectManager.getHeader().getArrowYourItineraryImage().click();
    WaitUtil.untilTimeCompleted(1000);
    pageObjectManager.getHeader().getFlightItineraryPanel().click();
    ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car",
            TRIP_TYPE,
            pageObjectManager.getHeader().getFlightItineraryText().getText().replace(" ", ""));

    //Passenger Info Methods
    //Step 10	enter all pax information and select continue
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    //get adult mandatory details
    PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");
    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),
            passengerInfoData.firstName+" "+passengerInfoData.lastName);
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //Bags page
    //Step 11	continue without bags
    //Step 12	I Don't need bags
    //Verify user landed on bags pag
    ValidationUtil.validateTestStep("Verify user lands on bags page",
            getDriver().getCurrentUrl().contains("bags"));

    //verify shopping cart
    pageObjectManager.getHeader().getArrowYourItineraryImage().click();

    WaitUtil.untilTimeCompleted(1000);

    pageObjectManager.getHeader().getFlightItineraryPanel().click();

    ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car",
            TRIP_TYPE, pageObjectManager.getHeader().getFlightItineraryText().getText().replace(" ", ""));

    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

    //Seats Page Methods
    //Step 13	on seats page continue without seats     
    ValidationUtil.validateTestStep("Verify user lands on seats page",
            getDriver().getCurrentUrl().contains("seats"));

    //verify shopping cart
    pageObjectManager.getHeader().getArrowYourItineraryImage().click();

    WaitUtil.untilTimeCompleted(1000);

    pageObjectManager.getHeader().getFlightItineraryPanel().click();

    ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car",
            TRIP_TYPE, pageObjectManager.getHeader().getFlightItineraryText().getText().replace(" ", ""));

    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

    //Step 14	verify flight flex is NOT greyed out
    ValidationUtil.validateTestStep("Verify user lands on options page",
            getDriver().getCurrentUrl().contains("options"));

    ValidationUtil.validateTestStep("Validating Flight Flex options is available",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardNotAvailableText()));

    //Step 15	select check in web
    //verify shopping cart
    pageObjectManager.getHeader().getArrowYourItineraryImage().click();

    WaitUtil.untilTimeCompleted(1000);

    pageObjectManager.getHeader().getFlightItineraryPanel().click();

    ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car",
            TRIP_TYPE, pageObjectManager.getHeader().getFlightItineraryText().getText().replace(" ", ""));

    pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

    //Step 18	complete booking
    ValidationUtil.validateTestStep("Verify user lands on payment page",
            getDriver().getCurrentUrl().contains("payment"));

    pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
    pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
    /**TODO: Hotel Cancellation Method required
     pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
     pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
     **/




  }
}
