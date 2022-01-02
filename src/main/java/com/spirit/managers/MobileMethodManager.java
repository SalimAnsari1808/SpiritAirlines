package com.spirit.managers;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.mobileMethods.*;
import com.spirit.mobileObjects.BagsPage;
import io.appium.java_client.AppiumDriver;

public class MobileMethodManager {

    private AppiumDriver driver;
    private ScenarioContext scenarioContext;
    private MobileObjectManager mobileObjectManager;
    private TripsPageMethods tripsPageMethods;
    private SignInPageMethods signInPageMethods;
    private FindATripPageMethods findATripPageMethods;
    private MyProfilePageMethods myProfilePageMethods;
    private BookPageMethods bookPageMethods;
    private BagsPageMethods bagsPageMethods;

    public MobileMethodManager(AppiumDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.mobileObjectManager = mobileObjectManager;
        this.scenarioContext = scenarioContext;
    }

    public TripsPageMethods getTripsPageMethods() {
        if(tripsPageMethods == null) {
            return tripsPageMethods = new TripsPageMethods(driver, mobileObjectManager, scenarioContext);
        }else {
            return tripsPageMethods;
        }
    }

    public SignInPageMethods getSignInPageMethods() {
        if(signInPageMethods == null) {
            return signInPageMethods = new SignInPageMethods(driver, mobileObjectManager, scenarioContext);
        }else {
            return signInPageMethods;
        }
    }

    public FindATripPageMethods getFindATripPageMethods() {
        if(findATripPageMethods == null) {
            return findATripPageMethods = new FindATripPageMethods(driver, mobileObjectManager, scenarioContext);
        }else {
            return findATripPageMethods;
        }
    }

    public MyProfilePageMethods getMyProfilePageMethods() {
        if(myProfilePageMethods == null) {
            return myProfilePageMethods = new MyProfilePageMethods(driver, mobileObjectManager, scenarioContext);
        }else {
            return myProfilePageMethods;
        }
    }

    public BookPageMethods getBookPageMethods() {
        if(bookPageMethods == null) {
            return bookPageMethods = new BookPageMethods(driver, mobileObjectManager, scenarioContext);
        }else {
            return bookPageMethods;
        }
    }


    public BagsPageMethods getBagsPageMethods() {
        if(bagsPageMethods == null) {
            return bagsPageMethods = new BagsPageMethods(driver, mobileObjectManager, scenarioContext);
        }else {
            return bagsPageMethods;
        }
    }
}
