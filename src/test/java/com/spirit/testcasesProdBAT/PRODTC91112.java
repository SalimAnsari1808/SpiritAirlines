package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

// **********************************************************************************************
// TestCase : CP_Bag-O-Tron New TripTest Scenario
// Description: Test Case will check the Bundle fare on FA page and Uplift Tag on Passenger Page, Hotel Page, Car Page, Activity Page
// Created By : Kartik Chauhan
// Created On : 18-Mar-2019
// Reviewed By: Salim Ansari
// Reviewed On: 30-Mar-2019
// **********************************************************************************************

public class PRODTC91112 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups="Production")

    public void CP_Bag_O_Tron_New_TripTest_Scenario (@Optional("NA")String platform){
        /******************************************************************************
         ****************************Navigate to Bags Page via FA user*****************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC91112 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";

        //Bag-O-Tron Constant Values
        final String TRIP_TYPE          = "NewTrip";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "6";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DISPLAY_PRICE      = "DisplayBagPrice";
        final String BAGS_PATH          = "BookingPath";
        final String BAGS_PRICE         = "Standard";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "nonstop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE		= "BookIt";

        //Bags Page Constant Values
        final String EACH_BAG_PRICE     = "EachBagPrice";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        WaitUtil.untilTimeCompleted(2000);
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHomePage().getOptionalServiceLink());
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bag-O-Tron Page Methods
        pageMethodManager.getOptionalServicesPageMethods().selectTrip(TRIP_TYPE);
        pageMethodManager.getOptionalServicesPageMethods().selectAirports(DEP_AIRPORT_CODE,ARR_AIRPORT_CODE);
        pageMethodManager.getOptionalServicesPageMethods().selectDates(DEP_DATE,ARR_DATE);
        pageMethodManager.getOptionalServicesPageMethods().selectTravellingPassenger(ADULTS,CHILDS,INFANT_LAP,INFANT_SEAT);
        pageMethodManager.getOptionalServicesPageMethods().selectBagoTronButton(DISPLAY_PRICE);
        pageMethodManager.getOptionalServicesPageMethods().setBagOTronBagPrices("Dep",BAGS_PATH,BAGS_PRICE);
        pageMethodManager.getOptionalServicesPageMethods().selectBookTrip();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /******************************************************************************
         ****************************Validation on Bags Page***************************
         ******************************************************************************/
        String eachBagPrice = pageMethodManager.getOptionalServicesPageMethods().getBagOTronBagPrice("Dep",EACH_BAG_PRICE,"0","0");

        //declare constant used in validation
        final String FS_1_CARRY_BAG 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[0]));
        final String FS_1_CHECKED_BAG 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[1]));
        final String FS_2_CHECKED_BAG 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[2]));
        final String FS_3_CHECKED_BAG   = TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[3]));
        final String FS_4_CHECKED_BAG 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[4]));
        final String FS_5_CHECKED_BAG 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[5]));

        //*******************************************
        //**Carry-On Bag after selecting one bag*****
        //*******************************************
        //verify carry bag
        ValidationUtil.validateTestStep("verify 1 Carry on Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCarryOnPriceText().get(0).getText(),FS_1_CARRY_BAG );

		//*******************************************
		//**Checked Bag after selecting first Bag****
		//*******************************************
		//Verify 1 checked bag
		ValidationUtil.validateTestStep("verify 1 Checked Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText(),FS_1_CHECKED_BAG  );

		//*******************************************
		//**Checked Bag after selecting second Bag***
		//*******************************************
        //add one checked bag
		pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 2 checked bag
        ValidationUtil.validateTestStep("verify 2 Checked Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText(),FS_2_CHECKED_BAG  );

		//*******************************************
		//**Checked Bag after selecting Third Bag****
		//*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 3 checked bag
        ValidationUtil.validateTestStep("verify 3 Checked Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText(),FS_3_CHECKED_BAG  );

		//*******************************************
		//**Checked Bag after selecting Fourth Bag***
		//*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 4 checked bag
        ValidationUtil.validateTestStep("verify 4 Checked Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText(),FS_4_CHECKED_BAG  );

		//*******************************************
		//**Checked Bag after selecting Fifth Bag****
		//*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 5 checked bag
        ValidationUtil.validateTestStep("verify 5 Checked Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText(),FS_5_CHECKED_BAG  );

       }

}

