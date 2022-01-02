package com.spirit.managers;

import com.spirit.mobileObjects.*;
import io.appium.java_client.AppiumDriver;

public class MobileObjectManager {

    private AppiumDriver driver;
    private TripsPage tripsPage;
    private BookPage bookPage;
    private SignInPage signInPage;
    private SeatsPage seatsPage;
    private BagsPage bagsPage;
    private FindATripPage findATripPage;
    private TripDetailsPage tripDetailsPage;
    private FlightStatusPage flightStatusPage;
    private SelectFlightPage selectFlightPage;
    private ModifyTripsPage modifyTripsPage;
    private NavigationFooterPage navigationFooterPage;
    private PassengerInformationPage passengerInformationPage;
    private MyProfilePage myProfilePage;
    private CheckInPage checkInPage;
    private Common common;

    public MobileObjectManager(AppiumDriver driver){
        this.driver = driver;
    }

    public BookPage getBookPage() {
        if(bookPage == null) {
            return bookPage = new BookPage(driver);
        }else {
            return bookPage;
        }
    }

    public FlightStatusPage getFlightStatusPage() {
        if(flightStatusPage == null) {
            return flightStatusPage = new FlightStatusPage(driver);
        }else {
            return flightStatusPage;
        }
    }

    public ModifyTripsPage getModifyTripsPage() {
        if(modifyTripsPage == null) {
            return modifyTripsPage = new ModifyTripsPage(driver);
        }else {
            return modifyTripsPage;
        }
    }

    public NavigationFooterPage getNavigationFooterPage() {
        if(navigationFooterPage == null) {
            return navigationFooterPage = new NavigationFooterPage(driver);
        }else {
            return navigationFooterPage;
        }
    }

    public PassengerInformationPage getPassengerInformationPage() {
        if(passengerInformationPage == null) {
            return passengerInformationPage = new PassengerInformationPage(driver);
        }else {
            return passengerInformationPage;
        }
    }

    public SeatsPage getSeatsPage() {
        if(seatsPage == null) {
            return seatsPage = new SeatsPage(driver);
        }else {
            return seatsPage;
        }
    }

    public SelectFlightPage getSelectFlightPage() {
        if(selectFlightPage == null) {
            return selectFlightPage = new SelectFlightPage(driver);
        }else {
            return selectFlightPage;
        }
    }

    public SignInPage getSignInPage() {
        if(signInPage == null) {
            return signInPage = new SignInPage(driver);
        }else {
            return signInPage;
        }
    }

    public TripDetailsPage getTripDetailsPage() {
        if(tripDetailsPage == null) {
            return tripDetailsPage = new TripDetailsPage(driver);
        }else {
            return tripDetailsPage;
        }
    }

    public TripsPage getTripsPage() {
        if(tripsPage == null) {
            return tripsPage = new TripsPage(driver);
        }else {
            return tripsPage;
        }
    }

    public FindATripPage getFindATripPage() {
        if(findATripPage == null) {
            return findATripPage = new FindATripPage(driver);
        }else {
            return findATripPage;
        }
    }

    public MyProfilePage getMyProfilePage() {
        if(myProfilePage == null) {
            return myProfilePage = new MyProfilePage(driver);
        }else {
            return myProfilePage;
        }
    }

    public Common getCommon() {
        if(common == null) {
            return common = new Common(driver);
        }else {
            return common;
        }
    }

      public CheckInPage getCheckInPage() {
        if(checkInPage == null) {
          return checkInPage = new CheckInPage(driver);
        }else {
          return checkInPage;
        }
      }

    public BagsPage getBagsPage() {
        if(bagsPage == null) {
            return bagsPage = new BagsPage(driver);
        }else {
            return bagsPage;
        }
    }


}
