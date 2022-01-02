package com.spirit.pageObjects;

import java.util.List;

import com.spirit.utility.TestUtil;
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
txtlst	 -  Text List
*/

public class PaymentPage {
	//private WebDriver driver;

	public PaymentPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//*******************************************************************
	//************************Your Itinerary*****************************
	//*******************************************************************
	public final String xpath_PaymentHeaderText= "//h1[contains(text(),'Payment') or contains(text(),'Pago')]";
	@FindBy(xpath=xpath_PaymentHeaderText)
	private WebElement txt_PaymentHeader;

	public final String xpath_PaymentSubHeaderText= "//h1[contains(text(),'Payment') or contains(text(),'Pago')]//following::p[1]";
	@FindBy(xpath = xpath_PaymentSubHeaderText)
	private WebElement txt_PaymentSubHeader;

	public final String xpath_YourItineraryText= "//h2[contains(text(),'Your Itinerary') or contains(text(),'Su Itinerario')]";
	@FindBy(xpath=xpath_YourItineraryText)
	private WebElement txt_YourItinerary;

	public final String xpath_YourNewFlightText= "//h2[contains(@class,'payment-itinerary-section-top') and contains(text(),'New')]";
	@FindBy(xpath = xpath_YourNewFlightText)
	private WebElement txt_YourNewFlight;

	public final String xpath_PrimaryPlaneImage= "//div[contains(@class,'flight-desktop-container')]//i[@class='icon-primary-plane']";
	@FindBy(xpath = xpath_PrimaryPlaneImage)
	private WebElement img_PrimaryPlane;

	public final String xpath_OriginalItineraryText= "//h2[contains(@class,'payment-itinerary-section-top') and contains(text(),'Original Itinerary')]";
	@FindBy(xpath = xpath_OriginalItineraryText)
	private WebElement txt_OriginalItinerary;

	public final String xpath_DepartDateText= "//i[@class='icon-primary-plane']/..";
	@FindBy(xpath=xpath_DepartDateText)
	private List<WebElement> txt_DepartDate;

	public final String xpath_DepartVerbiageText= "//strong[contains(text(),'Depart:') or contains(text(),'Salida:')]";
	@FindBy(xpath=xpath_DepartVerbiageText)
	private List<WebElement> txt_DepartVerbiage;

	public final String xpath_DepartingFlightCityNameText= "//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Depart') or contains(text(),'Salida')]/..";
	@FindBy(xpath=xpath_DepartingFlightCityNameText)
	private List<WebElement> txt_DepartingFlightCityName;

	public final String xpath_DurationVerbiageText= "//strong[contains(text(),'Duration:') or contains(text(),'Duración:')]";
	@FindBy(xpath=xpath_DurationVerbiageText)
	private List<WebElement> txt_DurationVerbiage;

	public final String xpath_DurationTimeText= "//div[@data-qa='flight-departure']//strong[text()='Duration:' or text()='Duración:']/..";
	@FindBy(xpath=xpath_DurationTimeText)
	private List<WebElement> txt_DurationTime;

	public final String xpath_FlightVerbiageText= "//strong[contains(text(),'Flight:') or contains(text(),'Vuelo:')]";
	@FindBy(xpath=xpath_FlightVerbiageText)
	private List<WebElement> txt_FlightVerbiage;

	public final String xpath_DepartingFlightCityNumberText= "//p[@class='custom-padding-summary mb-1']";
	@FindBy(xpath=xpath_DepartingFlightCityNumberText)
	private List<WebElement> txt_DepartingFlightCityNumber;

	public final String xpath_ArriveVerbiageText= "//strong[contains(text(),'Arrive:') or contains(text(),'Llegada:')]";
	@FindBy(xpath=xpath_ArriveVerbiageText)
	private List<WebElement> txt_ArriveVerbiage;

	public final String xpath_ArriveFlightCityNameText= "//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Arrive') or contains(text(),'Llegada')]/..";
	@FindBy(xpath=xpath_ArriveFlightCityNameText)
	private List<WebElement> txt_ArriveFlightCityName;

	public final String xpath_MilesVerbiageText= "//strong[contains(text(),'Miles:') or contains(text(),'Millas:')]";
	@FindBy(xpath=xpath_MilesVerbiageText)
	private List<WebElement> txt_MilesVerbiage;

	public final String xpath_MilesCoveredText= "//p[@class='custom-padding-summary mb-0']";
	@FindBy(xpath=xpath_MilesCoveredText)
	private List<WebElement> txt_MilesCovered;

	public final String xpath_LayoverVerbiageText= "//strong[contains(text(),'Layover:') or contains(text(),'Escala:')]";
	@FindBy(xpath=xpath_LayoverVerbiageText)
	private List<WebElement> txt_LayoverVerbiage;

	public final String xpath_LayoverTimeText= "//span[@class='text-capitalize']";
	@FindBy(xpath=xpath_LayoverTimeText)
	private List<WebElement> txt_LayoverTime;

	public final String xpath_YourItineraryArrowIcon= "//div[contains(@class,'flight-chevron-container')]//i";
	@FindBy(xpath=xpath_YourItineraryArrowIcon)
	private List<WebElement> txt_YourItineraryArrowIcon;

	public final String xpath_PassengerIcon= "//p[contains(@class,'payment-custom-padding')]/../i";
	@FindBy(xpath=xpath_PassengerIcon)
	private List<WebElement> icn_PassengerIcon;

	public final String xpath_PassengerNameText= "//p[contains(@class,'payment-custom-padding')]";
	@FindBy(xpath=xpath_PassengerNameText)
	private List<WebElement> txt_PassengerName;

	public final String xpath_FreeSpiritText= "//strong[contains(text(),'Free Spirit#:') or contains(text(),'Free Spirit#:')]/..";
	@FindBy(xpath=xpath_FreeSpiritText)
	private List<WebElement> txt_FreeSpirit;

	public final String xpath_KnownTravelerText= "//strong[contains(text(),'Known Traveler # (TSA Pre') or contains(text(),'Número de Viajero Conocido (TSA Pre')]/..";
	@FindBy(xpath=xpath_KnownTravelerText)
	private List<WebElement> txt_KnownTraveler;

	public final String xpath_RedressText= "//strong[contains(text(),'Redress:') or contains(text(),'Redress:')]";
	@FindBy(xpath=xpath_RedressText)
	private List<WebElement> txt_Redress;

	public final String xpath_AdditionalInfoText= "//strong[contains(text(),'Additional Info:') or contains(text(),'Additional Info:')]";
	@FindBy(xpath=xpath_AdditionalInfoText)
	private List<WebElement> txt_AdditionalInfo;

	public final String xpath_AdditionalInfoValueText= "//strong[contains(text(),'Additional Info:') or contains(text(),'Additional Info:')]/..//span";
	@FindBy(xpath = xpath_AdditionalInfoValueText)
	private List<WebElement> txt_AdditionalInfoValue;

	public final String xpath_BagIcon= "//i[@class='icon-checked-bag']";
	@FindBy(xpath=xpath_BagIcon)
	private List<WebElement> icn_BagIcon;

	public final String xpath_CityNameWithDepartingBagText= "//p[@class='d-inline']";
	@FindBy(xpath=xpath_CityNameWithDepartingBagText)
	private List<WebElement> txt_CityNameWithDepartingBag;

	public final String xpath_CityNameWithReturningBagText= "//p[@class='custom-padding-passenger mb-0']";
	@FindBy(xpath=xpath_CityNameWithReturningBagText)
	private List<WebElement> txt_CityNameWithReturningBag;

	public final String xpath_SeatsIcon= "//i[@class='icon-seats']";
	@FindBy(xpath=xpath_SeatsIcon)
	private List<WebElement> icn_SeatsIcon;

	public final String xpath_SeatSectionAllLegsText= "//div[@class='seats-section mt-3']";
	@FindBy(xpath=xpath_SeatSectionAllLegsText)
	private List<WebElement> txt_SeatSectionAllLegs;

	public final String xpath_YourItineraryPassengerInfoArrowIcon= "(//app-payment-itinerary//app-chevron/i)[4]";
//	public final String xpath_YourItineraryPassengerInfoArrowIcon= "//div[contains(@class,'passenger-chevron-container')]//i";
	@FindBy(xpath=xpath_YourItineraryPassengerInfoArrowIcon)
	private WebElement icn_YourItineraryPassengerInfoArrowIcon;

	public final String xpath_AllBagsInfoText= "//i[@class='icon-checked-bag']//following-sibling::span/p";
	@FindBy(xpath=xpath_AllBagsInfoText)
	private List<WebElement> txt_AllBagsInfo;

	public final String xpath_YourItineraryPassengerInfoSeatNumberText= "//div[contains(@class,'seats-section')]//p";
	@FindBy(xpath = xpath_YourItineraryPassengerInfoSeatNumberText)
	private List<WebElement> txt_YourItineraryPassengerInfoSeatNumber;

	//*******************************************************************
	//************************Flight Section*****************************
	//*******************************************************************
	public final String xpath_FlightSectionFlightChevronImage= "(//div[contains(@class,'flight-chevron-container')])[1]//i";
	@FindBy(xpath=xpath_FlightSectionFlightChevronImage)
	private WebElement img_FlightSectionFlightChevron;

	public final String xpath_FlightSectionTravellingDateText= "//div[contains(@class,'flight-desktop-container')]//i[@class='icon-primary-plane']/..";
	@FindBy(xpath=xpath_FlightSectionTravellingDateText)
	private List<WebElement> txt_FlightSectionTravellingDate;

	public final String xpath_FlightSectionFlightNumberText= "//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Flight:') or contains(text(),'Vuelo:')]/..";
	@FindBy(xpath=xpath_FlightSectionFlightNumberText)
	private List<WebElement> txt_FlightSectionFlightNumber;

	public final String xpath_FlightSectionDepartingFlightDetailText= "//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Depart:') or contains(text(),'Salida:')]/..";
	@FindBy(xpath=xpath_FlightSectionDepartingFlightDetailText)
	private List<WebElement> txt_FlightSectionDepartingFlightDetail;

	public final String xpath_FlightSectionArrivalFlightDetailText= "//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Arrive:') or contains(text(),'Llegada:')]/..";
	@FindBy(xpath=xpath_FlightSectionArrivalFlightDetailText)
	private List<WebElement> txt_FlightSectionArrivalFlightDetail;

	public final String xpath_FlightSectionFlightDurationText= "//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Duration:') or contains(text(),'Duración:')]/..";
	@FindBy(xpath=xpath_FlightSectionFlightDurationText)
	private List<WebElement> txt_FlightSectionFlightDuration;

	public final String xpath_FlightSectionFlightOverlayDurationText= "//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Layover:') or contains(text(),'Escala:')]/following-sibling::span";
	@FindBy(xpath=xpath_FlightSectionFlightOverlayDurationText)
	private List<WebElement> txt_FlightSectionFlightOverlayDuration;

	public final String xpath_AlOriginalItineraryDepartingFlightCityNameText= "//app-payment-itinerary[@ng-reflect-header='Original Itinerary']//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Depart') or contains(text(),'Salida')]/..";
	@FindBy(xpath = xpath_AlOriginalItineraryDepartingFlightCityNameText)
	private List<WebElement> txt_OriginalItineraryDepartingFlightCityName;

	public final String xpath_OriginalItineraryArriveFlightCityNameText= "//app-payment-itinerary[@ng-reflect-header='Original Itinerary']//div[contains(@class,'flight-desktop-container')]//strong[contains(text(),'Arrive') or contains(text(),'Llegada')]/..";
	@FindBy(xpath = xpath_OriginalItineraryArriveFlightCityNameText)
	private List<WebElement> txt_OriginalItineraryArriveFlightCityName;

	//*******************************************************************
	//************************Passenger Section**************************
	//*******************************************************************

	//**********Passenger Details*********

	//************Bags Details************


	//************Seats Details***********
	public final String xpath_PassengerSectionSeatFlightLegText= "//i[@class='icon-seats']/../following-sibling::div//b";
	@FindBy(xpath=xpath_PassengerSectionSeatFlightLegText)
	private List<WebElement> txt_PassengerSectionSeatFlightLeg;

	public final String xpath_PassengerSectionSeatFlightSeatNumberText= "//i[@class='icon-seats']/../following-sibling::div//p";
	@FindBy(xpath=xpath_PassengerSectionSeatFlightSeatNumberText)
	private List<WebElement> txt_PassengerSectionSeatFlightSeatNumber;

	//**************SSR Details***********

	//*******************************************************************
	//************************Options************************************
	//*******************************************************************
	public final String xpath_OptionstHeaderText= "//h2[contains(text(),'Options') or contains(text(),'Opciones')]";
	@FindBy(xpath=xpath_OptionstHeaderText)
	private WebElement txt_OptionstHeader;

