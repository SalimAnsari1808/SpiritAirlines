package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC90724
//Description : Dynamic Shopping Cart Bags Page WireFrame
//Created By : Sunny Sok
//Created On : 30-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 31-JUL-2019
//**********************************************************************************************

public class TC90724 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "BookIt" , "CheckedBags" , "DynamicShoppingCartUI","BagsUI"})
    public void Dynamic_Shopping_Cart_Bags_Page_WireFrame(@Optional("NA") String platform) {

        /******************************************************************************
         ***************************Navigate to Bags Page******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90724 under REGRESSION-CRITICAL Suite on " + platform + " Browser" , true);
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
        final String DEP_BAGS           = "Carry_0|Checked_1";

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
        final String BAGS_URL                   = "book/bags";
        final String BACKGROUND_GRAY            = "rgba(239, 239, 239, 1)";
        final String BACKGROUND_WHITE           = "rgba(0, 0, 0, 0)";
        final String BLACK                      = "rgba(0, 0, 0, 1)";
        final String BLUE_RGB                   = "rgba(0, 115, 230, 1)";
        final String WHITE_RGB                  = "rgba(255, 255, 255, 1)";
        final String BACKGROUND_YELLOW          = "rgba(255, 236, 0, 1)";
        final String FlIGHTTOTAL                = scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE).toString().replace("TOTAL","").trim();
        final String JOIN_SAVE_TEXT             = "JOIN $9 FARE CLUB AND SAVE ";


        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Bags page", getDriver().getCurrentUrl(),BAGS_URL);

        //--step 1
        //verify breadcrumbs
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
                pageObjectManager.getHeader().getBreadCumActiveStepNumberText().getCssValue("background-color"),BLACK);

        ValidationUtil.validateTestStep("BreadCum Active Step Number color is white",
                pageObjectManager.getHeader().getBreadCumActiveStepNumberText().findElement(By.tagName("p")).getCssValue("color"),WHITE_RGB);

        //--step 2
        //Prior to the Bags selection, The view of the cart is a grey text block,
        // left aligned is an icon of cart followed by verbiage in black font: "Your Itinerary"
        // and right aligned $XXX dynamic price that includes Flight and Taxes followed by a caret
        //Validate Shopping Cart is present
        ValidationUtil.validateTestStep("Verify user Shopping Cart is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));

        //Verify A Grey text block, left aligned icon of cart followed by verbiage in black font: Your Itinerary and right aligned $XXX (dynamic pricing).
        ValidationUtil.validateTestStep("Verify A Grey text block",
                pageObjectManager.getHeader().getYourItineraryPanel().getCssValue("background-color"),BACKGROUND_GRAY);

        ValidationUtil.validateTestStep("Verify Your Itinerary is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getYourItineraryText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryTotalAmountText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing is Correct for Your Itinerary",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText(), FlIGHTTOTAL);

        //--step 3
        //When bags are added an updated Shopping Cart will display
        //selecting bags
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

        //setting bags total
        scenarioContext.setContext(Context.BAGS_TOTAL_PRICE, pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText());

        //clicking on itinerary chevron to expose subtext boxes
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //Verify The 1st sub-text box has a white background, left aligned an icon of an airplane, followed by black font verbiage: "Flight or Flight + Hotel + Car or Flight + Hotel or Flight +Car,",
        // right aligned $XXX dynamic pricing and dropdown button / caret

        ValidationUtil.validateTestStep("Verify white background of flight subBox",
                pageObjectManager.getHeader().getFlightItineraryPanel().getCssValue("background-color"), BACKGROUND_WHITE);

        //verify Icon of an airplane
        ValidationUtil.validateTestStep("verify Icon of an airplane for flight is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryImage()));

        //verify flight label
        ValidationUtil.validateTestStep("verify flight label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryText()));

        //Verify font color
        ValidationUtil.validateTestStep("Verify font color", pageObjectManager.getHeader().getFlightItineraryText().getCssValue("color"), BLACK);

        //verify flight dynamic pricing
        System.out.println(pageObjectManager.getHeader().getFlightPriceItineraryText().getText());

        ValidationUtil.validateTestStep("verify flight price is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightPriceItineraryText()));

        ValidationUtil.validateTestStep("verify flight price is Correct",
                pageObjectManager.getHeader().getFlightPriceItineraryText().getText(), FlIGHTTOTAL);

        //verify arrow
        ValidationUtil.validateTestStep("verify arrow  is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowFlightItineraryImage()));

        //--step 4
        //Verify The 2nd sub-text box has a white background, left aligned is an icon of a suitcase,
        // followed by black font, verbiage "Bags", right aligned $XXX dynamic pricing and dropdown button / caret

        ValidationUtil.validateTestStep("Verify white background of Bags sub-text box",
                pageObjectManager.getHeader().getBagsItineraryPanel().getCssValue("background-color"), BACKGROUND_WHITE);

        //Verify a suitcase icon is displayed
        ValidationUtil.validateTestStep("Verify a suitcase icon for bags is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBagsItineraryImage()));

        //verify Bags label is displayed
        ValidationUtil.validateTestStep("verify Bags label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getBagsItineraryText()));

        //Verify font color
        ValidationUtil.validateTestStep("Verify font color", pageObjectManager.getHeader().getBagsItineraryText().getCssValue("color"), BLACK);

        System.out.println(scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString());
        //Verify price
        ValidationUtil.validateTestStep("Verify Bags Total matches shopping cart",
                pageObjectManager.getHeader().getBagsPriceItineraryText().getText(),scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString());

        //Verify chevron is displayed
        ValidationUtil.validateTestStep("Verify chevron is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowBagsItineraryImage()));

        //--Step 5
        //The 3rd sub-text box, has a white background, left aligned is a $ symbol, followed by blue font, verbiage "Bundle Discount", right aligned $XXX dynamic pricing
        /******Adding upgrade will cause more sub-text boxes to appear! test case need to be repaired!****/

        //--step 6
        //verify $9FC tile, yellow background, with black font, left aligned verbiage: JOIN $9FC AND SAVE
        //$XXX! (this amount is dynamic) right aligned down button / caret.
        ValidationUtil.validateTestStep("Verify $9FC tile yellow background",
                pageObjectManager.getHeader().getJoinFareClubAndSavePanel().getCssValue("background-color"),BACKGROUND_YELLOW);

        ValidationUtil.validateTestStep("Verify $9FC Font is black",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getCssValue("color"),BLACK);

        ValidationUtil.validateTestStep("Verify $9FC verbiage: JOIN AND SAVE (link)",
                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getText(), JOIN_SAVE_TEXT);

        ValidationUtil.validateTestStep("Verify $9FC caret is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage()));
    }
}