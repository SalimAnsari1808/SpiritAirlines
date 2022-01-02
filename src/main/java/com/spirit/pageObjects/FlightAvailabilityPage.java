package com.spirit.pageObjects;

import java.util.ArrayList;
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

public class FlightAvailabilityPage {

	//private WebDriver driver;

	public FlightAvailabilityPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*******************************************************************
	//*********************Flight Availability Header********************
	//*******************************************************************
	public final String xpath_FlightAvailabilityPageHeaderText = "//app-flights-page//h1";
	@FindBy(xpath=xpath_FlightAvailabilityPageHeaderText)
	private WebElement txt_FlightAvailabilityPageHeader;

	public final String xpath_FlightAvailabilityPageSubHeaderText = "//p[@class='d-inline']";
	@FindBy(xpath=xpath_FlightAvailabilityPageSubHeaderText)
	private WebElement txt_FlightAvailabilityPageSubHeader;

	public final String xpath_FlightAvailabilityPageLegalVerbiageText = "//span[@class='p-legal']";
	@FindBy(xpath=xpath_FlightAvailabilityPageLegalVerbiageText)
	private WebElement txt_FlightAvailabilityPageLegalVerbiage;

	public final String xpath_CoveredWithFlightFlexVerbiageText = "//div[contains(text(),'You’re covered with Flight Flex') or contains(text(),'¡Está cubierto con Flight Flex!')]";
	@FindBy(xpath = xpath_CoveredWithFlightFlexVerbiageText)
	private WebElement txt_CoveredWithFlightFlexVerbiage;

	public final String xpath_FlightAvailabilityBaggageLink = "//a[contains(text(),'baggage') or contains(text(),'equipaje')]";
	@FindBy(xpath=xpath_FlightAvailabilityBaggageLink)
	private WebElement lnk_FlightAvailabilityBaggage;

	public final String xpath_FlightAvailabilityAdvanceSeatsLink = "//a[contains(text(),'advance seats') or contains(text(),'asientos asignados con anticipación')]";
	@FindBy(xpath=xpath_FlightAvailabilityAdvanceSeatsLink)
	private WebElement lnk_FlightAvailabilityAdvanceSeats;

	public final String xpath_FlightAvailabilityAnyChangesLink = "//a[contains(text(),'any changes') or contains(text(),'cualquier cambio')]";
	@FindBy(xpath=xpath_FlightAvailabilityAnyChangesLink)
	private WebElement lnk_FlightAvailabilityAnyChanges;

	public final String xpath_FlightAvailabilityNonRefundableLink = "//a[contains(text(),'non-refundable') or contains(text(),' no son reembolsables')]";
	@FindBy(xpath=xpath_FlightAvailabilityNonRefundableLink)
	private WebElement lnk_FlightAvailabilityNonRefundable;

	public final String xpath_FlightAvailabilityTaxesAndFeesLink = "//a[contains(text(),'taxes and fees') or contains(text(), 'impuestos y tarifas') or contains(text(), 'impuestos y comisiones')]";
	@FindBy(xpath=xpath_FlightAvailabilityTaxesAndFeesLink)
	private WebElement lnk_FlightAvailabilityTaxesAndFees;

	public final String xpath_DepartingMonthsDatePanel = "(//div[@class='d-flex flex-column'])[1]";
	@FindBy(xpath=xpath_DepartingMonthsDatePanel)
	private WebElement pnl_DepartingMonthsDate;

	public final String xpath_DepartingMonthsDateColumnRowGridView = "(//div[@class='d-flex flex-column'])[1]";
	@FindBy(xpath=xpath_DepartingMonthsDateColumnRowGridView)
	private WebElement grdvew_DepartingMonthsDateColumnRow;

	public final String xpath_DepartingWeeklyColumnGridView = "(//div[@class='d-flex flex-column'])[1]//div[@class='d-flex justify-content-center ng-star-inserted']";
	@FindBy(xpath=xpath_DepartingWeeklyColumnGridView)
	private List<WebElement> grdvew_DepartingWeeklyColumn;

	public final String xpath_DepartingMonthsDateEachTileGridView = "(//div[@class='d-flex flex-column'])[1]//div[@class='d-flex justify-content-center ng-star-inserted']//button[@class='day d-flex flex-column justify-content-between align-items-stretch p-0 flight-select']";
	@FindBy(xpath=xpath_DepartingMonthsDateEachTileGridView)
	private List<WebElement> grdvew_DepartingMonthsDateEachTile;

	public final String xpath_DepartingSelectedDateTileGridView = "(//div[@class='d-flex flex-column'])[1]//button[@class='day d-flex flex-column justify-content-between align-items-stretch p-0 flight-select selected']";
	@FindBy(xpath=xpath_DepartingSelectedDateTileGridView)
	private WebElement grdvew_DepartingSelectedDateTile;

	public final String xpath_DepartingSelectedDateTileWeeklyGridView = "(//button[@class='day d-flex flex-column justify-content-between align-items-stretch p-0 flight-select selected']//div/span[2])[1]";
	@FindBy(xpath=xpath_DepartingSelectedDateTileWeeklyGridView)
	private WebElement grdvew_DepartingSelectedDateTileWeekly;

	public final String xpath_DepartingWeekEachTilesGridView = "//button[@class='day d-flex flex-column justify-content-between align-items-stretch p-0 flight-select']";
	@FindBy(xpath=xpath_DepartingWeekEachTilesGridView)
	private List<WebElement> grdvew_DepartingWeekEachTiles;

	public final String xpath_DepartingWeeklyTilesGridView = "(//div[@class='d-flex justify-content-center'])[3]";
	@FindBy(xpath = xpath_DepartingWeeklyTilesGridView)
	private WebElement grdvew_DepartingWeeklyTiles;

	public final String xpath_DepartingMonthsDateEachTileMoreLessText = "//app-low-fare-day//div//div//div//div//div//span[2]";
	@FindBy(xpath = xpath_DepartingMonthsDateEachTileMoreLessText)
	private List<WebElement> txt_DepartingMonthsDateEachTileMoreLess;

	public final String xpath_DepartingMonthsDateSelectedTilePlaneIcon = "//app-low-fare-day//i[@class='icon-primary-plane align-bottom']";
	@FindBy(xpath = xpath_DepartingMonthsDateSelectedTilePlaneIcon)
	private WebElement icn_DepartingMonthsDateSelectedTilePlane;

	//*******************************************************************
	//************************Flight Rows Header*************************
	//*******************************************************************
	public final String xpath_SortByText = "//p[contains(text(),'Sort by:')]";
	@FindBy(xpath=xpath_SortByText)
	private List<WebElement> txt_SortBy;

	public final String xpath_SortByDropDown = "//select[@aria-label='Sort By']";
	@FindBy(xpath=xpath_SortByDropDown)
	private List<WebElement> drpdn_SortBy;

	public final String xpath_JourneyDateText = "//p[contains(text(),'Sort by:')]/../../div[1]/div";
	@FindBy(xpath=xpath_JourneyDateText)
	private List<WebElement> txt_JourneyDate;

	public final String xpath_NewSearchButton = "//button[contains(text(),'New Search') or contains(text(),'búsqueda nueva')]";
	@FindBy(xpath=xpath_NewSearchButton)
	private WebElement btn_NewSearch;

	public final String xpath_FANewSearchPanel = "//div[@data-qa='new-search-container']/div[2]";
	@FindBy(xpath=xpath_FANewSearchPanel)
	private WebElement pnl_FANewSearch;

	//*******************************************************************
	//************************Departure Flight Calendar******************
	//*******************************************************************

	public final String xpath_NextArrowImage = "//button[contains(@class,'icon-chevron-right')]";
	@FindBy(xpath=xpath_NextArrowImage)
	private List<WebElement> img_NextArrow;

	public final String xpath_DepartingCityPairGreyLineImage = "//span[@class='flight-to']/../following-sibling::hr";
	@FindBy(xpath=xpath_DepartingCityPairGreyLineImage)
	private WebElement img_DepartingCityPairGreyLine;

	public final String xpath_DepartingHeader9FCBannerImage = "//div[contains(text(),'9FC')]";
	@FindBy(xpath=xpath_DepartingHeader9FCBannerImage)
	private WebElement img_DepartingHeader9FCBanner;

	public final String xpath_DepartingHeader9FCBannerText = "//div[contains(text(),'9FC')]//following-sibling::div";
	@FindBy(xpath=xpath_DepartingHeader9FCBannerText)
	private WebElement txt_DepartingHeader9FCBanner;

	public final String xpath_DepCalDateBlocksGridView = "(//div[@class='d-flex justify-content-center']//div[@class='d-flex'])[1]//app-low-fare-day";
	@FindBy(xpath=xpath_DepCalDateBlocksGridView)
	private List<WebElement> grdvew_DepCalDateBlocks;

	public final String xpath_DepartingCarouselWeekViewSwitchLabel = "(//div[@data-qa='journey-results'])[1]//label[contains(text(),'week') or contains(text(),'Semana')]";
	@FindBy(xpath=xpath_DepartingCarouselWeekViewSwitchLabel)
	private WebElement lbl_DepartingCarouselWeekViewSwitch;

	public final String xpath_DepartingCarouselMonthViewSwitchLabel = "(//div[@data-qa='journey-results'])[1]//label[contains(text(),'Month') or contains(text(),'Mes')]";
	@FindBy(xpath=xpath_DepartingCarouselMonthViewSwitchLabel)
	private WebElement lbl_DepartingCarouselMonthViewSwitch;

	public final String xpath_DepartingCarouselDollarsViewSwitchLabel = "(//div[@data-qa='journey-results'])[1]//label[contains(text(),'Dollars') or contains(text(),'Dólares ')]";
	@FindBy(xpath=xpath_DepartingCarouselDollarsViewSwitchLabel)
	private WebElement lbl_DepartingCarouselDollarsViewSwitch;

