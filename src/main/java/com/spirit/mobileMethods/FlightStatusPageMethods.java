package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.FlightStatusPage;
import org.openqa.selenium.WebDriver;

public class FlightStatusPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private FlightStatusPage flightStatusPage;

    public FlightStatusPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        flightStatusPage = mobileObjectManager.getFlightStatusPage();
    }
}
