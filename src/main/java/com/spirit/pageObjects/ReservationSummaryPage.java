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


public class ReservationSummaryPage {

	public ReservationSummaryPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*******************************************************************
	//***************************Header Section**************************
	//*******************************************************************
	public final String xpath_HeaderText = "//h1[contains(text(),'Online Check-In')]";
	@FindBy(xpath=xpath_HeaderText)
	private WebElement txt_Header;

	public final String xpath_SubHeaderText = "//h1[contains(text(),'Online Check-In')]/following-sibling::div";
	@FindBy(xpath=xpath_SubHeaderText)
	private List<WebElement> txt_SubHeader;

	public final String xpath_SubHeaderBagsLink= "//a[contains(text(),'bags')]";
	@FindBy(xpath=xpath_SubHeaderBagsLink)
	private WebElement lnk_SubHeaderBags;

	public final String xpath_SubHeaderSeatsLink = "//a[contains(text(),'seats')]";
	@FindBy(xpath=xpath_SubHeaderSeatsLink)
	private WebElement lnk_SubHeaderSeats;

	public final String xpath_SubHeaderBoardingPassText = "//strong[contains(text(),'NOTE: This page is not your boarding pass.')]";
	@FindBy(xpath=xpath_SubHeaderBoardingPassText)
	private WebElement txt_SubHeaderBoardingPass;

	public final String xpath_TravelAdvisoryText = "//div[contains(@class,'row station-advisory')]//p";
	@FindBy(xpath=xpath_TravelAdvisoryText)
	private WebElement txt_TravelAdvisory;

	public final String xpath_TravelAdvisoryLearnMoreLink= "//div[@class='row station-advisory-holder-reservation']//a[contains(text(),'learn more') or contains(text(),'aprende más')]";
	@FindBy(xpath=xpath_TravelAdvisoryLearnMoreLink)
	private WebElement lnk_TravelAdvisoryLearnMore;

	public final String xpath_TopCheckInAndGetBoardingPassButton = "//div[contains(@class,'card check-in-board-container')]//button";
	@FindBy(xpath=xpath_TopCheckInAndGetBoardingPassButton)
	private WebElement btn_TopCheckInAndGetBoardingPass;

	public final String xpath_StatusText = "//Strong[contains(text(),'Status: ')]/..";
	@FindBy(xpath = xpath_StatusText)
	private List<WebElement> txt_Status;

	public final String xpath_ConfirmationCodeNumberText = "//Strong[contains(text(),'Confirmation Code: ') or contains(text(),'Clave de Confirmación:')]/..";
	@FindBy(xpath = xpath_ConfirmationCodeNumberText)
	private List<WebElement> txt_ConfirmationCodeNumber;

	public final String xpath_HotelConfirmationCodeNumberText = "//Strong[contains(text(),'Hotel Confirmation Code: ') or contains(text(),'Confirmación del Hotel: ')]/..";
	@FindBy(xpath = xpath_HotelConfirmationCodeNumberText)
	private List<WebElement> txt_HotelConfirmationCodeNumber;

	public final String xpath_CarConfirmationCodeNumberText = "//Strong[contains(text(),'Car Confirmation Code:') or contains(text(),'Clave de Confirmación del Auto:')]/..";
	@FindBy(xpath = xpath_CarConfirmationCodeNumberText)
	private List<WebElement> txt_CarConfirmationCodeNumber;

	public final String xpath_PrintReceiptButton = "//button[contains(text(),'Print Receipt') or contains(text(),'Imprimir el Recibo')]";
	@FindBy(xpath = xpath_PrintReceiptButton)
	private WebElement btn_PrintReceipt;

	public final String xpath_AddTripToCalendarButton = "//button[contains(text(),'Add Trip to Calendar') or contains(text(),'Agregar Viaje al Calendario')]";
	@FindBy(xpath = xpath_AddTripToCalendarButton)
	private WebElement btn_AddTripToCalendar;

	public final String xpath_YourItineraryReceiptText = "//h1[contains(text(),'Your Itinerary Receipt') or contains(text(),'Tu Recibo de Itinerario')]";
	@FindBy(xpath = xpath_YourItineraryReceiptText)
	private WebElement txt_YourItineraryReceipt;

	public final String xpath_YourItineraryReceiptTotal = "//p[@class='text-right headline2 mb-0']";
	@FindBy(xpath = xpath_YourItineraryReceiptTotal)
	private WebElement txt_YourItineraryTotal;

	public final String xpath_YourReservationChangeText = "//p[@data-private='']/b/..";
	@FindBy(xpath = xpath_YourReservationChangeText)
	private WebElement txt_YourReservationChange;

	//*******************************************************************
	//***************************Flight Section**************************
	//*******************************************************************
	public final String xpath_FlightSectionHeaderText = "//h2[contains(text(),'Flights')]";
	@FindBy(xpath=xpath_FlightSectionHeaderText)
	private WebElement txt_FlightSectionHeader;

	public final String xpath_FlightSectionChangeFlightButton = "//button[contains(text(),'Change Flights') or contains(text(),'Cambiar Vuelos')]";
	@FindBy(xpath=xpath_FlightSectionChangeFlightButton)
	private WebElement btn_FlightSectionChangeFlight;

	public final String xpath_FlightSectionDateIconImage = "//app-flight-itinerary//div[contains(@class,'flight-desktop-container')]//strong//i";
	@FindBy(xpath = xpath_FlightSectionDateIconImage)
	private List<WebElement> img_FlightSectionDateIcon;

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
	//************************Passenger Section**************************
	//*******************************************************************
	public final String xpath_PassengerSectionAddBagsButton = "//button[contains(text(),'Add Bags') or contains(text(),'Añadir Equipaje')]";
	@FindBy(xpath=xpath_PassengerSectionAddBagsButton)
	private List<WebElement> btn_PassengerSectionAddBags;

	public final String xpath_PassengerSectionEditBagsButton = "//button[contains(text(),'Edit Bags') or contains(text(),'Cambiar Equipaje')]";
	@FindBy(xpath=xpath_PassengerSectionEditBagsButton)
	private List<WebElement> btn_PassengerSectionEditBags;

	public final String xpath_PassengerSectionAddSeatsButton = "//button[contains(text(),'Add Seats') or contains(text(),'Añadir Asientos')]";
	@FindBy(xpath=xpath_PassengerSectionAddSeatsButton)
	private List<WebElement> btn_PassengerSectionAddSeats;

	public final String xpath_PassengerSectionEditSeatsButton = "//button[contains(text(),'Edit Seats') or contains(text(),'Cambiar Asientos')]";
	@FindBy(xpath=xpath_PassengerSectionEditSeatsButton)
	private List<WebElement> btn_PassengerSectionEditSeats;

	public final String xpath_PassengerSectionKTNAddButton = "//span[contains(text(),'Known Traveler') or contains(text(),'Viajero Conocido') ]//following::div[1]/button[contains(text(),'Add')]";
	@FindBy(xpath = xpath_PassengerSectionKTNAddButton)
	private List<WebElement> btn_PassengerSectionKTNAdd;

	public final String xpath_PassengerSectionKTNEditButton = "//span[contains(text(),'Known Traveler') or contains(text(),'Viajero Conocido') ]//following::div[1]/button[contains(text(),'Edit')]";
	@FindBy(xpath = xpath_PassengerSectionKTNEditButton)
	private List<WebElement> btn_PassengerSectionKTNEdit;

	public final String xpath_PassengerSectionKTNTextBox = "//input[@id='ktnNumber0']";
	@FindBy(xpath =xpath_PassengerSectionKTNTextBox )
	private List<WebElement> txtbx_PassengerSectionKTN;

	public final String xpath_PassengerSectionKTNSaveButton = "//input[@name='ktnObject']//following::div[2]/button[contains(text(),'save changes') or contains(text(),'GUARDAR')]";
	@FindBy(xpath = xpath_PassengerSectionKTNSaveButton)
	private List<WebElement> btn_PassengerSectionKTNSave;

	public final String xpath_PassengerSectionRedressLabel = "//span[contains(text(),'Redress') or contains(text(),'Núm de Corrección')]";
	@FindBy(xpath = xpath_PassengerSectionRedressLabel)
	private List<WebElement> lbl_PassengerSectionRedress;

	public final String xpath_PassengerSectionRedressAddButton = "//span[contains(text(),'Redress') or contains(text(),'Núm de Corrección')]//following::div[1]//button[contains(text(),'Add')]";
	@FindBy(xpath = xpath_PassengerSectionRedressAddButton)
	private List<WebElement> btn_PassengerSectionRedressAdd;

	public final String xpath_PassengerSectionRedressEditButton = "//span[contains(text(),'Redress') or contains(text(),'Núm de Corrección') ]//following::div[1]/button[contains(text(),'Edit')]";
	@FindBy(xpath = xpath_PassengerSectionRedressEditButton)
	private List<WebElement> btn_PassengerSectionRedressEdit;

	public final String xpath_PassengerSectionAdditionalInfoAddButton = "//button[contains(@class,'additional-info-padding')]";
	@FindBy(xpath = xpath_PassengerSectionAdditionalInfoAddButton)
	private List<WebElement> btn_PassengerSectionAdditionalInfoAdd;

	//*******************************************************************
	//***************Passenger Additional infoSection********************
	//*******************************************************************
	public final String xpath_SpecialServicesText = "//div[contains(text(),'Special Services')]";
	@FindBy(xpath=xpath_SpecialServicesText)
	private WebElement txt_SpecialServices;

	public final String xpath_SpecialServicesIcon = "//div[contains(text(),'Special Services') or contains(text(),' Asistencia Especial ')]//i";
	@FindBy(xpath=xpath_SpecialServicesIcon)
	private WebElement icn_SpecialServices;

	public final String xpath_SpecialServicesMoreInfoLink = "//div[contains(text(),'Special Services') or contains(text(),' Asistencia Especial ')]//div//a[contains(text(),'More Information') or contains(text(),'Más Información')]";
	@FindBy(xpath=xpath_SpecialServicesMoreInfoLink)
	private WebElement lnk_SpecialServicesMoreInfo;

	public final String xpath_HearingImpairedListCheckBox = "//label[contains(@for,'customCheckhearing-impaired')]";
	@FindBy(xpath=xpath_HearingImpairedListCheckBox)
	private WebElement chkbx_HearingImpairedList;

	public final String xpath_VisionDisabilityListCheckBox = "//label[contains(@for,'customCheckvision-disability')]";
	@FindBy(xpath=xpath_VisionDisabilityListCheckBox)
	private WebElement chkbx_VisionDisabilityList;

