package com.spirit.pageObjects;

import java.util.List;

import com.spirit.enums.MobileType;
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
public class CarPage {

	public CarPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//****************************************************************
	//*******************Header Verbiage Car Page*********************
	//****************************************************************
	public final String xpath_PricePerPersonText = "//p[contains(text(),'Prices are per person and include flight') or contains(text(),'Los precios son por persona e incluyen vuelo')]";
	@FindBy(xpath=xpath_PricePerPersonText)
	private WebElement txt_PricePerPerson;

	public final String xpath_FlightTaxFeesLink = "//a[contains(text(),'flight taxes and fees') or contains(text(),'los impuestos y cargos del vuelo')]";
	@FindBy(xpath=xpath_FlightTaxFeesLink)
	private WebElement lnk_FlightTaxFees;

	public final String xpath_VacationPackagesCarRentalsText = "//app-taxes-and-fees-modal//h2[contains(text(),'Packages/Car Rentals')]//following-sibling::p[2]";
	@FindBy(xpath=xpath_VacationPackagesCarRentalsText)
	private WebElement txt_VacationPackagesCarRentals;

	public final String xpath_IncludesCarTaxesLink = "//a[contains(text(),'includes car taxes') or contains(text(),'incluye los impuestos del auto')]";
	@FindBy(xpath=xpath_IncludesCarTaxesLink)
	private WebElement lnk_IncludesCarTaxes;

	public final String xpath_BaggageLink = "//a[contains(text(),'baggage') or contains(text(),'equipaje')]";
	@FindBy(xpath=xpath_BaggageLink)
	private WebElement lnk_Baggage;

	public final String xpath_AdvanceSeatAssignmentLink = "//a[contains(text(),'advance seat assignment') or contains(text(),'asignación de asientos anticipado')]";
	@FindBy(xpath=xpath_AdvanceSeatAssignmentLink)
	private WebElement lnk_AdvanceSeatAssignment;

	public final String xpath_AnyChangesLink = "//a[contains(text(),'any changes') or contains(text(),'cualquier cambio')]";
	@FindBy(xpath=xpath_AnyChangesLink)
	private WebElement lnk_AnyChanges;

	//*******************************************************************
	//*******************Cars Container Options Page*********************
	//*******************************************************************
	public final String xpath_CarCarouselTitleText = "//h3[contains(text(),'Savings on Cars') or contains(text(),'Autos con ahorros')]";
	@FindBy(xpath=xpath_CarCarouselTitleText)
	private WebElement txt_CarCarouselTitle;

	public final String xpath_CarCarouselLeftButton = "//app-car//i[contains(@class,'left')]";
	@FindBy(xpath=xpath_CarCarouselLeftButton)
	private WebElement btn_CarCarouselLeft;

	public final String xpath_CarCarouselRightButton = "//app-car//i[contains(@class,'right')]";
	@FindBy(xpath=xpath_CarCarouselRightButton)
	private WebElement btn_CarCarouselRight;

	public final String xpath_ViewAllCarsLinkButton = "//button[contains(text(),'Ver todos los autos') or contains(text(),'View All Cars')]";
	@FindBy(xpath=xpath_ViewAllCarsLinkButton)
	private WebElement lnkbtn_ViewAllCars;

	public final String xpath_CarsPanel = "//app-car//app-ancillary-item[contains(@class,'options-table')]";
	@FindBy(xpath = xpath_CarsPanel)
	private List<WebElement> pnl_Cars;

	public final String xpath_CarsCarouselAddCarButton = "//app-car//app-ancillary-item//button[contains(text(),'Add Car') or contains(text(),'Añadir auto')]";
	@FindBy(xpath = xpath_CarsCarouselAddCarButton)
	private List<WebElement> btn_CarsCarouselAddCar;

	//*******************************************************************
	//********Car Cards containers Options Page**************************
	//*******************************************************************
	//car Name
	public final String xpath_CarCardNameText = xpath_CarsPanel + "//div[@class='row option-card-info-container']/div[1]/p[1]";
	@FindBy(xpath=xpath_CarCardNameText)
	private List<WebElement> txt_CarCardName;

	public final String xpath_CarsCarouselCompanyName = xpath_CarsPanel + "//div[@class='row option-card-info-container']//p[2]//span";
	@FindBy(xpath = xpath_CarsCarouselCompanyName)
	private List<WebElement> txt_CarCardCompanyName;

	//car price
	public final String xpath_CarCardPriceButton = xpath_CarsPanel + "//div[@class='row option-card-info-container']/div[3]//p[contains(@class,'h4')]";
	@FindBy(xpath=xpath_CarCardPriceButton)
	private List<WebElement> lnkbtn_CarCardPrice;

	public final String xpath_CarCardTotalPriceText = xpath_CarsPanel + "//div[@class='row option-card-info-container']/div[3]/p[3]";
	@FindBy(xpath = xpath_CarCardTotalPriceText)
	private List<WebElement> txt_CarCardTotalPrice;

	//excludes taxes
	public final String xpath_CarCardTaxesLinkButton = xpath_CarsPanel + "//a[contains(text(),'taxes') or contains(text(),'impuestos')]";
	@FindBy(xpath=xpath_CarCardTaxesLinkButton)
	private List<WebElement> lnkbtn_CarCardTaxes;

	public final String xpath_CarCarouselSeats = xpath_CarsPanel + "//div[@class='row option-card-info-container']//img[@alt='Seat Icon']//following::span[1]";
	@FindBy(xpath = xpath_CarCarouselSeats)
	private List<WebElement> txt_CarCardSeats;

	public final String xpath_CarsCarouselTransmission = xpath_CarsPanel + "//div[@class='row option-card-info-container']//img[@alt='Transmission Type Icon']//following::span[1]";
	@FindBy(xpath = xpath_CarsCarouselTransmission)
	private List<WebElement> txt_CarCardTransmission;

	public final String xpath_CarsCarouselBaggage = xpath_CarsPanel + "//div[@class='row option-card-info-container']//img[@alt='Bag Icon']//following::span[1]";
	@FindBy(xpath = xpath_CarsCarouselBaggage)
	private List<WebElement> txt_CarCardBaggage;

	public final String xpath_CarsCarouselMileageLimit = xpath_CarsPanel + "//div[@class='row option-card-info-container']//img[@alt='Miles Icon']//following::span[1]";
	@FindBy(xpath = xpath_CarsCarouselMileageLimit)
	private List<WebElement> txt_CarCardMileageLimit;

	//	public final String xpath_CarCardUpliftPricingText = "//app-car//app-ancillary-item//app-uplift-pricing[@type='options-card']";
	public final String xpath_CarCardUpliftPricingText = xpath_CarsPanel + "//app-uplift-pricing//span[@class='uplift-underline']";
	@FindBy(xpath=xpath_CarCardUpliftPricingText)
	private List<WebElement> txt_CarCardUpliftPricing;

	//view car
	public final String xpath_CarCardViewLinkButton = xpath_CarsPanel +  "//*[contains(text(),'View') or contains(text(),'Add Car') or contains(text(),'Añadir auto') or contains(text(),'Ver')]";
	@FindBy(xpath=xpath_CarCardViewLinkButton)
	private List<WebElement> lnkbtn_CarCardView;

	//*******************************************************************
	//***********Header Verbiage Link Popup Car Page********************
	//*******************************************************************
	public final String xpath_IncludeCarTaxesPopupHeaderText = "//h2[contains(text(),'Alquiler de Autos') or contains(text(),'cualquier cambio')]";
	@FindBy(xpath=xpath_IncludeCarTaxesPopupHeaderText)
	private WebElement txt_IncludeCarTaxesPopupHeader;

	public final String xpath_IncludeCarTaxesPopupSubHeaderText = "//div[contains(text(),' El precio del alquiler del auto incluye los') or contains(text(),'cualquier cambio')]";
	@FindBy(xpath=xpath_IncludeCarTaxesPopupSubHeaderText)
	private WebElement txt_IncludeCarTaxesPopupSubHeader;

