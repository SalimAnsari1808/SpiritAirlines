package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.SeatsPage;
import org.openqa.selenium.WebDriver;

public class SeatsPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private SeatsPage seatsPage;

    public SeatsPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        seatsPage = mobileObjectManager.getSeatsPage();
    }
}
