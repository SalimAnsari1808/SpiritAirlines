package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.AddOptions;
import org.openqa.selenium.WebDriver;

public class AddOptionsMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private AddOptions addOptions;

    public AddOptionsMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
//        addOptions = mobileObjectManager.getAddOptions();
    }


}
