package com.spirit.skySpeedTestCases;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.CardDetailsData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;
import java.util.List;

public class firstTest extends BaseClass {

    @Parameters({"Platform"})
    @Test()
    public void Generate_Overbook_ValidatePopUp(@Optional("NA") String platform) {
        //Home Page Constant Values
        final String LANGUAGE = "English";
        final String JOURNEY_TYPE = "Flight";
        final String TRIP_TYPE = "OneWay";
        final String DEP_AIRPORTS = "AllLocation";
        final String DEP_AIRPORT_CODE = "LAS";
        final String ARR_AIRPORTS = "AllLocation";
        final String ARR_AIRPORT_CODE = "DEN";
        final String DEP_DATE = "0";
        final String ARR_DATE = "NA";
        final String ADULTS = "1";
        final String CHILD = "0";
        final String INFANT_LAP = "0";
        final String INFANT_SEAT = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT_NUMBER = "906";
        final String FARE_TYPE = "Standard";
        final String UPGRADE_VALUE = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String TRAVEL_GUARD = "NotRequired";
        final String CARD_TYPE = "MasterCard";


        //open browser
        openBrowser(platform);
//STEP--1
//        //Home Page Methods
//        pageMethodManager.getHomePageMethods().launchSpiritApp();
//        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
//        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
//        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
//        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
//        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
//        pageMethodManager.getHomePageMethods().clickSearchButton();
//
//        //Flight Availability Methods
//        pageMethodManager.getFlightAvailabilityMethods().selectFlightNumberType("Dep", DEP_FLIGHT_NUMBER);
//        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
//        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//
//        //Passenger Info Methods
//        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
//        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
//        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//
//        //Bags page
//        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
//
//        //Seats Page Methods
//        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
//
//        //Options page
//        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
//        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//
//        //Payment page
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//
//        //Confirmation page closing ROKT Popup
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        //Coping Confirmation Code for retrieve on CP
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        getDriver().close();
//
////////////Start SkySpeed portion of test
        try {openWindowApplication("NA"); } catch (Exception e){}

        windowMethodManager.getSkySpeedLogInPageMethods().loginToSkySpeedApplication("GRPAgent");
//        getToHomePage();

        boolean loopThroughFare = true;

        while (loopThroughFare) {
            //Start Booking

            WaitUtil.untilTimeCompleted(2000);
            windowMethodManager.getSkySpeedHomePageMethods().selectFromToAirports("OneWay" , DEP_AIRPORT_CODE , ARR_AIRPORT_CODE);
            windowMethodManager.getSkySpeedHomePageMethods().selectJourneyDate(DEP_DATE , ARR_DATE);
            windowMethodManager.getSkySpeedHomePageMethods().clickNextHomePage();

            //desired flight number (future state: read from excel spreadsheet)
//                flightNumber = flightNumber.replace("NK","").trim();
//            String flightNumber = "777";

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

//                WebElement recordLocator = getDriver().findElement(By.id("_textboxReservationValue"));

//                System.out.println("PNR: " + recordLocator.getText());

            //if size of map is less than or equal to 1 and there were more than 100 fares stop loop
            if ((fareLoads.size() <= 1) && !switchedsizeto100) loopThroughFare = false; //break;
                //else keep looping
            else {
                getDriver().findElement(By.name("Start")).click();
                WaitUtil.untilTimeCompleted(2000);
                getDriver().findElement(By.name("Yes")).click();
                WaitUtil.untilTimeCompleted(2000);
            }
        }

        /*********************************************Start OF CheckIn Path**********************/
        //login to checkIn Path
        openBrowser(platform);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().loginToCheckIn();

        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        WaitUtil.untilTimeCompleted(20000);
        pageMethodManager.getReservationSummaryPageMethods().selectBidOption("Bid3");
        pageMethodManager.getReservationSummaryPageMethods().submitBid();
    }

//    public void searchCityAndDate(String from, String to , String DEP_DATE)
//    {
//        windowObjectManager.getSkySpeedHomePage().getFromgetFromCityTextBox().sendKeys(from);//From
//        windowObjectManager.getSkySpeedHomePage().getToCityTextBox().sendKeys(to);//To
//        System.out.println("User selects origin and destination");
//
//        String date= TestUtil.getStringDateFormat(DEP_DATE , "dd MM YYYY");//Convert String to date
//
//        windowObjectManager.getSkySpeedHomePage().getFromDateTextBox().sendKeys(date);
//
//        windowObjectManager.getSkySpeedHomePage().getNextButton().click();
//        WaitUtil.untilTimeCompleted(2000);
//    }
    public void passengerPage()
    {
        //Passenger Information page
        //select group booking so you wont need to fill all pax information
        windowObjectManager.getSkySpeedPassengerPage().getGroupBookingCheckBox().click();
        windowObjectManager.getSkySpeedPassengerPage().getPassengerLastNameTextBox().sendKeys("Flyer");
        windowObjectManager.getSkySpeedPassengerPage().getPassengerFirstNameTextBox().sendKeys("Joe");
        windowObjectManager.getSkySpeedPassengerPage().getPassengerDOBTextBox().sendKeys("01/01/1980" + Keys.TAB);
        WaitUtil.untilTimeCompleted(4000);
        //Click next to continue from passenger page
        getDriver().findElement(By.name("Next >")).click();
        WaitUtil.untilTimeCompleted(4000);
        //click next on the passenger review page
        getDriver().findElement(By.name("Next >")).click();

        //Fill contact page
        WaitUtil.untilTimeCompleted(4000);

        windowObjectManager.getSkySpeedContactsPage().getPhoneHomeTextBox().sendKeys("7865555555");
        //Click Next on the contacts page
        getDriver().findElement(By.name("Next >")).click();
        WaitUtil.untilTimeCompleted(4000);

        //Seat Assignment page
        //Click Next on the seat assignment page
        getDriver().findElement(By.name("Next >")).click();
        WaitUtil.untilTimeCompleted(4000);
        getDriver().findElement(By.name("Next >")).click();
    }

