package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class AddPaymentPage {

    private AppiumDriver driver;

    public AddPaymentPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //CANCEL button
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'CANCEL')]")
    private MobileElement btn_AddPaymentCancel;

    //Card Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_cardnumber")
    private MobileElement txtbx_CardNumber;

    //Scan Card
    @AndroidFindBy(id = "com.spirit.customerapp:id/imgCardScanner")
    private MobileElement btn_ScanCard;

    //Expiration Month And Year
    @AndroidFindBy(id = "com.spirit.customerapp:id/valid_thru")
    private MobileElement txtbx_ExpirationMonthAndYear;

    //CVV
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_cvv")
    private MobileElement txtbx_CVVNumber;

    //First Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_fname")
    private MobileElement txtbx_FirstName;

    //Expand Name
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id, 'chevron')][not(contains(@resource-id,'address'))]")
    private MobileElement img_CardArrow;

    //Last Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_lname")
    private MobileElement txtbx_LastName;

    //Expand Address
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id, 'chevron_address')]")
    private MobileElement img_AddressArrow;

    //Address
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_location_manually_entry")
    private MobileElement txtbx_Address;

    //City
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_city")
    private MobileElement btn_City;

    //State
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_state")
    private MobileElement btn_State;

    //Zip
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_zipcode")
    private MobileElement txtbx_Zip;

    //Country
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_country")
    private MobileElement btn_Country;

    //Input Address Manually
     @AndroidFindBy(id = "com.spirit.customerapp:id/txt_manually")
    private MobileElement btn_InputAddressManually ;

    //Select Default Payment Method
    @AndroidFindBy(id = "com.spirit.customerapp:id/switchDefault")
    private MobileElement btn_SelectDefaultPaymentMethod;

    //Add Payment Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/addCardToServer")
    private MobileElement btn_AddPayment;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getAddPaymentCancelButton(){
        return btn_AddPaymentCancel;
    }

    public MobileElement getCardNumberText(){
        return txtbx_CardNumber;
    }

    public MobileElement getScanCardButton(){
        return btn_ScanCard;
    }

    public MobileElement getExpirationMonthAndYearTextBox(){
        return txtbx_ExpirationMonthAndYear;
    }

    public MobileElement getCVVNumberTextBox(){
        return txtbx_CVVNumber;
    }

    public MobileElement getFirstNameTextBox(){
        return txtbx_FirstName;
    }

    public MobileElement getLastNameTextBox(){
        return txtbx_LastName;
    }

    public MobileElement getAddressArrowImage(){
        return img_AddressArrow;
    }

    public MobileElement getAddressTextBox(){
        return txtbx_Address;
    }

    public MobileElement getCityButton(){
        return btn_City;
    }

    public MobileElement getStateButton(){
        return btn_State;
    }

    public MobileElement getZipTextBox(){
        return txtbx_Zip;
    }

    public MobileElement getCountryButton(){
        return btn_Country;
    }

    public MobileElement getInputAddressManuallyButton(){
        return btn_InputAddressManually;
    }

    public MobileElement getSelectDefaultPaymentMethodButton(){
        return btn_SelectDefaultPaymentMethod;
    }

    public MobileElement getAddPaymentButton(){
        return btn_AddPayment;
    }

}
