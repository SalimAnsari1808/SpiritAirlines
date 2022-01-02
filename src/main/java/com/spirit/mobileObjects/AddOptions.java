package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class AddOptions {

    private AppiumDriver driver;

    public AddOptions(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Add Flight Flex Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/add_ff_text")
    private MobileElement txt_AddFlightFlexHeader;

    //Flight Flex Description
    @AndroidFindBy(id = "com.spirit.customerapp:id/ff_detailed_text")
    private MobileElement txt_FlightFlexDescription;

    //Flight Flex Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/txt_flight_flex_amount")
    private MobileElement txt_FlightFlexPrice;

    //Flight Flex Switch
    @AndroidFindBy(id = "com.spirit.customerapp:id/switchflight")
    private MobileElement swtch_FlightFlex;

    //Add ShortCut Security Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/add_ss_text")
    private MobileElement txt_AddShortCutSecurityHeader;

    //ShortCut Security Description
    @AndroidFindBy(id = "com.spirit.customerapp:id/ss_detailed_text")
    private MobileElement txt_ShortCutSecurityDescription;

    //ShortCut Security Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/txt_security_from")
    private MobileElement txt_ShortCutSecurityPrice;

    //ShortCut Security Switch
    @AndroidFindBy(id = "com.spirit.customerapp:id/switchSecurity")
    private MobileElement swtch_ShortCutSecurity;

    //Add ShortCut Boarding Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/add_sb_text")
    private MobileElement txt_AddShortCutBoardingHeader;

    //ShortCut Boarding Description
    @AndroidFindBy(id = "com.spirit.customerapp:id/sb_detailed_text")
    private MobileElement txt_ShortCutBoardingDescription;

    //ShortCut Boarding Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/txt_boarding_from")
    private MobileElement txt_ShortCutBoardingPrice;

    //ShortCut Boarding Switch
    @AndroidFindBy(id = "com.spirit.customerapp:id/switchBoarding")
    private MobileElement swtch_ShortCutBoarding;

    //ShortCut Security Journey Select
    @AndroidFindBy(id = "com.spirit.customerapp:id/onewayTripOriDestText")
    private MobileElement txt_ShortCutSecurityDepartingOriginAndDestiantion;

    //Departing
    @AndroidFindBy(id = "com.spirit.customerapp:id/onewayPriceText")
    private MobileElement txt_ShortCutSecurityDepartingPrice;

    @AndroidFindBy(id = "com.spirit.customerapp:id/switchPrice")
    private MobileElement swtch_ShortCutSecurityDepartingSelect;

    @AndroidFindBy(id = "com.spirit.customerapp:id/roundTripOriDestText")
    private MobileElement txt_ShortCutSecurityReturnOriginAndDestiantion;

    @AndroidFindBy(id = "com.spirit.customerapp:id/roundTripPriceText")
    private MobileElement txt_ShortCutSecurityReturnPrice;

    @AndroidFindBy(id = "com.spirit.customerapp:id/switchPriceReturnTrips")
    private MobileElement swtch_ShortCutSecurityReturnSelect;

    //Continue
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_review_and_pay")
    private MobileElement btn_ContinueFromOptions;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getAddFlightFlexHeaderText(){
        return txt_AddFlightFlexHeader;
    }

    public MobileElement getFlightFlexDescriptionText(){
        return txt_FlightFlexDescription;
    }

    public MobileElement getFlightFlexPriceText(){
        return txt_FlightFlexPrice;
    }

    public MobileElement getFlightFlexSwitch(){
        return swtch_FlightFlex;
    }

    public MobileElement getAddShortCutSecurityHeaderText(){
        return txt_AddShortCutSecurityHeader;
    }

    public MobileElement getShortCutSecurityDescriptionText(){
        return txt_ShortCutSecurityDescription;
    }

    public MobileElement getShortCutSecurityPriceText(){
        return txt_ShortCutSecurityPrice;
    }

    public MobileElement getShortCutSecuritySwitch(){
        return swtch_ShortCutSecurity;
    }

    public MobileElement getAddShortCutBoardingHeaderText(){
        return txt_AddShortCutBoardingHeader;
    }

    public MobileElement getShortCutBoardingDescriptionText(){
        return txt_ShortCutBoardingDescription;
    }

    public MobileElement getShortCutBoardingPriceText(){
        return txt_ShortCutBoardingPrice;
    }

    public MobileElement getShortCutBoardingSwitch(){
        return swtch_ShortCutBoarding;
    }

    public MobileElement getShortCutSecurityDepartingOriginAndDestiantionText(){
        return txt_ShortCutSecurityDepartingOriginAndDestiantion;
    }

    public MobileElement getShortCutSecurityDepartingPriceText(){
        return txt_ShortCutSecurityDepartingPrice;
    }

    public MobileElement getShortCutSecurityDepartingSelectSwitch(){
        return swtch_ShortCutSecurityDepartingSelect;
    }

    public MobileElement getShortCutSecurityReturnOriginAndDestiantionText(){
        return txt_ShortCutSecurityReturnOriginAndDestiantion;
    }

    public MobileElement getShortCutSecurityReturnPriceText(){
        return txt_ShortCutSecurityReturnPrice;
    }

    public MobileElement getShortCutSecurityReturnSelectSwitch(){
        return swtch_ShortCutSecurityReturnSelect;
    }

    public MobileElement getContinueFromOptionsButton(){
        return btn_ContinueFromOptions;
    }
}
