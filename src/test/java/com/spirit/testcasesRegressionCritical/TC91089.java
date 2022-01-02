package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91089
//Description: Flight Availability_CP_BP_Vacation Flight + Hotel + Car_Estimated Car Taxes and Fees Popup
//Created By: Anthony Cardona
//Created On: 1-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 5-Aug-2019
//**********************************************************************************************

public class TC91089 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "FlightHotelCar" , "DomesticDomestic" , "Outside21Days" , "Adult" ,"Guest", "BookIt" , "CarsUI" ,"FlightAvailabilityUI","HotelsUI"})
    public void Flight_Availability_CP_BP_Vacation_Flight_Hotel_Car_Estimated_Car_Taxes_and_Fees_Popup(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91089 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "91";
        final String ARR_DATE           = "94";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        final String UPGRADE_VALUE      = "BookIt";


//Step 1: Start Vacation booking on test environment
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        try {
            pageObjectManager.getHotelPage().getAllHotelsButton().click();//click on all hotels if the option is available
            WaitUtil.untilTimeCompleted(1200);
        }
        catch (Exception ee){}


        int USUABLE_HOTELCARD = 0;

        final String STRATOSPHERE_HOTEL_NAME       = "STRATOSPHERE CASINO, HOTEL & TOWER";
        final String THE_D_HOTEL_NAME              = "THE D LAS VEGAS";
        final String LUXOR_HOTEL_NAME              = "LUXOR HOTEL AND CASINO";

        int counter = 0;

        //Only the 3 listed hotels can be selected. the below loop will set USABLE_HOTELCARD to the correct int to use in the lists
        for(WebElement HotelName: pageObjectManager.getHotelPage().getHotelNamesText()) {
            if (HotelName.getText().contains(LUXOR_HOTEL_NAME)
                    || HotelName.getText().contains(THE_D_HOTEL_NAME)
                    || HotelName.getText().contains(STRATOSPHERE_HOTEL_NAME)) {
                USUABLE_HOTELCARD = counter;
                break;
            }
            counter ++;
        }

        pageObjectManager.getHotelPage().getViewAllHotelCardViewLink().get(USUABLE_HOTELCARD).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2500);

        pageObjectManager.getHotelPage().getHotelWindowRoomPricesButton().click();
        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();
        pageObjectManager.getHotelPage().getVacationPathContinueButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        /******************************************************************************
         ***************************Validation Cars Page*******************************
         ******************************************************************************/
        String CARS_URL         = "spirit.com/book/options/cars";
        String PAYMENT_URL      = "spirit.com/book/payment";
        String CARS_HEADER      = "Pick Your Ride";
        String BLACK            = "rgba(0, 0, 0, 1)";
        String BOLD             = "900";
        String CARS_SUB_HEADER  = "You weren't planning to walk everywhere, were you?";
        String GREY             = "rgba(239, 239, 239, 1)";
        String SORTBY           = "Sort By: Featured";
        String DISPLAY_NUMBER   = "Display: All";
        String BLUE             = "rgba(0, 115, 230, 1)";
        String WHITE            = "rgba(255, 255, 255, 1)";

//Step 2 : Validate user lands on the cars page page
        //URL Validation
        ValidationUtil.validateTestStep("Validating cars Page URL", getDriver().getCurrentUrl(),(CARS_URL));

        //Verifying header
        ValidationUtil.validateTestStep("Verify Cars page Header",
                pageObjectManager.getCarPage().getCarsPagePickYourRideText().getText().trim(),CARS_HEADER);

        ValidationUtil.validateTestStep("Verify Cars page Header is black and bold",
                pageObjectManager.getCarPage().getCarsPagePickYourRideText().getCssValue("font-Weight").equals(BOLD)
                        && pageObjectManager.getCarPage().getCarsPagePickYourRideText().getCssValue("color").equals(BLACK));

//Step 3: Verify subheader
        //Verifying Sub header
        ValidationUtil.validateTestStep("Verify Cars page Sub-Header",
                pageObjectManager.getCarPage().getCarsPageYouWerentPlanningToWalkEverywhereText().getText().trim(),CARS_SUB_HEADER);

