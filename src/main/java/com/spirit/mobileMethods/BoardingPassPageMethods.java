package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.BoardingPassPage;
import org.openqa.selenium.WebDriver;

public class BoardingPassPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private BoardingPassPage boardingPassPage;

    public BoardingPassPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
//        boardingPassPage = mobileObjectManager.getBoardingPassPage();
    }
}
