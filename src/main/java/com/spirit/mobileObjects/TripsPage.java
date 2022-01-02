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

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.util.*;

public class TripsPage{

private AppiumDriver driver;

    public TripsPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //*******************************************************************
    //**************************Top Ribbon *****************************
    //*******************************************************************
    //Page Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_title_toolbar")
    private MobileElement txt_TripsHeaderTrips;

    //Sign in
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_signin")
    private MobileElement btn_TripsHeaderSignIn;

    //Add plus button
    @AndroidFindBy(id = "com.spirit.customerapp:id/iv_right")
    private MobileElement btn_TripsHeaderPlus;

    //*******************************************************************
    //*************************Middle Content****************************
    //*******************************************************************
    //Book Trip Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_book_trip")
    private MobileElement btn_MiddleContentBookATrip;

    //    //Find Trip Button
    //    @AndroidFindBy(id = "com.spirit.customerapp:id/btn_add_trip")
    //    private MobileElement btn_FindTrip;
    //******************Find a Trip SubPage******************************
    //*******************************************************************

    //Cancel Button
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = 'com.spirit.customerapp:id/toolbar']//android.widget.TextView[contains(@text, 'Cancel')]")
    private MobileElement btn_FindATripCancel;

    //Last Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_last_name")
    private MobileElement txtbx_LastName;

    //Last Name
    @AndroidFindBy(id = "com.spirit.customerapp:id/et_confirmation_code")
    private MobileElement txtbx_ConfirmationCode;

    //Add plus button
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'FIND TRIP')]")
    private MobileElement btn_MiddleContentFindTrip;

    //*******************************************************************
    //*************Logged In Member Listed Trips*************************
    //*******************************************************************
    //Upcoming Flight List
    @AndroidFindBy(id = "com.spirit.customerapp:id/parent_frame")
    private List<MobileElement> frm_UpcomingTripsList;

    //Origin and destination
    @AndroidFindBy(id = "com.spirit.customerapp:id/origin_dest_pair")
    private List<MobileElement> txt_TripListOriginAndDestination ;

    //Date of travel
    @AndroidFindBy(id = "com.spirit.customerapp:id/date_departure_txt")
    private List<MobileElement> txt_TripListDateOftTavel ;

    //Confirmation Code
    @AndroidFindBy(id = "com.spirit.customerapp:id/recordlocator_txt")
    private List<MobileElement> txt_TripListConfirmationCode ;

    //days to go
    @AndroidFindBy(id = "com.spirit.customerapp:id/text_remaining_time")
    private List<MobileElement> txt_TripListTimeRemaining;

    //Boarding pass/CheckIn Available Text
    @AndroidFindBy(id = "com.spirit.customerapp:id/text_checkin")
    private List<MobileElement> txt_CheckInAvailable ;
    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/
    public MobileElement getTripsHeaderTripsText(){
        return txt_TripsHeaderTrips;
    }

    public MobileElement getTripsHeaderSignInButton(){
        return btn_TripsHeaderSignIn;
    }

    public MobileElement getTripsHeaderPlusButton(){
        return btn_TripsHeaderPlus;
    }

    //*******************************************************************
    //*************************Middle Content****************************
    //*******************************************************************

    public MobileElement getMiddleContentBookATripButton(){
        return btn_MiddleContentBookATrip;
    }

    public MobileElement getFindATripCancelButton(){
        return btn_FindATripCancel;
    }

    public MobileElement getLastNameTextBox(){
        return txtbx_LastName;
    }

    public MobileElement getConfirmationCodeTextBox(){
        return txtbx_ConfirmationCode;
    }

    public MobileElement getMiddleContentFindTripButton(){
        return btn_MiddleContentFindTrip;
    }

    //*******************************************************************
    //*************Logged In Member Listed Trips*************************
    //*******************************************************************

    public List<MobileElement> getUpcomingTripsListFrame(){
        return frm_UpcomingTripsList;
    }

    public List<MobileElement> getTripListOriginAndDestinationText(){
        return txt_TripListOriginAndDestination;
    }

    public List<MobileElement> getTripListDateOftTavelText(){
        return txt_TripListDateOftTavel;
    }

    public List<MobileElement> getTripListConfirmationCodeText(){
        return txt_TripListConfirmationCode;
    }

    public List<MobileElement> getTripListTimeRemainingText(){
        return txt_TripListTimeRemaining;
    }

    public List<MobileElement> getCheckInAvailableText(){
        return txt_CheckInAvailable;
    }



    public void allowNotification(){

        WebDriverWait explicitWait = new WebDriverWait(driver, 20);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.spirit.customerapp:id/confirm_button")));
        MobileElement allowButton = (MobileElement) driver.findElementById("com.spirit.customerapp:id/confirm_button");
        allowButton.click();
        System.out.println("Successfully Launched Spirit Native App and closed Allow Notification");
    }


}