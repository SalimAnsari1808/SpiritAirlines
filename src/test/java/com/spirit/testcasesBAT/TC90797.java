package com.spirit.testcasesBAT;
import com.spirit.baseClass.*;
import com.spirit.pageObjects.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.*;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90797
//Description: Search Widget_CP_BP_RT_OW_Multi_Calendar
//Created By : Sunny Sok
//Created On : 11-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 16-Mar-2019
//**********************************************************************************************

public class TC90797 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"Guest","BookPath","OneWay","MultiCity","RoundTrip","DomesticDomestic","WithIn7Days","Adult","HomeUI"})
    public void Search_Widget_CP_BP_RT_OW_Multi_Calendar(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90797 under BAT Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LGA";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "1";

        //open browser
        openBrowser(platform);
        
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

        //*******************************************************************
        //******************Verifying Calenders for Round Trip***************
        //*******************************************************************

        //get the back ground color after selection
        String backgroundColor = JSExecuteUtil.getElementBackGroundColor(getDriver(), pageObjectManager.getHomePage().getRoundTripLabel());

        //Verifying Round trip is selected
        ValidationUtil.validateTestStep("Round trip Radio is selected by default",backgroundColor,"rgb(0, 115, 230)");

        pageObjectManager.getHomePage().getDateBox().sendKeys(Keys.TAB);

        WaitUtil.untilTimeCompleted(2000);

        //clicking on Date box
        pageObjectManager.getHomePage().getDateBox().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Putting the calenders into list and verifying the size is 2
        List<WebElement> Calenders = getDriver().findElements(By.xpath("//bs-days-calendar-view"));

        ValidationUtil.validateTestStep("Two Calenders is displayed for RoundTrip Flight", 2 == Calenders.size());

        List<WebElement> CalenderPreviousBtnLst = pageObjectManager.getHomePage().getCalendarPreviousButton();
        List<WebElement> CalenderNextBtnLst = pageObjectManager.getHomePage().getCalendarNextButton();


        WebElement CalenderPreviousBtn = null;
        WebElement CalenderNextBtn = null;

        for (int count = 0; count<CalenderPreviousBtnLst.size();count++){
            if(CalenderPreviousBtnLst.get(count).isDisplayed()){
                CalenderPreviousBtn = CalenderPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalenderNextBtnLst.size();count++){
            if(CalenderNextBtnLst.get(count).isDisplayed()){
                CalenderNextBtn = CalenderNextBtnLst.get(count);
            }
        }

        //Verifying calender previous button is not enabled
        ValidationUtil.validateTestStep("Calender Previous button is not enabled for RoundTrip Flight",  !CalenderPreviousBtn.isEnabled()) ;
        
        //Verifying calender Next button is enabled
        ValidationUtil.validateTestStep("Calender Next button is enabled for RoundTrip Flight",  CalenderNextBtn.isEnabled());
        
        //Clicking the calender next button
        CalenderNextBtn.click();

        WaitUtil.untilTimeCompleted(1000);

        CalenderPreviousBtn = null;
        CalenderNextBtn = null;

        for (int count = 0; count<CalenderPreviousBtnLst.size();count++){
            if(CalenderPreviousBtnLst.get(count).isDisplayed()){
                CalenderPreviousBtn = CalenderPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalenderNextBtnLst.size();count++){
            if(CalenderNextBtnLst.get(count).isDisplayed()){
                CalenderNextBtn = CalenderNextBtnLst.get(count);
            }
        }

        //verifying the previous button is enabled
        ValidationUtil.validateTestStep("Calender Previous button is enabled after selecting next calender for Roundtrip Flight",  CalenderPreviousBtn.isEnabled()) ;
        //Clicking the calender previous button
        CalenderPreviousBtn.click();


        //*******************************************************************
        //*****************Verifying Calenders One Way***********************
        //*******************************************************************

        pageMethodManager.getHomePageMethods().selectTripType("OneWay");
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, "NA");

        pageObjectManager.getHomePage().getDateBox().sendKeys(Keys.TAB);

        WaitUtil.untilTimeCompleted(2000);

        //clicking on Date box
        pageObjectManager.getHomePage().getDateBox().click();

        WaitUtil.untilTimeCompleted(2000);

        //Putting the calenders into list and verifying the size is 2
        Calenders = getDriver().findElements(By.xpath("//bs-days-calendar-view"));

        ValidationUtil.validateTestStep("One Calenders is displayed for OneWay Flight", 1 == Calenders.size());

        CalenderPreviousBtnLst = pageObjectManager.getHomePage().getCalendarPreviousButton();
        CalenderNextBtnLst = pageObjectManager.getHomePage().getCalendarNextButton();


        CalenderPreviousBtn = null;
        CalenderNextBtn = null;

        for (int count = 0; count<CalenderPreviousBtnLst.size();count++){
            if(CalenderPreviousBtnLst.get(count).isDisplayed()){
                CalenderPreviousBtn = CalenderPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalenderNextBtnLst.size();count++){
            if(CalenderNextBtnLst.get(count).isDisplayed()){
                CalenderNextBtn = CalenderNextBtnLst.get(count);
            }
        }

        //Verifying calender previous button is not enabled
        ValidationUtil.validateTestStep("Calender Previous button is not enabled for OneWay Flight",  !CalenderPreviousBtn.isEnabled()) ;

        //Verifying calender Next button is enabled
        ValidationUtil.validateTestStep("Calender Next button is enabled for OneWay Flight",  CalenderNextBtn.isEnabled());

        //Clicking the calender next button
        CalenderNextBtn.click();

        WaitUtil.untilTimeCompleted(1000);

        CalenderPreviousBtn = null;
        CalenderNextBtn = null;

        for (int count = 0; count<CalenderPreviousBtnLst.size();count++){
            if(CalenderPreviousBtnLst.get(count).isDisplayed()){
                CalenderPreviousBtn = CalenderPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalenderNextBtnLst.size();count++){
            if(CalenderNextBtnLst.get(count).isDisplayed()){
                CalenderNextBtn = CalenderNextBtnLst.get(count);
            }
        }

        //verifying the previous button is enabled
        ValidationUtil.validateTestStep("Calender Previous button is enabled after selecting next calender for OneWay Flight",  CalenderPreviousBtn.isEnabled()) ;
        //Clicking the calender previous button
        CalenderPreviousBtn.click();


        //*******************************************************************
        //*****************Verifying Calenders Multi-City********************
        //*******************************************************************

        //clicking on Multi-City radio
        pageMethodManager.getHomePageMethods().selectTripType("MultiCity");

        //local variable for Flight 1 calender
        WebElement FlightOneDateInput = getDriver().findElement(By.xpath("//input[@id='date0']"));
        //clicking on flight one date input field
        FlightOneDateInput.click();

        WaitUtil.untilTimeCompleted(2000);

        //Putting the calenders into list and verifying the size is 2
        Calenders = getDriver().findElements(By.xpath("//div[@class='bs-datepicker-head']"));

        System.out.println(Calenders.size());
        //verifying flight one calender is displayed
        ValidationUtil.validateTestStep("Flight One Calender is displayed",Calenders.size()==1);

        CalenderPreviousBtnLst = pageObjectManager.getHomePage().getCalendarPreviousButton();
        CalenderNextBtnLst = pageObjectManager.getHomePage().getCalendarNextButton();

        //verifying the calender previous button is not Enabled
        CalenderPreviousBtn = null;
        CalenderNextBtn = null;

        for (int count = 0; count<CalenderPreviousBtnLst.size();count++){
            if(CalenderPreviousBtnLst.get(count).isDisplayed()){
                CalenderPreviousBtn = CalenderPreviousBtnLst.get(count);
                CalenderPreviousBtn.click();
            }
        }
        for (int count = 0; count<CalenderNextBtnLst.size();count++){
            if(CalenderNextBtnLst.get(count).isDisplayed()){
                CalenderNextBtn = CalenderNextBtnLst.get(count);
            }
        }
        System.out.println(CalenderPreviousBtn.isEnabled());

        ValidationUtil.validateTestStep("Calender Previous button is not enabled",  !CalenderPreviousBtn.isEnabled());

        //verifying the calender next button is enabled
        ValidationUtil.validateTestStep("Calender Next button is enabled",  CalenderNextBtn.isEnabled());
        //clicking on calender next button
        CalenderNextBtn.click();

        CalenderPreviousBtn = null;
        CalenderNextBtn = null;

        for (int count = 0; count<CalenderPreviousBtnLst.size();count++){
            if(CalenderPreviousBtnLst.get(count).isDisplayed()){
                CalenderPreviousBtn = CalenderPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalenderNextBtnLst.size();count++){
            if(CalenderNextBtnLst.get(count).isDisplayed()){
                CalenderNextBtn = CalenderNextBtnLst.get(count);
            }
        }

        //verifying the calender previous button is enabled
        ValidationUtil.validateTestStep("Calender Previous button is enabled",  CalenderPreviousBtn.isEnabled());
        //clicking on calender previous button
        CalenderPreviousBtn.click();
        //clicking on flight one date input field
        FlightOneDateInput.click();

        //local variable for Flight 2 calender
        WebElement FlightTwoDateInput = getDriver().findElement(By.xpath("//input[@id='date1']"));
        //clicking on flight two date input field
        FlightTwoDateInput.click();


        CalenderPreviousBtn = null;
        CalenderNextBtn = null;

        for (int count = 0; count<CalenderPreviousBtnLst.size();count++){
            if(CalenderPreviousBtnLst.get(count).isDisplayed()){
                CalenderPreviousBtn = CalenderPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalenderNextBtnLst.size();count++){
            if(CalenderNextBtnLst.get(count).isDisplayed()){
                CalenderNextBtn = CalenderNextBtnLst.get(count);
            }
        }

        //verifying flight one calender is displayed
        ValidationUtil.validateTestStep("Flight Two Calender is displayed", TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getCalenderPanel()));
        //verifying the calender previous button is not Enabled
        ValidationUtil.validateTestStep("Calender Previous button is not enabled",  !CalenderPreviousBtn.isEnabled());
        //verifying the calender next button is enabled
        ValidationUtil.validateTestStep("Calender Next button is enabled",  CalenderNextBtn.isEnabled());
        //clicking on calender next button
        CalenderNextBtn.click();

        CalenderPreviousBtn = null;
        CalenderNextBtn = null;

        for (int count = 0; count<CalenderPreviousBtnLst.size();count++){
            if(CalenderPreviousBtnLst.get(count).isDisplayed()){
                CalenderPreviousBtn = CalenderPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalenderNextBtnLst.size();count++){
            if(CalenderNextBtnLst.get(count).isDisplayed()){
                CalenderNextBtn = CalenderNextBtnLst.get(count);
            }
        }



        //verifying the calender previous button is enabled
        ValidationUtil.validateTestStep("Calender Previous button is enabled",  CalenderPreviousBtn.isEnabled());
        //clicking on calender previous button
        CalenderPreviousBtn.click();
    }
}
