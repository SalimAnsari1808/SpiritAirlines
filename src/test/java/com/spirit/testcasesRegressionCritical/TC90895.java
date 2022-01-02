package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;

// **********************************************************************************************
// TestCase :TC90895
// Description: BoardingPass_CP_CI_Boarding Pass WireFrame
// Created By : Anthony Cardona
// Created On : 27-Jun-2019
// Reviewed By: Salim Ansari
// Reviewed On: 09-Jul-2019
// *********************************************************************************************
//bug 25717: Prod: CP: CI : Boarding Pass: "Bag Drop" time and "Self Bag-Tag" time is displaying the same time as boarding time
public class TC90895 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "MandatoryFields" ,"PassengerInfoKTN", "CheckedBags" , "NoSeats" , "ShortCutBoarding" , "ShortCutSecurity" , "CheckInOptions" , "MasterCard" , "BoardingPassUI"})
    public void BoardingPass_CP_CI_Boarding_Pass_WireFrame(@Optional("NA") String platform) {
        //mention the browser
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90895 under REGRESSION-CRITICAL suite on " + platform + " Browser", true);
        }
        /******************************************************************************
         ********************************Navigate to Boarding Page*********************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "DTW";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORTING_TYPE       = "Departure";
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "standard";
        final String UPGRADE_TYPE       = "BookIt";

        //Bags Page Method
        final String DEP_BAGS           = "Checked_1";

        //Option Page Constant Values
        final String OPTIONS_VALUE      = "ShortCutSecurity,NotRequired|ShortCutBoarding|CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD       = "Required";
        final String CARD_TYPE          = "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //Bag Page constant values
        final String CHECKIN_BAG_PURCHASE   = "DontPurchase";

        //Seat Page constant values
        final String CHECKIN_SEAT_PURCHASE  = "DontPurchase";

        //Car Page constant values
        final String CHECKIN_CAR_PURCHASE   = "DontPurchase";

        //Reservation summary Constant value
        final String HAZARDOUS_POP_UP    = "Accept";

//Step 1: Create a booking
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORTING_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);

        //Passenger Info Methods

        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Traveler1");
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0),passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(passengerInfoData.dob);
        pageObjectManager.getPassengerInfoPage().getAdultKTNListTextBox().get(0).sendKeys(passengerInfoData.ktNumber);

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(3000);

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
//        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(), BOOKING_STATUS);
        /*********************************************Start OF CheckIn Path**********************/
        //login to checkIn Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on Check In Boarding Pass
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //wait till 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //do not select Bag
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECKIN_BAG_PURCHASE);

        //do not select Seat
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(CHECKIN_SEAT_PURCHASE);

        //handle car section
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup(CHECKIN_CAR_PURCHASE);

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);

        //validate Accept and Print Boarding Pass
        ValidationUtil.validateTestStep("Accept and Print Boarding Pass is selected",
                pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupAcceptBoardingPassButton().isDisplayed());

        //Make a click on Accept and Print Boarding Pass
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(HAZARDOUS_POP_UP);

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);


//Step 2 : validate the email modal
        //create Web Element for finish Check-In button
        //Boarding pass modal

        final String YOUR_BOARDINGPASS_HEADER      = "Your Boarding Pass";
        final String YOUR_BOARDINGPASS_BODYTEXT    = "Choose to print your boarding pass now, have your boarding pass emailed to you so that you can display it on your mobile device, or both.";
        final String PRINT_BOARDING_PASS_NOW       = "Print Boarding Pass Now";
        final String EMAIL_BOARDING_PASS_TO        = "mail Boarding Pass to:";
        final String EMAIL_INPUT_NOTE              = "Please check your email on a mobile device to make sure the bar code shows properly";
        final String FINISH_CHECKIN                = "FINISH CHECK-IN";

        ValidationUtil.validateTestStep("Your Boarding Pass modal close button is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopUpCloseImage()));
        ValidationUtil.validateTestStep("The Boarding pass modal header is correct",
                pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupHeaderText().getText() , YOUR_BOARDINGPASS_HEADER );
        ValidationUtil.validateTestStep("The Boarding pass modal body text is correct",
                pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopUpSubHeaderText().getText() , YOUR_BOARDINGPASS_BODYTEXT);
        ValidationUtil.validateTestStep("The Boarding pass modal  is correct",
                pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(0).getText() , PRINT_BOARDING_PASS_NOW );
        ValidationUtil.validateTestStep("The Boarding pass modal  is correct",
                pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(1).getText() , EMAIL_BOARDING_PASS_TO );
        ValidationUtil.validateTestStep("The Boarding pass emailTextBox is displayed",
                pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassTextBox().isDisplayed());
        ValidationUtil.validateTestStep("The Boarding pass modal body text is correct",
                pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopUpFooterVerbageYour().getText() , EMAIL_INPUT_NOTE);
        ValidationUtil.validateTestStep("Finish check in button is correct",
                pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassButton().getText() , FINISH_CHECKIN );


//Step:3 select both options for boarding pass and continue
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup("Print","Email");
        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);

        TestUtil.closeBoardingPassPrintPopup();

        WaitUtil.untilPageLoadComplete(getDriver());
        /******************************************************************************
         ***********************Validation to Boarding Page****************************
         ******************************************************************************/

