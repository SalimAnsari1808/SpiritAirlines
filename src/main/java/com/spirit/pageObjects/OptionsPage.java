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

public class OptionsPage {

	public OptionsPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public final String xpath_OptionsPageHeaderText = "//h1[contains(text(),'Add On Trip Options') or contains(text(),'Opciones De Servicios Adicionales')]";
	@FindBy(xpath=xpath_OptionsPageHeaderText)
	private WebElement txt_OptionsPageHeader;

	public final String xpath_OptionsPageDescriptionText = "//app-options-page//p[contains(text(),'Aproveche') or contains(text(),'Make the most of your getaway')]";
	@FindBy(xpath=xpath_OptionsPageDescriptionText)
	private WebElement txt_OptionsPageDescription;

	//*******************************************************************
	//*******************Ancillaries Header Options Page*****************
	//*******************************************************************
	public final String xpath_OptionsHeaderForAncillariesText = "//app-extras-simple//h3[contains(text(),'Options') or contains(text(),'Opciones')]";
	@FindBy(xpath=xpath_OptionsHeaderForAncillariesText)
	private WebElement txt_OptionsHeaderForAncillaries; //header ontop of Flight Flex , ShortCut Security, and Shortcut Boarding

	//*******************************************************************
	//*******************Flight Flex Options Page************************
	//*******************************************************************
	public final String xpath_FlightFlexCardPanel = "//div[@data-qa='options-flight-flex']";
	@FindBy(xpath=xpath_FlightFlexCardPanel)
	private WebElement pnl_FlightFlexCard;

	public final String xpath_FlightFlexCardTitleText = "//div[@data-qa='options-flight-flex']//div[contains(@class,'headline3')]";
	@FindBy(xpath=xpath_FlightFlexCardTitleText)
	private WebElement txt_FlightFlexCardTitle;

	public final String xpath_FlightFlexCardBodyText = "//div[@data-qa='options-flight-flex']//div[@class='options-text']";
	@FindBy(xpath=xpath_FlightFlexCardBodyText)
	private WebElement txt_FlightFlexCardBody;

	public final String xpath_FlightFlexCardIconImage = "//div[@data-qa='options-flight-flex']//i[contains(@class,'icon-flight-flex')]";
	@FindBy(xpath=xpath_FlightFlexCardIconImage)
	private WebElement img_FlightFlexCardIcon;

	public final String xpath_FlightFlexCardToolTipLink = "//div[@data-qa='options-flight-flex']//div[@class='options-text']//i";
	@FindBy(xpath=xpath_FlightFlexCardToolTipLink)
	private WebElement lnk_FlightFlexCardToolTip;

	public final String xpath_FlightFlexToolTipPopUpTitleText = "//app-branded-modal//h2";
	@FindBy(xpath=xpath_FlightFlexToolTipPopUpTitleText)
	private WebElement txt_FlightFlexToolTipPopUpTitle;

	public final String xpath_FlightFlexToolTipPopUpCloseButton = "//app-branded-modal//button[@class='close']";
	@FindBy(xpath=xpath_FlightFlexToolTipPopUpCloseButton)
	private WebElement btn_FlightFlexToolTipPopUpClose;

	public final String xpath_FlightFlexToolTipPopUpBodyText = "//app-branded-modal//h2//following::div//ul";
	@FindBy(xpath=xpath_FlightFlexToolTipPopUpBodyText)
	private WebElement txt_FlightFlexToolTipPopUpBody;

	public final String xpath_FlightFlexToolTipPopUpOkGotItButton = "//app-branded-modal//h2//following::div//button";
	@FindBy(xpath=xpath_FlightFlexToolTipPopUpOkGotItButton)
	private WebElement btn_FlightFlexToolTipPopUpOkGotIt;

	public final String xpath_FlightFlexCardPriceText = "//div[@data-qa='options-flight-flex']//div[contains(text(),'$')]";
	@FindBy(xpath=xpath_FlightFlexCardPriceText)
	private WebElement txt_FlightFlexCardPrice;

	public final String xpath_FlightFlexCardPerPersonText = "//div[@data-qa='options-flight-flex']//div[contains(text(),'Per person') or contains(text(),'Por Persona')]";
	@FindBy(xpath=xpath_FlightFlexCardPerPersonText)
	private WebElement txt_FlightFlexCardPerPerson;

