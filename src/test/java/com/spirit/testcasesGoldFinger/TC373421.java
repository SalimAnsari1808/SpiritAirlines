package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373421
//Description: Task 27870: TC373421 - 014 - CP - Car Tax Verbiage (with Hotel) - Flight + Hotel - Validate the Verbiage for a booking on a Thru Flight
// Created By: Gabriela
//Created On: 30-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 06-Dec-2019
//**********************************************************************************************
public class TC373421 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult", "Guest", "Through", "FlightAvailabilityUI", "CarsUI", "HotelsUI"})
    public void Car_Tax_Verbiage_with_Hotel_Flight_Hotel_Validate_the_Verbiage_for_a_booking_on_a_Thru_Flight(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373421 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "3 Rooms";

        //Car Page Constant Values
        final String TAXES_INFORMATION  = "Package prices include airfare and associated government taxes and carrier charges.  Hotel rates are quoted inclusive of hotel taxes. Additional mandatory resort or destination fees may be charged directly by the hotel. Cleaning fees for Vacation Homes and/or Condominiums are not included. Unless specifically quoted for your reservation, charges for transfers, meals, parking, tips, phone charges, tours, shows, room service, laundry, or other incidentals of a personal nature are not included in advertised rates. Car rental rates include taxes and airport fees in effect at the time of the booking. Additional options chosen such as insurance, underage driver charges (if applicable), fuel, etc. must be paid directly to the rental car company.  In rare cases, changes in taxes since the time of booking may be due at the rental car counter.";

//- Step 1: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: On the Home page Select the Vacation tab, specific Flight + Hotel
        //*** Home Page Methods **/
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Start a Vacation Flight + Hotel booking | DOM to DOM| outside 48h | 5 ADT |  3 Rooms|  Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: On the section Your Selected Departure check is the preselected Flight is a thru Flight, if not click the Edit button for select a thru flight.
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(1));
        WaitUtil.untilTimeCompleted(1200);
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getReturningJourneyEditButton().size(); i ++)
        {
            if(pageObjectManager.getFlightAvailabilityPage().getReturningJourneyEditButton().get(i).isDisplayed())
            {
                pageObjectManager.getFlightAvailabilityPage().getReturningJourneyEditButton().get(i).click();
                WaitUtil.untilTimeCompleted(1200);
            }
        }

        //Storing displayed departure flight in a list
        List<WebElement> departingFlightList = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(i).isDisplayed())
            {
                departingFlightList.add(pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(i));
            }
        }
        System.out.println("departingFlightList:" + departingFlightList.size());
        //Selecting departure through flight
        for (int i =0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(i).getText().equals("1 Stop"))
            {
                pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(i).click();
                WaitUtil.untilPageLoadComplete(getDriver());

                if (pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().equals(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(1).getText()))
                {
                    pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                    WaitUtil.untilTimeCompleted(1200);
                    departingFlightList.get(i-1).click();
                    break;
                }
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                WaitUtil.untilTimeCompleted(1200);

            }
        }
//- Step 5: On the section Your Selected Return check is the preselected Flight is a thru Flight, if not click the Edit button for select a thru flight.
        //Storing displayed flights in a list
        List<WebElement> arrFlightList  = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(i).isDisplayed())
            {
                arrFlightList.add(pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(i));
            }
        }
        List<WebElement> retStops = getDriver().findElements(By.xpath("(//div[@data-qa='journey-results'])[2]//button[@data-qa='journey-stops']"));
        for (int i =0; i < retStops.size(); i ++)
        {
            if (retStops.get(i).getText().equals("1 Stop"))
            {
                retStops.get(i).click();
                WaitUtil.untilPageLoadComplete(getDriver());
                if (pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().equals(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(1).getText()))
                {
                    pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                    WaitUtil.untilTimeCompleted(1200);

                    arrFlightList.get(i-1).click();
                    break;
                }
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                WaitUtil.untilTimeCompleted(1200);
            }
        }
//- Step 6: On the F + H  availability page, in the verbiage under the header Choose your Hotel locate and select the hyperlink "taxes and fees"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityTaxesAndFeesLink().click();
        WaitUtil.untilTimeCompleted(1200);

//- Step 7: Validate that the last sentence under the header PACKAGES/CAR RENTALS in the pop up:
        System.out.println("taxes:" + getDriver().findElements(By.xpath("//app-taxes-and-fees-modal//h2[contains(text(),'Vacation Packages/Car Rentals')]//following-sibling::p[2]")).get(0).getText());
        ValidationUtil.validateTestStep("Validating right verbiage displayed under Taxes and Fees information ",
                getDriver().findElements(By.xpath("//app-taxes-and-fees-modal//h2[contains(text(),'Vacation Packages/Car Rentals')]//following-sibling::p[2]")).get(0).getText(),TAXES_INFORMATION);

        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpCloseButton());
        WaitUtil.untilTimeCompleted(1200);

//- Step 8: On the First available Hotel select the SELECT ROOM button
//- Step 9: Click the Select Room button on the first available Room
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

//- Step 10: On the Car availability page scroll down to a Dollar car content box
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency("Dollar");

//- Step 11: Click "More" in the tile of one of the Dollar cars
        boolean collapsedFlag = true;
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i ++)
        {
            pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(i).click();
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("Validating Car descriptions is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(0)));

//- Step 12: Click the "Less" in the expanded tile. Validate that the tile collapses and the user remains on the Car availability page.
            pageObjectManager.getCarPage().getCarsPageLessInfoLink().click();
            WaitUtil.untilTimeCompleted(1000);

            try {
                if (pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(0).isDisplayed()) {
                    collapsedFlag = false;
                }
            }catch (Exception e){}
        }
        ValidationUtil.validateTestStep("Validating session collapsed after Less link clicked", collapsedFlag);
    }
}