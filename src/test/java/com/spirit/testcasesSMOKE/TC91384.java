package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC91384
//Title      : ADD SPORTING EQUIPMENT WITH PLUS (+) OR MINUS (-) (Surfboards)
//Created By : Alex Rodriguez
//Created On : 22-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 23-Apr-2019
//**********************************************************************************************
public class TC91384 extends BaseClass{
    @Parameters ({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "Guest", "DomesticDomestic" , "Outside21Days", "Adult" , "Connecting" , "BookIt" , "CarryOn" ,"CheckedBags" ,"BagsUI"})
    public void ADD_SPORTING_EQUIPMENT_WITH_PLUS_OR_MINUS_SURFBOARDS(@Optional("NA")String platform) {
        /******************************************************************************
         ****************************Navigate to Bags Page****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91384 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 		    = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE 		    = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE 		    = "25";
        final String ARR_DATE 	        = "30";
        final String ADULTS	            = "2";
        final String CHILDREN	        = "0";
        final String INFANT_LAP		    = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "Connecting";
        final String ARR_Flight 	    = "Connecting";
        final String FARE_TYPE		    = "Standard";
        final String UPGRADE_VALUE		= "BookIt";

        //open browser
        openBrowser( platform);

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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /******************************************************************************
         ****************************Validation on Bags Page***************************
         ******************************************************************************/
        //declare constant used in validation
        final String BLUE_COLOR 	    = "rgb(0, 115, 230)";
        final String GRAY_COLOR         = "rgb(204, 204, 204)";
        final int NO_SURFBOARD 	        = 0;
        final int ONE_SURFBOARD 	    = 1;
        final int TWO_SURFBOARDS 	    = 2;
        final int THREE_SURFBOARDS      = 3;
        final int FOUR_SURFBOARDS 	    = 4;
        final int FIVE_SURFBOARDS 	    = 5;
        final String DOLLAR_SIGN	    = "\\$";

        //declare variable used in validation
        String signColor;
        int surfboardCounter;
        double preSurfboardPrice;
        double crrSurfboardPrice;

        for (int passengerCounter = 0; passengerCounter < 2; passengerCounter++) {

            ValidationUtil.validateTestStep("***********************Passenger:" + (passengerCounter+1) + "***********************", true);

            //*******************************************Departing Surfboard Bag*****************************************************
            pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(passengerCounter).click();
            WaitUtil.untilTimeCompleted(1000);
            //********************************************
            //***Surfboard when no bag is selected******
            //********************************************
            //get surfboard minus link color before selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardMinusButton().get(passengerCounter), "color");

            //verify Surfboard minus link color before selection is Gray
            ValidationUtil.validateTestStep("Verify Departing Surfboard minus link color before selection is Gray", signColor,GRAY_COLOR);

            //get Surfboard plus link color before selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(passengerCounter), "color");

            //verify Surfboard bag minus link color before selection is blue
            ValidationUtil.validateTestStep("Verify Departing Surfboard plus link color before selection is Blue", signColor,BLUE_COLOR);

            //verify add Surfboard for $xx is displaying on screen
            ValidationUtil.validateTestStep("Verify \"Add a Surfboard for $xx\" is displaying on screen",
                    pageObjectManager.getBagsPage().getDepartingNextSurfBoardPriceText().get(passengerCounter).getText().contains("Add Surfboard for"));

            //store bag price in variable
            preSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //get the surfboard cont before selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 0
            ValidationUtil.validateTestStep("Verify Departing surfboard count before selection is 0", surfboardCounter == NO_SURFBOARD);

            //*******************************************
            //***surfboard after selecting one bag*****
            //*******************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(passengerCounter).click();

            //get surfboard minus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardMinusButton().get(passengerCounter), "color");

            //verify surfboard minus link color after selection is Blue
            ValidationUtil.validateTestStep("Verify Departing surfboard minus link color after selection is Blue", signColor,BLUE_COLOR);

            //get surfboard plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(passengerCounter), "color");

            //verify surfboard minus link color after selection is Blue
            ValidationUtil.validateTestStep("Verify Departing surfboard plus link color after selection is Blue", signColor,BLUE_COLOR);

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has increased after adding 1 Departing surfboard", crrSurfboardPrice - preSurfboardPrice > 0);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 1
            ValidationUtil.validateTestStep("Verify Departing surfboard count after selection is 1", surfboardCounter == ONE_SURFBOARD);

            //*******************************************
            //***surfboard after selecting two bags****
            //*******************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(passengerCounter).click();

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);


            //"Add surfboard for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has increased after adding 2 Departing surfboards", crrSurfboardPrice - preSurfboardPrice > 0);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 2
            ValidationUtil.validateTestStep("Verify Departing surfboard count after selection is 2", surfboardCounter == TWO_SURFBOARDS);

            //*********************************************
            //***surfboard after selecting three bags****
            //*********************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(passengerCounter).click();

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the same after adding 3 Departing surfboards", crrSurfboardPrice - preSurfboardPrice > 0);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing surfboard count after selection is 3", surfboardCounter == THREE_SURFBOARDS);

