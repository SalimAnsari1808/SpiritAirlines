package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.managers.PageMethodManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373309
//Description: Task:27786 | TC373309- 003 - CP - Vacation Flight + Car - Validate the Facade for a booking with an Active Duty Military Passenger
//Created By: Anthony Cardona
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373309 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"RoundTrip" , "FlightCar", "DomesticDomestic", "Guest" , "Military" , "Outside21Days", "Adult", "CarryOn" , "CheckedBags" , "NoSeats" , "Cars" , "CarsUI" , "MasterCard"})

    public void Vacation_Flight_Car_Validate_the_Facade_for_a_booking_with_an_Active_Duty_Military_Passenger(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Payment Page*******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373309 under GOLDFINGER Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE       = "English";
        final String JOURNEY_TYPE   = "Vacation";
        final String TRIP_TYPE      = "flight+car";
        final String DEP_AIRPORTS   = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS   = "AllLocation";
        final String ARR_AIRPORT_CODE = "LAS";
        final String DEP_DATE       = "100";
        final String ARR_DATE       = "103";
        final String ADULTS         = "1";
        final String CHILD          = "0";
        final String INFANT_LAP     = "0";
        final String INFANT_SEAT    = "0";
        final String DRIVERS_AGE    = "25+";

        final String DEP_BAGS       = "Carry_1|Checked_1";
        final String BAGS_FARE      = "Standard";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step2: Start the Vacation (Flight + Car) booking process for 1 ADT (Military passenger), 3 months out
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVERS_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 3: Verify the page layout will display in tiles
        ValidationUtil.validateTestStep("There are car tiles displayed on the Flight + Car page" , pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().size() > 0 );

//Step 4: Scroll to the bottom and verify there is not an option to continue without car selection
        ValidationUtil.validateTestStep("There is no option to continue without cars" , !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton()));

//- Step 5: Under the "Choose Your Hotel" section, validate there is a vertical section left aligned with filters
        ValidationUtil.validateTestStep("Validating vertical filter section is displayed on cars page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().geHotelFilterContainerPanel()));

        ValidationUtil.validateTestStep("Validating Price filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getPriceFilterLabel()));
        ValidationUtil.validateTestStep("Validating Seats filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilterLabel()));
        ValidationUtil.validateTestStep("Validating Bags filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getBagsFilterLabel()));
        ValidationUtil.validateTestStep("Validating Rental Agency filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getRentalAgencyFilterLabel()));
        ValidationUtil.validateTestStep("Validating Car Type filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarTypeFilterLabel()));
        ValidationUtil.validateTestStep("Validating Car Options filter is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarOptionsFilterLabel()));

//Step 6: Verify on the top left corner of the uppermost Car tile is the available car count:
        int carResultCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace(" Cars" , "").trim());
        ValidationUtil.validateTestStep("The car count is displayed onto the car section" , carResultCount > 0);

//Step 7: Verify on the upper right of the top car tile is the "Sort by:" with the following sort buttons: RECOMMENDED ,  PRICE  , SEATS , CAR TYPE
        ValidationUtil.validateTestStep("The sort by RECOMMENDED option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByRecommendedButton()));
        ValidationUtil.validateTestStep("The sort by PRICE option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByPriceButton()));
        ValidationUtil.validateTestStep("The sort by SEATS option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortBySeatsButton()));
        ValidationUtil.validateTestStep("The sort by CAR TYPE option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByCarTypeButton()));


//Step 8: Verify each Content box will display:
        for( int i = 0 ; i < pageObjectManager.getCarPage().getCarCardCompanyNameText().size() ; i ++)
        {
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRentalImg().get(i)) == false)
                ValidationUtil.validateTestStep("The Car image is not displayed for card number " + (i+1) ,false);

            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().get(i)) == false)
                ValidationUtil.validateTestStep("The car Company text is not displayed for card number " + (i+1) ,false);

            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarModelText().get(i)) == false)
                ValidationUtil.validateTestStep("The Type and Name of car text is not displayed for card number " + (i+1) ,false);

            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().get(i)) == false)
                ValidationUtil.validateTestStep("The Car Component Seats Icons is not displayed for card number " + (i+1) ,false);

            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardNumberOfBagsText().get(i)) == false)
                ValidationUtil.validateTestStep("The Car Component Bags Icons is not displayed for card number " + (i+1) ,false);

            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardTransmissionTypeText().get(i)) == false)
                ValidationUtil.validateTestStep("The Car Component Transmission Icons is not displayed for card number " + (i+1) ,false);

            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardMileageLimitText().get(i)) == false)
                ValidationUtil.validateTestStep("The Car Component Mileage Limit Icons is not displayed for card number " + (i+1) ,false);

            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i)) == false)
                ValidationUtil.validateTestStep("The per person pricing in USD text is not displayed for card number " + (i+1) ,false);

            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(i)) == false)
                ValidationUtil.validateTestStep("The More link text is not displayed for card number " + (i+1) ,false);

            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageUpliftlnk().get(i)) == false)
                ValidationUtil.validateTestStep("The Uplift promotion when applicable text is not displayed for card number " + (i+1) ,false);

            if (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageBookButton().get(i)) == false)
                ValidationUtil.validateTestStep("The car BOOK  button is not displayed for card number " + (i+1) ,false);
        }

        ValidationUtil.validateTestStep("All content in each car block is displayed" , true);


