package com.spirit.pageObjects;

import java.util.ArrayList;
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


public class PassengerInfoPage {
	//private WebDriver driver;

	public PassengerInfoPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public final String xpath_PassengerInformationText = "//h1[@class='headline1']";
	@FindBy(xpath = xpath_PassengerInformationText)
	private WebElement txt_PassengerInformation;

	//*******************************************************************
	//*******************Inline Log-in Passenger Page********************
	//*******************************************************************
	public final String id_UserNameInLineLogInTextBox = "pax-inline-username";
	@FindBy(id=id_UserNameInLineLogInTextBox)
	private WebElement txtbx_UserNameInLineLogIn;

	public final String id_PasswordInLineLogInTextBox = "pax-inline-password";
	@FindBy(id=id_PasswordInLineLogInTextBox)
	private WebElement txtbx_PasswordInLineLogIn;

	public final String xpath_LogInButtonInLineLogInButton = "//button[contains(text(),'Log In') or contains(text(),'Inicia Sesión')]";
	@FindBy(xpath=xpath_LogInButtonInLineLogInButton)
	private WebElement btn_LogInButtonInLineLogIn;

	public final String xpath_ResetPasswordInLineLogInLink = "//button[contains(text(),'Reset Password') or contains(text(),'Restablecer la Contraseña')]";
	@FindBy(xpath=xpath_ResetPasswordInLineLogInLink)
	private WebElement lnk_ResetPasswordInLineLogIn;

	//*******************************************************************
	//*******************Passenger(s) Passenger Page*********************
	//*******************************************************************
	public final String xpath_PassengerHeaderText = "//section[@data-qa='passenger-info']//h3/span";
	@FindBy(xpath=xpath_PassengerHeaderText)
	private List<WebElement> txt_PassengerHeader;

	public final String xpath_AdultTitleListDropDown = "//section[@data-qa='passenger-info']//select[contains(@id,'title')]";
	@FindBy(xpath=xpath_AdultTitleListDropDown)
	private List<WebElement> drpdwn_AdultTitleList;

	public final String xpath_AdultFirstNameListTextBox = "//section[@data-qa='passenger-info']//input[contains(@id,'firstName')]";
	@FindBy(xpath=xpath_AdultFirstNameListTextBox)
	private List<WebElement> txtbx_AdultFirstNameList;

	public final String xpath_AdultMiddleNameListTextBox = "//section[@data-qa='passenger-info']//input[contains(@id,'middleName')]";
	@FindBy(xpath=xpath_AdultMiddleNameListTextBox)
	private List<WebElement> txtbx_AdultMiddleNameList;

	public final String xpath_AdultLastNameListTextBox = "//section[@data-qa='passenger-info']//input[contains(@id,'lastName')]";
	@FindBy(xpath=xpath_AdultLastNameListTextBox)
	private List<WebElement> txtbx_AdultLastNameList;

	public final String xpath_AdultSuffixListDropDown = "//section[@data-qa='passenger-info']//select[contains(@id,'suffix')]";
	@FindBy(xpath=xpath_AdultSuffixListDropDown)
	private List<WebElement> drpdwn_AdultSuffixList;

	public final String xpath_AdultDOBListTextBox = "//section[@data-qa='passenger-info']//input[contains(@id,'dateOfBirth')]";
	@FindBy(xpath=xpath_AdultDOBListTextBox)
	private List<WebElement> txtbx_AdultDOBList;

	public final String xpath_AdultKTNListTextBox = "//section[@data-qa='passenger-info']//input[contains(@id,'knownTravelerNumber')]";
	@FindBy(xpath=xpath_AdultKTNListTextBox)
	private List<WebElement> txtbx_AdultKTNList;

	public final String xpath_AdultFSNumberListTextBox = "//section[@data-qa='passenger-info']//input[contains(@id,'number')]";
	@FindBy(xpath=xpath_AdultFSNumberListTextBox)
	private List<WebElement> txtbx_AdultFSNumberList;

	public final String xpath_AdditionalServicesListLinkButton = "//section[@data-qa='passenger-info']//a[contains(text(),'Additional Services') or contains(text(),'Servicios Adicionales')]";
	@FindBy(xpath=xpath_AdditionalServicesListLinkButton)
	private List<WebElement> lnkbtn_AdditionalServicesList;

	public final String xpath_RedressNumberListLinkButton = "//section[@data-qa='passenger-info']//a[contains(text(),'Redress Number') or contains(text(),'Número de Correción')]"; //gets double the expected webelements (both mobile and desktop view xpaths
	@FindBy(xpath=xpath_RedressNumberListLinkButton)
	private List<WebElement> lnkbtn_RedressNumberList;

	public final String xpath_ActiveMilitaryPersonnelListCheckbox = "//section[@data-qa='passenger-info']//label[contains(@for,'isMilitary')]";
	@FindBy(xpath=xpath_ActiveMilitaryPersonnelListCheckbox) //add following locator for not mobile  [not(contains(@for,'Mob'))]
	private List<WebElement> chkbx_ActiveMilitaryPersonnelList;

	//*******************************************************************
	//**********************Infant On Lap*******************************
	//*******************************************************************
	public final String xpath_InfantTitleListDropDown = "//select[contains(@id,'infantTitle')]";
	@FindBy(xpath=xpath_InfantTitleListDropDown)
	private List<WebElement> drpdwn_InfantTitleList;

	public final String xpath_InfantFirstNameListTextBox = "//input[contains(@id,'infantFirstName')]";
	@FindBy(xpath=xpath_InfantFirstNameListTextBox)
	private List<WebElement> txtbx_InfantFirstNameList;

	public final String xpath_InfantMiddleNameListTextBox = "//input[contains(@id,'infantMiddleName')]";
	@FindBy(xpath=xpath_InfantMiddleNameListTextBox)
	private List<WebElement> txtbx_InfantMiddleNameList;

	public final String xpath_InfantLastNameListTextBox = "//input[contains(@id,'infantLastName')]";
	@FindBy(xpath=xpath_InfantLastNameListTextBox)
	private List<WebElement> txtbx_InfantLastNameList;

	public final String xpath_InfantSuffixListDropDown = "//select[contains(@id,'infantSuffix')]";
	@FindBy(xpath=xpath_InfantSuffixListDropDown)
	private List<WebElement> drpdn_InfantSuffixList;

	public final String xpath_InfantDOBListTextBox = "//input[contains(@id,'infantDateOfBirth')]";
	@FindBy(xpath=xpath_InfantDOBListTextBox)
	private List<WebElement> txtbx_InfantDOBList;

	//*******************************************************************
	//**************Special Services Passenger Page**********************
	//*******************************************************************
	public final String xpath_HearingImpairedListCheckBox = "//section[@data-qa='passenger-info']//label[contains(@for,'customCheckhearing-impaired')]";
	@FindBy(xpath=xpath_HearingImpairedListCheckBox)
	private List<WebElement> chkbx_HearingImpairedList;

	public final String xpath_VisionDisabilityListCheckBox = "//section[@data-qa='passenger-info']//label[contains(@for,'customCheckvision-disability')]";
	@FindBy(xpath=xpath_VisionDisabilityListCheckBox)
	private List<WebElement> chkbx_VisionDisabilityList;

	public final String xpath_ServiceAnimalListCheckBox = "//section[@data-qa='passenger-info']//label[contains(@for,'customCheckservice-animal')]";
	@FindBy(xpath=xpath_ServiceAnimalListCheckBox)
	private List<WebElement> chkbx_ServiceAnimalList;

