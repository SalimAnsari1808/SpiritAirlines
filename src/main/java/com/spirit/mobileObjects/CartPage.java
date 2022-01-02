package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    private AppiumDriver driver;

    public CartPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Flight
    //Origin Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_dep_code")
    private MobileElement txt_OriginStationCode;

    //Destination Station Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_arr_code")
    private MobileElement txt_DestinationStationCode;
    //Flight Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_amt")
    private MobileElement txt_FlightPrice;

    //Show/Hide Price Details Button
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.spirit.customerapp:id/card_stations']//android.widget.TextView[contains(@resource-id, 'price')]")
    private MobileElement btn_ShowHideFlightPriceDetails;

    //Flight Price Breakdown
    //Flight Fare Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv_total_flight_amt']//preceding::android.widget.TextView[1]")
    private MobileElement txt_FlightFare;

    //Flight fare Amount
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_flight_amt")
    private MobileElement txt_FlightFareAmount;

    //Taxes And Fees Label
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.spirit.customerapp:id/ll_flight_tax']//android.widget.LinearLayout//android.widget.TextView[1]")
    private MobileElement txt_FlightTax;

    //Taxes And Fees Amount
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.spirit.customerapp:id/ll_flight_tax']//android.widget.LinearLayout//android.widget.TextView[2]")
    private MobileElement txt_FlightTaxAmount;

    //Bags
    //Bags Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Bags']")
    private MobileElement lbl_Bags;

    //Number Of Bags
    @AndroidFindBy(id = "com.spirit.customerapp:id/count_bag")
    private MobileElement txt_NumberOfBags;

    //Bags Edit Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/edit_bags")
    private MobileElement btn_BagsEdit;

    //Bags Total Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_amt_bags")
    private MobileElement txt_BagsTotalPrice;

    //Show/Hide Price Details Button
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.spirit.customerapp:id/card_bags']//android.widget.TextView[contains(@resource-id, 'price')]")
    private MobileElement btn_ShowHideBagsPriceDetails;

    //Bags Breakdown
    //Carry On Label and Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_carry_on_bags_amt")
    private MobileElement txt_BagsBreakdownCarryOn;

    //Checked Bags Label and Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_checked_bags_amt")
    private MobileElement txt_BagsBreakdownCheckedBags;

    //Bicycle Label and Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_bicycle_amt")
    private MobileElement txt_BagsBreakdownBicycle;

    //SufBoard Label and Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_surfboard_amt")
    private MobileElement txt_BagsBreakdownSurfBoard;

    //Seats
    //Seats Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Seats']")
    private MobileElement lbl_Seats;

    //Number Of Seats
    @AndroidFindBy(id = "com.spirit.customerapp:id/seat_count")
    private MobileElement txt_NumberOfSeats;

    //Seats Edit Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/edit_seats")
    private MobileElement btn_SeatsEdit;

    //Seats Total Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_amt_seats")
    private MobileElement txt_SeatsTotalPrice;

    //Options
    //Options Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Options']")
    private MobileElement lbl_Options;

    //Number Of Options
    @AndroidFindBy(id = "com.spirit.customerapp:id/count_options")
    private MobileElement txt_NumberOfOptions;

    //Options Edit Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/edit_options")
    private MobileElement btn_OptionsEdit;

    //Options Total Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_amt_option")
    private MobileElement txt_OptionsTotalPrice;

    //Show/Hide Price Details Button
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.spirit.customerapp:id/card_options']//android.widget.TextView[contains(@resource-id, 'price')]")
    private MobileElement btn_ShowHideOptionsPriceDetails;

    //Options Breakdown
    //Flight Flex Label and Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_flex_travel_guard_amt")
    private MobileElement txt_OptionsBreakdownFLightFlex;

    //ShortCut Security Label and Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_ss_guard_amt")
    private MobileElement txt_OptionsBreakdownShortCutSecurity;

    //ShortCut Boarding Label and Price------------------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_total_sb_travel_guard_amt")
    private MobileElement txt_OptionsBreakdownBicycle;

    //Your Wallet
    //Your Wallet Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your Wallet']")
    private MobileElement lbl_YourWallet;

    //Card Number (Card number (XXXXXXXXX####) or "No Payment Method")
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvCreditCardNumber")
    private MobileElement txt_CardNumber;

    //Add Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_add_card")
    private MobileElement btn_Add;

    //Add Payment card details
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_title_toolbar")
    private MobileElement txt_AddPaymentScreenHeader;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_cardnumber")
    private MobileElement txtbx_AddPaymentScreenCardNumber;

    @AndroidFindBy(id = "com.spirit.customerapp:id/valid_thru")
    private MobileElement txtbx_AddPaymentScreenValidThru;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_cvv")
    private MobileElement txtbx_AddPaymentScreenCVV;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_fname")
    private MobileElement txtbx_AddPaymentFirstName;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_lname")
    private MobileElement txtbx_AddPaymentScreenLastName;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_address")
    private MobileElement txtbx_AddPaymentScreenAddress;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'img_chevron_address')]")
    private MobileElement img_AddPaymentScreenAddressArrow;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_city")
    private MobileElement txtbx_AddPaymentScreenCity;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_state")
    private MobileElement btn_AddPaymentScreenState;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_zipcode")
    private MobileElement txtbx_AddPaymentScreenZipCode;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_country")
    private MobileElement btn_AddPaymentScreenCountry;

    @AndroidFindBy(id = "com.spirit.customerapp:id/et_search")
    private MobileElement txtbx_SearchStateScreenSearch;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_airport_name")
    private List<MobileElement> txt_SearchStateScreenStateName;

    //country search page
    @AndroidFindBy(id = "com.spirit.customerapp:id/etSearch")
    private MobileElement txtbx_CountrySearch;

    @AndroidFindBy(id = "com.spirit.customerapp:id/name")
    private List<MobileElement> txt_CountryName;

    //Card Expiration-----------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvExpiry")
    private MobileElement txt_CardExpiration;

    //Order Summary
    //Order Summary Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Summary']")
    private MobileElement lbl_OrderSummary;
    //Subtotal Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Subtotal']")
    private MobileElement lbl_Subtotal;
    //Subtotal Amount
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_subtotal")
    private MobileElement txt_SubtotalAmount;

    //Taxes Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Taxes']")
    private MobileElement lbl_Taxes;
    //Taxes Amount
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_taxes")
    private MobileElement txt_TaxesAmount;

    //Total Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='total']")
    private MobileElement lbl_Total;

    //Total Amount
    @AndroidFindBy(id = "com.spirit.customerapp:id/total_price")
    private MobileElement txt_TotalAmount;

    //Terms and Conditions Label
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_msg_pay_terms_condition")
    private MobileElement lbl_TermsAndConditions;

    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_pay")
    private MobileElement btn_Pay;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getOriginStationCodeText(){
        return txt_OriginStationCode;
    }

    public MobileElement getDestinationStationCodeText(){
        return txt_DestinationStationCode;
    }

    public MobileElement getFlightPriceText(){
        return txt_FlightPrice;
    }

    public MobileElement getShowHideFlightPriceDetailsButton(){
        return btn_ShowHideFlightPriceDetails;
    }

    public MobileElement getFlightFareText(){
        return txt_FlightFare;
    }

    public MobileElement getFlightFareAmountText(){
        return txt_FlightFareAmount;
    }

    public MobileElement getFlightTaxText(){
        return txt_FlightTax;
    }

    public MobileElement getFlightTaxAmountText(){
        return txt_FlightTaxAmount;
    }

    public MobileElement getBagsLabel(){
        return lbl_Bags;
    }

    public MobileElement getNumberOfBagsText(){
        return txt_NumberOfBags;
    }

    public MobileElement getBagsEditButton(){
        return btn_BagsEdit;
    }

    public MobileElement getBagsTotalPriceButton(){
        return txt_BagsTotalPrice;
    }

    public MobileElement getShowHideBagsPriceDetailsButton(){
        return btn_ShowHideBagsPriceDetails;
    }

    public MobileElement getBagsBreakdownCarryOnText(){
        return txt_BagsBreakdownCarryOn;
    }

    public MobileElement getBagsBreakdownCheckedBagsText(){
        return txt_BagsBreakdownCheckedBags;
    }

    public MobileElement getBagsBreakdownBicycleText(){
        return txt_BagsBreakdownBicycle;
    }

    public MobileElement getBagsBreakdownSurfBoardText(){
        return txt_BagsBreakdownSurfBoard;
    }

    public MobileElement getSeatsLabel(){
        return lbl_Seats;
    }

    public MobileElement getNumberOfSeatsText(){
        return txt_NumberOfSeats;
    }

    public MobileElement getSeatsEditButton(){
        return btn_SeatsEdit;
    }

    public MobileElement getSeatsTotalPriceText(){
        return txt_SeatsTotalPrice;
    }

    public MobileElement getOptionsLabel(){
        return lbl_Options;
    }

    public MobileElement getNumberOfOptionsText(){
        return txt_NumberOfOptions;
    }

    public MobileElement getOptionsEditButton(){
        return btn_OptionsEdit;
    }

    public MobileElement getOptionsTotalPriceText(){
        return txt_OptionsTotalPrice;
    }

    public MobileElement getShowHideOptionsPriceDetailsButton(){
        return btn_ShowHideOptionsPriceDetails;
    }

    public MobileElement getOptionsBreakdownFLightFlexText(){
        return txt_OptionsBreakdownFLightFlex;
    }

    public MobileElement getOptionsBreakdownShortCutSecurity(){
        return txt_OptionsBreakdownShortCutSecurity;
    }

    public MobileElement getOptionsBreakdownBicycleText(){
        return txt_OptionsBreakdownBicycle;
    }

    public MobileElement getYourWalletLabel(){
        return lbl_YourWallet;
    }

    public MobileElement getCardNumberText(){
        return txt_CardNumber;
    }

    public MobileElement getAddBUtton(){
        return btn_Add;
    }

    public MobileElement getAddPaymentScreenHeaderText(){
        return txt_AddPaymentScreenHeader;
    }

    public MobileElement getAddPaymentScreenCardNumberTextBox(){
        return txtbx_AddPaymentScreenCardNumber;
    }

    public MobileElement getAddPaymentScreenValidThruTextBox(){
        return txtbx_AddPaymentScreenValidThru;
    }

    public MobileElement getAddPaymentScreenCVVTextBox(){
        return txtbx_AddPaymentScreenCVV;
    }

    public MobileElement getAddPaymentFirstNameTextBox(){
        return txtbx_AddPaymentFirstName;
    }

    public MobileElement getAddPaymentScreenLastNameTextBox(){
        return txtbx_AddPaymentScreenLastName;
    }

    public MobileElement getAddPaymentScreenAddressTextBox(){
        return txtbx_AddPaymentScreenAddress;
    }

    public MobileElement getAddPaymentScreenAddressArrowImage(){
        return img_AddPaymentScreenAddressArrow;
    }

    public MobileElement getAddPaymentScreenCityTextBox(){
        return txtbx_AddPaymentScreenCity;
    }

    public MobileElement getAddPaymentScreenStateButton(){
        return btn_AddPaymentScreenState;
    }

    public MobileElement getAddPaymentScreenZipCodeTextBox(){
        return txtbx_AddPaymentScreenZipCode;
    }

    public MobileElement getAddPaymentScreenCountryButton(){
        return btn_AddPaymentScreenCountry;
    }

    public MobileElement getSearchStateScreenSearchTextBox(){
        return txtbx_SearchStateScreenSearch;
    }

    public List<MobileElement> getSearchStateScreenStateNameText(){
        return txt_SearchStateScreenStateName;
    }

    public MobileElement getCountrySearchTextBox(){
        return txtbx_CountrySearch;
    }

    public List<MobileElement> getCountryNameText(){
        return txt_CountryName;
    }

    public MobileElement getCardExpirationText(){
        return txt_CardExpiration;
    }

    public MobileElement getOrderSummaryLabel(){
        return lbl_OrderSummary;
    }

    public MobileElement getSubtotalLabel(){
        return lbl_Subtotal;
    }

    public MobileElement getSubtotalAmountText(){
        return txt_SubtotalAmount;
    }

    public MobileElement getTaxesLabel(){
        return lbl_Taxes;
    }

    public MobileElement getTaxesAmountText(){
        return txt_TaxesAmount;
    }

    public MobileElement getTotalLabel(){
        return lbl_Total;
    }

    public MobileElement getTotalAmountText(){
        return txt_TotalAmount;
    }

    public MobileElement getTermsAndConditionsLabel(){
        return lbl_TermsAndConditions;
    }

    public MobileElement getPayButton(){
        return btn_Pay;
    }
}
