package com.spirit.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelPage {

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

	public HotelPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*******************************************************************
	//*******************HOTELS Options Page*****************************
	//*******************************************************************
	public final String xpath_HotelCarouselTitleText = "//h3[contains(text(),'Hotels for Less') or contains(text(),'Hoteles por menos')]";
	@FindBy(xpath=xpath_HotelCarouselTitleText)
	private WebElement txt_HotelCarouselTitle;

	public final String xpath_HotelCarouselLeftButton = "//app-hotel//i[contains(@class,'left')]";
	@FindBy(xpath=xpath_HotelCarouselLeftButton)
	private WebElement btn_HotelCarouselLeft;

	public final String xpath_HotelCarouselRightButton = "//app-hotel//i[contains(@class,'right')]";
	@FindBy(xpath=xpath_HotelCarouselRightButton)
	private WebElement btn_HotelCarouselRight;

	public final String xpath_ViewAllHotelsLinkButton = "//button[contains(text(),'View All Hotels') or contains(text(),'Ver todos los Hoteles')]";
	@FindBy(xpath=xpath_ViewAllHotelsLinkButton)
	private WebElement lnkbtn_ViewAllHotels;

	public final String xpath_HotelPanel = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]";
	@FindBy(xpath = xpath_HotelPanel)
	private List<WebElement> pnl_Hotel;

	//*******************************************************************
	//********Hotel Card Container Options Page**************************
	//*******************************************************************
	//hotel Name
	public final String xpath_HotelCardNameText = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]//div[contains(@class,'hotel-name')]//strong";
	@FindBy(xpath=xpath_HotelCardNameText)
	private List<WebElement> txt_HotelCardName;

	public final String xpath_HotelPriceText = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]//div[contains(@class,'option-card-info-container')]/div[3]/p[contains(text(),'$') and not(contains(text(),'Total'))]";
	@FindBy(xpath = xpath_HotelPriceText)
	private List<WebElement> lnkbtn_HotelCardPrice;

	public final String xpath_HotelCardTotalPriceText = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]//div[contains(@class,'option-card-info-container')]/div[3]/p[contains(text(),'$') and contains(text(),'Total')]";
	@FindBy(xpath = xpath_HotelCardTotalPriceText)
	private List<WebElement> txt_HotelCardTotalPrice;

	public final String xpath_HotelCardUpliftPriceLink = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]//div[contains(@class,'option-card-info-container')]/div[4]//app-uplift-pricing//span[@class='uplift-underline']";
	@FindBy(xpath = xpath_HotelCardUpliftPriceLink)
	private List<WebElement> txt_HotelCardUpliftPricing;

	public final String xpath_HotelCardLocationText = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]//div[contains(@class,'hotel-name')]//p[contains(@class,'card-text')]";
	@FindBy(xpath=xpath_HotelCardLocationText)
	private List<WebElement> txt_HotelCardLocation;

	//Hotel Start rating
	public final String xpath_HotelCardStarRating = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]//app-rating";
	@FindBy(xpath = xpath_HotelCardStarRating)
	private List<WebElement> img_HotelCardStarRating;

	//Hotel View Room button
	public final String xpath_HotelCardViewRoomButton = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]//button[contains(text(),'View Rooms') or contains(text(),'Ver Habitacion')]";
	@FindBy(xpath = xpath_HotelCardViewRoomButton)
	private List<WebElement> btn_HotelCardViewRoom;

	//Hotel included options
	public final String xpath_HotelCardIncludedOptions = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]//app-rating//following::div[1]/div";
	@FindBy(xpath = xpath_HotelCardIncludedOptions)
	private List<WebElement> img_HotelCardIncludedOptions;

	//view hotel
	public final String xpath_HotelCardViewLinkButton = "//app-hotel//app-ancillary-item[contains(@class,'options-table')]//*[contains(text(),'View') or contains(text(),'Ver')]";
	@FindBy(xpath=xpath_HotelCardViewLinkButton)
	private List<WebElement> lnkbtn_HotelCardView;

	//Remove button
	public final String xpath_HotelCardRemoveLinkButton = "//app-hotel//app-selected-ancillary-item//button[contains(text(),'Remove') or contains(text(),'Quitar')]";
	@FindBy(xpath=xpath_HotelCardRemoveLinkButton)
	private WebElement lnkbtn_HotelCardRemove;

	//Edit button
	public final String xpath_HotelCardEditLinkButton = "//app-hotel//app-selected-ancillary-item//button[contains(text(),'Edit') or contains(text(),'Editar')]";
	@FindBy(xpath=xpath_HotelCardEditLinkButton)
	private WebElement lnkbtn_HotelCardEdit;

	//Edit Hotel Pop Up Title
	public final String xpath_HotelCardEditPopUpTitleText = "//app-branded-modal//h2";
	@FindBy(xpath=xpath_HotelCardEditPopUpTitleText)
	private WebElement txt_HotelCardEditPopUpTitle;

	//Edit Hotel Pop Up Close Button
	public final String xpath_HotelCardEditPopUpCloseButton = "//app-branded-modal//button[@class='close']";
	@FindBy(xpath=xpath_HotelCardEditPopUpCloseButton)
	private WebElement btn_HotelCardEditPopUpClose;

	//Edit Hotel Pop Up Continue to Flight Button
	public final String xpath_HotelCardEditPopUpContinueToFlightButton = "//app-branded-modal//button[contains(text(),'Continue to Flights') or contains(text(),'Continuar a los vuelos')]";
	@FindBy(xpath=xpath_HotelCardEditPopUpContinueToFlightButton)
	private WebElement btn_HotelCardEditPopUpContinueToFlight;

	//***********************************************************Hotel Page******************************************************
	public final String xpath_WhereAreYouStayingText = "//h1[contains(text(),'Where Are You Staying?') or contains(text(),'¿A dónde se quedará?')]";
	@FindBy(xpath=xpath_WhereAreYouStayingText)
	private WebElement txt_WhereAreYouStaying;

	public final String xpath_ViewAllHotelCardViewLinkButton = "//app-hotel-list-item//button[contains(text(),'View') or contains(text(),'Ver')]";
	@FindBy(xpath=xpath_ViewAllHotelCardViewLinkButton)
	private List<WebElement> lnkbtn_ViewAllHotelCardView;

	//*******************************************************************
	//*********************Refundability Pop-Up *************************
	//*******************************************************************

	public final String xpath_RefundabilityPopUpHeaderText = "//app-refundability-modal//h2";
	@FindBy(xpath=xpath_RefundabilityPopUpHeaderText)
	private WebElement txt_RefundabilityPopUpHeader;

	public final String xpath_RefundabilityPopUpCloseButton = "//app-refundability-modal//Button[contains(@class,'close')]";
	@FindBy(xpath=xpath_RefundabilityPopUpCloseButton)
	private WebElement btn_RefundabilityPopUpClose;

	public final String xpath_RefundabilityPopUpBodyText1Text = "//app-refundability-modal//p[1]";
	@FindBy(xpath=xpath_RefundabilityPopUpBodyText1Text)
	private WebElement txt_RefundabilityPopUpBodyText1;

	public final String xpath_RefundabilityPopUpBodyText2Text = "//app-refundability-modal//p[2]";
	@FindBy(xpath=xpath_RefundabilityPopUpBodyText2Text)
	private WebElement txt_RefundabilityPopUpBodyText2;

	public final String xpath_RefundabilityPopUpCloseWindowButton = "//app-refundability-modal//Button[contains(@class,'text')]";
	@FindBy(xpath=xpath_RefundabilityPopUpCloseWindowButton)
	private WebElement btn_RefundabilityPopUpCloseWindow;

	//*******************************************************************
	//************Pick Hotel Pop-Up Options Page*************************
	//*******************************************************************
	public final String xpath_HotelPopUpHotelNameText = "//h2[contains(text(),'Room category') or contains(text(),'Categoría de la habitación')]/../following-sibling::div[2]//h3";
	@FindBy(xpath=xpath_HotelPopUpHotelNameText)
	private WebElement txt_HotelPopUpHotelName;

	public final String xpath_HotelPopUpLandmarkNameText = "//h2[contains(text(),'Room category') or contains(text(),'Categoría de la habitación')]/../following-sibling::div[2]//span";
	@FindBy(xpath=xpath_HotelPopUpLandmarkNameText)
	private WebElement txt_HotelPopUpLandmarkName;

	public final String xpath_HotelPopUpAddressText = "//h2[contains(text(),'Room category') or contains(text(),'Categoría de la habitación')]/../following-sibling::div[2]//p";
	@FindBy(xpath=xpath_HotelPopUpAddressText)
	private WebElement txt_HotelPopUpAddress;

	public final String xpath_HotelPopUpExitIconLinkButton = "//app-package-detail-select-modal//button[@class='close']";
	@FindBy(xpath=xpath_HotelPopUpExitIconLinkButton)
	private WebElement lnkbtn_HotelPopUpExitIcon;

	public final String xpath_HotelPopUpUpliftPricingText = "//app-package-detail-select-modal//app-uplift-pricing/div//span[contains(@class,'uplift-underline')]";
	@FindBy(xpath=xpath_HotelPopUpUpliftPricingText)
	private WebElement txt_HotelPopUpUpliftPricing;

	public final String xpath_HotelPopUpSelectRoomsFromLinkButton = "//a[@id='roomSelect']/button";
	@FindBy(xpath=xpath_HotelPopUpSelectRoomsFromLinkButton)
	private WebElement linkbtn_HotelPopUpSelectRoomsFrom;

