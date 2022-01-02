package com.spirit.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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
   	rdbtnlst 				-  RadioButtonListF
   	txtbx    				-  Textbox
   	txt      				-  Text
 */

public class AccountProfilePage {

	public AccountProfilePage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*************************************************************
	//*************************Header******************************
	//*************************************************************
	public final String xpath_WelcomeHeaderText = "//h2[contains(text(), 'Welcome') or contains(text(), 'Bienvenido')]";
	@FindBy(xpath = xpath_WelcomeHeaderText)
	private WebElement txt_WelcomeHeader;

	public final String xpath_YourCurrentMilesText = "//strong[contains(text(), 'Your Current Miles') or contains(text(),'Sus millas actuales')]";
	@FindBy(xpath = xpath_YourCurrentMilesText)
	private  WebElement txt_YourCurrentMiles;

	public final String xpath_MileageEarningTierText = "//p[contains(text(), 'Mileage Earning Tier')or contains(text(), 'Nivel de Millas Acumuladas')]//following::p[1]";
	@FindBy(xpath = xpath_MileageEarningTierText)
	private	WebElement txt_MileageEarningTier;

	//*************************************************************
	//**********************Left Menu******************************
	//*************************************************************

	public final String xpath_LeftMenuMyAccountLink = "(//a[contains(text(),'My Account') or contains(text(),'Mi Cuenta')])[2]";
	@FindBy(xpath= xpath_LeftMenuMyAccountLink)
	private WebElement lnk_LeftMenuMyAccount;

	public final String xpath_LeftMenuPersonalInformationLink = "//app-account-page//a[contains(text(),'Personal Information') or contains(text(),'Información Personal')]";
	@FindBy(xpath=xpath_LeftMenuPersonalInformationLink)
	private WebElement lnk_LeftMenuPersonalInformation;

	public final String xpath_LeftMenuBillingInformationLink = "//app-account-page//a[contains(text(),'Billing Information') or contains(text(),'Información de Facturación')]";
	@FindBy(xpath= xpath_LeftMenuBillingInformationLink)
	private WebElement lnk_LeftMenuBillingInformation;

	public final String xpath_LeftMenuEmailSubscriptionsLink = "//app-account-page//a[contains(text(),'Email Subscriptions') or contains(text(),'Suscripciones de Email')]";
	@FindBy(xpath= xpath_LeftMenuEmailSubscriptionsLink)
	private WebElement lnk_LeftMenuEmailSubscriptions;

	public final String xpath_LeftMenuStatementsLink = "//app-account-page//a[contains(text(),'Statements') or contains(text(),'Estados')]";
	@FindBy(xpath=xpath_LeftMenuStatementsLink)
	private WebElement lnk_LeftMenuStatements;

	public final String xpath_LeftMenuRedeemMilesLink = "//app-account-page//a[contains(text(),'Redeem Miles') or contains(text(),'Canjear Millas')]";
	@FindBy(xpath= xpath_LeftMenuRedeemMilesLink)
	private WebElement lnk_LeftMenuRedeemMiles;

	public final String xpath_LeftMenuBuyGiftMilesLink = "//app-account-page//a[contains(text(),'Buy or Gift Miles') or contains(text(),'Comprar o Regalar Millas')]";
	@FindBy(xpath= xpath_LeftMenuBuyGiftMilesLink)
	private WebElement lnk_LeftMenuBuyGiftMiles;

	public final String xpath_LeftMenuRequestMileageCreditLink = "//app-account-page//a[contains(text(),'Request Mileage Credit') or contains(text(),'Solicitar Acreditación de Millas')]";
	@FindBy(xpath= xpath_LeftMenuRequestMileageCreditLink)
	private WebElement lnk_LeftMenuRequestMileageCredit;

	//*************************************************************
	//****************Your Membership******************************
	//*************************************************************
	public final String xpath_FreeSpiritNumberText = "//p[contains(text(),'Free Spirit Account Number') or contains(text(),'Número de cuenta Free Spirit')]//following::p[1]";
	@FindBy(xpath=xpath_FreeSpiritNumberText)
	private WebElement txt_FreeSpiritNumber;

	public final String xpath_MemberNameText = "//p[contains(text(), 'Member Name') or contains(text(), 'Nombre')]/following-sibling::p";
	@FindBy(xpath=xpath_MemberNameText)
	private WebElement txt_MemberName;

	public final String xpath_DateJoinedText = "//p[contains(text(), 'Date Joined') or contains(text(), 'Fecha de Inscripción')]/following-sibling::p";
	@FindBy(xpath=xpath_DateJoinedText)
	private WebElement txt_DateJoined;

	public final String xpath_YourMembershipHeaderText = "//h3[contains(text(), 'Your Membership') or contains(text(), 'Su Membresía')]";
	@FindBy(xpath = xpath_YourMembershipHeaderText)
	private WebElement txt_YourMembershipHeader;

	public final String xpath_YourMembershipTypeText = "//p[contains(text(), 'Paid Membership Type') or contains(text(), 'Tipo de Membresía Paga')]/following-sibling::p";
	@FindBy(xpath=xpath_YourMembershipTypeText)
	private WebElement txt_YourMembershipType;

	public final String xpath_DaysLeftInMembershipText = "//p[contains(text(), 'Days Left in Membership') or contains(text(), 'Días Restantes en Membresía')]/following-sibling::p";
	@FindBy(xpath=xpath_DaysLeftInMembershipText)
	private WebElement txt_DaysLeftInMembership;

	public final String xpath_CancelMembershipDropDownLink = "//button[contains(text(),'Cancel Membership ') or contains(text(),'Cancelar la membresía')]";
	@FindBy(xpath=xpath_CancelMembershipDropDownLink)
	private WebElement lnk_CancelMembershipDropDown;

	public final String name_MembershipCancellationReasonDropDown = "cancelReason";
	@FindBy(name=name_MembershipCancellationReasonDropDown)
	private WebElement drpdwn_MembershipCancellationReason;

	public final String xpath_MembershipCancellationButton = "//form//button[contains(text(),'Cancel Membership') or contains(text(),'Cancelar la membresía')]";
	@FindBy(xpath=xpath_MembershipCancellationButton)
	private WebElement btn_MembershipCancellation;

	public final String xpath_NotAMemberYetText = "//app-account-profile//div[contains(text(), \"Not a member yet of Spirit's $9 Fare Club?\") or contains(text(), \"¿No se ha inscrito en el $9 Fare Club de Spirit?\")]";
	@FindBy(xpath = xpath_NotAMemberYetText)
	private WebElement txt_NotAMemberYet;

	public final String xpath_YourReservationsHeaderText = "//h3[contains(text(),'Your Reservations') or contains(text(),'Sus Reservaciones')]";
	@FindBy(xpath=xpath_YourReservationsHeaderText)
	private WebElement txt_YourReservationsHeader;

	public final String xpath_LocateYourReservationText = "//p[(contains(text(), 'Enter your six character')) or contains(text(), 'Entre su Clave de Confirmación')]";
	@FindBy(xpath = xpath_LocateYourReservationText)
	private WebElement txt_LocateYourReservation;

	public final String name_ConfimationCodeSearchTextBox = "recordLocator";
	@FindBy(name=name_ConfimationCodeSearchTextBox)
	private WebElement txtbx_ConfimationCodeSearch;

	public final String xpath_ConfimationCodeGoButton = "//button[contains(text(),'Go') or contains(text(),'Buscar')]";
	@FindBy(xpath=xpath_ConfimationCodeGoButton)
	private WebElement btn_ConfimationCodeGo;

