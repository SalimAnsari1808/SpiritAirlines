package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//**********************************************************************************************
//Test Case ID: TC91314
//Title       : CP_MT_Itinerary Page_Edit bag(s)
//Description : Edit Seats on Manage Travel
//Created By  : Anthony Cardona
//Created On  : 02-Apr- 2019
//Reviewed By : Salim Ansari
//Reviewed On : 08-Apr- 2019
//**********************************************************************************************
public class TC91314 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "NonStop" ,"BookIt" ,
            "CarryOn" , "NoSeats" ,"ShortCutBoarding", "CheckInOptions" , "MasterCard" , "CheckedBags" ,"ReservationUI", "AddEditBags", "Bikes"})
    public void CP_MT_Itinerary_Page_Edit_Bags(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91314 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Steps 1
        //******************************************************************************
        //**************************Navigate to My Trip Bags Page***********************
        //******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
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

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_1|Checked_0";
        final String BAGS_FARE          = "Standard";

        //Options page constant Values
        final String CHECKIN_OPTION     = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //My Trip bags page
        final String MANAGE_TRAVEL_BAGS = "Carry_1|Checked_1";

        //My Trip Reservation page constant value
        final String MYTRIP_BUY_BAGS    = "Bags";
        final String MYTRIP_SEAT_POPUP  = "DontPurchase";

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

        //Bags Page
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

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

//Step 4

        WaitUtil.untilTimeCompleted(2000);

        //click on add Bags and select any available seat
        ValidationUtil.validateTestStep("Verify Edit Bags Link is diaplyed on Passenger Secion Reservation Summary Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPassengerSectionEditBagsButton().get(0)));
        //reservation summary common methods
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_BUY_BAGS);

//Step 5

        //add bags
        WaitUtil.untilPageLoadComplete(getDriver());

        //the validation on method "selectDepartingBags()" does not work on manage travel if previously selected bags on Book path
       pageMethodManager.getBagsPageMethods().selectDepartingBags(MANAGE_TRAVEL_BAGS);
        double checkedBagPrice  = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(0).getText().trim().replace("$",""));


        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(0).click(); //click on sporting equipment drop down

        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(0).click(); //click on bicycle plus button
        double bicyclePrice  = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingBicyclePriceText().get(0).getText().trim().replace("$",""));

//Step 5
        //******************************************************************************
        //**************************Validation to My Trip Bags Page*********************
        //******************************************************************************/
        //validate price and continue to the bags page
        pageObjectManager.getBagsPage().getBagsTotalContainerLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("The Bags total is correct for 1 checked bag on My Trip Bags Page" , pageObjectManager.getBagsPage().getOutboundJourneyBreakdownCheckedBagTotalPriceText().getText().contains("" + checkedBagPrice));
        ValidationUtil.validateTestStep("The Bags total is corect for 1 bicycle on My Trip Bags Page" , pageObjectManager.getBagsPage().getOutboundJourneyBreakdownBikeTotalPriceText().getText().contains("" + bicyclePrice));
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);

//Step 6
        //dont buy seat on seat popup
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(MYTRIP_SEAT_POPUP);

//Step 7
        //continue on the extras page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 8
        //input all information on the payment page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//Step 9

        //open bug, below test steps will fail 23384(http://miaptfs01.spirit.local/tfs/acapella/Spirit.com%20Remix/_workitems?id=23384&_a=edit&triage=false)
        //TODO up update below code after bug fix



//        //validate the page header
//        String headerTxt = getDriver().findElement(By.xpath("(//h1)[1]")).getText();
//        //ValidationUtil.validateTestStep("The header after modifying seats is \"Change Confirmation\"", headerTxt.contains("Change Confirmation"));
//
//        //int changeSent = getDriver().findElements(By.xpath("//p[contains(text(),'Your reservation has been changed and an email')]")).size();
//        //ValidationUtil.validateTestStep("The textbox with text \"Your reservation has been changed\" is diesplayed",  changeSent > 0 );
//
////Step 10
//        //validate the seat changes were made
//        List<WebElement> seatText = getDriver().findElements(By.xpath("//button[contains(text(),'Edit Seats')]/../..//p"));
//        String seatNumOnConfirmation = seatText.get(0).getText().substring(10).trim();
//        System.out.println(seatNumOnConfirmation);

//        //failed here
//        ValidationUtil.validateTestStep("The new seat matches the seat that was updated", newSeatNum.contains(seatNumOnConfirmation));

//Step 11
//        Validate the breakdown for the seat total
    }
}