//	public final String xpath_HotelPopUpSelectRoomButton = "//div[@id='roomSelect-panel']//button";
//	@FindBy(xpath=xpath_HotelPopUpSelectRoomButton)
//	private List<WebElement> btn_HotelPopUpSelectRoom;

	public final String xpath_HotelPopUpSelectRoomNewButton = "//app-package-detail-select-modal//div[@id='rooms-panel']//button";
	@FindBy(xpath=xpath_HotelPopUpSelectRoomNewButton)
	private List<WebElement> btn_HotelPopUpSelectRoom;

	public final String xpath_HotelSelectRoomNewButton = "//div[@id='rooms-panel']//button";
	@FindBy(xpath=xpath_HotelSelectRoomNewButton)
	private List<WebElement> btn_HotelSelectRoom;

	public final String xpath_HotelPopUpRoomPrice = "//div[@id='rooms-panel']//div[contains(@class,'card-body')]/span[2]";
	@FindBy(xpath = xpath_HotelPopUpRoomPrice)
	private List<WebElement> txt_HotelPopUpRoomPrice;

//	public final String xpath_HotelPopUpRoomPrice = "//div[@id='roomSelect-panel']//div[contains(@class,'card-body')]/span[2]";
//	@FindBy(xpath = xpath_HotelPopUpRoomPrice)
//	private List<WebElement> txt_HotelPopUpRoomPrice;

	public final String xpath_HotelPopUpRoomTypeText = "//div[@id='rooms-panel']//div[contains(@class,'card-body')]/span[1]";
	@FindBy(xpath = xpath_HotelPopUpRoomTypeText)
	private List<WebElement> txt_HotelPopUpRoomType;

//	public final String xpath_HotelPopUpRoomTypeTextNew = "//div[@id='rooms-panel']//div[contains(@class,'card-body')]/span[1]";
//	@FindBy(xpath = xpath_HotelPopUpRoomTypeTextNew)
//	private List<WebElement> txt_HotelPopUpRoomTypeNew;

	public final String xpath_HotelPopUpSelectRoomNumberButton = "//app-package-detail-select-modal//button[contains(text(),'Select Room')]";//----------------------------------------
	@FindBy(xpath=xpath_HotelPopUpSelectRoomNumberButton)
	private  List<WebElement> btn_HotelPopUpSelectRoomNumber;

	public final String xpath_HotelPopUpRoomsTabPanel = "//a[@id='rooms']";
	@FindBy(xpath=xpath_HotelPopUpRoomsTabPanel)
	private WebElement pnl_HotelPopUpRoomsTab;

	public final String xpath_HotelPopUpOverviewTabPanel = "//a[@id='overview']";
	@FindBy(xpath=xpath_HotelPopUpOverviewTabPanel)
	private WebElement pnl_HotelPopUpOverviewTab;

	public final String xpath_HotelPopUpPoliciesTabPanel = "//a[@id='policies']";
	@FindBy(xpath=xpath_HotelPopUpPoliciesTabPanel)
	private WebElement pnl_HotelPopUpPoliciesTab;

	public final String xpath_HotelPopUpAmenitiesTabPanel = "//a[@id='amenities']";
	@FindBy(xpath= xpath_HotelPopUpAmenitiesTabPanel)
	private WebElement pnl_HotelPopUpAmenitiesTab;

	public final String xpath_HotelPopUpDiningTabPanel = "//a[@id='dining']";
	@FindBy(xpath=xpath_HotelPopUpDiningTabPanel)
	private WebElement pnl_HotelPopUpDiningTab;

	//Replaced per Amenities
