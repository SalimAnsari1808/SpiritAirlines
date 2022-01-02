package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91407
//Title       : CP_MT_Itinerary Page_Itinerary Receipt page_9DFC
//Description : Validate Itinerary page on manage travel for 9DFC member
//Created By  : Anthony Cardona
//Created On  : 11-Apr-2019
//Reviewed By : Salim Ansari
//Reviewed On : 26-Apr-2019
//**********************************************************************************************

public class TC91407 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NineDFC" , "Connecting" ,"BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "ReservationUI"})
    public void CP_MT_Itinerary_Page_Itinerary_Receipt_page_9DFC(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91407 under SMOKE Suite on " + platform + " Browser", true);
        }

//Step 1
        //Booking Path Constant variables
        final String LANGUAGE               = "English";
        final String LOGIN_EMAIL            = "NineDFCEmail";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "Oneway";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "NA";
        final String ADULTS                 = "1";
        final String CHILDREN               = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String JOURNEY                = "Dep";
        final String DEP_FLIGHT             = "Connecting";
        final String FARE_TYPE              = "member";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GAURD           = "Notrequired";

        //MyTrip Constant value
        final String MYTRIP_ITINERARY_URL   = "my-trips/itinerary";
        final String MYTRIP_DATE_FORMAT     = "MMMMM d, YYYY";
        final String MYTRIP_STATUS          = "Confirmed";

        //Itnerary Section Constant Value
        final String ITINERARY_FREE_SPIRIT  = "Free Spirit #: ";
        final String PASSENGER_INFO_TYPE    = "NineFCMember";


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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);
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
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//Step 2 and 3
        pageMethodManager.getHomePageMethods().loginToMyTrip();
//Step 4
        //click on print itinerary reciept
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
        ValidationUtil.validateTestStep("User taken to the print itinerary page", getDriver().getCurrentUrl(),MYTRIP_ITINERARY_URL);

//Step 5
        //validate the page header says "Your Itinerary Receipt"
        ValidationUtil.validateTestStep("The header is correct", pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptText().isDisplayed());

//Step 6
        //Validate the print itinerary button
        ValidationUtil.validateTestStep("The print itinerary button is displayed", pageObjectManager.getReservationSummaryPage().getPrintItineraryButton().isDisplayed());

//Step 7

        //validate that the booking date contains current date in MMMMM DD, YYYY format
        for (int count = 0;count<pageObjectManager.getReservationSummaryPage().getFlightSectionDateText().size();count++){
            WebElement element = pageObjectManager.getReservationSummaryPage().getFlightSectionDateText().get(count);
            if (element.isDisplayed()) {
                Date curDate = TestUtil.convertStringToDate(element.getText().trim(),MYTRIP_DATE_FORMAT);
                ValidationUtil.validateTestStep(element.getText().trim()+"The booking date format is correct",!curDate.equals(null));
                System.out.println(curDate);
            }
        }

        //validate status is confirmed
        for(WebElement element : pageObjectManager.getReservationSummaryPage().getStatusText())
        {
            if (element.isDisplayed())
            {
                ValidationUtil.validateTestStep("The booking Status is correct", element.getText(),MYTRIP_STATUS);
                break;
            }
        }

        //validate the booking PNR
        for(WebElement element : pageObjectManager.getReservationSummaryPage().getConfirmationCodeNumberText())
        {
            if (element.isDisplayed())
            {
                ValidationUtil.validateTestStep("The booking confirmation code is correct", element.getText(),scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());
                break;
            }
        }

//Step 8

        for (WebElement routeElement: pageObjectManager.getReservationSummaryPage().getFlightSectionDepartCityText()) {
            if(routeElement.isDisplayed()){
                ValidationUtil.validateTestStep(routeElement.getText().trim()+" City code comes after the Origin and Destination City and time at last also "
                        ,routeElement.getText().trim().matches("(.)*\\s\\(([A-Z]){3}\\)\\s([0-9]){1,2}\\:([0-9]){2}\\s([A-Z]){2}"));
            }
        }


        for(WebElement flightNumberElement: pageObjectManager.getReservationSummaryPage().getFlightSectionFlightNumberText()){
            if(flightNumberElement.isDisplayed()){
                ValidationUtil.validateTestStep(flightNumberElement.getText().trim()+" City code comes after the Origin and Destination City and time at last also "
                        ,flightNumberElement.getText().trim().matches("^Flight:\\s([A-Z0-9]){5}$"));
            }
        }