	public final String xpath_FlightFlexCardAddButton = "//div[@data-qa='options-flight-flex']//button[contains(text(),'Add') or contains(text(), 'AÑADIR')]";
	@FindBy(xpath=xpath_FlightFlexCardAddButton)
	private WebElement btn_FlightFlexCardAdd;

	public final String xpath_FlightFlexCardGreyedPanel = "//app-extra-simple-col[@headertext='Flight Flex']/div";
	@FindBy(xpath=xpath_FlightFlexCardGreyedPanel)//when option added selected label shows
    private WebElement pnl_FlightFlexCardGreyed;

	public final String xpath_FlightFlexCardSelectedLabel = "//div[@data-qa='options-flight-flex']//div[@data-qa='options-selected-header']";
	@FindBy(xpath=xpath_FlightFlexCardSelectedLabel)//when option added selected label shows
	private WebElement lbl_FlightFlexCardSelected;

	public final String xpath_FlightFlexCardRemoveButton = "//div[@data-qa='options-flight-flex']//button[contains(text(),'Remove') or contains(text(),'QUITAR')]";
	@FindBy(xpath=xpath_FlightFlexCardRemoveButton)
	private WebElement btn_FlightFlexCardRemove;

	public final String xpath_FlightFlexCardNotAvailableText = "//div[@data-qa='options-flight-flex']//strong[contains(text(), 'Not Available') or contains(text(), 'no está disponible')]";
	@FindBy(xpath=xpath_FlightFlexCardNotAvailableText)
	private WebElement txt_FlightFlexCardNotAvailable;

	//*******************************************************************
	//**************Shortcut Security Options Page***********************
	//*******************************************************************
	public final String xpath_ShortCutSecurityCardPanel = "//div[@data-qa='options-shortcut-security']";
	@FindBy(xpath=xpath_ShortCutSecurityCardPanel)
	private WebElement pnl_ShortCutSecurityCard;

	public final String xpath_ShortCutSecurityCardTitleText = "//div[@data-qa='options-shortcut-security']//div[contains(@class,'headline3')]";
	@FindBy(xpath=xpath_ShortCutSecurityCardTitleText)
	private WebElement txt_ShortCutSecurityCardTitle;

	public final String xpath_ShortCutSecurityCardBodyText = "//div[@data-qa='options-shortcut-security']//div[@class='options-text']";
	@FindBy(xpath=xpath_ShortCutSecurityCardBodyText)
	private WebElement txt_ShortCutSecurityCardBody;

	public final String xpath_ShortCutSecurityCardIconImage = "//div[@data-qa='options-shortcut-security']//i[contains(@class,'icon-shortcut-security')]";
	@FindBy(xpath=xpath_ShortCutSecurityCardIconImage)
	private WebElement img_ShortCutSecurityCardIcon;

	public final String xpath_ShortCutSecurityCardToolTipLink = "//div[@data-qa='options-shortcut-security']//div[@class='options-text']//i";
	@FindBy(xpath=xpath_ShortCutSecurityCardToolTipLink)
	private WebElement lnk_ShortCutSecurityCardToolTip;

	public final String xpath_ShortCutSecurityCardPriceText = "//div[@data-qa='options-shortcut-security']//div[contains(text(),'$')]";
	@FindBy(xpath=xpath_ShortCutSecurityCardPriceText)
	private WebElement txt_ShortCutSecurityCardPrice;

	public final String xpath_ShortCutSecurityCardPerPersonText = "//div[@data-qa='options-shortcut-security']//div[contains(text(),'Per person') or contains(text(),'Por Persona')]";
	@FindBy(xpath=xpath_ShortCutSecurityCardPerPersonText)
	private WebElement txt_ShortCutSecurityCardPerPerson;

	public final String xpath_ShortCutSecurityCardAddButton = "//div[@data-qa='options-shortcut-security']//button[contains(text(),'Add') or contains(text(), 'AÑADIR')]";
	@FindBy(xpath=xpath_ShortCutSecurityCardAddButton)
	private WebElement btn_ShortCutSecurityCardAdd;

	public final String xpath_ShortCutSecurityCardChangeButton = "//div[@data-qa='options-shortcut-security']//button[contains(text(),'Change') or contains(text(), 'Cambiar')]";
	@FindBy(xpath=xpath_ShortCutSecurityCardChangeButton)
	private WebElement btn_ShortCutSecurityCardChange;