	public final String xpath_EmotionalSupportAnimalListCheckBox = "//section[@data-qa='passenger-info']//label[contains(@for,'customCheckemotional-support')]";
	@FindBy(xpath=xpath_EmotionalSupportAnimalListCheckBox)
	private List<WebElement> chkbx_EmotionalSupportAnimalList;

	public final String xpath_PortableOxygenContainerListCheckBox = "//section[@data-qa='passenger-info']//label[contains(@for,'customCheckportable-oxygen')]";
	@FindBy(xpath=xpath_PortableOxygenContainerListCheckBox)
	private List<WebElement> chkbx_PortableOxygenContainerList;

	public final String xpath_OtherDisabilityListCheckBox = "//section[@data-qa='passenger-info']//label[contains(@for,'customCheckpage.other')]";
	@FindBy(xpath=xpath_OtherDisabilityListCheckBox)
	private List<WebElement> chkbx_OtherDisabilityList;

	//*******************************************************************
	//**************Wheelchair Services Passenger Page*******************
	//*******************************************************************
	public final String xpath_WheelChairToAndFromGateListCheckBox = "//input[@name='hasWheelchairToFromGate']//following-sibling::label";
	@FindBy(xpath=xpath_WheelChairToAndFromGateListCheckBox)
	private List<WebElement> chkbx_WheelChairToAndFromGateList;

	public final String xpath_WheelchairIHaveMyOwnListCheckBox = "//input[@name='hasWheelchair']//following-sibling::label";
	@FindBy(xpath=xpath_WheelchairIHaveMyOwnListCheckBox)
	private List<WebElement> chkbx_WheelchairIHaveMyOwnList;

	public final String xpath_WheelChairToAndFromSeatListCheckbox = "//input[@name='hasWheelchairCustomerAisleChair']//following-sibling::label";
	@FindBy(xpath=xpath_WheelChairToAndFromSeatListCheckbox)
	private List<WebElement> chkbx_WheelChairToAndFromSeatList;

	public final String xpath_WheelChairCompletelyImmobileListCheckBox = "//input[@name='hasWheelchairStraightBack']//following-sibling::label";
	@FindBy(xpath=xpath_WheelChairCompletelyImmobileListCheckBox)
	private List<WebElement> chkbx_WheelChairCompletelyImmobileList;

	public final String xpath_WheelChairTypeOfWheelChairDropDown = "//select[@name='wheelChairOwn']";
	@FindBy(xpath = xpath_WheelChairTypeOfWheelChairDropDown)
	private List<WebElement> drpdown_WheelChairTypeOfWheelChair;

	//*******************************************************************
	//**Voluntary Provision of Emergency Services Program Passenger Page*
	//*******************************************************************
	public final String xpath_VoluntaryProvisionofEmergencyServicesProgramListCheckBox = "//input[@name='isVolunteer']//following-sibling::label";
	@FindBy(xpath=xpath_VoluntaryProvisionofEmergencyServicesProgramListCheckBox)
	private List<WebElement> chkbx_VoluntaryProvisionofEmergencyServicesProgramList;

	//*******************************************************************
	//****************Redress Number Passenger Page**********************
	//*******************************************************************
	public final String xpath_PassengerRedressNumberListTextBox = "//section[@data-qa='passenger-info']//input[@name='redressNumber']";
	@FindBy(xpath = xpath_PassengerRedressNumberListTextBox)
	private List<WebElement> txtbx_PassengerRedressNumberList;

	//*******************************************************************
	//**************Who is driving Passenger Page************************
	//*******************************************************************
	public final String xpath_PrimaryDriverDropDown = "//select[contains(@id,'primaryDriver')]";
	@FindBy(xpath=xpath_PrimaryDriverDropDown)
	private WebElement drpdwn_PrimaryDriver;

	//*******************************************************************
	//**********************Infant with Seat*****************************
	//*******************************************************************
	public final String xpath_InfantTravelingWithCarSeatCheckBox = "//label[contains(@for,'hasCarSeat')]";
	@FindBy(xpath=xpath_InfantTravelingWithCarSeatCheckBox)
	private List<WebElement> chkbx_InfantTravelingWithCarSeat;

	//*******************************************************************
	//**************Duplicate Passenger Names Passenger Page*************
	//*******************************************************************
	public final String xpath_DuplicatePassengerHeaderText = "//div[@class='modal-header']/h2[contains(text(),'Duplicate Passenger') or contains(text(),'Duplicate Passenger')]";
	@FindBy(xpath=xpath_DuplicatePassengerHeaderText)
	private WebElement txt_DuplicatePassengerHeader;

	public final String xpath_DuplicatePassengerCloseButton = "//div[@class='modal-header']//button[@class='close']";
	@FindBy(xpath=xpath_DuplicatePassengerCloseButton)
	private WebElement btn_DuplicatePassengerClose;

	public final String xpath_EditDuplicateNamesButton = "//div[@class='modal-body']//button[contains(text(),'Edit Duplicate') or contains(text(),'Edit Duplicate')]";
	@FindBy(xpath=xpath_EditDuplicateNamesButton)
	private WebElement btn_EditDuplicateNames;

	//*******************************************************************
	//*************Military or Bundle Popup Passenger Page***************
	//*******************************************************************
	public final String xpath_MilitaryBundlePopupHeaderText = "//h2[@class='modal-title']";
	@FindBy(xpath=xpath_MilitaryBundlePopupHeaderText)
	private WebElement txt_MilitaryBundlePopupHeader;

	public final String xpath_MilitaryBundlePopupCloseButton = "//i[@class='icon-close']";
	@FindBy(xpath=xpath_MilitaryBundlePopupCloseButton)
	private WebElement btn_MilitaryBundlePopupClose;

	public final String xpath_MilitaryBundlePopupVerbiageText = "//div[@class='modal-body']/div/div/p";
	@FindBy(xpath=xpath_MilitaryBundlePopupVerbiageText)
	private WebElement txt_MilitaryBundlePopupVerbiage;

	public final String xpath_MilitaryBundlePopupMilitaryBagsRadioButton = "//label[@for='radio-military']";
	@FindBy(xpath=xpath_MilitaryBundlePopupMilitaryBagsRadioButton)
	private WebElement rdbtn_MilitaryBundlePopupMilitaryBags;

	public final String xpath_MilitaryBundlePopupBundleSavingsRadioButton = "//label[@for='radio-bundle']";
	@FindBy(xpath=xpath_MilitaryBundlePopupBundleSavingsRadioButton)
	private WebElement rdbtn_MilitaryBundlePopupBundleSavings;

	public final String xpath_MilitaryBundlePopupContinueButton = "//button[contains(text(),'Continue') or contains(text(),'Continuar')]";
	@FindBy(xpath=xpath_MilitaryBundlePopupContinueButton)
	private WebElement btn_MilitaryBundlePopupContinue;

	//*******************************************************************
	//*************Unaccompanied Minor Popup Passenger Page**************
	//*******************************************************************
	public final String xpath_UnaccompaniedMinorPopupHeaderText = "//div[@class='modal-header']/h2";
	@FindBy(xpath=xpath_UnaccompaniedMinorPopupHeaderText)
	private WebElement txt_UnaccompaniedMinorPopupHeader;

	public final String xpath_UnaccompaniedMinorPopupCloseButton = "//div[@class='modal-header']/button";
	@FindBy(xpath=xpath_UnaccompaniedMinorPopupCloseButton)
	private WebElement btn_UnaccompaniedMinorPopupClose;

