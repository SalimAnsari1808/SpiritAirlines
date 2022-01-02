package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373247
//Description: Task 27185: TC373247 - 007 - CP - New Car Page Feature - Flight + Hotel + Car - Validate default sorting and filter settings for a standard booking
//Created By: Gabriela
//Created On: 18-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373247 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightHotelCar", "DomesticDomestic", "Outside21Days", "Adult","Child","InfantSeat", "Guest", "NonStop", "CarsUI"})
    public void CP_New_Car_Page_Feature_Flight_Hotel_Car_Validate_default_sorting_and_filter_settings_for_a_standard_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373247 under GoldFinger Suite on " + platform + " Browser", true);
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
        final String ADULT              = "2";
        final String CHILD              = "1";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Car Page Constant Values
        final String CAR_PAGE           = "book/options/cars";

//- Step 12: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 1: On the Home page Select the Vacation tab, specific Flight + Hotel + Car.
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: Start a Vacation Flight + Hotel + Car booking | DOM to DOM| outside 48h | 2 ADT + 1 Lap + 1 Child | Driver's age 25 +|  1 Room | Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Input the age for the Lap and the child passengers
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 4: On the First available Hotel select the SELECT ROOM button
//- Step 5: Select the button Rooms From $##.##
//- Step 6: Click the Select Room button on the first available Room
        //Step 4 to 6
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

//- Step 7: Validate that Car options are available
        ValidationUtil.validateTestStep("Validating Cars Page is on the right URL",
                getDriver().getCurrentUrl(),CAR_PAGE);

        ValidationUtil.validateTestStep("Validating car options are available",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageBookButton()));

//- Step 8: On Cars availability page validate that the default Display Number is 20.
        ValidationUtil.validateTestStep("Validating car list size is 20",
                pageObjectManager.getCarPage().getCarsPageCarsPanel().size() == 20);

//- Step 9: Validate that Pagination displays for the cars. If cars displayed is less than 20, pagination will be suppressed
        // Invalid step. Functionality change to 'SHOW MORE' button
        double carsCounter = Double.parseDouble(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace(" Cars",""));
        System.out.println("carscounter: " + carsCounter);
        if (carsCounter > 20)
        {
            ValidationUtil.validateTestStep("Validating 'Show More' button is displayed when more than 20 car are available to show",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageShowMoreButton()));
        }

//- Step 10: Validate the default car sorting should be Sort By: Price: Low to High.
        List<Double> carPrice = new ArrayList<>();
        String carPriceText = "";
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i++)
        {
            carPriceText = pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i).getText().replace("$","");
            carPrice.add(Double.parseDouble(carPriceText));
        }
        System.out.println("carPrice: " + carPrice );

        boolean carPriceFlag = true;
        for (int i = 0; i < carPrice.size(); i ++)
        {
            if (i==carPrice.size()-1)
            {
                break;
            }
            if (carPrice.get(i)>carPrice.get(i+1))
            {
                carPriceFlag = false;
            }
        }

        ValidationUtil.validateTestStep("Validating the default car sorting should be Sorted By price Low to Hight.",
                carPriceFlag);

//- Step 11: Validate that the View All link is not available at the bottom of the page
        try
        {
            ValidationUtil.validateTestStep("Validating 'View All' link is not displayed at the bottom if the page" ,
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getViewAllCarsButton()));
        }catch (Exception e){}

    }
}