	public final String xpath_DepartingCarouselMilesViewSwitchLabel = "(//div[@data-qa='journey-results'])[1]//label[contains(text(),'Miles') or contains(text(),'Millas')]";
	@FindBy(xpath=xpath_DepartingCarouselMilesViewSwitchLabel)
	private WebElement lbl_DepartingCarouselMilesViewSwitch;

	public final String xpath_DepartingCarouselLeftButton = "(//div[@data-qa='journey-results'])[1]//button[contains(@class,'left')]";
	@FindBy(xpath=xpath_DepartingCarouselLeftButton)
	private List<WebElement> btn_DepartingCarouselLeft;

	public final String xpath_DepartingCarouselRightButton = "(//div[@data-qa='journey-results'])[1]//button[contains(@class,'right')]";
	@FindBy(xpath=xpath_DepartingCarouselRightButton)
	private List<WebElement> btn_DepartingCarouselRight;

	public final String xpath_DepartingCarouselDateCardRowsPanel = "((//div[@data-qa='journey-results'])[1]//button[contains(@class,'left')])[1]//following-sibling::div/div";
	@FindBy(xpath=xpath_DepartingCarouselDateCardRowsPanel)
	private List<WebElement> pnl_DepartingCarouselDateCardRows;

	public final String xpath_DepartingCarouselDateCardColumnsPanel = "((//div[@data-qa='journey-results'])[1]//button[contains(@class,'left')])[1]//following-sibling::div/div[1]//button";
	@FindBy(xpath=xpath_DepartingCarouselDateCardColumnsPanel)
	private List<WebElement> pnl_DepartingCarouselDateCardColumns;

	public final String xpath_DepartingCarouselDateCardsPanel = "(//div[@data-qa='journey-results'])[1]//app-low-fare-day";
	@FindBy(xpath=xpath_DepartingCarouselDateCardsPanel)
	private List<WebElement> pnl_DepartingCarouselDateCards;

	public final String xpath_DepartingCarouselDateCards9FCIconText = "(//div[@data-qa='journey-results'])[1]//app-low-fare-day//span[contains(text(),'9FC')]";
	@FindBy(xpath=xpath_DepartingCarouselDateCards9FCIconText)
	private List<WebElement> txt_DepartingCarouselDateCards9FCIcon;

	public final String xpath_DepartingCarouselDateCardsMCIconText = "(//div[@data-qa='journey-results'])[1]//app-low-fare-day//span[contains(text(),'MC')]";
	@FindBy(xpath=xpath_DepartingCarouselDateCardsMCIconText)
	private List<WebElement> txt_DepartingCarouselDateCardsMCIcon;

	public final String xpath_DepartingCarouselDateCardsDateText = "(//div[@data-qa='journey-results'])[1]//app-low-fare-day//span[contains(@class,'right')]";
	@FindBy(xpath=xpath_DepartingCarouselDateCardsDateText)
	private List<WebElement> txt_DepartingCarouselDateCardsDate;

	public final String xpath_DepartingCarouselDateCardsLowestFareInDollarsText = "(//div[@data-qa='journey-results'])[1]//app-low-fare-day//div[contains(@class,'font-lead')]//span[contains(text(),'$')]";
	@FindBy(xpath=xpath_DepartingCarouselDateCardsLowestFareInDollarsText)
	private List<WebElement> txt_DepartingCarouselDateCardsLowestFareInDollars;

	public final String xpath_DepartingCarouselDateCardsLowestFareInMilesText = "(//div[@data-qa='journey-results'])[1]//app-low-fare-day//div[contains(@class,'font-lead')]//span[contains(text(),',')]";
	@FindBy(xpath=xpath_DepartingCarouselDateCardsLowestFareInMilesText)
	private List<WebElement> txt_DepartingCarouselDateCardsLowestFareInMiles;

	public final String xpath_DepartingCarouselDateCardsLowestFareMilesRedemptionFeeText = "(//div[@data-qa='journey-results'])[1]//app-low-fare-day//span[contains(@class,'font-lead')]";
	@FindBy(xpath=xpath_DepartingCarouselDateCardsLowestFareMilesRedemptionFeeText)
	private List<WebElement> txt_DepartingCarouselDateCardsLowestFareMilesRedemptionFee;

	public final String xpath_DepartingCarouselDateCardsSavingsText = "(//div[@data-qa='journey-results'])[1]//app-low-fare-day//div[contains(@class,'border-top-thin')]";
	@FindBy(xpath=xpath_DepartingCarouselDateCardsSavingsText)
	private List<WebElement> txt_DepartingCarouselDateCardsSavings;

	//*******************************************************************
	//********************Depart Journey*********************************
	//*******************************************************************

	public final String xpath_DepartingOriginAndDestinationText = "(//div[@data-qa='journey-results'])[1]//div[@class='headline3']";
	@FindBy(xpath=xpath_DepartingOriginAndDestinationText)
	private WebElement txt_DepartingOriginAndDestination;

	//p[@data-qa='journey-depart-time']
	//*******************************************************************
	//*************************Departure Flight Rows**********************
	//*******************************************************************
	public final String xpath_DepFlightRowsGridView = "(//div[@class='d-flex flex-column'])[1]//div[contains(@class,'flight-card-container')]";
	@FindBy(xpath=xpath_DepFlightRowsGridView)
	private List<WebElement> grdvew_DepFlightRows;

	public final String xpath_DepartingFlightDateText = "(//div[@data-qa='journey-results'])[1]//select/../../../div[1]/div";
	@FindBy(xpath=xpath_DepartingFlightDateText)
	private WebElement txt_DepartingFlightDate;

	public final String xpath_DepartingFlightSortByDropDown = "(//div[@data-qa='journey-results'])[1]//select";
	@FindBy(xpath=xpath_DepartingFlightSortByDropDown)
	private WebElement drpdwn_DepartingFlightSortBy;

	//	public final String xpath_DepartingDepartTimeText = "(//div[@data-qa='journey-results'])[1]//p[@data-qa='journey-depart-time']";
	public final String xpath_DepartingDepartTimeText = "//p[contains(@data-qa,'journey-depart-time')]";
	@FindBy(xpath=xpath_DepartingDepartTimeText)
	private List<WebElement> txt_DepartingDepartTime;

	public final String xpath_DepartingDurationText = "(//div[@data-qa='journey-results'])[1]//div[@data-qa='journey-duration']";
	@FindBy(xpath=xpath_DepartingDurationText)
	private List<WebElement> txt_DepartingDuration;

	public final String xpath_DepartingArriveTimeText = "(//div[@data-qa='journey-results'])[1]//p[@data-qa='journey-arrival-time']";
	@FindBy(xpath=xpath_DepartingArriveTimeText)
	private List<WebElement> txt_DepartingArriveTime;

	public final String xpath_DepartingNumebrOfStopsButton = "(//div[@data-qa='journey-results'])[1]//button[@data-qa='journey-stops']";
	@FindBy(xpath=xpath_DepartingNumebrOfStopsButton)
	private List<WebElement> btn_DepartingNumebrOfStops;

	public final String xpath_DepartingAvailableSeatsButton = "(//div[@data-qa='journey-results'])[1]//button[@data-qa='journey-seats']";
	@FindBy(xpath=xpath_DepartingAvailableSeatsButton)
	private List<WebElement> btn_DepartingAvailableSeats;

	public final String xpath_Departing9FCFarePriceText = "(//div[@data-qa='journey-results'])[1]//label[contains(@for,'club')]";
	@FindBy(xpath=xpath_Departing9FCFarePriceText)
	private List<WebElement> txt_Departing9FCFarePrice;

	public final String xpath_DepartingStandardFarePriceText = "(//div[@data-qa='journey-results'])[1]//label[contains(@for,'standard')]";
	@FindBy(xpath=xpath_DepartingStandardFarePriceText)
	private List<WebElement> txt_DepartingStandardFarePrice;

	public final String xpath_DepartingMCHolderFarePriceText = "(//div[@data-qa='journey-results'])[1]//label[contains(@for,'card-holder')]";
	@FindBy(xpath=xpath_DepartingMCHolderFarePriceText)
	private List<WebElement> txt_DepartingMCHolderFarePrice;

	public final String xpath_DepartingJourneyEditButton = "(//app-fare-picker)[1]//button[@data-qa='recap-edit']";;
	@FindBy(xpath=xpath_DepartingJourneyEditButton)
	private List<WebElement> btn_DepartingJourneyEdit;

	//*******************************************************************
	//*****************Selected Departure Flight Block*******************
	//*******************************************************************

	public final String xpath_SelectedFlightBlocksPanel = "//div[@class='card flight-card-space']";
	@FindBy(xpath=xpath_SelectedFlightBlocksPanel)
	private List<WebElement> pnl_SelectedFlightBlocks;

	public final String xpath_SelectedDepatureFlightBlockPanal = "//div[contains(text(),' Your Selected Departure ') or contains(text(),'Su Salida Seleccionada')]/following-sibling::div";
	@FindBy(xpath=xpath_SelectedDepatureFlightBlockPanal)
	private List<WebElement> pnl_SelectedDepatureFlightBlock;

	//public final String xpath_SelectedDepDateText = "(//div[@class='color-gunmetal p-small'])[1]";
	public final String xpath_SelectedDepDateText = "(//div[@class='card flight-card-space' or @class='card'])//div[contains(text(),',')]";
	@FindBy(xpath=xpath_SelectedDepDateText)
	private List<WebElement> txt_SelectedDepDate;

	public final String xpath_SelectedFlightCityText = "//div[@data-qa='recap-city']";
	@FindBy(xpath = xpath_SelectedFlightCityText)
	private List<WebElement> txt_SelectedFlightCity;

