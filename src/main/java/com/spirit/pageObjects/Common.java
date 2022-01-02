package com.spirit.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Common {

	public Common(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*******************************************************************
	//****************Common Xpath Used across application***************
	//*******************************************************************
	public final String xpath_PopupBlockPanel = "//div[@class='modal-dialog']";
	@FindBy(xpath=xpath_PopupBlockPanel)
	private List<WebElement> pnl_PopupBlock;

	//*******************************************************************
	//*******************Error Message Enrollment Page*******************
	//*******************************************************************
	public final String xpath_ErrorMessageLabel = "//div[contains(@class,'s-error-text') or contains(@class,'toast-error')]";
	@FindBy(xpath=xpath_ErrorMessageLabel)
	private WebElement lbl_ErrorMessage;

	public final String xpath_AlertMessageLabel = "//div[contains(@class,'alert alert-danger')]";
	@FindBy(xpath=xpath_AlertMessageLabel)
	private WebElement lbl_AlertMessage;

	public final String xpath_TriangleAlertMessageLabel = "//i[@class='icon-exclamation-triangle']/..";
	@FindBy(xpath=xpath_TriangleAlertMessageLabel)
	private WebElement lbl_TriangleAlertMessage;

	//*******************************************************************
	//****************Header Toast-Container(Red I-Block)****************
	//*******************************************************************
	public final String xpath_IBlockVerbiageText = "//div[@id='toast-container']/div/div";
	@FindBy(xpath=xpath_IBlockVerbiageText)
	private WebElement txt_IBlockVerbiage;

	public final String xpath_IBlockCloseLabel = "//div[@id='toast-container']//button";
	@FindBy(xpath=xpath_IBlockCloseLabel)
	private WebElement btn_IBlockClose;

	//*******************************************************************
	//**************************Session Out Popup************************
	//*******************************************************************
	public final String xpath_SessionOutPopupLogoImage = "//div[@class='modal-body']//img";
	@FindBy(xpath=xpath_SessionOutPopupLogoImage)
	private WebElement img_SessionOutPopupLogo;

	public final String xpath_SessionOutPopupVerbiageText = "//div[@class='modal-body']//p";
	@FindBy(xpath=xpath_SessionOutPopupVerbiageText)
	private WebElement txt_SessionOutPopupVerbiage;

	public final String xpath_SessionOutPopupButton = "//div[@class='modal-body']//button";
	@FindBy(xpath=xpath_SessionOutPopupButton)
	private WebElement btn_SessionOutPopupButton;

	//*******************************************************************
	//**********************Return To Homepage Popup*********************
	//*******************************************************************
	public final String xpath_ReturnToHomePageVerbiageText = "//button[contains(text(),'Return To Homepage')]/../../p";
	@FindBy(xpath=xpath_ReturnToHomePageVerbiageText)
	private WebElement txt_ReturnToHomePageVerbiage;

	public final String xpath_ReturnToHomePageButton = "//button[contains(text(),'Return To Homepage')]";
	@FindBy(xpath=xpath_ReturnToHomePageButton)
	private WebElement btn_ReturnToHomePage;

	//*******************************************************************
	//**************************Retrive Password Page********************
	//*******************************************************************

	public final String xpath_PopOverWindowPanel = "//ngb-popover-window";
	@FindBy(xpath = xpath_PopOverWindowPanel)
	private WebElement pnl_PopOverWindow;

	public final String xpath_PopOverWindowBodyText = "//ngb-popover-window//div[@class='popover-body']//p";
	@FindBy(xpath = xpath_PopOverWindowBodyText)
	private WebElement txt_PopOverWindowBody;

	//*******************************************************************
	//**************************Pop Over Alert***************************
	//*******************************************************************

	public final String xpath_PopOverCloseIcon = "//a[@class='icon-close popover-circle']";
	@FindBy(xpath = xpath_PopOverCloseIcon)
	private WebElement icn_PopOverClose;

	//*******************************************************************
	//**************************Calander Section*************************
	//*******************************************************************

	public final String xpath_CalanderYearText = "//table[@class='years']//td";
	@FindBy(xpath = xpath_CalanderYearText)
	private List<WebElement> txt_CalanderYear;

	public final String xpath_CalanderMonthText = "//table[@class='months']//td";
	@FindBy(xpath = xpath_CalanderMonthText)
	private List<WebElement> txt_CalanderMonth;

	public final String xpath_CalanderCurrentYearText = "//button[@class='current']";
	@FindBy(xpath = xpath_CalanderCurrentYearText)
	private WebElement txt_CalanderCurrentYear;

	public final String xpath_CalanderDaysText = "//table[@class='days weeks']//td/span[not(@class='is-other-month')]";
	@FindBy(xpath = xpath_CalanderDaysText)
	private List<WebElement> txt_CalanderDays;

	public final String xpath_CalanderNextButton = "//button[@class='next']";
	@FindBy(xpath = xpath_CalanderNextButton)
	private WebElement btn_CalanderNext;

	//*******************************************************************
	//**************************Tool Tip*********************************
	//*******************************************************************
	public final String xpath_ToolTipWindowBodyText = "(//ngb-popover-window//a)[1]/..";
	@FindBy(xpath = xpath_ToolTipWindowBodyText)
	private WebElement txt_ToolTipWindowBody;

	public final String xpath_ToolTipWindowMoreInformationLink = "(//ngb-popover-window//a)[1]";
	@FindBy(xpath = xpath_ToolTipWindowMoreInformationLink)
	private WebElement lnk_ToolTipWindowMoreInformation;


	//*******************************************************************************************************************************************
	//******************************************************Start getter Methods of Activity Page****************************************************
	//*******************************************************************************************************************************************


	public List<WebElement> getPopupBlockPanel() {
		return pnl_PopupBlock;
	}
	//*******************************************************************
	//*******************Error Message Enrollment Page*******************
	//*******************************************************************
	public WebElement getErrorMessageLabel() {
		return lbl_ErrorMessage;
	}

	public WebElement getAlertMessageLabel() {
		return lbl_AlertMessage;
	}

	public WebElement getTriangleAlertMessagLabel() {
		return lbl_TriangleAlertMessage;
	}



	//*******************************************************************
	//****************Header Toast-Container(Red I-Block)****************
	//*******************************************************************
	public WebElement getIBlockVerbiageText() {
		return txt_IBlockVerbiage;
	}

	public WebElement getIBlockCloseButton() {
		return btn_IBlockClose;
	}

	//*******************************************************************
	//**************************Session Out Popup************************
	//*******************************************************************
	public WebElement getSessionOutPopupLogoImage() {
		return img_SessionOutPopupLogo;
	}

	public WebElement getSessionOutPopupVerbiageText() {
		return txt_SessionOutPopupVerbiage;
	}

	public WebElement getSessionOutPopupButton() {
		return btn_SessionOutPopupButton;
	}

	//*******************************************************************
	//**********************Return To Homepage Popup*********************
	//*******************************************************************
	public WebElement getReturnToHomePageVerbiageText() {
		return txt_ReturnToHomePageVerbiage;
	}

	public WebElement getReturnToHomePageButton() {
		return btn_ReturnToHomePage;
	}

	//*******************************************************************
	//**************************Retrive Password Page********************
	//*******************************************************************
	public WebElement getPopOverWindow(){
		return pnl_PopOverWindow;
	}

	public WebElement getPopOverWindowBodyText(){
		return txt_PopOverWindowBody;
	}

	//*******************************************************************
	//**************************Pop Over Alert***************************
	//*******************************************************************

	public WebElement getPopOverCloseIcon(){
		return icn_PopOverClose;
	}

	//*******************************************************************
	//**************************Calander Section Method******************
	//*******************************************************************

	public List<WebElement> getCalanderYearText(){
		return txt_CalanderYear;
	}

	public List<WebElement> getCalanderMonthText(){
		return txt_CalanderMonth;
	}

	public WebElement getCalanderCurrentYearText(){
		return txt_CalanderCurrentYear;
	}

	public List<WebElement> getCalanderDaysText(){
		return txt_CalanderDays;
	}

	public WebElement geCalanderNextButton(){
		return btn_CalanderNext;
	}

	//*******************************************************************
	//**************************Tool Tip Method**************************
	//*******************************************************************

	public WebElement getToolTipWindowBodyText(){
		return txt_ToolTipWindowBody;
	}

	public WebElement getToolTipWindowMoreInformationLink(){
		return lnk_ToolTipWindowMoreInformation;
	}

}