	public final String xpath_ServiceAnimalListCheckBox = "//label[contains(@for,'customCheckservice-animal')]";
	@FindBy(xpath=xpath_ServiceAnimalListCheckBox)
	private WebElement chkbx_ServiceAnimalList;

	public final String xpath_EmotionalSupportAnimalListCheckBox = "//label[contains(@for,'customCheckemotional-support')]";
	@FindBy(xpath=xpath_EmotionalSupportAnimalListCheckBox)
	private WebElement chkbx_EmotionalSupportAnimalList;

	public final String xpath_EmotionalSupportAnimalListIcon = "//label[contains(@for,'customCheckemotional-support')]/../../following-sibling::span/i";
	@FindBy(xpath=xpath_EmotionalSupportAnimalListIcon)
	private WebElement icn_EmotionalSupportAnimalList;

	public final String xpath_EmotionalSupportAnimalListMoreInfoLink = "//div[contains(text(),'Please view') or contains(text(),'Por favor vea las')]//a";
	@FindBy(xpath=xpath_EmotionalSupportAnimalListMoreInfoLink)
	private WebElement lnk_EmotionalSupportAnimalListMoreInfo;

	public final String xpath_PortableOxygenContainerListCheckBox = "//label[contains(@for,'customCheckportable-oxygen')]";
	@FindBy(xpath=xpath_PortableOxygenContainerListCheckBox)
	private WebElement chkbx_PortableOxygenContainerList;

	public final String xpath_PortableOxygenContainerListIcon = "//label[contains(@for,'customCheckportable-oxygen')]/../../following-sibling::span/i";
	@FindBy(xpath=xpath_PortableOxygenContainerListIcon)
	private WebElement icn_PortableOxygenContainerList;

	public final String xpath_PortableOxygenContainerListMoreInfoLink = "//div[contains(text(),'If you plan') or contains(text(),'Contenedores Portátiles')]//a";
	@FindBy(xpath=xpath_PortableOxygenContainerListMoreInfoLink)
	private WebElement lnk_PortableOxygenContainerListMoreInfo;

	public final String xpath_OtherDisabilityListCheckBox= "//label[contains(@for,'customCheckpage.other')]";
	@FindBy(xpath=xpath_OtherDisabilityListCheckBox)
	private WebElement chkbx_OtherDisabilityList;

	public final String xpath_OtherDisabilityListIcon= "//label[contains(@for,'customCheckpage.other')]/span[2]/i";
	@FindBy(xpath=xpath_OtherDisabilityListIcon)
	private WebElement icn_OtherDisabilityList;

	public final String xpath_OtherDisabilityListMoreInfoLink= "//a[contains(text(),'Click for More Information') or contains(text(),'Haga clic para más inform')]";
	@FindBy(xpath=xpath_OtherDisabilityListMoreInfoLink)
	private WebElement lnk_OtherDisabilityListMoreInfo;

	//******************Wheelchair Services Reservation Page*************
	public final String xpath_WheelChairToAndFromGateListCheckBox= "//input[@name='hasWheelchairToFromGate']//following-sibling::label";
	@FindBy(xpath=xpath_WheelChairToAndFromGateListCheckBox)
	private WebElement chkbx_WheelChairToAndFromGateList;

	public final String xpath_WheelchairIHaveMyOwnListCheckBox= "//input[@name='hasWheelchair']//following-sibling::label";
	@FindBy(xpath=xpath_WheelchairIHaveMyOwnListCheckBox)
	private WebElement chkbx_WheelchairIHaveMyOwnList;

	public final String xpath_WheelChairToAndFromSeatListCheckBox= "//input[@name='hasWheelchairCustomerAisleChair']//following-sibling::label";
	@FindBy(xpath=xpath_WheelChairToAndFromSeatListCheckBox)
	private WebElement chkbx_WheelChairToAndFromSeatList;

	public final String xpath_WheelChairCompletelyImmobileListCheckBox= "//input[@name='hasWheelchairStraightBack']//following-sibling::label";
	@FindBy(xpath=xpath_WheelChairCompletelyImmobileListCheckBox)
	private WebElement chkbx_WheelChairCompletelyImmobileList;

	public final String xpath_WheelChairTypeOfWheelChairDropDown= "//select[@name='wheelChairOwn']";
	@FindBy(xpath = xpath_WheelChairTypeOfWheelChairDropDown)
	private WebElement drpdown_WheelChairTypeOfWheelChair;

	public final String xpath_WheelChairServiceRequestText= "//div[contains(@class,'card card-body')]";
	@FindBy(xpath=xpath_WheelChairServiceRequestText)
	private WebElement txt_WheelChairServiceRequest;

	//**Voluntary Provision of Emergency Services Program Reservation Page*
	public final String xpath_VoluntaryProvisionofEmergencyServicesProgramListCheckBox= "//input[@name='isVolunteer']//following-sibling::label";
	@FindBy(xpath=xpath_VoluntaryProvisionofEmergencyServicesProgramListCheckBox)
	private WebElement chkbx_VoluntaryProvisionofEmergencyServicesProgramList;

	public final String xpath_VoluntaryProvisionofEmergencyServicesProgramListIcon= "//input[@name='isVolunteer']//following-sibling::label//span//i";
	@FindBy(xpath=xpath_VoluntaryProvisionofEmergencyServicesProgramListIcon)
	private WebElement icn_VoluntaryProvisionofEmergencyServicesProgramList;

	public final String xpath_VoluntaryProvisionofEmergencyServicesProgramListMoreInfoLink= "//input[@name='isVolunteer']//following-sibling::label//div//a";
	@FindBy(xpath=xpath_VoluntaryProvisionofEmergencyServicesProgramListMoreInfoLink)
	private WebElement lnk_VoluntaryProvisionofEmergencyServicesProgramListMoreInfo;

	//******************Disability Seating Reservation Page**************
	public final String xpath_DisabilitySeatingLabel= "//span[contains(text(),'Disability Seating') or contains(text(),'Discapacidad para Sentarse')]";
	@FindBy(xpath =xpath_DisabilitySeatingLabel )
	private List<WebElement> lbl_DisabilitySeating;

	public final String xpath_DisabilitySeatingRadioButton= "//input[contains(@id,'customCheckdisabilityhas-disability-seating')]";
	@FindBy(xpath =xpath_DisabilitySeatingRadioButton )
	private List<WebElement> rdbtn_DisabilitySeating;

	public final String xpath_ToBestMeetText= "//div[@class='col-12 mt-3 ng-star-inserted']//div[@class='row'][1]";
	@FindBy(xpath = xpath_ToBestMeetText)
	private WebElement txt_ToBestMeet;

	public final String xpath_TravelingWithServiceEsanLabel= "//label[contains(@for,'customCheckdisabilityhas-support-animal')]";
	@FindBy(xpath = xpath_TravelingWithServiceEsanLabel)
	private List<WebElement> lbl_TravelingWithServiceEsan;

	public final String xpath_TravelingWithServiceEsanRadioButton= "//input[contains(@id,'customCheckdisabilityhas-support-animal')]";
	@FindBy(xpath = xpath_TravelingWithServiceEsanRadioButton)
	private List<WebElement> rdbtn_TravelingWithServiceEsan;

	public final String xpath_LapAnimalText=  "//label[contains(@for,'customCheckdisabilityhas-support-animal')]//following::div[1]/div";
	@FindBy(xpath =xpath_LapAnimalText)
	private List<WebElement> txt_LapAnimal;

	public final String xpath_LapAnimalYesLabel= "//label[contains(@for,'customCheckdisabilityhas-support-animal')]//following::div[1]//span[contains(text(),'Yes') or contains(text(),'Si')]";
	@FindBy(xpath = xpath_LapAnimalYesLabel)
	private List<WebElement> lbl_LapAnimalYes;

	public final String xpath_LapAnimalYesRadioButton= "//label[contains(@for,'customCheckdisabilityhas-support-animal')]//following::div[1]//input[contains(@id,'true')]";
	@FindBy(xpath = xpath_LapAnimalYesRadioButton)
	private List<WebElement> rdbtn_LapAnimalYes;

	public final String xpath_lapAnimalNoLabel= "//label[contains(@for,'customCheckdisabilityhas-support-animal')]//following::div[1]//span[contains(text(),'No')]";
	@FindBy(xpath = xpath_lapAnimalNoLabel)
	private List<WebElement> lbl_lapAnimalNo;

	public final String xpath_lapAnimalNoRadioButton= "//label[contains(@for,'customCheckdisabilityhas-support-animal')]//following::div[1]//input[contains(@id,'false')]";
	@FindBy(xpath = xpath_lapAnimalNoRadioButton)
	private List<WebElement> rdbtn_lapAnimalNo;

	public final String xpath_ImmobileLegLabel= "//label[contains(@for,'customCheckdisabilityhas-immobile-leg')]";
	@FindBy(xpath = xpath_ImmobileLegLabel)
	private List<WebElement> lbl_ImmobileLeg;

	public final String xpath_ImmobileLegRadioButton= "//input[contains(@id,'customCheckdisabilityhas-immobile-leg')]";
	@FindBy(xpath =xpath_ImmobileLegRadioButton)
	private List<WebElement> rdbtn_ImmobileLeg;

	public final String xpath_SeatWithAMoveableAisleArmrestLabel= "//label[contains(@for,'customCheckdisabilityhas-moveable-aisle-armrest')]";
	@FindBy(xpath = xpath_SeatWithAMoveableAisleArmrestLabel)
	private List<WebElement> lbl_SeatWithAMoveableAisleArmrest;

	public final String xpath_SeatWithAMoveableAisleArmrestRadioButton= "//input[contains(@id,'customCheckdisabilityhas-moveable-aisle-armrest')]";
	@FindBy(xpath = xpath_SeatWithAMoveableAisleArmrestRadioButton)
	private List<WebElement> rdbtn_SeatWithAMoveableAisleArmrest;

	public final String xpath_SeatForSomeoneTravelingWithMeLabel= "//label[contains(@for,'customCheckdisabilityhas-is-other-travel')]";
	@FindBy(xpath =xpath_SeatForSomeoneTravelingWithMeLabel)
	private List<WebElement> lbl_SeatForSomeoneTravelingWithMe;

	public final String xpath_SeatForSomeoneTravelingWithMeRadioButton= "//input[contains(@id,'customCheckdisabilityhas-is-other-travel')]";
	@FindBy(xpath = xpath_SeatForSomeoneTravelingWithMeRadioButton)
	private List<WebElement> rdbtn_SeatForSomeoneTravelingWithMe;

