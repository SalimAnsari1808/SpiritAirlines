package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91180
//Description: 35306 CP_BP_Hub_Options_Hotel Page_Purchase Room
//Created By: Anthony Cardona
//Created On: 3-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 23-Jul-2019
//**********************************************************************************************

public class TC91180 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"OutOfExecution", "BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "MandatoryFields" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "HoteLUI" , "OptionalUI","CheckInOptions","PaymentUI"})
    public void CP_BP_Hub_Options_Hotel_Page_Purchase_Room(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91180 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "60";
        final String ARR_DATE               = "65";
        final String ADULT                  = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String RET_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";


        //Option Page Constant Values
        final String OPTIONS_VALUE		    = "CheckInOption:AirportAgent";
        final String OPTIONS_PAGE_URL       = "spirit.com/book/options";

        //open browser
        openBrowser(platform);
//Step 1: Adult age between 18 and 21 years olg gets to the options page
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
        //Fill contact DOB more than 18 years old and less than 21
        String dateOfBirth = TestUtil.getStringDateFormat("-9000", "MM/dd/yyyy");
        System.out.println(dateOfBirth);

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0));
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(dateOfBirth);
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
        WaitUtil.untilPageLoadComplete(getDriver());
//Step 2 User lands on the options page
        ValidationUtil.validateTestStep("The user lands on the options page successfully" , getDriver().getCurrentUrl(),OPTIONS_PAGE_URL);

//Step 3: "Hotels for Less" verbiage displayed above hotel carousel
        ValidationUtil.validateTestStep("\"Hotels for Less\" is displayed on the options page" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText()));

//Step 4: Click on the "VIEW ALL HOTELS"
        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();

//Step 5: "Where Are You Staying?" page displays
        ValidationUtil.validateTestStep("\"Where Are You Staying?\" text is displayed correctly" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getWhereAreYouStayingText()));

//Step 6: validate the content at the bottom of the page
//        int boxSize = pageObjectManager.getHotelPage().getHotelNamesText().size();
//        if (boxSize == 20) //if there are more than 20 hotel boxes there will be an option at the bottom of the page to navigate to different page
//        {
//            ValidationUtil.validateTestStep("There is more than one page available for hotels", pageObjectManager.getHotelPage().getPageNumberButton().size() > 1);
//            ValidationUtil.validateTestStep("The right navigation button is displayed",
//                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getRightPageNavigationButton()));
//        }
        int hotelCounter = Integer.parseInt(pageObjectManager.getHotelPage().getHotelCounterText().getText().replace("Hotels", "").trim());
        if (hotelCounter>20) {
           ValidationUtil.validateTestStep("There is more than one page available for hotels",
                   TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getShowMoreButton()));
        }

        ValidationUtil.validateTestStep("The Continue without purchase is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getContinueWithoutHotelButton()));

//Step 7: If user is on page 1 one page displayed, there are no arrows
        ValidationUtil.validateTestStep("Left arrow is not displayed on the first page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getLeftPageNavigationButton()));

//Step 8: Click on the third hotel page
        try {
            if (pageObjectManager.getHotelPage().getPageNumberButton().size() >= 3) {
                pageObjectManager.getHotelPage().getPageNumberButton().get(2).click();//click on the third page
                WaitUtil.untilTimeCompleted(1200);
                ValidationUtil.validateTestStep("Left arrow is displayed on the third page", TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getLeftPageNavigationButton()));
            }
        }
        catch (Exception e) {}

//Step 9: Navigate to the stratosphere/Luxor/The D las Vegas
//        WaitUtil.untilTimeCompleted(2000);
//        pageObjectManager.getHotelPage().getAllHotelsButton().click();//click on all hotels if the option is available
//        WaitUtil.untilTimeCompleted(1200);

        int USUABLE_HOTELCARD = 0;

        final String STRATOSPHERE_HOTEL_NAME       = "STRATOSPHERE CASINO, HOTEL & TOWER";
        final String THE_D_HOTEL_NAME              = "THE D LAS VEGAS";
        final String LUXOR_HOTEL_NAME              = "LUXOR HOTEL AND CASINO";

        int counter = 0;

        //Only the 3 listed hotels can be selected. the below loop will set USUABLE_HOTELCARD to the correct int to use in the lists
        for(WebElement HotelName: pageObjectManager.getHotelPage().getHotelNamesText())
        {
            if (HotelName.getText().contains(LUXOR_HOTEL_NAME)
                    || HotelName.getText().contains(THE_D_HOTEL_NAME)
                    || HotelName.getText().contains(STRATOSPHERE_HOTEL_NAME))
            {
                USUABLE_HOTELCARD = counter;
                break;
            }

            counter ++;
        }

        WaitUtil.untilTimeCompleted(3000);
//        pageObjectManager.getHotelPage().getViewAllHotelCardViewLink().get(USUABLE_HOTELCARD).click();
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHotelPage().getViewAllHotelCardViewLink().get(USUABLE_HOTELCARD));

