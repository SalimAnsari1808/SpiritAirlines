package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91159
//Description: Hub-Options_CP_BP_Hotel Page Wireframe
//Created By: Anthony Cardona
//Created On: 25-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 26-Jul-2019
//**********************************************************************************************

public class TC91159 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "MandatoryFields" , "NoBags" , "NoSeats" ,"OptionalUI", "HotelsUI"})
    public void Hub_Options_CP_BP_Hotel_Page_Wireframe(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91159 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "60";
        final String ARR_DATE           = "65";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";


        //Option Page Constant Values
        final String RGB_BLUE           = "0, 115, 230";
        final String RGB_WHITE          = "255, 255, 255";
        final String OPTION_PAGE_URL    = "spirit.com/book/options";

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0));
        //adult DOB more than 18 years old and less than 21 years old list box
        String dateOfBirth = TestUtil.getStringDateFormat("-9000", "MM/dd/yyyy");
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(dateOfBirth);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/

        ValidationUtil.validateTestStep("The user lands on the options page successfully",
                getDriver().getCurrentUrl(),OPTION_PAGE_URL);

//Step 1: "Hotels for Less" verbiage displayed above hotel carousel
        ValidationUtil.validateTestStep("\"Hotels for Less\" is displayed on the options page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText()));

//Step 2: Click on the "VIEW ALL HOTELS"
        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();

//Step 3: "Where Are You Staying?" page displays
        ValidationUtil.validateTestStep("\"Where Are You Staying?\" text is displayed correctly" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getWhereAreYouStayingText()));

        ValidationUtil.validateTestStep("\"Where Are You Staying?\" text is bold correctly" ,
                pageObjectManager.getHotelPage().getWhereAreYouStayingText().getAttribute("class") , "headline1");

        ValidationUtil.validateTestStep("The subheading is correct" ,
                pageObjectManager.getHotelPage().getHotelPageSubHeadingText().getText() , "Save money by booking your flight and your hotel together.");
        ValidationUtil.validateTestStep("The hotel first paragraph is correct" ,
                pageObjectManager.getHotelPage().getAddHotelFirstParagraphText().getText().contains("Add a hotel for as little as ") &&
                        pageObjectManager.getHotelPage().getAddHotelFirstParagraphText().getText().contains("additional per person. Vacation packages are non-refundable"));
        ValidationUtil.validateTestStep("The hotel second paragraph is correct" ,
                pageObjectManager.getHotelPage().getAddHotelSecondParagraphText().getText(),"Additional resort fees and charges for changes may apply.");