	public final String xpath_UnaccompaniedMinorPopupSubHeaderText = "//div[@class='modal-body']/div/div//p";
	@FindBy(xpath=xpath_UnaccompaniedMinorPopupSubHeaderText)
	private List<WebElement> txt_UnaccompaniedMinorPopupSubHeader;

	public final String xpath_UnaccompaniedMinorPopupReturnToHomepageButton = "//div[@class='modal-body']//button";
	@FindBy(xpath=xpath_UnaccompaniedMinorPopupReturnToHomepageButton)
	private WebElement btn_UnaccompaniedMinorPopupReturnToHomepage;

	//*******************************************************************
	//******************Why can't you edit your name?********************
	//*******************************************************************
	public final String xpath_WhyCantEditYourNameLink = "//div[@data-qa='passenger-name-info']//button";
	@FindBy(xpath=xpath_WhyCantEditYourNameLink)
	private WebElement lnk_WhyCantEditYourName;

	public final String xpath_EditYourNamePopUpHeaderLink = "//h2[@class='modal-title']";
	@FindBy(xpath=xpath_EditYourNamePopUpHeaderLink)
	private WebElement lnk_EditYourNamePopUpHeader;

	public final String xpath_EditYourNamePopUpCrossIcon = "//div[@class='modal-header']//button[@class='close']";
	@FindBy(xpath=xpath_EditYourNamePopUpCrossIcon)
	private WebElement icn_EditYourNamePopUpCross;

	public final String xpath_EditYourNamePopUpCloseButton = "//div[@class='modal-body']/div[3]//button";
	@FindBy(xpath=xpath_EditYourNamePopUpCloseButton)
	private WebElement btn_EditYourNamePopUpClose;

	public final String xpath_EditYourNamePopUpLogOutButton = "//div[@class='modal-body']/div[2]//button";
	@FindBy(xpath=xpath_EditYourNamePopUpLogOutButton)
	private WebElement btn_EditYourNamePopUpLogOut;

	//*******************************************************************
	//*******************Contact Passenger Page**************************
	//*******************************************************************
	public final String xpath_PrimaryPassengerIstheContactPersonCheckBox = "//label[@for='check-usePrimaryAsContactName']";
	@FindBy(xpath=xpath_PrimaryPassengerIstheContactPersonCheckBox)
	private WebElement chkbx_PrimaryPassengerIstheContactPerson;

	public final String id_ContactPersonFirstNameTextBox = "firstName";
	@FindBy(id=id_ContactPersonFirstNameTextBox)
	private List<WebElement> txtbx_ContactPersonFirstName;

	public final String id_ContactPersonLastNameTextBox = "lastName";
	@FindBy(id=id_ContactPersonLastNameTextBox)
	private List<WebElement> txtbx_ContactPersonLastName;

	public final String id_ContactPersonAddressTextBox = "address";
	@FindBy(id=id_ContactPersonAddressTextBox)
	private WebElement txtbx_ContactPersonAddress;

	public final String id_ContactPersonCityTextBox = "city";
	@FindBy(id=id_ContactPersonCityTextBox)
	private WebElement txtbx_ContactPersonCity;

	public final String id_ContactPersonStateDropDown = "provinceState";
	@FindBy(id=id_ContactPersonStateDropDown)
	private WebElement drpdwn_ContactPersonState;

	public final String id_ContactPersonZipPostalCodeTextBox = "postalCode";
	@FindBy(id=id_ContactPersonZipPostalCodeTextBox)
	private WebElement txtbx_ContactPersonZipPostalCode;

	public final String xpath_ContactPersonCountryDropDown = "//select[@id='countryCode']";
	@FindBy(xpath = xpath_ContactPersonCountryDropDown)
	private WebElement drpdwn_ContactPersonCountry;

	public final String id_ContactPersonEmailTextBox = "contactEmailPrimary";
	@FindBy(id=id_ContactPersonEmailTextBox)
	private WebElement txtbx_ContactPersonEmail;

	public final String id_ContactPersonConfirmEmailTextBox = "contactEmailConfirm";
	@FindBy(id=id_ContactPersonConfirmEmailTextBox)
	private WebElement txtbx_ContactPersonConfirmEmail;

	public final String id_ContactPersonPhoneCountryCodeDropDown = "phoneCountryType";
	@FindBy(id=id_ContactPersonPhoneCountryCodeDropDown)
	private WebElement drpdwn_ContactPersonPhoneCountryCode; //"United State +1" , "India +91"

	public final String id_ContactPersonPhoneNumberTextBox = "phoneNumber";
	@FindBy(id=id_ContactPersonPhoneNumberTextBox)
	private WebElement txtbx_ContactPersonPhoneNumber; //Number should not start with 1

	public final String xpath_ContactPersonFirstNameLabel = "//label[@for='firstName']";
	@FindBy(xpath = xpath_ContactPersonFirstNameLabel)
	private WebElement lbl_ContactPersonFirstName;

	public final String xpath_ContactPersonLastNameLabel = "//label[@for='lastName']";
	@FindBy(xpath = xpath_ContactPersonLastNameLabel)
	private WebElement lbl_ContactPersonLastName;

	public final String xpath_ContactPersonAddressLabel = "//label[@for='address']";
	@FindBy(xpath = xpath_ContactPersonAddressLabel)
	private WebElement lbl_ContactPersonAddress;

	public final String xpath_ContactPersonCityLabel = "//label[@for='city']";
	@FindBy(xpath = xpath_ContactPersonCityLabel)
	private WebElement lbl_ContactPersonCity;

	public final String xpath_ContactPersonEmailLabel = "//label[@for='contactEmailPrimary']";
	@FindBy(xpath = xpath_ContactPersonEmailLabel)
	private WebElement lbl_ContactPersonEmail;

	public final String xpath_ContactPersonStateLabel = "//label[@for='provinceState']";
	@FindBy(xpath = xpath_ContactPersonStateLabel)
	private WebElement lbl_ContactPersonState;

	public final String xpath_ContactPersonZipCodeLabel = "//label[@for='postalCode']";
	@FindBy(xpath = xpath_ContactPersonZipCodeLabel)
	private WebElement lbl_ContactPersonZipCode;

	public final String xpath_ContactPersonCountryLabel = "//label[@for='countryCode']";
	@FindBy(xpath = xpath_ContactPersonCountryLabel)
	private WebElement lbl_ContactPersonCountry;

	public final String xpath_ContactPersonConfirmEmailLabel = "//label[@for='contactEmailConfirm']";
	@FindBy(xpath = xpath_ContactPersonConfirmEmailLabel)
	private WebElement lbl_ContactPersonConfirmEmail;

	public final String xpath_ContactPersonPhoneCountryCodeLabel = "//label[@for='phoneCountryType']";
	@FindBy(xpath = xpath_ContactPersonPhoneCountryCodeLabel)
	private WebElement lbl_ContactPersonPhoneCountryCode;

	public final String xpath_ContactPersonPhoneNumberLabel = "//label[@for='phoneNumber']";
	@FindBy(xpath = xpath_ContactPersonPhoneNumberLabel)
	private WebElement lbl_ContactPersonPhoneNumber;

	public final String xpath_ContactPersonSMSNotificationsCheckBox = "//label[@for='check-smsOptIn']";
	@FindBy(xpath = xpath_ContactPersonSMSNotificationsCheckBox)
	private WebElement chkbx_ContactPersonSMSNotifications;

