package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91524
//Description: Task 24813: 35245 Flight Availability_CP_BP_FS member booking $9FC flights gets signed up for $9fc membership
//Created By : Gabriela
//Created On : 26-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-Jul-2019
//**********************************************************************************************
public class TC91524 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"Footer","BagOTron","BookPath","RoundTrip","WithIn7Days","Adult","NineDFC","DomesticDomestic",
                    "Connecting","NonStop","BookIt","CarryOn","CheckedBags","NoSeats","ShortCutBoarding","CheckInOptions",
                    "MasterCard","BagsUI","FlightAvailabilityUI"})
    public void Flight_Availability_CP_BP_F_member_booking_$9FC_flights_gets_signed_up_for_$9fc_membership(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91524 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String TRIP_TYPE          = "NewTrip";
        final String DEP_AIRPORT_CODE   = "DEN";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "5";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DISPLAY_PRICE      = "DisplayBagPrice";
        final String BAGS_PATH          = "BookingPath";
        final String BAGS_PRICE         = "Member";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Values
        final String BAGS_URL           = "book/bags";
        final String EACH_BAG_PRICE     = "EachBagPrice";
        final String DEP_BAGS           = "Carry_1|Checked_1";
        final String RET_BAGS           = "Carry_1|Checked_1";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String OPTION_MEMBER      = " $9 Fare Club Membership";
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //click on footer Member enrollment link
        pageObjectManager.getFooter().getFreeSpiritLink().click();

        //New FS member
        pageMethodManager.getMemberEnrollmentPageMethods().createNewFSMember();

        //BAck to Home Page
        pageObjectManager.getHeader().getSpiritLogoImage().click();
        WaitUtil.untilPageLoadComplete(getDriver(), (long) 500);

        //Storing bgs 9fc prices for future comparision
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getHomePage().getOptionalServiceLink().click();
        WaitUtil.untilPageLoadComplete(getDriver(), (long) 500);

        //Bag-O-Tron Page Methods
        pageMethodManager.getOptionalServicesPageMethods().selectTrip(TRIP_TYPE);
        pageMethodManager.getOptionalServicesPageMethods().selectAirports(DEP_AIRPORT_CODE,ARR_AIRPORT_CODE);
        pageMethodManager.getOptionalServicesPageMethods().selectDates(DEP_DATE,ARR_DATE);
        pageMethodManager.getOptionalServicesPageMethods().selectTravellingPassenger(ADULT,CHILD,INFANT_LAP,INFANT_SEAT);
        pageMethodManager.getOptionalServicesPageMethods().selectBagoTronButton(DISPLAY_PRICE);
        pageMethodManager.getOptionalServicesPageMethods().setBagOTronBagPrices("Dep",BAGS_PATH,BAGS_PRICE);
        pageMethodManager.getOptionalServicesPageMethods().setBagOTronBagPrices("Ret",BAGS_PATH,BAGS_PRICE);
        pageMethodManager.getOptionalServicesPageMethods().selectBookTrip();

        //*******************Flight Availability Page Methods*************************/
//-- Step 3: On flight availability page, select a $9FC price for the outbound and a Standard Price for the returning flights or vice versa
        //9FC Fare for departing flight
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep","9DFC");

        //FS Fare for returning flight
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret","Nonstop");

//-- Step 4: Verify $9 Fare Club Fare and Standard Fare tiles are present at the bottom of the page.
        ValidationUtil.validateTestStep("$9FC container is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getMemberFareButton()));

        ValidationUtil.validateTestStep("Standard container is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getStandardFareButton()));

        //Recording Departing Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityName    = new ArrayList<>();
        List<String> arrCityName    = new ArrayList<>();
        List<String> depTime        = new ArrayList<>();
        List<String> arrTime        = new ArrayList<>();
        List<String> nkInfo         = new ArrayList<>();

        //Storing Departure Cities Name for 1st journey
        for(WebElement depCity:pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText()){
            depCityName.add(depCity.getText().trim());
        }

        //Storing Arrival Cities Name for 1st journey
        for(WebElement arrcity: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText()){
            arrCityName.add(arrcity.getText().trim());
        }

        //Storing Departure Cities Time for 1st journey
        for(WebElement depTim: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText()){
            depTime.add(depTim.getText().trim());
        }

        //Storing Arrival Cities Time for 1st journey
        for (WebElement arTime: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText()){
            arrTime.add(arTime.getText().trim());
        }

        //Storing NK Number for 1st journey
        for (WebElement flightNumber: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText()){
            nkInfo.add(flightNumber.getText().trim());
        }

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //Recording Returning Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSelectedReturningFlightNatureButton().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        //Storing Departure Cities Name for 2nd journey
        for(WebElement depCity:pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText()){
            depCityName.add(depCity.getText().trim());
        }

        //Storing Arrival Cities Name for 2nd journey
        for(WebElement arrcity: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText()){
            arrCityName.add(arrcity.getText().trim());
        }

        //Storing Departure Cities Time for 2nd journey
        for(WebElement depTim: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText()){
            depTime.add(depTim.getText().trim());
        }

        //Storing Arrival Cities Time for 2nd journey
        for (WebElement arTime: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText()){
            arrTime.add(arTime.getText().trim());
        }

        //Storing NK Number for 2nd journey
        for (WebElement flightNumber: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText()){
            nkInfo.add(flightNumber.getText().trim());
        }

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

//-- Step 5: Click Continue on the $9 Fare Club Fare tile
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getFlightAvailabilityPage().getMemberFareButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 7: Select "Book It"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //*******************Passenger Information Page Methods*************************/
//-- Step 8: Enter Customer Information and proceed to the next page
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //******************************Bags Page Methods********************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Bags Page URL",
                getDriver().getCurrentUrl(), BAGS_URL);

//-- Step 9: Verify the bags prices displaying are accurate with $9FC bag prices [Use bag-o-tron to compare]
        String depBagPrice = pageMethodManager.getOptionalServicesPageMethods().getBagOTronBagPrice("Dep",EACH_BAG_PRICE,"0","0");
        //declare constant used in validation
        final String FS_1_CARRY_BAG_DEP 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(depBagPrice.split("\\|")[0]));
        final String FS_1_CHECKED_BAG_DEP 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(depBagPrice.split("\\|")[1]));
        final String FS_2_CHECKED_BAG_DEP 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(depBagPrice.split("\\|")[2]));
        final String FS_3_CHECKED_BAG_DEP   = TestUtil.convertTo2DecimalValue(Double.parseDouble(depBagPrice.split("\\|")[3]));
        final String FS_4_CHECKED_BAG_DEP 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(depBagPrice.split("\\|")[4]));
        final String FS_5_CHECKED_BAG_DEP 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(depBagPrice.split("\\|")[5]));

        //Verifying Departing Carry on
        ValidationUtil.validateTestStep("verify 1 Departing Carry on Bags Price",
                pageObjectManager.getBagsPage().getDepartingNextCarryOnPriceText().get(0).getText().toString(),FS_1_CARRY_BAG_DEP );

        //Verify 1 Departing checked bag
        ValidationUtil.validateTestStep("verify 1 Departing Checked Bags Price",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().toString(),FS_1_CHECKED_BAG_DEP  );
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 2 Departing checked bag
        ValidationUtil.validateTestStep("verify 2 Departing Checked Bags Price",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().toString(),FS_2_CHECKED_BAG_DEP  );
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 3 Departing checked bag
        ValidationUtil.validateTestStep("verify 3 Departing Checked Bags Price",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().toString(),FS_3_CHECKED_BAG_DEP  );
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 4 Departing checked bag
        ValidationUtil.validateTestStep("verify 4 Departing Checked Bags Price",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().toString(),FS_4_CHECKED_BAG_DEP  );
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 5 Departing checked bag
        ValidationUtil.validateTestStep("verify 5 Departing Checked Bags Price",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().toString(),FS_5_CHECKED_BAG_DEP  );

        String retBagPrice = pageMethodManager.getOptionalServicesPageMethods().getBagOTronBagPrice("Ret",EACH_BAG_PRICE,"0","0");
        //declare constant used in validation
        final String FS_1_CARRY_BAG_RET 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(retBagPrice.split("\\|")[0]));
        final String FS_1_CHECKED_BAG_RET 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(retBagPrice.split("\\|")[1]));
        final String FS_2_CHECKED_BAG_RET 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(retBagPrice.split("\\|")[2]));
        final String FS_3_CHECKED_BAG_RET   = TestUtil.convertTo2DecimalValue(Double.parseDouble(retBagPrice.split("\\|")[3]));
        final String FS_4_CHECKED_BAG_RET 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(retBagPrice.split("\\|")[4]));
        final String FS_5_CHECKED_BAG_RET 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(retBagPrice.split("\\|")[5]));

        //Verifying Returning Carry on
        ValidationUtil.validateTestStep("verify 1 Returning Carry on Bags Price",
                pageObjectManager.getBagsPage().getDepartingNextCarryOnPriceText().get(0).getText(),FS_1_CARRY_BAG_DEP );

        //Verify 1 Returning checked bag
        ValidationUtil.validateTestStep("verify 1 Returning Checked Bags Price",
                pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(0).getText(),FS_1_CHECKED_BAG_RET  );
        pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(0).click();

        //Verify 2 Returning checked bag
        ValidationUtil.validateTestStep("verify 2 Returning Checked Bags Price",
                pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(0).getText(),FS_2_CHECKED_BAG_RET  );
        //add one checked bag
        pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(0).click();

        //Verify 3 Returning checked bag
        ValidationUtil.validateTestStep("verify 3 Returning Checked Bags Price",
                pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(0).getText(),FS_3_CHECKED_BAG_RET  );
        //add one checked bag
        pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(0).click();

        //Verify 4 Returning checked bag
        ValidationUtil.validateTestStep("verify 4 Returning Checked Bags Price",
                pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(0).getText(),FS_4_CHECKED_BAG_RET  );
        pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(0).click();

        //Verify 5 Returning checked bag
        ValidationUtil.validateTestStep("verify 5 Returning Checked Bags Price",
                pageObjectManager.getBagsPage().getReturningNextCheckedBagPriceText().get(0).getText(),FS_5_CHECKED_BAG_RET  );