	public final String xpath_VisionImpairmentReaderAttenantLabel= "//label[contains(@for,'customCheckdisabilityhas-is-other-vision')]";
	@FindBy(xpath = xpath_VisionImpairmentReaderAttenantLabel)
	private List<WebElement> lbl_VisionImpairmentReaderAttenant;

	public final String xpath_VisionImpairmentReaderAttenantRadioButton= "//input[contains(@id,'customCheckdisabilityhas-is-other-vision')]";
	@FindBy(xpath = xpath_VisionImpairmentReaderAttenantRadioButton)
	private List<WebElement> rdbtn_VisionImpairmentReaderAttenant;

	public final String xpath_VisionImpairmentAttendantNameTextBox= "//input[@id='isOtherVisionName']";
	@FindBy(xpath = xpath_VisionImpairmentAttendantNameTextBox)
	private WebElement txtbx_VisionImpairmentAttendantName;

	public final String xpath_VisionImpairmentAttendantReservationCodeTextBox= "//input[@id='isOtherVisionPnr']";
	@FindBy(xpath = xpath_VisionImpairmentAttendantReservationCodeTextBox)
	private WebElement txtbx_VisionImpairmentAttendantReservationCode;

	public final String xpath_SafteyPersonalCareAttendantLabel= "//label[contains(@for,'customCheckdisabilityhas-is-other-safety')]";
	@FindBy(xpath = xpath_SafteyPersonalCareAttendantLabel)
	private List<WebElement> lbl_SafteyPersonalCareAttendant;

	public final String xpath_SafteyPersonalCareAttendantRadioButton= "//input[contains(@id,'customCheckdisabilityhas-is-other-safety')]";
	@FindBy(xpath = xpath_SafteyPersonalCareAttendantRadioButton)
	private List<WebElement> rdbtn_SafteyPersonalCareAttendant;

	public final String xpath_SafteyPersonalCareAttendantNameTextBox= "//input[@id='isOtherSafetyName']";
	@FindBy(xpath = xpath_SafteyPersonalCareAttendantNameTextBox)
	private WebElement txtbx_SafteyPersonalCareAttendantName;

	public final String xpath_SafteyPersonalCareAttendantReservationCodeTextBox= "//input[@id='isOtherSafetyPnr']";
	@FindBy(xpath = xpath_SafteyPersonalCareAttendantReservationCodeTextBox)
	private WebElement txtbx_SafteyPersonalCareAttendantReservationCode;

	public final String xpath_HearingImpairmentInterpreterRadioButton= "//input[contains(@id,'customCheckdisabilityhas-is-other-hearing')]";
	@FindBy(xpath = xpath_HearingImpairmentInterpreterRadioButton)
	private List<WebElement> rdbtn_HearingImpairmentInterpreter;

	public final String xpath_HearingImpairmentInterpreterLabel= "//label[contains(@for,'customCheckdisabilityhas-is-other-hearing')]";
	@FindBy(xpath = xpath_HearingImpairmentInterpreterLabel)
	private List<WebElement> lbl_HearingImpairmentInterpreter;

	public final String xpath_HearingImpairmentInterpreterNameTextBox= "//input[@id='isOtherHearingName']";
	@FindBy(xpath = xpath_HearingImpairmentInterpreterNameTextBox)
	private WebElement txtbx_HearingImpairmentInterpreterName;

	public final String xpath_HearingImpairmentInterpreterReservationCodeTextBox= "//input[@id='isOtherHearingPnr']";
	@FindBy(xpath = xpath_HearingImpairmentInterpreterReservationCodeTextBox)
	private WebElement txtbx_HearingImpairmentInterpreterReservationCode;

	public final String xpath_OtherDisabilityLabel= "//label[contains(@for,'customCheckdisabilityhas-is-disability-other')]";
	@FindBy(xpath = xpath_OtherDisabilityLabel)
	private List<WebElement> lbl_OtherDisability;

	public final String xpath_OtherDisabilityRadioButton= "//input[contains(@id,'customCheckdisabilityhas-is-disability-other')]";
	@FindBy(xpath = xpath_OtherDisabilityRadioButton)
	private List<WebElement> rdbtn_OtherDisability;

	public final String xpath_OtherDisabilityTextBox= "//input[@id='disabilityOtherDescription']";
	@FindBy(xpath = xpath_OtherDisabilityTextBox)
	private WebElement txtbx_OtherDisability;

	public final String xpath_ErrorMessageText= "//div[contains(@class,'error-text')]";
	@FindBy(xpath = xpath_ErrorMessageText)
	private WebElement txt_ErrorMessage;

	public final String xpath_SSRSaveChangeButton= "(//app-special-assistance-ssrs-input/following-sibling::div//button)[1]";
	@FindBy(xpath=xpath_SSRSaveChangeButton)
	private WebElement btn_SSRSaveChange;

	public final String xpath_SSRCancelButton= "(//app-special-assistance-ssrs-input/following-sibling::div//button)[2]";
	@FindBy(xpath=xpath_SSRCancelButton)
	private WebElement btn_SSRCancel;

	//*******************************************************************
	//************************Footer Buttons*****************************
	//*******************************************************************
	public final String xpath_CheckInAndGetBoardingPassButton= "//button[contains(text(),'Check-In And Get Boarding Pass') or contains(text(),'Regístrarse y Obtener La Tarjeta de Embarque')]";
	@FindBy(xpath=xpath_CheckInAndGetBoardingPassButton)
	private List<WebElement> btn_CheckInAndGetBoardingPass;

	public final String xpath_CancelItineraryButton= "//button[contains(text(),'Cancel Itinerary') or contains(text(),'Cancelar el Itinerario')]";
	@FindBy(xpath=xpath_CancelItineraryButton)
	private WebElement btn_CancelItinerary;

	//*******************************************************************
	//*************************Change Flights Popup**********************
	//*******************************************************************
	public final String xpath_ChangeFlightPopupHeaderText= "//div[@class='modal-header']/h2";
	@FindBy(xpath=xpath_ChangeFlightPopupHeaderText)
	private WebElement txt_ChangeFlightPopupHeader;

	public final String xpath_ChangeFlightPopupCloseButton= "//div[@class='modal-header']/button";
	@FindBy(xpath=xpath_ChangeFlightPopupCloseButton)
	private WebElement btn_ChangeFlightPopupClose;

	public final String xpath_ChangeFlightPopupDepEditLabel= "(//div[contains(@class,'journeyContainer ')])[1]//label[contains(@for,'editCheckbox')]";
	@FindBy(xpath=xpath_ChangeFlightPopupDepEditLabel)
	private WebElement lbl_ChangeFlightPopupDepEdit;

	public final String xpath_ChangeFlightPopupDepFromCityDropDown= "((//div[contains(@class,'journeyContainer ')])[1]//select)[1]";
	@FindBy(xpath=xpath_ChangeFlightPopupDepFromCityDropDown)
	private WebElement drpdn_ChangeFlightPopupDepFromCity;

	public final String xpath_ChangeFlightPopupDepToCityDropDown= "((//div[contains(@class,'journeyContainer ')])[1]//select)[2]";
	@FindBy(xpath=xpath_ChangeFlightPopupDepToCityDropDown)
	private WebElement drpdn_ChangeFlightPopupDepToCity;

	public final String xpath_ChangeFlightPopupDepDateTextBox= "(//div[contains(@class,'journeyContainer ')])[1]//input[@id='date']";
	@FindBy(xpath=xpath_ChangeFlightPopupDepDateTextBox)
	private WebElement txtbx_ChangeFlightPopupDepDate;

	public final String xpath_ChangeFlightPopupRetEditLabel= "(//div[contains(@class,'journeyContainer ')])[2]//label[contains(@for,'editCheckbox')]";
	@FindBy(xpath=xpath_ChangeFlightPopupRetEditLabel)
	private WebElement lbl_ChangeFlightPopupRetEdit;

	public final String xpath_ChangeFlightPopupRetFromCityDropDown = "((//div[contains(@class,'journeyContainer ')])[2]//select)[1]";
	@FindBy(xpath=xpath_ChangeFlightPopupRetFromCityDropDown)
	private WebElement drpdn_ChangeFlightPopupRetFromCity;

	public final String xpath_ChangeFlightPopupRetToCityDropDown= "((//div[contains(@class,'journeyContainer ')])[2]//select)[2]";
	@FindBy(xpath=xpath_ChangeFlightPopupRetToCityDropDown)
	private WebElement drpdn_ChangeFlightPopupRetToCity;

	public final String xpath_ChangeFlightPopupRetDateTextBox= "(//div[contains(@class,'journeyContainer ')])[2]//input[@id='date']";
	@FindBy(xpath=xpath_ChangeFlightPopupRetDateTextBox)
	private WebElement txtbx_ChangeFlightPopupRetDate;

	public final String xpath_ChangeFlightPopupContinueButton= "//div[@class='modal-body']//button[(@class='btn btn-primary')]";
	@FindBy(xpath=xpath_ChangeFlightPopupContinueButton)
	private WebElement btn_ChangeFlightPopupContinue;

	public final String xpath_ChangeFlightPopupCancelButton= "//div[@class='modal-body']//button[contains(@class,'link')]";
	@FindBy(xpath=xpath_ChangeFlightPopupCancelButton)
	private WebElement btn_ChangeFlightPopupCancel;

	//*******************************************************************
	//******************************Bags Popup***************************
	//*******************************************************************
	public final String xpath_BagsPopupHeaderText= "//div[@class='modal-header']//h2[contains(text(),'Bags') or contains(text(),'Equipaje')]";
	@FindBy(xpath=xpath_BagsPopupHeaderText)
	private WebElement txt_BagsPopupHeader;

	public final String xpath_BagsPopupCloseButton= "//div[@class='modal-header']/button";
	@FindBy(xpath=xpath_BagsPopupCloseButton)
	private WebElement btn_BagsPopupClose;

	public final String xpath_BagsPopupSubHeaderText= "//div[@class='modal-body']//p";
	@FindBy(xpath=xpath_BagsPopupSubHeaderText)
	private WebElement txt_BagsPopupSubHeader;

	public final String xpath_BagsPopupPurchaseMyBagsButton= "//div[@class='modal-body']//button[contains(text(),'Purchase My Bags') or contains(text(),'Comprar mi equipaje')]";
	@FindBy(xpath=xpath_BagsPopupPurchaseMyBagsButton)
	private WebElement btn_BagsPopupPurchaseMyBags;

	public final String xpath_BagsPopupDontPurchaseMyBagsButton= "//div[@class='modal-body']//button[contains(text(),'Purchase Bags') or contains(text(),'No comprar equipaje')]";
	@FindBy(xpath=xpath_BagsPopupDontPurchaseMyBagsButton)
	private WebElement btn_BagsPopupDontPurchaseMyBags;

