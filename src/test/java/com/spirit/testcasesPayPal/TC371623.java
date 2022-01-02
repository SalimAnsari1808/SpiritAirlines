package com.spirit.testcasesPayPal;


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
//Test Case ID: TC375022
//Description: Validate_the_PayPal_accepted_option_does_not_disappear_when_PAY_MONTHLY_and_or_PAY_IN_FULL_options_are_selected
//Created By : Manasa Tilakraj
//Created On : 13-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC371623 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "OneWay", "DomesticDomestic", "Flight", "WithIn7Days", "Adult", "FreeSpirit",
            "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "ReservationUI", "PayPal"})
    public void Validate_the_PayPal_accepted_option_does_not_disappear_when_PAY_MONTHLY_and_or_PAY_IN_FULL_options_are_selected(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280085 under PayPal Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE = "Flight";
        final String LOG_IN = "FSEmail";
        final String TRIP_TYPE = "OneWay";
        final String DEP_AIRPORTS = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS = "AllLocation";
        final String ARR_AIRPORT_CODE = "LGA";
        final String DEP_DATE = "1";
        final String ARR_DATE = "NA";
        final String ADULTS = "1";
        final String CHILDREN = "0";
        final String INFANT_LAP = "0";
        final String INFANT_SEAT = "0";

        // Flight details constant values
        final String DEP_FLIGHT = "NonStop";
        final String FARE_TYPE = "Standard";
        final String UPGRADE_VALUE = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE = "CheckInOption:MobileFree";

        //Payment page values
        final String HEADER             = "PayPal";
        final String PAYPAL_ACCOUNT     = "AccountPayPalEmail";
        final String PAYPAL_TYPE        = "Credit Union";

        //Step 2: On the web, start booking a OW DOM as a FS member for  1 Pax, no bags, no seats, no extras and land on the Payment page
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOG_IN);

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

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Step 3: Payment on paypal
        pageObjectManager.getPaymentPage().getPayPalButton().click();
        pageMethodManager.getPaymentPageMethods().loginToPayPal(PAYPAL_ACCOUNT);
        pageMethodManager.getPaymentPageMethods().payWithPayPal(PAYPAL_TYPE);

        //pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();


        //Step 4: Validate Paypal options
        //Selected radio button
        if(pageObjectManager.getPaymentPage().getPaypalRadioButton().isSelected())
            System.out.println("Paypal selected");

        //PayPal Acceptance Mark
        ValidationUtil.validateTestStep("Validating Paypal is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().get_PayPalDisplay()));

        //PayPal email address
        ValidationUtil.validateTestStep("Validating Paypal email address is displayed",
                getDriver().findElement(By.xpath("//p[@class = 'pl-4 mb-0']")).isDisplayed());

        //Description will NOT display - invalid

        //Update link
        ValidationUtil.validateTestStep("Validating update link",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().get_PayPalUpdateLink()));


        //Step 5	Validate the Billing Information section is suppressed
        ValidationUtil.validateTestStep("Credit card under Payment Information is not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPaymentSectionCreditCardText()));


        //Step 6	Select the PAY IN FULL option and validate the PayPal option selection is removed but the content remains. 
        pageObjectManager.getPaymentPage().getPayInFullRadioButton().click();
        ValidationUtil.validateTestStep("Credit card under Payment Information is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPaymentSectionCreditCardText()));


        //Step 7	Select the PayPal option again and validate the Billing Information/Credit Card section is suppressed again.
        pageObjectManager.getPaymentPage().getPaypalRadioButton().click();

        ValidationUtil.validateTestStep("Credit card under Payment Information is not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPaymentSectionCreditCardText()));

        //Step 8	Select the PAY MONTHLY option and validate the PayPal option selection is removed but the content remains. 





    }
}
