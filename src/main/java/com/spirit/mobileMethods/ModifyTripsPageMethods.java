package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.ModifyTripsPage;
import org.openqa.selenium.WebDriver;

public class ModifyTripsPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private ModifyTripsPage modifyTripsPage;

    public ModifyTripsPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        modifyTripsPage = mobileObjectManager.getModifyTripsPage();
    }
}
