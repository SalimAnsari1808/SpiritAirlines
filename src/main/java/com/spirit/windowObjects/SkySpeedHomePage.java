package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SkySpeedHomePage {

    private WindowsDriver driver;

    public SkySpeedHomePage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
//*******************Market******************************
    public final String xpath_OpenJawCheckBox = "//*[@AutomationId = '_checkBoxOpenJaw']";
    @FindBy(xpath=xpath_OpenJawCheckBox)
    private WebElement chkbx_OpenJaw;

    public final String xpath_FromCityListDropDown = "//*[@AutomationId = '_comboBoxFrom']";
    @FindBy(xpath=xpath_FromCityListDropDown)
    private WebElement drpdwn_FromCityList;

    public final String xpath_ToCityListDropDown = "//*[@AutomationId = '_comboBoxTo']";
    @FindBy(xpath=xpath_ToCityListDropDown)
    private WebElement drpdwn_ToCityList;

    public final String xpath_ReturnToTextBox = "//*[@AutomationId = '_comboBoxReturnTo']";
    @FindBy(xpath=xpath_ReturnToTextBox)
    private WebElement txtbx_ReturnTo;

    public final String xpath_ReturnFromTextBox = "//*[@AutomationId = '_comboBoxReturnFrom']";
    @FindBy(xpath=xpath_ReturnFromTextBox)
    private WebElement txtbx_ReturnFrom;
//****************OutBound Flight******************
    public final String xpath_FromDateTextBox = "//*[@Name='Outbound Flight']//*[@AutomationId='_textBox']";
    @FindBy(xpath=xpath_FromDateTextBox)
    private WebElement txtbx_FromDate;

    public final String xpath_FromDateDownButton = "//*[@Name='Outbound Flight']//*[@AutomationId='_upButton']";
    @FindBy(xpath=xpath_FromDateDownButton)
    private WebElement btn_FromDateDown;
    //_upButton
    //_downButton

//****************Return Flight******************
    public final String xpath_ToDateTextBox = "//*[@Name='Return Flight']//*[@AutomationId='_textBox']";
    @FindBy(xpath=xpath_ToDateTextBox)
    private WebElement txtbx_ToDate;

    public final String xpath_ToDateDownButton = "//*[@Name='Return Flight']//*[@AutomationId='_upButton']";
    @FindBy(xpath=xpath_ToDateDownButton)
    private WebElement btn_ToDateDown;

//****************Query Details******************
    public final String xpath_PaxTypeColumn = "//*[contains(@Name,'type Row')]";
    @FindBy(xpath=xpath_PaxTypeColumn)
    private List<WebElement> txt_PaxTypeColumn;

    public final String xpath_PaxCountColumn = "//*[contains(@Name,'count Row')]";
    @FindBy(xpath=xpath_PaxCountColumn)
    private List<WebElement> txt_PaxCountColumn;

    public final String xpath_FareTypeDropDown = "//*[@AutomationId = '_comboBoxFareType']";
    @FindBy(xpath=xpath_FareTypeDropDown)
    private WebElement drpdwn_FareType;

    public final String xpath_NextButton = "//*[@AutomationId = '_buttonContinue']";
    @FindBy(xpath=xpath_NextButton)
    private WebElement btn_Next;

////////////////////////////

    public WebElement getOpenJawCheckBox() {
        return chkbx_OpenJaw;
    }

    public WebElement getFromCityListDropDown() {
        return drpdwn_FromCityList;
    }

    public WebElement getFromDateTextBox() {
        return txtbx_FromDate;
    }

    public WebElement getFromDateDownButton(){
        return btn_FromDateDown;
    }

    public WebElement getToDateTextBox() {
        return txtbx_ToDate;
    }

    public WebElement getToDateDownButton(){
        return btn_ToDateDown;
    }

    public List<WebElement> getPaxTypeColumnText() {
        return txt_PaxTypeColumn;
    }

    public List<WebElement> getPaxCountColumnText() {
        return txt_PaxCountColumn;
    }

    public WebElement getFareTypeDropDown() {
        return drpdwn_FareType;
    }

    public WebElement getReturnToTextBox() {
        return txtbx_ReturnTo;
    }

    public WebElement getReturnFromTextBox() {
        return txtbx_ReturnFrom;
    }

    public WebElement getToCityListDropDown() {
        return drpdwn_ToCityList;
    }

    public WebElement getNextButton() {
        return btn_Next;
    }


}
