package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.enums.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.text.*;
import java.util.Date;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91397
//Description: CP_MT_Itinerary Page_Wireframe_Itinerary Receipt page
//Created By: Sunny Sok
//Created On: 30-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 05-AUG-2019
//**********************************************************************************************

public class TC91397 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NineDFC" , "NonStop" , "BookIt" , "CarryOn" , "CheckedBags" , "Standard" ,"ShortCutBoarding", "CheckInOptions" , "MasterCard" , "TravelInsurancePopUp" , "ReservationUI","ItineraryReceiptUI"})
    public void CP_MT_Itinerary_Page_Wireframe_Itinerary_Receipt_page(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91397 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
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
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "8";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "9DFC";
        final String ARR_Flight         = "9DFC";
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
         *********************Navigate to Reservation summary page*********************
         ******************************************************************************/
        //open browser
        openBrowser(platform);

        //--step 1
        //Access the SkySales (Web) test environment.
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Ret", ARR_Flight);
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
        String DepBags = pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(0).getAttribute("value") + " Carry-On, " +
                pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(0).getAttribute("value") + " Checked Bag";
        String RetBags = pageObjectManager.getBagsPage().getReturningCarryOnCounterTextBox().get(0).getAttribute("value") + " Carry-On, " +
                pageObjectManager.getBagsPage().getReturningCheckedBagCounterTextBox().get(0).getAttribute("value") + " Checked Bag";

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
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        // closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //storing pnr
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(), BOOKING_STATUS);

        String TravelGuardPolicyNumber = pageObjectManager.getConfirmationPage().getTravelInsurancePolicyNumber().getText();
        String FareClubSavings = pageObjectManager.getConfirmationPage().get9FCBookingSavingsAmount().getText();

        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(2000);
        String TravelGuardPriceamount = pageObjectManager.getPaymentPage().getTotalDueTravelInsurancePriceText().getText();

        //Manage Travel Path
        //--step 2
        //Select the My trips tab from the search widget on Home Page:
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());

        //reservation Summary URL
        final String RESERVATION_URL    = "my-trips/reservation-summary";

        //verify user on Reservation summary Page
        ValidationUtil.validateTestStep("Verify user navigated to Manage Travel Reservation Summary Page,",
                getDriver().getCurrentUrl(),RESERVATION_URL);

        //--step 3
        //click on the PRINT RECEIPT link.
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        /******************************************************************************
         ************************Validation Itinerary Receipt page********************
         ******************************************************************************/

        //Constant Values to validate
        final String ITINERARY_URL          = "my-trips/itinerary";
        final String OPTION_HEADER          = "Options";
        final String TRAVELINSURANCE_HEADER = "Travel Insurance";
        final String OPTION_SELECTED        = "Shortcut Boarding\n" + "Zone 2 priority boarding and early access to the overhead bins";


        //verify user on Navigated to Itinerary Receipt page
        ValidationUtil.validateTestStep("Verify user navigated to Manage Travel Reservation Summary Page,",
                getDriver().getCurrentUrl(), ITINERARY_URL);

        //--step 4
        //verify The title of the page is Your Itinerary Receipt
        ValidationUtil.validateTestStep("verify The title of the page is Your Itinerary Receipt",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptText()));

        //--step 5
        //Verify Right aligned to the header of the page the  PRINT CONFIRMATION button is displayed.
        ValidationUtil.validateTestStep("verify The title of the page is Your Itinerary Receipt",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPrintItineraryButton()));

        //Verify When clicked the button, user should be redirected to their computer's printer settings.
        /**Print Itinerary Button doesnt work**/

        //--step 6
        //verify Booking Date: format is Month DD, YYYY
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, y");
        ValidationUtil.validateTestStep("verify Booking Date and format",
                !(TestUtil.convertStringToDate(pageObjectManager.getConfirmationPage().getBookingSectionTopDateText().get(0).getText().replace("Booking Date: ", "").trim(),
                        "MMMM d, y")==null));

        //verify Status: Confirmed
        ValidationUtil.validateTestStep("Verify booking Status: Confirmed",
                pageObjectManager.getReservationSummaryPage().getStatusText().get(0).getText(),"Status: " + BOOKING_STATUS);

        ValidationUtil.validateTestStep("verify Confirmation Code :PNR #",
                pageObjectManager.getReservationSummaryPage().getConfirmationCodeNumberText().get(0).getText(),
                "Confirmation Code: " + scenarioContext.getContext(Context.CONFIRMATION_CODE));

        //--step 7
        //verify First line East facing airplane icon Date format Month DD, YYYY
        ValidationUtil.validateTestStep("verify First line East facing airplane is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getFlightSectionDateText().get(0).findElement(By.tagName("i"))));

        String [] Dates = Date.split("\\-");

        Date DepdateTemp = TestUtil.convertStringToDate(Dates[0],"MM/d/y");
        String Depdate = dateFormat.format(DepdateTemp);

        Date ArrdateTemp = TestUtil.convertStringToDate(Dates[1],"MM/d/y");
        String Retdate = dateFormat.format(ArrdateTemp);

        String [] DepFlightDetails = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split("\\|");
        String [] RetFlightDetails = scenarioContext.getContext(Context.AVAILABILITY_RET_FLIGHT_DETAILS).toString().split("\\|");

        ValidationUtil.validateTestStep("Verify Departure date",
                pageObjectManager.getReservationSummaryPage().getFlightSectionDateText().get(0).getText(), Depdate);

        //verify Second line Depart: city then abbreviation in parentheses. Time format ##:## AM or PM
        ValidationUtil.validateTestStep("Verify Second line Depart: city then abbreviation in parentheses and Time",
                pageObjectManager.getReservationSummaryPage().getFlightSectionDepartCityText().get(0).getText(),"Depart: " +
                        DepFlightDetails[2].replace("DepartureAirport:","") + " " + DepFlightDetails[4].replace("DepartureTime:",""));

        //verify Arrive: city then abbreviation in parentheses. Time format ##:## AM or PM
        ValidationUtil.validateTestStep("verify Arrive: city then abbreviation in parentheses and Time",
                pageObjectManager.getReservationSummaryPage().getFlightSectionAriveCityText().get(0).getText(), "Arrive: " +
                        DepFlightDetails[3].replace("ArrivalAirport:","") + " " + DepFlightDetails[5].replace("ArrivalTime:", ""));

        //verify Flight Number
        ValidationUtil.validateTestStep("Verify Flight number",
                pageObjectManager.getReservationSummaryPage().getFlightSectionFlightNumberText().get(0).getText(),
                DepFlightDetails[0].replace("Number",""));

        //Verifying Return flight
        ValidationUtil.validateTestStep("Verify Departure date",
                pageObjectManager.getReservationSummaryPage().getFlightSectionDateText().get(1).getText(), Retdate);

        //verify Second line Depart: city then abbreviation in parentheses. Time format ##:## AM or PM
        ValidationUtil.validateTestStep("Verify Second line Depart: city then abbreviation in parentheses and Time",
                pageObjectManager.getReservationSummaryPage().getFlightSectionDepartCityText().get(1).getText(),"Depart: " +
                        RetFlightDetails[2].replace("DepartureAirport:","") + " " + RetFlightDetails[4].replace("DepartureTime:",""));

        //verify Arrive: city then abbreviation in parentheses. Time format ##:## AM or PM
        ValidationUtil.validateTestStep("verify Arrive: city then abbreviation in parentheses and Time",
                pageObjectManager.getReservationSummaryPage().getFlightSectionAriveCityText().get(1).getText(), "Arrive: " +
                        RetFlightDetails[3].replace("ArrivalAirport:","") + " " + RetFlightDetails[5].replace("ArrivalTime:", ""));

        //verify Flight Number
        ValidationUtil.validateTestStep("Verify Flight number",
                pageObjectManager.getReservationSummaryPage().getFlightSectionFlightNumberText().get(1).getText(),
                RetFlightDetails[0].replace("Number",""));

        //--step 8
        //verify Left aligned heading Passengers
        ValidationUtil.validateTestStep("verify Header Passengers label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getPassengerHeaderTxt()));

        //verify person icon
        ValidationUtil.validateTestStep("verify Person icon is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getPassengerIconImg()));

        //verify Name format Title First name Last name
        ValidationUtil.validateTestStep("verify First name and last name",
                pageObjectManager.getConfirmationPage().getPassengerSectionNamesText().get(0).getText(),
                passengerInfoData.title + passengerInfoData.firstName + " " + passengerInfoData.lastName );

        //verify FREE SPIRIT #: 10 digits displayed if provided. If not provided is it - a dash
        ValidationUtil.validateTestStep("verify FREE SPIRIT # is displayed",
                pageObjectManager.getConfirmationPage().getPassengerFreeSpirirtNumberText().get(0).getText(), "Free Spirit #: " + passengerInfoData.fsNumber);

        //verify Known Traveler # (TSA Pre check mark): displayed if applicable
        ValidationUtil.validateTestStep("verify KTN #",
                pageObjectManager.getConfirmationPage().PassengerKTN(), passengerInfoData.ktNumber);

        //verify Suitcase icon city abbreviation: format is depart - arrival all capital letters # Carry-On, # Checked
        ValidationUtil.validateTestStep("verify suitcase icon is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getBagsIcon().get(0)));

        ValidationUtil.validateTestStep("verify bags for Depart - Arrival" ,
                pageObjectManager.getConfirmationPage().getDepBagsTxt().get(0).getText(), DEP_AIRPORT_CODE + "-" + ARR_AIRPORT_CODE + ":" + DepBags);

        //verify  city abbreviation: format is arrival - depart all capital letters # Carry-On, # Checked
        ValidationUtil.validateTestStep("verify bags for Arrival- Departure" ,
                pageObjectManager.getConfirmationPage().getRetBagsTxt().get(0).getText(), ARR_AIRPORT_CODE + "-" + DEP_AIRPORT_CODE + ":" + RetBags);

        //verify Seat icon city abbreviation: format is depart - arrival all capital letters seat information format is ##letter
        ValidationUtil.validateTestStep("verify Seat icon is displayed",TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getSeatsIcon()));

        //verify city abbreviation: format is arrival - depart all capital letters seat information format is ##letter
        ValidationUtil.validateTestStep("verify arrival - depart city pair and seat information",
                pageObjectManager.getConfirmationPage().getDepSeatsTxt().get(0).getText(),DEP_AIRPORT_CODE + "-" + ARR_AIRPORT_CODE + ":" + DepSeat);

        ValidationUtil.validateTestStep("verify depart - arrival city pair and seat information",
                pageObjectManager.getConfirmationPage().getRetSeatsTxt().get(0).getText(), ARR_AIRPORT_CODE + "-" +  DEP_AIRPORT_CODE +  ":" + ArrSeat);

        //--step 9
        //verify Section title Options only display if a customer purchased
        ValidationUtil.validateTestStep("verify Options header is displayed",
                pageObjectManager.getReservationSummaryPage().getOptionsSectionHeaderTxt().getText(), OPTION_HEADER);

        //verify options
        ValidationUtil.validateTestStep("verify extras selected is displayed",
                pageObjectManager.getReservationSummaryPage().getOptionsExtras().getText(), OPTION_SELECTED);

        //--step 10
        //Verify Travel Insurance is display if was purchased on the Booking Path.
        ValidationUtil.validateTestStep("Verify Travel Insurance Header is displayed",
                pageObjectManager.getConfirmationPage().getTravelInsuranceHeaderText().getText(),TRAVELINSURANCE_HEADER);

        //verify policy label and number
        ValidationUtil.validateTestStep("verify policy number label is displayed",
                pageObjectManager.getConfirmationPage().getTravelInsuranceSubHeaderText().get(0).getText(),"Policy Number:");

        ValidationUtil.validateTestStep("verify policy number is correct",
                pageObjectManager.getConfirmationPage().getTravelInsurancePolicyNumber().getText(), TravelGuardPolicyNumber);

        //verify primary insured name
        ValidationUtil.validateTestStep("verify Primary Insured Name label is displayed",
                pageObjectManager.getConfirmationPage().getTravelInsuranceSubHeaderText().get(1).getText(), "Primary Insured Name:");

        ValidationUtil.validateTestStep("Verify primary insured name",
                pageObjectManager.getConfirmationPage().getTravelInsurancePrimaryInsuredName().getText(),
                passengerInfoData.firstName + " " + passengerInfoData.lastName);
        //verify Note
        ValidationUtil.validateTestStep("verify travel insurance note: ",
                pageObjectManager.getConfirmationPage().getTravelInsuranceNoteText().getText(),
                "NOTE: View your policy online . For additional questions about your policy please contact Travel Guard directly at 866-877-3191.");

        //--step 11
        //Verify contact header
        ValidationUtil.validateTestStep("verify contact header is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getContactHeaderTxt()));

        //verify name
        ValidationUtil.validateTestStep("verify contact name information",
                pageObjectManager.getConfirmationPage().getContactPersonName().getText(), passengerInfoData.firstName + " " + passengerInfoData.lastName);

        //verify passenger email
        //get the login credentials from Json file
        LoginCredentialsData loginCredentialsData =FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(LOGIN_EMAIL);
        ValidationUtil.validateTestStep("verify contact emails",
                pageObjectManager.getConfirmationPage().getContactPersonEmail().getText(),loginCredentialsData.userName);

        //verify passenger phone number
        ValidationUtil.validateTestStep("verify contacts phone number",
                pageObjectManager.getConfirmationPage().getContactPersonPhoneNumber().getText(), PhoneNumber);

        //--step 12
        //verify 9fc Saving banner
        ValidationUtil.validateTestStep("verify 9fc Saving banner",
                pageObjectManager.getConfirmationPage().get9FCBookingSavingVerbageText().getText(),"$9 FARE CLUB SAVINGS");

        //verify 9fc savings amount
        ValidationUtil.validateTestStep("verify 9fc Saving Amount",
                pageObjectManager.getConfirmationPage().get9FCBookingSavingsAmount().getText(),FareClubSavings);

        //--step 13
        //verify Header of the content box: Total Paid
        ValidationUtil.validateTestStep("verify Total paid is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getConfirmationPage().getTotalPaidVerbiageLabel()));

        //verify Display format: everything that is not the ‘Flight’ price under ‘Taxes & Carrier Charges’.
        ValidationUtil.validateTestStep("verify flight label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueFlightText()));

        ValidationUtil.validateTestStep("verify flight Price is displayed",
                pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText(),"$" +
                        scenarioContext.getContext(Context.AVAILABILITY_9DFC_TOTAL_PRICE).toString());

        ValidationUtil.validateTestStep("verify Bags label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueBagsText()));

        ValidationUtil.validateTestStep("verify flight Price is displayed",
                pageObjectManager.getPaymentPage().getTotalDueBagsPriceText().getText(),"$" + scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString());

        ValidationUtil.validateTestStep("verify Seats label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueSeatsText()));

        ValidationUtil.validateTestStep("verify Seats Price is displayed",
                pageObjectManager.getPaymentPage().getTotalDueSeatsPriceText().getText(),"$" + scenarioContext.getContext(Context.SEATS_TOTAL_PRICE).toString());

        ValidationUtil.validateTestStep("verify Options label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueOptionsText()));

        ValidationUtil.validateTestStep("verify Otpions Price is displayed",
                pageObjectManager.getPaymentPage().getTotalDueOptionsPriceText().getText(),"$0.00");

        ValidationUtil.validateTestStep("verify Travel Insurance label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueTravelInsuranceText()));

        ValidationUtil.validateTestStep("verify Travel Insurance Price is displayed",
                pageObjectManager.getPaymentPage().getTotalDueTravelInsurancePriceText().getText(),TravelGuardPriceamount);

        //--step 14
        //verify At the bottom of the page the following verbiage should be displayed:
        //Thank you for choosing Spirit. We look forward to serving you on your upcoming trip!
        //For modifications to flight only itineraries, please call 1.801.401.2222
        //For modifications to vacation package itineraries please call 1.954.698.0125.
        //To provide feedback, please email support@spirit.com or write to Spirit Customer Relations, 2800 Executive Way, Miramar, FL 33025.

        String ThankYou = "Thank you for choosing Spirit. We look forward to serving you on your upcoming trip!";
        String FlightModification = "For modifications to flight only itineraries, please call 1.801.401.2222";
        String VacationModication = "For modifications to vacation package itineraries please call 1.954.698.0125";
        String FeedBack = "To provide feedback, please email support@spirit.com or write to Spirit Customer Relations, 2800 Executive Way, Miramar, FL 33025.";

        List<WebElement> ContactInfo = pageObjectManager.getReservationSummaryPage().getFooterVerbageText();
        for(int count = 0 ;  count < ContactInfo.size() ; count ++) {
            String CantactVerbiage[] = new String[]{ThankYou, FlightModification, VacationModication, FeedBack};
            ValidationUtil.validateTestStep("Verifying Contact information verbiage" + CantactVerbiage[count],
                    ContactInfo.get(count).getText(), CantactVerbiage[count]);
        }
        //--step 15
        //At bottom of the page Right aligned verify that the button  PRINT PAGE is present.
        ValidationUtil.validateTestStep("The Print Itinerary button is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPrintItineraryButton()));
    }
}