	public final String xpath_YourHotelText= "//h3[contains(text(),'Your Hotel') or contains(text(),'Su Hotel')]";
	@FindBy(xpath = xpath_YourHotelText)
	private WebElement txt_YourHotel;

	public final String xpath_YourActivitiesText= "//h3[contains(text(),'Your Activities') or contains(text(),'Cancelación del Costo del Viaje')]";
	@FindBy(xpath=xpath_YourActivitiesText)
	private WebElement txt_YourActivities;

	public final String xpath_ActivitiesNameText= "//p[@class='font-weight-bold mb-0']";
	@FindBy(xpath=xpath_ActivitiesNameText)
	private List<WebElement> txt_ActivitiesName;

	public final String xpath_ActivitiesCityNameText= "//p[@class='//p[@class='font-weight-bold mb-0']/following-sibling::p";
	@FindBy(xpath=xpath_ActivitiesCityNameText)
	private List<WebElement> txt_ActivitiesCityName;

	public final String xpath_ActivitiesTimeDayDateText= "//div[@class='col-12 col-md-6']";
	@FindBy(xpath=xpath_ActivitiesTimeDayDateText)
	private List<WebElement> txt_ActivitiesTimeDayDate;

	public final String xpath_YourExtrasText= "//h3[contains(text(),'Your Extras') or contains(text(),'Sus Extras')]";
	@FindBy(xpath=xpath_YourExtrasText)
	private WebElement txt_YourExtras;

	public final String xpath_ShortcutSecurityText= "//p[contains(text(),'Shortcut Security') or contains(text(),'Cancelación del Costo del Viaje')]";
	@FindBy(xpath=xpath_ShortcutSecurityText)
	private WebElement txt_ShortcutSecurity;

	public final String xpath_SkipLineText= "//p[contains(text(),'Skip the line and get access to a dedicated security lane at the airport.') or contains(text(),'Cancelación del Costo del Viaje')]";
	@FindBy(xpath=xpath_SkipLineText)
	private WebElement txt_SkipLine;

	public final String xpath_FlightFlexText= "//p[contains(text(),'Flight Flex') or contains(text(),'Flight Flex')]";
	@FindBy(xpath=xpath_FlightFlexText)
	private WebElement txt_FlightFlex;

	public final String xpath_ModifyYourItineraryText= "//p[contains(text(),'Modify your itinerary once for free, up to 24 hours before departure') or contains(text(),'Modifique su itinerario una vez gratis, hasta 24 horas antes de la salida')]";
	@FindBy(xpath=xpath_ModifyYourItineraryText)
	private WebElement txt_ModifyYourItinerary;

	public final String xpath_ShortcutBoardingText= "//p[contains(text(),'Shortcut Boarding') or contains(text(),'Cancelación del Costo del Viaje')]";
	@FindBy(xpath=xpath_ShortcutBoardingText)
	private WebElement txt_ShortcutBoarding;

	public final String xpath_ZonePriorityText= "//p[contains(text(),'Zone 2 priority boarding and early access to the overhead bins') or contains(text(),'Cancelación del Costo del Viaje')]";
	@FindBy(xpath=xpath_ZonePriorityText)
	private WebElement txt_ZonePriority;

	//***************Hotel Section**************
	public final String xpath_OptionSectionHotelNameText= "//app-options-info-summary//section//div[@class='row']//p[contains(@class,'bold')]";
	@FindBy(xpath = xpath_OptionSectionHotelNameText)
	private WebElement txt_OptionSectionHotelName;

	public final String xpath_OptionSectionHotelAddressText= "//h3[contains(text(),'Your Hotel')]/following::p[2]";
	@FindBy(xpath = xpath_OptionSectionHotelAddressText)
	private WebElement txt_OptionSectionHotelAddress;

	public final String xpath_OptionSectionHotelAddress2Text= "//h3[contains(text(),'Your Hotel')]/following::p[3]";
	@FindBy(xpath = xpath_OptionSectionHotelAddress2Text)
	private WebElement txt_OptionSectionHotelAddress2;

	public final String xpath_OptionSectionHotelRoomText= "//app-options-info-summary//section//*[contains(text(),'Room')]/..";
	@FindBy(xpath = xpath_OptionSectionHotelRoomText)
	private WebElement txt_OptionSectionHotelRoom;

	public final String xpath_OptionSectionHotelGuestCountText= "//app-options-info-summary//section//p[contains(text(),'Guest')]";
	@FindBy(xpath = xpath_OptionSectionHotelGuestCountText)
	private WebElement txt_OptionSectionHotelGuestCount;

	public final String xpath_OptionSectionHotelNightsStayText= "//app-options-info-summary//section//p[contains(text(),'Night')]";
	@FindBy(xpath = xpath_OptionSectionHotelNightsStayText)
	private WebElement txt_OptionSectionHotelNightsStay;

//	public final String xpath_OptionSectionHotelCheckInText= "//app-options-info-summary//section//p[contains(text(),'CheckIn:') or contains(text(),'Check-In:')]";
	public final String xpath_OptionSectionHotelCheckInText= "//app-options-info-summary//section//p[contains(text(),'CheckIn:') or contains(text(),'Check-In:')]//following-sibling::p[1]";
	@FindBy(xpath = xpath_OptionSectionHotelCheckInText)
	private WebElement txt_OptionSectionHotelCheckIn;

	public final String xpath_OptionSelectedHotelCheckInDateText = "//app-options-info-summary//section//p[contains(text(),'CheckIn:') or contains(text(),'Check-In:')]//following-sibling::p";
	@FindBy(xpath=xpath_OptionSelectedHotelCheckInDateText)
	private WebElement txt_OptionSelectedHotelCheckInDate;

	public final String xpath_OptionSectionHotelCheckOutText= "//app-options-info-summary//section//p[contains(text(),'CheckOut:') or contains(text(),'Check-Out:')]//following-sibling::p";
//	public final String xpath_OptionSectionHotelCheckOutText= "//app-options-info-summary//section//p[contains(text(),'CheckOut:') or contains(text(),'Check-Out:')]";
	@FindBy(xpath = xpath_OptionSectionHotelCheckOutText)
	private WebElement txt_OptionSectionHotelCheckOut;

	public final String xpath_OptionSelectedHotelCheckOutDateText = "//app-options-info-summary//section//p[contains(text(),'CheckOut:') or contains(text(),'Check-Out:')]//following-sibling::p";
	@FindBy(xpath=xpath_OptionSelectedHotelCheckOutDateText)
	private WebElement txt_OptionSelectedHotelCheckOutDate;

	//***************Car Section**************
	public final String xpath_OptionSectionCarHeaderText = "//h3[contains(text(),'Your Car')]";
	@FindBy(xpath=xpath_OptionSectionCarHeaderText)
	private WebElement txt_OptionSectionCarHeader;

	public final String xpath_OptionSectionCarRentalAgencyText = "//h3[contains(text(),'Your Car')]/../../following-sibling::div[1]/div[1]//p[1]";
	@FindBy(xpath=xpath_OptionSectionCarRentalAgencyText)
	private WebElement txt_OptionSectionCarRentalAgency;

	public final String xpath_OptionSectionCarCarModelText = "//h3[contains(text(),'Your Car')]/../../following-sibling::div[1]/div[1]//p[2]";
	@FindBy(xpath=xpath_OptionSectionCarCarModelText)
	private WebElement txt_OptionSectionCarCarModel;

	public final String xpath_OptionSectionCarPickUpAddressText	= "//h3[contains(text(),'Your Car')]/../../following-sibling::div[1]/div[1]//p[2]/following-sibling::span";//list
	@FindBy(xpath=xpath_OptionSectionCarPickUpAddressText)
	private List<WebElement> txt_OptionSectionCarPickUpAddress;

	public final String xpath_OptionSectionCarPickUpTimeText = "//span[contains(text(),'Pick Up: ')]/following-sibling::span";
	@FindBy(xpath=xpath_OptionSectionCarPickUpTimeText)
	private WebElement txt_OptionSectionCarPickUpTime;

	public final String xpath_OptionSectionCarDropOffTimeText = "//span[contains(text(),'Drop Off: ')]/..";
	@FindBy(xpath=xpath_OptionSectionCarDropOffTimeText)
	private WebElement txt_OptionSectionCarDropOffTime;

	//*******************************************************************
	//********************Active Military Personal***********************
	//*******************************************************************
	public final String xpath_ActiveMilitrayHeaderText= "//section[@id='scroll-active-military']//h2";
	@FindBy(xpath=xpath_ActiveMilitrayHeaderText)
	private WebElement txt_ActiveMilitrayHeader;

	public final String xpath_ActiveMilitraySubHeaderText= "//section[@id='scroll-active-military']//p";
	@FindBy(xpath=xpath_ActiveMilitraySubHeaderText)
	private WebElement txt_ActiveMilitraySubHeader;

	public final String xpath_ActiveMilitrayRowsGridView= "//section[@id='scroll-active-military']//tr";
	@FindBy(xpath=xpath_ActiveMilitrayRowsGridView)
	private List<WebElement> grdvew_ActiveMilitrayRows;

	public final String xpath_ActiveMilitaryVerifyLink= "//section[@id='scroll-active-military']//a";
	@FindBy(xpath=xpath_ActiveMilitaryVerifyLink)
	private List<WebElement> lnk_ActiveMilitaryVerify;

	public final String xpath_ActiveMilitaryEmailTextBox= "//input[@id='user_email']";
	@FindBy(xpath=xpath_ActiveMilitaryEmailTextBox)
	private WebElement txtbx_ActiveMilitaryEmail;

	public final String xpath_ActiveMilitaryPasswordTextBox= "//input[@id='user_password']";
	@FindBy(xpath=xpath_ActiveMilitaryPasswordTextBox)
	private WebElement txtbx_ActiveMilitaryPassword;

	public final String xpath_ActiveMilitarySignInButton= "//input[@value='Sign in']";
	@FindBy(xpath=xpath_ActiveMilitarySignInButton)
	private WebElement btn_ActiveMilitarySignIn;

	public final String xpath_ActiveMilitaryThankYouText= "//tr[@class='ng-star-inserted']//p";
	@FindBy(xpath = xpath_ActiveMilitaryThankYouText)
	private WebElement txt_ActiveMilitaryThankYou;

	public final String xpath_ActiveMilitaryPleaseVerifyPopUpText= "//div[@class='popover-body']";
	@FindBy(xpath = xpath_ActiveMilitaryPleaseVerifyPopUpText)
	private WebElement txt_ActiveMilitaryPleaseVerifyPopUp;

	//*******************************************************************
	//*************************Options Section***************************
	//*******************************************************************
	public final String xpath_OptionSectionSelectedOptionsText= "//h3[contains(text(),'Your Extras') or contains(text(),'Sus Extras')]/../../following-sibling::div//div[contains(@class,'row')]";
	@FindBy(xpath=xpath_OptionSectionSelectedOptionsText)
	private List<WebElement> txt_OptionSectionSelectedOptions;

	public final String xpath_OptionSectionFlightFlexHeaderText= "//p[(text()='Flight Flex')]";
	@FindBy(xpath=xpath_OptionSectionFlightFlexHeaderText)
	private WebElement txt_OptionSectionFlightFlexHeader;

	public final String xpath_OptionSectionFlightFlexSubHeaderText= "//p[(text()='Flight Flex')]/following-sibling::p";
	@FindBy(xpath=xpath_OptionSectionFlightFlexSubHeaderText)
	private WebElement txt_OptionSectionFlightFlexSubHeader;

	public final String xpath_OptionSectionShortcutSecurityHeaderText= "//p[text()='Shortcut Security' or text()='Atajo de Seguridad']";
	@FindBy(xpath=xpath_OptionSectionShortcutSecurityHeaderText)
	private WebElement txt_OptionSectionShortcutSecurityHeader;

	public final String xpath_OptionSectionShortcutSecuritySubHeaderText= "//p[text()='Shortcut Security' or text()='Atajo de Seguridad']/following-sibling::p";
	@FindBy(xpath=xpath_OptionSectionShortcutSecuritySubHeaderText)
	private WebElement txt_OptionSectionShortcutSecuritySubHeader;

	public final String xpath_OptionSectionShortcutBoardingHeaderText= "//p[text()='Shortcut Boarding' or text()='Prioridad de Embarque']";
	@FindBy(xpath=xpath_OptionSectionShortcutBoardingHeaderText)
	private WebElement txt_OptionSectionShortcutBoardingHeader;

	public final String xpath_OptionSectionShortcutBoardingSubHeaderText= "//p[text()='Shortcut Boarding' or text()='Prioridad de Embarque']/following-sibling::p";
	@FindBy(xpath=xpath_OptionSectionShortcutBoardingSubHeaderText)
	private WebElement txt_OptionSectionShortcutBoardingSubHeader;

