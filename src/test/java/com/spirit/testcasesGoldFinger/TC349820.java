package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
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
//Test Case ID: TC349820
//Description: Task: 28220 | TC349820 - 002 - CP - Flight + Hotel - Availability Page - Validate Filter functionality
// Created By: Anthony Cardona
//Created On: 03-Dec--2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC349820 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightHotel" , "DomesticDomestic", "Outside21Days", "Adult", "Guest", "HotelsUI"})
    public void CP_Flight_Hotel_Availability_Page__Validate_Filter_functionality (@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349820 under GOLDFINGER Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "90";
        final String ARR_DATE           = "100";
        final String ADULTS             = "2";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String HOTEL_ROOM         = "1 Room";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//Step2: Start booking a Vacation Package [Flight + Hotel] for 2 adults outside of 90 days.
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTEL_ROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        final String FLIGHTANDHOTELS_URL = "/flights-hotels";

        ValidationUtil.validateTestStep("User navigated to Flights + Hotels page", getDriver().getCurrentUrl(), FLIGHTANDHOTELS_URL);

        JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getHotelPage().getChooseYourHotelHeaderText());

        //Step 3: Next to the filter, validate there is a hotel count formatted "# hotels".
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText();

        ValidationUtil.validateTestStep("The \"Review Your Flights\" SubHeader is displayed", getDriver().findElement(By.xpath("(//app-flights-hotels-page//div[1]//span[1]/..)[1]")).getText(), "Review Your Flights");
        ValidationUtil.validateTestStep("The \"Choose Your Hotel\" SubHeader is displayed", pageObjectManager.getHotelPage().getChooseYourHotelHeaderText().getText(), "Choose Your Hotel");

//Step 3: Go to the Choose your Hotel section and validate there is a vertical section left aligned with filters
        ValidationUtil.validateTestStep("Validating Hotel Search filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelSearchLink()));
        ValidationUtil.validateTestStep("Validating Price filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPriceLink()));
        ValidationUtil.validateTestStep("Validating Hotel Rating filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelRatingLink()));

//Step4 : Validate the first filter is "Hotel Search"
        ValidationUtil.validateTestStep("Validating 'Hotel Search' is the first options in the vertical filter",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNameTextBox()));

//Step 5: Type a Hotel Name and validate the list of available hotels updates to just what meets the search criteria
        String hotelName = pageObjectManager.getHotelPage().getHotelNamesText().get(3).getText();
        System.out.println("hotelName: " + hotelName);

        pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys(hotelName);
        WaitUtil.untilTimeCompleted(1200);

        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelCard().size(); i++) {
            ValidationUtil.validateTestStep("Validating hotel can be filtered by name",
                    pageObjectManager.getHotelPage().getHotelNamesText().get(i).getText(), hotelName);
        }


//- Step 6: Moving down, there should be a Pricing slider. Expand filter and validate "$price to $price per person" is displaying
        //TODO: Original Xpath defined on hotel page is not being using in this case, due the verification requirements.
        List<WebElement> priceRange = getDriver().findElements(By.xpath("//div[contains(@class,'filter-container')]//div[contains(@class,'current-range')]//span"));
        List<String> priceRangeText = new ArrayList<>();

        for (int i = 0; i < priceRange.size(); i++) {
            priceRangeText.add(priceRange.get(i).getText());
        }

        String rangeText = priceRangeText.get(0) + " to " + priceRangeText.get(1) + " " + priceRangeText.get(2); //Saving price range information for next validation
        System.out.println("rangeText: " + rangeText);
        System.out.println("displaying: " + pageObjectManager.getHotelPage().getHotelPriceRangePerPersonText().getText());

        ValidationUtil.validateTestStep("validating '$price to $price per person is displaying",
                rangeText, pageObjectManager.getHotelPage().getHotelPriceRangePerPersonText().getText());

//- Step 7: Drag the start (left)  handle and the end (right) handles (circles) to adjust the price range
        //Moving slider to lef for higer price
        WebElement slider = getDriver().findElement(By.xpath("(//ng5-slider//span)[9]"));
        Actions move = new Actions(getDriver());
        Action action = move.dragAndDropBy(slider, 30, 0).build();
        action.perform();

        ValidationUtil.validateTestStep("Validating price range was modified",
                !rangeText.equals(pageObjectManager.getHotelPage().getHotelPriceRangePerPersonText().getText()));

//- Step 8: Validate there is an Amenities filter, default collapsed
        //Invalid Step. No Amenities filter

//Step 9: Validate there is a Hotel Rating filter, default collapsed
        ValidationUtil.validateTestStep("Validating Hotel Rating filter is displayed ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelRatingLink()));

//- Step 10: Validate a "x clear" shows above each section where a filter has been applied.
        ValidationUtil.validateTestStep("Validating 'Clear' link is displayed on 'Price' section",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getClearPriceFilterButton()));

//- Step 11: Validate a "x Clear All Filters" hyperlink displays above the filters.
        ValidationUtil.validateTestStep("Validating 'Clear All filter' is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getClearAllFiltersButton()));


//- Step 12: Click the "x Clear All Filters" and validate filters are removed
        int hotelCounterWithFilter = Integer.parseInt(pageObjectManager.getHotelPage().getHotelCounterText().getText().replace(" Hotels","").trim());

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilPageLoadComplete(getDriver());

        int hotelCounterWithOutFilter = Integer.parseInt(pageObjectManager.getHotelPage().getHotelCounterText().getText().replace(" Hotels","").trim());

        ValidationUtil.validateTestStep("The hotel counter correctly shows more hotels after clearing filter" , hotelCounterWithOutFilter > hotelCounterWithFilter);
    }
}