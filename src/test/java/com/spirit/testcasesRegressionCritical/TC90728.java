package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90728
//Description : Dynamic Shopping Cart Bags Caret Dropdown WireFrame
//Created By : Sunny Sok
//Created On : 25-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-JUL-2019
//**********************************************************************************************
public class TC90728 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" ,"Connecting", "BookIt" , "CarryOn" , "CheckedBags" , "DynamicShoppingCartUI","BagsUI","SeatsUI"})
    public void Dynamic_Shopping_Cart_Bags_Caret_Dropdown_WireFrame(@Optional("NA") String platform) {

        /******************************************************************************
         ***************************Navigate to Bags Page******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90728 under REGRESSION-CRITICAL Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LGA";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "9DFC";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_FARE		= "BookIt";

        //Bags Page constant values
        final String DEP_BAGS           = "Carry_1|Checked_1";
        final String NEW_DEP_BAGS       = "Carry_1|Checked_5";

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

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /******************************************************************************
         **************************Validation Bags Page********************************
         ******************************************************************************/

        //Constant Values to validate
        final String BAGS_URL                  = "book/bags";
        final String SEATS_URL                 = "book/seats";
        final String BACKGROUND_COLOR          = "239, 239, 239";

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Bags page",
                getDriver().getCurrentUrl(),BAGS_URL);

        //selecting bags
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

        //setting bags total
        scenarioContext.setContext(Context.BAGS_TOTAL_PRICE, pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText());

        //Validate Shopping Cart is present
        ValidationUtil.validateTestStep("Verify user Shopping Cart is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));

        //Click on the down caret within the $9FC sub-text box: sub-text box expands
        pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage().click();

        //Verify A Grey text block, left aligned icon of cart followed by verbiage in black font: Your Itinerary and right aligned $XXX (dynamic pricing).
        ValidationUtil.validateTestStep("Verify A Grey text block",
                getDriver().findElement(By.xpath("//div[contains(@class,'total-cost')]")).getCssValue("background-color").contains(BACKGROUND_COLOR));

        ValidationUtil.validateTestStep("Verify Your Itinerary is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getYourItineraryText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryTotalAmountText()));

        //Verify a suitcase icon is displayed
        ValidationUtil.validateTestStep("Verify a suitcase icon is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getYouItineraryImage()));

        //verify Bags label
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

        ValidationUtil.validateTestStep("verify Bags label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBagsItineraryText()));

        //Verify price
        ValidationUtil.validateTestStep("Verify Bags Total matches shopping cart",
                scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString(), pageObjectManager.getHeader().getBagsPriceItineraryText().getText());

        //Verify chevron is displayed
        ValidationUtil.validateTestStep("Verify chevron is displayed",TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowBagsItineraryImage()));

        //click on chevron
        pageObjectManager.getHeader().getArrowBagsItineraryImage().click();

        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

        //verify POO – POD
        String DepAirportTemp   = scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString();
        String DepAirport       = DepAirportTemp.substring(DepAirportTemp.lastIndexOf("(") + 1 , DepAirportTemp.lastIndexOf(")"));
        String ArrAirportTemp   = scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString();
        String ArrAirport       = ArrAirportTemp.substring(ArrAirportTemp.lastIndexOf("(") + 1 , ArrAirportTemp.lastIndexOf(")"));

        ValidationUtil.validateTestStep("verify POO – POD",
                DepAirport + " - " + ArrAirport, pageObjectManager.getHeader().getAirportBagsItineraryText().get(0).getText());

        //verify Carry-Ons and  Checked Bags
        String Bags = pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(0).getAttribute("value") + " Carry-On, " + pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(0).getAttribute("value") + " Checked Bags";

        ValidationUtil.validateTestStep("verify Carry-Ons and Checked Bags",
                pageObjectManager.getHeader().getAirportCarryOnBagsSelectedItineraryText().get(0).getText() + " " + pageObjectManager.getHeader().getAirportCheckInBagsSelectedItineraryTxt().get(0).getText(), Bags);

        //verify Bag Pricing
        ValidationUtil.validateTestStep("Verify Bag Pricing",
                pageObjectManager.getHeader().getPriceBagsItineraryText().get(0).getText(), scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString());

        //Verify Underneath there is the verbiage: JOIN AND SAVE (link) $XX.xx! (Dynamic PRICING right aligned) and an up caret
        Double BagSaveTotal      = Double.parseDouble(pageObjectManager.getBagsPage().getContinueWith9FCBagsContainerSavingsText().getText().replace("SAVE $","").replace("TOTAL","").trim());
        Double FlightSaveTotal   = Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_9DFC_SAVEUPTO_PRICE).toString().replace("SAVE $","").replace("TOTAL","").trim());
        String FareClubSaveTotal = String.format("%.2f", BagSaveTotal + FlightSaveTotal);
        String JOIN_SAVE_TEXT    = "JOIN $9 FARE CLUB AND SAVE $" + FareClubSaveTotal  + "!";

        ValidationUtil.validateTestStep("Verify verbiage: JOIN AND SAVE",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getText(), JOIN_SAVE_TEXT);

        ValidationUtil.validateTestStep("Verify up caret is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage()));

        //Go to the Seats page
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //verify user navigated to seats page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Seats page",
                getDriver().getCurrentUrl(),SEATS_URL);

        //click the Edit link under the sub-text box labeled "Bags" they are redirected back to the book/bags page where they can add bags (if none were previously added) or modify bag selection following existing bags functionality.
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        pageObjectManager.getHeader().getArrowBagsItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHeader().getEditBagsItneraryButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Bags page",
                getDriver().getCurrentUrl(), BAGS_URL);

        //Modify bags
        pageMethodManager.getBagsPageMethods().selectDepartingBags(NEW_DEP_BAGS);

        //setting bags total
        scenarioContext.setContext(Context.BAGS_TOTAL_PRICE, pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText());

        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //Verify price
        ValidationUtil.validateTestStep("Verify Bags Total matches shopping cart",
                scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString(),
                pageObjectManager.getHeader().getBagsPriceItineraryText().getText());
    }
}