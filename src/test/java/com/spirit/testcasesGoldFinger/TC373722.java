package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373722
//Description: Task 27148: TC373722 - US 17569 - 001 - CP - Booking Path - Hub Packaging - View All Hotels - 1 ADT
//Created By: Gabriela
//Created On: 01-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373722 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt", "NoBags",
            "NoSeats", "CheckInOptions", "OptionalUI","Visa","FlightHotel"})
    public void CP_Booking_Path_Hub_Packaging_View_All_Hotels_1_ADT(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373722 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "107";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values:
        final String HOTEL_PAGE         = "/book/options/hotels";
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 1: Open goldfinger test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Book a RT DOM-DOM | 3 months out | 1 ADT
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select flights
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 4: Click Book It
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 5: Fill out customer info and click CONTINUE at the bottom of the page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 6 & 7: Select "CONTINUE WITHOUT ADDING BAGS" & Select "I DON'T NEED BAGS"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 8: Select "CONTINUE WITHOUT SELECTING SEATS"s
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 9: Validate VIEW ALL HOTELS link is displayed under the Hotel Carrousel and click on it.
        ValidationUtil.validateTestStep("Validating 'View All' link is displayed on Options page ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getViewAllHotelsButton()));

        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(HOTEL_PAGE));

//- Step 10: Validate hotels are displayed in tiles format and not carousel format
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelCard().size(); i ++) {
            ValidationUtil.validateTestStep("Verifying tiles format on Hotels page",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(i)));
        }

//- Step 11: Validate search hotel input box displayed
        ValidationUtil.validateTestStep("Validating Search Hotel by name filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNameTextBox()));

//- Step 12: Validate the Filter by Name section is functioning by inserting a title of a hotel
        pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys("Stratosphere");
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Validating hotel filtered by name is displayed",
                pageObjectManager.getHotelPage().getHotelNamesText().get(0).getText(), "Stratosphere");

        pageObjectManager.getHotelPage().getClearAllFiltersButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 13: Validate the following options to Sort By:
        //RECOMMENDED
        ValidationUtil.validateTestStep("Validating Sort by RECOMMENDED options is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getRecommendedButton()));
        //STARS
        ValidationUtil.validateTestStep("Validating Sort by STARS options is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getStarsButton()));
        //PRICE
        ValidationUtil.validateTestStep("Validating Sort by PRICE options is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getPriceButton()));

//- Step 14: Validate each hotel content box displays the following:
        for (int i = 0; i <  pageObjectManager.getHotelPage().getHotelCard().size(); i ++) {
            //Hotel image
            try {
                ValidationUtil.validateTestStep("Verifying Hotel Image is displayed for each tile",
                        TestUtil.verifyElementDisplayed(getDriver().findElements(By.xpath("//app-package-detail-select-modal//img[contains(@class,'hotel-image')]")).get(i)));

            }
            catch (Exception e){}
            try
            {
                ValidationUtil.validateTestStep("Verifying Hotel Image is displayed for each tile",
                        TestUtil.verifyElementDisplayed(getDriver().findElements(By.xpath("//app-hotel-list-item//ngb-carousel//div[@class='carousel-item ng-star-inserted active']//img[contains(@class,'hotel-image')]")).get(0)));
            }catch (Exception e){}

            //If hotel promotion exist display

            //Hotel Name
            ValidationUtil.validateTestStep("Verifying Hotel Name is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(i)));

            //Hotel address
            pageObjectManager.getHotelPage().getHotelNamesText().get(i).click();
            WaitUtil.untilPageLoadComplete(getDriver());
            WaitUtil.untilTimeCompleted(1200);
            ValidationUtil.validateTestStep("Verifying Hotel Address is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardAddressText().get(0)));

            //Map hyperlink with deeplink logic to google map
            ValidationUtil.validateTestStep("Verifying Hotel Map Hyperlink is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelMapButton().get(i)));

            pageObjectManager.getHotelPage().getHotelMapButton().get(i).click();
            WaitUtil.untilPageLoadComplete(getDriver());
            WaitUtil.untilTimeCompleted(3000);
            ValidationUtil.validateTestStep("Verifying Hotel Interactive Map is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotalPageMapPanel()));

            //5 star rating
            ValidationUtil.validateTestStep("Verifying Hotel Map Hyperlink is displayed for each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelStarRatingImage().get(i)));
        }

//- Step 15: Validate pricing is in USD and uplift promotion available when applicable
        ValidationUtil.validateTestStep("Verifying uplift information is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPageUpliftText()));

//- Step 16: Click on VIEW ROOMS on one of the hotels
        pageObjectManager.getHotelPage().getHotelNameTextBox().sendKeys("MGM");
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verifying Room Category window is displayed",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath("//ngb-modal-window//app-package-detail-select-modal")));

//- Step 17: Click ROOMS FROM $XXX.XX button.
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        WaitUtil.untilTimeCompleted(3000);

//- Step 18: Select room
        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 19: View on the Hotel Content box will be replaced with REMOVE
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Remove option is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelRemoveButton()));

//- Step 20: On the Options page select the Check-in Option and click Continue.
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 21: Input valid payment information and complete the booking
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();


        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

    }
}


/************************ XPATH ********************/
//public final String xpath_GoldFingerHotelCardAddress = "//div[@class='card']//div[3]//p//span"; //GOLDFINGER
//    @FindBy (xpath=xpath_GoldFingerHotelCardAddress)
//    private WebElement txt_GoldFingerHotelCardAddress;

//public final String xpath_GoldFingerHotelMapLink = "//app-rating//following-sibling::span[2]//a"; //goldfinger
//    @FindBy (xpath=xpath_GoldFingerHotelMapLink)
//    private List<WebElement> lnk_GoldFingerHotelMap;

//public final String xpath_GoldFingerHotelInteractiveMapPanel = "//app-interactive-map//agm-map[@class='sebm-google-map-container']"; //goldfinger
//    @FindBy (xpath=xpath_GoldFingerHotelInteractiveMapPanel)
//    private WebElement pnl_GoldFingerHotelInteractiveMap;

//public WebElement getGoldFingerHotelCardAddressText() {return txt_GoldFingerHotelCardAddress;}

//public List<WebElement> getGoldFingerMapLink() {return lnk_GoldFingerHotelMap;}

//public WebElement getGoldFingerHotelInteractiveMapPanel() {return pnl_GoldFingerHotelInteractiveMap;}