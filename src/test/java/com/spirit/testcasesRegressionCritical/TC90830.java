package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90830
//Description: Search Widget_CP_BP_Vacation Calendar
//Created By : Sunny Sok
//Created On : 06-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 07-Aug-2019
//**********************************************************************************************

public class TC90830 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"HomeUI","DomesticDomestic","BookPath","WithIn7Days","FlightHotel","Adult","Guest"})
    public void Search_Widget_CP_BP_Vacation_Calendar(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90830 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "vacation";
        final String TRIP_TYPE			= "flight+hotel+car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        //XPath for radio button is incorrect
        ValidationUtil.validateTestStep("Verify Flight+Hotel+Car default selected",
                JSExecuteUtil.getElementAfterProperty(getDriver(),pageObjectManager.getHomePage().getFlightHotelCarRadiobutton(),"background-color"),"rgb(0, 115, 230)");

        ValidationUtil.validateTestStep("Verify field box for room and driver's age is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getRoomsDropDown())
                        && TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getDriversAgeDropDown()));

        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

        //*******************************************************************
        //*************Verifying Calendars for flight + Hotel + Car**********
        //*******************************************************************

        WaitUtil.untilTimeCompleted(2000);

        //clicking on Date box
        pageObjectManager.getHomePage().getDateBox().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Putting the Calendars into list and verifying the size is 2
        List<WebElement> Calendars = getDriver().findElements(By.xpath("//bs-days-calendar-view"));

        ValidationUtil.validateTestStep("Two Calendars is displayed for flight + Hotel + Car Flight", 2 == Calendars.size());

        List<WebElement> CalendarPreviousBtnLst = pageObjectManager.getHomePage().getCalendarPreviousButton();
        List<WebElement> CalendarNextBtnLst = pageObjectManager.getHomePage().getCalendarNextButton();


        WebElement CalendarPreviousBtn = null;
        WebElement CalendarNextBtn = null;

        for (int count = 0; count<CalendarPreviousBtnLst.size();count++){
            if(CalendarPreviousBtnLst.get(count).isDisplayed()){
                CalendarPreviousBtn = CalendarPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalendarNextBtnLst.size();count++){
            if(CalendarNextBtnLst.get(count).isDisplayed()){
                CalendarNextBtn = CalendarNextBtnLst.get(count);
            }
        }

        //Verifying Calendar previous button is not enabled
        ValidationUtil.validateTestStep("Calendar Previous button is not enabled for flight + Hotel + Car Flight",  !CalendarPreviousBtn.isEnabled()) ;

        //Verifying Calendar Next button is enabled
        ValidationUtil.validateTestStep("Calendar Next button is enabled for flight + Hotel + Car Flight",  CalendarNextBtn.isEnabled());

        //Clicking the Calendar next button
        CalendarNextBtn.click();

        WaitUtil.untilTimeCompleted(1000);

        CalendarPreviousBtn = null;
        CalendarNextBtn = null;

        for (int count = 0; count<CalendarPreviousBtnLst.size();count++){
            if(CalendarPreviousBtnLst.get(count).isDisplayed()){
                CalendarPreviousBtn = CalendarPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalendarNextBtnLst.size();count++){
            if(CalendarNextBtnLst.get(count).isDisplayed()){
                CalendarNextBtn = CalendarNextBtnLst.get(count);
            }
        }

        //verifying the previous button is enabled
        ValidationUtil.validateTestStep("Calendar Previous button is enabled after selecting next Calendar for flight + Hotel + Car Flight",  CalendarPreviousBtn.isEnabled()) ;
        //Clicking the Calendar previous button
        CalendarPreviousBtn.click();


        //*******************************************************************
        //*****************Verifying Calendars Flight + Car******************
        //*******************************************************************

        pageMethodManager.getHomePageMethods().selectTripType("flight+car");

        WaitUtil.untilTimeCompleted(2000);

        //clicking on Date box
        pageObjectManager.getHomePage().getDateBox().click();

        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Two Calendars are displayed for Flight + Car bookings", 2 == Calendars.size());

        CalendarPreviousBtnLst = pageObjectManager.getHomePage().getCalendarPreviousButton();
        CalendarNextBtnLst = pageObjectManager.getHomePage().getCalendarNextButton();


        CalendarPreviousBtn = null;
        CalendarNextBtn = null;

        for (int count = 0; count<CalendarPreviousBtnLst.size();count++){
            if(CalendarPreviousBtnLst.get(count).isDisplayed()){
                CalendarPreviousBtn = CalendarPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalendarNextBtnLst.size();count++){
            if(CalendarNextBtnLst.get(count).isDisplayed()){
                CalendarNextBtn = CalendarNextBtnLst.get(count);
            }
        }

        //Verifying Calendar previous button is not enabled
        ValidationUtil.validateTestStep("Calendar Previous button is not enabled for Flight + Car Flight",  !CalendarPreviousBtn.isEnabled()) ;

        //Verifying Calendar Next button is enabled
        ValidationUtil.validateTestStep("Calendar Next button is enabled for Flight + Car Flight",  CalendarNextBtn.isEnabled());

        //Clicking the Calendar next button
        CalendarNextBtn.click();

        WaitUtil.untilTimeCompleted(1000);

        CalendarPreviousBtn = null;
        CalendarNextBtn = null;

        for (int count = 0; count<CalendarPreviousBtnLst.size();count++){
            if(CalendarPreviousBtnLst.get(count).isDisplayed()){
                CalendarPreviousBtn = CalendarPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalendarNextBtnLst.size();count++){
            if(CalendarNextBtnLst.get(count).isDisplayed()){
                CalendarNextBtn = CalendarNextBtnLst.get(count);
            }
        }

        //verifying the previous button is enabled
        ValidationUtil.validateTestStep("Calendar Previous button is enabled after selecting next Calendar for Flight + Car Flight",  CalendarPreviousBtn.isEnabled()) ;
        //Clicking the Calendar previous button
        CalendarPreviousBtn.click();

        //*******************************************************************
        //*****************Verifying Calendars Flight + Hotel***************
        //*******************************************************************

        //clicking on Multi-City radio
        pageMethodManager.getHomePageMethods().selectTripType("Flight+Hotel");

        WaitUtil.untilTimeCompleted(2000);

        //clicking on Date box
        pageObjectManager.getHomePage().getDateBox().click();

        WaitUtil.untilTimeCompleted(2000);

        //Putting the Calendars into list and verifying the size is 2
        ValidationUtil.validateTestStep("Two Calendars are displayed for Flight + Hotel Booking", 2 == Calendars.size());

        CalendarPreviousBtnLst = pageObjectManager.getHomePage().getCalendarPreviousButton();
        CalendarNextBtnLst = pageObjectManager.getHomePage().getCalendarNextButton();

        CalendarPreviousBtn = null;
        CalendarNextBtn = null;

        for (int count = 0; count<CalendarPreviousBtnLst.size();count++){
            if(CalendarPreviousBtnLst.get(count).isDisplayed()){
                CalendarPreviousBtn = CalendarPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalendarNextBtnLst.size();count++){
            if(CalendarNextBtnLst.get(count).isDisplayed()){
                CalendarNextBtn = CalendarNextBtnLst.get(count);
            }
        }

        //Verifying Calendar previous button is not enabled
        ValidationUtil.validateTestStep("Calendar Previous button is not enabled for Flight + Hotel Flight",  !CalendarPreviousBtn.isEnabled()) ;

        //Verifying Calendar Next button is enabled
        ValidationUtil.validateTestStep("Calendar Next button is enabled for Flight + Hotel Flight",  CalendarNextBtn.isEnabled());

        //Clicking the Calendar next button
        CalendarNextBtn.click();

        WaitUtil.untilTimeCompleted(1000);

        CalendarPreviousBtn = null;
        CalendarNextBtn = null;

        for (int count = 0; count<CalendarPreviousBtnLst.size();count++){
            if(CalendarPreviousBtnLst.get(count).isDisplayed()){
                CalendarPreviousBtn = CalendarPreviousBtnLst.get(count);
            }
        }
        for (int count = 0; count<CalendarNextBtnLst.size();count++){
            if(CalendarNextBtnLst.get(count).isDisplayed()){
                CalendarNextBtn = CalendarNextBtnLst.get(count);
            }
        }

        //verifying the previous button is enabled
        ValidationUtil.validateTestStep("Calendar Previous button is enabled after selecting next Calendar for Flight + Hotel Flight",  CalendarPreviousBtn.isEnabled()) ;
        //Clicking the Calendar previous button
        CalendarPreviousBtn.click();
    }
}