	public final String xpath_OptionSectionCheckInHeaderText= "//p[text()='Check-In' or text()='Opciones para Registrarse']";
	@FindBy(xpath=xpath_OptionSectionCheckInHeaderText)
	private WebElement txt_OptionSectionCheckInHeader;

	public final String xpath_OptionSectionCheckInSubHeaderText= "//p[text()='Check-In' or text()='Opciones para Registrarse']/following::p[1]";
	@FindBy(xpath=xpath_OptionSectionCheckInSubHeaderText)
	private WebElement txt_OptionSectionCheckInSubHeader;

	//*******************************************************************
	//************************Travel Guard*******************************
	//*******************************************************************
	public final String xpath_TravelGaurdPanel= "//div[@class='insurance-container']";
	@FindBy(xpath = xpath_TravelGaurdPanel)
	private WebElement pnl_TravelGaurd;

	public final String xpath_TripCostCancellationText= "//p[contains(text(),'Trip Cost Cancellation') or contains(text(),'Cancelación del Costo del Viaje')]";
	@FindBy(xpath=xpath_TripCostCancellationText)
	private WebElement txt_TripCostCancellation;

	public final String xpath_MissedConnectionText= "//p[contains(text(),'Missed Connection') or contains(text(),'Conexión Perdida')]";
	@FindBy(xpath=xpath_MissedConnectionText)
	private WebElement txt_MissedConnection;

	public final String xpath_BaggagedPersonalEffectsText= "//p[contains(text(),'Baggage & Personal Effects') or contains(text(),'Equipaje y Efectos Personales')]";
	@FindBy(xpath=xpath_BaggagedPersonalEffectsText)
	private WebElement txt_BaggagedPersonalEffects;

	public final String xpath_TripCostTripInterruptionText= "//p[contains(text(),'Trip Cost Trip Interruption') or contains(text(),'Costo del viaje por interrupción del viaje')]";
	@FindBy(xpath=xpath_TripCostTripInterruptionText)
	private WebElement txt_TripCostTripInterruption;

	public final String xpath_TripDelayText= "//p[contains(text(),'Trip Delay') or contains(text(),'Retraso del viaje')]";
	@FindBy(xpath=xpath_TripDelayText)
	private WebElement txt_TripDelay;

	public final String xpath_YesTravelGuardLabel= "//label[@for='radio-insuranceCoverage1']";
	@FindBy(xpath=xpath_YesTravelGuardLabel)
	private WebElement lbl_YesTravelGuard;

	public final String xpath_NoTravelGuardLabel= "//label[@for='radio-insuranceCoverage2']";
	@FindBy(xpath=xpath_NoTravelGuardLabel)
	private WebElement lbl_NoTravelGuard;

	public final String xpath_PaypalRadiobutton= "//*[@id='radio-paypal']";
	@FindBy(xpath=xpath_PaypalRadiobutton)
	private WebElement lbl_PaypalRadioButton;

	public final String xpath_PayInFullRadiobutton= "//*[@id='radio-navitaire']";
	@FindBy(xpath=xpath_PayInFullRadiobutton)
	private WebElement lbl_PayInFullRadioButton;

	public final String xpath_InsurancePolicyLink= "//a[contains(text(),'Insurance Policy') or contains(text(),'Descripción de Cobertura')]";
	@FindBy(xpath=xpath_InsurancePolicyLink)
	private WebElement lnk_InsurancePolicy;

	public final String xpath_TravelGuardGroupLink= "//a[contains(text(),'Travel Guard Group')]";
	@FindBy(xpath=xpath_TravelGuardGroupLink)
	private WebElement lnk_TravelGuardGroup;

	public final String xpath_TravelMoreWorryLessText= "//p[contains(text(),' Travel More. Worry Less. Get insurance') or contains(text(),' VIAJE MÁS. PREOCÚPESE MENOS.  OBTENGA UN SEGURO')]";
	@FindBy(xpath=xpath_TravelMoreWorryLessText)
	private WebElement txt_TravelMoreWorryLess;

	public final String xpath_HavereadAndUnderstandText= "//p[contains(text(),'I have read') or contains(text(),'He leído y entiendo')]";
	@FindBy(xpath=xpath_HavereadAndUnderstandText)
	private WebElement txt_HavereadAndUnderstand;

	public final String xpath_TravelGuardDisclaimerLink= "//a[contains(text(),'Full Disclaimer') or contains(text(),'Descargo de responsabilidad completo')]";
	@FindBy(xpath=xpath_TravelGuardDisclaimerLink)
	private WebElement lnk_TravelGuardDisclaimer;

	public final String xpath_CoverageOfferedText= "//p[contains(text(),'Coverage offered by ') or contains(text(),'La cobertura está ofrecida por ')]";
	@FindBy(xpath=xpath_CoverageOfferedText)
	private WebElement txt_CoverageOffered;

	public final String xpath_TravelGaurdCoverageText= "//div[@class='insurance-container']/div[1]/div//p";
	@FindBy(xpath = xpath_TravelGaurdCoverageText)
	private List<WebElement> txt_TravelGaurdCoverage;

	//*******************************************************************
	//*****************$9 FARE CLUB DISCOUNT SAVINGS*********************
	//*******************************************************************
	public final String xpath_FareClubSavingVerbiageText= "(//div[contains(@class,'card savings')]//p)[1]";
	@FindBy(xpath=xpath_FareClubSavingVerbiageText)
	private WebElement txt_FareClubSavingVerbiage;

	public final String xpath_FareClubSavingPriceText= "(//div[contains(@class,'card savings')]//p)[2]";
	@FindBy(xpath=xpath_FareClubSavingPriceText)
	private WebElement txt_FareClubSavingPrice;

	//*******************************************************************
	//************************$9FC Trail UpSell**************************
	//*******************************************************************
	public final String xpath_ExclusiveMembershipLabel= "//label[@for='exclusive9FCCheck']";
	@FindBy(xpath=xpath_ExclusiveMembershipLabel)
	private WebElement lbl_ExclusiveMembership;

	public final String xpath_TrialMembershipLabel= "//label[@for='check-trialMembership']";
	@FindBy(xpath=xpath_TrialMembershipLabel)
	private WebElement lbl_TrialMembership;

	public final String xpath_TrialMembershipPriceText= "//div[@class='terms-conditions-container']/div/div[2]/span";
	@FindBy(xpath=xpath_TrialMembershipPriceText)
	private WebElement txt_TrialMembershipPrice;

	public final String xpath_TrialMembershipTermsAndConditionsIcon= "//div[@class='terms-conditions-container']/div/div[2]/a";
	@FindBy(xpath=xpath_TrialMembershipTermsAndConditionsIcon)
	private WebElement icn_TrialMembershipTermsAndConditions;

	public final String xpath_MemberTrailTermsAndConditionPanel= "//div[contains(@class,'club-trial-container')]/following-sibling::div/div/div";
	@FindBy(xpath=xpath_MemberTrailTermsAndConditionPanel)
	private WebElement pnl_MemberTrailTermsAndCondition;

	public final String xpath_TermsAndConditionsCarLine= "(//div[@class='terms-container']//div)[1]//span[contains(text(),'2.2.3 Rental Cars')]/..//following-sibling::div[1]";
	@FindBy(xpath=xpath_TermsAndConditionsCarLine)
	private WebElement txt_TermsAndConditionsCarLine;

	public final String xpath_ExclusiveMembershipPriceText= "//div[contains(@class,'club-trial-container')]//app-chevron/../span";
	@FindBy(xpath = xpath_ExclusiveMembershipPriceText)
	private WebElement txt_ExclusiveMembershipPrice;

	public final String xpath_ExclusiveMembershipArrowImage= "//div[contains(@class,'club-trial-container')]//i";
	@FindBy(xpath = xpath_ExclusiveMembershipArrowImage)
	private WebElement img_ExclusiveMembershipArrow;

	public final String xpath_ExclusiveMembership$9FareClubDiscountText= "(//div[contains(@class,'card savings')]//p)[1]";
	@FindBy(xpath = xpath_ExclusiveMembership$9FareClubDiscountText)
	private WebElement txt_ExclusiveMembership$9FareClubDiscount;

	public final String xpath_ExclusiveMembership$9FareClubDiscountPriceText= "(//div[contains(@class,'card savings')]//p)[2]";
	@FindBy(xpath = xpath_ExclusiveMembership$9FareClubDiscountPriceText)
	private WebElement txt_ExclusiveMembership$9FareClubDiscountPrice;
	//*******************************************************************
	//************************Total Due Container************************
	//*******************************************************************
	public final String xpath_TotalDueText= "//p[contains(text(),'Total Due') or contains(text(),'Total Adeudado')]";
	@FindBy(xpath=xpath_TotalDueText)
	private WebElement txt_TotalDue;

	public final String xpath_TotalDuePriceText= "//p[contains(text(),'Total Due') or contains(text(),'Total Adeudado')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalDuePriceText)
	private WebElement txt_TotalDuePrice;

	public final String xpath_TotalDueChevronLink= "//div[contains(@class,'total-due-container')]//i[contains(@class,'icon-chevron-up')]";
	@FindBy(xpath=xpath_TotalDueChevronLink)
	private WebElement lnk_TotalDueChevron;

	public final String xpath_TotalDueFlightText= "(//div[@class='total-breakdown']//p[contains(text(),'Flight') or contains(text(),'Vuelo')])[1]";
	@FindBy(xpath=xpath_TotalDueFlightText)
	private WebElement txt_TotalDueFlight;

	public final String xpath_TotalDepFlightDueText= "(//app-price-section-line//div)[3]//p";
	@FindBy(xpath=xpath_TotalDepFlightDueText)
	private WebElement txt_TotalDepFlightDue;

	public final String xpath_TotalRetFlightDueText= "((//app-price-section-line//p[contains(text(),'Flight')])[2]//ancestor::app-price-section-line//div[2])[1]";
	@FindBy(xpath=xpath_TotalRetFlightDueText)
	private WebElement txt_TotalRetFlightDue;

	public final String xpath_TotalDuePayMonthlyText= "//label[@for='radio-uplift']";
	@FindBy(xpath = xpath_TotalDuePayMonthlyText)
	private WebElement txt_TotalDuePayMonthly;

	public final String xpath_TotalDueDisclaimerText= "//p[text()='Total Due' or text()='Total Adeudado']/../..//p[contains(@class,'disclaimer')]";
	@FindBy(xpath = xpath_TotalDueDisclaimerText)
	private WebElement txt_TotalDueDisclaimer;

	public final String xpath_TotalDueBreakDownText= "//div[@class='total-breakdown']//app-breakdown-section";
	@FindBy(xpath = xpath_TotalDueBreakDownText)
	private List<WebElement> txt_TotalDueBreakDown;

	public final String xpath_DepOnlyFlightChargeText= "(((//div[@class='total-breakdown']//p[contains(text(),'Flight')]/../../..)[1]//app-price-section-line[@level='1'])[1]//app-price-section-line[@level='2'])[1]//p";
	@FindBy(xpath=xpath_DepOnlyFlightChargeText)
	private List<WebElement> txt_DepOnlyFlightCharge;

	public final String xpath_RetOnlyFlightChargeText= "(((//div[@class='total-breakdown']//p[contains(text(),'Flight')]/../../..)[1]//app-price-section-line[@level='1'])[2]//app-price-section-line[@level='2'])[1]//p";
	@FindBy(xpath=xpath_RetOnlyFlightChargeText)
	private List<WebElement> txt_RetOnlyFlightCharge;

	public final String xpath_DepTotalBreakDownFlightText= "(//p[contains(text(),'taxes and carrier charges')]/../../..)[1]//app-price-section-line";
	@FindBy(xpath=xpath_DepTotalBreakDownFlightText)
	private List<WebElement> txt_DepTotalBreakDownFlight;

	public final String xpath_RetTotalBreakDownFlightText= "(//p[contains(text(),'taxes and carrier charges')]/../../..)[2]//app-price-section-line";
	@FindBy(xpath=xpath_RetTotalBreakDownFlightText)
	private List<WebElement> txt_RetTotalBreakDownFlight;

	public final String xpath_TotalDueFlightEditText= "//div[@class='total-breakdown']//p[contains(text(),'Flight') or contains(text(),'Vuelo')]/../following-sibling::div[1]/p";
	@FindBy(xpath=xpath_TotalDueFlightEditText)
	private WebElement txt_TotalDueFlightEdit;

	public final String xpath_TotalDueFlightPriceText= "//div[@class='total-breakdown']//p[contains(text(),'Flight') or contains(text(),'Vuelo')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalDueFlightPriceText)
	private WebElement txt_TotalDueFlightPrice;

