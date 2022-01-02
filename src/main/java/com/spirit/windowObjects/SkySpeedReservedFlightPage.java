package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SkySpeedReservedFlightPage {

    private WindowsDriver driver;

    public SkySpeedReservedFlightPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//*************Reserved Flight**************
    public final String xpath_ReservedFlightList = "//*[@AutomationId = '_treeViewFlights']//*[contains(@Name,'NK-')]";
    @FindBy(xpath=xpath_ReservedFlightList)
    private List<WebElement> txt_ReservedFlightList;

//*************Right pane*******************
    public final String xpath_AddButton = "//*[@AutomationId = '_buttonAdd']";
    @FindBy(xpath=xpath_AddButton)
    private WebElement btn_Add;

    public final String xpath_FareRulesButton = "//*[@AutomationId = '_buttonFareRules']";
    @FindBy(xpath=xpath_FareRulesButton)
    private WebElement btn_FareRules;

    public final String xpath_BagsButton = "//*[@AutomationId = '_sButtonBags']";
    @FindBy(xpath=xpath_BagsButton)
    private WebElement btn_Bags;

    public final String xpath_NextButton = "//*[@AutomationId = '_buttonContinue']";
    @FindBy(xpath=xpath_NextButton)
    private WebElement btn_Next;

    public List<WebElement> getReservedFlightListText() {
        return txt_ReservedFlightList;
    }

    public WebElement getAddButton() {
        return btn_Add;
    }
    public WebElement getFareRulesButton() {
        return btn_FareRules;
    }
    public WebElement getBagsButton() {
        return btn_Bags;
    }
    public WebElement getNextButton() {
        return btn_Next;
    }
}
