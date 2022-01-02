package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91465
//Test Name:  CP_MT_Billing Address
//Description:
//Created By : Sunny Sok
//Created On : 02-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 04-Apr-2019
//**********************************************************************************************

public class TC91465 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"MyTrips","OneWay","DomesticDomestic","WithIn7Days","Adult","NineDFC","Connecting","BookIt","CarryOn",
                    "CheckedBags","NoSeats","MasterCard","ChangeFlight","AddEditBags","PaymentUI","CheckInOptions"})
    public void CP_MT_Billing_Address(@Optional("NA") String platform) {
        //****************************Navigate to Payment Page******************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91465 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_TYPE         = "NineDFCEmail";
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
        final String DEP_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Manage Travel Flight Availability Constant
        final String MANAGE_DEP_FLIGHT  = "Connecting";

        //Manage Travel Bags Page
        final String MYTRIP_BUY_BAGS    = "Purchase";
        final String MYTRIP_DEP_BAGS    = "Carry_1|Checked_1";

        //STEP--1
        //Prerequisites Book a Flight outside 24 hours with any number of PAX. Then enter your PNR to go to the reservation summary page and make chages to the booking continue to the payment page. Make sure you are logged in as a 9DFC member
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //login to application with member login
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_TYPE);
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

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //*****************************Start of Manage Travel******************************/
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Reservation Summary Page
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        //wait till pop up appear on page
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on Departing edit box
//        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep","NA","NA","30");

        //click on continue button
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

        //Flight Availability common Methods
//        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", MANAGE_DEP_FLIGHT);

        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("dep","9DFC","Cheap");

        //9FC PRICE FOR COMPARISON ON PAYMENT PAGE
        String Str_9FCPrice = getDriver().findElement(By.xpath("(//div[contains(@class,'fare-club')]//p)[2]")).getText();

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //Wait for Bags pop up
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on dont purchase Bags
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(MYTRIP_BUY_BAGS);

        //buy bags on My Trip
        pageMethodManager.getBagsPageMethods().selectDepartingBags(MYTRIP_DEP_BAGS);
        String myTripsBagsPrice = pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText();

        double myTripsTotal = Double.parseDouble(myTripsBagsPrice.replace("$","")) +  Double.parseDouble(Str_9FCPrice.replace("$",""));


        pageMethodManager.getBagsPageMethods().checkInContiueWithBag();

        //Click on dont purchase seats
//        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup("DontPurchase");

        //pageObjectManager.getBagsPage().getCheckInPurchaseSeatPopupDontPurchaseMySeatsButton().click();


        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Check In Option method
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User verify Navigation to Extras page",
                getDriver().getCurrentUrl().contains("my-trips/extras"));
        WaitUtil.untilTimeCompleted(2000);

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());