	public final String xpath_TotalDueFlightChevronLink= "//div[@class='total-breakdown']//p[contains(text(),'Flight') or contains(text(),'Vuelo')]/../following-sibling::div[2]/p//i";
	@FindBy(xpath=xpath_TotalDueFlightChevronLink)
	private WebElement lnk_TotalDueFlightChevron;

	public final String xpath_TotalDueFlightCAFText= "//app-price-section-line[@ng-reflect-header='Colombian Administrative Fee']//div[@data-qa='total-options-price']//p";
	@FindBy(xpath=xpath_TotalDueFlightCAFText)
	private WebElement txt_TotalDueFlightCAF;

	public final String xpath_TotalDueFlightCOPUFText= "//div[@data-qa='total-options-item']//p[contains(text(),' Passenger Usage Fee (Carrier Fee) to Colombia')]/..//following::p[1])";
	@FindBy(xpath=xpath_TotalDueFlightCOPUFText)
	private WebElement txt_TotalDueFlightCOPUF;

	public final String xpath_TotalDueFlightPUFText= "//app-price-section-line[@ng-reflect-header = 'Passenger Usage Charge (Carrie']//div[@data-qa='total-options-price']//p";
	@FindBy(xpath=xpath_TotalDueFlightPUFText)
	private WebElement txt_TotalDueFlightPUF;

	public final String xpath_TotalDueBagsText= "//div[@class='total-breakdown']//p[contains(text(),'Bags') or contains(text(),'Equipaje')]";
	@FindBy(xpath=xpath_TotalDueBagsText)
	private WebElement txt_TotalDueBags;

	public final String xpath_TotalDueBagsBreakDownGridView= "//div[@class='total-breakdown']//p[contains(text(),'Bags') or contains(text(),'Equipaje')]/../following-sibling::div[3]//app-price-section-line[@level='1']";
	@FindBy(xpath=xpath_TotalDueBagsBreakDownGridView)
	private List<WebElement> grdvew_TotalDueBagsBreakDown;

	public final String xpath_TotalDueBagsEditText= "//div[@class='total-breakdown']//p[contains(text(),'Bags') or contains(text(),'Equipaje')]/../following-sibling::div[1]/p";
	@FindBy(xpath=xpath_TotalDueBagsEditText)
	private WebElement txt_TotalDueBagsEdit;

	public final String xpath_TotalDueBagsPriceText= "//div[@class='total-breakdown']//p[contains(text(),'Bags') or contains(text(),'Equipaje')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalDueBagsPriceText)
	private WebElement txt_TotalDueBagsPrice;

	public final String xpath_TotalDueBagsChevronText= "//div[@class='total-breakdown']//p[contains(text(),'Bags') or contains(text(),'Equipaje')]/../following-sibling::div[2]/p//i";
	@FindBy(xpath=xpath_TotalDueBagsChevronText)
	private WebElement lnk_TotalDueBagsChevron;

	public final String xpath_DepTotalDueBagsVatPriceText= "//div[@class='total-breakdown']//p[contains(text(),' VAT Tax') or contains(text(),'Equipaje')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_DepTotalDueBagsVatPriceText)
	private WebElement txt_DepTotalDueBagsVatPrice;

	public final String xpath_TotalDueBagsCityText= "(//div[@class='total-breakdown']//p[contains(text(),'Bags') or contains(text(),'Equipaje')]/../following-sibling::div[3]//div[@data-qa='total-options-item'])[1]";
	@FindBy(xpath = xpath_TotalDueBagsCityText)
	private WebElement txt_TotalDueBagsCity;

	public final String xpath_TotalDueBagsCheckedText= "//div[@class='total-breakdown']//p[contains(text(),'Checked Bag') or contains(text(),'Equipajes Registrados')]";
	@FindBy(xpath = xpath_TotalDueBagsCheckedText)
	private List<WebElement> txt_TotalDueBagsChecked;

	public final String xpath_TotalDueBagsCarryOnText= "//div[@class='total-breakdown']//p[contains(text(),' Carry-On') or contains(text(),'Equipaje de Mano')]";
	@FindBy(xpath = xpath_TotalDueBagsCarryOnText)
	private List<WebElement> txt_TotalDueBagsCarryOn;

	public final String xpath_TotalDueSeatsText= "//div[@class='total-breakdown']//p[contains(text(),'Seats') or contains(text(),'Asientos')]";
	@FindBy(xpath=xpath_TotalDueSeatsText)
	private WebElement txt_TotalDueSeats;

	public final String xpath_TotalDueSeatsBreakDownGridView= "//div[@class='total-breakdown']//p[contains(text(),'Seats') or contains(text(),'Asientos')]/../following-sibling::div[3]//app-price-section-line[@level='1']";
	@FindBy(xpath=xpath_TotalDueSeatsBreakDownGridView)
	private List<WebElement> grdvew_TotalDueSeatsBreakDown;

	public final String xpath_TotalDueSeatsEditText= "//div[@class='total-breakdown']//p[contains(text(),'Seats') or contains(text(),'Asientos')]/../following-sibling::div[1]/p";
	@FindBy(xpath=xpath_TotalDueSeatsEditText)
	private WebElement txt_TotalDueSeatsEdit;

	public final String xpath_TotalDueSeatsPriceText= "//div[@class='total-breakdown']//p[contains(text(),'Seats') or contains(text(),'Asientos')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalDueSeatsPriceText)
	private WebElement txt_TotalDueSeatsPrice;

	public final String xpath_TotalDueSeatsChevronLink= "//div[@class='total-breakdown']//p[contains(text(),'Seats') or contains(text(),'Asientos')]/../following-sibling::div[2]/p//i";
	@FindBy(xpath=xpath_TotalDueSeatsChevronLink)
	private WebElement lnk_TotalDueSeatsChevron;

	public final String xpath_TotalDueSeatsTypeText= "//div[@data-qa='total-options-item']//p[contains(text(),'Seat')]";
	@FindBy(xpath = xpath_TotalDueSeatsTypeText)
	private List<WebElement> txt_TotalDueSeatsType;

	public final String xpath_TotalDueOptionsText= "//div[@class='total-breakdown']//p[contains(text(),'Options') or contains(text(),'Opciones')]";
	@FindBy(xpath=xpath_TotalDueOptionsText)
	private WebElement txt_TotalDueOptions;

	public final String xpath_TotalDueOptionsBreakDownGridView= "//div[@class='total-breakdown']//p[contains(text(),'Options') or contains(text(),'Opciones')]/../following-sibling::div[3]//app-price-section-line[@level='1']";
	@FindBy(xpath=xpath_TotalDueOptionsBreakDownGridView)
	private List<WebElement> grdvew_TotalDueOptionsBreakDown;

	public final String xpath_TotalDueOptionsEditText= "//div[@class='total-breakdown']//p[contains(text(),'Options') or contains(text(),'Opciones')]/../following-sibling::div[1]/p";
	@FindBy(xpath=xpath_TotalDueOptionsEditText)
	private WebElement txt_TotalDueOptionsEdit;

	public final String xpath_TotalDueOptionsPriceText= "//div[@class='total-breakdown']//p[contains(text(),'Options') or contains(text(),'Opciones')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalDueOptionsPriceText)
	private WebElement txt_TotalDueOptionsPrice;

	public final String xpath_TotalDuePriceTextList= "//div[@class='total-breakdown']//p[contains(@class,'priceSummary')]";
	@FindBy(xpath=xpath_TotalDuePriceTextList)
	private List<WebElement> txt_TotalDuePriceTextList;

	public final String xpath_TotalDueOptionsChevronLink= "//div[@class='total-breakdown']//p[contains(text(),'Options') or contains(text(),'Opciones')]/../following-sibling::div[2]/p//i";
	@FindBy(xpath=xpath_TotalDueOptionsChevronLink)
	private WebElement lnk_TotalDueOptionsChevron;

	public final String xpath_TotalDueUpLiftPriceText= "//span[@class='total-underline']";
	@FindBy(xpath=xpath_TotalDueUpLiftPriceText)
	private WebElement txt_TotalDueUpLiftPrice;

	public final String xpath_TotalDueBundleDiscountText= "//div[@class='total-breakdown']//p[contains(text(),'Bundle It Discount') or contains(text(),'Bundle It Descuento')]";
	@FindBy(xpath=xpath_TotalDueBundleDiscountText)
	private WebElement txt_TotalDueBundleDiscount;

	public final String xpath_TotalDueBundleDiscountPriceText= "//div[@class='total-breakdown']//p[contains(text(),'Bundle It Discount') or contains(text(),'Bundle It Descuento')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalDueBundleDiscountPriceText)
	private WebElement txt_TotalDueBundleDiscountPrice;

	public final String xpath_TotalDueBoostDiscountText= "//div[@class='total-breakdown']//p[contains(text(),'Boost It Discount') or contains(text(),'Boost It Descuento')]";
	@FindBy(xpath=xpath_TotalDueBoostDiscountText)
	private WebElement txt_TotalDueBoostDiscount;

	public final String xpath_TotalDueBoostDiscountPriceText= "//div[@class='total-breakdown']//p[contains(text(),'Boost It Discount') or contains(text(),'Boost It Descuento')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalDueBoostDiscountPriceText)
	private WebElement txt_TotalDueBoostDiscountPrice;

	public final String xpath_TotalDueChangeFeesText= "//div[@class='total-breakdown']//p[contains(text(),'Change Fees')]";
	@FindBy(xpath = xpath_TotalDueChangeFeesText)
	private WebElement txt_TotalDueChangeFees;

	public final String xpath_TotalDueChangeFeesPriceText= "//div[@class='total-breakdown']//p[contains(text(),'Change Fees')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalDueChangeFeesPriceText)
	private List<WebElement> txt_TotalDueChangeFeesPrice;

	public final String xpath_TotalDueTravelInsuranceText= "//div[@class='total-breakdown']//p[contains(text(),'Travel Insurance') or contains(text(),'Seguro de Viaje')]";
	@FindBy(xpath = xpath_TotalDueTravelInsuranceText)
	private WebElement txt_TotalDueTravelInsurance;

	public final String xpath_TotalDueTravelInsurancePriceText= "//div[@class='total-breakdown']//p[contains(text(),'Travel Insurance') or contains(text(),'Seguro de Viaje')]/../following-sibling::div[2]/p";
	@FindBy(xpath = xpath_TotalDueTravelInsurancePriceText)
	private WebElement txt_TotalDueTravelInsurancePrice;

	public final String xpath_TotalDueVouchersAndCreditText= "//div[@class='total-breakdown']//p[contains(text(),'Vouchers & Credits') or contains(text(),'Vales y Créditos')]";
	@FindBy(xpath = xpath_TotalDueVouchersAndCreditText)
	private WebElement txt_TotalDueVouchersAndCredit;

	public final String xpath_TotalDueVoucherAndCreditPriceText= "//div[@class='total-breakdown']//p[contains(text(),'Vouchers & Credits') or contains(text(),'Vales y Créditos')]/../following-sibling::div[2]/p";
	@FindBy(xpath = xpath_TotalDueVoucherAndCreditPriceText)
	private WebElement txt_TotalDueVoucherAndCreditPrice;

	public final String xpath_TotalDueVoucherAndCreditChevronLink= "//div[@class='total-breakdown']//p[contains(text(),'Vouchers & Credits') or contains(text(),'Vales y Créditos')]/../following-sibling::div[2]/p//i";
	@FindBy(xpath = xpath_TotalDueVoucherAndCreditChevronLink)
	private WebElement lnk_TotalDueVoucherAndCreditChevron;

	public final String xpath_TotalDueVouchersAndCreditPriceListText= "//p[contains(text(),'Vouchers & Credits') or contains(text(),'Vales y Créditos')]//..//following::app-price-section-line//p[contains(text(),'$')]";
	@FindBy(xpath = xpath_TotalDueVouchersAndCreditPriceListText)
	private List<WebElement> txt_TotalDueVouchersAndCreditPriceList;

	public final String xpath_TotalDueVouchersAndCreditFieldListText= "//p[contains(text(),'Vouchers & Credits') or contains(text(),'Vales y Créditos')]//..//following::app-price-section-line//p";
	@FindBy(xpath = xpath_TotalDueVouchersAndCreditFieldListText)
	private List<WebElement> txt_TotalDueVouchersAndCreditFieldList;

	//*******************************************************************
	//*********************Redeem Voucher Block**********************
	//*******************************************************************
	public final String xpath_RedeemVoucherOrCreditLink= "//p[contains(text(),'Redeem a Voucher ') or contains(text(),'Canjear un vale o crédito')]";
	@FindBy(xpath=xpath_RedeemVoucherOrCreditLink)
	private WebElement lnk_RedeemVoucherOrCredit;

	public final String xpath_RedeemVoucherOrCreditChevronLink= "//p[contains(text(),'Redeem a Voucher ') or contains(text(),'Canjear un vale o crédito')]//i";
	@FindBy(xpath=xpath_RedeemVoucherOrCreditChevronLink)
	private WebElement lnk_RedeemVoucherOrCreditChevron;

