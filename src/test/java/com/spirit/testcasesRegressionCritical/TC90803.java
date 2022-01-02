package com.spirit.testcasesRegressionCritical;
import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC90803
// TestCase : 35177 CP_Bag-O-Tron Existing Trip_RT DOM_Validations
// Created By : Kartik Chauhan
// Created On : 04-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 05-July-2019
// **********************************************************************************************

public class TC90803 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups = {"BagOTron","RoundTrip","DomesticDomestic","WithIn7Days","Adult","Guest","Connecting","BookIt",
                    "CheckedBags","NoSeats","CheckInOptions","MasterCard","OptionalServicesUI"})
    public void CP_Bag_O_Tron_Existing_Trip_RT_DOM_Validations (@Optional("NA")String platform){
        //*************************Navigate to optional service Page********************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90803 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE 		    	= "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 	  	    = "AllLocation";
        final String ARR_AIRPORT_CODE  	    = "LAS";
        final String DEP_DATE 	    		= "6";
        final String ARR_DATE 	    		= "7";
        final String ADULTS 		       	= "1";
        final String CHILDREN		      	= "0";
        final String INFANT_LAP 	     	= "0";
        final String INFANT_SEAT	     	= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    	= "Connecting";
        final String ARR_Flight 		    = "Connecting";
        final String FARE_TYPE 	    		= "Standard";
        final String UPGRADE_VALUE 	  	    = "BookIt";

        //Bags Page constant values
        final String DEP_BAGS 			    = "Carry_0|Checked_5";
        final String RET_BAGS			    = "Carry_0|Checked_5";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		    = "NotRequired";
        final String CARD_TYPE			    = "MasterCard";
//Step 2
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        //************************Contniue Bags Button is not working******************************************
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
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

        //**********************Validation to Confirmation Page************************/
        //declare constant used in validation
        final String BOOKING_STATUS = "CONFIRMED";
        final String CONFIRMATION_URL = "book/confirmation";

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(),BOOKING_STATUS);

        //Store all the basic info
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //login to My Trip
        //click on header Spirit logo
        pageObjectManager.getHomePage().getSpiritLogoImage().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        getDriver().navigate().refresh();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getHomePage().getBookPathLink().click();

        //Click on Optional Service link
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHomePage().getOptionalServiceLink());

        WaitUtil.untilPageLoadComplete(getDriver());

        //Select Existing Trip
        pageObjectManager.getOptionalServicesPage().getBagOTronExistingTripLabel().click();
        WaitUtil.untilTimeCompleted(1200);

        //enter last name
//        pageObjectManager.getOptionalServicesPage().getExistingTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString().substring(0,5));
        pageObjectManager.getOptionalServicesPage().getExistingTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

        //enter confirmation code
        pageObjectManager.getOptionalServicesPage().getExistingTripConfirmationCodeTextBox().sendKeys("XXXXX");

        //select Display Bag Price
        pageObjectManager.getOptionalServicesPage().getBagOTronDisplayBagPriceButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Verify ALL characters in last Name are required IF last name is less than 5 characters on Optional Service Page",
                pageObjectManager.getOptionalServicesPage().getReservationNotFoundPopUpHeader().isDisplayed());

        pageObjectManager.getOptionalServicesPage().getReservationNotFoundPopUpCrossButton().click();

        JSExecuteUtil.refreshBrowser(getDriver());

        WaitUtil.untilPageLoadComplete(getDriver());


//STEP--5
        final String LAST_NAME_ERROR            = "Please enter your Last Name.";
        final String CONFIRMATION_CODE_ERROR    = "Please enter your Confirmation Code.";

        //Select Existing Trip
        pageObjectManager.getOptionalServicesPage().getBagOTronExistingTripLabel().click();

        //clear last name
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getOptionalServicesPage().getExistingTripLastNameTextBox());

        //enter confirmation code
        pageObjectManager.getOptionalServicesPage().getExistingTripConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        //select Display Bag Price
        pageObjectManager.getOptionalServicesPage().getBagOTronDisplayBagPriceButton().click();

        ValidationUtil.validateTestStep("Please enter your last name error is displaying",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),LAST_NAME_ERROR);