	//*******************************************************************
	//***********Shortcut Security Pop-up Options Page*******************
	//*******************************************************************
	public final String xpath_ShortCutSecurityPopUpHeaderText = "//div[@class='modal-header']/h2";
	@FindBy(xpath=xpath_ShortCutSecurityPopUpHeaderText)
	private WebElement txt_ShortCutSecurityPopUpHeader;

	public final String xpath_ShortCutSecurityPopUpCloseWindowButton = "//div[@class='modal-header']//i";
	@FindBy(xpath=xpath_ShortCutSecurityPopUpCloseWindowButton)
	private WebElement btn_ShortCutSecurityPopUpCloseWindow;

	public final String xpath_ShortCutSecurityPopUpSelectCityCheckBox = "//label[contains(@for,'option_')]";
	@FindBy(xpath=xpath_ShortCutSecurityPopUpSelectCityCheckBox)
	private List<WebElement> chkbx_ShortCutSecurityPopUpSelectCity;

	public final String xpath_ShortCutSecurityPopUpNotAvaiableText = "//div[contains(text(),'no disponible en') or contains(text(),'not available in')]";
	@FindBy(xpath=xpath_ShortCutSecurityPopUpNotAvaiableText)
	private WebElement txt_ShortCutSecurityPopUpNotAvaiable;

	public final String xpath_ShortCutSecurityPopUpAddButton = "//button[contains(text(),'Add Shortcut Security') or contains(text(),'Añadir Atajo de Seguridad')]";
	@FindBy(xpath=xpath_ShortCutSecurityPopUpAddButton)
	private WebElement btn_ShortCutSecurityPopUpAdd;

	public final String xpath_ShortCutSecurityCardSelectedLabel = "//div[@data-qa='options-shortcut-security']//div[@data-qa='options-selected-header']";
	@FindBy(xpath=xpath_ShortCutSecurityCardSelectedLabel)//when option added selected label shows
	private WebElement lbl_ShortCutSecurityCardSelected;

	public final String xpath_ShortCutSecurityCardRemoveButton = "//div[@data-qa='options-shortcut-security']//button[contains(text(),'Change') or contains(text(),'Cambiar')]";
	@FindBy(xpath=xpath_ShortCutSecurityCardRemoveButton)
	private WebElement btn_ShortCutSecurityCardRemove;

	//*******************************************************************
	//***************Shortcut Boarding Options Page***********************
	//*******************************************************************
	public final String xpath_ShortCutBoardingCardPanel = "//div[@data-qa='options-shortcut-boarding']";
	@FindBy(xpath=xpath_ShortCutBoardingCardPanel)
	private WebElement pnl_ShortCutBoardingCard;

	public final String xpath_ShortCutBoardingCardTitleText = "//div[@data-qa='options-shortcut-boarding']//div[contains(@class,'headline3')]";
	@FindBy(xpath=xpath_ShortCutBoardingCardTitleText)
	private WebElement txt_ShortCutBoardingCardTitle;

	public final String xpath_ShortCutBoardingCardBodyText = "//div[@data-qa='options-shortcut-boarding']//div[@class='options-text']";
	@FindBy(xpath=xpath_ShortCutBoardingCardBodyText)
	private WebElement txt_ShortCutBoardingCardBody;

	public final String xpath_ShortCutBoardingCardIconImage = "//div[@data-qa='options-shortcut-boarding']//i[contains(@class,'icon-shortcut-boarding')]";
	@FindBy(xpath=xpath_ShortCutBoardingCardIconImage)
	private WebElement img_ShortCutBoardingCardIcon;

	public final String xpath_ShortCutBoardingCardPricePrice = "//div[@data-qa='options-shortcut-boarding']//div[contains(text(),'$')]";
	@FindBy(xpath=xpath_ShortCutBoardingCardPricePrice)
	private WebElement txt_ShortCutBoardingCardPrice;

	public final String xpath_ShortCutBoardingCardPerPersonText = "//div[@data-qa='options-shortcut-boarding']//div[contains(text(),'Per person each way') or contains(text(),'Por persona cada vía')]";
	@FindBy(xpath=xpath_ShortCutBoardingCardPerPersonText)
	private WebElement txt_ShortCutBoardingCardPerPerson;

	public final String xpath_ShortCutBoardingCardAddButton = "//div[@data-qa='options-shortcut-boarding']//button[contains(text(),'Add') or contains(text(),'AÑADIR')]";
	@FindBy(xpath=xpath_ShortCutBoardingCardAddButton)
	private WebElement btn_ShortCutBoardingCardAdd;

