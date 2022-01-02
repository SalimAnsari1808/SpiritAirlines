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

import java.util.Date;


//**********************************************************************************************
//Test Case ID: TC373894
//Description: Task 27931: TC373894 - 003 - CP - Booking Path - Hub Packaging - View All Hotels -  1 ADT 1 CHILD
// Created By: Anthony Cardona
//Created On: 25-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373894 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "BookIt", "NonStop", "NoBags", "NoSeats" , "Cars" , "CarsUI"})
    public void Booking_Path_Hub_Packaging_View_All_Hotels_1_ADT_1_CHILD(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373894  under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

//- Step 1: Book a flight | 3 months out | Multi-pax DOM - INT
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Book a flight..... 3 months out | 1 ADT 1 CHILD , Step 3: Select city pair, dates, and passenger information
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//Step 4: Select flights
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//Step 5: If bundle pop up shows, click on book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Step 6: Fill out customer info
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 7: Click on Continue without bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//Step 8: Click on I dont need bags
//Step 9: click on Continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 10: Under the Hotels carousel, click the "View All Hotels" hyperlink.
        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();

        final String HOTEL_PAGE         = "/book/options/hotels";
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(HOTEL_PAGE));

//Step 11: Next to the filter, validate there is a hotel count formatted "# hotels".
        int hotelResultCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Hotels" , "").trim());
        ValidationUtil.validateTestStep("The hotel count is displayed onto the car section" , hotelResultCount > 0);

//Step 12: Use any of the filters to narrow the search and validate the value changes to only the amount of hotels meeting the filters applied.
        //Add filter
        pageObjectManager.getHotelPage().get3StartsCheckBox().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        int filteredHotelResultCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Hotels" , "").trim());
        ValidationUtil.validateTestStep("The filtered hotel result is less than the original search result: pass" , hotelResultCount >= filteredHotelResultCount);
        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();

//Step 13: Locate the Sort by section and validate Recommended is the default selected state.
        ValidationUtil.validateTestStep("The recommended hotel is pre-selected" , pageObjectManager.getHotelPage().getRecommendedButton().getAttribute("class").contains("active"));

        /*
        VALIDATION OF SORT BY RECOMMENDED CANNOT BE DONE BY AUTOMATION
         */

//Step 14: Click on Star
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

//Step 15: Click Price and validate the order goes from LOW to HIGH with first click and it reverses on second click
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

//Step 16: Moving down, validate there is a list of 20 hotel tiles displaying.
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


//Step 17: Return to the Sort by section and click on "Recommended"
        TestUtil.mouseClickOnElement(getDriver() , pageObjectManager.getHotelPage().getRecommendedButton());

//Step 18
        /*
        Out of scope for DEAL OF THE DAY.
         */

//Step 19: Validate hotel tile content
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

//Step 20: Scroll down to the end of the page and click SHOW MORE.
//Step 21: Repeat the process until the SHOW MORE no longer shows

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

//Step 22: Validate there is a CONTINUE WITHOUT A HOTEL button
        ValidationUtil.validateTestStep("There is no \"Show More\" button on the hotel name",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getShowMoreButton()));

    }
}