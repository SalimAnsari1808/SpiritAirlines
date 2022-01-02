package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.CartPage;
import org.openqa.selenium.WebDriver;

public class CartPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private CartPage cartPage;

    public CartPageMethods(WebDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
//        cartPage = mobileObjectManager.getCartPage();
    }
}