	public final String xpath_ShortCutBoardingCardSelectedLabel = "//div[@data-qa='options-shortcut-boarding']//div[@data-qa='options-selected-header']";
	@FindBy(xpath=xpath_ShortCutBoardingCardSelectedLabel)//when option added selected label shows
	private WebElement lbl_ShortCutBoardingCardSelected;

	public final String xpath_ShortCutBoardingCardRemoveButton = "//div[@data-qa='options-shortcut-boarding']//button[contains(text(),'Remove') or contains(text(),'QUITAR')]";
	@FindBy(xpath=xpath_ShortCutBoardingCardRemoveButton)
	private WebElement btn_ShortCutBoardingCardRemove;

	//*******************************************************************
	//*******************Check-in Options Page***************************
	//*******************************************************************
	public final String xpath_CheckInOptionCardPanel = "//div[contains(@class,'card-body check-in')]//div[contains(@class,'headline3')]/../../..";
	@FindBy(xpath=xpath_CheckInOptionCardPanel)
	private WebElement pnl_CheckInOptionCard;

	public final String xpath_CheckInOptionCardTitleText = "//div[contains(@class,'card-body check-in')]//div[contains(@class,'headline3')]";
	@FindBy(xpath=xpath_CheckInOptionCardTitleText)
	private WebElement txt_CheckInOptionCardTitle;

	public final String xpath_CheckInOptionCardBodyText = "//div[contains(@class,'card-body check-in')]//div[contains(text(),'$$$')]";
	@FindBy(xpath=xpath_CheckInOptionCardBodyText)
	private WebElement txt_CheckInOptionCardBody;

	public final String xpath_CheckInOptionCardIconImage = "//div[contains(@class,'card-body check-in')]//i[contains(@class,'icon-check-in')]";
	@FindBy(xpath=xpath_CheckInOptionCardIconImage)
	private WebElement img_CheckInOptionCardIcon;

	public final String xpath_CheckInOptionCardBodySelectLabel = "//div[contains(@class,'card-body check-in')]//label[@for='checkInSelect']";
	@FindBy(xpath=xpath_CheckInOptionCardBodySelectLabel)
	private WebElement lbl_CheckInOptionCardBodySelect;

	public final String xpath_CheckInOptionCardBodySelectDropDown = "//select[@id='checkInSelect']";
	@FindBy(xpath=xpath_CheckInOptionCardBodySelectDropDown)
	private WebElement drpdwn_CheckInOptionCardBodySelect;

	public final String xpath_CheckInOptionCardNotApplicablePeruText = "//div[contains(@class,'card-body check-in')]//strong";
	@FindBy(xpath=xpath_CheckInOptionCardNotApplicablePeruText)
	private WebElement txt_CheckInOptionCardNotApplicablePeru;

	public final String xpath_CheckInOptionCardSelectedLabel = "//div[contains(@class,'card-body check-in')]/../div/p";
	@FindBy(xpath=xpath_CheckInOptionCardSelectedLabel) //when option added selected label shows
	private WebElement lbl_CheckInOptionCardSelected;

	public final String xpath_CheckInOptionErrorText = "//div[contains(@class,'s-error-text')]";
	@FindBy(xpath=xpath_CheckInOptionErrorText)
	private WebElement txt_CheckInOptionError;

	//*******************************************************************
	//*******************Options Total Options Page**********************
	//*******************************************************************
	public final String xpath_OptionTotalContainerText = "//div[contains(@class,'total-due-container')]//p[contains(text(),'Options Total') or contains(text(),'Total de Adicionales')]";
	@FindBy(xpath=xpath_OptionTotalContainerText)
	private WebElement txt_OptionTotalContainer;

	public final String xpath_OptionTotalContainerAmountTotalText = "//div[contains(@class,'total-due-container')]//p[contains(text(),'$')]";
	@FindBy(xpath=xpath_OptionTotalContainerAmountTotalText)
	private WebElement txt_OptionTotalContainerAmountTotal;

	public final String xpath_OptionTotalContainerDropDownDropDown = "//div[contains(@class,'total-due-container')]//app-chevron//i";
	@FindBy(xpath=xpath_OptionTotalContainerDropDownDropDown)
	private WebElement drpdwn_OptionTotalContainerDropDown;

