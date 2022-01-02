package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.utility.WaitUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;
import java.util.List;

public class CommonMethods {


    private AppiumDriver driver;
    private MobileObjectManager mobileObjectManager;
    private ScenarioContext scenarioContext;

    public  CommonMethods(AppiumDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext){
        this.driver = driver;
        this.mobileObjectManager = mobileObjectManager;
        this.scenarioContext = scenarioContext;
    }


    public void swipeScreen(MobileElement fromElement, MobileElement toElement){

        //get coordinates if first and last elements
        int xInitail    = fromElement.getLocation().getX() + fromElement.getSize().width/2;
        int yInitail    = fromElement.getLocation().getY() + fromElement.getSize().height/2;
        int xFinal      = toElement.getLocation().getX()   + toElement.getSize().width/2;
        int yFinal      = toElement.getLocation().getY()   + toElement.getSize().height/2;

        //create reference of touch action
        TouchAction action = new TouchAction(driver);

        //swipe screen
        action.press(PointOption.point(xInitail,yInitail))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(xFinal,yFinal)).release().perform();

    }


    public void fillPassengerCalender(String calenderDate){

        //declare constant used nmethod
        final int DOB_YEAR  = Integer.parseInt(calenderDate.split("/")[2]);
        final int DOB_MONTH = Integer.parseInt(calenderDate.split("/")[1]);
        final int DOB_DAY   = Integer.parseInt(calenderDate.split("/")[0]);

        //declare variables used in method
        List<MobileElement> yearList;
        boolean yearFlag   = true;



        //click on calender year button
        mobileObjectManager.getCommon().getPassengerCalenderDOBPopUpYearHeaderButton().click();

        for(int waitCounter=0;waitCounter<7;waitCounter++){
            yearList = mobileObjectManager.getCommon().getPassengerCalenderDOBPopUpYearText();

            if(yearList.size()>0){
                //year list loaded break from loop
                break;
            }else{
                //wait for 1 sec
                WaitUtil.untilTimeCompleted(1000);
            }
        }


        yearList = mobileObjectManager.getCommon().getPassengerCalenderDOBPopUpYearText();

        while(yearFlag){

            for(MobileElement requiredYear : yearList){
                if(Integer.parseInt(requiredYear.getAttribute("text"))-DOB_YEAR==0){
                    requiredYear.click();

                    yearFlag    = false;

                    break;
                }
            }

            //get first and last element from year list
            MobileElement firstElement  = yearList.get(0);
            MobileElement lastElement   = yearList.get(yearList.size()-1);

            int xInitail    = 1;
            int yInitail    = 1;
            int xFinal      = 1;
            int yFinal      = 1;

            TouchAction  action = new TouchAction(driver);

            action.press(PointOption.point(xInitail,yInitail))
                  .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                  .moveTo(PointOption.point(xFinal,yFinal)).release().perform();


        }

        WaitUtil.untilTimeCompleted(100);




    }
}
