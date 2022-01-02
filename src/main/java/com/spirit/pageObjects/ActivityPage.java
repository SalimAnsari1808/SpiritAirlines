package com.spirit.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
public class ActivityPage {

	public ActivityPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*******************************************************************
	//***********************Activity Page*******************************
	//*******************************************************************

	public final String xpath_ActivitiesCarouselTitleText = "//p[contains(text(),'Deals on Activities') or contains(text(),'Activities por menos')]";
	@FindBy(xpath=xpath_ActivitiesCarouselTitleText)
	private WebElement txt_ActivitiesCarouselTitle;
	
	public final String xpath_ActivityCarouselLeftButton = "//app-activity//i[contains(@class,'left')]";
	@FindBy(xpath=xpath_ActivityCarouselLeftButton)
	private WebElement btn_ActivityCarouselLeft;
	
	public final String xpath_ActivityCarouselRightButton = "//app-activity//i[contains(@class,'right')]";
	@FindBy(xpath=xpath_ActivityCarouselRightButton)
	private WebElement btn_ActivityCarouselRight;

	public final String xpath_ViewAllActivitiesLinkButton = "//button[contains(text(),'View All Activities') or contains(text(),'Ver todos los Hoteles')]";
	@FindBy(xpath=xpath_ViewAllActivitiesLinkButton)
	private WebElement lnkbtn_ViewAllActivities;
		
	//*******************************************************************
	//********Activities Card Container Options Page*********************
	//*******************************************************************
	//Activities Name
	public final String xpath_ActivitiesCardNameText = "//app-activity//app-ancillary-item//strong[@class='text-capitalize']";
	@FindBy(xpath=xpath_ActivitiesCardNameText)
	private List<WebElement> txt_ActivitiesCardName;

	public final String xpath_ActivitiesCardUpliftPricingText = "//app-activity//app-ancillary-item//app-uplift-pricing[@type='options-card']";
	@FindBy(xpath=xpath_ActivitiesCardUpliftPricingText)
	private List<WebElement> txt_ActivitiesCardUpliftPricing;
	
	//Activities Card Price
	public final String xpath_ActivitiesCardPriceLinkButton = "//app-activity//app-ancillary-item//p[contains(@class,'h4')]";
	@FindBy(xpath=xpath_ActivitiesCardPriceLinkButton)
	private List<WebElement> lnkbtn_ActivitiesCardPrice;
			
	//uplift price on Activities Card
	public final String xpath_ActivitiesCardUpLiftPriceLinkButton = "//app-activity//app-ancillary-item//span[@data-up-from-dollars='true']";
	@FindBy(xpath=xpath_ActivitiesCardUpLiftPriceLinkButton) //will only display is flight amount over $300
	private List<WebElement> lnkbtn_ActivitiesCardUpLiftPrice;
	
	//view Activities
	public final String xpath_ActivitiesCardViewLinkButton = "//app-activity//app-ancillary-item//a[contains(text(),'View') or contains(text(),'Ver')]";
	@FindBy(xpath=xpath_ActivitiesCardViewLinkButton)
	private List<WebElement> lnkbtn_ActivitiesCardView;
	
	//*******************************************************************
	//************Activity Page************************
	public final String xpath_LetsHaveSomeFunText = "//h1[contains(text(),'Let's Have Some Fun') or contains(text(),'¿A dónde se quedará?')]";
	@FindBy(xpath=xpath_LetsHaveSomeFunText)
	private WebElement txt_LetsHaveSomeFun;

	public final String xpath_ViewActivitiesButton = "//app-activity-list-item//button[contains(text(),'View') or contains(text(),'Ver')]";
	@FindBy(xpath=xpath_ViewActivitiesButton)
	private List<WebElement> btn_ViewActivities;

	public final String xpath_AllActivitiesLinkButton = "//a[contains(text(),'All activities') or contains(text(),'¿A dónde se quedará?')]";
	@FindBy(xpath=xpath_AllActivitiesLinkButton)
	private WebElement lnkbtn_AllActivities;

	public final String xpath_ContinueWithPurchaseButton = "//a[contains(text(),'Continue with purchase') or contains(text(),'¿A dónde se quedará?')]";
	@FindBy(xpath=xpath_ContinueWithPurchaseButton)
	private WebElement btn_ContinueWithPurchase;

	public final String xpath_ContinueWithoutActivityLinkButton = "//button[contains(text(),'Continue Without Activity') or contains(text(),'¿A dónde se quedará?')]";
	@FindBy(xpath=xpath_ContinueWithoutActivityLinkButton)
	private WebElement lnkbtn_ContinueWithoutActivity;
	
	//*******************************************************************
	//************Pick Activity Pop-Up Options Page**********************
	//*******************************************************************
	public final String xpath_ActivityPopUpExitIconLinkButton = "//app-package-detail-select-modal//button[@class='close']";
	@FindBy(xpath=xpath_ActivityPopUpExitIconLinkButton)
	private WebElement lnkbtn_ActivityPopUpExitIcon;

	public final String xpath_ActivityPopUpUpliftPricingText = "//app-package-detail-select-modal//app-uplift-pricing/div";
	@FindBy(xpath=xpath_ActivityPopUpUpliftPricingText)
	private WebElement txt_ActivityPopUpUpliftPricing;