	public final String xpath_OptionTotalContainerFlightFlexText = "//app-breakdown-section[@data-qa='flex']//p[1]";
	@FindBy(xpath=xpath_OptionTotalContainerFlightFlexText)
	private WebElement txt_OptionTotalContainerFlightFlex;

	public final String xpath_OptionTotalContainerFlightFlexAmountText = "(//app-breakdown-section[@data-qa='flex']//p)[3]";
	@FindBy(xpath=xpath_OptionTotalContainerFlightFlexAmountText)
	private WebElement txt_OptionTotalContainerFlightFlexAmount;

	public final String xpath_OptionTotalContainerShortcutBoardingText = "//app-breakdown-section[@data-qa='shortcut-boarding']//p[1]";
	@FindBy(xpath=xpath_OptionTotalContainerShortcutBoardingText)
	private WebElement txt_OptionTotalContainerShortcutBoarding;

	public final String xpath_OptionTotalContainerShortcutBoardingAmountText = "(//app-breakdown-section[@data-qa='shortcut-boarding']//p)[3]";
	@FindBy(xpath=xpath_OptionTotalContainerShortcutBoardingAmountText)
	private WebElement txt_OptionTotalContainerShortcutBoardingAmount;

	//*******************************************************************
	//*******************Options Total Options Page**********************
	//*******************************************************************
	public final String xpath_OptionTotalHotelBreakdownPriceText = "//div[@class='total-breakdown']//app-breakdown-section[contains(@ng-reflect-header,'Hotel')])//p[contains(text(),'$')]";
	@FindBy(xpath= xpath_OptionTotalHotelBreakdownPriceText)
	private WebElement txt_OptionTotalHotelBreakdownPrice;

	public final String xpath_OptionTotalCarBreakdownPriceText = "//div[@class='total-breakdown']//app-breakdown-section[contains(@ng-reflect-header,'Car')]//p[contains(text(),'$')]";
	@FindBy(xpath=xpath_OptionTotalCarBreakdownPriceText)
	private WebElement txt_OptionTotalCarBreakdownPrice;

	public final String xpath_OptionTotalFlightFlexBreakdownPriceText = "//div[@class='total-breakdown']//app-breakdown-section[contains(@ng-reflect-header,'Flight Flex')]//p[contains(text(),'$')]";
	@FindBy(xpath=xpath_OptionTotalFlightFlexBreakdownPriceText)
	private WebElement txt_OptionTotalFlightFlexBreakdownPrice;

	public final String xpath_OptionTotalShortCutSecurityBreakdownPriceText = "//div[@class='total-breakdown']//app-breakdown-section[@ng-reflect-header='Shortcut Security']//p[contains(text(),'$')]";
	@FindBy(xpath=xpath_OptionTotalShortCutSecurityBreakdownPriceText)
	private WebElement txt_OptionTotalShortCutSecurityBreakdownPrice;

	public final String xpath_OptionTotalShortCutBoardingBreakdownPriceText = "//div[@class='total-breakdown']//app-breakdown-section[@ng-reflect-header='Shortcut Boarding']//p[contains(text(),'$')]";
	@FindBy(xpath=xpath_OptionTotalShortCutBoardingBreakdownPriceText)
	private WebElement txt_OptionTotalShortCutBoardingBreakdownPrice;

	public final String xpath_OptionTotalCheckInBreakdownPriceText = "//div[@class='total-breakdown']//app-breakdown-section[@ng-reflect-header='Check-In']//p[contains(text(),'$')]";
	@FindBy(xpath=xpath_OptionTotalCheckInBreakdownPriceText)
	private WebElement txt_OptionTotalCheckInBreakdownPrice;

	//*******************************************************************
	//***************Selected Hotel Section******************************
	//*******************************************************************

	public final String xpath_HotelContainerHeaderText = "//app-hotel//h3[contains(text(),'Your Hotel') or contains(text(), 'Su Hotel')]";
	@FindBy(xpath = xpath_HotelContainerHeaderText)
	private WebElement txt_HotelContainerHeaderText;

	public final String xpath_HotelContainerSelectedText = "//app-hotel//app-selected-ancillary-item//p[contains(text(), 'Selected') or contains(text(), 'Seleccionado')]";
	@FindBy(xpath = xpath_HotelContainerSelectedText)
	private WebElement txt_HotelContainerSelectedText;

