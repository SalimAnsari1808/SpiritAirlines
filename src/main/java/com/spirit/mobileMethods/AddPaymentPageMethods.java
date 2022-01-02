package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.AddPaymentPage;
import org.openqa.selenium.WebDriver;

public class AddPaymentPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private AddPaymentPage paymentPage;

    public AddPaymentPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
//        paymentPage = mobileObjectManager.getAddPaymentPage();
    }
}
