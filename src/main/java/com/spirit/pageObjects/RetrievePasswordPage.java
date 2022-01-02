package com.spirit.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RetrievePasswordPage {

    public RetrievePasswordPage(WebDriver driver) {
        //this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //*******************************************************************
    //**************************Retrive Password Page********************
    //*******************************************************************
    public final String xpath_ResetPasswordButton = "//button[contains(text(),'Send') or contains(text(),'Enviar')]";
    @FindBy(xpath=xpath_ResetPasswordButton)
    private WebElement btn_ResetPassword;

    public final String id_EmailFSNumberTextBox = "emailOrNumberText";
    @FindBy(id=id_EmailFSNumberTextBox)
    private WebElement txtbx_EmailFSNumber;

    public final String xpath_IncorrectEmailHeaderText = "//div[contains(@class,'input-errors')]//div";
    @FindBy(xpath=xpath_IncorrectEmailHeaderText)
    private WebElement txt_IncorrectEmailHeader;

    //*******************************************************************
    //**************************Reset Password Popup*********************
    //*******************************************************************
    public final String xpath_ResetPasswordHeaderText = "//app-password-reset-modal//h2";
    @FindBy(xpath=xpath_ResetPasswordHeaderText)
    private WebElement txt_ResetPasswordHeader;

    public final String xpath_ReserPasswordCloseButton = "//i[@class='icon-close']";
    @FindBy(xpath=xpath_ReserPasswordCloseButton)
    private WebElement btn_ReserPasswordClose;

    public final String xpath_ResetPasswordBodyText = "//app-password-reset-modal//p";
    @FindBy(xpath=xpath_ResetPasswordBodyText)
    private WebElement txt_ResetPasswordBody;

    public final String xpath_ResetPasswordGoToLoginPageButton = "//button[contains(text(),'Go To Login Page') or contains(text(),'Ir a la Página de Inicio de Sesión')]";
    @FindBy(xpath=xpath_ResetPasswordGoToLoginPageButton)
    private WebElement btn_ResetPasswordGoToLoginPage;
    //*******************************************************************
    //**************************Retrive Password Page********************
    //*******************************************************************
    public WebElement getResetPasswordButton() {
        return btn_ResetPassword;
    }

    public WebElement getEmailFSNumberTextBox() {
        return txtbx_EmailFSNumber;
    }

    public WebElement getIncorrectEmailHeaderText() {
        return txt_IncorrectEmailHeader;
    }


    //*******************************************************************
    //**************************Reset Password Popup*********************
    //*******************************************************************
    public WebElement getResetPasswordHeaderText() {
        return txt_ResetPasswordHeader;
    }

    public WebElement getReserPasswordCloseButton() {
        return btn_ReserPasswordClose;
    }


    public WebElement getResetPasswordBodyText() {
        return txt_ResetPasswordBody;
    }

    public WebElement getResetPasswordGoToLoginPageButton() {
        return btn_ResetPasswordGoToLoginPage;
    }
}
