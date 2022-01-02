package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91150
//Test Name:  CP_BP_Hub-Options_Car Page Wireframe
//Created By : Sunny Sok
//Created On : 05-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 09-JUL-2019
//**********************************************************************************************

public class TC91150 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"OutOfExecution", "BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" ,"CheckInOptions", "OptionsUI" , "CarsUI"})
    public void CP_BP_Hub_Options_Car_Page_Wireframe(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Option Page***************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91150 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "30";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());


        /******************************************************************************
         ***************************Validation Options Page****************************
         ******************************************************************************/
        String OPTION_URL       = "spirit.com/book/options";
        String CARS_URL         = "spirit.com/book/options/cars";
        String PAYMENT_URL      = "spirit.com/book/payment";
        String CARS_HEADER      = "Pick Your Ride";
        String BLACK            = "rgb(0, 0, 0)";
        String BOLD             = "900";
        String CARS_SUB_HEADER  = "You weren't planning to walk everywhere, were you?";
        String GREY             = "rgb(239, 239, 239, 1)";
        String SORTBY           = "Sort By: Featured";
        String DISPLAY_NUMBER   = "Display: 20";
        String BLUE             = "rgb(0, 115, 230)";
        String WHITE            = "rgb(255, 255, 255)";

        //URL Validation
        ValidationUtil.validateTestStep("Validating options Page URL", getDriver().getCurrentUrl(), OPTION_URL);

        pageObjectManager.getCarPage().getViewAllCarsButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating cars Page URL", getDriver().getCurrentUrl(), CARS_URL);

        //Verifying header
        ValidationUtil.validateTestStep("Verify Cars page Header",
                pageObjectManager.getCarPage().getCarsPagePickYourRideText().getText().trim(),CARS_HEADER);

        System.out.println("color: " +pageObjectManager.getCarPage().getCarsPagePickYourRideText().getCssValue("color"));
        ValidationUtil.validateTestStep("Verify Cars page Header is black and bold",
                pageObjectManager.getCarPage().getCarsPagePickYourRideText().getCssValue("font-Weight").contains(BOLD) && pageObjectManager.getCarPage().getCarsPagePickYourRideText().getCssValue("color").contains(BLACK));

        //Verifying Sub header
        ValidationUtil.validateTestStep("Verify Cars page Sub-Header",
                pageObjectManager.getCarPage().getCarsPageYouWerentPlanningToWalkEverywhereText().getText().trim(),CARS_SUB_HEADER);

//        //Verifying Content box below header
//        ValidationUtil.validateTestStep("Verify Content box below header",
//                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//app-sortable-list[@ng-reflect-filter-place-holder='Car name']//div[@class='card bg-light']"))));

        //Verifying Content box has grey background
//        ValidationUtil.validateTestStep("Verify Content box has grey background",
//                getDriver().findElement(By.xpath("//app-sortable-list[@ng-reflect-filter-place-holder='Car name']//div[@class='card bg-light']")).getCssValue("background-color"),GREY);

        //Verifying Car name txtbx is displayed
        ValidationUtil.validateTestStep("Verifying Car name Text Box is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarNameTextbx()));
        //Verifying Car name verbiage
        ValidationUtil.validateTestStep("Verifying Car name text box placeholder",
                pageObjectManager.getCarPage().getCarsPageCarNameTextbx().getAttribute("placeholder"),"Car name");

        //verifying sort by drpdwn is displayed
        ValidationUtil.validateTestStep("verifying sort by drop down is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageSortByDrpdwn()));

        //verifying sort by default value
        Select SortBy = new Select(pageObjectManager.getCarPage().getCarsPageSortByDrpdwn());
        ValidationUtil.validateTestStep("verifying sort by default value",
                SortBy.getFirstSelectedOption().getText(), SORTBY);

        //verifying display number drpdwn is displayed
        ValidationUtil.validateTestStep("verifying display number drop down is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageDisplayDrpdwn()));

        //Verifying Display number default value
        Select DisplayNumber = new Select(pageObjectManager.getCarPage().getCarsPageDisplayDrpdwn());
        ValidationUtil.validateTestStep("Verifying Display number default value",
                DisplayNumber.getFirstSelectedOption().getText(),DISPLAY_NUMBER);


        int Cars = pageObjectManager.getCarPage().getCarsPageCarsPanel().size();

        for(int count = 0 ;  count < Cars ; count ++) {

            //Verifying Car logo is displayed
            ValidationUtil.validateTestStep("Verifying Car logo is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().get(count)));

            //Verifying Car Rental Img is displayed
            ValidationUtil.validateTestStep("Verifying Car Rental image is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRentalImg().get(count)));

            //Verifying Car type is displayed
            ValidationUtil.validateTestStep("Verifying Car type is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarTypeText().get(count)));

            //Verifying Car Model is displayed
            ValidationUtil.validateTestStep("Verifying Car Model is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarModelText().get(count)));

            //Verifying More info link is displayed
            ValidationUtil.validateTestStep("Verifying More info link is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(count)));

            //Verifying More info link is blue
            ValidationUtil.validateTestStep("Verifying More info link is blue",
                    pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(count).getCssValue("color"),BLUE);

            //Verifying Car rental price is displayed
            ValidationUtil.validateTestStep("Verifying Car rental price is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(count)));

            //Verify excludes taxes link is displayed
            ValidationUtil.validateTestStep("Verify excludes taxes link is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageExcludesTaxesLink().get(count)));

            //Verify excludes taxes link is blue
            ValidationUtil.validateTestStep("Verify excludes taxes link is displayed",
                    pageObjectManager.getCarPage().getCarsPageExcludesTaxesLink().get(count).getCssValue("color"), BLUE);

            if ( TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageUpliftlnk().get(count))) {
                //Verify uplift is displayed
                ValidationUtil.validateTestStep("Verify uplift is displayed",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageUpliftlnk().get(count)));

                //Verify uplift circle icon is displayed
                ValidationUtil.validateTestStep("Verify uplift circle icon is displayed",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageUpliftCircleIcon().get(count)));
                //TODO verify missing pop-up after clicking uplift icon
            } else {
                System.out.println("No UPLIFT AVAILABLE");
            }

            //Verify book a car button is displayed
            ValidationUtil.validateTestStep("Verify book a car button is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageBookButton().get(count)));

            //Verify button has blue background
            ValidationUtil.validateTestStep("Verify button has blue background",
                    pageObjectManager.getCarPage().getBookCarButton().get(count).getCssValue("background-color"), BLUE);

            //Verify button has white text
            ValidationUtil.validateTestStep("Verify button has white text",
                    pageObjectManager.getCarPage().getBookCarButton().get(count).getCssValue("color"), WHITE);
        }

        //click Continue without car
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify User lands on options page
        ValidationUtil.validateTestStep("Validating options Page URL",
                getDriver().getCurrentUrl(), OPTION_URL);

        //select Checkin option
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);

        //click continue button
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //verify user lands on payment page
        ValidationUtil.validateTestStep("Validating payment Page URL",
                getDriver().getCurrentUrl(), PAYMENT_URL);
    }
}