	//	public final String xpath_DepartureFlightBlockEditText = "(//div[@class='card flight-card-space'])[1]//button[@data-qa='recap-edit']";
	public final String xpath_DepartureFlightBlockEditText = "//div[contains(text(),'Your Selected') or contains(text(),'Su')]/..//button[@data-qa='recap-edit']";
	@FindBy(xpath=xpath_DepartureFlightBlockEditText)
	private List<WebElement> btn_DepartureFlightBlockEdit;

	public final String xpath_DepartureFlightBlockPriceText = "(//div[@class='card flight-card-space'])[1]//strong";
	@FindBy(xpath=xpath_DepartureFlightBlockPriceText)
	private WebElement txt_DepartureFlightBlockPrice;

	public final String xpath_DepartingFlightBlockDepartTimeText = "//div[@data-qa= 'recap-departure-time']";
	@FindBy(xpath = xpath_DepartingFlightBlockDepartTimeText)
	private List<WebElement> txt_DepartingFlightBlockDepartTime;

	public final String xpath_DepartingFlightBlockArivalTimeText = "//div[@data-qa= 'recap-arrival-time']";
	@FindBy(xpath = xpath_DepartingFlightBlockArivalTimeText)
	private List<WebElement> txt_DepartingFlightBlockArivalTime;

	public final String xpath_SeletedDepatingFlightNatureButton = "(//div[@class='card flight-card-space'])[1]//button[@data-qa='recap-stops']";
	@FindBy(xpath = xpath_SeletedDepatingFlightNatureButton)
	private List<WebElement> btn_SeletedDepatingFlightNature;

	public final String xpath_SelectedDepatingFlightViewSeatMapButton = "(//button[@data-qa='recap-seats' and @aria-label='Recap Seats'])[1]";
	@FindBy(xpath = xpath_SelectedDepatingFlightViewSeatMapButton)
	private WebElement btn_SelectedDepatingFlightViewSeatMap;

	//*******************************************************************
	//*****************Return Journey************************************
	//*******************************************************************
	public final String xpath_ReturningOriginAndDestinationText = "(//div[@data-qa='journey-results'])[2]//div[@class='headline3']";
	@FindBy(xpath=xpath_ReturningOriginAndDestinationText)
	private WebElement txt_ReturningOriginAndDestination;

	//*******************************************************************
	//*************************Arrival Flight Calendar*******************
	//*******************************************************************
	public final String xpath_ArrCalDateBlocksGridView = "(//div[@class='d-flex justify-content-center']//div[@class='d-flex'])[2]//app-low-fare-day/button";
	@FindBy(xpath=xpath_ArrCalDateBlocksGridView)
	private List<WebElement> grdvew_ArrCalDateBlocks;

	public final String xpath_ReturningCarouselWeekViewSwitchLabel = "(//div[@data-qa='journey-results'])[2]//label[contains(text(),'week') or contains(text(),'Semana')]";
	@FindBy(xpath=xpath_ReturningCarouselWeekViewSwitchLabel)
	private WebElement lbl_ReturningCarouselWeekViewSwitch;

	public final String xpath_ReturningCarouselMonthViewSwitchLabel = "(//div[@data-qa='journey-results'])[2]//label[contains(text(),'Month') or contains(text(),'Mes')]";
	@FindBy(xpath=xpath_ReturningCarouselMonthViewSwitchLabel)
	private WebElement lbl_ReturningCarouselMonthViewSwitch;

	public final String xpath_ReturningCarouselDollarsViewSwitchLabel = "(//div[@data-qa='journey-results'])[2]//label[contains(text(),'Dollars') or contains(text(),'Dólares ')]";
	@FindBy(xpath=xpath_ReturningCarouselDollarsViewSwitchLabel)
	private WebElement lbl_ReturningCarouselDollarsViewSwitch;

	public final String xpath_ReturningCarouselMilesViewSwitchLabel = "(//div[@data-qa='journey-results'])[2]//label[contains(text(),'Miles') or contains(text(),'Millas')]";
	@FindBy(xpath=xpath_ReturningCarouselMilesViewSwitchLabel)
	private WebElement lbl_ReturningCarouselMilesViewSwitch;

	public final String xpath_ReturningCarouselLeftButton = "(//div[@data-qa='journey-results'])[2]//button[contains(@class,'left')]";
	@FindBy(xpath=xpath_ReturningCarouselLeftButton)
	private List<WebElement> btn_ReturningCarouselLeft;

	public final String xpath_ReturningCarouselRightButton = "(//div[@data-qa='journey-results'])[2]//button[contains(@class,'right')]";
	@FindBy(xpath=xpath_ReturningCarouselRightButton)
	private List<WebElement> btn_ReturningCarouselRight;

	public final String xpath_ReturningCarouselDateCardsPanel = "(//div[@data-qa='journey-results'])[2]//app-low-fare-day";
	@FindBy(xpath=xpath_ReturningCarouselDateCardsPanel)
	private List<WebElement> pnl_ReturningCarouselDateCards;

	public final String xpath_ReturningCarouselDateCards9FCIconText = "(//div[@data-qa='journey-results'])[2]//app-low-fare-day//span[contains(text(),'9FC')]";
	@FindBy(xpath=xpath_ReturningCarouselDateCards9FCIconText)
	private List<WebElement> txt_ReturningCarouselDateCards9FCIcon;

	public final String xpath_ReturningCarouselDateCardsMCIconText = "(//div[@data-qa='journey-results'])[2]//app-low-fare-day//span[contains(text(),'MC')]";
	@FindBy(xpath=xpath_ReturningCarouselDateCardsMCIconText)
	private List<WebElement> txt_ReturningCarouselDateCardsMCIcon;

	public final String xpath_ReturningCarouselDateCardsDateText = "(//div[@data-qa='journey-results'])[2]//app-low-fare-day//span[contains(@class,'right')]";
	@FindBy(xpath=xpath_ReturningCarouselDateCardsDateText)
	private List<WebElement> txt_ReturningCarouselDateCardsDate;

	public final String xpath_ReturningCarouselDateCardsLowestFareInDollarsText = "(//div[@data-qa='journey-results'])[2]//app-low-fare-day//div[contains(@class,'font-lead')]//span[contains(text(),'$')]";
	@FindBy(xpath=xpath_ReturningCarouselDateCardsLowestFareInDollarsText)
	private List<WebElement> txt_ReturningCarouselDateCardsLowestFareInDollars;

	public final String xpath_ReturningCarouselDateCardsLowestFareInMilesText = "(//div[@data-qa='journey-results'])[2]//app-low-fare-day//div[contains(@class,'font-lead')]//span[contains(text(),',')]";
	@FindBy(xpath=xpath_ReturningCarouselDateCardsLowestFareInMilesText)
	private List<WebElement> txt_ReturningCarouselDateCardsLowestFareInMiles;

	public final String xpath_ReturningCarouselDateCardsLowestFareMilesRedemptionFeeText = "(//div[@data-qa='journey-results'])[2]//app-low-fare-day//span[contains(@class,'font-lead')]";
	@FindBy(xpath=xpath_ReturningCarouselDateCardsLowestFareMilesRedemptionFeeText)
	private List<WebElement> txt_ReturningCarouselDateCardsLowestFareMilesRedemptionFee;

	public final String xpath_ReturningCarouselDateCardsSavingsText = "(//div[@data-qa='journey-results'])[2]//app-low-fare-day//div[contains(@class,'border-top-thin')]";
	@FindBy(xpath=xpath_ReturningCarouselDateCardsSavingsText)
	private List<WebElement> txt_ReturningCarouselDateCardsSavings;

	//*******************************************************************
	//**************************Arrival Flight Rows**********************
	//*******************************************************************
	public final String xpath_ArrFlightRowsGridView = "(//div[@class='d-flex flex-column'])[2]//div[contains(@class,'flight-card-container')]";
	@FindBy(xpath=xpath_ArrFlightRowsGridView)
	private List<WebElement> grdvew_ArrFlightRows;

	public final String xpath_ReturningFlightDateText = "(//div[@data-qa='journey-results'])[2]//div[contains(text(),'2019') or contains(text(),'2020')]";
	@FindBy(xpath=xpath_ReturningFlightDateText)
	private WebElement txt_ReturningFlightDate;

	public final String xpath_ReturningFlightSortByDropDown = "(//div[@data-qa='journey-results'])[2]//select";
	@FindBy(xpath=xpath_ReturningFlightSortByDropDown)
	private WebElement drpdwn_ReturningFlightSortBy;

	public final String xpath_ReturningDepartTimeText = "(//div[@data-qa='journey-results'])[2]//p[@data-qa='journey-depart-time']";
	@FindBy(xpath=xpath_ReturningDepartTimeText)
	private List<WebElement> txt_ReturningDepartTime;

	public final String xpath_ReturningDurationText = "(//div[@data-qa='journey-results'])[2]//div[@data-qa='journey-duration']";
	@FindBy(xpath=xpath_ReturningDurationText)
	private List<WebElement> txt_ReturningDuration;

	public final String xpath_ReturningArriveTimeText = "(//div[@data-qa='journey-results'])[2]//p[@data-qa='journey-arrival-time']";
	@FindBy(xpath=xpath_ReturningArriveTimeText)
	private List<WebElement> txt_ReturningArriveTime;

	public final String xpath_ReturningNumebrOfStopsButton = "(//div[@data-qa='journey-results'])[2]//p[@data-qa='journey-stops']";
	@FindBy(xpath=xpath_ReturningNumebrOfStopsButton)
	private List<WebElement> btn_ReturningNumebrOfStops;

	public final String xpath_ReturningAvailableSeatsButton = "(//div[@data-qa='journey-results'])[2]//p[@data-qa='journey-seats']";
	@FindBy(xpath=xpath_ReturningAvailableSeatsButton)
	private List<WebElement> btn_ReturningAvailableSeats;