	public final String xpath_SelectedHotelNameText = "//app-selected-ancillary-item//h5";
	@FindBy(xpath = xpath_SelectedHotelNameText)
	private WebElement txt_SelectedHotelName;

	public final String xpath_SelectedHotelAddressText = "//app-selected-ancillary-item//h5//following::p[1]";
	@FindBy(xpath = xpath_SelectedHotelAddressText)
	private WebElement txt_SelectedHotelAddress;

	public final String xpath_SelectedHotelRemoveButton = "(//app-selected-ancillary-item//button[contains(text(),'Remove') or contains(text(),'Quitar')])[2]";
	@FindBy(xpath = xpath_SelectedHotelRemoveButton)
	private WebElement btn_SelectedHotelRemoveButton;

	//*******************************************************************
	//***************Selected Car Section******************************
	//*******************************************************************
	public final String xpath_CarContainerSelectedText = "//app-car//app-selected-ancillary-item//p[contains(text(), 'Selected') or contains(text(), 'Seleccionado')]";
	@FindBy(xpath = xpath_CarContainerSelectedText)
	private WebElement txt_CarContainerSelectedText;

	public final String xpath_SelectedCarHeaderText = "//app-car//h3[contains(text(), 'Your Ride') or contains(text(), 'Su Auto')]";
	@FindBy(xpath =  xpath_SelectedCarHeaderText)
	private WebElement txt_SelectedCarHeaderText;

	public final String xpath_SelectedCarEditButton = "//app-car//button";
	@FindBy(xpath =  xpath_SelectedCarEditButton)
	private WebElement btn_SelectedCarEdit;

	//*******************************************************************
	//***************Continue Button Passenger Page**********************
	//*******************************************************************
	public final String xpath_ContinueToPurchaseButton = "//button[contains(text(),'Continue') or contains(text(),'Continuar')]";
	@FindBy(xpath=xpath_ContinueToPurchaseButton)
	private WebElement btn_ContinueToPurchase;

	public final String xpath_ContinueButtonOnCheckInPathButton = "//button[contains(text(),'Continue') or contains(text(),'Continuar')]";
	@FindBy(xpath=xpath_ContinueButtonOnCheckInPathButton)
	private WebElement btn_ContinueButtonOnCheckInPath;

	public final String xpath_CancelButtonOnCheckInPathButton = "//button[contains(text(),'Cancel') or contains(text(),'Cancelar')]";
	@FindBy(xpath=xpath_CancelButtonOnCheckInPathButton)
	private WebElement btn_CancelButtonOnCheckInPath;

	//************************************************************************************************************************************************
	//******************************************************Start getter Methods of Options Page******************************************************
	//************************************************************************************************************************************************
	public WebElement getOptionsPageHeaderText() {
		return txt_OptionsPageHeader;
	}

	public WebElement getOptionsPageDescriptionText() {
		return txt_OptionsPageDescription;
	}

	//*******************************************************************
	//*******************Ancillaries Header Options Page*****************
	//*******************************************************************
	public WebElement getOptionsHeaderForAncillariesText() {
		return txt_OptionsHeaderForAncillaries;
	}
	//*******************************************************************
	//*******************Flight Flex Options Page************************
	//*******************************************************************
	public WebElement getFlightFlexCardPanel() {return pnl_FlightFlexCard;}

	public WebElement getFlightFlexCardTitleText() {
		return txt_FlightFlexCardTitle;
	}

	public WebElement getFlightFlexCardBodyText() {
		return txt_FlightFlexCardBody;
	}

	public WebElement getFlightFlexCardIconImage() {
		return img_FlightFlexCardIcon;
	}

	public WebElement getFlightFlexCardToolTipLink() {return lnk_FlightFlexCardToolTip;}

	public WebElement getFlightFlexToolTipPopUpTitleText() {return txt_FlightFlexToolTipPopUpTitle;}

	public WebElement getFlightFlexToolTipPopUpCloseButton() {return btn_FlightFlexToolTipPopUpClose;}

	public WebElement getFlightFlexToolTipPopUpBodyText() {return txt_FlightFlexToolTipPopUpBody;}

	public WebElement getFlightFlexToolTipPopUpOkGotItButton() {return btn_FlightFlexToolTipPopUpOkGotIt;}

	public WebElement getFlightFlexCardPriceText() {
		return txt_FlightFlexCardPrice;
	}

