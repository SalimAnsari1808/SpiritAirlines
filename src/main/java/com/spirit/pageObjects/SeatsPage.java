package com.spirit.pageObjects;

import java.util.ArrayList;
import java.util.List;

import com.spirit.utility.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeatsPage {
	
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

	public SeatsPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*******************************************************************
	//************************Seat Header********************************
	//*******************************************************************
	public final String xpath_SeatPageHeaderText = "//h1";
	@FindBy(xpath=xpath_SeatPageHeaderText)
	private WebElement txt_SeatPageHeader;

	public final String xpath_SeatPageSubHeaderText = "//*[contains(text(),'Stretch out') or contains(text(),'Siéntese')]";
	@FindBy(xpath=xpath_SeatPageSubHeaderText)
	private WebElement txt_SeatPageSubHeader;

	//*******************************************************************
	//*********************Flight Seat Type******************************
	//*******************************************************************
	public final String xpath_BigFrontSeatsGridView = "//div[contains(@class,'seat-row-seat') and contains(@class,'big-front')]";
	@FindBy(xpath=xpath_BigFrontSeatsGridView)
	private List<WebElement> grdvew_BigFrontSeats;

	public final String xpath_BigFrontEmptySeatsGridView = "//div[contains(@class,'seat-row-seat') and contains(@class,'big-front') and contains(text(),'$')]";
	@FindBy(xpath = xpath_BigFrontEmptySeatsGridView)
	private List<WebElement> grdvew_BigFrontEmptySeats;

	public final String xpath_PremiumSeatsGridView = "//div[contains(@class,'seat-row-seat') and contains(@class,'premium')]";
	@FindBy(xpath=xpath_PremiumSeatsGridView)
	private List<WebElement> grdvew_PremiumSeats;

	public final String xpath_StandardSeatsGridView = "//div[contains(@class,'seat-row-seat') and contains(@class,'standard')]";
	@FindBy(xpath=xpath_StandardSeatsGridView)
	private List<WebElement> grdvew_StandardSeats;

	public final String xpath_AllSeatGridView = "//app-unit//div[@ng-reflect-klass='seat-row-seat']";
	@FindBy(xpath = xpath_AllSeatGridView)
	private List<WebElement> grdvew_AllSeat;

	//*******************************************************************
	//*********************Premium Seat PopUp****************************
	//*******************************************************************
	public final String xpath_AcceptExitRowSeatButton = "//button[text()='ACCEPT' or text()='ACEPTAR']";
	@FindBy(xpath=xpath_AcceptExitRowSeatButton)
	private WebElement btn_AcceptExitRowSeat;

	public final String xpath_RejectExitRowSeatButton = "//button[text()='Change Seat' or text()='Cambiar asiento']";
	@FindBy(xpath=xpath_RejectExitRowSeatButton)
	private WebElement btn_RejectExitRowSeat;

	//*******************************************************************
	//*********************Passenger Section*****************************
	//*******************************************************************

	public final String xpath_SeatPagePassengerDropDown = "//select";
	@FindBy(xpath = xpath_SeatPagePassengerDropDown)
	private WebElement drpdwn_SeatPagePassenger;

	//	public final String xpath_FlightLegsText = "//div[@class='card-header']//p";
	public final String xpath_FlightLegsText = "//div[@class='card-header']//p | //div[@id='scroll-mobile-seat-designator']/p[2]";
	@FindBy(xpath=xpath_FlightLegsText)
	private List<WebElement> txt_FlightLegs;

	public final String xpath_PassengerDetailListLink = "//div[@class='card-header']//i";
	@FindBy(xpath=xpath_PassengerDetailListLink)
	private List<WebElement> lnk_PassengerDetailList;

	public final String xpath_PassengerListText = "//li[contains(@class,'list-group-item')]";
	@FindBy(xpath=xpath_PassengerListText)
	private List<WebElement> txt_PassengerList;

	public final String xpath_PassengerCounterText = "//div[@class='pax-circle']";
	@FindBy(xpath=xpath_PassengerCounterText)
	private List<WebElement> txt_PassengerCounter;

	public final String xpath_PassengerNameText = "//div[@class='pax-circle']/../following-sibling::div[1]/p";
	@FindBy(xpath=xpath_PassengerNameText)
	private List<WebElement> txt_PassengerName;

	public final String xpath_PassengerSeatText = "//div[@class='pax-circle']/../following-sibling::div[2]/p";
	@FindBy(xpath=xpath_PassengerSeatText)
	private List<WebElement> txt_PassengerSeat;
	//*******************************************************************
	//************************Seats Total********************************
	//*******************************************************************
	public final String xpath_SeatTotalPanel = "(//div[contains(@class,'total-due-container')])[1]";
	@FindBy(xpath = xpath_SeatTotalPanel)
	private WebElement pnl_SeatTotal;

	public final String xpath_SeatsTotalText = "//p[contains(text(),'Seats Total') or contains(text(),'Total de Asientos') ]";
	@FindBy(xpath=xpath_SeatsTotalText)
	private List<WebElement> txt_SeatsTotal;

	public final String xpath_SeatsTotalPriceText = "//div[@data-qa='total-cost']/p";
	@FindBy(xpath=xpath_SeatsTotalPriceText)
	private List<WebElement> txt_SeatsTotalPrice;

	public final String xpath_SeatsTotalContainerLink = "//div[contains(@class,'total-due-container')]//app-chevron//i";
	@FindBy(xpath=xpath_SeatsTotalContainerLink)
	private List<WebElement>  lnk_SeatsTotalContainer;

	//	public final String xpath_SeatTotalBreakDownPriceText = "(//div[@class='total-breakdown'])[1]//app-breakdown-section//following-sibling::div[2]/p";
	public final String xpath_SeatTotalBreakDownPriceText = "//div[@class='total-breakdown']//app-breakdown-section//following-sibling::div[2]/p";
	@FindBy(xpath = xpath_SeatTotalBreakDownPriceText)
	private List<WebElement> txt_SeatTotalBreakDownPrice;

	public final String xpath_SeatTotalOptionItemText = "//div[@data-qa='total-options-item']";
	@FindBy(xpath = xpath_SeatTotalOptionItemText)
	private List<WebElement> txt_SeatTotalOptionItem;

	public final String xpath_SeatPageNextFlightButton = "//button[text()='Next Flight' or text()='Vuelo Siguiente']";
	@FindBy(xpath = xpath_SeatPageNextFlightButton)
	private WebElement btn_SeatPageNextFlight;

	public final String xpath_SeatPagePrevFlightButton = "//button[text()='Prev Flight' or text()='Vuelo Anterior']";
	@FindBy(xpath = xpath_SeatPagePrevFlightButton)
	private WebElement btn_SeatPagePrevFlight;


	//*******************************************************************
	//*********************Purchase Seat Continue************************
	//*******************************************************************
	public final String xpath_ContinueWithSeatButton = "//button[text()='Continue' or text()='Continuar']";
	@FindBy(xpath=xpath_ContinueWithSeatButton)
	private List<WebElement> btn_ContinueWithSeat;

	public final String xpath_ContinueWithoutSeatButton= "//button[contains(text(),'Continue without') or contains(text(),'Continuar sin')]";
	@FindBy(xpath=xpath_ContinueWithoutSeatButton)
	private List<WebElement> btn_ContinueWithoutSeat;

	//**************************************Start of Page return methods************************************

	//*******************************************************************
	//************************Seat Header********************************
	//*******************************************************************
	public WebElement getSeatPageHeaderText() {
		return txt_SeatPageHeader;
	}

	public WebElement getSeatPageSubHeaderText() {
		return txt_SeatPageSubHeader;
	}

	//*******************************************************************
	//*********************Flight Seat Type******************************
	//*******************************************************************
	public List<WebElement> getBigFrontSeatsGridView(){
		return grdvew_BigFrontSeats;
	}

	public List<WebElement> getBigFrontEmptySeatsGridView(){
		return grdvew_BigFrontEmptySeats;
	}

	public List<WebElement> getPremiumSeatsGridView(){
		return grdvew_PremiumSeats;
	}

	public List<WebElement> getStandardSeatsGridView(){
		return grdvew_StandardSeats;
	}

	public List<WebElement> getAllSeatGridView(){
		return grdvew_AllSeat;
	}

	//*******************************************************************
	//*********************Premium Seat PopUp****************************
	//*******************************************************************
	public WebElement getAcceptExitRowSeatButton() {
		return btn_AcceptExitRowSeat;
	}

	public WebElement getRejectExitRowSeatButton() {
		return btn_RejectExitRowSeat;
	}

	//*******************************************************************
	//*********************Passenger Section*****************************
	//*******************************************************************

	public WebElement getSeatPagePassengerDropDown(){
		return drpdwn_SeatPagePassenger;
	}

	public List<WebElement> getFlightLegsText(){
		ArrayList<WebElement> elementList = new ArrayList<>();
		for(WebElement element:txt_FlightLegs){
			if(element.isDisplayed()){
				elementList.add(element);
			}
		}
		return elementList;
	}

	public List<WebElement> getPassengerDetailListLink(){
		return lnk_PassengerDetailList;
	}

	//@FindBy(xpath="//div[@class='col-2 text-right d-flex align-items-center']")
	//    private List<WebElement> lnk_EachPassengerSeatNumber;
	//    public List<WebElement> getEachPassengerSeatNumberLink(){
	//        return lnk_EachPassengerSeatNumber;
	//    }

	public List<WebElement> getPassengerListText(){
		return txt_PassengerList;
	}

	public List<WebElement> getPassengerCounterText(){
		return txt_PassengerCounter;
	}

	public List<WebElement> getPassengerNameText(){
		return txt_PassengerName;
	}

	public List<WebElement> getPassengerSeatText(){
		return txt_PassengerSeat;
	}

	//*******************************************************************
	//************************Seats Total********************************
	//*******************************************************************
	public WebElement getSeatTotalPanel(){
		return pnl_SeatTotal;
	}

	public WebElement getSeatsTotalText(){
		for(WebElement totalAmountText : txt_SeatsTotal){
			if(TestUtil.verifyElementDisplayed(totalAmountText)){
				return totalAmountText;
			}
		}
		return null;
	}

	public WebElement getSeatsTotalPriceText(){
		for(WebElement totalAmountPrice : txt_SeatsTotalPrice){
			if(totalAmountPrice.isDisplayed()){
				return totalAmountPrice;
			}
		}
		return null;
	}

	public WebElement getSeatsTotalContainerLink(){
		for(WebElement totalAmountLink : lnk_SeatsTotalContainer){
			if(totalAmountLink.isDisplayed()){
				return totalAmountLink;
			}
		}

		return null;
	}

	public WebElement getSeatTotalBreakDownPriceText(){
		for(int elementCount=0;elementCount<txt_SeatTotalBreakDownPrice.size();elementCount++){
			if(txt_SeatTotalBreakDownPrice.get(elementCount).isDisplayed()){
				return txt_SeatTotalBreakDownPrice.get(elementCount);
			}
		}
		return null;
	}

	public List<WebElement> getSeatTotalOptionItemText(){
		return txt_SeatTotalOptionItem;
	}

	public WebElement getSeatPageNextFlightButton(){
		return btn_SeatPageNextFlight;
	}

	public WebElement getSeatPagePrevFlightButton(){
		return btn_SeatPagePrevFlight;
	}

	//*******************************************************************
	//*********************Purchase Seat Continue************************
	//*******************************************************************
	public List<WebElement> getContinueWithSeatButton(){
		ArrayList<WebElement> elementList = new ArrayList<>();
		for(WebElement element:btn_ContinueWithSeat){
			if(element.isDisplayed()){
				elementList.add(element);
			}
		}

		return elementList;
	}

	public List<WebElement> getContinueWithoutSeatButton(){
		return btn_ContinueWithoutSeat;
	}
}
