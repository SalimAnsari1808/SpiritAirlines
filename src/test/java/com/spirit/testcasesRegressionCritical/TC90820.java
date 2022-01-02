package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test CaseID: TC90820
//Title      : Home Page - Trip Planning_CP_BP
//Created By : Kartik chauhan
//Created On : 26-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-July-2019
//**********************************************************************************************
public class TC90820 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"HomeUI"})
    public void Home_Page_Trip_Planning_CP_BP (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Home Page******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90820 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        /******************************************************************************
         *******************************Home Page**************************************
         ******************************************************************************/
        //click on Where we Fly Link
        pageObjectManager.getHomePage().getTripPlanningLink().click();

        //2 sec wait
        WaitUtil.untilTimeCompleted(2000);

        //scroll down to display all verbiages on screen
        JSExecuteUtil.scrollDown(getDriver(),"500");

        //create constant
        final String POPULAR_FLIGHT        = "Popular Flight Destinations";
        final String TOP_VACATION          = "Top Vacation Destinations";
        final String NEW_AND_SEASONAL      = "New and Seasonal Service";
        final String POPULAR_FLIGHT_INFO   = "Spirit Airlines is committed to offering the lowest total price to all the places we fly";
        final String TOP_VACATION_INFO     = "Spirit flies to all the best destinations in the Americas";
        final String NEW_AND_SEASONAL_INFO = "Spirit keeps adding more airplanes and destinations";

        //verify Popular Flight Destination header is displaying
        ValidationUtil.validateTestStep("Popular Flight Destination header is displaying correctly",
                pageObjectManager.getHomePage().getTripPlanningHeaderText().get(0).getText(),POPULAR_FLIGHT);

        //verify Popular Flight Destination Sub header is displaying
        ValidationUtil.validateTestStep("Popular Flight Destination Sub header 'Spirit Airlines is committed...' is displaying correctly",
                pageObjectManager.getHomePage().getTripPlanningHeaderInfoText().get(0).getText(),POPULAR_FLIGHT_INFO);

        //verify Top Vacation Destinations header is displaying
        ValidationUtil.validateTestStep("Top Vacation Destinations header is displaying correctly",
                pageObjectManager.getHomePage().getTripPlanningHeaderText().get(1).getText(),TOP_VACATION);

        //verify Popular Flight Destination Sub header is displaying
        ValidationUtil.validateTestStep("Top Vacation Destinations Sub header 'Spirit flies to all the best..' is displaying correctly",
                pageObjectManager.getHomePage().getTripPlanningHeaderInfoText().get(1).getText(),TOP_VACATION_INFO);

        //verify New and Seasonal Service header is displaying
        ValidationUtil.validateTestStep("New and Seasonal Service header is displaying correctly",
                pageObjectManager.getHomePage().getTripPlanningHeaderText().get(2).getText(),NEW_AND_SEASONAL);

        //verify Popular Flight Destination Sub header is displaying
        ValidationUtil.validateTestStep("New and Seasonal Service Sub header 'Spirit keeps adding more..' is displaying correctly",
                pageObjectManager.getHomePage().getTripPlanningHeaderInfoText().get(2).getText(),NEW_AND_SEASONAL_INFO);

        //create list for all info displaying under Trip planning
        List<String> planningList = new ArrayList<String>();

        //add tax into list
        planningList.add("Flights to New York");
        planningList.add("Dallas flight deals");
        planningList.add("New Orleans for less");
        planningList.add("Best deals to Denver");
        planningList.add("Chicago for less");
        planningList.add("Oakland flight deals");
        planningList.add("Cheap flights to Boston");
        planningList.add("Last minute to Los Angeles");
        planningList.add("Pittsburgh flight deals");
        planningList.add("Orlando for less");
        planningList.add("Jamaica All-inclusive");
        planningList.add("Atlantic City Sale");
        planningList.add("Cancun deals");
        planningList.add("Dom Rep deals");
        planningList.add("Myrtle Beach for less");
        planningList.add("Tampa flight deals");
        planningList.add("Cheap flights to Ft. Lauderdale");
        planningList.add("Las Vegas for less");
        planningList.add("Baltimore to Jamaica non-stop");
        planningList.add("Columbus OH to Las Vegas");
        planningList.add("More service to Vegas");
        planningList.add("Columbus OH to Florida");
        planningList.add("Winter in West Palm Beach FL");
        planningList.add("Detroit to San Diego");
        planningList.add("Richmond VA to Florida");
        planningList.add("Ski Charleston WV");
        planningList.add("Columbus to Myrtle Beach");

        //Below Xpaths are not added into framework
        List<WebElement> allplanningList =  pageObjectManager.getHomePage().getTripPlanningInfoText();

        for (int Counter = 0; Counter < planningList.size(); Counter++){
            if(allplanningList.get(Counter).getText().replaceAll(" ","").contains(planningList.get(Counter).replaceAll(" ",""))){
                //validate Travel Planning info is displaying
                ValidationUtil.validateTestStep("Verbiage " + planningList.get(Counter) + " is displaying under Trip Planning", true);
            }else{
                //validate Travel Planning info is not displaying
                ValidationUtil.validateTestStep("Verbiage " + planningList.get(Counter) + " is not displaying under Trip Planning", false);
            }
        }

        //Validate Travel Planning info are displaying correctly
        ValidationUtil.validateTestStep("All Travel Planning info are displaying correctly", true);
    }
}