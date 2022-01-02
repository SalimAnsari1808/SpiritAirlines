package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91164
//Description: 35301 CP_BP_Hub-Options_Hotel Page Wireframe
//Created By: Anthony Cardona
//Created On: 19-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-Jul-2019
//**********************************************************************************************

public class TC91164 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "MandatoryFields" , "NoBags" , "NoSeats" , "OptionsUI" , "HotelsUI"})
    public void CP_BP_Hub_Options_Hotel_Page_Wireframe(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91164 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
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
        final String OPTIONS_VALUE      = "CheckInOption:AirportAgent";

        //open browser
        openBrowser(platform);
//Step 1: Adult age between 18 and 21 years olg gets to the options page
        //*************************Home Page Methods****************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //***************Flight Availability Page Methods********************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //******************Passenger Information Page Methods************************/
        //Fill contact DOB more than 18 years old and less than 21
        String dateOfBirth = TestUtil.getStringDateFormat("-9000", "MM/dd/yyyy");
        System.out.println(dateOfBirth);

        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");
        //adult title drop down
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0), passengerInfoData.title);
        //adult first name box
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);
        //adult last name list box
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);
        //adult DOB more than 18 years old and less than 21 years old list box
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(dateOfBirth);

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //*************************Bags Page Methods***********************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //************************Seats Page Methods***********************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //**********************Options Page Methods**********************************/

        ValidationUtil.validateTestStep("The user lands on the options page successfully", getDriver().getCurrentUrl(),"spirit.com/book/options");