            //*********************************************
            //***surfboard after selecting four bags*****
            //*********************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(passengerCounter).click();

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the same after adding 4 Departing surfboards", crrSurfboardPrice - preSurfboardPrice > 0);

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 4
            ValidationUtil.validateTestStep("Verify Departing surfboard count after selection is 4", surfboardCounter == FOUR_SURFBOARDS);

            //*********************************************
            //***surfboard after selecting five bags*****
            //*********************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(passengerCounter).click();

            //get surfboard plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(passengerCounter), "color");

            //Verify surfboard plus link color after selecting 5 bags is Gray
            ValidationUtil.validateTestStep("Verify surfboard plus link color after selecting 5 bags is Gray", signColor,GRAY_COLOR);

            //verify add surfboard for $xx is not displaying on screen
            ValidationUtil.validateTestStep("Verify \"Add a surfboard for $xx\" is not displaying on screen for Departing surfboards",
                    pageObjectManager.getBagsPage().getDepartingNextSurfBoardPriceText().size() == 1);


            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 5
            ValidationUtil.validateTestStep("Verify Departing surfboard count after selection is 5", surfboardCounter == FIVE_SURFBOARDS);

            //*********************************************
            //***surfboard cannot be more then 5 bags****
            //*********************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot be 6
            ValidationUtil.validateTestStep("Verify Departing surfboard count cannot be 6", surfboardCounter == FIVE_SURFBOARDS);

            //******************************************************
            //**surfboard after removing one bag(with 4 bags)*****
            //******************************************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify surfboard count is 4 after removing one Bag", surfboardCounter == FOUR_SURFBOARDS);

            //verify add surfboard for $xx is displaying on screen after removing one bag
            ValidationUtil.validateTestStep("Verify \"Add a surfboard for $xx\" is displaying on screen after removing one Departing bag",
                    pageObjectManager.getBagsPage().getDepartingNextSurfBoardPriceText().get(passengerCounter).isDisplayed());