//Step 10: Validate the Overview, Policies, Accommodations, Dining, Activities
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2500);

        ValidationUtil.validateTestStep("The Selected Hotel's Overview tab is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomsPanel()));
        ValidationUtil.validateTestStep("The Selected Hotel's Policies tab is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowOverviewPanel()));
        ValidationUtil.validateTestStep("The Selected Hotel's Accommodations tab is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowPoliciesPanel()));
        ValidationUtil.validateTestStep("The Selected Hotel's Dining tab is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowAmenitiesPanel()));
        ValidationUtil.validateTestStep("The Selected Hotel's Activities tab is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowDinningPanel()));
        ValidationUtil.validateTestStep("The Selected Hotel's Photos tab is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowPhotosPanel()));
        ValidationUtil.validateTestStep("The Selected Hotel's Maps tab is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowMapsPanel()));

//Step 11:Choose the drop-down located next to "FROM $xxx.xx" Select the cheapest room to continue

        pageObjectManager.getHotelPage().getHotelWindowRoomPricesButton().click();
        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();

//Step 13: Scroll down to view the selected hotel
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getHotelPage().getHotelCardRemoveLink());

//Step 14: Click on the blue REMOVE verbiage
        pageObjectManager.getHotelPage().getHotelCardRemoveLink().click();
        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("The selected Hotel is removed" ,
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelSelectedPanel()));

//Step 15: Return to the Hub/Options page
//        pageObjectManager.getHotelPage().getContinueWithoutHotelButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user is taken back to the options page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionsPageHeaderText()));

        WaitUtil.untilPageLoadComplete(getDriver());
//Step 16 17 18: Keep clicking the caret until passenger views "The Stratosphere Hotel Casino and Tower" or "The Flamingo" or "Luxor" or "The D LAS" inside the Hotel carousel
        WaitUtil.untilTimeCompleted(2000);

        final String STRATOSPHERE_HOTEL_NAME1       = "STRATOSPHERE CASINO, HOTEL AND TOWER";
        final String THE_D_HOTEL_NAME1              = "THE D LAS VEGAS";
        final String LUXOR_HOTEL_NAME1              = "LUXOR HOTEL AND CASINO";

        List<WebElement> displayedHotelNamesList = getDisplayedWebElements(pageObjectManager.getHotelPage().getHotelCardNameText()); //list of displayed Hotel Names
        List<WebElement> displayedHotelViewButtons = getDisplayedWebElements(pageObjectManager.getHotelPage().getHotelCardViewLink()); //list of displayed Hotel View Buttons

        for(int count = 0; count<displayedHotelNamesList.size();count++){

            //if the hotel names matches expected name
            if (displayedHotelNamesList.get(count).getText().toUpperCase().contains(LUXOR_HOTEL_NAME1) ||
                    displayedHotelNamesList.get(count).getText().toUpperCase().contains(THE_D_HOTEL_NAME1) ||
                    displayedHotelNamesList.get(count).getText().toUpperCase().contains(STRATOSPHERE_HOTEL_NAME1)) {
                if (displayedHotelViewButtons.get(count).isDisplayed()) //if the view link is displayed for that hotel, click it and break loop{
                    try { //script giving issues here clicking on element (TimeoutException), have to surround with try catch
                        displayedHotelViewButtons.get(count).click();
                    }
                    catch (Exception e){}
                break;
            }

        }

//Step 19: "The Stratosphere Hotel Casino and Tower" or "The Flamingo" or "Luxor" or "The D LAS"  hotel block pops-up. Choose the drop-down located next to "FROM $xxx.xx" Then select the cheapest room to continue
        ValidationUtil.skipTestCase(STRATOSPHERE_HOTEL_NAME1+" or "+THE_D_HOTEL_NAME1+" or "+LUXOR_HOTEL_NAME1+" is not available",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomPricesButton()));

        String hotelAddress = pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelAddressText().getText();
        System.out.println("hotelAddress: " +hotelAddress);

        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getHotelPage().getHotelWindowRoomPricesButton().click();
        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();

//Step 20: Scroll down to view the Check-in section, choose "I want to pre-pay for airport agent check-in for $20 per person each way" to navigate to the Payment Page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);

        String hotelName = pageObjectManager.getOptionsPage().getSelectedHotelNameText().getText();


        //Store Hotel Information
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilTimeCompleted(2000);


//Step 21: On the Payment Page, scroll to confirm the passenger selected hotel is displayed correctly
        ValidationUtil.validateTestStep("The hotel name on the payment page is correct",hotelName,
                pageObjectManager.getPaymentPage().getOptionSectionHotelNameText().getText() );
        ValidationUtil.validateTestStep("The hotel address on the payment page is correct", hotelAddress,
                pageObjectManager.getPaymentPage().getOptionSectionHotelAddressText().getText() );
    }

    private List<WebElement> getDisplayedWebElements(List<WebElement> elementList){
        ArrayList<WebElement> arrayList = new ArrayList<>();
        for (WebElement element:elementList) {
            if(element.isDisplayed()){
                arrayList.add(element);
            }
        }
        return arrayList;
    }
}