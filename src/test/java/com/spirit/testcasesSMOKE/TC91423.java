package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//**********************************************************************************************
//Test Case ID: TC91423
//Test Name:  Reservation Summay_CP_CI_Passenger_Additional Info_Disability Seating_Wireframe and Validations
//Description:
//Created By : Sunny Sok
//Created On :
//Reviewed By: Salim Ansari
//Reviewed On: 17-May-2019
//**********************************************************************************************

public class TC91423 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "PassengerInfoSSR" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "ReservationUI"})
    public void Reservation_Summay_CP_CI_Passenger_Additional_Info_Disability_Seating_Wireframe_and_Validations(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Reservation Summary*********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91423 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //STEP--1
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
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        /******************************************************************************
         ********************Validation Reservations Summary page****************************
         ******************************************************************************/
        //declare constant used in Validation
        final String LapAnimalText   = "Does the animal need to sit on your lap during any phase of the trip?";
        final String CheckIn_URL     = "check-in/reservation-summary";
        final String Max_Input       = "45";
        final String Pnr_Max_Input   = "6";
        final String Other_Max_Input = "145";

        //Should be able to be complete the search and directed to the passenger check page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("User verify Navigation to Confirmation page on Check-In Home Page",
                getDriver().getCurrentUrl(),(CheckIn_URL));

        pageObjectManager.getReservationSummaryPage().getPassengerSectionAdditionalInfoAddButton().get(0).click();

        //--Step 5
        pageObjectManager.getReservationSummaryPage().getDisabilitySeatingLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify Disability Seating radio button is selected",
                pageObjectManager.getReservationSummaryPage().getDisabilitySeatingRadioButton().get(0).isSelected());

        //--Step 6

        pageObjectManager.getReservationSummaryPage().getTravelingWithServiceEsanLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify Disability Seating section: I am traveling with a Service/Emotional Support Animal text",
                pageObjectManager.getReservationSummaryPage().getLapAnimalText().get(0).getText(),LapAnimalText);

        ValidationUtil.validateTestStep("User verify Disability Seating section: I am traveling with a Service/Emotional Support Animal options are displayed",
                pageObjectManager.getReservationSummaryPage().getLapAnimalNoLabel().get(0).isDisplayed()&& pageObjectManager.getReservationSummaryPage().getLapAnimalYesLabel().get(0).isDisplayed());

        pageObjectManager.getReservationSummaryPage().getLapAnimalNoLabel().get(0).click();

        pageObjectManager.getReservationSummaryPage().getLapAnimalYesLabel().get(0).click();

        ValidationUtil.validateTestStep("User Validate that the user is not able to select the two options at the same time.",
                !pageObjectManager.getReservationSummaryPage().getlapAnimalNoRadioButton().get(0).isSelected() && pageObjectManager.getReservationSummaryPage().getLapAnimalYesRadioButton().get(0).isSelected());

        //--Step 7
        pageObjectManager.getReservationSummaryPage().getImmobileLegLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify  unable to bend leg/fused leg/immobile leg radio button is selected",
                pageObjectManager.getReservationSummaryPage().getImmobileLegRadioButton().get(0).isSelected());

        pageObjectManager.getReservationSummaryPage().getSeatWithAMoveableAisleArmrestLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify need a seat with a moveable aisle armrest radio button is selected",
                pageObjectManager.getReservationSummaryPage().getSeatWithAMoveableAisleArmrestRadioButton().get(0).isSelected());

        //--Step 8
        pageObjectManager.getReservationSummaryPage().getSeatForSomeoneTravelingWithMeLabel().get(0).click();
        ValidationUtil.validateTestStep("User verify need a seat for someone traveling with me radio button is selected",
                pageObjectManager.getReservationSummaryPage().getSeatForSomeoneTravelingWithMeRadioButton().get(0).isSelected());

        ValidationUtil.validateTestStep("User verify Vision Impairment Reader Attendant, Hearing Impairment Interpreter, Safety/Personal Care Attendant options are displayed",
                pageObjectManager.getReservationSummaryPage().getVisionImpairmentReaderAttenantLabel().get(0).isDisplayed() &&
                        pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterLabel().get(0).isDisplayed() &&
                        pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantLabel().get(0).isDisplayed());

        //--Step 9
        pageObjectManager.getReservationSummaryPage().getVisionImpairmentReaderAttenantLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify Vision Impairment radio button is selected",
                pageObjectManager.getReservationSummaryPage().getVisionImpairmentReaderAttenantRadioButton().get(0).isSelected());

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User validate legal name and reservation code text boxes are displayed",
                pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantNameTextbox().isDisplayed() &&
                        pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantReservationCodeTextbox().isDisplayed());

        //--Step 10
        for (int i = 0; i < 10; i++) {
            pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantNameTextbox().sendKeys("TESTS");
        }

        String VisionAttendantNameInputValue = String.valueOf(pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantNameTextbox().getAttribute("value").length());

        ValidationUtil.validateTestStep("User verify Vision Impairment Attendant Name Textbox could only accept 45 characters",VisionAttendantNameInputValue,(Max_Input));

        //--step 11

        for (int i = 0; i < 2; i++) {
            pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantReservationCodeTextbox().sendKeys("T12#S");
        }

        pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify Vision Impairment Attendant Reservation Code Textbox could only accept 6 characters",pageObjectManager.getReservationSummaryPage().getErrorMessageText().getText().trim(),"Only letters and numbers are allowed");

        WaitUtil.untilTimeCompleted(1000);

        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantReservationCodeTextbox());


        for (int i = 0; i < 2; i++) {
            pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantReservationCodeTextbox().sendKeys("TESTS");
        }

        String attendantPnrTextboxInputValue = String.valueOf(pageObjectManager.getReservationSummaryPage().getVisionImpairmentAttendantReservationCodeTextbox().getAttribute("value").length());

        ValidationUtil.validateTestStep("User verify Vision Impairment Attendant Reservation Code Textbox could only accept 6 characters",attendantPnrTextboxInputValue.equals(Pnr_Max_Input));

        //--Step 12
        pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify Hearing Impairment radio button is selected",
                pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterRadioButton().get(0).isSelected());

