package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.enums.Context;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.*;
import java.util.*;


//**********************************************************************************************
//TODO:  Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
//Test CaseID: TC91184
//Title      : Flight Availability_CP_BP_Successful Login to use MILES_Change Flight
//Description: Validating that a user is unable to select flight without having enough miles
//Created By : Alex Rodriguez
//Created On : 18-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-Apr-2019
//**********************************************************************************************
public class TC91184 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "Oneway" , "Miles" , "DomesticDomestic" , "Within21Days" , "Adult" , "FreeSpirit" ,
                    "NonStop" , "BookIt" , "FlightAvailabilityUI", "ActiveBug"})

    public void Flight_Availability_CP_BP_Successful_Login_to_use_MILES_Change_Flight(@Optional("NA") String platform) {
        //mention the browser
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91184 under SMOKE suite on " + platform + " Browser", true);
        }


        //Home Page Constant Values
        final String LANGUAGE = "English";

        //Flight Availability Page Constant Values
        final String LOGIN_ACCOUNT      = "FiveKMiles";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ATL";
        final String DEP_DATE 			= "7";
        final String ARR_DATE 			= "8";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Passenger Info Page Constant Values
        final String PAX_INFO_URL       = "book/passenger";


        //open browser
        openBrowser(platform);

//Step_ 1,2,3, 4
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Flight Availability Methods
        //User switches from dollars to miles
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();

        //User signs into the Log-In modal with No miles User
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(LOGIN_ACCOUNT);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(loginCredentialsData.password);
        pageObjectManager.getHomePage().getLoginButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        selectMilesFlight("25000");
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();


        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User receives insufficient miles notification with option to change flight",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getInsufficientMilesPopUpChangeFlightButton()));

        pageObjectManager.getFlightAvailabilityPage().getInsufficientMilesPopUpChangeFlightButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);

        selectMilesFlight("10000");

        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();

        //User selects Book it option and navigates to Passenger Info page
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //validate user reached to the Bags page
        ValidationUtil.validateTestStep("User able to continue when account contains sufficient miles",
                getDriver().getCurrentUrl(),PAX_INFO_URL);

    }

    private void selectMilesFlight(String requiredMiles){
        //declare variable used in method
        boolean selectedFlightFlag = false;

        WaitUtil.untilPageLoadComplete(getDriver());

        List<WebElement> allFlightRows = null;

        //loop though complete month
        for(int calenderCounter=0;calenderCounter<3;calenderCounter++){

            //*****************************************************************
            //*************************Flight Week Calendar********************
            //*****************************************************************
            //declare variable used in method
            int calenderPrices = 0;
            Date journeyDate = null;
            boolean flightFound = false;
            boolean milesAvailable = false;
            List<WebElement> calenderBlock = null;
            WebElement CalenderPlane = null;

            //assign dep flight calendar
            calenderBlock = pageObjectManager.getFlightAvailabilityPage().getDepCalBlocksGridView();

            //get dep date
            journeyDate = TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE), "E, MMM dd"), "E, MMM dd");


            //loop through all calendar block and check flight is available on selected date
            for(calenderPrices=0;calenderPrices<calenderBlock.size();calenderPrices++) {

                //check date block is visible. This block will handle Mobile Date block in which only 3 Date block is visible
                if (!calenderBlock.get(calenderPrices).isDisplayed()) {
                    //move to next date block if date block is not visible
                    continue;
                }

                //when flight prices are available then webelement count is 4 else 2
                List<WebElement> CalenderPrices = calenderBlock.get(calenderPrices).findElements(By.tagName("span"));

                for (WebElement milesPrices : CalenderPrices) {
                    if (milesPrices.getText().contains("miles")) {

                        if (Double.parseDouble(requiredMiles) == Double.parseDouble(milesPrices.getText().replace("miles", "").replace(",", ""))) {
                            milesAvailable = true;

                            CalenderPlane = milesPrices;
                        } else {
                            milesAvailable = false;

                            CalenderPlane = milesPrices;
                        }

                        break;
                    }
                }

                //loop through all elements of calendar block
                for (WebElement calendarDateText : CalenderPrices) {
                    if (calendarDateText.getText().contains(",")) {
                        //get the date from calendar blocks
                        Date calenderDate = TestUtil.convertStringToDate(calendarDateText.getText().trim(), "E, MMM dd");

                        if (milesAvailable) {
                            //check selected date is after journey date or same date
                            if (calenderDate.after(journeyDate) && !CalenderPlane.getCssValue("font-weight").contains("700")) {
                                //click on calendar date block
                                calendarDateText.click();

                                //wait for 3 sec
                                WaitUtil.untilTimeCompleted(3000);

                                //traveling flight found
                                flightFound = true;

                                //break from per date block
                                break;
                            } else if (calenderDate.equals(journeyDate) && CalenderPlane.getCssValue("font-weight").contains("700")) {
                                //traveling flight found
                                flightFound = true;

                                //break from per date block
                                break;
                            }
                        }
                        break;
                    }
                }//if block of calendar text data

            }//with in Date Block

            //check flight found flag is set
            if(flightFound) {
                //*****************************************************************
                //**********************Flight Availability Table******************
                //*****************************************************************
                //select dep flight rows
                allFlightRows = pageObjectManager.getFlightAvailabilityPage().getDepFlightRowsGridView();

                //loop through all flight rows
                //for(int flightRows=0;flightRows<allFlightRows.size();flightRows++){
                for(int flightRows=allFlightRows.size()-1;flightRows>=0;flightRows--){
                    //check flight is not sold out before selection
                    if(allFlightRows.get(flightRows).getText().contains("Sold Out") || allFlightRows.get(flightRows).getText().contains("Agotado")) {
                        continue;
                    }

                    //check flight is not already selected on manage Travel or check In path
                    if(allFlightRows.get(flightRows).getText().contains("Original Choice") || allFlightRows.get(flightRows).getText().contains("Elección Original")) {
                        continue;
                    }

                    //get all labels in flight row
                    List<WebElement> allFareType = allFlightRows.get(flightRows).findElements(By.tagName("label"));

                    //get all radio button in flight row
                    List<WebElement> allRadioType = allFlightRows.get(flightRows).findElements(By.tagName("input"));

                    WebElement totalMiles         = allFlightRows.get(flightRows).findElement(By.tagName("span"));

                    //loop through all available flight fare
                    for(int fareCounter=0;fareCounter<allRadioType.size();fareCounter++) {
                        //check for standard flight fare
                        if(allFareType.get(fareCounter).getAttribute("for").contains("-standard-")
                                && allRadioType.get(fareCounter).isEnabled()
                                && totalMiles.getText().replace(",","").contains(requiredMiles)) {
                            //click standard flight fare
                            allFareType.get(fareCounter).click();

                            //wait for page load
                            WaitUtil.untilPageLoadComplete(getDriver());

                            //wait for 2 sec
                            WaitUtil.untilTimeCompleted(2000);

                            return;
                        }
                    }
                }//Flight Stop Count Loop
            }

            //loop through all flight next rows
            for(WebElement calNextButton : pageObjectManager.getFlightAvailabilityPage().getCalNextArrowImage()) {
                //check for displayed next row button
                if(calNextButton.isDisplayed()) {
                    //click on next week button
                    calNextButton.click();

                    //wait for page load
                    WaitUtil.untilPageLoadComplete(getDriver());

                    //Wait for 3 sec
                    WaitUtil.untilTimeCompleted(5000);

                    //break from loop
                    break;
                }
            }

        }//Calendar Date Blocks

    }
}



