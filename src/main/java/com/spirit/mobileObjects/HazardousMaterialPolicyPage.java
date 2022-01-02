package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HazardousMaterialPolicyPage {

    private AppiumDriver driver;

    public HazardousMaterialPolicyPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Cancel Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_signin")
    private MobileElement btn_HazerdousMaterialCancel;

    //Page Text
    @AndroidFindBy(xpath = "//android.widget.ScrollView//android.widget.LinearLayout//android.widget.TextView")
    private List<MobileElement> txt_HazardousMaterialVerbage;

    //Continue Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_acknowledge")
    private MobileElement btn_AcceptHazmatAndContinue;

    /******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getHazerdousMaterialCancelButton(){
        return btn_HazerdousMaterialCancel;
    }

    public List<MobileElement> getHazardousMaterialVerbageText(){
        return txt_HazardousMaterialVerbage;
    }

    public MobileElement getAcceptHazmatAndContinueButton(){
        return btn_AcceptHazmatAndContinue;
    }
}