//        /************************Validation Payment Page****************************************/
        //Constant Values to validate
        final String PAYMENT_URL = "my-trips/payment";
        final String CONFIRMATION_URL = "/my-trips/confirmation";
        final String PRE_BILLING_ADDRESS = "Please use";
        final String POST_BILLING_ADDRESS = "for my billing information.";

        //declare variable used in validation
        String billingAddress;

        //--Step 2
        //Validate User reaches payment page
        ValidationUtil.validateTestStep("User verify Navigation to Manage Travel Confirmation page",
                getDriver().getCurrentUrl().contains(PAYMENT_URL));
        //MUST CLICK NO TO TRAVEL GUARD TO GET pre-saved credit card drop down
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

        //--Step 3
        //Within pre-saved credit card drop down, select Other. Now, locate the Billing Address checkbox - by default the checkbox should be checked
        pageMethodManager.getPaymentPageMethods().fillAnotherCardPaymentDetailsModifyPath(CARD_TYPE);

        //wait for page load is completed
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate the checkbox is present and check mark is pre-filled
        ValidationUtil.validateTestStep("User verify Same Address checkbox is selected on Manage Travel Payment Page",
                pageObjectManager.getPaymentPage().getUseSameAddressCheckBox().isSelected());

        //get member enrollment data
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");
        //--Step 4
        //A verbiage should be aligned to the right of the checkbox that reads Please use {XXX Street, XXX City, XXX State} for my Billing Address
        //get the billing address
        billingAddress = pageObjectManager.getPaymentPage().getUseSameAddressLabel().getText();
        //Validate verbiage and font
        ValidationUtil.validateTestStep("User verify constant verbiage in address on Manage Travel Payment Page",
                billingAddress.contains(PRE_BILLING_ADDRESS) && billingAddress.contains(POST_BILLING_ADDRESS));

        ValidationUtil.validateTestStep("User verify Street, City and State in address on Manage Travel Payment Page",
                billingAddress.contains(memberEnrollmentData.address1) && billingAddress.contains(memberEnrollmentData.city) && billingAddress.contains(memberEnrollmentData.zipCode) );

        //--Step5
        //With the checkbox being pre-checked by default, verify the address fields are suppressed
        //Validate all Billing Address fields are suppressed - screenshot attached
        ValidationUtil.validateTestStep("User verify Address Textbox is not displayed on Manage Travel Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getAddressTextbox()));
        ValidationUtil.validateTestStep("User verify City Textbox is not displayed on Manage Travel Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getCityTextbox()));
        ValidationUtil.validateTestStep("User verify State Textbox is not displayed on Manage Travel Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getStateTextBox()));
        ValidationUtil.validateTestStep("User verify ZipCode Textbox is not displayed on Manage Travel Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getZipCodeTextbox()));
        ValidationUtil.validateTestStep("User verify Country Drop Down is not displayed on Manage Travel Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getCountryDropdown()));

        //--Step6
        //Verify the pricing on the booking is correct
        ValidationUtil.validateTestStep("User verify $9 FARE CLUB Flight price on FA page matches on Payment page",
                pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().contains("$" + myTripsTotal));

        //--Step 7
        //Unselect the checkbox
        pageObjectManager.getPaymentPage().getUseSameAddressLabel().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate
        //Checkbox should be cleared and Billing Address fields should expand for data entry
        ValidationUtil.validateTestStep("User verify Same Address Checkbox should be cleared on Manage Travel Payment Page",
                !pageObjectManager.getPaymentPage().getUseSameAddressCheckBox().isSelected());
        ValidationUtil.validateTestStep("User Verify  Address field are displayed for data entry on Manage Travel Payment Page",
                pageObjectManager.getPaymentPage().getAddressTextbox().isDisplayed());
        ValidationUtil.validateTestStep("User Verify City fields are displayed for data entry on Manage Travel Payment Page",
                pageObjectManager.getPaymentPage().getCityTextbox().isDisplayed());
        ValidationUtil.validateTestStep("User Verify State field are displayed for data entry on Manage Travel Payment Page",
                pageObjectManager.getPaymentPage().getStateTextBox().isDisplayed());
        ValidationUtil.validateTestStep("User Verify Zip Code fields are displayed for data entry on Manage Travel Payment Page",
                pageObjectManager.getPaymentPage().getZipCodeTextbox().isDisplayed());
        ValidationUtil.validateTestStep("User Verify Country fields are displayed for data entry on Manage Travel Payment Page",
                pageObjectManager.getPaymentPage().getCountryDropdown().isDisplayed());


        //--Step 8
        //Enter a new billing address and complete payment using any credit card
        pageObjectManager.getPaymentPage().getAddressTextbox().sendKeys("NEW ADDRESS " + memberEnrollmentData.address1);
        pageObjectManager.getPaymentPage().getCityTextbox().sendKeys(memberEnrollmentData.city);
        pageObjectManager.getPaymentPage().getZipCodeTextbox().sendKeys(memberEnrollmentData.zipCode);
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getStateDropdown(),memberEnrollmentData.state);
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getCountryDropdown(),memberEnrollmentData.country);

        //manage travel payment methods
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //pageObjectManager.getPaymentPage().getContunueButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilPageURLVisible(getDriver(), CONFIRMATION_URL);

        ValidationUtil.validateTestStep("User verify Navigation to Manage Travel Confirmation page",
                getDriver().getCurrentUrl().contains(CONFIRMATION_URL));
    }
}