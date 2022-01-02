package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC91051
//Description: Flight_Availability_CP_BP_Validate_Bundle_Upsell_Pop_up_Wireframe
//Created By : Gabriela Gonzalez
//Created On : 19-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-Apr-2019
//**********************************************************************************************

public class PRODTC91051 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups="Production")

    public void Flight_Availability_CP_BP_Validate_Bundle_Upsell_Pop_up_Wireframe(@Optional("NA") String platform) {
        /******************************************************************************
         *********************Navigate to Upgrade and Save popup************************
         ******************************************************************************/
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC91051 under PRODUCTION Suite on " + platform + " Browser", true);
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
        final String ARR_DATE           = "10";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        /******************************************************************************
         ******************Validation on Upgrade and Save popup************************
         ******************************************************************************/
        //--Steps 4
        //declare constant used in Validation
        final String UPGRADE_AND_SAVE_TITLE = "Upgrade & Save";
        final String BOOKIT_TITLE           = "Book It";
        final String BOOKIT_RANDOM_SEATS    = "Random Seat";
        final String BOOKIT_PERSONAL_ITEM   = "Personal Item";
        final String BOOKIT_VERBIAGE        = "You can purchase bags, seats, and options individually";
        final String BOOKIT_INCLUDED        = "Included";
        final String BOOKIT_BUTTON_TITLE    = "BOOK IT";
        final String BOOSTIT_TITLE          = "Boost It";
        final String BOOSTIT_FLAG           = "Save Up To $";
        final String BOOSTIT_1BENEFITS      = "Pick Your Seat - Grab a sweet spot in the plane (up to and including the Exit Row)";
        final String BOOSTIT_2BENEFITS      = "Personal Item";
        final String BOOSTIT_3BENEFITS      = "Checked Bag plus 10 lbs. extra";
        final String BOOSTIT_4BENEFITS      = "Shortcut Boarding - Board Early";
        final String BOOSTIT_VERBIAGE       = "Travel with extra, for just a little extra.";
        final String BOOSTIT_PLUS           = "+ $";
        final String BOOSTIT_MORE           = "more";
        final String BOOSTIT_PRICE_VERBIAGE = "per person, per way";
        final String BOOSTIT_BUTTON         = "BOOST IT";
        final String BUNDLEIT_TITLE         = "Bundle It";
        final String BUNDLEIT_FLAG          = "Save Up To $";
        final String BUNDLEIT_1BENEFITS     = "Pick Your Seat - Grab a sweet spot in the plane (up to and including the Exit Row)";
        final String BUNDLEIT_2BENEFITS     = "Personal Item";
        final String BUNDLEIT_3BENEFITS     = "Carry-On Bag";
        final String BUNDLEIT_4BENEFITS     = "Checked Bag plus 10 lbs. extra";
        final String BUNDLEIT_5BENEFITS     = "Shortcut Boarding - Board Early";
//        final String BUNDLEIT_6BENEFITS     = "Flight Flex - Modify your Trip"; /*QAEPIC*/
        final String BUNDLEIT_6BENEFITS     = "Flight Flex - Modify your Flight";
        //final String BUNDLEIT_6BENEFITS        = "Flight Flex - Modify your flight once for free! (fare difference may apply)"; /*NAV01*/
        final String BUNDLEIT_7BENEFITS     = "2X Flight Miles";
        final String BUNDLEIT_PLUS          = "+ $";
        final String BUNDLEIT_BUTTON        = "BUNDLE IT";


        ValidationUtil.validateTestStep("Upgrade & Save pops up is displayed",
                pageObjectManager.getFlightAvailabilityPage().getBundleFareHeaderText().isDisplayed());

        //--Steps 5
        ValidationUtil.validateTestStep("Pop Up title is Upgrade & Save",
                pageObjectManager.getFlightAvailabilityPage().getBundleFareHeaderText().getText(),UPGRADE_AND_SAVE_TITLE);

        //--Step 6
        ValidationUtil.validateTestStep("Close button is present",
                pageObjectManager.getFlightAvailabilityPage().getBundleFareCloseButton().isDisplayed());

        //--Step 7
//        ValidationUtil.validateTestStep("Verifying Upgrade & Save pop up has 3 tiles",
//                pageObjectManager.getFlightAvailabilityPage().getBundleFareTilesPanel().size() == 3);

        //--Step 8
        ValidationUtil.validateTestStep("Tile 1 title is Book It",
                pageObjectManager.getFlightAvailabilityPage().getBookItTitleText().getText(),BOOKIT_TITLE);


        //--Step 9 Verifying Benefits displayed on Book It tile
        ValidationUtil.validateTestStep("Verify Random Seat on Book It Tile",
                pageObjectManager.getFlightAvailabilityPage().getBookItTileBenefitsText().get(0).getText(),BOOKIT_RANDOM_SEATS);

        ValidationUtil.validateTestStep("Verify Personal Item on Book It Tile",
                pageObjectManager.getFlightAvailabilityPage().getBookItTileBenefitsText().get(1).getText(),BOOKIT_PERSONAL_ITEM);

        //--Step 10
        ValidationUtil.validateTestStep("More descriptions is present",
                pageObjectManager.getFlightAvailabilityPage().getBookItTileDescriptionText().getText(),BOOKIT_VERBIAGE);

        //--Step 11
        ValidationUtil.validateTestStep("Static message Included is present",
                pageObjectManager.getFlightAvailabilityPage().getBookItTileIncludedInfoText().getText(),BOOKIT_INCLUDED);

        //--Step 12
        ValidationUtil.validateTestStep("Book It button name is correct",
                pageObjectManager.getFlightAvailabilityPage().getBookItButton().get(0).getText(),BOOKIT_BUTTON_TITLE);

        //--Step 13
//        ValidationUtil.validateTestStep("Boost It is the 2nd tile title",
//                pageObjectManager.getFlightAvailabilityPage().getBoosItTitleText().getText(),BOOSTIT_TITLE);
//
//        //--Step 14
//        ValidationUtil.validateTestStep("Save Up To $ flag is present",
//                pageObjectManager.getFlightAvailabilityPage().getBoosItFlagText().getText(),BOOSTIT_FLAG);
//
//        //--Steps 15 Verifying Boost It Benefits list is displayed
//        List<WebElement> boostItBenefits = pageObjectManager.getFlightAvailabilityPage().getBoosItTileBenefitsText();
//
//        for(int i = 0 ;  i < boostItBenefits.size() ; i ++)
//        {
//            String BoostIt[] = new String[]{BOOSTIT_1BENEFITS,BOOSTIT_2BENEFITS,BOOSTIT_3BENEFITS,BOOSTIT_4BENEFITS};
//            ValidationUtil.validateTestStep("Boost It Benefits", boostItBenefits.get(i).getText(),BoostIt[i]);
//
//        }
//
//        //--Step 16 and 17
//        ValidationUtil.validateTestStep("Boots It more info displayed",
//                pageObjectManager.getFlightAvailabilityPage().getBoosItMoreInfoText().getText(),BOOSTIT_VERBIAGE);
//
//        //--Step 18
//        ValidationUtil.validateTestStep("+ $XX.xx",
//                pageObjectManager.getFlightAvailabilityPage().getBoostItSavingPriceText().getText(),BOOSTIT_PLUS);
//
//        ValidationUtil.validateTestStep("MORE",
//                pageObjectManager.getFlightAvailabilityPage().getBoostItSavingPlusText().getText(),BOOSTIT_MORE);
//
//        ValidationUtil.validateTestStep("per person, per way",
//                pageObjectManager.getFlightAvailabilityPage().getBoostItSavingPlusPriceText().getText(),BOOSTIT_PRICE_VERBIAGE);
//
//
//        //--Step 19
//        ValidationUtil.validateTestStep("Boost it button is present",
//                pageObjectManager.getFlightAvailabilityPage().getBoostItButton().get(0).getText(),BOOSTIT_BUTTON);

        //--Step20
        ValidationUtil.validateTestStep("Bundle It is the 3rd column title",
                pageObjectManager.getFlightAvailabilityPage().getBundleTileTitleText().getText(),BUNDLEIT_TITLE);

        //--Step 21
        ValidationUtil.validateTestStep("Save Up To $XX.xx flag is displayed",
                pageObjectManager.getFlightAvailabilityPage().getBundleFlagText().getText(),BUNDLEIT_FLAG);

        //--Step 22 & 23 Verifying Bundle It benefits list
        List<WebElement> bundleItBenefits = pageObjectManager.getFlightAvailabilityPage().getBundleTileBenefitsList();
        for(int i = 0 ;  i < bundleItBenefits.size() ; i ++)
        {
            String BundleIt[] = new String[]{BUNDLEIT_1BENEFITS,BUNDLEIT_2BENEFITS,BUNDLEIT_3BENEFITS,BUNDLEIT_4BENEFITS,BUNDLEIT_5BENEFITS,BUNDLEIT_6BENEFITS,BUNDLEIT_7BENEFITS};
            ValidationUtil.validateTestStep("Bundle Benefits", bundleItBenefits.get(i).getText(),BundleIt[i]);
        }

        //--Step 24
        ValidationUtil.validateTestStep("+ $XX.xx",
                pageObjectManager.getFlightAvailabilityPage().getBundleSavingPriceText().getText(),BUNDLEIT_PLUS);

        ///Step 25 & 26
        ValidationUtil.validateTestStep("Verifying Bundle Title name",
                pageObjectManager.getFlightAvailabilityPage().getBundleItButton().get(0).getText(),BUNDLEIT_BUTTON);
    }

}