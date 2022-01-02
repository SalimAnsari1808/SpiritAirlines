package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.TripSummaryPage;
import org.openqa.selenium.WebDriver;

public class TripSummaryPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private TripSummaryPage tripSummarypage;

    public TripSummaryPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver = driver;
        this.scenarioContext = scenarioContext;
//        tripSummarypage = mobileObjectManager.getTripSummary();
    }
}
