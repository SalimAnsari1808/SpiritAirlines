package com.spirit.testcasesGoldFinger;

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

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//TODO: [IN:25231] GoldFinger R1: CP: BP: Vacation: F+H: Availability Page: Same room with the same price options to select it's duplicated on the Room Category window
//Test Case ID: TC373279
//Description: Task 27224: TC373279 - 011 - CP - Room Rate - Flight + Car + Hotel - Validate Room Rates for a booking with a single passenger and room
//Created By: Gabriela
//Created On: 15-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373279 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug","BookPath", "RoundTrip","FlightHotelCar", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "HotelsUI", "FlightAvailabilityUI"})
    public void CP_Room_Rate_Flight_Car_Hotel_Validate_Room_Rates_for_a_booking_with_a_single_passenger_and_room(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373279 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

//- Step 1: Access GoldFinger test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Create Vacation booking F+H+ C 1 ADT DOM-DOM - 1 room - drivers age 25+
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: flight should be pre selected, if unhappy with flight selection, select edit
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Departing journey is selected ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedDepatureFlightBlockPanel()));

        ValidationUtil.validateTestStep("Validating Returning journey is selected ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedArrivalFlightBlockPanel()));

        //Storing journey information for validation
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

//- Step 4: under " Choose your hotel"  you should list of hotels
        int count = 0;
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelCard().size(); i ++)
        {
          if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(i)))
          {
              count++;
          }
        }

        ValidationUtil.validateTestStep("Validating Hotel list is displayed on Flight + Hotel Availability page", count!=0);

//- Step 5: verify unable to continue without selecting hotel
        try
        {
            ValidationUtil.validateTestStep("Validating the user cannot continue without hotel selection",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getContinueWithoutHotelButton()));
        }catch (Exception e){}

//- Step 6: Click "SELECT ROOM" button
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton());

//- Step 7: validate same room types have different prices and the reason why room rates are different ( whats included)
        List<WebElement> priceList = getDriver().findElements(By.xpath("//div[@id='roomSelect-panel']//div[@class='card-body row']//span[2]"));
        List<WebElement> nameList = getDriver().findElements(By.xpath("//div[@id='roomSelect-panel']//div[@class='card-body row']//span[1]"));
        List<String> roomName = new ArrayList<>();
        List<String> roomPrice = new ArrayList<>();

        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@id='roomSelect-panel']//div[@class='card-body row']")).size(); i ++)
        {
            roomPrice.add(priceList.get(i).getText());
            roomName.add(nameList.get(i).getText());
        }
        int count1 = 1;
        for (int i = 0; i < roomName.size(); i ++)
        {
            for (int e = count1 ; e < roomName.size(); e++)
            {
                if (roomName.get(i).equals(roomName.get(e))) {
                    //TODO: [IN:25231] GoldFinger R1: CP: BP: Vacation: F+H: Availability Page: Same room with the same price options to select it's duplicated on the Room Category window
                    ValidationUtil.validateTestStep("Comparing " + roomName.get(i) + " with price " + roomPrice.get(i) + " and " + roomName.get(e) + " e with price " + roomPrice.get(e),
                            !roomPrice.get(i).equals(roomPrice.get(e)));
                }

            } count1++;
        }

//- Step 8: access HBG webiste
        //TODO: Method to validate needs to be implemented
//- Step 9: input same city pair and pax info select search
        //TODO: Method to validate needs to be implemented
//- Step 10: select same hotel and room and verify info are matching
        //TODO: Method to validate needs to be implemented
    }
}