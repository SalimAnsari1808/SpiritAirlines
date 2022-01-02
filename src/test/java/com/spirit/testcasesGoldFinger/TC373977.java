package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:15876] - CP: BP: Options page:  Cars or Hotels sections are not displayed for an International destination (CUN)
//Test Case ID: TC373977
//Description: Task 27886: TC373977 - 016 - CP - SP - Car Verbiage - Dollar - Flight + Hotel + Car - Validate verbiage for a booking with an International Destination
//Created By: Gabriela
//Created On: 2-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373977 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "Spanish", "BookPath", "FlightHotelCar", "DomesticInternational", "Outside21Days", "Adult", "Guest" , "CarsUI"})
    public void Car_Verbiage_Dollar_Flight_Hotel_Car_Validate_verbiage_for_a_booking_with_an_International_Destination(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373977 under GoldFinger Suite on " + platform + " Browser", true);
        }

        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        final String CAR_TITLE          = "Seleccione su auto";
        final String CAR_COMPANY        = "Alamo";
        final String VEHICLE_DESCRIPTION1   = "*Por favor tenga en cuenta";
        final String VEHICLE_DESCRIPTION2   =" La persona que alquilará debe presentar una tarjeta de crédito principal válida a su nombre con un crédito disponible en la cantidad del depósito (si aplica).  Cuando alquile en los EE.UU. se podrían aceptar tarjetas de débito/bancarias de clientes que tienen evidencia de que aterrizaron o desembarcaron en la ubicación de alquiler.   Los clientes deben tener evidencia de pasaje de regreso o viaje de ida en una aerolínea, crucero o tren desde la ubicación en dónde se devolverá el vehículo.";
        final String VEHICLE_DESCRIPTION3   = " Una licencia de conducir válida se requiere para alquilar.  " +CAR_COMPANY + " podría verificar el estado de su licencia de conducir o historial de conductor.  "+CAR_COMPANY+ " podría denegar el alquiler basado en su historial de conductor.   La marca o modelo del auto no se puede garantizar y se podría sustituir por un auto similar o más grande.   Por favor verifique con las instalaciones de alquiler de autos acerca de restricciones adicionales o servicios opcionales.  Los días de alquiler que no sean usados no son elegibles para un reembolso.  Un pago adicional podría ser necesario de solicitarse días de alquiler adicionales al devolver el vehículo.  ";
      final String MINIMUM_AGE_NOTE       = " En algunos estados, los conductores deben tener 25 años de edad o más. Cuando esté permitido, los conductores menores de 25 años de edad pueden estar sujetos a recargos pagaderos directamente al suplidor de autos para alquiler al momento de alquilar.";

//- Step 1: Using Google Chrome, access Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Click on vacation tab, and click on Flight+Hotel+Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Book DOM_INT | 3 months out | 1 ADT | 1 Room | 25+ Driver and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Select any hotel and proceed to the option/cars page
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

//- Step 5: Select Espanol and verify page changes to Spanish
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getSelectedLanguage().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Car Title is displayed on Spanish",
                pageObjectManager.getCarPage().getCarsPagePickYourRideText().getText().equals(CAR_TITLE));

//- Step 6: Search for a Dollar car, select "Mas" link to expand Car tile. // Switching to Alamo
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency(CAR_COMPANY);

        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0).click();
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Validating first Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(0).getText(),VEHICLE_DESCRIPTION1);

        ValidationUtil.validateTestStep("Validating second Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(1).getText(),VEHICLE_DESCRIPTION2);

        ValidationUtil.validateTestStep("Validating third Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(2).getText(),VEHICLE_DESCRIPTION3);

//- Step 7: Click the "Políticas" tab and Verify top to bottom:
        pageObjectManager.getCarPage().getCarPageMoreInfoPoliciesLink().click();
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Minimum Age to Rent Note Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(2).getText(), MINIMUM_AGE_NOTE);

        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().get(0).getText(), "Reembolso por días/horas de alquiler no usadas");

        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours \"NO\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().get(1).getText(), "No");

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(0).getText(), "Se requiere una licencia de conducir válida");

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(1).getText(), "Sí");

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(2).getText(), "El nombre principal en la reservación debe coincidir con el conductor principal y tarjetahabiente de la tarjeta de crédito.");

//- Step 8: Click on the "Ubicacione" tab, verify top to bottom:
        pageObjectManager.getCarPage().getCarPageMoreInfoLocationsLink().click();
        WaitUtil.untilTimeCompleted(3000);

        //Unable to validate in case the information displayed is right or not, due is received by Carnect website
    }
}