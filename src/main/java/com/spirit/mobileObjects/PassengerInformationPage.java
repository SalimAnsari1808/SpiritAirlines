package com.spirit.mobileObjects;

/*
ifr      -  iframe
btn      -  Button
chkbx    -  CheckBox
chklst   -  CheckBoxList
drpdwn   -  DropDownList
grdvew   -  GridView
hyrlnk   -  Hyperlink
img      -  Image
imgbtn   -  ImageButton
lbl      -  Label
lnkbtn   -  LinkButton
lnk      -  Link
lstbx    -  ListBox
lit      -  Literal
pnl      -  Panel
ph       -  PlaceHolder
rdbtn    -  RadioButton
rdbtnlst -  RadioButtonList
txtbx       Textbox
txt      -  Text
 */
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class PassengerInformationPage {

    private AppiumDriver driver;

    public PassengerInformationPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Sign-In with Free Spirit
    @AndroidFindBy(id = "com.spirit.customerapp:id/layoutSignin")
    private MobileElement btn_SignInWithFreeSpirit;

    //Passenger Information label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PASSENGER INFORMATION']")
    private MobileElement lbl_PassengerInformation;

    //Primary Passenger label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Primary Passenger']")
    private MobileElement lbl_PrimaryPassenger;

    //Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvName")
    private List<MobileElement> btn_Name;

    //Date of Birth
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvDOB")
    private List<MobileElement> txt_PassengerDOB;

    //Passenger Type (Adult, Child, Infant)
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvType")
    private List<MobileElement> txt_PassengerType;

    //////////////////////////////////
    ////Edit Passenger Information////
    //////////////////////////////////

    //Done
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_done")
    private MobileElement btn_EditPassengerDetailDone;

    //Title Drop Down
    @AndroidFindBy(id = "com.spirit.customerapp:id/spinner_title")
    private MobileElement drpdwn_EditPassengerTitle;

    //First Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_first_name")
    private MobileElement txtbx_EditPassengerFirstName;

    //Middle Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_middle_name")
    private MobileElement txtbx_EditPassengerMiddleName;

    //Last Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_last_name")
    private MobileElement txtbx_EditPassengerLastName;

    //Suffix
    @AndroidFindBy(id = "com.spirit.customerapp:id/spinner_suffix")
    private MobileElement drpdwn_EditPassengerSuffix;

    //Date of birth
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dob")
    private MobileElement btn_EditPassengerDOB;

    //Add FreeSpirit Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_free_spirit_number")
    private MobileElement txtbx_EditPassengerAddFreeSpiritNumber;

    //KTN
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_known_traveller_number")
    private MobileElement txtbx_EditPassengerKTN;

    //Redress Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_redress_number")
    private MobileElement txtbx_EditPassengerRedressNumber;

    //More Options
    @AndroidFindBy(id = "com.spirit.customerapp:id/layoutMoreTap")
    private MobileElement btn_EditPassengerMoreOptions;

    //Request Special Services
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_special_assistance")
    private MobileElement txtbx_EditPassengerRequestSpecialServices;

    //////////////////////////////////
    //////Additional Services/////////
    //////////////////////////////////
    //Page Header
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = 'com.spirit.customerapp:id/toolbar']//android.widget.TextView[contains(@text, 'Additional Services')]")
    private MobileElement txt__AdditionalServicesHeader;

    //Hearing disability
    @AndroidFindBy(id = "com.spirit.customerapp:id/hearing_disability_switch")
    private MobileElement swtch_Hearingdisability;

    //Vision Disability
    @AndroidFindBy(id = "com.spirit.customerapp:id/vision_disability_switch")
    private MobileElement swtch_VisionDisability;

    //Service Animal
    @AndroidFindBy(id = "com.spirit.customerapp:id/service_animal_switch")
    private MobileElement swtch_ServiceAnimal;

    //Emotional Support Animal
    @AndroidFindBy(id = "com.spirit.customerapp:id/emotional_support_switch")
    private MobileElement swtch_EmotionalSupportAnimal ;

    //Portable oxygen container (POC)
    @AndroidFindBy(id = "com.spirit.customerapp:id/poc_switch")
    private MobileElement swtch_PortableOxygenContainer ;

    //Other
    @AndroidFindBy(id = "com.spirit.customerapp:id/other_poc_switch")
    private MobileElement swtch_OtherDisability;

    //Wheel Chair to and from gate
    @AndroidFindBy(id = "com.spirit.customerapp:id/gate_help_switch")
    private MobileElement swtch_WheelChairToAndFromGate;

    //Wheel Chair to and from seat
    @AndroidFindBy(id = "com.spirit.customerapp:id/seat_help_switch")
    private MobileElement swtch_WheelChairToAndFromSeat;

    //Personal WheelChair
    @AndroidFindBy(id = "com.spirit.customerapp:id/own_wheelchair")
    private MobileElement drpdwn_PersonalWheelChair;

    //Completely immobile
    @AndroidFindBy(id = "com.spirit.customerapp:id/immobile_switch")
    private MobileElement swtch_CompletelyImmobile ;

    //Voluntary Provision of Emergency Services Program
    @AndroidFindBy(id = "com.spirit.customerapp:id/voluntary_service_switch")
    private MobileElement swtch_VoluntaryProvisionOfEmergencyServicesProgram ;

    /////////////////////////////
    ////DOB Calendar Pop Up//////
    /////////////////////////////
    //year header
    @AndroidFindBy(id = "android:id/date_picker_header_year")
    private MobileElement btn_DOBPopUpYearHeader;

    @AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='android:id/date_picker_year_picker']//android.widget.TextView")
    private List<MobileElement> btn_DOBPopUpYearList;

    //Date Header
    @AndroidFindBy(id = "android:id/date_picker_header_date")
    private MobileElement txt_DOBPopUpDateHeader;

    //Previous Month
    @AndroidFindBy(id = "android:id/prev")
    private MobileElement btn_DOBPopUpPreviousMonth;

    //Days of Month
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'android:id/month_view']//android.view.View[@enabled='true']")
    private List<MobileElement> btn_DOBPopUpDaysOfMonth;

    //CANCEL button
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'CANCEL')]")
    private MobileElement btn_DOBPopUpCancel;

    //OK button
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'OK')]")
    private MobileElement btn_DOBPopUpOK;

    //Passenger Details Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Details must match your government ID.']")
    private MobileElement lbl_PassengerContactInformation;

    //Country
    @AndroidFindBy(id = "com.spirit.customerapp:id/country_passenger_info")
    private MobileElement btn_Country;

    //State
    @AndroidFindBy(id = "com.spirit.customerapp:id/state_passenger_info_et")
    private MobileElement btn_State;

    //City
    @AndroidFindBy(id = "com.spirit.customerapp:id/city_passenger_info")
    private MobileElement txtbx_City;

    //Address
    @AndroidFindBy(id = "com.spirit.customerapp:id/address_passenger_info")
    private MobileElement txtbx_Address;

    //Zip
    @AndroidFindBy(id = "com.spirit.customerapp:id/zip_passenger_info")
    private MobileElement txtbx_Zip;

    //Email Address
    @AndroidFindBy(id = "com.spirit.customerapp:id/email_passenger_info")
    private MobileElement txtbx_EmailAddress;

    //Phone Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/phone_passenger_info")
    private MobileElement txtbx_PhoneNumber;

    //Subscribe to emails
    @AndroidFindBy(id = "com.spirit.customerapp:id/subscribe_emails")
    private MobileElement swtch_SubscribeToEmails;

    //Sign Up to FreeSpirit
    @AndroidFindBy(id = "com.spirit.customerapp:id/free_spirit_signin_switch")
    private MobileElement swtch_SignUpToFreeSpirit;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_password")
    private MobileElement txtbx_SignUpPassword;

    @AndroidFindBy(id = "com.spirit.customerapp:id/infoIcon_password")
    private MobileElement icn_SignUpPasswordInfo;

    @AndroidFindBy(id = "com.spirit.customerapp:id/img_password")
    private MobileElement btn_SignUpViewPassword;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_confirm_password")
    private MobileElement txtbx_SignUpCofirmPassword;

    @AndroidFindBy(id = "com.spirit.customerapp:id/img_confirm_password")
    private MobileElement btn_SignUpViewConfirmPassword;

    @AndroidFindBy(id = "com.spirit.customerapp:id/infoIcon_confirm_password")
    private MobileElement icn_SignUpConfirmPasswordInfo;

    @AndroidFindBy(id = "com.spirit.customerapp:id/terms_condition")
    private MobileElement btn_SignUpTermsAndCondition;

    /////////////////////////////
    ////Country Select page//////
    /////////////////////////////

    //Country textbox
    @AndroidFindBy(id = "com.spirit.customerapp:id/etSearch")
    private MobileElement txtbx_CountrySearch;

    //recent country
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvRecentCountry")
    private MobileElement txt_CountrySearchRecentCountry;

    //All Countries Text header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvHeader")
    private MobileElement txt_CountrySearchAllCountriesHeader;

    //List of countries
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.spirit.customerapp:id/name']))")
    private List<MobileElement> txt_CountrySearchCountries;

    /////////////////////////////
    /////State Select page///////
    /////////////////////////////
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_toolbar")
    private MobileElement txt_StateSearchPageHeader;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_toolbar")
    private MobileElement btn_StateSearchPageClose;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_search")
    private MobileElement txtbx_StateSearchPage;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_airport_name")
    private List<MobileElement>  btn_StateSearchPageStateList;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getSignInWithFreeSpiritButton(){
        return btn_SignInWithFreeSpirit;
    }

    public MobileElement getPassengerInformationLabel(){
        return lbl_PassengerInformation;
    }

    public MobileElement getPrimaryPassengerLabel(){
        return lbl_PrimaryPassenger;
    }

    public List<MobileElement> getNameButton(){
        return btn_Name;
    }

    public List<MobileElement> getPassengerDOBTExt(){
        return txt_PassengerDOB;
    }

    public List<MobileElement> getPassengerTypeText(){
        return txt_PassengerType;
    }

    public MobileElement getEditPassengerDetailDoneButton(){
        return btn_EditPassengerDetailDone;
    }

    public MobileElement getEditPassengerTitleDropDown(){
        return drpdwn_EditPassengerTitle;
    }

    public MobileElement getEditPassengerFirstNameTextBox(){
        return txtbx_EditPassengerFirstName;
    }

    public MobileElement getEditPassengerMiddleNameTextBox(){
        return txtbx_EditPassengerMiddleName;
    }

    public MobileElement getEditPassengerLastNameTextBox(){
        return txtbx_EditPassengerLastName;
    }

    public MobileElement getEditPassengerSuffixDropDown(){
        return drpdwn_EditPassengerSuffix;
    }

    public MobileElement getEditPassengerDOBButton(){
        return btn_EditPassengerDOB;
    }

    public MobileElement getEditPassengerAddFreeSpiritNumberTextBox(){
        return txtbx_EditPassengerAddFreeSpiritNumber;
    }

    public MobileElement getEditPassengerKTNTextBox(){
        return txtbx_EditPassengerKTN;
    }

    public MobileElement getEditPassengerRedressNumberButton(){
        return txtbx_EditPassengerRedressNumber;
    }

    public MobileElement getEditPassengerMoreOptionsButton(){
        return btn_EditPassengerMoreOptions;
    }

    public MobileElement getEditPassengerRequestSpecialServicesTextBox(){
        return txtbx_EditPassengerRequestSpecialServices;
    }

    public MobileElement getAdditionalServicesHeaderText(){
        return txt__AdditionalServicesHeader;
    }

    public MobileElement getHearingdisabilitySwitch(){
        return swtch_Hearingdisability;
    }

    public MobileElement getVisionDisabilitySwitch(){
        return swtch_VisionDisability;
    }

    public MobileElement getServiceAnimalSwitch(){
        return swtch_ServiceAnimal;
    }

    public MobileElement getEmotionalSupportAnimalSwitch(){
        return swtch_EmotionalSupportAnimal;
    }

    public MobileElement getPortableOxygenContainerSwitch(){
        return swtch_PortableOxygenContainer;
    }

    public MobileElement getOtherDisabilitySwitch(){
        return swtch_OtherDisability;
    }

    public MobileElement getWheelChairToAndFromGateSwitch(){
        return swtch_WheelChairToAndFromGate;
    }

    public MobileElement getWheelChairToAndFromSeatSwitch(){
        return swtch_WheelChairToAndFromSeat;
    }

    public MobileElement getPersonalWheelChairDropDown(){
        return drpdwn_PersonalWheelChair;
    }

    public MobileElement getCompletelyImmobileSwitch(){
        return swtch_CompletelyImmobile;
    }

    public MobileElement getVoluntaryProvisionOfEmergencyServicesProgramSwitch(){
        return swtch_VoluntaryProvisionOfEmergencyServicesProgram;
    }

    public MobileElement getDOBPopUpYearHeaderButton(){
        return btn_DOBPopUpYearHeader;
    }

    public List<MobileElement> getDOBPopUpYearListButton(){
        return btn_DOBPopUpYearList;
    }

    public MobileElement getDOBPopUpDateHeaderText(){
        return txt_DOBPopUpDateHeader;
    }

    public MobileElement getDOBPopUpPreviousMonthButton(){
        return btn_DOBPopUpPreviousMonth;
    }

    public List<MobileElement> getDOBPopUpDaysOfMonthButton(){
        return btn_DOBPopUpDaysOfMonth;
    }

    public MobileElement getDOBPopUpCancelButton(){
        return btn_DOBPopUpCancel;
    }

    public MobileElement getDOBPopUpOKButton(){
        return btn_DOBPopUpOK;
    }

    public MobileElement getPassengerContactInformationLabel(){
        return lbl_PassengerContactInformation;
    }

    public MobileElement getCountryButton(){
        return btn_Country;
    }

    public MobileElement getStateButton(){
        return btn_State;
    }

    public MobileElement getCityTextBox(){
        return txtbx_City;
    }

    public MobileElement getAddressTextBox(){
        return txtbx_Address;
    }

    public MobileElement getZipTextBox(){
        return txtbx_Zip;
    }

    public MobileElement getEmailAddressTextBox(){
        return txtbx_EmailAddress;
    }

    public MobileElement getPhoneNumberTextBox(){
        return txtbx_PhoneNumber;
    }

    public MobileElement getSubscribeToEmailsSwitch(){
        return swtch_SubscribeToEmails;
    }

    public MobileElement getSignUpToFreeSpiritSwitch(){
        return swtch_SignUpToFreeSpirit;
    }

    public MobileElement getSignUpPasswordTextBox(){
        return txtbx_SignUpPassword;
    }

    public MobileElement getSignUpPasswordInfoIcon(){
        return icn_SignUpPasswordInfo;
    }

    public MobileElement getSignUpViewPasswordButton(){
        return btn_SignUpViewPassword;
    }

    public MobileElement getSignUpCofirmPasswordTextBox(){
        return txtbx_SignUpCofirmPassword;
    }

    public MobileElement getSignUpViewConfirmPasswordButton(){
        return btn_SignUpViewConfirmPassword;
    }

    public MobileElement getSignUpConfirmPasswordInfoIcon(){
        return icn_SignUpConfirmPasswordInfo;
    }

    public MobileElement getSignUpTermsAndConditionButton(){
        return btn_SignUpTermsAndCondition;
    }

    public MobileElement getCountrySearchTextBox(){
        return txtbx_CountrySearch;
    }

    public MobileElement getCountrySearchRecentCountryText(){
        return txt_CountrySearchRecentCountry;
    }

    public MobileElement getCountrySearchAllCountriesHeaderText(){
        return txt_CountrySearchAllCountriesHeader;
    }

    public List<MobileElement> getCountrySearchCountriesText(){
        return txt_CountrySearchCountries;
    }

    public MobileElement getStateSearchPageHeaderText(){
        return txt_StateSearchPageHeader;
    }

    public MobileElement getStateSearchPageCloseButton(){
        return btn_StateSearchPageClose;
    }

    public MobileElement getStateSearchPageTextBox(){
        return txtbx_StateSearchPage;
    }

    public List<MobileElement> getStateSearchPageStateListButton(){
        return btn_StateSearchPageStateList;
    }

}