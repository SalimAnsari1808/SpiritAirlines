package com.spirit.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
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

	public ConfirmationPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*******************************************************************
	//***********************Confirmation Page Header********************
	//*******************************************************************
	public final String xpath_ConfirtamionPageHeaderText = "//h1[@class='headline1']";
	@FindBy(xpath=xpath_ConfirtamionPageHeaderText)
	private WebElement txt_ConfirtamionPageHeader;

	public final String xpath_ConfirmationPageSubHeaderText = "//h1[@class='headline1']/..//p";
	@FindBy(xpath=xpath_ConfirmationPageSubHeaderText)
	private WebElement txt_ConfirmationPageSubHeader;

	public final String xpath_ConfirmationPageSubHeaderBuyYourBagsText = "//h1[@class='headline1']/following-sibling::div[2]";
	@FindBy(xpath=xpath_ConfirmationPageSubHeaderBuyYourBagsText)
	private WebElement txt_ConfirmationPageSubHeaderBuyYourBags;

	public final String xpath_HeaderPrintConfirmationButton = "(//button[@name='print-button'])[1]";
	@FindBy(xpath=xpath_HeaderPrintConfirmationButton)
	private WebElement btn_HeaderPrintConfirmation;

	public final String xpath_AddTripToCalendarButton = "//button[contains(text(),'Add Trip to Calendar')]";
	@FindBy(xpath=xpath_AddTripToCalendarButton)
	private WebElement btn_AddTripToCalendar;

	public final String xpath_NotYourBoardingPassText = "//strong//strong";
	@FindBy(xpath = xpath_NotYourBoardingPassText)
	private WebElement txt_NotYourBoardingPass;

	//*******************************************************************
	//***************************ROKT Popup******************************
	//*******************************************************************
	public final String xpath_ROGTIframe = "//iframe[contains(@id,'rl__widget')]";
	@FindBy(xpath=xpath_ROGTIframe)
	private WebElement ifr_ROGT;

	public final String xpath_ROKTPoupHeaderText = "//div[@id='ux_widget_inner']//a";
	@FindBy(xpath=xpath_ROKTPoupHeaderText)
	private List<WebElement> txt_ROKTPoupHeader;

	//	public final String xpath_ROKTPoupCloseButton = "//a[contains(@class,'ui_widget_close_button')]";
	public final String xpath_ROKTPoupCloseButton = "//button | //a";
	@FindBy(xpath=xpath_ROKTPoupCloseButton)
	private List<WebElement> btn_ROKTPoupClose;

	public final String xpath_ROKTNoThanksButton = "//button[@id='ui_smartsignup_layout_negative_button']";
	@FindBy(xpath=xpath_ROKTNoThanksButton)
	private List<WebElement> btn_ROKTNoThanks;

	//*******************************************************************
	//**********************Booking Section Top**************************
	//*******************************************************************
	public final String xpath_BookingSectionTopDateText = "//strong[contains(text(),'Booking Date: ') or contains(text(),'Fecha de la Reservación:')]/..";
	@FindBy(xpath=xpath_BookingSectionTopDateText)
	private List<WebElement> txt_BookingSectionTopDate;

	public final String xpath_BookingSectionTopStatusText = "//strong[contains(text(),'Status:') or contains(text(),'Estado:')]/.. | (//strong[contains(text(),'Status') or contains(text(),'Estado')]/../../following-sibling::div)[1]";
	@FindBy(xpath=xpath_BookingSectionTopStatusText)
	private List<WebElement> txt_BookingSectionTopStatus;

	public final String xpath_BookingSectionTopConfirmationCodeText = "//strong[contains(text(),'Confirmation Code:') or contains(text(),'Clave de Confirmación:')]/..|//strong[contains(text(),'Confirmation Code') or contains(text(),'Clave de Confirmación')]/../../following-sibling::div/p";
	@FindBy(xpath=xpath_BookingSectionTopConfirmationCodeText)
	private List<WebElement> txt_BookingSectionTopConfirmationCode;


	public final String xpath_BookingSectionTopCarConfirmationCodeText = "//strong[contains(text(),'Car Confirmation Code:') or contains(text(),'Clave de Confirmación del Auto:')]/..";
	@FindBy(xpath=xpath_BookingSectionTopCarConfirmationCodeText)
	private List<WebElement> txt_BookingSectionTopCarConfirmationCode;

	public final String xpath_BookingSectionTopHotelConfirmationCodeText = "//strong[contains(text(),'Hotel Confirmation Code:') or contains(text(),'Confirmación del Hotel:')]/..";
	@FindBy(xpath=xpath_BookingSectionTopHotelConfirmationCodeText)
	private List<WebElement> txt_BookingSectionTopHotelConfirmationCode;

	//*******************************************************************
	//**********************Fligth Section Top**************************
	//*******************************************************************

	public final String xpath_FlightSectionDateText = "//app-flight-itinerary//div[contains(@class,'flight-desktop-container')]//strong//i//..";
	@FindBy(xpath = xpath_FlightSectionDateText)
	private List<WebElement> txt_FlightSectionDate;

	public final String xpath_FlightSectionFlightNumberText = "//app-flight-itinerary//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Flight:')]/..";
	@FindBy(xpath = xpath_FlightSectionFlightNumberText)
	private List<WebElement> txt_FlightSectionFlightNumber;

	public final String xpath_FlightSectionDepartCityText = "//app-flight-itinerary//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Depart:')]/..";
	@FindBy(xpath = xpath_FlightSectionDepartCityText)
	private List<WebElement> txt_FlightSectionDepartCity;

	public final String xpath_FlightSectionAriveCityText = "//app-flight-itinerary//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Arrive:')]/..";
	@FindBy(xpath = xpath_FlightSectionAriveCityText)
	private List<WebElement> txt_FlightSectionAriveCity;

	//*******************************************************************
	//*************************Passenger Section*************************
	//*******************************************************************

	public final String xpath_PassengerHeaderText = "//h2[contains(text(),'Passenger') or contains(text(),'Pasajero')]";
	@FindBy(xpath=xpath_PassengerHeaderText)
	private WebElement txt_PassengerHeader;

	public final String xpath_PassengerIconImage = "//div[@ngmodelgroup='passengers']//i[contains(@class,'icon-user')]";
	@FindBy(xpath=xpath_PassengerIconImage)
	private List<WebElement> img_PassengerIcon;

	public final String xpath_PassengerSectionNamesText = "//div[@ngmodelgroup='passengers']//strong";
	@FindBy(xpath=xpath_PassengerSectionNamesText)
	private List<WebElement> txt_PassengerSectionNames;

	public final String xpath_PassengerFreeSpiritNumberText = "//div[@ngmodelgroup='passengers']//p[contains(text(),'Free Spirit #:')]";
	@FindBy(xpath=xpath_PassengerFreeSpiritNumberText)
	private List<WebElement> txt_PassengerFreeSpiritNumber;

	public final String xpath_PassengerAdditionalInfoText = "//p[contains(text(),' Additional Info: ')]";
	@FindBy(xpath= xpath_PassengerAdditionalInfoText)
	private List<WebElement> txt_PassengerAdditionalInfo;

	public final String xpath_BagsIconImage = "//i[contains(@class,'icon-checked-bag')]";
	@FindBy(xpath=xpath_BagsIconImage)
	private List<WebElement> img_BagsIcon;

	public final String xpath_DepBagsText = "//i[contains(@class,'icon-checked-bag')]/following::p[1]";
	@FindBy(xpath=xpath_DepBagsText)
	private List<WebElement> txt_DepBags;

	public final String xpath_RetBagsText = "//i[contains(@class,'icon-checked-bag')]/following::p[2]";
	@FindBy(xpath=xpath_RetBagsText)
	private List<WebElement> txt_RetBags;

	public final String xpath_SeatIconImage = "//i[contains(@class,'icon-seats')]";
	@FindBy(xpath=xpath_SeatIconImage)
	private List<WebElement> img_seatIcon;

	public final String xpath_DepSeatsText = "//i[contains(@class,'icon-seats')]/following::p[1]";
	@FindBy(xpath=xpath_DepSeatsText)
	private List<WebElement> txt_DepSeats;

	public final String xpath_RetSeatsText = "//i[contains(@class,'icon-seats')]/following::p[2]";
	@FindBy(xpath=xpath_RetSeatsText)
	private List<WebElement> txt_RetSeats;

	public final String xpath_WheelChairServiceText = "//strong[contains(text(),'Wheelchair Services')]/../following-sibling::span";
	@FindBy(xpath=xpath_WheelChairServiceText)
	private List<WebElement> txt_WheelChairService;

	public final String xpath_SpecialAssistanceText = "//strong[contains(text(),'Special Assistance')]/../following-sibling::span";
	@FindBy(xpath=xpath_SpecialAssistanceText)
	private List<WebElement> txt_SpecialAssistance;

	public final String xpath_KTNparagraphText = "//p[contains(text(),'):')]";
	@FindBy (xpath = xpath_KTNparagraphText)
	private WebElement txt_KTNparagraph;

	//*******************************************************************
	//*************************Travel Insurance**************************
	//*******************************************************************

	public final String xpath_TravelInsurancePolicyNumberText = "(//app-travel-insurance-info-summary//div)[7]";
	@FindBy(xpath=xpath_TravelInsurancePolicyNumberText)
	private WebElement txt_TravelInsurancePolicyNumber;

	public final String xpath_TravelInsurancePrimaryInsuredNameText = "(//app-travel-insurance-info-summary//div)[9]";
	@FindBy(xpath=xpath_TravelInsurancePrimaryInsuredNameText)
	private WebElement txt_TravelInsurancePrimaryInsuredName;

	public final String xpath_TravelInsuranceNoteText = "//div[contains(text(),'NOTE:')]";
	@FindBy(xpath=xpath_TravelInsuranceNoteText)
	private WebElement txt_TravelInsuranceNoteText;

	//*******************************************************************
	//*************************Contact Section***************************
	//*******************************************************************

	public final String xpath_ContactHeaderText = "//h2[contains(text(),'Contact') or contains(text(),'Contacto')]";
	@FindBy(xpath=xpath_ContactHeaderText)
	private WebElement txt_ContactHeader;

	public final String xpath_ContactPersonNameText = "(//app-contact-info-summary//p)[1]";
	@FindBy(xpath=xpath_ContactPersonNameText)
	private WebElement txt_ContactPersonName;

	public final String xpath_ContactPersonEmailText = "(//app-contact-info-summary//p)[2]";
	@FindBy(xpath=xpath_ContactPersonEmailText)
	private WebElement txt_ContactPersonEmail;

	public final String xpath_ContactPersonPhoneNumberText = "(//app-contact-info-summary//p)[3]";
	@FindBy(xpath=xpath_ContactPersonPhoneNumberText)
	private WebElement txt_ContactPersonPhoneNumber;

	public final String xpath_ContactPersonNoteText = "(//app-contact-info-summary//p)[4]";
	@FindBy(xpath=xpath_ContactPersonNoteText)
	private WebElement txt_ContactPersonNote;

	//*******************************************************************
	//********************9FC Yellow Savings Block***********************
	//*******************************************************************
	public final String xpath_9FCBookingSavingsAmountText = "(//div[contains(@class,'savings ')]//p)[2]";
	@FindBy(xpath=xpath_9FCBookingSavingsAmountText)
	private WebElement txt_9FCBookingSavingsAmount;

	public final String xpath_9FCBookingSavingVerbageText = "(//div[contains(@class,'savings ')]//p)[1]";
	@FindBy(xpath = xpath_9FCBookingSavingVerbageText)
	private WebElement txt_9FCBookingSavingVerbage;

	//*******************************************************************
	//*************************Travel Insurance**************************
	//*******************************************************************
	public final String xpath_TravelInsuranceHeaderText = "//h2[contains(text(),'Travel Insurance') or contains(text(),'Seguro de Viaje')]";
	@FindBy(xpath=xpath_TravelInsuranceHeaderText)
	private WebElement txt_TravelInsuranceHeader;

	public final String xpath_TravelInsuranceSubHeaderText = "//h2[contains(text(),'Travel Insurance') or contains(text(),'Seguro de Viaje')]/following-sibling::div//strong";
	@FindBy(xpath=xpath_TravelInsuranceSubHeaderText)
	private List<WebElement> txt_TravelInsuranceSubHeader;

	public final String xpath_TravelInsuranceSubHeaderValuesText = "//h2[contains(text(),'Travel Insurance') or contains(text(),'Seguro de Viaje')]/following-sibling::div/div/div[2]//div";
	@FindBy(xpath=xpath_TravelInsuranceSubHeaderValuesText)
	private List<WebElement> txt_TravelInsuranceSubHeaderValues;

	public final String xpath_TravelInsurancePolicyOnlineLink = "//h2[contains(text(),'Travel Insurance') or contains(text(),'Seguro de Viaje')]/following-sibling::div/div//a";
	@FindBy(xpath=xpath_TravelInsurancePolicyOnlineLink)
	private WebElement lnk_TravelInsurancePolicyOnline;

	public final String xpath_TravelInsuranceAdditionalQuestionsText = "//h2[contains(text(),'Travel Insurance') or contains(text(),'Seguro de Viaje')]/following-sibling::div/div";
	@FindBy(xpath=xpath_TravelInsuranceAdditionalQuestionsText)
	private WebElement txt_TravelInsuranceAdditionalQuestions;

	//*******************************************************************
	//*************************Options Section***************************
	//*******************************************************************
	public final String xpath_OptionSectionSelectedOptionsText = "//h3[contains(text(),'Your Extras') or contains(text(),'Sus Extras')]/../../following-sibling::div//div[contains(@class,'row')]";
	@FindBy(xpath=xpath_OptionSectionSelectedOptionsText)
	private List<WebElement> txt_OptionSectionSelectedOptions;

	public final String xpath_OptionSectionFlightFlexHeaderText = "//p[(text()='Flight Flex')]";
	@FindBy(xpath=xpath_OptionSectionFlightFlexHeaderText)
	private WebElement txt_OptionSectionFlightFlexHeader;

	public final String xpath_OptionSectionFlightFlexSubHeaderText = "//p[(text()='Flight Flex')]/following-sibling::p";
	@FindBy(xpath=xpath_OptionSectionFlightFlexSubHeaderText)
	private WebElement txt_OptionSectionFlightFlexSubHeader;

	public final String xpath_OptionSectionShortcutSecurityHeaderText = "//p[text()='Shortcut Security' or text()='Atajo de Seguridad']";
	@FindBy(xpath=xpath_OptionSectionShortcutSecurityHeaderText)
	private WebElement txt_OptionSectionShortcutSecurityHeader;

	public final String xpath_OptionSectionShortcutSecuritySubHeaderText = "//p[text()='Shortcut Security' or text()='Atajo de Seguridad']/following-sibling::p";
	@FindBy(xpath=xpath_OptionSectionShortcutSecuritySubHeaderText)
	private WebElement txt_OptionSectionShortcutSecuritySubHeader;

	public final String xpath_OptionSectionShortcutBoardingHeaderText = "//p[text()='Shortcut Boarding' or text()='Prioridad de Embarque']";
	@FindBy(xpath=xpath_OptionSectionShortcutBoardingHeaderText)
	private WebElement txt_OptionSectionShortcutBoardingHeader;

	public final String xpath_OptionSectionShortcutBoardingSubHeaderText = "//p[text()='Shortcut Boarding' or text()='Prioridad de Embarque']/following-sibling::p";
	@FindBy(xpath=xpath_OptionSectionShortcutBoardingSubHeaderText)
	private WebElement txt_OptionSectionShortcutBoardingSubHeader;

	//*******************************************************************
	//*************************Total Paid Amount*************************
	//*******************************************************************
	public final String xpath_TotalPaidVerbiageLabel = "//p[contains(text(),'Total Paid') or contains(text(),'Total Pagado')]";
	@FindBy(xpath=xpath_TotalPaidVerbiageLabel)
	private WebElement lbl_TotalPaidVerbiage;

	//public final String xpath_TotalPaidPriceText = "//p[contains(text(),'Total Paid') or contains(text(),'Total Pagado')]/../following-sibling::div[2]/p";
	public final String xpath_TotalPaidPriceText = "//p[contains(text(),'Total Paid') or contains(text(),'Total ')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalPaidPriceText)
	private WebElement txt_TotalPaidPrice;

	public final String xpath_PackagePricePaidLabel = "//div//p[contains(@class,'priceSummary')]";
	@FindBy(xpath=xpath_PackagePricePaidLabel)
	private WebElement lbl_PackagePricePaid;

	//div[@class='total-breakdown']/app-breakdown-section[1]//app-price-section-line[@level='1'][1]//app-price-section-line[@level='2'][1]/div//p
	public final String xpath_TotalPaidBreakDownLink = "//div[contains(@class,'total-due-container')]//a";
	@FindBy(xpath=xpath_TotalPaidBreakDownLink)
	private WebElement lnk_TotalPaidBreakDown;

	public final String xpath_FlightVerbiageLabel = "(//div[@class='total-breakdown']//p[contains(text(),'Flight') or contains(text(),'Opciones') ])[1]";
	@FindBy(xpath=xpath_FlightVerbiageLabel)
	private WebElement lbl_FlightVerbiage;

	public final String xpath_TotalDepFlightPaidText = "(//app-price-section-line//span)[1]";
	@FindBy(xpath=xpath_TotalDepFlightPaidText)
	private WebElement txt_TotalDepFlightPaid;

	public final String xpath_TotalRetFlightPaidText = "((//app-price-section-line//p[contains(text(),'Flight')])[2]//ancestor::app-price-section-line//div[2])[1]";
	@FindBy(xpath=xpath_TotalRetFlightPaidText)
	private WebElement txt_TotalRetFlightPaid;

	public final String xpath_BagsBreakdownLabel = "(//div[@class='total-breakdown']//p[contains(text(),'Bags') or contains(text(),'Equipaje') ])";
	@FindBy(xpath=xpath_BagsBreakdownLabel)
	private WebElement lbl_BagsBreakdown;

	public final String xpath_VatTaxAmountText = "//div[@class='total-breakdown']//p[contains(text(),' VAT Tax') or contains(text(),'Equipaje')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_VatTaxAmountText)
	private WebElement txt_VatTaxAmount;

	public final String xpath_OptioinsVerbiageLabel = "//p[contains(text(),'Options') or contains(text(),'Opciones') ]";
	@FindBy(xpath=xpath_OptioinsVerbiageLabel)
	private WebElement lbl_OptioinsVerbiage;

	public final String xpath_OptionsPriceText = "//p[contains(text(),'Options') or contains(text(),'Opciones') ]/../following-sibling::div[2]//p";
	@FindBy(xpath=xpath_OptionsPriceText)
	private WebElement txt_OptionsPrice;

	public final String xpath_OptionsBreakDownLink = "//p[contains(text(),'Options') or contains(text(),'Opciones') ]/../following-sibling::div[2]//a";
	@FindBy(xpath=xpath_OptionsBreakDownLink)
	private WebElement lnk_OptionsBreakDown;

	public final String xpath_9FCClubMembershipVerbiageLabel = "//div[@class='total-breakdown']//div/p[contains(text(),'$9 Fare Club Membership')]";
	@FindBy(xpath=xpath_9FCClubMembershipVerbiageLabel)
	private WebElement lbl_9FCClubMembershipVerbiage;

	public final String xpath_9FCClubMembershipPriceText = "//p[contains(text(),'$9 Fare Club Membership')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_9FCClubMembershipPriceText)
	private WebElement txt_9FCClubMembershipPrice;

	public final String xpath_TotalDueBundleDiscountText = "//div[@class='total-breakdown']//p[contains(text(),'Bundle It Discount') or contains(text(),'Bundle It Descuento')]";
	@FindBy(xpath=xpath_TotalDueBundleDiscountText)
	private WebElement txt_TotalDueBundleDiscount;

	public final String xpath_TotalDueBundleDiscountPriceText = "//div[@class='total-breakdown']//p[contains(text(),'Bundle It Discount') or contains(text(),'Bundle It Descuento')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalDueBundleDiscountPriceText)
	private WebElement txt_TotalDueBundleDiscountPrice;

	public final String xpath_TotalDueBoostDiscountText = "//div[@class='total-breakdown']//p[contains(text(),'Boost It Discount') or contains(text(),'Boost It Descuento')]";
	@FindBy(xpath=xpath_TotalDueBoostDiscountText)
	private WebElement txt_TotalDueBoostDiscount;

	public final String xpath_TotalDueBoostDiscountPriceText = "//div[@class='total-breakdown']//p[contains(text(),'Boost It Discount') or contains(text(),'Boost It Descuento')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalDueBoostDiscountPriceText)
	private WebElement txt_TotalDueBoostDiscountPrice;

	//***********************************Confirmation Page Methods***************************************************

	//*******************************************************************
	//***********************Confirmation Page Header********************
	//*******************************************************************
	public WebElement getConfirmationPageHeaderText() {
		return txt_ConfirtamionPageHeader;
	}

	public WebElement getConfirmationPageSubHeaderText() {
		return txt_ConfirmationPageSubHeader;
	}

	public WebElement getConfirmationPageSubHeaderBuyYourBagsText() {
		return txt_ConfirmationPageSubHeaderBuyYourBags;
	}

	public WebElement getHeaderPrintConfirmationButton() {
		return btn_HeaderPrintConfirmation;
	}

	public WebElement getAddTripToCalendarButton() {return btn_AddTripToCalendar;}

	public WebElement getNotYourBoardingPassText(){
		return txt_NotYourBoardingPass;
	}

	//*******************************************************************
	//***************************ROKT Popup******************************
	//*******************************************************************
	public WebElement getROKTIFrame() {
		return ifr_ROGT;
	}

	public List<WebElement> getROKTPoupHeaderText() {
		return txt_ROKTPoupHeader;
	}

	public List<WebElement> getROKTPoupCloseButton() {
		return btn_ROKTPoupClose;
	}

	public List<WebElement> getROKTNoThanksButton() {
		return btn_ROKTNoThanks;
	}

	//*******************************************************************
	//**********************Booking Section Top**************************
	//*******************************************************************
	public List<WebElement> getBookingSectionTopDateText() {
		return txt_BookingSectionTopDate;
	}

	public WebElement getBookingSectionTopStatusText() {

		for(int statusCount = 0;statusCount<txt_BookingSectionTopStatus.size();statusCount++){
			if(txt_BookingSectionTopStatus.get(statusCount).isDisplayed()){
				return txt_BookingSectionTopStatus.get(statusCount);
			}

		}

		return null;
	}

	public List<WebElement> getBookingSectionTopConfirmationCode() {
		return txt_BookingSectionTopConfirmationCode;
	}


	public List<WebElement> getBookingSectionTopCarConfirmationCodeText() {
		return txt_BookingSectionTopCarConfirmationCode;
	}
	public List<WebElement> getBookingSectionTopHotelConfirmationCodeText() {
		return txt_BookingSectionTopHotelConfirmationCode;
	}


	//*******************************************************************
	//**********************FLight Section method************************
	//*******************************************************************

	public List<WebElement> getFlightSectionDateText(){
		return txt_FlightSectionDate;
	}

	public List<WebElement> getFlightSectionFlightNumberText(){
		return txt_FlightSectionFlightNumber;
	}

	public List<WebElement> getFlightSectionDepartCityText(){
		return txt_FlightSectionDepartCity;
	}

	public List<WebElement> getFlightSectionAriveCityText(){
		return txt_FlightSectionAriveCity;
	}

	//*******************************************************************
	//*************************Passenger Section*************************
	//*******************************************************************

	public WebElement getPassengerHeaderTxt() { return txt_PassengerHeader; }

	public List<WebElement> getPassengerIconImg() { return img_PassengerIcon; }

	public List<WebElement> getPassengerSectionNamesText() {	return txt_PassengerSectionNames;}

	public List<WebElement> getPassengerFreeSpirirtNumberText() {	return txt_PassengerFreeSpiritNumber;}

	public List<WebElement> getBagsIcon() {	return img_BagsIcon;}

	public List<WebElement> getDepBagsTxt() { return txt_DepBags; }

	public List<WebElement> getRetBagsTxt() { return txt_RetBags; }

	public List<WebElement> getSeatsIcon() { return img_seatIcon;}

	public List<WebElement> getDepSeatsTxt() { return txt_DepSeats; }

	public List<WebElement> getRetSeatsTxt() { return txt_RetSeats; }

	public List<WebElement> getWheelChairServiceText() { return txt_WheelChairService;}

	public List<WebElement> getSpecialAssistanceText() { return txt_SpecialAssistance;}


	public WebElement getKTNparagraphText(){
		return txt_KTNparagraph;
	}

	public String PassengerKTN()
	{
		String ktn = txt_KTNparagraph.getText();

		return ktn.substring(ktn.indexOf(":")+1).trim();
	}


	public List<WebElement> getPassengerAdditionalInfo() { return txt_PassengerAdditionalInfo;}

	//*******************************************************************
	//*************************Travel Insurance**************************
	//*******************************************************************
	public WebElement getTravelInsurancePolicyNumber() { return txt_TravelInsurancePolicyNumber;}

	public WebElement getTravelInsurancePrimaryInsuredName() { return txt_TravelInsurancePrimaryInsuredName;}

	public WebElement getTravelInsuranceNoteText() { return txt_TravelInsuranceNoteText;}

	//*******************************************************************
	//***********************Contact information*************************
	//*******************************************************************

	public WebElement getContactHeaderTxt() { return txt_ContactHeader; }

	public WebElement getContactPersonName() {return txt_ContactPersonName;}

	public WebElement getContactPersonEmail() {return txt_ContactPersonEmail; }

	public WebElement getContactPersonPhoneNumber() {return txt_ContactPersonPhoneNumber; }

	public WebElement getContactPersonNote() {return txt_ContactPersonNote;}

	//*******************************************************************
	//*************************9FC Booking Amount************************
	//*******************************************************************
	public WebElement get9FCBookingSavingsAmount() {return txt_9FCBookingSavingsAmount;}

	public WebElement get9FCBookingSavingVerbageText(){
		return txt_9FCBookingSavingVerbage;
	}

	//*******************************************************************
	//*************************Travel Insurance**************************
	//*******************************************************************
	public WebElement getTravelInsuranceHeaderText() {
		return txt_TravelInsuranceHeader;
	}

	public List<WebElement> getTravelInsuranceSubHeaderText() {
		return txt_TravelInsuranceSubHeader;
	}

	public List<WebElement> getTravelInsuranceSubHeaderValuesText() {
		return txt_TravelInsuranceSubHeaderValues;
	}

	public WebElement getTravelInsurancePolicyOnlineLink() {
		return lnk_TravelInsurancePolicyOnline;
	}

	public WebElement getTravelInsuranceAdditionalQuestionsText() {
		return txt_TravelInsuranceAdditionalQuestions;
	}

	//*******************************************************************
	//*************************Options Section***************************
	//*******************************************************************
	public List<WebElement> getOptionSectionSelectedOptionsText() {
		return txt_OptionSectionSelectedOptions;
	}

	public WebElement getOptionSectionFlightFlexHeaderText() {
		return txt_OptionSectionFlightFlexHeader;
	}

	public WebElement getOptionSectionFlightFlexSubHeaderText() {
		return txt_OptionSectionFlightFlexSubHeader;
	}

	public WebElement getOptionSectionShortcutSecurityHeaderText() {
		return txt_OptionSectionShortcutSecurityHeader;
	}

	public WebElement getOptionSectionShortcutSecuritySubHeaderText() {
		return txt_OptionSectionShortcutSecuritySubHeader;
	}

	public WebElement getOptionSectionShortcutBoardingHeaderText() {
		return txt_OptionSectionShortcutBoardingHeader;
	}

	public WebElement getOptionSectionShortcutBoardingSubHeaderText() {
		return txt_OptionSectionShortcutBoardingSubHeader;
	}

	//*******************************************************************
	//*************************Total Due Amount**************************
	//*******************************************************************
	public WebElement getTotalPaidVerbiageLabel() {
		return lbl_TotalPaidVerbiage;
	}

	public WebElement getTotalPaidPriceText() {
		return txt_TotalPaidPrice;
	}

	public WebElement getPackagePricePaidlabel(){
		return lbl_PackagePricePaid;
	}
	public WebElement getTotalPaidBreakDownLink() {
		return lnk_TotalPaidBreakDown;
	}

	public WebElement getFlightVerbiageLabel() {return lbl_FlightVerbiage;    }
	public WebElement getTotalDepFlightPaidText() {return txt_TotalDepFlightPaid;    }

	public WebElement getTotalRetFlightPaidText() {return txt_TotalRetFlightPaid;    }

	public WebElement getBagsBreakdownLabel() {return lbl_BagsBreakdown;    }

	public WebElement getVatTaxAmountText() {return txt_VatTaxAmount;    }

	public WebElement getOptioinsVerbiageLabel() {
		return lbl_OptioinsVerbiage;
	}

	public WebElement getOptionsPriceText() {
		return txt_OptionsPrice;
	}

	public WebElement getOptionsBreakDownLink() {
		return lnk_OptionsBreakDown;
	}

	public WebElement get9FCClubMembershipVerbiageLabel() {
		return lbl_9FCClubMembershipVerbiage;
	}

	public WebElement get9FCClubMembershipPriceText() {
		return txt_9FCClubMembershipPrice;
	}

	public WebElement getTotalDueBundleDiscountText() {
		return txt_TotalDueBundleDiscount;
	}

	public WebElement getTotalDueBundleDiscountPriceText() {
		return txt_TotalDueBundleDiscountPrice;
	}

	public WebElement getTotalDueBoostDiscountText() {
		return txt_TotalDueBoostDiscount;
	}

	public WebElement getTotalDueBoostDiscountPriceText() {
		return txt_TotalDueBoostDiscountPrice;
	}

}