    public void paymentPage()
    {
        WaitUtil.untilTimeCompleted(4000);
        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType("MasterCard");

        windowObjectManager.getSkySpeedPaymentPage().getReservationAccountTypeDropDown().sendKeys("MC" + Keys.TAB);
        WaitUtil.untilTimeCompleted(1000);

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
        WaitUtil.untilTimeCompleted(2000);
        windowObjectManager.getSkySpeedPaymentPage().getNextButton().click();
        WaitUtil.untilTimeCompleted(1000);
    }

    /**
     * This method is used to select the argument flight number

     */
    private Map<String , String> returnFlightAvailability(String flightNumber)
    {
        List<WebElement> listOfFlights  = windowObjectManager.getSkySpeedFlightAvailabilityPage().getListOfOutboundFlightListText();
        System.out.println(listOfFlights.size());
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
    public void logInToSkyspeed()
    {
        String user = "grpagent";
        String password = "Spirit1";

        windowObjectManager.getSkySpeedLogInPage().getUserNameTextBox().sendKeys(user);
        windowObjectManager.getSkySpeedLogInPage().getPasswordTextBox().sendKeys(password);
        windowObjectManager.getSkySpeedLogInPage().getLoginButton().click();

        //Wait 4 seconds after clicking okay on LogIn modal
        WaitUtil.untilTimeCompleted(1000);
        TestUtil.switchToWindow(getDriver(),0); //switches user to main window
    }

    private  void getToHomePage()
    {
        WaitUtil.untilTimeCompleted(5500);
        List<WebElement> close = getDriver().findElements(By.name("Close"));
        close.get(1).click();//click on bottom close button

        WaitUtil.untilTimeCompleted(1000);
        getDriver().findElement(By.name("Start")).click();//click on bottom Start button

        WaitUtil.untilTimeCompleted(1000);
    }

    private void inputPassengerNumberOnPassengerPopUp(int chooseableFare)
    {
        windowObjectManager.getSkySpeedFlightAvailabilityPage().getPassengerInputTextBox().sendKeys(Integer.toString(chooseableFare));

        //Click okay on the Number of passenger PopUp
        windowObjectManager.getSkySpeedFlightAvailabilityPage().getPassengerOkayButton().click();
        WaitUtil.untilTimeCompleted(2000);
    }

    private void continueToPassengerPage()
    {
        //Click Next to proceed from the flight result page
        windowObjectManager.getSkySpeedFlightAvailabilityPage().getNextButton().click();
        WaitUtil.untilTimeCompleted(2000);

        //Click Next to proceed from the Reserved Flight page
        windowObjectManager.getSkySpeedFlightAvailabilityPage().getNextButton().click();
        WaitUtil.untilTimeCompleted(3000);
    }

    private void endRecord()
    {
        //Mandatory Comments page
        windowObjectManager.getSkySpeedCommentsPage().getCommentNextButton().click();
        WaitUtil.untilTimeCompleted(2000);

        //Comments page
        WaitUtil.untilTimeCompleted(2000);
        windowObjectManager.getSkySpeedCommentsPage().getCommentNextButton().click();

        //End Record Page
        windowObjectManager.getSkySpeedCommentsPage().getCommentNextButton().click();
        WaitUtil.untilTimeCompleted(2000);
    }

    private void handleCallerPopUp()
    {
        //caller Pop-Up'
        windowObjectManager.getSkySpeedMiscellaneousPage().getCallerInformationNameTextBox().sendKeys("Automation");
        windowObjectManager.getSkySpeedMiscellaneousPage().getCallerInformationNumberTextBox().sendKeys("123456");
        windowObjectManager.getSkySpeedMiscellaneousPage().getCallerInformationOkButton().click();

        WaitUtil.untilTimeCompleted(5000);
    }
}