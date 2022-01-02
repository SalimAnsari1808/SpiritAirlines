package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class TripSummaryPage {

    private AppiumDriver driver;

    public TripSummaryPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Fare Type Label
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFcType")
    private MobileElement lbl_FareType;

    //Label NonRefundable and Taxes And Fees
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_footer_text")
    private MobileElement lbl_NonRefundableAndTaxesAndFees;

    //Depart Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'DEPART']")
    private MobileElement lbl_Depart;

    //Departure City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_from")
    private MobileElement txt_OutBoundDepartureCity;

    //Departure Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_from_code")
    private MobileElement txt_OutBoundDepartureStationCode;

    //Departure date
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_date")
    private MobileElement txt_OutBoundDepatureDate;

    //Departure time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_dep_time")
    private MobileElement txt_OutBoundDepartureTime;

    //Departing Flight Numbers
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFlightNumberDept")
    private MobileElement txt_OutBoundDepartureFlightNumber;

    //AircraftType
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFlightTypeDept")
    private MobileElement txt_OutBoundDepartureAircraftType;

    //Duration
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_travel_time")
    private MobileElement txt_OutBoundFlightDuration;

    //Number of Stops
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_travel_stop")
    private MobileElement txt_OutBoundNumberOfStops;

    //Arrival City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_to")
    private MobileElement txt_OutBoundArrivalCity;

    //Arrival Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_to_code")
    private MobileElement txt_OutBoundArrivalStationCode;

    //Arrival date
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_arr_date")
    private MobileElement txt_OutBoundArrivalDate;

    //Arrival time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_arr_time")
    private MobileElement txt_OutBoundArrivalTime;

    //Return Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'RETURN']")
    private MobileElement lbl_Return;

    //Departure City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFromCountry")
    private MobileElement txt_ReturnDepartureCity;

    //Departure Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFromCode")
    private MobileElement txt_ReturnDepartureStationCode;

    //Departure date
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFromDate")
    private MobileElement txt_ReturnDepatureDate;

    //Departure time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFromTime")
    private MobileElement txt_ReturnDepartureTime;

    //Departing Flight Numbers
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFlightNumberReturn")
    private MobileElement txt_ReturnDepartureFlightNumber;

    //AircraftType
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFlightTypeReturn")
    private MobileElement txt_ReturnDepartureAircraftType;

    //Duration
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvTravelDuration")
    private MobileElement txt_ReturnFlightDuration;

    //Number of Stops
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_flight_travel__dept_stop")
    private MobileElement txt_ReturnNumberOfStops;

    //Arrival City
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvToCountry")
    private MobileElement txt_ReturnArrivalCity;

    //Arrival Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvToCode")
    private MobileElement txt_ReturnArrivalStationCode;

    //Arrival date
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvToDate")
    private MobileElement txt_ReturnArrivalDate;

    //Arrival time
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvToTime")
    private MobileElement txt_ReturnArrivalTime;

    //Bags and Fees Link (Opens the Spirit.com webpage)
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_bags_and_fees")
    private MobileElement btn_ViewBagsAndOptionalServices;

    //Continue Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnContinue")
    private MobileElement btn_ContinueFromTripSummaryPage;

    //Refundability Page

    //Refundability Message Text
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvRefundability")
    private MobileElement txt_RefundabilityMessage;

    //Taxes and Fees Page

    //Taxes and Fees Message Text
    @AndroidFindBy(xpath = "//android.webkit.WebView/android.webkit.WebView")
    private MobileElement txt_TaxesAndFeesMessage;


    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getFareTypeLabel(){
        return lbl_FareType;
    }

    public MobileElement getNonRefundableAndTaxesAndFeesLabel(){
        return lbl_NonRefundableAndTaxesAndFees;
    }

    public MobileElement getDepartLabel(){
        return lbl_Depart;
    }

    public MobileElement getOutBoundDepartureCityText(){
        return txt_OutBoundDepartureCity;
    }

    public MobileElement getOutBoundDepartureStationCodeText(){
        return txt_OutBoundDepartureStationCode;
    }

    public MobileElement getOutBoundDepatureDateText(){
        return txt_OutBoundDepatureDate;
    }

    public MobileElement getOutBoundDepartureTimeText(){
        return txt_OutBoundDepartureTime;
    }

    public MobileElement getOutBoundDepartureFlightNumberText(){
        return txt_OutBoundDepartureFlightNumber;
    }

    public MobileElement getOutBoundDepartureAircraftTypeText(){
        return txt_OutBoundDepartureAircraftType;
    }

    public MobileElement getOutBoundFlightDurationText(){
        return txt_OutBoundFlightDuration;
    }

    public MobileElement getOutBoundNumberOfStopsText(){
        return txt_OutBoundNumberOfStops;
    }

    public MobileElement getOutBoundArrivalCityText(){
        return txt_OutBoundArrivalCity;
    }

    public MobileElement getOutBoundArrivalStationCodeText(){
        return txt_OutBoundArrivalStationCode;
    }

    public MobileElement getOutBoundArrivalDateText(){
        return txt_OutBoundArrivalDate;
    }

    public MobileElement getOutBoundArrivalTimeText(){
        return txt_OutBoundArrivalTime;
    }

    public MobileElement getReturnLabel(){
        return lbl_Return;
    }

    public MobileElement getReturnDepartureCityText(){
        return txt_ReturnDepartureCity;
    }

    public MobileElement getReturnDepartureStationCodeText(){
        return txt_ReturnDepartureStationCode;
    }

    public MobileElement getReturnDepatureDateText(){
        return txt_ReturnDepatureDate;
    }

    public MobileElement getReturnDepartureTimeText(){
        return txt_ReturnDepartureTime;
    }

    public MobileElement getReturnDepartureFlightNumberText(){
        return txt_ReturnDepartureFlightNumber;
    }

    public MobileElement getReturnDepartureAircraftTypeText(){
        return txt_ReturnDepartureAircraftType;
    }

    public MobileElement getReturnFlightDurationText(){
        return txt_ReturnFlightDuration;
    }

    public MobileElement getReturnNumberOfStopsText(){
        return txt_ReturnNumberOfStops;
    }

    public MobileElement getReturnArrivalCityText(){
        return txt_ReturnArrivalCity;
    }

    public MobileElement getReturnArrivalStationCodeText(){
        return txt_ReturnArrivalStationCode;
    }

    public MobileElement getReturnArrivalDateText(){
        return txt_ReturnArrivalDate;
    }

    public MobileElement getReturnArrivalTimeText(){
        return txt_ReturnArrivalTime;
    }

    public MobileElement getViewBagsAndOptionalServicesButton(){
        return btn_ViewBagsAndOptionalServices;
    }

    public MobileElement getContinueFromTripSummaryPageButton(){
        return btn_ContinueFromTripSummaryPage;
    }

    public MobileElement getRefundabilityMessageText(){
        return txt_RefundabilityMessage;
    }

    public MobileElement getTaxesAndFeesMessageText(){
        return txt_TaxesAndFeesMessage;
    }
}