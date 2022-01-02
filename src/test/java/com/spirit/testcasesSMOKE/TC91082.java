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

//**********************************************************************************************
//Test Case ID: TC91082
//Title       : CP_MT_Payment_Travel_Guard_Non_Approved_TRM_State
//Description : Validate Travel Guard Text is not displayed for residents of New York, Pennsylvania, Washington
//Created By  : Anthony Cardona
//Created On  : 08-Apr-2019
//Reviewed By : Salim Ansari
//Reviewed On : 23-Apr-2019
//**********************************************************************************************
public class TC91082 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "ContactInformation" , "CarryOn" , "ShortCutBoarding","CheckedBags", "NoSeats" , "CheckInOptions" , "MasterCard" , "AddEditBags","PaymentUI"})
    public void CP_MT_Payment_Travel_Guard_Non_Approved_TRM_State(@Optional("NA") String platform) {

        /******************************************************************************
         ************************Navigate to Check-In Payment Page*********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91082 under SMOKE Suite on " + platform + " Browser", true);
        }

//Step 1
        //create a booking for member from NewYork
        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE                  = "Oneway";
        final String DEP_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "LGA";
        final String DEP_DATE                   = "25";
        final String ARR_DATE                   = "NA";
        final String ADULTS                     = "1";
        final String CHILDREN                   = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		        = "nonstop";
        final String JOURNEY                    = "Dep";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";

        //Passenger Page Constant
        final String USER_STATE                 = "New York";

        //Options page constant Values
        final String CHECKIN_OPTION             = "CheckInOption:MobileFree";
        final String SHORTCUT_OPTION            = "ShortCutBoarding";

        //payment page constant value
        final String CARD_TYPE                  = "MasterCard";
        final String TRAVEL_GAURD               = "NotRequired";

        //reservation summary constant value
        final String MYTRIP_SEAT_POPUP          = "DontPurchase";
        final String MYTRIP_BAG                 = "Bags";
        final String MYTRIP_BAG_SELECTION       = "Carry_1|Checked_1";
        final String MYTRIP_BAG_FARE_TYPE       = "Standard";
        final int FIRST_INDEX                   = 0;

        //open browser
        openBrowser(platform);

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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getContactPersonStateDropDown(),USER_STATE);
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(CHECKIN_OPTION);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);

        //Confirmation Page methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Go to manage travel
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //click on add Bags and select any available seat
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_BAG);

        //add bags
        pageMethodManager.getBagsPageMethods().selectDepartingBags(MYTRIP_BAG_SELECTION); //click on checked bags plus button
        double checkedBagPrice  = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(FIRST_INDEX).getText().trim().replace("$",""));
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(FIRST_INDEX).click(); //click on sporting equipment drop down
        WaitUtil.untilTimeCompleted(1200);

        //validate price and continue to the bags page
        pageObjectManager.getBagsPage().getBagsTotalContainerLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("The Bags total is correct for 1 checked bag" , pageObjectManager.getBagsPage().getOutboundJourneyBreakdownCheckedBagTotalPriceText().getText(),""+checkedBagPrice);
        pageMethodManager.getBagsPageMethods().selectBagsFare(MYTRIP_BAG_FARE_TYPE);

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(MYTRIP_SEAT_POPUP);

        //add options to get to the payment page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ************************Validation on Check-In Payment Page*******************
         ******************************************************************************/

//Step 4
        ///////////validation that the travel Guard text is not displayed
        String[] textBeingValidated =
                {
                        "100% Trip Cost Cancellation",
                        "125% Trip Cost Trip Interruption",
                        "$500 Missed Connection",
                        "$500 Trip Delay",
                        "$500 Baggage & Personal Effects"
                };

        for(String element : textBeingValidated)
        {
            try
            {
                WebElement tg_Text = getDriver().findElement(By.xpath("//div[@class='insurance-container']//p[contains(text(),'" + element + "')]"));
                ValidationUtil.validateTestStep("" , false); //text should not be dislpayed: fail
            }
            catch (Exception e)
            {
                ValidationUtil.validateTestStep("Travel Guard text \" " + element + "\" is supressed for New York Resident: pass", true);
            }
        }

//Step 5 does not apply
    }
}