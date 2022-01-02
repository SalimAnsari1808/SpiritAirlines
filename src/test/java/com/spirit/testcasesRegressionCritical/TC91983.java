package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91983
//Test Case Name: Task 24880: 35372 Uplift_CP_BP_Flight Only_NEG_Guest_9DFC Fare
//Created By: Gabriela
//Created On: 01-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 05-Aug-2019
//**********************************************************************************************

public class TC91983 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn21Days" , "Adult" , "NineDFC" , "Connecting","PassengerinfoSignUp","BagsUI"
            , "BookIt" , "CarryOn" , "Standard" , "DynamicShoppingCartUI" , "OptionalUI" ,"ShortCutBoarding", "CheckInOptions" , "Visa" , "PaymentUI"})
    public void Uplift_CP_BP_Flight_Only_NEG_Guest_9DFC_Fare(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91983 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

// Step 1: Book as Guest
        //Home Page Constant variables
        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE                  = "RoundTrip";
        final String DEP_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "LGA";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "LAS";
        final String DEP_DATE                   = "60";
        final String ARR_DATE                   = "63";
        final String ADULT                      = "1";
        final String CHILD                      = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                 = "Connecting";
        final String RET_FLIGHT                 = "Connecting";
        final String FARE_TYPE                  = "Member";
        final String UPGRADE_VALUE              = "BookIt";

        //Passenger information Constant Values
        final String PASSENGER_URL              = "/book/passenger";

        //Bags Page Constant Values
        final String BAGS_URL                   = "/book/bags";
        final String DEP_BAGS                   = "Carry_1";

        //Seats Page Constant Values
        final String SEATS_URL                  = "/book/seats";
        final String DEP_SEATS                  = "Standard||Standard";
        final String RET_SEATS                  = "Standard||Standard";

        //Options Page Constant Values
        final String OPTIONS_URL                = "/book/options";
        final String OPTION_VALUE               = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String PAYMENT_URL                = "/book/payment";
        final String CARD_TYPE                  = "VisaCard";
        final String TRAVEL_GUARD               = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS             = "Confirmed";
        final String CONFIRMATION_URL           = "book/confirmation";

//--Step 2: Access test environment
        //open browser
        openBrowser(platform);

        //*********************************HOME PAGE******************************************************/
//-- Step 3: Start a Flight Only booking outside of 7 days
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);

//-- Step 4 and 5: Select flight(s) and continue with $9 Fare Club Price
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//-- Step 5: Select "Bare Fare" option by clicking "Continue with Bare Fare" button
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //*********************************Passenger Information Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify Passenger Information Page URL", getDriver().getCurrentUrl(),(PASSENGER_URL));

//-- Step 6: Verify passenger information.
        ValidationUtil.validateTestStep("Uplift tag is not displayed. Since member decided to sign up for 9DFC, UPLIFT will no longer be an option",
                getDriver().findElement(By.xpath("//app-uplift-pricing")).getText().equals(""));

//-- Step 7: Continue to the next page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().get9FCUpselSignUpChoosePasswordTextBox().sendKeys("Spirit1!");
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpConfirmPasswordTextBox().sendKeys("Spirit1!");

        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpWithEmailButton().click();

        //*********************************Bags Page Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify Bags Page URL",
                getDriver().getCurrentUrl(),(BAGS_URL));

//-- Step 8: Add Carry On. Note: Make sure the booking goes over $300. If it hasn't, add bags. Continue to the next page
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        String itinerary = pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$","");
        double itineraryPrice = Double.parseDouble(itinerary.replace(",", ""));

        ValidationUtil.validateTestStep("Validating itinerary is over $300", itineraryPrice > 300);
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //*********************************Seats Page Methods*************************************************/
        ValidationUtil.validateTestStep("Verify Seats Page URL",
                getDriver().getCurrentUrl().contains(SEATS_URL));

//-- Step 9: Select seat(s) and continue to the next page
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //*********************************Options Page Methods*************************************************/
        ValidationUtil.validateTestStep("Verify Options Page URL",
                getDriver().getCurrentUrl(),(OPTIONS_URL));

//-- Step 10: Verify the uplift tag is NOT displaying within the Hotel, Car and Activities sections.
        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //Storing flight fare for  comparision
        String farePrice        = pageObjectManager.getHeader().getFlightPriceItineraryText().getText().replace("$", "");
        double farePriceValue   = Double.parseDouble(farePrice.replace(",",""));

        List<Double> carPriceList = new ArrayList<>();
        List<Double> carPriceValue = new ArrayList<>();

        for (int count = 0; count < pageObjectManager.getCarPage().getCarCardPriceLink().size(); count++) {
            if (pageObjectManager.getCarPage().getCarCardPriceLink().get(count).isDisplayed()) {
                carPriceList.add( Double.parseDouble(pageObjectManager.getCarPage().getCarCardPriceLink().get(count).getText().replace("$","")));
            }
        }

        //Looping into car tiles for uplift suppressed validation when total booking price is under $200
        for (int count = 0; count < pageObjectManager.getCarPage().getCarsPanel().size(); count++) {
            //If car tile is present...
            if (pageObjectManager.getCarPage().getCarsPanel().get(count).isDisplayed()) {
                //Calculating total price
                double totalBookingPrice = carPriceList.get(count) + farePriceValue;

                if (totalBookingPrice < 200) {
                    ValidationUtil.validateTestStep("Verifying car uplift is not displayed in this case that the total booking price is " + totalBookingPrice + " , under $200",
                            pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(count).getText().equals(""));
                }else {
                    ValidationUtil.validateTestStep("Verifying car uplift is displayed in this case that the total booking price is " + totalBookingPrice + " , above $200",
                            TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(count)));
                }

            }
        }

//-- Step 11: On the Options page, select the Check in option and continue to the next page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify Payment Page URL", getDriver().getCurrentUrl().contains(PAYMENT_URL));

//-- Step 12: Verify there is NOT an option to pay monthly under the Payment Information section
        ValidationUtil.validateTestStep("Verifying uplift is not displayed on Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDuePayMonthlyText()));

//-- Step 13: Finish the booking by making a credit card payment
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //***********************************Confirmation Page Method**************************************************/
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