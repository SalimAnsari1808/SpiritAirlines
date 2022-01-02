package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC91421
//Title      : Reservation Summary_CP_MT_PassE2enger_Additional Info_Disability Seating_Wireframe and Validations
//Created By : Kartik Chauhan
//Created On : 07-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************
public class TC91421 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "OneWay" , "DomesticInternational" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats"  , "Visa" , "AddEditPassengerInfo" , "ReservationUI" })
    public void  Reservation_Summary_CP_MT_Passenger_Additional_Info_Disability_Seating_Wireframe_and_Validations (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Reservation Summary Page***************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91421 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String JOURNEY_TYPE       = "Flight";
        final String LANGUAGE           = "English";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

        /******************************************************************************
         **************************Reservation Summary Page****************************
         //****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //my trip page
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--2
        //enter PNR and Last name
        pageMethodManager.getHomePageMethods().loginToMyTrip();
//STEP--3
        //Verify Additional Info caret
        ValidationUtil.validateTestStep("Verify Additional Info caret is displaying on Reservation Summary page",
                pageObjectManager.getReservationSummaryPage().getPassengerSectionAdditionalInfoAddButton().get(0).isDisplayed());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Click on Additional Info caret
        pageObjectManager.getReservationSummaryPage().getPassengerSectionAdditionalInfoAddButton().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--4
        //Select Disability Seating
        pageObjectManager.getReservationSummaryPage().getDisabilitySeatingLabel().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Validating 'To best meet your seating...' verbiage
        ValidationUtil.validateTestStep("Validating 'To best meet your seating...' are displaying'",
                pageObjectManager.getReservationSummaryPage().getToBestMeetVerbiage().isDisplayed());
//STEP--5
        //Select Traveling with Service/Emotional
        pageObjectManager.getReservationSummaryPage().getTravelingWithServiceEsanLabel().get(0).click();

        //Validating the Lap animal text is displaying
        ValidationUtil.validateTestStep("Validating the Lap animal text is displaying'",
                pageObjectManager.getReservationSummaryPage().getLapAnimalText().get(0).isDisplayed());

        //Validating the Lap animal No Radio button is displaying
        ValidationUtil.validateTestStep("Validating the Lap animal No Radio button is displaying'",
                pageObjectManager.getReservationSummaryPage().getLapAnimalNoLabel().get(0).isDisplayed());

        //Validating the Lap animal Yes Radio button is displaying
        ValidationUtil.validateTestStep("Validating the Lap animal Yes Radio button is displaying'",
                pageObjectManager.getReservationSummaryPage().getLapAnimalYesLabel().get(0).isDisplayed());

//STEP--6
        //select Immobile Leg
        pageObjectManager.getReservationSummaryPage().getImmobileLegLabel().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Select seat with Moveable Arm rest
        pageObjectManager.getReservationSummaryPage().getSeatWithAMoveableAisleArmrestLabel().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--7
        //Select Seat for someone Traveling with me
        pageObjectManager.getReservationSummaryPage().getSeatForSomeoneTravelingWithMeLabel().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Validating the Vision Impairment.. option is displaying
        ValidationUtil.validateTestStep("Validating the Vision Impairment.. option is displaying'",
                pageObjectManager.getReservationSummaryPage().getVisionImpairmentReaderAttenantLabel().get(0).isDisplayed());

        //Validating the Hearing Impairment.. option is displaying
        ValidationUtil.validateTestStep("Validating the Hearing Impairment.. option is displaying'",
                pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterLabel().get(0).isDisplayed());

        //Validating the Safety/Personal Care.. option is displaying
        ValidationUtil.validateTestStep("Validating the Safety/Personal Care.. option is displaying'",
                pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantLabel().get(0).isDisplayed());
//STEP--8
        //select Vision Impairment
        pageObjectManager.getReservationSummaryPage().getVisionImpairmentReaderAttenantLabel().get(0).click();

        //Validating the Vision Impairment Attendant Name textbox is displaying
        ValidationUtil.validateTestStep("Validating the Vision Impairment Attendant Name..  Textbox is displaying'",
                pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantNameTextbox().isDisplayed());

        //Validating the Vision Impairment - Reservation Code..  Textbox is displaying
        ValidationUtil.validateTestStep("Validating the Vision Impairment - Reservation Code..  Textbox is displaying'",
                pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantReservationCodeTextbox().isDisplayed());
//STEP--9
        //create final constant
        final String FOURTYSIX_INPUT = "qwertyuioppasdfghjklzxcvbnmqwertyuiopasdfghja";

        //send alphanumeric input
        pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantNameTextbox().sendKeys(FOURTYSIX_INPUT);

        //Validating the vision Impairment Attendent textbox accept 145 input
        ValidationUtil.validateTestStep("Validating the vision Impairment Attendent Name is accepting 45 input''",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantNameTextbox()).length()==45);

//STEP--10
        //create constant
        final String SIX_INPUT = "qwertyu";

        //enter 6 input
        pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantReservationCodeTextbox().sendKeys(SIX_INPUT);

        //Validating the vision Impairment- Confirmation Code..  Textbox is accepting 6 input
        ValidationUtil.validateTestStep("Validating the vision Impairment- Confirmation Code..  Textbox is accepting 6 input",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantReservationCodeTextbox()).length()==6);

//STEP--11
        //Select Hearing Impairment
        pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterLabel().get(0).click();

        //Wair for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Validating the Hearing Imparient-Legal name
        ValidationUtil.validateTestStep("Validating the Hearing Impairment-Legal name Textbox is displaying'",
                pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterNameTextBox().isDisplayed());

        //Validating the Hearing Imparient-Reservation Code Textbox
        ValidationUtil.validateTestStep("Validating the Hearing Impairment-Reservation Code Textbox is displaying'",
                pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterReservationCodeTextbox().isDisplayed());
//STEP--12
        //create constant
        final String HEARING_FOURTYSIX_INPUT = "qwertyuioppasdfghjklzxcvbnmqwertyuiopasdfghja";

        //Enter 46 input
        pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterNameTextBox().sendKeys(HEARING_FOURTYSIX_INPUT);

        //Validating the Hearing Impairment-Reservation Code Textbox accept 45 input
        ValidationUtil.validateTestStep("Validating the Legal Attendant..  Textbox is accepting 45 input''",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterNameTextBox()).length()==45);
//STEP--13
        //create constant
        final String HEARING_SEVEN_INPUT = "qwertyu";

        //Enter 7 input
        pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterReservationCodeTextbox().sendKeys(HEARING_SEVEN_INPUT);

        //Validating the Hearing Impairment-Reservation Code Textbox accept 6 input
        ValidationUtil.validateTestStep("Validating the Legal Attendant..  Textbox is accepting 6 input''",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterReservationCodeTextbox()).length()==6);
//STEP--14
        //Click on Safety Personal care attendant
        pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantLabel().get(0).click();

        //Wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //Validating the Hearing Impairment-Legal name
        ValidationUtil.validateTestStep("Validating the Safety Impairment-Legal name Textbox is displaying'",
                pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantNameTextbox().isDisplayed());

        //Validating the Safety Impairment-Reservation Code Textbox
        ValidationUtil.validateTestStep("Validating the Safety Impairment-Reservation Code Textbox is displaying'",
                pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantReservationCodeTextbox().isDisplayed());
//STEP--15
        //create constant
        final String SAFETY_FOURTYSIX_INPUT = "qwertyuioppasdfghjklzxcvbnmqwertyuiopasdfghja";

        //Enter max input
        pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantNameTextbox().sendKeys(SAFETY_FOURTYSIX_INPUT);

        //Validating the Safety panel- Attendant Textbox accept 45 input
        ValidationUtil.validateTestStep("Validating the Safety panel- Attendant Textbox is accepting 45 input",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantNameTextbox()).length()==45);
//STEP--16
        //create constant
        final String SAFETY_SEVEN_INPUT = "QWERTYU";

        //Enter 7 input
        pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantReservationCodeTextbox().sendKeys(SAFETY_SEVEN_INPUT);

        //verify textbox is accepting
        ValidationUtil.validateTestStep("Validating the Safety Personal care attendant Reservation code..  Text box is accepting 6 input''",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantReservationCodeTextbox()).length()==6);
//STEP--17
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on Save Changes button
        pageObjectManager.getReservationSummaryPage().getSSRSaveChangeButton().click();

//ERROR messgae was not displaying********************************************************
//STEP--18
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(4000);

        //Click on Additional Info carot
        pageObjectManager.getReservationSummaryPage().getPassengerSectionAdditionalInfoAddButton().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Select Disability Seating
        pageObjectManager.getReservationSummaryPage().getDisabilitySeatingLabel().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Select Other Disability option
        pageObjectManager.getReservationSummaryPage().getOtherDisabilityLabel().get(0).click();

        //Wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Create constant variable
        final String OTHER_DISABILITY_INPUT = "qwertyuioppasdfghjklzxcvbnmqwertyuiopasdfghjqwertyuioppasdfghjklzxcvbnmqwertyuiopasdfghjqwertyuioppasdfghjklzxcvbnmqwertyuiopasdfghjqwertyuiopqweww";

        //Send 146 keywords to other disability Textbox
        pageObjectManager.getReservationSummaryPage().getOtherDisabilityTextbox().sendKeys(OTHER_DISABILITY_INPUT);

        //validate the input should be of 145
        ValidationUtil.validateTestStep("Validating the Other Disability Text box is accepting 145 input'",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getReservationSummaryPage().getOtherDisabilityTextbox()).length()==145);
    }

}
