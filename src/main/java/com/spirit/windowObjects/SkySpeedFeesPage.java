package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SkySpeedFeesPage {
    private WindowsDriver driver;

    public SkySpeedFeesPage(WindowsDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Add Fee Window
    public final String xpath_AddFeeButton = "//*[@AutomationId = 'getdriv_buttonAddFee']";
    @FindBy(xpath=xpath_AddFeeButton)
    private WebElement btn_AddFee;

    public final String xpath_FeeSaveFeeButton = "//*[@AutomationId = '_buttonSaveFee']";
    @FindBy(xpath=xpath_FeeSaveFeeButton)
    private WebElement btn_SaveFee;

    public final String xpath_FeeCodeDropDown = "//*[@AutomationId = '_comboBoxFeeCode']";
    @FindBy(xpath=xpath_FeeCodeDropDown)
    private WebElement drpdwn_FeeCode;

    public final String xpath_TotalFeeAmountTextBox = "//*[@AutomationId = '_textBoxAmount']";
    @FindBy(xpath=xpath_TotalFeeAmountTextBox)
    private WebElement txtbx_TotalFeeAmount;

    public final String xpath_CommentForFeeTextBox = "//*[@AutomationId = '_selectedTextBoxComment']";
    @FindBy(xpath=xpath_CommentForFeeTextBox)
    private WebElement txtbx_CommentForFee;

    public final String xpath_FeeTypeTextbox = "//*[@AutomationId = '??????']";
    @FindBy(xpath=xpath_FeeTypeTextbox)
    private WebElement txtbx_FeeType;
////////////////////////////////////////////////////
    public WebElement getAddFeeButton() {
        return btn_AddFee;
    }

    public WebElement getSaveFeeButton() {
        return btn_SaveFee;
    }

    public WebElement getFeeCodeDropDown() {
        return drpdwn_FeeCode;
    }

    public WebElement getTotalFeeAmountTextBox() {
        return txtbx_TotalFeeAmount;
    }

    public WebElement getCommentForFee() {
        return txtbx_CommentForFee;
    }

    public WebElement getFeeType() {
        return txtbx_FeeType;
    }
}