//        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        ValidationUtil.validateTestStep("User validate legal name and reservation code text boxes are displayed",
                pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterNameTextBox().isDisplayed() &&
                        pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterReservationCodeTextbox().isDisplayed());

        //--Step 13
        for (int i = 0; i < 10; i++) {
            pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterNameTextBox().sendKeys("TESTS");
        }

        String HearingattendantNameInputValue = String.valueOf(pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterNameTextBox().getAttribute("value").length());

        ValidationUtil.validateTestStep("User verify hearing Impairment Attendant Name Textbox could only accept 45 characters",HearingattendantNameInputValue,(Max_Input));

        //--Step 14

        for (int i = 0; i < 2; i++) {
            pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterReservationCodeTextbox().sendKeys("T12#S");
        }

        pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify Hearing Impairment Attendant reservation code Textbox could only alphanumeric characters",pageObjectManager.getReservationSummaryPage().getErrorMessageText().getText().trim(),"Only letters and numbers are allowed");

        WaitUtil.untilTimeCompleted(1000);

        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterReservationCodeTextbox());

        for (int i = 0; i < 2; i++) {
            pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterReservationCodeTextbox().sendKeys("TESTS");
        }

        String hearingAttendantPnrInputValue = String.valueOf(pageObjectManager.getReservationSummaryPage().getHearingImpairmentInterpreterReservationCodeTextbox().getAttribute("value").length());

        ValidationUtil.validateTestStep("User verify Hearing Impairment Attendant reservation code Textbox could only accept 6 characters",hearingAttendantPnrInputValue,(Pnr_Max_Input));

        //--Step 15
        pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify Safety Personal Care Attendant radio button is selected",
                pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantRadioButton().get(0).isSelected());

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User validate legal name and reservation code text boxes are displayed",
                pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantNameTextbox().isDisplayed() &&
                        pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantReservationCodeTextbox().isDisplayed());

        //--Step 16
        for (int i = 0; i < 10; i++) {
            pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantNameTextbox().sendKeys("TESTS");
        }

        String safteyattendantNameInputValue = String.valueOf(pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantNameTextbox().getAttribute("value").length());

        ValidationUtil.validateTestStep("User verify Safety/Personal Care Attendant Name Textbox could only accept 45 characters",safteyattendantNameInputValue,(Max_Input));

        //--Step 17

        for (int i = 0; i < 2; i++) {
            pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantReservationCodeTextbox().sendKeys("T12#S");
        }

        pageObjectManager.getReservationSummaryPage().getOtherDisabilityLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify Hearing Impairment Attendant reservation code Textbox could only alphanumeric characters",pageObjectManager.getReservationSummaryPage().getErrorMessageText().getText().trim(),"Only letters and numbers are allowed");

        WaitUtil.untilTimeCompleted(1000);

        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantReservationCodeTextbox());

        for (int i = 0; i < 2; i++) {
            pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantReservationCodeTextbox().sendKeys("TESTS");
        }

        String safteyAttendantPnrInputValue = String.valueOf(pageObjectManager.getReservationSummaryPage().getSafteyPersonalCareAttendantReservationCodeTextbox().getAttribute("value").length());

        ValidationUtil.validateTestStep("User verify Safety/Personal Care Attendant reservation code Textbox could only accept 6 characters",safteyAttendantPnrInputValue,(Pnr_Max_Input));

        //--Step 18
        //--Input an invalid PNR with 6 alphanumeric characters and click the "Save" button
        //The user should get an Error message and be unable to save the wrong information.
        // ***functionality does not work-Looking further into this issue***

        //--Step 19
        pageObjectManager.getReservationSummaryPage().getOtherDisabilityLabel().get(0).click();

        ValidationUtil.validateTestStep("User verify Other disability radio button is selected",
                pageObjectManager.getReservationSummaryPage().getOtherDisabilityRadioButton().get(0).isSelected());

        for (int i = 0; i < 30; i++) {
            pageObjectManager.getReservationSummaryPage().getOtherDisabilityTextbox().sendKeys("TESTS");
        }

        String otherDisabliltyInputValue = String.valueOf(pageObjectManager.getReservationSummaryPage().getOtherDisabilityTextbox().getAttribute("value").length());
        ValidationUtil.validateTestStep("User verify Other Disability Textbox could only accept 145 characters",otherDisabliltyInputValue,(Other_Max_Input));
    }

}