	//*******************************************************************
	//**************T&C || Privacy policy Link***************************
	//*******************************************************************
	public final String xpath_TermsAndConditionsLink = "//a[contains(text(),'Terms & Conditions')]";
	@FindBy(xpath=xpath_TermsAndConditionsLink)
	private WebElement lnk_TermsAndConditions;

	public final String xpath_PrivacyPolicyLink = "//a[contains(text(),'Privacy Policy')]";
	@FindBy(xpath=xpath_PrivacyPolicyLink)
	private WebElement lnk_PrivacyPolicy;


	//*******************************************************************
	//*****************Join FS Passenger Page****************************
	//*******************************************************************
	public final String xpath_YesIwantToJoinFSCheckBox = "//label[@for='fSEnrollCheckbox']";
	@FindBy(xpath=xpath_YesIwantToJoinFSCheckBox)
	private WebElement chkbx_YesIwantToJoinFS;

	public final String id_FSEnrollmentPasswordTextBox = "passwordPrimary";
	@FindBy(id=id_FSEnrollmentPasswordTextBox)
	private WebElement txtbx_FSEnrollmentPassword;

	public final String id_FSEnrollmentConfirmPasswordTextBox = "passwordConfirmed";
	@FindBy(id=id_FSEnrollmentConfirmPasswordTextBox)
	private WebElement txtbx_FSEnrollmentConfirmPassword;

	public final String xpath_FSEnrollmentPasswordLabel = "//label[@for='passwordPrimary']";
	@FindBy(xpath=xpath_FSEnrollmentPasswordLabel)
	private WebElement lbl_FSEnrollmentPassword;

	public final String xpath_FSEnrollmentConfirmPasswordLabel = "//label[@for='passwordConfirmed']";
	@FindBy(xpath=xpath_FSEnrollmentConfirmPasswordLabel)
	private WebElement lbl_FSEnrollmentConfirmPassword;

	public final String xpath_FSEnrollmentEmailAddressAlreadyInUseErrorText = "//section[@ngmodelgroup='freeSpirit']//div[contains(@class,'error')]";
	@FindBy(xpath=xpath_FSEnrollmentEmailAddressAlreadyInUseErrorText)
	private WebElement txt_FSEnrollmentEmailAddressAlreadyInUseError;

	//*******************************************************************
	//*****************Already A Member Pop Up***************************
	//*******************************************************************
	public final String xpath_AlreadyAMemberPopUpHeaderText = "//app-branded-modal//h2";
	@FindBy(xpath=xpath_AlreadyAMemberPopUpHeaderText)
	private WebElement txt_AlreadyAMemberPopUpHeader;

	public final String xpath_AlreadyAMemberPopUpHeaderBackgroundPanel = "//app-branded-modal/div";
	@FindBy(xpath=xpath_AlreadyAMemberPopUpHeaderBackgroundPanel)
	private WebElement pnl_AlreadyAMemberPopUpHeaderBackground;

	public final String xpath_AlreadyAMemberPopUpCloseButton = "//app-branded-modal//i[@class='icon-close']";
	@FindBy(xpath=xpath_AlreadyAMemberPopUpCloseButton)
	private WebElement btn_AlreadyAMemberPopUpClose;

	public final String xpath_AlreadyAMemberPopUpUnderHeaderText = "//app-branded-modal//p[contains(text(),'The email address you entered is already in use')]";
	@FindBy(xpath=xpath_AlreadyAMemberPopUpUnderHeaderText)
	private WebElement txt_AlreadyAMemberPopUpUnderHeader;

	public final String xpath_AlreadyAMemberPopUpEmailAddressOrFSNumberLabel = "//app-branded-modal//label[@for='username']//span";
	@FindBy(xpath=xpath_AlreadyAMemberPopUpEmailAddressOrFSNumberLabel)
	private WebElement lbl_AlreadyAMemberPopUpEmailAddressOrFSNumber;

	public final String id_AlreadyAMemberPopUpEmailAddressOrFSNumberTextBox = "username";
	@FindBy(id=id_AlreadyAMemberPopUpEmailAddressOrFSNumberTextBox)
	private WebElement txtbx_AlreadyAMemberPopUpEmailAddressOrFSNumber;

	public final String xpath_AlreadyAMemberPopUpPasswordLabel = "//app-branded-modal//label[@for='password']//span";
	@FindBy(xpath=xpath_AlreadyAMemberPopUpPasswordLabel)
	private WebElement lbl_AlreadyAMemberPopUpPassword;

	public final String id_AlreadyAMemberPopUpPasswordTextBox = "password";
	@FindBy(id=id_AlreadyAMemberPopUpPasswordTextBox)
	private WebElement txtbx_AlreadyAMemberPopUpPassword;

	public final String xpath_AlreadyAMemberPopUpLoginButton = "//button[@class='btn btn-primary d-sm-inline']";
	@FindBy(xpath=xpath_AlreadyAMemberPopUpLoginButton)
	private WebElement btn_AlreadyAMemberPopUpLogin;

	public final String xpath_AlreadyAMemberPopUpUnderLoginButtonButton = "//app-branded-modal//p[contains(text(),'The email address you entered is already in use')]//following::p";
	@FindBy(xpath=xpath_AlreadyAMemberPopUpUnderLoginButtonButton)
	private WebElement txt_AlreadyAMemberPopUpUnderLoginButton;

	public final String xpath_AlreadyAMemberPopUpContinueWithStandardFaresButton = "//button[@class='btn btn-secondary btn-block']";
	@FindBy(xpath=xpath_AlreadyAMemberPopUpContinueWithStandardFaresButton)
	private WebElement btn_AlreadyAMemberPopUpContinueWithStandardFares;

//  public WebElement getAlreadyAMemberPopUpHeaderText() {return txt_AlreadyAMemberPopUpHeader;}
//
//	public WebElement getAlreadyAMemberPopUpCloseButton() {return btn_AlreadyAMemberPopUpClose;}
//
//	public WebElement getAlreadyAMemberPopUpUnderHeaderText() {return txt_AlreadyAMemberPopUpUnderHeader;}
//
//	public WebElement getAlreadyAMemberPopUpEmailAddressOrFSNumberLabel() {return lbl_AlreadyAMemberPopUpEmailAddressOrFSNumber;}
//
//	public WebElement getAlreadyAMemberPopUpEmailAddressOrFSNumberTextBox() {return txtbx_AlreadyAMemberPopUpEmailAddressOrFSNumber;}
//
//	public WebElement getAlreadyAMemberPopUpPasswordLabel() {return lbl_AlreadyAMemberPopUpPassword;}
//
//	public WebElement getAlreadyAMemberPopUpPasswordTextBox() {return txtbx_AlreadyAMemberPopUpPassword;}
//
//	public WebElement getAlreadyAMemberPopUpLoginButton() {return btn_AlreadyAMemberPopUpLogin;}
//
//	public WebElement getAlreadyAMemberPopUpUnderLoginButtonText() {return txt_AlreadyAMemberPopUpUnderLoginButton;}
//
//	public WebElement getAlreadyAMemberPopUpContinueWithStandardFaresButton() {return btn_AlreadyAMemberPopUpContinueWithStandardFares;}

	//*******************************************************************
	//**********9DFC Upsell Enrollment Upsell Passenger Page*************
	//*******************************************************************
	public final String xpath_9FCUpsellCloseButton = "//ngb-modal-window//i[contains(@class,'close')]";
	@FindBy(xpath=xpath_9FCUpsellCloseButton) //Exit button Top Right of pop-up
	private WebElement btn_9FCUpsellCloseBttn;