	public final String xpath_ActivitySelectDateDropDown = "//div[@id='pricingAndBooking-panel']//input[@id='usageDate']";
	@FindBy(xpath=xpath_ActivitySelectDateDropDown)
	private WebElement drpdn_ActivitySelectDate;

	public final String xpath_ActivitySelectTicketAgeDropDown = "////div[@id='pricingAndBooking-panel']//select[@id='adultTickets']";
	@FindBy(xpath=xpath_ActivitySelectTicketAgeDropDown)
	private WebElement drpdn_ActivitySelectTicketAge;

	public final String xpath_ActivityPopUpHighlightsTabPanel = "//a[@id='highlights']";
	@FindBy(xpath=xpath_ActivityPopUpHighlightsTabPanel)
	private WebElement pnl_ActivityPopUpHighlightsTab;

	public final String xpath_ActivityPopUpGeneralInformationTab = "//a[@id='generalInformation']";
	@FindBy(xpath=xpath_ActivityPopUpGeneralInformationTab)
	private WebElement pnl_ActivityPopUpGeneralInformationTab;

	public final String xpath_ActivityPopUpPricingAndBookingTabPanel = "//a[@id='pricingAndBooking']";
	@FindBy(xpath=xpath_ActivityPopUpPricingAndBookingTabPanel)
	private WebElement pnl_ActivityPopUpPricingAndBookingTab;

	public final String xpath_BookActivityButton = "//button[contains(text(),'Book Activity') or contains(text(),'¿A dónde se quedará?')]";
	@FindBy(xpath=xpath_BookActivityButton)
	private WebElement btn_BookActivity;

	public final String xpath_ActivitySelectedPanel = "//app-selected-ancillary-item[@alt='Selected Activity']";
	@FindBy(xpath=xpath_ActivitySelectedPanel)//once a hotel is selected it will display selected panel
	private WebElement pnl_ActivitySelected;

	public final String xpath_ActivityRemoveLink = "//app-selected-ancillary-item[@alt='Selected Activity']//button";
	@FindBy(xpath=xpath_ActivityRemoveLink)
	private WebElement lnk_ActivityRemove;
	
	//*******************************************************************************************************************************************
	//******************************************************Start getter Methods of Activity Page****************************************************
	//*******************************************************************************************************************************************

	public WebElement getActivitiesCarouselTitleText() {
		return txt_ActivitiesCarouselTitle;
	}

	public WebElement getActivityCarouselLeftButton() {
		return btn_ActivityCarouselLeft;
	}

	public WebElement getActivityCarouselRightButton() {
		return btn_ActivityCarouselRight;
	}

	public WebElement getViewAllActivitiesLink() {
		return lnkbtn_ViewAllActivities;
	}
	//*******************************************************************
	//********Activities Card Container Options Page*********************
	//*******************************************************************
	public List<WebElement> getActivitiesCardNameText() {
		return txt_ActivitiesCardName;
	}
	
	public List<WebElement> getActivitiesCardUpliftPricingText(){
		return txt_ActivitiesCardUpliftPricing;
	}

	public List<WebElement> getActivitiesCardPriceLinkButton() {
		return lnkbtn_ActivitiesCardPrice;
	}

	public List<WebElement> getActivitiesCardUpLiftPriceLink() {
		return lnkbtn_ActivitiesCardUpLiftPrice;
	}
	
	public List<WebElement> getActivitiesCardViewLink() {
		return lnkbtn_ActivitiesCardView;
	}
	//*******************************************************************
	//************Activity Page******************************************
	public WebElement getLetsHaveSomeFunText() {
		return txt_LetsHaveSomeFun;
	}

	public List<WebElement> getViewActivitiesButton() {
		return btn_ViewActivities;
	}

	public WebElement getAllActivitiesButton() {
		return lnkbtn_AllActivities;
	}
	
	public WebElement getContinueWithPurchaseButton() {
		return btn_ContinueWithPurchase;
	}
	
	public WebElement getContinueWithoutActivityButton() {
		return lnkbtn_ContinueWithoutActivity;
	}	
	//*******************************************************************
	//************Pick Activity Pop-Up Options Page**********************
	//*******************************************************************
	public WebElement getActivityPopUpExitIconButton() {
		return lnkbtn_ActivityPopUpExitIcon;
	}
	
	public WebElement getActivityPopUpUpliftPricingText() {
		return txt_ActivityPopUpUpliftPricing;
	}

	public WebElement getActivitySelectDateDropDown() {
		return drpdn_ActivitySelectDate;
	}

	public WebElement getActivitySelectTicketAgeDropDown() {
		return drpdn_ActivitySelectTicketAge;
	}
	
	public WebElement getActivityPopUpHighlightsTab() {
		return pnl_ActivityPopUpHighlightsTab;
	}
	
	public WebElement getActivityPopUpGeneralInformationTab() {
		return pnl_ActivityPopUpGeneralInformationTab;
	}	

	public WebElement getActivityPopUpPricingAndBookingTab() {
		return pnl_ActivityPopUpPricingAndBookingTab;
	}

	public WebElement getBookActivityButton() {
		return btn_BookActivity;
	}

	public WebElement getActivitySelectedPanel() {
		return pnl_ActivitySelected;
	}
	
	public WebElement getActivityRemoveLink() {
		return lnk_ActivityRemove;	
	}
}