	public final String xpath_Returning9FCFarePriceText = "(//div[@data-qa='journey-results'])[2]//label[contains(@for,'club')]";
	@FindBy(xpath=xpath_Returning9FCFarePriceText)
	private List<WebElement> txt_Returning9FCFarePrice;

	public final String xpath_ReturningStandardFarePriceText = "(//div[@data-qa='journey-results'])[2]//label[contains(@for,'standard')]";
	@FindBy(xpath=xpath_ReturningStandardFarePriceText)
	private List<WebElement> txt_ReturningStandardFarePrice;

	public final String xpath_ReturningMCHolderFarePriceText = "(//div[@data-qa='journey-results'])[2]//label[contains(@for,'card-holder')]";
	@FindBy(xpath=xpath_ReturningMCHolderFarePriceText)
	private List<WebElement> txt_ReturningMCHolderFarePrice;

	public final String xpath_ReturningJourneyEditButton = "(//app-fare-picker)[2]//button[@data-qa='recap-edit']";
	@FindBy(xpath=xpath_ReturningJourneyEditButton)
	private List<WebElement> btn_ReturningJourneyEdit;

	//*******************************************************************
	//*****************Selected Arrival Flight Block*********************
	//*******************************************************************
	public final String xpath_SelectedFlightBlockPanel = "//button[@data-qa='recap-seats']/../../..";
	@FindBy(xpath=xpath_SelectedFlightBlockPanel)
	private List<WebElement> pnl_SelectedFlightBlock;

	public final String xpath_SelectedArrivalFlightBlockPanal = "//div[contains(text(),' Your Selected Return ') or contains(text(),'Su Regreso Seleccionado')]/following-sibling::div";
	@FindBy(xpath=xpath_SelectedArrivalFlightBlockPanal)
	private List<WebElement> pnl_SelectedArrivalFlightBlock;

	public final String xpath_ArrivalFlightBlockEditButton = "(//div[@class='card flight-card-space'])[2]//button[@data-qa='recap-edit']";
	@FindBy(xpath=xpath_ArrivalFlightBlockEditButton)
	private List<WebElement> btn_ArrivalFlightBlockEdit;

	//public final String xpath_SelectedRetDateText = "(//div[@class='color-gunmetal p-small'])[2]";
	public final String xpath_SelectedRetDateText = "(//div[@class='card flight-card-space' or @class='card'])//div[contains(text(),',')]";
	@FindBy(xpath=xpath_SelectedRetDateText)
	private List<WebElement> txt_SelectedRetDate;

	public final String xpath_ArrivalFlightBlockPriceText = "(//div[@class='card flight-card-space'])[2]//strong";
	@FindBy(xpath=xpath_ArrivalFlightBlockPriceText)
	private WebElement txt_ArrivalFlightBlockPrice;

	public final String xpath_SelectedReturningFlightNatureButton = "(//div[@class='card flight-card-space'])[2]//button[@data-qa='recap-stops']";
	@FindBy(xpath = xpath_SelectedReturningFlightNatureButton)
	private List<WebElement> btn_SelectedReturningFlightNature;

	//*******************************************************************
	//*********************Flight Information Popup**********************
	//*******************************************************************
	//popup header
	public final String xpath_StopsPopUpHeaderText = "//h2[@class='modal-title']";
	@FindBy(xpath=xpath_StopsPopUpHeaderText)
	private WebElement txt_StopsPopUpHeader;

	//popup close button
	public final String xpath_StopsPopUpCloseButton = "//div[@class='modal-body']//button";
	@FindBy(xpath=xpath_StopsPopUpCloseButton)
	private WebElement btn_StopsPopUpClose;

	//Flight Number
	public final String xpath_StopsPopUpFlightsNumberText = "//div[@class='modal-body']//p[contains(text(),'Flight NK') or contains(text(),'Vuelo NK')]";
	@FindBy(xpath=xpath_StopsPopUpFlightsNumberText)
	private List<WebElement> txt_StopsPopUpFlightsNumber;

	//Aircraft Type
	public final String xpath_StopsPopUpAircraftTypeText = "//div[@data-qa='aircraft-type']//p";
	@FindBy(xpath=xpath_StopsPopUpAircraftTypeText)
	private List<WebElement> txt_StopsPopUpAircraftType;

	//Departure label
	public final String xpath_StopsPopUpDepartureLabel = "//div[@class='modal-body']//div[contains(@class,'flight-leg')]//strong[text()='Departure:' or text()='Salida:' ]";
	@FindBy(xpath=xpath_StopsPopUpDepartureLabel)
	private List<WebElement> lbl_StopsPopUpDeparture;

	//Departure Airports
	public final String xpath_StopsPopUpDepartureAirportsText = "//div[@class='modal-body']//div[contains(@class,'flight-leg')]//strong[text()='Departure:' or text()='Salida:' ]/../following-sibling::p";
	@FindBy(xpath=xpath_StopsPopUpDepartureAirportsText)
	private List<WebElement> txt_StopsPopUpDepartureAirports;

	//Departure Time
	public final String xpath_StopsPopUpDepartureTimeText = "//div[@data-qa='depart-time']//strong";
	@FindBy(xpath=xpath_StopsPopUpDepartureTimeText)
	private List<WebElement> txt_StopsPopUpDepartureTime;

	//Arrival label
	public final String xpath_StopsPopUpArrivalLabel = "//div[@class='modal-body']//div[contains(@class,'flight-leg')]//strong[text()='Arrival:' or text()='Llegada:' ]";
	@FindBy(xpath=xpath_StopsPopUpArrivalLabel)
	private List<WebElement> lbl_StopsPopUpArrival;

	//Arrival Airports
	public final String xpath_StopsPopUpArrivalAirportsText = "//div[@class='modal-body']//div[contains(@class,'flight-leg')]//strong[text()='Arrival:' or text()='Llegada:' ]/../following-sibling::p";
	@FindBy(xpath=xpath_StopsPopUpArrivalAirportsText)
	private List<WebElement> txt_StopsPopUpArrivalAirports;

	//Arrival Time
	public final String xpath_StopsPopUpArrivalTimeText = "//div[@data-qa='arrive-time']//strong";
	@FindBy(xpath=xpath_StopsPopUpArrivalTimeText)
	private List<WebElement> txt_StopsPopUpArrivalTime;

	//flight duration label
	public final String xpath_StopsPopUpFlightDurationLabel = "//div[@data-qa='arrive-time']/following-sibling::div/p[1]/strong";
	@FindBy(xpath=xpath_StopsPopUpFlightDurationLabel)
	private List<WebElement> lbl_StopsPopUpFlightDuration;

	//Flight Duration
	public final String xpath_StopsPopUpFlightDurationTimeText = "//div[@data-qa='arrive-time']/following-sibling::div/p[2]";
	@FindBy(xpath=xpath_StopsPopUpFlightDurationTimeText)
	private List<WebElement> txt_StopsPopUpFlightDurationTime;

	//ontime label
	public final String xpath_StopsPopUpOnTimeLabel = "//strong[contains(text(),'On-Time:') or contains(text(),'A Tiempo:')]";
	@FindBy(xpath=xpath_StopsPopUpOnTimeLabel)
	private List<WebElement> lbl_StopsPopUpOnTime;

	//Ontime value
	public final String xpath_StopsPopUpOnTimeText = "//strong[contains(text(),'On-Time:') or contains(text(),'A Tiempo:')]/../../p[2]";
	@FindBy(xpath=xpath_StopsPopUpOnTimeText)
	private List<WebElement> txt_StopsPopUpOnTime;

	//Late label
	public final String xpath_StopsPopUpLateLabel = "//strong[contains(text(),'Late:') or contains(text(),'Retrasado:')]";
	@FindBy(xpath=xpath_StopsPopUpLateLabel)
	private List<WebElement> lbl_StopsPopUpLate;

	//Late Value
	public final String xpath_StopsPopUpLateTimeText = "//strong[contains(text(),'Late:') or contains(text(),'Retrasado:')]/../../p[2]";
	@FindBy(xpath=xpath_StopsPopUpLateTimeText)
	private List<WebElement> txt_StopsPopUpLateTime;

	//layover
	public final String xpath_StopsPopUpLayoverTimeText = "//i[contains(@class,'clock')]//..";
	@FindBy(xpath=xpath_StopsPopUpLayoverTimeText)
	private List<WebElement> txt_StopsPopUpLayoverTime;

	//*******************************************************************
	//***********************Seat Availability Popup ********************
	//*******************************************************************
	public final String xpath_SeatMapPerLegLabel = "//div[@class='modal-body']//a[contains(@class,'nav-link')]";
	@FindBy(xpath=xpath_SeatMapPerLegLabel)
	private List<WebElement> lbl_SeatMapPerLeg;

	public final String xpath_BigFrontSeatsGridView = "//div[@class='modal-body']//div[contains(@class,'big-front')]";
	@FindBy(xpath=xpath_BigFrontSeatsGridView)
	private List<WebElement> grdvew_BigFrontSeats;

	public final String xpath_PremiumSeatsGridView = "//div[@class='modal-body']//div[contains(@class,'premium')]";
	@FindBy(xpath=xpath_BigFrontSeatsGridView)
	private List<WebElement> grdvew_PremiumSeats;

	public final String xpath_AircraftTypeText = "//div[@class='modal-body']//strong[contains(text(),'Airbus')]";
	@FindBy(xpath=xpath_AircraftTypeText)
	private WebElement txt_AircraftType;

	public final String xpath_SeatsPopupCloseButton = "//div[@class='modal-header']//i";
	@FindBy(xpath=xpath_SeatsPopupCloseButton)
	private WebElement btn_SeatsPopupClose;

	public final String xpath_AllSeatsGridView = "//app-unit//div[@ng-reflect-klass='seat-row-seat']";
	@FindBy(xpath = xpath_AllSeatsGridView)
	private List<WebElement> grdvew_AllSeats;