	public final String xpath_VoucherNumberTextBox= "//input[@id='voucherNumber']";
	@FindBy(xpath=xpath_VoucherNumberTextBox)
	private WebElement txtbx_VoucherNumber;

	public final String xpath_VoucherNumberGoButton= "(//app-payment-voucher-redemption//button[contains(text(),'Go')])[1]";
	@FindBy(xpath = xpath_VoucherNumberGoButton)
	private WebElement btn_VoucherNumberGo;

	public final String xpath_VoucherNumberApplyButton= "//p[contains(text(),'Voucher Redemption')]/ancestor::form//div[@class='card reservation-credit-amount']//button";
	@FindBy(xpath = xpath_VoucherNumberApplyButton)
	private WebElement btn_VoucherNumberApply;

	public final String xpath_RedeemReservationCreditSubHeaderText= "//label[@for='confirmationCode']/../../../../..//p";
	@FindBy(xpath = xpath_RedeemReservationCreditSubHeaderText)
	private WebElement txt_RedeemReservationCreditSubHeader;

	public final String xpath_RedeemReservationCreditConfirmationCodeText= "//label[@for='confirmationCode']/span";
	@FindBy(xpath = xpath_RedeemReservationCreditConfirmationCodeText)
	private WebElement txt_RedeemReservationCreditConfirmationCode;

	public final String xpath_RedeemReservationCreditFooterText= "(//app-payment-reservation-credit//div/p)[3]";
	@FindBy(xpath = xpath_RedeemReservationCreditFooterText)
	private WebElement txt_RedeemReservationCreditFooter;

	public final String xpath_RedeemVoucherConfirmationCodeTextBox= "//input[@id='confirmationCode']";
	@FindBy(xpath = xpath_RedeemVoucherConfirmationCodeTextBox)
	private WebElement txtbx_RedeemVoucherConfirmationCode;

	public final String xpath_RedeemVoucherGoButton= "//app-payment-reservation-credit//button[contains(text(),'Go')]";
	@FindBy(xpath = xpath_RedeemVoucherGoButton)
	private WebElement btn_RedeemVoucherGo;

	public final String xpath_RedeemVoucherAmountText= "//div[@class='card reservation-credit-amount']//p";
	@FindBy(xpath = xpath_RedeemVoucherAmountText)
	private List<WebElement> txt_RedeemVoucherAmount;

	public final String xpath_RedeemVoucherAmountToApplyTextBox= "//input[@id='voucherAmountToApply']";
	@FindBy(xpath = xpath_RedeemVoucherAmountToApplyTextBox)
	private WebElement txtbx_RedeemVoucherAmountToApply;

	public final String xpath_RedeemVoucherAmountPriceText= "//div[@class='card reservation-credit-amount']//p[contains(text(),'$')]";
	@FindBy(xpath = xpath_RedeemVoucherAmountPriceText)
	private WebElement txt_RedeemVoucherAmountPrice;

	public final String xpath_RedeemVoucherAmountToSpendTextBox= "//input[@id='amountToSpend']";
	@FindBy(xpath = xpath_RedeemVoucherAmountToSpendTextBox)
	private WebElement txtbx_RedeemVoucherAmountToSpend;

	public final String xpath_RedeemVoucherInvalidNumberRangeText= "//div[contains(@class,'error-text')][contains(text(),'Invalid number range.')]";
	@FindBy(xpath = xpath_RedeemVoucherInvalidNumberRangeText)
	private List<WebElement> txt_RedeemVoucherInvalidNumberRange;

	public final String xpath_RedeemReservationCreditAvailableFundsText=  "//div[@class='card reservation-credit-amount']//p[contains(text(),'Available Funds') or contains(text(),'Fondos Disponibles')]";
	@FindBy(xpath =xpath_RedeemReservationCreditAvailableFundsText)
	private WebElement txt_RedeemReservationCreditAvailableFunds;

	public final String xpath_RedeemVoucherApplyCreditButton= "//button[contains(text(),'APPLY CREDIT ')]";
	@FindBy(xpath = xpath_RedeemVoucherApplyCreditButton)
	private WebElement btn_RedeemVoucherApplyCredit;

	public final String xpath_RedeemVoucherErrorMessageText= "//div[contains(@class,'error-text')]";
	@FindBy(xpath =xpath_RedeemVoucherErrorMessageText )
	private List<WebElement> txt_RedeemVoucherErrorMessage;

	//*******************************************************************
	//*********************Reservation Credit Block**********************
	//*******************************************************************
	public final String xpath_ReservationCreditBlockHeaderText= "(//div[@class='card']//p)[1]";
	@FindBy(xpath=xpath_ReservationCreditBlockHeaderText)
	private WebElement txt_ReservationCreditBlockHeader;

	public final String xpath_ReservationCreditBlockSubHeaderText= "(//div[@class='card']//p)[2]";
	@FindBy(xpath=xpath_ReservationCreditBlockSubHeaderText)
	private WebElement txt_ReservationCreditBlockSubHeader;

	public final String xpath_ReservationCreditBlockTypeText= "(//div[@class='card']//p)[3]/following-sibling::p";
	@FindBy(xpath=xpath_ReservationCreditBlockTypeText)
	private WebElement txt_ReservationCreditBlockType;

	public final String xpath_ReservationCreditBlockPNRText= "(//div[@class='card']//p)[5]/following-sibling::p";
	@FindBy(xpath=xpath_ReservationCreditBlockPNRText)
	private WebElement txt_ReservationCreditBlockPNR;

	public final String xpath_ReservationCreditBlockPriceText= "(//div[@class='card']//p)[7]/following-sibling::p";
	@FindBy(xpath=xpath_ReservationCreditBlockPriceText)
	private WebElement txt_ReservationCreditBlockPrice;

	public final String xpath_ReservationCreditBlockDateText= "(//div[@class='card']//p)[9]/following-sibling::p";
	@FindBy(xpath=xpath_ReservationCreditBlockDateText)
	private WebElement txt_ReservationCreditBlockDate;

	public final String xpath_ReservationCreditBlockSubFooterText= "(//div[@class='card']//p)[11]";
	@FindBy(xpath=xpath_ReservationCreditBlockSubFooterText)
	private WebElement txt_ReservationCreditBlockSubFooter;

	public final String xpath_ReservationCreditBlockFooterText= "(//div[@class='card']//p)[12]";
	@FindBy(xpath=xpath_ReservationCreditBlockFooterText)
	private WebElement txt_ReservationCreditBlockFooter;

	//*******************************************************************
	//***********************Payment Section ****************************
	//*******************************************************************
	public final String xpath_PayPalButton = "(//div[contains(@class,'paypal')])[1]";
	@FindBy(xpath=xpath_PayPalButton)
	private WebElement btn_PaymentSectionPayPal;

	public final String xpath_PaymentSectionCreditCardText= "//p[contains(text(),'Credit Card') or contains(text(),'Tarjeta de Crédito')]";
	@FindBy(xpath=xpath_PaymentSectionCreditCardText)
	private WebElement txt_PaymentSectionCreditCard;

	public final String xpath_PaymentSectionUpdateCreditCardLink= "//a[contains(text(), 'Update Credit Card') or contains(text(), 'Actualizar la tarjeta de crédito')]";
	@FindBy(xpath=xpath_PaymentSectionUpdateCreditCardLink)
	private WebElement lnk_PaymentSectionUpdateCreditCard;

	public final String xpath_RPaymentSectionUpdateButton= "//button[contains(text(), 'Update') or contains(text(), 'Update')]";
	@FindBy(xpath=xpath_RPaymentSectionUpdateButton)
	private WebElement btn_PaymentSectionUpdate;

	public final String xpath_PaymentSectionCancelChangesButton= "//a[contains(text(), 'Cancel Changes') or contains(text(), 'Cancel Changes')]";
	@FindBy(xpath=xpath_PaymentSectionCancelChangesButton)
	private WebElement btn_PaymentSectionCancelChanges;

	public final String xpath_PaymentSectionSaveCardCheckBox= "//input[@name= 'saveCard']";
	@FindBy(xpath=xpath_PaymentSectionSaveCardCheckBox)
	private WebElement chkbx_PaymentSectionSaveCard;

	public final String xpath_PaymentSectionSaveCardLabel= "//label[ @for = 'saveCard']";
	@FindBy(xpath=xpath_PaymentSectionSaveCardLabel)
	private WebElement lbl_PaymentSectionSaveCard;

	//public final String xpath_PayPalUpdateLink = "//html/body/app-root/main/div[2]/app-book-path/div/app-payment-page/section/form/div[1]/div[2]/div/div[2]/div[2]/a";
	public final String xpath_PayPalUpdateLink = "//a[@href ='javascript:void(0)']";
	@FindBy(xpath=xpath_PayPalUpdateLink)
	private WebElement btn_PayPalUpdateLink;

	//public final String xpath_PayPalDisplay = "//html/body/app-root/main/div[2]/app-book-path/div/app-payment-page/section/form/div[1]/div[2]/div/div[1]/label/img";
	public final String xpath_PayPalDisplay = "//img[@alt = 'PayPal']";
	@FindBy(xpath=xpath_PayPalDisplay)
	private WebElement btn_PayPalDisplay;

	//*******************************************************************
	//*************************Billing Address***************************
	//*******************************************************************
	public final String xpath_UseSameAddressCheckBox= "//input[@id='useSameAddress']";
	@FindBy(xpath=xpath_UseSameAddressCheckBox)
	private WebElement chkbx_UseSameAddress;

	public final String xpath_UseSameAddressLabel= "//label[@for='useSameAddress']";
	@FindBy(xpath=xpath_UseSameAddressLabel)
	private WebElement lbl_UseSameAddress;

	public final String xpath_SelectCardDropDown= "//select[@id='cards']";
	@FindBy(xpath=xpath_SelectCardDropDown)
	private WebElement drpdwn_SelectCard;

	public final String xpath_AddressTextBox= "//input[@id='billingAddress']";
	@FindBy(xpath=xpath_AddressTextBox)
	private WebElement txtbox_Address;

	public final String xpath_CityTextBox= "//input[@id='billingCity']";
	@FindBy(xpath=xpath_CityTextBox)
	private WebElement txtbox_City;

	public final String xpath_StateDropDown= "//select[@id='billingState']";
	@FindBy(xpath=xpath_StateDropDown)
	private WebElement drpdwn_State;

	public final String xpath_ZipCodeTextBox =  "//input[@id='billingZipPostal']";
	@FindBy(xpath=xpath_ZipCodeTextBox)
	private WebElement txtbox_ZipCode;

	public final String xpath_CountryDropDown= "//select[@id='billingCountry']";
	@FindBy(xpath=xpath_CountryDropDown)
	private WebElement drpdwn_Country;

	public final String xpath_StateTextBox= "//input[@id='billingState']";
	@FindBy(xpath=xpath_StateTextBox)
	private WebElement txtbx_State;

	public final String xpath_BillingAddressHorizontalLine= "//p[contains(text(),'Billing Address')]/following::hr";
	@FindBy(xpath = xpath_BillingAddressHorizontalLine)
	private WebElement line_BillingAddressHorizontal;

	//*******************************************************************
	//**********************Terms and Continue***************************
	//*******************************************************************
	public final String xpath_TermsAndConditionsHeader= "(//p[contains(text(),'Terms and Conditions')])[2]";
	@FindBy(xpath=xpath_TermsAndConditionsHeader)
	private WebElement txt_TermsAndConditionsHeader;


	public final String xpath_TermsAndConditionsLabel= "//label[@for='termsCheck']";
	@FindBy(xpath=xpath_TermsAndConditionsLabel)
	private WebElement lbl_TermsAndConditions;

	public final String xpath_TermsAndConditionsHertzLabel= "//label[@for='hertzTermsCheck']";
	@FindBy(xpath=xpath_TermsAndConditionsHertzLabel)
	private WebElement lbl_TermsAndConditionsHertz;

	public final String xpath_TermsAndConditionsErrorMessageText= "//div[contains(@class,'terms-conditions-container')]//div[contains(@class,'error-text')]";
	@FindBy(xpath = xpath_TermsAndConditionsErrorMessageText)
	private WebElement txt_TermsAndConditionsErrorMessage;

	public final String xpath_BookTripButton = "//button[contains(text(),'BOOK TRIP') or contains(text(),'Reservar el Viaje') or contains(text(),'Continue')]";
	@FindBy(xpath=xpath_BookTripButton)
	private WebElement btn_BookTrip;

	public final String xpath_ContunueButton= "//button[contains(text(),'Continue') or contains(text(),'Continuar')]";
	@FindBy(xpath=xpath_ContunueButton)
	private WebElement btn_Contunue;

