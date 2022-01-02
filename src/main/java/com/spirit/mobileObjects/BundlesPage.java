package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BundlesPage {

    private AppiumDriver driver;

    public BundlesPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Number of Bundle Options/pages
    @AndroidFindBy(xpath = "//android.support.v7.app.ActionBar.Tab")
    private MobileElement btn_BundlesSlider;

    //Bundle Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvBundleTypeHeader")
    private MobileElement txt_NameOfBundle;

    //Pick seat
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pick Seat']")
    private MobileElement txt_PickSeat;

    //Personal Item
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Personal Item']")
    private MobileElement txt_PesronalItem;

    //CarryOn
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvCarryOnBagD")
    private MobileElement txt_CarryOn;

    //checked Bag
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvChecked")
    private MobileElement txt_CheckedBag;

    //Shortcut Boarding
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvShourtCut")
    private MobileElement txt_ShortcutBoarding;

    //Flight Flex
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvFlightFlexD")
    private MobileElement txt_FlightFlex;

    //Double Miles-----------------------------------------------------------------
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvMiles")
    private MobileElement txt_DoubleMiles;

    //A la Carte Label
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'La Carte Price')]")
    private MobileElement lbl_ALaCarte;

    //A la Carte Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvLaCartePrice")
    private MobileElement txt_ALaCartePrice;

    //vs label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'VS']")
    private MobileElement lbl_Vs;

    //Bundle Label
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvBundleType")
    private MobileElement lbl_BundlePrice;

    //Bundle Price
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvBundlePrice")
    private MobileElement txt_BundlePrice;

    //Net Savings Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Net Savings']")
    private MobileElement lbl_NetSavings;

    //Net Savings amount
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvNetSaving")
    private MobileElement txt_NetSavingsAmount;

    //Add Bundle Button (Button text changes to "Remove" once bundle is added)
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnAddBundle")
    private MobileElement txt_AddBundles;

    //Continue Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnContinue")
    private MobileElement btn_ContinueFromBundles;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getBundlesSliderButton(){
        return btn_BundlesSlider;
    }

    public MobileElement getNameOfBundleText(){
        return txt_NameOfBundle;
    }

    public MobileElement getPickSeatText(){
        return txt_PickSeat;
    }

    public MobileElement getPesronalItemText(){
        return txt_PesronalItem;
    }

    public MobileElement getCarryOnText(){
        return txt_CarryOn;
    }

    public MobileElement getCheckedBagText(){
        return txt_CheckedBag;
    }

    public MobileElement getShortcutBoardingText(){
        return txt_ShortcutBoarding;
    }

    public MobileElement getFlightFlexText(){
        return txt_FlightFlex;
    }

    public MobileElement getDoubleMilesText(){
        return txt_DoubleMiles;
    }

    public MobileElement getALaCarteLabel(){
        return lbl_ALaCarte;
    }

    public MobileElement getALaCartePriceText(){
        return txt_ALaCartePrice;
    }

    public MobileElement getVsLabel(){
        return lbl_Vs;
    }

    public MobileElement getBundlePriceLabel(){
        return lbl_BundlePrice;
    }

    public MobileElement getBundlePriceText(){
        return txt_BundlePrice;
    }

    public MobileElement getNetSavingsLabel(){
        return lbl_NetSavings;
    }

    public MobileElement getNetSavingsAmountText(){
        return txt_NetSavingsAmount;
    }

    public MobileElement getAddBundlesText(){
        return txt_AddBundles;
    }

    public MobileElement getContinueFromBundlesButton(){
        return btn_ContinueFromBundles;
    }
}
