package com.spirit.pageObjects;

import java.util.List;

import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BagsPage {
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

	public BagsPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*******************************************************************
	//************************Top Left Header****************************
	//*******************************************************************
	public final String xpath_HeaderText = "//h1[contains(@class,'headline1')]";
	@FindBy(xpath=xpath_HeaderText)
	private WebElement txt_Header;

	//Salim:Updated Xpath. Issue with tag as showing different tag for English and Spanish
	public final String xpath_MarketingVerbiageText = "//*[contains(text(),'Bringing anything besides your') or contains(text(),'¿Llevará algo además de')]";
	@FindBy(xpath=xpath_MarketingVerbiageText)
	private WebElement txt_MarketingVerbiage;

	//*******************************************************************
	//******************Bag Description Container************************
	//*******************************************************************
	//Personal Item
	public final String xpath_PersonalItemText = "//p[contains(text(),'1 Personal Item') or contains(text(),'1 Artículo Personal')]";
	@FindBy(xpath=xpath_PersonalItemText)
	private WebElement txt_PersonalItem;

	//Salim:Added new Xpath
	//Personal Item Dimensions
	public final String xpath_PersonalItemDimensionsText = "//p[contains(text(),'1 Personal Item') or contains(text(),'1 Artículo Personal')]/following-sibling::p";
	@FindBy(xpath=xpath_PersonalItemDimensionsText)
	private WebElement txt_PersonalItemDimensions;

	//Salim:Added new Xpath
	//Personal Item Price Display
	public final String xpath_PersonalItemPriceDisplayText = "//p[contains(text(),'1 Personal Item') or contains(text(),'1 Artículo Personal')]/following-sibling::p[2]/div";
	@FindBy(xpath=xpath_PersonalItemPriceDisplayText)
	private WebElement txt_PersonalItemPriceDisplay;

	public final String xpath_IncludedYellowCircleImage = "//div[@class='yellow-circle personal-item-circle']";
	@FindBy(xpath=xpath_IncludedYellowCircleImage)
	private WebElement img_IncludedYellowCircle;

	//Carry-On Bag
	public final String xpath_CarryOnBagText = "//p[contains(text(),'Carry-On Bag**') or contains(text(),'Equipaje de Mano**')]";
	@FindBy(xpath=xpath_CarryOnBagText)
	private WebElement txt_CarryOnBag;

	//Salim:Added new Xpath
	//Carry-On Bag Dimension
	public final String xpath_CarryOnBagDimensionsText = "//p[contains(text(),'Carry-On Bag**') or contains(text(),'Equipaje de Mano**')]/following-sibling::p";
	@FindBy(xpath=xpath_CarryOnBagDimensionsText)
	private WebElement txt_CarryOnBagDimensions;

	public final String xpath_IncludedYellowCarryonCircleImage = "//div[@class='yellow-circle carry-on-circle']";
	@FindBy(xpath=xpath_IncludedYellowCarryonCircleImage)
	private WebElement img_IncludedYellowCarryonCircle;

	//Salim:updated Xpath
	//Carry Bag price Display
	public final String xpath_CarryOnBagPriceDisplayText = "//p[contains(@class,'carry-on-text')]/div | //div[contains(text(),'1 Carry-On Bag') or contains(text(),'1 Equipaje de Mano')]/following-sibling::div/div";
	@FindBy(xpath=xpath_CarryOnBagPriceDisplayText)
	private List<WebElement> txt_CarryOnBagPriceDisplay;

	//Checked-Bag
	public final String xpath_CheckedBagText = "//p[contains(text(),'Up to 5 Checked Bags') or contains(text(),'Hasta 5 Equipajes Registrados')]";
	@FindBy(xpath=xpath_CheckedBagText)
	private WebElement txt_CheckedBag;

	//Salim:Added new Xpath
	//Checked Bag Dimension
	public final String xpath_CheckedBagDimensionsText = "//p[contains(text(),'Up to 5 Checked Bags') or contains(text(),'Hasta 5 Equipajes Registrados')]/following-sibling::p";
	@FindBy(xpath=xpath_CheckedBagDimensionsText)
	private WebElement txt_CheckedBagDimensions;

	//Salim:updated Xpath
	//checked Bag price Display
	public final String xpath_CheckedBagPriceDisplayText = "//p[contains(@class,'checked-bags-text')]/div | //div[contains(text(),'5 Checked Bags') or contains(text(),'5 Equipajes Registrados')]/following-sibling::div/div";
	@FindBy(xpath=xpath_CheckedBagPriceDisplayText)
	private List<WebElement> txt_CheckedBagPriceDisplay;

	public final String xpath_IncludedYellowCheckedCircle = "//div[@class='yellow-circle checked-bags-circle']";
	@FindBy(xpath=xpath_IncludedYellowCheckedCircle)
	private WebElement img_IncludedYellowCheckedCircle;

	//***************************************************************
	//********Departing Flights Bags Container***********************
	//***************************************************************
	final String DEPARTING_FLIGHT_CONTAINER = "(//div[@data-qa='flight-bag-container'])[1]";

	//Departure City
	public final String xpath_DepartureCityText = "(//h2[contains(text(),'-')])[1]";
	@FindBy(xpath=xpath_DepartureCityText)
	private WebElement txt_DepartureCity;

	//Salim:updated Xpath
	//Departure Date
	public final String xpath_DepartureDateText = "(//h2[contains(text(),'-')])[1]/../following-sibling::div/p";
	@FindBy(xpath=xpath_DepartureDateText)
	private WebElement txt_DepartureDate;

	//Passenger Container
	public final String xpath_DepartingPassengerFlightContainer = DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='passenger-flight-bag-container']";
	@FindBy(xpath= xpath_DepartingPassengerFlightContainer)
	private List<WebElement> txt_DepartingPassengerFlightContainer;

	//Passenger Name
	public final String xpath_DepartingPassengerNameContainerText = DEPARTING_FLIGHT_CONTAINER + "//h3[contains(@class,'headline4 ')]";
	@FindBy(xpath=xpath_DepartingPassengerNameContainerText)
	private List<WebElement> txt_DepartingPassengerNameContainer;

	//***************************************************************
	//********Departing carry-on Bags Container**********************
	//***************************************************************
	//Carry on Bag Minus Button
	public final String xpath_DepartingCarryOnMinusButton = DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='carry-on-bag']//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_DepartingCarryOnMinusButton)
	private List<WebElement> btn_DepartingCarryOnMinus;

	//Carry on Bag Counter
	public final String xpath_DepartingCarryOnBagCounterTextBox=  DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='carry-on-bag']//div[@class='counter-number']//input";
	@FindBy(xpath=xpath_DepartingCarryOnBagCounterTextBox)
	private List<WebElement> txtbx_DepartingCarryOnBagCounter;

	//Carry On Bag Plus Button
	public final String xpath_DepartingCarryOnPlusButton = DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='carry-on-bag']//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_DepartingCarryOnPlusButton)
	private List<WebElement> btn_DepartingCarryOnPlus;

	//Carry On Bag Price
	public final String xpath_DepartingCarryOnPriceText = DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='carry-on-bag']/div[4]/p";
	@FindBy(xpath= xpath_DepartingCarryOnPriceText)
	private List<WebElement> txt_DepartingCarryOnPrice;

	//Salim : added Xpath
	//Next Carry On Bag Price
	public final String xpath_DepartingNextCarryOnPriceText = DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='carry-on-bag']/div[5]";
	@FindBy(xpath= xpath_DepartingNextCarryOnPriceText)
	private List<WebElement> txt_DepartingNextCarryOnPrice;

	//***************************************************************
	//********Departing Checked  Bags Container**********************
	//***************************************************************
	//Checked Bag Minus Button
	public final String xpath_DepartingCheckedBagMinusButton = DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='checked-bag']//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_DepartingCheckedBagMinusButton)
	private List<WebElement> btn_DepartingCheckedBagMinus;

	//Checked Bag Counter
	public final String xpath_DepartingCheckedBagCounterTextBox =  DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='checked-bag']//div[@class='counter-number']//input";
	@FindBy(xpath=xpath_DepartingCheckedBagCounterTextBox)
	private List<WebElement> txtbx_DepartingCheckedBagCounter;

	//Checked Bag Plus Button
	public final String xpath_DepartingCheckedBagPlusButton = DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='checked-bag']//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_DepartingCheckedBagPlusButton)
	private List<WebElement> btn_DepartingCheckedBagPlus;

	//Checked Bag Price
	public final String xpath_DepartingCheckedBagPriceText = DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='checked-bag']/div[4]/p";
	@FindBy(xpath= xpath_DepartingCheckedBagPriceText)
	private List<WebElement> txt_DepartingCheckedBagPrice;

	//Salim: added Xpath
	//Next Checked Bag Price
	public final String xpath_DepartingNextCheckedBagPriceText = DEPARTING_FLIGHT_CONTAINER + "//div[@data-qa='checked-bag']/div[5]";
	@FindBy(xpath= xpath_DepartingNextCheckedBagPriceText)
	private List<WebElement> txt_DepartingNextCheckedBagPrice;

	//***************************************************************
	//********Departure Sporting Equipment Container*****************
	//***************************************************************

	//Salim:updated Xpath
	//Sporting Equipment Expansion Caret
	public final String xpath_DepartingSportingEquipmentLinkButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Sporting Equipment') or contains(text(),'Equipo Deportivo')]";
	@FindBy(xpath= xpath_DepartingSportingEquipmentLinkButton)
	private List<WebElement> lnkbtn_DepartingSportingEquipment;

	//Salim:updated Name
	//More Info Icon Close (x)
	public final String xpath_SportingEquipmentToolTipCloseImage = "//a[contains(@class,'icon-close')]";
	@FindBy(xpath= xpath_SportingEquipmentToolTipCloseImage)
	private WebElement img_SportingEquipmentToolTipClose;

	//Salim:updated Name
	//More Info Link
	public final String xpath_SportingEquipmentToolTipMoreInfoLink = "//a[contains(text(),'More Info')]";
	@FindBy(xpath= xpath_SportingEquipmentToolTipMoreInfoLink)
	private WebElement lnk_SportingEquipmentToolTipMoreInfo;

	public final String xpath_DepartingSportingEquipmentBicycleDescriptionText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]";
	@FindBy(xpath= xpath_DepartingSportingEquipmentBicycleDescriptionText)
	private List<WebElement> txt_DepartingSportingEquipmentBicycleDescription;

	//Salim:updated Xpath
	//Bicycle Info Icon
	public final String xpath_DepartingBicycleInfoIconLink = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/i";
	@FindBy(xpath=xpath_DepartingBicycleInfoIconLink)
	private List<WebElement> lnk_DepartingBicycleInfoIcon;

	//Salim:updated Xpath
	//Bicycle Plus Button
	public final String xpath_DepartingBicycleMinusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_DepartingBicycleMinusButton)
	private List<WebElement> btn_DepartingBicycleMinus;

	//Salim:updated Xpath
	//Bicycle Bag Counter
	public final String xpath_DepartingBicycleCounterTextBox = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_DepartingBicycleCounterTextBox)
	private List<WebElement> txtbx_DepartingBicycleCounter;

	//Salim:updated Xpath
	//Bicycle Minus Button
	public final String xpath_DepartingBicyclePlusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_DepartingBicyclePlusButton)
	private List<WebElement> btn_DepartingBicyclePlus;

	//Salim:added Xpath
	//Bicycle Next Price
	public final String xpath_DepartingNextBicyclePriceText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/../..//p[contains(text(),'Add Bicycle') or contains(text(),'Añadir una bicicleta')]";
	@FindBy(xpath= xpath_DepartingNextBicyclePriceText)
	private List<WebElement> txt_DepartingNextBicyclePrice;

	//Salim:updated Xpath
	//Bicycle Price
	public final String xpath_DepartingBicyclePriceText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/../..//p[contains(@class,'price')]";
	@FindBy(xpath= xpath_DepartingBicyclePriceText)
	private List<WebElement> txt_DepartingBicyclePrice;

	public final String xpath_DepartingSportingEquipmentSurfBoardDescriptionText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard') or contains(text(),'Surfboard -')]";
	@FindBy(xpath= xpath_DepartingSportingEquipmentSurfBoardDescriptionText)
	private List<WebElement> txt_DepartingSportingEquipmentSurfBoardDescription;

	//Salim:added Xpath
	//Surfboard Info Icon
	public final String xpath_DepartingSurfBoardInfoIconLink = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/i";
	@FindBy(xpath= xpath_DepartingSurfBoardInfoIconLink)
	private List<WebElement> lnk_DepartingSurfBoardInfoIcon;

	//Salim:updated Xpath
	//SurfBoard Mius Button
	public final String xpath_DepartingSurfBoardMinusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_DepartingSurfBoardMinusButton)
	private List<WebElement> btn_DepartingSurfBoardMinus;

	//Salim:updated Xpath
	//Bicycle Bag Counter
	public final String xpath_DepartingSurfBoardCounterTextBox = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_DepartingSurfBoardCounterTextBox)
	private List<WebElement> txtbx_DepartingSurfBoardCounter;

	//Salim:updated Xpath
	//SurfBoard Plus Button
	public final String xpath_DepartingSurfBoardPlusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_DepartingSurfBoardPlusButton)
	private List<WebElement> btn_DepartingSurfBoardPlus;

	//Salim:added Xpath
	//SurfBoard Next Price
	public final String xpath_DepartingNextSurfBoardPriceText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/../..//p[contains(text(),'Add Surfboard') or contains(text(),'Añadir una Tabla de Surf ')]";
	@FindBy(xpath=xpath_DepartingNextSurfBoardPriceText)
	private List<WebElement> txt_DepartingNextSurfBoardPrice;

	//Salim:updated Xpath
	//SurfBoard Price
	public final String xpath_DepartingSurfBoardPriceText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/../..//p[contains(@class,'price')]";
	@FindBy(xpath= xpath_DepartingSurfBoardPriceText)
	private List<WebElement> txt_DepartingSurfBoardPrice;

	//*******************************************************************
	//********Returning Flights Bags Container***************************
	//*******************************************************************
	//declare constant for return flight
	final String RETURN_FLIGHT_CONTAINER = "(//div[@data-qa='flight-bag-container'])[2]";

	//Departure City
	public final String xpath_ReturnCityText = "(//h2[contains(text(),'-')])[2]";
	@FindBy(xpath=xpath_ReturnCityText)
	private WebElement txt_ReturnCity;

	//ReturnDate
	public final String xpath_ReturnDateText = "(//h2[contains(text(),'-')])[2]/../following-sibling::div/p";
	@FindBy(xpath=xpath_ReturnDateText)
	private WebElement txt_ReturnDate;

	//Salim:update Xpath
	//Keep same bags for all flights
	public final String xpath_KeepSameBagsLabel = "//label[@for='check-keep-same']";
	@FindBy(xpath= xpath_KeepSameBagsLabel)
	private List<WebElement> lbl_KeepSameBags;

	//Passenger Container
	public final String xpath_ReturningPassengerFlightContainerText = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='passenger-flight-bag-container']";
	@FindBy(xpath= xpath_ReturningPassengerFlightContainerText)
	private List<WebElement> txt_ReturningPassengerFlightContainer;

	//***************************************************************
	//********Returning Carry-on Bags Container**********************
	//***************************************************************
	//Carry on Bag Minus Button
	public final String xpath_ReturningCarryOnMinusButton = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='carry-on-bag']//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_ReturningCarryOnMinusButton)
	private List<WebElement> btn_ReturningCarryOnMinus;

	//Carry on Bag Counter
	public final String xpath_ReturningCarryOnCounterTextBox = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='carry-on-bag']//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_ReturningCarryOnCounterTextBox)
	private List<WebElement> txtbx_ReturningCarryOnCounter;

	//Carry On Bag Plus Button
	public final String xpath_ReturningCarryOnPlusButton = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='carry-on-bag']//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_ReturningCarryOnPlusButton)
	private List<WebElement> btn_ReturningCarryOnPlus;

	//Carry On Bag Price
	public final String xpath_ReturningCarryOnPriceText = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='carry-on-bag']/div[4]/p";
	@FindBy(xpath= xpath_ReturningCarryOnPriceText)
	private List<WebElement> txt_ReturningCarryOnPrice;

	//Salim:added Xpath
	//Carry On Bag Price
	public final String xpath_ReturningNextCarryOnPriceText = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='carry-on-bag']/div[5]";
	@FindBy(xpath= xpath_ReturningNextCarryOnPriceText)
	private List<WebElement> txt_ReturningNextCarryOnPrice;

	//***************************************************************
	//********Returning Checked Bags Container***********************
	//***************************************************************
	//Checked Bag Minus Button
	public final String xpath_ReturningCheckedBagMinusButton = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='checked-bag']//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_ReturningCheckedBagMinusButton)
	private List<WebElement> btn_ReturningCheckedBagMinus;

	//Checked Bag Container
	public final String xpath_ReturningCheckedBagCounterTextBox = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='checked-bag']//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_ReturningCheckedBagCounterTextBox)
	private List<WebElement> txtbx_ReturningCheckedBagCounter;

	//Checked Bag Plus Button
	public final String xpath_ReturningCheckedBagPlus = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='checked-bag']//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_ReturningCheckedBagPlus)
	private List<WebElement> btn_ReturningCheckedBagPlus;

	//Checked Bag Price
	public final String xpath_ReturningCheckedBagPriceText = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='checked-bag']/div[4]/p";
	@FindBy(xpath= xpath_ReturningCheckedBagPriceText)
	private List<WebElement> txt_ReturningCheckedBagPrice;

	//Salim:added Xpath
	//Next Checked Bag Price
	public final String xpath_ReturningNextCheckedBagPriceText = RETURN_FLIGHT_CONTAINER + "//div[@data-qa='checked-bag']/div[5]";
	@FindBy(xpath= xpath_ReturningNextCheckedBagPriceText)
	private List<WebElement> txt_ReturningNextCheckedBagPrice;

	//***************************************************************
	//***********Returning Sporting Equipment Container**************
	//***************************************************************

	//Salim:updated Xpath
	//Sporting Equipment Expansion Caret
	public final String xpath_ReturningSportingEquipmentLinkButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Sporting Equipment') or contains(text(),'Equipo Deportivo')]";
	@FindBy(xpath= xpath_ReturningSportingEquipmentLinkButton)
	private List<WebElement> lnkbtn_ReturningSportingEquipment;

	//Salim:updated Xpath
	//Bicycle Info Icon
	public final String xpath_ReturningBicycleInfoIconButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/i";
	@FindBy(xpath= xpath_ReturningBicycleInfoIconButton)
	private List<WebElement> btn_ReturningBicycleInfoIcon;

	//Salim:updated Xpath
	//Bicycle Minus Button
	public final String xpath_ReturningBicycleMinusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_ReturningBicycleMinusButton)
	private List<WebElement> btn_ReturningBicycleMinus;

	//Salim:updated Xpath
	//Bicycle Bag Counter
	public final String xpath_ReturningBicycleCounterTextBox = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_ReturningBicycleCounterTextBox)
	private List<WebElement> txtbx_ReturningBicycleCounter;

	//Salim:updated Xpath
	//Bicycle Plus Button
	public final String xpath_ReturningBicyclePlusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_ReturningBicyclePlusButton)
	private List<WebElement> btn_ReturningBicyclePlus;

	//Salim:added Xpath
	//Bicycle Next Price
	public final String xpath_ReturningNextBicyclePriceText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/../..//p[contains(text(),'Add Bicycle') or contains(text(),'Añadir una bicicleta')]";
	@FindBy(xpath= xpath_ReturningNextBicyclePriceText)
	private List<WebElement> txt_ReturningNextBicyclePrice;

	//Salim:updated Xpath
	//Bicycle Price
	public final String xpath_ReturningBicyclePriceText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Bicycle - ') or contains(text(),'Bicicleta -')]/../..//p[contains(@class,'price')]";
	@FindBy(xpath= xpath_ReturningBicyclePriceText)
	private List<WebElement> txt_ReturningBicyclePrice;

	//Salim:added Xpath
	//Surfboard Info Icon
	public final String xpath_ReturningSurfBoardInfoIconLink = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/i";
	@FindBy(xpath= xpath_ReturningSurfBoardInfoIconLink)
	private List<WebElement> lnk_ReturningSurfBoardInfoIcon;

	//Salim:updated Xpath
	//SurfBoard Plus Button
	public final String xpath_ReturningSurfBoardMinusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_ReturningSurfBoardMinusButton)
	private List<WebElement> btn_ReturningSurfBoardMinus;

	//Salim:updated Xpath
	//SurfBoard Bag Counter
	public final String xpath_ReturningSurfBoardCounterTextBox = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_ReturningSurfBoardCounterTextBox)
	private List<WebElement> txtbx_ReturningSurfBoardCounter;

	//Salim:updated Xpath
	//SurfBoard Plus Button
	public final String xpath_ReturningSurfBoardPlusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_ReturningSurfBoardPlusButton)
	private List<WebElement> btn_ReturningSurfBoardPlus;

	//Salim:added Xpath
	//SurfBoard Next Price
	public final String xpath_ReturningNextSurfBoardPriceText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/../..//p[contains(text(),'Add Surfboard') or contains(text(),'Añadir una Tabla de Surf ')]";
	@FindBy(xpath=xpath_ReturningNextSurfBoardPriceText)
	private List<WebElement> txt_ReturningNextSurfBoardPrice;

	//Salim:updated Xpath
	//SurfBoard Price
	public final String xpath_ReturningSurfBoardPriceText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'Surfboard -') or contains(text(),'Tabla de Surf -')]/../..//p[contains(@class,'price')]";
	@FindBy(xpath= xpath_ReturningSurfBoardPriceText)
	private List<WebElement> txt_ReturningSurfBoardPrice;

	//*******************************************************************
	//*******************Bags Total Bags Page****************************
	//*******************************************************************

	public final String xpath_TotalDueContainerPanel = "//div[contains(@class,'total-due-container')]";
	@FindBy(xpath = xpath_TotalDueContainerPanel)
	private WebElement pnl_TotalDueContainer;

	//Salim:updated Xpath
	//Bags Total Amount text
	public final String xpath_BagsTotalContainerText = "//div[contains(@class,'total-due-container')]//p[contains(text(),'Bags Total') or contains(text(),'Equipaje Total')]";
	@FindBy(xpath=xpath_BagsTotalContainerText)
	private WebElement txt_BagsTotalContainer;

	//Salim:updated Xpath
	//Bags Total Amount
	public final String xpath_BagsTotalContainerAmountTotalText = "//div[contains(@class,'total-due-container')]//p[contains(text(),'$')]";
	@FindBy(xpath=xpath_BagsTotalContainerAmountTotalText)
	private WebElement txt_BagsTotalContainerAmountTotal;

	public final String xpath_OutboundJourneyBreakdownCityPairText = "(//app-breakdown-section/div/div)[1]/p";
	@FindBy(xpath = xpath_OutboundJourneyBreakdownCityPairText)
	private WebElement txt_OutboundJourneyBreakdownCityPair;

	//Salim:updated Xpath
	//Bags Total Amount dropdown
	public final String xpath_BagsTotalContainerLink = "//div[contains(@class,'total-due-container')]//app-chevron//i";
	@FindBy(xpath=xpath_BagsTotalContainerLink)
	private WebElement lnk_BagsTotalContainer;

	public final String xpath_BagsTotalContainerOptionsText = "//app-price-section-line//div[@data-qa='total-options-item']/p";
	@FindBy(xpath = xpath_BagsTotalContainerOptionsText)
	private List<WebElement> txt_BagsTotalContainerOptions;

	//*******************************************************************
	//*****************Bags Total Breakdown Bags Page********************
	//*******************************************************************

	//Salim:Xpath need to be added for verbiage section
	//outbound
	//Salim:updated Xpath
	//Departure Journey Breakdown CarryOn Bag Total
	public final String xpath_OutboundJourneyBreakdownCarryOnBagTotalPriceText = "(//div[@class='total-breakdown']//app-breakdown-section)[1]//p[contains(text(),'Carry-On') or contains(text(),'Equipaje de Mano')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_OutboundJourneyBreakdownCarryOnBagTotalPriceText)
	private WebElement txt_OutboundJourneyBreakdownCarryOnBagTotalPrice;

	//Salim:updated Xpath
	public final String xpath_OutboundJourneyBreakdownCheckedBagTotalPriceText = "(//div[@class='total-breakdown']//app-breakdown-section)[1]//p[contains(text(),'Checked Bags') or contains(text(),'Equipajes Registrados')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_OutboundJourneyBreakdownCheckedBagTotalPriceText)
	private WebElement txt_OutboundJourneyBreakdownCheckedBagTotalPrice;

	//Salim:updated Xpath
	public final String xpath_OutboundJourneyBreakdownBikeTotalPriceText = "(//div[@class='total-breakdown']//app-breakdown-section)[1]//p[contains(text(),'Bike') or contains(text(),'Bicicleta')]/../following-sibling::div/p";
	@FindBy(xpath= xpath_OutboundJourneyBreakdownBikeTotalPriceText)
	private WebElement txt_OutboundJourneyBreakdownBikeTotalPrice;

	//Salim:updated Xpath
	public final String xpath_OutboundJourneyBreakdownSurfTotalPriceText = "(//div[@class='total-breakdown']//app-breakdown-section)[1]//p[contains(text(),'Surf')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_OutboundJourneyBreakdownSurfTotalPriceText)
	private WebElement txt_OutboundJourneyBreakdownSurfTotalPrice;

	public final String xpath_OutboundJourneyBreakdownVatTaxPriceText = "(//div[@class='total-breakdown']//app-breakdown-section)[1]//p[contains(text(),'VAT Tax') or contains(text(),'VAT Tax')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_OutboundJourneyBreakdownVatTaxPriceText)
	private WebElement txt_OutboundJourneyBreakdownVatTaxPrice;

	//Salim:updated Xpath
	//return
	public final String xpath_ReturnJourneyBreakdownCarryOnBagTotalPriceText = "(//div[@class='total-breakdown']//app-breakdown-section)[2]//p[contains(text(),'Carry-On') or contains(text(),'Equipaje de Mano')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_ReturnJourneyBreakdownCarryOnBagTotalPriceText)
	private WebElement txt_ReturnJourneyBreakdownCarryOnBagTotalPrice;

	//Salim:updated Xpath
	public final String xpath_ReturnJourneyBreakdownCheckedBagTotalPriceText = "(//div[@class='total-breakdown']//app-breakdown-section)[2]//p[contains(text(),'Checked Bags') or contains(text(),'Equipajes Registrados')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_ReturnJourneyBreakdownCheckedBagTotalPriceText)
	private WebElement txt_ReturnJourneyBreakdownCheckedBagTotalPrice;

	//Salim:updated Xpath
	public final String xpath_ReturnJourneyBreakdownBikeTotalPriceText = "(//div[@class='total-breakdown']//app-breakdown-section)[2]//p[contains(text(),'Bike') or contains(text(),'Bicicleta')]/../following-sibling::div/p";
	@FindBy(xpath= xpath_ReturnJourneyBreakdownBikeTotalPriceText)
	private WebElement txt_ReturnJourneyBreakdownBikeTotalPrice;

	//Salim:updated Xpath
	public final String xpath_ReturnJourneyBreakdownSurfTotalPriceText = "(//div[@class='total-breakdown']//app-breakdown-section)[2]//p[contains(text(),'Surf')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_ReturnJourneyBreakdownSurfTotalPriceText)
	private WebElement txt_ReturnJourneyBreakdownSurfTotalPrice;

	public final String xpath_ReturnJourneyBreakdownVatTaxPriceText = "(//div[@class='total-breakdown']//app-breakdown-section)[1]//p[contains(text(),'VAT Tax') or contains(text(),'VAT Tax')]/../following-sibling::div/p";
	@FindBy(xpath=xpath_ReturnJourneyBreakdownVatTaxPriceText)
	private WebElement txt_ReturnJourneyBreakdownVatTaxPrice;

	//*******************************************************************
	//***************Continue With FareClub Bags Page********************
	//*******************************************************************

	public final String xpath_ContinueWith9FCBagsContainerHeaderText = "//div[contains(@class,'fare-club')]//p[contains(text(),'Fare Club')]";
	@FindBy(xpath=xpath_ContinueWith9FCBagsContainerHeaderText)
	private WebElement txt_ContinueWith9FCBagsContainerHeader;

	public final String xpath_ContinueWith9FCBagsContainerPriceText = "//div[contains(@class,'fare-club')]//p[contains(@class,'headline2')][contains(text(),'.')]";
	@FindBy(xpath=xpath_ContinueWith9FCBagsContainerPriceText)
	private WebElement txt_ContinueWith9FCBagsContainerPrice;

	//Salim:updated Xpath
	public final String xpath_ContinueWith9FCBagsContainerSavingsText = "//div[contains(@class,'fare-club')]//p[contains(text(),'Save') or contains(text(),'Ahorre')]";
	@FindBy(xpath=xpath_ContinueWith9FCBagsContainerSavingsText)
	private WebElement txt_ContinueWith9FCBagsContainerSavings;

	//Salim:updated Xpath
	public final String xpath_ContinueWith9FCBagsContainerContinueButton = "//div[contains(@class,'fare-club')]//button[contains(text(),'Continue') or contains(text(),'Continuar')]";
	@FindBy(xpath=xpath_ContinueWith9FCBagsContainerContinueButton)
	private WebElement btn_ContinueWith9FCBagsContainerContinue;

	//*******************************************************************
	//***************Continue With Standard Bags Page********************
	//*******************************************************************
	//Salim:updated Xpath
	public final String xpath_ContinueWithStandardBagsContainerHeaderText = "//div[contains(@class,'standard-pricing')]//p[contains(text(),'Standard') or contains(text(),'Estándar')]";
	@FindBy(xpath=xpath_ContinueWithStandardBagsContainerHeaderText)
	private WebElement txt_ContinueWithStandardBagsContainerHeader;

	public final String xpath_ContinueWithStandardBagsContainerPriceText = "//div[contains(@class,'standard-pricing')]//p[contains(@class,'headline2')][contains(text(),'.')]";
	@FindBy(xpath=xpath_ContinueWithStandardBagsContainerPriceText)
	private WebElement txt_ContinueWithStandardBagsContainerPrice;

	//Salim:updated Xpath
	public final String xpath_ContinueWithStandardBagsContainerContinueButton = "//div[contains(@class,'standard-pricing')]//button[contains(text(),'Continue') or contains(text(),'Continuar')]";
	@FindBy(xpath=xpath_ContinueWithStandardBagsContainerContinueButton)
	private WebElement btn_ContinueWithStandardBagsContainerContinue;

	//*******************************************************************
	//**********Fare Club SignUp Without Bags Page***********************
	//*******************************************************************
	//Salim:Pending for Review
	//Salim Xpath is missing for the Login as 9DFC member
	public final String xpath_9FCPopUpHeaderText = "//h2[@class='modal-title']";
	@FindBy(xpath=xpath_9FCPopUpHeaderText)
	private WebElement txt_9FCPopUpHeader;

	public final String xpath_9FCPopUpCloseButton = "//button[@class='close']//i";
	@FindBy(xpath=xpath_9FCPopUpCloseButton)
	private WebElement btn_9FCPopUpClose;

	public final String id_9FCPopUpUserNameTextBox = "username";
	@FindBy(id=id_9FCPopUpUserNameTextBox)
	private WebElement txtbx_9FCPopUpUserName;

	public final String id_9FCPopUpLogInPasswordTextBox = "password";
	@FindBy(id=id_9FCPopUpLogInPasswordTextBox)
	private WebElement txtbx_9FCPopUpLogInPassword;

	public final String xpath_9FCPopUpResetPasswordTextBox = "//button[contains(text(),'Reset Password') or contains(text(),' Restablecer la Contraseña')]";
	@FindBy(xpath=xpath_9FCPopUpResetPasswordTextBox)
	private WebElement txtbx_9FCPopUpResetPassword;

	public final String xpath_9FCUpsellLogInButton = "//button[contains(text(),'Log In') or contains(text(),'Inicia Sesión')]";
	@FindBy(xpath=xpath_9FCUpsellLogInButton)
	private WebElement btn_9FCUpsellLogIn;

	public final String xpath_ContinueWithStandardBagsOn9FCPopUpButton = "//button[contains(text(),'continue with standard fares')]";
	@FindBy(xpath=xpath_ContinueWithStandardBagsOn9FCPopUpButton) //no Spanish translation
	private WebElement btn_ContinueWithStandardBagsOn9FCPopUp;

	public final String xpath_WayToGoSaverText = "//div[contains(@class,'loyalty-savings-card')]/h5";
	@FindBy(xpath = xpath_WayToGoSaverText)
	private WebElement txt_WayToGoSaver;

	//*******************************************************************
	//***************Purchase Seat Popup(CheckIn)************************
	//*******************************************************************
	public final String xpath_CheckInPurchaseSeatPopupHeaderText = "//div[@class='modal-header']/h2";
	@FindBy(xpath=xpath_CheckInPurchaseSeatPopupHeaderText)
	private WebElement txt_CheckInPurchaseSeatPopupHeader;

	public final String xpath_CheckInPurchaseSeatPopupCloseButton = "//div[@class='modal-header']/button";
	@FindBy(xpath=xpath_CheckInPurchaseSeatPopupCloseButton)
	private WebElement btn_CheckInPurchaseSeatPopupClose;

	public final String xpath_CheckInPurchaseSeatPopupSubHeaderText = "//div[@class='modal-body']//p";
	@FindBy(xpath=xpath_CheckInPurchaseSeatPopupSubHeaderText)
	private WebElement txt_CheckInPurchaseSeatPopupSubHeader;

	public final String xpath_CheckInPurchaseSeatPopupPurchaseMySeatsButton = "//div[@class='modal-body']//button[contains(@class,'primary')]";
	@FindBy(xpath=xpath_CheckInPurchaseSeatPopupPurchaseMySeatsButton)
	private WebElement btn_CheckInPurchaseSeatPopupPurchaseMySeats;

	public final String xpath_CheckInPurchaseSeatPopupDontPurchaseMySeatsButton = "//div[@class='modal-body']//button[contains(@class,'secondary')]";
	@FindBy(xpath=xpath_CheckInPurchaseSeatPopupDontPurchaseMySeatsButton)
	private WebElement btn_CheckInPurchaseSeatPopupDontPurchaseMySeats;

	//*******************************************************************
	//***************Continue Without Bags Page**************************
	//*******************************************************************
	public final String xpath_ContinueWithouAreYouSureHeaderText = "//h2[contains(text(),'Are you sure?') or contains(text(),'¿Está Seguro?')]";
	@FindBy(xpath = xpath_ContinueWithouAreYouSureHeaderText)
	private  WebElement txt_ContinueWithouAreYouSureHeader;

	public final String xpath_ContinueWithoutBagsButton = "//button[contains(text(),'Continue without adding bags') or contains(text(),'Continuar sin añadir equipaje')]";
	@FindBy(xpath=xpath_ContinueWithoutBagsButton)
	private List<WebElement> btn_ContinueWithoutBags;

	public final String xpath_AreYouSurePopUpCloseButton = "//app-confirm-no-bag-modal//button[@class='close']";
	@FindBy(xpath=xpath_AreYouSurePopUpCloseButton)
	private WebElement btn_AreYouSurePopUpClose;

	public final String xpath_AreYouSurePopUpVerbageText = "//div[@class='modal-body']";
	@FindBy(xpath = xpath_AreYouSurePopUpVerbageText)
	private WebElement txt_AreYouSurePopUpVerbage;

	//Salim:updated Xpath
	public final String xpath_AreYouSurePopUpINeedBagsButton = "//button[(contains(text(),'i need bags') or contains(text(),'necesito equipaje'))  and contains(@class, 'primary')]";
	@FindBy(xpath=xpath_AreYouSurePopUpINeedBagsButton)
	private WebElement btn_AreYouSurePopUpINeedBags;

	//Salim:updated Xpath
	public final String xpath_AreYouSurePopUpIDontNeedBagsButton = "//button[(contains(text(),'need bags') or contains(text(),'no necesito equipaje')) and  contains(@class, 'secondary')]";
	@FindBy(xpath=xpath_AreYouSurePopUpIDontNeedBagsButton)
	private WebElement  btn_AreYouSurePopUpIDontNeedBags;

	//*******************************************************************
	//***************Continue Without Bags(CheckIn)**********************
	//*******************************************************************
	public final String xpath_CheckInContinueWithBagsButton = "//button[text()=' Continue ' or text()=' Continuar ']";
	@FindBy(xpath=xpath_CheckInContinueWithBagsButton)
	private List<WebElement> btn_CheckInContinueWithBags;

	public final String xpath_ContinueWithOutChangesButton = "//button[contains(text(),'Continue without changes ') or contains(text(),'Continue without additional bags')]";
	@FindBy(xpath=xpath_ContinueWithOutChangesButton)
	private List<WebElement> btn_ContinueWithOutChanges;

	//*******************************************************************
	//**********************Over weight Bags*****************************
	//*******************************************************************

	//*******************************************************************
	//***************Departing Flight************************************
	//*******************************************************************
	//Salim:Pending for future
	//41-50
	public final String xpath_DepartingOverWeightBag41to50MinusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'41 - 50lbs.(18-23 kg)')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_DepartingOverWeightBag41to50MinusButton)
	private List<WebElement> btn_DepartingOverWeightBag41to50Minus;

	public final String xpath_DepartingOverWeightBag41to50CounterTextBox = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'41 - 50lbs.(18-23 kg)')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_DepartingOverWeightBag41to50CounterTextBox)
	private List<WebElement> txtbx_DepartingOverWeightBag41to50Counter;

	public final String xpath_DepartingOverWeightBag41to50PlusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'41 - 50lbs.(18-23 kg)')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_DepartingOverWeightBag41to50PlusButton)
	private List<WebElement> btn_DepartingOverWeightBag41to50Plus;

	public final String xpath_DepartingOverWeightBag41to50TotalText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'41 - 50lbs.(18-23 kg)')]/../following-sibling::div[3]";
	@FindBy(xpath= xpath_DepartingOverWeightBag41to50TotalText)
	private List<WebElement> txt_DepartingOverWeightBag41to50Total;

	public final String xpath_DepartingOverWeightBag41to50NextBagPriceText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'41 - 50lbs.(18-23 kg)')]/../following-sibling::div[4]";
	@FindBy(xpath= xpath_DepartingOverWeightBag41to50NextBagPriceText)
	private List<WebElement> txt_DepartingOverWeightBag41to50NextBagPrice;

	//51-70
	public final String xpath_DepartingOverWeightBag51to70MinusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'51 - 70lbs.(23-32 kg)')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_DepartingOverWeightBag51to70MinusButton)
	private List<WebElement> btn_DepartingOverWeightBag51to70Minus;

	public final String xpath_DepartingOverWeightBag51to70CounterTextBox = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'51 - 70lbs.(23-32 kg)')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_DepartingOverWeightBag51to70CounterTextBox)
	private List<WebElement> txtbx_DepartingOverWeightBag51to70Counter;

	public final String xpath_DepartingOverWeightBag51to70Plus = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'51 - 70lbs.(23-32 kg)')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_DepartingOverWeightBag51to70Plus)
	private List<WebElement> btn_DepartingOverWeightBag51to70Plus;

	public final String xpath_DepartingOverWeightBag51to70TotalText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'51 - 70lbs.(23-32 kg)')]/../following-sibling::div[3]";
	@FindBy(xpath= xpath_DepartingOverWeightBag51to70TotalText)
	private List<WebElement> txt_DepartingOverWeightBag51to70Total;

	public final String xpath_DepartingOverWeightBag51to70NextBagPriceText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'51 - 70lbs.(23-32 kg)')]/../following-sibling::div[4]";
	@FindBy(xpath= xpath_DepartingOverWeightBag51to70NextBagPriceText)
	private List<WebElement> txt_DepartingOverWeightBag51to70NextBagPrice;

	//71-100
	public final String xpath_DepartingOverWeightBag71to100MinusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'71 - 100lbs.(32-45 kg)')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_DepartingOverWeightBag71to100MinusButton)
	private List<WebElement> btn_DepartingOverWeightBag71to100Minus;

	public final String xpath_DepartingOverWeightBag71to100CounterTextBox = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'71 - 100lbs.(32-45 kg)')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_DepartingOverWeightBag71to100CounterTextBox)
	private List<WebElement> txtbx_DepartingOverWeightBag71to100Counter;

	public final String xpath_DepartingOverWeightBag71to100PlusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'71 - 100lbs.(32-45 kg)')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_DepartingOverWeightBag71to100PlusButton)
	private List<WebElement> btn_DepartingOverWeightBag71to100Plus;

	public final String xpath_DepartingOverWeightBag71to100TotalText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'71 - 100lbs.(32-45 kg)')]/../following-sibling::div[3]";
	@FindBy(xpath= xpath_DepartingOverWeightBag71to100TotalText)
	private List<WebElement> txt_DepartingOverWeightBag71to100Total;

	public final String xpath_DepartingOverWeightBag71to100NextBagPriceText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'71 - 100lbs.(32-45 kg)')]/../following-sibling::div[4]";
	@FindBy(xpath= xpath_DepartingOverWeightBag71to100NextBagPriceText)
	private List<WebElement> txt_DepartingOverWeightBag71to100NextBagPrice;

	//63-80
	//63-80
	public final String xpath_DepartingOverWeightBag63to80MinusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'(158-203 cm)')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_DepartingOverWeightBag63to80MinusButton)
	private List<WebElement> btn_DepartingOverWeightBag63to80Minus;

	public final String xpath_DepartingOverWeightBag63to80CounterTextBox = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'(158-203 cm)')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_DepartingOverWeightBag63to80CounterTextBox)
	private List<WebElement> txtbx_DepartingOverWeightBag63to80Counter;

	public final String xpath_DepartingOverWeightBag63to80PlusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'(158-203 cm)')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_DepartingOverWeightBag63to80PlusButton)
	private List<WebElement> btn_DepartingOverWeightBag63to80Plus;

	public final String xpath_DepartingOverWeightBag63to80TotalText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'(158-203 cm)')]/../following-sibling::div[3]";
	@FindBy(xpath= xpath_DepartingOverWeightBag63to80TotalText)
	private List<WebElement> txt_DepartingOverWeightBag63to80Total;

	public final String xpath_DepartingOverWeightBag63to80NextBagPriceText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'(158-203 cm)')]/../following-sibling::div[4]";
	@FindBy(xpath= xpath_DepartingOverWeightBag63to80NextBagPriceText)
	private List<WebElement> txt_DepartingOverWeightBag63to80NextBagPrice;

	//Special Item
	public final String xpath_DepartingOverWeightBagSpecialItemMinusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'(203 cm) ')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_DepartingOverWeightBagSpecialItemMinusButton)
	private List<WebElement> btn_DepartingOverWeightBagSpecialItemMinus;

	public final String xpath_DepartingOverWeightBagSpecialItemCounterTextBox = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'(203 cm) ')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_DepartingOverWeightBagSpecialItemCounterTextBox)
	private List<WebElement> txtbx_DepartingOverWeightBagSpecialItemCounter;

	public final String xpath_DepartingOverWeightBagSpecialItemPlusButton = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'(203 cm) ')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_DepartingOverWeightBagSpecialItemPlusButton)
	private List<WebElement> btn_DepartingOverWeightBagSpecialItemPlus;

	public final String xpath_DepartingOverWeightBagSpecialItemTotalText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'(203 cm) ')]/../following-sibling::div[3]";
	@FindBy(xpath= xpath_DepartingOverWeightBagSpecialItemTotalText)
	private List<WebElement> txt_DepartingOverWeightBagSpecialItemTotal;

	public final String xpath_DepartingOverWeightBagSpecialItemNextBagPriceText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'(203 cm) ')]/../following-sibling::div[4]";
	@FindBy(xpath= xpath_DepartingOverWeightBagSpecialItemNextBagPriceText)
	private List<WebElement> txt_DepartingOverWeightBagSpecialItemNextBagPrice;

	public final String xpath_SpecialItemInfoIconLink = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Special') or contains(text(),'Tabla de Surf -')]/i";
	@FindBy(xpath= xpath_SpecialItemInfoIconLink)
	private List<WebElement> lnk_SpecialItemInfoIcon;

	public final String xpath_SpecialItemInfoIconText = DEPARTING_FLIGHT_CONTAINER + "//p[contains(text(),'Special') or contains(text(),'Tabla de Surf -')]//div[contains(text(),'No bags over 80 linear inches')]";
	@FindBy(xpath= xpath_SpecialItemInfoIconText)
	private WebElement txt_SpecialItemInfoIcon;

	//************************************************
	//***************Return Flight********************
	//************************************************
	//Salim:pending for Future
	//41-50
	public final String xpath_ReturningOverWeightBag41to50MinusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'41 - 50lbs.(18-23 kg)')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_ReturningOverWeightBag41to50MinusButton)
	private List<WebElement> btn_ReturningOverWeightBag41to50Minus;

	public final String xpath_ReturningOverWeightBag41to50CounterTextBox = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'41 - 50lbs.(18-23 kg)')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_ReturningOverWeightBag41to50CounterTextBox)
	private List<WebElement> txtbx_ReturningOverWeightBag41to50Counter;

	public final String xpath_ReturningOverWeightBag41to50PlusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'41 - 50lbs.(18-23 kg)')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_ReturningOverWeightBag41to50PlusButton)
	private List<WebElement> btn_ReturningOverWeightBag41to50Plus;

	public final String xpath_ReturningOverWeightBag41to50TotalText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'41 - 50lbs.(18-23 kg)')]/../following-sibling::div[3]";
	@FindBy(xpath= xpath_ReturningOverWeightBag41to50TotalText)
	private List<WebElement> txt_ReturningOverWeightBag41to50Total;

	public final String xpath_ReturningOverWeightBag41to50NextBagPriceText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'41 - 50lbs.(18-23 kg)')]/../following-sibling::div[4]";
	@FindBy(xpath= xpath_ReturningOverWeightBag41to50NextBagPriceText)
	private List<WebElement> txt_ReturningOverWeightBag41to50NextBagPrice;

	//51-60
	public final String xpath_ReturningOverWeightBag51to70Minus = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'51 - 70lbs.(23-32 kg)')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_ReturningOverWeightBag51to70Minus)
	private List<WebElement> btn_ReturningOverWeightBag51to70Minus;

	public final String xpath_ReturningOverWeightBag51to70CounterTextBox = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'51 - 70lbs.(23-32 kg)')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_ReturningOverWeightBag51to70CounterTextBox)
	private List<WebElement> txtbx_ReturningOverWeightBag51to70Counter;

	public final String xpath_ReturningOverWeightBag51to70PlusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'51 - 70lbs.(23-32 kg)')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_ReturningOverWeightBag51to70PlusButton)
	private List<WebElement> btn_ReturningOverWeightBag51to70Plus;

	public final String xpath_ReturningOverWeightBag51to70TotalText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'51 - 70lbs.(23-32 kg)')]/../following-sibling::div[3]";
	@FindBy(xpath= xpath_ReturningOverWeightBag51to70TotalText)
	private List<WebElement> txt_ReturningOverWeightBag51to70Total;

	public final String xpath_ReturningOverWeightBag51to70NextBagPriceText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'51 - 70lbs.(23-32 kg)')]/../following-sibling::div[4]";
	@FindBy(xpath= xpath_ReturningOverWeightBag51to70NextBagPriceText)
	private List<WebElement> txt_ReturningOverWeightBag51to70NextBagPrice;

	//71-100
	public final String xpath_ReturningOverWeightBag71to100MinusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'71 - 100lbs.(32-45 kg)')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_ReturningOverWeightBag71to100MinusButton)
	private List<WebElement> btn_ReturningOverWeightBag71to100Minus;

	public final String xpath_ReturningOverWeightBag71to100CounterTextBox = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'71 - 100lbs.(32-45 kg)')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_ReturningOverWeightBag71to100CounterTextBox)
	private List<WebElement> txtbx_ReturningOverWeightBag71to100Counter;

	public final String xpath_ReturningOverWeightBag71to100PlusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'71 - 100lbs.(32-45 kg)')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_ReturningOverWeightBag71to100PlusButton)
	private List<WebElement> btn_ReturningOverWeightBag71to100Plus;

	public final String xpath_ReturningOverWeightBag71to100TotalText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'71 - 100lbs.(32-45 kg)')]/../following-sibling::div[3]";
	@FindBy(xpath= xpath_ReturningOverWeightBag71to100TotalText)
	private List<WebElement> txt_ReturningOverWeightBag71to100Total;

	public final String xpath_ReturningOverWeightBag71to100NextBagPriceText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'71 - 100lbs.(32-45 kg)')]/../following-sibling::div[4]";
	@FindBy(xpath= xpath_ReturningOverWeightBag71to100NextBagPriceText)
	private List<WebElement> txt_ReturningOverWeightBag71to100NextBagPrice;

	//63-80
	//63-80
	public final String xpath_ReturningOverWeightBag63to80MinusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'158-203 cm)')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_ReturningOverWeightBag63to80MinusButton)
	private List<WebElement> btn_ReturningOverWeightBag63to80Minus;

	public final String xpath_ReturningOverWeightBag63to80CounterTextBox = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'(158-203 cm)')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_ReturningOverWeightBag63to80CounterTextBox)
	private List<WebElement> txtbx_ReturningOverWeightBag63to80Counter;

	public final String xpath_ReturningOverWeightBag63to80PlusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'(158-203 cm)')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_ReturningOverWeightBag63to80PlusButton)
	private List<WebElement> btn_ReturningOverWeightBag63to80Plus;

	public final String xpath_ReturningOverWeightBag63to80TotalText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'(158-203 cm)')]/../following-sibling::div[3]";
	@FindBy(xpath= xpath_ReturningOverWeightBag63to80TotalText)
	private List<WebElement> txt_ReturningOverWeightBag63to80Total;

	public final String xpath_ReturningOverWeightBag63to80NextBagPriceText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'(158-203 cm)')]/../following-sibling::div[4]";
	@FindBy(xpath= xpath_ReturningOverWeightBag63to80NextBagPriceText)
	private List<WebElement> txt_ReturningOverWeightBag63to80NextBagPrice;

	//Special Item
	public final String xpath_ReturningOverWeightBagSpecialItemMinusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'(203 cm) ')]/../..//i[contains(@class,'icon-minus')]";
	@FindBy(xpath= xpath_ReturningOverWeightBagSpecialItemMinusButton)
	private List<WebElement> btn_ReturningOverWeightBagSpecialItemMinus;

	public final String xpath_ReturningOverWeightBagSpecialItemCounterTextBox = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'(203 cm) ')]/../..//div[@class='counter-number']//input";
	@FindBy(xpath= xpath_ReturningOverWeightBagSpecialItemCounterTextBox)
	private List<WebElement> txtbx_ReturningOverWeightBagSpecialItemCounter;

	public final String xpath_ReturningOverWeightBagSpecialItemPlusButton = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'(203 cm) ')]/../..//i[contains(@class,'icon-add')]";
	@FindBy(xpath= xpath_ReturningOverWeightBagSpecialItemPlusButton)
	private List<WebElement> btn_ReturningOverWeightBagSpecialItemPlus;

	public final String xpath_ReturningOverWeightBagSpecialTotalText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'(203 cm) ')]/../following-sibling::div[3]";
	@FindBy(xpath= xpath_ReturningOverWeightBagSpecialTotalText)
	private List<WebElement> txt_ReturningOverWeightBagSpecialTotal;

	public final String xpath_ReturningOverWeightBagSpecialNextBagPriceText = RETURN_FLIGHT_CONTAINER + "//p[contains(text(),'(203 cm) ')]/../following-sibling::div[4]";
	@FindBy(xpath= xpath_ReturningOverWeightBagSpecialNextBagPriceText)
	private List<WebElement> txt_ReturningOverWeightBagSpecialNextBagPrice;

	//************************************************
	//**************More Bags Info********************
	//************************************************
	public final String xpath_EmbargoRestrictionsLink = "//a[contains(text(), 'embargo restrictions')]";
	@FindBy(xpath= xpath_EmbargoRestrictionsLink)
	private WebElement lnk_EmbargoRestrictions;

	public final String xpath_MoreInformationLink = "//a[contains(text(), 'More information')]";
	@FindBy(xpath= xpath_MoreInformationLink)
	private WebElement lnk_MoreInformation;

	//************************************************
	//**************Bags Size details********************
	//************************************************
	public final String xpath_MaximumSizeText = "//p[contains(text(),'Maximum size includes handles and wheels')]";
	@FindBy(xpath=xpath_MaximumSizeText)
	private WebElement txt_MaximumSize;

	public final String xpath_FollowingItemText = "//p[contains(text(),'**The following items are not counted as carry-on items')]";
	@FindBy(xpath=xpath_FollowingItemText)
	private WebElement txt_FollowingItem;

	public final String xpath_CertainEmbargoText = "//p[contains(text(),'Certain')]";
	@FindBy(xpath=xpath_CertainEmbargoText)
	private WebElement txt_CertainEmbargo;

	//*******************************************************************************************************************************************
	//******************************************************Start getter Methods of Bags Page****************************************************
	//*******************************************************************************************************************************************

	//*******************************************************************
	//************************Top Left Header****************************
	//*******************************************************************
	public WebElement getHeaderText() {
		return txt_Header;
	}

	public WebElement getMarketingVerbiageText() {
		return txt_MarketingVerbiage;
	}

	//*******************************************************************
	//******************Bag Description Container************************
	//*******************************************************************
	public WebElement getPersonalItemText() {
		return txt_PersonalItem;
	}

	public WebElement getPersonalItemDimensionsText() {
		return txt_PersonalItemDimensions;
	}

	public WebElement getPersonalItemPriceDisplayText() {
		return txt_PersonalItemPriceDisplay;
	}

	public WebElement getIncludedYellowCircleImage() {
		return img_IncludedYellowCircle;
	}

	public WebElement getCarryOnBagText() {
		return txt_CarryOnBag;
	}

	public WebElement getCarryOnBagDimensionsText() {
		return txt_CarryOnBagDimensions;
	}

	public WebElement getIncludedYellowCarryonCircleImage() {
		return img_IncludedYellowCarryonCircle;
	}

	public WebElement getCarryOnBagPriceDisplayText() {
		for(int elementCount = 0;elementCount<txt_CarryOnBagPriceDisplay.size();elementCount++){
			if(txt_CarryOnBagPriceDisplay.get(elementCount).isDisplayed()){
				return txt_CarryOnBagPriceDisplay.get(elementCount);
			}
		}
		return null;
	}

	public WebElement getCheckedBagText() {
		return txt_CheckedBag;
	}

	public WebElement getCheckedBagDimensionsText() {
		return txt_CheckedBagDimensions;
	}

	public WebElement getCheckedBagPriceDisplayText() {

		for(int elementCount = 0;elementCount<txt_CheckedBagPriceDisplay.size();elementCount++){
			if(txt_CheckedBagPriceDisplay.get(elementCount).isDisplayed()){
				return txt_CheckedBagPriceDisplay.get(elementCount);
			}
		}
		return null;
	}

	public WebElement getIncludedYellowCheckedCircleImage() {
		return img_IncludedYellowCheckedCircle;
	}

	//***************************************************************
	//********Departing Flights Bags Container***********************
	//***************************************************************
	public WebElement getDepartureCityText() {
		return txt_DepartureCity;
	}

	public WebElement getDepartureDateText() {
		return txt_DepartureDate;
	}

	public List<WebElement> getDepartingPassengerFlightContainerListText() {
		return txt_DepartingPassengerFlightContainer;
	}

	public List<WebElement> getDepartingPassengerNameContainerText() {
		return txt_DepartingPassengerNameContainer;
	}


	//***************************************************************
	//********Departing carry-on Bags Container**********************
	//***************************************************************

	public List<WebElement> getDepartingCarryOnMinusButton() {
		return btn_DepartingCarryOnMinus;
	}

	public List<WebElement> getDepartingCarryOnBagCounterTextBox() {
		return txtbx_DepartingCarryOnBagCounter;
	}

	public List<WebElement> getDepartingCarryOnPlusButton() {
		return btn_DepartingCarryOnPlus;
	}

	public List<WebElement> getDepartingCarryOnPriceText() {
		return txt_DepartingCarryOnPrice;
	}

	public List<WebElement> getDepartingNextCarryOnPriceText() {
		return txt_DepartingNextCarryOnPrice;
	}

	//***************************************************************
	//********Departing Checked  Bags Container**********************
	//***************************************************************

	public List<WebElement> getDepartingCheckedBagMinusButton() {
		return btn_DepartingCheckedBagMinus;
	}

	public List<WebElement> getDepartingCheckedBagCounterTextBox() {
		return txtbx_DepartingCheckedBagCounter;
	}

	public List<WebElement> getDepartingCheckedBagPlusButton() {
		return btn_DepartingCheckedBagPlus;
	}

	public List<WebElement> getDepartingCheckedBagPriceText() {
		return txt_DepartingCheckedBagPrice;
	}

	public List<WebElement> getDepartingNextCheckedBagPriceText() {
		return txt_DepartingNextCheckedBagPrice;
	}

	//***************************************************************
	//********Departure Sporting Equipment Container*****************
	//***************************************************************

	public List<WebElement> getDepartingSportingEquipmentLinkButton() {
		return lnkbtn_DepartingSportingEquipment;
	}


	public List<WebElement> getDepartingSportingEquipmentBicycleDescriptionText() {
		return txt_DepartingSportingEquipmentBicycleDescription;
	}

	public List<WebElement> getDepartingBicycleInfoIconLink() {
		return lnk_DepartingBicycleInfoIcon;
	}

	public List<WebElement> getDepartingBicycleMinusButton() {
		return btn_DepartingBicycleMinus;
	}

	public List<WebElement> getDepartingBicycleCounterTextBox() {
		return txtbx_DepartingBicycleCounter;
	}

	public List<WebElement> getDepartingBicyclePlusButton() {
		return btn_DepartingBicyclePlus;
	}

	public List<WebElement> getDepartingNextBicyclePriceText() {
		return txt_DepartingNextBicyclePrice;
	}

	public List<WebElement> getDepartingBicyclePriceText() {
		return txt_DepartingBicyclePrice;
	}

	public List<WebElement> getDepartingSportingEquipmentSurfBoardDescriptionText() {
		return txt_DepartingSportingEquipmentSurfBoardDescription;
	}

	public List<WebElement> getDepartingSurfBoardInfoIconLink() {
		return lnk_DepartingSurfBoardInfoIcon;
	}

	public List<WebElement> getDepartingSurfBoardMinusButton() {
		return btn_DepartingSurfBoardMinus;
	}

	public List<WebElement> getDepartingSurfBoardCounterTextBox() {
		return txtbx_DepartingSurfBoardCounter;
	}

	public List<WebElement> getDepartingSurfBoardPlusButton() {
		return btn_DepartingSurfBoardPlus;
	}

	public List<WebElement> getDepartingNextSurfBoardPriceText() {
		return txt_DepartingNextSurfBoardPrice;
	}

	public List<WebElement> getDepartingSurfBoardPriceText() {
		return txt_DepartingSurfBoardPrice;
	}

	public WebElement getSportingEquipmentToolTipCloseImage() {
		return img_SportingEquipmentToolTipClose;
	}

	public WebElement getSportingEquipmentToolTipMoreInfoLink() {
		return lnk_SportingEquipmentToolTipMoreInfo;
	}

	//*******************************************************************
	//********Returning Flights Bags Container***************************
	//*******************************************************************
	public WebElement getReturnCityText() {
		return txt_ReturnCity;
	}

	public WebElement getReturnDateText() {
		return txt_ReturnDate;
	}

	public List<WebElement> getKeepSameBagsLabel() {
		return lbl_KeepSameBags;
	}

	public List<WebElement> getReturningPassengerFlightContainerTextList() {
		return txt_ReturningPassengerFlightContainer;
	}

	//***************************************************************
	//********Returning Carry-on Bags Container**********************
	//***************************************************************

	public List<WebElement> getReturningCarryOnMinusButton() {
		return btn_ReturningCarryOnMinus;
	}

	public List<WebElement> getReturningCarryOnCounterTextBox() {
		return txtbx_ReturningCarryOnCounter;
	}

	public List<WebElement> getReturningCarryOnPlusButton() {
		return btn_ReturningCarryOnPlus;
	}

	public List<WebElement> getReturningCarryOnPriceText() {
		return txt_ReturningCarryOnPrice;
	}

	public List<WebElement> getReturningNextCarryOnPriceText() {
		return txt_ReturningNextCarryOnPrice;
	}

	//***************************************************************
	//********Returning Checked Bags Container***********************
	//***************************************************************
	public List<WebElement> getReturningCheckedBagMinusButton() {
		return btn_ReturningCheckedBagMinus;
	}

	public List<WebElement> getReturningCheckedBagCounterTextBox() {
		return txtbx_ReturningCheckedBagCounter;
	}

	public List<WebElement> getReturningCheckedBagPlusButton() {
		return btn_ReturningCheckedBagPlus;
	}

	public List<WebElement> getReturningCheckedBagPriceText() {
		return txt_ReturningCheckedBagPrice;
	}

	public List<WebElement> getReturningNextCheckedBagPriceText() {
		return txt_ReturningNextCheckedBagPrice;
	}

	//***************************************************************
	//***********Returning Sporting Equipment Container**************
	//***************************************************************

	public List<WebElement> getReturningSportingEquipmentLinkButton() {
		return lnkbtn_ReturningSportingEquipment;
	}

	public List<WebElement> getReturningBicycleInfoIconButton() {
		return btn_ReturningBicycleInfoIcon;
	}

	public List<WebElement> getReturningBicyclePlusButton() {
		return btn_ReturningBicyclePlus;
	}

	public List<WebElement> getReturningNextBicyclePriceText() {
		return txt_ReturningNextBicyclePrice;
	}

	public List<WebElement> getReturningBicycleCounterTextBox() {
		return txtbx_ReturningBicycleCounter;
	}

	public List<WebElement> getReturningBicycleMinusButton() {
		return btn_ReturningBicycleMinus;
	}

	public List<WebElement> getReturningBicyclePriceText() {
		return txt_ReturningBicyclePrice;
	}

	public List<WebElement> getReturningSurfBoardMinusButton() {
		return btn_ReturningSurfBoardMinus;
	}

	public List<WebElement> getReturningSurfBoardCounterTextBox() {
		return txtbx_ReturningSurfBoardCounter;
	}

	public List<WebElement> getReturningSurfBoardPlusButton() {
		return btn_ReturningSurfBoardPlus;
	}

	public List<WebElement> getReturningNextSurfBoardPriceText() {
		return txt_ReturningNextSurfBoardPrice;
	}

	public List<WebElement> getReturningSurfBoardPriceText() {
		return txt_ReturningSurfBoardPrice;
	}

	//*******************************************************************
	//*******************Bags Total Bags Page****************************
	//*******************************************************************
	public WebElement getTotalDueContainerPanel(){
		return pnl_TotalDueContainer;
	}

	public WebElement getBagsTotalContainerText() {
		return txt_BagsTotalContainer;
	}

	public WebElement getBagsTotalContainerAmountTotalText() {
		return txt_BagsTotalContainerAmountTotal;
	}

	public WebElement getOutboundJourneyBreakdownCityPairText(){return txt_OutboundJourneyBreakdownCityPair;}

	public WebElement getBagsTotalContainerLink() {
		return lnk_BagsTotalContainer;
	}

	public List<WebElement> getBagsTotalContainerOptionsText(){
		return txt_BagsTotalContainerOptions;
	}

	//*******************************************************************
	//*****************Bags Total Breakdown Bags Page********************
	//*******************************************************************
	public WebElement getOutboundJourneyBreakdownCarryOnBagTotalPriceText() { return txt_OutboundJourneyBreakdownCarryOnBagTotalPrice;	}

	public WebElement getOutboundJourneyBreakdownCheckedBagTotalPriceText() {return txt_OutboundJourneyBreakdownCheckedBagTotalPrice;	}

	public WebElement getOutboundJourneyBreakdownBikeTotalPriceText() {
		return txt_OutboundJourneyBreakdownBikeTotalPrice;
	}

	public WebElement getOutboundJourneyBreakdownSurfTotalPriceText() {
		return txt_OutboundJourneyBreakdownSurfTotalPrice;
	}

	public WebElement getOutboundJourneyBreakdownVatTaxPriceText() {
		return txt_OutboundJourneyBreakdownVatTaxPrice;
	}



	public WebElement getReturnJourneyBreakdownCarryOnBagTotalPriceText() {
		return txt_ReturnJourneyBreakdownCarryOnBagTotalPrice;
	}

	public WebElement getReturnJourneyBreakdownCheckedBagTotalPriceText() {
		return txt_ReturnJourneyBreakdownCheckedBagTotalPrice;
	}

	public WebElement getReturnJourneyBreakdownBikeTotalPriceText() {
		return txt_ReturnJourneyBreakdownBikeTotalPrice;
	}

	public WebElement getReturnJourneyBreakdownSurfTotalPriceText() {
		return txt_ReturnJourneyBreakdownSurfTotalPrice;
	}

	public WebElement getReturnJourneyBreakdownVatTaxPriceText() {
		return txt_ReturnJourneyBreakdownVatTaxPrice;
	}


	//*******************************************************************
	//***************Continue With FareClub Bags Page********************
	//*******************************************************************
	public WebElement getContinueWith9FCBagsContainerHeaderText() {
		return txt_ContinueWith9FCBagsContainerHeader;
	}

	public WebElement getContinueWith9FCBagsContainerPriceText() {
		return txt_ContinueWith9FCBagsContainerPrice;
	}

	public WebElement getContinueWith9FCBagsContainerSavingsText() {
		return txt_ContinueWith9FCBagsContainerSavings;
	}

	public WebElement getContinueWith9FCBagsContainerContinueButton() {
		return btn_ContinueWith9FCBagsContainerContinue;
	}

	//*******************************************************************
	//***************Continue With Standard Bags Page********************
	//*******************************************************************
	public WebElement getContinueWithStandardBagsContainerHeaderText() {
		return txt_ContinueWithStandardBagsContainerHeader;
	}

	public WebElement getContinueWithStandardBagsContainerPriceText() {
		return txt_ContinueWithStandardBagsContainerPrice;
	}

	public WebElement getContinueWithStandardBagsContainerContinueButton() {
		return btn_ContinueWithStandardBagsContainerContinue;
	}

	//*******************************************************************
	//**********Fare Club SignUp Without Bags Page***********************
	//*******************************************************************
	public WebElement get9FCPopUpHeaderText() {
		return txt_9FCPopUpHeader;
	}

	public WebElement get9FCPopUpCloseButton() {
		return btn_9FCPopUpClose;
	}

	public WebElement get9FCPopUpUserNameTextBox() {
		return txtbx_9FCPopUpUserName;
	}

	public WebElement get9FCPopUpLogInPasswordTextBox() {
		return txtbx_9FCPopUpLogInPassword;
	}

	public WebElement get9FCPopUpResetPasswordTextBox() {
		return txtbx_9FCPopUpResetPassword;
	}

	public WebElement get9FCUpsellLogInButton() {
		return btn_9FCUpsellLogIn;
	}

	public WebElement getContinueWithStandardBagsOn9FCPopUPButton() {
		return btn_ContinueWithStandardBagsOn9FCPopUp;
	}

	public WebElement getWayToGoSaverText(){
		return txt_WayToGoSaver;
	}

	//*******************************************************************
	//***************Purchase Seat Popup(CheckIn)************************
	//*******************************************************************
	public WebElement getCheckInPurchaseSeatPopupHeaderText() {
		return txt_CheckInPurchaseSeatPopupHeader;
	}

	public WebElement getCheckInPurchaseSeatPopupCloseButton() {
		return btn_CheckInPurchaseSeatPopupClose;
	}

	public WebElement getCheckInPurchaseSeatPopupSubHeaderText() {
		return txt_CheckInPurchaseSeatPopupSubHeader;
	}

	public WebElement getCheckInPurchaseSeatPopupPurchaseMySeatsButton() {
		return btn_CheckInPurchaseSeatPopupPurchaseMySeats;
	}

	public WebElement getCheckInPurchaseSeatPopupDontPurchaseMySeatsButton() {
		return btn_CheckInPurchaseSeatPopupDontPurchaseMySeats;
	}

	//*******************************************************************
	//***************Continue Without Bags Page**************************
	//*******************************************************************
	public WebElement getAreYouSureHeader() {return txt_ContinueWithouAreYouSureHeader; }

	public List<WebElement> getContinueWithoutBagsButton() {
		return btn_ContinueWithoutBags;
	}

	public WebElement getAreYouSurePopUpCloseButton() {
		return btn_AreYouSurePopUpClose;
	}

	public WebElement getAreYouSurePopUpVerbageText(){
		return txt_AreYouSurePopUpVerbage;
	}

	public WebElement getAreYouSurePopUpINeedBagsButton() {
		return btn_AreYouSurePopUpINeedBags;
	}

	public WebElement getAreYouSurePopUpIDontNeedBagButton() {
		return btn_AreYouSurePopUpIDontNeedBags;
	}

	//*******************************************************************
	//***************Continue Without Bags(CheckIn)**********************
	//*******************************************************************
	public WebElement getContinueWithBagsButton() {

		if(btn_CheckInContinueWithBags.size()==1){
			return btn_CheckInContinueWithBags.get(0);
		}else{
			ValidationUtil.validateTestStep("'getContinueWithBagsButton' will select the 9DFC continue button and convert the FS account to 9FC",false);
		}
		return null;
	}

	public List<WebElement> getContinueWithOutChangesButton() {
		return btn_ContinueWithOutChanges;
	}

	//*******************************************************************
	//**********************Over weight Bags*****************************
	//*******************************************************************

	//*******************************************************************
	//***************Departing Flight************************************ 
	//*******************************************************************
	public List<WebElement> getDepartingOverWeightBag41to50MinusButton() {
		return btn_DepartingOverWeightBag41to50Minus;
	}

	public List<WebElement> getDepartingOverWeightBag41to50CounterTextBox() {
		return txtbx_DepartingOverWeightBag41to50Counter;
	}

	public List<WebElement> getDepartingOverWeightBag41to50PlusButton() {
		return btn_DepartingOverWeightBag41to50Plus;
	}

	public List<WebElement> getDepartingOverWeightBag41to50TotalText() {
		return txt_DepartingOverWeightBag41to50Total;
	}

	public List<WebElement> getDepartingOverWeightBag41to50NextBagPriceText() {
		return txt_DepartingOverWeightBag41to50NextBagPrice;
	}

	public List<WebElement> getDepartingOverWeightBag51to70MinusButton() {
		return btn_DepartingOverWeightBag51to70Minus;
	}

	public List<WebElement> getDepartingOverWeightBag51to70CounterTextBox() {
		return txtbx_DepartingOverWeightBag51to70Counter;
	}

	public List<WebElement> getDepartingOverWeightBag51to70PlusButton() {
		return btn_DepartingOverWeightBag51to70Plus;
	}

	public List<WebElement> getDepartingOverWeightBag51to70TotalText() {
		return txt_DepartingOverWeightBag51to70Total;
	}

	public List<WebElement> getDepartingOverWeightBag51to70NextBagPriceText() {
		return txt_DepartingOverWeightBag51to70NextBagPrice;
	}

	public List<WebElement> getDepartingOverWeightBag71to100MinusButton() {
		return btn_DepartingOverWeightBag71to100Minus;
	}

	public List<WebElement> getDepartingOverWeightBag71to100CounterTextBox() {
		return txtbx_DepartingOverWeightBag71to100Counter;
	}

	public List<WebElement> getDepartingOverWeightBag71to100PlusButton() {
		return btn_DepartingOverWeightBag71to100Plus;
	}

	public List<WebElement> getDepartingOverWeightBag71to100TotalText() {
		return txt_DepartingOverWeightBag71to100Total;
	}

	public List<WebElement> getDepartingOverWeightBag71to100NextBagPriceText() {
		return txt_DepartingOverWeightBag71to100NextBagPrice;
	}

	public List<WebElement> getDepartingOverWeightBag63to80MinusButton() {
		return btn_DepartingOverWeightBag63to80Minus;
	}

	public List<WebElement> getDepartingOverWeightBag63to80CounterTextBox() {
		return txtbx_DepartingOverWeightBag63to80Counter;
	}

	public List<WebElement> getDepartingOverWeightBag63to80PlusButton() {
		return btn_DepartingOverWeightBag63to80Plus;
	}

	public List<WebElement> getDepartingOverWeightBag63to80TotalText() {
		return txt_DepartingOverWeightBag63to80Total;
	}

	public List<WebElement> getDepartingOverWeightBag63to80NextBagPriceText() {
		return txt_DepartingOverWeightBag63to80NextBagPrice;
	}

	public List<WebElement> getDepartingOverWeightBagSpecialItemMinusButton() {
		return btn_DepartingOverWeightBagSpecialItemMinus;
	}

	public List<WebElement> getDepartingOverWeightBagSpecialItemCounterTextBox() {
		return txtbx_DepartingOverWeightBagSpecialItemCounter;
	}

	public List<WebElement> getDepartingOverWeightBagSpecialItemPlusButton() {
		return btn_DepartingOverWeightBagSpecialItemPlus;
	}

	public List<WebElement> getDepartingOverWeightBagSpecialItemTotalText() {
		return txt_DepartingOverWeightBagSpecialItemTotal;
	}

	public List<WebElement> getDepartingOverWeightBagSpecialItemNextBagPriceText() {
		return txt_DepartingOverWeightBagSpecialItemNextBagPrice;
	}

	public List<WebElement> getSpecialItemInfoIconLink() {
		return lnk_SpecialItemInfoIcon;
	}

	public WebElement getSpecialItemInfoIconVerbiage() {
		return txt_SpecialItemInfoIcon;
	}

	//************************************************
	//***************Return Flight********************
	//************************************************

	public List<WebElement> getReturningOverWeightBag41to50MinusButton() {
		return btn_ReturningOverWeightBag41to50Minus;
	}

	public List<WebElement> getReturningOverWeightBag41to50CounterTextBox() {
		return txtbx_ReturningOverWeightBag41to50Counter;
	}

	public List<WebElement> getReturningOverWeightBag41to50PlusButton() {
		return btn_ReturningOverWeightBag41to50Plus;
	}

	public List<WebElement>  getReturningOverWeightBag41to50TotalText() {
		return txt_ReturningOverWeightBag41to50Total;
	}

	public List<WebElement>  getReturningOverWeightBag41to50NextBagPriceText() {
		return txt_ReturningOverWeightBag41to50NextBagPrice;
	}

	public List<WebElement> getReturningOverWeightBag51to70MinusButton() {
		return btn_ReturningOverWeightBag51to70Minus;
	}

	public List<WebElement> getReturningOverWeightBag51to70CounterTextBox() {
		return txtbx_ReturningOverWeightBag51to70Counter;
	}

	public List<WebElement> getReturningOverWeightBag51to70PlusButton() {
		return btn_ReturningOverWeightBag51to70Plus;
	}

	public List<WebElement>  getReturningOverWeightBag51to70TotalText() {
		return txt_ReturningOverWeightBag51to70Total;
	}

	public List<WebElement>  getReturningOverWeightBag51to70NextBagPriceText() {
		return txt_ReturningOverWeightBag51to70NextBagPrice;
	}

	public List<WebElement> getReturningOverWeightBag71to100MinusButton() {
		return btn_ReturningOverWeightBag71to100Minus;
	}

	public List<WebElement> getReturningOverWeightBag71to100CounterTextBox() {
		return txtbx_ReturningOverWeightBag71to100Counter;
	}

	public List<WebElement> getReturningOverWeightBag71to100PlusButton() {
		return btn_ReturningOverWeightBag71to100Plus;
	}

	public List<WebElement>  getReturningOverWeightBag71to100TotalText() {
		return txt_ReturningOverWeightBag71to100Total;
	}

	public List<WebElement>  getReturningOverWeightBag71to100NextBagPriceText() {
		return txt_ReturningOverWeightBag71to100NextBagPrice;
	}

	public List<WebElement> getReturningOverWeightBag63to80MinusButton() {
		return btn_ReturningOverWeightBag63to80Minus;
	}

	public List<WebElement> getReturningOverWeightBag63to80CounterTextBox() {
		return txtbx_ReturningOverWeightBag63to80Counter;
	}

	public List<WebElement> getReturningOverWeightBag63to80PlusButton() {
		return btn_ReturningOverWeightBag63to80Plus;
	}

	public List<WebElement>  getReturningOverWeightBag63to80TotalText() {
		return txt_ReturningOverWeightBag63to80Total;
	}

	public List<WebElement>  getReturningOverWeightBag63to80NextBagPriceText() {
		return txt_ReturningOverWeightBag63to80NextBagPrice;
	}

	public List<WebElement> getReturningOverWeightBagSpecialItemMinusButton() {
		return btn_ReturningOverWeightBagSpecialItemMinus;
	}

	public List<WebElement> getReturningOverWeightBagSpecialItemCounterTextBox() {
		return txtbx_ReturningOverWeightBagSpecialItemCounter;
	}

	public List<WebElement> getReturningOverWeightBagSpecialItemPlusButton() {
		return btn_ReturningOverWeightBagSpecialItemPlus;
	}

	public List<WebElement>  getReturningOverWeightBagSpecialTotalText() {
		return txt_ReturningOverWeightBagSpecialTotal;
	}

	public List<WebElement>  getReturningOverWeightBagSpecialNextBagPriceText() {
		return txt_ReturningOverWeightBagSpecialNextBagPrice;
	}

	//************************************************
	//**************More Bags Info********************
	//************************************************
	public WebElement getEmbargoRestrictionsLink() {return lnk_EmbargoRestrictions;}

	public WebElement getMoreInformationLink() {return lnk_MoreInformation;}

	//************************************************
	//**************Bags Size details********************
	//************************************************
	public WebElement getMaximumSizeText() {
		return txt_MaximumSize;
	}

	public WebElement getFollowingItemText() {
		return txt_FollowingItem;
	}

	public WebElement getCertainEmbargomText() {
		return txt_CertainEmbargo;
	}
}