            //get the current bag price for surfboard
            preSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //******************************************************
            //**surfboard after removing one bag(with 3 bags)*****
            //******************************************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot is 3 after removing one Bag
            ValidationUtil.validateTestStep("Verify Departing surfboard count is 3 after removing one Bag", surfboardCounter == THREE_SURFBOARDS);

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has same.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the same after removing one Departing surfboard(with 3 bags)",
                    crrSurfboardPrice == preSurfboardPrice);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //******************************************************
            //**surfboard after removing one bag(with 2 bags)*****
            //******************************************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot is 2 after removing one Bag
            ValidationUtil.validateTestStep("Verify Departing surfboard count is 2 after removing one Bag", surfboardCounter == TWO_SURFBOARDS);

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has same.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the same after removing one Departing surfboard(with 2 bags)",
                    crrSurfboardPrice == preSurfboardPrice);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //******************************************************
            //**surfboard after removing one bag(with 1 bags)*****
            //******************************************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Departing surfboard count is 1 after removing one Bag", surfboardCounter == ONE_SURFBOARD);

            //get the current bag price appear for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingNextSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has decreased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the decreased after removing one Departing surfboard(with 1 bags)",
                    crrSurfboardPrice == preSurfboardPrice);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //******************************************************
            //**surfboard after removing one bag(with 0 bags)*****
            //******************************************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Departing surfboard count is 0 after removing one Departing surfboard", surfboardCounter == NO_SURFBOARD);

            //get the current bag price appear for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has decreased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the decreased after removing one Departing surfboard(with 0 bags)",
                    crrSurfboardPrice < preSurfboardPrice);

            //*******************************
            //**surfboard cannot be -1*****
            //*******************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getDepartingSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Departing surfboard count is not -1 after removing one Departing surfboard", surfboardCounter == NO_SURFBOARD);


            //*******************************************Returning surfboard*****************************************************

            pageObjectManager.getBagsPage().getReturningSportingEquipmentLinkButton().get(passengerCounter).click();
            WaitUtil.untilTimeCompleted(1000);

            //********************************************
            //***surfboard when no bag is selected******
            //********************************************
            //get surfboard minus link color before selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardMinusButton().get(passengerCounter), "color");

            //verify surfboard minus link color before selection is Gray
            ValidationUtil.validateTestStep("Verify Returning surfboard minus link color before selection is Gray", signColor,GRAY_COLOR);

            //get surfboard plus link color before selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(passengerCounter), "color");

            //verify surfboard minus link color before selection is blue
            ValidationUtil.validateTestStep("Verify Returning surfboard plus link color before selection is Blue", signColor,BLUE_COLOR);

            //verify add surfboard for $xx is displaying on screen
            ValidationUtil.validateTestStep("Verify \"Add a surfboard for $xx\" is displaying on screen for Returning surfboard",
                    pageObjectManager.getBagsPage().getReturningNextSurfBoardPriceText().get(passengerCounter).isDisplayed());

            //store bag price in variable
            preSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //get the surfboard cont before selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 0
            ValidationUtil.validateTestStep("Verify Returning surfboard count before selection is 0", surfboardCounter == NO_SURFBOARD);

            //*******************************************
            //***surfboard after selecting one bag*****
            //*******************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(passengerCounter).click();

            //get surfboard minus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardMinusButton().get(passengerCounter), "color");

            //verify surfboard minus link color after selection is Blue
            ValidationUtil.validateTestStep("Verify Returning surfboard minus link color after selection is Blue", signColor,BLUE_COLOR);

            //get surfboard plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(passengerCounter), "color");

            //verify surfboard minus link color after selection is Blue
            ValidationUtil.validateTestStep("Verify Returning surfboard plus link color after selection is Blue", signColor,BLUE_COLOR);

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has increased after adding 1 Returning surfboard", crrSurfboardPrice - preSurfboardPrice > 0);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 1
            ValidationUtil.validateTestStep("Verify Returning surfboard count after selection is 1", surfboardCounter == ONE_SURFBOARD);

            //*******************************************
            //***surfboard after selecting two bags****
            //*******************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(passengerCounter).click();

            //get the current bag price appear for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has increased after adding 2 Returning surfboards", crrSurfboardPrice - preSurfboardPrice > 0);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 2
            ValidationUtil.validateTestStep("Verify Returning surfboard count after selection is 2", surfboardCounter == TWO_SURFBOARDS);

            //*********************************************
            //***surfboard after selecting three bags****
            //*********************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(passengerCounter).click();

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the same after adding 3 Returning surfboards", crrSurfboardPrice - preSurfboardPrice > 0);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning surfboard count after selection is 3", surfboardCounter == THREE_SURFBOARDS);

            //*********************************************
            //***surfboard after selecting four bags*****
            //*********************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(passengerCounter).click();

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the same after adding 4 Returning surfboards", crrSurfboardPrice - preSurfboardPrice > 0);

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 4
            ValidationUtil.validateTestStep("Verify Returning surfboard count after selection is 4", surfboardCounter == FOUR_SURFBOARDS);

            //*********************************************
            //***surfboard after selecting five bags*****
            //*********************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(passengerCounter).click();

            //get surfboard plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(passengerCounter), "color");

            //Verify surfboard plus link color after selecting 5 bags is Gray
            ValidationUtil.validateTestStep("Verify Returning surfboard plus link color after selecting 5 bags is Gray", signColor,GRAY_COLOR);

            //verify add surfboard for $xx is not displaying on screen
            ValidationUtil.validateTestStep("Verify \"Add a surfboard for $xx\" is not displaying on screen for Returning surfboards",
                    pageObjectManager.getBagsPage().getReturningNextSurfBoardPriceText().size() == 1);

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count before selection is 5
            ValidationUtil.validateTestStep("Verify Returning surfboard count after selection is 5", surfboardCounter == FIVE_SURFBOARDS);

            //*********************************************
            //***surfboard cannot be more then 5 bags****
            //*********************************************
            //click on surfboard plus link
            pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot be 6
            ValidationUtil.validateTestStep("Verify Returning surfboard count cannot be 6", surfboardCounter == FIVE_SURFBOARDS);

            //******************************************************
            //**surfboard after removing one bag(with 4 bags)*****
            //******************************************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getReturningSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot is 4 after removing one Bag
            ValidationUtil.validateTestStep("Verify Returning surfboard count is 4 after removing one Bag", surfboardCounter == FOUR_SURFBOARDS);

            //verify add surfboard for $xx is displaying on screen after removing one bag
            ValidationUtil.validateTestStep("Verify \"Add a surfboard for $xx\" is displaying on screen after removing one Returning surfboard",
                    pageObjectManager.getBagsPage().getReturningNextSurfBoardPriceText().get(passengerCounter).isDisplayed());

            //get the current bag price appear for surfboard
            preSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //******************************************************
            //**surfboard after removing one bag(with 3 bags)*****
            //******************************************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getReturningSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot is 3 after removing one Bag
            ValidationUtil.validateTestStep("Verify Returning surfboard count is 3 after removing one Bag", surfboardCounter == THREE_SURFBOARDS);

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has same.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the same after removing one Returning surfboard(with 3 bags)",
                    crrSurfboardPrice == preSurfboardPrice);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //******************************************************
            //**surfboard after removing one bag(with 2 bags)*****
            //******************************************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getReturningSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count is 2 after removing one Bag
            ValidationUtil.validateTestStep("Verify Returning surfboard count is 2 after removing one Bag", surfboardCounter == TWO_SURFBOARDS);

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has same.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the same after removing one Returning surfboard(with 2 bags)", crrSurfboardPrice == preSurfboardPrice);

            //store current bag price to previous bag price variable for further testing
            preSurfboardPrice = crrSurfboardPrice;

            //******************************************************
            //**surfboard after removing one bag(with 1 bags)*****
            //******************************************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getReturningSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot is 1 after removing one Bag
            ValidationUtil.validateTestStep("Verify Returning surfboard count is 1 after removing one Bag", surfboardCounter == ONE_SURFBOARD);

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningNextSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has decreased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the decreased after removing one Returning surfboard(with 1 bags)", crrSurfboardPrice == preSurfboardPrice);

            //******************************************************
            //**surfboard after removing one bag(with 0 bags)*****
            //******************************************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getReturningSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot is 0 after removing one Bag
            ValidationUtil.validateTestStep("Verify Returning surfboard count is 0 after removing one Returning surfboard", surfboardCounter == NO_SURFBOARD);

            //get the current bag price for surfboard
            crrSurfboardPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningSurfBoardPriceText().get(passengerCounter).getText().split(DOLLAR_SIGN)[1]);

            //"Add surfboard for $XX" price has decreased.
            ValidationUtil.validateTestStep("\"Add surfboard for $XX\" price has Stayed the decreased after removing one Returning surfboard(with 0 bags)", crrSurfboardPrice < preSurfboardPrice);

            //*******************************
            //**surfboard cannot be -1*****
            //*******************************
            //click on surfboard minus link
            pageObjectManager.getBagsPage().getReturningSurfBoardMinusButton().get(passengerCounter).click();

            //get the surfboard counter after selection
            surfboardCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardCounterTextBox().get(passengerCounter)));

            //verify surfboard count cannot be less than 0
            ValidationUtil.validateTestStep("Verify Returning surfboard count is not -1 after removing one Returning surfboard", surfboardCounter == NO_SURFBOARD);

        }
    }

}
