package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91120
//Description: Hub-Options_CP_BP_ Page Wireframe
//Created By: Anthony Cardona
//Created On: 31-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 1-AUG-2019
//**********************************************************************************************

public class TC91120 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "OptionsUI","CarsUI","HotelsUI"})
    public void Hub_Options_CP_BP_Page_Wireframe(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91120 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "10";
        final String ARR_DATE           = "15";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //open browser
        openBrowser(platform);

        //Home Page Methods
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

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 1: Validate the user ands on the Hub options page
        final String BAG_URL            = "/book/options";

        ValidationUtil.validateTestStep("Validating Bags Page is on the right URL", getDriver().getCurrentUrl(),BAG_URL);

//Step 2: Validate ""ADD ON TRIP OPTIONS""
        ValidationUtil.validateTestStep("Options page header is displayed on the options page",
                pageObjectManager.getOptionsPage().getOptionsPageHeaderText().getText(), "Add On Trip Options");
        ValidationUtil.validateTestStep("Options page paragraph is displayed on the options page",
                pageObjectManager.getOptionsPage().getOptionsPageDescriptionText().getText(), "Make the most of your getaway with these special deals.");

//Step 3: Validate savings on Car
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCarouselTitleText())) {
            ValidationUtil.validateTestStep("Options page Car header is displayed on the options page",
                    pageObjectManager.getCarPage().getCarCarouselTitleText().getText(), "Savings on Cars");

//Step 4: Validate the car card content boxes
            //there are 4 cards
            int carCardcounter = 0;
            for (WebElement cardName : pageObjectManager.getCarPage().getCarCardNameText()) {
                if (cardName.isDisplayed()) {
                    carCardcounter++;
                }
            }
            ValidationUtil.validateTestStep("There are 4 car cards displayed", carCardcounter == 4);

            //blue right arrow
            ValidationUtil.validateTestStep("The right carousel button is blue",
                    pageObjectManager.getCarPage().getCarCarouselRightButton().getCssValue("color"),"rgba(0, 115, 230, 1)");

            //view all cars button is displayed
            ValidationUtil.validateTestStep("The View all cars button is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getViewAllCarsButton()));
        }
//Step 5: Hotel content is displayed
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText())) {
            ValidationUtil.validateTestStep("\"Hotels for Less\" is displayed on the options page",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText()));

            ValidationUtil.validateTestStep("The right carousel button is blue", pageObjectManager.getHotelPage().getHotelCarouselRightButton().getCssValue("color").equals("rgba(0, 115, 230, 1)"));

            int hotelCardcounter = 0;
            for (WebElement cardName : pageObjectManager.getHotelPage().getHotelCardNameText()) {
                if (cardName.isDisplayed()) {
                    hotelCardcounter++;
                }
            }
            ValidationUtil.validateTestStep("There are 4 hotel cards displayed", hotelCardcounter == 4);
            ValidationUtil.validateTestStep("The right carousel button is blue",
                    pageObjectManager.getHotelPage().getHotelCarouselRightButton().getCssValue("color"),"rgba(0, 115, 230, 1)");
            ValidationUtil.validateTestStep("The view all hotels button is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getViewAllHotelsButton()));
        }
//Step 6: Invalid as there are no longer activities

//Step 7: Options header is displayed
        ValidationUtil.validateTestStep("The Options sub-header is correct" ,
                pageObjectManager.getOptionsPage().getOptionsHeaderForAncillariesText().getText(), "Options");
        //validate the 3 content boxes
        boolean threeContentBoxesIsDisplayed = TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardPanel()) &&
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutSecurityCardPanel()) &&
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardPanel());
        ValidationUtil.validateTestStep("The three content boxes are dsiplayed" , threeContentBoxesIsDisplayed);

