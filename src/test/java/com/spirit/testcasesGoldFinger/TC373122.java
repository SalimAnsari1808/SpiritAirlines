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

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373122
//Description: Task 27134: TC373122 - 010 - CP - Flight Only - Car Page - Validate the Seats Filter behavior
//Created By:  Anthony Cardona
//Created On:  25-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373122 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "BookIt", "NoBags", "NoSeats", "Cars", "CarsUI"})
    public void Flight_Only_Car_Page_Validate_the_Seats_Filter_behavior(@Optional("NA")String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373122 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";


        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";
        final String FARE_TYPE          = "Standard";

//Step 1: Access test environment
        openBrowser(platform);

//Step 2: Start booking a flight only RT DOM for 1 ADT and get to the Car Page from the Options Page
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Hotel Selection
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep" , "NonStop");
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret" , "NonStop");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page method
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page method
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 3: Locate the Seats filter and validate it contains a pointing down arrow. Click on the arrow
        pageObjectManager.getCarPage().getViewAllCarsButton().click();
        WaitUtil.untilTimeCompleted(2000);
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Seats chevron is pointing down ",
                pageObjectManager.getCarPage().getSeatsFilterDropDown().getAttribute("style"), "transform: rotate(0deg);");

//Step 4: Validate check boxes are displayed when the section is expanded.
//        boolean seatFilterOption4orLessIsDisplayed = TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilter4orLessButton());
        boolean seatFilterOptionI5to6Displayed = TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilter5to6Button());
        boolean seatFilterOption7orMoreDisplayed =  TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilter7orMoreButton());

        ValidationUtil.validateTestStep("The Seat Option \"Seats\" is correctly displayed" , seatFilterOptionI5to6Displayed || seatFilterOption7orMoreDisplayed);

//Step 5: Validate check boxes are displayed when the section is expanded.

//        if(seatFilterOption4orLessIsDisplayed) selectSeatFilter(pageObjectManager.getCarPage().getSeatsFilter4orLessButton());
        if(seatFilterOptionI5to6Displayed) selectSeatFilter(pageObjectManager.getCarPage().getSeatsFilter5to6Button());
        if(seatFilterOption7orMoreDisplayed) selectSeatFilter(pageObjectManager.getCarPage().getSeatsFilter7orMoreButton());


//Step 6: Use other filters like Rental Agency and the price slider to narrow the search.
//The objective is to have non qualifying check boxes gray out.
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageClearAllFiltersButton()))
            pageObjectManager.getCarPage().getCarsPageClearAllFiltersButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        WebElement slider1 = getDriver().findElement(By.xpath("(//ng5-slider//span)[10]"));
        Actions move1 = new Actions(getDriver());
        Action action1 = move1.dragAndDropBy(slider1, -20, 0).build();
        action1.perform();

        WaitUtil.untilPageLoadComplete(getDriver());

        int fromPrice =   Integer.parseInt(pageObjectManager.getCarPage().getPriceFilterFromPriceText().getText().replace("$",""));
        int toPrice =   Integer.parseInt(pageObjectManager.getCarPage().getPriceFilterToPriceText().getText().replace("$",""));

        for (WebElement carPriceOnCard : pageObjectManager.getCarPage().getCarsPageRentalPriceText())
        {
            String carPriceStr = carPriceOnCard.getText().replace("$","");
            int carPriceInt = Integer.parseInt(carPriceStr.substring(0 , carPriceStr.indexOf(".")));

            ValidationUtil.validateTestStep("The car Price is within the range" ,
                    fromPrice <= carPriceInt  && toPrice >= carPriceInt);
        }

//Step 7: Click on and off all check boxes and validate they show selected or unselected || All check boxes can be clicked on and off to allow selection or remove selection
//Step 8: Validate a "Clear" filter displays once a check box selection is made

//        checkFilterCheckBoxesAndValidateSelected(pageObjectManager.getCarPage().xpath_SeatsFilter4orLess);
        checkFilterCheckBoxesAndValidateSelected(pageObjectManager.getCarPage().xpath_SeatsFilter5to6);
        checkFilterCheckBoxesAndValidateSelected(pageObjectManager.getCarPage().xpath_SeatsFilter7orMore);
        checkFilterCheckBoxesAndValidateSelected(pageObjectManager.getCarPage().xpath_BagsFilter1to2);
        checkFilterCheckBoxesAndValidateSelected(pageObjectManager.getCarPage().xpath_BagsFilter3to4);
        checkFilterCheckBoxesAndValidateSelected(pageObjectManager.getCarPage().xpath_BagsFilter5orMore);
        checkFilterCheckBoxesAndValidateSelected(pageObjectManager.getCarPage().getCarTypeFilterOptionListButton());
        checkFilterCheckBoxesAndValidateSelected(pageObjectManager.getCarPage().getCarOptionsFilterOptionListButton());


        WaitUtil.untilPageLoadComplete(getDriver());
