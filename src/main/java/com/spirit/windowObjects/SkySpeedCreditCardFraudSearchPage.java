package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SkySpeedCreditCardFraudSearchPage {

    private WindowsDriver driver;

    public SkySpeedCreditCardFraudSearchPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //SkySpeedCreditCardFraudSearch
    public final String xpath_ResetButton = "//*[@AutomationId = '_buttonReset']";
    @FindBy(xpath=xpath_ResetButton)
    private WebElement btn_Reset;

    public final String xpath_ShowButton = "//*[@AutomationId = '_buttonShow']";
    @FindBy(xpath=xpath_ShowButton)
    private WebElement btn_Show;

    public final String xpath_BinNumberTextBox = "//*[@AutomationId = '_textBinRange']";
    @FindBy(xpath=xpath_BinNumberTextBox)
    private WebElement txtbx_BinNumber;

    public final String xpath_RangeFromDate = "//*[@AutomationId = '???']";
    @FindBy(xpath=xpath_RangeFromDate)
    private WebElement FraudSearchRangeFromDate;

    public final String xpath_RangeToDate = "//*[@AutomationId = '???']";
    @FindBy(xpath=xpath_RangeToDate)
    private WebElement FraudSearchRangeToDate;

    public WebElement getResetButton() {
        return btn_Reset;
    }

    public WebElement getShowButton() {
        return btn_Show;
    }

    public WebElement getBinNumberTextBox() {
        return txtbx_BinNumber;
    }

    public WebElement getRangeFromDate() {
        return FraudSearchRangeFromDate;
    }

    public WebElement getRangeToDate() {
        return FraudSearchRangeToDate;
    }

}