	public final String xpath_CurrentReservationsHeaderText = "//h3[contains(text(),'Current Reservations') or contains(text(),'Reservaciones Actuales')]";
	@FindBy(xpath=xpath_CurrentReservationsHeaderText)
	private WebElement txt_CurrentReservationsHeader;

	public final String xpath_CurrentReservationsDateLabel = "(//table)[1]//thead//th[1]";
	@FindBy(xpath = xpath_CurrentReservationsDateLabel)
	private WebElement lbl_CurrentReservationsDate;

	public final String xpath_CurrentReservationsOriginLabel = "(//table)[1]//thead//th[2]";
	@FindBy(xpath = xpath_CurrentReservationsOriginLabel)
	private WebElement lbl_CurrentReservationsOrigin;

	public final String xpath_CurrentReservationsDestinationLabel = "(//table)[1]//thead//th[3]";
	@FindBy(xpath = xpath_CurrentReservationsDestinationLabel)
	private WebElement lbl_CurrentReservationsDestination;

	public final String xpath_CurrentReservationsConfirmationCodeLabel = "(//table)[1]//thead//th[4]";
	@FindBy(xpath = xpath_CurrentReservationsConfirmationCodeLabel)
	private WebElement lbl_CurrentReservationsConfirmationCode;

	public final String xpath_CurrentReservationsMilesLabel = "(//table)[1]//thead//th[5]";
	@FindBy(xpath = xpath_CurrentReservationsMilesLabel)
	private WebElement lbl_CurrentReservationsMiles;

	public final String xpath_CurrentReservationsDateText = "(//table)[1]//td[1]";
	@FindBy(xpath=xpath_CurrentReservationsDateText)
	private List<WebElement> txt_CurrentReservationsDate;

	public final String xpath_CurrentReservationsOriginText = "(//table)[1]//td[2]";
	@FindBy(xpath=xpath_CurrentReservationsOriginText)
	private List<WebElement> txt_CurrentReservationsOrigin;

	public final String xpath_CurrentReservationsDestinationText = "(//table)[1]//td[3]";
	@FindBy(xpath=xpath_CurrentReservationsDestinationText)
	private List<WebElement> txt_CurrentReservationsDestination;

	public final String xpath_CurrentReservationsConfirmationCodeText = "(//table)[1]//td[4]";
	@FindBy(xpath=xpath_CurrentReservationsConfirmationCodeText)
	private List<WebElement> txt_CurrentReservationsConfirmationCode;

	public final String xpath_CurrentReservationsMilesText = "(//table)[1]//td[5]";
	@FindBy(xpath=xpath_CurrentReservationsMilesText)
	private List<WebElement> txt_CurrentReservationsMiles;

	public final String xpath_CurrentReservationsClickHereButton = "(//table)[1]//td[6]//a";
	@FindBy(xpath=xpath_CurrentReservationsClickHereButton)
	private List<WebElement> btn_CurrentReservationsClickHere;

	public final String xpath_PastReservationsHeaderText = "//h3[contains(text(),'Past Reservations') or contains(text(),'Reservaciones Pasadas')]";
	@FindBy(xpath=xpath_PastReservationsHeaderText)
	private WebElement txt_PastReservationsHeader;

	public final String xpath_PastReservationsDateLabel = "(//table)[2]//thead//th[1]";
	@FindBy(xpath = xpath_PastReservationsDateLabel)
	private WebElement lbl_PastReservationsDate;

	public final String xpath_PastReservationsOriginLabel = "(//table)[2]//thead//th[2]";
	@FindBy(xpath = xpath_PastReservationsOriginLabel)
	private WebElement lbl_PastReservationsOrigin;

	public final String xpath_PastReservationsDestinationLabel = "(//table)[2]//thead//th[3]";
	@FindBy(xpath = xpath_PastReservationsDestinationLabel)
	private WebElement lbl_PastReservationsDestination;

	public final String xpath_PastReservationConfirmationCodeLabel = "(//table)[2]//thead//th[4]";
	@FindBy(xpath = xpath_PastReservationConfirmationCodeLabel)
	private WebElement lbl_PastReservationConfirmationCode;

	public final String xpath_PastReservationCMilesLabel = "(//table)[2]//thead//th[5]";
	@FindBy(xpath = xpath_PastReservationCMilesLabel)
	private WebElement lbl_PastReservationCMiles;

	public final String xpath_PastReservationsDateText = "(//table)[2]//td[1]";
	@FindBy(xpath=xpath_PastReservationsDateText)
	private List<WebElement> txt_PastReservationsDate;

	public final String xpath_PastReservationsOriginText = "(//table)[2]//td[2]";
	@FindBy(xpath=xpath_PastReservationsOriginText)
	private List<WebElement> txt_PastReservationsOrigin;

	public final String xpath_PastReservationsDestinationText = "(//table)[2]//td[3]";
	@FindBy(xpath=xpath_PastReservationsDestinationText)
	private List<WebElement> txt_PastReservationsDestination;

	public final String xpath_PastReservationsConfirmationCodeText = "(//table)[2]//td[4]";
	@FindBy(xpath=xpath_PastReservationsConfirmationCodeText)
	private List<WebElement> txt_PastReservationsConfirmationCode;

	public final String xpath_PastReservationsMilesText = "(//table)[2]//td[5]";
	@FindBy(xpath=xpath_PastReservationsMilesText)
	private List<WebElement> txt_PastReservationsMiles;

	public final String xpath_PastReservationsClickHereButton = "(//table)[2]//td[6]//a";
	@FindBy(xpath=xpath_PastReservationsClickHereButton)
	private List<WebElement> btn_PastReservationsClickHere;

	//**************************************************************************************************
	//***************************************Billing Information Page***********************************
	//**************************************************************************************************
	public final String xpath_BillingInformationNameOnCardText = "(//label[contains(text(), 'Name on Card') or contains(text(),'Nombre en la tarjeta')]//following::p)[1]";
	@FindBy(xpath = xpath_BillingInformationNameOnCardText)
	private WebElement txt_BillingInformationNameOnCard;

	public final String xpath_BillingInformationCardNumberText = "//label[contains(text(), 'Card Number') or contains(text(),'Número de la tarjeta')]//following-sibling::span";
	@FindBy(xpath = xpath_BillingInformationCardNumberText)
	private WebElement txt_BillingInformationCardNumber;

	public final String xpath_BillingInformationExpiryMonthYearTextBox = "//input[@id='expMonthYear']";
	@FindBy(xpath = xpath_BillingInformationExpiryMonthYearTextBox)
	private WebElement txtbx_BillingInformationExpiryMonthYear;

	public final String xpath_BillingInformationStateDropDown = "//select[@id = 'billingState']";
	@FindBy(xpath = xpath_BillingInformationStateDropDown)
	private WebElement drpdwn_BillingInformationState;

	public final String xpath_BillingInformationCountryDropDown = "//select[@id='billingCountry']";
	@FindBy(xpath = xpath_BillingInformationCountryDropDown)
	private WebElement drpdwn_BillingInformationCountry;


	//*************************************************************
	//*******************Primary Card******************************
	//*************************************************************
	public final String xpath_BillingInformationPrimaryCardHolderText = "(//h4[contains(text(),'Primary Card') or contains(text(),'Tarjeta primaria')]/../../following-sibling::div//tbody/tr//td)[1]";
	@FindBy(xpath=xpath_BillingInformationPrimaryCardHolderText)
	private WebElement txt_BillingInformationPrimaryCardHolder;

	public final String xpath_BillingInformationPrimaryCardTypeText = "(//h4[contains(text(),'Primary Card') or contains(text(),'Tarjeta primaria')]/../../following-sibling::div//tbody/tr//td)[2]";
	@FindBy(xpath=xpath_BillingInformationPrimaryCardTypeText)
	private WebElement txt_BillingInformationPrimaryCardType;

