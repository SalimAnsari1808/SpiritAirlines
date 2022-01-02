package com.spirit.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class MemberEnrollmentPage {
	

/*
 * Prefix with WebElement      PostFix with Method
 		ifr     				-  IFrame
      	btn      				-  Button
       	chkbx    				-  CheckBox
       	chklst   				-  CheckBoxList
       	drpdwn   				-  DropDown
       	grdvew   				-  GridView
       	hyrlnk   				-  Hyperlink
       	img      				-  Image
       	imgbtn   				-  ImageButton
       	lbl      				-  Label
       	lnkbtn   				-  LinkButton
       	lnk     				-  Link
       	lstbx    				-  ListBox
       	lit      				-  Literal
       	pnl      				-  Panel
       	ph      				-  PlaceHolder
       	rdbtn    				-  RadioButton
       	rdbtnlst 				-  RadioButtonList
       	txtbx    				-  Textbox
       	txt      				-  Text
 */

	public MemberEnrollmentPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*******************************************************************
	//***********************Enrollment Links****************************
	//*******************************************************************
	public final String xpath_DiscountedFaresAndCheaperBagsPopOverButton = "//i[@popoverclass='fareclubCheapPopover']";
	@FindBy(xpath=xpath_DiscountedFaresAndCheaperBagsPopOverButton)
	private WebElement btn_DiscountedFaresAndCheaperBagsPopOver;

	public final String xpath_CoversEveryoneOnYourBookingPopOverButton = "//i[@popoverclass='fareclubPopover']";
	@FindBy(xpath=xpath_CoversEveryoneOnYourBookingPopOverButton)
	private WebElement btn_CoversEveryoneOnYourBookingPopOver;

	public final String xpath_logInPopupText = "//div[@class='modal-content']//h2[contains(text(),'Log In To Your Account')]";
	@FindBy(xpath=xpath_logInPopupText)
	private List<WebElement> txt_logInPopup;

	public final String xpath_TermsAndConditionsLink = "//a[contains(text(),'Terms and Conditions') or contains(text(),'Términos y Condiciones')]";
	@FindBy(xpath=xpath_TermsAndConditionsLink)
	private WebElement lnk_TermsAndConditions;

	public final String xpath_FAQLink = "//a[contains(text(),'F.A.Q.') or contains(text(),'Preguntas Frecuentes.')]";
	@FindBy(xpath=xpath_FAQLink)
	private WebElement lnk_FAQ;

	public final String xpath_SignUpFasterAndEasierLink = "//a[contains(text(),'Sign-up faster and easier') or contains(text(),'Inscríbase más rápido y fácilmente')]";
	@FindBy(xpath=xpath_SignUpFasterAndEasierLink)
	private WebElement lnk_SignUpFasterAndEasier;

	public final String xpath_FareClubPopOverBodyPlaceHolder = "//div[@class='popover-body']";
	@FindBy(xpath=xpath_FareClubPopOverBodyPlaceHolder)
	private WebElement ph_FareClubPopOverBody;

	public final String xpath_FareClubPopOverTopText = "(//div[@class='popover-body']//p)[1]";
	@FindBy(xpath=xpath_FareClubPopOverTopText)
	private WebElement txt_FareClubPopOverTop;

	public final String xpath_FareClubPopOverBottomText = "(//div[@class='popover-body']//p)[2]";
	@FindBy(xpath=xpath_FareClubPopOverBottomText)
	private WebElement txt_FareClubPopOverBottom;

	public final String xpath_SignUpNowButton = "//a[contains(text(),'SIGN-UP NOW!') or contains(text(),'¡INSCRíBASE AHORA!')]";
	@FindBy(xpath=xpath_SignUpNowButton)
	private WebElement btn_SignUpNow;

	public final String xpath_PopUpLogInToYourAccountCloseButton = "//div[@class='modal-content']//i";
	@FindBy(xpath=xpath_PopUpLogInToYourAccountCloseButton)
	private WebElement btn_PopUpLogInToYourAccountClose;
	//*******************************************************************
	//*******************Account Tab Enrollment Page*********************
	//*******************************************************************
	public final String xpath_TitleText = "//span[contains(text(),'Title') or contains(text(),'Título')]/..";
	@FindBy(xpath=xpath_TitleText)
	private WebElement txt_Title;

	public final String xpath_TitleDropDown = "//select[@id='title']";
	@FindBy(xpath=xpath_TitleDropDown)
	private WebElement drpdn_Title;

	public final String xpath_FirstNameText = "//span[contains(text(),'First Name') or contains(text(),'Primer Nombre')]/..";
	@FindBy(xpath=xpath_FirstNameText)
	private WebElement txt_FirstName;

	public final String xpath_FirstNameTextBox = "//input[@id='firstName']";
	@FindBy(xpath=xpath_FirstNameTextBox)
	private WebElement txtbx_FirstName;

	public final String xpath_MiddleNameText = "//span[contains(text(),'Middle Name') or contains(text(),'Segundo Nombre')]/..";
	@FindBy(xpath=xpath_MiddleNameText)
	private WebElement txt_MiddleName;

	public final String xpath_MiddleNameTextBox = "//input[@id='middleName']";
	@FindBy(xpath=xpath_MiddleNameTextBox)
	private WebElement txtbx_MiddleName;

	public final String xpath_LastNameText = "//span[contains(text(),'Last Name') or contains(text(),'Apellido(s)')]/..";
	@FindBy(xpath=xpath_LastNameText)
	private WebElement txt_LastName;

	public final String xpath_LastNameTextBox = "//input[@id='lastName']";
	@FindBy(xpath=xpath_LastNameTextBox)
	private WebElement txtbx_LastName;

	public final String xpath_SuffixText = "//span[contains(text(),'Suffix') or contains(text(),'Sufijo')]/..";
	@FindBy(xpath=xpath_SuffixText)
	private WebElement txt_Suffix;

	public final String xpath_SuffixDropDown = "//select[@id='suffix']";
	@FindBy(xpath=xpath_SuffixDropDown)
	private WebElement drpdn_Suffix;

	public final String xpath_DateOfBirthText = "//span[contains(text(),'Date of Birth') or contains(text(),'Fecha de Nacimiento')]/..";
	@FindBy(xpath=xpath_DateOfBirthText)
	private WebElement txt_DateOfBirth;

	public final String xpath_DateOfBirthTextBox = "//input[@id='dateOfBirth']";
	@FindBy(xpath=xpath_DateOfBirthTextBox)
	private WebElement txtbx_DateOfBirth;

	public final String xpath_ImportantText = "//p[contains(text(),'important') or contains(text(),'IMPORTANTE')]/..";
	@FindBy(xpath=xpath_ImportantText)
	private WebElement txt_Important;

	public final String xpath_EnterYourFirstText = "//p[contains(text(),'Enter your first') or contains(text(),'Ingrese su primer nombre')]/..";
	@FindBy(xpath=xpath_EnterYourFirstText)
	private WebElement txt_EnterYourFirst;

	public final String xpath_EmailText = "//span[contains(text(),'Email') or contains(text(),'Email')]/..";
	@FindBy(xpath=xpath_EmailText)
	private WebElement txt_Email;

	public final String xpath_EmailIdTextBox = "//input[@id='emailAddress']";
	@FindBy(xpath=xpath_EmailIdTextBox)
	private WebElement txtbx_EmailId;

	public final String xpath_ConfirmEmailText = "//span[contains(text(),'Confirm Email') or contains(text(),'Confirme su email')]/..";
	@FindBy(xpath=xpath_ConfirmEmailText)
	private WebElement txt_ConfirmEmail;

	public final String xpath_ConfirmEmailIdTextBox = "//input[@id='confirmEmail']";
	@FindBy(xpath=xpath_ConfirmEmailIdTextBox)
	private WebElement txtbx_ConfirmEmailId;

	public final String xpath_CreateAPasswordText = "//span[contains(text(),'Create a Password') or contains(text(),'Cree una contraseña')]/..";
	@FindBy(xpath=xpath_CreateAPasswordText)
	private WebElement txt_CreateAPassword;

	public final String xpath_PasswordTextBox = "//input[@id='password']";
	@FindBy(xpath=xpath_PasswordTextBox)
	private WebElement txtbx_Password;

	public final String xpath_ConfirmAPasswordText = "//span[contains(text(),'Confirm a Password') or contains(text(),'Confirme su Contraseña')]/..";
	@FindBy(xpath=xpath_ConfirmAPasswordText)
	private WebElement txt_ConfirmAPassword;

	public final String xpath_ConfirmPasswordTextBox = "//input[@id='confirmPassword']";
	@FindBy(xpath=xpath_ConfirmPasswordTextBox)
	private WebElement txtbx_ConfirmPassword;

	public final String xpath_ContinueToStep2Button = "//button[contains(text(),'continue to step 2') or contains(text(),'continuar al paso 2')]";
	@FindBy(xpath=xpath_ContinueToStep2Button)
	private WebElement btn_ContinueToStep2;

	//*******************************************************************
	//*******************Contact Tab Enrollment Page*********************
	//*******************************************************************
	public final String xpath_AddressText = "//span[contains(text(),'Address') or contains(text(),'Dirección')]/..";
	@FindBy(xpath=xpath_AddressText)
	private WebElement txt_Address;

	public final String xpath_Address1TextBox = "//input[@id='address1']";
	@FindBy(xpath=xpath_Address1TextBox)
	private WebElement txtbx_Address1;

	public final String xpath_CityText = "//span[contains(text(),'City') or contains(text(),'Ciudad')]/..";
	@FindBy(xpath=xpath_CityText)
	private WebElement txt_City;

	public final String xpath_CityTextBox = "//input[@id='city']";
	@FindBy(xpath=xpath_CityTextBox)
	private WebElement txtbx_City;

	public final String xpath_StateText = "//span[contains(text(),'State/Province') or contains(text(),'Estado/Provincia')]/..";
	@FindBy(xpath=xpath_StateText)
	private WebElement txt_State;

	public final String xpath_StateDropDown = "//select[@id='state']";
	@FindBy(xpath=xpath_StateDropDown)
	private WebElement drpdn_State;

	public final String xpath_ZipText = "//span[contains(text(),'Zip/Postal Code') or contains(text(),'Código postal')]/..";
	@FindBy(xpath=xpath_ZipText)
	private WebElement txt_Zip;

	public final String xpath_PostalCodeTextBox = "//input[@id='postalCode']";
	@FindBy(xpath=xpath_PostalCodeTextBox)
	private WebElement txtbx_PostalCode;

	public final String xpath_CountryText = "//span[contains(text(),'Country') or contains(text(),'País')]/..";
	@FindBy(xpath=xpath_CountryText)
	private WebElement txt_Country;

	public final String xpath_CountryDropDown = "//select[@id='country']";
	@FindBy(xpath=xpath_CountryDropDown)
	private WebElement drpdn_Country;

	public final String xpath_PrimaryPhoneText = "//span[contains(text(),'Primary Phone') or contains(text(),'Teléfono Primario')]/..";
	@FindBy(xpath=xpath_PrimaryPhoneText)
	private WebElement txt_PrimaryPhone;

	public final String xpath_HomePhoneTextBox = "//input[@id='homePhone' or @id='phonePrimaryNumber']";
	@FindBy(xpath=xpath_HomePhoneTextBox)
	private WebElement txtbx_HomePhone;

	public final String xpath_WorkPhoneText = "//span[contains(text(),'Secondary Phone') or contains(text(),'Teléfono Secundario')]/..";
	@FindBy(xpath=xpath_WorkPhoneText)
	private WebElement txt_WorkPhone;

	public final String xpath_WorkPhoneTextBox = "//input[@id='workPhone' or @id='phoneSecondaryNumber']";
	@FindBy(xpath=xpath_WorkPhoneTextBox)
	private WebElement txtbx_WorkPhone;

	public final String xpath_HomeAirportText = "//span[contains(text(),'Home Airport') or contains(text(),'Aeropuerto Primario')]/..";
	@FindBy(xpath=xpath_HomeAirportText)
	private WebElement txt_HomeAirport;

	public final String xpath_PrimaryAirportDropDown = "//select[@id='primaryAirport']";
	@FindBy(xpath=xpath_PrimaryAirportDropDown)
	private WebElement drpdn_PrimaryAirport;

	public final String xpath_FSTermsAndConditionText = "//span[contains(text(),'I have read and agree to the Free Spirit Terms & Conditions.') or contains(text(),'Términos y Condiciones; He leído y estoy de acuerdo con los Términos y Condiciones de FREE SPIRIT.*')]/..";
	@FindBy(xpath=xpath_FSTermsAndConditionText)
	private WebElement txt_FSTermsAndCondition;

	public final String xpath_FSignUpButton = "//button[contains(text(),'free sign up') or contains(text(),'Inscribirme Gratis')]";
	@FindBy(xpath=xpath_FSignUpButton)
	private WebElement btn_FSignUp;

	public final String xpath_ContinueToStep3Button = "//button[contains(text(),'continue to step 3') or contains(text(),'continuar al paso 3')]";
	@FindBy(xpath=xpath_ContinueToStep3Button)
	private WebElement btn_ContinueToStep3;

	//*******************************************************************
	//*******************Billing Tab Enrollment Page*********************
	//*******************************************************************
	public final String xpath_SelectCardDropDown = "//select[@id='cards']";
	@FindBy(xpath=xpath_SelectCardDropDown)
	private WebElement drpdwn_SelectCard;

//	public final String xpath_NameOnCardText = "//label[contains(text(),'Name on Card') or contains(text(),'Nombre en la tarjeta')]/..";
	public final String xpath_NameOnCardText = "//span[contains(text(),'Name on Card') or contains(text(),'Nombre en la tarjeta')]/..";

	@FindBy(xpath=xpath_NameOnCardText)
	private WebElement txt_NameOnCard;

	public final String xpath_AccountHolderNameTextBox = "//input[@id='accountHolderName']";
	@FindBy(xpath=xpath_AccountHolderNameTextBox)
	private WebElement txtbx_AccountHolderName;

	public final String xpath_CardNumberText = "//span[contains(text(),'Card Number') or contains(text(),'Número de la tarjeta')]/..";
	@FindBy(xpath=xpath_CardNumberText)
	private WebElement txt_CardNumber;

	public final String xpath_CardNumberTextBox = "//input[@id='cardNumber']";
	@FindBy(xpath=xpath_CardNumberTextBox)
	private WebElement txtbx_CardNumber;

	public final String xpath_CardTypeImage = "//div[contains(@class, 'card-container')]//img[not(contains(@class, 'disabled'))]";
	@FindBy(xpath = xpath_CardTypeImage)
	private WebElement img_CardType;

	public final String xpath_ExpirationDateText = "//span[contains(text(),'Expiration Date') or contains(text(),'Fecha de expiración')]/..";
	@FindBy(xpath=xpath_ExpirationDateText)
	private WebElement txt_ExpirationDate;

	public final String xpath_ExpMonthYearTextBox = "//input[@id='expMonthYear']";
	@FindBy(xpath=xpath_ExpMonthYearTextBox)
	private WebElement txtbx_ExpMonthYear;

	public final String xpath_SecurityCodeText = "//span[contains(text(),'Security Code') or contains(text(),'Clave de Seguridad')]/..";
	@FindBy(xpath=xpath_SecurityCodeText)
	private WebElement txt_SecurityCode;

	public final String xpath_SecurityCodeTextBox = "//input[@id='securityCode']";
	@FindBy(xpath=xpath_SecurityCodeTextBox)
	private WebElement txtbx_SecurityCode;

	public final String xpath_SecurityCodeToolTipIcon = "//input[@id='securityCode']/following::i[1]";
	@FindBy(xpath=xpath_SecurityCodeToolTipIcon)
	private WebElement icn_SecurityCodeToolTip;

	public final String xpath_BillingAddressText = "//p[contains(text(),'Billing Address') or contains(text(),'Direccion de Facturación')]/..";
	@FindBy(xpath=xpath_BillingAddressText)
	private WebElement txt_BillingAddress;

	public final String xpath_AddressForBillingText = "//span[contains(text(),'Address') or contains(text(),'Dirección')]/..";
	@FindBy(xpath=xpath_AddressForBillingText)
	private WebElement txt_AddressForBilling;

	public final String xpath_PleaseUseSameAddressText = "//span[contains(text(),'Please use') or contains(text(),'La Dirección')]/..";
	@FindBy(xpath=xpath_PleaseUseSameAddressText)
	private WebElement txt_PleaseUseSameAddress;

	public final String xpath_BillingAddressTextBox = "//input[@id='billingAddress']";
	@FindBy(xpath=xpath_BillingAddressTextBox)
	private WebElement txtbx_BillingAddress;

	public final String xpath_CityForBillingText = "//span[contains(text(),'City') or contains(text(),'Ciudad')]/..";
	@FindBy(xpath=xpath_CityForBillingText)
	private WebElement txt_CityForBilling;

	public final String xpath_BillingCityTextBox = "//input[@id='billingCity']";
	@FindBy(xpath=xpath_BillingCityTextBox)
	private WebElement txtbx_BillingCity;

	public final String xpath_StateForBillingText = "//span[contains(text(),'State') or contains(text(),'Estado')]/..";
	@FindBy(xpath=xpath_StateForBillingText)
	private WebElement txt_StateForBilling;

	public final String xpath_BillingStateDropDown = "//select[@id='billingState']";
	@FindBy(xpath=xpath_BillingStateDropDown)
	private WebElement drpdn_BillingState;

	public final String xpath_ZipCodeForBillingText = "//span[contains(text(),'Zip/Postal Code') or contains(text(),'Código Postal')]/..";
	@FindBy(xpath=xpath_ZipCodeForBillingText)
	private WebElement txt_ZipCodeForBilling;

	public final String xpath_BillingZipPostalTextBox = "//input[@id='billingZipPostal]";
	@FindBy(xpath=xpath_BillingZipPostalTextBox)
	private WebElement txtbx_BillingZipPostal;

	public final String xpath_CountryForBillingText = "//span[contains(text(),'Country') or contains(text(),'País')]/..";
	@FindBy(xpath=xpath_CountryForBillingText)
	private WebElement txt_CountryForBilling;

	public final String xpath_BillingCountryDropDown = "//select[@id='billingCountry]";
	@FindBy(xpath=xpath_BillingCountryDropDown)
	private WebElement drpdn_BillingCountry;

	public final String xpath_DFCTermsAndConditionHeaderText = "//p[contains(text(),'$9 Fare Club Terms and Conditions') or contains(text(),'Términos y Condiciones $9 Fare Club')]/..";
	@FindBy(xpath=xpath_DFCTermsAndConditionHeaderText)
	private WebElement txt_DFCTermsAndConditionHeader;

	public final String xpath_DFCTermsAndConditionText = "//span[contains(text(),'I have read and agree to the $9 Fare Club terms and conditions. I understand') or contains(text(),'He leído y estoy de acuerdo con los Términos y Condiciones del $9 Fare Club')]/..";
	@FindBy(xpath=xpath_DFCTermsAndConditionText)
	private WebElement txt_DFCTermsAndCondition;

	public final String xpath_DFCSignUpButton = "//button[contains(text(),'sign-up and start saving today!') or contains(text(),'¡Inscríbase y comience a ahorrar hoy!')]";
	@FindBy(xpath=xpath_DFCSignUpButton)
	private WebElement btn_DFCSignUp;

	//*******************************************************************
	//*********Successfully Registered PopUp Enrollment Page*************
	//*******************************************************************
	public final String xpath_PopUpHeaderText = "//div[@class='modal-header']/h2";
	@FindBy(xpath=xpath_PopUpHeaderText)
	private WebElement txt_PopUpHeader;

	public final String xpath_CloseImage = "//div[@class='modal-header']/button";
	@FindBy(xpath=xpath_CloseImage)
	private WebElement img_Close;

	public final String xpath_BookTripButton = "//div[@class='modal-body']//button[contains(@class,'btn-primary')]";
	@FindBy(xpath=xpath_BookTripButton)
	private WebElement btn_BookTrip;

	public final String xpath_GoToAccountLink = "//div[@class='modal-body']//button[contains(@class,'btn-secondary')]";
	@FindBy(xpath=xpath_GoToAccountLink)
	private WebElement lnk_GoToAccount;

	public final String xpath_SuccessfullyRegisteredText = "//div[@class='modal-body']";
	@FindBy(xpath=xpath_SuccessfullyRegisteredText)
	private WebElement txt_SuccessfullyRegistered;

	//*****************************************************************************************************************
	//*************************************Start of Methods of Enrollment Page*****************************************
	//*****************************************************************************************************************

	//*******************************************************************
	//***********************Enrollment Links****************************
	//*******************************************************************
	public WebElement getDiscountedFaresAndCheaperBagsPopOverButton() { return btn_DiscountedFaresAndCheaperBagsPopOver; }

	public WebElement getCoversEveryoneOnYourBookingPopOverButton() { return btn_CoversEveryoneOnYourBookingPopOver; }

	public WebElement getTermsAndConditionsLink() { return lnk_TermsAndConditions; }

	public WebElement getFAQLink() { return lnk_FAQ; }

	public WebElement getSignUpFasterAndEasierLink() { return lnk_SignUpFasterAndEasier; }

	public List<WebElement> getlogInPopupText() { return txt_logInPopup; }

	public WebElement getFareClubPopOverBodyPlaceholder() { return ph_FareClubPopOverBody; }

	public WebElement getFareClubPopOverTopText() { return txt_FareClubPopOverTop; }

	public WebElement getFareClubPopOverBottomText() { return txt_FareClubPopOverBottom; }

	public WebElement getSignUpNowButton() { return btn_SignUpNow; }

	public WebElement getPopUpLogInToYourAccountCloseButton() { return btn_PopUpLogInToYourAccountClose; }

	//*******************************************************************
	//*******************Account Tab Enrollment Page*********************
	//*******************************************************************
	public WebElement getUserTitleText() {
		return txt_Title;
	}

	public WebElement getUserTitleDropDown() {
		return drpdn_Title;
	}

	public WebElement getUserFirstNameText() {
		return txt_FirstName;
	}

	public WebElement getUserFirstNameTextBox() {
		return txtbx_FirstName;
	}

	public WebElement getUserMiddleNameText() {
		return txt_MiddleName;
	}

	public WebElement getUserMiddleNameTextBox() {
		return txtbx_MiddleName;
	}

	public WebElement getUserLastNameText() {
		return txt_LastName;
	}

	public WebElement getUserLastNameTextBox() {
		return txtbx_LastName;
	}

	public WebElement getUserSuffixText() {
		return txt_Suffix;
	}

	public WebElement getUserSuffixDropDown() {
		return drpdn_Suffix;
	}

	public WebElement getUserDateOfBirthText() {
		return txt_DateOfBirth;
	}

	public WebElement getUserDateOfBirthTextBox() {
		return txtbx_DateOfBirth;
	}

	public WebElement getImportantText() {
		return txt_Important;
	}

	public WebElement getEnterYourFirstText() {
		return txt_EnterYourFirst;
	}

	public WebElement getEmailText() {
		return txt_Email;
	}

	public WebElement getUserEmailIdTextBox() {
		return txtbx_EmailId;
	}

	public WebElement getConfirmEmailText() {
		return txt_ConfirmEmail;
	}

	public WebElement getUserConfirmEmailIdTextBox() {
		return txtbx_ConfirmEmailId;
	}

	public WebElement getCreateAPasswordText() {
		return txt_CreateAPassword;
	}

	public WebElement getUserCreateAPasswordTextBox() {
		return txtbx_Password;
	}

	public WebElement getConfirmAPasswordText() {
		return txt_ConfirmAPassword;
	}

	public WebElement getConfirmAPasswordTextBox() {
		return txtbx_ConfirmPassword;
	}

	public WebElement getContinueToStep2Button() {
		return btn_ContinueToStep2;
	}

	//*******************************************************************
	//*******************Contact Tab Enrollment Page*********************
	//*******************************************************************
	public WebElement getAddressText() {
		return txt_Address;
	}

	public WebElement getAddressTextBox() {
		return txtbx_Address1;
	}

	public WebElement getCityText() {
		return txt_City;
	}

	public WebElement getCityTextBox() {
		return txtbx_City;
	}

	public WebElement getStateText() {
		return txt_State;
	}

	public WebElement getStateDropDown() {
		return drpdn_State;
	}

	public WebElement getZipText() {
		return txt_Zip;
	}

	public WebElement getPostalCodeTextBox() {
		return txtbx_PostalCode;
	}

	public WebElement getCountryText() {
		return txt_Country;
	}

	public WebElement getCountryDropDown() {
		return drpdn_Country;
	}

	public WebElement getPrimaryPhoneText() {
		return txt_PrimaryPhone;
	}

	public WebElement getPrimaryPhoneTextBox() {
		return txtbx_HomePhone;
	}

	public WebElement getSecondaryPhoneText() {
		return txt_WorkPhone;
	}

	public WebElement getSecondaryPhoneTextBox() {
		return txtbx_WorkPhone;
	}

	public WebElement getHomeAirportText() {
		return txt_HomeAirport;
	}

	public WebElement getPrimaryAirportDropDown() {
		return drpdn_PrimaryAirport;
	}

	public WebElement getFSTermsAndConditionText() {
		return txt_FSTermsAndCondition;
	}

	public WebElement getFSSignUpButton() {
		return btn_FSignUp;
	}

	public WebElement getContinueToStep3Button() {
		return btn_ContinueToStep3;
	}
	//*******************************************************************
	//*******************Billing Tab Enrollment Page*********************
	//*******************************************************************
	public WebElement getnameOnCardText() {
		return txt_NameOnCard;
	}

	public WebElement getAccountHolderNameTextBox() {
		return txtbx_AccountHolderName;
	}

	public WebElement getSelectCardDropDown() {
		return drpdwn_SelectCard;
	}

	public WebElement getCardNumberText() {
		return txt_CardNumber;
	}

	public WebElement getCardNumberTextBox() {
		return txtbx_CardNumber;
	}

	public WebElement getCardTypeImage(){
		return img_CardType;
	}

	public WebElement getExpirationDateText() {
		return txt_ExpirationDate;
	}

	public WebElement getExpirationMonthYearTextBox() {
		return txtbx_ExpMonthYear;
	}

	public WebElement getSecurityCodeText() {
		return txt_SecurityCode;
	}

	public WebElement getSecurityCodeTextBox() {
		return txtbx_SecurityCode;
	}

	public WebElement getSecurityCodeToolTipIcon() { return icn_SecurityCodeToolTip; }

	public WebElement getBillingAddressText() {
		return txt_BillingAddress;
	}

	public WebElement getAddressForBillingText() {
		return txt_AddressForBilling;
	}

	public WebElement getPleaseUseSameAddressText() {
		return txt_PleaseUseSameAddress;
	}

	public WebElement getAddressForBillingTextBox() {
		return txtbx_BillingAddress;
	}

	public WebElement getBillingCityText() {
		return txt_CityForBilling;
	}

	public WebElement getBillingCityTextBox() {
		return txtbx_BillingCity;
	}

	public WebElement getBillingStateText() {
		return txt_StateForBilling;
	}

	public WebElement getBillingStateDropDown() {
		return drpdn_BillingState;
	}

	public WebElement getBillingZipCodeText() {
		return txt_ZipCodeForBilling;
	}

	public WebElement getBillingZipTextBox() {
		return txtbx_BillingZipPostal;
	}

	public WebElement getBillingCountryText() {
		return txt_CountryForBilling;
	}

	public WebElement getBillingCountryDropDown() {
		return drpdn_BillingCountry;
	}

	public WebElement getDFCTermsAndConditionText() {
		return txt_DFCTermsAndConditionHeader;
	}

	public WebElement getAgreeTermsAndConditionText() {
		return txt_DFCTermsAndCondition;
	}

	public WebElement getDFCSignUpButton() {
		return btn_DFCSignUp;
	}

	//*******************************************************************
	//*********Successfully Registered PopUp Enrollment Page*************
	//*******************************************************************
	public WebElement getPopUpHeaderText() {
		return txt_PopUpHeader;
	}

	public WebElement getCloseImage() {
		return img_Close;
	}

	public WebElement getBookTripButton() {
		return btn_BookTrip;
	}

	public WebElement getGoToAccountLink() {
		return lnk_GoToAccount;
	}

	public WebElement getSuccessfullyRegisteredText() {
		return txt_SuccessfullyRegistered;
	}

}