//	public final String xpath_HotelPopUpActivitiesTabPanel = "//a[@id='activities']";
//	@FindBy(xpath=xpath_HotelPopUpActivitiesTabPanel)
//	private WebElement pnl_HotelPopUpActivitiesTab;

	public final String xpath_HotelPopUpPhotosTabPanel = "//a[@id='photos']";
	@FindBy(xpath=xpath_HotelPopUpPhotosTabPanel)
	private WebElement pnl_HotelPopUpPhotosTab;

	public final String xpath_HotelPopUpMapTabPanel = "//a[@id='map']";
	@FindBy(xpath=xpath_HotelPopUpMapTabPanel)
	private WebElement pnl_HotelPopUpMapTab;

	public final String xpath_HotelSelectedPanel = "//app-selected-ancillary-item";//---------------------------------
	@FindBy(xpath=xpath_HotelSelectedPanel)//once a hotel is selected it will display selected panel
	private WebElement pnl_HotelSelected;

	public final String xpath_HotelSelectedText = "(//app-selected-ancillary-item//p)[1]";
//	public final String xpath_HotelSelectedText = "//app-selected-ancillary-item//div/h5";
	@FindBy(xpath=xpath_HotelSelectedText)
	private WebElement text_HotelSelected;

	public final String xpath_HotelRemoveButton = "//app-selected-ancillary-item//button";//-------------------------------
	@FindBy(xpath=xpath_HotelRemoveButton)
	private WebElement btn_HotelRemove;


	//***********************************************************************************************************************
	//****************************************************Hotel Page*********************************************************
	//***********************************************************************************************************************

	public final String xpath_HotelPageSubHeadingText = "//h1//following-sibling::h4";//-------------------
	@FindBy(xpath=xpath_HotelPageSubHeadingText)
	private WebElement txt_HotelPageSubHeading;

	public final String xpath_AddHotelFirstParagraphText = "//h1//following-sibling::p[1]";//-------------------
	@FindBy(xpath=xpath_AddHotelFirstParagraphText)
	private WebElement txt_AddHotelFirstParagraph;

	public final String xpath_NonRefundableLink = "//h1//following-sibling::p[1]//a"; //-------------------------
	@FindBy(xpath=xpath_NonRefundableLink)
	private WebElement link_NonRefundable;

	public final String xpath_AddHotelSecondParagraphText = "//h1//following-sibling::p[2]";//-----------------
	@FindBy(xpath=xpath_AddHotelSecondParagraphText)
	private WebElement txt_AddHotelSecondParagraph;

	public final String id_FilterByNameTextBox = "name";
	@FindBy (id=id_FilterByNameTextBox)
	private WebElement txtbx_FilterByName;

	public final String id_SortByDropDown = "sortby";
	@FindBy (id=id_SortByDropDown)
	private WebElement drpdwn_SortBy;

	public final String id_DisplayNumberDropDown = "displayNumber";
	@FindBy (id=id_DisplayNumberDropDown)
	private WebElement drpdwn_DisplayNumber;

	public final String xpath_ViewMapButton = "//button[contains(text(),'View Map')]";
	@FindBy (xpath=xpath_ViewMapButton)
	private WebElement btn_ViewMap;

	public final String xpath_FilterByNameLabel = "//label[@for='name']//span";
	@FindBy (xpath=xpath_FilterByNameLabel)
	private WebElement lbl_FilterByName;

	public final String xpath_SortByLabel = "//span[contains(text(),'Sort')]";
	@FindBy (xpath=xpath_SortByLabel)
	private WebElement lbl_SortBy;

	public final String xpath_DisplayNumberLabel = "//label[@for='displayNumber']//span";
	@FindBy (xpath=xpath_DisplayNumberLabel)
	private WebElement lbl_DisplayNumber;

	//***********Hotel List Boxes
	public final String xpath_FeaturedLabel = "//div[contains(text(),'Featured') or contains(text(),'Destacados')]";
	@FindBy(xpath = xpath_FeaturedLabel)
	private WebElement lbl_Featured;

	public final String xpath_HotelCardPanel = "//div[contains(@class,'card-body')]//app-hotel-list-item/div";
	@FindBy (xpath = xpath_HotelCardPanel)
	private List<WebElement> pnl_hotelCard;

	public final String xpath_HotelNamesText = "//app-hotel-list-item/div/div[2]/div[1]";
	@FindBy (xpath=xpath_HotelNamesText)
	private List<WebElement> txt_HotelNames;

	public final String xpath_HotelCardImage = "//app-hotel-list-item/div/div[1]";
	@FindBy (xpath=xpath_HotelCardImage)
	private List<WebElement> img_HotelCardImage;

	public final String xpath_HotelCardSlideImage = "//app-hotel-list-item//div[contains(@class,'carousel-item ng-star-inserted active')]/img";
	@FindBy (xpath=xpath_HotelCardSlideImage)
	private List<WebElement> img_HotelCardSlideImage;

	public final String xpath_HotelAddressText = "//app-hotel-list-item/div/div[2]/div[2]";
	@FindBy (xpath=xpath_HotelAddressText)
	private List<WebElement> txt_HotelAddress;

	public final String xpath_HotelMapButtonLinkButton = "//app-hotel-list-item//a[contains(@title,'map')]";
	@FindBy (xpath=xpath_HotelMapButtonLinkButton)
	private List<WebElement> lnkbtn_HotelMapButton;

	public final String xpath_HotelStarRatingImage = "//app-hotel-list-item//app-rating";
	@FindBy (xpath=xpath_HotelStarRatingImage)
	private List<WebElement> img_HotelStarRating;

	public final String xpath_HotelCardAddress = "(//app-hotel-list-item//span)[7]"; //GOLDFINGER
	@FindBy (xpath=xpath_HotelCardAddress)
	private List<WebElement> txt_HotelCardAddress;

	public final String xpath_MoreInformationLink = "//app-hotel-list-item//a[contains(text(),'More info') or contains(text(),'Más Información')]";
	@FindBy (xpath=xpath_MoreInformationLink)
	private List<WebElement> lnk_MoreInformation;

	public final String xpath_LessInformationLink = "//app-hotel-list-item//a[contains(text(),'Less info') or contains(text(),'Menos Información')]";
	@FindBy(xpath = xpath_LessInformationLink)
	private List<WebElement> lnk_LessInformation;

	public final String xpath_LimitedTimePromotionLink = "//a[contains(text(),'Limited time promotion')]";
	@FindBy(xpath=xpath_LimitedTimePromotionLink)
	private List<WebElement> lnk_LimitedTimePromotion;

	public final String xpath_StartingFromPricePerPersonText = "//app-uplift-pricing/..//strong[contains(text(),'$')]";
	@FindBy (xpath=xpath_StartingFromPricePerPersonText)
	private List<WebElement> txt_StartingFromPricePerPerson;

	public final String xpath_TotalPriceText = "//app-hotel-list-item//p[contains(text(),'Total')]";
	@FindBy (xpath=xpath_TotalPriceText)
	private List<WebElement> txt_TotalPriceText;

	public final String xpath_HotelPageUpliftText = "//app-hotel-list-item//span[@class='uplift-underline']/..";//-------------------------------
	@FindBy (xpath=xpath_HotelPageUpliftText)
	private List<WebElement> txt_HotelPageUplift;

	public final String xpath_HotelPageUpliftLink = "//app-hotel-list-item//span[@class='uplift-underline']";//---------------------------------
	@FindBy (xpath=xpath_HotelPageUpliftLink)
	private List<WebElement> lnk_HotelPageUplift;

	//Select Room in Gold Finger
	public final String xpath_HotelViewButton = "//app-hotel-list-item//button[contains(text(),'View') or contains(text(),'Ver') or contains(text(),'Select Room') or contains(text(),'Seleccionar la habitación')]";
	@FindBy (xpath=xpath_HotelViewButton)
	private List<WebElement> btn_HotelView;

	public final String xpath_HotelPageRemoveButton = "//app-hotel-list-item//button[contains(text(),'Remove') or contains(text(),'Quitar')]";
	@FindBy (xpath=xpath_HotelPageRemoveButton)
	private WebElement btn_HotelPageRemove;

	public final String xpath_HotalPageMapPanel = "//app-interactive-map//agm-map";
	@FindBy(xpath = xpath_HotalPageMapPanel)
	private List<WebElement> pnl_HotalPageMap;

	public final String xpath_HotelPageDetailsPanel = "//div[contains(@class,'tab-pane active')]";
	@FindBy(xpath = xpath_HotelPageDetailsPanel)
	private List<WebElement> pnl_HotelPageDetails;

	//******************************************************************************************
	//*************************Extra XPATH in GoldFinger **************************************
	//*****************************************************************************************
	public final String xpath_HotelCounterText = "//span[contains(@class,'count')]";//goldfinger
	@FindBy (xpath=xpath_HotelCounterText)
	private WebElement txt_HotelCounter;

	public final String xpath_HotelFilterContainerPanel = "//div[contains(@class,'filter-container')]";//goldfinger
	@FindBy (xpath=xpath_HotelFilterContainerPanel)
	private WebElement pnl_HotelFilterContainer;

	public final String xpath_ClearAllFiltersButton = "//button[contains(text(),'Clear All Filters')]";//goldfinger
	@FindBy (xpath=xpath_ClearAllFiltersButton)
	private WebElement btn_ClearAllFilters;

	public final String xpath_ClearPriceFilterButton = "//div[@class='custom-slider row collapse show']//button[contains(text(),'Clear')]";//goldfinger
	@FindBy (xpath=xpath_ClearPriceFilterButton)
	private WebElement btn_ClearPriceFilter;

	public final String xpath_ClearHotelSearchFilterButton = "//div[@class='filter-name']//button[contains(text(),'Clear')]";//goldfinger
	@FindBy (xpath=xpath_ClearHotelSearchFilterButton)
	private WebElement btn_ClearHotelSearchFilter;

	public final String xpath_HotelNameTextBox = "//input[@id='hotelName']"; //goldfinger
	@FindBy (xpath=xpath_HotelNameTextBox)
	private WebElement txtbx_HotelName;

	public final String xpath_HotelSearchLink = "//a//span[contains(text(),' Hotel Search')]";//goldfinger
	@FindBy (xpath=xpath_HotelSearchLink)
	private WebElement lnk_HotelSearch;

	public final String xpath_HotelPriceLink = "//a//span[contains(text(),'Price')]";//goldfinger
	@FindBy (xpath=xpath_HotelPriceLink)
	private WebElement lnk_HotelPrice;

	public final String xpath_HotelPriceRangePerPersonText = "//div[contains(@class,'filter-container')]//div[contains(@class,'current-range')]";//goldfinger
	@FindBy (xpath=xpath_HotelPriceRangePerPersonText)
	private WebElement txt_HotelPriceRangePerPerson;

	public final String xpath_HotelRatingLink = "//a//span[contains(text(),'Hotel Rating')]";//goldfinger
	@FindBy (xpath=xpath_HotelRatingLink)
	private WebElement lnk_HotelRating;

	public final String xpath_5StartsCheckBox = "//label[@for='5.0']";//goldfinger
	@FindBy (xpath=xpath_5StartsCheckBox)
	private WebElement chkbx_5Starts;

	public final String xpath_4StartsCheckBox = "//label[@for='4.0']";//goldfinger
	@FindBy (xpath=xpath_4StartsCheckBox)
	private WebElement chkbx_4Starts;

	public final String xpath_3StartsCheckBox = "//label[@for='3.0']";//goldfinger
	@FindBy (xpath=xpath_3StartsCheckBox)
	private WebElement chkbx_3Starts;

	public final String xpath_RecommendedButton = "//div[@data-toggle='buttons']//label[contains(text(),'Recommended')]"; //goldfinger
	@FindBy (xpath=xpath_RecommendedButton)
	private WebElement btn_Recommended;

	public final String xpath_StarsButton = "//div[@data-toggle='buttons']//label[contains(text(),'Stars')]"; //goldfinger
	@FindBy (xpath=xpath_StarsButton)
	private WebElement btn_Stars;

	public final String xpath_PriceButton = "//div[@data-toggle='buttons']//label[contains(text(),'Price')]"; //goldfinger
	@FindBy (xpath=xpath_PriceButton)
	private WebElement btn_Price;

	public final String xpath_CasinoLink = "//a[@title='Casino']"; //goldfinger
	@FindBy (xpath=xpath_CasinoLink)
	private List<WebElement> lnk_Casino;

	public final String xpath_GymLink = "//a[@title='Gym']"; //goldfinger
	@FindBy (xpath=xpath_GymLink)
	private List<WebElement> lnk_Gym;

	public final String xpath_RestaurantLink = "//a[@title='Restaurant']"; //goldfinger
	@FindBy (xpath=xpath_RestaurantLink)
	private List<WebElement> lnk_Restaurant;

	public final String xpath_WiFiLink = "//a[@title='Wi-Fi']"; //goldfinger
	@FindBy (xpath=xpath_WiFiLink)
	private List<WebElement> lnk_WiFi;

	public final String xpath_ParkingLink = "//a[@title='Parking']"; //goldfinger
	@FindBy (xpath=xpath_ParkingLink)
	private List<WebElement> lnk_Parking;

	public final String xpath_PoolLink = "//a[@title='Pool']"; //goldfinger
	@FindBy (xpath=xpath_PoolLink)
	private List<WebElement> lnk_GoldFingerPool;

	public final String xpath_ShowMoreButton = "//button[contains(text(),'Show More ')]";
	@FindBy (xpath=xpath_ShowMoreButton)
	private WebElement btn_ShowMore;

	//****************************************************************************************
	//*****************************GoldFinger XPATH END**************************************
	//***************************************************************************************

	//***********************************************************************************************************//
	//******************************************Pick Up Hotel (Hotel Page)***************************************//
	//**********************************************************************************************************//

	public final String xpath_HotelWindowRoomCategoryHeaderText = "//app-package-detail-select-modal//h2";
	@FindBy (xpath=xpath_HotelWindowRoomCategoryHeaderText)
	private WebElement txt_HotelWindowRoomCategoryHeader;

	public final String xpath_HotelWindowRoomCategoryParagraphText = "//app-package-detail-select-modal//h2/..";
	@FindBy (xpath=xpath_HotelWindowRoomCategoryParagraphText)
	private WebElement txt_HotelWindowRoomCategoryParagraph;

	public final String xpath_HotelWindowRoomCategoryHotelNameText = "//app-package-detail-select-modal//h3";
	@FindBy (xpath=xpath_HotelWindowRoomCategoryHotelNameText)
	private WebElement txt_HotelWindowRoomCategoryHotelName;

	public final String xpath_HotelWindowRoomCategoryHotelAddressText = "//app-package-detail-select-modal//h3/..//p[1]";
	@FindBy (xpath=xpath_HotelWindowRoomCategoryHotelAddressText)
	private WebElement txt_HotelWindowRoomCategoryHotelAddress;

	public final String xpath_HotelWindowRoomCategoryViewMapText = "//app-package-detail-select-modal//a[contains(text(),'View map') or contains(text(),'Ver mapa')]";
	@FindBy (xpath=xpath_HotelWindowRoomCategoryViewMapText)
	private WebElement txt_HotelWindowRoomCategoryViewMap;

	public final String xpath_HotelWindowRoomCategoryRatingText = "//app-package-detail-select-modal//app-rating";
	@FindBy (xpath=xpath_HotelWindowRoomCategoryRatingText)
	private WebElement txt_HotelWindowRoomCategoryRating;

	public final String xpath_HotelWindowRoomCategoryStaringPriceText = "//app-package-detail-select-modal//span[@class='headline4']";
	@FindBy (xpath=xpath_HotelWindowRoomCategoryStaringPriceText)
	private WebElement txt_HotelWindowRoomCategoryStaringPrice;

	public final String xpath_HotelWindowRoomsPanel = "(//app-hotel-detail-tabset//li)[1]";
	@FindBy (xpath= xpath_HotelWindowRoomsPanel)
	private WebElement pnl_HotelWindowRooms;

	public final String xpath_HotelWindowOverviewPanel = "(//app-hotel-detail-tabset//li)[2]";
	@FindBy (xpath= xpath_HotelWindowOverviewPanel)
	private WebElement pnl_HotelWindowOverview;

	public final String xpath_HotelWindowPoliciesPanel = "(//app-hotel-detail-tabset//li)[3]";
	@FindBy (xpath= xpath_HotelWindowPoliciesPanel)
	private WebElement pnl_HotelWindowPolicies;

	public final String xpath_HotelWindowAmenitiesPanel = "(//app-hotel-detail-tabset//li)[4]";
	@FindBy (xpath= xpath_HotelWindowAmenitiesPanel)
	private WebElement pnl_HotelWindowAmenities;

	public final String xpath_HotelWindowDinningPanel = "(//app-hotel-detail-tabset//li)[5]";
	@FindBy (xpath= xpath_HotelWindowDinningPanel)
	private WebElement pnl_HotelWindowDinning;

	public final String xpath_HotelWindowPhotosPanel = "(//app-hotel-detail-tabset//li)[6]";
	@FindBy (xpath=xpath_HotelWindowPhotosPanel)
	private WebElement pnl_HotelWindowPhotos;

	public final String xpath_HotelWindowMapsPanel = "(//app-hotel-detail-tabset//li)[7]";
	@FindBy (xpath=xpath_HotelWindowMapsPanel)
	private WebElement pnl_HotelWindowMaps;

	public final String xpath_HotelWindowRoomPricesButton = "//app-hotel-detail-tabset//button[@class='btn btn-secondary room-select-button mr-2 ng-star-inserted']";
	@FindBy (xpath=xpath_HotelWindowRoomPricesButton)
	private WebElement btn_HotelWindowRoomPrices;

	public final String xpath_HotelWindowSelectRoomButton = "//app-hotel-detail-tabset//button[contains(text(),'Select room') or contains(text(),'Seleccione la habitación')]";
	@FindBy (xpath=xpath_HotelWindowSelectRoomButton)
	private List<WebElement> btn_HotelWindowSelectRoom;


	//***********Hotel Page Bottom Buttons
	public final String xpath_AllHotelsButton = "//a[contains(text(),'All hotels') or contains(text(),'Todos los hoteles')]";
	@FindBy (xpath=xpath_AllHotelsButton)
	private WebElement btn_AllHotels;

	public final String xpath_ContinueWithPurchaseButton = "//a[contains(text(),'Continue with purchase') or contains(text(),'Continuar con la compra') ]";
	@FindBy (xpath=xpath_ContinueWithPurchaseButton)
	private WebElement btn_ContinueWithPurchase;

	public final String xpath_ContinueWithoutHotelButton = "//button[contains(text(),'Continue Without Hotel')]";
	@FindBy (xpath=xpath_ContinueWithoutHotelButton)
	private WebElement btn_ContinueWithoutHotel;

	public final String xpath_LeftPageNavigationButton = "//span[contains(@class,'page-number')]/..//a[@class='icon-chevron-left']";
	@FindBy (xpath=xpath_LeftPageNavigationButton)
	private WebElement btn_LeftPageNavigation;

	public final String xpath_PageNumberButton = "//span[contains(@class,'page-number')]";
	@FindBy (xpath=xpath_PageNumberButton)
	private List<WebElement> btn_PageNumber;

	public final String xpath_RightPageNavigationButton = "//span[contains(@class,'page-number')]/..//a[@class='icon-chevron-right']";
	@FindBy (xpath=xpath_RightPageNavigationButton)
	private WebElement btn_RightPageNavigation;

	//*******************************************************************
	//*******************Flight+Hotel Page*******************************
	//*******************************************************************
	public final String xpath_SelectedHotelTotalPriceText = "//div[contains(@class,'card card-body hotel-selected')]//Strong";
	@FindBy(xpath= xpath_SelectedHotelTotalPriceText)
	private WebElement txt_SelectedHotelTotalPrice;

	public final String xpath_ContinueButton = "//button[contains(text(),'Continue') or contains(text(),'Continuar')]";
	@FindBy(xpath = xpath_ContinueButton)
	private WebElement btn_Continue;

	//Vacation path only
	public final String xpath_VacationPathContinueButtonButton = "//app-flights-hotels-page//button[contains(text(),'Continue')]";
	@FindBy (xpath= xpath_VacationPathContinueButtonButton)
	private WebElement btn_VacationPathContinueButton;

	public final String xpath_VacationPathSelectRoomNumberButton = "//app-package-detail-select-modal//button[contains(text(),'Select Room')]";
	@FindBy(xpath = xpath_VacationPathSelectRoomNumberButton)
	private List<WebElement> btn_VacationPathSelectRoomNumber;

	public final String xpath_ChooseYourHotelHeaderText ="//div[contains(text(),'Choose Your Hotel')]";
	@FindBy (xpath=xpath_ChooseYourHotelHeaderText)
	private WebElement txt_ChooseYourHotelHeader;

	public final String xpath_SelectYourHotelHeaderText ="//h1[contains(text(),'Select Your Hotel') or contains(text(),'Elija su Hotel')]";
	@FindBy(xpath=xpath_SelectYourHotelHeaderText)
	private WebElement txt_SelectYourHotelHeader;

	public final String xpath_ReviewYourFlightsHeaderText ="//div[contains(text(),'Review Your Flights') or contains(text(),'Revise sus Vuelos ')]";
	@FindBy (xpath=xpath_ReviewYourFlightsHeaderText)
	private WebElement txt_ReviewYourFlightsHeader;

	public final String xpath_HotelImageLeftSlideButton ="//a[contains(@class,'carousel')]//span[contains(@class,'prev-icon')]";
	@FindBy (xpath=xpath_HotelImageLeftSlideButton)
	private List<WebElement> btn_HotelImageLeftSlide;

	public final String xpath_HotelImageRightSlideButton ="//a[contains(@class,'carousel')]//span[contains(@class,'next-icon')]";
	@FindBy (xpath=xpath_HotelImageLeftSlideButton)
	private List<WebElement> btn_HotelImageRightSlide;

	//*******************************************************************
	//**********************Selected Hotel Option Page*******************
	//*******************************************************************
	public final String xpath_SelectedHotelNameText	= "//h3[contains(text(),'Your Hotel')]/../../following-sibling::app-selected-ancillary-item//h5";
	@FindBy(xpath=xpath_SelectedHotelNameText)
	private WebElement txt_SelectedHotelName;

	public final String xpath_SelectedHotelLankmarkText = "//h3[contains(text(),'Your Hotel')]/../../following-sibling::app-selected-ancillary-item/div/div[2]//p[1]";
	@FindBy(xpath=xpath_SelectedHotelLankmarkText)
	private WebElement txt_SelectedHotelLankmark;

	//*******************************************************************************************************************************************
	//******************************************************Start getter Methods of Hotel Page****************************************************
	//*******************************************************************************************************************************************

	//*******************************************************************
	//*******************HOTELS Options Page*****************************
	//*******************************************************************
	public WebElement getHotelCarouselTitleText() {
		return txt_HotelCarouselTitle;
	}

	public List<WebElement> getHotelPanel(){
		return pnl_Hotel;
	}

	public WebElement getHotelCarouselLeftButton() {
		return btn_HotelCarouselLeft;
	}


	public WebElement getHotelCarouselRightButton() {
		return btn_HotelCarouselRight;
	}

	public WebElement getViewAllHotelsButton() {
		return lnkbtn_ViewAllHotels;
	}
	//*******************************************************************
	//********Hotel Card Container Options Page**************************
	//*******************************************************************
	public List<WebElement> getHotelCardNameText() {
		return txt_HotelCardName;
	}

	public List<WebElement> getHotelCardLocationText(){
		return txt_HotelCardLocation;
	}

	public List<WebElement> getHotelCardUpliftPricingText(){
		return txt_HotelCardUpliftPricing;
	}

	public List<WebElement> getHotelCardPriceLink() {
		return lnkbtn_HotelCardPrice;
	}

	public List<WebElement> getHotelTotalPriceText(){
		return txt_HotelCardTotalPrice;
	}

	public List<WebElement> getHotelCardViewLink() {return lnkbtn_HotelCardView;}

	public WebElement getHotelCardRemoveLink() {return lnkbtn_HotelCardRemove;}

	public WebElement getHotelCardEditLink() {return lnkbtn_HotelCardEdit;}

	public WebElement getHotelCardEditPopUpTitleText() {return txt_HotelCardEditPopUpTitle;}

	public WebElement getHotelCardEditPopUpCloseButton() {return btn_HotelCardEditPopUpClose;}

	public WebElement getHotelCardEditPopUpContinueToFlightButton() {return btn_HotelCardEditPopUpContinueToFlight;}

	public List<WebElement> getHotelCarouselStarRatingImage() {
		return img_HotelCardStarRating;
	}
	public List<WebElement> getHotelCardViewRoomButton() {return btn_HotelCardViewRoom;}

	public List<WebElement> getHotelIncludedOptionsImage() {
		return img_HotelCardIncludedOptions;
	}
	//*******************************************************************

	public List<WebElement> getViewAllHotelCardViewLink() {
		return lnkbtn_ViewAllHotelCardView;
	}

	//*******************************************************************
	//*********************Refundability Pop-Up *************************
	//*******************************************************************

	public WebElement getRefundabilityPopUpHeaderText() {
		return txt_RefundabilityPopUpHeader;
	}

	public WebElement getRefundabilityPopUpCloseButton() {
		return btn_RefundabilityPopUpClose;
	}

	public WebElement getRefundabilityPopUpBodyText1() {
		return txt_RefundabilityPopUpBodyText1;
	}

	public WebElement getRefundabilityPopUpBodyText2() {
		return txt_RefundabilityPopUpBodyText2;
	}

	public WebElement getRefundabilityPopUpCloseWindowButton() {
		return btn_RefundabilityPopUpCloseWindow;
	}
	//*******************************************************************
	//************Hotel Page*********************************************
	public WebElement getWhereAreYouStayingText() {
		return txt_WhereAreYouStaying;
	}
	//*******************************************************************
	//************Pick Hotel Pop-Up Options Page************************
	//*******************************************************************

	public WebElement getHotelPopUpHotelNameText() {
		return txt_HotelPopUpHotelName;
	}

	public WebElement getHotelPopUpLandmarkNameText() {
		return txt_HotelPopUpLandmarkName;
	}

	public WebElement getHotelPopUpAddressText() {
		return txt_HotelPopUpAddress;
	}

	public WebElement getHotelPopUpExitIconLink() {
		return lnkbtn_HotelPopUpExitIcon;
	}

	public WebElement getHotelPopUpUpliftPricingText() {
		return txt_HotelPopUpUpliftPricing;
	}

	public WebElement getHotelPopUpSelectRoomsFromButton() {
		return linkbtn_HotelPopUpSelectRoomsFrom;
	}

