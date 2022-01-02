package com.spirit.pageObjects;


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

public class Footer {

	public Footer(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//********************************************************************
	//*************************Footer Section*****************************
	//********************************************************************

	public final String xpath_GetToKnowUsArrowIcon = "//h4[text()='Get To Know Us ' or text()='Conózcanos ']/app-chevron";
	@FindBy(xpath = xpath_GetToKnowUsArrowIcon)
	private WebElement icn_GetGetToKnowUsArrow;

	public final String xpath_FreeSpiritLink = "//div[contains(@class,'foot')]//a[contains(text(),'Free Spirit')]";
	@FindBy(xpath = xpath_FreeSpiritLink)
	private List<WebElement> lnk_FreeSpirit;

	public final String xpath_NineFareClubLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Fare Club')]";
	@FindBy(xpath = xpath_NineFareClubLink)
	private WebElement lnk_NineFareClub;

	public final String xpath_MediaCenterLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'media center')  or contains(text(),'Sala de Medios')]";
	@FindBy(xpath = xpath_MediaCenterLink)
	private WebElement lnk_MediaCenter;

	public final String xpath_InvestorRelationsLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Investor Relations')  or contains(text(),'Inversionistas')]";
	@FindBy(xpath = xpath_InvestorRelationsLink)
	private WebElement lnk_InvestorRelations;

	public final String xpath_CareersLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Careers')  or contains(text(),'Oportunidades Laborales')]";
	@FindBy(xpath = xpath_CareersLink)
	private WebElement lnk_Careers;

	public final String xpath_AboutUsLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'About Us')  or contains(text(),'Acerca de Nosotros')]";
	@FindBy(xpath = xpath_AboutUsLink)
	private WebElement lnk_AboutUs;

	public final String xpath_HelpCenterLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Help Center')  or contains(text(),'Centro de Ayuda')]";
	@FindBy(xpath = xpath_HelpCenterLink)
	private WebElement lnk_HelpCenter;

	public final String xpath_ContactUsLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Contact Us')  or contains(text(),'Comuníquese con nosotros')]";
	@FindBy(xpath = xpath_ContactUsLink)
	private WebElement lnk_ContactUs;

	public final String xpath_LegalLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Legal')]";
	@FindBy(xpath = xpath_LegalLink)
	private WebElement lnk_Legal;

	public final String xpath_PrivacyPolicyLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Privacy Policy')  or contains(text(),'Política de Privacidad')]";
	@FindBy(xpath = xpath_PrivacyPolicyLink)
	private WebElement lnk_PrivacyPolicy;

	public final String xpath_ContractOfCarriageLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Contract of Carriage')  or contains(text(),'Contrato de Transporte')]";
	@FindBy(xpath = xpath_ContractOfCarriageLink)
	private WebElement lnk_ContractOfCarriage;

	public final String xpath_TermacDelayPlanLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Tarmac Delay Plan')  or contains(text(),'Plan para Demoras en la Pista')]";
	@FindBy(xpath = xpath_TermacDelayPlanLink)
	private WebElement lnk_TermacDelayPlan;

	public final String xpath_BookLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Book')  or contains(text(),'Reservar')]";
	@FindBy(xpath = xpath_BookLink)
	private WebElement lnk_Book;

	public final String xpath_TravelAgentLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Travel Agent')  or contains(text(),'Agentes de Viajes')]";
	@FindBy(xpath = xpath_TravelAgentLink)
	private WebElement lnk_TravelAgent;

	public final String xpath_GroupTravelLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Group Travel')  or contains(text(),'Viajes en grupo')]";
	@FindBy(xpath = xpath_GroupTravelLink)
	private WebElement lnk_GroupTravel;

	public final String xpath_WhereWeFlyLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Where We Fly')  or contains(text(),'Destinos')]";
	@FindBy(xpath = xpath_WhereWeFlyLink)
	private WebElement lnk_WhereWeFly;

	public final String xpath_DealsLink = "//div[contains(@class,'footDesktop')]//a[contains(text(),'Deals')  or contains(text(),'Ofertas')]";
	@FindBy(xpath = xpath_DealsLink)
	private WebElement lnk_Deals;

	//********************************************************************
	//*************************Footer World Mastercard********************
	//********************************************************************

	public final String xpath_MasterCardImage = "//img[contains(@alt,'Spirit Airlines World Mastercard')]";
	@FindBy(xpath = xpath_MasterCardImage)
	private WebElement img_MasterCard;

	public final String xpath_MasterCardUpToBonusMilesText = "//img[contains(@alt,'Spirit Airlines World Mastercard')]/../../following-sibling::div/p//strong";
	@FindBy(xpath = xpath_MasterCardUpToBonusMilesText)
	private WebElement txt_MasterCardUpToBonusMiles;

	public final String xpath_MasterCardApplyNowButton = "//img[contains(@alt,'Spirit Airlines World Mastercard')]/../../following-sibling::div/button";
	@FindBy(xpath = xpath_MasterCardApplyNowButton)
	private WebElement btn_MasterCardApplyNow;

	//********************************************************************
	//*****************************Connect With Us************************
	//********************************************************************
	public final String xpath_ConnectWithUsText = "//h3[contains(text(),' Connect With Us') or contains(text(),'Conéctese con nosotros')]";
	@FindBy(xpath = xpath_ConnectWithUsText)
	private WebElement txt_ConnectWithUs;

	public final String xpath_SocialMediaImageLink = "//div[contains(@id,'social-nav-container')]//a";
	@FindBy(xpath = xpath_SocialMediaImageLink)
	private List<WebElement> lnk_SocialMediaImage;

	//********************************************************************
	//*****************************Legel Page*****************************
	//********************************************************************
	public final String xpath_LegalGeneralTermsLink = "//a[contains(text(),'General Terms and Conditions') or contains(text(),'Términos y Condiciones Generales')]";
	@FindBy(xpath = xpath_LegalGeneralTermsLink)
	private WebElement lnk_LegalGeneralTerms;

	public final String xpath_LegalPrivacyPolicyLink = "//div[@class='legal-page']//a[contains(text(),'Privacy Policy') or contains(text(),'Política de Privacidad')]";
	@FindBy(xpath = xpath_LegalPrivacyPolicyLink)
	private WebElement lnk_LegalPrivacyPolicy;

	public final String xpath_LegalContactOfCarriageLink = "//div[@class='legal-page']//a[contains(text(),'Contract of Carriage') or contains(text(),'Política de Privacidad')]";
	@FindBy(xpath = xpath_LegalContactOfCarriageLink)
	private WebElement lnk_LegalContactOfCarriage;

	public final String xpath_LegalFreeSpiritTermsConditionsLink = "//div[@class='legal-page']//a[contains(text(),'Free Spirit Terms and Conditions') or contains(text(),'Términos y Condiciones de FREE SPIRIT ®')]";
	@FindBy(xpath = xpath_LegalFreeSpiritTermsConditionsLink)
	private WebElement lnk_LegalFreeSpiritTermsConditions;

	public final String xpath_LegalFareClubTermsConditionsLink = "//div[@class='legal-page']//a[contains(text(),'$9 Fare Club Terms and Conditions') or contains(text(),'Términos y Condiciones del $9 Fare Club')]";
	@FindBy(xpath = xpath_LegalFareClubTermsConditionsLink)
	private WebElement lnk_LegalFareClubTermsConditions;

	public final String xpath_LegalGeneralTermsConditionsLink = "//div[@class='legal-page']//a[contains(text(),'General Terms and Conditions') or contains(text(),'Términos y Condiciones Generales')]";
	@FindBy(xpath = xpath_LegalGeneralTermsConditionsLink)
	private WebElement lnk_LegalGeneralTermsConditions;

	//********************************************************************
	//***************Legel Page Privacy Policy pdf************************ These Links only work for Firefox
	//********************************************************************
	public final String xpath_LegalPrivacyPolicyPDFEmailSignUpPageLink = "//a[contains(@title,'email-notify-sign-in')]";
	@FindBy(xpath=xpath_LegalPrivacyPolicyPDFEmailSignUpPageLink)
	private WebElement lnk_LegalPrivacyPolicyPDFEmailSignUpPage;

	public final String xpath_LegalPrivacyPolicyPDFHelpLink = "//a[contains(@title,'help')]";
	@FindBy(xpath=xpath_LegalPrivacyPolicyPDFHelpLink)
	private List<WebElement> lnk_LegalPrivacyPolicyPDFHelp;

	public final String xpath_LegalPrivacyPolicyPDFNetworkAdvertisingLink = "//a[contains(@title,'networkadvertising')]";
	@FindBy(xpath=xpath_LegalPrivacyPolicyPDFNetworkAdvertisingLink)
	private WebElement lnk_LegalPrivacyPolicyPDFNetworkAdvertising;

	//********************************************************************
	//***************Legel Page Contact Of Carriage pdf******************* These Links only work for Firefox
	//********************************************************************
	public final String xpath_LegalContactOfCarriagePDFForFurtherInformationLink = "(//a[contains(@title,'217154817')])[1]";
	@FindBy(xpath = xpath_LegalContactOfCarriagePDFForFurtherInformationLink)
	private WebElement lnk_LegalContactOfCarriagePDFForFurtherInformation;

	public final String xpath_LegalContactOfCarriagePDFOptionalServicesLink = "(//a[contains(@title,'/optional-services')])";
	@FindBy(xpath = xpath_LegalContactOfCarriagePDFOptionalServicesLink)
	private WebElement lnk_LegalContactOfCarriagePDFOptionalServices;

	public final String xpath_LegalContactOfCarriagePDFAdditionalInformationLink = "(//a[contains(@title,'202098626')])";
	@FindBy(xpath = xpath_LegalContactOfCarriagePDFAdditionalInformationLink)
	private WebElement lnk_LegalContactOfCarriagePDFAdditionalInformation;

	public final String xpath_LegalContactOfCarriagePDFMinimumSeatLink = "(//a[contains(@title,'202096')])";
	@FindBy(xpath = xpath_LegalContactOfCarriagePDFMinimumSeatLink)
	private WebElement lnk_LegalContactOfCarriagePDFMinimumSeat;

	public final String xpath_LegalContactOfCarriagePDFCustomerPropertyLink = "(//a[contains(@title,'21354')])";
	@FindBy(xpath = xpath_LegalContactOfCarriagePDFCustomerPropertyLink)
	private WebElement lnk_LegalContactOfCarriagePDFCustomerProperty;

	//********************************************************************
	//***************Legel Page FS Terms and Conditions pdf************** These Links only work for Firefox
	//********************************************************************
	public final String xpath_LegalFSTermsAndConditionPDFPrivacyPolicyLink = "(//a[contains(@title,'Privacy_Policy')])";
	@FindBy(xpath = xpath_LegalFSTermsAndConditionPDFPrivacyPolicyLink)
	private WebElement lnk_LegalFSTermsAndConditionPDFPrivacyPolicy;

	public final String xpath_LegalFSTermsAndConditionPDFSignUpLink = "(//a[contains(@title,'notify-sign-in')])";
	@FindBy(xpath = xpath_LegalFSTermsAndConditionPDFSignUpLink)
	private WebElement lnk_LegalFSTermsAndConditionPDFSignUp;

	//********************************************************************
	//***************Legel Page 9FC Terms and Conditions pdf************** These Links only work for Firefox
	//********************************************************************

	public final String xpath_LegalDFCTermsAndConditionPDFFreeSpiritProfileLink = "(//a[contains(@title,'FreeSpiritLogin')])";
	@FindBy(xpath = xpath_LegalDFCTermsAndConditionPDFFreeSpiritProfileLink)
	private WebElement lnk_LegalDFCTermsAndConditionPDFFreeSpiritProfile;

	public final String xpath_LegalDFCTermsAndConditionPDFSignUpHereLink = "(//a[contains(@title,'content.spirit.com/Shared')])";
	@FindBy(xpath = xpath_LegalDFCTermsAndConditionPDFSignUpHereLink)
	private WebElement lnk_LegalDFCTermsAndConditionPDFSignUpHere;

	//*****************************************************************************************
	//*************************Start getter Methods of Footer Page*****************************
	//*****************************************************************************************

	public WebElement getGetGetToKnowUsArrowIcon(){
		return icn_GetGetToKnowUsArrow;
	}

	public WebElement getFreeSpiritLink() {

		for(WebElement element:lnk_FreeSpirit){
			if(element.isDisplayed()){
				return element;
			}
		}

		return null;
	}

	public WebElement getNineFareClubLink() {
		return lnk_NineFareClub;
	}

	public WebElement getMediaCenterLink() {
		return lnk_MediaCenter;
	}

	public WebElement getInvestorRelationsLink() {
		return lnk_InvestorRelations;
	}

	public WebElement getCareersLink() {
		return lnk_Careers;
	}

	public WebElement getAboutUsLink() {
		return lnk_AboutUs;
	}

	public WebElement getHelpCenterLink() {
		return lnk_HelpCenter;
	}

	public WebElement getContactUsLink() {
		return lnk_ContactUs;
	}

	public WebElement getLegalLink() {
		return lnk_Legal;
	}

	public WebElement getPrivacyPolicyLink() {
		return lnk_PrivacyPolicy;
	}

	public WebElement getContractOfCarriageLink() {
		return lnk_ContractOfCarriage;
	}

	public WebElement getTermacDelayPlanLink() {
		return lnk_TermacDelayPlan;
	}

	public WebElement getBookLink() {
		return lnk_Book;
	}

	public WebElement getTravelAgentLink() {
		return lnk_TravelAgent;
	}

	public WebElement getGroupTravelLink() {
		return lnk_GroupTravel;
	}

	public WebElement getWhereWeFlyLink() {
		return lnk_WhereWeFly;
	}

	public WebElement getDealsLink() {
		return lnk_Deals;
	}

	//********************************************************************
	//*************************Footer World Mastercard*******************
	//********************************************************************
	public WebElement getMasterCardImage() {
		return img_MasterCard;
	}

	public WebElement getMasterCardUpToBonusMilesVerbiage() {
		return txt_MasterCardUpToBonusMiles;
	}

	public WebElement getMasterCardApplyNowButton() {
		return btn_MasterCardApplyNow;
	}

	//********************************************************************
	//*****************************Connect With Us************************
	//********************************************************************

	public WebElement getConnectWithUsText() {
		return txt_ConnectWithUs;
	}
	public List<WebElement> getSocialMediaImageLink() {
		return lnk_SocialMediaImage;
	}

	//********************************************************************
	//*****************************Legel Page*****************************
	//********************************************************************
	public WebElement getLegalGeneralTermsLink() {
		return lnk_LegalGeneralTerms;
	}

	public WebElement getLegalPrivacyPolicyLink() {
		return lnk_LegalPrivacyPolicy;
	}

	public WebElement getLegalContactOfCarriageLink() {
		return lnk_LegalContactOfCarriage;
	}

	public WebElement getLegalFreeSpiritTermsConditionsLink() {
		return lnk_LegalFreeSpiritTermsConditions;
	}

	public WebElement getLegalFareClubTermsConditionsLink() {
		return lnk_LegalFareClubTermsConditions;
	}

	public WebElement getLegalGeneralTermsConditionsLink() {
		return lnk_LegalGeneralTermsConditions;
	}

	//********************************************************************
	//***************Legel Page Privacy Policy pdf************************ These Links only work for Firefox
	//********************************************************************
	public WebElement getLegalPrivacyPolicyPDFEmailSignUpPageLink() {
		return lnk_LegalPrivacyPolicyPDFEmailSignUpPage;
	}

	public List<WebElement> getLegalPrivacyPolicyPDFHelpLink() {
		return lnk_LegalPrivacyPolicyPDFHelp;
	}

	public WebElement getLegalPrivacyPolicyPDFNetworkAdvertisingLink() {
		return lnk_LegalPrivacyPolicyPDFNetworkAdvertising;
	}

	//********************************************************************
	//***************Legel Page Contact Of Carriage pdf******************* These Links only work for Firefox
	//********************************************************************
	public WebElement getLegalContactOfCarriagePDFForFurtherInformationLink() {
		return lnk_LegalContactOfCarriagePDFForFurtherInformation;
	}
	public WebElement getLegalContactOfCarriagePDFOptionalServicesLink() {
		return lnk_LegalContactOfCarriagePDFOptionalServices;
	}
	public WebElement getLegalContactOfCarriagePDFAdditionalInformationLink() {
		return lnk_LegalContactOfCarriagePDFAdditionalInformation;
	}
	public WebElement getLegalContactOfCarriagePDFMinimumSeatLink() {
		return lnk_LegalContactOfCarriagePDFMinimumSeat;
	}

	public WebElement getLegalContactOfCarriagePDFCustomerPropertyLink() {
		return lnk_LegalContactOfCarriagePDFCustomerProperty;
	}

	//********************************************************************
	//***************Legel Page FS Terms and Conditionse pdf************** These Links only work for Firefox
	//********************************************************************
	public WebElement getLegalFSTermsAndConditionPDFPrivacyPolicyLink() {
		return lnk_LegalFSTermsAndConditionPDFPrivacyPolicy;
	}

	public WebElement getLegalFSTermsAndConditionPDFSignUpLink() {
		return lnk_LegalFSTermsAndConditionPDFSignUp;
	}

	//********************************************************************
	//***************Legel Page 9FC Terms and Conditions pdf************** These Links only work for Firefox
	//********************************************************************

	public WebElement getLegalDFCTermsAndConditionPDFFreeSpiritProfileLink() {
		return lnk_LegalDFCTermsAndConditionPDFFreeSpiritProfile;
	}

	public WebElement getLegalDFCTermsAndConditionPDFSignUpHereLink() {
		return lnk_LegalDFCTermsAndConditionPDFSignUpHere;
	}
}