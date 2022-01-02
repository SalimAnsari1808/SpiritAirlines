package com.spirit.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BoardingPassPage {

    	/*
	 * Prefix with List<WebElement>      PostFix with Method
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

    public BoardingPassPage(WebDriver driver) {
        //this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public final String xpath_BoardingPassSectionPanel = "//div[contains(@class,'desktop')]/div/div";
    @FindBy(xpath = xpath_BoardingPassSectionPanel)
    private List<WebElement> pnl_BoardingPassSection;

    //************************Top Left***************************************
    public final String xpath_WelcomeHeaderText = "//h1[contains(text(),'Welcome Aboard,')]";
    @FindBy(xpath=xpath_WelcomeHeaderText)
    private List<WebElement> txt_WelcomeHeader;

    public final String xpath_UserNameText = "//h1[contains(text(),'Welcome Aboard,')]/..//p[1]";
    @FindBy(xpath=xpath_UserNameText)
    private List<WebElement> txt_UserName;

    public final String xpath_YourBoardingPassText = "//h1[contains(text(),'Welcome Aboard,')]/..//p[contains(text(),'This is your ')]";
    @FindBy(xpath=xpath_YourBoardingPassText)
    private List<WebElement> txt_YourBoardingPass;

    public final String xpath_NotBoardingPassText = "//strong[contains(text(),'This is not a boarding pass.') or contains(text(),'This is not a boarding pass.')]";
    @FindBy(xpath=xpath_NotBoardingPassText)
    private List<WebElement> txt_NotBoardingPass;

    public final String xpath_SelfBagTagMessageText = "(//div[@class='row quad-ul'])[1]//p[contains(@class,'quadInfo')][2]";
    @FindBy(xpath=xpath_SelfBagTagMessageText)
    private List<WebElement> txt_SelfBagTagMessage;

    public final String xpath_FoldBoardingPassText = "//img[@alt='Fold Boarding Pass']/..";
    @FindBy(xpath=xpath_FoldBoardingPassText)
    private List<WebElement> txt_FoldBoardingPass;

    public final String xpath_TravelAlertsText = "//app-station-advisory-info//..//p[1][contains(text(),'Travel Alerts')]";
    @FindBy(xpath=xpath_TravelAlertsText)
    private List<WebElement> txt_TravelAlerts;

    public final String xpath_StationAdvisoryMessageText = "//app-station-advisory-info//div//p";
    @FindBy(xpath=xpath_StationAdvisoryMessageText)
    private List<WebElement> txt_StationAdvisoryMessage;

    public final String xpath_BringThisPageToAvoidBeingChargedText = "//p[contains(text(),'Bring this page to avoid being charged')]";
    @FindBy(xpath=xpath_BringThisPageToAvoidBeingChargedText)
    private List<WebElement> txt_BringThisPageToAvoidBeingCharged;
    //************************Top Right***************************************

    public final String xpath_FareClubSavingImage = "//img[@alt='Fare Club Savings']";
    @FindBy(xpath= xpath_FareClubSavingImage)
    private List<WebElement> img_FareClubSaving;

    //************************Bottom Left***************************************
    public final String xpath_SpiritLogoImage = "//img[@alt='Spirit Logo']";
    @FindBy(xpath=xpath_SpiritLogoImage)
    private List<WebElement> img_SpiritLogo;

    public final String xpath_BoardingPassTSAPrecheckImage = "//img[@alt='TSA Precheck Logo']";
    @FindBy(xpath= xpath_BoardingPassTSAPrecheckImage)
    private List<WebElement> img_BoardingPassTSAPrecheck;

    public final String xpath_BoardingPassHeaderText = "//p[contains(text(),'Boarding Pass')]";
    @FindBy(xpath=xpath_BoardingPassHeaderText)
    private List<WebElement> txt_BoardingPassHeader;

    public final String xpath_DateText = "//p[contains(text(),'Boarding Pass')]/..//strong[contains(text(),'Date')]";
    @FindBy(xpath=xpath_DateText)
    private List<WebElement> txt_Date;

    public final String xpath_FlightDateText = "//p[contains(text(),'Boarding Pass')]/..//strong[contains(text(),'Date')]/..";
    @FindBy(xpath=xpath_FlightDateText)
    private List<WebElement> txt_FlightDate;

    public final String xpath_UserNameLeftBottomText = "//div[@data-qa='passenger-name']//p";
    @FindBy(xpath=xpath_UserNameLeftBottomText)
    private List<WebElement> txt_UserNameLeftBottom;

    public final String xpath_FromText = "//div[@data-qa='from-station-city']//preceding::div[1]//p";
    @FindBy(xpath=xpath_FromText)
    private List<WebElement> txt_From;

    public final String xpath_FromCityCodeText = "//div[@data-qa='from-station-city']//p[1]";
    @FindBy(xpath=xpath_FromCityCodeText)
    private List<WebElement> txt_FromCityCode;

    public final String xpath_FromCityNameText = "//div[@data-qa='from-station-city']//p[2]";
    @FindBy(xpath= xpath_FromCityNameText)
    private List<WebElement> txt_FromCityName;

    public final String xpath_ToText = "//div[@data-qa='to-station-city']//p[1]//preceding::div[1]//p";
    @FindBy(xpath=xpath_ToText)
    private List<WebElement> txt_To;

    public final String xpath_ToCityCodeText = "//div[@data-qa='to-station-city']//p[1]";
    @FindBy(xpath=xpath_ToCityCodeText)
    private List<WebElement> txt_ToCityCode;

    public final String xpath_ToCityNameText = "//div[@data-qa='to-station-city']//p[2]";
    @FindBy(xpath=xpath_ToCityNameText)
    private List<WebElement> txt_ToCityName;

    public final String xpath_CarryonNumberText ="//th[@data-qa='passenger-bags-carryon']";
    @FindBy(xpath=xpath_CarryonNumberText)
    private List<WebElement> txt_CarryonNumber;

    public final String xpath_CarryOnTextText = "//th[@data-qa='passenger-bags-checked']";
    @FindBy(xpath=xpath_CarryOnTextText)
    private List<WebElement> txt_CarryOnText;

    public final String xpath_CheckedBagNumberText = "//th[@data-qa='passenger-bags-checked']//following::td[1]";
    @FindBy(xpath=xpath_CheckedBagNumberText)
    private List<WebElement> txt_CheckedBagNumber;

    public final String xpath_CheckedBagText ="//th[@data-qa='passenger-bags-checked']//following::td[2]";
    @FindBy(xpath=xpath_CheckedBagText)
    private List<WebElement> txt_CheckedBag;

    public final String xpath_SeqText = "//strong[contains(text(),'Seq')]";
    @FindBy(xpath=xpath_SeqText)
    private List<WebElement> txt_Seq;

    public final String xpath_SeqNumberText = "//strong[contains(text(),'Seq')]/..";
    @FindBy(xpath=xpath_SeqNumberText)
    private List<WebElement> txt_SeqNumber;

    public final String xpath_ConfirmationText = "//p[contains(text(),'Confirmation')]";
    @FindBy(xpath=xpath_ConfirmationText)
    private List<WebElement> txt_Confirmation;

    public final String xpath_ConfirmationCodeText = "//div[@data-qa='pnr-confirmation-code']//span";
    @FindBy(xpath=xpath_ConfirmationCodeText)
    private List<WebElement> txt_ConfirmationCode;

    //Extras
    public final String xpath_ShortcutSecurityExtraSoldText = "//div[contains(text(),'Shortcut Security')]/..//span[@class='circle']";
    @FindBy(xpath=xpath_ShortcutSecurityExtraSoldText)
    private List<WebElement> txt_ShortcutSecurityExtraSold;

    public final String xpath_ShortcutBoardingExtraSoldText = "//div[contains(text(),'Shortcut Boarding')]/..//span[@class='circle']";
    @FindBy(xpath=xpath_ShortcutBoardingExtraSoldText)
    private List<WebElement> txt_ShortcutBoardingExtraSold;

    public final String xpath_BoardingPassBarcodeImage = "//img[@alt='barcode']";
    @FindBy(xpath=xpath_BoardingPassBarcodeImage)
    private List<WebElement> img_BoardingPassBarcode;

    //************************Bottom Right***************************************

    public final String xpath_FlightHeaderText = "//strong[contains(text(),'Flight')]";
    @FindBy(xpath=xpath_FlightHeaderText)
    private List<WebElement> txt_FlightHeader;

    public final String xpath_FlightNumberText = "//strong[(@data-qa='flight-number')]";
    @FindBy(xpath=xpath_FlightNumberText)
    private List<WebElement> txt_FlightNumber;
    //****************************************************
    public final String xpath_GateText = "//p[contains(text(),'Gate')][1]";
    @FindBy(xpath=xpath_GateText)
    private List<WebElement> txt_Gate;

    public final String xpath_CheckScreensText = "//p[contains(text(),'Check Screens')]";
    @FindBy(xpath=xpath_CheckScreensText)
    private List<WebElement> txt_CheckScreens;

    public final String xpath_BoardingTimeText = "//strong[contains(text(),'Boarding Time')]";
    @FindBy(xpath=xpath_BoardingTimeText)
    private List<WebElement> txt_BoardingTime;

    public final String xpath_BoardingActualTimeText = "//p[@data-qa='flight-boarding-time']";
    @FindBy(xpath=xpath_BoardingActualTimeText)
    private List<WebElement> txt_BoardingActualTime;

    public final String xpath_LocatedinTerminalText = "//span[contains(text(),'Located in Terminal')]";
    @FindBy(xpath=xpath_LocatedinTerminalText)
    private List<WebElement> txt_LocatedinTerminal;

    public final String xpath_DoorCloseText = "//p[contains(text(),'Doors close 15 minutes prior to departure.')]";
    @FindBy(xpath=xpath_DoorCloseText)
    private List<WebElement> txt_DoorClose;
    //****************************************************
    public final String xpath_ZoneText = "//strong[contains(text(),'Zone')]";
    @FindBy(xpath=xpath_ZoneText)
    private List<WebElement> txt_Zone;

    public final String xpath_ZoneNumberText = "//div[@data-qa='flight-boarding-zone']//strong";
    @FindBy(xpath=xpath_ZoneNumberText)
    private List<WebElement> txt_ZoneNumber;

    public final String xpath_SeatText = "//strong[contains(text(),'Seat')]";
    @FindBy(xpath=xpath_SeatText)
    private List<WebElement> txt_Seat;

    public final String xpath_SeatNumberText = "//div[@data-qa='flight-seat']";
    @FindBy(xpath=xpath_SeatNumberText)
    private List<WebElement> txt_SeatNumber;
    //****************************************************
    public final String xpath_DepartsText = "//p[contains(text(),'Departs')]";
    @FindBy(xpath=xpath_DepartsText)
    private List<WebElement> txt_Departs;

    public final String xpath_DepartsCityNameText = "//div[@data-qa='depature-arrival-city']//div[1]//p";
    @FindBy(xpath=xpath_DepartsCityNameText)
    private List<WebElement> txt_DepartsCityName;

    public final String xpath_DepartsCityTimeText = "//div[@data-qa='depature-arrival-time']//div[1]//p";
    @FindBy(xpath=xpath_DepartsCityTimeText)
    private List<WebElement> txt_DepartsCityTime;

    public final String xpath_ArriveText = "(//p[contains(text(),'Arrives')])";
    @FindBy(xpath=xpath_ArriveText)
    private List<WebElement> txt_Arrive;

    public final String xpath_ArriveCityNameText = "//div[@data-qa='depature-arrival-city']//div[2]//p";
    @FindBy(xpath=xpath_ArriveCityNameText)
    private List<WebElement> txt_ArriveCityName;

    public final String xpath_ArriveCityTimeText = "//div[@data-qa='depature-arrival-time']//div[2]//p";
    @FindBy(xpath=xpath_ArriveCityTimeText)
    private List<WebElement> txt_ArriveCityTime;
    //****************************************************
    public final String xpath_IssuedText = "//strong[contains(text(),'Issued')]";
    @FindBy(xpath=xpath_IssuedText)
    private List<WebElement> txt_Issued;

    public final String xpath_IssuedTimeText = "//strong[contains(text(),'Issued')]/..";
    @FindBy(xpath=xpath_IssuedTimeText)
    private List<WebElement> txt_IssuedTime;

    //****************************************************
    //XPaths for inhibited doc: Print
    //****************************************************
    public final String xpath_InhibitedDocPrintMeText = "//div[contains(@class,'print-me')]";
    @FindBy(xpath=xpath_InhibitedDocPrintMeText)
    private List<WebElement> txt_InhibitedDocPrintMe;

    public final String xpath_InhibitedDocTitleText = "//div[contains(@class,'inhibited-title')]//div";
    @FindBy(xpath=xpath_InhibitedDocTitleText)
    private List<WebElement> txt_InhibitedDocTitle;

    public final String xpath_InhibitedDocMessageText = "//div[contains(@class,'inhibited-context')]//p";
    @FindBy(xpath=xpath_InhibitedDocMessageText)
    private List<WebElement> txt_InhibitedDocMessage;

    public final String xpath_InhibitedPaxNameText = "//div[contains(@class,'inhibited-context')]//div[2]";
    @FindBy(xpath=xpath_InhibitedPaxNameText)
    private List<WebElement> txt_InhibitedPaxName;

    public final String xpath_InhibitedDocBulletMessage1Text = "//div[contains(@class,'inhibited-context')]//li[1]";
    @FindBy(xpath=xpath_InhibitedDocBulletMessage1Text)
    private List<WebElement> txt_InhibitedDocBulletMessage1;

    public final String xpath_InhibitedDocBulletMessage2Text = "//div[contains(@class,'inhibited-context')]//li[2]";
    @FindBy(xpath=xpath_InhibitedDocBulletMessage2Text)
    private List<WebElement> txt_InhibitedDocBulletMessage2;

    public final String xpath_InhibitedDocBulletMessage3Text = "//div[contains(@class,'inhibited-context')]//li[3]";
    @FindBy(xpath=xpath_InhibitedDocBulletMessage3Text)
    private List<WebElement> txt_InhibitedDocBulletMessage3;

    public final String xpath_InhibitedDocFlightDateText = "//div[contains(@class,'quad-lr')]//p[@class='label']  [not (@strong)]";
    @FindBy(xpath=xpath_InhibitedDocFlightDateText)
    private List<WebElement> txt_InhibitedDocFlightDate;

    public final String xpath_InhibitedDocConfirmationCodeText = "//p[contains(@class,'confirmCode')]";
    @FindBy(xpath=xpath_InhibitedDocConfirmationCodeText)
    private List<WebElement> txt_InhibitedDocConfirmationCode;

    public final String xpath_InhibitedDocFlightNumberText = "//span[contains(@class,'flightnum text-uppercase')][1]";
    @FindBy(xpath=xpath_InhibitedDocFlightNumberText)
    private List<WebElement> txt_InhibitedDocFlightNumber;

    public final String xpath_InhibitedDocTerminalText = "//span[contains(@class,'flightnum text-uppercase')][2]";
    @FindBy(xpath=xpath_InhibitedDocTerminalText)
    private List<WebElement> txt_InhibitedDocTerminal;

    public final String xpath_InhibitedDocOriginAndDestinationText = "//p[contains(@class,'station')]";
    @FindBy(xpath=xpath_InhibitedDocOriginAndDestinationText)
    private List<WebElement> txt_InhibitedDocOriginAndDestination;

    public final String xpath_InhibitedDocDepartingTimeLabel = "//div[contains(text(),'Departing:')]";
    @FindBy(xpath=xpath_InhibitedDocDepartingTimeLabel)
    private List<WebElement> lbl_InhibitedDocDepartingTime;

    public final String xpath_InhibitedDocDepartingTimeText = "//div[contains(text(),'Departing:')]//following::div[1]";
    @FindBy(xpath=xpath_InhibitedDocDepartingTimeText)
    private List<WebElement> txt_InhibitedDocDepartingTime;

    public final String xpath_InhibitedDocArrivingTimeLabel = "//div[contains(text(),'Arriving:')]";
    @FindBy(xpath=xpath_InhibitedDocArrivingTimeLabel)
    private List<WebElement> lbl_InhibitedDocArrivingTime;

    public final String xpath_InhibitedDocArrivingTimeText = "//div[contains(text(),'Arriving:')]//following::div[1]";
    @FindBy(xpath=xpath_InhibitedDocArrivingTimeText)
    private List<WebElement> txt_InhibitedDocArrivingTime;

    public final String xpath_InhibitedDocFlightDurationLabel = "//div[contains(text(),'Duration:')]";
    @FindBy(xpath=xpath_InhibitedDocFlightDurationLabel)
    private List<WebElement> lbl_InhibitedDocFlightDuration;

    public final String xpath_InhibitedDocFlightDurationText = "//div[contains(text(),'Duration:')]//following::div[1]";
    @FindBy(xpath=xpath_InhibitedDocFlightDurationText)
    private List<WebElement> txt_InhibitedDocFlightDuration;

    //*******************************************************************************************************************************************
    //******************************************************Start getter Methods of Boarding Pass Page****************************************************
    //*******************************************************************************************************************************************

    public List<WebElement> getBoardingPassSectionPanel(){
        return pnl_BoardingPassSection;
    }

    //************************Top Left***************************************
    public List<WebElement> getWelcomeHeaderText(){
        return txt_WelcomeHeader;}

    public List<WebElement> getUserNameText(){
        return txt_UserName;}

    public List<WebElement> getYourBoardingPassText(){
        return txt_YourBoardingPass;}

    public List<WebElement> getNotBoardingPassText(){
        return txt_NotBoardingPass;}

    public List<WebElement> getSelfBagTagText(){
        return txt_SelfBagTagMessage;}

    public List<WebElement> getFoldBoaringPassText(){
        return txt_FoldBoardingPass;}

    public List<WebElement> getTravelAlertsText(){
        return txt_TravelAlerts;}

    public List<WebElement> getStationAdvisoryMessageText(){
        return txt_StationAdvisoryMessage;}

    public List<WebElement> getBringThisPageToAvoidBeingChargedText() { return txt_BringThisPageToAvoidBeingCharged; }

    //************************Top Right***************************************
    public List<WebElement> getFareClubSavingImage(){
        return img_FareClubSaving; }

    //************************Bottom Left***************************************
    public List<WebElement> getSpiritLogoImage(){
        return img_SpiritLogo;}

    public List<WebElement> getBoardingPassTSAPrecheckImage(){
        return img_BoardingPassTSAPrecheck;}

    public List<WebElement> getBoardingPassHeader(){
        return txt_BoardingPassHeader;}

    public List<WebElement> getDate(){
        return txt_Date;}

    public List<WebElement> getFlightDate(){
        return txt_FlightDate;}

    public List<WebElement> getUserNameLeftBottomText(){
        return txt_UserNameLeftBottom;}

    public List<WebElement> getFromText(){
        return txt_From; }

    public List<WebElement> getFromCityCodeText(){
        return txt_FromCityCode; }

    public List<WebElement> getFromCityNameText(){
        return txt_FromCityName; }

    public List<WebElement> getToText(){ return txt_To; }

    public List<WebElement> getToCityCode(){
        return txt_ToCityCode; }

    public List<WebElement> getToCityName(){
        return txt_ToCityName; }

    public List<WebElement> getNumberOfCarryon(){
        return txt_CarryonNumber; }

    public List<WebElement> getCarryOnText(){
        return txt_CarryOnText; }

    public List<WebElement> getNumberCheckedBagText(){
        return txt_CheckedBagNumber; }

    public List<WebElement> getCheckedBagText(){
        return txt_CheckedBag; }

    public List<WebElement> getSeqText(){
        return txt_Seq; }

        //Unable to locate this element with XPath
    public List<WebElement> getSeqNumberText(){
        return txt_SeqNumber; }

    public List<WebElement> getConfirmationText(){
        return txt_Confirmation; }

    public List<WebElement> getConfirmationCode(){
        return txt_ConfirmationCode; }

    //Extras
    public List<WebElement> getShortcutSecuritySold(){
        return txt_ShortcutSecurityExtraSold; }

    public List<WebElement> getShortcutBoardingSold(){
        return txt_ShortcutBoardingExtraSold; }

    public List<WebElement> getBoardingPassImage(){
        return img_BoardingPassBarcode; }

    //************************Bottom Right***************************************

    public List<WebElement> getFlightHeaderText(){
        return txt_FlightHeader; }

    public List<WebElement> getFlightNumberText(){
        return txt_FlightNumber; }
    //****************************************************
    public List<WebElement> getGateText(){
        return txt_Gate; }

    public List<WebElement> getCheckScreensText(){
        return txt_CheckScreens; }

    public List<WebElement> getBoardingTimeText(){
        return txt_BoardingTime; }

    public List<WebElement> getBoardingActualTimeText(){
        return txt_BoardingActualTime; }

    public List<WebElement> getLocatedinTerminalText(){
        return txt_LocatedinTerminal; }

    public List<WebElement> getDoorCloseText(){
        return txt_DoorClose; }
    //****************************************************
    public List<WebElement> getZoneText(){
        return txt_Zone; }

    public List<WebElement> getZoneNumberText(){
        return txt_ZoneNumber; }

    public List<WebElement> getSeatText(){
        return txt_Seat; }

    public List<WebElement> getSeatNumberText(){
        return txt_SeatNumber; }
    //****************************************************
    public List<WebElement> getDepartsText(){
        return txt_Departs; }

    public List<WebElement> getDepartsCityNameText(){
        return txt_DepartsCityName; }

    public List<WebElement> getDepartsCityTimeText(){
        return txt_DepartsCityTime; }

    public List<WebElement> getArriveText(){
        return txt_Arrive; }

    public List<WebElement> getArriveCityNameText(){
        return txt_ArriveCityName; }

    public List<WebElement> getArriveCityTimeText(){
        return txt_ArriveCityTime; }
    //****************************************************
    public List<WebElement> getIssuedText(){
        return txt_Issued; }

    public List<WebElement> getIssuedTimeText(){
        return txt_IssuedTime; }

    public List<WebElement> getInhibitedDocPrintMeText(){
        return txt_InhibitedDocPrintMe;
    }

    public List<WebElement> getInhibitedDocTitleText() {
        return txt_InhibitedDocTitle;
    }

    public List<WebElement> getInhibitedDocMessage() {
        return txt_InhibitedDocMessage;
    }

    public List<WebElement> getInhibitedPaxNameText() { return txt_InhibitedPaxName; }

    public List<WebElement> getInhibitedDocBullet1Text() {
        return txt_InhibitedDocBulletMessage1;
    }

    public List<WebElement> getInhibitedDocBullet2Text() {
        return txt_InhibitedDocBulletMessage2;
    }

    public List<WebElement> getInhibitedDocBullet3Text() {
        return txt_InhibitedDocBulletMessage3;
    }

    public List<WebElement> getInhibitedDocFlightDateText() { return txt_InhibitedDocFlightDate; }

    public List<WebElement> getInhibitedDocConfirmationCode() {
        return txt_InhibitedDocConfirmationCode;
    }

    public List<WebElement> getInhibitedDocFlightNumber() {
        return txt_InhibitedDocFlightNumber;
    }

    public List<WebElement> getInhibitedDocterminalText() {
        return txt_InhibitedDocTerminal;
    }

    public List<WebElement> getInhibitedDocOriginAndDestiantionText() {
        return txt_InhibitedDocOriginAndDestination;
    }

    public List<WebElement> getInhibitedDocDepartingTimeLabel() { return lbl_InhibitedDocDepartingTime; }

    public List<WebElement> getInhibitedDocDepartingTimeText() {
        return txt_InhibitedDocDepartingTime;
    }

    public List<WebElement> getInhibitedDocArrivingTimeLabel() { return lbl_InhibitedDocArrivingTime; }

    public List<WebElement> getInhibitedDocArrivingTimeText() {
        return txt_InhibitedDocArrivingTime;
    }

    public List<WebElement> getInhibitedDocFlightDurationLabel() { return lbl_InhibitedDocFlightDuration; }

    public List<WebElement> getInhibitedDocFlightDurationText() {
        return txt_InhibitedDocFlightDuration;
    }
}
