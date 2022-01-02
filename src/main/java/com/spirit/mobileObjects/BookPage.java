package com.spirit.mobileObjects;

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
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookPage {

    private AppiumDriver driver;

    public BookPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Page Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_title_toolbar")
    private MobileElement txt_BookHeader;

    //One Way
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'One-Way']")
    private MobileElement btn_OneWay;

    //Round Trip
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Round Trip']")
    private MobileElement btn_RoundTrip;

    //From
    @AndroidFindBy(id = "com.spirit.customerapp:id/layout_from")
    private MobileElement btn_FromCitySelect;

    //To
    @AndroidFindBy(id = "com.spirit.customerapp:id/layout_to")
    private MobileElement btn_ToCitySelect ;

    /////////////////////////////////////////////////
    ///////////////Search City Page//////////////////
    /////////////////////////////////////////////////

    //Close search
    @AndroidFindBy(id = "com.spirit.customerapp:id/iv_back_toolbar")
    private MobileElement btn_CloseAirportSearch;

    //where search box
    @AndroidFindBy(id = "com.spirit.customerapp:id/etSearch")
    private MobileElement txtbx_StationCodeSearch;

    //recent searches Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvRecentSearch")
    private MobileElement txt_RecentSearchesHeader;

    //clear recent searches
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvRecentSearchClear")
    private MobileElement btn_ClearRecentSearches;

    //Recent searches origin and destination buttons
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.spirit.customerapp:id/recentSearchParent']/android.widget.LinearLayout")
    private List<MobileElement> btn_RecentSearchesCityPairs;

    //recent search origin station code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvOriginCode")
    private List<MobileElement> txt_RecentSearchesOriginStationCode;

    //recent search origin City and state Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvOriginName")
    private List<MobileElement> txt_RecentSearchesOriginCityName;


    //recent search Destination station code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvDestCode")
    private List<MobileElement> txt_RecentSearchesDestinationStationCode;

    //recent search Destination City and state Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvDestName")
    private List<MobileElement> txt_RecentSearchesDestinationCityName;

    //Near Me Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvNearMe")
    private MobileElement txt_NearMeHeader;

    //Near Me City and State
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvNearMeFirst")
    private MobileElement txt_NearMeCityName;

    //Near Me Sub Area
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFirstNearSubArea")
    private MobileElement txt_NearMeCityArea;

    //Near Me Station
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFirstNearCodeAirport")
    private MobileElement txt_NearMeStationCode;

    //All Airport Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvHeader")
    private MobileElement txt_AllAirportsHeader;

    //All airport City and State
    @AndroidFindBy(id = "com.spirit.customerapp:id/name")
    private List<MobileElement> txt_AllAirportsCityName;

    //All airport Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvCodeAirport")
    private List<MobileElement> txt_AllAirportsStationCode;

    //Date/s
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_date")
    private MobileElement btn_DateOfTravel;

    ////////////////////////////
    /////Calendar Pop Up////////
    ////////////////////////////

    //Close Calendar Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/close_calendar")
    private MobileElement btn_CloseCalendar;

    //DepartDateSelected
    @AndroidFindBy(id = "com.spirit.customerapp:id/first_day_selected")
    private MobileElement txt_DepartDateSelected;

    //ReturnDateSelected
    @AndroidFindBy(id = "com.spirit.customerapp:id/last_day_selected")
    private MobileElement txt_ReturnDateSelected;

    //Select Dates Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/done_button")
    private MobileElement btn_SelectDates;

    //Passenger/s
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_passenger_type")
    private MobileElement btn_Passengers;

    ////////////////////////////
    ///Passenger Select Page////
    ////////////////////////////
    //Back Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/iv_back_toolbar")
    private MobileElement btn_PassengersBack;

    //Page Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_title_toolbar")
    private MobileElement txt_PassengersHeader;

    //Done Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_done")
    private MobileElement btn_PassengersDone;

    //Adults Text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Adults']")
    private MobileElement txt_PassengersAdults;

    //Number of adults
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvAdult")
    private MobileElement txt_PassengersNumberOfAdults;

    //Adults Minus Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnAdultMinus")
    private MobileElement btn_PassengersAdultsMinus;

    //Adults Plus Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnAdultPlus")
    private MobileElement btn_PassengersAdultsPlus;

    //Children Text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Children (2-14)']")
    private MobileElement txt_PassengersChildren;

    //Number of Children
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvChild")
    private MobileElement txt_PassengersNumberOfChildren;

    //Children Minus Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnChildMinus")
    private MobileElement btn_PassengersChildrenMinus;

    //Children Plus Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnChildPlus")
    private MobileElement btn_PassengersChildrenPlus;

    //Child Date of Birth Button
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.spirit.customerapp:id/childLayout']//android.widget.TextView")
    private List<MobileElement> btn_PassengersChildDateOfBirth;

    //Lap Infant Text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Lap Infants (0-2)']")
    private MobileElement txt_PassengersLapInfant;

    //Number of Lap Infant
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvInfant")
    private MobileElement txt_PassengersNumberOfLapInfant;

    //Lap Infant Minus Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnInfantMinus")
    private MobileElement btn_PassengersLapInfantMinus;

    //Lap Infant Plus Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnInfantPlus")
    private MobileElement btn_PassengersLapInfantPlus;

    //Lap Infant Date of Birth Button
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.spirit.customerapp:id/infantLayout']/android.widget.LinearLayout/android.widget.TextView[1]")
    private List<MobileElement> btn_PassengersLapInfantDateOfBirth;

    //Infant Seat Switch
    @AndroidFindBy(id = "com.spirit.customerapp:id/infant_seat_switch")
    private List<MobileElement> swtch_PassengersInfantNeedsSeat;

    //Promo Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_promo_code")
    private MobileElement txtbx_PromoCode;

    //Search Flight Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_search")
    private MobileElement btn_SearchFlight;

    //////////////////
    ///UMNR PopUp////
    /////////////////
    @AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='android:id/custom']/android.widget.LinearLayout")
    private List<MobileElement> lbl_UMNRPopUp;

    @AndroidFindBy(id="com.spirit.customerapp:id/tvMsg")
    private MobileElement txt_UMNRPopUpMessage;

    @AndroidFindBy(id="com.spirit.customerapp:id/tvOk")
    private MobileElement btn_UMNRPopUpCancel;

    @AndroidFindBy(id="com.spirit.customerapp:id/tvAccept")
    private MobileElement btn_UMNRPopUpAccept;

    @AndroidFindBy(id="com.spirit.customerapp:id/tvOk")
    private MobileElement btn_UMNRPopUpOK;

    ///////////////////////////////////
    ///ONE WAY INTERNATIONAL POPUP////
    ///////////////////////////////////
    @AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='android:id/custom']/android.widget.LinearLayout")
    private List<MobileElement> lbl_OneWayInternationalPopUp;

    @AndroidFindBy(id="com.spirit.customerapp:id/tvMsg")
    private MobileElement txt_OneWayInternationalPopUpMessage;

    @AndroidFindBy(id="com.spirit.customerapp:id/tvOk")
    private MobileElement btn_OneWayInternationalPopUpOK;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/
    public MobileElement getBookHeaderText() {
        return txt_BookHeader;
    }

    public MobileElement getOneWayButton() {
        return btn_OneWay;
    }

    public MobileElement getRoundTripButton() {
        return btn_RoundTrip;
    }

    public MobileElement getFromCitySelectButton() {
        return btn_FromCitySelect;
    }

    public MobileElement getToCitySelectButton() {
        return btn_ToCitySelect;
    }

    public MobileElement getCloseAirportSearchButton() {
        return btn_CloseAirportSearch;
    }

    public MobileElement getStationCodeSearchTextBox() {
        return txtbx_StationCodeSearch;
    }

    public MobileElement getRecentSearchesHeaderText() {
        return txt_RecentSearchesHeader;
    }

    public MobileElement getClearRecentSearchesButton() {
        return btn_ClearRecentSearches;
    }

    public List<MobileElement> getRecentSearchesCityPairsButton() {
        return btn_RecentSearchesCityPairs;
    }

    public List<MobileElement> getRecentSearchesOriginStationCodeText() {
        return txt_RecentSearchesOriginStationCode;
    }

    public List<MobileElement> getRecentSearchesOriginCityNameText() {
        return txt_RecentSearchesOriginCityName;
    }

    public List<MobileElement> getRecentSearchesDestinationStationCodeText() {
        return txt_RecentSearchesDestinationStationCode;
    }

    public List<MobileElement> getRecentSearchesDestinationCityNameText() {
        return txt_RecentSearchesDestinationCityName;
    }

    public MobileElement getNearMeHeaderText() {
        return txt_NearMeHeader;
    }

    public MobileElement getNearMeCityNameText() {
        return txt_NearMeCityName;
    }

    public MobileElement getNearMeCityAreaText() {
        return txt_NearMeCityArea;
    }

    public MobileElement getNearMeStationCodeText() {
        return txt_NearMeStationCode;
    }

    public MobileElement getAllAirportsHeaderText() {
        return txt_AllAirportsHeader;
    }

    public List<MobileElement> getAllAirportsCityNameText() {
        return txt_AllAirportsCityName;
    }

    public List<MobileElement> getAllAirportsStationCodeText() {
        return txt_AllAirportsStationCode;
    }

    public MobileElement getDateOfTravelButton() {
        return btn_DateOfTravel;
    }

    public MobileElement getCloseCalendarButton() {
        return btn_CloseCalendar;
    }

    public MobileElement getDepartDateSelectedText() {
        return txt_DepartDateSelected;
    }

    public MobileElement getReturnDateSelectedText() {
        return txt_ReturnDateSelected;
    }

    public MobileElement getSelectDatesButton() {
        return btn_SelectDates;
    }

    public MobileElement getPassengersTextButton() {
        return btn_Passengers;
    }

    public MobileElement getPassengersBackButton() {
        return btn_PassengersBack;
    }

    public MobileElement getPassengersHeaderText() {
        return txt_PassengersHeader;
    }

    public MobileElement getPassengersDoneButton() {
        return btn_PassengersDone;
    }

    public MobileElement getPassengersAdultsText() {
        return txt_PassengersAdults;
    }

    public MobileElement getPassengersNumberOfAdultsText() {
        return txt_PassengersNumberOfAdults;
    }

    public MobileElement getPassengersAdultsMinusButton() {
        return btn_PassengersAdultsMinus;
    }

    public MobileElement getPassengersAdultsPlusButton() {
        return btn_PassengersAdultsPlus;
    }

    public MobileElement getPassengersChildrenText() {
        return txt_PassengersChildren;
    }

    public MobileElement getPassengersNumberOfChildrenText() {
        return txt_PassengersNumberOfChildren;
    }

    public MobileElement getPassengersChildrenMinusButton() {
        return btn_PassengersChildrenMinus;
    }

    public MobileElement getPassengersChildrenPlusButton() {
        return btn_PassengersChildrenPlus;
    }

    public List<MobileElement> getPassengersChildDateOfBirthButton() {
        return btn_PassengersChildDateOfBirth;
    }

    public MobileElement getPassengersLapInfantText() {
        return txt_PassengersLapInfant;
    }

    public MobileElement getPassengersNumberOfLapInfantText() {
        return txt_PassengersNumberOfLapInfant;
    }

    public MobileElement getPassengersLapInfantMinusButton() {
        return btn_PassengersLapInfantMinus;
    }

    public MobileElement getPassengersLapInfantPlusButton() {
        return btn_PassengersLapInfantPlus;
    }

    public List<MobileElement> getPassengersLapInfantDateOfBirthButton() {
        return btn_PassengersLapInfantDateOfBirth;
    }

    public List<MobileElement> getPassengersInfantNeedsSeatSwitch() {
        return swtch_PassengersInfantNeedsSeat;
    }

    public MobileElement getPromoCodeTextBox() {
        return txtbx_PromoCode;
    }

    public MobileElement getSearchFlightButton() {
        return btn_SearchFlight;
    }


    //////////////////
    ///UMNR PopUp////
    /////////////////
    public List<MobileElement> getUMNRPopUpLabel(){
        return lbl_UMNRPopUp;
    }

    public MobileElement getUMNRPopUpMessageText(){
        return txt_UMNRPopUpMessage;
    }

    public MobileElement getUMNRPopUpCancelButton(){
        return btn_UMNRPopUpCancel;
    }

    public MobileElement getUMNRPopUpAcceptButton(){
        return btn_UMNRPopUpAccept;
    }

    public MobileElement getUMNRPopUpOKButton(){
        return btn_UMNRPopUpOK;
    }

    ///////////////////////////////////
    ///ONE WAY INTERNATIONAL POPUP////
    //////////////////////////////////
    public List<MobileElement> getOneWayInternationalPopUpLabel(){
        return lbl_OneWayInternationalPopUp;
    }

    public MobileElement getOneWayInternationalPopUpMessageText(){
        return txt_OneWayInternationalPopUpMessage;
    }

    public MobileElement getOneWayInternationalPopUpOKButton(){
        return btn_OneWayInternationalPopUpOK;
    }
}