	public final String xpath_BillingInformationPrimaryCardNumberText = "(//h4[contains(text(),'Primary Card') or contains(text(),'Tarjeta primaria')]/../../following-sibling::div//tbody/tr//td)[3]";
	@FindBy(xpath=xpath_BillingInformationPrimaryCardNumberText)
	private WebElement txt_BillingInformationPrimaryCardNumber;

	public final String xpath_BillingInformationPrimaryCardExpirationDateText = "(//h4[contains(text(),'Primary Card') or contains(text(),'Tarjeta primaria')]/../../following-sibling::div//tbody/tr//td)[4]";
	@FindBy(xpath=xpath_BillingInformationPrimaryCardExpirationDateText)
	private WebElement txt_BillingInformationPrimaryCardExpirationDate;

	public final String xpath_BillingInformationPrimaryCardBillingAddressText = "(//h4[contains(text(),'Primary Card') or contains(text(),'Tarjeta primaria')]/../../following-sibling::div//tbody/tr//td)[5]";
	@FindBy(xpath= xpath_BillingInformationPrimaryCardBillingAddressText)
	private WebElement txt_BillingInformationPrimaryCardBillingAddress;

	public final String xpath_BillingInformationPrimaryCardEditLink = "(//h4[contains(text(),'Primary Card') or contains(text(),'Tarjeta primaria')]/../../following-sibling::div//tbody/tr//td)[6]/a[1]";
	@FindBy(xpath=xpath_BillingInformationPrimaryCardEditLink)
	private WebElement lnk_BillingInformationPrimaryCardEdit;

	public final String xpath_BillingInformationPrimaryCardDeleteLink = "(//h4[contains(text(),'Primary Card') or contains(text(),'Tarjeta primaria')]/../../following-sibling::div//tbody/tr//td)[6]/a[2]";
	@FindBy(xpath=xpath_BillingInformationPrimaryCardDeleteLink)
	private WebElement lnk_BillingInformationPrimaryCardDelete;

	//*************************************************************
	//****************Additional Card******************************
	//*************************************************************
	public final String xpath_BillingInformationAdditionalCardHolderText = "(//h4[contains(text(),'Additional Cards') or contains(text(),'Tarjetas Adicionales')]/../../following-sibling::div[2]//tbody/tr//td)[1]";
	@FindBy(xpath=xpath_BillingInformationAdditionalCardHolderText)
	private WebElement txt_BillingInformationAdditionalCardHolder;

	public final String xpath_BillingInformationAdditionalCardTypeText = "(//h4[contains(text(),'Additional Cards') or contains(text(),'Tarjetas Adicionales')]/../../following-sibling::div[2]//tbody/tr//td)[2]";
	@FindBy(xpath=xpath_BillingInformationAdditionalCardTypeText)
	private WebElement txt_BillingInformationAdditionalCardType;

	public final String xpath_BillingInformationAdditionalCardNumberText = "//h4[contains(text(),'Additional Cards') or contains(text(),'Tarjetas Adicionales')]/../../following-sibling::div[2]//tbody/tr//td[3]";
	@FindBy(xpath=xpath_BillingInformationAdditionalCardNumberText)
	private List<WebElement> txt_BillingInformationAdditionalCardNumber;

	public final String xpath_BillingInformationAdditionalCardExpirationDateText = "(//h4[contains(text(),'Additional Cards') or contains(text(),'Tarjetas Adicionales')]/../../following-sibling::div[2]//tbody/tr//td)[4]";
	@FindBy(xpath=xpath_BillingInformationAdditionalCardExpirationDateText)
	private WebElement txt_BillingInformationAdditionalCardExpirationDate;

	public final String xpath_BillingInformationAdditionalCardBillingAddressText = "(//h4[contains(text(),'Additional Cards') or contains(text(),'Tarjetas Adicionales')]/../../following-sibling::div[2]//tbody/tr//td)[5]";
	@FindBy(xpath=xpath_BillingInformationAdditionalCardBillingAddressText)
	private WebElement txt_BillingInformationAdditionalCardBillingAddress;

	public final String xpath_BillingInformationAdditionalCardEditLink = "(//h4[contains(text(),'Additional Cards') or contains(text(),'Tarjetas Adicionales')]/../../following-sibling::div[2]//tbody/tr//td)[6]/a[1]";
	@FindBy(xpath=xpath_BillingInformationAdditionalCardEditLink)
	private WebElement lnk_BillingInformationAdditionalCardEdit;

	public final String xpath_BillingInformationAdditionalCardDeleteLink = "(//h4[contains(text(),'Additional Cards') or contains(text(),'Tarjetas Adicionales')]/../../following-sibling::div[2]//tbody/tr//td)/a[2]";
	@FindBy(xpath=xpath_BillingInformationAdditionalCardDeleteLink)
	private List<WebElement> lnk_BillingInformationAdditionalCardDelete;

	public final String xpath_BillingInformationAddAnotherCardButton = "//button[@name='addButton']";
	@FindBy(xpath=xpath_BillingInformationAddAnotherCardButton)
	private WebElement btn_BillingInformationAddAnotherCard;

	//*************************************************************
	//*******************Add Card**********************************
	//*************************************************************

	public final String xpath_BillingInformationAddCardAccountHolderNameTextBox = "//input[@id='accountHolderName']";
	@FindBy(xpath=xpath_BillingInformationAddCardAccountHolderNameTextBox)
	private WebElement txtbx_BillingInformationAddCardAccountHolderName;

	public final String xpath_BillingInformationAddCardCardNumberTextBox = "//input[@id='cardNumber']";
	@FindBy(xpath=xpath_BillingInformationAddCardCardNumberTextBox)
	private WebElement txtbx_BillingInformationAddCardCardNumber;

	public final String xpath_BillingInformationAddCardExpMonthYearTextBox = "//input[@id='expMonthYear']";
	@FindBy(xpath=xpath_BillingInformationAddCardExpMonthYearTextBox)
	private WebElement txtbx_BillingInformationAddCardExpMonthYear;

	public final String xpath_BillingInformationAddCardMakeThisPrimaryCardLabel = "//label[@for='defaultCard']";
	@FindBy(xpath=xpath_BillingInformationAddCardMakeThisPrimaryCardLabel)
	private WebElement lbl_BillingInformationAddCardMakeThisPrimaryCard;

	public final String xpath_BillingInformationAddCardMakeThisPrimaryCardCheckBox = "//input[@id='defaultCard']";
	@FindBy(xpath=xpath_BillingInformationAddCardMakeThisPrimaryCardCheckBox)
	private WebElement chkbx_BillingInformationAddCardMakeThisPrimaryCard;

	//*************************************************************
	//*******************Billing Address***************************
	//*************************************************************

	public final String xpath_BillingInformationUseSameAddressLabel = "//label[@for='useSameAddress']";
	@FindBy(xpath=xpath_BillingInformationUseSameAddressLabel)
	private WebElement lbl_BillingInformationUseSameAddress;

	public final String xpath_BillingInformationUseSameAddressCheckBox = "//input[@id='useSameAddress']";
	@FindBy(xpath=xpath_BillingInformationUseSameAddressCheckBox)
	private WebElement chkbx_BillingInformationUseSameAddress;

	public final String xpath_BillingInformationBillingAddressTextBox = "//input[@id='billingAddress']";
	@FindBy(xpath=xpath_BillingInformationBillingAddressTextBox)
	private WebElement txtbx_BillingInformationBillingAddress;

	public final String xpath_BillingInformationBillingCityTextBox = "//input[@id='billingCity']";
	@FindBy(xpath=xpath_BillingInformationBillingCityTextBox)
	private WebElement txtbx_BillingInformationBillingCity;

