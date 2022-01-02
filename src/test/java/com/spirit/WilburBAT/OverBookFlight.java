package com.spirit.WilburBAT;
import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

import java.net.*;
import java.util.HashSet;

public class OverBookFlight extends BaseClass {

    String platform = "NA";

    @Parameters({"platform"})
    @Test
    public void OverBookFlight() throws MalformedURLException
    {

        String sheet = "WilburBat";
        ExcelUtil excel = new ExcelUtil("TESTDATA.xlsx");

        HashSet<String> overbookedFlight = new HashSet<>();
        for (int i = 2; i <= excel.getRowCount(sheet);i++) {
            String runMode = excel.getCellData(sheet, "RUN_MODE", i);
            if(!runMode.equalsIgnoreCase("Y")){
                continue;
            }
            String tripType = excel.getCellData(sheet, "TRIP_TYPE", i);
            String depCode = excel.getCellData(sheet, "DEP_AIRPORT_CODE", i);
            String arrCode = excel.getCellData(sheet, "ARR_AIRPORT_CODE", i);
            String depDate = String.valueOf((int) Double.parseDouble(excel.getCellData(sheet, "DEP_DATE", i)));
            String flightNumber = String.valueOf((int) Double.parseDouble(excel.getCellData(sheet, "FLIGHT_NUM", i)));
            if (overbookedFlight.contains(flightNumber)){
                continue;
            }else{
                overbookedFlight.add(flightNumber);
            }

            openWindowApplication(platform);
            WaitUtil.untilTimeCompleted(5000);
            //login page
            windowMethodManager.getSkySpeedLogInPageMethods().loginToSkySpeedApplication("ITMgr");
            //home Page
            windowMethodManager.getSkySpeedHomePageMethods().selectFromToAirports(tripType, depCode, arrCode);
            windowMethodManager.getSkySpeedHomePageMethods().selectJourneyDate(depDate, "NA");
            windowMethodManager.getSkySpeedHomePageMethods().clickNextHomePage();
            //Flight Availability page
            windowMethodManager.getSkySpeedFlightAvailabilityPageMethods().clickRightPaneButtons("OverBook");
            windowMethodManager.getSkySpeedFlightAvailabilityPageMethods().overBookFlightByNumber(flightNumber);
            windowMethodManager.getSkySpeedFlightAvailabilityPageMethods().clickNextFlightAvailabilityPage();
            WaitUtil.untilTimeCompleted(5000);
            //Reserved Flight
            windowMethodManager.getSkySpeedReservedFlightPageMethods().clickNextReservedFlightPage();
            WaitUtil.untilTimeCompleted(5000);
            //Passenger Info Page
            windowMethodManager.getSkySpeedPassengerPageMethods().fillPassengerOverBookingMandatoryFields();
            windowMethodManager.getSkySpeedPassengerPageMethods().clickNextPassengerPage();
            //contact Info
            windowMethodManager.getSkySpeedContactsPageMethods().addContactMandatoryFields();
            windowMethodManager.getSkySpeedContactsPageMethods().clickNextContactPage();
            //Seat Page
            windowMethodManager.getSkySpeedSeatsPageMethods().skipSeatSelection();
            //payment Page
            windowMethodManager.getSkySpeedPaymentPageMethods().cashPayment();
            windowMethodManager.getSkySpeedPaymentPageMethods().clickNextPaymentPage();
            windowMethodManager.getSkySpeedCommentsPageMethods().fillMandatoryComment();
            windowMethodManager.getSkySpeedCommentsPageMethods().clickNextMandatoryCommentPage();
            windowMethodManager.getSkySpeedCommentsPageMethods().clickNextCommentPage();
            windowMethodManager.getSkySpeedEndRecordPageMethods().clickNextEndRecordPage();
            windowMethodManager.getSkySpeedEndRecordPageMethods().fillCallerDetails();
            WaitUtil.untilTimeCompleted(5000);
            getDriver().close();
        }
    }
}
