package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC90800
//Description: MT-CI_ADD Overweight Oversized BAG(S) (63-80 linear inches (158-203 cm) With PLUS (+) OR MINUS(-)
//Created By : Kartik Chauhan
//Created On : 01-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 03-May-2019
//**********************************************************************************************
public class TC90800 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Child" , "Guest" ,
                     "Connecting" , "BookIt" , "CheckedBags" , "NoSeats" , "CheckInOptions" , "TravelInsuranceBlock" , "MasterCard"
                   , "AddEditBags" , "Overweight" , "BagsUI"})
    public void MT_CI_ADD_Overweight_Oversized_BAG_S_63_80_linear_inches_158_203cm_WITH_PLUS_OR_MINUS (@Optional("NA")String platform) {
        /******************************************************************************
         *********************Navigate to Manage Travel Bags Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90800 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "30";
        final String ADULTS				= "2";
        final String CHILDS				= "1";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Connecting";
        final String ARR_Flight 		= "Connecting";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE		= "BookIt";

        //Bags Page constant values
        final String DEP_BAGS           = "Carry_0|Checked_5||Carry_0|Checked_5||Carry_0|Checked_5";
        final String RET_BAGS	        = "Carry_0|Checked_5||Carry_0|Checked_5||Carry_0|Checked_5";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "Required";
        final String CARD_TYPE			= "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

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
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));

        /*********************************************Start OF Manage Travel**********************/
        //login to checkIn Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Clicked on Add Bag Link
        pageObjectManager.getReservationSummaryPage().getPassengerSectionEditBagsButton().get(0).click();

        //wait till Bags page appear
        WaitUtil.untilPageLoadComplete(getDriver());

        //open dep sporting equipment link
        for(WebElement depSortingEquipment : pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton()){
            //click to open sporting equipment
            depSortingEquipment.click();

            //wait for .5 sec
            WaitUtil.untilTimeCompleted(500);
        }

        //open dep sporting equipment link
        for(WebElement retSortingEquipment : pageObjectManager.getBagsPage().getReturningSportingEquipmentLinkButton()){
            //click to open sporting equipment
            retSortingEquipment.click();

            //wait for .5 sec
            WaitUtil.untilTimeCompleted(500);
        }

        /******************************************************************************
         *********************Validation to Manage Travel Bags Page********************
         ******************************************************************************/
        //declare constant used in validation
        final String MANAGE_BAGS_URL    = "my-trips/bags";
        final String BLUE_COLOR 	    = "rgb(0, 115, 230)";
        final String GRAY_COLOR 	    = "rgb(204, 204, 204)";
        final int NO_OVERWEIGHT_BAG 	= 0;
        final int ONE_OVERWEIGHT_BAG 	= 1;
        final int TWO_OVERWEIGHT_BAG 	= 2;
        final int THREE_OVERWEIGHT_BAG  = 3;
        final int FOUR_OVERWEIGHT_BAG 	= 4;
        final int FIVE_OVERWEIGHT_BAG 	= 5;
        final String DOLLOR_SIGN	    = "\\$";

        //declare variable used in validation
        String signColor;
        int carryBagCounter;
        int overweightBagCounter;
        double preOverWeightBagPrice;
        double crrOverWeightBagPrice;

        //Verify  Passenger Overweight Bags URL
        ValidationUtil.validateTestStep("Verify User reached to Manage Travel Bags Page", getDriver().getCurrentUrl().contains(MANAGE_BAGS_URL));

        for(int passengerCounter=0;passengerCounter<=2;passengerCounter++) {

            //Verify  Pasenger Overweight Bags
            ValidationUtil.validateTestStep("*************Verify Bags Details for passenger " + (passengerCounter+1) + "*************", true);

            /*********************************************************************************************************************
             *******************************************************Departing Bags*************************************************
             *********************************************************************************************************************/

            //get the cover weight price appear for checked Bag
            preOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //**********************************************
            //***After selecting one Overweight Bag*********
            //**********************************************
            //get overweight bag minus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80MinusButton().get(passengerCounter), "color");

            //Verify overweight bag minus link color after selecting 5 bags is Gray
            ValidationUtil.validateTestStep("Verify overweight bag minus link color before selecting any bags is Gray", signColor.equals(GRAY_COLOR));

            //click on over weight plus link
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get overweight bag plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80MinusButton().get(passengerCounter), "color");

            //Verify overweight bag plus link color after selecting 5 bags is Gray
            ValidationUtil.validateTestStep("Verify overweight bag minus link color after selecting 1 bags is Blue", signColor.equals(BLUE_COLOR));

            //get the bag price appear for over weight Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after adding (63-80) Linear Inches Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for future testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the over weight bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify over weight bag count after section is 2
            ValidationUtil.validateTestStep("Verify Departing Over 63 to 80 inches.. Bag count after selection is 1", overweightBagCounter==ONE_OVERWEIGHT_BAG);

            //**********************************************
            //***After selecting two Overweight Bag*********
            //**********************************************
            //click on over weight plus link
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after adding 2 (63-80 Linear Inches).. Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 2
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches... Bag count after selection is 2", overweightBagCounter==TWO_OVERWEIGHT_BAG);

            //**********************************************
            //***After selecting three Overweight Bag*******
            //**********************************************
            //click on over weight plus link
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after adding 3 (63-80) Linear Inches.. Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches.. Bags Bag count after selection is 3", overweightBagCounter==THREE_OVERWEIGHT_BAG);

            //**********************************************
            //***After selecting four Overweight Bag********
            //**********************************************
            //click on over weight plus link
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after adding 4 Over Weight (63-80) Linear Inches.. Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches.. Bag count after selection is 4", overweightBagCounter==FOUR_OVERWEIGHT_BAG);

            //**********************************************
            //***After selecting five Overweight Bag********
            //**********************************************
            //get overweight bag plus link color before selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80PlusButton().get(passengerCounter), "color");

            //Verify overweight bag plus link color after selecting 4 bags is Blue
            ValidationUtil.validateTestStep("Verify overweight bag plus link color before selecting 5 bags is Blue", signColor.equals(BLUE_COLOR));

            //click on over weight plus link
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get overweight bag plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80PlusButton().get(passengerCounter), "color");

            //Verify overweight bag plus link color after selecting 4 bags is Blue
            ValidationUtil.validateTestStep("Verify overweight bag plus link color after selecting 5 bags is Gray", signColor.equals(GRAY_COLOR));

            //verify add for $xx is not displaying on screen
            ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is not displaying on screen for Departing Overweight Bags" , pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()==0);

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches... Bag count after selection is 5", overweightBagCounter==FIVE_OVERWEIGHT_BAG);

            //**********************************************
            //***User cannot select six Overweight Bag******
            //**********************************************
            //click on over weight plus link
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches... Bag count after selection is 6", overweightBagCounter==FIVE_OVERWEIGHT_BAG);

            //**********************************************
            //***After removing Fifth Overweight Bag********
            //**********************************************
            //click on minus sign
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is reappear on screen for Departing Overweight Bags" , pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches... Bag count after removing is 4", overweightBagCounter==FOUR_OVERWEIGHT_BAG);

            //**********************************************
            //***After removing Fourth Overweight Bag*******
            //**********************************************
            //click on minus sign
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after removing 4th Over Weight (63-80) Linear Inches... Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches... Bag count after removing is 3", overweightBagCounter==THREE_OVERWEIGHT_BAG);

            //**********************************************
            //***After removing Third Overweight Bag*******
            //**********************************************
            //click on minus sign
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after removing 3th Over Weight (63-80) Linear Inches... Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches... Bag count after removing is 2", overweightBagCounter==TWO_OVERWEIGHT_BAG);

            //**********************************************
            //***After removing Second Overweight Bag*******
            //**********************************************
            //click on minus sign
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after removing 2nd Over Weight (63-80) Linear Inches... Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches... Bag count after removing is 1", overweightBagCounter==ONE_OVERWEIGHT_BAG);

            //**********************************************
            //***After removing First Overweight Bag********
            //**********************************************
            //get overweight bag minus link color before selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80MinusButton().get(passengerCounter), "color");

            //Verify overweight bag minus link color after removing 4 bags is Blue
            ValidationUtil.validateTestStep("Verify overweight bag minus link color after removing 4 bags is Blue", signColor.equals(BLUE_COLOR));

            //click on minus sign
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get overweight bag minus link color before selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80MinusButton().get(passengerCounter), "color");

            //Verify overweight bag minus link color after removing 5bags is Gray
            ValidationUtil.validateTestStep("Verify overweight bag minus link color after removing 5 bags is Gray", signColor.equals(GRAY_COLOR));

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after removing 1st Over Weight (63-80) Linear Inches... Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches... Bag count after removing is 0", overweightBagCounter==NO_OVERWEIGHT_BAG);

            //**********************************************
            //*User cannot select Negative Overweight Bag***
            //**********************************************
            //click on minus sign
            pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Departing Over Weight (63-80) Linear Inches.. Bag count after removing is 0", overweightBagCounter==NO_OVERWEIGHT_BAG);

            /*********************************************************************************************************************
             ********************************************************Returning Bags***********************************************
             *********************************************************************************************************************/
            //get the cover weight price appear for checked Bag
            preOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //**********************************************
            //***After selecting one Overweight Bag*********
            //**********************************************
            //get overweight bag minus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80MinusButton().get(passengerCounter), "color");

            //Verify overweight bag minus link color after selecting 5 bags is Gray
            ValidationUtil.validateTestStep("Verify Returning overweight bag minus link color before selecting any bags is Gray", signColor.equals(GRAY_COLOR));

            //click on over weight plus link
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get overweight bag plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80MinusButton().get(passengerCounter), "color");

            //Verify overweight bag plus link color after selecting 5 bags is Gray
            ValidationUtil.validateTestStep("Verify Returning overweight bag minus link color after selecting 1 bags is Blue", signColor.equals(BLUE_COLOR));

            //get the bag price appear for over weight Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after adding 1 Over Weight (63-80) Linear Inches.. Returning Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for future testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the over weight bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify over weight bag count after section is 2
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after selection is 1", overweightBagCounter==ONE_OVERWEIGHT_BAG);

            //**********************************************
            //***After selecting two Overweight Bag*********
            //**********************************************
            //click on over weight plus link
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after adding 2 Over Weight (63-80) Linear Inches.. Returning Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 2
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after selection is 2", overweightBagCounter==TWO_OVERWEIGHT_BAG);

            //**********************************************
            //***After selecting three Overweight Bag*******
            //**********************************************
            //click on over weight plus link
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after adding 3 Over Weight (63-80) Linear Inches.. Returning Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after selection is 3", overweightBagCounter==THREE_OVERWEIGHT_BAG);

            //**********************************************
            //***After selecting four Overweight Bag********
            //**********************************************
            //click on over weight plus link
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after adding 4 Over Weight (63-80) Linear Inches.. Returning Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after selection is 4", overweightBagCounter==FOUR_OVERWEIGHT_BAG);

            //**********************************************
            //***After selecting five Overweight Bag********
            //**********************************************
            //get overweight bag plus link color before selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80PlusButton().get(passengerCounter), "color");

            //Verify overweight bag plus link color after selecting 4 bags is Blue
            ValidationUtil.validateTestStep("Verify overweight bag plus link color before selecting 5 bags is Blue", signColor.equals(BLUE_COLOR));

            //click on over weight plus link
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get overweight bag plus link color after selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80PlusButton().get(passengerCounter), "color");

            //Verify overweight bag plus link color after selecting 4 bags is Blue
            ValidationUtil.validateTestStep("Verify overweight bag plus link color after selecting 5 bags is Gray", signColor.equals(GRAY_COLOR));

            //verify add for $xx is not displaying on screen
            ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is not displaying on screen for Returning Overweight Bags" , pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()==0);

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after selection is 5", overweightBagCounter==FIVE_OVERWEIGHT_BAG);

            //**********************************************
            //***User cannot select six Overweight Bag******
            //**********************************************
            //click on over weight plus link
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80PlusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after selection is 6", overweightBagCounter==FIVE_OVERWEIGHT_BAG);

            //**********************************************
            //***After removing Fifth Overweight Bag********
            //**********************************************
            //click on minus sign
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("Verify \"Add a Checked Bag for $xx\" is reappear on screen for Returning Overweight Bags" , pageObjectManager.getBagsPage().getDepartingOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).size()>0);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after removing is 4", overweightBagCounter==FOUR_OVERWEIGHT_BAG);

            //**********************************************
            //***After removing Fourth Overweight Bag*******
            //**********************************************
            //click on minus sign
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after removing 4th Over Weight (63-80) Linear Inches.. Returning Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after removing is 3", overweightBagCounter==THREE_OVERWEIGHT_BAG);

            //**********************************************
            //***After removing Third Overweight Bag*******
            //**********************************************
            //click on minus sign
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after removing 3th Over Weight (63-80) Linear Inches... Returning Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after removing is 2", overweightBagCounter==TWO_OVERWEIGHT_BAG);

            //**********************************************
            //***After removing Second Overweight Bag*******
            //**********************************************
            //click on minus sign
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after removing 2nd Over Weight (63-80) Linear Inches.. Returning Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after removing is 1", overweightBagCounter==ONE_OVERWEIGHT_BAG);

            //**********************************************
            //***After removing First Overweight Bag********
            //**********************************************
            //get overweight bag minus link color before selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80MinusButton().get(passengerCounter), "color");

            //Verify overweight bag minus link color after removing 4 bags is Blue
            ValidationUtil.validateTestStep("Verify overweight bag minus link color after removing 4 bags is Blue", signColor.equals(BLUE_COLOR));

            //click on minus sign
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get overweight bag minus link color before selection
            signColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80MinusButton().get(passengerCounter), "color");

            //Verify overweight bag minus link color after removing 5bags is Gray
            ValidationUtil.validateTestStep("Verify overweight bag minus link color after removing 5 bags is Gray", signColor.equals(GRAY_COLOR));

            //get the current bag price appear for checked Bag
            crrOverWeightBagPrice = Double.parseDouble(pageObjectManager.getBagsPage().getReturningOverWeightBag63to80NextBagPriceText().get(passengerCounter).findElements(By.tagName("p")).get(0).getText().split(DOLLOR_SIGN)[1]);

            //"Add Checked Bag for $XX" price has increased.
            ValidationUtil.validateTestStep("\"Add for $XX\" price has same after removing 1st Over Weight (63-80) Linear Inches.. Returning Bags", crrOverWeightBagPrice==preOverWeightBagPrice);

            //store current bag price to previous bag price variable for furthur testing
            preOverWeightBagPrice = crrOverWeightBagPrice;

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches.. Bag count after removing is 0", overweightBagCounter==NO_OVERWEIGHT_BAG);

            //**********************************************
            //*User cannot select Negative Overweight Bag***
            //**********************************************
            //click on minus sign
            pageObjectManager.getBagsPage().getReturningOverWeightBag63to80MinusButton().get(passengerCounter).click();

            //get the checked bag counter after selection
            overweightBagCounter = Integer.parseInt(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getReturningOverWeightBag63to80CounterTextBox().get(passengerCounter)));

            //verify checked bag count before selection is 3
            ValidationUtil.validateTestStep("Verify Returning Over Weight (63-80) Linear Inches... Bag count after removing is 0", overweightBagCounter==NO_OVERWEIGHT_BAG);

        }
    }
}