	public final String xpath_BillingInformationBillingStateDropDown = "//input[@id='billingState']";
	@FindBy(xpath=xpath_BillingInformationBillingStateDropDown)
	private WebElement drpdn_BillingInformationBillingState;

	public final String xpath_BillingInformationBillingZipPostalTextBox = "//input[@id='billingZipPostal']";
	@FindBy(xpath=xpath_BillingInformationBillingZipPostalTextBox)
	private WebElement txtbx_BillingInformationBillingZipPostal;

	public final String xpath_BillingInformationBillingCountryDropDown = "//input[@id='billingCountry']";
	@FindBy(xpath=xpath_BillingInformationBillingCountryDropDown)
	private WebElement drpdn_BillingInformationBillingCountry;

	public final String xpath_BillingInformationSaveButtonButton = "//button[@name='saveButton']";
	@FindBy(xpath=xpath_BillingInformationSaveButtonButton)
	private WebElement btn_BillingInformationSaveButton;

	public final String xpath_BillingInformationCancelButton = "//a[@name='cancelButton']";
	@FindBy(xpath=xpath_BillingInformationCancelButton)
	private WebElement btn_BillingInformationCancelButton;

	//*************************************************************
	//*******************Delete Card Popup*************************
	//*************************************************************
	public final String xpath_DeleteCardPopupHeaderText = "//div[@class='modal-header']/h2";
	@FindBy(xpath=xpath_DeleteCardPopupHeaderText)
	private WebElement txt_DeleteCardPopupHeader;

	public final String xpath_DeleteCardPopupCloseLink = "//div[@class='modal-header']//i";
	@FindBy(xpath=xpath_DeleteCardPopupCloseLink)
	private WebElement lnk_DeleteCardPopupClose;

	public final String xpath_DeleteCardPopupSubHeaderText = "//div[@class='modal-body']/div[1]/div";
	@FindBy(xpath=xpath_DeleteCardPopupSubHeaderText)
	private WebElement txt_DeleteCardPopupSubHeader;

	public final String xpath_DeleteCardPopupDeleteCardButton = "//div[@class='modal-body']//button";
	@FindBy(xpath=xpath_DeleteCardPopupDeleteCardButton)
	private WebElement btn_DeleteCardPopupDeleteCard;

	//**************************************************************************************************
	//********************************************Redeem Miles Page*************************************
	//**************************************************************************************************
	public final String xpath_RedeemMilesHeaderText = "//h2[@class='headline2']";
	@FindBy(xpath=xpath_RedeemMilesHeaderText)
	private WebElement txt_RedeemMilesHeader;

	public final String xpath_RedeemMilesYourCurrentMilesText = "//h2[@class='headline2']/../p";
	@FindBy(xpath=xpath_RedeemMilesYourCurrentMilesText)
	private WebElement txt_RedeemMilesYourCurrentMiles;

	public final String xpath_RedeemMilesYourCurrentMilesLink = "//h2[@class='headline2']/../p/a";
	@FindBy(xpath=xpath_RedeemMilesYourCurrentMilesLink)
	private WebElement lnk_RedeemMilesYourCurrentMiles;

	public final String xpath_RedeemMilesSubHeadersText = "//h2[@class='headline2']/../div//p";
	@FindBy(xpath=xpath_RedeemMilesSubHeadersText)
	private List<WebElement> txt_RedeemMilesSubHeaders;

	//**************************************************************************************************
	//********************************************Email Deal Page***************************************
	//**************************************************************************************************

	public final String xpath_EmailDealEmailAddressTextBox = "//input[@id='initialEmailAddress']";
	@FindBy(xpath=xpath_EmailDealEmailAddressTextBox)
	private WebElement txtbx_EmailDealEmailAddress;

	public final String xpath_EmailDealNewEmailAddressTextBox = "//input[@id='newEmailAddress']";
	@FindBy(xpath= xpath_EmailDealNewEmailAddressTextBox)
	private WebElement txtbx_EmailDealNewEmailAddress;

	public final String xpath_EmailDealConfirmEmailAddressTextBox = "//input[@id='validateNewEmailAddress']";
	@FindBy(xpath=xpath_EmailDealConfirmEmailAddressTextBox)
	private WebElement txtbx_EmailDealConfirmEmailAddress;

	public final String xpath_EmailDealFirstNameTextBox = "//input[@id='firstName']";
	@FindBy(xpath=xpath_EmailDealFirstNameTextBox)
	private WebElement txtbx_EmailDealFirstName;

	public final String xpath_EmailDealLastNameTextBox = "//input[@id='lastName']";
	@FindBy(xpath=xpath_EmailDealLastNameTextBox)
	private WebElement txtbx_EmailDealLastName;

	public final String xpath_EmailDealPrimaryAirportDropDown = "//select[@id='primaryAirport']";
	@FindBy(xpath=xpath_EmailDealPrimaryAirportDropDown)
	private WebElement drpdwn_EmailDealPrimaryAirport;

	public final String xpath_EmailDealSecondaryAirportDropDown = "//select[@id='secondaryAirport']";
	@FindBy(xpath=xpath_EmailDealSecondaryAirportDropDown)
	private WebElement drpdwn_EmailDealSecondaryAirport;

	public final String xpath_EmailDealUpdateAccountOrUnsubscribeText = "//p[contains(text(),'Update your account or unsubscribe from our email deals.') or contains(text(),'Actualizar su cuenta o cancelar su suscripción de nuestra ofertas por email.')]";
	@FindBy(xpath= xpath_EmailDealUpdateAccountOrUnsubscribeText)
	private WebElement txt_EmailDealUpdateAccountOrUnsubscribe;

	public final String xpath_EmailDealUnsubscribeCheckBox = "//label[@for='unsubscribe']";
	@FindBy(xpath= xpath_EmailDealUnsubscribeCheckBox)
	private WebElement chkbx_EmailDealUnsubscribe;

	public final String xpath_EmailDealSubscribeButton = "//button[contains(text(),'subscribe') or contains(text(),'Suscribirme')]";
	@FindBy(xpath=xpath_EmailDealSubscribeButton)
	private WebElement btn_EmailDealSubscribe;

	public final String xpath_EmailDealContinueButton = "//button[contains(text(),'Continue') or contains(text(),'Continuar')]";
	@FindBy(xpath=xpath_EmailDealContinueButton)
	private WebElement btn_EmailDealContinue;

	public final String xpath_EmailDealSubmitChangesButton = "//button[contains(text(),'Submit') or contains(text(),'Enviar')]";
	@FindBy(xpath=xpath_EmailDealSubmitChangesButton)
	private WebElement btn_EmailDealSubmitChanges;

	public final String xpath_EmailDealPopupHeaderText = "//h2[@class='modal-title']";
	@FindBy(xpath=xpath_EmailDealPopupHeaderText)
	private WebElement txt_EmailDealPopupHeader;

	public final String xpath_EmailDealPopupSubHeaderText = "//div[@class='modal-body']//strong";
	@FindBy(xpath=xpath_EmailDealPopupSubHeaderText)
	private WebElement txt_EmailDealPopupSubHeader;

	public final String xpath_EmailDealPopupParagraphText = "//div[@class='modal-body']//div[contains(text(),'spiritairlines')]";
	@FindBy(xpath=xpath_EmailDealPopupParagraphText)
	private WebElement txt_EmailDealPopupParagraph;

	public final String xpath_EmailDealPopupGoToHomepageButton = "//div[@class='modal-body']//button[contains(text(),'Go To Homepage')]";
	@FindBy(xpath=xpath_EmailDealPopupGoToHomepageButton)
	private WebElement btn_EmailDealPopupGoToHomepage;

