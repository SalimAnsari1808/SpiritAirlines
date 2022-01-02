package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//**********************************************************************************************
//Test Case ID: TC91108
//Description: BordingPass_CP_CI_INT_Verify self tagging message for NonSelf-Tagging Airports
//Created By: Anthony Cardona
//Created On: 29-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 30-Jul-2019
//**********************************************************************************************

public class TC91108 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "ColombiaDomestic" , "WithIn7Days" , "Adult" , "FreeSpirit" , "NonStop" ,
                    "BookIt" , "CheckedBags" , "NoSeats" , "CheckInOptions" , "Visa" , "BoardingPassUI"})
    public void BordingPass_CP_CI_INT_Verify_self_tagging_message_for_NonSelf_Tagging_Airports(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91108 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "BOG";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "3";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags page constant Variables
        final String DEP_BAGS           = "Carry_0|Checked_2";
        final String BAGS_FARE          = "Standard";

        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Check In Constant Values
        final String BAG_SEAT_DONT_BUY  = "DontPurchase";

        //Boarding Pass Constant
        final String HAZMET_POPUP       = "Accept";

//-- Step 1: Start creating a booking
        //open browser
        openBrowser(platform);

        //**************************Home Page Methods*********************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
//        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();
        //***************Flight Availability Page Methods*****************************/
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //***************Passenger Information Page Methods***************************/
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //************Bags Page Method***************************************************************/
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);
        WaitUtil.untilPageLoadComplete(getDriver());

        //**************************Seats Page Methods*********************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //**********************Options Page Methods**********************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //***********************Payment Page Validations*****************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //No TG within 24 hrs
//        pageMethodManager.getPaymentPageMethods().travelGuardRecommendedPopUp();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //************************Confirmation Page Validations***********************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//Step 2: Start CheckIn
        //**************************Check In Page Methods*****************************/
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //****************************Check In Validations****************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassportPageMethods().fillPassportInformation();

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(BAG_SEAT_DONT_BUY);

        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(BAG_SEAT_DONT_BUY);

        WaitUtil.untilPageLoadComplete(getDriver());

        try {
            pageObjectManager.getReservationSummaryPage().getRentACarNoThanksButton().click();
        }
        catch (Exception carPopUp)
        {}

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //accept hazmat popup
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(HAZMET_POPUP);
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup("Print","NA");

        TestUtil.closeBoardingPassPrintPopup();

        WaitUtil.untilPageLoadComplete(getDriver());
        //***********************Validation to Boarding Page***************************/

//Step 4 and 5: Validate user lands on the boarding pass page
        //declare constant used in Validation
        final String BOARDING_PASS_URL = "check-in/boarding-pass";

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
                getDriver().getCurrentUrl(), BOARDING_PASS_URL);


//Step 6: Validate boarding pass is divided into 4
        ValidationUtil.validateTestStep("The boarding pass page divided in 4",
                pageObjectManager.getBoardingPassPage().getBoardingPassSectionPanel().size() == 4);


//Step 7: Validate the Self Bag Tag airport verbiage is correct
        String bagDropExpectedTime = getDesiredTime(pageObjectManager.getBoardingPassPage().getDepartsCityTimeText().get(0).getText().trim() , -1 , 0, "HH:mm");

        ValidationUtil.validateTestStep("Self Bag Tag time is correct" , "Please arrive to Bag Drop before " + bagDropExpectedTime + " to check baggage." ,
                pageObjectManager.getBoardingPassPage().getSelfBagTagText().get(0).getText().trim());