	public WebElement getFlightFlexCardPerPersonText() {
		return txt_FlightFlexCardPerPerson;
	}

	public WebElement getFlightFlexCardAddButton() {
		return btn_FlightFlexCardAdd;
	}

	public WebElement getFlightFlexCardGreyedPanel() { return pnl_FlightFlexCardGreyed;	}

	public WebElement getFlightFlexCardSelectedLabel() {
		return lbl_FlightFlexCardSelected;
	}

	public WebElement getFlightFlexCardRemoveButton() {
		return btn_FlightFlexCardRemove;
	}

	public WebElement getFlightFlexCardNotAvailableText() { return txt_FlightFlexCardNotAvailable; }
	//*******************************************************************
	//**************Shortcut Security Options Page***********************
	//*******************************************************************
	public WebElement getShortCutSecurityCardPanel() {return pnl_ShortCutSecurityCard;}

	public WebElement getShortCutSecurityCardTitleText() {
		return txt_ShortCutSecurityCardTitle;
	}

	public WebElement getShortCutSecurityCardBodyText() {
		return txt_ShortCutSecurityCardBody;
	}

	public WebElement getShortCutSecurityCardIconImage() {
		return img_ShortCutSecurityCardIcon;
	}

	public WebElement getShortCutSecurityCardToolTipLink() {
		return lnk_ShortCutSecurityCardToolTip;
	}

	public WebElement getShortCutSecurityCardPriceText() {
		return txt_ShortCutSecurityCardPrice;
	}

	public WebElement getShortCutSecurityCardPerPersonText() {
		return txt_ShortCutSecurityCardPerPerson;
	}

	public WebElement getShortCutSecurityCardAddButton() {
		return btn_ShortCutSecurityCardAdd;
	}

	public WebElement getShortCutSecurityCardChangeButton() { return btn_ShortCutSecurityCardChange; }

	//*******************************************************************
	//***********Shortcut Security Pop-up Options Page*******************
	//*******************************************************************
	public WebElement getShortCutSecurityPopUpHeaderText() {
		return txt_ShortCutSecurityPopUpHeader;
	}

	public WebElement getShortCutSecurityPopUpCloseWindowButton() {
		return btn_ShortCutSecurityPopUpCloseWindow;
	}

	public List<WebElement> getShortCutSecurityPopUpSelectCityCheckBox() {
		return chkbx_ShortCutSecurityPopUpSelectCity;
	}

	public WebElement getShortCutSecurityPopUpNotAvaiableText() {
		return txt_ShortCutSecurityPopUpNotAvaiable;
	}


	public WebElement getShortCutSecurityPopUpAddButton() {
		return btn_ShortCutSecurityPopUpAdd;
	}

	public WebElement getShortCutSecurityCardSelectedLabel() {
		return lbl_ShortCutSecurityCardSelected;
	}

	public WebElement getShortCutSecurityCardRemoveButton() {
		return btn_ShortCutSecurityCardRemove;
	}
	//*******************************************************************
	//***************Shortcut Boarding Options Page**********************
	//*******************************************************************
	public WebElement getShortCutBoardingCardPanel() {return pnl_ShortCutBoardingCard;}

	public WebElement getShortCutBoardingCardTitleText() {
		return txt_ShortCutBoardingCardTitle;
	}

	public WebElement getShortCutBoardingCardBodyText() {
		return txt_ShortCutBoardingCardBody;
	}

	public WebElement getShortCutBoardingCardIcon() {
		return img_ShortCutBoardingCardIcon;
	}

	public WebElement getShortCutBoardingCardPriceText() {
		return txt_ShortCutBoardingCardPrice;
	}

	public WebElement getShortCutBoardingCardPerPersonText() {
		return txt_ShortCutBoardingCardPerPerson;
	}

	public WebElement getShortCutBoardingCardAddButton() {
		return btn_ShortCutBoardingCardAdd;
	}

	public WebElement getShortCutBoardingCardSelectedLabel() {
		return lbl_ShortCutBoardingCardSelected;
	}

	public WebElement getShortCutBoardingCardRemoveButton() {
		return btn_ShortCutBoardingCardRemove;
	}
	//*******************************************************************
	//*******************Check-in Options Page***************************
	//*******************************************************************
	public WebElement getCheckInOptionCardPanel() {
		return pnl_CheckInOptionCard;
	}

