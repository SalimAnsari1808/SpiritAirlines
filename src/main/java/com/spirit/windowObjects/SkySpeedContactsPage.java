package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SkySpeedContactsPage {

    private WindowsDriver driver;

    public SkySpeedContactsPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //SkySpeed Contact Information Window
    public final String xpath_AddContactButton = "//*[@AutomationId = '_buttonAdd']";
    @FindBy(xpath=xpath_AddContactButton)
    private WebElement btn_AddContact;

    public final String xpath_EditContactButton = "//*[@AutomationId = '_buttonEdit']";
    @FindBy(xpath=xpath_EditContactButton)
    private WebElement btn_EditContact;

    public final String xpath_DeleteContactButton = "//*[@AutomationId = '_buttonDelete']";
    @FindBy(xpath=xpath_DeleteContactButton)
    private WebElement btn_DeleteContact;

    public final String xpath_SaveContactInfoButton = "//*[@AutomationId = '_buttonSave']";
    @FindBy(xpath=xpath_SaveContactInfoButton)
    private WebElement btn_SaveContactInfo;

    public final String xpath_AddressLine1Button = "//*[@AutomationId = '_textBoxAddressLine1']";
    @FindBy(xpath=xpath_AddressLine1Button)
    private WebElement txtbx_AddressLine1;

    public final String xpath_CityTextBox = "//*[@AutomationId = '_textBoxAddressCity']";
    @FindBy(xpath=xpath_CityTextBox)
    private WebElement txtbx_City;

    public final String xpath_EMailAddressTextBox = "//*[@AutomationId = '_textBoxAddressEMail']";
    @FindBy(xpath=xpath_EMailAddressTextBox)
    private WebElement txtbx_EMailAddress;

    public final String xpath_FirstNameTextBox = "//*[@AutomationId = '_textBoxFirstName']";
    @FindBy(xpath=xpath_FirstNameTextBox)
    private WebElement txtbx_FirstName;

    public final String xpath_LastNameTextBox = "//*[@AutomationId = '_textBoxLastName']";
    @FindBy(xpath=xpath_LastNameTextBox)
    private WebElement txtbx_LastName;

//    public final String xpath_PhoneHomeTextBox = "//*[@AutomationId = '_textBoxPhoneNumber']"; //modified by anthony 1/16/20
    public final String xpath_PhoneHomeTextBox = "//*[@AutomationId = '_textBoxPhoneHome']";
    @FindBy(xpath=xpath_PhoneHomeTextBox)
    private WebElement txtbx_PhoneHome;

    public final String xpath_PostalCodeTextBox = "//*[@AutomationId = '_textBoxPostalCode']";
    @FindBy(xpath=xpath_PostalCodeTextBox)
    private WebElement txtbx_PostalCode;

    public final String xpath_StateTextBox = "//*[@AutomationId = '_textBoxAddressState']";
    @FindBy(xpath=xpath_StateTextBox)
    private WebElement txtbx_State;

    public final String xpath_ContactList = "//*[@AutomationId = '_contactListView']//*[@AutomationId = 'ListViewSubItem-']";
    @FindBy(xpath=xpath_ContactList)
    private List<WebElement> txt_ContactList;

    public final String xpath_CountryDropDown = "//*[@AutomationId = '_comboBoxAddressCountry']";
    @FindBy(xpath=xpath_CountryDropDown)
    private WebElement drpdwn_Country;

    public final String xpath_ItineraryDistribution = "//*[@AutomationId = '_comboBoxItineraryDistribution']";
    @FindBy(xpath=xpath_ItineraryDistribution)
    private WebElement drpdwn_ItineraryDistribution;

    public final String xpath_TitleTextBox = "//*[@AutomationId = '_comboBoxTitle']";
    @FindBy(xpath=xpath_TitleTextBox)
    private WebElement txtbx_Title;

    public final String xpath_BackButton = "//*[@AutomationId = '_ButtonBack']";
    @FindBy(xpath=xpath_BackButton)
    private WebElement btn_Back;

    public final String xpath_NextButton = "//*[@AutomationId = '_buttonContinue']";
    @FindBy(xpath=xpath_NextButton)
    private WebElement btn_Next;

    ////////////////////////////////////////////////////////////////


    public WebElement getBackButton() {
        return btn_Back;
    }

    public WebElement getAddContactButton() {
        return btn_AddContact;
    }

    public WebElement getDeleteContactButton() {
        return btn_DeleteContact;
    }

    public WebElement getEditContactButton() {
        return btn_EditContact;
    }

    public WebElement getNextButton() {
        return btn_Next;
    }

    public WebElement getSaveContactInfoButton() {
        return btn_SaveContactInfo;
    }

    public WebElement getAddressLine1TextBox() {
        return txtbx_AddressLine1;
    }

    public WebElement getCityTextBox() {
        return txtbx_City;
    }

    public WebElement getEMailAddressTextBox() {
        return txtbx_EMailAddress;
    }

    public WebElement getFirstNameTextBox() {
        return txtbx_FirstName;
    }

    public WebElement getLastNameTextBox() {
        return txtbx_LastName;
    }

    public WebElement getPhoneHomeTextBox() {
        return txtbx_PhoneHome;
    }

    public WebElement getPostalCodeTextBox() {
        return txtbx_PostalCode;
    }

    public WebElement getStateTextBox() {
        return txtbx_State;
    }

    public List<WebElement> getContactListText() {
        return txt_ContactList;
    }

    public WebElement getCountryTextBox() {
        return drpdwn_Country;
    }

    public WebElement getItineraryDistributionDropDown() {
        return drpdwn_ItineraryDistribution;
    }

    public WebElement getTitleTextBox() {
        return txtbx_Title;
    }


}
