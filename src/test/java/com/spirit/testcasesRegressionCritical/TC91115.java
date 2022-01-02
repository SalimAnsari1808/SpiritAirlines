package com.spirit.testcasesRegressionCritical;
import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC91115
// TestCase : CP_Bag-O-Tron Widget Exisiting Trip Test Scenario
// Created By : Kartik Chauhan
// Created On : 04-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 05-July-2019
// **********************************************************************************************
public class TC91115 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups = {"BagOTron" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "CheckedBags" ,"BagsUI", "NoSeats" , "MasterCard"})
    public void CP_Bag_O_Tron_Widget_Exisiting_Trip_Test_Scenario (@Optional("NA")String platform){

        /******************************************************************************
         *************************Navigate to optional service Page********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91115 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";

        //Bag-O-Tron Constant Values
        final String TRIP_TYPE          = "NewTrip";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "6";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DISPLAY_PRICE      = "DisplayBagPrice";
        final String BAGS_PATH          = "BookingPath";
        final String BAGS_PRICE         = "Standard";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE		= "BookIt";

        //Bags Page Constant Values
        final String EACH_BAG_PRICE     = "EachBagPrice";

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_0|Checked_4";
        final String RET_BAGS			= "Carry_0|Checked_4";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "NotRequired";
        final String CARD_TYPE			= "MasterCard";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        WaitUtil.untilTimeCompleted(2000);
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHomePage().getOptionalServiceLink());
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bag-O-Tron Page Methods
        pageMethodManager.getOptionalServicesPageMethods().selectTrip(TRIP_TYPE);
        pageMethodManager.getOptionalServicesPageMethods().selectAirports(DEP_AIRPORT_CODE,ARR_AIRPORT_CODE);
        pageMethodManager.getOptionalServicesPageMethods().selectDates(DEP_DATE,ARR_DATE);
        pageMethodManager.getOptionalServicesPageMethods().selectTravellingPassenger(ADULTS,CHILDS,INFANT_LAP,INFANT_SEAT);
        pageMethodManager.getOptionalServicesPageMethods().selectBagoTronButton(DISPLAY_PRICE);
        pageMethodManager.getOptionalServicesPageMethods().setBagOTronBagPrices("Dep",BAGS_PATH,BAGS_PRICE);
        pageMethodManager.getOptionalServicesPageMethods().selectBookTrip();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /******************************************************************************
         ****************************Validation on Bags Page***************************
         ******************************************************************************/
        String eachBagPrice = pageMethodManager.getOptionalServicesPageMethods().getBagOTronBagPrice("Dep",EACH_BAG_PRICE,"0","0");

        //declare constant used in validation
        final String FS_1_CARRY_BAG 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[0]));
        final String FS_1_CHECKED_BAG 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[1]));
        final String FS_2_CHECKED_BAG 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[2]));
        final String FS_3_CHECKED_BAG   = TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[3]));
        final String FS_4_CHECKED_BAG 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[4]));
        final String FS_5_CHECKED_BAG 	= TestUtil.convertTo2DecimalValue(Double.parseDouble(eachBagPrice.split("\\|")[5]));

        //*******************************************
        //**Carry-On Bag after selecting one bag*****
        //*******************************************
        //verify carry bag
        ValidationUtil.validateTestStep("verify 1 Carry on Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCarryOnPriceText().get(0).getText().toString(),FS_1_CARRY_BAG );

        //*******************************************
        //**Checked Bag after selecting first Bag****
        //*******************************************
        //Verify 1 checked bag
        ValidationUtil.validateTestStep("verify 1 Checked Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().toString(),FS_1_CHECKED_BAG  );

        //*******************************************
        //**Checked Bag after selecting second Bag***
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 2 checked bag
        ValidationUtil.validateTestStep("verify 2 Checked Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().toString(),FS_2_CHECKED_BAG  );

        //*******************************************
        //**Checked Bag after selecting Third Bag****
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 3 checked bag
        ValidationUtil.validateTestStep("verify 3 Checked Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().toString(),FS_3_CHECKED_BAG  );

        //*******************************************
        //**Checked Bag after selecting Fourth Bag***
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 4 checked bag
        ValidationUtil.validateTestStep("verify 4 Checked Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().toString(),FS_4_CHECKED_BAG  );

        //*******************************************
        //**Checked Bag after selecting Fifth Bag****
        //*******************************************
        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //Verify 5 checked bag
        ValidationUtil.validateTestStep("verify 5 Checked Bags Price for Guest user",
                pageObjectManager.getBagsPage().getDepartingNextCheckedBagPriceText().get(0).getText().toString(),FS_5_CHECKED_BAG  );

        //add one checked bag
        pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(0).click();

        //************************Continue Bags Button is not working******************************************
//        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
//        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS             = "CONFIRMED";
        final String CONFIRMATION_URL           = "book/confirmation";

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(),BOOKING_STATUS);

        //Store all the basic info
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        final String LAST_NAME_ERROR                = "Invalid name characters entered";
        final String CONFIRMATION_CODE_ERROR        = "Please enter your Confirmation Code.";
        final String RESERVATION_NOT_FOUND_HEADER   = "Reservation Not Found";
        final String RESERVATION_NOT_FOUND_TEXT     = "We are unable to locate the itinerary. Please verify the information is correct and try again. The combination of the customer last name and the Confirmation Code is invalid.  Please try again.";
        final String RESERVATION_NOT_FOUND_CLOSE    = "Close";

        //login to My Trip
        //click on header spirit logo
        pageObjectManager.getHomePage().getSpiritLogoImage().click();

        pageObjectManager.getHomePage().getBookPathLink().click();
//STEP--2
        //Click on Optional Service link
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHomePage().getOptionalServiceLink());
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP--3
        //Select Existing Trip
        pageObjectManager.getOptionalServicesPage().getBagOTronExistingTripLabel().click();

        //create constant
        final String NUMBER ="5";

        //enter last name
        pageObjectManager.getOptionalServicesPage().getExistingTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString(),NUMBER);
//STEP--4
        //select Display Bag Price
        pageObjectManager.getOptionalServicesPage().getBagOTronDisplayBagPriceButton().click();

        ValidationUtil.validateTestStep("'Invalid name characters entered' error is displaying",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),LAST_NAME_ERROR);
//STEP--6
        JSExecuteUtil.refreshBrowser(getDriver());

        WaitUtil.untilPageLoadComplete(getDriver());

        //Select Existing Trip
        pageObjectManager.getOptionalServicesPage().getBagOTronExistingTripLabel().click();

        //enter last name
        pageObjectManager.getOptionalServicesPage().getExistingTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

        //enter confirmation code
        pageObjectManager.getOptionalServicesPage().getExistingTripConfirmationCodeTextBox().sendKeys("XXXXX");

        //Click on DISPLAY BAG PRICE button
        pageObjectManager.getOptionalServicesPage().getBagOTronDisplayBagPriceButton().click();

        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Verify Reservation Not Found pop-up header on Optional Service Page",
                pageObjectManager.getOptionalServicesPage().getReservationNotFoundPopUpHeader().getText(),RESERVATION_NOT_FOUND_HEADER);

        ValidationUtil.validateTestStep("Verify Reservation Not Found pop-up body text on Optional Service Page",
                pageObjectManager.getOptionalServicesPage().getReservationNotFoundPopUpVerbiageText().getText(),RESERVATION_NOT_FOUND_TEXT);

        ValidationUtil.validateTestStep("Verify Reservation Not Found pop-up close button on Optional Service Page",
                pageObjectManager.getOptionalServicesPage().getReservationNotFoundPopUpCloseButton().getText(),RESERVATION_NOT_FOUND_CLOSE);

//STEP--7
        pageObjectManager.getOptionalServicesPage().getReservationNotFoundPopUpCrossButton().click();

        JSExecuteUtil.refreshBrowser(getDriver());

        WaitUtil.untilPageLoadComplete(getDriver());

        //Select Existing Trip
        pageObjectManager.getOptionalServicesPage().getBagOTronExistingTripLabel().click();

        pageObjectManager.getOptionalServicesPage().getExistingTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

        pageObjectManager.getOptionalServicesPage().getExistingTripConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        //Click on DISPLAY BAG PRICE button
        pageObjectManager.getOptionalServicesPage().getBagOTronDisplayBagPriceButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //verify two table appear on option page
        ValidationUtil.validateTestStep("Verify one grids that are displayed one for the outbound trip  on Optional Service Page",
                getDriver().findElements(By.tagName("table")).size()==1);
    }
}