	public final String xpath_IncludeCarTaxesPopupCloseWindowButton = "//button[contains(text(),'Close Window') or contains(text(),'Cerrar la Ventana')]";
	@FindBy(xpath=xpath_IncludeCarTaxesPopupCloseWindowButton)
	private WebElement btn_IncludeCarTaxesPopupCloseWindow;

	//*******************************************************************
	//********Car Estimated Taxes pop-up Options Page********************
	//*******************************************************************
	public final String xpath_EstimatedTaxesPopUpHeaderText = "//app-estimated-car-taxes-and-fees-modal//h2";
	@FindBy(xpath=xpath_EstimatedTaxesPopUpHeaderText)
	private WebElement txt_EstimatedTaxesPopUpHeader;

	public final String xpath_EstimatedTaxesPopUpExitIconButton = "//app-estimated-car-taxes-and-fees-modal//button[contains(@class,'close')]//i";
	@FindBy(xpath=xpath_EstimatedTaxesPopUpExitIconButton)
	private WebElement btn_EstimatedTaxesPopUpExitIcon;

	public final String xpath_EstimatedTaxesBodyButton = "//app-estimated-car-taxes-and-fees-modal//div[@class='modal-body']";
	@FindBy(xpath=xpath_EstimatedTaxesBodyButton)
	private WebElement btn_EstimatedTaxesBody;

	public final String xpath_EstimatedTaxesPopUpCloseButton = "//app-estimated-car-taxes-and-fees-modal//button[contains(text(),'Close Window') or contains(text(),'Cerrar la Ventana')]";
	@FindBy(xpath=xpath_EstimatedTaxesPopUpCloseButton)
	private WebElement btn_EstimatedTaxesPopUpClose;

	//uplift amount
	public final String xpath_CarCardUpLiftAmountLinkButton = "//app-car//app-ancillary-item//span[@data-up-from-dollars='true']";
	@FindBy(xpath=xpath_CarCardUpLiftAmountLinkButton) //will only display is flight amount over $300
	private List<WebElement> lnkbtn_CarCardUpLiftAmount;

	//*******************************************************************
	//********************Who's Driving Window***************************
	//*******************************************************************
	public final String xpath_WhoSDrivingVerifyBookThisCarButton = "//app-package-detail-select-modal//button[contains(@class,'verify-car-button')]"; //new buttons on Who's driving? window
	@FindBy(xpath=xpath_WhoSDrivingVerifyBookThisCarButton)
	private WebElement btn_WhoSDrivingVerifyAndBookThisCar;

	public final String xpath_WhoSDrivingCancelButton = "//app-package-detail-select-modal//button[contains(@class,'cancel-car-button')]"; //new buttons on Who's driving? window
	@FindBy(xpath=xpath_WhoSDrivingCancelButton)
	private WebElement btn_WhoSDrivingCancel;

	//*******************************************************************
	//************Pick Car Pop-Up Options Page***************************
	//*******************************************************************
	//Book Car Button************newly added
	//public final String xpath_BookCarButton = "//button[contains(text(),'Add Car') or contains(text(),'Reservar Auto')]";
	public final String xpath_BookCarButton = "//*[@id='locations']/button";
	@FindBy(xpath=xpath_BookCarButton)
	private List<WebElement> btn_BookCar;

	public final String xpath_CarPopUpExitIconLinkButton = "//app-package-detail-select-modal//button[@class='close']";
	@FindBy(xpath=xpath_CarPopUpExitIconLinkButton)
	private WebElement lnkbtn_CarPopUpExitIcon;

	public final String xpath_CarPopUpCompanyMakeAndModelText = "//div[contains(@class,'modal-header')]/div/div[2]/h3";
	@FindBy(xpath = xpath_CarPopUpCompanyMakeAndModelText)
	private WebElement txt_CarPopUpCompanyMakeAndModel;

	public final String xpath_CarPopUpCompanyNameText = "//div[contains(@class,'modal-header')]/div/div[2]/p";
	@FindBy(xpath = xpath_CarPopUpCompanyNameText)
	private WebElement txt_CarPopUpCompanyName;

	public final String xpath_CarPopUpSeatIcon = "//div[contains(@class,'modal-header')]//img[@alt='Seat Icon']";
	@FindBy(xpath = xpath_CarPopUpSeatIcon)
	private WebElement icn_CarPopUpSeat;

	public final String xpath_CarPopUpSeatText = "//div[contains(@class,'modal-header')]//img[@alt='Seat Icon']/following-sibling::p";
	@FindBy(xpath = xpath_CarPopUpSeatText)
	private WebElement txt_CarPopUpSeat;

	public final String xpath_CarPopUpBagsIcon = "//div[contains(@class,'modal-header')]//img[@alt='Bag Icon']";
	@FindBy(xpath = xpath_CarPopUpBagsIcon)
	private WebElement icn_CarPopUpBags;

	public final String xpath_CarPopUpBagsText = "//div[contains(@class,'modal-header')]//img[@alt='Bag Icon']/following-sibling::p";
	@FindBy(xpath = xpath_CarPopUpBagsText)
	private WebElement txt_CarPopUpBags;

//	public final String xpath_CarPopUpAddCar = "//a[@class='nav-link']//button[contains(text(),'Add Car')]";
	public final String xpath_CarPopUpAddCar = "//app-package-detail-select-modal//button[contains(text(),'Add Car')]";
	@FindBy(xpath = xpath_CarPopUpAddCar)
	private WebElement btn_CarPopUpAddCar;

	public final String xpath_CarPopUpTransmissionTypeIcon = "//div[contains(@class,'modal-header')]//img[@alt='Transmission Type Icon']";
	@FindBy(xpath = xpath_CarPopUpTransmissionTypeIcon)
	private WebElement icn_CarPopUpTransmissionType;

	public final String xpath_CarPopUpAutomaticText = "//div[contains(@class,'modal-header')]//img[@alt='Transmission Type Icon']/following-sibling::p";
	@FindBy(xpath = xpath_CarPopUpAutomaticText)
	private WebElement txt_CarPopUpAutomatic;

	public final String xpath_CarPopUpMilesIcon = "//div[contains(@class,'modal-header')]//img[@alt='Miles Icon']";
	@FindBy(xpath = xpath_CarPopUpMilesIcon)
	private WebElement icn_CarPopUpMiles;

	public final String xpath_CarPopUpUnlimitedMilesText = "//div[contains(@class,'modal-header')]//img[@alt='Miles Icon']/following-sibling::p";
	@FindBy(xpath = xpath_CarPopUpUnlimitedMilesText)
	private WebElement txt_CarPopUpUnlimitedMiles;

	public final String xpath_CarPopUpUpliftPricingText = "//app-package-detail-select-modal//app-uplift-pricing/div";
	@FindBy(xpath=xpath_CarPopUpUpliftPricingText)
	private WebElement txt_CarPopUpUpliftPricing;

	public final String xpath_CarPopUpPrimaryDriverLabel = "//app-package-detail-select-modal//label[@for='primaryDriverIndex']";
	@FindBy(xpath=xpath_CarPopUpPrimaryDriverLabel)
	private WebElement lbl_CarPopUpPrimaryDriver;

	//Book Car - Driver Dropdown************newly added
	public final String xpath_CarPopUpPrimaryDriverDropDown = "//select[@id='primaryDriverIndex']";
	@FindBy(xpath=xpath_CarPopUpPrimaryDriverDropDown)
	private WebElement Drpdn_CarPopUpPrimaryDriver;

	public final String xpath_CarPopUpVehicleDescriptionTabPanel = "//a[@id='vehicleDescription']";
	@FindBy(xpath=xpath_CarPopUpVehicleDescriptionTabPanel)
	private WebElement pnl_CarPopUpVehicleDescriptionTab;

	public final String xpath_CarPopUpPoliciesTabPanel = "//a[@id='policies']";
	@FindBy(xpath=xpath_CarPopUpPoliciesTabPanel)
	private WebElement pnl_CarPopUpPoliciesTab;

