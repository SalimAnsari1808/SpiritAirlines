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
//Test CaseID: TC373363
//Title      : 003 - CP - Car Verbiage - Hertz - Check-In Upsell - Validate Car content for a booking with an International Origin
//Created By : Kartik Chauhan
//Created On : 27-Nov-2019
//Reviewed By: Gabriela
//Reviewed On: 9-Dec-2019
//**********************************************************************************************

public class TC373363 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn", "RoundTrip", "ColombiaDomestic", "WithIn7Days", "Adult", "Guest", "Connecting", "BookIt",
                    "NoBags","NoSeats","CheckInOptions","MasterCard","PaymentUI","AddEditPassportInfo","CarsUI"})
    public void CP_Car_Verbiage_Hertz_Check_In_Upsell_Validate_Car_content_for_a_booking_with_an_International_Origin(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373363 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE = "English";
        final String JOURNEY_TYPE = "Flight";
        final String TRIP_TYPE2 = "RoundTrip";
        final String DEP_AIRPORTS = "AllLocation";
        final String DEP_AIRPORT_CODE = "CUN";
        final String ARR_AIRPORTS = "AllLocation";
        final String ARR_AIRPORT_CODE = "MCO";
        final String DEP_DATE2 = "0";
        final String ARR_DATE2 = "2";
        final String ADULTS = "1";
        final String CHILDREN = "0";
        final String INFANT_LAP = "0";
        final String INFANT_SEAT = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT = "Connecting";
        final String RET_FLIGHT = "Connecting";
        final String FARE_TYPE = "Standard";
        final String UPGRADE_VALUE = "BookIt";

        //Options Page Constant Values
        final String OPTION_VALUE = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE = "Mastercard";
        final String TRAVEL_GUARD = "NotRequired";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE2, ARR_DATE2);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("ret", RET_FLIGHT);
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
        WaitUtil.untilPageLoadComplete(getDriver());

        if (pageObjectManager.getPaymentPage().getNoTravelGuardLabel().isDisplayed())
        {
            pageObjectManager.getPaymentPage().getNoTravelGuardLabel().click();
        }

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

        ValidationUtil.validateTestStep("Verify verbiage of Check-in and Get Boarding Pass button",
                pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).isDisplayed());

        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassportPage().getPassengerPassportNumberTextBox().get(0).sendKeys("AKDPICD");

        //Set Greater then Today's Expiration Date
        String strDepDate = "150";
        String strActualDepDate;

        //Convert the format of Expiration date
        strActualDepDate = TestUtil.getStringDateFormat(strDepDate, "MM/dd/yyyy");

        //click on Passport Expiration Date Text Box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).click();

        //Send specific date to Expiration date Text box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).sendKeys(strActualDepDate);

        pageObjectManager.getPassportPage().getSaveChangesButton().click();
        WaitUtil.untilTimeCompleted(2000);

        pageObjectManager.getPassportPage().getContinueWithoutMiddleNameButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //do not select Bag
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECKIN_BAG_PURCHASE);

        //do not select Seat
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(CHECKIN_SEAT_PURCHASE);
        WaitUtil.untilTimeCompleted(2000);

        //STEP--15:
        pageObjectManager.getCarPage().getViewAllCarsButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating car name on Car Page",
                pageObjectManager.getCarPage().getCarsPageCarPanels().get(0).isDisplayed());

        int Cars = pageObjectManager.getCarPage().getCarsPageCarPanels().size();
        List<WebElement> bookCarButton = getDriver().findElements(By.xpath("//button[contains(text(),'Book Car') or contains(text(),'Reservar Auto')]"));
        for (int count = 0; count < Cars; count++) {

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
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageObjectManager.getCarPage().getPaidAtRentalCarCounterText().click();
        WaitUtil.untilTimeCompleted(2000);

        //Verify excludes taxes link is displayed
        ValidationUtil.validateTestStep("Verify Car prices are displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getBreakdownCarPricerText()));

    }
}
