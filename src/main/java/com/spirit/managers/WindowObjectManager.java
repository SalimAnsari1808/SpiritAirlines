package com.spirit.managers;
import com.spirit.windowObjects.*;
import io.appium.java_client.windows.WindowsDriver;


public class WindowObjectManager {

    private WindowsDriver                       driver;
    private SkySpeedCommentsPage                skySpeedCommentsPage;
    private SkySpeedContactsPage                skySpeedContactsPage;
    private SkySpeedCreditCardFraudSearchPage   skySpeedCreditCardFraudSearchPage;
    private SkySpeedEndRecordPage               skySpeedEndRecordPage;
    private SkySpeedFeesPage                    skySpeedFeesPage;
    private SkySpeedFlightAvailabilityPage      skySpeedFlightAvailabilityPage;
    private SkySpeedHomePage                    skySpeedHomePage;
    private SkySpeedLogInPage                   skySpeedLogInPage;
    private SkySpeedPassengerPage               skySpeedPassengerPage;
    private SkySpeedPaymentPage                 skySpeedPaymentPage;
    private SkySpeedReservationSummaryPage      skySpeedReservationSummaryPage;
    private SkySpeedReservedFlightPage          skySpeedReservedFlightPage;
    private SkySpeedSeatsPage                   skySpeedSeatsPage;
    private SkySpeedMiscellaneousPage           skySpeedMiscellaneousPage;
    private SkySpeedHeaderPage                  skySpeedHeaderPage;


    //declare constructor to initialize Window Driver
    public WindowObjectManager(WindowsDriver driver) {
        this.driver = driver;
    }

    public SkySpeedCommentsPage getSkySpeedCommentsPage() {
        if(skySpeedCommentsPage == null) {
            return skySpeedCommentsPage = new SkySpeedCommentsPage(driver);
        }else {
            return skySpeedCommentsPage;
        }
    }

    public SkySpeedContactsPage getSkySpeedContactsPage() {
        if(skySpeedContactsPage == null) {
            return skySpeedContactsPage = new SkySpeedContactsPage(driver);
        }else {
            return skySpeedContactsPage;
        }
    }

    public SkySpeedCreditCardFraudSearchPage getSkySpeedCreditCardFraudSearchPage() {
        if(skySpeedCreditCardFraudSearchPage == null) {
            return skySpeedCreditCardFraudSearchPage = new SkySpeedCreditCardFraudSearchPage(driver);
        }else {
            return skySpeedCreditCardFraudSearchPage;
        }
    }

    public SkySpeedEndRecordPage getSkySpeedEndRecordPage() {
        if(skySpeedContactsPage == null) {
            return skySpeedEndRecordPage = new SkySpeedEndRecordPage(driver);
        }else {
            return skySpeedEndRecordPage;
        }
    }

    public SkySpeedFeesPage getSkySpeedFeesPage() {
        if(skySpeedFeesPage == null) {
            return skySpeedFeesPage = new SkySpeedFeesPage(driver);
        }else {
            return skySpeedFeesPage;
        }
    }

    public SkySpeedFlightAvailabilityPage getSkySpeedFlightAvailabilityPage() {
        if(skySpeedFlightAvailabilityPage == null) {
            return skySpeedFlightAvailabilityPage = new SkySpeedFlightAvailabilityPage(driver);
        }else {
            return skySpeedFlightAvailabilityPage;
        }
    }

    public SkySpeedHomePage getSkySpeedHomePage() {
        if(skySpeedHomePage == null) {
            return skySpeedHomePage = new SkySpeedHomePage(driver);
        }else {
            return skySpeedHomePage;
        }
    }

    public SkySpeedLogInPage getSkySpeedLogInPage() {
        if(skySpeedLogInPage == null) {
            return skySpeedLogInPage = new SkySpeedLogInPage(driver);
        }else {
            return skySpeedLogInPage;
        }
    }

    public SkySpeedPassengerPage getSkySpeedPassengerPage() {
        if(skySpeedPassengerPage == null) {
            return skySpeedPassengerPage = new SkySpeedPassengerPage(driver);
        }else {
            return skySpeedPassengerPage;
        }
    }

    public SkySpeedPaymentPage getSkySpeedPaymentPage() {
        if(skySpeedPaymentPage == null) {
            return skySpeedPaymentPage = new SkySpeedPaymentPage(driver);
        }else {
            return skySpeedPaymentPage;
        }
    }

    public SkySpeedReservationSummaryPage getSkySpeedReservationSummaryPage() {
        if(skySpeedReservationSummaryPage == null) {
            return skySpeedReservationSummaryPage = new SkySpeedReservationSummaryPage(driver);
        }else {
            return skySpeedReservationSummaryPage;
        }
    }

    public SkySpeedReservedFlightPage getSkySpeedReservedFlightPage() {
        if(skySpeedReservedFlightPage == null) {
            return skySpeedReservedFlightPage = new SkySpeedReservedFlightPage(driver);
        }else {
            return skySpeedReservedFlightPage;
        }
    }

    public SkySpeedSeatsPage getSkySpeedSeatsPage() {
        if(skySpeedSeatsPage == null) {
            return skySpeedSeatsPage = new SkySpeedSeatsPage(driver);
        }else {
            return skySpeedSeatsPage;
        }
    }

    public SkySpeedMiscellaneousPage getSkySpeedMiscellaneousPage() {
        if(skySpeedMiscellaneousPage == null) {
            return skySpeedMiscellaneousPage = new SkySpeedMiscellaneousPage(driver);
        }else {
            return skySpeedMiscellaneousPage;
        }
    }

    public SkySpeedHeaderPage getSkySpeedHeaderPage() {
        if(skySpeedHeaderPage == null) {
            return skySpeedHeaderPage = new SkySpeedHeaderPage(driver);
        }else {
            return skySpeedHeaderPage;
        }
    }


}