//Step 4: Validate user lands on the boarding pass page
        //declare constant used in Validation
        final String BOARDING_PASS_URL = "check-in/boarding-pass";

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
                getDriver().getCurrentUrl(), BOARDING_PASS_URL);

//Step 5: Validate boarding pass is divided into 4

        ValidationUtil.validateTestStep("The boarding pass page divided in 4" ,
                pageObjectManager.getBoardingPassPage().getBoardingPassSectionPanel().size() == 4 );

//Step 6: Validate top left quadrant

        //wait till 4 sec
        WaitUtil.untilTimeCompleted(4000);

        //CheckIn Seat Page
        final String WELCOME_ABOARD     = "Welcome Aboard";
        final String YOUR_BOARDINGPASS  = "This is your";
        final String PLEASE_ARRIVE      = "Please arrive to Self-Tag before";
        final String TO_CHECK_BAGS      = "to check baggage.";
        final String FOLD_BOARDING_PASS = "Fold Pass So...\nBarcode Faces Out.";
        final String STATION_ADVISORY   = "Detroit (DTW) Guests: Arrive early due to long lines Due to higher volume at the airport, learn more";

        //validate Welcome verbiage is displaying correct
        ValidationUtil.validateTestStep("Welcome verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getWelcomeHeaderText().get(0).getText(), WELCOME_ABOARD);

        ValidationUtil.validateTestStep("Name on top left quadrant is correct",
                pageObjectManager.getBoardingPassPage().getUserNameText().get(0).getText().contains(passengerInfoData.firstName.toUpperCase())
                        && pageObjectManager.getBoardingPassPage().getUserNameText().get(0).getText().contains(passengerInfoData.lastName.toUpperCase()));
        ValidationUtil.validateTestStep("'This is your boarding Pass' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getYourBoardingPassText().get(0).getText(), YOUR_BOARDINGPASS);

        ValidationUtil.validateTestStep("Name on top left quadrant is correct",
                pageObjectManager.getBoardingPassPage().getSelfBagTagText().get(0).getText().contains(PLEASE_ARRIVE)
                        && pageObjectManager.getBoardingPassPage().getSelfBagTagText().get(0).getText().contains(TO_CHECK_BAGS));
        ValidationUtil.validateTestStep("The fold boarding pass instruction are displayed" ,
                pageObjectManager.getBoardingPassPage().getFoldBoaringPassText().get(0).getText(), FOLD_BOARDING_PASS);
        ValidationUtil.validateTestStep("The station advisory message is displayed" ,
                pageObjectManager.getBoardingPassPage().getStationAdvisoryMessageText().get(0).getText(), STATION_ADVISORY );

        Date date = new Date();
        SimpleDateFormat newformat = new SimpleDateFormat("ddMMMYYYY");
        SimpleDateFormat newformat1 = new SimpleDateFormat("MM-dd-YYYY");

        final String CURRENTDATE = newformat.format(date);
        final String DEPART_CITY_CODE       = "DTW";
        final String DEPART_CITY            = "Detroit, MI";
        final String ARRIVE_CITY_CODE       = "FLL";
        final String ARRIVE_CITY            = "Fort Lauderdale, FL";
        final String NUMBER_CARRY_ON        = "0";
        final String CARRY_ON_TEXT          = "** NO CARRY-ON BAG **";
        final String NUMBER_CHECKED_ON      = "1";
        final String CHECKED_BAG_TEXT       = "CHECKED BAGS";

        final String SPIRIT_LOGO_IMAGE_ALT  = "Spirit Logo";
        final String BOARDING_PASS          = "BOARDING PASS";
        final String DATE_TEXT              = "DATE";
        final String FROM_TEXT              = "FROM:";
        final String TO_TEXT                = "TO:";
        final String SEQ_TEXT               = "Seq #";
        final String CONFIRMATION_TEXT      = "CONFIRMATION";
        final String FLIGHT_TEXT            = "FLIGHT";
        final String GATE_TEXT              = "GATE";
        final String CHECK_SCREEN_TEXT      = "CHECK SCREEN";
        final String BOARDING_TIME_TEXT     = "BOARDING TIME";
        final String LOCATED_TERMINAL       = "LOCATED IN TERMINAL";
        final String DOOR_CLOSED            = "DOORS CLOSE 15 MINUTES PRIOR TO DEPARTURE.";
        final String ZONE                   = "ZONE";
        final String SEAT                   = "SEAT";
        final String DEPARTS                = "DEPARTS";
        final String ARRIVE                 = "ARRIVE";
        final String ISSUED                 = "ISSUED";


//Step 7: validate 9dfc image
        //validate '$9 Image' is displaying correct
        ValidationUtil.validateTestStep("'$9 Image' is displaying correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getFareClubSavingImage()));

//Step 8: validate the lower left quadrant
        //validate 'Spirit' Logo is displaying correct
        ValidationUtil.validateTestStep("'Spirit' Logo is displaying correct",
                pageObjectManager.getBoardingPassPage().getSpiritLogoImage().get(0).getAttribute("alt"), SPIRIT_LOGO_IMAGE_ALT);

        //validate the TSA pre check image
        ValidationUtil.validateTestStep("'TSA PreCheck Logo is displaying correct",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getBoardingPassTSAPrecheckImage()));

        //validate 'Boarding Pass' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Boarding Pass' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getBoardingPassHeader().get(0).getText(), BOARDING_PASS);

        //validate 'Date' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Date' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getDate().get(0).getText().trim(), DATE_TEXT);
        ValidationUtil.validateTestStep("Flight Date is displaying correct",
                pageObjectManager.getBoardingPassPage().getFlightDate().get(0).getText().replace("DATE","").trim(), CURRENTDATE);

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

        //validate Sold SSRs is displaying correct
        ValidationUtil.validateTestStep("ShortCut Security circle is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getShortcutSecuritySold()));
        ValidationUtil.validateTestStep("ShortCut Boarding circle is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getShortcutBoardingSold()));
        ValidationUtil.validateTestStep("The Bar Code is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getBoardingPassPage().getBoardingPassImage()));

//Step--9
        //validate 'Flight' verbiage is displaying correct
        ValidationUtil.validateTestStep("'Flight' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getFlightHeaderText().get(0).getText().trim(), FLIGHT_TEXT);
        ValidationUtil.validateTestStep("'Flight' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getFlightNumberText().get(0).getText().trim(), "388");

        //validate 'GATE' verbiage is displaying correct
        ValidationUtil.validateTestStep("'GATE' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getGateText().get(0).getText().toUpperCase().trim(), GATE_TEXT);

        //validate 'CHECK SCREEN' verbiage is displaying correct
        ValidationUtil.validateTestStep("'CHECK SCREEN' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getCheckScreensText().get(0).getText().toUpperCase().trim(), CHECK_SCREEN_TEXT);
        ValidationUtil.validateTestStep("Terminal Location is displaying correct",
                pageObjectManager.getBoardingPassPage().getLocatedinTerminalText().get(0).getText().toUpperCase().trim(), LOCATED_TERMINAL);

        //validate 'BOARDING TIME' verbiage is displaying correct
        ValidationUtil.validateTestStep("'BOARDING TIME' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getBoardingTimeText().get(0).getText().trim(), BOARDING_TIME_TEXT);
        ValidationUtil.validateTestStep("'BOARDING TIME' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getBoardingActualTimeText().get(0).getText().trim(), "6:25 PM");
        ValidationUtil.validateTestStep("'BOARDING TIME' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getDoorCloseText().get(0).getText().trim(), DOOR_CLOSED);

        //validate 'ZONE' verbiage is displaying correct
        ValidationUtil.validateTestStep("'ZONE' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getZoneText().get(0).getText().trim(), ZONE);
        ValidationUtil.validateTestStep("'ZONE' verbiage is displaying correct",
                pageObjectManager.getBoardingPassPage().getZoneNumberText().get(0).getText().trim(), "2");

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
                pageObjectManager.getBoardingPassPage().getIssuedTimeText().get(0).getText().trim(),newformat1.format(date));

//STEP--10
        //********************************************SKYSPEED***********************************************
    }
}