	//*******************************************************************
	//******************************Seats Popup**************************
	//*******************************************************************
	public final String xpath_SeatsPopupHeaderText= "//div[@class='modal-header']//h2[contains(text(),'Seats') or contains(text(),'Asientos')]";
	@FindBy(xpath=xpath_SeatsPopupHeaderText)
	private WebElement txt_SeatsPopupHeader;

	public final String xpath_SeatsPopupCloseButton= "//div[@class='modal-header']/button";
	@FindBy(xpath=xpath_SeatsPopupCloseButton)
	private WebElement btn_SeatsPopupClose;

	public final String xpath_SeatsPopupSubHeaderText= "//div[@class='modal-body']//p";
	@FindBy(xpath=xpath_SeatsPopupSubHeaderText)
	private WebElement txt_SeatsPopupSubHeader;

	public final String xpath_SeatsPopupPurchaseMySeatsButton= "//div[@class='modal-body']//button[contains(text(),'Purchase My Seats') or contains(text(),'Comprar Mis Asientos')]";
	@FindBy(xpath=xpath_SeatsPopupPurchaseMySeatsButton)
	private WebElement btn_SeatsPopupPurchaseMySeats;

	public final String xpath_SeatsPopupDontPurchaseMySeatsButton= "//div[@class='modal-body']//button[contains(text(),'Purchase Seats') or contains(text(),'Continuar Sin Asientos')]";
	@FindBy(xpath=xpath_SeatsPopupDontPurchaseMySeatsButton)
	private WebElement btn_SeatsPopupDontPurchaseMySeats;
	//*******************************************************************
	//*************************Save On Bags Popup************************
	//*******************************************************************
	public final String xpath_SaveOnBagsPopupHeaderText="//div[@class='modal-header']//h2[contains(text(),'Save On Bags')or contains(text(),'Ahorrar En Bolsas')]";
	@FindBy(xpath=xpath_SaveOnBagsPopupHeaderText)
	private WebElement txt_SaveOnBagsPopupHeader;

	public final String xpath_SaveOnBagsPopupCloseButton= "//div[@class='modal-header']/button";
	@FindBy(xpath=xpath_SaveOnBagsPopupCloseButton)
	private WebElement btn_SaveOnBagsPopupClose;

	public final String xpath_SaveOnBagsPopupSubHeaderText= "//div[@class='modal-body']/div[1]/div[1]";
	@FindBy(xpath=xpath_SaveOnBagsPopupSubHeaderText)
	private WebElement txt_SaveOnBagsPopupSubHeader;

	public final String xpath_SaveOnBagsPopupBuyBagsNowButton= "//div[@class='modal-body']//button[contains(text(),'Buy Bags Now')or contains(text(),'Sí')]";
	@FindBy(xpath=xpath_SaveOnBagsPopupBuyBagsNowButton)
	private WebElement btn_SaveOnBagsPopupBuyBagsNow;

	public final String xpath_SaveOnBagsPopupNopeIAmGoodButton= "//div[@class='modal-body']//button[contains(text(),'m good')or contains(text(),'No, No Quiero')]";
	@FindBy(xpath=xpath_SaveOnBagsPopupNopeIAmGoodButton)
	private WebElement btn_SaveOnBagsPopupNopeIAmGood;

	//*******************************************************************
	//*********************Choose Your Seat Popup************************
	//*******************************************************************
	public final String xpath_ChooseYourSeatHeaderText= "//div[@class='modal-header']//h2[contains(text(),'Choose Your Seat')or contains(text(),'Escoja Su Asiento')]";
	@FindBy(xpath=xpath_ChooseYourSeatHeaderText)
	private WebElement txt_ChooseYourSeatHeader;

	public final String xpath_ChooseYourSeatCloseButton= "//div[@class='modal-header']/button";
	@FindBy(xpath=xpath_ChooseYourSeatCloseButton)
	private WebElement btn_ChooseYourSeatClose;

	public final String xpath_ChooseYourSeatSubHeaderText= "//div[@class='modal-body']/div/div";
	@FindBy(xpath=xpath_ChooseYourSeatSubHeaderText)
	private WebElement txt_ChooseYourSeatSubHeader;

	public final String xpath_ChooseYourSeatGetRandomSeatButton= "//div[@class='modal-body']//button[contains(text(),'Get Random Seats') or contains(text(),'Obtener Asientos al Azar')]";
	@FindBy(xpath=xpath_ChooseYourSeatGetRandomSeatButton)
	private WebElement btn_ChooseYourSeatGetRandomSeat;

	public final String xpath_ChooseYourSeatSelectSeatButton= "//div[@class='modal-body']//button[contains(text(),'Select Seats') or contains(text(),'Seleccionar Asientos')]";
	@FindBy(xpath=xpath_ChooseYourSeatSelectSeatButton)
	private WebElement btn_ChooseYourSeatSelectSeat;

	//*******************************************************************
	//**************************Rent a Car Popup*************************
	//*******************************************************************
	public final String xpath_RentACarHeaderText= "//div[@class='modal-header']//h2[contains(text(),'Rent A Car')or contains(text(),'Escoja Su Asiento')]";
	@FindBy(xpath=xpath_RentACarHeaderText)
	private WebElement txt_RentACarHeader;

	public final String xpath_RentACarCloseButton= "//div[@class='modal-header']/button";
	@FindBy(xpath=xpath_RentACarCloseButton)
	private WebElement btn_RentACarClose;

	public final String xpath_RentACarSubHeaderText= "//div[@class='modal-body']/div/div";
	@FindBy(xpath=xpath_RentACarSubHeaderText)
	private WebElement txt_RentACarSubHeader;

	public final String xpath_RentACarContinueButton= "//div[@class='modal-body']//button[contains(text(),'Continue') or contains(text(),'Continuar')]";
	@FindBy(xpath=xpath_RentACarContinueButton)
	private WebElement btn_RentACarContinue;

	public final String xpath_RentACarNoThanksButton= "//div[@class='modal-body']//button[contains(text(),'Continue Without Car') or contains(text(),'No, gracias')]";
	@FindBy(xpath=xpath_RentACarNoThanksButton)
	private WebElement btn_RentACarNoThanks;

	public final String xpath_RentACarSelectCarButton= "//div[@class='modal-body']//button[contains(text(),'Select car') or contains(text(),'Escoja un auto')]";
	@FindBy(xpath=xpath_RentACarSelectCarButton)
	private WebElement btn_RentACarSelectCar;

	//*******************************************************************
	//**********************Hazardous Material Popup*********************
	//*******************************************************************
	public final String xpath_HazardousMaterialPopupHeaderText= "//div[@class='modal-content']//h5[contains(text(),'Bring Hazardous Material') or contains(text(),'No Traiga Materiales Peligrosos')]";
	@FindBy(xpath=xpath_HazardousMaterialPopupHeaderText)
	private WebElement txt_HazardousMaterialPopupHeader;

	public final String xpath_HazardousMaterialSubHeaderText= "//div[@class='modal-body']//div[contains(text(),'Federal') or contains(text(),'La ley federal')]";
	@FindBy(xpath=xpath_HazardousMaterialSubHeaderText)
	private WebElement txt_HazardousMaterialSubHeader;

	public final String xpath_ForbiddenItemHeaderText= "//div[@class='modal-body']//h6[contains(text(),'Forbidden Items') or contains(text(),'Artículos prohibidos')]";
	@FindBy(xpath=xpath_ForbiddenItemHeaderText)
	private WebElement txt_ForbiddenItemHeader;

	public final String xpath_ExplosiveFireworksText= "//ul[@id='forbiddenLeft']//li[contains(text(),'Explosives and Fireworks') or contains(text(),'Explosivos y fuegos artificialesExplosivos y fuegos artificiales')]";
	@FindBy(xpath=xpath_ExplosiveFireworksText)
	private WebElement txt_ExplosiveFireworks;

	public final String xpath_FlammableLiquidText= "//ul[@id='forbiddenLeft']//li[contains(text(),'Flammable Liquids and Solids') or contains(text(),'Líquidos inflamables y sólidos')]";
	@FindBy(xpath=xpath_FlammableLiquidText)
	private WebElement txt_FlammableLiquid;

	public final String xpath_LighterRefillsText= "//ul[@id='forbiddenLeft']//li[contains(text(),'Lighter Refills, Torch Lighters and Strike Anywhere Matches') or contains(text(),'Recargas de encendedores')]";
	@FindBy(xpath=xpath_LighterRefillsText)
	private WebElement txt_LighterRefills;

	public final String xpath_AlcoholAboveProofText= "//ul[@id='forbiddenLeft']//li[contains(text(),'Alcohol above 140 proof (70% alcohol by volume)') or contains(text(),'El alcohol que supere los 140 grados prueba (70% por volumen) ')]";
	@FindBy(xpath=xpath_AlcoholAboveProofText)
	private WebElement txt_AlcoholAboveProof;

	public final String xpath_WeaponsSelfDefenceText= "//ul[@id='forbiddenLeft']//li[contains(text(),'Weapons and Self Defense Spray') or contains(text(),'Armas y gas para defensa personal')]";
	@FindBy(xpath=xpath_WeaponsSelfDefenceText)
	private WebElement txt_WeaponsSelfDefence;

	public final String xpath_ProductUnderSafetyText= "//ul[@id='forbiddenLeft']//li[contains(text(),'Products under Safety Recalls (e.g, batteries)') or contains(text(),'Productos que se han retirado del mercado por seguridad (p.ej. baterías)')]";
	@FindBy(xpath=xpath_ProductUnderSafetyText)
	private WebElement txt_ProductUnderSafety;

	public final String xpath_CompressedGasText= "//ul[@id='forbiddenRight']//li[contains(text(),'Compressed Gas') or contains(text(),'Gases comprimidos')]";
	@FindBy(xpath=xpath_CompressedGasText)
	private WebElement txt_CompressedGas;

	public final String xpath_RadioactiveMaterialsText= "//ul[@id='forbiddenRight']//li[contains(text(),'Radioactive Materials') or contains(text(),'Materiales Radioactivos')]";
	@FindBy(xpath=xpath_RadioactiveMaterialsText)
	private WebElement txt_RadioactiveMaterials;

	public final String xpath_CorrosivesAndOxidizersText= "//ul[@id='forbiddenRight']//li[contains(text(),'Corrosives and Oxidizers') or contains(text(),'Corrosivos y oxidantes')]";
	@FindBy(xpath=xpath_CorrosivesAndOxidizersText)
	private WebElement txt_CorrosivesAndOxidizers;

