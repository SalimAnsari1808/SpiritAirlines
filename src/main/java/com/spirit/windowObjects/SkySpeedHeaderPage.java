package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SkySpeedHeaderPage {
    private WindowsDriver driver;

    public SkySpeedHeaderPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //System DropDown
    public final String xpath_SystemLink = "//*[@name='System']";
    @FindBy(xpath=xpath_SystemLink)
    private WebElement lnk_System;

    public final String xpath_RestoreLink = "//*[@name='Restore']";
    @FindBy(xpath=xpath_RestoreLink)
    private WebElement lnk_Restore;

    public final String xpath_MoveLink = "//*[@name='Move']";
    @FindBy(xpath=xpath_MoveLink)
    private WebElement lnk_Move;

    public final String xpath_SizeLink = "//*[@name='Size']";
    @FindBy(xpath=xpath_SizeLink)
    private WebElement lnk_Size;

    public final String xpath_MaximizeLink = "//*[@name='Minimize']";
    @FindBy(xpath=xpath_MaximizeLink)
    private WebElement lnk_Maximize;

    public final String xpath_MinimizeLink = "//*[@name='Maximize']";
    @FindBy(xpath=xpath_MinimizeLink)
    private WebElement lnk_Minimize;

    public final String xpath_CloseLink = "//*[@name='Close']";
    @FindBy(xpath=xpath_CloseLink)
    private WebElement lnk_Close;

    //System DropDown
    public WebElement getSystemLink(){
        return lnk_System;
    }

    public WebElement getRestoreLink(){
        return lnk_Restore;
    }

    public WebElement getMoveLink(){
        return lnk_Move;
    }

    public WebElement getSizeLink(){
        return lnk_Size;
    }

    public WebElement getMaximizeLink(){
        return lnk_Maximize;
    }

    public WebElement getMinimizeLink(){
        return lnk_Minimize;
    }

    public WebElement getCloseLink(){
        return lnk_Close;
    }


}
