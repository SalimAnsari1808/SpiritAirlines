package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.By;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91262
//Description: Uplift_CP_BP_Vacation_Flight + Hotel_Uplift offered when the booking is more than $200
//Created By : Anthony Cardona
//Created On : 12-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 20-Aug-2019
//**********************************************************************************************
public class TC91262 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"BookPath", "FlightHotel", "RoundTrip", "DomesticDomestic", "Outside21Days" ,"BundleIt", "CarryOn", "CheckedBags","FlightFlex","ShortCutBoarding","CheckInOptions", "OptionalUI","HomeUI"})
    public void Uplift_CP_BP_Vacation_Flight_Hotel_Uplift_offered_when_the_booking_is_more_than_$200(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91262 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "10";
        final String ARR_DATE           = "15";
        final String ADULTS             = "8";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String HOTELROOM          = "1 Room";

        final String UPGRADE_VALUE      = "BundleIt";

        final String BAGS_PRESELECTED   = "1";
        final String BAGS_INCLUDED	    = "Included";

        //option page constant value
        final String OPTION_PAGE        = "CheckInOption:MobileFree";
        final String CAR_URL            = "book/options/cars";

        //open browser
        openBrowser(platform);
//Step 1,2 Access the test environment select vacation booking
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);

        pageObjectManager.getHomePage().getPassengerBox().click();
//Step 3: Validate that you cant add more than 8 passengers for a vacation booking
        ValidationUtil.validateTestStep("The adults plus button is disabled" ,
                pageObjectManager.getHomePage().getAdultPlusLink().getAttribute("class"),("disabled"));

        int paxCount = Integer.parseInt(ADULTS);
        while (paxCount > 1) {
            pageObjectManager.getHomePage().getAdultMinusLink().click();
            paxCount--;
        }
        pageObjectManager.getHomePage().getPassengerBox().click();

        pageMethodManager.getHomePageMethods().selectTravellingPassenger("2", CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 3: Select a room and continue to the payment page
        //Validate hotel Uplift displayed
//        ValidationUtil.validateTestStep("The Uplift is displayed" , pageObjectManager.getHotelPage().getHotelPageUpliftLink().get(0).isDisplayed());

        //Complete the flight + Hotel page
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);


        pageObjectManager.getHotelPage().getContinueButton().click();//click continue at the bottom of the page
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        //Salim Modification Required
        //pageObjectManager.getCarPage().getContinueWithoutCarButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

//Step 4: Select Boost It
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user taken to the Cars page" ,
                getDriver().getCurrentUrl(),CAR_URL);
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        //select upgrade value
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Step 5: Input passenger information

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        //Verifying there only 2 passengers present on Bags Page
        ValidationUtil.validateTestStep("Validating only 2 passengers can select bags",
                pageObjectManager.getBagsPage().getDepartingPassengerFlightContainerListText().size() <= 2);

        //ValidationUtil.validateTestStep("Verify 1-Carry-On and 1-Checked Bags are Pre-Selected for all passengers on Bags Page
        for(int counter = 0; counter < pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().size(); counter ++) {
            // Verifying Carry-on is preselected
            ValidationUtil.validateTestStep("Verifying Carry On is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(counter)),BAGS_PRESELECTED);

            //Verifying Carry On price is included
            ValidationUtil.validateTestStep("Verifying Carry On price is included",
                    pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(counter).getText(),BAGS_INCLUDED);

            // Verifying Checked Bag is preselected
            ValidationUtil.validateTestStep("Verifying 1 checked bag is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(counter)),BAGS_PRESELECTED);

            //Verifying Checked Bag price is included
            ValidationUtil.validateTestStep("Verifying Checked Bag price is included",
                    pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(counter).getText(),BAGS_INCLUDED);

        }

        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);

//Step 7: add seats
        int numberOfSegments  = pageObjectManager.getSeatsPage().getFlightLegsText().size();
        int selectSeatsCounter = 0;
        while(selectSeatsCounter < numberOfSegments * 2) {
            getDriver().findElement(By.xpath("//div[contains(@class,'seat-row-seat') and contains(@class,'standard') and contains(text(),'$')]")).click();
            selectSeatsCounter++;
        }

        //Seats page Methods
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//Step 8:Select check in option and continue
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_PAGE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);

//Step 9: Validate monthly payment section
        ValidationUtil.validateTestStep("The Uplift Option is available on the Payment page" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDuePayMonthlyText()));
//Step 10: Validate voucher and Credits
        ValidationUtil.validateTestStep("The Voucher section link is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditChevronLink()));
        ValidationUtil.validateTestStep("The credit card section is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getMemberEnrollmentPage().getnameOnCardText()));
    }
}