//	public List<WebElement> getHotelPopUpSelectRoomButton() {
//		return btn_HotelPopUpSelectRoom;
//	}

	public List<WebElement> getHotelPopUpSelectRoomButton() {
		return btn_HotelPopUpSelectRoom;
	}

	public List<WebElement> getHotelSelectRoomButton() {
		return btn_HotelSelectRoom;
	}


	public List<WebElement> getHotelPopUpRoomprice(){
		return txt_HotelPopUpRoomPrice;
	}

	public List<WebElement> getHotelPopUpRoomTypeText(){
		return txt_HotelPopUpRoomType;
	}

//	public List<WebElement> getHotelPopUpRoomTypeNewText(){
//		return txt_HotelPopUpRoomTypeNew;
//	}

	public List<WebElement> getHotelPopUpSelectRoomNumberButton() {
		return btn_HotelPopUpSelectRoomNumber;
	}

	public WebElement getHotelPopUpRoomsTab() {
		return pnl_HotelPopUpRoomsTab;
	}

	public WebElement getHotelPopUpOverviewTab() {
		return pnl_HotelPopUpOverviewTab;
	}

	public WebElement getHotelPopUpPoliciesTab() {
		return pnl_HotelPopUpPoliciesTab;
	}

	public WebElement getHotelPopUpAmenitiesTab() {
		return pnl_HotelPopUpAmenitiesTab;
	}

	public WebElement getHotelPopUpDiningTab() {
		return pnl_HotelPopUpDiningTab;
	}