	public final String xpath_MercuryBarometerText= "//ul[@id='forbiddenRight']//li[contains(text(),'Mercury Barometer or Thermometer') or contains(text(),'Barómetro o termómetro de mercurio')]";
	@FindBy(xpath=xpath_MercuryBarometerText)
	private WebElement txt_MercuryBarometer;

	public final String xpath_PoisonText= "//ul[@id='forbiddenRight']//li[contains(text(),'Poison') or contains(text(),'Veneno')]";
	@FindBy(xpath=xpath_PoisonText)
	private WebElement txt_Poison;

	public final String xpath_HoverboardsText= "//ul[@id='forbiddenRight']//li[contains(text(),'Hoverboards') or contains(text(),'Aeropatinetas')]";
	@FindBy(xpath=xpath_HoverboardsText)
	private WebElement txt_Hoverboards;

	public final String xpath_CarryOnItemsOnlyText= "//div[@class='modal-body']//h6[contains(text(),'Carry-On Only Items') or contains(text(),'Artículos de mano solamente')]";
	@FindBy(xpath=xpath_CarryOnItemsOnlyText)
	private WebElement txt_CarryOnItemsOnly;

	public final String xpath_BatteryPoweredSmokingDeviceText= "//ul[@id='carryOnLeft']//li[contains(text(),'Battery-powered Smoking Devices') or contains(text(),'Dispositivos para fumar impulsados por batería')]";
	@FindBy(xpath=xpath_BatteryPoweredSmokingDeviceText)
	private WebElement txt_BatteryPoweredSmokingDevice;

	public final String xpath_SpareLithiumBatteriesText= "//ul[@id='carryOnLeft']//li[contains(text(),'Spare Lithium Batteries and External Battery Chargers') or contains(text(),'Baterías de litio de repuesto')]";
	@FindBy(xpath=xpath_SpareLithiumBatteriesText)
	private WebElement txt_SpareLithiumBatteries;

	public final String xpath_FuelCellsText= "//ul[@id='carryOnRight']//li[contains(text(),'Fuel Cells') or contains(text(),'Células de combustible')]";
	@FindBy(xpath=xpath_FuelCellsText)
	private WebElement txt_FuelCells;

	public final String xpath_SafetyMatchesAndLightersText= "//ul[@id='carryOnRight']//li[contains(text(),'Safety Matches and Lighters') or contains(text(),'Fósforos de la Seguridad y Encendedores')]";
	@FindBy(xpath=xpath_SafetyMatchesAndLightersText)
	private WebElement txt_SafetyMatchesAndLighters;

	public final String xpath_YouMustIndicateText= "//div[@class='row text-center']//b[contains(text(),'You must indicate') or contains(text(),'Usted debe indicar')]";
	@FindBy(xpath=xpath_YouMustIndicateText)
	private WebElement txt_YouMustIndicate;

	public final String xpath_HazardousMaterialPopupAcceptBoardingPassButton= "//div[@class='modal-body']//button[contains(text(),'Accept & Print Boarding Pass') or contains(text(),'Aceptar e Imprimir la Tarjeta de Embarque')]";
	@FindBy(xpath=xpath_HazardousMaterialPopupAcceptBoardingPassButton)
	private WebElement btn_HazardousMaterialPopupAcceptBoardingPass;

	public final String xpath_HazardousMaterialPopupRejectBoardingPassButton= "//div[@class='modal-body']//button[contains(text(),'Cancel') or contains(text(),'Cancelar')]";
	@FindBy(xpath=xpath_HazardousMaterialPopupRejectBoardingPassButton)
	private WebElement btn_HazardousMaterialPopupRejectBoardingPass;
	//*******************************************************************
	//**********************Your Boarding Pass Popup*********************
	//*******************************************************************
	public final String xpath_YourBoardingPassPopupHeaderText= "//div[@class='modal-header']//h2[contains(text(),'Your Boarding Pass') or contains(text(),'Tu pase de abordar')]";
	@FindBy(xpath=xpath_YourBoardingPassPopupHeaderText)
	private WebElement txt_YourBoardingPassPopupHeader;

	public final String xpath_YourBoardingPassPopUpCloseImage= "//i[@class='icon-close']";
	@FindBy(xpath = xpath_YourBoardingPassPopUpCloseImage)
	private WebElement img_YourBoardingPassPopUpClose;

	public final String xpath_YourBoardingPassPopUpSubHeaderText= "//div[@class='modal-body']/div";
	@FindBy(xpath = xpath_YourBoardingPassPopUpSubHeaderText)
	private WebElement txt_YourBoardingPassPopUpSubHeader;

	public final String xpath_YourBoardingPassPopupPrintBoardingPassOptionsLabel= "//div[@class='modal-body']//span";
	@FindBy(xpath=xpath_YourBoardingPassPopupPrintBoardingPassOptionsLabel)
	private List<WebElement> lbl_YourBoardingPassPopupPrintBoardingPassOptions;

	public final String xpath_YourBoardingPassPopupEmailBoardingPassTextBox= "//input[@id='emailTo']";
	@FindBy(xpath=xpath_YourBoardingPassPopupEmailBoardingPassTextBox)
	private WebElement txtbx_YourBoardingPassPopupEmailBoardingPass;

	public final String xpath_YourBoardingPassPupUpFooterVerbageText= "//div[@class='modal-body']/form/div[contains(@class,'small')]";
	@FindBy(xpath = xpath_YourBoardingPassPupUpFooterVerbageText)
	private WebElement txt_YourBoardingPassPupUpFooterVerbage;

	public final String xpath_YourBoardingPassPopupFinishCheckInButton= "//div[@class='modal-body']//button[contains(text(),'finish Check-In') or contains(text(),'finalizar registro')]";
	@FindBy(xpath=xpath_YourBoardingPassPopupFinishCheckInButton)
	private WebElement btn_YourBoardingPassPopupFinishCheckIn;

	//*******************************************************************
	//************************Your Itinerary Section*********************
	//*******************************************************************
	public final String xpath_PrintItineraryButton= "(//button[contains(text(),'Print Itinerary') or contains(text(),' Imprimir El Itinerario ')])[1]";
	@FindBy(xpath = xpath_PrintItineraryButton)
	private WebElement btn_PrintItinerary;

	//*******************************************************************
	//**********************Fligth Section Top**************************
	//*******************************************************************


	//*******************************************************************
	//*****************Passenger Section Itnerary************************
	//*******************************************************************
	public final String xpath_FreeSpiritNumberText= "//p[contains(text(),'Free Spirit #: ')]";
	@FindBy(xpath = xpath_FreeSpiritNumberText)
	private WebElement txt_FreeSpiritNumber;

	public final String xpath_PassengerNameText= "//i[contains(@class,'icon-user ')]/..//Strong";
	@FindBy(xpath = xpath_PassengerNameText)
	private WebElement txt_PassengerName;
	//*******************************************************************
	//*****************Options Section Itnerary************************
	//*******************************************************************
	public final String xpath_OptionsSectionHeaderText= "//h2[contains(text(),'Options')]";
	@FindBy(xpath = xpath_OptionsSectionHeaderText)
	private WebElement txt_OptionsSectionHeader;

	public final String xpath_OptionsExtrasText= "//div[contains(@class,'extras')]";
	@FindBy(xpath = xpath_OptionsExtrasText)
	private WebElement txt_OptionsExtras;
	//*******************************************************************
	//*****************Contact Section Itnerary************************
	//*******************************************************************
	public final String xpath_ContactSectionNameText= "(//app-contact-info-summary//p)[1]";
	@FindBy(xpath = xpath_ContactSectionNameText)
	private WebElement txt_ContactSectionName;

	public final String xpath_ContactSectionEmailText= "(//app-contact-info-summary//p)[2]";
	@FindBy(xpath = xpath_ContactSectionEmailText)
	private  WebElement txt_ContactSectionEmail;

	public final String xpath_ContactSectionPhoneText= "(//app-contact-info-summary//p)[3]";
	@FindBy(xpath = xpath_ContactSectionPhoneText)
	private  WebElement txt_ContactSectionPhone;

	public final String xpath_ContactSectionVerbageText= "//div[contains(@class,'contact-info-instruc text')]/p";
	@FindBy(xpath = xpath_ContactSectionVerbageText)
	private WebElement txt_ContactSectionVerbage;

	public final String xpath_ContactSectionEditButton= "(//app-contact-info-summary//button)[2]";
	@FindBy(xpath = xpath_ContactSectionEditButton)
	private  WebElement btn_ContactSectionEditButton;

	//*******************************************************************
	//*****************Total Paid Section Itnerary***********************
	//*******************************************************************
	public final String xpath_TotalPaidVerbiageLabel= "//p[contains(text(),'Total Paid') or contains(text(),'Total Pagado')]";
	@FindBy(xpath=xpath_TotalPaidVerbiageLabel)
	private WebElement lbl_TotalPaidVerbiage;

	public final String xpath_TotalPaidPriceText= "//p[contains(text(),'Total Paid') or contains(text(),'Total Pagado')]/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_TotalPaidPriceText)
	private WebElement txt_TotalPaidPrice;

	public final String xpath_TotalPaidChevronLink= "//div[contains(@class,'total-due-container')]//i[contains(@class,'icon-chevron-up')]";
	@FindBy(xpath=xpath_TotalPaidChevronLink)
	private WebElement lnk_TotalPaidChevron;

	public final String xpath_TotalPaidFlightChevronLink= "//div[@class='total-breakdown']//p[contains(text(),'Flight') or contains(text(),'Vuelo')]/../following-sibling::div[2]/p//i";
	@FindBy(xpath=xpath_TotalPaidFlightChevronLink)
	private WebElement lnk_TotalPaidFlightChevron;

	public final String xpath_TotalPaidTaxAndCarrierChargeText= "//div[@class='total-breakdown']//p[contains(text(),'taxes and carrier charges') or contains(text(),'Impuestos y Cargos de Transporte')]";
	@FindBy(xpath = xpath_TotalPaidTaxAndCarrierChargeText)
	private WebElement txt_TotalPaidTaxAndCarrierCharge;

	public final String xpath_TotalPaidOrignalReservationValueText= "//div[@class='total-breakdown']//p[contains(text(),'Original reservation value')]";
	@FindBy(xpath = xpath_TotalPaidOrignalReservationValueText)
	private WebElement txt_TotalPaidOrignalReservationValue;

	public final String xpath_TotalPaidOrignalReservationValuePriceText= "//div[@class='total-breakdown']//p[contains(text(),'Original reservation value')]/../following-sibling::div[2]/p";
	@FindBy(xpath = xpath_TotalPaidOrignalReservationValuePriceText)
	private WebElement txt_TotalPaidOrignalReservationValuePrice;

