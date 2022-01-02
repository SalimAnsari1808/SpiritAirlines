package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.BookPage;
import com.spirit.mobileObjects.Common;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookPageMethods {

    private AppiumDriver driver;
    private ScenarioContext scenarioContext;
    private BookPage bookPage;
    private Common common;

    public BookPageMethods(AppiumDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        bookPage = mobileObjectManager.getBookPage();
        common = mobileObjectManager.getCommon();
    }

    // **********************************************************************************************
    // Method : selectTripType
    // Description: Method is used to Between One-Way and RoundTrip
    // Input Arguments: String tripType (either "OneWay" or "RoundTrip")
    // Return: Null
    // Created By : Anthony Cardona
    // Created On : 22-10-2019
    // Reviewed By: Salim Ansari
    // Reviewed On: 24-Oct-2019
    // ***********************************************************************************************
    public void selectTripType(String tripType) {
        switch (tripType.toUpperCase()) {
            case "ONEWAY":
                bookPage.getOneWayButton().click();
                ValidationUtil.validateTestStep("The user selected One-Way on Book Screen" , true);
                break;

            case "ROUNDTRIP":
                bookPage.getRoundTripButton().click();
                ValidationUtil.validateTestStep("The user selected Round Trip on Book Screen" , true);
                break;
        }
    }

    // **********************************************************************************************
    // Method : selectAirports
    // Description: Method is used to select Origin and Destination Airports
    // Input Arguments: String strDepAirport , String strArrAirport
    // Return:
    // Created By : Anthony Cardona
    // Created On : 22-10-2019
    // Reviewed By: Salim Ansari
    // Reviewed On: 24-Oct-2019
    // **********************************************************************************************
    public void selectAirports(String strDepAirport, String strArrAirport) {
        //click on departure city box
        bookPage.getFromCitySelectButton().click();

        //send departure airport code
        bookPage.getStationCodeSearchTextBox().sendKeys(strDepAirport);

        //get all list of departure airports code
        List<MobileElement> allCityPairCode = bookPage.getAllAirportsStationCodeText();

        //loop through all displayed airports code
        for(MobileElement element : allCityPairCode){
            //check for matching airport code on screen
            if(element.getAttribute("text").equalsIgnoreCase(strDepAirport)){
                //click on airport code
                element.click();

                break;
            }
        }

        //verify departure airport is selected
        ValidationUtil.validateTestStep("From Airport is correct on Book Screen" , strDepAirport , bookPage.getFromCitySelectButton().getText());

        //Store Airport into Context file
        scenarioContext.setContext(Context.HOMEPAGE_DEP_AIRPORT , strDepAirport);

        //click on return airport
        bookPage.getToCitySelectButton().click();

        //send arrival airport code
        bookPage.getStationCodeSearchTextBox().sendKeys(strArrAirport);

        //get all airports code
        allCityPairCode = bookPage.getAllAirportsStationCodeText();

        //loop through all airports code
        for(MobileElement element : allCityPairCode){
            //check for arrival airport
            if(element.getAttribute("text").equalsIgnoreCase(strArrAirport)){
                //click on arrival airport
                element.click();

                break;
            }
        }

        //verify arrival airport is correct
        ValidationUtil.validateTestStep("To Airport is correct on Book Screen" , strDepAirport , bookPage.getToCitySelectButton().getText());

        //Store Airport into Context file
        scenarioContext.setContext(Context.HOMEPAGE_ARR_AIRPORT , strArrAirport);
    }

    // **********************************************************************************************
    // Method : inputPromoCode
    // Description: Method is used to input promo code into the promo code textbox
    // Input Arguments: String promoCode
    // Return:
    // Created By : Anthony Cardona
    // Created On : 22-10-2019
    // Reviewed By: Salim Ansari
    // Reviewed On: 24-Oct-2019
    // **********************************************************************************************
    public void inputPromoCode(String promoCode) {
        bookPage.getPromoCodeTextBox().sendKeys(promoCode);

        ValidationUtil.validateTestStep("The user input promo code: " + promoCode , true);

    }






    // **********************************************************************************************
    // Method : selectTravellingPassenger
    // Description: Method is used to Select the number of passengers (Adult, Children , infant_Seat , infant_Lap )
    // Input Arguments: String strAdultCount,String strChildCount,String strInfantSeatCount,String strInfantLapCount
    // Return: NA
    // Created By : Anthony Cardona
    // Created On : 23-10-2019
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    public void selectTravellingPassenger(String strAdultCount,String strChildCount,String strInfantSeatCount,String strInfantLapCount) {
        //constant variables
        int adults_desired      = Integer.parseInt(strAdultCount);
        int children_desired    = Integer.parseInt(strChildCount);
        int seatInfant_desired  = Integer.parseInt(strInfantSeatCount);
        int lapInfant_desired   = Integer.parseInt(strInfantLapCount);
        int totalInfant         = seatInfant_desired + lapInfant_desired;

        //store information into
        scenarioContext.setContext(Context.HOMEPAGE_ADULT_COUNT , strAdultCount);
        scenarioContext.setContext(Context.HOMEPAGE_CHILD_COUNT , strChildCount);
        scenarioContext.setContext(Context.HOMEPAGE_INFANTSEAT_COUNT , strInfantSeatCount);
        scenarioContext.setContext(Context.HOMEPAGE_INFANTLAP_COUNT , strInfantLapCount);

        //click on passenger button
        bookPage.getPassengersTextButton().click();

        //WaitUtil.untilTimeCompleted(1000); //wait 1 second

        ////////////////////////////////ADD/REMOVE ADULT////////////////////////////////////////

        //get defaultAdult count as int
        int defaultAdultCount = Integer.parseInt(bookPage.getPassengersNumberOfAdultsText().getText());

        //create for loop to increment until correct number of adults are added
        //if default < adultDesiredCount
        //create for loop to decrease until correct number of adults are added
        if(defaultAdultCount < adults_desired) {
            for(int i = defaultAdultCount ; i < adults_desired ; i ++) {
                bookPage.getPassengersAdultsPlusButton().click();//click add button
                ValidationUtil.validateTestStep("Adult:" + (i+1) + " added on Book Screen" , Integer.toString(i+1) , bookPage.getPassengersNumberOfAdultsText().getText());
            }
        }
        //else default > adultDesiredCount
        //create for loop to DECREASE until correct number of adults are removed
        else if (defaultAdultCount > adults_desired) {
            for(int i = defaultAdultCount ; i > adults_desired ; i --) {
                bookPage.getPassengersAdultsMinusButton().click();//click minus button
                ValidationUtil.validateTestStep("Adult " + (i+1) + " removed on Book Screen" , Integer.toString(i-1) , bookPage.getPassengersNumberOfAdultsText().getText());
            }
        }

        ////////////////////////////////ADD/REMOVE CHILDREN////////////////////////////////////////

        //get defaultChildren count
        int defaultChildCount = Integer.parseInt(bookPage.getPassengersNumberOfChildrenText().getText());

        //if default < ChildrenDesiredCount
        //create for loop to increment until correct number of adults are added
        if(defaultChildCount < children_desired) {
            for(int i = defaultChildCount ; i < children_desired ; i ++) {
                bookPage.getPassengersChildrenPlusButton().click();//click add button
                ValidationUtil.validateTestStep("Child " + (i+1) + " added" , Integer.toString(i+1) , bookPage.getPassengersNumberOfChildrenText().getText());
            }
        }
        //else default > ChildrenDesiredCount
        //create for loop to decrease until correct number of adults are added
        else if (defaultChildCount > children_desired) {
            for(int i = defaultChildCount ; i > children_desired ; i --) {
                bookPage.getPassengersChildrenMinusButton().click();//click minus button
                ValidationUtil.validateTestStep("Child " + (i+1) + " removed" , Integer.toString(i-1) , bookPage.getPassengersNumberOfChildrenText().getText());
            }
        }

        for(int childCalenderCount=0;childCalenderCount<bookPage.getPassengersChildDateOfBirthButton().size();childCalenderCount++){
            //open child calender
            bookPage.getPassengersChildDateOfBirthButton().get(childCalenderCount).click();

            //fill child DOB
            fillChildrenAndInfantDOB(TestUtil.getStringDateFormat(Integer.toString(-2190-childCalenderCount),"MM/dd/YYY"));
        }


        ////////////////////////////////ADD/REMOVE INFANTS////////////////////////////////////////

        //getdefaultInfant count
        int defaultInfantCount = Integer.parseInt(bookPage.getPassengersNumberOfLapInfantText().getText());

        //if default < InfantDesiredCount
        //create for loop to increment until correct number of adults are added
        if (defaultInfantCount < totalInfant) {
            for(int i = defaultInfantCount ; i < totalInfant ; i ++) {
                bookPage.getPassengersLapInfantPlusButton().click();//click plus button
                ValidationUtil.validateTestStep("Infant " + (i+1) + " added" , Integer.toString(i+1) , bookPage.getPassengersNumberOfLapInfantText().getText());
            }
        }
        //else default > InfantDesiredCount
        //create for loop to decrease until correct number of adults are added
        else if (defaultInfantCount > totalInfant) {
            for(int i = defaultInfantCount ; i > totalInfant ; i --) {
                bookPage.getPassengersLapInfantMinusButton().click();//click minus button
                ValidationUtil.validateTestStep("Infant " + (i+1) + " removed" , Integer.toString(i-1) , bookPage.getPassengersNumberOfLapInfantText().getText());
            }
        }

        for(int infantCalenderCount=0;infantCalenderCount<bookPage.getPassengersLapInfantDateOfBirthButton().size();infantCalenderCount++){

            //click on infant calender
            bookPage.getPassengersLapInfantDateOfBirthButton().get(infantCalenderCount).click();

            //fill infant DOB
            fillChildrenAndInfantDOB(TestUtil.getStringDateFormat(Integer.toString(-14-infantCalenderCount),"MM/dd/YYY"));

            //wait for 0.5 sec
            WaitUtil.untilTimeCompleted(500);

            //check seat is required
            if(infantCalenderCount<lapInfant_desired){
                //select seat for infant
                bookPage.getPassengersInfantNeedsSeatSwitch().get(infantCalenderCount).click();
            }
        }

        bookPage.getPassengersDoneButton().click();

    }

    // **********************************************************************************************
    // Method : fillChildrenAndInfantDOB
    // Description: Method is used to Accept Default date of birth for Infants and children older than 5 (requirement for UMNR)
    // Input Arguments: NA
    // Return:
    // Created By : Anthony Cardona
    // Created On : 23-Oct-2019
    // Reviewed By: Salim Ansari
    // Reviewed On: 25-Oct-2019
    // **********************************************************************************************
    private void fillChildrenAndInfantDOB(String infantChildDOB) {
        //declare constant used nmethod
        final int DOB_YEAR  = Integer.parseInt(infantChildDOB.split("/")[2]);
        final int DOB_DAY   = Integer.parseInt(infantChildDOB.split("/")[1]);
        final int DOB_MONTH = Integer.parseInt(infantChildDOB.split("/")[0]);

        //declare variables used in method
        List<MobileElement> yearList;
        boolean listFlag   = true;
        int     swipeCounter = 0;

        //click on calender year button
        common.getPassengerCalenderDOBPopUpYearHeaderButton().click();

        //wait for year list appear on screen
        for(int waitCounter=0;waitCounter<7;waitCounter++){
            yearList = common.getPassengerCalenderDOBPopUpYearText();

            if(yearList.size()>0){
                //year list loaded break from loop
                break;
            }else{
                //wait for 1 sec
                WaitUtil.untilTimeCompleted(1000);
            }
        }

        //get all year list
        yearList = common.getPassengerCalenderDOBPopUpYearText();

        while(listFlag){
            //loop through all mobile lement of yesr
            for(MobileElement requiredYear : yearList){
                //check selected element is matching with year
                if(Integer.parseInt(requiredYear.getAttribute("text"))-DOB_YEAR==0){
                    //click on required element
                    requiredYear.click();

                    //set flag to False
                    listFlag    = false;

                    //break loop
                    break;
                }else if(swipeCounter==20){
                    //break loop if counter reeach 20
                    listFlag    = false;

                    break;
                }
            }

            //check required year is not found in list
            if(listFlag){
                //get first and last element from year list
                MobileElement firstElement  = yearList.get(0);
                MobileElement lastElement   = yearList.get(yearList.size()-1);

                //check required year is below or above screen
                if(Integer.parseInt(lastElement.getAttribute("text"))-DOB_YEAR>0){
                    //scroll down
                    TestUtil.swipeScreenOnMobile(driver,firstElement,lastElement);
                }else{
                    //scroll up
                    TestUtil.swipeScreenOnMobile(driver,lastElement,firstElement);
                }
            }

            //increment swipe counter
            swipeCounter++;
        }

        //get all elements of days on current calender
        List<MobileElement> allDays = common.getPassengerCalenderDOBPopUpDaysText();

        //declare variable
        Calendar cal = null;
        MobileElement selectMonthLink;
        int monthCounter;

        //get the required month number from given date
        try{
            Date date = new SimpleDateFormat("MMMM").parse(allDays.get(0).getAttribute("content-desc").split(" ")[1]);
            cal = Calendar.getInstance();
            cal.setTime(date);
        }catch(Exception e){

        }

        //check required month is previous of next month as compared with current month
        if(DOB_MONTH - cal.get(Calendar.MONTH)-1>0){
            //select next month button
            selectMonthLink = common.getPassengerCalenderDOBPopUpNextMonthButton();

            //get month difference
            monthCounter = DOB_MONTH - cal.get(Calendar.MONTH);
        }else{
            //get previous month button
            selectMonthLink = common.getPassengerCalenderDOBPopUpPreviousMonthButton();

            //get month difference
            monthCounter = cal.get(Calendar.MONTH)-DOB_MONTH;
        }

        //loop through to select the required montyh either using previous/next month button
        for(int counterMonth=0;counterMonth<=monthCounter;counterMonth++){
            selectMonthLink.click();

            WaitUtil.untilTimeCompleted(500);
        }

        //get all days of required month
        allDays = common.getPassengerCalenderDOBPopUpDaysText();

        //loop through all days of month calender
        for(int counterDay=0;counterDay<allDays.size();counterDay++){
            //get selected day from required month
            int dayNumber = Integer.parseInt(allDays.get(counterDay).getAttribute("content-desc").split(" ")[0]);

            //check the curerent day is required day in calender
            if(DOB_DAY-dayNumber==0){
                allDays.get(counterDay).click();

                break;
            }
        }

        //wait for 1/2 sec
        WaitUtil.untilTimeCompleted(500);

        //click on OK button
        common.getPassengerCalenderDOBPopUpOKButton().click();
    }

    // **********************************************************************************************
    // Method : clickSearchFlights
    // Description: Click on Search Button at bottom of book page
    // Input Arguments:
    // Return: NA
    // Created By : Anthony Cardona
    // Created On : 23-Oct-2019
    // Reviewed By: Salim Ansari
    // Reviewed On: 25-Oct-2019
    // **********************************************************************************************
    public void clickSearchFlights() {
        //click on flight seaqrch button
        bookPage.getSearchFlightButton().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);
    }

    // **********************************************************************************************
    // Method : acceptUMNRPopup
    // Description: accept UMNR popup on Books page
    // Input Arguments:
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 29-Oct-2019
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    public void acceptUMNRPopup(){

        //declare constant used in method
        final String UMNR_MESSAGE   = "Unaccompanied Minor Service Fee will be charged: $100 per child, each way. This will be included in the fare shown on the next page. Please review our Unaccompanied Minor acceptance policies.";

        //check UMNR popup appear on screen
        if(bookPage.getUMNRPopUpMessageText().isDisplayed()){
            ValidationUtil.validateTestStep("Verify the message appear in UMNR popup on Book Screen",
                    bookPage.getUMNRPopUpMessageText().getAttribute("text").equalsIgnoreCase(UMNR_MESSAGE) );
        }

        bookPage.getUMNRPopUpAcceptButton().click();

        ValidationUtil.validateTestStep("User accepted the UMNR popup on Book Screen",true);
    }

    // **********************************************************************************************
    // Method : acceptOneWayInternationalPopup
    // Description: accept OneWay International popup on Books page
    // Input Arguments:
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 29-Oct-2019
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    public void acceptOneWayInternationalPopup(){

        final String ONEWAY_INTERNATIONAL_POPUP = "One-Way International travel may require proof of return travel at the airport.";

        if(bookPage.getOneWayInternationalPopUpMessageText().isDisplayed()){

            ValidationUtil.validateTestStep("Verify the message appear in One Way International popup on Book Screen",
                    bookPage.getOneWayInternationalPopUpMessageText().getAttribute("text").equalsIgnoreCase(ONEWAY_INTERNATIONAL_POPUP));
        }

        bookPage.getOneWayInternationalPopUpOKButton().click();


        ValidationUtil.validateTestStep("User accepted the One Way International popup on Book Screen",true);
    }
}