//Step 8: Validate the first content box (Flight Flex)
        ValidationUtil.validateTestStep("The flight flex card title is correct" ,
                pageObjectManager.getOptionsPage().getFlightFlexCardTitleText().getText() , "Flight Flex" );
        ValidationUtil.validateTestStep("The flight flex card Icon is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardIconImage()));
        ValidationUtil.validateTestStep("The flight flex card body text is correct" ,
                pageObjectManager.getOptionsPage().getFlightFlexCardBodyText().getText() ,
                "Flight Flex - Modify your flight once for free!" );
        ValidationUtil.validateTestStep("The flight flex tool tip is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardToolTipLink()) );
        ValidationUtil.validateTestStep("The flight flex card price text is correct" ,
                pageObjectManager.getOptionsPage().getFlightFlexCardPriceText().getText() , "$45.00" );
        ValidationUtil.validateTestStep("The flight flex per person text is displayed" ,
                pageObjectManager.getOptionsPage().getFlightFlexCardPerPersonText().getText() , "Per person");
        ValidationUtil.validateTestStep("The Blue Add button is displayed" ,
                pageObjectManager.getOptionsPage().getFlightFlexCardAddButton().getCssValue("color"),"rgba(0, 115, 230, 1)");

//Step 9: Validate the third content box (Shortcut Boarding)
        ValidationUtil.validateTestStep("The Shortcut boarding card title is correct" ,
                pageObjectManager.getOptionsPage().getShortCutBoardingCardTitleText().getText() , "Shortcut Boarding" );
        ValidationUtil.validateTestStep("The Shortcut Boarding card Icon is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardIcon()));
        ValidationUtil.validateTestStep("The Shortcut boarding card body text is correct" ,
                pageObjectManager.getOptionsPage().getShortCutBoardingCardBodyText().getText() , "Zone 2 priority boarding and early access to the overhead bins." );
        ValidationUtil.validateTestStep("The Shortcut boarding card price text is correct" ,
                Double.parseDouble(pageObjectManager.getOptionsPage().getShortCutBoardingCardPriceText().getText().replace("$","")) >= 5.99);
        ValidationUtil.validateTestStep("The Shortcut boarding per person text is displayed" ,
                pageObjectManager.getOptionsPage().getShortCutBoardingCardPerPersonText().getText() , "Per person");
        ValidationUtil.validateTestStep("The Blue Add button is displayed" ,
                pageObjectManager.getOptionsPage().getShortCutBoardingCardAddButton().getCssValue("color").equals("rgba(0, 115, 230, 1)"));


//Step 10: Validate the second content box (Shortcut Security)
        ValidationUtil.validateTestStep("The Shortcut Security card title is correct" ,
                pageObjectManager.getOptionsPage().getShortCutSecurityCardTitleText().getText() , "Shortcut Security" );
        ValidationUtil.validateTestStep("The Shortcut Security card Icon is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutSecurityCardIconImage()));
        ValidationUtil.validateTestStep("The Shortcut Security card body text is correct" ,
                pageObjectManager.getOptionsPage().getShortCutSecurityCardBodyText().getText() ,
                "Skip the line and get access to a dedicated security lane at the airport. " );
        ValidationUtil.validateTestStep("The Shortcut Security card body ToolTip is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutSecurityCardToolTipLink()));
        ValidationUtil.validateTestStep("The Shortcut Security card price text is correct" ,
                Double.parseDouble(pageObjectManager.getOptionsPage().getShortCutSecurityCardPriceText().getText().replace("$","")) <=15.00 );
        ValidationUtil.validateTestStep("The Shortcut Security per person text is displayed" ,
                pageObjectManager.getOptionsPage().getShortCutSecurityCardPerPersonText().getText() , "Per person");
        ValidationUtil.validateTestStep("The Blue Add button is displayed" ,
                pageObjectManager.getOptionsPage().getShortCutSecurityCardAddButton().getCssValue("color"),"rgba(0, 115, 230, 1)");

//Step 11: Validate the check in option box
        ValidationUtil.validateTestStep("The Check in option box is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getCheckInOptionCardPanel()));

//Step 12  Validate the content inside the check in option box
        ValidationUtil.validateTestStep("Check in Content Box title is correct",
                pageObjectManager.getOptionsPage().getCheckInOptionCardTitleText().getText() , "Check-In");
        ValidationUtil.validateTestStep("The Shortcut Boarding card Icon is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getCheckInOptionCardIcon()));
        ValidationUtil.validateTestStep("Check in Content Box Body Text is correct",
                pageObjectManager.getOptionsPage().getCheckInOptionCardBodyText().getText() ,
                "Save yourself some $$$ and check in online for free. It costs time and resources to check in at the airport, which means it'll cost you $10.");
        ValidationUtil.validateTestStep("Check in Select label is correct",
                pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectLabel().getText() , "Select your Check-In option (Required)");
        ValidationUtil.validateTestStep("Check in Select drop down is correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown()));

//Step 13: Validate the Options Total Breakdown
        ValidationUtil.validateTestStep("The total breakdown text is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalContainerText()));
        ValidationUtil.validateTestStep("The total breakdown total is correct",
                pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().getText() , "$0.00");

//Step 14: Validate the continue button on the options page
        ValidationUtil.validateTestStep("The options continue button is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getContinueToPurchaseButton()));
    }
}