	public final String xpath_CarPopUpLocationsTabPanel = "//a[@id='locations']";
	@FindBy(xpath=xpath_CarPopUpLocationsTabPanel)
	private WebElement pnl_CarPopUpLocationsTab;

	public final String xpath_CarPopUpLocationsTabLocationHoursText = "//div[@id='locations-panel']//div[contains(text(),'Location hours') or contains(text(),'Horas de la Ubicación')]";
	@FindBy(xpath=xpath_CarPopUpLocationsTabLocationHoursText)
	private WebElement txt_CarPopUpLocationsTabLocationHours;

	public final String xpath_CarPopUpVehicleDescriptionText = "//div[@id='vehicleDescription-panel']/div[2]//div";
	@FindBy(xpath = xpath_CarPopUpVehicleDescriptionText)
	private List<WebElement> txt_CarPopUpVehicleDescription;

//	public final String xpath_CarPopUpPoliciesWhatsIncludesText = "//div[@id='policies-panel']/div[1]";
//	@FindBy(xpath = xpath_CarPopUpPoliciesWhatsIncludesText)
//	private WebElement txt_CarPopUpPoliciesWhatsIncludes;

	public final String xpath_CarPopUpPoliciesRentalInclusionsText = "//div[@id='policies-panel']/div[1]";
	@FindBy(xpath = xpath_CarPopUpPoliciesRentalInclusionsText)
	private WebElement txt_CarPopUpPoliciesRentalInclusions;

	public final String xpath_CarPopUpPoliciesRentalExclusionsText = "//div[@id='policies-panel']/div[2]";
	@FindBy(xpath = xpath_CarPopUpPoliciesRentalExclusionsText)
	private WebElement txt_CarPopUpPoliciesRentalExclusions;

	public final String xpath_CarPopUpPoliciesRentalRulesText = "//div[@id='policies-panel']/div[3]/div[1]";
	@FindBy(xpath = xpath_CarPopUpPoliciesRentalRulesText)
	private WebElement txt_CarPopUpPoliciesRentalRules;

	public final String xpath_CarPopUpPoliciesChildSeatsRequiredText = "//div[@id='policies-panel']/div[3]/div[2]//div";
	@FindBy(xpath = xpath_CarPopUpPoliciesChildSeatsRequiredText)
	private List<WebElement> txt_CarPopUpPoliciesChildSeatsRequired;

	public final String xpath_CarPopUpPoliciesMinimumAgeText = "(//div[@id='policies-panel']//div[3])[3]//div";
	@FindBy(xpath = xpath_CarPopUpPoliciesMinimumAgeText)
	private List<WebElement> txt_CarPopUpPoliciesMinimumAge;

	public final String xpath_CarPopUpPoliciesRefundForUnusedText = "//div[@id='policies-panel']/div[3]/div[4]//div";
	@FindBy(xpath = xpath_CarPopUpPoliciesRefundForUnusedText)
	private List<WebElement> txt_CarPopUpPoliciesRefundForUnused;

	public final String xpath_CarPopUpPoliciesLicenseRequiredText = "//div[@id='policies-panel']//div[3]//div[5]//div";
	@FindBy(xpath = xpath_CarPopUpPoliciesLicenseRequiredText)
	private List<WebElement> txt_CarPopUpPoliciesLicenseRequired;

	public final String xpath_CarPopUpEstimatedTaxesAndFeesTabPanel = "//a[@id='estimatedTaxesAndFees']";
	@FindBy(xpath=xpath_CarPopUpEstimatedTaxesAndFeesTabPanel)
	private WebElement pnl_CarPopUpEstimatedTaxesAndFeesTab;
	//*******************************************************************
	//***********************Car Page************************************
	//*******************************************************************

	public final String xpath_CarsPageCarsPanel = "//app-car-list-item/div";
	@FindBy(xpath = xpath_CarsPageCarsPanel)
	private List<WebElement> pnl_CarsPageCars;

	//Pick Your Ride Header************newly added
	public final String xpath_CarsPagePickYourRideText = "//h1[contains(text(),'Pick Your Ride') or contains(text(),'Seleccione su auto')]";
	@FindBy(xpath=xpath_CarsPagePickYourRideText)
	private WebElement txt_CarsPagePickYourRide;

	//Continue Without Car Button************newly added
	public final String xpath_CarsPageContinueWithoutCarButton = "//button[contains(text(),'Continue Without Car') or contains(text(),'Continuar sin auto')]";
	@FindBy(xpath=xpath_CarsPageContinueWithoutCarButton)
	private WebElement btn_CarsPageContinueWithoutCar;

	public final String xpath_CarsPageCarSelectedPanel = "//app-selected-ancillary-item[@alt='Selected Car']";
	@FindBy(xpath=xpath_CarsPageCarSelectedPanel)//once a car is selected it will display selected panel
	private WebElement pnl_CarsPageCarSelected;

	public final String xpath_CarsPageCarRemoveButton = "//app-selected-ancillary-item[@alt='Selected Car']//button";
	@FindBy(xpath=xpath_CarsPageCarRemoveButton)
	private WebElement btn_CarsPageCarRemove;

	//SubHeader You weren't planning to walk everywhere, were you?
	public final String xpath_CarsPageYouWerentPlanningToWalkEverywhereText = "//h1[@class='headline1']/../p";
	@FindBy(xpath=xpath_CarsPageYouWerentPlanningToWalkEverywhereText)
	private WebElement txt_CarsPageYouWerentPlanningToWalkEverywhere;

	public final String xpath_CarsPageCarNameTextBox= "//input[@id='name']";
	@FindBy(xpath=xpath_CarsPageCarNameTextBox)
	private WebElement txtbx_CarsPageCarName;

	public final String xpath_CarsPageCarCounterText = "(//app-sortable-list//div[@class='sort-list-container']//span)[1]";
	@FindBy(xpath=xpath_CarsPageCarCounterText)
	private WebElement txt_CarsPageCarCounter;

	public final String xpath_CarsPageSortByDropDown = "//select[@id='sortby']";
	@FindBy(xpath=xpath_CarsPageSortByDropDown)
	private WebElement drpdwn_CarsPageSortBy;

	//recommended
	public final String id_SortByRecommendedButton = "radio-recommended";
	@FindBy(id= id_SortByRecommendedButton)
	private WebElement btn_SortByRecommended;

	//price
	public final String id_SortByPriceButton = "radio-price";
	@FindBy(id= id_SortByPriceButton)
	private WebElement btn_SortByPrice;

	//Bags
	public final String id_SortBySeatsButton = "radio-seats";
	@FindBy(id= id_SortBySeatsButton)
	private WebElement btn_SortBySeats;

	//CarType
	public final String id_SortByCarTypeButton = "radio-size";
	@FindBy(id= id_SortByCarTypeButton)
	private WebElement btn_SortByCarType;

	public final String xpath_CarsPageDisplayDropDown = "//select[@id='displayNumber']";
	@FindBy(xpath=xpath_CarsPageDisplayDropDown)
	private WebElement drpdwn_CarsPageDisplay;

	public final String xpath_CarsPageCarCompanyLogoImage = "//img[contains(@class,'img-fluid') and contains(@class,'logo')]";
	@FindBy(xpath=xpath_CarsPageCarCompanyLogoImage)
	private List<WebElement> img_CarsPageCarCompanyLogo;

	public final String xpath_CarsPageCarRentalImage= "//img[contains(@class,'img-fluid') and contains(@class,'logo')]/following::img[1]";
	@FindBy(xpath=xpath_CarsPageCarRentalImage)
	private List<WebElement> img_CarsPageCarRental;

	public final String xpath_CarsPageCarTypeText = "//img[contains(@class,'img-fluid') and contains(@class,'logo')]/following::div[1]//p[2]";
	@FindBy(xpath=xpath_CarsPageCarTypeText)
	private List<WebElement> txt_CarsPageCarType;

	public final String xpath_CarsPageCarModelText = "//app-car-list-item/div/div[2]/p[1]";
	@FindBy(xpath=xpath_CarsPageCarModelText)
	private List<WebElement> txt_CarsPageCarModel;

