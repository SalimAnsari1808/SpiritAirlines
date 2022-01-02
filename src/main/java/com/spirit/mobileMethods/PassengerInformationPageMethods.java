package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.PassengerInformationPage;
import com.spirit.mobileObjects.PassengerSelectionPage;
import org.openqa.selenium.WebDriver;

public class PassengerInformationPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private PassengerInformationPage passengerInformationPage;

    public PassengerInformationPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        passengerInformationPage = mobileObjectManager.getPassengerInformationPage();
    }
}
