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
//**********************************************************************************************
//Test Case ID: TC373264
//Description: Task: 27208 |  016 - CP - Car Tax Verbiage (with Hotel) - Flight + Hotel - Validate the verbiage for a booking on the Spanish Path
//Created By: Anthony Cardona
//Created On: 20-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373264 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "BookPath", "FlightHotel", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "CarsUI", "MasterCard"})
    public void Car_Tax_Verbiage_with_Hotel_Flight_Hotel_Validate_the_verbiage_for_a_booking_on_the_Spanish_Path(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373264 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ATL";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "137";
        final String ADULT              = "4";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";
//- Step 1: Open goldfinger website environment on consumer portal
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//- Step 2: On the Home page Select the Vacation tab, specific Flight + Hotel
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
//Step 3: Start a Vacation Flight + Hotel booking | DOM to DOM| outside 48h | 4 ADT | 2 Rooms |  Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 4: On the section Your Selected Departure check is the preselected Flight is a thru Flight, if not click the Edit button for select a thru flight.
        //open the edit button for both the departing and returning flights AND SELECT THROUGH FLIGHTS
//        for (WebElement element: pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton())
//        {
//            if (element.isDisplayed()) element.click();
//        }
//        for (WebElement element: pageObjectManager.getFlightAvailabilityPage().getArrivalFlightEditButton())
//        {
//            if (element.isDisplayed()) element.click();
//        }
//
//        //Select the through flights
//        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep" , "Through");
//        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret" , "Through");

//Step 5: On the Header select the Español hyperlink
        //change language to spanish        
        JSExecuteUtil.scrollDownToElementVisible(getDriver() , pageObjectManager.getHeader().getEnglishSpanishLink());
        JSExecuteUtil.clickOnElement(getDriver() , pageObjectManager.getHeader().getEnglishSpanishLink());


//Step 6: click on taxes and fees "impuestos y comisiones"
        //click on taxes and fees
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityTaxesAndFeesLink().click();
        //validate popUp
        ValidationUtil.validateTestStep("The \"impuestos y comisiones\" Pop-up is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpHeaderText()));

//Step 7: Validate that the last sentence under the header PACKAGES/CAR RENTALS i
        //TODO: pending validation of verbiage
        final String SPANISH_PACKAGES_CAR_RENTALS = "Las tarifas de alquiler de vehículos incluyen impuestos y tasas aeroportuarias vigentes al momento de la reserva.";
        //validate popUp
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The Packages/Car Rentals text contains \"Las tarifas de alquiler de vehículos incluyen impuestos...\" text is displayed" ,
                PackagesCar_Rentals().getText().contains(SPANISH_PACKAGES_CAR_RENTALS));
        WaitUtil.untilTimeCompleted(1000);
        //close popUp
        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpCloseButton().click();

//Step 8: On the First available Hotel select the View button
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        ValidationUtil.validateTestStep("The hotel view option is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelNameText()));
//Step 9: Select the button Rooms From $##.## | validate the getHotelWindowSelectRoomButton buttons are displayed
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("The Select Room buttons are displayed after clicking on Room Price",
                pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().size() > 0 );
//Step 10: Click the Select Room button on the first available Room | The Hotel Room selected is displayed on the Flight + Hotel availability page.
        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();
        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());
        String BOOK_CARS_URL = "spirit.com/book/options/cars";
        ValidationUtil.validateTestStep("The user is taken to the cars page after selecting a hotel"
                , getDriver().getCurrentUrl() , BOOK_CARS_URL);

//Step 12: Scroll down to a hertz car
        int carsIterated = 0;
        for(WebElement carCompany : pageObjectManager.getCarPage().getCarsPageCarTypeText())
        {
            if (carCompany.getText().toLowerCase().contains("hertz"))
            {
                JSExecuteUtil.scrollDownToElementVisible(getDriver() , carCompany);
                break;
            }
            carsIterated++;
        }

//Step 13: Click on "Mas" hyperlink to reveal car content box for Hertz car | Verify tile expands and vehicle description is displayed
        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(carsIterated).click();

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("The \"Mas\" button is clicked and Vehicle description tile is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab()));

//Step 14: Click the "Menos" hyperlink and validate that hte vehicle description is no longer displayed
        pageObjectManager.getCarPage().getCarsPageLessInfoLink().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("The \"Menos\" button is clicked and Vehicle description tile is no longer displayed" ,
                !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab()));

    }
    public WebElement PackagesCar_Rentals()
    {
        return getDriver().findElement(By.xpath("//app-taxes-and-fees-modal//h3[contains(text(), 'Paquetes/Alquiler de Auto')]//following::div[1]"));
    }
}