	//*******************************************************************
	//*********************Travel Guard on POP_UP************************
	//*******************************************************************
	public final String xpath_TravelGuardPopupHeaderText= "//h2[@class='modal-title']";
	@FindBy(xpath=xpath_TravelGuardPopupHeaderText)
	private WebElement txt_TravelGuardPopupHeader;

	public final String xpath_TravelGuardPopupSubHeaderText= "//div[@class='modal-body']//h4";
	@FindBy(xpath=xpath_TravelGuardPopupSubHeaderText)
	private WebElement txt_TravelGuardPopupSubHeader;

	public final String xpath_YesTravelGuardPopupLabel= "//label[contains(@for,'radio-travel-guard-yes') or contains(@for,'radio-yes')]";
	@FindBy(xpath=xpath_YesTravelGuardPopupLabel)
	private WebElement lbl_YesTravelGuardPopup;

	public final String xpath_CNoTravelGuardPopupLabel= "//label[contains(@for,'radio-travel-guard-no') or contains(@for,'radio-no')]";
	@FindBy(xpath=xpath_CNoTravelGuardPopupLabel)
	private WebElement lbl_NoTravelGuardPopup;

	//*******************************************************************
	//*********************Manage Travel Buttons************************
	//*******************************************************************
	public final String xpath_ManageTravelContinueButton= "//button[contains(text(),'Continue') or contains(text(),'Continuar')]";
	@FindBy(xpath=xpath_ManageTravelContinueButton)
	private WebElement btn_ManageTravelContinue;

	public final String xpath_ManageTravelCancelChangesButton= "//p[contains(text(),'Cancel Changes') or contains(text(),'Cancelar Cambios')]";
	@FindBy(xpath=xpath_ManageTravelCancelChangesButton)
	private WebElement btn_ManageTravelCancelChanges;

	//*******************************************************************
	//*********************Are You Sure PopUp****************************
	//*******************************************************************
	public final String xpath_AreYouSurePopUpHeaderText= "//h2[contains(text(),'Are you sure?') or contains(text(),'¿Está seguro?')]";
	@FindBy(xpath = xpath_AreYouSurePopUpHeaderText)
	private WebElement txt_AreYouSurePopUpHeader;

	public final String xpath_AreYouSurePopUpBodyText= "//app-flight-edit-disclaimer-modal//strong/..";
	@FindBy(xpath = xpath_AreYouSurePopUpBodyText)
	private WebElement txt_AreYouSurePopUpBody;

	public final String xpath_AreYouSurePopUpContinueToFlightButton= "//button[contains(text(),'Continue to flights') or contains(text(),'Continuar a los vuelos')]";
	@FindBy(xpath = xpath_AreYouSurePopUpContinueToFlightButton)
	private WebElement btn_AreYouSurePopUpContinueToFlight;
	//****************************************************************************
	//*********************Sorry PopUp Milatry Reprice **************************
	//****************************************************************************
	public final String xpath_SorryPopUpMilitaryRepricePanel= "//app-military-reprice-modal";
	@FindBy(xpath = xpath_SorryPopUpMilitaryRepricePanel)
	private WebElement pnl_SorryPopUpMilitaryReprice;

	public final String xpath_SorryPopUpMilitaryRepriceCloseButton= "//app-military-reprice-modal//i";
	@FindBy(xpath = xpath_SorryPopUpMilitaryRepriceCloseButton)
	private WebElement btn_SorryPopUpMilitaryRepriceClose;

	//****************************************************************************
	//**********************9FC fare club PopUp **********************************
	//****************************************************************************
	public final String xpath_MembershipLogInButton= "//button[text()='log in']";
	@FindBy(xpath = xpath_MembershipLogInButton)
	private WebElement btn_$9FMembershipLogIn;

	public final String xpath_MembershipSignUpButton= "//button[text()='sign up']";
	@FindBy(xpath = xpath_MembershipSignUpButton)
	private WebElement btn_$9FMembershipSignUp;

	public final String xpath_9FCPopUpChoosePassWordTextBox= "//input[@id='password']";
	@FindBy(xpath = xpath_9FCPopUpChoosePassWordTextBox)
	private WebElement txtbox_9FCPopUpChoosePassWord;

	public final String xpath_9FCPopUpConfirmPassWordTextBox= "//input[@id='confirmPassword']";
	@FindBy(xpath = xpath_9FCPopUpConfirmPassWordTextBox)
	private WebElement txtbox_9FCPopUpConfirmPassWord;

	public final String xpath_9FCPopUpSignUpWithEmailButton= "//button[text()='sign up with email']";
	@FindBy(xpath = xpath_9FCPopUpSignUpWithEmailButton)
	private WebElement btn_9FCPopUpSignUpWithEmail;

	public final String xpath_$9FCFareClubPopUpPanel= "//app-nine-dollar-fare-club-modal";
	@FindBy(xpath = xpath_$9FCFareClubPopUpPanel)
	private  WebElement pnl_$9FCFareClubPopUp;

	public final String xpath_$9FCFareClubPopUpHeaderText= "//app-nine-dollar-fare-club-modal//h2";
	@FindBy(xpath = xpath_$9FCFareClubPopUpHeaderText)
	private WebElement txt_$9FCFareClubPopUpHeader;

	public final String xpath_$9FCFareClubPopUpCloseImage= "//app-nine-dollar-fare-club-modal//i";
	@FindBy(xpath = xpath_$9FCFareClubPopUpCloseImage)
	private WebElement img_$9FCFareClubPopUpClose;

	public final String xpath_$9FCFareClubPopUpSubHeaderText= "(//app-nine-dollar-fare-club-modal//p)[1]";
	@FindBy(xpath = xpath_$9FCFareClubPopUpSubHeaderText)
	private WebElement txt_$9FCFareClubPopUpSubHeader;

	public final String xpath_$9FCFareClubPopUpYesIWantButton= "//app-nine-dollar-fare-club-modal//button[contains(text(),'Yes')]";
	@FindBy(xpath = xpath_$9FCFareClubPopUpYesIWantButton)
	private WebElement btn_$9FCFareClubPopUpYesIWant;

	public final String xpath_$9FCFareClubPopUpNoIDontWantButton= "//app-nine-dollar-fare-club-modal//button[contains(text(),'No')]";
	@FindBy(xpath = xpath_$9FCFareClubPopUpNoIDontWantButton)
	private WebElement btn_$9FCFareClubPopUpNoIDontWant;

	public final String xpath_$9FCFareClubPopUpYesIWantVerbageText= "(//app-nine-dollar-fare-club-modal//p)[2]";
	@FindBy(xpath = xpath_$9FCFareClubPopUpYesIWantVerbageText)
	private WebElement txt_$9FCFareClubPopUpYesIWantVerbage;

	public final String xpath_$9FCFareClubPopUpNoIDontWantVerbageText= "(//app-nine-dollar-fare-club-modal//p)[3]";
	@FindBy(xpath = xpath_$9FCFareClubPopUpNoIDontWantVerbageText)
	private WebElement txt_$9FCFareClubPopUpNoIDontWantVerbage;

	//*******************************************************************
	//**********************PayPal Pop Up***************************
	//*******************************************************************
	public final String xpath_PayPalPaymentMethodsLabel= "//div[@id='paymentMethod']//span[contains(@class,'fsName')]";
	@FindBy(xpath=xpath_PayPalPaymentMethodsLabel)
	private List<WebElement> lbl_PayPalPaymentMethods;

	public final String xpath_PayPalEmailTextBox= "//input[@id='email']";
	@FindBy(xpath = xpath_PayPalEmailTextBox)
	private WebElement txtbx_PayPalEmail;

	public final String xpath_PayPalNextOnEmailButton = "//button[@id='btnNext']";
	@FindBy(xpath = xpath_PayPalNextOnEmailButton)
	private WebElement btn_PayPalNextOnEmail;

	public final String xpath_PayPalPasswordTextBox= "//input[@id='password']";
	@FindBy(xpath = xpath_PayPalPasswordTextBox)
	private WebElement txtbx_PayPalPassword;

	public final String xpath_PayPalLogInButton = "//button[@id='btnLogin']";
	@FindBy(xpath = xpath_PayPalLogInButton)
	private WebElement btn_PayPalLogIn;

	public final String xpath_PayPalPayNowButton = "//div//input[@id='confirmButtonTop']";
	@FindBy(xpath = xpath_PayPalPayNowButton)
	private WebElement btn_PayPalPayNowButton;


	//*****************************************************************************************************************
	//*************************************Start of Methods of Payment Page********************************************
	//*****************************************************************************************************************
	//*******************************************************************
	//************************Your Itinerary*****************************
	//*******************************************************************
	public WebElement getPaymentHeaderText() {
		return txt_PaymentHeader;
	}

	public WebElement getPaymentSubHeaderText(){
		return txt_PaymentSubHeader;
	}

	public WebElement getYourItineraryText() {
		return txt_YourItinerary;
	}

	public WebElement getYourNewFlightText(){
		return txt_YourNewFlight;
	}

	public WebElement getPrimaryPlaneImage(){
		return img_PrimaryPlane;
	}

	public WebElement getOriginalItineraryText(){
		return txt_OriginalItinerary;
	}


	public List<WebElement> getDepartDateText() {
		return txt_DepartDate;
	}

	public List<WebElement> getDepartVerbiageText() {
		return txt_DepartVerbiage;
	}

	public List<WebElement> getDepartingFlightCityNameText() {
		return txt_DepartingFlightCityName;
	}

	public List<WebElement> getDurationVerbiageText() {
		return txt_DurationVerbiage;
	}

	public List<WebElement> getDurationTimeText() {
		return txt_DurationTime;
	}

	public List<WebElement> getFlightVerbiageText() {
		return txt_FlightVerbiage;
	}

	public List<WebElement> getDepartingFlightCityNumberText() {
		return txt_DepartingFlightCityNumber;
	}

	public List<WebElement> getArriveVerbiageText() {
		return txt_ArriveVerbiage;
	}

	public List<WebElement> getArriveFlightCityNameText() {
		return txt_ArriveFlightCityName;
	}

	public List<WebElement> getMilesVerbiageText() {
		return txt_MilesVerbiage;
	}

	public List<WebElement> getMilesCoveredText() {
		return txt_MilesCovered;
	}

	public List<WebElement> getLayoverVerbiageText() {
		return txt_LayoverVerbiage;
	}

	public List<WebElement> getLayoverTimeText() {
		return txt_LayoverTime;
	}

	public List<WebElement> getYourItineraryArrowIcon() {
		return txt_YourItineraryArrowIcon;
	}

	public List<WebElement> getPassengerIcon() {
		return icn_PassengerIcon;
	}

	public List<WebElement> getPassengerNameText() {
		return txt_PassengerName;
	}

	public List<WebElement> getFreeSpiritText() {
		return txt_FreeSpirit;
	}

	public List<WebElement> getKnownTravelerText() {
		return txt_KnownTraveler;
	}

	public List<WebElement> getRedressText() {
		return txt_Redress;
	}

	public List<WebElement> getAdditionalInfoText() {
		return txt_AdditionalInfo;
	}

	public List<WebElement> getAdditionalInfoValueText(){
		return txt_AdditionalInfoValue;
	}

	public List<WebElement> geticn_BagIconIcon() {
		return icn_BagIcon;
	}

	public List<WebElement> getCityNameWithDepartingBagText() {
		return txt_CityNameWithDepartingBag;
	}

	public List<WebElement> getSeatsIcon() {
		return icn_SeatsIcon;
	}

	public List<WebElement> getSeatSectionAllLegsText() {
		return txt_SeatSectionAllLegs;
	}

	public List<WebElement> getAllBagsInfoText() {
		return txt_AllBagsInfo;
	}

	public List<WebElement> getYourItineraryPassengerInfoSeatNumberText(){
		return txt_YourItineraryPassengerInfoSeatNumber;
	}

	public WebElement getYourItineraryPassengerInfoArrowIcon() {
		return icn_YourItineraryPassengerInfoArrowIcon;
	}

	//*******************************************************************
	//************************Flight Section*****************************
	//*******************************************************************
	public WebElement getFlightSectionFlightChevronImage() {
		return img_FlightSectionFlightChevron;
	}

	public List<WebElement> getFlightSectionTravellingDateText() {
		return txt_FlightSectionTravellingDate;
	}

	public List<WebElement> getFlightSectionFlightNumberText() {
		return txt_FlightSectionFlightNumber;
	}

	public List<WebElement> getFlightSectionDepartingFlightDetailText() {
		return txt_FlightSectionDepartingFlightDetail;
	}

	public List<WebElement> getFlightSectionArrivalFlightDetailText() {
		return txt_FlightSectionArrivalFlightDetail;
	}

	public List<WebElement> getFlightSectionFlightDurationText() {
		return txt_FlightSectionFlightDuration;
	}

