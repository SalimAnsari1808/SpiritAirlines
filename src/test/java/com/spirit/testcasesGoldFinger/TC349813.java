package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC349813
//Description: Task 28171: TC349813 - 001 - CP - Flight + Car - Availability Page - Validate Sections are displaying
//Created By: Gabriela
//Created On: 2-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC349813 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "FlightCar", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "CarsUI"})
    public void CP_Flight_Car_Availability_Page_Validate_Sections_are_displaying(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349813 under GoldFinger Suite on " + platform + " Browser", true);
        }

        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

//- Step 1: Access test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Start booking a Vacation [Flight + Car], 2 ADT, outside of 90 days
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Left aligned, validate there is a Filter section displaying:
    //Price
        ValidationUtil.validateTestStep("Validating Price filter is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getPriceFilterToPriceText()));
    //Seats
        ValidationUtil.validateTestStep("Validating Seats filter is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilterLabel()));
    //Bags
        ValidationUtil.validateTestStep("Validating Bags filter is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getBagsFilterLabel()));
    //Car Type
        ValidationUtil.validateTestStep("Validating Car Type filter is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarTypeFilterLabel()));
    //Rental Agency
        ValidationUtil.validateTestStep("Validating Rental Agency filter is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getRentalAgencyFilterLabel()));
    //Car Options
        ValidationUtil.validateTestStep("Validating Car Options filter is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarOptionsFilterLabel()));

//- Step 4: Next to the filter, validate there is a car count formatted "# cars".
        ValidationUtil.validateTestStep("Validating Car Count information is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCounterText()));

//- Step 5: Right aligned, validate there is a Sort by section with the following options:
    //Recommended
        ValidationUtil.validateTestStep("Validating Sort By Recommended option is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByRecommendedButton()));
    //Price
        ValidationUtil.validateTestStep("Validating Sort By Price option is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByPriceButton()));
    //Seats
        ValidationUtil.validateTestStep("Validating Sort By Seats option is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortBySeatsButton()));
    //Car Type
        ValidationUtil.validateTestStep("Validating Sort By Car Type option is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByCarTypeButton()));

//- Step 6: Verify Car Vendor Banner is NOT displayed
        //Invalid Step

//- Step 7: Moving down, validate there is a list of 20 car tiles displaying.
        int carCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace(" Cars",""));
        if (carCount >= 20) {
            ValidationUtil.validateTestStep("There is only 20 defaulted cars displayed on the availability page",
                    pageObjectManager.getCarPage().getCarsPageCarsPanel().size() == 20);
//- Step 8: Scroll down to the end of the page and validate there is a SHOW MORE link, when clicked additional 20 car tiles are added to the original list
            ValidationUtil.validateTestStep("There is \"Show More\" button on the availability page for more than 20 car tiles",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageShowMoreButton()));
        }

        else
        {
            ValidationUtil.validateTestStep("There is no \"Show More\" button on the hotel name",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageShowMoreButton()));
        }

//- Step 9: Click SHOW MORE, validate a quick fade will replace the link acknowledging the request. Repeat the process until the SHOW MORE no longer shows
        pageObjectManager.getCarPage().getCarPageShowMoreButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating more than 20 cars are displayed after click on 'Show More' button", pageObjectManager.getCarPage().getCarsPageCarsPanel().size() >= 20 );

        ValidationUtil.validateTestStep("Validating 'Show More' button is not more displayed", !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageShowMoreButton()));
    }
}