	public final String xpath_EmailDealPopupContinueButton = "//button[contains(text(),'Close')]";
	@FindBy(xpath=xpath_EmailDealPopupContinueButton)
	private WebElement btn_EmailDealPopupContinue;

	//**************************************************************************************************
	//****************************************Statements Page*******************************************
	//**************************************************************************************************

	public final String xpath_StatementsHeaderText = "//app-account-statement//h2";
	@FindBy(xpath=xpath_StatementsHeaderText)
	private WebElement txt_StatementsHeader;

	public final String id_StatementActivityPeriodDropDown = "activityPeriods";
	@FindBy(id=id_StatementActivityPeriodDropDown)
	private WebElement drpdwn_StatementActivityPeriod;

	public final String id_StatementTransactionTypeDropDown = "activityTypes";
	@FindBy(id=id_StatementTransactionTypeDropDown)
	private WebElement drpdwn_StatementTransactionType;

	public final String xpath_StatementTransactionGoButton = "//button[contains(text(),'Go')]";
	@FindBy(xpath=xpath_StatementTransactionGoButton)
	private WebElement btn_StatementTransactionGo;

	public final String xpath_PostedTransactionHeaderText = "//app-account-statement//h3";
	@FindBy(xpath=xpath_PostedTransactionHeaderText)
	private WebElement txt_PostedTransactionHeader;

	public final String xpath_StatementTableDateHeaderText = "//table//thead//th[1]";
	@FindBy(xpath=xpath_StatementTableDateHeaderText)
	private WebElement txt_StatementTableDateHeader;

	public final String xpath_StatementTableTransactionHeaderText = "//table//thead//th[2]";
	@FindBy(xpath=xpath_StatementTableTransactionHeaderText)
	private WebElement txt_StatementTableTransactionHeader;

	public final String xpath_StatementTableDebitHeaderText = "//table//thead//th[3]";
	@FindBy(xpath=xpath_StatementTableDebitHeaderText)
	private WebElement txt_StatementTableDebitHeader;

	public final String xpath_StatementTableCreditHeaderText = "//table//thead//th[4]";
	@FindBy(xpath=xpath_StatementTableCreditHeaderText)
	private WebElement txt_StatementTableCreditHeader;

	public final String xpath_StatementTableBalanceHeaderText = "//table//thead//th[5]";
	@FindBy(xpath=xpath_StatementTableBalanceHeaderText)
	private WebElement txt_StatementTableBalanceHeader;

	public final String xpath_StatementTableDatesText = "//table//tbody//tr//td[1]";
	@FindBy(xpath=xpath_StatementTableDatesText)
	private List<WebElement> txt_StatementTableDates;

	public final String xpath_StatementTableTransactionsText = "//table//tbody//tr//td[2]";
	@FindBy(xpath=xpath_StatementTableTransactionsText)
	private List<WebElement> txt_StatementTableTransactions;

	public final String xpath_StatementTableDebitsText = "//table//tbody//tr//td[3]";
	@FindBy(xpath=xpath_StatementTableDebitsText)
	private List<WebElement> txt_StatementTableDebits;

	public final String xpath_StatementTableCreditsText = "//table//tbody//tr//td[4]";
	@FindBy(xpath=xpath_StatementTableCreditsText)
	private List<WebElement> txt_StatementTableCredits;

	public final String xpath_StatementTableBalancesText = "//table//tbody//tr//td[5]";
	@FindBy(xpath=xpath_StatementTableBalancesText)
	private List<WebElement> txt_StatementTableBalances;

	public final String xpath_StatementBeginningBalanceDateText = "//p[@class='p-legal']";
	@FindBy(xpath=xpath_StatementBeginningBalanceDateText)
	private WebElement txt_StatementBeginningBalanceDate;

	//**************************************************************************************************
	//***********************************Request MileageCredit Page*************************************
	//**************************************************************************************************

	public final String id_RequestMilesConfirmationCodeTextBox = "confirmationCode";
	@FindBy(id=id_RequestMilesConfirmationCodeTextBox)
	private WebElement txtbx_RequestMilesConfirmationCode;

	public final String xpath_RequestMilesGoButton = "//button[contains(text(),'Go')]";
	@FindBy(xpath=xpath_RequestMilesGoButton)
	private WebElement btn_RequestMilesGo;

	public final String xpath_WaitForFlightLandedAlertMessageText = "//div[@id='toast-container']//div[@role='alertdialog']";
	@FindBy(xpath=xpath_WaitForFlightLandedAlertMessageText)
	private WebElement txt_WaitForFlightLandedAlertMessage;

	public final String xpath_AlreadyReceivedMileageMessageText = "//div[@id='toast-container']//div[@role='alertdialog']";
	@FindBy(xpath=xpath_AlreadyReceivedMileageMessageText)
	private WebElement txt_AlreadyReceivedMileageMessage;

	public final String xpath_CloseWaitForFlightAlertMessageButton = "//div[@id='toast-container']//button";
	@FindBy(xpath=xpath_CloseWaitForFlightAlertMessageButton)
	private WebElement btn_CloseWaitForFlightAlertMessage;

	//**************************************************************************************************
	//***********************************Personal Information Page*************************************
	//**************************************************************************************************
	public final String xpath_PersonalInfoTitleLabel = "//label[@for='title']";
	@FindBy(xpath=xpath_PersonalInfoTitleLabel)
	private WebElement lbl_PersonalInfoTitle;

	public final String xpath_PersonalInfoTitleDropDown = "//select[@name='title']";
	@FindBy(xpath=xpath_PersonalInfoTitleDropDown)
	private WebElement drpdwn_PersonalInfoTitle;

	public final String xpath_PersonalInfoNameLabel = "//label[contains(text(),'Name')]";
	@FindBy(xpath=xpath_PersonalInfoNameLabel)
	private WebElement lbl_PersonalInfoName;

	public final String xpath_PersonalInfoNameText = "//label[contains(text(),'Name')]//following::p[1]";
	@FindBy(xpath=xpath_PersonalInfoNameText)
	private WebElement txt_PersonalInfoName;

	public final String xpath_PersonalInfoAddressLabel = "//label[@for='lineOne']";
	@FindBy(xpath=xpath_PersonalInfoAddressLabel)
	private WebElement lbl_PersonalInfoAddress;

	public final String xpath_PersonalInfoAddressTextBox = "//input[@id='lineOne']";
	@FindBy(xpath = xpath_PersonalInfoAddressTextBox)
	private WebElement txtbx_PersonalInfoAddress;

	public final String xpath_PersonalInfoAddressContinuedLabel = "//label[@for='lineTwo']";
	@FindBy(xpath=xpath_PersonalInfoAddressContinuedLabel)
	private WebElement lbl_PersonalInfoAddressContinued;

	public final String xpath_PersonalInfoAddressContinuedTextBox = "//input[@id='lineTwo']";
	@FindBy(xpath = xpath_PersonalInfoAddressContinuedTextBox)
	private WebElement txtbx_PersonalInfoAddressContinued;

	public final String xpath_PersonalInfoCityLabel = "//label[@for='city']";
	@FindBy(xpath=xpath_PersonalInfoCityLabel)
	private WebElement lbl_PersonalInfoCity;

	public final String xpath_PersonalInfoCityTextBox = "//input[@id='city']";
	@FindBy(xpath = xpath_PersonalInfoCityTextBox)
	private WebElement txtbx_PersonalInfoCity;

	public final String xpath_PersonalInfoStateLabel = "//label[@for='provinceState']";
	@FindBy(xpath=xpath_PersonalInfoStateLabel)
	private WebElement lbl_PersonalInfoState;

