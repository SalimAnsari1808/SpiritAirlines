package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.util.List;

//@Listeners(com.spirit.testNG.Listener.class)
//**********************************************************************************************
//TODO: Bug 23384: CP : MT: Change Confirmation page, the header of the page does not say" Change Confirmation", when modifying seats
//Test Case ID: TC91316
//Title       : CP_MT_Itinerary Page_Edit seat(s)
//Description : Edit Seats on Manage Travel
//Created By  : Anthony Cardona
//Created On  : 01-Apr-2019
//Reviewed By : Salim Ansari
//Reviewed On : 08-Apr-2019
//**********************************************************************************************
public class TC91316 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "NonStop" ,"BookIt" ,
            "NoBags" , "BigFrontSeat" , "CheckInOptions" , "MasterCard","AddEditSeats","ConfirmationUI","ReservationUI","ActiveBug"})
    public void CP_MT_Itinerary_Page_Edit_seats(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91316 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Steps 1
        //******************************************************************************
        //*********************Navigate to My Trip Confirmation Page*********************
        //******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "25";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant value
        final String DEP_SEAT           = "Standard";

        //payment page Constant values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Options page constant Values
        final String CHECKIN_OPTION     = "CheckInOption:MobileFree";

        //Reservation Page Constant Values
        final String MYTRIP_BUY_SEATS   = "Seats";
        final String MYTRIP_BAGS_POPUP  = "DontPurchase";

        //My Trip Seat Page
        final String MYTRIP_DEP_SEAT    = "BigFront";

        //open browser
        openBrowser(platform);

//Step 1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Pagec
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(CHECKIN_OPTION);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);


        //Confirmation Page methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//Stepm 3
        pageMethodManager.getHomePageMethods().loginToMyTrip();


        //click on add seat and select any available seat
        //click on add Bags and select any available seat
        ValidationUtil.validateTestStep("Verify Edit Bags Link is diaplyed on Passenger Secion Reservation Summary Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPassengerSectionEditSeatsButton().get(0)));
        //reservation summary common methods
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_BUY_SEATS);

        //My Trip Seat Page Common Methods
        //pageMethodManager.getSeatsPageMethods().selectDepartureSeats("Standard");

//Step 4
        //add big front seat
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(MYTRIP_DEP_SEAT);
        //get the new seat Number
        String newSeatNum = getDriver().findElement(By.xpath("//div[@class='card seat-selection-card']//ul//div[3]//p")).getText().trim();
        System.out.println("Seats page seat number: " +newSeatNum);

//Step 5
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//Step 6
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(MYTRIP_BAGS_POPUP);

//Step 7
        //continue on the extras page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 8
        //input all information on the payment page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);



//Step 9
        //******************************************************************************
        //*********************Validation to My Trip Confirmation Page*********************
        //******************************************************************************/
        //open bug, below test steps will fail 23384(http://miaptfs01.spirit.local/tfs/acapella/Spirit.com%20Remix/_workitems?id=23384&_a=edit&triage=false)
        //TODO up update below code after bug fix

        //validate the page header
//        String headerTxt = getDriver().findElement(By.xpath("(//h1)[1]")).getText();
        //ValidationUtil.validateTestStep("The header after modifying seats is \"Change Confirmation\"", headerTxt.contains("Change Confirmation"));

        //int changeSent = getDriver().findElements(By.xpath("//p[contains(text(),'Your reservation has been changed and an email')]")).size();
        //ValidationUtil.validateTestStep("The textbox with text \"Your reservation has been changed\" is diesplayed",  changeSent > 0 );

//Step 10
        //validate the seat changes were made
        List<WebElement> seatText = getDriver().findElements(By.xpath("//button[contains(text(),'Edit Seats')]/../..//p"));
        String seatNumOnConfirmation = seatText.get(0).getText().substring(10).trim();
        System.out.println(seatNumOnConfirmation);

        //failed here
        ValidationUtil.validateTestStep("The new seat matches the seat that was updated", newSeatNum.contains(seatNumOnConfirmation));

//Step 11
        //Validate the breakdown for the seat total
    }
}
