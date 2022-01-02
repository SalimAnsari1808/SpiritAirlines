package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91130
//Test Name: Vacation Path_CP_Flight-Hotel_Booking rooms
// Created By: Anthony Cardona
//Created On : 14-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 20-Aug-2019
//**************************************************************************************************

public class TC91130 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups={"BookPath", "DomesticDomestic", "RoundTrip", "FlightHotel", "Outside21Days","Adult","Guest", "FlightAvailabilityUI", "HotelsUI", "HomeUI"})

    public void Vacation_Path_CP_Flight_Hotel_Booking_rooms(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91130 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
//-- Step 1: (Pre-requisite: Create a booking 3 months out. Keep booking a cheap as possible. In other words only book for 1 night stay
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE 			= "Flight+Hotel";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "110";
        final String ARR_DATE 			= "111";
        final String ADULT				= "6";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String HOTEL_ROOM 		= "3 Rooms";
        final String HOME_PAGE_URL      = "spirit.com";

        //Flight + Hotel Availability Page
        final String UPGRADE_VALUE      = "BookIt";

        //Passenger Information Constant Values
        final String PASS_INFO_URL      = "/book/passenger";

        //open browser
        openBrowser( platform);

        /******************************** Home Page *****************************************/

        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
//Step 1: Validate that user cant select 2 rooms for 1 passenger

        pageObjectManager.getHomePage().getPassengerBox().click();
        pageObjectManager.getHomePage().getAdultMinusLink().click();

        List<WebElement> displayNumberOptions = new Select(pageObjectManager.getHomePage().getRoomsDropDown()).getOptions();
        ValidationUtil.validateTestStep("There is only one option available on room number" , displayNumberOptions.size() == 1 && displayNumberOptions.get(0).getText().contains("1 Room"));

        pageObjectManager.getHomePage().getPassengerBox().click();//close passenger box

//Step 2: Add 6 adult validate that they cant select more than 4 rooms
        pageMethodManager.getHomePageMethods().selectTravellingPassenger("6", CHILD, INFANT_SEAT, INFANT_LAP);

        displayNumberOptions = new Select(pageObjectManager.getHomePage().getRoomsDropDown()).getOptions();

        ValidationUtil.validateTestStep("There is only one option available on room number" , displayNumberOptions.size() == 4 && displayNumberOptions.get(3).getText().contains("4 Rooms"));


//Step 3: Validate that you cant add more than 8 passengers for a vacation booking
        pageMethodManager.getHomePageMethods().selectTravellingPassenger("8", CHILD, INFANT_SEAT, INFANT_LAP);
        ValidationUtil.validateTestStep("The adults plus button is disabled" , pageObjectManager.getHomePage().getAdultPlusLink().getAttribute("class").contains("disabled"));

//Step 4: Select 3 adults and 3 rooms, Valdiate user taken to the flights page
        pageMethodManager.getHomePageMethods().selectTravellingPassenger("3", CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTEL_ROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());


//Step 5: Validate the content inside the filter settings
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


        ValidationUtil.validateTestStep("Filter By Text Box 'Hotel Name' inside verbage displayed ",
                pageObjectManager.getHotelPage().getFilterByNameTextBox().getAttribute("placeholder"),"Hotel name");

        ValidationUtil.validateTestStep("Sorted By Drop Down have 'Sort By: Featured' inside verbage",
                new Select(pageObjectManager.getHotelPage().getSortByDropDown()).getFirstSelectedOption().getText(),"Sort By: Featured");

        ValidationUtil.validateTestStep("Displayed Number Drop Down have 'Display: 20' inside verbage",
                new Select(pageObjectManager.getHotelPage().getDisplayNumberDropDown()).getFirstSelectedOption().getText(),"Display: 20");

//Step 6: Hotel name , address, rating, and price are displayed
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

        for(WebElement hotelPrice : pageObjectManager.getHotelPage().getHotelCardPriceLink()) {
            ValidationUtil.validateTestStep("The hotel price is displayed",
                    Double.parseDouble(hotelPrice.getText().replace("$", "").trim()) > 0.00);
        }

//Step 7: Click on more info link
        pageObjectManager.getHotelPage().getMoreInformationLink().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Hotel Overview is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomsPanel()));
        ValidationUtil.validateTestStep("Hotel Policies is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowOverviewPanel()));
        ValidationUtil.validateTestStep("Hotel Accomodations is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowPoliciesPanel()));
        ValidationUtil.validateTestStep("Hotel Dining is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowAmenitiesPanel()));
        ValidationUtil.validateTestStep("Hotel Activities is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowDinningPanel()));
        ValidationUtil.validateTestStep("Hotel Photos is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowPhotosPanel()));
        ValidationUtil.validateTestStep("Hotel Maps is displayed under \"More info\"",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowMapsPanel()));

//Step 8: Click on view button

        String hotelName        = pageObjectManager.getHotelPage().getHotelNamesText().get(0).getText();

        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The hotel name on the hotel pop-up is correct" , hotelName , pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelNameText().getText());

        //Select the 3 hotel Rooms
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();

        selectRoom(pageObjectManager.getHotelPage().getVacationPathSelectRoomNumber().get(0));//select Room 1
        pageObjectManager.getHotelPage().getVacationPathSelectRoomNumber().get(0).click();
        selectRoom(pageObjectManager.getHotelPage().getVacationPathSelectRoomNumber().get(2));//Select Room 2
        pageObjectManager.getHotelPage().getVacationPathSelectRoomNumber().get(2).click();
        selectRoom(pageObjectManager.getHotelPage().getVacationPathSelectRoomNumber().get(4));//Select Room 3

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);

        getDriver().findElement(By.xpath("//button[contains(text(),'Continue')]")).click();//click continue at the bottom of the page
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
    }

    private void selectRoom(WebElement selectRoomNumberButton) {
        selectRoomNumberButton.click();
        for(WebElement hotelRoom: pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton()) {
            if(hotelRoom.isDisplayed()) {
                hotelRoom.click();
                break;
            }
        }
        WaitUtil.untilTimeCompleted(2000);
    }
}