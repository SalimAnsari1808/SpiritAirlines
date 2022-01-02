package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91050
//Description: Search Widget_ CP_BP_ Vacation layout
//Created By : Kartik Chauhan
//Created On : 08-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************
public class TC91050 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "FlightHotel" , "FlightCar" , "FlightHotelCar" , "HomeUI"})

    public void Search_Widget_CP_BP_Vacation_layout(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91050 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "vacation";
        final String TRIP_TYPE			= "flight+car";
        final String TRIP_TYPE1			= "flight+hotel";
        final String TRIP_TYPE2			= "flight+hotel+car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//STEP--1
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        //Verify field box for room and driver's age is displayed
        ValidationUtil.validateTestStep("Verify field box for room and driver's age is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getRoomsDropDown())
                        && TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getDriversAgeDropDown()));

        //*******************************************************************
        //*****************Verifying Calenders Flight + Car******************
        //*******************************************************************
//STEP--2
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

        WaitUtil.untilTimeCompleted(2000);

//STEP--3
        //select from and To City pairs
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

//STEP--4
        //Verify Date field
        ValidationUtil.validateTestStep("Verify Date field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getDateBox()));

        //Verify Passenger field
        ValidationUtil.validateTestStep("Verify Passenger field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getPassengerBox()));

        //Verify Driver's Age field
        ValidationUtil.validateTestStep("Verify Driver's Age field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getDriversAgeDropDown()));
//VERIFY Date************************************************
        //declare variable used in method
        String strActualDepDate, strActualArrDate = null, strFinalDate=null;
        String strDepDate = "1";
        String strArrDate = "4";
        //get the dep date
        strActualDepDate = TestUtil.getStringDateFormat(strDepDate, "MM/dd/yyyy");

        //convert date in required format
        strActualArrDate = TestUtil.getStringDateFormat(strArrDate, "MM/dd/yyyy");

        strFinalDate = strActualDepDate + " - " + strActualArrDate;

        //Verify Date field field
        ValidationUtil.validateTestStep("Verify Date field is displaying tomorrow's date",
                pageObjectManager.getHomePage().getDateBox().getAttribute("value"),strFinalDate);
//VERIFY pax ************************************************
//Verify 2 Adults
        //Verify 2 Adult's pax is displaying
        ValidationUtil.validateTestStep("Verify 2 Adult's pax is displaying as By Default",
                pageObjectManager.getHomePage().getAdultBox().getAttribute("value"),"2");

//VERIFY Driver's age ************************************************

        //Click on Drivers Age drop down
        pageObjectManager.getHomePage().getDriversAgeDropDown().click();

        //Verify Drivers's option 21- 24
        ValidationUtil.validateTestStep("Verify Drivers's option 21- 24 is displaying",
                pageObjectManager.getHomePage().getDriversAgeDropDown().getText(),"21-24");

        //Verify Drivers's option 21- 24
        ValidationUtil.validateTestStep("Verify Drivers's option 25+ is displaying",
                pageObjectManager.getHomePage().getDriversAgeDropDown().getText(),"25+");
        //STEP--5
        //*******************************************************************
        //*****************Verifying Calenders Flight + Hotel****************
        //*******************************************************************
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE1);
//STEP--6
        //select from and To City pairs
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

        //Verify Date field
        ValidationUtil.validateTestStep("Verify Date field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getDateBox()));

        //Verify Passenger field
        ValidationUtil.validateTestStep("Verify Passenger field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getPassengerBox()));

        //Verify Driver's Age field
        ValidationUtil.validateTestStep("Verify Driver's Age field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getDriversAgeDropDown()));
//STEP--7
// VERIFY Date************************************************
        //declare variable used in method
        String strActualDepDate1, strActualArrDate1 = null, strFinalDate1=null;
        String strDepDate1 = "1";
        String strArrDate1 = "4";
        //get the dep date
        strActualDepDate1 = TestUtil.getStringDateFormat(strDepDate1, "MM/dd/yyyy");

        //convert date in required format
        strActualArrDate1 = TestUtil.getStringDateFormat(strArrDate1, "MM/dd/yyyy");

        strFinalDate1 = strActualDepDate1 + " - " + strActualArrDate1;

        //Verify Date field field
        ValidationUtil.validateTestStep("Verify Date field is displaying tomorrow's date",
                pageObjectManager.getHomePage().getDateBox().getAttribute("value"),strFinalDate1);
//VERIFY pax ************************************************
//Verify 2 Adults
        //Verify 2 Adult's pax is displaying
        ValidationUtil.validateTestStep("Verify 2 Adult's pax is displaying as By Default",
                pageObjectManager.getHomePage().getAdultBox().getAttribute("value"),"2");
//VERIFY Room************************************************
//Verify 1 Room
        //Verify 1 Room is displaying
        ValidationUtil.validateTestStep("Verify 1 Room is displaying as By Default",
                pageObjectManager.getHomePage().getRoomsDropDown().getText(),"1 Room");

//STEP--8
        //*******************************************************************
        //*****************Verifying Calenders Flight + Hotel + Car**********
        //*******************************************************************
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);

        WaitUtil.untilTimeCompleted(2000);
//STEP--9
        //select from and To City pairs
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

        //Verify Date field
        ValidationUtil.validateTestStep("Verify Date field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getDateBox()));

        //Verify Passenger field
        ValidationUtil.validateTestStep("Verify Passenger field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getPassengerBox()));

        //Verify Driver's Age field
        ValidationUtil.validateTestStep("Verify Driver's Age field is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getDriversAgeDropDown()));
//STEP--10
// VERIFY Date************************************************
        //declare variable used in method
        String strActualDepDate2, strActualArrDate2 = null, strFinalDate2=null;
        String strDepDate2 = "1";
        String strArrDate2 = "4";
        //get the dep date
        strActualDepDate2 = TestUtil.getStringDateFormat(strDepDate2, "MM/dd/yyyy");

        //convert date in required format
        strActualArrDate2 = TestUtil.getStringDateFormat(strArrDate2, "MM/dd/yyyy");

        strFinalDate2 = strActualDepDate2 + " - " + strActualArrDate2;

        //Verify Date field field
        ValidationUtil.validateTestStep("Verify Date field is displaying tomorrow's date",
                pageObjectManager.getHomePage().getDateBox().getAttribute("value"),strFinalDate2);
//VERIFY pax ************************************************
//Verify 2 Adults
        //Verify 2 Adult's pax is displaying
        ValidationUtil.validateTestStep("Verify 2 Adult's pax is displaying as By Default",
                pageObjectManager.getHomePage().getAdultBox().getAttribute("value"),"2");
//VERIFY Room************************************************
//Verify 1 Room
        //Verify 1 Room is displaying
        ValidationUtil.validateTestStep("Verify 1 Room is displaying as By Default",
                pageObjectManager.getHomePage().getRoomsDropDown().getText(),"1 Room");

        //VERIFY Driver's age ************************************************

        //Click on Drivers Age drop down
        pageObjectManager.getHomePage().getDriversAgeDropDown().click();

        //Verify Drivers's option 21- 24
        ValidationUtil.validateTestStep("Verify Drivers's option 21- 24 is displaying",
                pageObjectManager.getHomePage().getDriversAgeDropDown().getText(),"21-24");

        //Verify Drivers's option 21- 24
        ValidationUtil.validateTestStep("Verify Drivers's option 25+ is displaying",
                pageObjectManager.getHomePage().getDriversAgeDropDown().getText(),"25+");

    }
}