	public final String xpath_FooterVerbageText= "//app-price-info-summary/following::section//p";
	@FindBy(xpath = xpath_FooterVerbageText)
	private List<WebElement> txt_FooterVerbage;

	//*******************************************************************
	//*****************Passport Information Section *********************
	//*******************************************************************
	public final String xpath_TitleText= "//label[contains(@class,'ng-star-inserted asterisk')][1]";
	@FindBy(xpath = xpath_TitleText)
	private WebElement txt_Title;

	public final String xpath_AddEditPassportInformationLink= "//button[contains(text(),'Add/Edit Passport Information')]";
	@FindBy(xpath = xpath_AddEditPassportInformationLink)
	private WebElement lnk_AddEditPassportInformation;

	public final String xpath_ReservationCreditAmountText= "/html/body/app-root/main/div[2]/app-cancel-reservation/section/div[6]/section/div[2]/div[3]";
	@FindBy(xpath = xpath_ReservationCreditAmountText)
	private WebElement lnk_ReservationCreditAmountText;

	//*******************************************************************
	//*******************Solicit Volunteer Pop-Up**********************
	//*******************************************************************
	public final String xpath_SolicitVolunteerHeader = "//app-volunteer-bid-modal//h2";
	@FindBy(xpath = xpath_SolicitVolunteerHeader)
	private WebElement txt_SolicitVolunteerHeader;

	public final String xpath_SolicitVolunteerCloseButton = "//app-volunteer-bid-modal//button[@class = 'close']";
	@FindBy(xpath = xpath_SolicitVolunteerCloseButton)
	private WebElement btn_SolicitVolunteerClose;

	public final String xpath_SolicitVolunteerFlightFullText = "//app-volunteer-bid-modal//div[@class = 'modal-body']//p[contains(@class , 'bold')][1]";
	@FindBy(xpath = xpath_SolicitVolunteerFlightFullText)
	private WebElement txt_SolicitVolunteerFullFlightText;

	public final String xpath_SolicitVolunteerParagraph1 = "//app-volunteer-bid-modal//div[@class = 'modal-body']//p[2]";
	@FindBy(xpath = xpath_SolicitVolunteerParagraph1 )
	private WebElement txt_SolicitVolunteerParagraph1;

	public final String xpath_SolicitVolunteerParagraph2 = "//app-volunteer-bid-modal//div[@class = 'modal-body']//p[3]";
	@FindBy(xpath = xpath_SolicitVolunteerParagraph2)
	private WebElement txt_SolicitVolunteerParagraph2;

	public final String xpath_SolicitVolunteerParagraph3 = "//app-volunteer-bid-modal//div[@class = 'modal-body']//p[4]";
	@FindBy(xpath = xpath_SolicitVolunteerParagraph3)
	private WebElement txt_SolicitVolunteerParagraph3;

	public final String xpath_SolicitVolunteerBidMessage = "//app-volunteer-bid-modal//div[@class = 'modal-body']//p[contains(@class , 'bold')][2]";
	@FindBy(xpath = xpath_SolicitVolunteerBidMessage)
	private WebElement txt_SolicitVolunteerBidMessage;

	public final String xpath_SolicitVolunteerBid1Button = "(//app-volunteer-bid-modal//form//button)[1]";
	@FindBy(xpath = xpath_SolicitVolunteerBid1Button)
	private WebElement btn_SolicitVolunteerBid1;

	public final String xpath_SolicitVolunteerBid2Button = "(//app-volunteer-bid-modal//form//button)[2]";
	@FindBy(xpath = xpath_SolicitVolunteerBid2Button)
	private WebElement btn_SolicitVolunteerBid2;

	public final String xpath_SolicitVolunteerBid3Button = "(//app-volunteer-bid-modal//form//button)[3]";
	@FindBy(xpath = xpath_SolicitVolunteerBid3Button)
	private WebElement btn_SolicitVolunteerBid3;

	public final String xpath_SolicitVolunteerBid4Button = "(//app-volunteer-bid-modal//form//button)[4]";
	@FindBy(xpath = xpath_SolicitVolunteerBid4Button)
	private WebElement btn_SolicitVolunteerBid4;

	public final String xpath_SolicitVolunteerBidAmountLabel  = "//app-volunteer-bid-modal//form//label";
	@FindBy(xpath = xpath_SolicitVolunteerBidAmountLabel)
	private WebElement lbl_SolicitVolunteerAmount;

	public final String xpath_SolicitVolunteerBidAmountTextBox = "//app-volunteer-bid-modal//form//input[@name = 'bidAmount']";
	@FindBy(xpath = xpath_SolicitVolunteerBidAmountTextBox)
	private WebElement txtbx_SolicitVolunteerAmount;

	public final String xpath_SolicitVolunteerSubmitBidButton = "//app-volunteer-bid-modal//button[contains(text(), 'Submit Bid') or (contains(text(), 'Enviar la oferta'))]";
	@FindBy(xpath = xpath_SolicitVolunteerSubmitBidButton)
	private WebElement btn_SolicitVolunteerSubmitBid;

	public final String xpath_SolicitVolunteerNoThanksButton = "//app-volunteer-bid-modal//button[contains(text(), 'No Thank You') or (contains(text(), 'No, gracias'))] ";
	@FindBy(xpath = xpath_SolicitVolunteerNoThanksButton)
	private WebElement btn_SolicitVolunteerNoThanks;


	//*******************************************************************
	//****************Volunteer Bid Accepted Pop-Up**********************
	//*******************************************************************

	public final String xpath_BidAcceptedHeader = "//app-volunteer-bid-accepted-modal//h2 ";
	@FindBy(xpath = xpath_BidAcceptedHeader)
	private WebElement txt_BidAcceptedHeader;

	public final String xpath_BidAcceptedNoThanksButton = "//app-volunteer-bid-accepted-modal//button[@class = 'close']";
	@FindBy(xpath = xpath_BidAcceptedNoThanksButton)
	private WebElement btn_BidAcceptedExit;

	public final String xpath_BidAcceptedThanksMessage = "//app-volunteer-bid-accepted-modal//p";
	@FindBy(xpath = xpath_BidAcceptedThanksMessage)
	private WebElement btn_BidAcceptedThanksMessage;

	public final String xpath_BidAcceptedCloseButton = "//app-volunteer-bid-accepted-modal//button[contains(text(), 'Close') or (contains(text(), 'Cerrar'))] ";
	@FindBy(xpath = xpath_BidAcceptedCloseButton)
	private WebElement btn_BidAcceptedClose;
	//*****************************************************************************************************************
	//******************************Start of Methods of Reservation Summary Page***************************************
	//*****************************************************************************************************************


	//*******************************************************************
	//***************************Header Section**************************
	//*******************************************************************
	public WebElement getHeaderText() {
		return txt_Header;
	}

	public List<WebElement> getSubHeadeText() {
		return txt_SubHeader;
	}

	public WebElement getSubHeaderBagsText() {
		return lnk_SubHeaderBags;
	}

	public WebElement getSubHeaderSeatsText() {
		return lnk_SubHeaderSeats;
	}

	public WebElement getSubHeaderBoardingPassText() {
		return txt_SubHeaderBoardingPass;
	}


	public WebElement getTravelAdvisoryText() {
		return txt_TravelAdvisory;
	}

	public WebElement getTravelAdvisoryLearnMoreLink() {
		return lnk_TravelAdvisoryLearnMore;
	}

	public WebElement getTopCheckInAndGetBoardingPassButton() {
		return btn_TopCheckInAndGetBoardingPass;
	}

	public List<WebElement> getStatusText(){
		return txt_Status;
	}

	public List<WebElement> getConfirmationCodeNumberText(){return txt_ConfirmationCodeNumber;}

	public List<WebElement> getHotelConfirmationCodeNumberText(){return txt_HotelConfirmationCodeNumber;}

	public List<WebElement> getCarConfirmationCodeNumberText(){return txt_CarConfirmationCodeNumber;}

	public WebElement getPrintReceiptButton(){
		return btn_PrintReceipt;
	}

	public WebElement getAddTripToCalendarButton(){
		return btn_AddTripToCalendar;
	}

	public WebElement getYourItineraryReceiptText(){
		return txt_YourItineraryReceipt;
	}

	public WebElement getYourItineraryReceiptTotal(){
		return txt_YourItineraryTotal;
	}

	public WebElement getYourReservationChangeText(){
		return txt_YourReservationChange;
	}
	//*******************************************************************
	//***************************Flight Section**************************
	//*******************************************************************
	public WebElement getFlightSectionHeaderText() {
		return txt_FlightSectionHeader;
	}

	public WebElement getFlightSectionChangeFlightButton() {
		return btn_FlightSectionChangeFlight;
	}

	public List<WebElement> getFlightSectionDateIconImg() { return img_FlightSectionDateIcon; }

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
	//************************Passenger Section**************************
	//*******************************************************************
	public List<WebElement> getPassengerSectionAddBagsButton() {
		return btn_PassengerSectionAddBags;
	}

	public List<WebElement> getPassengerSectionEditBagsButton() {
		return btn_PassengerSectionEditBags;
	}

	public List<WebElement> getPassengerSectionAddSeatsButton() { return btn_PassengerSectionAddSeats; }

	public List<WebElement> getPassengerSectionEditSeatsButton() { return btn_PassengerSectionEditSeats; }

	public List<WebElement> getPassengerSectionKTNAddButton() { return btn_PassengerSectionKTNAdd; }

	public List<WebElement> getPassengerSectionKTNEditButton() { return btn_PassengerSectionKTNEdit; }

	public List<WebElement> getPassengerSectionKTNTextBox() { return txtbx_PassengerSectionKTN; }

	public List<WebElement> getPassengerSectionKTNSaveButton() { return btn_PassengerSectionKTNSave; }

	public List<WebElement> getPassengerSectionRedressLabel() { return lbl_PassengerSectionRedress; }

	public List<WebElement> getPassengerSectionRedressAddButton() { return btn_PassengerSectionRedressAdd; }

	public List<WebElement> getPassengerSectionRedressEditButton() { return btn_PassengerSectionRedressEdit; }

	public List<WebElement> getPassengerSectionAdditionalInfoAddButton() { return btn_PassengerSectionAdditionalInfoAdd; }

	//*******************************************************************
	//***************Passenger Additional infoSection********************
	//*******************************************************************
	public WebElement getSpecialServicesVerbiage() {
		return txt_SpecialServices;
	}