//Step 4 : Validate the content inside the filter settings
        ValidationUtil.validateTestStep("Filter By label is correct",
                pageObjectManager.getHotelPage().getFilterByNamelabel().getText() , "Filter By" );
        ValidationUtil.validateTestStep("Filter By Textbox is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getFilterByNameTextBox()));
        ValidationUtil.validateTestStep("Sort By label is correct",
                pageObjectManager.getHotelPage().getSortBylabel().getText() , "Sort By");
        ValidationUtil.validateTestStep("Sort By Drop Down is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getSortByDropDown()));
        ValidationUtil.validateTestStep("Display Number label is correct",
                pageObjectManager.getHotelPage().getDisplayNumberlabel().getText(), "Display Number");
        ValidationUtil.validateTestStep("Display Number is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getDisplayNumberDropDown()));
        ValidationUtil.validateTestStep("The view map button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getViewMapButton()));

//Step 5:
        ValidationUtil.validateTestStep("Filter By Text Box 'Hotel Name' inside verbiage displayed ",
                pageObjectManager.getHotelPage().getFilterByNameTextBox().getAttribute("placeholder"),"Hotel name");

        ValidationUtil.validateTestStep("Sorted By Drop Down have 'Sort By: Featured' inside verbiage",
                new Select(pageObjectManager.getHotelPage().getSortByDropDown()).getFirstSelectedOption().getText(),"Sort By: Featured");

        ValidationUtil.validateTestStep("Displayed Number Drop Down have 'Display: 20' inside verbiage",
                new Select(pageObjectManager.getHotelPage().getDisplayNumberDropDown()).getFirstSelectedOption().getText(),"Display: 20");

//Step 6: Featured Subheading is spelled correctly
        ValidationUtil.validateTestStep("Featured SubHeading is correct",
                pageObjectManager.getHotelPage().getFeaturedLabel().getText(), "Featured");

//Step 7: Content Block is displayed under featured
        ValidationUtil.validateTestStep("Hotel Card is displayed",
                pageObjectManager.getHotelPage().getHotelCard().size() > 0 );

//Step 8: the content block has an image inside
        for (WebElement hotelCard : pageObjectManager.getHotelPage().getHotelCard()) {
            ValidationUtil.validateTestStep("The hotel card have images" ,
                    hotelCard.findElement(By.tagName("img")).getAttribute("class"), "img-fluid");
        }

//Step 9: Hotel name , address, rating are displayed
        for (int count = 0 ; count < pageObjectManager.getHotelPage().getHotelCard().size() ;count ++) {
            ValidationUtil.validateTestStep("The hotel name for card " +count + " is displayed"  ,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(count)));
            ValidationUtil.validateTestStep("The hotel address for card " + count + " is displayed"  ,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelAddressText().get(count)));
            ValidationUtil.validateTestStep("The hotel rating for card " + count + " is displayed"  ,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelStarRatingImage().get(count)));
            ValidationUtil.validateTestStep("The hotel more info link for card " + count + " is displayed"  ,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getMoreInformationLink().get(count)));
        }

//Step 10: Validate hotel prices
        for(WebElement hotelPrice : pageObjectManager.getHotelPage().getHotelCardPriceLink()) {
            ValidationUtil.validateTestStep("The hotel price is displayed",
                    Double.parseDouble(hotelPrice.getText().replace("$", "").trim()) > 0.00);
        }
//Step 11:
        for (WebElement viewButton: pageObjectManager.getHotelPage().getHotelViewButton()) {
            ValidationUtil.validateTestStep("VIEW text color is white",viewButton.getCssValue("color"),RGB_WHITE);
            ValidationUtil.validateTestStep("VIEW background color is blue",viewButton.getCssValue("background-color"),RGB_BLUE);
        }

//Step 12: Validate that no more than 20 hotel cards are displayed on the page by default
        ValidationUtil.validateTestStep("Only 20 or less hotels displayed by default on the hotel page" ,
                pageObjectManager.getHotelPage().getHotelCard().size() <= 20);

//Step 13: Validate functionality of botton of the page navigation
        ValidationUtil.validateTestStep("Left arrow is not displayed on the first page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getLeftPageNavigationButton()));

        int boxSize = pageObjectManager.getHotelPage().getHotelCardPriceLink().size();
        if (boxSize == 20){ //if there are more than 20 hotel boxes there will be an option at the bottom of the page to navigate to different page
            ValidationUtil.validateTestStep("There is more than one page available for hotels",
                    pageObjectManager.getHotelPage().getPageNumberButton().size() > 1);
            ValidationUtil.validateTestStep("The right navigation button is displayed",
                    pageObjectManager.getHotelPage().getRightPageNavigationButton().isDisplayed());
        }

        ValidationUtil.validateTestStep("The All hotels button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getAllHotelsButton()));

//Step 14: Continue without hotel button is displayed
        ValidationUtil.validateTestStep("The Continue without hotel is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getContinueWithoutHotelButton()));

        ValidationUtil.validateTestStep("Continue Without Hotel text color is Blue",
                pageObjectManager.getHotelPage().getContinueWithoutHotelButton().getCssValue("color"),RGB_BLUE);

        pageObjectManager.getHotelPage().getContinueWithoutHotelButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP 15: VALIDATE USER REDIRECTED TO THE OPTIONS PAGE
        ValidationUtil.validateTestStep("The user taken to the options page correctly" ,getDriver().getCurrentUrl(),OPTION_PAGE_URL);
    }
}