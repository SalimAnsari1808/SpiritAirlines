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
//Test Case ID: TC373762
//Description: Task 27183: TC373762 - US 20607 - 007 - CP - New Hotel Page Feature - Flight + Hotel + Car - Validate default sorting and filter settings for a standard booking
//Created By: Gabriela
//Created On: 20-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373762 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult","Child",
                    "InfantLap", "Guest", "NonStop", "CarsUI","HotelsUI"})
    public void CP_New_Hotel_Page_Feature_Flight_Hotel_Car_Validate_default_sorting_and_filter_settings_for_a_standard_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373762 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "106";
        final String ADULT              = "2";
        final String CHILD              = "1";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

//- Step 1: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: On the Home page Select the Vacation tab, specific Flight + Hotel + Car.
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Start a Vacation Flight + Hotel + Car booking | DOM to DOM| outside 48h | 2 ADT + 1 Lap + 1 Child |  Driver's age 25 +|1 Room | Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Input the age for the Lap and the child passengers
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 5: Under the "Choose Your Hotel" section, validate there is a vertical section left aligned with filters
        ValidationUtil.validateTestStep("Validating vertical filter section is displayed under 'Choose Your Hotel' title",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().geHotelFilterContainerPanel()));

//- Step 6: Validate the first filter is "Hotel Search"
        ValidationUtil.validateTestStep("Validating 'Hotel Search' is the first options in the vertical filter",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelSearchLink()));

//- Step 7: Type a Hotel Name and validate the list of available hotels updates to just what meets the search criteria
        String hotelName = pageObjectManager.getHotelPage().getHotelNamesText().get(3).getText();
        System.out.println("hotelName: " + hotelName);

        pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys(hotelName);
        WaitUtil.untilTimeCompleted(1200);

        for(int i = 0; i < pageObjectManager.getHotelPage().getHotelCard().size(); i ++)
        {
            ValidationUtil.validateTestStep("Validating hotel can be filtered by name",
                    pageObjectManager.getHotelPage().getHotelNamesText().get(i).getText(),hotelName);
        }

//- Step 8: Moving down, there should be a Pricing slider. Expand filter and validate "$price to $price per person" is displaying
        List<WebElement> priceRange = getDriver().findElements(By.xpath("//div[contains(@class,'filter-container')]//div[contains(@class,'current-range')]//span"));
        List<String> priceRangeText = new ArrayList<>();

        for(int i = 0; i < priceRange.size(); i ++)
        {
            priceRangeText.add(priceRange.get(i).getText());
        }

        String rangeText = priceRangeText.get(0) + " to " + priceRangeText.get(1) + " " + priceRangeText.get(2); //Saving price range information for next validation
        System.out.println("rangeText: " + rangeText);
        System.out.println("displaying: "+ pageObjectManager.getHotelPage().getHotelPriceRangePerPersonText().getText());

        ValidationUtil.validateTestStep("validating '$price to $price per person is displaying",
                rangeText,pageObjectManager.getHotelPage().getHotelPriceRangePerPersonText().getText());

//- Step 9: Drag the start (left)  handle and the end (right) handles (circles) to adjust the price range
        //Moving slider to lef for higer price
        WebElement slider = getDriver().findElement(By.xpath("(//ng5-slider//span)[9]"));
        Actions move = new Actions(getDriver());
        Action action = move.dragAndDropBy(slider, 30, 0).build();
        action.perform();

        ValidationUtil.validateTestStep("Validating price range was modified",
                !rangeText.equals(pageObjectManager.getHotelPage().getHotelPriceRangePerPersonText().getText()));

//- Step 10: Validate a "x clear" shows above each section where a filter has been applied.
        ValidationUtil.validateTestStep("Validating 'Clear' link is displayed on 'Hotel Search' section",
            TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getClearHotelSearchFilterButton()));

        ValidationUtil.validateTestStep("Validating 'Clear' link is displayed on 'Price' section",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getClearPriceFilterButton()));

