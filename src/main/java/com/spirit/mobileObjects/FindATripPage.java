package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class FindATripPage {

    private AppiumDriver driver;

    public FindATripPage(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //*******************************************************************
    //******************Find a Trip SubPage******************************
    //*******************************************************************
    //Find A Trip Header
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = 'com.spirit.customerapp:id/toolbar']//android.widget.TextView[contains(@text, 'Find a Trip')]")
    private MobileElement txt_FindATripHeader;

    //Cancel Button
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = 'com.spirit.customerapp:id/toolbar']//android.widget.TextView[contains(@text, 'Cancel')]")
    private MobileElement btn_FindATripCancelButton;

    //Last Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_last_name")
    private MobileElement txtbx_LastName;

    //Last Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_confirmation_code")
    private MobileElement txtbx_ConfirmationCode;

    //Add plus button
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'FIND TRIP')]")
    private MobileElement btn_FindTripRetrievePNR;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getFindATripHeaderText(){
        return txt_FindATripHeader;
    }

    public MobileElement getFindATripCancelButton(){
        return btn_FindATripCancelButton;
    }

    public MobileElement getLastNameTextBox(){
        return txtbx_LastName;
    }

    public MobileElement getConfirmationCodeTextBox(){
        return txtbx_ConfirmationCode;
    }

    public MobileElement getFindTripRetrievePNRButton(){
        return btn_FindTripRetrievePNR;
    }
}