	public WebElement getCheckInOptionCardTitleText() {
		return txt_CheckInOptionCardTitle;
	}

	public WebElement getCheckInOptionCardBodyText() {
		return txt_CheckInOptionCardBody;
	}

	public WebElement getCheckInOptionCardIcon() {
		return img_CheckInOptionCardIcon;
	}

	public WebElement getCheckInOptionCardBodySelectLabel() {
		return lbl_CheckInOptionCardBodySelect;
	}

	public WebElement getCheckInOptionCardBodySelectDropDown() {
		return drpdwn_CheckInOptionCardBodySelect;
	}

	public WebElement getCheckInOptionCardNotApplicablePeruText() {
		return txt_CheckInOptionCardNotApplicablePeru;
	}

	public WebElement getCheckInOptionCardSelectedLabel() {
		return lbl_CheckInOptionCardSelected;
	}

	public WebElement getCheckInOptionErrorText() {
		return txt_CheckInOptionError;
	}
	//*******************************************************************
	//*******************Options Total Options Page**********************
	//*******************************************************************
	public WebElement getOptionTotalContainerText() {
		return txt_OptionTotalContainer;
	}

	public WebElement getOptionTotalContainerAmountTotalText() {
		return txt_OptionTotalContainerAmountTotal;
	}

	public WebElement getOptionTotalContainerDropDown() {
		return drpdwn_OptionTotalContainerDropDown;
	}

	public WebElement getOptionTotalContainerFlightFlexText() {
		return txt_OptionTotalContainerFlightFlex;
	}

	public WebElement getOptionTotalContainerFlightFlexAmountText() {
		return txt_OptionTotalContainerFlightFlexAmount;
	}

	public WebElement getOptionTotalContainerShortcutBoardingText() {
		return txt_OptionTotalContainerShortcutBoarding;
	}

	public WebElement getOptionTotalContainerShortcutBoardingAmountText() {
		return txt_OptionTotalContainerShortcutBoardingAmount;
	}

	//*******************************************************************
	//*******************Options Total Options Page**********************
	//*******************************************************************
	public WebElement getOptionTotalHotelBreakdownPriceText() {
		return txt_OptionTotalHotelBreakdownPrice;
	}

	public WebElement getOptionTotalCarBreakdownPriceText() {
		return txt_OptionTotalCarBreakdownPrice;
	}

	public WebElement getOptionTotalFlightFlexBreakdownPriceText() {
		return txt_OptionTotalFlightFlexBreakdownPrice;
	}

	public WebElement getOptionTotalShortCutSecurityBreakdownPriceText() {
		return txt_OptionTotalShortCutSecurityBreakdownPrice;
	}

	public WebElement getOptionTotalShortCutBoardingBreakdownPriceText() {
		return txt_OptionTotalShortCutBoardingBreakdownPrice;
	}

	public WebElement getOptionTotalCheckInBreakdownPriceText() {
		return txt_OptionTotalCheckInBreakdownPrice;
	}


	//*******************************************************************
	//***************Selected Hotel Section Method***********************
	//*******************************************************************

	public WebElement getHotelContainerHeaderText() {
		return txt_HotelContainerHeaderText;
	}
	public WebElement getHotelContainerSelectedText() {
		return txt_HotelContainerSelectedText;
	}

	public WebElement getSelectedHotelNameText(){
		return txt_SelectedHotelName;
	}

	public WebElement getSelectedHotelAddressText(){
		return txt_SelectedHotelAddress;
	}

	public WebElement getSelectedHotelRemoveButton(){
		return btn_SelectedHotelRemoveButton;
	}

	//*******************************************************************
	//***************Selected Car Section Method***********************
	//*******************************************************************
	public WebElement getCarContainerSelectedText() {
		return txt_CarContainerSelectedText;
	}

	public WebElement getSelectedCarHeaderText() {return txt_SelectedCarHeaderText;}

	public WebElement getSelectedCarEditButton() {return btn_SelectedCarEdit;}

	//*******************************************************************
	//***************Continue Button Passenger Page**********************
	//*******************************************************************
	public WebElement getContinueToPurchaseButton() {
		return btn_ContinueToPurchase;
	}

	public WebElement getContinueButtonOnCheckInPathButton() {
		return btn_ContinueButtonOnCheckInPath;
	}

	public WebElement getCancelButtonOnCheckInPathButton() {
		return btn_CancelButtonOnCheckInPath;
	}
}