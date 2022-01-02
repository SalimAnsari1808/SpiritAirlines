package com.spirit.mobileMethods;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.managers.*;
import com.spirit.mobileObjects.*;
import io.appium.java_client.*;
import io.appium.java_client.touch.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;

import java.time.*;
import java.util.*;

public class CheckInPageMethods {

  private AppiumDriver driver;
  private ScenarioContext scenarioContext;
  private CheckInPage checkInPage;
  private MobileObjectManager mobileObjectManager;
  private CheckInPageMethods checkInPageMethods;


    public CheckInPageMethods(AppiumDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
//        checkInPage = mobileObjectManager.getCheckInPage();
    }

  public synchronized void retrieveBooking(){
    WaitOptions.waitOptions(Duration.ofSeconds(5));
//    scenarioContext.setContext(Context.CONFIRMATION_LASTNAME , "AUTOMATION");
//    scenarioContext.setContext(Context.CONFIRMATION_CODE , "KF3KMA");
    System.out.println("PNR: " + scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());
    System.out.println("Last Name: " + scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());
    String lastName = scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString();
    mobileObjectManager.getCheckInPage().getLastNameInputTextBox().sendKeys(lastName);
    System.out.println(lastName);
    String confirmationCode = scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString();
    mobileObjectManager.getCheckInPage().getConfirmationCodeInputTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());
    System.out.println(confirmationCode);
    mobileObjectManager.getCheckInPage().getCheckInButton().click();
  }

  public void verticalSwipe(String locator) {

    MobileElement slider = (MobileElement) driver.findElementById(locator);
    Point source = slider.getCenter();
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence dragNDrop = new Sequence(finger, 1);
    dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
            PointerInput.Origin.viewport(),
            source.x / 2, source.y + 400));
    dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
    dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(600),
            PointerInput.Origin.viewport(), source.getX() / 2, source.y / 2));
    dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
    driver.perform(Arrays.asList(dragNDrop));
  }
}