//-- Step 10: Select 1 Carry On and 1 Checked Bag, then continue to the next page
        for (int i = 0; i < 4; i ++) {
            pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(0).click();
        }
        for (int i = 0; i < 4; i ++) {
            pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(0).click();
        }

        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //******************************Seats Page Methods********************************/
//-- Step 11: Click "Continue without seats"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //******************************Options Page Methods********************************/
//-- Step 12: Do not add any additional Extras, select Web Check in and continue with the booking process
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //******************************Payment Page Methods********************************/
//-- Step 13: Validate Flight and Bags prices match what was selected on steps 3 and 9
        //Recording Dep and Arr info for validation on Original Itinerary section
        List<String>  depCityNamePayment = getDisplayedText(pageObjectManager.getPaymentPage().getOriginalItineraryDepartingFlightCityNameText());
        List<String>  arrCityNamePayment = getDisplayedText(pageObjectManager.getPaymentPage().getOriginalItineraryArriveFlightCityNameText());

        //Validating Dep City info
        for (int count = 0; count < depCityNamePayment.size(); count ++) {
            ValidationUtil.validateTestStep("Validating right departing city displayed on the original itinerary section",
                    depCityNamePayment.get(count), depCityName.get(count) + " " + depTime.get(count));
        }
        //Validating Arr City  Info
        for (int count = 0; count < arrCityNamePayment.size(); count ++) {
            ValidationUtil.validateTestStep("Validating right Arrival displayed on the original itinerary section",
                    arrCityNamePayment.get(count), arrCityName.get(count) + " " + arrTime.get(count));
        }

//-- Step 14: Verify the $9FC membership charge is added to the booking
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getPaymentPage().getTotalDueOptionsChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Validating $9 FC information is displayed on the total due dropdown",
                pageObjectManager.getPaymentPage().getTotalDueOptionsBreakDownGridView().get(1).getText(),OPTION_MEMBER);

        ValidationUtil.validateTestStep("Validating $9 FC price is displayed on the total due dropdown",
                pageObjectManager.getPaymentPage().getTotalDueOptionsPriceText().getText()," $59.95");

//-- Step 15: Make a payment by using a credit card
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //******************************Confirmation Page Methods********************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
    }
    private List<String> getDisplayedText(List<WebElement> elementList){

        ArrayList<String> arrayList = new ArrayList<>();//empty

        for (WebElement element:elementList) {
            if(element.isDisplayed()){

                arrayList.add(element.getText().trim());
            }
        }
        return arrayList;
    }
}