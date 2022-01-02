package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//TODO: [IN:25595]	GoldFinger R1: CP: BP: F+H: Availability Page. Same picture displayed twice for The D Las Vegas hotel.
//Test Case ID: TC349828
//Description: Task 28185: TC349828 - 002 - CP - Flight + Hotel + Car - Availability Page - Validate Display components and functionality
//Created By: Gabriela
//Created On: 4-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC349828 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath","RoundTrip", "DomesticDomestic","FlightHotelCar", "Outside21Days", "Adult", "Guest", "HotelsUI", "CarsUI"})
    public void CP_Flight_Hotel_Car_Availability_Page_Validate_Display_components_and_functionality (@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349828 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Vacation";
        final String TRIP_TYPE              = "Flight+Hotel+Car";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "133";
        final String ARR_DATE               = "135";
        final String ADULT                  = "2";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        final String HOTEL_ROOM             = "1 Room";
        final String DRIVER_AGE             = "25+";

        //Flight Availability Constant Values
        final String CAR_URL                = "/book/options/cars";

//- Step 1: Access test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Start booking a Vacation [Flight + Hotel + Car], 2 ADT, outside of 90 days, with destination to LAS or MCO
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTEL_ROOM);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Under the Hotel selection section, next to the filter, validate there is a hotel count formatted "# hotels".
        ValidationUtil.validateTestStep("Validating hotels counter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCounterText()));

        int hotelCounter = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Hotels", "").trim());

//- Step 4: Use any of the filters to narrow the search and validate the value changes to only the amount of hotels meeting the filters applied.
        //Add filter
        pageObjectManager.getHotelPage().get3StartsCheckBox().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        int filteredHoteResultCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Hotels", "").trim());
        ValidationUtil.validateTestStep("The filtered hotel result is less than the original search result: pass", hotelCounter >= filteredHoteResultCount);
        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();

//- Step 5: Locate the Sort by section and validate Recommended is the default selected state.
        ValidationUtil.validateTestStep("The recommended hotel is pre-selected", pageObjectManager.getHotelPage().getRecommendedButton().getAttribute("class").contains("active"));

//- Step 6: Click on Star
        TestUtil.mouseClickOnElement(getDriver(), pageObjectManager.getHotelPage().getStarsButton());

        int previousStarRating = 3;
        int currentStarRating;

        for (WebElement stars : pageObjectManager.getHotelPage().getHotelStarRatingImage()) {
            //get the number of star images with the src "Full"
            currentStarRating = stars.findElements(By.xpath("//img[@src='./assets/img/rating/star_full.svg']")).size();
            //validate the current star is the same or more than the previous one
            ValidationUtil.validateTestStep("The previous hotel has lower star rating than the current hotel", previousStarRating <= currentStarRating);
            //make previous rating current star rating for next iteration
            previousStarRating = currentStarRating;
        }

//- Step 7: Click Price and validate the order goes from LOW to HIGH with first click and it reverses on second click
        TestUtil.mouseClickOnElement(getDriver(), pageObjectManager.getHotelPage().getPriceButton());
        WaitUtil.untilPageLoadComplete(getDriver());

        double previousPrice = 0.00;
        double currentPrice;

        for (WebElement price : pageObjectManager.getHotelPage().getHotelCardPriceLink()) {
            currentPrice = Double.parseDouble(price.getText().replace("$", "").trim());

            ValidationUtil.validateTestStep("The current price is more than the last hotel price",
                    currentPrice > previousPrice);

            previousPrice = currentPrice;
        }


        TestUtil.mouseClickOnElement(getDriver(), pageObjectManager.getHotelPage().getPriceButton());

        previousPrice = 10000.00;

        for (WebElement price : pageObjectManager.getHotelPage().getHotelCardPriceLink()) {
            currentPrice = Double.parseDouble(price.getText().replace("$", "").trim());

            ValidationUtil.validateTestStep("The current price is more than the last hotel price",
                    currentPrice < previousPrice);

            previousPrice = currentPrice;
        }

//- Step 8: Moving down, validate there is a list of 20 hotel tiles displaying.
        if (hotelCounter >= 20) {
            ValidationUtil.validateTestStep("There is only 20 defaulted hotels displayed on the hotel page",
                    pageObjectManager.getHotelPage().getHotelNamesText().size() >= 20);

            ValidationUtil.validateTestStep("There is \"Show More\" button on the hotel name for more than 20 hotels",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getShowMoreButton()));
        } else {
            ValidationUtil.validateTestStep("There is no \"Show More\" button on the hotel name",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getShowMoreButton()));
        }

