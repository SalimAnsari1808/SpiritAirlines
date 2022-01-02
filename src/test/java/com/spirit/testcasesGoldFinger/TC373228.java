package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//TODO: [IN:25646]	GoldFinger R1: CP: CI: Payment Page PMT: "or similar" extra verbiage displayed on car section
//Test CaseID: TC373228
//Title      : CP - Car Verbiage - Hertz - Check-In Upsell - Validate correct verbiage displays
//Created By : Kartik Chauhan
//Created On : 21-Nov-2019
//Reviewed By: Gabriela
//Reviewed On: 6-Dec-2019
//**********************************************************************************************

public class TC373228 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "CheckIn", "RoundTrip", "DomesticDomestic", "WithIn7Days", "Adult", "Guest", "NonStop", "BookIt",
                     "NoBags","NoSeats","CheckInOptions","Discover","Cars","PaymentUI"})
    public void Car_Verbiage_Hertz_Check_In_Upsell_Validate_correct_verbiage_displays(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373228 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE2         = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE2          = "0";
        final String ARR_DATE2          = "1";
        final String ADULTS             = "3";
        final String CHILDREN           = "0";
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
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE2, ARR_DATE2);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
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

        //Options page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());


        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bag Page constant values
        final String CHECKIN_BAG_PURCHASE = "DontPurchase";

        //Seat Page constant values
        final String CHECKIN_SEAT_PURCHASE = "DontPurchase";

        //Car Page constant values
        final String CHECKIN_CAR_PURCHASE = "DontPurchase";

        ValidationUtil.validateTestStep("Verify verbiage of Checkin and Get Boarding Pass button",
                pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).isDisplayed());

//STEP--4
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //do not select Bag
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECKIN_BAG_PURCHASE);

        //do not select Seat
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(CHECKIN_SEAT_PURCHASE);
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getCarPage().getViewAllCarsButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating car name on Car Page",
                pageObjectManager.getCarPage().getCarsPageCarPanels().get(0).isDisplayed());

        int Cars = pageObjectManager.getCarPage().getCarsPageCarPanels().size();
        List<WebElement> bookCarButton = getDriver().findElements(By.xpath("//button[contains(text(),'Book Car') or contains(text(),'Reservar Auto')]"));

        for (int count = 0; count < Cars; count++) {
            System.out.println(count);
            //Verifying Car logo is displayed
            ValidationUtil.validateTestStep("Verifying Car logo is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().get(count)));

            //Verifying Car Rental Img is displayed
            ValidationUtil.validateTestStep("Verifying Car Rental image is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRentalImg().get(count)));

            //Verifying Car type is displayed
            ValidationUtil.validateTestStep("Verifying Car type is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarTypeText().get(count)));


            //Verifying Car type is displayed
            ValidationUtil.validateTestStep("Verifying user icon is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageSeatIcon().get(count)));

            //Verifying Car type is displayed
            ValidationUtil.validateTestStep("Verifying number of seat is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageSeatNumber().get(count)));

            //Verifying Car type is displayed
            ValidationUtil.validateTestStep("Verifying verbiage 'per day'is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPagePricePerDay().get(count)));

            //Verify excludes taxes link is displayed
            ValidationUtil.validateTestStep("Verify excludes taxes link is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageExcludesTaxesLink().get(count)));

            //Verify excludes taxes link is blue
            ValidationUtil.validateTestStep("Verify BOOK CAR button is displayed",
                    TestUtil.verifyElementDisplayed(bookCarButton.get(count)));
        }

        pageObjectManager.getCarPage().getCarsPageExcludesTaxesLink().get(0).click();
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getCarPage().getIncludeCarTaxesPopupCloseWindowButton().click();
        WaitUtil.untilTimeCompleted(2000);

        scenarioContext.setContext(Context.CAR_DETAILS,pageObjectManager.getCarPage().getCheckInCarTypeText().get(0).getText());
        bookCarButton.get(0).click();
        //wait till 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        WaitUtil.untilTimeCompleted(2000);
        WaitUtil.untilPageLoadComplete(getDriver());

        //TODO: car section is displaying differently on Booking Path and on Check-in Path(in discussion)
        //Validating Car Company
        //TODO: [IN:25646]	GoldFinger R1: CP: CI: Payment Page PMT: "or similar" extra verbiage displayed on car section
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getCarPage().getPaidAtRentalCarCounterText().click();

        WaitUtil.untilTimeCompleted(2000);
        //Verify Car Rental Price displayed
        ValidationUtil.validateTestStep("Verify Car prices are displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getBreakdownCarPricerText()));

        ValidationUtil.validateTestStep("Validating Hertz Terms and Conditions displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTermsAndConditionsHertzLabel()));
    }
}