//STEP--6
        JSExecuteUtil.refreshBrowser(getDriver());

        WaitUtil.untilPageLoadComplete(getDriver());

        //Select Existing Trip
        pageObjectManager.getOptionalServicesPage().getBagOTronExistingTripLabel().click();

        //enter last name
        pageObjectManager.getOptionalServicesPage().getExistingTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

        //Click on DISPLAY BAG PRICE button
        pageObjectManager.getOptionalServicesPage().getBagOTronDisplayBagPriceButton().click();

        //verify Confirmation code error
        ValidationUtil.validateTestStep("Please enter Confirmation Code error is displaying on Optional Service Page",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),CONFIRMATION_CODE_ERROR);

        //Click on How to Find Confirmation link
        pageObjectManager.getOptionalServicesPage().getHowToFindConfirmationCodeLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Verify Header of confirmation code pop-up error
        ValidationUtil.validateTestStep("Confirmation Code Header Is displaying on Optional Service Page",
                pageObjectManager.getOptionalServicesPage().getConfirmationCodePopUpHeader().isDisplayed());

        //Close the popup
        pageObjectManager.getOptionalServicesPage().getConfirmationCodePopUpCrossButton().click();
//STEP--7
        //clear last name
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getOptionalServicesPage().getExistingTripLastNameTextBox());

        //clear confirmation code
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getOptionalServicesPage().getExistingTripConfirmationCodeTextBox());

        //enter last name
        pageObjectManager.getOptionalServicesPage().getExistingTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

        //enter confirmation code
        pageObjectManager.getOptionalServicesPage().getExistingTripConfirmationCodeTextBox().sendKeys("XXXXX");

        pageObjectManager.getOptionalServicesPage().getBagOTronDisplayBagPriceButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Reservation Not Found pop-up Is displaying on Optional Service Page",
                pageObjectManager.getOptionalServicesPage().getReservationNotFoundPopUpHeader().isDisplayed());

        pageObjectManager.getOptionalServicesPage().getReservationNotFoundPopUpCrossButton().click();

        JSExecuteUtil.refreshBrowser(getDriver());

        WaitUtil.untilPageLoadComplete(getDriver());

        //Select Existing Trip
        pageObjectManager.getOptionalServicesPage().getBagOTronExistingTripLabel().click();

        //enter last name
        pageObjectManager.getOptionalServicesPage().getExistingTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

        //enter confirmation code
        pageObjectManager.getOptionalServicesPage().getExistingTripConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        pageObjectManager.getOptionalServicesPage().getBagOTronDisplayBagPriceButton().click();

        //wait until page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify two table appear on option page
        ValidationUtil.validateTestStep("Verify two grids that are displayed one for the outbound trip and the other for the return flight on Optional Service Page",
                getDriver().findElements(By.tagName("table")).size()==2);

        ValidationUtil.validateTestStep("Over Weight Bag is displaying on Optional Service Page",
                pageObjectManager.getOptionalServicesPage().getOverweightOrOversizedText().isDisplayed());


        ValidationUtil.validateTestStep("Sporting Equipment is displaying on Optional Service Page",
                pageObjectManager.getOptionalServicesPage().getSportingEquipmentText().isDisplayed());

        pageObjectManager.getOptionalServicesPage().getSportingEquipmentLink().click();

        WaitUtil.untilTimeCompleted(2000);

        pageObjectManager.getOptionalServicesPage().getSportingEquipmentFullListText().click();

        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Verify user will be redirected to page 22 of the Contract_of_Carriage.pdf on Optional Service Page",
                getDriver().getCurrentUrl(),"Contract_of_Carriage.pdf#page=23");

        getDriver().close();

        TestUtil.switchToWindow(getDriver(),0);
    }
}