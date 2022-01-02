package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.PurchaseConfirmationPage;
import org.openqa.selenium.WebDriver;

public class PurchaseConfirmationPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private PurchaseConfirmationPage purchaseConfirmationPage;

    public PurchaseConfirmationPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
//        purchaseConfirmationPage = mobileObjectManager.getPurchaseConfirmationPage();
    }
}
