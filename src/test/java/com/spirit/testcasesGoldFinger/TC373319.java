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
//Test Case ID: TC373319
//Description: Task:27935 | TC373319 - 003 - CP - Vacation Flight + Hotel - Validate the Facade for a booking with an Active Duty Military Passenger
//Created By: Anthony Cardona
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373319 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"RoundTrip" , "FlightCar", "DomesticDomestic", "Guest" , "Military" , "Outside21Days", "Adult", "CarryOn" , "CheckedBags" , "NoSeats" , "Cars" , "CarsUI" , "MasterCard"})

    public void Vacation_Flight_Hotel_Validate_the_Facade_for_a_booking_with_an_Active_Duty_Military_Passenger(@Optional("NA")String platform) {

        //********************Navigate to Payment Page**********************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373319 under GOLDFINGER Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE       = "English";
        final String JOURNEY_TYPE   = "Vacation";
        final String TRIP_TYPE      = "flight+Hotel";
        final String DEP_AIRPORTS   = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS   = "AllLocation";
        final String ARR_AIRPORT_CODE = "LAS";
        final String DEP_DATE       = "100";
        final String ARR_DATE       = "103";
        final String ADULTS         = "1";
        final String CHILD          = "0";
        final String INFANT_LAP     = "0";
        final String INFANT_SEAT    = "0";
        final String HOTEL_ROOM      = "1 Room";

        final String UPGRADE_VALUE      = "BookIt";

        final String DEP_BAGS       = "Carry_1|Checked_1";
        final String BAGS_FARE      = "Standard";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step2: Start the Vacation (Flight + Hotel) booking process for 1 ADT (Military passenger), 3 months out
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTEL_ROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 4: Verify shopping cart is not displayed on the vacation booking path
        ValidationUtil.validateTestStep("There shopping cart is not displayed on the Flight + Hotel page" , !TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));

//Step 5: Verify "Hotel Search", "Price" and "Hotel Rating" expandable filters appear vertically on the left side of the Hotel tiles.
        ValidationUtil.validateTestStep("There is no option to continue without cars" , !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton()));


//- Step 5: Under the "Choose Your Hotel" section, validate there is a vertical section left aligned with filters
        ValidationUtil.validateTestStep("Validating Hotel Search filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelSearchLink()));
        ValidationUtil.validateTestStep("Validating Price filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPriceLink()));
        ValidationUtil.validateTestStep("Validating Hotel Rating filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelRatingLink()));


// Step 6: Click on "Hotel Search" | Verify "Hotel Search" expands to reveal a text entry box and the caret is not pointing up "^".
        pageObjectManager.getHotelPage().getHotelSearchLink().click();

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("after clicking the hotel search link the search hotel name textBox is no longer displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNameTextBox()));


        ValidationUtil.validateTestStep("The chevron is pointing down",
                getDriver().findElement(By.xpath(pageObjectManager.getHotelPage().xpath_HotelSearchLink + "//../app-chevron//i")).getAttribute("style"), "transform: rotate(-180deg);");

//Step 7: "XXX Hotels" appears on the top left corner of the top Hotel tile.
        int hotelResultCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Hotels" , "").trim());
        ValidationUtil.validateTestStep("The hotel count is displayed onto the car section" , hotelResultCount > 0);

//Step 8: "Sort By:" feature is present on the top right of the uppermost Hotel tile. Next to "Sort By" verbiage there will be 3 buttons: RECOMMENDED | STARS | PRICE
        ValidationUtil.validateTestStep("The sort by RECOMMENDED option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getRecommendedButton()));
        ValidationUtil.validateTestStep("The sort by STARS option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getStarsButton()));
        ValidationUtil.validateTestStep("The sort by PRICE option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getPriceButton()));
//Step 9: Verify at bottom of the page there is no "Continue without a hotel" option
        ValidationUtil.validateTestStep("There is no \"Continue without a hotel\" option" ,
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getContinueWithoutHotelButton()));

//Step 10: Verify each Content box will display:
        for( int i = 0 ; i < pageObjectManager.getHotelPage().getHotelCardImageImage().size() ; i ++)
        {
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardImageImage().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel image is not displayed for card number " + (i+1) ,false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel name text is not displayed for card number " + (i+1) ,false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelAddressText().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel Area is not displayed for card number " + (i+1) ,false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelMapButton().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel Map hyperlink to Google maps is not displayed for card number " + (i+1) ,false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelStarRatingImage().get(i)) == false)
                ValidationUtil.validateTestStep("TheHotel Star rating is not displayed for card number " + (i+1) ,false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel Pricing in USD per person is not displayed for card number " + (i+1) ,false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPageUpliftText().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel Uplift promotion when applicable is not displayed for card number " + (i+1) ,false);
        }
        ValidationUtil.validateTestStep("All content in each Hotel block is displayed" , true);

//Step 11: Click on the hotel name within the Hotel tile.
        //A Drop down should expands to display the tabs: Overview, Policies, Accommodations, Dining, Activities & Entertainment, Photos, Map
        pageObjectManager.getHotelPage().getHotelNamesText().get(0).click();
        ValidationUtil.validateTestStep("There is a pop-up displaying hotel  Overview, Policies, Accommodations, Dining, Activities & Entertainment, Photos, Map" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpOverviewTab()) &&
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpPoliciesTab()) &&
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpAmenitiesTab()) &&
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpDiningTab()) &&
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpRoomsTab()) &&
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpPhotosTab()) &&
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpMapTab())) ;