//- Step 11: Apply filters to narrow down the search until no results display
        //Unable to validate this step

//- Step 12: Validate a "x Clear All Filters" hyperlink displays above the filters.
        ValidationUtil.validateTestStep("Validating 'Clear All filter' is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getClearAllFiltersButton()));

//- Step 13: Click the "x Clear All Filters" and validate filters are removed
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();
        WaitUtil.untilTimeCompleted(1000);

//- Step 14: Select a Hotel and continue to the next page
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

//- Step 15: Locate the Filters section on the left side of the page
        // Price
        ValidationUtil.validateTestStep("Validating Price text filter is displayed on Cars page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getPriceFilterLabel()));
        // Seats
        ValidationUtil.validateTestStep("Validating Seats text filter is displayed on Cars page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilterLabel()));
        // Bags
        ValidationUtil.validateTestStep("Validating Bags text filter is displayed on Cars page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getBagsFilterLabel()));
        // Car Type
        ValidationUtil.validateTestStep("Validating Car Type text filter is displayed on Cars page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarTypeFilterLabel()));
        // Rental Agency
        ValidationUtil.validateTestStep("Validating Rental Agency Name text filter is displayed on Cars page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getRentalAgencyFilterLabel()));
        // Car Options
        ValidationUtil.validateTestStep("Validating Car Options text filter is displayed on Cars page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarOptionsFilterLabel()));

//- Step 16: Validate each filter contains a pointing down arrow that when clicked it expands and the arrow turns up // arrows pointing up as default with filter expanded
        //Price
        ValidationUtil.validateTestStep("Validating Price chevron is pointing up ",
                pageObjectManager.getCarPage().getPriceFilterDropDown().getAttribute("style"), "transform: rotate(0deg);");
        pageObjectManager.getCarPage().getPriceFilterDropDown().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Price chevron is pointing down ",
                pageObjectManager.getCarPage().getPriceFilterDropDown().getAttribute("style"), "transform: rotate(-180deg);");

        // Seats
        ValidationUtil.validateTestStep("Validating Seats chevron is pointing up ",
                pageObjectManager.getCarPage().getSeatsFilterDropDown().getAttribute("style"), "transform: rotate(0deg);");
        pageObjectManager.getCarPage().getSeatsFilterDropDown().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Seats chevron is pointing down ",
                pageObjectManager.getCarPage().getSeatsFilterDropDown().getAttribute("style"), "transform: rotate(-180deg);");

        // Bags
        ValidationUtil.validateTestStep("Validating Bags chevron is pointing up ",
                pageObjectManager.getCarPage().getBagsFilterDropDown().getAttribute("style"), "transform: rotate(0deg);");
        pageObjectManager.getCarPage().getBagsFilterDropDown().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Bags chevron is pointing down ",
                pageObjectManager.getCarPage().getBagsFilterDropDown().getAttribute("style"), "transform: rotate(-180deg);");

        //Car Type
        ValidationUtil.validateTestStep("Validating Car Type chevron is pointing up ",
                pageObjectManager.getCarPage().getCarTypeFilterDropDown().getAttribute("style"), "transform: rotate(0deg);");
        pageObjectManager.getCarPage().getCarTypeFilterDropDown().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Car Type chevron is pointing down ",
                pageObjectManager.getCarPage().getCarTypeFilterDropDown().getAttribute("style"), "transform: rotate(-180deg);");

        //Rental Agency
        ValidationUtil.validateTestStep("Validating Rental Agency chevron is pointing up ",
                pageObjectManager.getCarPage().getRentalAgencyFilterDropDown().getAttribute("style"), "transform: rotate(0deg);");
        pageObjectManager.getCarPage().getRentalAgencyFilterDropDown().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Rental Agency chevron is pointing down ",
                pageObjectManager.getCarPage().getRentalAgencyFilterDropDown().getAttribute("style"), "transform: rotate(-180deg);");

        // Car Options
        ValidationUtil.validateTestStep("Validating Car Options chevron is pointing up ",
                pageObjectManager.getCarPage().getCarOptionsFilterDropDown().getAttribute("style"), "transform: rotate(0deg);");
        pageObjectManager.getCarPage().getCarOptionsFilterDropDown().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Car Options chevron is pointing down ",
                pageObjectManager.getCarPage().getCarOptionsFilterDropDown().getAttribute("style"), "transform: rotate(-180deg);");

//- Step 17: Click the arrows for each filter and validate check boxes are displayed when the section is expanded.
        //Price
        try {
            ValidationUtil.validateTestStep("Validating price information is suppressed when chevron is collapsed",
                   !pageObjectManager.getCarPage().getPriceFilterSlider().isDisplayed());
        }catch (Exception e){}
        pageObjectManager.getCarPage().getPriceFilterDropDown().click();
        WaitUtil.untilTimeCompleted(100);
        ValidationUtil.validateTestStep("Validating price information is not suppressed when chevron is collapsed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getPriceFilterSlider()));

        //Seats
        try {
            ValidationUtil.validateTestStep("Validating seats information is suppressed when chevron is collapsed",
                    !pageObjectManager.getCarPage().getSeatsFilter5to6Button().isDisplayed());
        }catch (Exception e){}
        pageObjectManager.getCarPage().getSeatsFilterDropDown().click();
        WaitUtil.untilTimeCompleted(100);
        ValidationUtil.validateTestStep("Validating seats information is not suppressed when chevron is collapsed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilter5to6Button()));

        //Bags
        try {
            ValidationUtil.validateTestStep("Validating bags information is suppressed when chevron is collapsed",
                    !pageObjectManager.getCarPage().getBagsFilter1to2Button().isDisplayed());
        }catch (Exception e){}
        pageObjectManager.getCarPage().getBagsFilterDropDown().click();
        WaitUtil.untilTimeCompleted(100);
        ValidationUtil.validateTestStep("Validating bags information is not suppressed when chevron is collapsed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getBagsFilter1to2Button()));

        //Car Type
        try {
            ValidationUtil.validateTestStep("Validating Car Type information is suppressed when chevron is collapsed",
                    !pageObjectManager.getCarPage().getCarTypeFilterOptionListButton().get(0).isDisplayed());
        } catch (Exception e){}
        pageObjectManager.getCarPage().getCarTypeFilterDropDown().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Car Type information is not suppressed when chevron is collapsed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarTypeFilterOptionListButton().get(0)));

        //Rental Agency
        try {
            ValidationUtil.validateTestStep("Validating Rental Agency information is suppressed when chevron is collapsed",
                    !pageObjectManager.getCarPage().getRentalAgencyFilterOptionListButton().get(0).isDisplayed());
        }catch (Exception e){}
        pageObjectManager.getCarPage().getRentalAgencyFilterDropDown().click();
        WaitUtil.untilTimeCompleted(100);
        ValidationUtil.validateTestStep("Validating Rental Agency information is not suppressed when chevron is collapsed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getRentalAgencyFilterOptionListButton().get(0)));

        //Car Options
        try {
            ValidationUtil.validateTestStep("Validating Car Options information is suppressed when chevron is collapsed",
                    !pageObjectManager.getCarPage().getCarOptionsFilterOptionListButton().get(0).isDisplayed());
        }catch (Exception e){}
        pageObjectManager.getCarPage().getCarOptionsFilterDropDown().click();
        WaitUtil.untilTimeCompleted(100);
        ValidationUtil.validateTestStep("Validating Car Options information is not suppressed when chevron is collapsed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarOptionsFilterOptionListButton().get(0)));

//- Step 18: Validate each check box displays an inventory count
        String count = pageObjectManager.getCarPage().getCarsPageCarCounterText().getText();

        pageMethodManager.getCarPageMethods().filterCarBySeats("7ORMORE");
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating inventory count changed after filter apply",
                !count.equals(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText()));

//- Step 19: Use other filters like Rental Agency and the price slider to narrow the search. The objective is to have non qualifying check boxes gray out.
        //Invalid Step

//- Step 20: Click on and off all check boxes and validate they show selected or unselected
        //4 or less checkbox. Taking the xpath for the checkbox box
//        ValidationUtil.validateTestStep("4 or less checkbox is not selected",
//                !getDriver().findElements(By.xpath("(//app-filter-list)[1]//input[@id='Seats4']")).get(0).isSelected());
//        pageObjectManager.getCarPage().getSeatsFilter4orLessButton().click();
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        ValidationUtil.validateTestStep("4 or less checkbox is selected",
//                getDriver().findElements(By.xpath("(//app-filter-list)[1]//input[@id='Seats4']")).get(0).isSelected());

        // 5 to 6  checkbox Taking the xpath for the checkbox box
        ValidationUtil.validateTestStep(" 5 to 6  checkbox is not selected",
                !getDriver().findElements(By.xpath("(//app-filter-list)[1]//input[@id='Seats5']")).get(0).isSelected());
        pageObjectManager.getCarPage().getSeatsFilter5to6Button().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep(" 5 to 6  checkbox is selected",
                getDriver().findElements(By.xpath("(//app-filter-list)[1]//input[@id='Seats5']")).get(0).isSelected());

        // 7 or more  checkbox. Taking the xpath for the checkbox box
        ValidationUtil.validateTestStep("7 or more  checkbox is selected",
                getDriver().findElements(By.xpath("(//app-filter-list)[1]//input[@id='Seats7']")).get(0).isSelected());

        //1 or 2 bags checkbox. Taking the xpath for the checkbox box
        ValidationUtil.validateTestStep(" 1 or 2 bags checkbox is not selected",
                !getDriver().findElements(By.xpath("(//app-filter-list)[2]//input[@id='Bags2']")).get(0).isSelected());
        pageObjectManager.getCarPage().getBagsFilter1to2Button().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("1 or 2 bags checkbox is selected",
                getDriver().findElements(By.xpath("(//app-filter-list)[2]//input[@id='Bags2']")).get(0).isSelected());

        //3 or 4 bags checkbox. Taking the xpath for the checkbox box
        ValidationUtil.validateTestStep("3 or 4 bags checkbox is not selected",
                !getDriver().findElements(By.xpath("(//app-filter-list)[2]//input[@id='Bags3']")).get(0).isSelected());
        pageObjectManager.getCarPage().getBagsFilter3to4Button().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("3 or 4 bags checkbox is selected",
                getDriver().findElements(By.xpath("(//app-filter-list)[2]//input[@id='Bags3']")).get(0).isSelected());

        //5 or more bags checkbox. Taking the xpath for the checkbox box
        ValidationUtil.validateTestStep("5 or more bags checkbox is not selected",
                !getDriver().findElements(By.xpath("(//app-filter-list)[2]//input[@id='Bags5']")).get(0).isSelected());
        pageObjectManager.getCarPage().getBagsFilter5orMoreButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("5 or more bags checkbox is selected",
                getDriver().findElements(By.xpath("(//app-filter-list)[2]//input[@id='Bags5']")).get(0).isSelected());

        //Car Type checkbox. Taking the xpath for the checkbox box
        boolean carTypeFlag = true;
        for (int i = 0; i < pageObjectManager.getCarPage().getCarTypeFilterOptionListButton().size(); i ++)
        {
           if (getDriver().findElements(By.xpath("(//app-filter-list)[3]//div[@class='sectionTitle']//input[@type='checkbox']")).get(i).isSelected())
           {carTypeFlag=false;}
            pageObjectManager.getCarPage().getCarTypeFilterOptionListButton().get(i).click();
           WaitUtil.untilPageLoadComplete(getDriver());
            if (!getDriver().findElements(By.xpath("(//app-filter-list)[3]//div[@class='sectionTitle']//input[@type='checkbox']")).get(i).isSelected())
            {carTypeFlag=false;}
        }
        ValidationUtil.validateTestStep("Validating Car Type checkboxes are checked after click on each",carTypeFlag);

        //Car Options checkbox. Taking the xpath for the checkbox box
        boolean carOptionsFlag = true;
        for (int i = 0; i < pageObjectManager.getCarPage().getCarOptionsFilterOptionListButton().size(); i ++)
        {
            if (getDriver().findElements(By.xpath("(//app-filter-list)[5]//div[@class='sectionTitle']//input[@type='checkbox']")).get(i).isSelected())
            {carOptionsFlag=false;}
            pageObjectManager.getCarPage().getCarOptionsFilterOptionListButton().get(i).click();
            WaitUtil.untilPageLoadComplete(getDriver());
            if (!getDriver().findElements(By.xpath("(//app-filter-list)[5]//div[@class='sectionTitle']//input[@type='checkbox']")).get(i).isSelected())
            {carOptionsFlag=false;}
        }
        ValidationUtil.validateTestStep("Validating Car Options checkboxes are checked after click on each",carOptionsFlag);

//- Step 21: Validate a "Clear" filter displays once a check box selection is made
        ValidationUtil.validateTestStep("Validating 'Clear All Filter' link is displayed, after all the filters applied",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageClearAllFiltersButton()));

//- Step 22: Apply filters to narrow down the search until no results display
        pageObjectManager.getCarPage().getCarsPageClearAllFiltersButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //selecting 7 or more seats filter and lowing price for no car results displayed
        pageObjectManager.getCarPage().getSeatsFilter7orMoreButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Moving slider to lef for higer price
        WebElement slider1 = getDriver().findElement(By.xpath("(//ng5-slider//span)[10]"));
        Actions move1 = new Actions(getDriver());
        Action action1 = move1.dragAndDropBy(slider1, -30, 0).build();
        action1.perform();

    try {
        ValidationUtil.validateTestStep("Validating no car results displayed on cars page", !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardNameText()));
    }catch (Exception e){}

//- Step 23: Click the "Clear All" filter to remove all filters
        pageObjectManager.getCarPage().getCarsPageClearAllFiltersButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 24: Now go to the Price Slider, leave the left (min) circle where it is, take the right (max) and move it to the left to approximately 1/3
        //creating price list range
        List<WebElement> carPriceRange = getDriver().findElements(By.xpath("//div[contains(@class,'filter-container')]//div[contains(@class,'current-range')]//span"));

        //Saving higer price in a variable
        double higerPrice = Double.parseDouble(carPriceRange.get(1).getText().replace("$",""));
        System.out.println("higerPrice: " +higerPrice);

        //Moving the right circle to the left for decrease the price
        WebElement slider2 = getDriver().findElement(By.xpath("(//ng5-slider//span)[10]"));
        Actions move2 = new Actions(getDriver());
        Action action2 = move2.dragAndDropBy(slider2, -30, 0).build();
        action2.perform();

        //Validating prices decreases
        ValidationUtil.validateTestStep("Validating price decrease",
                higerPrice > Double.parseDouble(carPriceRange.get(1).getText().replace("$","") ));

        List<Double> cardCarPrice = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getCarPage().getCarCardNameText().size(); i ++)
        {
            cardCarPrice.add(Double.parseDouble(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i).getText().replace("$","")));
        }

        boolean rangeFlag = true;
        for (int i = 0; i < cardCarPrice.size(); i ++)
        {
            if (Double.parseDouble(carPriceRange.get(0).getText().replace("$","")) >= cardCarPrice.get(i) && cardCarPrice.get(i) <= Double.parseDouble(carPriceRange.get(1).getText().replace("$",""))  )
           {
               rangeFlag = false;
           }
        }
        ValidationUtil.validateTestStep("Validating Car prices list being into the price range specified",rangeFlag);