//Step 4: Validate Filter Cars Section
        //Verifying Car name TextBox is displayed
        ValidationUtil.validateTestStep("Verifying Car name txtbx is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarNameTextbx()));
        //Verifying Car name verbiage
        ValidationUtil.validateTestStep("Verifying Car name txtbx placeholder",
                pageObjectManager.getCarPage().getCarsPageCarNameTextbx().getAttribute("placeholder"),"Car name");

        //verifying sort by drpdwn is displayed
        ValidationUtil.validateTestStep("verifying sort by drpdwn is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageSortByDrpdwn()));

        //verifying sort by default value
        Select SortBy = new Select(pageObjectManager.getCarPage().getCarsPageSortByDrpdwn());
        ValidationUtil.validateTestStep("verifying sort by default value",
                SortBy.getFirstSelectedOption().getText(), SORTBY);

        //verifying display number drpdwn is displayed
        ValidationUtil.validateTestStep("verifying display number drpdwn is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageDisplayDrpdwn()));

        //Verifying Display number default value
        Select DisplayNumber = new Select(pageObjectManager.getCarPage().getCarsPageDisplayDrpdwn());
        ValidationUtil.validateTestStep("Verifying Display number default value",
                DisplayNumber.getFirstSelectedOption().getText(),DISPLAY_NUMBER);

//Step 5 and 6 validate car content block
        int Cars = pageObjectManager.getCarPage().getCarsPageCarsPanel().size();

        for(int count = 0 ;  count < Cars ; count ++) {

            //Verifying Car logo is displayed
            ValidationUtil.validateTestStep("Verifying Car logo is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().get(count)));

            //Verifying Car Rental Img is displayed
            ValidationUtil.validateTestStep("Verifying Car Rental Img is displayed",
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

                //Verify uplift cicle icon is displayed
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

//Step 7: Excludes Taxes link validation
        try{pageObjectManager.getCarPage().getCarsPageExcludesTaxesLink().get(0).click();}
        catch(Exception e){}

        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(1500);

        ValidationUtil.validateTestStep("The Estimated Tax Header is correct",
                pageObjectManager.getCarPage().getEstimatedTaxesPopUpHeaderText().getText() , "Estimated Car Taxes and Fees");
        ValidationUtil.validateTestStep("The Estimated Tax body text is displayed",
                pageObjectManager.getCarPage().getEstimatedTaxesBodyText().getText(),
                ("Taxes and fees for your car rental will be collected" +
                        " at the car rental counter and the following is an estimate of the amounts involved."));
        ValidationUtil.validateTestStep("The Tax is displayed",
                pageObjectManager.getCarPage().getEstimatedTaxesBodyText().getText(),("TAX: "));
        ValidationUtil.validateTestStep("The Miscellaneous APF Fee is displayed",
                pageObjectManager.getCarPage().getEstimatedTaxesBodyText().getText(),("MISCELLANEOUS APF FEE: "));
        ValidationUtil.validateTestStep("The Customer Facility Charge is displayed",
                pageObjectManager.getCarPage().getEstimatedTaxesBodyText().getText(),("CUSTOMER FACILITY CHARGE: "));
        ValidationUtil.validateTestStep("The Miscellaneous VLF Fee is displayed",
                pageObjectManager.getCarPage().getEstimatedTaxesBodyText().getText(),("MISCELLANEOUS VLF FEE: "));
        ValidationUtil.validateTestStep("The Estimated Car Taxes and Fees is displayed",
                pageObjectManager.getCarPage().getEstimatedTaxesBodyText().getText(),("ESTIMATED CAR TAXES AND FEES:"));

        ValidationUtil.validateTestStep("The close button is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getEstimatedTaxesPopUpCloseButton()));
        pageObjectManager.getCarPage().getEstimatedTaxesPopUpCloseButton().click();

        WaitUtil.untilTimeCompleted(2500);

//Step 8: Close the Taxes Pop-Up
        pageObjectManager.getCarPage().getBookCarButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 9: Click on Book car and validate user is taken to the passenger page
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("The user redirected to the Passenger page",
                getDriver().getCurrentUrl(),PAYMENT_URL);
    }
}