	public final String xpath_9FCUpsellSignUpButton = "//ngb-modal-window//button[contains(text(),'sign up')]";
	@FindBy(xpath=xpath_9FCUpsellSignUpButton) //no Spanish translation
	private WebElement btn_9FCUpsellSignUp;

	public final String id_9FCUpselSignUpChoosePasswordTextBox = "password";
	@FindBy(id=id_9FCUpselSignUpChoosePasswordTextBox)
	private WebElement txtbx_9FCUpselSignUpChoosePassword;

	public final String xpath_9FCUpsellSignUpConfirmPasswordTextBox = "confirmPassword";
	@FindBy(id=xpath_9FCUpsellSignUpConfirmPasswordTextBox)
	private WebElement txtbx_9FCUpsellSignUpConfirmPassword;

	public final String xpath_9FCUpsellSignUpWithEmailButton = "//ngb-modal-window//button[contains(text(),'sign up with email')]";
	@FindBy(xpath=xpath_9FCUpsellSignUpWithEmailButton) //no Spanish translation
	private WebElement btn_9FCUpsellSignUpWithEmail;

	public final String xpath_9FCUpsellLogInButton = "//ngb-modal-window//button[contains(text(),'log in')]";
	@FindBy(xpath=xpath_9FCUpsellLogInButton) //no Spanish translation
	private WebElement btn_9FCUpsellLogIn;

	public final String xpath_9FCUpsellLogInUserNameTextBox = "//ngb-modal-window//input[@id='username']";
	@FindBy(xpath=xpath_9FCUpsellLogInUserNameTextBox)
	private WebElement txtbx_9FCUpsellLogInUserName;

	public final String xpath_9FCUpsellLogInPasswordTextBox = "//ngb-modal-window//input[@id='password']";
	@FindBy(xpath=xpath_9FCUpsellLogInPasswordTextBox)
	private WebElement txtbx_9FCUpsellLogInPassword;

	public final String xpath_9FCUpsellLogInResetPasswordTextBox = "//ngb-modal-window//button[contains(text(),'Reset Password')]";
	@FindBy(xpath=xpath_9FCUpsellLogInResetPasswordTextBox) //no Spanish translation
	private WebElement txtbx_9FCUpsellLogInResetPassword;

	public final String xpath_9FCUpsellLogInUNPWSubmitLogInTextBox = "//ngb-modal-window//button[contains(text(),'Log In')]";
	@FindBy(xpath=xpath_9FCUpsellLogInUNPWSubmitLogInTextBox)  //no Spanish translation
	private WebElement txtbx_9FCUpsellLogInUNPWSubmitLogIn;

	public final String xpath_9FCUpsellContinueWithStandardFareButton = "//button[contains(text(), 'continue with standard fares')]";
	@FindBy(xpath=xpath_9FCUpsellContinueWithStandardFareButton) //no Spanish translation
	private WebElement btn_9FCUpsellContinueWithStandardFare;

	//*******************************************************************
	//*******************Overlay Container Passenger Page***************
	//*******************************************************************
	public final String xpath_OverlayErrorMessageText = "//div[contains(@class,'toast-error')]";
	@FindBy(xpath=xpath_OverlayErrorMessageText)
	private List<WebElement> txt_OverlayErrorMessage;

	//*******************************************************************
	//*******************Deals Subscription Passenger Page***************  Missing from page
	//*******************************************************************


	//*******************************************************************
	//*****SMS notifications (Currently Suppressed) Passenger Page******* Missing From Page
	//*******************************************************************


	//*******************************************************************
	//***************Continue Button Passenger Page*********************
	//*******************************************************************
	public final String xpath_ContinueToBagsButton = "//button[contains(text(),'continue') or contains(text(),'continuar')]";
	@FindBy(xpath=xpath_ContinueToBagsButton)
	private WebElement btn_ContinueToBags;

	//*******************************************************************
	//*******************Insufficient Age Pop Up*************************
	//*******************************************************************
	public final String xpath_InsufficientAgePopUpHeaderText = "//h2[@class='modal-title']";
	@FindBy(xpath=xpath_InsufficientAgePopUpHeaderText)
	private WebElement txt_InsufficientAgePopUpHeader;

	public final String xpath_InsufficientAgePopUpCloseButton = "//i[@class='icon-close']";
	@FindBy(xpath=xpath_InsufficientAgePopUpCloseButton)
	private WebElement btn_InsufficientAgePopUpClose;

	public final String xpath_InsufficientAgePopUpVerbageText = "//div[@class='modal-body']";
	@FindBy(xpath = xpath_InsufficientAgePopUpVerbageText)
	private WebElement txt_InsufficientAgePopUpVerbage;

	public final String xpath_InsufficientAgePopUpFlightOnlyButton = "//button[contains(text(),'Flight Only') or contains(text(),'Solo vuelo')]";
	@FindBy(xpath=xpath_InsufficientAgePopUpFlightOnlyButton)
	private WebElement btn_InsufficientAgePopUpFlightOnly;

	//*******************************************************************
	//*******************Insufficient Age Pop Up*************************
	//*******************************************************************
	public final String xpath_YoungTravelerPopUpHeaderText = "//h2[@class='modal-title']";
	@FindBy(xpath=xpath_YoungTravelerPopUpHeaderText)
	private WebElement txt_YoungTravelerPopUpHeader;

	public final String xpath_YoungTravelerPopUpCloseXButton = "//i[@class='icon-close']";
	@FindBy(xpath=xpath_YoungTravelerPopUpCloseXButton)
	private WebElement btn_YoungTravelerPopUpCloseX;

	public final String xpath_YoungTravelerPopUpReturnToHomePageButton = "//button[contains(text(),'Return to Homepage')]";
	@FindBy(xpath=xpath_YoungTravelerPopUpReturnToHomePageButton)
	private WebElement btn_YoungTravelerPopUpReturnToHomePage;

	public final String xpath_YoungTravelerPopUpCloseButton = "//a[contains(text(),'Close')]";
	@FindBy(xpath=xpath_YoungTravelerPopUpCloseButton)
	private WebElement btn_YoungTravelerPopUpClose;

	//*******************************************************************
	//*****************Tool Tip of Passenger Page************************
	//*******************************************************************

	public final String xpath_TravelWithCarSeatToolTipImage = "//span[@tooltipclass='carSeatPopover']/i";
	@FindBy(xpath = xpath_TravelWithCarSeatToolTipImage)
	private List<WebElement> img_TravelWithCarSeatToolTip;

	public final String xpath_TravelWithCarSeatToolTipWindowHeaderText = "(//ngb-tooltip-window//p)[1]";
	@FindBy(xpath = xpath_TravelWithCarSeatToolTipWindowHeaderText)
	private WebElement txt_TravelWithCarSeatToolTipWindowHeader;

	public final String xpath_TravelWithCarSeatToolTipWindowBodyText = "(//ngb-tooltip-window//p)[2]";
	@FindBy(xpath = xpath_TravelWithCarSeatToolTipWindowBodyText)
	private WebElement txt_TravelWithCarSeatToolTipWindowBody;

	public final String xpath_KnownTravelerNumberToolTipImage = "//input[@name='knownTravelerNumber']//following-sibling::i";
	@FindBy(xpath = xpath_KnownTravelerNumberToolTipImage)
	private List<WebElement> img_KnownTravelerNumberToolTip;

