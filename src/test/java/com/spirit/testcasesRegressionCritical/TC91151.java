package com.spirit.testcasesRegressionCritical;

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

//**********************************************************************************************
//Test Case ID: TC91151
//Description: Vacation Path_CP_NEG_Flight-Hotel-Car_Removing 1 out of 2 Hotel rooms after selecting it
//Created By : Anthony Cardona
//Created On : 06-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-Aug-2019
//**********************************************************************************************
public class TC91151 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" ,"FlightHotelCar", "Outside21Days" , "Adult" , "Guest" ,"HotelsUI", "CarsUI"})
    public void Vacation_Path_CP_NEG_Flight_Hotel_Car_Removing_1_out_of_2_Hotel_rooms_after_selecting_it(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Passenger Info Page************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91151  under REGRESSION CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "60";
        final String ARR_DATE           = "65";
        final String ADULTS             = "2";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String HOTELROOM          = "2 Rooms";
        final String DRIVER_AGE         = "25+";

        final String UPGRADE_VALUE = "BookIt";

        final String DOB16YearOld   = TestUtil.getStringDateFormat((-5840), "MM/dd/yyyy");

        //open browser
        openBrowser(platform);
//Step 1,2 Access the test environment select vacation booking 2 adults, 2 room
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());


//Step 3: Select a room and continue to the payment page
        String hotelName;

        //Complete the flight + Hotel page
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
        //Select the 2 hotel Rooms
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomNumberButton().get(0).click();
        for(WebElement hotelRoom: pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton())
        {
            if(hotelRoom.isDisplayed())
            {
                hotelRoom.click();
                break;
            }
        }
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomNumberButton().get(0).click();
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomNumberButton().get(2).click();
        for(WebElement hotelRoom: pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton())
        {
            if(hotelRoom.isDisplayed())
            {
                hotelRoom.click();
                break;
            }
        }
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);

        hotelName   = pageObjectManager.getHotelPage().getHotelNamesText().get(0).getText();

        //Click continue at the bottom of the page
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHotelPage().getContinueButton());

//        contButton.click();//click continue at the bottom of the page
        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user taken to the Cars page" , getDriver().getCurrentUrl().contains("book/options/cars"));

//Step 4: Navigate back to the hotels Availability page
        getDriver().navigate().back();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user taken to the flight + hotel page" , getDriver().getCurrentUrl().contains("book/hotels"));

//Step 5: Validate hotel remove button
        ValidationUtil.validateTestStep("The hotel \"Remove\" button is displayed" , pageObjectManager.getHotelPage().getHotelPageRemoveButton().isDisplayed());
        ValidationUtil.validateTestStep("The hotel name is correct" , hotelName , pageObjectManager.getHotelPage().getHotelNamesText().get(0).getText());

//Step 6: Click on the remove button
        pageObjectManager.getHotelPage().getHotelPageRemoveButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The selected hotel was removed" , !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPageRemoveButton()));

//Step 7: Validate that the hotel has been removed
        ValidationUtil.validateTestStep("The user should not be able to continue" , !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getVacationPathContinueButton()));
    }
}