//Step 8: Validate top left quadrant

        //wait till 4 sec
        WaitUtil.untilTimeCompleted(4000);

        //CheckIn Seat Page
        final String WELCOME_ABOARD     = "Welcome Aboard";
        final String YOUR_BOARDINGPASS  = "This is your";

        //validate Welcome verbiage is displaying correct
        ValidationUtil.validateTestStep("Welcome verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getWelcomeHeaderText().get(0).getText(), WELCOME_ABOARD);

        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("FSMember");

        ValidationUtil.validateTestStep("Name on top left quadrant is correct",
                pageObjectManager.getBoardingPassPage().getUserNameText().get(0).getText().contains(passengerInfoData.firstName.toUpperCase())
                        && pageObjectManager.getBoardingPassPage().getUserNameText().get(0).getText().contains(passengerInfoData.lastName.toUpperCase()));
        ValidationUtil.validateTestStep("'This is your boarding Pass' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getYourBoardingPassText().get(0).getText(), YOUR_BOARDINGPASS);

        final String FLIGHTDATE             = TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString(), "ddMMMYYYY");
        final String PRINTED_DATE           = TestUtil.getStringDateFormat( "0" , "MM-dd-YYYY");
        final String DEPART_CITY_CODE       = "BOG";
        final String DEPART_CITY            = "Bogota, Colombia";
        final String ARRIVE_CITY_CODE       = "FLL";
        final String ARRIVE_CITY            = "Fort Lauderdale, FL";
        final String NUMBER_CARRY_ON        = "0";
        final String CARRY_ON_TEXT          = "** NO CARRY-ON BAG **";
        final String NUMBER_CHECKED_ON      = "2";
        final String CHECKED_BAG_TEXT       = "CHECKED BAGS";

        final String SPIRIT_LOGO_IMAGE_ALT  = "Spirit Logo";
        final String BOARDING_PASS_TEXT     = "BOARDING PASS";
        final String DATE_TEXT              = "DATE";
        final String FROM_TEXT              = "FROM:";
        final String TO_TEXT                = "TO:";
        final String SEQ_TEXT               = "Seq #";
        final String CONFIRMATION_TEXT      = "CONFIRMATION";
        final String FLIGHT_TEXT            = "FLIGHT";
        final String GATE_TEXT              = "GATE";
        final String CHECK_SCREEN_TEXT      = "CHECK SCREEN";
        final String BOARDING_TIME_TEXT     = "BOARDING TIME";
        final String DOOR_CLOSED            = "DOORS CLOSE 15 MINUTES PRIOR TO DEPARTURE.";
        final String ZONE                   = "ZONE";
        final String SEAT                   = "SEAT";
        final String DEPARTS                = "DEPARTS";
        final String ARRIVE                 = "ARRIVE";
        final String ISSUED                 = "ISSUED";


//Step 9: validate 9dfc image
        //validate '$9 Image' is displaying correct
        ValidationUtil.validateTestStep("'$9 Image' is displaying correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getFareClubSavingImage()) );

//Step 10: validate the lower left quadrant
        //validate 'Spirit' Logo is displaying correct
        ValidationUtil.validateTestStep("'Spirit' Logo is displaying correct",
                pageObjectManager.getBoardingPassPage().getSpiritLogoImage().get(0).getAttribute("alt"), SPIRIT_LOGO_IMAGE_ALT);

        //validate 'Boarding Pass' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Boarding Pass' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getBoardingPassHeader().get(0).getText(), BOARDING_PASS_TEXT);

        //validate 'Date' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Date' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getDate().get(0).getText().trim(), DATE_TEXT);

        ValidationUtil.validateTestStep("Flight Date is displaying correct",
                pageObjectManager.getBoardingPassPage().getFlightDate().get(0).getText().replace("DATE","").trim(), FLIGHTDATE);

        ValidationUtil.validateTestStep("Name on top left quadrant is correct",
                pageObjectManager.getBoardingPassPage().getUserNameLeftBottomText().get(0).getText().contains(passengerInfoData.firstName.toUpperCase())
                        && pageObjectManager.getBoardingPassPage().getUserNameLeftBottomText().get(0).getText().contains(passengerInfoData.lastName.toUpperCase()));

        //validate 'FROM' verbiage is displaying correct
        ValidationUtil.validateTestStep("'FROM' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getFromText().get(0).getText().toUpperCase().trim(), FROM_TEXT);
        ValidationUtil.validateTestStep("'FROM' city code is correct",
                pageObjectManager.getBoardingPassPage().getFromCityCodeText().get(0).getText().toUpperCase().trim(), DEPART_CITY_CODE);
        ValidationUtil.validateTestStep("'FROM' city name is correct",
                pageObjectManager.getBoardingPassPage().getFromCityNameText().get(0).getText().toUpperCase().trim(), DEPART_CITY);

        //validate 'To' verbiage is displaying correct
        ValidationUtil.validateTestStep("'TO' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getToText().get(0).getText().toUpperCase().trim(), TO_TEXT);
        ValidationUtil.validateTestStep("'TO' city code is correct",
                pageObjectManager.getBoardingPassPage().getToCityCode().get(0).getText().toUpperCase().trim(), ARRIVE_CITY_CODE);
        ValidationUtil.validateTestStep("'TO' city name is correct",
                pageObjectManager.getBoardingPassPage().getToCityName().get(0).getText().toUpperCase().trim(), ARRIVE_CITY);

        //validate 'Bags' verbiage is displaying correct
        ValidationUtil.validateTestStep("Number of carry on is displaying correct",
                pageObjectManager.getBoardingPassPage().getNumberOfCarryon().get(0).getText(), NUMBER_CARRY_ON);
        ValidationUtil.validateTestStep("'No Carry On' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getCarryOnText().get(0).getText(), CARRY_ON_TEXT);

        ValidationUtil.validateTestStep("Number of carry on is displaying correct",
                pageObjectManager.getBoardingPassPage().getNumberCheckedBagText().get(0).getText(), NUMBER_CHECKED_ON);
        ValidationUtil.validateTestStep("'No Carry On' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getCheckedBagText().get(0).getText(), CHECKED_BAG_TEXT);

        //validate 'SEQ' verbiage is displaying correct
        ValidationUtil.validateTestStep("'SEQ' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getSeqText().get(0).getText(), SEQ_TEXT);
        System.out.println("SEQ_TEXT = " + SEQ_TEXT);
        System.out.println("Whole text = " + pageObjectManager.getBoardingPassPage().getSeqNumberText().get(0).getText());
        // 4 + because it includes the space, checking the number has 3 digits
        ValidationUtil.validateTestStep("'SEQ' Number is the correct length",
                pageObjectManager.getBoardingPassPage().getSeqNumberText().get(0).getText().length() == 4 + SEQ_TEXT.length());

        //validate 'Confirmation' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Confirmation' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getConfirmationText().get(0).getText().toUpperCase().trim(), CONFIRMATION_TEXT);
        ValidationUtil.validateTestStep("'Confirmation-Code' is displaying correct",
                pageObjectManager.getBoardingPassPage().getConfirmationCode().get(0).getText().trim(),
                scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        ValidationUtil.validateTestStep("The Bar Code is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getBoardingPassImage()));

//Step--11
        //validate that all infromation at the botton right of the boarding pass is correct
        //validate 'Flight' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Flight' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getFlightHeaderText().get(0).getText().trim(), FLIGHT_TEXT);
        ValidationUtil.validateTestStep("'Flight' verbiage is displaying correct",
                Integer.parseInt(pageObjectManager.getBoardingPassPage().getFlightNumberText().get(0).getText()) >= 100 );

        //validate 'GATE' verbiage is displaying correct
        ValidationUtil.validateTestStep("'GATE' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getGateText().get(0).getText().toUpperCase().trim(), GATE_TEXT);

        //validate 'CHECK SCREEN' verbiage is displaying correct
        ValidationUtil.validateTestStep("'CHECK SCREEN' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getCheckScreensText().get(0).getText().toUpperCase().trim(), CHECK_SCREEN_TEXT);

        //validate 'BOARDING TIME' verbiage is displaying correct
        ValidationUtil.validateTestStep("'BOARDING TIME' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getBoardingTimeText().get(0).getText().trim(), BOARDING_TIME_TEXT);
        ValidationUtil.validateTestStep("'BOARDING TIME' verbiage is displaying correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getBoardingActualTimeText()));
        ValidationUtil.validateTestStep("'BOARDING TIME' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getDoorCloseText().get(0).getText().trim(), DOOR_CLOSED);

        //validate 'ZONE' verbiage is displaying correct
        ValidationUtil.validateTestStep("'ZONE' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getZoneText().get(0).getText().trim(), ZONE);
        ValidationUtil.validateTestStep("'ZONE' verbiage is displaying correct",
                Integer.parseInt(pageObjectManager.getBoardingPassPage().getZoneNumberText().get(0).getText().trim()) > 1 );

        //validate 'SEAT' verbiage is displaying correct
        ValidationUtil.validateTestStep("'SEAT' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getSeatText().get(0).getText().trim(), SEAT);
        ValidationUtil.validateTestStep("'SEAT' verbiage is displaying correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getSeatNumberText()));

        //validate 'DEPART' verbiage is displaying correct
        ValidationUtil.validateTestStep("'DEPART' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getDepartsText().get(0).getText().trim(), DEPARTS);
        ValidationUtil.validateTestStep("Depart city name is displaying correct",
                pageObjectManager.getBoardingPassPage().getDepartsCityNameText().get(0).getText().trim(), DEPART_CITY);
        ValidationUtil.validateTestStep("Depart Time is displaying correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getArriveCityTimeText()));

        //validate 'ARRIVE' verbiage is displaying correct
        ValidationUtil.validateTestStep("'ARRIVE' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getArriveText().get(0).getText().toUpperCase().trim(), ARRIVE);
        ValidationUtil.validateTestStep("Depart City name is displaying correct",
                pageObjectManager.getBoardingPassPage().getArriveCityNameText().get(0).getText().toUpperCase().trim(), ARRIVE_CITY);
        ValidationUtil.validateTestStep("Depart Time is displaying correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getDepartsCityNameText()));

        //validate 'ISSUED' verbiage is displaying correct
        ValidationUtil.validateTestStep("'ISSUED' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getIssuedText().get(0).getText().trim(), ISSUED);
        ValidationUtil.validateTestStep("Issued date is correct",
                pageObjectManager.getBoardingPassPage().getIssuedTimeText().get(0).getText().trim(),PRINTED_DATE);

//STEP--12
        //********************************************SKYSPEED***********************************************
    }

    /**
     * @param boardingTime String from boarding pass that contains the HH:mm a time
     * @param hourIncrementor Amount of hours to increment the boarding time
     * @param minIncrementor Amount of minutes to increment the boarding time
     * @param dateFormat date format for the return value (ex: HH:mm a)
     * @return the expected time from paramaters pushed
     */
    private String getDesiredTime(String boardingTime, int hourIncrementor, int minIncrementor, String dateFormat) {

        //create calendar instance
        Calendar calendar = Calendar.getInstance();

//        System.out.println(boardingTime.substring(0 , boardingTime.indexOf( ":")));
//        System.out.println(boardingTime.substring(boardingTime.indexOf(":")+1, boardingTime.indexOf(":")+ 3));

        int hour = Integer.parseInt(boardingTime.substring(0 , boardingTime.indexOf( ":")));
        int min = Integer.parseInt(boardingTime.substring(boardingTime.indexOf(":")+1, boardingTime.indexOf(":")+ 3));

        calendar.set(Calendar.HOUR_OF_DAY , hour);
        calendar.set(Calendar.MINUTE ,  min);

        //Increment calendar date to get departure date
        calendar.add(Calendar.HOUR_OF_DAY, hourIncrementor);
        calendar.add(Calendar.MINUTE, minIncrementor);

        //get date from calendar in correct format
        Date date = calendar.getTime();

        //set format in which date is required
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        String time = dateFormatter.format(date);

        if( time.charAt(0) == '0') {
            time = time.substring(1);
        }

        if (boardingTime.contains("AM")) {
            time = time + "AM";
        }
        else {
            time = time + "PM";
        }

        //convert date in required format
        return  time;
    }
}