package com.spirit.skySpeedTestCases;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.*;

public class LogInToGoNow extends BaseClass {

    @Parameters({"platform"})
    @Test
    public void SampleTest1 (@Optional("NA")String platform) throws MalformedURLException{



//////Go Now Test
        openWindowApplication("GoNow");

        WaitUtil.untilTimeCompleted(5000);


        //GoNow LogIn window
        WebElement GoNowWindow = getDriver().findElement(By.name("GoNow Login"));
        String GoNowHandle = GoNowWindow.getAttribute("NativeWindowHandle");
        getDriver().switchTo().window(GoNowHandle);

        WebElement userName = getDriver().findElement(By.id("UserIdField"));
        WebElement password = getDriver().findElement(By.id("PasswordField"));

        userName.sendKeys("apagent");
        password.sendKeys("Spirit9");
        userName.click();

        WebElement submit = getDriver().findElement(By.name("OK"));
        submit.click();

        WaitUtil.untilTimeCompleted(5000);

        WebElement advanceSearch = getDriver().findElement(By.id("_advancedSearchButton"));
        advanceSearch.click();

        List<WebElement> originSearch = getDriver().findElements(By.id("PART_EditableTextBox"));
        originSearch.get(0).sendKeys("LAS");
        originSearch.get(1).sendKeys("FLL");

        WebElement advancedSearchButton = getDriver().findElement(By.name("_Search"));
        advancedSearchButton.click();

//////SkySpeed Test

        /*
        openLocalApplication("SkySpeed");

        WaitUtil.untilTimeCompleted(5000);

        //Skyspeed LogIn Window
        WebElement SkySpeedWindow = getDriver().findElement(By.name("Login"));
        String skySpeedHandle = SkySpeedWindow.getAttribute("NativeWindowHandle");
        getDriver().switchTo().window(skySpeedHandle);

        WebElement userName = getDriver().findElement(By.id("_textBoxUserID"));
        WebElement password = getDriver().findElement(By.id("_textBoxPassword"));

        userName.sendKeys("apagent");
        password.sendKeys("Spirit9");
        userName.click();

        //click okay button
        getDriver().findElement(By.id("_buttonOK")).click();
        */
    }
}