//Step 12: Open HBG website and locate the same Hotel for the city and date chosen on the booking (Goldfinger) Reach out your team lead for gets the update URL and credentials.
        /*
        Cannot Automate this step
         */

//Step 13: Select a Room
        WaitUtil.untilPageLoadComplete(getDriver());
        if (getDriver().findElement(By.xpath("//app-filter-name//i")).getAttribute("style").contains("-180"))
        {
            getDriver().findElement(By.xpath("//app-filter-name//i")).click();
            WaitUtil.untilTimeCompleted(1200);
        }
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM", "NA");

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade("BookIt");

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);


        //verify user navigated to passenger Information page
        final String PASSENGER_PAGE     = "book/passenger";
        ValidationUtil.validateTestStep("User reached the Passenger Info Page", getDriver().getCurrentUrl(),PASSENGER_PAGE);

//Step 16: Fill customers info, check "Active Duty US Military Personnel" box
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 17: Populate all the required information to reach Options page and validate that the Flight Flex is offered after the Hotel been selected.
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(DEP_BAGS);
        checkMilitaryBagsPrice();
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        ValidationUtil.validateTestStep("The hotel options displays Flight Flex" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//Step 18: Continue with booking process thru Confirmation page
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();

        pageMethodManager.getOptionsPageMethods().selectOptions("CheckInOption:MobileFree");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard("NotRequired");
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails("MasterCard");
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//Step 19: Verify that in addition to the flight PNR a Packaging confirmation will display: Hotel Confirmation
        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //cancel Hotel booking
        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

        /*
        Cannot automate step 20-22 due to skyspeed validation
         */

    }

    private void checkMilitaryBagsPrice()
    {
        final String ZERO_BAG_PRICE = "$0.00";
        boolean statusFlag = true;

        //loop through all departing carry-on bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().size();bagsCounter++) {
            //check carry-on Bag price for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFlag = false;
            }
        }
        //Verify Departing Carry-On Bag prices
        ValidationUtil.validateTestStep("Verify Departing Carry-On Bag prices is zero on Bags Page", statusFlag);

        //set flag to true for returning carry-on bags
        statusFlag = true;

        //loop through all returning carry-on bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getReturningNextCarryOnPriceText().size();bagsCounter++) {
            //check carry-on Bag price for all passenger
            if(!pageObjectManager.getBagsPage().getReturningCarryOnPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFlag = false;
            }
        }

        //Verify Departing Carry-On Bag prices
        ValidationUtil.validateTestStep("Verify Departing Carry-On Bag prices is zero on Bags Page", statusFlag);

        //set flag to true for departing checked bags
        statusFlag = true;

        //loop through all departing checked bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().size();bagsCounter++) {
            //check checked bag bag prices for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFlag = false;
            }
        }

        //Verify Departing Checked Bag prices
        ValidationUtil.validateTestStep("Verify Departing Checked Bag prices is zero on Bags Page", statusFlag);

        //set flag to true for returning checked bags
        statusFlag = true;

        //loop through all returning checked bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getReturningCheckedBagPriceText().size();bagsCounter++) {
            //check checked bag bag prices for all passenger
            if(!pageObjectManager.getBagsPage().getReturningCheckedBagPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFlag = false;
            }
        }

        //Verify Returning Checked Bag prices
        ValidationUtil.validateTestStep("Verify Departing Checked Bag prices is zero on Bags Page", statusFlag);
    }
}