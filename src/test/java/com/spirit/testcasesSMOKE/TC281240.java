package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


//**********************************************************************************************
//Test Case ID: TC281240
//Title       : 746. E2E_MT_9DFC_ OW DOM Multi ADT add Flight Flex_Flight Booked out of 7 days_New flight -$ chosen_No Bags_No Seats_No Extras_Customer Receives reservation credit
//Description :
//Created By  : Anthony Cardona
//Created On  : 26-Apr-2019
//Reviewed By :
//Reviewed On :
//**********************************************************************************************
public class TC281240 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"MyTrips","OneWay","DomesticDomestic","Within21Days","Adult","NineDFC","Connecting","BookIt",
                    "NoBags" , "NoSeats" , "CheckInOptions" , "ChangeFlight","FlightFlex"})
    public void  TC_746_E2E_MT_9DFC(@Optional("NA") String platform) {
        // public void  TC_746_E2E_MT_9DFC_OW_DOM_Multi_ADT_add_Flight_Flex_Flight_Booked_out_of_7_days_New_flight_$_chosen_No_Bags_No_Seats_No_Extras_Customer_Receives_reservation_credit(@Optional("NA") String platform) {
        //*************************Navigate to Confirmation Page**********************
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281240 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_EMAIL        = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "BWI";
        final String DEP_DATE           = "10";
        final String ARR_DATE           = "NA";
        final String ADULT              = "3";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Member";
        final String UPGRADEVALUE       = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "FlightFlex|CheckInOption:MobileFree";
//        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);


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

        //Reach to Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

        pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox().sendKeys("123");
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Home Page ManageTravel
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Reservation Summary Page, Click on change Flight
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());//wait till pop up appear on page

        //Edit Flight Pop-up and continue
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep","NA","NA","30");
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

//        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();//click on Departing edit box
//        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();//click on continue button

        //select less expensive 9FC flight
//        WaitUtil.untilPageLoadComplete(getDriver());
//        WaitUtil.untilTimeCompleted(1200);
//        manageTravel_SelectLessExpensiveFlight("club");
//        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("dep","9DFC","Cheap");
        WaitUtil.untilPageLoadComplete(getDriver());

        //Selecting continue
        pageObjectManager.getFlightAvailabilityPage().getMemberFareButton().click();

        //dont purchase bags
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup("DontPurchase");

        //dont purchase Seats
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup("DontPurchase");

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());

        //click on accept terms and conditions
        pageObjectManager.getPaymentPage().getTermsAndConditionsLabel().click();
        
        //click continue at the bottom of the payment page
        pageObjectManager.getPaymentPage().getManageTravelContinueButton().click();

        WaitUtil.untilTimeCompleted(1200);
//        if(pageObjectManager.getPaymentPage().getNoTravelGuardPopupLabel().isDisplayed()) {
            if(TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getNoTravelGuardPopupLabel())) {
            //not require TG
            pageObjectManager.getPaymentPage().getNoTravelGuardPopupLabel().click();
        }

        //validate that the uer is taken to the confirmation  page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user is takem to the Change Confirmation page" , getDriver().getCurrentUrl().contains("confirmation"));

        //Validate that the user is not charged for the flight Cancellation
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();//open breakdown
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("The change fee is not displayed on the Total Paid Breakdown Total" ,
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueChangeFeesPriceText()));

    }

    /**
     * This method clicks on a Less Expensive (noted by price of ($0.00)) fare  that is of paramater fareType
     * @param fareType String: "club" , "standard", or "card-holder"
     * @author Anthony Cardona <BR>
     * Created on 21 Mar, 2019
     * Reviewed By:
     * Reviewed Date:
     */
    private void manageTravel_SelectLessExpensiveFlight(String fareType)
    {
        WaitUtil.untilTimeCompleted(1500);

        fareType = fareType.toLowerCase();
        if (fareType.contains("card")) fareType = "card-holder"; //if faretype is card, change faretype to "card-holder"

        WaitUtil.untilPageLoadComplete(getDriver());
        List<WebElement> TempallStandardsFares = getDriver().findElements(By.xpath("//label[contains(@for,'" + fareType + "')]"));


        List<WebElement> allStandardFares = new ArrayList<>();
        for(WebElement element : TempallStandardsFares)
        {
            if (element.isDisplayed())
            {
                allStandardFares.add(element);
            }
        }

        for(WebElement fare : allStandardFares)
        {
            if (fare.getText().contains("-"))// if label contains + it is more expensive than original flight
            {
                fare.click();
                break;
            }
        }
    }
}