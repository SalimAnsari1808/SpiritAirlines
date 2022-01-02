package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.PassengerSelectionPage;
import org.openqa.selenium.WebDriver;

public class PassengerSelectionPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private PassengerSelectionPage passengerSelectionPage;

    public PassengerSelectionPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
//        passengerSelectionPage = mobileObjectManager.getPassengerSelectionPage();
    }
}
