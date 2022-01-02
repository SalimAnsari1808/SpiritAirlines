package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90740
//Description : Dynamic Shopping Cart 9DFC Caret Dropdown WireFrame
//Created By : Sunny Sok
//Created On : 24-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-JUL-2019
//**********************************************************************************************

public class TC90740 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "BookIt" , "DynamicShoppingCartUI"})
    public void Dynamic_Shopping_Cart_9DFC_Caret_Dropdown_WireFrame(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Passenger Info Page************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90740 under REGRESSION CRITICAL Suite on " + platform + " Browser" , true);
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

        //Constant Values to validate
        final String PASSENGER_INFO_URL        = "book/passenger";
        final String BACKGROUND_COLOR          = "239, 239, 239";
        final String SAVEUPTO_PRICE            = scenarioContext.getContext(Context.AVAILABILITY_9DFC_SAVEUPTO_PRICE).toString().replace("SAVE","").replace("TOTAL","").trim();
        final String JOIN_SAVE_TEXT            = "JOIN $9 FARE CLUB AND SAVE " + SAVEUPTO_PRICE + "!";
        final String NON_REFUNDABLE_TEXT       = "*Membership charges are non-refundable and program renews automatically each year.";
        final String CONGRATS_TEXT             = "CONGRATS! YOU’RE SAVING " + SAVEUPTO_PRICE + " ON YOUR BOOKING!" ;

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Passenger Info page",
                getDriver().getCurrentUrl(),(PASSENGER_INFO_URL));

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

        //Verify Underneath there is the verbiage: JOIN AND SAVE (link) $XX.xx! (Dynamic PRICING right aligned) and an up caret
        ValidationUtil.validateTestStep("Verify verbiage: JOIN AND SAVE (link)",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getText(), JOIN_SAVE_TEXT);

        ValidationUtil.validateTestStep("Verify up caret is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage()));

        //verify Yes, I want to save by joining the $9 Fare Club $59.95 (*Membership charges are non-refundable and program renews automatically each year).
        //Terms and conditions apply (link) is displayed
        ValidationUtil.validateTestStep("Verify Membership charges are non-refundable is displayed",
                pageObjectManager.getHeader().getMembershipDisclosureTxt().getText(), NON_REFUNDABLE_TEXT);

        ValidationUtil.validateTestStep("Verify Terms and conditions apply (link) is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getMemberTermsAndConditionItineraryLink()));

        //verify JOIN NOW AND SAVE BUTTON is displayed
        ValidationUtil.validateTestStep("verify JOIN NOW AND SAVE BUTTON is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getJoinNowAndSaveItineraryButton()));

        //Verify that the $9FC sign up was added to the cart
        pageObjectManager.getHeader().getJoinNowAndSaveItineraryButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verify CONGRATS! YOU’RE SAVING is displayed",
                pageObjectManager.getHeader().getMembershipCongratsVerbiageTxt().getText(), CONGRATS_TEXT);

        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();

        ValidationUtil.validateTestStep("Verify $9 Fare Club Membership was added to cart",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getMembershipVerbiageText()));

        ValidationUtil.validateTestStep("Verify $9 Fare Club Membership Price was added to cart",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getMembershipAmountText()));
    }
}