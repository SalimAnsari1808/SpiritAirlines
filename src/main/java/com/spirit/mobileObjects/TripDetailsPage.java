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

////Page after retrieving PNR on My Trips
public class TripDetailsPage {

    private AppiumDriver driver;

    public TripDetailsPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //*******************************************************************
    //*******************Trip Details************************************
    //*******************************************************************
    //Modify Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_done")
    private MobileElement btn_Modify;

    //Flight Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_number")
    private MobileElement txt_FlightNumber;

    //Confirmation Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_confirmation")
    private MobileElement txt_ConfirmationCode;

    //Departing City Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_from_city")
    private MobileElement txt_DepartingCityName;

    //Departing Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_from_code")
    private MobileElement txt_DepartingStationCode;

    //Departing Date of travel
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_date")
    private MobileElement txt_DepartingDateOfTravel;

    //Departing Time of Travel
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_time")
    private MobileElement txt_DepartingTimeOfTravel;

    //Departing Terminal
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dep_terminal_name")
    private MobileElement txt_DepartingTerminal;

    //Departing Gate
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dep_gate")
    private MobileElement txt_DepartingGate;

    //Departing Weather
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dep_weather")
    private MobileElement txt_DepartingWeather;

    //Flight Status
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_status")
    private MobileElement txt_FlightStatus;

    //Flight Duration
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_travel_time")
    private MobileElement txt_FlightDuration;

    //Arrival City Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_to_city")
    private MobileElement txt_ArrivalCityName;

    //Arrival Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_to_code")
    private MobileElement txt_ArrivalStationCode;

    //Arrival Date of travel
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_arr_date")
    private MobileElement txt_ArrivalDateOfTravel;

    //Arrival Time of Travel
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_arr_time")
    private MobileElement txt_ArrivalTimeOfTravel;

    //Arrival Terminal
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_arr_terminal_name")
    private MobileElement txt_ArrivalTerminal;

    //Arrival Gate
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_arr_gate")
    private MobileElement txt_ArrivalGate;

    //Arrival Weather
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_arr_weather")
    private MobileElement txt_ArrivalWeather;

    //Edit Bags Icon My Trips
    @AndroidFindBy(id = "com.spirit.customerapp:id/linear_before_checkin_bags")
    private MobileElement btn_MyTripsEditBags;

    //Edit Bags Icon Check In--------------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_bag_during")
    private MobileElement btn_CheckInEditBags;

    //Edit Seats Icon
    @AndroidFindBy(id = "com.spirit.customerapp:id/linear_before_checkin_seats")
    private MobileElement btn_MyTripsEditSeats;

    //Edit Seats Icon Check In-----------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_p_seats")
    private MobileElement btn_CheckInEditSeats;

    //Edit Options Icon
    @AndroidFindBy(id = "com.spirit.customerapp:id/linear_before_checkin_options")
    private MobileElement btn_EditOptions;

    //CheckIn Button---------------------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_during_checkin")
    private MobileElement btn_CheckInOptions;

    //Boarding Pass Button-----------------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/pt_boardingpass_user")
    private MobileElement btn_BoardingPass;

    //passenger Details label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PASSENGER DETAILS']")
    private MobileElement lbl_PassengerDetails;

    //Passenger Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvNameOfPassenger")
    private List<MobileElement> txt_PassengerName;

    //Passenger Seat Numbers
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvSeatOfPassenger")
    private List<MobileElement> txt_PassengerSeatNumbers;

    //Passenger Carry-on
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvCarryOnPassenger")
    private List<MobileElement> txt_PassengerCarryOn;

    //Passenger Checked Bags
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvCheckedPassenger")
    private List<MobileElement> txt_PassengerCheckedBags;

    //CheckIn Button (button changes to check in unavailable for restricted passengers)
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_start_check_in")
    private MobileElement btn_CheckIn;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getModifyButton(){
        return btn_Modify;
    }

    public MobileElement getFlightNumberText(){
        return txt_FlightNumber;
    }

    public MobileElement getConfirmationCodeText(){
        return txt_ConfirmationCode;
    }

    public MobileElement getDepartingCityNameText(){
        return txt_DepartingCityName;
    }

    public MobileElement getDepartingStationCodeText(){
        return txt_DepartingStationCode;
    }

    public MobileElement getDepartingDateOfTravelText(){
        return txt_DepartingDateOfTravel;
    }

    public MobileElement getDepartingTimeOfTravelText(){
        return txt_DepartingTimeOfTravel;
    }

    public MobileElement getDepartingTerminalText(){
        return txt_DepartingTerminal;
    }

    public MobileElement getDepartingGateText(){
        return txt_DepartingGate;
    }

    public MobileElement getDepartingWeatherText(){
        return txt_DepartingWeather;
    }

    public MobileElement getFlightStatusText(){
        return txt_FlightStatus;
    }

    public MobileElement getFlightDurationText(){
        return txt_FlightDuration;
    }

    public MobileElement getArrivalCityNameText(){
        return txt_ArrivalCityName;
    }

    public MobileElement getArrivalStationCodeText(){
        return txt_ArrivalStationCode;
    }

    public MobileElement getArrivalDateOfTravelText(){
        return txt_ArrivalDateOfTravel;
    }

    public MobileElement getArrivalTimeOfTravelText(){
        return txt_ArrivalTimeOfTravel;
    }

    public MobileElement getArrivalTerminalText(){
        return txt_ArrivalTerminal;
    }

    public MobileElement getArrivalGateText(){
        return txt_ArrivalGate;
    }

    public MobileElement getArrivalWeatherText(){
        return txt_ArrivalWeather;
    }

    public MobileElement getMyTripsEditBagsButton(){
        return btn_MyTripsEditBags;
    }

    public MobileElement getCheckInEditBagsButton(){
        return btn_CheckInEditBags;
    }

    public MobileElement getMyTripsEditSeatsButton(){
        return btn_MyTripsEditSeats;
    }

    public MobileElement getCheckInEditSeatsButton(){
        return btn_CheckInEditSeats;
    }

    public MobileElement getEditOptionsButton(){
        return btn_EditOptions;
    }
    public MobileElement getCheckInOptionsButton(){
        return btn_CheckInOptions;
    }

    public MobileElement getBoardingPassButton(){
        return btn_BoardingPass;
    }

    public MobileElement getPassengerDetailsLabel(){
        return lbl_PassengerDetails;
    }

    public List<MobileElement> getPassengerNameText(){
        return txt_PassengerName;
    }

    public List<MobileElement> getPassengerSeatNumbersText(){
        return txt_PassengerSeatNumbers;
    }

    public List<MobileElement> getPassengerCarryOnText(){
        return txt_PassengerCarryOn;
    }

    public List<MobileElement> getPassengerCheckedBagsText(){
        return txt_PassengerCheckedBags;
    }

    public MobileElement getCheckInButton(){
        return btn_CheckIn;
    }

}