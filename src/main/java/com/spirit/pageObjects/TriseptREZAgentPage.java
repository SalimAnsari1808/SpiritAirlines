package com.spirit.pageObjects;


import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
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

public class TriseptREZAgentPage {

    public TriseptREZAgentPage(WebDriver driver) {
        //this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //*******************************************
    //***************Log In Page*****************
    //*******************************************
    public final String xpath_UserNameTextBox = "//input[contains(@id,'LoginCtrl_UserName')]";
    @FindBy(xpath=xpath_UserNameTextBox)
    private WebElement txtbx_UserName;

    public final String xpath_PasswordTextBox = "//input[contains(@id,'LoginCtrl_Password')]";
    @FindBy(xpath=xpath_PasswordTextBox)
    private WebElement txtbx_Password;

    public final String xpath_SignInButton = "//input[contains(@id,'LoginCtrl_LoginButton')]";
    @FindBy(xpath=xpath_SignInButton)
    private WebElement btn_SignIn;

    //********************************************
    //*************Header Retrieve Button*********
    //********************************************
    public final String xpath_NewClientHeaderTabButton = "//ul[@class='member-actions']//a[contains(text(),'New Client')]";
    @FindBy(xpath=xpath_NewClientHeaderTabButton)
    private WebElement btn_NewClientHeaderTab;

    public final String xpath_RetrieveReservationHeaderTabButton = "//ul[@class='member-actions']//a[contains(text(),'Retrieve Reservation')]";
    @FindBy(xpath=xpath_RetrieveReservationHeaderTabButton)
    private WebElement btn_RetrieveReservationHeaderTab;

    public final String xpath_RetrieveGroupHeaderTabButton = "//ul[@class='member-actions']//a[contains(text(),'Retrieve Group')]";
    @FindBy(xpath=xpath_RetrieveGroupHeaderTabButton)
    private WebElement btn_RetrieveGroupHeaderTab;

    public final String xpath_TripBookmarksHeaderTabButton = "//ul[@class='member-actions']//a[contains(text(),'Trip Bookmarks')]";
    @FindBy(xpath=xpath_TripBookmarksHeaderTabButton)
    private WebElement btn_TripBookmarksHeaderTab;

    public final String xpath_AdministrativeToolsHeaderTabButton = "//ul[@class='member-actions']//a[contains(text(),'Administrative Tools')]";
    @FindBy(xpath=xpath_AdministrativeToolsHeaderTabButton)
    private WebElement btn_AdministrativeToolsHeaderTab;

    public final String xpath_LogOutHeaderTabButton = "//ul[@class='member-actions']//a[contains(text(),'Log Out')]";
    @FindBy(xpath=xpath_LogOutHeaderTabButton)
    private WebElement btn_LogOutHeaderTab;

    //********************************************
    //*****************Bookings Page**************
    //********************************************
    public final String xpath_RetrieveByReservationNumberTextBox= "//input[contains(@id,'ArcRetrievalComponent_ResNumberInput')]";
    @FindBy(xpath=xpath_RetrieveByReservationNumberTextBox)
    private WebElement txtbx_RetrieveByReservationNumber;

    public final String xpath_RetrievePassengerLastNameFirstTextBox= "//input[contains(@id,'ArcRetrievalComponent_NameInput')]";
    @FindBy(xpath=xpath_RetrievePassengerLastNameFirstTextBox)
    private WebElement txtbx_RetrievePassengerLastNameFirst;

    public final String xpath_RetrieveReservationButton= "//input[contains(@id,'ArcRetrievalComponent_RetrieveReservation')]";
    @FindBy(xpath=xpath_RetrieveReservationButton)
    private WebElement btn_RetrieveReservation;

    //***********************************************************************************************************
    //**************************************************Carnet Website*******************************************
    //***********************************************************************************************************

    //*******************************************
    //***************Log In Page*****************
    //*******************************************
    public final String xpath_CarnetUserNameTextBox = "//input[@id='loginEmail']";
    @FindBy(xpath=xpath_CarnetUserNameTextBox)
    private WebElement txtbx_CarnetUserName;

    public final String xpath_CarnetPasswordTextBox = "//input[@id='loginPassword']";
    @FindBy(xpath=xpath_CarnetPasswordTextBox)
    private WebElement txtbx_CarnetPassword;

    public final String xpath_CarnetSignInButton = "//button[contains(text(),'Sign In')]";
    @FindBy(xpath=xpath_CarnetSignInButton)
    private WebElement btn_CarnetSignIn;

    //*******************************************
    //***************Home Page******************
    //*******************************************
    public final String xpath_CarnetAvailabilitySearchLink = "//a[contains(text(),'Availability Search') and @class='header-navigation-link']";
    @FindBy(xpath=xpath_CarnetAvailabilitySearchLink)
    private WebElement lnk_CarnetAvailabilitySearch;

    public final String xpath_CarnetRetrieveLink = "//a[contains(text(),'Retrieve') and @class='header-navigation-link']";
    @FindBy(xpath=xpath_CarnetRetrieveLink)
    private WebElement lnk_CarnetRetrieve;

    public final String xpath_CarnetContactUsLink = "//a[contains(text(),'Contact us') and @class='header-navigation-link']";
    @FindBy(xpath=xpath_CarnetContactUsLink)
    private WebElement lnk_CarnetContactUs;

    public final String xpath_CarnetPickUpLocationTextBox = "//input[@id='pickup-location-select-1']";
    @FindBy(xpath=xpath_CarnetPickUpLocationTextBox)
    private WebElement txtbx_CarnetPickUpLocation;

    public final String xpath_CarnetPickUpLocationText = "//span[@class='location-label']";
    @FindBy(xpath=xpath_CarnetPickUpLocationText)
    private List<WebElement> txt_CarnetPickUpLocation;

    public final String xpath_CarnetPickUpDateTextBox = "//input[@id='pickup-date-1']";
    @FindBy(xpath=xpath_CarnetPickUpDateTextBox)
    private WebElement txtbx_CarnetPickUpDate;

    public final String xpath_CarnetDropOffDateTextBox = "//input[@id='dropoff-date-1']";
    @FindBy(xpath=xpath_CarnetDropOffDateTextBox)
    private WebElement txtbx_CarnetDropOffDate;

    public final String xpath_CarnetCalenderDatesText = "//span[contains(@class,'datepicker-selectable-date')]";
    @FindBy(xpath=xpath_CarnetCalenderDatesText)
    private List<WebElement> txt_CarnetCalenderDates;

    public final String xpath_CarnetDriverAgeLabel = "//label[@for='have-optional-1']";
    @FindBy(xpath=xpath_CarnetDriverAgeLabel)
    private WebElement lbl_CarnetDriverAge;

    public final String xpath_CarnetDriverAgeTextBox = "//input[@id='AGE-1']";
    @FindBy(xpath=xpath_CarnetDriverAgeTextBox)
    private WebElement txtbx_CarnetDriverAge;

    public final String xpath_CarnetCountryLabel = "//label[@for='different-residence']";
    @FindBy(xpath=xpath_CarnetCountryLabel)
    private WebElement lbl_CarnetCountry;

    public final String xpath_CarnetCountryDropDown = "//select[@id='smarket-1']";
    @FindBy(xpath=xpath_CarnetCountryDropDown)
    private WebElement drpdn_CarnetCountry;

    public final String xpath_CarnetSearchButton = "//input[@class='submit-button']";
    @FindBy(xpath=xpath_CarnetSearchButton)
    private WebElement btn_CarnetSearch;

    public final String xpath_CarnetRetrieveByReferenceTextBox = "//input[@id='retrieveByReference']";
    @FindBy(xpath=xpath_CarnetRetrieveByReferenceTextBox)
    private WebElement txtbx_CarnetRetrieveByReference;

    public final String xpath_CarnetRetrieveByAffiliateTextBox = "//ng-select[@id='dropDownAffiliates']//input";
    @FindBy(xpath=xpath_CarnetRetrieveByAffiliateTextBox)
    private WebElement txtbx_CarnetRetrieveByAffiliate;

    public final String xpath_CarnetRetrieveByAffiliateText= "//span[text()='Spirit Airlines API']";
    @FindBy(xpath=xpath_CarnetRetrieveByAffiliateText)
    private WebElement txt_CarnetRetrieveByAffiliate;

    public final String xpath_CarnetRetrieveByReservationStateTextBox = "//ng-select[@id='dropDownReservationState']//input";
    @FindBy(xpath=xpath_CarnetRetrieveByReservationStateTextBox)
    private WebElement txtbx_CarnetRetrieveByReservationState;

    public final String xpath_CarnetRetrieveByFamilyNameText = "//input[@id='retrieveByFamilyName']";
    @FindBy(xpath=xpath_CarnetRetrieveByFamilyNameText)
    private WebElement txt_CarnetRetrieveByFamilyName;

    public final String xpath_CarnetRetrieveByReservationStateText = "//div[@role='option']/span[text()='Confirmed']";
    @FindBy(xpath=xpath_CarnetRetrieveByReservationStateText)
    private WebElement txt_CarnetRetrieveByReservationState;

    public final String xpath_CarnetRetrieveDateRangeTextBox = "//input[@name='dateRangePicker']";
    @FindBy(xpath=xpath_CarnetRetrieveDateRangeTextBox)
    private WebElement txtbx_CarnetRetrieveDateRange;

    public final String xpath_CarnetRetrieveReservationSubmitButton = "//button[@id='retrieve-reservation-form-submit-button']";
    @FindBy(xpath=xpath_CarnetRetrieveReservationSubmitButton)
    private WebElement btn_CarnetRetrieveReservationSubmit;

    public final String xpath_CarnetRetrieveResultsStatusText = "//td[@class='retrieve-results-state']/span";
    @FindBy(xpath=xpath_CarnetRetrieveResultsStatusText)
    private List<WebElement> txt_CarnetRetrieveResultsStatus;

    public final String xpath_CarnetRetrieveResultsDetailsText = "//td[@id='retrieve-results-details']";
    @FindBy(xpath=xpath_CarnetRetrieveResultsDetailsText)
    private List<WebElement> txt_CarnetRetrieveResultsDetails;

    public final String xpath_CarnetRetrieveResultsPassengerReservationNameText = "//td[@id='retrieve-results-details']/p[@class='bold small']";
    @FindBy(xpath=xpath_CarnetRetrieveResultsPassengerReservationNameText)
    private List<WebElement> txt_CarnetRetrieveResultsPassengerReservationName;

    public final String xpath_CarnetRetrieveResultsPaginationText = "//li[contains(@class,'pagination-next')]";
    @FindBy(xpath=xpath_CarnetRetrieveResultsPaginationText)
    private WebElement txt_CarnetRetrieveResultsPagination;

    public final String xpath_CarnetCancelReservationButton = "//button[contains(@id,'btnCancelReservation')]";
//    public final String xpath_CarnetCancelReservationButton = "//button[@id='btnCancelReservation']";
    @FindBy(xpath=xpath_CarnetCancelReservationButton)
    private WebElement btn_CarnetCancelReservation;

    public final String xpath_CarnetConfirmYesButton = "//div[@id='btn-notification-confirm-yes']";
    @FindBy(xpath=xpath_CarnetConfirmYesButton)
    private WebElement btn_CarnetConfirmYes;

    public final String xpath_CarnetCancelConfirmationText = "//div[contains(@class,'booking-header-state')]";
    @FindBy(xpath=xpath_CarnetCancelConfirmationText)
    private WebElement txt_CarnetCancelConfirmation;

    //*****************Car Details****************

    //*************************************************
    //*****************Car Header Panel****************
    //*************************************************
    public final String xpath_CarnetCarDetailNameText = "//div[@data-component='rate-card']//div[@class='car-inner-panel']//h3[@class='car-header-text']";
    @FindBy(xpath=xpath_CarnetCarDetailNameText)
    private List<WebElement> txt_CarnetCarDetailName;

    public final String xpath_CarnetCarDetailVendorNameText = "//div[@data-component='rate-card']//div[@class='car-inner-panel']//span[@class='vendor-image']/img";
    @FindBy(xpath = xpath_CarnetCarDetailVendorNameText)
    private List<WebElement> txt_CarnetCarDetailVendorName;

    //*************************************************
    //*****************Car Front Panel*****************
    //*************************************************
    public final String xpath_CarnetCarDetailCarImage  = "//div[@data-component='rate-card']//div[@class='car-inner-panel']//span[@class='car-image']";
    @FindBy(xpath=xpath_CarnetCarDetailCarImage)
    private List<WebElement> img_CarnetCarDetailCarImage;

    public final String xpath_CarnetCarDetailsTypeText = "//div[@data-component='rate-card']//div[@class='car-inner-panel']//div[@class='car-acriss']";
    @FindBy(xpath=xpath_CarnetCarDetailsTypeText)
    private List<WebElement> txt_CarnetCarDetailsType;

    public final String xpath_CarnetCarDetailsMilesText = "//div[@class='car-marketing']/div[2]//span/following-sibling::div";
    @FindBy(xpath=xpath_CarnetCarDetailsMilesText)
    private List<WebElement> txt_CarnetCarDetailsMiles;

    public final String xpath_CarnetCarDetailsTransmissionText = "//div[@data-component='rate-card']//div[@class='car-inner-panel']//i[contains(@class,'icon-transmission-solid')]/..";
    @FindBy(xpath=xpath_CarnetCarDetailsTransmissionText)
    private List<WebElement> txt_CarnetCarDetailsTransmission;

    public final String xpath_CarnetCarDetailsBagsText = "//div[@data-component='rate-card']//div[@class='car-inner-panel']//i[contains(@class,'icon-bags-solid')]/..";
    @FindBy(xpath=xpath_CarnetCarDetailsBagsText)
    private List<WebElement> txt_CarnetCarDetailsBags;

    public final String xpath_CarnetCarDetailsPassengersText = "//i[contains(@class,'icon-passengers-solid')]/..";
    @FindBy(xpath=xpath_CarnetCarDetailsPassengersText)
    private List<WebElement> txt_CarnetCarDetailsPassengers;

    public final String xpath_CarnetCarDetailsPriceText = "//span[contains(@class,'base-price')]";
    @FindBy(xpath=xpath_CarnetCarDetailsPriceText)
    private List<WebElement> txt_CarnetCarDetailsPrice;

    //*************************************************
    //*****************Car Back Panel******************
    //*************************************************
    public final String xpath_CarnetCarDetailFullDetailLink = "//div[@data-component='rate-card']//div[@class='back-panel-buttons']//a[contains(@class,'back-panel-more-details')]";
    @FindBy(xpath=xpath_CarnetCarDetailFullDetailLink)
    private List<WebElement> lnk_CarnetCarDetailFullDetail;


    //*************************************************
    //*****************Car Full Detail*****************
    //*************************************************
    public final String xpath_CarnetCarFullDetailLocationLink = "//div[@class='full-car-details']//span[contains(text(),'Location')]";
    @FindBy(xpath=xpath_CarnetCarFullDetailLocationLink)
    private WebElement lnk_CarnetCarFullDetailLocation;

    public final String xpath_CarnetCarFullDetailLocationAddressText = "//div[@class='full-car-details']//div[@class='more-section-address-line']";
    @FindBy(xpath=xpath_CarnetCarFullDetailLocationAddressText)
    private List<WebElement> txt_CarnetCarFullDetailLocationAddress;

    public final String xpath_CarnetCarFullDetailLocationOpeningDaysText = "//div[@class='full-car-details']//div[@class='opening-day']";
    @FindBy(xpath=xpath_CarnetCarFullDetailLocationOpeningDaysText)
    private List<WebElement> txt_CarnetCarFullDetailLocationOpeningDays;

    public final String xpath_CarnetCarFullDetailLocationOpeningTimeText = "//div[@class='full-car-details']//div[@class='opening-time']";
    @FindBy(xpath=xpath_CarnetCarFullDetailLocationOpeningTimeText)
    private List<WebElement> txt_CarnetCarFullDetailLocationOpeningTime;


    /****************Footer*************************/
    public final String xpath_CarnetCarShowMoreLink = "//a[contains(text(),'SHOW MORE')]";
    @FindBy(xpath=xpath_CarnetCarShowMoreLink)
    private WebElement lnk_CarnetCarShowMore;

    //***********************************************************************************************************
    //***********************************************HotelBeds Website*******************************************
    //***********************************************************************************************************

    //*******************************************
    //***************Log In Page*****************
    //*******************************************
    public final String xpath_HBGUserNameTextBox = "//input[@id='username']";
    @FindBy(xpath=xpath_HBGUserNameTextBox)
    private WebElement txtbx_HBGUserName;

    public final String xpath_HBGPasswordTextBox = "//input[@id='password']";
    @FindBy(xpath=xpath_HBGPasswordTextBox)
    private WebElement txtbx_HBGPassword;

    public final String xpath_HBGLoginButton = "//button[@data-qa='login-submit']";
    @FindBy(xpath=xpath_HBGLoginButton)
    private WebElement btn_Login;

    //*******************************************
    //***************Home Page*******************
    //*******************************************
    public final String xpath_HBGHeaderBookingLink = "//a[@id='mybookingsprivate']";
    @FindBy(xpath=xpath_HBGHeaderBookingLink)
    private WebElement lnk_HBGHeaderBooking;

    public final String xpath_HBGBookingDateTypeTextBox ="//select[@id='tipoFecha']";
    @FindBy(xpath=xpath_HBGBookingDateTypeTextBox)
    private WebElement txtbx_HBGBookingDateType;

    public final String xpath_HBGBookingDateFromTextBox = "//input[@id='dateFrom']";
    @FindBy(xpath=xpath_HBGBookingDateFromTextBox)
    private WebElement txtbx_HBGBookingDateFrom;

    public final String xpath_HBGBookingDateToTextBox = "//input[@id='dateTo']";
    @FindBy(xpath=xpath_HBGBookingDateToTextBox)
    private WebElement txtbx_HBGBookingDateTo;

    public final String xpath_HBGBookingDestinationTextBox = "//input[@id='s-destination-search']";
    @FindBy(xpath=xpath_HBGBookingDestinationTextBox)
    private WebElement txtbx_HBGBookingDestination;

    public final String xpath_HBGBookingStatusDropDown = "//select[@id='bookingStatus']";
    @FindBy(xpath=xpath_HBGBookingStatusDropDown)
    private WebElement drpdn_HBGBookingStatus;

    public final String xpath_HBGBookingSearchButton = "//input[@id='bookingSearchBtn']";
    @FindBy(xpath=xpath_HBGBookingSearchButton)
    private WebElement btn_HBGBookingSearch;

    public final String xpath_HBGRetrieveBookingNameText = "//input[@name='selected_bookings']/../following-sibling::div[2]/div[2]";
    @FindBy(xpath=xpath_HBGRetrieveBookingNameText)
    private List<WebElement> txt_HBGRetrieveBookingName;

    public final String xpath_HBGRetrieveBookingIDText = "//div[contains(@class,'booking-id')]";
    @FindBy(xpath=xpath_HBGRetrieveBookingIDText)
    private List<WebElement> txt_HBGRetrieveBookingID;

    public final String xpath_HBGRetrieveBookingCancelAllText = "//a[@data-qa='cancel-all']";
    @FindBy(xpath=xpath_HBGRetrieveBookingCancelAllText)
    private List<WebElement> txt_HBGRetrieveBookingCancelAll;

    public final String xpath_HBGConfirmationCodeTextBox = "//input[@id='s-locata']";
    @FindBy(xpath=xpath_HBGConfirmationCodeTextBox)
    private WebElement txtbx_HBGConfirmationCode;

    public final String xpath_HBGConfirmationCodeSearchButton = "//input[@id='bookingNumberBtn']";
    @FindBy(xpath=xpath_HBGConfirmationCodeSearchButton)
    private WebElement btn_HBGConfirmationCodeSearch;

    public final String xpath_HBGSelectedHotelNameText = "//div[@data-type='HOTEL']";
    @FindBy(xpath=xpath_HBGSelectedHotelNameText)
    private WebElement txt_HBGSelectedHotelName;

    public final String xpath_HBGSelectedHotelCancelLink = "//a[@data-qa='cancel-service']";
    @FindBy(xpath=xpath_HBGSelectedHotelCancelLink)
    private WebElement lnk_HBGSelectedHotelCancel;

    public final String xpath_HBGSelectedHotelModifyLink = "//a[@data-qa='modify-service']";
    @FindBy(xpath=xpath_HBGSelectedHotelModifyLink)
    private WebElement lnk_HBGSelectedHotelModify;

    public final String xpath_HBGSelectedHotelVoucherLink = "//a[@data-tl='booking_product_voucher']";
    @FindBy(xpath=xpath_HBGSelectedHotelVoucherLink)
    private WebElement lnk_HBGSelectedHotelVoucher;

    public final String xpath_HBGSelectedHotelBookingModificationIDText = "//h4[@id='locator']";
    @FindBy(xpath=xpath_HBGSelectedHotelBookingModificationIDText)
    private WebElement txt_HBGSelectedHotelBookingModificationID;

    public final String xpath_HBGSelectedHotelAcceptCancellationCheckBox = "//label[contains(text(),'Yes, I want to cancel this booking ') or contains(text(),'Yes, I wish cancel this service ')]/../input";
    @FindBy(xpath=xpath_HBGSelectedHotelAcceptCancellationCheckBox)
    private WebElement chkbx_HBGSelectedHotelAcceptCancellation;

    public final String xpath_HBGSelectedHotelCancelServiceButton = "//input[@value='Cancel booking' or @value='Cancel service']";
    @FindBy(xpath=xpath_HBGSelectedHotelCancelServiceButton)
    private WebElement btn_HBGSelectedHotelCancelService;

    public final String xpath_HBGSelectedHotelCancelReasonRadioButton = "//input[@name='cancellation-reason']/..";
    @FindBy(xpath=xpath_HBGSelectedHotelCancelReasonRadioButton)
    private List<WebElement> rdbtn_HBGSelectedHotelCancelReason;

    public final String xpath_HBGSelectedHotelConfirmCancelButton = "//button[@id='btnConfirmCancel']";
    @FindBy(xpath=xpath_HBGSelectedHotelConfirmCancelButton)
    private WebElement btn_HBGSelectedHotelConfirmCancel;

    public final String xpath_HBGSelectedHotelCancelConfirmationMessageText = "//ul[@class='successlist']/li/strong";
    @FindBy(xpath=xpath_HBGSelectedHotelCancelConfirmationMessageText)
    private WebElement txt_HBGSelectedHotelCancelConfirmationMessage;

    public final String xpath_HBGSelectedHotelPaginationNextButton = "//*[contains(@id,'next_') or (@class='next disabled')]";
    @FindBy(xpath=xpath_HBGSelectedHotelPaginationNextButton)
    private WebElement btn_HBGSelectedHotelPaginationNext;

    //***********************************************************************************************************
    //***************************Start of Methods of Reservation Summary Page************************************
    //***********************************************************************************************************

    //*******************************************
    //***************Log In Page*****************
    //*******************************************
    public WebElement getUserNameTextBox() {
        return txtbx_UserName;
    }
    public WebElement getPasswordTextBox() {
        return txtbx_Password;
    }
    public WebElement getSignInButton() {
        return btn_SignIn;
    }

    //********************************************
    //*************Header Retrieve Button*********
    //********************************************
    public WebElement getNewClientHeaderTabButton() {
        return btn_NewClientHeaderTab;
    }

    public WebElement getRetrieveReservationHeaderTabButton() {
        return btn_RetrieveReservationHeaderTab;
    }

    public WebElement getRetrieveGroupHeaderTabButton() {
        return btn_RetrieveGroupHeaderTab;
    }

    public WebElement getTripBookmarksHeaderTabButton() {
        return btn_TripBookmarksHeaderTab;
    }

    public WebElement getAdministrativeToolsHeaderTabButton() {
        return btn_AdministrativeToolsHeaderTab;
    }

    public WebElement getLogOutHeaderTabButton() {
        return btn_LogOutHeaderTab;
    }

    //********************************************
    //*****************Bookings Page**************
    //********************************************
    public WebElement getRetrieveByReservationNumberTextBox() {
        return txtbx_RetrieveByReservationNumber;
    }
    public WebElement getRetrievePassengerLastNameFirstTextBox() {
        return txtbx_RetrievePassengerLastNameFirst;
    }
    public WebElement getRetrieveReservationButton() {
        return btn_RetrieveReservation;
    }


    //***********************************************************************************************************
    //**************************************************Carnet Website*******************************************
    //***********************************************************************************************************

    //*******************************************
    //***************Log In Page*****************
    //*******************************************
    public WebElement getCarnetUserNameTextBox() {
        return txtbx_CarnetUserName;
    }

    public WebElement getCarnetPasswordTextBox() {
        return txtbx_CarnetPassword;
    }

    public WebElement getCarnetSignInButton() {
        return btn_CarnetSignIn;
    }

    //*******************************************
    //***************Home Page******************
    //*******************************************
    public WebElement getCarnetAvailabilitySearchLink() {
        return lnk_CarnetAvailabilitySearch;
    }

    public WebElement getCarnetRetrieveLink() {
        return lnk_CarnetRetrieve;
    }

    public WebElement getCarnetContactUsLink() {
        return lnk_CarnetContactUs;
    }

    public WebElement getCarnetPickUpLocationTextBox() {
        return txtbx_CarnetPickUpLocation;
    }

    public List<WebElement> getCarnetPickUpLocationText() {
        return txt_CarnetPickUpLocation;
    }



    public WebElement getCarnetPickUpDateTextBox() {
        return txtbx_CarnetPickUpDate;
    }

    public WebElement getCarnetDropOffDateTextBox() {
        return txtbx_CarnetDropOffDate;
    }

    public List<WebElement> getCarnetCalenderDatesText() {
        return txt_CarnetCalenderDates;
    }

    public WebElement getCarnetDriverAgeLabel() {
        return lbl_CarnetDriverAge;
    }

    public WebElement getCarnetDriverAgeTextBox() {
        return txtbx_CarnetDriverAge;
    }

    public WebElement getCarnetCountryLabel() {
        return lbl_CarnetCountry;
    }

    public WebElement getCarnetCountryDropDown() {
        return drpdn_CarnetCountry;
    }

    public WebElement getCarnetSearchButton () {
        return btn_CarnetSearch;
    }

    public WebElement getCarnetRetrieveByReferenceTextBox() {
        return txtbx_CarnetRetrieveByReference;
    }

    public WebElement getCarnetRetrieveByAffiliateTextBox(){
        return txtbx_CarnetRetrieveByAffiliate;
    }


    public WebElement getCarnetRetrieveByAffiliateText(){
        return txt_CarnetRetrieveByAffiliate;
    }

    public WebElement getCarnetRetrieveByReservationStateTextBox(){
        return txtbx_CarnetRetrieveByReservationState;
    }

    public WebElement getCarnetRetrieveByFamilyNameText(){
        return txt_CarnetRetrieveByFamilyName;
    }

    public WebElement getCarnetRetrieveByReservationStateText(){
        return txt_CarnetRetrieveByReservationState;
    }

    public WebElement getCarnetRetrieveDateRangeTextBox(){
        return txtbx_CarnetRetrieveDateRange;
    }

    public WebElement getCarnetRetrieveReservationSubmitButton() {
        return btn_CarnetRetrieveReservationSubmit;
    }

    public List<WebElement> getCarnetRetrieveResultsStatusText(){
        return txt_CarnetRetrieveResultsStatus;
    }


    public List<WebElement> getCarnetRetrieveResultsDetailsText() {
        return txt_CarnetRetrieveResultsDetails;
    }

    public List<WebElement> getCarnetRetrieveResultsPassengerReservationNameText(){
        return txt_CarnetRetrieveResultsPassengerReservationName;
    }

    public WebElement getCarnetRetrieveResultsPaginationText(){
        return txt_CarnetRetrieveResultsPagination;
    }

    public WebElement getCarnetCancelReservationButton() {
        return btn_CarnetCancelReservation;
    }

    public WebElement getCarnetConfirmYesButton() {
        return btn_CarnetConfirmYes;
    }

    public WebElement getCarnetCancelConfirmationText() {
        return txt_CarnetCancelConfirmation;
    }

    //*****************Car Details****************
    public List<WebElement> getCarnetCarDetailNameText(){
        return txt_CarnetCarDetailName;
    }

    public List<WebElement> getCarnetCarDetailVendorNameText(){
        return txt_CarnetCarDetailVendorName;
    }


    public List<WebElement> getCarnetCarDetailCarImage(){
        return img_CarnetCarDetailCarImage;
    }

    public List<WebElement> getCarnetCarDetailsTypeText(){
        return txt_CarnetCarDetailsType;
    }

    public List<WebElement> getCarnetCarDetailsMilesText(){
        return txt_CarnetCarDetailsMiles;
    }

    public List<WebElement> getCarnetCarDetailsTransmissionText(){
        return txt_CarnetCarDetailsTransmission;
    }

    public List<WebElement> getCarnetCarDetailsBagsText(){
        return txt_CarnetCarDetailsBags;
    }

    public List<WebElement> getCarnetCarDetailsPassengersText(){
        return txt_CarnetCarDetailsPassengers;
    }

    public List<WebElement> getCarnetCarDetailsPriceText(){
        return txt_CarnetCarDetailsPrice;
    }


    //*************************************************
    //*****************Car Back Panel******************
    //*************************************************
    public List<WebElement> getCarnetCarDetailFullDetailLink(){
        return lnk_CarnetCarDetailFullDetail;
    }

    //*************************************************
    //*****************Car Full Detail*****************
    //*************************************************
    public WebElement getCarnetCarFullDetailLocationLink(){
        return lnk_CarnetCarFullDetailLocation;
    }

    public List<WebElement> getCarnetCarFullDetailLocationAddressText(){
        return txt_CarnetCarFullDetailLocationAddress;
    }

    public List<WebElement> getCarnetCarFullDetailLocationOpeningDaysText(){
        return txt_CarnetCarFullDetailLocationOpeningDays;
    }

    public List<WebElement> getCarnetCarFullDetailLocationOpeningTimeText(){
        return txt_CarnetCarFullDetailLocationOpeningTime;
    }


    public WebElement getCarnetCarShowMoreLink(){
        return lnk_CarnetCarShowMore;
    }

    //***********************************************************************************************************
    //***********************************************HotelBeds Website*******************************************
    //***********************************************************************************************************

    //*******************************************
    //***************Log In Page*****************
    //*******************************************
    public WebElement getHBGUserNameTextBox(){
        return txtbx_HBGUserName;
    }

    public WebElement getHBGPasswordTextBox(){
        return txtbx_HBGPassword;
    }

    public WebElement getHBGLoginButton(){
        return btn_Login;
    }

    //*******************************************
    //***************Home Page*******************
    //*******************************************
    public WebElement getHBGHeaderBookingLink(){
        return lnk_HBGHeaderBooking;
    }

    public WebElement getHBGBookingDateTypeTextBox(){
        return txtbx_HBGBookingDateType;
    }

    public WebElement getHBGBookingDateFromTextBox(){
        return txtbx_HBGBookingDateFrom;
    }

    public WebElement getHBGBookingDateToTextBox(){
        return txtbx_HBGBookingDateTo;
    }

    public WebElement getHBGBookingDestinationTextBox(){
        return txtbx_HBGBookingDestination;
    }

    public WebElement getHBGBookingStatusDropDown(){
        return drpdn_HBGBookingStatus;
    }

    public WebElement getHBGBookingSearchButton(){
        return btn_HBGBookingSearch;
    }

    public List<WebElement> getHBGRetrieveBookingNameText(){
        return txt_HBGRetrieveBookingName;

    }

    public List<WebElement> getHBGRetrieveBookingIDText(){
        return txt_HBGRetrieveBookingID;
    }

    public List<WebElement> getHBGRetrieveBookingCancelAllText(){
        return txt_HBGRetrieveBookingCancelAll;

    }

    public WebElement getHBGConfirmationCodeTextBox(){
        return txtbx_HBGConfirmationCode;
    }

    public WebElement getHBGConfirmationCodeSearchButton(){
        return btn_HBGConfirmationCodeSearch;
    }

    public WebElement getHBGSelectedHotelNameText(){
        return txt_HBGSelectedHotelName;
    }

    public WebElement getHBGSelectedHotelCancelLink(){
        return lnk_HBGSelectedHotelCancel;
    }

    public WebElement getHBGSelectedHotelModifyLink(){
        return lnk_HBGSelectedHotelModify;
    }

    public WebElement getHBGSelectedHotelVoucherLink(){
        return lnk_HBGSelectedHotelVoucher;
    }

    public WebElement getHBGSelectedHotelBookingModificationIDText(){
        return txt_HBGSelectedHotelBookingModificationID;
    }

    public WebElement getHBGSelectedHotelAcceptCancellationCheckBox(){
        return chkbx_HBGSelectedHotelAcceptCancellation;
    }

    public WebElement getHBGSelectedHotelCancelServiceButton(){
        return btn_HBGSelectedHotelCancelService;
    }

    public List<WebElement> getHBGSelectedHotelCancelReasonRadioButton(){
        return rdbtn_HBGSelectedHotelCancelReason;
    }

    public WebElement getHBGSelectedHotelConfirmCancelButton(){
        return btn_HBGSelectedHotelConfirmCancel;
    }

    public WebElement getHBGSelectedHotelCancelConfirmationMessageText(){
        return txt_HBGSelectedHotelCancelConfirmationMessage;
    }

    public WebElement getHBGSelectedHotelPaginationNextButton(){
        return btn_HBGSelectedHotelPaginationNext;
    }

    ///********************TriseptREZAgentPage
    public final String xpath_HBGNameSearchTextBox = "//input[@id='holdersurname']";
    @FindBy(xpath=xpath_HBGNameSearchTextBox)
    private WebElement txtbx_HBGNameSearch;

    public final String xpath_HBGSearchButton = "//input[@id='bookingSearchBtn']";
    @FindBy(xpath=xpath_HBGSearchButton)
    private WebElement btn_HBGSearch;

    public final String xpath_HBGCancelBookingButton = "//input[@id='cancelBookingBtn']";
    @FindBy(xpath=xpath_HBGCancelBookingButton)
    private WebElement btn_HBGCancelBooking;

    public final String xpath_HBGOthersTextTextBox = "//textarea[@id='cancellationReasonText']";
    @FindBy(xpath=xpath_HBGOthersTextTextBox)
    private WebElement txtbx_HBGOthersText;

    public final String xpath_CarnetRetrievedCarNumberText = "//div[@class='retrieve-results']//td[@class='retrieve-results-details']//p[1]";
    @FindBy(xpath=xpath_CarnetRetrievedCarNumberText)
    private List<WebElement> txt_CarnetRetrievedCarNumber;

    public final String xpath_CarnetRetrieveButton = "//app-header//a[@routerlink='/reservations']";
    @FindBy(xpath=xpath_CarnetRetrieveButton)
    private WebElement btn_CarnetRetrieve;

    public final String xpath_HBGRetrievedPaxNameText = "//div[@class='booking-header']//div[3]//child::div[2]";
    @FindBy(xpath=xpath_HBGRetrievedPaxNameText)
    private List<WebElement> txt_HBGRetrievedPaxName;

    public final String xpath_CarnetRetrievedReservationPanel = "//tbody//td[2]";
    @FindBy(xpath=xpath_CarnetRetrievedReservationPanel)
    private List<WebElement> pnl_CarnetRetrievedReservation;

    public final String xpath_CarnetRetrievedNameListedText = "//tbody//td[2]/p[2]";
    @FindBy(xpath=xpath_CarnetRetrievedNameListedText)
    private List<WebElement> txt_CarnetRetrievedNameListed;


    public List<WebElement> getCarnetRetrievedCarNumberText(){return txt_CarnetRetrievedCarNumber;}
    public WebElement getCarnetRetrieveButton(){return btn_CarnetRetrieve;}
    public WebElement getHBGNameSearchTextBox(){return txtbx_HBGNameSearch;}
    public WebElement getHBGSearchButton(){return btn_HBGSearch;}
    public WebElement getHBGCancelBookingButton(){return btn_HBGCancelBooking;}
    public WebElement getHBGOthersTextTextBox(){return txtbx_HBGOthersText;}
    public List<WebElement> getHBGRetrievedPaxNameText(){return txt_HBGRetrievedPaxName;}
    public List<WebElement> getCarnetRetrievedReservationPanel(){return pnl_CarnetRetrievedReservation;}
    public List<WebElement> getCarnetRetrievedNameListedText(){return txt_CarnetRetrievedNameListed;}

}