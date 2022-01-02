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
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class SeatsPage {

    private AppiumDriver driver;

    public SeatsPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Seat Key
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_done")
    private MobileElement btn_SeatKey;

    //Passenger DropDown
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvPassengerName")
    private MobileElement drpdwn_PassengerSelect;

    //Available Big Front Seats
    @AndroidFindBy(id = "com.spirit.customerapp:id/imgBtnBFSSeat")
    private List<MobileElement> img_AvailableBigFrontSeats;

    //Available Exit Seats
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnExitSeat")
    private List<MobileElement> img_AvailableExitRowSeats;

    //Available regular Seats
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnNormalSeat")
    private List<MobileElement> img_AvailableRegularSeats;

    //Unavailable Seats
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnNonAssignableSeat")
    private List<MobileElement> img_UnavailableSeats;

    //Continue Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnSkip")
    private MobileElement btn_Continue;

    /////////Seat Key//////
    //Seat Key Header
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'SEAT KEY']")
    private MobileElement txt_SeatKeyHeader;

    //Big Front Seat
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Big Front Seat']")
    private MobileElement txt_BigFrontSeatKey;

    //Big Front Seat Color
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout//android.view.View[@index = '0'])[1]")
    private MobileElement img_BigFrontSeatColor;

    //Big Front Seat legroom text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Up to 11 inches more legroom']")
    private MobileElement txt_BigFrontSeatLegRoom;

    //Regular Seat
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Regular Seat']")
    private MobileElement txt_RegularSeatKey;

    //Regular Seat Color
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout//android.view.View[@index = '0'])[2]")
    private MobileElement img_RegularSeatColor;

    //Regular Seat leg room
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= '10 inches legroom']")
    private MobileElement txt_RegularSeatLegRoom;

    //Selection
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Selection']")
    private MobileElement txt_SelectionKey;

    //Selection Color
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout//android.view.View[@index = '0'])[3]")
    private MobileElement img_SelectionColor;

    //Exit Seat
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Exit Seat']")
    private MobileElement txt_ExitRowSeatKey;

    //Exit Seat Color
    @AndroidFindBy(xpath = "(//android.widget.LinearLayout//android.view.View[@index = '0'])[4]")
    private MobileElement img_ExitRowSeatColor;

    //Unavailable Text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'Unavailable']")
    private MobileElement txt_UnavailableSeatKey;

    //Okay
    @AndroidFindBy(id = "com.spirit.customerapp:id/ok_button")
    private MobileElement btn_SeatKeyOkay;

    ///////Exit Row Pop-Up//////
    //Header
    @AndroidFindBy(id = "com.spirit.customerapp:id/tv_title_toolbar")
    private MobileElement txt_ExitRowPopUpHeader;

    //Accept and Continue
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnAcceptContinue")
    private MobileElement btn_ExitRowPopUpAcceptAndContinue;

    //Change Seat
    @AndroidFindBy(id = "com.spirit.customerapp:id/tvChangeSeat")
    private MobileElement btn_ExitRowPopUpChangeSeat;

    //************************************************************************
    //***************Love at first seat PopUp*********************************
    //************************************************************************

    //Seats Upsell
    //Header
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='LOVE AT FIRST SEAT!']")
    private MobileElement txt_SeatUpsellPopUpHeader;
    //Body Text
    @AndroidFindBy(id = "com.spirit.customerapp:id/popupTv")
    private MobileElement txt_SeatUpsellPopUpMessage;

    //Choose seat Button
    @AndroidFindBy(id = "com.spirit.customerapp:id/btnChooseSeat")
    private MobileElement btn_LoveFirstSeatPopUpChooseSeat;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tvSkipFlight")
    private MobileElement btn_LoveFirstSeatPopUpSkipFlight;

    @AndroidFindBy(id = "com.spirit.customerapp:id/tvSkipAll")
    private MobileElement btn_LoveFirstSeatPopUpSkipAll;


    /*******************************************************************
     ************************Getter Methods****************************
     ******************************************************************/

    public MobileElement getSeatKeyButton(){
        return btn_SeatKey;
    }

    public MobileElement getPassengerSelectDropDown(){
        return drpdwn_PassengerSelect;
    }

    public List<MobileElement> getAvailableBigFrontSeatsImage(){
        return img_AvailableBigFrontSeats;
    }

    public List<MobileElement> getAvailableExitRowSeatsImage(){
        return img_AvailableExitRowSeats;
    }

    public List<MobileElement> getAvailableRegularSeatsImage(){
        return img_AvailableRegularSeats;
    }

    public List<MobileElement> getUnavailableSeatsImage(){
        return img_UnavailableSeats;
    }

    public MobileElement getContinueButton(){
        return btn_Continue;
    }

    public MobileElement getSeatKeyHeaderText(){
        return txt_SeatKeyHeader;
    }

    public MobileElement getBigFrontSeatKeyText(){
        return txt_BigFrontSeatKey;
    }

    public MobileElement getBigFrontSeatColorImage(){
        return img_BigFrontSeatColor;
    }

    public MobileElement getBigFrontSeatLegRoomText(){
        return txt_BigFrontSeatLegRoom;
    }

    public MobileElement getRegularSeatKeyText(){
        return txt_RegularSeatKey;
    }

    public MobileElement getRegularSeatColorImage(){
        return img_RegularSeatColor;
    }

    public MobileElement getRegularSeatLegRoomText(){
        return txt_RegularSeatLegRoom;
    }

    public MobileElement getSelectionKeyText(){
        return txt_SelectionKey;
    }

    public MobileElement getSelectionColorImage(){
        return img_SelectionColor;
    }

    public MobileElement getExitRowSeatKeyText(){
        return txt_ExitRowSeatKey;
    }

    public MobileElement getExitRowSeatColorImage(){
        return img_ExitRowSeatColor;
    }

    public MobileElement getUnavailableSeatKeyText(){
        return txt_UnavailableSeatKey;
    }

    public MobileElement getSeatKeyOkayButton(){
        return btn_SeatKeyOkay;
    }

    public MobileElement getExitRowPopUpHeaderText(){
        return txt_ExitRowPopUpHeader;
    }

    public MobileElement getExitRowPopUpAcceptAndContinueButton(){
        return btn_ExitRowPopUpAcceptAndContinue;
    }

    public MobileElement getExitRowPopUpChangeSeatButton(){
        return btn_ExitRowPopUpChangeSeat;
    }

    public MobileElement getSeatUpsellPopUpHeaderText(){
        return txt_SeatUpsellPopUpHeader;
    }

    public MobileElement getSeatUpsellPopUpMessageText(){
        return txt_SeatUpsellPopUpMessage;
    }

    public MobileElement getLoveFirstSeatPopUpChooseSeatButton(){
        return btn_LoveFirstSeatPopUpChooseSeat;
    }

    public MobileElement getLoveFirstSeatPopUpSkipFlightButton(){
        return btn_LoveFirstSeatPopUpSkipFlight;
    }

    public MobileElement getLoveFirstSeatPopUpSkipAllButton(){
        return btn_LoveFirstSeatPopUpSkipAll;
    }

}