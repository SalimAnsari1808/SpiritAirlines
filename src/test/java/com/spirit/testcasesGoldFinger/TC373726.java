package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.WindowAdapter;

//**********************************************************************************************
//Test Case ID: TC373726
//Description: Task 27151: TC373726 - US 17576 - 001 - CP - Vacation Hotel - Flight + Hotel + Car - Validate Facade and page format for Cars on a Standard booking
//Created By: Gabriela
//Created On: 05-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373726 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightHotelCar", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "BookIt",
            "FlightAvailabilityUI", "CarsUI", "HotelsUI", "NoBags","NoSeats","CheckInOptions","Visa"})
    public void CP_Vacation_Hotel_Flight_Hotel_Car_Validate_Facade_and_page_format_for_Cars_on_a_Standard_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373726 under GoldFinger Suite on " + platform + " Browser", true);
        }
//Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 1: Using Google Chrome access to Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Start Vacation [Flight + Car + Hotel] booking, departure in 3 months out for 5 ADT, 2 rooms and driver age +25
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Verify "Hotel Name" "Sort By" and "Display Number" filter exist
        //Step is not longer valid

//- Step 4: Scroll to the bottom and verify there is not an option to continue without Hotel selection
        ValidationUtil.validateTestStep("Validating there is not option to continue without a Hotel selection",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getContinueWithoutHotelButton()));

//- Step 5: Type a Hotel name and make sure list them below
        String hotelName = pageObjectManager.getHotelPage().getHotelNamesText().get(1).getText();
        pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys(hotelName);
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Validating hotel filtered by name is displayed",
                pageObjectManager.getHotelPage().getHotelNamesText().get(0).getText(), hotelName);

//- Step 6: Clear the "Hotel Name" field filter
        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();
        int count = 0;
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelNamesText().size(); i ++)
        {
            if (pageObjectManager.getHotelPage().getHotelCard().get(i).isDisplayed())
            {
                count++;
            }
        }
        System.out.println("count: "+ count);
        ValidationUtil.validateTestStep("Validating Hotels list displayed again without preferences", count != 1);

//- Step 7: Drop Down "Sort By" box and verify the options displayed
        //Step is not longer valid

//- Step 8: Drop down "Display Number" box and verify the options displayed
        //Step is not longer valid

//- Step 9: Verify each Content box will display:
        for (int i = 0; i <  pageObjectManager.getHotelPage().getHotelNamesText().size(); i ++)
        {
            //Hotel image
            ValidationUtil.validateTestStep("Verifying Hotel Image is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardImageImage()));

            //If hotel promotion exist display
            //Invalid

            //Hotel Name
            ValidationUtil.validateTestStep("Verifying Hotel Name is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(i)));

            //Hotel address
            pageObjectManager.getHotelPage().getHotelNamesText().get(i).click();
            WaitUtil.untilPageLoadComplete(getDriver());
            WaitUtil.untilTimeCompleted(1200);
            ValidationUtil.validateTestStep("Verifying Hotel Address is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelAddressText().get(i)));

            //Map hyperlink with deeplink logic to google map
            ValidationUtil.validateTestStep("Verifying Hotel Map Hyperlink is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelMapButton().get(i)));

            pageObjectManager.getHotelPage().getHotelMapButton().get(i).click();
            WaitUtil.untilPageLoadComplete(getDriver());
            WaitUtil.untilTimeCompleted(3000);

            ValidationUtil.validateTestStep("Verifying Hotel Interactive Map is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotalPageMapPanel()));

            //5 star rating
            ValidationUtil.validateTestStep("Verifying Hotel Map Hyperlink is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelStarRatingImage().get(i)));

            //More Info
            //Step is not longer valid

            //The daily pricing in USD per person
            ValidationUtil.validateTestStep("Verifying Hotel price is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(i)));
            System.out.println("hotel price: "+ pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(i).getText());

            ValidationUtil.validateTestStep("Verifying Hotel price per person is displayed for each tile",
                    TestUtil.verifyElementDisplayed((getDriver().findElement(By.xpath("//app-hotel-list-item//h3//following::p[contains(text(),'per person, Flight + Hotel')]")))));

            //Uplift promotion when applicable
            //TODO: [IN:16205] CP: BP: VC: F+H+C: Uplift prices are not being displayed for Hotels
            //Code needs to be implemented once uplift is present for xpath
        }

//- Step 10: Click on "More Info" link
        //Step is not longer valid

//- Step 11: Click on "Less Info" link
        //Step is not longer valid

//- Step 12: Click on View button // for this case is "SELECT ROOM" button
//- Step 13: Verify Spirit site and HotelBeds information match
//- Step 14: Select a standard room and click on SELECT ROOM button
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

//- Step 15: Select "continue" at the bottom of the page
        //Step is not longer valid due the passenger is automatically taken to cars page after select a hotel

//- Step 16: Scroll to the bottom and verify there is not an option to continue without car selection
        try {
            ValidationUtil.validateTestStep("Validating there is not option to continue without a Car selection",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton()));
        } catch (Exception e){}

//- Step 17: Click on BOOK CAR button
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 18: Click "CONTINUE" at the bottom of the page.
        //Step is not longer valid due the passenger is automatically taken to Passenger Information page after select a car

//- Step 19: Click on BOOK IT button
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 20: fill out passenger information and continue
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Ste 21 & 22: Click on Continue without adding bags & Click on i dont need bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 23: Click on Continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 24: Choose Check-in free option and continue
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 25: Complete the booking and record the PNR information
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//
////- Step 26- 29: Not valid step to automate
////- Step 30: Open Carnect and search with carnect reservation number giving on payment page
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
////- Step 31: Open HBG hotel page. and search for hotel confirmation and note down price paid for hotel
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//- Step 32: add HBG & Carnect prices, and match with DPF charge on skypseed
    }
}