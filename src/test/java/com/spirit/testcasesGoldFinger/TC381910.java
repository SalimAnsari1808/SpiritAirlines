package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//*********************************************************************************************
//Test Case ID: TC381910
//Description: Task 27853: TC381910- 007 - CP - Flight Flex Packaging Verbiage - Flight + Hotel + Car - Validate verbiage for Flight Flex on the Thrills Pop-Up
//Created By: Gabriela
//Created On: 24-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 06-Dec-2019
//**********************************************************************************************

public class TC381910 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest", "FlightAvailabilityUI"})
    public void  CP_Flight_Flex_Packaging_Verbiage_Flight_Hotel_Car_Validate_verbiage_for_Flight_Flex_on_the_Thrills_Pop_Up(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC381910 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        final String ROOMS_VALUE        = "2 Rooms";

        //Flight Availability Page Constant Values
        final String F_H_PAGE           = "/book/hotels";
        final String F_C_PAGE           = "/book/options/cars";


//- Step 1: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        //*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Click on Vacation tab and keep the radio button defaulted to Flight + Hotel + Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Input the following: DOM_DOM | Any Date 3 months in the future | 5 PAX | 2 Room | Driver 25+ and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(F_H_PAGE));

//- Step 4: Select any hotel and proceed options/cars page
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(F_C_PAGE));

//- Step 5: Book any car by clicking BOOK CAR
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 6: Click CONTINUE at the bottom for the cars page
        //Invalid Step

//- Step 7: Verify the Bundle It verbiage contains:
        boolean Flag = false;

        for(WebElement bundleItBenfits : pageObjectManager.getFlightAvailabilityPage().getBundleTileBenefitsList()){
            if(bundleItBenfits.isDisplayed()){
                if(bundleItBenfits.getText().equalsIgnoreCase("Flight Flex - Modify your flight once for free! (fare difference may apply)")){
                    Flag = true;
                }
            }
        }

        ValidationUtil.validateTestStep("Verify the Flight Flex - Modify your flight once for free! (fare difference may apply) verbiage in the Bundle it section of the pop up on Flight Availability Page", Flag);

    }
}