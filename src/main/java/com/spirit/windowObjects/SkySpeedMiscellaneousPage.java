package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SkySpeedMiscellaneousPage {

    /*
 * Prefix with WebElement      PostFix with Method
         ifr     				-  IFrame
          btn      				-  Button
           chkbx    				-  CheckBox
           chklst   				-  CheckBoxList
           drpdwn   				-  DropDown
           grdvew   				-  GridView
           hyrlnk   				-  Hyperlink
           img      				-  Image
           imgbtn   				-  ImageButton
           lbl      				-  Label
           lnkbtn   				-  LinkButton
           lnk     				-  Link
           lstbx    				-  ListBox
           lit      				-  Literal
           pnl      				-  Panel
           ph      				-  PlaceHolder
           rdbtn    				-  RadioButton
           rdbtnlst 				-  RadioButtonListF
           txtbx    				-  Textbox
           txt      				-  Text
 */

    private WindowsDriver driver;

    public SkySpeedMiscellaneousPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //SkyspeedCallerInformationWindow
    public final String xpath_CallerInformationOkButton = "//*[@AutomationId='_buttonOK']";
    @FindBy(xpath=xpath_CallerInformationOkButton)
    private WebElement btn_CallerInformationOk;

    public final String xpath_CallerInformationNameTextBox = "//*[@AutomationId='_autoTextBoxCurrentCaller']";
    @FindBy(xpath=xpath_CallerInformationNameTextBox)
    private WebElement txtbx_CallerInformationName;

    public final String xpath_CallerInformationNumberTextBox = "//*[@AutomationId='_textBoxPhoneCallerNumber']";
    @FindBy(xpath=xpath_CallerInformationNumberTextBox)
    private WebElement txtbx_CallerInformationNumber;

    //SSR-RestorationConfirmationPopUp
    public final String xpath_SSROkButton = "//*[@AutomationId='_buttonOK']";
    @FindBy(xpath=xpath_SSROkButton)
    private WebElement btn_SSROk;

    public final String xpath_PrimarySortingDropDown = "//*[@AutomationId='_comboBoxFirstSortElement']";
    @FindBy(xpath=xpath_PrimarySortingDropDown)
    private WebElement drpdwn_PrimarySorting;

    public final String xpath_SecondarySortingDropDown = "//*[@AutomationId='_comboBoxSecondSortElement']";
    @FindBy(xpath=xpath_SecondarySortingDropDown)
    private WebElement drpdwn_SecondarySorting;



    @FindBy(xpath = "//*[@AutomationId = '???????']")
    private WebElement PrimarySorting;
    @FindBy(xpath = "//*[@AutomationId = '???????']")
    private WebElement SecondarySorting;


//////////////////////////////////////////Getter methods//////////////////////////////////////


    public WebElement getCallerInformationOkButton() {
        return btn_CallerInformationOk;
    }

    public WebElement getCallerInformationNameTextBox() {
        return txtbx_CallerInformationName;
    }

    public WebElement getCallerInformationNumberTextBox() {
        return txtbx_CallerInformationNumber;
    }


    public WebElement getSSROkButton() {
        return btn_SSROk;
    }

    public WebElement getPrimarySortingDropDown() {
        return drpdwn_PrimarySorting;
    }

    public WebElement getSecondarySortingDropDown() {
        return drpdwn_SecondarySorting;
    }

    public WebElement get_PrimarySorting() {
        return PrimarySorting;
    }

    public WebElement get_SecondarySorting() {
        return SecondarySorting;
    }
}