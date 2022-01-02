package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC90726
//Description : Dynamic Shopping Cart Options page WireFrame
//Created By : Sunny Sok
//Created On : 29-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 31-JUL-2019
//**********************************************************************************************

public class PRODTC90726 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups="Production")

    public void Dynamic_Shopping_Cart_Options_page_WireFrame(@Optional("NA") String platform) {

        /******************************************************************************
         **************************Navigate to Options Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90726 under PRODUCTION Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "BWI";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "9DFC";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_FARE		= "BoostIt";


        //Bags Page constant values
        final String CHECKED_BAG        = "Included";

        //Seats Page Constant Values
        final String SEATS_DEP          = "Standard";

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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("dep","NonStop");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Get all shopping cart values
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        WaitUtil.untilPageLoadComplete(getDriver(),(long)1000);

        Double FlIGHTTOTAL      = Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_PRICE).toString().replace("$","").replace("TOTAL","").trim());
        Double BAGSTOTAL        = Double.parseDouble(pageObjectManager.getHeader().getBagsPriceItineraryText().getText().replace("$",""));
        Double SEATTOTAL        = Double.parseDouble(pageObjectManager.getHeader().getSeatsPriceItineraryText().getText().replace("$",""));
        Double OPTIONSTOTAL     = Double.parseDouble(pageObjectManager.getHeader().getOptionsPriceItineraryText().getText().replace("$",""));
        Double BOOSTITDISCOUNT  = Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString().replace("Save Up To $",""));

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        for(WebElement checkedBag : pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText()){
            //verify checked is already included
            ValidationUtil.validateTestStep("Verify Checked Bag is included with Boost It on Flight Availiability Page",
                    checkedBag.getText(),CHECKED_BAG);
        }

        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS_DEP);

        //verify for continue without seat
        ValidationUtil.validateTestStep("Verify 'Continue Without Seat' is not displayed on Seat Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton()));

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /******************************************************************************
         **********************Validation Options Page*********************************
         ******************************************************************************/

        //Constant Values to validate
        final String OPTIONS_URL        = "book/options";
        final String BACKGROUND_GRAY    = "rgba(239, 239, 239, 1)";
        final String BACKGROUND_YELLOW  = "rgba(255, 236, 0, 1)";
        final String BLACK_RGB          = "rgba(0, 0, 0, 1)";
        final String BLUE_RGB           = "rgba(0, 115, 230, 1)";
        final String WHITE_RGB          = "rgba(255, 255, 255, 1)";
        final String ITINERARY_TOTAL    = String.format("%.2f", FlIGHTTOTAL + BAGSTOTAL + SEATTOTAL + OPTIONSTOTAL - BOOSTITDISCOUNT);
        final String JOIN_SAVE_TEXT     = "JOIN $9 FARE CLUB AND SAVE ";

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Options page", getDriver().getCurrentUrl(),(OPTIONS_URL));

        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

        List<WebElement> breadCrumbs = pageObjectManager.getHeader().getBreadCumListText();

        for(int count = 0; count < breadCrumbs.size(); count++) {
            String[] breadCrumbText = { "Flight" , "Passenger", "Bags" ,  "Seats" , "Options" , "Payment" , "Confirmation" };
            ValidationUtil.validateTestStep("Verifying BreadCrumb " + breadCrumbText[count] + " is Displayed" ,
                    breadCrumbs.get(count).getText(),(breadCrumbText[count]));
        }

        for(WebElement check:pageObjectManager.getHeader().getBreadCumCheckMarkImage()){
            ValidationUtil.validateTestStep("BreadCum check mark back ground color is blue",check.getCssValue("background-color"),BLUE_RGB);
            ValidationUtil.validateTestStep("BreadCum check mark color is white",check.findElement(By.tagName("i")).getCssValue("color"),WHITE_RGB);
        }

        ValidationUtil.validateTestStep("BreadCum Active Step Number back ground color is black",
                pageObjectManager.getHeader().getBreadCumActiveStepNumberText().getCssValue("background-color"),BLACK_RGB);

        ValidationUtil.validateTestStep("BreadCum Active Step Number color is white",
                pageObjectManager.getHeader().getBreadCumActiveStepNumberText().findElement(By.tagName("p")).getCssValue("color"),WHITE_RGB);

        //Validate Shopping Cart is present
        ValidationUtil.validateTestStep("Verify user Shopping Cart is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));

        //Verify A Grey text block, left aligned icon of cart followed by verbiage in black font: Your Itinerary and right aligned $XXX (dynamic pricing).
        ValidationUtil.validateTestStep("Verify A Grey text block",
                pageObjectManager.getHeader().getYourItineraryPanel().getCssValue("background-color"),BACKGROUND_GRAY);

        ValidationUtil.validateTestStep("Verify Your Itinerary is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getYourItineraryText()));

        ValidationUtil.validateTestStep("Verify Your Itinerary text color is black",
                pageObjectManager.getHeader().getYourItineraryText().getCssValue("color"),BLACK_RGB);

        ValidationUtil.validateTestStep("Verify dynamic pricing Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryTotalAmountText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing is Correct for Your Itinerary",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText(),"$" + ITINERARY_TOTAL);

        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //--Flight
        //verify Icon of an airplane
        ValidationUtil.validateTestStep("verify Icon of an airplane for flight is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryImage()));

        //verify flight label
        ValidationUtil.validateTestStep("verify flight label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryText()));

        //verify flight dynamic pricing
        ValidationUtil.validateTestStep("verify flight price is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightPriceItineraryText())
                        && pageObjectManager.getHeader().getFlightPriceItineraryText().getText().equals("$" + String.format("%.2f", FlIGHTTOTAL)));

        //verify arrow
        ValidationUtil.validateTestStep("verify arrow  is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowFlightItineraryImage()));

        //--Bags
        //Verify a suitcase icon is displayed
        ValidationUtil.validateTestStep("Verify a suitcase icon for bags is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBagsItineraryImage()));

        //verify Bags label is displayed
        ValidationUtil.validateTestStep("verify Bags label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBagsItineraryText()));

        //Verify price
        ValidationUtil.validateTestStep("Verify Bags Total matches shopping cart",
                pageObjectManager.getHeader().getBagsPriceItineraryText().getText(),"$" + String.format("%.2f",BAGSTOTAL));

        //Verify chevron is displayed
        ValidationUtil.validateTestStep("Verify chevron is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowBagsItineraryImage()));

        //--Seat
        //Verify a Chair icon is displayed
        ValidationUtil.validateTestStep("Verify a chair icon for seats is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSeatsItinerarayImage()));

        //verify Seats label is displayed
        ValidationUtil.validateTestStep("verify Seats label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSeatsItinerarayTextText()));

        //Verify price
        ValidationUtil.validateTestStep("Verify Seats Total matches shopping cart",
                pageObjectManager.getHeader().getSeatsPriceItineraryText().getText(),"$" + String.format("%.2f", SEATTOTAL));

        //Verify chevron is displayed
        ValidationUtil.validateTestStep("Verify chevron is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowSeatsItineraryImage()));

        //--Options
        //Verify a Star icon is displayed
        ValidationUtil.validateTestStep("Verify a suitcase icon for options is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getOptionsItineraryImage()));

        //verify Seats label is displayed
        ValidationUtil.validateTestStep("verify options label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getOptionsItineraryText()));

        //Verify price
        ValidationUtil.validateTestStep("Verify options Total matches shopping cart",
                pageObjectManager.getHeader().getOptionsPriceItineraryText().getText(),"$" + String.format("%.2f",OPTIONSTOTAL));

        //--Bundle
        //Verify a Dollar icon is displayed
        ValidationUtil.validateTestStep("Verify a Dollar icon for Upgrade is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowBareFareDiscountItineraryImage()));

        //verify Seats label is displayed
        ValidationUtil.validateTestStep("verify options label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBareFareDiscountItineraryText())
                        && pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText().equals("Boost It Discount"));

        //Verify price
        ValidationUtil.validateTestStep("Verify options Total matches shopping cart",
                pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText(),"-$" + String.format("%.2f",BOOSTITDISCOUNT));


        //verify $9FC tile, yellow background, with black font, left aligned verbiage: JOIN $9FC AND SAVE
        //$XXX! (this amount is dynamic) right aligned down button / caret.
        ValidationUtil.validateTestStep("Verify $9FC tile yellow background",
                pageObjectManager.getHeader().getJoinFareClubAndSavePanel().getCssValue("background-color"),BACKGROUND_YELLOW);

        ValidationUtil.validateTestStep("Verify $9FC Font is black",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getCssValue("color"), BLACK_RGB);

        ValidationUtil.validateTestStep("Verify $9FC verbiage: JOIN AND SAVE (link)",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getText(), JOIN_SAVE_TEXT);

        ValidationUtil.validateTestStep("Verify $9FC caret is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage()));
    }
}