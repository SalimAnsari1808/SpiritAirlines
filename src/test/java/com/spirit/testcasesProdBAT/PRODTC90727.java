package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC90727
//Description : Dynamic Shopping Cart Flights Caret Dropdown WireFrame
//Created By : Sunny Sok
//Created On : 26-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-JUL-2019
//**********************************************************************************************

public class PRODTC90727 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups="Production")

    public void Dynamic_Shopping_Cart_Flights_Caret_Dropdown_WireFrame(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Passenger Info Page************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90727 under PRODUCTION Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "OneWay";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "MCO";
        final String NEW_ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE 			    = "1";
        final String ARR_DATE 			    = "NA";
        final String ADULTS				    = "1";
        final String CHILDS				    = "0";
        final String INFANT_LAP			    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "9DFC";
        final String FARE_TYPE			    = "Standard";
        final String UPGRADE_FARE		    = "BookIt";

        //open browser
        openBrowser( platform);

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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        /******************************************************************************
         *********************Validation Passenger Info Page***************************
         ******************************************************************************/

        //Constant Values to validate
        final String PASSENGER_URL             = "book/passenger";
        final String FA_URL                    = "book/flights";
        final String BACKGROUND_GRAY           = "rgba(239, 239, 239, 1)";
        final String BACKGROUND_YELLOW         = "rgba(255, 236, 0, 1)";
        final String FONT_BLACK                = "rgba(0, 0, 0, 1)";
        final String SAVEUPTO_PRICE            = scenarioContext.getContext(Context.AVAILABILITY_9DFC_SAVEUPTO_PRICE).toString().replace("SAVE","").replace("TOTAL","").trim();
        final String JOIN_SAVE_TEXT            = "JOIN $9 FARE CLUB AND SAVE " + SAVEUPTO_PRICE + "!";
        final String ARE_YOU_SURE_HEAD_TEXT    = "Are you sure?";
        final String ARE_YOU_SURE_BODY_TEXT    = "Are you sure you want to go back to the flight selection page?\n" + "You will lose your current booking and will have to re-enter information.";
        final String CONTINUE_TO_FLIGHTS       = "CONTINUE TO FLIGHTS";

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Passenger Info page",
                getDriver().getCurrentUrl(), PASSENGER_URL);

        //Validate Shopping Cart is present
        ValidationUtil.validateTestStep("Verify user Shopping Cart is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));

        //Verify A Grey text block, left aligned icon of cart followed by verbiage in black font: Your Itinerary and right aligned $XXX (dynamic pricing).
        ValidationUtil.validateTestStep("Verify A Grey text block",
                getDriver().findElement(By.xpath("//div[contains(@class,'total-cost')]")).getCssValue("background-color"),BACKGROUND_GRAY);

        ValidationUtil.validateTestStep("Verify Your Itinerary is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getYourItineraryText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryTotalAmountText()));

        ValidationUtil.validateTestStep("Verify Caret is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowYourItineraryImage()));

        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //verify Icon of an airplane
        ValidationUtil.validateTestStep("verify Icon of an airplane is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryImage()));

        //verify flight label
        ValidationUtil.validateTestStep("verify flight label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryText()));

        //verify flight dynamic pricing
        ValidationUtil.validateTestStep("verify flight label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightPriceItineraryText()));
        //verify arrow
        ValidationUtil.validateTestStep("verify arrow  is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowFlightItineraryImage()));

        pageObjectManager.getHeader().getArrowFlightItineraryImage().click();

        //verify date format
        Date date = TestUtil.convertStringToDate(pageObjectManager.getHeader().getFlightDateItineraryText().get(0).getText(),"E, MMM d, YYYY");
        ValidationUtil.validateTestStep("Verify date format", !(date==null));

        //verify city pair
        ValidationUtil.validateTestStep("verify city pair",
                DEP_AIRPORT_CODE + " - " + ARR_AIRPORT_CODE, pageObjectManager.getHeader().getAirportFlightItineraryText().get(0).getText());

        //verify Edit link(blue font)
        ValidationUtil.validateTestStep("verify Edit link(blue font) is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getEditFlightItineraryButton())
                        && pageObjectManager.getHeader().getEditFlightItineraryButton().getCssValue("color").equals("rgba(0, 115, 230, 1)"));

        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

        //verify $9FC tile, yellow background, with black font, left aligned verbiage: JOIN $9FC AND SAVE
        //$XXX! (this amount is dynamic) right aligned down button / caret.
        ValidationUtil.validateTestStep("Verify yellow background",
                getDriver().findElement(By.xpath("//div[contains(@class,'flight-club-savings')]")).getCssValue("background-color"),BACKGROUND_YELLOW);

        ValidationUtil.validateTestStep("Verify Font is black",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getCssValue("color"), FONT_BLACK);

        ValidationUtil.validateTestStep("Verify verbiage: JOIN AND SAVE (link)",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getText(), JOIN_SAVE_TEXT);

        //Click the Edit link
        pageObjectManager.getHeader().getEditFlightItineraryButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //verify Are you sure popup is displayed
        ValidationUtil.validateTestStep("verify Are you sure popup is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getAreYouSurePopUpPanel()));
        //verify header of popup
        ValidationUtil.validateTestStep("verify header of Are you sure popup",
                pageObjectManager.getPaymentPage().getAreYouSurePopUpHeaderText().getText(), ARE_YOU_SURE_HEAD_TEXT);

        //verify body of popup
        ValidationUtil.validateTestStep("verify Body of Are you sure popup",
                pageObjectManager.getPaymentPage().getAreYouSurePopUpBodyText().getText(), ARE_YOU_SURE_BODY_TEXT);
        System.out.println(pageObjectManager.getPaymentPage().getAreYouSurePopUpContinueToFlightButton().getText());
        //verify button of popup
        ValidationUtil.validateTestStep("verify Body of Are you sure popup",
                pageObjectManager.getPaymentPage().getAreYouSurePopUpContinueToFlightButton().getText(), CONTINUE_TO_FLIGHTS);

        //click button on popup
        pageObjectManager.getPaymentPage().getAreYouSurePopUpContinueToFlightButton().click();

        //Verify user lands on Flight Availability page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Fight Availability page",
                getDriver().getCurrentUrl(), FA_URL);

        //On the Flight Availability page, verify that the previous booking selections have been wiped and you can make a new flight selection.
        //Selecting new city pair to verify shopping cart is updated
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, NEW_ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Passenger Info page",
                getDriver().getCurrentUrl(), PASSENGER_URL);

        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        pageObjectManager.getHeader().getArrowFlightItineraryImage().click();

        //verify city pair
        ValidationUtil.validateTestStep("verify NEW POO – POD",
                DEP_AIRPORT_CODE + " - " + NEW_ARR_AIRPORT_CODE,
                pageObjectManager.getHeader().getAirportFlightItineraryText().get(0).getText());
    }
}