package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test Case ID: TC373308
//Description: Task:27930 | TC373308 - 002 - CP - Vacation Flight + Car - Validate the Facade for a booking with an Infant on Lap
//Created By: Anthony Cardona
//Created On: 22-Nov-2019
//Reviewed By: kartik Chauhan
//Reviewed On:11 -Dec-2019
//**********************************************************************************************
public class TC373308 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"FlightCar", "DomesticDomestic", "Guest" , "Outside21Days", "Adult", "InfantLap" , "NoBags" , "NoSeats" ,
                    "Cars" , "CarsUI","MasterCard"})

    public void CP_Vacation_Flight_Car_Validate_the_Facade_for_a_booking_with_an_Infant_on_Lap(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Payment Page*******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373308 under GOLDFINGER Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "flight+car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "100";
        final String ARR_DATE           = "103";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";
        final String DRIVERS_AGE        = "25+";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step2: Start the Vacation (Flight + Car) booking process for DOM, 1 ADT and 1 INFT(LAP CHILD), 3 months out. Click Search Vacation
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVERS_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 3: Fill out infant child of birth and choose lap child and click continue
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 4: Verify the page layout will display in tiles
        ValidationUtil.validateTestStep("There are car tiles displayed on the Flight + Car page" , pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().size() > 0 );

//Step 5: Scroll to the bottom and verify there is not an option to continue without car selection
        ValidationUtil.validateTestStep("There is no option to continue without cars" , !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton()));

//- Step 5: Under the "Choose Your Hotel" section, validate there is a vertical section left aligned with filters
        ValidationUtil.validateTestStep("Validating vertical filter section is displayed on cars page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().geHotelFilterContainerPanel()));

// Step 6: Verify on the left side of the Car tiles are a series of filters with expandable carets: Price , Seat , Bag , Rental Agency , Car Type, Car Options
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

//Step 7: Verify on the top left corner of the uppermost Car tile is the available car count:
        int carResultCount = Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace(" Cars" , "").trim());
        ValidationUtil.validateTestStep("The car count is displayed onto the car section" , carResultCount > 0);

//Step 8: Verify on the upper right of the top car tile is the "Sort by:" with the following sort buttons: RECOMMENDED ,  PRICE  , SEATS , CAR TYPE
        ValidationUtil.validateTestStep("The sort by RECOMMENDED option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByRecommendedButton()));
        ValidationUtil.validateTestStep("The sort by PRICE option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByPriceButton()));
        ValidationUtil.validateTestStep("The sort by SEATS option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortBySeatsButton()));
        ValidationUtil.validateTestStep("The sort by CAR TYPE option is displayed" ,TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByCarTypeButton()));


//Step 9: Verify each Content box will display:
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


//Step 10: Verify "includes taxes" is displayed
//        ValidationUtil.validateTestStep("The Includes Taxes text is displayed" ,  false);

//Step 11: Click on "More" link | Tile should expand with "Vehicle Description", "Policies", "Location",  Information tabs and "More " link is replaced by "Less " link
        WebElement firstMoreInfoLink = pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0);

        firstMoreInfoLink.click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The more info Vehicle Description  button is not displayed for card More Info Pop-up", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab()));
        ValidationUtil.validateTestStep("The more info Policies  button is not displayed for card More Info Pop-up" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpPoliciesTab()));
        ValidationUtil.validateTestStep("The more info Location  button is not displayed for card More Info Pop-up" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpLocationsTab()));

        ValidationUtil.validateTestStep("The More info link is turned into less info" , (!TestUtil.verifyElementDisplayed(firstMoreInfoLink)) &&
                (TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageLessInfoLink())));

//Step 12: Click on "Less" link
        pageObjectManager.getCarPage().getCarsPageLessInfoLink().click();
        ValidationUtil.validateTestStep("After clicking on less info, the information collapses", !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab()));

//Step 13: Click on BOOK button
        pageObjectManager.getCarPage().getCarsPageBookButton().get(0).click();
        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade("BookIt");


        //verify user navigated to passenger Information page
        final String PASSENGER_PAGE     = "book/passenger";
        ValidationUtil.validateTestStep("User reached the Passenger Info Page", getDriver().getCurrentUrl(),PASSENGER_PAGE);

//Step 14: Fill all required information on Passenger Information Page. Continue with booking through the Bags >Seats>Options pages and proceed to the Payment page.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        pageMethodManager.getPaymentPageMethods().selectTravelGuard("NotRequired");
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails("MasterCard");
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //cancel Car booking
        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
    }
}