	public final String xpath_PersonalInfoStateDropDown = "//select[@id='provinceState']";
	@FindBy(xpath = xpath_PersonalInfoStateDropDown)
	private WebElement drpdwn_PersonalInfoState;

	public final String xpath_PersonalInfoZipCodeLabel = "//label[@for='postalCode']";
	@FindBy(xpath=xpath_PersonalInfoZipCodeLabel)
	private WebElement lbl_PersonalInfoZipCode;

	public final String xpath_PersonalInfoZipCodeTextBox = "//input[@id='postalCode']";
	@FindBy(xpath = xpath_PersonalInfoZipCodeTextBox)
	private WebElement txtbx_PersonalInfoZipCode;

	public final String xpath_PersonalInfoCountryLabel = "//label[@for='countryCode']";
	@FindBy(xpath=xpath_PersonalInfoCountryLabel)
	private WebElement lbl_PersonalInfoCountry;

	public final String xpath_PersonalInfoCountryDropDown = "//select[@id='countryCode']";
	@FindBy(xpath = xpath_PersonalInfoCountryDropDown)
	private WebElement drpdwn_PersonalInfoCountry;

	public final String xpath_PersonalInfoPrimaryPhoneLabel = "//label[@for='homePhone']";
	@FindBy(xpath=xpath_PersonalInfoPrimaryPhoneLabel)
	private WebElement lbl_PersonalInfoPrimaryPhone;

	public final String xpath_PersonalInfoPrimaryPhoneTextBox = "//input[@id='phonePrimaryNumber']";
	@FindBy(xpath = xpath_PersonalInfoPrimaryPhoneTextBox)
	private WebElement txtbx_PersonalInfoPrimaryPhone;

	public final String xpath_PersonalInfoSecondaryPhoneLabel = "//label[@for='workPhone']";
	@FindBy(xpath=xpath_PersonalInfoSecondaryPhoneLabel)
	private WebElement lbl_PersonalInfoSecondaryPhone;

	public final String xpath_PersonalInfoSecondaryPhoneTextBox = "//input[@id='workPhone']";
	@FindBy(xpath = xpath_PersonalInfoSecondaryPhoneTextBox)
	private WebElement txtbx_PersonalInfoSecondaryPhone;

	public final String xpath_PersonalInfoDOBLabel = "//label[@for='dateOfBirth']";
	@FindBy(xpath=xpath_PersonalInfoDOBLabel)
	private WebElement lbl_PersonalInfoDOB;

	public final String xpath_PersonalInfoDOBTextBox = "//input[@id='dateOfBirth']";
	@FindBy(xpath = xpath_PersonalInfoDOBTextBox)
	private WebElement txtbx_PersonalInfoDOB;

	public final String xpath_PersonalInfoKTNLabel = "//label[@for='knownTravelerNumber']";
	@FindBy(xpath=xpath_PersonalInfoKTNLabel)
	private WebElement lbl_PersonalInfoKTN;

	public final String xpath_PersonalInfoKTNTextBox = "//input[@id='knownTravelerNumber']";
	@FindBy(xpath = xpath_PersonalInfoKTNTextBox)
	private WebElement txtbx_PersonalInfoKTN;

	public final String xpath_PersonalInfoRedressNumberLabel = "//label[@for='redressNumber']";
	@FindBy(xpath=xpath_PersonalInfoRedressNumberLabel)
	private WebElement lbl_PersonalInfoRedressNumber;

	public final String xpath_PersonalInfoRedressNumberTextBox = "//input[@id='redressNumber']";
	@FindBy(xpath = xpath_PersonalInfoRedressNumberTextBox)
	private WebElement txtbx_PersonalInfoRedressNumber;


	//*****************************************************************************************************************
	//************************************Start of Methods of Account Profile Page*************************************
	//*****************************************************************************************************************

	//*************************************************************
	//*************************Header******************************
	//*************************************************************
	public WebElement getWelcomeHeaderText() {
		return txt_WelcomeHeader;
	}
	public WebElement getYourCurrentMilesText() {
		return txt_YourCurrentMiles;
	}
	public WebElement getMileageEarningTierText() {
		return txt_MileageEarningTier;
	}
	//*************************************************************
	//**********************Left Menu******************************
	//*************************************************************
	public WebElement getLeftMenuMyAccountLink(){
		return lnk_LeftMenuMyAccount;
	}

	public WebElement getLeftMenuPersonalInformationLink(){
		return lnk_LeftMenuPersonalInformation;
	}

	public WebElement getLeftMenuBillingInformationLink(){
		return lnk_LeftMenuBillingInformation;
	}

	public WebElement getLeftMenuEmailSubscriptionsLink(){
		return lnk_LeftMenuEmailSubscriptions;
	}

	public WebElement getLeftMenuStatementsLink(){
		return lnk_LeftMenuStatements;
	}

	public WebElement getLeftMenuRedeemMilesLink(){
		return lnk_LeftMenuRedeemMiles;
	}

	public WebElement getLeftMenuBuyGiftMilesLink(){
		return lnk_LeftMenuBuyGiftMiles;
	}

	public WebElement getLeftMenuRequestMileageCreditLink(){
		return lnk_LeftMenuRequestMileageCredit;
	}

	public WebElement getCancelMembershipDropDown(){
		return lnk_CancelMembershipDropDown;
	}

	public WebElement getMembershipCancellationReasonDropDown() {
		return drpdwn_MembershipCancellationReason;
	}

	public WebElement getMembershipCancellationButton() {
		return btn_MembershipCancellation;
	}

	public WebElement getNotAMemberYetText() {
		return txt_NotAMemberYet;
	}
	public WebElement getYourReservationsHeaderText() {
		return txt_YourReservationsHeader;
	}

	public WebElement getLocateYourReservationText() {
		return txt_LocateYourReservation;
	}
	public WebElement getConfirmationCodeSearchTextBox() {
		return txtbx_ConfimationCodeSearch;
	}
	public WebElement getConfirmationCodeGoButton() {
		return btn_ConfimationCodeGo;
	}
	public WebElement getCurrentReservationsHeaderText() {
		return txt_CurrentReservationsHeader;
	}


	public WebElement getCurrentReservationsDateLabel()
	{
		return lbl_CurrentReservationsDate;
	}

	public WebElement getCurrentReservationsOriginLabel()
	{
		return lbl_CurrentReservationsOrigin;
	}

	public WebElement getCurrentReservationsDestinationLabel()
	{
		return lbl_CurrentReservationsDestination;
	}

	public WebElement getCurrentReservationsConfirmationCodeLabel()

	{
		return lbl_CurrentReservationsConfirmationCode;
	}

	public WebElement getCurrentReservationsMilesLabel()
	{
		return lbl_CurrentReservationsMiles;
	}

	public List<WebElement> getCurrentReservationsDateText() {
		return txt_CurrentReservationsDate;
	}
	public List<WebElement> getCurrentReservationsOriginText() {
		return txt_CurrentReservationsOrigin;
	}
	public List<WebElement> getCurrentReservationsDestinationText() {
		return txt_CurrentReservationsDestination;
	}

	public List<WebElement> getCurrentReservationsConfirmationCodeText() {
		return txt_CurrentReservationsConfirmationCode;
	}

	public List<WebElement> getCurrentReservationsMilesText() {
		return txt_CurrentReservationsMiles;
	}

	public List<WebElement> getCurrentReservationsClickHereButton() {
		return btn_CurrentReservationsClickHere;
	}

	public WebElement getPastReservationsHeader() {
		return txt_PastReservationsHeader;
	}


	public WebElement getPastReservationsDateLabel(){
		return lbl_PastReservationsDate;
	}

	public WebElement getPastReservationsOriginLabel()
	{
		return lbl_PastReservationsOrigin;
	}

