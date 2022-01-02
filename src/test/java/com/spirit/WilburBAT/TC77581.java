package com.spirit.WilburBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.CardDetailsData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.xml.stream.events.Comment;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//**********************************************************************************************
//Test Case ID: TC77581
//Description: Market Tier Matrix- Tier 1 A319-Bid 1- DOM-DOM
//Created By : Anthony Cardona
//Created On : 27-Jan-2020
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC77581 extends BaseClass {

    //Home Page Constant Values
    final String LANGUAGE 		    = "English";
    final String JOURNEY_TYPE       = "Flight";
    final String TRIP_TYPE 		    = "RoundTrip";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "LAS";
    final String ARR_AIRPORTS 		= "AllLocation";
    final String ARR_AIRPORT_CODE   = "LAX";
    final String DEP_DATE 		    = "0";
    final String ARR_DATE 	        = "1";
    final String ADULTS	            = "1";
    final String CHILDREN	        = "0";
    final String INFANT_LAP		    = "0";
    final String INFANT_SEAT        = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT 	    = "719";
    final String ARR_Flight 	    = "NonStop";
    final String FARE_TYPE		    = "Standard";
    final String UPGRADE_VALUE		= "BookIt";

    //Option Page Constant Values
    final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

    //Payment page Constant values
    final String TRAVEL_GUARD		= "NotRequired";
    final String CARD_TYPE			= "MasterCard";

    //Confirmation Page Constant value
    final String BOOKING_STATUS     = "Confirmed";


    @Parameters({"platform"})
    @Test(groups = {""})
    public void Market_Tier_Matrix_Tier_1_A319_Bid_1_DOM_DOM(@Optional("NA") String platform) throws MalformedURLException{
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC77581 under WilburBAT Suite on " + platform + " Browser", true);
        }

//Step 1 - 5 create booking
        //open browser
//        openBrowser( platform);
//
//        //Home Page Methods
//        pageMethodManager.getHomePageMethods().launchSpiritApp();
//        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
//        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
//        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
//        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
//        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
//        pageMethodManager.getHomePageMethods().clickSearchButton();
//
//        //Flight Availability Methods
//        pageMethodManager.getFlightAvailabilityMethods().selectFlightNumberType("Dep", DEP_FLIGHT);
//        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
//        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
//        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//
//
//        //Passenger Info Methods
//        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
//        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
//        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//
//        //Bags page
//        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        //Seats Page
//        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
//
//        //Option Page Methods
//        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
//        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//
//        //Payment page
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();



////Step 6 -11  - Over Book Flight
////        overBookFlight(platform);
//
//        openBrowser( platform);
//        scenarioContext.setContext(Context.CONFIRMATION_LASTNAME, "TEST");
//        scenarioContext.setContext(Context.CONFIRMATION_CODE, "AJ4K3E");
//
//        pageMethodManager.getHomePageMethods().launchSpiritApp();
//        pageMethodManager.getHomePageMethods().loginToCheckIn();
//        WaitUtil.untilPageLoadComplete(getDriver());
//
////Step 12: Validate the pop-up
//        //Click on Check-in and get Boarding pass button
//        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
//
//        /*
//        //validation steps
//         */
//
////Step 13: Validate Bid Options
//        /*
//        //validation bid options
//         */
//
////Step 14: Select Bid option 1
//        pageMethodManager.getReservationSummaryPageMethods().selectBidOption("Bid1");
//
//        WaitUtil.untilTimeCompleted(1000);
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        pageMethodManager.getReservationSummaryPageMethods().submitBid();
//
//        WaitUtil.untilPageLoadComplete(getDriver());
//        System.out.println(pageObjectManager.getReservationSummaryPage().getBidAcceptedCloseButton().isDisplayed());
//        pageObjectManager.getReservationSummaryPage().getBidAcceptedCloseButton().click();
//
//        WaitUtil.untilTimeCompleted(1000);
//        WaitUtil.untilPageLoadComplete(getDriver());
//
////Step 15: Complete the check in process and retrieve boarding pass
//        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup("DontPurchase");
//        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup("DontPurchase");
//        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup("DontPurchase");
//        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup("NotRequired");
//        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");
//        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup("Print","NA");
//        WaitUtil.untilPageLoadComplete(getDriver());


//Step 16: Go to skyspeed and validate that the VOL SSR is added
//        openWindowApplication(platform);
//
//        WaitUtil.untilTimeCompleted(10000);
//        windowMethodManager.getSkySpeedLogInPageMethods().loginToSkySpeedApplication("APAgent");
//        try {//create instance of robot class
//            java.awt.Robot robot = new java.awt.Robot();
//            robot.keyPress(KeyEvent.VK_F10);
//        }
//        catch (Exception e ){}
//
//        getDriver().findElement(By.xpath("//Edit[@Name = 'Reservation Number:']")).sendKeys( "AJ4K3E");
//        getDriver().findElement(By.xpath("//Button[@Name = 'Search']")).click();
//        waitUntilLoaded();
//
//        List<WebElement> comments = getDriver().findElements(By.xpath("//Pane[@Name='Comments']//ListItem"));
//        ValidationUtil.validateTestStep("The Volunteer Comment was added to the booking", comments.get(1).getText() , "Volunteer Bid (VOL): $100.00");




        //invalid step, user will not see this comment or be placed onto the queue until the bid is accepted on the DBT
//Step 17: Validate the Comments "RETURN QUE" Is active & the correct bid amount is listed in the comment section. Format: [ VOL BID ADDED] - LASTNAME/FIRSTNAME - [$###] - FLT #### XXX-XXX DD/MMM/YY]
        /*
         */

//Step 18: Open the Denied boarding Tool
        openBrowser(platform);
        getDriver().get("http://miadesc03:2380/#/");

        WaitUtil.untilTimeCompleted(3000);

        pageMethodManager.getDeniedBoardingToolMethods().selectCity(DEP_AIRPORT_CODE);
        pageMethodManager.getDeniedBoardingToolMethods().clickSearch();
        WaitUtil.untilTimeCompleted(3500);
        pageMethodManager.getDeniedBoardingToolMethods().selectFlightByNumber(DEP_FLIGHT);
        WaitUtil.untilTimeCompleted(10000);
        pageMethodManager.getDeniedBoardingToolMethods().selectMemberFromFlightManifest("TEST/SPIRITAUTMATION" , "AJ4K3E" );

        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getDeniedBoardingToolPage().getManifestTableBidAcceptedText().get(1).click();
        getDriver().findElement(By.id("a208376410")).click();







    }

    private void overBookFlight(String platform) throws MalformedURLException
    {
        openWindowApplication(platform);

        WaitUtil.untilTimeCompleted(5000);

        windowMethodManager.getSkySpeedLogInPageMethods().loginToSkySpeedApplication("grpAgent");

        //Home Page
        windowMethodManager.getSkySpeedHomePageMethods().selectFareType("LowFare");
        //loop through fares
        loopThroughAllFaresForFlight(DEP_AIRPORT_CODE , ARR_AIRPORT_CODE , "" , DEP_FLIGHT);
    }

    private void loopThroughAllFaresForFlight(String DEP_AIRPORT_CODE , String ARR_AIRPORT_CODE , String DEP_DATE, String DEP_FLIGHT_NUMBER) {
        boolean loopThroughFare = true;

        while (loopThroughFare) {
            //Start Booking

            waitUntilLoaded();
            windowMethodManager.getSkySpeedHomePageMethods().selectFromToAirports("Oneway", DEP_AIRPORT_CODE, ARR_AIRPORT_CODE);
            windowMethodManager.getSkySpeedHomePageMethods().clickNextHomePage();

            waitUntilLoaded();

            //return map of the available segments to choose from
            Map<String, String> fareLoads = returnFlightAvailability(DEP_FLIGHT_NUMBER);

            //get the number of chooseable fares from the map
            int chooseableFare = Integer.parseInt(fareLoads.get("1").trim()); //always get key 1 for nex available fare

            //if chooseable fare is grater than 100, select only 100 fares (The max number of people that can be selected is 100)
            boolean switchedsizeto100 = false;//stores if less than 100
            if (chooseableFare > 100) {
                chooseableFare = 100;
                switchedsizeto100 = true;
            }

            //Type number of passengers to add to the booking on the Number of passenger PopUp
            inputPassengerNumberOnPassengerPopUp(chooseableFare);

            continueToPassengerPage();

            //Input Passenger information
            passengerPage();
            paymentPage();
            endRecord();
            handleCallerPopUp();

            //if size of map is less than or equal to 1 and there were more than 100 fares stop loop
            if ((fareLoads.size() <= 1) && !switchedsizeto100) loopThroughFare = false; //break;
                //else keep looping
            else {
                getDriver().findElement(By.name("Start")).click();
                waitUntilLoaded();
                getDriver().findElement(By.name("Yes")).click();
                waitUntilLoaded();
            }
        }
    }


    private void inputPassengerNumberOnPassengerPopUp(int chooseableFare)
    {
        windowObjectManager.getSkySpeedFlightAvailabilityPage().getPassengerInputTextBox().sendKeys(Integer.toString(chooseableFare));

        //Click okay on the Number of passenger PopUp
        windowObjectManager.getSkySpeedFlightAvailabilityPage().getPassengerOkayButton().click();
        waitUntilLoaded();
    }

    private void continueToPassengerPage()
    {

        waitUntilLoaded();
        //Click Next to proceed from the flight result page
        windowObjectManager.getSkySpeedFlightAvailabilityPage().getNextButton().click();
        waitUntilLoaded();

        //Click Next to proceed from the Reserved Flight page
        windowObjectManager.getSkySpeedFlightAvailabilityPage().getNextButton().click();
        waitUntilLoaded();
    }

    private void endRecord()
    {
        //Mandatory Comments page
        windowObjectManager.getSkySpeedCommentsPage().getCommentNextButton().click();
        waitUntilLoaded();

        //Comments page
        waitUntilLoaded();
        windowObjectManager.getSkySpeedCommentsPage().getCommentNextButton().click();

        //End Record Page
        windowObjectManager.getSkySpeedCommentsPage().getCommentNextButton().click();
        waitUntilLoaded();
    }

    private void handleCallerPopUp()
    {
        //caller Pop-Up'
        windowObjectManager.getSkySpeedMiscellaneousPage().getCallerInformationNameTextBox().sendKeys("Automation");
        windowObjectManager.getSkySpeedMiscellaneousPage().getCallerInformationNumberTextBox().sendKeys("123456");
        windowObjectManager.getSkySpeedMiscellaneousPage().getCallerInformationOkButton().click();

        WaitUtil.untilTimeCompleted(5000);
    }

    private Map<String , String> returnFlightAvailability(String flightNumber)
    {
        WaitUtil.untilTimeCompleted(2000);
        List<WebElement> listOfFlights  = windowObjectManager.getSkySpeedFlightAvailabilityPage().getListOfOutboundFlightListText();
        Map<String , String> fareLoads = new HashMap<>();//map will store the fares information
        List<WebElement> newElements = new ArrayList<>();// new webElements of expanded flight train(includes fares, flight number, and segments)

        WebElement desiredFlight = null; //will store the desired flight


        //loop through flight results
        for(WebElement flight :  listOfFlights)
        {
            //if flight result contains the flight number
            if (flight.getText().contains(flightNumber))
            {
                //Double click on the flightOption for this flight number
                desiredFlight = flight;
                Actions action = new Actions(getDriver());
                action.doubleClick(flight).perform();

                //get expanded flight result information(includes fares, flight number, and segments)
                newElements = windowObjectManager.getSkySpeedFlightAvailabilityPage().getListOfOutboundFlightFaresText();
                System.out.println(newElements.size());
                newElements = flight.findElements(By.tagName("TreeItem"));
                System.out.println(newElements.size());
            }
        }

        String lid_OfFlight;
        String cap_OfFlight;
        String sold_OfFlight;
        String fareClass;
        String fareAvailable;

        int fareClassCounter = 1;
        //loop though each of the new elements
        for (WebElement information : newElements)
        {
            //get string that contains "L:" , "S:" , and "C:"
            if (information.getText().contains("L:") && information.getText().contains("S:") && information.getText().contains("C:"))
            {
                System.out.println(information.getText());
                //get the "L:"
                if (information.getText().contains("L:")) {
                    int startLengthOfLid = information.getText().indexOf("L:");
                    String strStartingOnLid = information.getText().substring(startLengthOfLid);
                    int endLengthOfLid = strStartingOnLid.indexOf(" ") + startLengthOfLid;

                    lid_OfFlight = information.getText().substring(startLengthOfLid, endLengthOfLid);
                    System.out.print(lid_OfFlight + "\t\t");
                }

                //get the "C:"
                if (information.getText().contains("C:")) {
                    int startLengthOfCap = information.getText().indexOf("C:");
                    String strStartingOnCap = information.getText().substring(startLengthOfCap);
                    int endLengthOfCap = strStartingOnCap.indexOf(" ") + startLengthOfCap;

                    cap_OfFlight = information.getText().substring(startLengthOfCap, endLengthOfCap);
                    System.out.print(cap_OfFlight + "\t\t");
                }
                //get the "S:"
                if (information.getText().contains("S:")) {
                    int startLengthOfSold = information.getText().indexOf("S:");
                    String strStartingOnSold = information.getText().substring(startLengthOfSold).trim();
                    int endLengthOfSold = strStartingOnSold.indexOf(" ") + startLengthOfSold;

                    sold_OfFlight= information.getText().substring(startLengthOfSold);
                    System.out.println(sold_OfFlight + "\t\n");

                }
            }

            //Store fare information in Map
            if (information.getText().contains("(Class "))
            {
                //get the fare class
                int startLengthOfClass = information.getText().indexOf("Class ") ;
                String strStartingOnClass = information.getText().substring(startLengthOfClass);
                int endLengthOfClass = strStartingOnClass.indexOf(")") + startLengthOfClass;
                fareClass= information.getText().substring(startLengthOfClass , endLengthOfClass);
                System.out.print(fareClass + ": ");

                /////////////////////////////////get the fare load/////////////////////////////////
                //get the fares available
                int startLengthOfAvailableFares = information.getText().indexOf("+") + 1;
                String strStartingOnAvailableFares = information.getText().substring(startLengthOfAvailableFares);
                int endLengthOfAvailableFares = strStartingOnAvailableFares.indexOf("Standard") + startLengthOfAvailableFares;
                fareAvailable = information.getText().substring(startLengthOfAvailableFares , endLengthOfAvailableFares );
                System.out.println(fareAvailable);

//                fareLoads.put(fareClass , fareAvailable);
                fareLoads.put(Integer.toString(fareClassCounter) , fareAvailable);
                fareClassCounter++;
            }
        }
        //send space bar to the desired flight
        desiredFlight.sendKeys(" ");

        return fareLoads;
    }

    public void passengerPage()
    {

        //Passenger Information page
        //select group booking so you wont need to fill all pax information
        windowObjectManager.getSkySpeedPassengerPage().getGroupBookingCheckBox().click();
        windowObjectManager.getSkySpeedPassengerPage().getPassengerLastNameTextBox().sendKeys("Flyer");
        windowObjectManager.getSkySpeedPassengerPage().getPassengerFirstNameTextBox().sendKeys("Joe");
        windowObjectManager.getSkySpeedPassengerPage().getPassengerDOBTextBox().sendKeys("01/01/1980" + Keys.TAB);
        waitUntilLoaded();
        //Click next to continue from passenger page
        getDriver().findElement(By.name("Next >")).click();
        waitUntilLoaded();
        //click next on the passenger review page
        getDriver().findElement(By.name("Next >")).click();

        //Fill contact page
        waitUntilLoaded();

        windowObjectManager.getSkySpeedContactsPage().getPhoneHomeTextBox().sendKeys("7865555555");
        //Click Next on the contacts page
        getDriver().findElement(By.name("Next >")).click();
        waitUntilLoaded();

        //Seat Assignment page
        //Click Next on the seat assignment page
        getDriver().findElement(By.name("Next >")).click();
        waitUntilLoaded();
        getDriver().findElement(By.name("Next >")).click();
    }

    public void paymentPage()
    {
        waitUntilLoaded();
        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType("MasterCard");

        windowObjectManager.getSkySpeedPaymentPage().getReservationAccountTypeDropDown().sendKeys("MC" + Keys.TAB);
        waitUntilLoaded();

        windowObjectManager.getSkySpeedPaymentPage().getAccountNumberTextBox().sendKeys(cardDetailsData.cardNumber);
        windowObjectManager.getSkySpeedPaymentPage().getCVVTextBox().sendKeys(cardDetailsData.securityCode);
        windowObjectManager.getSkySpeedPaymentPage().getAccountHolderNameTextBox().sendKeys(cardDetailsData.cardHolderName);
        windowObjectManager.getSkySpeedPaymentPage().getExpiryMonthTextBox().sendKeys("10");
        windowObjectManager.getSkySpeedPaymentPage().getExpiryYearTextBox().sendKeys("2022");
        windowObjectManager.getSkySpeedPaymentPage().getBillingAddressTextBox().sendKeys("2800 Executive Way");
        windowObjectManager.getSkySpeedPaymentPage().getPageCityTextBox().sendKeys("Miramar");
        windowObjectManager.getSkySpeedPaymentPage().getStateTextBox().sendKeys("Florida");
        windowObjectManager.getSkySpeedPaymentPage().getZipTextBox().sendKeys("33025");
        windowObjectManager.getSkySpeedPaymentPage().getExpiryMonthTextBox().sendKeys("10");
        windowObjectManager.getSkySpeedPaymentPage().getExpiryYearTextBox().sendKeys("2020");
        ValidationUtil.validateTestStep("The Credit Card information was correctly ", true);

        windowObjectManager.getSkySpeedPaymentPage().getNextButton().click();
        waitUntilLoaded();
        windowObjectManager.getSkySpeedPaymentPage().getNextButton().click();
        waitUntilLoaded();
    }

    public void waitUntilLoaded ()
    {
        WaitUtil.untilTimeCompleted(500);
        while(getDriver().findElements(By.xpath("//Text[@AutomationId='_labelPleaseWait']")).size() > 0);
        {
            System.out.println("The page is loading");
            WaitUtil.untilTimeCompleted(1000);
        }
    }
}