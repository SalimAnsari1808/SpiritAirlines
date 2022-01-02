package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
/*10/21/19 test case passed, removed active *bug tag**/
//Test Case ID: TC91122
//Title: Task 22968: 31284 Flight Availability_CP_BP_Applying for FSMC booking flights with miles
//Description: Validating user is taken to the right URL after select apply for a FSMC
//Created By : Gabriela
//Created On : 13-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-May-2019
//**********************************************************************************************

public class TC91122 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" ,"Adult", "FreeSpirit",
                     "FlightAvailabilityUI","Miles"})
    public void Flight_Availability_CP_BP_Applying_for_FSMC_booking_flights_with_miles(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91122 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE 	    = "Flight";
        final String TRIP_TYPE 		    = "Oneway";
        final String DEP_AIRPORTS 	    = "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "BOS";
        final String DEP_DATE 		    = "53";
        final String ARR_DATE 		    = "NA";
        final String ADULTS 		    = "1";
        final String CHILD  		    = "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT	    = "0";

        //Flight Availability Constant Values
        final String FA_URL             = "book/flights";
        final String BOFA_URL           = "https://secure.bankofamerica.com/apply";

//-- Step 1: Access test environment and login with a member that is not a Free Spirit Mastercard holder
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//-- Step 2: Start booking a flight and reach flight availability page
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//-- Step 3: Verify user lands on Flight Availability page url
        ValidationUtil.validateTestStep("Validating the user land on Flight Availability Page",
                getDriver().getCurrentUrl(),FA_URL);

//-- Step 4: Locate the slider button WEEK/MONTH and DOLLARS/MILES
        ValidationUtil.validateTestStep("Validating Dollar button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDollarsViewSwitchLabel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Miles button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Months button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Week button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselWeekViewSwitchLabel().isDisplayed());

//-- Step 5: Click on Miles
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();

//-- Step 6: Click on Month
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().click();

//-- Step 7: Select a date for which there are FSMC flights

        for(int monthCounter=0;monthCounter<5;monthCounter++){
            int numOfDates = getDriver().findElements(By.xpath("(//div[@data-qa='journey-results'])[1]//app-low-fare-day/button[not(@disabled)]")).size();

            //Looping on Carousel and verifying there is FSMC present
            for(int i = 0; i < numOfDates; i ++)
            {
                WaitUtil.untilTimeCompleted(1200);
                WaitUtil.untilPageLoadComplete(getDriver());
                List<WebElement> tempMCFlights = pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsMCIconText(); //FSMC flights list
                List<WebElement> newFSMCFlight = new ArrayList<>(); //array that will hold values that are displayed

                for (WebElement flightCheck : tempMCFlights)
                {
                    if (flightCheck.isDisplayed())
                    {
                        newFSMCFlight.add(flightCheck);
                    }
                }

                for (WebElement flight : newFSMCFlight) // selecting MC flights
                {
                    if (flight.getText().contains("MC"))
                    {
                        flight.click();
                        monthCounter = 5;
                        i = numOfDates;
                        break;
                    }
                }

                if ( i == numOfDates-1) //if last date in the calendar, go to next month
                {
                    pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselRightButton().get(0).click();
                    WaitUtil.untilPageLoadComplete(getDriver());
                }

                newFSMCFlight.clear();

                try { //try to get the next date, if the flight is selected this will fail so we must use a try catch
                    getDriver().findElement(By.xpath("((//div[@data-qa='journey-results'])[1]//app-low-fare-day/button[not(@disabled)])["+(i+1)+"]")).click();
                    WaitUtil.untilJqueryIsDone(getDriver());
                }
                catch (Exception e){}
            }

        }


//-- Step 8: Select a flight for FSMC
        List<WebElement> fares = pageObjectManager.getFlightAvailabilityPage().getDepartingMCHolderFarePriceText();
        for (int e = 0; e < fares.size(); e ++)
        {
            if (fares.get(e).isDisplayed())
                fares.get(e).click();
            break;
        }

//-- Step 9: Click on the Apply Now button
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getFSMCPopUpApplyNowButton().click();

        TestUtil.switchToWindow(getDriver(),1);
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating the user land on Apply Spirit Master Card Page",
                getDriver().getCurrentUrl(),BOFA_URL);

        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);

    }
}