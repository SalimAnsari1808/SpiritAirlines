package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.support.PageFactory;

public class SkySpeedReservationSummaryPage {

    private WindowsDriver driver;

    public SkySpeedReservationSummaryPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



}
