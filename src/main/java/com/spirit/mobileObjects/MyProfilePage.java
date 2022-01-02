package com.spirit.mobileObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage {

    private AppiumDriver driver;

    public MyProfilePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //More page header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv.miles")
    private MobileElement txt_PageHeader;

    //Sign Out Button
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Sign out']")
    private MobileElement btn_SignOut;

    //Back Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/iv_back_toolbar")
    private MobileElement btn_BackButton;

    //Wdit Image Button
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.spirit.customerapp:id/profile_image']//following-sibling::android.widget.ImageView")
    private MobileElement btn_EditImage;

    //User Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv.userName")
    private MobileElement txt_UserName;

    //Points Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv.miles']//preceding::android.widget.TextView[1]")
    private MobileElement lbl_SpiritPoints;

    //Points
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv.miles")
    private MobileElement txt_SpiritPoints;

    //Free Spirit Number Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv.freeSpirit']//preceding::android.widget.TextView[1]")
    private MobileElement lbl_FreeSpiritNumber;

    //Free Spirit Number
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv.freeSpirit")
    private MobileElement txt_FreeSpiritNumber;

    //Member Since Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/tv.memberSince']//preceding::android.widget.TextView[1]")
    private MobileElement lbl_MemberSince;

    //Member Since Date
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv.memberSince")
    private MobileElement txt_MemberSinceDate;

    //Enroll in $9FC Label
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id= 'com.spirit.customerapp:id/btn_9_join']//preceding::android.widget.TextView[1]")
    private MobileElement lbl_EnrollFor9DFC;

    //Join $9FC Button(This button has no Functionality)
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_9_join")
    private MobileElement btn_9DFCJoin;

    //Sign Out Pop Up
    //Warning Image
    @AndroidFindBy(id = "com.spirit.customerapp:id/error_x")
    private MobileElement img_SignOutPopUpWarning;

    //Sign Out Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/title_text")
    private MobileElement txt_SignOutPopUpHeader;

    //Sign Out Message
    @AndroidFindBy(id = "com.spirit.customerapp:id/content_text")
    private MobileElement txt_SignOutPopMessage;

    //No Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/cancel_button")
    private MobileElement btn_SignOutPopNo;

    //Yes Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/confirm_button")
    private MobileElement btn_SignOutPopYes;

    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    //page header
    public MobileElement getPageHeaderText(){
        return txt_PageHeader;
    }

    //Sign Out Button
    public MobileElement getSignOutButton(){
        return btn_SignOut;
    }

    //Back Button
    public MobileElement getBackButton(){
        return btn_BackButton;
    }

    //Wdit Image Button
    public MobileElement getEditImageButton(){
        return btn_EditImage;
    }

    //User Name
    public MobileElement getUserNameText(){
        return txt_UserName;
    }

    //Points Label
    public MobileElement getSpiritPointsLabel(){
        return lbl_SpiritPoints;
    }

    //Points
    public MobileElement getSpiritPointsText(){
        return txt_SpiritPoints;
    }

    //Free Spirit Number Label
    public MobileElement getFreeSpiritNumberLabel(){
        return lbl_FreeSpiritNumber;
    }

    //Free Spirit Number
    public MobileElement getFreeSpiritNumberText(){
        return txt_FreeSpiritNumber;
    }

    //Member Since Label
    public MobileElement getMemberSinceLabel(){
        return lbl_MemberSince;
    }

    //Member Since Date
    public MobileElement getMemberSinceDateText(){
        return txt_MemberSinceDate;
    }

    //Enroll in $9FC Label
    public MobileElement getEnrollFor9DFCLabel(){
        return lbl_EnrollFor9DFC;
    }

    //Join $9FC Button(This button has no Functionality)
    public MobileElement get9DFCJoinButton(){
        return btn_9DFCJoin;
    }

    //Sign Out Pop Up
    //Warning Image
    public MobileElement getSignOutPopUpWarningImage(){
        return img_SignOutPopUpWarning;
    }


    //Sign Out Header
    public MobileElement getSignOutPopUpHeaderText(){
        return txt_SignOutPopUpHeader;
    }

    //Sign Out Message
    public MobileElement getSignOutPopMessageText(){
        return txt_SignOutPopMessage;
    }

    //No Button
    public MobileElement getSignOutPopNoButton(){
        return btn_SignOutPopNo;
    }

    //Yes Button
    public MobileElement getSignOutPopYesButton(){
        return btn_SignOutPopYes;
    }

}