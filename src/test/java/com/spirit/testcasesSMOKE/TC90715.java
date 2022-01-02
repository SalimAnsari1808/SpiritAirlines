package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
//Test Case ID: TC90715
//Description: ADD BAG(S) WITH PLUS (+) OR MINUS (-) Combo
//Created By : Salim Ansari
//Created On : 28-Mar-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 28-Mar-2019
//**********************************************************************************************

public class TC90715 extends BaseClass{

    @Parameters ({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Child" , "Guest" , "Connecting" , "BundleIt" ,"CarryOn", "CheckedBags" , "BagsUI"})
    public void ADD_BAG_WITH_PLUS_OR_MINUS_Combo (@Optional("NA")String platform) {
        /******************************************************************************
         ****************************Navigate to Bags Page*****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90715 under SMOKE Suite on " + platform + " Browser"   , true);
        }


        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "30";
        final String ADULTS				= "2";
        final String CHILDS				= "1";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Connecting";
        final String ARR_Flight 		= "Connecting";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE		= "BundleIt";

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
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /******************************************************************************
         ****************************Validation on Bags Page***************************
         ******************************************************************************/
        //declare constant used in validation
        final String GRAY_COLOR 	= "rgb(204, 204, 204)";
        final int ONE_CHECKED_BAG 	= 1;
        final int TWO_CHECKED_BAG 	= 2;
        final int THREE_CHECKED_BAG = 3;
        final int FOUR_CHECKED_BAG 	= 4;
        final int FIVE_CHECKED_BAG 	= 5;
        final String DOLLOR_SIGN	= "\\$";

        //declare variable used in validation
        String signColor;
        int carryBagCounter;
        int checkedBagCounter;
        double preChecBagPrice;
        double crrChecBagPrice;

        for(int passengerCounter=0;passengerCounter<=2;passengerCounter++) {

            //Verify carry-on bag minus link color before selection is Gray
            ValidationUtil.validateTestStep("*************Verify Bags Details for passenger " + (passengerCounter+1) + "*************", true);

            //*******************************************Departing Checked Bag****************************************************

            //get the current bag price appear for checked Bag
            preChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //*******************************************
            //***Checked Bag after selecting two bags****
            //*******************************************
            //click on checked bag plus link
            pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has increased after adding 2 Departing checked Bags", crrChecBagPrice-preChecBagPrice>0);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 2
            ValidationUtil.validateTestStep("Verify Departing checked bag count after selection is 2", checkedBagCounter==TWO_CHECKED_BAG);

            //*********************************************
            //***Checked Bag after selecting three bags****
            //*********************************************
            //click on checked bag plus link
            pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after adding 3 Departing checked Bags", crrChecBagPrice==preChecBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing checked bag count after selection is 3", checkedBagCounter==THREE_CHECKED_BAG);

            //*********************************************
            //***Checked Bag after selecting four bags*****
            //*********************************************
            //click on checked bag plus link
            pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after adding 4 Departing checked Bags", crrChecBagPrice==preChecBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 4
            ValidationUtil.validateTestStep("Verify Departing checked bag count after selection is 4", checkedBagCounter==FOUR_CHECKED_BAG);

            //*********************************************
            //***Checked Bag after selecting five bags*****
            //*********************************************
            //click on checked bag plus link
            pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();

            //get checked bag plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter), "color");

            //Verify checked bag plus link color after selecting 5 bags is Gray
            ValidationUtil.validateTestStep("Verify checked bag plus link color after selecting 5 bags is Gray", signColor.equals(GRAY_COLOR));

            //verify add Checked for $xx is not displaying on screen
            ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is not displaying on screen for Departing Checked Bags" , pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()==0);

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 5
            ValidationUtil.validateTestStep("Verify Departing checked bag count after selection is 5", checkedBagCounter==FIVE_CHECKED_BAG);

            //*********************************************
            //***Checked Bag cannot be more then 5 bags****
            //*********************************************
            //click on checked bag plus link
            pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count cannot be 6
            ValidationUtil.validateTestStep("Verify Departing Checked bag count cannot be 6", checkedBagCounter==FIVE_CHECKED_BAG);

            //******************************************************
            //**Checked Bag after removing one bag(with 4 bags)*****
            //******************************************************
            //click on checked bag minus link
            pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify checked bag count is 4 after removing one Bag", checkedBagCounter==FOUR_CHECKED_BAG);

            //verify add Checked Bag for $xx is displaying on screen after removing one bag
            ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is displaying on screenafter removing one Departing bag", pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);

            //get the current bag price appear for checked Bag
            preChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //******************************************************
            //**Checked Bag after removing one bag(with 3 bags)*****
            //******************************************************
            //click on checked bag minus link
            pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Departing checked bag count is 3 after removing one Bag", checkedBagCounter==THREE_CHECKED_BAG);

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has same.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after removing one Departing Checked bag(with 3 bags)", crrChecBagPrice==preChecBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //******************************************************
            //**Checked Bag after removing one bag(with 2 bags)*****
            //******************************************************
            //click on checked bag minus link
            pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Departing Checked bag count is 2 after removing one Bag", checkedBagCounter==TWO_CHECKED_BAG);

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has same.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after removing one Departing Checked bag(with 2 bags)", crrChecBagPrice==preChecBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //******************************************************
            //**Checked Bag after removing one bag(with 1 bags)*****
            //******************************************************
            //click on checked bag minus link
            pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter).click();

            //get checked bag minus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(passengerCounter), "color");

            //Verify checked bag plus link color after selecting 5 bags is Gray
            ValidationUtil.validateTestStep("Verify checked bag minus link color after after removing 4 bags is Gray", signColor.equals(GRAY_COLOR));

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Departing Checked bag count is 1 after removing one Bag", checkedBagCounter==ONE_CHECKED_BAG);

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has decreased.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the decreased after removing one Departing Checked bag(with 1 bags)", crrChecBagPrice<preChecBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //*******************************************Returning Checked Bag*****************************************************

            //get the current bag price appear for checked Bag
            preChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //*******************************************
            //***Checked Bag after selecting two bags****
            //*******************************************
            //click on checked bag plus link
            pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has increased after adding 2 Returning Checked Bags", crrChecBagPrice-preChecBagPrice>0);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 2
            ValidationUtil.validateTestStep("Verify Returning Checked bag count after selection is 2", checkedBagCounter==TWO_CHECKED_BAG);

            //*********************************************
            //***Checked Bag after selecting three bags****
            //*********************************************
            //click on checked bag plus link
            pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after adding 3 Returning Checked Bags", crrChecBagPrice==preChecBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Checked bag count after selection is 3", checkedBagCounter==THREE_CHECKED_BAG);

            //*********************************************
            //***Checked Bag after selecting four bags*****
            //*********************************************
            //click on checked bag plus link
            pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after adding 4 Returning Checked Bags", crrChecBagPrice==preChecBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 4
            ValidationUtil.validateTestStep("Verify Returning Checked bag count after selection is 4", checkedBagCounter==FOUR_CHECKED_BAG);

            //*********************************************
            //***Checked Bag after selecting five bags*****
            //*********************************************
            //click on checked bag plus link
            pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();

            //get checked bag plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter), "color");

            //Verify checked bag plus link color after selecting 5 bags is Gray
            ValidationUtil.validateTestStep("Verify Returning Checked bag plus link color after selecting 5 bags is Gray", signColor.equals(GRAY_COLOR));

            //verify add Checked for $xx is not displaying on screen
            ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is not displaying on screen for Returning Checked Bags" , pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()==0);

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 5
            ValidationUtil.validateTestStep("Verify Returning Checked bag count after selection is 5", checkedBagCounter==FIVE_CHECKED_BAG);

            //*********************************************
            //***Checked Bag cannot be more then 5 bags****
            //*********************************************
            //click on checked bag plus link
            pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count cannot be 6
            ValidationUtil.validateTestStep("Verify Returning Checked bag count cannot be 6", checkedBagCounter==FIVE_CHECKED_BAG);

            //******************************************************
            //**Checked Bag after removing one bag(with 4 bags)*****
            //******************************************************
            //click on checked bag minus link
            pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Returning Checked bag count is 4 after removing one Bag", checkedBagCounter==FOUR_CHECKED_BAG);

            //verify add Checked Bag for $xx is displaying on screen after removing one bag
            ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is displaying on screenafter removing one Returning Checked bag", pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);

            //get the current bag price appear for checked Bag
            preChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //******************************************************
            //**Checked Bag after removing one bag(with 3 bags)*****
            //******************************************************
            //click on checked bag minus link
            pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Returning Checked bag count is 3 after removing one Bag", checkedBagCounter==THREE_CHECKED_BAG);

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has same.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after removing one Returning Checked bag(with 3 bags)", crrChecBagPrice==preChecBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //******************************************************
            //**Checked Bag after removing one bag(with 2 bags)*****
            //******************************************************
            //click on checked bag minus link
            pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Returning Checked bag count is 2 after removing one Bag", checkedBagCounter==TWO_CHECKED_BAG);

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has same.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the same after removing one Returning Checked bag(with 2 bags)", crrChecBagPrice==preChecBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;

            //******************************************************
            //**Checked Bag after removing one bag(with 1 bags)*****
            //******************************************************
            //click on checked bag minus link
            pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter).click();

            //get checked bag plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(passengerCounter), "color");

            //Verify checked bag plus link color after selecting 5 bags is Gray
            ValidationUtil.validateTestStep("Verify Returning Checked bag minus link color after removing 4 Checked Bag is Gray", signColor.equals(GRAY_COLOR));

            //get the checked bag counter after selection
            checkedBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(passengerCounter)));

            //verify checked bag count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Returning Checked bag count is 1 after removing one Bag", checkedBagCounter==ONE_CHECKED_BAG);

            //get the current bag price appear for checked Bag
            crrChecBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has decreased.
            ValidationUtil.validateTestStep("\"Add Checked Bag for $XX\" price has Stayed the decreased after removing one Returning Checked bag(with 1 bags)", crrChecBagPrice<preChecBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preChecBagPrice = crrChecBagPrice;
        }
    }

}