	public final String xpath_FSNumberToolTipImage = "//section[@data-qa='passenger-info']//input[contains(@id,'number')]//following-sibling::i";
	@FindBy(xpath = xpath_FSNumberToolTipImage)
	private List<WebElement> img_FSNumberToolTip;

	public final String xpath_RedressNumberToolTipImage = "//section[@data-qa='passenger-info']//input[@name='redressNumber']//following-sibling::i";
	@FindBy(xpath = xpath_RedressNumberToolTipImage)
	private List<WebElement> img_RedressNumberToolTip;

	public final String xpath_ServiceAnimalToolTipImage = "//section[@data-qa='passenger-info']//label[contains(@for,'customCheckservice-animal')]/../../following-sibling::span/i";
	@FindBy(xpath = xpath_ServiceAnimalToolTipImage)
	private List<WebElement> img_ServiceAnimalToolTip;

	public final String xpath_PortableOxygenToolTipImage = "//section[@data-qa='passenger-info']//label[contains(@for,'customCheckportable-oxygen')]/../../following-sibling::span/i";
	@FindBy(xpath = xpath_PortableOxygenToolTipImage)
	private List<WebElement> img_PortableOxygenToolTip;

	public final String xpath_EmotionalSupportAnimalToolTipImage = "//section[@data-qa='passenger-info']//label[contains(@for,'customCheckemotional-support')]/../../following-sibling::span/i";
	@FindBy(xpath = xpath_EmotionalSupportAnimalToolTipImage)
	private List<WebElement> img_EmotionalSupportAnimalToolTip;

	public final String xpath_OtherServicesToolTipImage = "//section[@data-qa='passenger-info']//label[contains(@for,'customCheckpage.other')]//i";
	@FindBy(xpath = xpath_OtherServicesToolTipImage)
	private List<WebElement> img_OtherServicesToolTip;

	public final String xpath_VoluntaryProvisionTooltipImage = "//input[@name='isVolunteer']//following-sibling::label//i";
	@FindBy(xpath = xpath_VoluntaryProvisionTooltipImage)
	private List<WebElement> img_VoluntaryProvisionTooltip;

	public final String xpath_FSNumberToolTipWindowBodyText = "//ngb-popover-window/div/div";
	@FindBy(xpath = xpath_FSNumberToolTipWindowBodyText)
	private WebElement txt_FSNumberToolTipWindowBody;

	public final String xpath_WheelChairServicesToolTipImage = "//div[contains(text(),'Wheelchair Services') or contains(text(),'Servicio de Silla de Ruedas')]//i";
	@FindBy(xpath = xpath_WheelChairServicesToolTipImage)
	private List<WebElement> img_WheelChairServicesToolTip;

	//*******************************************************************
	//*****************Change Information Page **************************
	//*******************************************************************

	public final String xpath_CancelChangesButton = "//button[contains(text(), 'cancel changes') or contains(text(), 'Cancelar')]";
	@FindBy(xpath = xpath_CancelChangesButton)
	private WebElement btn_CancelChanges;

	//************************************************************************************************************************************************
	//******************************************************Start getter Methods of Passenger Page****************************************************
	//************************************************************************************************************************************************

	public WebElement getPassengerInformationText(){
		return txt_PassengerInformation;
	}

	//*******************************************************************
	//*******************Inline Log-in Passenger Page********************
	//*******************************************************************
	public WebElement getUserNameInLineLogInTextBox() {
		return txtbx_UserNameInLineLogIn;
	}

	public WebElement getPasswordInLineLogInTextBox() {
		return txtbx_PasswordInLineLogIn;
	}

	public WebElement getLogInButtonInLineLogInButton() {
		return btn_LogInButtonInLineLogIn;
	}

	public WebElement getResetPasswordInLineLogInLink() {
		return lnk_ResetPasswordInLineLogIn;
	}

	//*******************************************************************
	//*******************Passenger(s) Passenger Page*********************
	//*******************************************************************
	public List<WebElement> getPassengerHeaderText() {
		return txt_PassengerHeader;
	}

	public List<WebElement> getAdultTitleListDropDown() {
		return drpdwn_AdultTitleList;
	}

	public List<WebElement> getAdultFirstNameListTextBox() {
		return txtbx_AdultFirstNameList;
	}

	public List<WebElement> getAdultMiddleNameListTextBox() {
		return txtbx_AdultMiddleNameList;
	}

	public List<WebElement> getAdultLastNameListTextBox(){
		return txtbx_AdultLastNameList;
	}

	public List<WebElement> getAdultSuffixListDropDown(){
		return drpdwn_AdultSuffixList;
	}

	public List<WebElement> getAdultDOBListTextBox() {
		return txtbx_AdultDOBList;
	}

	public List<WebElement> getAdultKTNListTextBox() {
		return txtbx_AdultKTNList;
	}

	public List<WebElement> getAdultFSNumberListTextBox() {
		return txtbx_AdultFSNumberList;
	}

	public List<WebElement> getAdditionalServicesListLinkButton() {
		return lnkbtn_AdditionalServicesList;
	}

	public List<WebElement> getRedressNumberListLinkButton() {
		return lnkbtn_RedressNumberList;
	}

	public List<WebElement> getActiveMilitaryPersonnelListCheckBox() {

		ArrayList<WebElement> elementList = new ArrayList<>();
		for(WebElement element:chkbx_ActiveMilitaryPersonnelList){
			if(element.isDisplayed()){
				elementList.add(element);
			}
		}

		return elementList;
	}

	//**************************************
	//***********Infant On Lap**************
	//**************************************
	public List<WebElement> getInfantTitleListDropDown() {
		return drpdwn_InfantTitleList;
	}

	public List<WebElement> getInfantFirstNameListTextBox() {
		return txtbx_InfantFirstNameList;
	}

	public List<WebElement> getInfantMiddleNameListTextBox() {
		return txtbx_InfantMiddleNameList;
	}

	public List<WebElement> getInfantLastNameListTextBox() {
		return txtbx_InfantLastNameList;
	}

	public List<WebElement> getInfantSuffixListDropDown() {
		return drpdn_InfantSuffixList;
	}

	public List<WebElement> getInfantDOBListTextBox() {
		return txtbx_InfantDOBList;
	}

	//*******************************************************************
	//**************Special Services Passenger Page*******************
	//*******************************************************************
	public List<WebElement> getHearingImpairedListCheckBox() {
		return chkbx_HearingImpairedList;
	}

	public List<WebElement> getVisionDisabilityListCheckBox() {
		return chkbx_VisionDisabilityList;
	}

	public List<WebElement> getServiceAnimalListCheckBox() {
		return chkbx_ServiceAnimalList;
	}

	public List<WebElement> getEmotionalSupportAnimalListCheckBox() {
		return chkbx_EmotionalSupportAnimalList;
	}

	public List<WebElement> getPortableOxygenContainerListCheckBox() {
		return chkbx_PortableOxygenContainerList;
	}

	public List<WebElement> getOtherDisabilityListCheckBox() {
		return chkbx_OtherDisabilityList;
	}

	//*******************************************************************
	//**************Wheelchair Services Passenger Page*******************
	//*******************************************************************
	public List<WebElement> getWheelChairToAndFromGateListCheckBox() {
		return chkbx_WheelChairToAndFromGateList;
	}

	public List<WebElement> getWheelchairIHaveMyOwnListCheckBox() {
		return chkbx_WheelchairIHaveMyOwnList;
	}

