package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BagsPage {

    private AppiumDriver driver;

    public BagsPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Passenger DropDown
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvPassengerName")
    private MobileElement drpdwn_PassengerSelect;

    //Personal Item Image
    @AndroidFindBy(id = "com.spirit.customerapp:id/personal_item_title")
    private MobileElement img_PersonalItem;

    //Personal Item Dimension Inches
    @AndroidFindBy(id = "com.spirit.customerapp:id/personal_item_dimen_in")
    private MobileElement txt_PersonalItemDimensionInches;

    //Personal Item Dimension centimeters
    @AndroidFindBy(id = "com.spirit.customerapp:id/personal_item_dimen_cm")
    private MobileElement txt_PersonalItemDimensionCentimeters;

    //Personal Item Included
    @AndroidFindBy(id = "com.spirit.customerapp:id/personal_item_text")
    private MobileElement txt_PersonalItemIncluded;

    //CarryOn Image
    @AndroidFindBy(id = "com.spirit.customerapp:id/carry_on_item_title")
    private MobileElement img_CarryOn;

    //CarryOn Dimension Inches
    @AndroidFindBy(id = "com.spirit.customerapp:id/carry_on_item_dimen_in")
    private MobileElement txt_CarryOnDimensionInches;

    //CarryOn Dimension centimeters
    @AndroidFindBy(id = "com.spirit.customerapp:id/carry_on_item_dimen_cm")
    private MobileElement txt_CarryOnDimensionCentimeters;

    //CarryOn Add Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/carry_on_item_spinner")
    private MobileElement btn_CarryOnAdd;

    @AndroidFindBy(id = "android:id/title")
    private List<MobileElement> txt_CarryOnAddButtonOptions;

    //Checked Bags Image
    @AndroidFindBy(id = "com.spirit.customerapp:id/checked_in_item_title")
    private MobileElement img_CheckedBags;

    //Checked Bags Dimension Inches
    @AndroidFindBy(id = "com.spirit.customerapp:id/checked_in_item_dimen_in")
    private MobileElement txt_CheckedBagsDimensionInches;

    //Checked Bags Dimension centimeters
    @AndroidFindBy(id = "com.spirit.customerapp:id/checked_in_item_dimen_cm")
    private MobileElement txt_CheckedBagsDimensionCentimeters;

    //Checked Bags Add Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/checked_in_item_spinner")
    private MobileElement btn_CheckedBagsAdd;

    @AndroidFindBy(id = "android:id/title")
    private List<MobileElement> txt_CheckedBagsAddButtonOptions;

    //Bicycle Image
    @AndroidFindBy(id = "com.spirit.customerapp:id/bicycle_title")
    private MobileElement img_Bicycle;

    //Bicycle Description
    @AndroidFindBy(id = "com.spirit.customerapp:id/bicycle_text")
    private MobileElement txt_BicycleDescription;

    //Bicycle Add Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/bicycle_spinner")
    private MobileElement btn_BicycleAdd;

    //Surfboard Image
    @AndroidFindBy(id = "com.spirit.customerapp:id/surfboard_title")
    private MobileElement img_Surfboard;

    //Surfboard Dimension Description
    @AndroidFindBy(id = "com.spirit.customerapp:id/surfboard_text")
    private MobileElement txt_SurfboardDimensionDescription;

    //Surfboard Add Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/surfboard_spinner")
    private MobileElement btn_SurfboardAdd;

    //Keep Same Bags------
    @AndroidFindBy(id = "com.spirit.customerapp:id/all_flights_switch")
    private MobileElement swtch_SameBagsForReturnFlight;
    //Continue
    @AndroidFindBy(id = "com.spirit.customerapp:id/continue_button")
    private MobileElement btn_ContinueFromTheBagsPage;

    //Bags Upsell
    //Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/bag_your_pardon_header")
    private MobileElement txt_BagsUpsellPopUpHeader;

    //Body Text
    @AndroidFindBy(id = "com.spirit.customerapp:id/bag_your_pardon_text")
    private MobileElement txt_BagsUpsellPopUpMessage;

    //AddBagsButton
    @AndroidFindBy(id = "com.spirit.customerapp:id/add_bags_button")
    private MobileElement btn_BagsUpsellPopAddBags;

    //Next Passenger
    @AndroidFindBy(id = "com.spirit.customerapp:id/next_passenger")
    private MobileElement btn_BagsUpsellNextPassenger;
    //Next Flight
    @AndroidFindBy(id = "com.spirit.customerapp:id/next_flight")
    private MobileElement btn_BagsUpsellPopNextFlight;

    //Skip all Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/skip")
    private MobileElement btn_BagsUpsellPopUpSkipAll;

    //All checked, carry-on, Bicycle, surfboard Options
    @AndroidFindBy(id = "android:id/title")
    private List<MobileElement> txt_BagsPageOptions;

    @AndroidFindBy(id = "android:id/title") //after clicking on bags, bike, surfboard
    private List<MobileElement> btn_ListOfBags;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tvPassengerName") //after clicking passengers rop down
    private List<MobileElement> btn_PassengerSelectList;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getPassengerSelectDropDown(){
        return drpdwn_PassengerSelect;
    }

    public MobileElement getPersonalItemImage(){
        return img_PersonalItem;
    }

    public MobileElement getPersonalItemDimensionInchesText(){
        return txt_PersonalItemDimensionInches;
    }

    public MobileElement getPersonalItemDimensionCentimetersText(){
        return txt_PersonalItemDimensionCentimeters;
    }

    public MobileElement getPersonalItemIncludedText(){
        return txt_PersonalItemIncluded;
    }

    public MobileElement getCarryOnImage(){
        return img_CarryOn;
    }

    public MobileElement getCarryOnDimensionInchesText(){
        return txt_CarryOnDimensionInches;
    }

    public MobileElement getCarryOnDimensionCentimetersText(){
        return txt_CarryOnDimensionCentimeters;
    }

    public MobileElement getCarryOnAddButton(){
        return btn_CarryOnAdd;
    }

    public List<MobileElement> getCarryOnAddButtonOptionsText(){
        return txt_CarryOnAddButtonOptions;
    }

    public MobileElement getCheckedBagsImage(){
        return img_CheckedBags;
    }

    public MobileElement getCheckedBagsDimensionInchesText(){
        return txt_CheckedBagsDimensionInches;
    }

    public MobileElement getCheckedBagsDimensionCentimetersText(){
        return txt_CheckedBagsDimensionCentimeters;
    }
    public MobileElement getCheckedBagsAddButton(){
        return btn_CheckedBagsAdd;
    }

    public List<MobileElement> getCheckedBagsAddButtonOptionsText(){
        return txt_CheckedBagsAddButtonOptions;
    }

    public MobileElement getBicycleImage(){
        return img_Bicycle;
    }

    public MobileElement getBicycleDescriptionText(){
        return txt_BicycleDescription;
    }

    public MobileElement getBicycleAddButton(){
        return btn_BicycleAdd;
    }

    public MobileElement getSurfboardImage(){
        return img_Surfboard;
    }

    public MobileElement getSurfboardDimensionDescriptionText(){
        return txt_SurfboardDimensionDescription;
    }

    public MobileElement getSurfboardAddButton(){
        return btn_SurfboardAdd;
    }

    public MobileElement getSameBagsForReturnFlightSwitch(){
        return swtch_SameBagsForReturnFlight;
    }

    public MobileElement getContinueFromTheBagsPageButton(){
        return btn_ContinueFromTheBagsPage;
    }

    public MobileElement getBagsUpsellPopUpHeaderText(){
        return txt_BagsUpsellPopUpHeader;
    }

    public MobileElement getBagsUpsellPopUpMessageText(){
        return txt_BagsUpsellPopUpMessage;
    }

    public MobileElement getBagsUpsellPopAddBagsButton(){
        return btn_BagsUpsellPopAddBags;
    }

    public MobileElement getBagsUpsellNextPassengerButton(){
        return btn_BagsUpsellNextPassenger;
    }

    public MobileElement getBagsUpsellPopNextFlightButton(){
        return btn_BagsUpsellPopNextFlight;
    }

    public MobileElement getBagsUpsellPopUpSkipAllButton(){
        return btn_BagsUpsellPopUpSkipAll;
    }

    public List<MobileElement> getBagsPageOptionsText(){
        return txt_BagsPageOptions;
    }

    public List<MobileElement> getListOfBagsButton() {
        return btn_ListOfBags;
    }

    public List<MobileElement> getPassengerSelectList() {
        return btn_PassengerSelectList;
    }
}
