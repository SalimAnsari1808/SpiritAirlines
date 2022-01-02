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

public class DeniedBoardingToolPage {

    public DeniedBoardingToolPage(WebDriver driver) {
        //this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //*******************************************************************
    //*************************Navigation Bar***************************
    //*******************************************************************
    public final String xpath_DeniedBoardingToolButton = "//span[contains(text(),'Denied Boarding Tool')]";
    @FindBy(xpath= xpath_DeniedBoardingToolButton)
    private WebElement btn_DeniedBoardingTool;

    public final String xpath_DashBoardButton = "//i[contains(@class,'dashboard')]";
    @FindBy(xpath= xpath_DashBoardButton)
    private WebElement btn_Dashboard;

    public final String xpath_SummaryButton = "//a[contains(@href,'summary')]";
    @FindBy(xpath= xpath_SummaryButton)
    private WebElement btn_Summary;

    public final String xpath_AdminButton = "//a[contains(@href,'admin')]";
    @FindBy(xpath= xpath_AdminButton)
    private WebElement btn_Admin;

    //*******************************************************************
    //*************************Search DashBoard**************************
    //*******************************************************************
    public final String xpath_SelectStationDropDown = "//select[@id='inputStation']";
    @FindBy(xpath= xpath_SelectStationDropDown)
    private WebElement drpdwn_SelectStation;

    public final String xpath_TodayRadioButton = "//input[@id='optionToday']";
    @FindBy(xpath= xpath_TodayRadioButton)
    private WebElement rdbtn_Today;

    public final String xpath_YesterdayRadioButton = "//input[@id='optionYesteday']";
    @FindBy(xpath= xpath_YesterdayRadioButton)
    private WebElement rdbtn_Yesterday;

    public final String xpath_DateRangeRadioButton = "//input[@id='optionDateRange']";
    @FindBy(xpath= xpath_DateRangeRadioButton)
    private WebElement rdbtn_DateRange;

    public final String xpath_DateRangeStartTextbox = "//input[@id='travelDateStart']";
    @FindBy(xpath= xpath_DateRangeStartTextbox)
    private WebElement txtbox_DateRangeStart;

    public final String xpath_DateRangeEndTextbox = "//input[@id='travelDateEnd']";
    @FindBy(xpath= xpath_DateRangeEndTextbox)
    private WebElement txtbox_DateRangeEnd;

    public final String xpath_SearchButton = "//form[contains(@class,'dashboard')]//button";
    @FindBy(xpath= xpath_SearchButton)
    private WebElement btn_Search;

    public final String xpath_PageLoadedToaster = "//div[@id = 'toast-container']//div[contains(text(),'Denied Boarding Tool loaded!')]";
    @FindBy(xpath= xpath_PageLoadedToaster)
    private WebElement pnl_PageLoadedToaster;

    public final String xpath_FlightTableLoadSpinnerImage = "//table//tr[contains(@class, 'loading')]//i[contains(@class, spinner)]";
    @FindBy(xpath= xpath_FlightTableLoadSpinnerImage)
    private WebElement img_FlightTableLoadSpinner;

    //************************************************
    //*********Station Flight Table Data**************
    //************************************************
    public final String xpath_TableFlightNumberButton = "//table//tr//button[1]";
    @FindBy(xpath= xpath_TableFlightNumberButton)
    private List<WebElement> btn_TableFlightNumber;

    public final String xpath_TableDateText = "//table//tr//td[2]";
    @FindBy(xpath= xpath_TableDateText)
    private List<WebElement> txt_TableDate;

    public final String xpath_TableJourneyText = "//table//tr//td[3]";
    @FindBy(xpath= xpath_TableJourneyText)
    private List<WebElement> txt_TableJourney;

    public final String xpath_TableSTDText = "//table//tr//td[4]";
    @FindBy(xpath= xpath_TableSTDText)
    private List<WebElement> txt_TableSTD;

    public final String xpath_TableFlightStatusText = "//table//tr//td[5]";
    @FindBy(xpath= xpath_TableFlightStatusText)
    private List<WebElement> txt_TableFlightStatus;

    public final String xpath_TableYesDeniedBoardingRadioButton = "//table//tr//td//input[@value = '0']";
    @FindBy(xpath= xpath_TableYesDeniedBoardingRadioButton)
    private List<WebElement> rdbtn_TableYesDeniedBoarding;

    public final String xpath_TableNoDeniedBoardingRadioButton = "//table//tr//td//input[@value = '1']";
    @FindBy(xpath= xpath_TableNoDeniedBoardingRadioButton)
    private List<WebElement> rdbtn_TableNoDeniedBoarding;

    public final String xpath_TableBoardingStatusText = "//table//tr//td[7]";
    @FindBy(xpath= xpath_TableBoardingStatusText)
    private List<WebElement> txt_TableBoardingStatus;

    //************************************************
    //********Flight Reservation Table Data************
    //************************************************
    public final String xpath_ManifestTablePaxNameText = "//div[contains(@data-ng-show, 'selectedFlight.FlightNumber')]//tbody//td[@class='name']";
    @FindBy(xpath= xpath_ManifestTablePaxNameText)
    private List<WebElement> txt_ManifestTablePaxName;

    public final String xpath_ManifestTablePNRText = "//div[contains(@data-ng-show, 'selectedFlight.FlightNumber')]//tbody//td[@class='name']//following-sibling::td[1]";
    @FindBy(xpath= xpath_ManifestTablePNRText)
    private List<WebElement> txt_ManifestTablePNR;

    public final String xpath_ManifestTableJourneyText = "//div[contains(@data-ng-show, 'selectedFlight.FlightNumber')]//tbody//td[@class='name']//following-sibling::td[2]";
    @FindBy(xpath= xpath_ManifestTableJourneyText)
    private List<WebElement> txt_ManifestTableJourney;

    public final String xpath_ManifestTableBoardingStatusText = "//div[contains(@data-ng-show, 'selectedFlight.FlightNumber')]//tbody//td[@class='name']//following-sibling::td[3]";
    @FindBy(xpath= xpath_ManifestTableBoardingStatusText)
    private List<WebElement> txt_ManifestTableBoardingStatus;

    public final String xpath_ManifestTableSeqNumText = "//div[contains(@data-ng-show, 'selectedFlight.FlightNumber')]//tbody//td[@class='name']//following-sibling::td[4]";
    @FindBy(xpath= xpath_ManifestTableSeqNumText)
    private List<WebElement> txt_ManifestTableSeqNum;

    public final String xpath_ManifestTableDBStatusText = "//div[contains(@data-ng-show, 'selectedFlight.FlightNumber')]//tbody//td[@class='name']//following-sibling::td[5]";
    @FindBy(xpath= xpath_ManifestTableDBStatusText)
    private List<WebElement> txt_ManifestTableDBStatus;

    public final String xpath_ManifestTableVolunteerBidButton = "//div[contains(@data-ng-show, 'selectedFlight.FlightNumber')]//tbody//td[@class='name']//following-sibling::td[6]";
    @FindBy(xpath= xpath_ManifestTableVolunteerBidButton)
    private List<WebElement> btn_ManifestTableVolunteerBid;

    public final String xpath_ManifestTableBidAcceptedText = "//div[contains(@data-ng-show, 'selectedFlight.FlightNumber')]//tbody//td[@class='name']//following-sibling::td[7]//input[@type='checkbox']";
    @FindBy(xpath= xpath_ManifestTableBidAcceptedText)
    private List<WebElement> txt_ManifestTableBidAccepted;


    //************************************************
    //********Red Box Reservation Information*********
    //************************************************
    public final String xpath_ReservationPNRText = "//form[@name = 'paxDetailsForm']//dd[1]";
    @FindBy(xpath= xpath_ReservationPNRText)
    private WebElement txt_ReservationPNR;

    public final String xpath_ReservationFlightNumberText = "//form[@name = 'paxDetailsForm']//dd[2]";
    @FindBy(xpath= xpath_ReservationFlightNumberText)
    private WebElement txt_ReservationFlightNumnber;

    public final String xpath_ReservationDepartureText = "//form[@name = 'paxDetailsForm']//dd[3]";
    @FindBy(xpath= xpath_ReservationDepartureText)
    private WebElement txt_ReservationDeparture;

    public final String xpath_ReservationArrivalText = "//form[@name = 'paxDetailsForm']//dd[4]";
    @FindBy(xpath= xpath_ReservationArrivalText)
    private WebElement txt_ReservationArrival;

    public final String xpath_ReservationSTDText = "//form[@name = 'paxDetailsForm']//dd[5]";
    @FindBy(xpath= xpath_ReservationSTDText)
    private WebElement txt_ReservationSTD;

    public final String xpath_ReservationSTAText = "//form[@name = 'paxDetailsForm']//dd[6]";
    @FindBy(xpath= xpath_ReservationSTAText)
    private WebElement txt_ReservationSTA;

    public final String xpath_ReservationAircraftTailNumberText = "//form[@name = 'paxDetailsForm']//dd[7]";
    @FindBy(xpath= xpath_ReservationAircraftTailNumberText)
    private WebElement txt_ReservationAircraftTailNumber;

    public final String xpath_ReservationJourneyText = "//form[@name = 'paxDetailsForm']//dd[8]";
    @FindBy(xpath= xpath_ReservationJourneyText)
    private WebElement txt_ReservationJourney;

    public final String xpath_ReservationPassengersOnBookingText= "//form[@name = 'paxDetailsForm']//dd[9]";
    @FindBy(xpath= xpath_ReservationPassengersOnBookingText)
    private WebElement txt_ReservationPassengerOnBooking;

    //Tabs
    public final String xpath_DeniedBoardingTab = "//li[@heading = 'Denied Boarding']//a";
    @FindBy(xpath= xpath_DeniedBoardingTab)
    private WebElement tab_DeniedBoarding;

    public final String xpath_RebookTab= "//li[@heading = 'Rebook']//a";
    @FindBy(xpath= xpath_RebookTab)
    private WebElement tab_Rebook;

    public final String xpath_CompensationAndVouchersTab= "//li[@heading = 'Compensation & Vouchers']";
    @FindBy(xpath= xpath_CompensationAndVouchersTab)
    private WebElement tab_CompensationAndVouchers;

    public final String xpath_AmenitiesTab= "//li[@heading = 'Amenities']//a";
    @FindBy(xpath= xpath_AmenitiesTab)
    private WebElement tab_Amenities;

    public final String xpath_FinalizeVoucherTab= "//li[@heading = 'Finalized Voucher']//a";
    @FindBy(xpath= xpath_FinalizeVoucherTab)
    private WebElement tab_FinalizeVoucher;

    public final String xpath_BidInputUpdateTab= "//li[@heading = 'Bid Input/Update']//a";
    @FindBy(xpath= xpath_BidInputUpdateTab)
    private WebElement tab_BidInputUpdate;

    //Denied Boarding
    public final String xpath_BoardingStatus= "//label[contains(text(),'Boarding Status')]//following-sibling::span";
    @FindBy(xpath= xpath_BoardingStatus)
    private WebElement txt_BoardingStatus;

    public final String xpath_DeniedBoardingStatusINVOL= "//input[@name = 'deniedBoardingStatus'][@value = 'INVOL']";
    @FindBy(xpath= xpath_DeniedBoardingStatusINVOL)
    private WebElement rdbtn_DeniedBoardingStatusINVOL;

    public final String xpath_DeniedBoardingStatusVOL= "//input[@name = 'deniedBoardingStatus'][@value = 'VOL']";
    @FindBy(xpath= xpath_DeniedBoardingStatusVOL)
    private WebElement rdbtn_DeniedBoardingStatusVOL;

    public final String xpath_DeniedBoardingReasonEquipmentDowngrade= "//input[@name = 'deniedBoardingReason'][@value = 'Equipment Downgrade']";
    @FindBy(xpath= xpath_DeniedBoardingReasonEquipmentDowngrade)
    private WebElement rdbtn_DeniedBoardingReasonEquipmentDowngrade;

    public final String xpath_DeniedBoardingReasonOverbooked= "//input[@name = 'deniedBoardingReason'][@value = 'Overbooked']";
    @FindBy(xpath= xpath_DeniedBoardingReasonOverbooked)
    private WebElement rdbtn_DeniedBoardingReasonOverbooked;

    public final String xpath_DeniedBoardingReasonWeightRestriction= "//input[@name = 'deniedBoardingReason'][@value = 'Weight Restriction']";
    @FindBy(xpath= xpath_DeniedBoardingReasonWeightRestriction)
    private WebElement rdbtn_DeniedBoardingReasonWeightRestriction;

    //Rebook
    public final String xpath_RebookNo= "//input[@name = 'isRebooked'][@value = '0']";
    @FindBy(xpath= xpath_RebookNo)
    private WebElement rdbtn_RebookNo;

    public final String xpath_RebookYes= "//input[@name = 'isRebooked'][@value = '1']";
    @FindBy(xpath= xpath_RebookYes)
    private WebElement rdbtn_RebookYes;

    public final String xpath_RebookWithNK= "//input[@name = 'rebookedWith'][@value = 'NK']";
    @FindBy(xpath= xpath_RebookWithNK)
    private WebElement rdbtn_RebookWithNK;

    public final String xpath_RebookWithOA = "//input[@name = 'rebookedWith'][@value = 'OA']";
    @FindBy(xpath= xpath_RebookWithOA)
    private WebElement rdbtn_RebookWithOA;

    public final String xpath_RebookTwoLetterAirlineCodeTextBox = "//input[@placeholder='Two-letter Airline Code']";
    @FindBy(xpath= xpath_RebookTwoLetterAirlineCodeTextBox)
    private WebElement txtbx_RebookTwoLetterAirlineCode;

    public final String xpath_RebookTicketAmountTextBox = "//input[@placeholder='XXXX.XX']";
    @FindBy(xpath= xpath_RebookTicketAmountTextBox)
    private WebElement txtbx_RebookTicketAmount;

    public final String xpath_RebookFormOfPaymentITSMAXRadioButton = "(//input[@name='rebookedFOP'])[1]";
    @FindBy(xpath= xpath_RebookFormOfPaymentITSMAXRadioButton)
    private WebElement rdbtn_RebookFormOfPaymentITSMAX;

    public final String xpath_RebookFormOfPaymentPCardRadioButton = "(//input[@name='rebookedFOP'])[2]";
    @FindBy(xpath= xpath_RebookFormOfPaymentPCardRadioButton)
    private WebElement rdbtn_RebookFormOfPaymentPCardRadioButton;

    public final String xpath_RebookArriveTime0to1Hour = "//input[@name = 'arrivalTimeFrame'][@value = '0-1 Hour']";
    @FindBy(xpath= xpath_RebookArriveTime0to1Hour)
    private WebElement rdbtn_ArriveTimeFrame0to1Hour;

    public final String xpath_RebookArriveTime1to2Hours = "//input[@name = 'arrivalTimeFrame'][@value = '1-2 Hours']";
    @FindBy(xpath= xpath_RebookArriveTime1to2Hours)
    private WebElement rdbtn_ArriveTimeFrame1to2Hours;

    public final String xpath_RebookArriveTimeMoreThan2Hours= "//input[@name = 'arrivalTimeFrame'][@value = 'More Than 2 Hours']";
    @FindBy(xpath= xpath_RebookArriveTimeMoreThan2Hours)
    private WebElement rdbtn_ArriveTimeFrameMoreThan2Hours;

    //Amenities
    public final String xpath_AmenitiesFoodNo = "//input[@name = 'hasFoodCompensation'][@value = '0']";
    @FindBy(xpath= xpath_AmenitiesFoodNo)
    private WebElement rdbtn_AmenitiesFoodNo;

    public final String xpath_AmenitiesFoodYes = "//input[@name = 'hasFoodCompensation'][@value = '1']";
    @FindBy(xpath= xpath_AmenitiesFoodYes )
    private WebElement rdbtn_AmenitiesFoodYes;

    public final String xpath_AmenitiesFoodAmountTextBox = "//label[contains(text() ,'Food Amount')]//following-sibling::input";
    @FindBy(xpath= xpath_AmenitiesFoodAmountTextBox )
    private WebElement txtbx_AmenitiesFoodAmount;

    public final String xpath_AmenitiesFoodDirectBillRadioButton = "(//input[@name='foodFOP'])[1]";
    @FindBy(xpath= xpath_AmenitiesFoodDirectBillRadioButton )
    private WebElement rdbtn_AmenitiesFoodDirectBill;

    public final String xpath_AmenitiesFoodPCardRadioButton = "(//input[@name='foodFOP'])[2]";
    @FindBy(xpath= xpath_AmenitiesFoodPCardRadioButton )
    private WebElement rdbtn_AmenitiesFoodPCard;

    public final String xpath_AmenitiesFoodVoucherRadioButton = "(//input[@name='foodFOP'])[3]";
    @FindBy(xpath= xpath_AmenitiesFoodVoucherRadioButton )
    private WebElement rdbtn_AmenitiesFoodVoucher;

    public final String xpath_AmenitiesGroundTransportationNo = "//input[@name = 'hasGroundTransportationCompensation'][@value = '0']";
    @FindBy(xpath= xpath_AmenitiesGroundTransportationNo )
    private WebElement rdbtn_AmenitiesGroundTransportationNo;

    public final String xpath_AmenitiesGroundTransportationYes = "//input[@name = 'hasGroundTransportationCompensation'][@value = '1']";
    @FindBy(xpath= xpath_AmenitiesGroundTransportationYes )
    private WebElement rdbtn_AmenitiesGroundTransportationYes;

    public final String xpath_AmenitiesGroundTransportationAmountTextBox = "//label[contains(text() ,'Ground Transportation Amount')]//following-sibling::input";
    @FindBy(xpath= xpath_AmenitiesGroundTransportationAmountTextBox )
    private WebElement txtbx_AmenitiesGroundTransportationAmount;

    public final String xpath_AmenitiesGroundTransportationDirectBillRadioButton = "(//input[@name='groundTransportationFOP'])[1]";
    @FindBy(xpath= xpath_AmenitiesGroundTransportationDirectBillRadioButton )
    private WebElement rdbtn_AmenitiesGroundTransportationDirectBill;

    public final String xpath_AmenitiesGroundTransportationPCardRadioButton = "(//input[@name='groundTransportationFOP'])[2]";
    @FindBy(xpath= xpath_AmenitiesGroundTransportationPCardRadioButton )
    private WebElement rdbtn_AmenitiesGroundTransportationPCard;

    public final String xpath_AmenitiesGroundTransportationVoucherRadioButton = "(//input[@name='groundTransportationFOP'])[3]";
    @FindBy(xpath= xpath_AmenitiesGroundTransportationVoucherRadioButton )
    private WebElement rdbtn_AmenitiesGroundTransportationVoucher;

    public final String xpath_AmenitiesHotelNo = "//input[@name = 'hasHotelCompensation'][@value = '0']";
    @FindBy(xpath= xpath_AmenitiesHotelNo )
    private WebElement rdbtn_AmenitiesHotelNo;

    public final String xpath_AmenitiesHotelYes = "//input[@name = 'hasHotelCompensation'][@value = '1']";
    @FindBy(xpath= xpath_AmenitiesHotelYes )
    private WebElement rdbtn_AmenitiesHotelYes;


    public final String xpath_AmenitiesHotelAmountTextBox = "//label[contains(text() ,'Hotel Amount')]//following-sibling::input";
    @FindBy(xpath= xpath_AmenitiesHotelAmountTextBox )
    private WebElement txtbx_AmenitiesHotelAmount;

    public final String xpath_AmenitiesHotelDirectBillRadioButton = "(//input[@name='hotelFOP'])[1]";
    @FindBy(xpath= xpath_AmenitiesHotelDirectBillRadioButton )
    private WebElement rdbtn_AmenitiesHotelDirectBill;

    public final String xpath_AmenitiesHotelPCardRadioButton = "(//input[@name='hotelFOP'])[2]";
    @FindBy(xpath= xpath_AmenitiesHotelPCardRadioButton )
    private WebElement rdbtn_AmenitiesHotelPCard;

    public final String xpath_AmenitiesHotelVoucherRadioButton = "(//input[@name='hotelFOP'])[3]";
    @FindBy(xpath= xpath_AmenitiesHotelVoucherRadioButton )
    private WebElement rdbtn_AmenitiesHotelVoucher;

    //Bid Input/Update
    public final String xpath_BidInputUpdateSelectBid1 = "(//input[@name='selectBid'])[1]";
    @FindBy(xpath= xpath_BidInputUpdateSelectBid1 )
    private WebElement rdbtn__BidInputUpdateSelectBid1;

    public final String xpath_BidInputUpdateSelectBid2 = "(//input[@name='selectBid'])[2]";
    @FindBy(xpath= xpath_BidInputUpdateSelectBid2 )
    private WebElement rdbtn__BidInputUpdateSelectBid2;

    public final String xpath_BidInputUpdateSelectBid3 = "(//input[@name='selectBid'])[3]";
    @FindBy(xpath= xpath_BidInputUpdateSelectBid3 )
    private WebElement rdbtn__BidInputUpdateSelectBid3;

    public final String xpath_BidInputUpdateSelectBid4 = "(//input[@name='selectBid'])[4]";
    @FindBy(xpath= xpath_BidInputUpdateSelectBid4 )
    private WebElement rdbtn__BidInputUpdateSelectBid4;

    public final String xpath_BidInputUpdateSelectBidTextBox = "//input[@id = 'inputBid']";
    @FindBy(xpath= xpath_BidInputUpdateSelectBidTextBox )
    private WebElement txtbx__BidInputUpdateSelectBidTextBox;

    public final String xpath_BidInputUpdateSelectBidRemoveButton = "//b[contains(text(),'Save')]//parent::button";
    @FindBy(xpath= xpath_BidInputUpdateSelectBidRemoveButton )
    private WebElement btn__BidInputUpdateSelectBidRemove;

    public final String xpath_BidInputUpdateSelectBidSaveButton = "//b[contains(text(),'Remove')]//parent::button";
    @FindBy(xpath= xpath_BidInputUpdateSelectBidSaveButton )
    private WebElement btn__BidInputUpdateSelectBidSave;

    //Compensation & vouchers
    public final String xpath_CompensationAndVouchersNoRadioButton = "(//input[@name='HasCheck'])[1]";
    @FindBy(xpath= xpath_CompensationAndVouchersNoRadioButton )
    private WebElement rdbtn_CompensationAndVouchersNo;

    public final String xpath_CompensationAndVouchersYesRadioButton = "(//input[@name='HasCheck'])[2]";
    @FindBy(xpath= xpath_CompensationAndVouchersYesRadioButton )
    private WebElement rdbtn_CompensationAndVouchersYes;

    public final String xpath_CompensationAndVouchersCheckNumberTextBox = "//label[contains(text(),'Check Number')]//following-sibling::input";
    @FindBy(xpath= xpath_CompensationAndVouchersCheckNumberTextBox )
    private WebElement txtbx_CompensationAndVouchersCheckNumber;

    //Finalize Voucher or Compensation and voucher
    public final String xpath_FinalizeVoucherNoRadioButton = "//input[@name = 'hasVoucher'][@value = '0']";
    @FindBy(xpath= xpath_FinalizeVoucherNoRadioButton )
    private WebElement rdbtn_FinalizeVoucherNo;

    public final String xpath_FinalizeVoucherYesRadioButton = "//input[@name = 'hasVoucher'][@value = '1']";
    @FindBy(xpath= xpath_FinalizeVoucherYesRadioButton )
    private WebElement rdbtn_FinalizeVoucherYes;

    public final String xpath_FinalizeVoucherIssueVoucher = "//button[contains(text() ,'Issue Voucher')]";
    @FindBy(xpath= xpath_FinalizeVoucherIssueVoucher )
    private WebElement btn_FinalizeVoucherIssueVoucher;

    public final String xpath_FinalizeVoucherNumber= "//input[@placeholder = 'Voucher Number']";
    @FindBy(xpath= xpath_FinalizeVoucherNumber )
    private WebElement txt_FinalizeVoucherNumber;

    public final String xpath_FinalizeVoucherExpiration = "//input[@placeholder = 'dd MMM yy']";
    @FindBy(xpath= xpath_FinalizeVoucherExpiration )
    private WebElement txt_FinalizeVoucherExpiration;

    public final String xpath_FinalizeVoucherAmount=  "//input[@placeholder = 'Amount']";
    @FindBy(xpath= xpath_FinalizeVoucherAmount )
    private WebElement txt_FinalizeVoucherAmount;

    //Denied Boarding Passenger
    public final String xpath_DeniedPassengerNumberText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[1]";
    @FindBy(xpath= xpath_DeniedPassengerNumberText)
    private WebElement txt_DeniedPassengerNumber;

    public final String xpath_DeniedPassengerIDText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[2]";
    @FindBy(xpath= xpath_DeniedPassengerIDText)
    private WebElement txt_DeniedPassengerID;

    public final String xpath_DeniedPassengerBoardingStatusText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[3]";
    @FindBy(xpath= xpath_DeniedPassengerBoardingStatusText)
    private WebElement txt_DeniedPassengerBoardingStatus;

    public final String xpath_DeniedPassengerStandByText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[4]";
    @FindBy(xpath= xpath_DeniedPassengerStandByText)
    private WebElement txt_DeniedPassengerStandBy;

    public final String xpath_DeniedPassengerDBText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[5]";
    @FindBy(xpath= xpath_DeniedPassengerDBText)
    private WebElement txt_DeniedPassengerDB;

    public final String xpath_DeniedPassengerReasonText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[6]";
    @FindBy(xpath= xpath_DeniedPassengerReasonText)
    private WebElement txt_DeniedPassengerReason;

    public final String xpath_DeniedPassengerRebookedText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[7]";
    @FindBy(xpath= xpath_DeniedPassengerRebookedText)
    private WebElement txt_DeniedPassengerRebooked;

    public final String xpath_DeniedPassengerAirLineText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[8]";
    @FindBy(xpath= xpath_DeniedPassengerAirLineText)
    private WebElement txt_DeniedPassengerAirLine;

    public final String xpath_DeniedPassengerArrTimeFrameText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[9]";
    @FindBy(xpath= xpath_DeniedPassengerArrTimeFrameText)
    private WebElement txt_DeniedPassengerArrTimeFrame;

    public final String xpath_DeniedPassengerTicketAmtText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[10]";
    @FindBy(xpath= xpath_DeniedPassengerTicketAmtText)
    private WebElement txt_DeniedPassengerTicketAmt;

    public final String xpath_DeniedPassengerTicketArrTimeFrameText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[11]";
    @FindBy(xpath= xpath_DeniedPassengerTicketArrTimeFrameText)
    private WebElement txt_DeniedPassengerTicketArrTimeFrame;

    public final String xpath_DeniedPassengerRebookFOPText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[12]";
    @FindBy(xpath= xpath_DeniedPassengerRebookFOPText)
    private WebElement txt_DeniedPassengerRebookFOP;

    public final String xpath_DeniedPassengerDBCompText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[13]";
    @FindBy(xpath= xpath_DeniedPassengerDBCompText)
    private WebElement txt_DeniedPassengerDBComp;

    public final String xpath_DeniedPassengerFTVText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[14]";
    @FindBy(xpath= xpath_DeniedPassengerFTVText)
    private WebElement txt_DeniedPassengerFTV;

    public final String xpath_DeniedPassenger1FTVText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[15]";
    @FindBy(xpath= xpath_DeniedPassenger1FTVText)
    private WebElement txt_DeniedPassenger1FTV;

    public final String xpath_DeniedPassenger1FTVAmtText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[16]";
    @FindBy(xpath= xpath_DeniedPassenger1FTVAmtText)
    private WebElement txt_DeniedPassenger1FTVAmt;

    public final String xpath_DeniedPassengerBidFTVNumText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[17]";
    @FindBy(xpath= xpath_DeniedPassengerBidFTVNumText)
    private WebElement txt_DeniedPassengerBidFTVNum;

    public final String xpath_DeniedPassengerFoodText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[18]";
    @FindBy(xpath= xpath_DeniedPassengerFoodText)
    private WebElement txt_DeniedPassengerFood;


    public final String xpath_DeniedPassengerFAmtText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[19]";
    @FindBy(xpath= xpath_DeniedPassengerFAmtText)
    private WebElement txt_DeniedPassengerFAmt;


    public final String xpath_DeniedPassengerFFOPText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[20]";
    @FindBy(xpath= xpath_DeniedPassengerFFOPText)
    private WebElement txt_DeniedPassengerFFOP;


    public final String xpath_DeniedPassengerGT1Text = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[21]";
    @FindBy(xpath= xpath_DeniedPassengerGT1Text)
    private WebElement txt_DeniedPassengerGT1;

    public final String xpath_DeniedPassengerGTFOPText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[22]";
    @FindBy(xpath= xpath_DeniedPassengerGTFOPText)
    private WebElement txt_DeniedPassengerGTFOP;

    public final String xpath_DeniedPassengerHotelText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[23]";
    @FindBy(xpath= xpath_DeniedPassengerHotelText)
    private WebElement txt_DeniedPassengerHotel;

    public final String xpath_DeniedPassengerHAmtText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[24]";
    @FindBy(xpath= xpath_DeniedPassengerHAmtText)
    private WebElement txt_DeniedPassengerHAmt;

    public final String xpath_DeniedPassengerHFOPText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[25]";
    @FindBy(xpath= xpath_DeniedPassengerHFOPText)
    private WebElement txt_DeniedPassengerHFOP;

    public final String xpath_DeniedPassengerCompletedText = "//div[contains(@data-ng-show, 'deniedBoardingPaxes')]//tbody//td[26]";
    @FindBy(xpath= xpath_DeniedPassengerCompletedText)
    private WebElement txt_DeniedPassengerCompleted;

    //Getters
    //*******************************************************************
    //*************************Navigation Bar***************************
    //*******************************************************************
    public WebElement getDeniedBoardingToolButton() { return btn_DeniedBoardingTool; }

    public WebElement getDashboardButton() { return btn_Dashboard; }

    public WebElement getSummaryButton() { return btn_Summary; }

    public WebElement getAdminButton() { return btn_Admin; }
    //*******************************************************************
    //*************************Search DashBoard**************************
    //*******************************************************************
    public WebElement getSelectStationDropDown() { return drpdwn_SelectStation; }

    public WebElement getTodayRadioButton() { return rdbtn_Today; }

    public WebElement getYesterdayRadioButton() { return rdbtn_Yesterday; }

    public WebElement getDateRangeRadioButton() { return rdbtn_DateRange; }

    public WebElement getDateRangeStartTextBox() { return txtbox_DateRangeStart; }

    public WebElement getDateRangeEndTextBox() { return txtbox_DateRangeEnd; }

    public WebElement getSearchButton() { return btn_Search; }

    public WebElement getPageLoadToasterPanel() { return pnl_PageLoadedToaster; }

    public WebElement getFlightTableLoadSpinner() { return img_FlightTableLoadSpinner; }


    //************************************************
    //****Station Flight Table Data Methods***********
    //************************************************
    public List<WebElement> getTableFlightNumberButton() { return btn_TableFlightNumber; }

    public List<WebElement> getTableDateText() { return txt_TableDate; }

    public List<WebElement> getTableJourneyText() { return txt_TableJourney; }

    public List<WebElement> getTableSTDText() { return txt_TableSTD; }

    public List<WebElement> getTableFlightStatusText() { return txt_TableFlightStatus; }

    public List<WebElement> getTableYesDeniedBoardingRadioButton() { return rdbtn_TableYesDeniedBoarding; }

    public List<WebElement> getTableNoDeniedBoardingRadioButton() { return rdbtn_TableNoDeniedBoarding; }

    public List<WebElement> getTableBoardingStatusText() { return txt_TableBoardingStatus; }


    //************************************************
    //******Flight Reservation Table Methods**********
    //************************************************
    public List<WebElement> getManifestTablePaxNameText() { return txt_ManifestTablePaxName; }

    public List<WebElement> getManifestTablePNRText() { return txt_ManifestTablePNR; }

    public List<WebElement> getManifestTableJourneyText() { return txt_ManifestTableJourney; }

    public List<WebElement> getManifestTableBoardingStatusText() { return txt_ManifestTableBoardingStatus; }

    public List<WebElement> getManifestTableSeqNumText() { return txt_ManifestTableSeqNum; }

    public List<WebElement> getManifestTableDBStatusText() { return txt_ManifestTableDBStatus; }

    public List<WebElement> getManifestTableVolunteerBidButton() { return btn_ManifestTableVolunteerBid; }

    public List<WebElement> getManifestTableBidAcceptedText() { return txt_ManifestTableBidAccepted; }

    public WebElement getReservationPNRText() { return txt_ReservationPNR; }

    public WebElement getReservationFlightNumnberText() { return txt_ReservationFlightNumnber;}

    public WebElement getReservationDepartureText () { return txt_ReservationDeparture;}

    public WebElement getReservationArrivalText () { return txt_ReservationArrival;}

    public WebElement getReservationSTDText() { return txt_ReservationSTD;}

    public WebElement getReservationSTAText() { return txt_ReservationSTA;}

    public WebElement getReservationAircraftTailNumbeText() { return txt_ReservationAircraftTailNumber;}

    public WebElement getReservationJourneyText() { return txt_ReservationJourney;}

    public WebElement getReservationPassengerOnBookingText() { return txt_ReservationPassengerOnBooking;}

    public WebElement getDeniedBoardingTab() { return tab_DeniedBoarding;}

    public WebElement getRebookTab() { return tab_Rebook;}

    public WebElement getCompensationAndVouchersTab() { return tab_CompensationAndVouchers;}

    public WebElement getAmenitiesTab() { return tab_Amenities;}

    public WebElement getFinalizeVoucherTab() { return tab_FinalizeVoucher;}

    public WebElement getBidInputUpdateTab() { return tab_BidInputUpdate;}

    public WebElement getBoardingStatusText() { return txt_BoardingStatus;}

    public WebElement getDeniedBoardingStatusINVOLRadioButton() { return rdbtn_DeniedBoardingStatusINVOL;}

    public WebElement getDeniedBoardingStatusVOLRadioButton() { return rdbtn_DeniedBoardingStatusVOL;}

    public WebElement getDeniedBoardingReasonEquipmentDowngradeRadioButton() { return rdbtn_DeniedBoardingReasonEquipmentDowngrade;}

    public WebElement getDeniedBoardingReasonOverbookedRadioButton() { return rdbtn_DeniedBoardingReasonOverbooked;}

    public WebElement getDeniedBoardingReasonWeightRestrictionRadioButton() { return rdbtn_DeniedBoardingReasonWeightRestriction;}

    public WebElement getRebookNoRadioButton() { return rdbtn_RebookNo;}

    public WebElement getRebookYesRadioButton() { return rdbtn_RebookYes;}

    public WebElement getRebookWithNKRadioButton() { return rdbtn_RebookWithNK;}

    public WebElement getRebookWithOARadioButton() { return rdbtn_RebookWithOA;}

    public WebElement getRebookTwoLetterAirlineCodeTextBox() {return txtbx_RebookTwoLetterAirlineCode;}

    public WebElement getRebookTicketAmountTextBox() {return txtbx_RebookTicketAmount;}

    public WebElement getRebookFormOfPaymentITSMAXRadioButton() {return rdbtn_RebookFormOfPaymentITSMAX;}

    public WebElement getRebookFormOfPaymentPCardRadioButton() {return rdbtn_RebookFormOfPaymentPCardRadioButton;}

    public WebElement getArriveTimeFrame0to1HourRadioButton() { return rdbtn_ArriveTimeFrame0to1Hour;}

    public WebElement getArriveTimeFrame1to2HoursRadioButton() { return rdbtn_ArriveTimeFrame1to2Hours;}

    public WebElement getArriveTimeFrameMoreThan2HoursRadioButton() { return rdbtn_ArriveTimeFrameMoreThan2Hours;}

    public WebElement getAmenitiesFoodNoRadioButton() { return rdbtn_AmenitiesFoodNo;}

    public WebElement getAmenitiesFoodYesRadioButton() { return rdbtn_AmenitiesFoodYes;}

    public WebElement getAmenitiesFoodAmountTextBox() { return txtbx_AmenitiesFoodAmount;}

    public WebElement getAmenitiesFoodDirectBillRadioButton() { return rdbtn_AmenitiesFoodDirectBill;}

    public WebElement getAmenitiesFoodPCardRadioButton() {return rdbtn_AmenitiesFoodPCard;}

    public WebElement getAmenitiesFoodVoucherRadioButton() { return rdbtn_AmenitiesFoodVoucher;}

    public WebElement getAmenitiesGroundTransportationNoRadioButton() { return rdbtn_AmenitiesGroundTransportationNo;}

    public WebElement getAmenitiesGroundTransportationYesRadioButton() { return rdbtn_AmenitiesGroundTransportationYes;}

    public WebElement getAmenitiesGroundTransportationAmountTextBox() { return txtbx_AmenitiesGroundTransportationAmount;}

    public WebElement getAmenitiesGroundTransportationDirectBillRadioButton() { return rdbtn_AmenitiesGroundTransportationDirectBill;}

    public WebElement getAmenitiesGroundTransportationPCardRadioButton() { return rdbtn_AmenitiesGroundTransportationPCard;}

    public WebElement getAmenitiesGroundTransportationVoucherRadioButton() { return rdbtn_AmenitiesGroundTransportationVoucher;}

    public WebElement getAmenitiesHotelNoRadioButton() { return rdbtn_AmenitiesHotelNo;}

    public WebElement getAmenitiesHotelYesRadioButton() { return rdbtn_AmenitiesHotelYes;}

    public WebElement getAmenitiesHotelAmountTextBox() { return txtbx_AmenitiesHotelAmount;}

    public WebElement getAmenitiesHotelDirectBillRadioButton() { return rdbtn_AmenitiesHotelDirectBill;}

    public WebElement getAmenitiesHotelPCardRadioButton() { return rdbtn_AmenitiesHotelPCard;}

    public WebElement getAmenitiesHotelVoucherRadioButton() { return rdbtn_AmenitiesHotelVoucher;}

    public WebElement getBidInputUpdateSelectBid1RadioButton() { return rdbtn__BidInputUpdateSelectBid1;}

    public WebElement getBidInputUpdateSelectBid2RadioButton() { return rdbtn__BidInputUpdateSelectBid2;}

    public WebElement getBidInputUpdateSelectBid3RadioButton() { return rdbtn__BidInputUpdateSelectBid3;}

    public WebElement getBidInputUpdateSelectBid4RadioButton() { return rdbtn__BidInputUpdateSelectBid4;}

    public WebElement getBidInputUpdateSelectBidTextBox() { return txtbx__BidInputUpdateSelectBidTextBox;}

    public WebElement getBidInputUpdateSelectBidRemoveButton() { return btn__BidInputUpdateSelectBidRemove;}

    public WebElement getBidInputUpdateSelectBidSaveButton() { return btn__BidInputUpdateSelectBidSave;}

    public WebElement getCompensationAndVouchersYesRadioButton() { return rdbtn_CompensationAndVouchersYes;}

    public WebElement getCompensationAndVouchersNoRadioButton() { return rdbtn_CompensationAndVouchersNo;}

    public WebElement getCompensationAndVouchersCheckNumberTextbox() { return txtbx_CompensationAndVouchersCheckNumber;}

    public WebElement getFinalizeVoucherNoRadioButton() { return rdbtn_FinalizeVoucherNo;}

    public WebElement getFinalizeVoucherYesRadioButton() { return rdbtn_FinalizeVoucherYes;}

    public WebElement getFinalizeVoucherIssueVoucherButton() { return btn_FinalizeVoucherIssueVoucher;}

    public WebElement getFinalizeVoucherNumberText() { return txt_FinalizeVoucherNumber;}

    public WebElement getFinalizeVoucherExpirationText() { return txt_FinalizeVoucherExpiration;}

    public WebElement getFinalizeVoucherAmountText() { return txt_FinalizeVoucherAmount;}

    public WebElement getDeniedPassengerNumberText() { return txt_DeniedPassengerNumber;}

    public WebElement getDeniedPassengerIDText() { return txt_DeniedPassengerID;}

    public WebElement getDeniedPassengerBoardingStatusText() { return txt_DeniedPassengerBoardingStatus;}

    public WebElement getDeniedPassengerStandByText() { return txt_DeniedPassengerStandBy;}

    public WebElement getDeniedPassengerDBText() { return txt_DeniedPassengerDB;}

    public WebElement getDeniedPassengerReasonText() { return txt_DeniedPassengerReason;}

    public WebElement getDeniedPassengerRebookedText() { return txt_DeniedPassengerRebooked;}

    public WebElement getDeniedPassengerAirLineText() { return txt_DeniedPassengerAirLine;}

    public WebElement getDeniedPassengerArrTimeFrameText() { return txt_DeniedPassengerArrTimeFrame;}

    public WebElement getDeniedPassengerTicketAmtText() { return txt_DeniedPassengerTicketAmt;}

    public WebElement getDeniedPassengerTicketArrTimeFrameText() { return txt_DeniedPassengerTicketArrTimeFrame;}

    public WebElement getDeniedPassengerRebookFOPText() { return txt_DeniedPassengerRebookFOP;}

    public WebElement getDeniedPassengerDBCompText() { return txt_DeniedPassengerDBComp;}

    public WebElement getDeniedPassengerFTVText() { return txt_DeniedPassengerFTV;}

    public WebElement getDeniedPassenger1FTVText() { return txt_DeniedPassenger1FTV;}

    public WebElement getDeniedPassenger1FTVAmtText() { return txt_DeniedPassenger1FTVAmt;}

    public WebElement getDeniedPassengerBidFTVNumText() { return txt_DeniedPassengerBidFTVNum;}

    public WebElement getDeniedPassengerFoodText() { return txt_DeniedPassengerFood;}

    public WebElement getDeniedPassengerFAmtText() { return txt_DeniedPassengerFAmt;}

    public WebElement getDeniedPassengerFFOPText() { return txt_DeniedPassengerFFOP;}

    public WebElement getDeniedPassengerGT1Text() { return txt_DeniedPassengerGT1;}

    public WebElement getDeniedPassengerGTFOPText() { return txt_DeniedPassengerGTFOP;}

    public WebElement getDeniedPassengerHotelText() { return txt_DeniedPassengerHotel;}

    public WebElement getDeniedPassengerHAmtText() { return txt_DeniedPassengerHAmt;}

    public WebElement getDeniedPassengerHFOPText() { return txt_DeniedPassengerHFOP;}

    public WebElement getDeniedPassengerCompletedText() { return txt_DeniedPassengerCompleted;}
}
