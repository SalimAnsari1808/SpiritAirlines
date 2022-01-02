package com.spirit.testcasesGoldFinger;


import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC373873
//Description : TC373873 - CP - Price Display Total Due - Vacation Path - Flight + Hotel + Car with SB
//Created By  : Alex Rodriguez
//Created On  : 19-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 04-Dec-2019
//**********************************************************************************************

public class TC373873 extends BaseClass {
  @Parameters({"platform"})
  @Test(groups = {"BookPath","RoundTrip","FlightHotelCar","Guest","DomesticDomestic","Outside21Days","Adult","NoBags"
          ,"NoSeats","CheckInOptions", "ShortCutBoarding","OptionalUI","PaymentUI"})
  public void CP_Price_Display_Total_Due_Vacation_Path_Flight_Hotel_Car_with_SB(@Optional("NA") String platform)  {
    //Mention Suite and Browser in reports
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC373873 under GoldFinger Suite on " + platform + " Browser", true);
    }

    //Reservation Credit Path Constant variables
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Vacation";
    final String TRIP_TYPE          = "Flight+Hotel+Car";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "FLL";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "LAS";
    final String DEP_DATE           = "135";
    final String ARR_DATE           = "137";
    final String ADULTS             = "3";
    final String CHILDREN           = "0";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";
    final String DRIVER_AGE         = "25+";
    final String HOTEL_ROOM         = "2 Rooms";
    //Flight Availability Page Constant Values
    final String UPGRADE_VALUE      = "BookIt";
    //Options Page Constant Values
    final String OPTION_VALUE       = "ShortCutBoarding|CheckInOption:MobileFree";
    //Payment Page Constant Value
    final String CARD_TYPE          = "MasterCard";
    final String TRAVEL_GUARD       = "NotRequired";

    //  Step 1	Access GoldFinger testing environment
    openBrowser(platform);
    pageMethodManager.getHomePageMethods().launchSpiritApp();

    //  Step 2	Create a vacation booking DOM-DOM | F+H+C | 3 ADT | 2 rooms | 25+ | 3 months out
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
    //  Step 4	Select ROOMS FROM $XXX.XX
    //  Step 5	Select room
    pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA", "NA");

    //  Step 6	Select ADD CAR on any car
    pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");

    //  Step 7	Select Book It
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    //  Step 8	Enter all pax information and select continue
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    //get adult mandatory details
    PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");
    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),passengerInfoData.firstName+" "+passengerInfoData.lastName);
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //  Step 9	Continue without bags
    //  Step 10	I don't need bags
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

    //  Step 11	Continue without selecting seats
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

    //  Step 12	Verify Flight Flex is not grayed out
    ValidationUtil.validateTestStep("Validating Flight Flex options is available",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardNotAvailableText()));

    //  Step 13	Select SB
    //  Step 14	Select I'll check in at Spirit.com/Mobile for free and continue
    pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
    //verify Shortcut Boarding is selected
    ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

    //  Step 15	Complete booking
    pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions("ShortCutBoarding");
    pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
    pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);


    /**Booking over $4k so cannot be completed*/
    //    pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
    //  Step 16	Verify total paid is displaying as packaging item and that options is displaying SB
  }
}