//Step 2: "Hotels for Less" verbiage displayed above hotel carousel
        ValidationUtil.validateTestStep("\"Hotels for Less\" is displayed on the options page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText()));

//Step 3: Click on the "VIEW ALL HOTELS"
        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();

//Step 4: "Where Are You Staying?" page displays, click on the non-refundable link
        ValidationUtil.validateTestStep("\"Where Are You Staying?\" text is displayed correctly",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getWhereAreYouStayingText()));


        pageObjectManager.getHotelPage().getNonRefundableLink().click();


        String RefundabilityPopUpHeader             = "Refundability";
        String RefundabilityPopUpBodyMessage1       = "Refunds are allowed for reservations made one week or more prior to your departure, provided that you make the refund request within 24 hours of your initial reservation.";
        String RefundabilityPopUpBodyMessage2       = "Please call 801.401.2222 to request such a refund.";
        String RefundabilityPopUpCloseButtonText    = "CLOSE WINDOW";


        ValidationUtil.validateTestStep("The refundability Header is correct",
                RefundabilityPopUpHeader, pageObjectManager.getHotelPage().getRefundabilityPopUpHeaderText().getText());
        ValidationUtil.validateTestStep("The refundability close button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getRefundabilityPopUpCloseButton()));
        ValidationUtil.validateTestStep("The refundability body text is correct",
                RefundabilityPopUpBodyMessage1, pageObjectManager.getHotelPage().getRefundabilityPopUpBodyText1().getText());
        ValidationUtil.validateTestStep("The refundability body text is correct",
                RefundabilityPopUpBodyMessage2, pageObjectManager.getHotelPage().getRefundabilityPopUpBodyText2().getText());
        ValidationUtil.validateTestStep("The refundability Close Window button text is correct",
                RefundabilityPopUpCloseButtonText, pageObjectManager.getHotelPage().getRefundabilityPopUpCloseWindowButton().getText());

//Step 5: click on close button for the modal
        WaitUtil.untilTimeCompleted(2500);
        pageObjectManager.getHotelPage().getRefundabilityPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(2500);

//Step 6: click on non refundable link

        pageObjectManager.getHotelPage().getNonRefundableLink().click();

        WaitUtil.untilTimeCompleted(2500);

//Step 7: click on close window for the modal
        pageObjectManager.getHotelPage().getRefundabilityPopUpCloseWindowButton().click();
        WaitUtil.untilTimeCompleted(2500);

//Step 8 and 9: INVALID, Selenium cant click on elements that are not clickable

//Step 10: Validate hoverover color
        ValidationUtil.validateTestStep("The hover over color is blue",
                pageObjectManager.getHotelPage().getFilterByNameTextBox().getAttribute("class"),"hover-blue-border");

//Step 12: Validate options for the Sort By Drop Down
        List<WebElement> sortByOptions = new Select(pageObjectManager.getHotelPage().getSortByDropDown()).getOptions();
        String[] sortByExpected = { "Sort By: Featured",
                "Sort By: Alphabetical: A to Z",
                "Sort By: Alphabetical: Z to A",
                "Sort By: Price: Low to High",
                "Sort By: Price: High to Low",
                "Sort By: Star Rating: 0 to 5",
                "Sort By: Star Rating: 5 to 0"};

        for (int count = 0; count < sortByOptions.size(); count++) {
            ValidationUtil.validateTestStep(sortByExpected[count] + " is correctly displayed as a sort by option", sortByExpected[count], sortByOptions.get(count).getText());
        }

//Step 13: Validate hoverover color
        ValidationUtil.validateTestStep("The hover over color is blue", pageObjectManager.getHotelPage().getDisplayNumberDropDown().getAttribute("class"),"hover-blue-border");

//Step 14: Validate options for the Display Number Drop Down
        List<WebElement> displayNumberOptions = new Select(pageObjectManager.getHotelPage().getDisplayNumberDropDown()).getOptions();
        String[] displayNumberExpected = {"Display: 10", "Display: 20", "Display: All"};

        for (int count = 0; count < displayNumberOptions.size(); count++) {
            ValidationUtil.validateTestStep(displayNumberExpected[count] + " is correctly displayed as a sort by option", sortByExpected[count], sortByOptions.get(count).getText());
        }

//Step 15: Click on View Map and validate map displayed
        pageObjectManager.getHotelPage().getViewMapButton().click();
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The Google map is displayed correctly",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotalPageMapPanel()));

//Step 16: Hover over the hotel content card and validate tool tip

        for (int count = 0; count < pageObjectManager.getHotelPage().getHotelNamesText().size(); count++) {
            String hotelName = pageObjectManager.getHotelPage().getHotelNamesText().get(count).getText().toUpperCase();
            String mapToolTipName = pageObjectManager.getHotelPage().getHotelMapButton().get(count).getAttribute("title").toUpperCase();
            ValidationUtil.validateTestStep("The Hotel Map tool tip is correct", "MAP (" + hotelName + ")", mapToolTipName);
        }

//Step 17: Click on map next to hotel name
        pageObjectManager.getHotelPage().getHotelMapButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

//Step 18: Validate when the user clicks on map a second time, the map closes
        pageObjectManager.getHotelPage().getHotelMapButton().get(0).click();
        //you can use the below code for workaround
        pageObjectManager.getHotelPage().getLessInformationLink().get(0).click();
        WaitUtil.untilTimeCompleted(1200);

//Step 19: hover over the hotel more info link and validate the tool tip
        for (int count = 0; count < pageObjectManager.getHotelPage().getHotelNamesText().size(); count++) {
            String hotelName = pageObjectManager.getHotelPage().getHotelNamesText().get(count).getText().toUpperCase();
            String mapToolTipName = pageObjectManager.getHotelPage().getMoreInformationLink().get(count).getAttribute("title").toUpperCase();
            ValidationUtil.validateTestStep("The Hotel More info tool tip is correct", hotelName, mapToolTipName);
        }

//Step 20: Click on the more info link and validate the hotel expands
        pageObjectManager.getHotelPage().getMoreInformationLink().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 21: Validate that the hotel information is displayed
        ValidationUtil.validateTestStep("Hotel Overview is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomsPanel()));
        ValidationUtil.validateTestStep("Hotel Policies is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowOverviewPanel()));
        ValidationUtil.validateTestStep("Hotel Accommodations is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowPoliciesPanel()));
        ValidationUtil.validateTestStep("Hotel Dining is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowAmenitiesPanel()));
        ValidationUtil.validateTestStep("Hotel Activities is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowDinningPanel()));
        ValidationUtil.validateTestStep("Hotel Photos is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowPhotosPanel()));
        ValidationUtil.validateTestStep("Hotel Maps is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowMapsPanel()));

//Step 22: Click on less info
        pageObjectManager.getHotelPage().getLessInformationLink().get(0).click();


//Step 23: validate the content at the bottom of the page
        int boxSize = pageObjectManager.getHotelPage().getHotelNamesText().size();
        if (boxSize == 20) //if there are more than 20 hotel boxes there will be an option at the bottom of the page to navigate to different page
        {
            ValidationUtil.validateTestStep("There is more than one page available for hotels",
                    pageObjectManager.getHotelPage().getPageNumberButton().size() > 1);
            ValidationUtil.validateTestStep("The right navigation button is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getRightPageNavigationButton()));
        }

        ValidationUtil.validateTestStep("The All Hotels Button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getAllHotelsButton()));
        ValidationUtil.validateTestStep("The Continue without purchase is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getContinueWithoutHotelButton()));

//Step 24: Click continue without hotel and navigate to options page
        pageObjectManager.getHotelPage().getContinueWithoutHotelButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user is correctly taken to the options page", getDriver().getCurrentUrl(),"spirit.com/book/options");
    }
}