package com.spirit.windowMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.WindowObjectManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import com.spirit.windowObjects.SkySpeedSeatsPage;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class SkySpeedSeatsPageMethods {
    private WindowsDriver       driver;
    private WindowObjectManager windowObjectManager;
    private ScenarioContext     scenarioContext;
    private SkySpeedSeatsPage   skySpeedSeatsPage;

    public SkySpeedSeatsPageMethods(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver              = driver;
        this.scenarioContext     = scenarioContext;
        this.windowObjectManager = windowObjectManager;
        skySpeedSeatsPage        = windowObjectManager.getSkySpeedSeatsPage();
    }


    public synchronized void skipSeatSelection(){

        new Actions(driver).click(driver.findElement(By.xpath("//*[@AutomationId = '_buttonSkip']"))).build().perform();

        //click on next button
        ValidationUtil.validateTestStep("Seat selected is skipped on Seat Page", true);
    }

    public synchronized void closePopupOnAssignSeat(){
        for(int counter=0;counter<20;counter++){
            try {
                driver.findElement(By.xpath("//*[@AutomationId = '_buttonClose']")).isDisplayed();

                new Actions(driver).click(driver.findElement(By.xpath("//*[@AutomationId = '_buttonClose']"))).build().perform();

                break;
            } catch (Exception e) {
                //return false
                //return ;
            }

            WaitUtil.untilTimeCompleted(1000);
        }
    }


}
