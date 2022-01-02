package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC349800
//Description: Task 28163: TC349800 - 002 - CP - Flight Only - Car Page - Validate Sections are displaying
//Created By: Gabriela
//Created On: 3-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC349800 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt",
            "CarsUI", "NoBags", "NoSeats", "OptionalUI"})
    public void CP_Flight_Only_Car_Page_Validate_Sections_are_displaying(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349800 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
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
        final String OPTIONS_PAGE       = "/book/options";
        final String PAGE_HEADER        = "Pick Your Ride";

//- Step 1: Access test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Start booking a flight only RT DOM Nonstop for 1 ADT outside 7 days
        /*** Home Page Methods **/
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Continue with the booking until you reach the Options Page.
        /*** Flight Availability Page Methods **/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        /*** Passenger Information Page Methods **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        /*** Bags Page Methods **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        /*** Seats Page Methods **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();


//- Step 4: When on the Options page, locate the car carousel and click the VIEW ALL CARS link
        /*** Options Page Methods **/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(OPTIONS_PAGE));

        pageObjectManager.getCarPage().getViewAllCarsButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 5: Verify there is a page header and it matches the screenshot
        ValidationUtil.validateTestStep("Validating page header displaying right information",
                pageObjectManager.getCarPage().getCarsPagePickYourRideText().getText(),PAGE_HEADER);

//- Step 6: Left aligned, validate there is a Filter section displaying:
        //Price
        ValidationUtil.validateTestStep("Validating Price filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getPriceFilterLabel()));
        //Seats
        ValidationUtil.validateTestStep("Validating Seats filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilterLabel()));
        //Bags
        ValidationUtil.validateTestStep("Validating Bags filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getBagsFilterLabel()));
        //Car Type
        ValidationUtil.validateTestStep("Validating Car Type filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarTypeFilterLabel()));
        //Rental Agency
        ValidationUtil.validateTestStep("Validating Rental Agency filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getRentalAgencyFilterLabel()));
        //Car Options
        ValidationUtil.validateTestStep("Validating Car Options filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarOptionsFilterLabel()));

//- Step 7:Next to the filter, validate there is a car count formatted "# cars".
        ValidationUtil.validateTestStep("Validating there is a car count ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCounterText()));

//- Step 8: Right aligned, validate there is a Sort by section with the following options:
        //RECOMMENDED
        ValidationUtil.validateTestStep("Validating Sorting By Recommended option is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByRecommendedButton()));
        //PRICE
        ValidationUtil.validateTestStep("Validating Sorting By Price option is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByPriceButton()));
        //SEATS
        ValidationUtil.validateTestStep("Validating Sorting By Seats option is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortBySeatsButton()));
        //CAR TYPE
        ValidationUtil.validateTestStep("Validating Sorting By Car Type option is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByCarTypeButton()));

//- Step 9: Verify Car Vendor Banner is NOT displayed
        //Invalid Step

//- Step 10: Moving down, validate there is a list of 20 car tiles displaying.
        int carCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace(" Cars",""));
        if (carCount >= 20) {
            ValidationUtil.validateTestStep("There is only 20 defaulted cars displayed on the availability page",
                    pageObjectManager.getCarPage().getCarsPageCarsPanel().size() == 20);
//- Step 11: Scroll down to the end of the page and validate there is a SHOW MORE link, when clicked additional 20 car tiles are added to the original list
            ValidationUtil.validateTestStep("There is \"Show More\" button on the availability page for more than 20 car tiles",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageShowMoreButton()));
        }

        else
        {
//- Step 12: Click SHOW MORE, validate a quick fade will replace the link acknowledging the request. Repeat the process until the SHOW MORE no longer shows
            ValidationUtil.validateTestStep("There is no \"Show More\" button on the hotel name",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageShowMoreButton()));
        }

//- Step 13: Validate there is a CONTINUE WITHOUT A CAR button that takes you back to the Options page when clicked.
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(OPTIONS_PAGE));
    }
}