	//Number Of seats
	public final String xpath_CarsCardNumberOfSeatsText = "//img[@alt= 'Seat Icon']//following-sibling::p";
	@FindBy(xpath=xpath_CarsCardNumberOfSeatsText)
	private List<WebElement> txt_CarsCardNumberOfSeats;

	//Number of bags
	public final String xpath_CarsCardNumberOfBagsText = "//img[@alt= 'Bag Icon']//following-sibling::p";
	@FindBy(xpath=xpath_CarsCardNumberOfBagsText)
	private List<WebElement> txt_CarsCardNumberOfBags;

	//Transmission
	public final String xpath_CarsCardTransmissionText = "//img[@alt= 'Transmission Type Icon']//following-sibling::p";
	@FindBy(xpath=xpath_CarsCardTransmissionText)
	private List<WebElement> txt_CarsCardTransmissionType;

	//Mileage Limit
	public final String xpath_CarsCardMileageLimitText = "//img[@alt= 'Miles Icon']//following-sibling::p";
	@FindBy(xpath=xpath_CarsCardMileageLimitText)
	private List<WebElement> txt_CarsCardMileageLimit;

	public final String xpath_CarsPageMoreInfoLink = "//a[contains(text(),'More') or contains(text(),'Más ')]";
	@FindBy(xpath=xpath_CarsPageMoreInfoLink)
	private List<WebElement> lnk_CarsPageMoreInfo;

	public final String xpath_CarsPageIncludesTaxesLink = "//a[contains(text(),'Includes taxes') or contains(text(),'Incluye impuestos')]";
	@FindBy(xpath=xpath_CarsPageIncludesTaxesLink)
	private List<WebElement> lnk_CarsPageIncludesTaxes;

	public final String xpath_CarPageMoreInfoVehicleDescription = "//a[@id='vehicleDescription']";
	@FindBy(xpath=xpath_CarPageMoreInfoVehicleDescription)
	private WebElement lnk_CarPageMoreInfoVehicleDescription;

	public final String xpath_CarPageMoreInfoPolicies = "//a[@id='policies']";
	@FindBy(xpath=xpath_CarPageMoreInfoPolicies)
	private WebElement lnk_CarPageMoreInfoPolicies;

	public final String xpath_CarPageMoreInfoLocations = "//a[@id='locations']";
	@FindBy(xpath=xpath_CarPageMoreInfoLocations)
	private WebElement lnk_CarPageMoreInfoLocations;

	public final String xpath_CarPageMoreInfoLocationPickUpPoint = "//div[@id='locations-panel']//div[@class='text-capitalize']";
	@FindBy(xpath=xpath_CarPageMoreInfoLocationPickUpPoint)
	private List<WebElement> lnk_CarPageMoreInfoLocationPickUpPoint;

	public final String xpath_CarPageMoreInfoLocationPickUpVoiceText = "//div[@id='locations-panel']/div[2]/div/div/div[4]";
	@FindBy(xpath=xpath_CarPageMoreInfoLocationPickUpVoiceText)
	private WebElement txt_CarPageMoreInfoLocationPickUpVoice;

	public final String xpath_CarnetPickUpLocationDayTimeText = "//div[@id='locations-panel']/div[2]/div[2]/div/div";
	@FindBy(xpath=xpath_CarnetPickUpLocationDayTimeText)
	private List<WebElement> txt_CarnetPickUpLocationDayTime;

	public final String xpath_CarsPageLessInfoLink = "//a[contains(text(),'Less') or contains(text(),'Menos')]";
	@FindBy(xpath=xpath_CarsPageLessInfoLink)
	private WebElement lnk_CarsPageLessInfo;

	public final String xpath_CarsPageRentalPriceText = "//app-car-list-item/div/div[3]//strong[contains(text(),'$')]";
	@FindBy(xpath=xpath_CarsPageRentalPriceText)
	private List<WebElement> txt_CarsPageRentalPrice;

	public final String xpath_CarsPageExcludesTaxesLink = "//a[contains(text(),'excludes taxes')]";
	@FindBy(xpath=xpath_CarsPageExcludesTaxesLink)
	private List<WebElement> lnk_CarsPageExcludesTaxes;

	public final String xpath_CarsPageUpliftLink = "//img[contains(@class,'img-fluid') and contains(@class,'logo')]/following::div[2]//app-uplift-pricing/div";
	@FindBy(xpath=xpath_CarsPageUpliftLink)
	private List<WebElement> lnk_CarsPageUplift;

	public final String xpath_CarsPageUpliftCircleIcon = "//img[contains(@class,'img-fluid') and contains(@class,'logo')]/following::div[2]//i";
	@FindBy(xpath=xpath_CarsPageUpliftCircleIcon)
	private List<WebElement> icon_CarsPageUpliftCircle;

	public final String xpath_FACarTotalPriceText = "//app-car-list-item/div/div[3]/div/p[2]";
	@FindBy(xpath = xpath_FACarTotalPriceText)
	private List<WebElement> txt_FACarTotalPrice;

	public final String xpath_CarsPageBookButton = "//app-car-list-item//button";
	@FindBy(xpath = xpath_CarsPageBookButton)
	private List<WebElement> btn_CarsPageBook;

	public final String xpath_CarPageShowMoreButton = "//button[contains(text(),'Show More')]";
	@FindBy(xpath=xpath_CarPageShowMoreButton)
	private WebElement btn_CarPageShowMore;

	//*******************************************************************
	//**********************Selected Car Option Page*********************
	//*******************************************************************
	public final String xpath_SelectedCarCarModelText = "//strong[contains(text(),'Drop Off:') or contains(text(),'Entregar:')]/../../..//h5";
	@FindBy(xpath=xpath_SelectedCarCarModelText)
	private WebElement txt_SelectedCarCarModel;

	public final String xpath_SelectedCarCarTypeText = "//strong[contains(text(),'Drop Off:') or contains(text(),'Entregar:')]/../../..//h5/../p[2]";
	@FindBy(xpath=xpath_SelectedCarCarTypeText)
	private WebElement txt_SelectedCarCarType;

	public final String xpath_SelectedCarPickUPTimeText = "//strong[contains(text(),'Pick Up:') or contains(text(),'Recoger:')]/..";
	@FindBy(xpath=xpath_SelectedCarPickUPTimeText)
	private List<WebElement> txt_SelectedCarPickUPTime;

	public final String xpath_SelectedCarDropOffTimeText = "//strong[contains(text(),'Drop Off:') or contains(text(),'Entregar:')]/..";
	@FindBy(xpath=xpath_SelectedCarDropOffTimeText)
	private List<WebElement> txt_SelectedCarDropOffTime;

	//*******************************************************************
	//**********************Car Page(Check-In Path)**********************
	//*******************************************************************

	public final String xpath_CheckInCarsPageBookCarButton = "//button[@class='btn btn-primary' and contains(text(),'Book')]";
	@FindBy(xpath=xpath_CheckInCarsPageBookCarButton)
	private List<WebElement> btn_CheckInCarsPageBookCar;

	public final String xpath_CheckInCarNameText = "//p[contains(text(),'per day')]/../../div[2]/p[1]";
	@FindBy(xpath=xpath_CheckInCarNameText)
	private List<WebElement> txt_CheckInCarName;

	public final String xpath_CheckInCarTypeText = "//p[contains(text(),'per day')]/../../div[2]/p[1]//following::span[1]";
	@FindBy(xpath=xpath_CheckInCarTypeText)
	private List<WebElement> txt_CheckInCarType;

	public final String xpath_CheckInCarPriceText = "//p[contains(text(),'per day')]/../p[1]/strong";
	@FindBy(xpath=xpath_CheckInCarPriceText)
	private List<WebElement> txt_CheckInCarPrice;

	//*******************************************************************
	//******************Last Chance Popup(Check-In Path)*****************
	//*******************************************************************
	public final String xpath_LastChanceHeaderText = "//div[@class='modal-header']/h2";
	@FindBy(xpath=xpath_LastChanceHeaderText)
	private WebElement txt_LastChanceHeader;

