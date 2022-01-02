package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.BagsPage;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class BagsPageMethods {

    private AppiumDriver driver;
    private ScenarioContext scenarioContext;
    private BagsPage bagsPage;

    public BagsPageMethods(AppiumDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        bagsPage = mobileObjectManager.getBagsPage();
    }

    // **********************************************************************************************
    //   Method : selectDepartingBags
    // 	 Description: Method is used to select the departing bags on Bags page (Carry On, CheckedBags , Bicycle , SurfBoard)
    // 	 Input Arguments: String ( Carry_1|Checked_2 | bike_1| surf_2||Carry_1|Checked_3|bike_1||Carry_0|Checked_1
    // 	 Return: NA
    // 	 Created By : Anthony Cardona
    // 	 Created On : 29-Oct-2019
    // 	 Reviewed By: Salim Ansari
    // 	 Reviewed On: 30-Oct-2019
    // **********************************************************************************************
    public synchronized void selectDepartingBags(String depBags) {
        //declare variable used in method
        String[] passengerBagsType;
        int carryBagCount       = 0;
        int checkedBagCount     = 0;
        int bicycleCounter      = 0;
        int surfBoardCounter    = 0;

        int paxCounter          = 0;
        String carryCount       = "";
        String carryPrice       = "";
        String checkedCount     = "";
        String checkedPrice     = "";

        //declare constant used in method
        final String CARRY_ON_BAG       = "carry_";
        final String CHECKED_BAG        = "checked_";
        final String BICYCLE            = "bike_";
        final String SURF_BOARD         = "surf_";
        final String NULL_VALUE	        = "";
        final String DOUBLE_SEPARATOR   = "\\|\\|";
        final String SINGLE_SEPARATOR   = "\\|";

        //get all the Bags per passenger
        String[] allDepartureBags = depBags.split(DOUBLE_SEPARATOR) ;

        //loop through all passengers Bags
        for(String perPassengerBags : allDepartureBags) {
            //reset counters
            carryBagCount       = 0;
            checkedBagCount     = 0;
            bicycleCounter      = 0;
            surfBoardCounter    = 0;

            //change passenger from top drop down
            bagsPage.getPassengerSelectDropDown().click();
            bagsPage.getPassengerSelectList().get(paxCounter).click();

            //get bag type and count
            passengerBagsType = perPassengerBags.split(SINGLE_SEPARATOR);

            //loop through bag type to get the bag count
            for(String perPassengerBag:passengerBagsType) {
                //check Bag type to get the count
                if(perPassengerBag.toLowerCase().contains(CARRY_ON_BAG)) {
                    //get carry bag count
                    carryBagCount = Integer.parseInt(perPassengerBag.replace(CARRY_ON_BAG, NULL_VALUE).trim());
                }else if(perPassengerBag.toLowerCase().contains(CHECKED_BAG)) {
                    //get checked bag count
                    checkedBagCount = Integer.parseInt(perPassengerBag.replace(CHECKED_BAG, NULL_VALUE).trim());
                }else if(perPassengerBag.toLowerCase().contains(BICYCLE)) {
                    //get checked bag count
                    bicycleCounter = Integer.parseInt(perPassengerBag.replace(BICYCLE, NULL_VALUE).trim());
                }else if(perPassengerBag.toLowerCase().contains(SURF_BOARD)) {
                    //get checked bag count
                    surfBoardCounter = Integer.parseInt(perPassengerBag.replace(SURF_BOARD, NULL_VALUE).trim());
                }
            }

            //select Carry-On Bags
            if(carryBagCount>0) {
                //click on add carry-on bag button, store price, and select 1 from drop down
                bagsPage.getCarryOnAddButton().click();
                carryPrice = bagsPage.getListOfBagsButton().get(1).getText().substring(bagsPage.getListOfBagsButton().get(1).getText().indexOf("$" + 1));
                bagsPage.getListOfBagsButton().get(1).click();

                ValidationUtil.validateTestStep("1 departure Carry-On Bag selected for Passenger: "+(paxCounter+1), Integer.parseInt(bagsPage.getCarryOnAddButton().getText().replace("CHANGE" , "").trim())==carryBagCount);
            }
            else if (carryBagCount==0){
                //else click 0
                bagsPage.getCarryOnAddButton().click();
                bagsPage.getListOfBagsButton().get(0).click();
            }

            //add checked Bags
            if(checkedBagCount > 0) {
                //select checked bags and store price
                bagsPage.getCheckedBagsAddButton().click();
                checkedPrice = bagsPage.getListOfBagsButton().get(checkedBagCount).getText().substring(bagsPage.getListOfBagsButton().get(checkedBagCount).getText().indexOf("$"));
                bagsPage.getListOfBagsButton().get(checkedBagCount).click();

                ValidationUtil.validateTestStep(checkedBagCount + " departure Checked Bag selected for Passenger: "+(paxCounter+1), Integer.parseInt(bagsPage.getCheckedBagsAddButton().getText().replace("CHANGE" , "").trim())==checkedBagCount);
            }

            //add Bycle
            if(bicycleCounter > 0) {
                //select Bicycle
                bagsPage.getBicycleAddButton().click();
                bagsPage.getListOfBagsButton().get(bicycleCounter).click();

                ValidationUtil.validateTestStep(bicycleCounter + " departure Bicycle selected for Passenger: "+(paxCounter+1), Integer.parseInt(bagsPage.getBicycleAddButton().getText().replace("CHANGE" , "").trim())==bicycleCounter);
            }

            //add SurfBoard
            if(surfBoardCounter > 0) {
                //Select SurfBoard
                bagsPage.getSurfboardAddButton().click();
                WaitUtil.untilTimeCompleted(1000);
                bagsPage.getListOfBagsButton().get(surfBoardCounter).click();

                ValidationUtil.validateTestStep(surfBoardCounter + " departure Surf Board selected for Passenger: "+(paxCounter+1), Integer.parseInt(bagsPage.getSurfboardAddButton().getText().replace("CHANGE" , "").trim())==surfBoardCounter);
            }

            //store departing bags information into Global variable
            //loop through all departing bags
            //check value is already stored in global variable
            if(!scenarioContext.isContains(Context.BAGS_DEP_BAGS)) {
                //set new value in global variable
                scenarioContext.setContext(Context.BAGS_DEP_BAGS, carryCount + "-" + carryPrice.replace("$","") + "|" + checkedCount + "-" + checkedPrice.replace("$",""));
            }else {
                //add new value in global variable
                scenarioContext.setContext(Context.BAGS_DEP_BAGS, scenarioContext.getContext(Context.BAGS_DEP_BAGS).toString() + "||" + carryCount + "-" + carryPrice.replace("$","") + "|" + checkedCount + "-" + checkedPrice.replace("$",""));
            }

            //Increment passenger counter
            paxCounter = paxCounter + 1;
        }
    }

    // **********************************************************************************************
    //   Method : selectReturningBags
    // 	 Description: Method is used to select the returning bags on Bags page (Carry On, CheckedBags , Bicycle , SurfBoard)
    // 	 Input Arguments: String ( Carry_1|Checked_2 | bike_1| surf_2||Carry_1|Checked_3|bike_1||Carry_0|Checked_1
    // 	 Return: NA
    // 	 Created By : Anthony Cardona
    // 	 Created On : 29-Oct-2019
    // 	 Reviewed By: Salim Ansari
    // 	 Reviewed On: 30-Oct-2019
    // **********************************************************************************************
    public synchronized void selectReturningBags(String retBags) {
        //declare variable used in method
        String[] passengerBagsType;
        int carryBagCount       = 0;
        int checkedBagCount     = 0;
        int bicycleCounter      = 0;
        int surfBoardCounter    = 0;

        int paxCounter          = 0;
        String carryCount       = "";
        String carryPrice       = "";
        String checkedCount     = "";
        String checkedPrice     = "";

        //declare constant used in method
        final String CARRY_ON_BAG       = "carry_";
        final String CHECKED_BAG        = "checked_";
        final String BICYCLE            = "bike_";
        final String SURF_BOARD         = "surf_";
        final String NULL_VALUE	        = "";
        final String DOUBLE_SEPARATOR   = "\\|\\|";
        final String SINGLE_SEPARATOR   = "\\|";

        //get all the Bags per passenger
        String[] allReturnBags = retBags.split(DOUBLE_SEPARATOR) ;

        //loop through all passengers Bags
        for(String perPassengerBags : allReturnBags) {
            //reset counters
            carryBagCount       = 0;
            checkedBagCount     = 0;
            bicycleCounter      = 0;
            surfBoardCounter    = 0;

            //change passenger from top drop down
            bagsPage.getPassengerSelectDropDown().click();
            bagsPage.getPassengerSelectList().get(paxCounter).click();

            //get bag type and count
            passengerBagsType = perPassengerBags.split(SINGLE_SEPARATOR);

            //loop through bag type to get the bag count
            for(String perPassengerBag:passengerBagsType) {
                //check Bag type to get the count
                if(perPassengerBag.toLowerCase().contains(CARRY_ON_BAG)) {
                    //get carry bag count
                    carryBagCount = Integer.parseInt(perPassengerBag.replace(CARRY_ON_BAG, NULL_VALUE).trim());
                }else if(perPassengerBag.toLowerCase().contains(CHECKED_BAG)) {
                    //get checked bag count
                    checkedBagCount = Integer.parseInt(perPassengerBag.replace(CHECKED_BAG, NULL_VALUE).trim());
                }else if(perPassengerBag.toLowerCase().contains(BICYCLE)) {
                    //get checked bag count
                    bicycleCounter = Integer.parseInt(perPassengerBag.replace(BICYCLE, NULL_VALUE).trim());
                }else if(perPassengerBag.toLowerCase().contains(SURF_BOARD)) {
                    //get checked bag count
                    surfBoardCounter = Integer.parseInt(perPassengerBag.replace(SURF_BOARD, NULL_VALUE).trim());
                }
            }

            //select Carry-On Bags
            if(carryBagCount>0) {
                //click on add carry-on bag button, store price, and select 1 from drop down
                bagsPage.getCarryOnAddButton().click();
                carryPrice = bagsPage.getListOfBagsButton().get(1).getText().substring(bagsPage.getListOfBagsButton().get(1).getText().indexOf("$" + 1));
                bagsPage.getListOfBagsButton().get(1).click();

                ValidationUtil.validateTestStep("1 return Carry-On Bag selected for Passenger: "+(paxCounter+1), Integer.parseInt(bagsPage.getCarryOnAddButton().getText().replace("CHANGE" , "").trim())==carryBagCount);
            }
            else if (carryBagCount==0){
                //else click 0
                bagsPage.getCarryOnAddButton().click();
                bagsPage.getListOfBagsButton().get(0).click();
            }

            //add checked Bags
            if(checkedBagCount > 0) {
                //select checked bags and store price
                bagsPage.getCheckedBagsAddButton().click();
                checkedPrice = bagsPage.getListOfBagsButton().get(checkedBagCount).getText().substring(bagsPage.getListOfBagsButton().get(checkedBagCount).getText().indexOf("$"));
                bagsPage.getListOfBagsButton().get(checkedBagCount).click();

                ValidationUtil.validateTestStep(checkedBagCount + " return Checked Bag selected for Passenger: "+(paxCounter+1), Integer.parseInt(bagsPage.getCheckedBagsAddButton().getText().replace("CHANGE" , "").trim())==checkedBagCount);
            }

            //add Bicycle
            if(bicycleCounter > 0) {
                //select Bicycle
                bagsPage.getBicycleAddButton().click();
                bagsPage.getListOfBagsButton().get(bicycleCounter).click();

                ValidationUtil.validateTestStep(bicycleCounter + " return Bicycle selected for Passenger: "+(paxCounter+1), Integer.parseInt(bagsPage.getBicycleAddButton().getText().replace("CHANGE" , "").trim())==bicycleCounter);
            }

            //add SurfBoard
            if(surfBoardCounter > 0) {
                //Select SurfBoard
                bagsPage.getSurfboardAddButton().click();
                WaitUtil.untilTimeCompleted(1000);
                bagsPage.getListOfBagsButton().get(surfBoardCounter).click();

                ValidationUtil.validateTestStep(surfBoardCounter + " return Surf Board selected for Passenger: "+(paxCounter+1), Integer.parseInt(bagsPage.getSurfboardAddButton().getText().replace("CHANGE" , "").trim())==surfBoardCounter);
            }

            //store returning bags information into Global variable
            //loop through all returning bags
            //check value is already stored in global variable
            if(!scenarioContext.isContains(Context.BAGS_RET_BAGS)) {
                //set new value in global variable
                scenarioContext.setContext(Context.BAGS_RET_BAGS, carryCount + "-" + carryPrice.replace("$", "") + "|" + checkedCount + "-" + checkedPrice.replace("$", ""));
            }else {
                //add new value in global variable
                scenarioContext.setContext(Context.BAGS_RET_BAGS, scenarioContext.getContext(Context.BAGS_RET_BAGS).toString() + "||" + carryCount + "-" + carryPrice.replace("$", "") + "|" + checkedCount + "-" + checkedPrice.replace("$", ""));
            }

            //Increment passenger counter
            paxCounter = paxCounter + 1;
        }
    }
}
