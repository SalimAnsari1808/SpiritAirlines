package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SkySpeedEndRecordPage {

    private WindowsDriver driver;

    public SkySpeedEndRecordPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //EndRecord Label
//    @FindBy(id=Yes
//    @FindBy(id=No
    public final String xpath_EndRecordAndClearRadioButton = "//*[@AutomationId = '_radioButtonEndAndClear']";
    @FindBy(xpath=xpath_EndRecordAndClearRadioButton)
    private WebElement rdbtn_EndRecordAndClear;

    public final String xpath_EndRecordRadioButton = "//*[@AutomationId = '_radioButtonEnd']";
    @FindBy(xpath=xpath_EndRecordRadioButton)
    private WebElement rdbtn_EndRecordRadio;

    public final String xpath_IgnoreChangesRadioButton = "//*[@AutomationId = '_radioButtonIgnoreRedisplay']";
    @FindBy(xpath=xpath_IgnoreChangesRadioButton)
    private WebElement rdbtn_IgnoreChanges;

    public final String xpath_IgnoreChangesAndClearRadioButton = "//*[@AutomationId = '_radioButtonIgnoreClear']";
    @FindBy(xpath=xpath_IgnoreChangesAndClearRadioButton)
    private WebElement rdbtn_IgnoreChangesAndClear;

    public final String xpath_EndRecordItineraryDistribution = "//*[@AutomationId = '????']";
    @FindBy(xpath=xpath_EndRecordItineraryDistribution)
    private WebElement EndRecordItineraryDistribution;

    public final String xpath_NextButton = "//*[@AutomationId = '_buttonContinue']";
    @FindBy(xpath=xpath_NextButton)
    private WebElement btn_Next;



    public WebElement getEndRecordAndClearRadioButton() {
        return rdbtn_EndRecordAndClear;
    }

    public WebElement getRadioRadioButton() {
        return rdbtn_EndRecordRadio;
    }

    public WebElement getIgnoreChangesRadioButton() {
        return rdbtn_IgnoreChanges;
    }

    public WebElement getIgnoreChangesAndClearRadioButton() {
        return rdbtn_IgnoreChangesAndClear;
    }

    public WebElement get_EndRecordItineraryDistribution() {
        return EndRecordItineraryDistribution;
    }

    public WebElement getNextButton() {
        return btn_Next;
    }
}
