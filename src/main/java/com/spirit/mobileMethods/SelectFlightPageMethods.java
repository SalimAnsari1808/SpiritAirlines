package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.SelectFlightPage;
import org.openqa.selenium.WebDriver;

public class SelectFlightPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private SelectFlightPage selectFlightPage;

    public SelectFlightPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        selectFlightPage = mobileObjectManager.getSelectFlightPage();
    }
}