	public WebElement getPastReservationsDestinationLabel()
	{
		return lbl_PastReservationsDestination;
	}

	public WebElement getPastReservationConfirmationCodeLabel()
	{
		return lbl_PastReservationConfirmationCode;
	}

	public WebElement getPastReservationCMilesLabel()
	{
		return lbl_PastReservationCMiles;
	}
	public List<WebElement> getPastReservationsDateText() {
		return txt_PastReservationsDate;
	}

	public List<WebElement> getPastReservationsOriginText() {
		return txt_PastReservationsOrigin;
	}

	public List<WebElement> getPastReservationsDestinationText() {
		return txt_PastReservationsDestination;
	}

	public List<WebElement> getPastReservationsConfirmationCodeText() {
		return txt_PastReservationsConfirmationCode;
	}

	public List<WebElement> getPastReservationsMilesText() {
		return txt_PastReservationsMiles;
	}

	public List<WebElement> getPastReservationsClickHereButton() {
		return btn_PastReservationsClickHere;
	}

	//**************************************************************************************************
	//***************************************Billing Information Page***********************************
	//**************************************************************************************************
	public WebElement getBillingInformationNameOnCardText(){
		return txt_BillingInformationNameOnCard;
	}

	public WebElement getBillingInformationCardNumberText(){
		return txt_BillingInformationCardNumber;
	}

	public WebElement getBillingInformationExpiryMonthYearTextBox(){
		return txtbx_BillingInformationExpiryMonthYear;
	}

	public WebElement getBillingInformationStateDropDown(){
		return drpdwn_BillingInformationState;
	}

	public WebElement getBillingInformationCountryDropDown(){
		return drpdwn_BillingInformationCountry;
	}


	//*************************************************************
	//*******************Primary Card******************************
	//*************************************************************

	public WebElement getBillingInformationPrimaryCardHolderText(){
		return txt_BillingInformationPrimaryCardHolder;
	}

	public WebElement getBillingInformationPrimaryCardTypeText(){
		return txt_BillingInformationPrimaryCardType;
	}

	public WebElement getBillingInformationPrimaryCardNumberText(){
		return txt_BillingInformationPrimaryCardNumber;
	}

	public WebElement getBillingInformationPrimaryCardExpirationDateText(){
		return txt_BillingInformationPrimaryCardExpirationDate;
	}

	public WebElement getBillingInformationPrimaryCardBillingAddressText(){
		return txt_BillingInformationPrimaryCardBillingAddress;
	}

	public WebElement getBillingInformationPrimaryCardEditLink(){
		return lnk_BillingInformationPrimaryCardEdit;
	}

	public WebElement getBillingInformationPrimaryCardDeleteLink(){
		return lnk_BillingInformationPrimaryCardDelete;
	}

	//*************************************************************
	//****************Additional Card******************************
	//*************************************************************
	public WebElement getBillingInformationAdditionalCardHolderText(){
		return txt_BillingInformationAdditionalCardHolder;
	}

	public WebElement getBillingInformationAdditionalCardTypeText(){
		return txt_BillingInformationAdditionalCardType;
	}

	public List<WebElement> getBillingInformationAdditionalCardNumberText(){
		return txt_BillingInformationAdditionalCardNumber;
	}

	public WebElement getBillingInformationAdditionalCardExpirationDateText(){
		return txt_BillingInformationAdditionalCardExpirationDate;
	}

	public WebElement getBillingInformationAdditionalCardBillingAddressText(){
		return txt_BillingInformationAdditionalCardBillingAddress;
	}

	public WebElement getBillingInformationAdditionalCardEditLink(){
		return lnk_BillingInformationAdditionalCardEdit;
	}

	public List<WebElement> getBillingInformationAdditionalCardDeleteLink(){
		return lnk_BillingInformationAdditionalCardDelete;
	}

	public WebElement getBillingInformationAddAnotherCardButton(){
		return btn_BillingInformationAddAnotherCard;
	}

	//*************************************************************
	//*******************Add Card**********************************
	//*************************************************************
	public WebElement getBillingInformationAddCardAccountHolderNameTextBox(){
		return txtbx_BillingInformationAddCardAccountHolderName;
	}

	public WebElement getBillingInformationAddCardCardNumberTextBox(){
		return txtbx_BillingInformationAddCardCardNumber;
	}

	public WebElement getBillingInformationAddCardExpMonthYearTextBox(){
		return txtbx_BillingInformationAddCardExpMonthYear;
	}

	public WebElement getBillingInformationAddCardMakeThisPrimaryCardLabel(){
		return lbl_BillingInformationAddCardMakeThisPrimaryCard;
	}

	public WebElement getBillingInformationAddCardMakeThisPrimaryCardCheckBox(){
		return chkbx_BillingInformationAddCardMakeThisPrimaryCard;
	}

	//*************************************************************
	//*******************Billing Address***************************
	//*************************************************************
	public WebElement getBillingInformationUseSameAddressLabel(){
		return lbl_BillingInformationUseSameAddress;
	}

	public WebElement getBillingInformationUseSameAddressCheckBox(){
		return chkbx_BillingInformationUseSameAddress;
	}

	public WebElement getBillingInformationBillingAddressTextBox(){
		return txtbx_BillingInformationBillingAddress;
	}

	public WebElement getBillingInformationBillingCityTextBox(){
		return txtbx_BillingInformationBillingCity;
	}

	public WebElement getBillingInformationBillingStateDropDown(){
		return drpdn_BillingInformationBillingState;
	}

	public WebElement getBillingInformationBillingZipPostalTextBox(){
		return txtbx_BillingInformationBillingZipPostal;
	}

	public WebElement getBillingInformationBillingCountryDropDown(){
		return drpdn_BillingInformationBillingCountry;
	}

	public WebElement getBillingInformationSaveButton(){
		return btn_BillingInformationSaveButton;
	}

	public WebElement getBillingInformationCancelButton(){
		return btn_BillingInformationCancelButton;
	}
	//*************************************************************
	//*******************Delete Card Popup*************************
	//*************************************************************
	public WebElement getDeleteCardPopupHeaderText(){
		return txt_DeleteCardPopupHeader;
	}

	public WebElement getDeleteCardPopupCloseLink(){
		return lnk_DeleteCardPopupClose;
	}

	public WebElement getDeleteCardPopupSubHeaderText(){
		return txt_DeleteCardPopupSubHeader;
	}

	public WebElement getDeleteCardPopupDeleteCardButton(){
		return btn_DeleteCardPopupDeleteCard;
	}

	//**************************************************************************************************
	//********************************************Redeem Miles Page*************************************
	//**************************************************************************************************
	public WebElement getRedeemMilesHeaderText(){
		return txt_RedeemMilesHeader;
	}

	public WebElement getRedeemMilesYourCurrentMilesText(){
		return txt_RedeemMilesYourCurrentMiles;
	}

	public WebElement getRedeemMilesYourCurrentMilesLink(){
		return lnk_RedeemMilesYourCurrentMiles;
	}

	public List<WebElement> getRedeemMilesSubHeadersText(){
		return txt_RedeemMilesSubHeaders;
	}

	//*************************************************************
	//****************Your Membership******************************
	//*************************************************************

	public WebElement getFreeSpiritNumberText() {
		return txt_FreeSpiritNumber;
	}

	public WebElement getMemberNameText() {
		return txt_MemberName;
	}

	public WebElement getDateJoinedText() {
		return txt_DateJoined;
	}

	public WebElement getYourMembershipHeaderText() {
		return txt_YourMembershipHeader;
	}
	public WebElement getYourMembershipTypeText() {
		return txt_YourMembershipType;
	}

