package com.spirit.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



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


public class PassportPage  {

    public PassportPage(WebDriver driver) {
        //this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public final String xpath_PassengerInformationPageHeaderText= "//h1[contains(text(),'Passport Information') or contains(text(),'Información del pasaporte')]";
    @FindBy(xpath=xpath_PassengerInformationPageHeaderText)
    private WebElement txt_PassengerInformationPageHeader;

    public final String xpath_PassengerNumberText= "//h3[contains(text(),'Customer') or contains(text(),'Cliente')]";
    @FindBy(xpath=xpath_PassengerNumberText)
    private List<WebElement> txt_PassengerNumber;

    //*******************************************************************
    //*******************Customer Passport Information*******************
    //*******************************************************************
    public final String xpath_PassengerTitleDropDown= "//select[@name='title']";
    @FindBy(xpath=xpath_PassengerTitleDropDown)
    private List<WebElement> drpdwn_PassengerTitle;

    public final String xpath_PassengerMiddleNameTextBox= "//input[@name='middleName']";
    @FindBy(xpath=xpath_PassengerMiddleNameTextBox)
    private List<WebElement> txtbx_PassengerMiddleName;

    public final String xpath_PassengerSuffixDropDown= "//select[@name='suffix']";
    @FindBy(xpath=xpath_PassengerSuffixDropDown)
    private List<WebElement> drpdwn_PassengerSuffix;

    public final String xpath_PassengerCountryOfResidenceDropDown= "//select[@name='nationality']";
    @FindBy(xpath=xpath_PassengerCountryOfResidenceDropDown)
    private List<WebElement> drpdwn_PassengerCountryOfResidence;

    public final String xpath_PassengerPassportNumberTextBox= "//input[@name='passportNumber']";
    @FindBy(xpath=xpath_PassengerPassportNumberTextBox)
    private List<WebElement> txtbx_PassengerPassportNumber;

    public final String xpath_PassengerIssuingCountryDropDown= "//select[contains(@id,'passportIssuingCountry')]";
    @FindBy(xpath=xpath_PassengerIssuingCountryDropDown)
    private List<WebElement> drpdwn_PassengerIssuingCountry;

    public final String xpath_PassengerExpirationDateTextBox= "//input[contains(@id,'changeForBeforeDepartureDate')]";
    @FindBy(xpath=xpath_PassengerExpirationDateTextBox)
    private List<WebElement> txtbx_PassengerExpirationDate;

    public final String xpath_UhOkButton= "//button[contains(text(),'ok')]";
    @FindBy(xpath = xpath_UhOkButton)
    private WebElement btn_UhOk;
    //*******************************************************************
    //*************Temporary Stay Information**************************
    //*******************************************************************
    public final String id_TemporaryStayAddressTextBox= "tempStayAddress";
    @FindBy(id=id_TemporaryStayAddressTextBox)
    private WebElement txtbx_TemporaryStayAddress;

    public final String id_TemporaryStayCityTextBox= "tempStayCity";
    @FindBy(id=id_TemporaryStayCityTextBox)
    private WebElement txtbx_TemporaryStayCity;

    public final String id_TemporaryStayStateDropDown= "tempStayState";
    @FindBy(id=id_TemporaryStayStateDropDown)
    private WebElement drpdwn_TemporaryStayState;

    public final String id_TemporaryStayZipCodeTextBox= "tempStayZip";
    @FindBy(id=id_TemporaryStayZipCodeTextBox)
    private WebElement txtbx_TemporaryStayZipCode;

    public final String id_TemporaryStayCountyTextBox= "tempStayCountry";
    @FindBy(id=id_TemporaryStayCountyTextBox)
    private WebElement txtbx_TemporaryStayCounty;

    public final String xpath_TemporaryStayBlockPanel= "//div[@ngmodelgroup='temporaryStayInformation']";
    @FindBy(xpath = xpath_TemporaryStayBlockPanel)
    private WebElement pnl_TemporaryStayBlock;

    //*******************************************************************
    //*****************Passport Information Page Buttons*****************
    //*******************************************************************
    public final String xpath_PassengerBlockHeaderText= "//h2[contains(text(),'Passenger') or contains(text(),'Pasajero')]";
    @FindBy(xpath = xpath_PassengerBlockHeaderText)
    private WebElement txt_PassengerBlockHeader;

    public final String xpath_SaveChangesButton= "//button[contains(text(),'Save Changes') or contains(text(),'Guardar los Cambios')]";
    @FindBy(xpath=xpath_SaveChangesButton)
    private WebElement btn_SaveChanges;

    public final String xpath_CancelChangesButton= "//a[contains(text(),'Cancel Changes') or contains(text(),'Cancelar los Cambioss')]";
    @FindBy(xpath=xpath_CancelChangesButton)
    private List<WebElement> btn_CancelChanges;

    public final String xpath_PassportFirstNameText= "//input[@name='firstName']";
    @FindBy(xpath = xpath_PassportFirstNameText)
    private WebElement txt_PassportFirstName;

    public final String xpath_AddEditPassportInfoLink= "//button[contains(text(),'Add/Edit Passport Information') or contains(text(),'Añadir/Editar La Información del Pasaporte')]";
    @FindBy(xpath = xpath_AddEditPassportInfoLink)
    private WebElement lnk_AddEditPassportInfo;

    public final String xpath_PassportLastNameText= "//input[@name='lastName']";
    @FindBy(xpath = xpath_PassportLastNameText)
    private WebElement txt_PassportLastName;

    public final String xpath_PassportExpirationDateBoxText= "//input[@id='changeForBeforeDepartureDate0']";
    @FindBy(xpath = xpath_PassportExpirationDateBoxText)
    private WebElement txt_PassportExpirationDateBox;

    public final String xpath_PassportRequiredErrorText= "//div[contains(text(),'Passport Number is required') or contains(text(),'Número del pasaporte es requerido')]";
    @FindBy(xpath = xpath_PassportRequiredErrorText)
    private WebElement txt_PassportRequiredError;

    public final String xpath_PassportSixMonthText= "//h2[contains(text(),'Uh-oh!')]";
    @FindBy(xpath = xpath_PassportSixMonthText)
    private WebElement txt_PassportSixMonth;

    public final String xpath_UhOhOkButton= "//button[contains(text(),'ok')]";
    @FindBy(xpath = xpath_UhOhOkButton)
    private WebElement btn_UhOhOk;

    public final String xpath_ExpirationDateText= "//label[@for='changeForBeforeDepartureDate0']";
    @FindBy(xpath = xpath_ExpirationDateText)
    private WebElement txt_ExpirationDate;

    public final String xpath_IssuingCountryText= "//label[@for='passportIssuingCountry']";
    @FindBy(xpath = xpath_IssuingCountryText)
    private WebElement txt_IssuingCountry;

    public final String xpath_CountryOfResidenceText= "//label[@for='nationality']";
    @FindBy(xpath = xpath_CountryOfResidenceText)
    private WebElement txt_CountryOfResidence;

    public final String xpath_TitleText= "//label[@for='title']";
    @FindBy(xpath = xpath_TitleText)
    private WebElement txt_Title;

    public final String xpath_PassportNumberText= "//label[@for='passportNumber']";
    @FindBy(xpath = xpath_PassportNumberText)
    private WebElement txt_PassportNumber;

    public final String xpath_PassengerExpirationDateRedTextBox= "//input[@id='changeForBeforeDepartureDate0']";
    @FindBy(xpath=xpath_PassengerExpirationDateRedTextBox)
    private WebElement txtbx_PassengerExpirationDateRed;

    //*******************************************************************
    //*************Middle Name Not Found Pop-Up**************************
    //*******************************************************************
    public final String xpath_MiddleNameNotFoundPopUpHeaderText= "//h2[@class='modal-title']";
    @FindBy(xpath = xpath_MiddleNameNotFoundPopUpHeaderText)
    private WebElement txt_MiddleNameNotFoundPopUpHeader;

    public final String xpath_MiddleNameNotFoundPopUpVerbageText= "//div[@class='modal-body']//p";
    @FindBy(xpath = xpath_MiddleNameNotFoundPopUpVerbageText)
    private WebElement txt_MiddleNameNotFoundPopUpVerbage;

    public final String xpath_UpdateMiddleNameButton= "//app-travel-document-middle-name-modal//button[contains(text(),'Middle Name') or contains(text(),'Segundo Nombre')]";
    @FindBy(xpath=xpath_UpdateMiddleNameButton)
    private WebElement btn_UpdateMiddleName;

    public final String xpath_ContinueWithoutMiddleNameButton= "//app-travel-document-middle-name-modal//a";
    @FindBy(xpath=xpath_ContinueWithoutMiddleNameButton)
    private WebElement btn_ContinueWithoutMiddleName;

    //*****************************************************************************************************************
    //*************************************Start of Methods of Passport Page*****************************************
    //*****************************************************************************************************************


    public WebElement getPassengerInformationPageHeaderText() {
        return txt_PassengerInformationPageHeader;
    }

    public List<WebElement> getPassengerNumberText() {
        return txt_PassengerNumber;
    }

    public List<WebElement> getPassengerTitleDropDown() {
        return drpdwn_PassengerTitle;
    }

    public List<WebElement> getPassengerMiddleNameTextBox() {
        return txtbx_PassengerMiddleName;
    }

    public List<WebElement> getPassengerSuffixDropDown() {
        return drpdwn_PassengerSuffix;
    }

    public List<WebElement> getPassengerCountryOfResidenceDropDown() {
        return drpdwn_PassengerCountryOfResidence;
    }

    public List<WebElement> getPassengerPassportNumberTextBox() {
        return txtbx_PassengerPassportNumber;
    }

    public List<WebElement> getPassengerIssuingCountryDropDown() {
        return drpdwn_PassengerIssuingCountry;
    }

    public List<WebElement> getPassengerExpirationDateTextBox() {
        return txtbx_PassengerExpirationDate;
    }

    public WebElement getUhOkButton(){
        return btn_UhOk;
    }

    public WebElement getAddEditPassportInfoLink(){
        return lnk_AddEditPassportInfo;
    }

    public WebElement getPassportLastNameText(){
        return txt_PassportLastName;
    }

    public WebElement getPassportExpirationDateTextBox(){
        return txt_PassportExpirationDateBox;
    }

    public WebElement getPassportRequiredErrorText(){
        return txt_PassportRequiredError;
    }

    public WebElement getPassportSixMonthText(){
        return txt_PassportSixMonth;
    }

    public WebElement getUhOhOkButton(){
        return btn_UhOhOk;
    }

    public WebElement getExpirationDatetext(){
        return txt_ExpirationDate;
    }

    public WebElement getIssuingCountrytext(){
        return txt_IssuingCountry;
    }

    public WebElement getCountryOfResidencetext(){
        return txt_CountryOfResidence;
    }

    public WebElement getTitletext(){
        return txt_Title;
    }

    public WebElement getPassportNumbertext(){
        return txt_PassportNumber;
    }

    public WebElement getPassengerExpirationDateRedTextBox() {
        return txtbx_PassengerExpirationDateRed;
    }


    public WebElement getTemporaryStayAddressTextBox() {
        return txtbx_TemporaryStayAddress;
    }

    public WebElement getTemporaryStayCityTextBox() {
        return txtbx_TemporaryStayCity;
    }

    public WebElement getTemporaryStayStateDropDown() {
        return drpdwn_TemporaryStayState;
    }

    public WebElement getTemporaryStayZipCodeTextBox() {
        return txtbx_TemporaryStayZipCode;
    }

    public WebElement getTemporaryStayCountyTextBox() {
        return txtbx_TemporaryStayCounty;
    }

    public WebElement getTemporaryStayPanel(){
        return pnl_TemporaryStayBlock;
    }

    public WebElement getPassengerBlockHeader(){
        return txt_PassengerBlockHeader;
    }





    public WebElement getSaveChangesButton() {
        return btn_SaveChanges;
    }

    public List<WebElement> getCancelChangesButton() {
        return btn_CancelChanges;
    }

    public WebElement getPassportFirstNameText(){
        return txt_PassportFirstName;
    }



    //*******************************************************************
    //*************Middle Name Not Found Pop-Up Method*******************
    //*******************************************************************

    public WebElement getMiddleNameNotFoundPopUpHeaderText(){
        return txt_MiddleNameNotFoundPopUpHeader;
    }

    public WebElement getMiddleNameNotFoundPopUpVerbageText(){
        return txt_MiddleNameNotFoundPopUpVerbage;
    }

    public WebElement getUpdateMiddleNameButton() {
        return btn_UpdateMiddleName;
    }

    public WebElement getContinueWithoutMiddleNameButton() {
        return btn_ContinueWithoutMiddleName;
    }
}