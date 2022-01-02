package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.lang.management.MonitorInfo;

public class PurchaseConfirmationPage {


    private AppiumDriver driver;

    public PurchaseConfirmationPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Confirmation Icon
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.spirit.customerapp:id/tv_email']/../android.widget.ImageView")
    private MobileElement icn_Confirmation;

    //Email Message
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_email")
    private MobileElement txt_EmailMessage;

    //Origin Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_origin")
    private MobileElement txt_OriginStationCode;

    //Destination Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_destination")
    private MobileElement txt_DestinationStationCode;

    //Confirmation Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvConfirmationCode")
    private MobileElement txt_ConfirmationCode;

    //Departing Journey
    //Date
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_oneway_date")
    private MobileElement txt_DepartingJourneyDate;

    //Origin City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_origin_oneway")
    private MobileElement txt_DepartingJourneyOriginCity;

    //Departure Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_origin_oneway_time")
    private MobileElement txt_DepartingJourneyDepartureTime;

    //Arrival City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_destination_oneway")
    private MobileElement txt_DepartingJourneyArrivalCity;

    //Arrival Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_destination_oneway_time")
    private MobileElement txt_DepartingJourneyArrivalTime;

    //Returning Journey
    //Date
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_round_date")
    private MobileElement txt_ReturningJourneyDate;

    //Origin City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_origin_round")
    private MobileElement txt_ReturningJourneyOriginCity;

    //Departure Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_origin_round_time")
    private MobileElement txt_ReturningJourneyDepartureTime;

    //Arrival City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_destination_round")
    private MobileElement txt_ReturningJourneyArrivalCity;

    //Arrival Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_destination_round_time")
    private MobileElement txt_ReturningJourneyArrivalTime;

    //View Trip Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_done")
    private MobileElement btn_ViewTrip;


    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getConfirmationIcon(){
        return icn_Confirmation;
    }

    public MobileElement getEmailMessageText(){
        return txt_EmailMessage;
    }

    public MobileElement getOriginStationCodeText(){
        return txt_OriginStationCode;
    }

    public MobileElement getDestinationStationCodeText(){
        return txt_DestinationStationCode;
    }

    public MobileElement getConfirmationCodeText(){
        return txt_ConfirmationCode;
    }

    public MobileElement getDepartingJourneyDateText(){
        return txt_DepartingJourneyDate;
    }

    public MobileElement getDepartingJourneyOriginCityText(){
        return txt_DepartingJourneyOriginCity;
    }

    public MobileElement getDepartingJourneyDepartureTimeText(){
        return txt_DepartingJourneyDepartureTime;
    }

    public MobileElement getDepartingJourneyArrivalCityText(){
        return txt_DepartingJourneyArrivalCity;
    }

    public MobileElement getDepartingJourneyArrivalTimeText(){
        return txt_DepartingJourneyArrivalTime;
    }

    public MobileElement getReturningJourneyDateText(){
        return txt_ReturningJourneyDate;
    }

    public MobileElement getReturningJourneyOriginCityText(){
        return txt_ReturningJourneyOriginCity;
    }

    public MobileElement getReturningJourneyDepartureTimeText(){
        return txt_ReturningJourneyDepartureTime;
    }

    public MobileElement getReturningJourneyArrivalCityText(){
        return txt_ReturningJourneyArrivalCity;
    }

    public MobileElement getReturningJourneyArrivalTimeText(){
        return txt_ReturningJourneyArrivalTime;
    }

    public MobileElement getViewTripButton(){
        return btn_ViewTrip;
    }

}