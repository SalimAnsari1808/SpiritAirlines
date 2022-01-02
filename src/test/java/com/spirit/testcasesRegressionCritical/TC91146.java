package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91146
//Description: Vacation Path_CP_Flight-Hotel-Car_NEG_Booking without hotel
//Created By : Anthony Cardona
//Created On : 06-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-Aug-2019
//**********************************************************************************************
public class TC91146 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"BookPath", "DomesticDomestic", "RoundTrip", "FlightHotelCar", "Outside21Days","Adult","Guest",  "HotelsUI","BookIt"})

    public void Vacation_Path_CP_Flight_Hotel_Car_NEG_Booking_without_hotel(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Passenger Info Page************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91146  under REGRESSION CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Vacation";
        final String TRIP_TYPE              = "Flight+Hotel+car";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "60";
        final String ARR_DATE               = "65";
        final String ADULTS                 = "3";
        final String CHILDS                 = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        final String HOTELROOM              = "1 Room";
        final String DRIVER_AGE 		    = "25+";

        final String UPGRADE_VALUE          = "BookIt";

        //open browser
        openBrowser(platform);
//Step 1,2,3: Access the test environment select vacation booking 3 adults, 1 room
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

//Step 4: Select Hotel and store hote information
        String hotelName;
        String flighPlusHotelPrice;

        //Complete the flight + Hotel page
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
        flighPlusHotelPrice  = pageObjectManager.getHotelPage().getHotelWindowRoomPricesButton().getText().replace("ROOMS FROM " , "").trim();
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        hotelName  = pageObjectManager.getHotelPage().getHotelNamesText().get(0).getText();

        pageObjectManager.getHotelPage().getContinueButton().click();//click continue at the bottom of the page
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        //select upgrade value
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user taken to the Cars page" , getDriver().getCurrentUrl().contains("book/options/cars"));

//Step 5: User navigates back one page tothe hotels page
        getDriver().navigate().back();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user taken to the flight + hotel page" , getDriver().getCurrentUrl().contains("book/hotels"));

//Step 6: Validate hotel remove button
        ValidationUtil.validateTestStep("The hotel \"Remove\" button is displayed" , pageObjectManager.getHotelPage().getHotelPageRemoveButton().isDisplayed());
        ValidationUtil.validateTestStep("The hotel name is correct" , hotelName , pageObjectManager.getHotelPage().getHotelNamesText().get(0).getText());
        ValidationUtil.validateTestStep("The hotel price is correct" , flighPlusHotelPrice , pageObjectManager.getHotelPage().getSelectedHotelTotalPrice().getText().replace("Total " , "").trim());

//Step 7: Click on the remove button
        pageObjectManager.getHotelPage().getHotelPageRemoveButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

//Step 8: Validate that the hotel has been removed
        ValidationUtil.validateTestStep("The selected hotel was removed" , !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPageRemoveButton()));

//Step 9: Validate user should not be able to continue
        ValidationUtil.validateTestStep("The user should not be able to continue" , !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getVacationPathContinueButton()));
    }

}