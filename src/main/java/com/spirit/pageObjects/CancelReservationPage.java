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
public class CancelReservationPage {
	
	public CancelReservationPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//*******************************************************************
	//****************************Cancel Header**************************
	//*******************************************************************
	public final String xpath_CancellationHeaderText = "//h1[contains(@class,'ng')]";
	@FindBy(xpath=xpath_CancellationHeaderText)
	private WebElement txt_CancellationHeader;

	public final String xpath_ConfirmationCodeVerbiageText = "//strong[contains(text(),'Confirmation Code') or contains(text(),'Clave de Confirmación')]";
	@FindBy(xpath=xpath_ConfirmationCodeVerbiageText)
	private WebElement txt_ConfirmationCodeVerbiage;

    public final String xpath_ConfirmationCodeText = "//strong[contains(text(),'Confirmation Code') or contains(text(),'Clave de Confirmación')]/../../following-sibling::div//strong";
	@FindBy(xpath=xpath_ConfirmationCodeText)
	private WebElement txt_ConfirmationCode;

    public final String xpath_CancellationSubHeaderText = "(//div[contains(@class,'card card-body ')])[2]//p";
	@FindBy(xpath=xpath_CancellationSubHeaderText)
	private List<WebElement> txt_CancellationSubHeader;

    public final String xpath_ReservationSummaryText = "//h2[contains(text(),'Reservation Summary') or contains(text(),'Resumen de la Reservación')]";
	@FindBy(xpath=xpath_ReservationSummaryText)
	private WebElement txt_ReservationSummary;
	
	//*******************************************************************
	//****************************TOTAL CREDIT***************************
	//*******************************************************************
    public final String xpath_TotalCreditVerbiageText = "//p[contains(text(),'Total Credit') or contains(text(),'Crédito Total')]";
	@FindBy(xpath=xpath_TotalCreditVerbiageText)
	private WebElement txt_TotalCreditVerbiage;

    public final String xpath_TotalCreditPriceText = "//div[@data-qa='total-cost']/p";
	@FindBy(xpath=xpath_TotalCreditPriceText)
	private WebElement txt_TotalCreditPrice;
	
	//*******************************************************************
	//**************************Credit Summary***************************
	//*******************************************************************
    public final String xpath_CreditSummaryText = "//h2[contains(text(),'Credit Summary') or contains(text(),'Resumen de Crédito')]";
	@FindBy(xpath=xpath_CreditSummaryText)
	private WebElement txt_CreditSummary;

    public final String xpath_CreditSummaryHeaderText = "//h2/../../following-sibling::div[2]/section/div//strong";
	@FindBy(xpath=xpath_CreditSummaryHeaderText)
	private List<WebElement> txt_CreditSummaryHeader;

    public final String xpath_CreditSummaryValuesTypeText = "(//h2/../../following-sibling::div[2]/section/div[2]//div)[1]";
	@FindBy(xpath = xpath_CreditSummaryValuesTypeText)
	private WebElement txt_CreditSummaryValuesType;

    public final String xpath_CreditSummaryValuesValidThruText = "(//h2/../../following-sibling::div[2]/section/div[2]//div)[2]";
	@FindBy(xpath = xpath_CreditSummaryValuesValidThruText)
	private WebElement txt_CreditSummaryValuesValidThru;

    public final String xpath_CreditSummaryValuesAmountText = "(//h2/../../following-sibling::div[2]/section/div[2]//div)[3]";
	@FindBy(xpath = xpath_CreditSummaryValuesAmountText)
	private WebElement txt_CreditSummaryValuesAmount;

    public final String xpath_CreditSummaryVoucherNumberText = "((//h2)[1]/../../following-sibling::section/div/div/div[2])/div[1]";
	@FindBy(xpath = xpath_CreditSummaryVoucherNumberText)
	private WebElement txt_CreditSummaryVoucherNumber;

    public final String xpath_CreditSummaryVoucherValidThruText = "((//h2)[1]/../../following-sibling::section/div/div/div[2])/div[3]";
	@FindBy(xpath = xpath_CreditSummaryVoucherValidThruText)
	private WebElement txt_CreditSummaryVoucherValidThru;

    public final String xpath_CreditSummaryVoucherAmountText = "((//h2)[1]/../../following-sibling::section/div/div/div[2])/div[4]";
	@FindBy(xpath = xpath_CreditSummaryVoucherAmountText)
	private WebElement txt_CreditSummaryVoucherAmount;

	//*******************************************************************
	//**************************Cancel Footer****************************
	//*******************************************************************
    public final String xpath_CancelReservationButton = "//button[contains(text(),'Cancel Reservation') or contains(text(),'Cancelar Reservación')]";
	@FindBy(xpath=xpath_CancelReservationButton)
	private WebElement btn_CancelReservation;

    public final String xpath_DoNotCancelReservationButton = "//p[contains(text(),'Do Not Cancel Reservation') or contains(text(),'No Quiero Cancelar')]";
	@FindBy(xpath=xpath_DoNotCancelReservationButton)
	private WebElement btn_DoNotCancelReservation;

    public final String xpath_ContinueToHomePageButton = "//button[contains(text(),'Continue To Homepage') or contains(text(),'Continuar Al Inicio')]";
	@FindBy(xpath=xpath_ContinueToHomePageButton)
	private WebElement btn_ContinueToHomePage;
	
