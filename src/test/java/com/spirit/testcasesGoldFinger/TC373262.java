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

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373262
//Description: Task 27206: TC373262 - 008 - CP - Car Tax Verbiage (with Hotel) - Flight + Hotel + Car - Validate the verbiage for a booking on the Spanish Path
//Created By: Gabriela
//Created On: 27-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373262 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest","Through", "FlightAvailabilityUI","CarsUI", "HotelsUI","Spanish"})
    public void CP_Car_Tax_Verbiage_with_Hotel_Flight_Hotel_Car_Validate_the_verbiage_for_a_booking_on_the_Spanish_Path(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373262 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CLE";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";
        final String ROOMS_VALUE        = "4 Rooms";

        //Car Page Constant Values
        final String TAXES_INFORMATION  = "Los precios de los paquetes incluyen pasajes aéreos e impuestos gubernamentales asociados y cargos de transportista. Las tarifas del hotel se incluyen con los impuestos del hotel. El hotel puede cobrar directamente tarifas adicionales de resort o destino directamente. Las tarifas de limpieza para casas de vacaciones y / o condominios no están incluidas. A menos que se especifique lo contrario para su reserva, los cargos por traslados, comidas, estacionamiento, propinas, cargos telefónicos, tours, espectáculos, servicio a la habitación, lavandería u otros imprevistos de naturaleza personal no están incluidos en las tarifas anunciadas. Las tarifas de alquiler de vehículos incluyen impuestos y tasas aeroportuarias vigentes al momento de la reserva. Las opciones adicionales elegidas, como el seguro, los cargos del conductor menor de edad (si corresponde), el combustible, etc. deben pagarse directamente a la compañía de alquiler de automóviles. En casos raros, los cambios en los impuestos desde el momento de la reserva pueden ser debidos en el mostrador de alquiler de automóviles.";

//- Step 1: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: On the Home page Select the Vacation tab, specific Flight + Hotel + Car.
        /*** Home Page Methods **/
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Start a Vacation Flight + Hotel + Car booking | DOM to DOM| outside 48h | 5 ADT | 4 Rooms | Driver's age 25 +|  Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: On the section Your Selected Departure check is the preselected Flight is a thru Flight, if not click the Edit button for select a thru flight.
        /*** Flight Availability Page **/
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
                    pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(i).click();
                    break;
                }
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                WaitUtil.untilTimeCompleted(1200);

            }
        }

        //Selecting returning through flight
        WaitUtil.untilTimeCompleted(1200);
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

                    pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(i).click();
                    break;
                }
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                WaitUtil.untilTimeCompleted(1200);
            }
        }

//- Step 5: On the Header select the Español hyperlink
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getSelectedLanguage().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 6: On the F + H  availability page, in the verbiage under the header Choose your Hotel locate and select the translate hyperlink for "impuestos y comisiones"
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityTaxesAndFeesLink().click();

//- Step 7: Validate that the last sentence under the header PACKAGES/CAR RENTALS in the pop up:
        ValidationUtil.validateTestStep("Validating right verbiage displayed under Taxes and Fees information on Spanish",
                getDriver().findElements(By.xpath("//app-taxes-and-fees-modal//h3[contains(text(),'Paquetes/Alquiler de Auto')]//following-sibling::div")).get(0).getText(),TAXES_INFORMATION);

       JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpCloseButton());

//- Step 8: On the First available Hotel select the View button
//- Step 9: Select the button Rooms From $##.##
//- Step 10: Click the Select Room button on the first available Room
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

//- Step 11: Scroll down to the bottom of the page and select the Continue button.
        //Invalid Step

//- Step 12: On the Car availability page scroll down to a Hertz car content box
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency("Hertz");

//- Step 13: Click on the "Mas" hyperlink within the Dollar car tile
        boolean collapsedFlag = true;
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i ++)
        {
            pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(i).click();
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("Validating Car descriptions is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(0)));

//- Step 14: Click the "Menos" hyperlink in the expanded tile. Validate that the tile collapses and the user remains on the Car availability page.
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