	public List<WebElement> getFlightSectionFlightOverlayDurationText() {
		return txt_FlightSectionFlightOverlayDuration;
	}

	public List<WebElement> getOriginalItineraryDepartingFlightCityNameText(){
		return txt_OriginalItineraryDepartingFlightCityName;
	}

	public List<WebElement> getOriginalItineraryArriveFlightCityNameText(){
		return txt_OriginalItineraryArriveFlightCityName;
	}

	//*******************************************************************
	//************************Passenger Section**************************
	//*******************************************************************

	//**********Passenger Details*********

	//************Bags Details************

	//************Seats Details***********
	public List<WebElement> getPassengerSectionSeatFlightLegText() {
		return txt_PassengerSectionSeatFlightLeg;
	}

	public List<WebElement> getPassengerSectionSeatFlightSeatNumberText() {
		return txt_PassengerSectionSeatFlightSeatNumber;
	}

	//**************SSR Details***********

	//*******************************************************************
	//*******************************Options*****************************
	//*******************************************************************
	public WebElement getOptionstHeaderText() {
		return txt_OptionstHeader;
	}

	public WebElement getYourHotelText() {
		return txt_YourHotel;
	}

	public WebElement getYourActivitiesText() {
		return txt_YourActivities;
	}

	public List<WebElement> getActivitiesNameText() {
		return txt_ActivitiesName;
	}

	public List<WebElement> getActivitiesCityNameText() {
		return txt_ActivitiesCityName;
	}

	public List<WebElement> getActivitiesTimeDayDateText() {
		return txt_ActivitiesTimeDayDate;
	}

	public WebElement getYourExtrasText() {
		return txt_YourExtras;
	}

	public WebElement getShortcutSecurityText() {
		return txt_ShortcutSecurity;
	}

	public WebElement getSkipLineText() {
		return txt_SkipLine;
	}

	public WebElement getFlightFlexText() {
		return txt_FlightFlex;
	}

	public WebElement getModifyYourItineraryText() {
		return txt_ModifyYourItinerary;
	}

	public WebElement getShortcutBoardingText() {
		return txt_ShortcutBoarding;
	}

	public WebElement getZonePriorityText() {
		return txt_ZonePriority;
	}

	public WebElement getOptionSectionHotelNameText(){
		return txt_OptionSectionHotelName;
	}

	public WebElement getOptionSectionHotelAddressText(){
		return txt_OptionSectionHotelAddress;
	}

	public WebElement getOptionSectionHotelAddress2Text(){
		return txt_OptionSectionHotelAddress2;
	}

	public WebElement getOptionSectionHotelRoomsText() {
		return txt_OptionSectionHotelRoom;
	}

	public WebElement getOptionSectionHotelGuestCountText() {
		return txt_OptionSectionHotelGuestCount;
	}

	public WebElement getOptionSectionHotelNightsStayText() {
		return txt_OptionSectionHotelNightsStay;
	}

	public WebElement getOptionSectionHotelCheckInText() {
		return txt_OptionSectionHotelCheckIn;
	}

	public WebElement getOptionSelectedHotelCheckInDateText() {
		return txt_OptionSelectedHotelCheckInDate;
	}


	public WebElement getOptionSectionHotelCheckOutText() {
		return txt_OptionSectionHotelCheckOut;
	}

	public WebElement getOptionSelectedHotelCheckOutDateText() {
		return txt_OptionSelectedHotelCheckOutDate;
	}

	//***************Car Section**************
	public WebElement getOptionSectionCarHeaderText() {
		return txt_OptionSectionCarHeader;
	}

	public WebElement getOptionSectionCarRentalAgencyText() {
		return txt_OptionSectionCarRentalAgency;
	}

	public WebElement getOptionSectionCarCarModelText() {
		return txt_OptionSectionCarCarModel;
	}

	public List<WebElement> getOptionSectionCarPickUpAddressText() {
		return txt_OptionSectionCarPickUpAddress;
	}

	public WebElement getOptionSectionCarPickUpTimeText() {
		return txt_OptionSectionCarPickUpTime;
	}

	public WebElement getOptionSectionCarDropOffTimeText() {
		return txt_OptionSectionCarDropOffTime;
	}

	//*******************************************************************
	//********************Active Military Personal***********************
	//*******************************************************************
	public WebElement getActiveMilitrayHeaderText() {
		return txt_ActiveMilitrayHeader;
	}

	public WebElement getActiveMilitraySubHeaderText() {
		return txt_ActiveMilitraySubHeader;
	}

	public List<WebElement> getActiveMilitrayRowsGridView() {
		return grdvew_ActiveMilitrayRows;
	}

	public List<WebElement> getActiveMilitaryVerifyLink() {
		return lnk_ActiveMilitaryVerify;
	}

	public WebElement getActiveMilitaryEmailTextBox() {
		return txtbx_ActiveMilitaryEmail;
	}

	public WebElement getActiveMilitaryPasswordTextBox() {
		return txtbx_ActiveMilitaryPassword;
	}

	public WebElement getActiveMilitarySignInButton() {
		return btn_ActiveMilitarySignIn;
	}

	public WebElement getActiveMilitaryThankYouText(){
		return txt_ActiveMilitaryThankYou;
	}

