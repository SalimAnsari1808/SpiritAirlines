package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.TripDetailsPage;
import org.openqa.selenium.WebDriver;

public class TripDetailsPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private TripDetailsPage tripDetailsPage;

    public TripDetailsPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        tripDetailsPage = mobileObjectManager.getTripDetailsPage();
    }
}
