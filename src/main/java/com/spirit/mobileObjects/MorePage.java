package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MorePage {

    private AppiumDriver driver;

    public MorePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Help Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/txtHelp")
    private MobileElement btn_Help;

    //Settings
    @AndroidFindBy(id = "com.spirit.customerapp:id/txtSetting")
    private MobileElement btn_Settings;

    //Call Us
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_call_us")
    private MobileElement btn_CallUs;

    //Most Common Questions
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_most_common")
    private MobileElement btn_MostCommonQuestions;

    //Submit a Request
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_submit_request")
    private MobileElement btn_SubmitARequest;

    //Legal
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_legal")
    private MobileElement btn_Legal;

    //Contact and social Media
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'CONTACT VIA SOCIAL MEDIA']")
    private MobileElement lbl_ContactAndSocialMedia;

    //Facebook
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_facebook")
    private MobileElement btn_Facebook;

    //Twitter
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_twitter")
    private MobileElement btn_Twitter;

    //Instagram
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_instagram")
    private MobileElement btn_Instagram;

    //North America
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_us")
    private MobileElement btn_CallUsNorthAmerica;

    //Caribbean
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_caribean")
    private MobileElement btn_CallUsCaribbean;

    //Central America
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_centralamerica")
    private MobileElement btn_CallUsCentralAmerica;

    //South America
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_southamerica")
    private MobileElement btn_CallUsSouthAmerica;

    //Spirit MasterCard Assistance
    @AndroidFindBy(id = "com.spirit.customerapp:id/txt_assistance")
    private MobileElement btn_CallUsSpiritMasterCardAssistance;

    //United States, Canada, Puerto Rico, US Virgin Islands
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_us']//preceding::android.widget.TextView[1]")
    private MobileElement txt_USCanadaPuertoRicoUSVI;

    //United States, Canada, Puerto Rico, US Virgin Islands PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_us")
    private MobileElement txt_USCanadaPuertoRicoUSVIPhoneNumber;

    //Assistance for the hearing
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_assist']//preceding::android.widget.TextView[1]")
    private MobileElement txt_AssistanceForTheHearing;

    //Assistance for the hearing PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_assist")
    private MobileElement txt_AssistanceForTheHearingPhoneNumber;

    //Spirit Vacations
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_spirit']//preceding::android.widget.TextView[1]")
    private MobileElement txt_SpiritVacations;

    //Spirit Vacations PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_spirit")
    private MobileElement txt_SpiritVacationsPhoneNumber;

    //Jamaica
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_jam']//following-sibling::android.widget.TextView")
    private MobileElement txt_Jamaica;

    //Jamaica PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_jam")
    private MobileElement txt_JamaicaPhoneNumber;

    //Haiti (French and Creole)
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_haiti']//following-sibling::android.widget.TextView")
    private MobileElement txt_HaitiFrenchAndCreole;

    //Haiti (French and Creole) PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_haiti")
    private MobileElement txt_HaitiFrenchAndCreolePhoneNumber;

    //Panama
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_pan']//following-sibling::android.widget.TextView")
    private MobileElement txt_Panama;

    //Panama PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_pan")
    private MobileElement txt_PanamaPhoneNumber;

    //Guatemala Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_guat")
    private MobileElement btn_Guatemala;

    //Guatemala Phone Numbers
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'com.spirit.customerapp:id/tv_dom_phone')]")
    private List<MobileElement> btn_GuatemalaPhoneNumbers;

    //Xela Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_xela")
    private MobileElement btn_Xela;

    //Xela Phone Numbers
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'com.spirit.customerapp:id/tv_dom_phone')]")
    private List<MobileElement> btn_XelaPhoneNumbers;

    //El Salvador
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_el']//preceding::android.widget.TextView[1]")
    private MobileElement txt_ElSalvador;

    //El Salvador PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_el")
    private MobileElement txt_ElSalvadorPhoneNumber;

    //Honduras
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_hond']//preceding::android.widget.TextView[1]")
    private MobileElement txt_Honduras;

    //Honduras Phone Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_hond")
    private MobileElement txt_HondurasPhoneNumber;

    //San Pedro Sula
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_san']//preceding::android.widget.TextView[1]")
    private MobileElement txt_SanPedroSula;

    //San Pedro Sula Phone Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_san")
    private MobileElement txt_SanPedroSulaPhoneNumber;

    //Tegucigalpa
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_teg']//preceding::android.widget.TextView[1]")
    private MobileElement txt_Tegucigalpa;

    //Tegucigalpa Phone Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_teg")
    private MobileElement txt_TegucigalpaPhoneNumber;

    //La Ceiba
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_la']//preceding::android.widget.TextView[1]")
    private MobileElement txt_LaCeiba;

    //Tegucigalpa Phone Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_la")
    private MobileElement txt_LaCeibaPhoneNumber;

    //Nicaragua
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_nic']//preceding::android.widget.TextView[1]")
    private MobileElement txt_Nicaragua;

    //Nicaragua Phone Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_nic")
    private MobileElement txt_NicaraguaPhoneNumber;

    //Costa Rica
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_cost']//preceding::android.widget.TextView[1]")
    private MobileElement txt_CostaRica;

    //Costa Rica Phone Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_cost")
    private MobileElement txt_CostaRicaPhoneNumber;

    //Peru
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_peru']//following-sibling::android.widget.TextView")
    private MobileElement txt_Peru;

    //Peru PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_peru")
    private MobileElement txt_PeruPhoneNumber;

    //Armenia
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_arm']//following-sibling::android.widget.TextView")
    private MobileElement txt_Armenia;

    //Armenia PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_arm")
    private MobileElement txt_ArmeniaPhoneNumber;

    //Bogota
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_bog']//following-sibling::android.widget.TextView")
    private MobileElement txt_Bogota;

    //Bogota PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_bog")
    private MobileElement txt_BogotaPhoneNumber;

    //Cartagena
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_cart']//following-sibling::android.widget.TextView")
    private MobileElement txt_Cartagena;

    //Cartagena PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_cart")
    private MobileElement txt_CartagenaPhoneNumber;

    //Medellin
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_med']//following-sibling::android.widget.TextView")
    private MobileElement txt_Medellinna;

    //Medellin PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_med")
    private MobileElement txt_MedellinPhoneNumber;

    //Bank of America Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'BANK OF AMERICA']")
    private MobileElement lbl_BankOfAmerica;

    //US
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_us']//following-sibling::android.widget.TextView")
    private MobileElement txt_SpiritMasterCardUS;

    //US PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_us")
    private MobileElement txt_SpiritMasterCardUSPhoneNumber;

    //Non-US
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_non_us']//preceding::android.widget.TextView[1]")
    private MobileElement txt_SpiritMasterCardNonUS;

    //Non-US PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_non_us")
    private MobileElement txt_SpiritMasterCardNonUSPhoneNumber;

    //Banco Promerica Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Banco Promerica']")
    private MobileElement lbl_BancoPromerica;

    //Dominicana Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dom")
    private MobileElement btn_SpiritMasterCardDominicana;

    //Dominicana Phone Numbers
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'com.spirit.customerapp:id/tv_dom_phone')]")
    private List<MobileElement> btn_SpiritMasterCardDominicanaPhoneNumbers;

    //Panama
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_pn']//preceding::android.widget.TextView[1]")
    private MobileElement txt_SpiritMasterCarPanama;

    //Panama PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_pn")
    private MobileElement txt_SpiritMasterCardPanamaPhoneNumber;

    //Costa Rica
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_cost']//preceding::android.widget.TextView[1]")
    private MobileElement txt_SpiritMasterCarCostaRica;

    //Costa Rica PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_cost")
    private MobileElement txt_SpiritMasterCardCostaRicaPhoneNumber;

    //Nicaragua
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_nic']//preceding::android.widget.TextView[1]")
    private MobileElement txt_SpiritMasterCarNicaragua;

    //Nicaragua PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_nic")
    private MobileElement txt_SpiritMasterCardNicaraguaPhoneNumber;

    //Honduras Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_hond")
    private MobileElement btn_SpiritMasterCardHonduras;

    //Honduras Phone Numbers
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'com.spirit.customerapp:id/tv_dom_phone')]")
    private List<MobileElement> btn_SpiritMasterCardHondurasPhoneNumbers;

    //El Salvador
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_el']//preceding::android.widget.TextView[1]")
    private MobileElement txt_SpiritMasterCarElSalvador;

    //El Salvador PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_el")
    private MobileElement txt_SpiritMasterCardElSalvadorPhoneNumber;

    //Guatemala
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_guat']//preceding::android.widget.TextView[1]")
    private MobileElement txt_SpiritMasterCarGuatemala;

    //El Salvador PHONE NUMBER
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_guat")
    private MobileElement txt_SpiritMasterCardGuatemalaPhoneNumber;

    //Flight Notifications
    @AndroidFindBy(id = "com.spirit.customerapp:id/switch_flight_noti")
    private MobileElement swtch_FlightNotifications;

    //Location Services
    @AndroidFindBy(id = "com.spirit.customerapp:id/switch_location")
    private MobileElement swtch_LocationServices;

    //App Version
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_version")
    private MobileElement txt_AppVersion;

    //Update button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_update_version")
    private MobileElement btn_UpdateAppVersion;

    //Company Name
    @AndroidFindBy(id = "//android.widget.TextView[@text= 'Spirit Airlines']")
    private MobileElement txt_CompanyName;

    //Call PopUp (When user clicks on any of the Phone Numbers listed on the app)
    //Call pop-up Header (phone number)
    @AndroidFindBy(id = "android:id/alertTitle")
    private MobileElement txt_CallPopUpHeader;

    //Call pop-up message
    @AndroidFindBy(id = "android:id/message")
    private MobileElement txt_CallPopUpMessage;

    //Call pop-up Cancel Button
    @AndroidFindBy(id = "android:id/button2")
    private MobileElement btn_CallPopUpCancel;

    //Call pop-up Call Button
    @AndroidFindBy(id = "android:id/button1")
    private MobileElement btn_CallPopUpCall;


    /******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/
    public MobileElement getHelpButton(){
        return btn_Help;
    }

    public MobileElement getSettingsButton(){
        return btn_Settings;
    }

    public MobileElement getCallUsButton(){
        return btn_CallUs;
    }

    public MobileElement getMostCommonQuestionsButton(){
        return btn_MostCommonQuestions;
    }

    public MobileElement getSubmitARequestButton(){
        return btn_SubmitARequest;
    }

    public MobileElement getLegalButton(){
        return btn_Legal;
    }

    public MobileElement getContactAndSocialMediaLabel(){
        return lbl_ContactAndSocialMedia;
    }

    public MobileElement getFacebookButton(){
        return btn_Facebook;
    }

    public MobileElement getTwitterButton(){
        return btn_Twitter;
    }

    public MobileElement getInstagramButton(){
        return btn_Instagram;
    }

    public MobileElement getCallUsNorthAmericaButton(){
        return btn_CallUsNorthAmerica;
    }

    public MobileElement getCallUsCaribbeanButton(){
        return btn_CallUsCaribbean;
    }

    public MobileElement getCallUsCentralAmericaButton(){
        return btn_CallUsCentralAmerica;
    }

    public MobileElement getCallUsSouthAmericaButton(){
        return btn_CallUsSouthAmerica;
    }

    public MobileElement getCallUsSpiritMasterCardAssistanceButton(){
        return btn_CallUsSpiritMasterCardAssistance;
    }

    public MobileElement getUSCanadaPuertoRicoUSVIText(){
        return txt_USCanadaPuertoRicoUSVI;
    }

    public MobileElement getUSCanadaPuertoRicoUSVIPhoneNumberText(){
        return txt_USCanadaPuertoRicoUSVIPhoneNumber;
    }

    public MobileElement getAssistanceForTheHearingText(){
        return txt_AssistanceForTheHearing;
    }

    public MobileElement getAssistanceForTheHearingPhoneNumberText(){
        return txt_AssistanceForTheHearingPhoneNumber;
    }

    public MobileElement getSpiritVacationsText(){
        return txt_SpiritVacations;
    }

    public MobileElement getSpiritVacationsPhoneNumberText(){
        return txt_SpiritVacationsPhoneNumber;
    }

    public MobileElement getJamaicaText(){
        return txt_Jamaica;
    }

    public MobileElement getJamaicaPhoneNumberText(){
        return txt_JamaicaPhoneNumber;
    }

    public MobileElement getHaitiFrenchAndCreoleText(){
        return txt_HaitiFrenchAndCreole;
    }

    public MobileElement getHaitiFrenchAndCreolePhoneNumberText(){
        return txt_HaitiFrenchAndCreolePhoneNumber;
    }

    public MobileElement getPanamaText(){
        return txt_Panama;
    }

    public MobileElement getPanamaPhoneNumberText(){
        return txt_PanamaPhoneNumber;
    }

    public MobileElement getGuatemalaButton(){
        return btn_Guatemala;
    }

    public List<MobileElement> getGuatemalaPhoneNumbersButton(){
        return btn_GuatemalaPhoneNumbers;
    }

    public MobileElement getXelaButton(){
        return btn_Xela;
    }

    public List<MobileElement> getXelaPhoneNumbersButton(){
        return btn_XelaPhoneNumbers;
    }

    public MobileElement getElSalvadorText(){
        return txt_ElSalvador;
    }

    public MobileElement getElSalvadorPhoneNumberText(){
        return txt_ElSalvadorPhoneNumber;
    }

    public MobileElement getHondurasText(){
        return txt_Honduras;
    }

    public MobileElement getHondurasPhoneNumberText(){
        return txt_HondurasPhoneNumber;
    }

    public MobileElement getSanPedroSulaText(){
        return txt_SanPedroSula;
    }

    public MobileElement getSanPedroSulaPhoneNumberText(){
        return txt_SanPedroSulaPhoneNumber;
    }

    public MobileElement getTegucigalpaText(){
        return txt_Tegucigalpa;
    }

    public MobileElement getTegucigalpaPhoneNumberText(){
        return txt_TegucigalpaPhoneNumber;
    }

    public MobileElement getLaCeibaText(){
        return txt_LaCeiba;
    }

    public MobileElement getLaCeibaPhoneNumberText(){
        return txt_LaCeibaPhoneNumber;
    }

    public MobileElement getNicaraguaText(){
        return txt_Nicaragua;
    }

    public MobileElement getNicaraguaPhoneNumberText(){
        return txt_NicaraguaPhoneNumber;
    }

    public MobileElement getCostaRicaText(){
        return txt_CostaRica;
    }

    public MobileElement getCostaRicaPhoneNumberText(){
        return txt_CostaRicaPhoneNumber;
    }

    public MobileElement getPeruText(){
        return txt_Peru;
    }

    public MobileElement getPeruPhoneNumberText(){
        return txt_PeruPhoneNumber;
    }

    public MobileElement getArmeniaText(){
        return txt_Armenia;
    }

    public MobileElement getArmeniaPhoneNumberText(){
        return txt_ArmeniaPhoneNumber;
    }

    public MobileElement getBogotaText(){
        return txt_Bogota;
    }

    public MobileElement getBogotaPhoneNumberText(){
        return txt_BogotaPhoneNumber;
    }

    public MobileElement getCartagenaText(){
        return txt_Cartagena;
    }

    public MobileElement getCartagenaPhoneNumberText(){
        return txt_CartagenaPhoneNumber;
    }

    public MobileElement getMedellinnaText(){
        return txt_Medellinna;
    }

    public MobileElement getMedellinPhoneNumberText(){
        return txt_MedellinPhoneNumber;
    }

    public MobileElement getBankOfAmericaLabel(){
        return lbl_BankOfAmerica;
    }

    public MobileElement getSpiritMasterCardUSText(){
        return txt_SpiritMasterCardUS;
    }

    public MobileElement getSpiritMasterCardUSPhoneNumberText(){
        return txt_SpiritMasterCardUSPhoneNumber;
    }

    public MobileElement getSpiritMasterCardNonUSText(){
        return txt_SpiritMasterCardNonUS;
    }

    public MobileElement getSpiritMasterCardNonUSPhoneNumberText(){
        return txt_SpiritMasterCardNonUSPhoneNumber;
    }

    public MobileElement getBancoPromericaLabel(){
        return lbl_BancoPromerica;
    }

    public MobileElement getSpiritMasterCardDominicanaButton(){
        return btn_SpiritMasterCardDominicana;
    }

    public List<MobileElement> getSpiritMasterCardDominicanaPhoneNumbersButton(){
        return btn_SpiritMasterCardDominicanaPhoneNumbers;
    }

    public MobileElement getSpiritMasterCarPanamaText(){
        return txt_SpiritMasterCarPanama;
    }

    public MobileElement getSpiritMasterCardPanamaPhoneNumberText(){
        return txt_SpiritMasterCardPanamaPhoneNumber;
    }

    public MobileElement getSpiritMasterCarCostaRicaText(){
        return txt_SpiritMasterCarCostaRica;
    }

    public MobileElement getSpiritMasterCardCostaRicaPhoneNumberText(){
        return txt_SpiritMasterCardCostaRicaPhoneNumber;
    }

    public MobileElement getSpiritMasterCarNicaraguaText(){
        return txt_SpiritMasterCarNicaragua;
    }

    public MobileElement getSpiritMasterCardNicaraguaPhoneNumberText(){
        return txt_SpiritMasterCardNicaraguaPhoneNumber;
    }

    public MobileElement getSpiritMasterCardHondurasButton(){
        return btn_SpiritMasterCardHonduras;
    }

    public List<MobileElement> getSpiritMasterCardHondurasPhoneNumbersButton(){
        return btn_SpiritMasterCardHondurasPhoneNumbers;
    }

    public MobileElement getSpiritMasterCarElSalvadorText(){
        return txt_SpiritMasterCarElSalvador;
    }

    public MobileElement getSpiritMasterCardElSalvadorPhoneNumberText(){
        return txt_SpiritMasterCardElSalvadorPhoneNumber;
    }

    public MobileElement getSpiritMasterCarGuatemalaText(){
        return txt_SpiritMasterCarGuatemala;
    }

    public MobileElement getSpiritMasterCardGuatemalaPhoneNumberText(){
        return txt_SpiritMasterCardGuatemalaPhoneNumber;
    }

    public MobileElement getFlightNotificationsSwitch(){
        return swtch_FlightNotifications;
    }

    public MobileElement getLocationServicesSwitch(){
        return swtch_LocationServices;
    }

    public MobileElement getAppVersionText(){
        return txt_AppVersion;
    }

    public MobileElement getUpdateAppVersionButton(){
        return btn_UpdateAppVersion;
    }

    public MobileElement getCompanyNameText(){
        return txt_CompanyName;
    }

    public MobileElement getCallPopUpHeaderText(){
        return txt_CallPopUpHeader;
    }

    public MobileElement getCallPopUpMessageText(){
        return txt_CallPopUpMessage;
    }

    public MobileElement getCallPopUpCancelButton(){
        return btn_CallPopUpCancel;
    }

    public MobileElement getCallPopUpCallButton(){
        return btn_CallPopUpCall;
    }

}
