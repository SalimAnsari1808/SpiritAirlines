package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

//**********************************************************************************************
//Test Case ID: TC90861
//Title       : 35401 Misc_CP_CI_SinglePAX _ Edit-Add Passport_Verify Passport Information page
//Created By  : Anthony Cardona
//Created On  : 24-Jul-2019
//Reviewed By : Salim Ansari
//Reviewed On : 25-Jul-2019
//**********************************************************************************************

public class TC90861 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "DomesticInternational" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "AddEditPassportInfo" , "PassportUI"})
    public void Misc_CP_CI_SinglePAX_Edit_Add_Passport_Verify_Passport_Information_page(@Optional("NA") String platform) {

        /******************************************************************************
         *************************Navigate to Boarding Pass Page***********************
         ******************************************************************************/

        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC90861 under REGRESSION-CRITICAL suite on " + platform +" Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE 		    	= "OneWay";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 	  	    = "AllLocation";
        final String ARR_AIRPORT_CODE  	    = "CUN";
        final String DEP_DATE 	    		= "0";
        final String ARR_DATE 	    		= "NA";
        final String ADULTS 		       	= "1";
        final String CHILDREN		      	= "0";
        final String INFANT_LAP 	     	= "0";
        final String INFANT_SEAT	     	= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    	= "NonStop";
        final String FARE_TYPE 	    		= "Standard";
        final String UPGRADE_VALUE 	  	    = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		    = "NotRequired";
        final String CARD_TYPE			    = "MasterCard";

        //open browser
        openBrowser(platform);

//Step 1: Get to the passport page
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
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
        final String BOOKING_STATUS     = "CONFIRMED";
        final String CONFIRMATION_URL   = "book/confirmation";
        final String RES_SUMMARY_URL	= "check-in/reservation-summary";
        final String RES_CHECK_IN       = "check-in/travel-docs";
        final String PASSPORT_HEADER    = "Passport Information";
        final String EXP_DATE_INVALID   = "EXPIRATION DATE IS INVALID";
        final String MID_NAME_INVALID   = "Middle Name has an invalid format";
        final String PASSPORT_REQUIRED  = "Passport Number is required";
        final String INVALID_DATE       = "INVALID DATE";

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(),BOOKING_STATUS);

        //Store all the basic info
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Homepage method to check in
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Click on 'Edit/view Passport Link
        pageObjectManager.getPassportPage().getAddEditPassportInfoLink().click();

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 2: Validate user lands on the passenger information page
        ValidationUtil.validateTestStep("The user correctly lands on the passport page" , getDriver().getCurrentUrl(),RES_CHECK_IN);

//Step 3: Verify "Passport Information" page is bold and verbiage is correct
        ValidationUtil.validateTestStep("The title on the passport page is correct" ,
                pageObjectManager.getPassportPage().getPassengerInformationPageHeaderText().getText() , PASSPORT_HEADER);
        ValidationUtil.validateTestStep("The title on the passport page is Bold" ,
                pageObjectManager.getPassportPage().getPassengerInformationPageHeaderText().getAttribute("class") , "headline1");

//Step 4: Verify all information on the customer section is correct for each customer
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

        String selectedTitleOption = new Select(pageObjectManager.getPassportPage().getPassengerTitleDropDown().get(0)).getFirstSelectedOption().getText();
        ValidationUtil.validateTestStep("The passenger title is correct" , selectedTitleOption, passengerInfoData.title);

        System.out.println(pageObjectManager.getPassportPage().getPassportFirstNameText().getAttribute("value"));

        ValidationUtil.validateTestStep("The user's first name is correct" , pageObjectManager.getPassportPage().getPassportFirstNameText().getAttribute("value") , passengerInfoData.firstName);
        ValidationUtil.validateTestStep("The user's last name is correct" , pageObjectManager.getPassportPage().getPassportLastNameText().getAttribute("value") , passengerInfoData.lastName);

