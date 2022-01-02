package com.spirit.mobileObjects;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.*;

public class CheckInPage {

  private AppiumDriver driver;

  public CheckInPage(AppiumDriver driver) {
  //Check In Page
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  //Last Name
  @AndroidFindBy(id = "com.spirit.customerapp:id/et_last_name")
  private MobileElement txtbx_CheckInLastName;

  //Confirmation Code
  @AndroidFindBy(id = "com.spirit.customerapp:id/et_confirmation_code")
  private MobileElement txtbx_CheckInConfirmationCode;

  //Check In Button
  @AndroidFindBy(id = "com.spirit.customerapp:id/btn_check_in")
  private MobileElement btn_CheckIn;

  @AndroidFindBy(id = "com.spirit.customerapp:id/navigation_checkin")
  private MobileElement btn_NavCheckIn;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_title_toolbar")
  private MobileElement txt_CheckInTitle;

  @AndroidFindBy(id = "com.spirit.customerapp:id/et_last_name")
  private MobileElement txtbx_LastNameInput;

  @AndroidFindBy(id = "com.spirit.customerapp:id/et_confirmation_code")
  private MobileElement txtbx_ConfirmationCodeInput;

  //Trip Details
  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_title_toolbar")
  private MobileElement txt_TripDetailTitle;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_number")
  private MobileElement txt_FlightNumber;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_confirmation")
  private MobileElement txt_ConfirmationCode;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_from_city")
  private MobileElement txt_DepartureCity;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_from_citylight_from_code")
  private MobileElement txt_DepartureCityAirportCode;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_to_city")
  private MobileElement txt_ArrivalCity;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_to_code")
  private MobileElement txt_ArrivalCityAirportCode;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_status")
  private MobileElement txt_FlightStatus;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_date")
  private MobileElement txt_DepartureDate;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_time")
  private MobileElement txt_DepartureTime;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_schedule")
  private MobileElement txt_DepartureSchedule;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dep_terminal_name")
  private MobileElement txt_DepartureCityTerminal;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dep_gate")
  private MobileElement txt_DepartureCityGate;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dep_weather")
  private MobileElement txt_DepartureCityWeather;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_travel_time")
  private MobileElement txt_TravelTime;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_arr_date")
  private MobileElement txt_ArrivalDate;

  @AndroidFindBy(id = "	com.spirit.customerapp:id/tv_flight_arr_time")
  private MobileElement txt_ArrivalTime;

  @AndroidFindBy(id = "	com.spirit.customerapp:id/tv_flight_arr_schedule")
  private MobileElement txt_ArrivalSchedule;

  @AndroidFindBy(id = "	com.spirit.customerapp:id/tv_arr_terminal_name")
  private MobileElement txt_ArrivalTerminal;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_arr_gate")
  private MobileElement txt_ArrivalGate;

  @AndroidFindBy(id = "com.spirit.customerapp:id/tv_arr_weather")
  private MobileElement txt_ArrivalWeather;

  /*******************************************************************
   ************************Getter Methods****************************
   ******************************************************************/

  public MobileElement getCheckInLastNameTextBox(){
    return txtbx_CheckInLastName;
  }

  public MobileElement getCheckInConfirmationCodeTextBox(){
    return txtbx_CheckInConfirmationCode;
  }

  public MobileElement getCheckInButton(){
    return btn_CheckIn;
  }

  public MobileElement getNavCheckInButton() {
    return btn_NavCheckIn;
  }

  public MobileElement getCheckInTitleText(){
    return txt_CheckInTitle;
  }

  public MobileElement getLastNameInputTextBox() {
    return txtbx_LastNameInput;
  }

  public MobileElement getConfirmationCodeInputTextBox() {
    return txtbx_ConfirmationCodeInput;
  }

  public MobileElement getTripDetailTitleText(){
    return txt_TripDetailTitle;
  }

  public MobileElement getFlightNumberText(){
    return txt_FlightNumber;
  }

  public MobileElement getConfirmationCodeTextBox(){
    return txt_ConfirmationCode;
  }

  public MobileElement getDepartureCityText() {
    return txt_DepartureCity;
  }

  public MobileElement getDepartureCityAirportCodeText(){
    return txt_DepartureCityAirportCode;
  }

  public MobileElement getArrivalCityText(){
    return txt_ArrivalCity;
  }

  public MobileElement getArrivalCityAirportCodeText(){
    return txt_ArrivalCityAirportCode;
  }

  public MobileElement getFlightStatusText(){
    return txt_FlightStatus;
  }

  public MobileElement getDepartureDateText(){
    return txt_DepartureDate;
  }

  public MobileElement getDepartureTimeText(){
    return txt_DepartureTime;
  }

  public MobileElement getDepartureScheduleText(){
    return txt_DepartureSchedule;
  }

  public MobileElement getDepartureCityTerminalText(){
    return txt_DepartureCityTerminal;
  }

  public MobileElement getDepartureCityGateText(){
    return txt_DepartureCityGate;
  }

  public MobileElement getDepartureCityWeatherText(){
    return txt_DepartureCityWeather;
  }

  public MobileElement getTravelTimeText(){
    return txt_TravelTime;
  }

  public MobileElement getArrivalDateText(){
    return txt_ArrivalDate;
  }

  public MobileElement getArrivalTimeText(){
    return txt_ArrivalTime;
  }

  public MobileElement getArrivalScheduleText(){
    return txt_ArrivalSchedule;
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

}
