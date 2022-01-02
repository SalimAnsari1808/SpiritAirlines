package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.ModifyTripsPage;
import com.spirit.mobileObjects.MorePage;
import org.openqa.selenium.WebDriver;

public class MorePageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private MorePage morePage;

    public MorePageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
//        morePage = mobileObjectManager.getMorePage();
    }
}
