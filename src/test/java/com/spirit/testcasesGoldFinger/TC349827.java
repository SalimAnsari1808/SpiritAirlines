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
//Test CaseID: TC349827
//Title      : 001 - CP - Flight + Hotel + Car - Availability Page - Validate Sections are displaying
//Created By : Kartik Chauhan
//Created On : 04-Dec-2019
//Reviewed By: Gabriela
//Reviewed On: 11-Dec-2019
//**********************************************************************************************
public class TC349827 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","RoundTrip","FlightHotelCar","Guest","DomesticDomestic","Outside21Days","Adult",
            "CarsUI","HotelUI"})
    public void CP_Flight_Hotel_Car_Availability_Page_Validate_Sections_are_displaying (@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349827 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "115";
        final String ARR_DATE           = "119";
        final String ADULTS             = "2";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        final String HOTELROOM          = "2 Rooms";
        //open browser
        openBrowser(platform);
//Step--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step--2/3/4
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
//STep--3
        //verify Select your Hotel (Page Header)
        ValidationUtil.validateTestStep("Verify 'Select Your Hotel' header is displaying at the top of the Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getSelectYourHotelHeaderText()));
        //verify Review your Flights
        ValidationUtil.validateTestStep("Verify 'Review Your Flights' sub-header is displaying at the top of the Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getReviewYourFlightsHeaderText()));
        //Choose your Hotel
        ValidationUtil.validateTestStep("Verify 'Choose Your Hotel' sub-header is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getChooseYourHotelHeaderText()));
//Step--4
        ValidationUtil.validateTestStep("Validating Hotel Search filter is displayed",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHotelPage().xpath_HotelSearchLink)));
        ValidationUtil.validateTestStep("Validating Price filter is displayed",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHotelPage().xpath_HotelPriceLink)));
        ValidationUtil.validateTestStep("Validating Hotel Rating filter is displayed",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHotelPage().xpath_HotelRatingLink)));
        //Step 7: "XXX Hotels" appears on the top left corner of the top Hotel tile.
            ValidationUtil.validateTestStep("The hotel count is displayed onto the Hotel section",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCounterText()));

        ValidationUtil.validateTestStep("The sort by RECOMMENDED option is displayed" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHotelPage().xpath_RecommendedButton)));
        ValidationUtil.validateTestStep("The sort by STARS option is displayed" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHotelPage().xpath_StarsButton)));
        ValidationUtil.validateTestStep("The sort by PRICE option is displayed" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHotelPage().xpath_PriceButton)));
//Step--8
        int hotelCounter = Integer.parseInt(pageObjectManager.getHotelPage().getHotelCounterText().getText().replace("Hotels", "").trim());
        if (hotelCounter>=20) {
            ValidationUtil.validateTestStep("Validating there is a list of 20 hotel tiles displayed.",
                    pageObjectManager.getHotelPage().getHotelCardImageImage().size() == 20);
        }else{
            ValidationUtil.validateTestStep("Validating there is a list of 20 hotel tiles displayed.",
                    pageObjectManager.getHotelPage().getHotelCardImageImage().size() < 20);
        }
//Step--9
        int time1 = hotelCounter/20;
        for (int i =0; i < time1; i ++)
        {
                pageObjectManager.getHotelPage().getShowMoreButton().click();
        }

        ValidationUtil.validateTestStep("'SHOW MORE' button is no longer displayed on the page",
                !TestUtil.verifyElementDisplayed( pageObjectManager.getHotelPage().getShowMoreButton()));
        WaitUtil.untilPageLoadComplete(getDriver());
//Step--10
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Hilton","NA");
        WaitUtil.untilPageLoadComplete(getDriver());
//Step--11
        ValidationUtil.validateTestStep("'Pick Your Ride' header is displaying" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_CarsPagePickYourRideText)));

//Step--12
        ValidationUtil.validateTestStep("'Price' header is displaying in left pane" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_PriceFilterLabel)));

        ValidationUtil.validateTestStep("'Seat' header is displaying in left pane" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_SeatsFilterLabel)));

        ValidationUtil.validateTestStep("'Bag' header is displaying in left pane" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_BagsFilterLabel)));

        ValidationUtil.validateTestStep("'Car Type' header is displaying in left pane" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_CarTypeFilterLabel)));

        ValidationUtil.validateTestStep("'Rental Agency' header is displaying in left pane" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_CarsPageRentalPriceText)));

        ValidationUtil.validateTestStep("'Car Options' header is displaying in left pane" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_CarOptionsFilterLabel)));
//Step--13:

        try{
            Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Cars","").trim());
            ValidationUtil.validateTestStep("Once on the Car Page, next to the filter, validate there is a car count formatted \"# cars\".",true);
        }catch(Exception e){
            ValidationUtil.validateTestStep("Once on the Car Page, next to the filter, validate there is a car count formatted \"# cars\".",false );
        }

//Step--14:
        ValidationUtil.validateTestStep("'Recommended' header is displaying in left pane" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.id(pageObjectManager.getCarPage().id_SortByRecommendedButton)));

        ValidationUtil.validateTestStep("'Price' header is displaying in left pane" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.id(pageObjectManager.getCarPage().id_SortByPriceButton)));

        ValidationUtil.validateTestStep("'Seats' header is displaying in left pane" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.id(pageObjectManager.getCarPage().id_SortBySeatsButton)));

        ValidationUtil.validateTestStep("'Car Type' header is displaying in left pane" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.id(pageObjectManager.getCarPage().id_SortByCarTypeButton)));

//Step--16:
        ValidationUtil.validateTestStep("The Car count is displayed onto the Car section" ,
                pageObjectManager.getCarPage().getCarsPageCarsPanel().size() <= 20);

//Step--17:
        while (TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getCarPage().xpath_CarPageShowMoreButton))) {
            pageObjectManager.getCarPage().getCarPageShowMoreButton().click();
            WaitUtil.untilTimeCompleted(2000);
        }
//step--18:
        ValidationUtil.validateTestStep("'Show More' link is displaying" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_CarsPageMoreInfoLink)));

    }
}
//
//    public final String xpath_SelectYourHotelHeaderText ="//h1[contains(text(),'Select Your Hotel') or contains(text(),'Elija su Hotel')]";
//    @FindBy(xpath=xpath_SelectYourHotelHeaderText)
//    private WebElement txt_SelectYourHotelHeader;
//
//    public final String xpath_ReviewYourFlightsHeaderText ="//div[contains(text(),'Review Your Flights') or contains(text(),'Revise sus Vuelos ')]";
//    @FindBy (xpath=xpath_ReviewYourFlightsHeaderText)
//    private WebElement txt_ReviewYourFlightsHeader;

//    public WebElement getSelectYourHotelHeaderText() {
//        return txt_SelectYourHotelHeader;
//    }
//
//    public WebElement getReviewYourFlightsHeaderText() {
//        return txt_ReviewYourFlightsHeader;
//    }