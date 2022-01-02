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
//Test Case ID: TC373900
//Description : CP - Vacation Hotel - Flight + Hotel + Car - Validate Facade and page format for Cars on a Max Passenger (8) booking
//Created By  : Un Fai Chan
//Created On  : 11/27/2019
//Reviewed By : Gabriela
//Reviewed On : 10-Dec-2019
//**********************************************************************************************

public class TC373900 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightHotelCar", "DomesticDomestic", "Outside21Days", "Adult", "Child", "Guest", "NonStop", "HotelsUI"})
    public void CP_Vacation_Hotel_Flight_Hotel_Car_Validate_Facade_and_page_format_for_Cars_on_a_Max_Passenger_8_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373900 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "5";
        final String CHILD              = "3";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        final String ROOMS_VALUE        = "2 Rooms";

        // Hotel Page Constant Values
        final String HOTEL_SEARCH_NAME  = "Universal";
        final String HOTEL_BOOK_NAME    = "Universal";

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

        // Step 2: Enter DOB of birth the child(s) and Continue
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();


        // Step 3: Verify "Hotel Name" "Sort By" and "Display Number" filter exist
        // Verify Hotel Name filter exists
        ValidationUtil.validateTestStep("Hotel Name filter exists",TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNameTextBox()));

        // Verify Sort By filter exists
        ValidationUtil.validateTestStep("Sort By filter exists",TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getSortBylabel()));


        // Step 4: Scroll to the bottom and verify there is not an option to continue without Hotel selection
        ValidationUtil.validateTestStep("Validating the user cannot continue without hotel selection",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getContinueWithoutHotelButton()));


        // Step 5: Type a Hotel name and make sure list them below
        pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys(HOTEL_SEARCH_NAME);
        System.out.println(pageObjectManager.getHotelPage().getHotelNamesText().get(1).getText().toLowerCase());

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating that there are hotels with HOTEL_SEARCH_NAME listed",
                pageObjectManager.getHotelPage().getHotelNamesText().get(1).getText().toLowerCase().contains(HOTEL_SEARCH_NAME.toLowerCase()));

        // Step 6: Clear the "Hotel Name" field filter 
        pageObjectManager.getHotelPage().getHotelNameTextBox().clear();
        pageObjectManager.getHotelPage().getClearHotelSearchFilterButton().click();

        // Step 7: Drop Down "Sort By" box and verify the options displayed
        ValidationUtil.validateTestStep("Validating RECOMMENDED Button is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getRecommendedButton()));
        ValidationUtil.validateTestStep("Validating STARS Button is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getStarsButton()));
        ValidationUtil.validateTestStep("Validating PRICE Button is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getPriceButton()));

        // Step 8: Drop down "Display Number" box and verify the options displayed
        // Invalid test step

        // Step 9: "Verify each Content box will display:
        //for(int i = 0 ; i < 1; i++)
        for (int i = 0; i <  pageObjectManager.getHotelPage().getHotelNamesText().size(); i ++)
        {
            //Hotel image
            ValidationUtil.validateTestStep("Verifying Hotel Image is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardImageImage().get(i)));

            //If hotel promotion exist display - skip

            //Hotel Name
            ValidationUtil.validateTestStep("Verifying Hotel Name is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(i)));
            WaitUtil.untilPageLoadComplete(getDriver());

            //Hotel address
            pageObjectManager.getHotelPage().getHotelNamesText().get(i).click();
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("Verifying Hotel Address is displayed for each tile",
                    TestUtil.verifyElementDisplayed(getDriver(),By.xpath("//div[@class='card']//div[3]//p//span")));

            //Map hyperlink with deeplink logic to google map
            ValidationUtil.validateTestStep("Verifying Hotel Map Hyperlink is displayed for each tile",
                    TestUtil.verifyElementDisplayed(getDriver().findElements(By.xpath("//app-rating//following-sibling::span[2]//a")).get(i)));

            getDriver().findElements(By.xpath("//app-rating//following-sibling::span[2]//a")).get(i).click();

            WaitUtil.untilPageLoadComplete(getDriver());

            WaitUtil.untilTimeCompleted(3000);

            ValidationUtil.validateTestStep("Verifying Hotel Interactive Map is displayed for each tile",
                    TestUtil.verifyElementDisplayed(getDriver(),By.xpath("//app-interactive-map//agm-map[@class='sebm-google-map-container']")));

            //5 star rating
            ValidationUtil.validateTestStep("Verifying Hotel Map Hyperlink is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelStarRatingImage().get(i)));

            //More Info
            //Step is not longer valid

            //The daily pricing in USD per person
            ValidationUtil.validateTestStep("Verifying Hotel price is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(i)));
            System.out.println("hotel price: "+pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(i).getText());

            ValidationUtil.validateTestStep("Verifying Hotel price per person is displayed for each tile",
                    TestUtil.verifyElementDisplayed((getDriver().findElement(By.xpath("//app-hotel-list-item//h3//following::p[contains(text(),'per person, Flight + Hotel')]")))));

            //Uplift promotion when applicable
            //TODO: [IN:16205] CP: BP: VC: F+H+C: Uplift prices are not being displayed for Hotels
            String totalPrice1 = pageObjectManager.getHotelPage().getTotalPriceText().get(i).getText().replace("$","");
            String totalPrice2 = totalPrice1.replace(" Total","");
            double totalPrice = Double.parseDouble(totalPrice2.replace(",",""));
            if (totalPrice >= 300) {
                ValidationUtil.validateTestStep("User verify Uplift option is displaying is displayed",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPageUpliftLink().get(i)));
            }
        }

        // Step 10: Click on "More Info" link
        // Invalid Step
        // Step 11: Click on "Less Info" link
        // Invalid Step

        // Step 12: Click on View button // for this case is "SELECT ROOM" button
        // Step 13: Select a standard room and click on SELECT ROOM button
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage(HOTEL_BOOK_NAME, "NA");

        // Step 14: Select "continue" at the bottom of the page
        //Step is not longer valid due the passenger is automatically taken to cars page after select a hotel

        // Step 15: Scroll to the bottom and verify there is not an option to continue without car selection
        ValidationUtil.validateTestStep("Validating there is not option to continue without a Car selection",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton()));
    }
}
