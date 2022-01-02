package com.spirit.testcasesGoldFinger;

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
//Test Case ID: TC373263
//Description: Task: 27207 | 009 - CP - Car Tax Verbiage (with Hotel) - Flight + Hotel - Validate the verbiage for a Max (8) Passenger Booking
//Created By: Anthony Cardona
//Created On: 18-Nov-2019
//Reviewed By:Kartik Chauhan
//Reviewed On:10-Dec-2019
//**********************************************************************************************

public class TC373263 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "FlightHotel", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest",
                    "CarsUI", "MasterCard"})
    public void CP_Hotel_Widget_Dynamic_Selection_Validate_the_default_Room_Selection_for_4_passengers(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373263 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "116";
        final String ADULT              = "8";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";

//- Step 1: Open goldfinger website environment on consumer portal
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: On the Home page Select Vacation Flight + Hotel booking | DOM to DOM| outside 48h | 8 ADT | 2 Room |  Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 3: Click on verbiage under the header Choose your Hotel locate and select the hyperlink "taxes and fees" | pop up displayed
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityTaxesAndFeesLink().click();

        //validate popUp
        ValidationUtil.validateTestStep("The Taxes and Fees Pop-up is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpHeaderText()));

//Step 4: Validate that the last sentence under the header PACKAGES/CAR RENTALS in the pop up:
        final String PACKAGES_CAR_RENTALS = "Car rental rates include taxes and airport fees in effect at the time of the booking.";

        //validate popUp
        ValidationUtil.validateTestStep("The Packages/Car Rentals text contains \"Car rental rates include taxes and airport fees in effect at the time of the booking.\"" ,
                PackagesCar_Rentals().getText().contains(PACKAGES_CAR_RENTALS));

        WaitUtil.untilTimeCompleted(1000);
        //close popUp
        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpCloseButton().click();

//Step 5: On the First available Hotel select the View button
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();

        ValidationUtil.validateTestStep("The hotel view option is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelNameText()));

//Step 6: Select the button Rooms From $##.## | validate the getHotelWindowSelectRoomButton buttons are displayed
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("The Select Room buttons are displayed after clicking on Room Price",
            pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().size() > 0 );

//Step 7: Click the Select Room button on the first available Room | The Hotel Room selected is displayed on the Flight + Hotel availability page.
        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();

        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());

        String BOOK_CARS_URL = "spirit.com/book/options/cars";

        ValidationUtil.validateTestStep("The user is taken to the cars page after selecting a hotel"
                , getDriver().getCurrentUrl() , BOOK_CARS_URL);

//Step 10: Click on "Include taxes" hyperlink inside of the Alamo car content box. | Hyperlink opens a pop up
        ValidationUtil.validateTestStep("The Include taxes link is displayed on the cars card  page " ,
                pageObjectManager.getCarPage().getCarsPageIncludesTaxesLink().size() > 0);

        pageObjectManager.getCarPage().getCarsPageIncludesTaxesLink().get(0).click();

//Step 11: Validate the sub header  "Car rental price includes taxes and fees. Additional options such as limited damage or liability coverage, when selected, are payable directly to the rental car agency."
        String carRentalTaxesHeader = "Estimated Car Taxes and Fees";
        String carRentalTaxesText = "Car rental price includes taxes and fees. Additional options such as limited damage or liability coverage, when selected, are payable directly to the rental car agency.";

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("The Car Rental subheader \"Car rental price includes taxes and fees...\" is correct",
                pageObjectManager.getCarPage().getEstimatedTaxesPopUpHeaderText().getText(), carRentalTaxesHeader);


//Step 12: Click the x on the right corner of the pop up. Validate that the pop up closes and the user remains on the Car availability page.
        pageObjectManager.getCarPage().getEstimatedTaxesPopUpCloseButton().click();

        String bookCarsPage = "spirit.com/book/options/cars";

        ValidationUtil.validateTestStep("Verify user nis still on the Cars page after closing Taxes pop-up",
                getDriver().getCurrentUrl(), bookCarsPage);

    }

    public WebElement PackagesCar_Rentals()
    {
        return getDriver().findElement(By.xpath(pageObjectManager.getCarPage().xpath_VacationPackagesCarRentalsText));
    }
}