//Step 9

        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest(PASSENGER_INFO_TYPE);
        //validate all passenger information is displayed
        String paxName = pageObjectManager.getReservationSummaryPage().getPassengerNameText().getText();
        ValidationUtil.validateTestStep("The passenger name is correct" , paxName,
                passengerInfoData.title+" "+passengerInfoData.firstName+" "+passengerInfoData.lastName);
        //validate the FS number is 10 characters long
        String fsNum = pageObjectManager.getReservationSummaryPage().getFreeSpiritNumberText().getText().replace(ITINERARY_FREE_SPIRIT, "").trim();
        ValidationUtil.validateTestStep("The FS number is 10 characters" , fsNum.length() == 10 );

//Step 10
        //skip this step as no options were selected

//Step 11
        String contactName          = pageObjectManager.getReservationSummaryPage().getContactSectionNameText().getText().trim();
        String contactEmail         = pageObjectManager.getReservationSummaryPage().getContactSectionEmailText().getText().trim();

        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(LOGIN_EMAIL);
        //validation of member information
        ValidationUtil.validateTestStep("The Contact Person name is correct" , contactName,passengerInfoData.firstName+" "+passengerInfoData.lastName);
        ValidationUtil.validateTestStep("The Contact Email is correct" , contactEmail,loginCredentialsData.userName);

        String messageExpected      =  "We'll keep you posted about any changes to this trip,\n" +
                "so please double check your email and phone number.";
        String contactInfoMessage   = pageObjectManager.getReservationSummaryPage().getContactSectionVerbageText().getText().trim();

        ValidationUtil.validateTestStep("The Contact message is correct" , contactInfoMessage.contains(messageExpected));

//Step 12
        //Skip this step because travel guard was not purchased

//Step 13
        //Validation of purchase section
        ValidationUtil.validateTestStep("The toatl paid breakdown is displayed" , pageObjectManager.getReservationSummaryPage().getTotalPaidVerbiageLabel().isDisplayed());
        ValidationUtil.validateTestStep("The toatal paid breakdown is displayed" , pageObjectManager.getReservationSummaryPage().getTotalPaidTaxAndCarrierCharge().isDisplayed());


//Step 14
        String thankYou             = "Thank you for choosing Spirit. We look forward to serving you on your upcoming trip!";
        String flightModification   = "For modifications to flight only itineraries, please call 1.801.401.2222";
        String vacationModification = "For modifications to vacation package itineraries please call 1.954.698.0125";
        String feedback             = "To provide feedback, please email support@spirit.com or write to Spirit Customer Relations, 2800 Executive Way, Miramar, FL 33025.";

        List<String> footerVerbage = getDisplayedTextList(pageObjectManager.getReservationSummaryPage().getFooterVerbageText());

        ValidationUtil.validateTestStep("Message at the bottom of the page is correct" , footerVerbage.contains(thankYou) &&  footerVerbage.contains(flightModification)
                &&  footerVerbage.contains(vacationModification) && footerVerbage.contains(feedback));

//Step 15
        //vaidate that there is another print itinrary button at the bottom of the page
        ValidationUtil.validateTestStep("The print itinerary button is displayed", pageObjectManager.getReservationSummaryPage().getPrintItineraryButton().isDisplayed());
    }

    private List<String> getDisplayedTextList(List<WebElement> elementList){
        ArrayList<String> arrayList = new ArrayList<>();
        for (WebElement element:elementList) {
            if(element.isDisplayed()){
                arrayList.add(element.getText().trim());
            }
        }
        return arrayList;
    }
}