	//*******************************************************************
	//***********************Flight Total Amount Block*******************
	//*******************************************************************
	public final String xpath_FlightTotalAmountText = "//app-total-breakdown//div[@data-qa='total-cost']";
	@FindBy(xpath=xpath_FlightTotalAmountText)
	private WebElement txt_FlightTotalAmount;

	public final String xpath_FlightTotalBreakDownCityPairText = "//app-breakdown-section/div/div[1]/p";
	@FindBy(xpath = xpath_FlightTotalBreakDownCityPairText)
	private WebElement txt_FlightTotalBreakDownCityPair;

	public final String xpath_FlightTotalBreakDownCityPairPriceText = "//app-breakdown-section/div/div[3]/p";
	@FindBy(xpath = xpath_FlightTotalBreakDownCityPairPriceText)
	private WebElement txt_FlightTotalBreakDownCityPairPrice;

	public final String xpath_FlightTotalTextText = "//p[contains(text(),'flights total') or text()='Total de Vuelos']";
	@FindBy(xpath = xpath_FlightTotalTextText)
	private WebElement txt_FlightTotalText;

	//*******************************************************************
	//************************Spirit Member Container********************
	//*******************************************************************
	public final String xpath_FareContainerPanel = "//app-pricing-tiles//div[contains(@class,'container') and not(contains(@class,'legal'))]";
	@FindBy(xpath=xpath_FareContainerPanel)
	private List<WebElement> pnl_FareContainer;

	public final String xpath_StandardFareButton = "//div[contains(@class,'standard-pricing-container')]//button";
	@FindBy(xpath=xpath_StandardFareButton)
	private WebElement btn_StandardFare;

	public final String xpath_MemberTypeText = "(//app-pricing-tiles//div[contains(@class,'container') and not(contains(@class,'legal'))]//p)[1]";
	@FindBy(xpath = xpath_MemberTypeText)
	private WebElement txt_MemberType;

	public final String xpath_StandardTypeText = "(//app-pricing-tiles//div[contains(@class,'container') and not(contains(@class,'legal'))]//p)[5]";
	@FindBy(xpath = xpath_StandardTypeText)
	private WebElement txt_StandardType;

	//@FindBy(xpath="//div[contains(@class,'standard-pricing-container')]//p[contains(text(),'$')]")
	public final String xpath_StandardFarePriceText = "//div[contains(@class,'standard-pricing-container')]//p[contains(text(),'$') or contains(text(),'miles')]";
	@FindBy(xpath=xpath_StandardFarePriceText)
	private WebElement txt_StandardFarePrice;

	public final String xpath_MemberFareButton = "//div[contains(@class,'fare-club-container')]//button";
	@FindBy(xpath=xpath_MemberFareButton)
	private WebElement btn_MemberFare;

	public final String xpath_MemberFarePriceText = "(//div[contains(@class,'fare-club-container')]//p[contains(text(),'$')])[2]";
	@FindBy(xpath=xpath_MemberFarePriceText)
	private WebElement txt_MemberFarePrice;

	public final String xpath_MemberFareSaveUpToPriceText = "//div[@class='price-flag mobile']/p";
	@FindBy(xpath=xpath_MemberFareSaveUpToPriceText)
	private WebElement txt_MemberFareSaveUpToPrice;


	//*******************************************************************
	//*******************Bundle Fare Upgrade & Save Popup****************
	//*******************************************************************
	public final String xpath_BundleFareHeaderText = "//app-branded-modal//h2[contains (text(),'Upgrade & Save') or contains(text(),'Mejora y Ahorra')]";
	@FindBy(xpath=xpath_BundleFareHeaderText)
	private WebElement txt_BundleFareHeader;

	public final String xpath_BundleFareCloseButton = "//app-branded-modal//button[@class='close']";
	@FindBy(xpath=xpath_BundleFareCloseButton)
	private WebElement btn_BundleFareClose;

	public final String xpath_BundleFareTilesPanel = "//div[@class='row thrills-upsell-modal']//div[contains(@class,'-upsell')]";
	@FindBy(xpath=xpath_BundleFareTilesPanel)
	private List<WebElement> pnl_BundleFareTiles;

	public final String xpath_BookItTitleText = "//div[contains(@class, 'smallest')]//p[contains(text(),'Book It')]";
	@FindBy(xpath=xpath_BookItTitleText)
	private WebElement txt_BookItTitle;

	public final String xpath_BookItTileBenefitsText = "//div[contains(@class, 'smallest')]//ul[@class='benefit-list']/li";
	@FindBy(xpath=xpath_BookItTileBenefitsText)
	private List<WebElement> txt_BookItTileBenefits;

	public final String xpath_BookItTileDescriptionText = "//div[contains(@class,'smallest')]//strong";
	@FindBy(xpath=xpath_BookItTileDescriptionText)
	private WebElement txt_BookItTileDescription;

	public final String xpath_BookItTileIncludedInfoText = "//div[contains(@class,'smallest')]//div[contains(@class, 'saving-price')]//p";
	@FindBy(xpath=xpath_BookItTileIncludedInfoText)
	private WebElement txt_BookItTileIncludedInfo;

	public final String xpath_BookItPriceText = "//p[contains(text(),'Book It')]/../../following-sibling::div[1]//p";
	@FindBy(xpath=xpath_BookItPriceText)
	private WebElement txt_BookItPrice;

	public final String xpath_BookItButton = "//button[contains(text(),'Book It')]";
	@FindBy(xpath=xpath_BookItButton)
	private List<WebElement> btn_BookIt;

	public final String xpath_BoosItTitleText = "//div[contains(@class, 'regular')]//p[contains(text(), 'Boost It')]";
	@FindBy(xpath=xpath_BoosItTitleText)
	private WebElement txt_BoosItTitle;

	public final String xpath_BoosItFlagText = "//div[contains(@class, 'regular')]//div[@class='black-flag-container']/p";
	@FindBy(xpath=xpath_BoosItFlagText)
	private WebElement txt_BoosItFlag;

	public final String xpath_BoosItTileBenefitsText = "//div[contains(@class, 'regular')]//ul[@class='benefit-list']/li";
	@FindBy(xpath=xpath_BoosItTileBenefitsText)
	private List<WebElement> txt_BoosItTileBenefits;

	public final String xpath_BoosItMoreInfoText = "(//div[contains(@class, 'regular')]//strong)[1]";
	@FindBy(xpath=xpath_BoosItMoreInfoText)
	private WebElement txt_BoosItMoreInfo;

	public final String xpath_BoostItSavingPriceText = "//div[contains(@class,'regular')]//h3";
	@FindBy(xpath=xpath_BoostItSavingPriceText)
	private WebElement txt_BoostItSavingPrice;

	public final String xpath_BoostItSavingPlusText = "(//div[contains(@class,'regular')]//p)[4]";
	@FindBy(xpath=xpath_BoostItSavingPlusText)
	private WebElement txt_BoostItSavingPlus;

	public final String xpath_BoostItSavingPlusPriceText = "//div[contains(@class,'regular')]//p/span";
	@FindBy(xpath=xpath_BoostItSavingPlusPriceText)
	private WebElement txt_BoostItSavingPlusPrice;

	public final String xpath_BoostItSaveUpToPriceText = "//p[contains(text(),'Boost It')]/../following-sibling::div//p";
	@FindBy(xpath=xpath_BoostItSaveUpToPriceText)
	private WebElement txt_BoostItSaveUpToPrice;

	public final String xpath_BoostItPriceText = "//p[contains(text(),'Boost It')]/../../following-sibling::div[2]//p[contains(text(),'$')]";
	@FindBy(xpath=xpath_BoostItPriceText)
	private WebElement txt_BoostItPrice;

	public final String xpath_BoostItButton = "//button[contains(text(),'Boost It')]";
	@FindBy(xpath=xpath_BoostItButton)
	private List<WebElement> btn_BoostIt;

	public final String xpath_BundleTileTitleText = "//div[contains(@class, 'largest')]//div[@class='row']//p";
	@FindBy(xpath=xpath_BundleTileTitleText)
	private WebElement txt_BundleTileTitle;

	public final String xpath_BundleFlagText = "//div[contains(@class, 'largest')]//div[@class='black-flag-container']//p";
	@FindBy(xpath=xpath_BundleFlagText)
	private WebElement txt_BundleFlag;

	public final String xpath_BundleTileBenefitsListBox = "//div[contains(@class, 'largest')]//ul[@class='benefit-list']/li";
	@FindBy(xpath=xpath_BundleTileBenefitsListBox)
	private List<WebElement> lstbx_BundleTileBenefits;

	public final String xpath_BundleSavingPriceText = "//div[contains(@class, 'largest')]//h3";
	@FindBy(xpath=xpath_BundleSavingPriceText)
	private WebElement txt_BundleSavingPrice;

	public final String xpath_BundleItSaveUpToPriceText = "//p[contains(text(),'Bundle It')]/../following-sibling::div//p";
	@FindBy(xpath=xpath_BundleItSaveUpToPriceText)
	private WebElement txt_BundleItSaveUpToPrice;

	public final String xpath_BundleItPriceText = "//p[contains(text(),'Bundle It')]/../../following-sibling::div[2]//p[contains(text(),'$')]";
	@FindBy(xpath=xpath_BundleItPriceText)
	private WebElement txt_BundleItPrice;

	public final String xpath_BundleItButton = "//button[contains(text(),'Bundle It')]";
	@FindBy(xpath=xpath_BundleItButton)
	private List<WebElement> btn_BundleIt;

	//*******************************************************************
	//**********************Early Departure Popup************************
	//*******************************************************************
	public final String xpath_EarlyDepartureHeaderText = "//div[@class='modal-header']/h2[contains(text(),'Early Departure') or contains(text(),'Salida de Madrugada')]";
	@FindBy(xpath=xpath_EarlyDepartureHeaderText)
	private WebElement txt_EarlyDepartureHeader;

