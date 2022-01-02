package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Common {
    private AppiumDriver driver;

    public Common(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //*******************************************************************
    //***************************Error Message***************************
    //*******************************************************************
    //invalid email input error
    @AndroidFindBy(id = "com.spirit.customerapp:id/textinput_error")
    private MobileElement txt_Error;

    //header notification
    @AndroidFindBy(id="com.spirit.customerapp:id/tv_error_msg")
    private List<MobileElement> txt_HeaderNotification;

    //*******************************************************************
    //***************************Calender********************************
    //*******************************************************************
    //year header
    @AndroidFindBy(id = "android:id/date_picker_header_year")
    private MobileElement btn_PassengerCalenderDOBPopUpYearHeader;

    @AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='android:id/date_picker_year_picker']//android.widget.TextView")
    private List<MobileElement> txt_PassengerCalenderDOBPopUpYear;

    //Date Header
    @AndroidFindBy(id = "android:id/date_picker_header_date")
    private MobileElement txt_PassengerCalenderDOBPopUpDateHeader;

    //Previous Month
    @AndroidFindBy(id = "android:id/prev")
    private MobileElement btn_PassengerCalenderDOBPopUpPreviousMonth;

    //Next Month
    @AndroidFindBy(id = "android:id/next")
    private MobileElement btn_PassengerCalenderDOBPopUpNextMonth;

    //Days of Month
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='android:id/month_view']/android.view.View[@enabled='true']")
    private List<MobileElement> txt_PassengerCalenderDOBPopUpDays;

    //CANCEL button
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'CANCEL')]")
    private MobileElement btn_PassengerCalenderDOBPopUpCancel;

    //OK button
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'OK')]")
    private MobileElement btn_PassengerCalenderDOBPopUpOK;

    //*******************************************************************
    //***************************Back button*****************************
    //*******************************************************************
    @AndroidFindBy(id = "com.spirit.customerapp:id/iv_back_toolbar")
    private MobileElement btn_Back;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_title_toolbar")
    private MobileElement txt_Header;


    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    //*******************************************************************
    //***************************Error Message***************************
    //*******************************************************************
    //invalid email input error
     public MobileElement getErrorText(){
         return txt_Error;
     }

     public List<MobileElement> getHeaderNotificationText(){ return txt_HeaderNotification;}

    //*******************************************************************
    //***************************Calender********************************
    //*******************************************************************
    //year header
    public MobileElement getPassengerCalenderDOBPopUpYearHeaderButton(){
         return btn_PassengerCalenderDOBPopUpYearHeader;
    }

    //calender year list
    public List<MobileElement> getPassengerCalenderDOBPopUpYearText(){
         return txt_PassengerCalenderDOBPopUpYear;
    }

    //Date Header
    public MobileElement getPassengerCalenderDOBPopUpDateHeaderText(){
         return txt_PassengerCalenderDOBPopUpDateHeader;
    }

    //Previous Month
    public MobileElement getPassengerCalenderDOBPopUpPreviousMonthButton(){
         return btn_PassengerCalenderDOBPopUpPreviousMonth;
    }

    //Next Month
    public MobileElement getPassengerCalenderDOBPopUpNextMonthButton(){
         return btn_PassengerCalenderDOBPopUpNextMonth;
    }

    //Days of Month
    public List<MobileElement> getPassengerCalenderDOBPopUpDaysText(){
         return txt_PassengerCalenderDOBPopUpDays;
    }

    //CANCEL button
    public MobileElement getPassengerCalenderDOBPopUpCancel(){
         return btn_PassengerCalenderDOBPopUpCancel;
    }

    //OK button
    public MobileElement getPassengerCalenderDOBPopUpOKButton(){
         return btn_PassengerCalenderDOBPopUpOK;
    }

    //*******************************************************************
    //***************************Back button*****************************
    //*******************************************************************
    public MobileElement getBackButton(){
         return btn_Back;
    }

    public MobileElement getHeaderText(){
         return txt_Header;
    }
}