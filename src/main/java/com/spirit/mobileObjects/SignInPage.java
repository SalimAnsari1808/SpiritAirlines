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

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;

public class SignInPage {

    private AppiumDriver driver;

    public SignInPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //*******************************************************************
    //***************************Sign In********************************
    //*******************************************************************
    //Sign In
    @AndroidFindBy(id = "com.spirit.customerapp:id/iv_back_toolbar")
    private MobileElement btn_CloseSignIn;

    //Spirit Logo
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc='content Description'])[2]")
    private MobileElement img_SpiritLogo;

    //Email Image
    @AndroidFindBy(id="com.spirit.customerapp:id/img_email")
    private MobileElement img_Email;

    //Email text Box
    @AndroidFindBy(id = "com.spirit.customerapp:id/edit_email")
    private MobileElement txtbx_Email;

    //Password Image
    @AndroidFindBy(id="com.spirit.customerapp:id/img_password_lock")
    private MobileElement img_Password;

    //Password text box
    @AndroidFindBy(id = "com.spirit.customerapp:id/edit_password")
    private MobileElement txtbx_Password;

    //View Password Text
    @AndroidFindBy(id = "com.spirit.customerapp:id/img_password")
    private MobileElement img_PasswordView;

    //Sign In Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_sign_in")
    private MobileElement btn_SignIn;

    //Sign In Image
    @AndroidFindBy(id="com.spirit.customerapp:id/iv_user_image")
    private MobileElement img_LoggedUser;

    //Forgot Password Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_forgot_password")
    private MobileElement btn_ForgotPassword;

    //Create Account
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_join_free")
    private MobileElement btn_CreateAccount;

    //*******************************************************************
    //*************************Forgot Password***************************
    //*******************************************************************
    //Forgot password Header
    @AndroidFindBy(xpath = "//android.widget.LinearLayout//android.widget.TextView)[1]")
    private MobileElement txt_ForgotPasswordHeader;

    //Close forgot password
    @AndroidFindBy(id = "com.spirit.customerapp:id/close")
    private MobileElement btn_closeForgotPassword;

    //Forgot Password Description Paragraph
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_email_text")
    private MobileElement txt_ForgotPasswordDescription;

    //Email Image
    @AndroidFindBy(id ="com.spirit.customerapp:id/img_email")
    private MobileElement img_ForgotPasswordEmail;

    //Forgot Password Email Text Box
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_email")
    private MobileElement txtbx_ForgotPasswordEmail;

    //Forgot Password Submit Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_send")
    private MobileElement btn_ForgotPasswordSubmit;

    //invalid email input error
    @AndroidFindBy(id = "com.spirit.customerapp:id/textinput_error")
    private MobileElement txt_ForgotPasswordInputError;

    //Create account Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_join_free")
    private MobileElement btn_CreateAccountButton;
    //email send conformation
    @AndroidFindBy(id="com.spirit.customerapp:id/tv_error_msg")
    private MobileElement txt_ForgotPasswordEmailConfirmation;

    //*******************************************************************
    //***********************Create an Account***************************
    //*******************************************************************
    //First Name Image
    @AndroidFindBy(id="com.spirit.customerapp:id/image_firstname")
    private MobileElement img_CreateAccountFirstName;
    //Back button
    @AndroidFindBy(id = "com.spirit.customerapp:id/iv_back_toolbar")
    private MobileElement btn_CreateAccountBack;

    //First Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_firstName")
    private MobileElement txtbx_CreateAccountFirstName;

    //Last Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_lastName")
    private MobileElement txtbx_CreateAccountLastName;

    //Email Image
    @AndroidFindBy(id="com.spirit.customerapp:id/image_email")
    private MobileElement img_CreateAccountEmail;

    //Email textbox
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_email")
    private MobileElement txtbx_CreateAccountEmail;

    //Password
    @AndroidFindBy(id = "com.spirit.customerapp:id/edit_password")
    private MobileElement txtbx_CreateAccountPassword;

    //View Password Text
    @AndroidFindBy(id = "com.spirit.customerapp:id/img_password")
    private MobileElement img_CreateAccountViewPassword;

    //Date Of Birth
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_dob")
    private MobileElement btn_CreateAccountDOB;

    //Country
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_country")
    private MobileElement btn_CreateAccountCountry;

    /////////////////////////////
    ////Country Select page//////
    /////////////////////////////

    //Country textbox
    @AndroidFindBy(id = "com.spirit.customerapp:id/etSearch")
    private MobileElement txtbx_CountrySearchTextBox;

    //recent country
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvRecentCountry")
    private MobileElement txt_CountrySearchRecentCountry;

    //All Countries Text header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvHeader")
    private MobileElement txt_CountrySearchAllCountriesHeader;

    //List of countries
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.spirit.customerapp:id/name']))")
    private List<MobileElement> txt_CountrySearchCountries;

    //Free Spirit Terms and conditions lnk
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvDeeplink")
    private MobileElement lnk_CreateAccountTermsAndConditions;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    //*******************************************************************
    //***************************Sign In********************************
    //*******************************************************************

    public MobileElement getCloseSignInButton(){
        return btn_CloseSignIn;
    }

    //Spirit Logo
    public MobileElement getSpiritLogoImage(){
        return img_SpiritLogo;
    }

    //Email Image
    public MobileElement getEmailImage(){
        return img_Email;
    }

    //Email text Box
    public MobileElement getEmailTextBox(){
        return txtbx_Email;
    }

    //Password Image
    public MobileElement getPasswordImage(){
        return img_Password;
    }

    //Password text box
    public MobileElement getPasswordTextBox(){
        return txtbx_Password;
    }

    //View Password Text
    public MobileElement getPasswordViewImage(){
        return img_PasswordView;
    }

    //Sign In Button
    public MobileElement getSignInButton(){
        return btn_SignIn;
    }

    public MobileElement getLoggedUserImage(){
        return img_LoggedUser;
    }

    //Forgot Password Button
    public MobileElement getForgotPasswordButton(){
        return btn_ForgotPassword;
    }

    //Create Account
    public MobileElement getCreateAccountButton(){
        return btn_CreateAccount;
    }

    //*******************************************************************
    //*************************Forgot Password***************************
    //*******************************************************************
    //Forgot password Header
    public MobileElement getForgotPasswordHeaderText(){
        return txt_ForgotPasswordHeader;
    }

    //Close forgot password
    public MobileElement getForgotPasswordCloseButton(){
        return btn_closeForgotPassword;
    }

    //Forgot Password Description Paragraph
    public MobileElement getForgotPasswordSubHeaderText(){
        return txt_ForgotPasswordDescription;
    }

    //Email Image
    public MobileElement getForgotPasswordEmailImage(){
        return img_ForgotPasswordEmail;
    }

    //Forgot Password Email Text Box
    public MobileElement getForgotPasswordEmailTextBox(){
        return txtbx_ForgotPasswordEmail;
    }

    //Forgot Password Submit Button
    public MobileElement getForgotPasswordSubmitButton(){
        return btn_ForgotPasswordSubmit;
    }

    public MobileElement getForgotPasswordInputErrorText(){
        return txt_ForgotPasswordInputError;
    }

    //email send conformation
    public MobileElement getForgotPasswordEmailConfirmationText(){
        return txt_ForgotPasswordEmailConfirmation;
    }

    //*******************************************************************
    //***********************Create an Account***************************
    //*******************************************************************
    //First Name Image
    public MobileElement getCreateAccountFirstNameImage(){
        return img_CreateAccountFirstName;
    }

    public MobileElement getCreateAccountBackButton(){
        return btn_CreateAccountBack;
    }

    //First Name
    public MobileElement getCreateAccountFirstNameTextBox(){
        return txtbx_CreateAccountFirstName;
    }

    //Last Name
    public MobileElement getCreateAccountLastNameTextBox(){
        return txtbx_CreateAccountLastName;
    }

    //Email Image
    public MobileElement getCreateAccountEmailImage(){
        return img_CreateAccountEmail;
    }

    //Email textbox
    public MobileElement getCreateAccountEmailTextBox(){
        return txtbx_CreateAccountEmail;
    }

    //Password
    public MobileElement getCreateAccountPasswordTextBox(){
        return txtbx_CreateAccountPassword;
    }

    //View Password Text
    public MobileElement getCreateAccountViewPasswordImage(){
        return img_CreateAccountViewPassword;
    }

    //Date Of Birth
    public MobileElement getCreateAccountDOBButton(){
        return btn_CreateAccountDOB;
    }

    //Country
    public MobileElement getCreateAccountCountryButton(){
        return btn_CreateAccountCountry;
    }

    /////////////////////////////
    ////Country Select page//////
    /////////////////////////////
    //Country textbox
    public MobileElement getCountrySearchTextBox(){
        return txtbx_CountrySearchTextBox;
    }

    //recent country
    public MobileElement getCountrySearchRecentCountryText(){
        return txt_CountrySearchRecentCountry;
    }

    //All Countries Text header
    public MobileElement getCountrySearchAllCountriesHeaderText(){
        return txt_CountrySearchAllCountriesHeader;
    }

    //List of countries
    public List<MobileElement> getCountrySearchCountriesText(){
        return txt_CountrySearchCountries;
    }

    public MobileElement getCreateAccountTermsAndConditionsLink(){
        return lnk_CreateAccountTermsAndConditions;
    }

}
