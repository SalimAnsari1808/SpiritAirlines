package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC349821
//Description : TC349821 - 003 - CP - Flight + Hotel - Availability Page - Validate Display components and functionality
// Created By : Sunny Sok
//Created On  : 29-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 5-Dec-2019
//**********************************************************************************************
public class TC349821 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic","FlightHotel", "Outside21Days", "Adult", "Guest","HotelsUI"})
    public void CP_Flight_Hotel_Availability_Page_Validate_Display_components_and_functionality(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349821 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "99";
        final String ARR_DATE           = "100";
        final String ADULTS             = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Constant Values
        final String FLIGHTANDHOTELS_URL = "/flights-hotels";

        //Step 1: Access test environment
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Steep 2:
        //Start booking a Vacation [Flight + Hotel], 2 ADT, outside of 90 days with destination to LAS or MCO
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /******************************************************************************
         **************Validation  Flight + Hotel - Availability Page******************
         ******************************************************************************/
        ValidationUtil.validateTestStep("User navigated to Flights + Hotels page", getDriver().getCurrentUrl(), FLIGHTANDHOTELS_URL);

        JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getHotelPage().getChooseYourHotelHeaderText());

        //Step 3: Under the Hotel selection section, next to the filter, validate there is a hotel count formatted "# hotels".
        int hotelResultCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Hotels" , "").trim());
        ValidationUtil.validateTestStep("The hotel count is displayed onto the car section" , hotelResultCount > 0);

        //Step 4: Use any of the filters to narrow the search and validate the value changes to only the amount of hotels meeting the filters applied.
        //Add filter
        pageObjectManager.getHotelPage().get3StartsCheckBox().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        int filteredHoteResultCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Hotels" , "").trim());
        ValidationUtil.validateTestStep("The filtered hotel result is less than the original search result: pass" , hotelResultCount >= filteredHoteResultCount);
        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();

        //Step 5: Locate the Sort by section and validate Recommended is the default selected state.
        ValidationUtil.validateTestStep("The recommended hotel is pre-selected" , pageObjectManager.getHotelPage().getRecommendedButton().getAttribute("class").contains("active"));

        /*
        VALIDATION OF SORT BY RECOMMENDED CANNOT BE DONE BY AUTOMATION
         */


        //Step 6: Click on Star
        TestUtil.mouseClickOnElement(getDriver() , pageObjectManager.getHotelPage().getStarsButton());

        int previousStarRating = 3;
        int currentStarRating ;

        for (WebElement stars : pageObjectManager.getHotelPage().getHotelStarRatingImage())
        {
            //get the number of star images with the src "Full"
            currentStarRating = stars.findElements(By.xpath("//img[@src='./assets/img/rating/star_full.svg']")).size();
            //validate the current star is the same or more than the previous one
            ValidationUtil.validateTestStep("The previous hotel has lower star rating than the current hotel" , previousStarRating <= currentStarRating);
            //make previous rating current star rating for next iteration
            previousStarRating = currentStarRating;
        }

        //Step 7: Click Price and validate the order goes from LOW to HIGH with first click and it reverses on second click
        TestUtil.mouseClickOnElement(getDriver() , pageObjectManager.getHotelPage().getPriceButton());
        WaitUtil.untilPageLoadComplete(getDriver());

        double previousPrice = 0.00;
        double currentPrice;

        for (WebElement price : pageObjectManager.getHotelPage().getHotelCardPriceLink())
        {
            currentPrice = Double.parseDouble(price.getText().replace("$","").trim());

            ValidationUtil.validateTestStep("The current price is more than the last hotel price" ,
                    currentPrice > previousPrice);

            previousPrice=currentPrice;
        }


        TestUtil.mouseClickOnElement(getDriver() , pageObjectManager.getHotelPage().getPriceButton());

        previousPrice = 10000.00;

        for (WebElement price : pageObjectManager.getHotelPage().getHotelCardPriceLink())
        {
            currentPrice = Double.parseDouble(price.getText().replace("$","").trim());

            ValidationUtil.validateTestStep("The current price is more than the last hotel price" ,
                    currentPrice < previousPrice);

            previousPrice=currentPrice;
        }

        //Step 8: Moving down, validate there is a list of 20 hotel tiles displaying.
        if (hotelResultCount >= 20) {
            ValidationUtil.validateTestStep("There is only 20 defaulted hotels displayed on the hotel page",
                    pageObjectManager.getHotelPage().getHotelNamesText().size() == 20);

            ValidationUtil.validateTestStep("There is \"Show More\" button on the hotel name for more than 20 hotels",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getShowMoreButton()));
        }

        else
        {
            ValidationUtil.validateTestStep("There is no \"Show More\" button on the hotel name",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getShowMoreButton()));
        }


        //Step 9: Return to the Sort by section and click on "Recommended"
        TestUtil.mouseClickOnElement(getDriver() , pageObjectManager.getHotelPage().getRecommendedButton());

        //Step 10
        /*
        Out of scope for DEAL OF THE DAY.
         */

        //Step 11: Validate hotel tile content
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

        //Step 12: Scroll down to the end of the page and click SHOW MORE.
        //Step 13: Repeat the process until the SHOW MORE no longer shows

        int hotelsDisplayed = 20;
        Date startTime = TestUtil.convertStringToDate( TestUtil.getStringDateFormat("0", "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");

        if (hotelResultCount >= 20) {

            while(hotelResultCount > hotelsDisplayed)
            {
                ValidationUtil.validateTestStep("There are " + pageObjectManager.getHotelPage().getHotelNamesText().size() + " displayed",
                        pageObjectManager.getHotelPage().getHotelNamesText().size() == hotelsDisplayed);

                pageObjectManager.getHotelPage().getShowMoreButton().click();
                WaitUtil.untilPageLoadComplete(getDriver());
                WaitUtil.untilTimeCompleted(1000);
                hotelsDisplayed = hotelsDisplayed + 20;


                Date endTime = TestUtil.convertStringToDate(TestUtil.getStringDateFormat("0", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");

                if (endTime.getTime() - startTime.getTime() > 7 * 60 * 1000)
                {
                    ValidationUtil.validateTestStep("test execution took longer than 7 minutes and did not recieve email", false);
                }
            }
        }

        //Step 14: Validate there is a CONTINUE WITHOUT A HOTEL button
        ValidationUtil.validateTestStep("There is no \"Show More\" button on the hotel name",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getShowMoreButton()));

    }
}