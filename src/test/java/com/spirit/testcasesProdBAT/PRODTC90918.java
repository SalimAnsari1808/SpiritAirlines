package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90918
//Description: Task 24871: 35298 Seats Page_CP_BP-CI-MT_Generic Wireframe Validation (Thrills Combo)
//Created By: Gabriela
//Created On: 27-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 28-Jun-2019
//**********************************************************************************************

public class PRODTC90918 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups="Production")

    public void Seats_Page_CP_BP_CI_MT_Generic_Wireframe_Validation_Thrills_Combo(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90918 under PRODUCTION Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "BWI";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "6";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Seats Page constant Values
        final String SEAT_URL           = "book/seats";
        final String HEADER_TEXT        = "Choose Your Seat";
        final String SEAT_TOTAL         = "SEATS TOTAL";
        final String SEAT               = "Standard";
        final String BLACK_RGB          = "rgba(0, 0, 0, 1)";
        final String BLACK_RGB_MAC      = "rgb(0, 0, 0)";
        final String WHITE_RGB          = "rgba(255, 255, 255, 1)";
        final String WHITE_RGB_MAC      = "rgb(255, 255, 255)";
        final String BLUE_RGB           = "rgba(0, 115, 230, 1)";
        final String BLUE_RGB_MAC       = "rgba(0, 115, 230)";

        //open browser
        openBrowser(platform);

//-- Step 1: Continue to Seats Page
        /******************************************************************************
         ***************************Navigate to Seats Page********************
         ******************************************************************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("RET", DEP_FLIGHT);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        String DEP_CITY_INFO = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText();
        String RET_CITY_INFO = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText();

        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /******************************************************************************
         ***************************Seats Page Validations*****************************
         ******************************************************************************/
        ValidationUtil.validateTestStep("Validating Seats Page  is on the right URL", getDriver().getCurrentUrl(),SEAT_URL);

//-- Step 2: Navigate to Page Title
        ValidationUtil.validateTestStep("Validating the right title",
                pageObjectManager.getSeatsPage().getSeatPageHeaderText().getText(),HEADER_TEXT);

//-- Step 3: Navigate to Flight Content Block
        ValidationUtil.validateTestStep("Validating Departing city info",
                pageObjectManager.getSeatsPage().getFlightLegsText().get(0).getText(),DEP_CITY_INFO);

        ValidationUtil.validateTestStep("Validating first leg is active by default",
                pageObjectManager.getSeatsPage().getPassengerNameText().get(0).isDisplayed());

        ValidationUtil.validateTestStep("Validating Returning city info",
                pageObjectManager.getSeatsPage().getFlightLegsText().get(1).getText(),RET_CITY_INFO);

        for (WebElement element : pageObjectManager.getSeatsPage().getPassengerSeatText())
        {
            if (element.isDisplayed())
            {
                String tempText = element.getText() ;
                ValidationUtil.validateTestStep("The Seat number field is empty" ,
                        "" , tempText);
            }
        }

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);

        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getSeatsPage().getPassengerDetailListLink().get(0).click();

        for (WebElement element : pageObjectManager.getSeatsPage().getPassengerSeatText())
        {
            if (element.isDisplayed())
            {
                String tempText = element.getText() ;
                ValidationUtil.validateTestStep("The Seat number field is empty" ,
                        tempText.length() > 0);
            }
        }

//-- Step 4: Navigate to Seat Total Content Block
        ValidationUtil.validateTestStep("Validating Total Content Block is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatsTotalText()));
        ValidationUtil.validateTestStep("Validating right content block text",
                pageObjectManager.getSeatsPage().getSeatsTotalText().getText(),SEAT_TOTAL);

        ValidationUtil.validateTestStep("Validating price is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatsTotalPriceText()));

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getSeatsPage().getSeatsTotalContainerLink().click();

        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("Validating seats prices are displayed on the total breakdown for departure leg",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText()));

        ValidationUtil.validateTestStep("Validating seats prices are displayed on the total breakdown for returning leg",
                TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText()));

//-- Step 5: Navigate to ‘Continue’ and ‘Continue Without Selecting Seats’ Action Button
        ValidationUtil.validateTestStep("Validating Continue With Seat button is displayed",
                pageObjectManager.getSeatsPage().getContinueWithSeatButton().get(0).isDisplayed());

        try{
            ValidationUtil.validateTestStep("Validating Continue without seat button is not displayed after select Bundle option",
                    !pageObjectManager.getSeatsPage().getContinueWithoutSeatButton().get(0).isDisplayed());
        }catch (Exception e){}

        String seatTotalPanelRGBValue = pageObjectManager.getSeatsPage().getSeatTotalPanel().getCssValue("color");
        String seatTotalTextRGBValue = pageObjectManager.getSeatsPage().getSeatsTotalText().getCssValue("color");

        ValidationUtil.validateTestStep("Validating Seat Total Panel Color is Black",seatTotalPanelRGBValue.contains(BLACK_RGB) || seatTotalPanelRGBValue.contains(BLACK_RGB_MAC));
        ValidationUtil.validateTestStep("Validating Seat Total Text Color is White",seatTotalTextRGBValue.contains(WHITE_RGB) || seatTotalTextRGBValue.contains(WHITE_RGB_MAC));

        ValidationUtil.validateTestStep("",!TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton()));

        String continueButtonTextRGBValue = pageObjectManager.getSeatsPage().getContinueWithSeatButton().get(0).getCssValue("color");
        String continueButtonBackgroundRGBValue = pageObjectManager.getSeatsPage().getContinueWithSeatButton().get(0).getCssValue("background-color");

        ValidationUtil.validateTestStep("Validating Continue button Text Color is White",continueButtonTextRGBValue.contains(WHITE_RGB) ||continueButtonTextRGBValue.contains(WHITE_RGB_MAC) );
        ValidationUtil.validateTestStep("Validating Background Color is Blue",continueButtonBackgroundRGBValue.contains(BLUE_RGB) || continueButtonBackgroundRGBValue.contains(BLUE_RGB_MAC));

//-- Step 6: Navigate to Seat Map // Invalid Steps
    }
}