//- Step 25: Now take the  left (min) circle and move it to the middle, then take the right (max) and move it to the left to approximately half the slider, leaving a space between left and right circle
        double lowerPrice =  Double.parseDouble(carPriceRange.get(0).getText().replace("$",""));
        System.out.println("lowerPrice: " +lowerPrice);

        WebElement slider3 = getDriver().findElement(By.xpath("(//ng5-slider//span)[9]"));
        Actions move3 = new Actions(getDriver());
        Action action3 = move3.dragAndDropBy(slider3, 50, 0).build();
        action3.perform();

        //Validating prices decreases
        ValidationUtil.validateTestStep("right price range displayed",
                lowerPrice < Double.parseDouble(carPriceRange.get(0).getText().replace("$","")));

        List<Double> cardCarPrice1 = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getCarPage().getCarCardNameText().size(); i ++)
        {
            cardCarPrice1.add(Double.parseDouble(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i).getText().replace("$","")));
        }

        for (int i = 0; i < cardCarPrice.size(); i ++)
        {
            if (Double.parseDouble(carPriceRange.get(0).getText().replace("$","")) >= cardCarPrice1.get(i) && cardCarPrice.get(i) <= Double.parseDouble(carPriceRange.get(1).getText().replace("$",""))  )
            {
                rangeFlag = false;
            }
        }
        ValidationUtil.validateTestStep("Validating Car prices list being into the price range specified",rangeFlag);