	public final String xpath_EarlyDepartureCloseButton = "//button[@class='close']";
	@FindBy(xpath=xpath_EarlyDepartureCloseButton)
	private WebElement btn_EarlyDepartureClose;

	public final String xpath_EarlyDepartureContinueButton = "//div[@class='modal-body']//button";
	@FindBy(xpath=xpath_EarlyDepartureContinueButton)
	private WebElement btn_EarlyDepartureContinue;

	//*******************************************************************
	//*****************Insufficient Miles********************************
	//*******************************************************************

	public final String xpath_InsufficientMilesPopUpBuyMoreMilesButton = "//button[contains(text(),'Buy More Miles* ') or contains(text(),'Comprar más millas*')]";
	@FindBy(xpath=xpath_InsufficientMilesPopUpBuyMoreMilesButton)
	private WebElement btn_InsufficientMilesPopUpBuyMoreMiles;

	public final String xpath_InsufficientMilesPopUpChangeFlightButton = "//button[contains(text(),'Change Flights') or contains(text(),'Cambiar vuelos')]";
	@FindBy(xpath=xpath_InsufficientMilesPopUpChangeFlightButton)
	private WebElement btn_InsufficientMilesPopUpChangeFlight;

	public final String xpath_InsufficientMilesPopUpViewInDollarsButton = "//button[contains(text(),'View In USD') or contains(text(),'Ver en USD')]";
	@FindBy(xpath=xpath_InsufficientMilesPopUpViewInDollarsButton)
	private WebElement btn_InsufficientMilesPopUpViewInDollars;

	//*******************************************************************
	//***************Spirit MasterCard***********************************
	//*******************************************************************
	public final String xpath_FSMCPopUpApplyNowButton = "//button[contains(text(),'apply now') or contains(text(),'Solicitar ahora')]";
	@FindBy(xpath=xpath_FSMCPopUpApplyNowButton)
	private WebElement btn_FSMCPopUpApplyNow;

	//*******************************************************************
	//*******************Refundability Pop Up****************************
	//*******************************************************************
	public final String xpath_RefundabilityPopUpCloseButton = "//button[contains(text(),'CLOSE WINDOW') or contains(text(),'CERRAR')]";
	@FindBy(xpath=xpath_RefundabilityPopUpCloseButton)
	private WebElement btn_RefundabilityPopUpClose;

	public final String xpath_RefundabilityPopUpCloseText = "//app-refundability-modal//div[@class='modal-header']";
	@FindBy(xpath=xpath_RefundabilityPopUpCloseText)
	private WebElement txt_RefundabilityPopUpHeader;

	public final String xpath_RefundabilityPopUpBodyInfoText = "//app-refundability-modal//p";
	@FindBy(xpath=xpath_RefundabilityPopUpBodyInfoText)
	private WebElement txt_RefundabilityPopUpBodyInfo;

	//*******************************************************************
	//**********************Taxes and Fees Pop Up************************
	//*******************************************************************
	public final String xpath_TaxesAndFeesPopUpCloseButton = "//button[contains(text(),'CLOSE WINDOW') or contains(text(),'CERRAR LA VENTANA')]";
	@FindBy(xpath=xpath_TaxesAndFeesPopUpCloseButton)
	private WebElement btn_TaxesAndFeesPopUpClose;

	public final String xpath_TaxesAndFeesPopUpHeaderText = "//app-taxes-and-fees-modal//div[@class='modal-header']";
	@FindBy(xpath=xpath_TaxesAndFeesPopUpHeaderText)
	private WebElement txt_TaxesAndFeesPopUpHeader;

	public final String xpath_TaxesAndFeesPopUpBodyInfoText = "//app-taxes-and-fees-modal//div[2]";
	@FindBy(xpath=xpath_TaxesAndFeesPopUpBodyInfoText)
	private WebElement txt_TaxesAndFeesPopUpBodyInfo;

	public final String xpath_TaxesAndFeesPopUpFuelPassThroughLink = "//app-taxes-and-fees-modal//a[@class='fuelLink']";
	@FindBy(xpath=xpath_TaxesAndFeesPopUpFuelPassThroughLink)
	private WebElement lnk_TaxesAndFeesPopUpFuelPassThrough;

	public final String xpath_TaxesAndFeesPopUpContractOfCarriageLink = "//app-taxes-and-fees-modal//a[contains(text(),'Contract of Carriage')]";
	@FindBy(xpath=xpath_TaxesAndFeesPopUpContractOfCarriageLink)
	private WebElement lnk_TaxesAndFeesPopUpContractOfCarriage;

	public final String xpath_TaxesAndFeesPopUpBaggageLink = "//app-taxes-and-fees-modal//a[@class='baggageFees']";
	@FindBy(xpath=xpath_TaxesAndFeesPopUpBaggageLink)
	private WebElement lnk_TaxesAndFeesPopUpBaggage;

	public final String xpath_TaxesAndFeesPopUpSeatAssignmentLink = "//app-taxes-and-fees-modal//a[@class='seatFees']";
	@FindBy(xpath=xpath_TaxesAndFeesPopUpSeatAssignmentLink)
	private WebElement lnk_TaxesAndFeesPopUpSeatAssignment;

	public final String xpath_TaxesAndFeesPopUpAnyChangesLink = "//app-taxes-and-fees-modal//a[@class='changeFees']";
	@FindBy(xpath=xpath_TaxesAndFeesPopUpAnyChangesLink)
	private WebElement lnk_TaxesAndFeesPopUpAnyChanges;

	//*****************************************************************************************************************
	//********************************Start of Methods of Flight Availability Page*************************************
	//*****************************************************************************************************************

	//*******************************************************************
	//*********************Flight Availability Header********************
	//*******************************************************************
	public WebElement getFlightAvailabilityPageHeaderText() {
		return txt_FlightAvailabilityPageHeader;
	}

	public WebElement getFlightAvailabilityPageSubHeaderText() {
		return txt_FlightAvailabilityPageSubHeader;
	}

	public WebElement getCoveredWithFlightFlexVerbiageText(){
		return txt_CoveredWithFlightFlexVerbiage;
	}

	public WebElement getFlightAvailabilityPageLegalVerbiageText() {
		return txt_FlightAvailabilityPageLegalVerbiage;
	}

	public WebElement getFlightAvailabilityBaggageLink() {
		return lnk_FlightAvailabilityBaggage;
	}

	public WebElement getFlightAvailabilityAdvanceSeatsLink() {
		return lnk_FlightAvailabilityAdvanceSeats;
	}

	public WebElement getFlightAvailabilityAnyChangesLink() {
		return lnk_FlightAvailabilityAnyChanges;
	}

	public WebElement getFlightAvailabilityNonRefundableLink() {return lnk_FlightAvailabilityNonRefundable;}

	public WebElement getFlightAvailabilityTaxesAndFeesLink() {return lnk_FlightAvailabilityTaxesAndFees;}

	public WebElement getDepartingMonthsDatePanel() {
		return pnl_DepartingMonthsDate;
	}

	public WebElement getDepartingMonthsDateColumnRowGrid() {
		return grdvew_DepartingMonthsDateColumnRow;
	}

	public List<WebElement> getDepartingWeeklyColumn() {
		return grdvew_DepartingWeeklyColumn;
	}

	public List<WebElement> getDepartingMonthsDateEachTile() {
		return grdvew_DepartingMonthsDateEachTile;
	}

	public WebElement getDepartingSelectedDateTile() {
		return grdvew_DepartingSelectedDateTile;
	}

	public WebElement getDepartingSelectedDateTileWeekly() {
		return grdvew_DepartingSelectedDateTileWeekly;
	}

	public List<WebElement> getDepartingWeekEachTilesGrid() {
		return grdvew_DepartingWeekEachTiles;
	}

	public WebElement getDepartingWeeklyTilesGrid(){
		return grdvew_DepartingWeeklyTiles;
	}

	public List<WebElement> getDepartingMonthsDateEachTileMoreLessText(){
		return txt_DepartingMonthsDateEachTileMoreLess;
	}

	public WebElement getDepartingMonthsDateSelectedTilePlaneIcon(){
		return icn_DepartingMonthsDateSelectedTilePlane;
	}

	//*******************************************************************
	//************************Flight Rows Header*************************
	//*******************************************************************
	public List<WebElement> getSortByText(){
		return txt_SortBy;
	}

	public List<WebElement> getSortByDropDown() {
		return drpdn_SortBy;
	}

	public List<WebElement> getJourneyDatesText(){
		return txt_JourneyDate;
	}

	public WebElement getNewSearchButton() {
		return btn_NewSearch;
	}

	public WebElement getFANewSearchPanel() {
		return pnl_FANewSearch;
	}
	//*******************************************************************
	//************************Departure Flight Calendar******************
	//*******************************************************************
	public List<WebElement> getCalNextArrowImage(){
		return img_NextArrow;
	}

	public WebElement getDepartingCityPairGreyLineImage() {
		return img_DepartingCityPairGreyLine;
	}

	public WebElement getDepartingHeader9FCBannerImage() {
		return img_DepartingHeader9FCBanner;
	}

	public WebElement getDepartingHeader9FCBannerText() {
		return txt_DepartingHeader9FCBanner;
	}


	public List<WebElement> getDepCalBlocksGridView(){
		return grdvew_DepCalDateBlocks;
	}

	public WebElement getDepartingCarouselWeekViewSwitchLabel() {
		return lbl_DepartingCarouselWeekViewSwitch;
	}

	public WebElement getDepartingCarouselMonthViewSwitchLabel() {
		return lbl_DepartingCarouselMonthViewSwitch;
	}

	public WebElement getDepartingCarouselDollarsViewSwitchLabel() {
		return lbl_DepartingCarouselDollarsViewSwitch;
	}

	public WebElement getDepartingCarouselMilesViewSwitchLabel() {
		return lbl_DepartingCarouselMilesViewSwitch;
	}

