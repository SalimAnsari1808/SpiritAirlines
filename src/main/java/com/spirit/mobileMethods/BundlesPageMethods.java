package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.BundlesPage;
import org.openqa.selenium.WebDriver;

public class BundlesPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private BundlesPage bundlesPage;

    public BundlesPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
//        bundlesPage = mobileObjectManager.getBundlesPage();
    }
}
