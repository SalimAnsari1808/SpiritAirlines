package com.spirit.windowObjects;

import com.spirit.managers.FileReaderManager;
import com.spirit.utility.WaitUtil;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SkySpeedLogInPage {

    private WindowsDriver driver;
    int implicitWait;

    public SkySpeedLogInPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        implicitWait = FileReaderManager.getInstance().getConfigReader().getImplicitWait().intValue();
    }

    public final String xpath_UserNameTextBox = "//*[@AutomationId='_textBoxUserID']";
    @FindBy(xpath=xpath_UserNameTextBox)
    private WebElement txtbx_UserName;

    public final String xpath_PasswordTextBox = "//*[@AutomationId='_textBoxPassword']";
    @FindBy(xpath=xpath_PasswordTextBox)
    private WebElement txtbx_Password;

    public final String xpath_LoginButton = "//*[@AutomationId='_buttonOK']";
    @FindBy(xpath=xpath_LoginButton)
    private WebElement btn_Login;

    public WebElement getUserNameTextBox() {
       // WaitUtil.waitforWindowsElement(driver, "xpath", xpath_UserNameTextBox, implicitWait);
        return txtbx_UserName;
    }

    public WebElement getPasswordTextBox() {
        return txtbx_Password;
    }

    public WebElement getLoginButton(){
        return btn_Login;
    }
}