	public List<WebElement> getDepartingCarouselLeftButton() {
		return btn_DepartingCarouselLeft;
	}

	public List<WebElement> getDepartingCarouselRightButton() {
		return btn_DepartingCarouselRight;
	}

	public List<WebElement> getDepartingCarouselDateCardRowsPanel() {
		return pnl_DepartingCarouselDateCardRows;
	}

	public List<WebElement> getDepartingCarouselDateCardColumnsPanel() {
		return pnl_DepartingCarouselDateCardColumns;
	}

	public List<WebElement> getDepartingCarouselDateCardsPanel() {
		return pnl_DepartingCarouselDateCards;
	}

	public List<WebElement> getDepartingCarouselDateCards9FCIconText() {
		return txt_DepartingCarouselDateCards9FCIcon;
	}

	public List<WebElement> getDepartingCarouselDateCardsMCIconText() {
		return txt_DepartingCarouselDateCardsMCIcon;
	}

	public List<WebElement> getDepartingCarouselDateCardsDateText() {
		return txt_DepartingCarouselDateCardsDate;
	}

	public List<WebElement> getDepartingCarouselDateCardsLowestFareInDollarsText() {
		return txt_DepartingCarouselDateCardsLowestFareInDollars;
	}

	public List<WebElement> getDepartingCarouselDateCardsLowestFareInMilesText() {
		return txt_DepartingCarouselDateCardsLowestFareInMiles;
	}

	public List<WebElement> getDepartingCarouselDateCardsLowestFareMilesRedemptionFeeText() {
		return txt_DepartingCarouselDateCardsLowestFareMilesRedemptionFee;
	}

	public List<WebElement> getDepartingCarouselDateCardsSavingsText() {
		return txt_DepartingCarouselDateCardsSavings;
	}

	//*******************************************************************
	//********************Depart Journey*********************************
	//*******************************************************************
	public WebElement getDepartingOriginAndDestinationText() {
		return txt_DepartingOriginAndDestination;
	}

	//*******************************************************************
	//*************************Departure Flight Rows*********************
	//*******************************************************************
	public List<WebElement> getDepFlightRowsGridView(){
		return grdvew_DepFlightRows;
	}

	public WebElement getDepartingFlightDateText() {
		return txt_DepartingFlightDate;
	}

	public WebElement getDepartingFlightSortByDropDown() {
		return drpdwn_DepartingFlightSortBy;
	}

	public List<WebElement> getDepartingDepartTimeText() {
		List<WebElement> dateElementList = new ArrayList<>();

		for(WebElement element:txt_DepartingDepartTime){
			if(element.isDisplayed()){
				dateElementList.add(element);
			}
		}

		return dateElementList;
	}

	public List<WebElement> getDepartingDurationText() {
		return txt_DepartingDuration;
	}

	public List<WebElement> getDepartingArriveTimeText() {
		return txt_DepartingArriveTime;
	}

	public List<WebElement> getDepartingNumebrOfStopsButton() {
		return btn_DepartingNumebrOfStops;
	}

	public List<WebElement> getDepartingAvailableSeatsButton() {
		return btn_DepartingAvailableSeats;
	}

	public List<WebElement> getDeparting9FCFarePriceText() {
		return txt_Departing9FCFarePrice;
	}

	public List<WebElement> getDepartingStandardFarePriceText() {
		return txt_DepartingStandardFarePrice;
	}

	public List<WebElement> getDepartingMCHolderFarePriceText() {
		return txt_DepartingMCHolderFarePrice;
	}

	public List<WebElement> getDepartingJourneyEditButton() {
		return btn_DepartingJourneyEdit;
	}

	//*******************************************************************
	//*****************Selected Departure Flight Block*******************
	//*******************************************************************
	public List<WebElement> getSelectedFlightBlocksPanel(){
		return pnl_SelectedFlightBlocks;
	}

	public List<WebElement> getSelectedDepatureFlightBlockPanel(){
		return pnl_SelectedDepatureFlightBlock;
	}

	public WebElement getSelectedDepDateText(){

		for(int depDateCounter=0;depDateCounter<txt_SelectedDepDate.size();depDateCounter++){
			if(txt_SelectedDepDate.get(depDateCounter).isDisplayed()){
				return txt_SelectedDepDate.get(depDateCounter);
			}
		}
		return null;
	}

	public List<WebElement> getSelectedFlightCityText(){
		return txt_SelectedFlightCity;
	}

	public List<WebElement> getDepartureFlightEditButton(){
		return btn_DepartureFlightBlockEdit;
	}

	public WebElement getDepartureFlightBlockPriceText(){
		return txt_DepartureFlightBlockPrice;
	}

	public List<WebElement> getDepartingFlightBlockDepartTimeText(){
		return txt_DepartingFlightBlockDepartTime;
	}

	public List<WebElement> getDepartingFlightBlockArivalTimeText(){
		return txt_DepartingFlightBlockArivalTime;
	}

	public List<WebElement> getSeletedDepatingFlightNatureButton(){
		return btn_SeletedDepatingFlightNature;
	}

	public WebElement getSelectedDepatingFlightViewSeatMapButton(){
		return btn_SelectedDepatingFlightViewSeatMap;
	}

	//*******************************************************************
	//*****************Return Journey************************************
	//*******************************************************************
	public WebElement getReturningOriginAndDestinationText() {
		return txt_ReturningOriginAndDestination;
	}

	//*******************************************************************
	//*************************Arrival Flight Calendar*******************
	//*******************************************************************
	public List<WebElement> getArrCalBlocksGridView(){
		return grdvew_ArrCalDateBlocks;
	}

	public WebElement getReturningCarouselWeekViewSwitchLabel() {
		return lbl_ReturningCarouselWeekViewSwitch;
	}

	public WebElement getReturningCarouselMonthViewSwitchLabel() {
		return lbl_ReturningCarouselMonthViewSwitch;
	}

	public WebElement getReturningCarouselDollarsViewSwitchLabel() {
		return lbl_ReturningCarouselDollarsViewSwitch;
	}

	public WebElement getReturningCarouselMilesViewSwitchLabel() {
		return lbl_ReturningCarouselMilesViewSwitch;
	}

	public List<WebElement> getReturningCarouselLeftButton() {
		return btn_ReturningCarouselLeft;
	}

	public List<WebElement> getReturningCarouselRightButton() {
		return btn_ReturningCarouselRight;
	}

	public List<WebElement> getReturningCarouselDateCardsPanel() {
		return pnl_ReturningCarouselDateCards;
	}

	public List<WebElement> getReturningCarouselDateCards9FCIconText() {
		return txt_ReturningCarouselDateCards9FCIcon;
	}

	public List<WebElement> getReturningCarouselDateCardsMCIconText() {
		return txt_ReturningCarouselDateCardsMCIcon;
	}

	public List<WebElement> getReturningCarouselDateCardsDateText() {
		return txt_ReturningCarouselDateCardsDate;
	}

	public List<WebElement> getReturningCarouselDateCardsLowestFareInDollarsText() {
		return txt_ReturningCarouselDateCardsLowestFareInDollars;
	}

	public List<WebElement> getReturningCarouselDateCardsLowestFareInMilesText() {
		return txt_ReturningCarouselDateCardsLowestFareInMiles;
	}

	public List<WebElement> getReturningCarouselDateCardsLowestFareMilesRedemptionFeeText() {
		return txt_ReturningCarouselDateCardsLowestFareMilesRedemptionFee;
	}

	public List<WebElement> getReturningCarouselDateCardsSavingsText() {
		return txt_ReturningCarouselDateCardsSavings;
	}

	//*******************************************************************
	//**************************Arrival Flight Rows**********************
	//*******************************************************************
	public List<WebElement> getArrFlightRowsGridView(){
		return grdvew_ArrFlightRows;
	}

	public WebElement getReturningFlightDateText() {
		return txt_ReturningFlightDate;
	}

	public WebElement getReturningFlightSortByDropDown() {
		return drpdwn_ReturningFlightSortBy;
	}

	public List<WebElement> getReturningDepartTimeText() {
		return txt_ReturningDepartTime;
	}

	public List<WebElement> getReturningDurationText() {
		return txt_ReturningDuration;
	}

	public List<WebElement> getReturningArriveTimeText() {
		return txt_ReturningArriveTime;
	}

	public List<WebElement> getReturningNumebrOfStopsButton() {
		return btn_ReturningNumebrOfStops;
	}

	public List<WebElement> getReturningAvailableSeatsButton() {
		return btn_ReturningAvailableSeats;
	}

	public List<WebElement> getReturning9FCFarePriceText() {
		return txt_Returning9FCFarePrice;
	}

	public List<WebElement> getReturningStandardFarePriceText() {
		return txt_ReturningStandardFarePrice;
	}

	public List<WebElement> getReturningMCHolderFarePriceText() {
		return txt_ReturningMCHolderFarePrice;
	}

	public List<WebElement> getReturningJourneyEditButton() {
		return btn_ReturningJourneyEdit;
	}

	//*******************************************************************
	//*****************Selected Arrival Flight Block*********************
	//*******************************************************************
	public List<WebElement> getSelectedFlightBlockPanel(){
		return pnl_SelectedFlightBlock;
	}

	public List<WebElement> getSelectedArrivalFlightBlockPanel(){
		return pnl_SelectedArrivalFlightBlock;
	}

	public List<WebElement> getArrivalFlightEditButton(){
		return btn_ArrivalFlightBlockEdit;
	}

	public WebElement getSelectedRetDateText(){

		for(int retDateCounter=2;retDateCounter<txt_SelectedRetDate.size();retDateCounter++){
			if(txt_SelectedRetDate.get(retDateCounter).isDisplayed()){
				return txt_SelectedRetDate.get(retDateCounter);
			}
		}
		return null;
	}


