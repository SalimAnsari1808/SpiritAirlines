package com.spirit.testcasesGoldFinger;

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
//Test Case ID: TC377266
//Description: Task 28162: TC377266 - 001 - CP - Flight Only - Options Page - Validate the Car Carousel and its components
//Created By: Gabriela
//Created On: 3-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC377266 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt", "NoBags", "NoSeats", "OptionalUI", "CarsUI"})
    public void CP_Flight_Only_Options_Page_Validate_the_Car_Carousel_and_its_components (@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC377266 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "15";
        final String ARR_DATE           = "17";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values:
        final String OPTIONS_PAGE       = "/book/options";
        final String CAROUSEL_TITLE     = "Savings on Cars";
        final String ADD_CAR_BUTTON     = "ADD CAR";
        final String NAV_URL            = "nav01a";

//- Step 1: Pre Requisite: You will need access to HBG. Ask your lead for help
//- Step 2: Access test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 3: Start booking a flight only RT DOM outside 7 days with destination to LAS or MCO
        //*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //*** Flight Availability Page **/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//- Step 4: Continue with the booking until you reach the Options Page.
        //*** Passenger Information Page **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        String firstName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value").toUpperCase();
        String lastName = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value").toUpperCase();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //*** Bags Page **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //*** Seats Page **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //*** Options Page **/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(OPTIONS_PAGE));

//- Step 5: Validate there is a up-sell car carousel displaying.
        ValidationUtil.validateTestStep("Validating Car Carousel is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCarouselTitleText()));

//- Step 6: Verify the Carousel heading says "Savings on Cars"
        ValidationUtil.validateTestStep("Validating 'Saving on Cars' title is displayed for the car carousel",
                pageObjectManager.getCarPage().getCarCarouselTitleText().getText().equals(CAROUSEL_TITLE));

//- Step 7: Validate the first card on the list is:
        //Invalid Validation with new format

//- Step 8: Look at the displaying car cards. Validate the car cards are sorted by pricing Low to High
        List<Double> carPrice = new ArrayList<>();

        for (int i = 0; i < pageObjectManager.getCarPage().getCarCardPriceLink().size(); i ++)
        {
           carPrice.add(Double.parseDouble(pageObjectManager.getCarPage().getCarCardPriceLink().get(i).getText().replace("$","")));
        }
        System.out.println("carPrice: " + carPrice);

        for (int i = 0;  i < carPrice.size(); i ++)
        {
            if (i==carPrice.size()-1)
            {
                break;
            }
            ValidationUtil.validateTestStep("Validating Price are sorted by low to high",
                    carPrice.get(i) <= carPrice.get(i+1));
        }

//- Step 9: Count the tiles/cards included on the carousel
        //Invalid Step

//- Step 10: Look at the upper right side of a car tile/card.
        boolean carMakeFlag = true;
        for (int i = 0; i < pageObjectManager.getCarPage().getCarCardNameText().size(); i ++)
        {
            ValidationUtil.validateTestStep("Validating car supplier information displayed",
                    pageObjectManager.getCarPage().getCarCardCompanyNameText().get(i).getText().contains("Dollar")
                            ||pageObjectManager.getCarPage().getCarCardCompanyNameText().get(i).getText().contains("Thrifty")
                            ||pageObjectManager.getCarPage().getCarCardCompanyNameText().get(i).getText().contains("Hertz"));

//- Step 11: Look below the Car Image
            ValidationUtil.validateTestStep("Validating the Car make and model are displaying including the 'or similar' verbiage",
                              pageObjectManager.getCarPage().getCarCardNameText().get(i).getText().contains("similar"));

//- Step 12: Under the Car Make and Model, locate the Car size
            ValidationUtil.validateTestStep("Validating the Car make and model are displaying including the 'or similar' verbiage",
                    pageObjectManager.getCarPage().getCarCardCompanyNameText().get(i).getText().contains("Compact")
                            ||pageObjectManager.getCarPage().getCarCardCompanyNameText().get(i).getText().contains("Mini")
                            ||pageObjectManager.getCarPage().getCarCardCompanyNameText().get(i).getText().contains("Full-Size")
                            ||pageObjectManager.getCarPage().getCarCardCompanyNameText().get(i).getText().contains("Standard")
                            ||pageObjectManager.getCarPage().getCarCardCompanyNameText().get(i).getText().contains("Economy")
                            ||pageObjectManager.getCarPage().getCarCardCompanyNameText().get(i).getText().contains("Intermediate")
                            ||pageObjectManager.getCarPage().getCarCardCompanyNameText().get(i).getText().contains("Luxury"));

//- Step 13: Following you should see Car icons
            //Invalid Step

//- Step 14: Verify the following are displaying:
            //Seat count
            ValidationUtil.validateTestStep("Seats information displayed on the carousel",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardSeatsText()));
            //Bag count
            ValidationUtil.validateTestStep("Seats information displayed on the carousel",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardBaggageText()));
            //Transmission type
            ValidationUtil.validateTestStep("Seats information displayed on the carousel",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardTransmissionText()));
            //Mileage
            ValidationUtil.validateTestStep("Seats information displayed on the carousel",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardMileageLinkText()));

//- Step 15: Look at the button on each car tile/card.
        ValidationUtil.validateTestStep("Validating right button information displayed",
                pageObjectManager.getCarPage().getCarsCarouselAddCarButton().get(i).getText().equals(ADD_CAR_BUTTON));
        }

//- Step 16: Click "ADD CAR" on one of the car tiles/cards.
        pageObjectManager.getCarPage().getCarsCarouselAddCarButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 17: Select a primary driver and continue
        //TODO: No Primary driver selection need it when only 1 passenger with more than 21 years is on the booking
        if (getDriver().getCurrentUrl().contains(NAV_URL)) {
            TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(), firstName + " " + lastName);
            WaitUtil.untilTimeCompleted(1200);

           // pageObjectManager.getCarPage().getBookCarButton().get(0).click();
            pageObjectManager.getCarPage().getWhoSDrivingVerifyAndBookThisCarButton().click();

        }


//- Step 18: Look at the button previously labeled "ADD CAR"
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating ADD CAR is replaced by Remove link button",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRemoveButton()));

    }
}