//Step 5: Verify that Title, Country of Residence, Passport Number,Issuing Country all have asterisk
        ValidationUtil.validateTestStep("The title label has a red asterisk",
                pageObjectManager.getPassportPage().getTitletext().getAttribute("class").contains("asterisk"));
        ValidationUtil.validateTestStep("The Country of Residence label has a red asterisk",
                pageObjectManager.getPassportPage().getCountryOfResidencetext().getAttribute("class").contains("asterisk"));
        ValidationUtil.validateTestStep("The Passport Number label has a red asterisk",
                pageObjectManager.getPassportPage().getPassportNumbertext().getAttribute("class").contains("asterisk"));
        ValidationUtil.validateTestStep("The Issuing Country label has a red asterisk",
                pageObjectManager.getPassportPage().getIssuingCountrytext().getAttribute("class").contains("asterisk"));
        ValidationUtil.validateTestStep("The Expiration Date label has a red asterisk",
                pageObjectManager.getPassportPage().getExpirationDatetext().getAttribute("class").contains("asterisk"));

//Step 6: Verify Country of Residence and Issuing Country is defaulted to United States of America
        String selectedCountryOption = new Select(pageObjectManager.getPassportPage().getPassengerCountryOfResidenceDropDown().get(0)).getFirstSelectedOption().getText();
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");
        ValidationUtil.validateTestStep("The Country of Residence drop down is default to the Unite States" , selectedCountryOption, memberEnrollmentData.country);

        String IssuingCountryOption = new Select(pageObjectManager.getPassportPage().getPassengerIssuingCountryDropDown().get(0)).getFirstSelectedOption().getText();
        ValidationUtil.validateTestStep("The Issuing Country drop down is default to the Unite States" ,IssuingCountryOption, memberEnrollmentData.country);

//Step 7: Verify that Expiration year goes up to 10 years in the future
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        int year = localDate.getYear();

        //input expiration date + 10 years from today
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).sendKeys(month + "/" + (day + 1) + "/" + (year + 10) + (Keys.TAB));
        ValidationUtil.validateTestStep("The error message is displayed when expiration is greater than 10 years" ,
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),EXP_DATE_INVALID);
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0));

//Step 8 and 9: Verify that first and last name are auto populated
        ValidationUtil.validateTestStep("The user's first name is correct" ,
                pageObjectManager.getPassportPage().getPassportFirstNameText().getAttribute("value") , passengerInfoData.firstName);
        ValidationUtil.validateTestStep("The user's last name is correct" ,
                pageObjectManager.getPassportPage().getPassportLastNameText().getAttribute("value") , passengerInfoData.lastName);

//Step 10: input numbers or letter in middle name and validate error message
        pageObjectManager.getPassportPage().getPassengerMiddleNameTextBox().get(0).sendKeys("3212"+(Keys.TAB));
        ValidationUtil.validateTestStep("The middle name does not allow invalid inputs(numbers)" ,
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),MID_NAME_INVALID);

        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getPassportPage().getPassengerMiddleNameTextBox().get(0));
        pageObjectManager.getPassportPage().getPassengerMiddleNameTextBox().get(0).sendKeys("!@##$"+(Keys.TAB));
        ValidationUtil.validateTestStep("The middle name does not allow invalid inputs(symbols)" ,
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),MID_NAME_INVALID);

        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getPassportPage().getPassengerMiddleNameTextBox().get(0));
        pageObjectManager.getPassportPage().getPassengerMiddleNameTextBox().get(0).sendKeys("TEST");

////Step 11: Try to save changes without inputting Passport number, validate the error message
        pageObjectManager.getPassportPage().getSaveChangesButton().click();
        ValidationUtil.validateTestStep("The Passport number required error message is displayed" ,
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),PASSPORT_REQUIRED);

        pageObjectManager.getPassportPage().getPassengerPassportNumberTextBox().get(0).sendKeys("ALDKJDI2");

//Step 12: inputting invalid expiration date
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).sendKeys("20/60/9999" + (Keys.TAB));
        ValidationUtil.validateTestStep("The invalid date error is displayed" ,
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),INVALID_DATE);

        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).clear();
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).sendKeys(month + "/" + day + "/" + (year + 5) + (Keys.TAB));


//Step 13: Validate that the save changes and cancel changes buttons are displayed
        ValidationUtil.validateTestStep("The save changes button is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassportPage().getSaveChangesButton()));

        ValidationUtil.validateTestStep("The Cancel changes button is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassportPage().getCancelChangesButton().get(0)));

        //click on Save Changes Button
        pageObjectManager.getPassportPage().getSaveChangesButton().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify User is directed to check in reservation Summary Page",
                getDriver().getCurrentUrl(),RES_SUMMARY_URL);
    }
}