//- Step 9: Return to the Sort by section and click on "Recommended"
        TestUtil.mouseClickOnElement(getDriver(), pageObjectManager.getHotelPage().getRecommendedButton());

//- Step 10: Validate the first tile on the list is:
        //Invalid Step

//- Step 11: Validate each tile contains:
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelCardImageImage().size(); i++) {
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardImageImage().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel image is not displayed for card number " + (i + 1), false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel name text is not displayed for card number " + (i + 1), false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelAddressText().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel Area is not displayed for card number " + (i + 1), false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelMapButton().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel Map hyperlink to Google maps is not displayed for card number " + (i + 1), false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelStarRatingImage().get(i)) == false)
                ValidationUtil.validateTestStep("TheHotel Star rating is not displayed for card number " + (i + 1), false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel Pricing in USD per person is not displayed for card number " + (i + 1), false);
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPageUpliftText().get(i)) == false)
                ValidationUtil.validateTestStep("The Hotel Uplift promotion when applicable is not displayed for card number " + (i + 1), false);
        }
//- Step 12: Locate and click the left and right arrows on the Hotel Picture
        String HotelImage1, HotelImage2;
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelCardImageImage().size(); i++) {
            if(pageObjectManager.getHotelPage().getHotelCardImageImage().get(i).isDisplayed()){

                pageObjectManager.getHotelPage().getHotelImageLeftSlideButton().get(i).click();
                WaitUtil.untilPageLoadComplete(getDriver());
                WaitUtil.untilTimeCompleted(2000);

                HotelImage1 = pageObjectManager.getHotelPage().getHotelCardSlideImage().get(0).getAttribute("src");
                pageObjectManager.getHotelPage().getHotelImageRightSlideButton().get(i).click();

                WaitUtil.untilTimeCompleted(2000);
                HotelImage2 = pageObjectManager.getHotelPage().getHotelCardSlideImage().get(0).getAttribute("src");

                //TODO: [IN:25595]	GoldFinger R1: CP: BP: F+H: Availability Page. Same picture displayed twice for The D Las Vegas hotel.
                ValidationUtil.validateTestStep("User Validate the images are different", !HotelImage1.equals(HotelImage2));
            }

        }

//- Step 13: Scroll down to the end of the page and click SHOW MORE.
        int tileSize = pageObjectManager.getHotelPage().getHotelCard().size();
        if (hotelCounter > 20)
        {
            pageObjectManager.getHotelPage().getShowMoreButton().click();
            WaitUtil.untilTimeCompleted(8000);
            ValidationUtil.validateTestStep("Validating more tiles displayed after click on Show More link",
                    pageObjectManager.getHotelPage().getHotelCard().size()>tileSize);
        }

//- Step 14: Validate there is NOT a CONTINUE WITHOUT A HOTEL button on the page
        ValidationUtil.validateTestStep("Validating there is not 'CONTINUE WITHOUT HOTEL BUTTON' displayed on the page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getContinueWithoutHotelButton()));

//- Step 15: Repeat the process until the SHOW MORE no longer shows
        int time1 = hotelCounter/20;
        for (int i =0; i < time1; i ++)
        {
            try {
                    pageObjectManager.getHotelPage().getShowMoreButton().click();
            }catch (Exception e){}
        }

        ValidationUtil.validateTestStep("'SHOW MORE' button is no longer displayed on the page",
                !TestUtil.verifyElementDisplayed( pageObjectManager.getHotelPage().getShowMoreButton()));

//- Step 16: Select the Hotel, then reach the Car Page
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM","NA");

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify Car Page loaded",
                getDriver().getCurrentUrl(),CAR_URL);

//- Step 17: Once on the Car Page, next to the filter, validate there is a car count formatted "# cars".
        ValidationUtil.validateTestStep("Validating Car Counter is displayed on the car page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCounterText()));

//- Step 18: Use any of the filters to narrow the search and validate the value changes to only the amount of cars meeting the filters applied.
        //Saving initial car amount displayed
        String carCount = pageObjectManager.getCarPage().getCarsPageCarCounterText().getText();

        //Applying filters
        pageObjectManager.getCarPage().getSeatsFilter7orMoreButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating filters applied", !pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().equals(carCount));
        pageObjectManager.getCarPage().getCarsPageClearAllFiltersButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 19: Locate the Sort by section and validate Recommended is the default selected state.
        ValidationUtil.validateTestStep("The recommended Car is pre-selected",getDriver().findElements(By.xpath("//div[@data-toggle='buttons']//label[contains(text(),'Recommended')]")).get(0).getAttribute("class").contains("active"));

//- Step 20: Click on Seat
       JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortBySeatsButton());
        WaitUtil.untilPageLoadComplete(getDriver());

        List<Integer> seat = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i++)
        {
            seat.add(Integer.parseInt(pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().get(i).getText().replace(" Seats","")));
        }

        for (int i = 0; i < seat.size(); i ++)
        {
            if (i==seat.size()-1) {break;}
            ValidationUtil.validateTestStep("Validate the order goes from ascending to descending order",
                    seat.get(i) <= seat.get(i+1));
        }

