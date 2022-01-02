package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;
//**********************************************************************************************
//Test Case ID: TC90725
//Description: Dynamic Shopping Cart Seats page WireFrame
//Created By:  Kartik chauhan
//Created On:  31-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 31-July-2019
//**********************************************************************************************

public class PRODTC90725 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void Dynamic_Shopping_Cart_Seats_page_WireFrame(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90725 under PRODUCTION Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "10";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Bags Page Constant
        final String BUNDLE_ITINERARY   = "Bundle It Discount";
        final String ZERO_BAG_PRICE 	= "$0.00";

        //Seats Page Constant Values
        final String SEATS_DEP          = "BigFront";

        //open browser
        openBrowser(platform);
//STEP--1
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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(2000);

        //verify Bundle it Discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        //verify Bundle it price
        ValidationUtil.validateTestStep("Validating Bags Total Price displayed is 0.00 on Bags Page for Bundle It",
                pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText(),ZERO_BAG_PRICE);

        //continue with selected Bags
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
 //STEP--2
        //Get all shopping cart values
        WaitUtil.untilPageLoadComplete(getDriver());

        //Constant Values to validate
        final String SEAT_URL           = "book/seats";
        final String BLACK_RGB          = "rgba(0, 0, 0, 1)";
        final String BLUE_RGB           = "rgba(0, 115, 230, 1)";
        final String WHITE_RGB          = "rgba(255, 255, 255, 1)";

        //verify correct URL
        ValidationUtil.validateTestStep("Verify user navigated to Options page",
                getDriver().getCurrentUrl(),(SEAT_URL));

        //wait till page load
        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

        List<WebElement> breadCrumbs = pageObjectManager.getHeader().getBreadCumListText();

        for(int count = 0; count < breadCrumbs.size(); count++) {
            String[] breadCrumbText = { "Flight" , "Passenger", "Bags" , "Seats", "Options" , "Payment" , "Confirmation" };
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

        //Click on itinerary
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait till page load complete
        WaitUtil.untilPageLoadComplete(getDriver(),(long)1000);

        Double FlIGHTTOTAL  = Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_PRICE).toString().replace("$","").replace("TOTAL","").trim());
        Double BAGSTOTAL    = Double.parseDouble(pageObjectManager.getHeader().getBagsPriceItineraryText().getText().replace("$",""));
        Double SEATTOTAL    = Double.parseDouble(pageObjectManager.getHeader().getSeatsPriceItineraryText().getText().replace("$",""));
        Double OPTIONSTOTAL = Double.parseDouble(pageObjectManager.getHeader().getOptionsPriceItineraryText().getText().replace("$",""));
        Double BUNDLEITDISCOUNT  = Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString().replace("Save Up To $",""));

        //Constant Values to validate
        final String BACKGROUND_GRAY           = "rgba(239, 239, 239, 1)";
        final String BACKGROUND_YELLOW         = "rgba(255, 236, 0, 1)";
        final String FONT_BLACK                = "rgba(0, 0, 0, 1)";
        final String ITINERARYTOTAL            = String.format("%.2f", FlIGHTTOTAL + BAGSTOTAL + SEATTOTAL + OPTIONSTOTAL - BUNDLEITDISCOUNT);
        final String JOIN_SAVE_TEXT            = "JOIN $9 FARE CLUB AND SAVE $";

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

        ValidationUtil.validateTestStep("Verify dynamic pricing is Correct for Your Itinerary",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText(),"$" + ITINERARYTOTAL);

        //Click on itinerary
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        WaitUtil.untilTimeCompleted(2000);
//STEP--3
        //select Seat
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS_DEP);

        //Click on itinerary
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        WaitUtil.untilTimeCompleted(2000);

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
//STEP--4
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
//STEp--5
        Double SEATTOTAL1    = Double.parseDouble(pageObjectManager.getHeader().getSeatsPriceItineraryText().getText().replace("$",""));

        //--Seat
        //Verify a Chair icon is displayed
        ValidationUtil.validateTestStep("Verify a chair icon for seats is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSeatsItinerarayImage()));

        //verify Seats label is displayed
        ValidationUtil.validateTestStep("verify Seats label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSeatsItinerarayTextText()));

        //Verify price
        ValidationUtil.validateTestStep("Verify Seats Total matches shopping cart",
                pageObjectManager.getHeader().getSeatsPriceItineraryText().getText(),"$" + String.format("%.2f", SEATTOTAL1));

        //Verify chevron is displayed
        ValidationUtil.validateTestStep("Verify chevron is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowSeatsItineraryImage()));
//STEP--6
        //Bundle
        //Verify a Dollar icon is displayed
        ValidationUtil.validateTestStep("Verify a Dollar icon for Upgrade is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowBareFareDiscountItineraryImage()));

        //verify Seats label is displayed
        ValidationUtil.validateTestStep("verify options label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBareFareDiscountItineraryText())
                        && pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText().equals("Bundle It Discount"));

        //Verify price
        ValidationUtil.validateTestStep("Verify options Total matches shopping cart",
                pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText(),"-$" + String.format("%.2f",BUNDLEITDISCOUNT));

//STEP--7
        //verify $9FC tile, yellow background, with black font, left aligned verbiage: JOIN $9FC AND SAVE
        //$XXX! (this amount is dynamic) right aligned down button / caret.
        ValidationUtil.validateTestStep("Verify $9FC tile yellow background",
                getDriver().findElement(By.xpath("//div[contains(@class,'flight-club-savings')]")).getCssValue("background-color"),BACKGROUND_YELLOW);

        ValidationUtil.validateTestStep("Verify $9FC Font is black",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getCssValue("color"), FONT_BLACK);

        ValidationUtil.validateTestStep("Verify $9FC verbiage: JOIN AND SAVE (link)",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getText(), JOIN_SAVE_TEXT);

        ValidationUtil.validateTestStep("Verify $9FC caret is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage()));

        //click on Itinerary
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
    }
}