	public WebElement getSpecialServicesIcon() {
		return icn_SpecialServices;
	}

	public WebElement getSpecialServicesMoreInfoLink() {
		return lnk_SpecialServicesMoreInfo;
	}

	public WebElement getHearingImpairedListCheckBox() {
		return chkbx_HearingImpairedList;
	}

	public WebElement getVisionDisabilityListCheckBox() {
		return chkbx_VisionDisabilityList;
	}

	public WebElement getServiceAnimalListCheckBox() {
		return chkbx_ServiceAnimalList;
	}

	public WebElement getEmotionalSupportAnimalListCheckBox() {
		return chkbx_EmotionalSupportAnimalList;
	}

	public WebElement getEmotionalSupportAnimalListIcon() {
		return icn_EmotionalSupportAnimalList;
	}

	public WebElement getEmotionalSupportAnimalListMoreInfoLink() {
		return lnk_EmotionalSupportAnimalListMoreInfo;
	}

	public WebElement getPortableOxygenContainerListCheckBox() {
		return chkbx_PortableOxygenContainerList;
	}

	public WebElement getPortableOxygenContainerListIcon() {
		return icn_PortableOxygenContainerList;
	}

	public WebElement getPortableOxygenContainerListMoreInfoLink() {
		return lnk_PortableOxygenContainerListMoreInfo;
	}

	public WebElement getOtherDisabilityListCheckBox() {
		return chkbx_OtherDisabilityList;
	}

	public WebElement gettherDisabilityListIcon() {
		return icn_OtherDisabilityList;
	}

	public WebElement getOtherDisabilityListMoreInfoLink() {
		return lnk_OtherDisabilityListMoreInfo;
	}

	//******************Wheelchair Services Reservation Page*************
	public WebElement getWheelChairToAndFromGateListCheckBox() {
		return chkbx_WheelChairToAndFromGateList;
	}

	public WebElement getWheelchairIHaveMyOwnListCheckBox() {
		return chkbx_WheelchairIHaveMyOwnList;
	}

	public WebElement getWheelChairToAndFromSeatListCheckBox() {
		return chkbx_WheelChairToAndFromSeatList;
	}

	public WebElement getWheelChairCompletelyImmobileListCheckBox() {
		return chkbx_WheelChairCompletelyImmobileList;
	}

	public WebElement getWheelChairTypeOfWheelChairDropDown(){
		return drpdown_WheelChairTypeOfWheelChair;
	}

	public WebElement getWheelChairServiceRequestText(){
		return txt_WheelChairServiceRequest;
	}


	//******************Disability Seating Reservation Page**********
	public WebElement getVoluntaryProvisionofEmergencyServicesProgramListCheckBox() {
		return chkbx_VoluntaryProvisionofEmergencyServicesProgramList;
	}

	public WebElement getVoluntaryProvisionofEmergencyServicesProgramListIcon() {
		return icn_VoluntaryProvisionofEmergencyServicesProgramList;
	}

	public WebElement getVoluntaryProvisionofEmergencyServicesProgramListMoreInfoLink() {
		return lnk_VoluntaryProvisionofEmergencyServicesProgramListMoreInfo;
	}

	//******************Disability Seating Reservation Page**************
	public List<WebElement> getDisabilitySeatingLabel() { return lbl_DisabilitySeating; }

	public List<WebElement> getDisabilitySeatingRadioButton() { return rdbtn_DisabilitySeating; }

	public WebElement getToBestMeetVerbiage() { return txt_ToBestMeet; }

	public List<WebElement> getTravelingWithServiceEsanLabel() { return lbl_TravelingWithServiceEsan; }

	public List<WebElement> getTravelingWithServiceEsanRadioButton() { return rdbtn_TravelingWithServiceEsan; }

	public List<WebElement> getLapAnimalText() { return txt_LapAnimal; }

	public List<WebElement> getLapAnimalYesLabel() { return lbl_LapAnimalYes; }

	public List<WebElement> getLapAnimalYesRadioButton() { return rdbtn_LapAnimalYes; }

	public List<WebElement> getLapAnimalNoLabel() { return lbl_lapAnimalNo; }

	public List<WebElement> getlapAnimalNoRadioButton() { return rdbtn_lapAnimalNo; }

	public List<WebElement> getImmobileLegLabel() { return lbl_ImmobileLeg; }

	public List<WebElement> getImmobileLegRadioButton() { return rdbtn_ImmobileLeg; }

	public List<WebElement> getSeatWithAMoveableAisleArmrestLabel() { return lbl_SeatWithAMoveableAisleArmrest; }

	public List<WebElement> getSeatWithAMoveableAisleArmrestRadioButton() { return rdbtn_SeatWithAMoveableAisleArmrest; }

	public List<WebElement> getSeatForSomeoneTravelingWithMeLabel() { return lbl_SeatForSomeoneTravelingWithMe; }

	public List<WebElement> getSeatForSomeoneTravelingWithMeRadioButton() { return rdbtn_SeatForSomeoneTravelingWithMe; }

	public List<WebElement> getVisionImpairmentReaderAttenantLabel() { return lbl_VisionImpairmentReaderAttenant; }

	public List<WebElement> getVisionImpairmentReaderAttenantRadioButton() { return rdbtn_VisionImpairmentReaderAttenant; }

	public WebElement getVisionImpairmentAttendantNameTextbox() { return txtbx_VisionImpairmentAttendantName; }

	public WebElement getVisionImpairmentAttendantReservationCodeTextbox() { return txtbx_VisionImpairmentAttendantReservationCode; }

	public List<WebElement> getSafteyPersonalCareAttendantLabel() { return lbl_SafteyPersonalCareAttendant; }

	public List<WebElement> getSafteyPersonalCareAttendantRadioButton() { return rdbtn_SafteyPersonalCareAttendant; }

	public WebElement getSafteyPersonalCareAttendantNameTextbox() { return txtbx_SafteyPersonalCareAttendantName; }

	public WebElement getSafteyPersonalCareAttendantReservationCodeTextbox() { return txtbx_SafteyPersonalCareAttendantReservationCode; }

	public List<WebElement> getHearingImpairmentInterpreterLabel() { return lbl_HearingImpairmentInterpreter; }

	public List<WebElement> getHearingImpairmentInterpreterRadioButton() { return rdbtn_HearingImpairmentInterpreter; }

	public WebElement getHearingImpairmentInterpreterNameTextBox() { return txtbx_HearingImpairmentInterpreterName; }

	public WebElement getHearingImpairmentInterpreterReservationCodeTextbox() { return txtbx_HearingImpairmentInterpreterReservationCode; }

	public List<WebElement> getOtherDisabilityLabel() { return lbl_OtherDisability; }

	public List<WebElement> getOtherDisabilityRadioButton() { return rdbtn_OtherDisability; }

	public WebElement getOtherDisabilityTextbox() { return txtbx_OtherDisability; }

	public WebElement getErrorMessageText(){
		return txt_ErrorMessage;
	}

	public WebElement getSSRSaveChangeButton(){
		return btn_SSRSaveChange;
	}

	public WebElement getSSRCancelButton(){
		return btn_SSRCancel;
	}

	//*******************************************************************
	//************************Footer Buttons*****************************
	//*******************************************************************
	public List<WebElement> getCheckInAndGetBoardingPassButton() {
		return btn_CheckInAndGetBoardingPass;
	}

	public WebElement getCancelItineraryButton() {
		return btn_CancelItinerary;
	}


	//*******************************************************************
	//*************************Change Flights Popup**********************
	//*******************************************************************
	public WebElement getChangeFlightPopupHeaderText() {
		return txt_ChangeFlightPopupHeader;
	}

	public WebElement getChangeFlightPopupCloseButton() {
		return btn_ChangeFlightPopupClose;
	}

	public WebElement getChangeFlightPopupDepEditLabel() {
		return lbl_ChangeFlightPopupDepEdit;
	}

	public WebElement getChangeFlightPopupDepFromCityDropDown() {
		return drpdn_ChangeFlightPopupDepFromCity;
	}

	public WebElement getChangeFlightPopupDepToCityDropDown() {
		return drpdn_ChangeFlightPopupDepToCity;
	}

	public WebElement getChangeFlightPopupDepDateTextBox() {
		return txtbx_ChangeFlightPopupDepDate;
	}

	public WebElement getChangeFlightPopupRetEditLabel() {
		return lbl_ChangeFlightPopupRetEdit;
	}

	public WebElement getChangeFlightPopupRetFromCityDropDown() {
		return drpdn_ChangeFlightPopupRetFromCity;
	}

	public WebElement getChangeFlightPopupRetToCityDropDown() {
		return drpdn_ChangeFlightPopupRetToCity;
	}

	public WebElement getChangeFlightPopupRetDateTextBox() {
		return txtbx_ChangeFlightPopupRetDate;
	}

	public WebElement getChangeFlightPopupContinueButton() {
		return btn_ChangeFlightPopupContinue;
	}

	public WebElement getChangeFlightPopupCancelButton() {
		return btn_ChangeFlightPopupCancel;
	}

	//*******************************************************************
	//******************************Bags Popup***************************
	//*******************************************************************
	public WebElement getBagsPopupHeaderText() {
		return txt_BagsPopupHeader;
	}

	public WebElement getBagsPopupCloseButton() {
		return btn_BagsPopupClose;
	}

	public WebElement getBagsPopupSubHeaderText() {
		return txt_BagsPopupSubHeader;
	}

	public WebElement getBagsPopupPurchaseMyBagsButton() {
		return btn_BagsPopupPurchaseMyBags;
	}

	public WebElement getBagsPopupDontPurchaseMyBagsButton() {
		return btn_BagsPopupDontPurchaseMyBags;
	}

	//*******************************************************************
	//******************************Seats Popup**************************
	//*******************************************************************
	public WebElement getSeatsPopupHeaderText() {
		return txt_SeatsPopupHeader;
	}

	public WebElement getSeatsPopupCloseButton() {
		return btn_SeatsPopupClose;
	}

	public WebElement getSeatsPopupSubHeaderText() {
		return txt_SeatsPopupSubHeader;
	}

	public WebElement getSeatsPopupPurchaseMySeatsButton() {
		return btn_SeatsPopupPurchaseMySeats;
	}

	public WebElement getSeatsPopupDontPurchaseMySeatsButton() {
		return btn_SeatsPopupDontPurchaseMySeats;
	}

	//*******************************************************************
	//*************************Save On Bags Popup************************
	//*******************************************************************
	public WebElement getSaveOnBagsPopupHeaderText() {
		return txt_SaveOnBagsPopupHeader;
	}