	public final String xpath_LastChanceCloseButton = "//div[@class='modal-header']/button";
	@FindBy(xpath=xpath_LastChanceCloseButton)
	private WebElement btn_LastChanceClose;

	public final String xpath_LastChanceReserveACarText = "//div[@class='modal-body']/div/div";
	@FindBy(xpath=xpath_LastChanceReserveACarText)
	private WebElement txt_LastChanceReserveACar;

	public final String xpath_LastChanceViewAllCarsButton = "//div[@class='modal-body']//button[contains(@class,'btn-primary')]";
	@FindBy(xpath=xpath_LastChanceViewAllCarsButton)
	private WebElement btn_LastChanceViewAllCars;

	public final String xpath_LastChanceQuickPayText = "//div[@class='modal-body']//div[@class='option-card-img-container']//span";
	@FindBy( xpath=xpath_LastChanceQuickPayText)
	private WebElement txt_LastChanceQuickPay;

	public final String xpath_LastChanceCarCompanyLogoImage = "//div[@class='modal-body']//div[@class='option-card-img-container']//img[contains(@class,'car-logo')]";
	@FindBy(xpath=xpath_LastChanceCarCompanyLogoImage)
	private WebElement img_LastChanceCarCompanyLogo;

	public final String xpath_LastChanceCarLogoImage = "//div[@class='modal-body']//div[@class='option-card-img-container']//img[contains(@class,'options-img')]";
	@FindBy(xpath=xpath_LastChanceCarLogoImage)
	private WebElement img_LastChanceCarLogo;

	public final String xpath_LastChanceCarNameText = "//div[@class='modal-body']//div[@class='option-card-info-container']//span";
	@FindBy(xpath=xpath_LastChanceCarNameText)
	private WebElement txt_LastChanceCarName;

	public final String xpath_LastChanceCarPriceText = "//div[@class='modal-body']//div[@class='option-card-info-container']/div[3]/p";
	@FindBy(xpath=xpath_LastChanceCarPriceText)
	private WebElement txt_LastChanceCarPrice;

	public final String xpath_LastChanceBookCarButton = "//div[@class='modal-body']//a[contains(@class,'btn-secondary')]";
	@FindBy(xpath=xpath_LastChanceBookCarButton)
	private WebElement btn_LastChanceBookCar;

	public final String xpath_LastChanceContinueWithoutCarButton = "//div[@class='modal-body']//button[contains(@class,'btn-link')]";
	@FindBy(xpath=xpath_LastChanceContinueWithoutCarButton)
	private WebElement btn_LastChanceContinueWithoutCar;


	//*******************************************************************************************************************************************************
	//******************************************Gold Finger XPATH********************************************************************************************
	//*******************************************************************************************************************************************************

	///////////////////////////////////////////////////////////////////Left Ribbon Filter//////////////////////////////////////////////////////////////////////

	//Clear All Filters
	public final String xpath_ClearAllFilters = "//button[contains(text(),'Clear All Filters ')]";
	@FindBy(xpath=xpath_ClearAllFilters)
	private WebElement btn_CarsPageClearAllFilters;

	//Price Label
	public final String xpath_PriceFilterLabel = "//app-filter-price-slider//a//span";
	@FindBy(xpath=xpath_PriceFilterLabel)
	private WebElement lbl_PriceFilterLabel;
	//price Drop Down
	public final String xpath_PriceFilterDropDown = "//app-filter-price-slider//app-chevron/i";
	@FindBy(xpath=xpath_PriceFilterDropDown)
	private WebElement drpDwn_PriceFilterDropDown;
	//Price slider
	public final String xpath_PriceFilterSlider = "//app-filter-price-slider//ng5-slider";
	@FindBy(xpath=xpath_PriceFilterSlider)
	private WebElement sldr_PriceFilterSlider;
	//Price Clear Button
	public final String xpath_PriceFilterClear = "//app-filter-price-slider//button[contains(text(),'Clear')]";
	@FindBy(xpath=xpath_PriceFilterClear)
	private WebElement btn_PriceFilterClear;
	//price from text
	public final String xpath_PriceFilterFromPrice = "//app-filter-price-slider//div[contains(@class,'range')]//span[1]";
	@FindBy(xpath=xpath_PriceFilterFromPrice)
	private WebElement txt_PriceFilterFromPrice;
	//price to text
	public final String xpath_PriceFilterToPrice = "//app-filter-price-slider//div[contains(@class,'range')]//span[2]";
	@FindBy(xpath=xpath_PriceFilterToPrice)
	private WebElement txt_PriceFilterToPrice;

	//Seats Label
	public final String xpath_SeatsFilterLabel = "(//app-filter-list)[1]//a//span";
	@FindBy(xpath=xpath_SeatsFilterLabel)
	private WebElement lbl_SeatsFilterLabel;
	//Seats Drop Down
	public final String xpath_SeatsFilterDropDown = "(//app-filter-list)[1]//app-chevron/i";
	@FindBy(xpath=xpath_SeatsFilterDropDown)
	private WebElement drpDwn_SeatsFilterDropDown;
	//Seats Clear Button
	public final String xpath_SeatsFilterClear = "(//app-filter-list)[1]//button[contains(text(),'Clear')]";
	@FindBy(xpath=xpath_SeatsFilterClear)
	private WebElement btn_SeatsFilterClear;
	//Seats 4 or Less
//	public final String xpath_SeatsFilter4orLess = "(//app-filter-list)[1]//label[@for= 'Seats4']";
//	@FindBy(xpath=xpath_SeatsFilter4orLess)
//	private WebElement btn_SeatsFilter4orLess;
	//Seats 5 to 6
	public final String xpath_SeatsFilter5to6 = "(//app-filter-list)[1]//label[@for= 'Seats5']";
	@FindBy(xpath=xpath_SeatsFilter5to6)
	private WebElement btn_SeatsFilter5to6;
	//Seats 7 or more
	public final String xpath_SeatsFilter7orMore = "(//app-filter-list)[1]//label[@for= 'Seats7']";
	@FindBy(xpath=xpath_SeatsFilter7orMore)
	private WebElement btn_SeatsFilter7orMore;

	//Bags Label
	public final String xpath_BagsFilterLabel = "(//app-filter-list)[2]//a//span";
	@FindBy(xpath=xpath_BagsFilterLabel)
	private WebElement lbl_BagsFilterLabel;
	//Bags Drop Down
	public final String xpath_BagsFilterDropDown = "(//app-filter-list)[2]//app-chevron/i";
	@FindBy(xpath=xpath_BagsFilterDropDown)
	private WebElement drpDwn_BagsFilterDropDown;
	//Bags Clear Button
	public final String xpath_BagsFilterClear = "(//app-filter-list)[2]//button[contains(text(),'Clear')]";
	@FindBy(xpath=xpath_BagsFilterClear)
	private WebElement btn_BagsFilterClear;
	//Bags 1 to 2
	public final String xpath_BagsFilter1to2 = "(//app-filter-list)[2]//label[@for= 'Bags2']";
	@FindBy(xpath=xpath_BagsFilter1to2)
	private WebElement btn_BagsFilter1to2;
	//Bags 3 to 4
	public final String xpath_BagsFilter3to4 = "(//app-filter-list)[2]//label[@for= 'Bags3']";
	@FindBy(xpath=xpath_BagsFilter3to4)
	private WebElement btn_BagsFilter3to4;
	//Bags 5 or more
	public final String xpath_BagsFilter5orMore= "(//app-filter-list)[2]//label[@for= 'Bags5']";
	@FindBy(xpath=xpath_BagsFilter5orMore)
	private WebElement btn_BagsFilter5orMore;

