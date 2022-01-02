package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:15876] - CP: BP: Options page:  Cars or Hotels sections are not displayed for an International destination (CUN)
//Test Case ID: TC373886
//Description: Task 27784: TC373886- 008 - CP - Hub Packaging - View All Cars - Validate that the View All Cars link is present for a DOM-INT Booking
//Created By: Gabriela
//Created On: 28-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373886 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticInternational", "Outside21Days", "Adult", "Guest", "BookIt",
            "OptionalUI", "CarsUI", "NoBags","NoSeats"})
    public void CP_Hu_Packaging_View_All_Cars_Validate_that_the_View_All_Cars_link_is_present_for_a_DOM_INT_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373886 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_2";

        //Seats Page Constant Values
        final String DEP_SEATS          = "Premium";

        //Options Page Constant Values
        final String CARS_URL           = "/book/options/cars";
        final String OPTIONS_URL        = "/BOOK/OPTIONS";
//- Step 1: Open the GoldFinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Start a DOM-INT RT booking with 1 passenger outside 48 hours and click Search Flight button
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Choose connecting flights for POO and POD
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep",DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",ARR_Flight);

//- Step 4: Click Continue at the bottom of the page.
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 5: Select "Book it"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: Fill the requested information and select "Continue" at the bottom of the page.
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7: Select 1 Carry On and 2 Checked bags then Click "Continue" at the bottom of the page.
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

//- Step 8: Select "Exit row" Seats and Click "Continue"
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//- Step 9: Verify Cars section is shown on Options page
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Car section is displayed on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCarouselTitleText()));

//- Step 10: Verify under your ride tile that there is a "VIEW ALL CARS" link
        ValidationUtil.validateTestStep("Validating 'VIEW ALL CARS' link is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getViewAllCarsButton()));

//- Step 11: Click on "VIEW ALL CARS" link
        pageObjectManager.getCarPage().getViewAllCarsButton().click();

//- Step 12: Verify the "Price", "Seats", "Bags", "Rental Agency", "Car Type" and "Car Options" filters are present on the left side of the page.
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(CARS_URL));

        ValidationUtil.validateTestStep("Validating Price section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getPriceFilterLabel()));

        ValidationUtil.validateTestStep("Validating Seats section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilterLabel()));

        ValidationUtil.validateTestStep("Validating Bags section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getBagsFilterLabel()));

        ValidationUtil.validateTestStep("Validating Rental Agency section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getRentalAgencyFilterLabel()));

        ValidationUtil.validateTestStep("Validating Car Type section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarTypeFilterLabel()));

        ValidationUtil.validateTestStep("Validating Car Options section is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarOptionsFilterLabel()));

//- Step 13: Verify the following "Sort By:" options exist.
        //RECOMMENDED
        ValidationUtil.validateTestStep("Validating Sorting By Recommended option is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByRecommendedButton()));
        //PRICE
        ValidationUtil.validateTestStep("Validating Sorting By Price option is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByPriceButton()));
        //SEATS
        ValidationUtil.validateTestStep("Validating Sorting By Seats option is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortBySeatsButton()));
        //CAR TYPE
        ValidationUtil.validateTestStep("Validating Sorting By Car Type option is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByCarTypeButton()));

//- Step 14: Go to the Carnect website and select the same City and Dates. Reach out your team lead for gets the update URL (https://bridge.carnect.com/login) and credentials.
//- Step 15: Verify that the Carnect inventory and the Spirit list of available Cars is matching.
        //Unable to validate
//- Step 16: Verify each Content box is displaying:
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i++)
        {
            //Car image
                ValidationUtil.validateTestStep("Validating Car Image is displayed on card" + i,
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRentalImg().get(i)));

            //Company
            ValidationUtil.validateTestStep("Validating Car Company Logo is displayed on card" + i,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().get(i)));
            //Type and Name of car
            ValidationUtil.validateTestStep("Validating Type and Name of car is displayed on card" + i,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarTypeText().get(i)));
            //More Info
            ValidationUtil.validateTestStep("Validating More Link is displayed on card" + i,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(i)));
            //Tax hyperlink
            ValidationUtil.validateTestStep("Validating Includes Taxes Link is displayed on card" + i,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageIncludesTaxesLink().get(i)));
            //The pricing in USD
            ValidationUtil.validateTestStep("Validating Includes Taxes Link is displayed on card" + i,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i)));
            //Uplift promotion when applicable
//           ValidationUtil.validateTestStep("Validating Uplift promotion when applicable is displayed on card" + i,
//                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(i)));
            //An action button to BOOK the car
            ValidationUtil.validateTestStep("Validating action button to BOOK the car is displayed on card" + i,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageBookButton().get(i)));
        }

//- Step 17: Click on "More Info" link
        boolean vehicleDescription = true;
        boolean policies = true;
        boolean location = true;

        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i++)
        {
            pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(i).click();
            if(!pageObjectManager.getCarPage().getCarPageMoreInfoVehicleDescriptionLink().isDisplayed())
            {
                vehicleDescription = false;
            }
            if(!pageObjectManager.getCarPage().getCarPageMoreInfoPoliciesLink().isDisplayed())
            {
                policies = false;
            }
            if(!pageObjectManager.getCarPage().getCarPageMoreInfoLocationsLink().isDisplayed())
            {
                location = false;
            }
//- Step 18: Click on "Less Info" link
            pageObjectManager.getCarPage().getCarsPageLessInfoLink().click();
            WaitUtil.untilTimeCompleted(1200);
            ValidationUtil.validateTestStep("Validating Information collapsed after click o Less link",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageMoreInfoVehicleDescriptionLink()));
        }
        ValidationUtil.validateTestStep("Validating Vehicle Description tab is displayed under More Info link", vehicleDescription);
        ValidationUtil.validateTestStep("Validating Policies tab is displayed under More Info link", policies);
        ValidationUtil.validateTestStep("Validating Location Description tab is displayed under More Info link", location);

//- Step 19: Select the ADD CAR button
        pageObjectManager.getCarPage().getCarsPageBookButton().get(0).click();

        //TODO: No primary driver available when only 1 passenger older than 21 is present on the booking
//- Step 20: Select a Primary driver within the drop down box
//        WaitUtil.untilPageLoadComplete(getDriver());
//       TestUtil.selectDropDownUsingValue(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(),"1:0");
//       WaitUtil.untilTimeCompleted(1000);


//- Step 21: Click on BOOK CAR button again
//        pageObjectManager.getCarPage().getBookCarButton().get(0).click();

//- Step 22: Once back in Options page, verify the original carousel is replaced with a tile view of the selected car with the REMOVE link within it.
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating user is taken back to Options page after car selected",
                getDriver().getCurrentUrl(),(OPTIONS_URL));

        ValidationUtil.validateTestStep("Validating car carousel is replaced with the selected car tile",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarSelectedPanel()));

//- Step 23: Verify "View All" link is suppressed
        try {
            ValidationUtil.validateTestStep("Validating 'View All' link is suppressed ",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getViewAllCarsButton()));
        }catch (Exception e){}

    }
}

//*******************************************************************
//***********************Car Page************************************
//*******************************************************************
//public final String xpath_CarsPageIncludesTaxesLink = "//a[contains(text(),'Includes taxes') or contains(text(),'Incluye impuestos')]";
//    @FindBy(xpath=xpath_CarsPageIncludesTaxesLink)
//    private List<WebElement> lnk_CarsPageIncludesTaxes;
//public List<WebElement> getCarsPageIncludesTaxesLink() { return lnk_CarsPageIncludesTaxes; }