	//*******************************************************************
	//***********************Reservation Cancellation PopUp**************
	//*******************************************************************
    public final String xpath_ReservationCancellationPopUpHeaderText = "//h2[@class='modal-title']";
	@FindBy(xpath=xpath_ReservationCancellationPopUpHeaderText)
	private WebElement txt_ReservationCancellationPopUpHeader;

    public final String xpath_ReservationCancellationPopUpCloseButton = "//button[@class = 'close']";
	@FindBy(xpath=xpath_ReservationCancellationPopUpCloseButton)
	private WebElement btn_ReservationCancellationPopUpClose;

    public final String xpath_ReservationCancellationPopUpSubHeaderText = "//div[@class='modal-body']/div/div";
	@FindBy(xpath=xpath_ReservationCancellationPopUpSubHeaderText)
	private WebElement txt_ReservationCancellationPopUpSubHeader;

    public final String xpath_ReservationCancellationPopUpKeepMyReservationButton = "//button[contains(text(),'Keep My Reservation') or contains(text(),'Conservar mi Reservación')]";
	@FindBy(xpath=xpath_ReservationCancellationPopUpKeepMyReservationButton)
	private WebElement btn_ReservationCancellationPopUpKeepMyReservation;

    public final String xpath_ReservationCancellationPopUpCancelReservationButton = "//div[@class='modal-body']//button[contains(text(),'Cancel Reservation') or contains(text(),'Cancelar Reservación')]";
	@FindBy(xpath=xpath_ReservationCancellationPopUpCancelReservationButton)
	private WebElement btn_ReservationCancellationPopUpCancelReservation;

	//*******************************************************************
	//************Cancel Reservation Confirmation Code*******************
	//*******************************************************************
    public final String xpath_ReservationCancellationLearnHowToUseYourCreditLink = "//a[contains(text(), 'Learn how to use your credit')]";
	@FindBy(xpath=xpath_ReservationCancellationLearnHowToUseYourCreditLink)
	private WebElement lnk_ReservationCancellationLearnHowToUseYourCredit;

	//*******************************************************************
	//****************************Cancel Header**************************
	//*******************************************************************
	public WebElement getCancellationHeaderText() {
		return txt_CancellationHeader;
	}

	public WebElement getConfirmationCodeVerbiageText() {
		return txt_ConfirmationCodeVerbiage;
	}
	
	public WebElement getConfirmationCodeText() {
		return txt_ConfirmationCode;
	}
	
	public List<WebElement> getCancellationSubHeaderText() {
		return txt_CancellationSubHeader;
	}
	
	public WebElement getReservationSummaryText() {
		return txt_ReservationSummary;
	}

	//*******************************************************************
	//****************************TOTAL CREDIT***************************
	//*******************************************************************
	public WebElement getTotalCreditVerbiageText() {
		return txt_TotalCreditVerbiage;
	}
	
	public WebElement getTotalCreditPriceText() {
		return txt_TotalCreditPrice;
	}

	//*******************************************************************
	//**************************Credit Summary***************************
	//*******************************************************************
	public WebElement getCreditSummaryText() {
		return txt_CreditSummary;
	}
	
	public List<WebElement> getCreditSummaryHeaderText() {
		return txt_CreditSummaryHeader;
	}

	public WebElement getCreditSummaryValuesTypeText() {
		return txt_CreditSummaryValuesType;
	}

	public WebElement getCreditSummaryValuesValidThruText() {
		return txt_CreditSummaryValuesValidThru;
	}

	public WebElement getCreditSummaryValesAmountText() {
		return txt_CreditSummaryValuesAmount;
	}

	public WebElement getCreditSummaryVoucherNumberText(){
		return txt_CreditSummaryVoucherNumber;
	}

	public WebElement getCreditSummaryVoucherValidThruText(){
		return txt_CreditSummaryVoucherValidThru;
	}

	public WebElement getCreditSummaryVoucherAmountText(){
		return txt_CreditSummaryVoucherAmount;
	}

	//*******************************************************************
	//**************************Cancel Footer****************************
	//*******************************************************************
	public WebElement getCancelReservationButton() {
		return btn_CancelReservation;
	}
	
	public WebElement getDoNotCancelReservationButton() {
		return btn_DoNotCancelReservation;
	}
	
	public WebElement getContinueToHomePageButton() {
		return btn_ContinueToHomePage;
	}

	//*******************************************************************
	//***********************Reservation Cancellation PopUp**************
	//*******************************************************************
	public WebElement getReservationCancellationPopUpHeaderText() {
		return txt_ReservationCancellationPopUpHeader;
	}

	public WebElement getReservationCancellationPopUpCloseButton() {return btn_ReservationCancellationPopUpClose;}

	public WebElement getReservationCancellationPopUpSubHeaderText() {
		return txt_ReservationCancellationPopUpSubHeader;
	}
	
	public WebElement getReservationCancellationPopUpKeepMyReservationButton() {
		return btn_ReservationCancellationPopUpKeepMyReservation;
	}
	
	public WebElement getReservationCancellationPopUpCancelReservationButton() {
		return btn_ReservationCancellationPopUpCancelReservation;
	}

	//*******************************************************************
	//************Cancel Reservation Confirmation Code*******************
	//*******************************************************************
	public WebElement getReservationCancellationLearnHowToUseYourCreditLink() {return lnk_ReservationCancellationLearnHowToUseYourCredit;}

}