//Step 9: Apply filters to narrow down the search until no results display
        WebElement slider2 = getDriver().findElement(By.xpath("(//ng5-slider//span)[9]"));
        Actions move2 = new Actions(getDriver());
        Action action2 = move2.dragAndDropBy(slider2, 180, 0).build();
        action2.perform();

        WaitUtil.untilPageLoadComplete(getDriver());

        if(Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Cars" , "").trim()) == 0)
        {

            ValidationUtil.validateTestStep("The image and text for \"No Results Were Found\" is correct" , getDriver().findElement(By.xpath("//img[@alt='No results were found.']/..")).getText(),
                    "NO RESULTS WERE FOUND.\n Please adjust your filters and try again.");
        }
        else
        {
            pageObjectManager.getCarPage().getSeatsFilter5to6Button().click();
            WaitUtil.untilPageLoadComplete(getDriver());
            pageObjectManager.getCarPage().getCarTypeFilterOptionListButton().get(0).click();
            WaitUtil.untilPageLoadComplete(getDriver());
        }

        int carResultCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Cars" , "").trim());
        ValidationUtil.validateTestStep("The Car count result is 0 on the car section" , carResultCount == 0);


    }

    public void selectSeatFilter(WebElement seatFilter)
    {
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilterClearButton()))
        {
            pageObjectManager.getCarPage().getSeatsFilterClearButton().click();
            WaitUtil.untilTimeCompleted(1000);
            WaitUtil.untilPageLoadComplete(getDriver());
        }

        seatFilter.click();

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilPageLoadComplete(getDriver());

        int carResultCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Cars" , "").trim());
        ValidationUtil.validateTestStep("The Car count result is displayed onto the car section" , carResultCount > 0);
    }

    public void checkFilterCheckBoxesAndValidateSelected(String xpathOfCheckBox)
    {
        int initialfilterClearButtonCount = getDriver().findElements(By.xpath("//button[contains(text(),'Clear')]")).size();
        int finalfilterclearbuttonCount;

        if (TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath(xpathOfCheckBox))))
        {
            WebElement checkBox = getDriver().findElement(By.xpath(xpathOfCheckBox));

            //click on checkBox
            checkBox.click();

            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("The Check box is selected" , getDriver().findElement(By.xpath(xpathOfCheckBox + "/preceding::input[1]")).isSelected());
            finalfilterclearbuttonCount = getDriver().findElements(By.xpath("//button[contains(text(),'Clear')]")).size();
            ValidationUtil.validateTestStep("A new clear Button is displayed after selecting filter" , finalfilterclearbuttonCount ==  initialfilterClearButtonCount + 1);

            //click on checkBox
            checkBox.click();
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("The Check box is not selected" , !getDriver().findElement(By.xpath(xpathOfCheckBox + "/preceding::input[1]")).isSelected());
            finalfilterclearbuttonCount = getDriver().findElements(By.xpath("//button[contains(text(),'Clear')]")).size();
            ValidationUtil.validateTestStep("A new clear Button is displayed after selecting filter" , finalfilterclearbuttonCount ==  initialfilterClearButtonCount);
        }
    }

    public void checkFilterCheckBoxesAndValidateSelected(List<WebElement> filterCheckBox)
    {
        int initialfilterClearButtonCount = getDriver().findElements(By.xpath("//button[contains(text(),'Clear')]")).size();
        int finalfilterclearbuttonCount;

        for (WebElement filter: filterCheckBox) {

            //click on checkBox
            filter.click();
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("The Check box is selected", filter.findElement(By.xpath("./preceding::input[1]")).isSelected());
            finalfilterclearbuttonCount = getDriver().findElements(By.xpath("//button[contains(text(),'Clear')]")).size();
            ValidationUtil.validateTestStep("A new clear Button is displayed after selecting filter" , finalfilterclearbuttonCount ==  initialfilterClearButtonCount + 1);

            //click on checkBox
            filter.click();
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("The Check box is not selected", !filter.findElement(By.xpath("./preceding::input[1]")).isSelected());
            finalfilterclearbuttonCount = getDriver().findElements(By.xpath("//button[contains(text(),'Clear')]")).size();
            ValidationUtil.validateTestStep("A new clear Button is displayed after selecting filter" , finalfilterclearbuttonCount ==  initialfilterClearButtonCount);
        }
    }
}