//Step 9: Verify "includes taxes" is displayed
//        ValidationUtil.validateTestStep("The Includes Taxes text is displayed" ,  false);

//Step 10: Click on "More" link | Tile should expand with "Vehicle Description", "Policies", "Location",  Information tabs and "More " link is replaced by "Less " link
        WebElement firstMoreInfoLink = pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0);

        firstMoreInfoLink.click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The more info Vehicle Description  button is not displayed for card More Info Pop-up", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab()));
        ValidationUtil.validateTestStep("The more info Policies  button is not displayed for card More Info Pop-up" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpPoliciesTab()));
        ValidationUtil.validateTestStep("The more info Location  button is not displayed for card More Info Pop-up" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpLocationsTab()));

        ValidationUtil.validateTestStep("The More info link is turned into less info" , (!TestUtil.verifyElementDisplayed(firstMoreInfoLink)) &&
                (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageLessInfoLink())));

//Step 11: Click on "Less" link
        pageObjectManager.getCarPage().getCarsPageLessInfoLink().click();
        ValidationUtil.validateTestStep("After clicking on less info, the information collapses", !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab()));

//Step 12: Click on BOOK button
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA" , "NA");

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade("BookIt");


        //verify user navigated to passenger Information page
        final String PASSENGER_PAGE     = "book/passenger";
        ValidationUtil.validateTestStep("User reached the Passenger Info Page", getDriver().getCurrentUrl(),PASSENGER_PAGE);

//Step 13: Fill all required information on Passenger Information Page.
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 14: Select 1 carry-on and 1 checked bag
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(DEP_BAGS);
        checkMilitaryBagsPrice();
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 15: continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 16: select check-in free option and continue
        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
		pageMethodManager.getOptionsPageMethods().selectOptions("CheckInOption:MobileFree");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 17: click on "Verify" under the Active Military personnel section and use military credentials as per Roles and Credentials
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();

//Step 18: type in payment method, complete booking
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard("NotRequired");
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails("MasterCard");
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //cancel Car booking
        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();

        /*
        Cannot automate step 27-33 due to skyspeed validation
         */

    }

    private void checkMilitaryBagsPrice()
    {
        final String ZERO_BAG_PRICE = "$0.00";
        boolean statusFlag = true;

        //loop through all departing carry-on bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().size();bagsCounter++) {
            //check carry-on Bag price for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFlag = false;
            }
        }
        //Verify Departing Carry-On Bag prices
        ValidationUtil.validateTestStep("Verify Departing Carry-On Bag prices is zero on Bags Page", statusFlag);

        //set flag to true for returning carry-on bags
        statusFlag = true;

        //loop through all returning carry-on bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getReturningNextCarryOnPriceText().size();bagsCounter++) {
            //check carry-on Bag price for all passenger
            if(!pageObjectManager.getBagsPage().getReturningCarryOnPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFlag = false;
            }
        }

        //Verify Departing Carry-On Bag prices
        ValidationUtil.validateTestStep("Verify Departing Carry-On Bag prices is zero on Bags Page", statusFlag);

        //set flag to true for departing checked bags
        statusFlag = true;

        //loop through all departing checked bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().size();bagsCounter++) {
            //check checked bag bag prices for all passenger
            if(!pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFlag = false;
            }
        }

        //Verify Departing Checked Bag prices
        ValidationUtil.validateTestStep("Verify Departing Checked Bag prices is zero on Bags Page", statusFlag);

        //set flag to true for returning checked bags
        statusFlag = true;

        //loop through all returning checked bags
        for(int bagsCounter=0;bagsCounter<pageObjectManager.getBagsPage().getReturningCheckedBagPriceText().size();bagsCounter++) {
            //check checked bag bag prices for all passenger
            if(!pageObjectManager.getBagsPage().getReturningCheckedBagPriceText().get(bagsCounter).getText().trim().equals(ZERO_BAG_PRICE)) {
                //set flag to false
                statusFlag = false;
            }
        }

        //Verify Returning Checked Bag prices
        ValidationUtil.validateTestStep("Verify Departing Checked Bag prices is zero on Bags Page", statusFlag);
    }
}