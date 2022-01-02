package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.HazardousMaterialPolicyPage;
import org.openqa.selenium.WebDriver;

public class HazardousMaterialPolicyPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private HazardousMaterialPolicyPage hazardousMaterialPolicyPage;

    public HazardousMaterialPolicyPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
//        hazardousMaterialPolicyPage = mobileObjectManager.getHazardousMaterialPolicyPage();
    }
}
