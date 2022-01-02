package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BoardingPassPage {

    private AppiumDriver driver;

    public BoardingPassPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Done Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_done")
    private MobileElement btn_BoardingPassDone;

    //Boarding Pass (1 per passenger per segment)
    //Flight Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flightNo")
    private MobileElement txt_FlightNumber;

    //Date of Travel
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_date")
    private MobileElement txt_DateOfTravel;

    //Type of booking(Non-Rev NRSA/NRSP)-----------------------------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_ssf_code")
    private MobileElement txt_TypeOfBooking;

    //Origin Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_code")
    private MobileElement txt_OriginStationCode;

    //Origin City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_city")
    private MobileElement txt_OriginCity;

    //Destination Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_arr_code")
    private MobileElement txt_DestinationStationCode;

    //Destination City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_arr_city")
    private MobileElement txt_DestinationCity;

    //Boarding Time Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.spirit.customerapp:id/tv_flight_br_time']//..//android.widget.TextView[1]")
    private MobileElement lbl_BoardingTime;

    //Boarding Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_br_time")
    private MobileElement txt_BoardingTime;

    //Departure Time Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.spirit.customerapp:id/tv_flight_dep_time']//..//android.widget.TextView[1]")
    private MobileElement lbl_DepartureTime;

    //Departure Time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_time")
    private MobileElement txt_DepartureTime;

    //Gate Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.spirit.customerapp:id/tv_dep_gate']//..//android.widget.TextView[1]")
    private MobileElement lbl_Gate;

    //Gate
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dep_gate")
    private MobileElement txt_Gate;

    //Seat Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.spirit.customerapp:id/tv_flight_seat']//..//android.widget.TextView[1]")
    private MobileElement lbl_Seat;

    //Seat
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_seat")
    private MobileElement txt_Seat;

    //Passenger Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_passenger_name")
    private MobileElement txt_PassengerName;

    //TSA image---------------------------------------------------------------------------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/img_tsa_precheck")
    private MobileElement img_TSAPreCheckIcon;

    //CarryOn Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.spirit.customerapp:id/tv_flight_carry_on']/../android.widget.TextView[1]")
    private MobileElement lbl_CarryOn;

    //CarryOn (YES/NO)
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_carry_on")
    private MobileElement txt_CarryOn;

    //Zone Label
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_text_zone")
    private MobileElement lbl_Zone;

    //Zone
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_zone")
    private MobileElement txt_Zone;

    //Sequence Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvSeq")
    private MobileElement txt_SequenceNumber;

    //Number of Boarding passes (1 Boarding pass per passenger per leg)
    @AndroidFindBy(xpath = "//android.support.v7.app.ActionBar.Tab")
    private MobileElement btn_BoardingPassSlider;

    //Inhibited passenger (UNABLE TO CHECK-IN)
    //Inhibited Passenger Page Header------------------------------
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = 'com.spirit.customerapp:id/toolbar']//android.widget.TextView[contains(@text, 'Not a Boarding Pass')]")
    private MobileElement txt_NotABoardingPassHeader;

    //CheckIn Error for Inhibited Passenger-------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_err_header")
    private MobileElement txt_CheckInUnavailableErrorMessage;

    //Inhibited Passenger Confirmation Code------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_ssf_code")
    private MobileElement  txt_InhibitedPassengerConfirmationCode;

    //Inhibited Passenger Departing Terminal--------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_terminal")
    private MobileElement txt_InhibitedPassengerDepartingTerminal;

    //Inhibited Passenger Flight Duration------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_travel_time")
    private MobileElement txt_InhibitedPassengerFlightDuration;

    //Inhibited Passenger Arriving Terminal-----------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_arr_terminal")
    private MobileElement txt_InhibitedPassengerArrivingTerminal;

    //CheckIn With Agent---------------------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_checkin_time")
    private MobileElement txt_CheckInWithAgent;

    //We're Sorry Message-------------------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_err_footer")
    private MobileElement txt_WeAreSorryMessage;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getBoardingPassDoneButton(){
        return btn_BoardingPassDone;
    }

    public MobileElement getFlightNumberText(){
        return txt_FlightNumber;
    }

    public MobileElement getDateOfTravelText(){
        return txt_DateOfTravel;
    }

    public MobileElement getTypeOfBookingText(){
        return txt_TypeOfBooking;
    }

    public MobileElement getOriginStationCodeText(){
        return txt_OriginStationCode;
    }

    public MobileElement getOriginCityText(){
        return txt_OriginCity;
    }

    public MobileElement getDestinationStationCodeText(){
        return txt_DestinationStationCode;
    }

    public MobileElement getDestinationCityText(){
        return txt_DestinationCity;
    }

    public MobileElement getBoardingTimeLabel(){
        return lbl_BoardingTime;
    }

    public MobileElement getBoardingTimeText(){
        return txt_BoardingTime;
    }

    public MobileElement getDepartureTimeLabel(){
        return lbl_DepartureTime;
    }

    public MobileElement getDepartureTimeText(){
        return txt_DepartureTime;
    }

    public MobileElement getGateLabel(){
        return lbl_Gate;
    }

    public MobileElement getGateText(){
        return txt_Gate;
    }

    public MobileElement getSeatLabel(){
        return lbl_Seat;
    }

    public MobileElement getSeatText(){
        return txt_Seat;
    }

    public MobileElement getPassengerNameText(){
        return txt_PassengerName;
    }

    public MobileElement getTSAPreCheckIconImage(){
        return img_TSAPreCheckIcon;
    }

    public MobileElement getCarryOnLabel(){
        return lbl_CarryOn;
    }

    public MobileElement getCarryOnText(){
        return txt_CarryOn;
    }

    public MobileElement getZoneLabel(){
        return lbl_Zone;
    }

    public MobileElement getZoneText(){
        return txt_Zone;
    }

    public MobileElement getSequenceNumberText(){
        return txt_SequenceNumber;
    }

    public MobileElement getBoardingPassSliderButton(){
        return btn_BoardingPassSlider;
    }

    public MobileElement getNotABoardingPassHeaderText(){
        return txt_NotABoardingPassHeader;
    }

    public MobileElement getCheckInUnavailableErrorMessageText(){
        return txt_CheckInUnavailableErrorMessage;
    }

    public MobileElement getInhibitedPassengerConfirmationCodeText(){
        return txt_InhibitedPassengerConfirmationCode;
    }

    public MobileElement getInhibitedPassengerDepartingTerminalText(){
        return txt_InhibitedPassengerDepartingTerminal;
    }

    public MobileElement getInhibitedPassengerFlightDurationText(){
        return txt_InhibitedPassengerFlightDuration;
    }

    public MobileElement getInhibitedPassengerArrivingTerminalText(){
        return txt_InhibitedPassengerArrivingTerminal;
    }

    public MobileElement getCheckInWithAgentText(){
        return txt_CheckInWithAgent;
    }

    public MobileElement getWeAreSorryMessageText(){
        return txt_WeAreSorryMessage;
    }

}