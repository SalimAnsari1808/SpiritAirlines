package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//**********************************************************************************************
//Test Case ID: TC373865
//Description : Task 27260: TC373865 - 006 - CP - Price Display Shopping Cart - Vacation Path - Flight + Hotel with Car upsell
//Created By  : Alex Rodriguez
//Created On  : 19-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 5-Dec-2019
//**********************************************************************************************
public class TC373865 extends BaseClass {
  @Parameters({"platform"})
  @Test(groups = {"BookPath", "FlightHotel", "DomesticDomestic", "Cars", "Outside21Days", "Adult", "Guest",
          "DynamicShoppingCartUI","NoBags","NoSeats","CheckInOptions"})
  public void CP_Price_Display_Shopping_Cart_Hub_Packaging_Flight_with_Hotel_Car_upsell(@Optional("NA") String platform){
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC373865 under GoldFinger Suite on " + platform + " Browser", true);
    }

    //Reservation Credit Path Constant variables
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Vacation";
    final String TRIP_TYPE          = "Flight+Hotel";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "FLL";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "LAS";
    final String DEP_DATE           = "135";
    final String ARR_DATE           = "136";
    final String ADULTS             = "2";
    final String CHILDREN           = "0";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";

    //Flight Availability Page Constant Values
    final String UPGRADE_VALUE      = "BookIt";

    //Options Page Constant Values
    final String OPTION_VALUE       = "CheckInOption:MobileFree";

    //Payment Page Constant Value
    final String PAYMENT_PAGE       = "/book/payment";

    //  Step 1	Access GoldFinger testing environment
    openBrowser(platform);
    pageMethodManager.getHomePageMethods().launchSpiritApp();

    //  Step 2	Create a vacation booking dom - dom | F+H | 2 adt | 1 room 3 months out
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //Step 3:	Log into the HBG website and search the same destination City, Dates, Number of Guests and 1 Room.
    //Step 4: 	Apply the following filters on the HBG search result page.

    //Step 5:	Back on the GoldFinger site, click on "SELECT ROOM" on a hotel tile.
    //Step 6:   Click "(x) ROOM FROM $XXX.xx" button
    //  Step 7	Compare the HBG Room Types from the selected hotel, and choose a Room Type that DOES NOT have cancellation fees. Click "SELECT ROOM" button.
    pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA", "NA");

    //  Step 8	Select "ADD CAR" on any of the car tiles.
    pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");

    //Step 9	Select BOOK IT.
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    //Step 10 locate the dynamic shopping cart at the top right corner and select drop down arrow
    WaitUtil.untilPageLoadComplete(getDriver());
    pageObjectManager.getHeader().getArrowYourItineraryImage().click();
    WaitUtil.untilTimeCompleted(1000);
    pageObjectManager.getHeader().getFlightItineraryPanel().click();

    //Step 11: verify that you see FLIGHT + HOTEL + CAR
    ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car",
            "Flight + Hotel + Car",
            pageObjectManager.getHeader().getFlightItineraryText().getText().replace(" ", ""));

    //Step 12	enter all pax information and select continue
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

    //get adult mandatory details
    PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");
    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),
            passengerInfoData.firstName+" "+passengerInfoData.lastName);
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //Bags page
    //Step 13	continue without bags
    //Step 14: complete booking
    //Invalid Step
    //Step 15	I Don't need bags
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

    //Seats Page Methods
    //Step 16	continue without selecting seats  
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

    //Step 17	verify flight flex is NOT greyed out
    ValidationUtil.validateTestStep("Verify Flight Flex is not greyed out on Option Page",
            pageObjectManager.getOptionsPage().getFlightFlexCardAddButton().isDisplayed());

    //Step 18	select check in web
    pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

    WaitUtil.untilPageLoadComplete(getDriver());
    ValidationUtil.validateTestStep("The user redirected to the correct page",
            getDriver().getCurrentUrl(),(PAYMENT_PAGE));

    //TODO: Package booking without complete due no validation need it after payment

  }
}