	public WebElement getSaveOnBagsPopupSubHeaderText() {
		return txt_SaveOnBagsPopupSubHeader;
	}

	public WebElement getSaveOnBagsPopupCloseButton(){
		return btn_SaveOnBagsPopupClose;
	}

	public WebElement getSaveOnBagsPopupBuyBagsNowButton() {
		return btn_SaveOnBagsPopupBuyBagsNow;
	}

	public WebElement getSaveOnBagsPopupNopeIAmGoodButton() {
		return btn_SaveOnBagsPopupNopeIAmGood;
	}

	//*******************************************************************
	//*********************Choose Your Seat Popup************************
	//*******************************************************************
	public WebElement getChooseYourSeatHeaderText() {
		return txt_ChooseYourSeatHeader;
	}

	public WebElement getChooseYourSeatCloseButton() {
		return btn_ChooseYourSeatClose;
	}

	public WebElement getChooseYourSeatSubHeaderText() {
		return txt_ChooseYourSeatSubHeader;
	}

	public WebElement getChooseYourSeatGetRandomSeatButton() {
		return btn_ChooseYourSeatGetRandomSeat;
	}

	public WebElement getChooseYourSeatSelectSeatButton() {
		return btn_ChooseYourSeatSelectSeat;
	}

	//*******************************************************************
	//**************************Rent a Car Popup*************************
	//*******************************************************************
	public WebElement getRentACarHeaderText() {
		return txt_RentACarHeader;
	}

	public WebElement getRentACarCloseButton() {
		return btn_RentACarClose;
	}

	public WebElement getRentACarSubHeaderText() {
		return txt_RentACarSubHeader;
	}

	public WebElement getRentACarContinueButton() {
		return btn_RentACarContinue;
	}

	public WebElement getRentACarNoThanksButton() {
		return btn_RentACarNoThanks;
	}

	public WebElement getRentACarSelectCarButton() {
		return btn_RentACarSelectCar;
	}
	//*******************************************************************
	//**********************Hazardous Material Popup*********************
	//*******************************************************************
	public WebElement getHazardousMaterialPopupHeaderText() {
		return txt_HazardousMaterialPopupHeader;
	}

	public WebElement getHazardousMaterialSubHeaderText() {
		return txt_HazardousMaterialSubHeader;
	}

	public WebElement getForbiddenItemText() {
		return txt_ForbiddenItemHeader;
	}

	public WebElement getExplosiveFireworksText() {
		return txt_ExplosiveFireworks;
	}

	public WebElement getFlammableLiquidText() {
		return txt_FlammableLiquid;
	}

	public WebElement getLighterRefillsText() {
		return txt_LighterRefills;
	}

	public WebElement getAlcoholAboveProofText() {
		return txt_AlcoholAboveProof;
	}

	public WebElement getWeaponsSelfDefenceText() {
		return txt_WeaponsSelfDefence;
	}

	public WebElement getProductUnderSafetyText() {
		return txt_ProductUnderSafety;
	}

	public WebElement getCompressedGasText() {
		return txt_CompressedGas;
	}

	public WebElement getRadioactiveMaterialsText() {
		return txt_RadioactiveMaterials;
	}

	public WebElement getCorrosivesAndOxidizersText() {
		return txt_CorrosivesAndOxidizers;
	}

	public WebElement getMercuryBarometerText() {
		return txt_MercuryBarometer;
	}

	public WebElement getPoisonText() {
		return txt_Poison;
	}

	public WebElement getHoverboardsText() {
		return txt_Hoverboards;
	}

	public WebElement getCarryOnItemsOnlyText() {
		return txt_CarryOnItemsOnly;
	}

	public WebElement getBatteryPoweredSmokingDeviceText() {
		return txt_BatteryPoweredSmokingDevice;
	}

	public WebElement getSpareLithiumBatteriesText() {
		return txt_SpareLithiumBatteries;
	}

	public WebElement getFuelCellsText() {
		return txt_FuelCells;
	}

	public WebElement getSafetyMatchesAndLightersText() {
		return txt_SafetyMatchesAndLighters;
	}

	public WebElement getYouMustIndicateText() {
		return txt_YouMustIndicate;
	}

	public WebElement getHazardousMaterialPopupAcceptBoardingPassButton() {
		return btn_HazardousMaterialPopupAcceptBoardingPass;
	}

	public WebElement getHazardousMaterialPopupRejectBoardingPassButton() {
		return btn_HazardousMaterialPopupRejectBoardingPass;
	}


	public WebElement getReservationCreditAmountText() {
		return lnk_ReservationCreditAmountText;
	}


	//*******************************************************************
	//**********************Your Boarding Pass Popup*********************
	//*******************************************************************
	public WebElement getYourBoardingPassPopupHeaderText() {
		return txt_YourBoardingPassPopupHeader;
	}

	public WebElement getYourBoardingPassPopUpCloseImage(){
		return img_YourBoardingPassPopUpClose;
	}

	public WebElement getYourBoardingPassPopUpSubHeaderText(){
		return txt_YourBoardingPassPopUpSubHeader;
	}

	public List<WebElement> getYourBoardingPassPopupPrintBoardingPassOptionsLabel() {
		return lbl_YourBoardingPassPopupPrintBoardingPassOptions;
	}

	public WebElement getYourBoardingPassPopupEmailBoardingPassTextBox() {
		return txtbx_YourBoardingPassPopupEmailBoardingPass;
	}

	public WebElement getYourBoardingPassPopUpFooterVerbageYour(){
		return txt_YourBoardingPassPupUpFooterVerbage;
	}

	public WebElement getYourBoardingPassPopupEmailBoardingPassButton() {
		return btn_YourBoardingPassPopupFinishCheckIn;
	}

	//*******************************************************************
	//*********************Your Itinerary Section Method*****************
	//*******************************************************************

	public WebElement getPrintItineraryButton(){
		return btn_PrintItinerary;
	}

	//*******************************************************************
	//**********************Passenger Section Method************************
	//*******************************************************************

	public WebElement getFreeSpiritNumberText(){
		return txt_FreeSpiritNumber;
	}

	public WebElement getPassengerNameText(){
		return txt_PassengerName;
	}

	//*******************************************************************
	//*****************Options Section Itnerary**************************
	//*******************************************************************
	public WebElement getOptionsSectionHeaderTxt() { return txt_OptionsSectionHeader; }

	public WebElement getOptionsExtras() { return txt_OptionsExtras; }

	//*******************************************************************
	//**********************Contact Section Method***********************
	//*******************************************************************

	public WebElement getContactSectionNameText(){
		return txt_ContactSectionName;
	}

	public WebElement getContactSectionEmailText(){
		return txt_ContactSectionEmail;
	}

	public WebElement getContactSectionPhoneText(){
		return txt_ContactSectionPhone;
	}

	public WebElement getContactSectionVerbageText(){
		return txt_ContactSectionVerbage;
	}

	public WebElement getContactSectionEditButton(){
		return btn_ContactSectionEditButton;
	}

	//*******************************************************************
	//**********************Total Paid Section Method********************
	//*******************************************************************

	public WebElement getTotalPaidVerbiageLabel() {
		return lbl_TotalPaidVerbiage;
	}

	public WebElement getTotalPaidPriceText() {
		return txt_TotalPaidPrice;
	}

	public WebElement getTotalPaidChevronLink(){
		return lnk_TotalPaidChevron;
	}

	public WebElement getTotalPaidFlightChevronLink(){
		return lnk_TotalPaidFlightChevron;
	}

	public WebElement getTotalPaidTaxAndCarrierCharge(){
		return txt_TotalPaidTaxAndCarrierCharge;
	}

	public WebElement getTotalPaidOrignalReservationValueText(){
		return txt_TotalPaidOrignalReservationValue;
	}

	public WebElement getTotalPaidOrignalReservationValuePriceText(){
		return txt_TotalPaidOrignalReservationValuePrice;
	}


	public List<WebElement> getFooterVerbageText(){
		return txt_FooterVerbage;
	}


	//*******************************************************************
	//**********************Possport Section Method**********************
	//*******************************************************************

	public WebElement getTitletext(){
		return txt_Title;
	}

	public WebElement getAddEditPassportInformationLink(){
		return lnk_AddEditPassportInformation;
	}


	//*******************************************************************
	//*******************Solicit Volunteer Methods**********************
	//*******************************************************************
	public WebElement getSolicitVolunteerHeaderText(){
		return txt_SolicitVolunteerHeader;
	}

	public WebElement getSolicitVolunteerCloseButton(){
		return btn_SolicitVolunteerClose;
	}

	public WebElement getSolicitVolunteerFullFlightText(){
		return txt_SolicitVolunteerFullFlightText;
	}

	public WebElement getSolicitVolunteerParagraph1Text(){
		return txt_SolicitVolunteerParagraph1;
	}

	public WebElement getSolicitVolunteerParagraph2Text(){
		return txt_SolicitVolunteerParagraph2;
	}

	public WebElement getSolicitVolunteerParagraph3Text(){
		return txt_SolicitVolunteerParagraph3;
	}

	public WebElement getSolicitVolunteerParagraphBidMessage(){
		return txt_SolicitVolunteerBidMessage;
	}

	public WebElement getSolicitVolunteerBid1Button(){
		return btn_SolicitVolunteerBid1;
	}

	public WebElement getSolicitVolunteerBid2Button(){
		return btn_SolicitVolunteerBid2;
	}

	public WebElement getSolicitVolunteerBid3Button(){
		return btn_SolicitVolunteerBid3;
	}

	public WebElement getSolicitVolunteerBid4Button(){
		return btn_SolicitVolunteerBid4;
	}

	public WebElement getSolicitVolunteerAmountLabel(){
		return lbl_SolicitVolunteerAmount;
	}

	public WebElement getSolicitVolunteerAmountTextBox(){
		return txtbx_SolicitVolunteerAmount;
	}

	public WebElement getSolicitVolunteerSubmitBidButton(){
		return btn_SolicitVolunteerSubmitBid;
	}

	public WebElement getSolicitVolunteerNoThanksButton(){
		return btn_SolicitVolunteerNoThanks;
	}

	//*******************************************************************
	//****************Volunteer Bid Accepted Methods*********************
	//*******************************************************************
	public WebElement getBidAcceptedHeaderText() {
		return txt_BidAcceptedHeader;
	}

	public WebElement getBidAcceptedExitButton() {
		return btn_BidAcceptedExit;
	}

	public WebElement getBidAcceptedThanksMessageText() {
		return btn_BidAcceptedThanksMessage;
	}

	public WebElement getBidAcceptedCloseButton() {
		return btn_BidAcceptedClose;
	}

}