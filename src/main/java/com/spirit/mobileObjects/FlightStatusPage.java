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
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class FlightStatusPage {

    private AppiumDriver driver;

    public FlightStatusPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //page header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_title_toolbar")
    private MobileElement txt_FlightStatusHeader;

    //Date Picker
    @AndroidFindBy(id = "com.spirit.customerapp:id/spinner_date")
    private MobileElement drpdwn_DatePicker;

    //From City
    @AndroidFindBy(id = "com.spirit.customerapp:id/layout_from")
    private MobileElement btn_FromCity;

    //To City
    @AndroidFindBy(id = "com.spirit.customerapp:id/layout_to")
    private MobileElement btn_ToCity;

    //Flight Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_number")
    private MobileElement txtbx_FlightNumber;

    //View Status Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_check_status")
    private MobileElement btn_ViewStatus;

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

    /////////////////////////////////////////////////
    ///////////////Results Page//////////////////////
    /////////////////////////////////////////////////

    //To From header
    @AndroidFindBy(id = "com.spirit.customerapp:id/ll_to_from_title")
    private MobileElement txt_ResultsToFromHeader;

    //Date of travel sub Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_sub_title_toolbar")
    private MobileElement txt_ResultsDateOfTravelDateHeader;

    //Sort By button
    @AndroidFindBy(id = "com.spirit.customerapp:id/iv_shot")
    private MobileElement btn_ResultsSortBy;

    //Departure Time
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.spirit.customerapp:id/tv_flight_dep_time']//..")
    private List<MobileElement> txt_ResultsDepartTime;

    //Arrival Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/llarrivaltime")
    private List<MobileElement> txt_ResultsArriveTime;

    //Journey Duration time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_duration")
    private List<MobileElement> txt_ResultsJourneyDuration;

    /////////////////////////////////////////////////
    ///////////////Sort By Page//////////////////////
    /////////////////////////////////////////////////

    //Sort By header
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout//android.widget.TextView)[1]")
    private MobileElement txt_SortByHeader;

    //Sort By Number of Stops
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_stops")
    private MobileElement btn_SortByNumberOfStops;

    //Sort By Departure Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dept")
    private MobileElement btn_SortByDepartureTime;

    //Sort By Arrival Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_arrv")
    private MobileElement btn_SortByArrivalTime;

    //Sort By Journey Duration
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_duration")
    private MobileElement btn_SortByDuration;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getFlightStatusHeaderText(){
        return txt_FlightStatusHeader;
    }

    public MobileElement getDatePickerDropDown(){
        return drpdwn_DatePicker;
    }

    public MobileElement getFromCityButton(){
        return btn_FromCity;
    }

    public MobileElement getToCityButton(){
        return btn_ToCity;
    }

    public MobileElement getFlightNumberTextBox(){
        return txtbx_FlightNumber;
    }

    public MobileElement getViewStatusButton(){
        return btn_ViewStatus;
    }

    public MobileElement getCloseAirportSearchButton(){
        return btn_CloseAirportSearch;
    }

    public MobileElement getStationCodeSearchTextBox(){
        return txtbx_StationCodeSearch;
    }

    public MobileElement getRecentSearchesHeaderText(){
        return txt_RecentSearchesHeader;
    }

    public MobileElement getClearRecentSearchesButton(){
        return btn_ClearRecentSearches;
    }

    public List<MobileElement> getRecentSearchesCityPairsButton(){
        return btn_RecentSearchesCityPairs;
    }

    public List<MobileElement> getRecentSearchesOriginStationCodeText(){
        return txt_RecentSearchesOriginStationCode;
    }

    public List<MobileElement> getRecentSearchesOriginCityNameText(){
        return txt_RecentSearchesOriginCityName;
    }

    public List<MobileElement> getRecentSearchesDestinationStationCodeText(){
        return txt_RecentSearchesDestinationStationCode;
    }

    public List<MobileElement> getRecentSearchesDestinationCityNameText(){
        return txt_RecentSearchesDestinationCityName;
    }

    public MobileElement getNearMeHeaderText(){
        return txt_NearMeHeader;
    }

    public MobileElement getNearMeCityNameText(){
        return txt_NearMeCityName;
    }

    public MobileElement getNearMeCityAreaText(){
        return txt_NearMeCityArea;
    }

    public MobileElement getNearMeStationCodeText(){
        return txt_NearMeStationCode;
    }

    public MobileElement getAllAirportsHeaderText(){
        return txt_AllAirportsHeader;
    }

    public List<MobileElement> getAllAirportsCityNameText(){
        return txt_AllAirportsCityName;
    }

    public List<MobileElement> getAllAirportsStationCodeText(){
        return txt_AllAirportsStationCode;
    }

    public MobileElement getResultsToFromHeaderText(){
        return txt_ResultsToFromHeader;
    }

    public MobileElement getResultsDateOfTravelDateHeaderText(){
        return txt_ResultsDateOfTravelDateHeader;
    }

    public MobileElement getResultsSortByButton(){
        return btn_ResultsSortBy;
    }

    public List<MobileElement> getResultsDepartTimeText(){
        return txt_ResultsDepartTime;
    }

    public List<MobileElement> getResultsArriveTimeText(){
        return txt_ResultsArriveTime;
    }

    public List<MobileElement> getResultsJourneyDurationText(){
        return txt_ResultsJourneyDuration;
    }

    public MobileElement getSortByHeaderText(){
        return txt_SortByHeader;
    }

    public MobileElement getSortByNumberOfStopsButton(){
        return btn_SortByNumberOfStops;
    }

    public MobileElement getSortByDepartureTimeButton(){
        return btn_SortByDepartureTime;
    }

    public MobileElement getSortByArrivalTimeButton(){
        return btn_SortByArrivalTime;
    }

    public MobileElement getSortByDurationButton(){
        return btn_SortByDuration;
    }

}