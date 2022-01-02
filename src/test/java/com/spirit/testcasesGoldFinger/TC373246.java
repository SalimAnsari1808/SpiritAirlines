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
//Test Case ID: TC373246
//Description: Task 27184: TC373246 - 004 - CP - New Car Page Feature - Flight + Car - Validate default sorting and filter settings for a standard booking
//Created By: Gabriela
//Created On: 18-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373246 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightCar", "DomesticDomestic", "Outside21Days", "Adult", "NineDFC", "NonStop", "CarsUI", "FlightAvailabilityUI"})
    public void CP_New_Car_Page_Feature_Flight_Car_Validate_default_sorting_and_filter_settings_for_a_standard_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373246 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "4";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

//- Step 8: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 1: Sign into a $9FC account
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//- Step 2: On the Home page Select the Vacation tab, specific Flight + Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Start a Vacation Flight+Car booking | DOM to DOM| outside 48h | 4 ADT | Driver's age 25 +|  Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: On the Flight + Car availability page scroll down to the Pick Your Ride section and validate the default Display Number is 20.
        ValidationUtil.validateTestStep("Validating car list size is 20",
                pageObjectManager.getCarPage().getCarsPageCarsPanel().size() == 20);

//- Step 5: Validate the default car sorting should be Sort By: Price: Low to Hight.
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

//- Step 6: Validate that Pagination displays for the cars. If cars displayed is less than 20, pagination will be suppressed
        // Invalid step. Functionality change to 'SHOW MORE' button
        double carscounter = Double.parseDouble(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace(" Cars",""));
        System.out.println("carscounter: " + carscounter);
        if (carscounter > 20)
        {
            ValidationUtil.validateTestStep("Validating 'Show More' button is displayed when more than 20 car are available to show",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageShowMoreButton()));
        }


//- Step 7: Validate that the View All link is not available at the bottom of the page
        try
        {
            ValidationUtil.validateTestStep("Validating 'View All' link is not displayed at the bottom if the page" ,
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getViewAllCarsButton()));
        }catch (Exception e){}

    }
}

/*************************** Car Page Xpath **********************************/
//*******************************************************************
//***********************Car Page************************************
//*******************************************************************

//    public final String xpath_CarPageShowMoreButton = "//button[contains(text(),'Show More')]";
//    @FindBy(xpath=xpath_CarPageShowMoreButton)
//    private WebElement btn_CarPageShowMore;

//public final String xpath_CarsPageCarCounterText = "(//app-sortable-list//div[@class='sort-list-container']//span)[1]";
//    @FindBy(xpath=xpath_CarsPageCarCounterText)
//    private WebElement txt_CarsPageCarCounter;

//public WebElement getCarsPageCarCounterText() { return txt_CarsPageCarCounter; }
//public WebElement getCarPageShowMoreButton() {return btn_CarPageShowMore;}