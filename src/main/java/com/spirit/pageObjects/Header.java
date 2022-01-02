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

public class Header {

	public Header(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//*************************************************************************************
	//****************WebElement Before SignIn of Header Page******************************
	//*************************************************************************************

	public final String xpath_SpiritLogoImage = "//img[@class='logo']";
	@FindBy(xpath = xpath_SpiritLogoImage)
	private WebElement img_SpiritLogo;

	public final String xpath_SpiritLogoPanel = "//div[@class='header container']";
	@FindBy(xpath = xpath_SpiritLogoPanel)
	private WebElement pnl_SpiritLogo;

	public final String xpath_SignInLink = "//div[@class='desktop']//a[contains(text(),'Sign-In')  or contains(text(),'Ingresar')]";
	@FindBy(xpath = xpath_SignInLink)
	private WebElement lnk_SignIn;

	public final String xpath_HelpLink = "//div[@class='desktop']//a[contains(text(),'Help')  or contains(text(),'Ayuda')]";
	@FindBy(xpath = xpath_HelpLink)
	private WebElement lnk_Help;

	public final String xpath_ContactUsLink = "//div[@class='desktop']//a[contains(text(),'Contact Us')  or contains(text(),'Comuníquese con Nosotros')]";
	@FindBy(xpath = xpath_ContactUsLink)
	private WebElement lnk_ContactUs;

	public final String xpath_DealsLink = "//div[@class='desktop']//a[contains(text(),'Deals')  or contains(text(),'Ofertas')]";
	@FindBy(xpath = xpath_DealsLink)
	private WebElement lnk_Deals;

	public final String xpath_NineFareClubLink = "//div[@class='desktop']//a[contains(text(),'Fare Club')]";
	@FindBy(xpath = xpath_NineFareClubLink)
	private WebElement lnk_NineFareClub;

	public final String xpath_Spirit101Link = "//div[@class='desktop']//a[contains(text(),'Spirit 101')]";
	@FindBy(xpath = xpath_Spirit101Link)
	private WebElement lnk_Spirit101;

	public final String xpath_DestinationsLink = "//div[@class='desktop']//a[contains(text(),'Destinations')  or contains(text(),'Destinos')]";
	@FindBy(xpath = xpath_DestinationsLink)
	private WebElement lnk_Destinations;

	public final String xpath_EnglishSpanishLink = "//div[@class='desktop']//a[contains(text(),'English')  or contains(text(),'Español')]";
	@FindBy(xpath = xpath_EnglishSpanishLink)
	private WebElement lnk_EnglishSpanish;

	public final String xpath_BookLink = "//div[@class='desktop']//a[contains(text(),'Book')  or contains(text(),'Reservar')]";
	@FindBy(xpath = xpath_BookLink)
	private WebElement lnk_Book;

	public final String xpath_MyTripsLink = "//div[@class='desktop']//a[contains(text(),'My Trips')  or contains(text(),'Mis Viajes')]";
	@FindBy(xpath = xpath_MyTripsLink)
	private WebElement lnk_MyTrips;

	public final String xpath_CheckInLink = "//div[@class='desktop']//a[contains(text(),'Check-In')  or contains(text(),'Registrarme')]";
	@FindBy(xpath = xpath_CheckInLink)
	private WebElement lnk_CheckIn;

	public final String xpath_FlightStatusLink = "//div[@class='desktop']//a[contains(text(),'Flight Status')  or contains(text(),'Estado Del Vuelo')]";
	@FindBy(xpath = xpath_FlightStatusLink)
	private WebElement lnk_FlightStatus;

	//*************************************************************************************
	//****************WebElement after SignIn of Header Page*******************************
	//*************************************************************************************
	public final String xpath_UserDropDown = "//a[@aria-label='account profile' or @id='aria-basic-link']";
	@FindBy(xpath = xpath_UserDropDown)
	private List<WebElement> drpdwn_User;

	public final String xpath_YourMilesUserLink = "//div[@class='desktop']//a[contains(text(),'Your Miles:')  or contains(text(),'Sus Millas:')]";
	@FindBy(xpath = xpath_YourMilesUserLink)
	private WebElement lnk_YourMilesUser;

	public final String xpath_FreeSpiritUserLink = "//div[@class='desktop']//a[contains(text(),'Free Spirit #:')]";
	@FindBy(xpath = xpath_FreeSpiritUserLink)
	private WebElement lnk_FreeSpiritUser;

	public final String xpath_MyReservationUserLink = "//div[@class='desktop']//a[contains(text(),'My Reservations')  or contains(text(),'Mis Reservaciones')]";
	@FindBy(xpath = xpath_MyReservationUserLink)
	private WebElement lnk_MyReservationUser;

	public final String xpath_MyAccountUserLink = "//div[@class='desktop']//a[contains(text(),'My Account')  or contains(text(),'Mi Cuenta')]";
	@FindBy(xpath = xpath_MyAccountUserLink)
	private WebElement lnk_MyAccountUser;

	public final String xpath_ManageSubscriptionsLink = "//div[@class='desktop']//a[contains(text(),'Manage Subscriptions')  or contains(text(),'Manejar Suscripciones')]";
	@FindBy(xpath = xpath_ManageSubscriptionsLink)
	private WebElement lnk_ManageSubscriptions;

	public final String xpath_StatementUserLink = "//div[@class='desktop']//a[contains(text(),'Statement')  or contains(text(),'Estados de Cuenta')]";
	@FindBy(xpath = xpath_StatementUserLink)
	private WebElement lnk_StatementUser;

	public final String xpath_RequestMileageCreditUserLink = "//div[@class='desktop']//a[contains(text(),'Request Mileage Credit')  or contains(text(),'Solicitar Crédito de Millas')]";
	@FindBy(xpath = xpath_RequestMileageCreditUserLink)
	private WebElement lnk_RequestMileageCreditUser;

	public final String xpath_BuyMilesUserLink = "//div[@class='desktop']//a[contains(text(),'Buy Miles')  or contains(text(),'Comprar Millas')]";
	@FindBy(xpath = xpath_BuyMilesUserLink)
	private WebElement lnk_BuyMilesUser;

	public final String xpath_SignOutUserLink = "//a[contains(text(),'Sign Out')  or contains(text(),'Cerrar Sesión')]";
	@FindBy(xpath = xpath_SignOutUserLink)
	private List<WebElement> lnk_SignOutUser;

	//*************************************************************************************
	//*******************WebElement Itinerary of Header Page*******************************
	//*************************************************************************************

	public final String xpath_ItineraryPanel = "//div[contains(@class,'itinerary')]";
	@FindBy(xpath = xpath_ItineraryPanel)
	private WebElement pnl_Itinerary;

	public final String xpath_YourItineraryText = "//div[contains(text(),'Your Itinerary')  or contains(text(),'Su Itinerario')]";
	@FindBy(xpath = xpath_YourItineraryText)
	private WebElement txt_YourItinerary;

	public final String xpath_YouItineraryImage = "//div[contains(text(),'Your Itinerary')  or contains(text(),'Su Itinerario')]/i";
	@FindBy(xpath = xpath_YouItineraryImage)
	private WebElement img_YouItinerary;

	public final String xpath_YourItineraryPanel = "//div[contains(@class,'total-cost')]";
	@FindBy(xpath = xpath_YourItineraryPanel)
	private WebElement pnl_YourItinerary;

	public final String xpath_ItineraryTotalAmountText = "//div[contains(text(),'Your Itinerary')  or contains(text(),'Su Itinerario')]/following-sibling::div/div";
	@FindBy(xpath = xpath_ItineraryTotalAmountText)
	private WebElement txt_ItineraryTotalAmount;

	public final String xpath_ArrowYourItineraryImage = "//div[contains(text(),'Your Itinerary')  or contains(text(),'Su Itinerario')]/following-sibling::div/a";
	@FindBy(xpath = xpath_ArrowYourItineraryImage)
	private WebElement img_ArrowYourItinerary;

	public final String xpath_FlightItineraryBlockPanel = "//div[@data-qa='journey-flight-line']//p";
	@FindBy(xpath = xpath_FlightItineraryBlockPanel)
	private List<WebElement> pnl_FlightItineraryBlock;

	public final String xpath_FlightItineraryText = "//div[contains(@class,'itinerary')]//span[contains(text(),'Flight') or contains(text(),'Vuelo')]";
	@FindBy(xpath = xpath_FlightItineraryText)
	private WebElement txt_FlightItinerary;

	public final String xpath_FlightItineraryImage = "//div[contains(@class,'itinerary')]//span[contains(text(),'Flight') or contains(text(),'Vuelo')]/parent::div/i";
	@FindBy(xpath = xpath_FlightItineraryImage)
	private WebElement img_FlightItinerary;

	public final String xpath_FlightItineraryPanel = "//div[@data-qa='flight-cost-breakdown-expander']";
	@FindBy(xpath = xpath_FlightItineraryPanel)
	private WebElement pnl_FlightItinerary;

	public final String xpath_FlightPriceItineraryText = "//div[contains(@class,'itinerary')]//span[contains(text(),'Flight') or contains(text(),'Vuelo')]/parent::div/following-sibling::div/div";
	@FindBy(xpath =xpath_FlightPriceItineraryText)
	private WebElement txt_FlightPriceItinerary;

	public final String xpath_ArrowFlightItineraryImage = "//div[contains(@class,'itinerary')]//span[contains(text(),'Flight') or contains(text(),'Vuelo')]/parent::div/following-sibling::div/a";
	@FindBy(xpath = xpath_ArrowFlightItineraryImage)
	private WebElement img_ArrowFlightItinerary;

	public final String xpath_BagsItineraryText = "//div[contains(@class,'itinerary')]//div[contains(text(),'Bags ') or contains(text(),'Equipaje')]";
	@FindBy(xpath = xpath_BagsItineraryText)
	private WebElement txt_BagsItinerary;

	public final String xpath_BagsItineraryImage = "//div[contains(@class,'itinerary')]//div[contains(text(),'Bags ') or contains(text(),'Equipaje')]/i";
	@FindBy(xpath = xpath_BagsItineraryImage)
	private WebElement img_BagsItinerary;

	public final String xpath_BagsItineraryPanel = "//div[@data-qa='bags-cost-breakdown-expander']";
	@FindBy(xpath = xpath_BagsItineraryPanel)
	private WebElement pnl_BagsItinerary;

	public final String xpath_BagsPriceItineraryText = "//div[contains(@class,'itinerary')]//div[contains(text(),'Bags') or contains(text(),'Equipaje')]/following-sibling::div";
	@FindBy(xpath = xpath_BagsPriceItineraryText)
	private WebElement txt_BagsPriceItinerary;

	public final String xpath_ArrowBagsItineraryImage = "//div[contains(@class,'itinerary')]//div[contains(text(),'Bags') or contains(text(),'Equipaje')]/following-sibling::div/a";
	@FindBy(xpath = xpath_ArrowBagsItineraryImage)
	private WebElement img_ArrowBagsItinerary;

	public final String xpath_AirportCarryOnBagsSelectedItineraryText = "//div[@data-qa='bags-line']//p/span[1]";
	@FindBy(xpath = xpath_AirportCarryOnBagsSelectedItineraryText)
	private List<WebElement> txt_AirportCarryOnBagsSelectedItinerary;

	public final String xpath_AirportCheckInBagsSelectedItineraryText = "//div[@data-qa='bags-line']//p/span[2]";
	@FindBy(xpath = xpath_AirportCheckInBagsSelectedItineraryText)
	private List<WebElement> txt_AirportCheckInBagsSelectedItinerary;

	public final String xpath_SeatsItinerarayText = "//div[contains(@class,'itinerary')]//div[contains(text(),'Seats') or contains(text(),'Asientos')]";
	@FindBy(xpath = xpath_SeatsItinerarayText)
	private WebElement txt_SeatsItineraray;

	public final String xpath_SeatsItinerarayImage = "//div[contains(@class,'itinerary')]//div[contains(text(),'Seats') or contains(text(),'Asientos')]/i";
	@FindBy(xpath = xpath_SeatsItinerarayImage)
	private WebElement img_SeatsItineraray;

	public final String xpath_SeatsPriceItineraryText = "//div[contains(@class,'itinerary')]//div[contains(text(),'Seats') or contains(text(),'Asientos')]/following-sibling::div";
	@FindBy(xpath = xpath_SeatsPriceItineraryText)
	private WebElement txt_SeatsPriceItinerary;

	public final String xpath_ArrowSeatsItineraryImage = "//div[contains(@class,'itinerary')]//div[contains(text(),'Seats') or contains(text(),'Asientos')]/following-sibling::div/a";
	@FindBy(xpath = xpath_ArrowSeatsItineraryImage)
	private WebElement img_ArrowSeatsItinerary;

	public final String xpath_DepSeatFlightLegDetailsText = "(//div[@data-qa='seats-line'])[1]//span";
	@FindBy(xpath=xpath_DepSeatFlightLegDetailsText)
	private List<WebElement> txt_DepSeatFlightLegDetails;

	public final String xpath_RetSeatFlightLegDetailsText = "(//div[@data-qa='seats-line'])[2]//span";
	@FindBy(xpath=xpath_RetSeatFlightLegDetailsText)
	private List<WebElement> txt_RetSeatFlightLegDetails;

	public final String xpath_OptionsItineraryText = "//div[contains(@class,'itinerary')]//div[contains(text(),'Options') or contains(text(),'Opciones')]";
	@FindBy(xpath = xpath_OptionsItineraryText)
	private WebElement txt_OptionsItinerary;

	public final String xpath_OptionsItineraryImage = "//div[contains(@class,'itinerary')]//div[contains(text(),'Options') or contains(text(),'Opciones')]/i";
	@FindBy(xpath = xpath_OptionsItineraryImage)
	private WebElement img_OptionsItinerary;

	public final String xpath_OptionsPriceItineraryText = "//div[contains(@class,'itinerary')]//div[contains(text(),'Options') or contains(text(),'Opciones')]/following-sibling::div";
	@FindBy(xpath = xpath_OptionsPriceItineraryText)
	private WebElement txt_OptionsPriceItinerary;

	public final String xpath_ArrowOptionsItineraryImage = "//div[contains(@class,'itinerary')]//div[contains(text(),'Options') or contains(text(),'Opciones')]/following-sibling::div/a";
	@FindBy(xpath = xpath_ArrowOptionsItineraryImage)
	private WebElement img_ArrowOptionsItinerary;

	public final String xpath_OptionsSeatsItineraryImage = "//div[contains(@class,'itinerary')]//div[contains(text(),'Options') or contains(text(),'Opciones')]/following-sibling::div/a";
	@FindBy(xpath = xpath_OptionsSeatsItineraryImage)
	private WebElement img_OptionsSeatsItinerary;

	public final String xpath_ShortcutBoardingOptionPriceItineraryText = "//div[contains(@class,'itinerary')]//div[@data-qa='option-prices']//p[@data-qa='shortcut-boarding']";
	@FindBy(xpath = xpath_ShortcutBoardingOptionPriceItineraryText)
	private WebElement txt_ShortcutBoardingOptionPriceItinerary;

	//updated 12/1 -Sunny reverted back to old xpath
	//updated 11/21 from //div[contains(@class,'itinerary')]//div[@data-qa='option-labels']//p[@data-qa='shortcut-boarding']
	public final String xpath_ShortcutBoardingOptionItineraryLabel = "//div[contains(@class,'itinerary')]//div[@data-qa='option-labels']//p[@data-qa=('shortcut-boarding') or @data-qa=('shortcut-with-bag')]";
	//    public final String xpath_ShortcutBoardingOptionItineraryLabel = "//div[contains(@class,'itinerary')]//div[@data-qa='option-labels']//p[@data-qa='shortcut-boarding']";
	@FindBy(xpath = xpath_ShortcutBoardingOptionItineraryLabel)
	private WebElement lbl_ShortcutBoardingOptionItinerary;

	public final String xpath_FlightFlexOptionPriceItineraryText = "//div[contains(@class,'itinerary')]//div[@data-qa='option-prices']//p[@data-qa='flight-flex']";
	@FindBy(xpath = xpath_FlightFlexOptionPriceItineraryText)
	private WebElement txt_FlightFlexOptionPriceItinerary;

	public final String xpath_FlightFlexOptionItineraryLabel = "//div[contains(@class,'itinerary')]//div[@data-qa='option-labels']//p[@data-qa='flight-flex']";
	@FindBy(xpath = xpath_FlightFlexOptionItineraryLabel)
	private WebElement lbl_FlightFlexOptionItinerary;

	public final String xpath_ShortcutSecurityOptionPriceItineraryText = "//div[contains(@class,'itinerary')]//div[@data-qa='option-prices']//p[@data-qa='shortcut-security']";
	@FindBy(xpath = xpath_ShortcutSecurityOptionPriceItineraryText)
	private WebElement txt_ShortcutSecurityOptionPriceItinerary;

	public final String xpath_ShortcutSecurityOptiontineraryLabel = "//div[contains(@class,'itinerary')]//div[@data-qa='option-labels']//p[@data-qa='shortcut-security']";
	@FindBy(xpath = xpath_ShortcutSecurityOptiontineraryLabel)
	private WebElement lbl_ShortcutSecurityOptiontinerary;

	public final String xpath_JoinFareClubAndSaveItineraryText = "//div[contains(@class,'itinerary')]//div[contains(text(),'Fare Club and Save') or contains(text(),'Fare Club y ahorre')]";
	@FindBy(xpath = xpath_JoinFareClubAndSaveItineraryText)
	private WebElement txt_JoinFareClubAndSaveItinerary;

	public final String xpath_JoinFareClubAndSavePanel = "//div[contains(@class,'flight-club-savings')]";
	@FindBy(xpath = xpath_JoinFareClubAndSavePanel)
	private WebElement pnl_JoinFareClubAndSave;

	public final String xpath_JoinFareClubAndSaveCarrot = "//div[contains(@class,'flight-club-savings')]//a[1]";
	@FindBy(xpath = xpath_JoinFareClubAndSaveCarrot)
	private WebElement crt_JoinFareClubAndSave;

	public final String xpath_ArrowJoinFareClubAndSaveItineraryImage= "//div[contains(@class,'itinerary')]//div[contains(text(),'Fare Club and Save') or contains(text(),'Fare Club y ahorre')]/following-sibling::div//i";
	@FindBy(xpath = xpath_ArrowJoinFareClubAndSaveItineraryImage)
	private WebElement img_ArrowJoinFareClubAndSaveItinerary;

	public final String xpath_MemberTermsAndConditionItineraryLink = "//div[contains(@class,'itinerary')]//a[contains(text(),'Membership Terms and Conditions apply') or contains(text(),'Aplican Términos y Condiciones de la membresía')]";
	@FindBy(xpath = xpath_MemberTermsAndConditionItineraryLink)
	private WebElement lnk_MemberTermsAndConditionItinerary;

	public final String xpath_JoinNowAndSaveItineraryButton = "//div[contains(@class,'itinerary')]//button[contains(text(),'Join now and save') or contains(text(),'Inscríbase ahora y Ahorre')]";
	@FindBy(xpath = xpath_JoinNowAndSaveItineraryButton)
	private WebElement btn_JoinNowAndSaveItinerary;

	public final String xpath_MembershipDisclosureText = "//p[contains(text(),'Membership charges are non-refundable')]";
	@FindBy(xpath = xpath_MembershipDisclosureText)
	private WebElement txt_MembershipDisclosure;

	public final String xpath_MembershipCongratsVerbiageText = "//div[contains(text(),'Congrats!')]";
	@FindBy(xpath=xpath_MembershipCongratsVerbiageText)
	private WebElement txt_MembershipCongratsVerbiage;

	public final String xpath_MembershipVerbiageText = "//div/p[contains(text(),'$9 Fare Club Membership')]";
	@FindBy(xpath=xpath_MembershipVerbiageText)
	private WebElement txt_MembershipVerbiage;

	public final String xpath_MembershipAmountText = "//div/p[contains(text(),'$59.95')]";
	@FindBy(xpath=xpath_MembershipAmountText)
	private WebElement txt_MembershipAmount;

	public final String xpath_FlightDateItineraryText = "//div[@data-qa='journey-flight-line']//p[contains(@class,'headings-font-weight')]";
	@FindBy(xpath = xpath_FlightDateItineraryText)
	private List<WebElement> txt_FlightDateItinerary;

	public final String xpath_FareFlightItineraryText = "//div[@data-qa='journey-flight-line']//p[contains(text(),'$')]";
	@FindBy(xpath = xpath_FareFlightItineraryText)
	private List<WebElement> txt_FareFlightItinerary;

	public final String xpath_AirportFlightItineraryText = "//div[@data-qa='journey-flight-line']//p[contains(text(),'-')]";
	@FindBy(xpath = xpath_AirportFlightItineraryText)
	private List<WebElement> txt_AirportFlightItinerary;

	public final String xpath_EditFlightItineraryPanel = "//div[@data-qa='flight-cost-breakdown']//button[contains(text(),'Edit')]";
	@FindBy(xpath = xpath_EditFlightItineraryPanel)
	private WebElement btn_EditFlightItinerary;

	public final String xpath_EditBagsItneraryButton = "//div[@data-qa='bags-cost-breakdown']//button[contains(text(),'Edit')]";
	@FindBy(xpath = xpath_EditBagsItneraryButton)
	private WebElement btn_EditBagsItnerary;

	public final String xpath_AirportBagsItineraryText = "//div[@data-qa='bags-line']//p[contains(@class,'headings-font-weight')]";
	@FindBy(xpath = xpath_AirportBagsItineraryText)
	private List<WebElement> txt_AirportBagsItinerary;

	public final String xpath_PriceBagsItineraryText = "//div[@data-qa='bags-line']//p[contains(@class,'headings-font-weight')]/parent::div/following-sibling::div/p";
	@FindBy(xpath = xpath_PriceBagsItineraryText)
	private List<WebElement> txt_PriceBagsItinerary;

	public final String xpath_OtherOptionItineraryText = "//div[contains(@class,'itinerary')]//div[@data-qa='option-labels']/p";
	@FindBy(xpath = xpath_OtherOptionItineraryText)
	private List<WebElement> txt_OtherOptionItinerary;

	public final String xpath_OtherOptionPriceItineraryText = "//div[contains(@class,'itinerary')]//div[@data-qa='option-prices']/p";
	@FindBy(xpath = xpath_OtherOptionPriceItineraryText)
	private List<WebElement> txt_OtherOptionPriceItinerary;

	public final String xpath_EditSeatItneraryButton = "//div[@data-qa='seats-cost-breakdown-expander']//following::button[contains(text(),'Edit')]";
	@FindBy(xpath = xpath_EditSeatItneraryButton)
	private WebElement btn_EditSeatItnerary;

	public final String xpath_BareFareDiscountItineraryText = "//div[@data-qa='bundle-cost-breakdown']//span";
	@FindBy(xpath=xpath_BareFareDiscountItineraryText)
	private WebElement txt_BareFareDiscountItinerary;

	public final String xpath_BareFareDiscountPriceItineraryText = "//div[@data-qa='bundle-cost-breakdown']/div/div[2]";
	@FindBy(xpath=xpath_BareFareDiscountPriceItineraryText)
	private WebElement txt_BareFareDiscountPriceItinerary;

	public final String xpath_ArrowBareFareDiscountItineraryImage = "//div[@data-qa='bundle-cost-breakdown']//i";
	@FindBy(xpath=xpath_ArrowBareFareDiscountItineraryImage)
	private WebElement img_ArrowBareFareDiscountItinerary;

	//*******************************************************************************************************************************************
	//******************************************************Start Are you sure popup*************************************************************
	//*******************************************************************************************************************************************
	public final String xpath_AreYouSurePopUpPanel = "//app-flight-edit-disclaimer-modal";
	@FindBy(xpath = xpath_AreYouSurePopUpPanel)
	private WebElement pnl_AreYouSurePopUp;

	public final String xpath_AreYouSurePopUpCloseImage = "//app-flight-edit-disclaimer-modal//button[@class='close']";
	@FindBy(xpath = xpath_AreYouSurePopUpCloseImage)
	private WebElement img_AreYouSurePopUpClose;

	//****************************************************************************************************************************
	//******************************************************Bread Cum*************************************************************
	//****************************************************************************************************************************
//	public final String xpath_BreadCumListText = "(//app-book-path//div[contains(@class,'breadcrumbs-booking')])[2]//span";
	public final String xpath_BreadCumListText = "(//app-book-path//div[contains(@class,'breadcrumbs-booking')])[2]//p[not (contains(@class,'ng'))]";
	@FindBy(xpath = xpath_BreadCumListText)
	private List<WebElement> txt_BreadCumList;

	public final String xpath_BreadCumCheckMarkImage = "(//app-book-path//div[contains(@class,'breadcrumbs-booking')])[2]//i/..";
	@FindBy(xpath = xpath_BreadCumCheckMarkImage)
	private List<WebElement> img_BreadCumCheckMark;

	public final String xpath_BreadCumActiveStepNumberText = "(//app-book-path//div[contains(@class,'breadcrumbs-booking')])[2]//div[@class='booking-bread-crumbs active-step']";
	@FindBy(xpath = xpath_BreadCumActiveStepNumberText)
	private WebElement txt_BreadCumActiveStepNumber;

	//*******************************************************************************************************************************************
	//******************************************************Start getter Methods of Header Page**************************************************
	//*******************************************************************************************************************************************

	public WebElement getSpiritLogoImage() {
		return img_SpiritLogo;
	}

	public WebElement getSpiritLogoPanel() {

		return pnl_SpiritLogo;
	}

	public WebElement getSignInLink() {
		return lnk_SignIn;
	}

	public WebElement getHelpLink() {
		return lnk_Help;
	}

	public WebElement getContactUsLink() {
		return lnk_ContactUs;
	}

	public WebElement getDealsLinkLink() {
		return lnk_Deals;
	}

	public WebElement getNineFareClubLink() {
		return lnk_NineFareClub;
	}

	public WebElement getSpirit101Link() {
		return lnk_Spirit101;
	}

	public WebElement getDestinationsLink() {
		return lnk_Destinations;
	}

	public WebElement getEnglishSpanishLink() {
		return lnk_EnglishSpanish;
	}

	public WebElement getBookLink() {
		return lnk_Book;
	}

	public WebElement getMyTripsLink() {
		return lnk_MyTrips;
	}

	public WebElement getCheckInLink() {
		return lnk_CheckIn;
	}

	public WebElement getFlightStatusLink() {
		return lnk_FlightStatus;
	}

	//*************************************************************************************
	//*******************Method after SignIn of Header Page********************************
	//*************************************************************************************
	public WebElement getUserDropDown() {

		for(WebElement element:drpdwn_User){
			if(element.isDisplayed()){
				return element;
			}
		}

		return null;
	}

	public WebElement getYourMilesUserLink() {
		return lnk_YourMilesUser;
	}

	public WebElement getFreeSpiritUserLink() {
		return lnk_FreeSpiritUser;
	}

	public WebElement getMyReservationUserLink() {
		return lnk_MyReservationUser;
	}

	public WebElement getMyAccountUserLink() {
		return lnk_MyAccountUser;
	}

	public WebElement getManageSubscriptionsLink() {
		return lnk_ManageSubscriptions;
	}

	public WebElement getStatementUserLink() {
		return lnk_StatementUser;
	}

	public WebElement getRequestMileageCreditUserLink() {
		return lnk_RequestMileageCreditUser;
	}

	public WebElement getBuyMilesUserLink() {
		return lnk_BuyMilesUser;
	}

	public WebElement getSignOutUserLink() {

		for(WebElement element:lnk_SignOutUser){
			if(element.isDisplayed()){
				return element;
			}
		}

		return null;
	}

	//*************************************************************************************
	//*******************Method of Itinerary of Header Page********************************
	//*************************************************************************************
	public WebElement getItineraryPanel() {
		return pnl_Itinerary;
	}

	public WebElement getYourItineraryText() {
		return txt_YourItinerary;
	}

	public WebElement getYouItineraryImage() {
		return img_YouItinerary;
	}

	public WebElement getYourItineraryPanel(){
		return pnl_YourItinerary;
	}

	public WebElement getItineraryTotalAmountText() {
		return txt_ItineraryTotalAmount;
	}

	public WebElement getArrowYourItineraryImage() {
		return img_ArrowYourItinerary;
	}

	public List<WebElement> getFlightItineraryBlockPanel() {
		return pnl_FlightItineraryBlock;
	}
	public WebElement getFlightItineraryText() {
		return txt_FlightItinerary;
	}

	// @FindBy(xpath = "//div[@class='col-7']//p")
	// private List<WebElement> pnl_FlightItineraryBlock;

	// public List<WebElement> getFlightItineraryBlockPanel() {
	// 	return pnl_FlightItineraryBlock;
	// }



	public WebElement getFlightItineraryImage() {
		return img_FlightItinerary;
	}

	public WebElement getFlightItineraryPanel(){
		return pnl_FlightItinerary;
	}

	public WebElement getFlightPriceItineraryText() {
		return txt_FlightPriceItinerary;
	}

	public WebElement getArrowFlightItineraryImage() {
		return img_ArrowFlightItinerary;
	}

	public WebElement getBagsItineraryText() {
		return txt_BagsItinerary;
	}

	public WebElement getBagsItineraryImage() {
		return img_BagsItinerary;
	}

	public WebElement getBagsItineraryPanel(){
		return pnl_BagsItinerary;
	}

	public WebElement getBagsPriceItineraryText() {
		return txt_BagsPriceItinerary;
	}

	public WebElement getArrowBagsItineraryImage() {
		return img_ArrowBagsItinerary;
	}

	public List<WebElement> getAirportCarryOnBagsSelectedItineraryText() { return txt_AirportCarryOnBagsSelectedItinerary; }

	public List<WebElement> getAirportCheckInBagsSelectedItineraryTxt() { return txt_AirportCheckInBagsSelectedItinerary; }

	public WebElement getSeatsItinerarayTextText() {
		return txt_SeatsItineraray;
	}

	public WebElement getSeatsItinerarayImage() {
		return img_SeatsItineraray;
	}

	public WebElement getSeatsPriceItineraryText() {
		return txt_SeatsPriceItinerary;
	}

	public WebElement getArrowSeatsItineraryImage() {
		return img_ArrowSeatsItinerary;
	}

	public List<WebElement> getDepSeatFlightLegDetailsText(){ return txt_DepSeatFlightLegDetails;}

	public List<WebElement> getRetSeatFlightLegDetailsText(){ return txt_RetSeatFlightLegDetails;}

	public WebElement getOptionsItineraryText() {
		return txt_OptionsItinerary;
	}

	public WebElement getOptionsItineraryImage() {
		return img_OptionsItinerary;
	}

	public WebElement getOptionsPriceItineraryText() {
		return txt_OptionsPriceItinerary;
	}

	public WebElement getArrowOptionsItineraryImage() {
		return img_ArrowOptionsItinerary;
	}

	public WebElement getOptionsSeatsItineraryImage() {
		return img_OptionsSeatsItinerary;
	}

	public WebElement getShortcutBoardingOptionPriceItineraryText(){
		return txt_ShortcutBoardingOptionPriceItinerary;
	}

	public WebElement getShortcutBoardingOptionItineraryLabel() { return lbl_ShortcutBoardingOptionItinerary; }

	public WebElement getFlightFlexOptionItineraryLabel() { return lbl_FlightFlexOptionItinerary; }

	public WebElement getFlightFlexOptionPriceItineraryText(){
		return txt_FlightFlexOptionPriceItinerary;
	}

	public WebElement getShortcutSecurityOptiontineraryLabel() { return lbl_ShortcutSecurityOptiontinerary; }

	public WebElement getShortcutSecurityOptionPriceItineraryText(){
		return txt_ShortcutSecurityOptionPriceItinerary;
	}

	public WebElement getJoinFareClubAndSaveItineraryText() {
		return txt_JoinFareClubAndSaveItinerary;
	}

	public WebElement getJoinFareClubAndSavePanel(){
		return pnl_JoinFareClubAndSave;
	}

	public WebElement getJoinFareClubAndSaveCarrot(){
		return crt_JoinFareClubAndSave;
	}

	public WebElement getArrowJoinFareClubAndSaveItineraryImage() {
		return img_ArrowJoinFareClubAndSaveItinerary;
	}

	public WebElement getMemberTermsAndConditionItineraryLink() {
		return lnk_MemberTermsAndConditionItinerary;
	}

	public WebElement getJoinNowAndSaveItineraryButton() {
		return btn_JoinNowAndSaveItinerary;
	}

	public WebElement getMembershipVerbiageText() {
		return txt_MembershipVerbiage;
	}

	public WebElement getMembershipAmountText() {
		return txt_MembershipAmount;
	}

	public WebElement getMembershipDisclosureTxt() { return txt_MembershipDisclosure; }

	public WebElement getMembershipCongratsVerbiageTxt() { return txt_MembershipCongratsVerbiage; }


	public List<WebElement> getFlightDateItineraryText() {
		return txt_FlightDateItinerary;
	}

	public List<WebElement> getFareFlightItineraryText() {
		return txt_FareFlightItinerary;
	}

	public List<WebElement> getAirportFlightItineraryText() {
		return txt_AirportFlightItinerary;
	}

	public WebElement getEditFlightItineraryButton() {
		return btn_EditFlightItinerary;
	}

	public WebElement getEditBagsItneraryButton() {
		return btn_EditBagsItnerary;
	}

	public List<WebElement> getAirportBagsItineraryText() {
		return txt_AirportBagsItinerary;
	}

	public List<WebElement> getPriceBagsItineraryText() {
		return txt_PriceBagsItinerary;
	}

	public List<WebElement> getOtherOptionItineraryText() {
		return txt_OtherOptionItinerary;
	}

	public List<WebElement> getOtherOptionPriceItineraryText() {
		return txt_OtherOptionPriceItinerary;
	}

	public WebElement getEditSeatItneraryButton() {
		return btn_EditSeatItnerary;
	}

	public WebElement getBareFareDiscountItineraryText() {
		return txt_BareFareDiscountItinerary;
	}

	public WebElement getBareFareDiscountPriceItineraryText() {
		return txt_BareFareDiscountPriceItinerary;
	}

	public WebElement getArrowBareFareDiscountItineraryImage(){
		return img_ArrowBareFareDiscountItinerary;
	}

	//*************************************************************************************
	//*******************Method Are You sure popup********************************
	//*************************************************************************************

	public WebElement getAreYouSurePopUpPanel(){
		return pnl_AreYouSurePopUp;
	}

	public WebElement getAreYouSurePopUpCloseImage(){
		return img_AreYouSurePopUpClose;
	}


	public List<WebElement> getBreadCumListText(){
		return txt_BreadCumList;
	}

	public List<WebElement> getBreadCumCheckMarkImage(){
		return img_BreadCumCheckMark;
	}

	public WebElement getBreadCumActiveStepNumberText(){
		return txt_BreadCumActiveStepNumber;
	}

}