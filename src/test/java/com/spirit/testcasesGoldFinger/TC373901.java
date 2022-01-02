package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test Case ID: TC373901
//Description: Task: 27938 | TC373902 - US 17576 - 004 - CP - Vacation Hotel - Flight + Hotel + Car - Validate Facade and page format for Cars on a booking with an International Origin
//Created By: Anthony Cardona
//Created On: 01-Dec-2019
//Reviewed By: kartik Chauhan
//Reviewed On: 10-Dec- 2019
//**********************************************************************************************
public class TC373901 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath", "FlightHotelCar", "InternationalDomestic", "Outside21Days", "Adult", "Guest" , "NoBags" ,
                    "NoSeats" , "HotelsUI", "FlightAvailabilityUI","Visa","CheckInOptions"})
    public void Vacation_Hotel_Flight_Hotel_Car_Validate_Facade_and_page_format_for_Cars_on_a_booking_with_an_International_Origin(@Optional("NA")String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373901 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CUN";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        final String ROOMS_VALUE        = "1 Room";

        // Hotel Page Constant Values
        final String HOTEL_SEARCH_NAME  = "MGM";
        final String HOTEL_BOOK_NAME    = "MGM";
        final String HOTEL_ROOM_TYPE    = "standard";

        final String UPGRADE_VALUE      = "BookIt";
        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

// Step 1: Start Vacation [Flight + Car + Hotel] booking, departure in 3 months out for 5 ADT and 3 CHD passengers, 2 rooms and driver age +25
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().clickSearchButton();


// Step 2: Verify "Hotel Name" "Sort By" and "Display Number" filter exist
        //invalid steps as this is old functionality

//Step 3: Scroll to the bottom and verify there is not an option to continue without Hotel selection
        try
        {
            ValidationUtil.validateTestStep("Validating the user cannot continue without hotel selection",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getContinueWithoutHotelButton()));
        }catch (Exception e){}

// Step 4: Type a Hotel name and make sure list them below
        pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys(HOTEL_SEARCH_NAME);
        System.out.println(pageObjectManager.getHotelPage().getHotelNamesText().get(0).getText().toLowerCase());

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating that there are hotels with HOTEL_SEARCH_NAME listed",
                pageObjectManager.getHotelPage().getHotelNamesText().get(0).getText().toLowerCase().contains(HOTEL_SEARCH_NAME.toLowerCase()));

//Step 5: Clear the "Hotel Name" field filter
        pageObjectManager.getHotelPage().getHotelNameTextBox().clear();
        pageObjectManager.getHotelPage().getClearHotelSearchFilterButton().click();
        WaitUtil.untilTimeCompleted(1000);


// Step 6: Drop Down "Sort By" box and verify the options displayed
// Step 7: Drop down "Display Number" box and verify the options displayed
// Invalid test steps for 7 and 8

// Step 8: "Verify each Content box will display:
        for (int i = 0; i <  pageObjectManager.getHotelPage().getHotelNamesText().size(); i ++)
        {
            //Hotel image
            ValidationUtil.validateTestStep("Verifying Hotel Image is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardImageImage().get(i)));

            //If hotel promotion exist display - skip

            //Hotel Name
            ValidationUtil.validateTestStep("Verifying Hotel Name is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(i)));

            //Hotel address
            pageObjectManager.getHotelPage().getHotelNamesText().get(i).click();
            WaitUtil.untilTimeCompleted(800);
            ValidationUtil.validateTestStep("Verifying Hotel Address is displayed for each tile",
                    TestUtil.verifyElementDisplayed(getDriver(), By.xpath("//div[@class='card']//div[3]//p//span")));

            //Map hyperlink with deeplink logic to google map
            ValidationUtil.validateTestStep("Verifying Hotel Map Hyperlink is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelMapButton().get(i)));

            //map button next to rating
            pageObjectManager.getHotelPage().getHotelMapButton().get(i).click();

            WaitUtil.untilPageLoadComplete(getDriver());
            WaitUtil.untilTimeCompleted(800);

            ValidationUtil.validateTestStep("Verifying Hotel Interactive Map is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotalPageMapPanel()));

            //5 star rating
            ValidationUtil.validateTestStep("Verifying Hotel Map Hyperlink is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelStarRatingImage().get(i)));

            //More info no longer valid

            //The daily pricing in USD per person
            ValidationUtil.validateTestStep("Verifying Hotel price is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(i)));
            System.out.println("hotel price: "+pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(i).getText());

            ValidationUtil.validateTestStep("Verifying Hotel price per person is displayed for each tile",
                    TestUtil.verifyElementDisplayed((getDriver().findElement(By.xpath("//app-hotel-list-item//h3//following::p[contains(text(),'per person, Flight + Hotel')]")))));

            //Uplift promotion when applicable
            //TODO: [IN:16205] CP: BP: VC: F+H+C: Uplift prices are not being displayed for Hotels
            //Code needs to be implemented once uplift is present for xpath
        }

// Step 9: Click on "More Info" link
// Invalid Step
// Step 10: Click on "Less Info" link
// Invalid Step

// Step 11: Click on View button // for this case is "SELECT ROOM" button
// Step 12: Select a standard room and click on SELECT ROOM button
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage(HOTEL_BOOK_NAME, "NA");

// Step 13: Select "continue" at the bottom of the page
//Step is not longer valid due the passenger is automatically taken to cars page after select a hotel

//Step 14: Scroll to the bottom and verify there is not an option to continue without car selection

// Step 15: Click on BOOK CAR button
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");
        //Cannot verify that there us a black border since the user is taken to the passenger information page

// Step 16: Select "continue" at the bottom of the page
//Step is not longer valid due the passenger is automatically taken to PassengerInfo page after select a hotel

//Step 17: Click on BOOK IT button
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Step 18: fill out passenger information and continue
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 19: Click on Continue without adding bags
//Step 20: Click on i dont need bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//Step 21: Click on Continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 22: Choose Check-in free option and continue
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 23: Complete the booking and record the PNR information
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();


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