//	public WebElement getHotelPopUpActivitiesTab() {return pnl_HotelPopUpAmenitiesTab;}

	public WebElement getHotelPopUpPhotosTab() {
		return pnl_HotelPopUpPhotosTab;
	}

	public WebElement getHotelPopUpMapTab() {
		return pnl_HotelPopUpMapTab;
	}

	public WebElement getHotelSelectedPanel() {
		return pnl_HotelSelected;
	}

	public WebElement getHotelSelectedText() {
		return text_HotelSelected;
	}

	public WebElement getHotelRemoveButton() {
		return btn_HotelRemove;
	}

	public WebElement getWhereAreYouStayingHeaderText() {
		return txt_WhereAreYouStaying;
	}

	public WebElement getHotelPageSubHeadingText() {
		return txt_HotelPageSubHeading;
	}

	public WebElement getAddHotelFirstParagraphText() {
		return txt_AddHotelFirstParagraph;
	}

	public WebElement getNonRefundableLink() {
		return link_NonRefundable;
	}

	public WebElement getAddHotelSecondParagraphText() {
		return txt_AddHotelSecondParagraph;
	}

	public WebElement getFilterByNameTextBox() {
		return txtbx_FilterByName;
	}

	public WebElement getSortByDropDown() {
		return drpdwn_SortBy;
	}

	public WebElement getDisplayNumberDropDown() {
		return drpdwn_DisplayNumber;
	}

	public WebElement getViewMapButton() {
		return btn_ViewMap;
	}

	public WebElement getFilterByNamelabel() {
		return lbl_FilterByName;
	}

	public WebElement getSortBylabel() {
		return lbl_SortBy;
	}

	public WebElement getDisplayNumberlabel() {
		return lbl_DisplayNumber;
	}

	public WebElement getFeaturedLabel(){
		return lbl_Featured;
	}

	public List<WebElement> getHotelCard() {
		return pnl_hotelCard;
	}

	public List<WebElement> getHotelNamesText() {
		return txt_HotelNames;
	}

	public List<WebElement> getHotelCardImageImage(){
		return img_HotelCardImage;
	}

	public List<WebElement> getHotelCardSlideImage() {
		return img_HotelCardSlideImage;
	}

	public List<WebElement> getHotelAddressText() {
		return txt_HotelAddress;
	}

	public List<WebElement> getHotelMapButton() {
		return lnkbtn_HotelMapButton;
	}

	public List<WebElement> getHotelStarRatingImage() {
		return img_HotelStarRating;
	}

	public List<WebElement> getHotelCardAddressText(){
		return txt_HotelCardAddress;
	}

	public List<WebElement> getMoreInformationLink() {
		return lnk_MoreInformation;
	}

	public List<WebElement> getLessInformationLink(){
		return lnk_LessInformation;
	}

	public List<WebElement> getLimitedTimePromotionLink(){
		return lnk_LimitedTimePromotion;
	}



	public List<WebElement> getStartingFromPricePerPersonText() {
		return txt_StartingFromPricePerPerson;
	}

	public List<WebElement> getTotalPriceText() { return txt_TotalPriceText; }


	public List<WebElement> getHotelPageUpliftText() {
		return txt_HotelPageUplift;
	}

	public List<WebElement> getHotelPageUpliftLink() {
		return lnk_HotelPageUplift;
	}

	public List<WebElement> getHotelViewButton() {
		return btn_HotelView;
	}

	public WebElement getHotelPageRemoveButton() {
		return btn_HotelPageRemove;
	}

	public List<WebElement> getHotalPageMapPanel(){
		return pnl_HotalPageMap;
	}

	public List<WebElement> getHotelPageDetailsPanel() {
		return pnl_HotelPageDetails;
	}

	//**************************************************************************************************
	//*****************************getter method of Gold Finger XAPTH**********************************
	//*************************************************************************************************
	public WebElement getHotelCounterText(){return txt_HotelCounter;}

	public WebElement geHotelFilterContainerPanel(){return pnl_HotelFilterContainer;}

	public WebElement getClearAllFiltersButton(){
		return btn_ClearAllFilters;
	}

	public WebElement getClearPriceFilterButton(){return btn_ClearPriceFilter;}

	public WebElement getClearHotelSearchFilterButton(){return btn_ClearHotelSearchFilter;}

	public WebElement getHotelNameTextBox(){
		return txtbx_HotelName;
	}

	public WebElement getHotelSearchLink(){
		return lnk_HotelSearch;
	}

	public WebElement getHotelPriceLink(){return lnk_HotelPrice;}

	public WebElement getHotelPriceRangePerPersonText(){return txt_HotelPriceRangePerPerson;}

	public WebElement getHotelRatingLink(){
		return lnk_HotelRating;
	}

	public WebElement get5StartsCheckBox(){
		return chkbx_5Starts;
	}

	public WebElement get4StartsCheckBox(){
		return chkbx_4Starts;
	}

	public WebElement get3StartsCheckBox(){
		return chkbx_3Starts;
	}

	public WebElement getRecommendedButton(){
		return btn_Recommended;
	}

	public WebElement getStarsButton(){
		return btn_Stars;
	}

	public WebElement getPriceButton(){
		return btn_Price;
	}

	public List<WebElement> getCasinoLink(){
		return lnk_Casino;
	}

	public List<WebElement> getGymLink(){
		return lnk_Gym;
	}

	public List<WebElement> getRestaurantLink(){
		return lnk_Restaurant;
	}

	public List<WebElement> getWiFiLink(){
		return lnk_WiFi;
	}

	public List<WebElement> getParkingLink(){
		return lnk_Parking;
	}

	public List<WebElement> getGoldFingerPoolLink(){
		return lnk_GoldFingerPool;
	}

	public WebElement getShowMoreButton(){
		return btn_ShowMore;
	}

	//***********Hotel Page Room Category
	public WebElement getHotelWindowRoomCategoryHeaderText() {
		return txt_HotelWindowRoomCategoryHeader;
	}

	public WebElement getHotelWindowRoomCategoryParagraphText() {
		return txt_HotelWindowRoomCategoryParagraph;
	}

	public WebElement getHotelWindowRoomCategoryHotelNameText() {
		return txt_HotelWindowRoomCategoryHotelName;
	}

	public WebElement getHotelWindowRoomCategoryHotelAddressText() {
		return txt_HotelWindowRoomCategoryHotelAddress;
	}

	public WebElement getHotelWindowRoomCategoryViewMapText() {
		return txt_HotelWindowRoomCategoryViewMap;
	}

	public WebElement getHotelWindowRoomCategoryRatingText() {
		return txt_HotelWindowRoomCategoryRating;
	}

	public WebElement getHotelWindowRoomCategoryStaringPriceText() {
		return txt_HotelWindowRoomCategoryStaringPrice;
	}

	public WebElement getHotelWindowRoomsPanel() {
		return pnl_HotelWindowRooms;
	}

	public WebElement getHotelWindowOverviewPanel() {
		return pnl_HotelWindowOverview;
	}

	public WebElement getHotelWindowPoliciesPanel() {
		return pnl_HotelWindowPolicies;
	}

	public WebElement getHotelWindowAmenitiesPanel() {
		return pnl_HotelWindowAmenities;
	}

	public WebElement getHotelWindowDinningPanel() {
		return pnl_HotelWindowDinning;
	}

	public WebElement getHotelWindowPhotosPanel() {
		return pnl_HotelWindowPhotos;
	}

	public WebElement getHotelWindowMapsPanel() {
		return pnl_HotelWindowMaps;
	}

	public WebElement getHotelWindowRoomPricesButton() {
		return btn_HotelWindowRoomPrices;
	}

	public List<WebElement> getHotelWindowSelectRoomButton() {
		return btn_HotelWindowSelectRoom;
	}

	public WebElement getAllHotelsButton() {
		return btn_AllHotels;
	}

	public WebElement getContinueWithPurchaseButton() {
		return btn_ContinueWithPurchase;
	}

	public WebElement getContinueWithoutHotelButton() {
		return btn_ContinueWithoutHotel;
	}

	public WebElement getLeftPageNavigationButton() {
		return btn_LeftPageNavigation;
	}

	public List<WebElement> getPageNumberButton() {
		return btn_PageNumber;
	}

	public WebElement getRightPageNavigationButton() {
		return btn_RightPageNavigation;
	}

	//*******************************************************************
	//*******************Flight+Hotel Page*******************************
	//*******************************************************************

	public WebElement getSelectedHotelTotalPrice()
	{
		return txt_SelectedHotelTotalPrice;
	}
	public WebElement getContinueButton(){
		return btn_Continue;
	}

	//Vacation path only
	public WebElement getVacationPathContinueButton() {
		return btn_VacationPathContinueButton;
	}

	public List<WebElement> getVacationPathSelectRoomNumber() {
		return btn_VacationPathSelectRoomNumber;
	}

	public WebElement getChooseYourHotelHeaderText() {return txt_ChooseYourHotelHeader;}

	public WebElement getSelectYourHotelHeaderText() {return txt_SelectYourHotelHeader;}

	public WebElement getReviewYourFlightsHeaderText() {return txt_ReviewYourFlightsHeader;}

	public List<WebElement> getHotelImageLeftSlideButton() {
		return btn_HotelImageLeftSlide;
	}

	public List<WebElement> getHotelImageRightSlideButton() {
		return btn_HotelImageRightSlide;
	}
	//*******************************************************************
	//**********************Selected Hotel Option Page*******************
	//*******************************************************************
	public WebElement getSelectedHotelNameText(){
		return txt_SelectedHotelName;
	}

	public WebElement getSelectedHotelLankmarkText(){
		return txt_SelectedHotelLankmark;
	}

}