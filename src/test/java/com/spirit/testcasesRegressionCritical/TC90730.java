package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90730
//Description : Dynamic Shopping Cart Options Caret Dropdown WireFrame
//Created By : Sunny Sok
//Created On : 27-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 31-JUL-2019
//**********************************************************************************************

public class TC90730 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" ,"Connecting", "BookIt" , "NoBags" , "NoSeats" , "DynamicShoppingCartUI","FlightFlex","ShortCutBoarding","ShortCutSecurity"})
    public void Dynamic_Shopping_Cart_Options_Caret_Dropdown_WireFrame(@Optional("NA") String platform) {

        /******************************************************************************
         **************************Navigate to Options Page***************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90730 under REGRESSION CRITICAL Suite on " + platform + " Browser" , true);
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

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /******************************************************************************
         **********************Validation Options Page*********************************
         ******************************************************************************/

        //Constant Values to validate
        final String OPTIONS_URL               = "book/options";
        final String BACKGROUND_GRAY           = "239, 239, 239";
        final String BACKGROUND_YELLOW         = "255, 236, 0";
        final String FONT_BLACK                = "0, 0, 0";
        final String SAVEUPTO_PRICE            = scenarioContext.getContext(Context.AVAILABILITY_9DFC_SAVEUPTO_PRICE).toString().replace("SAVE","").replace("TOTAL","").trim();
        final String JOIN_SAVE_TEXT            = "JOIN $9 FARE CLUB AND SAVE " + SAVEUPTO_PRICE + "!";

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Options page", getDriver().getCurrentUrl(),OPTIONS_URL);

        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

        pageMethodManager.getOptionsPageMethods().selectOptions("FlightFlex|ShortCutSecurity,NotRequired|ShortCutBoarding");

        Double FLIGHTFLEXPRICE          = Double.parseDouble(scenarioContext.getContext(Context.OPTIONS_FLIGHT_FLEX).toString().replace("$",""));
        String SHORTCUT_SECURITY        = scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY).toString().replace("$","");
        Double SHORTCUTSECURITYPRICE    = Double.parseDouble(SHORTCUT_SECURITY.substring(SHORTCUT_SECURITY.indexOf("|")+1));
        Double SHORTCUTBOARDING         = Double.parseDouble(scenarioContext.getContext(Context.OPTIONS_SHORTCUT_BOARDING).toString().replace("$",""));
        Double OPTIONTOTAL              = (FLIGHTFLEXPRICE +SHORTCUTSECURITYPRICE +SHORTCUTBOARDING);
        Double FLIGHT                   = Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE).toString().replace("$",""));
        String ITINERARYTOTAL           = String.format("%.2f", FLIGHT + OPTIONTOTAL);

        //Validate Shopping Cart is present
        ValidationUtil.validateTestStep("Verify user Shopping Cart is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));

        //Verify A Grey text block, left aligned icon of cart followed by verbiage in black font: Your Itinerary and right aligned $XXX (dynamic pricing).
        ValidationUtil.validateTestStep("Verify A Grey text block",
                pageObjectManager.getHeader().getYourItineraryPanel().getCssValue("background-color").contains(BACKGROUND_GRAY));

        ValidationUtil.validateTestStep("Verify Your Itinerary is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getYourItineraryText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryTotalAmountText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing is Correct for Your Itinerary",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText(),"$" + ITINERARYTOTAL);

        //verify The Options tab is displayed
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilPageLoadComplete(getDriver(),(long)1000);

        ValidationUtil.validateTestStep("verify The Options tab is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getOptionsItineraryText()));

        //verify price is displayed (dynamic pricing)
        ValidationUtil.validateTestStep("verify The Options tab is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getOptionsPriceItineraryText()));

        ValidationUtil.validateTestStep("Verifying options total pricing is correct",
                pageObjectManager.getHeader().getOptionsPriceItineraryText().getText(), "$" + OPTIONTOTAL.toString());

        //verify caret is displayed
        ValidationUtil.validateTestStep("Verify options caret is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowOptionsItineraryImage()));

        //click on caret to view options selected
        pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();
        WaitUtil.untilPageLoadComplete(getDriver(),(long)1000);

        //verify Under Options is Flight FLEX followed by right aligned $XX.XX (dynamic pricing)
        ValidationUtil.validateTestStep("Verify Flight Flex label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightFlexOptionItineraryLabel())
                        && pageObjectManager.getHeader().getFlightFlexOptionItineraryLabel().getText().equals("Flight Flex"));

        ValidationUtil.validateTestStep("Verify Flight Flex Dynamic pricing is Displayed and correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightFlexOptionPriceItineraryText())
                        && pageObjectManager.getHeader().getFlightFlexOptionPriceItineraryText().getText().contains(scenarioContext.getContext(Context.OPTIONS_FLIGHT_FLEX).toString()));

        //Under Flight Flex is Shortcut Security followed by right aligned $XX.XX (dynamic pricing)
        ValidationUtil.validateTestStep("Verify Shortcut Security label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getShortcutSecurityOptiontineraryLabel())
                        && pageObjectManager.getHeader().getShortcutSecurityOptiontineraryLabel().getText().equals("Shortcut Security"));

        System.out.println(scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY).toString());
        System.out.println(pageObjectManager.getHeader().getShortcutSecurityOptionPriceItineraryText().getText());

        ValidationUtil.validateTestStep("Verify Shortcut Security Dynamic pricing is Displayed and correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getShortcutSecurityOptionPriceItineraryText())
                        &&scenarioContext.getContext(Context.OPTIONS_SHORTCUT_SECURITY).toString().contains(pageObjectManager.getHeader().getShortcutSecurityOptionPriceItineraryText().getText().replace("$","")));

        //Under Shortcut Security is Shortcut Boarding followed by right aligned $XX.XX (dynamic pricing)
        ValidationUtil.validateTestStep("Verify Shortcut Boarding label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getShortcutBoardingOptionItineraryLabel())
                        && pageObjectManager.getHeader().getShortcutBoardingOptionItineraryLabel().getText().equals("Shortcut Boarding"));

        ValidationUtil.validateTestStep("Verify Shortcut Boarding Dynamic pricing is Displayed and correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getShortcutBoardingOptionPriceItineraryText())
                        && pageObjectManager.getHeader().getShortcutBoardingOptionPriceItineraryText().getText().contains(scenarioContext.getContext(Context.OPTIONS_SHORTCUT_BOARDING).toString()));

        //verify $9FC tile, yellow background, with black font, left aligned verbiage: JOIN $9FC AND SAVE
        //$XXX! (this amount is dynamic) right aligned down button / caret.
        ValidationUtil.validateTestStep("Verify $9FC tile yellow background",
                pageObjectManager.getHeader().getJoinFareClubAndSavePanel().getCssValue("background-color").contains(BACKGROUND_YELLOW));

        ValidationUtil.validateTestStep("Verify $9FC Font is black",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getCssValue("color").contains( FONT_BLACK));

        ValidationUtil.validateTestStep("Verify $9FC verbiage: JOIN AND SAVE (link)",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getText(), JOIN_SAVE_TEXT);

        ValidationUtil.validateTestStep("Verify $9FC caret is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage()));

    }
}