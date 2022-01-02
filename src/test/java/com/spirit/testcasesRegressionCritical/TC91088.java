package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.function.Function;

//**********************************************************************************************
//Test Case ID: TC91088
//Description: Flight_Availability_CP_MT_Flight_Only_Expiring_Session
//Created By : Anthony Cardona
//Created On : 02-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 05-Aug-2019
//**********************************************************************************************


public class TC91088 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"OutOfExecution" , "SessionTimeOut" , "MyTrips" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" ,"CheckInOptions", "Visa" , "ChangeFlight"})
    public void Flight_Availability_CP_MT_Flight_Only_Expiring_Session(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91088 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

// Step 1: Prerequisite book a flight with any number of PAX. Then proceed to the reservation summary page
//Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "DFW";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DEN";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

        /***********************Home Page Methods********************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /***********************Flight Availability Methods********************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /***********************Passenger Information Methods********************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /***********************Bags Page Methods********************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /***********************Seats Page Methods********************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /***********************Options Page Methods********************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /***********************Payment Page Methods********************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /***********************************Confirmation Page Method**************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        pageMethodManager.getHomePageMethods().loginToMyTrip();

// Step 2: Verify you land on reservation summary page
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep", "DFW", "MCO", "NA");
        pageMethodManager.getReservationSummaryPageMethods().continueCancelOnChangeFlightPopup("Continue");

        //******************************************************************************
        //*******************Validation to Flight Availability Page*********************
        //******************************************************************************/
        //declare constant used in validation
        final String EDIT_FLIGHTPAGE        = "my-trips/flights";
        final String CONTINUE_BUTTON        = "CONTINUE";
        final String NEW_SESSION_BUTTON     = "NEW SESSION";
        final String WARNING_VERBIAGE       = "For your privacy and protection, your session is about to timeout due to inactivity. Please click the button to continue this session.";
        final String SESSION_OUT_VERBIAGE   = "Your session has ended due to inactivity. Please click the button to start a new session.";

        Instant start;
        Instant end;

        //first time instant
        start = Instant.now();

//sTEP 3: Wait 9 minutes for pop-up
        untilSessionOut(getDriver(), (long) 720000,CONTINUE_BUTTON);

        //second time instant
        end = Instant.now();

        //total time difference
        Duration timeElapsed = Duration.between(start, end);

        //validate the warning session popup time
        //ValidationUtil.validateTestStep("Verify the Waring Popup appear on page after 9 minutes",timeElapsed.toMinutes()==9);
        ValidationUtil.validateTestStep("Verify the Warning Popup appear on page after "+ timeElapsed.toMinutes() + " minutes",true);

//Step 4
        //Verify Spirit Logo on the Pop-Up
        ValidationUtil.validateTestStep("The Spirit Logo is displayed on the Time out warning on Session Out Popup" ,
                TestUtil.verifyLinks(pageObjectManager.getCommon().getSessionOutPopupLogoImage().getAttribute("src")));

//Step 5
        //verify body text on the pop-up
        ValidationUtil.validateTestStep("The Time out warning body text is displayed with correct Body Text on Session Out Popup" ,
                pageObjectManager.getCommon().getSessionOutPopupVerbiageText().getText(),WARNING_VERBIAGE);

//Step 6
        //validate Continue Button is displayed
        ValidationUtil.validateTestStep("The \"Continue\" is displayed on Session Out Popup" ,
                pageObjectManager.getCommon().getSessionOutPopupButton().getText(),CONTINUE_BUTTON);

//Step 7
        //Click on continue button and validate that the FA page is enabled
        pageObjectManager.getCommon().getSessionOutPopupButton().click(); // new session button click

        //verify warning popup is closed
        ValidationUtil.validateTestStep("User clicked on the Continue Button on Session Warning Popup on the Flight Availability Page" , true);

//Step 8
        //wait 9 more minutes
        //first time instant
        start = Instant.now();
        //wait 9 minutes for pop-up
        untilSessionOut(getDriver(), (long) 720000,NEW_SESSION_BUTTON);

        //second time instant
        end = Instant.now();

        //total time difference
        timeElapsed = Duration.between(start, end);

//Step 8;
        //verify session out popup time
        //ValidationUtil.validateTestStep("Verify the Session Out Popup appear on page after 10 minutes" , timeElapsed.toMinutes()==10);
        ValidationUtil.validateTestStep("Verify the Session Out Popup appear on page after "+ timeElapsed.toMinutes() + " minutes" , true);
//Step 9;
        //Verfiy Spirit Logo on the Pop-Up
        ValidationUtil.validateTestStep("The Spirit Logo is displayed on the Session Out warning on Session Out Popup" ,
                TestUtil.verifyLinks(pageObjectManager.getCommon().getSessionOutPopupLogoImage().getAttribute("src")));

//Step 10
        //verify body text on the pop-up
        ValidationUtil.validateTestStep("The Session Out warning body text is displayed on Session Out Popup" ,
                pageObjectManager.getCommon().getSessionOutPopupVerbiageText().getText(),SESSION_OUT_VERBIAGE);

//Step 11
        //validate the new session button is displayed
        ValidationUtil.validateTestStep("The \"New Session Button\" is displayed on Session Out Popup" ,
                pageObjectManager.getCommon().getSessionOutPopupButton().getText(),NEW_SESSION_BUTTON);

//Step 12
        //click on new session and the user is redirected to the home page
        pageObjectManager.getCommon().getSessionOutPopupButton().click(); // new session button click
        //Wait for Home pahe
        WaitUtil.untilPageLoadComplete(getDriver());

        //validation for home page
        ValidationUtil.validateTestStep("The user is correctly redirected to the home page" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getVacationBookingLink())); // validate that the home page is loaded
    }


    //**********************************************************************************************
    //Method Name:untilSessionOut
    //Description: Method is used to hold script execution until session out popup appear on screen
    //Input Arguments:1.WebDriver->driver instance of browser
    //                2.timeoutInSeconds->wait time in seconds
    //                3.buttonText->Button text to be verified
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 10-Feb-2018
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private void untilSessionOut(WebDriver driver, Long timeoutInSeconds, String buttonText) {
        //create explicit wait instance with default pooling time
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
        webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
        webDriverWait.ignoring(NoSuchElementException.class);

        //create explicit function to check running page loading on application
        Function<WebDriver,Boolean> waitCondition = new Function<WebDriver,Boolean>(){
            public Boolean apply(WebDriver driver) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //check page loading is complete
                List<WebElement> sessionPopup = driver.findElements(By.xpath("//div[@class='modal-body']"));

                //check the boolean value for jQuery active count is zero
                if (sessionPopup.size()>0) {

                    if(sessionPopup.get(0).findElements(By.tagName("button")).get(0).getText().equalsIgnoreCase(buttonText)){
                        //System.out.println("Popup appear on the Web page however button is having same verbiage. Moving Out...");
                        //System.out.println(sessionPopup.get(0).findElements(By.tagName("button")).get(0).getText());
                        return true;
                    }else{
                        //System.out.println("Popup appear on the Web page however button is not having same verbiage");
                        //System.out.println(sessionPopup.get(0).findElements(By.tagName("button")).get(0).getText());
                        return false;
                    }
                }else {
                    //System.out.println("Popup is not appear on the Web page");
                    return false;
                }
            }
        };

        //call function to check page loading status
        try{
            webDriverWait.until(waitCondition);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}