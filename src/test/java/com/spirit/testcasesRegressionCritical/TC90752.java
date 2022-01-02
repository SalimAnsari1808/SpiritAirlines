package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC90752
// TestCase : BAGS_CP_BP_OW_DOM_1PAX_VALIDATE VERBIAGE IN BAGS BOX
// Created By : Kartik Chauhan
// Created On : 03-July-2019
// Reviewed By:
// Reviewed On:
// **********************************************************************************************
public class TC90752 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" , "BagsUI"})
    public void BAGS_CP_BP_OW_DOM_1PAX_VALIDATE_VERBIAGE_IN_BAGS_BOX (@Optional("NA")String platform) {
        /******************************************************************************
         ****************************Navigate to Bags Page ****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90752 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
//Step 2
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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//STEP--2
        //Bags Page Methods
        String SameCity1[] = scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split(",");

        //Validate correct Departure City pair is displaying on Bags Page
        ValidationUtil.validateTestStep("Departed City Pair Selected on FA page is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(), SameCity1[0]);

        String SameCity2[] = scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split(",");

        //Validate correct Arrival City pair is displaying on Bags Page
        ValidationUtil.validateTestStep("Arrival City Pair Selected on FA page is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(), SameCity2[0]);

//***********************************Only Date section Will be covered by Salim************************************
//STEP--3
      String date1 = TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString(), "dd");

      ValidationUtil.validateTestStep("Selected Date on FA page is displaying on Bags Page",
                        pageObjectManager.getBagsPage().getDepartureDateText().getText(),date1.replace("^0",""));

//STEP--4
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

        //verify by default First name displaying on Bags page
        ValidationUtil.validateTestStep("Verify First name is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingPassengerNameContainerText().get(0).getText(), passengerInfoData.firstName);

        //verify by default Last name displaying on Passport page
        ValidationUtil.validateTestStep("Verify By default Last name is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingPassengerNameContainerText().get(0).getText(), passengerInfoData.lastName);

//STEP--5
        //verify by default Carry on Bag is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Carry on Bag' verbiage is displaying on Bags Page",
                pageObjectManager.getBagsPage().getCarryOnBagText().isDisplayed());
//STEP--6
        //verify by default Carry on Bag is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Checked Bag' verbiage is displaying on Bags Page",
                pageObjectManager.getBagsPage().getCheckedBagText().isDisplayed());

//STEP--7
        //verify by default Carry on Bag Minus Button is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Carry on Bag' Minus Button is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCarryOnMinusButton().get(0).isDisplayed());

        //verify by default Carry on Bag Plus Button is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Carry on Bag' Plus Button is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCarryOnPlusButton().get(0).isDisplayed());

        //verify by default Carry on Bag Plus Button is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Carry on Bag' Counter Box is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(0).isDisplayed());

//STEP--8
        //verify by default Checked Bag Minus Button is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Checked Bag' Minus Button is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(0).isDisplayed());

        //verify by default Checked Bag Plus Button is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Checked Bag' Plus Button is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).isDisplayed());

        //verify by default Checked Bag Plus Button is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Checked Bag' Counter Box is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(0).isDisplayed());
//STEP--9
        //verify by 'Add Carry on $xx' verbiage is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Add Carry on $xx' verbiage is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingNextCarryOnPriceText().get(0).isDisplayed());
//STEP--10
        //verify by 'Add Checked Bag $xx' verbiage is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Add Checked Bag $xx' verbiage is displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).isDisplayed());
//STEP--11
        //verify by 'Departing Price' of Carry on Bags is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Departing Price' of Carry on Bags are displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(0).isDisplayed());
//STEP--12
        //verify by 'Departing Price' of Checked Bags is displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Departing Price' of Checked Bags are displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(0).isDisplayed());
//STEP--13
        //verify by 'Sporting Equipment' link is  displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Sporting Equipment' link is  displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(0).isDisplayed());

        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(0).click();

//STEP--14 & STEP--15
        //*****************As per new application it is not displaying***********************
//STEP--16
        //Create constant
        final String BICYCLE_BRING      = "Bicycle - Bring non-motorized bicycle ";
        final String SURFBOARD_PUTUP    = "Surfboard - Put up to two boards in the same package ";

        //verify by 'Sporting Equipment' link is  displaying on Passport page
        ValidationUtil.validateTestStep("Verify 'Sporting Equipment' link is  displaying on Bags Page",
                pageObjectManager.getBagsPage().getDepartingSportingEquipmentBicycleDescriptionText().get(0).getText(), BICYCLE_BRING);
//STEP--17
        //verify by default 'Sporting Equipment-Bicycle' counter box is displaying on Bags page
        ValidationUtil.validateTestStep("'Sporting Equipment-Bicycle' counter box is displaying on Bags page",
                pageObjectManager.getBagsPage().getDepartingBicycleCounterTextBox().get(0).isDisplayed());
//STEP--18
        //verify by default 'Sporting Equipment-Add Bicycle for $XX' counter box is displaying on Bags page
        ValidationUtil.validateTestStep("'Sporting Equipment-Add Bicycle for $XX' counter box is displaying on Bags page",
                pageObjectManager.getBagsPage().getDepartingNextBicyclePriceText().get(0).isDisplayed());
//STEP--19
        //verify by default 'Sporting Equipment-Bicycle' Price Text is displaying on Bags page
        ValidationUtil.validateTestStep("'Sporting Equipment-Bicycle' Price Text is displaying on Bags page",
                pageObjectManager.getBagsPage().getDepartingBicyclePriceText().get(0).isDisplayed());
//STEP--20
        //verify by default 'SurfBoard'  Text is displaying on Bags page
        ValidationUtil.validateTestStep("'Sporting Equipment-SurfBoard' Text is displaying on Bags page",
                pageObjectManager.getBagsPage().getDepartingSportingEquipmentSurfBoardDescriptionText().get(0).getText(), SURFBOARD_PUTUP);
//STEP--21
        //verify by default 'SurfBoard' Caret Textbox is displaying on Bags page
        ValidationUtil.validateTestStep("'Sporting Equipment-SurfBoard' Caret Text box is displaying on Bags page",
                pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(0).isDisplayed());
//STEP--22
        //verify by default 'Sporting Equipment-Add Surfboard for $XX' counter box is displaying on Bags page
        ValidationUtil.validateTestStep("'Sporting Equipment-Add Surfboard for $XX' counter box is displaying on Bags page",
                pageObjectManager.getBagsPage().getDepartingNextSurfBoardPriceText().get(0).isDisplayed());
//STEP--23
        //verify by default 'Sporting Equipment-Price' Price verbiage is displaying on Bags page
        ValidationUtil.validateTestStep("'Sporting Equipment-Price' counter box is displaying on Bags page",
                pageObjectManager.getBagsPage().getDepartingSurfBoardPriceText().get(0).isDisplayed());
    }
}