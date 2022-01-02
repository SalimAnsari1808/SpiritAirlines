package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SkySpeedPaymentPage {

    private WindowsDriver driver;

    public SkySpeedPaymentPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //Reservation window
    public final String xpath_TotalDueAmountText = "//*[@AutomationId = '_labelAmountDueValue']";
    @FindBy (xpath=xpath_TotalDueAmountText)
    private WebElement txt_TotalDueAmount;

    //Voucher Payment
    public final String xpath_VoucherNumberTextBox = "//*[@AutomationId = '_textBoxVoucherNumber']";
    @FindBy (xpath=xpath_VoucherNumberTextBox)
    private WebElement txtbx_VoucherNumber;

    public final String xpath_VoucherCodeTextBox = "//*[@AutomationId = '_textBoxVoucherCode']";
    @FindBy (xpath=xpath_VoucherCodeTextBox)
    private WebElement txtbx_VoucherCode;

    //Reservation Credit
    public final String xpath_ReservationCreditAccountTypeDropDown = "//*[@AutomationId = '_comboBoxAccountType']";
    @FindBy(xpath=xpath_ReservationCreditAccountTypeDropDown)
    private WebElement drpdwn_ReservationCreditAccountType;

    public final String xpath_ReservationCreditAccountNumberTextBox = "//*[@AutomationId = '_textBoxAccountNumber']";
    @FindBy(xpath=xpath_ReservationCreditAccountNumberTextBox)
    private WebElement txtbx_ReservationCreditAccountNumber;

    public final String xpath_AddReservationCreditButton = "//*[@AutomationId = '_btnAdd']";
    @FindBy(xpath=xpath_AddReservationCreditButton)
    private WebElement btn_AddReservationCredit;

    public final String xpath_NextButton = "//*[@AutomationId = '_buttonContinue']";
    @FindBy(xpath=xpath_NextButton)
    private WebElement btn_Next;

    public final String xpath_ReservationBooking = "//*[@AutomationId = '_btnReverse']";
    @FindBy(xpath=xpath_ReservationBooking)
    private WebElement btn_ReservationBooking;

    public final String xpath_SavePaymentBooking = "//*[@AutomationId = '_buttonSavePayment']";
    @FindBy(xpath=xpath_SavePaymentBooking)
    private WebElement btn_SavePayment;

    public final String xpath_SavePaymentBookingButton = "//*[@AutomationId = '_buttonUseContactAddress']";
    @FindBy(xpath=xpath_SavePaymentBookingButton)
    private WebElement btn_UseContactAddress;

    public final String xpath_PaymentMethodDropDown = "//*[@AutomationId = '_comboPaymentMethod']";
    @FindBy(xpath=xpath_PaymentMethodDropDown)
    private WebElement drpdwn_PaymentMethod;

    public final String xpath_ReservationAccountTypeDropDown = "//Edit[@Name = 'Payment Method:']";
    @FindBy(xpath=xpath_ReservationAccountTypeDropDown)
    private WebElement drpdwn_ReservationAccountType;

    public final String xpath_AccountHolderNameTextBox = "//Edit[@Name = 'Name']";
    @FindBy(xpath=xpath_AccountHolderNameTextBox)
    private WebElement txtbx_AccountHolderName;

    public final String xpath_AccountNumberTextBox = "//Edit[@Name = 'Account Number']";
    @FindBy(xpath=xpath_AccountNumberTextBox)
    private WebElement txtbx_AccountNumber;

    public final String xpath_BillingAddressTextBox = "//*[@AutomationId = '_textBoxAvs::Address1']";
    @FindBy(xpath=xpath_BillingAddressTextBox)
    private WebElement txtbx_BillingAddress;

    public final String xpath_PageCityTextBox = "//*[@AutomationId = '_textBoxCity']";
    @FindBy(xpath=xpath_PageCityTextBox)
    private WebElement txtbx_PageCity;

    public final String xpath_StateTextBox = "//Edit[@AutomationId = '_textBoxState']";
    @FindBy(xpath=xpath_StateTextBox)
    private WebElement txtbx_State;

    public final String xpath_ZipTextBox = "//Edit[@Name= 'Zip']";
    @FindBy(xpath=xpath_ZipTextBox)
    private WebElement txtbx_Zip;

    public final String xpath_CommentForReservationCreditTextBox = "//*[@AutomationId = '_textBoxComment']";
    @FindBy(xpath=xpath_CommentForReservationCreditTextBox)
    private WebElement txtbx_CommentForReservationCredit;

    public final String xpath_CountryTextBox = "//*[@AutomationId = '_textBoxCountry']";
    @FindBy(xpath=xpath_CountryTextBox)
    private WebElement txtbx_Country;

    public final String xpath_CVVTextBox = "//Edit[@Name = 'Security Code']";
    @FindBy(xpath=xpath_CVVTextBox)
    private WebElement txtbx_CVV;

    public final String xpath_EmailD1TextBox = "//*[@AutomationId = '_textBoxEmail']";
    @FindBy(xpath=xpath_EmailD1TextBox)
    private WebElement txtbx_EmailD1;

    public final String xpath_EmailD2TextBox = "//*[@AutomationId = '_textBoxBillTo::Email']";
    @FindBy(xpath=xpath_EmailD2TextBox)
    private WebElement txtbx_EmailD2;

    public final String xpath_ExpiryMonthTextBox = "//Pane[@Name = 'Expiration Date']//Edit[1]";
    @FindBy(xpath=xpath_ExpiryMonthTextBox)
    private WebElement txtbx_ExpiryMonth;

    public final String xpath_ExpiryYearTextBox = "//Pane[@Name = 'Expiration Date']//Edit[2]";
    @FindBy(xpath=xpath_ExpiryYearTextBox)
    private WebElement txtbx_ExpiryYear;

    public final String xpath_HomePhoneNumberTextBox = "//*[@AutomationId = '_textBoxHomePhone']";
    @FindBy(xpath=xpath_HomePhoneNumberTextBox)
    private WebElement txtbx_HomePhoneNumber;

    public WebElement getTotalDueAmountText(){ return txt_TotalDueAmount; }

    //Voucher Payment
    public WebElement getVoucherNumberTextBox(){
        return txtbx_VoucherNumber;
    }

    public WebElement getVoucherCodeTextBox(){
        return txtbx_VoucherCode;
    }

    //Reservation Credit
    public WebElement getReservationCreditAccountTypeDropDown(){
        return drpdwn_ReservationCreditAccountType;
    }

    public WebElement getReservationCreditAccountNumberTextBox(){
        return txtbx_ReservationCreditAccountNumber;
    }


    public WebElement getAddReservationCreditButton() {
        return btn_AddReservationCredit;
    }

    public WebElement getNextButton() {
        return btn_Next;
    }

    public WebElement getReservationBookingButton() {
        return btn_ReservationBooking;
    }

    public WebElement getSavePaymentButton() {
        return btn_SavePayment;
    }

    public WebElement getUseContactAddressButton() {
        return btn_UseContactAddress;
    }

    public WebElement getPaymentMethodDropDown() {
        return drpdwn_PaymentMethod;
    }

    public WebElement getReservationAccountTypeDropDown() {
        return drpdwn_ReservationAccountType;
    }

    public WebElement getAccountHolderNameTextBox() {
        return txtbx_AccountHolderName;
    }

    public WebElement getAccountNumberTextBox() {
        return txtbx_AccountNumber;
    }

    public WebElement getBillingAddressTextBox() {
        return txtbx_BillingAddress;
    }

    public WebElement getPageCityTextBox() {
        return txtbx_PageCity;
    }

    public WebElement getStateTextBox() {
        return txtbx_State;
    }

    public WebElement getZipTextBox() {
        return txtbx_Zip;
    }

    public WebElement getCommentForReservationCreditTextBox() {
        return txtbx_CommentForReservationCredit;
    }

    public WebElement getCountryTextBox() {
        return txtbx_Country;
    }

    public WebElement getCVVTextBox() {
        return txtbx_CVV;
    }

    public WebElement getEmailD1TextBox() {
        return txtbx_EmailD1;
    }

    public WebElement getEmailD2TextBox() {
        return txtbx_EmailD2;
    }

    public WebElement getExpiryMonthTextBox() {
        return txtbx_ExpiryMonth;
    }

    public WebElement getExpiryYearTextBox() {
        return txtbx_ExpiryYear;
    }

    public WebElement getHomePhoneNumberTextBox() {
        return txtbx_HomePhoneNumber;
    }

}