	public WebElement getDaysLeftInMembershipText() {
		return txt_DaysLeftInMembership;
	}

	//**************************************************************************************************
	//********************************************Email Deal Page***************************************
	//**************************************************************************************************
	public WebElement getEmailDealEmailAddressTextBox() {
		return txtbx_EmailDealEmailAddress;
	}

	public WebElement getEmailDealNewEmailAddressTextBox() {
		return txtbx_EmailDealNewEmailAddress;
	}

	public WebElement getEmailDealConfirmEmailAddressTextBox() {
		return txtbx_EmailDealConfirmEmailAddress;
	}

	public WebElement getEmailDealFirstNameTextBox() {
		return txtbx_EmailDealFirstName;
	}

	public WebElement getEmailDealLastNameTextBox() {
		return txtbx_EmailDealLastName;
	}

	public WebElement getEmailDealPrimaryAirportDropDown() {
		return drpdwn_EmailDealPrimaryAirport;
	}

	public WebElement getEmailDealSecondaryAirportDropDown() {
		return drpdwn_EmailDealSecondaryAirport;
	}

	public WebElement getEmailDealUpdateAccountOrUnsubscribeText() {
		return txt_EmailDealUpdateAccountOrUnsubscribe;
	}

	public WebElement getEmailDealUnsubscribeCheckBox() {
		return chkbx_EmailDealUnsubscribe;
	}

	public WebElement getEmailDealSubscribeButton() {
		return btn_EmailDealSubscribe;
	}

	public WebElement getEmailDealContinueButton() {
		return btn_EmailDealContinue;
	}

	public WebElement getEmailDealSubmitChangeButton() {
		return btn_EmailDealSubmitChanges;
	}

	public WebElement getEmailDealPopupHeaderText() {
		return txt_EmailDealPopupHeader;
	}

	public WebElement getEmailDealPopupSubHeaderText() {
		return txt_EmailDealPopupSubHeader;
	}

	public WebElement getEmailDealPopupParagraphText() {
		return txt_EmailDealPopupParagraph;
	}

	public WebElement getEmailDealPopupGoToHomepageButton() {
		return btn_EmailDealPopupGoToHomepage;
	}

	public WebElement getEmailDealPopupContinueButton() {
		return btn_EmailDealPopupContinue;
	}

	//**************************************************************************************************
	//****************************************Statements Page*******************************************
	//**************************************************************************************************
	public WebElement getStatementsHeaderText() {
		return txt_StatementsHeader;
	}

	public WebElement getStatementActivityPeriodDropDown() {
		return drpdwn_StatementActivityPeriod;
	}

	public WebElement getStatementTransactionTypeDropDown() {
		return drpdwn_StatementTransactionType;
	}

	public WebElement getStatementTransactionGoButton() {
		return btn_StatementTransactionGo;
	}

	public WebElement getPostedTransactionHeaderText() {
		return txt_PostedTransactionHeader;
	}

	public WebElement getStatementTableDateHeaderText() {
		return txt_StatementTableDateHeader;
	}

	public WebElement getStatementTableTransactionHeaderText() {
		return txt_StatementTableTransactionHeader;
	}

	public WebElement getStatementTableDebitHeaderText() {
		return txt_StatementTableDebitHeader;
	}

	public WebElement getStatementTableCreditHeaderText() {
		return txt_StatementTableCreditHeader;
	}

	public WebElement getStatementTableBalanceHeaderText() {
		return txt_StatementTableBalanceHeader;
	}

	public List<WebElement> getStatementTableDatesText() {
		return txt_StatementTableDates;
	}

	public List<WebElement> getStatementTableTransactionsText() {
		return txt_StatementTableTransactions;
	}

	public List<WebElement> getStatementTableDebitsText() {
		return txt_StatementTableDebits;
	}

	public List<WebElement> getStatementTableCreditsText() {
		return txt_StatementTableCredits;
	}

	public List<WebElement> getStatementTableBalancesText() {
		return txt_StatementTableBalances;
	}

	public WebElement getStatementBeginningBalanceDate() {
		return txt_StatementBeginningBalanceDate;
	}

	//**************************************************************************************************
	//***********************************Request MileageCredit Page*************************************
	//**************************************************************************************************

	public WebElement getReqestMilesConfirmationTextBox() {
		return txtbx_RequestMilesConfirmationCode;
	}

	public WebElement getReqestMilesGoButton() {
		return btn_RequestMilesGo;
	}

	public WebElement getReqestWaitForFlightLandedAlertText() {
		return txt_WaitForFlightLandedAlertMessage;
	}

	public WebElement getAlreadyReceivedMileageMessageText()
	{
		return txt_AlreadyReceivedMileageMessage;
	}

	public WebElement CloseWaitForFlightAlertMessageButton() {
		return btn_CloseWaitForFlightAlertMessage;
	}

	//**************************************************************************************************
	//********************************Personal Information Page MEthod**********************************
	//**************************************************************************************************

	public WebElement getPersonalInfoTitleLabel() {return lbl_PersonalInfoTitle;}

	public WebElement getPersonalInfoTitleDropDown() {return drpdwn_PersonalInfoTitle;}

	public WebElement getPersonalInfoNameLabel() {return lbl_PersonalInfoName;}

	public WebElement getPersonalInfoNameText() {return txt_PersonalInfoName;}

	public WebElement getPersonalInfoAddressLabel() {return lbl_PersonalInfoAddress;}

	public WebElement getPersonalInfoAddressTextBox() {return txtbx_PersonalInfoAddress;}

	public WebElement getPersonalInfoAddressContinuedLabel() {return lbl_PersonalInfoAddressContinued;}

	public WebElement getPersonalInfoAddressContinuedTextBox() {return txtbx_PersonalInfoAddressContinued;}

	public WebElement getPersonalInfoCityLabel() {return lbl_PersonalInfoCity;}

	public WebElement getPersonalInfoCityTextBox() {return txtbx_PersonalInfoCity;}

	public WebElement getPersonalInfoStateLabel() {return lbl_PersonalInfoState;}

	public WebElement getPersonalInfoStateDropDown() {return drpdwn_PersonalInfoState;}

	public WebElement getPersonalInfoZipCodeLabel() {return lbl_PersonalInfoZipCode;}

	public WebElement getPersonalInfoZipCodeTextBox() {return txtbx_PersonalInfoZipCode;}

	public WebElement getPersonalInfoCountry() {return lbl_PersonalInfoCountry;}

	public WebElement getPersonalInfoCountryDropDown() {return drpdwn_PersonalInfoCountry;}

	public WebElement getPersonalInfoPrimaryPhoneLabel() {return lbl_PersonalInfoPrimaryPhone;}

	public WebElement getPersonalInfoPrimaryPhoneTextBox() {return txtbx_PersonalInfoPrimaryPhone;}

	public WebElement getPersonalInfoSecondaryPhoneLabel() {return lbl_PersonalInfoSecondaryPhone;}

	public WebElement getPersonalInfoSecondaryPhoneTextBox() {return txtbx_PersonalInfoSecondaryPhone;}

	public WebElement getPersonalInfoDOBLabel() {return lbl_PersonalInfoDOB;}

	public WebElement getPersonalInfoDOBTextBox() {return txtbx_PersonalInfoDOB;}

	public WebElement getPersonalInfoKTNLabel() {return lbl_PersonalInfoKTN;}

	public WebElement getPersonalInfoKTNTextBox() {return txtbx_PersonalInfoKTN;}

	public WebElement getPersonalInfoRedressNumberLabel() {return lbl_PersonalInfoRedressNumber;}

	public WebElement getPersonalInfoRedressNumberTextBox() {return txtbx_PersonalInfoRedressNumber;}

}
