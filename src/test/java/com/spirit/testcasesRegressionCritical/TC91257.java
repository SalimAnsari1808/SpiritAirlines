package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


//**********************************************************************************************
//Test Case ID: TC91257
//Description: Task 24877: 35368 Uplift_CP_BP_Flight Only_Booking less than $200
//Created By : Gabriela
//Created On : 01-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 09-Aug-2019
//**********************************************************************************************

public class TC91257 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups={"BookPath", "RoundTrip", "DomesticDomestic","Adult","Guest", "Outside21Days" , "NonStop","BookIt", "NoBags", "NoSeats","CheckInOptions", "CarsUI","PaymentUI", "Visa"})
    public void Uplift_CP_BP_Flight_Only_Booking_less_than_$200(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91257 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAX";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "70";
        final String ARR_DATE           = "71";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String PAYMENT_URL        = "/book/payment";
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

//-- Step 1: Access test environment
        //open browser
        openBrowser(platform);

        /*****************************************Home Page Methods*****************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//-- Step 2: Start booking a Flight only
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*****************************************Flight Availability Page Methods*****************************************/
//-- Step 3: Select the a flight less than $200 and continue to the next page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//-- Step 4: Select "Bare Fare" option by clicking "Continue with Bare Fare" button
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*****************************************Passenger Info Page Methods*****************************************/
//-- Step 5: Enter Customer information and continue to the next page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /*****************************************Bags Page Methods*****************************************/
//-- Step 6: Continue without adding bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*****************************************Seats Page Methods*****************************************/
//-- Step 7: Continue without adding seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*****************************************Options Page Methods*****************************************/
//-- Step 8: Scroll down the Options page and validate there is no option to pay monthly for the Cars, Hotels or Activities offered. The attached image will show what you should NOT see
        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Storing flight fare for  comparision
        double farePriceValue = Double.parseDouble(pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$", ""));
        System.out.println("farePriceValue " + farePriceValue);

        List<Double> carPriceList = new ArrayList<>();
        List<Double> carPriceValue = new ArrayList<>();

        for (int i = 0; i < pageObjectManager.getCarPage().getCarCardPriceLink().size(); i ++)
        {
            if (pageObjectManager.getCarPage().getCarCardPriceLink().get(i).isDisplayed())
            {
                carPriceList.add( Double.parseDouble(pageObjectManager.getCarPage().getCarCardPriceLink().get(i).getText().replace("$","")));
                System.out.println("carPriceList " + carPriceList);
            }
        }

        //Looping into car tiles for uplift suppressed validation when total booking price is under $200
        for (int i = 0; i < pageObjectManager.getCarPage().getCarCardNameText().size(); i++)
        {
            //If car tile is present...
            if (pageObjectManager.getCarPage().getCarCardNameText().get(i).isDisplayed())
            {
                //Calculating total price
                double totalBookingPrice = carPriceList.get(i) + farePriceValue;
                System.out.println("totalBookingPrice " + totalBookingPrice);

                if (totalBookingPrice < 200)
                {
                    System.out.println("uplift under 200 " + pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(i).getText());
                    ValidationUtil.validateTestStep("Verifying car uplift is not displayed in this case that the total booking price is " + totalBookingPrice + " , under $200",
                            pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(i).getText().equals(""));
                }else
                {
                    ValidationUtil.validateTestStep("Verifying car uplift is displayed in this case that the total booking price is " + totalBookingPrice + " , above $200",
                            TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(i)));
                }

            }
        }

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*****************************************Payment Page Methods*****************************************/
//-- Step 9: Continue to the next page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify Payment Page URL",
                getDriver().getCurrentUrl(),PAYMENT_URL);

//-- Step 10: Verify there is NO option to pay monthly under the Payment Information section
        double totalPriceValue = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$",""));

        if (totalPriceValue < 200)
        {
            ValidationUtil.validateTestStep("Verifying car uplift is not displayed in this case that the total booking price is " + totalPriceValue + " , under $200",
                    pageObjectManager.getPaymentPage().getTotalDueUpLiftPriceText().getText().equals(""));
        }else
        {
            ValidationUtil.validateTestStep("Verifying car uplift is displayed in this case that the total booking price is " + totalPriceValue + " , above $200",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueUpLiftPriceText()));
        }

//-- Step 11: Verify the following sections are present:
        //Vouchers and Credits
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditChevronLink().click();
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Voucher section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getVoucherNumberTextBox()));

        ValidationUtil.validateTestStep("Reservation Credit section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getRedeemReservationCreditConfirmationCodeText()));

        //Credit card under Payment Information
        ValidationUtil.validateTestStep("Credit card under Payment Information is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPaymentSectionCreditCardText()));

//-- Step 12: Finish the booking by making a credit card payment
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /***********************************Confirmation Page Method**************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    }
}
