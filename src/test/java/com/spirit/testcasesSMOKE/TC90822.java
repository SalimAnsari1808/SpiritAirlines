package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
//TODO: [IN:13263]CP: BP: Bags Page DB: Passengers are not being charged for bags correctly when compared using Bag-O-Tron (Total cost and Per Bag don't match)

// TestCase : CP_Bag-O-Tron New Trip_RT
// Description: Validate that prices displayed in bag-o-tron are consistent when completing a booking
// Created By : Alex Rodriguez
// Created On : 8-April-2019
// Reviewed By: Salim Ansari
// Reviewed On: 29-Apr-2019
// **********************************************************************************************
public class TC90822 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups = {"ActiveBug","BagOTron" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Nonstop" , "BookIt" , "CarryOn" , "CheckedBags","OptionalServicesUI","BagsUI" })
    public void CP_Bag_O_Tron_New_Trip_RT (@Optional("NA")String platform){
        //************************Navigate to Bags Page ********************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90822 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";

        //Bag-O-Tron Constant Values
        final String TRIP_TYPE          = "NewTrip";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "6";
        final String ARR_DATE 			= "8";
        final String ADULTS				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DISPLAY_PRICE      = "DisplayBagPrice";
        final String BAGS_PATH          = "BookingPath";
        final String BAGS_PRICE         = "Standard";
        final String DIFF_PRICE_TEXT    = "Bag prices vary based on dates of travel, route, and channel of purchase. Buy your bags when booking at Spirit.com to save the most!";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "nonstop";
        final String RET_FLIGHT 		= "nonstop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE	    = "BookIt";

        //Bags Page Constant Values
        final String EACH_BAG_PRICE     = "EachBagPrice";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getHomePage().getOptionalServiceLink().click();

        //Bag-O-Tron Page Methods
        pageMethodManager.getOptionalServicesPageMethods().selectTrip(TRIP_TYPE);
        pageMethodManager.getOptionalServicesPageMethods().selectAirports(DEP_AIRPORT_CODE,ARR_AIRPORT_CODE);
        pageMethodManager.getOptionalServicesPageMethods().selectDates(DEP_DATE,ARR_DATE);
        pageMethodManager.getOptionalServicesPageMethods().selectTravellingPassenger(ADULTS,CHILD,INFANT_LAP,INFANT_SEAT);
        pageMethodManager.getOptionalServicesPageMethods().selectBagoTronButton(DISPLAY_PRICE);

        //click on different price link
        pageObjectManager.getOptionalServicesPage().getBagPriceDifferentPriceLink().click();

        //verify different price link verbiage
        ValidationUtil.validateTestStep("User verify Different Price verbiage appear for Return Journey on Bag-O-Tron Page",
                pageObjectManager.getOptionalServicesPage().getBagPriceDifferentPriceVerbiageText().getText(),DIFF_PRICE_TEXT);


        //click on different price link
        pageObjectManager.getOptionalServicesPage().getBagPriceDifferentPriceLink().click();

        //verify different price link verbiage
        ValidationUtil.validateTestStep("User verify Different Price verbiage not appear for Return Journey on Bag-O-Tron Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionalServicesPage().getBagPriceDifferentPriceVerbiageText()));


        pageMethodManager.getOptionalServicesPageMethods().setBagOTronBagPrices("Dep",BAGS_PATH,BAGS_PRICE);
        pageMethodManager.getOptionalServicesPageMethods().setBagOTronBagPrices("Ret",BAGS_PATH,BAGS_PRICE);
        pageMethodManager.getOptionalServicesPageMethods().selectBookTrip();



        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //****************************Validation on Bags Page****************************/
        //get departing Bag-O-Tron bags price
        String eachBagPrice = pageMethodManager.getOptionalServicesPageMethods().getBagOTronBagPrice("Dep",EACH_BAG_PRICE,"0","0");

        //declare constant used in validation
        final String DEP_CARRY_BAG1 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[0]));
        final String DEP_CHECKED_BAG1 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[1]));
        final String DEP_CHECKED_BAG2 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[2]));
        final String DEP_CHECKED_BAG3   = TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[3]));
        final String DEP_CHECKED_BAG4 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[4]));
        final String DEP_CHECKED_BAG5 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[5]));

        //*******************************************
        //**Carry-On Bag after selecting one bag*****
        //*******************************************
        //verify carry bag
        //TODO: [IN:13263]
        ValidationUtil.validateTestStep("verify Departure 1 Carry on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getDepartingNextCarryOnPriceText().get(0).getText().toString(),DEP_CARRY_BAG1 );

        //*******************************************
        //**Checked Bag after selecting first Bag****
        //*******************************************
        //Verify 1 checked bag
        ValidationUtil.validateTestStep("verify Departure 1 Checked on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText(),DEP_CHECKED_BAG1  );

        //*******************************************
        //**Checked Bag after selecting second Bag***
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 2 checked bag
        ValidationUtil.validateTestStep("verify Departure 2 Checked on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText(),DEP_CHECKED_BAG2  );

        //*******************************************
        //**Checked Bag after selecting Third Bag****
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 3 checked bag
        ValidationUtil.validateTestStep("verify Departure 3 Checked on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText(),DEP_CHECKED_BAG3  );

        //*******************************************
        //**Checked Bag after selecting Fourth Bag***
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 4 checked bag
        ValidationUtil.validateTestStep("verify Departure 4 Checked on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText(),DEP_CHECKED_BAG4  );

        //*******************************************
        //**Checked Bag after selecting Fifth Bag****
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 5 checked bag
        ValidationUtil.validateTestStep("verify Departure 5 Checked on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText(),DEP_CHECKED_BAG5  );


        //get departing Bag-O-Tron bags price
        eachBagPrice = pageMethodManager.getOptionalServicesPageMethods().getBagOTronBagPrice("Dep",EACH_BAG_PRICE,"0","0");

        //declare constant used in validation
        final String RET_CARRY_BAG1 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[0]));
        final String RET_CHECKED_BAG1 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[1]));
        final String RET_CHECKED_BAG2 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[2]));
        final String RET_CHECKED_BAG3   = TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[3]));
        final String RET_CHECKED_BAG4 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[4]));
        final String RET_CHECKED_BAG5 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[5]));

        //*******************************************
        //**Carry-On Bag after selecting one bag*****
        //*******************************************
        //verify carry bag
        ValidationUtil.validateTestStep("verify Returning 1 Carry on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getReturningNextCarryOnPriceText().get(0).getText(),RET_CARRY_BAG1 );

        //*******************************************
        //**Checked Bag after selecting first Bag****
        //*******************************************
        //Verify 1 checked bag
        ValidationUtil.validateTestStep("verify Returning 1 Checked on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(0).getText(),RET_CHECKED_BAG1  );

        //*******************************************
        //**Checked Bag after selecting second Bag***
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(0).click();

        //Verify 2 checked bag
        ValidationUtil.validateTestStep("verify Returning 2 Checked on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(0).getText(),RET_CHECKED_BAG2  );

        //*******************************************
        //**Checked Bag after selecting Third Bag****
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(0).click();

        //Verify 3 checked bag
        ValidationUtil.validateTestStep("verify Returning 3 Checked on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(0).getText(),RET_CHECKED_BAG3  );

        //*******************************************
        //**Checked Bag after selecting Fourth Bag***
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(0).click();

        //Verify 4 checked bag
        ValidationUtil.validateTestStep("verify Returning 4 Checked on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(0).getText(),RET_CHECKED_BAG4  );

        //*******************************************
        //**Checked Bag after selecting Fifth Bag****
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(0).click();

        //Verify 5 checked bag
        ValidationUtil.validateTestStep("verify Returning 5 Checked on Bags Price with Bag-O-Tron Bag Price on Bags Page",
                pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(0).getText(),RET_CHECKED_BAG5  );
    }

}