	public List<WebElement> getWheelChairToAndFromSeatListCheckBox() {
		return chkbx_WheelChairToAndFromSeatList;
	}

	public List<WebElement> getWheelChairCompletelyImmobileListCheckBox() {
		return chkbx_WheelChairCompletelyImmobileList;
	}

	public List<WebElement> getWheelChairTypeOfWheelChairDropDown(){
		return drpdown_WheelChairTypeOfWheelChair;
	}

	//*******************************************************************
	//**Voluntary Provision of Emergency Services Program Passenger Page*
	//*******************************************************************

	public List<WebElement> getVoluntaryProvisionofEmergencyServicesProgramListCheckBox() {
		return chkbx_VoluntaryProvisionofEmergencyServicesProgramList;
	}

	//*******************************************************************
	//****************Redress Number Passenger Page**********************
	//*******************************************************************
	public List<WebElement> getPassengerRedressNumberListTextBox() {
		return txtbx_PassengerRedressNumberList;
	}

	//*******************************************************************
	//**************Who is driving Passenger Page************************
	//*******************************************************************
	public WebElement getPrimaryDriverDropDown() {
		return drpdwn_PrimaryDriver;
	}

	//*******************************************************************
	//**********************Infant with Seat*****************************
	//*******************************************************************
	public List<WebElement> getInfantTravelingWithCarSeatCheckBox() {
		return chkbx_InfantTravelingWithCarSeat;
	}
	//*******************************************************************
	//**************Duplicate Passenger Names Passenger Page*************
	//*******************************************************************
	public WebElement getDuplicatePassengerHeaderText() {
		return txt_DuplicatePassengerHeader;
	}

	public WebElement getDuplicatePassengerCloseButton() {
		return btn_DuplicatePassengerClose;
	}

	public WebElement getEditDuplicateNamesButton() {
		return btn_EditDuplicateNames;
	}

	//*******************************************************************
	//*************Military or Bundle Popup Passenger Page***************
	//*******************************************************************
	public WebElement getMilitaryBundlePopupHeaderText() {
		return txt_MilitaryBundlePopupHeader;
	}

	public WebElement getMilitaryBundlePopupCloseButton() {
		return btn_MilitaryBundlePopupClose;
	}

	public WebElement getMilitaryBundlePopupVerbiageText() {
		return txt_MilitaryBundlePopupVerbiage;
	}

	public WebElement getMilitaryBundlePopupMilitaryBagsRadioButton() {
		return rdbtn_MilitaryBundlePopupMilitaryBags;
	}

	public WebElement getMilitaryBundlePopupBundleRadioButton() {
		return rdbtn_MilitaryBundlePopupBundleSavings;
	}

	public WebElement getMilitaryBundlePopupContinueButton() {
		return btn_MilitaryBundlePopupContinue;
	}

	//*******************************************************************
	//*************Unaccompanied Minor Popup Passenger Page**************
	//*******************************************************************
	public WebElement getUnaccompaniedMinorPopupHeaderText() {
		return txt_UnaccompaniedMinorPopupHeader;
	}

	public WebElement getUnaccompaniedMinorPopupCloseButton() {
		return btn_UnaccompaniedMinorPopupClose;
	}

	public List<WebElement> getUnaccompaniedMinorPopupSubHeaderText() {
		return txt_UnaccompaniedMinorPopupSubHeader;
	}

	public WebElement getUnaccompaniedMinorPopupReturnToHomepageButton() {
		return btn_UnaccompaniedMinorPopupReturnToHomepage;
	}

	//*******************************************************************
	//******************Why can't you edit your name?********************
	//*******************************************************************
	public WebElement getWhyCantEditYourNameLink() {
		return lnk_WhyCantEditYourName;
	}

	public WebElement getEditYourNamePopUpHeaderText() {
		return lnk_EditYourNamePopUpHeader;
	}

	public WebElement getEditYourNamePopUpCrossIcon() {
		return icn_EditYourNamePopUpCross;
	}

	public WebElement getEditYourNamePopUpCloseButton() {
		return btn_EditYourNamePopUpClose;
	}


	public WebElement getEditYourNamePopUpLogOutButton() {
		return btn_EditYourNamePopUpLogOut;
	}


	//*******************************************************************
	//*******************Contact Passenger Page**********************
	//*******************************************************************
	public WebElement getPrimaryPassengerIstheContactPersonCheckBox() {
		return chkbx_PrimaryPassengerIstheContactPerson;
	}

	public List<WebElement> getContactPersonFirstNameTextBox() {
		return txtbx_ContactPersonFirstName;
	}

	public List<WebElement> getContactPersonLastNameTextBox() {
		return txtbx_ContactPersonLastName;
	}

	public WebElement getContactPersonAddressTextBox() {
		return txtbx_ContactPersonAddress;
	}

	public WebElement getContactPersonCityTextBox() {
		return txtbx_ContactPersonCity;
	}

	public WebElement getContactPersonStateDropDown() {
		return drpdwn_ContactPersonState;
	}

	public WebElement getContactPersonZipPostalCodeTextBox() {
		return txtbx_ContactPersonZipPostalCode;
	}

	public WebElement getContactPersonCountryDropDown() {
		return drpdwn_ContactPersonCountry;
	}

	public WebElement getContactPersonEmailTextBox() {
		return txtbx_ContactPersonEmail;
	}

	public WebElement getContactPersonConfirmEmailTextBox() {
		return txtbx_ContactPersonConfirmEmail;
	}

	public WebElement getContactPersonPhoneCountryCodeDropDown() {
		return drpdwn_ContactPersonPhoneCountryCode;
	}

	public WebElement getContactPersonPhoneNumberTextBox() {
		return txtbx_ContactPersonPhoneNumber;
	}

	/////////////////////*******************////////////////////////////////////
	public WebElement getContactPersonFirstNameLabel() {return lbl_ContactPersonFirstName;}

	public WebElement getContactPersonLastNameLabel() {return lbl_ContactPersonLastName;}

	public WebElement getContactPersonAddressLabel() {return lbl_ContactPersonAddress;}

	public WebElement getContactPersonCityLabel() {return lbl_ContactPersonCity;}

	public WebElement getContactPersonEmailLabel() {return lbl_ContactPersonEmail;}

	public WebElement getContactPersonStateLabel() {return lbl_ContactPersonState;}

	public WebElement getContactPersonZipCodeLabel() {return lbl_ContactPersonZipCode;}

	public WebElement getContactPersonCountryLabel() {return lbl_ContactPersonCountry;}

	public WebElement getContactPersonConfirmEmailLabel() {return lbl_ContactPersonConfirmEmail;}

	public WebElement getContactPersonPhoneCountryCodeLabel() {return lbl_ContactPersonPhoneCountryCode;}

	public WebElement getContactPersonPhoneNumberLabel() {return lbl_ContactPersonPhoneNumber;}

	public WebElement getContactPersonSMSNotificationsCheckBox() {return chkbx_ContactPersonSMSNotifications;}

	//*******************************************************************
	//**************T&C || Privacy policy Link***************************
	//*******************************************************************
	public WebElement getTermsAndConditionsLink() {return lnk_TermsAndConditions;}

	public WebElement getPrivacyPolicyLink() {return lnk_PrivacyPolicy;}

	//*******************************************************************
	//*****************Join FS Passenger Page****************************
	//*******************************************************************
	public WebElement getYesIwantToJoinFSCheckBox() {
		return chkbx_YesIwantToJoinFS;
	}

