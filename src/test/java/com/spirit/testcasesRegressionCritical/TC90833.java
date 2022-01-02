package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.enums.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.text.*;
import java.util.*;
//**********************************************************************************************

//Test Case ID: TC90833
//Description: Reservation Summary_CP_CI_Page WireFrame
//Created By:  Sunny Sok
//Created On:  07-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************
//Bug 26069 CP: CI: Online Check In Page: "Edit" Link is displayed instead "Add" link on Passenger section for KTN and REDRESS fields when the information wasn't is intro before
public class TC90833 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "CheckIn" , "RoundTrip" , "DomesticDomestic" , "NineDFC" , "WithIn7Days" , "Adult" , "NonStop" , "BookIt" ,"PassengerInfoKTN", "CarryOn" , "CheckedBags" , "Standard" , "ShortCutBoarding" ,"CheckInOptions","TravelInsurancePopUp", "MasterCard" , "ReservationUI"})
    public void Reservation_Summary_CP_CI_Page_WireFrame(@Optional("NA") String platform) {
        /******************************************************************************
         *********************Navigate to Reservation Summary Page*********************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90833 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_EMAIL 		= "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "8";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page constant values
        final String DEP_BAGS           = "Carry_1|Checked_1";
        final String ARR_BAGS           = "Carry_1|Checked_1";

        //Seats Page Constant Values
        final String DEP_SEAT           = "Standard";
        final String ARR_SEAT           = "Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "Required";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        /******************************************************************************
         *********************Navigate to Reservation Summary page*********************
         ******************************************************************************/
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        //getting date
        String Date = pageObjectManager.getHomePage().getDateBox().getAttribute("value");

        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("NineFCMember");

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getAdultKTNListTextBox().get(0).sendKeys(passengerInfoData.ktNumber);
        String PhoneNumber = pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberTextBox().getAttribute("value");
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(ARR_BAGS);
        String DepBags = pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(0).getAttribute("value") + " Carry-On, " + pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(0).getAttribute("value") + " Checked Bag";
        String RetBags = pageObjectManager.getBagsPage().getReturningCarryOnCounterTextBox().get(0).getAttribute("value") + " Carry-On, " + pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(0).getAttribute("value") + " Checked Bag";

        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(ARR_SEAT);

        pageObjectManager.getSeatsPage().getFlightLegsText().get(0).click();
        String DepSeat = pageObjectManager.getSeatsPage().getPassengerSeatText().get(0).getText();

        pageObjectManager.getSeatsPage().getFlightLegsText().get(1).click();
        String ArrSeat = pageObjectManager.getSeatsPage().getPassengerSeatText().get(1).getText();

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().fillAnotherCardPaymentDetailsModifyPath(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        // closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //storing pnr
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(), BOOKING_STATUS);

//        String TravelGuardPolicyNumber = pageObjectManager.getConfirmationPage().getTravelInsurancePolicyNumber().getText();

        //--step 1
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        WaitUtil.untilPageLoadComplete(getDriver());

        /******************************************************************************
         ************************Validation Reservation Summary page********************
         ******************************************************************************/
        LoginCredentialsData loginCredentialsData =FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(LOGIN_EMAIL);

        //Constant Values to validate
        final String CHECKIN_RESERVATION_URL    = "/check-in/reservation-summary";
        String BLACK                            = "rgba(0, 0, 0, 1)";
        String BLUE                             = "rgba(0, 115, 230, 1)";
        final String SUBHEADER_LINE1            = "Hey there! Online and mobile app check-in is available 24 hours and up to 1 hour before flight departure.";
        final String SUBHEADER_LINE2            = "Buy your bags and seats anytime prior to 1 hour before flight departure. Our updated baggage policies.";
        final String MARKETING_VERBIAGE         = "Watch your emails for any flight time changes that may occur prior to your trip. Schedule change notifications will be sent to "+ loginCredentialsData.userName +" up until the day of your departure. On the day you're traveling, you can reconfirm your flight times by visiting our website or by calling us directly at 1.801.401.2222. It is recommended that you arrive at the airport about 2 hours prior to your departure for domestic flights and 3 hours prior to departure for international flights.";
        final String OPTION_HEADER              = "Options";
        final String TRAVELINSURANCE_HEADER     = "Travel Insurance";
        final String OPTION_SELECTED            = "Shortcut Boarding\n" + "Zone 2 priority boarding and early access to the overhead bins";
        final String TRAVELGUARD_NOTE           ="NOTE: View your policy online . For additional questions about your policy please contact Travel Guard directly at 866-877-3191.";

        //--step 2
        //verify user on Check-in Reservation summary Page
        ValidationUtil.validateTestStep("Verify user navigated to Manage Travel Reservation Summary Page,",
                getDriver().getCurrentUrl(), CHECKIN_RESERVATION_URL);

        //--step 4
        //verify The title of the page is "Online Check-In" in black color font
        ValidationUtil.validateTestStep("verify The title of the page is Your Itinerary Receipt",
                pageObjectManager.getReservationSummaryPage().getHeaderText().getCssValue("color"), BLACK);

        //--step 5
        //Under "Online Check-In" left aligned verbiage says "Hey There! Online and mobile app check-in is available 24 hours and up to 1 hour before flight departure.
        List<WebElement> HeaderSubText = pageObjectManager.getReservationSummaryPage().getSubHeadeText();
        for(int i = 0 ;  i < HeaderSubText.size() ; i ++) {
            String HeaderSubTextVerbiage[] = new String[]{SUBHEADER_LINE1, SUBHEADER_LINE2};
            ValidationUtil.validateTestStep("Verify SubHeader Text: " + HeaderSubText.get(i).getText(), HeaderSubText.get(i).getText(),HeaderSubTextVerbiage[i]);}

        //--step 6
        //Under the "Hey There!", left aligned is "Buy your Bags and Seats anytime prior to 1 hour before flight departure"
        //NOTE: "Buy your Bags and Seats" will be in blue color and is a hyperlink
        ValidationUtil.validateTestStep("Verify links Bags and seat are displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSubHeaderBagsText())
                && TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSubHeaderSeatsText()));

        ValidationUtil.validateTestStep("Verify links Bags and seats are blue",
                pageObjectManager.getReservationSummaryPage().getSubHeaderBagsText().getCssValue("color").contains(BLUE)
                && pageObjectManager.getReservationSummaryPage().getSubHeaderSeatsText().getCssValue("color").contains(BLUE) );

        //--step 7
        //Verify NOTE: This page is not your boarding pass is displayed
        ValidationUtil.validateTestStep("Verify NOTE: This page is not your boarding pass is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSubHeaderBoardingPassText()));

        //--step 8
        //Verify PRINT RECEIPT  is in blue color font and is displayed
        ValidationUtil.validateTestStep("Verify PRINT RECEIPT  is in blue color font and is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPrintReceiptButton())
                        && pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().getCssValue("color").contains(BLUE));

        //--step 9 + step 10
        //Verify  content block with booking date, status, and confirmation code.
        //verify Booking Date: format is Month DD, YYYY
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, y");
        Date TodaysDate = new Date();
        ValidationUtil.validateTestStep("Verify Booking Date and format",
                pageObjectManager.getConfirmationPage().getBookingSectionTopDateText().get(0).getText(),"Booking Date: " + dateFormat.format(TodaysDate));

        //verify Status: Confirmed
        ValidationUtil.validateTestStep("Verify booking Status: Confirmed",
                pageObjectManager.getReservationSummaryPage().getStatusText().get(0).getText(),"Status: " + BOOKING_STATUS);

        //verify Confirmation code
        ValidationUtil.validateTestStep("Verify Confirmation Code:" + scenarioContext.getContext(Context.CONFIRMATION_CODE) ,
                pageObjectManager.getReservationSummaryPage().getConfirmationCodeNumberText().get(0).getText(),"Confirmation Code: " + scenarioContext.getContext(Context.CONFIRMATION_CODE));

        //--step 12
        //Verify subheader verbiage Flights is displayed
        ValidationUtil.validateTestStep("Verify subheader verbiage Flights is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getFlightSectionHeaderText()));

        //--step 13 + step 14
        //verify  icon of an airplane facing right followed by a date for example: "September 27, 2018". Right aligned the verbiage "CHANGE FLIGHTS" in blue color font
        ValidationUtil.validateTestStep("Verify Change flights is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton()));

        ValidationUtil.validateTestStep("Verify airplane icon  is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getFlightSectionDateIconImg().get(0)));

        String [] Dates = Date.split("\\-");

        Date DepdateTemp = TestUtil.convertStringToDate(Dates[0],"MM/d/y");
        String Depdate = dateFormat.format(DepdateTemp);

        Date ArrdateTemp = TestUtil.convertStringToDate(Dates[1],"MM/d/y");
        String Retdate = dateFormat.format(ArrdateTemp);

        String [] DepFlightDetails = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split("\\|");
        String [] RetFlightDetails = scenarioContext.getContext(Context.AVAILABILITY_RET_FLIGHT_DETAILS).toString().split("\\|");

        //--step 15
        //Verify depart details
        ValidationUtil.validateTestStep("Verify Departure date",
                pageObjectManager.getReservationSummaryPage().getFlightSectionDateText().get(0).getText(), Depdate);

        //Verify departing city and time
        ValidationUtil.validateTestStep("Verify departing city and time",
                pageObjectManager.getReservationSummaryPage().getFlightSectionDepartCityText().get(0).getText(),"Depart: " + DepFlightDetails[2].replace("DepartureAirport:","") + " " + DepFlightDetails[4].replace("DepartureTime:",""));

        //Verify Arriving City and time
        ValidationUtil.validateTestStep("Verify Arriving City and time",
                pageObjectManager.getReservationSummaryPage().getFlightSectionAriveCityText().get(0).getText(), "Arrive: " + DepFlightDetails[3].replace("ArrivalAirport:","") + " " + DepFlightDetails[5].replace("ArrivalTime:", ""));

        //verify Flight Number
        ValidationUtil.validateTestStep("Verify Flight number",
                pageObjectManager.getReservationSummaryPage().getFlightSectionFlightNumberText().get(0).getText(), DepFlightDetails[0].replace("Number",""));

        //--step 16 - step 18
        //Verifying Return flight
        ValidationUtil.validateTestStep("Verify Departure date",
                pageObjectManager.getReservationSummaryPage().getFlightSectionDateText().get(1).getText(), Retdate);

        //Verify departing city and time
        ValidationUtil.validateTestStep("Verify departing city and time",
                pageObjectManager.getReservationSummaryPage().getFlightSectionDepartCityText().get(1).getText(),"Depart: " + RetFlightDetails[2].replace("DepartureAirport:","") + " " + RetFlightDetails[4].replace("DepartureTime:",""));

        //verify Arrival city and time
        ValidationUtil.validateTestStep("Verify Arrival city and time",
                pageObjectManager.getReservationSummaryPage().getFlightSectionAriveCityText().get(1).getText(), "Arrive: " + RetFlightDetails[3].replace("ArrivalAirport:","") + " " + RetFlightDetails[5].replace("ArrivalTime:", ""));

        //verify Flight Number
        ValidationUtil.validateTestStep("Verify Flight number",
                pageObjectManager.getReservationSummaryPage().getFlightSectionFlightNumberText().get(1).getText(), RetFlightDetails[0].replace("Number",""));

        //step--19
        //Inside the content block under all the flight information is marketing verbiage
        ValidationUtil.validateTestStep("Verify marketing verbiage is displayed",
                 getDriver().findElement(By.xpath("//*[contains(text(),'Watch your emails')]")).getText(), MARKETING_VERBIAGE);

        //--step 23 - step 30
        //Verify sub header passengers is displayed
        ValidationUtil.validateTestStep("Verify Header Passengers label is displayed",
               TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getPassengerHeaderTxt()));

        //verify person icon
        ValidationUtil.validateTestStep("Verify Person icon is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getPassengerIconImg()));

        //verify First name Last name
        ValidationUtil.validateTestStep("Verify First name and last name",
                pageObjectManager.getConfirmationPage().getPassengerSectionNamesText().get(0).getText(), passengerInfoData.title + passengerInfoData.firstName + " " + passengerInfoData.lastName );

        //verify FREE SPIRIT #: 10 digits displayed if provided. If not provided is it - a dash
        ValidationUtil.validateTestStep("Verify FREE SPIRIT # is displayed",
                pageObjectManager.getConfirmationPage().getPassengerFreeSpirirtNumberText().get(0).getText(), "Free Spirit #: " + passengerInfoData.fsNumber);

        //verify Known Traveler # (TSA Pre check mark): displayed if applicable
        ValidationUtil.validateTestStep("Verify KTN #",
                pageObjectManager.getConfirmationPage().PassengerKTN(), passengerInfoData.ktNumber);

        //verify KTN EDIT button is displayed
        ValidationUtil.validateTestStep("Verify KTN EDIT button is displayed#",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPassengerSectionKTNEditButton()));

        //Verify Redress is displayed
        ValidationUtil.validateTestStep("Verify Redress is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPassengerSectionRedressLabel().get(0)));

        /****BUG: REDRESS EDIT BUTTON IS DISPLAYED**/
        //Verify Redress Add button is displayed
        ValidationUtil.validateTestStep("Verify Redress Add button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPassengerSectionRedressAddButton().get(0)));

        //verify Suitcase icon
        ValidationUtil.validateTestStep("Verify suitcase icon is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getBagsIcon().get(0)));

        //verify Depart bags
        ValidationUtil.validateTestStep("Verify bags for Depart - Arrival" ,
                pageObjectManager.getConfirmationPage().getDepBagsTxt().get(0).getText(), DEP_AIRPORT_CODE + "-" + ARR_AIRPORT_CODE + ":" + DepBags);

        //verify Return bags
        //verify  city abbreviation: format is arrival - depart all capital letters # Carry-On, # Checked
        ValidationUtil.validateTestStep("Verify bags for Arrival- Departure" ,
                pageObjectManager.getConfirmationPage().getRetBagsTxt().get(0).getText(), ARR_AIRPORT_CODE + "-" + DEP_AIRPORT_CODE + ":" + RetBags);

        //Verify Bags Edit button is displayed
        ValidationUtil.validateTestStep("Verify Bags Edit button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPassengerSectionEditBagsButton().get(0)));

        //verify Seat icon is displayed
        ValidationUtil.validateTestStep("Verify Seat icon is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getSeatsIcon()));

        //verify Departing Seat
        ValidationUtil.validateTestStep("Verify arrival - depart city pair and seat information",
                pageObjectManager.getConfirmationPage().getDepSeatsTxt().get(0).getText(),DEP_AIRPORT_CODE + "-" + ARR_AIRPORT_CODE + ":" + DepSeat);

        ValidationUtil.validateTestStep("Verify depart - arrival city pair and seat information",
                pageObjectManager.getConfirmationPage().getRetSeatsTxt().get(0).getText(), ARR_AIRPORT_CODE + "-" +  DEP_AIRPORT_CODE +  ":" + ArrSeat);

        //Verify Edit Seats button is displayed
        ValidationUtil.validateTestStep("Verify Edit Seats Button is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPassengerSectionEditSeatsButton().get(0)));

        //--step 31
        // Verify Additional Info: followed by right aligned ADD or EDIT hyperlink is displayed
        ValidationUtil.validateTestStep("Verify Additional Info: followed by right alighed ADD or EDIT hyperlink is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPassengerSectionAdditionalInfoAddButton()));

        //--step 33
        //verify Section title Options only display if a customer purchased
        ValidationUtil.validateTestStep("Verify Options header is displayed",
                pageObjectManager.getReservationSummaryPage().getOptionsSectionHeaderTxt().getText(), OPTION_HEADER);

        //--step 41
        //verify your extras is displayed
        ValidationUtil.validateTestStep("Verify your extras is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getYourExtrasText()));

        //verify options
        ValidationUtil.validateTestStep("Verify extras selected is displayed",
                pageObjectManager.getReservationSummaryPage().getOptionsExtras().getText(), OPTION_SELECTED);

        //--step 47
//        //Verify Travel Insurance is display if was purchased on the Booking Path.
//        ValidationUtil.validateTestStep("Verify Travel Insurance Header is displayed",
//                pageObjectManager.getConfirmationPage().getTravelInsuranceHeaderText().getText(),TRAVELINSURANCE_HEADER);
//
//        //verify policy label and number
//        ValidationUtil.validateTestStep("Verify policy number label is displayed",
//                pageObjectManager.getConfirmationPage().getTravelInsuranceSubHeaderText().get(0).getText(),"Policy Number:");
//
//        ValidationUtil.validateTestStep("Verify policy number is correct",
//                pageObjectManager.getConfirmationPage().getTravelInsurancePolicyNumber().getText(), TravelGuardPolicyNumber);
//
//        //verify primary insured name
//        ValidationUtil.validateTestStep("Verify Primary Insured Name label is displayed",
//                pageObjectManager.getConfirmationPage().getTravelInsuranceSubHeaderText().get(1).getText(), "Primary Insured Name:");
//
//        ValidationUtil.validateTestStep("Verify primary insured name",
//                pageObjectManager.getConfirmationPage().getTravelInsurancePrimaryInsuredName().getText(), passengerInfoData.firstName + " " + passengerInfoData.lastName);
//
//        //verify Note
//        ValidationUtil.validateTestStep("Verify travel insurance note: ",
//                pageObjectManager.getConfirmationPage().getTravelInsuranceNoteText().getText(), TRAVELGUARD_NOTE);

        //--step 48 - step 50
        //Verify contact header
        ValidationUtil.validateTestStep("Verify contact header is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getContactHeaderTxt()));

        //verify name
        ValidationUtil.validateTestStep("Verify contact name information",
                pageObjectManager.getConfirmationPage().getContactPersonName().getText(), passengerInfoData.firstName + " " + passengerInfoData.lastName);

        //verify passenger email
        ValidationUtil.validateTestStep("Verify contact emails",
                pageObjectManager.getConfirmationPage().getContactPersonEmail().getText(),loginCredentialsData.userName);

        //verify passenger phone number
        ValidationUtil.validateTestStep("Verify contacts phone number",
                pageObjectManager.getConfirmationPage().getContactPersonPhoneNumber().getText(), PhoneNumber);

        //--step 51
        //Verify "CHECK-IN AND GET BOARDING PASS" button is displayed
        ValidationUtil.validateTestStep("Verify CHECK-IN AND GET BOARDING PASS button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton()));

        //--step 52
        //Verify CANCEL ITINERARY button is displayed
        ValidationUtil.validateTestStep("Verify CANCEL ITINERARY button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getCancelItineraryButton()));
    }

}