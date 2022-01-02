package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;


//**********************************************************************************************
//Test Case ID: TC90927
//Test Name:  CP_BP_Billing Address
//Description:
//Created By : Sunny Sok
//Created On : 05-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 11-May-2019
//**********************************************************************************************

public class PRODTC90927 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups="Production")

    public void CP_BP_Billing_Address(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Payment Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90927 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //STEP--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

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

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ************************Validation Payment Page*******************************
         ******************************************************************************/
        //Constant Values to validate
        final String PAYMENT_URL = "/book/payment";
        final String CONFIRMATION_URL = "/book/confirmation";
        final String PRE_BILLING_ADDRESS = "Please use";
        final String POST_BILLING_ADDRESS = "for my billing information.";

        //declare variable used in validation
        String billingAddress;

        //--Step 2
        //Validate User reaches payment page
        ValidationUtil.validateTestStep("User verify Navigation to Payment page",
                getDriver().getCurrentUrl(),PAYMENT_URL);

        //MUST CLICK NO TO TRAVEL GUARD
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

        //wait for page load is completed
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate the checkbox is present and check mark is pre-filled
        ValidationUtil.validateTestStep("User verify Same Address checkbox is selected on Payment Page",
                pageObjectManager.getPaymentPage().getUseSameAddressCheckBox().isSelected());

        //get customer contact info
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");
        //--Step 3
        //A verbiage should be aligned to the right of the checkbox that reads Please use {XXX Street, XXX City, XXX State} for my Billing Address
        //get the billing address
        billingAddress = pageObjectManager.getPaymentPage().getUseSameAddressLabel().getText();
        //Validate verbiage and font
        ValidationUtil.validateTestStep("User verify constnt verbiage in address on Payment Page",
                billingAddress.contains(PRE_BILLING_ADDRESS) && billingAddress.contains(POST_BILLING_ADDRESS));

        ValidationUtil.validateTestStep("User verify Street, City and State in address on Payment Page",
                billingAddress.contains(memberEnrollmentData.address1) && billingAddress.contains(memberEnrollmentData.city) && billingAddress.contains(memberEnrollmentData.zipCode) );

        //--Step4
        //With the checkbox being pre-checked by default, verify the address fields are suppressed
        //Validate all Billing Address fields are suppressed - screenshot attached
        ValidationUtil.validateTestStep("User verify Address Textbox is not displayed on Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getAddressTextbox()));
        ValidationUtil.validateTestStep("User verify City Textbox is not displayed on Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getCityTextbox()));
        ValidationUtil.validateTestStep("User verify State Textbox is not displayed on Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getStateTextBox()));
        ValidationUtil.validateTestStep("User verify ZipCode Textbox is not displayed on Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getZipCodeTextbox()));
        ValidationUtil.validateTestStep("User verify Coutry Drop Down is not displayed on Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getCountryDropdown()));

        //--Step 5
        //Unselect the checkbox
        pageObjectManager.getPaymentPage().getUseSameAddressLabel().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate
        //Checkbox should be cleared and Billing Address fields should expand for data entry
        ValidationUtil.validateTestStep("User verify Same Address Checkbox should be cleared on Payment Page",
                !pageObjectManager.getPaymentPage().getUseSameAddressCheckBox().isSelected());
        ValidationUtil.validateTestStep("User Verify  Address field are displayed for data entry on Payment Page",
                pageObjectManager.getPaymentPage().getAddressTextbox().isDisplayed());
        ValidationUtil.validateTestStep("User Verify City field sare displayed for data entry on Payment Page",
                pageObjectManager.getPaymentPage().getCityTextbox().isDisplayed());
        ValidationUtil.validateTestStep("User Verify State field are displayed for data entry on Payment Page",
                pageObjectManager.getPaymentPage().getStateTextBox().isDisplayed());
        ValidationUtil.validateTestStep("User Verify Zip Code fields are displayed for data entry on Payment Page",
                pageObjectManager.getPaymentPage().getZipCodeTextbox().isDisplayed());
        ValidationUtil.validateTestStep("User Verify Country fields are displayed for data entry on Payment Page",
                pageObjectManager.getPaymentPage().getCountryDropdown().isDisplayed());

        ValidationUtil.validateTestStep("User Verify  Address field is cleared on Payment Page",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getPaymentPage().getAddressTextbox()).length()==0);
        ValidationUtil.validateTestStep("User Verify City field is cleared on Payment Page",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getPaymentPage().getCityTextbox()).length()==0);
        ValidationUtil.validateTestStep("User Verify State field is cleared on Payment Page",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getPaymentPage().getStateTextBox()).length()==0);
        ValidationUtil.validateTestStep("User Verify Zip Code fields is cleared on Payment Page",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getPaymentPage().getZipCodeTextbox()).length()==0);
        ValidationUtil.validateTestStep("User Verify Country fields is contain 'USA' as default on Payment Page",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getPaymentPage().getCountryDropdown()),"US");

        //Enter a new billing address and complete payment using any credit card
        pageObjectManager.getPaymentPage().getAddressTextbox().sendKeys("NEW ADDRESS " + memberEnrollmentData.address1);
        pageObjectManager.getPaymentPage().getCityTextbox().sendKeys(memberEnrollmentData.city);
        pageObjectManager.getPaymentPage().getZipCodeTextbox().sendKeys(memberEnrollmentData.zipCode);
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getStateDropdown(),memberEnrollmentData.state);
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getCountryDropdown(),memberEnrollmentData.country);

        //payment methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        WaitUtil.untilPageURLVisible(getDriver(), CONFIRMATION_URL);
//
//        ValidationUtil.validateTestStep("User verify Navigation to Confirmation page",
//                getDriver().getCurrentUrl().contains(CONFIRMATION_URL));
    }
}