//- Step 21: Click on Seat one more time and validate the order now reverses (Largest to Smallest number of seats)
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortBySeatsButton());
        WaitUtil.untilPageLoadComplete(getDriver());

        List<Integer> seat1 = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i++)
        {
            seat1.add(Integer.parseInt(pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().get(i).getText().replace(" Seats","")));
        }

        for (int i = 0; i < seat1.size(); i ++)
        {
            if (i==seat1.size()-1){break;}
            ValidationUtil.validateTestStep("Validate now is displaying Largest to Smallest number of seats",
                    seat1.get(i) >= seat1.get(i+1));
        }

//- Step 22: Click Price and validate the order goes from LOW to HIGH with first click and it reverses on second click
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortByPriceButton());
        WaitUtil.untilPageLoadComplete(getDriver());
        List<Double> price = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i++)
        {
            price.add(Double.parseDouble(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i).getText().replace("$","")));
        }

        for (int i = 0; i < price.size(); i ++)
        {
            if (i==price.size()-1){break;}
            ValidationUtil.validateTestStep("Validating the price order goes from LOW to HIGH with first click",
                    price.get(i) <= price.get(i+1));
        }

        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortByPriceButton());
        WaitUtil.untilPageLoadComplete(getDriver());
        List<Double> price1 = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i++)
        {
            price1.add(Double.parseDouble(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i).getText().replace("$","")));
        }

        for (int i = 0; i < price1.size(); i ++)
        {
            if (i==price1.size()-1){break;}
            ValidationUtil.validateTestStep("Validating the price order goes from LOW to HIGH with first click",
                    price1.get(i) >= price1.get(i+1));
        }

//- Step 23: Click on Car Type and validate the order sorts by car type.
//- Step 24: Moving down, validate there is a list of 20 car tiles displaying.
        int count = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace(" Cars",""));
        if (count>20){
        ValidationUtil.validateTestStep("Validating there displaying 20 car tiles when apply",
                pageObjectManager.getCarPage().getCarsPageCarsPanel().size()==20);}

//- Step 25: Return to the Sort by section and click on "Recommended"
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortByRecommendedButton());
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 26: Validate the first tile on the list is:
        //Invalid Step

//- Step 27: Validate each tile contains:
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i++)
        {
            ValidationUtil.validateTestStep("Validating 'Car Image' section is displaying on Top",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRentalImg().get(i)));

            ValidationUtil.validateTestStep("Validating 'Company' section is displaying on Top",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().get(i)));

            ValidationUtil.validateTestStep("Validating 'Type and Name of Car' section is displaying on Top",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarModelText().get(i)));

            ValidationUtil.validateTestStep("Validating 'More Info' section is displaying on Top",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(i)));

            ValidationUtil.validateTestStep("Validating 'Pricing in USD' section is displaying on Top",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i)));

            ValidationUtil.validateTestStep("Validating 'Logo' section is displaying on Top",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().get(i)));
        }


//- Step 28: Scroll down to the end of the page and locate the SHOW MORE link.
        if (count>20){
        ValidationUtil.validateTestStep("Validating 'Show more link is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageShowMoreButton()));}

//- Step 29: Validate there is NOT a CONTINUE WITHOUT A CAR button on the page
        ValidationUtil.validateTestStep("Validating there is not 'CONTINUE WITHOUT CAR' button when F+H+C",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton()));

//- Step 30: Click the SHOW MORE link
        int time = count/20;
        for (int i = 0; i < time; i ++)
        {
            JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getCarPageShowMoreButton());
            WaitUtil.untilTimeCompleted(5000);

            ValidationUtil.validateTestStep("Validating 20 more tiles added after click on 'SHOW MORE' button",
                     pageObjectManager.getCarPage().getCarsPageCarsPanel().size() >= count && pageObjectManager.getCarPage().getCarsPageCarsPanel().size() <= count + 20);
            count = count + 20;
        }

//- Step 31: Repeat the process until the SHOW MORE no longer shows
        ValidationUtil.validateTestStep("Validating there is no 'SHOW MORE' button",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageShowMoreButton()));
    }
}