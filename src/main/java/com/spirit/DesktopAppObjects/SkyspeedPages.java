//package com.spirit.DesktopAppObjects;
//
//import io.appium.java_client.windows.WindowsDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class SkyspeedPages {
//
//    /*
// * Prefix with WebElement      PostFix with Method
//         ifr     				-  IFrame
//          btn      				-  Button
//           chkbx    				-  CheckBox
//           chklst   				-  CheckBoxList
//           drpdwn   				-  DropDown
//           grdvew   				-  GridView
//           hyrlnk   				-  Hyperlink
//           img      				-  Image
//           imgbtn   				-  ImageButton
//           lbl      				-  Label
//           lnkbtn   				-  LinkButton
//           lnk     				-  Link
//           lstbx    				-  ListBox
//           lit      				-  Literal
//           pnl      				-  Panel
//           ph      				-  PlaceHolder
//           rdbtn    				-  RadioButton
//           rdbtnlst 				-  RadioButtonListF
//           txtbx    				-  Textbox
//           txt      				-  Text
// */
//
//    private WindowsDriver driver;
//
//    public SkyspeedPages(WindowsDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//
//
//    //SkyspeedCallerInformationWindow
//    @FindBy(xpath = "//*[@AutomationId = '_buttonOK']")
//    private WebElement btn_CallerInformationOk;
//    @FindBy(xpath = "//*[@AutomationId = '_autoTextBoxCurrentCaller']")
//    private WebElement txtbx_CallerInformationName;
//    @FindBy(xpath = "//*[@AutomationId = '_textBoxPhoneCallerNumber']")
//    private WebElement txtbx_CallerInformationNumber;
//
//
//
//
//
//    //SSR-RestorationConfirmationPopUp
//    @FindBy(xpath = "//*[@AutomationId = '_buttonOK']")
//    private WebElement btn_SSROk;
//    @FindBy(xpath = "//*[@AutomationId = '_comboBoxFirstSortElement']")
//    private WebElement drpdwn_PrimarySorting;
//    @FindBy(xpath = "//*[@AutomationId = '_comboBoxSecondSortElement']")
//    private WebElement drpdwn_SecondarySorting;
//    @FindBy(xpath = "//*[@AutomationId = '???????']")
//    private WebElement PrimarySorting;
//    @FindBy(xpath = "//*[@AutomationId = '???????']")
//    private WebElement SecondarySorting;
//
//
//
//
////////////////////////////////////////////Getter methods//////////////////////////////////////
//
//
//    public WebElement getCallerInformationOkButton() {
//        return btn_CallerInformationOk;
//    }
//
//    public WebElement getCallerInformationNameTextBox() {
//        return txtbx_CallerInformationName;
//    }
//
//    public WebElement getCallerInformationNumberTextBox() {
//        return txtbx_CallerInformationNumber;
//    }
//
//
//
//
//
//
//
//
//
//    public WebElement getSSROkButton() {
//        return btn_SSROk;
//    }
//
//    public WebElement getPrimarySortingDropDown() {
//        return drpdwn_PrimarySorting;
//    }
//
//    public WebElement getSecondarySortingDropDown() {
//        return drpdwn_SecondarySorting;
//    }
//
//    public WebElement get_PrimarySorting() {
//        return PrimarySorting;
//    }
//
//    public WebElement get_SecondarySorting() {
//        return SecondarySorting;
//    }
//
//}