	//Rental Agency Label
	public final String xpath_RentalAgencyFilterLabel = "(//app-filter-list)[4]//a//span";
	@FindBy(xpath=xpath_RentalAgencyFilterLabel)
	private WebElement lbl_RentalAgencyFilterLabel;
	//Rental Agency Drop Down
	public final String xpath_RentalAgencyFilterDropDown = "(//app-filter-list)[4]//app-chevron/i";
	@FindBy(xpath=xpath_RentalAgencyFilterDropDown)
	private WebElement drpDwn_RentalAgencyFilterDropDown;
	//Rental Agency Clear Button
	public final String xpath_RentalAgencyFilterClear = "(//app-filter-list)[4]//button[contains(text(),'Clear')]";
	@FindBy(xpath=xpath_RentalAgencyFilterClear)
	private WebElement btn_RentalAgencyFilterClear;
	//Rental Agency Company Name List
	public final String xpath_RentalAgencyFilterOptionList = "(//app-filter-list)[4]//div[@class='sectionTitle']//label/span";
	@FindBy(xpath=xpath_RentalAgencyFilterOptionList)
	private List<WebElement> btn_RentalAgencyFilterOptionList;

	//Car Type Label
	public final String xpath_CarTypeFilterLabel = "(//app-filter-list)[3]//a//span";
	@FindBy(xpath=xpath_CarTypeFilterLabel)
	private WebElement lbl_CarTypeFilterLabel;
	//Car Type Drop Down
	public final String xpath_CarTypeFilterDropDown = "(//app-filter-list)[3]//app-chevron/i";
	@FindBy(xpath=xpath_CarTypeFilterDropDown)
	private WebElement drpDwn_CarTypeFilterDropDown;
	//Car Type Clear Button
	public final String xpath_CarTypeFilterClear = "(//app-filter-list)[3]//button[contains(text(),'Clear')]";
	@FindBy(xpath=xpath_CarTypeFilterClear)
	private WebElement btn_CarTypeFilterClear;
	//Car Type List
	public final String xpath_CarTypeFilterOptionList = "(//app-filter-list)[3]//div[@class='sectionTitle']//label/span";
	@FindBy(xpath=xpath_CarTypeFilterOptionList)
	private List<WebElement> btn_CarTypeFilterOptionList;

	//Car Options Label
	public final String xpath_CarOptionsFilterLabel = "(//app-filter-list)[5]//a//span";
	@FindBy(xpath=xpath_CarOptionsFilterLabel)
	private WebElement lbl_CarOptionsFilterLabel;
	//Car Options Drop Down
	public final String xpath_CarOptionsFilterDropDown = "(//app-filter-list)[5]//app-chevron/i";
	@FindBy(xpath=xpath_CarOptionsFilterDropDown)
	private WebElement drpDwn_CarOptionsFilterDropDown;
	//Car Options Clear Button
	public final String xpath_CarOptionsFilterClear = "(//app-filter-list)[5]//button[contains(text(),'Clear')]";
	@FindBy(xpath=xpath_CarOptionsFilterClear)
	private WebElement btn_CarOptionsFilterClear;
	//Car Options List
	public final String xpath_CarOptionsFilterOptionList = "(//app-filter-list)[5]//div[@class='sectionTitle']//label/span";
	@FindBy(xpath=xpath_CarOptionsFilterOptionList)
	private List<WebElement> btn_CarOptionsFilterOptionList;


	//*******************************************************************************************************************************************
	//******************************************************Start getter Methods of Car Page****************************************************
	//*******************************************************************************************************************************************

	//****************************************************************
	//*******************Header Verbiage Car Page*********************
	//****************************************************************
	public WebElement getPricePerPersonText() {
		return txt_PricePerPerson;
	}
	public WebElement getFlightTaxFeesLink() {
		return lnk_FlightTaxFees;
	}
	public WebElement getVacationPackagesCarRentalsText() {
		return txt_VacationPackagesCarRentals;
	}
	public WebElement getIncludesCarTaxesLink() {
		return lnk_IncludesCarTaxes;
	}
	public WebElement getBaggageLink() {
		return lnk_Baggage;
	}
	public WebElement getAdvanceSeatAssignmentLink() {
		return lnk_AdvanceSeatAssignment;
	}
	public WebElement getAnyChangesLink() {
		return lnk_AnyChanges;
	}

	//*******************************************************************
	//*******************Cars Container Options Page*********************
	//*******************************************************************
	public WebElement getCarCarouselTitleText() {
		return txt_CarCarouselTitle;
	}

	public WebElement getCarCarouselLeftButton() {
		return btn_CarCarouselLeft;
	}

	public WebElement getCarCarouselRightButton() {
		return btn_CarCarouselRight;
	}

	public WebElement getViewAllCarsButton() {
		return lnkbtn_ViewAllCars;
	}

	public List<WebElement> getCarsPanel(){return pnl_Cars;}

	public List<WebElement> getCarsCarouselAddCarButton(){return btn_CarsCarouselAddCar;}
	//*******************************************************************
	//********Car Cards containers Options Page**************************
	//*******************************************************************
	public List<WebElement> getCarCardNameText() {
		return txt_CarCardName;
	}

	public List<WebElement> getCarCardPriceLink() {
		return lnkbtn_CarCardPrice;
	}

	public List<WebElement> getCarCardTotalPriceText(){
		return txt_CarCardTotalPrice;
	}

	public List<WebElement> getCarCardTaxesLink() {
		return lnkbtn_CarCardTaxes;
	}

	public List<WebElement> getCarCardCompanyNameText() {
		return txt_CarCardCompanyName;
	}
	public List<WebElement> getCarCardSeatsText() {
		return txt_CarCardSeats;
	}
	public List<WebElement> getCarCardTransmissionText() {
		return txt_CarCardTransmission;
	}
	public List<WebElement> getCarCardBaggageText() {
		return txt_CarCardBaggage;
	}
	public List<WebElement> getCarCardMileageLinkText() {
		return txt_CarCardMileageLimit;
	}

	public List<WebElement> getCarCardUpliftPricingText(){
		return txt_CarCardUpliftPricing;
	}

	public List<WebElement> getCarcardViewLink() {
		return lnkbtn_CarCardView;
	}

	//*******************************************************************
	//***********Header Verbiage Link Popup Car Page********************
	//*******************************************************************
	public WebElement getIncludeCarTaxesPopupHeaderText() {
		return txt_IncludeCarTaxesPopupHeader;
	}
	public WebElement getIncludeCarTaxesPopupSubHeaderText() {
		return txt_IncludeCarTaxesPopupSubHeader;
	}
	public WebElement getIncludeCarTaxesPopupCloseWindowButton() {
		return btn_IncludeCarTaxesPopupCloseWindow;
	}

	//*******************************************************************
	//********Car Estimated Taxes pop-up Options Page********************
	//*******************************************************************
	public WebElement getEstimatedTaxesPopUpHeaderText() {
		return txt_EstimatedTaxesPopUpHeader;
	}

	public WebElement getEstimatedTaxesPopUpExitIconButton() {
		return btn_EstimatedTaxesPopUpExitIcon;
	}

	public WebElement getEstimatedTaxesBodyText() {
		return btn_EstimatedTaxesBody;
	}

	public WebElement getEstimatedTaxesPopUpCloseButton() {
		return btn_EstimatedTaxesPopUpClose;
	}

	public List<WebElement> getCarCardUpLiftAmountLink() {
		return lnkbtn_CarCardUpLiftAmount;
	}

	//*******************************************************************
	//********************Who's Driving Window***************************
	//*******************************************************************
	public WebElement getWhoSDrivingVerifyAndBookThisCarButton() {
		return btn_WhoSDrivingVerifyAndBookThisCar;
	}

	public WebElement getWhoSDrivingCancelButton() {
		return btn_WhoSDrivingCancel;
	}

	//*******************************************************************
	//************Pick Car Pop-Up Options Page***************************
	//*******************************************************************
	public List<WebElement> getBookCarButton() {
		return btn_BookCar;
	}

	public WebElement getCarPopUpExitIconButton() {
		return lnkbtn_CarPopUpExitIcon;
	}

	public WebElement getCarPopUpCompanyMakeAndModelText(){return txt_CarPopUpCompanyMakeAndModel;}

	public WebElement getCarPopUpCompanyNameText(){return txt_CarPopUpCompanyName;}

	public WebElement getCarPopUpSeatIcon(){
		return icn_CarPopUpSeat;
	}

