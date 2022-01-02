package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ModifyTripsPage {

    private AppiumDriver driver;

    public ModifyTripsPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Back Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/iv_back_toolbar")
    private MobileElement btn_TripDeatilsBack;

    //Trip Details Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_title_toolbar")
    private MobileElement txt_TripDetailsHeader;

    //Seats
    @AndroidFindBy(id = "com.spirit.customerapp:id/seats_item_layout")
    private MobileElement btn_ModifySeats;

    //Bags
    @AndroidFindBy(id = "com.spirit.customerapp:id/relative_bags_layout")
    private MobileElement btn_ModifyBags;

    //Options
    @AndroidFindBy(id = "com.spirit.customerapp:id/relative_options_layout")
    private MobileElement btn_ModifyOptions;

    //Change Flight
    @AndroidFindBy(id = "com.spirit.customerapp:id/relative_change_flight_layout")
    private MobileElement btn_ChangeFlight;

    //Cancel Flight
    @AndroidFindBy(id = "com.spirit.customerapp:id/linear_cancel_flight")
    private MobileElement btn_CancelFlight;

    /******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getTripDeatilsBackButton(){
        return btn_TripDeatilsBack;
    }

    public MobileElement getTripDetailsHeaderText(){
        return txt_TripDetailsHeader;
    }

    public MobileElement getModifySeatsButton(){
        return btn_ModifySeats;
    }

    public MobileElement getModifyBagsButton(){
        return btn_ModifyBags;
    }

    public MobileElement getModifyOptionsButton(){
        return btn_ModifyOptions;
    }

    public MobileElement getChangeFlightButton(){
        return btn_ChangeFlight;
    }

    public MobileElement getCancelFlightButton(){
        return btn_CancelFlight;
    }

}
