package com.spirit.mobileObjects;

import com.spirit.managers.MobileObjectManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PassengerSelectionPage {

    private AppiumDriver driver;

    public PassengerSelectionPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Trip Details Header
    @AndroidFindBy(xpath = "//android.view.View[@text='CHOOSE PASSENGERS TO CHECK IN']")
    private MobileElement lbl_ChoosePassenger;

    @AndroidFindBy(id = "com.spirit.customerapp:id/simpleSwitch")
    private List<MobileElement> txt_PassengerName;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_free_spirit_no")
    private List<MobileElement> btn_FreeSpirit;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_ktn_no")
    private List<MobileElement> btn_KTN;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_redress_no")
    private List<MobileElement> btn_RedressNumber;

    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_next")
    private MobileElement btn_ContinueFromPassengerSelection;

    //Add New Document Page
    //Cancel Button
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'CANCEL')]")
    private MobileElement btn_PCancel;
    //Done Button
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'DONE')]")
    private MobileElement btn_DOBPopUpCancel;
    //Number Text Box
    @AndroidFindBy(id = "com.spirit.customerapp:id/_number")
    private MobileElement txtbx_Number;


    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getChoosePassengerLabel(){
        return lbl_ChoosePassenger;
    }

    public List<MobileElement> getPassengerNameText(){
        return txt_PassengerName;
    }

    public List<MobileElement> getFreeSpiritButton(){
        return btn_FreeSpirit;
    }

    public List<MobileElement> getKTNButton(){
        return btn_KTN;
    }

    public List<MobileElement> getRedressNumberButton(){
        return btn_RedressNumber;
    }

    public MobileElement getContinueFromPassengerSelectionButton(){
        return btn_ContinueFromPassengerSelection;
    }

    public MobileElement getPCancelButton(){
        return btn_PCancel;
    }

    public MobileElement getDOBPopUpCancelButton(){
        return btn_DOBPopUpCancel;
    }

    public MobileElement getNumberTextBox(){
        return txtbx_Number;
    }

}