	public WebElement getCarPopUpSeatText(){
		return txt_CarPopUpSeat;
	}

	public WebElement getCarPopUpBagsIcon(){
		return icn_CarPopUpBags;
	}

	public WebElement getCarPopUpBagsText(){
		return txt_CarPopUpBags;
	}

	public WebElement getCarPopUpTransmissionTypeIcon(){
		return icn_CarPopUpTransmissionType;
	}

	public WebElement getCarPopUpAutomaticText(){
		return txt_CarPopUpAutomatic;
	}

	public WebElement getCarPopUpMilesIcon(){
		return icn_CarPopUpMiles;
	}

	public WebElement getCarPopUpUnlimitedMilesText(){
		return txt_CarPopUpUnlimitedMiles;
	}

	public WebElement getCarPopUpUpliftPricingText() {
		return txt_CarPopUpUpliftPricing;
	}

	public WebElement getCarPopUpPrimaryDriverlabel() {
		return lbl_CarPopUpPrimaryDriver;
	}

	public WebElement getCarPopUpPrimaryDriverDropDown() {
		return Drpdn_CarPopUpPrimaryDriver;
	}

	public WebElement getCarPopUpVehicleDescriptionTab() {
		return pnl_CarPopUpVehicleDescriptionTab;
	}

	public WebElement getCarPopUpPoliciesTab() {
		return pnl_CarPopUpPoliciesTab;
	}

	public WebElement getCarPopUpAddCar() {
		return btn_CarPopUpAddCar;
	}

	public WebElement getCarPopUpLocationsTab() {return pnl_CarPopUpLocationsTab;}

	public WebElement getCarPopUpLocationsTabLocationHoursText() {return txt_CarPopUpLocationsTabLocationHours;}

	public List<WebElement> getCarPopUpVehicleDescriptionText(){
		return txt_CarPopUpVehicleDescription;
	}

//	public WebElement getCarPopUpPoliciesWhatsIncludesText(){
//		return txt_CarPopUpPoliciesWhatsIncludes;
//	}

	public WebElement getCarPopUpPoliciesRentalInclusionsText(){
		return txt_CarPopUpPoliciesRentalInclusions;
	}

	public WebElement getCarPopUpPoliciesRentalExclusionsText(){
		return txt_CarPopUpPoliciesRentalExclusions;
	}

	public WebElement getCarPopUpPoliciesRentalRulesText(){
		return txt_CarPopUpPoliciesRentalRules;
	}

	public List<WebElement>  getCarPopUpPoliciesChildSeatsRequiredText(){
		return txt_CarPopUpPoliciesChildSeatsRequired;
	}

	public List<WebElement>  getCarPopUpPoliciesMinimumAgeText(){
		return txt_CarPopUpPoliciesMinimumAge;
	}

	public List<WebElement>  getCarPopUpPoliciesRefundForUnusedText(){
		return txt_CarPopUpPoliciesRefundForUnused;
	}

	public List<WebElement> getCarPopUpPoliciesLicenseRequiredText(){
		return txt_CarPopUpPoliciesLicenseRequired;
	}

	public WebElement getCarPopUpEstimatedTaxesAndFeesTab() {
		return pnl_CarPopUpEstimatedTaxesAndFeesTab;
	}
	//*******************************************************************
	//***************************Car Page********************************
	//*******************************************************************

	public List<WebElement> getCarsPageCarsPanel(){
		return pnl_CarsPageCars;
	}

	public WebElement getCarsPagePickYourRideText() {
		return txt_CarsPagePickYourRide;
	}

	public WebElement getCarsPageContinueWithoutCarButton() {
		return btn_CarsPageContinueWithoutCar;
	}

	public WebElement getCarsPageCarSelectedPanel() {
		return pnl_CarsPageCarSelected;
	}

	public WebElement getCarsPageCarRemoveButton() {
		return btn_CarsPageCarRemove;
	}

	public WebElement getCarsPageYouWerentPlanningToWalkEverywhereText() { return txt_CarsPageYouWerentPlanningToWalkEverywhere; }

	public WebElement getCarsPageCarNameTextbx() { return txtbx_CarsPageCarName; }

	public WebElement getCarsPageCarCounterText() { return txt_CarsPageCarCounter; }

	public WebElement getCarsPageSortByDrpdwn() { return drpdwn_CarsPageSortBy; }

	public WebElement getSortByRecommendedButton() {
		return btn_SortByRecommended;
	}

	public WebElement getSortByPriceButton() {
		return btn_SortByPrice;
	}

	public WebElement getSortBySeatsButton() {
		return btn_SortBySeats;
	}

	public WebElement getSortByCarTypeButton() {
		return btn_SortByCarType;
	}

	public WebElement getCarsPageDisplayDrpdwn() { return drpdwn_CarsPageDisplay; }

	public List<WebElement> getCarsPageCarCompanyLogoImg() { return img_CarsPageCarCompanyLogo; }

	public List<WebElement> getCarsPageCarRentalImg() { return img_CarsPageCarRental; }

	public List<WebElement> getCarsPageCarTypeText() { return txt_CarsPageCarType; }

	public List<WebElement> getCarsPageCarModelText() { return txt_CarsPageCarModel; }

	public List<WebElement> getCarsCardNumberOfSeatsText() {
		return txt_CarsCardNumberOfSeats;
	}

	public List<WebElement> getCarsCardNumberOfBagsText() {
		return txt_CarsCardNumberOfBags;
	}

	public List<WebElement> getCarsCardTransmissionTypeText() {
		return txt_CarsCardTransmissionType;
	}

	public List<WebElement> getCarsCardMileageLimitText() {
		return txt_CarsCardMileageLimit;
	}

	public List<WebElement> getCarsPageMoreInfoLink() { return lnk_CarsPageMoreInfo; }

	public List<WebElement> getCarsPageIncludesTaxesLink() { return lnk_CarsPageIncludesTaxes; }

	public WebElement getCarPageMoreInfoVehicleDescriptionLink(){
		return lnk_CarPageMoreInfoVehicleDescription;
	}

	public WebElement getCarPageMoreInfoPoliciesLink(){
		return lnk_CarPageMoreInfoPolicies;
	}

	public WebElement getCarPageMoreInfoLocationsLink(){
		return lnk_CarPageMoreInfoLocations;
	}

	public List<WebElement> getCarPageMoreInfoLocationPickUpPointLink(){
		return lnk_CarPageMoreInfoLocationPickUpPoint;
	}

	public WebElement getCarPageMoreInfoLocationPickUpVoiceText(){
		return txt_CarPageMoreInfoLocationPickUpVoice;
	}

	public List<WebElement> getCarnetPickUpLocationDayTimeText(){
		return txt_CarnetPickUpLocationDayTime;
	}

	public WebElement getCarsPageLessInfoLink(){
		return lnk_CarsPageLessInfo;
	}

	public List<WebElement> getCarsPageRentalPriceText() { return txt_CarsPageRentalPrice; }

	public List<WebElement> getCarsPageExcludesTaxesLink() { return lnk_CarsPageExcludesTaxes; }

	public List<WebElement> getCarsPageUpliftlnk() { return lnk_CarsPageUplift; }

	public List<WebElement> getCarsPageUpliftCircleIcon() { return icon_CarsPageUpliftCircle; }

	public List<WebElement> getFACarTotalPriceText(){
		return txt_FACarTotalPrice;
	}

	public List<WebElement> getCarsPageBookButton(){
		return btn_CarsPageBook;
	}

	public WebElement getCarPageShowMoreButton() {return btn_CarPageShowMore;}
	//*******************************************************************
	//**********************Selected Car Optional Page*******************
	//*******************************************************************
	public WebElement getSelectedCarCarModelText(){
		return txt_SelectedCarCarModel;
	}

	public WebElement getSelectedCarCarTypeText(){
		return txt_SelectedCarCarType;
	}

	public WebElement getSelectedCarPickUPTimeText(){
		for(WebElement pickUpTime : txt_SelectedCarPickUPTime){
			if(pickUpTime.isDisplayed()){
				return pickUpTime;
			}
		}

		return null;
	}

