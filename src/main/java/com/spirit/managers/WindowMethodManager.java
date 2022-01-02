package com.spirit.managers;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.windowMethods.*;
import io.appium.java_client.windows.WindowsDriver;

public class WindowMethodManager {
    private WindowsDriver                           driver;
    private WindowObjectManager                     windowObjectManager;
    private ScenarioContext                         scenarioContext;
    private SkySpeedLogInPageMethods                skySpeedLogInPageMethods;
    private SkySpeedHomePageMethods                 skySpeedHomePageMethods;
    private SkySpeedFlightAvailabilityPageMethods   skySpeedFlightAvailabilityPageMethods;
    private SkySpeedReservedFlightPageMethods       skySpeedReservedFlightPageMethods;
    private SkySpeedPassengerPageMethods            skySpeedPassengerPageMethods;
    private SkySpeedContactsPageMethods             skySpeedContactsPageMethods;
    private SkySpeedSeatsPageMethods                skySpeedSeatsPageMethods;
    private SkySpeedPaymentPageMethods              skySpeedPaymentPageMethods;
    private SkySpeedCommentsPageMethods             skySpeedCommentsPageMethods;
    private SkySpeedEndRecordPageMethods            skySpeedEndRecordPageMethods;

    public WindowMethodManager(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver              = driver;
        this.scenarioContext     = scenarioContext;
        this.windowObjectManager = windowObjectManager;
    }

    public SkySpeedLogInPageMethods getSkySpeedLogInPageMethods() {
        if(skySpeedLogInPageMethods == null) {
            return skySpeedLogInPageMethods = new SkySpeedLogInPageMethods(driver, windowObjectManager, scenarioContext);
        }else {
            return skySpeedLogInPageMethods;
        }
    }

    public SkySpeedHomePageMethods getSkySpeedHomePageMethods() {
        if(skySpeedHomePageMethods == null) {
            return skySpeedHomePageMethods = new SkySpeedHomePageMethods(driver, windowObjectManager, scenarioContext);
        }else {
            return skySpeedHomePageMethods;
        }
    }

    public SkySpeedFlightAvailabilityPageMethods getSkySpeedFlightAvailabilityPageMethods() {
        if(skySpeedFlightAvailabilityPageMethods == null) {
            return skySpeedFlightAvailabilityPageMethods = new SkySpeedFlightAvailabilityPageMethods(driver, windowObjectManager, scenarioContext);
        }else {
            return skySpeedFlightAvailabilityPageMethods;
        }
    }

    public SkySpeedReservedFlightPageMethods getSkySpeedReservedFlightPageMethods() {
        if(skySpeedReservedFlightPageMethods == null) {
            return skySpeedReservedFlightPageMethods = new SkySpeedReservedFlightPageMethods(driver, windowObjectManager, scenarioContext);
        }else {
            return skySpeedReservedFlightPageMethods;
        }
    }

    public SkySpeedPassengerPageMethods getSkySpeedPassengerPageMethods() {
        if(skySpeedPassengerPageMethods == null) {
            return skySpeedPassengerPageMethods = new SkySpeedPassengerPageMethods(driver, windowObjectManager, scenarioContext);
        }else {
            return skySpeedPassengerPageMethods;
        }
    }

    public SkySpeedContactsPageMethods getSkySpeedContactsPageMethods() {
        if(skySpeedContactsPageMethods == null) {
            return skySpeedContactsPageMethods = new SkySpeedContactsPageMethods(driver, windowObjectManager, scenarioContext);
        }else {
            return skySpeedContactsPageMethods;
        }
    }

    public SkySpeedSeatsPageMethods getSkySpeedSeatsPageMethods() {
        if(skySpeedSeatsPageMethods == null) {
            return skySpeedSeatsPageMethods = new SkySpeedSeatsPageMethods(driver, windowObjectManager, scenarioContext);
        }else {
            return skySpeedSeatsPageMethods;
        }
    }

    public SkySpeedPaymentPageMethods getSkySpeedPaymentPageMethods() {
        if(skySpeedPaymentPageMethods == null) {
            return skySpeedPaymentPageMethods = new SkySpeedPaymentPageMethods(driver, windowObjectManager, scenarioContext);
        }else {
            return skySpeedPaymentPageMethods;
        }
    }

    public SkySpeedCommentsPageMethods getSkySpeedCommentsPageMethods() {
        if(skySpeedCommentsPageMethods == null) {
            return skySpeedCommentsPageMethods = new SkySpeedCommentsPageMethods(driver, windowObjectManager, scenarioContext);
        }else {
            return skySpeedCommentsPageMethods;
        }
    }

    public SkySpeedEndRecordPageMethods getSkySpeedEndRecordPageMethods() {
        if(skySpeedEndRecordPageMethods == null) {
            return skySpeedEndRecordPageMethods = new SkySpeedEndRecordPageMethods(driver, windowObjectManager, scenarioContext);
        }else {
            return skySpeedEndRecordPageMethods;
        }
    }
}
