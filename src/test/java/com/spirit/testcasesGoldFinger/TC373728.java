package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:14803] GoldFinger MVP1: CP: BP:  Vacation: Flight-Car: Payment Page: Pick-Up time for car is before flight time
//Test Case ID: TC373728
//Description: Task 27152: TC373728 - US 18549 - 001 - Confirmation Email - RT HUB Packaging booking Verification
//Created By: Gabriela
//Created On: 14-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373728 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug","BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest",
                    "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "OptionalUI", "Visa", "PaymentUI",
                    "ConfirmationUI", "Email"})
    public void Confirmation_Email_RT_HUB_Packaging_booking_Verification(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373728 under GoldFinger Suite on " + platform + " Browser", true);
        }
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE2         = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE2          = "135";
        final String ARR_DATE2          = "139";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 1: Land on GoldFinger Test enviroment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Begin to Book  Round Trip | DOM to DOM | At least 4 months out | 1 Adult |
        //*** Home Page Methods**/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE2, ARR_DATE2);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select Flights and Click on "CONTINUE"
        //*** Flight Availability Page Methods **/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 4: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 5: Input passenger information. Make sure your passenger is older than 21 years of age and you are using an unique email address and click "CONTINUE"
        //*** Passenger Information Page Methods **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 6 & 7: Scroll to the bottom of the page and click on "CONTINUE WITHOUT ADDING BAGS" & click "I DON'T NEED BAGS"
        //*** Bags Page Methods **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 8: Scroll down and select "CONTINUE WITHOUT SELECTING SEATS"
        //*** Seats Page Methods **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 9: Select a Vehicle from Hertz company and a Hotel. Scroll down to "Select your Check-In option click drop down carrot and select "I'm not sure, I'll decide later" Then click on "CONTINUE"
        //*** Options Page Methods **/
        pageMethodManager.getCarPageMethods().selectCarOnOptionPage("Hertz","NA");
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 10: Purchase the booking using any Valid testing Credit Card
        //***Payment Page Method**/
        //TODO: [IN:14803]
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

        try {
            pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
            pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

            //***Confirmation Page Method**/
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
            WaitUtil.untilPageLoadComplete(getDriver());

//- Step 11:  Verify the Flight and Car are displayed on the top right hand side of the Page
            ValidationUtil.validateTestStep("Validating Booking Confirmation Code is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getBookingSectionTopConfirmationCode()));
            ValidationUtil.validateTestStep("Validating Package Confirmation Code is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getBookingSectionTopCarConfirmationCodeText()));

//- Step 12: Open SkySpeed on the test enviroment 1_4.2
            //TODO: UNable to automate this step
//- Step 13: Log in using apagent credentials
            //TODO: UNable to automate this step
//- Step 14: Input the flights PNR on the "Retrieve Reservation" Page(F10)
            //TODO: UNable to automate this step
//- Step 15: Verify confirmation codes match between the original displayed on the Confirmation page on web and on Comment section on SkySpeed
            //TODO: UNable to automate this step

//- Step 16: Access the email and verify the confirmation email received is from Spirit only
            pageMethodManager.getCommonPageMethods().verifyPackageBookingEmails();

//- Step 17: Verify the email displays the correct flight information
            //TODO: Method to validate package booking on email needs to be implemented
//- Step 18: Verify the email displays the correct Car information
            //TODO: Method to validate package booking on email needs to be implemented
//- Step 19: Verify the following verbiage is displayed on Hotel section:
            //TODO: Method to validate package booking on email needs to be implemented
            //With Penalty:
            //Without Penalty Showing in System:
//- Step 20: Validate the template reflect Flight+Car+Hotel / Flight+Car / Flight+Hotel when applicable
            //TODO: Method to validate package booking on email needs to be implemented
//- Step 21: Validate a break down of the flight vs. car or hotel is not displayed but instead display a packaging price
            //TODO: Method to validate package booking on email needs to be implemented
//- Step 22: Compare Reservation price on Confirmation Page, Skyspeed and confirmation Email

            //Canceling car selected
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
        }
        //This catch block will catch any Validation/Assertion errors encountered after Payment and still cancel reservations
        catch(AssertionError fail)
        {
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
        }
        //This catch block will catch any Exceptions (null pointer, no such element, etc) after Payment and still cancel reservations
        catch (Exception fail)
        {
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
        }
    }
}