	public WebElement getSelectedCarDropOffTimeText(){
		for(WebElement dropOffTime : txt_SelectedCarDropOffTime){
			if(dropOffTime.isDisplayed()){
				return dropOffTime;
			}
		}

		return null;
	}

	//*******************************************************************
	//**********************Car Page(Check-In Path)**********************
	//*******************************************************************
	public List<WebElement> getCheckInCarsPageBookCarButton() {
		return btn_CheckInCarsPageBookCar; }

	public List<WebElement> getCheckInCarNameText() {
		return txt_CheckInCarName; }

	public List<WebElement> getCheckInCarTypeText() {
		return txt_CheckInCarType; }

	public List<WebElement> getCheckInCarPriceText() {
		return txt_CheckInCarPrice; }

	//*******************************************************************
	//******************Last Chance Popup(Check-In Path)*****************
	//*******************************************************************
	public WebElement getLastChanceHeaderText() {
		return txt_LastChanceHeader;
	}

	public WebElement getLastChanceCloseButton() {
		return btn_LastChanceClose;
	}

	public WebElement getLastChanceReserveACarText() {
		return txt_LastChanceReserveACar;
	}

	public WebElement getLastChanceViewAllCarsButton() {
		return btn_LastChanceViewAllCars;
	}

	public WebElement getLastChanceQuickPayText() {
		return txt_LastChanceQuickPay;
	}

	public WebElement getLastChanceCarCompanyLogoImage() {
		return img_LastChanceCarCompanyLogo;
	}

	public WebElement getLastChanceCarLogoImage() {
		return img_LastChanceCarLogo;
	}

	public WebElement getLastChanceCarNameText() {
		return txt_LastChanceCarName;
	}

	public WebElement getLastChanceCarPriceText() {
		return txt_LastChanceCarPrice;
	}

	public WebElement getLastChanceBookCarButton() {
		return btn_LastChanceBookCar;
	}

	public WebElement getLastChanceContinueWithoutCarButton() {
		return btn_LastChanceContinueWithoutCar;
	}

	public WebElement getCarsPageClearAllFiltersButton() {
		return btn_CarsPageClearAllFilters;
	}

	public WebElement getPriceFilterLabel() {
		return lbl_PriceFilterLabel;
	}

	public WebElement getPriceFilterDropDown() {
		return drpDwn_PriceFilterDropDown;
	}

	public WebElement getPriceFilterSlider() {
		return sldr_PriceFilterSlider;
	}

	public WebElement getPriceFilterClearButton() {
		return btn_PriceFilterClear;
	}

	public WebElement getPriceFilterFromPriceText() {
		return txt_PriceFilterFromPrice;
	}

	public WebElement getPriceFilterToPriceText() {
		return txt_PriceFilterToPrice;
	}

	public WebElement getSeatsFilterLabel() {
		return lbl_SeatsFilterLabel;
	}

	public WebElement getSeatsFilterDropDown() {
		return drpDwn_SeatsFilterDropDown;
	}

	public WebElement getSeatsFilterClearButton() {
		return btn_SeatsFilterClear;
	}

//	public WebElement getSeatsFilter4orLessButton() {
//		return btn_SeatsFilter4orLess;
//	}

	public WebElement getSeatsFilter5to6Button() {
		return btn_SeatsFilter5to6;
	}

	public WebElement getSeatsFilter7orMoreButton() {
		return btn_SeatsFilter7orMore;
	}

	public WebElement getBagsFilterLabel() {
		return lbl_BagsFilterLabel;
	}

	public WebElement getBagsFilterDropDown() {
		return drpDwn_BagsFilterDropDown;
	}

	public WebElement getBagsFilterClearButton() {
		return btn_BagsFilterClear;
	}

	public WebElement getBagsFilter1to2Button() {
		return btn_BagsFilter1to2;
	}

	public WebElement getBagsFilter3to4Button() {
		return btn_BagsFilter3to4;
	}

	public WebElement getBagsFilter5orMoreButton() {
		return btn_BagsFilter5orMore;
	}

	public WebElement getRentalAgencyFilterLabel() {
		return lbl_RentalAgencyFilterLabel;
	}

	public WebElement getRentalAgencyFilterDropDown() {
		return drpDwn_RentalAgencyFilterDropDown;
	}

	public WebElement getRentalAgencyFilterClearButton() {
		return btn_RentalAgencyFilterClear;
	}

	public List<WebElement> getRentalAgencyFilterOptionListButton() {
		return btn_RentalAgencyFilterOptionList;
	}

	public WebElement getCarTypeFilterLabel() {
		return lbl_CarTypeFilterLabel;
	}

	public WebElement getCarTypeFilterDropDown() {
		return drpDwn_CarTypeFilterDropDown;
	}

	public WebElement getCarTypeFilterClearButton() {
		return btn_CarTypeFilterClear;
	}

	public List<WebElement> getCarTypeFilterOptionListButton() {
		return btn_CarTypeFilterOptionList;
	}

	public WebElement getCarOptionsFilterLabel() {
		return lbl_CarOptionsFilterLabel;
	}

	public WebElement getCarOptionsFilterDropDown() {
		return drpDwn_CarOptionsFilterDropDown;
	}

	public WebElement getCarOptionsFilterClearButton() {
		return btn_CarOptionsFilterClear;
	}

	public List<WebElement> getCarOptionsFilterOptionListButton() {
		return btn_CarOptionsFilterOptionList;
	}

	//******************************************Newly created***************************

	public final String xpath_CarsPageCarPanels = "//div[contains(@class,'car-card-container ng-star-inserted')]";
	@FindBy(xpath=xpath_CarsPageCarPanels)
	private List<WebElement> pnl_CarsPageCarPanels;

	public List<WebElement> getCarsPageCarPanels() {
		return pnl_CarsPageCarPanels;
	}

	//i[contains(@class,'icon-user')]

	public final String xpath_CarsPageSeatIcon = "//i[contains(@class,'icon-user')]";
	@FindBy(xpath=xpath_CarsPageSeatIcon)
	private List<WebElement> icn_CarsPageSeatIcon;

	public List<WebElement> getCarsPageSeatIcon() {
		return icn_CarsPageSeatIcon;
	}


	public final String xpath_CarsPageSeatNumber = "//span[contains(text(),'Seats') or contains(text(),'Asientos')]";
	@FindBy(xpath=xpath_CarsPageSeatNumber)
	private List<WebElement> txt_CarsPageSeatNumber;

	public List<WebElement> getCarsPageSeatNumber() {
		return txt_CarsPageSeatNumber;
	}

	public final String xpath_CarsPagePricePerDay = "//p[contains(text(),'per day') or contains(text(),'por día')]";
	@FindBy(xpath=xpath_CarsPagePricePerDay)
	private List<WebElement> txt_CarsPagePricePerDay;

	public List<WebElement> getCarsPagePricePerDay() {
		return txt_CarsPagePricePerDay;

	}

	public final String xpath_PaidAtRentalCarCounterText = "//p[contains(text(),'Paid at Rental Car Counter') or contains(text(),'Pagado en el mostrador de alquiler de autos')]";
	@FindBy(xpath=xpath_PaidAtRentalCarCounterText)
	private WebElement txt_PaidAtRentalCarCounter;

	public WebElement getPaidAtRentalCarCounterText() { return txt_PaidAtRentalCarCounter; }


	public final String xpath_BreakdownCarPriceText = "//div[contains(@class,'total-breakdown')]//p[contains(text(),'$')]";
	@FindBy(xpath=xpath_BreakdownCarPriceText)
	private WebElement txt_BreakdownCarPrice;

	public WebElement getBreakdownCarPricerText() { return txt_BreakdownCarPrice; }

	public final String xpath_AddCarButton = "//button[contains(text(),'Add Car') or contains(text(),'Añadir auto')]";
	@FindBy(xpath = xpath_AddCarButton)
	private List<WebElement> btn_AddCar;
	public List<WebElement> getAddCarButton(){return btn_AddCar;}

}