	public WebElement getFSEnrollmentPasswordTextBox() {
		return txtbx_FSEnrollmentPassword;
	}

	public WebElement getFSEnrollmentConfirmPasswordTextBox() {
		return txtbx_FSEnrollmentConfirmPassword;
	}

	public WebElement getFSEnrollmentPasswordLabel() {return lbl_FSEnrollmentPassword;}

	public WebElement getFSEnrollmentConfirmPasswordLabel() {return lbl_FSEnrollmentConfirmPassword;}

	public WebElement getFSEnrollmentEmailAddressAlreadyInUseErrorText() {return txt_FSEnrollmentEmailAddressAlreadyInUseError;}


	//*******************************************************************
	//*****************Already A Member Pop Up***************************
	//*******************************************************************

	public WebElement getAlreadyAMemberPopUpHeaderText() {return txt_AlreadyAMemberPopUpHeader;}

	public WebElement getAlreadyAMemberPopUpHeaderBackgroundPanel() {return pnl_AlreadyAMemberPopUpHeaderBackground;}

	public WebElement getAlreadyAMemberPopUpCloseButton() {return btn_AlreadyAMemberPopUpClose;}

	public WebElement getAlreadyAMemberPopUpUnderHeaderText() {return txt_AlreadyAMemberPopUpUnderHeader;}

	public WebElement getAlreadyAMemberPopUpEmailAddressOrFSNumberLabel() {return lbl_AlreadyAMemberPopUpEmailAddressOrFSNumber;}

	public WebElement getAlreadyAMemberPopUpEmailAddressOrFSNumberTextBox() {return txtbx_AlreadyAMemberPopUpEmailAddressOrFSNumber;}

	public WebElement getAlreadyAMemberPopUpPasswordLabel() {return lbl_AlreadyAMemberPopUpPassword;}

	public WebElement getAlreadyAMemberPopUpPasswordTextBox() {return txtbx_AlreadyAMemberPopUpPassword;}

	public WebElement getAlreadyAMemberPopUpLoginButton() {return btn_AlreadyAMemberPopUpLogin;}

	public WebElement getAlreadyAMemberPopUpUnderLoginButtonText() {return txt_AlreadyAMemberPopUpUnderLoginButton;}

	public WebElement getAlreadyAMemberPopUpContinueWithStandardFaresButton() {return btn_AlreadyAMemberPopUpContinueWithStandardFares;}

	//*******************************************************************
	//**********9DFC Upsell Enrollment Upsell Passenger Page*************
	//*******************************************************************
	public WebElement get9FCUpsellCloseBttnButton() {
		return btn_9FCUpsellCloseBttn;
	}

	public WebElement get9FCUpsellSignUpButton() {
		return btn_9FCUpsellSignUp;
	}

	public WebElement get9FCUpselSignUpChoosePasswordTextBox() {
		return txtbx_9FCUpselSignUpChoosePassword;
	}

	public WebElement get9FCUpsellSignUpConfirmPasswordTextBox() {
		return txtbx_9FCUpsellSignUpConfirmPassword;
	}

	public WebElement get9FCUpsellSignUpWithEmailButton() {
		return btn_9FCUpsellSignUpWithEmail;
	}

	public WebElement get9FCUpsellLogInButton() {
		return btn_9FCUpsellLogIn;
	}

	public WebElement get9FCUpsellLogInUserNameTextBox() {
		return txtbx_9FCUpsellLogInUserName;
	}

	public WebElement get9FCUpsellLogInPasswordTextBox() {
		return txtbx_9FCUpsellLogInPassword;
	}

	public WebElement getFCUpsellLogInResetPasswordTextBox() {
		return txtbx_9FCUpsellLogInResetPassword;
	}

	public WebElement get9FCUpsellLogInUNPWSubmitLogInTextBox() {
		return txtbx_9FCUpsellLogInUNPWSubmitLogIn;
	}

	public WebElement get9FCUpsellContinueWithStandardFareButton() {
		return btn_9FCUpsellContinueWithStandardFare;
	}

	//*******************************************************************
	//*******************Overlay Container Passenger Page***************
	//*******************************************************************
	public List<WebElement> getOverlayErrorMessageText() {
		return txt_OverlayErrorMessage;
	}

	//*******************************************************************
	//***************Continue Button Passenger Page*********************
	//*******************************************************************
	public WebElement getContinueToBagsButton() {
		return btn_ContinueToBags;
	}

	//*******************************************************************
	//*******************Insufficient Age Pop Up*************************
	//*******************************************************************
	public WebElement getInsufficientAgePopUpHeaderText() {return txt_InsufficientAgePopUpHeader;}

	public WebElement getInsufficientAgePopUpCloseButton() {return btn_InsufficientAgePopUpClose;}

	public WebElement getInsufficientAgePopUpVerbageText(){
		return txt_InsufficientAgePopUpVerbage;
	}

	public WebElement getInsufficientAgePopUpFlightOnlyButton() {return btn_InsufficientAgePopUpFlightOnly;}

	//*******************************************************************
	//*******************Insufficient Age Pop Up*************************
	//*******************************************************************
	public WebElement getYoungTravelerPopUpHeaderText() {return txt_YoungTravelerPopUpHeader;}

	public WebElement getYoungTravelerPopUpCloseXButton() {return btn_YoungTravelerPopUpCloseX;}

	public WebElement getYoungTravelerPopUpReturnToHomePageButton() {return btn_YoungTravelerPopUpReturnToHomePage;}

	public WebElement getYoungTravelerPopUpCloseButton() {return btn_YoungTravelerPopUpClose;}


	//*******************************************************************
	//*****************Method Tool Tip of Passenger Page*****************
	//*******************************************************************

	public List<WebElement> getTravelWithCarSeatToolTipImage(){
		return img_TravelWithCarSeatToolTip;
	}

	public WebElement getTravelWithCarSeatToolTipWindowHeaderText(){
		return txt_TravelWithCarSeatToolTipWindowHeader;
	}

	public WebElement getTravelWithCarSeatToolTipWindowBodyText(){
		return txt_TravelWithCarSeatToolTipWindowBody;
	}

	public List<WebElement> getKnownTravelerNumberToolTipImage(){
		return img_KnownTravelerNumberToolTip;
	}

	public List<WebElement> getFSNumberToolTipImage(){
		return img_FSNumberToolTip;
	}

	public List<WebElement> getRedressNumberToolTipImage(){
		return img_RedressNumberToolTip;
	}

	public List<WebElement> getServiceAnimalToolTipImage(){
		return img_ServiceAnimalToolTip;
	}

	public List<WebElement> getPortableOxygenToolTipImage(){
		return img_PortableOxygenToolTip;
	}

	public List<WebElement> getEmotionalSupportAnimalToolTipImage(){
		return img_EmotionalSupportAnimalToolTip;
	}

	public List<WebElement> getOtherServicesToolTipImage(){
		return img_OtherServicesToolTip;
	}

	public List<WebElement> getVoluntaryProvisionTooltipImage(){
		return img_VoluntaryProvisionTooltip;
	}

	public WebElement getFSNumberToolTipWindowBodyText(){
		return txt_FSNumberToolTipWindowBody;
	}

	public List<WebElement> getWheelChairServicesToolTipImage(){
		return img_WheelChairServicesToolTip;
	}

	//*******************************************************************
	//*******************Changes Information Page************************
	//*******************************************************************

	public WebElement getCancelChangesButton(){
		return btn_CancelChanges;
	}
}