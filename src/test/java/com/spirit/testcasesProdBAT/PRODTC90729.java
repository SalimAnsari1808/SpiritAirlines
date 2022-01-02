package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test CaseID: EPIC35168
//Description: Dynamic Shopping Cart Seats Caret Dropdown WireFrame
//Created By : Anthony Cardona
//Created On : 31-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 01-AUG-2019
//**********************************************************************************************

public class PRODTC90729 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups="Production")

    public void Dynamic_Shopping_Cart_Seats_Caret_Dropdown_WireFrame(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODEPIC35168 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         ***********************Navigate to Passenger Info Page************************
         ******************************************************************************/
        //Home Page Constant ValuesPRODTC90129
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "40";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "9DFC";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_FARE       = "BookIt";


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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep", DEP_FLIGHT);

        String flightFarePrice = pageObjectManager.getFlightAvailabilityPage().getStandardFarePriceText().getText(); //store flight information

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait until Bags page appear on web
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        String SeatPrice = "";

        //Select a seat
        for (WebElement seat : pageObjectManager.getSeatsPage().getStandardSeatsGridView()) {
            if (seat.getText().contains("$")) {
                SeatPrice = seat.getText();
                seat.click();
                WaitUtil.untilTimeCompleted(2000);
                break;
            }
        }

        /******************************************************************************
         **********************Validation Payment Page*********************************
         ******************************************************************************/

        //Constant Values to validate
        final String SEATS_INFO_URL     = "book/seats";
        final String BACKGROUND_GRAY    = "rgba(239, 239, 239, 1)";
        final String BLUE_RGB           = "rgba(0, 115, 230, 1)";
        final String FONT_BLACK         = "rgba(0, 0, 0, 1)";
        final String BACKGROUND_YELLOW  = "rgba(255, 236, 0, 1)";
        final String SAVEUPTO_PRICE     = scenarioContext.getContext(Context.AVAILABILITY_9DFC_SAVEUPTO_PRICE).toString().replace("SAVE", "").replace("TOTAL", "").trim();
        final String JOIN_SAVE_TEXT     = "JOIN $9 FARE CLUB AND SAVE " + SAVEUPTO_PRICE + "!";

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Seats page", getDriver().getCurrentUrl(), (SEATS_INFO_URL));

//Step 2 and 4: Validate content in the Seats drop down (Step 3 is invalid while window maximized at 100%)
        //Validate Shopping Cart is present

        //Validate Shopping Cart is present
        ValidationUtil.validateTestStep("Verify user Shopping Cart is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));

        //Verify A Grey text block, left aligned icon of cart followed by verbiage in black font: Your Itinerary and right aligned $XXX (dynamic pricing).
        ValidationUtil.validateTestStep("Verify A Grey text block",
                pageObjectManager.getHeader().getYourItineraryPanel().getCssValue("background-color"),BACKGROUND_GRAY);

        ValidationUtil.validateTestStep("Verify dynamic pricing Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryTotalAmountText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing is Correct for Your Itinerary",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText(),"$");

        ValidationUtil.validateTestStep("Verify user Shopping Cart Caret is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowYourItineraryImage()));

        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //verify Icon of an airplane
        ValidationUtil.validateTestStep("verify Icon of a seat is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSeatsItinerarayImage()));

        //verify seat label
        ValidationUtil.validateTestStep("verify seat label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSeatsItinerarayTextText()));

        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Verify dynamic pricing correct",
                pageObjectManager.getHeader().getSeatsItinerarayTextText().getText(), "Seats");

        //verify flight dynamic pricing
        ValidationUtil.validateTestStep("verify seats price is displayed",
                pageObjectManager.getHeader().getSeatsPriceItineraryText().getText(), SeatPrice);

        //verify arrow
        ValidationUtil.validateTestStep("verify arrow  is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowSeatsItineraryImage()));

        pageObjectManager.getHeader().getArrowSeatsItineraryImage().click();

        ValidationUtil.validateTestStep("verify Seat Type is standard",
                "Standard", pageObjectManager.getHeader().getDepSeatFlightLegDetailsText().get(0).getText());

        ValidationUtil.validateTestStep("Verify $9FC Font is black",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getCssValue("color"), FONT_BLACK);

        ValidationUtil.validateTestStep("Verify $9FC verbiage: JOIN AND SAVE (link)",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getText(), JOIN_SAVE_TEXT);

        ValidationUtil.validateTestStep("Verify $9FC caret is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage()));

        ValidationUtil.validateTestStep("Verify $9FC tile yellow background",
                pageObjectManager.getHeader().getJoinFareClubAndSavePanel().getCssValue("background-color"),BACKGROUND_YELLOW);

//Step 5 is invalid , guest will not receive this button until they proceed to the options page
        //Click on the down caret within the $9FC sub-text box: sub-text box expands

//Step 6: Continue to the options page and click on the blue EDIT seats link
        pageMethodManager.getSeatsPageMethods().continueWithSeats();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHeader().getArrowSeatsItineraryImage().click();

        System.out.println(pageObjectManager.getHeader().getEditSeatItneraryButton().getCssValue("color"));

        //verify Edit link(blue font)
        ValidationUtil.validateTestStep("verify Edit link(blue font) is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getEditSeatItneraryButton())
                        && pageObjectManager.getHeader().getEditSeatItneraryButton().getCssValue("color").equals(BLUE_RGB));

        pageObjectManager.getHeader().getEditSeatItneraryButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verify user navigated to Seats page",
                getDriver().getCurrentUrl(), SEATS_INFO_URL);

//sTEP 7: Validate the shopping cart is displayed
        ValidationUtil.validateTestStep("Verify user Shopping Cart is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));
    }
}