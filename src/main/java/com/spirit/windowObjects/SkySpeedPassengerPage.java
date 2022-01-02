package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SkySpeedPassengerPage {

    private WindowsDriver driver;

    public SkySpeedPassengerPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Passenger List
    public final String xpath_PassengerNameListText = "//*[@AutomationId = 'ListViewSubItem-0']";
    @FindBy(xpath=xpath_PassengerNameListText)
    private List<WebElement> txt_PassengerNameList;

    public final String xpath_PassengerTypeListText = "//*[@AutomationId = 'ListViewSubItem-1']";
    @FindBy(xpath=xpath_PassengerTypeListText)
    private List<WebElement> txt_PassengerTypeList;

    //Customer Program

    //Passenger details
    public final String xpath_PassengerTitleDropDown = "//*[@AutomationId = '_comboBoxTitle']";
    @FindBy(xpath=xpath_PassengerTitleDropDown)
    private WebElement drpdwn_PassengerTitle;

    public final String xpath_PassengerFirstNameTextBox = "//*[@AutomationId = '_textBoxFirstName']";
    @FindBy(xpath=xpath_PassengerFirstNameTextBox)
    private WebElement txtbx_PassengerFirstName;

    public final String xpath_PassengerLastNameTextBox = "//*[@AutomationId = '_textBoxLastName']";
    @FindBy(xpath=xpath_PassengerLastNameTextBox)
    private WebElement txtbx_PassengerLastName;

    public final String xpath_PassengerDOBTextBox = "//*[@AutomationId = '_dateSelectorDOB']";
    @FindBy(xpath=xpath_PassengerDOBTextBox)
    private WebElement txtbx_PassengerDOB;

    public final String xpath_PassengerPaxTypeTextBox = "//*[@AutomationId = '_cboType']";
    @FindBy(xpath=xpath_PassengerPaxTypeTextBox)
    private WebElement txtbx_PassengerPaxType;

    public final String xpath_PassengerGenderDropDown = "//*[@AutomationId = '_cboGender']";
    @FindBy(xpath=xpath_PassengerGenderDropDown)
    private WebElement drpdwn_PassengerGender;

    //Infant Details
    public final String xpath_InfantTitleDropDown = "//*[@AutomationId = '_comboBoxInfantTitle']";
    @FindBy(xpath=xpath_InfantTitleDropDown)
    private WebElement drpdwn_InfantTitle;

    public final String xpath_InfantFirstNameTextBox = "//*[@AutomationId = '_textBoxInfantFirstName']";
    @FindBy(xpath=xpath_InfantFirstNameTextBox)
    private WebElement txtbx_InfantFirstName;

    public final String xpath_InfantLastNameTextBox = "//*[@AutomationId = '_textBoxInfantLastName']";
    @FindBy(xpath=xpath_InfantLastNameTextBox)
    private WebElement txtbx_InfantLastName;

    public final String xpath_InfantDOBTextBox = "//*[@AutomationId = '_dateSelectorInfantDOB']";
    @FindBy(xpath=xpath_InfantDOBTextBox)
    private WebElement txtbx_InfantDOB;

    //Passenger Right Pane
    public final String xpath_SaveButton = "//*[@AutomationId = '_btnSave']";
    @FindBy(xpath=xpath_SaveButton)
    private WebElement btn_Save;




    public final String xpath_GroupBookingCheckBox = "//*[@AutomationId = '_checkBoxGroupBooking']";
    @FindBy(xpath=xpath_GroupBookingCheckBox)
    private WebElement chkbx_GroupBooking;

    public final String xpath_BackButton = "//*[@AutomationId = '_buttonBack']";
    @FindBy(xpath=xpath_BackButton)
    private WebElement btn_Back;

    public final String xpath_CustomerInformationNextButton = "//*[@AutomationId = '_buttonContinue']";
    @FindBy(xpath=xpath_CustomerInformationNextButton)
    private WebElement btn_CustomerInformationNext;



    public final String xpath_ssrDetails = "//*[@AutomationId = '_labelSSRValue']";
    @FindBy(xpath=xpath_ssrDetails)
    private WebElement CustomerInformationssrDetails;

    public final String xpath_ListOfPassengers = "//*[@AutomationId = '_lstPassengers']";
    @FindBy(xpath=xpath_ListOfPassengers)
    private WebElement CustomerInformationListOfPassengers;



    //Look Up Passengers
    public final String xpath_LookUpButton = "//*[@AutomationId = '_buttonLookUpCustomer']";
    @FindBy(xpath=xpath_LookUpButton)
    private WebElement btn_LookUp;

    public final String xpath_LookUpPopUpContactRadioButton = "//*[@AutomationId = '_groupBoxSearchCriteria']//*[@AutomationId = '_radioButtonContact']";
    @FindBy(xpath=xpath_LookUpPopUpContactRadioButton)
    private WebElement rdbtn_LookUpPopUpContact;

    public final String xpath_LookUpPopUpLastNameTextBox = "//*[@AutomationId = '_groupBoxSearchCriteria']//*[@AutomationId = '_textBoxLastName']";
    @FindBy(xpath=xpath_LookUpPopUpLastNameTextBox)
    private WebElement txtbx_LookUpPopUpLastName;

    public final String xpath_LookUpPopUpFirstNameTextBox = "//*[@AutomationId = '_groupBoxSearchCriteria']//*[@AutomationId = '_textBoxFirstName']";
    @FindBy(xpath=xpath_LookUpPopUpFirstNameTextBox)
    private WebElement txtbx_LookUpPopUpFirstName;

    public final String xpath_LookUpPopUpPhoneNumberTextBox = "//*[@AutomationId = '_groupBoxSearchCriteria']//*[@AutomationId = '_textBoxPhoneNumber']";
    @FindBy(xpath=xpath_LookUpPopUpPhoneNumberTextBox)
    private WebElement txtbx_LookUpPopUpPhoneNumber;

    public final String xpath_LookUpPopUpEmailAddressTextBox = "//*[@AutomationId = '_groupBoxSearchCriteria']//*[@AutomationId = '_textBoxEmailAddress']";
    @FindBy(xpath=xpath_LookUpPopUpEmailAddressTextBox)
    private WebElement txtbx_LookUpPopUpEmailAddress;

    public final String xpath_LookUpPopUpCustomerProgramRadioButton = "//*[@AutomationId = '_groupBoxSearchCriteria']//*[@AutomationId = '_radioButtonProgram']";
    @FindBy(xpath=xpath_LookUpPopUpCustomerProgramRadioButton)
    private WebElement rdbtn_LookUpPopUpCustomerProgram;

    public final String xpath_LookUpPopUpCustomerProgramDropDown = "//*[@AutomationId = '_groupBoxSearchCriteria']//*[@AutomationId = '_lookupCustomerPrograms']";
    @FindBy(xpath=xpath_LookUpPopUpCustomerProgramDropDown)
    private WebElement drpdn_LookUpPopUpCustomerProgram;

    public final String xpath_LookUpPopUpProgramNumberTextBox = "//*[@AutomationId = '_groupBoxSearchCriteria']//*[@AutomationId = '_textBoxProgramNumber']";
    @FindBy(xpath=xpath_LookUpPopUpProgramNumberTextBox)
    private WebElement txtbx_LookUpPopUpProgramNumber;

    public final String xpath_LookUpPopUpLookUpButton = "//*[@AutomationId = '_groupBoxSearchCriteria']//*[@AutomationId = '_buttonLookUp']";
    @FindBy(xpath=xpath_LookUpPopUpLookUpButton)
    private WebElement btn_LookUpPopUpLookUp;

    public final String xpath_LookUpPopUpResetButton = "//*[@AutomationId = '_groupBoxSearchCriteria']//*[@AutomationId = '_buttonReset']";
    @FindBy(xpath=xpath_LookUpPopUpResetButton)
    private WebElement btn_LookUpPopUpReset;

    public final String xpath_LookUpPopUpCustomerListText = "//*[@AutomationId = '_groupBoxCustomers']//*[@AutomationId = 'ListViewItem-']";
    @FindBy(xpath=xpath_LookUpPopUpCustomerListText)
    private List<WebElement> txt_LookUpPopUpCustomerList;

    public final String xpath_LookUpPopUpOkButton = "//*[@AutomationId = 'CustomerLookupDialog']//*[@AutomationId = '_buttonOK']";
    @FindBy(xpath=xpath_LookUpPopUpOkButton)
    private WebElement btn_LookUpPopUpOk;

    public final String xpath_LookUpPopUpCancelButton = "//*[@AutomationId = 'CustomerLookupDialog']//*[@AutomationId = '_buttonCancel']";
    @FindBy(xpath=xpath_LookUpPopUpCancelButton)
    private WebElement btn_LookUpPopUpCancel;

    //TravelDocuments
    public final String xpath_addTravelDocumentButton = "//*[@AutomationId = '_buttonAdd']";
    @FindBy(xpath=xpath_addTravelDocumentButton)
    private WebElement btn_addTravelDocument;

    public final String xpath_TravelDocumentsOkButton = "//*[@AutomationId = 'PassengerDocumentDialog']//*[@AutomationId = '_buttonOK']";
    @FindBy(xpath=xpath_TravelDocumentsOkButton)
    private WebElement btn_TravelDocumentsOk;

    public final String xpath_TravelDocumentTypeDropDown = "//*[@AutomationId = '_comboBoxDocumentType']";
    @FindBy(xpath=xpath_TravelDocumentTypeDropDown)
    private WebElement drpdwn_TravelDocumentTypeDropDown;

    public final String xpath_TravelDocumentsNumberTextBox = "//*[@AutomationId = '_textBoxNumber']";
    @FindBy(xpath=xpath_TravelDocumentsNumberTextBox)
    private WebElement txtbx_TravelDocumentsNumber;

    public final String xpath_TravelDocumentsExpDateTextBox = "//*[@AutomationId = '_dateSelectorExpirationDate']";
    @FindBy(xpath=xpath_TravelDocumentsExpDateTextBox)
    private WebElement txtbx_TravelDocumentsExpDate;

    public final String xpath_TravelDocumentsIssuedDateTextBox = "//*[@AutomationId = '_dateSelectorIssueDate']";
    @FindBy(xpath=xpath_TravelDocumentsIssuedDateTextBox)
    private WebElement txtbx_TravelDocumentsIssuedDate;

    public final String xpath_TravelDocumentsBirthCountryTextBox = "//*[contains(@Name,'Birth Country:')]";
    @FindBy(xpath=xpath_TravelDocumentsBirthCountryTextBox)
    private WebElement txtbx_TravelDocumentsBirthCountry;

    public final String xpath_TravelDocumentsIssueCountryTextBox = "//*[contains(@Name,'Issue Country:')]";
    @FindBy(xpath=xpath_TravelDocumentsIssueCountryTextBox)
    private WebElement txtbx_TravelDocumentsIssueCountry;

    public final String xpath_TravelDocumentsTypeTextBox = "//*[contains(@Name,'Doc Type:')]";
    @FindBy(xpath=xpath_TravelDocumentsTypeTextBox)
    private WebElement txtbx_TravelDocumentsType;

    public final String xpath_TravelDocumentsNationalityTextBox = "//*[@AutomationId = '_panelDocument']//*[contains(@Name,'Nationality')]";
    @FindBy(xpath=xpath_TravelDocumentsNationalityTextBox)
    private WebElement txtbx_TravelDocumentsNationality;

    //ManageSSRWindow
    public final String xpath_ManageSSRButton = "//*[@AutomationId = '_buttonManage']";
    @FindBy(xpath=xpath_ManageSSRButton)
    private WebElement btn_ManageSSR;

    public final String xpath_ManageSSRAddSSRButton = "//*[@AutomationId = '_buttonReserve']";
    @FindBy(xpath=xpath_ManageSSRAddSSRButton)
    private WebElement btn_ManageSSRAddSSR;

    public final String xpath_ManageSSRCloseButton = "//*[@AutomationId = '_buttonClose']";
    @FindBy(xpath=xpath_ManageSSRCloseButton)
    private WebElement btn_ManageSSRClose;

    public final String xpath_ManageSSRDeleteSSRButton = "//*[@AutomationId = '_buttonDelete']";
    @FindBy(xpath=xpath_ManageSSRDeleteSSRButton)
    private WebElement btn_ManageSSRDeleteSSR;

    public final String xpath_ManageSSRAvailableSSRList = "//*[@AutomationId = '_ssrAvailabilityCtrl']//*[@AutomationId = 'ListViewSubItem-0']";
    @FindBy(xpath=xpath_ManageSSRAvailableSSRList)
    private List<WebElement> ManageSSRAvailableSSRList;

    public final String xpath_ManageSSRReservedSSRForPax = "//*[@AutomationId = '_dataGridReservedSSR']";
    @FindBy(xpath=xpath_ManageSSRReservedSSRForPax)
    private WebElement ManageSSRReservedSSRForPax;


    //Passenger List
    public List<WebElement> getPassengerNameListText(){
       return txt_PassengerNameList;
    }

    public List<WebElement> getPassengerTypeListText(){
        return txt_PassengerTypeList;
    }

    /////////////////////////////////
    public WebElement getGroupBookingCheckBox() {
        return chkbx_GroupBooking;
    }

    public WebElement getAddTravelDocumentButton() {
        return btn_addTravelDocument;
    }

    public WebElement getBackButton() {
        return btn_Back;
    }

    public WebElement getManageSSRButton() {
        return btn_ManageSSR;
    }

    public WebElement getCustomerInformationNextButton() {
        return btn_CustomerInformationNext;
    }

    public WebElement getSaveButton() {
        return btn_Save;
    }

    public WebElement getPassengerPaxTypeTextBox() {
        return txtbx_PassengerPaxType;
    }

    public WebElement getPassengerFirstNameTextBox() {
        return txtbx_PassengerFirstName;
    }

    public WebElement getInfantFirstNameTextBox() {
        return txtbx_InfantFirstName;
    }

    public WebElement getInfantLastNameTextBox() {
        return txtbx_InfantLastName;
    }

    public WebElement getPassengerLastNameTextBox() {
        return txtbx_PassengerLastName;
    }

    public WebElement getPassengerDOBTextBox() {
        return txtbx_PassengerDOB;
    }

    public WebElement getInfantDOBTextBox() {
        return txtbx_InfantDOB;
    }

    public WebElement get_ssrDetails() {
        return CustomerInformationssrDetails;
    }

    public WebElement get_ListOfPassengers() {
        return CustomerInformationListOfPassengers;
    }



    //Look Up Passengers
    public WebElement getLookUpButton() {
        return btn_LookUp;
    }

    public WebElement getLookUpPopUpContactRadioButton() {
        return rdbtn_LookUpPopUpContact;
    }

    public WebElement getLookUpPopUpLastNameTextBox() {
        return txtbx_LookUpPopUpLastName;
    }

    public WebElement getLookUpPopUpFirstNameTextBox() {
        return txtbx_LookUpPopUpFirstName;
    }

    public WebElement getLookUpPopUpPhoneNumberTextBox() {
        return txtbx_LookUpPopUpPhoneNumber;
    }

    public WebElement getLookUpPopUpEmailAddressTextBox() {
        return txtbx_LookUpPopUpEmailAddress;
    }

    public WebElement getLookUpPopUpCustomerProgramRadioButton() {
        return rdbtn_LookUpPopUpCustomerProgram;
    }

    public WebElement getLookUpPopUpCustomerProgramDropDown() {
        return drpdn_LookUpPopUpCustomerProgram;
    }

    public WebElement getLookUpPopUpProgramNumberTextBox() {
        return txtbx_LookUpPopUpProgramNumber;
    }

    public WebElement getLookUpPopUpLookUpButton() {
        return btn_LookUpPopUpLookUp;
    }

    public WebElement getLookUpPopUpResetButton() {
        return btn_LookUpPopUpReset;
    }

    public List<WebElement> getLookUpPopUpCustomerListText() {
        return txt_LookUpPopUpCustomerList;
    }

    public WebElement getLookUpPopUpOkButton() {
        return btn_LookUpPopUpOk;
    }

    public WebElement getLookUpPopUpCancelButton() {
        return btn_LookUpPopUpCancel;
    }

    public WebElement getPassengerGenderTextBox() {
        return drpdwn_PassengerGender;
    }

    public WebElement getInfantTitleTextBox() {
        return drpdwn_InfantTitle;
    }

    public WebElement getPassengerTitleTextBox() {
        return drpdwn_PassengerTitle;
    }

    public WebElement getTravelDocumentsOkButton() {
        return btn_TravelDocumentsOk;
    }

    public WebElement getTravelDocumentTypeDropDownDropDown() {
        return drpdwn_TravelDocumentTypeDropDown;
    }

    public WebElement getTravelDocumentsNumberTextBox() {
        return txtbx_TravelDocumentsNumber;
    }

    public WebElement getTravelDocumentsExpDateTextBox() {
        return txtbx_TravelDocumentsExpDate;
    }

    public WebElement getTravelDocumentsIssuedDateTextBox() {
        return txtbx_TravelDocumentsIssuedDate;
    }

    public WebElement getTravelDocumentsBirthCountryTextBox() {
        return txtbx_TravelDocumentsBirthCountry;
    }

    public WebElement getTravelDocumentsIssueCountryTextBox() {
        return txtbx_TravelDocumentsIssueCountry;
    }

    public WebElement getTravelDocumentsTypeTextBox() {
        return txtbx_TravelDocumentsType;
    }

    public WebElement getTravelDocumentsNationalityTextBox() {
        return txtbx_TravelDocumentsNationality;
    }

    public WebElement getManageSSRAddSSRButton() {
        return btn_ManageSSRAddSSR;
    }

    public WebElement getManageSSRCloseButton() {
        return btn_ManageSSRClose;
    }

    public WebElement getManageSSRDeleteSSRButton() {
        return btn_ManageSSRDeleteSSR;
    }

    public List<WebElement> getManageSSRAvailableSSRList() {
        return ManageSSRAvailableSSRList;
    }

    public WebElement get_ManageSSRReservedSSRForPax() {
        return ManageSSRReservedSSRForPax;
    }

}