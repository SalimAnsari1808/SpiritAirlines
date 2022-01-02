package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SkySpeedFlightAvailabilityPage {

    private WindowsDriver driver;

    public SkySpeedFlightAvailabilityPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

 //****FLIGHT SECTION*****************
    //Departing flight Section
   public final String xpath_SourceFlightSectionText = "//*[@AutomationId = '_tvOutboundFlights']";
   @FindBy(xpath=xpath_SourceFlightSectionText)
   private WebElement txt_SourceFlightSection;

    public final String xpath_SourceFlightList = "//*[@AutomationId = '_tvOutboundFlights']//*[contains(@Name,'NK-')]";
    @FindBy(xpath=xpath_SourceFlightList)
    private List<WebElement> FlightAvailabilitySourceFlightList;

    public final String xpath_OutboundJourneyStatus = "//*[@AutomationId = '_labelOutboundJourneyStatus']";
    @FindBy(xpath=xpath_OutboundJourneyStatus)
    private WebElement FlightAvailabilityOutboundJourneyStatus;

    //Return Flight Section
    public final String xpath_DestinationFlightSectionText = "//*[@AutomationId = '_tvReturnFlights']";
    @FindBy(xpath=xpath_DestinationFlightSectionText)
    private WebElement txt_DestinationFlightSection;

    public final String xpath_DestinationFlightList = "//*[@AutomationId = '_tvReturnFlights']//*[contains(@Name,'NK-')]";
    @FindBy(xpath=xpath_DestinationFlightList)
    private List<WebElement> FlightAvailabilityDestinationFlightList;

    public final String xpath_ReturnJourneyStatus = "//*[@AutomationId = '_labelReturnJourneyStatus']";
    @FindBy(xpath=xpath_ReturnJourneyStatus)
    private WebElement FlightAvailabilityReturnJourneyStatus;

//****FLIGHT RESULT********************

    //SkySpeedFlightAvailabilityWindow
    public final String xpath_NextButton = "//*[@AutomationId = '_buttonContinue']";
    @FindBy(xpath=xpath_NextButton)
    private WebElement btn_Next;

//****RIGHT PANE***********************

    public final String xpath_SortButton = "//*[@AutomationId = '_buttonSort']";
    @FindBy(xpath=xpath_SortButton)
    private WebElement btn_Sort;

    public final String xpath_ResetButton = "//*[@AutomationId = '_buttonReset']";
    @FindBy(xpath=xpath_ResetButton)
    private WebElement btn_Reset;

    public final String xpath_OverBookButton = "//*[@AutomationId = '_buttonOverbook']";
    @FindBy(xpath=xpath_OverBookButton)
    private WebElement btn_OverBook;

    public final String xpath_StandByButton = "//*[@AutomationId = '_buttonStandBy']";
    @FindBy(xpath=xpath_StandByButton)
    private WebElement btn_StandBy;

    public final String xpath_PriorityButton = "//*[@AutomationId = '_buttonPriority']";
    @FindBy(xpath=xpath_PriorityButton)
    private WebElement btn_Priority;

    public final String xpath_ManifestButton = "//*[@AutomationId = '_buttonManifest']";
    @FindBy(xpath=xpath_ManifestButton)
    private WebElement btn_Manifest;

    public final String xpath_SaleChannelButton = "//*[@AutomationId = '_buttonManifest']";
    @FindBy(xpath=xpath_SaleChannelButton)
    private WebElement btn_SaleChannel;

//******Standby Priority Code Popup****************
    //
    public final String xpath_StandByPriorityCodePopUpSelectDropDown = "//*[@AutomationId = '_lookupComboBoxPriorityCode']";
    @FindBy(xpath=xpath_StandByPriorityCodePopUpSelectDropDown)
    private WebElement drpdwn_StandByPriorityCodePopUpSelect;

    public final String xpath_StandByPriorityCodePopUpOkButton = "//*[@AutomationId = '_lookupComboBoxPriorityCode']";
    @FindBy(xpath=xpath_StandByPriorityCodePopUpOkButton)
    private WebElement btn_StandByPriorityCodePopUpOk;

//**********Availability Sort Order Popup*********
    public final String xpath_AvailabilitySortOrderPopupDepartureDropDown = "//*[@AutomationId = '_comboBoxFirstSortElement']";
    @FindBy(xpath=xpath_AvailabilitySortOrderPopupDepartureDropDown)
    private WebElement drpdwn_AvailabilitySortOrderPopupDeparture;

    public final String xpath_AvailabilitySortOrderPopupReturnDropDown = "//*[@AutomationId = '_comboBoxSecondSortElement']";
    @FindBy(xpath=xpath_AvailabilitySortOrderPopupReturnDropDown)
    private WebElement drpdwn_AvailabilitySortOrderPopupReturn;

    public final String xpath_AvailabilitySortOrderPopupOkButton = "//*[@AutomationId = '_buttonOK']";
    @FindBy(xpath=xpath_AvailabilitySortOrderPopupOkButton)
    private WebElement btn_AvailabilitySortOrderPopupOk;

//*********Not Utilized*********************

    public final String xpath_PassengerInputTextBox = "//*[@AutomationId = '_upDownNumPax']";
    @FindBy(xpath=xpath_PassengerInputTextBox)
    private WebElement txtbx_PassengerInput;

    public final String xpath_PassengerOkayButton = "//*[@AutomationId = '_buttonOK']";
    @FindBy(xpath=xpath_PassengerOkayButton)
    private WebElement btn_PassengerOkay;

    public final String xpath_OutBoundFlightList = "//Tree[@AutomationId='_tvOutboundFlights']//TreeItem";
    @FindBy(xpath=xpath_OutBoundFlightList)
    private List<WebElement> txt_OutboundFlightList;

    public final String xpath_OutBoundFlightFareList = "//Tree[@AutomationId='_tvOutboundFlights']//TreeItem[@Name='Services']/..//TreeItem";
    @FindBy(xpath=xpath_OutBoundFlightFareList)
    private List<WebElement> txt_OutboundFlightFareList;

    public final String xpath_ReturnFlightList = "//Tree[@AutomationId='tvReturnFlights']//TreeItem";
    @FindBy(xpath=xpath_ReturnFlightList)
    private List<WebElement> txt_ReturnFlightList;

    public final String xpath_ReturnFlightFareList = "//Tree[@AutomationId='tvReturnFlights']//TreeItem[@Name='Services']/..//TreeItem";
    @FindBy(xpath=xpath_ReturnFlightFareList)
    private List<WebElement> txt_ReturnFlightFareList;

    public WebElement getNextButton() {
        return btn_Next;
    }

    public WebElement getSortButton() {
        return btn_Sort;
    }

    public WebElement getResetButton() {
        return btn_Reset;
    }

    public WebElement getOverBookButton() {
        return btn_OverBook;
    }

    public WebElement getStandByButton() {
        return btn_StandBy;
    }

    public WebElement getPriorityButton() {
        return btn_Priority;
    }

    public WebElement getManifestButton() {
        return btn_Manifest;
    }

    public WebElement getSaleChannelButton() {
        return btn_SaleChannel;
    }

    public WebElement getStandByPriorityCodePopUpSelectDropDown() {
        return drpdwn_StandByPriorityCodePopUpSelect;
    }

    public WebElement getStandByPriorityCodePopUpOkButton() {
        return btn_StandByPriorityCodePopUpOk;
    }

    public WebElement getAvailabilitySortOrderPopupDepartureDropDown() {
        return drpdwn_AvailabilitySortOrderPopupDeparture;
    }

    public WebElement getAvailabilitySortOrderPopupReturnDropDown() {
        return drpdwn_AvailabilitySortOrderPopupReturn;
    }

    public WebElement getAvailabilitySortOrderPopupOkButton() {
        return btn_AvailabilitySortOrderPopupOk;
    }

    public WebElement getOutboundJourneyStatus() {
        return FlightAvailabilityOutboundJourneyStatus;
    }

    public WebElement getReturnJourneyStatus() {
        return FlightAvailabilityReturnJourneyStatus;
    }

    public WebElement getDestinationFlightSectionText(){
       return txt_DestinationFlightSection;
    }

    public List<WebElement> getDestinationFlightList() {
        return FlightAvailabilityDestinationFlightList;
    }

    public WebElement getSourceFlightSectionText(){
       return txt_SourceFlightSection;
    }

    public List<WebElement> getSourceFlightList() {
        return FlightAvailabilitySourceFlightList;
    }

    public WebElement getPassengerInputTextBox() {
        return txtbx_PassengerInput;
    }

    public WebElement getPassengerOkayButton() {
        return btn_PassengerOkay;
    }

    public List<WebElement> getListOfOutboundFlightListText() {return txt_OutboundFlightList;}

    public List<WebElement> getListOfOutboundFlightFaresText(){return txt_OutboundFlightFareList;}

    public List<WebElement> getListOfReturnFlightListText() {return txt_ReturnFlightList;}

    public List<WebElement> getListOfReturnFlightFaresText(){return txt_ReturnFlightFareList;}

}