//- Step 26: Now leave the  left (min) circle where it is and take the right (max) and move it all the way to the right.
        //Moving the right circle to the left for decrease the price
        WebElement slider4 = getDriver().findElement(By.xpath("(//ng5-slider//span)[10]"));
        Actions move4 = new Actions(getDriver());
        Action action4 = move4.dragAndDropBy(slider4, 100, 0).build();
        action4.perform();

        //Validating prices decreases
        ValidationUtil.validateTestStep("Validating right price range displayed",
                higerPrice == Double.parseDouble(carPriceRange.get(1).getText().replace("$","")));

        List<Double> cardCarPrice2 = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getCarPage().getCarCardNameText().size(); i ++)
        {
            cardCarPrice2.add(Double.parseDouble(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i).getText().replace("$","")));
        }

        for (int i = 0; i < cardCarPrice.size(); i ++)
        {
            if (Double.parseDouble(carPriceRange.get(0).getText().replace("$","")) >= cardCarPrice2.get(i) && cardCarPrice.get(i) <= Double.parseDouble(carPriceRange.get(1).getText().replace("$",""))  )
            {
                rangeFlag = false;
            }
        }
        ValidationUtil.validateTestStep("Validating Car prices list being into the price range specified",rangeFlag);

    }
}

//******************************************************************************************
//*************************Extra XPATH in GoldFinger **************************************
//*****************************************************************************************
//public final String xpath_HotelFilterContainerPanel = "//div[contains(@class,'filter-container')]";//goldfinger
//    @FindBy (xpath=xpath_HotelFilterContainerPanel)
//    private WebElement pnl_HotelFilterContainer;
//
//public final String xpath_HotelPriceRangePerPersonText = "//div[contains(@class,'filter-container')]//div[contains(@class,'current-range')]";//goldfinger
//    @FindBy (xpath=xpath_HotelPriceRangePerPersonText)
//    private WebElement txt_HotelPriceRangePerPerson;

//public final String xpath_ClearHotelSearchFilterButton = "//div[@class='filter-name']//button[contains(text(),'Clear')]";//goldfinger
//    @FindBy (xpath=xpath_ClearHotelSearchFilterButton)
//    private WebElement btn_ClearHotelSearchFilter;

//    public WebElement geHotelFilterContainerPanel(){return pnl_HotelFilterContainer;}
//public WebElement getHotelPriceRangePerPersonText(){return txt_HotelPriceRangePerPerson;}
//public WebElement getClearHotelSearchFilterButton(){return btn_ClearHotelSearchFilter;}