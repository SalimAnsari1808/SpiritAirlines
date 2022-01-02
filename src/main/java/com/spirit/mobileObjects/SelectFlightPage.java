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

public class SelectFlightPage {

    private AppiumDriver driver;

    public SelectFlightPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //left calendar button
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id ='com.spirit.customerapp:id/corousal_layout']//android.widget.ImageView)[1]")
    private MobileElement btn_PreviousCalendarCarousel;

    //right calendar button
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id ='com.spirit.customerapp:id/corousal_layout']//android.widget.ImageView)[2]")
    private MobileElement btn_NextCalendarCarousel;

//////////////////////////////////Need To Update Xpath for dates on the calendar carousel//////////////
    //dates on calendar
    @AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id = 'com.spirit.customerapp:id/corousal_rv']")
    private MobileElement btn_DatesOnCalendarCarousel;

    //Journey Cities
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_from_to")
    private List<MobileElement> txt_ResultsJourneyCities;

    //Departure Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_time_dept")
    private List<MobileElement> txt_ResultsDepartureTime;

    //Arrival Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_time_arrival")
    private List<MobileElement> txt_ResultsArrivalTime;

    //Number Of Stops
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_stop")
    private List<MobileElement> txt_ResultsFlightStopType;

    //Flight Duration
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_stop_time")
    private List<MobileElement> txt_ResultsFlightDuration;

    //Flight Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dollar_right")
    private List<MobileElement> txt_ResultsFlightPrice;

    @AndroidFindBy(id = "com.spirit.customerapp:id/img_chevron_gray")
    private List<MobileElement> img_ResultsFlightArrow;

    //Flight Details
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_detail_btn")
    private MobileElement btn_FlightDetails;

    //Seat Availability
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_seat_available_btn")
    private MobileElement btn_SeatAvailabilty;

    //$9 Fare Club
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='$9 Fare Club*']")
    private MobileElement txt_$9FareClub;

    //$9 Fare Club Flight Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dollar_bottom")
    private MobileElement txt_$9FareClubFarePrice;

    //Standard
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Standard']")
    private MobileElement txt_Standard;

    //Standard Flight Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dollor_standard")
    private MobileElement txt_StandardFarePrice;

    //////////////////////////////////////
    ////////////Flight Details////////////
    //////////////////////////////////////
    //Number of flights at the bottom of page swipe to see other segment
    @AndroidFindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.spirit.customerapp:id/tabDots']//android.support.v7.app.ActionBar.Tab")
    private List<MobileElement> btn_NumberOfSegments;

    //Done Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_action_toolbar")
    private MobileElement btn_FlightInformationDone;

    //Flight Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_no")
    private MobileElement txt_FlightDetailsFlightNumber;

    //Aircraft Type
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_fligh_type")
    private MobileElement txt_FlightDetailsAircraftType;

    //Departure City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_from")
    private MobileElement txt_FlightDetailsDepartureCity;

    //Departure Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_from_code")
    private MobileElement txt_FlightDetailsDepartureStationCode;

    //Departure date
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_date")
    private MobileElement txt_FlightDetailsDepatureDate;

    //Departure time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_time")
    private MobileElement txt_FlightDetailsDepartureTime;

    //Duration
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_travel_time")
    private MobileElement txt_FlightDetailsFlightDuration;

    //Layover text
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_overLay_time")
    private MobileElement txt_FlightDetailsLayoverTime;

    //Arrival City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_to")
    private MobileElement txt_FlightDetailsArrivalCity;

    //Arrival Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_to_code")
    private MobileElement txt_FlightDetailsArrivalStationCode;

    //Arrival date
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_arr_date")
    private MobileElement txt_FlightDetailsArrivalDate;

    //Arrival time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_arr_time")
    private MobileElement txt_FlightDetailsArrivalTime;

    //On time performance
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_ontime")
    private MobileElement txt_FlightDetailsOnTimePerformance;

    //Late Performance
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_late")
    private MobileElement txt_FlightDetailsLatePerformance;

    //////////////////////////////////////
    /////////Seat Availability////////////
    //////////////////////////////////////
    //Done Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_done")
    private MobileElement btn_SeatAvailabiltyDone;

    //Origin and destination
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFlight")
    private MobileElement txt_SeatAvailabiltyCurrentSegment;

    //Seat Key
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvSeatKey")
    private MobileElement btn_ViewSeatKey;

    //Available Big Front Seats
    @AndroidFindBy(id = "com.spirit.customerapp:id/imgBtnBFSSeat")
    private List<MobileElement> img_AvailableBigFrontSeats;

    //Available Exit Seats------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnExitSeat")
    private List<MobileElement> img_AvailableExitRowSeats;

    //Available regular Seats
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnNormalSeat")
    private List<MobileElement> img_AvailableRegularSeats;

    //Unavailable Seats
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnNonAssignableSeat")
    private List<MobileElement> img_UnavailableSeats;

    /////////Seat Key//////
    //Seat Key Header
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'SEAT KEY']")
    private MobileElement txt_SeatKeyHeader;

    //Big Front Seat
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Big Front Seat']")
    private MobileElement txt_BigFrontSeatKey;

    //Big Front Seat Color
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout//android.view.View[@index = '0'])[1]")
    private MobileElement img_BigFrontSeatColor;

    //Big Front Seat legroom text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Up to 11 inches more legroom']")
    private MobileElement txt_BigFrontSeatLegRoom;

    //Regular Seat
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Regular Seat']")
    private MobileElement txt_RegularSeatKey;

    //Regular Seat Color
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout//android.view.View[@index = '0'])[2]")
    private MobileElement img_RegularSeatColor;

    //Regular Seat leg room
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= '10 inches legroom']")
    private MobileElement txt_RegularSeatLegRoom;

    //Exit Seat
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Exit Seat']")
    private MobileElement txt_ExitRowSeatKey;

    //Exit Seat Color
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout//android.view.View[@index = '0'])[3]")
    private MobileElement img_ExitRowSeatColor;

    //Unavailable Text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Unavailable']")
    private MobileElement txt_UnavailableSeatKey;

    //Okay
    @AndroidFindBy(id = "com.spirit.customerapp:id/ok_button")
    private MobileElement btn_SeatKeyOkay;

    /////////////////////
    //$9FC Upsell PopUp//
    /////////////////////
    //Close PopUp
    @AndroidFindBy(id = "com.spirit.customerapp:id/close_btn_fc")
    private MobileElement btn_9DFCSClose;
    //Save Amount
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvSaveAmount")
    private MobileElement txt_9DFCSaveAmount;
    //Join For
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnJoinMember")
    private MobileElement btn_Join9DFCFor;
    //Already A Member Sign In
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvSigninCard")
    private MobileElement btn_AlreadyAMemberSignIn;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getPreviousCalendarCarouselButton(){
        return btn_PreviousCalendarCarousel;
    }

    public MobileElement getNextCalendarCarouselButton(){
        return btn_NextCalendarCarousel;
    }

    public MobileElement getDatesOnCalendarCarouselButton(){
        return btn_DatesOnCalendarCarousel;
    }

    public List<MobileElement> getResultsJourneyCitiesText(){
        return txt_ResultsJourneyCities;
    }

    public List<MobileElement> getResultsDepartureTimeText(){
        return txt_ResultsDepartureTime;
    }

    public List<MobileElement> getResultsArrivalTimeText(){
        return txt_ResultsArrivalTime;
    }

    public List<MobileElement> getResultsFlightStopTypeText(){
        return txt_ResultsFlightStopType;
    }

    public List<MobileElement> getResultsFlightDurationText(){
        return txt_ResultsFlightDuration;
    }

    public List<MobileElement> getResultsFlightPriceText(){
        return txt_ResultsFlightPrice;
    }

    public List<MobileElement> getResultsFlightArrowImage(){
        return img_ResultsFlightArrow;
    }

    public MobileElement getFlightDetailsButton(){
        return btn_FlightDetails;
    }

    public MobileElement getSeatAvailabiltyButton(){
        return btn_SeatAvailabilty;
    }

    public MobileElement get$9FareClubText(){
        return txt_$9FareClub;
    }

    public MobileElement get$9FareClubFarePriceText(){
        return txt_$9FareClubFarePrice;
    }

    public MobileElement getStandardText(){
        return txt_Standard;
    }

    public MobileElement getStandardFarePriceText(){
        return txt_StandardFarePrice;
    }

    public List<MobileElement> getNumberOfSegmentsButton(){
        return btn_NumberOfSegments;
    }

    public MobileElement getFlightInformationDoneButton(){
        return btn_FlightInformationDone;
    }

    public MobileElement getFlightDetailsFlightNumberText(){
        return txt_FlightDetailsFlightNumber;
    }

    public MobileElement getFlightDetailsAircraftTypeText(){
        return txt_FlightDetailsAircraftType;
    }

    public MobileElement getFlightDetailsDepartureCityText(){
        return txt_FlightDetailsDepartureCity;
    }

    public MobileElement getFlightDetailsDepartureStationCodeText(){
        return txt_FlightDetailsDepartureStationCode;
    }

    public MobileElement getFlightDetailsDepatureDateText(){
        return txt_FlightDetailsDepatureDate;
    }

    public MobileElement getFlightDetailsDepartureTimeText(){
        return txt_FlightDetailsDepartureTime;
    }

    public MobileElement getFlightDetailsFlightDurationText(){
        return txt_FlightDetailsFlightDuration;
    }

    public MobileElement getFlightDetailsLayoverTimeText(){
        return txt_FlightDetailsLayoverTime;
    }

    public MobileElement getFlightDetailsArrivalCityText(){
        return txt_FlightDetailsArrivalCity;
    }

    public MobileElement getFlightDetailsArrivalStationCodeText(){
        return txt_FlightDetailsArrivalStationCode;
    }

    public MobileElement getFlightDetailsArrivalDateText(){
        return txt_FlightDetailsArrivalDate;
    }

    public MobileElement getFlightDetailsArrivalTimeText(){
        return txt_FlightDetailsArrivalTime;
    }

    public MobileElement getFlightDetailsOnTimePerformanceText(){
        return txt_FlightDetailsOnTimePerformance;
    }

    public MobileElement getFlightDetailsLatePerformanceText(){
        return txt_FlightDetailsLatePerformance;
    }

    public MobileElement getSeatAvailabiltyDoneButton(){
        return btn_SeatAvailabiltyDone;
    }

    public MobileElement getSeatAvailabiltyCurrentSegmentText(){
        return txt_SeatAvailabiltyCurrentSegment;
    }

    public MobileElement getViewSeatKeyButton(){
        return btn_ViewSeatKey;
    }

    public List<MobileElement> getAvailableBigFrontSeatsImage(){
        return img_AvailableBigFrontSeats;
    }

    public List<MobileElement> getAvailableExitRowSeatsImage(){
        return img_AvailableExitRowSeats;
    }

    public List<MobileElement> getAvailableRegularSeatsImage(){
        return img_AvailableRegularSeats;
    }

    public List<MobileElement> getUnavailableSeatsImage(){
        return img_UnavailableSeats;
    }

    public MobileElement getSeatKeyHeaderText(){
        return txt_SeatKeyHeader;
    }

    public MobileElement getBigFrontSeatKeyText(){
        return txt_BigFrontSeatKey;
    }

    public MobileElement getBigFrontSeatColorImage(){
        return img_BigFrontSeatColor;
    }

    public MobileElement getBigFrontSeatLegRoomText(){
        return txt_BigFrontSeatLegRoom;
    }

    public MobileElement getRegularSeatKeyText(){
        return txt_RegularSeatKey;
    }

    public MobileElement getRegularSeatColorImage(){
        return img_RegularSeatColor;
    }

    public MobileElement getRegularSeatLegRoomText(){
        return txt_RegularSeatLegRoom;
    }

    public MobileElement getExitRowSeatKeyText(){
        return txt_ExitRowSeatKey;
    }

    public MobileElement getExitRowSeatColorImage(){
        return img_ExitRowSeatColor;
    }

    public MobileElement getUnavailableSeatKeyText(){
        return txt_UnavailableSeatKey;
    }

    public MobileElement getSeatKeyOkayButton(){
        return btn_SeatKeyOkay;
    }

    public MobileElement get9DFCSCloseButton(){
        return btn_9DFCSClose;
    }

    public MobileElement get9DFCSaveAmountText(){
        return txt_9DFCSaveAmount;
    }

    public MobileElement getJoin9DFCForButton(){
        return btn_Join9DFCFor;
    }

    public MobileElement getAlreadyAMemberSignInButton(){
        return btn_AlreadyAMemberSignIn;
    }
}