	public WebElement getActiveMilitaryPleaseVerifyPopUpText(){
		return txt_ActiveMilitaryPleaseVerifyPopUp;
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

	public WebElement getOptionSectionCheckInHeaderText() {
		return txt_OptionSectionCheckInHeader;
	}

	public WebElement getOptionSectionCheckInSubHeaderText() {
		return txt_OptionSectionCheckInSubHeader;
	}

	//*******************************************************************
	//************************Travel Guard*******************************
	//*******************************************************************

	public WebElement getTravelGaurdPanel(){
		return pnl_TravelGaurd;
	}

	public WebElement getTripCostCancellationText() {
		return txt_TripCostCancellation;
	}

	public WebElement getMissedConnectionText() {
		return txt_MissedConnection;
	}

	public WebElement getBaggagedPersonalEffectsText() {
		return txt_BaggagedPersonalEffects;
	}

	public WebElement getTripCostTripInterruptionText() {
		return txt_TripCostTripInterruption;
	}

	public WebElement getTripDelayText() {
		return txt_TripDelay;
	}

	public WebElement getYesTravelGuardLabel() {
		return lbl_YesTravelGuard;
	}

	public WebElement getNoTravelGuardLabel() {
		return lbl_NoTravelGuard;
	}

	public WebElement getPaypalRadioButton() {
		return lbl_PaypalRadioButton;
	}

	public WebElement getPayInFullRadioButton() {
		return lbl_PayInFullRadioButton;
	}

	public WebElement getInsurancePolicyLink() {
		return lnk_InsurancePolicy;
	}

	public WebElement getTravelGuardGroupLink() {
		return lnk_TravelGuardGroup;
	}

	public WebElement getTravelGuardDisclaimerLink() {
		return lnk_TravelGuardDisclaimer;
	}

	public WebElement getHavereadAndUnderstandText() {
		return txt_HavereadAndUnderstand;
	}

	public WebElement getCoverageOfferedText() {
		return txt_CoverageOffered;
	}

	public WebElement getTravelMoreWorryLessText() {
		return txt_TravelMoreWorryLess;
	}

	public List<WebElement> getTravelGaurdCoverageText(){
		return txt_TravelGaurdCoverage;
	}

	//*******************************************************************
	//*****************$9 FARE CLUB DISCOUNT SAVINGS*********************
	//*******************************************************************
	public WebElement getFareClubSavingVerbiageText() {
		return txt_FareClubSavingVerbiage;
	}

	public WebElement getFareClubSavingPriceText() {
		return txt_FareClubSavingPrice;
	}

	//*******************************************************************
	//************************$9FC UpSell********************************
	//*******************************************************************
	public WebElement getMembershipLogInButton(){
		return btn_$9FMembershipLogIn;
	}

	public WebElement getMembershipSignUpButton(){
		return btn_$9FMembershipSignUp;
	}

	public WebElement getTrialMembershipLabel() {
		return lbl_TrialMembership;
	}

	public WebElement getExclusiveMembershipLabel() {
		return lbl_ExclusiveMembership;
	}

	public WebElement getTrialMembershipPriceText() {
		return txt_TrialMembershipPrice;
	}

	public WebElement getTrialMembershipTermsAndConditionsIcon() {
		return icn_TrialMembershipTermsAndConditions;
	}

	public WebElement getMemberTrailTermsAndConditionPanel() {
		return pnl_MemberTrailTermsAndCondition;
	}

	public WebElement getTermsAndConditionsCarLineText() {
		return txt_TermsAndConditionsCarLine;
	}

	public WebElement getExclusiveMembershipPriceText(){
		return txt_ExclusiveMembershipPrice;
	}

	public WebElement getExclusiveMembershipArrowImage(){
		return img_ExclusiveMembershipArrow;
	}

	public WebElement getExclusiveMembership$9FareClubDiscountText(){
		return txt_ExclusiveMembership$9FareClubDiscount;
	}

	public WebElement getExclusiveMembership$9FareClubDiscountPriceText(){
		return txt_ExclusiveMembership$9FareClubDiscountPrice;
	}

	//*******************************************************************
	//************************Total Due Container************************
	//*******************************************************************
	public WebElement getTotalDueText() {
		return txt_TotalDue;
	}

	public WebElement getTotalDuePriceText() {
		return txt_TotalDuePrice;
	}

	public WebElement getTotalDueChevronLink() {
		return lnk_TotalDueChevron;
	}

	public WebElement getTotalDueFlightText() {return txt_TotalDueFlight;}

	public WebElement getTotalDepFlightDueText() {return txt_TotalDepFlightDue;}

	public WebElement getTotalRetFlightDueText() {return txt_TotalRetFlightDue;}

	public WebElement getTotalDuePayMonthlyText(){
		return txt_TotalDuePayMonthly;
	}

	public WebElement getTotalDueDisclaimerText(){
		return txt_TotalDueDisclaimer;
	}

	public List<WebElement> getTotalDueBreakDownText(){
		return txt_TotalDueBreakDown;
	}

	public List<WebElement> getDepOnlyFlightChargeText() {
		return txt_DepOnlyFlightCharge;
	}

	public List<WebElement> getRetOnlyFlightChargeText() {
		return txt_RetOnlyFlightCharge;
	}

	public List<WebElement> getDepTotalBreakDownFlightText() {
		return txt_DepTotalBreakDownFlight;
	}

	public List<WebElement> getRetTotalBreakDownFlightText() {
		return txt_RetTotalBreakDownFlight;
	}

	public WebElement getTotalDueFlightEditText() {
		return txt_TotalDueFlightEdit;
	}

	public WebElement getTotalDueFlightPriceText() {
		return txt_TotalDueFlightPrice;
	}

	public WebElement getTotalDueFlightChevronLink() {
		return lnk_TotalDueFlightChevron;
	}

	public WebElement getTotalDueFlightCAFText() {
		return txt_TotalDueFlightCAF;
	}

	public WebElement getTotalDueFlightCOPUFText() {
		return txt_TotalDueFlightCOPUF;
	}

	public WebElement getTotalDueFlightPUFText() {
		return txt_TotalDueFlightPUF;
	}

	public WebElement getTotalDueBagsText() {
		return txt_TotalDueBags;
	}

	public List<WebElement> getTotalDueBagsBreakDownGridView() {
		return grdvew_TotalDueBagsBreakDown;
	}

	public WebElement getTotalDueBagsEditText() {
		return txt_TotalDueBagsEdit;
	}

	public WebElement getTotalDueBagsPriceText() {
		return txt_TotalDueBagsPrice;
	}

	public WebElement getTotalDueBagsChevronLink() {
		return lnk_TotalDueBagsChevron;
	}

	public WebElement getDepTotalDueBagsVatPriceText() {
		return txt_DepTotalDueBagsVatPrice;
	}

	public WebElement getTotalDueBagsCityText(){
		return txt_TotalDueBagsCity;
	}

	public List<WebElement> getTotalDueBagsCheckedText(){
		return txt_TotalDueBagsChecked;
	}

	public List<WebElement> getTotalDueBagsCarryOnText(){
		return txt_TotalDueBagsCarryOn;
	}


	public WebElement getTotalDueSeatsText() {
		return txt_TotalDueSeats;
	}

	public List<WebElement> getTotalDueSeatsBreakDownGridView() {
		return grdvew_TotalDueSeatsBreakDown;
	}

	public WebElement getTotalDueSeatsEditText() {
		return txt_TotalDueSeatsEdit;
	}

	public WebElement getTotalDueSeatsPriceText() {
		return txt_TotalDueSeatsPrice;
	}

	public WebElement getTotalDueSeatsChevronLink() {
		return lnk_TotalDueSeatsChevron;
	}

	public List<WebElement> getTotalDueSeatsTypeText(){
		return txt_TotalDueSeatsType;
	}

	public WebElement getTotalDueOptionsText() {
		return txt_TotalDueOptions;
	}

	public List<WebElement> getTotalDueOptionsBreakDownGridView() {
		return grdvew_TotalDueOptionsBreakDown;
	}

	public WebElement getTotalDueOptionsEditText() {
		return txt_TotalDueOptionsEdit;
	}

	public WebElement getTotalDueOptionsPriceText() {
		return txt_TotalDueOptionsPrice;
	}

	public List<WebElement> getTotalDuePriceTextList() {
		return txt_TotalDuePriceTextList;
	}

	public WebElement getTotalDueOptionsChevronLink() {
		return lnk_TotalDueOptionsChevron;
	}

	public WebElement getTotalDueUpLiftPriceText() {
		return 	txt_TotalDueUpLiftPrice;
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

	public WebElement getTotalDueChangeFeesText(){
		return txt_TotalDueChangeFees;
	}

	public List<WebElement> getTotalDueChangeFeesPriceText(){
		return txt_TotalDueChangeFeesPrice;
	}

	public WebElement getTotalDueTravelInsuranceText(){
		return txt_TotalDueTravelInsurance;
	}

	public WebElement getTotalDueTravelInsurancePriceText(){
		return txt_TotalDueTravelInsurancePrice;
	}

	public WebElement getTotalDueVouchersAndCreditText(){
		return txt_TotalDueVouchersAndCredit;
	}

	public WebElement getTotalDueVoucherAndCreditPriceText(){
		return txt_TotalDueVoucherAndCreditPrice;
	}

	public WebElement getTotalDueVoucherAndCreditChevronLink(){
		return lnk_TotalDueVoucherAndCreditChevron;
	}

	public List<WebElement> getTotalDueVouchersAndCreditPriceListText(){
		return txt_TotalDueVouchersAndCreditPriceList;
	}

	public List<WebElement> getTotalDueVouchersAndCreditFieldListText(){
		return txt_TotalDueVouchersAndCreditFieldList;
	}



	//*******************************************************************
	//*********************Redeem Voucher Block**************************
	//*******************************************************************

	public WebElement getRedeemVoucherOrCreditLink() {
		return lnk_RedeemVoucherOrCredit;
	}

	public WebElement getRedeemVoucherOrCreditChevronLink() {
		return lnk_RedeemVoucherOrCreditChevron;
	}


	public WebElement getVoucherNumberTextBox(){
		return txtbx_VoucherNumber;
	}

	public WebElement getRedeemReservationCreditSubHeaderText(){
		return txt_RedeemReservationCreditSubHeader;
	}

	public WebElement getRedeemReservationCreditConfirmationCodeText(){
		return txt_RedeemReservationCreditConfirmationCode;
	}

	public WebElement getRedeemReservationCreditFooterText(){
		return txt_RedeemReservationCreditFooter;
	}

	public WebElement getVoucherNumberGoButton(){
		return btn_VoucherNumberGo;
	}
	public WebElement getVoucherNumberApplyButton(){
		return btn_VoucherNumberApply;
	}

	public WebElement getRedeemVoucherConfirmationCodeTextBox(){
		return txtbx_RedeemVoucherConfirmationCode;
	}

	public WebElement getRedeemVoucherGoButton(){
		return btn_RedeemVoucherGo;
	}

	public WebElement getRedeemVoucherAmountPriceText(){return txt_RedeemVoucherAmountPrice;}

	public WebElement getRedeemVoucherAmountToApplyTextBox(){return txtbx_RedeemVoucherAmountToApply;}

	public WebElement getRedeemVoucherAmountToSpendTextBox(){
		return txtbx_RedeemVoucherAmountToSpend;
	}

	public WebElement getRedeemVoucherAmountText(){

		for(WebElement toatlRedeemAmount : txt_RedeemVoucherAmount){
			if(TestUtil.verifyElementDisplayed(toatlRedeemAmount)){
				if(toatlRedeemAmount.getText().contains("$")){
					return toatlRedeemAmount;
				}
			}
		}

		return null;
	}

	public WebElement geRedeemVoucherInvalidNumberRangeText(){

		for(int count = 0 ; count<txt_RedeemVoucherInvalidNumberRange.size(); count++){

			if(TestUtil.verifyElementDisplayed(txt_RedeemVoucherInvalidNumberRange.get(count))){
				return txt_RedeemVoucherInvalidNumberRange.get(count);
			}

		}

		return txt_RedeemVoucherInvalidNumberRange.get(0);
	}

	public WebElement getRedeemReservationCreditAvailableFundsText(){
		return txt_RedeemReservationCreditAvailableFunds;
	}

	public WebElement getRedeemVoucherApplyCreditButton(){
		return btn_RedeemVoucherApplyCredit;
	}

	public WebElement getRedeemVoucherErrorMessageText(){

		for (int count = 0; count<txt_RedeemVoucherErrorMessage.size();count++){

			if(TestUtil.verifyElementDisplayed(txt_RedeemVoucherErrorMessage.get(count))){
				return txt_RedeemVoucherErrorMessage.get(count);
			}

		}
		return txt_RedeemVoucherErrorMessage.get(0);
	}


	//*******************************************************************
	//*********************Reservation Credit Block**********************
	//*******************************************************************
	public WebElement getReservationCreditBlockHeaderText()
	{
		return txt_ReservationCreditBlockHeader;
	}

	public WebElement getReservationCreditBlockSubHeaderText()
	{
		return txt_ReservationCreditBlockSubHeader;
	}

	public WebElement getReservationCreditBlockTypeText()
	{
		return txt_ReservationCreditBlockType;
	}

	public WebElement getReservationCreditBlockPNRText()
	{
		return txt_ReservationCreditBlockPNR;
	}

	public WebElement getReservationCreditBlockPriceText()
	{
		return txt_ReservationCreditBlockPrice;
	}

	public WebElement getReservationCreditBlockDateText()
	{
		return txt_ReservationCreditBlockDate;
	}


	public WebElement getReservationCreditBlockSubFooterText()
	{
		return txt_ReservationCreditBlockSubFooter;
	}

	public WebElement getReservationCreditBlockFooterText()
	{
		return txt_ReservationCreditBlockFooter;
	}

	//*******************************************************************
	//*************************Payment Section***************************
	//*******************************************************************
	public WebElement getPayPalButton() {return btn_PaymentSectionPayPal;}

	public WebElement getPaymentSectionCreditCardText() { return txt_PaymentSectionCreditCard;}

	public WebElement getPaymentSectionUpdateCreditCardLink() { return lnk_PaymentSectionUpdateCreditCard;}

	public WebElement getPaymentSectionUpdateButton() { return btn_PaymentSectionUpdate; }

	public WebElement getPaymentSectionCancelChangesButton() { return btn_PaymentSectionCancelChanges; }

	public WebElement getPaymentSectionSaveCardCheckBox() {
		return chkbx_PaymentSectionSaveCard;
	}

	public WebElement getPaymentSectionSaveCardCLabel() {
		return lbl_PaymentSectionSaveCard;
	}

	public WebElement get_PayPalUpdateLink() {return btn_PayPalUpdateLink;}

	public WebElement get_PayPalDisplay() {return btn_PayPalDisplay;}

	//*******************************************************************
	//*************************Billing Address***************************
	//*******************************************************************

	public WebElement getSelectCardDropDown() { return drpdwn_SelectCard; }

	public WebElement getUseSameAddressCheckBox() { return chkbx_UseSameAddress; }

	public WebElement getUseSameAddressLabel() { return lbl_UseSameAddress; }

	public WebElement getAddressTextbox() { return txtbox_Address; }

	public WebElement getCityTextbox() { return txtbox_City; }

	public WebElement getStateDropdown() { return drpdwn_State; }

	public WebElement getZipCodeTextbox() { return txtbox_ZipCode; }

	public WebElement getCountryDropdown() { return drpdwn_Country; }

	public WebElement getStateTextBox() {return txtbx_State; }

	public WebElement getBillingAddressHorizontalLine(){return line_BillingAddressHorizontal;}

	//*******************************************************************
	//**********************Terms and Continue***************************
	//*******************************************************************
	public WebElement getTermsAndConditionsHeader() {
		return txt_TermsAndConditionsHeader;
	}

	public WebElement getTermsAndConditionsLabel() {
		return lbl_TermsAndConditions;
	}

	public WebElement getTermsAndConditionsHertzLabel() {
		return lbl_TermsAndConditionsHertz;
	}

	public WebElement getTermsAndConditionsErrorMessageText(){
		return txt_TermsAndConditionsErrorMessage;
	}

	public WebElement getBookTripButton() {
		return btn_BookTrip;
	}

	public WebElement getContunueButton(){
		return btn_Contunue;
	}
	//*******************************************************************
	//*********************Travel Guard on POP_UP************************
	//*******************************************************************

	public WebElement getTravelGuardPopupHeaderText() {
		return txt_TravelGuardPopupHeader;
	}

	public WebElement getTravelGuardPopupSubHeaderText() {
		return txt_TravelGuardPopupSubHeader;
	}

	public WebElement getYesTravelGuardPopupLabel() {
		return lbl_YesTravelGuardPopup;
	}

	public WebElement getNoTravelGuardPopupLabel() {
		return lbl_NoTravelGuardPopup;
	}

	//*******************************************************************
	//*********************Manage Travel Buttons************************
	//*******************************************************************
	public WebElement getManageTravelContinueButton() {
		return btn_ManageTravelContinue;
	}

	public WebElement getManageTravelCancelChangesButton() {
		return btn_ManageTravelCancelChanges;
	}

	//*******************************************************************
	//*********************Are You Sure PopUp Method*********************
	//*******************************************************************

	public WebElement getAreYouSurePopUpHeaderText(){
		return txt_AreYouSurePopUpHeader;
	}

	public WebElement getAreYouSurePopUpBodyText(){
		return txt_AreYouSurePopUpBody;
	}

	public WebElement getAreYouSurePopUpContinueToFlightButton(){
		return btn_AreYouSurePopUpContinueToFlight;
	}

	//****************************************************************************
	//*********************Sorry PopUp Milatry Reprice Method*********************
	//****************************************************************************

	public WebElement getSorryPopUpMilitaryRepricePanel(){
		return pnl_SorryPopUpMilitaryReprice;
	}

	public WebElement getSorryPopUpMilitaryRepriceCloseButton(){
		return btn_SorryPopUpMilitaryRepriceClose;
	}


	//****************************************************************************
	//*************************$9 Fare Club PopUp Method**************************
	//****************************************************************************
	public WebElement get9FCPopUpChoosePassWordTextBox() {
		return txtbox_9FCPopUpChoosePassWord;
	}

	public WebElement get9FCPopUpConfirmPassWordTextBox() {
		return txtbox_9FCPopUpConfirmPassWord;
	}

	public WebElement get9FCPopUpSignUpWithEmailButton(){
		return btn_9FCPopUpSignUpWithEmail;
	}
	public WebElement get$9FCFareClubPopUpPanel(){
		return pnl_$9FCFareClubPopUp;
	}

	public WebElement get$9FCFareClubPopUpHeaderText(){
		return txt_$9FCFareClubPopUpHeader;
	}

	public WebElement get$9FCFareClubPopUpCloseImage(){
		return img_$9FCFareClubPopUpClose;
	}

	public WebElement get$9FCFareClubPopUpSubHeaderText(){
		return txt_$9FCFareClubPopUpSubHeader;
	}

	public WebElement get$9FCFareClubPopUpYesIWantButton(){
		return btn_$9FCFareClubPopUpYesIWant;
	}

	public WebElement get$9FCFareClubPopUpNoIDontWantButton(){
		return btn_$9FCFareClubPopUpNoIDontWant;
	}

	public WebElement get$9FCFareClubPopUpYesIWantVerbageText(){
		return txt_$9FCFareClubPopUpYesIWantVerbage;
	}

	public WebElement get$9FCFareClubPopUpNoIDontWantVerbageText(){
		return txt_$9FCFareClubPopUpNoIDontWantVerbage;
	}

	//*******************************************************************
	//**********************PayPal Pop Up***************************
	//*******************************************************************
	public List<WebElement> getPayPalPaymentMethodsLabelList() {return lbl_PayPalPaymentMethods;}

	public WebElement getPayPalEmailTextBox(){ return txtbx_PayPalEmail;}

	public WebElement getPayPalPasswordTextBox(){ return txtbx_PayPalPassword;}

	public WebElement getPayPalNextOnEmailButton(){return btn_PayPalNextOnEmail;}

	public WebElement getPayPalLogInButton(){return btn_PayPalLogIn;}

	public WebElement getPayPalPayNowButton(){return btn_PayPalPayNowButton;}

}