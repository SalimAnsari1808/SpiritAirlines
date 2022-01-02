package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373761
//Description: Task 27182: TC373761 - US 20607 - 004 - CP - New Hotel Page Feature - Flight + Hotel - Validate default sorting and filter settings for a standard booking
//Created By: Gabriela
//Created On: 20-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373761 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest", "NonStop", "HotelsUI"})
    public void CP_New_Hotel_Page_Feature_Flight_Hotel_Validate_default_sorting_and_filter_settings_for_a_standard_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373761 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "106";
        final String ADULT              = "8";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";

//- Step 1: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: On the Home page Select the Vacation tab, specific Flight + Hotel
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Start a Vacation Flight+Hotel booking | DOM to DOM| outside 48h | 8 ADT | 2 Rooms|  Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Go to the Choose your Hotel section and validate there is a vertical section left aligned with filters
        WaitUtil.untilPageLoadComplete(getDriver());
        String counter = pageObjectManager.getHotelPage().getHotelCounterText().getText();

        ValidationUtil.validateTestStep("Validating vertical filter section is displayed under 'Choose Your Hotel' title",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().geHotelFilterContainerPanel()));

//- Step 5: Validate the first filter is "Hotel Search"
        ValidationUtil.validateTestStep("Validating 'Hotel Search' is the first options in the vertical filter",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelSearchLink()));

//- Step 6: Type a Hotel Name and validate the list of available hotels updates to just what meets the search criteria
        String hotelName = pageObjectManager.getHotelPage().getHotelNamesText().get(3).getText();
        System.out.println("hotelName: " + hotelName);

        pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys(hotelName);
        WaitUtil.untilTimeCompleted(1200);

        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelCard().size(); i ++)
        {
            ValidationUtil.validateTestStep("Validating hotel can be filtered by name",
                    pageObjectManager.getHotelPage().getHotelNamesText().get(i).getText(),hotelName);
        }

//- Step 7: Moving down, there should be a Pricing slider. Expand filter and validate "$price to $price per person" is displaying
        //TODO: Original Xpath defined on hotel page is not being using in this case, due the verification requirements.
        List<WebElement> priceRange = getDriver().findElements(By.xpath("//div[contains(@class,'filter-container')]//div[contains(@class,'current-range')]//span"));
        List<String> priceRangeText = new ArrayList<>();

        for (int i = 0; i < priceRange.size(); i ++)
        {
            priceRangeText.add(priceRange.get(i).getText());
        }

        String rangeText = priceRangeText.get(0) + " to " + priceRangeText.get(1) + " " + priceRangeText.get(2); //Saving price range information for next validation
        System.out.println("rangeText: " + rangeText);
        System.out.println("displaying: "+ pageObjectManager.getHotelPage().getHotelPriceRangePerPersonText().getText());

        ValidationUtil.validateTestStep("validating '$price to $price per person is displaying",
                rangeText,pageObjectManager.getHotelPage().getHotelPriceRangePerPersonText().getText());

//- Step 8: Drag the start (left)  handle and the end (right) handles (circles) to adjust the price range
        //Moving slider to lef for higer price
        WebElement slider = getDriver().findElement(By.xpath("(//ng5-slider//span)[9]"));
        Actions move = new Actions(getDriver());
        Action action = move.dragAndDropBy(slider, 30, 0).build();
        action.perform();

        ValidationUtil.validateTestStep("Validating price range was modified",
                !rangeText.equals(pageObjectManager.getHotelPage().getHotelPriceRangePerPersonText().getText()));

//- Step 9: Validate there is an Amenities filter, default collapsed
        //Invalid Step. No Amenities filter

//- Step 10: Validate there is a Hotel Rating filter, default collapsed
        ValidationUtil.validateTestStep("Validating price range was modified",
               TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelRatingLink()));

//- Step 11: Validate a "x clear" shows above each section where a filter has been applied.
        ValidationUtil.validateTestStep("Validating 'Clear' link is displayed on 'Hotel Search' section",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getClearHotelSearchFilterButton()));

        ValidationUtil.validateTestStep("Validating 'Clear' link is displayed on 'Price' section",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getClearPriceFilterButton()));

//- Step 12: Validate a "x Clear All Filters" hyperlink displays above the filters.
        ValidationUtil.validateTestStep("Validating 'Clear All filter' is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getClearAllFiltersButton()));

//- Step 13: Click the "x Clear All Filters" and validate filters are removed
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating all filters are cleared",
                counter.equals(pageObjectManager.getHotelPage().getHotelCounterText().getText()));
    }
}