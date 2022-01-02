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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NavigationFooterPage {

    private AppiumDriver driver;

    public NavigationFooterPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Trips
    @AndroidFindBy(id = "com.spirit.customerapp:id/navigation_trip")
    private MobileElement btn_FootersTrips;

    //Book
    @AndroidFindBy(id = "com.spirit.customerapp:id/navigation_book")
    private MobileElement btn_FootersBook;

    //CheckIn
    @AndroidFindBy(id = "com.spirit.customerapp:id/navigation_checkin")
    private MobileElement btn_FootersCheckIn;

    //Flight Status
    @AndroidFindBy(id = "com.spirit.customerapp:id/navigation_status")
    private MobileElement btn_FootersFlightStatus;

    //More
    @AndroidFindBy(id = "com.spirit.customerapp:id/navigation_more")
    private MobileElement btn_FootersMore;


    //Loading PopUp
    //Loading Wheel
    @AndroidFindBy(id = "com.spirit.customerapp:id/progressWheel")
    private MobileElement btn_LoadingWheel;
    //Please Wait Text
    @AndroidFindBy(id = "com.spirit.customerapp:id/title_text")
    private MobileElement txt_PleaseWait;





    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getFootersTripsButton(){
        return btn_FootersTrips;
    }

    public MobileElement getFootersBookButton(){
        return btn_FootersBook;
    }

    public MobileElement getFootersCheckInButton(){
        return btn_FootersCheckIn;
    }

    public MobileElement getFootersFlightStatusButton(){
        return btn_FootersFlightStatus;
    }

    public MobileElement getFootersMoreButton(){
        return btn_FootersMore;
    }
}