	public WebElement getArrivalFlightBlockPriceText(){
		return txt_ArrivalFlightBlockPrice;
	}

	public List<WebElement> getSelectedReturningFlightNatureButton(){
		return btn_SelectedReturningFlightNature;
	}

	//*******************************************************************
	//*********************Flight Information Popup**********************
	//*******************************************************************
	public WebElement getStopsPopUpHeaderText() {
		return txt_StopsPopUpHeader;
	}

	public WebElement getStopsPopUpCloseButton() {
		return btn_StopsPopUpClose;
	}

	public List<WebElement> getStopsPopUpFlightsNumberText(){
		return txt_StopsPopUpFlightsNumber;
	}

	public List<WebElement> getStopsPopUpAircraftTypeText(){
		return txt_StopsPopUpAircraftType;
	}

	public List<WebElement> getStopsPopUpDepartureLabel(){
		return lbl_StopsPopUpDeparture;
	}

	public List<WebElement> getStopsPopUpDepartureAirportsText(){
		return txt_StopsPopUpDepartureAirports;
	}

	public List<WebElement> getStopsPopUpDepartureTimeText(){
		return txt_StopsPopUpDepartureTime;
	}

	public List<WebElement> getStopsPopUpArrivalLabel(){
		return lbl_StopsPopUpArrival;
	}

	public List<WebElement> getStopsPopUpArrivalAirportsText(){
		return txt_StopsPopUpArrivalAirports;
	}

	public List<WebElement> getStopsPopUpArrivalTimeText(){
		return txt_StopsPopUpArrivalTime;
	}

	public List<WebElement> getStopsPopUpDurationLabel(){
		return lbl_StopsPopUpFlightDuration;
	}

	public List<WebElement> getStopsPopUpFlightDurationTimeText(){
		return txt_StopsPopUpFlightDurationTime;
	}

	public List<WebElement> getStopsPopUpOnTimeLabel(){
		return lbl_StopsPopUpOnTime;
	}

	public List<WebElement> getStopsPopUpOnTimeText(){
		return txt_StopsPopUpOnTime;
	}

	public List<WebElement> getStopsPopUpLateLabel(){
		return lbl_StopsPopUpLate;
	}

	public List<WebElement> getStopsPopUpLateTimeText(){
		return txt_StopsPopUpLateTime;
	}

	public List<WebElement> getStopsPopUpLayoverTimeText(){
		return txt_StopsPopUpLayoverTime;
	}


	//*******************************************************************
	//***********************Seat Availability Popup ********************
	//*******************************************************************
	public List<WebElement> getFlightLegSeatMapLabel(){
		return lbl_SeatMapPerLeg;
	}

	public List<WebElement> getBFSSeatMapGridView(){
		return grdvew_BigFrontSeats;
	}

	public List<WebElement> getPSSeatMapgridview(){
		return grdvew_PremiumSeats;
	}

	public WebElement getAircraftTypeSeatMapLabel() {
		return txt_AircraftType;
	}

	public WebElement getCloseSeatMapButton() {
		return btn_SeatsPopupClose;
	}

	public List<WebElement> getAllSeatsGridView(){
		return grdvew_AllSeats;
	}

	//*******************************************************************
	//***********************Flight Total Amount Block*******************
	//*******************************************************************
	public WebElement getFlightTotalAmountText() {
		return txt_FlightTotalAmount;
	}

	public WebElement getFlightTotalBreakDownCityPairText(){
		return txt_FlightTotalBreakDownCityPair;
	}

	public WebElement getFlightTotalBreakDownCityPairPriceText(){
		return txt_FlightTotalBreakDownCityPairPrice;
	}

	public WebElement getFlightTotalTextText(){
		return txt_FlightTotalText;
	}

	//*******************************************************************
	//************************Spirit Member Container********************
	//*******************************************************************
	public List<WebElement> getFareContainerPanel(){
		return pnl_FareContainer;
	}


	public WebElement getStandardFareButton() {
		return btn_StandardFare;
	}

	public WebElement getMemberTypeText(){
		return txt_MemberType;
	}

	public WebElement getStandardTypeText(){
		return txt_StandardType;
	}

	public WebElement getStandardFarePriceText() {
		return txt_StandardFarePrice;
	}

	public WebElement getMemberFareButton() {
		return btn_MemberFare;
	}

	public WebElement getMemberFarePriceText() {
		return txt_MemberFarePrice;
	}

	public WebElement getMemberFareSaveUpToPriceText() {
		return txt_MemberFareSaveUpToPrice;
	}


	//*******************************************************************
	//*******************Bundle Fare Upgrade & Save Popup****************
	//*******************************************************************
	public WebElement getBundleFareHeaderText() {
		return txt_BundleFareHeader;
	}

	public WebElement getBundleFareCloseButton() {
		return btn_BundleFareClose;
	}

	public List<WebElement> getBundleFareTilesPanel() {
		return pnl_BundleFareTiles;
	}

	public WebElement getBookItTitleText() {
		return txt_BookItTitle;
	}

	public List<WebElement> getBookItTileBenefitsText() {
		return txt_BookItTileBenefits;
	}

	public WebElement getBookItTileDescriptionText() {
		return txt_BookItTileDescription;
	}

	public WebElement getBookItTileIncludedInfoText() {
		return txt_BookItTileIncludedInfo;
	}

	public WebElement getBookItPriceText() {
		return txt_BookItPrice;
	}

	public List<WebElement> getBookItButton() {
		return btn_BookIt;
	}

	public WebElement getBoosItTitleText() {
		return txt_BoosItTitle;
	}

	public WebElement getBoosItFlagText() {
		return txt_BoosItFlag;
	}

	public List<WebElement> getBoosItTileBenefitsText() {
		return txt_BoosItTileBenefits;
	}

	public WebElement getBoosItMoreInfoText() {
		return txt_BoosItMoreInfo;
	}

	public WebElement getBoostItSavingPriceText() {return txt_BoostItSavingPrice; }

	public WebElement getBoostItSavingPlusText() {return txt_BoostItSavingPlus; }

	public WebElement getBoostItSavingPlusPriceText() {return txt_BoostItSavingPlusPrice; }

	public WebElement getBoostItSaveUpToPriceText() {
		return txt_BoostItSaveUpToPrice;
	}

	public WebElement getBoostItPriceText() {
		return txt_BoostItPrice;
	}

	public List<WebElement> getBoostItButton() {
		return btn_BoostIt;
	}

	public WebElement getBundleTileTitleText() {return txt_BundleTileTitle; }

	public WebElement getBundleFlagText() {return txt_BundleFlag; }

	public List<WebElement> getBundleTileBenefitsList() { return lstbx_BundleTileBenefits; }

	public WebElement getBundleSavingPriceText() {return txt_BundleSavingPrice; }

	public WebElement getBundleItSaveUpToPriceText() {
		return txt_BundleItSaveUpToPrice;
	}

	public WebElement getBundleItPriceText() {
		return txt_BundleItPrice;
	}

	public List<WebElement> getBundleItButton() {
		return btn_BundleIt;
	}

	//*******************************************************************
	//**********************Early Departure Popup************************
	//*******************************************************************
	public WebElement getEarlyDepartureHeaderText() {
		return txt_EarlyDepartureHeader;
	}

	public WebElement getEarlyDepartureCloseButton(){
		return btn_EarlyDepartureClose;
	}

	public WebElement getEarlyDepartureContinueButton() {
		return btn_EarlyDepartureContinue;
	}

	//*******************************************************************
	//*****************Insufficient Miles********************************
	//*******************************************************************
	public WebElement getInsufficientMilesPopUpBuyMoreMilesButton() {
		return btn_InsufficientMilesPopUpBuyMoreMiles;
	}

	public WebElement getInsufficientMilesPopUpChangeFlightButton() {
		return btn_InsufficientMilesPopUpChangeFlight;
	}

	public WebElement getInsufficientMilesPopUpViewInDollarsButton() {
		return btn_InsufficientMilesPopUpViewInDollars;
	}

	//*******************************************************************
	//***************Spirit MasterCard***********************************
	//*******************************************************************
	public WebElement getFSMCPopUpApplyNowButton() {
		return btn_FSMCPopUpApplyNow;
	}

	//*******************************************************************
	//*******************Refundability Pop Up****************************
	//*******************************************************************
	public WebElement getRefundabilityPopUpCloseButton() {
		return btn_RefundabilityPopUpClose;
	}

	public WebElement getRefundabilityPopUpHeaderText() {return txt_RefundabilityPopUpHeader;}

	public WebElement getRefundabilityPopUpBodyInfoText() {return txt_RefundabilityPopUpBodyInfo;}

	//*******************************************************************
	//**********************Taxes and Fees Pop Up************************
	//*******************************************************************
	public WebElement getTaxesAndFeesPopUpCloseButton() {
		return btn_TaxesAndFeesPopUpClose;
	}

	public WebElement getTaxesAndFeesPopUpHeaderText() {return txt_TaxesAndFeesPopUpHeader;}

	public WebElement getTaxesAndFeesPopUpBodyInfoText() {return txt_TaxesAndFeesPopUpBodyInfo;}

	public WebElement getTaxesAndFeesPopUpFuelPassThroughLink() {return lnk_TaxesAndFeesPopUpFuelPassThrough;}

	public WebElement getTaxesAndFeesPopUpContractOfCarriageLink() {return lnk_TaxesAndFeesPopUpContractOfCarriage;}

	public WebElement getTaxesAndFeesPopUpBaggageLink() {return lnk_TaxesAndFeesPopUpBaggage;}

	public WebElement getlnk_TaxesAndFeesPopUpSeatAssignmentLink() {return lnk_TaxesAndFeesPopUpSeatAssignment;}

	public WebElement getTaxesAndFeesPopUpAnyChangesLink() {return lnk_TaxesAndFeesPopUpAnyChanges;}
}
